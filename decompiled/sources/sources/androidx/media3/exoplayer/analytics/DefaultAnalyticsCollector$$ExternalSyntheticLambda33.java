package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda33 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda33(AnalyticsListener.EventTime r1, int r2, long r3, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r8) {
        DefaultAnalyticsCollector.m798$r8$lambda$1vYjxRJ2chw1jS8CCU22gyC5WQ(this.f$0, this.f$1, this.f$2, this.f$3, (AnalyticsListener) r8);
    }
}
