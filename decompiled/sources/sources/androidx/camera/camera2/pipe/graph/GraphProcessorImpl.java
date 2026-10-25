package androidx.camera.camera2.pipe.graph;

import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraGraphId;
import androidx.camera.camera2.pipe.GraphState;
import androidx.camera.camera2.pipe.GraphStateListener;
import androidx.camera.camera2.pipe.InputRequest;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.compat.Camera2Quirks;
import androidx.camera.camera2.pipe.compat.CameraPipeKeys;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002BF\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0013\b\u0001\u0010\u000e\u001a\r\u0012\t\u0012\u00070\f¢\u0006\u0002\b\r0\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u001a\u0010\u0015J\u0019\u0010\u001b\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0004\b\u001b\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u001c\u0010\u0019J\u0017\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001f\u0010 J\u001d\u0010$\u001a\u00020#2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0\u000bH\u0016¢\u0006\u0004\b$\u0010%J#\u0010)\u001a\u00020#2\u0012\u0010(\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010'0&H\u0016¢\u0006\u0004\b)\u0010*J#\u0010+\u001a\u00020\u00132\u0012\u0010(\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010'0&H\u0016¢\u0006\u0004\b+\u0010,J#\u0010-\u001a\u00020\u00132\u0012\u0010(\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010'0&H\u0016¢\u0006\u0004\b-\u0010,J\u001d\u0010/\u001a\u00020\u00132\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016¢\u0006\u0004\b/\u00100J\u000f\u00101\u001a\u00020\u0013H\u0016¢\u0006\u0004\b1\u0010\u0015J\u000f\u00103\u001a\u000202H\u0016¢\u0006\u0004\b3\u00104R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u00105R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u001a\u0010;\u001a\b\u0012\u0004\u0012\u00020:0\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<R\u001a\u0010?\u001a\b\u0012\u0004\u0012\u00020>0=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R(\u0010F\u001a\u0004\u0018\u00010!2\b\u0010A\u001a\u0004\u0018\u00010!8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bB\u0010C\"\u0004\bD\u0010E¨\u0006G"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphProcessorImpl;", "Landroidx/camera/camera2/pipe/graph/GraphProcessor;", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "Landroidx/camera/camera2/pipe/core/Threads;", "threads", "Landroidx/camera/camera2/pipe/CameraGraphId;", "cameraGraphId", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "cameraGraphConfig", "Landroidx/camera/camera2/pipe/graph/Listener3A;", "graphListener3A", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request$Listener;", "Lkotlin/jvm/JvmSuppressWildcards;", "graphListeners", "Landroidx/camera/camera2/pipe/compat/Camera2Quirks;", "camera2Quirks", "<init>", "(Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/CameraGraphId;Landroidx/camera/camera2/pipe/CameraGraph$Config;Landroidx/camera/camera2/pipe/graph/Listener3A;Ljava/util/List;Landroidx/camera/camera2/pipe/compat/Camera2Quirks;)V", _UrlKt.FRAGMENT_ENCODE_SET, "onGraphStarting", "()V", "Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;", "requestProcessor", "onGraphStarted", "(Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;)V", "onGraphStopping", "onGraphStopped", "onGraphModified", "Landroidx/camera/camera2/pipe/GraphState$GraphStateError;", "graphStateError", "onGraphError", "(Landroidx/camera/camera2/pipe/GraphState$GraphStateError;)V", "Landroidx/camera/camera2/pipe/Request;", "requests", _UrlKt.FRAGMENT_ENCODE_SET, "submit", "(Ljava/util/List;)Z", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "parameters", "trigger", "(Ljava/util/Map;)Z", "updateGraphParameters", "(Ljava/util/Map;)V", "update3AParameters", "listeners", "updateRequestListeners", "(Ljava/util/List;)V", "close", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/camera/camera2/pipe/CameraGraphId;", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "Landroidx/camera/camera2/pipe/graph/GraphLoop;", "graphLoop", "Landroidx/camera/camera2/pipe/graph/GraphLoop;", "Landroidx/camera/camera2/pipe/GraphStateListener;", "externalStateGraphListeners", "Ljava/util/List;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/camera/camera2/pipe/GraphState;", "_graphState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "value", "getRepeatingRequest", "()Landroidx/camera/camera2/pipe/Request;", "setRepeatingRequest", "(Landroidx/camera/camera2/pipe/Request;)V", "repeatingRequest", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGraphProcessor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GraphProcessor.kt\nandroidx/camera/camera2/pipe/graph/GraphProcessorImpl\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 StateFlow.kt\nkotlinx/coroutines/flow/StateFlowKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,275:1\n59#2,2:276\n50#2,2:278\n50#2,2:280\n50#2,2:282\n50#2,2:284\n50#2,2:286\n50#2,2:288\n230#3,5:290\n295#4,2:295\n*S KotlinDebug\n*F\n+ 1 GraphProcessor.kt\nandroidx/camera/camera2/pipe/graph/GraphProcessorImpl\n*L\n133#1:276,2\n174#1:278,2\n182#1:280,2\n191#1:282,2\n200#1:284,2\n209#1:286,2\n214#1:288,2\n215#1:290,5\n230#1:295,2\n*E\n"})
/* loaded from: classes3.dex */
public final class GraphProcessorImpl implements GraphProcessor, GraphListener {
    private final MutableStateFlow<GraphState> _graphState;
    private final CameraGraph.Config cameraGraphConfig;
    private final CameraGraphId cameraGraphId;
    private final List<GraphStateListener> externalStateGraphListeners;
    private final GraphLoop graphLoop;

