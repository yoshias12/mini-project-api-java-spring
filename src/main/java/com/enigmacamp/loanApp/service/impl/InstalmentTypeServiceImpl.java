package com.enigmacamp.loanApp.service.impl;

import com.enigmacamp.loanApp.model.entity.InstalmentType;
import com.enigmacamp.loanApp.repository.InstalmentTypeRepository;
import com.enigmacamp.loanApp.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstalmentTypeServiceImpl implements InstalmentTypeService {
    private final InstalmentTypeRepository instalmentTypeRepository;

    @Override
    public InstalmentType create(InstalmentType request) {
//        if (!request.getInstalmentType().equals(InstalmentType.EInstalmentType.values())){
//            throw new IllegalArgumentException("Invalid instalment type.");
//        }
//
//        return InstalmentType.builder()
//
//                .build();
        return instalmentTypeRepository.save(request);

    }

    @Override
    public InstalmentType getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public List<InstalmentType> getAllInstalments() {
        return instalmentTypeRepository.findAll();
    }

    @Override
    public InstalmentType update(InstalmentType request) {
        getById(request.getId());
        return instalmentTypeRepository.saveAndFlush(
                InstalmentType.builder()
                        .id(request.getId())
                        .instalmentType(request.getInstalmentType())
                        .build()
        );
    }

    @Override
    public void delete(String id) {
        getById(id);
        instalmentTypeRepository.deleteById(id);
    }

    private InstalmentType findByIdOrThrowNotFound(String id) {
        return instalmentTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found"));
    }
}
