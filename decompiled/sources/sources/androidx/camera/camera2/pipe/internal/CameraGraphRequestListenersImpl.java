package androidx.camera.camera2.pipe.internal;

import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.graph.GraphProcessor;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B#\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0000¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00148\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraGraphRequestListenersImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/internal/GraphSessionLock;", "sessionLock", "Landroidx/camera/camera2/pipe/graph/GraphProcessor;", "graphProcessor", "Lkotlinx/coroutines/CoroutineScope;", "graphScope", "<init>", "(Landroidx/camera/camera2/pipe/internal/GraphSessionLock;Landroidx/camera/camera2/pipe/graph/GraphProcessor;Lkotlinx/coroutines/CoroutineScope;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request$Listener;", "fetchUpdatedListeners$camera_camera2_pipe", "()Ljava/util/List;", "fetchUpdatedListeners", "Landroidx/camera/camera2/pipe/internal/GraphSessionLock;", "Landroidx/camera/camera2/pipe/graph/GraphProcessor;", "Lkotlinx/coroutines/CoroutineScope;", "lock", "Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "listeners", "Ljava/util/Set;", _UrlKt.FRAGMENT_ENCODE_SET, "dirty", "Z", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraGraphRequestListenersImpl {
    private boolean dirty;
    private final GraphProcessor graphProcessor;
    private final CoroutineScope graphScope;
    private final Set<Request.Listener> listeners;
    private final Object lock;
    private final GraphSessionLock sessionLock;

    public CameraGraphRequestListenersImpl(GraphSessionLock r1, GraphProcessor r2, CoroutineScope r3) {
        this.sessionLock = r1;
        this.graphProcessor = r2;
        this.graphScope = r3;
        this.lock = new Object();
        this.listeners = new LinkedHashSet();
    }

    public final List<Request.Listener> fetchUpdatedListeners$camera_camera2_pipe() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (this.dirty == true) goto L10;
        monitor-exit(r0);
        return null;
    L10:
        this.dirty = false;     // Catch: Throwable -> L13
        List<Request.Listener> r2 = CollectionsKt.toList(this.listeners);     // Catch: Throwable -> L13
        monitor-exit(r0);
        return r2;
    }
}
