package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.upstream.BandwidthMeter;

/* loaded from: classes4.dex */
public abstract class TrackSelector {
    private BandwidthMeter bandwidthMeter;
    private InvalidationListener listener;

    public interface InvalidationListener {
        void onRendererCapabilitiesChanged(Renderer r1);

        void onTrackSelectionsInvalidated();
    }

    public TrackSelector() {
    }

    public final BandwidthMeter getBandwidthMeter() {
        return (BandwidthMeter) Assertions.checkStateNotNull(this.bandwidthMeter);
    }

    public abstract TrackSelectionParameters getParameters();

    public abstract RendererCapabilities.Listener getRendererCapabilitiesListener();

    public void init(InvalidationListener r2, BandwidthMeter r3) {
        if (this.listener != null) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        this.listener = r2;
        this.bandwidthMeter = r3;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public final void invalidate() {
        InvalidationListener r0 = this.listener;
        if (r0 == null) goto L6;
        r0.onTrackSelectionsInvalidated();
        return;
    }

    public final void invalidateForRendererCapabilitiesChange(Renderer r1) {
        InvalidationListener r0 = this.listener;
        if (r0 == null) goto L6;
        r0.onRendererCapabilitiesChanged(r1);
        return;
    }

    public abstract boolean isSetParametersSupported();

    public abstract void onSelectionActivated(Object r1);

    public void release() {
        this.listener = null;
        this.bandwidthMeter = null;
    }

    public abstract TrackSelectorResult selectTracks(RendererCapabilities[] r1, TrackGroupArray r2, MediaSource.MediaPeriodId r3, Timeline r4);

    public abstract void setAudioAttributes(AudioAttributes r1);

    public abstract void setParameters(TrackSelectionParameters r1);
}
