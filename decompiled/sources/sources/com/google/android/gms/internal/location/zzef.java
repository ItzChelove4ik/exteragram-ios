package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.location.LocationRequest;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

@Deprecated
/* loaded from: classes4.dex */
public final class zzef extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzef> CREATOR = null;
    LocationRequest zza;

    static {
        CREATOR = new zzeg();
    }

    public zzef(LocationRequest r3, List r4, boolean r5, boolean r6, boolean r7, boolean r8, String r9, long r10) {
        LocationRequest.Builder r92 = new LocationRequest.Builder(r3);
        if (r4 == null) goto L13;
        if (r4.isEmpty() == false) goto L7;
        WorkSource r32 = null;
    L11:
        r92.zzc(r32);
        goto L13
    L7:
        r32 = new WorkSource();
        Iterator r42 = r4.iterator();
    L9:
        if (r42.hasNext() == false) goto L11;
        ClientIdentity r0 = (ClientIdentity) r42.next();
        WorkSourceUtil.add(r32, r0.uid, r0.packageName);
    L13:
        if (r5 == false) goto L15;
        r92.setGranularity(1);
    L15:
        if (r6 == false) goto L17;
        r92.zza(2);
    L17:
        if (r7 == false) goto L19;
        r92.zzb(true);
    L19:
        if (r8 == false) goto L22;
        r92.setWaitForAccurateLocation(true);
    L22:
        if (r10 == LongCompanionObject.MAX_VALUE) goto L24;
        r92.setMaxUpdateAgeMillis(r10);
    L24:
        this.zza = r92.build();
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof zzef) == true) goto L5;
        return false;
    L5:
        return Objects.equal(this.zza, ((zzef) r2).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return this.zza.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeParcelable(r4, 1, this.zza, r5, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
