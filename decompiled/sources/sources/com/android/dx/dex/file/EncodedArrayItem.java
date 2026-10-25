package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstArray;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.ByteArrayAnnotatedOutput;

/* loaded from: classes4.dex */
public final class EncodedArrayItem extends OffsettedItem {
    private static final int ALIGNMENT = 1;
    private final CstArray array;
    private byte[] encodedForm;

    public EncodedArrayItem(CstArray r3) {
        super(1, -1);
        if (r3 == null) goto L6;
        this.array = r3;
        this.encodedForm = null;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("array == null");
        throw null;
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r1) {
        ValueEncoder.addContents(r1, this.array);
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public int compareTo0(OffsettedItem r1) {
        return this.array.compareTo2(((EncodedArrayItem) r1).array);
    }

    public int hashCode() {
        return this.array.hashCode();
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_ENCODED_ARRAY_ITEM;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void place0(Section r3, int r4) {
        ByteArrayAnnotatedOutput r42 = new ByteArrayAnnotatedOutput();
        new ValueEncoder(r3.getFile(), r42).writeArray(this.array, false);
        byte[] r32 = r42.toByteArray();
        this.encodedForm = r32;
        setWriteSize(r32.length);
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public String toHuman() {
        return this.array.toHuman();
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile r3, AnnotatedOutput r4) {
        if (r4.annotates() == false) goto L6;
        r4.annotate(0, offsetString() + " encoded array");
        new ValueEncoder(r3, r4).writeArray(this.array, true);
        return;
    L6:
        r4.write(this.encodedForm);
    }
}
