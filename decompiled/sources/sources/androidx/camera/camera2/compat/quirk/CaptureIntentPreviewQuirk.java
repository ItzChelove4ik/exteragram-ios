package androidx.camera.camera2.compat.quirk;

import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.Quirks;
import java.util.Iterator;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004J\b\u0010\u0002\u001a\u00020\u0003H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/compat/quirk/CaptureIntentPreviewQuirk;", "Landroidx/camera/core/impl/Quirk;", "workaroundByCaptureIntentPreview", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CaptureIntentPreviewQuirk extends Quirk {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/compat/quirk/CaptureIntentPreviewQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "workaroundByCaptureIntentPreview", _UrlKt.FRAGMENT_ENCODE_SET, "quirks", "Landroidx/camera/core/impl/Quirks;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        static {
            $$INSTANCE = new Companion();
        }

        private Companion() {
        }

        public final boolean workaroundByCaptureIntentPreview(Quirks r1) {
            Iterator r0 = r1.getAll(CaptureIntentPreviewQuirk.class).iterator();
        L4:
            if (r0.hasNext() == false) goto L9;
            if (((CaptureIntentPreviewQuirk) r0.next()).workaroundByCaptureIntentPreview() == false) goto L4;
            return true;
        L9:
            return false;
        }
    }

    static {
        Companion = Companion.$$INSTANCE;
    }

    default boolean workaroundByCaptureIntentPreview() {
        return true;
    }
}
