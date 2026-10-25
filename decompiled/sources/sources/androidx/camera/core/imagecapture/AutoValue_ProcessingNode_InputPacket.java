package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageProxy;
import androidx.camera.core.imagecapture.ProcessingNode;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_ProcessingNode_InputPacket extends ProcessingNode.InputPacket {
    private final ImageProxy imageProxy;
    private final ProcessingRequest processingRequest;

    public AutoValue_ProcessingNode_InputPacket(ProcessingRequest r1, ImageProxy r2) {
        f$$ExternalSyntheticBUOutline2.m("Null processingRequest");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ProcessingNode.InputPacket) == false) goto L12;
        ProcessingNode.InputPacket r52 = (ProcessingNode.InputPacket) r5;
        if (this.processingRequest.equals(r52.getProcessingRequest()) == false) goto L12;
        if (this.imageProxy.equals(r52.getImageProxy()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // androidx.camera.core.imagecapture.ProcessingNode.InputPacket
    public ImageProxy getImageProxy() {
        return this.imageProxy;
    }

    @Override // androidx.camera.core.imagecapture.ProcessingNode.InputPacket
    public ProcessingRequest getProcessingRequest() {
        return this.processingRequest;
    }

    public int hashCode() {
        int r0 = (this.processingRequest.hashCode() ^ 1000003) * 1000003;
        return this.imageProxy.hashCode() ^ r0;
    }

    public String toString() {
        return "InputPacket{processingRequest=" + this.processingRequest + ", imageProxy=" + this.imageProxy + "}";
    }
}
