package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaErrorCode;
import com.google.android.recaptcha.RecaptchaException;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes5.dex */
public final class zzhg extends Exception {
    private static final Map zzf = null;
    private final zzhf zza;
    private final zzhe zzb;
    private final String zzc;
    private final Exception zzd;
    private final Map zze;

    static {
        Pair r2 = TuplesKt.to(zzami.zzc, new zzhg(zzhf.zzc, zzhe.zzd, null, null, 12, null));
        zzami r0 = zzami.zzd;
        zzhf r4 = zzhf.zzb;
        zzf = MapsKt.mapOf(new Pair[]{r2, TuplesKt.to(r0, new zzhg(r4, zzhe.zzc, null, null, 12, null)), TuplesKt.to(zzami.zze, new zzhg(zzhf.zzd, zzhe.zze, null, null, 12, null)), TuplesKt.to(zzami.zzf, new zzhg(zzhf.zze, zzhe.zzf, null, null, 12, null)), TuplesKt.to(zzami.zzi, new zzhg(zzhf.zzf, zzhe.zzg, null, null, 12, null)), TuplesKt.to(zzami.zzh, new zzhg(zzhf.zzg, zzhe.zzh, null, null, 12, null)), TuplesKt.to(zzami.zzj, new zzhg(r4, zzhe.zzj, null, null, 12, null))});
    }

    public zzhg(zzhf r11, zzhe r12, String r13, Exception r14) {
        this.zza = r11;
        this.zzb = r12;
        this.zzc = r13;
        this.zzd = r14;
        zzhf r112 = zzhf.zzc;
        RecaptchaErrorCode r132 = RecaptchaErrorCode.NETWORK_ERROR;
        this.zze = MapsKt.mapOf(new Pair[]{TuplesKt.to(r112, new RecaptchaException(r132, null, 2, null)), TuplesKt.to(zzhf.zzh, new RecaptchaException(r132, null, 2, null)), TuplesKt.to(zzhf.zzi, new RecaptchaException(r132, null, 2, null)), TuplesKt.to(zzhf.zzd, new RecaptchaException(RecaptchaErrorCode.INVALID_SITEKEY, null, 2, null)), TuplesKt.to(zzhf.zze, new RecaptchaException(RecaptchaErrorCode.INVALID_KEYTYPE, null, 2, null)), TuplesKt.to(zzhf.zzf, new RecaptchaException(RecaptchaErrorCode.INVALID_PACKAGE_NAME, null, 2, null)), TuplesKt.to(zzhf.zzg, new RecaptchaException(RecaptchaErrorCode.INVALID_ACTION, null, 2, null)), TuplesKt.to(zzhf.zzb, new RecaptchaException(RecaptchaErrorCode.INTERNAL_ERROR, null, 2, null)), TuplesKt.to(zzhf.zzj, new RecaptchaException(RecaptchaErrorCode.INVALID_TIMEOUT, null, 2, null))});
    }

    public /* synthetic */ zzhg(zzhf r1, zzhe r2, String r3, Exception r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 4) == 0) goto L5;
        r3 = null;
    L5:
        this(r1, r2, r3, null);
    }

    public static final /* synthetic */ Map zze() {
        return zzf;
    }

    @Override // java.lang.Throwable
    public final /* synthetic */ Throwable getCause() {
        return this.zzd;
    }

    public final zzhf zza() {
        return this.zza;
    }

    public final zzhe zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final RecaptchaException zzd() {
        zzhe r0 = this.zzb;
        if (Intrinsics.areEqual(r0, zzhe.zzm) == false) goto L7;
        return new RecaptchaException(RecaptchaErrorCode.INVALID_TIMEOUT, null, 2, null);
    L7:
        if (Intrinsics.areEqual(r0, zzhe.zzy) == true) goto L9;
        RecaptchaException r4 = (RecaptchaException) this.zze.get(this.zza);
        if (r4 == null) goto L13;
        return r4;
    L13:
        return new RecaptchaException(RecaptchaErrorCode.INTERNAL_ERROR, null, 2, null);
    L9:
        return new RecaptchaException(RecaptchaErrorCode.NO_NETWORK_FOUND, null, 2, null);
    }
}
