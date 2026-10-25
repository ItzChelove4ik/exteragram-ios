package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public enum AttestationConveyancePreference extends Enum<AttestationConveyancePreference> implements Parcelable {
    public static final Parcelable.Creator<AttestationConveyancePreference> CREATOR = null;
    public static final AttestationConveyancePreference DIRECT = null;
    public static final AttestationConveyancePreference INDIRECT = null;
    public static final AttestationConveyancePreference NONE = null;
    private static final /* synthetic */ AttestationConveyancePreference[] zza = null;
    private final String zzb;

    public static class UnsupportedAttestationConveyancePreferenceException extends Exception {
        public UnsupportedAttestationConveyancePreferenceException(String r2) {
            super(String.format("Attestation conveyance preference %s not supported", new Object[]{r2}));
        }
    }

    static {
        AttestationConveyancePreference r0 = new AttestationConveyancePreference("NONE", 0, "none");
        NONE = r0;
        AttestationConveyancePreference r1 = new AttestationConveyancePreference("INDIRECT", 1, "indirect");
        INDIRECT = r1;
        AttestationConveyancePreference r2 = new AttestationConveyancePreference("DIRECT", 2, "direct");
        DIRECT = r2;
        zza = new AttestationConveyancePreference[]{r0, r1, r2};
        CREATOR = new zzb();
    }

    AttestationConveyancePreference(String r1, int r2, String r3) {
        this.zzb = r3;
    }

    public static AttestationConveyancePreference fromString(String r5) {
        AttestationConveyancePreference[] r0 = values();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        AttestationConveyancePreference r3 = r0[r2];
        if (r5.equals(r3.zzb) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r3;
    L9:
        throw new UnsupportedAttestationConveyancePreferenceException(r5);
    }

    public static AttestationConveyancePreference valueOf(String r1) {
        return (AttestationConveyancePreference) Enum.valueOf(AttestationConveyancePreference.class, r1);
    }

    public static AttestationConveyancePreference[] values() {
        return (AttestationConveyancePreference[]) zza.clone();
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
