package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.BaseAudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.telegram.ui.WearAuthSheet$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class ChannelMappingAudioProcessor extends BaseAudioProcessor {
    private int[] outputChannels;
    private int[] pendingOutputChannels;

    public ChannelMappingAudioProcessor() {
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat r7) {
        int[] r6 = this.pendingOutputChannels;
        if (r6 != null) goto L7;
        return AudioProcessor.AudioFormat.NOT_SET;
    L7:
        if (Util.isEncodingLinearPcm(r7.encoding) == false) goto L29;
        if (r7.channelCount == r6.length) goto L11;
        boolean r0 = true;
    L12:
        int r1 = 0;
    L14:
        if (r1 >= r6.length) goto L23;
        int r4 = r6[r1];
        if (r4 >= r7.channelCount) goto L22;
        if (r4 == r1) goto L19;
        boolean r42 = true;
    L20:
        r0 = r0 | r42;
        r1 = r1 + 1;
        goto L14
    L19:
        r42 = false;
        goto L20
    L22:
        throw new AudioProcessor.UnhandledAudioFormatException("Channel map (" + Arrays.toString(r6) + ") trying to access non-existent input channel.", r7);
    L23:
        if (r0 == false) goto L27;
        return new AudioProcessor.AudioFormat(r7.sampleRate, r6.length, r7.encoding);
    L27:
        return AudioProcessor.AudioFormat.NOT_SET;
    L11:
        r0 = false;
        goto L12
    L29:
        throw new AudioProcessor.UnhandledAudioFormatException(r7);
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onFlush() {
        this.outputChannels = this.pendingOutputChannels;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onReset() {
        this.outputChannels = null;
        this.pendingOutputChannels = null;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer r10) {
        int[] r0 = (int[]) Assertions.checkNotNull(this.outputChannels);
        int r1 = r10.position();
        int r2 = r10.limit();
        ByteBuffer r3 = replaceOutputBuffer(((r2 - r1) / this.inputAudioFormat.bytesPerFrame) * this.outputAudioFormat.bytesPerFrame);
    L3:
        if (r1 >= r2) goto L32;
        int r4 = r0.length;
        int r5 = 0;
    L5:
        if (r5 >= r4) goto L31;
        int r6 = r0[r5];
        int r7 = (Util.getByteDepth(this.inputAudioFormat.encoding) * r6) + r1;
        int r62 = this.inputAudioFormat.encoding;
        if (r62 != 2) goto L9;
    L29:
        r3.putShort(r10.getShort(r7));
    L30:
        r5 = r5 + 1;
        goto L5
    L9:
        if (r62 != 3) goto L11;
        r3.put(r10.get(r7));
        goto L30
    L11:
        if (r62 != 4) goto L13;
        r3.putFloat(r10.getFloat(r7));
        goto L30
    L13:
        if (r62 != 21) goto L15;
    L26:
        Util.putInt24(r3, Util.getInt24(r10, r7));
        goto L30
    L15:
        if (r62 != 22) goto L17;
    L25:
        r3.putInt(r10.getInt(r7));
        goto L30
    L17:
        if (r62 == 268435456) goto L29;
        if (r62 == 1342177280) goto L26;
        if (r62 == 1610612736) goto L25;
        WearAuthSheet$$ExternalSyntheticBUOutline0.m("Unexpected encoding: ", this.inputAudioFormat.encoding);
        return;
    L31:
        r1 = r1 + this.inputAudioFormat.bytesPerFrame;
        goto L3
    L32:
        r10.position(r2);
        r3.flip();
    }

    public void setChannelMap(int[] r1) {
        this.pendingOutputChannels = r1;
    }
}
