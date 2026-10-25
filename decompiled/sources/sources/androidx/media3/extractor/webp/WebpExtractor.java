package androidx.media3.extractor.webp;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SingleSampleExtractor;

/* loaded from: classes4.dex */
public final class WebpExtractor implements Extractor {
    private final SingleSampleExtractor imageExtractor;
    private final ParsableByteArray scratch;

    public WebpExtractor() {
        this.scratch = new ParsableByteArray(4);
        this.imageExtractor = new SingleSampleExtractor(-1, -1, "image/webp");
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
    public boolean sniff(ExtractorInput r8) {
        this.scratch.reset(4);
        r8.peekFully(this.scratch.getData(), 0, 4);
        if (this.scratch.readUnsignedInt() == 1380533830) goto L5;
        return false;
    L5:
        r8.advancePeekPosition(4);
        this.scratch.reset(4);
        r8.peekFully(this.scratch.getData(), 0, 4);
        if (this.scratch.readUnsignedInt() != 1464156752) goto L9;
        return true;
    L9:
        return false;
    }
}
