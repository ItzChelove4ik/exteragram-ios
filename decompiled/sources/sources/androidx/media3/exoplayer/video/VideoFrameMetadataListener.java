package androidx.media3.exoplayer.video;

import android.media.MediaFormat;
import androidx.media3.common.Format;

/* loaded from: classes4.dex */
public interface VideoFrameMetadataListener {
    void onVideoFrameAboutToBeRendered(long r1, long r3, Format r5, MediaFormat r6);
}
