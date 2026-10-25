package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzaag {
    public static final zzzz zza = null;
    private static volatile zzaag zzc;
    private final zzxg zzb;
    private final zzxp zzd;

    static {
        zza = new zzzz(null);
    }

    public /* synthetic */ zzaag(zzxg r1, int r2, zzxp r3, int r4, DefaultConstructorMarker r5) {
        zzxp r22 = zzxp.zza;
        this.zzb = r1;
        this.zzd = r22;
    }

    public static /* synthetic */ Object zza(zzaag r5, Context r6, String r7, String r8, Continuation r9) {
        if ((r9 instanceof zzaaf) == false) goto L7;
        zzaaf r0 = (zzaaf) r9;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        r8 = r0.zzd;
        ResultKt.throwOnFailure(r92);
    L20:
        zzaab r93 = (zzaab) r92;
        if (r93 == null) goto L23;
        String r52 = r93.zze();
    L25:
        if (Intrinsics.areEqual(r52, r8) == false) goto L27;
        return r93;
    L27:
        return null;
    L23:
        r52 = null;
        goto L25
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r92);
        if (r7.length() != 0) goto L17;
        return null;
    L17:
        String r72 = zzj(r7, r8);
        zzxg r53 = r5.zzb;
        r0.zzd = r8;
        r0.zzc = 1;
        r92 = zzxg.zza(r53, r6, r72, r0);
        if (r92 != r12) goto L20;
        return r12;
    L7:
        r0 = new zzaaf(r5, r9);
        goto L8
    }

    public static /* synthetic */ Object zzb(zzaag r17, Context r18, zzaab r19, Continuation r20) {
        zzaag r0 = r17;
        Context r1 = r18;
        if ((r20 instanceof zzaad) == false) goto L7;
        zzaad r3 = (zzaad) r20;
        int r4 = r3.zze;
        if ((r4 & Integer.MIN_VALUE) == 0) goto L7;
        r3.zze = r4 - Integer.MIN_VALUE;
    L8:
        Object r2 = r3.zzc;
        Object r42 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r5 = r3.zze;
        if (r5 == 0) goto L19;
        if (r5 == 1) goto L18;
        if (r5 == 2) goto L17;
        if (r5 != 3) goto L15;
        ParcelFileDescriptor r02 = (ParcelFileDescriptor) r3.zza;
        ResultKt.throwOnFailure(r2);
        return r02;
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L17:
        zzaab r03 = r3.zzg;
        String r12 = r3.zzf;
        Context r52 = (Context) r3.zzb;
        zzaag r7 = (zzaag) r3.zza;
        ResultKt.throwOnFailure(r2);
        String r11 = r12;
    L27:
        ParcelFileDescriptor r22 = (ParcelFileDescriptor) r2;
        if (r22 == null) goto L31;
        zzxp r13 = r7.zzd;
        long r132 = System.currentTimeMillis();
        zzaab r10 = new zzaab(r11, r03.zzb(), r132, r03.zze());
        zzxg r04 = r7.zzb;
        r3.zza = r22;
        r3.zzb = null;
        r3.zzf = null;
        r3.zzg = null;
        r3.zze = 3;
        if (zzxg.zzc(r04, r52, r10, r3) != r42) goto L31;
    L32:
        return r42;
    L31:
        return r22;
    L18:
        String r05 = r3.zzf;
        r1 = (Context) r3.zzb;
        zzaag r53 = (zzaag) r3.zza;
        ResultKt.throwOnFailure(r2);
        String r23 = r05;
        r0 = r53;
        Object r54 = r2;
    L21:
        zzaab r55 = (zzaab) r54;
        if (r55 != null) goto L24;
        return null;
    L24:
        zzxg r8 = r0.zzb;
        r3.zza = r0;
        r3.zzb = r1;
        r3.zzf = r23;
        r3.zzg = r55;
        r3.zze = 2;
        Object r72 = zzxg.zzd(r8, r1, r55, r3);
        if (r72 == r42) goto L32;
        r11 = r23;
        r2 = r72;
        r7 = r0;
        r03 = r55;
        r52 = r1;
        goto L27
    L19:
        ResultKt.throwOnFailure(r2);
        r23 = r19.zza();
        zzxg r56 = r0.zzb;
        r3.zza = r0;
        r3.zzb = r1;
        r3.zzf = r23;
        r3.zze = 1;
        r54 = zzxg.zza(r56, r1, r23, r3);
        if (r54 == r42) goto L32;
    L7:
        r3 = new zzaad(r0, r20);
        goto L8
    }

    public static /* synthetic */ Object zzc(zzaag r6, Context r7, String r8, String r9, Continuation r10) {
        if (r8.length() != 0) goto L6;
        return null;
    L6:
        zzaab r0 = new zzaab(zzj(r8, r9), zzxh.zza(), System.currentTimeMillis(), r9);
        return zzxg.zze(r6.zzb, r7, r0, new zzaaa(r6), r10);
    }

    public static /* synthetic */ Object zzd(zzaag r0, Context r1, zzaab r2, Continuation r3) {
        return zzxg.zzf(r0.zzb, r1, r2, r3);
    }

    public static final /* synthetic */ zzaag zzf() {
        return zzc;
    }

    public static final /* synthetic */ void zzg(zzaag r0) {
        zzc = r0;
    }

    public static /* synthetic */ Collection zzh(zzaag r0, Map r1) {
        return zzi(r0, r1);
    }

    private static final Collection zzi(zzaag r3, Map r4) {
        if (r4.size() <= 10) goto L11;
        List r32 = CollectionsKt.take(CollectionsKt.sortedWith(r4.values(), new zzaae()), r4.size() - 10);
        Iterator r42 = r32.iterator();
    L6:
        if (r42.hasNext() == false) goto L9;
        Log.i("ImplicitCacheManager", "Cache limit reached. Remove least recent used cache with name ".concat(String.valueOf(((zzaab) r42.next()).zza())));
        goto L6
    L9:
        return r32;
    L11:
        return CollectionsKt.emptyList();
    }

    private static final String zzj(String r3, String r4) {
        int r0 = String.valueOf(r3).length();
        int r02 = r0 + 1;
        StringBuilder r2 = new StringBuilder(r02 + String.valueOf(r4).length());
        r2.append(r3);
        r2.append("_");
        r2.append(r4);
        return r2.toString();
    }
}
