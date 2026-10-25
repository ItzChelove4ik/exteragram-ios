package androidx.camera.core.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class Quirks {
    private final List<Quirk> mQuirks;

    public Quirks(List<Quirk> r2) {
        this.mQuirks = new ArrayList(r2);
    }

    public static String toString(Quirks r2) {
        ArrayList r0 = new ArrayList();
        Iterator<Quirk> r22 = r2.mQuirks.iterator();
    L4:
        if (r22.hasNext() == false) goto L7;
        r0.add(r22.next().getClass().getSimpleName());
        goto L4
    L7:
        return Quirks$$ExternalSyntheticBackport0.m(" | ", r0);
    }

    public boolean contains(Class<? extends Quirk> r2) {
        Iterator<Quirk> r1 = this.mQuirks.iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        if (r2.isAssignableFrom(r1.next().getClass()) == false) goto L4;
        return true;
    L9:
        return false;
    }

    public <T extends Quirk> T get(Class<T> r3) {
        Iterator<Quirk> r2 = this.mQuirks.iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        T r0 = (T) r2.next();
        if (r0.getClass() != r3) goto L4;
        return r0;
    L8:
        return null;
    }

    public <T extends Quirk> List<T> getAll(Class<T> r4) {
        ArrayList r0 = new ArrayList();
        Iterator<Quirk> r3 = this.mQuirks.iterator();
    L4:
        if (r3.hasNext() == false) goto L8;
        Quirk r1 = r3.next();
        if (r4.isAssignableFrom(r1.getClass()) == false) goto L4;
        r0.add(r1);
        goto L4
    L8:
        return r0;
    }
}
