package com.android.dx.dex.file;

import com.android.dx.rop.cst.CstString;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;

/* loaded from: classes.dex */
public final class HeaderItem extends IndexedItem {
    public HeaderItem() {
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r1) {
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_HEADER_ITEM;
    }

    @Override // com.android.dx.dex.file.Item
    public int writeSize() {
        return 112;
    }

    @Override // com.android.dx.dex.file.Item
    public void writeTo(DexFile r10, AnnotatedOutput r11) {
        int r9 = r10.getMap().getFileOffset();
        Section r0 = r10.getFirstDataSection();
        Section r1 = r10.getLastDataSection();
        int r02 = r0.getFileOffset();
        int r2 = (r1.getFileOffset() + r1.writeSize()) - r02;
        String r12 = r10.getDexOptions().getMagic();
        if (r11.annotates() == false) goto L5;
        r11.annotate(8, "magic: " + new CstString(r12).toQuoted());
        r11.annotate(4, "checksum");
        r11.annotate(20, "signature");
        r11.annotate(4, "file_size:       " + Hex.u4(r10.getFileSize()));
        r11.annotate(4, "header_size:     " + Hex.u4(112));
        r11.annotate(4, "endian_tag:      " + Hex.u4(305419896));
        r11.annotate(4, "link_size:       0");
        r11.annotate(4, "link_off:        0");
        r11.annotate(4, "map_off:         " + Hex.u4(r9));
    L5:
        int r3 = 0;
    L6:
        if (r3 >= 8) goto L8;
        r11.writeByte(r12.charAt(r3));
        r3 = r3 + 1;
        goto L6
    L8:
        r11.writeZeroes(24);
        r11.writeInt(r10.getFileSize());
        r11.writeInt(112);
        r11.writeInt(305419896);
        r11.writeZeroes(8);
        r11.writeInt(r9);
        r10.getStringIds().writeHeaderPart(r11);
        r10.getTypeIds().writeHeaderPart(r11);
        r10.getProtoIds().writeHeaderPart(r11);
        r10.getFieldIds().writeHeaderPart(r11);
        r10.getMethodIds().writeHeaderPart(r11);
        r10.getClassDefs().writeHeaderPart(r11);
        if (r11.annotates() == false) goto L11;
        r11.annotate(4, "data_size:       " + Hex.u4(r2));
        r11.annotate(4, "data_off:        " + Hex.u4(r02));
    L11:
        r11.writeInt(r2);
        r11.writeInt(r02);
    }
}
