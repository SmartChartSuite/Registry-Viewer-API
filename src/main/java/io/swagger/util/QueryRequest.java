package io.swagger.util;

public enum QueryRequest {
    RUNNING(0x0001, "RUNNING", "Case is running for case monitoring.", "information", "Case is running in normal mode."),
    END(0x0002, "END", "Case is at the end of active monitoring period.", "information", "Case is outside of monitoring period and not tracked."),
    REQUEST_PENDING(0x0004, "REQUEST_PENDING", "Request to Query for case is queued but not yet in running queue.", "information", "Job is pending. The number of allowed concurrent jobs can be configured."), 
    TIMED_OUT(0x0008, "TIMED_OUT", "Retry count reached the maximum allowed count. Giving up.", "paused", "Job taking longer than expected. This may indicate a temporary network error. If this error persists, please contact technical support. (0x0008)."), 
    ERROR_IN_CLIENT(0x0100, "ERROR_IN_CLIENT", "Error occurred on client side during Request. Request is paused.", "fatal", "Registry internal error occurred. Please contact technical support (0x0100)."), 
    ERROR_IN_SERVER(0x0200, "ERROR_IN_SERVER", "Error occurred on server side during Request. Request will be made again until it reaches the maximum retry-count.", "fatal", "Registry encountered an error in the backend services. Please contact technical support (0x0200)."), 
    ERROR_UNKNOWN(0x0400, "ERROR_UNKNOWN", "Unknown error occurred. Some resources in result bundle failed to be imported to db.", "error", "Error occurred during data import to registry. Please contact technical support (0x0400)"), 
    INVALID(0x0000, "INVALID", "Invalid Status. Retrigger with a valid status is required.", "warning", "not in use (0x0000)");

    private int code;
    private String codeString;
    private String desc;
    private String uiCode;
    private String uiDesc;

    QueryRequest(int code, String codeString, String desc, String uiCode, String uiDesc) {
        this.code = code;
        this.codeString = codeString;
        this.desc = desc;
        this.uiCode = uiCode;
        this.uiDesc = uiDesc;
    }

    public int getCode() {
        return this.code;
    }

    public String getCodeString() {
        return this.codeString;
    }

    public String getDesc() {
        return this.desc;
    }
    
    public static QueryRequest codeEnumOf(String s) {
        QueryRequest retv = INVALID;

        if ("RUNNING".equals(s)) retv = RUNNING;
        else if ("END".equals(s)) retv =  END;
        else if ("REQUEST_PENDING".equals(s)) retv = REQUEST_PENDING;
        else if ("TIMED_OUT".equals(s)) return TIMED_OUT;
        else if ("ERROR_IN_CLIENT".equals(s)) return ERROR_IN_CLIENT;
        else if ("ERROR_IN_SERVER".equals(s)) return ERROR_IN_SERVER;
        else if ("ERROR_UNKNOWN".equals(s)) return ERROR_UNKNOWN;

        return retv;
    }

    public static int codeOf(String s) {
        int retv = INVALID.code;

        if ("RUNNING".equals(s)) retv = RUNNING.code;
        else if ("END".equals(s)) retv =  END.code;
        else if ("REQUEST_PENDING".equals(s)) retv = REQUEST_PENDING.code;
        else if ("TIMED_OUT".equals(s)) return TIMED_OUT.code;
        else if ("ERROR_IN_CLIENT".equals(s)) return ERROR_IN_CLIENT.code;
        else if ("ERROR_IN_SERVER".equals(s)) return ERROR_IN_SERVER.code;
        else if ("ERROR_UNKNOWN".equals(s)) return ERROR_UNKNOWN.code;

        return retv;
    }

    public static String descOf(String s) {
        String retv = INVALID.desc;

        if ("RUNNING".equals(s)) retv = RUNNING.desc;
        else if ("END".equals(s)) retv =  END.desc;
        else if ("REQUEST_PENDING".equals(s)) retv = REQUEST_PENDING.desc;
        else if ("TIMED_OUT".equals(s)) return TIMED_OUT.desc;
        else if ("ERROR_IN_CLIENT".equals(s)) return ERROR_IN_CLIENT.desc;
        else if ("ERROR_IN_SERVER".equals(s)) return ERROR_IN_SERVER.desc;
        else if ("ERROR_UNKNOWN".equals(s)) return ERROR_UNKNOWN.desc;

        return retv;
    }

    public static String uiCodeOf(String s) {
        String retv = INVALID.uiCode;

        if ("RUNNING".equals(s)) retv = RUNNING.uiCode;
        else if ("END".equals(s)) retv =  END.uiCode;
        else if ("REQUEST_PENDING".equals(s)) retv = REQUEST_PENDING.uiCode;
        else if ("TIMED_OUT".equals(s)) return TIMED_OUT.uiCode;
        else if ("ERROR_IN_CLIENT".equals(s)) return ERROR_IN_CLIENT.uiCode;
        else if ("ERROR_IN_SERVER".equals(s)) return ERROR_IN_SERVER.uiCode;
        else if ("ERROR_UNKNOWN".equals(s)) return ERROR_UNKNOWN.uiCode;

        return retv;
    }

    public static String uiDescOf(String s) {
        String retv = INVALID.uiDesc;

        if ("RUNNING".equals(s)) retv = RUNNING.uiDesc;
        else if ("END".equals(s)) retv =  END.uiDesc;
        else if ("REQUEST_PENDING".equals(s)) retv = REQUEST_PENDING.uiDesc;
        else if ("TIMED_OUT".equals(s)) return TIMED_OUT.uiDesc;
        else if ("ERROR_IN_CLIENT".equals(s)) return ERROR_IN_CLIENT.uiDesc;
        else if ("ERROR_IN_SERVER".equals(s)) return ERROR_IN_SERVER.uiDesc;
        else if ("ERROR_UNKNOWN".equals(s)) return ERROR_UNKNOWN.uiDesc;

        return retv;
    }

    public static String codesFromUiCode(String c) {
        String retv = null;
        if ("paused".equals(c)) retv = TIMED_OUT.codeString;
        if ("fatal".equals(c)) retv = ERROR_IN_CLIENT.codeString + "," + ERROR_IN_SERVER.codeString;
        if ("error".equals(c)) retv = ERROR_UNKNOWN.codeString;
        if ("information".equals(c)) retv = RUNNING.codeString + "," + REQUEST_PENDING.codeString + "," + END.codeString;

        return retv;
    }
}