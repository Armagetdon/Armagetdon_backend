package com.armagetdon.server.apiPayload.exception.handler;

import com.armagetdon.server.apiPayload.code.BaseErrorCode;
import com.armagetdon.server.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
