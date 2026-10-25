package androidx.media3.exoplayer.drm;

import androidx.media3.exoplayer.drm.ExoMediaDrm;
import java.util.UUID;

/* loaded from: classes4.dex */
public interface MediaDrmCallback {
    byte[] executeKeyRequest(UUID r1, ExoMediaDrm.KeyRequest r2);

    byte[] executeProvisionRequest(UUID r1, ExoMediaDrm.ProvisionRequest r2);
}
