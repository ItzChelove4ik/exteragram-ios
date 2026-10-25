package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.RendererConfiguration;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class TrackSelectorResult {
    public final Object info;
    public final int length;
    public final RendererConfiguration[] rendererConfigurations;
    public final ExoTrackSelection[] selections;
    public final Tracks tracks;

    public TrackSelectorResult(RendererConfiguration[] r3, ExoTrackSelection[] r4, Tracks r5, Object r6) {
        if (r3.length != r4.length) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        this.rendererConfigurations = r3;
        this.selections = (ExoTrackSelection[]) r4.clone();
        this.tracks = r5;
        this.info = r6;
        this.length = r3.length;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public boolean isEquivalent(TrackSelectorResult r4) {
        if (r4 != null) goto L5;
    L16:
        return false;
    L5:
        if (r4.selections.length != this.selections.length) goto L16;
        int r1 = 0;
    L9:
        if (r1 >= this.selections.length) goto L14;
        if (isEquivalent(r4, r1) == false) goto L12;
        r1 = r1 + 1;
        goto L9
    L12:
        return false;
    L14:
        return true;
    }

    public boolean isEquivalent(TrackSelectorResult r4, int r5) {
        if (r4 != null) goto L6;
        return false;
    L6:
        if (Objects.equals(this.rendererConfigurations[r5], r4.rendererConfigurations[r5]) == true) goto L8;
    L11:
        return false;
    L8:
        if (Objects.equals(this.selections[r5], r4.selections[r5]) == false) goto L11;
        return true;
    }

    public boolean isRendererEnabled(int r1) {
        if (this.rendererConfigurations[r1] == null) goto L6;
        return true;
    L6:
        return false;
    }
}
