package androidx.camera.core.imagecapture;

import android.util.Size;
import androidx.camera.core.ImageReaderProxyProvider;
import androidx.camera.core.imagecapture.CaptureNode;
import androidx.camera.core.imagecapture.TakePictureManager;
import androidx.camera.core.processing.Edge;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.List;

/* loaded from: classes3.dex */
final class AutoValue_CaptureNode_In extends CaptureNode.In {
    private final Edge<TakePictureManager.CaptureError> errorEdge;
    private final int inputFormat;
    private final List<Integer> outputFormats;
    private final PostviewSettings postviewSettings;
    private final Edge<ProcessingRequest> requestEdge;
    private final Size size;
    private final boolean virtualCamera;

    public AutoValue_CaptureNode_In(Size r1, int r2, List<Integer> r3, boolean r4, ImageReaderProxyProvider r5, PostviewSettings r6, Edge<ProcessingRequest> r7, Edge<TakePictureManager.CaptureError> r8) {
        if (r1 == null) goto L18;
        this.size = r1;
        this.inputFormat = r2;
        if (r3 == null) goto L16;
        this.outputFormats = r3;
        this.virtualCamera = r4;
        this.postviewSettings = r6;
        if (r7 == null) goto L14;
        this.requestEdge = r7;
        if (r8 == null) goto L12;
        this.errorEdge = r8;
        return;
    L12:
        f$$ExternalSyntheticBUOutline2.m("Null errorEdge");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline2.m("Null requestEdge");
        throw null;
    L16:
        f$$ExternalSyntheticBUOutline2.m("Null outputFormats");
        throw null;
    L18:
        f$$ExternalSyntheticBUOutline2.m("Null size");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CaptureNode.In) == false) goto L20;
        CaptureNode.In r52 = (CaptureNode.In) r5;
        if (this.size.equals(r52.getSize()) == false) goto L20;
        if (this.inputFormat != r52.getInputFormat()) goto L20;
        if (this.outputFormats.equals(r52.getOutputFormats()) == false) goto L20;
        if (this.virtualCamera != r52.isVirtualCamera()) goto L20;
        r52.getImageReaderProxyProvider();
        r52.getPostviewSettings();
        if (this.requestEdge.equals(r52.getRequestEdge()) == false) goto L20;
        if (this.errorEdge.equals(r52.getErrorEdge()) == false) goto L20;
        return true;
    L20:
        return false;
    }

    @Override // androidx.camera.core.imagecapture.CaptureNode.In
    public Edge<TakePictureManager.CaptureError> getErrorEdge() {
        return this.errorEdge;
    }

    @Override // androidx.camera.core.imagecapture.CaptureNode.In
    public ImageReaderProxyProvider getImageReaderProxyProvider() {
        return null;
    }

    @Override // androidx.camera.core.imagecapture.CaptureNode.In
    public int getInputFormat() {
        return this.inputFormat;
    }

    @Override // androidx.camera.core.imagecapture.CaptureNode.In
    public List<Integer> getOutputFormats() {
        return this.outputFormats;
    }

    @Override // androidx.camera.core.imagecapture.CaptureNode.In
    public PostviewSettings getPostviewSettings() {
        return this.postviewSettings;
    }

    @Override // androidx.camera.core.imagecapture.CaptureNode.In
    public Edge<ProcessingRequest> getRequestEdge() {
        return this.requestEdge;
    }

    @Override // androidx.camera.core.imagecapture.CaptureNode.In
    public Size getSize() {
        return this.size;
    }

    public int hashCode() {
        int r0 = (((((this.size.hashCode() ^ 1000003) * 1000003) ^ this.inputFormat) * 1000003) ^ this.outputFormats.hashCode()) * 1000003;
        if (this.virtualCamera == false) goto L5;
        int r2 = 1231;
    L6:
        int r02 = (((((r0 ^ r2) * (-721379959)) ^ 0) * 1000003) ^ this.requestEdge.hashCode()) * 1000003;
        return this.errorEdge.hashCode() ^ r02;
    L5:
        r2 = 1237;
        goto L6
    }

    @Override // androidx.camera.core.imagecapture.CaptureNode.In
    public boolean isVirtualCamera() {
        return this.virtualCamera;
    }

    public String toString() {
        return "In{size=" + this.size + ", inputFormat=" + this.inputFormat + ", outputFormats=" + this.outputFormats + ", virtualCamera=" + this.virtualCamera + ", imageReaderProxyProvider=null, postviewSettings=" + this.postviewSettings + ", requestEdge=" + this.requestEdge + ", errorEdge=" + this.errorEdge + "}";
    }
}