    public GraphProcessorImpl(Threads r10, CameraGraphId r11, CameraGraph.Config r12, Listener3A r13, List<Request.Listener> r14, Camera2Quirks r15) {
        this.cameraGraphId = r11;
        this.cameraGraphConfig = r12;
        this.externalStateGraphListeners = r12.getGraphStateListeners();
        Map<?, Object> r3 = r12.getDefaultParameters();
        Map<?, Object> r4 = r12.getRequiredParameters();
        CameraPipeKeys r0 = CameraPipeKeys.INSTANCE;
        Object r1 = r3.get(r0.getIgnore3ARequiredParameters());
        Boolean r2 = Boolean.TRUE;
        if (Intrinsics.areEqual(r1, r2) == true) goto L7;
        if (Intrinsics.areEqual(r4.get(r0.getIgnore3ARequiredParameters()), r2) == true) goto L7;
    L9:
        int r122 = r15.getRepeatingRequestFrameCountForCapture$camera_camera2_pipe(r12.getFlags());
        if (r122 == 0) goto L12;
        CaptureLimiter r152 = new CaptureLimiter(r122);
    L13:
        GraphLoop r16 = new GraphLoop(r11, r3, r4, CollectionsKt.plus(r14, CollectionsKt.listOfNotNull(r152)), CollectionsKt.listOfNotNull(new Object[]{r13, r152}), r10.getCameraPipeScope(), r10.getLightweightDispatcher());
        this.graphLoop = r16;
        if (r152 == null) goto L16;
        r152.setGraphLoop(r16);
    L16:
        this._graphState = StateFlowKt.MutableStateFlow(GraphState.GraphStateStopped.INSTANCE);
        return;
    L12:
        r152 = null;
    L7:
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L9;
        android.util.Log.i("CXCP", r0.getIgnore3ARequiredParameters() + " is set to true, ignoring GraphState3A parameters.");
        goto L9
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphProcessor
    public void close() {
        this.graphLoop.close();
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphProcessor
    public Request getRepeatingRequest() {
        return this.graphLoop.getRepeatingRequest();
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphListener
    public void onGraphError(GraphState.GraphStateError r5) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", this + " onGraphError(" + r5 + ')');
    L5:
        MutableStateFlow<GraphState> r0 = this._graphState;
    L6:
        GraphState r1 = r0.getValue();
        GraphState r2 = r1;
        if ((r2 instanceof GraphState.GraphStateStopping) == false) goto L9;
    L12:
        GraphState r22 = GraphState.GraphStateStopped.INSTANCE;
    L14:
        if (r0.compareAndSet(r1, r22) == false) goto L6;
        Iterator<GraphStateListener> r4 = this.externalStateGraphListeners.iterator();
    L17:
        if (r4.hasNext() == false) goto L19;
        r4.next().onGraphError(r5);
        goto L17
    L19:
        return;
    L9:
        if ((r2 instanceof GraphState.GraphStateStopped) == true) goto L12;
        r22 = r5;
        goto L14
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphListener
    public void onGraphModified(GraphRequestProcessor r2) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", this + " onGraphModified");
    L5:
        this.graphLoop.invalidate();
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphListener
    public void onGraphStarted(GraphRequestProcessor r3) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", this + " onGraphStarted");
    L5:
        this._graphState.setValue(GraphState.GraphStateStarted.INSTANCE);
        this.graphLoop.setRequestProcessor(r3);
        Iterator<GraphStateListener> r2 = this.externalStateGraphListeners.iterator();
    L7:
        if (r2.hasNext() == false) goto L9;
        r2.next().onGraphStarted();
        goto L7
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphListener
    public void onGraphStarting() {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", this + " onGraphStarting");
    L5:
        this._graphState.setValue(GraphState.GraphStateStarting.INSTANCE);
        Iterator<GraphStateListener> r2 = this.externalStateGraphListeners.iterator();
    L7:
        if (r2.hasNext() == false) goto L9;
        r2.next().onGraphStarting();
        goto L7
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphListener
    public void onGraphStopped(GraphRequestProcessor r2) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", this + " onGraphStopped");
    L5:
        this._graphState.setValue(GraphState.GraphStateStopped.INSTANCE);
        this.graphLoop.setRequestProcessor(null);
        Iterator<GraphStateListener> r1 = this.externalStateGraphListeners.iterator();
    L7:
        if (r1.hasNext() == false) goto L9;
        r1.next().onGraphStopped();
        goto L7
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphListener
    public void onGraphStopping() {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", this + " onGraphStopping");
    L5:
        this._graphState.setValue(GraphState.GraphStateStopping.INSTANCE);
        this.graphLoop.setRequestProcessor(null);
        Iterator<GraphStateListener> r2 = this.externalStateGraphListeners.iterator();
    L7:
        if (r2.hasNext() == false) goto L9;
        r2.next().onGraphStopping();
        goto L7
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphProcessor
    public void setRepeatingRequest(Request r1) {
        this.graphLoop.setRepeatingRequest(r1);
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphProcessor
    public boolean submit(List<Request> r4) {
        Iterator<T> r0 = r4.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        Object r1 = r0.next();
        if (((Request) r1).getInputRequest() == null) goto L4;
    L9:
        Request r12 = (Request) r1;
        if (r12 == null) goto L17;
        if (this.cameraGraphConfig.getInput() != null) goto L17;
        StringBuilder r42 = new StringBuilder("Cannot submit ");
        r42.append(r12);
        InputRequest r02 = r12.getInputRequest();
        r42.append(" with input request ");
        r42.append(r02);
        r42.append(" to ");
        r42.append(this);
        r42.append(" because CameraGraph was not configured to support reprocessing");
        throw new IllegalStateException(r42.toString().toString());
    L17:
        return this.graphLoop.submit(r4);
    L8:
        r1 = null;
        goto L9
    }

    public String toString() {
        return "GraphProcessor(cameraGraph: " + this.cameraGraphId + ')';
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphProcessor
    public boolean trigger(Map<?, ? extends Object> r1) {
        return this.graphLoop.trigger(r1);
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphProcessor
    public void update3AParameters(Map<?, ? extends Object> r1) {
        this.graphLoop.setGraph3AParameters(r1);
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphProcessor
    public void updateGraphParameters(Map<?, ? extends Object> r1) {
        this.graphLoop.setGraphParameters(r1);
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphProcessor
    public void updateRequestListeners(List<? extends Request.Listener> r1) {
        this.graphLoop.setRequestListeners(r1);
    }
}
