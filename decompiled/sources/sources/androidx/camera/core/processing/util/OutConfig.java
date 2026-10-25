package androidx.camera.core.processing.util;

import android.graphics.Rect;
import android.util.Size;
import java.util.UUID;

/* loaded from: classes3.dex */
public abstract class OutConfig {
    public OutConfig() {
    }

    public static OutConfig of(int r7, int r8, Rect r9, Size r10, int r11, boolean r12) {
        return of(r7, r8, r9, r10, r11, r12, false);
    }

    public static OutConfig of(int r9, int r10, Rect r11, Size r12, int r13, boolean r14, boolean r15) {
        return new AutoValue_OutConfig(UUID.randomUUID(), r9, r10, r11, r12, r13, r14, r15);
    }

    public abstract Rect getCropRect();

    public abstract int getFormat();

    public abstract int getRotationDegrees();

    public abstract Size getSize();

    public abstract int getTargets();

    public abstract UUID getUuid();

    public abstract boolean isMirroring();

    public abstract boolean shouldRespectInputCropRect();
}
