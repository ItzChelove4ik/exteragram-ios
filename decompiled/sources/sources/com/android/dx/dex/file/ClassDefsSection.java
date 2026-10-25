package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class ClassDefsSection extends UniformItemSection {
    private final TreeMap<Type, ClassDefItem> classDefs;
    private ArrayList<ClassDefItem> orderedDefs;

    public ClassDefsSection(DexFile r3) {
        super("class_defs", r3, 4);
        this.classDefs = new TreeMap();
        this.orderedDefs = null;
    }

    private int orderItems0(Type r5, int r6, int r7) {
        ClassDefItem r0 = this.classDefs.get(r5);
        if (r0 != null) goto L5;
    L19:
        return r6;
    L5:
        if (r0.hasIndex() == true) goto L19;
        int r1 = 0;
        if (r7 < 0) goto L17;
        int r72 = r7 - 1;
        CstType r52 = r0.getSuperclass();
        if (r52 == null) goto L12;
        r6 = orderItems0(r52.getClassType(), r6, r72);
    L12:
        TypeList r53 = r0.getInterfaces();
        int r2 = r53.size();
    L13:
        if (r1 >= r2) goto L15;
        r6 = orderItems0(r53.getType(r1), r6, r72);
        r1 = r1 + 1;
        goto L13
    L15:
        r0.setIndex(r6);
        this.orderedDefs.add(r0);
        return r6 + 1;
    L17:
        MVEL$$ExternalSyntheticBUOutline0.m("class circularity with ", r5);
        return 0;
    }

    public void add(ClassDefItem r3) {
        Type r0 = r3.getThisClass().getClassType();     // Catch: NullPointerException -> L9
        throwIfPrepared();
        if (this.classDefs.get(r0) != null) goto L7;
        this.classDefs.put(r0, r3);
        return;
    L7:
        Native$$ExternalSyntheticBUOutline5.m("already added: ", r0);
        return;
    L9:
        f$$ExternalSyntheticBUOutline2.m("clazz == null");
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public IndexedItem get(Constant r2) {
        if (r2 == null) goto L9;
        throwIfNotPrepared();
        Type r22 = ((CstType) r2).getClassType();
        ClassDefItem r1 = this.classDefs.get(r22);
        if (r1 == null) goto L7;
        return r1;
    L7:
        f$$ExternalSyntheticBUOutline1.m("not found");
        return null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("cst == null");
        return null;
    }

    @Override // com.android.dx.dex.file.Section
    public Collection<? extends Item> items() {
        ArrayList<ClassDefItem> r0 = this.orderedDefs;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return this.classDefs.values();
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public void orderItems() {
        int r0 = this.classDefs.size();
        this.orderedDefs = new ArrayList(r0);
        Iterator<Type> r1 = this.classDefs.keySet().iterator();
        int r2 = 0;
    L4:
        if (r1.hasNext() == false) goto L6;
        r2 = orderItems0(r1.next(), r2, r0 - r2);
        goto L4
    }

    public void writeHeaderPart(AnnotatedOutput r5) {
        throwIfNotPrepared();
        int r0 = this.classDefs.size();
        if (r0 != 0) goto L5;
        int r4 = 0;
    L7:
        if (r5.annotates() == false) goto L9;
        r5.annotate(4, "class_defs_size: " + Hex.u4(r0));
        r5.annotate(4, "class_defs_off:  " + Hex.u4(r4));
    L9:
        r5.writeInt(r0);
        r5.writeInt(r4);
        return;
    L5:
        r4 = getFileOffset();
        goto L7
    }
}
