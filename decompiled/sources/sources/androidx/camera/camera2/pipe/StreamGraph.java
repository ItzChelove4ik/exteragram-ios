package androidx.camera.camera2.pipe;

import androidx.camera.camera2.pipe.CameraStream;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0096\u0002¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u0005\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\u000bH\u0096\u0002¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0012ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/StreamGraph;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraStream$Config;", "config", "Landroidx/camera/camera2/pipe/CameraStream;", "get", "(Landroidx/camera/camera2/pipe/CameraStream$Config;)Landroidx/camera/camera2/pipe/CameraStream;", "Landroidx/camera/camera2/pipe/StreamId;", "streamId", "get-aKI5c8E", "(I)Landroidx/camera/camera2/pipe/CameraStream;", "Landroidx/camera/camera2/pipe/OutputId;", "outputId", "Landroidx/camera/camera2/pipe/OutputStream;", "get-iYJqvbA", "(I)Landroidx/camera/camera2/pipe/OutputStream;", _UrlKt.FRAGMENT_ENCODE_SET, "getStreams", "()Ljava/util/List;", "streams", "Landroidx/camera/camera2/pipe/InputStream;", "getInputs", "inputs", "getOutputs", "outputs", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStreamGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamGraph.kt\nandroidx/camera/camera2/pipe/StreamGraph\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,62:1\n1#2:63\n*E\n"})
/* loaded from: classes3.dex */
public interface StreamGraph {
    CameraStream get(CameraStream.Config r1);

    /* renamed from: get-aKI5c8E, reason: not valid java name */
    default CameraStream m399getaKI5c8E(int r3) {
        Iterator<T> r2 = getStreams().iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        Object r0 = r2.next();
        if (StreamId.m404equalsimpl0(((CameraStream) r0).m241getIdptHMqGs(), r3) == false) goto L4;
    L10:
        return (CameraStream) r0;
    L8:
        r0 = null;
        goto L10
    }

    /* renamed from: get-iYJqvbA, reason: not valid java name */
    default OutputStream m400getiYJqvbA(int r3) {
        Iterator<T> r2 = getOutputs().iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        Object r0 = r2.next();
        if (OutputId.m293equalsimpl0(((OutputStream) r0).mo311getId4LaLFng(), r3) == false) goto L4;
    L10:
        return (OutputStream) r0;
    L8:
        r0 = null;
        goto L10
    }

    List<InputStream> getInputs();

    List<OutputStream> getOutputs();

    List<CameraStream> getStreams();
}
