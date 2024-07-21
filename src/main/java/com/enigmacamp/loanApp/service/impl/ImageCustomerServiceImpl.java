package com.enigmacamp.loanApp.service.impl;

import com.enigmacamp.loanApp.constant.ResponseMessageImage;
import com.enigmacamp.loanApp.model.entity.Customer;
import com.enigmacamp.loanApp.model.entity.ImageCustomer;
import com.enigmacamp.loanApp.repository.CustomerRepository;
import com.enigmacamp.loanApp.repository.ImageCustomerRepository;
import com.enigmacamp.loanApp.service.CustomerService;
import com.enigmacamp.loanApp.service.ImageCustomerService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageCustomerServiceImpl implements ImageCustomerService {
    private final ImageCustomerRepository imageCustomerRepository;
    private final Path directoryPath;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @Autowired
    public ImageCustomerServiceImpl(@Value("${app.loanshop.customer.multipart.path-location}") String directoryPath,
                                    ImageCustomerRepository imageCustomerRepository,
                                    CustomerService customerService,
                                    CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.directoryPath = Paths.get(directoryPath);
        this.imageCustomerRepository = imageCustomerRepository;
    }

    @PostConstruct
    public void initDirectory() {
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
    }


    @Override
    public ImageCustomer uploadImage(MultipartFile multipartFile, String id) {
        try {
            if (!List.of("image/jpeg", "image/png", "image/jpg", "image/svg+xml").contains(multipartFile.getContentType()))
                throw new ConstraintViolationException(ResponseMessageImage.ERROR_IMAGE_CONTENT_TYPE, null);
            String uniqueFilename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            Path filePath = directoryPath.resolve(uniqueFilename);
            Files.copy(multipartFile.getInputStream(), filePath);

            Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessageImage.USER_NOT_FOUND));

            ImageCustomer imageCustomer = ImageCustomer.builder()
                    .name(uniqueFilename)
                    .contentType(multipartFile.getContentType())
                    .size(multipartFile.getSize())
                    .path(filePath.toString())
                    .build();
            imageCustomerRepository.saveAndFlush(imageCustomer);


            customer.setImageCustomer(imageCustomer);
            customerRepository.saveAndFlush(customer);


            return imageCustomer;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

}
