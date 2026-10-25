package androidx.media3.exoplayer.hls;

import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public interface HlsExtractorFactory {
    HlsMediaChunkExtractor createExtractor(Uri r1, Format r2, List<Format> r3, TimestampAdjuster r4, Map<String, List<String>> r5, ExtractorInput r6, PlayerId r7);

    HlsExtractorFactory experimentalParseSubtitlesDuringExtraction(boolean r1);

    HlsExtractorFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1);

    Format getOutputTextFormat(Format r1);

    HlsExtractorFactory setSubtitleParserFactory(SubtitleParser.Factory r1);
}
