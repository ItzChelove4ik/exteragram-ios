package androidx.media3.exoplayer;

import android.os.Looper;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;

/* loaded from: classes4.dex */
public final class PlayerMessage {
    private final Clock clock;
    private boolean deleteAfterDelivery;
    private boolean isCanceled;
    private boolean isDelivered;
    private boolean isProcessed;
    private boolean isSent;
    private Looper looper;
    private int mediaItemIndex;
    private Object payload;
    private long positionMs;
    private final Sender sender;
    private final Target target;
    private final Timeline timeline;
    private int type;

    public interface Sender {
        void sendMessage(PlayerMessage r1);
    }

    public interface Target {
        void handleMessage(int r1, Object r2);
    }

    public PlayerMessage(Sender r1, Target r2, Timeline r3, int r4, Clock r5, Looper r6) {
        this.sender = r1;
        this.target = r2;
        this.timeline = r3;
        this.looper = r6;
        this.clock = r5;
        this.mediaItemIndex = r4;
        this.positionMs = -9223372036854775807L;
        this.deleteAfterDelivery = true;
    }

    public boolean getDeleteAfterDelivery() {
        return this.deleteAfterDelivery;
    }

    public Looper getLooper() {
        return this.looper;
    }

    public int getMediaItemIndex() {
        return this.mediaItemIndex;
    }

    public Object getPayload() {
        return this.payload;
    }

    public long getPositionMs() {
        return this.positionMs;
    }

    public Target getTarget() {
        return this.target;
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    public int getType() {
        return this.type;
    }

    public synchronized boolean isCanceled() {
        monitor-enter(this);
        boolean r0 = this.isCanceled;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void markAsProcessed(boolean r2) {
        monitor-enter(this);
        this.isDelivered = r2 | this.isDelivered;     // Catch: Throwable -> L6
        this.isProcessed = true;     // Catch: Throwable -> L6
        notifyAll();     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public PlayerMessage send() {
        Assertions.checkState(!this.isSent);
        if (this.positionMs != (-9223372036854775807L)) goto L5;
        Assertions.checkArgument(this.deleteAfterDelivery);
    L5:
        this.isSent = true;
        this.sender.sendMessage(this);
        return this;
    }

    public PlayerMessage setPayload(Object r2) {
        Assertions.checkState(!this.isSent);
        this.payload = r2;
        return this;
    }

    public PlayerMessage setType(int r2) {
        Assertions.checkState(!this.isSent);
        this.type = r2;
        return this;
    }
}
