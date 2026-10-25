package com.google.android.gms.cast.framework.media.internal;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import androidx.mediarouter.media.MediaItemMetadata;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.ReconnectionService;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.NotificationAction;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.internal.cast.zzbx;
import com.google.android.gms.internal.cast.zzfg;
import com.google.android.gms.internal.cast.zzfk;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes4.dex */
public final class zzs {
    public static final /* synthetic */ int zza = 0;
    private static final Logger zzb = null;
    private final Context zzc;
    private final CastOptions zzd;
    private final zzbx zze;
    private final SessionManager zzf;
    private final NotificationOptions zzg;
    private final ComponentName zzh;
    private final ComponentName zzi;
    private final zzb zzj;
    private final zzb zzk;
    private final zzm zzl;
    private final Handler zzm;
    private final Runnable zzn;
    private final RemoteMediaClient.Callback zzo;
    private RemoteMediaClient zzp;
    private CastDevice zzq;
    private MediaSessionCompat zzr;
    private MediaSessionCompat.Callback zzs;
    private boolean zzt;
    private PlaybackStateCompat.CustomAction zzu;
    private PlaybackStateCompat.CustomAction zzv;
    private PlaybackStateCompat.CustomAction zzw;
    private PlaybackStateCompat.CustomAction zzx;

    static {
        zzb = new Logger("MediaSessionManager");
    }

    public zzs(Context r4, CastOptions r5, zzbx r6) {
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        CastContext r62 = CastContext.getSharedInstance();
        zzm r0 = null;
        if (r62 == null) goto L5;
        SessionManager r63 = r62.getSessionManager();
    L6:
        this.zzf = r63;
        CastMediaOptions r64 = r5.getCastMediaOptions();
        if (r64 != null) goto L9;
        NotificationOptions r1 = null;
    L10:
        this.zzg = r1;
        this.zzo = new zzr(this, null);
        if (r64 != null) goto L13;
        String r12 = null;
    L15:
        if (TextUtils.isEmpty(r12) == true) goto L17;
        ComponentName r2 = new ComponentName(r4, r12);
    L18:
        this.zzh = r2;
        if (r64 != null) goto L21;
        String r65 = null;
    L23:
        if (TextUtils.isEmpty(r65) == true) goto L25;
        ComponentName r13 = new ComponentName(r4, r65);
    L26:
        this.zzi = r13;
        zzb r66 = new zzb(r4);
        this.zzj = r66;
        r66.zza(new zzn(this));
        zzb r67 = new zzb(r4);
        this.zzk = r67;
        r67.zza(new zzo(this));
        this.zzm = new zzfk(Looper.getMainLooper());
        if (zzm.zzb(r5) == false) goto L29;
        r0 = new zzm(r4);
    L29:
        this.zzl = r0;
        this.zzn = new zzq(this);
        return;
    L25:
        r13 = null;
        goto L26
    L21:
        r65 = r64.getMediaIntentReceiverClassName();
        goto L23
    L17:
        r2 = null;
        goto L18
    L13:
        r12 = r64.getExpandedControllerActivityClassName();
        goto L15
    L9:
        r1 = r64.getNotificationOptions();
        goto L10
    L5:
        r63 = null;
        goto L6
    }

    public static /* synthetic */ Logger zzg() {
        return zzb;
    }

