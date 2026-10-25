package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstString;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;

/* loaded from: classes.dex */
public final class StringIdItem extends IndexedItem implements Comparable {
    private StringDataItem data;
    private final CstString value;

    public StringIdItem(CstString r2) {
        if (r2 == null) goto L6;
        this.value = r2;
        this.data = null;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("value == null");
        throw null;
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r3) {
        if (this.data != null) goto L6;
        MixedItemSection r32 = r3.getStringData();
        StringDataItem r0 = new StringDataItem(this.value);
        this.data = r0;
        r32.add(r0);
        return;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object r1) {
        return this.value.compareTo2(((StringIdItem) r1).value);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof StringIdItem) == true) goto L7;
        return false;
    L7:
        return this.value.equals(((StringIdItem) r2).value);
    }

    public StringDataItem getData() {
        return this.data;
    }

    public CstString getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_STRING_ID_ITEM;
    }

    @Override // com.android.dx.dex.file.Item
    public int writeSize() {
        return 4;
    }

    @Override // com.android.dx.dex.file.Item
    public void writeTo(DexFile r3, AnnotatedOutput r4) {
        int r32 = this.data.getAbsoluteOffset();
        if (r4.annotates() == false) goto L5;
        r4.annotate(0, indexString() + ' ' + this.value.toQuoted(100));
        StringBuilder r2 = new StringBuilder("  string_data_off: ");
        r2.append(Hex.u4(r32));
        r4.annotate(4, r2.toString());
    L5:
        r4.writeInt(r32);
    }
}
