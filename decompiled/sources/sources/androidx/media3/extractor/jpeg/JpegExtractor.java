package androidx.media3.extractor.jpeg;

import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SingleSampleExtractor;

/* loaded from: classes4.dex */
public final class JpegExtractor implements Extractor {
    private final Extractor extractor;

    public JpegExtractor(int r4) {
        if ((r4 & 1) == 0) goto L6;
        this.extractor = new SingleSampleExtractor(65496, 2, "image/jpeg");
        return;
    L6:
        this.extractor = new JpegMotionPhotoExtractor();
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r1) {
        this.extractor.init(r1);
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r1, PositionHolder r2) {
        return this.extractor.read(r1, r2);
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
        this.extractor.release();
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r1, long r3) {
        this.extractor.seek(r1, r3);
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r1) {
        return this.extractor.sniff(r1);
    }
}
