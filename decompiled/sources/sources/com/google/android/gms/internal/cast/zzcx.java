package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
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
public final class zzcx extends UIController {
    private final ImageView zza;
    private final ImageHints zzb;
    private final Bitmap zzc;
    private final ImagePicker zzd;
    private final com.google.android.gms.cast.framework.media.internal.zzb zze;

    public zzcx(ImageView r3, Context r4, ImageHints r5, int r6) {
        com.google.android.gms.cast.framework.media.internal.zzb r0 = new com.google.android.gms.cast.framework.media.internal.zzb(r4.getApplicationContext());
        this.zza = r3;
        this.zzb = r5;
        this.zzc = BitmapFactory.decodeResource(r4.getResources(), r6);
        CastContext r32 = CastContext.zza(r4);
        ImagePicker r42 = null;
        if (r32 == null) goto L7;
        CastMediaOptions r33 = r32.getCastOptions().getCastMediaOptions();
        if (r33 == null) goto L7;
        r42 = r33.getImagePicker();
    L7:
        this.zzd = r42;
        this.zze = r0;
    }

    private final void zzb() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
    L27:
        this.zza.setImageBitmap(this.zzc);
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L27;
        MediaQueueItem r02 = r0.getPreloadedItem();
        Uri r1 = null;
        if (r02 == null) goto L22;
        MediaInfo r03 = r02.getMedia();
        if (r03 == null) goto L22;
        ImagePicker r12 = this.zzd;
        MediaMetadata r2 = r03.getMetadata();
        if (r12 == null) goto L21;
        if (r2 == null) goto L21;
        WebImage r13 = r12.onPickImage(r2, this.zzb);
        if (r13 == null) goto L21;
        if (r13.getUrl() == null) goto L21;
        r1 = r13.getUrl();
    L21:
        r1 = MediaUtils.getImageUri(r03, 0);
    L22:
        if (r1 != null) goto L25;
        this.zza.setImageBitmap(this.zzc);
        return;
    L25:
        this.zze.zzb(r1);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r2) {
        super.onSessionConnected(r2);
        zzcw r22 = new zzcw(this);
        this.zze.zza(r22);
        this.zza.setImageBitmap(this.zzc);
        zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zze.zzc();
        this.zza.setImageBitmap(this.zzc);
        super.onSessionEnded();
    }

    public final /* synthetic */ ImageView zza() {
        return this.zza;
    }
}
