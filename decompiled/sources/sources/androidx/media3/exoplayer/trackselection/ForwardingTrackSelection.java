package androidx.media3.exoplayer.trackselection;

import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class ForwardingTrackSelection implements ExoTrackSelection {
    private final ExoTrackSelection trackSelection;

    public ForwardingTrackSelection(ExoTrackSelection r1) {
        this.trackSelection = r1;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void disable() {
        this.trackSelection.disable();
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void enable() {
        this.trackSelection.enable();
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof ForwardingTrackSelection) == true) goto L10;
        return false;
    L10:
        return this.trackSelection.equals(((ForwardingTrackSelection) r2).trackSelection);
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int evaluateQueueSize(long r1, List<? extends MediaChunk> r3) {
        return this.trackSelection.evaluateQueueSize(r1, r3);
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public boolean excludeTrack(int r1, long r2) {
        return this.trackSelection.excludeTrack(r1, r2);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public int getIndexInTrackGroup(int r1) {
        return this.trackSelection.getIndexInTrackGroup(r1);
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int getSelectedIndex() {
        return this.trackSelection.getSelectedIndex();
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int getSelectedIndexInTrackGroup() {
        return this.trackSelection.getSelectedIndexInTrackGroup();
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public Object getSelectionData() {
        return this.trackSelection.getSelectionData();
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int getSelectionReason() {
        return this.trackSelection.getSelectionReason();
    }

    public ExoTrackSelection getWrappedInstance() {
        return this.trackSelection;
    }

    public int hashCode() {
        return this.trackSelection.hashCode();
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public int indexOf(int r1) {
        return this.trackSelection.indexOf(r1);
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public boolean isTrackExcluded(int r1, long r2) {
        return this.trackSelection.isTrackExcluded(r1, r2);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelection
    public int length() {
        return this.trackSelection.length();
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void onDiscontinuity() {
        this.trackSelection.onDiscontinuity();
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void onPlayWhenReadyChanged(boolean r1) {
        this.trackSelection.onPlayWhenReadyChanged(r1);
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void onPlaybackSpeed(float r1) {
        this.trackSelection.onPlaybackSpeed(r1);
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void onRebuffer() {
        this.trackSelection.onRebuffer();
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public boolean shouldCancelChunkLoad(long r1, Chunk r3, List<? extends MediaChunk> r4) {
        return this.trackSelection.shouldCancelChunkLoad(r1, r3, r4);
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void updateSelectedTrack(long r1, long r3, long r5, List<? extends MediaChunk> r7, MediaChunkIterator[] r8) {
        this.trackSelection.updateSelectedTrack(r1, r3, r5, r7, r8);
    }
}
