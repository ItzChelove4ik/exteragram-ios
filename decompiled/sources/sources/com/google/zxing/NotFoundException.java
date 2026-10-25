package com.google.zxing;

/* loaded from: classes5.dex */
public final class NotFoundException extends ReaderException {
    private static final NotFoundException INSTANCE = null;

    static {
        NotFoundException r0 = new NotFoundException();
        INSTANCE = r0;
        r0.setStackTrace(ReaderException.NO_TRACE);
    }

    private NotFoundException() {
    }

    public static NotFoundException getNotFoundInstance() {
        if (ReaderException.isStackTrace == false) goto L7;
        return new NotFoundException();
    L7:
        return INSTANCE;
    }
}
