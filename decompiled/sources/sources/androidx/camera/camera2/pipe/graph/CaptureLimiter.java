package androidx.camera.camera2.pipe.graph;

import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.graph.GraphLoop;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicLong;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u0012H\u0016J\b\u0010\u001c\u001a\u00020\u0012H\u0016J\b\u0010\u001d\u001a\u00020\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001e"}, d2 = {"Landroidx/camera/camera2/pipe/graph/CaptureLimiter;", "Landroidx/camera/camera2/pipe/Request$Listener;", "Landroidx/camera/camera2/pipe/graph/GraphLoop$Listener;", "requestsUntilActive", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(J)V", "frameCount", "Lkotlinx/atomicfu/AtomicLong;", "_graphLoop", "Landroidx/camera/camera2/pipe/graph/GraphLoop;", "value", "graphLoop", "getGraphLoop", "()Landroidx/camera/camera2/pipe/graph/GraphLoop;", "setGraphLoop", "(Landroidx/camera/camera2/pipe/graph/GraphLoop;)V", "onComplete", _UrlKt.FRAGMENT_ENCODE_SET, "requestMetadata", "Landroidx/camera/camera2/pipe/RequestMetadata;", "frameNumber", "Landroidx/camera/camera2/pipe/FrameNumber;", "result", "Landroidx/camera/camera2/pipe/FrameInfo;", "onComplete-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameInfo;)V", "onStopRepeating", "onGraphStopped", "onGraphShutdown", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCaptureLimiter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureLimiter.kt\nandroidx/camera/camera2/pipe/graph/CaptureLimiter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 4 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n*L\n1#1,87:1\n1#2:88\n71#3,2:89\n71#3,2:95\n71#3,2:101\n499#4,4:91\n477#4,4:97\n*S KotlinDebug\n*F\n+ 1 CaptureLimiter.kt\nandroidx/camera/camera2/pipe/graph/CaptureLimiter\n*L\n50#1:89,2\n63#1:95,2\n76#1:101,2\n61#1:91,4\n74#1:97,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CaptureLimiter implements Request.Listener, GraphLoop.Listener {
    private GraphLoop _graphLoop;
    private final AtomicLong frameCount;
    private final long requestsUntilActive;

    public CaptureLimiter(long r3) {
        this.requestsUntilActive = r3;
        if (r3 <= 0) goto L6;
        this.frameCount = AtomicFU.atomic(0);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        throw null;
    }

    public static final /* synthetic */ long access$getRequestsUntilActive$p(CaptureLimiter r2) {
        return r2.requestsUntilActive;
    }

    public static final /* synthetic */ GraphLoop access$get_graphLoop$p(CaptureLimiter r0) {
        return r0._graphLoop;
    }

    public final GraphLoop getGraphLoop() {
        return this._graphLoop;
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onComplete-CcXjc1I */
    public void mo13onCompleteCcXjc1I(RequestMetadata r3, long r4, FrameInfo r6) {
        AtomicLong r32 = this.frameCount;
    L3:
        long r42 = r32.getValue();
        long r0 = -1;
        if (r42 == (-1)) goto L8;
        r0 = 1 + r42;
    L8:
        if (r32.compareAndSet(r42, r0) == false) goto L3;
        if (r0 == this.requestsUntilActive) goto L12;
        return;
    L12:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L14;
        android.util.Log.w("CXCP", "Capture processing is now enabled for " + access$get_graphLoop$p(this) + " after " + r0 + " frames.");
    L14:
        getGraphLoop().setCaptureProcessingEnabled(true);
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphLoop.Listener
    public void onGraphShutdown() {
        this.frameCount.setValue(-1);
        getGraphLoop().setCaptureProcessingEnabled(false);
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphLoop.Listener
    public void onGraphStopped() {
        AtomicLong r0 = this.frameCount;
    L3:
        long r1 = r0.getValue();
        long r3 = -1;
        if (r1 == (-1)) goto L8;
        r3 = 0;
    L8:
        if (r0.compareAndSet(r1, r3) == false) goto L3;
        getGraphLoop().setCaptureProcessingEnabled(false);
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L15;
        android.util.Log.w("CXCP", "Capture processing has been disabled for " + getGraphLoop() + " until " + access$getRequestsUntilActive$p(this) + " frames have been completed.");
        return;
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphLoop.Listener
    public void onStopRepeating() {
    }

    public final void setGraphLoop(GraphLoop r3) {
        if (this._graphLoop != null) goto L8;
        this._graphLoop = r3;
        r3.setCaptureProcessingEnabled(false);
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L10;
        android.util.Log.w("CXCP", "Capture processing has been disabled for " + r3 + " until " + access$getRequestsUntilActive$p(this) + " frames have been completed.");
        return;
    L10:
        return;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("GraphLoop has already been set!");
    }
}
