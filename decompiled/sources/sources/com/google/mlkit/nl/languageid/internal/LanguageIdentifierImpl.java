package com.google.mlkit.nl.languageid.internal;

import android.os.SystemClock;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_language_id_common.zzhm;
import com.google.android.gms.internal.mlkit_language_id_common.zzhs;
import com.google.android.gms.internal.mlkit_language_id_common.zzhu;
import com.google.android.gms.internal.mlkit_language_id_common.zzhw;
import com.google.android.gms.internal.mlkit_language_id_common.zzhx;
import com.google.android.gms.internal.mlkit_language_id_common.zzhy;
import com.google.android.gms.internal.mlkit_language_id_common.zzhz;
import com.google.android.gms.internal.mlkit_language_id_common.zziu;
import com.google.android.gms.internal.mlkit_language_id_common.zziv;
import com.google.android.gms.internal.mlkit_language_id_common.zziy;
import com.google.android.gms.internal.mlkit_language_id_common.zzja;
import com.google.android.gms.internal.mlkit_language_id_common.zzjd;
import com.google.android.gms.internal.mlkit_language_id_common.zzla;
import com.google.android.gms.internal.mlkit_language_id_common.zzli;
import com.google.android.gms.internal.mlkit_language_id_common.zzlk;
import com.google.android.gms.internal.mlkit_language_id_common.zzll;
import com.google.android.gms.internal.mlkit_language_id_common.zzlt;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.nl.languageid.LanguageIdentificationOptions;
import com.google.mlkit.nl.languageid.LanguageIdentifier;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
public class LanguageIdentifierImpl implements LanguageIdentifier {
    private final LanguageIdentificationOptions zza;
    private final zzli zzb;
    private final zzlk zzc;
    private final Executor zzd;
    private final AtomicReference zze;
    private final CancellationTokenSource zzf;
    private final zzhw zzg;

    /* loaded from: classes.dex */
    public static final class Factory {
        private final zzli zza;
        private final zzg zzb;
        private final ExecutorSelector zzc;

        public Factory(zzg r1, ExecutorSelector r2) {
            this.zzb = r1;
            this.zzc = r2;
            if (true == r1.zzf()) goto L5;
            String r12 = "play-services-mlkit-language-id";
        L6:
            this.zza = zzlt.zzb(r12);
            return;
        L5:
            r12 = "language-id";
            goto L6
        }

        public LanguageIdentifier create(LanguageIdentificationOptions r3) {
            this.zzb.zze(r3);
            return LanguageIdentifierImpl.zza(r3, this.zzb, this.zza, this.zzc);
        }
    }

    private LanguageIdentifierImpl(LanguageIdentificationOptions r1, zzg r2, zzli r3, Executor r4) {
        this.zza = r1;
        this.zzb = r3;
        this.zzd = r4;
        this.zze = new AtomicReference(r2);
        this.zzf = new CancellationTokenSource();
        if (r2.zzf() == false) goto L5;
        zzhw r12 = zzhw.zzc;
    L6:
        this.zzg = r12;
        this.zzc = zzlk.zza(MlKitContext.getInstance().getApplicationContext());
        return;
    L5:
        r12 = zzhw.zzb;
        goto L6
    }

    public static LanguageIdentifier zza(LanguageIdentificationOptions r2, zzg r3, zzli r4, ExecutorSelector r5) {
        LanguageIdentifierImpl r0 = new LanguageIdentifierImpl(r2, r3, r4, r5.getExecutorToUse(r2.getExecutor()));
        zzli r22 = r0.zzb;
        zzhz r32 = new zzhz();
        r32.zzc(r0.zzg);
        zziu r42 = new zziu();
        r42.zzf(zzf(r0.zza.getConfidenceThreshold()));
        r32.zze(r42.zzi());
        r22.zzc(zzll.zzg(r32, 1), zzhy.zzx);
        ((zzg) r0.zze.get()).pin();
        return r0;
    }

    private final void zze(long r9, boolean r11, zzjd r12, zzja r13, zzhx r14) {
        long r2 = SystemClock.elapsedRealtime() - r9;
        zzf r0 = new zzf(this, r2, r11, r14, r12, r13);
        this.zzb.zze(r0, zzhy.zzw);
        long r4 = System.currentTimeMillis();
        zzlk r02 = this.zzc;
        if (this.zzg != zzhw.zzc) goto L5;
        int r1 = 24603;
    L6:
        int r92 = r1;
        r02.zzc(r92, r14.zza(), r4 - r2, r4);
        return;
    L5:
        r1 = 24602;
        goto L6
    }

