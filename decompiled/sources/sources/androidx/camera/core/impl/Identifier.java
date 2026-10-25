package androidx.camera.core.impl;

/* loaded from: classes3.dex */
public abstract class Identifier {
    public Identifier() {
    }

    public static Identifier create(Object r1) {
        return new AutoValue_Identifier(r1);
    }

    public abstract Object getValue();
}
