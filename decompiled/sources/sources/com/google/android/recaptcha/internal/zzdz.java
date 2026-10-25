package com.google.android.recaptcha.internal;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
final class zzdz extends SuspendLambda implements Function2 {
    final /* synthetic */ zzea zza;

    public zzdz(zzea r1, Continuation r2) {
        this.zza = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r1, Continuation r2) {
        return new zzdz(this.zza, r2);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdz) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r9);
        Cursor r92 = zzea.zze(this.zza).query(Uri.parse("content://com.google.android.gsf.gservices"), null, null, new String[]{"android_id"}, null);
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r92 == null) goto L13;
        if (r92.moveToFirst() == false) goto L13;
        if (r92.getColumnCount() < 2) goto L13;
        r0 = String.valueOf(Long.parseLong(r92.getString(1)));     // Catch: Exception -> L21
    L11:
        r92.close();
    L13:
        if (r0.length() == 0) goto L15;
        zzea r8 = this.zza;
        zzaly r93 = zzamb.zzb();
        zzalz r1 = zzama.zza();
        r1.zzc(r0);
        r93.zzc(CollectionsKt.listOf(r1.zzp()));
        return zzew.zza(r8, (zzamb) r93.zzp());
    L15:
        if (Build.VERSION.SDK_INT <= 34) goto L18;
        throw new zzhg(zzhf.zzb, zzhe.zzP, null, null, 12, null);
    L18:
        throw new zzhg(zzhf.zzb, zzhe.zzQ, null, null, 12, null);
    }
}
