package androidx.camera.core.imagecapture;

import androidx.camera.core.imagecapture.ProcessingNode;
import androidx.camera.core.processing.Edge;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.List;

/* loaded from: classes3.dex */
final class AutoValue_ProcessingNode_In extends ProcessingNode.In {
    private final Edge<ProcessingNode.InputPacket> edge;
    private final int inputFormat;
    private final List<Integer> outputFormats;
    private final Edge<ProcessingNode.InputPacket> postviewEdge;

    public AutoValue_ProcessingNode_In(Edge<ProcessingNode.InputPacket> r2, Edge<ProcessingNode.InputPacket> r3, int r4, List<Integer> r5) {
        if (r2 == null) goto L14;
        this.edge = r2;
        if (r3 == null) goto L12;
        this.postviewEdge = r3;
        this.inputFormat = r4;
        if (r5 == null) goto L10;
        this.outputFormats = r5;
        return;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null outputFormats");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("Null postviewEdge");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline2.m("Null edge");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ProcessingNode.In) == false) goto L16;
        ProcessingNode.In r52 = (ProcessingNode.In) r5;
        if (this.edge.equals(r52.getEdge()) == false) goto L16;
        if (this.postviewEdge.equals(r52.getPostviewEdge()) == false) goto L16;
        if (this.inputFormat != r52.getInputFormat()) goto L16;
        if (this.outputFormats.equals(r52.getOutputFormats()) == false) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // androidx.camera.core.imagecapture.ProcessingNode.In
    public Edge<ProcessingNode.InputPacket> getEdge() {
        return this.edge;
    }

    @Override // androidx.camera.core.imagecapture.ProcessingNode.In
    public int getInputFormat() {
        return this.inputFormat;
    }

    @Override // androidx.camera.core.imagecapture.ProcessingNode.In
    public List<Integer> getOutputFormats() {
        return this.outputFormats;
    }

    @Override // androidx.camera.core.imagecapture.ProcessingNode.In
    public Edge<ProcessingNode.InputPacket> getPostviewEdge() {
        return this.postviewEdge;
    }

    public int hashCode() {
        int r0 = (((((this.edge.hashCode() ^ 1000003) * 1000003) ^ this.postviewEdge.hashCode()) * 1000003) ^ this.inputFormat) * 1000003;
        return this.outputFormats.hashCode() ^ r0;
    }

    public String toString() {
        return "In{edge=" + this.edge + ", postviewEdge=" + this.postviewEdge + ", inputFormat=" + this.inputFormat + ", outputFormats=" + this.outputFormats + "}";
    }
}
