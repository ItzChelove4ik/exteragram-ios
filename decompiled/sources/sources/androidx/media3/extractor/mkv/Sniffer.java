package androidx.media3.extractor.mkv;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import kotlin.UByte;

/* loaded from: classes4.dex */
final class Sniffer {
    private int peekLength;
    private final ParsableByteArray scratch;

    public Sniffer() {
        this.scratch = new ParsableByteArray(8);
    }

    private long readUint(ExtractorInput r7) {
        int r1 = 0;
        r7.peekFully(this.scratch.getData(), 0, 1);
        int r0 = this.scratch.getData()[0] & UByte.MAX_VALUE;
        if (r0 != 0) goto L6;
        return Long.MIN_VALUE;
    L6:
        int r3 = 128;
        int r4 = 0;
    L8:
        if ((r0 & r3) != 0) goto L10;
        r3 = r3 >> 1;
        r4 = r4 + 1;
        goto L8
    L10:
        int r02 = r0 & (~r3);
        r7.peekFully(this.scratch.getData(), 1, r4);
    L11:
        if (r1 >= r4) goto L13;
        r1 = r1 + 1;
        r02 = (this.scratch.getData()[r1] & UByte.MAX_VALUE) + (r02 << 8);
        goto L11
    L13:
        this.peekLength += r4 + 1;
        return r02;
    }

    public boolean sniff(ExtractorInput r14) {
        long r0 = r14.getLength();
        long r3 = 1024;
        if (r0 != (-1)) goto L5;
    L8:
        int r32 = (int) r3;
        r14.peekFully(this.scratch.getData(), 0, 4);
        long r7 = this.scratch.readUnsignedInt();
        this.peekLength = 4;
    L10:
        if (r7 == 440786851) goto L15;
        int r4 = this.peekLength + 1;
        this.peekLength = r4;
        if (r4 == r32) goto L13;
        r14.peekFully(this.scratch.getData(), 0, 1);
        r7 = ((r7 << 8) & (-256)) | (this.scratch.getData()[0] & UByte.MAX_VALUE);
        goto L10
    L13:
        return false;
    L15:
        long r33 = readUint(r14);
        long r72 = this.peekLength;
        if (r33 == Long.MIN_VALUE) goto L37;
        if (r0 == (-1)) goto L21;
        if ((r72 + r33) >= r0) goto L37;
    L21:
        int r02 = this.peekLength;
        long r11 = r72 + r33;
        if (r02 >= r11) goto L35;
        if (readUint(r14) == Long.MIN_VALUE) goto L25;
        long r03 = readUint(r14);
        if (r03 < 0) goto L33;
        if (r03 > 2147483647L) goto L33;
        if (r03 == 0) goto L21;
        int r04 = (int) r03;
        r14.advancePeekPosition(r04);
        this.peekLength += r04;
    L33:
        return false;
    L25:
        return false;
    L35:
        if (r02 != r11) goto L37;
        return true;
    L37:
        return false;
    L5:
        if (r0 > 1024) goto L8;
        r3 = r0;
        goto L8
    }
}
