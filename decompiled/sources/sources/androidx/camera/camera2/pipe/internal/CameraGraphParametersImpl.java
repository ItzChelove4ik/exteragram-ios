package androidx.camera.camera2.pipe.internal;

import androidx.camera.camera2.pipe.graph.GraphProcessor;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B#\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\rR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\"\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00128\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraGraphParametersImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/internal/GraphSessionLock;", "sessionLock", "Landroidx/camera/camera2/pipe/graph/GraphProcessor;", "graphProcessor", "Lkotlinx/coroutines/CoroutineScope;", "graphScope", "<init>", "(Landroidx/camera/camera2/pipe/internal/GraphSessionLock;Landroidx/camera/camera2/pipe/graph/GraphProcessor;Lkotlinx/coroutines/CoroutineScope;)V", _UrlKt.FRAGMENT_ENCODE_SET, "flush", "()V", "Landroidx/camera/camera2/pipe/internal/GraphSessionLock;", "Landroidx/camera/camera2/pipe/graph/GraphProcessor;", "Lkotlinx/coroutines/CoroutineScope;", "lock", "Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "parameters", "Ljava/util/Map;", _UrlKt.FRAGMENT_ENCODE_SET, "dirty", "Z", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraGraphParametersImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraGraphParametersImpl.kt\nandroidx/camera/camera2/pipe/internal/CameraGraphParametersImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,179:1\n1#2:180\n71#3,2:181\n71#3,2:183\n*S KotlinDebug\n*F\n+ 1 CameraGraphParametersImpl.kt\nandroidx/camera/camera2/pipe/internal/CameraGraphParametersImpl\n*L\n88#1:181,2\n133#1:183,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraGraphParametersImpl {
    private boolean dirty;
    private final GraphProcessor graphProcessor;
    private final CoroutineScope graphScope;
    private final Object lock;
    private final Map<Object, Object> parameters;
    private final GraphSessionLock sessionLock;

    public CameraGraphParametersImpl(GraphSessionLock r1, GraphProcessor r2, CoroutineScope r3) {
        this.sessionLock = r1;
        this.graphProcessor = r2;
        this.graphScope = r3;
        this.lock = new Object();
        this.parameters = new LinkedHashMap();
    }

    public final void flush() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (this.dirty == true) goto L9;
        monitor-exit(r0);
        return;
    L9:
        this.dirty = false;     // Catch: Throwable -> L13
        HashMap r1 = new HashMap(this.parameters);     // Catch: Throwable -> L13
        monitor-exit(r0);
        this.graphProcessor.updateGraphParameters(r1);
    }
}
