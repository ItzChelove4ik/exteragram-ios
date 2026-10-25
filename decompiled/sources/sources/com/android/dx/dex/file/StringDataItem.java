package com.android.dx.dex.file;

import com.android.dex.Leb128;
import com.android.dx.rop.cst.CstString;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;

/* loaded from: classes.dex */
public final class StringDataItem extends OffsettedItem {
    private final CstString value;

    public StringDataItem(CstString r3) {
        super(1, writeSize(r3));
        this.value = r3;
    }

    private static int writeSize(CstString r1) {
        return (Leb128.unsignedLeb128Size(r1.getUtf16Size()) + r1.getUtf8Size()) + 1;
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r1) {
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public int compareTo0(OffsettedItem r1) {
        return this.value.compareTo2(((StringDataItem) r1).value);
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_STRING_DATA_ITEM;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public String toHuman() {
        return this.value.toQuoted();
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile r5, AnnotatedOutput r6) {
        ByteArray r52 = this.value.getBytes();
        int r0 = this.value.getUtf16Size();
        if (r6.annotates() == false) goto L5;
        r6.annotate(Leb128.unsignedLeb128Size(r0), "utf16_size: " + Hex.u4(r0));
        r6.annotate(r52.size() + 1, this.value.toQuoted());
    L5:
        r6.writeUleb128(r0);
        r6.write(r52);
        r6.writeByte(0);
    }
}
