package androidx.media3.extractor;

/* loaded from: classes4.dex */
public abstract class ForwardingExtractorInput implements ExtractorInput {
    private final ExtractorInput input;

    public ForwardingExtractorInput(ExtractorInput r1) {
        this.input = r1;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void advancePeekPosition(int r1) {
        this.input.advancePeekPosition(r1);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean advancePeekPosition(int r1, boolean r2) {
        return this.input.advancePeekPosition(r1, r2);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public long getLength() {
        return this.input.getLength();
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public long getPeekPosition() {
        return this.input.getPeekPosition();
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public long getPosition() {
        return this.input.getPosition();
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public int peek(byte[] r1, int r2, int r3) {
        return this.input.peek(r1, r2, r3);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void peekFully(byte[] r1, int r2, int r3) {
        this.input.peekFully(r1, r2, r3);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean peekFully(byte[] r1, int r2, int r3, boolean r4) {
        return this.input.peekFully(r1, r2, r3, r4);
    }

    @Override // androidx.media3.extractor.ExtractorInput, androidx.media3.common.DataReader
    public int read(byte[] r1, int r2, int r3) {
        return this.input.read(r1, r2, r3);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void readFully(byte[] r1, int r2, int r3) {
        this.input.readFully(r1, r2, r3);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean readFully(byte[] r1, int r2, int r3, boolean r4) {
        return this.input.readFully(r1, r2, r3, r4);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void resetPeekPosition() {
        this.input.resetPeekPosition();
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public int skip(int r1) {
        return this.input.skip(r1);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void skipFully(int r1) {
        this.input.skipFully(r1);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean skipFully(int r1, boolean r2) {
        return this.input.skipFully(r1, r2);
    }
}
