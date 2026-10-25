package androidx.media3.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaSession;
import androidx.media3.session.PlayerInfo;
import androidx.media3.session.SequencedFutureManager;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.ui.WearAuthSheet$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
class MediaSessionImpl {
    private static final SessionResult RESULT_WHEN_CLOSED = null;
    private final Handler applicationHandler;
    private final BitmapLoader bitmapLoader;
    private final MediaSession.Callback callback;
    private boolean closed;
    private final ImmutableList<CommandButton> commandButtonsForMediaItems;
    private final Context context;
    private MediaSession.ControllerInfo controllerForCurrentRequest;
    private ImmutableList<CommandButton> customLayout;
    private final MediaSession instance;
    private boolean isMediaNotificationControllerConnected;
    private final boolean isPeriodicPositionUpdateEnabled;
    private final Object lock;
    private final Handler mainHandler;
    private ImmutableList<CommandButton> mediaButtonPreferences;
    private final MediaPlayPauseKeyHandler mediaPlayPauseKeyHandler;
    private final PlayerInfoChangedHandler onPlayerInfoChangedHandler;
    private final Runnable periodicSessionPositionInfoUpdateRunnable;
    private final boolean playIfSuppressed;
    private PlaybackException playbackException;
    private PlayerInfo playerInfo;
    private PlayerListener playerListener;
    private PlayerWrapper playerWrapper;
    private PendingIntent sessionActivity;
    private Bundle sessionExtras;
    private final String sessionId;
    private final MediaSessionLegacyStub sessionLegacyStub;
    private long sessionPositionUpdateDelayMs;
    private final MediaSessionStub sessionStub;
    private final SessionToken sessionToken;
    private final Uri sessionUri;

    /* renamed from: androidx.media3.session.MediaSessionImpl$1, reason: invalid class name */
    public class AnonymousClass1 implements FutureCallback<MediaSession.MediaItemsWithStartPosition> {
        final /* synthetic */ MediaSessionImpl this$0;
        final /* synthetic */ boolean val$callOnPlayerInteractionFinished;
        final /* synthetic */ MediaSession.ControllerInfo val$controllerForRequest;
        final /* synthetic */ Player.Commands val$playCommand;

        /* renamed from: $r8$lambda$SAGIpfgpIP-zdfCKENbGxwvJew8, reason: not valid java name */
        public static /* synthetic */ void m880$r8$lambda$SAGIpfgpIPzdfCKENbGxwvJew8(AnonymousClass1 r1, MediaSession.MediaItemsWithStartPosition r2, boolean r3, MediaSession.ControllerInfo r4, Player.Commands r5) {
            MediaUtils.setMediaItemsWithStartIndexAndPosition(MediaSessionImpl.access$000(r1.this$0), r2);
            Util.handlePlayButtonAction(MediaSessionImpl.access$000(r1.this$0));
            if (r3 == false) goto L6;
            r1.this$0.onPlayerInteractionFinishedOnHandler(r4, r5);
            return;
        }

        public AnonymousClass1(MediaSessionImpl r1, MediaSession.ControllerInfo r2, boolean r3, Player.Commands r4) {
            this.this$0 = r1;
            this.val$controllerForRequest = r2;
            this.val$callOnPlayerInteractionFinished = r3;
            this.val$playCommand = r4;
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable r4) {
            if ((r4 instanceof UnsupportedOperationException) == false) goto L5;
            Log.w("MediaSessionImpl", "UnsupportedOperationException: Make sure to implement MediaSession.Callback.onPlaybackResumption() if you add a media button receiver to your manifest or if you implement the recent media item contract with your MediaLibraryService.", r4);
        L6:
            Util.handlePlayButtonAction(MediaSessionImpl.access$000(this.this$0));
            if (this.val$callOnPlayerInteractionFinished == false) goto L10;
            this.this$0.onPlayerInteractionFinishedOnHandler(this.val$controllerForRequest, this.val$playCommand);
            return;
        L10:
            return;
        L5:
            Log.e("MediaSessionImpl", "Failure calling MediaSession.Callback.onPlaybackResumption(): " + r4.getMessage(), r4);
            goto L6
        }

        /* renamed from: onSuccess, reason: avoid collision after fix types in other method */
        public void onSuccess2(MediaSession.MediaItemsWithStartPosition r8) {
            MediaSessionImpl r0 = this.this$0;
            MediaSession.ControllerInfo r5 = this.val$controllerForRequest;
            r0.callWithControllerForCurrentRequestSet(r5, new MediaSessionImpl$1$$ExternalSyntheticLambda0(this, r8, this.val$callOnPlayerInteractionFinished, r5, this.val$playCommand)).run();
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public /* bridge */ /* synthetic */ void onSuccess(MediaSession.MediaItemsWithStartPosition r1) {
            onSuccess2(r1);
        }
    }

    public class MediaPlayPauseKeyHandler extends Handler {
        private Runnable playPauseTask;
        final /* synthetic */ MediaSessionImpl this$0;

        public static /* synthetic */ void $r8$lambda$uyPWhim0S19Cqz8c58uvz4Iclzg(MediaPlayPauseKeyHandler r2, MediaSession.ControllerInfo r3, KeyEvent r4) {
            boolean r0 = r2.this$0.isMediaNotificationController(r3);
            MediaSessionImpl r1 = r2.this$0;
            if (r0 == false) goto L5;
            MediaSessionImpl.access$800(r1, r4, false, false);
        L6:
            r2.playPauseTask = null;
            return;
        L5:
            MediaSessionImpl.access$900(r1).handleMediaPlayPauseOnHandler((MediaSessionManager.RemoteUserInfo) Assertions.checkNotNull(r3.getRemoteUserInfo()));
            goto L6
        }

        public MediaPlayPauseKeyHandler(MediaSessionImpl r1, Looper r2) {
            this.this$0 = r1;
            super(r2);
        }

        public Runnable clearPendingPlayPauseTask() {
            Runnable r0 = this.playPauseTask;
            if (r0 == null) goto L6;
            removeCallbacks(r0);
            Runnable r02 = this.playPauseTask;
            this.playPauseTask = null;
            return r02;
        L6:
            return null;
        }

        public void flush() {
            Runnable r0 = clearPendingPlayPauseTask();
            if (r0 == null) goto L6;
            Util.postOrRun(this, r0);
            return;
        }

        public boolean hasPendingPlayPauseTask() {
            if (this.playPauseTask == null) goto L6;
            return true;
        L6:
            return false;
        }

        public void setPendingPlayPauseTask(MediaSession.ControllerInfo r2, KeyEvent r3) {
            MediaSessionImpl$MediaPlayPauseKeyHandler$$ExternalSyntheticLambda0 r0 = new MediaSessionImpl$MediaPlayPauseKeyHandler$$ExternalSyntheticLambda0(this, r2, r3);
            this.playPauseTask = r0;
            postDelayed(r0, ViewConfiguration.getDoubleTapTimeout());
        }
    }

    public class PlayerInfoChangedHandler extends Handler {
        private boolean excludeTimeline;
        private boolean excludeTracks;
        final /* synthetic */ MediaSessionImpl this$0;

        public PlayerInfoChangedHandler(MediaSessionImpl r1, Looper r2) {
            this.this$0 = r1;
            super(r2);
            this.excludeTimeline = true;
            this.excludeTracks = true;
        }

        @Override // android.os.Handler
        public void handleMessage(Message r6) {
            if (r6.what != 1) goto L6;
            MediaSessionImpl r62 = this.this$0;
            MediaSessionImpl.access$202(r62, MediaSessionImpl.access$200(r62).copyWithTimelineAndSessionPositionInfo(this.this$0.getPlayerWrapper().getCurrentTimelineWithCommandCheck(), this.this$0.getPlayerWrapper().createSessionPositionInfo(), MediaSessionImpl.access$200(this.this$0).timelineChangeReason));
            MediaSessionImpl r63 = this.this$0;
            MediaSessionImpl.access$1000(r63, MediaSessionImpl.access$200(r63), this.excludeTimeline, this.excludeTracks);
            this.excludeTimeline = true;
            this.excludeTracks = true;
            return;
        L6:
            WearAuthSheet$$ExternalSyntheticBUOutline0.m("Invalid message what=", r6.what);
        }

