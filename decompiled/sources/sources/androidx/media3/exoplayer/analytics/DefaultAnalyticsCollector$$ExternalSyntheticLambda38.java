package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda38 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ DecoderCounters f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda38(AnalyticsListener.EventTime r1, DecoderCounters r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r2) {
        DefaultAnalyticsCollector.m807$r8$lambda$VZrPDtOVzR2O10Xoj9V7Uj7KBE(this.f$0, this.f$1, (AnalyticsListener) r2);
    }
}
