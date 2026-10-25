package androidx.media3.exoplayer.trackselection;

import android.graphics.Point;
import androidx.media3.common.TrackGroup;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import java.util.List;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultTrackSelector$$ExternalSyntheticLambda3 implements DefaultTrackSelector.TrackInfo.Factory {
    public final /* synthetic */ DefaultTrackSelector.Parameters f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ int[] f$2;
    public final /* synthetic */ Point f$3;

    public /* synthetic */ DefaultTrackSelector$$ExternalSyntheticLambda3(DefaultTrackSelector.Parameters r1, String r2, int[] r3, Point r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo.Factory
    public final List create(int r8, TrackGroup r9, int[] r10) {
        return DefaultTrackSelector.$r8$lambda$mEmKQSusMDZEMY2TdLalqESnSC0(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9, r10);
    }
}
