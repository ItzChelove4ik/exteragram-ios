package androidx.media3.common.util;

/* loaded from: classes3.dex */
public class ConditionVariable {
    private final Clock clock;
    private boolean isOpen;

    public ConditionVariable() {
        this(Clock.DEFAULT);
    }

    public ConditionVariable(Clock r1) {
        this.clock = r1;
    }

    public synchronized void block() {
        monitor-enter(this);
    L13:
        if (this.isOpen == true) goto L9;
        this.clock.onThreadBlocked();     // Catch: Throwable -> L7
        wait();     // Catch: Throwable -> L7
        goto L13
    L9:
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized void blockUninterruptible() {
        monitor-enter(this);
        boolean r0 = false;
    L19:
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.isOpen == true) goto L11;
        this.clock.onThreadBlocked();     // Catch: Throwable -> L8 InterruptedException -> L10
        wait();     // Catch: Throwable -> L8 InterruptedException -> L10
    L10:
        r0 = true;
        goto L19
    L11:
        if (r0 == false) goto L13;
        Thread.currentThread().interrupt();     // Catch: Throwable -> L8
    L13:
        monitor-exit(this);
    }

    public synchronized boolean blockUninterruptible(long r5) {
        monitor-enter(this);
        if (r5 > 0) goto L10;
        boolean r52 = this.isOpen;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r52;
    L10:
        long r0 = this.clock.elapsedRealtime();     // Catch: Throwable -> L8
        long r53 = r5 + r0;     // Catch: Throwable -> L8
        if (r53 >= r0) goto L13;
        blockUninterruptible();     // Catch: Throwable -> L8
    L24:
        boolean r54 = this.isOpen;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r54;
    L13:
        boolean r2 = false;
    L15:
        if (this.isOpen == true) goto L22;
        if (r0 >= r53) goto L22;
        this.clock.onThreadBlocked();     // Catch: Throwable -> L8 InterruptedException -> L20
        wait(r53 - r0);     // Catch: Throwable -> L8 InterruptedException -> L20
    L21:
        r0 = this.clock.elapsedRealtime();     // Catch: Throwable -> L8
    L20:
        r2 = true;
    L22:
        if (r2 == false) goto L24;
        Thread.currentThread().interrupt();     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public synchronized boolean close() {
        monitor-enter(this);
        boolean r0 = this.isOpen;     // Catch: Throwable -> L6
        this.isOpen = false;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized boolean isOpen() {
        monitor-enter(this);
        boolean r0 = this.isOpen;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized boolean open() {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L4:
        if (this.isOpen == false) goto L9;
        monitor-exit(this);
        return false;
    L9:
        this.isOpen = true;     // Catch: Throwable -> L12
        notifyAll();     // Catch: Throwable -> L12
        monitor-exit(this);
        return true;
    }
}
