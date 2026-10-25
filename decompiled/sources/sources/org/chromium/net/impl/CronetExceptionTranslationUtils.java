package org.chromium.net.impl;

import org.chromium.net.CronetException;
import org.chromium.net.InlineExecutionProhibitedException;

/* loaded from: classes5.dex */
abstract class CronetExceptionTranslationUtils {

    public interface CronetWork<T, E extends Exception> {
        T run();
    }

    public static <T, E extends Exception> T executeTranslatingCronetExceptions(CronetWork<T, E> r1, Class<E> r2) {
        return r1.run();
    L4:
        e = move-exception;
        if (isUncheckedAndroidCronetException(e) == true) goto L16;
        if (isCheckedAndroidCronetException(e) == true) goto L14;
        if (r2.isInstance(e) == false) goto L12;
        throw e;
    L12:
        throw e;
    L14:
        throw translateCheckedAndroidCronetException(e);
    L16:
        throw translateUncheckedAndroidCronetException(e);
    }

    public static boolean isCheckedAndroidCronetException(Exception r0) {
        return CronetExceptionTranslationUtils$$ExternalSyntheticApiModelOutline6.m(r0);
    }

    public static boolean isUncheckedAndroidCronetException(Exception r0) {
        return CronetExceptionTranslationUtils$$ExternalSyntheticApiModelOutline0.m(r0);
    }

    public static CronetException translateCheckedAndroidCronetException(Exception r2) {
        if (isCheckedAndroidCronetException(r2) == false) goto L23;
        if (AndroidNetworkExceptionWrapper$$ExternalSyntheticApiModelOutline0.m(r2) == false) goto L9;
        return new AndroidQuicExceptionWrapper(CronetExceptionTranslationUtils$$ExternalSyntheticApiModelOutline1.m(r2));
    L9:
        if (CronetExceptionTranslationUtils$$ExternalSyntheticApiModelOutline2.m(r2) == false) goto L13;
        return new AndroidNetworkExceptionWrapper(CronetExceptionTranslationUtils$$ExternalSyntheticApiModelOutline3.m(r2));
    L13:
        if (CronetExceptionTranslationUtils$$ExternalSyntheticApiModelOutline4.m(r2) == false) goto L17;
        return new AndroidCallbackExceptionWrapper(CronetExceptionTranslationUtils$$ExternalSyntheticApiModelOutline5.m(r2));
    L17:
        if (CronetExceptionTranslationUtils$$ExternalSyntheticApiModelOutline6.m(r2) == false) goto L21;
        return new AndroidHttpExceptionWrapper(CronetExceptionTranslationUtils$$ExternalSyntheticApiModelOutline7.m(r2));
    L21:
        throw new UnsupportedOperationException("Checked exception translation discrepancy", r2);
    L23:
        throw new IllegalArgumentException("Not an Android Cronet exception", r2);
    }

    public static RuntimeException translateUncheckedAndroidCronetException(Exception r2) {
        if (isUncheckedAndroidCronetException(r2) == false) goto L11;
        if (CronetExceptionTranslationUtils$$ExternalSyntheticApiModelOutline0.m(r2) == false) goto L9;
        InlineExecutionProhibitedException r0 = new InlineExecutionProhibitedException();
        r0.initCause(r2);
        return r0;
    L9:
        throw new UnsupportedOperationException("Unchecked exception translation discrepancy", r2);
    L11:
        throw new IllegalArgumentException("Not an Android Cronet exception", r2);
    }
}
