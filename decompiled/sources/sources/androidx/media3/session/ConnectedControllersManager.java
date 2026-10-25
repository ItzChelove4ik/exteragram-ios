package androidx.media3.session;

import androidx.collection.ArrayMap;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaSession;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
final class ConnectedControllersManager<T> {
    private final ArrayMap<T, MediaSession.ControllerInfo> controllerInfoMap;
    private final ArrayMap<MediaSession.ControllerInfo, ConnectedControllerRecord<T>> controllerRecords;
    private final Object lock;
    private final WeakReference<MediaSessionImpl> sessionImpl;

    public interface AsyncCommand {
        ListenableFuture<Void> run();
    }

    public static final class ConnectedControllerRecord<T> {
        public final Deque<AsyncCommand> commandQueue;
        public boolean commandQueueIsFlushing;
        public Player.Commands commandQueuePlayerCommands;
        public final T controllerKey;
        public PlaybackException playbackException;
        public Player.Commands playerCommands;
        public Player.Commands playerCommandsBeforePlaybackException;
        public PlayerInfo playerInfoForPlaybackException;
        public final SequencedFutureManager sequencedFutureManager;
        public SessionCommands sessionCommands;

        public ConnectedControllerRecord(T r1, SequencedFutureManager r2, SessionCommands r3, Player.Commands r4) {
            this.controllerKey = r1;
            this.sequencedFutureManager = r2;
            this.sessionCommands = r3;
            this.playerCommands = r4;
            this.commandQueue = new ArrayDeque();
            this.commandQueuePlayerCommands = Player.Commands.EMPTY;
        }
    }

