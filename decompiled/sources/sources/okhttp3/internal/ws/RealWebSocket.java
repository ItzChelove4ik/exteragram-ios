package okhttp3.internal.ws;

import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal._UtilJvmKt$$ExternalSyntheticBUOutline1;
import okhttp3.internal.concurrent.Lockable;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.BufferedSocket;
import okhttp3.internal.connection.BufferedSocketKt;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.url._UrlKt;
import okhttp3.internal.ws.WebSocketReader;
import okio.ByteString;
import okio.Options$Companion$$ExternalSyntheticBUOutline0;
import okio.Sink;
import okio.Socket;
import okio.Source;

@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 f2\u00020\u00012\u00020\u00022\u00020\u0003:\u0004cdefBI\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\b\u00109\u001a\u00020\u0007H\u0016J\b\u0010.\u001a\u00020\rH\u0016J\b\u0010:\u001a\u00020;H\u0016J\u000e\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020>J\f\u0010?\u001a\u000200*\u00020\u000fH\u0002J\u0015\u0010@\u001a\u00020(2\u0006\u0010A\u001a\u00020BH\u0000¢\u0006\u0002\bCJ\u001e\u0010D\u001a\u00020;2\u0006\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020E2\u0006\u0010=\u001a\u000200J\u000e\u0010F\u001a\u00020;2\u0006\u0010A\u001a\u00020BJ\u0006\u0010G\u001a\u000200J\u0006\u0010H\u001a\u00020;J\u0006\u0010I\u001a\u00020;J\u0006\u00105\u001a\u000202J\u0006\u00106\u001a\u000202J\u0006\u00107\u001a\u000202J\u0010\u0010J\u001a\u00020;2\u0006\u0010K\u001a\u00020\u0017H\u0016J\u0010\u0010J\u001a\u00020;2\u0006\u0010L\u001a\u00020+H\u0016J\u0010\u0010M\u001a\u00020;2\u0006\u0010N\u001a\u00020+H\u0016J\u0010\u0010O\u001a\u00020;2\u0006\u0010N\u001a\u00020+H\u0016J\u0018\u0010P\u001a\u00020;2\u0006\u0010Q\u001a\u0002022\u0006\u0010R\u001a\u00020\u0017H\u0016J\u0010\u0010S\u001a\u0002002\u0006\u0010K\u001a\u00020\u0017H\u0016J\u0010\u0010S\u001a\u0002002\u0006\u0010L\u001a\u00020+H\u0016J\u0018\u0010S\u001a\u0002002\u0006\u0010T\u001a\u00020+2\u0006\u0010U\u001a\u000202H\u0002J\u000e\u0010V\u001a\u0002002\u0006\u0010N\u001a\u00020+J\u001a\u0010W\u001a\u0002002\u0006\u0010Q\u001a\u0002022\b\u0010R\u001a\u0004\u0018\u00010\u0017H\u0016J \u0010W\u001a\u0002002\u0006\u0010Q\u001a\u0002022\b\u0010R\u001a\u0004\u0018\u00010\u00172\u0006\u0010X\u001a\u00020\rJ\b\u0010Y\u001a\u00020;H\u0002J\r\u0010Z\u001a\u000200H\u0000¢\u0006\u0002\b[J\r\u0010\\\u001a\u00020;H\u0000¢\u0006\u0002\b]J(\u0010^\u001a\u00020;2\n\u0010_\u001a\u00060`j\u0002`a2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\b\b\u0002\u0010b\u001a\u000200R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020-0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006g"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket;", "Lokhttp3/WebSocket;", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "Lokhttp3/internal/concurrent/Lockable;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "originalRequest", "Lokhttp3/Request;", "listener", "Lokhttp3/WebSocketListener;", "random", "Ljava/util/Random;", "pingIntervalMillis", _UrlKt.FRAGMENT_ENCODE_SET, "extensions", "Lokhttp3/internal/ws/WebSocketExtensions;", "minimumDeflateSize", "webSocketCloseTimeout", "<init>", "(Lokhttp3/internal/concurrent/TaskRunner;Lokhttp3/Request;Lokhttp3/WebSocketListener;Ljava/util/Random;JLokhttp3/internal/ws/WebSocketExtensions;JJ)V", "getListener$okhttp", "()Lokhttp3/WebSocketListener;", PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "call", "Lokhttp3/Call;", "getCall$okhttp", "()Lokhttp3/Call;", "setCall$okhttp", "(Lokhttp3/Call;)V", "writerTask", "Lokhttp3/internal/concurrent/Task;", "reader", "Lokhttp3/internal/ws/WebSocketReader;", "writer", "Lokhttp3/internal/ws/WebSocketWriter;", "taskQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "name", "socket", "Lokio/Socket;", "pongQueue", "Ljava/util/ArrayDeque;", "Lokio/ByteString;", "messageAndCloseQueue", _UrlKt.FRAGMENT_ENCODE_SET, "queueSize", "enqueuedClose", _UrlKt.FRAGMENT_ENCODE_SET, "receivedCloseCode", _UrlKt.FRAGMENT_ENCODE_SET, "receivedCloseReason", "failed", "sentPingCount", "receivedPingCount", "receivedPongCount", "awaitingPong", PluginsConstants.REQUEST, "cancel", _UrlKt.FRAGMENT_ENCODE_SET, "connect", "client", "Lokhttp3/OkHttpClient;", "isValid", "checkUpgradeSuccess", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "checkUpgradeSuccess$okhttp", "initReaderAndWriter", "Lokhttp3/internal/connection/BufferedSocket;", "loopReader", "processNextFrame", "finishReader", "tearDown", "onReadMessage", "text", "bytes", "onReadPing", "payload", "onReadPong", "onReadClose", "code", "reason", "send", "data", "formatOpcode", "pong", "close", "cancelAfterCloseMillis", "runWriter", "writeOneFrame", "writeOneFrame$okhttp", "writePingFrame", "writePingFrame$okhttp", "failWebSocket", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "isWriter", "Message", "Close", "WriterTask", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRealWebSocket.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealWebSocket.kt\nokhttp3/internal/ws/RealWebSocket\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n*L\n1#1,703:1\n1#2:704\n55#3,4:705\n*S KotlinDebug\n*F\n+ 1 RealWebSocket.kt\nokhttp3/internal/ws/RealWebSocket\n*L\n499#1:705,4\n*E\n"})
/* loaded from: classes5.dex */
public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback, Lockable {
    public static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    public static final Companion Companion = null;
    public static final long DEFAULT_MINIMUM_DEFLATE_SIZE = 1024;
    private static final long MAX_QUEUE_SIZE = 16777216;
    private static final List<Protocol> ONLY_HTTP1 = null;
    private boolean awaitingPong;
    private Call call;
    private boolean enqueuedClose;
    private WebSocketExtensions extensions;
    private boolean failed;
    private final String key;
    private final WebSocketListener listener;
    private final ArrayDeque<Object> messageAndCloseQueue;
    private long minimumDeflateSize;
    private String name;
    private final Request originalRequest;
    private final long pingIntervalMillis;
    private final ArrayDeque<ByteString> pongQueue;
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Socket socket;
    private TaskQueue taskQueue;
    private final long webSocketCloseTimeout;
    private WebSocketWriter writer;
    private Task writerTask;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Close;", _UrlKt.FRAGMENT_ENCODE_SET, "code", _UrlKt.FRAGMENT_ENCODE_SET, "reason", "Lokio/ByteString;", "cancelAfterCloseMillis", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(ILokio/ByteString;J)V", "getCode", "()I", "getReason", "()Lokio/ByteString;", "getCancelAfterCloseMillis", "()J", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Close {
        private final long cancelAfterCloseMillis;
        private final int code;
        private final ByteString reason;

        public Close(int r1, ByteString r2, long r3) {
            this.code = r1;
            this.reason = r2;
            this.cancelAfterCloseMillis = r3;
        }

        public final long getCancelAfterCloseMillis() {
            return this.cancelAfterCloseMillis;
        }

        public final int getCode() {
            return this.code;
        }

        public final ByteString getReason() {
            return this.reason;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "ONLY_HTTP1", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Protocol;", "MAX_QUEUE_SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "CANCEL_AFTER_CLOSE_MILLIS", "DEFAULT_MINIMUM_DEFLATE_SIZE", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Message;", _UrlKt.FRAGMENT_ENCODE_SET, "formatOpcode", _UrlKt.FRAGMENT_ENCODE_SET, "data", "Lokio/ByteString;", "<init>", "(ILokio/ByteString;)V", "getFormatOpcode", "()I", "getData", "()Lokio/ByteString;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Message {
        private final ByteString data;
        private final int formatOpcode;

        public Message(int r1, ByteString r2) {
            this.formatOpcode = r1;
            this.data = r2;
        }

        public final ByteString getData() {
            return this.data;
        }

        public final int getFormatOpcode() {
            return this.formatOpcode;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$WriterTask;", "Lokhttp3/internal/concurrent/Task;", "<init>", "(Lokhttp3/internal/ws/RealWebSocket;)V", "runOnce", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class WriterTask extends Task {
        final /* synthetic */ RealWebSocket this$0;

        public WriterTask(RealWebSocket r4) {
            this.this$0 = r4;
            super(RealWebSocket.access$getName$p(r4) + " writer", false, 2, null);
        }

        @Override // okhttp3.internal.concurrent.Task
        public long runOnce() {
            if (this.this$0.writeOneFrame$okhttp() == false) goto L12;
            return 0;
        L12:
            return -1;
        L6:
            e = move-exception;
            RealWebSocket.failWebSocket$default(this.this$0, e, null, true, 2, null);
            return -1;
        }
    }

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"okhttp3/internal/ws/RealWebSocket$connect$1", "Lokhttp3/Callback;", "onResponse", _UrlKt.FRAGMENT_ENCODE_SET, "call", "Lokhttp3/Call;", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "onFailure", "e", "Ljava/io/IOException;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: okhttp3.internal.ws.RealWebSocket$connect$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Callback {
        final /* synthetic */ Request $request;
        final /* synthetic */ RealWebSocket this$0;

        public AnonymousClass1(RealWebSocket r1, Request r2) {
            this.this$0 = r1;
            this.$request = r2;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call r7, IOException r8) {
            RealWebSocket.failWebSocket$default(this.this$0, r8, null, false, 6, null);
        }

        @Override // okhttp3.Callback
        public void onResponse(Call r7, Response r8) {
            Socket r72 = this.this$0.checkUpgradeSuccess$okhttp(r8);     // Catch: IOException -> L16
            WebSocketExtensions r0 = WebSocketExtensions.Companion.parse(r8.headers());
            RealWebSocket.access$setExtensions$p(this.this$0, r0);
            if (RealWebSocket.access$isValid(this.this$0, r0) == true) goto L14;
            RealWebSocket r1 = this.this$0;
            monitor-enter(r1);
            RealWebSocket.access$getMessageAndCloseQueue$p(r1).clear();     // Catch: Throwable -> L10
            r1.close(1010, "unexpected Sec-WebSocket-Extensions in response header");     // Catch: Throwable -> L10
            monitor-exit(r1);
        L10:
            th = move-exception;
            throw th;
        L14:
            this.this$0.initReaderAndWriter(_UtilJvmKt.okHttpName + " WebSocket " + this.$request.url().redact(), BufferedSocketKt.asBufferedSocket(r72), true);
            this.this$0.loopReader(r8);
            return;
        L16:
            e = move-exception;
            RealWebSocket.failWebSocket$default(this.this$0, e, r8, false, 4, null);
            _UtilCommonKt.closeQuietly(r8);
            Socket r6 = r8.socket();
            if (r6 == null) goto L22;
            Sink r62 = r6.getSink();
            if (r62 == null) goto L22;
            _UtilCommonKt.closeQuietly(r62);
        L22:
            Socket r63 = r8.socket();
            if (r63 == null) goto L32;
            Source r64 = r63.getSource();
            if (r64 == null) goto L33;
            _UtilCommonKt.closeQuietly(r64);
            return;
        L33:
            return;
        }
    }

    /* renamed from: $r8$lambda$0T3qT4Eg5PnlD3bX-T9nHdqY_Jw, reason: not valid java name */
    public static /* synthetic */ Unit m3756$r8$lambda$0T3qT4Eg5PnlD3bXT9nHdqY_Jw(WebSocketWriter r0) {
        return finishReader$lambda$0$0(r0);
    }

    public static /* synthetic */ Unit $r8$lambda$3UeTKdScoteZtprq_bJvU_Ppjpc(RealWebSocket r0) {
        return writeOneFrame$lambda$0$0(r0);
    }

    public static /* synthetic */ Unit $r8$lambda$CoyTb8PMxSv5bSlhxaYuHDtmnaQ(Ref.ObjectRef r0) {
        return failWebSocket$lambda$0$0(r0);
    }

    public static /* synthetic */ long $r8$lambda$M3uLbVxgXTEj1BhnKpRA6a_aJpc(RealWebSocket r0, long r1) {
        return initReaderAndWriter$lambda$0$0(r0, r1);
    }

    static {
        Companion = new Companion(null);
        ONLY_HTTP1 = CollectionsKt.listOf(Protocol.HTTP_1_1);
    }

    public RealWebSocket(TaskRunner r1, Request r2, WebSocketListener r3, Random r4, long r5, WebSocketExtensions r7, long r8, long r10) {
        this.originalRequest = r2;
        this.listener = r3;
        this.random = r4;
        this.pingIntervalMillis = r5;
        this.extensions = r7;
        this.minimumDeflateSize = r8;
        this.webSocketCloseTimeout = r10;
        this.taskQueue = r1.newQueue();
        this.pongQueue = new ArrayDeque();
        this.messageAndCloseQueue = new ArrayDeque();
        this.receivedCloseCode = -1;
        if (Intrinsics.areEqual("GET", r2.method()) == false) goto L6;
        ByteString.Companion r22 = ByteString.Companion;
        byte[] r32 = new byte[16];
        r4.nextBytes(r32);
        Unit r12 = Unit.INSTANCE;
        this.key = ByteString.Companion.of$default(r22, r32, 0, 0, 3, null).base64();
        return;
    L6:
        Options$Companion$$ExternalSyntheticBUOutline0.m("Request must be GET: ", r2.method());
        throw null;
    }

    public static final /* synthetic */ ArrayDeque access$getMessageAndCloseQueue$p(RealWebSocket r0) {
        return r0.messageAndCloseQueue;
    }

    public static final /* synthetic */ String access$getName$p(RealWebSocket r0) {
        return r0.name;
    }

    public static final /* synthetic */ boolean access$isValid(RealWebSocket r0, WebSocketExtensions r1) {
        return r0.isValid(r1);
    }

    public static final /* synthetic */ void access$setExtensions$p(RealWebSocket r0, WebSocketExtensions r1) {
        r0.extensions = r1;
    }

    public static /* synthetic */ void failWebSocket$default(RealWebSocket r0, Exception r1, Response r2, boolean r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = null;
    L6:
        if ((r4 & 4) == 0) goto L8;
        r3 = false;
    L8:
        r0.failWebSocket(r1, r2, r3);
    }

    private static final Unit failWebSocket$lambda$0$0(Ref.ObjectRef r0) {
        _UtilCommonKt.closeQuietly((Closeable) r0.element);
        return Unit.INSTANCE;
    }

    private static final Unit finishReader$lambda$0$0(WebSocketWriter r0) {
        _UtilCommonKt.closeQuietly(r0);
        return Unit.INSTANCE;
    }

    private static final long initReaderAndWriter$lambda$0$0(RealWebSocket r0, long r1) {
        r0.writePingFrame$okhttp();
        return r1;
    }

    private final boolean isValid(WebSocketExtensions r2) {
        if (r2.unknownValues == false) goto L6;
        return false;
    L6:
        if (r2.clientMaxWindowBits == null) goto L8;
        return false;
    L8:
        Integer r1 = r2.serverMaxWindowBits;
        if (r1 == null) goto L16;
        int r12 = r1.intValue();
        if (8 <= r12) goto L13;
    L15:
        return false;
    L13:
        if (r12 >= 16) goto L15;
        return true;
    L16:
        return true;
    }

    private final void runWriter() {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        Task r3 = this.writerTask;
        if (r3 == null) goto L13;
        TaskQueue.schedule$default(this.taskQueue, r3, 0, 2, null);
        return;
    L13:
        return;
    L5:
        if (Thread.holdsLock(this) == true) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", this);
    }

    private final synchronized boolean send(ByteString r7, int r8) {
        monitor-enter(this);
    L13:
        th = move-exception;
        throw th;
    L4:
        if (this.failed == false) goto L6;
    L19:
        monitor-exit(this);
        return false;
    L6:
        if (this.enqueuedClose == true) goto L19;
        if ((this.queueSize + r7.size()) <= MAX_QUEUE_SIZE) goto L15;
        close(WebSocketProtocol.CLOSE_CLIENT_GOING_AWAY, null);     // Catch: Throwable -> L13
        monitor-exit(this);
        return false;
    L15:
        this.queueSize += r7.size();
        this.messageAndCloseQueue.add(new Message(r8, r7));     // Catch: Throwable -> L13
        runWriter();     // Catch: Throwable -> L13
        monitor-exit(this);
        return true;
    }

    private static final Unit writeOneFrame$lambda$0$0(RealWebSocket r0) {
        r0.cancel();
        return Unit.INSTANCE;
    }

    @Override // okhttp3.WebSocket
    public void cancel() {
        this.call.cancel();
    }

    public final Socket checkUpgradeSuccess$okhttp(Response r8) {
        if (r8.code() != 101) goto L22;
        String r0 = Response.header$default(r8, "Connection", null, 2, null);
        if (StringsKt.equals("Upgrade", r0, true) == false) goto L20;
        String r02 = Response.header$default(r8, "Upgrade", null, 2, null);
        if (StringsKt.equals("websocket", r02, true) == false) goto L18;
        String r03 = Response.header$default(r8, "Sec-WebSocket-Accept", null, 2, null);
        String r7 = ByteString.Companion.encodeUtf8(this.key + WebSocketProtocol.ACCEPT_MAGIC).sha1().base64();
        if (Intrinsics.areEqual(r7, r03) == false) goto L16;
        Socket r72 = r8.socket();
        if (r72 == null) goto L14;
        return r72;
    L14:
        throw new ProtocolException("Web Socket socket missing: bad interceptor?");
    L16:
        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + r7 + "' but was '" + r03 + '\'');
    L18:
        throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + r02 + '\'');
    L20:
        throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + r0 + '\'');
    L22:
        throw new ProtocolException("Expected HTTP 101 response but was '" + r8.code() + ' ' + r8.message() + '\'');
    }

    @Override // okhttp3.WebSocket
    public boolean close(int r3, String r4) {
        return close(r3, r4, this.webSocketCloseTimeout);
    }

    public final synchronized boolean close(int r7, String r8, long r9) {
        monitor-enter(this);
        WebSocketProtocol.INSTANCE.validateCloseCode(r7);     // Catch: Throwable -> L11
        if (r8 == null) goto L13;
        ByteString r1 = ByteString.Companion.encodeUtf8(r8);     // Catch: Throwable -> L11
        if (r1.size() > 123) goto L10;
    L15:
        if (this.failed == false) goto L17;
    L22:
        monitor-exit(this);
        return false;
    L17:
        if (this.enqueuedClose == true) goto L22;
        this.enqueuedClose = true;     // Catch: Throwable -> L11
        this.messageAndCloseQueue.add(new Close(r7, r1, r9));     // Catch: Throwable -> L11
        runWriter();     // Catch: Throwable -> L11
        monitor-exit(this);
        return true;
    L10:
        throw new IllegalArgumentException("reason.size() > 123: ".concat(r8).toString());     // Catch: Throwable -> L11
    L13:
        r1 = null;
    L11:
        th = move-exception;
        throw th;
    }

    public final void connect(OkHttpClient r9) {
        if (this.originalRequest.header("Sec-WebSocket-Extensions") == null) goto L6;
        failWebSocket$default(this, new ProtocolException("Request header not permitted: 'Sec-WebSocket-Extensions'"), null, false, 6, null);
        return;
    L6:
        OkHttpClient r8 = r9.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        Request r92 = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").header("Sec-WebSocket-Extensions", "permessage-deflate").build();
        RealCall r0 = new RealCall(r8, r92, true);
        this.call = r0;
        r0.enqueue(new AnonymousClass1(this, r92));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [T, okhttp3.internal.ws.WebSocketWriter] */
    public final void failWebSocket(Exception r13, Response r14, boolean r15) {
        Ref.ObjectRef r1 = new Ref.ObjectRef();
        monitor-enter(this);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (this.failed == false) goto L9;
        monitor-exit(this);
        return;
    L9:
        this.failed = true;     // Catch: Throwable -> L13
        Socket r2 = this.socket;     // Catch: Throwable -> L13
        ?? r0 = this.writer;     // Catch: Throwable -> L13
        r1.element = r0;     // Catch: Throwable -> L13
        this.writer = null;     // Catch: Throwable -> L13
        if (r15 == true) goto L15;
        if (r0 == 0) goto L15;
        TaskQueue.execute$default(this.taskQueue, this.name + " writer close", 0, false, new RealWebSocket$$ExternalSyntheticLambda3(r1), 2, null);     // Catch: Throwable -> L13
    L15:
        this.taskQueue.shutdown();     // Catch: Throwable -> L13
        Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(this);
        this.listener.onFailure(this, r13, r14);     // Catch: Throwable -> L25
        if (r2 == null) goto L20;
        r2.cancel();
    L20:
        if (r15 == false) goto L40;
        WebSocketWriter r12 = (WebSocketWriter) r1.element;
        if (r12 == null) goto L41;
        _UtilCommonKt.closeQuietly(r12);
        return;
    L41:
        return;
    L40:
        return;
    L25:
        th = move-exception;
        if (r2 == null) goto L29;
        r2.cancel();
    L29:
        if (r15 == false) goto L42;
        WebSocketWriter r132 = (WebSocketWriter) r1.element;
        if (r132 == null) goto L43;
        _UtilCommonKt.closeQuietly(r132);
        throw th;
    L43:
        throw th;
    L42:
        throw th;
    }

    public final void finishReader() {
        monitor-enter(this);
        int r0 = this.receivedCloseCode;     // Catch: Throwable -> L10
        String r1 = this.receivedCloseReason;     // Catch: Throwable -> L10
        WebSocketReader r2 = this.reader;     // Catch: Throwable -> L10
        this.reader = null;     // Catch: Throwable -> L10
        if (this.enqueuedClose == false) goto L14;
        if (this.messageAndCloseQueue.isEmpty() == false) goto L14;
        WebSocketWriter r4 = this.writer;     // Catch: Throwable -> L10
        if (r4 == null) goto L12;
        this.writer = null;     // Catch: Throwable -> L10
        TaskQueue.execute$default(this.taskQueue, this.name + " writer close", 0, false, new RealWebSocket$$ExternalSyntheticLambda1(r4), 2, null);     // Catch: Throwable -> L10
    L12:
        this.taskQueue.shutdown();     // Catch: Throwable -> L10
    L14:
        if (this.failed == false) goto L16;
    L20:
        boolean r3 = false;
    L21:
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(this);
        if (r3 == false) goto L25;
        this.listener.onClosed(this, r0, r1);
    L25:
        if (r2 == null) goto L32;
        _UtilCommonKt.closeQuietly(r2);
        return;
    L32:
        return;
    L16:
        if (this.writer != null) goto L20;
        if (this.receivedCloseCode == (-1)) goto L20;
        r3 = true;
    L10:
        th = move-exception;
        throw th;
    }

    public final Call getCall$okhttp() {
        return this.call;
    }

    public final WebSocketListener getListener$okhttp() {
        return this.listener;
    }

    public final void initReaderAndWriter(String r13, BufferedSocket r14, boolean r15) {
        WebSocketExtensions r2 = this.extensions;
        monitor-enter(this);
        this.name = r13;     // Catch: Throwable -> L7
        this.socket = r14;     // Catch: Throwable -> L7
        this.writer = new WebSocketWriter(r15, r14.getSink(), this.random, r2.perMessageDeflate, r2.noContextTakeover(r15), this.minimumDeflateSize);     // Catch: Throwable -> L7
        this.writerTask = new WriterTask(this);     // Catch: Throwable -> L7
        long r4 = this.pingIntervalMillis;     // Catch: Throwable -> L7
        if (r4 == 0) goto L10;
        long r42 = TimeUnit.MILLISECONDS.toNanos(r4);     // Catch: Throwable -> L7
        this.taskQueue.schedule(r13 + " ping", r42, new RealWebSocket$$ExternalSyntheticLambda2(this, r42));     // Catch: Throwable -> L7
    L10:
        if (this.messageAndCloseQueue.isEmpty() == true) goto L12;
        runWriter();     // Catch: Throwable -> L7
    L12:
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(this);
        this.reader = new WebSocketReader(r15, r14.getSource(), this, r2.perMessageDeflate, r2.noContextTakeover(!r15));
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void loopReader(Response r8) {
        this.listener.onOpen(this, r8);     // Catch: Throwable -> L7 Exception -> L9
    L3:
        if (this.receivedCloseCode != (-1)) goto L11;
        this.reader.processNextFrame();     // Catch: Throwable -> L7 Exception -> L9
        goto L3
    L11:
        finishReader();
        return;
    L9:
        e = move-exception;
        RealWebSocket r1 = this;
        failWebSocket$default(r1, e, null, false, 6, null);     // Catch: Throwable -> L17
        r1.finishReader();
        return;
    L17:
        th = move-exception;
        Throwable r82 = th;
    L19:
        r1.finishReader();
        throw r82;
    L7:
        th = move-exception;
        r82 = th;
        r1 = this;
        goto L19
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadClose(int r3, String r4) {
        if (r3 == (-1)) goto L17;
        monitor-enter(this);
    L11:
        th = move-exception;
        throw th;
    L6:
        if (this.receivedCloseCode != (-1)) goto L14;
        this.receivedCloseCode = r3;     // Catch: Throwable -> L11
        this.receivedCloseReason = r4;     // Catch: Throwable -> L11
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(this);
        this.listener.onClosing(this, r3, r4);
        return;
    L14:
        throw new IllegalStateException("already closed");     // Catch: Throwable -> L11
    L17:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(String r2) {
        this.listener.onMessage(this, r2);
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(ByteString r2) {
        this.listener.onMessage(this, r2);
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPing(ByteString r2) {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (this.failed == false) goto L6;
    L15:
        monitor-exit(this);
        return;
    L6:
        if (this.enqueuedClose == true) goto L8;
    L12:
        this.pongQueue.add(r2);     // Catch: Throwable -> L10
        runWriter();     // Catch: Throwable -> L10
        this.receivedPingCount++;
        monitor-exit(this);
        return;
    L8:
        if (this.messageAndCloseQueue.isEmpty() == false) goto L12;
        goto L12
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPong(ByteString r1) {
        monitor-enter(this);
        this.receivedPongCount++;
        this.awaitingPong = false;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized boolean pong(ByteString r2) {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (this.failed == false) goto L6;
    L16:
        monitor-exit(this);
        return false;
    L6:
        if (this.enqueuedClose == true) goto L8;
    L12:
        this.pongQueue.add(r2);     // Catch: Throwable -> L10
        runWriter();     // Catch: Throwable -> L10
        monitor-exit(this);
        return true;
    L8:
        if (this.messageAndCloseQueue.isEmpty() == false) goto L12;
        goto L12
    }

    public final boolean processNextFrame() {
        this.reader.processNextFrame();     // Catch: Exception -> L9
        if (this.receivedCloseCode != (-1)) goto L8;
        return true;
    L8:
        return false;
    L9:
        e = move-exception;
        failWebSocket$default(this, e, null, false, 6, null);
        return false;
    }

    @Override // okhttp3.WebSocket
    public synchronized long queueSize() {
        monitor-enter(this);
        long r0 = this.queueSize;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized int receivedPingCount() {
        monitor-enter(this);
        int r0 = this.receivedPingCount;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized int receivedPongCount() {
        monitor-enter(this);
        int r0 = this.receivedPongCount;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // okhttp3.WebSocket
    public Request request() {
        return this.originalRequest;
    }

    @Override // okhttp3.WebSocket
    public boolean send(String r2) {
        return send(ByteString.Companion.encodeUtf8(r2), 1);
    }

    @Override // okhttp3.WebSocket
    public boolean send(ByteString r2) {
        return send(r2, 2);
    }

    public final synchronized int sentPingCount() {
        monitor-enter(this);
        int r0 = this.sentPingCount;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final void setCall$okhttp(Call r1) {
        this.call = r1;
    }

    public final void tearDown() {
        this.taskQueue.shutdown();
        this.taskQueue.idleLatch().await(10, TimeUnit.SECONDS);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v5 */
    public final boolean writeOneFrame$okhttp() {
        monitor-enter(this);
    L20:
        th = move-exception;
        throw th;
    L5:
        boolean r2 = false;
        if (this.failed == false) goto L9;
        monitor-exit(this);
        return false;
    L9:
        WebSocketWriter r0 = this.writer;     // Catch: Throwable -> L20
        ByteString r3 = this.pongQueue.poll();     // Catch: Throwable -> L20
        Message r5 = 0;
        if (r3 != null) goto L30;
        Object r7 = this.messageAndCloseQueue.poll();     // Catch: Throwable -> L20
        if ((r7 instanceof Close) == false) goto L26;
        int r8 = this.receivedCloseCode;     // Catch: Throwable -> L20
        String r9 = this.receivedCloseReason;     // Catch: Throwable -> L20
        if (r8 == (-1)) goto L24;
        WebSocketWriter r6 = this.writer;     // Catch: Throwable -> L20
        this.writer = null;     // Catch: Throwable -> L20
        if (r6 != null) goto L18;
    L22:
        this.taskQueue.shutdown();     // Catch: Throwable -> L20
    L23:
        r5 = r7;
    L31:
        Unit r72 = Unit.INSTANCE;     // Catch: Throwable -> L20
        monitor-exit(this);
        if (r3 == null) goto L38;
        r0.writePong(r3);     // Catch: Throwable -> L35
    L52:
        if (r6 == null) goto L54;
        _UtilCommonKt.closeQuietly(r6);
    L54:
        return true;
    L38:
        if ((r5 instanceof Message) == false) goto L48;
        r0.writeMessageFrame(r5.getFormatOpcode(), r5.getData());     // Catch: Throwable -> L35
        monitor-enter(this);     // Catch: Throwable -> L35
        this.queueSize -= r5.getData().size();
        monitor-exit(this);     // Catch: Throwable -> L35
        goto L52
    L44:
        th = move-exception;
        throw th;     // Catch: Throwable -> L35
    L48:
        if ((r5 instanceof Close) == false) goto L56;
        r0.writeClose(r5.getCode(), ((Close) r5).getReason());     // Catch: Throwable -> L35
        if (r2 == false) goto L52;
        this.listener.onClosed(this, r8, r9);     // Catch: Throwable -> L35
        goto L52
    L56:
        throw new AssertionError();     // Catch: Throwable -> L35
    L35:
        th = move-exception;
        if (r6 == null) goto L59;
        _UtilCommonKt.closeQuietly(r6);
    L59:
        throw th;
    L18:
        if (this.reader != null) goto L22;
        r2 = true;
        goto L22
    L24:
        long r10 = ((Close) r7).getCancelAfterCloseMillis();     // Catch: Throwable -> L20
        TaskQueue.execute$default(this.taskQueue, this.name + " cancel", TimeUnit.MILLISECONDS.toNanos(r10), false, new RealWebSocket$$ExternalSyntheticLambda0(this), 4, null);     // Catch: Throwable -> L20
        r6 = null;
        goto L23
    L26:
        if (r7 != null) goto L29;
        monitor-exit(this);
        return false;
    L29:
        r9 = null;
        r8 = -1;
        r6 = null;
        goto L23
    L30:
        r9 = null;
        r8 = -1;
        r6 = null;
        goto L31
    }

    public final void writePingFrame$okhttp() {
        monitor-enter(this);
    L14:
        th = move-exception;
        throw th;
    L4:
        if (this.failed == false) goto L7;
        monitor-exit(this);
        return;
    L7:
        WebSocketWriter r0 = this.writer;     // Catch: Throwable -> L14
        if (r0 != null) goto L12;
        monitor-exit(this);
        return;
    L12:
        if (this.awaitingPong == false) goto L16;
        int r2 = this.sentPingCount;     // Catch: Throwable -> L14
    L17:
        this.sentPingCount++;
        this.awaitingPong = true;     // Catch: Throwable -> L14
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(this);
        if (r2 == (-1)) goto L29;
        failWebSocket$default(this, new SocketTimeoutException("sent ping but didn't receive pong within " + this.pingIntervalMillis + "ms (after " + (r2 - 1) + " successful ping/pongs)"), null, true, 2, null);
        return;
    L29:
        r0.writePing(ByteString.EMPTY);     // Catch: IOException -> L24
        return;
    L24:
        e = move-exception;
        failWebSocket$default(this, e, null, true, 2, null);
        return;
    L16:
        r2 = -1;
        goto L17
    }
}
