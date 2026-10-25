package androidx.camera.camera2.pipe.graph;

import androidx.camera.camera2.pipe.GraphState;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphListener;", _UrlKt.FRAGMENT_ENCODE_SET, "onGraphStarting", _UrlKt.FRAGMENT_ENCODE_SET, "onGraphStarted", "requestProcessor", "Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;", "onGraphStopping", "onGraphStopped", "onGraphModified", "onGraphError", "graphStateError", "Landroidx/camera/camera2/pipe/GraphState$GraphStateError;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface GraphListener {
    void onGraphError(GraphState.GraphStateError r1);

    void onGraphModified(GraphRequestProcessor r1);

    void onGraphStarted(GraphRequestProcessor r1);

    void onGraphStarting();

    void onGraphStopped(GraphRequestProcessor r1);

    void onGraphStopping();
}