    /* renamed from: $r8$lambda$BTX49EH-hVm6N_fSIVxVw5T6308, reason: not valid java name */
    public static /* synthetic */ void m863$r8$lambda$BTX49EHhVm6N_fSIVxVw5T6308(ConnectedControllersManager r1, AtomicBoolean r2, ConnectedControllerRecord r3, AtomicBoolean r4) {
        Object r0 = r1.lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r2.get() == true) goto L9;
        r1.flushCommandQueue(r3);     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L9:
        r4.set(true);     // Catch: Throwable -> L7
        goto L10
    }

    /* renamed from: $r8$lambda$I1DSu1H7ZL-MrmiN2sHpNMsO75M, reason: not valid java name */
    public static /* synthetic */ void m864$r8$lambda$I1DSu1H7ZLMrmiN2sHpNMsO75M(ConnectedControllersManager r1, AsyncCommand r2, AtomicBoolean r3, ConnectedControllerRecord r4, AtomicBoolean r5) {
        r1.getClass();
        r2.run().addListener(new ConnectedControllersManager$$ExternalSyntheticLambda3(r1, r3, r4, r5), MoreExecutors.directExecutor());
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$Wn1Z224Rn9gy3x_xpq9X0idp7xg(ConnectedControllersManager r0, MediaSession.ControllerInfo r1, Player.Commands r2) {
        MediaSessionImpl r02 = r0.sessionImpl.get();
        if (r02 == null) goto L6;
        r02.onPlayerInteractionFinishedOnHandler(r1, r2);
    L6:
        return Futures.immediateVoidFuture();
    }

    public static /* synthetic */ void $r8$lambda$rzd9vU5j8dPQFbY2HKCFCnRfavQ(MediaSessionImpl r1, MediaSession.ControllerInfo r2) {
        if (r1.isReleased() == false) goto L5;
        return;
    L5:
        r1.onDisconnectedOnHandler(r2);
    }

    public ConnectedControllersManager(MediaSessionImpl r2) {
        this.controllerInfoMap = new ArrayMap();
        this.controllerRecords = new ArrayMap();
        this.lock = new Object();
        this.sessionImpl = new WeakReference(r2);
    }

    private void flushCommandQueue(ConnectedControllerRecord<T> r12) {
        MediaSessionImpl r0 = this.sessionImpl.get();
        if (r0 == null) goto L13;
        AtomicBoolean r6 = new AtomicBoolean(true);
    L7:
        if (r6.get() == false) goto L16;
        r6.set(false);
        AsyncCommand r3 = r12.commandQueue.poll();
        if (r3 == null) goto L10;
        AtomicBoolean r4 = new AtomicBoolean(true);
        ConnectedControllersManager<T> r2 = this;
        Util.postOrRun(r0.getApplicationHandler(), r0.callWithControllerForCurrentRequestSet(this.getController(r12.controllerKey), new ConnectedControllersManager$$ExternalSyntheticLambda2(r2, r3, r4, r12, r6)));
        r4.set(false);
        ConnectedControllersManager<T> this = r2;
        goto L7
    L10:
        r12.commandQueueIsFlushing = false;
        return;
    L16:
        return;
    }

    public void addController(T r4, MediaSession.ControllerInfo r5, SessionCommands r6, Player.Commands r7) {
        Object r0 = this.lock;
        monitor-enter(r0);
        MediaSession.ControllerInfo r1 = getController(r4);     // Catch: Throwable -> L7
        if (r1 != null) goto L9;
        this.controllerInfoMap.put(r4, r5);     // Catch: Throwable -> L7
        this.controllerRecords.put(r5, new ConnectedControllerRecord(r4, new SequencedFutureManager(), r6, r7));     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L9:
        ConnectedControllerRecord r3 = (ConnectedControllerRecord) Assertions.checkStateNotNull(this.controllerRecords.get(r1));     // Catch: Throwable -> L7
        r3.sessionCommands = r6;     // Catch: Throwable -> L7
        r3.playerCommands = r7;     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    public void addToCommandQueue(MediaSession.ControllerInfo r2, int r3, AsyncCommand r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ConnectedControllerRecord<T> r1 = this.controllerRecords.get(r2);     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.commandQueuePlayerCommands = r1.commandQueuePlayerCommands.buildUpon().add(r3).build();     // Catch: Throwable -> L7
        r1.commandQueue.add(r4);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void flushCommandQueue(MediaSession.ControllerInfo r6) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ConnectedControllerRecord<T> r1 = this.controllerRecords.get(r6);     // Catch: Throwable -> L8
        if (r1 != null) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        Player.Commands r2 = r1.commandQueuePlayerCommands;     // Catch: Throwable -> L8
        r1.commandQueuePlayerCommands = Player.Commands.EMPTY;     // Catch: Throwable -> L8
        r1.commandQueue.add(new ConnectedControllersManager$$ExternalSyntheticLambda1(this, r6, r2));     // Catch: Throwable -> L8
        if (r1.commandQueueIsFlushing == false) goto L14;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L14:
        r1.commandQueueIsFlushing = true;     // Catch: Throwable -> L8
        flushCommandQueue(r1);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public Player.Commands getAvailablePlayerCommands(MediaSession.ControllerInfo r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ConnectedControllerRecord<T> r1 = this.controllerRecords.get(r2);     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        Player.Commands r12 = r1.playerCommands;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r12;
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return null;
    L9:
        th = move-exception;
        throw th;
    }

    public ImmutableList<MediaSession.ControllerInfo> getConnectedControllers() {
        Object r0 = this.lock;
        monitor-enter(r0);
        ImmutableList<MediaSession.ControllerInfo> r1 = ImmutableList.copyOf(this.controllerInfoMap.values());     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public MediaSession.ControllerInfo getController(T r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        MediaSession.ControllerInfo r1 = this.controllerInfoMap.get(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public PlaybackException getPlaybackException(MediaSession.ControllerInfo r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ConnectedControllerRecord<T> r1 = this.controllerRecords.get(r2);     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        PlaybackException r12 = r1.playbackException;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r12;
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return null;
    L9:
        th = move-exception;
        throw th;
    }

    public PlayerInfo getPlayerInfoForPlaybackException(MediaSession.ControllerInfo r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ConnectedControllerRecord<T> r1 = this.controllerRecords.get(r2);     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        PlayerInfo r12 = r1.playerInfoForPlaybackException;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r12;
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return null;
    L9:
        th = move-exception;
        throw th;
    }

    public SequencedFutureManager getSequencedFutureManager(MediaSession.ControllerInfo r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ConnectedControllerRecord<T> r1 = this.controllerRecords.get(r2);     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        if (r1 != null) goto L8;
        return null;
    L8:
        return r1.sequencedFutureManager;
    L11:
        th = move-exception;
        throw th;
    }

    public SequencedFutureManager getSequencedFutureManager(T r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        MediaSession.ControllerInfo r32 = getController(r3);     // Catch: Throwable -> L7
        if (r32 == null) goto L9;
        ConnectedControllerRecord<T> r2 = this.controllerRecords.get(r32);     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        if (r2 != null) goto L13;
        return null;
    L13:
        return r2.sequencedFutureManager;
    L9:
        r2 = null;
    L7:
        th = move-exception;
        throw th;
    }

    public boolean isConnected(MediaSession.ControllerInfo r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.controllerRecords.get(r2) == null) goto L7;
        boolean r1 = true;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r1;
    L7:
        r1 = false;
        goto L8
    }

    public boolean isPlayerCommandAvailable(MediaSession.ControllerInfo r3, int r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ConnectedControllerRecord<T> r32 = this.controllerRecords.get(r3);     // Catch: Throwable -> L17
        monitor-exit(r0);     // Catch: Throwable -> L17
        MediaSessionImpl r2 = this.sessionImpl.get();
        if (r32 != null) goto L9;
        return false;
    L9:
        if (r32.playerCommands.contains(r4) == false) goto L22;
        if (r2 != null) goto L12;
        return false;
    L12:
        if (r2.getPlayerWrapper().getAvailableCommands().contains(r4) == false) goto L24;
        return true;
    L24:
        return false;
    L22:
        return false;
    L17:
        th = move-exception;
        throw th;
    }

    public boolean isSessionCommandAvailable(MediaSession.ControllerInfo r2, int r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ConnectedControllerRecord<T> r1 = this.controllerRecords.get(r2);     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        if (r1 != null) goto L8;
        return false;
    L8:
        if (r1.sessionCommands.contains(r3) == false) goto L18;
        return true;
    L18:
        return false;
    L13:
        th = move-exception;
        throw th;
    }

    public boolean isSessionCommandAvailable(MediaSession.ControllerInfo r2, SessionCommand r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ConnectedControllerRecord<T> r1 = this.controllerRecords.get(r2);     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        if (r1 != null) goto L8;
        return false;
    L8:
        if (r1.sessionCommands.contains(r3) == false) goto L18;
        return true;
    L18:
        return false;
    L13:
        th = move-exception;
        throw th;
    }

    public void removeController(MediaSession.ControllerInfo r5) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ConnectedControllerRecord<T> r1 = this.controllerRecords.remove(r5);     // Catch: Throwable -> L8
        if (r1 != null) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        this.controllerInfoMap.remove(r1.controllerKey);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        r1.sequencedFutureManager.release();
        MediaSessionImpl r4 = this.sessionImpl.get();
        if (r4 != null) goto L15;
        return;
    L15:
        if (r4.isReleased() == true) goto L23;
        Util.postOrRun(r4.getApplicationHandler(), new ConnectedControllersManager$$ExternalSyntheticLambda0(r4, r5));
        return;
    L23:
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public void removeController(T r1) {
        MediaSession.ControllerInfo r12 = getController(r1);
        if (r12 == null) goto L6;
        removeController(r12);
        return;
    }

    public void setPlaybackException(MediaSession.ControllerInfo r2, PlaybackException r3, Player.Commands r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ConnectedControllerRecord<T> r1 = this.controllerRecords.get(r2);     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.playbackException = r3;     // Catch: Throwable -> L7
        r1.playerCommandsBeforePlaybackException = r4;     // Catch: Throwable -> L7
        r1.playerInfoForPlaybackException = null;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setPlayerInfoForPlaybackException(MediaSession.ControllerInfo r2, PlayerInfo r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ConnectedControllerRecord<T> r1 = this.controllerRecords.get(r2);     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        Assertions.checkStateNotNull(r1.playbackException);     // Catch: Throwable -> L7
        r1.playerInfoForPlaybackException = r3;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
