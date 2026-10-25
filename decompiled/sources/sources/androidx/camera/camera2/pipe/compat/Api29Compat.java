package androidx.camera.camera2.pipe.compat;

import android.media.ImageWriter;
import android.view.Surface;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Api29Compat;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/view/Surface;", "surface", _UrlKt.FRAGMENT_ENCODE_SET, "maxImages", "format", "Landroid/media/ImageWriter;", "imageWriterNewInstance", "(Landroid/view/Surface;II)Landroid/media/ImageWriter;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Api29Compat {
    public static final Api29Compat INSTANCE = null;

    static {
        INSTANCE = new Api29Compat();
    }

    private Api29Compat() {
    }

    @JvmStatic
    public static final ImageWriter imageWriterNewInstance(Surface r0, int r1, int r2) {
        return ImageWriter.newInstance(r0, r1, r2);
    }
}
