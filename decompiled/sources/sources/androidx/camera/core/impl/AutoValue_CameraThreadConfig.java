package androidx.camera.core.impl;

import android.os.Handler;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
final class AutoValue_CameraThreadConfig extends CameraThreadConfig {
    private final Executor cameraExecutor;
    private final Handler schedulerHandler;

    public AutoValue_CameraThreadConfig(Executor r2, Handler r3) {
        if (r2 == null) goto L10;
        this.cameraExecutor = r2;
        if (r3 == null) goto L8;
        this.schedulerHandler = r3;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null schedulerHandler");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null cameraExecutor");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CameraThreadConfig) == false) goto L12;
        CameraThreadConfig r52 = (CameraThreadConfig) r5;
        if (this.cameraExecutor.equals(r52.getCameraExecutor()) == false) goto L12;
        if (this.schedulerHandler.equals(r52.getSchedulerHandler()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // androidx.camera.core.impl.CameraThreadConfig
    public Executor getCameraExecutor() {
        return this.cameraExecutor;
    }

    @Override // androidx.camera.core.impl.CameraThreadConfig
    public Handler getSchedulerHandler() {
        return this.schedulerHandler;
    }

    public int hashCode() {
        int r0 = (this.cameraExecutor.hashCode() ^ 1000003) * 1000003;
        return this.schedulerHandler.hashCode() ^ r0;
    }

    public String toString() {
        return "CameraThreadConfig{cameraExecutor=" + this.cameraExecutor + ", schedulerHandler=" + this.schedulerHandler + "}";
    }
}
