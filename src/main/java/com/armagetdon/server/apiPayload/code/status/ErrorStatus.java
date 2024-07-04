package com.armagetdon.server.apiPayload.code.status;

import com.armagetdon.server.apiPayload.code.BaseErrorCode;
import com.armagetdon.server.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가본 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "IMAGE400", "이미지가 없습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST400", "게시물이 없습니다."),
    /**
     * 400
     */
    _INVALID_REWARD(HttpStatus.BAD_REQUEST, "400", "잘못된 리워드값입니다."),


    /**
     * 404
     */
    _NOT_EXIST_MEMBER(HttpStatus.NOT_FOUND, "404", "존재하지 않는 멤버입니다."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER400", "멤버가 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
