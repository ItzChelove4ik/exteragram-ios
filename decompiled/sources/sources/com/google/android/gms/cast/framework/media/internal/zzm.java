package com.google.android.gms.cast.framework.media.internal;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.media.app.NotificationCompat$MediaStyle;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.NotificationAction;
import com.google.android.gms.cast.framework.media.NotificationActionsProvider;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzfg;
import com.google.android.gms.internal.cast.zzpm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class zzm {
    private static final Logger zza = null;
    private final Context zzb;
    private final NotificationManager zzc;
    private final CastContext zzd;
    private final NotificationOptions zze;
    private final ImagePicker zzf;
    private final ComponentName zzg;
    private final ComponentName zzh;
    private List zzi;
    private int[] zzj;
    private final long zzk;
    private final zzb zzl;
    private final ImageHints zzm;
    private final Resources zzn;
    private zzk zzo;
    private zzl zzp;
    private Notification zzq;
    private NotificationCompat.Action zzr;
    private NotificationCompat.Action zzs;
    private NotificationCompat.Action zzt;
    private NotificationCompat.Action zzu;
    private NotificationCompat.Action zzv;
    private NotificationCompat.Action zzw;
    private NotificationCompat.Action zzx;
    private NotificationCompat.Action zzy;

    static {
        zza = new Logger("MediaNotificationProxy");
    }

    public zzm(Context r7) {
        this.zzi = new ArrayList();
        this.zzb = r7;
        NotificationManager r0 = (NotificationManager) r7.getSystemService("notification");
        this.zzc = r0;
        CastContext r1 = (CastContext) Preconditions.checkNotNull(CastContext.getSharedInstance());
        this.zzd = r1;
        CastMediaOptions r12 = (CastMediaOptions) Preconditions.checkNotNull(((CastOptions) Preconditions.checkNotNull(r1.getCastOptions())).getCastMediaOptions());
        NotificationOptions r2 = (NotificationOptions) Preconditions.checkNotNull(r12.getNotificationOptions());
        this.zze = r2;
        this.zzf = r12.getImagePicker();
        Resources r3 = r7.getResources();
        this.zzn = r3;
        this.zzg = new ComponentName(r7.getApplicationContext(), r12.getMediaIntentReceiverClassName());
        if (TextUtils.isEmpty(r2.getTargetActivityClassName()) == true) goto L5;
        this.zzh = new ComponentName(r7.getApplicationContext(), r2.getTargetActivityClassName());
    L6:
        this.zzk = r2.getSkipStepMs();
        int r13 = r3.getDimensionPixelSize(r2.zza());
        ImageHints r22 = new ImageHints(1, r13, r13);
        this.zzm = r22;
        this.zzl = new zzb(r7.getApplicationContext(), r22);
        if (PlatformVersion.isAtLeastO() == false) goto L10;
        if (r0 == null) goto L10;
        NotificationChannel r6 = CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0.m("cast_media_notification", ((Context) Preconditions.checkNotNull(r7)).getResources().getString(R.string.media_notification_channel_name), 2);
        r6.setShowBadge(false);
        r0.createNotificationChannel(r6);
    L10:
        com.google.android.gms.internal.cast.zzr.zzb(zzpm.zzad);
        return;
    L5:
        this.zzh = null;
        goto L6
    }

    public static boolean zzb(CastOptions r7) {
        CastMediaOptions r72 = r7.getCastMediaOptions();
        if (r72 != null) goto L5;
        return false;
    L5:
        NotificationOptions r73 = r72.getNotificationOptions();
        if (r73 != null) goto L8;
        return false;
    L8:
        com.google.android.gms.cast.framework.media.zzg r74 = r73.zzo();
        if (r74 != null) goto L11;
        return true;
    L11:
        List r2 = zzt.zzb(r74);
        int[] r75 = zzt.zzc(r74);
        if (r2 != null) goto L14;
        int r3 = 0;
    L16:
        if (r2 != null) goto L18;
    L37:
        zza.e(NotificationActionsProvider.class.getSimpleName().concat(" doesn't provide any action."), new Object[0]);
    L38:
        return false;
    L18:
        if (r2.isEmpty() == true) goto L37;
        if (r2.size() <= 5) goto L23;
        zza.e(NotificationActionsProvider.class.getSimpleName().concat(" provides more than 5 actions."), new Object[0]);
        goto L38
    L23:
        if (r75 == null) goto L36;
        int r22 = r75.length;
        if (r22 == 0) goto L36;
        int r5 = 0;
    L28:
        if (r5 >= r22) goto L35;
        int r6 = r75[r5];
        if (r6 < 0) goto L34;
        if (r6 >= r3) goto L34;
        r5 = r5 + 1;
    L34:
        zza.e(NotificationActionsProvider.class.getSimpleName().concat("provides a compact view action whose index is out of bounds."), new Object[0]);
        goto L38
    L35:
        return true;
    L36:
        zza.e(NotificationActionsProvider.class.getSimpleName().concat(" doesn't provide any actions for compact view."), new Object[0]);
        goto L38
    L14:
        r3 = r2.size();
        goto L16
    }

    private final void zzf() {
        NotificationManager r0 = this.zzc;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.zzo == null) goto L84;
        zzl r1 = this.zzp;
        int[] r2 = null;
        if (r1 == null) goto L15;
        Bitmap r12 = r1.zzb;
        if (r12 == null) goto L15;
        if (r12.getWidth() <= 1) goto L15;
        if (r12.getHeight() <= 1) goto L15;
    L16:
        Context r4 = this.zzb;
        NotificationCompat.Builder r13 = new NotificationCompat.Builder(r4, "cast_media_notification").setLargeIcon(r12);
        NotificationOptions r5 = this.zze;
        NotificationCompat.Builder r14 = r13.setSmallIcon(r5.getSmallIconDrawableResId()).setContentTitle(this.zzo.zzd).setContentText(this.zzn.getString(r5.getCastingToDeviceStringResId(), new Object[]{this.zzo.zze})).setOngoing(true).setShowWhen(false).setVisibility(1);
        ComponentName r7 = this.zzh;
        if (r7 != null) goto L19;
        PendingIntent r72 = null;
    L20:
        if (r72 == null) goto L22;
        r14.setContentIntent(r72);
    L22:
        com.google.android.gms.cast.framework.media.zzg r73 = r5.zzo();
        if (r73 == null) goto L53;
        zza.d("actionsProvider != null", new Object[0]);
        int[] r52 = zzt.zzc(r73);
        if (r52 == null) goto L28;
        r2 = (int[]) r52.clone();
    L28:
        this.zzj = r2;
        List r22 = zzt.zzb(r73);
        this.zzi = new ArrayList();
        if (r22 == null) goto L60;
        Iterator r23 = r22.iterator();
    L33:
        if (r23.hasNext() == false) goto L60;
        NotificationAction r53 = (NotificationAction) r23.next();
        String r74 = r53.getAction();
        if (r74.equals(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK) == false) goto L37;
    L50:
        NotificationCompat.Action r54 = zzg(r53.getAction());
    L51:
        if (r54 == null) goto L33;
        this.zzi.add(r54);
        goto L33
    L37:
        if (r74.equals(MediaIntentReceiver.ACTION_SKIP_NEXT) == true) goto L50;
        if (r74.equals(MediaIntentReceiver.ACTION_SKIP_PREV) == true) goto L50;
        if (r74.equals(MediaIntentReceiver.ACTION_FORWARD) == true) goto L50;
        if (r74.equals(MediaIntentReceiver.ACTION_REWIND) == true) goto L50;
        if (r74.equals(MediaIntentReceiver.ACTION_STOP_CASTING) == true) goto L50;
        if (r74.equals(MediaIntentReceiver.ACTION_DISCONNECT) == true) goto L50;
        Intent r75 = new Intent(r53.getAction());
        r75.setComponent(this.zzg);
        r54 = new NotificationCompat.Action.Builder(r53.getIconResId(), r53.getContentDescription(), zzfg.zzb(r4, 0, r75, 67108864)).build();
    L60:
        Iterator r24 = this.zzi.iterator();
    L62:
        if (r24.hasNext() == false) goto L64;
        r14.addAction((NotificationCompat.Action) r24.next());
        goto L62
    L64:
        NotificationCompat$MediaStyle r25 = new NotificationCompat$MediaStyle();
        int[] r42 = this.zzj;
        if (r42 == null) goto L67;
        r25.setShowActionsInCompactView(r42);
    L67:
        MediaSessionCompat.Token r43 = this.zzo.zza;
        if (r43 == null) goto L70;
        r25.setMediaSession(r43);
    L70:
        r14.setStyle(r25);
        Notification r15 = r14.build();
        this.zzq = r15;
        r0.notify("castMediaNotification", 1, r15);
        return;
    L53:
        zza.d("actionsProvider == null", new Object[0]);
        this.zzi = new ArrayList();
        Iterator<String> r26 = r5.getActions().iterator();
    L55:
        if (r26.hasNext() == false) goto L59;
        NotificationCompat.Action r44 = zzg(r26.next());
        if (r44 == null) goto L55;
        this.zzi.add(r44);
        goto L55
    L59:
        this.zzj = (int[]) r5.getCompatActionIndices().clone();
        goto L60
    L19:
        Intent r8 = new Intent();
        r8.putExtra("targetActivity", r7);
        r8.setAction(r7.flattenToString());
        r8.setComponent(r7);
        TaskStackBuilder r76 = TaskStackBuilder.create(r4);
        r76.addNextIntentWithParentStack(r8);
        int r82 = zzfg.$r8$clinit;
        r72 = r76.getPendingIntent(1, 201326592);
    L15:
        r12 = null;
        goto L16
    }

    private final NotificationCompat.Action zzg(String r7) {
        PendingIntent r3 = null;
        switch(r7.hashCode()) {
            case -1699820260: goto L63;
            case -945151566: goto L54;
            case -945080078: goto L45;
            case -668151673: goto L38;
            case -124479363: goto L31;
            case 235550565: goto L13;
            case 1362116196: goto L6;
            default: goto L69;
        };
    L69:
        zza.e("Action: %s is not a pre-defined action.", new Object[]{r7});
        return null;
    L6:
        if (r7.equals(MediaIntentReceiver.ACTION_FORWARD) == false) goto L69;
        long r32 = this.zzk;
        if (this.zzv != null) goto L11;
        Intent r72 = new Intent(MediaIntentReceiver.ACTION_FORWARD);
        r72.setComponent(this.zzg);
        r72.putExtra(MediaIntentReceiver.EXTRA_SKIP_STEP_MS, r32);
        PendingIntent r73 = zzfg.zzb(this.zzb, 0, r72, 201326592);
        NotificationOptions r0 = this.zze;
        int r1 = zzt.zzd(r0, r32);
        int r02 = zzt.zze(r0, r32);
        this.zzv = new NotificationCompat.Action.Builder(r1, this.zzn.getString(r02), r73).build();
    L11:
        return this.zzv;
    L13:
        if (r7.equals(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK) == false) goto L69;
        zzk r74 = this.zzo;
        int r12 = r74.zzc;
        if (r74.zzb == false) goto L26;
        if (this.zzs != null) goto L24;
        NotificationOptions r75 = this.zze;
        if (r12 != 2) goto L21;
        int r13 = r75.getStopLiveStreamDrawableResId();
        int r76 = r75.getStopLiveStreamTitleResId();
    L22:
        Intent r2 = new Intent(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK);
        r2.setComponent(this.zzg);
        PendingIntent r03 = zzfg.zzb(this.zzb, 0, r2, 67108864);
        this.zzs = new NotificationCompat.Action.Builder(r13, this.zzn.getString(r76), r03).build();
        goto L24
    L21:
        r13 = r75.getPauseDrawableResId();
        r76 = r75.zzb();
    L24:
        return this.zzs;
    L26:
        if (this.zzr != null) goto L29;
        Intent r77 = new Intent(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK);
        r77.setComponent(this.zzg);
        PendingIntent r78 = zzfg.zzb(this.zzb, 0, r77, 67108864);
        NotificationOptions r04 = this.zze;
        Resources r14 = this.zzn;
        this.zzr = new NotificationCompat.Action.Builder(r04.getPlayDrawableResId(), r14.getString(r04.zzc()), r78).build();
    L29:
        return this.zzr;
    L31:
        if (r7.equals(MediaIntentReceiver.ACTION_DISCONNECT) == false) goto L69;
        if (this.zzx != null) goto L36;
        Intent r79 = new Intent(MediaIntentReceiver.ACTION_DISCONNECT);
        r79.setComponent(this.zzg);
        PendingIntent r710 = zzfg.zzb(this.zzb, 0, r79, 67108864);
        NotificationOptions r05 = this.zze;
        Resources r15 = this.zzn;
        this.zzx = new NotificationCompat.Action.Builder(r05.getDisconnectDrawableResId(), r15.getString(r05.zzl(), new Object[]{_UrlKt.FRAGMENT_ENCODE_SET}), r710).build();
    L36:
        return this.zzx;
    L38:
        if (r7.equals(MediaIntentReceiver.ACTION_STOP_CASTING) == false) goto L69;
        if (this.zzy != null) goto L43;
        Intent r711 = new Intent(MediaIntentReceiver.ACTION_STOP_CASTING);
        r711.setComponent(this.zzg);
        PendingIntent r712 = zzfg.zzb(this.zzb, 0, r711, 67108864);
        NotificationOptions r06 = this.zze;
        Resources r16 = this.zzn;
        this.zzy = new NotificationCompat.Action.Builder(r06.getDisconnectDrawableResId(), r16.getString(r06.zzl()), r712).build();
    L43:
        return this.zzy;
    L45:
        if (r7.equals(MediaIntentReceiver.ACTION_SKIP_PREV) == false) goto L69;
        boolean r713 = this.zzo.zzg;
        if (this.zzu != null) goto L52;
        if (r713 == false) goto L50;
        Intent r714 = new Intent(MediaIntentReceiver.ACTION_SKIP_PREV);
        r714.setComponent(this.zzg);
        r3 = zzfg.zzb(this.zzb, 0, r714, 67108864);
    L50:
        NotificationOptions r715 = this.zze;
        Resources r07 = this.zzn;
        this.zzu = new NotificationCompat.Action.Builder(r715.getSkipPrevDrawableResId(), r07.getString(r715.zze()), r3).build();
    L52:
        return this.zzu;
    L54:
        if (r7.equals(MediaIntentReceiver.ACTION_SKIP_NEXT) == false) goto L69;
        boolean r716 = this.zzo.zzf;
        if (this.zzt != null) goto L61;
        if (r716 == false) goto L59;
        Intent r717 = new Intent(MediaIntentReceiver.ACTION_SKIP_NEXT);
        r717.setComponent(this.zzg);
        r3 = zzfg.zzb(this.zzb, 0, r717, 67108864);
    L59:
        NotificationOptions r718 = this.zze;
        Resources r08 = this.zzn;
        this.zzt = new NotificationCompat.Action.Builder(r718.getSkipNextDrawableResId(), r08.getString(r718.zzd()), r3).build();
    L61:
        return this.zzt;
    L63:
        if (r7.equals(MediaIntentReceiver.ACTION_REWIND) == false) goto L69;
        long r33 = this.zzk;
        if (this.zzw != null) goto L68;
        Intent r719 = new Intent(MediaIntentReceiver.ACTION_REWIND);
        r719.setComponent(this.zzg);
        r719.putExtra(MediaIntentReceiver.EXTRA_SKIP_STEP_MS, r33);
        PendingIntent r720 = zzfg.zzb(this.zzb, 0, r719, 201326592);
        NotificationOptions r09 = this.zze;
        int r17 = zzt.zzf(r09, r33);
        int r010 = zzt.zzg(r09, r33);
        this.zzw = new NotificationCompat.Action.Builder(r17, this.zzn.getString(r010), r720).build();
    L68:
        return this.zzw;
    }

    public final void zza() {
        this.zzl.zzc();
        NotificationManager r2 = this.zzc;
        if (r2 == null) goto L6;
        r2.cancel("castMediaNotification", 1);
        return;
    }

    public final void zzc(CastDevice r18, RemoteMediaClient r19, MediaSessionCompat r20, boolean r21) {
        if (r18 == null) goto L66;
        if (r19 == null) goto L67;
        if (r20 == null) goto L71;
        MediaInfo r1 = r19.getMediaInfo();
        if (r1 == null) goto L68;
        MediaMetadata r2 = r1.getMetadata();
        if (r2 == null) goto L69;
        MediaStatus r3 = r19.getMediaStatus();
        if (r3 == null) goto L29;
        int r7 = r3.getQueueRepeatMode();
        if (r7 == 1) goto L31;
        if (r7 == 2) goto L31;
        if (r7 == 3) goto L31;
        Integer r72 = r3.getIndexById(r3.getCurrentItemId());
        if (r72 == null) goto L29;
        if (r72.intValue() <= 0) goto L23;
        boolean r8 = true;
    L25:
        if (r72.intValue() >= (r3.getQueueItemCount() - 1)) goto L28;
        boolean r15 = true;
    L27:
        boolean r16 = r8;
    L33:
        if (r19.getPlayerState() != 2) goto L35;
        boolean r10 = true;
    L36:
        zzk r9 = new zzk(r10, r1.getStreamType(), r2.getString(MediaMetadata.KEY_TITLE), r18.getFriendlyName(), r20.getSessionToken(), r15, r16);
        if (r21 == true) goto L52;
        zzk r12 = this.zzo;
        if (r12 == null) goto L52;
        if (r9.zzb != r12.zzb) goto L52;
        if (r9.zzc != r12.zzc) goto L52;
        if (CastUtils.zza(r9.zzd, r12.zzd) == false) goto L52;
        if (CastUtils.zza(r9.zze, r12.zze) == false) goto L52;
        if (r9.zzf != r12.zzf) goto L52;
        if (r9.zzg != r12.zzg) goto L52;
    L53:
        ImagePicker r13 = this.zzf;
        if (r13 == null) goto L57;
        WebImage r14 = r13.onPickImage(r2, this.zzm);
    L60:
        zzl r32 = new zzl(r14);
        zzl r17 = this.zzp;
        if (r17 != null) goto L63;
    L64:
        zzb r110 = this.zzl;
        r110.zza(new zzj(this, r32));
        r110.zzb(r32.zza);
        return;
    L63:
        if (CastUtils.zza(r32.zza, r17.zza) == false) goto L64;
        return;
    L57:
        if (r2.hasImages() == false) goto L59;
        r14 = r2.getImages().get(0);
        goto L60
    L59:
        r14 = null;
    L52:
        this.zzo = r9;
        zzf();
        goto L53
    L35:
        r10 = false;
        goto L36
    L28:
        r15 = false;
        goto L27
    L23:
        r8 = false;
    L31:
        r15 = true;
    L30:
        r16 = r15;
    L29:
        r15 = false;
        goto L30
    L69:
        return;
    L68:
        return;
    L71:
        return;
    L67:
        return;
    }

    public final /* synthetic */ void zzd() {
        zzf();
    }

    public final /* synthetic */ void zze(zzl r1) {
        this.zzp = r1;
    }
}
