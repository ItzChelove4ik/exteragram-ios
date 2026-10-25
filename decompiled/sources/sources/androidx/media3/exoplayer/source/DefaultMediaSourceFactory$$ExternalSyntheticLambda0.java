package androidx.media3.exoplayer.source;

import androidx.media3.common.Format;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultMediaSourceFactory$$ExternalSyntheticLambda0 implements ExtractorsFactory {
    public final /* synthetic */ DefaultMediaSourceFactory f$0;
    public final /* synthetic */ Format f$1;

    public /* synthetic */ DefaultMediaSourceFactory$$ExternalSyntheticLambda0(DefaultMediaSourceFactory r1, Format r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public final Extractor[] createExtractors() {
        return DefaultMediaSourceFactory.m834$r8$lambda$_VZ2pXMbMSLEQ9WgwhkaEf3oJI(this.f$0, this.f$1);
    }
}
