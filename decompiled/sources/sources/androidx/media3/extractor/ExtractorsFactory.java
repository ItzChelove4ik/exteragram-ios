package androidx.media3.extractor;

import android.net.Uri;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public interface ExtractorsFactory {
    public static final ExtractorsFactory EMPTY = null;

    static /* synthetic */ Extractor[] $r8$lambda$Km_UToGAasPNMKzsvW0dtCSXBAI() {
        return new Extractor[0];
    }

    static {
        EMPTY = new ExtractorsFactory$$ExternalSyntheticLambda0();
    }

    Extractor[] createExtractors();

    default Extractor[] createExtractors(Uri r1, Map<String, List<String>> r2) {
        return createExtractors();
    }

    default ExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1) {
        return this;
    }

    @Deprecated
    default ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean r1) {
        return this;
    }

    default ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory r1) {
        return this;
    }
}
