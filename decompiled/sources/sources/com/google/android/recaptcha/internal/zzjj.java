package com.google.android.recaptcha.internal;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaTasksClient;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzjj implements RecaptchaTasksClient {
    private final zzkn zza;
    private final String zzb;
    private final zzmd zzc;
    private final Mutex zzd;
    private final zzhu zze;

    public zzjj(zzkn r1, String r2, zzmd r3, zzhu r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zze = r4;
        this.zzd = MutexKt.Mutex$default(false, 1, null);
    }

    public static final /* synthetic */ Object zzc(zzjj r0, RecaptchaAction r1, long r2, Continuation r4) {
        return r0.zzf(r1, r2, r4);
    }

    public static final /* synthetic */ Mutex zzd(zzjj r0) {
        return r0.zzd;
    }

    public static final /* synthetic */ zzmd zze(zzjj r0) {
        return r0.zzc;
    }

    private final Object zzf(RecaptchaAction r11, long r12, Continuation r14) {
        if ((r14 instanceof zzjd) == false) goto L7;
        zzjd r0 = (zzjd) r14;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r142 = r0.zza;
        Object r13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r142);
    L18:
        return ((Result) r142).m2027unboximpl();
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r142);
        CoroutineContext r143 = this.zze.zzc().getCoroutineContext();
        zzjg r4 = new zzjg(this, r12, r11, null);
        r0.zzc = 1;
        r142 = BuildersKt.withContext(r143, r4, r0);
        if (r142 != r13) goto L18;
        return r13;
    L7:
        r0 = new zzjd(this, r14);
        goto L8
    }

    @Override // com.google.android.recaptcha.RecaptchaTasksClient
    public final Task<String> executeTask(RecaptchaAction r3) {
        return executeTask(r3, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    }

    public final Task<String> executeTask(RecaptchaAction r9, long r10) {
        return zzhz.zza(BuildersKt.async$default(this.zze.zza(), null, null, new zzjh(this, r9, r10, null), 3, null));
    }

    public final zzkn zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }
}
