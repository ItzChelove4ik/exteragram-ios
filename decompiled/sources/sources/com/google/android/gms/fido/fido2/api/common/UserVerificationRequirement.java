package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public enum UserVerificationRequirement extends Enum<UserVerificationRequirement> implements Parcelable {
    public static final Parcelable.Creator<UserVerificationRequirement> CREATOR = null;
    public static final UserVerificationRequirement zza = null;
    public static final UserVerificationRequirement zzb = null;
    public static final UserVerificationRequirement zzc = null;
    private static final /* synthetic */ UserVerificationRequirement[] zzd = null;
    private final String zze;

    static {
        UserVerificationRequirement r0 = new UserVerificationRequirement("USER_VERIFICATION_REQUIRED", 0, "required");
        zza = r0;
        UserVerificationRequirement r1 = new UserVerificationRequirement("USER_VERIFICATION_PREFERRED", 1, "preferred");
        zzb = r1;
        UserVerificationRequirement r2 = new UserVerificationRequirement("USER_VERIFICATION_DISCOURAGED", 2, "discouraged");
        zzc = r2;
        zzd = new UserVerificationRequirement[]{r0, r1, r2};
        CREATOR = new zzbb();
    }

    UserVerificationRequirement(String r1, int r2, String r3) {
        this.zze = r3;
    }

    public static UserVerificationRequirement fromString(String r5) {
        UserVerificationRequirement[] r0 = values();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        UserVerificationRequirement r3 = r0[r2];
        if (r5.equals(r3.zze) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r3;
    L9:
        throw new zzbc(r5);
    }

    public static UserVerificationRequirement[] values() {
        return (UserVerificationRequirement[]) zzd.clone();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.zze;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r1, int r2) {
        r1.writeString(this.zze);
    }
}
