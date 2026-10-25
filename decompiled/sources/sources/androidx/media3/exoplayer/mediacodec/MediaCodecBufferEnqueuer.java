package androidx.media3.exoplayer.mediacodec;

import android.os.Bundle;
import androidx.media3.decoder.CryptoInfo;

/* loaded from: classes4.dex */
interface MediaCodecBufferEnqueuer {
    void flush();

    void maybeThrowException();

    void queueInputBuffer(int r1, int r2, int r3, long r4, int r6);

    void queueSecureInputBuffer(int r1, int r2, CryptoInfo r3, long r4, int r6);

    void setParameters(Bundle r1);

    void shutdown();

    void start();
}
