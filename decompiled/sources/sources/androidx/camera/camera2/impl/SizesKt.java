package androidx.camera.camera2.impl;

import android.util.Size;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroid/util/Size;", _UrlKt.FRAGMENT_ENCODE_SET, "area", "(Landroid/util/Size;)I", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class SizesKt {
    public static final int area(Size r1) {
        return r1.getWidth() * r1.getHeight();
    }
}
