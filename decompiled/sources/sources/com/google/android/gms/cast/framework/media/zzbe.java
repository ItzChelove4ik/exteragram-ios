package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzbe implements com.google.android.gms.cast.internal.zzao {
    final /* synthetic */ RemoteMediaClient zza;

    public /* synthetic */ zzbe(RemoteMediaClient r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    private final void zzn() {
        RemoteMediaClient r3 = this.zza;
        if (r3.zzw() == null) goto L10;
        MediaStatus r0 = r3.getMediaStatus();
        if (r0 == null) goto L11;
        RemoteMediaClient.ParseAdsInfoCallback r1 = r3.zzw();
        r0.getWriter().setIsPlayingAd(r1.parseIsPlayingAdFromMediaStatus(r0));
        List<AdBreakInfo> r02 = r3.zzw().parseAdBreaksFromMediaStatus(r0);
        MediaInfo r32 = r3.getMediaInfo();
        if (r32 == null) goto L12;
        r32.getWriter().setAdBreaks(r02);
        return;
    L12:
        return;
    L11:
        return;
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zza() {
        zzn();
        RemoteMediaClient r2 = this.zza;
        r2.zzp();
        Iterator r0 = r2.zzu().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((RemoteMediaClient.Listener) r0.next()).onStatusUpdated();
        goto L4
    L6:
        Iterator r22 = r2.zzv().iterator();
    L8:
        if (r22.hasNext() == false) goto L10;
        ((RemoteMediaClient.Callback) r22.next()).onStatusUpdated();
        goto L8
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzb() {
        zzn();
        RemoteMediaClient r2 = this.zza;
        Iterator r0 = r2.zzu().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((RemoteMediaClient.Listener) r0.next()).onMetadataUpdated();
        goto L4
    L6:
        Iterator r22 = r2.zzv().iterator();
    L8:
        if (r22.hasNext() == false) goto L10;
        ((RemoteMediaClient.Callback) r22.next()).onMetadataUpdated();
        goto L8
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzc() {
        RemoteMediaClient r2 = this.zza;
        Iterator r0 = r2.zzu().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((RemoteMediaClient.Listener) r0.next()).onQueueStatusUpdated();
        goto L4
    L6:
        Iterator r22 = r2.zzv().iterator();
    L8:
        if (r22.hasNext() == false) goto L10;
        ((RemoteMediaClient.Callback) r22.next()).onQueueStatusUpdated();
        goto L8
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzd() {
        RemoteMediaClient r2 = this.zza;
        Iterator r0 = r2.zzu().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((RemoteMediaClient.Listener) r0.next()).onPreloadStatusUpdated();
        goto L4
    L6:
        Iterator r22 = r2.zzv().iterator();
    L8:
        if (r22.hasNext() == false) goto L10;
        ((RemoteMediaClient.Callback) r22.next()).onPreloadStatusUpdated();
        goto L8
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zze() {
        RemoteMediaClient r2 = this.zza;
        Iterator r0 = r2.zzu().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((RemoteMediaClient.Listener) r0.next()).onAdBreakStatusUpdated();
        goto L4
    L6:
        Iterator r22 = r2.zzv().iterator();
    L8:
        if (r22.hasNext() == false) goto L10;
        ((RemoteMediaClient.Callback) r22.next()).onAdBreakStatusUpdated();
        goto L8
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzf(MediaError r2) {
        Iterator r1 = this.zza.zzv().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((RemoteMediaClient.Callback) r1.next()).onMediaError(r2);
        goto L4
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzg(int[] r2) {
        Iterator r1 = this.zza.zzv().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((RemoteMediaClient.Callback) r1.next()).zzb(r2);
        goto L4
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzh(int[] r2, int r3) {
        Iterator r1 = this.zza.zzv().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((RemoteMediaClient.Callback) r1.next()).zzc(r2, r3);
        goto L4
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzi(int[] r2) {
        Iterator r1 = this.zza.zzv().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((RemoteMediaClient.Callback) r1.next()).zzd(r2);
        goto L4
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzj(int[] r2) {
        Iterator r1 = this.zza.zzv().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((RemoteMediaClient.Callback) r1.next()).zze(r2);
        goto L4
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzk(MediaQueueItem[] r2) {
        Iterator r1 = this.zza.zzv().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((RemoteMediaClient.Callback) r1.next()).zzf(r2);
        goto L4
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzl(List r2, List r3, int r4) {
        Iterator r1 = this.zza.zzv().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((RemoteMediaClient.Callback) r1.next()).zzg(r2, r3, r4);
        goto L4
    }

    @Override // com.google.android.gms.cast.internal.zzao
    public final void zzm() {
        Iterator r1 = this.zza.zzv().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((RemoteMediaClient.Callback) r1.next()).zzh();
        goto L4
    }
}
