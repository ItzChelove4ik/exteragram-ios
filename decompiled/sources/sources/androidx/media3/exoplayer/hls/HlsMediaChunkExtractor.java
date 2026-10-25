package androidx.media3.exoplayer.hls;

import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;

/* loaded from: classes4.dex */
public interface HlsMediaChunkExtractor {
    void init(ExtractorOutput r1);

    boolean isPackedAudioExtractor();

    boolean isReusable();

    void onTruncatedSegmentParsed();

    boolean read(ExtractorInput r1);

    HlsMediaChunkExtractor recreate();
}
