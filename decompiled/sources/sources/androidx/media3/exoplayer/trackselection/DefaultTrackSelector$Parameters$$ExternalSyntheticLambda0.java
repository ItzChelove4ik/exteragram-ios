package androidx.media3.exoplayer.trackselection;

import androidx.media3.exoplayer.source.TrackGroupArray;
import com.google.common.base.Function;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultTrackSelector$Parameters$$ExternalSyntheticLambda0 implements Function {
    public /* synthetic */ DefaultTrackSelector$Parameters$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object r1) {
        return ((TrackGroupArray) r1).toBundle();
    }
}
