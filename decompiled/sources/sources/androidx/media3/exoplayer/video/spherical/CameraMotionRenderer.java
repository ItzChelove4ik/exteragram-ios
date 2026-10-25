package androidx.media3.exoplayer.video.spherical;

import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.RendererCapabilities;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class CameraMotionRenderer extends BaseRenderer {
    private final DecoderInputBuffer buffer;
    private long lastTimestampUs;
    private CameraMotionListener listener;
    private final ParsableByteArray scratch;

    public CameraMotionRenderer() {
        super(6);
        this.buffer = new DecoderInputBuffer(1);
        this.scratch = new ParsableByteArray();
    }

    private float[] parseMetadata(ByteBuffer r4) {
        if (r4.remaining() == 16) goto L6;
        return null;
    L6:
        this.scratch.reset(r4.array(), r4.limit());
        this.scratch.setPosition(r4.arrayOffset() + 4);
        float[] r0 = new float[3];
        int r1 = 0;
    L7:
        if (r1 >= 3) goto L9;
        r0[r1] = Float.intBitsToFloat(this.scratch.readLittleEndianInt());
        r1 = r1 + 1;
        goto L7
    L9:
        return r0;
    }

    private void resetListener() {
        CameraMotionListener r0 = this.listener;
        if (r0 == null) goto L6;
        r0.onCameraMotionReset();
        return;
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return "CameraMotionRenderer";
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int r2, Object r3) {
        if (r2 != 8) goto L6;
        this.listener = (CameraMotionListener) r3;
        return;
    L6:
        super.handleMessage(r2, r3);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return hasReadStreamToEnd();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        return true;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        resetListener();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long r1, boolean r3) {
        this.lastTimestampUs = Long.MIN_VALUE;
        resetListener();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long r5, long r7) {
    L3:
        if (hasReadStreamToEnd() == true) goto L22;
        if (this.lastTimestampUs >= (100000 + r5)) goto L35;
        this.buffer.clear();
        boolean r0 = false;
        if (readSource(getFormatHolder(), this.buffer, 0) != (-4)) goto L36;
        if (this.buffer.isEndOfStream() == true) goto L37;
        long r72 = this.buffer.timeUs;
        this.lastTimestampUs = r72;
        if (r72 >= getLastResetPositionUs()) goto L15;
        r0 = true;
    L15:
        if (this.listener == null) goto L3;
        if (r0 == true) goto L3;
        this.buffer.flip();
        float[] r73 = parseMetadata((ByteBuffer) Util.castNonNull(this.buffer.data));
        if (r73 == null) goto L3;
        ((CameraMotionListener) Util.castNonNull(this.listener)).onCameraMotion(this.lastTimestampUs - getStreamOffsetUs(), r73);
        goto L3
    L37:
        return;
    L36:
        return;
    L35:
        return;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public int supportsFormat(Format r1) {
        if ("application/x-camera-motion".equals(r1.sampleMimeType) == false) goto L7;
        return RendererCapabilities.create(4);
    L7:
        return RendererCapabilities.create(0);
    }
}
