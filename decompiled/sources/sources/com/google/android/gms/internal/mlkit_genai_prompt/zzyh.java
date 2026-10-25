package com.google.android.gms.internal.mlkit_genai_prompt;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* loaded from: classes4.dex */
public final class zzyh {
    public static final zzyh zza = null;

    static {
        zza = new zzyh();
    }

    private zzyh() {
    }

    public final zzhz zza(KClass r3) {
        if (Intrinsics.areEqual(r3, Reflection.getOrCreateKotlinClass(String.class)) == false) goto L6;
        zzhz r2 = zzib.zzu();
        r2.zzm(2);
    L21:
        if (r2 == null) goto L23;
        return r2;
    L23:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(zzyg.zzb().get(r3));
        f$$ExternalSyntheticBUOutline1.m(String.valueOf(r3.getSimpleName()).concat(" is neither a registered @Generable type nor a directly supported basic type (String, Int, Boolean, etc.)."));
        return null;
    L6:
        if (Intrinsics.areEqual(r3, Reflection.getOrCreateKotlinClass(Integer.TYPE)) == false) goto L9;
        r2 = zzib.zzu();
        r2.zzm(4);
        r2.zza("int32");
        goto L21
    L9:
        if (Intrinsics.areEqual(r3, Reflection.getOrCreateKotlinClass(Long.TYPE)) == false) goto L12;
        r2 = zzib.zzu();
        r2.zzm(4);
        r2.zza("int64");
        goto L21
    L12:
        if (Intrinsics.areEqual(r3, Reflection.getOrCreateKotlinClass(Boolean.TYPE)) == false) goto L15;
        r2 = zzib.zzu();
        r2.zzm(5);
        goto L21
    L15:
        if (Intrinsics.areEqual(r3, Reflection.getOrCreateKotlinClass(Float.TYPE)) == false) goto L18;
        r2 = zzib.zzu();
        r2.zzm(3);
        r2.zza("float");
        goto L21
    L18:
        if (Intrinsics.areEqual(r3, Reflection.getOrCreateKotlinClass(Double.TYPE)) == false) goto L20;
        r2 = zzib.zzu();
        r2.zzm(3);
        r2.zza("double");
        goto L21
    L20:
        r2 = null;
        goto L21
    }
}
