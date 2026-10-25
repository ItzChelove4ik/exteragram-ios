package androidx.media3.exoplayer.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Surface;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.EGLSurfaceTexture;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;

/* loaded from: classes4.dex */
public final class PlaceholderSurface extends Surface {
    private static int secureMode;
    private static boolean secureModeInitialized;
    public final boolean secure;
    private final PlaceholderSurfaceThread thread;
    private boolean threadReleased;

    /* renamed from: androidx.media3.exoplayer.video.PlaceholderSurface$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class PlaceholderSurfaceThread extends HandlerThread implements Handler.Callback {
        private EGLSurfaceTexture eglSurfaceTexture;
        private Handler handler;
        private Error initError;
        private RuntimeException initException;
        private PlaceholderSurface surface;

        public PlaceholderSurfaceThread() {
            super("ExoPlayer:PlaceholderSurface");
        }

        private void initInternal(int r4) {
            Assertions.checkNotNull(this.eglSurfaceTexture);
            this.eglSurfaceTexture.init(r4);
            SurfaceTexture r1 = this.eglSurfaceTexture.getSurfaceTexture();
            if (r4 == 0) goto L5;
            boolean r42 = true;
        L6:
            this.surface = new PlaceholderSurface(this, r1, r42, null);
            return;
        L5:
            r42 = false;
            goto L6
        }

        private void releaseInternal() {
            Assertions.checkNotNull(this.eglSurfaceTexture);
            this.eglSurfaceTexture.release();
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message r4) {
            int r0 = r4.what;
            if (r0 != 1) goto L5;
            initInternal(r4.arg1);     // Catch: Throwable -> L24 Error -> L26 GlUtil.GlException -> L28 RuntimeException -> L30
            monitor-enter(this);
            notify();     // Catch: Throwable -> L21
            monitor-exit(this);     // Catch: Throwable -> L21
        L77:
            return true;
        L21:
            th = move-exception;
            throw th;
        L28:
            e = move-exception;
            Log.e("PlaceholderSurface", "Failed to initialize placeholder surface", e);     // Catch: Throwable -> L24
            this.initException = new IllegalStateException(e);     // Catch: Throwable -> L24
            monitor-enter(this);
            notify();     // Catch: Throwable -> L45
        L45:
            th = move-exception;
            throw th;
        L26:
            e = move-exception;
            Log.e("PlaceholderSurface", "Failed to initialize placeholder surface", e);     // Catch: Throwable -> L24
            this.initError = e;     // Catch: Throwable -> L24
            monitor-enter(this);
            notify();     // Catch: Throwable -> L37
        L37:
            th = move-exception;
            throw th;
        L30:
            e = move-exception;
            Log.e("PlaceholderSurface", "Failed to initialize placeholder surface", e);     // Catch: Throwable -> L24
            this.initException = e;     // Catch: Throwable -> L24
            monitor-enter(this);
            notify();     // Catch: Throwable -> L53
        L53:
            th = move-exception;
            throw th;
        L5:
            if (r0 == 2) goto L63;
            return true;
        L63:
            releaseInternal();     // Catch: Throwable -> L9
        L8:
            quit();
            return true;
        L9:
            th = move-exception;
            Log.e("PlaceholderSurface", "Failed to release placeholder surface", th);     // Catch: Throwable -> L13
        L13:
            th = move-exception;
            quit();
            throw th;
        L24:
            th = move-exception;
            monitor-enter(this);
            notify();     // Catch: Throwable -> L60
            throw th;
        L60:
            th = move-exception;
            throw th;
        }

        public PlaceholderSurface init(int r4) {
            start();
            this.handler = new Handler(getLooper(), this);
            this.eglSurfaceTexture = new EGLSurfaceTexture(this.handler);
            monitor-enter(this);
            boolean r2 = false;
            this.handler.obtainMessage(1, r4, 0).sendToTarget();     // Catch: Throwable -> L13
        L6:
            if (this.surface != null) goto L16;
            if (this.initException != null) goto L16;
            if (this.initError != null) goto L16;
            wait();     // Catch: Throwable -> L13 InterruptedException -> L15
        L15:
            r2 = true;
        L16:
            monitor-exit(this);     // Catch: Throwable -> L13
            if (r2 == false) goto L19;
            Thread.currentThread().interrupt();
        L19:
            RuntimeException r42 = this.initException;
            if (r42 != null) goto L26;
            Error r43 = this.initError;
            if (r43 == null) goto L24;
            throw r43;
        L24:
            return (PlaceholderSurface) Assertions.checkNotNull(this.surface);
        L26:
            throw r42;
        L13:
            th = move-exception;
            throw th;
        }

        public void release() {
            Assertions.checkNotNull(this.handler);
            this.handler.sendEmptyMessage(2);
        }
    }

    private PlaceholderSurface(PlaceholderSurfaceThread r1, SurfaceTexture r2, boolean r3) {
        super(r2);
        this.thread = r1;
        this.secure = r3;
    }

    public /* synthetic */ PlaceholderSurface(PlaceholderSurfaceThread r1, SurfaceTexture r2, boolean r3, AnonymousClass1 r4) {
        this(r1, r2, r3);
    }

    private static int getSecureMode(Context r3) {
    L11:
        e = move-exception;
        Log.e("PlaceholderSurface", "Failed to determine secure mode due to GL error: " + e.getMessage());
        return 0;
    L4:
        if (GlUtil.isProtectedContentExtensionSupported(r3) == false) goto L13;
        if (GlUtil.isSurfacelessContextExtensionSupported() == false) goto L9;
        return 1;
    L9:
        return 2;
    L13:
        return 0;
    }

    public static synchronized boolean isSecureSupported(Context r3) {
        monitor-enter(PlaceholderSurface.class);
        boolean r2 = true;
        if (secureModeInitialized == true) goto L9;
        secureMode = getSecureMode(r3);     // Catch: Throwable -> L7
        secureModeInitialized = true;     // Catch: Throwable -> L7
    L9:
        if (secureMode != 0) goto L13;
        r2 = false;
    L13:
        monitor-exit(PlaceholderSurface.class);
        return r2;
    L7:
        th = move-exception;
        throw th;
    }

    public static PlaceholderSurface newInstance(Context r1, boolean r2) {
        int r0 = 0;
        if (r2 == true) goto L5;
    L8:
        boolean r12 = true;
    L9:
        Assertions.checkState(r12);
        PlaceholderSurfaceThread r13 = new PlaceholderSurfaceThread();
        if (r2 == false) goto L13;
        r0 = secureMode;
    L13:
        return r13.init(r0);
    L5:
        if (isSecureSupported(r1) == true) goto L8;
        r12 = false;
        goto L9
    }

    @Override // android.view.Surface
    public void release() {
        super.release();
        PlaceholderSurfaceThread r0 = this.thread;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.threadReleased == true) goto L9;
        this.thread.release();     // Catch: Throwable -> L7
        this.threadReleased = true;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }
}
