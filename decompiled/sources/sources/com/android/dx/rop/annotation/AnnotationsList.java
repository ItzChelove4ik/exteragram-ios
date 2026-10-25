package com.android.dx.rop.annotation;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.util.FixedSizeList;

/* loaded from: classes4.dex */
public final class AnnotationsList extends FixedSizeList {
    public static final AnnotationsList EMPTY = null;

    static {
        EMPTY = new AnnotationsList(0);
    }

    public AnnotationsList(int r1) {
        super(r1);
    }

    public static AnnotationsList combine(AnnotationsList r5, AnnotationsList r6) {
        int r0 = r5.size();
        if (r0 != r6.size()) goto L9;
        AnnotationsList r1 = new AnnotationsList(r0);
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L7;
        r1.set(r2, Annotations.combine(r5.get(r2), r6.get(r2)));
        r2 = r2 + 1;
        goto L5
    L7:
        r1.setImmutable();
        return r1;
    L9:
        f$$ExternalSyntheticBUOutline1.m("list1.size() != list2.size()");
        return null;
    }

    public Annotations get(int r1) {
        return (Annotations) get0(r1);
    }

    public void set(int r1, Annotations r2) {
        r2.throwIfMutable();
        set0(r1, r2);
    }
}
