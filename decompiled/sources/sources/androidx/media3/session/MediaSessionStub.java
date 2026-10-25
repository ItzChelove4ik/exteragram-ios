package androidx.media3.session;

import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.Surface;
import androidx.core.util.ObjectsCompat;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.BundleListRetriever;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.IMediaSession;
import androidx.media3.session.MediaSession;
import androidx.media3.session.PlayerInfo;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* loaded from: classes4.dex */
final class MediaSessionStub extends IMediaSession.Stub {
    private final ConnectedControllersManager<IBinder> connectedControllersManager;
    private int nextUniqueTrackGroupIdPrefix;
    private final Set<MediaSession.ControllerInfo> pendingControllers;
    private final WeakReference<MediaSessionImpl> sessionImpl;
    private ImmutableBiMap<TrackGroup, String> trackGroupIdMap;

    public static final class Controller2Cb implements MediaSession.ControllerCb {
        private final int controllerInterfaceVersion;
        private final IMediaController iController;

        public Controller2Cb(IMediaController r1, int r2) {
            this.iController = r1;
            this.controllerInterfaceVersion = r2;
        }

        public boolean equals(Object r3) {
            if (this != r3) goto L5;
            return true;
        L5:
            if (r3 != null) goto L7;
            return false;
        L7:
            if (r3.getClass() == Controller2Cb.class) goto L10;
            return false;
        L10:
            return Objects.equals(getCallbackBinder(), ((Controller2Cb) r3).getCallbackBinder());
        }

        public IBinder getCallbackBinder() {
            return this.iController.asBinder();
        }

