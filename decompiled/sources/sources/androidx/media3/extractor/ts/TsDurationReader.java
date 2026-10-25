package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.PositionHolder;

/* loaded from: classes4.dex */
final class TsDurationReader {
    private long durationUs;
    private long firstPcrValue;
    private boolean isDurationRead;
    private boolean isFirstPcrValueRead;
    private boolean isLastPcrValueRead;
    private long lastPcrValue;
    private final ParsableByteArray packetBuffer;
    private final TimestampAdjuster pcrTimestampAdjuster;
    private final int timestampSearchBytes;

    public TsDurationReader(int r3) {
        this.timestampSearchBytes = r3;
        this.pcrTimestampAdjuster = new TimestampAdjuster(0);
        this.firstPcrValue = -9223372036854775807L;
        this.lastPcrValue = -9223372036854775807L;
        this.durationUs = -9223372036854775807L;
        this.packetBuffer = new ParsableByteArray();
    }

    private int finishReadDuration(ExtractorInput r3) {
        this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        this.isDurationRead = true;
        r3.resetPeekPosition();
        return 0;
    }

    private int readFirstPcrValue(ExtractorInput r6, PositionHolder r7, int r8) {
        int r0 = (int) Math.min(this.timestampSearchBytes, r6.getLength());
        if (r6.getPosition() == 0) goto L6;
        r7.position = 0;
        return 1;
    L6:
        this.packetBuffer.reset(r0);
        r6.resetPeekPosition();
        r6.peekFully(this.packetBuffer.getData(), 0, r0);
        this.firstPcrValue = readFirstPcrValueFromBuffer(this.packetBuffer, r8);
        this.isFirstPcrValueRead = true;
        return 0;
    }

    private long readFirstPcrValueFromBuffer(ParsableByteArray r6, int r7) {
        int r5 = r6.getPosition();
        int r0 = r6.limit();
    L4:
        if (r5 >= r0) goto L12;
        if (r6.getData()[r5] != 71) goto L11;
        long r3 = TsUtil.readPcrFromPacket(r6, r5, r7);
        if (r3 == (-9223372036854775807L)) goto L11;
        return r3;
    L11:
        r5 = r5 + 1;
        goto L4
    L12:
        return -9223372036854775807L;
    }

    private int readLastPcrValue(ExtractorInput r6, PositionHolder r7, int r8) {
        long r0 = r6.getLength();
        int r2 = (int) Math.min(this.timestampSearchBytes, r0);
        long r02 = r0 - r2;
        if (r6.getPosition() == r02) goto L6;
        r7.position = r02;
        return 1;
    L6:
        this.packetBuffer.reset(r2);
        r6.resetPeekPosition();
        r6.peekFully(this.packetBuffer.getData(), 0, r2);
        this.lastPcrValue = readLastPcrValueFromBuffer(this.packetBuffer, r8);
        this.isLastPcrValueRead = true;
        return 0;
    }

    private long readLastPcrValueFromBuffer(ParsableByteArray r7, int r8) {
        int r6 = r7.getPosition();
        int r0 = r7.limit();
        int r1 = r0 - 188;
    L4:
        if (r1 < r6) goto L12;
        if (TsUtil.isStartOfTsPacket(r7.getData(), r6, r0, r1) == false) goto L11;
        long r4 = TsUtil.readPcrFromPacket(r7, r1, r8);
        if (r4 == (-9223372036854775807L)) goto L11;
        return r4;
    L11:
        r1 = r1 - 1;
        goto L4
    L12:
        return -9223372036854775807L;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public TimestampAdjuster getPcrTimestampAdjuster() {
        return this.pcrTimestampAdjuster;
    }

    public boolean isDurationReadFinished() {
        return this.isDurationRead;
    }

    public int readDuration(ExtractorInput r5, PositionHolder r6, int r7) {
        if (r7 > 0) goto L6;
        return finishReadDuration(r5);
    L6:
        if (this.isLastPcrValueRead == true) goto L10;
        return readLastPcrValue(r5, r6, r7);
    L10:
        if (this.lastPcrValue != (-9223372036854775807L)) goto L14;
        return finishReadDuration(r5);
    L14:
        if (this.isFirstPcrValueRead == false) goto L16;
        long r62 = this.firstPcrValue;
        if (r62 == (-9223372036854775807L)) goto L20;
        long r63 = this.pcrTimestampAdjuster.adjustTsTimestamp(r62);
        this.durationUs = this.pcrTimestampAdjuster.adjustTsTimestampGreaterThanPreviousTimestamp(this.lastPcrValue) - r63;
        return finishReadDuration(r5);
    L20:
        return finishReadDuration(r5);
    L16:
        return readFirstPcrValue(r5, r6, r7);
    }
}
