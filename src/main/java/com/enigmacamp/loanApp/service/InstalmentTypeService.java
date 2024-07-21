package com.enigmacamp.loanApp.service;

import com.enigmacamp.loanApp.model.dto.request.InstalmentTypeRequest;
import com.enigmacamp.loanApp.model.entity.InstalmentType;

import java.util.List;

public interface InstalmentTypeService {
    InstalmentType create(InstalmentType request);
    InstalmentType getById(String id);
    List<InstalmentType> getAllInstalments();
    InstalmentType update(InstalmentType request);
    void delete(String id);
}

