package androidx.media3.extractor.flac;

import androidx.media3.extractor.BinarySearchSeeker;
import androidx.media3.extractor.FlacStreamMetadata;

/* loaded from: classes4.dex */
public final /* synthetic */ class FlacBinarySearchSeeker$$ExternalSyntheticLambda0 implements BinarySearchSeeker.SeekTimestampConverter {
    public final /* synthetic */ FlacStreamMetadata f$0;

    public /* synthetic */ FlacBinarySearchSeeker$$ExternalSyntheticLambda0(FlacStreamMetadata r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.extractor.BinarySearchSeeker.SeekTimestampConverter
    public final long timeUsToTargetTime(long r1) {
        return this.f$0.getSampleNumber(r1);
    }
}
