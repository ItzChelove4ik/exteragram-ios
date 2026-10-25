package androidx.camera.core.impl;

import androidx.camera.core.impl.StateObservable;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_StateObservable_ErrorWrapper extends StateObservable.ErrorWrapper {
    private final Throwable error;

    public AutoValue_StateObservable_ErrorWrapper(Throwable r1) {
        if (r1 == null) goto L6;
        this.error = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null error");
        throw null;
    }

    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof StateObservable.ErrorWrapper) == true) goto L8;
        return false;
    L8:
        return this.error.equals(((StateObservable.ErrorWrapper) r2).getError());
    }

    @Override // androidx.camera.core.impl.StateObservable.ErrorWrapper
    public Throwable getError() {
        return this.error;
    }

    public int hashCode() {
        return this.error.hashCode() ^ 1000003;
    }

    public String toString() {
        return "ErrorWrapper{error=" + this.error + "}";
    }
}
