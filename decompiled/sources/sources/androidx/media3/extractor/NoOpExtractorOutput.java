package androidx.media3.extractor;

/* loaded from: classes4.dex */
public final class NoOpExtractorOutput implements ExtractorOutput {
    public NoOpExtractorOutput() {
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(SeekMap r1) {
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public TrackOutput track(int r1, int r2) {
        return new DiscardingTrackOutput();
    }
}
