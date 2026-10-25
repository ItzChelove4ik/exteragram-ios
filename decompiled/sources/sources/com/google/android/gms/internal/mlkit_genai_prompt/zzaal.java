package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.ParcelFileDescriptor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes4.dex */
public final class zzaal {
    private final String zza;
    private final ParcelFileDescriptor zzb;
    private final ParcelFileDescriptor zzc;

    public /* synthetic */ zzaal(String r1, ParcelFileDescriptor r2, ParcelFileDescriptor r3, int r4, DefaultConstructorMarker r5) {
        this.zza = r1;
        if ((r4 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this.zzb = r2;
        if ((r4 & 4) == 0) goto L8;
        r3 = null;
    L8:
        this.zzc = r3;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzaal) == true) goto L8;
        return false;
    L8:
        zzaal r52 = (zzaal) r5;
        if (Intrinsics.areEqual(this.zza, r52.zza) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.zzb, r52.zzb) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.zzc, r52.zzc) == true) goto L17;
        return false;
    L17:
        return true;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() * 31;
        ParcelFileDescriptor r1 = this.zzb;
        int r2 = 0;
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        int r02 = (r0 + r12) * 31;
        ParcelFileDescriptor r3 = this.zzc;
        if (r3 == null) goto L11;
        r2 = r3.hashCode();
    L11:
        return r02 + r2;
    L5:
        r12 = r1.hashCode();
        goto L6
    }

    public final String toString() {
        String r0 = this.zza;
        int r1 = String.valueOf(r0).length();
        ParcelFileDescriptor r2 = this.zzb;
        int r3 = String.valueOf(r2).length();
        ParcelFileDescriptor r5 = this.zzc;
        int r12 = ((r1 + 54) + r3) + 23;
        StringBuilder r32 = new StringBuilder((r12 + String.valueOf(r5).length()) + 1);
        r32.append("PrefixParameters(promptPrefix=");
        r32.append(r0);
        r32.append(", fileDescriptorToWrite=");
        r32.append(r2);
        r32.append(", fileDescriptorToRead=");
        r32.append(r5);
        r32.append(")");
        return r32.toString();
    }

    public final String zza() {
        return this.zza;
    }

    public final ParcelFileDescriptor zzb() {
        return this.zzb;
    }

    public final ParcelFileDescriptor zzc() {
        return this.zzc;
    }
}
