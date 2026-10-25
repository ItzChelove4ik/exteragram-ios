package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<Scope> CREATOR = null;
    final int zza;
    private final String zzb;

    static {
        CREATOR = new zzd();
    }

    public Scope(int r2, String r3) {
        Preconditions.checkNotEmpty(r3, "scopeUri must not be null or empty");
        this.zza = r2;
        this.zzb = r3;
    }

    public Scope(String r2) {
        this(1, r2);
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof Scope) == true) goto L10;
        return false;
    L10:
        return this.zzb.equals(((Scope) r2).zzb);
    }

    public String getScopeUri() {
        return this.zzb;
    }

    public int hashCode() {
        return this.zzb.hashCode();
    }

    public String toString() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        int r42 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, r42);
        SafeParcelWriter.writeString(r3, 2, getScopeUri(), false);
        SafeParcelWriter.finishObjectHeader(r3, r0);
    }
}
