package com.armagetdon.server.apiPayload.exception.handler;

import com.armagetdon.server.apiPayload.code.BaseErrorCode;
import com.armagetdon.server.apiPayload.exception.GeneralException;

public class PostImageHandler extends GeneralException {

    public PostImageHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}