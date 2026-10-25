package androidx.camera.camera2.pipe.graph;

import androidx.camera.camera2.pipe.CaptureSequence;
import java.util.List;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¨\u0006\u0006"}, d2 = {"androidx/camera/camera2/pipe/graph/GraphRequestProcessor$activeBurstListener$1", "Landroidx/camera/camera2/pipe/CaptureSequence$CaptureSequenceListener;", "onCaptureSequenceComplete", _UrlKt.FRAGMENT_ENCODE_SET, "captureSequence", "Landroidx/camera/camera2/pipe/CaptureSequence;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GraphRequestProcessor$activeBurstListener$1 implements CaptureSequence.CaptureSequenceListener {
    final /* synthetic */ GraphRequestProcessor this$0;

    public GraphRequestProcessor$activeBurstListener$1(GraphRequestProcessor r1) {
        this.this$0 = r1;
    }

    @Override // androidx.camera.camera2.pipe.CaptureSequence.CaptureSequenceListener
    public void onCaptureSequenceComplete(CaptureSequence<?> r2) {
        if (r2.getRepeating() == true) goto L12;
        List r0 = GraphRequestProcessor.access$getActiveCaptureSequences$p(this.this$0);
        GraphRequestProcessor r1 = this.this$0;
        monitor-enter(r0);
        GraphRequestProcessor.access$getActiveCaptureSequences$p(r1).remove(r2);     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L9:
        th = move-exception;
        throw th;
    }
}
