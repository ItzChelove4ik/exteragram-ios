package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.MediaUtils;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.common.images.WebImage;

/* loaded from: classes4.dex */
public final class zzda extends UIController {
    private final ImageView zza;
    private final ImageHints zzb;
    private final Bitmap zzc;
    private final View zzd;
    private final ImagePicker zze;
    private final zzcz zzf;
    private final com.google.android.gms.cast.framework.media.internal.zzb zzg;

    public zzda(ImageView r1, Context r2, ImageHints r3, int r4, View r5, zzcz r6) {
        this.zza = r1;
        this.zzb = r3;
        this.zzf = r6;
        ImagePicker r12 = null;
        if (r4 == 0) goto L5;
        Bitmap r32 = BitmapFactory.decodeResource(r2.getResources(), r4);
    L6:
        this.zzc = r32;
        this.zzd = r5;
        CastContext r33 = CastContext.zza(r2);
        if (r33 == null) goto L11;
        CastMediaOptions r34 = r33.getCastOptions().getCastMediaOptions();
        if (r34 == null) goto L11;
        r12 = r34.getImagePicker();
    L11:
        this.zze = r12;
        this.zzg = new com.google.android.gms.cast.framework.media.internal.zzb(r2.getApplicationContext());
        return;
    L5:
        r32 = null;
        goto L6
    }

    private final void zzd() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
    L24:
        zze();
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L24;
        MediaInfo r02 = r0.getMediaInfo();
        if (r02 != null) goto L10;
        Uri r03 = null;
    L19:
        if (r03 != null) goto L22;
        zze();
        return;
    L22:
        this.zzg.zzb(r03);
        return;
    L10:
        ImagePicker r1 = this.zze;
        MediaMetadata r2 = r02.getMetadata();
        if (r1 == null) goto L18;
        if (r2 == null) goto L18;
        WebImage r12 = r1.onPickImage(r2, this.zzb);
        if (r12 == null) goto L18;
        if (r12.getUrl() == null) goto L18;
        r03 = r12.getUrl();
    L18:
        r03 = MediaUtils.getImageUri(r02, 0);
        goto L19
    }

    private final void zze() {
        View r0 = this.zzd;
        if (r0 == null) goto L5;
        r0.setVisibility(0);
        this.zza.setVisibility(4);
    L5:
        Bitmap r02 = this.zzc;
        if (r02 == null) goto L9;
        this.zza.setImageBitmap(r02);
        return;
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzd();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r2) {
        super.onSessionConnected(r2);
        zzcy r22 = new zzcy(this);
        this.zzg.zza(r22);
        zze();
        zzd();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zzg.zzc();
        zze();
        super.onSessionEnded();
    }

    public final /* synthetic */ ImageView zza() {
        return this.zza;
    }

    public final /* synthetic */ View zzb() {
        return this.zzd;
    }

    public final /* synthetic */ zzcz zzc() {
        return this.zzf;
    }
}
