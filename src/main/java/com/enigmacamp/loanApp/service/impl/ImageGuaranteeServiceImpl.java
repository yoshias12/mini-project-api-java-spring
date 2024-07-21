package com.enigmacamp.loanApp.service.impl;

import com.enigmacamp.loanApp.constant.ResponseMessageImage;
import com.enigmacamp.loanApp.model.entity.ImageGuarantee;
import com.enigmacamp.loanApp.model.entity.LoanTransactionDetail;
import com.enigmacamp.loanApp.repository.ImageGuaranteeRepository;
import com.enigmacamp.loanApp.repository.LoanTransactionDetailRepository;
import com.enigmacamp.loanApp.service.ImageGuaranteeService;
import com.enigmacamp.loanApp.service.LoanTransactionDetailService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolationException;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageGuaranteeServiceImpl implements ImageGuaranteeService {
    private final ImageGuaranteeRepository imageGuaranteeRepository;
    private final Path directoryPath;
    private final LoanTransactionDetailService loanTransactionDetailService;
    private final LoanTransactionDetailRepository loanTransactionDetailRepository;

    @Autowired
    public ImageGuaranteeServiceImpl(@Value("${app.loanshop.guarantee.multipart.path-location}") String directoryPath,
                                     ImageGuaranteeRepository imageGuaranteeRepository,
                                     LoanTransactionDetailService loanTransactionDetailService,
                                     LoanTransactionDetailRepository loanTransactionDetailRepository) {
        this.imageGuaranteeRepository = imageGuaranteeRepository;
        this.loanTransactionDetailRepository = loanTransactionDetailRepository;
        this.directoryPath = Paths.get(directoryPath);
        this.loanTransactionDetailService = loanTransactionDetailService;
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
    public ImageGuarantee uploadImage(MultipartFile multipartFile, String id) {
        try {
            if (!List.of("image/jpeg", "image/png", "image/jpg", "image/svg+xml").contains(multipartFile.getContentType()))
                throw new ConstraintViolationException(ResponseMessageImage.ERROR_IMAGE_CONTENT_TYPE, null);
            String uniqueFilename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            Path filePath = directoryPath.resolve(uniqueFilename);
            Files.copy(multipartFile.getInputStream(), filePath);

            LoanTransactionDetail loanTransactionDetail = loanTransactionDetailService.findById(id);

            ImageGuarantee imageGuarantee = ImageGuarantee.builder()
                    .contentType(multipartFile.getContentType())
                    .name(uniqueFilename)
                    .path(filePath.toString())
                    .size(multipartFile.getSize())
                    .build();

            imageGuaranteeRepository.saveAndFlush(imageGuarantee);
            loanTransactionDetail.setGuaranteePictureId(imageGuarantee);

            loanTransactionDetailRepository.saveAndFlush(loanTransactionDetail);


            return imageGuarantee;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }



    @Override
    public byte[] loadImage(String filename) {
        String fileName = null;
        try {
            fileName = "assets/images/guarantee/" + filename;
            Path path = Paths.get(fileName);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Thumbnails.of(path.toFile())
                    .size(200, 200)
                    .outputFormat("JPEG")
                    .toOutputStream(outputStream);

            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Could not load image" + fileName + ": " + e.getMessage());
        }
    }
}