        public boolean hasPendingPlayerInfoChangedUpdate() {
            return hasMessages(1);
        }

        public void sendPlayerInfoChangedMessage(boolean r4, boolean r5) {
            boolean r1 = false;
            if (this.excludeTimeline == false) goto L6;
            if (r4 == false) goto L6;
            boolean r42 = true;
        L7:
            this.excludeTimeline = r42;
            if (this.excludeTracks == false) goto L11;
            if (r5 == false) goto L11;
            r1 = true;
        L11:
            this.excludeTracks = r1;
            if (hasMessages(1) == true) goto L15;
            sendEmptyMessage(1);
            return;
        L15:
            return;
        L6:
            r42 = false;
            goto L7
        }
    }

    public static class PlayerListener implements Player.Listener {
        private final WeakReference<PlayerWrapper> player;
        private final WeakReference<MediaSessionImpl> session;

        /* renamed from: $r8$lambda$-tbzpNaHrx86wpMXLnmHCZnDM_I, reason: not valid java name */
        public static /* synthetic */ void m881$r8$lambda$tbzpNaHrx86wpMXLnmHCZnDM_I(TrackSelectionParameters r0, MediaSession.ControllerCb r1, int r2) {
            r1.onTrackSelectionParametersChanged(r2, r0);
        }

        public static /* synthetic */ void $r8$lambda$0Tf5nHOMzhjqKJscoDrKQkKlrfY(VideoSize r0, MediaSession.ControllerCb r1, int r2) {
            r1.onVideoSizeChanged(r2, r0);
        }

        public static /* synthetic */ void $r8$lambda$75sh95cSoeo_DxlQ56v16fR4Ndw(int r0, MediaSession.ControllerCb r1, int r2) {
            r1.onPlaybackSuppressionReasonChanged(r2, r0);
        }

        /* renamed from: $r8$lambda$AGlR_sz6D1gU6mnA8wOm9Ttk-20, reason: not valid java name */
        public static /* synthetic */ void m882$r8$lambda$AGlR_sz6D1gU6mnA8wOm9Ttk20(PlaybackException r0, MediaSession.ControllerCb r1, int r2) {
            r1.onPlayerError(r2, r0);
        }

        public static /* synthetic */ void $r8$lambda$Aj9EoK_619_Fv4CrKWwvCYledKw(Tracks r0, MediaSession.ControllerCb r1, int r2) {
            r1.onTracksChanged(r2, r0);
        }

        public static /* synthetic */ void $r8$lambda$GHEKenr0E94YNtmcu6hP3bvvTJM(AudioAttributes r0, MediaSession.ControllerCb r1, int r2) {
            r1.onAudioAttributesChanged(r2, r0);
        }

        public static /* synthetic */ void $r8$lambda$H2CXoqZJHXxSnf2SA_Hd8tmwWD0(MediaMetadata r0, MediaSession.ControllerCb r1, int r2) {
            r1.onPlaylistMetadataChanged(r2, r0);
        }

        /* renamed from: $r8$lambda$JEHtxHrVtnCF8sj1-KeQ5NMbuQM, reason: not valid java name */
        public static /* synthetic */ void m883$r8$lambda$JEHtxHrVtnCF8sj1KeQ5NMbuQM(PlaybackParameters r0, MediaSession.ControllerCb r1, int r2) {
            r1.onPlaybackParametersChanged(r2, r0);
        }

        public static /* synthetic */ void $r8$lambda$MCfqAsOdHwPMsu9DmvzciysL290(Timeline r0, int r1, MediaSession.ControllerCb r2, int r3) {
            r2.onTimelineChanged(r3, r0, r1);
        }

        public static /* synthetic */ void $r8$lambda$Tb1bFiBT1Zxg1lM8JzTF6AqMS9w(boolean r0, int r1, MediaSession.ControllerCb r2, int r3) {
            r2.onPlayWhenReadyChanged(r3, r0, r1);
        }

        public static /* synthetic */ void $r8$lambda$Tq9kM6LXTieW4IdJKT5xHXNG7NE(MediaItem r0, int r1, MediaSession.ControllerCb r2, int r3) {
            r2.onMediaItemTransition(r3, r0, r1);
        }

