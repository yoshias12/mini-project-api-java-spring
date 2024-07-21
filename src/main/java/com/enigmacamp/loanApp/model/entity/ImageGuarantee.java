package com.enigmacamp.loanApp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_guarantee_picture")
public class ImageGuarantee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "content_type")
    private String contentType;
    private String name;
    private String path;
    private Long size;
}
