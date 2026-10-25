package com.google.android.gms.location;

import android.location.Location;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public final class LocationResult extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LocationResult> CREATOR = null;
    static final List zza = null;
    private final List zzb;

    static {
        zza = Collections.EMPTY_LIST;
        CREATOR = new zzaf();
    }

    public LocationResult(List r1) {
        this.zzb = r1;
    }

    public boolean equals(Object r8) {
        if ((r8 instanceof LocationResult) == false) goto L31;
        LocationResult r82 = (LocationResult) r8;
        int r0 = Build.VERSION.SDK_INT;
        List r7 = this.zzb;
        if (r0 >= 31) goto L7;
        int r02 = r7.size();
        List r83 = r82.zzb;
        if (r02 == r83.size()) goto L11;
        return false;
    L11:
        Iterator r72 = r7.iterator();
        Iterator r84 = r83.iterator();
    L13:
        if (r72.hasNext() == false) goto L29;
        Location r03 = (Location) r72.next();
        Location r2 = (Location) r84.next();
        if (Double.compare(r03.getLatitude(), r2.getLatitude()) != 0) goto L16;
        if (Double.compare(r03.getLongitude(), r2.getLongitude()) != 0) goto L19;
        if (r03.getTime() != r2.getTime()) goto L22;
        if (r03.getElapsedRealtimeNanos() != r2.getElapsedRealtimeNanos()) goto L25;
        if (Objects.equal(r03.getProvider(), r2.getProvider()) == true) goto L13;
        return false;
    L25:
        return false;
    L22:
        return false;
    L19:
        return false;
    L16:
        return false;
    L29:
        return true;
    L7:
        return r7.equals(r82.zzb);
    L31:
        return false;
    }

    public Location getLastLocation() {
        List r1 = this.zzb;
        int r0 = r1.size();
        if (r0 != 0) goto L7;
        return null;
    L7:
        return (Location) r1.get(r0 - 1);
    }

    public List<Location> getLocations() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zzb});
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("LocationResult");
        int r1 = zzaj.$r8$clinit;
        List r3 = this.zzb;
        r0.ensureCapacity(r3.size() * 100);
        r0.append("[");
        Iterator r32 = r3.iterator();
        boolean r12 = false;
    L4:
        if (r32.hasNext() == false) goto L6;
        zzaj.zza((Location) r32.next(), r0);
        r0.append(", ");
        r12 = true;
        goto L4
    L6:
        if (r12 == false) goto L8;
        r0.setLength(r0.length() - 2);
    L8:
        r0.append("]");
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeTypedList(r3, 1, getLocations(), false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
