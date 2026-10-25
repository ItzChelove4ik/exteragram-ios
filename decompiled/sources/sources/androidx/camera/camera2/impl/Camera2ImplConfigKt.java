package androidx.camera.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import androidx.camera.core.impl.Config;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0003H\u0000\u001a\u001a\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00020\u0005*\u00020\u0006¨\u0006\u0007"}, d2 = {"createCaptureRequestOption", "Landroidx/camera/core/impl/Config$Option;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", "toParameters", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/Config;", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class Camera2ImplConfigKt {
    public static final Config.Option<Object> createCaptureRequestOption(CaptureRequest.Key<?> r2) {
        return Config.Option.create("camera2.captureRequest.option." + r2.getName(), Object.class, r2);
    }

    public static final Map<CaptureRequest.Key<?>, Object> toParameters(Config r5) {
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator<Config.Option<?>> r1 = r5.listOptions().iterator();
    L4:
        if (r1.hasNext() == false) goto L15;
        Config.Option<?> r2 = r1.next();
        Object r3 = r2.getToken();
        if ((r3 instanceof CaptureRequest.Key) == false) goto L8;
        CaptureRequest.Key r32 = (CaptureRequest.Key) r3;
    L9:
        if (r32 == null) goto L4;
        Object r22 = r5.retrieveOption(r2);
        if (r22 == null) goto L4;
        r0.put(r32, r22);
        goto L4
    L8:
        r32 = null;
        goto L9
    L15:
        return r0;
    }
}