    private final void zzm(int r13, MediaInfo r14) {
        MediaSessionCompat r0 = this.zzr;
        if (r0 == null) goto L121;
        Bundle r1 = new Bundle();
        PlaybackStateCompat.Builder r2 = new PlaybackStateCompat.Builder();
        RemoteMediaClient r3 = this.zzp;
        long r5 = 0;
        if (r3 != null) goto L8;
    L50:
        PlaybackStateCompat r22 = r2.build();
    L51:
        r0.setPlaybackState(r22);
        NotificationOptions r23 = this.zzg;
        if (r23 == null) goto L57;
        if (r23.zzm() == false) goto L57;
        r1.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", true);
    L57:
        if (r23 == null) goto L62;
        if (r23.zzn() == false) goto L62;
        r1.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", true);
    L62:
        if (r1.containsKey("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS") == false) goto L64;
    L65:
        r0.setExtras(r1);
    L66:
        if (r13 != 0) goto L68;
        r0.setMetadata(new MediaMetadataCompat.Builder().build());
        return;
    L68:
        if (this.zzp == null) goto L76;
        ComponentName r132 = this.zzh;
        if (r132 != null) goto L72;
        PendingIntent r133 = null;
    L73:
        if (r133 == null) goto L76;
        r0.setSessionActivity(r133);
        goto L76
    L72:
        Intent r24 = new Intent();
        r24.setComponent(r132);
        r133 = zzfg.zza(this.zzc, 0, r24, 201326592);
    L76:
        if (this.zzp == null) goto L117;
        MediaSessionCompat r134 = this.zzr;
        if (r134 == null) goto L118;
        if (r14 == null) goto L119;
        MediaMetadata r02 = r14.getMetadata();
        if (r02 == null) goto L120;
        RemoteMediaClient r25 = this.zzp;
        if (r25 != null) goto L85;
    L87:
        r5 = r14.getStreamDuration();
    L88:
        String r142 = r02.getString(MediaMetadata.KEY_TITLE);
        String r26 = r02.getString(MediaMetadata.KEY_SUBTITLE);
        MediaMetadataCompat.Builder r32 = zzq().putLong(MediaItemMetadata.KEY_DURATION, r5);
        if (r142 == null) goto L91;
        r32.putString(MediaItemMetadata.KEY_TITLE, r142);
        r32.putString("android.media.metadata.DISPLAY_TITLE", r142);
    L91:
        if (r26 == null) goto L93;
        r32.putString("android.media.metadata.DISPLAY_SUBTITLE", r26);
    L93:
        r134.setMetadata(r32.build());
        Uri r135 = zzp(r02, 0);
        if (r135 == null) goto L96;
        this.zzj.zzb(r135);
    L97:
        Uri r143 = zzp(r02, 3);
        if (r143 == null) goto L101;
        this.zzk.zzb(r143);
        return;
    L101:
        zze(null, 3);
        return;
    L96:
        zze(null, 0);
        goto L97
    L85:
        if (r25.isLiveStream() == false) goto L87;
    L120:
        return;
    L119:
        return;
    L118:
        return;
    L117:
        return;
    L64:
        if (r1.containsKey("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT") == false) goto L66;
    L8:
        if (this.zzl == null) goto L50;
        if (r3.zzk() != 0) goto L13;
    L14:
        long r7 = 0;
    L16:
        r2.setState(r13, r7, 1.0f);
        if (r13 != 0) goto L19;
        r22 = r2.build();
        goto L51
    L19:
        NotificationOptions r33 = this.zzg;
        if (r33 == null) goto L22;
        com.google.android.gms.cast.framework.media.zzg r72 = r33.zzo();
    L23:
        RemoteMediaClient r8 = this.zzp;
        if (r8 != null) goto L26;
    L29:
        long r82 = 0;
    L31:
        if (r72 == null) goto L41;
        List r34 = zzt.zzb(r72);
        if (r34 == null) goto L49;
        Iterator r35 = r34.iterator();
    L36:
        if (r35.hasNext() == false) goto L49;
        NotificationAction r73 = (NotificationAction) r35.next();
        String r10 = r73.getAction();
        if (zzu(r10) == true) goto L39;
        zzo(r2, r10, r73);
        goto L36
    L39:
        r82 = r82 | zzn(r10, r13, r1);
    L49:
        r22 = r2.setActions(r82).build();
        goto L51
    L41:
        if (r33 == null) goto L49;
        Iterator<String> r36 = r33.getActions().iterator();
    L44:
        if (r36.hasNext() == false) goto L49;
        String r74 = r36.next();
        if (zzu(r74) == true) goto L47;
        zzo(r2, r74, null);
        goto L44
    L47:
        r82 = r82 | zzn(r74, r13, r1);
        goto L44
    L26:
        if (r8.isLiveStream() == true) goto L29;
        if (this.zzp.isPlayingAd() == true) goto L29;
        r82 = 256;
        goto L31
    L22:
        r72 = null;
        goto L23
    L13:
        if (r3.isLiveStream() == true) goto L14;
        r7 = r3.getApproximateStreamPosition();
        goto L16
    }

