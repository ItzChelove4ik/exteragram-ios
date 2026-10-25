package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.mediarouter.media.MediaItemMetadata;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.utils.AppUtils;
import com.google.android.gms.cast.MediaError;
import java.io.File;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.audioinfo.AudioInfo;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AudioPlayerAlert;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.LaunchActivity;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes5.dex */
public class MusicPlayerService extends Service implements NotificationCenter.NotificationCenterDelegate {
    private static final int ID_NOTIFICATION = 5;
    public static final String NOTIFY_CLOSE = "org.telegram.android.musicplayer.close";
    public static final String NOTIFY_NEXT = "org.telegram.android.musicplayer.next";
    public static final String NOTIFY_PAUSE = "org.telegram.android.musicplayer.pause";
    public static final String NOTIFY_PLAY = "org.telegram.android.musicplayer.play";
    public static final String NOTIFY_PREVIOUS = "org.telegram.android.musicplayer.previous";
    public static final String NOTIFY_REPEAT = "org.telegram.android.musicplayer.repeat";
    public static final String NOTIFY_SEEK = "org.telegram.android.musicplayer.seek";
    public static final String NOTIFY_SHUFFLE = "org.telegram.android.musicplayer.shuffle";
    private static boolean supportBigNotifications = true;
    private static boolean supportLockScreenControls;
    private Bitmap albumArtPlaceholder;
    private AudioManager audioManager;
    private boolean foregroundServiceIsStarted;
    private BroadcastReceiver headsetPlugReceiver;
    private ImageReceiver imageReceiver;
    private String loadingFilePath;
    private MediaSessionCompat mediaSession;
    private int notificationMessageID;
    private PlaybackStateCompat.Builder playbackState;
    private RemoteControlClient remoteControlClient;

    /* renamed from: org.telegram.messenger.MusicPlayerService$1, reason: invalid class name */
    public class AnonymousClass1 extends BroadcastReceiver {
        final /* synthetic */ MusicPlayerService this$0;

        public AnonymousClass1(MusicPlayerService r1) {
            this.this$0 = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r1, Intent r2) {
            if ("android.media.AUDIO_BECOMING_NOISY".equals(r2.getAction()) == false) goto L6;
            MediaController.getInstance().pauseMessage(MediaController.getInstance().getPlayingMessageObject());
            return;
        }
    }

    /* renamed from: org.telegram.messenger.MusicPlayerService$2, reason: invalid class name */
    public class AnonymousClass2 extends MediaSessionCompat.Callback {
        final /* synthetic */ MusicPlayerService this$0;

