package androidx.media3.extractor.avif;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SingleSampleExtractor;

/* loaded from: classes4.dex */
public final class AvifExtractor implements Extractor {
    private final SingleSampleExtractor imageExtractor;
    private final ParsableByteArray scratch;

    public AvifExtractor() {
        this.scratch = new ParsableByteArray(4);
        this.imageExtractor = new SingleSampleExtractor(-1, -1, "image/avif");
    }

    private boolean readAndCompareFourBytes(ExtractorInput r4, int r5) {
        this.scratch.reset(4);
        r4.peekFully(this.scratch.getData(), 0, 4);
        if (this.scratch.readUnsignedInt() != r5) goto L6;
        return true;
    L6:
        return false;
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
    public boolean sniff(ExtractorInput r2) {
        r2.advancePeekPosition(4);
        if (readAndCompareFourBytes(r2, 1718909296) == true) goto L5;
        return false;
    L5:
        if (readAndCompareFourBytes(r2, 1635150182) == false) goto L10;
        return true;
    L10:
        return false;
    }
}
