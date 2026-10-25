package androidx.camera.camera2.pipe.graph;

import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import java.util.Comparator;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 StreamGraphImpl.kt\nandroidx/camera/camera2/pipe/graph/StreamGraphImpl\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,102:1\n210#2:103\n1#3:104\n*E\n"})
/* loaded from: classes3.dex */
public final class StreamGraphImpl$special$$inlined$sortedBy$1<T> implements Comparator {
    final /* synthetic */ StreamGraphImpl this$0;

    public StreamGraphImpl$special$$inlined$sortedBy$1(StreamGraphImpl r1) {
        this.this$0 = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T r5, T r6) {
        Iterator<T> r52 = ((StreamGraphImpl.OutputConfig) r5).getStreams().iterator();
        if (r52.hasNext() == false) goto L22;
        CameraStream r0 = (CameraStream) r52.next();
        Integer r02 = Integer.valueOf(this.this$0.getStreams().indexOf(r0));
    L6:
        if (r52.hasNext() == false) goto L10;
        CameraStream r2 = (CameraStream) r52.next();
        Integer r22 = Integer.valueOf(this.this$0.getStreams().indexOf(r2));
        if (r02.compareTo(r22) <= 0) goto L6;
        r02 = r22;
        goto L6
    L10:
        Iterator<T> r53 = ((StreamGraphImpl.OutputConfig) r6).getStreams().iterator();
        if (r53.hasNext() == false) goto L20;
        CameraStream r62 = (CameraStream) r53.next();
        Integer r63 = Integer.valueOf(this.this$0.getStreams().indexOf(r62));
    L14:
        if (r53.hasNext() == false) goto L19;
        CameraStream r1 = (CameraStream) r53.next();
        Integer r12 = Integer.valueOf(this.this$0.getStreams().indexOf(r1));
        if (r63.compareTo(r12) <= 0) goto L14;
        r63 = r12;
        goto L14
    L19:
        return ComparisonsKt.compareValues(r02, r63);
    L20:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    L22:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    }
}
