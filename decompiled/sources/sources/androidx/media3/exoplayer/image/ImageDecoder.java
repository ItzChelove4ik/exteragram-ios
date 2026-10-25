package androidx.media3.exoplayer.image;

import androidx.media3.common.Format;
import androidx.media3.decoder.Decoder;
import androidx.media3.decoder.DecoderInputBuffer;

/* loaded from: classes4.dex */
public interface ImageDecoder extends Decoder<DecoderInputBuffer, ImageOutputBuffer, ImageDecoderException> {

    public interface Factory {
        ImageDecoder createImageDecoder();

        int supportsFormat(Format r1);
    }

    @Override // androidx.media3.decoder.Decoder
    ImageOutputBuffer dequeueOutputBuffer();

    void queueInputBuffer(DecoderInputBuffer r1);
}
