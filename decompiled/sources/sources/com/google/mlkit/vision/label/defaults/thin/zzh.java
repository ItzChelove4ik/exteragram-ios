package com.google.mlkit.vision.label.defaults.thin;

import android.os.SystemClock;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_label.zzbe;
import com.google.android.gms.internal.mlkit_vision_label.zzdf;
import com.google.android.gms.internal.mlkit_vision_label.zzdh;
import com.google.android.gms.internal.mlkit_vision_label.zzjq;
import com.google.android.gms.internal.mlkit_vision_label.zzjr;
import com.google.android.gms.internal.mlkit_vision_label.zzjv;
import com.google.android.gms.internal.mlkit_vision_label.zzkd;
import com.google.android.gms.internal.mlkit_vision_label.zzke;
import com.google.android.gms.internal.mlkit_vision_label.zzkf;
import com.google.android.gms.internal.mlkit_vision_label.zzkg;
import com.google.android.gms.internal.mlkit_vision_label.zzky;
import com.google.android.gms.internal.mlkit_vision_label.zzlb;
import com.google.android.gms.internal.mlkit_vision_label.zzle;
import com.google.android.gms.internal.mlkit_vision_label.zzlg;
import com.google.android.gms.internal.mlkit_vision_label.zznf;
import com.google.android.gms.internal.mlkit_vision_label.zznm;
import com.google.android.gms.internal.mlkit_vision_label.zznp;
import com.google.android.gms.internal.mlkit_vision_label.zznr;
import com.google.android.gms.internal.mlkit_vision_label.zzns;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MLTaskInput;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import java.util.List;

/* loaded from: classes5.dex */
public final class zzh extends MLTask {
    private static final ImageUtils zzc = null;
    boolean zza;
    final zzlg zzb;
    private final zzb zzd;
    private final zznp zze;
    private final zznr zzf;

    static {
        zzc = ImageUtils.getInstance();
    }

    public zzh(ImageLabelerOptions r2, zzb r3, zznp r4) {
        this.zza = true;
        Preconditions.checkNotNull(r2, "ImageLabelerOptions can not be null");
        this.zzd = r3;
        this.zze = r4;
        zzle r32 = new zzle();
        r32.zza(Float.valueOf(r2.getConfidenceThreshold()));
        this.zzb = r32.zzb();
        this.zzf = zznr.zza(MlKitContext.getInstance().getApplicationContext());
    }

    private final void zze(zzke r13, InputImage r14, long r15) {
        long r2 = SystemClock.elapsedRealtime() - r15;
        zzf r0 = new zzf(this, r2, r13, r14);
        this.zze.zzh(r0, zzkf.zzn);
        zzdf r02 = new zzdf();
        r02.zza(this.zzb);
        r02.zzb(r13);
        r02.zzc(Boolean.valueOf(this.zza));
        zzdh r5 = r02.zzd();
        zzg r8 = zzg.zza;
        zznp r3 = this.zze;
        zzkf r4 = zzkf.zzbg;
        MLTaskExecutor.workerThreadExecutor().execute(new zznm(r3, r4, r5, r2, r8, null));
        long r10 = System.currentTimeMillis();
        this.zzf.zzc(24305, r13.zza(), r10 - r2, r10);
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void load() {
        monitor-enter(this);
        this.zzd.zzb();     // Catch: Throwable -> L6
        zznp r0 = this.zze;     // Catch: Throwable -> L6
        zzkg r1 = new zzkg();     // Catch: Throwable -> L6
        r1.zze(zzkd.zzb);     // Catch: Throwable -> L6
        zzlb r2 = new zzlb();     // Catch: Throwable -> L6
        r2.zzb(this.zzb);     // Catch: Throwable -> L6
        r2.zzc(zzbe.zzi(zzke.zza));     // Catch: Throwable -> L6
        r1.zzh(r2.zzd());     // Catch: Throwable -> L6
        r0.zzd(zzns.zzf(r1), zzkf.zzq);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void release() {
        monitor-enter(this);
        this.zzd.zzc();     // Catch: Throwable -> L6
        this.zza = true;     // Catch: Throwable -> L6
        zznp r0 = this.zze;     // Catch: Throwable -> L6
        zzkg r1 = new zzkg();     // Catch: Throwable -> L6
        r1.zze(zzkd.zzb);     // Catch: Throwable -> L6
        r0.zzd(zzns.zzf(r1), zzkf.zzp);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // com.google.mlkit.common.sdkinternal.MLTask
    public final /* bridge */ /* synthetic */ Object run(MLTaskInput r1) {
        return zzd((InputImage) r1);
    }

    public final /* synthetic */ zznf zzc(long r4, zzke r6, InputImage r7) {
        zzkg r0 = new zzkg();
        r0.zze(zzkd.zzb);
        zzky r1 = new zzky();
        zzjv r2 = new zzjv();
        r2.zzc(Long.valueOf(r4));
        r2.zzd(r6);
        r2.zze(Boolean.valueOf(this.zza));
        Boolean r42 = Boolean.TRUE;
        r2.zza(r42);
        r2.zzb(r42);
        r1.zzd(r2.zzf());
        ImageUtils r43 = zzc;
        int r5 = r43.getMobileVisionImageFormat(r7);
        int r44 = r43.getMobileVisionImageSize(r7);
        zzjq r62 = new zzjq();
        if (r5 != (-1)) goto L5;
        zzjr r52 = zzjr.zzg;
    L18:
        r62.zza(r52);
        r62.zzb(Integer.valueOf(r44));
        r1.zzc(r62.zzd());
        r1.zze(this.zzb);
        r0.zzg(r1.zzf());
        return zzns.zzf(r0);
    L5:
        if (r5 != 35) goto L7;
        r52 = zzjr.zze;
        goto L18
    L7:
        if (r5 != 842094169) goto L9;
        r52 = zzjr.zzd;
        goto L18
    L9:
        if (r5 != 16) goto L11;
        r52 = zzjr.zzb;
        goto L18
    L11:
        if (r5 == 17) goto L13;
        r52 = zzjr.zza;
        goto L18
    L13:
        r52 = zzjr.zzc;
        goto L18
    }

    public final synchronized List zzd(InputImage r6) {
        monitor-enter(this);
        Preconditions.checkNotNull(r6, "Mobile vision input can not be null");     // Catch: Throwable -> L7
        long r0 = SystemClock.elapsedRealtime();     // Catch: Throwable -> L7
        List r2 = this.zzd.zza(r6);     // Catch: Throwable -> L7 MlKitException -> L9
        zze(zzke.zza, r6, r0);     // Catch: Throwable -> L7 MlKitException -> L9
        this.zza = false;     // Catch: Throwable -> L7 MlKitException -> L9
        monitor-exit(this);
        return r2;
    L9:
        e = move-exception;
        if (e.getErrorCode() != 14) goto L13;
        zzke r3 = zzke.zzk;     // Catch: Throwable -> L7
    L14:
        zze(r3, r6, r0);     // Catch: Throwable -> L7
        throw e;     // Catch: Throwable -> L7
    L13:
        r3 = zzke.zzV;     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }
}
