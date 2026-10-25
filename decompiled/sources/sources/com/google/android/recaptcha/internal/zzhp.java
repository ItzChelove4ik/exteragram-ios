package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.TimeoutKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzhp {
    public static final zzhp zza = null;

    static {
        zza = new zzhp();
    }

    private zzhp() {
    }

    public static /* synthetic */ Object zzb(zzhp r0, int r1, long r2, long r4, double r6, Function1 r8, Continuation r9, int r10, Object r11) {
        return r0.zza(r1, 100, 1000, 2.0d, r8, r9);
    }

    public static /* synthetic */ Object zzd(zzhp r10, Function1 r11, long r12, long r14, double r16, Function1 r18, Continuation r19, int r20, Object r21) {
        if ((r20 & 2) == 0) goto L6;
        long r122 = 100;
    L5:
        long r2 = r122;
        if ((r20 & 4) == 0) goto L10;
        long r4 = 1000;
    L12:
        return r10.zzc(r11, r2, r4, 2.0d, r18, r19);
    L10:
        r4 = r14;
        goto L12
    L6:
        r122 = 0;
        goto L5
    }

    public static /* synthetic */ Object zze(zzhp r10, long r11, int r13, long r14, long r16, double r18, Function1 r20, Continuation r21, int r22, Object r23) {
        return TimeoutKt.withTimeout(r11, new zzho(20, 100, 1000, 2.0d, r20, null), r21);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00be -> B:18:0x004b). Please report as a decompilation issue!!! */
    public final Object zza(int r18, long r19, long r21, double r23, Function1 r25, Continuation r26) {
        if ((r26 instanceof zzhm) == false) goto L7;
        zzhm r1 = (zzhm) r26;
        int r2 = r1.zzh;
        if ((r2 & Integer.MIN_VALUE) == 0) goto L7;
        r1.zzh = r2 - Integer.MIN_VALUE;
    L8:
        Object r0 = r1.zzf;
        Object r22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r3 = r1.zzh;
        Ref.LongRef r4 = null;
        if (r3 == 0) goto L22;
        if (r3 == 1) goto L19;
        if (r3 == 2) goto L17;
        if (r3 != 3) goto L15;
        ResultKt.throwOnFailure(r0);
        return r0;
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L17:
        int r32 = r1.zze;
        int r8 = r1.zzd;
        double r9 = r1.zzb;
        long r11 = r1.zza;
        Ref.LongRef r13 = r1.zzi;
        Function1 r14 = (Function1) r1.zzc;
        ResultKt.throwOnFailure(r0);
    L18:
        long r15 = r11;
        zzhm r112 = r1;
        long r02 = r15;
        int r42 = r32;
        int r12 = r8;
        double r82 = r9;
        Ref.LongRef r10 = r13;
        Function1 r33 = r14;
        int r132 = r42 + 1;
        r4 = null;
    L23:
        if (r132 < r12) goto L37;
        Ref.LongRef r03 = r4;
        r112.zzc = r03;
        r112.zzi = r03;
        r112.zzh = 3;
        Object r04 = r33.invoke(r112);
        if (r04 != r22) goto L35;
        return r22;
    L35:
        return r04;
    L37:
        r112.zzc = r33;     // Catch: Exception -> L28
        r112.zzi = r10;     // Catch: Exception -> L28
        r112.zza = r02;     // Catch: Exception -> L28
        r112.zzb = r82;     // Catch: Exception -> L28
        r112.zzd = r12;     // Catch: Exception -> L28
        r112.zze = r132;     // Catch: Exception -> L28
        r112.zzh = 1;     // Catch: Exception -> L28
        r04 = r33.invoke(r112);     // Catch: Exception -> L28
        if (r04 != r22) goto L35;
        return r22;
    L28:
        r14 = r33;
        r32 = r132;
        r13 = r10;
        r9 = r82;
        r8 = r12;
        r1 = r112;
        r11 = r02;
    L29:
        long r43 = RangesKt.coerceAtMost((long) (r13.element * r9), r11);
        r13.element = r43;
        r1.zzc = r14;
        r1.zzi = r13;
        r1.zza = r11;
        r1.zzb = r9;
        r1.zzd = r8;
        r1.zze = r32;
        r1.zzh = 2;
        if (DelayKt.delay(r43, r1) != r22) goto L18;
        return r22;
    L19:
        r32 = r1.zze;
        r8 = r1.zzd;
        r9 = r1.zzb;
        r11 = r1.zza;
        r13 = r1.zzi;
        r14 = (Function1) r1.zzc;
        ResultKt.throwOnFailure(r0);     // Catch: Exception -> L36
        return r0;
    L22:
        ResultKt.throwOnFailure(r0);
        Ref.LongRef r05 = new Ref.LongRef();
        r05.element = r19;
        r10 = r05;
        r112 = r1;
        r12 = r18 - 1;
        r132 = 0;
        r02 = r21;
        r82 = r23;
        r33 = r25;
    L7:
        r1 = new zzhm(this, r26);
        goto L8
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00ab -> B:13:0x003d). Please report as a decompilation issue!!! */
    public final Object zzc(Function1 r17, long r18, long r20, double r22, Function1 r24, Continuation r25) {
        if ((r25 instanceof zzhn) == false) goto L7;
        zzhn r1 = (zzhn) r25;
        int r2 = r1.zzh;
        if ((r2 & Integer.MIN_VALUE) == 0) goto L7;
        r1.zzh = r2 - Integer.MIN_VALUE;
    L8:
        Object r0 = r1.zzf;
        Object r23 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r3 = r1.zzh;
        if (r3 == 0) goto L21;
        if (r3 == 1) goto L16;
        if (r3 != 2) goto L14;
        long r6 = r1.zzd;
        double r8 = r1.zze;
        long r10 = r1.zzc;
        Function1 r32 = (Function1) r1.zzb;
        Function1 r12 = (Function1) r1.zza;
        ResultKt.throwOnFailure(r0);
    L13:
        Function1 r14 = r12;
        zzhn r122 = r1;
        Function1 r13 = r14;
        long r142 = r10;
        double r102 = r8;
        long r82 = r142;
    L37:
        r122.zza = r13;     // Catch: Exception -> L26
        r122.zzb = r32;     // Catch: Exception -> L26
        r122.zzc = r82;     // Catch: Exception -> L26
        r122.zze = r102;     // Catch: Exception -> L26
        r122.zzd = r6;     // Catch: Exception -> L26
        r122.zzh = 1;     // Catch: Exception -> L26
        Object r02 = r32.invoke(r122);     // Catch: Exception -> L26
        if (r02 == r23) goto L33;
        return r02;
    L33:
        return r23;
    L26:
        Exception e = e;
        zzhn r143 = r122;
        r12 = r13;
        r1 = r143;
        double r144 = r102;
        r10 = r82;
        r8 = r144;
    L29:
        if (((Boolean) r12.invoke(e)).booleanValue() == false) goto L34;
        r6 = RangesKt.coerceAtMost((long) (r6 * r8), r10);
        r1.zza = r12;
        r1.zzb = r32;
        r1.zzc = r10;
        r1.zze = r8;
        r1.zzd = r6;
        r1.zzh = 2;
        if (DelayKt.delay(r6, r1) == r23) goto L33;
    L34:
        throw e;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        r6 = r1.zzd;
        r8 = r1.zze;
        r10 = r1.zzc;
        r32 = (Function1) r1.zzb;
        r12 = (Function1) r1.zza;
        ResultKt.throwOnFailure(r0);     // Catch: Exception -> L19
        return r0;
    L19:
        e = e;
        goto L29
    L21:
        ResultKt.throwOnFailure(r0);
        r6 = r18;
        r82 = r20;
        r102 = r22;
        r32 = r24;
        r122 = r1;
        r13 = r17;
    L7:
        r1 = new zzhn(this, r25);
        goto L8
    }
}
