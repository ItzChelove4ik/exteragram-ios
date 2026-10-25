package androidx.media3.exoplayer.hls;

import java.io.IOException;

/* loaded from: classes4.dex */
public final class SampleQueueMappingException extends IOException {
    public SampleQueueMappingException(String r3) {
        super("Unable to bind a sample queue to TrackGroup with MIME type " + r3 + ".");
    }
}
