package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.prompt.GenerateContentRequest;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* loaded from: classes4.dex */
public final class zzaan {
    private final GenerateContentRequest zza;
    private final KClass zzb;
    private final boolean zzc;

    public zzaan(GenerateContentRequest r1, KClass r2, boolean r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzaan) == true) goto L8;
        return false;
    L8:
        zzaan r52 = (zzaan) r5;
        if (Intrinsics.areEqual(this.zza, r52.zza) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.zzb, r52.zzb) == true) goto L15;
        return false;
    L15:
        if (this.zzc == r52.zzc) goto L17;
        return false;
    L17:
        return true;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() * 31;
        KClass r1 = this.zzb;
        if (r1 != null) goto L5;
        int r12 = 0;
    L7:
        return ((r0 + r12) * 31) + Boolean.hashCode(this.zzc);
    L5:
        r12 = r1.hashCode();
        goto L7
    }

    public final String toString() {
        GenerateContentRequest r0 = this.zza;
        int r1 = String.valueOf(r0).length();
        KClass r2 = this.zzb;
        int r3 = String.valueOf(r2).length();
        boolean r5 = this.zzc;
        int r12 = ((r1 + 52) + r3) + 24;
        StringBuilder r32 = new StringBuilder((r12 + String.valueOf(r5).length()) + 1);
        r32.append("RequestWrapper(generateContentRequest=");
        r32.append(r0);
        r32.append(", outputClass=");
        r32.append(r2);
        r32.append(", includeSchemaInPrompt=");
        r32.append(r5);
        r32.append(")");
        return r32.toString();
    }

    public final GenerateContentRequest zza() {
        return this.zza;
    }

    public final KClass zzb() {
        return this.zzb;
    }

    public final boolean zzc() {
        return this.zzc;
    }
}
