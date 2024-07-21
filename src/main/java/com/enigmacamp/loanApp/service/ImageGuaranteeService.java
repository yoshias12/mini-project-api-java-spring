package com.enigmacamp.loanApp.service;

import com.enigmacamp.loanApp.model.entity.ImageCustomer;
import com.enigmacamp.loanApp.model.entity.ImageGuarantee;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageGuaranteeService {
    ImageGuarantee uploadImage(MultipartFile multipartFile, String id);
    byte[] loadImage(String filename);
}
