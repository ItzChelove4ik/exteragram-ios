package androidx.media3.exoplayer.image;

import android.graphics.Bitmap;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.image.ImageDecoder;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class ImageRenderer extends BaseRenderer {
    private boolean codecNeedsInitialization;
    private int currentTileIndex;
    private ImageDecoder decoder;
    private final ImageDecoder.Factory decoderFactory;
    private int decoderReinitializationState;
    private int firstFrameState;
    private final DecoderInputBuffer flagsOnlyBuffer;
    private ImageOutput imageOutput;
    private DecoderInputBuffer inputBuffer;
    private Format inputFormat;
    private boolean inputStreamEnded;
    private long largestQueuedPresentationTimeUs;
    private long lastProcessedOutputBufferTimeUs;
    private TileInfo nextTileInfo;
    private Bitmap outputBitmap;
    private boolean outputStreamEnded;
    private OutputStreamInfo outputStreamInfo;
    private final ArrayDeque<OutputStreamInfo> pendingOutputStreamChanges;
    private boolean readyToOutputTiles;
    private TileInfo tileInfo;

    public static final class OutputStreamInfo {
        public static final OutputStreamInfo UNSET = null;
        public final long previousStreamLastBufferTimeUs;
        public final long streamOffsetUs;

        static {
            UNSET = new OutputStreamInfo(-9223372036854775807L, -9223372036854775807L);
        }

        public OutputStreamInfo(long r1, long r3) {
            this.previousStreamLastBufferTimeUs = r1;
            this.streamOffsetUs = r3;
        }
    }

    public static class TileInfo {
        private final long presentationTimeUs;
        private Bitmap tileBitmap;
        private final int tileIndex;

        public TileInfo(int r1, long r2) {
            this.tileIndex = r1;
            this.presentationTimeUs = r2;
        }

        public long getPresentationTimeUs() {
            return this.presentationTimeUs;
        }

        public Bitmap getTileBitmap() {
            return this.tileBitmap;
        }

        public int getTileIndex() {
            return this.tileIndex;
        }

        public boolean hasTileBitmap() {
            if (this.tileBitmap == null) goto L6;
            return true;
        L6:
            return false;
        }

        public void setTileBitmap(Bitmap r1) {
            this.tileBitmap = r1;
        }
    }

    public ImageRenderer(ImageDecoder.Factory r2, ImageOutput r3) {
        super(4);
        this.decoderFactory = r2;
        this.imageOutput = getImageOutput(r3);
        this.flagsOnlyBuffer = DecoderInputBuffer.newNoDataInstance();
        this.outputStreamInfo = OutputStreamInfo.UNSET;
        this.pendingOutputStreamChanges = new ArrayDeque();
        this.largestQueuedPresentationTimeUs = -9223372036854775807L;
        this.lastProcessedOutputBufferTimeUs = -9223372036854775807L;
        this.decoderReinitializationState = 0;
        this.firstFrameState = 1;
    }

    private boolean canCreateDecoderForFormat(Format r1) {
        int r0 = this.decoderFactory.supportsFormat(r1);
        if (r0 != RendererCapabilities.create(4)) goto L5;
        return true;
    L5:
        if (r0 == RendererCapabilities.create(3)) goto L11;
        return false;
    L11:
        return true;
    }

    private Bitmap cropTileFromImageGrid(int r5) {
        Assertions.checkStateNotNull(this.outputBitmap);
        int r0 = this.outputBitmap.getWidth() / ((Format) Assertions.checkStateNotNull(this.inputFormat)).tileCountHorizontal;
        int r1 = this.outputBitmap.getHeight() / ((Format) Assertions.checkStateNotNull(this.inputFormat)).tileCountVertical;
        int r2 = this.inputFormat.tileCountHorizontal;
        Bitmap r4 = this.outputBitmap;
        return Bitmap.createBitmap(r4, (r5 % r2) * r0, (r5 / r2) * r1, r0, r1);
    }

    private boolean drainOutput(long r13, long r15) {
        if (this.outputBitmap == null) goto L8;
        if (this.tileInfo != null) goto L8;
        return false;
    L8:
        if (this.firstFrameState != 0) goto L13;
        if (getState() == 2) goto L13;
        return false;
    L13:
        if (this.outputBitmap != null) goto L28;
        Assertions.checkStateNotNull(this.decoder);
        ImageOutputBuffer r1 = this.decoder.dequeueOutputBuffer();
        if (r1 != null) goto L18;
        return false;
    L18:
        if (((ImageOutputBuffer) Assertions.checkStateNotNull(r1)).isEndOfStream() == true) goto L20;
        Assertions.checkStateNotNull(r1.bitmap, "Non-EOS buffer came back from the decoder without bitmap.");
        this.outputBitmap = r1.bitmap;
        ((ImageOutputBuffer) Assertions.checkStateNotNull(r1)).release();
        goto L28
    L20:
        if (this.decoderReinitializationState != 3) goto L22;
        releaseDecoderResources();
        Assertions.checkStateNotNull(this.inputFormat);
        maybeInitCodec();
    L25:
        return false;
    L22:
        ((ImageOutputBuffer) Assertions.checkStateNotNull(r1)).release();
        if (this.pendingOutputStreamChanges.isEmpty() == false) goto L25;
        this.outputStreamEnded = true;
    L28:
        if (this.readyToOutputTiles == true) goto L30;
    L60:
        return false;
    L30:
        if (this.outputBitmap == null) goto L60;
        if (this.tileInfo == null) goto L60;
        Assertions.checkStateNotNull(this.inputFormat);
        Format r12 = this.inputFormat;
        int r2 = r12.tileCountHorizontal;
        if (r2 != 1) goto L38;
        if (r12.tileCountVertical != 1) goto L38;
    L42:
        boolean r11 = false;
    L44:
        if (this.tileInfo.hasTileBitmap() == true) goto L51;
        TileInfo r14 = this.tileInfo;
        if (r11 == false) goto L48;
        Bitmap r22 = cropTileFromImageGrid(r14.getTileIndex());
    L49:
        r14.setTileBitmap(r22);
        goto L51
    L48:
        r22 = (Bitmap) Assertions.checkStateNotNull(this.outputBitmap);
    L51:
        if (processOutputBuffer(r13, r15, (Bitmap) Assertions.checkStateNotNull(this.tileInfo.getTileBitmap()), this.tileInfo.getPresentationTimeUs()) == true) goto L53;
        return false;
    L53:
        onProcessedOutputBuffer(((TileInfo) Assertions.checkStateNotNull(this.tileInfo)).getPresentationTimeUs());
        this.firstFrameState = 3;
        if (r11 == true) goto L56;
    L57:
        this.outputBitmap = null;
    L58:
        this.tileInfo = this.nextTileInfo;
        this.nextTileInfo = null;
        return true;
    L56:
        if (((TileInfo) Assertions.checkStateNotNull(this.tileInfo)).getTileIndex() != ((((Format) Assertions.checkStateNotNull(this.inputFormat)).tileCountVertical * ((Format) Assertions.checkStateNotNull(this.inputFormat)).tileCountHorizontal) - 1)) goto L58;
    L38:
        if (r2 == (-1)) goto L42;
        if (r12.tileCountVertical == (-1)) goto L42;
        r11 = true;
        goto L44
    }

    private boolean feedInputBuffer(long r8) {
        if (this.readyToOutputTiles == true) goto L5;
    L7:
        FormatHolder r0 = getFormatHolder();
        ImageDecoder r2 = this.decoder;
        if (r2 != null) goto L10;
    L54:
        return false;
    L10:
        if (this.decoderReinitializationState == 3) goto L54;
        if (this.inputStreamEnded == true) goto L54;
        if (this.inputBuffer != null) goto L19;
        DecoderInputBuffer r22 = r2.dequeueInputBuffer();
        this.inputBuffer = r22;
        if (r22 != null) goto L19;
        return false;
    L19:
        int r23 = this.decoderReinitializationState;
        DecoderInputBuffer r3 = this.inputBuffer;
        if (r23 != 2) goto L23;
        Assertions.checkStateNotNull(r3);
        this.inputBuffer.setFlags(4);
        ((ImageDecoder) Assertions.checkStateNotNull(this.decoder)).queueInputBuffer(this.inputBuffer);
        this.inputBuffer = null;
        this.decoderReinitializationState = 3;
        return false;
    L23:
        int r24 = readSource(r0, r3, 0);
        if (r24 != (-5)) goto L26;
        this.inputFormat = (Format) Assertions.checkStateNotNull(r0.format);
        this.codecNeedsInitialization = true;
        this.decoderReinitializationState = 2;
        return true;
    L26:
        if (r24 != (-4)) goto L28;
        this.inputBuffer.flip();
        ByteBuffer r02 = this.inputBuffer.data;
        if (r02 == null) goto L37;
        if (r02.remaining() <= 0) goto L37;
    L38:
        boolean r03 = true;
    L40:
        if (r03 == false) goto L42;
        ((DecoderInputBuffer) Assertions.checkStateNotNull(this.inputBuffer)).format = this.inputFormat;
        ((ImageDecoder) Assertions.checkStateNotNull(this.decoder)).queueInputBuffer((DecoderInputBuffer) Assertions.checkStateNotNull(this.inputBuffer));
        this.currentTileIndex = 0;
    L42:
        maybeAdvanceTileInfo(r8, (DecoderInputBuffer) Assertions.checkStateNotNull(this.inputBuffer));
        if (((DecoderInputBuffer) Assertions.checkStateNotNull(this.inputBuffer)).isEndOfStream() == false) goto L46;
        this.inputStreamEnded = true;
        this.inputBuffer = null;
        return false;
    L46:
        this.largestQueuedPresentationTimeUs = Math.max(this.largestQueuedPresentationTimeUs, ((DecoderInputBuffer) Assertions.checkStateNotNull(this.inputBuffer)).timeUs);
        if (r03 == false) goto L49;
        this.inputBuffer = null;
    L51:
        return !this.readyToOutputTiles;
    L49:
        ((DecoderInputBuffer) Assertions.checkStateNotNull(this.inputBuffer)).clear();
    L37:
        if (((DecoderInputBuffer) Assertions.checkStateNotNull(this.inputBuffer)).isEndOfStream() == true) goto L38;
        r03 = false;
        goto L40
    L28:
        if (r24 != (-3)) goto L30;
        return false;
    L30:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return false;
    L5:
        if (this.tileInfo == null) goto L7;
        return false;
    }

    private static ImageOutput getImageOutput(ImageOutput r0) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        return ImageOutput.NO_OP;
    }

    private boolean isTileLastInGrid(TileInfo r4) {
        if (((Format) Assertions.checkStateNotNull(this.inputFormat)).tileCountHorizontal != (-1)) goto L5;
    L11:
        return true;
    L5:
        if (this.inputFormat.tileCountVertical == (-1)) goto L11;
        if (r4.getTileIndex() == ((((Format) Assertions.checkStateNotNull(this.inputFormat)).tileCountVertical * this.inputFormat.tileCountHorizontal) - 1)) goto L11;
        return false;
    }

    private void lowerFirstFrameState(int r2) {
        this.firstFrameState = Math.min(this.firstFrameState, r2);
    }

    private void maybeAdvanceTileInfo(long r9, DecoderInputBuffer r11) {
        boolean r1 = true;
        if (r11.isEndOfStream() == false) goto L6;
        this.readyToOutputTiles = true;
        return;
    L6:
        TileInfo r0 = new TileInfo(this.currentTileIndex, r11.timeUs);
        this.nextTileInfo = r0;
        this.currentTileIndex++;
        if (this.readyToOutputTiles == true) goto L32;
        long r2 = r0.getPresentationTimeUs();
        if ((r2 - NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) <= r9) goto L11;
    L13:
        boolean r112 = false;
    L14:
        TileInfo r4 = this.tileInfo;
        if (r4 != null) goto L17;
    L21:
        boolean r92 = false;
    L22:
        boolean r10 = isTileLastInGrid((TileInfo) Assertions.checkStateNotNull(this.nextTileInfo));
        if (r112 == true) goto L28;
        if (r92 == true) goto L28;
        if (r10 == true) goto L28;
        r1 = false;
    L28:
        this.readyToOutputTiles = r1;
        if (r92 == false) goto L32;
        if (r112 == true) goto L32;
        return;
    L17:
        if (r4.getPresentationTimeUs() > r9) goto L21;
        if (r9 >= r2) goto L21;
        r92 = true;
        goto L22
    L11:
        if (r9 > (NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS + r2)) goto L13;
        r112 = true;
    L32:
        this.tileInfo = this.nextTileInfo;
        this.nextTileInfo = null;
    }

    private boolean maybeInitCodec() {
        if (maybeInitializeProcessingPipeline() == true) goto L6;
        return false;
    L6:
        if (this.codecNeedsInitialization == true) goto L9;
        return true;
    L9:
        if (canCreateDecoderForFormat((Format) Assertions.checkNotNull(this.inputFormat)) == false) goto L16;
        ImageDecoder r0 = this.decoder;
        if (r0 == null) goto L13;
        r0.release();
    L13:
        this.decoder = this.decoderFactory.createImageDecoder();
        this.codecNeedsInitialization = false;
        return true;
    L16:
        throw createRendererException(new ImageDecoderException("Provided decoder factory can't create decoder for format."), this.inputFormat, 4005);
    }

    private void onProcessedOutputBuffer(long r3) {
        this.lastProcessedOutputBufferTimeUs = r3;
    L4:
        if (this.pendingOutputStreamChanges.isEmpty() == true) goto L8;
        if (r3 < this.pendingOutputStreamChanges.peek().previousStreamLastBufferTimeUs) goto L11;
        this.outputStreamInfo = this.pendingOutputStreamChanges.removeFirst();
        goto L4
    L11:
        return;
    }

    private void releaseDecoderResources() {
        this.inputBuffer = null;
        this.decoderReinitializationState = 0;
        this.largestQueuedPresentationTimeUs = -9223372036854775807L;
        ImageDecoder r1 = this.decoder;
        if (r1 == null) goto L6;
        r1.release();
        this.decoder = null;
        return;
    }

    private void setImageOutput(ImageOutput r1) {
        this.imageOutput = getImageOutput(r1);
    }

    private boolean shouldForceRender() {
        if (getState() != 2) goto L5;
        boolean r0 = true;
    L6:
        int r4 = this.firstFrameState;
        if (r4 == 0) goto L15;
        if (r4 != 1) goto L10;
        return true;
    L10:
        if (r4 != 3) goto L12;
        return false;
    L12:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return false;
    L15:
        return r0;
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return "ImageRenderer";
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int r2, Object r3) {
        if (r2 == 15) goto L7;
        super.handleMessage(r2, r3);
        return;
    L7:
        if ((r3 instanceof ImageOutput) == false) goto L9;
        ImageOutput r32 = (ImageOutput) r3;
    L10:
        setImageOutput(r32);
        return;
    L9:
        r32 = null;
        goto L10
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        int r0 = this.firstFrameState;
        if (r0 == 3) goto L10;
        if (r0 == 0) goto L6;
        return false;
    L6:
        if (this.readyToOutputTiles == true) goto L13;
        return false;
    L13:
        return true;
    L10:
        return true;
    }

    public boolean maybeInitializeProcessingPipeline() {
        return true;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.inputFormat = null;
        this.outputStreamInfo = OutputStreamInfo.UNSET;
        this.pendingOutputStreamChanges.clear();
        releaseDecoderResources();
        this.imageOutput.onDisabled();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onEnabled(boolean r1, boolean r2) {
        this.firstFrameState = r2 ? 1 : 0;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long r1, boolean r3) {
        lowerFirstFrameState(1);
        this.outputStreamEnded = false;
        this.inputStreamEnded = false;
        this.outputBitmap = null;
        this.tileInfo = null;
        this.nextTileInfo = null;
        this.readyToOutputTiles = false;
        this.inputBuffer = null;
        ImageDecoder r12 = this.decoder;
        if (r12 == null) goto L5;
        r12.flush();
    L5:
        this.pendingOutputStreamChanges.clear();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onRelease() {
        releaseDecoderResources();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onReset() {
        releaseDecoderResources();
        lowerFirstFrameState(1);
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStreamChanged(Format[] r5, long r6, long r8, MediaSource.MediaPeriodId r10) {
        super.onStreamChanged(r5, r6, r8, r10);
        if (this.outputStreamInfo.streamOffsetUs != (-9223372036854775807L)) goto L5;
    L15:
        this.outputStreamInfo = new OutputStreamInfo(-9223372036854775807L, r8);
        return;
    L5:
        if (this.pendingOutputStreamChanges.isEmpty() == false) goto L13;
        long r52 = this.largestQueuedPresentationTimeUs;
        if (r52 == (-9223372036854775807L)) goto L15;
        long r2 = this.lastProcessedOutputBufferTimeUs;
        if (r2 == (-9223372036854775807L)) goto L13;
        if (r2 >= r52) goto L15;
    L13:
        this.pendingOutputStreamChanges.add(new OutputStreamInfo(this.largestQueuedPresentationTimeUs, r8));
    }

    public boolean processOutputBuffer(long r1, long r3, Bitmap r5, long r6) {
        long r12 = r6 - r1;
        if (shouldForceRender() == false) goto L5;
    L9:
        this.imageOutput.onImageAvailable(r6 - this.outputStreamInfo.streamOffsetUs, r5);
        return true;
    L5:
        if (r12 < NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L9;
        return false;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long r5, long r7) {
        if (this.outputStreamEnded == false) goto L6;
        return;
    L6:
        if (this.inputFormat != null) goto L15;
        FormatHolder r0 = getFormatHolder();
        this.flagsOnlyBuffer.clear();
        int r1 = readSource(r0, this.flagsOnlyBuffer, 2);
        if (r1 != (-5)) goto L11;
        this.inputFormat = (Format) Assertions.checkStateNotNull(r0.format);
        this.codecNeedsInitialization = true;
        goto L15
    L11:
        if (r1 != (-4)) goto L18;
        Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
        this.inputStreamEnded = true;
        this.outputStreamEnded = true;
        return;
    L18:
        return;
    L15:
        if (this.decoder == null) goto L17;
    L31:
        TraceUtil.beginSection("drainAndFeedDecoder");     // Catch: ImageDecoderException -> L28
    L21:
        if (drainOutput(r5, r7) == true) goto L21;
    L24:
        if (feedInputBuffer(r5) == true) goto L24;
        TraceUtil.endSection();     // Catch: ImageDecoderException -> L28
        return;
    L28:
        e = move-exception;
        throw createRendererException(e, null, 4003);
    L17:
        if (maybeInitCodec() == true) goto L31;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public int supportsFormat(Format r1) {
        return this.decoderFactory.supportsFormat(r1);
    }
}
