package androidx.media3.session;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.core.util.ObjectsCompat;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.DefaultAudioSink$StreamEventCallbackV29$$ExternalSyntheticLambda0;
import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaDescriptionCompat;
import androidx.media3.session.legacy.MediaMetadataCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import androidx.media3.session.legacy.PlaybackStateCompat;
import androidx.media3.session.legacy.RatingCompat;
import androidx.media3.session.legacy.VolumeProviderCompat;
import com.google.android.gms.cast.MediaStatus;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.url._UrlKt;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes4.dex */
class MediaSessionLegacyStub extends MediaSessionCompat.Callback {
    private static final int PENDING_INTENT_FLAG_MUTABLE = 0;
    private Player.Commands availablePlayerCommands;
    private SessionCommands availableSessionCommands;
    private final ComponentName broadcastReceiverComponentName;
    private final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager;
    private final ConnectionTimeoutHandler connectionTimeoutHandler;
    private volatile long connectionTimeoutMs;
    private final ControllerLegacyCbForBroadcast controllerLegacyCbForBroadcast;
    private ImmutableList<CommandButton> customLayout;
    private PlaybackException customPlaybackException;
    private Bundle legacyExtras;
    private ImmutableList<CommandButton> mediaButtonPreferences;
    private FutureCallback<Bitmap> pendingBitmapLoadCallback;
    private final boolean playIfSuppressed;
    private Player.Commands playerCommandsForErrorState;
    private final MediaButtonReceiver runtimeBroadcastReceiver;
    private final MediaSessionCompat sessionCompat;
    private int sessionFlags;
    private final MediaSessionImpl sessionImpl;
    private final MediaSessionManager sessionManager;
    private VolumeProviderCompat volumeProviderCompat;

    /* renamed from: androidx.media3.session.MediaSessionLegacyStub$1, reason: invalid class name */
    public class AnonymousClass1 implements FutureCallback<MediaSession.MediaItemsWithStartPosition> {
        final /* synthetic */ MediaSessionLegacyStub this$0;
        final /* synthetic */ MediaSession.ControllerInfo val$controller;
        final /* synthetic */ boolean val$play;

        /* renamed from: $r8$lambda$kpNnPM6VcsiyFYPO-apJaq82Fco, reason: not valid java name */
        public static /* synthetic */ void m898$r8$lambda$kpNnPM6VcsiyFYPOapJaq82Fco(AnonymousClass1 r3, MediaSession.MediaItemsWithStartPosition r4, boolean r5, MediaSession.ControllerInfo r6) {
            PlayerWrapper r0 = MediaSessionLegacyStub.access$300(r3.this$0).getPlayerWrapper();
            MediaUtils.setMediaItemsWithStartIndexAndPosition(r0, r4);
            int r42 = r0.getPlaybackState();
            if (r42 != 1) goto L6;
            r0.prepareIfCommandAvailable();
        L8:
            if (r5 == false) goto L10;
            r0.playIfCommandAvailable();
        L10:
            MediaSessionLegacyStub.access$300(r3.this$0).onPlayerInteractionFinishedOnHandler(r6, new Player.Commands.Builder().addAll(new int[]{31, 2}).addIf(1, r5).build());
            return;
        L6:
            if (r42 != 4) goto L8;
            r0.seekToDefaultPositionIfCommandAvailable();
            goto L8
        }

        public AnonymousClass1(MediaSessionLegacyStub r1, MediaSession.ControllerInfo r2, boolean r3) {
            this.this$0 = r1;
            this.val$controller = r2;
            this.val$play = r3;
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable r1) {
        }

        /* renamed from: onSuccess, reason: avoid collision after fix types in other method */
        public void onSuccess2(MediaSession.MediaItemsWithStartPosition r6) {
            Handler r0 = MediaSessionLegacyStub.access$300(this.this$0).getApplicationHandler();
            MediaSessionImpl r1 = MediaSessionLegacyStub.access$300(this.this$0);
            MediaSession.ControllerInfo r2 = this.val$controller;
            Util.postOrRun(r0, r1.callWithControllerForCurrentRequestSet(r2, new MediaSessionLegacyStub$1$$ExternalSyntheticLambda0(this, r6, this.val$play, r2)));
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public /* bridge */ /* synthetic */ void onSuccess(MediaSession.MediaItemsWithStartPosition r1) {
            onSuccess2(r1);
        }
    }

    /* renamed from: androidx.media3.session.MediaSessionLegacyStub$2, reason: invalid class name */
    public class AnonymousClass2 implements FutureCallback<List<MediaItem>> {
        final /* synthetic */ MediaSessionLegacyStub this$0;
        final /* synthetic */ MediaSession.ControllerInfo val$controller;
        final /* synthetic */ int val$index;

        /* renamed from: $r8$lambda$tqnZ3Onpq0lgDodWXxj-GI6JFFc, reason: not valid java name */
        public static /* synthetic */ void m899$r8$lambda$tqnZ3Onpq0lgDodWXxjGI6JFFc(AnonymousClass2 r2, int r3, List r4, MediaSession.ControllerInfo r5) {
            MediaSessionLegacyStub r0 = r2.this$0;
            if (r3 != (-1)) goto L5;
            MediaSessionLegacyStub.access$300(r0).getPlayerWrapper().addMediaItems(r4);
        L6:
            MediaSessionLegacyStub.access$300(r2.this$0).onPlayerInteractionFinishedOnHandler(r5, new Player.Commands.Builder().add(20).build());
            return;
        L5:
            MediaSessionLegacyStub.access$300(r0).getPlayerWrapper().addMediaItems(r3, r4);
            goto L6
        }

        public AnonymousClass2(MediaSessionLegacyStub r1, MediaSession.ControllerInfo r2, int r3) {
            this.this$0 = r1;
            this.val$controller = r2;
            this.val$index = r3;
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable r1) {
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public /* bridge */ /* synthetic */ void onSuccess(List<MediaItem> r1) {
            onSuccess2(r1);
        }

        /* renamed from: onSuccess, reason: avoid collision after fix types in other method */
        public void onSuccess2(List<MediaItem> r6) {
            Handler r0 = MediaSessionLegacyStub.access$300(this.this$0).getApplicationHandler();
            MediaSessionImpl r1 = MediaSessionLegacyStub.access$300(this.this$0);
            MediaSession.ControllerInfo r2 = this.val$controller;
            Util.postOrRun(r0, r1.callWithControllerForCurrentRequestSet(r2, new MediaSessionLegacyStub$2$$ExternalSyntheticLambda0(this, this.val$index, r6, r2)));
        }
    }

    /* renamed from: androidx.media3.session.MediaSessionLegacyStub$3, reason: invalid class name */
    public class AnonymousClass3 extends VolumeProviderCompat {
        final /* synthetic */ Handler val$handler;
        final /* synthetic */ int val$legacyVolumeFlag;
        final /* synthetic */ PlayerWrapper val$player;

        /* renamed from: $r8$lambda$TjbUq-tUwysPI6p6wigghYjknkg, reason: not valid java name */
        public static /* synthetic */ void m900$r8$lambda$TjbUqtUwysPI6p6wigghYjknkg(PlayerWrapper r3, int r4, int r5) {
            if (r3.isCommandAvailable(26) == true) goto L8;
            if (r3.isCommandAvailable(34) == true) goto L8;
            return;
        L8:
            if (r4 == (-100)) goto L43;
            if (r4 == (-1)) goto L37;
            if (r4 == 1) goto L31;
            if (r4 == 100) goto L25;
            if (r4 == 101) goto L19;
            Log.w("VolumeProviderCompat", "onAdjustVolume: Ignoring unknown direction: " + r4);
            return;
        L19:
            if (r3.isCommandAvailable(34) == false) goto L22;
            r3.setDeviceMuted(!r3.isDeviceMutedWithCommandCheck(), r5);
            return;
        L22:
            r3.setDeviceMuted(!r3.isDeviceMutedWithCommandCheck());
            return;
        L25:
            if (r3.isCommandAvailable(34) == false) goto L28;
            r3.setDeviceMuted(false, r5);
            return;
        L28:
            r3.setDeviceMuted(false);
            return;
        L31:
            if (r3.isCommandAvailable(34) == false) goto L34;
            r3.increaseDeviceVolume(r5);
            return;
        L34:
            r3.increaseDeviceVolume();
            return;
        L37:
            if (r3.isCommandAvailable(34) == false) goto L40;
            r3.decreaseDeviceVolume(r5);
            return;
        L40:
            r3.decreaseDeviceVolume();
            return;
        L43:
            if (r3.isCommandAvailable(34) == false) goto L46;
            r3.setDeviceMuted(true, r5);
            return;
        L46:
            r3.setDeviceMuted(true);
        }

        public static /* synthetic */ void $r8$lambda$c44gYbuaJQRDsnsgVdnkYipByuw(PlayerWrapper r2, int r3, int r4) {
            if (r2.isCommandAvailable(25) == true) goto L8;
            if (r2.isCommandAvailable(33) == true) goto L8;
            return;
        L8:
            if (r2.isCommandAvailable(33) == false) goto L11;
            r2.setDeviceVolume(r3, r4);
            return;
        L11:
            r2.setDeviceVolume(r3);
        }

        public AnonymousClass3(int r1, int r2, int r3, String r4, Handler r5, PlayerWrapper r6, int r7) {
            this.val$handler = r5;
            this.val$player = r6;
            this.val$legacyVolumeFlag = r7;
            super(r1, r2, r3, r4);
        }

        @Override // androidx.media3.session.legacy.VolumeProviderCompat
        public void onAdjustVolume(int r4) {
            Util.postOrRun(this.val$handler, new MediaSessionLegacyStub$3$$ExternalSyntheticLambda1(this.val$player, r4, this.val$legacyVolumeFlag));
        }

