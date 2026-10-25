package androidx.media3.exoplayer.analytics;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda14 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ Metadata f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda14(AnalyticsListener.EventTime r1, Metadata r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r2) {
        DefaultAnalyticsCollector.$r8$lambda$S4lQQxj2nAf0u1bXhMcCSywBUSw(this.f$0, this.f$1, (AnalyticsListener) r2);
    }
}
