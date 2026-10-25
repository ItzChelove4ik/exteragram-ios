package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class Feature extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Feature> CREATOR = null;
    private final String zza;

    @Deprecated
    private final int zzb;
    private final long zzc;
    private final boolean zzd;

    static {
        CREATOR = new zzb();
    }

    public Feature(String r1, int r2, long r3, boolean r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r5;
    }

    public Feature(String r7, long r8) {
        this(r7, -1, r8, false);
    }

    public Feature(String r7, long r8, boolean r10) {
        this(r7, -1, r8, r10);
    }

    public final boolean equals(Object r7) {
        if ((r7 instanceof Feature) == false) goto L12;
        Feature r72 = (Feature) r7;
        if (Objects.equal(getName(), r72.getName()) == false) goto L12;
        if (getVersion() != r72.getVersion()) goto L12;
        if (getIsFullyRolledOut() != r72.getIsFullyRolledOut()) goto L12;
        return true;
    L12:
        return false;
    }

    public boolean getIsFullyRolledOut() {
        return this.zzd;
    }

    public String getName() {
        return this.zza;
    }

    public long getVersion() {
        long r0 = this.zzc;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        return this.zzb;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{getName(), Long.valueOf(getVersion()), Boolean.valueOf(getIsFullyRolledOut())});
    }

    public final String toString() {
        Objects.ToStringHelper r0 = Objects.toStringHelper(this);
        r0.add("name", getName());
        r0.add("version", Long.valueOf(getVersion()));
        r0.add("is_fully_rolled_out", Boolean.valueOf(getIsFullyRolledOut()));
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, getName(), false);
        SafeParcelWriter.writeInt(r4, 2, this.zzb);
        SafeParcelWriter.writeLong(r4, 3, getVersion());
        SafeParcelWriter.writeBoolean(r4, 4, getIsFullyRolledOut());
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
