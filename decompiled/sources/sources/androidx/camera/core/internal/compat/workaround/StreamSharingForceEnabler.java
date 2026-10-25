package androidx.camera.core.internal.compat.workaround;

import androidx.camera.core.UseCase;
import androidx.camera.core.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.internal.compat.quirk.ImageCaptureFailedForSpecificCombinationQuirk;
import androidx.camera.core.internal.compat.quirk.PreviewGreenTintQuirk;
import java.util.Collection;

/* loaded from: classes3.dex */
public class StreamSharingForceEnabler {
    private final PreviewGreenTintQuirk mPreviewGreenTintQuirk;
    private final ImageCaptureFailedForSpecificCombinationQuirk mSpecificCombinationQuirk;

    public StreamSharingForceEnabler() {
        this.mSpecificCombinationQuirk = (ImageCaptureFailedForSpecificCombinationQuirk) DeviceQuirks.get(ImageCaptureFailedForSpecificCombinationQuirk.class);
        this.mPreviewGreenTintQuirk = (PreviewGreenTintQuirk) DeviceQuirks.get(PreviewGreenTintQuirk.class);
    }

    public boolean shouldForceEnableStreamSharing(String r2, Collection<UseCase> r3) {
        ImageCaptureFailedForSpecificCombinationQuirk r0 = this.mSpecificCombinationQuirk;
        if (r0 == null) goto L7;
        return r0.shouldForceEnableStreamSharing(r2, r3);
    L7:
        if (this.mPreviewGreenTintQuirk != null) goto L9;
        return false;
    L9:
        return PreviewGreenTintQuirk.shouldForceEnableStreamSharing(r2, r3);
    }
}
