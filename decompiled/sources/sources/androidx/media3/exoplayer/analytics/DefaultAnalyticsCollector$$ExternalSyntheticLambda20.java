package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda20 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda20(AnalyticsListener.EventTime r1, boolean r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r3) {
        DefaultAnalyticsCollector.$r8$lambda$TlrYZMQTf3veSivtBYC8GlN4dG4(this.f$0, this.f$1, this.f$2, (AnalyticsListener) r3);
    }
}
