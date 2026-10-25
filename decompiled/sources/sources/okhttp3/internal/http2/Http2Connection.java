package okhttp3.internal.http2;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Headers;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal._UtilJvmKt$$ExternalSyntheticBUOutline1;
import okhttp3.internal.concurrent.Lockable;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.BufferedSocket;
import okhttp3.internal.http2.FlowControlListener;
import okhttp3.internal.http2.Http2Reader;
import okhttp3.internal.http2.flowcontrol.WindowCounter;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 ¡\u00012\u00020\u00012\u00020\u0002:\b\u009e\u0001\u009f\u0001 \u0001¡\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010V\u001a\u00020\u0011J\u0010\u0010W\u001a\u0004\u0018\u00010\u00122\u0006\u0010X\u001a\u00020\u0011J\u0017\u0010Y\u001a\u0004\u0018\u00010\u00122\u0006\u0010Z\u001a\u00020\u0011H\u0000¢\u0006\u0002\b[J\u0015\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020+H\u0000¢\u0006\u0002\b_J$\u0010`\u001a\u00020\u00122\u0006\u0010a\u001a\u00020\u00112\f\u0010b\u001a\b\u0012\u0004\u0012\u00020d0c2\u0006\u0010e\u001a\u00020\bJ\u001c\u0010f\u001a\u00020\u00122\f\u0010b\u001a\b\u0012\u0004\u0012\u00020d0c2\u0006\u0010e\u001a\u00020\bJ&\u0010f\u001a\u00020\u00122\u0006\u0010a\u001a\u00020\u00112\f\u0010b\u001a\b\u0012\u0004\u0012\u00020d0c2\u0006\u0010e\u001a\u00020\bH\u0002J+\u0010g\u001a\u00020]2\u0006\u0010Z\u001a\u00020\u00112\u0006\u0010h\u001a\u00020\b2\f\u0010i\u001a\b\u0012\u0004\u0012\u00020d0cH\u0000¢\u0006\u0002\bjJ(\u0010k\u001a\u00020]2\u0006\u0010Z\u001a\u00020\u00112\u0006\u0010h\u001a\u00020\b2\b\u0010l\u001a\u0004\u0018\u00010m2\u0006\u0010n\u001a\u00020+J\u001d\u0010o\u001a\u00020]2\u0006\u0010Z\u001a\u00020\u00112\u0006\u0010p\u001a\u00020qH\u0000¢\u0006\u0002\brJ\u001d\u0010s\u001a\u00020]2\u0006\u0010Z\u001a\u00020\u00112\u0006\u0010t\u001a\u00020qH\u0000¢\u0006\u0002\buJ\u001d\u0010v\u001a\u00020]2\u0006\u0010Z\u001a\u00020\u00112\u0006\u0010w\u001a\u00020+H\u0000¢\u0006\u0002\bxJ\u001e\u0010y\u001a\u00020]2\u0006\u0010z\u001a\u00020\b2\u0006\u0010{\u001a\u00020\u00112\u0006\u0010|\u001a\u00020\u0011J\u0006\u0010}\u001a\u00020]J\u0006\u0010y\u001a\u00020]J\u0006\u0010~\u001a\u00020]J\u0006\u0010\u007f\u001a\u00020]J\u000f\u0010\u0080\u0001\u001a\u00020]2\u0006\u0010t\u001a\u00020qJ\t\u0010\u0081\u0001\u001a\u00020]H\u0016J-\u0010\u0081\u0001\u001a\u00020]2\u0007\u0010\u0082\u0001\u001a\u00020q2\u0007\u0010\u0083\u0001\u001a\u00020q2\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001H\u0000¢\u0006\u0003\b\u0086\u0001J\u0015\u0010\u0087\u0001\u001a\u00020]2\n\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0085\u0001H\u0002J\u0014\u0010\u0089\u0001\u001a\u00020]2\t\b\u0002\u0010\u008a\u0001\u001a\u00020\bH\u0007J\u0010\u0010\u008b\u0001\u001a\u00020]2\u0007\u0010\u008c\u0001\u001a\u000207J\u0010\u0010\u008d\u0001\u001a\u00020\b2\u0007\u0010\u008e\u0001\u001a\u00020+J\u000f\u0010\u008f\u0001\u001a\u00020]H\u0000¢\u0006\u0003\b\u0090\u0001J\u0017\u0010\u0091\u0001\u001a\u00020\b2\u0006\u0010Z\u001a\u00020\u0011H\u0000¢\u0006\u0003\b\u0092\u0001J%\u0010\u0093\u0001\u001a\u00020]2\u0006\u0010Z\u001a\u00020\u00112\f\u0010b\u001a\b\u0012\u0004\u0012\u00020d0cH\u0000¢\u0006\u0003\b\u0094\u0001J.\u0010\u0095\u0001\u001a\u00020]2\u0006\u0010Z\u001a\u00020\u00112\f\u0010b\u001a\b\u0012\u0004\u0012\u00020d0c2\u0007\u0010\u0096\u0001\u001a\u00020\bH\u0000¢\u0006\u0003\b\u0097\u0001J2\u0010\u0098\u0001\u001a\u00020]2\u0006\u0010Z\u001a\u00020\u00112\b\u0010\u0099\u0001\u001a\u00030\u009a\u00012\u0006\u0010n\u001a\u00020\u00112\u0007\u0010\u0096\u0001\u001a\u00020\bH\u0000¢\u0006\u0003\b\u009b\u0001J\u001f\u0010\u009c\u0001\u001a\u00020]2\u0006\u0010Z\u001a\u00020\u00112\u0006\u0010p\u001a\u00020qH\u0000¢\u0006\u0003\b\u009d\u0001R\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0016X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u000e\u0010!\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00102\u001a\u000203X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u00106\u001a\u000207¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u001a\u0010:\u001a\u000207X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u00109\"\u0004\b<\u0010=R\u0011\u0010>\u001a\u00020?¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u001e\u0010C\u001a\u00020+2\u0006\u0010B\u001a\u00020+@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u001e\u0010F\u001a\u00020+2\u0006\u0010B\u001a\u00020+@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bG\u0010ER\u0014\u0010H\u001a\u00020IX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010KR\u0011\u0010L\u001a\u00020M¢\u0006\b\n\u0000\u001a\u0004\bN\u0010OR\u0015\u0010P\u001a\u00060QR\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\bR\u0010SR\u0014\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00110UX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006¢\u0001"}, d2 = {"Lokhttp3/internal/http2/Http2Connection;", "Ljava/io/Closeable;", "Lokhttp3/internal/concurrent/Lockable;", "builder", "Lokhttp3/internal/http2/Http2Connection$Builder;", "<init>", "(Lokhttp3/internal/http2/Http2Connection$Builder;)V", "client", _UrlKt.FRAGMENT_ENCODE_SET, "getClient$okhttp", "()Z", "listener", "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "streams", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/http2/Http2Stream;", "getStreams$okhttp", "()Ljava/util/Map;", "connectionName", _UrlKt.FRAGMENT_ENCODE_SET, "getConnectionName$okhttp", "()Ljava/lang/String;", "lastGoodStreamId", "getLastGoodStreamId$okhttp", "()I", "setLastGoodStreamId$okhttp", "(I)V", "nextStreamId", "getNextStreamId$okhttp", "setNextStreamId$okhttp", "isShutdown", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "writerQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "pushQueue", "settingsListenerQueue", "pushObserver", "Lokhttp3/internal/http2/PushObserver;", "intervalPingsSent", _UrlKt.FRAGMENT_ENCODE_SET, "intervalPongsReceived", "degradedPingsSent", "degradedPongsReceived", "awaitPingsSent", "awaitPongsReceived", "degradedPongDeadlineNs", "flowControlListener", "Lokhttp3/internal/http2/FlowControlListener;", "getFlowControlListener$okhttp", "()Lokhttp3/internal/http2/FlowControlListener;", "okHttpSettings", "Lokhttp3/internal/http2/Settings;", "getOkHttpSettings", "()Lokhttp3/internal/http2/Settings;", "peerSettings", "getPeerSettings", "setPeerSettings", "(Lokhttp3/internal/http2/Settings;)V", "readBytes", "Lokhttp3/internal/http2/flowcontrol/WindowCounter;", "getReadBytes", "()Lokhttp3/internal/http2/flowcontrol/WindowCounter;", "value", "writeBytesTotal", "getWriteBytesTotal", "()J", "writeBytesMaximum", "getWriteBytesMaximum", "socket", "Lokhttp3/internal/connection/BufferedSocket;", "getSocket$okhttp", "()Lokhttp3/internal/connection/BufferedSocket;", "writer", "Lokhttp3/internal/http2/Http2Writer;", "getWriter", "()Lokhttp3/internal/http2/Http2Writer;", "readerRunnable", "Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "getReaderRunnable", "()Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "currentPushRequests", _UrlKt.FRAGMENT_ENCODE_SET, "openStreamCount", "getStream", "id", "removeStream", "streamId", "removeStream$okhttp", "updateConnectionFlowControl", _UrlKt.FRAGMENT_ENCODE_SET, "read", "updateConnectionFlowControl$okhttp", "pushStream", "associatedStreamId", "requestHeaders", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/http2/Header;", "out", "newStream", "writeHeaders", "outFinished", "alternating", "writeHeaders$okhttp", "writeData", "buffer", "Lokio/Buffer;", "byteCount", "writeSynResetLater", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "writeSynResetLater$okhttp", "writeSynReset", "statusCode", "writeSynReset$okhttp", "writeWindowUpdateLater", "unacknowledgedBytesRead", "writeWindowUpdateLater$okhttp", "writePing", "reply", "payload1", "payload2", "writePingAndAwaitPong", "awaitPong", "flush", "shutdown", "close", "connectionCode", "streamCode", "cause", "Ljava/io/IOException;", "close$okhttp", "failConnection", "e", "start", "sendConnectionPreface", "setSettings", "settings", "isHealthy", "nowNs", "sendDegradedPingLater", "sendDegradedPingLater$okhttp", "pushedStream", "pushedStream$okhttp", "pushRequestLater", "pushRequestLater$okhttp", "pushHeadersLater", "inFinished", "pushHeadersLater$okhttp", "pushDataLater", "source", "Lokio/BufferedSource;", "pushDataLater$okhttp", "pushResetLater", "pushResetLater$okhttp", "Builder", "ReaderRunnable", "Listener", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHttp2Connection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http2Connection.kt\nokhttp3/internal/http2/Http2Connection\n+ 2 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 -UtilCommon.kt\nokhttp3/internal/_UtilCommonKt\n+ 5 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 6 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,1042:1\n63#2:1043\n63#2:1044\n63#2:1045\n38#2:1046\n63#2:1047\n63#2:1049\n63#2:1050\n63#2:1051\n34#2:1052\n63#2:1053\n63#2:1054\n34#2:1055\n63#2:1056\n63#2:1057\n49#2,4:1058\n63#2:1067\n63#2:1087\n63#2:1088\n63#2:1089\n63#2:1090\n63#2:1091\n63#2:1092\n63#2:1095\n63#2:1101\n63#2:1107\n63#2:1111\n1#3:1048\n228#4,5:1062\n228#4,5:1071\n228#4,5:1077\n228#4,5:1082\n228#4,2:1093\n230#4,3:1096\n228#4,2:1099\n230#4,3:1102\n228#4,2:1105\n230#4,3:1108\n37#5,2:1068\n13805#6:1070\n13806#6:1076\n*S KotlinDebug\n*F\n+ 1 Http2Connection.kt\nokhttp3/internal/http2/Http2Connection\n*L\n174#1:1043\n176#1:1044\n179#1:1045\n183#1:1046\n190#1:1047\n242#1:1049\n243#1:1050\n313#1:1051\n321#1:1052\n394#1:1053\n405#1:1054\n407#1:1055\n424#1:1056\n426#1:1057\n452#1:1058,4\n459#1:1067\n519#1:1087\n520#1:1088\n531#1:1089\n557#1:1090\n914#1:1091\n152#1:1092\n926#1:1095\n944#1:1101\n971#1:1107\n985#1:1111\n454#1:1062,5\n467#1:1071,5\n473#1:1077,5\n478#1:1082,5\n923#1:1093,2\n923#1:1096,3\n941#1:1099,2\n941#1:1102,3\n967#1:1105,2\n967#1:1108,3\n461#1:1068,2\n466#1:1070\n466#1:1076\n*E\n"})
/* loaded from: classes.dex */
public final class Http2Connection implements Closeable, Lockable {
    public static final int AWAIT_PING = 3;
    public static final Companion Companion = null;
    private static final Settings DEFAULT_SETTINGS = null;
    public static final int DEGRADED_PING = 2;
    public static final int DEGRADED_PONG_TIMEOUT_NS = 1000000000;
    public static final int INTERVAL_PING = 1;
    public static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    private long awaitPingsSent;
    private long awaitPongsReceived;
    private final boolean client;
    private final String connectionName;
    private final Set<Integer> currentPushRequests;
    private long degradedPingsSent;
    private long degradedPongDeadlineNs;
    private long degradedPongsReceived;
    private final FlowControlListener flowControlListener;
    private long intervalPingsSent;
    private long intervalPongsReceived;
    private boolean isShutdown;
    private int lastGoodStreamId;
    private final Listener listener;
    private int nextStreamId;
    private final Settings okHttpSettings;
    private Settings peerSettings;
    private final PushObserver pushObserver;
    private final TaskQueue pushQueue;
    private final WindowCounter readBytes;
    private final ReaderRunnable readerRunnable;
    private final TaskQueue settingsListenerQueue;
    private final BufferedSocket socket;
    private final Map<Integer, Http2Stream> streams;
    private final TaskRunner taskRunner;
    private long writeBytesMaximum;
    private long writeBytesTotal;
    private final Http2Writer writer;
    private final TaskQueue writerQueue;

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u00102\u001a\u00020\u0015J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010 \u001a\u00020\u00002\u0006\u0010 \u001a\u00020!J\u000e\u0010&\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'J\u000e\u0010,\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-J\u0006\u00103\u001a\u000204R\u001a\u0010\u0002\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020'X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020-X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101¨\u00065"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "client", _UrlKt.FRAGMENT_ENCODE_SET, "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "<init>", "(ZLokhttp3/internal/concurrent/TaskRunner;)V", "getClient$okhttp", "()Z", "setClient$okhttp", "(Z)V", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "socket", "Lokhttp3/internal/connection/BufferedSocket;", "getSocket$okhttp", "()Lokhttp3/internal/connection/BufferedSocket;", "setSocket$okhttp", "(Lokhttp3/internal/connection/BufferedSocket;)V", "connectionName", _UrlKt.FRAGMENT_ENCODE_SET, "getConnectionName$okhttp", "()Ljava/lang/String;", "setConnectionName$okhttp", "(Ljava/lang/String;)V", "listener", "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "setListener$okhttp", "(Lokhttp3/internal/http2/Http2Connection$Listener;)V", "pushObserver", "Lokhttp3/internal/http2/PushObserver;", "getPushObserver$okhttp", "()Lokhttp3/internal/http2/PushObserver;", "setPushObserver$okhttp", "(Lokhttp3/internal/http2/PushObserver;)V", "pingIntervalMillis", _UrlKt.FRAGMENT_ENCODE_SET, "getPingIntervalMillis$okhttp", "()I", "setPingIntervalMillis$okhttp", "(I)V", "flowControlListener", "Lokhttp3/internal/http2/FlowControlListener;", "getFlowControlListener$okhttp", "()Lokhttp3/internal/http2/FlowControlListener;", "setFlowControlListener$okhttp", "(Lokhttp3/internal/http2/FlowControlListener;)V", "peerName", "build", "Lokhttp3/internal/http2/Http2Connection;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Builder {
        private boolean client;
        public String connectionName;
        private FlowControlListener flowControlListener;
        private Listener listener;
        private int pingIntervalMillis;
        private PushObserver pushObserver;
        public BufferedSocket socket;
        private final TaskRunner taskRunner;

        public Builder(boolean r1, TaskRunner r2) {
            this.client = r1;
            this.taskRunner = r2;
            this.listener = Listener.REFUSE_INCOMING_STREAMS;
            this.pushObserver = PushObserver.CANCEL;
            this.flowControlListener = FlowControlListener.None.INSTANCE;
        }

        public final Http2Connection build() {
            return new Http2Connection(this);
        }

        public final Builder flowControlListener(FlowControlListener r1) {
            this.flowControlListener = r1;
            return this;
        }

        public final boolean getClient$okhttp() {
            return this.client;
        }

        public final String getConnectionName$okhttp() {
            String r0 = this.connectionName;
            if (r0 == null) goto L5;
            return r0;
        L5:
            return null;
        }

        public final FlowControlListener getFlowControlListener$okhttp() {
            return this.flowControlListener;
        }

        public final Listener getListener$okhttp() {
            return this.listener;
        }

        public final int getPingIntervalMillis$okhttp() {
            return this.pingIntervalMillis;
        }

        public final PushObserver getPushObserver$okhttp() {
            return this.pushObserver;
        }

        public final BufferedSocket getSocket$okhttp() {
            BufferedSocket r0 = this.socket;
            if (r0 == null) goto L5;
            return r0;
        L5:
            return null;
        }

        public final TaskRunner getTaskRunner$okhttp() {
            return this.taskRunner;
        }

        public final Builder listener(Listener r1) {
            this.listener = r1;
            return this;
        }

        public final Builder pingIntervalMillis(int r1) {
            this.pingIntervalMillis = r1;
            return this;
        }

        public final Builder pushObserver(PushObserver r1) {
            this.pushObserver = r1;
            return this;
        }

        public final void setClient$okhttp(boolean r1) {
            this.client = r1;
        }

        public final void setConnectionName$okhttp(String r1) {
            this.connectionName = r1;
        }

        public final void setFlowControlListener$okhttp(FlowControlListener r1) {
            this.flowControlListener = r1;
        }

        public final void setListener$okhttp(Listener r1) {
            this.listener = r1;
        }

        public final void setPingIntervalMillis$okhttp(int r1) {
            this.pingIntervalMillis = r1;
        }

        public final void setPushObserver$okhttp(PushObserver r1) {
            this.pushObserver = r1;
        }

        public final void setSocket$okhttp(BufferedSocket r1) {
            this.socket = r1;
        }

        public final Builder socket(BufferedSocket r2, String r3) {
            setSocket$okhttp(r2);
            if (this.client == false) goto L5;
            String r22 = _UtilJvmKt.okHttpName + ' ' + r3;
        L6:
            setConnectionName$okhttp(r22);
            return this;
        L5:
            r22 = "MockWebServer " + r3;
            goto L6
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "OKHTTP_CLIENT_WINDOW_SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "DEFAULT_SETTINGS", "Lokhttp3/internal/http2/Settings;", "getDEFAULT_SETTINGS", "()Lokhttp3/internal/http2/Settings;", "INTERVAL_PING", "DEGRADED_PING", "AWAIT_PING", "DEGRADED_PONG_TIMEOUT_NS", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final Settings getDEFAULT_SETTINGS() {
            return Http2Connection.access$getDEFAULT_SETTINGS$cp();
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\u000e"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Listener;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "onStream", _UrlKt.FRAGMENT_ENCODE_SET, "stream", "Lokhttp3/internal/http2/Http2Stream;", "onSettings", "connection", "Lokhttp3/internal/http2/Http2Connection;", "settings", "Lokhttp3/internal/http2/Settings;", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Listener {
        public static final Companion Companion = null;

        @JvmField
        public static final Listener REFUSE_INCOMING_STREAMS = null;

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Listener$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "REFUSE_INCOMING_STREAMS", "Lokhttp3/internal/http2/Http2Connection$Listener;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }
        }

        static {
            Companion = new Companion(null);
            REFUSE_INCOMING_STREAMS = new Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1();
        }

        public Listener() {
        }

        public void onSettings(Http2Connection r1, Settings r2) {
        }

        public abstract void onStream(Http2Stream r1);
    }

    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0011\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\n\u001a\u00020\u0003H\u0096\u0002J(\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J.\u0010\u0013\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0016J\u0018\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001dH\u0016J\u0016\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001dJ\b\u0010\u001f\u001a\u00020\u0003H\u0016J \u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0016J \u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020'H\u0016J\u0018\u0010(\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*H\u0016J(\u0010+\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\rH\u0016J&\u0010/\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0016J8\u00102\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020'2\u0006\u00106\u001a\u0002042\u0006\u00107\u001a\u00020\u000f2\u0006\u00108\u001a\u00020*H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u00069"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "Lokhttp3/internal/http2/Http2Reader$Handler;", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "reader", "Lokhttp3/internal/http2/Http2Reader;", "<init>", "(Lokhttp3/internal/http2/Http2Connection;Lokhttp3/internal/http2/Http2Reader;)V", "getReader$okhttp", "()Lokhttp3/internal/http2/Http2Reader;", "invoke", "data", "inFinished", _UrlKt.FRAGMENT_ENCODE_SET, "streamId", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lokio/BufferedSource;", "length", "headers", "associatedStreamId", "headerBlock", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/http2/Header;", "rstStream", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "settings", "clearPrevious", "Lokhttp3/internal/http2/Settings;", "applyAndAckSettings", "ackSettings", "ping", "ack", "payload1", "payload2", "goAway", "lastGoodStreamId", "debugData", "Lokio/ByteString;", "windowUpdate", "windowSizeIncrement", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.MenuItemProperties.PRIORITY, "streamDependency", "weight", "exclusive", "pushPromise", "promisedStreamId", "requestHeaders", "alternateService", "origin", _UrlKt.FRAGMENT_ENCODE_SET, "protocol", "host", "port", "maxAge", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nHttp2Connection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http2Connection.kt\nokhttp3/internal/http2/Http2Connection$ReaderRunnable\n+ 2 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 -UtilCommon.kt\nokhttp3/internal/_UtilCommonKt\n*L\n1#1,1042:1\n63#2:1043\n63#2:1044\n63#2:1045\n63#2:1048\n63#2:1049\n38#2:1050\n63#2:1051\n63#2:1054\n38#2:1055\n63#2:1056\n37#3,2:1046\n37#3,2:1052\n228#4,5:1057\n*S KotlinDebug\n*F\n+ 1 Http2Connection.kt\nokhttp3/internal/http2/Http2Connection$ReaderRunnable\n*L\n678#1:1043\n757#1:1044\n758#1:1045\n792#1:1048\n809#1:1049\n819#1:1050\n845#1:1051\n864#1:1054\n866#1:1055\n871#1:1056\n775#1:1046,2\n846#1:1052,2\n703#1:1057,5\n*E\n"})
    public final class ReaderRunnable implements Http2Reader.Handler, Function0<Unit> {
        private final Http2Reader reader;
        final /* synthetic */ Http2Connection this$0;

        /* renamed from: $r8$lambda$-_UQnGq5VUgB0kEomNLPoIaKCCk, reason: not valid java name */
        public static /* synthetic */ Unit m3754$r8$lambda$_UQnGq5VUgB0kEomNLPoIaKCCk(Http2Connection r0, Ref.ObjectRef r1) {
            return applyAndAckSettings$lambda$0$0$1(r0, r1);
        }

        public static Unit $r8$lambda$PtXKxbYp71Z79OduJTrwUeJvS08(Http2Connection r1, int r2, int r3) {
            r1.writePing(true, r2, r3);
            return Unit.INSTANCE;
        }

        /* renamed from: $r8$lambda$XZ_EeCBqRdcY-_YZDRWVjMHYQQs, reason: not valid java name */
        public static /* synthetic */ Unit m3755$r8$lambda$XZ_EeCBqRdcY_YZDRWVjMHYQQs(Http2Connection r0, Http2Stream r1) {
            return headers$lambda$0$0(r0, r1);
        }

        public static Unit $r8$lambda$x5ZPvPMHRGx0rtr2nCeHtpvAJmY(ReaderRunnable r0, boolean r1, Settings r2) {
            r0.applyAndAckSettings(r1, r2);
            return Unit.INSTANCE;
        }

        public ReaderRunnable(Http2Connection r1, Http2Reader r2) {
            this.this$0 = r1;
            this.reader = r2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private static final Unit applyAndAckSettings$lambda$0$0$1(Http2Connection r1, Ref.ObjectRef r2) {
            r1.getListener$okhttp().onSettings(r1, (Settings) r2.element);
            return Unit.INSTANCE;
        }

        private static final Unit headers$lambda$0$0(Http2Connection r4, Http2Stream r5) {
            r4.getListener$okhttp().onStream(r5);     // Catch: IOException -> L4
        L8:
            return Unit.INSTANCE;
        L4:
            e = move-exception;
            Platform.Companion.get().log("Http2Connection.Listener failure for " + r4.getConnectionName$okhttp(), 4, e);
            r5.close(ErrorCode.PROTOCOL_ERROR, e);     // Catch: IOException -> L9
            goto L8
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void ackSettings() {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void alternateService(int r1, String r2, ByteString r3, String r4, int r5, long r6) {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r15v1 */
        /* JADX WARN: Type inference failed for: r15v2, types: [T, okhttp3.internal.http2.Settings] */
        /* JADX WARN: Type inference failed for: r15v3 */
        public final void applyAndAckSettings(boolean r14, Settings r15) {
            Ref.ObjectRef r0 = new Ref.ObjectRef();
            Http2Writer r1 = this.this$0.getWriter();
            Http2Connection r13 = this.this$0;
            monitor-enter(r1);
            monitor-enter(r13);     // Catch: Throwable -> L22
            Settings r2 = r13.getPeerSettings();     // Catch: Throwable -> L15
            if (r14 == false) goto L8;
            ?? r152 = r15;
        L9:
            r0.element = r152;     // Catch: Throwable -> L15
            long r142 = r152.getInitialWindowSize() - r2.getInitialWindowSize();     // Catch: Throwable -> L15
            int r3 = 0;
            if (r142 != 0) goto L12;
        L17:
            Http2Stream[] r22 = null;
        L18:
            r13.setPeerSettings((Settings) r0.element);     // Catch: Throwable -> L15
            TaskQueue.execute$default(Http2Connection.access$getSettingsListenerQueue$p(r13), r13.getConnectionName$okhttp() + " onSettings", 0, false, new Http2Connection$ReaderRunnable$$ExternalSyntheticLambda3(r13, r0), 6, null);     // Catch: Throwable -> L15
            Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L15
            monitor-exit(r13);     // Catch: Throwable -> L22
            r13.getWriter().applyAndAckSettings((Settings) r0.element);     // Catch: Throwable -> L22 IOException -> L24
        L26:
            Unit r132 = Unit.INSTANCE;     // Catch: Throwable -> L22
            monitor-exit(r1);
            if (r22 == null) goto L40;
            int r133 = r22.length;
        L30:
            if (r3 >= r133) goto L53;
            Http2Stream r12 = r22[r3];
            monitor-enter(r12);
            r12.addBytesToWriteWindow(r142);     // Catch: Throwable -> L36
            Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L36
            monitor-exit(r12);
            r3 = r3 + 1;
        L36:
            th = move-exception;
            throw th;
        L53:
            return;
        L40:
            return;
        L24:
            e = move-exception;
            Http2Connection.access$failConnection(r13, e);     // Catch: Throwable -> L22
            goto L26
        L12:
            if (r13.getStreams$okhttp().isEmpty() == true) goto L17;
            r22 = (Http2Stream[]) r13.getStreams$okhttp().values().toArray(new Http2Stream[0]);     // Catch: Throwable -> L15
            goto L18
        L8:
            Settings r143 = new Settings();     // Catch: Throwable -> L15
            r143.merge(r2);     // Catch: Throwable -> L15
            r143.merge(r15);     // Catch: Throwable -> L15
            r152 = r143;
        L15:
            th = move-exception;
            throw th;     // Catch: Throwable -> L22
        L22:
            th = move-exception;
            throw th;
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void data(boolean r3, int r4, BufferedSource r5, int r6) {
            boolean r0 = this.this$0.pushedStream$okhttp(r4);
            Http2Connection r1 = this.this$0;
            if (r0 == false) goto L6;
            r1.pushDataLater$okhttp(r4, r5, r6, r3);
            return;
        L6:
            Http2Stream r02 = r1.getStream(r4);
            if (r02 != null) goto L10;
            this.this$0.writeSynResetLater$okhttp(r4, ErrorCode.PROTOCOL_ERROR);
            long r32 = r6;
            this.this$0.updateConnectionFlowControl$okhttp(r32);
            r5.skip(r32);
            return;
        L10:
            r02.receiveData(r5, r6);
            if (r3 == false) goto L14;
            r02.receiveHeaders(Headers.EMPTY, true);
            return;
        }

        public final Http2Reader getReader$okhttp() {
            return this.reader;
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void goAway(int r4, ErrorCode r5, ByteString r6) {
            r6.size();
            Http2Connection r52 = this.this$0;
            monitor-enter(r52);
            int r0 = 0;
            Object[] r62 = r52.getStreams$okhttp().values().toArray(new Http2Stream[0]);     // Catch: Throwable -> L15
            Http2Connection.access$setShutdown$p(r52, true);     // Catch: Throwable -> L15
            Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L15
            monitor-exit(r52);
            Http2Stream[] r63 = (Http2Stream[]) r62;
            int r53 = r63.length;
        L7:
            if (r0 >= r53) goto L14;
            Http2Stream r12 = r63[r0];
            if (r12.getId() <= r4) goto L13;
            if (r12.isLocallyInitiated() == false) goto L13;
            r12.receiveRstStream(ErrorCode.REFUSED_STREAM);
            this.this$0.removeStream$okhttp(r12.getId());
        L13:
            r0 = r0 + 1;
            goto L7
        L14:
            return;
        L15:
            th = move-exception;
            throw th;
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void headers(boolean r12, int r13, int r14, List<Header> r15) {
            boolean r142 = this.this$0.pushedStream$okhttp(r13);
            Http2Connection r2 = this.this$0;
            if (r142 == false) goto L6;
            r2.pushHeadersLater$okhttp(r13, r15, r12);
            return;
        L6:
            monitor-enter(r2);
            Http2Stream r11 = r2.getStream(r13);     // Catch: Throwable -> L24
            if (r11 != null) goto L27;
            if (Http2Connection.access$isShutdown$p(r2) == false) goto L14;
            monitor-exit(r2);
            return;
        L14:
            if (r13 > r2.getLastGoodStreamId$okhttp()) goto L18;
            monitor-exit(r2);
            return;
        L18:
            if ((r13 % 2) != (r2.getNextStreamId$okhttp() % 2)) goto L21;
            monitor-exit(r2);
            return;
        L21:
            Http2Stream r0 = new Http2Stream(r13, r2, false, r12, _UtilJvmKt.toHeaders(r15));     // Catch: Throwable -> L24
            r2.setLastGoodStreamId$okhttp(r13);     // Catch: Throwable -> L24
            r2.getStreams$okhttp().put(Integer.valueOf(r13), r0);     // Catch: Throwable -> L24
            TaskQueue.execute$default(Http2Connection.access$getTaskRunner$p(r2).newQueue(), r2.getConnectionName$okhttp() + '[' + r13 + "] onStream", 0, false, new Http2Connection$ReaderRunnable$$ExternalSyntheticLambda1(r2, r0), 6, null);     // Catch: Throwable -> L24
            monitor-exit(r2);
            return;
        L27:
            Unit r122 = Unit.INSTANCE;     // Catch: Throwable -> L24
            monitor-exit(r2);
            r11.receiveHeaders(_UtilJvmKt.toHeaders(r15), r12);
            return;
        L24:
            th = move-exception;
            throw th;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v3 */
        /* JADX WARN: Type inference failed for: r5v4, types: [java.io.Closeable, okhttp3.internal.http2.Http2Reader] */
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2() {
            ErrorCode r0 = ErrorCode.INTERNAL_ERROR;
            IOException e = null;
            this.reader.readConnectionPreface(this);     // Catch: Throwable -> L15 IOException -> L17
        L5:
            if (this.reader.nextFrame(false, this) == true) goto L5;
            ErrorCode r2 = ErrorCode.NO_ERROR;     // Catch: Throwable -> L15 IOException -> L17
            r0 = ErrorCode.CANCEL;     // Catch: Throwable -> L11 IOException -> L13
            this.this$0.close$okhttp(r2, r0, null);
            ErrorCode r22 = r2;
        L9:
            ReaderRunnable this = this.reader;
            _UtilCommonKt.closeQuietly(this);
            return;
        L13:
            e = e;
        L19:
            r0 = ErrorCode.PROTOCOL_ERROR;     // Catch: Throwable -> L11
            Http2Connection r23 = this.this$0;
            r23.close$okhttp(r0, r0, e);
            r22 = r23;
            goto L9
        L11:
            th = th;
        L21:
            this.this$0.close$okhttp(r22, r0, e);
            _UtilCommonKt.closeQuietly(this.reader);
            throw th;
        L17:
            e = e;
        L15:
            th = th;
            r22 = r0;
            goto L21
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void ping(boolean r11, int r12, int r13) {
            Http2Connection r1 = this.this$0;
            if (r11 == false) goto L21;
            monitor-enter(r1);
            if (r12 != 1) goto L8;
            Http2Connection.access$setIntervalPongsReceived$p(r1, Http2Connection.access$getIntervalPongsReceived$p(r1) + 1);     // Catch: Throwable -> L12
        L17:
            monitor-exit(r1);
            return;
        L8:
            if (r12 != 2) goto L10;
            Http2Connection.access$setDegradedPongsReceived$p(r1, Http2Connection.access$getDegradedPongsReceived$p(r1) + 1);     // Catch: Throwable -> L12
            goto L17
        L10:
            if (r12 == 3) goto L14;
        L11:
            Unit r10 = Unit.INSTANCE;     // Catch: Throwable -> L12
            goto L17
        L14:
            Http2Connection.access$setAwaitPongsReceived$p(r1, Http2Connection.access$getAwaitPongsReceived$p(r1) + 1);     // Catch: Throwable -> L12
            r1.notifyAll();     // Catch: Throwable -> L12
        L12:
            th = move-exception;
            throw th;
        L21:
            TaskQueue.execute$default(Http2Connection.access$getWriterQueue$p(r1), this.this$0.getConnectionName$okhttp() + " ping", 0, false, new Http2Connection$ReaderRunnable$$ExternalSyntheticLambda0(this.this$0, r12, r13), 6, null);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void priority(int r1, int r2, int r3, boolean r4) {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void pushPromise(int r1, int r2, List<Header> r3) {
            this.this$0.pushRequestLater$okhttp(r2, r3);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void rstStream(int r2, ErrorCode r3) {
            boolean r0 = this.this$0.pushedStream$okhttp(r2);
            Http2Connection r1 = this.this$0;
            if (r0 == false) goto L6;
            r1.pushResetLater$okhttp(r2, r3);
            return;
        L6:
            Http2Stream r12 = r1.removeStream$okhttp(r2);
            if (r12 == null) goto L10;
            r12.receiveRstStream(r3);
            return;
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void settings(boolean r10, Settings r11) {
            TaskQueue.execute$default(Http2Connection.access$getWriterQueue$p(this.this$0), this.this$0.getConnectionName$okhttp() + " applyAndAckSettings", 0, false, new Http2Connection$ReaderRunnable$$ExternalSyntheticLambda2(this, r10, r11), 6, null);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void windowUpdate(int r3, long r4) {
            Http2Connection r2 = this.this$0;
            if (r3 != 0) goto L11;
            monitor-enter(r2);
            Http2Connection.access$setWriteBytesMaximum$p(r2, r2.getWriteBytesMaximum() + r4);     // Catch: Throwable -> L8
            r2.notifyAll();     // Catch: Throwable -> L8
            Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L8
            monitor-exit(r2);
            return;
        L8:
            th = move-exception;
            throw th;
        L11:
            Http2Stream r22 = r2.getStream(r3);
            if (r22 == null) goto L20;
            monitor-enter(r22);
            r22.addBytesToWriteWindow(r4);     // Catch: Throwable -> L17
            Unit r33 = Unit.INSTANCE;     // Catch: Throwable -> L17
            monitor-exit(r22);
            return;
        L17:
            th = move-exception;
            throw th;
        }
    }

    public static Unit $r8$lambda$BmmSWn00knWMLDPauw0U0_44Io0(Http2Connection r1, int r2, List r3) {
        if (r1.pushObserver.onRequest(r2, r3) == false) goto L13;
        r1.writer.rstStream(r2, ErrorCode.CANCEL);     // Catch: IOException -> L14
        monitor-enter(r1);     // Catch: IOException -> L14
        r1.currentPushRequests.remove(Integer.valueOf(r2));     // Catch: Throwable -> L9
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: IOException -> L14
        goto L13
    L9:
        th = move-exception;
        throw th;     // Catch: IOException -> L14
    L13:
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$IqUIVMAEhaATpKLfgBtHeD0nKgM(Http2Connection r1, int r2, ErrorCode r3) {
        r1.pushObserver.onReset(r2, r3);
        monitor-enter(r1);
        r1.currentPushRequests.remove(Integer.valueOf(r2));     // Catch: Throwable -> L8
        monitor-exit(r1);
        return Unit.INSTANCE;
    L8:
        th = move-exception;
        throw th;
    }

    /* renamed from: $r8$lambda$RDrtX_1LOc8QQSkh6EyfS-ti1CI, reason: not valid java name */
    public static Unit m3752$r8$lambda$RDrtX_1LOc8QQSkh6EyfSti1CI(Http2Connection r1, int r2, long r3) {
        r1.writer.windowUpdate(r2, r3);     // Catch: IOException -> L4
    L7:
        return Unit.INSTANCE;
    L4:
        e = move-exception;
        r1.failConnection(e);
        goto L7
    }

    /* renamed from: $r8$lambda$VZEphnhi3biD6zkwtmVY-JPKUDc, reason: not valid java name */
    public static Unit m3753$r8$lambda$VZEphnhi3biD6zkwtmVYJPKUDc(Http2Connection r1, int r2, Buffer r3, int r4, boolean r5) {
        boolean r32 = r1.pushObserver.onData(r2, r3, r4, r5);     // Catch: IOException -> L16
        if (r32 == false) goto L5;
        r1.writer.rstStream(r2, ErrorCode.CANCEL);     // Catch: IOException -> L16
    L5:
        if (r32 == true) goto L7;
        if (r5 == false) goto L15;
    L7:
        monitor-enter(r1);     // Catch: IOException -> L16
        r1.currentPushRequests.remove(Integer.valueOf(r2));     // Catch: Throwable -> L11
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r1);     // Catch: IOException -> L16
        goto L15
    L11:
        th = move-exception;
        throw th;     // Catch: IOException -> L16
    L15:
        return Unit.INSTANCE;
    }

    public static long $r8$lambda$Z28XoadYOXbYtyd3_4baGfxpKWM(Http2Connection r7, long r8) {
        monitor-enter(r7);
        long r0 = r7.intervalPongsReceived;     // Catch: Throwable -> L14
        long r2 = r7.intervalPingsSent;     // Catch: Throwable -> L14
        if (r0 >= r2) goto L6;
        boolean r02 = true;
    L8:
        monitor-exit(r7);
        if (r02 == false) goto L12;
        r7.failConnection(null);
        return -1;
    L12:
        r7.writePing(false, 1, 0);
        return r8;
    L6:
        r7.intervalPingsSent = r2 + 1;     // Catch: Throwable -> L14
        r02 = false;
    L14:
        th = move-exception;
        throw th;
    }

    public static Unit $r8$lambda$bwIaywCJy9lPsamLQkcJJPFkoMA(Http2Connection r2, int r3, List r4, boolean r5) {
        boolean r42 = r2.pushObserver.onHeaders(r3, r4, r5);
        if (r42 == true) goto L19;
    L5:
        if (r42 == true) goto L7;
        if (r5 == true) goto L7;
    L15:
        return Unit.INSTANCE;
    L7:
        monitor-enter(r2);     // Catch: IOException -> L16
        r2.currentPushRequests.remove(Integer.valueOf(r3));     // Catch: Throwable -> L11
        Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r2);     // Catch: IOException -> L16
        goto L15
    L11:
        th = move-exception;
        throw th;     // Catch: IOException -> L16
    L19:
        r2.writer.rstStream(r3, ErrorCode.CANCEL);     // Catch: IOException -> L16
        goto L5
    }

    public static Unit $r8$lambda$cu_a_P82SDW32jURw83_HhRGWmE(Http2Connection r2) {
        r2.writePing(false, 2, 0);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$ztxaN52Zhu5JliTkHluCVoFr0Rw(Http2Connection r0, int r1, ErrorCode r2) {
        r0.writeSynReset$okhttp(r1, r2);     // Catch: IOException -> L4
    L7:
        return Unit.INSTANCE;
    L4:
        e = move-exception;
        r0.failConnection(e);
        goto L7
    }

    static {
        Companion = new Companion(null);
        Settings r0 = new Settings();
        r0.set(4, 65535);
        r0.set(5, 16384);
        DEFAULT_SETTINGS = r0;
    }

    public Http2Connection(Builder r7) {
        boolean r0 = r7.getClient$okhttp();
        this.client = r0;
        this.listener = r7.getListener$okhttp();
        this.streams = new LinkedHashMap();
        String r1 = r7.getConnectionName$okhttp();
        this.connectionName = r1;
        if (r7.getClient$okhttp() == false) goto L5;
        int r2 = 3;
    L6:
        this.nextStreamId = r2;
        TaskRunner r22 = r7.getTaskRunner$okhttp();
        this.taskRunner = r22;
        TaskQueue r3 = r22.newQueue();
        this.writerQueue = r3;
        this.pushQueue = r22.newQueue();
        this.settingsListenerQueue = r22.newQueue();
        this.pushObserver = r7.getPushObserver$okhttp();
        this.flowControlListener = r7.getFlowControlListener$okhttp();
        Settings r23 = new Settings();
        if (r7.getClient$okhttp() == false) goto L9;
        r23.set(4, 16777216);
    L9:
        this.okHttpSettings = r23;
        this.peerSettings = DEFAULT_SETTINGS;
        this.readBytes = new WindowCounter(0);
        this.writeBytesMaximum = this.peerSettings.getInitialWindowSize();
        BufferedSocket r24 = r7.getSocket$okhttp();
        this.socket = r24;
        this.writer = new Http2Writer(r24.getSink(), r0);
        this.readerRunnable = new ReaderRunnable(this, new Http2Reader(r24.getSource(), r0));
        this.currentPushRequests = new LinkedHashSet();
        if (r7.getPingIntervalMillis$okhttp() == 0) goto L13;
        long r4 = TimeUnit.MILLISECONDS.toNanos(r7.getPingIntervalMillis$okhttp());
        r3.schedule(r1 + " ping", r4, new Http2Connection$$ExternalSyntheticLambda1(this, r4));
        return;
    L13:
        return;
    L5:
        r2 = 2;
        goto L6
    }

    public static final /* synthetic */ void access$failConnection(Http2Connection r0, IOException r1) {
        r0.failConnection(r1);
    }

    public static final /* synthetic */ long access$getAwaitPongsReceived$p(Http2Connection r2) {
        return r2.awaitPongsReceived;
    }

    public static final /* synthetic */ Settings access$getDEFAULT_SETTINGS$cp() {
        return DEFAULT_SETTINGS;
    }

    public static final /* synthetic */ long access$getDegradedPongsReceived$p(Http2Connection r2) {
        return r2.degradedPongsReceived;
    }

    public static final /* synthetic */ long access$getIntervalPongsReceived$p(Http2Connection r2) {
        return r2.intervalPongsReceived;
    }

    public static final /* synthetic */ TaskQueue access$getSettingsListenerQueue$p(Http2Connection r0) {
        return r0.settingsListenerQueue;
    }

    public static final /* synthetic */ TaskRunner access$getTaskRunner$p(Http2Connection r0) {
        return r0.taskRunner;
    }

    public static final /* synthetic */ TaskQueue access$getWriterQueue$p(Http2Connection r0) {
        return r0.writerQueue;
    }

    public static final /* synthetic */ boolean access$isShutdown$p(Http2Connection r0) {
        return r0.isShutdown;
    }

    public static final /* synthetic */ void access$setAwaitPongsReceived$p(Http2Connection r0, long r1) {
        r0.awaitPongsReceived = r1;
    }

    public static final /* synthetic */ void access$setDegradedPongsReceived$p(Http2Connection r0, long r1) {
        r0.degradedPongsReceived = r1;
    }

    public static final /* synthetic */ void access$setIntervalPongsReceived$p(Http2Connection r0, long r1) {
        r0.intervalPongsReceived = r1;
    }

    public static final /* synthetic */ void access$setShutdown$p(Http2Connection r0, boolean r1) {
        r0.isShutdown = r1;
    }

    public static final /* synthetic */ void access$setWriteBytesMaximum$p(Http2Connection r0, long r1) {
        r0.writeBytesMaximum = r1;
    }

    private final void failConnection(IOException r2) {
        ErrorCode r0 = ErrorCode.PROTOCOL_ERROR;
        close$okhttp(r0, r0, r2);
    }

    private final Http2Stream newStream(int r10, List<Header> r11, boolean r12) {
        boolean r3 = !r12;
        Http2Writer r6 = this.writer;
        monitor-enter(r6);
        monitor-enter(this);     // Catch: Throwable -> L34
    L45:
        th = th;
        Http2Connection r2 = this;
    L25:
        Throwable r102 = th;
    L50:
        monitor-exit(r2);     // Catch: Throwable -> L34
        throw r102;     // Catch: Throwable -> L34
    L7:
        if (this.nextStreamId <= 1073741823) goto L55;
        shutdown(ErrorCode.REFUSED_STREAM);     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        r102 = th;
        r2 = this;
    L55:
        if (this.isShutdown == true) goto L49;
        int r1 = this.nextStreamId;     // Catch: Throwable -> L45
        this.nextStreamId = r1 + 2;     // Catch: Throwable -> L45
        Http2Stream r0 = new Http2Stream(r1, this, r3, false, null);     // Catch: Throwable -> L24
        if (r12 == true) goto L19;
    L26:
        boolean r9 = true;
    L28:
        if (r0.isOpen() == false) goto L30;
        this.streams.put(Integer.valueOf(r1), r0);     // Catch: Throwable -> L24
    L30:
        Unit r122 = Unit.INSTANCE;     // Catch: Throwable -> L24
        monitor-exit(this);     // Catch: Throwable -> L34
        if (r10 != 0) goto L37;
        this.writer.headers(r3, r1, r11);     // Catch: Throwable -> L34
    L39:
        monitor-exit(r6);
        if (r9 == false) goto L42;
        this.writer.flush();
    L42:
        return r0;
    L37:
        if (this.client == true) goto L44;
        this.writer.pushPromise(r10, r1, r11);     // Catch: Throwable -> L34
        goto L39
    L44:
        throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");     // Catch: Throwable -> L34
    L19:
        if (this.writeBytesTotal >= this.writeBytesMaximum) goto L26;
        if (r0.getWriteBytesTotal() >= r0.getWriteBytesMaximum()) goto L26;
        r9 = false;
        goto L28
    L49:
        throw new ConnectionShutdownException();     // Catch: Throwable -> L24
    L24:
        th = th;
    L34:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ void start$default(Http2Connection r0, boolean r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = true;
    L5:
        r0.start(r1);
    }

    public final void awaitPong() {
        monitor-enter(this);
    L13:
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.awaitPongsReceived >= this.awaitPingsSent) goto L8;
        wait();     // Catch: Throwable -> L6
        goto L13
    L8:
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L6
        monitor-exit(this);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        close$okhttp(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
    }

    public final void close$okhttp(ErrorCode r4, ErrorCode r5, IOException r6) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L44:
        shutdown(r4);     // Catch: IOException -> L32
    L10:
        monitor-enter(this);
        int r0 = 0;
        if (this.streams.isEmpty() == true) goto L16;
        Object[] r42 = this.streams.values().toArray(new Http2Stream[0]);     // Catch: Throwable -> L14
        this.streams.clear();     // Catch: Throwable -> L14
    L17:
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(this);
        Http2Stream[] r43 = (Http2Stream[]) r42;
        if (r43 == null) goto L42;
        int r12 = r43.length;
    L22:
        if (r0 >= r12) goto L42;
        r43[r0].close(r5, r6);     // Catch: IOException -> L33
    L25:
        r0 = r0 + 1;
    L42:
        this.writer.close();     // Catch: IOException -> L34
    L38:
        this.socket.cancel();     // Catch: IOException -> L35
    L28:
        this.writerQueue.shutdown();
        this.pushQueue.shutdown();
        this.settingsListenerQueue.shutdown();
        return;
    L16:
        r42 = null;
    L14:
        th = move-exception;
        throw th;
    L5:
        if (Thread.holdsLock(this) == false) goto L44;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", this);
    }

    public final void flush() {
        this.writer.flush();
    }

    public final boolean getClient$okhttp() {
        return this.client;
    }

    public final String getConnectionName$okhttp() {
        return this.connectionName;
    }

    public final FlowControlListener getFlowControlListener$okhttp() {
        return this.flowControlListener;
    }

    public final int getLastGoodStreamId$okhttp() {
        return this.lastGoodStreamId;
    }

    public final Listener getListener$okhttp() {
        return this.listener;
    }

    public final int getNextStreamId$okhttp() {
        return this.nextStreamId;
    }

    public final Settings getOkHttpSettings() {
        return this.okHttpSettings;
    }

    public final Settings getPeerSettings() {
        return this.peerSettings;
    }

    public final WindowCounter getReadBytes() {
        return this.readBytes;
    }

    public final ReaderRunnable getReaderRunnable() {
        return this.readerRunnable;
    }

    public final BufferedSocket getSocket$okhttp() {
        return this.socket;
    }

    public final Http2Stream getStream(int r2) {
        monitor-enter(this);
        Http2Stream r22 = this.streams.get(Integer.valueOf(r2));     // Catch: Throwable -> L6
        monitor-exit(this);
        return r22;
    L6:
        th = move-exception;
        throw th;
    }

    public final Map<Integer, Http2Stream> getStreams$okhttp() {
        return this.streams;
    }

    public final long getWriteBytesMaximum() {
        return this.writeBytesMaximum;
    }

    public final long getWriteBytesTotal() {
        return this.writeBytesTotal;
    }

    public final Http2Writer getWriter() {
        return this.writer;
    }

    public final boolean isHealthy(long r7) {
        monitor-enter(this);
    L15:
        th = move-exception;
        throw th;
    L5:
        if (this.isShutdown == false) goto L9;
        monitor-exit(this);
        return false;
    L9:
        if (this.degradedPongsReceived >= this.degradedPingsSent) goto L17;
        if (r7 < this.degradedPongDeadlineNs) goto L17;
        monitor-exit(this);
        return false;
    L17:
        monitor-exit(this);
        return true;
    }

    public final Http2Stream newStream(List<Header> r2, boolean r3) {
        return newStream(0, r2, r3);
    }

    public final int openStreamCount() {
        monitor-enter(this);
        int r0 = this.streams.size();     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final void pushDataLater$okhttp(int r13, BufferedSource r14, int r15, boolean r16) {
        Buffer r3 = new Buffer();
        long r1 = r15;
        r14.require(r1);
        r14.read(r3, r1);
        TaskQueue.execute$default(this.pushQueue, this.connectionName + '[' + r13 + "] onData", 0, false, new Http2Connection$$ExternalSyntheticLambda0(this, r13, r3, r15, r16), 6, null);
    }

    public final void pushHeadersLater$okhttp(int r9, List<Header> r10, boolean r11) {
        TaskQueue.execute$default(this.pushQueue, this.connectionName + '[' + r9 + "] onHeaders", 0, false, new Http2Connection$$ExternalSyntheticLambda6(this, r9, r10, r11), 6, null);
    }

    public final void pushRequestLater$okhttp(int r11, List<Header> r12) {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (this.currentPushRequests.contains(Integer.valueOf(r11)) == false) goto L10;
        writeSynResetLater$okhttp(r11, ErrorCode.PROTOCOL_ERROR);     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L10:
        this.currentPushRequests.add(Integer.valueOf(r11));     // Catch: Throwable -> L8
        monitor-exit(this);
        TaskQueue.execute$default(this.pushQueue, this.connectionName + '[' + r11 + "] onRequest", 0, false, new Http2Connection$$ExternalSyntheticLambda5(this, r11, r12), 6, null);
    }

    public final void pushResetLater$okhttp(int r9, ErrorCode r10) {
        TaskQueue.execute$default(this.pushQueue, this.connectionName + '[' + r9 + "] onReset", 0, false, new Http2Connection$$ExternalSyntheticLambda7(this, r9, r10), 6, null);
    }

    public final Http2Stream pushStream(int r2, List<Header> r3, boolean r4) {
        if (this.client == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Client cannot push requests.");
        return null;
    L5:
        return newStream(r2, r3, r4);
    }

    public final boolean pushedStream$okhttp(int r1) {
        if (r1 != 0) goto L4;
        return false;
    L4:
        if ((r1 & 1) != 0) goto L8;
        return true;
    L8:
        return false;
    }

    public final Http2Stream removeStream$okhttp(int r2) {
        monitor-enter(this);
        Http2Stream r22 = this.streams.remove(Integer.valueOf(r2));     // Catch: Throwable -> L6
        notifyAll();     // Catch: Throwable -> L6
        monitor-exit(this);
        return r22;
    L6:
        th = move-exception;
        throw th;
    }

    public final void sendDegradedPingLater$okhttp() {
        monitor-enter(this);
        long r0 = this.degradedPongsReceived;     // Catch: Throwable -> L13
        long r2 = this.degradedPingsSent;     // Catch: Throwable -> L13
        if (r0 >= r2) goto L9;
        monitor-exit(this);
        return;
    L9:
        this.degradedPingsSent = r2 + 1;     // Catch: Throwable -> L13
        this.degradedPongDeadlineNs = System.nanoTime() + 1000000000;     // Catch: Throwable -> L13
        Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(this);
        TaskQueue.execute$default(this.writerQueue, this.connectionName + " ping", 0, false, new Http2Connection$$ExternalSyntheticLambda3(this), 6, null);
        return;
    L13:
        th = move-exception;
        throw th;
    }

    public final void setLastGoodStreamId$okhttp(int r1) {
        this.lastGoodStreamId = r1;
    }

    public final void setNextStreamId$okhttp(int r1) {
        this.nextStreamId = r1;
    }

    public final void setPeerSettings(Settings r1) {
        this.peerSettings = r1;
    }

    public final void setSettings(Settings r3) {
        Http2Writer r0 = this.writer;
        monitor-enter(r0);
        monitor-enter(this);     // Catch: Throwable -> L12
    L14:
        th = move-exception;
        throw th;     // Catch: Throwable -> L12
    L6:
        if (this.isShutdown == true) goto L17;
        this.okHttpSettings.merge(r3);     // Catch: Throwable -> L14
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(this);     // Catch: Throwable -> L12
        this.writer.settings(r3);     // Catch: Throwable -> L12
        monitor-exit(r0);
        return;
    L17:
        throw new ConnectionShutdownException();     // Catch: Throwable -> L14
    L12:
        th = move-exception;
        throw th;
    }

    public final void shutdown(ErrorCode r4) {
        Http2Writer r0 = this.writer;
        monitor-enter(r0);
        monitor-enter(this);     // Catch: Throwable -> L10
    L18:
        th = move-exception;
        throw th;     // Catch: Throwable -> L10
    L6:
        if (this.isShutdown == false) goto L13;
        monitor-exit(this);     // Catch: Throwable -> L10
        monitor-exit(r0);
        return;
    L13:
        this.isShutdown = true;     // Catch: Throwable -> L18
        int r1 = this.lastGoodStreamId;     // Catch: Throwable -> L18
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L18
        monitor-exit(this);     // Catch: Throwable -> L10
        this.writer.goAway(r1, r4, _UtilCommonKt.EMPTY_BYTE_ARRAY);     // Catch: Throwable -> L10
        monitor-exit(r0);
        return;
    L10:
        th = move-exception;
        throw th;
    }

    @JvmOverloads
    public final void start() {
        start$default(this, false, 1, null);
    }

    @JvmOverloads
    public final void start(boolean r9) {
        if (r9 == false) goto L6;
        this.writer.connectionPreface();
        this.writer.settings(this.okHttpSettings);
        if (this.okHttpSettings.getInitialWindowSize() == 65535) goto L6;
        this.writer.windowUpdate(0, r9 - 65535);
    L6:
        TaskQueue.execute$default(this.taskRunner.newQueue(), this.connectionName, 0, false, this.readerRunnable, 6, null);
    }

    public final void updateConnectionFlowControl$okhttp(long r8) {
        monitor-enter(this);
        WindowCounter.update$default(this.readBytes, r8, 0, 2, null);     // Catch: Throwable -> L6
        long r3 = this.readBytes.getUnacknowledged();     // Catch: Throwable -> L6
        if (r3 < (this.okHttpSettings.getInitialWindowSize() / 2)) goto L8;
        writeWindowUpdateLater$okhttp(0, r3);     // Catch: Throwable -> L6
        WindowCounter.update$default(this.readBytes, 0, r3, 1, null);     // Catch: Throwable -> L6
    L8:
        this.flowControlListener.receivingConnectionWindowChanged(this.readBytes);     // Catch: Throwable -> L6
        Unit r82 = Unit.INSTANCE;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public final void writeData(int r9, boolean r10, Buffer r11, long r12) {
        if (r12 != 0) goto L7;
        this.writer.data(r10, r9, r11, 0);
        return;
    L7:
        if (r12 <= 0) goto L32;
        monitor-enter(this);
    L33:
        long r4 = this.writeBytesTotal;     // Catch: Throwable -> L14 InterruptedException -> L28
        long r6 = this.writeBytesMaximum;     // Catch: Throwable -> L14 InterruptedException -> L28
        if (r4 < r6) goto L19;
        if (this.streams.containsKey(Integer.valueOf(r9)) == false) goto L17;
        wait();     // Catch: Throwable -> L14 InterruptedException -> L28
        goto L33
    L17:
        throw new IOException("stream closed");     // Catch: Throwable -> L14 InterruptedException -> L28
    L19:
        int r2 = Math.min((int) Math.min(r12, r6 - r4), this.writer.maxDataLength());     // Catch: Throwable -> L14
        long r62 = r2;
        this.writeBytesTotal += r62;
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(this);
        r12 = r12 - r62;
        Http2Writer r43 = this.writer;
        if (r10 == true) goto L24;
    L26:
        boolean r5 = false;
    L27:
        r43.data(r5, r9, r11, r2);
        goto L7
    L24:
        if (r12 != 0) goto L26;
        r5 = true;
    L28:
        Thread.currentThread().interrupt();     // Catch: Throwable -> L14
        throw new InterruptedIOException();     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        throw th;
    }

    public final void writeHeaders$okhttp(int r1, boolean r2, List<Header> r3) {
        this.writer.headers(r2, r1, r3);
    }

    public final void writePing() {
        monitor-enter(this);
        this.awaitPingsSent++;
        monitor-exit(this);
        writePing(false, 3, 1330343787);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void writePing(boolean r2, int r3, int r4) {
        this.writer.ping(r2, r3, r4);     // Catch: IOException -> L4
        return;
    L4:
        e = move-exception;
        failConnection(e);
    }

    public final void writePingAndAwaitPong() {
        writePing();
        awaitPong();
    }

    public final void writeSynReset$okhttp(int r1, ErrorCode r2) {
        this.writer.rstStream(r1, r2);
    }

    public final void writeSynResetLater$okhttp(int r9, ErrorCode r10) {
        TaskQueue.execute$default(this.writerQueue, this.connectionName + '[' + r9 + "] writeSynReset", 0, false, new Http2Connection$$ExternalSyntheticLambda4(this, r9, r10), 6, null);
    }

    public final void writeWindowUpdateLater$okhttp(int r9, long r10) {
        TaskQueue.execute$default(this.writerQueue, this.connectionName + '[' + r9 + "] windowUpdate", 0, false, new Http2Connection$$ExternalSyntheticLambda2(this, r9, r10), 6, null);
    }
}
