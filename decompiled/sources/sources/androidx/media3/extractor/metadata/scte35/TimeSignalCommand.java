package androidx.media3.extractor.metadata.scte35;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;

/* loaded from: classes4.dex */
public final class TimeSignalCommand extends SpliceCommand {
    public final long playbackPositionUs;
    public final long ptsTime;

    private TimeSignalCommand(long r1, long r3) {
        this.ptsTime = r1;
        this.playbackPositionUs = r3;
    }

    public static TimeSignalCommand parseFromSection(ParsableByteArray r1, long r2, TimestampAdjuster r4) {
        long r12 = parseSpliceTime(r1, r2);
        return new TimeSignalCommand(r12, r4.adjustTsTimestamp(r12));
    }

    public static long parseSpliceTime(ParsableByteArray r6, long r7) {
        long r0 = r6.readUnsignedByte();
        if ((128 & r0) != 0) goto L5;
        return -9223372036854775807L;
    L5:
        return 8589934591L & ((((r0 & 1) << 32) | r6.readUnsignedInt()) + r7);
    }

    @Override // androidx.media3.extractor.metadata.scte35.SpliceCommand
    public String toString() {
        return "SCTE-35 TimeSignalCommand { ptsTime=" + this.ptsTime + ", playbackPositionUs= " + this.playbackPositionUs + " }";
    }
}
