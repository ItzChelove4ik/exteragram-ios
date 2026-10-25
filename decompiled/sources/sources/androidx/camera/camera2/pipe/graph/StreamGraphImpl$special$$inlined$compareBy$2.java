package androidx.camera.camera2.pipe.graph;

import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.StreamFormat;
import java.util.Comparator;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 StreamGraphImpl.kt\nandroidx/camera/camera2/pipe/graph/StreamGraphImpl\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,102:1\n466#2:103\n1#3:104\n*E\n"})
/* loaded from: classes3.dex */
public final class StreamGraphImpl$special$$inlined$compareBy$2<T> implements Comparator {
    public StreamGraphImpl$special$$inlined$compareBy$2() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T r4, T r5) {
        Iterator<T> r3 = ((CameraStream) r4).getOutputs().iterator();
        if (r3.hasNext() == false) goto L22;
        OutputStream r42 = (OutputStream) r3.next();
        Integer r43 = Integer.valueOf(StreamGraphImpl.access$getPreviewFormats$cp().indexOf(StreamFormat.m389boximpl(r42.mo310getFormat8FPWQzE())));
    L6:
        if (r3.hasNext() == false) goto L10;
        OutputStream r1 = (OutputStream) r3.next();
        Integer r12 = Integer.valueOf(StreamGraphImpl.access$getPreviewFormats$cp().indexOf(StreamFormat.m389boximpl(r1.mo310getFormat8FPWQzE())));
        if (r43.compareTo(r12) >= 0) goto L6;
        r43 = r12;
        goto L6
    L10:
        Iterator<T> r32 = ((CameraStream) r5).getOutputs().iterator();
        if (r32.hasNext() == false) goto L20;
        OutputStream r52 = (OutputStream) r32.next();
        Integer r53 = Integer.valueOf(StreamGraphImpl.access$getPreviewFormats$cp().indexOf(StreamFormat.m389boximpl(r52.mo310getFormat8FPWQzE())));
    L14:
        if (r32.hasNext() == false) goto L19;
        OutputStream r0 = (OutputStream) r32.next();
        Integer r02 = Integer.valueOf(StreamGraphImpl.access$getPreviewFormats$cp().indexOf(StreamFormat.m389boximpl(r0.mo310getFormat8FPWQzE())));
        if (r53.compareTo(r02) >= 0) goto L14;
        r53 = r02;
        goto L14
    L19:
        return ComparisonsKt.compareValues(r43, r53);
    L20:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    L22:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    }
}
