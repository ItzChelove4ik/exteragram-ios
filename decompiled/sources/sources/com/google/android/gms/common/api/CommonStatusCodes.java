package com.google.android.gms.common.api;

import com.google.android.gms.cast.MediaError;

/* loaded from: classes4.dex */
public abstract class CommonStatusCodes {
    public static final int API_NOT_CONNECTED = 17;
    public static final int CANCELED = 16;
    public static final int CONNECTION_SUSPENDED_DURING_CALL = 20;
    public static final int DEVELOPER_ERROR = 10;
    public static final int ERROR = 13;
    public static final int INTERNAL_ERROR = 8;
    public static final int INTERRUPTED = 14;
    public static final int INVALID_ACCOUNT = 5;
    public static final int NETWORK_ERROR = 7;
    public static final int RECONNECTION_TIMED_OUT = 22;
    public static final int RECONNECTION_TIMED_OUT_DURING_UPDATE = 21;
    public static final int REMOTE_EXCEPTION = 19;
    public static final int RESOLUTION_REQUIRED = 6;

    @Deprecated
    public static final int SERVICE_DISABLED = 3;

    @Deprecated
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
    public static final int SIGN_IN_REQUIRED = 4;
    public static final int SUCCESS = 0;
    public static final int SUCCESS_CACHE = -1;
    public static final int TIMEOUT = 15;

    public CommonStatusCodes() {
    }

    public static String getStatusCodeString(int r2) {
        switch(r2) {
            case -1: goto L43;
            case 0: goto L41;
            case 1: goto L3;
            case 2: goto L39;
            case 3: goto L37;
            case 4: goto L35;
            case 5: goto L33;
            case 6: goto L31;
            case 7: goto L29;
            case 8: goto L27;
            case 9: goto L3;
            case 10: goto L25;
            case 11: goto L3;
            case 12: goto L3;
            case 13: goto L23;
            case 14: goto L21;
            case 15: goto L19;
            case 16: goto L17;
            case 17: goto L15;
            case 18: goto L13;
            case 19: goto L11;
            case 20: goto L9;
            case 21: goto L7;
            case 22: goto L5;
            default: goto L3;
        };
    L3:
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 21);
        r1.append("unknown status code: ");
        r1.append(r2);
        return r1.toString();
    L5:
        return "RECONNECTION_TIMED_OUT";
    L7:
        return "RECONNECTION_TIMED_OUT_DURING_UPDATE";
    L9:
        return "CONNECTION_SUSPENDED_DURING_CALL";
    L11:
        return "REMOTE_EXCEPTION";
    L13:
        return "DEAD_CLIENT";
    L15:
        return "API_NOT_CONNECTED";
    L17:
        return "CANCELED";
    L19:
        return "TIMEOUT";
    L21:
        return "INTERRUPTED";
    L23:
        return MediaError.ERROR_TYPE_ERROR;
    L25:
        return "DEVELOPER_ERROR";
    L27:
        return "INTERNAL_ERROR";
    L29:
        return "NETWORK_ERROR";
    L31:
        return "RESOLUTION_REQUIRED";
    L33:
        return "INVALID_ACCOUNT";
    L35:
        return "SIGN_IN_REQUIRED";
    L37:
        return "SERVICE_DISABLED";
    L39:
        return "SERVICE_VERSION_UPDATE_REQUIRED";
    L41:
        return "SUCCESS";
    L43:
        return "SUCCESS_CACHE";
    }
}
