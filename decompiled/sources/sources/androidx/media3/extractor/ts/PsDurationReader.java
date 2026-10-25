package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.PositionHolder;
import kotlin.UByte;

/* loaded from: classes4.dex */
final class PsDurationReader {
    private long durationUs;
    private long firstScrValue;
    private boolean isDurationRead;
    private boolean isFirstScrValueRead;
    private boolean isLastScrValueRead;
    private long lastScrValue;
    private final ParsableByteArray packetBuffer;
    private final TimestampAdjuster scrTimestampAdjuster;

    public PsDurationReader() {
        this.scrTimestampAdjuster = new TimestampAdjuster(0);
        this.firstScrValue = -9223372036854775807L;
        this.lastScrValue = -9223372036854775807L;
        this.durationUs = -9223372036854775807L;
        this.packetBuffer = new ParsableByteArray();
    }

    private static boolean checkMarkerBits(byte[] r3) {
        if ((r3[0] & 196) == 68) goto L6;
        return false;
    L6:
        if ((r3[2] & 4) == 4) goto L9;
        return false;
    L9:
        if ((r3[4] & 4) == 4) goto L12;
        return false;
    L12:
        if ((r3[5] & 1) == 1) goto L15;
        return false;
    L15:
        if ((r3[8] & 3) != 3) goto L17;
        return true;
    L17:
        return false;
    }

    private int finishReadDuration(ExtractorInput r3) {
        this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        this.isDurationRead = true;
        r3.resetPeekPosition();
        return 0;
    }

    private int peekIntAtPosition(byte[] r2, int r3) {
        return ((((r2[r3] & UByte.MAX_VALUE) << 24) | ((r2[r3 + 1] & UByte.MAX_VALUE) << 16)) | ((r2[r3 + 2] & UByte.MAX_VALUE) << 8)) | (r2[r3 + 3] & UByte.MAX_VALUE);
    }

    private int readFirstScrValue(ExtractorInput r6, PositionHolder r7) {
        int r0 = (int) Math.min(20000, r6.getLength());
        if (r6.getPosition() == 0) goto L6;
        r7.position = 0;
        return 1;
    L6:
        this.packetBuffer.reset(r0);
        r6.resetPeekPosition();
        r6.peekFully(this.packetBuffer.getData(), 0, r0);
        this.firstScrValue = readFirstScrValueFromBuffer(this.packetBuffer);
        this.isFirstScrValueRead = true;
        return 0;
    }

    private long readFirstScrValueFromBuffer(ParsableByteArray r8) {
        int r0 = r8.getPosition();
        int r1 = r8.limit();
    L4:
        if (r0 >= (r1 - 3)) goto L11;
        if (peekIntAtPosition(r8.getData(), r0) != 442) goto L10;
        r8.setPosition(r0 + 4);
        long r5 = readScrValueFromPack(r8);
        if (r5 == (-9223372036854775807L)) goto L10;
        return r5;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return -9223372036854775807L;
    }

    private int readLastScrValue(ExtractorInput r6, PositionHolder r7) {
        long r0 = r6.getLength();
        int r2 = (int) Math.min(20000, r0);
        long r02 = r0 - r2;
        if (r6.getPosition() == r02) goto L6;
        r7.position = r02;
        return 1;
    L6:
        this.packetBuffer.reset(r2);
        r6.resetPeekPosition();
        r6.peekFully(this.packetBuffer.getData(), 0, r2);
        this.lastScrValue = readLastScrValueFromBuffer(this.packetBuffer);
        this.isLastScrValueRead = true;
        return 0;
    }

    private long readLastScrValueFromBuffer(ParsableByteArray r7) {
        int r0 = r7.getPosition();
        int r1 = r7.limit() - 4;
    L4:
        if (r1 < r0) goto L11;
        if (peekIntAtPosition(r7.getData(), r1) != 442) goto L10;
        r7.setPosition(r1 + 4);
        long r4 = readScrValueFromPack(r7);
        if (r4 == (-9223372036854775807L)) goto L10;
        return r4;
    L10:
        r1 = r1 - 1;
        goto L4
    L11:
        return -9223372036854775807L;
    }

    public static long readScrValueFromPack(ParsableByteArray r6) {
        int r0 = r6.getPosition();
        if (r6.bytesLeft() >= 9) goto L5;
        return -9223372036854775807L;
    L5:
        byte[] r1 = new byte[9];
        r6.readBytes(r1, 0, 9);
        r6.setPosition(r0);
        if (checkMarkerBits(r1) == true) goto L9;
        return -9223372036854775807L;
    L9:
        return readScrValueFromPackHeader(r1);
    }

    private static long readScrValueFromPackHeader(byte[] r13) {
        byte r0 = r13[0];
        long r02 = ((((r0 & 56) >> 3) << 30) | ((r0 & 3) << 28)) | ((r13[1] & 255) << 20);
        byte r2 = r13[2];
        return (((r02 | (((r2 & 248) >> 3) << 15)) | ((r2 & 3) << 13)) | ((r13[3] & 255) << 5)) | ((r13[4] & 248) >> 3);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public TimestampAdjuster getScrTimestampAdjuster() {
        return this.scrTimestampAdjuster;
    }

    public boolean isDurationReadFinished() {
        return this.isDurationRead;
    }

    public int readDuration(ExtractorInput r5, PositionHolder r6) {
        if (this.isLastScrValueRead == true) goto L7;
        return readLastScrValue(r5, r6);
    L7:
        if (this.lastScrValue != (-9223372036854775807L)) goto L11;
        return finishReadDuration(r5);
    L11:
        if (this.isFirstScrValueRead == false) goto L13;
        long r0 = this.firstScrValue;
        if (r0 == (-9223372036854775807L)) goto L17;
        long r02 = this.scrTimestampAdjuster.adjustTsTimestamp(r0);
        this.durationUs = this.scrTimestampAdjuster.adjustTsTimestampGreaterThanPreviousTimestamp(this.lastScrValue) - r02;
        return finishReadDuration(r5);
    L17:
        return finishReadDuration(r5);
    L13:
        return readFirstScrValue(r5, r6);
    }
}
