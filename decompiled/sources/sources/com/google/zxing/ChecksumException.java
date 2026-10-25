package com.google.zxing;

/* loaded from: classes5.dex */
public final class ChecksumException extends ReaderException {
    private static final ChecksumException INSTANCE = null;

    static {
        ChecksumException r0 = new ChecksumException();
        INSTANCE = r0;
        r0.setStackTrace(ReaderException.NO_TRACE);
    }

    private ChecksumException() {
    }

    public static ChecksumException getChecksumInstance() {
        if (ReaderException.isStackTrace == false) goto L7;
        return new ChecksumException();
    L7:
        return INSTANCE;
    }
}
