package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.TrackGroup;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import java.util.List;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultTrackSelector$$ExternalSyntheticLambda1 implements DefaultTrackSelector.TrackInfo.Factory {
    public final /* synthetic */ DefaultTrackSelector.Parameters f$0;

    public /* synthetic */ DefaultTrackSelector$$ExternalSyntheticLambda1(DefaultTrackSelector.Parameters r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo.Factory
    public final List create(int r1, TrackGroup r2, int[] r3) {
        return DefaultTrackSelector.$r8$lambda$JU1mh3M1J_D61mV1BweXBVb6UzI(this.f$0, r1, r2, r3);
    }
}
