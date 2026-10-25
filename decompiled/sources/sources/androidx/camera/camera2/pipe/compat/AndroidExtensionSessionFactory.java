package androidx.camera.camera2.pipe.compat;

import android.view.Surface;
import androidx.camera.camera2.pipe.CameraExtensionMetadata;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.StrictMode;
import androidx.camera.camera2.pipe.compat.CaptureSessionFactory;
import androidx.camera.camera2.pipe.core.HandlerExecutor;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import com.sun.jna.Native$$ExternalSyntheticBUOutline2;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ,\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidExtensionSessionFactory;", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "graphConfig", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "streamGraph", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "camera2MetadataProvider", "Landroidx/camera/camera2/pipe/compat/Camera2MetadataProvider;", "strictMode", "Landroidx/camera/camera2/pipe/StrictMode;", "<init>", "(Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/CameraGraph$Config;Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;Landroidx/camera/camera2/pipe/compat/Camera2MetadataProvider;Landroidx/camera/camera2/pipe/StrictMode;)V", "create", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory$Result;", "cameraDevice", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "surfaces", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "captureSessionState", "Landroidx/camera/camera2/pipe/compat/CaptureSessionState;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCaptureSessionFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureSessionFactory.kt\nandroidx/camera/camera2/pipe/compat/AndroidExtensionSessionFactory\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 StrictMode.kt\nandroidx/camera/camera2/pipe/StrictMode\n+ 4 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,588:1\n1#2:589\n25#3,4:590\n29#3,5:596\n25#3,4:601\n29#3,5:607\n71#4,2:594\n71#4,2:605\n71#4,2:612\n71#4,2:614\n*S KotlinDebug\n*F\n+ 1 CaptureSessionFactory.kt\nandroidx/camera/camera2/pipe/compat/AndroidExtensionSessionFactory\n*L\n333#1:590,4\n333#1:596,5\n340#1:601,4\n340#1:607,5\n333#1:594,2\n340#1:605,2\n351#1:612,2\n377#1:614,2\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidExtensionSessionFactory implements CaptureSessionFactory {
    private final Camera2MetadataProvider camera2MetadataProvider;
    private final CameraGraph.Config graphConfig;
    private final StreamGraphImpl streamGraph;
    private final StrictMode strictMode;
    private final Threads threads;

    public AndroidExtensionSessionFactory(Threads r1, CameraGraph.Config r2, StreamGraphImpl r3, Camera2MetadataProvider r4, StrictMode r5) {
        this.threads = r1;
        this.graphConfig = r2;
        this.streamGraph = r3;
        this.camera2MetadataProvider = r4;
        this.strictMode = r5;
    }

    public static final /* synthetic */ CameraGraph.Config access$getGraphConfig$p(AndroidExtensionSessionFactory r0) {
        return r0.graphConfig;
    }

    @Override // androidx.camera.camera2.pipe.compat.CaptureSessionFactory
    public CaptureSessionFactory.Result create(CameraDeviceWrapper r13, Map<StreamId, ? extends Surface> r14, CaptureSessionState r15) {
        boolean r0 = CameraGraph.OperatingMode.m216equalsimpl0(this.graphConfig.m204getSessionMode2uNL3no(), CameraGraph.OperatingMode.Companion.m220getEXTENSION2uNL3no());
        CameraGraph.Config r1 = this.graphConfig;
        if (r0 == false) goto L60;
        Object r02 = r1.getSessionParameters().get(CameraPipeKeys.INSTANCE.getCamera2ExtensionMode());
        if ((r02 instanceof Integer) == false) goto L7;
        Integer r03 = (Integer) r02;
    L8:
        if (r03 == null) goto L58;
        int r04 = r03.intValue();
        if (this.graphConfig.getInput() != null) goto L56;
        CameraMetadata r12 = this.camera2MetadataProvider.mo456awaitCameraMetadataEfqyGwQ(r13.mo412getCameraIdDz_R5H8());
        Set<Integer> r3 = r12.getSupportedExtensions();
        StrictMode r5 = this.strictMode;
        if (r3.contains(Integer.valueOf(r04)) == true) goto L21;
        String r32 = r13 + " does not support extension mode " + r04 + ". Supported extensions are " + r3;
        if (r5.getEnabled() == false) goto L16;
        Segment$$ExternalSyntheticBUOutline1.m(r32);
        return null;
    L16:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L21;
        android.util.Log.w("CXCP", r32);
    L21:
        if (this.graphConfig.getPostviewStream() == null) goto L36;
        CameraExtensionMetadata r16 = r12.awaitExtensionMetadata(r04);
        StrictMode r33 = this.strictMode;
        if (r16.isPostviewSupported() == true) goto L32;
        String r17 = r13 + " does not support Postview streams";
        if (r33.getEnabled() == false) goto L27;
        Segment$$ExternalSyntheticBUOutline1.m(r17);
        return null;
    L27:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L32;
        android.util.Log.w("CXCP", r17);
    L32:
        if (this.graphConfig.getPostviewStream().getOutputs().size() == 1) goto L36;
        Segment$$ExternalSyntheticBUOutline1.m("Postview streams can only have one OutputStream.config object");
        return null;
    L36:
        OutputConfigurations r11 = CaptureSessionFactoryKt.buildOutputConfigurations(this.graphConfig, this.streamGraph, r14);
        if (r11.getAll().isEmpty() == false) goto L44;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L41;
        android.util.Log.w("CXCP", "Failed to create OutputConfigurations for " + access$getGraphConfig$p(this));
    L41:
        r15.onSessionFinalized();
        return CaptureSessionFactory.Result.Failed.INSTANCE;
    L44:
        if (r11.getDeferred().isEmpty() == true) goto L46;
        Segment$$ExternalSyntheticBUOutline1.m("Deferred output is not supported for Extensions");
        return null;
    L46:
        if (r13.createExtensionSession(new ExtensionSessionConfigData(2, r11.getAll(), new HandlerExecutor(this.threads.getCamera2Handler()), r15, this.graphConfig.m205getSessionTemplatefGx8uWA(), this.graphConfig.getSessionParameters(), Integer.valueOf(r04), new ExtensionSessionState(r15), r11.getPostviewOutput())) == true) goto L53;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L50;
        android.util.Log.w("CXCP", "Failed to create ExtensionCaptureSession from " + r13 + " for " + r15 + '!');
    L50:
        r15.onSessionFinalized();
        return CaptureSessionFactory.Result.Failed.INSTANCE;
    L53:
        return new CaptureSessionFactory.Result.Success(r11.getDeferred(), r11.getOutputSurfaceMap());
    L56:
        Segment$$ExternalSyntheticBUOutline1.m("Reprocessing is not supported for Extensions");
        return null;
    L58:
        Segment$$ExternalSyntheticBUOutline1.m("The CameraPipeKeys.camera2ExtensionMode must be set in the sessionParameters of the CameraGraph.Config when creating an Extension CameraGraph.");
        return null;
    L7:
        r03 = null;
        goto L8
    L60:
        Native$$ExternalSyntheticBUOutline2.m("Unsupported session mode: ", CameraGraph.OperatingMode.m218toStringimpl(r1.m204getSessionMode2uNL3no()), " for Extension CameraGraph");
        return null;
    }
}
