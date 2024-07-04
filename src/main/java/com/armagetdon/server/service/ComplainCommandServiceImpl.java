package com.armagetdon.server.service;

import com.armagetdon.server.converter.ComplainConverter;
import com.armagetdon.server.domain.Complain;
import com.armagetdon.server.dto.ComplainRequestDTO;
import com.armagetdon.server.repository.ComplainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ComplainCommandServiceImpl implements ComplainCommandService{

    private final ComplainRepository complainRepository;

    @Override
    @Transactional
    public Complain askComplain(ComplainRequestDTO.complainPostDTO request){
        Complain newComplain = ComplainConverter.toComplain(request);
        return complainRepository.save(newComplain);
    }
}
