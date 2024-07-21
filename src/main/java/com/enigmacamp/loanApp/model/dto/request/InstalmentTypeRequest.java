package com.enigmacamp.loanApp.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Setter Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstalmentTypeRequest {
    private String instalmentType;
}
