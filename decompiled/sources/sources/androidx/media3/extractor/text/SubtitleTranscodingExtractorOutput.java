package androidx.media3.extractor.text;

import android.util.SparseArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;

/* loaded from: classes4.dex */
public final class SubtitleTranscodingExtractorOutput implements ExtractorOutput {
    private final ExtractorOutput delegate;
    private boolean hasNonTextTracks;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final SparseArray<SubtitleTranscodingTrackOutput> textTrackOutputs;

    public SubtitleTranscodingExtractorOutput(ExtractorOutput r1, SubtitleParser.Factory r2) {
        this.delegate = r1;
        this.subtitleParserFactory = r2;
        this.textTrackOutputs = new SparseArray();
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
        this.delegate.endTracks();
        if (this.hasNonTextTracks == false) goto L8;
        int r0 = 0;
    L6:
        if (r0 >= this.textTrackOutputs.size()) goto L10;
        this.textTrackOutputs.valueAt(r0).shouldSuppressParsingErrors(true);
        r0 = r0 + 1;
        goto L6
    L10:
        return;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(SeekMap r1) {
        this.delegate.seekMap(r1);
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public TrackOutput track(int r3, int r4) {
        if (r4 == 3) goto L6;
        this.hasNonTextTracks = true;
        return this.delegate.track(r3, r4);
    L6:
        SubtitleTranscodingTrackOutput r0 = this.textTrackOutputs.get(r3);
        if (r0 == null) goto L9;
        return r0;
    L9:
        SubtitleTranscodingTrackOutput r02 = new SubtitleTranscodingTrackOutput(this.delegate.track(r3, r4), this.subtitleParserFactory);
        this.textTrackOutputs.put(r3, r02);
        return r02;
    }
}
