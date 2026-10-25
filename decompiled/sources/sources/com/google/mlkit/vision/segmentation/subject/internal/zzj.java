package com.google.mlkit.vision.segmentation.subject.internal;

import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzas;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzav;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzdu;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzdw;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzng;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zznh;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zznn;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zznz;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzoa;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzob;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzoc;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzra;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzrd;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzrt;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzta;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zztj;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zztl;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zztn;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzto;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zztz;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzub;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzud;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzuf;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzuh;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzuj;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MLTaskInput;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.segmentation.subject.Subject;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmentationResult;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmenterOptions;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public final class zzj extends MLTask {
    private static final Feature[] zza = null;
    private static final ImageUtils zzb = null;
    private final Context zzc;
    private final SubjectSegmenterOptions zzd;
    private final zztl zze;
    private final zztn zzf;
    private boolean zzg;
    private boolean zzh;
    private zzub zzi;

    static {
        zza = new Feature[]{OptionalModuleUtils.FEATURE_SUBJECT_SEGMENTATION};
        zzb = ImageUtils.getInstance();
    }

    public zzj(MlKitContext r2, SubjectSegmenterOptions r3, zztl r4, zztn r5) {
        this.zzg = true;
        Preconditions.checkNotNull(r2, "MlKitContext can not be null");
        Preconditions.checkNotNull(r3, "SubjectSegmenterOptions can not be null");
        this.zzc = r2.getApplicationContext();
        this.zzd = r3;
        this.zze = r4;
        this.zzf = r5;
    }

    private final void zzg(zzoa r10, long r11, boolean r13, InputImage r14, zzuh r15) {
        long r2 = SystemClock.elapsedRealtime() - r11;
        zzg r0 = new zzg(this, r2, r10, r13, r14, r15);
        this.zze.zzf(r0, zzob.zzey);
        zzdu r02 = new zzdu();
        r02.zzc(this.zzd.zza());
        r02.zza(r10);
        r02.zzb(Boolean.valueOf(r13));
        zzdw r5 = r02.zzd();
        zzh r8 = zzh.zza;
        zzob r4 = zzob.zzbw;
        MLTaskExecutor.workerThreadExecutor().execute(new zztj(this.zze, r4, r5, r2, r8));
        long r42 = System.currentTimeMillis();
        zztn r03 = this.zzf;
        int r6 = r10.zza();
        r03.zzc(24336, r6, r42 - r2, r42);
    }

    private static final FloatBuffer zzh(float[] r1) {
        if (r1 != null) goto L5;
        return null;
    L5:
        FloatBuffer r0 = FloatBuffer.allocate(r1.length);
        r0.put(r1);
        r0.rewind();
        return r0;
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void load() {
        monitor-enter(this);
        long r1 = SystemClock.elapsedRealtime();     // Catch: Throwable -> L8
        Context r0 = this.zzc;     // Catch: Throwable -> L8
        Feature[] r3 = zza;     // Catch: Throwable -> L8
        if (OptionalModuleUtils.areAllRequiredModulesAvailable(r0, r3) == true) goto L30;
        if (this.zzh == true) goto L10;
        OptionalModuleUtils.requestDownload(this.zzc, r3);     // Catch: Throwable -> L8
        this.zzh = true;     // Catch: Throwable -> L8
    L10:
        zzf(zzoa.zzB, r1);     // Catch: Throwable -> L8
        throw new MlKitException("Waiting for the subject segmentation optional module to be downloaded. Please wait.", 14);     // Catch: Throwable -> L8
    L30:
    L17:
        e = move-exception;
        zzf(zzoa.zzH, r1);     // Catch: Throwable -> L8
        throw new MlKitException("Failed to load subject segmentation module", 13, e);     // Catch: Throwable -> L8
    L14:
        if (this.zzi != null) goto L33;
        DynamiteModule.VersionPolicy r02 = DynamiteModule.PREFER_REMOTE;     // Catch: Throwable -> L8 Exception -> L17
        this.zzi = zzud.zza(DynamiteModule.load(this.zzc, r02, "com.google.android.gms.mlkit_subject_segmentation").instantiate("com.google.android.gms.mlkit.segmentation.subject.SubjectSegmenterCreator")).zzd(ObjectWrapper.wrap(this.zzc), new zzuj(this.zzd.zzd(), this.zzd.zzc(), this.zzd.zze(), this.zzd.zzg(), this.zzd.zzf()));     // Catch: Throwable -> L8 Exception -> L17
    L33:
        this.zzi.zze();     // Catch: Throwable -> L8 RemoteException -> L23
        zzf(zzoa.zza, r1);     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L23:
        e = move-exception;
        zzf(zzoa.zzC, r1);     // Catch: Throwable -> L8
        throw new MlKitException("Failed to init module subject segmenter", 13, e);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void release() {
        monitor-enter(this);
        zzub r1 = this.zzi;     // Catch: Throwable -> L8 RemoteException -> L14
        if (r1 == null) goto L10;
        r1.zzf();     // Catch: Throwable -> L8 RemoteException -> L14
    L10:
        this.zzi = null;     // Catch: Throwable -> L12
    L16:
        this.zzg = true;     // Catch: Throwable -> L12
        this.zze.zzf(zzf.zza, zzob.zzez);     // Catch: Throwable -> L12
        monitor-exit(this);
        return;
    L14:
        Log.e("SubjectSegmenterTask", "Failed to release subject segmenter");     // Catch: Throwable -> L8
        this.zzi = null;     // Catch: Throwable -> L12
        goto L16
    L8:
        th = move-exception;
        this.zzi = null;     // Catch: Throwable -> L12
        throw th;     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        throw th;
    }

    @Override // com.google.mlkit.common.sdkinternal.MLTask
    public final /* bridge */ /* synthetic */ Object run(MLTaskInput r1) {
        return zze((InputImage) r1);
    }

    public final /* synthetic */ zzta zzc(long r3, zzoa r5, boolean r6, InputImage r7, zzuh r8) {
        zzra r0 = new zzra();
        zznn r1 = new zznn();
        r1.zza(Long.valueOf(r3));
        r1.zzb(r5);
        r1.zzc(Boolean.valueOf(r6));
        r0.zzf(r1.zzd());
        int r32 = r7.getFormat();
        int r4 = zzb.getMobileVisionImageSize(r7);
        zzng r52 = new zzng();
        if (r32 != (-1)) goto L5;
        zznh r33 = zznh.zzg;
    L18:
        r52.zza(r33);
        r52.zzb(Integer.valueOf(r4));
        r0.zze(r52.zzd());
        r0.zzi(this.zzd.zza());
        if (r8 == null) goto L27;
        r0.zzg(zzav.zzh(r8.zzb()));
        List r2 = r8.zzc();
        if (r2.isEmpty() == true) goto L27;
        zzas r34 = new zzas();
        Iterator r22 = r2.iterator();
    L24:
        if (r22.hasNext() == false) goto L26;
        zzuf r42 = (zzuf) r22.next();
        zzrt r53 = new zzrt();
        r53.zzd(Integer.valueOf(r42.zzd()));
        r53.zza(Integer.valueOf(r42.zza()));
        r53.zzb(Integer.valueOf(r42.zzb()));
        r53.zzc(Integer.valueOf(r42.zzc()));
        r34.zza(r53.zze());
        goto L24
    L26:
        r0.zzh(r34.zzb());
    L27:
        zzoc r23 = new zzoc();
        r23.zze(zznz.zzb);
        r23.zzh(r0.zzj());
        return zzto.zzf(r23);
    L5:
        if (r32 != 35) goto L7;
        r33 = zznh.zze;
        goto L18
    L7:
        if (r32 != 842094169) goto L9;
        r33 = zznh.zzd;
        goto L18
    L9:
        if (r32 != 16) goto L11;
        r33 = zznh.zzb;
        goto L18
    L11:
        if (r32 == 17) goto L13;
        r33 = zznh.zza;
        goto L18
    L13:
        r33 = zznh.zzc;
        goto L18
    }

    public final /* synthetic */ zzta zzd(zzoa r3, long r4) {
        zzoc r0 = new zzoc();
        r0.zze(zznz.zzb);
        zzrd r1 = new zzrd();
        r1.zzd(this.zzd.zza());
        r1.zzc(r3);
        r1.zzb(Long.valueOf(SystemClock.elapsedRealtime() - r4));
        r0.zzi(r1.zze());
        return zzto.zzf(r0);
    }

    public final synchronized SubjectSegmentationResult zze(InputImage r16) {
        monitor-enter(this);
        Preconditions.checkNotNull(r16, "Input image can not be null");     // Catch: Throwable -> L11
        long r3 = SystemClock.elapsedRealtime();     // Catch: Throwable -> L11
        zzub r0 = (zzub) Preconditions.checkNotNull(this.zzi);     // Catch: Throwable -> L11
        zztz r7 = new zztz(r16.getFormat(), r16.getWidth(), r16.getHeight(), CommonConvertUtils.convertToMVRotation(r16.getRotationDegrees()), SystemClock.elapsedRealtime());     // Catch: Throwable -> L11
        zzuh r72 = r0.zzd(ImageUtils.getInstance().getImageDataWrapper(r16), r7);     // Catch: Throwable -> L11 RemoteException -> L13
        ArrayList r02 = new ArrayList();     // Catch: Throwable -> L11 RemoteException -> L13
        if (this.zzd.zze() == false) goto L15;
        Iterator r1 = r72.zzc().iterator();     // Catch: Throwable -> L11 RemoteException -> L13
    L9:
        if (r1.hasNext() == false) goto L15;
        zzuf r2 = (zzuf) r1.next();     // Catch: Throwable -> L11 RemoteException -> L13
        r02.add(new Subject(zzh(r2.zzf()), r2.zze(), r2.zzd(), r2.zza(), r2.zzb(), r2.zzc()));     // Catch: Throwable -> L11 RemoteException -> L13
    L15:
        zzg(zzoa.zza, r3, this.zzg, r16, r72);     // Catch: Throwable -> L11 RemoteException -> L13
        this.zzg = false;     // Catch: Throwable -> L11 RemoteException -> L13
        SubjectSegmentationResult r22 = new SubjectSegmentationResult(r02, zzh(r72.zzd()), r72.zza());     // Catch: Throwable -> L11 RemoteException -> L13
        monitor-exit(this);
        return r22;
    L13:
        e = move-exception;
        zzg(zzoa.zzD, r3, this.zzg, r16, null);     // Catch: Throwable -> L11
        throw new MlKitException("Failed to run thin subject segmenter.", 13, e);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    public final void zzf(zzoa r2, long r3) {
        zzi r0 = new zzi(this, r2, r3);
        zzob r22 = zzob.zzex;
        this.zze.zzf(r0, r22);
    }
}