        /* renamed from: $r8$lambda$UlVEMWzon4aHqT7K7NE1IF-yiOY, reason: not valid java name */
        public static /* synthetic */ void m884$r8$lambda$UlVEMWzon4aHqT7K7NE1IFyiOY(Player.PositionInfo r0, Player.PositionInfo r1, int r2, MediaSession.ControllerCb r3, int r4) {
            r3.onPositionDiscontinuity(r4, r0, r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$V0ENIDmFAHKZ7Zi__DWuK3inXyE(boolean r0, MediaSession.ControllerCb r1, int r2) {
            r1.onShuffleModeEnabledChanged(r2, r0);
        }

        public static /* synthetic */ void $r8$lambda$ZVGaSeuTadNkFzpw0tZnQzGRbsM(int r0, PlayerWrapper r1, MediaSession.ControllerCb r2, int r3) {
            r2.onPlaybackStateChanged(r3, r0, r1.getPlayerError());
        }

        public static /* synthetic */ void $r8$lambda$ZsFiawqVWV7k__Q_7T3Xi4pLhgU(boolean r0, MediaSession.ControllerCb r1, int r2) {
            r1.onIsLoadingChanged(r2, r0);
        }

        public static /* synthetic */ void $r8$lambda$im2ZGM9bO65sP4ILdTCVa9g96EA(float r0, MediaSession.ControllerCb r1, int r2) {
            r1.onVolumeChanged(r2, r0);
        }

        /* renamed from: $r8$lambda$jsfEWB7_AL316Am3M-aBOA6okW8, reason: not valid java name */
        public static /* synthetic */ void m885$r8$lambda$jsfEWB7_AL316Am3MaBOA6okW8(int r0, MediaSession.ControllerCb r1, int r2) {
            r1.onRepeatModeChanged(r2, r0);
        }

        public static /* synthetic */ void $r8$lambda$mAgkDWYL76btjoXkHkicZnM5sAM(DeviceInfo r0, MediaSession.ControllerCb r1, int r2) {
            r1.onDeviceInfoChanged(r2, r0);
        }

        public static /* synthetic */ void $r8$lambda$qS38FDgcvaM_UZAyz6CaGWc2GMg(int r0, boolean r1, MediaSession.ControllerCb r2, int r3) {
            r2.onDeviceVolumeChanged(r3, r0, r1);
        }

        public static /* synthetic */ void $r8$lambda$v0mCKdUiJrbkBzdqVTuZOYGKSC8(MediaMetadata r0, MediaSession.ControllerCb r1, int r2) {
            r1.onMediaMetadataChanged(r2, r0);
        }

        /* renamed from: $r8$lambda$zno-yh8iBW8EAl3pCaXkCOYyzEs, reason: not valid java name */
        public static /* synthetic */ void m886$r8$lambda$znoyh8iBW8EAl3pCaXkCOYyzEs(boolean r0, MediaSession.ControllerCb r1, int r2) {
            r1.onIsPlayingChanged(r2, r0);
        }

        public PlayerListener(MediaSessionImpl r2, PlayerWrapper r3) {
            this.session = new WeakReference(r2);
            this.player = new WeakReference(r3);
        }

        private MediaSessionImpl getSession() {
            return this.session.get();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onAudioAttributesChanged(AudioAttributes r3) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithAudioAttributes(r3));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda13(r3));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onAvailableCommandsChanged(Player.Commands r2) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$600(r0, r2);
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onCues(CueGroup r3) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, new PlayerInfo.Builder(MediaSessionImpl.access$200(r0)).setCues(r3).build());
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onDeviceInfoChanged(DeviceInfo r3) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithDeviceInfo(r3));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda9(r3));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onDeviceVolumeChanged(int r3, boolean r4) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithDeviceVolume(r3, r4));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda2(r3, r4));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onIsLoadingChanged(boolean r3) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithIsLoading(r3));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda8(r3));
            MediaSessionImpl.access$500(r0);
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onIsPlayingChanged(boolean r3) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithIsPlaying(r3));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda0(r3));
            MediaSessionImpl.access$500(r0);
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onMediaItemTransition(MediaItem r3, int r4) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithMediaItemTransitionReason(r4));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda1(r3, r4));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onMediaMetadataChanged(MediaMetadata r3) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithMediaMetadata(r3));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda17(r3));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayWhenReadyChanged(boolean r3, int r4) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithPlayWhenReady(r3, r4, MediaSessionImpl.access$200(r0).playbackSuppressionReason));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda5(r3, r4));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackParametersChanged(PlaybackParameters r3) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithPlaybackParameters(r3));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda15(r3));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackStateChanged(int r4) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            PlayerWrapper r3 = this.player.get();
            if (r3 != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithPlaybackState(r4, r3.getPlayerError()));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda11(r4, r3));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackSuppressionReasonChanged(int r4) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithPlayWhenReady(MediaSessionImpl.access$200(r0).playWhenReady, MediaSessionImpl.access$200(r0).playWhenReadyChangeReason, r4));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda7(r4));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayerError(PlaybackException r3) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithPlayerError(r3));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda4(r3));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaylistMetadataChanged(MediaMetadata r3) {
            MediaSessionImpl r2 = getSession();
            if (r2 != null) goto L5;
            return;
        L5:
            MediaSessionImpl.access$100(r2);
            MediaSessionImpl.access$202(r2, MediaSessionImpl.access$200(r2).copyWithPlaylistMetadata(r3));
            MediaSessionImpl.access$300(r2).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r2, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda19(r3));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPositionDiscontinuity(Player.PositionInfo r3, Player.PositionInfo r4, int r5) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithPositionInfos(r3, r4, r5));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda3(r3, r4, r5));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onRenderedFirstFrame() {
            MediaSessionImpl r5 = getSession();
            if (r5 == null) goto L12;
            MediaSessionImpl.access$100(r5);
            ConnectedControllersManager<IBinder> r0 = MediaSessionImpl.access$700(r5).getConnectedControllersManager();
            ImmutableList<MediaSession.ControllerInfo> r1 = r0.getConnectedControllers();
            int r2 = 0;
        L7:
            if (r2 >= r1.size()) goto L16;
            MediaSession.ControllerInfo r3 = r1.get(r2);
            if (r0.getPlaybackException(r3) != null) goto L11;
            r5.dispatchRemoteControllerTaskWithoutReturn(r3, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda18());
        L11:
            r2 = r2 + 1;
            goto L7
        L16:
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onRepeatModeChanged(int r3) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithRepeatMode(r3));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda16(r3));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onShuffleModeEnabledChanged(boolean r3) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithShuffleModeEnabled(r3));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda21(r3));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTimelineChanged(Timeline r4, int r5) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            PlayerWrapper r3 = this.player.get();
            if (r3 != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithTimelineAndSessionPositionInfo(r4, r3.createSessionPositionInfo(), r5));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(false, true);
            MediaSessionImpl.access$400(r0, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda12(r4, r5));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTrackSelectionParametersChanged(TrackSelectionParameters r3) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithTrackSelectionParameters(r3));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, true);
            r0.dispatchRemoteControllerTaskWithoutReturn(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda20(r3));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTracksChanged(Tracks r4) {
            MediaSessionImpl r0 = getSession();
            if (r0 == null) goto L10;
            MediaSessionImpl.access$100(r0);
            if (this.player.get() != null) goto L8;
            return;
        L8:
            MediaSessionImpl.access$202(r0, MediaSessionImpl.access$200(r0).copyWithCurrentTracks(r4));
            MediaSessionImpl.access$300(r0).sendPlayerInfoChangedMessage(true, false);
            r0.dispatchRemoteControllerTaskWithoutReturn(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda10(r4));
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onVideoSizeChanged(VideoSize r3) {
            MediaSessionImpl r2 = getSession();
            if (r2 != null) goto L5;
            return;
        L5:
            MediaSessionImpl.access$100(r2);
            MediaSessionImpl.access$202(r2, MediaSessionImpl.access$200(r2).copyWithVideoSize(r3));
            MediaSessionImpl.access$300(r2).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r2, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda6(r3));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onVolumeChanged(float r3) {
            MediaSessionImpl r2 = getSession();
            if (r2 != null) goto L5;
            return;
        L5:
            MediaSessionImpl.access$100(r2);
            MediaSessionImpl.access$202(r2, MediaSessionImpl.access$200(r2).copyWithVolume(r3));
            MediaSessionImpl.access$300(r2).sendPlayerInfoChangedMessage(true, true);
            MediaSessionImpl.access$400(r2, new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda14(r3));
        }
    }

    public interface RemoteControllerTask {
        void run(MediaSession.ControllerCb r1, int r2);
    }

    public static /* synthetic */ void $r8$lambda$0JJAc8dqDCSwOHZTi4HsGWjO3d8(SessionCommand r0, Bundle r1, MediaSession.ControllerCb r2, int r3) {
        r2.sendCustomCommand(r3, r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$0KXhYynaXSf7ddUrubjSkjdskIU(MediaSessionImpl r0) {
        r0.schedulePeriodicSessionPositionInfoChanges();
    }

    public static /* synthetic */ void $r8$lambda$3R8lUXP9eNyR8zUo5NSO2Q7Myj4(MediaSessionImpl r1, MediaSession.ControllerInfo r2) {
        r1.sessionStub.playForControllerInfo(r2, Integer.MIN_VALUE);
    }

    public static /* synthetic */ void $r8$lambda$4V9MNjRWIkxq_nLBTCDiHii0x0U(MediaSessionImpl r0, MediaSession.ControllerCb r1, int r2) {
        r1.onDeviceInfoChanged(r2, r0.playerInfo.deviceInfo);
    }

    public static /* synthetic */ void $r8$lambda$6vLrA69rUhTS0iG4bgjxSBvDRAQ(MediaSessionImpl r2, boolean r3, MediaSession.ControllerInfo r4, Runnable r5) {
        r2.getClass();
        if (r3 == false) goto L5;
        Bundle r0 = Bundle.EMPTY;
        r2.sendCustomCommand(r4, new SessionCommand("androidx.media3.session.NOTIFICATION_DISMISSED_EVENT_KEY", r0), r0);
    L5:
        r5.run();
        r2.sessionStub.getConnectedControllersManager().flushCommandQueue(r4);
    }

    /* renamed from: $r8$lambda$8Imq7BY3Fm0wc7OUOm-25HBznu0, reason: not valid java name */
    public static /* synthetic */ void m866$r8$lambda$8Imq7BY3Fm0wc7OUOm25HBznu0(PlayerWrapper r0, PlayerWrapper r1, MediaSession.ControllerCb r2, int r3) {
        r2.onPlayerChanged(r3, r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$9T_FSc9mRWOwvB0dAswwp0tJEYQ(MediaSessionImpl r1, MediaSession.ControllerInfo r2) {
        r1.sessionStub.pauseForControllerInfo(r2, Integer.MIN_VALUE);
    }

    /* renamed from: $r8$lambda$AWMWA98l_10D_hsqLl02-fHqLcg, reason: not valid java name */
    public static /* synthetic */ void m867$r8$lambda$AWMWA98l_10D_hsqLl02fHqLcg(MediaSessionImpl r1) {
        PlayerListener r0 = r1.playerListener;
        if (r0 == null) goto L6;
        r1.playerWrapper.removeListener(r0);
        return;
    }

    /* renamed from: $r8$lambda$CA0XR11JyCwuIBCJgSZN-kNH0YU, reason: not valid java name */
    public static /* synthetic */ void m868$r8$lambda$CA0XR11JyCwuIBCJgSZNkNH0YU(MediaSessionImpl r1, MediaSession.ControllerInfo r2) {
        r1.sessionStub.seekBackForControllerInfo(r2, Integer.MIN_VALUE);
    }

    /* renamed from: $r8$lambda$JYyq-KMVv0G2-7Kxxqf0fhhbULY, reason: not valid java name */
    public static /* synthetic */ void m869$r8$lambda$JYyqKMVv0G27Kxxqf0fhhbULY(MediaSessionImpl r1, MediaSession.ControllerInfo r2) {
        r1.sessionStub.seekToNextForControllerInfo(r2, Integer.MIN_VALUE);
    }

    /* renamed from: $r8$lambda$LbaJMA-nvOZMx4M1Mvj1WGuv1Lc, reason: not valid java name */
    public static /* synthetic */ void m870$r8$lambda$LbaJMAnvOZMx4M1Mvj1WGuv1Lc(MediaSessionImpl r0) {
        r0.getClass();
    }

    public static /* synthetic */ void $r8$lambda$NJtBiMoKSTIKwc8FbKCFCnh5tG4(Player.Commands r0, MediaSession.ControllerCb r1, int r2) {
        r1.onAvailableCommandsChangedFromPlayer(r2, r0);
    }

    /* renamed from: $r8$lambda$PlgEiNQFdzh2COk_GTDDwcVq-0c, reason: not valid java name */
    public static /* synthetic */ void m871$r8$lambda$PlgEiNQFdzh2COk_GTDDwcVq0c(MediaSessionImpl r0) {
        r0.notifyPeriodicSessionPositionInfoChangesOnHandler();
    }

    public static /* synthetic */ void $r8$lambda$QsHlxCnflWkQXKZkG_rSfoOxlFM(MediaSessionImpl r0, SettableFuture r1) {
        r1.set(Boolean.valueOf(r0.onPlayRequested()));
    }

    /* renamed from: $r8$lambda$bk-KOqVu8Xet7Ru7rKxDT58jseI, reason: not valid java name */
    public static /* synthetic */ void m872$r8$lambda$bkKOqVu8Xet7Ru7rKxDT58jseI(MediaSessionImpl r1, MediaSession.ControllerInfo r2) {
        r1.sessionStub.pauseForControllerInfo(r2, Integer.MIN_VALUE);
    }

    /* renamed from: $r8$lambda$chW9-q9fPf--4qcTNZwl7fq_HPk, reason: not valid java name */
    public static /* synthetic */ void m873$r8$lambda$chW9q9fPf4qcTNZwl7fq_HPk(MediaSessionImpl r1, MediaSession.ControllerInfo r2) {
        r1.sessionStub.seekToPreviousForControllerInfo(r2, Integer.MIN_VALUE);
    }

    /* renamed from: $r8$lambda$mOEJZ8IoLcqEVyk1-HOBlDL7s1g, reason: not valid java name */
    public static /* synthetic */ void m874$r8$lambda$mOEJZ8IoLcqEVyk1HOBlDL7s1g(MediaSessionImpl r1, MediaSession.ControllerInfo r2) {
        r1.sessionStub.stopForControllerInfo(r2, Integer.MIN_VALUE);
    }

    /* renamed from: $r8$lambda$opJlFqnUbd-UMyFztXyMFwJ56mo, reason: not valid java name */
    public static /* synthetic */ void m875$r8$lambda$opJlFqnUbdUMyFztXyMFwJ56mo(MediaSessionImpl r0, MediaSession.ControllerInfo r1, Runnable r2) {
        r0.controllerForCurrentRequest = r1;
        r2.run();
        r0.controllerForCurrentRequest = null;
    }

    /* renamed from: $r8$lambda$qt16YGURvQEOYMo-5n4KH8te5yU, reason: not valid java name */
    public static /* synthetic */ void m876$r8$lambda$qt16YGURvQEOYMo5n4KH8te5yU(MediaSessionImpl r1, MediaSession.ControllerInfo r2) {
        r1.sessionStub.seekForwardForControllerInfo(r2, Integer.MIN_VALUE);
    }

    /* renamed from: $r8$lambda$skkGNmjm-ETktD2Qmg3gU3g3bg0, reason: not valid java name */
    public static /* synthetic */ void m877$r8$lambda$skkGNmjmETktD2Qmg3gU3g3bg0(MediaSessionImpl r1, PlayerWrapper r2) {
        r1.setPlayerInternal(null, r2);
    }

    /* renamed from: $r8$lambda$uCr7tcCAHn1WP3r1i67jXm-DWhY, reason: not valid java name */
    public static /* synthetic */ void m878$r8$lambda$uCr7tcCAHn1WP3r1i67jXmDWhY(SessionPositionInfo r1, boolean r2, boolean r3, MediaSession.ControllerInfo r4, MediaSession.ControllerCb r5, int r6) {
        r5.onPeriodicSessionPositionInfoChanged(r6, r1, r2, r3, r4.getInterfaceVersion());
    }

    public static /* synthetic */ void $r8$lambda$x4Hecr6lNdk4Y9wrS1czF0XiyNw(MediaSessionImpl r0, Runnable r1) {
        r0.postOrRunOnApplicationHandler(r1);
    }

    /* renamed from: $r8$lambda$ySSRB9IOiBmAKq1i5-fmFGXJa8w, reason: not valid java name */
    public static /* synthetic */ void m879$r8$lambda$ySSRB9IOiBmAKq1i5fmFGXJa8w(MediaSessionImpl r1, MediaSession.ControllerInfo r2) {
        r1.sessionStub.playForControllerInfo(r2, Integer.MIN_VALUE);
    }

    static {
        RESULT_WHEN_CLOSED = new SessionResult(1);
    }

    public MediaSessionImpl(MediaSession r13, Context r14, String r15, Player r16, PendingIntent r17, ImmutableList<CommandButton> r18, ImmutableList<CommandButton> r19, ImmutableList<CommandButton> r20, MediaSession.Callback r21, Bundle r22, Bundle r23, BitmapLoader r24, boolean r25, boolean r26) {
        this.lock = new Object();
        Log.i("MediaSessionImpl", "Init " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.8.1] [" + Util.DEVICE_DEBUG_INFO + "]");
        this.instance = r13;
        this.context = r14;
        this.sessionId = r15;
        this.sessionActivity = r17;
        this.customLayout = r18;
        this.mediaButtonPreferences = r19;
        this.commandButtonsForMediaItems = r20;
        this.callback = r21;
        this.sessionExtras = r23;
        this.bitmapLoader = r24;
        this.playIfSuppressed = r25;
        this.isPeriodicPositionUpdateEnabled = r26;
        MediaSessionStub r11 = new MediaSessionStub(this);
        this.sessionStub = r11;
        this.mainHandler = new Handler(Looper.getMainLooper());
        Looper r0 = r16.getApplicationLooper();
        Handler r3 = new Handler(r0);
        this.applicationHandler = r3;
        this.playerInfo = PlayerInfo.DEFAULT;
        this.onPlayerInfoChangedHandler = new PlayerInfoChangedHandler(this, r0);
        this.mediaPlayPauseKeyHandler = new MediaPlayPauseKeyHandler(this, r0);
        Uri r2 = new Uri.Builder().scheme(MediaSessionImpl.class.getName()).appendPath(r15).appendPath(String.valueOf(SystemClock.elapsedRealtime())).build();
        this.sessionUri = r2;
        MediaSession.ConnectionResult r132 = new MediaSession.ConnectionResult.AcceptedResultBuilder(r13).build();
        MediaSessionLegacyStub r02 = new MediaSessionLegacyStub(this, r2, r3, r22, r25, r18, r19, r132.availableSessionCommands, r132.availablePlayerCommands, r23);
        this.sessionLegacyStub = r02;
        this.sessionToken = new SessionToken(Process.myUid(), 0, 1008001300, 5, r14.getPackageName(), r11, r22, r02.getSessionCompat().getSessionToken().getToken());
        PlayerWrapper r142 = new PlayerWrapper(r16);
        this.playerWrapper = r142;
        Util.postOrRun(r3, new MediaSessionImpl$$ExternalSyntheticLambda1(this, r142));
        this.sessionPositionUpdateDelayMs = 3000;
        this.periodicSessionPositionInfoUpdateRunnable = new MediaSessionImpl$$ExternalSyntheticLambda2(this);
        Util.postOrRun(r3, new MediaSessionImpl$$ExternalSyntheticLambda3(this));
    }

    public static /* synthetic */ PlayerWrapper access$000(MediaSessionImpl r0) {
        return r0.playerWrapper;
    }

    public static /* synthetic */ void access$100(MediaSessionImpl r0) {
        r0.verifyApplicationThread();
    }

    public static /* synthetic */ void access$1000(MediaSessionImpl r0, PlayerInfo r1, boolean r2, boolean r3) {
        r0.dispatchOnPlayerInfoChanged(r1, r2, r3);
    }

    public static /* synthetic */ PlayerInfo access$200(MediaSessionImpl r0) {
        return r0.playerInfo;
    }

    public static /* synthetic */ PlayerInfo access$202(MediaSessionImpl r0, PlayerInfo r1) {
        r0.playerInfo = r1;
        return r1;
    }

    public static /* synthetic */ PlayerInfoChangedHandler access$300(MediaSessionImpl r0) {
        return r0.onPlayerInfoChangedHandler;
    }

    public static /* synthetic */ void access$400(MediaSessionImpl r0, RemoteControllerTask r1) {
        r0.dispatchRemoteControllerTaskToLegacyStub(r1);
    }

    public static /* synthetic */ void access$500(MediaSessionImpl r0) {
        r0.schedulePeriodicSessionPositionInfoChanges();
    }

    public static /* synthetic */ void access$600(MediaSessionImpl r0, Player.Commands r1) {
        r0.handleAvailablePlayerCommandsChanged(r1);
    }

    public static /* synthetic */ MediaSessionStub access$700(MediaSessionImpl r0) {
        return r0.sessionStub;
    }

    public static /* synthetic */ boolean access$800(MediaSessionImpl r0, KeyEvent r1, boolean r2, boolean r3) {
        return r0.applyMediaButtonKeyEvent(r1, r2, r3);
    }

    public static /* synthetic */ MediaSessionLegacyStub access$900(MediaSessionImpl r0) {
        return r0.sessionLegacyStub;
    }

    private boolean applyMediaButtonKeyEvent(KeyEvent r3, boolean r4, boolean r5) {
        MediaSession.ControllerInfo r0 = (MediaSession.ControllerInfo) Assertions.checkNotNull(this.instance.getMediaNotificationControllerInfo());
        int r32 = r3.getKeyCode();
        if (r32 != 85) goto L5;
    L6:
        if (r4 == false) goto L9;
        r32 = 87;
    L9:
        if (r32 != 126) goto L11;
        Runnable r33 = new MediaSessionImpl$$ExternalSyntheticLambda9(this, r0);
    L30:
        Util.postOrRun(getApplicationHandler(), new MediaSessionImpl$$ExternalSyntheticLambda16(this, r5, r0, r33));
        return true;
    L11:
        if (r32 != 127) goto L13;
        r33 = new MediaSessionImpl$$ExternalSyntheticLambda10(this, r0);
        goto L30
    L13:
        if (r32 != 272) goto L15;
    L27:
        r33 = new MediaSessionImpl$$ExternalSyntheticLambda11(this, r0);
        goto L30
    L15:
        if (r32 == 273) goto L26;
        switch(r32) {
            case 85: goto L23;
            case 86: goto L21;
            case 87: goto L27;
            case 88: goto L26;
            case 89: goto L20;
            case 90: goto L19;
            default: goto L17;
        };
    L17:
        return false;
    L19:
        r33 = new MediaSessionImpl$$ExternalSyntheticLambda13(this, r0);
        goto L30
    L20:
        r33 = new MediaSessionImpl$$ExternalSyntheticLambda14(this, r0);
        goto L30
    L21:
        r33 = new MediaSessionImpl$$ExternalSyntheticLambda15(this, r0);
        goto L30
    L23:
        if (getPlayerWrapper().getPlayWhenReady() == false) goto L25;
        r33 = new MediaSessionImpl$$ExternalSyntheticLambda7(this, r0);
        goto L30
    L25:
        r33 = new MediaSessionImpl$$ExternalSyntheticLambda8(this, r0);
    L26:
        r33 = new MediaSessionImpl$$ExternalSyntheticLambda12(this, r0);
        goto L30
    L5:
        if (r32 != 79) goto L9;
        goto L6
    }

    public static Player.Commands createPlayerCommandsForCustomErrorState(Player.Commands r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        Player.Commands.Builder r0 = Player.Commands.EMPTY.buildUpon();
        if (r3.contains(16) == false) goto L9;
        r0.add(16);
    L9:
        if (r3.contains(17) == false) goto L12;
        r0.add(17);
    L12:
        if (r3.contains(18) == false) goto L15;
        r0.add(18);
    L15:
        if (r3.contains(21) == false) goto L18;
        r0.add(21);
    L18:
        if (r3.contains(22) == false) goto L21;
        r0.add(22);
    L21:
        if (r3.contains(23) == false) goto L24;
        r0.add(23);
    L24:
        if (r3.contains(30) == false) goto L27;
        r0.add(30);
    L27:
        if (r3.contains(32) == false) goto L30;
        r0.add(32);
    L30:
        return r0.build();
    }

    public static PlayerInfo createPlayerInfoForCustomPlaybackException(PlayerInfo r20, PlaybackException r21) {
        PlayerInfo r1 = r20.copyWithPlaybackState(1, r21);
        SessionPositionInfo r0 = r20.sessionPositionInfo;
        return r1.copyWithSessionPositionInfo(new SessionPositionInfo(r0.positionInfo, r0.isPlayingAd, r0.eventTimeMs, r0.durationMs, 0, 0, 0, r0.currentLiveOffsetMs, r0.contentDurationMs, 0));
    }

    private void dispatchOnPeriodicSessionPositionInfoChanged(SessionPositionInfo r8) {
        ConnectedControllersManager<IBinder> r0 = this.sessionStub.getConnectedControllersManager();
        ImmutableList<MediaSession.ControllerInfo> r1 = r0.getConnectedControllers();
        int r2 = 0;
    L4:
        if (r2 >= r1.size()) goto L15;
        MediaSession.ControllerInfo r3 = r1.get(r2);
        if (r0.getPlaybackException(r3) != null) goto L9;
        dispatchRemoteControllerTaskWithoutReturn(r3, new MediaSessionImpl$$ExternalSyntheticLambda17(r8, r0.isPlayerCommandAvailable(r3, 16), r0.isPlayerCommandAvailable(r3, 17), r3));
    L9:
        r2 = r2 + 1;
        goto L4
    L15:
        this.sessionLegacyStub.getControllerLegacyCbForBroadcast().onPeriodicSessionPositionInfoChanged(0, r8, true, true, 0);     // Catch: RemoteException -> L12
        return;
    L12:
        e = move-exception;
        Log.e("MediaSessionImpl", "Exception in using media1 API", e);
    }

    private void dispatchOnPlayerInfoChanged(PlayerInfo r13, boolean r14, boolean r15) {
        PlayerInfo r132 = this.sessionStub.generateAndCacheUniqueTrackGroupIds(r13);
        ImmutableList<MediaSession.ControllerInfo> r1 = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        int r3 = 0;
    L4:
        if (r3 >= r1.size()) goto L45;
        MediaSession.ControllerInfo r4 = r1.get(r3);
        ConnectedControllersManager<IBinder> r0 = this.sessionStub.getConnectedControllersManager();     // Catch: RemoteException -> L9 DeadObjectException -> L11
        SequencedFutureManager r5 = r0.getSequencedFutureManager(r4);     // Catch: RemoteException -> L9 DeadObjectException -> L11
        if (r5 == null) goto L13;
        int r7 = r5.obtainNextSequenceNumber();     // Catch: RemoteException -> L9 DeadObjectException -> L11
    L16:
        PlayerInfo r52 = r0.getPlayerInfoForPlaybackException(r4);     // Catch: RemoteException -> L9 DeadObjectException -> L11
        if (r52 == null) goto L19;
        boolean r10 = r14;
        boolean r11 = r15;
    L32:
        r3 = r3 + 1;
        r14 = r10;
        r15 = r11;
        goto L4
    L19:
        PlaybackException r6 = r0.getPlaybackException(r4);     // Catch: RemoteException -> L9 DeadObjectException -> L11
        if (r6 == null) goto L22;
        r52 = createPlayerInfoForCustomPlaybackException(r132, r6);     // Catch: RemoteException -> L9 DeadObjectException -> L11
        r0.setPlayerInfoForPlaybackException(r4, r52);     // Catch: RemoteException -> L9 DeadObjectException -> L11
    L22:
        Player.Commands r9 = MediaUtils.intersect(r0.getAvailablePlayerCommands(r4), getPlayerWrapper().getAvailableCommands());     // Catch: RemoteException -> L9 DeadObjectException -> L11
        MediaSession.ControllerCb r62 = (MediaSession.ControllerCb) Assertions.checkStateNotNull(r4.getControllerCb());     // Catch: RemoteException -> L9 DeadObjectException -> L11
        if (r52 != null) goto L26;
        PlayerInfo r8 = r132;
    L25:
        r10 = r14;
        r11 = r15;
        r62.onPlayerInfoChanged(r7, r8, r9, r10, r11);     // Catch: RemoteException -> L29 DeadObjectException -> L34
    L29:
        e = e;
    L30:
        Log.w("MediaSessionImpl", "Exception in " + r4, e);
    L31:
        onDeadObjectException(r4);
        goto L32
    L26:
        r8 = r52;
        goto L25
    L13:
        if (isConnected(r4) == false) goto L33;
        r7 = 0;
        goto L16
    L33:
        return;
    L11:
        r10 = r14;
        r11 = r15;
    L9:
        e = e;
        r10 = r14;
        r11 = r15;
        goto L30
    }

    private ListenableFuture<SessionResult> dispatchRemoteControllerTask(MediaSession.ControllerInfo r5, RemoteControllerTask r6) {
        SequencedFutureManager r1 = this.sessionStub.getConnectedControllersManager().getSequencedFutureManager(r5);     // Catch: RemoteException -> L6 DeadObjectException -> L19
        if (r1 == null) goto L9;
        SequencedFutureManager.SequencedFuture r12 = r1.createSequencedFuture(RESULT_WHEN_CLOSED);     // Catch: RemoteException -> L6 DeadObjectException -> L19
        int r2 = r12.getSequenceNumber();     // Catch: RemoteException -> L6 DeadObjectException -> L19
        ListenableFuture<SessionResult> r13 = r12;
    L13:
        MediaSession.ControllerCb r3 = r5.getControllerCb();     // Catch: RemoteException -> L6 DeadObjectException -> L19
        if (r3 == null) goto L16;
        r6.run(r3, r2);     // Catch: RemoteException -> L6 DeadObjectException -> L19
    L16:
        return r13;
    L9:
        if (isConnected(r5) == false) goto L11;
        r2 = 0;
        r13 = Futures.immediateFuture(new SessionResult(0));     // Catch: RemoteException -> L6 DeadObjectException -> L19
        goto L13
    L11:
        return Futures.immediateFuture(new SessionResult(-100));
    L19:
        onDeadObjectException(r5);
        return Futures.immediateFuture(new SessionResult(-100));
    L6:
        e = move-exception;
        Log.w("MediaSessionImpl", "Exception in " + r5, e);
        return Futures.immediateFuture(new SessionResult(-1));
    }

    private void dispatchRemoteControllerTaskToLegacyStub(RemoteControllerTask r2) {
        r2.run(this.sessionLegacyStub.getControllerLegacyCbForBroadcast(), 0);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        Log.e("MediaSessionImpl", "Exception in using media1 API", e);
    }

    private void handleAvailablePlayerCommandsChanged(Player.Commands r3) {
        this.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(false, false);
        dispatchRemoteControllerTaskWithoutReturn(new MediaSessionImpl$$ExternalSyntheticLambda5(r3));
        dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$$ExternalSyntheticLambda6(this));
    }

    private void notifyPeriodicSessionPositionInfoChangesOnHandler() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.closed == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        SessionPositionInfo r02 = this.playerWrapper.createSessionPositionInfo();
        if (this.onPlayerInfoChangedHandler.hasPendingPlayerInfoChangedUpdate() == false) goto L14;
    L16:
        schedulePeriodicSessionPositionInfoChanges();
        return;
    L14:
        if (MediaUtils.areSessionPositionInfosInSamePeriodOrAd(r02, this.playerInfo.sessionPositionInfo) == false) goto L16;
        dispatchOnPeriodicSessionPositionInfoChanged(r02);
        goto L16
    }

    private void onDeadObjectException(MediaSession.ControllerInfo r1) {
        this.sessionStub.getConnectedControllersManager().removeController(r1);
    }

    private void postOrRunOnApplicationHandler(Runnable r1) {
        Util.postOrRun(getApplicationHandler(), r1);
    }

    private void schedulePeriodicSessionPositionInfoChanges() {
        this.applicationHandler.removeCallbacks(this.periodicSessionPositionInfoUpdateRunnable);
        if (this.isPeriodicPositionUpdateEnabled == true) goto L5;
        return;
    L5:
        if (this.sessionPositionUpdateDelayMs > 0) goto L7;
        return;
    L7:
        if (this.playerWrapper.isPlaying() == false) goto L9;
    L10:
        this.applicationHandler.postDelayed(this.periodicSessionPositionInfoUpdateRunnable, this.sessionPositionUpdateDelayMs);
        return;
    L9:
        if (this.playerWrapper.isLoading() == true) goto L10;
    }

    private void setPlayerInternal(PlayerWrapper r2, PlayerWrapper r3) {
        this.playerWrapper = r3;
        if (r2 == null) goto L5;
        r2.removeListener((Player.Listener) Assertions.checkStateNotNull(this.playerListener));
    L5:
        PlayerListener r0 = new PlayerListener(this, r3);
        r3.addListener(r0);
        this.playerListener = r0;
        dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$$ExternalSyntheticLambda4(r2, r3));
        if (r2 != null) goto L8;
        this.sessionLegacyStub.start();
    L8:
        this.playerInfo = r3.createInitialPlayerInfo();
        handleAvailablePlayerCommandsChanged(r3.getAvailableCommands());
    }

    private void verifyApplicationThread() {
        if (Looper.myLooper() != this.applicationHandler.getLooper()) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Player callback method is called from a wrong thread. See javadoc of MediaSession for details.");
    }

    public Runnable callWithControllerForCurrentRequestSet(MediaSession.ControllerInfo r2, Runnable r3) {
        return new MediaSessionImpl$$ExternalSyntheticLambda22(this, r2, r3);
    }

    public void dispatchRemoteControllerTaskWithoutReturn(MediaSession.ControllerInfo r3, RemoteControllerTask r4) {
        SequencedFutureManager r0 = this.sessionStub.getConnectedControllersManager().getSequencedFutureManager(r3);     // Catch: RemoteException -> L5 DeadObjectException -> L16
        if (r0 == null) goto L8;
        int r02 = r0.obtainNextSequenceNumber();     // Catch: RemoteException -> L5 DeadObjectException -> L16
    L11:
        MediaSession.ControllerCb r1 = r3.getControllerCb();     // Catch: RemoteException -> L5 DeadObjectException -> L16
        if (r1 == null) goto L21;
        r4.run(r1, r02);     // Catch: RemoteException -> L5 DeadObjectException -> L16
        return;
    L21:
        return;
    L8:
        if (isConnected(r3) == false) goto L20;
        r02 = 0;
        goto L11
    L20:
        return;
    L16:
        onDeadObjectException(r3);
        return;
    L5:
        e = move-exception;
        Log.w("MediaSessionImpl", "Exception in " + r3, e);
    }

    public void dispatchRemoteControllerTaskWithoutReturn(RemoteControllerTask r5) {
        ImmutableList<MediaSession.ControllerInfo> r0 = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        int r2 = 0;
    L4:
        if (r2 >= r0.size()) goto L11;
        dispatchRemoteControllerTaskWithoutReturn(r0.get(r2), r5);
        r2 = r2 + 1;
        goto L4
    L11:
        r5.run(this.sessionLegacyStub.getControllerLegacyCbForBroadcast(), 0);     // Catch: RemoteException -> L8
        return;
    L8:
        e = move-exception;
        Log.e("MediaSessionImpl", "Exception in using media1 API", e);
    }

    public Handler getApplicationHandler() {
        return this.applicationHandler;
    }

    public BitmapLoader getBitmapLoader() {
        return this.bitmapLoader;
    }

    public ImmutableList<CommandButton> getCommandButtonsForMediaItems() {
        return this.commandButtonsForMediaItems;
    }

    public Context getContext() {
        return this.context;
    }

    public ImmutableList<CommandButton> getCustomLayout() {
        return this.customLayout;
    }

    public String getId() {
        return this.sessionId;
    }

    public ImmutableList<CommandButton> getMediaButtonPreferences() {
        return this.mediaButtonPreferences;
    }

    public MediaSession.ControllerInfo getMediaNotificationControllerInfo() {
        ImmutableList<MediaSession.ControllerInfo> r0 = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        int r1 = 0;
    L4:
        if (r1 >= r0.size()) goto L9;
        MediaSession.ControllerInfo r2 = r0.get(r1);
        if (isMediaNotificationController(r2) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return r2;
    L9:
        return null;
    }

    public MediaSession.Token getPlatformToken() {
        return this.sessionLegacyStub.getSessionCompat().getSessionToken().getToken();
    }

    public PlaybackException getPlaybackException() {
        return this.playbackException;
    }

    public PlayerInfo getPlayerInfo() {
        return this.playerInfo;
    }

    public PlayerWrapper getPlayerWrapper() {
        return this.playerWrapper;
    }

    public PendingIntent getSessionActivity() {
        return this.sessionActivity;
    }

    public Bundle getSessionExtras() {
        return this.sessionExtras;
    }

    public SessionToken getToken() {
        return this.sessionToken;
    }

    public Uri getUri() {
        return this.sessionUri;
    }

    public void handleMediaControllerPlayRequest(MediaSession.ControllerInfo r6, boolean r7) {
        if (onPlayRequested() == false) goto L29;
        boolean r1 = false;
        if (this.playerWrapper.isCommandAvailable(16) == true) goto L8;
    L10:
        boolean r0 = false;
    L12:
        if (this.playerWrapper.isCommandAvailable(31) == false) goto L14;
    L15:
        r1 = true;
    L16:
        MediaSession.ControllerInfo r62 = resolveControllerInfoForCallback(r6);
        Player.Commands r2 = new Player.Commands.Builder().add(1).build();
        if (r0 == true) goto L22;
        if (r1 == false) goto L22;
        Futures.addCallback((ListenableFuture) Assertions.checkNotNull(this.callback.onPlaybackResumption(this.instance, r62), "Callback.onPlaybackResumption must return a non-null future"), new AnonymousClass1(this, r62, r7, r2), new MediaSessionImpl$$ExternalSyntheticLambda20(this));
        return;
    L22:
        if (r0 == true) goto L24;
        Log.w("MediaSessionImpl", "Play requested without current MediaItem, but playback resumption prevented by missing available commands");
    L24:
        Util.handlePlayButtonAction(this.playerWrapper);
        if (r7 == false) goto L28;
        onPlayerInteractionFinishedOnHandler(r62, r2);
        return;
    L28:
        return;
    L14:
        if (this.playerWrapper.isCommandAvailable(20) == false) goto L16;
    L8:
        if (this.playerWrapper.getCurrentMediaItem() == null) goto L10;
        r0 = true;
        goto L12
    }

    public boolean isConnected(MediaSession.ControllerInfo r2) {
        if (this.sessionStub.getConnectedControllersManager().isConnected(r2) == false) goto L5;
        return true;
    L5:
        if (this.sessionLegacyStub.getConnectedControllersManager().isConnected(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isMediaNotificationController(MediaSession.ControllerInfo r2) {
        if (Objects.equals(r2.getPackageName(), this.context.getPackageName()) == true) goto L5;
    L10:
        return false;
    L5:
        if (r2.getControllerVersion() == 0) goto L10;
        if (r2.getConnectionHints().getBoolean("androidx.media3.session.MediaNotificationManager", false) == false) goto L10;
        return true;
    }

    public boolean isMediaNotificationControllerConnected() {
        return this.isMediaNotificationControllerConnected;
    }

    public boolean isReleased() {
        Object r0 = this.lock;
        monitor-enter(r0);
        boolean r1 = this.closed;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public boolean isSystemUiController(MediaSession.ControllerInfo r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (Objects.equals(r1.getPackageName(), "com.android.systemui") == false) goto L9;
        return true;
    L9:
        return false;
    }

    public ListenableFuture<List<MediaItem>> onAddMediaItemsOnHandler(MediaSession.ControllerInfo r3, List<MediaItem> r4) {
        return (ListenableFuture) Assertions.checkNotNull(this.callback.onAddMediaItems(this.instance, resolveControllerInfoForCallback(r3), r4), "Callback.onAddMediaItems must return a non-null future");
    }

    public MediaSession.ConnectionResult onConnectOnHandler(MediaSession.ControllerInfo r4) {
        if (this.isMediaNotificationControllerConnected == true) goto L5;
    L8:
        MediaSession.ConnectionResult r0 = (MediaSession.ConnectionResult) Assertions.checkNotNull(this.callback.onConnect(this.instance, r4), "Callback.onConnect must return non-null future");
        if (isMediaNotificationController(r4) == true) goto L11;
    L25:
        return r0;
    L11:
        if (r0.isAccepted == false) goto L25;
        this.isMediaNotificationControllerConnected = true;
        ImmutableList<CommandButton> r42 = r0.mediaButtonPreferences;
        if (r42 != null) goto L16;
        r42 = this.instance.getMediaButtonPreferences();
    L16:
        boolean r1 = r42.isEmpty();
        MediaSessionLegacyStub r2 = this.sessionLegacyStub;
        if (r1 == false) goto L23;
        ImmutableList<CommandButton> r43 = r0.customLayout;
        if (r43 != null) goto L22;
        r43 = this.instance.getCustomLayout();
    L22:
        r2.setPlatformCustomLayout(r43);
    L24:
        this.sessionLegacyStub.setAvailableCommands(r0.availableSessionCommands, r0.availablePlayerCommands);
        goto L25
    L23:
        r2.setPlatformMediaButtonPreferences(r42);
        goto L24
    L5:
        if (isSystemUiController(r4) == false) goto L8;
        return this.sessionLegacyStub.getPlatformConnectionResult(this.instance);
    }

    public ListenableFuture<SessionResult> onCustomCommandOnHandler(MediaSession.ControllerInfo r3, SessionCommand r4, Bundle r5) {
        return (ListenableFuture) Assertions.checkNotNull(this.callback.onCustomCommand(this.instance, resolveControllerInfoForCallback(r3), r4, r5), "Callback.onCustomCommandOnHandler must return non-null future");
    }

    public void onDisconnectedOnHandler(MediaSession.ControllerInfo r2) {
        if (this.isMediaNotificationControllerConnected == true) goto L5;
    L10:
        this.callback.onDisconnected(this.instance, r2);
        return;
    L5:
        if (isSystemUiController(r2) == false) goto L8;
        return;
    L8:
        if (isMediaNotificationController(r2) == false) goto L10;
        this.isMediaNotificationControllerConnected = false;
        goto L10
    }

    public boolean onMediaButtonEvent(MediaSession.ControllerInfo r9, Intent r10) {
        KeyEvent r0 = DefaultActionFactory.getKeyEvent(r10);
        ComponentName r1 = r10.getComponent();
        if (Objects.equals(r10.getAction(), "android.intent.action.MEDIA_BUTTON") == false) goto L45;
        if (r1 != null) goto L6;
    L7:
        if (r0 == null) goto L45;
        if (r0.getAction() != 0) goto L45;
        verifyApplicationThread();
        if (this.callback.onMediaButtonEvent(this.instance, r9, r10) == false) goto L14;
        return true;
    L14:
        int r12 = r0.getKeyCode();
        boolean r4 = this.context.getPackageManager().hasSystemFeature("android.software.leanback");
        if (r12 == 79) goto L18;
        if (r12 == 85) goto L18;
        this.mediaPlayPauseKeyHandler.flush();
    L30:
        boolean r42 = false;
    L32:
        if (isMediaNotificationControllerConnected() == true) goto L44;
        if (r12 == 85) goto L35;
        if (r12 == 79) goto L35;
    L39:
        if (r9.getControllerVersion() == 0) goto L42;
        this.sessionLegacyStub.getSessionCompat().getController().dispatchMediaButtonEvent(r0);
        return true;
    L42:
        return false;
    L35:
        if (r42 == false) goto L39;
        this.sessionLegacyStub.onSkipToNext();
        return true;
    L44:
        return applyMediaButtonKeyEvent(r0, r42, r10.getBooleanExtra("androidx.media3.session.NOTIFICATION_DISMISSED_EVENT_KEY", false));
    L18:
        if (r4 == false) goto L20;
    L29:
        this.mediaPlayPauseKeyHandler.flush();
        goto L30
    L20:
        if (r9.getControllerVersion() != 0) goto L29;
        if (r0.getRepeatCount() != 0) goto L29;
        boolean r43 = this.mediaPlayPauseKeyHandler.hasPendingPlayPauseTask();
        MediaPlayPauseKeyHandler r7 = this.mediaPlayPauseKeyHandler;
        if (r43 == false) goto L27;
        r7.clearPendingPlayPauseTask();
        r42 = true;
        goto L32
    L27:
        r7.setPendingPlayPauseTask(r9, r0);
        return true;
    L6:
        if (Objects.equals(r1.getPackageName(), this.context.getPackageName()) == true) goto L7;
    L45:
        return false;
    }

    public void onNotificationRefreshRequired() {
        Util.postOrRun(this.mainHandler, new MediaSessionImpl$$ExternalSyntheticLambda19(this));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onPlayRequested() {
        if (Looper.myLooper() == Looper.getMainLooper()) goto L10;
        SettableFuture r0 = SettableFuture.create();
        this.mainHandler.post(new MediaSessionImpl$$ExternalSyntheticLambda21(this, r0));
        return ((Boolean) r0.get()).booleanValue();
    L7:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return false;
    L10:
        return true;
    }

    public int onPlayerCommandRequestOnHandler(MediaSession.ControllerInfo r3, int r4) {
        return this.callback.onPlayerCommandRequest(this.instance, resolveControllerInfoForCallback(r3), r4);
    }

    public void onPlayerInteractionFinishedOnHandler(MediaSession.ControllerInfo r3, Player.Commands r4) {
        this.callback.onPlayerInteractionFinished(this.instance, resolveControllerInfoForCallback(r3), r4);
    }

    public void onPostConnectOnHandler(MediaSession.ControllerInfo r2) {
        if (this.isMediaNotificationControllerConnected == true) goto L5;
    L7:
        this.callback.onPostConnect(this.instance, r2);
        return;
    L5:
        if (isSystemUiController(r2) == false) goto L7;
    }

    public ListenableFuture<MediaSession.MediaItemsWithStartPosition> onSetMediaItemsOnHandler(MediaSession.ControllerInfo r8, List<MediaItem> r9, int r10, long r11) {
        return (ListenableFuture) Assertions.checkNotNull(this.callback.onSetMediaItems(this.instance, resolveControllerInfoForCallback(r8), r9, r10, r11), "Callback.onSetMediaItems must return a non-null future");
    }

    public ListenableFuture<SessionResult> onSetRatingOnHandler(MediaSession.ControllerInfo r3, Rating r4) {
        return (ListenableFuture) Assertions.checkNotNull(this.callback.onSetRating(this.instance, resolveControllerInfoForCallback(r3), r4), "Callback.onSetRating must return non-null future");
    }

    public ListenableFuture<SessionResult> onSetRatingOnHandler(MediaSession.ControllerInfo r3, String r4, Rating r5) {
        return (ListenableFuture) Assertions.checkNotNull(this.callback.onSetRating(this.instance, resolveControllerInfoForCallback(r3), r4, r5), "Callback.onSetRating must return non-null future");
    }

    public void release() {
        Log.i("MediaSessionImpl", "Release " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.8.1] [" + Util.DEVICE_DEBUG_INFO + "] [" + MediaLibraryInfo.registeredModules() + "]");
        Object r0 = this.lock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.closed == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        this.closed = true;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        this.mediaPlayPauseKeyHandler.clearPendingPlayPauseTask();
        this.applicationHandler.removeCallbacksAndMessages(null);
        Util.postOrRun(this.applicationHandler, new MediaSessionImpl$$ExternalSyntheticLambda0(this));     // Catch: Exception -> L15
    L17:
        this.sessionLegacyStub.release();
        this.sessionStub.release();
        return;
    L15:
        e = move-exception;
        Log.w("MediaSessionImpl", "Exception thrown while closing", e);
        goto L17
    }

    public MediaSession.ControllerInfo resolveControllerInfoForCallback(MediaSession.ControllerInfo r2) {
        if (this.isMediaNotificationControllerConnected == true) goto L5;
    L8:
        return r2;
    L5:
        if (isSystemUiController(r2) == false) goto L8;
        return (MediaSession.ControllerInfo) Assertions.checkNotNull(getMediaNotificationControllerInfo());
    }

    public ListenableFuture<SessionResult> sendCustomCommand(MediaSession.ControllerInfo r2, SessionCommand r3, Bundle r4) {
        return dispatchRemoteControllerTask(r2, new MediaSessionImpl$$ExternalSyntheticLambda18(r3, r4));
    }

    public void setPlayer(Player r3) {
        if (r3 != this.playerWrapper.getWrappedPlayer()) goto L5;
        return;
    L5:
        setPlayerInternal(this.playerWrapper, new PlayerWrapper(r3));
    }

    public boolean shouldPlayIfSuppressed() {
        return this.playIfSuppressed;
    }

    public void triggerPlayerInfoUpdate() {
        this.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
    }
}