    private final long zzn(String r8, int r9, Bundle r10) {
        int r0 = r8.hashCode();
        if (r0 == (-945151566)) goto L31;
        if (r0 == (-945080078)) goto L21;
        if (r0 == 235550565) goto L10;
    L39:
        return 0;
    L10:
        if (r8.equals(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK) == false) goto L39;
        int r7 = 3;
        if (r9 != 3) goto L14;
        long r82 = 514;
    L16:
        if (r7 == 2) goto L18;
        return r82;
    L18:
        return 516;
    L14:
        r7 = r9;
        r82 = 512;
        goto L16
    L21:
        if (r8.equals(MediaIntentReceiver.ACTION_SKIP_PREV) == false) goto L39;
        RemoteMediaClient r72 = this.zzp;
        if (r72 != null) goto L25;
    L28:
        r10.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", true);
        return 0;
    L25:
        if (r72.zzl() == false) goto L28;
        return 16;
    L31:
        if (r8.equals(MediaIntentReceiver.ACTION_SKIP_NEXT) == false) goto L39;
        RemoteMediaClient r73 = this.zzp;
        if (r73 != null) goto L35;
    L38:
        r10.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", true);
        goto L39
    L35:
        if (r73.zzm() == false) goto L38;
        return 32;
    }

    private final void zzo(PlaybackStateCompat.Builder r4, String r5, NotificationAction r6) {
        switch(r5.hashCode()) {
            case -1699820260: goto L30;
            case -668151673: goto L22;
            case -124479363: goto L14;
            case 1362116196: goto L6;
            default: goto L37;
        };
    L37:
        if (r6 == null) goto L39;
        PlaybackStateCompat.CustomAction r3 = new PlaybackStateCompat.CustomAction.Builder(r5, r6.getContentDescription(), r6.getIconResId()).build();
    L40:
        if (r3 == null) goto L43;
        r4.addCustomAction(r3);
        return;
    L43:
        return;
    L39:
        r3 = null;
        goto L40
    L6:
        if (r5.equals(MediaIntentReceiver.ACTION_FORWARD) == false) goto L37;
        if (this.zzu != null) goto L12;
        NotificationOptions r52 = this.zzg;
        if (r52 == null) goto L12;
        long r1 = r52.getSkipStepMs();
        int r62 = zzt.zze(r52, r1);
        int r53 = zzt.zzd(r52, r1);
        this.zzu = new PlaybackStateCompat.CustomAction.Builder(MediaIntentReceiver.ACTION_FORWARD, this.zzc.getResources().getString(r62), r53).build();
    L12:
        r3 = this.zzu;
        goto L40
    L14:
        if (r5.equals(MediaIntentReceiver.ACTION_DISCONNECT) == false) goto L37;
        if (this.zzx != null) goto L20;
        NotificationOptions r54 = this.zzg;
        if (r54 == null) goto L20;
        this.zzx = new PlaybackStateCompat.CustomAction.Builder(MediaIntentReceiver.ACTION_DISCONNECT, this.zzc.getResources().getString(r54.zzl()), r54.getDisconnectDrawableResId()).build();
    L20:
        r3 = this.zzx;
        goto L40
    L22:
        if (r5.equals(MediaIntentReceiver.ACTION_STOP_CASTING) == false) goto L37;
        if (this.zzw != null) goto L28;
        NotificationOptions r55 = this.zzg;
        if (r55 == null) goto L28;
        this.zzw = new PlaybackStateCompat.CustomAction.Builder(MediaIntentReceiver.ACTION_STOP_CASTING, this.zzc.getResources().getString(r55.zzl()), r55.getDisconnectDrawableResId()).build();
    L28:
        r3 = this.zzw;
        goto L40
    L30:
        if (r5.equals(MediaIntentReceiver.ACTION_REWIND) == false) goto L37;
        if (this.zzv != null) goto L36;
        NotificationOptions r56 = this.zzg;
        if (r56 == null) goto L36;
        long r12 = r56.getSkipStepMs();
        int r63 = zzt.zzg(r56, r12);
        int r57 = zzt.zzf(r56, r12);
        this.zzv = new PlaybackStateCompat.CustomAction.Builder(MediaIntentReceiver.ACTION_REWIND, this.zzc.getResources().getString(r63), r57).build();
    L36:
        r3 = this.zzv;
        goto L40
    }

