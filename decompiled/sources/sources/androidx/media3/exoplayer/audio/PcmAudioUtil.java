package androidx.media3.exoplayer.audio;

import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UByte;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class PcmAudioUtil {
    public static ByteBuffer rampUpVolume(ByteBuffer r6, int r7, int r8, int r9, int r10) {
        ByteBuffer r0 = ByteBuffer.allocateDirect(r6.remaining()).order(ByteOrder.nativeOrder());
        int r1 = r6.position();
    L4:
        if (r6.hasRemaining() == false) goto L9;
        if (r9 >= r10) goto L9;
        write32BitIntPcm(r0, (int) ((readAs32BitIntPcm(r6, r7) * r9) / r10), r7);
        if (r6.position() != (r1 + r8)) goto L4;
        r9 = r9 + 1;
        r1 = r6.position();
    L9:
        r0.put(r6);
        r0.flip();
        return r0;
    }

    public static int readAs32BitIntPcm(ByteBuffer r1, int r2) {
        if (r2 != 2) goto L5;
        int r22 = (r1.get() & UByte.MAX_VALUE) << 16;
        return ((r1.get() & UByte.MAX_VALUE) << 24) | r22;
    L5:
        if (r2 == 3) goto L37;
        if (r2 != 4) goto L9;
        float r12 = Util.constrainValue(r1.getFloat(), -1.0f, 1.0f);
        if (r12 >= 0.0f) goto L35;
        return (int) ((-r12) * (-2.1474836E9f));
    L35:
        return (int) (r12 * 2.1474836E9f);
    L9:
        if (r2 != 21) goto L11;
        int r23 = ((r1.get() & UByte.MAX_VALUE) << 8) | ((r1.get() & UByte.MAX_VALUE) << 16);
        return ((r1.get() & UByte.MAX_VALUE) << 24) | r23;
    L11:
        if (r2 != 22) goto L13;
        int r24 = ((r1.get() & UByte.MAX_VALUE) | ((r1.get() & UByte.MAX_VALUE) << 8)) | ((r1.get() & UByte.MAX_VALUE) << 16);
        return ((r1.get() & UByte.MAX_VALUE) << 24) | r24;
    L13:
        if (r2 != 268435456) goto L15;
        int r25 = (r1.get() & UByte.MAX_VALUE) << 24;
        return ((r1.get() & UByte.MAX_VALUE) << 16) | r25;
    L15:
        if (r2 != 1342177280) goto L17;
        int r26 = ((r1.get() & UByte.MAX_VALUE) << 24) | ((r1.get() & UByte.MAX_VALUE) << 16);
        return ((r1.get() & UByte.MAX_VALUE) << 8) | r26;
    L17:
        if (r2 != 1610612736) goto L20;
        int r27 = (((r1.get() & UByte.MAX_VALUE) << 24) | ((r1.get() & UByte.MAX_VALUE) << 16)) | ((r1.get() & UByte.MAX_VALUE) << 8);
        return (r1.get() & UByte.MAX_VALUE) | r27;
    L20:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return 0;
    L37:
        return (r1.get() & UByte.MAX_VALUE) << 24;
    }

    public static void write32BitIntPcm(ByteBuffer r1, int r2, int r3) {
        if (r3 != 2) goto L5;
        r1.put((byte) (r2 >> 16));
        r1.put((byte) (r2 >> 24));
        return;
    L5:
        if (r3 != 3) goto L7;
        r1.put((byte) (r2 >> 24));
        return;
    L7:
        if (r3 != 4) goto L9;
        if (r2 >= 0) goto L33;
        r1.putFloat((-r2) / (-2.1474836E9f));
        return;
    L33:
        r1.putFloat(r2 / 2.1474836E9f);
        return;
    L9:
        if (r3 != 21) goto L11;
        r1.put((byte) (r2 >> 8));
        r1.put((byte) (r2 >> 16));
        r1.put((byte) (r2 >> 24));
        return;
    L11:
        if (r3 != 22) goto L13;
        r1.put((byte) r2);
        r1.put((byte) (r2 >> 8));
        r1.put((byte) (r2 >> 16));
        r1.put((byte) (r2 >> 24));
        return;
    L13:
        if (r3 != 268435456) goto L15;
        r1.put((byte) (r2 >> 24));
        r1.put((byte) (r2 >> 16));
        return;
    L15:
        if (r3 != 1342177280) goto L17;
        r1.put((byte) (r2 >> 24));
        r1.put((byte) (r2 >> 16));
        r1.put((byte) (r2 >> 8));
        return;
    L17:
        if (r3 != 1610612736) goto L20;
        r1.put((byte) (r2 >> 24));
        r1.put((byte) (r2 >> 16));
        r1.put((byte) (r2 >> 8));
        r1.put((byte) r2);
        return;
    L20:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }
}
