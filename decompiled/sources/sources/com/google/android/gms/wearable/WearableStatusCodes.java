package com.google.android.gms.wearable;

import com.google.android.gms.common.api.CommonStatusCodes;

/* loaded from: classes5.dex */
public abstract class WearableStatusCodes extends CommonStatusCodes {
    public static String getStatusCodeString(int r0) {
        switch(r0) {
            case 4000: goto L25;
            case 4001: goto L23;
            case 4002: goto L21;
            case 4003: goto L19;
            case 4004: goto L17;
            case 4005: goto L15;
            case 4006: goto L13;
            case 4007: goto L11;
            case 4008: goto L9;
            case 4009: goto L7;
            case 4010: goto L5;
            default: goto L4;
        };
    L5:
        return "ACCOUNT_KEY_CREATION_FAILED";
    L7:
        return "UNSUPPORTED_BY_TARGET";
    L9:
        return "WIFI_CREDENTIAL_SYNC_NO_CREDENTIAL_FETCHED";
    L11:
        return "UNKNOWN_CAPABILITY";
    L13:
        return "DUPLICATE_CAPABILITY";
    L15:
        return "ASSET_UNAVAILABLE";
    L17:
        return "INVALID_TARGET_NODE";
    L19:
        return "DATA_ITEM_TOO_LARGE";
    L21:
        return "UNKNOWN_LISTENER";
    L23:
        return "DUPLICATE_LISTENER";
    L25:
        return "TARGET_NODE_NOT_CONNECTED";
    L4:
        return CommonStatusCodes.getStatusCodeString(r0);
    }
}
