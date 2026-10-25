package androidx.camera.core.impl;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_Identifier extends Identifier {
    private final Object value;

    public AutoValue_Identifier(Object r1) {
        if (r1 == null) goto L6;
        this.value = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null value");
        throw null;
    }

    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof Identifier) == true) goto L8;
        return false;
    L8:
        return this.value.equals(((Identifier) r2).getValue());
    }

    @Override // androidx.camera.core.impl.Identifier
    public Object getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() ^ 1000003;
    }

    public String toString() {
        return "Identifier{value=" + this.value + "}";
    }
}