    private final Uri zzp(MediaMetadata r2, int r3) {
        CastMediaOptions r1 = this.zzd.getCastMediaOptions();
        if (r1 != null) goto L5;
        ImagePicker r12 = null;
    L6:
        if (r12 == null) goto L9;
        WebImage r13 = r12.onPickImage(r2, r3);
    L12:
        if (r13 != null) goto L15;
        return null;
    L15:
        return r13.getUrl();
    L9:
        if (r2.hasImages() == false) goto L11;
        r13 = r2.getImages().get(0);
        goto L12
    L11:
        r13 = null;
        goto L12
    L5:
        r12 = r1.getImagePicker();
        goto L6
    }

    private final MediaMetadataCompat.Builder zzq() {
        MediaSessionCompat r1 = this.zzr;
        if (r1 != null) goto L5;
        MediaMetadataCompat r12 = null;
    L6:
        if (r12 != null) goto L10;
        return new MediaMetadataCompat.Builder();
    L10:
        return new MediaMetadataCompat.Builder(r12);
    L5:
        r12 = r1.getController().getMetadata();
        goto L6
    }

    private final void zzr() {
        zzm r3 = this.zzl;
        if (r3 == null) goto L6;
        zzb.d("Stopping media notification.", new Object[0]);
        r3.zza();
        return;
    }

    @RequiresNonNull({"appContext", "handler", "options"})
    private final void zzs(boolean r4) {
        if (this.zzd.getEnableReconnectionService() == false) goto L17;
        Runnable r0 = this.zzn;
        if (r0 == null) goto L8;
        this.zzm.removeCallbacks(r0);
    L8:
        Context r02 = this.zzc;
        Intent r1 = new Intent(r02, ReconnectionService.class);
        r1.setPackage(r02.getPackageName());
        r02.startService(r1);     // Catch: IllegalStateException -> L11
        return;
    L11:
        if (r4 == false) goto L16;
        this.zzm.postDelayed(this.zzn, 1000);
        return;
    L16:
        return;
    }

    private final void zzt() {
        if (this.zzd.getEnableReconnectionService() == true) goto L5;
        return;
    L5:
        this.zzm.removeCallbacks(this.zzn);
        Context r2 = this.zzc;
        Intent r0 = new Intent(r2, ReconnectionService.class);
        r0.setPackage(r2.getPackageName());
        r2.stopService(r0);
    }

