package androidx.camera.core.processing.util;

import android.graphics.Rect;
import android.util.Size;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.UUID;

/* loaded from: classes3.dex */
final class AutoValue_OutConfig extends OutConfig {
    private final Rect getCropRect;
    private final int getFormat;
    private final int getRotationDegrees;
    private final Size getSize;
    private final int getTargets;
    private final UUID getUuid;
    private final boolean isMirroring;
    private final boolean shouldRespectInputCropRect;

    public AutoValue_OutConfig(UUID r2, int r3, int r4, Rect r5, Size r6, int r7, boolean r8, boolean r9) {
        if (r2 == null) goto L14;
        this.getUuid = r2;
        this.getTargets = r3;
        this.getFormat = r4;
        if (r5 == null) goto L12;
        this.getCropRect = r5;
        if (r6 == null) goto L10;
        this.getSize = r6;
        this.getRotationDegrees = r7;
        this.isMirroring = r8;
        this.shouldRespectInputCropRect = r9;
        return;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null getSize");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("Null getCropRect");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline2.m("Null getUuid");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof OutConfig) == false) goto L24;
        OutConfig r52 = (OutConfig) r5;
        if (this.getUuid.equals(r52.getUuid()) == false) goto L24;
        if (this.getTargets != r52.getTargets()) goto L24;
        if (this.getFormat != r52.getFormat()) goto L24;
        if (this.getCropRect.equals(r52.getCropRect()) == false) goto L24;
        if (this.getSize.equals(r52.getSize()) == false) goto L24;
        if (this.getRotationDegrees != r52.getRotationDegrees()) goto L24;
        if (this.isMirroring != r52.isMirroring()) goto L24;
        if (this.shouldRespectInputCropRect != r52.shouldRespectInputCropRect()) goto L24;
        return true;
    L24:
        return false;
    }

    @Override // androidx.camera.core.processing.util.OutConfig
    public Rect getCropRect() {
        return this.getCropRect;
    }

    @Override // androidx.camera.core.processing.util.OutConfig
    public int getFormat() {
        return this.getFormat;
    }

    @Override // androidx.camera.core.processing.util.OutConfig
    public int getRotationDegrees() {
        return this.getRotationDegrees;
    }

    @Override // androidx.camera.core.processing.util.OutConfig
    public Size getSize() {
        return this.getSize;
    }

    @Override // androidx.camera.core.processing.util.OutConfig
    public int getTargets() {
        return this.getTargets;
    }

    @Override // androidx.camera.core.processing.util.OutConfig
    public UUID getUuid() {
        return this.getUuid;
    }

    public int hashCode() {
        int r0 = (((((((((((this.getUuid.hashCode() ^ 1000003) * 1000003) ^ this.getTargets) * 1000003) ^ this.getFormat) * 1000003) ^ this.getCropRect.hashCode()) * 1000003) ^ this.getSize.hashCode()) * 1000003) ^ this.getRotationDegrees) * 1000003;
        int r3 = 1237;
        if (this.isMirroring == false) goto L5;
        int r2 = 1231;
    L6:
        int r02 = (r0 ^ r2) * 1000003;
        if (this.shouldRespectInputCropRect == false) goto L10;
        r3 = 1231;
    L10:
        return r02 ^ r3;
    L5:
        r2 = 1237;
        goto L6
    }

    @Override // androidx.camera.core.processing.util.OutConfig
    public boolean isMirroring() {
        return this.isMirroring;
    }

    @Override // androidx.camera.core.processing.util.OutConfig
    public boolean shouldRespectInputCropRect() {
        return this.shouldRespectInputCropRect;
    }

    public String toString() {
        return "OutConfig{getUuid=" + this.getUuid + ", getTargets=" + this.getTargets + ", getFormat=" + this.getFormat + ", getCropRect=" + this.getCropRect + ", getSize=" + this.getSize + ", getRotationDegrees=" + this.getRotationDegrees + ", isMirroring=" + this.isMirroring + ", shouldRespectInputCropRect=" + this.shouldRespectInputCropRect + "}";
    }
}
