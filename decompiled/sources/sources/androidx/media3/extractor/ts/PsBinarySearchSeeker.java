package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.BinarySearchSeeker;
import androidx.media3.extractor.ExtractorInput;
import kotlin.UByte;

/* loaded from: classes4.dex */
final class PsBinarySearchSeeker extends BinarySearchSeeker {

    /* renamed from: androidx.media3.extractor.ts.PsBinarySearchSeeker$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class PsScrSeeker implements BinarySearchSeeker.TimestampSeeker {
        private final ParsableByteArray packetBuffer;
        private final TimestampAdjuster scrTimestampAdjuster;

        private PsScrSeeker(TimestampAdjuster r1) {
            this.scrTimestampAdjuster = r1;
            this.packetBuffer = new ParsableByteArray();
        }

        public /* synthetic */ PsScrSeeker(TimestampAdjuster r1, AnonymousClass1 r2) {
            this(r1);
        }

        private BinarySearchSeeker.TimestampSearchResult searchForScrValueInBuffer(ParsableByteArray r10, long r11, long r13) {
            int r0 = -1;
            int r3 = -1;
            long r4 = -9223372036854775807L;
        L4:
            if (r10.bytesLeft() < 4) goto L25;
            if (PsBinarySearchSeeker.access$100(r10.getData(), r10.getPosition()) != 442) goto L7;
            r10.skipBytes(4);
            long r6 = PsDurationReader.readScrValueFromPack(r10);
            if (r6 == (-9223372036854775807L)) goto L23;
            long r62 = this.scrTimestampAdjuster.adjustTsTimestamp(r6);
            if (r62 > r11) goto L13;
            if ((100000 + r62) > r11) goto L21;
            r3 = r10.getPosition();
            r4 = r62;
            goto L23
        L21:
            return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(r13 + r10.getPosition());
        L13:
            if (r4 != (-9223372036854775807L)) goto L17;
            return BinarySearchSeeker.TimestampSearchResult.overestimatedResult(r62, r13);
        L17:
            return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(r13 + r3);
        L23:
            skipToEndOfCurrentPack(r10);
            r0 = r10.getPosition();
            goto L4
        L7:
            r10.skipBytes(1);
            goto L4
        L25:
            if (r4 == (-9223372036854775807L)) goto L29;
            return BinarySearchSeeker.TimestampSearchResult.underestimatedResult(r4, r13 + r0);
        L29:
            return BinarySearchSeeker.TimestampSearchResult.NO_TIMESTAMP_IN_RANGE_RESULT;
        }

        private static void skipToEndOfCurrentPack(ParsableByteArray r5) {
            int r0 = r5.limit();
            if (r5.bytesLeft() >= 10) goto L6;
            r5.setPosition(r0);
            return;
        L6:
            r5.skipBytes(9);
            int r1 = r5.readUnsignedByte() & 7;
            if (r5.bytesLeft() >= r1) goto L10;
            r5.setPosition(r0);
            return;
        L10:
            r5.skipBytes(r1);
            if (r5.bytesLeft() >= 4) goto L15;
            r5.setPosition(r0);
            return;
        L15:
            if (PsBinarySearchSeeker.access$100(r5.getData(), r5.getPosition()) != 443) goto L22;
            r5.skipBytes(4);
            int r12 = r5.readUnsignedShort();
            if (r5.bytesLeft() >= r12) goto L20;
            r5.setPosition(r0);
            return;
        L20:
            r5.skipBytes(r12);
        L22:
            if (r5.bytesLeft() < 4) goto L36;
            int r13 = PsBinarySearchSeeker.access$100(r5.getData(), r5.getPosition());
            if (r13 == 442) goto L43;
            if (r13 == 441) goto L44;
            if ((r13 >>> 8) != 1) goto L45;
            r5.skipBytes(4);
            if (r5.bytesLeft() < 2) goto L33;
            int r14 = r5.readUnsignedShort();
            r5.setPosition(Math.min(r5.limit(), r5.getPosition() + r14));
            goto L22
        L33:
            r5.setPosition(r0);
            return;
        L45:
            return;
        L44:
            return;
        L43:
            return;
        }

        @Override // androidx.media3.extractor.BinarySearchSeeker.TimestampSeeker
        public void onSeekFinished() {
            this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        }

        @Override // androidx.media3.extractor.BinarySearchSeeker.TimestampSeeker
        public BinarySearchSeeker.TimestampSearchResult searchForTimestamp(ExtractorInput r7, long r8) {
            long r4 = r7.getPosition();
            int r0 = (int) Math.min(20000, r7.getLength() - r4);
            this.packetBuffer.reset(r0);
            r7.peekFully(this.packetBuffer.getData(), 0, r0);
            return searchForScrValueInBuffer(this.packetBuffer, r8, r4);
        }
    }

    public PsBinarySearchSeeker(TimestampAdjuster r17, long r18, long r20) {
        super(new BinarySearchSeeker.DefaultSeekTimestampConverter(), new PsScrSeeker(r17, null), r18, 0, r18 + 1, 0, r20, 188, 1000);
    }

    public static /* synthetic */ int access$100(byte[] r0, int r1) {
        return peekIntAtPosition(r0, r1);
    }

    private static int peekIntAtPosition(byte[] r2, int r3) {
        int r0 = (((r2[r3] & UByte.MAX_VALUE) << 24) | ((r2[r3 + 1] & UByte.MAX_VALUE) << 16)) | ((r2[r3 + 2] & UByte.MAX_VALUE) << 8);
        return (r2[r3 + 3] & UByte.MAX_VALUE) | r0;
    }
}
