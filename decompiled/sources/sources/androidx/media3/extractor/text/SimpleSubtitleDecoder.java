package androidx.media3.extractor.text;

import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.DecoderOutputBuffer;
import androidx.media3.decoder.SimpleDecoder;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public abstract class SimpleSubtitleDecoder extends SimpleDecoder<SubtitleInputBuffer, SubtitleOutputBuffer, SubtitleDecoderException> implements SubtitleDecoder {
    private final String name;

    /* renamed from: androidx.media3.extractor.text.SimpleSubtitleDecoder$1, reason: invalid class name */
    public class AnonymousClass1 extends SubtitleOutputBuffer {
        final /* synthetic */ SimpleSubtitleDecoder this$0;

        public AnonymousClass1(SimpleSubtitleDecoder r1) {
            this.this$0 = r1;
        }

        @Override // androidx.media3.decoder.DecoderOutputBuffer
        public void release() {
            SimpleSubtitleDecoder.access$000(this.this$0, this);
        }
    }

    public SimpleSubtitleDecoder(String r3) {
        super(new SubtitleInputBuffer[2], new SubtitleOutputBuffer[2]);
        this.name = r3;
        setInitialInputBufferSize(1024);
    }

    public static /* synthetic */ void access$000(SimpleSubtitleDecoder r0, DecoderOutputBuffer r1) {
        r0.releaseOutputBuffer(r1);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public /* bridge */ /* synthetic */ DecoderInputBuffer createInputBuffer() {
        return createInputBuffer();
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public final SubtitleInputBuffer createInputBuffer() {
        return new SubtitleInputBuffer();
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public /* bridge */ /* synthetic */ DecoderOutputBuffer createOutputBuffer() {
        return createOutputBuffer();
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public final SubtitleOutputBuffer createOutputBuffer() {
        return new AnonymousClass1(this);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public /* bridge */ /* synthetic */ DecoderException createUnexpectedDecodeException(Throwable r1) {
        return createUnexpectedDecodeException(r1);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public final SubtitleDecoderException createUnexpectedDecodeException(Throwable r2) {
        return new SubtitleDecoderException("Unexpected decode error", r2);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public /* bridge */ /* synthetic */ DecoderException decode(DecoderInputBuffer r1, DecoderOutputBuffer r2, boolean r3) {
        return decode((SubtitleInputBuffer) r1, (SubtitleOutputBuffer) r2, r3);
    }

    public abstract Subtitle decode(byte[] r1, int r2, boolean r3);

    public final SubtitleDecoderException decode(SubtitleInputBuffer r9, SubtitleOutputBuffer r10, boolean r11) {
        ByteBuffer r0 = (ByteBuffer) Assertions.checkNotNull(r9.data);     // Catch: SubtitleDecoderException -> L5
        Subtitle r5 = decode(r0.array(), r0.limit(), r11);     // Catch: SubtitleDecoderException -> L5
        r10.setContent(r9.timeUs, r5, r9.subsampleOffsetUs);     // Catch: SubtitleDecoderException -> L5
        r10.shouldBeSkipped = false;     // Catch: SubtitleDecoderException -> L5
        return null;
    L5:
        e = move-exception;
        return e;
    }

    @Override // androidx.media3.decoder.Decoder
    public final String getName() {
        return this.name;
    }

    @Override // androidx.media3.extractor.text.SubtitleDecoder
    public void setPositionUs(long r1) {
    }
}
