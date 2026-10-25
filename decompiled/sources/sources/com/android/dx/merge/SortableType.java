package com.android.dx.merge;

import com.android.dex.ClassDef;
import com.android.dex.Dex;
import com.android.dex.DexException;
import java.util.Comparator;

/* loaded from: classes4.dex */
final class SortableType {
    public static final Comparator<SortableType> NULLS_LAST_ORDER = null;
    private final ClassDef classDef;
    private int depth;
    private final Dex dex;
    private final IndexMap indexMap;

    /* renamed from: com.android.dx.merge.SortableType$1, reason: invalid class name */
    public static class AnonymousClass1 implements Comparator<SortableType> {
        public AnonymousClass1() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(SortableType r2, SortableType r3) {
            if (r2 != r3) goto L5;
            return 0;
        L5:
            if (r3 != null) goto L8;
            return -1;
        L8:
            if (r2 != null) goto L12;
            return 1;
        L12:
            if (SortableType.access$000(r2) == SortableType.access$000(r3)) goto L16;
            int r1 = SortableType.access$000(r2);
            int r22 = SortableType.access$000(r3);
        L15:
            return r1 - r22;
        L16:
            r1 = r2.getTypeIndex();
            r22 = r3.getTypeIndex();
            goto L15
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(SortableType r1, SortableType r2) {
            return compare2(r1, r2);
        }
    }

    static {
        NULLS_LAST_ORDER = new AnonymousClass1();
    }

    public SortableType(Dex r2, IndexMap r3, ClassDef r4) {
        this.depth = -1;
        this.dex = r2;
        this.indexMap = r3;
        this.classDef = r4;
    }

    public static /* synthetic */ int access$000(SortableType r0) {
        return r0.depth;
    }

    public ClassDef getClassDef() {
        return this.classDef;
    }

    public Dex getDex() {
        return this.dex;
    }

    public IndexMap getIndexMap() {
        return this.indexMap;
    }

    public int getTypeIndex() {
        return this.classDef.getTypeIndex();
    }

    public boolean isDepthAssigned() {
        if (this.depth == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean tryAssignDepth(SortableType[] r9) {
        if (this.classDef.getSupertypeIndex() != (-1)) goto L5;
        int r0 = 0;
    L13:
        short[] r4 = this.classDef.getInterfaces();
        int r5 = r4.length;
        int r6 = 0;
    L14:
        if (r6 >= r5) goto L23;
        SortableType r7 = r9[r4[r6]];
        if (r7 != null) goto L18;
        r0 = Math.max(r0, 1);
    L22:
        r6 = r6 + 1;
        goto L14
    L18:
        int r72 = r7.depth;
        if (r72 == (-1)) goto L20;
        r0 = Math.max(r0, r72);
        goto L22
    L20:
        return false;
    L23:
        this.depth = r0 + 1;
        return true;
    L5:
        int r02 = this.classDef.getSupertypeIndex();
        int r42 = this.classDef.getTypeIndex();
        ClassDef r52 = this.classDef;
        if (r02 == r42) goto L26;
        SortableType r03 = r9[r52.getSupertypeIndex()];
        if (r03 != null) goto L10;
        r0 = 1;
        goto L13
    L10:
        r0 = r03.depth;
        if (r0 != (-1)) goto L13;
        return false;
    L26:
        throw new DexException("Class with type index " + r52.getTypeIndex() + " extends itself");
    }
}
