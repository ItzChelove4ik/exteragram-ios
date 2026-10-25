package com.android.dx.dex.file;

import com.android.dx.rop.cst.Constant;
import com.android.dx.util.AnnotatedOutput;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class UniformItemSection extends Section {
    public UniformItemSection(String r1, DexFile r2, int r3) {
        super(r1, r2, r3);
    }

    public abstract IndexedItem get(Constant r1);

    @Override // com.android.dx.dex.file.Section
    public final int getAbsoluteItemOffset(Item r2) {
        IndexedItem r22 = (IndexedItem) r2;
        return getAbsoluteOffset(r22.getIndex() * r22.writeSize());
    }

    public abstract void orderItems();

    @Override // com.android.dx.dex.file.Section
    public final void prepare0() {
        DexFile r0 = getFile();
        orderItems();
        Iterator<? extends Item> r2 = items().iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        r2.next().addContents(r0);
        goto L4
    }

    @Override // com.android.dx.dex.file.Section
    public final int writeSize() {
        Collection<? extends Item> r1 = items();
        int r0 = r1.size();
        if (r0 != 0) goto L7;
        return 0;
    L7:
        return r0 * r1.iterator().next().writeSize();
    }

    @Override // com.android.dx.dex.file.Section
    public final void writeTo0(AnnotatedOutput r4) {
        DexFile r0 = getFile();
        int r1 = getAlignment();
        Iterator<? extends Item> r3 = items().iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        r3.next().writeTo(r0, r4);
        r4.alignTo(r1);
        goto L4
    }
}
