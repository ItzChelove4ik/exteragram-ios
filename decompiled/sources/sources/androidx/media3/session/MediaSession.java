package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceBitmapLoader;
import androidx.media3.session.SessionCommands;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Longs;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class MediaSession {
    private static final HashMap<String, MediaSession> SESSION_ID_TO_SESSION_MAP = null;
    private static final Object STATIC_LOCK = null;
    private final MediaSessionImpl impl;

    /* renamed from: androidx.media3.session.MediaSession$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends BuilderBase<MediaSession, Builder, Callback> {

        /* renamed from: androidx.media3.session.MediaSession$Builder$1, reason: invalid class name */
        public class AnonymousClass1 implements Callback {
            public AnonymousClass1() {
            }
        }

        public Builder(Context r2, Player r3) {
            super(r2, r3, new AnonymousClass1());
        }

        public MediaSession build() {
            if (this.bitmapLoader != null) goto L6;
            this.bitmapLoader = new CacheBitmapLoader(new DataSourceBitmapLoader(this.context));
        L6:
            return new MediaSession(this.context, this.id, this.player, this.sessionActivity, this.customLayout, this.mediaButtonPreferences, this.commandButtonsForMediaItems, this.callback, this.tokenExtras, this.sessionExtras, (BitmapLoader) Assertions.checkNotNull(this.bitmapLoader), this.playIfSuppressed, this.isPeriodicPositionUpdateEnabled, 0);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setId(String r1) {
            return (Builder) super.setId(r1);
        }
    }

    public static abstract class BuilderBase<SessionT extends MediaSession, BuilderT extends BuilderBase<SessionT, BuilderT, CallbackT>, CallbackT extends Callback> {
        BitmapLoader bitmapLoader;
        CallbackT callback;
        ImmutableList<CommandButton> commandButtonsForMediaItems;
        final Context context;
        ImmutableList<CommandButton> customLayout;
        String id;
        boolean isPeriodicPositionUpdateEnabled;
        ImmutableList<CommandButton> mediaButtonPreferences;
        boolean playIfSuppressed;
        final Player player;
        PendingIntent sessionActivity;
        Bundle sessionExtras;
        Bundle tokenExtras;

        public BuilderBase(Context r1, Player r2, CallbackT r3) {
            this.context = (Context) Assertions.checkNotNull(r1);
            this.player = (Player) Assertions.checkNotNull(r2);
            Assertions.checkArgument(r2.canAdvertiseSession());
            this.id = _UrlKt.FRAGMENT_ENCODE_SET;
            this.callback = r3;
            this.tokenExtras = new Bundle();
            this.sessionExtras = new Bundle();
            this.customLayout = ImmutableList.of();
            this.mediaButtonPreferences = ImmutableList.of();
            this.playIfSuppressed = true;
            this.isPeriodicPositionUpdateEnabled = true;
            this.commandButtonsForMediaItems = ImmutableList.of();
        }

        public BuilderT setId(String r1) {
            this.id = (String) Assertions.checkNotNull(r1);
            return this;
        }
    }

    public interface Callback {
        /* renamed from: $r8$lambda$lhwU59qlBPXWP-mpGG20yrEFlHo, reason: not valid java name */
        static /* synthetic */ ListenableFuture m865$r8$lambda$lhwU59qlBPXWPmpGG20yrEFlHo(int r1, long r2, List r4) {
            return Futures.immediateFuture(new MediaItemsWithStartPosition(r4, r1, r2));
        }

        default ListenableFuture<List<MediaItem>> onAddMediaItems(MediaSession r1, ControllerInfo r2, List<MediaItem> r3) {
            Iterator<MediaItem> r0 = r3.iterator();
        L4:
            if (r0.hasNext() == false) goto L10;
            if (r0.next().localConfiguration != null) goto L4;
            return Futures.immediateFailedFuture(new UnsupportedOperationException());
        L10:
            return Futures.immediateFuture(r3);
        }

        default ConnectionResult onConnect(MediaSession r1, ControllerInfo r2) {
            return new ConnectionResult.AcceptedResultBuilder(r1).build();
        }

        default ListenableFuture<SessionResult> onCustomCommand(MediaSession r1, ControllerInfo r2, SessionCommand r3, Bundle r4) {
            return Futures.immediateFuture(new SessionResult(-6));
        }

        default void onDisconnected(MediaSession r1, ControllerInfo r2) {
        }

        default boolean onMediaButtonEvent(MediaSession r1, ControllerInfo r2, Intent r3) {
            return false;
        }

        default ListenableFuture<MediaItemsWithStartPosition> onPlaybackResumption(MediaSession r1, ControllerInfo r2) {
            return Futures.immediateFailedFuture(new UnsupportedOperationException());
        }

        @Deprecated
        default int onPlayerCommandRequest(MediaSession r1, ControllerInfo r2, int r3) {
            return 0;
        }

        default void onPlayerInteractionFinished(MediaSession r1, ControllerInfo r2, Player.Commands r3) {
        }

        default void onPostConnect(MediaSession r1, ControllerInfo r2) {
        }

        default ListenableFuture<MediaItemsWithStartPosition> onSetMediaItems(MediaSession r1, ControllerInfo r2, List<MediaItem> r3, int r4, long r5) {
            return Util.transformFutureAsync(onAddMediaItems(r1, r2, r3), new MediaSession$Callback$$ExternalSyntheticLambda0(r4, r5));
        }

        default ListenableFuture<SessionResult> onSetRating(MediaSession r1, ControllerInfo r2, Rating r3) {
            return Futures.immediateFuture(new SessionResult(-6));
        }

        default ListenableFuture<SessionResult> onSetRating(MediaSession r1, ControllerInfo r2, String r3, Rating r4) {
            return Futures.immediateFuture(new SessionResult(-6));
        }
    }

    public static final class ConnectionResult {
        public static final Player.Commands DEFAULT_PLAYER_COMMANDS = null;
        public static final SessionCommands DEFAULT_SESSION_AND_LIBRARY_COMMANDS = null;
        public static final SessionCommands DEFAULT_SESSION_COMMANDS = null;
        public final Player.Commands availablePlayerCommands;
        public final SessionCommands availableSessionCommands;
        public final ImmutableList<CommandButton> customLayout;
        public final boolean isAccepted;
        public final ImmutableList<CommandButton> mediaButtonPreferences;
        public final PendingIntent sessionActivity;
        public final Bundle sessionExtras;

        public static class AcceptedResultBuilder {
            private Player.Commands availablePlayerCommands;
            private SessionCommands availableSessionCommands;
            private ImmutableList<CommandButton> customLayout;
            private ImmutableList<CommandButton> mediaButtonPreferences;
            private PendingIntent sessionActivity;
            private Bundle sessionExtras;

            public AcceptedResultBuilder(MediaSession r1) {
                this.availablePlayerCommands = ConnectionResult.DEFAULT_PLAYER_COMMANDS;
                this.availableSessionCommands = ConnectionResult.DEFAULT_SESSION_COMMANDS;
            }

            public ConnectionResult build() {
                return new ConnectionResult(true, this.availableSessionCommands, this.availablePlayerCommands, this.customLayout, this.mediaButtonPreferences, this.sessionExtras, this.sessionActivity, null);
            }

            public AcceptedResultBuilder setAvailablePlayerCommands(Player.Commands r1) {
                this.availablePlayerCommands = (Player.Commands) Assertions.checkNotNull(r1);
                return this;
            }

            public AcceptedResultBuilder setAvailableSessionCommands(SessionCommands r1) {
                this.availableSessionCommands = (SessionCommands) Assertions.checkNotNull(r1);
                return this;
            }

            public AcceptedResultBuilder setCustomLayout(List<CommandButton> r1) {
                if (r1 != null) goto L4;
                ImmutableList<CommandButton> r12 = null;
            L5:
                this.customLayout = r12;
                return this;
            L4:
                r12 = ImmutableList.copyOf(r1);
                goto L5
            }

            public AcceptedResultBuilder setMediaButtonPreferences(List<CommandButton> r1) {
                if (r1 != null) goto L4;
                ImmutableList<CommandButton> r12 = null;
            L5:
                this.mediaButtonPreferences = r12;
                return this;
            L4:
                r12 = ImmutableList.copyOf(r1);
                goto L5
            }
        }

        static {
            DEFAULT_SESSION_COMMANDS = new SessionCommands.Builder().addAllSessionCommands().build();
            DEFAULT_SESSION_AND_LIBRARY_COMMANDS = new SessionCommands.Builder().addAllLibraryCommands().addAllSessionCommands().build();
            DEFAULT_PLAYER_COMMANDS = new Player.Commands.Builder().addAllCommands().build();
        }

        private ConnectionResult(boolean r1, SessionCommands r2, Player.Commands r3, ImmutableList<CommandButton> r4, ImmutableList<CommandButton> r5, Bundle r6, PendingIntent r7) {
            this.isAccepted = r1;
            this.availableSessionCommands = r2;
            this.availablePlayerCommands = r3;
            this.customLayout = r4;
            this.mediaButtonPreferences = r5;
            this.sessionExtras = r6;
            this.sessionActivity = r7;
        }

        public /* synthetic */ ConnectionResult(boolean r1, SessionCommands r2, Player.Commands r3, ImmutableList r4, ImmutableList r5, Bundle r6, PendingIntent r7, AnonymousClass1 r8) {
            this(r1, r2, r3, r4, r5, r6, r7);
        }

        public static ConnectionResult accept(SessionCommands r8, Player.Commands r9) {
            return new ConnectionResult(true, r8, r9, null, null, null, null);
        }
    }

    public interface ControllerCb {
        default void onAudioAttributesChanged(int r1, AudioAttributes r2) {
        }

        default void onAvailableCommandsChangedFromPlayer(int r1, Player.Commands r2) {
        }

        default void onDeviceInfoChanged(int r1, DeviceInfo r2) {
        }

        default void onDeviceVolumeChanged(int r1, int r2, boolean r3) {
        }

        default void onDisconnected(int r1) {
        }

        default void onIsLoadingChanged(int r1, boolean r2) {
        }

        default void onIsPlayingChanged(int r1, boolean r2) {
        }

        default void onLibraryResult(int r1, LibraryResult<?> r2) {
        }

        default void onMediaItemTransition(int r1, MediaItem r2, int r3) {
        }

        default void onMediaMetadataChanged(int r1, MediaMetadata r2) {
        }

        default void onPeriodicSessionPositionInfoChanged(int r1, SessionPositionInfo r2, boolean r3, boolean r4, int r5) {
        }

        default void onPlayWhenReadyChanged(int r1, boolean r2, int r3) {
        }

        default void onPlaybackParametersChanged(int r1, PlaybackParameters r2) {
        }

        default void onPlaybackStateChanged(int r1, int r2, PlaybackException r3) {
        }

        default void onPlaybackSuppressionReasonChanged(int r1, int r2) {
        }

        default void onPlayerChanged(int r1, PlayerWrapper r2, PlayerWrapper r3) {
        }

        default void onPlayerError(int r1, PlaybackException r2) {
        }

        default void onPlayerInfoChanged(int r1, PlayerInfo r2, Player.Commands r3, boolean r4, boolean r5) {
        }

        default void onPlaylistMetadataChanged(int r1, MediaMetadata r2) {
        }

        default void onPositionDiscontinuity(int r1, Player.PositionInfo r2, Player.PositionInfo r3, int r4) {
        }

        default void onRenderedFirstFrame(int r1) {
        }

        default void onRepeatModeChanged(int r1, int r2) {
        }

        default void onSessionResult(int r1, SessionResult r2) {
        }

        default void onShuffleModeEnabledChanged(int r1, boolean r2) {
        }

        default void onTimelineChanged(int r1, Timeline r2, int r3) {
        }

        default void onTrackSelectionParametersChanged(int r1, TrackSelectionParameters r2) {
        }

        default void onTracksChanged(int r1, Tracks r2) {
        }

        default void onVideoSizeChanged(int r1, VideoSize r2) {
        }

        default void onVolumeChanged(int r1, float r2) {
        }

        default void sendCustomCommand(int r1, SessionCommand r2, Bundle r3) {
        }
    }

    public static final class ControllerInfo {
        private final Bundle connectionHints;
        private final ControllerCb controllerCb;
        private final int interfaceVersion;
        private final boolean isTrusted;
        private final int libraryVersion;
        private final int maxCommandsForMediaItems;
        private final MediaSessionManager.RemoteUserInfo remoteUserInfo;

        public ControllerInfo(MediaSessionManager.RemoteUserInfo r1, int r2, int r3, boolean r4, ControllerCb r5, Bundle r6, int r7) {
            this.remoteUserInfo = r1;
            this.libraryVersion = r2;
            this.interfaceVersion = r3;
            this.isTrusted = r4;
            this.controllerCb = r5;
            this.connectionHints = r6;
            this.maxCommandsForMediaItems = r7;
        }

        public boolean equals(Object r3) {
            if ((r3 instanceof ControllerInfo) == true) goto L6;
            return false;
        L6:
            if (this != r3) goto L9;
            return true;
        L9:
            ControllerInfo r32 = (ControllerInfo) r3;
            ControllerCb r0 = this.controllerCb;
            if (r0 != null) goto L17;
            if (r32.controllerCb != null) goto L17;
            return this.remoteUserInfo.equals(r32.remoteUserInfo);
        L17:
            return Objects.equals(r0, r32.controllerCb);
        }

        public Bundle getConnectionHints() {
            return new Bundle(this.connectionHints);
        }

        public ControllerCb getControllerCb() {
            return this.controllerCb;
        }

        public int getControllerVersion() {
            return this.libraryVersion;
        }

        public int getInterfaceVersion() {
            return this.interfaceVersion;
        }

        public String getPackageName() {
            return this.remoteUserInfo.getPackageName();
        }

        public MediaSessionManager.RemoteUserInfo getRemoteUserInfo() {
            return this.remoteUserInfo;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.controllerCb, this.remoteUserInfo});
        }

        public boolean isTrusted() {
            return this.isTrusted;
        }

        public String toString() {
            return "ControllerInfo {pkg=" + this.remoteUserInfo.getPackageName() + ", uid=" + this.remoteUserInfo.getUid() + "}";
        }
    }

    public static final class MediaItemsWithStartPosition {
        public final ImmutableList<MediaItem> mediaItems;
        public final int startIndex;
        public final long startPositionMs;

        public MediaItemsWithStartPosition(List<MediaItem> r1, int r2, long r3) {
            this.mediaItems = ImmutableList.copyOf(r1);
            this.startIndex = r2;
            this.startPositionMs = r3;
        }

        public boolean equals(Object r6) {
            if (this != r6) goto L6;
            return true;
        L6:
            if ((r6 instanceof MediaItemsWithStartPosition) == true) goto L8;
            return false;
        L8:
            MediaItemsWithStartPosition r62 = (MediaItemsWithStartPosition) r6;
            if (this.mediaItems.equals(r62.mediaItems) == true) goto L11;
        L15:
            return false;
        L11:
            if (this.startIndex != r62.startIndex) goto L15;
            if (this.startPositionMs != r62.startPositionMs) goto L15;
            return true;
        }

        public int hashCode() {
            return (((this.mediaItems.hashCode() * 31) + this.startIndex) * 31) + Longs.hashCode(this.startPositionMs);
        }
    }

    static {
        STATIC_LOCK = new Object();
        SESSION_ID_TO_SESSION_MAP = new HashMap();
    }

    public MediaSession(Context r5, String r6, Player r7, PendingIntent r8, ImmutableList<CommandButton> r9, ImmutableList<CommandButton> r10, ImmutableList<CommandButton> r11, Callback r12, Bundle r13, Bundle r14, BitmapLoader r15, boolean r16, boolean r17, int r18) {
        Object r1 = STATIC_LOCK;
        monitor-enter(r1);
        HashMap<String, MediaSession> r2 = SESSION_ID_TO_SESSION_MAP;     // Catch: Throwable -> L10
        if (r2.containsKey(r6) == true) goto L13;
        r2.put(r6, this);     // Catch: Throwable -> L10
        monitor-exit(r1);     // Catch: Throwable -> L10
        this.impl = createImpl(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18);
        return;
    L13:
        throw new IllegalStateException("Session ID must be unique. ID=" + r6);     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    public MediaSessionImpl createImpl(Context r1, String r2, Player r3, PendingIntent r4, ImmutableList<CommandButton> r5, ImmutableList<CommandButton> r6, ImmutableList<CommandButton> r7, Callback r8, Bundle r9, Bundle r10, BitmapLoader r11, boolean r12, boolean r13, int r14) {
        return new MediaSessionImpl(this, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    public ImmutableList<CommandButton> getCustomLayout() {
        return this.impl.getCustomLayout();
    }

    public ImmutableList<CommandButton> getMediaButtonPreferences() {
        return this.impl.getMediaButtonPreferences();
    }

    public ControllerInfo getMediaNotificationControllerInfo() {
        return this.impl.getMediaNotificationControllerInfo();
    }

    public final Player getPlayer() {
        return this.impl.getPlayerWrapper().getWrappedPlayer();
    }

    public final void release() {
        Object r0 = STATIC_LOCK;     // Catch: Exception -> L11
        monitor-enter(r0);     // Catch: Exception -> L11
        SESSION_ID_TO_SESSION_MAP.remove(this.impl.getId());     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        this.impl.release();     // Catch: Exception -> L11
        return;
    L8:
        th = move-exception;
        throw th;     // Catch: Exception -> L11
    }

    public final void setPlayer(Player r5) {
        Assertions.checkNotNull(r5);
        Assertions.checkArgument(r5.canAdvertiseSession());
        boolean r2 = false;
        if (r5.getApplicationLooper() != getPlayer().getApplicationLooper()) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        if (r5.getApplicationLooper() != Looper.myLooper()) goto L9;
        r2 = true;
    L9:
        Assertions.checkState(r2);
        this.impl.setPlayer(r5);
        return;
    L5:
        r0 = false;
        goto L6
    }
}
