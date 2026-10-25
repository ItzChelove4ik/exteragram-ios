package com.sun.jna;

/* loaded from: classes5.dex */
public class LastErrorException extends RuntimeException {
    private static final long serialVersionUID = 1;
    private int errorCode;

    public LastErrorException(int r2) {
        this(r2, formatMessage(r2));
    }

    public LastErrorException(int r1, String r2) {
        super(r2);
        this.errorCode = r1;
    }

    public LastErrorException(String r3) {
        super(parseMessage(r3.trim()));
    L8:
        this.errorCode = -1;
        return;
    L4:
        if (r3.startsWith("[") == false) goto L6;
        r3 = r3.substring(1, r3.indexOf("]"));     // Catch: NumberFormatException -> L8
    L6:
        this.errorCode = Integer.parseInt(r3);     // Catch: NumberFormatException -> L8
    }

    private static String formatMessage(int r2) {
        if (Platform.isWindows() == false) goto L7;
        return "GetLastError() returned " + r2;
    L7:
        return "errno was " + r2;
    }

    private static String parseMessage(String r1) {
        return formatMessage(Integer.parseInt(r1));
    L7:
        return r1;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