        @Override // androidx.media3.session.legacy.VolumeProviderCompat
        public void onSetVolumeTo(int r4) {
            Util.postOrRun(this.val$handler, new MediaSessionLegacyStub$3$$ExternalSyntheticLambda0(this.val$player, r4, this.val$legacyVolumeFlag));
        }
    }

    public static final class Api31 {
        public static void setMediaButtonBroadcastReceiver(MediaSessionCompat r1, ComponentName r2) {
            ((android.media.session.MediaSession) Assertions.checkNotNull(r1.getMediaSession())).setMediaButtonBroadcastReceiver(r2);     // Catch: IllegalArgumentException -> L4
            return;
        L4:
            e = move-exception;
            if (Build.MANUFACTURER.equals("motorola") == false) goto L9;
            Log.e("MediaSessionLegacyStub", "caught IllegalArgumentException on a motorola device when attempting to set the media button broadcast receiver. See https://github.com/androidx/media/issues/1730 for details.", e);
            return;
        L9:
            throw e;
        }
    }

    public static class ConnectionTimeoutHandler extends Handler {
        private final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager;

        public ConnectionTimeoutHandler(Looper r1, ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> r2) {
            super(r1);
            this.connectedControllersManager = r2;
        }

        public void disconnectControllerAfterTimeout(MediaSession.ControllerInfo r2, long r3) {
            removeMessages(WebSocketProtocol.CLOSE_CLIENT_GOING_AWAY, r2);
            sendMessageDelayed(obtainMessage(WebSocketProtocol.CLOSE_CLIENT_GOING_AWAY, r2), r3);
        }

        @Override // android.os.Handler
        public void handleMessage(Message r3) {
            MediaSession.ControllerInfo r32 = (MediaSession.ControllerInfo) r3.obj;
            if (this.connectedControllersManager.isConnected(r32) == false) goto L6;
            ((MediaSession.ControllerCb) Assertions.checkStateNotNull(r32.getControllerCb())).onDisconnected(0);
            this.connectedControllersManager.removeController(r32);
            return;
        }
    }

    public static final class ControllerLegacyCb implements MediaSession.ControllerCb {
        private final MediaSessionManager.RemoteUserInfo remoteUserInfo;

        public ControllerLegacyCb(MediaSessionManager.RemoteUserInfo r1) {
            this.remoteUserInfo = r1;
        }

        public boolean equals(Object r3) {
            if (this != r3) goto L5;
            return true;
        L5:
            if (r3 != null) goto L7;
            return false;
        L7:
            if (r3.getClass() == ControllerLegacyCb.class) goto L10;
            return false;
        L10:
            return Objects.equals(this.remoteUserInfo, ((ControllerLegacyCb) r3).remoteUserInfo);
        }

        public int hashCode() {
            return ObjectsCompat.hash(new Object[]{this.remoteUserInfo});
        }
    }

    public final class ControllerLegacyCbForBroadcast implements MediaSession.ControllerCb {
        private long lastDurationMs;
        private String lastMediaId;
        private MediaMetadata lastMediaMetadata;
        private Uri lastMediaUri;
        final /* synthetic */ MediaSessionLegacyStub this$0;

        /* renamed from: androidx.media3.session.MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$1, reason: invalid class name */
        public class AnonymousClass1 implements FutureCallback<Bitmap> {
            final /* synthetic */ ControllerLegacyCbForBroadcast this$1;
            final /* synthetic */ long val$newDurationMs;
            final /* synthetic */ String val$newMediaId;
            final /* synthetic */ MediaMetadata val$newMediaMetadata;
            final /* synthetic */ Uri val$newMediaUri;

            public AnonymousClass1(ControllerLegacyCbForBroadcast r1, MediaMetadata r2, String r3, Uri r4, long r5) {
                this.this$1 = r1;
                this.val$newMediaMetadata = r2;
                this.val$newMediaId = r3;
                this.val$newMediaUri = r4;
                this.val$newDurationMs = r5;
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable r2) {
                if (this == MediaSessionLegacyStub.access$1500(this.this$1.this$0)) goto L5;
                return;
            L5:
                Log.w("MediaSessionLegacyStub", MediaSessionLegacyStub.access$1600(r2));
            }

            /* renamed from: onSuccess, reason: avoid collision after fix types in other method */
            public void onSuccess2(Bitmap r8) {
                if (this == MediaSessionLegacyStub.access$1500(this.this$1.this$0)) goto L5;
                return;
            L5:
                MediaSessionLegacyStub.access$1700(MediaSessionLegacyStub.access$700(this.this$1.this$0), LegacyConversions.convertToMediaMetadataCompat(this.val$newMediaMetadata, this.val$newMediaId, this.val$newMediaUri, this.val$newDurationMs, r8));
                MediaSessionLegacyStub.access$300(this.this$1.this$0).onNotificationRefreshRequired();
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public /* bridge */ /* synthetic */ void onSuccess(Bitmap r1) {
                onSuccess2(r1);
            }
        }

        /* renamed from: $r8$lambda$FOEjVJh95pMxD-mpHvg3o9UcGz4, reason: not valid java name */
        public static /* synthetic */ void m901$r8$lambda$FOEjVJh95pMxDmpHvg3o9UcGz4(ControllerLegacyCbForBroadcast r1, AtomicInteger r2, List r3, List r4) {
            r1.getClass();
            if (r2.incrementAndGet() != r3.size()) goto L6;
            r1.handleBitmapFuturesAllCompletedAndSetQueue(r4, r3);
            return;
        }

        public ControllerLegacyCbForBroadcast(MediaSessionLegacyStub r3) {
            this.this$0 = r3;
            this.lastMediaMetadata = MediaMetadata.EMPTY;
            this.lastMediaId = _UrlKt.FRAGMENT_ENCODE_SET;
            this.lastDurationMs = -9223372036854775807L;
        }

        public static /* synthetic */ void access$1800(ControllerLegacyCbForBroadcast r0, Timeline r1) {
            r0.updateQueue(r1);
        }

        private void handleBitmapFuturesAllCompletedAndSetQueue(List<ListenableFuture<Bitmap>> r6, List<MediaItem> r7) {
            ArrayList r0 = new ArrayList();
            int r1 = 0;
        L4:
            if (r1 >= r6.size()) goto L15;
            ListenableFuture<Bitmap> r2 = r6.get(r1);
            if (r2 != null) goto L17;
        L13:
            Bitmap r22 = null;
        L14:
            r0.add(LegacyConversions.convertToQueueItem(r7.get(r1), r1, r22));
            r1 = r1 + 1;
            goto L4
        L17:
            r22 = (Bitmap) Futures.getDone(r2);     // Catch: ExecutionException -> L9 Throwable -> L11
        L11:
            e = move-exception;
            Log.d("MediaSessionLegacyStub", "Failed to get bitmap", e);
            goto L13
        L15:
            MediaSessionLegacyStub.access$1100(MediaSessionLegacyStub.access$700(this.this$0), r0);
        }

        private void updateMetadataIfChanged() {
            PlayerWrapper r0 = MediaSessionLegacyStub.access$300(this.this$0).getPlayerWrapper();
            MediaItem r1 = r0.getCurrentMediaItemWithCommandCheck();
            MediaMetadata r2 = r0.getMediaMetadataWithCommandCheck();
            if (r0.isCurrentMediaItemLiveWithCommandCheck() == false) goto L6;
            long r3 = -9223372036854775807L;
        L5:
            long r5 = r3;
            if (r1 == null) goto L10;
            String r02 = r1.mediaId;
        L9:
            String r32 = r02;
            Bitmap r9 = null;
            if (r1 == null) goto L16;
            Uri r03 = r1.requestMetadata.mediaUri;
            if (r03 == null) goto L16;
            Uri r4 = r03;
        L18:
            if (Objects.equals(this.lastMediaMetadata, r2) == true) goto L20;
        L26:
            this.lastMediaId = r32;
            this.lastMediaUri = r4;
            this.lastMediaMetadata = r2;
            this.lastDurationMs = r5;
            ListenableFuture<Bitmap> r04 = MediaSessionLegacyStub.access$300(this.this$0).getBitmapLoader().loadBitmapFromMetadata(r2);
            if (r04 == null) goto L32;
            MediaSessionLegacyStub.access$1502(this.this$0, null);
            if (r04.isDone() == true) goto L40;
            long r7 = r5;
            Uri r6 = r4;
            MediaMetadata r42 = r2;
            String r52 = r32;
            ControllerLegacyCbForBroadcast r33 = this;
            MediaSessionLegacyStub.access$1502(this.this$0, new AnonymousClass1(r33, r42, r52, r6, r7));
            FutureCallback r10 = MediaSessionLegacyStub.access$1500(r33.this$0);
            Handler r12 = MediaSessionLegacyStub.access$300(r33.this$0).getApplicationHandler();
            Objects.requireNonNull(r12);
            Futures.addCallback(r04, r10, new DefaultAudioSink$StreamEventCallbackV29$$ExternalSyntheticLambda0(r12));
        L38:
            MediaSessionLegacyStub.access$1700(MediaSessionLegacyStub.access$700(r33.this$0), LegacyConversions.convertToMediaMetadataCompat(r42, r52, r6, r7, r9));
            return;
        L40:
            r9 = (Bitmap) Futures.getDone(r04);     // Catch: Throwable -> L33 CancellationException -> L35
        L33:
            e = move-exception;
            Log.w("MediaSessionLegacyStub", MediaSessionLegacyStub.access$1600(e));
        L32:
            r7 = r5;
            r52 = r32;
            r6 = r4;
            r33 = this;
            r42 = r2;
            goto L38
        L20:
            if (Objects.equals(this.lastMediaId, r32) == false) goto L26;
            if (Objects.equals(this.lastMediaUri, r4) == false) goto L26;
            if (this.lastDurationMs != r5) goto L26;
            return;
        L16:
            r4 = null;
            goto L18
        L10:
            r02 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L9
        L6:
            r3 = r0.getDurationWithCommandCheck();
            goto L5
        }

