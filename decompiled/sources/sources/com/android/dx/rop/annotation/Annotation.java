package com.android.dx.rop.annotation;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.MutabilityControl;
import com.android.dx.util.ToHuman;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public final class Annotation extends MutabilityControl implements Comparable<Annotation>, ToHuman {
    private final TreeMap<CstString, NameValuePair> elements;
    private final CstType type;
    private final AnnotationVisibility visibility;

    public Annotation(CstType r2, AnnotationVisibility r3) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        this.type = r2;
        this.visibility = r3;
        this.elements = new TreeMap();
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("visibility == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("type == null");
        throw null;
    }

    public void add(NameValuePair r3) {
        throwIfImmutable();
        if (r3 == null) goto L10;
        CstString r0 = r3.getName();
        if (this.elements.get(r0) != null) goto L8;
        this.elements.put(r0, r3);
        return;
    L8:
        Native$$ExternalSyntheticBUOutline5.m("name already added: ", r0);
        return;
    L10:
        f$$ExternalSyntheticBUOutline2.m("pair == null");
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(Annotation r3) {
        int r0 = this.type.compareTo2(r3.type);
        if (r0 == 0) goto L5;
        return r0;
    L5:
        int r02 = this.visibility.compareTo(r3.visibility);
        if (r02 == 0) goto L8;
        return r02;
    L8:
        Iterator<NameValuePair> r2 = this.elements.values().iterator();
        Iterator<NameValuePair> r32 = r3.elements.values().iterator();
    L10:
        if (r2.hasNext() == false) goto L17;
        if (r32.hasNext() == false) goto L17;
        int r03 = r2.next().compareTo2(r32.next());
        if (r03 == 0) goto L10;
        return r03;
    L17:
        if (r2.hasNext() == false) goto L21;
        return 1;
    L21:
        if (r32.hasNext() == false) goto L24;
        return -1;
    L24:
        return 0;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Annotation r1) {
        return compareTo2(r1);
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof Annotation) == true) goto L5;
        return false;
    L5:
        Annotation r42 = (Annotation) r4;
        if (this.type.equals(r42.type) == true) goto L8;
    L12:
        return false;
    L8:
        if (this.visibility != r42.visibility) goto L12;
        return this.elements.equals(r42.elements);
    }

    public Collection<NameValuePair> getNameValuePairs() {
        return Collections.unmodifiableCollection(this.elements.values());
    }

    public CstType getType() {
        return this.type;
    }

    public AnnotationVisibility getVisibility() {
        return this.visibility;
    }

    public int hashCode() {
        return (((this.type.hashCode() * 31) + this.elements.hashCode()) * 31) + this.visibility.hashCode();
    }

    public void put(NameValuePair r2) {
        throwIfImmutable();
        if (r2 == null) goto L6;
        this.elements.put(r2.getName(), r2);
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("pair == null");
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        StringBuilder r0 = new StringBuilder();
        r0.append(this.visibility.toHuman());
        r0.append("-annotation ");
        r0.append(this.type.toHuman());
        r0.append(" {");
        Iterator<NameValuePair> r4 = this.elements.values().iterator();
        boolean r1 = true;
    L4:
        if (r4.hasNext() == false) goto L10;
        NameValuePair r2 = r4.next();
        if (r1 == false) goto L8;
        r1 = false;
    L9:
        r0.append(r2.getName().toHuman());
        r0.append(": ");
        r0.append(r2.getValue().toHuman());
        goto L4
    L8:
        r0.append(", ");
        goto L9
    L10:
        r0.append("}");
        return r0.toString();
    }

    public String toString() {
        return toHuman();
    }
}
