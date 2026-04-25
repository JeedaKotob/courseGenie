package com.course_genie.clo;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CLOService {
    private static final Pattern CLO_NAME_PATTERN = Pattern.compile("^CLO\\d+$", Pattern.CASE_INSENSITIVE);
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
        String normalizedName = normalizeAndValidateCloName(cloDTO.name());
        CLO normalized = cloMapper.apply(CLODTO.builder()
                .cloId(cloDTO.cloId())
                .name(normalizedName)
                .description(cloDTO.description())
                .courseId(cloDTO.courseId())
                .build());
        return cloDTOMapper.apply(cloRepository.save(normalized));
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
        clo.setName(normalizeAndValidateCloName(cloDTO.name()));
        clo.setDescription(cloDTO.description());
        return cloDTOMapper.apply(cloRepository.save(clo));
    }

    // Delete
    public void deleteClo(long cloId) {
        CLO clo = cloRepository.findById(cloId).orElseThrow(() -> new EntityNotFoundException("Clo not found"));
        cloRepository.delete(clo);
    }

    private String normalizeAndValidateCloName(String inputName) {
        String normalized = inputName == null ? "" : inputName.trim().toUpperCase(Locale.ROOT);
        if (!CLO_NAME_PATTERN.matcher(normalized).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CLO name must be in format CLO1, CLO2, etc.");
        }
        return normalized;
    }
}
