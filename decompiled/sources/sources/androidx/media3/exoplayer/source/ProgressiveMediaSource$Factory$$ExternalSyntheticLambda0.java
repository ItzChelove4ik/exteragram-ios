package androidx.media3.exoplayer.source;

import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.ProgressiveMediaExtractor;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.ExtractorsFactory;

/* loaded from: classes4.dex */
public final /* synthetic */ class ProgressiveMediaSource$Factory$$ExternalSyntheticLambda0 implements ProgressiveMediaExtractor.Factory {
    public final /* synthetic */ ExtractorsFactory f$0;

    public /* synthetic */ ProgressiveMediaSource$Factory$$ExternalSyntheticLambda0(ExtractorsFactory r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor.Factory
    public final ProgressiveMediaExtractor createProgressiveMediaExtractor(PlayerId r1) {
        return ProgressiveMediaSource.Factory.$r8$lambda$h6Gx0DBq5OUxdxhehQgXI7wPWuk(this.f$0, r1);
    }
}
