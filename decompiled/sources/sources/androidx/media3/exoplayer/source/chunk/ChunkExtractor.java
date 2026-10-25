package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;

/* loaded from: classes4.dex */
public interface ChunkExtractor {

    public interface Factory {
        ChunkExtractor createProgressiveMediaExtractor(int r1, Format r2, boolean r3, List<Format> r4, TrackOutput r5, PlayerId r6);

        Factory experimentalParseSubtitlesDuringExtraction(boolean r1);

        Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1);

        Format getOutputTextFormat(Format r1);

        Factory setSubtitleParserFactory(SubtitleParser.Factory r1);
    }

    public interface TrackOutputProvider {
        TrackOutput track(int r1, int r2);
    }

    ChunkIndex getChunkIndex();

    Format[] getSampleFormats();

    void init(TrackOutputProvider r1, long r2, long r4);

    boolean read(ExtractorInput r1);

    void release();
}
