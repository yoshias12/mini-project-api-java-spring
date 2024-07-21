package com.enigmacamp.loanApp.service;

import com.enigmacamp.loanApp.model.entity.ImageCustomer;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageCustomerService {
    ImageCustomer uploadImage(MultipartFile multipartFile, String id);

}
