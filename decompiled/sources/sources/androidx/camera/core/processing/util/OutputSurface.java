package androidx.camera.core.processing.util;

import android.opengl.EGLSurface;

/* loaded from: classes3.dex */
public abstract class OutputSurface {
    public OutputSurface() {
    }

    public static OutputSurface of(EGLSurface r1, int r2, int r3) {
        return new AutoValue_OutputSurface(r1, r2, r3);
    }

    public abstract EGLSurface getEglSurface();

    public abstract int getHeight();

    public abstract int getWidth();
}
