package com.google.zxing;

/* loaded from: classes5.dex */
public final class FormatException extends ReaderException {
    private static final FormatException INSTANCE = null;

    static {
        FormatException r0 = new FormatException();
        INSTANCE = r0;
        r0.setStackTrace(ReaderException.NO_TRACE);
    }

    private FormatException() {
    }

    public static FormatException getFormatInstance() {
        if (ReaderException.isStackTrace == false) goto L7;
        return new FormatException();
    L7:
        return INSTANCE;
    }
}
