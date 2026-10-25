package com.google.mlkit.vision.label.defaults.thin;

import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_vision_label.zzod;
import com.google.android.gms.internal.mlkit_vision_label.zzof;
import com.google.android.gms.internal.mlkit_vision_label.zzoh;
import com.google.android.gms.internal.mlkit_vision_label.zzoj;
import com.google.android.gms.internal.mlkit_vision_label.zzol;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
final class zza implements zzb {
    private final Context zza;
    private final ImageLabelerOptions zzb;
    private boolean zzc;
    private boolean zzd;
    private zzof zze;

    public zza(Context r1, ImageLabelerOptions r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.mlkit.vision.label.defaults.thin.zzb
    public final List zza(InputImage r11) {
        if (this.zze != null) goto L5;
        zzb();
    L5:
        zzof r0 = (zzof) Preconditions.checkNotNull(this.zze);
        if (this.zzc == false) goto L25;
    L12:
        zzod r3 = new zzod(r11.getFormat(), r11.getWidth(), r11.getHeight(), CommonConvertUtils.convertToMVRotation(r11.getRotationDegrees()), SystemClock.elapsedRealtime());
        List r10 = r0.zzd(ImageUtils.getInstance().getImageDataWrapper(r11), r3);     // Catch: RemoteException -> L18
        ArrayList r112 = new ArrayList();     // Catch: RemoteException -> L18
        Iterator r102 = r10.iterator();     // Catch: RemoteException -> L18
    L14:
        if (r102.hasNext() == false) goto L20;
        zzoj r02 = (zzoj) r102.next();     // Catch: RemoteException -> L18
        r112.add(new ImageLabel(r02.zzd(), r02.zza(), r02.zzb(), r02.zzc()));     // Catch: RemoteException -> L18
        goto L14
    L20:
        return r112;
    L18:
        e = move-exception;
        throw new MlKitException("Failed to run thin image labeler.", 13, e);
    L25:
        r0.zze();     // Catch: RemoteException -> L9
        this.zzc = true;     // Catch: RemoteException -> L9
    L9:
        e = move-exception;
        throw new MlKitException("Failed to init thin image labeler.", 13, e);
    }

    @Override // com.google.mlkit.vision.label.defaults.thin.zzb
    public final void zzb() {
        if (this.zze == null) goto L15;
        return;
    L15:
        DynamiteModule.VersionPolicy r0 = DynamiteModule.PREFER_REMOTE;     // Catch: RemoteException -> L7 DynamiteModule.LoadingException -> L10
        this.zze = zzoh.zza(DynamiteModule.load(this.zza, r0, "com.google.android.gms.vision.ica").instantiate("com.google.android.gms.vision.label.mlkit.ImageLabelerCreator")).zzd(ObjectWrapper.wrap(this.zza), new zzol(this.zzb.getConfidenceThreshold(), -1));     // Catch: RemoteException -> L7 DynamiteModule.LoadingException -> L10
        return;
    L7:
        e = move-exception;
        throw new MlKitException("Failed to create thin image labeler.", 13, e);
    L11:
        if (this.zzd == true) goto L14;
        OptionalModuleUtils.requestDownload(this.zza, "ica");
        this.zzd = true;
    L14:
        throw new MlKitException("Waiting for the label optional module to be downloaded. Please wait.", 14);
    }

    @Override // com.google.mlkit.vision.label.defaults.thin.zzb
    public final void zzc() {
        zzof r0 = this.zze;
        if (r0 == null) goto L11;
        r0.zzf();     // Catch: RemoteException -> L6
    L7:
        this.zze = null;
        this.zzc = false;
        return;
    L6:
        Log.e("DecoupledImageLabeler", "Failed to release thin image labeler.");
        goto L7
    }
}
