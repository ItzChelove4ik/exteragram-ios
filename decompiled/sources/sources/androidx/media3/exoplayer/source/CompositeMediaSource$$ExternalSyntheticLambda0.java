package androidx.media3.exoplayer.source;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.source.MediaSource;

/* loaded from: classes4.dex */
public final /* synthetic */ class CompositeMediaSource$$ExternalSyntheticLambda0 implements MediaSource.MediaSourceCaller {
    public final /* synthetic */ CompositeMediaSource f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ CompositeMediaSource$$ExternalSyntheticLambda0(CompositeMediaSource r1, Object r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.MediaSourceCaller
    public final void onSourceInfoRefreshed(MediaSource r2, Timeline r3) {
        CompositeMediaSource.$r8$lambda$rq_us2tG3vcSaIoUs5QzhKY_2b4(this.f$0, this.f$1, r2, r3);
    }
}
