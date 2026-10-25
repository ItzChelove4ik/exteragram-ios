package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.cast.framework.media.widget.CastSeekBar;
import com.google.android.gms.cast.framework.media.widget.zze;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzct extends UIController implements RemoteMediaClient.ProgressListener {
    private final CastSeekBar zza;
    private final long zzb;
    private final com.google.android.gms.cast.framework.media.uicontroller.zza zzc;

    public zzct(CastSeekBar r1, long r2, com.google.android.gms.cast.framework.media.uicontroller.zza r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
        r1.setEnabled(false);
        r1.zza(null);
        r1.zzb = null;
        r1.postInvalidate();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final RemoteMediaClient getRemoteMediaClient() {
        return super.getRemoteMediaClient();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long r1, long r3) {
        zzc();
        zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r3) {
        super.onSessionConnected(r3);
        RemoteMediaClient r32 = super.getRemoteMediaClient();
        if (r32 == null) goto L5;
        r32.addProgressListener(this, this.zzb);
    L5:
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        RemoteMediaClient r0 = super.getRemoteMediaClient();
        if (r0 == null) goto L5;
        r0.removeProgressListener(this);
    L5:
        super.onSessionEnded();
        zza();
    }

    public final void zza() {
        zzc();
        RemoteMediaClient r0 = super.getRemoteMediaClient();
        ArrayList r1 = null;
        if (r0 != null) goto L5;
        MediaInfo r2 = null;
    L6:
        if (r0 != null) goto L8;
    L27:
        this.zza.zza(null);
    L28:
        zzb();
        return;
    L8:
        if (r0.hasMediaSession() == false) goto L27;
        if (r0.isLoadingNextItem() == true) goto L27;
        if (r2 == null) goto L27;
        CastSeekBar r02 = this.zza;
        List<AdBreakInfo> r22 = r2.getAdBreaks();
        if (r22 == null) goto L26;
        r1 = new ArrayList();
        Iterator<AdBreakInfo> r23 = r22.iterator();
    L17:
        if (r23.hasNext() == false) goto L26;
        AdBreakInfo r3 = r23.next();
        if (r3 == null) goto L17;
        long r4 = r3.getPlaybackPositionInMs();
        com.google.android.gms.cast.framework.media.uicontroller.zza r7 = this.zzc;
        if (r4 != (-1000)) goto L23;
        int r42 = r7.zzb();
    L24:
        if (r42 < 0) goto L17;
        r1.add(new com.google.android.gms.cast.framework.media.widget.zza(r42, (int) r3.getDurationInMs(), r3.isExpanded()));
        goto L17
    L23:
        r42 = Math.min((int) (r4 - r7.zzh()), r7.zzb());
    L26:
        r02.zza(r1);
        goto L28
    L5:
        r2 = r0.getMediaInfo();
        goto L6
    }

    public final void zzb() {
        RemoteMediaClient r0 = super.getRemoteMediaClient();
        AdBreakClipInfo r1 = null;
        if (r0 != null) goto L5;
    L22:
        CastSeekBar r4 = this.zza;
        r4.zzb = null;
        r4.postInvalidate();
        return;
    L5:
        if (r0.isPlayingAd() == false) goto L22;
        int r2 = (int) r0.getApproximateAdBreakClipPositionMs();
        MediaStatus r02 = r0.getMediaStatus();
        if (r02 == null) goto L10;
        r1 = r02.getCurrentAdBreakClip();
    L10:
        if (r1 == null) goto L12;
        int r03 = (int) r1.getDurationInMs();
    L13:
        if (r2 >= 0) goto L15;
        r2 = 0;
    L15:
        if (r03 >= 0) goto L17;
        r03 = 1;
    L17:
        CastSeekBar r42 = this.zza;
        if (r2 <= r03) goto L20;
        r03 = r2;
    L20:
        r42.zzb = new com.google.android.gms.cast.framework.media.widget.zzb(r2, r03);
        r42.postInvalidate();
        return;
    L12:
        r03 = r2;
        goto L13
    }

    public final void zzc() {
        RemoteMediaClient r0 = super.getRemoteMediaClient();
        boolean r1 = true;
        if (r0 != null) goto L5;
    L10:
        this.zza.setEnabled(false);
    L11:
        zze r02 = new zze();
        com.google.android.gms.cast.framework.media.uicontroller.zza r3 = this.zzc;
        r02.zza = r3.zzc();
        r02.zzb = r3.zzb();
        r02.zzc = (int) (-r3.zzh());
        RemoteMediaClient r4 = super.getRemoteMediaClient();
        if (r4 != null) goto L14;
    L19:
        int r42 = r3.zzc();
    L20:
        r02.zzd = r42;
        RemoteMediaClient r43 = super.getRemoteMediaClient();
        if (r43 != null) goto L23;
    L28:
        int r32 = r3.zzc();
    L29:
        r02.zze = r32;
        RemoteMediaClient r33 = super.getRemoteMediaClient();
        if (r33 != null) goto L32;
    L36:
        r1 = false;
    L37:
        r02.zzf = r1;
        this.zza.zzb(r02);
        return;
    L32:
        if (r33.hasMediaSession() == false) goto L36;
        if (r33.zzj() == false) goto L36;
    L23:
        if (r43.hasMediaSession() == false) goto L28;
        if (r43.zzj() == false) goto L28;
        r32 = r3.zzg();
        goto L29
    L14:
        if (r4.hasMediaSession() == false) goto L19;
        if (r4.zzj() == false) goto L19;
        r42 = r3.zzf();
        goto L20
    L5:
        if (r0.hasMediaSession() == false) goto L10;
        if (r0.isPlayingAd() == true) goto L10;
        this.zza.setEnabled(true);
        goto L11
    }
}
