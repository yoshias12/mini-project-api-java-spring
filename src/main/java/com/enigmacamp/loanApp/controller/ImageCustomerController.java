package com.enigmacamp.loanApp.controller;

import com.enigmacamp.loanApp.model.dto.response.CommonResponse;
import com.enigmacamp.loanApp.model.dto.response.ImageCustomerResponse;
import com.enigmacamp.loanApp.model.entity.ImageCustomer;
import com.enigmacamp.loanApp.service.ImageCustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/customers")
public class ImageCustomerController {
   private final ImageCustomerService imageCustomerService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    @PostMapping("/{customerId}/upload/avatar")
    public ResponseEntity<CommonResponse<ImageCustomerResponse>> uploadImageCustomer (@PathVariable String customerId, @RequestParam("avatar") MultipartFile image, HttpServletRequest request){
        ImageCustomer fileName = imageCustomerService.uploadImage(image,customerId);

        String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/customers/")
                .path(customerId)
                .path("/avatar")
                .path(fileName.getName())
                .toUriString();

        ImageCustomerResponse imageCustomerResponse = ImageCustomerResponse.builder()
                .name(fileName.getName())
                .url(fileDownloadUrl)
                .build();

        CommonResponse<ImageCustomerResponse> commonResponse = CommonResponse.<ImageCustomerResponse>builder()
                .message("File uploaded successfully")
                .data(Optional.of(imageCustomerResponse))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }
}
