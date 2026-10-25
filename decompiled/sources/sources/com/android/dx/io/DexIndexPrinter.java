package com.android.dx.io;

import com.android.dex.ClassDef;
import com.android.dex.Dex;
import com.android.dex.FieldId;
import com.android.dex.MethodId;
import com.android.dex.ProtoId;
import com.android.dex.TableOfContents;
import java.io.File;
import java.util.Iterator;

/* loaded from: classes4.dex */
public final class DexIndexPrinter {
    private final Dex dex;
    private final TableOfContents tableOfContents;

    public DexIndexPrinter(File r2) {
        Dex r0 = new Dex(r2);
        this.dex = r0;
        this.tableOfContents = r0.getTableOfContents();
    }

    public static void main(String[] r3) {
        DexIndexPrinter r0 = new DexIndexPrinter(new File(r3[0]));
        r0.printMap();
        r0.printStrings();
        r0.printTypeIds();
        r0.printProtoIds();
        r0.printFieldIds();
        r0.printMethodIds();
        r0.printTypeLists();
        r0.printClassDefs();
    }

    private void printClassDefs() {
        Iterator<ClassDef> r5 = this.dex.classDefs().iterator();
        int r0 = 0;
    L4:
        if (r5.hasNext() == false) goto L6;
        ClassDef r1 = r5.next();
        System.out.println("class def " + r0 + ": " + r1);
        r0 = r0 + 1;
        goto L4
    }

    private void printFieldIds() {
        Iterator<FieldId> r5 = this.dex.fieldIds().iterator();
        int r0 = 0;
    L4:
        if (r5.hasNext() == false) goto L6;
        FieldId r1 = r5.next();
        System.out.println("field " + r0 + ": " + r1);
        r0 = r0 + 1;
        goto L4
    }

    private void printMap() {
        TableOfContents.Section[] r6 = this.tableOfContents.sections;
        int r0 = r6.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        TableOfContents.Section r2 = r6[r1];
        if (r2.off == (-1)) goto L7;
        System.out.println("section " + Integer.toHexString(r2.type) + " off=" + Integer.toHexString(r2.off) + " size=" + Integer.toHexString(r2.size) + " byteCount=" + Integer.toHexString(r2.byteCount));
    L7:
        r1 = r1 + 1;
        goto L3
    }

    private void printMethodIds() {
        Iterator<MethodId> r5 = this.dex.methodIds().iterator();
        int r0 = 0;
    L4:
        if (r5.hasNext() == false) goto L6;
        MethodId r1 = r5.next();
        System.out.println("methodId " + r0 + ": " + r1);
        r0 = r0 + 1;
        goto L4
    }

    private void printProtoIds() {
        Iterator<ProtoId> r5 = this.dex.protoIds().iterator();
        int r0 = 0;
    L4:
        if (r5.hasNext() == false) goto L6;
        ProtoId r1 = r5.next();
        System.out.println("proto " + r0 + ": " + r1);
        r0 = r0 + 1;
        goto L4
    }

    private void printStrings() {
        Iterator<String> r5 = this.dex.strings().iterator();
        int r0 = 0;
    L4:
        if (r5.hasNext() == false) goto L6;
        String r1 = r5.next();
        System.out.println("string " + r0 + ": " + r1);
        r0 = r0 + 1;
        goto L4
    }

    private void printTypeIds() {
        Iterator<Integer> r0 = this.dex.typeIds().iterator();
        int r1 = 0;
    L4:
        if (r0.hasNext() == false) goto L6;
        Integer r2 = r0.next();
        System.out.println("type " + r1 + ": " + this.dex.strings().get(r2.intValue()));
        r1 = r1 + 1;
        goto L4
    }

    private void printTypeLists() {
        int r0 = this.tableOfContents.typeLists.off;
        if (r0 != (-1)) goto L6;
        System.out.println("No type lists");
        return;
    L6:
        Dex.Section r02 = this.dex.open(r0);
        int r2 = 0;
    L8:
        if (r2 >= this.tableOfContents.typeLists.size) goto L16;
        int r3 = r02.readInt();
        System.out.print("Type list i=" + r2 + ", size=" + r3 + ", elements=");
        int r4 = 0;
    L10:
        if (r4 >= r3) goto L13;
        System.out.print(" " + this.dex.typeNames().get(r02.readShort()));
        r4 = r4 + 1;
        goto L10
    L13:
        if ((r3 % 2) != 1) goto L15;
        r02.readShort();
    L15:
        System.out.println();
        r2 = r2 + 1;
        goto L8
    }
}
