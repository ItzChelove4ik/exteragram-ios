package androidx.media3.exoplayer.image;

import android.content.Context;
import android.graphics.Point;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.BitmapUtil;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.DecoderOutputBuffer;
import androidx.media3.decoder.SimpleDecoder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.image.ImageDecoder;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class BitmapFactoryImageDecoder extends SimpleDecoder<DecoderInputBuffer, ImageOutputBuffer, ImageDecoderException> implements ImageDecoder {
    private final Context context;
    private final int maxOutputSize;

    /* renamed from: androidx.media3.exoplayer.image.BitmapFactoryImageDecoder$1, reason: invalid class name */
    public class AnonymousClass1 extends ImageOutputBuffer {
        final /* synthetic */ BitmapFactoryImageDecoder this$0;

        public AnonymousClass1(BitmapFactoryImageDecoder r1) {
            this.this$0 = r1;
        }

        @Override // androidx.media3.decoder.DecoderOutputBuffer
        public void release() {
            BitmapFactoryImageDecoder.access$100(this.this$0, this);
        }
    }

    @Deprecated
    public interface BitmapDecoder {
    }

    public static final class Factory implements ImageDecoder.Factory {
        private final Context context;
        private int maxOutputSize;

        public Factory(Context r2) {
            this(r2, null);
        }

        private Factory(Context r1, BitmapDecoder r2) {
            this.context = r1;
            this.maxOutputSize = -1;
        }

        @Override // androidx.media3.exoplayer.image.ImageDecoder.Factory
        public BitmapFactoryImageDecoder createImageDecoder() {
            return new BitmapFactoryImageDecoder(this.context, null, this.maxOutputSize, null);
        }

        @Override // androidx.media3.exoplayer.image.ImageDecoder.Factory
        public /* bridge */ /* synthetic */ ImageDecoder createImageDecoder() {
            return createImageDecoder();
        }

        @Override // androidx.media3.exoplayer.image.ImageDecoder.Factory
        public int supportsFormat(Format r1) {
            String r0 = r1.sampleMimeType;
            if (r0 == null) goto L14;
            if (MimeTypes.isImage(r0) == false) goto L14;
            if (Util.isBitmapFactorySupportedMimeType(r1.sampleMimeType) == false) goto L12;
            return RendererCapabilities.create(4);
        L12:
            return RendererCapabilities.create(1);
        L14:
            return RendererCapabilities.create(0);
        }
    }

    private BitmapFactoryImageDecoder(Context r2, BitmapDecoder r3, int r4) {
        super(new DecoderInputBuffer[1], new ImageOutputBuffer[1]);
        this.context = r2;
        this.maxOutputSize = r4;
    }

    public /* synthetic */ BitmapFactoryImageDecoder(Context r1, BitmapDecoder r2, int r3, AnonymousClass1 r4) {
        this(r1, r2, r3);
    }

    public static /* synthetic */ void access$100(BitmapFactoryImageDecoder r0, DecoderOutputBuffer r1) {
        r0.releaseOutputBuffer(r1);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public DecoderInputBuffer createInputBuffer() {
        return new DecoderInputBuffer(1);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public /* bridge */ /* synthetic */ DecoderOutputBuffer createOutputBuffer() {
        return createOutputBuffer();
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public ImageOutputBuffer createOutputBuffer() {
        return new AnonymousClass1(this);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public /* bridge */ /* synthetic */ DecoderException createUnexpectedDecodeException(Throwable r1) {
        return createUnexpectedDecodeException(r1);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public ImageDecoderException createUnexpectedDecodeException(Throwable r2) {
        return new ImageDecoderException("Unexpected decode error", r2);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public /* bridge */ /* synthetic */ DecoderException decode(DecoderInputBuffer r1, DecoderOutputBuffer r2, boolean r3) {
        return decode(r1, (ImageOutputBuffer) r2, r3);
    }

    public ImageDecoderException decode(DecoderInputBuffer r6, ImageOutputBuffer r7, boolean r8) {
        ByteBuffer r82 = (ByteBuffer) Assertions.checkNotNull(r6.data);
        Assertions.checkState(r82.hasArray());
        if (r82.arrayOffset() != 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        int r02 = this.maxOutputSize;     // Catch: IOException -> L25 ParserException -> L28
        if (r02 != (-1)) goto L22;
        Context r5 = this.context;     // Catch: IOException -> L25 ParserException -> L28
        if (r5 == null) goto L21;
        Point r52 = Util.getCurrentDisplayModeSize(r5);     // Catch: IOException -> L25 ParserException -> L28
        int r03 = r52.x;     // Catch: IOException -> L25 ParserException -> L28
        int r53 = r52.y;     // Catch: IOException -> L25 ParserException -> L28
        Format r3 = r6.format;     // Catch: IOException -> L25 ParserException -> L28
        if (r3 == null) goto L20;
        int r4 = r3.tileCountHorizontal;     // Catch: IOException -> L25 ParserException -> L28
        if (r4 == (-1)) goto L17;
        r03 = r03 * r4;     // Catch: IOException -> L25 ParserException -> L28
    L17:
        int r32 = r3.tileCountVertical;     // Catch: IOException -> L25 ParserException -> L28
        if (r32 == (-1)) goto L20;
        r53 = r53 * r32;     // Catch: IOException -> L25 ParserException -> L28
    L20:
        r02 = (Math.max(r03, r53) * 2) - 1;
        goto L22
    L21:
        r02 = 4096;
    L22:
        r7.bitmap = BitmapUtil.decode(r82.array(), r82.remaining(), null, r02);     // Catch: IOException -> L25 ParserException -> L28
        r7.timeUs = r6.timeUs;
        return null;
    L28:
        e = move-exception;
        return new ImageDecoderException("Could not decode image data with BitmapFactory.", e);
    L25:
        e = move-exception;
        return new ImageDecoderException(e);
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.decoder.SimpleDecoder, androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ ImageOutputBuffer dequeueOutputBuffer() {
        return (ImageOutputBuffer) super.dequeueOutputBuffer();
    }

    @Override // androidx.media3.decoder.Decoder
    public String getName() {
        return "BitmapFactoryImageDecoder";
    }
}
