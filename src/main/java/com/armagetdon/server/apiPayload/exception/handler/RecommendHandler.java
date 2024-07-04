package com.armagetdon.server.apiPayload.exception.handler;

import com.armagetdon.server.apiPayload.code.BaseErrorCode;
import com.armagetdon.server.apiPayload.exception.GeneralException;

public class RecommendHandler extends GeneralException {
    public RecommendHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
