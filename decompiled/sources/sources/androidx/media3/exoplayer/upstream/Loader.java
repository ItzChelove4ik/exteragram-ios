package androidx.media3.exoplayer.upstream;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import java.io.IOException;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class Loader implements LoaderErrorThrower {
    public static final LoadErrorAction DONT_RETRY = null;
    public static final LoadErrorAction DONT_RETRY_FATAL = null;
    public static final LoadErrorAction RETRY = null;
    public static final LoadErrorAction RETRY_RESET_ERROR_COUNT = null;
    private LoadTask<? extends Loadable> currentTask;
    private final ReleasableExecutor downloadExecutor;
    private IOException fatalError;

    /* renamed from: androidx.media3.exoplayer.upstream.Loader$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public interface Callback<T extends Loadable> {
        void onLoadCanceled(T r1, long r2, long r4, boolean r6);

        void onLoadCompleted(T r1, long r2, long r4);

        LoadErrorAction onLoadError(T r1, long r2, long r4, IOException r6, int r7);

        default void onLoadStarted(T r1, long r2, long r4, int r6) {
        }
    }

    public static final class LoadErrorAction {
        private final long retryDelayMillis;
        private final int type;

        private LoadErrorAction(int r1, long r2) {
            this.type = r1;
            this.retryDelayMillis = r2;
        }

        public /* synthetic */ LoadErrorAction(int r1, long r2, AnonymousClass1 r4) {
            this(r1, r2);
        }

        public static /* synthetic */ int access$300(LoadErrorAction r0) {
            return r0.type;
        }

        public static /* synthetic */ long access$400(LoadErrorAction r2) {
            return r2.retryDelayMillis;
        }

        public boolean isRetry() {
            int r1 = this.type;
            if (r1 == 0) goto L8;
            if (r1 == 1) goto L8;
            return false;
        L8:
            return true;
        }
    }

    @SuppressLint({"HandlerLeak"})
    public final class LoadTask<T extends Loadable> extends Handler implements Runnable {
        private Callback<T> callback;
        private boolean canceled;
        private IOException currentError;
        public final int defaultMinRetryCount;
        private int errorCount;
        private Thread executorThread;
        private final T loadable;
        private volatile boolean released;
        private final long startTimeMs;
        final /* synthetic */ Loader this$0;

        public LoadTask(Loader r1, Looper r2, T r3, Callback<T> r4, int r5, long r6) {
            this.this$0 = r1;
            super(r2);
            this.loadable = r3;
            this.callback = r4;
            this.defaultMinRetryCount = r5;
            this.startTimeMs = r6;
        }

        private void execute() {
            long r2 = SystemClock.elapsedRealtime();
            long r4 = r2 - this.startTimeMs;
            ((Callback) Assertions.checkNotNull(this.callback)).onLoadStarted(this.loadable, r2, r4, this.errorCount);
            this.currentError = null;
            Loader.access$500(this.this$0).execute((Runnable) Assertions.checkNotNull(Loader.access$100(this.this$0)));
        }

        private void finish() {
            Loader.access$102(this.this$0, null);
        }

        private long getRetryDelayMillis() {
            return Math.min((this.errorCount - 1) * 1000, 5000);
        }

        public void cancel(boolean r9) {
            this.released = r9;
            this.currentError = null;
            if (hasMessages(1) == false) goto L7;
            this.canceled = true;
            removeMessages(1);
            if (r9 == true) goto L14;
            sendEmptyMessage(2);
        L14:
            if (r9 == false) goto L21;
            finish();
            long r3 = SystemClock.elapsedRealtime();
            ((Callback) Assertions.checkNotNull(this.callback)).onLoadCanceled(this.loadable, r3, r3 - this.startTimeMs, true);
            this.callback = null;
            return;
        L21:
            return;
        L7:
            monitor-enter(this);
            this.canceled = true;     // Catch: Throwable -> L11
            this.loadable.cancelLoad();     // Catch: Throwable -> L11
            Thread r1 = this.executorThread;     // Catch: Throwable -> L11
            if (r1 == null) goto L13;
            r1.interrupt();     // Catch: Throwable -> L11
        L13:
            monitor-exit(this);     // Catch: Throwable -> L11
        L11:
            th = move-exception;
            throw th;
        }

        @Override // android.os.Handler
        public void handleMessage(Message r13) {
            if (this.released == true) goto L45;
            int r0 = r13.what;
            if (r0 != 1) goto L10;
            execute();
            return;
        L10:
            if (r0 == 4) goto L41;
            finish();
            long r5 = SystemClock.elapsedRealtime();
            long r7 = r5 - this.startTimeMs;
            Callback r3 = (Callback) Assertions.checkNotNull(this.callback);
            if (this.canceled == false) goto L15;
            r3.onLoadCanceled(this.loadable, r5, r7, false);
            return;
        L15:
            int r02 = r13.what;
            if (r02 != 2) goto L18;
            r3.onLoadCompleted(this.loadable, r5, r7);     // Catch: RuntimeException -> L37
            return;
        L37:
            e = move-exception;
            Log.e("LoadTask", "Unexpected exception handling load completed", e);
            Loader.access$202(this.this$0, new UnexpectedLoaderException(e));
            return;
        L18:
            if (r02 != 3) goto L46;
            IOException r9 = (IOException) r13.obj;
            this.currentError = r9;
            int r10 = this.errorCount + 1;
            this.errorCount = r10;
            LoadErrorAction r132 = r3.onLoadError(this.loadable, r5, r7, r9, r10);
            if (LoadErrorAction.access$300(r132) != 3) goto L25;
            Loader.access$202(this.this$0, this.currentError);
            return;
        L25:
            if (LoadErrorAction.access$300(r132) != 2) goto L27;
            return;
        L27:
            if (LoadErrorAction.access$300(r132) != 1) goto L30;
            this.errorCount = 1;
        L30:
            if (LoadErrorAction.access$400(r132) == (-9223372036854775807L)) goto L32;
            long r03 = LoadErrorAction.access$400(r132);
        L33:
            start(r03);
            return;
        L32:
            r03 = getRetryDelayMillis();
            goto L33
        L46:
            return;
        L41:
            throw ((Error) r13.obj);
        }

        public void maybeThrowError(int r2) {
            IOException r0 = this.currentError;
            if (r0 != null) goto L5;
            return;
        L5:
            if (this.errorCount <= r2) goto L9;
            throw r0;
        }

        @Override // java.lang.Runnable
        public void run() {
            monitor-enter(this);     // Catch: Error -> L10 OutOfMemoryError -> L12 Exception -> L14 IOException -> L16
            boolean r2 = this.canceled;     // Catch: Throwable -> L32
            this.executorThread = Thread.currentThread();     // Catch: Throwable -> L32
            monitor-exit(this);     // Catch: Throwable -> L32
            if (r2 == true) goto L21;
            TraceUtil.beginSection("load:".concat(this.loadable.getClass().getSimpleName()));     // Catch: Error -> L10 OutOfMemoryError -> L12 Exception -> L14 IOException -> L16
            this.loadable.load();     // Catch: Throwable -> L18
            TraceUtil.endSection();     // Catch: Error -> L10 OutOfMemoryError -> L12 Exception -> L14 IOException -> L16
        L18:
            th = move-exception;
            TraceUtil.endSection();     // Catch: Error -> L10 OutOfMemoryError -> L12 Exception -> L14 IOException -> L16
            throw th;     // Catch: Error -> L10 OutOfMemoryError -> L12 Exception -> L14 IOException -> L16
        L21:
            monitor-enter(this);     // Catch: Error -> L10 OutOfMemoryError -> L12 Exception -> L14 IOException -> L16
            this.executorThread = null;     // Catch: Throwable -> L29
            Thread.interrupted();     // Catch: Throwable -> L29
            monitor-exit(this);     // Catch: Throwable -> L29
            if (this.released == true) goto L56;
            sendEmptyMessage(2);     // Catch: Error -> L10 OutOfMemoryError -> L12 Exception -> L14 IOException -> L16
            return;
        L56:
            return;
        L29:
            th = move-exception;
            throw th;     // Catch: Error -> L10 OutOfMemoryError -> L12 Exception -> L14 IOException -> L16
        L32:
            th = move-exception;
            throw th;     // Catch: Error -> L10 OutOfMemoryError -> L12 Exception -> L14 IOException -> L16
        L16:
            e = move-exception;
            if (this.released == true) goto L61;
            obtainMessage(3, e).sendToTarget();
            return;
        L61:
            return;
        L14:
            e = move-exception;
            if (this.released == true) goto L59;
            Log.e("LoadTask", "Unexpected exception loading stream", e);
            obtainMessage(3, new UnexpectedLoaderException(e)).sendToTarget();
            return;
        L59:
            return;
        L12:
            e = move-exception;
            if (this.released == true) goto L57;
            Log.e("LoadTask", "OutOfMemory error loading stream", e);
            obtainMessage(3, new UnexpectedLoaderException(e)).sendToTarget();
            return;
        L57:
            return;
        L10:
            e = move-exception;
            if (this.released == true) goto L38;
            Log.e("LoadTask", "Unexpected error loading stream", e);
            obtainMessage(4, e).sendToTarget();
        L38:
            throw e;
        }

        public void start(long r5) {
            if (Loader.access$100(this.this$0) != null) goto L5;
            boolean r0 = true;
        L6:
            Assertions.checkState(r0);
            Loader.access$102(this.this$0, this);
            if (r5 <= 0) goto L10;
            sendEmptyMessageDelayed(1, r5);
            return;
        L10:
            execute();
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    public interface Loadable {
        void cancelLoad();

        void load();
    }

    public interface ReleaseCallback {
        void onLoaderReleased();
    }

    public static final class ReleaseTask implements Runnable {
        private final ReleaseCallback callback;

        public ReleaseTask(ReleaseCallback r1) {
            this.callback = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.callback.onLoaderReleased();
        }
    }

    public static final class UnexpectedLoaderException extends IOException {
        public UnexpectedLoaderException(Throwable r4) {
            StringBuilder r0 = new StringBuilder("Unexpected ");
            r0.append(r4.getClass().getSimpleName());
            if (r4.getMessage() == null) goto L5;
            String r1 = ": " + r4.getMessage();
        L6:
            r0.append(r1);
            super(r0.toString(), r4);
            return;
        L5:
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L6
        }
    }

    static {
        RETRY = createRetryAction(false, -9223372036854775807L);
        RETRY_RESET_ERROR_COUNT = createRetryAction(true, -9223372036854775807L);
        DONT_RETRY = new LoadErrorAction(2, -9223372036854775807L, null);
        DONT_RETRY_FATAL = new LoadErrorAction(3, -9223372036854775807L, null);
    }

    public Loader(ReleasableExecutor r1) {
        this.downloadExecutor = r1;
    }

    public Loader(String r3) {
        this(ReleasableExecutor.from(Util.newSingleThreadExecutor("ExoPlayer:Loader:" + r3), new Loader$$ExternalSyntheticLambda0()));
    }

    public static /* synthetic */ LoadTask access$100(Loader r0) {
        return r0.currentTask;
    }

    public static /* synthetic */ LoadTask access$102(Loader r0, LoadTask r1) {
        r0.currentTask = r1;
        return r1;
    }

    public static /* synthetic */ IOException access$202(Loader r0, IOException r1) {
        r0.fatalError = r1;
        return r1;
    }

    public static /* synthetic */ ReleasableExecutor access$500(Loader r0) {
        return r0.downloadExecutor;
    }

    public static LoadErrorAction createRetryAction(boolean r2, long r3) {
        return new LoadErrorAction(r2 ? 1 : 0, r3, null);
    }

    public void cancelLoading() {
        ((LoadTask) Assertions.checkStateNotNull(this.currentTask)).cancel(false);
    }

    public void clearFatalError() {
        this.fatalError = null;
    }

    public boolean hasFatalError() {
        if (this.fatalError == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isLoading() {
        if (this.currentTask == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.exoplayer.upstream.LoaderErrorThrower
    public void maybeThrowError() {
        maybeThrowError(Integer.MIN_VALUE);
    }

    public void maybeThrowError(int r2) {
        IOException r0 = this.fatalError;
        if (r0 != null) goto L11;
        LoadTask<? extends Loadable> r1 = this.currentTask;
        if (r1 != null) goto L7;
        return;
    L7:
        if (r2 != Integer.MIN_VALUE) goto L9;
        r2 = r1.defaultMinRetryCount;
    L9:
        r1.maybeThrowError(r2);
        return;
    L11:
        throw r0;
    }

    public void release() {
        release(null);
    }

    public void release(ReleaseCallback r3) {
        LoadTask<? extends Loadable> r0 = this.currentTask;
        if (r0 == null) goto L5;
        r0.cancel(true);
    L5:
        if (r3 == null) goto L7;
        this.downloadExecutor.execute(new ReleaseTask(r3));
    L7:
        this.downloadExecutor.release();
    }

    public <T extends Loadable> long startLoading(T r10, Callback<T> r11, int r12) {
        Looper r3 = (Looper) Assertions.checkStateNotNull(Looper.myLooper());
        this.fatalError = null;
        long r7 = SystemClock.elapsedRealtime();
        new LoadTask(this, r3, r10, r11, r12, r7).start(0);
        return r7;
    }
}
