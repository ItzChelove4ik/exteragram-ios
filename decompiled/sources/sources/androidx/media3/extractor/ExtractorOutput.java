package androidx.media3.extractor;

/* loaded from: classes4.dex */
public interface ExtractorOutput {
    public static final ExtractorOutput PLACEHOLDER = null;

    /* renamed from: androidx.media3.extractor.ExtractorOutput$1, reason: invalid class name */
    public class AnonymousClass1 implements ExtractorOutput {
        public AnonymousClass1() {
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public void endTracks() {
            throw new UnsupportedOperationException();
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public void seekMap(SeekMap r1) {
            throw new UnsupportedOperationException();
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public TrackOutput track(int r1, int r2) {
            throw new UnsupportedOperationException();
        }
    }

    static {
        PLACEHOLDER = new AnonymousClass1();
    }

    void endTracks();

    void seekMap(SeekMap r1);

    TrackOutput track(int r1, int r2);
}
