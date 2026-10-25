package androidx.camera.camera2.pipe.compat;

import android.view.Surface;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.compat.CaptureSessionFactory;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J,\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidMHighSpeedSessionFactory;", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory;", "streamGraph", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "<init>", "(Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;Landroidx/camera/camera2/pipe/core/Threads;)V", "create", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory$Result;", "cameraDevice", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "surfaces", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "captureSessionState", "Landroidx/camera/camera2/pipe/compat/CaptureSessionState;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCaptureSessionFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureSessionFactory.kt\nandroidx/camera/camera2/pipe/compat/AndroidMHighSpeedSessionFactory\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,588:1\n126#2:589\n153#2,3:590\n71#3,2:593\n*S KotlinDebug\n*F\n+ 1 CaptureSessionFactory.kt\nandroidx/camera/camera2/pipe/compat/AndroidMHighSpeedSessionFactory\n*L\n160#1:589\n160#1:590,3\n164#1:593,2\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidMHighSpeedSessionFactory implements CaptureSessionFactory {
    private final StreamGraphImpl streamGraph;
    private final Threads threads;

    public AndroidMHighSpeedSessionFactory(StreamGraphImpl r1, Threads r2) {
        this.streamGraph = r1;
        this.threads = r2;
    }

    @Override // androidx.camera.camera2.pipe.compat.CaptureSessionFactory
    public CaptureSessionFactory.Result create(CameraDeviceWrapper r4, Map<StreamId, ? extends Surface> r5, CaptureSessionState r6) {
        ArrayList r0 = new ArrayList(r5.size());
        Iterator<Map.Entry<StreamId, ? extends Surface>> r1 = r5.entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L7;
        r0.add(r1.next().getValue());
        goto L4
    L7:
        if (r4.createConstrainedHighSpeedCaptureSession(r0, r6) == true) goto L14;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L11;
        android.util.Log.w("CXCP", "Failed to create ConstrainedHighSpeedCaptureSession from " + r4 + " for " + r6 + '!');
    L11:
        r6.onSessionFinalized();
        return CaptureSessionFactory.Result.Failed.INSTANCE;
    L14:
        return new CaptureSessionFactory.Result.Success(MapsKt.emptyMap(), CaptureSessionFactoryKt.access$buildSimpleOutputSurfaceMap(r5, this.streamGraph));
    }
}
