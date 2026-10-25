package okhttp3.internal.connection;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Tags;
import okhttp3.internal.TagsKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal._UtilJvmKt$$ExternalSyntheticBUOutline1;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.concurrent.Lockable;
import okhttp3.internal.connection.RoutePlanner;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.url._UrlKt;
import okio.AsyncTimeout;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.Timeout;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000É\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005*\u0001\u0019\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0002uvB\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0018\u001a\u00020;H\u0016J'\u0010<\u001a\u0004\u0018\u0001H=\"\b\b\u0000\u0010=*\u00020\u001e2\f\u0010>\u001a\b\u0012\u0004\u0012\u0002H=0?H\u0016¢\u0006\u0002\u0010@J%\u0010<\u001a\u0004\u0018\u0001H=\"\u0004\b\u0000\u0010=2\u000e\u0010>\u001a\n\u0012\u0006\b\u0001\u0012\u0002H=0AH\u0016¢\u0006\u0002\u0010BJ3\u0010<\u001a\u0002H=\"\b\b\u0000\u0010=*\u00020\u001e2\f\u0010>\u001a\b\u0012\u0004\u0012\u0002H=0?2\f\u0010C\u001a\b\u0012\u0004\u0012\u0002H=0DH\u0016¢\u0006\u0002\u0010EJ3\u0010<\u001a\u0002H=\"\b\b\u0000\u0010=*\u00020\u001e2\f\u0010>\u001a\b\u0012\u0004\u0012\u0002H=0A2\f\u0010C\u001a\b\u0012\u0004\u0012\u0002H=0DH\u0016¢\u0006\u0002\u0010FJ\b\u0010G\u001a\u00020\u0001H\u0016J\b\u0010H\u001a\u00020\u0007H\u0016J\b\u0010I\u001a\u00020JH\u0016J\b\u0010K\u001a\u00020\tH\u0016J\b\u0010L\u001a\u00020MH\u0016J\u0010\u0010N\u001a\u00020J2\u0006\u0010O\u001a\u00020PH\u0016J\b\u0010Q\u001a\u00020\tH\u0016J\b\u0010R\u001a\u00020JH\u0002J\r\u0010S\u001a\u00020MH\u0000¢\u0006\u0002\bTJ\u001e\u0010U\u001a\u00020J2\u0006\u0010H\u001a\u00020\u00072\u0006\u0010V\u001a\u00020\t2\u0006\u0010W\u001a\u00020XJ\u0015\u0010Y\u001a\u00020'2\u0006\u0010W\u001a\u00020XH\u0000¢\u0006\u0002\bZJ\u000e\u0010[\u001a\u00020J2\u0006\u0010#\u001a\u00020\"JI\u0010\\\u001a\u0004\u0018\u00010]2\u0006\u00101\u001a\u00020'2\b\b\u0002\u0010^\u001a\u00020\t2\b\b\u0002\u0010_\u001a\u00020\t2\b\b\u0002\u0010`\u001a\u00020\t2\b\b\u0002\u0010a\u001a\u00020\t2\b\u0010b\u001a\u0004\u0018\u00010]H\u0000¢\u0006\u0002\bcJ\u0019\u0010d\u001a\u0004\u0018\u00010]2\b\u0010b\u001a\u0004\u0018\u00010]H\u0000¢\u0006\u0002\beJ\u0014\u0010f\u001a\u0004\u0018\u00010]2\b\u0010b\u001a\u0004\u0018\u00010]H\u0002J\u000f\u0010g\u001a\u0004\u0018\u00010hH\u0000¢\u0006\u0002\biJ\u0014\u0010j\u001a\u0004\u0018\u00010]2\b\u0010k\u001a\u0004\u0018\u00010]H\u0002J\u0006\u0010&\u001a\u00020JJ\u0006\u0010l\u001a\u00020JJ\u0015\u0010m\u001a\u00020J2\u0006\u0010n\u001a\u00020\tH\u0000¢\u0006\u0002\boJ\u0006\u0010p\u001a\u00020\tJ\b\u0010q\u001a\u00020rH\u0002J\r\u0010s\u001a\u00020rH\u0000¢\u0006\u0002\btR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u0015X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010#\u001a\u0004\u0018\u00010\"2\b\u0010!\u001a\u0004\u0018\u00010\"@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010(\u001a\u0004\u0018\u00010'2\b\u0010!\u001a\u0004\u0018\u00010'@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020403X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u001c\u00107\u001a\u0010\u0012\f\u0012\n :*\u0004\u0018\u0001090908X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006w"}, d2 = {"Lokhttp3/internal/connection/RealCall;", "Lokhttp3/Call;", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/concurrent/Lockable;", "client", "Lokhttp3/OkHttpClient;", "originalRequest", "Lokhttp3/Request;", "forWebSocket", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokhttp3/OkHttpClient;Lokhttp3/Request;Z)V", "getClient", "()Lokhttp3/OkHttpClient;", "getOriginalRequest", "()Lokhttp3/Request;", "getForWebSocket", "()Z", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "eventListener", "Lokhttp3/EventListener;", "getEventListener$okhttp", "()Lokhttp3/EventListener;", "timeout", "okhttp3/internal/connection/RealCall$timeout$1", "Lokhttp3/internal/connection/RealCall$timeout$1;", "executed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "callStackTrace", _UrlKt.FRAGMENT_ENCODE_SET, "exchangeFinder", "Lokhttp3/internal/connection/ExchangeFinder;", "value", "Lokhttp3/internal/connection/RealConnection;", "connection", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "timeoutEarlyExit", "Lokhttp3/internal/connection/Exchange;", "interceptorScopedExchange", "getInterceptorScopedExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "requestBodyOpen", "responseBodyOpen", "socketSinkOpen", "socketSourceOpen", "expectMoreExchanges", "canceled", "exchange", "plansToCancel", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "getPlansToCancel$okhttp", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "tags", "Ljava/util/concurrent/atomic/AtomicReference;", "Lokhttp3/internal/Tags;", "kotlin.jvm.PlatformType", "Lokio/Timeout;", "tag", "T", "type", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "computeIfAbsent", "Lkotlin/Function0;", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "(Ljava/lang/Class;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "clone", PluginsConstants.REQUEST, "cancel", _UrlKt.FRAGMENT_ENCODE_SET, "isCanceled", "execute", "Lokhttp3/Response;", "enqueue", "responseCallback", "Lokhttp3/Callback;", "isExecuted", "callStart", "getResponseWithInterceptorChain", "getResponseWithInterceptorChain$okhttp", "enterNetworkInterceptorExchange", "newRoutePlanner", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "initExchange", "initExchange$okhttp", "acquireConnectionNoEvents", "messageDone", "Ljava/io/IOException;", "requestDone", "responseDone", "socketSourceDone", "socketSinkDone", "e", "messageDone$okhttp", "noMoreExchanges", "noMoreExchanges$okhttp", "callDone", "releaseConnectionNoEvents", "Ljava/net/Socket;", "releaseConnectionNoEvents$okhttp", "timeoutExit", "cause", "upgradeToSocket", "exitNetworkInterceptorExchange", "closeExchange", "exitNetworkInterceptorExchange$okhttp", "retryAfterFailure", "toLoggableString", _UrlKt.FRAGMENT_ENCODE_SET, "redactedUrl", "redactedUrl$okhttp", "AsyncCall", "CallReference", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRealCall.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealCall.kt\nokhttp3/internal/connection/RealCall\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,612:1\n1#2:613\n63#3:614\n63#3:615\n63#3:616\n55#3,4:617\n63#3:621\n63#3:622\n49#3,4:623\n49#3,4:627\n63#3:631\n55#3,4:632\n63#3:643\n63#3:644\n360#4,7:636\n*S KotlinDebug\n*F\n+ 1 RealCall.kt\nokhttp3/internal/connection/RealCall\n*L\n257#1:614\n292#1:615\n303#1:616\n313#1:617,4\n340#1:621\n373#1:622\n401#1:623,4\n405#1:627,4\n407#1:631\n438#1:632,4\n479#1:643\n495#1:644\n441#1:636,7\n*E\n"})
/* loaded from: classes.dex */
public final class RealCall implements Call, Cloneable, Lockable {
    private Object callStackTrace;
    private volatile boolean canceled;
    private final OkHttpClient client;
    private RealConnection connection;
    private final RealConnectionPool connectionPool;
    private final EventListener eventListener;
    private volatile Exchange exchange;
    private ExchangeFinder exchangeFinder;
    private final AtomicBoolean executed;
    private boolean expectMoreExchanges;
    private final boolean forWebSocket;
    private Exchange interceptorScopedExchange;
    private final Request originalRequest;
    private final CopyOnWriteArrayList<RoutePlanner.Plan> plansToCancel;
    private boolean requestBodyOpen;
    private boolean responseBodyOpen;
    private boolean socketSinkOpen;
    private boolean socketSourceOpen;
    private final AtomicReference<Tags> tags;
    private final AnonymousClass1 timeout;
    private boolean timeoutEarlyExit;

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u0000R\u00020\u000eJ\u000e\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0019\u0010\u001d\u001a\u00020\f2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0000¢\u0006\u0002\b J\b\u0010!\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\""}, d2 = {"Lokhttp3/internal/connection/RealCall$AsyncCall;", "Ljava/lang/Runnable;", "responseCallback", "Lokhttp3/Callback;", "<init>", "(Lokhttp3/internal/connection/RealCall;Lokhttp3/Callback;)V", "value", "Ljava/util/concurrent/atomic/AtomicInteger;", "callsPerHost", "getCallsPerHost", "()Ljava/util/concurrent/atomic/AtomicInteger;", "reuseCallsPerHostFrom", _UrlKt.FRAGMENT_ENCODE_SET, "other", "Lokhttp3/internal/connection/RealCall;", "host", _UrlKt.FRAGMENT_ENCODE_SET, "getHost", "()Ljava/lang/String;", PluginsConstants.REQUEST, "Lokhttp3/Request;", "getRequest", "()Lokhttp3/Request;", "call", "getCall", "()Lokhttp3/internal/connection/RealCall;", "executeOn", "executorService", "Ljava/util/concurrent/ExecutorService;", "failRejected", "e", "Ljava/util/concurrent/RejectedExecutionException;", "failRejected$okhttp", "run", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nRealCall.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealCall.kt\nokhttp3/internal/connection/RealCall$AsyncCall\n+ 2 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n*L\n1#1,612:1\n227#2,9:613\n*S KotlinDebug\n*F\n+ 1 RealCall.kt\nokhttp3/internal/connection/RealCall$AsyncCall\n*L\n570#1:613,9\n*E\n"})
    public final class AsyncCall implements Runnable {
        private volatile AtomicInteger callsPerHost;
        private final Callback responseCallback;
        final /* synthetic */ RealCall this$0;

        public AsyncCall(RealCall r1, Callback r2) {
            this.this$0 = r1;
            this.responseCallback = r2;
            this.callsPerHost = new AtomicInteger(0);
        }

        public static /* synthetic */ void failRejected$okhttp$default(AsyncCall r0, RejectedExecutionException r1, int r2, Object r3) {
            if ((r2 & 1) == 0) goto L5;
            r1 = null;
        L5:
            r0.failRejected$okhttp(r1);
        }

        public final void executeOn(ExecutorService r2) {
            _UtilJvmKt.assertLockNotHeld(this.this$0.getClient().dispatcher());
            r2.execute(this);     // Catch: Throwable -> L5 RejectedExecutionException -> L7
            return;
        L5:
            th = move-exception;
            this.this$0.getClient().dispatcher().finished$okhttp(this);
            throw th;
        L7:
            e = move-exception;
            failRejected$okhttp(e);     // Catch: Throwable -> L5
            this.this$0.getClient().dispatcher().finished$okhttp(this);
        }

        public final void failRejected$okhttp(RejectedExecutionException r3) {
            InterruptedIOException r0 = new InterruptedIOException("executor rejected");
            r0.initCause(r3);
            this.this$0.noMoreExchanges$okhttp(r0);
            this.responseCallback.onFailure(this.this$0, r0);
        }

        public final RealCall getCall() {
            return this.this$0;
        }

        public final AtomicInteger getCallsPerHost() {
            return this.callsPerHost;
        }

        public final String getHost() {
            return this.this$0.getOriginalRequest().url().host();
        }

        public final Request getRequest() {
            return this.this$0.getOriginalRequest();
        }

        public final void reuseCallsPerHostFrom(AsyncCall r1) {
            this.callsPerHost = r1.callsPerHost;
        }

        @Override // java.lang.Runnable
        public void run() {
            String r2 = "OkHttp " + this.this$0.redactedUrl$okhttp();
            RealCall r3 = this.this$0;
            Thread r4 = Thread.currentThread();
            String r5 = r4.getName();
            r4.setName(r2);
            RealCall.access$getTimeout$p(r3).enter();     // Catch: Throwable -> L11
            boolean r22 = false;
        L43:
            this.responseCallback.onResponse(r3, r3.getResponseWithInterceptorChain$okhttp());     // Catch: Throwable -> L13 IOException -> L15
            OkHttpClient r0 = r3.getClient();     // Catch: Throwable -> L11
        L9:
            r0.dispatcher().finished$okhttp(this);     // Catch: Throwable -> L11
            r4.setName(r5);
            return;
        L15:
            e = e;
            r22 = true;
        L30:
            if (r22 == false) goto L32;
            Platform.Companion.get().log("Callback failure for " + RealCall.access$toLoggableString(r3), 4, e);     // Catch: Throwable -> L21
        L33:
            r0 = r3.getClient();     // Catch: Throwable -> L11
            goto L9
        L32:
            this.responseCallback.onFailure(r3, e);     // Catch: Throwable -> L21
        L13:
            th = th;
            r22 = true;
        L18:
            r3.cancel();     // Catch: Throwable -> L21
            if (r22 == true) goto L24;
            IOException r23 = new IOException("canceled due to " + th);     // Catch: Throwable -> L21
            r23.initCause(th);     // Catch: Throwable -> L21
            this.responseCallback.onFailure(r3, r23);     // Catch: Throwable -> L21
        L24:
            if ((th instanceof InterruptedException) == false) goto L28;
            Thread.currentThread().interrupt();     // Catch: Throwable -> L21
            r0 = r3.getClient();     // Catch: Throwable -> L11
            goto L9
        L28:
            throw th;     // Catch: Throwable -> L21
        L21:
            th = move-exception;
            r3.getClient().dispatcher().finished$okhttp(this);     // Catch: Throwable -> L11
            throw th;     // Catch: Throwable -> L11
        L29:
            e = e;
        L17:
            th = th;
        L11:
            th = move-exception;
            r4.setName(r5);
            throw th;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lokhttp3/internal/connection/RealCall$CallReference;", "Ljava/lang/ref/WeakReference;", "Lokhttp3/internal/connection/RealCall;", "referent", "callStackTrace", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokhttp3/internal/connection/RealCall;Ljava/lang/Object;)V", "getCallStackTrace", "()Ljava/lang/Object;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CallReference extends WeakReference<RealCall> {
        private final Object callStackTrace;

        public CallReference(RealCall r1, Object r2) {
            super(r1);
            this.callStackTrace = r2;
        }

        public final Object getCallStackTrace() {
            return this.callStackTrace;
        }
    }

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0014¨\u0006\u0004"}, d2 = {"okhttp3/internal/connection/RealCall$timeout$1", "Lokio/AsyncTimeout;", "timedOut", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: okhttp3.internal.connection.RealCall$timeout$1, reason: invalid class name */
    public static final class AnonymousClass1 extends AsyncTimeout {
        final /* synthetic */ RealCall this$0;

        public AnonymousClass1(RealCall r1) {
            this.this$0 = r1;
        }

        @Override // okio.AsyncTimeout
        public void timedOut() {
            this.this$0.cancel();
        }
    }

    public RealCall(OkHttpClient r3, Request r4, boolean r5) {
        this.client = r3;
        this.originalRequest = r4;
        this.forWebSocket = r5;
        this.connectionPool = r3.connectionPool().getDelegate$okhttp();
        this.eventListener = r3.eventListenerFactory().create(this);
        AnonymousClass1 r52 = new AnonymousClass1(this);
        r52.timeout(r3.callTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.timeout = r52;
        this.executed = new AtomicBoolean();
        this.expectMoreExchanges = true;
        this.plansToCancel = new CopyOnWriteArrayList();
        this.tags = new AtomicReference(r4.getTags$okhttp());
    }

    public static final /* synthetic */ AnonymousClass1 access$getTimeout$p(RealCall r0) {
        return r0.timeout;
    }

    public static final /* synthetic */ String access$toLoggableString(RealCall r0) {
        return r0.toLoggableString();
    }

    private final IOException callDone(IOException r5) {
        boolean r0 = _UtilJvmKt.assertionsEnabled;
        if (r0 == true) goto L5;
    L9:
        RealConnection r2 = this.connection;
        if (r2 == null) goto L34;
        if (r0 == true) goto L13;
    L17:
        monitor-enter(r2);
        Socket r02 = releaseConnectionNoEvents$okhttp();     // Catch: Throwable -> L31
        monitor-exit(r2);
        if (this.connection != null) goto L27;
        if (r02 == null) goto L24;
        _UtilJvmKt.closeQuietly(r02);
    L24:
        this.eventListener.connectionReleased(this, r2);
        r2.getConnectionListener$okhttp().connectionReleased(r2, this);
        if (r02 == null) goto L34;
        r2.getConnectionListener$okhttp().connectionClosed(r2);
        goto L34
    L27:
        if (r02 == null) goto L34;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    L31:
        th = move-exception;
        throw th;
    L13:
        if (Thread.holdsLock(r2) == false) goto L17;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", r2);
        return null;
    L34:
        IOException r03 = timeoutExit(r5);
        EventListener r1 = this.eventListener;
        if (r5 == null) goto L38;
        r1.callFailed(this, r03);
        return r03;
    L38:
        r1.callEnd(this);
        return r03;
    L5:
        if (Thread.holdsLock(this) == false) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", this);
        return null;
    }

    private final void callStart() {
        this.callStackTrace = Platform.Companion.get().getStackTraceForCloseable("response.body().close()");
        this.eventListener.callStart(this);
    }

    public static /* synthetic */ IOException messageDone$okhttp$default(RealCall r1, Exchange r2, boolean r3, boolean r4, boolean r5, boolean r6, IOException r7, int r8, Object r9) {
        if ((r8 & 2) == 0) goto L6;
        r3 = false;
    L6:
        if ((r8 & 4) == 0) goto L9;
        r4 = false;
    L9:
        if ((r8 & 8) == 0) goto L12;
        r5 = false;
    L12:
        if ((r8 & 16) == 0) goto L15;
        r6 = false;
    L15:
        return r1.messageDone$okhttp(r2, r3, r4, r5, r6, r7);
    }

    private final IOException timeoutExit(IOException r2) {
        if (this.timeoutEarlyExit == false) goto L6;
    L7:
        return r2;
    L6:
        if (this.timeout.exit() == false) goto L7;
        InterruptedIOException r1 = new InterruptedIOException("timeout");
        if (r2 == null) goto L11;
        r1.initCause(r2);
    L11:
        return r1;
    }

    private final String toLoggableString() {
        StringBuilder r0 = new StringBuilder();
        if (isCanceled() == false) goto L5;
        String r1 = "canceled ";
    L6:
        r0.append(r1);
        if (this.forWebSocket == false) goto L9;
        String r12 = "web socket";
    L10:
        r0.append(r12);
        r0.append(" to ");
        r0.append(redactedUrl$okhttp());
        return r0.toString();
    L9:
        r12 = "call";
        goto L10
    L5:
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    public final void acquireConnectionNoEvents(RealConnection r3) {
        if (_UtilJvmKt.assertionsEnabled == false) goto L10;
        if (Thread.holdsLock(r3) == true) goto L10;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", r3);
        return;
    L10:
        if (this.connection != null) goto L13;
        this.connection = r3;
        r3.getCalls().add(new CallReference(this, this.callStackTrace));
        return;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
    }

    @Override // okhttp3.Call
    public void cancel() {
        if (this.canceled == false) goto L5;
        return;
    L5:
        this.canceled = true;
        Exchange r0 = this.exchange;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        Iterator<RoutePlanner.Plan> r02 = this.plansToCancel.iterator();
    L10:
        if (r02.hasNext() == false) goto L12;
        r02.next().mo3746cancel();
        goto L10
    L12:
        this.eventListener.canceled(this);
    }

    public /* bridge */ /* synthetic */ Object clone() {
        return mo3749clone();
    }

    @Override // okhttp3.Call
    /* renamed from: clone, reason: collision with other method in class */
    public Call mo3749clone() {
        return new RealCall(this.client, this.originalRequest, this.forWebSocket);
    }

    @Override // okhttp3.Call
    public void enqueue(Callback r4) {
        if (this.executed.compareAndSet(false, true) == false) goto L6;
        callStart();
        this.client.dispatcher().enqueue$okhttp(new AsyncCall(this, r4));
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Already Executed");
    }

    public final void enterNetworkInterceptorExchange(Request r15, boolean r16, RealInterceptorChain r17) {
        if (this.interceptorScopedExchange != null) goto L30;
        monitor-enter(this);
    L22:
        th = move-exception;
        throw th;
    L6:
        if (this.responseBodyOpen == true) goto L27;
        if (this.requestBodyOpen == true) goto L25;
        if (this.socketSourceOpen == true) goto L25;
        if (this.socketSinkOpen == true) goto L25;
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L22
        monitor-exit(this);
        if (r16 == false) goto L33;
        RealRoutePlanner r02 = new RealRoutePlanner(this.client.getTaskRunner$okhttp(), this.connectionPool, this.client.readTimeoutMillis(), this.client.writeTimeoutMillis(), r17.getConnectTimeoutMillis$okhttp(), r17.getReadTimeoutMillis$okhttp(), this.client.pingIntervalMillis(), this.client.retryOnConnectionFailure(), this.client.fastFallback(), this.client.address(r15.url()), this.client.getRouteDatabase$okhttp(), this, r15);
        if (this.client.fastFallback() == false) goto L19;
        ExchangeFinder r1 = new FastFallbackExchangeFinder(r02, this.client.getTaskRunner$okhttp());
    L20:
        this.exchangeFinder = r1;
        return;
    L19:
        r1 = new SequentialExchangeFinder(r02);
        goto L20
    L33:
        return;
    L25:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L22
    L27:
        throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()");     // Catch: Throwable -> L22
    L30:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
    }

    @Override // okhttp3.Call
    public Response execute() {
        if (this.executed.compareAndSet(false, true) == false) goto L11;
        this.timeout.enter();
        callStart();
        this.client.dispatcher().executed$okhttp(this);     // Catch: Throwable -> L8
        Response r0 = getResponseWithInterceptorChain$okhttp();     // Catch: Throwable -> L8
        this.client.dispatcher().finished$okhttp(this);
        return r0;
    L8:
        th = move-exception;
        this.client.dispatcher().finished$okhttp(this);
        throw th;
    L11:
        Segment$$ExternalSyntheticBUOutline1.m("Already Executed");
        return null;
    }

    public final void exitNetworkInterceptorExchange$okhttp(boolean r2) {
        monitor-enter(this);
    L13:
        th = move-exception;
        throw th;
    L4:
        if (this.expectMoreExchanges == false) goto L16;
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(this);
        if (r2 == false) goto L11;
        Exchange r22 = this.exchange;
        if (r22 == null) goto L11;
        r22.detachWithViolence();
    L11:
        this.interceptorScopedExchange = null;
        return;
    L16:
        throw new IllegalStateException("released");     // Catch: Throwable -> L13
    }

    public final OkHttpClient getClient() {
        return this.client;
    }

    public final RealConnection getConnection() {
        return this.connection;
    }

    public final EventListener getEventListener$okhttp() {
        return this.eventListener;
    }

    public final boolean getForWebSocket() {
        return this.forWebSocket;
    }

    public final Exchange getInterceptorScopedExchange$okhttp() {
        return this.interceptorScopedExchange;
    }

    public final Request getOriginalRequest() {
        return this.originalRequest;
    }

    public final CopyOnWriteArrayList<RoutePlanner.Plan> getPlansToCancel$okhttp() {
        return this.plansToCancel;
    }

    public final Response getResponseWithInterceptorChain$okhttp() {
        ArrayList r2 = new ArrayList();
        CollectionsKt.addAll(r2, this.client.interceptors());
        r2.add(new RetryAndFollowUpInterceptor(this.client));
        r2.add(new BridgeInterceptor(this.client.cookieJar()));
        r2.add(new CacheInterceptor(this.client.cache()));
        r2.add(ConnectInterceptor.INSTANCE);
        if (this.forWebSocket == true) goto L5;
        CollectionsKt.addAll(r2, this.client.networkInterceptors());
    L5:
        r2.add(CallServerInterceptor.INSTANCE);
        RealInterceptorChain r0 = new RealInterceptorChain(this, r2, 0, null, this.originalRequest, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis());
        boolean r22 = false;
        Response r02 = r0.proceed(this.originalRequest);     // Catch: Throwable -> L12 IOException -> L14
        if (isCanceled() == true) goto L10;
        noMoreExchanges$okhttp(null);
        return r02;
    L10:
        _UtilCommonKt.closeQuietly(r02);     // Catch: Throwable -> L12 IOException -> L14
        throw new IOException("Canceled");     // Catch: Throwable -> L12 IOException -> L14
    L14:
        e = move-exception;
        r22 = true;
        throw noMoreExchanges$okhttp(e);     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        if (r22 == true) goto L20;
        noMoreExchanges$okhttp(null);
    L20:
        throw th;
    }

    public final Exchange initExchange$okhttp(RealInterceptorChain r4) {
        monitor-enter(this);
    L28:
        th = move-exception;
        throw th;
    L4:
        if (this.expectMoreExchanges == false) goto L33;
        if (this.responseBodyOpen == true) goto L31;
        if (this.requestBodyOpen == true) goto L31;
        if (this.socketSourceOpen == true) goto L31;
        if (this.socketSinkOpen == true) goto L31;
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L28
        monitor-exit(this);
        ExchangeFinder r02 = this.exchangeFinder;
        ExchangeCodec r42 = r02.find().newCodec$okhttp(this.client, r4);
        Exchange r1 = new Exchange(this, this.eventListener, r02, r42);
        this.interceptorScopedExchange = r1;
        this.exchange = r1;
        monitor-enter(this);
        this.requestBodyOpen = true;     // Catch: Throwable -> L25
        this.responseBodyOpen = true;     // Catch: Throwable -> L25
        monitor-exit(this);
        if (this.canceled == true) goto L23;
        return r1;
    L23:
        Model$$ExternalSyntheticBUOutline0.m("Canceled");
        return null;
    L25:
        th = move-exception;
        throw th;
    L31:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L28
    L33:
        throw new IllegalStateException("released");     // Catch: Throwable -> L28
    }

    @Override // okhttp3.Call
    public boolean isCanceled() {
        return this.canceled;
    }

    @Override // okhttp3.Call
    public boolean isExecuted() {
        return this.executed.get();
    }

    public final IOException messageDone$okhttp(Exchange r3, boolean r4, boolean r5, boolean r6, boolean r7, IOException r8) {
        if (Intrinsics.areEqual(r3, this.exchange) == false) goto L55;
        monitor-enter(this);
        boolean r32 = false;
        if (r4 == true) goto L58;
    L13:
        if (r5 == true) goto L15;
    L16:
        if (r7 == true) goto L18;
    L19:
        if (r6 == true) goto L21;
    L45:
        boolean r42 = false;
    L46:
        Unit r52 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(this);
        if (r32 == false) goto L52;
        this.exchange = null;
        RealConnection r33 = this.connection;
        if (r33 == null) goto L52;
        r33.incrementSuccessCount$okhttp();
    L52:
        if (r42 == false) goto L55;
        return callDone(r8);
    L11:
        th = move-exception;
        throw th;
    L21:
        if (this.socketSourceOpen == false) goto L45;
    L22:
        if (r4 == false) goto L24;
        this.requestBodyOpen = false;     // Catch: Throwable -> L11
    L24:
        if (r5 == false) goto L26;
        this.responseBodyOpen = false;     // Catch: Throwable -> L11
    L26:
        if (r7 == false) goto L28;
        this.socketSinkOpen = false;     // Catch: Throwable -> L11
    L28:
        if (r6 == false) goto L31;
        this.socketSourceOpen = false;     // Catch: Throwable -> L11
    L31:
        if (this.requestBodyOpen == false) goto L33;
    L39:
        boolean r43 = false;
    L40:
        if (r43 == true) goto L42;
    L44:
        boolean r1 = r43;
        r42 = r32;
        r32 = r1;
        goto L46
    L42:
        if (this.expectMoreExchanges == true) goto L44;
        r32 = true;
        goto L44
    L33:
        if (this.responseBodyOpen == true) goto L39;
        if (this.socketSinkOpen == true) goto L39;
        if (this.socketSourceOpen == true) goto L39;
        r43 = true;
        goto L40
    L18:
        if (this.socketSinkOpen == true) goto L22;
    L15:
        if (this.responseBodyOpen == true) goto L22;
    L58:
        if (this.requestBodyOpen == true) goto L22;
    L55:
        return r8;
    }

    public final IOException noMoreExchanges$okhttp(IOException r3) {
        monitor-enter(this);
        boolean r1 = false;
        if (this.expectMoreExchanges == false) goto L16;
        this.expectMoreExchanges = false;     // Catch: Throwable -> L14
        if (this.requestBodyOpen == true) goto L16;
        if (this.responseBodyOpen == true) goto L16;
        if (this.socketSinkOpen == true) goto L16;
        if (this.socketSourceOpen == true) goto L16;
        r1 = true;
    L16:
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(this);
        if (r1 == true) goto L20;
        return r3;
    L20:
        return callDone(r3);
    L14:
        th = move-exception;
        throw th;
    }

    public final String redactedUrl$okhttp() {
        return this.originalRequest.url().redact();
    }

    public final Socket releaseConnectionNoEvents$okhttp() {
        RealConnection r0 = this.connection;
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        List<Reference<RealCall>> r1 = r0.getCalls();
        Iterator<Reference<RealCall>> r3 = r1.iterator();
        int r4 = 0;
    L11:
        if (r3.hasNext() == false) goto L16;
        if (Intrinsics.areEqual(r3.next().get(), this) == true) goto L17;
        r4 = r4 + 1;
    L17:
        if (r4 == (-1)) goto L25;
        r1.remove(r4);
        this.connection = null;
        if (r1.isEmpty() == false) goto L24;
        r0.setIdleAtNs(System.nanoTime());
        if (this.connectionPool.connectionBecameIdle(r0) == false) goto L24;
        return r0.socket();
    L24:
        return null;
    L25:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    L16:
        r4 = -1;
        goto L17
    L5:
        if (Thread.holdsLock(r0) == true) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", r0);
        return null;
    }

    @Override // okhttp3.Call
    public Request request() {
        return this.originalRequest;
    }

    public final boolean retryAfterFailure() {
        Exchange r0 = this.exchange;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getHasFailure$okhttp() != true) goto L15;
        RoutePlanner r02 = this.exchangeFinder.getRoutePlanner();
        Exchange r2 = this.exchange;
        if (r2 == null) goto L9;
        RealConnection r22 = r2.getConnection$okhttp();
    L11:
        if (r02.hasNext(r22) == false) goto L16;
        return true;
    L16:
        return false;
    L9:
        r22 = null;
        goto L11
    L15:
        return false;
    }

    @Override // okhttp3.Call
    public <T> T tag(Class<? extends T> r1) {
        return (T) tag(JvmClassMappingKt.getKotlinClass(r1));
    }

    @Override // okhttp3.Call
    public <T> T tag(Class<T> r1, Function0<? extends T> r2) {
        return (T) TagsKt.computeIfAbsent(this.tags, JvmClassMappingKt.getKotlinClass(r1), r2);
    }

    @Override // okhttp3.Call
    public <T> T tag(KClass<T> r2) {
        return (T) JvmClassMappingKt.getJavaClass(r2).cast(this.tags.get().get(r2));
    }

    @Override // okhttp3.Call
    public <T> T tag(KClass<T> r1, Function0<? extends T> r2) {
        return (T) TagsKt.computeIfAbsent(this.tags, r1, r2);
    }

    @Override // okhttp3.Call
    public Timeout timeout() {
        return this.timeout;
    }

    public final void timeoutEarlyExit() {
        if (this.timeoutEarlyExit == true) goto L6;
        this.timeoutEarlyExit = true;
        this.timeout.exit();
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
    }

    public final void upgradeToSocket() {
        timeoutEarlyExit();
        monitor-enter(this);
    L17:
        th = move-exception;
        throw th;
    L5:
        if (this.exchange == null) goto L26;
        if (this.socketSinkOpen == true) goto L24;
        if (this.socketSourceOpen == true) goto L24;
        if (this.requestBodyOpen == true) goto L22;
        if (this.responseBodyOpen == false) goto L20;
        this.responseBodyOpen = false;     // Catch: Throwable -> L17
        this.socketSinkOpen = true;     // Catch: Throwable -> L17
        this.socketSourceOpen = true;     // Catch: Throwable -> L17
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L17
        monitor-exit(this);
        return;
    L20:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L17
    L22:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L17
    L24:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L17
    L26:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L17
    }
}
