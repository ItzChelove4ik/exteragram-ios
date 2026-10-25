package org.chromium.net.impl;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticBUOutline0;
import androidx.webkit.WebMessageCompat$$ExternalSyntheticBUOutline0;
import c.f$$ExternalSyntheticBUOutline1;
import j$.time.Duration;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.chromium.base.metrics.ScopedSysTraceEvent;
import org.chromium.net.CronetException;
import org.chromium.net.ExperimentalUrlRequest;
import org.chromium.net.InlineExecutionProhibitedException;
import org.chromium.net.ThreadStatsUid;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;
import org.chromium.net.impl.CronetLogger;
import org.chromium.net.impl.JavaUrlRequestUtils;

/* loaded from: classes5.dex */
final class JavaUrlRequest extends ExperimentalUrlRequest {
    private static final String TAG = "JavaUrlRequest";
    private volatile int mAdditionalStatusDetails;
    private final boolean mAllowDirectExecutor;
    private final AsyncUrlRequestCallback mCallbackAsync;
    private final int mCronetEngineId;
    private String mCurrentUrl;
    private HttpURLConnection mCurrentUrlConnection;
    private final JavaCronetEngine mEngine;
    private final Executor mExecutor;
    private boolean mFinalUserCallbackThrew;
    private final String mInitialMethod;
    private final CronetLogger mLogger;
    private final long mNetworkHandle;
    private int mNonfinalUserCallbackExceptionCount;
    private OutputStreamDataSink mOutputStreamDataSink;
    private String mPendingRedirectUrl;
    private int mReadCount;
    private final Map<String, String> mRequestHeaders;
    private ReadableByteChannel mResponseChannel;
    private final AtomicInteger mState;
    private final VersionSafeCallbacks$UploadDataProviderWrapper mUploadDataProvider;
    private final Executor mUploadExecutor;
    private final AtomicBoolean mUploadProviderClosed;
    private final List<String> mUrlChain;
    private UrlResponseInfoImpl mUrlResponseInfo;
    private final String mUserAgent;

    /* renamed from: org.chromium.net.impl.JavaUrlRequest$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ JavaUrlRequest this$0;

        public AnonymousClass1(JavaUrlRequest r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            JavaUrlRequest r0 = this.this$0;
            JavaUrlRequest.m3825$$Nest$fputmCurrentUrl(r0, JavaUrlRequest.m3819$$Nest$fgetmPendingRedirectUrl(r0));
            JavaUrlRequest.m3826$$Nest$fputmPendingRedirectUrl(this.this$0, null);
            JavaUrlRequest.m3833$$Nest$mfireOpenConnection(this.this$0);
        }
    }

    public final class AsyncUrlRequestCallback {
        final VersionSafeCallbacks$UrlRequestCallback mCallback;
        final Executor mFallbackExecutor;
        final Executor mUserExecutor;
        final /* synthetic */ JavaUrlRequest this$0;

        /* renamed from: $r8$lambda$-ElxwdM5p9ntEnR5c00jhA5ySA4, reason: not valid java name */
        public static /* synthetic */ void m3839$r8$lambda$ElxwdM5p9ntEnR5c00jhA5ySA4(AsyncUrlRequestCallback r0, UrlResponseInfo r1) {
            r0.lambda$onSucceeded$6(r1);
        }

        /* renamed from: $r8$lambda$D3Lg1jcdUAIZEKduVfK-uga6A4E, reason: not valid java name */
        public static /* synthetic */ void m3840$r8$lambda$D3Lg1jcdUAIZEKduVfKuga6A4E(String r2, Runnable r3) {
            ScopedSysTraceEvent r22 = ScopedSysTraceEvent.scoped("Cronet JavaUrlRequest.AsyncUrlRequestCallback#executeOnUserExecutor " + r2 + " running callback");
            r3.run();     // Catch: Throwable -> L7
            if (r22 == null) goto L18;
            r22.close();
            return;
        L18:
            return;
        L7:
            th = move-exception;
            if (r22 != null) goto L16;
        L13:
            throw th;
        L16:
            r22.close();     // Catch: Throwable -> L11
        L11:
            th = move-exception;
            th.addSuppressed(th);
            goto L13
        }

