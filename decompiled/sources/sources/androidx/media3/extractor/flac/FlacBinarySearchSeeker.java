package androidx.media3.extractor.flac;

import androidx.media3.extractor.BinarySearchSeeker;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.FlacFrameReader;
import androidx.media3.extractor.FlacStreamMetadata;
import java.util.Objects;

/* loaded from: classes4.dex */
final class FlacBinarySearchSeeker extends BinarySearchSeeker {

    /* renamed from: androidx.media3.extractor.flac.FlacBinarySearchSeeker$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class FlacTimestampSeeker implements BinarySearchSeeker.TimestampSeeker {
        private final FlacStreamMetadata flacStreamMetadata;
        private final int frameStartMarker;
        private final FlacFrameReader.SampleNumberHolder sampleNumberHolder;

        private FlacTimestampSeeker(FlacStreamMetadata r1, int r2) {
            this.flacStreamMetadata = r1;
            this.frameStartMarker = r2;
            this.sampleNumberHolder = new FlacFrameReader.SampleNumberHolder();
        }

        public /* synthetic */ FlacTimestampSeeker(FlacStreamMetadata r1, int r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        private long findNextFrame(ExtractorInput r7) {
        L3:
            if (r7.getPeekPosition() >= (r7.getLength() - 6)) goto L8;
            if (FlacFrameReader.checkFrameHeaderFromPeek(r7, this.flacStreamMetadata, this.frameStartMarker, this.sampleNumberHolder) == true) goto L8;
            r7.advancePeekPosition(1);
        L8:
            if (r7.getPeekPosition() < (r7.getLength() - 6)) goto L12;
            r7.advancePeekPosition((int) (r7.getLength() - r7.getPeekPosition()));
            return this.flacStreamMetadata.totalSamples;
        L12:
            return this.sampleNumberHolder.sampleNumber;
        }

        @Override // androidx.media3.extractor.BinarySearchSeeker.TimestampSeeker
        public BinarySearchSeeker.TimestampSearchResult searchForTimestamp(ExtractorInput r10, long r11) {
            long r0 = r10.getPosition();
            long r2 = findNextFrame(r10);
            long r4 = r10.getPeekPosition();
            r10.advancePeekPosition(Math.max(6, this.flacStreamMetadata.minFrameSize));
            long r6 = findNextFrame(r10);
            long r9 = r10.getPeekPosition();
            if (r2 > r11) goto L9;
            if (r6 <= r11) goto L9;
            return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(r4);
        L9:
            if (r6 > r11) goto L13;
            return BinarySearchSeeker.TimestampSearchResult.underestimatedResult(r6, r9);
        L13:
            return BinarySearchSeeker.TimestampSearchResult.overestimatedResult(r2, r0);
        }
    }

    public FlacBinarySearchSeeker(FlacStreamMetadata r17, int r18, long r19, long r21) {
        Objects.requireNonNull(r17);
        super(new FlacBinarySearchSeeker$$ExternalSyntheticLambda0(r17), new FlacTimestampSeeker(r17, r18, null), r17.getDurationUs(), 0, r17.totalSamples, r19, r21, r17.getApproxBytesPerFrame(), Math.max(6, r17.minFrameSize));
    }
}
