package androidx.core.os;

@Deprecated
/* loaded from: classes3.dex */
public final class CancellationSignal {
    private boolean mCancelInProgress;
    private Object mCancellationSignalObj;
    private boolean mIsCanceled;
    private OnCancelListener mOnCancelListener;

    public interface OnCancelListener {
        void onCancel();
    }

    public CancellationSignal() {
    }

    private void waitForCancelFinishedLocked() {
    L3:
        if (this.mCancelInProgress == false) goto L6;
        wait();     // Catch: InterruptedException -> L7
        goto L3
    }

    public void cancel() {
        monitor-enter(this);
    L7:
        th = move-exception;
        throw th;
    L4:
        if (this.mIsCanceled == false) goto L9;
        monitor-exit(this);     // Catch: Throwable -> L7
        return;
    L9:
        this.mIsCanceled = true;     // Catch: Throwable -> L7
        this.mCancelInProgress = true;     // Catch: Throwable -> L7
        OnCancelListener r0 = this.mOnCancelListener;     // Catch: Throwable -> L7
        Object r1 = this.mCancellationSignalObj;     // Catch: Throwable -> L7
        monitor-exit(this);     // Catch: Throwable -> L7
        if (r0 != null) goto L38;
    L16:
        if (r1 == null) goto L26;
        ((android.os.CancellationSignal) r1).cancel();     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        monitor-enter(this);
        this.mCancelInProgress = false;     // Catch: Throwable -> L23
        notifyAll();     // Catch: Throwable -> L23
        throw th;
    L23:
        th = move-exception;
        throw th;
    L26:
        monitor-enter(this);
        this.mCancelInProgress = false;     // Catch: Throwable -> L30
        notifyAll();     // Catch: Throwable -> L30
        monitor-exit(this);     // Catch: Throwable -> L30
        return;
    L30:
        th = move-exception;
        throw th;
    L38:
        r0.onCancel();     // Catch: Throwable -> L14
        goto L16
    }

    public void setOnCancelListener(OnCancelListener r2) {
        monitor-enter(this);
        waitForCancelFinishedLocked();     // Catch: Throwable -> L7
        if (this.mOnCancelListener != r2) goto L9;
        monitor-exit(this);     // Catch: Throwable -> L7
        return;
    L9:
        this.mOnCancelListener = r2;     // Catch: Throwable -> L7
        if (this.mIsCanceled == false) goto L16;
        if (r2 == null) goto L16;
        monitor-exit(this);     // Catch: Throwable -> L7
        r2.onCancel();
        return;
    L16:
        monitor-exit(this);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
