package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public abstract class Atom implements Cloneable {
    public int alignment;
    public int type;
    public int type_limits;

    public Atom() {
        this.type = 0;
        this.type_limits = 1;
        this.alignment = -1;
    }

    public /* bridge */ /* synthetic */ Object clone() {
        return m3846clone();
    }

    /* renamed from: clone, reason: collision with other method in class */
    public Atom m3846clone() {
        return (Atom) super.clone();
    L4:
        return null;
    }

    public abstract Box createBox(TeXEnvironment r1);

    public int getLeftType() {
        return this.type;
    }

    public int getRightType() {
        return this.type;
    }
}
