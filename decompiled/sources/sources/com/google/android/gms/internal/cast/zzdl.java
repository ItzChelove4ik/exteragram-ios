package com.google.android.gms.internal.cast;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.SeekBar;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* loaded from: classes4.dex */
public final class zzdl extends UIController implements RemoteMediaClient.ProgressListener {
    private final SeekBar zza;
    private final long zzb;
    private final com.google.android.gms.cast.framework.media.uicontroller.zza zzc;
    private boolean zzd;
    private Boolean zze;
    private Drawable zzf;

    public zzdl(SeekBar r2, long r3, com.google.android.gms.cast.framework.media.uicontroller.zza r5) {
        this.zzd = true;
        this.zzf = null;
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r5;
        r2.setEnabled(false);
        this.zzf = r2.getThumb();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long r1, long r3) {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r3) {
        super.onSessionConnected(r3);
        RemoteMediaClient r32 = getRemoteMediaClient();
        if (r32 == null) goto L5;
        r32.addProgressListener(this, this.zzb);
    L5:
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 == null) goto L5;
        r0.removeProgressListener(this);
    L5:
        super.onSessionEnded();
        zza();
    }

    public final void zza() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
    L36:
        SeekBar r02 = this.zza;
        com.google.android.gms.cast.framework.media.uicontroller.zza r6 = this.zzc;
        r02.setMax(r6.zzb());
        r02.setProgress(r6.zzc());
        r02.setEnabled(false);
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L36;
        if (this.zzd == false) goto L41;
        SeekBar r2 = this.zza;
        com.google.android.gms.cast.framework.media.uicontroller.zza r3 = this.zzc;
        r2.setMax(r3.zzb());
        if (r0.isLiveStream() == true) goto L12;
    L14:
        r2.setProgress(r3.zzc());
    L16:
        if (r0.isPlayingAd() == false) goto L18;
        r2.setEnabled(false);
    L19:
        RemoteMediaClient r03 = getRemoteMediaClient();
        if (r03 != null) goto L22;
        return;
    L22:
        if (r03.hasMediaSession() == false) goto L39;
        Boolean r4 = this.zze;
        if (r4 != null) goto L26;
    L27:
        Boolean r04 = Boolean.valueOf(r03.zzj());
        this.zze = r04;
        if (r04.booleanValue() == false) goto L34;
        Drawable r62 = this.zzf;
        if (r62 == null) goto L32;
        r2.setThumb(r62);
    L32:
        r2.setClickable(true);
        r2.setOnTouchListener(null);
        return;
    L34:
        r2.setThumb(new ColorDrawable(0));
        r2.setClickable(false);
        r2.setOnTouchListener(new zzdk(this));
        return;
    L26:
        if (r4.booleanValue() != r03.zzj()) goto L27;
        return;
    L39:
        return;
    L18:
        r2.setEnabled(true);
        goto L19
    L12:
        if (r3.zze() == false) goto L14;
        r2.setProgress(r3.zzg());
        goto L16
    }

    public final void zzb(boolean r1) {
        this.zzd = r1;
    }
}
