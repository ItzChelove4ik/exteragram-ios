package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import kotlin.UByte;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public final class ToInt16PcmAudioProcessor extends BaseAudioProcessor {
    public ToInt16PcmAudioProcessor() {
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat r3) {
        int r2 = r3.encoding;
        if (r2 == 3) goto L20;
        if (r2 == 2) goto L20;
        if (r2 == 268435456) goto L20;
        if (r2 == 21) goto L20;
        if (r2 == 1342177280) goto L20;
        if (r2 == 22) goto L20;
        if (r2 == 1610612736) goto L20;
        if (r2 == 4) goto L20;
        throw new AudioProcessor.UnhandledAudioFormatException(r3);
    L20:
        if (r2 == 2) goto L24;
        return new AudioProcessor.AudioFormat(r3.sampleRate, r3.channelCount, 2);
    L24:
        return AudioProcessor.AudioFormat.NOT_SET;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer r12) {
        int r0 = r12.position();
        int r1 = r12.limit();
        int r2 = r1 - r0;
        int r3 = this.inputAudioFormat.encoding;
        if (r3 == 3) goto L14;
        if (r3 == 4) goto L15;
        if (r3 == 21) goto L13;
        if (r3 == 22) goto L15;
        if (r3 == 268435456) goto L16;
        if (r3 == 1342177280) goto L13;
        if (r3 == 1610612736) goto L15;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    L16:
        ByteBuffer r22 = replaceOutputBuffer(r2);
        int r11 = this.inputAudioFormat.encoding;
        if (r11 == 3) goto L38;
        if (r11 == 4) goto L36;
        if (r11 == 21) goto L34;
        if (r11 == 22) goto L32;
        if (r11 == 268435456) goto L30;
        if (r11 == 1342177280) goto L28;
        if (r11 != 1610612736) goto L26;
    L24:
        if (r0 >= r1) goto L40;
        r22.put(r12.get(r0 + 1));
        r22.put(r12.get(r0));
        r0 = r0 + 4;
    L40:
        r12.position(r12.limit());
        r22.flip();
        return;
    L26:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    L28:
        if (r0 >= r1) goto L40;
        r22.put(r12.get(r0 + 1));
        r22.put(r12.get(r0));
        r0 = r0 + 3;
    L30:
        if (r0 >= r1) goto L40;
        r22.put(r12.get(r0 + 1));
        r22.put(r12.get(r0));
        r0 = r0 + 2;
    L32:
        if (r0 >= r1) goto L40;
        r22.put(r12.get(r0 + 2));
        r22.put(r12.get(r0 + 3));
        r0 = r0 + 4;
    L34:
        if (r0 >= r1) goto L40;
        r22.put(r12.get(r0 + 1));
        r22.put(r12.get(r0 + 2));
        r0 = r0 + 3;
    L36:
        if (r0 >= r1) goto L40;
        short r112 = (short) (Util.constrainValue(r12.getFloat(r0), -1.0f, 1.0f) * 32767.0f);
        r22.put((byte) (r112 & 255));
        r22.put((byte) ((r112 >> 8) & 255));
        r0 = r0 + 4;
    L38:
        if (r0 >= r1) goto L40;
        r22.put((byte) 0);
        r22.put((byte) ((r12.get(r0) & UByte.MAX_VALUE) - 128));
        r0 = r0 + 1;
    L13:
        r2 = r2 / 3;
    L15:
        r2 = r2 / 2;
    L14:
        r2 = r2 * 2;
        goto L16
    }
}
