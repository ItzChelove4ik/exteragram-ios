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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ,\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidPSessionFactory;", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "graphConfig", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "streamGraph", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "<init>", "(Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/CameraGraph$Config;Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;)V", "create", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory$Result;", "cameraDevice", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "surfaces", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "captureSessionState", "Landroidx/camera/camera2/pipe/compat/CaptureSessionState;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCaptureSessionFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureSessionFactory.kt\nandroidx/camera/camera2/pipe/compat/AndroidPSessionFactory\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,588:1\n71#2,2:589\n71#2,2:598\n1563#3:591\n1634#3,3:592\n1740#3,3:595\n*S KotlinDebug\n*F\n+ 1 CaptureSessionFactory.kt\nandroidx/camera/camera2/pipe/compat/AndroidPSessionFactory\n*L\n252#1:589,2\n286#1:598,2\n258#1:591\n258#1:592,3\n268#1:595,3\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidPSessionFactory implements CaptureSessionFactory {
    private final CameraGraph.Config graphConfig;
    private final StreamGraphImpl streamGraph;
    private final Threads threads;

    public AndroidPSessionFactory(Threads r1, CameraGraph.Config r2, StreamGraphImpl r3) {
        this.threads = r1;
        this.graphConfig = r2;
        this.streamGraph = r3;
    }

    public static final /* synthetic */ CameraGraph.Config access$getGraphConfig$p(AndroidPSessionFactory r0) {
        return r0.graphConfig;
    }

    @Override // androidx.camera.camera2.pipe.compat.CaptureSessionFactory
    public CaptureSessionFactory.Result create(CameraDeviceWrapper r18, Map<StreamId, ? extends Surface> r19, CaptureSessionState r20) {
        int r2 = this.graphConfig.m204getSessionMode2uNL3no();
        CameraGraph.OperatingMode.Companion r3 = CameraGraph.OperatingMode.Companion;
        if (CameraGraph.OperatingMode.m216equalsimpl0(r2, r3.m222getNORMAL2uNL3no()) == false) goto L6;
        int r8 = 0;
    L12:
        OutputConfigurations r22 = CaptureSessionFactoryKt.buildOutputConfigurations(this.graphConfig, this.streamGraph, r19);
        if (r22.getAll().isEmpty() == true) goto L15;
        List<InputStream.Config> r32 = this.graphConfig.getInput();
        if (r32 == null) goto L26;
        List<InputStream.Config> r33 = r32;
        ArrayList r7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r33, 10));
        Iterator<T> r34 = r33.iterator();
    L23:
        if (r34.hasNext() == false) goto L25;
        OutputStream.Config r9 = (OutputStream.Config) CollectionsKt.single(((InputStream.Config) r34.next()).getStream().getOutputs());
        r7.add(new InputConfigData(r9.getSize().getWidth(), r9.getSize().getHeight(), r9.m318getFormat8FPWQzE()));
        goto L23
    L25:
        ArrayList r92 = r7;
    L27:
        if (r92 == null) goto L39;
        if (r92.isEmpty() == true) goto L39;
        int r35 = r92.size();
        int r72 = 0;
    L32:
        if (r72 >= r35) goto L39;
        Object r10 = r92.get(r72);
        r72 = r72 + 1;
        if (((InputConfigData) r10).getFormat() == ((InputConfigData) r92.get(0)).getFormat()) goto L32;
        Segment$$ExternalSyntheticBUOutline1.m("All InputStream.Config objects must have the same format for multi resolution");
        return null;
    L39:
        if (r18.createCaptureSession(new SessionConfigData(r8, r92, r22.getAll(), this.threads.getCamera2Executor(), r20, this.graphConfig.m205getSessionTemplatefGx8uWA(), this.graphConfig.getSessionParameters(), this.graphConfig.m203getSessionColorSpacedxVZaPA(), null)) == true) goto L47;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L43;
        StringBuilder r0 = new StringBuilder("Failed to create capture session from ");
        r0.append(r18);
        r0.append(" for ");
        CaptureSessionState r12 = r20;
        r0.append(r12);
        r0.append('!');
        android.util.Log.w("CXCP", r0.toString());
    L44:
        r12.onSessionFinalized();
        return CaptureSessionFactory.Result.Failed.INSTANCE;
    L43:
        r12 = r20;
        goto L44
    L47:
        return new CaptureSessionFactory.Result.Success(r22.getDeferred(), r22.getOutputSurfaceMap());
    L26:
        r92 = null;
        goto L27
    L15:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L17;
        android.util.Log.w("CXCP", "Failed to create OutputConfigurations for " + access$getGraphConfig$p(this));
    L17:
        r20.onSessionFinalized();
        return CaptureSessionFactory.Result.Failed.INSTANCE;
    L6:
        if (CameraGraph.OperatingMode.m216equalsimpl0(r2, r3.m221getHIGH_SPEED2uNL3no()) == false) goto L9;
        int r23 = 1;
    L8:
        r8 = r23;
        goto L12
    L9:
        boolean r24 = CameraGraph.OperatingMode.m216equalsimpl0(r2, r3.m220getEXTENSION2uNL3no());
        CameraGraph.Config r36 = this.graphConfig;
        if (r24 == true) goto L48;
        r23 = r36.m204getSessionMode2uNL3no();
        goto L8
    L48:
        Buffer$$ExternalSyntheticBUOutline4.m("Unsupported session mode: ", CameraGraph.OperatingMode.m218toStringimpl(r36.m204getSessionMode2uNL3no()));
        return null;
    }
}
