package com.android.dx.dex.file;

import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;

/* loaded from: classes.dex */
public final class TypeListItem extends OffsettedItem {
    private static final int ALIGNMENT = 4;
    private static final int ELEMENT_SIZE = 2;
    private static final int HEADER_SIZE = 4;
    private final TypeList list;

    public TypeListItem(TypeList r3) {
        super(4, (r3.size() * 2) + 4);
        this.list = r3;
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r4) {
        TypeIdsSection r42 = r4.getTypeIds();
        int r0 = this.list.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r42.intern(this.list.getType(r1));
        r1 = r1 + 1;
        goto L3
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public int compareTo0(OffsettedItem r1) {
        return StdTypeList.compareContents(this.list, ((TypeListItem) r1).list);
    }

    public TypeList getList() {
        return this.list;
    }

    public int hashCode() {
        return StdTypeList.hashContents(this.list);
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_TYPE_LIST;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public String toHuman() {
        throw new RuntimeException("unsupported");
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile r8, AnnotatedOutput r9) {
        TypeIdsSection r82 = r8.getTypeIds();
        int r0 = this.list.size();
        int r2 = 0;
        if (r9.annotates() == false) goto L7;
        r9.annotate(0, offsetString() + " type_list");
        StringBuilder r1 = new StringBuilder("  size: ");
        r1.append(Hex.u4(r0));
        r9.annotate(4, r1.toString());
        int r12 = 0;
    L5:
        if (r12 >= r0) goto L7;
        Type r3 = this.list.getType(r12);
        r9.annotate(2, "  " + Hex.u2(r82.indexOf(r3)) + " // " + r3.toHuman());
        r12 = r12 + 1;
    L7:
        r9.writeInt(r0);
    L8:
        if (r2 >= r0) goto L10;
        r9.writeShort(r82.indexOf(this.list.getType(r2)));
        r2 = r2 + 1;
        goto L8
    }
}
