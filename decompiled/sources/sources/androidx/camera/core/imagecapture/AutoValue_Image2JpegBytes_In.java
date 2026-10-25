package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageProxy;
import androidx.camera.core.imagecapture.Image2JpegBytes;
import androidx.camera.core.processing.Packet;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_Image2JpegBytes_In extends Image2JpegBytes.In {
    private final int jpegQuality;
    private final Packet<ImageProxy> packet;

    public AutoValue_Image2JpegBytes_In(Packet<ImageProxy> r1, int r2) {
        if (r1 == null) goto L6;
        this.packet = r1;
        this.jpegQuality = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null packet");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof Image2JpegBytes.In) == false) goto L12;
        Image2JpegBytes.In r52 = (Image2JpegBytes.In) r5;
        if (this.packet.equals(r52.getPacket()) == false) goto L12;
        if (this.jpegQuality != r52.getJpegQuality()) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // androidx.camera.core.imagecapture.Image2JpegBytes.In
    public int getJpegQuality() {
        return this.jpegQuality;
    }

    @Override // androidx.camera.core.imagecapture.Image2JpegBytes.In
    public Packet<ImageProxy> getPacket() {
        return this.packet;
    }

    public int hashCode() {
        int r0 = (this.packet.hashCode() ^ 1000003) * 1000003;
        return this.jpegQuality ^ r0;
    }

    public String toString() {
        return "In{packet=" + this.packet + ", jpegQuality=" + this.jpegQuality + "}";
    }
}
