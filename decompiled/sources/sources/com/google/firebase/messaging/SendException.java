package com.google.firebase.messaging;

import java.util.Locale;

/* loaded from: classes5.dex */
public final class SendException extends Exception {
    private final int errorCode;

    public SendException(String r1) {
        super(r1);
        this.errorCode = parseErrorCode(r1);
    }

    private int parseErrorCode(String r7) {
        if (r7 != null) goto L5;
        return 0;
    L5:
        String r72 = r7.toLowerCase(Locale.US);
        r72.getClass();
        char r5 = 65535;
        switch(r72.hashCode()) {
            case -1743242157: goto L25;
            case -1290953729: goto L21;
            case -920906446: goto L17;
            case -617027085: goto L13;
            case -95047692: goto L9;
            default: goto L28;
        };
    L28:
        switch(r5) {
            case 0: goto L33;
            case 1: goto L32;
            case 2: goto L31;
            case 3: goto L30;
            case 4: goto L31;
            default: goto L29;
        };
    L29:
        return 0;
    L30:
        return 2;
    L31:
        return 1;
    L32:
        return 4;
    L33:
        return 3;
    L9:
        if (r72.equals("missing_to") == false) goto L28;
        r5 = 4;
        goto L28
    L13:
        if (r72.equals("messagetoobig") == false) goto L28;
        r5 = 3;
        goto L28
    L17:
        if (r72.equals("invalid_parameters") == false) goto L28;
        r5 = 2;
        goto L28
    L21:
        if (r72.equals("toomanymessages") == false) goto L28;
        r5 = 1;
        goto L28
    L25:
        if (r72.equals("service_not_available") == false) goto L28;
        r5 = 0;
        goto L28
    }
}
