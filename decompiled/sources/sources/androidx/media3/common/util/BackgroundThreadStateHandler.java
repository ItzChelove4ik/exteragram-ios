package androidx.media3.common.util;

import android.os.Looper;
import com.google.common.base.Function;

/* loaded from: classes3.dex */
public final class BackgroundThreadStateHandler<T> {
    private final HandlerWrapper backgroundHandler;
    private T backgroundState;
    private final HandlerWrapper foregroundHandler;
    private T foregroundState;
    private final StateChangeListener<T> onStateChanged;
    private int pendingOperations;

    public interface StateChangeListener<T> {
        void onStateChanged(T r1, T r2);
    }

    /* renamed from: $r8$lambda$f0hFC73tfQBWeDExFIZmwNfoA-E, reason: not valid java name */
    public static /* synthetic */ void m755$r8$lambda$f0hFC73tfQBWeDExFIZmwNfoAE(BackgroundThreadStateHandler r1, Function r2) {
        T r22 = (T) r2.apply(r1.backgroundState);
        r1.backgroundState = r22;
        r1.runInForeground(new BackgroundThreadStateHandler$$ExternalSyntheticLambda2(r1, r22));
    }

    public static /* synthetic */ void $r8$lambda$hhYjRnaRRUwjgYpDYJM_auE3ai0(BackgroundThreadStateHandler r1, Object r2) {
        if (r1.pendingOperations != 0) goto L6;
        r1.updateStateInForeground(r2);
        return;
    }

    /* renamed from: $r8$lambda$lNGZQG-zRQnsJ9W6th9-dnrHlBQ, reason: not valid java name */
    public static /* synthetic */ void m756$r8$lambda$lNGZQGzRQnsJ9W6th9dnrHlBQ(BackgroundThreadStateHandler r1, Object r2) {
        int r0 = r1.pendingOperations - 1;
        r1.pendingOperations = r0;
        if (r0 != 0) goto L6;
        r1.updateStateInForeground(r2);
        return;
    }

    public BackgroundThreadStateHandler(T r2, Looper r3, Looper r4, Clock r5, StateChangeListener<T> r6) {
        this.backgroundHandler = r5.createHandler(r3, null);
        this.foregroundHandler = r5.createHandler(r4, null);
        this.foregroundState = r2;
        this.backgroundState = r2;
        this.onStateChanged = r6;
    }

    private void runInForeground(Runnable r2) {
        if (this.foregroundHandler.getLooper().getThread().isAlive() == true) goto L5;
        return;
    L5:
        this.foregroundHandler.post(r2);
    }

    private void updateStateInForeground(T r3) {
        T r0 = this.foregroundState;
        this.foregroundState = r3;
        if (r0.equals(r3) == true) goto L6;
        this.onStateChanged.onStateChanged(r0, r3);
        return;
    }

    public T get() {
        Looper r0 = Looper.myLooper();
        if (r0 != this.foregroundHandler.getLooper()) goto L7;
        return this.foregroundState;
    L7:
        if (r0 != this.backgroundHandler.getLooper()) goto L9;
        boolean r02 = true;
    L10:
        Assertions.checkState(r02);
        return this.backgroundState;
    L9:
        r02 = false;
        goto L10
    }

    public void runInBackground(Runnable r2) {
        if (this.backgroundHandler.getLooper().getThread().isAlive() == true) goto L5;
        return;
    L5:
        this.backgroundHandler.post(r2);
    }

    public void setStateInBackground(T r2) {
        this.backgroundState = r2;
        runInForeground(new BackgroundThreadStateHandler$$ExternalSyntheticLambda0(this, r2));
    }

    public void updateStateAsync(Function<T, T> r4, Function<T, T> r5) {
        if (Looper.myLooper() != this.foregroundHandler.getLooper()) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        this.pendingOperations++;
        runInBackground(new BackgroundThreadStateHandler$$ExternalSyntheticLambda1(this, r5));
        updateStateInForeground(r4.apply(this.foregroundState));
        return;
    L5:
        r0 = false;
        goto L6
    }
}
