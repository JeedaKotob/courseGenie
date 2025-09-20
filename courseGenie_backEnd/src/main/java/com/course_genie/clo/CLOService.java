package com.course_genie.clo;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CLOService {
    private final CLORepository cloRepository;
    private final CLOMapper cloMapper;
    private final CLODTOMapper cloDTOMapper;

    public CLOService(CLORepository cloRepository, CLOMapper cloMapper, CLODTOMapper cloDTOMapper) {
        this.cloRepository = cloRepository;
        this.cloMapper = cloMapper;
        this.cloDTOMapper = cloDTOMapper;
    }

    // Create
    public CLODTO createClo(CLODTO cloDTO) {
        return cloDTOMapper.apply(cloRepository.save(cloMapper.apply(cloDTO)));
    }

    // Read
    public List<CLODTO> getAllClos() {
        return cloRepository.findAll().stream().map(cloDTOMapper).collect(Collectors.toList());
    }

    public CLODTO getCloById(long cloId) {
        return cloDTOMapper.apply(cloRepository.findById(cloId).orElseThrow(()-> new EntityNotFoundException("clo not found")));
    }

    // Update
    public CLODTO updateClo(CLODTO cloDTO) {
        CLO clo = cloRepository.findById(cloDTO.cloId()).orElseThrow(() -> new EntityNotFoundException("Clo not found"));
        clo.setName(cloDTO.name());
        clo.setDescription(cloDTO.description());
        return cloDTOMapper.apply(cloRepository.save(clo));
    }

    // Delete
    public void deleteClo(long cloId) {
        CLO clo = cloRepository.findById(cloId).orElseThrow(() -> new EntityNotFoundException("Clo not found"));
        cloRepository.delete(clo);
    }
}
