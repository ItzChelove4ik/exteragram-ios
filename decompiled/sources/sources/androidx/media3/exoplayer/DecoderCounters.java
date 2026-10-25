package androidx.media3.exoplayer;

import androidx.media3.common.util.Util;

/* loaded from: classes4.dex */
public final class DecoderCounters {
    public int decoderInitCount;
    public int decoderReleaseCount;
    public int droppedBufferCount;
    public int droppedInputBufferCount;
    public int droppedToKeyframeCount;
    public int maxConsecutiveDroppedBufferCount;
    public int queuedInputBufferCount;
    public int renderedOutputBufferCount;
    public int skippedInputBufferCount;
    public int skippedOutputBufferCount;
    public long totalVideoFrameProcessingOffsetUs;
    public int videoFrameProcessingOffsetCount;

    public DecoderCounters() {
    }

    private void addVideoFrameProcessingOffsets(long r3, int r5) {
        this.totalVideoFrameProcessingOffsetUs += r3;
        this.videoFrameProcessingOffsetCount += r5;
    }

    public void addVideoFrameProcessingOffset(long r2) {
        addVideoFrameProcessingOffsets(r2, 1);
    }

    public synchronized void ensureUpdated() {
        monitor-enter(this);
        monitor-exit(this);
    }

    public String toString() {
        return Util.formatInvariant("DecoderCounters {\n decoderInits=%s,\n decoderReleases=%s\n queuedInputBuffers=%s\n skippedInputBuffers=%s\n renderedOutputBuffers=%s\n skippedOutputBuffers=%s\n droppedBuffers=%s\n droppedInputBuffers=%s\n maxConsecutiveDroppedBuffers=%s\n droppedToKeyframeEvents=%s\n totalVideoFrameProcessingOffsetUs=%s\n videoFrameProcessingOffsetCount=%s\n}", new Object[]{Integer.valueOf(this.decoderInitCount), Integer.valueOf(this.decoderReleaseCount), Integer.valueOf(this.queuedInputBufferCount), Integer.valueOf(this.skippedInputBufferCount), Integer.valueOf(this.renderedOutputBufferCount), Integer.valueOf(this.skippedOutputBufferCount), Integer.valueOf(this.droppedBufferCount), Integer.valueOf(this.droppedInputBufferCount), Integer.valueOf(this.maxConsecutiveDroppedBufferCount), Integer.valueOf(this.droppedToKeyframeCount), Long.valueOf(this.totalVideoFrameProcessingOffsetUs), Integer.valueOf(this.videoFrameProcessingOffsetCount)});
    }
}
