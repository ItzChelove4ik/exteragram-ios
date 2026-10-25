package androidx.camera.camera2.compat.workaround;

import android.hardware.camera2.CaptureRequest;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.StillCaptureFlashStopRepeatingQuirk;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestTemplate;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002¨\u0006\u0004"}, d2 = {"shouldStopRepeatingBeforeCapture", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request;", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStillCaptureFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StillCaptureFlow.kt\nandroidx/camera/camera2/compat/workaround/StillCaptureFlowKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,47:1\n1869#2,2:48\n*S KotlinDebug\n*F\n+ 1 StillCaptureFlow.kt\nandroidx/camera/camera2/compat/workaround/StillCaptureFlowKt\n*L\n32#1:48,2\n*E\n"})
/* loaded from: classes3.dex */
public abstract class StillCaptureFlowKt {
    public static final boolean shouldStopRepeatingBeforeCapture(List<Request> r7) {
        if (((StillCaptureFlashStopRepeatingQuirk) DeviceQuirks.INSTANCE.get(StillCaptureFlashStopRepeatingQuirk.class)) != null) goto L5;
        return false;
    L5:
        Iterator<T> r72 = r7.iterator();
        boolean r0 = false;
        boolean r2 = false;
    L7:
        if (r72.hasNext() == false) goto L23;
        Request r3 = (Request) r72.next();
        RequestTemplate r5 = r3.m362getTemplateejQnlcg();
        if (r5 != null) goto L11;
    L13:
        Integer r32 = (Integer) r3.get(CaptureRequest.CONTROL_AE_MODE);
        if (r32 != null) goto L17;
    L18:
        if (r32 == null) goto L7;
        if (r32.intValue() != 3) goto L7;
    L22:
        r2 = true;
        goto L7
    L17:
        if (r32.intValue() == 2) goto L22;
    L11:
        if (r5.m377unboximpl() != 2) goto L13;
        r0 = true;
        goto L13
    L23:
        if (r0 == false) goto L26;
        if (r2 == false) goto L26;
        return true;
    L26:
        return false;
    }
}
