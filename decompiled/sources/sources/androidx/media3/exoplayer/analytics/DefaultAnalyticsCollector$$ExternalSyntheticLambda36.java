package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda36 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ LoadEventInfo f$1;
    public final /* synthetic */ MediaLoadData f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda36(AnalyticsListener.EventTime r1, LoadEventInfo r2, MediaLoadData r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r4) {
        DefaultAnalyticsCollector.$r8$lambda$DIdc623UX2VV8N7tNg4F2MQjb7w(this.f$0, this.f$1, this.f$2, this.f$3, (AnalyticsListener) r4);
    }
}
