package androidx.biometric;

import java.util.Arrays;

/* loaded from: classes3.dex */
class BiometricErrorData {
    private final int mErrorCode;
    private final CharSequence mErrorMessage;

    public BiometricErrorData(int r1, CharSequence r2) {
        this.mErrorCode = r1;
        this.mErrorMessage = r2;
    }

    private static String convertToString(CharSequence r0) {
        if (r0 != null) goto L4;
        return null;
    L4:
        return r0.toString();
    }

    private boolean isErrorMessageEqualTo(CharSequence r1) {
        String r0 = convertToString(this.mErrorMessage);
        String r12 = convertToString(r1);
        if (r0 != null) goto L5;
        if (r12 != null) goto L5;
        return true;
    L5:
        if (r0 != null) goto L7;
        return false;
    L7:
        if (r0.equals(r12) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof BiometricErrorData) == false) goto L10;
        BiometricErrorData r42 = (BiometricErrorData) r4;
        if (this.mErrorCode != r42.mErrorCode) goto L10;
        if (isErrorMessageEqualTo(r42.mErrorMessage) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public CharSequence getErrorMessage() {
        return this.mErrorMessage;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mErrorCode), convertToString(this.mErrorMessage)});
    }
}
