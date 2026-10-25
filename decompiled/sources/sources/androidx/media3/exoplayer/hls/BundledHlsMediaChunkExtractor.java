package androidx.media3.exoplayer.hls;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.mp3.Mp3Extractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.ts.Ac3Extractor;
import androidx.media3.extractor.ts.Ac4Extractor;
import androidx.media3.extractor.ts.AdtsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class BundledHlsMediaChunkExtractor implements HlsMediaChunkExtractor {
    private static final PositionHolder POSITION_HOLDER = null;
    final Extractor extractor;
    private final Format multivariantPlaylistFormat;
    private final boolean parseSubtitlesDuringExtraction;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final TimestampAdjuster timestampAdjuster;

    static {
        POSITION_HOLDER = new PositionHolder();
    }

    public BundledHlsMediaChunkExtractor(Extractor r1, Format r2, TimestampAdjuster r3, SubtitleParser.Factory r4, boolean r5) {
        this.extractor = r1;
        this.multivariantPlaylistFormat = r2;
        this.timestampAdjuster = r3;
        this.subtitleParserFactory = r4;
        this.parseSubtitlesDuringExtraction = r5;
    }

    @Override // androidx.media3.exoplayer.hls.HlsMediaChunkExtractor
    public void init(ExtractorOutput r1) {
        this.extractor.init(r1);
    }

    @Override // androidx.media3.exoplayer.hls.HlsMediaChunkExtractor
    public boolean isPackedAudioExtractor() {
        Extractor r1 = this.extractor.getUnderlyingImplementation();
        if ((r1 instanceof AdtsExtractor) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof Ac3Extractor) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof Ac4Extractor) == false) goto L9;
        return true;
    L9:
        if ((r1 instanceof Mp3Extractor) == true) goto L17;
        return false;
    L17:
        return true;
    }

    @Override // androidx.media3.exoplayer.hls.HlsMediaChunkExtractor
    public boolean isReusable() {
        Extractor r1 = this.extractor.getUnderlyingImplementation();
        if ((r1 instanceof TsExtractor) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof FragmentedMp4Extractor) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // androidx.media3.exoplayer.hls.HlsMediaChunkExtractor
    public void onTruncatedSegmentParsed() {
        this.extractor.seek(0, 0);
    }

    @Override // androidx.media3.exoplayer.hls.HlsMediaChunkExtractor
    public boolean read(ExtractorInput r2) {
        if (this.extractor.read(r2, POSITION_HOLDER) != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.exoplayer.hls.HlsMediaChunkExtractor
    public HlsMediaChunkExtractor recreate() {
        boolean r1 = true;
        Assertions.checkState(!isReusable());
        if (this.extractor.getUnderlyingImplementation() == this.extractor) goto L6;
        r1 = false;
    L6:
        Assertions.checkState(r1, "Can't recreate wrapped extractors. Outer type: " + this.extractor.getClass());
        Extractor r0 = this.extractor;
        if ((r0 instanceof WebvttExtractor) == false) goto L11;
        Extractor r02 = new WebvttExtractor(this.multivariantPlaylistFormat.language, this.timestampAdjuster, this.subtitleParserFactory, this.parseSubtitlesDuringExtraction);
    L23:
        return new BundledHlsMediaChunkExtractor(r02, this.multivariantPlaylistFormat, this.timestampAdjuster, this.subtitleParserFactory, this.parseSubtitlesDuringExtraction);
    L11:
        if ((r0 instanceof AdtsExtractor) == false) goto L14;
        r02 = new AdtsExtractor();
        goto L23
    L14:
        if ((r0 instanceof Ac3Extractor) == false) goto L17;
        r02 = new Ac3Extractor();
        goto L23
    L17:
        if ((r0 instanceof Ac4Extractor) == false) goto L20;
        r02 = new Ac4Extractor();
        goto L23
    L20:
        if ((r0 instanceof Mp3Extractor) == false) goto L24;
        r02 = new Mp3Extractor();
        goto L23
    L24:
        Segment$$ExternalSyntheticBUOutline1.m("Unexpected extractor type for recreation: ".concat(r0.getClass().getSimpleName()));
        return null;
    }
}
