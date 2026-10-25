package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.params.InputConfiguration;
import android.view.Surface;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.InputStream;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.compat.CaptureSessionFactory;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ,\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidMSessionFactory;", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "streamGraph", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "graphConfig", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "<init>", "(Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;Landroidx/camera/camera2/pipe/CameraGraph$Config;)V", "create", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory$Result;", "cameraDevice", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "surfaces", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "captureSessionState", "Landroidx/camera/camera2/pipe/compat/CaptureSessionState;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCaptureSessionFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureSessionFactory.kt\nandroidx/camera/camera2/pipe/compat/AndroidMSessionFactory\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,588:1\n126#2:589\n153#2,3:590\n126#2:595\n153#2,3:596\n71#3,2:593\n71#3,2:599\n*S KotlinDebug\n*F\n+ 1 CaptureSessionFactory.kt\nandroidx/camera/camera2/pipe/compat/AndroidMSessionFactory\n*L\n122#1:589\n122#1:590,3\n135#1:595\n135#1:596,3\n126#1:593,2\n137#1:599,2\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidMSessionFactory implements CaptureSessionFactory {
    private final CameraGraph.Config graphConfig;
    private final StreamGraphImpl streamGraph;
    private final Threads threads;

    public AndroidMSessionFactory(Threads r1, StreamGraphImpl r2, CameraGraph.Config r3) {
        this.threads = r1;
        this.streamGraph = r2;
        this.graphConfig = r3;
    }

    @Override // androidx.camera.camera2.pipe.compat.CaptureSessionFactory
    public CaptureSessionFactory.Result create(CameraDeviceWrapper r8, Map<StreamId, ? extends Surface> r9, CaptureSessionState r10) {
        if (this.graphConfig.getInput() == null) goto L15;
        OutputStream.Config r0 = (OutputStream.Config) CollectionsKt.single(((InputStream.Config) CollectionsKt.single(this.graphConfig.getInput())).getStream().getOutputs());
        InputConfiguration r4 = new InputConfiguration(r0.getSize().getWidth(), r0.getSize().getHeight(), r0.m318getFormat8FPWQzE());
        ArrayList r02 = new ArrayList(r9.size());
        Iterator<Map.Entry<StreamId, ? extends Surface>> r5 = r9.entrySet().iterator();
    L6:
        if (r5.hasNext() == false) goto L9;
        r02.add(r5.next().getValue());
        goto L6
    L9:
        if (r8.createReprocessableCaptureSession(r4, r02, r10) == true) goto L27;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L13;
        android.util.Log.w("CXCP", "Failed to create reprocessable captures session from " + r8 + " for " + r10 + '!');
    L13:
        r10.onSessionFinalized();
        return CaptureSessionFactory.Result.Failed.INSTANCE;
    L27:
        return new CaptureSessionFactory.Result.Success(MapsKt.emptyMap(), CaptureSessionFactoryKt.access$buildSimpleOutputSurfaceMap(r9, this.streamGraph));
    L15:
        ArrayList r03 = new ArrayList(r9.size());
        Iterator<Map.Entry<StreamId, ? extends Surface>> r42 = r9.entrySet().iterator();
    L17:
        if (r42.hasNext() == false) goto L20;
        r03.add(r42.next().getValue());
        goto L17
    L20:
        if (r8.createCaptureSession(r03, r10) == true) goto L27;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L24;
        android.util.Log.w("CXCP", "Failed to create captures session from " + r8 + " for " + r10 + '!');
    L24:
        r10.onSessionFinalized();
        return CaptureSessionFactory.Result.Failed.INSTANCE;
    }
}