    private static final boolean zzu(String r1) {
        if (TextUtils.equals(r1, MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK) == false) goto L5;
        return true;
    L5:
        if (TextUtils.equals(r1, MediaIntentReceiver.ACTION_SKIP_PREV) == false) goto L7;
        return true;
    L7:
        if (TextUtils.equals(r1, MediaIntentReceiver.ACTION_SKIP_NEXT) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public final void zza(RemoteMediaClient r7, CastDevice r8) {
        CastOptions r0 = this.zzd;
        if (r0 != null) goto L5;
        CastMediaOptions r2 = null;
    L7:
        if (this.zzt == true) goto L27;
        if (r0 == null) goto L27;
        if (r2 == null) goto L27;
        if (this.zzg == null) goto L27;
        if (r7 == null) goto L27;
        if (r8 == null) goto L27;
        ComponentName r02 = this.zzi;
        if (r02 == null) goto L27;
        this.zzp = r7;
        r7.registerCallback(this.zzo);
        this.zzq = r8;
        Intent r72 = new Intent("android.intent.action.MEDIA_BUTTON");
        r72.setComponent(r02);
        Context r82 = this.zzc;
        PendingIntent r73 = zzfg.zzb(r82, 0, r72, 67108864);
        if (r2.getMediaSessionEnabled() == false) goto L25;
        MediaSessionCompat r22 = new MediaSessionCompat(r82, "CastMediaSession", r02, r73);
        this.zzr = r22;
        zzm(0, null);
        CastDevice r74 = this.zzq;
        if (r74 != null) goto L22;
    L24:
        zzp r75 = new zzp(this);
        this.zzs = r75;
        r22.setCallback(r75);
        r22.setActive(true);
        this.zze.zzv(r22);
        goto L25
    L22:
        if (TextUtils.isEmpty(r74.getFriendlyName()) == true) goto L24;
        r22.setMetadata(new MediaMetadataCompat.Builder().putString(MediaItemMetadata.KEY_ALBUM_ARTIST, r82.getResources().getString(R.string.cast_casting_to_device, new Object[]{this.zzq.getFriendlyName()})).build());
    L25:
        this.zzt = true;
        zzd(false);
        return;
    L27:
        zzb.d("skip attaching media session", new Object[0]);
        return;
    L5:
        r2 = r0.getCastMediaOptions();
        goto L7
    }

    public final void zzb(int r5) {
        if (this.zzt == false) goto L28;
        this.zzt = false;
        RemoteMediaClient r1 = this.zzp;
        if (r1 == null) goto L8;
        r1.unregisterCallback(this.zzo);
    L8:
        AudioManager r12 = (AudioManager) this.zzc.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (r12 == null) goto L11;
        r12.abandonAudioFocus(null);
    L11:
        this.zze.zzv(null);
        zzb r13 = this.zzj;
        if (r13 == null) goto L14;
        r13.zzc();
    L14:
        zzb r14 = this.zzk;
        if (r14 == null) goto L17;
        r14.zzc();
    L17:
        MediaSessionCompat r15 = this.zzr;
        if (r15 == null) goto L20;
        r15.setCallback(null);
        this.zzr.setMetadata(new MediaMetadataCompat.Builder().build());
        zzm(0, null);
    L20:
        MediaSessionCompat r16 = this.zzr;
        if (r16 == null) goto L23;
        r16.setActive(false);
        this.zzr.release();
        this.zzr = null;
    L23:
        this.zzp = null;
        this.zzq = null;
        this.zzs = null;
        zzr();
        if (r5 != 0) goto L27;
        zzt();
        return;
    L27:
        return;
    }

    public final void zzc(CastDevice r4) {
        zzb.i("update Cast device to %s", new Object[]{r4});
        this.zzq = r4;
        zzd(false);
    }

    public final void zzd(boolean r6) {
        RemoteMediaClient r0 = this.zzp;
        if (r0 == null) goto L26;
        int r1 = r0.zzk();
        MediaInfo r2 = r0.getMediaInfo();
        if (r0.isLoadingNextItem() == false) goto L12;
        MediaQueueItem r3 = r0.getLoadingItem();
        if (r3 == null) goto L12;
        if (r3.getMedia() == null) goto L12;
        r2 = r3.getMedia();
    L12:
        zzm(r1, r2);
        if (r0.hasMediaSession() == true) goto L16;
        zzr();
        zzt();
        return;
    L16:
        if (r1 == 0) goto L24;
        zzm r12 = this.zzl;
        if (r12 == null) goto L21;
        zzb.d("Update media notification.", new Object[0]);
        r12.zzc(this.zzq, this.zzp, this.zzr, r6);
    L21:
        if (r0.isLoadingNextItem() == true) goto L25;
        zzs(true);
        return;
    L25:
        return;
    L24:
        return;
    }

    public final void zze(Bitmap r4, int r5) {
        MediaSessionCompat r0 = this.zzr;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r4 != null) goto L7;
    L10:
        r4 = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
        r4.eraseColor(0);
    L11:
        MediaMetadataCompat.Builder r3 = zzq();
        if (r5 != 0) goto L14;
        String r52 = "android.media.metadata.DISPLAY_ICON";
    L15:
        r0.setMetadata(r3.putBitmap(r52, r4).build());
        return;
    L14:
        r52 = "android.media.metadata.ALBUM_ART";
        goto L15
    L7:
        if (r4.getWidth() <= 1) goto L10;
        if (r4.getHeight() > 1) goto L11;
        goto L10
    }

    public final /* synthetic */ void zzf() {
        zzs(false);
    }

    public final /* synthetic */ Context zzh() {
        return this.zzc;
    }

    public final /* synthetic */ SessionManager zzi() {
        return this.zzf;
    }

    public final /* synthetic */ NotificationOptions zzj() {
        return this.zzg;
    }

    public final /* synthetic */ ComponentName zzk() {
        return this.zzi;
    }

    public final /* synthetic */ RemoteMediaClient zzl() {
        return this.zzp;
    }
}
