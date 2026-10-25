package com.google.android.gms.cast.framework.media.uicontroller;

import android.text.format.DateUtils;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.text.DateFormat;
import java.util.Date;

/* loaded from: classes4.dex */
public final class zza {
    RemoteMediaClient zza;

    private zza() {
    }

    public static zza zza() {
        return new zza();
    }

    private final MediaMetadata zzo() {
        RemoteMediaClient r0 = this.zza;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.hasMediaSession() == false) goto L14;
        MediaInfo r1 = this.zza.getMediaInfo();
        if (r1 != null) goto L10;
        return null;
    L10:
        return r1.getMetadata();
    L14:
        return null;
    }

    private static final String zzp(long r3) {
        if (r3 < 0) goto L7;
        return DateUtils.formatElapsedTime(r3 / 1000);
    L7:
        return "-".concat(String.valueOf(DateUtils.formatElapsedTime((-r3) / 1000)));
    }

    public final int zzb() {
        RemoteMediaClient r0 = this.zza;
        long r1 = 1;
        if (r0 == null) goto L25;
        if (r0.hasMediaSession() == false) goto L25;
        RemoteMediaClient r02 = this.zza;
        if (r02.isLiveStream() == false) goto L10;
        Long r3 = zzj();
        if (r3 == null) goto L20;
        r1 = r3.longValue();
        goto L25
    L20:
        Long r32 = zzl();
        if (r32 == null) goto L23;
        r1 = r32.longValue();
        goto L25
    L23:
        r1 = Math.max(r02.getApproximateStreamPosition(), 1);
        goto L25
    L10:
        if (r02.isLoadingNextItem() == false) goto L16;
        MediaQueueItem r03 = r02.getLoadingItem();
        if (r03 == null) goto L25;
        MediaInfo r04 = r03.getMedia();
        if (r04 == null) goto L25;
        r1 = Math.max(r04.getStreamDuration(), 1);
        goto L25
    L16:
        r1 = Math.max(r02.getStreamDuration(), 1);
    L25:
        return Math.max((int) (r1 - zzh()), 1);
    }

    public final int zzc() {
        RemoteMediaClient r0 = this.zza;
        if (r0 != null) goto L5;
    L16:
        return 0;
    L5:
        if (r0.hasMediaSession() == false) goto L16;
        RemoteMediaClient r02 = this.zza;
        if (r02.isLiveStream() == false) goto L10;
    L11:
        long r2 = r02.getApproximateStreamPosition() - zzh();
        int r22 = (int) r2;
        if (r02.zzj() == false) goto L14;
        int r03 = zzf();
        int r3 = zzg();
        int r4 = CastUtils.zza;
        r22 = Math.min(Math.max(r22, r03), r3);
    L14:
        int r6 = zzb();
        int r04 = CastUtils.zza;
        return Math.min(Math.max(r22, 0), r6);
    L10:
        if (r02.isLoadingNextItem() == true) goto L16;
        goto L11
    }

    public final boolean zzd(long r7) {
        RemoteMediaClient r0 = this.zza;
        if (r0 != null) goto L5;
    L14:
        return false;
    L5:
        if (r0.hasMediaSession() == false) goto L14;
        if (this.zza.zzj() == true) goto L11;
        return false;
    L11:
        if (((zzg() + zzh()) - r7) >= NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L14;
        return true;
    }

    public final boolean zze() {
        return zzd(zzc() + zzh());
    }

    public final int zzf() {
        RemoteMediaClient r0 = this.zza;
        if (r0 != null) goto L5;
    L13:
        return 0;
    L5:
        if (r0.hasMediaSession() == false) goto L13;
        if (this.zza.isLiveStream() == false) goto L13;
        if (this.zza.zzj() == false) goto L13;
        long r2 = ((Long) Preconditions.checkNotNull(zzk())).longValue() - zzh();
        int r6 = zzb();
        int r02 = CastUtils.zza;
        return Math.min(Math.max((int) r2, 0), r6);
    }

    public final int zzg() {
        RemoteMediaClient r0 = this.zza;
        if (r0 == null) goto L15;
        if (r0.hasMediaSession() == false) goto L15;
        if (this.zza.isLiveStream() == false) goto L15;
        if (this.zza.zzj() == true) goto L12;
        return 0;
    L12:
        long r2 = ((Long) Preconditions.checkNotNull(zzl())).longValue() - zzh();
        int r6 = zzb();
        int r02 = CastUtils.zza;
        return Math.min(Math.max((int) r2, 0), r6);
    L15:
        return zzb();
    }

    public final long zzh() {
        RemoteMediaClient r0 = this.zza;
        if (r0 != null) goto L5;
        return 0;
    L5:
        if (r0.hasMediaSession() == true) goto L7;
        return 0;
    L7:
        if (this.zza.isLiveStream() == false) goto L22;
        RemoteMediaClient r02 = this.zza;
        Long r1 = zzi();
        if (r1 != null) goto L12;
        Long r2 = zzk();
        if (r2 == null) goto L18;
        return r2.longValue();
    L18:
        return r02.getApproximateStreamPosition();
    L12:
        return r1.longValue();
    L22:
        return 0;
    }

    public final Long zzi() {
        RemoteMediaClient r0 = this.zza;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.hasMediaSession() == true) goto L7;
        return null;
    L7:
        if (this.zza.isLiveStream() == false) goto L27;
        RemoteMediaClient r02 = this.zza;
        MediaInfo r1 = r02.getMediaInfo();
        MediaMetadata r3 = zzo();
        if (r1 == null) goto L23;
        if (r3 != null) goto L13;
        return null;
    L13:
        if (r3.containsKey(MediaMetadata.KEY_SECTION_START_TIME_IN_MEDIA) == true) goto L15;
        return null;
    L15:
        if (r3.containsKey(MediaMetadata.KEY_SECTION_DURATION) == true) goto L19;
        if (r02.zzj() == true) goto L19;
        return null;
    L19:
        return Long.valueOf(r3.getTimeMillis(MediaMetadata.KEY_SECTION_START_TIME_IN_MEDIA));
    L23:
        return null;
    L27:
        return null;
    }

    public final Long zzj() {
        RemoteMediaClient r0 = this.zza;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.hasMediaSession() == true) goto L7;
        return null;
    L7:
        if (this.zza.isLiveStream() == false) goto L23;
        MediaMetadata r02 = zzo();
        if (r02 != null) goto L12;
        return null;
    L12:
        if (r02.containsKey(MediaMetadata.KEY_SECTION_DURATION) == false) goto L21;
        Long r4 = zzi();
        if (r4 != null) goto L16;
        return null;
    L16:
        return Long.valueOf(r4.longValue() + r02.getTimeMillis(MediaMetadata.KEY_SECTION_DURATION));
    L21:
        return null;
    L23:
        return null;
    }

    public final Long zzk() {
        RemoteMediaClient r0 = this.zza;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.hasMediaSession() == true) goto L7;
        return null;
    L7:
        if (this.zza.isLiveStream() == true) goto L9;
        return null;
    L9:
        if (this.zza.zzj() == false) goto L23;
        RemoteMediaClient r2 = this.zza;
        MediaStatus r02 = r2.getMediaStatus();
        if (r02 != null) goto L14;
        return null;
    L14:
        if (r02.getLiveSeekableRange() != null) goto L16;
        return null;
    L16:
        return Long.valueOf(r2.getApproximateLiveSeekableRangeStart());
    L23:
        return null;
    }

    public final Long zzl() {
        RemoteMediaClient r0 = this.zza;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.hasMediaSession() == true) goto L7;
        return null;
    L7:
        if (this.zza.isLiveStream() == true) goto L9;
        return null;
    L9:
        if (this.zza.zzj() == false) goto L23;
        RemoteMediaClient r2 = this.zza;
        MediaStatus r02 = r2.getMediaStatus();
        if (r02 != null) goto L14;
        return null;
    L14:
        if (r02.getLiveSeekableRange() != null) goto L16;
        return null;
    L16:
        return Long.valueOf(r2.getApproximateLiveSeekableRangeEnd());
    L23:
        return null;
    }

    public final String zzm(long r4) {
        RemoteMediaClient r0 = this.zza;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.hasMediaSession() == false) goto L31;
        RemoteMediaClient r02 = this.zza;
        int r1 = 1;
        if (r02 == null) goto L18;
        if (r02.hasMediaSession() == false) goto L18;
        if (this.zza.isLiveStream() == false) goto L18;
        if (zzn() == null) goto L18;
        r1 = 2;
    L18:
        if ((r1 - 1) == 0) goto L22;
        return DateFormat.getTimeInstance().format(new Date(((Long) Preconditions.checkNotNull(zzn())).longValue() + r4));
    L22:
        if (r02.isLiveStream() == false) goto L28;
        if (zzi() != null) goto L28;
        return zzp(r4);
    L28:
        return zzp(r4 - zzh());
    L31:
        return null;
    }

    public final Long zzn() {
        RemoteMediaClient r0 = this.zza;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.hasMediaSession() == true) goto L7;
        return null;
    L7:
        if (this.zza.isLiveStream() == false) goto L20;
        MediaInfo r4 = this.zza.getMediaInfo();
        if (r4 != null) goto L12;
        return null;
    L12:
        if (r4.getStartAbsoluteTime() != (-1)) goto L14;
        return null;
    L14:
        return Long.valueOf(r4.getStartAbsoluteTime());
    L20:
        return null;
    }
}
