package com.google.android.recaptcha.internal;

import com.google.android.play.core.integrity.StandardIntegrityManager;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzfp {
    public CompletableDeferred zza;
    private final StandardIntegrityManager zzb;
    private zzfq zzc;
    private long zzd;
    private final Mutex zze;
    private boolean zzf;
    private final zzhu zzg;

    public zzfp(zzhu r1, StandardIntegrityManager r2, long r3) {
        this.zzg = r1;
        this.zzb = r2;
        this.zzc = zzfq.zza;
        this.zze = MutexKt.Mutex$default(false, 1, null);
    }

    public static final /* synthetic */ Mutex zzd(zzfp r0) {
        return r0.zze;
    }

    public static final /* synthetic */ zzfq zze(zzfp r0) {
        return r0.zzc;
    }

    public static final /* synthetic */ void zzf(zzfp r0, zzfq r1) {
        r0.zzc = r1;
    }

    public static final /* synthetic */ Object zzg(zzfp r0, Continuation r1) {
        return r0.zzl(r1);
    }

    public static final /* synthetic */ boolean zzh(zzfp r0) {
        return r0.zzf;
    }

    public static final /* synthetic */ void zzi(zzfp r0, boolean r1) {
        r0.zzf = true;
    }

    public static final /* synthetic */ Object zzj(zzfp r0, String r1, Continuation r2) {
        return r0.zzm(r1, r2);
    }

    public static final /* synthetic */ zzhu zzk(zzfp r0) {
        return r0.zzg;
    }

    private final Object zzl(Continuation r7) {
        if ((r7 instanceof zzff) == false) goto L7;
        zzff r0 = (zzff) r7;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L15;
        if (r2 != 1) goto L13;
        ResultKt.throwOnFailure(r72);
        return r72;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r72);
        StandardIntegrityManager.PrepareIntegrityTokenRequest r73 = StandardIntegrityManager.PrepareIntegrityTokenRequest.builder().setCloudProjectNumber(this.zzd).build();
        Deferred r6 = zzic.zza(this.zzb.prepareIntegrityToken(r73));
        r0.zzc = 1;
        Object r62 = r6.await(r0);
        if (r62 != r12) goto L18;
        return r12;
    L18:
        return r62;
    L7:
        r0 = new zzff(this, r7);
        goto L8
    }

    private final Object zzm(String r7, Continuation r8) {
        if ((r8 instanceof zzfj) == false) goto L7;
        zzfj r0 = (zzfj) r8;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        ResultKt.throwOnFailure(r82);
    L22:
        return ((StandardIntegrityManager.StandardIntegrityToken) r82).token();
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        r7 = r0.zzd;
        ResultKt.throwOnFailure(r82);
    L18:
        Deferred r6 = zzic.zza(((StandardIntegrityManager.StandardIntegrityTokenProvider) r82).request(StandardIntegrityManager.StandardIntegrityTokenRequest.builder().setRequestHash(r7).build()));
        r0.zzd = null;
        r0.zzc = 2;
        r82 = r6.await(r0);
        if (r82 != r12) goto L22;
    L23:
        return r12;
    L16:
        ResultKt.throwOnFailure(r82);
        CompletableDeferred r62 = zza();
        r0.zzd = r7;
        r0.zzc = 1;
        r82 = r62.await(r0);
        if (r82 == r12) goto L23;
    L7:
        r0 = new zzfj(this, r8);
        goto L8
    }

    public final CompletableDeferred zza() {
        CompletableDeferred r0 = this.zza;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }

    public final void zzb(long r1) {
        this.zzd = r1;
    }

    public final Object zzc(Continuation r2) {
        return new zzmc(new zzfo(this, null));
    }
}
