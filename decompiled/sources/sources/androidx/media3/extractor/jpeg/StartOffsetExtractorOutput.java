package androidx.media3.extractor.jpeg;

import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ForwardingSeekMap;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.TrackOutput;

/* loaded from: classes4.dex */
public final class StartOffsetExtractorOutput implements ExtractorOutput {
    private final ExtractorOutput extractorOutput;
    private final long startOffset;

    /* renamed from: androidx.media3.extractor.jpeg.StartOffsetExtractorOutput$1, reason: invalid class name */
    public class AnonymousClass1 extends ForwardingSeekMap {
        final /* synthetic */ StartOffsetExtractorOutput this$0;
        final /* synthetic */ SeekMap val$seekMap;

        public AnonymousClass1(StartOffsetExtractorOutput r1, SeekMap r2, SeekMap r3) {
            this.this$0 = r1;
            this.val$seekMap = r3;
            super(r2);
        }

        @Override // androidx.media3.extractor.ForwardingSeekMap, androidx.media3.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long r9) {
            SeekMap.SeekPoints r92 = this.val$seekMap.getSeekPoints(r9);
            SeekPoint r1 = r92.first;
            SeekPoint r0 = new SeekPoint(r1.timeUs, r1.position + StartOffsetExtractorOutput.access$000(this.this$0));
            SeekPoint r93 = r92.second;
            return new SeekMap.SeekPoints(r0, new SeekPoint(r93.timeUs, r93.position + StartOffsetExtractorOutput.access$000(this.this$0)));
        }
    }

    public StartOffsetExtractorOutput(long r1, ExtractorOutput r3) {
        this.startOffset = r1;
        this.extractorOutput = r3;
    }

    public static /* synthetic */ long access$000(StartOffsetExtractorOutput r2) {
        return r2.startOffset;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
        this.extractorOutput.endTracks();
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(SeekMap r3) {
        this.extractorOutput.seekMap(new AnonymousClass1(this, r3, r3));
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public TrackOutput track(int r1, int r2) {
        return this.extractorOutput.track(r1, r2);
    }
}
