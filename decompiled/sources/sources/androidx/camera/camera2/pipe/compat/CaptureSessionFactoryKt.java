package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.params.OutputConfiguration;
import android.os.Build;
import android.util.Size;
import android.view.Surface;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.OutputId;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.compat.AndroidOutputConfiguration;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import androidx.camera.camera2.pipe.media.AndroidMultiResolutionImageReader;
import androidx.camera.camera2.pipe.media.ImageSource;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0001\u001a0\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\t0\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u0006\f"}, d2 = {"buildOutputConfigurations", "Landroidx/camera/camera2/pipe/compat/OutputConfigurations;", "graphConfig", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "streamGraph", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "surfaces", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "buildSimpleOutputSurfaceMap", "Landroidx/camera/camera2/pipe/OutputId;", "camera-camera2-pipe"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCaptureSessionFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureSessionFactory.kt\nandroidx/camera/camera2/pipe/compat/CaptureSessionFactoryKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,588:1\n1617#2,9:589\n1869#2:598\n1870#2:600\n1626#2:601\n774#2:602\n865#2,2:603\n774#2:607\n865#2,2:608\n1#3:599\n71#4,2:605\n71#4,2:610\n*S KotlinDebug\n*F\n+ 1 CaptureSessionFactory.kt\nandroidx/camera/camera2/pipe/compat/CaptureSessionFactoryKt\n*L\n469#1:589,9\n469#1:598\n469#1:600\n469#1:601\n475#1:602\n475#1:603,2\n523#1:607\n523#1:608,2\n469#1:599\n511#1:605,2\n546#1:610,2\n*E\n"})
/* loaded from: classes3.dex */
public abstract class CaptureSessionFactoryKt {
    public static final /* synthetic */ Map access$buildSimpleOutputSurfaceMap(Map r0, StreamGraphImpl r1) {
        return buildSimpleOutputSurfaceMap(r0, r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final OutputConfigurations buildOutputConfigurations(CameraGraph.Config r34, StreamGraphImpl r35, Map<StreamId, ? extends Surface> r36) {
        ArrayList r2 = new ArrayList();
        LinkedHashMap r3 = new LinkedHashMap();
        LinkedHashMap r4 = new LinkedHashMap();
        LinkedHashMap r5 = new LinkedHashMap();
        Iterator<Map.Entry<StreamId, ImageSource>> r6 = r35.getImageSourceMap$camera_camera2_pipe().entrySet().iterator();
    L3:
        boolean r9 = true;
        OutputConfigurations r10 = null;
        if (r6.hasNext() == false) goto L35;
        Map.Entry<StreamId, ImageSource> r7 = r6.next();
        int r11 = r7.getKey().m407unboximpl();
        ImageSource r72 = r7.getValue();
        CameraStream r112 = r35.m399getaKI5c8E(r11);
        if (r112 == null) goto L33;
        List<OutputStream> r113 = r112.getOutputs();
        if (r113.size() == 1) goto L3;
        if (Build.VERSION.SDK_INT < 31) goto L31;
        Object r73 = r72.unwrapAs(Reflection.getOrCreateKotlinClass(AndroidMultiResolutionImageReader.class));
        if (r73 == null) goto L29;
        List<OutputConfiguration> r74 = ((AndroidMultiResolutionImageReader) r73).getOutputConfigurations$camera_camera2_pipe();
        if (r74.size() != r113.size()) goto L27;
        int r92 = r113.size();
        int r12 = 0;
    L17:
        if (r12 >= r92) goto L3;
        OutputStream r13 = r113.get(r12);
        OutputConfiguration r14 = r74.get(r12);
        StreamGraphImpl.OutputConfig r132 = r35.getOutputConfigMap$camera_camera2_pipe().get(r13);
        if (r132 == null) goto L25;
        StreamGraphImpl.OutputConfig r133 = r132;
        if (r133.getExternalOutputConfig() != null) goto L23;
        r5.put(r133, r14);
        r12 = r12 + 1;
        goto L17
    L23:
        Segment$$ExternalSyntheticBUOutline1.m("External OutputConfiguration shouldn't be set in multi-output streams configured with ImageSource.Config");
        return null;
    L25:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L27:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    L29:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L31:
        f$$ExternalSyntheticBUOutline1.m("Cannot configure multiple outputs pre-S!");
        return null;
    L33:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L35:
        Iterator<CameraStream> r62 = r35.getStreams().iterator();
    L37:
        if (r62.hasNext() == false) goto L58;
        CameraStream r75 = r62.next();
        List<OutputStream> r114 = r75.getOutputs();
        if (r114.size() == 1) goto L40;
        Iterator<OutputStream> r115 = r114.iterator();
    L45:
        if (r115.hasNext() == false) goto L37;
        OutputStream r122 = r115.next();
        StreamGraphImpl.OutputConfig r134 = r35.getOutputConfigMap$camera_camera2_pipe().get(r122);
        if (r134 == null) goto L56;
        StreamGraphImpl.OutputConfig r135 = r134;
        OutputConfiguration r142 = r135.getExternalOutputConfig();
        if (r142 != null) goto L51;
        r142 = (OutputConfiguration) r5.get(r135);
    L51:
        if (r142 == null) goto L53;
        Surface r136 = r142.getSurface();
    L54:
        if (r136 == null) goto L45;
        r4.put(OutputId.m290boximpl(r122.mo311getId4LaLFng()), r136);
        goto L45
    L53:
        r136 = r36.get(StreamId.m401boximpl(r75.m241getIdptHMqGs()));
        goto L54
    L56:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L40:
        Surface r76 = r36.get(StreamId.m401boximpl(r75.m241getIdptHMqGs()));
        if (r76 == null) goto L37;
        r4.put(OutputId.m290boximpl(((OutputStream) CollectionsKt.single(r114)).mo311getId4LaLFng()), r76);
        goto L37
    L58:
        Iterator<StreamGraphImpl.OutputConfig> r63 = r35.getOutputConfigs$camera_camera2_pipe().iterator();
        OutputConfigurationWrapper r77 = null;
    L60:
        if (r63.hasNext() == false) goto L144;
        StreamGraphImpl.OutputConfig r8 = r63.next();
        List<CameraStream> r116 = r8.getStreams();
        ArrayList r123 = new ArrayList();
        Iterator<T> r117 = r116.iterator();
    L63:
        if (r117.hasNext() == false) goto L67;
        Surface r137 = r36.get(StreamId.m401boximpl(((CameraStream) r117.next()).m241getIdptHMqGs()));
        if (r137 == null) goto L63;
        r123.add(r137);
        goto L63
    L67:
        OutputConfiguration r118 = r8.getExternalOutputConfig();
        if (r118 != null) goto L70;
        r118 = (OutputConfiguration) r5.get(r8);
    L70:
        OutputConfiguration r143 = r118;
        if (r143 != null) goto L73;
        OutputConfigurations r16 = r10;
        int r17 = -1;
        if (r8.getDeferrable() == false) goto L105;
        if (r123.size() == r8.getStreams().size()) goto L105;
        AndroidOutputConfiguration.Companion r19 = AndroidOutputConfiguration.Companion;
        Size r28 = r8.getSize();
        OutputStream.OutputType r22 = r8.getDeferredOutputType();
        OutputStream.MirrorMode r23 = r8.m547getMirrorModedO1_9xk();
        r8.m550getTimestampBasepcPfPbY();
        OutputStream.DynamicRangeProfile r25 = r8.m545getDynamicRangeProfileOoVcG5w();
        OutputStream.StreamUseCase r26 = r8.m548getStreamUseCase8x2ez34();
        List<Object> r27 = r8.getSensorPixelModes();
        boolean r29 = r8.getSurfaceSharing();
        Integer r93 = r8.getGroupNumber();
        if (r93 == null) goto L90;
        r17 = r93.intValue();
    L90:
        int r30 = r17;
        if (CameraId.m230equalsimpl0(r8.m544getCameraDz_R5H8(), r34.m200getCameraDz_R5H8()) == true) goto L93;
        String r31 = r8.m544getCameraDz_R5H8();
    L94:
        OutputConfigurationWrapper r94 = AndroidOutputConfiguration.Companion.m421creategWWoySg$default(r19, null, null, r22, r23, null, r25, r26, r27, r28, r29, r30, r31, 2, null);
        if (r94 == null) goto L97;
        r2.add(r94);
        Iterator<CameraStream> r82 = r8.getStreamBuilder$camera_camera2_pipe().iterator();
    L102:
        if (r82.hasNext() == false) goto L99;
        r3.put(StreamId.m401boximpl(r82.next().m241getIdptHMqGs()), r94);
    L99:
        boolean r102 = true;
    L130:
        r9 = r102;
        r10 = r16;
        goto L60
    L97:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L99;
        android.util.Log.w("CXCP", "Failed to create AndroidOutputConfiguration for " + r8);
        goto L99
    L93:
        r31 = r16;
    L105:
        if (r123.size() != r8.getStreams().size()) goto L135;
        AndroidOutputConfiguration.Companion r192 = AndroidOutputConfiguration.Companion;
        Surface r20 = (Surface) CollectionsKt.first(r123);
        OutputStream.MirrorMode r232 = r8.m547getMirrorModedO1_9xk();
        r8.m550getTimestampBasepcPfPbY();
        OutputStream.DynamicRangeProfile r252 = r8.m545getDynamicRangeProfileOoVcG5w();
        OutputStream.StreamUseCase r262 = r8.m548getStreamUseCase8x2ez34();
        List<Object> r272 = r8.getSensorPixelModes();
        Size r282 = r8.getSize();
        boolean r292 = r8.getSurfaceSharing();
        Integer r95 = r8.getGroupNumber();
        if (r95 == null) goto L109;
        r17 = r95.intValue();
    L109:
        int r302 = r17;
        if (CameraId.m230equalsimpl0(r8.m544getCameraDz_R5H8(), r34.m200getCameraDz_R5H8()) == true) goto L112;
        String r312 = r8.m544getCameraDz_R5H8();
    L113:
        OutputConfigurationWrapper r96 = AndroidOutputConfiguration.Companion.m421creategWWoySg$default(r192, r20, null, null, r232, null, r252, r262, r272, r282, r292, r302, r312, 6, null);
        if (r96 == null) goto L116;
        r102 = true;
        Iterator r119 = CollectionsKt.drop(r123, 1).iterator();
    L120:
        if (r119.hasNext() == false) goto L123;
        r96.addSurface((Surface) r119.next());
        goto L120
    L123:
        if (r34.getPostviewStream() == null) goto L134;
        CameraStream r1110 = r35.get(r34.getPostviewStream());
        if (r1110 == null) goto L132;
        if (r77 == null) goto L128;
    L131:
        r2.add(r96);
        goto L130
    L128:
        if (r8.getStreams().contains(r1110) == false) goto L131;
        r77 = r96;
        goto L130
    L132:
        Segment$$ExternalSyntheticBUOutline1.m("Postview Stream in StreamGraph cannot be null for reprocessing request");
        return r16;
    L134:
        r2.add(r96);
        goto L130
    L116:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L99;
        android.util.Log.w("CXCP", "Failed to create AndroidOutputConfiguration for " + r8);
        goto L99
    L112:
        r312 = r16;
        goto L113
    L135:
        List<CameraStream> r0 = r8.getStreams();
        ArrayList r24 = new ArrayList();
        Iterator<T> r02 = r0.iterator();
    L137:
        if (r02.hasNext() == false) goto L141;
        Object r32 = r02.next();
        if (r36.containsKey(StreamId.m401boximpl(((CameraStream) r32).m241getIdptHMqGs())) == true) goto L137;
        r24.add(r32);
        goto L137
    L141:
        Camera2DeviceCloserImpl$$ExternalSyntheticBUOutline0.m("Surfaces are not yet available for ", r8, "! Missing surfaces for ", r24);
        return r16;
    L73:
        if (r123.size() != r8.getStreams().size()) goto L75;
        r2.add(new AndroidOutputConfiguration(r143, false, 1, null, null));
        r16 = r10;
        r102 = r9;
        goto L130
    L75:
        List<CameraStream> r03 = r8.getStreams();
        ArrayList r210 = new ArrayList();
        Iterator<T> r04 = r03.iterator();
    L77:
        if (r04.hasNext() == false) goto L81;
        Object r33 = r04.next();
        if (r36.containsKey(StreamId.m401boximpl(((CameraStream) r33).m241getIdptHMqGs())) == true) goto L77;
        r210.add(r33);
        goto L77
    L81:
        Camera2DeviceCloserImpl$$ExternalSyntheticBUOutline0.m("Surfaces are not yet available for ", r8, "! Missing surfaces for ", r210);
        return r10;
    L144:
        return new OutputConfigurations(r2, r3, r77, r4);
    }

    private static final Map<OutputId, Surface> buildSimpleOutputSurfaceMap(Map<StreamId, ? extends Surface> r4, StreamGraphImpl r5) {
        Map r0 = MapsKt.createMapBuilder();
        Iterator<CameraStream> r52 = r5.getStreams().iterator();
    L4:
        if (r52.hasNext() == false) goto L13;
        CameraStream r1 = r52.next();
        Surface r2 = r4.get(StreamId.m401boximpl(r1.m241getIdptHMqGs()));
        if (r2 == null) goto L4;
        Iterator<OutputStream> r12 = r1.getOutputs().iterator();
    L10:
        if (r12.hasNext() == false) goto L4;
        r0.put(OutputId.m290boximpl(r12.next().mo311getId4LaLFng()), r2);
        goto L10
    L13:
        return MapsKt.build(r0);
    }
}
