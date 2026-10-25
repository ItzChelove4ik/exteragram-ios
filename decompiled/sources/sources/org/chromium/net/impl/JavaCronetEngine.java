package org.chromium.net.impl;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.metrics.ScopedSysTraceEvent;
import org.chromium.net.ExperimentalUrlRequest;
import org.chromium.net.RequestFinishedInfo;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UrlRequest;
import org.chromium.net.impl.CronetLogger;

/* loaded from: classes5.dex */
public final class JavaCronetEngine extends CronetEngineBase {
    private static final String TAG = "JavaCronetEngine";
    private final AtomicInteger mActiveRequestCount;
    private final Context mContext;
    private final int mCronetEngineId;
    private final ExecutorService mExecutorService;
    private final CronetLogger mLogger;
    private long mNetworkHandle;
    private final String mUserAgent;

    /* renamed from: org.chromium.net.impl.JavaCronetEngine$1, reason: invalid class name */
    public class AnonymousClass1 implements ThreadFactory {
        final /* synthetic */ JavaCronetEngine this$0;

        /* renamed from: org.chromium.net.impl.JavaCronetEngine$1$1, reason: invalid class name and collision with other inner class name */
        public class RunnableC00321 implements Runnable {
            final /* synthetic */ AnonymousClass1 this$1;
            final /* synthetic */ Runnable val$r;

            public RunnableC00321(AnonymousClass1 r1, Runnable r2) {
                this.val$r = r2;
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                Thread.currentThread().setName("JavaCronetEngine");
                Process.setThreadPriority(0);
                this.val$r.run();
            }
        }

        public AnonymousClass1(JavaCronetEngine r1) {
            this.this$0 = r1;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r3) {
            return Executors.defaultThreadFactory().newThread(new RunnableC00321(this, r3));
        }
    }

    static {
    }

    public JavaCronetEngine(CronetEngineBuilderImpl r13) {
        this.mActiveRequestCount = new AtomicInteger();
        this.mNetworkHandle = -1;
        ScopedSysTraceEvent r1 = ScopedSysTraceEvent.scoped("JavaCronetEngine#JavaCronetEngine");
        Context r0 = r13.getContext();     // Catch: Throwable -> L7
        this.mContext = r0;     // Catch: Throwable -> L7
        int r2 = hashCode();     // Catch: Throwable -> L7
        this.mCronetEngineId = r2;     // Catch: Throwable -> L7
        this.mUserAgent = r13.getUserAgent();     // Catch: Throwable -> L7
        this.mExecutorService = new ThreadPoolExecutor(10, 10, 50, TimeUnit.SECONDS, new LinkedBlockingQueue(), new AnonymousClass1(this));     // Catch: Throwable -> L7
        CronetLogger.CronetSource r10 = CronetLogger.CronetSource.CRONET_SOURCE_FALLBACK;     // Catch: Throwable -> L7
        CronetLogger r5 = CronetLoggerFactory.createLogger(r0, r10);     // Catch: Throwable -> L7
        this.mLogger = r5;     // Catch: Throwable -> L7
        r5.logCronetEngineCreation(r2, r13.toLoggerInfo(), buildCronetVersion(), r10);     // Catch: Throwable -> L7 RuntimeException -> L9
    L12:
        Log.w(TAG, "using the fallback Cronet Engine implementation. Performance will suffer and many HTTP client features, including caching, will not work.");     // Catch: Throwable -> L7
        if (r1 == null) goto L28;
        r1.close();
        return;
    L28:
        return;
    L9:
        e = move-exception;
        Log.e(TAG, "Error while trying to log JavaCronetEngine creation: ", e);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        if (r1 == null) goto L29;
        r1.close();     // Catch: Throwable -> L19
        throw th;
    L19:
        th = move-exception;
        th.addSuppressed(th);
        throw th;
    L29:
        throw th;
    }

    private CronetLogger.CronetVersion buildCronetVersion() {
        return new CronetLogger.CronetVersion(getVersionString().split("/")[1].split("@")[0]);
    }

    @Override // org.chromium.net.impl.CronetEngineBase
    public ExperimentalUrlRequest createRequest(String r19, UrlRequest.Callback r20, Executor r21, int r22, Collection<Object> r23, boolean r24, boolean r25, boolean r26, boolean r27, int r28, boolean r29, int r30, RequestFinishedInfo.Listener r31, int r32, long r33, String r35, ArrayList<Map.Entry<String, String>> r36, UploadDataProvider r37, Executor r38, byte[] r39, ByteBuffer r40, String r41) {
        if (r33 == (-1)) goto L6;
        this.mNetworkHandle = r33;
    L6:
        return new JavaUrlRequest(this, r20, this.mExecutorService, r21, r19, this.mUserAgent, r26, r27, r28, r29, r30, this.mNetworkHandle, r35, r36, r37, r38);
    }

    public void decrementActiveRequestCount() {
        this.mActiveRequestCount.decrementAndGet();
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getCronetEngineId() {
        return this.mCronetEngineId;
    }

    public CronetLogger getCronetLogger() {
        return this.mLogger;
    }

    public String getVersionString() {
        return "CronetHttpURLConnection/" + ImplVersion.getCronetVersionWithLastChange();
    }

    public void incrementActiveRequestCount() {
        this.mActiveRequestCount.incrementAndGet();
    }

    @Override // org.chromium.net.CronetEngine
    public /* bridge */ /* synthetic */ UrlRequest.Builder newUrlRequestBuilder(String r1, UrlRequest.Callback r2, Executor r3) {
        return super.newUrlRequestBuilder(r1, r2, r3);
    }
}