        /* renamed from: $r8$lambda$L2MmDQF1IP9TU3XYnKRRuW-u5Xs, reason: not valid java name */
        public static /* synthetic */ void m3841$r8$lambda$L2MmDQF1IP9TU3XYnKRRuWu5Xs(AsyncUrlRequestCallback r0, UrlResponseInfo r1, ByteBuffer r2) {
            r0.lambda$onReadCompleted$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$RXe1m0PdxXhwUrKBIben1xR2I_M(AsyncUrlRequestCallback r0, UrlResponseInfo r1, String r2) {
            r0.lambda$onRedirectReceived$1(r1, r2);
        }

        /* renamed from: $r8$lambda$U-zbUASvl6MLjUvoY4iIFSdcqHE, reason: not valid java name */
        public static /* synthetic */ void m3842$r8$lambda$UzbUASvl6MLjUvoY4iIFSdcqHE(AsyncUrlRequestCallback r0, UrlResponseInfo r1) {
            r0.lambda$onCanceled$5(r1);
        }

        public static /* synthetic */ void $r8$lambda$WCDd2pugEgBS6NGVWYDm2iefcwo(AsyncUrlRequestCallback r0) {
            r0.lambda$onResponseStarted$2();
        }

        public static /* synthetic */ void $r8$lambda$YvZtwCJrGUY8GOrtOFEOSZ9D6T8(AsyncUrlRequestCallback r0, UrlResponseInfo r1, CronetException r2) {
            r0.lambda$onFailed$7(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$_9B4GRLfAWjHu6e_D7q3tew4R6c(String r2, Runnable r3) {
            ScopedSysTraceEvent r22 = ScopedSysTraceEvent.scoped("Cronet JavaUrlRequest.AsyncUrlRequestCallback#executeOnFallbackExecutor  " + r2 + " running callback");
            r3.run();     // Catch: Throwable -> L7
            if (r22 == null) goto L18;
            r22.close();
            return;
        L18:
            return;
        L7:
            th = move-exception;
            if (r22 != null) goto L16;
        L13:
            throw th;
        L16:
            r22.close();     // Catch: Throwable -> L11
        L11:
            th = move-exception;
            th.addSuppressed(th);
            goto L13
        }

        public static /* synthetic */ void $r8$lambda$wBc_bJEQLJvr4m_oENjqxQ9IYig(AsyncUrlRequestCallback r0) {
            r0.lambda$maybeReportMetrics$4();
        }

        static {
        }

        public AsyncUrlRequestCallback(JavaUrlRequest r2, UrlRequest.Callback r3, Executor r4) {
            this.this$0 = r2;
            this.mCallback = new VersionSafeCallbacks$UrlRequestCallback(r3);
            if (JavaUrlRequest.m3812$$Nest$fgetmAllowDirectExecutor(r2) == false) goto L6;
            this.mUserExecutor = r4;
            this.mFallbackExecutor = null;
            return;
        L6:
            this.mUserExecutor = new JavaUrlRequestUtils.DirectPreventingExecutor(r4);
            this.mFallbackExecutor = r4;
        }

        private CronetLogger.CronetTrafficInfo buildCronetTrafficInfo() {
            int r2 = 0;
            if (JavaUrlRequest.m3823$$Nest$fgetmUrlResponseInfo(this.this$0) == null) goto L6;
            Map<String, List<String>> r1 = JavaUrlRequest.m3823$$Nest$fgetmUrlResponseInfo(this.this$0).getAllHeaders();
            String r3 = JavaUrlRequest.m3823$$Nest$fgetmUrlResponseInfo(this.this$0).getNegotiatedProtocol();
            int r4 = JavaUrlRequest.m3823$$Nest$fgetmUrlResponseInfo(this.this$0).getHttpStatusCode();
            boolean r5 = JavaUrlRequest.m3823$$Nest$fgetmUrlResponseInfo(this.this$0).wasCached();
            int r12 = r4;
        L5:
            String r15 = r3;
            long r32 = -1;
            long r6 = 0;
            if (r5 == false) goto L10;
            long r8 = 0;
            long r10 = 0;
        L11:
            if (r5 == false) goto L13;
            r32 = r10;
            long r13 = r32;
        L16:
            Duration r14 = Duration.ofSeconds(r10);
            Duration r52 = Duration.ofSeconds(r10);
            int r102 = JavaUrlRequest.m3822$$Nest$fgetmState(this.this$0).get();
            if (r102 != 6) goto L19;
            CronetLogger.CronetTrafficInfo.RequestTerminalState r103 = CronetLogger.CronetTrafficInfo.RequestTerminalState.ERROR;
        L23:
            CronetLogger.CronetTrafficInfo.RequestTerminalState r18 = r103;
            long r104 = r32;
            int r19 = JavaUrlRequest.m3817$$Nest$fgetmNonfinalUserCallbackExceptionCount(this.this$0);
            int r20 = JavaUrlRequest.m3820$$Nest$fgetmReadCount(this.this$0);
            if (JavaUrlRequest.m3818$$Nest$fgetmOutputStreamDataSink(this.this$0) == null) goto L33;
            r2 = JavaUrlRequest.m3818$$Nest$fgetmOutputStreamDataSink(this.this$0).getReadCount();
        L33:
            return new CronetLogger.CronetTrafficInfo(r8, r6, r13, r104, r12, r14, r52, r15, false, false, r18, r19, r20, r2, false, JavaUrlRequest.m3815$$Nest$fgetmFinalUserCallbackThrew(this.this$0), Process.myUid(), 0, 0, 0, CronetLogger.CronetTrafficInfo.RequestFailureReason.UNKNOWN, false, ImplVersion.getCronetVersion(), CronetLogger.CronetSource.CRONET_SOURCE_FALLBACK, -1, -1, -1, -1);
        L19:
            if (r102 != 7) goto L21;
            r103 = CronetLogger.CronetTrafficInfo.RequestTerminalState.SUCCEEDED;
            goto L23
        L21:
            if (r102 != 8) goto L24;
            r103 = CronetLogger.CronetTrafficInfo.RequestTerminalState.CANCELLED;
            goto L23
        L24:
            ExifInterface$$ExternalSyntheticBUOutline0.m("Internal Cronet error: attempted to report metrics but current state (", r102, ") is not a done state!");
            return null;
        L13:
            r13 = JavaUrlRequest.estimateHeadersSizeInBytesList(r1);
            if (r1.containsKey("Content-Length") == false) goto L16;
            r32 = JavaUrlRequest.m3838$$Nest$smparseContentLengthString(r1.get("Content-Length").get(0));
            goto L16
        L10:
            r8 = JavaUrlRequest.estimateHeadersSizeInBytes(JavaUrlRequest.m3821$$Nest$fgetmRequestHeaders(this.this$0));
            r10 = 0;
            r6 = -1;
            goto L11
        L6:
            r1 = Collections.EMPTY_MAP;
            r3 = _UrlKt.FRAGMENT_ENCODE_SET;
            r5 = false;
            r12 = 0;
            goto L5
        }

        private /* synthetic */ void lambda$maybeReportMetrics$4() {
            JavaUrlRequest.m3816$$Nest$fgetmLogger(this.this$0).logCronetTrafficInfo(JavaUrlRequest.m3813$$Nest$fgetmCronetEngineId(this.this$0), buildCronetTrafficInfo());     // Catch: RuntimeException -> L4
            return;
        L4:
            e = move-exception;
            Log.i(JavaUrlRequest.m3837$$Nest$sfgetTAG(), "Error while trying to log CronetTrafficInfo: ", e);
        }

        private /* synthetic */ void lambda$onCanceled$5(UrlResponseInfo r3) {
            this.mCallback.onCanceled(this.this$0, r3);     // Catch: Exception -> L4
        L6:
            maybeReportMetrics();
            JavaUrlRequest.m3814$$Nest$fgetmEngine(this.this$0).decrementActiveRequestCount();
            return;
        L4:
            e = move-exception;
            JavaUrlRequest.m3834$$Nest$monFinalCallbackException(this.this$0, "onCanceled", e);
            goto L6
        }

        private /* synthetic */ void lambda$onFailed$7(UrlResponseInfo r3, CronetException r4) {
            this.mCallback.onFailed(this.this$0, r3, r4);     // Catch: Exception -> L4
        L6:
            maybeReportMetrics();
            JavaUrlRequest.m3814$$Nest$fgetmEngine(this.this$0).decrementActiveRequestCount();
            return;
        L4:
            e = move-exception;
            JavaUrlRequest.m3834$$Nest$monFinalCallbackException(this.this$0, "onFailed", e);
            goto L6
        }

        private /* synthetic */ void lambda$onReadCompleted$3(UrlResponseInfo r4, ByteBuffer r5) {
            if (JavaUrlRequest.m3822$$Nest$fgetmState(this.this$0).compareAndSet(5, 4) == false) goto L6;
            this.mCallback.onReadCompleted(this.this$0, r4, r5);
            return;
        }

        private /* synthetic */ void lambda$onRedirectReceived$1(UrlResponseInfo r2, String r3) {
            this.mCallback.onRedirectReceived(this.this$0, r2, r3);
        }

        private /* synthetic */ void lambda$onResponseStarted$2() {
            if (JavaUrlRequest.m3822$$Nest$fgetmState(this.this$0).compareAndSet(1, 4) == false) goto L6;
            VersionSafeCallbacks$UrlRequestCallback r0 = this.mCallback;
            JavaUrlRequest r3 = this.this$0;
            r0.onResponseStarted(r3, JavaUrlRequest.m3823$$Nest$fgetmUrlResponseInfo(r3));
            return;
        }

        private /* synthetic */ void lambda$onSucceeded$6(UrlResponseInfo r3) {
            this.mCallback.onSucceeded(this.this$0, r3);     // Catch: Exception -> L4
        L6:
            maybeReportMetrics();
            JavaUrlRequest.m3814$$Nest$fgetmEngine(this.this$0).decrementActiveRequestCount();
            return;
        L4:
            e = move-exception;
            JavaUrlRequest.m3834$$Nest$monFinalCallbackException(this.this$0, "onSucceded", e);
            goto L6
        }

        private void maybeReportMetrics() {
            if (Build.VERSION.SDK_INT >= 26) goto L5;
            return;
        L5:
            JavaUrlRequest.m3831$$Nest$mexecuteOnExecutor(this.this$0, new JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda7(this), "maybeReportMetrics");
        }

        public void execute(JavaUrlRequestUtils.CheckedRunnable r2, String r3) {
            executeOnUserExecutor(JavaUrlRequest.m3836$$Nest$muserErrorSetting(this.this$0, r2), r3);     // Catch: RejectedExecutionException -> L4
            return;
        L4:
            e = move-exception;
            JavaUrlRequest.m3828$$Nest$menterErrorState(this.this$0, new CronetExceptionImpl("Exception posting task to executor", e));
        }

        public void executeOnFallbackExecutor(Runnable r3, String r4) {
            ScopedSysTraceEvent r0 = ScopedSysTraceEvent.scoped("Cronet JavaUrlRequest.AsyncUrlRequestCallback#executeOnFallbackExecutor " + r4);
            this.mFallbackExecutor.execute(new JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda5(r4, r3));     // Catch: Throwable -> L7
            if (r0 == null) goto L18;
            r0.close();
            return;
        L18:
            return;
        L7:
            th = move-exception;
            if (r0 != null) goto L16;
        L13:
            throw th;
        L16:
            r0.close();     // Catch: Throwable -> L11
        L11:
            th = move-exception;
            th.addSuppressed(th);
            goto L13
        }

        public void executeOnUserExecutor(Runnable r3, String r4) {
            ScopedSysTraceEvent r0 = ScopedSysTraceEvent.scoped("Cronet JavaUrlRequest.AsyncUrlRequestCallback#executeOnUserExecutor " + r4);
            this.mUserExecutor.execute(new JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda4(r4, r3));     // Catch: Throwable -> L7
            if (r0 == null) goto L18;
            r0.close();
            return;
        L18:
            return;
        L7:
            th = move-exception;
            if (r0 != null) goto L16;
        L13:
            throw th;
        L16:
            r0.close();     // Catch: Throwable -> L11
        L11:
            th = move-exception;
            th.addSuppressed(th);
            goto L13
        }

        public void onCanceled(UrlResponseInfo r2) {
            JavaUrlRequest.m3827$$Nest$mcloseResponseChannel(this.this$0);
            executeOnUserExecutor(new JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda0(this, r2), "onCanceled");
        }

        public void onFailed(UrlResponseInfo r3, CronetException r4) {
            JavaUrlRequest.m3827$$Nest$mcloseResponseChannel(this.this$0);
            JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda2 r1 = new JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda2(this, r3, r4);
            executeOnUserExecutor(r1, "onFailed");     // Catch: InlineExecutionProhibitedException -> L5
            return;
        L6:
            if (this.mFallbackExecutor == null) goto L11;
            executeOnFallbackExecutor(r1, "onFailed");
            return;
        }

        public void onReadCompleted(UrlResponseInfo r2, ByteBuffer r3) {
            execute(new JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda3(this, r2, r3), "onReadCompleted");
        }

        public void onRedirectReceived(UrlResponseInfo r2, String r3) {
            execute(new JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda8(this, r2, r3), "onRedirectReceived");
        }

        public void onResponseStarted() {
            execute(new JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda6(this), "onResponseStarted");
        }

        public void onSucceeded(UrlResponseInfo r2) {
            executeOnUserExecutor(new JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda1(this, r2), "onSucceeded");
        }
    }

    public final class OutputStreamDataSink extends JavaUploadDataSinkBase {
        private WritableByteChannel mOutputChannel;
        private final AtomicBoolean mOutputChannelClosed;
        private final HttpURLConnection mUrlConnection;
        private OutputStream mUrlConnectionOutputStream;
        final /* synthetic */ JavaUrlRequest this$0;

        public OutputStreamDataSink(JavaUrlRequest r1, Executor r2, Executor r3, HttpURLConnection r4, VersionSafeCallbacks$UploadDataProviderWrapper r5) {
            this.this$0 = r1;
            super(r2, r3, r5);
            this.mOutputChannelClosed = new AtomicBoolean(false);
            this.mUrlConnection = r4;
        }

        public void closeOutputChannel() {
            if (this.mOutputChannel != null) goto L5;
            return;
        L5:
            if (this.mOutputChannelClosed.compareAndSet(false, true) == false) goto L9;
            this.mOutputChannel.close();
            return;
        }

        @Override // org.chromium.net.impl.JavaUploadDataSinkBase
        public void finish() {
            closeOutputChannel();
            JavaUrlRequest.m3832$$Nest$mfireGetHeaders(this.this$0);
        }

        @Override // org.chromium.net.impl.JavaUploadDataSinkBase
        public Runnable getErrorSettingRunnable(JavaUrlRequestUtils.CheckedRunnable r1) {
            return JavaUrlRequest.m3830$$Nest$merrorSetting(this.this$0, r1);
        }

        @Override // org.chromium.net.impl.JavaUploadDataSinkBase
        public Runnable getUploadErrorSettingRunnable(JavaUrlRequestUtils.CheckedRunnable r1) {
            return JavaUrlRequest.m3835$$Nest$muploadErrorSetting(this.this$0, r1);
        }

        @Override // org.chromium.net.impl.JavaUploadDataSinkBase
        public void initializeRead() {
            if (this.mOutputChannel != null) goto L6;
            JavaUrlRequest.m3824$$Nest$fputmAdditionalStatusDetails(this.this$0, 10);
            this.mUrlConnection.setDoOutput(true);
            this.mUrlConnection.connect();
            JavaUrlRequest.m3824$$Nest$fputmAdditionalStatusDetails(this.this$0, 12);
            OutputStream r0 = this.mUrlConnection.getOutputStream();
            this.mUrlConnectionOutputStream = r0;
            this.mOutputChannel = Channels.newChannel(r0);
            return;
        }

        @Override // org.chromium.net.impl.JavaUploadDataSinkBase
        public void initializeStart(long r3) {
            HttpURLConnection r2 = this.mUrlConnection;
            if (r3 <= 0) goto L6;
            r2.setFixedLengthStreamingMode(r3);
            return;
        L6:
            r2.setChunkedStreamingMode(8192);
        }

        @Override // org.chromium.net.impl.JavaUploadDataSinkBase
        public int processSuccessfulRead(ByteBuffer r3) {
            int r0 = 0;
        L4:
            if (r3.hasRemaining() == false) goto L6;
            r0 = r0 + this.mOutputChannel.write(r3);
            goto L4
        L6:
            this.mUrlConnectionOutputStream.flush();
            return r0;
        }

        @Override // org.chromium.net.impl.JavaUploadDataSinkBase
        public void processUploadError(Throwable r1) {
            JavaUrlRequest.m3829$$Nest$menterUploadErrorState(this.this$0, r1);
        }
    }

    public static final class SerializingExecutor implements Executor {
        private final Runnable mRunTasks;
        private boolean mRunning;
        private final ArrayDeque<Runnable> mTaskQueue;
        private final Executor mUnderlyingExecutor;

        public static /* synthetic */ void $r8$lambda$73N2fPyyNbL0NrlrYZlCbgePDq0(SerializingExecutor r0) {
            r0.runTasks();
        }

        public SerializingExecutor(Executor r2) {
            this.mRunTasks = new JavaUrlRequest$SerializingExecutor$$ExternalSyntheticLambda0(this);
            this.mTaskQueue = new ArrayDeque();
            this.mUnderlyingExecutor = r2;
        }

        private void runTasks() {
            ArrayDeque<Runnable> r0 = this.mTaskQueue;
            monitor-enter(r0);
        L8:
            th = move-exception;
            throw th;
        L5:
            if (this.mRunning == false) goto L10;
            monitor-exit(r0);     // Catch: Throwable -> L8
            return;
        L10:
            Runnable r1 = this.mTaskQueue.pollFirst();     // Catch: Throwable -> L8
            if (r1 == null) goto L13;
            boolean r4 = true;
        L14:
            this.mRunning = r4;     // Catch: Throwable -> L8
            monitor-exit(r0);     // Catch: Throwable -> L8
        L16:
            if (r1 == null) goto L42;
            r1.run();     // Catch: Throwable -> L30
            ArrayDeque<Runnable> r02 = this.mTaskQueue;
            monitor-enter(r02);
            r1 = this.mTaskQueue.pollFirst();     // Catch: Throwable -> L27
            if (r1 == null) goto L23;
            boolean r42 = true;
        L24:
            this.mRunning = r42;     // Catch: Throwable -> L27
            monitor-exit(r02);     // Catch: Throwable -> L27
            goto L16
        L23:
            r42 = false;
        L27:
            th = move-exception;
            throw th;
        L30:
            th = move-exception;
            monitor-enter(this.mTaskQueue);
            this.mRunning = false;     // Catch: Throwable -> L36
            this.mUnderlyingExecutor.execute(this.mRunTasks);     // Catch: Throwable -> L36 RejectedExecutionException -> L45
        L38:
            throw th;
        L36:
            th = move-exception;
            throw th;
        L42:
            return;
        L13:
            r4 = false;
            goto L14
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r3) {
            ArrayDeque<Runnable> r0 = this.mTaskQueue;
            monitor-enter(r0);
            this.mTaskQueue.addLast(r3);     // Catch: Throwable -> L7
            this.mUnderlyingExecutor.execute(this.mRunTasks);     // Catch: Throwable -> L7 RejectedExecutionException -> L9
        L10:
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L9:
            this.mTaskQueue.removeLast();     // Catch: Throwable -> L7
        L7:
            th = move-exception;
            throw th;
        }
    }

    public static /* synthetic */ void $r8$lambda$02qzLzE_VPpZarHuSLhMV6jVulc(JavaUrlRequest r0) {
        r0.lambda$fireGetHeaders$4();
    }

    public static /* synthetic */ void $r8$lambda$38dzsNc1zmdQHSuBA_JyRMFyZtI(JavaUrlRequest r0) {
        r0.lambda$start$2();
    }

    public static /* synthetic */ void $r8$lambda$8Jo97Oa6sMq63KSfyXfVRlvw9I0(int r1, boolean r2, int r3, Runnable r4) {
        int r0 = TrafficStats.getThreadStatsTag();
        TrafficStats.setThreadStatsTag(r1);
        if (r2 == false) goto L15;
        ThreadStatsUid.set(r3);
    L15:
        r4.run();     // Catch: Throwable -> L10
        if (r2 == false) goto L8;
        ThreadStatsUid.clear();
    L8:
        TrafficStats.setThreadStatsTag(r0);
        return;
    L10:
        th = move-exception;
        if (r2 == false) goto L13;
        ThreadStatsUid.clear();
    L13:
        TrafficStats.setThreadStatsTag(r0);
        throw th;
    }

    public static /* synthetic */ void $r8$lambda$GSdwLjQ4bmMzASRLACmvH5xfWmo(JavaUrlRequest r0) {
        r0.lambda$fireOpenConnection$7();
    }

    public static /* synthetic */ void $r8$lambda$NQYCbe0U94j06X1DTQBiU1HE4R4(String r2, Runnable r3) {
        ScopedSysTraceEvent r22 = ScopedSysTraceEvent.scoped("Cronet JavaUrlRequest#executeOnExecutor " + r2 + " running callback");
        r3.run();     // Catch: Throwable -> L7
        if (r22 == null) goto L18;
        r22.close();
        return;
    L18:
        return;
    L7:
        th = move-exception;
        if (r22 != null) goto L16;
    L13:
        throw th;
    L16:
        r22.close();     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        th.addSuppressed(th);
        goto L13
    }

    public static /* synthetic */ void $r8$lambda$PIFh1790Oeyx5a3X_KbAZQz5meg(JavaUrlRequest r0, JavaUrlRequestUtils.CheckedRunnable r1) {
        r0.lambda$userErrorSetting$9(r1);
    }

    /* renamed from: $r8$lambda$Wkt5Drg-UQxBhoSqw7stujcUC7Y, reason: not valid java name */
    public static /* synthetic */ void m3808$r8$lambda$Wkt5DrgUQxBhoSqw7stujcUC7Y(JavaUrlRequest r0, JavaUrlRequestUtils.CheckedRunnable r1) {
        r0.lambda$read$12(r1);
    }

    /* renamed from: $r8$lambda$_wWXlBR8WJ1mu3-hFNKhRrcaJoA, reason: not valid java name */
    public static /* synthetic */ void m3809$r8$lambda$_wWXlBR8WJ1mu3hFNKhRrcaJoA(JavaUrlRequest r0) {
        r0.lambda$closeResponseChannel$14();
    }

    public static /* synthetic */ void $r8$lambda$aHdNiaUE4hHQdj0jbu3NBWOvxDg(JavaUrlRequest r0, JavaUrlRequestUtils.CheckedRunnable r1) {
        r0.lambda$uploadErrorSetting$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$bMvZoSNaUr4APxhn21vnlY_ECAQ(JavaUrlRequest r0, JavaUrlRequestUtils.CheckedRunnable r1) {
        r0.lambda$errorSetting$8(r1);
    }

    /* renamed from: $r8$lambda$bR7r4RzNBFEh7BVU9n2PGU-qg68, reason: not valid java name */
    public static /* synthetic */ void m3810$r8$lambda$bR7r4RzNBFEh7BVU9n2PGUqg68(JavaUrlRequest r0, String r1, UrlResponseInfo r2) {
        r0.lambda$fireRedirectReceived$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ecpyyaPHU986yHiuxnDquO9vmIo(Executor r1, int r2, boolean r3, int r4, Runnable r5) {
        r1.execute(new JavaUrlRequest$$ExternalSyntheticLambda8(r2, r3, r4, r5));
    }

    public static /* synthetic */ void $r8$lambda$gJzZ29OPTZVGbpITnuc1VPRlWyc(JavaUrlRequest r0) {
        r0.lambda$fireDisconnect$13();
    }

    public static /* synthetic */ void $r8$lambda$r4_dhdLDT53SSUzE8bdt2qsjO3Q(JavaUrlRequest r0, UrlResponseInfo r1) {
        r0.lambda$fireRedirectReceived$5(r1);
    }

    /* renamed from: $r8$lambda$u-jVhrWpEjz9BKtoI5JBlbbMeKM, reason: not valid java name */
    public static /* synthetic */ void m3811$r8$lambda$ujVhrWpEjz9BKtoI5JBlbbMeKM(JavaUrlRequest r0) {
        r0.lambda$enterUserErrorState$3();
    }

    public static /* synthetic */ void $r8$lambda$yRZjnistK_LSFIhhQZTjA_r2CdQ(JavaUrlRequest r0, ByteBuffer r1) {
        r0.lambda$read$11(r1);
    }

    /* renamed from: -$$Nest$fgetmAllowDirectExecutor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m3812$$Nest$fgetmAllowDirectExecutor(JavaUrlRequest r0) {
        return r0.mAllowDirectExecutor;
    }

    /* renamed from: -$$Nest$fgetmCronetEngineId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m3813$$Nest$fgetmCronetEngineId(JavaUrlRequest r0) {
        return r0.mCronetEngineId;
    }

    /* renamed from: -$$Nest$fgetmEngine, reason: not valid java name */
    public static /* bridge */ /* synthetic */ JavaCronetEngine m3814$$Nest$fgetmEngine(JavaUrlRequest r0) {
        return r0.mEngine;
    }

    /* renamed from: -$$Nest$fgetmFinalUserCallbackThrew, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m3815$$Nest$fgetmFinalUserCallbackThrew(JavaUrlRequest r0) {
        return r0.mFinalUserCallbackThrew;
    }

    /* renamed from: -$$Nest$fgetmLogger, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CronetLogger m3816$$Nest$fgetmLogger(JavaUrlRequest r0) {
        return r0.mLogger;
    }

    /* renamed from: -$$Nest$fgetmNonfinalUserCallbackExceptionCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m3817$$Nest$fgetmNonfinalUserCallbackExceptionCount(JavaUrlRequest r0) {
        return r0.mNonfinalUserCallbackExceptionCount;
    }

    /* renamed from: -$$Nest$fgetmOutputStreamDataSink, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OutputStreamDataSink m3818$$Nest$fgetmOutputStreamDataSink(JavaUrlRequest r0) {
        return r0.mOutputStreamDataSink;
    }

    /* renamed from: -$$Nest$fgetmPendingRedirectUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m3819$$Nest$fgetmPendingRedirectUrl(JavaUrlRequest r0) {
        return r0.mPendingRedirectUrl;
    }

    /* renamed from: -$$Nest$fgetmReadCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m3820$$Nest$fgetmReadCount(JavaUrlRequest r0) {
        return r0.mReadCount;
    }

    /* renamed from: -$$Nest$fgetmRequestHeaders, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Map m3821$$Nest$fgetmRequestHeaders(JavaUrlRequest r0) {
        return r0.mRequestHeaders;
    }

    /* renamed from: -$$Nest$fgetmState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AtomicInteger m3822$$Nest$fgetmState(JavaUrlRequest r0) {
        return r0.mState;
    }

    /* renamed from: -$$Nest$fgetmUrlResponseInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UrlResponseInfoImpl m3823$$Nest$fgetmUrlResponseInfo(JavaUrlRequest r0) {
        return r0.mUrlResponseInfo;
    }

    /* renamed from: -$$Nest$fputmAdditionalStatusDetails, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3824$$Nest$fputmAdditionalStatusDetails(JavaUrlRequest r0, int r1) {
        r0.mAdditionalStatusDetails = r1;
    }

    /* renamed from: -$$Nest$fputmCurrentUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3825$$Nest$fputmCurrentUrl(JavaUrlRequest r0, String r1) {
        r0.mCurrentUrl = r1;
    }

    /* renamed from: -$$Nest$fputmPendingRedirectUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3826$$Nest$fputmPendingRedirectUrl(JavaUrlRequest r0, String r1) {
        r0.mPendingRedirectUrl = r1;
    }

    /* renamed from: -$$Nest$mcloseResponseChannel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3827$$Nest$mcloseResponseChannel(JavaUrlRequest r0) {
        r0.closeResponseChannel();
    }

    /* renamed from: -$$Nest$menterErrorState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3828$$Nest$menterErrorState(JavaUrlRequest r0, CronetException r1) {
        r0.enterErrorState(r1);
    }

    /* renamed from: -$$Nest$menterUploadErrorState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3829$$Nest$menterUploadErrorState(JavaUrlRequest r0, Throwable r1) {
        r0.enterUploadErrorState(r1);
    }

    /* renamed from: -$$Nest$merrorSetting, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m3830$$Nest$merrorSetting(JavaUrlRequest r0, JavaUrlRequestUtils.CheckedRunnable r1) {
        return r0.errorSetting(r1);
    }

    /* renamed from: -$$Nest$mexecuteOnExecutor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3831$$Nest$mexecuteOnExecutor(JavaUrlRequest r0, Runnable r1, String r2) {
        r0.executeOnExecutor(r1, r2);
    }

    /* renamed from: -$$Nest$mfireGetHeaders, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3832$$Nest$mfireGetHeaders(JavaUrlRequest r0) {
        r0.fireGetHeaders();
    }

    /* renamed from: -$$Nest$mfireOpenConnection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3833$$Nest$mfireOpenConnection(JavaUrlRequest r0) {
        r0.fireOpenConnection();
    }

    /* renamed from: -$$Nest$monFinalCallbackException, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3834$$Nest$monFinalCallbackException(JavaUrlRequest r0, String r1, Exception r2) {
        r0.onFinalCallbackException(r1, r2);
    }

    /* renamed from: -$$Nest$muploadErrorSetting, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m3835$$Nest$muploadErrorSetting(JavaUrlRequest r0, JavaUrlRequestUtils.CheckedRunnable r1) {
        return r0.uploadErrorSetting(r1);
    }

    /* renamed from: -$$Nest$muserErrorSetting, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m3836$$Nest$muserErrorSetting(JavaUrlRequest r0, JavaUrlRequestUtils.CheckedRunnable r1) {
        return r0.userErrorSetting(r1);
    }

    /* renamed from: -$$Nest$sfgetTAG, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m3837$$Nest$sfgetTAG() {
        return TAG;
    }

    /* renamed from: -$$Nest$smparseContentLengthString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m3838$$Nest$smparseContentLengthString(String r2) {
        return parseContentLengthString(r2);
    }

    static {
    }

    public JavaUrlRequest(JavaCronetEngine r7, UrlRequest.Callback r8, Executor r9, Executor r10, String r11, String r12, boolean r13, boolean r14, int r15, boolean r16, int r17, long r18, String r20, ArrayList<Map.Entry<String, String>> r21, UploadDataProvider r22, Executor r23) {
        this.mRequestHeaders = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        this.mUrlChain = new ArrayList();
        this.mState = new AtomicInteger(0);
        this.mUploadProviderClosed = new AtomicBoolean(false);
        this.mAdditionalStatusDetails = -1;
        ScopedSysTraceEvent r2 = ScopedSysTraceEvent.scoped("Cronet JavaUrlRequest#JavaUrlRequest");
        Objects.requireNonNull(r11, "URL is required");     // Catch: Throwable -> L12
        Objects.requireNonNull(r8, "Listener is required");     // Catch: Throwable -> L12
        Objects.requireNonNull(r9, "Executor is required");     // Catch: Throwable -> L12
        Objects.requireNonNull(r10, "userExecutor is required");     // Catch: Throwable -> L12
        this.mAllowDirectExecutor = r13;     // Catch: Throwable -> L12
        this.mCallbackAsync = new AsyncUrlRequestCallback(this, r8, r10);     // Catch: Throwable -> L12
        if (r14 == false) goto L6;
        int r82 = r15;
    L7:
        this.mExecutor = new SerializingExecutor(new JavaUrlRequest$$ExternalSyntheticLambda2(r9, r82, r16, r17));     // Catch: Throwable -> L12
        this.mEngine = r7;     // Catch: Throwable -> L12
        this.mCronetEngineId = r7.getCronetEngineId();     // Catch: Throwable -> L12
        this.mLogger = r7.getCronetLogger();     // Catch: Throwable -> L12
        this.mCurrentUrl = r11;     // Catch: Throwable -> L12
        this.mUserAgent = r12;     // Catch: Throwable -> L12
        this.mNetworkHandle = r18;     // Catch: Throwable -> L12
        this.mInitialMethod = checkedHttpMethod(r20);     // Catch: Throwable -> L12
        setHeaders(r21);     // Catch: Throwable -> L12
        this.mUploadDataProvider = checkedUploadDataProvider(r22);     // Catch: Throwable -> L12
        if (r23 == null) goto L14;
        if (r13 == true) goto L14;
        Executor r72 = new JavaUrlRequestUtils.DirectPreventingExecutor(r23);     // Catch: Throwable -> L12
    L15:
        this.mUploadExecutor = r72;     // Catch: Throwable -> L12
        if (r2 == null) goto L29;
        r2.close();
        return;
    L29:
        return;
    L14:
        r72 = r23;
        goto L15
    L6:
        r82 = TrafficStats.getThreadStatsTag();     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        if (r2 == null) goto L30;
        r2.close();     // Catch: Throwable -> L22
        throw th;
    L22:
        th = move-exception;
        th.addSuppressed(th);
        throw th;
    L30:
        throw th;
    }

    private static String checkedHttpMethod(String r1) {
        Objects.requireNonNull(r1, "Method is required.");
        if ("OPTIONS".equalsIgnoreCase(r1) == false) goto L5;
        return r1;
    L5:
        if ("GET".equalsIgnoreCase(r1) == false) goto L7;
        return r1;
    L7:
        if ("HEAD".equalsIgnoreCase(r1) == false) goto L9;
        return r1;
    L9:
        if ("POST".equalsIgnoreCase(r1) == false) goto L11;
        return r1;
    L11:
        if ("PUT".equalsIgnoreCase(r1) == false) goto L13;
        return r1;
    L13:
        if ("DELETE".equalsIgnoreCase(r1) == false) goto L15;
        return r1;
    L15:
        if ("TRACE".equalsIgnoreCase(r1) == false) goto L17;
        return r1;
    L17:
        if ("PATCH".equalsIgnoreCase(r1) == true) goto L28;
        f$$ExternalSyntheticBUOutline1.m("Invalid http method ".concat(r1));
        return null;
    L28:
        return r1;
    }

    private VersionSafeCallbacks$UploadDataProviderWrapper checkedUploadDataProvider(UploadDataProvider r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        if (this.mRequestHeaders.containsKey("Content-Type") == true) goto L8;
        f$$ExternalSyntheticBUOutline1.m("Requests with upload data must have a Content-Type.");
        return null;
    L8:
        return new VersionSafeCallbacks$UploadDataProviderWrapper(r3);
    }

    private void closeResponseChannel() {
        executeOnExecutor(new JavaUrlRequest$$ExternalSyntheticLambda11(this), "closeResponseChannel");
    }

    private void enterCronetErrorState(Throwable r3) {
        enterErrorState(new CronetExceptionImpl("System error", r3));
    }

    private void enterErrorState(CronetException r2) {
        if (setTerminalState(6) == false) goto L6;
        fireDisconnect();
        fireCloseUploadDataProvider();
        this.mCallbackAsync.onFailed(this.mUrlResponseInfo, r2);
        return;
    }

    private void enterUploadErrorState(Throwable r3) {
        enterErrorState(new CallbackExceptionImpl("Exception received from UploadDataProvider", r3));
    }

    private void enterUserErrorState(Throwable r3) {
        executeOnExecutor(new JavaUrlRequest$$ExternalSyntheticLambda16(this), "enterUserErrorState");
        enterErrorState(new CallbackExceptionImpl("Exception received from UrlRequest.Callback", r3));
    }

    private Runnable errorSetting(JavaUrlRequestUtils.CheckedRunnable r2) {
        return new JavaUrlRequest$$ExternalSyntheticLambda12(this, r2);
    }

    public static long estimateHeadersSizeInBytes(Map<String, String> r5) {
        long r0 = 0;
        if (r5 != null) goto L5;
        return 0;
    L5:
        Iterator<Map.Entry<String, String>> r52 = r5.entrySet().iterator();
    L7:
        if (r52.hasNext() == false) goto L14;
        Map.Entry<String, String> r2 = r52.next();
        if (r2.getKey() == null) goto L12;
        r0 = r0 + r3.length();
    L12:
        if (r2.getValue() == null) goto L7;
        r0 = r0 + r2.length();
        goto L7
    L14:
        return r0;
    }

    public static long estimateHeadersSizeInBytesList(Map<String, List<String>> r5) {
        long r0 = 0;
        if (r5 != null) goto L5;
        return 0;
    L5:
        Iterator<Map.Entry<String, List<String>>> r52 = r5.entrySet().iterator();
    L7:
        if (r52.hasNext() == false) goto L20;
        Map.Entry<String, List<String>> r2 = r52.next();
        if (r2.getKey() == null) goto L12;
        r0 = r0 + r3.length();
    L12:
        if (r2.getValue() == null) goto L7;
        Iterator<String> r22 = r2.getValue().iterator();
    L16:
        if (r22.hasNext() == false) goto L7;
        if (r22.next() == null) goto L16;
        r0 = r0 + r3.length();
        goto L16
    L20:
        return r0;
    }

    private void executeOnExecutor(Runnable r3, String r4) {
        ScopedSysTraceEvent r0 = ScopedSysTraceEvent.scoped("Cronet JavaUrlRequest#executeOnExecutor " + r4);
        this.mExecutor.execute(new JavaUrlRequest$$ExternalSyntheticLambda5(r4, r3));     // Catch: Throwable -> L7
        if (r0 == null) goto L18;
        r0.close();
        return;
    L18:
        return;
    L7:
        th = move-exception;
        if (r0 != null) goto L16;
    L13:
        throw th;
    L16:
        r0.close();     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        th.addSuppressed(th);
        goto L13
    }

    private void fireCloseUploadDataProvider() {
        if (this.mUploadDataProvider != null) goto L5;
        return;
    L5:
        if (this.mUploadProviderClosed.compareAndSet(false, true) == false) goto L14;
        Executor r0 = this.mUploadExecutor;     // Catch: RejectedExecutionException -> L8
        VersionSafeCallbacks$UploadDataProviderWrapper r1 = this.mUploadDataProvider;     // Catch: RejectedExecutionException -> L8
        Objects.requireNonNull(r1);     // Catch: RejectedExecutionException -> L8
        r0.execute(uploadErrorSetting(new JavaUrlRequest$$ExternalSyntheticLambda0(r1)));     // Catch: RejectedExecutionException -> L8
        return;
    L8:
        e = move-exception;
        Log.e(TAG, "Exception when closing uploadDataProvider", e);
        return;
    }

    private void fireDisconnect() {
        executeOnExecutor(new JavaUrlRequest$$ExternalSyntheticLambda1(this), "fireDisconnect");
    }

    private void fireGetHeaders() {
        this.mAdditionalStatusDetails = 13;
        executeOnExecutor(errorSetting(new JavaUrlRequest$$ExternalSyntheticLambda10(this)), "fireGetHeaders");
    }

    private void fireOpenConnection() {
        executeOnExecutor(errorSetting(new JavaUrlRequest$$ExternalSyntheticLambda4(this)), "fireOpenConnection");
    }

    private void fireRedirectReceived(String r2, UrlResponseInfo r3) {
        transitionStates(1, 2, new JavaUrlRequest$$ExternalSyntheticLambda13(this, r2, r3));
    }

    private Network getNetworkFromHandle(long r6) {
        Network[] r5 = ((ConnectivityManager) this.mEngine.getContext().getSystemService("connectivity")).getAllNetworks();
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        Network r2 = r5[r1];
        if (r2.getNetworkHandle() == r6) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r2;
    L8:
        return null;
    }

    private static boolean isValidHeaderName(String r4) {
        int r1 = 0;
    L4:
        if (r1 >= r4.length()) goto L23;
        char r2 = r4.charAt(r1);
        if (r2 == ',') goto L22;
        if (r2 == '/') goto L22;
        if (r2 == '{') goto L22;
        if (r2 == '}') goto L22;
        switch(r2) {
            case 39: goto L22;
            case 40: goto L22;
            case 41: goto L22;
            default: goto L14;
        };
    L14:
        switch(r2) {
            case 58: goto L22;
            case 59: goto L22;
            case 60: goto L22;
            case 61: goto L22;
            case 62: goto L22;
            case 63: goto L22;
            case 64: goto L22;
            default: goto L15;
        };
    L15:
        switch(r2) {
            case 91: goto L22;
            case 92: goto L22;
            case 93: goto L22;
            default: goto L17;
        };
    L17:
        if (Character.isISOControl(r2) == true) goto L22;
        if (Character.isWhitespace(r2) == true) goto L22;
        r1 = r1 + 1;
    L22:
        return false;
    L23:
        return true;
    }

    private /* synthetic */ void lambda$closeResponseChannel$14() {
        ReadableByteChannel r0 = this.mResponseChannel;
        if (r0 == null) goto L12;
        r0.close();     // Catch: IOException -> L6
    L8:
        this.mResponseChannel = null;
        return;
    L6:
        e = move-exception;
        e.printStackTrace();
        goto L8
    }

    private /* synthetic */ void lambda$enterUserErrorState$3() {
        this.mNonfinalUserCallbackExceptionCount++;
    }

    private /* synthetic */ void lambda$errorSetting$8(JavaUrlRequestUtils.CheckedRunnable r1) {
        r1.run();     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        enterCronetErrorState(th);
    }

    private /* synthetic */ void lambda$fireDisconnect$13() {
        OutputStreamDataSink r0 = this.mOutputStreamDataSink;
        if (r0 != null) goto L12;
    L8:
        HttpURLConnection r02 = this.mCurrentUrlConnection;
        if (r02 == null) goto L14;
        r02.disconnect();
        this.mCurrentUrlConnection = null;
        return;
    L14:
        return;
    L12:
        r0.closeOutputChannel();     // Catch: IOException -> L6
    L6:
        e = move-exception;
        Log.e(TAG, "Exception when closing OutputChannel", e);
        goto L8
    }

    private /* synthetic */ void lambda$fireGetHeaders$4() {
        if (this.mCurrentUrlConnection != null) goto L5;
        return;
    L5:
        ArrayList r0 = new ArrayList();
        String r9 = "http/1.1";
        int r2 = 0;
    L6:
        String r3 = this.mCurrentUrlConnection.getHeaderFieldKey(r2);
        if (r3 == null) goto L15;
        if ("X-Android-Selected-Transport".equalsIgnoreCase(r3) == false) goto L12;
        r9 = this.mCurrentUrlConnection.getHeaderField(r2);
    L12:
        if (r3.startsWith("X-Android") == true) goto L14;
        r0.add(new AbstractMap.SimpleEntry(r3, this.mCurrentUrlConnection.getHeaderField(r2)));
    L14:
        r2 = r2 + 1;
        goto L6
    L15:
        int r5 = this.mCurrentUrlConnection.getResponseCode();
        UrlResponseInfoImpl r32 = new UrlResponseInfoImpl(new ArrayList(this.mUrlChain), r5, this.mCurrentUrlConnection.getResponseMessage(), Collections.unmodifiableList(r0), false, r9, _UrlKt.FRAGMENT_ENCODE_SET, 0);
        if (r5 < 300) goto L22;
        if (r5 >= 400) goto L22;
        List<String> r02 = r32.getAllHeaders().get("location");
        if (r02 == null) goto L22;
        fireRedirectReceived(r02.get(0), r32);
        return;
    L22:
        this.mUrlResponseInfo = r32;
        fireCloseUploadDataProvider();
        HttpURLConnection r03 = this.mCurrentUrlConnection;
        if (r5 < 400) goto L30;
        InputStream r04 = r03.getErrorStream();
        if (r04 != null) goto L27;
        ReadableByteChannel r05 = null;
    L28:
        this.mResponseChannel = r05;
        this.mCallbackAsync.onResponseStarted();
        return;
    L27:
        r05 = InputStreamChannel.wrap(r04);
        goto L28
    L30:
        this.mResponseChannel = InputStreamChannel.wrap(r03.getInputStream());
        this.mCallbackAsync.onResponseStarted();
    }

    private /* synthetic */ void lambda$fireOpenConnection$7() {
        if (this.mState.get() != 8) goto L5;
        return;
    L5:
        URL r0 = new URL(this.mCurrentUrl);
        HttpURLConnection r1 = this.mCurrentUrlConnection;
        if (r1 == null) goto L8;
        r1.disconnect();
        this.mCurrentUrlConnection = null;
    L8:
        long r12 = this.mNetworkHandle;
        if (r12 != (-1)) goto L11;
        this.mCurrentUrlConnection = (HttpURLConnection) r0.openConnection();
    L14:
        boolean r13 = false;
        this.mCurrentUrlConnection.setInstanceFollowRedirects(false);
        if (this.mRequestHeaders.containsKey("User-Agent") == true) goto L17;
        this.mRequestHeaders.put("User-Agent", this.mUserAgent);
    L17:
        Iterator<Map.Entry<String, String>> r02 = this.mRequestHeaders.entrySet().iterator();
    L19:
        if (r02.hasNext() == false) goto L21;
        Map.Entry<String, String> r2 = r02.next();
        this.mCurrentUrlConnection.setRequestProperty(r2.getKey(), r2.getValue());
        goto L19
    L21:
        this.mCurrentUrlConnection.setRequestMethod(this.mInitialMethod);
        VersionSafeCallbacks$UploadDataProviderWrapper r8 = this.mUploadDataProvider;
        if (r8 == null) goto L28;
        OutputStreamDataSink r3 = new OutputStreamDataSink(this, this.mUploadExecutor, this.mExecutor, this.mCurrentUrlConnection, r8);
        this.mOutputStreamDataSink = r3;
        if (this.mUrlChain.size() != 1) goto L26;
        r13 = true;
    L26:
        r3.start(r13);
        return;
    L28:
        this.mAdditionalStatusDetails = 10;
        this.mCurrentUrlConnection.connect();
        fireGetHeaders();
        return;
    L11:
        Network r14 = getNetworkFromHandle(r12);
        if (r14 == null) goto L31;
        this.mCurrentUrlConnection = (HttpURLConnection) r14.openConnection(r0);
        goto L14
    L31:
        throw new NetworkExceptionImpl("Network bound to request not found", 9, -4);
    }

    private /* synthetic */ void lambda$fireRedirectReceived$5(UrlResponseInfo r2) {
        this.mCallbackAsync.onRedirectReceived(r2, this.mPendingRedirectUrl);
    }

    private /* synthetic */ void lambda$fireRedirectReceived$6(String r2, UrlResponseInfo r3) {
        String r22 = URI.create(this.mCurrentUrl).resolve(r2).toString();
        this.mPendingRedirectUrl = r22;
        this.mUrlChain.add(r22);
        transitionStates(2, 3, new JavaUrlRequest$$ExternalSyntheticLambda14(this, r3));
    }

    private /* synthetic */ void lambda$read$11(ByteBuffer r3) {
        ReadableByteChannel r0 = this.mResponseChannel;
        if (r0 == null) goto L5;
        this.mReadCount++;
        int r02 = r0.read(r3);
    L6:
        processReadResult(r02, r3);
        return;
    L5:
        r02 = -1;
        goto L6
    }

    private /* synthetic */ void lambda$read$12(JavaUrlRequestUtils.CheckedRunnable r2) {
        executeOnExecutor(errorSetting(r2), "read");
    }

    private /* synthetic */ void lambda$start$2() {
        this.mUrlChain.add(this.mCurrentUrl);
        fireOpenConnection();
    }

    private /* synthetic */ void lambda$uploadErrorSetting$10(JavaUrlRequestUtils.CheckedRunnable r1) {
        r1.run();     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        enterUploadErrorState(th);
    }

    private /* synthetic */ void lambda$userErrorSetting$9(JavaUrlRequestUtils.CheckedRunnable r1) {
        r1.run();     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        enterUserErrorState(th);
    }

    private void onFinalCallbackException(String r4, Exception r5) {
        Log.e(TAG, "Exception in " + r4 + " method", r5);
        this.mFinalUserCallbackThrew = true;
    }

    private static long parseContentLengthString(String r2) {
        return Long.parseLong(r2);
    L4:
        return 0;
    }

    private void processReadResult(int r2, ByteBuffer r3) {
        if (r2 == (-1)) goto L6;
        this.mCallbackAsync.onReadCompleted(this.mUrlResponseInfo, r3);
        return;
    L6:
        ReadableByteChannel r22 = this.mResponseChannel;
        if (r22 == null) goto L10;
        r22.close();
    L10:
        if (this.mState.compareAndSet(5, 7) == false) goto L13;
        fireDisconnect();
        this.mCallbackAsync.onSucceeded(this.mUrlResponseInfo);
        return;
    }

    private void setHeaders(ArrayList<Map.Entry<String, String>> r6) {
        int r0 = r6.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L14;
        Map.Entry<String, String> r2 = r6.get(r1);
        r1 = r1 + 1;
        Map.Entry<String, String> r22 = r2;
        if (isValidHeaderName(r22.getKey()) == false) goto L9;
        if (r22.getValue().contains("\r\n") == true) goto L9;
        this.mRequestHeaders.put(r22.getKey(), r22.getValue());
    L9:
        Buffer$$ExternalSyntheticBUOutline4.m("Invalid header with headername: ", r22.getKey());
        return;
    }

    private boolean setTerminalState(int r3) {
    L2:
        int r0 = this.mState.get();
        if (r0 == 0) goto L16;
        if (r0 == 6) goto L14;
        if (r0 == 7) goto L24;
        if (r0 == 8) goto L25;
        if (this.mState.compareAndSet(r0, r3) == false) goto L2;
        return true;
    L25:
        return false;
    L24:
        return false;
    L14:
        return false;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("Can't enter error state before start");
        return false;
    }

    private void transitionStates(int r2, int r3, Runnable r4) {
        if (this.mState.compareAndSet(r2, r3) == true) goto L11;
        int r1 = this.mState.get();
        if (r1 != 8) goto L7;
        return;
    L7:
        if (r1 == 6) goto L29;
        WebMessageCompat$$ExternalSyntheticBUOutline0.m("Invalid state transition - expected ", JavaUrlRequestUtils.stateToString(r2), " but was ", JavaUrlRequestUtils.stateToString(r1));
        return;
    L29:
        return;
    L11:
        ScopedSysTraceEvent r12 = ScopedSysTraceEvent.scoped("Cronet JavaUrlRequest#transitionStates " + JavaUrlRequestUtils.stateToString(r2) + " -> " + JavaUrlRequestUtils.stateToString(r3));
        r4.run();     // Catch: Throwable -> L16
        if (r12 == null) goto L28;
        r12.close();
        return;
    L28:
        return;
    L16:
        th = move-exception;
        if (r12 != null) goto L25;
    L22:
        throw th;
    L25:
        r12.close();     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        th.addSuppressed(th);
        goto L22
    }

    private Runnable uploadErrorSetting(JavaUrlRequestUtils.CheckedRunnable r2) {
        return new JavaUrlRequest$$ExternalSyntheticLambda9(this, r2);
    }

    private Runnable userErrorSetting(JavaUrlRequestUtils.CheckedRunnable r2) {
        return new JavaUrlRequest$$ExternalSyntheticLambda15(this, r2);
    }

    @Override // org.chromium.net.UrlRequest
    public void cancel() {
        int r0 = this.mState.getAndSet(8);
        if (r0 != 1) goto L5;
    L13:
        fireDisconnect();
        fireCloseUploadDataProvider();
        this.mCallbackAsync.onCanceled(this.mUrlResponseInfo);
        return;
    L5:
        if (r0 == 2) goto L13;
        if (r0 == 3) goto L13;
        if (r0 == 4) goto L13;
        if (r0 == 5) goto L13;
    }

    @Override // org.chromium.net.UrlRequest
    public void followRedirect() {
        transitionStates(3, 1, new AnonymousClass1(this));
    }

    @Override // org.chromium.net.UrlRequest
    public void read(ByteBuffer r3) {
        Preconditions.checkDirect(r3);
        Preconditions.checkHasRemaining(r3);
        transitionStates(4, 5, new JavaUrlRequest$$ExternalSyntheticLambda7(this, new JavaUrlRequest$$ExternalSyntheticLambda6(this, r3)));
    }

    @Override // org.chromium.net.UrlRequest
    public void start() {
        this.mAdditionalStatusDetails = 10;
        this.mEngine.incrementActiveRequestCount();
        transitionStates(0, 1, new JavaUrlRequest$$ExternalSyntheticLambda3(this));
    }
}
