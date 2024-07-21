package com.enigmacamp.loanApp.controller;

import com.enigmacamp.loanApp.model.dto.response.CommonResponse;
import com.enigmacamp.loanApp.model.entity.ImageGuarantee;
import com.enigmacamp.loanApp.service.ImageGuaranteeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/transactions")
public class ImageGuaranteeController {
    private final ImageGuaranteeService imageGuaranteeService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF', 'ROLE_CUSTOMER')")
    @PostMapping("/{trxId}/upload/images")
    public ResponseEntity<CommonResponse<ImageGuarantee>> uploadImageGuarantee(@PathVariable String trxId, @RequestParam("images")MultipartFile image){
        ImageGuarantee imageGuarantee = imageGuaranteeService.uploadImage(image, trxId);
        CommonResponse<ImageGuarantee> response = CommonResponse.<ImageGuarantee>builder()
                .message("Image uploaded successfully")
                .data(Optional.ofNullable(imageGuarantee))
                .build();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF' ,'ROLE_CUSTOMER')")
    @GetMapping("/download/images/{filename}")
    public ResponseEntity<byte[]> loadAvatar(@PathVariable String filename, HttpServletRequest request) {
        byte[] imageBytes = imageGuaranteeService.loadImage(filename);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

}

