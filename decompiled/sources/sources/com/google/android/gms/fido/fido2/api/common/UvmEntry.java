package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class UvmEntry extends AbstractSafeParcelable {
    public static final Parcelable.Creator<UvmEntry> CREATOR = null;
    private final int zza;
    private final short zzb;
    private final short zzc;

    static {
        CREATOR = new zzbe();
    }

    public UvmEntry(int r1, short r2, short r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof UvmEntry) == true) goto L5;
        return false;
    L5:
        UvmEntry r42 = (UvmEntry) r4;
        if (this.zza == r42.zza) goto L8;
    L13:
        return false;
    L8:
        if (this.zzb != r42.zzb) goto L13;
        if (this.zzc != r42.zzc) goto L13;
        return true;
    }

    public short getKeyProtectionType() {
        return this.zzb;
    }

    public short getMatcherProtectionType() {
        return this.zzc;
    }

    public int getUserVerificationMethod() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zza), Short.valueOf(this.zzb), Short.valueOf(this.zzc)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, getUserVerificationMethod());
        SafeParcelWriter.writeShort(r3, 2, getKeyProtectionType());
        SafeParcelWriter.writeShort(r3, 3, getMatcherProtectionType());
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
