package androidx.camera.core.impl.compat;

import android.media.CamcorderProfile;
import android.media.EncoderProfiles;
import android.os.Build;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.EncoderProfilesProxy;

/* loaded from: classes3.dex */
public abstract class EncoderProfilesProxyCompat {
    public static EncoderProfilesProxy from(CamcorderProfile r3) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 31) goto L6;
        Logger.w("EncoderProfilesProxyCompat", "Should use from(EncoderProfiles) on API " + r0 + "instead. CamcorderProfile is deprecated on API 31.");
    L6:
        return EncoderProfilesProxyCompatBaseImpl.from(r3);
    }

    public static EncoderProfilesProxy from(EncoderProfiles r3) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 33) goto L7;
        return EncoderProfilesProxyCompatApi33Impl.from(r3);
    L7:
        if (r0 < 31) goto L11;
        return EncoderProfilesProxyCompatApi31Impl.from(r3);
    L11:
        throw new RuntimeException("Unable to call from(EncoderProfiles) on API " + r0 + ". Version 31 or higher required.");
    }
}
