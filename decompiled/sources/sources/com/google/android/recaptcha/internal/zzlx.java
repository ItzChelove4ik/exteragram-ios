package com.google.android.recaptcha.internal;

import android.content.Context;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.BuildersKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzlx implements zzlr {
    private static Timer zzb;
    private final zzlq zza;
    private final zzlz zzc;
    private final zzhu zzd;

    public zzlx(Context r7, zzlz r8, zzhu r9, zzlq r10) {
        this.zzc = r8;
        this.zzd = r9;
        this.zza = r10;
        if (zzb != null) goto L6;
        Timer r0 = new Timer();
        zzb = r0;
        r0.schedule(new zzlu(this), NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
        return;
    }

    public static /* synthetic */ void zzb(zzlx r0, int r1, int r2, Object r3) {
        r0.zzg(20);
    }

    public static final /* synthetic */ zzlq zzc(zzlx r0) {
        return r0.zza;
    }

    public static final /* synthetic */ Object zzd(zzlx r0, List r1, Continuation r2) {
        return r0.zzh(r1, r2);
    }

    public static final /* synthetic */ void zze(zzlx r0, int r1) {
        r0.zzg(0);
    }

    public static final /* synthetic */ Object zzf(zzlx r0, List r1, Continuation r2) {
        return r0.zzi(null, r2);
    }

    private final void zzg(int r8) {
        BuildersKt.launch$default(this.zzd.zzf(), null, null, new zzlw(this, r8, null), 3, null);
    }

    private final Object zzh(List r5, Continuation r6) {
        if ((r6 instanceof zzls) == false) goto L7;
        zzls r0 = (zzls) r6;
        int r1 = r0.zzd;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzd = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.zzb;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzd;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        Iterator r52 = (Iterator) r0.zza;
        ResultKt.throwOnFailure(r62);
    L16:
        if (r52.hasNext() == false) goto L21;
        List r63 = (List) r52.next();
        r0.zza = r52;
        r0.zzd = 1;
        if (zzi(r63, r0) != r12) goto L16;
        return r12;
    L21:
        return Unit.INSTANCE;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r62);
        r52 = CollectionsKt.windowed(r5, 20, 20, true).iterator();
    L7:
        r0 = new zzls(this, r6);
        goto L8
    }

    private final Object zzi(List r10, Continuation r11) {
        if ((r11 instanceof zzlt) == false) goto L7;
        zzlt r0 = (zzlt) r11;
        int r1 = r0.zzd;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzd = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.zzb;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzd;
        if (r2 == 0) goto L16;
        if (r2 != 1) goto L14;
        List r102 = (List) r0.zza;
        ResultKt.throwOnFailure(r112);     // Catch: Exception -> L50
    L42:
        if (((Boolean) r112).booleanValue() == false) goto L49;
        this.zza.zzd(r102);     // Catch: Exception -> L50
    L49:
        return Unit.INSTANCE;
    L47:
        this.zza.zza(r102);
        goto L49
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        ResultKt.throwOnFailure(r112);
        zzajk r113 = zzajl.zzc();
        ArrayList r22 = new ArrayList();
        Iterator r103 = r10.iterator();
    L18:
        if (r103.hasNext() == false) goto L35;
        zzlo r5 = (zzlo) r103.next();
        zzamk r6 = zzamk.zzc(zzrv.zzi().zzh(r5.zza()));     // Catch: Exception -> L51
        int r7 = r6.zzh();
        int r8 = r7 - 1;
        if (r7 == 0) goto L33;
        if (r8 == 0) goto L31;
        if (r8 != 1) goto L26;
        r113.zzd(r6.zzb());
    L32:
        r22.add(r5);
        goto L18
    L26:
        if (r8 == 2) goto L32;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L31:
        r113.zzb(r6.zza());
        goto L32
    L33:
        throw null;
    L35:
        if ((r113.zza() + r113.zzc()) == 0) goto L37;
        zzlz r104 = this.zzc;     // Catch: Exception -> L46
        zzajl r114 = (zzajl) r113.zzp();     // Catch: Exception -> L46
        r0.zza = r22;     // Catch: Exception -> L46
        r0.zzd = 1;     // Catch: Exception -> L46
        r112 = r104.zza(r114, r0);     // Catch: Exception -> L46
        if (r112 == r12) goto L45;
        r102 = r22;
        goto L42
    L45:
        return r12;
    L46:
        r102 = r22;
        goto L47
    L37:
        return Unit.INSTANCE;
    L7:
        r0 = new zzlt(this, r11);
        goto L8
    }

    @Override // com.google.android.recaptcha.internal.zzlr
    public final void zza(zzamk r8) {
        BuildersKt.launch$default(this.zzd.zzc(), null, null, new zzlv(this, r8, null), 3, null);
    }
}
