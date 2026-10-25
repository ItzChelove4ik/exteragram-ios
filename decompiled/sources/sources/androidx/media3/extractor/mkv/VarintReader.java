package androidx.media3.extractor.mkv;

import androidx.media3.extractor.ExtractorInput;
import kotlin.UByte;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class VarintReader {
    private static final long[] VARINT_LENGTH_MASKS = null;
    private int length;
    private final byte[] scratch;
    private int state;

    static {
        VARINT_LENGTH_MASKS = new long[]{128, 64, 32, 16, 8, 4, 2, 1};
    }

    public VarintReader() {
        this.scratch = new byte[8];
    }

    public static long assembleVarint(byte[] r6, int r7, boolean r8) {
        long r0 = r6[0] & 255;
        if (r8 == false) goto L5;
        r0 = r0 & (~VARINT_LENGTH_MASKS[r7 - 1]);
    L5:
        int r82 = 1;
    L6:
        if (r82 >= r7) goto L8;
        r0 = (r0 << 8) | (r6[r82] & 255);
        r82 = r82 + 1;
        goto L6
    L8:
        return r0;
    }

    public static int parseUnsignedVarintLength(int r6) {
        int r0 = 0;
    L3:
        long[] r1 = VARINT_LENGTH_MASKS;
        if (r0 >= r1.length) goto L10;
        if ((r1[r0] & r6) != 0) goto L8;
        r0 = r0 + 1;
        goto L3
    L8:
        return r0 + 1;
    L10:
        return -1;
    }

    public int getLastLength() {
        return this.length;
    }

    public long readUnsignedVarint(ExtractorInput r4, boolean r5, boolean r6, int r7) {
        if (this.state == 0) goto L5;
    L13:
        int r52 = this.length;
        if (r52 <= r7) goto L17;
        this.state = 0;
        return -2;
    L17:
        if (r52 == 1) goto L19;
        r4.readFully(this.scratch, 1, r52 - 1);
    L19:
        this.state = 0;
        return assembleVarint(this.scratch, this.length, r6);
    L5:
        if (r4.readFully(this.scratch, 0, 1, r5) == true) goto L8;
        return -1;
    L8:
        int r53 = parseUnsignedVarintLength(this.scratch[0] & UByte.MAX_VALUE);
        this.length = r53;
        if (r53 == (-1)) goto L11;
        this.state = 1;
        goto L13
    L11:
        Segment$$ExternalSyntheticBUOutline1.m("No valid varint length mask found");
        return 0;
    }

    public void reset() {
        this.state = 0;
        this.length = 0;
    }
}
