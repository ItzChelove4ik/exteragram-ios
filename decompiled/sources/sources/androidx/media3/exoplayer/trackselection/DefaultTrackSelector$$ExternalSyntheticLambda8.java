package androidx.media3.exoplayer.trackselection;

import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultTrackSelector$$ExternalSyntheticLambda8 implements Comparator {
    public /* synthetic */ DefaultTrackSelector$$ExternalSyntheticLambda8() {
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return DefaultTrackSelector.TextTrackInfo.compareSelections((List) r1, (List) r2);
    }
}
