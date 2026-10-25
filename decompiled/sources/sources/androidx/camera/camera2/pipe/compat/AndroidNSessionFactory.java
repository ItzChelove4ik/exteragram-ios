package androidx.camera.camera2.pipe.compat;

import android.view.Surface;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.InputStream;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.compat.CaptureSessionFactory;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ,\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidNSessionFactory;", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "streamGraph", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "graphConfig", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "<init>", "(Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;Landroidx/camera/camera2/pipe/CameraGraph$Config;)V", "create", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory$Result;", "cameraDevice", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "surfaces", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "captureSessionState", "Landroidx/camera/camera2/pipe/compat/CaptureSessionState;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCaptureSessionFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureSessionFactory.kt\nandroidx/camera/camera2/pipe/compat/AndroidNSessionFactory\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,588:1\n71#2,2:589\n71#2,2:591\n*S KotlinDebug\n*F\n+ 1 CaptureSessionFactory.kt\nandroidx/camera/camera2/pipe/compat/AndroidNSessionFactory\n*L\n191#1:589,2\n215#1:591,2\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidNSessionFactory implements CaptureSessionFactory {
    private final CameraGraph.Config graphConfig;
    private final StreamGraphImpl streamGraph;
    private final Threads threads;

    public AndroidNSessionFactory(Threads r1, StreamGraphImpl r2, CameraGraph.Config r3) {
        this.threads = r1;
        this.streamGraph = r2;
        this.graphConfig = r3;
    }

    public static final /* synthetic */ CameraGraph.Config access$getGraphConfig$p(AndroidNSessionFactory r0) {
        return r0.graphConfig;
    }

    @Override // androidx.camera.camera2.pipe.compat.CaptureSessionFactory
    public CaptureSessionFactory.Result create(CameraDeviceWrapper r5, Map<StreamId, ? extends Surface> r6, CaptureSessionState r7) {
        OutputConfigurations r62 = CaptureSessionFactoryKt.buildOutputConfigurations(this.graphConfig, this.streamGraph, r6);
        if (r62.getAll().isEmpty() == false) goto L10;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L7;
        android.util.Log.w("CXCP", "Failed to create OutputConfigurations for " + access$getGraphConfig$p(this));
    L7:
        r7.onSessionFinalized();
        return CaptureSessionFactory.Result.Failed.INSTANCE;
    L10:
        if (this.graphConfig.getInput() != null) goto L12;
        boolean r4 = r5.createCaptureSessionByOutputConfigurations(r62.getAll(), r7);
    L13:
        if (r4 == true) goto L20;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L17;
        android.util.Log.w("CXCP", "Failed to create capture session from " + r5 + " for " + r7 + '!');
    L17:
        r7.onSessionFinalized();
        return CaptureSessionFactory.Result.Failed.INSTANCE;
    L20:
        return new CaptureSessionFactory.Result.Success(MapsKt.emptyMap(), r62.getOutputSurfaceMap());
    L12:
        OutputStream.Config r42 = (OutputStream.Config) CollectionsKt.single(((InputStream.Config) CollectionsKt.single(this.graphConfig.getInput())).getStream().getOutputs());
        r4 = r5.createReprocessableCaptureSessionByConfigurations(new InputConfigData(r42.getSize().getWidth(), r42.getSize().getHeight(), r42.m318getFormat8FPWQzE()), r62.getAll(), r7);
        goto L13
    }
}
