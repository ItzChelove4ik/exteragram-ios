package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.TrackGroup;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import java.util.List;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultTrackSelector$$ExternalSyntheticLambda7 implements DefaultTrackSelector.TrackInfo.Factory {
    public final /* synthetic */ DefaultTrackSelector.Parameters f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ DefaultTrackSelector$$ExternalSyntheticLambda7(DefaultTrackSelector.Parameters r1, String r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo.Factory
    public final List create(int r7, TrackGroup r8, int[] r9) {
        return DefaultTrackSelector.$r8$lambda$xEvqaQbm4_pjUM81V7bbZMVIUic(this.f$0, this.f$1, this.f$2, r7, r8, r9);
    }
}
