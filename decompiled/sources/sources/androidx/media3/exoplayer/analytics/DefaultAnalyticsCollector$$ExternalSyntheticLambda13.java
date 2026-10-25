package androidx.media3.exoplayer.analytics;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda13 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ AudioAttributes f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda13(AnalyticsListener.EventTime r1, AudioAttributes r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r2) {
        DefaultAnalyticsCollector.m799$r8$lambda$4qT9YXiU1WsyrWmg6L6byZndc(this.f$0, this.f$1, (AnalyticsListener) r2);
    }
}
