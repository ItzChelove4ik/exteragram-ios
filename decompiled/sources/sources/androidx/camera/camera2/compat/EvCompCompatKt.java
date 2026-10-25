package androidx.camera.camera2.compat;

import android.util.Range;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"EMPTY_RANGE", "Landroid/util/Range;", _UrlKt.FRAGMENT_ENCODE_SET, "getEMPTY_RANGE", "()Landroid/util/Range;", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class EvCompCompatKt {
    private static final Range<Integer> EMPTY_RANGE = null;

    static {
        EMPTY_RANGE = new Range(0, 0);
    }

    public static final Range<Integer> getEMPTY_RANGE() {
        return EMPTY_RANGE;
    }
}
