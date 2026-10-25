package androidx.camera.core.processing.concurrent;

import androidx.camera.core.processing.SurfaceEdge;
import androidx.camera.core.processing.concurrent.DualSurfaceProcessorNode;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.List;

/* loaded from: classes3.dex */
final class AutoValue_DualSurfaceProcessorNode_In extends DualSurfaceProcessorNode.In {
    private final List<DualOutConfig> outConfigs;
    private final SurfaceEdge primarySurfaceEdge;
    private final SurfaceEdge secondarySurfaceEdge;

    public AutoValue_DualSurfaceProcessorNode_In(SurfaceEdge r2, SurfaceEdge r3, List<DualOutConfig> r4) {
        if (r2 == null) goto L14;
        this.primarySurfaceEdge = r2;
        if (r3 == null) goto L12;
        this.secondarySurfaceEdge = r3;
        if (r4 == null) goto L10;
        this.outConfigs = r4;
        return;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null outConfigs");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("Null secondarySurfaceEdge");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline2.m("Null primarySurfaceEdge");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof DualSurfaceProcessorNode.In) == false) goto L14;
        DualSurfaceProcessorNode.In r52 = (DualSurfaceProcessorNode.In) r5;
        if (this.primarySurfaceEdge.equals(r52.getPrimarySurfaceEdge()) == false) goto L14;
        if (this.secondarySurfaceEdge.equals(r52.getSecondarySurfaceEdge()) == false) goto L14;
        if (this.outConfigs.equals(r52.getOutConfigs()) == false) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // androidx.camera.core.processing.concurrent.DualSurfaceProcessorNode.In
    public List<DualOutConfig> getOutConfigs() {
        return this.outConfigs;
    }

    @Override // androidx.camera.core.processing.concurrent.DualSurfaceProcessorNode.In
    public SurfaceEdge getPrimarySurfaceEdge() {
        return this.primarySurfaceEdge;
    }

    @Override // androidx.camera.core.processing.concurrent.DualSurfaceProcessorNode.In
    public SurfaceEdge getSecondarySurfaceEdge() {
        return this.secondarySurfaceEdge;
    }

    public int hashCode() {
        int r0 = (((this.primarySurfaceEdge.hashCode() ^ 1000003) * 1000003) ^ this.secondarySurfaceEdge.hashCode()) * 1000003;
        return this.outConfigs.hashCode() ^ r0;
    }

    public String toString() {
        return "In{primarySurfaceEdge=" + this.primarySurfaceEdge + ", secondarySurfaceEdge=" + this.secondarySurfaceEdge + ", outConfigs=" + this.outConfigs + "}";
    }
}
