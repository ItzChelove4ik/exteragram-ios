package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import java.util.List;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda22 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda22(AnalyticsListener.EventTime r1, List r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r2) {
        DefaultAnalyticsCollector.$r8$lambda$Y8DQqUgI7UBECd_3BYAapcRHq9c(this.f$0, this.f$1, (AnalyticsListener) r2);
    }
}