        public int hashCode() {
            return ObjectsCompat.hash(new Object[]{getCallbackBinder()});
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onAvailableCommandsChangedFromPlayer(int r1, Player.Commands r2) {
            this.iController.onAvailableCommandsChangedFromPlayer(r1, r2.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onDisconnected(int r1) {
            SessionUtil.disconnectIMediaController(this.iController);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onLibraryResult(int r1, LibraryResult<?> r2) {
            this.iController.onLibraryResult(r1, r2.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPeriodicSessionPositionInfoChanged(int r1, SessionPositionInfo r2, boolean r3, boolean r4, int r5) {
            this.iController.onPeriodicSessionPositionInfoChanged(r1, r2.filterByAvailableCommands(r3, r4).toBundle(r5));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlayerInfoChanged(int r6, PlayerInfo r7, Player.Commands r8, boolean r9, boolean r10) {
            boolean r1 = false;
            if (this.controllerInterfaceVersion == 0) goto L5;
            boolean r0 = true;
        L6:
            Assertions.checkState(r0);
            if (r9 == false) goto L9;
        L12:
            boolean r02 = true;
        L13:
            if (r10 == false) goto L15;
        L16:
            r1 = true;
        L18:
            if (this.controllerInterfaceVersion < 2) goto L21;
            this.iController.onPlayerInfoChangedWithExclusions(r6, r7.filterByAvailableCommands(r8, r9, r10).toBundleForRemoteProcess(this.controllerInterfaceVersion), new PlayerInfo.BundlingExclusions(r02, r1).toBundle());
            return;
        L21:
            this.iController.onPlayerInfoChanged(r6, r7.filterByAvailableCommands(r8, r9, true).toBundleForRemoteProcess(this.controllerInterfaceVersion), r02);
            return;
        L15:
            if (r8.contains(30) == true) goto L18;
        L9:
            if (r8.contains(17) == false) goto L12;
            r02 = false;
            goto L13
        L5:
            r0 = false;
            goto L6
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onRenderedFirstFrame(int r1) {
            this.iController.onRenderedFirstFrame(r1);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSessionResult(int r1, SessionResult r2) {
            this.iController.onSessionResult(r1, r2.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void sendCustomCommand(int r1, SessionCommand r2, Bundle r3) {
            this.iController.onCustomCommand(r1, r2.toBundle(), r3);
        }
    }

    public interface ControllerPlayerTask {
        void run(PlayerWrapper r1, MediaSession.ControllerInfo r2);
    }

    public interface MediaItemPlayerTask {
        void run(PlayerWrapper r1, MediaSession.ControllerInfo r2, List<MediaItem> r3);
    }

    public interface MediaItemsWithStartPositionPlayerTask {
        void run(PlayerWrapper r1, MediaSession.MediaItemsWithStartPosition r2);
    }

    public interface SessionTask<T, K extends MediaSessionImpl> {
        T run(K r1, MediaSession.ControllerInfo r2, int r3);
    }

    /* renamed from: $r8$lambda$-sO_dzuDVi4Um3NQeY2UpD6YZKo, reason: not valid java name */
    public static /* synthetic */ void m902$r8$lambda$sO_dzuDVi4Um3NQeY2UpD6YZKo(MediaSessionStub r1, MediaSession.ControllerInfo r2, SessionCommand r3, MediaSessionImpl r4, int r5, int r6, SessionTask r7) {
        if (r1.connectedControllersManager.isConnected(r2) == true) goto L5;
        return;
    L5:
        ConnectedControllersManager<IBinder> r12 = r1.connectedControllersManager;
        if (r3 == null) goto L12;
        if (r12.isSessionCommandAvailable(r2, r3) == true) goto L15;
        sendSessionResult(r4, r2, r5, new SessionResult(-4));
        return;
    L15:
        r7.run(r4, r2, r5);
        return;
    L12:
        if (r12.isSessionCommandAvailable(r2, r6) == true) goto L15;
        sendSessionResult(r4, r2, r5, new SessionResult(-4));
    }

    /* renamed from: $r8$lambda$0R2n2gLxemMsXbfVwiX_p-j70Bc, reason: not valid java name */
    public static /* synthetic */ void m903$r8$lambda$0R2n2gLxemMsXbfVwiX_pj70Bc(Surface r0, PlayerWrapper r1) {
        r1.setVideoSurface(r0);
    }

    public static /* synthetic */ void $r8$lambda$0sp5GeU0WzI_qEuAy4ySukcYQuA(MediaMetadata r0, PlayerWrapper r1) {
        r1.setPlaylistMetadata(r0);
    }

    public static /* synthetic */ void $r8$lambda$1vN0gC2ZaTovIExjX5xTnwlbne8(MediaSessionStub r0, IMediaController r1) {
        r0.connectedControllersManager.removeController(r1.asBinder());
    }

    public static /* synthetic */ void $r8$lambda$28SMpqmt5aYxI_VI6PKVYthe0dg(float r0, PlayerWrapper r1) {
        r1.setPlaybackSpeed(r0);
    }

    /* renamed from: $r8$lambda$2Ey-JeZWWD-aFOi-g1p5oYCto-0, reason: not valid java name */
    public static /* synthetic */ void m904$r8$lambda$2EyJeZWWDaFOig1p5oYCto0(MediaSessionStub r0, int r1, PlayerWrapper r2, MediaSession.ControllerInfo r3) {
        r2.seekToDefaultPosition(r0.maybeCorrectMediaItemIndex(r3, r2, r1));
    }

    /* renamed from: $r8$lambda$2T_8vW-EogaRDt89KsNGfIqK7gw, reason: not valid java name */
    public static /* synthetic */ ListenableFuture m905$r8$lambda$2T_8vWEogaRDt89KsNGfIqK7gw(String r0, MediaLibraryService$LibraryParams r1, MediaLibrarySessionImpl r2, MediaSession.ControllerInfo r3, int r4) {
        throw null;
    }

    /* renamed from: $r8$lambda$2VLwnFvVDZ-XEX9OENpl0EQqWb0, reason: not valid java name */
    public static /* synthetic */ ListenableFuture m906$r8$lambda$2VLwnFvVDZXEX9OENpl0EQqWb0(MediaSessionImpl r2, MediaSession.ControllerInfo r3, MediaItemsWithStartPositionPlayerTask r4, MediaSession.MediaItemsWithStartPosition r5) {
        return Util.postOrRunWithCompletion(r2.getApplicationHandler(), r2.callWithControllerForCurrentRequestSet(r3, new MediaSessionStub$$ExternalSyntheticLambda84(r2, r4, r5)), new SessionResult(0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: $r8$lambda$2isH75fLe4J0Dtj3oqE-3ggOXeg, reason: not valid java name */
    public static /* synthetic */ void m907$r8$lambda$2isH75fLe4J0Dtj3oqE3ggOXeg(MediaSession.ControllerInfo r2, int r3, ListenableFuture r4) {
        LibraryResult r42 = (LibraryResult) Assertions.checkNotNull((LibraryResult) r4.get(), "LibraryResult must not be null");     // Catch: InterruptedException -> L5 ExecutionException -> L7 CancellationException -> L9
    L13:
        sendLibraryResult(r2, r3, r42);
        return;
    L5:
        e = e;
    L11:
        Log.w("MediaSessionStub", "Library operation failed", e);
        r42 = LibraryResult.ofError(-1);
    L9:
        e = move-exception;
        Log.w("MediaSessionStub", "Library operation cancelled", e);
        r42 = LibraryResult.ofError(1);
    L7:
        e = e;
        goto L11
    }

    public static /* synthetic */ void $r8$lambda$36wsACNw3i186IZvBrowtMnuTEk(MediaSessionStub r0, int r1, PlayerWrapper r2, MediaSession.ControllerInfo r3) {
        r2.removeMediaItem(r0.maybeCorrectMediaItemIndex(r3, r2, r1));
    }

    /* renamed from: $r8$lambda$4orfWviQa1EjzyBTJr-yQgH6Qws, reason: not valid java name */
    public static /* synthetic */ void m908$r8$lambda$4orfWviQa1EjzyBTJryQgH6Qws(int r0, PlayerWrapper r1) {
        r1.decreaseDeviceVolume(r0);
    }

    /* renamed from: $r8$lambda$4u38cwJjRvGrJn-jKvlnyWzyPfU, reason: not valid java name */
    public static /* synthetic */ ListenableFuture m909$r8$lambda$4u38cwJjRvGrJnjKvlnyWzyPfU(MediaLibraryService$LibraryParams r0, MediaLibrarySessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        throw null;
    }

    public static /* synthetic */ void $r8$lambda$6LU3_piQnzAW2gdi3_ZmbUEYE2M(MediaSessionImpl r1, MediaItemsWithStartPositionPlayerTask r2, MediaSession.MediaItemsWithStartPosition r3) {
        if (r1.isReleased() == true) goto L6;
        r2.run(r1.getPlayerWrapper(), r3);
        return;
    }

    public static /* synthetic */ void $r8$lambda$7iJyAqo_yFvIK6ewaRF71VXX7as(boolean r0, PlayerWrapper r1) {
        r1.setDeviceMuted(r0);
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$8VI4pJat_48yTFY_991wSULRUdw(String r0, Rating r1, MediaSessionImpl r2, MediaSession.ControllerInfo r3, int r4) {
        return r2.onSetRatingOnHandler(r3, r0, r1);
    }

    /* renamed from: $r8$lambda$DZ1ZMTWdb6K-hKSLz3T89Fewt48, reason: not valid java name */
    public static /* synthetic */ void m910$r8$lambda$DZ1ZMTWdb6KhKSLz3T89Fewt48(boolean r0, int r1, PlayerWrapper r2) {
        r2.setDeviceMuted(r0, r1);
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$FW3cFSx_7CUbhon2sTzh49FpWSE(MediaItem r2, long r3, MediaSessionImpl r5, MediaSession.ControllerInfo r6, int r7) {
        return r5.onSetMediaItemsOnHandler(r6, ImmutableList.of(r2), 0, r3);
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$G2yFvKUfRMY9Pnv8FG4VEvznbt0(SessionTask r1, MediaItemsWithStartPositionPlayerTask r2, MediaSessionImpl r3, MediaSession.ControllerInfo r4, int r5) {
        if (r3.isReleased() == false) goto L7;
        return Futures.immediateFuture(new SessionResult(-100));
    L7:
        return Util.transformFutureAsync((ListenableFuture) r1.run(r3, r4, r5), new MediaSessionStub$$ExternalSyntheticLambda77(r3, r4, r2));
    }

    /* renamed from: $r8$lambda$HwUeDXc3qNIOSOLs_DN-bsxrzAU, reason: not valid java name */
    public static /* synthetic */ void m911$r8$lambda$HwUeDXc3qNIOSOLs_DNbsxrzAU(int r0, PlayerWrapper r1) {
        r1.setDeviceVolume(r0);
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$JzabdfIl6aZpGerMlypCxjzhGss(List r0, MediaSessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        return r1.onAddMediaItemsOnHandler(r2, r0);
    }

    public static /* synthetic */ void $r8$lambda$K995gx7motyQjeFmdF6d9Aega4E(int r0, PlayerWrapper r1) {
        r1.increaseDeviceVolume(r0);
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$KS3xK4qkadqzcBe0qXhTzN2XY4U(MediaItem r6, boolean r7, MediaSessionImpl r8, MediaSession.ControllerInfo r9, int r10) {
        ImmutableList r2 = ImmutableList.of(r6);
        if (r7 == false) goto L6;
        int r62 = -1;
    L5:
        int r3 = r62;
        if (r7 == false) goto L10;
        long r63 = -9223372036854775807L;
    L12:
        return r8.onSetMediaItemsOnHandler(r9, r2, r3, r63);
    L10:
        r63 = r8.getPlayerWrapper().getCurrentPosition();
        goto L12
    L6:
        r62 = r8.getPlayerWrapper().getCurrentMediaItemIndex();
        goto L5
    }

    public static /* synthetic */ void $r8$lambda$KyjWKWhNqTIIX8P65HEEZqOTqFo(PlayerWrapper r0, MediaSession.ControllerInfo r1, List r2) {
        r0.addMediaItems(r2);
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$M6ePkKHOPlkBJsLIuAbZTxNgu3s(MediaItem r0, MediaSessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        return r1.onAddMediaItemsOnHandler(r2, ImmutableList.of(r0));
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$MGipxOQsujaGdggWl9JrNr2GU4w(MediaSessionImpl r2, MediaSession.ControllerInfo r3, MediaItemPlayerTask r4, List r5) {
        return Util.postOrRunWithCompletion(r2.getApplicationHandler(), r2.callWithControllerForCurrentRequestSet(r3, new MediaSessionStub$$ExternalSyntheticLambda85(r2, r4, r3, r5)), new SessionResult(0));
    }

    public static /* synthetic */ void $r8$lambda$MzTDuo4pdhfWYP7Zt5IqdPqqs3I(MediaSessionStub r2, int r3, PlayerWrapper r4, MediaSession.ControllerInfo r5, List r6) {
        r2.getClass();
        if (r6.size() != 1) goto L6;
        r4.replaceMediaItem(r2.maybeCorrectMediaItemIndex(r5, r4, r3), (MediaItem) r6.get(0));
        return;
    L6:
        r4.replaceMediaItems(r2.maybeCorrectMediaItemIndex(r5, r4, r3), r2.maybeCorrectMediaItemIndex(r5, r4, r3 + 1), r6);
    }

    /* renamed from: $r8$lambda$NNomdGB-sxRck4A0A21lVx5ADcI, reason: not valid java name */
    public static /* synthetic */ ListenableFuture m912$r8$lambda$NNomdGBsxRck4A0A21lVx5ADcI(SessionTask r1, MediaItemPlayerTask r2, MediaSessionImpl r3, MediaSession.ControllerInfo r4, int r5) {
        if (r3.isReleased() == false) goto L7;
        return Futures.immediateFuture(new SessionResult(-100));
    L7:
        return Util.transformFutureAsync((ListenableFuture) r1.run(r3, r4, r5), new MediaSessionStub$$ExternalSyntheticLambda81(r3, r4, r2));
    }

    /* renamed from: $r8$lambda$OjZVxwd-qIjM50w02a-TctS7Iu0, reason: not valid java name */
    public static /* synthetic */ ListenableFuture m913$r8$lambda$OjZVxwdqIjM50w02aTctS7Iu0(MediaItem r0, MediaSessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        return r1.onAddMediaItemsOnHandler(r2, ImmutableList.of(r0));
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$Qat3F7M14YDEAgetqACtFQZZUlo(List r0, MediaSessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        return r1.onAddMediaItemsOnHandler(r2, r0);
    }

    public static /* synthetic */ void $r8$lambda$QvwfZ09nIqxswKV1xmXdKUppLAg(PlayerWrapper r0) {
        r0.increaseDeviceVolume();
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$RRPw6LAp1NW6DX57p_2Wvz9dOWQ(SessionTask r0, MediaSessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        return (ListenableFuture) r0.run(r1, r2, r3);
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$RbO8L_G_ucak8Vpgdv62mXVnlyM(SessionTask r1, MediaLibrarySessionImpl r2, MediaSession.ControllerInfo r3, int r4) {
        return handleSessionTaskWhenReady(r2, r3, r4, r1, new MediaSessionStub$$ExternalSyntheticLambda76(r3, r4));
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$RnYLpwrJxrN7hpkjiYAImZ9IBvo(String r0, MediaLibrarySessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        throw null;
    }

    public static /* synthetic */ void $r8$lambda$RnbuTI8o82GySANGocFrgjKNtT8(MediaSessionStub r0, int r1, long r2, PlayerWrapper r4, MediaSession.ControllerInfo r5) {
        r4.seekTo(r0.maybeCorrectMediaItemIndex(r5, r4, r1), r2);
    }

    /* renamed from: $r8$lambda$SB_R528bXpysgXyT-wrxElWlUz8, reason: not valid java name */
    public static /* synthetic */ ListenableFuture m914$r8$lambda$SB_R528bXpysgXyTwrxElWlUz8(List r6, boolean r7, MediaSessionImpl r8, MediaSession.ControllerInfo r9, int r10) {
        if (r7 == false) goto L5;
        int r102 = -1;
    L4:
        int r3 = r102;
        if (r7 == false) goto L9;
        long r0 = -9223372036854775807L;
    L11:
        return r8.onSetMediaItemsOnHandler(r9, r6, r3, r0);
    L9:
        r0 = r8.getPlayerWrapper().getCurrentPosition();
        goto L11
    L5:
        r102 = r8.getPlayerWrapper().getCurrentMediaItemIndex();
        goto L4
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void $r8$lambda$SjZC1HY1SjS6ktIzYoyCorTzbWs(MediaSessionImpl r2, MediaSession.ControllerInfo r3, int r4, ListenableFuture r5) {
        SessionResult r52 = (SessionResult) Assertions.checkNotNull((SessionResult) r5.get(), "SessionResult must not be null");     // Catch: InterruptedException -> L5 ExecutionException -> L7 CancellationException -> L9
    L17:
        sendSessionResult(r2, r3, r4, r52);
        return;
    L5:
        e = e;
    L11:
        Log.w("MediaSessionStub", "Session operation failed", e);
        if ((e.getCause() instanceof UnsupportedOperationException) == false) goto L14;
        int r53 = -6;
    L15:
        r52 = new SessionResult(r53);
        goto L17
    L14:
        r53 = -1;
    L9:
        e = move-exception;
        Log.w("MediaSessionStub", "Session operation cancelled", e);
        r52 = new SessionResult(1);
    L7:
        e = e;
        goto L11
    }

    public static /* synthetic */ void $r8$lambda$Snygvt5R7sGxA3ZYiMfN7FARnFo(MediaSessionStub r0, int r1, PlayerWrapper r2, MediaSession.ControllerInfo r3, List r4) {
        r2.addMediaItems(r0.maybeCorrectMediaItemIndex(r3, r2, r1), r4);
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$TQEUBINVIXHWsushhlUKakEq6Tg(ImmutableList r0, MediaSessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        return r1.onAddMediaItemsOnHandler(r2, r0);
    }

    public static /* synthetic */ void $r8$lambda$TaCsrVznIbjM2Arrzd3PrrzvW4A(PlayerWrapper r0) {
        r0.decreaseDeviceVolume();
    }

    public static /* synthetic */ void $r8$lambda$WEAykr2iadOQ43Snp8jBt0HIjlk(int r0, int r1, int r2, PlayerWrapper r3) {
        r3.moveMediaItems(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$WcL-m9AzryeTmodxbW72X4a4o0E, reason: not valid java name */
    public static /* synthetic */ ListenableFuture m915$r8$lambda$WcLm9AzryeTmodxbW72X4a4o0E(MediaItem r0, MediaSessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        return r1.onAddMediaItemsOnHandler(r2, ImmutableList.of(r0));
    }

    public static /* synthetic */ void $r8$lambda$WnZfQos91Q9GL47M8dPI6s08yak(int r0, PlayerWrapper r1) {
        r1.setRepeatMode(r0);
    }

    public static /* synthetic */ void $r8$lambda$X9qtLqgCg2LrIW1PolLnzIoXoRM(boolean r0, PlayerWrapper r1) {
        r1.setShuffleModeEnabled(r0);
    }

    /* renamed from: $r8$lambda$Xc4eNQfbE-JS3aLWoIm18RfatR8, reason: not valid java name */
    public static /* synthetic */ ListenableFuture m916$r8$lambda$Xc4eNQfbEJS3aLWoIm18RfatR8(String r0, MediaLibraryService$LibraryParams r1, MediaLibrarySessionImpl r2, MediaSession.ControllerInfo r3, int r4) {
        throw null;
    }

    public static /* synthetic */ void $r8$lambda$ZIwDF4b8VjuDdrDDLvZAcNMmToc(int r0, int r1, PlayerWrapper r2) {
        r2.setDeviceVolume(r0, r1);
    }

    /* renamed from: $r8$lambda$_N5B-ktRJWFjwk7XLhrty_xH19M, reason: not valid java name */
    public static /* synthetic */ ListenableFuture m917$r8$lambda$_N5BktRJWFjwk7XLhrty_xH19M(String r0, int r1, int r2, MediaLibraryService$LibraryParams r3, MediaLibrarySessionImpl r4, MediaSession.ControllerInfo r5, int r6) {
        throw null;
    }

    /* renamed from: $r8$lambda$bvK32Cktts-LHWKTbiPRQQ31Hus, reason: not valid java name */
    public static /* synthetic */ void m918$r8$lambda$bvK32CkttsLHWKTbiPRQQ31Hus(MediaSessionStub r1, MediaSession.ControllerInfo r2, int r3, MediaSessionImpl r4, int r5, SessionTask r6) {
        if (r1.connectedControllersManager.isPlayerCommandAvailable(r2, r3) == true) goto L6;
        sendSessionResult(r4, r2, r5, new SessionResult(-4));
        return;
    L6:
        int r0 = r4.onPlayerCommandRequestOnHandler(r2, r3);
        if (r0 == 0) goto L11;
        sendSessionResult(r4, r2, r5, new SessionResult(r0));
        return;
    L11:
        if (r3 != 27) goto L14;
        r4.callWithControllerForCurrentRequestSet(r2, new MediaSessionStub$$ExternalSyntheticLambda78(r6, r4, r2, r5)).run();
        r1.connectedControllersManager.addToCommandQueue(r2, r3, new MediaSessionStub$$ExternalSyntheticLambda79());
        return;
    L14:
        r1.connectedControllersManager.addToCommandQueue(r2, r3, new MediaSessionStub$$ExternalSyntheticLambda80(r6, r4, r2, r5));
    }

    public static /* synthetic */ void $r8$lambda$cl88C5A9qjQTBKzlFIqOn1ECUEw(MediaSessionStub r0, MediaSession.ControllerInfo r1) {
        r0.connectedControllersManager.flushCommandQueue(r1);
    }

    public static /* synthetic */ void $r8$lambda$crQPEwytYJPQH29XVKZYBjtYIaA(float r0, PlayerWrapper r1) {
        r1.setVolume(r0);
    }

    public static /* synthetic */ void $r8$lambda$eBYEb4hdvmb8_NvJdQcYW_NbeUQ(MediaSessionStub r18, MediaSession.ControllerInfo r19, MediaSessionImpl r20, IMediaController r21) {
        r18.getClass();
        boolean r16 = false;
        r18.pendingControllers.remove(r19);     // Catch: Throwable -> L13
        if (r20.isReleased() == false) goto L7;
        SessionUtil.disconnectIMediaController(r21);
        return;
    L7:
        IBinder r2 = ((Controller2Cb) Assertions.checkStateNotNull((Controller2Cb) r19.getControllerCb())).getCallbackBinder();     // Catch: Throwable -> L13
        MediaSession.ConnectionResult r4 = r20.onConnectOnHandler(r19);     // Catch: Throwable -> L13
        if (r4.isAccepted == true) goto L16;
        if (r19.isTrusted() == true) goto L16;
        SessionUtil.disconnectIMediaController(r21);
        return;
    L16:
        if (r4.isAccepted == true) goto L20;
        r4 = MediaSession.ConnectionResult.accept(SessionCommands.EMPTY, Player.Commands.EMPTY);     // Catch: Throwable -> L13
    L20:
        if (r18.connectedControllersManager.isConnected(r19) == false) goto L22;
        Log.w("MediaSessionStub", "Controller " + r19 + " has sent connection request multiple times");     // Catch: Throwable -> L13
    L22:
        r18.connectedControllersManager.addController(r2, r19, r4.availableSessionCommands, r4.availablePlayerCommands);     // Catch: Throwable -> L13
        SequencedFutureManager r17 = r18.connectedControllersManager.getSequencedFutureManager(r19);     // Catch: Throwable -> L13
        if (r17 != null) goto L27;
        Log.w("MediaSessionStub", "Ignoring connection request from unknown controller info");     // Catch: Throwable -> L13
        SessionUtil.disconnectIMediaController(r21);
        return;
    L27:
        PlayerWrapper r1 = r20.getPlayerWrapper();     // Catch: Throwable -> L13
        PlayerInfo r22 = r20.getPlayerInfo();     // Catch: Throwable -> L13
        PlaybackException r5 = r20.getPlaybackException();     // Catch: Throwable -> L13
        if (r5 != null) goto L31;
        Player.Commands r52 = r4.availablePlayerCommands;     // Catch: Throwable -> L13
    L30:
        Player.Commands r9 = r52;
        PlayerInfo r13 = r18.generateAndCacheUniqueTrackGroupIds(r22);     // Catch: Throwable -> L13
        MediaSession.Token r14 = r20.getPlatformToken();     // Catch: Throwable -> L13
        PendingIntent r23 = r4.sessionActivity;     // Catch: Throwable -> L13
        if (r23 != null) goto L36;
        r23 = r20.getSessionActivity();     // Catch: Throwable -> L13
    L36:
        ImmutableList<CommandButton> r53 = r4.customLayout;     // Catch: Throwable -> L13
        if (r53 != null) goto L40;
        r53 = r20.getCustomLayout();     // Catch: Throwable -> L13
    L40:
        ImmutableList<CommandButton> r6 = r4.mediaButtonPreferences;     // Catch: Throwable -> L13
        if (r6 != null) goto L44;
        r6 = r20.getMediaButtonPreferences();     // Catch: Throwable -> L13
    L44:
        ImmutableList<CommandButton> r7 = r20.getCommandButtonsForMediaItems();     // Catch: Throwable -> L13
        SessionCommands r8 = r4.availableSessionCommands;     // Catch: Throwable -> L13
        Player.Commands r10 = r1.getAvailableCommands();     // Catch: Throwable -> L13
        Bundle r11 = r20.getToken().getExtras();     // Catch: Throwable -> L13
        Bundle r12 = r4.sessionExtras;     // Catch: Throwable -> L13
        if (r12 != null) goto L48;
        r12 = r20.getSessionExtras();     // Catch: Throwable -> L13
    L48:
        ConnectionState r0 = new ConnectionState(1008001300, 5, r18, r23, r53, r6, r7, r8, r9, r10, r11, r12, r13, r14);     // Catch: Throwable -> L13
        if (r20.isReleased() == false) goto L72;
        SessionUtil.disconnectIMediaController(r21);
        return;
    L72:
        IMediaController r24 = r21;
        r24.onConnected(r17.obtainNextSequenceNumber(), r0.toBundleForRemoteProcess(r19.getInterfaceVersion()));     // Catch: Throwable -> L56 RemoteException -> L68
        r16 = true;
    L56:
        th = th;
    L65:
        if (r16 == true) goto L67;
        SessionUtil.disconnectIMediaController(r24);
    L67:
        throw th;
    L59:
        if (r16 == true) goto L61;
    L62:
        if (r16 == true) goto L75;
        SessionUtil.disconnectIMediaController(r24);
        return;
    L75:
        return;
    L61:
        r20.onPostConnectOnHandler(r19);     // Catch: Throwable -> L56
    L58:
        r24 = r21;
        goto L59
    L31:
        r18.connectedControllersManager.setPlaybackException(r19, r5, r4.availablePlayerCommands);     // Catch: Throwable -> L13
        r22 = MediaSessionImpl.createPlayerInfoForCustomPlaybackException(r22, r5);     // Catch: Throwable -> L13
        r52 = (Player.Commands) Assertions.checkNotNull(MediaSessionImpl.createPlayerCommandsForCustomErrorState(r4.availablePlayerCommands));     // Catch: Throwable -> L13
    L13:
        th = th;
        r24 = r21;
        goto L65
    }

    public static /* synthetic */ void $r8$lambda$etKihSPsLDYaqI_bxYU6oxT56tA(PlaybackParameters r0, PlayerWrapper r1) {
        r1.setPlaybackParameters(r0);
    }

    public static /* synthetic */ void $r8$lambda$gEaAkzt4VVcjDYDEwUyHFWeMw6k(boolean r0, PlayerWrapper r1) {
        r1.setPlayWhenReady(r0);
    }

    /* renamed from: $r8$lambda$hKBqIrojPNFCUd8JM-qUZI-XF0k, reason: not valid java name */
    public static /* synthetic */ void m919$r8$lambda$hKBqIrojPNFCUd8JMqUZIXF0k(PlayerWrapper r0) {
        r0.seekToDefaultPosition();
    }

    /* renamed from: $r8$lambda$i2EvuvUx0xjwJMia1GgUeDx-f3A, reason: not valid java name */
    public static /* synthetic */ void m920$r8$lambda$i2EvuvUx0xjwJMia1GgUeDxf3A(Consumer r0, PlayerWrapper r1, MediaSession.ControllerInfo r2) {
        r0.accept(r1);
    }

    public static /* synthetic */ void $r8$lambda$iB7uajzIPvE0KaToblkq9E1BDt4(int r0, int r1, PlayerWrapper r2) {
        r2.moveMediaItem(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$jRN4VrGe4xUGCV7662poTJu0sPM(long r0, PlayerWrapper r2) {
        r2.seekTo(r0);
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$jctfXnkx38NPsW6JGZSFETdj3xU(List r3, int r4, long r5, MediaSessionImpl r7, MediaSession.ControllerInfo r8, int r9) {
        if (r4 != (-1)) goto L5;
        int r0 = r7.getPlayerWrapper().getCurrentMediaItemIndex();
    L6:
        if (r4 != (-1)) goto L8;
        r5 = r7.getPlayerWrapper().getCurrentPosition();
    L8:
        int r6 = r0;
        return r7.onSetMediaItemsOnHandler(r8, r3, r6, r5);
    L5:
        r0 = r4;
        goto L6
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$lbi7uaDr5xncGNoo38cOPym_eO4(String r0, MediaLibrarySessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        throw null;
    }

    /* renamed from: $r8$lambda$lnOMCm1EtshGmAsX3bb8oWGPU-4, reason: not valid java name */
    public static /* synthetic */ ListenableFuture m921$r8$lambda$lnOMCm1EtshGmAsX3bb8oWGPU4(String r0, int r1, int r2, MediaLibraryService$LibraryParams r3, MediaLibrarySessionImpl r4, MediaSession.ControllerInfo r5, int r6) {
        throw null;
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$mdjzG08KjiIcUiSGhLWXonK_2F0(SessionTask r1, MediaSessionImpl r2, MediaSession.ControllerInfo r3, int r4) {
        return handleSessionTaskWhenReady(r2, r3, r4, r1, new MediaSessionStub$$ExternalSyntheticLambda82(r2, r3, r4));
    }

    /* renamed from: $r8$lambda$p7tF0g_H2ubFuPXvGP_KG8Uap-c, reason: not valid java name */
    public static /* synthetic */ ListenableFuture m922$r8$lambda$p7tF0g_H2ubFuPXvGP_KG8Uapc(Rating r0, MediaSessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        return r1.onSetRatingOnHandler(r2, r0);
    }

    /* renamed from: $r8$lambda$qt38YGsAj_nfvJKeCy-Cch587nM, reason: not valid java name */
    public static /* synthetic */ void m923$r8$lambda$qt38YGsAj_nfvJKeCyCch587nM(PlayerWrapper r0, MediaSession.ControllerInfo r1, List r2) {
        r0.addMediaItems(r2);
    }

    public static /* synthetic */ void $r8$lambda$tOp2NyO__sjfWIaPHxX6T0NK2tk(MediaSessionStub r0, int r1, int r2, PlayerWrapper r3, MediaSession.ControllerInfo r4) {
        r3.removeMediaItems(r0.maybeCorrectMediaItemIndex(r4, r3, r1), r0.maybeCorrectMediaItemIndex(r4, r3, r2));
    }

    /* renamed from: $r8$lambda$tn3XQe-cIU9mXM0WRHN4Oi6Ff1E, reason: not valid java name */
    public static /* synthetic */ void m924$r8$lambda$tn3XQecIU9mXM0WRHN4Oi6Ff1E(MediaSessionStub r0, int r1, int r2, PlayerWrapper r3, MediaSession.ControllerInfo r4, List r5) {
        r3.replaceMediaItems(r0.maybeCorrectMediaItemIndex(r4, r3, r1), r0.maybeCorrectMediaItemIndex(r4, r3, r2), r5);
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$vZhZQF2vW4WdI1dX10QEfSas5VE(SessionCommand r0, Bundle r1, MediaSessionImpl r2, MediaSession.ControllerInfo r3, int r4) {
        return r2.onCustomCommandOnHandler(r3, r0, r1);
    }

    /* renamed from: $r8$lambda$vjsvpMxv0--OaBZA18qdHn4Bra8, reason: not valid java name */
    public static /* synthetic */ void m925$r8$lambda$vjsvpMxv0OaBZA18qdHn4Bra8(SessionTask r0, MediaSessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        r0.run(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$voxCYz7Fjc-o7fKBP-tN_S3Exok, reason: not valid java name */
    public static /* synthetic */ void m926$r8$lambda$voxCYz7Fjco7fKBPtN_S3Exok(AudioAttributes r0, boolean r1, PlayerWrapper r2) {
        r2.setAudioAttributes(r0, r1);
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$w6qIkBSRZSZXrnt4gax8PQOK6u8(ControllerPlayerTask r1, MediaSessionImpl r2, MediaSession.ControllerInfo r3, int r4) {
        if (r2.isReleased() == true) goto L5;
        r1.run(r2.getPlayerWrapper(), r3);
        sendSessionResult(r2, r3, r4, new SessionResult(0));
        return Futures.immediateVoidFuture();
    L5:
        return Futures.immediateVoidFuture();
    }

    /* renamed from: $r8$lambda$wMocS4AH4xsCwAU3JbOpgcmZu-g, reason: not valid java name */
    public static /* synthetic */ void m927$r8$lambda$wMocS4AH4xsCwAU3JbOpgcmZug(MediaSessionStub r0, MediaSession.ControllerInfo r1, PlayerWrapper r2) {
        MediaSessionImpl r02 = r0.sessionImpl.get();
        if (r02 != null) goto L5;
        return;
    L5:
        if (r02.isReleased() == true) goto L10;
        r02.handleMediaControllerPlayRequest(r1, false);
        return;
    }

    public static /* synthetic */ void $r8$lambda$wfDxSfPfVlMZB5zb3giyUnuOv18(MediaSessionStub r0, TrackSelectionParameters r1, PlayerWrapper r2) {
        r2.setTrackSelectionParameters(r0.updateOverridesUsingUniqueTrackGroupIds(r1));
    }

    public static /* synthetic */ void $r8$lambda$x1ODFKMtyhVuSULPq4PvEFD4jyo(MediaSessionStub r0, int r1, PlayerWrapper r2, MediaSession.ControllerInfo r3, List r4) {
        r2.addMediaItems(r0.maybeCorrectMediaItemIndex(r3, r2, r1), r4);
    }

    public static /* synthetic */ void $r8$lambda$yJgpcYXXKZF9DSZCkx_tkq7p_pA(MediaSessionImpl r1, MediaItemPlayerTask r2, MediaSession.ControllerInfo r3, List r4) {
        if (r1.isReleased() == true) goto L6;
        r2.run(r1.getPlayerWrapper(), r3, r4);
        return;
    }

    public static /* synthetic */ void $r8$lambda$zNvv3J7cSmshUkEZlCtyHBdu4zw(MediaSessionImpl r1, SettableFuture r2, Consumer r3, ListenableFuture r4) {
        if (r1.isReleased() == false) goto L11;
        r2.set(null);
        return;
    L11:
        r3.accept(r4);     // Catch: Throwable -> L8
        r2.set(null);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        r2.setException(th);
    }

    public MediaSessionStub(MediaSessionImpl r2) {
        this.sessionImpl = new WeakReference(r2);
        this.connectedControllersManager = new ConnectedControllersManager(r2);
        this.pendingControllers = Collections.synchronizedSet(new HashSet());
        this.trackGroupIdMap = ImmutableBiMap.of();
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController r7, int r8, int r9, SessionTask<ListenableFuture<Void>, K> r10) {
        dispatchSessionTaskWithSessionCommand(r7, r8, null, r9, r10);
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController r12, int r13, SessionCommand r14, int r15, SessionTask<ListenableFuture<Void>, K> r16) {
        long r1 = Binder.clearCallingIdentity();
        MediaSessionImpl r7 = this.sessionImpl.get();     // Catch: Throwable -> L15
        if (r7 != null) goto L6;
    L17:
        Binder.restoreCallingIdentity(r1);
        return;
    L6:
        if (r7.isReleased() == true) goto L17;
        MediaSession.ControllerInfo r5 = this.connectedControllersManager.getController(r12.asBinder());     // Catch: Throwable -> L15
        if (r5 != null) goto L12;
        Binder.restoreCallingIdentity(r1);
        return;
    L12:
        Util.postOrRun(r7.getApplicationHandler(), new MediaSessionStub$$ExternalSyntheticLambda69(this, r5, r14, r7, r13, r15, r16));     // Catch: Throwable -> L15
        Binder.restoreCallingIdentity(r1);
        return;
    L15:
        th = move-exception;
        Binder.restoreCallingIdentity(r1);
        throw th;
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController r7, int r8, SessionCommand r9, SessionTask<ListenableFuture<Void>, K> r10) {
        dispatchSessionTaskWithSessionCommand(r7, r8, r9, 0, r10);
    }

    private String generateUniqueTrackGroupId(TrackGroup r4) {
        StringBuilder r0 = new StringBuilder();
        int r1 = this.nextUniqueTrackGroupIdPrefix;
        this.nextUniqueTrackGroupIdPrefix = r1 + 1;
        r0.append(Util.intToStringMaxRadix(r1));
        r0.append("-");
        r0.append(r4.id);
        return r0.toString();
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<SessionResult>, K> handleMediaItemsWhenReady(SessionTask<ListenableFuture<List<MediaItem>>, K> r1, MediaItemPlayerTask r2) {
        return new MediaSessionStub$$ExternalSyntheticLambda75(r1, r2);
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<SessionResult>, K> handleMediaItemsWithStartPositionWhenReady(SessionTask<ListenableFuture<MediaSession.MediaItemsWithStartPosition>, K> r1, MediaItemsWithStartPositionPlayerTask r2) {
        return new MediaSessionStub$$ExternalSyntheticLambda71(r1, r2);
    }

    private static <T, K extends MediaSessionImpl> ListenableFuture<Void> handleSessionTaskWhenReady(K r1, MediaSession.ControllerInfo r2, int r3, SessionTask<ListenableFuture<T>, K> r4, Consumer<ListenableFuture<T>> r5) {
        if (r1.isReleased() == true) goto L5;
        ListenableFuture<T> r22 = r4.run(r1, r2, r3);
        SettableFuture r32 = SettableFuture.create();
        r22.addListener(new MediaSessionStub$$ExternalSyntheticLambda83(r1, r32, r5, r22), MoreExecutors.directExecutor());
        return r32;
    L5:
        return Futures.immediateVoidFuture();
    }

    private int maybeCorrectMediaItemIndex(MediaSession.ControllerInfo r3, PlayerWrapper r4, int r5) {
        if (r4.isCommandAvailable(17) == true) goto L5;
        return r5;
    L5:
        if (this.connectedControllersManager.isPlayerCommandAvailable(r3, 17) == false) goto L7;
        return r5;
    L7:
        if (this.connectedControllersManager.isPlayerCommandAvailable(r3, 16) == true) goto L9;
        return r5;
    L9:
        return r5 + r4.getCurrentMediaItemIndex();
    }

    private <K extends MediaSessionImpl> void queueSessionTaskWithPlayerCommand(IMediaController r2, int r3, int r4, SessionTask<ListenableFuture<Void>, K> r5) {
        MediaSession.ControllerInfo r22 = this.connectedControllersManager.getController(r2.asBinder());
        if (r22 == null) goto L6;
        queueSessionTaskWithPlayerCommandForControllerInfo(r22, r3, r4, r5);
        return;
    }

    private <K extends MediaSessionImpl> void queueSessionTaskWithPlayerCommandForControllerInfo(MediaSession.ControllerInfo r11, int r12, int r13, SessionTask<ListenableFuture<Void>, K> r14) {
        long r1 = Binder.clearCallingIdentity();
        MediaSessionImpl r7 = this.sessionImpl.get();     // Catch: Throwable -> L11
        if (r7 != null) goto L6;
    L13:
        Binder.restoreCallingIdentity(r1);
        return;
    L6:
        if (r7.isReleased() == true) goto L13;
        Util.postOrRun(r7.getApplicationHandler(), new MediaSessionStub$$ExternalSyntheticLambda70(this, r11, r13, r7, r12, r14));     // Catch: Throwable -> L11
        Binder.restoreCallingIdentity(r1);
        return;
    L11:
        th = move-exception;
        Binder.restoreCallingIdentity(r1);
        throw th;
    }

    private static void sendLibraryResult(MediaSession.ControllerInfo r1, int r2, LibraryResult<?> r3) {
        ((MediaSession.ControllerCb) Assertions.checkStateNotNull(r1.getControllerCb())).onLibraryResult(r2, r3);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        Log.w("MediaSessionStub", "Failed to send result to browser " + r1, e);
    }

    private static <V, K extends MediaLibrarySessionImpl> SessionTask<ListenableFuture<Void>, K> sendLibraryResultWhenReady(SessionTask<ListenableFuture<LibraryResult<V>>, K> r1) {
        return new MediaSessionStub$$ExternalSyntheticLambda73(r1);
    }

    private static void sendSessionResult(MediaSessionImpl r1, MediaSession.ControllerInfo r2, int r3, SessionResult r4) {
        ((MediaSession.ControllerCb) Assertions.checkStateNotNull(r2.getControllerCb())).onSessionResult(r3, r4);     // Catch: RemoteException -> L4
        r1.triggerPlayerInfoUpdate();     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        Log.w("MediaSessionStub", "Failed to send result to controller " + r2, e);
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<Void>, K> sendSessionResultSuccess(Consumer<PlayerWrapper> r1) {
        return sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda72(r1));
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<Void>, K> sendSessionResultSuccess(ControllerPlayerTask r1) {
        return new MediaSessionStub$$ExternalSyntheticLambda68(r1);
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<Void>, K> sendSessionResultWhenReady(SessionTask<ListenableFuture<SessionResult>, K> r1) {
        return new MediaSessionStub$$ExternalSyntheticLambda74(r1);
    }

    private TrackSelectionParameters updateOverridesUsingUniqueTrackGroupIds(TrackSelectionParameters r6) {
        if (r6.overrides.isEmpty() == false) goto L5;
        return r6;
    L5:
        TrackSelectionParameters.Builder r0 = r6.buildUpon().clearOverrides();
        UnmodifiableIterator<TrackSelectionOverride> r62 = r6.overrides.values().iterator();
    L7:
        if (r62.hasNext() == false) goto L15;
        TrackSelectionOverride r1 = r62.next();
        TrackGroup r2 = r1.mediaTrackGroup;
        TrackGroup r22 = this.trackGroupIdMap.inverse().get(r2.id);
        if (r22 == null) goto L13;
        if (r1.mediaTrackGroup.length != r22.length) goto L13;
        r0.addOverride(new TrackSelectionOverride(r22, r1.trackIndices));
    L13:
        r0.addOverride(r1);
        goto L7
    L15:
        return r0.build();
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItem(IMediaController r2, int r3, Bundle r4) {
        if (r2 == null) goto L13;
        if (r4 == null) goto L14;
        queueSessionTaskWithPlayerCommand(r2, r3, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new MediaSessionStub$$ExternalSyntheticLambda50(MediaItem.fromBundle(r4)), new MediaSessionStub$$ExternalSyntheticLambda51())));
        return;
    L8:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e);
        return;
    L14:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItemWithIndex(IMediaController r2, int r3, int r4, Bundle r5) {
        if (r2 == null) goto L14;
        if (r5 == null) goto L15;
        if (r4 < 0) goto L16;
        queueSessionTaskWithPlayerCommand(r2, r3, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new MediaSessionStub$$ExternalSyntheticLambda21(MediaItem.fromBundle(r5)), new MediaSessionStub$$ExternalSyntheticLambda22(this, r4))));
        return;
    L9:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e);
        return;
    L16:
        return;
    L15:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItems(IMediaController r2, int r3, IBinder r4) {
        if (r2 == null) goto L13;
        if (r4 == null) goto L14;
        queueSessionTaskWithPlayerCommand(r2, r3, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new MediaSessionStub$$ExternalSyntheticLambda48(BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda7(), BundleListRetriever.getList(r4))), new MediaSessionStub$$ExternalSyntheticLambda49())));
        return;
    L8:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e);
        return;
    L14:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItemsWithIndex(IMediaController r2, int r3, int r4, IBinder r5) {
        if (r2 == null) goto L14;
        if (r5 == null) goto L15;
        if (r4 < 0) goto L16;
        queueSessionTaskWithPlayerCommand(r2, r3, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new MediaSessionStub$$ExternalSyntheticLambda33(BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda7(), BundleListRetriever.getList(r5))), new MediaSessionStub$$ExternalSyntheticLambda34(this, r4))));
        return;
    L9:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e);
        return;
    L16:
        return;
    L15:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void clearMediaItems(IMediaController r3, int r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r3, r4, 20, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda66()));
    }

    @Override // androidx.media3.session.IMediaSession
    public void connect(IMediaController r12, int r13, Bundle r14) {
        if (r12 == null) goto L31;
        if (r14 == null) goto L32;
        ConnectionRequest r132 = ConnectionRequest.fromBundle(r14);     // Catch: RuntimeException -> L24
        int r142 = Binder.getCallingUid();
        int r0 = Binder.getCallingPid();
        long r1 = Binder.clearCallingIdentity();
        if (r0 != 0) goto L29;
        r0 = r132.pid;
    L29:
        MediaSessionManager.RemoteUserInfo r4 = new MediaSessionManager.RemoteUserInfo(r132.packageName, r0, r142);     // Catch: Throwable -> L16
        MediaSessionImpl r143 = this.sessionImpl.get();     // Catch: Throwable -> L16
        if (r143 != null) goto L13;
    L18:
        boolean r144 = false;
    L15:
        boolean r7 = r144;
        int r5 = r132.libraryVersion;     // Catch: Throwable -> L16
        int r6 = r132.controllerInterfaceVersion;     // Catch: Throwable -> L16
        connect(r12, new MediaSession.ControllerInfo(r4, r5, r6, r7, new Controller2Cb(r12, r6), r132.connectionHints, r132.maxCommandsForMediaItems));     // Catch: Throwable -> L16
        Binder.restoreCallingIdentity(r1);
        return;
    L13:
        if (MediaSessionManager.getSessionManager(r143.getContext()).isTrustedForMediaControl(r4) == false) goto L18;
        r144 = true;
    L16:
        th = move-exception;
        Binder.restoreCallingIdentity(r1);
        throw th;
    L24:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for ConnectionRequest", e);
        return;
    L32:
        return;
    }

    public void connect(IMediaController r4, MediaSession.ControllerInfo r5) {
        if (r4 == null) goto L14;
        if (r5 == null) goto L14;
        MediaSessionImpl r0 = this.sessionImpl.get();
        if (r0 != null) goto L8;
    L12:
        SessionUtil.disconnectIMediaController(r4);
        return;
    L8:
        if (r0.isReleased() == true) goto L12;
        this.pendingControllers.add(r5);
        Util.postOrRun(r0.getApplicationHandler(), new MediaSessionStub$$ExternalSyntheticLambda3(this, r5, r0, r4));
        return;
    L14:
        SessionUtil.disconnectIMediaController(r4);
    }

    @Override // androidx.media3.session.IMediaSession
    public void decreaseDeviceVolume(IMediaController r3, int r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r3, r4, 26, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda4()));
    }

    @Override // androidx.media3.session.IMediaSession
    public void decreaseDeviceVolumeWithFlags(IMediaController r2, int r3, int r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r2, r3, 34, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda2(r4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void flushCommandQueue(IMediaController r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        long r0 = Binder.clearCallingIdentity();
        MediaSessionImpl r2 = this.sessionImpl.get();     // Catch: Throwable -> L14
        if (r2 != null) goto L8;
    L18:
        Binder.restoreCallingIdentity(r0);
        return;
    L8:
        if (r2.isReleased() == true) goto L18;
        MediaSession.ControllerInfo r52 = this.connectedControllersManager.getController(r5.asBinder());     // Catch: Throwable -> L14
        if (r52 == null) goto L16;
        Util.postOrRun(r2.getApplicationHandler(), new MediaSessionStub$$ExternalSyntheticLambda37(this, r52));     // Catch: Throwable -> L14
    L16:
        Binder.restoreCallingIdentity(r0);
        return;
    L14:
        th = move-exception;
        Binder.restoreCallingIdentity(r0);
        throw th;
    }

    public PlayerInfo generateAndCacheUniqueTrackGroupIds(PlayerInfo r8) {
        ImmutableList<Tracks.Group> r0 = r8.currentTracks.getGroups();
        ImmutableList.Builder r1 = ImmutableList.builder();
        ImmutableBiMap.Builder r2 = ImmutableBiMap.builder();
        int r3 = 0;
    L4:
        if (r3 >= r0.size()) goto L9;
        Tracks.Group r4 = r0.get(r3);
        TrackGroup r5 = r4.getMediaTrackGroup();
        String r6 = this.trackGroupIdMap.get(r5);
        if (r6 != null) goto L8;
        r6 = generateUniqueTrackGroupId(r5);
    L8:
        r2.put(r5, r6);
        r1.add(r4.copyWithId(r6));
        r3 = r3 + 1;
        goto L4
    L9:
        this.trackGroupIdMap = r2.buildOrThrow();
        PlayerInfo r82 = r8.copyWithCurrentTracks(new Tracks(r1.build()));
        if (r82.trackSelectionParameters.overrides.isEmpty() == false) goto L12;
        return r82;
    L12:
        TrackSelectionParameters.Builder r02 = r82.trackSelectionParameters.buildUpon().clearOverrides();
        UnmodifiableIterator<TrackSelectionOverride> r12 = r82.trackSelectionParameters.overrides.values().iterator();
    L14:
        if (r12.hasNext() == false) goto L20;
        TrackSelectionOverride r22 = r12.next();
        TrackGroup r32 = r22.mediaTrackGroup;
        String r42 = this.trackGroupIdMap.get(r32);
        if (r42 != null) goto L17;
        r02.addOverride(r22);
        goto L14
    L17:
        r02.addOverride(new TrackSelectionOverride(r32.copyWithId(r42), r22.trackIndices));
        goto L14
    L20:
        return r82.copyWithTrackSelectionParameters(r02.build());
    }

    @Override // androidx.media3.session.IMediaSession
    public void getChildren(IMediaController r3, int r4, String r5, int r6, int r7, Bundle r8) {
        if (r3 != null) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r5) == false) goto L8;
        Log.w("MediaSessionStub", "getChildren(): Ignoring empty parentId");
        return;
    L8:
        if (r6 >= 0) goto L12;
        Log.w("MediaSessionStub", "getChildren(): Ignoring negative page");
        return;
    L12:
        if (r7 >= 1) goto L15;
        Log.w("MediaSessionStub", "getChildren(): Ignoring pageSize less than 1");
        return;
    L15:
        if (r8 != null) goto L23;
        MediaLibraryService$LibraryParams r82 = null;
    L18:
        dispatchSessionTaskWithSessionCommand(r3, r4, 50003, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda12(r5, r6, r7, r82)));
        return;
    L23:
        r82 = MediaLibraryService$LibraryParams.fromBundle(r8);     // Catch: RuntimeException -> L20
    L20:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for LibraryParams", e);
    }

    public ConnectedControllersManager<IBinder> getConnectedControllersManager() {
        return this.connectedControllersManager;
    }

    @Override // androidx.media3.session.IMediaSession
    public void getItem(IMediaController r2, int r3, String r4) {
        if (r2 != null) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r4) == false) goto L8;
        Log.w("MediaSessionStub", "getItem(): Ignoring empty mediaId");
        return;
    L8:
        dispatchSessionTaskWithSessionCommand(r2, r3, 50004, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda41(r4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void getLibraryRoot(IMediaController r2, int r3, Bundle r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r4 != null) goto L12;
        MediaLibraryService$LibraryParams r42 = null;
    L7:
        dispatchSessionTaskWithSessionCommand(r2, r3, 50000, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda57(r42)));
        return;
    L12:
        r42 = MediaLibraryService$LibraryParams.fromBundle(r4);     // Catch: RuntimeException -> L9
    L9:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for LibraryParams", e);
    }

    @Override // androidx.media3.session.IMediaSession
    public void getSearchResult(IMediaController r3, int r4, String r5, int r6, int r7, Bundle r8) {
        if (r3 != null) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r5) == false) goto L8;
        Log.w("MediaSessionStub", "getSearchResult(): Ignoring empty query");
        return;
    L8:
        if (r6 >= 0) goto L12;
        Log.w("MediaSessionStub", "getSearchResult(): Ignoring negative page");
        return;
    L12:
        if (r7 >= 1) goto L15;
        Log.w("MediaSessionStub", "getSearchResult(): Ignoring pageSize less than 1");
        return;
    L15:
        if (r8 != null) goto L23;
        MediaLibraryService$LibraryParams r82 = null;
    L18:
        dispatchSessionTaskWithSessionCommand(r3, r4, 50006, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda52(r5, r6, r7, r82)));
        return;
    L23:
        r82 = MediaLibraryService$LibraryParams.fromBundle(r8);     // Catch: RuntimeException -> L20
    L20:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for LibraryParams", e);
    }

    @Override // androidx.media3.session.IMediaSession
    public void increaseDeviceVolume(IMediaController r3, int r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r3, r4, 26, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda24()));
    }

    @Override // androidx.media3.session.IMediaSession
    public void increaseDeviceVolumeWithFlags(IMediaController r2, int r3, int r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r2, r3, 34, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda36(r4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void moveMediaItem(IMediaController r2, int r3, int r4, int r5) {
        if (r2 == null) goto L8;
        if (r4 < 0) goto L9;
        if (r5 < 0) goto L10;
        queueSessionTaskWithPlayerCommand(r2, r3, 20, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda63(r4, r5)));
        return;
    L10:
        return;
    L9:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void moveMediaItems(IMediaController r2, int r3, int r4, int r5, int r6) {
        if (r2 == null) goto L9;
        if (r4 < 0) goto L10;
        if (r5 < r4) goto L11;
        if (r6 < 0) goto L12;
        queueSessionTaskWithPlayerCommand(r2, r3, 20, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda39(r4, r5, r6)));
        return;
    L12:
        return;
    L11:
        return;
    L10:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void onControllerResult(IMediaController r3, int r4, Bundle r5) {
        if (r3 == null) goto L24;
        if (r5 == null) goto L25;
        SessionResult r52 = SessionResult.fromBundle(r5);     // Catch: RuntimeException -> L17
        long r0 = Binder.clearCallingIdentity();
        SequencedFutureManager r2 = this.connectedControllersManager.getSequencedFutureManager(r3.asBinder());     // Catch: Throwable -> L14
        if (r2 != null) goto L11;
        Binder.restoreCallingIdentity(r0);
        return;
    L11:
        r2.setFutureResult(r4, r52);     // Catch: Throwable -> L14
        Binder.restoreCallingIdentity(r0);
        return;
    L14:
        th = move-exception;
        Binder.restoreCallingIdentity(r0);
        throw th;
    L17:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for SessionResult", e);
        return;
    L25:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void onCustomCommand(IMediaController r2, int r3, Bundle r4, Bundle r5) {
        if (r2 == null) goto L14;
        if (r4 == null) goto L15;
        if (r5 == null) goto L16;
        SessionCommand r42 = SessionCommand.fromBundle(r4);     // Catch: RuntimeException -> L9
        dispatchSessionTaskWithSessionCommand(r2, r3, r42, sendSessionResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda29(r42, r5)));
        return;
    L9:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for SessionCommand", e);
        return;
    L16:
        return;
    L15:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void pause(IMediaController r2, int r3) {
        if (r2 == null) goto L9;
        MediaSession.ControllerInfo r22 = this.connectedControllersManager.getController(r2.asBinder());
        if (r22 == null) goto L8;
        pauseForControllerInfo(r22, r3);
        return;
    L8:
        return;
    }

    public void pauseForControllerInfo(MediaSession.ControllerInfo r3, int r4) {
        queueSessionTaskWithPlayerCommandForControllerInfo(r3, r4, 1, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda20()));
    }

    @Override // androidx.media3.session.IMediaSession
    public void play(IMediaController r2, int r3) {
        if (r2 == null) goto L9;
        MediaSession.ControllerInfo r22 = this.connectedControllersManager.getController(r2.asBinder());
        if (r22 == null) goto L8;
        playForControllerInfo(r22, r3);
        return;
    L8:
        return;
    }

    public void playForControllerInfo(MediaSession.ControllerInfo r3, int r4) {
        queueSessionTaskWithPlayerCommandForControllerInfo(r3, r4, 1, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda35(this, r3)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void prepare(IMediaController r3, int r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r3, r4, 2, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda58()));
    }

    public void release() {
        ImmutableList<MediaSession.ControllerInfo> r0 = this.connectedControllersManager.getConnectedControllers();
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L7;
        MediaSession.ControllerInfo r4 = r0.get(r3);
        r3 = r3 + 1;
        MediaSession.ControllerInfo r42 = r4;
        this.connectedControllersManager.removeController(r42);
        MediaSession.ControllerCb r43 = r42.getControllerCb();
        if (r43 == null) goto L3;
        r43.onDisconnected(0);
        goto L3
    L7:
        Iterator<MediaSession.ControllerInfo> r02 = this.pendingControllers.iterator();
    L9:
        if (r02.hasNext() == false) goto L13;
        MediaSession.ControllerCb r12 = r02.next().getControllerCb();
        if (r12 == null) goto L9;
        r12.onDisconnected(0);
        goto L9
    L13:
        this.pendingControllers.clear();
        this.sessionImpl.clear();
    }

    @Override // androidx.media3.session.IMediaSession
    public void release(IMediaController r4, int r5) {
        if (r4 != null) goto L4;
        return;
    L4:
        long r0 = Binder.clearCallingIdentity();
        MediaSessionImpl r52 = this.sessionImpl.get();     // Catch: Throwable -> L13
        if (r52 != null) goto L8;
    L15:
        Binder.restoreCallingIdentity(r0);
        return;
    L8:
        if (r52.isReleased() == true) goto L15;
        Util.postOrRun(r52.getApplicationHandler(), new MediaSessionStub$$ExternalSyntheticLambda13(this, r4));     // Catch: Throwable -> L13
        Binder.restoreCallingIdentity(r0);
        return;
    L13:
        th = move-exception;
        Binder.restoreCallingIdentity(r0);
        throw th;
    }

    @Override // androidx.media3.session.IMediaSession
    public void removeMediaItem(IMediaController r2, int r3, int r4) {
        if (r2 == null) goto L7;
        if (r4 < 0) goto L8;
        queueSessionTaskWithPlayerCommand(r2, r3, 20, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda6(this, r4)));
        return;
    L8:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void removeMediaItems(IMediaController r2, int r3, int r4, int r5) {
        if (r2 == null) goto L8;
        if (r4 < 0) goto L9;
        if (r5 < r4) goto L10;
        queueSessionTaskWithPlayerCommand(r2, r3, 20, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda0(this, r4, r5)));
        return;
    L10:
        return;
    L9:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void replaceMediaItem(IMediaController r2, int r3, int r4, Bundle r5) {
        if (r2 == null) goto L14;
        if (r5 == null) goto L15;
        if (r4 < 0) goto L16;
        queueSessionTaskWithPlayerCommand(r2, r3, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new MediaSessionStub$$ExternalSyntheticLambda25(MediaItem.fromBundle(r5)), new MediaSessionStub$$ExternalSyntheticLambda26(this, r4))));
        return;
    L9:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e);
        return;
    L16:
        return;
    L15:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void replaceMediaItems(IMediaController r2, int r3, int r4, int r5, IBinder r6) {
        if (r2 == null) goto L15;
        if (r6 == null) goto L16;
        if (r4 < 0) goto L17;
        if (r5 < r4) goto L18;
        queueSessionTaskWithPlayerCommand(r2, r3, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new MediaSessionStub$$ExternalSyntheticLambda14(BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda7(), BundleListRetriever.getList(r6))), new MediaSessionStub$$ExternalSyntheticLambda15(this, r4, r5))));
        return;
    L10:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e);
        return;
    L18:
        return;
    L17:
        return;
    L16:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void search(IMediaController r3, int r4, String r5, Bundle r6) {
        if (r3 != null) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r5) == false) goto L8;
        Log.w("MediaSessionStub", "search(): Ignoring empty query");
        return;
    L8:
        if (r6 != null) goto L16;
        MediaLibraryService$LibraryParams r62 = null;
    L11:
        dispatchSessionTaskWithSessionCommand(r3, r4, 50005, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda67(r5, r62)));
        return;
    L16:
        r62 = MediaLibraryService$LibraryParams.fromBundle(r6);     // Catch: RuntimeException -> L13
    L13:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for LibraryParams", e);
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekBack(IMediaController r2, int r3) {
        if (r2 == null) goto L9;
        MediaSession.ControllerInfo r22 = this.connectedControllersManager.getController(r2.asBinder());
        if (r22 == null) goto L8;
        seekBackForControllerInfo(r22, r3);
        return;
    L8:
        return;
    }

    public void seekBackForControllerInfo(MediaSession.ControllerInfo r3, int r4) {
        queueSessionTaskWithPlayerCommandForControllerInfo(r3, r4, 11, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda27()));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekForward(IMediaController r2, int r3) {
        if (r2 == null) goto L9;
        MediaSession.ControllerInfo r22 = this.connectedControllersManager.getController(r2.asBinder());
        if (r22 == null) goto L8;
        seekForwardForControllerInfo(r22, r3);
        return;
    L8:
        return;
    }

    public void seekForwardForControllerInfo(MediaSession.ControllerInfo r3, int r4) {
        queueSessionTaskWithPlayerCommandForControllerInfo(r3, r4, 12, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda42()));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekTo(IMediaController r2, int r3, long r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r2, r3, 5, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda65(r4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToDefaultPosition(IMediaController r3, int r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r3, r4, 4, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda5()));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToDefaultPositionWithMediaItemIndex(IMediaController r2, int r3, int r4) {
        if (r2 == null) goto L7;
        if (r4 < 0) goto L8;
        queueSessionTaskWithPlayerCommand(r2, r3, 10, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda16(this, r4)));
        return;
    L8:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToNext(IMediaController r2, int r3) {
        if (r2 == null) goto L9;
        MediaSession.ControllerInfo r22 = this.connectedControllersManager.getController(r2.asBinder());
        if (r22 == null) goto L8;
        seekToNextForControllerInfo(r22, r3);
        return;
    L8:
        return;
    }

    public void seekToNextForControllerInfo(MediaSession.ControllerInfo r3, int r4) {
        queueSessionTaskWithPlayerCommandForControllerInfo(r3, r4, 9, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda43()));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToNextMediaItem(IMediaController r3, int r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r3, r4, 8, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda23()));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToPrevious(IMediaController r2, int r3) {
        if (r2 == null) goto L9;
        MediaSession.ControllerInfo r22 = this.connectedControllersManager.getController(r2.asBinder());
        if (r22 == null) goto L8;
        seekToPreviousForControllerInfo(r22, r3);
        return;
    L8:
        return;
    }

    public void seekToPreviousForControllerInfo(MediaSession.ControllerInfo r3, int r4) {
        queueSessionTaskWithPlayerCommandForControllerInfo(r3, r4, 7, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda32()));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToPreviousMediaItem(IMediaController r3, int r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r3, r4, 6, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda38()));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToWithMediaItemIndex(IMediaController r2, int r3, int r4, long r5) {
        if (r2 == null) goto L7;
        if (r4 < 0) goto L8;
        queueSessionTaskWithPlayerCommand(r2, r3, 10, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda19(this, r4, r5)));
        return;
    L8:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setAudioAttributes(IMediaController r2, int r3, Bundle r4, boolean r5) {
        if (r2 == null) goto L13;
        if (r4 == null) goto L14;
        queueSessionTaskWithPlayerCommand(r2, r3, 35, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda47(AudioAttributes.fromBundle(r4), r5)));
        return;
    L8:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for AudioAttributes", e);
        return;
    L14:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceMuted(IMediaController r2, int r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r2, r3, 26, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda28(r4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceMutedWithFlags(IMediaController r2, int r3, boolean r4, int r5) {
        if (r2 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r2, r3, 34, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda59(r4, r5)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceVolume(IMediaController r2, int r3, int r4) {
        if (r2 == null) goto L7;
        if (r4 < 0) goto L8;
        queueSessionTaskWithPlayerCommand(r2, r3, 25, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda64(r4)));
        return;
    L8:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceVolumeWithFlags(IMediaController r2, int r3, int r4, int r5) {
        if (r2 == null) goto L7;
        if (r4 < 0) goto L8;
        queueSessionTaskWithPlayerCommand(r2, r3, 33, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda45(r4, r5)));
        return;
    L8:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItem(IMediaController r2, int r3, Bundle r4) {
        setMediaItemWithResetPosition(r2, r3, r4, true);
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemWithResetPosition(IMediaController r2, int r3, Bundle r4, boolean r5) {
        if (r2 == null) goto L13;
        if (r4 == null) goto L14;
        queueSessionTaskWithPlayerCommand(r2, r3, 31, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new MediaSessionStub$$ExternalSyntheticLambda31(MediaItem.fromBundle(r4), r5), new MediaSessionStub$$ExternalSyntheticLambda9())));
        return;
    L8:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e);
        return;
    L14:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemWithStartPosition(IMediaController r2, int r3, Bundle r4, long r5) {
        if (r2 == null) goto L13;
        if (r4 == null) goto L14;
        queueSessionTaskWithPlayerCommand(r2, r3, 31, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new MediaSessionStub$$ExternalSyntheticLambda61(MediaItem.fromBundle(r4), r5), new MediaSessionStub$$ExternalSyntheticLambda9())));
        return;
    L8:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e);
        return;
    L14:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItems(IMediaController r2, int r3, IBinder r4) {
        setMediaItemsWithResetPosition(r2, r3, r4, true);
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemsWithResetPosition(IMediaController r2, int r3, IBinder r4, boolean r5) {
        if (r2 == null) goto L13;
        if (r4 == null) goto L14;
        queueSessionTaskWithPlayerCommand(r2, r3, 20, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new MediaSessionStub$$ExternalSyntheticLambda8(BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda7(), BundleListRetriever.getList(r4)), r5), new MediaSessionStub$$ExternalSyntheticLambda9())));
        return;
    L8:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e);
        return;
    L14:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemsWithStartIndex(IMediaController r2, int r3, IBinder r4, int r5, long r6) {
        if (r2 == null) goto L16;
        if (r4 != null) goto L5;
        return;
    L5:
        if (r5 == (-1)) goto L14;
        if (r5 >= 0) goto L14;
        return;
    L14:
        queueSessionTaskWithPlayerCommand(r2, r3, 20, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new MediaSessionStub$$ExternalSyntheticLambda40(BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda7(), BundleListRetriever.getList(r4)), r5, r6), new MediaSessionStub$$ExternalSyntheticLambda9())));
        return;
    L11:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e);
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlayWhenReady(IMediaController r2, int r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r2, r3, 1, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda17(r4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlaybackParameters(IMediaController r2, int r3, Bundle r4) {
        if (r2 == null) goto L13;
        if (r4 == null) goto L14;
        queueSessionTaskWithPlayerCommand(r2, r3, 13, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda30(PlaybackParameters.fromBundle(r4))));
        return;
    L8:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for PlaybackParameters", e);
        return;
    L14:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlaybackSpeed(IMediaController r2, int r3, float r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r4 <= 0.0f) goto L9;
        queueSessionTaskWithPlayerCommand(r2, r3, 13, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda18(r4)));
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlaylistMetadata(IMediaController r2, int r3, Bundle r4) {
        if (r2 == null) goto L13;
        if (r4 == null) goto L14;
        queueSessionTaskWithPlayerCommand(r2, r3, 19, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda60(MediaMetadata.fromBundle(r4))));
        return;
    L8:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for MediaMetadata", e);
        return;
    L14:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setRating(IMediaController r2, int r3, Bundle r4) {
        if (r2 == null) goto L13;
        if (r4 == null) goto L14;
        dispatchSessionTaskWithSessionCommand(r2, r3, 40010, sendSessionResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda10(Rating.fromBundle(r4))));
        return;
    L8:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for Rating", e);
        return;
    L14:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setRatingWithMediaId(IMediaController r3, int r4, String r5, Bundle r6) {
        if (r3 == null) goto L18;
        if (r5 == null) goto L19;
        if (r6 != null) goto L7;
        return;
    L7:
        if (TextUtils.isEmpty(r5) == false) goto L16;
        Log.w("MediaSessionStub", "setRatingWithMediaId(): Ignoring empty mediaId");
        return;
    L16:
        dispatchSessionTaskWithSessionCommand(r3, r4, 40010, sendSessionResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda44(r5, Rating.fromBundle(r6))));
        return;
    L13:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for Rating", e);
        return;
    L19:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setRepeatMode(IMediaController r2, int r3, int r4) {
        if (r2 != null) goto L5;
        return;
    L5:
        if (r4 == 2) goto L10;
        if (r4 == 0) goto L10;
        if (r4 == 1) goto L10;
        return;
    L10:
        queueSessionTaskWithPlayerCommand(r2, r3, 15, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda46(r4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setShuffleModeEnabled(IMediaController r2, int r3, boolean r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r2, r3, 14, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda54(r4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setTrackSelectionParameters(IMediaController r2, int r3, Bundle r4) {
        if (r2 == null) goto L13;
        if (r4 == null) goto L14;
        queueSessionTaskWithPlayerCommand(r2, r3, 29, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda1(this, TrackSelectionParameters.fromBundle(r4))));
        return;
    L8:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for TrackSelectionParameters", e);
        return;
    L14:
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void setVideoSurface(IMediaController r2, int r3, Surface r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        queueSessionTaskWithPlayerCommand(r2, r3, 27, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda56(r4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setVolume(IMediaController r2, int r3, float r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r4 >= 0.0f) goto L6;
        return;
    L6:
        if (r4 > 1.0f) goto L12;
        queueSessionTaskWithPlayerCommand(r2, r3, 24, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda53(r4)));
        return;
    }

    @Override // androidx.media3.session.IMediaSession
    public void stop(IMediaController r2, int r3) {
        if (r2 == null) goto L9;
        MediaSession.ControllerInfo r22 = this.connectedControllersManager.getController(r2.asBinder());
        if (r22 == null) goto L8;
        stopForControllerInfo(r22, r3);
        return;
    L8:
        return;
    }

    public void stopForControllerInfo(MediaSession.ControllerInfo r3, int r4) {
        queueSessionTaskWithPlayerCommandForControllerInfo(r3, r4, 3, sendSessionResultSuccess(new MediaSessionStub$$ExternalSyntheticLambda55()));
    }

    @Override // androidx.media3.session.IMediaSession
    public void subscribe(IMediaController r3, int r4, String r5, Bundle r6) {
        if (r3 != null) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r5) == false) goto L8;
        Log.w("MediaSessionStub", "subscribe(): Ignoring empty parentId");
        return;
    L8:
        if (r6 != null) goto L16;
        MediaLibraryService$LibraryParams r62 = null;
    L11:
        dispatchSessionTaskWithSessionCommand(r3, r4, 50001, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda62(r5, r62)));
        return;
    L16:
        r62 = MediaLibraryService$LibraryParams.fromBundle(r6);     // Catch: RuntimeException -> L13
    L13:
        e = move-exception;
        Log.w("MediaSessionStub", "Ignoring malformed Bundle for LibraryParams", e);
    }

    @Override // androidx.media3.session.IMediaSession
    public void unsubscribe(IMediaController r2, int r3, String r4) {
        if (r2 != null) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r4) == false) goto L8;
        Log.w("MediaSessionStub", "unsubscribe(): Ignoring empty parentId");
        return;
    L8:
        dispatchSessionTaskWithSessionCommand(r2, r3, 50002, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda11(r4)));
    }
}
