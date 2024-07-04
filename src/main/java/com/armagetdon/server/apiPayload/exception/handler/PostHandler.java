package com.armagetdon.server.apiPayload.exception.handler;

import com.armagetdon.server.apiPayload.code.BaseErrorCode;
import com.armagetdon.server.apiPayload.exception.GeneralException;

public class PostHandler extends GeneralException {

    public PostHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