        public AnonymousClass2(MusicPlayerService r1) {
            this.this$0 = r1;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onCustomAction(String r2, Bundle r3) {
            if (MusicPlayerService.NOTIFY_REPEAT.equals(r2) == false) goto L8;
            SharedConfig.setRepeatMode((SharedConfig.repeatMode + 1) % 3);
            MusicPlayerService.m4779$$Nest$mupdateRepeatMode(this.this$0);
            AudioPlayerAlert r22 = AudioPlayerAlert.instance;
            if (r22 == null) goto L16;
            r22.updateRepeatButton();
        L16:
            MessageObject r23 = MediaController.getInstance().getPlayingMessageObject();
            if (r23 == null) goto L20;
            MusicPlayerService.m4777$$Nest$mcreateNotification(this.this$0, r23, false);
            return;
        L20:
            return;
        L8:
            if (MusicPlayerService.NOTIFY_SHUFFLE.equals(r2) == false) goto L16;
            if (SharedConfig.shuffleMusic == false) goto L12;
            MediaController.getInstance().setPlaybackOrderType(0);
        L13:
            MusicPlayerService.m4780$$Nest$mupdateShuffleMode(this.this$0);
            AudioPlayerAlert r24 = AudioPlayerAlert.instance;
            if (r24 == null) goto L16;
            r24.updateRepeatButton();
            goto L16
        L12:
            MediaController.getInstance().setPlaybackOrderType(2);
            goto L13
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPause() {
            MediaController.getInstance().pauseMessage(MediaController.getInstance().getPlayingMessageObject());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPlay() {
            MediaController.getInstance().playMessage(MediaController.getInstance().getPlayingMessageObject());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSeekTo(long r6) {
            MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
            if (r0 == null) goto L6;
            MediaController.getInstance().seekToProgress(r0, (r6 / 1000) / ((float) r0.getDuration()));
            MusicPlayerService.m4778$$Nest$mupdatePlaybackState(this.this$0, r6);
            return;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSetRepeatMode(int r4) {
            int r1 = 2;
            if (r4 == 1) goto L9;
            if (r4 != 2) goto L6;
        L8:
            r1 = 1;
            goto L9
        L6:
            if (r4 == 3) goto L8;
            r1 = 0;
        L9:
            SharedConfig.setRepeatMode(r1);
            MusicPlayerService.m4779$$Nest$mupdateRepeatMode(this.this$0);
            MessageObject r42 = MediaController.getInstance().getPlayingMessageObject();
            if (r42 == null) goto L13;
            MusicPlayerService.m4777$$Nest$mcreateNotification(this.this$0, r42, false);
            return;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSetShuffleMode(int r4) {
            if (r4 == 1) goto L10;
            if (r4 == 2) goto L10;
            if (SharedConfig.shuffleMusic == false) goto L12;
            MediaController.getInstance().setPlaybackOrderType(0);
        L12:
            MusicPlayerService.m4780$$Nest$mupdateShuffleMode(this.this$0);
            MessageObject r42 = MediaController.getInstance().getPlayingMessageObject();
            if (r42 == null) goto L16;
            MusicPlayerService.m4777$$Nest$mcreateNotification(this.this$0, r42, false);
            return;
        L16:
            return;
        L10:
            if (SharedConfig.shuffleMusic == true) goto L12;
            MediaController.getInstance().setPlaybackOrderType(2);
            goto L12
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSkipToNext() {
            MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isMusic() == false) goto L9;
            MediaController.getInstance().playNextMessage();
            return;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSkipToPrevious() {
            MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isMusic() == false) goto L9;
            MediaController.getInstance().playPreviousMessage();
            return;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onStop() {
        }
    }

    /* renamed from: org.telegram.messenger.MusicPlayerService$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ MusicPlayerService this$0;

        public AnonymousClass3(MusicPlayerService r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MusicPlayerService.m4776$$Nest$fgetremoteControlClient(this.this$0) != null) goto L5;
            return;
        L5:
            if (MediaController.getInstance().getPlayingMessageObject() != null) goto L8;
            return;
        L8:
            if (MediaController.getInstance().getPlayingMessageObject().audioPlayerDuration != (-9223372036854775807L)) goto L11;
            AndroidUtilities.runOnUIThread(this, 500);
            return;
        L11:
            RemoteControlClient.MetadataEditor r0 = MusicPlayerService.m4776$$Nest$fgetremoteControlClient(this.this$0).editMetadata(false);
            r0.putLong(9, MediaController.getInstance().getPlayingMessageObject().audioPlayerDuration * 1000);
            r0.apply();
            RemoteControlClient r6 = MusicPlayerService.m4776$$Nest$fgetremoteControlClient(this.this$0);
            if (MediaController.getInstance().isMessagePaused() == false) goto L14;
            int r02 = 2;
        L15:
            long r1 = Math.max(MediaController.getInstance().getPlayingMessageObject().audioProgressSec * 1000, 100);
            if (MediaController.getInstance().isMessagePaused() == false) goto L18;
            float r3 = 0.0f;
        L19:
            r6.setPlaybackState(r02, r1, r3);
            return;
        L18:
            r3 = 1.0f;
            goto L19
        L14:
            r02 = 3;
            goto L15
        }
    }

    /* renamed from: $r8$lambda$c-Q65OeUTHkBopIdGe5BCaGuNNY, reason: not valid java name */
    public static /* synthetic */ void m4775$r8$lambda$cQ65OeUTHkBopIdGe5BCaGuNNY(MusicPlayerService r0, ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        r0.lambda$onCreate$0(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetremoteControlClient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RemoteControlClient m4776$$Nest$fgetremoteControlClient(MusicPlayerService r0) {
        return r0.remoteControlClient;
    }

    /* renamed from: -$$Nest$mcreateNotification, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4777$$Nest$mcreateNotification(MusicPlayerService r0, MessageObject r1, boolean r2) {
        r0.createNotification(r1, r2);
    }

    /* renamed from: -$$Nest$mupdatePlaybackState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4778$$Nest$mupdatePlaybackState(MusicPlayerService r0, long r1) {
        r0.updatePlaybackState(r1);
    }

    /* renamed from: -$$Nest$mupdateRepeatMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4779$$Nest$mupdateRepeatMode(MusicPlayerService r0) {
        r0.updateRepeatMode();
    }

    /* renamed from: -$$Nest$mupdateShuffleMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4780$$Nest$mupdateShuffleMode(MusicPlayerService r0) {
        r0.updateShuffleMode();
    }

    static {
        supportLockScreenControls = !TextUtils.isEmpty(AndroidUtilities.getSystemProperty("ro.miui.ui.version.code"));
    }

    public MusicPlayerService() {
        this.headsetPlugReceiver = new AnonymousClass1(this);
    }

    @SuppressLint({"NewApi"})
    private void createNotification(MessageObject r31, boolean r32) {
        String r2 = r31.getMusicTitle();
        String r3 = r31.getMusicAuthor();
        AudioInfo r4 = MediaController.getInstance().getAudioInfo();
        Intent r5 = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);
        if (r31.isMusic() == false) goto L6;
        r5.setAction("com.tmessages.openplayer");
        r5.addCategory("android.intent.category.LAUNCHER");
    L25:
        PendingIntent r52 = PendingIntent.getActivity(ApplicationLoader.applicationContext, 0, r5, fixIntentFlags(33554432));
        long r11 = (long) (r31.getDuration() * 1000.0d);
        if (r31.isMusic() == false) goto L45;
        String r6 = r31.getArtworkUrl(true);
        String r14 = r31.getArtworkUrl(false);
        if (r4 == null) goto L30;
        Bitmap r15 = r4.getSmallCover();
    L31:
        if (r4 == null) goto L33;
        Bitmap r16 = r4.getCover();
    L34:
        this.loadingFilePath = null;
        this.imageReceiver.setImageBitmap(null);
        if (r15 == null) goto L37;
    L42:
        this.loadingFilePath = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r31.getDocument()).getAbsolutePath();
    L43:
        Bitmap r62 = r16;
    L69:
        boolean r7 = MediaController.getInstance().isMessagePaused();
        boolean r8 = !r7;
        PendingIntent r42 = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(NOTIFY_PREVIOUS).setComponent(new ComponentName(this, MusicPlayerReceiver.class)), fixIntentFlags(301989888));
        PendingIntent r72 = PendingIntent.getService(getApplicationContext(), 0, new Intent(this, getClass()).setAction(getPackageName() + ".STOP_PLAYER"), fixIntentFlags(301989888));
        Context r9 = getApplicationContext();
        if (r7 == true) goto L72;
        String r142 = NOTIFY_PAUSE;
    L73:
        Bitmap r322 = r62;
        PendingIntent r63 = PendingIntent.getBroadcast(r9, 0, new Intent(r142).setComponent(new ComponentName(this, MusicPlayerReceiver.class)), fixIntentFlags(301989888));
        PendingIntent r92 = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(NOTIFY_NEXT).setComponent(new ComponentName(this, MusicPlayerReceiver.class)), fixIntentFlags(301989888));
        PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(NOTIFY_SEEK).setComponent(new ComponentName(this, MusicPlayerReceiver.class)), fixIntentFlags(301989888));
        PendingIntent r64 = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(NOTIFY_REPEAT).setComponent(new ComponentName(this, MusicPlayerReceiver.class)), fixIntentFlags(301989888));
        PendingIntent r65 = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(NOTIFY_SHUFFLE).setComponent(new ComponentName(this, MusicPlayerReceiver.class)), fixIntentFlags(301989888));
        Notification.MediaStyle r10 = new Notification.MediaStyle().setMediaSession((MediaSession.Token) this.mediaSession.getSessionToken().getToken());
        if (r31.isMusic() == false) goto L76;
        PendingIntent r24 = r65;
        String r22 = NOTIFY_REPEAT;
        String r25 = NOTIFY_SHUFFLE;
        r10.setShowActionsInCompactView(new int[]{0, 1, 2, 3, 4});
    L81:
        Notification.Builder r112 = new Notification.Builder(this);
        Notification.Builder r13 = r112.setSmallIcon(IconManager.getNotificationSystemIcon()).setOngoing(r8).setContentTitle(r2).setContentText(r3);
        if (r4 != null) goto L84;
    L86:
        String r143 = null;
    L87:
        r13.setSubText(r143).setContentIntent(r52).setDeleteIntent(r72).setShowWhen(false).setCategory("transport").setPriority(2).setStyle(r10);
        int r53 = Build.VERSION.SDK_INT;
        if (r53 >= 31) goto L91;
        r112.setColor(AppUtils.getNotificationColor());
    L91:
        if (r53 < 26) goto L93;
        NotificationsController.checkOtherNotificationsChannel();
        r112.setChannelId(NotificationsController.OTHER_NOTIFICATIONS_CHANNEL);
    L93:
        if (r15 == null) goto L95;
        r112.setLargeIcon(r15);
    L96:
        String r102 = LocaleController.getString(R.string.Next);
        String r132 = LocaleController.getString(R.string.AccDescrPrevious);
        this.playbackState = new PlaybackStateCompat.Builder();
        if (MediaController.getInstance().isDownloadingCurrentMessage() == false) goto L105;
        long r26 = 1000;
        this.playbackState.setState(6, 0, 1.0f).setActions(0);
        if (r31.isMusic() == false) goto L101;
        r112.addAction(new Notification.Action.Builder(R.drawable.ic_action_previous, r132, r42).build());
    L101:
        r112.addAction(new Notification.Action.Builder(R.drawable.loading_animation2, LocaleController.getString(R.string.Loading), null).build());
        if (r31.isMusic() == false) goto L104;
        r112.addAction(new Notification.Action.Builder(R.drawable.ic_action_next, r102, r92).build());
    L104:
        String r28 = r2;
        int r29 = r53;
    L158:
        PlaybackStateCompat r23 = this.playbackState.build();
        this.mediaSession.setPlaybackState(r23);
        TelegramMediaSession r43 = TelegramMediaSession.peekInstance();
        if (r43 == null) goto L161;
        r43.publishPlaybackState(r23);
    L161:
        updateRepeatMode();
        updateShuffleMode();
        String r66 = r28;
        MediaMetadataCompat.Builder r27 = new MediaMetadataCompat.Builder().putString(MediaItemMetadata.KEY_ALBUM_ARTIST, r3).putString(MediaItemMetadata.KEY_ARTIST, r3).putLong(MediaItemMetadata.KEY_DURATION, r11).putString(MediaItemMetadata.KEY_TITLE, r66);
        if (r4 != null) goto L164;
    L166:
        String r93 = null;
    L167:
        MediaMetadataCompat.Builder r0 = r27.putString("android.media.metadata.ALBUM", r93);
        if (r322 != null) goto L170;
    L172:
        Bitmap r94 = r322;
    L173:
        MediaMetadataCompat r02 = r0.build();
        this.mediaSession.setMetadata(r02);
        if (r43 == null) goto L176;
        r43.publishMetadata(r02);
    L176:
        r112.setVisibility(1);
        Notification r03 = r112.build();
        if (r29 >= 31) goto L179;
        if (r7 == true) goto L184;
        startForeground(5, r03);
    L186:
        if (this.remoteControlClient == null) goto L218;
        int r04 = MediaController.getInstance().getPlayingMessageObject().getId();
        if (this.notificationMessageID == r04) goto L202;
        this.notificationMessageID = r04;
        RemoteControlClient.MetadataEditor r210 = this.remoteControlClient.editMetadata(true);
        int r54 = 2;
        r210.putString(2, r3);
        r210.putString(7, r66);
        if (r4 != null) goto L192;
    L194:
        r210.putLong(9, MediaController.getInstance().getPlayingMessageObject().audioPlayerDuration * r26);
        if (r94 != null) goto L216;
    L201:
        r210.apply();
        AndroidUtilities.runOnUIThread(new AnonymousClass3(this), r26);
    L203:
        boolean r05 = MediaController.getInstance().isDownloadingCurrentMessage();
        RemoteControlClient r211 = this.remoteControlClient;
        if (r05 == false) goto L206;
        r211.setPlaybackState(8);
        return;
    L206:
        RemoteControlClient.MetadataEditor r06 = r211.editMetadata(false);
        r06.putLong(9, MediaController.getInstance().getPlayingMessageObject().audioPlayerDuration * 1000);
        r06.apply();
        RemoteControlClient r07 = this.remoteControlClient;
        if (MediaController.getInstance().isMessagePaused() == false) goto L209;
        int r12 = r54;
    L210:
        long r1 = Math.max(MediaController.getInstance().getPlayingMessageObject().audioProgressSec * 1000, 100);
        if (MediaController.getInstance().isMessagePaused() == false) goto L213;
        float r152 = 0.0f;
    L214:
        r07.setPlaybackState(r12, r1, r152);
        return;
    L213:
        r152 = 1.0f;
        goto L214
    L209:
        r12 = 3;
        goto L210
    L216:
        r210.putBitmap(100, r94);     // Catch: Throwable -> L199
    L199:
        th = move-exception;
        FileLog.e(th);
        goto L201
    L192:
        if (TextUtils.isEmpty(r4.getAlbum()) == true) goto L194;
        r210.putString(1, r4.getAlbum());
        goto L194
    L202:
        r54 = 2;
        goto L203
    L218:
        return;
    L184:
        stopForeground(false);
        ((NotificationManager) getSystemService("notification")).notify(5, r03);
        goto L186
    L179:
        if (this.foregroundServiceIsStarted == true) goto L181;
        this.foregroundServiceIsStarted = true;
        startForeground(5, r03);
        goto L186
    L181:
        ((NotificationManager) getSystemService("notification")).notify(5, r03);
        goto L186
    L170:
        if (r322.isRecycled() == true) goto L172;
        r94 = r322;
        r0.putBitmap("android.media.metadata.ALBUM_ART", r94);
        goto L173
    L164:
        if (r31.isMusic() == false) goto L166;
        r93 = r4.getAlbum();
        goto L167
    L105:
        r26 = 1000;
        if (r31.isMusic() == false) goto L108;
        long r67 = 2360118;
    L110:
        if (r31.isMusic() == true) goto L112;
        r28 = r2;
        r29 = r53;
    L118:
        PlaybackStateCompat.Builder r212 = this.playbackState;
        if (r7 == true) goto L121;
        int r55 = 3;
    L122:
        r212.setState(r55, MediaController.getInstance().getPlayingMessageObject().audioProgressSec * 1000, getPlaybackSpeed(r8, r31)).setActions(r67);
        if (r31.isMusic() == false) goto L132;
        int r213 = SharedConfig.repeatMode;
        if (r213 != 1) goto L127;
        int r214 = R.drawable.player_new_repeatall;
    L131:
        this.playbackState.addCustomAction(new PlaybackStateCompat.CustomAction.Builder(r22, LocaleController.getString(R.string.RepeatSong), r214).build());
        goto L132
    L127:
        if (r213 == 2) goto L129;
        r214 = R.drawable.player_new_repeat_off;
        goto L131
    L129:
        r214 = R.drawable.player_new_repeatone;
    L132:
        if (r7 == true) goto L135;
        int r215 = R.string.AccActionPause;
    L134:
        String r216 = LocaleController.getString(r215);
        if (r31.isMusic() == false) goto L144;
        if (SharedConfig.shuffleMusic == false) goto L141;
        int r56 = R.drawable.player_new_shuffle;
    L142:
        r112.addAction(new Notification.Action.Builder(r56, LocaleController.getString(R.string.ShuffleList), r24).build());
        r112.addAction(new Notification.Action.Builder(R.drawable.ic_action_previous, r132, r42).build());
        goto L144
    L141:
        r56 = R.drawable.player_new_shuffle_off;
    L144:
        if (r7 == true) goto L147;
        int r57 = R.drawable.ic_action_pause;
    L148:
        r112.addAction(new Notification.Action.Builder(r57, r216, r63).build());
        if (r31.isMusic() == false) goto L158;
        r112.addAction(new Notification.Action.Builder(R.drawable.ic_action_next, r102, r92).build());
        int r217 = SharedConfig.repeatMode;
        if (r217 != 1) goto L153;
        int r218 = R.drawable.player_new_repeatall;
    L157:
        r112.addAction(new Notification.Action.Builder(r218, LocaleController.getString(R.string.RepeatSong), r64).build());
        goto L158
    L153:
        if (r217 == 2) goto L155;
        r218 = R.drawable.player_new_repeat_off;
        goto L157
    L155:
        r218 = R.drawable.player_new_repeatone;
        goto L157
    L147:
        r57 = R.drawable.ic_action_play;
        goto L148
    L135:
        r215 = R.string.AccActionPlay;
        goto L134
    L121:
        r55 = 2;
        goto L122
    L112:
        if (SharedConfig.shuffleMusic == false) goto L115;
        int r162 = R.drawable.player_new_shuffle;
    L114:
        int r144 = r162;
        r29 = r53;
        r28 = r2;
        this.playbackState.addCustomAction(new PlaybackStateCompat.CustomAction.Builder(r25, LocaleController.getString(R.string.ShuffleList), r144).build());
        goto L118
    L115:
        r162 = R.drawable.player_new_shuffle_off;
        goto L114
    L108:
        r67 = 2360070;
        goto L110
    L95:
        r112.setLargeIcon(this.albumArtPlaceholder);
        goto L96
    L84:
        if (r31.isMusic() == false) goto L86;
        r143 = r4.getAlbum();
        goto L87
    L76:
        r24 = r65;
        r22 = NOTIFY_REPEAT;
        r25 = NOTIFY_SHUFFLE;
        if (r31.isVoice() == false) goto L79;
    L80:
        r10.setShowActionsInCompactView(new int[]{0});
        goto L81
    L79:
        if (r31.isRoundVideo() == false) goto L81;
    L72:
        r142 = NOTIFY_PLAY;
        goto L73
    L37:
        if (TextUtils.isEmpty(r6) == true) goto L42;
        boolean r73 = !r32;
        r16 = loadArtworkFromUrl(r14, true, r73);
        if (r16 != null) goto L41;
        r15 = loadArtworkFromUrl(r6, false, r73);
        r16 = r15;
        goto L43
    L41:
        r15 = loadArtworkFromUrl(r14, false, r73);
        goto L43
    L33:
        r16 = null;
        goto L34
    L30:
        r15 = null;
        goto L31
    L45:
        if (r31.isVoice() == false) goto L47;
    L50:
        long r68 = r31.getSenderId();
        if (r31.isFromUser() == false) goto L57;
        TLRPC.User r33 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r68));
        if (r33 == null) goto L55;
        r2 = UserObject.getUserName(r33);
        boolean r69 = !r32;
        Bitmap r74 = getAvatarBitmap(r33, true, r69);
        Bitmap r34 = getAvatarBitmap(r33, false, r69);
    L56:
        r15 = r34;
    L61:
        if (r74 != null) goto L64;
        if (r15 == null) goto L64;
        r16 = r15;
    L66:
        if (r31.isVoice() == false) goto L68;
        r3 = LocaleController.getString(R.string.AttachAudio);
        goto L43
    L68:
        r3 = LocaleController.getString(R.string.AttachRound);
    L64:
        r16 = r74;
        goto L66
    L55:
        r34 = null;
        r74 = null;
        goto L56
    L57:
        TLRPC.Chat r35 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r68));
        if (r35 == null) goto L60;
        r2 = r35.title;
        boolean r610 = !r32;
        r74 = getAvatarBitmap(r35, true, r610);
        r34 = getAvatarBitmap(r35, false, r610);
        goto L56
    L60:
        r74 = null;
        r15 = null;
        goto L61
    L47:
        if (r31.isRoundVideo() == true) goto L50;
        r62 = null;
        r15 = null;
        goto L69
    L6:
        if (r31.isVoice() == false) goto L8;
    L9:
        r5.setAction("android.intent.action.VIEW");
        TLRPC.Peer r611 = r31.messageOwner.peer_id;
        if ((r611 instanceof TLRPC.TL_peerUser) == false) goto L13;
        long r95 = r611.user_id;
    L20:
        if (r95 == 0) goto L25;
        if ((r611 instanceof TLRPC.TL_peerUser) == false) goto L24;
        r5.setData(Uri.parse("tg://openmessage?user_id=" + r95 + "&message_id=" + r31.getId()));
        goto L25
    L24:
        r5.setData(Uri.parse("tg://openmessage?chat_id=" + r95 + "&message_id=" + r31.getId()));
        goto L25
    L13:
        if ((r611 instanceof TLRPC.TL_peerChat) == false) goto L16;
        r95 = r611.chat_id;
        goto L20
    L16:
        if ((r611 instanceof TLRPC.TL_peerChannel) == false) goto L18;
        r95 = r611.channel_id;
        goto L20
    L18:
        r95 = 0;
        goto L20
    L8:
        if (r31.isRoundVideo() == false) goto L25;
        goto L9
    }

    private int fixIntentFlags(int r2) {
        if (Build.VERSION.SDK_INT < 31) goto L5;
    L8:
        return r2;
    L5:
        if (XiaomiUtilities.isMIUI() == false) goto L8;
        return (-100663297) & r2;
    }

    private Bitmap getAvatarBitmap(TLObject r13, boolean r14, boolean r15) {
        if (r14 == false) goto L5;
        int r0 = MediaError.DetailedErrorCode.TEXT_UNKNOWN;
    L4:
        int r1 = r0;
    L13:
        th = move-exception;
        FileLog.e(th);
    L40:
        if (r14 == true) goto L48;
        Theme.createDialogsResources(this);
        if ((r13 instanceof TLRPC.User) == false) goto L44;
        AvatarDrawable r12 = new AvatarDrawable((TLRPC.User) r13);
    L45:
        r12.setRoundRadius(1);
        float r132 = r1;
        Bitmap r3 = Bitmap.createBitmap(AndroidUtilities.dp(r132), AndroidUtilities.dp(r132), Bitmap.Config.ARGB_8888);
        r12.setBounds(0, 0, r3.getWidth(), r3.getHeight());
        r12.draw(new Canvas(r3));
        return r3;
    L44:
        r12 = new AvatarDrawable((TLRPC.Chat) r13);
        goto L45
    L48:
        return null;
    L8:
        if ((r13 instanceof TLRPC.User) == false) goto L25;
        TLRPC.User r02 = (TLRPC.User) r13;     // Catch: Throwable -> L13
        TLRPC.UserProfilePhoto r5 = r02.photo;
        if (r14 == false) goto L15;
        TLRPC.FileLocation r52 = r5.photo_big;     // Catch: Throwable -> L13
    L16:
        if (r52 == null) goto L40;
        File r6 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r52, true);     // Catch: Throwable -> L13
        if (r6.exists() == false) goto L21;
        float r03 = r1;
        return ImageLoader.loadBitmap(r6.getAbsolutePath(), null, r03, r03, false);
    L21:
        if (r14 == false) goto L40;
        if (r15 == false) goto L24;
        this.loadingFilePath = FileLoader.getAttachFileName(r52);     // Catch: Throwable -> L13
        this.imageReceiver.setImage(ImageLocation.getForUser(UserConfig.selectedAccount, r02, 0), _UrlKt.FRAGMENT_ENCODE_SET, null, null, null, 0);     // Catch: Throwable -> L13
        goto L40
    L24:
        this.loadingFilePath = null;     // Catch: Throwable -> L13
        goto L40
    L15:
        r52 = r5.photo_small;     // Catch: Throwable -> L13
        goto L16
    L25:
        TLRPC.Chat r04 = (TLRPC.Chat) r13;     // Catch: Throwable -> L13
        if (r14 == false) goto L28;
        TLRPC.FileLocation r53 = r04.photo.photo_big;     // Catch: Throwable -> L13
    L29:
        if (r53 == null) goto L40;
        File r62 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r53, true);     // Catch: Throwable -> L13
        if (r62.exists() == false) goto L34;
        float r05 = r1;
        return ImageLoader.loadBitmap(r62.getAbsolutePath(), null, r05, r05, false);
    L34:
        if (r14 == false) goto L40;
        if (r15 == false) goto L37;
        this.loadingFilePath = FileLoader.getAttachFileName(r53);     // Catch: Throwable -> L13
        this.imageReceiver.setImage(ImageLocation.getForChat(r04, 0), _UrlKt.FRAGMENT_ENCODE_SET, null, null, null, 0);     // Catch: Throwable -> L13
        goto L40
    L37:
        this.loadingFilePath = null;     // Catch: Throwable -> L13
        goto L40
    L28:
        r53 = r04.photo.photo_small;     // Catch: Throwable -> L13
        goto L29
    L5:
        r0 = 100;
        goto L4
    }

    private float getPlaybackSpeed(boolean r1, MessageObject r2) {
        if (r1 == false) goto L12;
        if (r2 != null) goto L5;
        return 1.0f;
    L5:
        if (r2.isVoice() == true) goto L9;
        if (r2.isRoundVideo() == true) goto L9;
        return 1.0f;
    L9:
        return MediaController.getInstance().getPlaybackSpeed(false);
    L12:
        return 0.0f;
    }

    private /* synthetic */ void lambda$onCreate$0(ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        if (r2 == true) goto L4;
        return;
    L4:
        if (TextUtils.isEmpty(this.loadingFilePath) == true) goto L11;
        MessageObject r12 = MediaController.getInstance().getPlayingMessageObject();
        if (r12 == null) goto L8;
        createNotification(r12, true);
    L8:
        this.loadingFilePath = null;
        return;
    }

    private Bitmap loadArtworkFromUrl(String r11, boolean r12, boolean r13) {
        File r0 = ImageLoader.getHttpFilePath(r11, "jpg");
        if (r0.exists() == false) goto L12;
        String r10 = r0.getAbsolutePath();
        float r112 = 100.0f;
        if (r12 == false) goto L7;
        float r02 = 600.0f;
    L8:
        if (r12 == false) goto L11;
        r112 = 600.0f;
    L11:
        return ImageLoader.loadBitmap(r10, null, r02, r112, false);
    L7:
        r02 = 100.0f;
        goto L8
    L12:
        if (r13 == false) goto L16;
        this.loadingFilePath = r0.getAbsolutePath();
        if (r12 == true) goto L17;
        this.imageReceiver.setImage(r11, "48_48", null, null, 0);
    L17:
        return null;
    L16:
        this.loadingFilePath = null;
        goto L17
    }

    private void updatePlaybackState(long r9) {
        this.playbackState = new PlaybackStateCompat.Builder();
        boolean r0 = MediaController.getInstance().isMessagePaused();
        boolean r1 = !r0;
        if (MediaController.getInstance().isDownloadingCurrentMessage() == false) goto L5;
        this.playbackState.setState(6, 0, 1.0f).setActions(0);
    L30:
        PlaybackStateCompat r92 = this.playbackState.build();
        this.mediaSession.setPlaybackState(r92);
        TelegramMediaSession r8 = TelegramMediaSession.peekInstance();
        if (r8 == null) goto L34;
        r8.publishPlaybackState(r92);
        return;
    L34:
        return;
    L5:
        MessageObject r2 = MediaController.getInstance().getPlayingMessageObject();
        if (r2 != null) goto L8;
    L14:
        long r3 = 2360070;
    L15:
        PlaybackStateCompat.Builder r5 = this.playbackState;
        if (r0 == true) goto L18;
        int r02 = 3;
    L19:
        r5.setState(r02, r9, getPlaybackSpeed(r1, r2)).setActions(r3);
        if (r2 == null) goto L30;
        if (r2.isMusic() == false) goto L30;
        int r93 = SharedConfig.repeatMode;
        if (r93 == 1) goto L28;
        if (r93 == 2) goto L27;
        int r94 = R.drawable.player_new_repeat_off;
    L29:
        this.playbackState.addCustomAction(new PlaybackStateCompat.CustomAction.Builder(NOTIFY_REPEAT, LocaleController.getString(R.string.RepeatSong), r94).build());
        goto L30
    L27:
        r94 = R.drawable.player_new_repeatone;
        goto L29
    L28:
        r94 = R.drawable.player_new_repeatall;
        goto L29
    L18:
        r02 = 2;
        goto L19
    L8:
        if (r2.isMusic() == false) goto L14;
        if (SharedConfig.shuffleMusic == false) goto L12;
        int r32 = R.drawable.player_new_shuffle;
    L13:
        this.playbackState.addCustomAction(new PlaybackStateCompat.CustomAction.Builder(NOTIFY_SHUFFLE, LocaleController.getString(R.string.ShuffleList), r32).build());
        r3 = 2360118;
        goto L15
    L12:
        r32 = R.drawable.player_new_shuffle_off;
        goto L13
    }

    private void updateRepeatMode() {
        MediaSessionCompat r3 = this.mediaSession;
        if (r3 == null) goto L10;
        int r0 = SharedConfig.repeatMode;
        int r1 = 2;
        if (r0 == 1) goto L9;
        if (r0 == 2) goto L8;
        r1 = 0;
        goto L9
    L8:
        r1 = 1;
    L9:
        r3.setRepeatMode(r1);
    L10:
        TelegramMediaSession r32 = TelegramMediaSession.peekInstance();
        if (r32 == null) goto L14;
        r32.updateRepeatMode();
        return;
    }

    private void updateShuffleMode() {
        MediaSessionCompat r1 = this.mediaSession;
        if (r1 == null) goto L5;
        r1.setShuffleMode(SharedConfig.shuffleMusic ? 1 : 0);
    L5:
        TelegramMediaSession r12 = TelegramMediaSession.peekInstance();
        if (r12 == null) goto L9;
        r12.updateShuffleMode();
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.messagePlayingPlayStateChanged) goto L11;
        MessageObject r32 = MediaController.getInstance().getPlayingMessageObject();
        if (r32 == null) goto L8;
        createNotification(r32, false);
        return;
    L8:
        stopSelf();
        return;
    L11:
        if (r3 != NotificationCenter.messagePlayingDidSeek) goto L28;
        if (MediaController.getInstance().getPlayingMessageObject() == null) goto L55;
        long r33 = Math.round(r3.audioPlayerDuration * ((Float) r5[1]).floatValue()) * 1000;
        updatePlaybackState(r33);
        RemoteControlClient r2 = this.remoteControlClient;
        if (r2 != null) goto L18;
        return;
    L18:
        if (MediaController.getInstance().isMessagePaused() == false) goto L20;
        int r52 = 2;
    L22:
        if (MediaController.getInstance().isMessagePaused() == false) goto L24;
        float r0 = 0.0f;
    L25:
        r2.setPlaybackState(r52, r33, r0);
        return;
    L24:
        r0 = 1.0f;
        goto L25
    L20:
        r52 = 3;
        goto L22
    L55:
        return;
    L28:
        if (r3 != NotificationCenter.httpFileDidLoad) goto L38;
        String r34 = (String) r5[0];
        MessageObject r42 = MediaController.getInstance().getPlayingMessageObject();
        if (r42 == null) goto L48;
        String r53 = this.loadingFilePath;
        if (r53 != null) goto L34;
        return;
    L34:
        if (r53.equals(r34) == false) goto L50;
        createNotification(r42, false);
        return;
    L50:
        return;
    L48:
        return;
    L38:
        if (r3 != NotificationCenter.fileLoaded) goto L51;
        String r35 = (String) r5[0];
        MessageObject r43 = MediaController.getInstance().getPlayingMessageObject();
        if (r43 == null) goto L52;
        String r54 = this.loadingFilePath;
        if (r54 != null) goto L44;
        return;
    L44:
        if (r54.equals(r35) == false) goto L54;
        createNotification(r43, false);
        return;
    L54:
        return;
    L52:
        return;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.audioManager = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        int r1 = 0;
    L4:
        if (r1 >= 16) goto L6;
        NotificationCenter.getInstance(r1).addObserver(this, NotificationCenter.messagePlayingDidSeek);
        NotificationCenter.getInstance(r1).addObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(r1).addObserver(this, NotificationCenter.httpFileDidLoad);
        NotificationCenter.getInstance(r1).addObserver(this, NotificationCenter.fileLoaded);
        r1 = r1 + 1;
        goto L4
    L6:
        ImageReceiver r12 = new ImageReceiver(null);
        this.imageReceiver = r12;
        r12.setDelegate(new MusicPlayerService$$ExternalSyntheticLambda0(this));
        this.mediaSession = new MediaSessionCompat(this, "telegramAudioPlayer");
        this.playbackState = new PlaybackStateCompat.Builder();
        this.albumArtPlaceholder = Bitmap.createBitmap(AndroidUtilities.dp(102.0f), AndroidUtilities.dp(102.0f), Bitmap.Config.ARGB_8888);
        Drawable r13 = getResources().getDrawable(R.drawable.nocover_big);
        r13.setBounds(0, 0, this.albumArtPlaceholder.getWidth(), this.albumArtPlaceholder.getHeight());
        r13.draw(new Canvas(this.albumArtPlaceholder));
        this.mediaSession.setCallback(new AnonymousClass2(this));
        this.mediaSession.setActive(true);
        updateRepeatMode();
        updateShuffleMode();
        registerReceiver(this.headsetPlugReceiver, new IntentFilter("android.media.AUDIO_BECOMING_NOISY"));
        super.onCreate();
    }

    @Override // android.app.Service
    @SuppressLint({"NewApi"})
    public void onDestroy() {
        unregisterReceiver(this.headsetPlugReceiver);
        super.onDestroy();
        stopForeground(true);
        RemoteControlClient r1 = this.remoteControlClient;
        if (r1 == null) goto L5;
        RemoteControlClient.MetadataEditor r0 = r1.editMetadata(true);
        r0.clear();
        r0.apply();
        this.audioManager.unregisterRemoteControlClient(this.remoteControlClient);
    L5:
        MediaSessionCompat r02 = this.mediaSession;
        if (r02 == null) goto L8;
        r02.release();
    L8:
        int r03 = 0;
    L10:
        if (r03 >= 16) goto L12;
        NotificationCenter.getInstance(r03).removeObserver(this, NotificationCenter.messagePlayingDidSeek);
        NotificationCenter.getInstance(r03).removeObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(r03).removeObserver(this, NotificationCenter.httpFileDidLoad);
        NotificationCenter.getInstance(r03).removeObserver(this, NotificationCenter.fileLoaded);
        r03 = r03 + 1;
        goto L10
    }

    @Override // android.app.Service
    @SuppressLint({"NewApi"})
    public int onStartCommand(Intent r4, int r5, int r6) {
        if (r4 != null) goto L31;
    L10:
        MessageObject r42 = MediaController.getInstance().getPlayingMessageObject();     // Catch: Exception -> L8
        if (r42 != null) goto L15;
        AndroidUtilities.runOnUIThread(new MusicPlayerService$$ExternalSyntheticLambda1(this));     // Catch: Exception -> L8
        return 1;
    L15:
        if (supportLockScreenControls == false) goto L25;
        ComponentName r62 = new ComponentName(getApplicationContext(), MusicPlayerReceiver.class.getName());     // Catch: Exception -> L8
    L20:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L8
    L8:
        e = move-exception;
        e.printStackTrace();
    L28:
        return 1;
    L18:
        if (this.remoteControlClient != null) goto L22;
        this.audioManager.registerMediaButtonEventReceiver(r62);     // Catch: Exception -> L20
        Intent r1 = new Intent("android.intent.action.MEDIA_BUTTON");     // Catch: Exception -> L20
        r1.setComponent(r62);     // Catch: Exception -> L20
        RemoteControlClient r12 = new RemoteControlClient(PendingIntent.getBroadcast(this, 0, r1, fixIntentFlags(33554432)));     // Catch: Exception -> L20
        this.remoteControlClient = r12;     // Catch: Exception -> L20
        this.audioManager.registerRemoteControlClient(r12);     // Catch: Exception -> L20
    L22:
        this.remoteControlClient.setTransportControlFlags(189);     // Catch: Exception -> L20
    L25:
        createNotification(r42, false);     // Catch: Exception -> L8
        goto L28
    L31:
        if ((getPackageName() + ".STOP_PLAYER").equals(r4.getAction()) == false) goto L10;
        MediaController.getInstance().cleanupPlayer(true, true);     // Catch: Exception -> L8
        return 2;
    }

    public void setListeners(RemoteViews r6) {
        PendingIntent r0 = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(NOTIFY_PREVIOUS), fixIntentFlags(167772160));
        r6.setOnClickPendingIntent(R.id.player_previous, r0);
        PendingIntent r02 = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(NOTIFY_CLOSE), fixIntentFlags(167772160));
        r6.setOnClickPendingIntent(R.id.player_close, r02);
        PendingIntent r03 = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(NOTIFY_PAUSE), fixIntentFlags(167772160));
        r6.setOnClickPendingIntent(R.id.player_pause, r03);
        PendingIntent r04 = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(NOTIFY_NEXT), fixIntentFlags(167772160));
        r6.setOnClickPendingIntent(R.id.player_next, r04);
        PendingIntent r5 = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(NOTIFY_PLAY), fixIntentFlags(167772160));
        r6.setOnClickPendingIntent(R.id.player_play, r5);
    }
}
