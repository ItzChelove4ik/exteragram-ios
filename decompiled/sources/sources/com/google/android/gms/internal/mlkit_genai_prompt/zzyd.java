package com.google.android.gms.internal.mlkit_genai_prompt;

import android.util.Log;
import com.google.mlkit.genai.common.DownloadCallback;
import com.google.mlkit.genai.common.GenAiException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScopeKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzyd {
    private final zzaur zza;
    private final List zzb;
    private final Map zzc;

    public zzyd(zzaur r1, List r2) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = new LinkedHashMap();
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x009b -> B:32:0x00b7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00b0 -> B:31:0x00b1). Please report as a decompilation issue!!! */
    public static /* synthetic */ Object zza(zzyd r10, Continuation r11) {
        if ((r11 instanceof zzxv) == false) goto L7;
        zzxv r0 = (zzxv) r11;
        int r1 = r0.zzg;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzg = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.zze;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzg;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        int r102 = r0.zzd;
        Collection r22 = (Collection) r0.zzc;
        Iterator r6 = (Iterator) r0.zzb;
        Collection r7 = (Collection) r0.zza;
        zzyd r8 = r0.zzh;
        ResultKt.throwOnFailure(r112);
    L31:
        int r113 = ((Number) r112).intValue();
    L32:
        r22.add(Boxing.boxInt(r113));
        r22 = r7;
    L24:
        if (r6.hasNext() == false) goto L33;
        zzaur r114 = (zzaur) r6.next();
        if (Intrinsics.areEqual(r8.zzc.get(r114), Boxing.boxBoolean(true)) == false) goto L28;
        r7 = r22;
        r113 = 0;
        goto L32
    L28:
        r0.zzh = r8;
        r0.zza = r22;
        r0.zzb = r6;
        r0.zzc = r22;
        r0.zzd = r102;
        r0.zzg = 2;
        r112 = r114.zza(r0);
        if (r112 == r12) goto L63;
        r7 = r22;
    L63:
        return r12;
    L33:
        List r23 = (List) r22;
        if (r102 == 1) goto L62;
        List r24 = r23;
        boolean r115 = r24 instanceof Collection;
        if (r115 == true) goto L38;
    L40:
        Iterator r02 = r24.iterator();
    L42:
        if (r02.hasNext() == false) goto L46;
        if (((Number) r02.next()).intValue() != 1) goto L42;
    L46:
        if (r102 == 2) goto L60;
        if (r115 == true) goto L49;
    L51:
        Iterator r103 = r24.iterator();
    L53:
        if (r103.hasNext() == false) goto L58;
        if (((Number) r103.next()).intValue() != 2) goto L53;
    L58:
        return Boxing.boxInt(3);
    L49:
        if (r24.isEmpty() == false) goto L51;
    L60:
        return Boxing.boxInt(2);
    L38:
        if (r24.isEmpty() == false) goto L40;
    L62:
        return Boxing.boxInt(1);
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        r10 = r0.zzh;
        ResultKt.throwOnFailure(r112);
    L18:
        int r116 = ((Number) r112).intValue();
        if (r116 == 0) goto L21;
        List r25 = r10.zzb;
        ArrayList r62 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r25, 10));
        r6 = r25.iterator();
        r22 = r62;
        r8 = r10;
        r102 = r116;
        goto L24
    L21:
        return Boxing.boxInt(0);
    L16:
        ResultKt.throwOnFailure(r112);
        zzaur r117 = r10.zza;
        r0.zzh = r10;
        r0.zzg = 1;
        r112 = r117.zza(r0);
        if (r112 == r12) goto L63;
    L7:
        r0 = new zzxv(r10, r11);
        goto L8
    }

    public static /* synthetic */ Object zzb(zzyd r2, DownloadCallback r3, Continuation r4) {
        Object r22 = CoroutineScopeKt.coroutineScope(new zzya(r2, r3, null), r4);
        if (r22 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r22;
    L6:
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object zzc(zzyd r0, zzaur r1, DownloadCallback r2, boolean r3, CompletableDeferred r4, Function0 r5, Continuation r6, int r7, Object r8) {
        return r0.zzj(r1, r2, false, null, r5, r6);
    }

    public static final /* synthetic */ zzaur zze(zzyd r0) {
        return r0.zza;
    }

    public static final /* synthetic */ List zzf(zzyd r0) {
        return r0.zzb;
    }

    public static final /* synthetic */ Map zzg(zzyd r0) {
        return r0.zzc;
    }

    public static final /* synthetic */ Object zzh(zzyd r0, zzaur r1, DownloadCallback r2, boolean r3, CompletableDeferred r4, Function0 r5, Continuation r6) {
        return r0.zzj(r1, r2, r3, r4, r5, r6);
    }

    public static final /* synthetic */ void zzi(zzyd r0, zzaur r1, GenAiException r2, DownloadCallback r3) {
        r0.zzk(r1, r2, r3);
    }

    private final Object zzj(zzaur r12, DownloadCallback r13, boolean r14, CompletableDeferred r15, Function0 r16, Continuation r17) {
        if ((r17 instanceof zzyb) == false) goto L8;
        zzyb r1 = (zzyb) r17;
        int r2 = r1.zzg;
        if ((r2 & Integer.MIN_VALUE) == 0) goto L8;
        r1.zzg = r2 - Integer.MIN_VALUE;
    L7:
        zzyb r7 = r1;
        Object r0 = r7.zze;
        Object r8 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r18 = r7.zzg;
        if (r18 == 0) goto L19;
        if (r18 != 1) goto L17;
        Function0 r19 = (Function0) r7.zzd;
        CompletableDeferred r22 = (CompletableDeferred) r7.zzc;
        DownloadCallback r3 = (DownloadCallback) r7.zzb;
        zzaur r5 = (zzaur) r7.zza;
        ResultKt.throwOnFailure(r0);     // Catch: GenAiException -> L15
    L38:
        return Unit.INSTANCE;
    L15:
        e = e;
        CompletableDeferred r32 = r22;
        DownloadCallback r23 = r3;
    L33:
        zzk(r5, e, r23);
        if (r32 == null) goto L36;
        Boxing.boxBoolean(r32.completeExceptionally(e));
    L36:
        r19.invoke();
        goto L38
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L19:
        ResultKt.throwOnFailure(r0);
    L30:
        e = e;
        Function0 r6 = r16;
    L32:
        r5 = r12;
        r23 = r13;
        r32 = r15;
        r19 = r6;
        goto L33
    L21:
        r6 = r16;
        DownloadCallback r02 = new zzyc(r14, r13, r15, this, r12, r6);     // Catch: GenAiException -> L28
        r7.zza = r12;     // Catch: GenAiException -> L30
        r7.zzb = r13;     // Catch: GenAiException -> L30
        r7.zzc = r15;     // Catch: GenAiException -> L30
        r6 = r16;
        r7.zzd = r6;     // Catch: GenAiException -> L28
        r7.zzg = 1;     // Catch: GenAiException -> L28
        if (r12.zzC(r02, r7) != r8) goto L38;
        return r8;
    L28:
        e = e;
    L8:
        r1 = new zzyb(this, r17);
        goto L7
    }

    private final void zzk(zzaur r4, GenAiException r5, DownloadCallback r6) {
        Boolean r0 = Boolean.TRUE;
        this.zzc.put(r4, r0);
        new StringBuilder(String.valueOf(r4).length() + 28);
        Objects.toString(r4);
        Log.w("DownloadManager", "Failed to download feature: ".concat(String.valueOf(r4)), r5);
        if (Intrinsics.areEqual(r4, this.zza) == false) goto L6;
        r6.onDownloadFailed(r5);
        return;
    }

    public final boolean zzd(zzaur r1) {
        return Intrinsics.areEqual(this.zzc.get(r1), Boolean.TRUE);
    }
}