    private static final zzhu zzf(Float r1) {
        zzhs r0 = new zzhs();
        if (r1 != null) goto L5;
        float r12 = -1.0f;
    L6:
        r0.zza(Float.valueOf(r12));
        return r0.zzb();
    L5:
        r12 = r1.floatValue();
        goto L6
    }

    @Override // com.google.mlkit.nl.languageid.LanguageIdentifier, java.io.Closeable, java.lang.AutoCloseable
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void close() {
        zzg r0 = (zzg) this.zze.getAndSet(null);
        if (r0 != null) goto L5;
        return;
    L5:
        this.zzf.cancel();
        r0.unpin(this.zzd);
        zzli r02 = this.zzb;
        zzhz r1 = new zzhz();
        r1.zzc(this.zzg);
        zziu r2 = new zziu();
        r2.zzf(zzf(this.zza.getConfidenceThreshold()));
        r1.zze(r2.zzi());
        r02.zzc(zzll.zzg(r1, 1), zzhy.zzz);
    }

    @Override // com.google.android.gms.common.api.OptionalModuleApi
    public final Feature[] getOptionalFeatures() {
        if (this.zzg != zzhw.zzc) goto L7;
        return OptionalModuleUtils.EMPTY_FEATURES;
    L7:
        return new Feature[]{OptionalModuleUtils.FEATURE_LANGID};
    }

    @Override // com.google.mlkit.nl.languageid.LanguageIdentifier
    public final Task<String> identifyLanguage(String r6) {
        Preconditions.checkNotNull(r6, "Text can not be null");
        zzg r0 = (zzg) this.zze.get();
        if (r0 == null) goto L5;
        boolean r2 = true;
    L6:
        Preconditions.checkState(r2, "LanguageIdentification has been closed");
        return r0.callAfterLoad(this.zzd, new zze(this, r0, r6, true ^ r0.isLoaded()), this.zzf.getToken());
    L5:
        r2 = false;
        goto L6
    }

    public final /* synthetic */ zzla zzb(long r2, boolean r4, zzhx r5, zzjd r6, zzja r7) {
        zziu r62 = new zziu();
        r62.zzf(zzf(this.zza.getConfidenceThreshold()));
        zzhm r0 = new zzhm();
        r0.zza(Long.valueOf(r2));
        r0.zzc(Boolean.valueOf(r4));
        r0.zzb(r5);
        r62.zze(r0.zzd());
        if (r7 == null) goto L5;
        r62.zzc(r7);
    L5:
        zzhz r22 = new zzhz();
        r22.zzc(this.zzg);
        r22.zze(r62.zzi());
        return zzll.zzf(r22);
    }

    public final /* synthetic */ String zzc(zzg r9, String r10, boolean r11) {
        Float r0 = this.zza.getConfidenceThreshold();
        long r2 = SystemClock.elapsedRealtime();
        String r102 = r10.substring(0, Math.min(r10.length(), 200));     // Catch: RuntimeException -> L21
        if (r0 != null) goto L29;
        float r02 = 0.5f;
    L10:
        String r92 = r9.zzc(r102, r02);     // Catch: RuntimeException -> L21
        if (r92 != null) goto L14;
        zzja r103 = null;
    L15:
        LanguageIdentifierImpl r1 = this;
        boolean r4 = r11;
        r1.zze(r2, r4, null, r103, zzhx.zza);     // Catch: RuntimeException -> L19
        return r92;
    L19:
        e = e;
    L20:
        RuntimeException r93 = e;
    L23:
        r1.zze(r2, r4, null, null, zzhx.zzV);
        throw r93;
    L14:
        zziy r104 = new zziy();     // Catch: RuntimeException -> L21
        zziv r03 = new zziv();     // Catch: RuntimeException -> L21
        r03.zzb(r92);     // Catch: RuntimeException -> L21
        r104.zzb(r03.zzc());     // Catch: RuntimeException -> L21
        r103 = r104.zzc();     // Catch: RuntimeException -> L21
        goto L15
    L29:
        r02 = r0.floatValue();     // Catch: RuntimeException -> L7
    L7:
        e = move-exception;
        r93 = e;
        r1 = this;
        r4 = r11;
    L21:
        e = e;
        r1 = this;
        r4 = r11;
        goto L20
    }
}
