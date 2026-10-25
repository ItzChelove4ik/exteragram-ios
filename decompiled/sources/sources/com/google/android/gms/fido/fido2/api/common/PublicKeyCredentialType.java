package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public enum PublicKeyCredentialType extends Enum<PublicKeyCredentialType> implements Parcelable {
    public static final Parcelable.Creator<PublicKeyCredentialType> CREATOR = null;
    public static final PublicKeyCredentialType PUBLIC_KEY = null;
    private static final /* synthetic */ PublicKeyCredentialType[] zza = null;
    private final String zzb;

    public static class UnsupportedPublicKeyCredTypeException extends Exception {
        public UnsupportedPublicKeyCredTypeException(String r1) {
            super(r1);
        }
    }

    static {
        PublicKeyCredentialType r0 = new PublicKeyCredentialType("PUBLIC_KEY", 0, "public-key");
        PUBLIC_KEY = r0;
        zza = new PublicKeyCredentialType[]{r0};
        CREATOR = new zzat();
    }

    PublicKeyCredentialType(String r1, int r2, String r3) {
        this.zzb = "public-key";
    }

    public static PublicKeyCredentialType fromString(String r5) {
        PublicKeyCredentialType[] r0 = values();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        PublicKeyCredentialType r3 = r0[r2];
        if (r5.equals(r3.zzb) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r3;
    L9:
        throw new UnsupportedPublicKeyCredTypeException(String.format("PublicKeyCredentialType %s not supported", new Object[]{r5}));
    }

    public static PublicKeyCredentialType valueOf(String r1) {
        return (PublicKeyCredentialType) Enum.valueOf(PublicKeyCredentialType.class, r1);
    }

    public static PublicKeyCredentialType[] values() {
        return (PublicKeyCredentialType[]) zza.clone();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeString(this.zzb);
    }
}
