package com.armagetdon.server.service;

import com.armagetdon.server.domain.Complain;
import com.armagetdon.server.dto.ComplainRequestDTO;

public interface ComplainCommandService {

    public Complain askComplain(ComplainRequestDTO.complainPostDTO request);
}
