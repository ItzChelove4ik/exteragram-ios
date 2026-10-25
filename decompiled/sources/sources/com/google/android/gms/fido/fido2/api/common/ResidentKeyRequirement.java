package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public enum ResidentKeyRequirement extends Enum<ResidentKeyRequirement> implements Parcelable {
    public static final Parcelable.Creator<ResidentKeyRequirement> CREATOR = null;
    public static final ResidentKeyRequirement RESIDENT_KEY_DISCOURAGED = null;
    public static final ResidentKeyRequirement RESIDENT_KEY_PREFERRED = null;
    public static final ResidentKeyRequirement RESIDENT_KEY_REQUIRED = null;
    private static final /* synthetic */ ResidentKeyRequirement[] zza = null;
    private final String zzb;

    public static class UnsupportedResidentKeyRequirementException extends Exception {
        public UnsupportedResidentKeyRequirementException(String r2) {
            super(String.format("Resident key requirement %s not supported", new Object[]{r2}));
        }
    }

    static {
        ResidentKeyRequirement r0 = new ResidentKeyRequirement("RESIDENT_KEY_DISCOURAGED", 0, "discouraged");
        RESIDENT_KEY_DISCOURAGED = r0;
        ResidentKeyRequirement r1 = new ResidentKeyRequirement("RESIDENT_KEY_PREFERRED", 1, "preferred");
        RESIDENT_KEY_PREFERRED = r1;
        ResidentKeyRequirement r2 = new ResidentKeyRequirement("RESIDENT_KEY_REQUIRED", 2, "required");
        RESIDENT_KEY_REQUIRED = r2;
        zza = new ResidentKeyRequirement[]{r0, r1, r2};
        CREATOR = new zzav();
    }

    ResidentKeyRequirement(String r1, int r2, String r3) {
        this.zzb = r3;
    }

    public static ResidentKeyRequirement fromString(String r5) {
        ResidentKeyRequirement[] r0 = values();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        ResidentKeyRequirement r3 = r0[r2];
        if (r5.equals(r3.zzb) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r3;
    L9:
        throw new UnsupportedResidentKeyRequirementException(r5);
    }

    public static ResidentKeyRequirement valueOf(String r1) {
        return (ResidentKeyRequirement) Enum.valueOf(ResidentKeyRequirement.class, r1);
    }

    public static ResidentKeyRequirement[] values() {
        return (ResidentKeyRequirement[]) zza.clone();
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
