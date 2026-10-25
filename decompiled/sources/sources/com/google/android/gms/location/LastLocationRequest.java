package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.location.zzd;
import com.google.android.gms.internal.location.zzen;
import kotlin.jvm.internal.LongCompanionObject;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes5.dex */
public final class LastLocationRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LastLocationRequest> CREATOR = null;
    private final long zza;
    private final int zzb;
    private final boolean zzc;
    private final zzd zzd;

    public static final class Builder {
        private long zza;
        private int zzb;
        private final boolean zzc;
        private final zzd zzd;

        public Builder() {
            this.zza = LongCompanionObject.MAX_VALUE;
            this.zzb = 0;
            this.zzc = false;
            this.zzd = null;
        }

        public LastLocationRequest build() {
            return new LastLocationRequest(this.zza, this.zzb, this.zzc, this.zzd);
        }
    }

    static {
        CREATOR = new zzz();
    }

    public LastLocationRequest(long r1, int r3, boolean r4, zzd r5) {
        this.zza = r1;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
    }

    public boolean equals(Object r7) {
        if ((r7 instanceof LastLocationRequest) == true) goto L5;
        return false;
    L5:
        LastLocationRequest r72 = (LastLocationRequest) r7;
        if (this.zza == r72.zza) goto L8;
    L15:
        return false;
    L8:
        if (this.zzb != r72.zzb) goto L15;
        if (this.zzc != r72.zzc) goto L15;
        if (Objects.equal(this.zzd, r72.zzd) == false) goto L15;
        return true;
    }

    @Pure
    public int getGranularity() {
        return this.zzb;
    }

    @Pure
    public long getMaxUpdateAgeMillis() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Long.valueOf(this.zza), Integer.valueOf(this.zzb), Boolean.valueOf(this.zzc)});
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append("LastLocationRequest[");
        long r1 = this.zza;
        if (r1 == LongCompanionObject.MAX_VALUE) goto L5;
        r0.append("maxAge=");
        zzen.zzc(r1, r0);
    L5:
        int r12 = this.zzb;
        if (r12 == 0) goto L9;
        r0.append(", ");
        r0.append(zzp.zzb(r12));
    L9:
        if (this.zzc == false) goto L11;
        r0.append(", bypass");
    L11:
        zzd r5 = this.zzd;
        if (r5 == null) goto L14;
        r0.append(", impersonation=");
        r0.append(r5);
    L14:
        r0.append(']');
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeLong(r5, 1, getMaxUpdateAgeMillis());
        SafeParcelWriter.writeInt(r5, 2, getGranularity());
        SafeParcelWriter.writeBoolean(r5, 3, this.zzc);
        SafeParcelWriter.writeParcelable(r5, 5, this.zzd, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
