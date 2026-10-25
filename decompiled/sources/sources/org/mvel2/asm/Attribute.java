package org.mvel2.asm;

import com.android.dx.cf.attrib.AttDeprecated;
import com.android.dx.cf.attrib.AttSignature;
import com.android.dx.cf.attrib.AttSynthetic;

/* loaded from: classes5.dex */
public class Attribute {
    private byte[] content;
    Attribute nextAttribute;
    public final String type;

    public static final class Set {
        private static final int SIZE_INCREMENT = 6;
        private Attribute[] data;
        private int size;

        public Set() {
            this.data = new Attribute[6];
        }

        private void add(Attribute r5) {
            int r0 = this.size;
            Attribute[] r1 = this.data;
            if (r0 < r1.length) goto L5;
            Attribute[] r2 = new Attribute[r1.length + 6];
            System.arraycopy(r1, 0, r2, 0, r0);
            this.data = r2;
        L5:
            Attribute[] r02 = this.data;
            int r12 = this.size;
            this.size = r12 + 1;
            r02[r12] = r5;
        }

        private boolean contains(Attribute r5) {
            int r1 = 0;
        L4:
            if (r1 >= this.size) goto L10;
            if (this.data[r1].type.equals(r5.type) == true) goto L7;
            r1 = r1 + 1;
            goto L4
        L7:
            return true;
        L10:
            return false;
        }

        public void addAttributes(Attribute r2) {
        L2:
            if (r2 == null) goto L7;
            if (contains(r2) == true) goto L6;
            add(r2);
        L6:
            r2 = r2.nextAttribute;
            goto L2
        }

        public Attribute[] toArray() {
            int r0 = this.size;
            Attribute[] r1 = new Attribute[r0];
            System.arraycopy(this.data, 0, r1, 0, r0);
            return r1;
        }
    }

    public Attribute(String r1) {
        this.type = r1;
    }

    public static int computeAttributesSize(SymbolTable r3, int r4, int r5) {
        if ((r4 & 4096) != 0) goto L5;
    L7:
        int r0 = 0;
    L8:
        if (r5 == 0) goto L11;
        r3.addConstantUtf8(AttSignature.ATTRIBUTE_NAME);
        r0 = r0 + 8;
    L11:
        if ((r4 & 131072) == 0) goto L14;
        r3.addConstantUtf8(AttDeprecated.ATTRIBUTE_NAME);
        return r0 + 6;
    L14:
        return r0;
    L5:
        if (r3.getMajorVersion() >= 49) goto L7;
        r3.addConstantUtf8(AttSynthetic.ATTRIBUTE_NAME);
        r0 = 6;
        goto L8
    }

    public static void putAttributes(SymbolTable r3, int r4, int r5, ByteVector r6) {
        if ((r4 & 4096) != 0) goto L5;
    L7:
        if (r5 == 0) goto L10;
        r6.putShort(r3.addConstantUtf8(AttSignature.ATTRIBUTE_NAME)).putInt(2).putShort(r5);
    L10:
        if ((r4 & 131072) == 0) goto L13;
        r6.putShort(r3.addConstantUtf8(AttDeprecated.ATTRIBUTE_NAME)).putInt(0);
        return;
    L13:
        return;
    L5:
        if (r3.getMajorVersion() >= 49) goto L7;
        r6.putShort(r3.addConstantUtf8(AttSynthetic.ATTRIBUTE_NAME)).putInt(0);
        goto L7
    }

    public final int computeAttributesSize(SymbolTable r7) {
        return computeAttributesSize(r7, null, 0, -1, -1);
    }

    public final int computeAttributesSize(SymbolTable r8, byte[] r9, int r10, int r11, int r12) {
        ClassWriter r1 = r8.classWriter;
        Attribute r0 = this;
        int r7 = 0;
    L3:
        if (r0 == null) goto L5;
        r8.addConstantUtf8(r0.type);
        byte[] r2 = r9;
        r7 = r7 + (r0.write(r1, r2, r10, r11, r12).length + 6);
        r0 = r0.nextAttribute;
        r9 = r2;
        goto L3
    L5:
        return r7;
    }

    public final int getAttributeCount() {
        int r0 = 0;
    L3:
        if (this == null) goto L5;
        r0 = r0 + 1;
        Attribute this = this.nextAttribute;
        goto L3
    L5:
        return r0;
    }

    public Label[] getLabels() {
        return new Label[0];
    }

    public boolean isCodeAttribute() {
        return false;
    }

    public boolean isUnknown() {
        return true;
    }

    public final void putAttributes(SymbolTable r8, ByteVector r9) {
        putAttributes(r8, null, 0, -1, -1, r9);
    }

    public final void putAttributes(SymbolTable r7, byte[] r8, int r9, int r10, int r11, ByteVector r12) {
        ClassWriter r1 = r7.classWriter;
        Attribute r0 = this;
    L3:
        if (r0 == null) goto L5;
        byte[] r2 = r8;
        int r3 = r9;
        ByteVector r6 = r0.write(r1, r2, r3, r10, r11);
        r12.putShort(r7.addConstantUtf8(r0.type)).putInt(r6.length);
        r12.putByteArray(r6.data, 0, r6.length);
        r0 = r0.nextAttribute;
        r8 = r2;
        r9 = r3;
        goto L3
    }

    public Attribute read(ClassReader r1, int r2, int r3, char[] r4, int r5, Label[] r6) {
        Attribute r42 = new Attribute(this.type);
        byte[] r0 = new byte[r3];
        r42.content = r0;
        System.arraycopy(r1.classFileBuffer, r2, r0, 0, r3);
        return r42;
    }

    public ByteVector write(ClassWriter r1, byte[] r2, int r3, int r4, int r5) {
        return new ByteVector(this.content);
    }
}
