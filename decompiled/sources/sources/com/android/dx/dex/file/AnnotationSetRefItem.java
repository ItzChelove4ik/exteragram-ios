package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;

/* loaded from: classes4.dex */
public final class AnnotationSetRefItem extends OffsettedItem {
    private static final int ALIGNMENT = 4;
    private static final int WRITE_SIZE = 4;
    private AnnotationSetItem annotations;

    public AnnotationSetRefItem(AnnotationSetItem r2) {
        super(4, 4);
        if (r2 == null) goto L6;
        this.annotations = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("annotations == null");
        throw null;
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r2) {
        this.annotations = (AnnotationSetItem) r2.getWordData().intern(this.annotations);
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_ANNOTATION_SET_REF_ITEM;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public String toHuman() {
        return this.annotations.toHuman();
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile r2, AnnotatedOutput r3) {
        int r1 = this.annotations.getAbsoluteOffset();
        if (r3.annotates() == false) goto L5;
        r3.annotate(4, "  annotations_off: " + Hex.u4(r1));
    L5:
        r3.writeInt(r1);
    }
}
