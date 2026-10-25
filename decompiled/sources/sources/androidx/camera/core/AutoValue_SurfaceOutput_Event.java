package androidx.camera.core;

import androidx.camera.core.SurfaceOutput;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_SurfaceOutput_Event extends SurfaceOutput.Event {
    private final int eventCode;
    private final SurfaceOutput surfaceOutput;

    public AutoValue_SurfaceOutput_Event(int r1, SurfaceOutput r2) {
        this.eventCode = r1;
        if (r2 == null) goto L6;
        this.surfaceOutput = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null surfaceOutput");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof SurfaceOutput.Event) == false) goto L12;
        SurfaceOutput.Event r52 = (SurfaceOutput.Event) r5;
        if (this.eventCode != r52.getEventCode()) goto L12;
        if (this.surfaceOutput.equals(r52.getSurfaceOutput()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // androidx.camera.core.SurfaceOutput.Event
    public int getEventCode() {
        return this.eventCode;
    }

    @Override // androidx.camera.core.SurfaceOutput.Event
    public SurfaceOutput getSurfaceOutput() {
        return this.surfaceOutput;
    }

    public int hashCode() {
        int r0 = (this.eventCode ^ 1000003) * 1000003;
        return this.surfaceOutput.hashCode() ^ r0;
    }

    public String toString() {
        return "Event{eventCode=" + this.eventCode + ", surfaceOutput=" + this.surfaceOutput + "}";
    }
}
