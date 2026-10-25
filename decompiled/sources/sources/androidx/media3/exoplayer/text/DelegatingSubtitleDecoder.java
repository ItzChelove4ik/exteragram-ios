package androidx.media3.exoplayer.text;

import androidx.media3.extractor.text.SimpleSubtitleDecoder;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleParser;

/* loaded from: classes4.dex */
final class DelegatingSubtitleDecoder extends SimpleSubtitleDecoder {
    private final SubtitleParser subtitleParser;

    public DelegatingSubtitleDecoder(String r1, SubtitleParser r2) {
        super(r1);
        this.subtitleParser = r2;
    }

    @Override // androidx.media3.extractor.text.SimpleSubtitleDecoder
    public Subtitle decode(byte[] r1, int r2, boolean r3) {
        if (r3 == false) goto L5;
        this.subtitleParser.reset();
    L5:
        return this.subtitleParser.parseToLegacySubtitle(r1, 0, r2);
    }
}
