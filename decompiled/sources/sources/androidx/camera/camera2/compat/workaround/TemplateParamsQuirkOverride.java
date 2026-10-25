package androidx.camera.camera2.compat.workaround;

import android.hardware.camera2.CaptureRequest;
import androidx.camera.camera2.compat.quirk.CaptureIntentPreviewQuirk;
import androidx.camera.camera2.compat.quirk.ImageCaptureFailedForVideoSnapshotQuirk;
import androidx.camera.camera2.pipe.RequestTemplate;
import androidx.camera.core.impl.Quirks;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J'\u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\f0\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0002\b\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/camera/camera2/compat/workaround/TemplateParamsQuirkOverride;", "Landroidx/camera/camera2/compat/workaround/TemplateParamsOverride;", "quirks", "Landroidx/camera/core/impl/Quirks;", "<init>", "(Landroidx/camera/core/impl/Quirks;)V", "workaroundByCaptureIntentPreview", _UrlKt.FRAGMENT_ENCODE_SET, "workaroundByCaptureIntentStillCapture", "getOverrideParams", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "template", "Landroidx/camera/camera2/pipe/RequestTemplate;", "getOverrideParams-xlOpshk", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TemplateParamsQuirkOverride implements TemplateParamsOverride {
    private final boolean workaroundByCaptureIntentPreview;
    private final boolean workaroundByCaptureIntentStillCapture;

    public TemplateParamsQuirkOverride(Quirks r2) {
        this.workaroundByCaptureIntentPreview = CaptureIntentPreviewQuirk.Companion.workaroundByCaptureIntentPreview(r2);
        this.workaroundByCaptureIntentStillCapture = r2.contains(ImageCaptureFailedForVideoSnapshotQuirk.class);
    }

    @Override // androidx.camera.camera2.compat.workaround.TemplateParamsOverride
    /* renamed from: getOverrideParams-xlOpshk */
    public Map<CaptureRequest.Key<?>, Object> mo35getOverrideParamsxlOpshk(RequestTemplate r3) {
        if (r3 != null) goto L4;
    L9:
        if (r3 == null) goto L17;
        if (r3.m377unboximpl() != 4) goto L17;
        if (this.workaroundByCaptureIntentStillCapture == false) goto L17;
        return MapsKt.mapOf(TuplesKt.to(CaptureRequest.CONTROL_CAPTURE_INTENT, 2));
    L17:
        return MapsKt.emptyMap();
    L4:
        if (r3.m377unboximpl() != 3) goto L9;
        if (this.workaroundByCaptureIntentPreview == false) goto L9;
        return MapsKt.mapOf(TuplesKt.to(CaptureRequest.CONTROL_CAPTURE_INTENT, 1));
    }
}
