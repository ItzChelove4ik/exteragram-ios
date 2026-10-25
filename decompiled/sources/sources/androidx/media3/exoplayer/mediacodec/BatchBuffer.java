package androidx.media3.exoplayer.mediacodec;

import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
final class BatchBuffer extends DecoderInputBuffer {
    private long lastSampleTimeUs;
    private int maxSampleCount;
    private int sampleCount;

    public BatchBuffer() {
        super(2);
        this.maxSampleCount = 32;
    }

    private boolean canAppendSampleBuffer(DecoderInputBuffer r5) {
        if (hasSamples() == true) goto L6;
        return true;
    L6:
        if (this.sampleCount < this.maxSampleCount) goto L8;
        return false;
    L8:
        ByteBuffer r52 = r5.data;
        if (r52 == null) goto L15;
        ByteBuffer r4 = this.data;
        if (r4 == null) goto L15;
        if ((r4.position() + r52.remaining()) <= 3072000) goto L15;
        return false;
    L15:
        return true;
    }

    public boolean append(DecoderInputBuffer r5) {
        Assertions.checkArgument(!r5.isEncrypted());
        Assertions.checkArgument(!r5.hasSupplementalData());
        Assertions.checkArgument(!r5.isEndOfStream());
        if (canAppendSampleBuffer(r5) == true) goto L6;
        return false;
    L6:
        int r0 = this.sampleCount;
        this.sampleCount = r0 + 1;
        if (r0 != 0) goto L11;
        this.timeUs = r5.timeUs;
        if (r5.isKeyFrame() == false) goto L11;
        setFlags(1);
    L11:
        ByteBuffer r02 = r5.data;
        if (r02 == null) goto L14;
        ensureSpaceForWrite(r02.remaining());
        this.data.put(r02);
    L14:
        this.lastSampleTimeUs = r5.timeUs;
        return true;
    }

    @Override // androidx.media3.decoder.DecoderInputBuffer, androidx.media3.decoder.Buffer
    public void clear() {
        super.clear();
        this.sampleCount = 0;
    }

    public long getFirstSampleTimeUs() {
        return this.timeUs;
    }

    public long getLastSampleTimeUs() {
        return this.lastSampleTimeUs;
    }

    public int getSampleCount() {
        return this.sampleCount;
    }

    public boolean hasSamples() {
        if (this.sampleCount <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void setMaxSampleCount(int r2) {
        if (r2 <= 0) goto L4;
        boolean r0 = true;
    L5:
        Assertions.checkArgument(r0);
        this.maxSampleCount = r2;
        return;
    L4:
        r0 = false;
        goto L5
    }
}
