package androidx.camera.core.processing;

import androidx.camera.core.processing.SurfaceProcessorNode;
import androidx.camera.core.processing.util.OutConfig;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.List;

/* loaded from: classes3.dex */
final class AutoValue_SurfaceProcessorNode_In extends SurfaceProcessorNode.In {
    private final List<OutConfig> outConfigs;
    private final SurfaceEdge surfaceEdge;

    public AutoValue_SurfaceProcessorNode_In(SurfaceEdge r2, List<OutConfig> r3) {
        if (r2 == null) goto L10;
        this.surfaceEdge = r2;
        if (r3 == null) goto L8;
        this.outConfigs = r3;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null outConfigs");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null surfaceEdge");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof SurfaceProcessorNode.In) == false) goto L12;
        SurfaceProcessorNode.In r52 = (SurfaceProcessorNode.In) r5;
        if (this.surfaceEdge.equals(r52.getSurfaceEdge()) == false) goto L12;
        if (this.outConfigs.equals(r52.getOutConfigs()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // androidx.camera.core.processing.SurfaceProcessorNode.In
    public List<OutConfig> getOutConfigs() {
        return this.outConfigs;
    }

    @Override // androidx.camera.core.processing.SurfaceProcessorNode.In
    public SurfaceEdge getSurfaceEdge() {
        return this.surfaceEdge;
    }

    public int hashCode() {
        int r0 = (this.surfaceEdge.hashCode() ^ 1000003) * 1000003;
        return this.outConfigs.hashCode() ^ r0;
    }

    public String toString() {
        return "In{surfaceEdge=" + this.surfaceEdge + ", outConfigs=" + this.outConfigs + "}";
    }
}
