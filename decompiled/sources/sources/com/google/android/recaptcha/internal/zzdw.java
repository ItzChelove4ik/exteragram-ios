package com.google.android.recaptcha.internal;

import android.util.Base64;
import com.google.android.gms.recaptchabase.ExecuteRequest;
import com.google.android.gms.recaptchabase.ExecuteResult;
import com.google.android.gms.recaptchabase.InitResult;
import com.google.android.gms.recaptchabase.RecaptchaBase;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Deferred;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
final class zzdw extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzdy zzb;
    final /* synthetic */ zzaka zzc;
    final /* synthetic */ String zzd;

    public zzdw(zzdy r1, zzaka r2, String r3, Continuation r4) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzdw(this.zzb, this.zzc, this.zzd, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdw) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L9;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r8);     // Catch: Exception -> L55
    L29:
        ExecuteResult r82 = (ExecuteResult) r8;     // Catch: Exception -> L55
        String r7 = this.zzd;
        zzakt r02 = zzaku.zza();
        r02.zza(r7);
        String r72 = r82.getPayload();
        if (r72 == null) goto L53;
        zzalt r73 = zzalt.zzc(Base64.decode(r72, 0));
        zzals r12 = (zzals) r73.zzG();
        r12.zzd();
        Iterator r74 = r73.zza().iterator();
    L34:
        if (r74.hasNext() == false) goto L51;
        zzalr r2 = (zzalr) r74.next();
        zzalp r4 = (zzalp) r2.zzG();
        if (r2.zza() == false) goto L42;
        byte[] r5 = Base64.decode(r2.zzb(), 0);     // Catch: IllegalArgumentException -> L39
        zzaer r6 = zzaer.zza;     // Catch: IllegalArgumentException -> L39
        r4.zze(zzaer.zzs(r5, 0, r5.length));     // Catch: IllegalArgumentException -> L39
    L40:
        r4.zza();
    L39:
        r2.zzh();
    L42:
        if (r2.zzc() == false) goto L48;
        byte[] r52 = Base64.decode(r2.zzd(), 0);     // Catch: IllegalArgumentException -> L45
        zzaer r62 = zzaer.zza;     // Catch: IllegalArgumentException -> L45
        r4.zzf(zzaer.zzs(r52, 0, r52.length));     // Catch: IllegalArgumentException -> L45
    L46:
        r4.zzb();
    L45:
        r2.zzh();
    L48:
        if (r4.zzc() == false) goto L50;
        zzaly r22 = (zzaly) zzamb.zza(r4.zzd()).zzG();     // Catch: Exception -> L62
        zzalz r53 = zzama.zza();     // Catch: Exception -> L62
        r53.zza(true);     // Catch: Exception -> L62
        r22.zzb(r53);     // Catch: Exception -> L62
        r4.zze(((zzamb) r22.zzp()).zzv());     // Catch: Exception -> L62
    L50:
        r12.zzb((zzalr) r4.zzp());
        goto L34
    L51:
        zzakb r75 = zzakc.zza();
        r75.zza((zzalt) r12.zzp());
        r02.zzd((zzakc) r75.zzp());
        return Result.m2017boximpl(Result.m2018constructorimpl((zzaku) r02.zzp()));
    L53:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L7:
        ResultKt.throwOnFailure(r8);     // Catch: Exception -> L60
    L15:
        InitResult r83 = (InitResult) r8;     // Catch: Exception -> L60
        zzaka r84 = this.zzc;
        if (r84 == null) goto L21;
        zzaer r85 = r84.zze();
        if (r85 == null) goto L21;
        String r86 = r85.zzz(StandardCharsets.UTF_8);
    L22:
        if (r86 != null) goto L24;
    L57:
        Result.Companion r76 = Result.Companion;
        return Result.m2017boximpl(Result.m2018constructorimpl(ResultKt.createFailure(new zzhg(zzhf.zzb, zzhe.zzav, null, null, 12, null))));
    L24:
        if (r86.length() == 0) goto L57;
        Deferred r87 = zzic.zza(RecaptchaBase.getClient(zzdy.zzh(this.zzb)).execute(new ExecuteRequest.Builder().setNonce(r86).setExecuteId(this.zzd).build()));     // Catch: Exception -> L55
        this.zza = 2;     // Catch: Exception -> L55
        r8 = r87.await(this);     // Catch: Exception -> L55
        if (r8 != r0) goto L29;
    L59:
        return r0;
    L21:
        r86 = null;
        goto L22
    L9:
        ResultKt.throwOnFailure(r8);
        Deferred r88 = zzdy.zzj(this.zzb);     // Catch: Exception -> L60
        if (r88 != null) goto L13;
        r88 = null;
    L13:
        this.zza = 1;     // Catch: Exception -> L60
        r8 = r88.await(this);     // Catch: Exception -> L60
        if (r8 == r0) goto L59;
    L55:
        Result.Companion r77 = Result.Companion;
        return Result.m2017boximpl(Result.m2018constructorimpl(ResultKt.createFailure(new zzhg(zzhf.zzb, zzhe.zzH, null, null, 12, null))));
    L60:
        Result.Companion r78 = Result.Companion;
        return Result.m2017boximpl(Result.m2018constructorimpl(ResultKt.createFailure(new zzhg(zzhf.zzb, zzhe.zzG, null, null, 12, null))));
    }
}
