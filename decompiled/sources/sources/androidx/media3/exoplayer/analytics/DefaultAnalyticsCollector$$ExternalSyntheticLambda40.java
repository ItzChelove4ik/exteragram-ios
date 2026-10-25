package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import java.io.IOException;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda40 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ LoadEventInfo f$1;
    public final /* synthetic */ MediaLoadData f$2;
    public final /* synthetic */ IOException f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda40(AnalyticsListener.EventTime r1, LoadEventInfo r2, MediaLoadData r3, IOException r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r7) {
        DefaultAnalyticsCollector.$r8$lambda$_77neWu5Vg8AE2X_5nMs7Wdw498(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (AnalyticsListener) r7);
    }
}
