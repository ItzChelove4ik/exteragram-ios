package com.google.mlkit.vision.label.defaults.thin;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_vision_label.zzl;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.ImageConvertUtils;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
final class zzc implements zzb {
    private final Context zza;
    private final com.google.android.gms.internal.mlkit_vision_label.zzj zzb;
    private final String zzc;
    private boolean zzd;
    private com.google.android.gms.internal.mlkit_vision_label.zzd zze;

    public zzc(Context r4, ImageLabelerOptions r5) {
        this.zza = r4;
        Locale.getDefault().getLanguage().equals(Locale.ENGLISH.getLanguage());
        this.zzb = new com.google.android.gms.internal.mlkit_vision_label.zzj(1, -1, r5.getConfidenceThreshold(), 1);
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(r4) < 200400000) goto L5;
        String r42 = "com.google.android.gms.vision.ica";
    L6:
        this.zzc = r42;
        return;
    L5:
        r42 = "com.google.android.gms.vision.dynamite";
        goto L6
    }

    private final void zzd() {
        if (this.zzd == true) goto L6;
        Log.d("LegacyLabelDelegate", "Request ICA optional module download.");
        OptionalModuleUtils.requestDownload(this.zza, "ica");
        this.zzd = true;
        return;
    }

    @Override // com.google.mlkit.vision.label.defaults.thin.zzb
    public final List zza(InputImage r8) {
        if (this.zze != null) goto L6;
        zzb();
    L6:
        if (this.zze == null) goto L17;
        Bitmap r82 = ImageConvertUtils.getInstance().convertToUpRightBitmap(r8);
        com.google.android.gms.internal.mlkit_vision_label.zzh[] r7 = ((com.google.android.gms.internal.mlkit_vision_label.zzd) Preconditions.checkNotNull(this.zze)).zze(ObjectWrapper.wrap(r82), new zzl(-1));     // Catch: RemoteException -> L13
        ArrayList r83 = new ArrayList();     // Catch: RemoteException -> L13
        int r0 = r7.length;     // Catch: RemoteException -> L13
        int r1 = 0;
    L9:
        if (r1 >= r0) goto L12;
        com.google.android.gms.internal.mlkit_vision_label.zzh r2 = r7[r1];     // Catch: RemoteException -> L13
        r83.add(new ImageLabel(r2.zzb, r2.zzc, r2.zzd, r2.zza));     // Catch: RemoteException -> L13
        r1 = r1 + 1;
        goto L9
    L12:
        return r83;
    L13:
        e = move-exception;
        throw new MlKitException("Failed to run legacy image labeler.", 13, e);
    L17:
        throw new MlKitException("Waiting for the image labeling module to be downloaded. Please wait.", 14);
    }

    @Override // com.google.mlkit.vision.label.defaults.thin.zzb
    public final void zzb() {
        Log.d("LegacyLabelDelegate", "Try to load legacy label module.");
        if (this.zze == null) goto L23;
        return;
    L23:
        com.google.android.gms.internal.mlkit_vision_label.zzd r1 = com.google.android.gms.internal.mlkit_vision_label.zzf.zza(DynamiteModule.load(this.zza, DynamiteModule.PREFER_REMOTE, this.zzc).instantiate("com.google.android.gms.vision.label.ChimeraNativeImageLabelerCreator")).zzd(ObjectWrapper.wrap(this.zza), this.zzb);     // Catch: DynamiteModule.LoadingException -> L10 RemoteException -> L12
        this.zze = r1;     // Catch: DynamiteModule.LoadingException -> L10 RemoteException -> L12
        if (r1 != null) goto L14;
        zzd();     // Catch: DynamiteModule.LoadingException -> L10 RemoteException -> L12
        return;
    L14:
        return;
    L12:
        e = move-exception;
        throw new MlKitException("Failed to create legacy image labeler.", 13, e);
    L10:
        e = move-exception;
        if (this.zzc.equals("com.google.android.gms.vision.dynamite") == true) goto L20;
        zzd();
        return;
    L20:
        throw new MlKitException("Failed to load deprecated vision dynamite module.", 13, e);
    }

    @Override // com.google.mlkit.vision.label.defaults.thin.zzb
    public final void zzc() {
        com.google.android.gms.internal.mlkit_vision_label.zzd r0 = this.zze;
        if (r0 == null) goto L12;
        r0.zzd();     // Catch: RemoteException -> L6
    L8:
        this.zze = null;
        return;
    L6:
        e = move-exception;
        Log.e("LegacyLabelDelegate", "Failed to release legacy image labeler.", e);
        goto L8
    }
}
