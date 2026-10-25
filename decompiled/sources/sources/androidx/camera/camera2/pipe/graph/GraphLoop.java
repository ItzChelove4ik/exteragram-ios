package androidx.camera.camera2.pipe.graph;

import androidx.camera.camera2.pipe.CameraGraphId;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestsKt;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.ProcessingQueue;
import androidx.camera.camera2.pipe.graph.GraphCommand;
import java.io.Closeable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b&\b\u0000\u0018\u0000 \u0085\u00012\u00020\u0001:\u0004\u0086\u0001\u0085\u0001Bc\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u001e\u0010\u0017\u001a\u00020\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0082@¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u001a\u001a\u00020\u00192\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ7\u0010!\u001a\u00020\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b!\u0010\"J-\u0010$\u001a\u00020\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020#H\u0002¢\u0006\u0004\b$\u0010%J/\u0010'\u001a\u00020\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001c\u001a\u00020\u00192\b\b\u0002\u0010&\u001a\u00020\u001fH\u0002¢\u0006\u0004\b'\u0010(J-\u0010*\u001a\u00020\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020)H\u0002¢\u0006\u0004\b*\u0010+J-\u0010-\u001a\u00020\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020,H\u0002¢\u0006\u0004\b-\u0010.J.\u00100\u001a\u00020\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020/H\u0082@¢\u0006\u0004\b0\u00101J%\u00102\u001a\u00020\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001c\u001a\u00020\u0019H\u0002¢\u0006\u0004\b2\u00103J%\u00104\u001a\u00020\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001c\u001a\u00020\u0019H\u0002¢\u0006\u0004\b4\u00103J\u001e\u00105\u001a\u00020\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0082@¢\u0006\u0004\b5\u0010\u0018J\u000f\u00106\u001a\u00020\u001fH\u0002¢\u0006\u0004\b6\u00107J\u001d\u0010:\u001a\u00020\u00162\f\u00109\u001a\b\u0012\u0004\u0012\u0002080\bH\u0002¢\u0006\u0004\b:\u0010;J\u001d\u0010=\u001a\u00020\u00162\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00140\bH\u0002¢\u0006\u0004\b=\u0010;J;\u0010@\u001a\u00020\u001f2\u0006\u0010>\u001a\u00020\u001f2\f\u00109\u001a\b\u0012\u0004\u0012\u0002080\b2\u0014\b\u0002\u0010?\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004H\u0002¢\u0006\u0004\b@\u0010AJ\u001b\u0010B\u001a\u00020\u001f2\f\u00109\u001a\b\u0012\u0004\u0012\u0002080\b¢\u0006\u0004\bB\u0010CJ!\u0010E\u001a\u00020\u001f2\u0012\u0010D\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\u0004\bE\u0010FJ\r\u0010G\u001a\u00020\u0016¢\u0006\u0004\bG\u0010HJ\u000f\u0010I\u001a\u00020\u0016H\u0016¢\u0006\u0004\bI\u0010HJ\u000f\u0010K\u001a\u00020JH\u0016¢\u0006\u0004\bK\u0010LR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010MR \u0010\u0006\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010NR \u0010\u0007\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010NR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010OR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010OR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010PR\u0014\u0010Q\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010PR\u001a\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00140R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010TR\u0014\u0010U\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010VR\u0016\u0010W\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bW\u0010XR\u0018\u0010Z\u001a\u0004\u0018\u00010Y8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bZ\u0010[R\u0018\u0010\\\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R\"\u0010^\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b^\u0010NR\"\u0010_\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b_\u0010NR\u001c\u0010`\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b`\u0010OR\u0014\u0010b\u001a\u00020a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bb\u0010cR\u0018\u0010d\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bd\u0010]R\"\u0010e\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\be\u0010NR\"\u0010f\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bf\u0010NR\"\u0010g\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bg\u0010NR\u001c\u0010h\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bh\u0010OR\u0018\u0010i\u001a\u0004\u0018\u00010Y8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bi\u0010[R(\u0010o\u001a\u0004\u0018\u00010Y2\b\u0010j\u001a\u0004\u0018\u00010Y8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bk\u0010l\"\u0004\bm\u0010nR(\u0010t\u001a\u0004\u0018\u0001082\b\u0010j\u001a\u0004\u0018\u0001088F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bp\u0010q\"\u0004\br\u0010sR<\u0010y\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0012\u0010j\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bu\u0010v\"\u0004\bw\u0010xR<\u0010|\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0012\u0010j\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bz\u0010v\"\u0004\b{\u0010xR1\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020\t0\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b}\u0010~\"\u0004\b\u007f\u0010;R(\u0010\u0084\u0001\u001a\u00020\u001f2\u0006\u0010j\u001a\u00020\u001f8F@FX\u0086\u000e¢\u0006\u000f\u001a\u0005\b\u0081\u0001\u00107\"\u0006\b\u0082\u0001\u0010\u0083\u0001¨\u0006\u0087\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphLoop;", "Ljava/io/Closeable;", "Landroidx/camera/camera2/pipe/CameraGraphId;", "cameraGraphId", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "defaultParameters", "requiredParameters", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request$Listener;", "requiredListeners", "Landroidx/camera/camera2/pipe/graph/GraphLoop$Listener;", "listeners", "Lkotlinx/coroutines/CoroutineScope;", "shutdownScope", "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher", "<init>", "(Landroidx/camera/camera2/pipe/CameraGraphId;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Ljava/util/List;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineDispatcher;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/graph/GraphCommand;", "commands", _UrlKt.FRAGMENT_ENCODE_SET, "process", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "selectGraphCommand", "(Ljava/util/List;)I", "idx", "Landroidx/camera/camera2/pipe/graph/GraphCommand$Capture;", "command", _UrlKt.FRAGMENT_ENCODE_SET, "repeatAllowed", "processCapture", "(Ljava/util/List;ILandroidx/camera/camera2/pipe/graph/GraphCommand$Capture;Z)V", "Landroidx/camera/camera2/pipe/graph/GraphCommand$Trigger;", "processTrigger", "(Ljava/util/List;ILandroidx/camera/camera2/pipe/graph/GraphCommand$Trigger;)V", "captureAllowed", "processRepeat", "(Ljava/util/List;IZ)V", "Landroidx/camera/camera2/pipe/graph/GraphCommand$Parameters;", "processParameters", "(Ljava/util/List;ILandroidx/camera/camera2/pipe/graph/GraphCommand$Parameters;)V", "Landroidx/camera/camera2/pipe/graph/GraphCommand$Listeners;", "processListeners", "(Ljava/util/List;ILandroidx/camera/camera2/pipe/graph/GraphCommand$Listeners;)V", "Landroidx/camera/camera2/pipe/graph/GraphCommand$RequestProcessor;", "processRequestProcessor", "(Ljava/util/List;ILandroidx/camera/camera2/pipe/graph/GraphCommand$RequestProcessor;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processStop", "(Ljava/util/List;I)V", "processAbort", "processShutdown", "reissueRepeatingRequest", "()Z", "Landroidx/camera/camera2/pipe/Request;", "requests", "abortRequests", "(Ljava/util/List;)V", "unprocessedCommands", "finalizeUnprocessedCommands", "isRepeating", "oneTimeRequiredParameters", "buildAndSubmit", "(ZLjava/util/List;Ljava/util/Map;)Z", "submit", "(Ljava/util/List;)Z", "parameters", "trigger", "(Ljava/util/Map;)Z", "invalidate", "()V", "close", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/camera/camera2/pipe/CameraGraphId;", "Ljava/util/Map;", "Ljava/util/List;", "Lkotlinx/coroutines/CoroutineScope;", "graphLoopScope", "Landroidx/camera/camera2/pipe/core/ProcessingQueue;", "processingQueue", "Landroidx/camera/camera2/pipe/core/ProcessingQueue;", "lock", "Ljava/lang/Object;", "closed", "Z", "Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;", "_requestProcessor", "Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;", "_repeatingRequest", "Landroidx/camera/camera2/pipe/Request;", "_graphParameters", "_graph3AParameters", "_requestListeners", "Lkotlinx/atomicfu/AtomicBoolean;", "_captureProcessingEnabled", "Lkotlinx/atomicfu/AtomicBoolean;", "currentRepeatingRequest", "currentGraphParameters", "currentGraph3AParameters", "currentRequiredParameters", "currentRequestListeners", "currentRequestProcessor", "value", "getRequestProcessor", "()Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;", "setRequestProcessor", "(Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;)V", "requestProcessor", "getRepeatingRequest", "()Landroidx/camera/camera2/pipe/Request;", "setRepeatingRequest", "(Landroidx/camera/camera2/pipe/Request;)V", "repeatingRequest", "getGraphParameters", "()Ljava/util/Map;", "setGraphParameters", "(Ljava/util/Map;)V", "graphParameters", "getGraph3AParameters", "setGraph3AParameters", "graph3AParameters", "getRequestListeners", "()Ljava/util/List;", "setRequestListeners", "requestListeners", "getCaptureProcessingEnabled", "setCaptureProcessingEnabled", "(Z)V", "captureProcessingEnabled", "Companion", "Listener", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGraphLoop.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GraphLoop.kt\nandroidx/camera/camera2/pipe/graph/GraphLoop\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 GraphLoop.kt\nandroidx/camera/camera2/pipe/graph/GraphLoop$Companion\n+ 4 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,731:1\n1#2:732\n694#3,11:733\n694#3,11:744\n694#3,11:755\n694#3,11:766\n694#3,11:777\n694#3,11:788\n71#4,2:799\n71#4,2:801\n71#4,2:803\n*S KotlinDebug\n*F\n+ 1 GraphLoop.kt\nandroidx/camera/camera2/pipe/graph/GraphLoop\n*L\n417#1:733,11\n453#1:744,11\n465#1:755,11\n476#1:766,11\n517#1:777,11\n535#1:788,11\n668#1:799,2\n671#1:801,2\n673#1:803,2\n*E\n"})
/* loaded from: classes3.dex */
public final class GraphLoop implements Closeable {
    public static final Companion Companion = null;
    private final AtomicBoolean _captureProcessingEnabled;
    private Map<?, ? extends Object> _graph3AParameters;
    private Map<?, ? extends Object> _graphParameters;
    private Request _repeatingRequest;
    private List<? extends Request.Listener> _requestListeners;
    private GraphRequestProcessor _requestProcessor;
    private final CameraGraphId cameraGraphId;
    private volatile boolean closed;
    private Map<?, ? extends Object> currentGraph3AParameters;
    private Map<?, ? extends Object> currentGraphParameters;
    private Request currentRepeatingRequest;
    private List<? extends Request.Listener> currentRequestListeners;
    private GraphRequestProcessor currentRequestProcessor;
    private Map<?, ? extends Object> currentRequiredParameters;
    private final Map<?, Object> defaultParameters;
    private final CoroutineScope graphLoopScope;
    private final List<Listener> listeners;
    private final Object lock;
    private final ProcessingQueue<GraphCommand> processingQueue;
    private final List<Request.Listener> requiredListeners;
    private final Map<?, Object> requiredParameters;
    private final CoroutineScope shutdownScope;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphLoop$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphLoop$Listener;", _UrlKt.FRAGMENT_ENCODE_SET, "onStopRepeating", _UrlKt.FRAGMENT_ENCODE_SET, "onGraphStopped", "onGraphShutdown", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Listener {
        void onGraphShutdown();

        void onGraphStopped();

        void onStopRepeating();
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.graph.GraphLoop$finalizeUnprocessedCommands$1", f = "GraphLoop.kt", i = {}, l = {630, 631}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.graph.GraphLoop$finalizeUnprocessedCommands$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ GraphCommand $command;
        int label;

        public AnonymousClass1(GraphCommand r1, Continuation<? super AnonymousClass1> r2) {
            this.$command = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass1(this.$command, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r5) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L10;
            if (r1 == 1) goto L9;
            if (r1 != 2) goto L7;
            ResultKt.throwOnFailure(r5);
        L21:
            return Unit.INSTANCE;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r5);
        L15:
            GraphRequestProcessor r52 = ((GraphCommand.RequestProcessor) this.$command).getNew();
            if (r52 == null) goto L21;
            this.label = 2;
            if (r52.shutdown$camera_camera2_pipe(this) != r0) goto L21;
        L19:
            return r0;
        L10:
            ResultKt.throwOnFailure(r5);
            GraphRequestProcessor r53 = ((GraphCommand.RequestProcessor) this.$command).getOld();
            if (r53 == null) goto L15;
            this.label = 1;
            if (r53.shutdown$camera_camera2_pipe(this) != r0) goto L15;
            goto L15
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.graph.GraphLoop", f = "GraphLoop.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2}, l = {479, 480, 488}, m = "processRequestProcessor", n = {"commands", "command", "commandsRemoved", "$this$removeUpTo$iv", "it", "a$iv", "b$iv", "commands", "command", "commandsRemoved", "$this$removeUpTo$iv", "a$iv", "b$iv", "commands", "command", "commandsRemoved"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "L$0", "L$1", "L$2"}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.graph.GraphLoop$processRequestProcessor$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01021 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ GraphLoop this$0;

        public C01021(GraphLoop r1, Continuation<? super C01021> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r3) {
            this.result = r3;
            this.label |= Integer.MIN_VALUE;
            return GraphLoop.access$processRequestProcessor(this.this$0, null, 0, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.graph.GraphLoop", f = "GraphLoop.kt", i = {0, 1, 1, 1, 2, 2}, l = {566, 572, 573}, m = "processShutdown", n = {"commands", "commands", "command", "idx", "commands", "idx"}, s = {"L$0", "L$0", "L$1", "I$0", "L$0", "I$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.graph.GraphLoop$processShutdown$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01031 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ GraphLoop this$0;

        public C01031(GraphLoop r1, Continuation<? super C01031> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return GraphLoop.access$processShutdown(this.this$0, null, this);
        }
    }

    static {
        Companion = new Companion(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GraphLoop(CameraGraphId r7, Map<?, ? extends Object> r8, Map<?, ? extends Object> r9, List<? extends Request.Listener> r10, List<? extends Listener> r11, CoroutineScope r12, CoroutineDispatcher r13) {
        this.cameraGraphId = r7;
        this.defaultParameters = r8;
        this.requiredParameters = r9;
        this.requiredListeners = r10;
        this.listeners = r11;
        this.shutdownScope = r12;
        CoroutineScope r72 = CoroutineScopeKt.CoroutineScope(r13.plus(new CoroutineName("CXCP-GraphLoop")));
        this.graphLoopScope = r72;
        this.processingQueue = ProcessingQueue.Companion.processIn(new ProcessingQueue(0, new GraphLoop$processingQueue$1(this), new GraphLoop$processingQueue$2(this), 1, null), r72);
        this.lock = new Object();
        this._graphParameters = MapsKt.emptyMap();
        this._graph3AParameters = MapsKt.emptyMap();
        this._requestListeners = CollectionsKt.emptyList();
        this._captureProcessingEnabled = AtomicFU.atomic(true);
        this.currentGraphParameters = MapsKt.emptyMap();
        this.currentGraph3AParameters = MapsKt.emptyMap();
        this.currentRequiredParameters = r9;
        this.currentRequestListeners = r10;
    }

    private final void abortRequests(List<Request> r9) {
        List<Request> r0 = r9;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L8;
        Request r4 = r9.get(r3);
        int r5 = this.currentRequestListeners.size();
        int r6 = 0;
    L5:
        if (r6 >= r5) goto L7;
        this.currentRequestListeners.get(r6).onAborted(r4);
        r6 = r6 + 1;
        goto L5
    L7:
        r3 = r3 + 1;
        goto L3
    L8:
        int r8 = r0.size();
        int r02 = 0;
    L9:
        if (r02 >= r8) goto L14;
        Request r12 = r9.get(r02);
        int r32 = r12.getListeners().size();
        int r42 = 0;
    L11:
        if (r42 >= r32) goto L13;
        r12.getListeners().get(r42).onAborted(r12);
        r42 = r42 + 1;
        goto L11
    L13:
        r02 = r02 + 1;
        goto L9
    }

    public static final /* synthetic */ void access$finalizeUnprocessedCommands(GraphLoop r0, List r1) {
        r0.finalizeUnprocessedCommands(r1);
    }

    public static final /* synthetic */ Object access$process(GraphLoop r0, List r1, Continuation r2) {
        return r0.process(r1, r2);
    }

    public static final /* synthetic */ Object access$processRequestProcessor(GraphLoop r0, List r1, int r2, GraphCommand.RequestProcessor r3, Continuation r4) {
        return r0.processRequestProcessor(r1, r2, r3, r4);
    }

    public static final /* synthetic */ Object access$processShutdown(GraphLoop r0, List r1, Continuation r2) {
        return r0.processShutdown(r1, r2);
    }

    private final boolean buildAndSubmit(boolean r8, List<Request> r9, Map<?, ? extends Object> r10) {
        GraphRequestProcessor r0 = this.currentRequestProcessor;
        if (r0 != null) goto L6;
        return false;
    L6:
        Map<?, ? extends Object> r3 = this.defaultParameters;
        Map<?, ? extends Object> r4 = this.currentGraphParameters;
        if (r10.isEmpty() == false) goto L10;
        Map<?, ? extends Object> r1 = this.currentRequiredParameters;
    L11:
        boolean r7 = r0.submit$camera_camera2_pipe(r8, r9, r3, r4, r1, this.currentRequestListeners);
        if (r7 == false) goto L14;
    L28:
        return r7;
    L14:
        if (r8 == false) goto L20;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L28;
        android.util.Log.w("CXCP", "Failed to repeat with " + CollectionsKt.single(r9));
        return r7;
    L20:
        if (r10.isEmpty() == false) goto L26;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L28;
        android.util.Log.w("CXCP", "Failed to submit capture with " + r9);
        return r7;
    L26:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L28;
        android.util.Log.w("CXCP", "Failed to trigger with " + CollectionsKt.single(r9) + " and " + r10);
        goto L28
    L10:
        Map r12 = MapsKt.createMapBuilder();
        RequestsKt.putAllMetadata(r12, this.currentGraph3AParameters);
        RequestsKt.putAllMetadata(r12, r10);
        RequestsKt.putAllMetadata(r12, this.requiredParameters);
        Unit r2 = Unit.INSTANCE;
        r1 = MapsKt.build(r12);
        goto L11
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean buildAndSubmit$default(GraphLoop r0, boolean r1, List r2, Map r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = MapsKt.emptyMap();
    L6:
        return r0.buildAndSubmit(r1, r2, r3);
    }

    private final void finalizeUnprocessedCommands(List<? extends GraphCommand> r9) {
        Iterator<? extends GraphCommand> r92 = r9.iterator();
    L4:
        if (r92.hasNext() == false) goto L11;
        GraphCommand r0 = r92.next();
        if ((r0 instanceof GraphCommand.Capture) == true) goto L7;
        if ((r0 instanceof GraphCommand.RequestProcessor) == false) goto L4;
        BuildersKt.launch$default(this.shutdownScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(r0, null), 1, null);
        goto L4
    L7:
        abortRequests(((GraphCommand.Capture) r0).getRequests());
        goto L4
    }

    private final Object process(List<GraphCommand> r8, Continuation<? super Unit> r9) {
        int r2 = selectGraphCommand(r8);
        GraphCommand r0 = r8.get(r2);
        if (Intrinsics.areEqual(r0, GraphCommand.Invalidate.INSTANCE) == false) goto L6;
        r8.remove(r2);
    L41:
        return Unit.INSTANCE;
    L6:
        if (Intrinsics.areEqual(r0, GraphCommand.Shutdown.INSTANCE) == false) goto L13;
        Object r7 = processShutdown(r8, r9);
        if (r7 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L11;
        return r7;
    L11:
        return Unit.INSTANCE;
    L13:
        if (Intrinsics.areEqual(r0, GraphCommand.Abort.INSTANCE) == false) goto L16;
        processAbort(r8, r2);
        goto L41
    L16:
        if (Intrinsics.areEqual(r0, GraphCommand.Stop.INSTANCE) == false) goto L19;
        processStop(r8, r2);
        goto L41
    L19:
        if ((r0 instanceof GraphCommand.RequestProcessor) == false) goto L26;
        Object r72 = processRequestProcessor(r8, r2, (GraphCommand.RequestProcessor) r0, r9);
        if (r72 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L24;
        return r72;
    L24:
        return Unit.INSTANCE;
    L26:
        if ((r0 instanceof GraphCommand.Capture) == false) goto L29;
        processCapture$default(this, r8, r2, (GraphCommand.Capture) r0, false, 8, null);
        goto L41
    L29:
        if ((r0 instanceof GraphCommand.Trigger) == false) goto L32;
        processTrigger(r8, r2, (GraphCommand.Trigger) r0);
        goto L41
    L32:
        if ((r0 instanceof GraphCommand.Parameters) == false) goto L35;
        processParameters(r8, r2, (GraphCommand.Parameters) r0);
        goto L41
    L35:
        if ((r0 instanceof GraphCommand.Listeners) == false) goto L38;
        processListeners(r8, r2, (GraphCommand.Listeners) r0);
        goto L41
    L38:
        if ((r0 instanceof GraphCommand.Repeat) == false) goto L42;
        processRepeat$default(this, r8, r2, false, 4, null);
        goto L41
    L42:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    private final void processAbort(List<GraphCommand> r4, int r5) {
        GraphRequestProcessor r0 = this.currentRequestProcessor;
        if (r0 == null) goto L5;
        r0.abortCaptures$camera_camera2_pipe();
    L5:
        this.currentRepeatingRequest = null;
        r4.remove(r5);
        int r02 = 0;
    L6:
        if (r02 >= r5) goto L21;
        GraphCommand r1 = r4.get(r02);
        if (Intrinsics.areEqual(r1, GraphCommand.Stop.INSTANCE) == true) goto L20;
        if (Intrinsics.areEqual(r1, GraphCommand.Abort.INSTANCE) == true) goto L20;
        if ((r1 instanceof GraphCommand.Repeat) == true) goto L20;
        if ((r1 instanceof GraphCommand.Trigger) == true) goto L20;
        if ((r1 instanceof GraphCommand.Capture) == false) goto L19;
        abortRequests(((GraphCommand.Capture) r1).getRequests());
        goto L20
    L19:
        r02 = r02 + 1;
    L20:
        r4.remove(r02);
        r5 = r5 - 1;
        goto L6
    }

    private final void processCapture(List<GraphCommand> r8, int r9, GraphCommand.Capture r10, boolean r11) {
        if (getCaptureProcessingEnabled() == false) goto L8;
        GraphLoop r1 = this;
        if (buildAndSubmit$default(r1, false, r10.getRequests(), null, 4, null) == false) goto L9;
        r8.remove(r9);
        return;
    L9:
        if (r11 == false) goto L17;
        if (r9 <= 0) goto L18;
        int r92 = r9 - 1;
        if ((r8.get(r92) instanceof GraphCommand.Repeat) == false) goto L15;
        r1.processRepeat(r8, r92, false);
        return;
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L18:
        return;
    L17:
        return;
    L8:
        r1 = this;
        goto L9
    }

    public static /* synthetic */ void processCapture$default(GraphLoop r0, List r1, int r2, GraphCommand.Capture r3, boolean r4, int r5, Object r6) {
        if ((r5 & 8) == 0) goto L5;
        r4 = true;
    L5:
        r0.processCapture(r1, r2, r3, r4);
    }

    private final void processListeners(List<GraphCommand> r2, int r3, GraphCommand.Listeners r4) {
        this.currentRequestListeners = CollectionsKt.distinct(CollectionsKt.plus(r4.getListeners(), this.requiredListeners));
        r2.remove(r3);
        int r42 = 0;
    L3:
        if (r42 >= r3) goto L8;
        if ((r2.get(r42) instanceof GraphCommand.Listeners) == true) goto L6;
        r42 = r42 + 1;
        goto L3
    L6:
        r2.remove(r42);
        r3 = r3 - 1;
        goto L3
    L8:
        reissueRepeatingRequest();
    }

    private final void processParameters(List<GraphCommand> r2, int r3, GraphCommand.Parameters r4) {
        this.currentGraphParameters = r4.getGraphParameters();
        this.currentGraph3AParameters = r4.getGraph3AParameters();
        if (r4.getGraph3AParameters().isEmpty() == false) goto L5;
        Map<?, ? extends Object> r42 = this.requiredParameters;
    L6:
        this.currentRequiredParameters = r42;
        r2.remove(r3);
        int r43 = 0;
    L7:
        if (r43 >= r3) goto L12;
        if ((r2.get(r43) instanceof GraphCommand.Parameters) == true) goto L10;
        r43 = r43 + 1;
        goto L7
    L10:
        r2.remove(r43);
        r3 = r3 - 1;
        goto L7
    L12:
        reissueRepeatingRequest();
        return;
    L5:
        Map r0 = MapsKt.createMapBuilder();
        RequestsKt.putAllMetadata(r0, r4.getGraph3AParameters());
        RequestsKt.putAllMetadata(r0, this.requiredParameters);
        r42 = MapsKt.build(r0);
        goto L6
    }

    private final void processRepeat(List<GraphCommand> r11, int r12, boolean r13) {
        int r0 = r12;
    L3:
        int r1 = 0;
        if ((-1) >= r0) goto L17;
        GraphCommand r2 = r11.get(r0);
        if ((r2 instanceof GraphCommand.Repeat) == false) goto L15;
        GraphCommand.Repeat r22 = (GraphCommand.Repeat) r2;
        GraphLoop r4 = this;
        if (buildAndSubmit$default(r4, true, CollectionsKt.listOf(r22.getRequest()), null, 4, null) == false) goto L16;
        r4.currentRepeatingRequest = r22.getRequest();
        r11.remove(r0);
    L10:
        if (r1 >= r0) goto L42;
        if ((r11.get(r1) instanceof GraphCommand.Repeat) == true) goto L13;
        r1 = r1 + 1;
        goto L10
    L13:
        r11.remove(r1);
        r0 = r0 - 1;
        goto L10
    L42:
        return;
    L16:
        r0 = r0 - 1;
        GraphLoop this = r4;
        goto L3
    L15:
        r4 = this;
        goto L16
    L17:
        GraphLoop r42 = this;
        if (r13 == false) goto L39;
        int r122 = r12 + 1;
        if (r122 >= r11.size()) goto L40;
        GraphCommand r10 = r11.get(r122);
        if ((r10 instanceof GraphCommand.Capture) == false) goto L26;
        r42.processCapture(r11, r122, (GraphCommand.Capture) r10, false);
        return;
    L26:
        if ((r10 instanceof GraphCommand.Trigger) == false) goto L41;
        r42.processTrigger(r11, r122, (GraphCommand.Trigger) r10);
        return;
    L41:
        return;
    L40:
        return;
    }

    public static /* synthetic */ void processRepeat$default(GraphLoop r0, List r1, int r2, boolean r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L5;
        r3 = true;
    L5:
        r0.processRepeat(r1, r2, r3);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00dc -> B:37:0x00f7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00f5 -> B:36:0x00f6). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0104 -> B:39:0x0105). Please report as a decompilation issue!!! */
    private final Object processRequestProcessor(List<GraphCommand> r18, int r19, GraphCommand.RequestProcessor r20, Continuation<? super Unit> r21) {
        if ((r21 instanceof C01021) == false) goto L7;
        C01021 r2 = (C01021) r21;
        int r3 = r2.label;
        if ((r3 & Integer.MIN_VALUE) == 0) goto L7;
        r2.label = r3 - Integer.MIN_VALUE;
    L8:
        Object r1 = r2.result;
        Object r32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r4 = r2.label;
        if (r4 == 0) goto L18;
        if (r4 == 1) goto L17;
        if (r4 == 2) goto L16;
        if (r4 != 3) goto L14;
        Ref.IntRef r33 = (Ref.IntRef) r2.L$2;
        GraphCommand.RequestProcessor r42 = (GraphCommand.RequestProcessor) r2.L$1;
        List<GraphCommand> r22 = (List) r2.L$0;
        ResultKt.throwOnFailure(r1);
    L48:
        Ref.IntRef r12 = r33;
        GraphCommand.RequestProcessor r10 = r42;
    L49:
        this.currentRequestProcessor = r10.getNew();
        if (reissueRepeatingRequest() == true) goto L58;
        Request r13 = this.currentRepeatingRequest;
        if (r13 == null) goto L56;
        r22.add(0, new GraphCommand.Repeat(r13));
        if (r12.element != 1) goto L56;
        r22.add(GraphCommand.Invalidate.INSTANCE);
    L56:
        this.currentRepeatingRequest = null;
    L58:
        return Unit.INSTANCE;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        int r43 = r2.I$1;
        int r102 = r2.I$0;
        List<GraphCommand> r11 = (List) r2.L$3;
        r12 = (Ref.IntRef) r2.L$2;
        GraphCommand.RequestProcessor r132 = (GraphCommand.RequestProcessor) r2.L$1;
        List<GraphCommand> r14 = (List) r2.L$0;
        ResultKt.throwOnFailure(r1);
    L36:
        List<GraphCommand> r15 = r14;
    L37:
        GraphCommand.RequestProcessor r16 = r132;
        int r133 = r102;
        r12.element++;
        r10 = r16;
        boolean r142 = true;
        List<GraphCommand> r17 = r11;
        C01021 r112 = r2;
        r22 = r15;
    L39:
        if (r142 == false) goto L41;
        r17.remove(r133);
        r43 = r43 - 1;
    L19:
        if (r133 >= r43) goto L42;
        GraphCommand r143 = r17.get(r133);
        if ((r143 instanceof GraphCommand.RequestProcessor) == false) goto L38;
        GraphRequestProcessor r152 = ((GraphCommand.RequestProcessor) r143).getOld();
        if (r152 == null) goto L29;
        r112.L$0 = r22;
        r112.L$1 = r10;
        r112.L$2 = r12;
        r112.L$3 = r17;
        r112.L$4 = r143;
        r112.I$0 = r133;
        r112.I$1 = r43;
        r112.label = 1;
        if (r152.shutdown$camera_camera2_pipe(r112) == r32) goto L46;
        r15 = r22;
        r2 = r112;
        GraphCommand r113 = r143;
        GraphCommand.RequestProcessor r144 = r10;
        r102 = r133;
        Ref.IntRef r134 = r12;
        List<GraphCommand> r122 = r17;
    L28:
        GraphCommand.RequestProcessor r162 = r144;
        r143 = r113;
        r11 = r122;
        r12 = r134;
        r132 = r162;
    L30:
        GraphRequestProcessor r110 = ((GraphCommand.RequestProcessor) r143).getNew();
        if (r110 == null) goto L37;
        r2.L$0 = r15;
        r2.L$1 = r132;
        r2.L$2 = r12;
        r2.L$3 = r11;
        r2.L$4 = null;
        r2.I$0 = r102;
        r2.I$1 = r43;
        r2.label = 2;
        if (r110.shutdown$camera_camera2_pipe(r2) == r32) goto L46;
        r14 = r15;
    L46:
        return r32;
    L29:
        int r153 = r133;
        r132 = r10;
        r102 = r153;
        r15 = r22;
        r2 = r112;
        r11 = r17;
        goto L30
    L38:
        r142 = false;
        goto L39
    L42:
        GraphRequestProcessor r111 = r10.getOld();
        if (r111 == null) goto L49;
        r112.L$0 = r22;
        r112.L$1 = r10;
        r112.L$2 = r12;
        r112.L$3 = null;
        r112.L$4 = null;
        r112.label = 3;
        if (r111.shutdown$camera_camera2_pipe(r112) == r32) goto L46;
        r42 = r10;
        r33 = r12;
        goto L48
    L41:
        r133 = r133 + 1;
        goto L19
    L17:
        r43 = r2.I$1;
        r102 = r2.I$0;
        r113 = (GraphCommand) r2.L$4;
        r122 = (List) r2.L$3;
        r134 = (Ref.IntRef) r2.L$2;
        r144 = (GraphCommand.RequestProcessor) r2.L$1;
        r15 = (List) r2.L$0;
        ResultKt.throwOnFailure(r1);
        goto L28
    L18:
        ResultKt.throwOnFailure(r1);
        Ref.IntRef r114 = new Ref.IntRef();
        r114.element = 1;
        r18.remove(r19);
        r43 = r19;
        r10 = r20;
        r12 = r114;
        r112 = r2;
        r133 = 0;
        r17 = r18;
        r22 = r17;
    L7:
        r2 = new C01021(this, r21);
        goto L8
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x00ae -> B:48:0x00ec). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00d7 -> B:47:0x00ea). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00e7 -> B:47:0x00ea). Please report as a decompilation issue!!! */
    private final Object processShutdown(List<GraphCommand> r11, Continuation<? super Unit> r12) {
        if ((r12 instanceof C01031) == false) goto L7;
        C01031 r0 = (C01031) r12;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r122 = r0.result;
        Object r13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        int r3 = 0;
        if (r2 == 0) goto L18;
        if (r2 == 1) goto L17;
        if (r2 == 2) goto L16;
        if (r2 != 3) goto L14;
        int r112 = r0.I$1;
        int r22 = r0.I$0;
        List<GraphCommand> r32 = (List) r0.L$0;
        ResultKt.throwOnFailure(r122);
    L47:
        List<GraphCommand> r8 = r32;
        r3 = r22;
    L48:
        r3 = r3 + 1;
    L30:
        if (r3 >= r112) goto L49;
        GraphCommand r123 = r8.get(r3);
        if ((r123 instanceof GraphCommand.RequestProcessor) == false) goto L48;
        GraphRequestProcessor r23 = ((GraphCommand.RequestProcessor) r123).getOld();
        if (r23 == null) goto L41;
        r0.L$0 = r8;
        r0.L$1 = r123;
        r0.I$0 = r3;
        r0.I$1 = r112;
        r0.label = 2;
        if (r23.shutdown$camera_camera2_pipe(r0) == r13) goto L46;
        r22 = r3;
        GraphCommand r33 = r123;
    L39:
        r123 = r33;
    L40:
        r32 = r8;
        GraphRequestProcessor r124 = ((GraphCommand.RequestProcessor) r123).getNew();
        if (r124 == null) goto L47;
        r0.L$0 = r32;
        r0.L$1 = null;
        r0.I$0 = r22;
        r0.I$1 = r112;
        r0.label = 3;
        if (r124.shutdown$camera_camera2_pipe(r0) != r13) goto L47;
    L46:
        return r13;
    L41:
        r22 = r3;
        goto L40
    L49:
        r8.clear();
        CoroutineScopeKt.cancel$default(this.graphLoopScope, null, 1, null);
        return Unit.INSTANCE;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        r112 = r0.I$1;
        r22 = r0.I$0;
        r33 = (GraphCommand) r0.L$1;
        r8 = (List) r0.L$0;
        ResultKt.throwOnFailure(r122);
        goto L39
    L17:
        r11 = (List) r0.L$0;
        ResultKt.throwOnFailure(r122);
    L29:
        this.currentRequestProcessor = null;
        r8 = r11;
        r112 = r11.size();
        goto L30
    L18:
        ResultKt.throwOnFailure(r122);
        this.currentRepeatingRequest = null;
        this.currentGraphParameters = MapsKt.emptyMap();
        this.currentGraph3AParameters = MapsKt.emptyMap();
        int r125 = r11.size();
        int r24 = 0;
    L19:
        if (r24 >= r125) goto L24;
        GraphCommand r82 = r11.get(r24);
        if ((r82 instanceof GraphCommand.Capture) == false) goto L23;
        abortRequests(((GraphCommand.Capture) r82).getRequests());
    L23:
        r24 = r24 + 1;
        goto L19
    L24:
        GraphRequestProcessor r126 = this.currentRequestProcessor;
        if (r126 == null) goto L29;
        r0.L$0 = r11;
        r0.label = 1;
        if (r126.shutdown$camera_camera2_pipe(r0) != r13) goto L29;
    L7:
        r0 = new C01031(this, r12);
        goto L8
    }

    private final void processStop(List<GraphCommand> r3, int r4) {
        GraphRequestProcessor r0 = this.currentRequestProcessor;
        if (r0 == null) goto L5;
        r0.stopRepeating$camera_camera2_pipe();
    L5:
        this.currentRepeatingRequest = null;
        r3.remove(r4);
        int r2 = 0;
    L6:
        if (r2 >= r4) goto L14;
        GraphCommand r02 = r3.get(r2);
        if (Intrinsics.areEqual(r02, GraphCommand.Stop.INSTANCE) == true) goto L13;
        if ((r02 instanceof GraphCommand.Repeat) == true) goto L13;
        r2 = r2 + 1;
    L13:
        r3.remove(r2);
        r4 = r4 - 1;
        goto L6
    }

    private final void processTrigger(List<GraphCommand> r4, int r5, GraphCommand.Trigger r6) {
        Request r0 = this.currentRepeatingRequest;
        if (r0 != null) goto L8;
        if (r5 != 0) goto L8;
        r4.remove(r5);
        return;
    L8:
        if (getCaptureProcessingEnabled() == false) goto L14;
        if (r0 == null) goto L14;
        if (buildAndSubmit(false, CollectionsKt.listOf(r0), r6.getTriggerParameters()) == false) goto L14;
        r4.remove(r5);
        return;
    L14:
        if (r5 <= 0) goto L21;
        int r52 = r5 - 1;
        if ((r4.get(r52) instanceof GraphCommand.Repeat) == false) goto L19;
        processRepeat(r4, r52, false);
        return;
    L19:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    }

    private final boolean reissueRepeatingRequest() {
        GraphRequestProcessor r0 = this.currentRequestProcessor;
        if (r0 == null) goto L10;
        Request r1 = this.currentRepeatingRequest;
        if (r1 == null) goto L7;
        Boolean r7 = Boolean.valueOf(r0.submit$camera_camera2_pipe(true, CollectionsKt.listOf(r1), this.defaultParameters, this.currentGraphParameters, this.currentRequiredParameters, this.currentRequestListeners));
    L9:
        return Intrinsics.areEqual(r7, Boolean.TRUE);
    L7:
        r7 = null;
        goto L9
    L10:
        return false;
    }

    private final int selectGraphCommand(List<GraphCommand> r11) {
        if (r11.size() != 1) goto L5;
        return 0;
    L5:
        List<GraphCommand> r0 = r11;
        int r1 = r0.size() - 1;
        int r4 = -1;
        if (r1 < 0) goto L24;
    L7:
        int r5 = r1 - 1;
        GraphCommand r6 = r11.get(r1);
        if (Intrinsics.areEqual(r6, GraphCommand.Abort.INSTANCE) == true) goto L23;
        if (Intrinsics.areEqual(r6, GraphCommand.Invalidate.INSTANCE) == true) goto L23;
        if (Intrinsics.areEqual(r6, GraphCommand.Stop.INSTANCE) == true) goto L23;
        if (Intrinsics.areEqual(r6, GraphCommand.Shutdown.INSTANCE) == true) goto L23;
        if ((r6 instanceof GraphCommand.RequestProcessor) == false) goto L20;
        if (r4 >= 0) goto L20;
        r4 = r1;
    L20:
        if (r5 < 0) goto L24;
        r1 = r5;
    L23:
        return r1;
    L24:
        if (r4 < 0) goto L26;
        return r4;
    L26:
        int r12 = r0.size();
        int r42 = 0;
        int r52 = -1;
        int r62 = -1;
    L27:
        if (r42 >= r12) goto L38;
        GraphCommand r7 = r11.get(r42);
        if ((r7 instanceof GraphCommand.Parameters) == false) goto L32;
        r52 = r42;
    L37:
        r42 = r42 + 1;
        goto L27
    L32:
        if ((r7 instanceof GraphCommand.Listeners) == false) goto L35;
        r62 = r42;
        goto L37
    L35:
        if ((r7 instanceof GraphCommand.Repeat) == true) goto L37;
    L38:
        if (r52 < 0) goto L40;
        return r52;
    L40:
        if (r62 < 0) goto L43;
        return r62;
    L43:
        if (this.currentRepeatingRequest != null) goto L45;
    L55:
        int r10 = r0.size();
        int r02 = -1;
        int r3 = 0;
    L56:
        if (r3 >= r10) goto L60;
        if ((r11.get(r3) instanceof GraphCommand.Repeat) == false) goto L60;
        int r9 = r3;
        r3 = r3 + 1;
        r02 = r9;
    L60:
        if (r02 < 0) goto L62;
        return r02;
    L62:
        return 0;
    L45:
        if (getCaptureProcessingEnabled() == false) goto L55;
        int r102 = r0.size();
        int r13 = 0;
    L47:
        if (r13 >= r102) goto L55;
        GraphCommand r43 = r11.get(r13);
        if ((r43 instanceof GraphCommand.Capture) == true) goto L54;
        if ((r43 instanceof GraphCommand.Trigger) == true) goto L54;
        r13 = r13 + 1;
    L54:
        return r13;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Object r1 = this.lock;
        monitor-enter(r1);
    L12:
        th = move-exception;
        throw th;
    L5:
        if (this.closed == false) goto L9;
        monitor-exit(r1);
        return;
    L9:
        this.closed = true;     // Catch: Throwable -> L12
        GraphRequestProcessor r0 = this._requestProcessor;     // Catch: Throwable -> L12
        if (r0 == null) goto L14;
        BuildersKt.launch$default(this.shutdownScope, null, null, new GraphLoop$close$1$1$1(r0, null), 3, null);     // Catch: Throwable -> L12
    L14:
        this._requestProcessor = null;     // Catch: Throwable -> L12
        this.processingQueue.tryEmit(GraphCommand.Shutdown.INSTANCE);     // Catch: Throwable -> L12
        monitor-exit(r1);
        int r02 = this.listeners.size();
        int r12 = 0;
    L17:
        if (r12 >= r02) goto L19;
        this.listeners.get(r12).onGraphShutdown();
        r12 = r12 + 1;
        goto L17
    }

    public final boolean getCaptureProcessingEnabled() {
        return this._captureProcessingEnabled.getValue();
    }

    public final Request getRepeatingRequest() {
        Object r0 = this.lock;
        monitor-enter(r0);
        Request r1 = this._repeatingRequest;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public final void invalidate() {
        this.processingQueue.tryEmit(GraphCommand.Invalidate.INSTANCE);
    }

    public final void setCaptureProcessingEnabled(boolean r2) {
        this._captureProcessingEnabled.setValue(r2);
        if (r2 == false) goto L6;
        invalidate();
        return;
    }

    public final void setGraph3AParameters(Map<?, ? extends Object> r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this._graph3AParameters = r4;     // Catch: Throwable -> L7
        this.processingQueue.tryEmit(new GraphCommand.Parameters(this._graphParameters, r4));     // Catch: Throwable -> L7
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void setGraphParameters(Map<?, ? extends Object> r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this._graphParameters = r4;     // Catch: Throwable -> L7
        this.processingQueue.tryEmit(new GraphCommand.Parameters(r4, this._graph3AParameters));     // Catch: Throwable -> L7
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void setRepeatingRequest(Request r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
        Request r1 = this._repeatingRequest;     // Catch: Throwable -> L11
        this._repeatingRequest = r4;     // Catch: Throwable -> L11
        if (r1 != null) goto L8;
        if (r4 != null) goto L8;
    L14:
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r0);
        if (r4 != null) goto L20;
        int r42 = this.listeners.size();
        int r02 = 0;
    L18:
        if (r02 >= r42) goto L26;
        this.listeners.get(r02).onStopRepeating();
        r02 = r02 + 1;
        goto L18
    L26:
        return;
    L20:
        return;
    L8:
        ProcessingQueue<GraphCommand> r13 = this.processingQueue;
        if (r4 == null) goto L13;
        r13.tryEmit(new GraphCommand.Repeat(r4));     // Catch: Throwable -> L11
        goto L14
    L13:
        r13.tryEmit(GraphCommand.Stop.INSTANCE);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    public final void setRequestListeners(List<? extends Request.Listener> r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this._requestListeners = r3;     // Catch: Throwable -> L7
        this.processingQueue.tryEmit(new GraphCommand.Listeners(r3));     // Catch: Throwable -> L7
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void setRequestProcessor(GraphRequestProcessor r9) {
        Object r1 = this.lock;
        monitor-enter(r1);
        GraphRequestProcessor r0 = this._requestProcessor;     // Catch: Throwable -> L10
        this._requestProcessor = r9;     // Catch: Throwable -> L10
        if (this.closed == false) goto L14;
        this._requestProcessor = null;     // Catch: Throwable -> L10
        if (r9 == null) goto L12;
        BuildersKt.launch$default(this.shutdownScope, null, null, new GraphLoop$requestProcessor$2$1(r9, null), 3, null);     // Catch: Throwable -> L10
    L12:
        monitor-exit(r1);
        return;
    L14:
        if (r0 == r9) goto L17;
        this.processingQueue.tryEmit(new GraphCommand.RequestProcessor(r0, r9));     // Catch: Throwable -> L10
    L17:
        Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(r1);
        if (r9 != null) goto L23;
        int r92 = this.listeners.size();
        int r03 = 0;
    L21:
        if (r03 >= r92) goto L28;
        this.listeners.get(r03).onGraphStopped();
        r03 = r03 + 1;
        goto L21
    L28:
        return;
    L23:
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public final boolean submit(List<Request> r3) {
        if (this.processingQueue.tryEmit(new GraphCommand.Capture(r3)) == true) goto L6;
        abortRequests(r3);
        return false;
    L6:
        return true;
    }

    public String toString() {
        return "GraphLoop(" + this.cameraGraphId + ')';
    }

    public final boolean trigger(Map<?, ? extends Object> r2) {
        if (getRepeatingRequest() != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Cannot submit parameters without an active repeating request!");
        return false;
    L5:
        return this.processingQueue.tryEmit(new GraphCommand.Trigger(r2));
    }
}
