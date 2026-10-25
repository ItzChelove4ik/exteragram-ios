package androidx.media3.decoder;

import androidx.media3.decoder.DecoderException;

/* loaded from: classes3.dex */
public interface Decoder<I, O, E extends DecoderException> {
    I dequeueInputBuffer();

    O dequeueOutputBuffer();

    void flush();

    String getName();

    void queueInputBuffer(I r1);

    void release();

    void setOutputStartTimeUs(long r1);
}
