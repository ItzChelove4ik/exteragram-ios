package androidx.camera.core.impl;

import android.util.Log;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public abstract class DeferrableSurface {
    private static final boolean DEBUG = false;
    public static final Size SIZE_UNDEFINED = null;
    private static final AtomicInteger TOTAL_COUNT = null;
    private static final AtomicInteger USED_COUNT = null;
    private CallbackToFutureAdapter.Completer<Void> mCloseCompleter;
    private final ListenableFuture<Void> mCloseFuture;
    private boolean mClosed;
    Class<?> mContainerClass;
    private final Object mLock;
    private final Size mPrescribedSize;
    private final int mPrescribedStreamFormat;
    private CallbackToFutureAdapter.Completer<Void> mTerminationCompleter;
    private final ListenableFuture<Void> mTerminationFuture;
    private int mUseCount;

    public static final class SurfaceClosedException extends Exception {
        DeferrableSurface mDeferrableSurface;

        public SurfaceClosedException(String r1, DeferrableSurface r2) {
            super(r1);
            this.mDeferrableSurface = r2;
        }

        public DeferrableSurface getDeferrableSurface() {
            return this.mDeferrableSurface;
        }
    }

    public static final class SurfaceUnavailableException extends Exception {
        public SurfaceUnavailableException(String r1) {
            super(r1);
        }
    }

    /* renamed from: $r8$lambda$2bNbZSrwzPE-6VUuBbHBM9VrhXU, reason: not valid java name */
    public static /* synthetic */ Object m599$r8$lambda$2bNbZSrwzPE6VUuBbHBM9VrhXU(DeferrableSurface r1, CallbackToFutureAdapter.Completer r2) {
        Object r0 = r1.mLock;
        monitor-enter(r0);
        r1.mTerminationCompleter = r2;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return "DeferrableSurface-termination(" + r1 + ")";
    L8:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ Object $r8$lambda$n5A2Gczc3HX9R1GqVs9IlIvNPv4(DeferrableSurface r1, CallbackToFutureAdapter.Completer r2) {
        Object r0 = r1.mLock;
        monitor-enter(r0);
        r1.mCloseCompleter = r2;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return "DeferrableSurface-close(" + r1 + ")";
    L8:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ void $r8$lambda$o2NHrpK5pP8NNndqchJRakLjc8U(DeferrableSurface r5, String r6) {
        r5.getClass();
        r5.mTerminationFuture.get();     // Catch: Exception -> L5
        r5.printGlobalDebugCounts("Surface terminated", TOTAL_COUNT.decrementAndGet(), USED_COUNT.get());     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        Logger.e("DeferrableSurface", "Unexpected surface termination for " + r5 + "\nStack Trace:\n" + r6);
        monitor-enter(r5.mLock);
    L10:
        th = move-exception;
        throw th;
    L9:
        throw new IllegalArgumentException(String.format("DeferrableSurface %s [closed: %b, use_count: %s] terminated with unexpected exception.", new Object[]{r5, Boolean.valueOf(r5.mClosed), Integer.valueOf(r5.mUseCount)}), e);     // Catch: Throwable -> L10
    }

    static {
        SIZE_UNDEFINED = new Size(0, 0);
        DEBUG = Logger.isDebugEnabled("DeferrableSurface");
        USED_COUNT = new AtomicInteger(0);
        TOTAL_COUNT = new AtomicInteger(0);
    }

    public DeferrableSurface(Size r3, int r4) {
        this.mLock = new Object();
        this.mUseCount = 0;
        this.mClosed = false;
        this.mPrescribedSize = r3;
        this.mPrescribedStreamFormat = r4;
        ListenableFuture<Void> r32 = CallbackToFutureAdapter.getFuture(new DeferrableSurface$$ExternalSyntheticLambda0(this));
        this.mTerminationFuture = r32;
        this.mCloseFuture = CallbackToFutureAdapter.getFuture(new DeferrableSurface$$ExternalSyntheticLambda1(this));
        if (Logger.isDebugEnabled("DeferrableSurface") == false) goto L6;
        printGlobalDebugCounts("Surface created", TOTAL_COUNT.incrementAndGet(), USED_COUNT.get());
        r32.addListener(new DeferrableSurface$$ExternalSyntheticLambda2(this, Log.getStackTraceString(new Exception())), CameraXExecutors.directExecutor());
        return;
    }

    private void printGlobalDebugCounts(String r3, int r4, int r5) {
        if (DEBUG == false) goto L5;
    L7:
        Logger.d("DeferrableSurface", r3 + "[total_surfaces=" + r4 + ", used_surfaces=" + r5 + "](" + this + "}");
        return;
    L5:
        if (Logger.isDebugEnabled("DeferrableSurface") == false) goto L7;
        Logger.d("DeferrableSurface", "DeferrableSurface usage statistics may be inaccurate since debug logging was not enabled at static initialization time. App restart may be required to enable accurate usage statistics.");
        goto L7
    }

    public void close() {
        Object r1 = this.mLock;
        monitor-enter(r1);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.mClosed == true) goto L15;
        this.mClosed = true;     // Catch: Throwable -> L9
        this.mCloseCompleter.set(null);     // Catch: Throwable -> L9
        if (this.mUseCount != 0) goto L11;
        CallbackToFutureAdapter.Completer<Void> r2 = this.mTerminationCompleter;     // Catch: Throwable -> L9
        this.mTerminationCompleter = null;     // Catch: Throwable -> L9
    L13:
        if (Logger.isDebugEnabled("DeferrableSurface") == false) goto L16;
        Logger.d("DeferrableSurface", "surface closed,  useCount=" + this.mUseCount + " closed=true " + this);     // Catch: Throwable -> L9
    L16:
        monitor-exit(r1);     // Catch: Throwable -> L9
        if (r2 == null) goto L24;
        r2.set(null);
        return;
    L24:
        return;
    L11:
        r2 = null;
        goto L13
    L15:
        r2 = null;
        goto L16
    }

    public void decrementUseCount() {
        Object r1 = this.mLock;
        monitor-enter(r1);
        int r2 = this.mUseCount;     // Catch: Throwable -> L11
        if (r2 == 0) goto L24;
        int r22 = r2 - 1;
        this.mUseCount = r22;     // Catch: Throwable -> L11
        if (r22 == 0) goto L9;
    L13:
        CallbackToFutureAdapter.Completer<Void> r23 = null;
    L15:
        if (Logger.isDebugEnabled("DeferrableSurface") == false) goto L19;
        Logger.d("DeferrableSurface", "use count-1,  useCount=" + this.mUseCount + " closed=" + this.mClosed + " " + this);     // Catch: Throwable -> L11
        if (this.mUseCount != 0) goto L19;
        printGlobalDebugCounts("Surface no longer in use", TOTAL_COUNT.get(), USED_COUNT.decrementAndGet());     // Catch: Throwable -> L11
    L19:
        monitor-exit(r1);     // Catch: Throwable -> L11
        if (r23 == null) goto L28;
        r23.set(null);
        return;
    L28:
        return;
    L9:
        if (this.mClosed == false) goto L13;
        r23 = this.mTerminationCompleter;     // Catch: Throwable -> L11
        this.mTerminationCompleter = null;     // Catch: Throwable -> L11
        goto L15
    L24:
        throw new IllegalStateException("Decrementing use count occurs more times than incrementing");     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    public ListenableFuture<Void> getCloseFuture() {
        return Futures.nonCancellationPropagating(this.mCloseFuture);
    }

    public Class<?> getContainerClass() {
        return this.mContainerClass;
    }

    public Size getPrescribedSize() {
        return this.mPrescribedSize;
    }

    public int getPrescribedStreamFormat() {
        return this.mPrescribedStreamFormat;
    }

    public final ListenableFuture<Surface> getSurface() {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.mClosed == false) goto L11;
        ListenableFuture<Surface> r3 = Futures.immediateFailedFuture(new SurfaceClosedException("DeferrableSurface already closed.", this));     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r3;
    L11:
        ListenableFuture<Surface> r32 = provideSurface();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r32;
    }

    public ListenableFuture<Void> getTerminationFuture() {
        return Futures.nonCancellationPropagating(this.mTerminationFuture);
    }

    public void incrementUseCount() {
        Object r1 = this.mLock;
        monitor-enter(r1);
        int r2 = this.mUseCount;     // Catch: Throwable -> L11
        if (r2 == 0) goto L7;
    L13:
        this.mUseCount = r2 + 1;     // Catch: Throwable -> L11
        if (Logger.isDebugEnabled("DeferrableSurface") == true) goto L16;
    L19:
        monitor-exit(r1);     // Catch: Throwable -> L11
        return;
    L16:
        if (this.mUseCount != 1) goto L18;
        printGlobalDebugCounts("New surface in use", TOTAL_COUNT.get(), USED_COUNT.incrementAndGet());     // Catch: Throwable -> L11
    L18:
        Logger.d("DeferrableSurface", "use count+1, useCount=" + this.mUseCount + " " + this);     // Catch: Throwable -> L11
        goto L19
    L7:
        if (this.mClosed == false) goto L13;
        throw new SurfaceClosedException("Cannot begin use on a closed surface.", this);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    public boolean isClosed() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        boolean r1 = this.mClosed;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public abstract ListenableFuture<Surface> provideSurface();

    public void setContainerClass(Class<?> r1) {
        this.mContainerClass = r1;
    }
}
