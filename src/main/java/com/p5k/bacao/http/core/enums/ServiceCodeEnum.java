/*
 * Copyright (c) 2023 Smartee Vina. All rights reserved.
 *
 */

package com.p5k.bacao.http.core.enums;

import lombok.Getter;


@Getter
public enum ServiceCodeEnum {
    //AUTH - 01
    AUTH_EXCEPTION(23030100, "AuthException"),
    AUTH_EXCEPTION_UNAUTHORIZED(23030101, "AuthException.Unauthorized"),
    AUTH_EXCEPTION_USERNAME_IS_NOT_FOUND(23030102, "AuthException.UsernameNotFound"),
    AUTH_EXCEPTION_AUTHENTICATION_FAIL(23030103, "AuthException.AuthenticationFail"),
    AUTH_EXCEPTION_TOKEN_REFRESH_EXPIRED(23030104, "AuthException.TokenRefreshExpired"),
    AUTH_EXCEPTION_TOKEN_CANNOT_CREATE(23030105, "AuthException.TokenCannotCreate"),
    AUTH_EXCEPTION_ROLE_NAME_DUPLICATED(23030106, "AuthException.RoleNameDuplicated"),
    AUTH_EXCEPTION_PROD_CD_DUPLICATED(23030107, "AuthException.ProgCdDuplicated"),
    AUTH_EXCEPTION_BTN_CD_DUPLICATED(23030108, "AuthException.BtnCdDuplicated"),
    AUTH_EXCEPTION_AUTHENTICATION_SNS(23030110, "AuthException.FailVerifySns"),

    //USER - 02
    USER_EXCEPTION_USER_NAME_DUPLICATED(23030200, "UserException.UsernameDuplicated"),
    USER_EXCEPTION_EMAIL_DUPLICATED(23030201, "UserException.EmailDuplicated"),
    USER_EXCEPTION_MOBILE_DUPLICATED(23030202, "UserException.MobileDuplicated"),
    USER_EXCEPTION_OLD_PASSWORD_NOT_MATCHING(23030203, "UserException.OldPasswordNotMatching"),
    USER_EXCEPTION_USER_NOT_FOUND(23030204, "UserException.UserNotFound"),
    USER_EXCEPTION_CONFIRM_PASSWORD_DOES_NOT_MATCH(23030205, "UserException.ConfirmPasswordDoesNotMatch"),
    USER_EXCEPTION_ROLE_INVALID(23030206, "UserException.RoleInvalid"),
    //FILE - 03
    FILE_EXCEPTION_CANNOT_UPLOAD_DOCUMENT(23030300, "FileException.CannotUploadDocument"),
    FILE_EXCEPTION_FILE_EXTENSION_NOT_SUPPORT(23030301, "FileException.FileExtensionNotSupport"),
    FILE_EXCEPTION_DOCUMENT_TYPE_NOT_SUPPORT(23030302, "FileException.DocumentTypeNotSupport"),
    FILE_EXCEPTION_FILE_EMPTY(23030303, "FileException.FilesAreEmpty"),
    //TRANSACTION - 04
    TRANS_EXCEPTION_TRANSACTION_FAIL(23030400, "TransException.TransactionFail"),
    TRANS_EXCEPTION_TRANSACTION_NOT_FOUND(23030401, "TransException.TransactionNotFound"),
    //OTHER - 05
    PROCESSING_FAILED(23030500, "ProcessingFailed"),
    PROCESSING_SUCCESS(23030501, "Success"),
    REQUEST_DATA_NOT_FOUND(23030502, "Exception.RequestDataNotFound"),
    DATA_NOT_FOUND(23030503, "Exception.DataNotFound"),
    INTERNAL_SERVER_ERROR(23030504, "Exception.InternalServerError"),
    EVENT_NOT_FOUND(23030505,"Exception.EventNotFound"),

    // COMMUNICATION ERROR - 06
    COMMUNICATION_PROCESS_FAILED(23030600, "Exception.RequestDataNotFound"),

    // COMMON - 07
    CM_EXCEPTION_ID_CANNOT_EMPTY(24020700, "Exception.IdCannotEmpty"),
    CM_EXCEPTION_BAD_ARGUMENT(24020701, "Exception.BadArgument"),
    CM_EXCEPTION_DATA_EMPTY(24020702, "Exception.DataEmpty"),
    CM_EXCEPTION_NOT_FOUND(24020702, "Exception.DataNotFound")
    ;
    private final int code;
    private final String message;

    ServiceCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ServiceCodeEnum findByCode(int code) {
        for (ServiceCodeEnum codeMap : values()) {
            if (codeMap.code == code) {
                return codeMap;
            }
        }
        return INTERNAL_SERVER_ERROR;
    }

}
