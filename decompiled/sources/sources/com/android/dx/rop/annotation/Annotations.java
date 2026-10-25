package com.android.dx.rop.annotation;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.MutabilityControl;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;
import okio.Buffer$$ExternalSyntheticBUOutline4;

/* loaded from: classes4.dex */
public final class Annotations extends MutabilityControl implements Comparable<Annotations> {
    public static final Annotations EMPTY = null;
    private final TreeMap<CstType, Annotation> annotations;

    static {
        Annotations r0 = new Annotations();
        EMPTY = r0;
        r0.setImmutable();
    }

    public Annotations() {
        this.annotations = new TreeMap();
    }

    public static Annotations combine(Annotations r1, Annotation r2) {
        Annotations r0 = new Annotations();
        r0.addAll(r1);
        r0.add(r2);
        r0.setImmutable();
        return r0;
    }

    public static Annotations combine(Annotations r1, Annotations r2) {
        Annotations r0 = new Annotations();
        r0.addAll(r1);
        r0.addAll(r2);
        r0.setImmutable();
        return r0;
    }

    public void add(Annotation r3) {
        throwIfImmutable();
        if (r3 == null) goto L10;
        CstType r0 = r3.getType();
        if (this.annotations.containsKey(r0) == true) goto L8;
        this.annotations.put(r0, r3);
        return;
    L8:
        Buffer$$ExternalSyntheticBUOutline4.m("duplicate type: ", r0.toHuman());
        return;
    L10:
        f$$ExternalSyntheticBUOutline2.m("annotation == null");
    }

    public void addAll(Annotations r2) {
        throwIfImmutable();
        if (r2 == null) goto L9;
        Iterator<Annotation> r22 = r2.annotations.values().iterator();
    L6:
        if (r22.hasNext() == false) goto L8;
        add(r22.next());
        goto L6
    L8:
        return;
    L9:
        f$$ExternalSyntheticBUOutline2.m("toAdd == null");
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(Annotations r3) {
        Iterator<Annotation> r2 = this.annotations.values().iterator();
        Iterator<Annotation> r32 = r3.annotations.values().iterator();
    L4:
        if (r2.hasNext() == false) goto L11;
        if (r32.hasNext() == false) goto L11;
        int r0 = r2.next().compareTo2(r32.next());
        if (r0 == 0) goto L4;
        return r0;
    L11:
        if (r2.hasNext() == false) goto L15;
        return 1;
    L15:
        if (r32.hasNext() == false) goto L18;
        return -1;
    L18:
        return 0;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Annotations r1) {
        return compareTo2(r1);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof Annotations) == true) goto L7;
        return false;
    L7:
        return this.annotations.equals(((Annotations) r2).annotations);
    }

    public Collection<Annotation> getAnnotations() {
        return Collections.unmodifiableCollection(this.annotations.values());
    }

    public int hashCode() {
        return this.annotations.hashCode();
    }

    public int size() {
        return this.annotations.size();
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("annotations{");
        Iterator<Annotation> r4 = this.annotations.values().iterator();
        boolean r1 = true;
    L4:
        if (r4.hasNext() == false) goto L10;
        Annotation r2 = r4.next();
        if (r1 == false) goto L8;
        r1 = false;
    L9:
        r0.append(r2.toHuman());
        goto L4
    L8:
        r0.append(", ");
        goto L9
    L10:
        r0.append("}");
        return r0.toString();
    }
}
