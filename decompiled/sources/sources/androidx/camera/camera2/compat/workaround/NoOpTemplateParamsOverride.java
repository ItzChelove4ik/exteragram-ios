package androidx.camera.camera2.compat.workaround;

import android.hardware.camera2.CaptureRequest;
import androidx.camera.camera2.pipe.RequestTemplate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00070\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/compat/workaround/NoOpTemplateParamsOverride;", "Landroidx/camera/camera2/compat/workaround/TemplateParamsOverride;", "<init>", "()V", "getOverrideParams", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "template", "Landroidx/camera/camera2/pipe/RequestTemplate;", "getOverrideParams-xlOpshk", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NoOpTemplateParamsOverride implements TemplateParamsOverride {
    public static final NoOpTemplateParamsOverride INSTANCE = null;

    static {
        INSTANCE = new NoOpTemplateParamsOverride();
    }

    private NoOpTemplateParamsOverride() {
    }

    @Override // androidx.camera.camera2.compat.workaround.TemplateParamsOverride
    /* renamed from: getOverrideParams-xlOpshk, reason: not valid java name */
    public Map<CaptureRequest.Key<?>, Object> mo35getOverrideParamsxlOpshk(RequestTemplate r1) {
        return MapsKt.emptyMap();
    }
}
