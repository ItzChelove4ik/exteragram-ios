package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda21 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda21(AnalyticsListener.EventTime r1, int r2, int r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r4) {
        DefaultAnalyticsCollector.$r8$lambda$BJbK9foTDOLytGYx0qdKUrWcUTo(this.f$0, this.f$1, this.f$2, this.f$3, (AnalyticsListener) r4);
    }
}
