package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.dex.file.OffsettedItem;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.util.Iterator;
import java.util.List;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class UniformListItem<T extends OffsettedItem> extends OffsettedItem {
    private static final int HEADER_SIZE = 4;
    private final ItemType itemType;
    private final List<T> items;

    public UniformListItem(ItemType r3, List<T> r4) {
        super(getAlignment(r4), writeSize(r4));
        if (r3 == null) goto L6;
        this.items = r4;
        this.itemType = r3;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("itemType == null");
        throw null;
    }

    private static int getAlignment(List<? extends OffsettedItem> r1) {
        return Math.max(4, r1.get(0).getAlignment());
    L8:
        f$$ExternalSyntheticBUOutline1.m("items.size() == 0");
        return 0;
    L5:
        f$$ExternalSyntheticBUOutline2.m("items == null");
        return 0;
    }

    private int headerSize() {
        return getAlignment();
    }

    private static int writeSize(List<? extends OffsettedItem> r2) {
        OffsettedItem r0 = r2.get(0);
        return (r2.size() * r0.writeSize()) + getAlignment(r2);
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r2) {
        Iterator<T> r1 = this.items.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().addContents(r2);
        goto L4
    }

    public final List<T> getItems() {
        return this.items;
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return this.itemType;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void place0(Section r7, int r8) {
        int r82 = r8 + headerSize();
        Iterator<T> r6 = this.items.iterator();
        boolean r0 = true;
        int r1 = -1;
        int r2 = -1;
    L4:
        if (r6.hasNext() == false) goto L21;
        T r3 = r6.next();
        int r4 = r3.writeSize();
        if (r0 == false) goto L8;
        r2 = r3.getAlignment();
        r0 = false;
        r1 = r4;
    L11:
        r82 = r3.place(r7, r82) + r4;
        goto L4
    L8:
        if (r4 != r1) goto L14;
        if (r3.getAlignment() == r2) goto L11;
        ByteString$$ExternalSyntheticBUOutline0.m("item alignment mismatch");
        return;
    L14:
        ByteString$$ExternalSyntheticBUOutline0.m("item size mismatch");
        return;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public final String toHuman() {
        StringBuilder r0 = new StringBuilder(100);
        r0.append("{");
        Iterator<T> r4 = this.items.iterator();
        boolean r1 = true;
    L4:
        if (r4.hasNext() == false) goto L10;
        T r2 = r4.next();
        if (r1 == false) goto L8;
        r1 = false;
    L9:
        r0.append(r2.toHuman());
        goto L4
    L8:
        r0.append(", ");
        goto L9
    L10:
        r0.append("}");
        return r0.toString();
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(100);
        r0.append(UniformListItem.class.getName());
        r0.append(this.items);
        return r0.toString();
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile r4, AnnotatedOutput r5) {
        int r0 = this.items.size();
        if (r5.annotates() == false) goto L5;
        r5.annotate(0, offsetString() + " " + typeName());
        StringBuilder r1 = new StringBuilder("  size: ");
        r1.append(Hex.u4(r0));
        r5.annotate(4, r1.toString());
    L5:
        r5.writeInt(r0);
        Iterator<T> r3 = this.items.iterator();
    L7:
        if (r3.hasNext() == false) goto L9;
        r3.next().writeTo(r4, r5);
        goto L7
    }
}
