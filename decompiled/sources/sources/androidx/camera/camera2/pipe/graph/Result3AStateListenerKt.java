package androidx.camera.camera2.pipe.graph;

import android.hardware.camera2.CaptureResult;
import androidx.camera.camera2.pipe.FrameMetadata;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\u001a.\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004H\u0000¨\u0006\b"}, d2 = {"toConditionChecker", "Lkotlin/Function1;", "Landroidx/camera/camera2/pipe/FrameMetadata;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureResult$Key;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class Result3AStateListenerKt {
    public static boolean $r8$lambda$6eKrfLPahyf8ytTlPs2wBLGcMEY(Map r2, FrameMetadata r3) {
        Iterator r22 = r2.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L9;
        Map.Entry r0 = (Map.Entry) r22.next();
        CaptureResult.Key r1 = (CaptureResult.Key) r0.getKey();
        List r02 = (List) r0.getValue();
        List r03 = r02;
        if (CollectionsKt.contains(r03, r3.get(r1)) == true) goto L4;
        return false;
    L9:
        return true;
    }

    public static final Function1<FrameMetadata, Boolean> toConditionChecker(Map<CaptureResult.Key<?>, ? extends List<? extends Object>> r1) {
        return new Result3AStateListenerKt$$ExternalSyntheticLambda0(r1);
    }
}