        private void updateQueue(Timeline r8) {
            if (MediaSessionLegacyStub.access$1000(this.this$0) == true) goto L5;
        L16:
            MediaSessionLegacyStub.access$1100(MediaSessionLegacyStub.access$700(this.this$0), null);
            return;
        L5:
            if (r8.isEmpty() == true) goto L16;
            List<MediaItem> r82 = LegacyConversions.convertToMediaItemList(r8);
            ArrayList r0 = new ArrayList();
            int r3 = 0;
            MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0 r4 = new MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0(this, new AtomicInteger(0), r82, r0);
        L9:
            if (r3 >= r82.size()) goto L15;
            MediaMetadata r2 = r82.get(r3).mediaMetadata;
            if (r2.artworkData != null) goto L13;
            r0.add(null);
            r4.run();
        L14:
            r3 = r3 + 1;
            goto L9
        L13:
            ListenableFuture<Bitmap> r22 = MediaSessionLegacyStub.access$300(this.this$0).getBitmapLoader().decodeBitmap(r2.artworkData);
            r0.add(r22);
            Handler r5 = MediaSessionLegacyStub.access$300(this.this$0).getApplicationHandler();
            Objects.requireNonNull(r5);
            r22.addListener(r4, new DefaultAudioSink$StreamEventCallbackV29$$ExternalSyntheticLambda0(r5));
            goto L14
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onAudioAttributesChanged(int r1, AudioAttributes r2) {
            if (MediaSessionLegacyStub.access$300(this.this$0).getPlayerWrapper().getDeviceInfo().playbackType != 0) goto L6;
            int r12 = LegacyConversions.getLegacyStreamType(r2);
            MediaSessionLegacyStub.access$700(this.this$0).setPlaybackToLocal(r12);
            return;
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onAvailableCommandsChangedFromPlayer(int r1, Player.Commands r2) {
            if (skipLegacySessionPlaybackStateUpdates() == false) goto L5;
            return;
        L5:
            PlayerWrapper r12 = MediaSessionLegacyStub.access$300(this.this$0).getPlayerWrapper();
            this.this$0.maybeUpdateFlags(r12);
            this.this$0.updateLegacySessionPlaybackState(r12);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onDeviceInfoChanged(int r2, DeviceInfo r3) {
            PlayerWrapper r22 = MediaSessionLegacyStub.access$300(this.this$0).getPlayerWrapper();
            MediaSessionLegacyStub.access$1302(this.this$0, MediaSessionLegacyStub.access$1400(r22));
            if (MediaSessionLegacyStub.access$1300(this.this$0) != null) goto L6;
            int r23 = LegacyConversions.getLegacyStreamType(r22.getAudioAttributesWithCommandCheck());
            MediaSessionLegacyStub.access$700(this.this$0).setPlaybackToLocal(r23);
            return;
        L6:
            MediaSessionLegacyStub.access$700(this.this$0).setPlaybackToRemote(MediaSessionLegacyStub.access$1300(this.this$0));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onDeviceVolumeChanged(int r1, int r2, boolean r3) {
            if (MediaSessionLegacyStub.access$1300(this.this$0) == null) goto L9;
            VolumeProviderCompat r0 = MediaSessionLegacyStub.access$1300(this.this$0);
            if (r3 == false) goto L7;
            r2 = 0;
        L7:
            r0.setCurrentVolume(r2);
            return;
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onDisconnected(int r1) {
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onIsPlayingChanged(int r1, boolean r2) {
            if (skipLegacySessionPlaybackStateUpdates() == false) goto L5;
            return;
        L5:
            MediaSessionLegacyStub r0 = this.this$0;
            r0.updateLegacySessionPlaybackState(MediaSessionLegacyStub.access$300(r0).getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onMediaItemTransition(int r1, MediaItem r2, int r3) {
            if (skipLegacySessionPlaybackStateUpdates() == false) goto L5;
            return;
        L5:
            updateMetadataIfChanged();
            MediaSessionLegacyStub r12 = this.this$0;
            if (r2 != null) goto L8;
            MediaSessionLegacyStub.access$700(r12).setRatingType(0);
        L9:
            MediaSessionLegacyStub r0 = this.this$0;
            r0.updateLegacySessionPlaybackState(MediaSessionLegacyStub.access$300(r0).getPlayerWrapper());
            return;
        L8:
            MediaSessionLegacyStub.access$700(r12).setRatingType(LegacyConversions.getRatingCompatStyle(r2.mediaMetadata.userRating));
            goto L9
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onMediaMetadataChanged(int r1, MediaMetadata r2) {
            if (skipLegacySessionPlaybackStateUpdates() == false) goto L5;
            return;
        L5:
            updateMetadataIfChanged();
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPeriodicSessionPositionInfoChanged(int r1, SessionPositionInfo r2, boolean r3, boolean r4, int r5) {
            if (skipLegacySessionPlaybackStateUpdates() == true) goto L6;
            MediaSessionLegacyStub r0 = this.this$0;
            r0.updateLegacySessionPlaybackState(MediaSessionLegacyStub.access$300(r0).getPlayerWrapper());
            return;
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlayWhenReadyChanged(int r1, boolean r2, int r3) {
            if (skipLegacySessionPlaybackStateUpdates() == false) goto L5;
            return;
        L5:
            MediaSessionLegacyStub r0 = this.this$0;
            r0.updateLegacySessionPlaybackState(MediaSessionLegacyStub.access$300(r0).getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlaybackParametersChanged(int r1, PlaybackParameters r2) {
            if (skipLegacySessionPlaybackStateUpdates() == false) goto L5;
            return;
        L5:
            MediaSessionLegacyStub r0 = this.this$0;
            r0.updateLegacySessionPlaybackState(MediaSessionLegacyStub.access$300(r0).getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlaybackStateChanged(int r1, int r2, PlaybackException r3) {
            if (skipLegacySessionPlaybackStateUpdates() == false) goto L5;
            return;
        L5:
            MediaSessionLegacyStub r0 = this.this$0;
            r0.updateLegacySessionPlaybackState(MediaSessionLegacyStub.access$300(r0).getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlaybackSuppressionReasonChanged(int r1, int r2) {
            if (skipLegacySessionPlaybackStateUpdates() == false) goto L5;
            return;
        L5:
            MediaSessionLegacyStub r0 = this.this$0;
            r0.updateLegacySessionPlaybackState(MediaSessionLegacyStub.access$300(r0).getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlayerChanged(int r3, PlayerWrapper r4, PlayerWrapper r5) {
            Timeline r0 = r5.getCurrentTimelineWithCommandCheck();
            if (r4 != null) goto L5;
        L6:
            onTimelineChanged(r3, r0, 0);
        L7:
            MediaMetadata r02 = r5.getPlaylistMetadataWithCommandCheck();
            if (r4 != null) goto L10;
        L11:
            onPlaylistMetadataChanged(r3, r02);
        L12:
            MediaMetadata r03 = r5.getMediaMetadataWithCommandCheck();
            if (r4 != null) goto L15;
        L16:
            onMediaMetadataChanged(r3, r03);
        L17:
            if (r4 != null) goto L19;
        L20:
            onShuffleModeEnabledChanged(r3, r5.getShuffleModeEnabled());
        L21:
            if (r4 != null) goto L23;
        L24:
            onRepeatModeChanged(r3, r5.getRepeatMode());
        L25:
            onDeviceInfoChanged(r3, r5.getDeviceInfo());
            this.this$0.maybeUpdateFlags(r5);
            MediaItem r04 = r5.getCurrentMediaItemWithCommandCheck();
            if (r4 != null) goto L28;
        L34:
            onMediaItemTransition(r3, r04, 3);
            return;
        L28:
            if (Objects.equals(r4.getCurrentMediaItemWithCommandCheck(), r04) == false) goto L34;
            if (skipLegacySessionPlaybackStateUpdates() == true) goto L36;
            this.this$0.updateLegacySessionPlaybackState(r5);
            return;
        L36:
            return;
        L23:
            if (r4.getRepeatMode() == r5.getRepeatMode()) goto L25;
        L19:
            if (r4.getShuffleModeEnabled() == r5.getShuffleModeEnabled()) goto L21;
        L15:
            if (Objects.equals(r4.getMediaMetadataWithCommandCheck(), r03) == true) goto L17;
        L10:
            if (Objects.equals(r4.getPlaylistMetadataWithCommandCheck(), r02) == true) goto L12;
        L5:
            if (Objects.equals(r4.getCurrentTimelineWithCommandCheck(), r0) == true) goto L7;
            goto L6
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlayerError(int r1, PlaybackException r2) {
            if (skipLegacySessionPlaybackStateUpdates() == false) goto L5;
            return;
        L5:
            MediaSessionLegacyStub r0 = this.this$0;
            r0.updateLegacySessionPlaybackState(MediaSessionLegacyStub.access$300(r0).getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlaylistMetadataChanged(int r1, MediaMetadata r2) {
            if (skipLegacySessionPlaybackStateUpdates() == true) goto L10;
            CharSequence r12 = MediaSessionLegacyStub.access$700(this.this$0).getController().getQueueTitle();
            CharSequence r22 = r2.title;
            if (TextUtils.equals(r12, r22) == true) goto L9;
            MediaSessionLegacyStub r0 = this.this$0;
            MediaSessionLegacyStub.access$1200(r0, MediaSessionLegacyStub.access$700(r0), r22);
            return;
        L9:
            return;
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPositionDiscontinuity(int r1, Player.PositionInfo r2, Player.PositionInfo r3, int r4) {
            if (skipLegacySessionPlaybackStateUpdates() == false) goto L5;
            return;
        L5:
            MediaSessionLegacyStub r0 = this.this$0;
            r0.updateLegacySessionPlaybackState(MediaSessionLegacyStub.access$300(r0).getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onRepeatModeChanged(int r1, int r2) {
            MediaSessionLegacyStub.access$700(this.this$0).setRepeatMode(LegacyConversions.convertToPlaybackStateCompatRepeatMode(r2));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onShuffleModeEnabledChanged(int r1, boolean r2) {
            MediaSessionLegacyStub.access$700(this.this$0).setShuffleMode(LegacyConversions.convertToPlaybackStateCompatShuffleMode(r2));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onTimelineChanged(int r1, Timeline r2, int r3) {
            if (skipLegacySessionPlaybackStateUpdates() == false) goto L5;
            return;
        L5:
            updateQueue(r2);
            updateMetadataIfChanged();
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void sendCustomCommand(int r1, SessionCommand r2, Bundle r3) {
            MediaSessionLegacyStub.access$700(this.this$0).sendSessionEvent(r2.customAction, r3);
        }

        public boolean skipLegacySessionPlaybackStateUpdates() {
            if (MediaSessionLegacyStub.access$200(this.this$0) == null) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public final class MediaButtonReceiver extends BroadcastReceiver {
        final /* synthetic */ MediaSessionLegacyStub this$0;

        private MediaButtonReceiver(MediaSessionLegacyStub r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ MediaButtonReceiver(MediaSessionLegacyStub r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r2, Intent r3) {
            if (Objects.equals(r3.getAction(), "android.intent.action.MEDIA_BUTTON") == false) goto L10;
            KeyEvent r22 = (KeyEvent) r3.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if (r22 != null) goto L8;
            return;
        L8:
            MediaSessionLegacyStub.access$700(this.this$0).getController().dispatchMediaButtonEvent(r22);
            return;
        }
    }

    public interface SessionTask {
        void run(MediaSession.ControllerInfo r1);
    }

    /* renamed from: $r8$lambda$55oygNI1y9dO6MZ7FK2rdOV-BOA, reason: not valid java name */
    public static /* synthetic */ void m887$r8$lambda$55oygNI1y9dO6MZ7FK2rdOVBOA(MediaSessionLegacyStub r0, int r1, MediaSession.ControllerInfo r2) {
        r0.sessionImpl.getPlayerWrapper().setRepeatMode(LegacyConversions.convertToRepeatMode(r1));
    }

    /* renamed from: $r8$lambda$60ruWMvO-ti4R-ZaClqtwcgKmf0, reason: not valid java name */
    public static /* synthetic */ void m888$r8$lambda$60ruWMvOti4RZaClqtwcgKmf0(MediaSessionLegacyStub r2, PlayerWrapper r3) {
        r2.sessionCompat.setPlaybackState(r2.createPlaybackStateCompat(r3));
        ControllerLegacyCbForBroadcast r22 = r2.controllerLegacyCbForBroadcast;
        if (r3.getAvailableCommands().contains(17) == false) goto L5;
        Timeline r32 = r3.getCurrentTimeline();
    L6:
        ControllerLegacyCbForBroadcast.access$1800(r22, r32);
        return;
    L5:
        r32 = Timeline.EMPTY;
        goto L6
    }

    /* renamed from: $r8$lambda$6S9qKQqrCsE93xIY-ftgXmdIOPE, reason: not valid java name */
    public static /* synthetic */ void m889$r8$lambda$6S9qKQqrCsE93xIYftgXmdIOPE(SessionTask r2, MediaSession.ControllerInfo r3) {
        r2.run(r3);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        Log.w("MediaSessionLegacyStub", "Exception in " + r3, e);
    }

    public static /* synthetic */ void $r8$lambda$AvdgiGL7_83iPUD8IOHY1cmgAao(MediaSessionLegacyStub r0, MediaSession.ControllerInfo r1) {
        r0.sessionImpl.getPlayerWrapper().stop();
    }

    public static /* synthetic */ void $r8$lambda$BuydQq0IpBecHYccVYlPbF4_nm4(MediaSessionLegacyStub r3, MediaDescriptionCompat r4, MediaSession.ControllerInfo r5) {
        r3.getClass();
        String r42 = r4.getMediaId();
        if (TextUtils.isEmpty(r42) == false) goto L6;
        Log.w("MediaSessionLegacyStub", "onRemoveQueueItem(): Media ID shouldn't be null");
        return;
    L6:
        PlayerWrapper r32 = r3.sessionImpl.getPlayerWrapper();
        if (r32.isCommandAvailable(17) == true) goto L10;
        Log.w("MediaSessionLegacyStub", "Can't remove item by ID without COMMAND_GET_TIMELINE being available");
        return;
    L10:
        Timeline r52 = r32.getCurrentTimeline();
        Timeline.Window r0 = new Timeline.Window();
        int r1 = 0;
    L12:
        if (r1 >= r52.getWindowCount()) goto L18;
        if (TextUtils.equals(r52.getWindow(r1, r0).mediaItem.mediaId, r42) == true) goto L15;
        r1 = r1 + 1;
        goto L12
    L15:
        r32.removeMediaItem(r1);
        return;
    }

    public static /* synthetic */ void $r8$lambda$HnAgMFJfV4nxGMJOBEDf56jzDsY(MediaSessionLegacyStub r0, long r1, MediaSession.ControllerInfo r3) {
        r0.sessionImpl.getPlayerWrapper().seekToDefaultPosition((int) r1);
    }

    /* renamed from: $r8$lambda$PyZ5CZWdFBE-1cPMhJhVKTvAF8g, reason: not valid java name */
    public static /* synthetic */ void m890$r8$lambda$PyZ5CZWdFBE1cPMhJhVKTvAF8g(MediaSessionLegacyStub r0, MediaSession.ControllerInfo r1) {
        r0.sessionImpl.getPlayerWrapper().seekToNextMediaItem();
    }

    public static /* synthetic */ void $r8$lambda$R860oOR7Ur9nrRVIHyJ9YFQgwfc(MediaSessionLegacyStub r0, SessionCommand r1, Bundle r2, MediaSession.ControllerInfo r3) {
        MediaSessionImpl r02 = r0.sessionImpl;
        if (r2 != null) goto L6;
        r2 = Bundle.EMPTY;
    L6:
        ignoreFuture(r02.onCustomCommandOnHandler(r3, r1, r2));
    }

    public static /* synthetic */ void $r8$lambda$Wul7mI6nggethBr1Sk_3t_BmRXA(MediaSessionLegacyStub r1, MediaSession.ControllerInfo r2) {
        r1.sessionImpl.handleMediaControllerPlayRequest(r2, true);
    }

    public static /* synthetic */ void $r8$lambda$XiUkN6tUdeCl0TnoxdphKY2ODbY(MediaSessionLegacyStub r0, MediaSession.ControllerInfo r1) {
        r0.sessionImpl.getPlayerWrapper().seekBack();
    }

    public static /* synthetic */ void $r8$lambda$Y6DY0gQr0UFcIDfia_a7LYaKTqo(MediaSessionLegacyStub r0, SessionCommand r1, Bundle r2, ResultReceiver r3, MediaSession.ControllerInfo r4) {
        MediaSessionImpl r02 = r0.sessionImpl;
        if (r2 != null) goto L5;
        r2 = Bundle.EMPTY;
    L5:
        ListenableFuture<SessionResult> r03 = r02.onCustomCommandOnHandler(r4, r1, r2);
        if (r3 == null) goto L9;
        sendCustomCommandResultWhenReady(r3, r03);
        return;
    L9:
        ignoreFuture(r03);
    }

    public static /* synthetic */ void $r8$lambda$Y8unc_uOiYzIESWnW3VkT2QF3WY(MediaSessionLegacyStub r1, MediaDescriptionCompat r2, int r3, MediaSession.ControllerInfo r4) {
        r1.getClass();
        if (TextUtils.isEmpty(r2.getMediaId()) == false) goto L6;
        Log.w("MediaSessionLegacyStub", "onAddQueueItem(): Media ID shouldn't be empty");
        return;
    L6:
        MediaItem r22 = LegacyConversions.convertToMediaItem(r2);
        Futures.addCallback(r1.sessionImpl.onAddMediaItemsOnHandler(r4, ImmutableList.of(r22)), new AnonymousClass2(r1, r4, r3), MoreExecutors.directExecutor());
    }

    public static /* synthetic */ void $r8$lambda$ZSqHkRizxzPrXnnj721mstdQaus(MediaSessionLegacyStub r0, MediaSession.ControllerInfo r1) {
        Util.handlePlayPauseButtonAction(r0.sessionImpl.getPlayerWrapper(), r0.sessionImpl.shouldPlayIfSuppressed());
    }

    public static /* synthetic */ void $r8$lambda$ZTzY66AdxIgVMyJLBzZESMRj1ok(MediaSessionLegacyStub r0, MediaSession.ControllerInfo r1) {
        r0.sessionImpl.getPlayerWrapper().prepare();
    }

    public static /* synthetic */ void $r8$lambda$_GWKNqsuBTKVJCOQXfaxqGjRgmE(MediaSessionLegacyStub r0, MediaSession.ControllerInfo r1) {
        r0.sessionImpl.getPlayerWrapper().seekToPrevious();
    }

    public static /* synthetic */ void $r8$lambda$aLt2E_c0Xman1mPYM0YtnMqO67s(MediaSessionLegacyStub r0, MediaSession.ControllerInfo r1) {
        r0.sessionImpl.getPlayerWrapper().seekForward();
    }

    /* renamed from: $r8$lambda$aTjja303fgWDzl3_a1R-qedDdMY, reason: not valid java name */
    public static /* synthetic */ void m891$r8$lambda$aTjja303fgWDzl3_a1RqedDdMY(MediaSessionLegacyStub r1, Rating r2, MediaSession.ControllerInfo r3) {
        MediaItem r0 = r1.sessionImpl.getPlayerWrapper().getCurrentMediaItemWithCommandCheck();
        if (r0 != null) goto L5;
        return;
    L5:
        ignoreFuture(r1.sessionImpl.onSetRatingOnHandler(r3, r0.mediaId, r2));
    }

    /* renamed from: $r8$lambda$eENUD_3k9r8-fJtUq2YoekcNMX4, reason: not valid java name */
    public static /* synthetic */ void m892$r8$lambda$eENUD_3k9r8fJtUq2YoekcNMX4(MediaSessionLegacyStub r0, MediaSession.ControllerInfo r1) {
        r0.sessionImpl.getPlayerWrapper().seekToPreviousMediaItem();
    }

    public static /* synthetic */ void $r8$lambda$foMxgWuixLvLUh8nE0tMtV4Pw5M(MediaSessionLegacyStub r0, MediaSession.ControllerInfo r1) {
        r0.sessionImpl.getPlayerWrapper().seekToNext();
    }

    public static /* synthetic */ void $r8$lambda$i_hiXgnlh3yefF3weSKAVCwfWdA(MediaSessionLegacyStub r1, PlayerWrapper r2) {
        r1.sessionCompat.setPlaybackState(r1.createPlaybackStateCompat(r2));
    }

    /* renamed from: $r8$lambda$jEXiPM_X0-ewiisZycqB7fSB4fE, reason: not valid java name */
    public static /* synthetic */ void m893$r8$lambda$jEXiPM_X0ewiisZycqB7fSB4fE(MediaSessionLegacyStub r0, MediaSession.ControllerInfo r1) {
        Util.handlePauseButtonAction(r0.sessionImpl.getPlayerWrapper());
    }

    /* renamed from: $r8$lambda$kOReCQo2wKCUmAX6VK_-5D2agCk, reason: not valid java name */
    public static /* synthetic */ void m894$r8$lambda$kOReCQo2wKCUmAX6VK_5D2agCk(MediaSessionLegacyStub r0, int r1, MediaSession.ControllerInfo r2) {
        r0.sessionImpl.getPlayerWrapper().setShuffleModeEnabled(LegacyConversions.convertToShuffleModeEnabled(r1));
    }

    public static /* synthetic */ void $r8$lambda$mk9a_qh3f89yHUBeGey9WUPhflA(MediaSessionLegacyStub r2, SessionCommand r3, int r4, MediaSessionManager.RemoteUserInfo r5, SessionTask r6) {
        if (r2.sessionImpl.isReleased() == false) goto L6;
        return;
    L6:
        if (r2.sessionCompat.isActive() == true) goto L13;
        StringBuilder r22 = new StringBuilder("Ignore incoming session command before initialization. command=");
        if (r3 != null) goto L10;
        Object r32 = Integer.valueOf(r4);
    L11:
        r22.append(r32);
        r22.append(", pid=");
        r22.append(r5.getPid());
        Log.w("MediaSessionLegacyStub", r22.toString());
        return;
    L10:
        r32 = r3.customAction;
        goto L11
    L13:
        MediaSession.ControllerInfo r52 = r2.tryGetController(r5);
        if (r52 == null) goto L32;
        ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> r23 = r2.connectedControllersManager;
        if (r3 == null) goto L22;
        if (r23.isSessionCommandAvailable(r52, r3) == false) goto L33;
    L29:
        r6.run(r52);     // Catch: RemoteException -> L26
        return;
    L26:
        e = move-exception;
        Log.w("MediaSessionLegacyStub", "Exception in " + r52, e);
        return;
    L33:
        return;
    L22:
        if (r23.isSessionCommandAvailable(r52, r4) == true) goto L29;
        return;
    }

    /* renamed from: $r8$lambda$qy7t8wVgOXS-nxDglYMxV5fGWZk, reason: not valid java name */
    public static /* synthetic */ void m895$r8$lambda$qy7t8wVgOXSnxDglYMxV5fGWZk(MediaSessionLegacyStub r6, MediaItem r7, boolean r8, MediaSession.ControllerInfo r9) {
        Futures.addCallback(r6.sessionImpl.onSetMediaItemsOnHandler(r9, ImmutableList.of(r7), -1, -9223372036854775807L), new AnonymousClass1(r6, r9, r8), MoreExecutors.directExecutor());
    }

    /* renamed from: $r8$lambda$rPY-D8FRyevyRCL35R9o2o7VVfs, reason: not valid java name */
    public static /* synthetic */ void m896$r8$lambda$rPYD8FRyevyRCL35R9o2o7VVfs(MediaSessionLegacyStub r0, long r1, MediaSession.ControllerInfo r3) {
        r0.sessionImpl.getPlayerWrapper().seekTo(r1);
    }

    public static /* synthetic */ void $r8$lambda$rRDgN57RSpxBH0FKFeMlgJGmWPE(MediaSessionLegacyStub r0, float r1, MediaSession.ControllerInfo r2) {
        r0.sessionImpl.getPlayerWrapper().setPlaybackSpeed(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: $r8$lambda$see-tukGCxOFvWd11mohCEIZn3Y, reason: not valid java name */
    public static /* synthetic */ void m897$r8$lambda$seetukGCxOFvWd11mohCEIZn3Y(ListenableFuture r2, ResultReceiver r3) {
        SessionResult r22 = (SessionResult) Assertions.checkNotNull((SessionResult) r2.get(), "SessionResult must not be null");     // Catch: InterruptedException -> L5 ExecutionException -> L7 CancellationException -> L9
    L13:
        r3.send(r22.resultCode, r22.extras);
        return;
    L5:
        e = e;
    L11:
        Log.w("MediaSessionLegacyStub", "Custom command failed", e);
        r22 = new SessionResult(-1);
    L9:
        e = move-exception;
        Log.w("MediaSessionLegacyStub", "Custom command cancelled", e);
        r22 = new SessionResult(1);
    L7:
        e = e;
        goto L11
    }

    public static /* synthetic */ void $r8$lambda$yD4aSGJcUf9LNqu46DhlW5QP0T4(MediaSessionLegacyStub r2, int r3, MediaSessionManager.RemoteUserInfo r4, SessionTask r5, boolean r6) {
        if (r2.sessionImpl.isReleased() == false) goto L6;
        return;
    L6:
        if (r2.sessionCompat.isActive() == true) goto L9;
        Log.w("MediaSessionLegacyStub", "Ignore incoming player command before initialization. command=" + r3 + ", pid=" + r4.getPid());
        return;
    L9:
        MediaSession.ControllerInfo r42 = r2.tryGetController(r4);
        if (r42 != null) goto L13;
        return;
    L13:
        if (r2.connectedControllersManager.isPlayerCommandAvailable(r42, r3) == true) goto L21;
        if (r3 == 1) goto L17;
        return;
    L17:
        if (r2.sessionImpl.getPlayerWrapper().getPlayWhenReady() == true) goto L28;
        Log.w("MediaSessionLegacyStub", "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
        return;
    L28:
        return;
    L21:
        if (r2.sessionImpl.onPlayerCommandRequestOnHandler(r42, r3) != 0) goto L32;
        r2.sessionImpl.callWithControllerForCurrentRequestSet(r42, new MediaSessionLegacyStub$$ExternalSyntheticLambda26(r5, r42)).run();
        if (r6 == false) goto L29;
        r2.sessionImpl.onPlayerInteractionFinishedOnHandler(r42, new Player.Commands.Builder().add(r3).build());
        return;
    L29:
        return;
    }

    static {
        if (Build.VERSION.SDK_INT < 31) goto L5;
        int r0 = 33554432;
    L6:
        PENDING_INTENT_FLAG_MUTABLE = r0;
        return;
    L5:
        r0 = 0;
        goto L6
    }

    public MediaSessionLegacyStub(MediaSessionImpl r9, Uri r10, Handler r11, Bundle r12, boolean r13, ImmutableList<CommandButton> r14, ImmutableList<CommandButton> r15, SessionCommands r16, Player.Commands r17, Bundle r18) {
        this.sessionImpl = r9;
        this.playIfSuppressed = r13;
        this.customLayout = r14;
        this.mediaButtonPreferences = r15;
        this.availableSessionCommands = r16;
        this.availablePlayerCommands = r17;
        this.legacyExtras = new Bundle(r18);
        Context r0 = r9.getContext();
        this.sessionManager = MediaSessionManager.getSessionManager(r0);
        this.controllerLegacyCbForBroadcast = new ControllerLegacyCbForBroadcast(this);
        ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> r1 = new ConnectedControllersManager(r9);
        this.connectedControllersManager = r1;
        this.connectionTimeoutMs = 300000;
        this.connectionTimeoutHandler = new ConnectionTimeoutHandler(r9.getApplicationHandler().getLooper(), r1);
        if (r15.isEmpty() == true) goto L5;
        updateCustomLayoutAndLegacyExtrasForMediaButtonPreferences();
    L5:
        ComponentName r19 = queryPackageManagerForMediaButtonReceiver(r0);
        this.broadcastReceiverComponentName = r19;
        if (r19 != null) goto L8;
    L11:
        ComponentName r3 = getServiceComponentByAction(r0, "androidx.media3.session.MediaLibraryService");
        if (r3 != null) goto L14;
        r3 = getServiceComponentByAction(r0, "androidx.media3.session.MediaSessionService");
    L14:
        if (r3 != null) goto L16;
    L18:
        boolean r4 = false;
    L19:
        Intent r5 = new Intent("android.intent.action.MEDIA_BUTTON", r10);
        if (r3 != null) goto L22;
        MediaButtonReceiver r32 = new MediaButtonReceiver(this, null);
        this.runtimeBroadcastReceiver = r32;
        IntentFilter r42 = new IntentFilter("android.intent.action.MEDIA_BUTTON");
        r42.addDataScheme((String) Util.castNonNull(r10.getScheme()));
        Util.registerReceiverNotExported(r0, r32, r42);
        r5.setPackage(r0.getPackageName());
        PendingIntent r102 = PendingIntent.getBroadcast(r0, 0, r5, PENDING_INTENT_FLAG_MUTABLE);
        r3 = new ComponentName(r0, r0.getClass());
    L30:
        String r132 = TextUtils.join(".", new String[]{"androidx.media3.session.id", r9.getId()});
        int r52 = Build.VERSION.SDK_INT;
        if (r52 < 31) goto L34;
        r3 = null;
    L34:
        if (r52 >= 31) goto L37;
        PendingIntent r172 = r102;
    L38:
        MediaSessionCompat r43 = new MediaSessionCompat(r0, r132, r3, r172, r12);
        this.sessionCompat = r43;
        if (r52 < 31) goto L42;
        if (r19 == null) goto L42;
        Api31.setMediaButtonBroadcastReceiver(r43, r19);
    L42:
        PendingIntent r92 = r9.getSessionActivity();
        if (r92 == null) goto L45;
        r43.setSessionActivity(r92);
    L45:
        r43.setCallback(this, r11);
        return;
    L37:
        r172 = null;
        goto L38
    L22:
        r5.setComponent(r3);
        if (r4 == true) goto L25;
        r102 = PendingIntent.getBroadcast(r0, 0, r5, PENDING_INTENT_FLAG_MUTABLE);
    L29:
        this.runtimeBroadcastReceiver = null;
        goto L30
    L25:
        if (Build.VERSION.SDK_INT < 26) goto L27;
        r102 = PendingIntent.getForegroundService(r0, 0, r5, PENDING_INTENT_FLAG_MUTABLE);
        goto L29
    L27:
        r102 = PendingIntent.getService(r0, 0, r5, PENDING_INTENT_FLAG_MUTABLE);
        goto L29
    L16:
        if (r3.equals(r19) == true) goto L18;
        r4 = true;
        goto L19
    L8:
        if (Build.VERSION.SDK_INT < 31) goto L11;
        r4 = false;
        r3 = r19;
        goto L19
    }

    public static /* synthetic */ boolean access$1000(MediaSessionLegacyStub r0) {
        return r0.isQueueEnabled();
    }

    public static /* synthetic */ void access$1100(MediaSessionCompat r0, List r1) {
        setQueue(r0, r1);
    }

    public static /* synthetic */ void access$1200(MediaSessionLegacyStub r0, MediaSessionCompat r1, CharSequence r2) {
        r0.setQueueTitle(r1, r2);
    }

    public static /* synthetic */ VolumeProviderCompat access$1300(MediaSessionLegacyStub r0) {
        return r0.volumeProviderCompat;
    }

    public static /* synthetic */ VolumeProviderCompat access$1302(MediaSessionLegacyStub r0, VolumeProviderCompat r1) {
        r0.volumeProviderCompat = r1;
        return r1;
    }

    public static /* synthetic */ VolumeProviderCompat access$1400(PlayerWrapper r0) {
        return createVolumeProviderCompat(r0);
    }

    public static /* synthetic */ FutureCallback access$1500(MediaSessionLegacyStub r0) {
        return r0.pendingBitmapLoadCallback;
    }

    public static /* synthetic */ FutureCallback access$1502(MediaSessionLegacyStub r0, FutureCallback r1) {
        r0.pendingBitmapLoadCallback = r1;
        return r1;
    }

    public static /* synthetic */ String access$1600(Throwable r0) {
        return getBitmapLoadErrorMessage(r0);
    }

    public static /* synthetic */ void access$1700(MediaSessionCompat r0, MediaMetadataCompat r1) {
        setMetadata(r0, r1);
    }

    public static /* synthetic */ PlaybackException access$200(MediaSessionLegacyStub r0) {
        return r0.customPlaybackException;
    }

    public static /* synthetic */ MediaSessionImpl access$300(MediaSessionLegacyStub r0) {
        return r0.sessionImpl;
    }

    public static /* synthetic */ MediaSessionCompat access$700(MediaSessionLegacyStub r0) {
        return r0.sessionCompat;
    }

    private static long convertCommandToPlaybackStateActions(int r1, boolean r2) {
        if (r1 != 1) goto L5;
        if (r2 == false) goto L40;
        return 516;
    L40:
        return 514;
    L5:
        if (r1 != 2) goto L7;
        return 16384;
    L7:
        if (r1 != 3) goto L9;
        return 1;
    L9:
        if (r1 == 31) goto L31;
        switch(r1) {
            case 5: goto L29;
            case 6: goto L27;
            case 7: goto L27;
            case 8: goto L25;
            case 9: goto L25;
            case 10: goto L23;
            case 11: goto L21;
            case 12: goto L19;
            case 13: goto L17;
            case 14: goto L15;
            case 15: goto L13;
            default: goto L11;
        };
    L11:
        return 0;
    L13:
        return MediaStatus.COMMAND_STREAM_TRANSFER;
    L15:
        return 2621440;
    L17:
        return 4194304;
    L19:
        return 64;
    L21:
        return 8;
    L23:
        return MediaStatus.COMMAND_EDIT_TRACKS;
    L25:
        return 32;
    L27:
        return 16;
    L29:
        return 256;
    L31:
        return 240640;
    }

    private static MediaItem createMediaItemForMediaRequest(String r1, Uri r2, String r3, Bundle r4) {
        MediaItem.Builder r0 = new MediaItem.Builder();
        if (r1 != null) goto L6;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return r0.setMediaId(r1).setRequestMetadata(new MediaItem.RequestMetadata.Builder().setMediaUri(r2).setSearchQuery(r3).setExtras(r4).build()).build();
    }

    private PlaybackStateCompat createPlaybackStateCompat(PlayerWrapper r19) {
        PlaybackException r2 = this.customPlaybackException;
        if (r2 != null) goto L6;
        r2 = r19.getPlayerError();
    L6:
        boolean r4 = true;
        if (r19.isCommandAvailable(16) == true) goto L9;
    L11:
        boolean r3 = false;
    L12:
        if (r2 == null) goto L14;
    L17:
        if (r2 == null) goto L20;
        int r6 = 7;
    L19:
        int r8 = r6;
        Player.Commands r62 = r19.getAvailableCommands();
        Player.Commands r7 = this.playerCommandsForErrorState;
        if (r7 == null) goto L24;
        Player.Commands r63 = MediaUtils.intersect(r7, r62);
    L25:
        long r9 = 128;
        int r72 = 0;
    L27:
        if (r72 >= r63.size()) goto L30;
        r9 = r9 | convertCommandToPlaybackStateActions(r63.get(r72), r4);
        r72 = r72 + 1;
        goto L27
    L30:
        if (this.mediaButtonPreferences.isEmpty() == true) goto L35;
        if (this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS") == true) goto L35;
        r9 = r9 & (-17);
    L35:
        if (this.mediaButtonPreferences.isEmpty() == false) goto L37;
    L39:
        if (r3 == true) goto L41;
        r9 = r9 & (-257);
    L41:
        long r14 = r9;
        if (r19.isCommandAvailable(17) == false) goto L44;
        long r92 = LegacyConversions.convertToQueueItemId(r19.getCurrentMediaItemIndex());
    L45:
        float r42 = r19.getPlaybackParameters().speed;
        if (r19.isPlaying() == false) goto L49;
        if (r3 == false) goto L49;
        float r11 = r42;
    L51:
        if (r2 == null) goto L53;
        Bundle r12 = new Bundle(r2.extras);
    L54:
        r12.putAll(this.legacyExtras);
        r12.putFloat("EXO_SPEED", r42);
        MediaItem r43 = r19.getCurrentMediaItemWithCommandCheck();
        if (r43 != null) goto L57;
    L59:
        if (r3 == false) goto L61;
        long r64 = r19.getCurrentPosition();
    L62:
        if (r3 == false) goto L65;
        long r32 = r19.getBufferedPosition();
    L66:
        PlaybackStateCompat.Builder r1 = new PlaybackStateCompat.Builder().setState(r8, r64, r11, SystemClock.elapsedRealtime()).setActions(r14).setActiveQueueItemId(r92).setBufferedPosition(r32).setExtras(r12);
        if (this.customLayout.size() > 0) goto L72;
        if (r2 == null) goto L71;
        r1.setErrorMessage(LegacyConversions.convertToLegacyErrorCode(r2), r2.getMessage());
    L71:
        return r1.build();
    L72:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.customLayout.get(0));
        throw null;
    L65:
        r32 = -1;
        goto L66
    L61:
        r64 = -1;
        goto L62
    L57:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r43.mediaId) == true) goto L59;
        r12.putString("androidx.media.PlaybackStateCompat.Extras.KEY_MEDIA_ID", r43.mediaId);
        goto L59
    L53:
        r12 = new Bundle();
    L49:
        r11 = 0.0f;
        goto L51
    L44:
        r92 = -1;
        goto L45
    L37:
        if (this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT") == true) goto L39;
        r9 = r9 & (-33);
        goto L39
    L24:
        r63 = MediaUtils.intersect(this.availablePlayerCommands, r62);
        goto L25
    L20:
        r6 = LegacyConversions.convertToPlaybackStateCompatState(r19, r4);
        goto L19
    L14:
        if (Util.shouldShowPlayButton(r19, this.playIfSuppressed) == true) goto L17;
        r4 = false;
        goto L17
    L9:
        if (r19.isCurrentMediaItemLive() == true) goto L11;
        r3 = true;
        goto L12
    }

    private static VolumeProviderCompat createVolumeProviderCompat(PlayerWrapper r9) {
        if (r9.getDeviceInfo().playbackType != 0) goto L6;
        return null;
    L6:
        Player.Commands r0 = r9.getAvailableCommands();
        if (r0.containsAny(new int[]{26, 34}) == true) goto L9;
        int r02 = 0;
    L11:
        int r2 = r02;
        Handler r6 = new Handler(r9.getApplicationLooper());
        int r4 = r9.getDeviceVolumeWithCommandCheck();
        DeviceInfo r03 = r9.getDeviceInfo();
        return new AnonymousClass3(r2, r03.maxVolume, r4, r03.routingControllerId, r6, r9, 1);
    L9:
        if (r0.containsAny(new int[]{25, 33}) == false) goto L12;
        r02 = 2;
        goto L11
    L12:
        r02 = 1;
        goto L11
    }

    private void dispatchSessionTaskWithPlayerCommand(int r8, SessionTask r9, MediaSessionManager.RemoteUserInfo r10, boolean r11) {
        if (this.sessionImpl.isReleased() == false) goto L5;
        return;
    L5:
        if (r10 != null) goto L8;
        Log.d("MediaSessionLegacyStub", "RemoteUserInfo is null, ignoring command=" + r8);
        return;
    L8:
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new MediaSessionLegacyStub$$ExternalSyntheticLambda22(this, r8, r10, r9, r11));
    }

    private void dispatchSessionTaskWithSessionCommand(int r3, SessionTask r4) {
        dispatchSessionTaskWithSessionCommandInternal(null, r3, r4, this.sessionCompat.getCurrentControllerInfo());
    }

    private void dispatchSessionTaskWithSessionCommand(SessionCommand r3, SessionTask r4) {
        dispatchSessionTaskWithSessionCommandInternal(r3, 0, r4, this.sessionCompat.getCurrentControllerInfo());
    }

    private void dispatchSessionTaskWithSessionCommandInternal(SessionCommand r8, int r9, SessionTask r10, MediaSessionManager.RemoteUserInfo r11) {
        if (r11 != null) goto L8;
        StringBuilder r7 = new StringBuilder("RemoteUserInfo is null, ignoring command=");
        Object r82 = r8;
        if (r8 != null) goto L6;
        r82 = Integer.valueOf(r9);
    L6:
        r7.append(r82);
        Log.d("MediaSessionLegacyStub", r7.toString());
        return;
    L8:
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new MediaSessionLegacyStub$$ExternalSyntheticLambda23(this, r8, r9, r11, r10));
    }

    private static String getBitmapLoadErrorMessage(Throwable r2) {
        return "Failed to load bitmap: " + r2.getMessage();
    }

    private static ComponentName getServiceComponentByAction(Context r2, String r3) {
        PackageManager r0 = r2.getPackageManager();
        Intent r1 = new Intent(r3);
        r1.setPackage(r2.getPackageName());
        List<ResolveInfo> r32 = r0.queryIntentServices(r1, 0);
        if (r32 != null) goto L5;
        return null;
    L5:
        if (r32.isEmpty() == true) goto L11;
        ServiceInfo r22 = r32.get(0).serviceInfo;
        return new ComponentName(r22.packageName, r22.name);
    L11:
        return null;
    }

    private void handleMediaRequest(MediaItem r3, boolean r4) {
        dispatchSessionTaskWithPlayerCommand(31, new MediaSessionLegacyStub$$ExternalSyntheticLambda21(this, r3, r4), this.sessionCompat.getCurrentControllerInfo(), false);
    }

    private void handleOnAddQueueItem(MediaDescriptionCompat r3, int r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r4 == (-1)) goto L7;
        if (r4 >= 0) goto L7;
        return;
    L7:
        dispatchSessionTaskWithPlayerCommand(20, new MediaSessionLegacyStub$$ExternalSyntheticLambda13(this, r3, r4), this.sessionCompat.getCurrentControllerInfo(), false);
    }

    private static <T> void ignoreFuture(Future<T> r0) {
    }

    private boolean isQueueEnabled() {
        PlayerWrapper r0 = this.sessionImpl.getPlayerWrapper();
        if (this.availablePlayerCommands.contains(17) == true) goto L5;
        return false;
    L5:
        if (r0.getAvailableCommands().contains(17) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static ComponentName queryPackageManagerForMediaButtonReceiver(Context r3) {
        PackageManager r0 = r3.getPackageManager();
        Intent r1 = new Intent("android.intent.action.MEDIA_BUTTON");
        r1.setPackage(r3.getPackageName());
        List<ResolveInfo> r02 = r0.queryBroadcastReceivers(r1, 0);
        if (r02.size() != 1) goto L7;
        ActivityInfo r32 = r02.get(0).activityInfo;
        return new ComponentName(r32.packageName, r32.name);
    L7:
        if (r02.size() <= 1) goto L11;
        Log.w("MediaSessionLegacyStub", "Expected 1 broadcast receiver that handles android.intent.action.MEDIA_BUTTON, found " + r02.size() + ". Using runtime media button receiver instead.");
        return null;
    L11:
        return null;
    }

    private static void sendCustomCommandResultWhenReady(ResultReceiver r1, ListenableFuture<SessionResult> r2) {
        r2.addListener(new MediaSessionLegacyStub$$ExternalSyntheticLambda27(r2, r1), MoreExecutors.directExecutor());
    }

    private static void setMediaButtonReceiver(MediaSessionCompat r0, PendingIntent r1) {
        r0.setMediaButtonReceiver(r1);
    }

    private static void setMetadata(MediaSessionCompat r0, MediaMetadataCompat r1) {
        r0.setMetadata(r1);
    }

    private static void setQueue(MediaSessionCompat r0, List<MediaSessionCompat.QueueItem> r1) {
        r0.setQueue(r1);
    }

    private void setQueueTitle(MediaSessionCompat r1, CharSequence r2) {
        if (isQueueEnabled() == true) goto L6;
        r2 = null;
    L6:
        r1.setQueueTitle(r2);
    }

    private MediaSession.ControllerInfo tryGetController(MediaSessionManager.RemoteUserInfo r10) {
        MediaSession.ControllerInfo r0 = this.connectedControllersManager.getController(r10);
        if (r0 != null) goto L9;
        ControllerLegacyCb r6 = new ControllerLegacyCb(r10);
        MediaSession.ControllerInfo r1 = new MediaSession.ControllerInfo(r10, 0, 0, this.sessionManager.isTrustedForMediaControl(r10), r6, Bundle.EMPTY, 0);
        MediaSession.ConnectionResult r102 = this.sessionImpl.onConnectOnHandler(r1);
        if (r102.isAccepted == true) goto L8;
        r6.onDisconnected(0);
        return null;
    L8:
        this.connectedControllersManager.addController(r1.getRemoteUserInfo(), r1, r102.availableSessionCommands, r102.availablePlayerCommands);
        this.sessionImpl.onPostConnectOnHandler(r1);
        r0 = r1;
    L9:
        this.connectionTimeoutHandler.disconnectControllerAfterTimeout(r0, this.connectionTimeoutMs);
        return r0;
    }

    private void updateCustomLayoutAndLegacyExtrasForMediaButtonPreferences() {
        ImmutableList<CommandButton> r0 = this.mediaButtonPreferences;
        SessionCommands r1 = this.availableSessionCommands;
        Player.Commands r2 = this.playerCommandsForErrorState;
        if (r2 != null) goto L6;
        r2 = this.availablePlayerCommands;
    L6:
        this.customLayout = CommandButton.getCustomLayoutFromMediaButtonPreferences(CommandButton.copyWithUnavailableButtonsDisabled(r0, r1, r2), true, true);
        this.legacyExtras.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", !CommandButton.containsButtonForSlot(r0, 2));
        this.legacyExtras.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", !CommandButton.containsButtonForSlot(this.customLayout, 3));
    }

    public ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> getConnectedControllersManager() {
        return this.connectedControllersManager;
    }

    public MediaSession.ControllerCb getControllerLegacyCbForBroadcast() {
        return this.controllerLegacyCbForBroadcast;
    }

    public MediaSession.ConnectionResult getPlatformConnectionResult(MediaSession r2) {
        return new MediaSession.ConnectionResult.AcceptedResultBuilder(r2).setAvailableSessionCommands(this.availableSessionCommands).setAvailablePlayerCommands(this.availablePlayerCommands).setCustomLayout(this.customLayout).setMediaButtonPreferences(this.mediaButtonPreferences).build();
    }

    public MediaSessionCompat getSessionCompat() {
        return this.sessionCompat;
    }

    public void handleMediaPlayPauseOnHandler(MediaSessionManager.RemoteUserInfo r3) {
        dispatchSessionTaskWithPlayerCommand(1, new MediaSessionLegacyStub$$ExternalSyntheticLambda24(this), r3, true);
    }

    public void maybeUpdateFlags(PlayerWrapper r2) {
        if (r2.isCommandAvailable(20) == false) goto L5;
        int r22 = 4;
    L7:
        if (this.sessionFlags == r22) goto L10;
        this.sessionFlags = r22;
        this.sessionCompat.setFlags(r22);
        return;
    L10:
        return;
    L5:
        r22 = 0;
        goto L7
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onAddQueueItem(MediaDescriptionCompat r2) {
        handleOnAddQueueItem(r2, -1);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onAddQueueItem(MediaDescriptionCompat r1, int r2) {
        handleOnAddQueueItem(r1, r2);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onCommand(String r3, Bundle r4, ResultReceiver r5) {
        Assertions.checkStateNotNull(r3);
        if (r3.equals("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST") == false) goto L6;
        return;
    L6:
        if (r3.equals("androidx.media3.session.SESSION_COMMAND_REQUEST_SESSION3_TOKEN") == false) goto L10;
        if (r5 == null) goto L10;
        r5.send(0, this.sessionImpl.getToken().toBundle());
        return;
    L10:
        SessionCommand r0 = new SessionCommand(r3, Bundle.EMPTY);
        dispatchSessionTaskWithSessionCommand(r0, new MediaSessionLegacyStub$$ExternalSyntheticLambda18(this, r0, r4, r5));
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onCustomAction(String r3, Bundle r4) {
        if (r3.equals("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST") == false) goto L5;
        return;
    L5:
        SessionCommand r0 = new SessionCommand(r3, Bundle.EMPTY);
        dispatchSessionTaskWithSessionCommand(r0, new MediaSessionLegacyStub$$ExternalSyntheticLambda11(this, r0, r4));
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onFastForward() {
        dispatchSessionTaskWithPlayerCommand(12, new MediaSessionLegacyStub$$ExternalSyntheticLambda0(this), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public boolean onMediaButtonEvent(Intent r10) {
        return this.sessionImpl.onMediaButtonEvent(new MediaSession.ControllerInfo((MediaSessionManager.RemoteUserInfo) Assertions.checkNotNull(this.sessionCompat.getCurrentControllerInfo()), 0, 0, false, null, Bundle.EMPTY, 0), r10);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPause() {
        dispatchSessionTaskWithPlayerCommand(1, new MediaSessionLegacyStub$$ExternalSyntheticLambda8(this), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPlay() {
        dispatchSessionTaskWithPlayerCommand(1, new MediaSessionLegacyStub$$ExternalSyntheticLambda6(this), this.sessionCompat.getCurrentControllerInfo(), false);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPlayFromMediaId(String r2, Bundle r3) {
        handleMediaRequest(createMediaItemForMediaRequest(r2, null, null, r3), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPlayFromSearch(String r2, Bundle r3) {
        handleMediaRequest(createMediaItemForMediaRequest(null, null, r2, r3), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPlayFromUri(Uri r2, Bundle r3) {
        handleMediaRequest(createMediaItemForMediaRequest(null, r2, null, r3), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPrepare() {
        dispatchSessionTaskWithPlayerCommand(2, new MediaSessionLegacyStub$$ExternalSyntheticLambda19(this), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPrepareFromMediaId(String r2, Bundle r3) {
        handleMediaRequest(createMediaItemForMediaRequest(r2, null, null, r3), false);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPrepareFromSearch(String r2, Bundle r3) {
        handleMediaRequest(createMediaItemForMediaRequest(null, null, r2, r3), false);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPrepareFromUri(Uri r2, Bundle r3) {
        handleMediaRequest(createMediaItemForMediaRequest(null, r2, null, r3), false);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onRemoveQueueItem(MediaDescriptionCompat r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        dispatchSessionTaskWithPlayerCommand(20, new MediaSessionLegacyStub$$ExternalSyntheticLambda1(this, r4), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onRewind() {
        dispatchSessionTaskWithPlayerCommand(11, new MediaSessionLegacyStub$$ExternalSyntheticLambda17(this), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSeekTo(long r3) {
        dispatchSessionTaskWithPlayerCommand(5, new MediaSessionLegacyStub$$ExternalSyntheticLambda5(this, r3), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetCaptioningEnabled(boolean r1) {
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetPlaybackSpeed(float r4) {
        if (r4 > 0.0f) goto L5;
        return;
    L5:
        dispatchSessionTaskWithPlayerCommand(13, new MediaSessionLegacyStub$$ExternalSyntheticLambda9(this, r4), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetRating(RatingCompat r2) {
        onSetRating(r2, null);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetRating(RatingCompat r1, Bundle r2) {
        Rating r22 = LegacyConversions.convertToRating(r1);
        if (r22 != null) goto L6;
        Log.w("MediaSessionLegacyStub", "Ignoring invalid RatingCompat " + r1);
        return;
    L6:
        dispatchSessionTaskWithSessionCommand(40010, new MediaSessionLegacyStub$$ExternalSyntheticLambda12(this, r22));
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetRepeatMode(int r4) {
        dispatchSessionTaskWithPlayerCommand(15, new MediaSessionLegacyStub$$ExternalSyntheticLambda16(this, r4), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetShuffleMode(int r4) {
        dispatchSessionTaskWithPlayerCommand(14, new MediaSessionLegacyStub$$ExternalSyntheticLambda7(this, r4), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSkipToNext() {
        if (this.sessionImpl.getPlayerWrapper().isCommandAvailable(9) == false) goto L6;
        dispatchSessionTaskWithPlayerCommand(9, new MediaSessionLegacyStub$$ExternalSyntheticLambda3(this), this.sessionCompat.getCurrentControllerInfo(), true);
        return;
    L6:
        dispatchSessionTaskWithPlayerCommand(8, new MediaSessionLegacyStub$$ExternalSyntheticLambda4(this), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSkipToPrevious() {
        if (this.sessionImpl.getPlayerWrapper().isCommandAvailable(7) == false) goto L6;
        dispatchSessionTaskWithPlayerCommand(7, new MediaSessionLegacyStub$$ExternalSyntheticLambda14(this), this.sessionCompat.getCurrentControllerInfo(), true);
        return;
    L6:
        dispatchSessionTaskWithPlayerCommand(6, new MediaSessionLegacyStub$$ExternalSyntheticLambda15(this), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSkipToQueueItem(long r3) {
        if (r3 >= 0) goto L5;
        return;
    L5:
        dispatchSessionTaskWithPlayerCommand(10, new MediaSessionLegacyStub$$ExternalSyntheticLambda10(this, r3), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onStop() {
        dispatchSessionTaskWithPlayerCommand(3, new MediaSessionLegacyStub$$ExternalSyntheticLambda20(this), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    public void release() {
        if (Build.VERSION.SDK_INT >= 31) goto L9;
        if (this.broadcastReceiverComponentName != null) goto L7;
        setMediaButtonReceiver(this.sessionCompat, null);
        goto L9
    L7:
        Intent r0 = new Intent("android.intent.action.MEDIA_BUTTON", this.sessionImpl.getUri());
        r0.setComponent(this.broadcastReceiverComponentName);
        PendingIntent r02 = PendingIntent.getBroadcast(this.sessionImpl.getContext(), 0, r0, PENDING_INTENT_FLAG_MUTABLE);
        setMediaButtonReceiver(this.sessionCompat, r02);
    L9:
        if (this.runtimeBroadcastReceiver == null) goto L11;
        this.sessionImpl.getContext().unregisterReceiver(this.runtimeBroadcastReceiver);
    L11:
        this.sessionCompat.release();
    }

    public void setAvailableCommands(SessionCommands r6, Player.Commands r7) {
        if (this.customPlaybackException == null) goto L6;
        return;
    L6:
        if (this.availablePlayerCommands.contains(17) == r7.contains(17)) goto L8;
        boolean r0 = true;
    L9:
        this.availableSessionCommands = r6;
        this.availablePlayerCommands = r7;
        if (this.mediaButtonPreferences.isEmpty() == true) goto L16;
        boolean r62 = this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", false);
        boolean r1 = this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false);
        updateCustomLayoutAndLegacyExtrasForMediaButtonPreferences();
        if (this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", false) == r62) goto L14;
    L15:
        getSessionCompat().setExtras(this.legacyExtras);
        goto L16
    L14:
        if (this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false) != r1) goto L15;
    L16:
        MediaSessionImpl r63 = this.sessionImpl;
        if (r0 == false) goto L20;
        updateLegacySessionPlaybackStateAndQueue(r63.getPlayerWrapper());
        return;
    L20:
        updateLegacySessionPlaybackState(r63.getPlayerWrapper());
        return;
    L8:
        r0 = false;
        goto L9
    }

    public void setPlatformCustomLayout(ImmutableList<CommandButton> r1) {
        this.customLayout = r1;
    }

    public void setPlatformMediaButtonPreferences(ImmutableList<CommandButton> r6) {
        this.mediaButtonPreferences = r6;
        boolean r62 = this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", false);
        boolean r2 = this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false);
        updateCustomLayoutAndLegacyExtrasForMediaButtonPreferences();
        if (this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", false) == r62) goto L5;
    L8:
        getSessionCompat().setExtras(this.legacyExtras);
        return;
    L5:
        if (this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false) != r2) goto L8;
    }

    public void start() {
        this.sessionCompat.setActive(true);
    }

    public void updateLegacySessionPlaybackState(PlayerWrapper r3) {
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new MediaSessionLegacyStub$$ExternalSyntheticLambda2(this, r3));
    }

    public void updateLegacySessionPlaybackStateAndQueue(PlayerWrapper r3) {
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new MediaSessionLegacyStub$$ExternalSyntheticLambda25(this, r3));
    }
}
