package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.drm.DrmSession;

/* loaded from: classes4.dex */
public final class FormatHolder {
    public DrmSession drmSession;
    public Format format;

    public FormatHolder() {
    }

    public void clear() {
        this.drmSession = null;
        this.format = null;
    }
}
