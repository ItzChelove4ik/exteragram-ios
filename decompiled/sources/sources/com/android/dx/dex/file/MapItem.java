package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class MapItem extends OffsettedItem {
    private static final int ALIGNMENT = 4;
    private static final int WRITE_SIZE = 12;
    private final Item firstItem;
    private final int itemCount;
    private final Item lastItem;
    private final Section section;
    private final ItemType type;

    private MapItem(ItemType r3, Section r4, Item r5, Item r6, int r7) {
        super(4, 12);
        if (r3 == null) goto L18;
        if (r4 == null) goto L16;
        if (r5 == null) goto L14;
        if (r6 == null) goto L12;
        if (r7 <= 0) goto L10;
        this.type = r3;
        this.section = r4;
        this.firstItem = r5;
        this.lastItem = r6;
        this.itemCount = r7;
        return;
    L10:
        f$$ExternalSyntheticBUOutline1.m("itemCount <= 0");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("lastItem == null");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline2.m("firstItem == null");
        throw null;
    L16:
        f$$ExternalSyntheticBUOutline2.m("section == null");
        throw null;
    L18:
        f$$ExternalSyntheticBUOutline2.m("type == null");
        throw null;
    }

    private MapItem(Section r3) {
        super(4, 12);
        if (r3 == null) goto L6;
        this.type = ItemType.TYPE_MAP_LIST;
        this.section = r3;
        this.firstItem = null;
        this.lastItem = null;
        this.itemCount = 1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("section == null");
        throw null;
    }

    public static void addMap(Section[] r13, MixedItemSection r14) {
        if (r13 != null) goto L4;
        f$$ExternalSyntheticBUOutline2.m("sections == null");
        return;
    L4:
        if (r14.items().size() != 0) goto L23;
        ArrayList r0 = new ArrayList(50);
        int r1 = r13.length;
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L21;
        Section r6 = r13[r3];
        Iterator<? extends Item> r10 = r6.items().iterator();
        int r9 = 0;
        ItemType r5 = null;
        Item r7 = null;
        Item r8 = null;
    L9:
        if (r10.hasNext() == false) goto L16;
        Item r11 = r10.next();
        ItemType r12 = r11.itemType();
        if (r12 == r5) goto L15;
        if (r9 == 0) goto L14;
        r0.add(new MapItem(r5, r6, r7, r8, r9));
    L14:
        r9 = 0;
        r7 = r11;
        r5 = r12;
    L15:
        r9 = r9 + 1;
        r8 = r11;
        goto L9
    L16:
        if (r9 == 0) goto L18;
        r0.add(new MapItem(r5, r6, r7, r8, r9));
    L20:
        r3 = r3 + 1;
        goto L6
    L18:
        if (r6 != r14) goto L20;
        r0.add(new MapItem(r14));
        goto L20
    L21:
        r14.add(new UniformListItem(ItemType.TYPE_MAP_LIST, r0));
        return;
    L23:
        f$$ExternalSyntheticBUOutline1.m("mapSection.items().size() != 0");
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r1) {
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_MAP_ITEM;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public final String toHuman() {
        return toString();
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(100);
        r0.append(MapItem.class.getName());
        r0.append('{');
        r0.append(this.section.toString());
        r0.append(' ');
        r0.append(this.type.toHuman());
        r0.append('}');
        return r0.toString();
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile r6, AnnotatedOutput r7) {
        int r62 = this.type.getMapValue();
        Item r0 = this.firstItem;
        Section r1 = this.section;
        if (r0 != null) goto L5;
        int r02 = r1.getFileOffset();
    L7:
        if (r7.annotates() == false) goto L9;
        r7.annotate(0, offsetString() + ' ' + this.type.getTypeName() + " map");
        StringBuilder r12 = new StringBuilder("  type:   ");
        r12.append(Hex.u2(r62));
        r12.append(" // ");
        r12.append(this.type.toString());
        r7.annotate(2, r12.toString());
        r7.annotate(2, "  unused: 0");
        r7.annotate(4, "  size:   " + Hex.u4(this.itemCount));
        r7.annotate(4, "  offset: " + Hex.u4(r02));
    L9:
        r7.writeShort(r62);
        r7.writeShort(0);
        r7.writeInt(this.itemCount);
        r7.writeInt(r02);
        return;
    L5:
        r02 = r1.getAbsoluteItemOffset(r0);
        goto L7
    }
}
