package com.google.android.recaptcha.internal;

import android.util.Base64;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public abstract class zzoa {
    private final zzoo zza;
    private final zznf zzb;
    private final String zzc;
    private final zzahe zzd;
    private final Mutex zze;
    private final int zzf;
    private final int zzg;
    private final int zzh;
    private final int zzi;

    public /* synthetic */ zzoa(zzoo r1, zznf r2, String r3, int r4, int r5, int r6, int r7, zzahe r8, DefaultConstructorMarker r9) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzf = r4;
        this.zzg = r5;
        this.zzh = r6;
        this.zzi = r7;
        this.zzd = r8;
        this.zze = MutexKt.Mutex$default(false, 1, null);
    }

    private final Object zza(zzagx r7, String r8, Continuation r9) {
        if ((r9 instanceof zznz) == false) goto L7;
        zznz r0 = (zznz) r9;
        int r1 = r0.zzd;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzd = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.zzb;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzd;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        ResultKt.throwOnFailure(r92);
    L23:
        return Unit.INSTANCE;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        String r6 = r0.zze;
        zzoo r72 = (zzoo) r0.zza;
        ResultKt.throwOnFailure(r92);
    L19:
        r0.zza = null;
        r0.zze = null;
        r0.zzd = 2;
        if (r72.zzb(r6, (String) r92, r0) != r12) goto L23;
    L24:
        return r12;
    L16:
        ResultKt.throwOnFailure(r92);
        zzoo r93 = this.zza;
        String r22 = this.zzc;
        String r82 = String.valueOf(r8);
        r0.zza = r93;
        String r83 = r22.concat(r82);
        r0.zze = r83;
        r0.zzd = 1;
        byte[] r73 = r7.zzw();
        String r62 = Base64.encodeToString(this.zzb.zza(r73), 8);
        if (r62 == r12) goto L24;
        r72 = r93;
        r92 = r62;
        r6 = r83;
    L7:
        r0 = new zznz(this, r9);
        goto L8
    }

    private final Object zzb(String r12, Continuation r13) {
        if ((r13 instanceof zznw) == false) goto L7;
        zznw r0 = (zznw) r13;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r132 = r0.zza;
        Object r14 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        zzagx r3 = null;
        if (r2 == 0) goto L18;
        if (r2 == 1) goto L16;
        if (r2 != 2) goto L14;
        ResultKt.throwOnFailure(r132);
        return r132;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        ResultKt.throwOnFailure(r132);     // Catch: Exception -> L36
    L22:
        String r133 = (String) r132;     // Catch: Exception -> L36
        if (r133 == null) goto L35;
        r0.zzc = 2;
        byte[] r122 = this.zzb.zzb(Base64.decode(r133, 8));
        if (r122 != null) goto L41;
    L31:
        if (r3 != r14) goto L33;
    L32:
        return r14;
    L33:
        return r3;
    L41:
        r3 = (zzagx) this.zzd.zzb(r122);     // Catch: Exception -> L28
    L28:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzau, e.getMessage(), null, 8, null);
    L35:
        throw new zzhg(zzhf.zzb, zzhe.zzas, null, null, 12, null);
    L18:
        ResultKt.throwOnFailure(r132);
        zzoo r134 = this.zza;     // Catch: Exception -> L36
        String r22 = this.zzc;     // Catch: Exception -> L36
        StringBuilder r7 = new StringBuilder(r22.length() + String.valueOf(r12).length());     // Catch: Exception -> L36
        r7.append(r22);     // Catch: Exception -> L36
        r7.append(r12);     // Catch: Exception -> L36
        String r123 = r7.toString();     // Catch: Exception -> L36
        r0.zzc = 1;     // Catch: Exception -> L36
        r132 = r134.zzc(r123, r0);     // Catch: Exception -> L36
        if (r132 != r14) goto L22;
    L36:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzat, e.getMessage(), null, 8, null);
    L7:
        r0 = new zznw(this, r13);
        goto L8
    }

    public static /* synthetic */ Object zze(zzoa r1, zzagx r2, Continuation r3) {
        zzny r32 = new zzny(r1, r2, null);
        return new zzmb(r1.zzg, r32, null);
    }

    public static /* synthetic */ Object zzg(zzoa r1, zzagx r2, Continuation r3) {
        zznx r32 = new zznx(r1, r2, null);
        return new zzmb(r1.zzi, r32, null);
    }

    public static /* synthetic */ Object zzi(zzoa r1, Continuation r2) {
        return new zzmc(new zznv(r1, null));
    }

    public static final /* synthetic */ Object zzj(zzoa r0, zzagx r1, String r2, Continuation r3) {
        return r0.zza(r1, r2, r3);
    }

    public static final /* synthetic */ Object zzk(zzoa r0, String r1, Continuation r2) {
        return r0.zzb(r1, r2);
    }

    public static final /* synthetic */ int zzl(zzoa r0) {
        return r0.zzf;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final Mutex zzd() {
        return this.zze;
    }

    public final Object zzf(Function1 r2, Continuation r3) {
        zznt r32 = new zznt(this, r2, null);
        return new zzmb(this.zzh, r32, null);
    }

    public final Object zzh(Continuation r1) {
        return zzi(this, r1);
    }
}
