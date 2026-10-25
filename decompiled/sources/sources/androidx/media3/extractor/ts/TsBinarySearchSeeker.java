package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.BinarySearchSeeker;
import androidx.media3.extractor.ExtractorInput;

/* loaded from: classes4.dex */
final class TsBinarySearchSeeker extends BinarySearchSeeker {

    public static final class TsPcrSeeker implements BinarySearchSeeker.TimestampSeeker {
        private final ParsableByteArray packetBuffer;
        private final int pcrPid;
        private final TimestampAdjuster pcrTimestampAdjuster;
        private final int timestampSearchBytes;

        public TsPcrSeeker(int r1, TimestampAdjuster r2, int r3) {
            this.pcrPid = r1;
            this.pcrTimestampAdjuster = r2;
            this.timestampSearchBytes = r3;
            this.packetBuffer = new ParsableByteArray();
        }

        private BinarySearchSeeker.TimestampSearchResult searchForPcrValueInBuffer(ParsableByteArray r17, long r18, long r20) {
            int r4 = r17.limit();
            long r5 = -1;
            long r9 = -1;
            long r11 = -9223372036854775807L;
        L4:
            if (r17.bytesLeft() < 188) goto L25;
            int r13 = TsUtil.findSyncBytePosition(r17.getData(), r17.getPosition(), r4);
            int r14 = r13 + 188;
            if (r14 > r4) goto L25;
            long r52 = TsUtil.readPcrFromPacket(r17, r13, this.pcrPid);
            if (r52 == (-9223372036854775807L)) goto L23;
            long r53 = this.pcrTimestampAdjuster.adjustTsTimestamp(r52);
            if (r53 > r18) goto L13;
            if ((100000 + r53) > r18) goto L21;
            r9 = r13;
            r11 = r53;
            goto L23
        L21:
            return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(r13 + r20);
        L13:
            if (r11 != (-9223372036854775807L)) goto L17;
            return BinarySearchSeeker.TimestampSearchResult.overestimatedResult(r53, r20);
        L17:
            return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(r20 + r9);
        L23:
            r17.setPosition(r14);
            r5 = r14;
        L25:
            if (r11 == (-9223372036854775807L)) goto L29;
            return BinarySearchSeeker.TimestampSearchResult.underestimatedResult(r11, r20 + r5);
        L29:
            return BinarySearchSeeker.TimestampSearchResult.NO_TIMESTAMP_IN_RANGE_RESULT;
        }

        @Override // androidx.media3.extractor.BinarySearchSeeker.TimestampSeeker
        public void onSeekFinished() {
            this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        }

        @Override // androidx.media3.extractor.BinarySearchSeeker.TimestampSeeker
        public BinarySearchSeeker.TimestampSearchResult searchForTimestamp(ExtractorInput r7, long r8) {
            long r4 = r7.getPosition();
            int r0 = (int) Math.min(this.timestampSearchBytes, r7.getLength() - r4);
            this.packetBuffer.reset(r0);
            r7.peekFully(this.packetBuffer.getData(), 0, r0);
            return searchForPcrValueInBuffer(this.packetBuffer, r8, r4);
        }
    }

    public TsBinarySearchSeeker(TimestampAdjuster r17, long r18, long r20, int r22, int r23) {
        super(new BinarySearchSeeker.DefaultSeekTimestampConverter(), new TsPcrSeeker(r22, r17, r23), r18, 0, r18 + 1, 0, r20, 188, 940);
    }
}
