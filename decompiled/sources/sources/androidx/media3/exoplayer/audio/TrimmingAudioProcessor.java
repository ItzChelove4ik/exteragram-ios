package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.BaseAudioProcessor;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class TrimmingAudioProcessor extends BaseAudioProcessor {
    private byte[] endBuffer;
    private int endBufferSize;
    private int pendingTrimStartBytes;
    private boolean reconfigurationPending;
    private int trimEndFrames;
    private int trimStartFrames;
    private long trimmedFrameCount;

    public TrimmingAudioProcessor() {
        this.endBuffer = Util.EMPTY_BYTE_ARRAY;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor, androidx.media3.common.audio.AudioProcessor
    public ByteBuffer getOutput() {
        if (super.isEnded() == false) goto L8;
        int r0 = this.endBufferSize;
        if (r0 <= 0) goto L8;
        replaceOutputBuffer(r0).put(this.endBuffer, 0, this.endBufferSize).flip();
        this.endBufferSize = 0;
    L8:
        return super.getOutput();
    }

    public long getTrimmedFrameCount() {
        return this.trimmedFrameCount;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor, androidx.media3.common.audio.AudioProcessor
    public boolean isEnded() {
        if (super.isEnded() == true) goto L5;
        return false;
    L5:
        if (this.endBufferSize != 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat r2) {
        if (Util.isEncodingLinearPcm(r2.encoding) == false) goto L13;
        this.reconfigurationPending = true;
        if (this.trimStartFrames == 0) goto L7;
    L11:
        return r2;
    L7:
        if (this.trimEndFrames != 0) goto L11;
        return AudioProcessor.AudioFormat.NOT_SET;
    L13:
        throw new AudioProcessor.UnhandledAudioFormatException(r2);
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onFlush() {
        if (this.reconfigurationPending == false) goto L5;
        this.reconfigurationPending = false;
        int r0 = this.trimEndFrames;
        int r2 = this.inputAudioFormat.bytesPerFrame;
        this.endBuffer = new byte[r0 * r2];
        this.pendingTrimStartBytes = this.trimStartFrames * r2;
    L5:
        this.endBufferSize = 0;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onQueueEndOfStream() {
        if (this.reconfigurationPending == true) goto L5;
        return;
    L5:
        if (this.endBufferSize <= 0) goto L7;
        this.trimmedFrameCount += r0 / this.inputAudioFormat.bytesPerFrame;
    L7:
        this.endBufferSize = 0;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onReset() {
        this.endBuffer = Util.EMPTY_BYTE_ARRAY;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer r9) {
        int r0 = r9.position();
        int r1 = r9.limit();
        int r2 = r1 - r0;
        if (r2 == 0) goto L10;
        int r3 = Math.min(r2, this.pendingTrimStartBytes);
        this.trimmedFrameCount += r3 / this.inputAudioFormat.bytesPerFrame;
        this.pendingTrimStartBytes -= r3;
        r9.position(r0 + r3);
        if (this.pendingTrimStartBytes <= 0) goto L8;
        return;
    L8:
        int r22 = r2 - r3;
        int r02 = (this.endBufferSize + r22) - this.endBuffer.length;
        ByteBuffer r32 = replaceOutputBuffer(r02);
        int r4 = Util.constrainValue(r02, 0, this.endBufferSize);
        r32.put(this.endBuffer, 0, r4);
        int r03 = Util.constrainValue(r02 - r4, 0, r22);
        r9.limit(r9.position() + r03);
        r32.put(r9);
        r9.limit(r1);
        int r23 = r22 - r03;
        int r04 = this.endBufferSize - r4;
        this.endBufferSize = r04;
        byte[] r12 = this.endBuffer;
        System.arraycopy(r12, r4, r12, 0, r04);
        r9.get(this.endBuffer, this.endBufferSize, r23);
        this.endBufferSize += r23;
        r32.flip();
        return;
    }

    public void resetTrimmedFrameCount() {
        this.trimmedFrameCount = 0;
    }

    public void setTrimFrameCount(int r1, int r2) {
        this.trimStartFrames = r1;
        this.trimEndFrames = r2;
    }
}
