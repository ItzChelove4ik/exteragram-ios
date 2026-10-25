package androidx.media3.exoplayer.source;

import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;

/* loaded from: classes4.dex */
public interface SampleStream {
    boolean isReady();

    void maybeThrowError();

    int readData(FormatHolder r1, DecoderInputBuffer r2, int r3);

    int skipData(long r1);
}
