package androidx.media3.exoplayer.drm;

import android.os.Build;
import androidx.media3.decoder.CryptoConfig;
import java.util.UUID;

/* loaded from: classes4.dex */
public final class FrameworkCryptoConfig implements CryptoConfig {
    public static final boolean WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC = false;

    @Deprecated
    public final boolean forceAllowInsecureDecoderComponents;
    public final byte[] sessionId;
    public final UUID uuid;

    static {
        if ("Amazon".equals(Build.MANUFACTURER) == false) goto L9;
        String r0 = Build.MODEL;
        if ("AFTM".equals(r0) == false) goto L7;
    L8:
        boolean r02 = true;
    L10:
        WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC = r02;
        return;
    L7:
        if ("AFTB".equals(r0) == true) goto L8;
    L9:
        r02 = false;
        goto L10
    }

    public FrameworkCryptoConfig(UUID r2, byte[] r3) {
        this(r2, r3, false);
    }

    @Deprecated
    public FrameworkCryptoConfig(UUID r1, byte[] r2, boolean r3) {
        this.uuid = r1;
        this.sessionId = r2;
        this.forceAllowInsecureDecoderComponents = r3;
    }
}
