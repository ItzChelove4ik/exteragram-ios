package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public class PatternItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PatternItem> CREATOR = null;
    private final int zzb;
    private final Float zzc;

    static {
        CREATOR = new zzq();
    }

    public PatternItem(int r5, Float r6) {
        boolean r0 = true;
        if (r5 != 1) goto L5;
    L10:
        Preconditions.checkArgument(r0, "Invalid PatternItem: type=" + r5 + " length=" + r6);
        this.zzb = r5;
        this.zzc = r6;
        return;
    L5:
        if (r6 != null) goto L7;
    L9:
        r0 = false;
        goto L10
    L7:
        if (r6.floatValue() < 0.0f) goto L9;
        goto L9
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof PatternItem) == true) goto L8;
        return false;
    L8:
        PatternItem r52 = (PatternItem) r5;
        if (this.zzb == r52.zzb) goto L11;
    L13:
        return false;
    L11:
        if (Objects.equal(this.zzc, r52.zzc) == false) goto L13;
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzb), this.zzc});
    }

    public String toString() {
        return "[PatternItem: type=" + this.zzb + " length=" + this.zzc + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        int r42 = this.zzb;
        int r0 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 2, r42);
        SafeParcelWriter.writeFloatObject(r3, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(r3, r0);
    }
}
