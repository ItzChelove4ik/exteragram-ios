package androidx.media3.exoplayer;

import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.analytics.DefaultAnalyticsCollector;
import com.google.common.base.Function;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayer$Builder$$ExternalSyntheticLambda8 implements Function {
    public /* synthetic */ ExoPlayer$Builder$$ExternalSyntheticLambda8() {
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object r1) {
        return new DefaultAnalyticsCollector((Clock) r1);
    }
}
