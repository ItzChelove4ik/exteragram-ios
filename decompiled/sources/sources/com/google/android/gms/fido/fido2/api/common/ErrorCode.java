package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

/* loaded from: classes4.dex */
public enum ErrorCode extends Enum<ErrorCode> implements Parcelable {
    public static final ErrorCode ABORT_ERR = null;
    public static final ErrorCode ATTESTATION_NOT_PRIVATE_ERR = null;
    public static final ErrorCode CONSTRAINT_ERR = null;
    public static final Parcelable.Creator<ErrorCode> CREATOR = null;
    public static final ErrorCode DATA_ERR = null;
    public static final ErrorCode ENCODING_ERR = null;
    public static final ErrorCode INVALID_STATE_ERR = null;
    public static final ErrorCode NETWORK_ERR = null;
    public static final ErrorCode NOT_ALLOWED_ERR = null;
    public static final ErrorCode NOT_SUPPORTED_ERR = null;
    public static final ErrorCode SECURITY_ERR = null;
    public static final ErrorCode TIMEOUT_ERR = null;
    public static final ErrorCode UNKNOWN_ERR = null;
    private static final /* synthetic */ ErrorCode[] zza = null;
    private final int zzb;

    public static class UnsupportedErrorCodeException extends Exception {
        public UnsupportedErrorCodeException(int r3) {
            super(String.format(Locale.US, "Error code %d is not supported", new Object[]{Integer.valueOf(r3)}));
        }
    }

    static {
        ErrorCode r0 = new ErrorCode("NOT_SUPPORTED_ERR", 0, 9);
        NOT_SUPPORTED_ERR = r0;
        ErrorCode r1 = new ErrorCode("INVALID_STATE_ERR", 1, 11);
        INVALID_STATE_ERR = r1;
        ErrorCode r2 = new ErrorCode("SECURITY_ERR", 2, 18);
        SECURITY_ERR = r2;
        ErrorCode r3 = new ErrorCode("NETWORK_ERR", 3, 19);
        NETWORK_ERR = r3;
        ErrorCode r4 = new ErrorCode("ABORT_ERR", 4, 20);
        ABORT_ERR = r4;
        ErrorCode r5 = new ErrorCode("TIMEOUT_ERR", 5, 23);
        TIMEOUT_ERR = r5;
        ErrorCode r6 = new ErrorCode("ENCODING_ERR", 6, 27);
        ENCODING_ERR = r6;
        ErrorCode r7 = new ErrorCode("UNKNOWN_ERR", 7, 28);
        UNKNOWN_ERR = r7;
        ErrorCode r8 = new ErrorCode("CONSTRAINT_ERR", 8, 29);
        CONSTRAINT_ERR = r8;
        ErrorCode r9 = new ErrorCode("DATA_ERR", 9, 30);
        DATA_ERR = r9;
        ErrorCode r10 = new ErrorCode("NOT_ALLOWED_ERR", 10, 35);
        NOT_ALLOWED_ERR = r10;
        ErrorCode r11 = new ErrorCode("ATTESTATION_NOT_PRIVATE_ERR", 11, 36);
        ATTESTATION_NOT_PRIVATE_ERR = r11;
        zza = new ErrorCode[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11};
        CREATOR = new zzw();
    }

    ErrorCode(String r1, int r2, int r3) {
        this.zzb = r3;
    }

    public static ErrorCode toErrorCode(int r5) {
        ErrorCode[] r0 = values();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        ErrorCode r3 = r0[r2];
        if (r5 == r3.zzb) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r3;
    L9:
        throw new UnsupportedErrorCodeException(r5);
    }

    public static ErrorCode valueOf(String r1) {
        return (ErrorCode) Enum.valueOf(ErrorCode.class, r1);
    }

    public static ErrorCode[] values() {
        return (ErrorCode[]) zza.clone();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCode() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeInt(this.zzb);
    }
}
