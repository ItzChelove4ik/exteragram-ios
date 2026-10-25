package androidx.camera.core.processing.util;

import android.opengl.EGLSurface;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_OutputSurface extends OutputSurface {
    private final EGLSurface eglSurface;
    private final int height;
    private final int width;

    public AutoValue_OutputSurface(EGLSurface r1, int r2, int r3) {
        if (r1 == null) goto L6;
        this.eglSurface = r1;
        this.width = r2;
        this.height = r3;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null eglSurface");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof OutputSurface) == false) goto L14;
        OutputSurface r52 = (OutputSurface) r5;
        if (this.eglSurface.equals(r52.getEglSurface()) == false) goto L14;
        if (this.width != r52.getWidth()) goto L14;
        if (this.height != r52.getHeight()) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // androidx.camera.core.processing.util.OutputSurface
    public EGLSurface getEglSurface() {
        return this.eglSurface;
    }

    @Override // androidx.camera.core.processing.util.OutputSurface
    public int getHeight() {
        return this.height;
    }

    @Override // androidx.camera.core.processing.util.OutputSurface
    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int r0 = (((this.eglSurface.hashCode() ^ 1000003) * 1000003) ^ this.width) * 1000003;
        return this.height ^ r0;
    }

    public String toString() {
        return "OutputSurface{eglSurface=" + this.eglSurface + ", width=" + this.width + ", height=" + this.height + "}";
    }
}
