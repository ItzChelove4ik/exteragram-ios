package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import com.google.common.base.Predicate;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultTrackSelector$$ExternalSyntheticLambda9 implements Predicate {
    public final /* synthetic */ DefaultTrackSelector f$0;
    public final /* synthetic */ DefaultTrackSelector.Parameters f$1;

    public /* synthetic */ DefaultTrackSelector$$ExternalSyntheticLambda9(DefaultTrackSelector r1, DefaultTrackSelector.Parameters r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.common.base.Predicate
    public final boolean apply(Object r2) {
        return DefaultTrackSelector.m844$r8$lambda$ZyLy63p4VGAMnoSurBI1BFReI(this.f$0, this.f$1, (Format) r2);
    }
}
