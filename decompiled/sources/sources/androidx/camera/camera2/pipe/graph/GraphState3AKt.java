package androidx.camera.camera2.pipe.graph;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.AfMode;
import androidx.camera.camera2.pipe.AwbMode;
import androidx.camera.camera2.pipe.FlashMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a#\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/camera/camera2/pipe/graph/State3A;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "toCaptureRequestParameterMap", "(Landroidx/camera/camera2/pipe/graph/State3A;)Ljava/util/Map;", "camera-camera2-pipe"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGraphState3A.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GraphState3A.kt\nandroidx/camera/camera2/pipe/graph/GraphState3AKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,154:1\n1#2:155\n37#3:156\n36#3,3:157\n37#3:160\n36#3,3:161\n37#3:164\n36#3,3:165\n*S KotlinDebug\n*F\n+ 1 GraphState3A.kt\nandroidx/camera/camera2/pipe/graph/GraphState3AKt\n*L\n55#1:156\n55#1:157,3\n56#1:160\n56#1:161,3\n57#1:164\n57#1:165,3\n*E\n"})
/* loaded from: classes3.dex */
public abstract class GraphState3AKt {
    public static final Map<CaptureRequest.Key<?>, Object> toCaptureRequestParameterMap(State3A r5) {
        LinkedHashMap r0 = new LinkedHashMap();
        AeMode r1 = r5.m535getAeModeO_cDUUs();
        if (r1 == null) goto L5;
        int r12 = r1.m116unboximpl();
        r0.put(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(r12));
    L5:
        AfMode r13 = r5.m536getAfMode32_E3BI();
        if (r13 == null) goto L8;
        int r14 = r13.m127unboximpl();
        r0.put(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(r14));
    L8:
        AwbMode r15 = r5.m537getAwbModeaLFtWSU();
        if (r15 == null) goto L11;
        int r16 = r15.m146unboximpl();
        r0.put(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(r16));
    L11:
        FlashMode r17 = r5.m538getFlashModecL19HE();
        if (r17 == null) goto L14;
        int r18 = r17.m260unboximpl();
        r0.put(CaptureRequest.FLASH_MODE, Integer.valueOf(r18));
    L14:
        List<MeteringRectangle> r19 = r5.getAeRegions();
        if (r19 == null) goto L17;
        r0.put(CaptureRequest.CONTROL_AE_REGIONS, r19.toArray(new MeteringRectangle[0]));
    L17:
        List<MeteringRectangle> r110 = r5.getAfRegions();
        if (r110 == null) goto L20;
        r0.put(CaptureRequest.CONTROL_AF_REGIONS, r110.toArray(new MeteringRectangle[0]));
    L20:
        List<MeteringRectangle> r111 = r5.getAwbRegions();
        if (r111 == null) goto L23;
        r0.put(CaptureRequest.CONTROL_AWB_REGIONS, r111.toArray(new MeteringRectangle[0]));
    L23:
        Boolean r112 = r5.getAeLock();
        if (r112 == null) goto L26;
        r0.put(CaptureRequest.CONTROL_AE_LOCK, r112);
    L26:
        Boolean r52 = r5.getAwbLock();
        if (r52 == null) goto L29;
        r0.put(CaptureRequest.CONTROL_AWB_LOCK, r52);
    L29:
        return r0;
    }
}
