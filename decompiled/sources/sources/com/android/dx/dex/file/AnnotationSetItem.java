package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.annotation.Annotation;
import com.android.dx.rop.annotation.Annotations;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.util.Iterator;

/* loaded from: classes4.dex */
public final class AnnotationSetItem extends OffsettedItem {
    private static final int ALIGNMENT = 4;
    private static final int ENTRY_WRITE_SIZE = 4;
    private final Annotations annotations;
    private final AnnotationItem[] items;

    public AnnotationSetItem(Annotations r5, DexFile r6) {
        super(4, writeSize(r5));
        this.annotations = r5;
        this.items = new AnnotationItem[r5.size()];
        Iterator<Annotation> r52 = r5.getAnnotations().iterator();
        int r0 = 0;
    L4:
        if (r52.hasNext() == false) goto L6;
        Annotation r1 = r52.next();
        this.items[r0] = new AnnotationItem(r1, r6);
        r0 = r0 + 1;
        goto L4
    }

    private static int writeSize(Annotations r0) {
        return (r0.size() * 4) + 4;
    L5:
        f$$ExternalSyntheticBUOutline2.m("list == null");
        return 0;
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r5) {
        MixedItemSection r52 = r5.getByteData();
        int r0 = this.items.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        AnnotationItem[] r2 = this.items;
        r2[r1] = (AnnotationItem) r52.intern(r2[r1]);
        r1 = r1 + 1;
        goto L3
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public int compareTo0(OffsettedItem r1) {
        return this.annotations.compareTo2(((AnnotationSetItem) r1).annotations);
    }

    public Annotations getAnnotations() {
        return this.annotations;
    }

    public int hashCode() {
        return this.annotations.hashCode();
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_ANNOTATION_SET_ITEM;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void place0(Section r1, int r2) {
        AnnotationItem.sortByTypeIdIndex(this.items);
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public String toHuman() {
        return this.annotations.toString();
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile r7, AnnotatedOutput r8) {
        boolean r72 = r8.annotates();
        int r0 = this.items.length;
        int r2 = 0;
        if (r72 == false) goto L5;
        r8.annotate(0, offsetString() + " annotation set");
        StringBuilder r3 = new StringBuilder("  size: ");
        r3.append(Hex.u4(r0));
        r8.annotate(4, r3.toString());
    L5:
        r8.writeInt(r0);
    L6:
        if (r2 >= r0) goto L11;
        int r32 = this.items[r2].getAbsoluteOffset();
        if (r72 == false) goto L10;
        r8.annotate(4, "  entries[" + Integer.toHexString(r2) + "]: " + Hex.u4(r32));
        this.items[r2].annotateTo(r8, "    ");
    L10:
        r8.writeInt(r32);
        r2 = r2 + 1;
        goto L6
    }
}
