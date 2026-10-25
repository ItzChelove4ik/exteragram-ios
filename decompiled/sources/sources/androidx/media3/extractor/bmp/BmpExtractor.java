package androidx.media3.extractor.bmp;

import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SingleSampleExtractor;

/* loaded from: classes4.dex */
public final class BmpExtractor implements Extractor {
    private final SingleSampleExtractor imageExtractor;

    public BmpExtractor() {
        this.imageExtractor = new SingleSampleExtractor(16973, 2, "image/bmp");
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r1) {
        this.imageExtractor.init(r1);
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r1, PositionHolder r2) {
        return this.imageExtractor.read(r1, r2);
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r1, long r3) {
        this.imageExtractor.seek(r1, r3);
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r1) {
        return this.imageExtractor.sniff(r1);
    }
}
