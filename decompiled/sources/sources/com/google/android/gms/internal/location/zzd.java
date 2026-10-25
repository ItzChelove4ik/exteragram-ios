package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;
import java.util.Objects;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes4.dex */
public final class zzd extends AbstractSafeParcelable {

    @JvmField
    public static final Parcelable.Creator<zzd> CREATOR = null;
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final List zze;
    private final zzd zzf;

    static {
        CREATOR = new zze();
        Process.myUid();
        Process.myPid();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "only for Parcelable.Creator")
    public zzd(int r3, String r4, String r5, String r6, List r7, zzd r8) {
        if (r8 != null) goto L5;
    L9:
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r5;
        if (r6 != null) goto L14;
        if (r8 == null) goto L13;
        r6 = r8.zzd;
        goto L14
    L13:
        r6 = null;
    L14:
        this.zzd = r6;
        if (r7 != null) goto L21;
        if (r8 == null) goto L18;
        r7 = r8.zze;
    L19:
        if (r7 != null) goto L21;
        r7 = zzeu.zzi();
        goto L21
    L18:
        r7 = null;
    L21:
        this.zze = zzeu.zzj(r7);
        this.zzf = r8;
        return;
    L5:
        if (r8.zza() == false) goto L9;
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        throw null;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzd) == false) goto L18;
        zzd r42 = (zzd) r4;
        if (this.zza != r42.zza) goto L18;
        if (Intrinsics.areEqual(this.zzb, r42.zzb) == false) goto L18;
        if (Intrinsics.areEqual(this.zzc, r42.zzc) == false) goto L18;
        if (Intrinsics.areEqual(this.zzd, r42.zzd) == false) goto L18;
        if (Intrinsics.areEqual(this.zzf, r42.zzf) == false) goto L18;
        if (Intrinsics.areEqual(this.zze, r42.zze) == false) goto L18;
        return true;
    L18:
        return false;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.zza), this.zzb, this.zzc, this.zzd, this.zzf});
    }

    public final String toString() {
        String r0 = this.zzb;
        int r1 = r0.length() + 18;
        String r2 = this.zzc;
        if (r2 == null) goto L5;
        int r4 = r2.length();
    L6:
        StringBuilder r42 = new StringBuilder(r1 + r4);
        r42.append(this.zza);
        r42.append("/");
        r42.append(r0);
        if (r2 == null) goto L13;
        r42.append("[");
        if (StringsKt.startsWith$default(r2, r0, false, 2, null) == false) goto L11;
        r42.append(r2, r0.length(), r2.length());
    L12:
        r42.append("]");
        goto L13
    L11:
        r42.append(r2);
    L13:
        String r7 = this.zzd;
        if (r7 == null) goto L17;
        r42.append("/");
        r42.append(Integer.toHexString(r7.hashCode()));
    L17:
        return r42.toString();
    L5:
        r4 = 0;
        goto L6
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = this.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, r0);
        SafeParcelWriter.writeString(r5, 3, this.zzb, false);
        SafeParcelWriter.writeString(r5, 4, this.zzc, false);
        SafeParcelWriter.writeString(r5, 6, this.zzd, false);
        SafeParcelWriter.writeParcelable(r5, 7, this.zzf, r6, false);
        SafeParcelWriter.writeTypedList(r5, 8, this.zze, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }

    @Pure
    public final boolean zza() {
        if (this.zzf == null) goto L6;
        return true;
    L6:
        return false;
    }
}
