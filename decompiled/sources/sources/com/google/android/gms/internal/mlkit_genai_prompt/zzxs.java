package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.util.Log;
import com.google.mlkit.genai.common.GenAiException;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzxs {
    public static final zzxq zza = null;
    private static volatile zzxs zzc;
    private final zzxg zzb;

    static {
        zza = new zzxq(null);
    }

    public /* synthetic */ zzxs(zzxg r1, DefaultConstructorMarker r2) {
        this.zzb = r1;
    }

    public static /* synthetic */ Object zza(zzxs r5, Context r6, String r7, String r8, Continuation r9) {
        if ((r9 instanceof zzxr) == false) goto L7;
        zzxr r0 = (zzxr) r9;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        r8 = r0.zze;
        r7 = r0.zzd;
        ResultKt.throwOnFailure(r92);
    L16:
        zzxt r93 = (zzxt) r92;
        if (r93 != null) goto L19;
        return null;
    L19:
        if (r8 != null) goto L21;
    L25:
        return r93;
    L21:
        if (Intrinsics.areEqual(r93.zze(), r8) == true) goto L25;
        String r62 = r93.zze();
        int r94 = String.valueOf(r7).length();
        StringBuilder r02 = new StringBuilder(((((r94 + 42) + String.valueOf(r62).length()) + 33) + r8.length()) + 60);
        r02.append("Cached context '");
        r02.append(r7);
        r02.append("' was created with model '");
        r02.append(r62);
        r02.append("', but is being used with model '");
        r02.append(r8);
        r02.append("'. Please either update the cache or use the matching model.");
        throw new GenAiException(r02.toString(), null, -103);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r92);
        zzxg r52 = r5.zzb;
        r0.zzd = r7;
        r0.zze = r8;
        r0.zzc = 1;
        r92 = zzxg.zza(r52, r6, r7, r0);
        if (r92 != r12) goto L16;
        return r12;
    L7:
        r0 = new zzxr(r5, r9);
        goto L8
    }

    public static /* synthetic */ Object zzb(zzxs r0, Context r1, Continuation r2) {
        return zzxg.zzb(r0.zzb, r1, r2);
    }

    public static /* synthetic */ Object zzc(zzxs r0, Context r1, zzxt r2, Continuation r3) {
        return zzxg.zzd(r0.zzb, r1, r2, r3);
    }

    public static /* synthetic */ Object zzd(zzxs r3, Context r4, String r5, String r6, String r7, Continuation r8) {
        if (StringsKt.isBlank(r5) == false) goto L7;
        Log.w("ExplicitCacheManager", "Cache name is blank. Will not open cache for writing.");
        return null;
    L7:
        if (StringsKt.isBlank(r6) == false) goto L10;
        StringBuilder r42 = new StringBuilder(String.valueOf(r5).length() + 73);
        r42.append("Prompt prefix is blank for cache name: ");
        r42.append(r5);
        r42.append(". Will not open cache for writing.");
        Log.w("ExplicitCacheManager", r42.toString());
        return null;
    L10:
        zzxt r1 = new zzxt(r5, zzxh.zza(), r6, r7);
        return zzxg.zze(r3.zzb, r4, r1, null, r8);
    }

    public static /* synthetic */ Object zze(zzxs r0, Context r1, zzxt r2, Continuation r3) {
        return zzxg.zzf(r0.zzb, r1, r2, r3);
    }

    public static final /* synthetic */ zzxs zzf() {
        return zzc;
    }

    public static final /* synthetic */ void zzg(zzxs r0) {
        zzc = r0;
    }
}
