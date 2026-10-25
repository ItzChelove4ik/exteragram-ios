package org.mvel2.asm;

import com.android.dx.cf.attrib.AttBootstrapMethods;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class SymbolTable {
    private int bootstrapMethodCount;
    private ByteVector bootstrapMethods;
    private String className;
    final ClassWriter classWriter;
    private ByteVector constantPool;
    private int constantPoolCount;
    private Entry[] entries;
    private int entryCount;
    private int majorVersion;
    private final ClassReader sourceClassReader;
    private int typeCount;
    private Entry[] typeTable;

    public static class Entry extends Symbol {
        final int hashCode;
        Entry next;

        public Entry(int r9, int r10, long r11, int r13) {
            super(r9, r10, null, null, null, r11);
            this.hashCode = r13;
        }

        public Entry(int r9, int r10, String r11, int r12) {
            super(r9, r10, null, null, r11, 0);
            this.hashCode = r12;
        }

        public Entry(int r9, int r10, String r11, long r12, int r14) {
            super(r9, r10, null, null, r11, r12);
            this.hashCode = r14;
        }

        public Entry(int r9, int r10, String r11, String r12, int r13) {
            super(r9, r10, null, r11, r12, 0);
            this.hashCode = r13;
        }

        public Entry(int r1, int r2, String r3, String r4, String r5, long r6, int r8) {
            super(r1, r2, r3, r4, r5, r6);
            this.hashCode = r8;
        }
    }

    public SymbolTable(ClassWriter r1) {
        this.classWriter = r1;
        this.sourceClassReader = null;
        this.entries = new Entry[256];
        this.constantPoolCount = 1;
        this.constantPool = new ByteVector();
    }

    public SymbolTable(ClassWriter r12, ClassReader r13) {
        this.classWriter = r12;
        this.sourceClassReader = r13;
        byte[] r122 = r13.classFileBuffer;
        int r1 = r13.getItem(1) - 1;
        int r2 = r13.header - r1;
        this.constantPoolCount = r13.getItemCount();
        ByteVector r3 = new ByteVector(r2);
        this.constantPool = r3;
        r3.putByteArray(r122, r1, r2);
        this.entries = new Entry[this.constantPoolCount * 2];
        char[] r14 = new char[r13.getMaxStringLength()];
        boolean r32 = false;
        int r5 = 1;
    L4:
        if (r5 >= this.constantPoolCount) goto L26;
        int r4 = r13.getItem(r5);
        byte r6 = r122[r4 - 1];
        switch(r6) {
            case 1: goto L17;
            case 2: goto L7;
            case 3: goto L16;
            case 4: goto L16;
            case 5: goto L15;
            case 6: goto L15;
            case 7: goto L14;
            case 8: goto L14;
            case 9: goto L13;
            case 10: goto L13;
            case 11: goto L13;
            case 12: goto L12;
            case 13: goto L7;
            case 14: goto L7;
            case 15: goto L10;
            case 16: goto L14;
            case 17: goto L9;
            case 18: goto L9;
            case 19: goto L14;
            case 20: goto L14;
            default: goto L7;
        };
    L9:
        int r33 = r13.getItem(r13.readUnsignedShort(r4 + 2));
        String r7 = r13.readUTF8(r33, r14);
        String r8 = r13.readUTF8(r33 + 2, r14);
        int r9 = r13.readUnsignedShort(r4);
        int r62 = r5;
        SymbolTable r42 = this;
        r42.addConstantDynamicOrInvokeDynamicReference(r6, r62, r7, r8, r9);
        r6 = r6;
        r5 = r62;
        r32 = true;
        SymbolTable this = r42;
    L19:
        if (r6 != 5) goto L21;
    L24:
        int r43 = 2;
    L25:
        r5 = r5 + r43;
        goto L4
    L21:
        if (r6 == 6) goto L24;
        r43 = 1;
        goto L25
    L10:
        byte r10 = r6;
        int r63 = r13.getItem(r13.readUnsignedShort(r4 + 1));
        int r72 = r13.getItem(r13.readUnsignedShort(r63 + 2));
        this.addConstantMethodHandle(r5, r13.readByte(r4), r13.readClass(r63, r14), r13.readUTF8(r72, r14), r13.readUTF8(r72 + 2, r14));
    L11:
        r6 = r10;
        goto L19
    L12:
        r10 = r6;
        this.addConstantNameAndType(r5, r13.readUTF8(r4, r14), r13.readUTF8(r4 + 2, r14));
        goto L11
    L13:
        int r64 = r13.getItem(r13.readUnsignedShort(r4 + 2));
        String r73 = r13.readClass(r4, r14);
        String r82 = r13.readUTF8(r64, r14);
        String r92 = r13.readUTF8(r64 + 2, r14);
        r6 = r6;
        this.addConstantMemberReference(r5, r6, r73, r82, r92);
        goto L19
    L14:
        this.addConstantUtf8Reference(r5, r6, r13.readUTF8(r4, r14));
        goto L19
    L15:
        this.addConstantLongOrDouble(r5, r6, r13.readLong(r4));
        goto L19
    L16:
        this.addConstantIntegerOrFloat(r5, r6, r13.readInt(r4));
        goto L19
    L17:
        this.addConstantUtf8(r5, r13.readUtf(r5, r14));
        goto L19
    L7:
        Segment$$ExternalSyntheticBUOutline0.m();
        throw null;
    L26:
        if (r32 == false) goto L33;
        this.copyBootstrapMethods(r13, r14);
        return;
    }

    private void add(Entry r3) {
        this.entryCount++;
        int r0 = r3.hashCode;
        Entry[] r2 = this.entries;
        int r02 = r0 % r2.length;
        r3.next = r2[r02];
        r2[r02] = r3;
    }

    private Symbol addBootstrapMethod(int r9, int r10, int r11) {
        byte[] r0 = this.bootstrapMethods.data;
        Entry r1 = get(r11);
    L3:
        if (r1 == null) goto L17;
        if (r1.tag != 64) goto L16;
        if (r1.hashCode != r11) goto L16;
        int r2 = (int) r1.data;
        int r3 = 0;
    L9:
        if (r3 >= r10) goto L14;
        if (r0[r9 + r3] != r0[r2 + r3]) goto L16;
        r3 = r3 + 1;
        goto L9
    L14:
        this.bootstrapMethods.length = r9;
        return r1;
    L16:
        r1 = r1.next;
        goto L3
    L17:
        int r32 = this.bootstrapMethodCount;
        this.bootstrapMethodCount = r32 + 1;
        return put(new Entry(r32, 64, r9, r11));
    }

    private Symbol addConstantDynamicOrInvokeDynamicReference(int r10, String r11, String r12, int r13) {
        int r8 = hash(r10, r11, r12, r13);
        Entry r0 = get(r8);
    L3:
        if (r0 == null) goto L16;
        if (r0.tag != r10) goto L15;
        if (r0.hashCode != r8) goto L15;
        if (r0.data != r13) goto L15;
        if (r0.name.equals(r11) == false) goto L15;
        if (r0.value.equals(r12) == false) goto L15;
        return r0;
    L15:
        r0 = r0.next;
        goto L3
    L16:
        this.constantPool.put122(r10, r13, addConstantNameAndType(r11, r12));
        int r1 = this.constantPoolCount;
        this.constantPoolCount = r1 + 1;
        return put(new Entry(r1, r10, null, r11, r12, r13, r8));
    }

    private void addConstantDynamicOrInvokeDynamicReference(int r10, int r11, String r12, String r13, int r14) {
        add(new Entry(r11, r10, null, r12, r13, r14, hash(r10, r12, r13, r14)));
    }

    private Symbol addConstantIntegerOrFloat(int r7, int r8) {
        int r5 = hash(r7, r8);
        Entry r0 = get(r5);
    L3:
        if (r0 == null) goto L12;
        if (r0.tag != r7) goto L11;
        if (r0.hashCode != r5) goto L11;
        if (r0.data != r8) goto L11;
        return r0;
    L11:
        r0 = r0.next;
        goto L3
    L12:
        this.constantPool.putByte(r7).putInt(r8);
        int r1 = this.constantPoolCount;
        this.constantPoolCount = r1 + 1;
        return put(new Entry(r1, r7, r8, r5));
    }

    private void addConstantIntegerOrFloat(int r7, int r8, int r9) {
        add(new Entry(r7, r8, r9, hash(r8, r9)));
    }

    private Symbol addConstantLongOrDouble(int r7, long r8) {
        int r5 = hash(r7, r8);
        Entry r0 = get(r5);
    L3:
        if (r0 == null) goto L12;
        if (r0.tag != r7) goto L11;
        if (r0.hashCode != r5) goto L11;
        if (r0.data != r8) goto L11;
        return r0;
    L11:
        r0 = r0.next;
        goto L3
    L12:
        int r1 = this.constantPoolCount;
        this.constantPool.putByte(r7).putLong(r8);
        this.constantPoolCount += 2;
        return put(new Entry(r1, r7, r8, r5));
    }

    private void addConstantLongOrDouble(int r7, int r8, long r9) {
        add(new Entry(r7, r8, r9, hash(r8, r9)));
    }

    private Entry addConstantMemberReference(int r10, String r11, String r12, String r13) {
        int r8 = hash(r10, r11, r12, r13);
        Entry r0 = get(r8);
    L3:
        if (r0 == null) goto L16;
        if (r0.tag != r10) goto L15;
        if (r0.hashCode != r8) goto L15;
        if (r0.owner.equals(r11) == false) goto L15;
        if (r0.name.equals(r12) == false) goto L15;
        if (r0.value.equals(r13) == false) goto L15;
        return r0;
    L15:
        r0 = r0.next;
        goto L3
    L16:
        this.constantPool.put122(r10, addConstantClass(r11).index, addConstantNameAndType(r12, r13));
        int r1 = this.constantPoolCount;
        this.constantPoolCount = r1 + 1;
        return put(new Entry(r1, r10, r11, r12, r13, 0, r8));
    }

    private void addConstantMemberReference(int r10, int r11, String r12, String r13, String r14) {
        add(new Entry(r10, r11, r12, r13, r14, 0, hash(r11, r12, r13, r14)));
    }

    private void addConstantMethodHandle(int r11, int r12, String r13, String r14, String r15) {
        add(new Entry(r11, 15, r13, r14, r15, r12, hash(15, r13, r14, r15, r12)));
    }

    private void addConstantNameAndType(int r7, String r8, String r9) {
        add(new Entry(r7, 12, r8, r9, hash(12, r8, r9)));
    }

    private void addConstantUtf8(int r4, String r5) {
        add(new Entry(r4, 1, r5, hash(1, r5)));
    }

    private Symbol addConstantUtf8Reference(int r5, String r6) {
        int r0 = hash(r5, r6);
        Entry r1 = get(r0);
    L3:
        if (r1 == null) goto L12;
        if (r1.tag != r5) goto L11;
        if (r1.hashCode != r0) goto L11;
        if (r1.value.equals(r6) == false) goto L11;
        return r1;
    L11:
        r1 = r1.next;
        goto L3
    L12:
        this.constantPool.put12(r5, addConstantUtf8(r6));
        int r2 = this.constantPoolCount;
        this.constantPoolCount = r2 + 1;
        return put(new Entry(r2, r5, r6, r0));
    }

    private void addConstantUtf8Reference(int r3, int r4, String r5) {
        add(new Entry(r3, r4, r5, hash(r4, r5)));
    }

    private int addTypeInternal(Entry r5) {
        if (this.typeTable != null) goto L5;
        this.typeTable = new Entry[16];
    L5:
        int r0 = this.typeCount;
        Entry[] r1 = this.typeTable;
        if (r0 != r1.length) goto L8;
        Entry[] r02 = new Entry[r1.length * 2];
        System.arraycopy(r1, 0, r02, 0, r1.length);
        this.typeTable = r02;
    L8:
        Entry[] r03 = this.typeTable;
        int r12 = this.typeCount;
        this.typeCount = r12 + 1;
        r03[r12] = r5;
        return put(r5).index;
    }

    private void copyBootstrapMethods(ClassReader r10, char[] r11) {
        byte[] r0 = r10.classFileBuffer;
        int r1 = r10.getFirstAttributeOffset();
        int r2 = r10.readUnsignedShort(r1 - 2);
    L3:
        if (r2 <= 0) goto L9;
        if (AttBootstrapMethods.ATTRIBUTE_NAME.equals(r10.readUTF8(r1, r11)) == true) goto L6;
        r1 = r1 + (r10.readInt(r1 + 2) + 6);
        r2 = r2 - 1;
        goto L3
    L6:
        this.bootstrapMethodCount = r10.readUnsignedShort(r1 + 6);
    L9:
        if (this.bootstrapMethodCount <= 0) goto L18;
        int r22 = r1 + 8;
        int r12 = r10.readInt(r1 + 2) - 2;
        ByteVector r3 = new ByteVector(r12);
        this.bootstrapMethods = r3;
        r3.putByteArray(r0, r22, r12);
        int r4 = 0;
        int r02 = r22;
    L12:
        if (r4 >= this.bootstrapMethodCount) goto L23;
        int r13 = r02 - r22;
        int r32 = r10.readUnsignedShort(r02);
        int r5 = r10.readUnsignedShort(r02 + 2);
        r02 = r02 + 4;
        int r33 = r10.readConst(r32, r11).hashCode();
    L14:
        int r6 = r5 - 1;
        if (r5 <= 0) goto L17;
        int r52 = r10.readUnsignedShort(r02);
        r02 = r02 + 2;
        r33 = r33 ^ r10.readConst(r52, r11).hashCode();
        r5 = r6;
        goto L14
    L17:
        add(new Entry(r4, 64, r13, r33 & Integer.MAX_VALUE));
        r4 = r4 + 1;
        goto L12
    L23:
        return;
    }

    private Entry get(int r2) {
        Entry[] r1 = this.entries;
        return r1[r2 % r1.length];
    }

    private static int hash(int r1, int r2) {
        return (r1 + r2) & Integer.MAX_VALUE;
    }

    private static int hash(int r1, long r2) {
        return ((r1 + ((int) r2)) + ((int) (r2 >>> 32))) & Integer.MAX_VALUE;
    }

    private static int hash(int r0, String r1) {
        return (r0 + r1.hashCode()) & Integer.MAX_VALUE;
    }

    private static int hash(int r0, String r1, int r2) {
        return ((r0 + r1.hashCode()) + r2) & Integer.MAX_VALUE;
    }

    private static int hash(int r0, String r1, String r2) {
        return (r0 + (r1.hashCode() * r2.hashCode())) & Integer.MAX_VALUE;
    }

    private static int hash(int r0, String r1, String r2, int r3) {
        return (r0 + ((r1.hashCode() * r2.hashCode()) * (r3 + 1))) & Integer.MAX_VALUE;
    }

    private static int hash(int r0, String r1, String r2, String r3) {
        return (r0 + ((r1.hashCode() * r2.hashCode()) * r3.hashCode())) & Integer.MAX_VALUE;
    }

    private static int hash(int r0, String r1, String r2, String r3, int r4) {
        return (r0 + (((r1.hashCode() * r2.hashCode()) * r3.hashCode()) * r4)) & Integer.MAX_VALUE;
    }

    private Entry put(Entry r8) {
        int r0 = this.entryCount;
        Entry[] r1 = this.entries;
        if (r0 <= ((r1.length * 3) / 4)) goto L11;
        int r02 = r1.length;
        int r12 = (r02 * 2) + 1;
        Entry[] r2 = new Entry[r12];
        int r03 = r02 - 1;
    L5:
        if (r03 < 0) goto L10;
        Entry r3 = this.entries[r03];
    L7:
        if (r3 == null) goto L9;
        int r4 = r3.hashCode % r12;
        Entry r5 = r3.next;
        r3.next = r2[r4];
        r2[r4] = r3;
        r3 = r5;
        goto L7
    L9:
        r03 = r03 - 1;
        goto L5
    L10:
        this.entries = r2;
    L11:
        this.entryCount++;
        int r04 = r8.hashCode;
        Entry[] r7 = this.entries;
        int r05 = r04 % r7.length;
        r8.next = r7[r05];
        r7[r05] = r8;
        return r8;
    }

    public Symbol addBootstrapMethod(Handle r12, Object... r13) {
        ByteVector r0 = this.bootstrapMethods;
        if (r0 != null) goto L5;
        r0 = new ByteVector();
        this.bootstrapMethods = r0;
    L5:
        int r1 = r13.length;
        int[] r2 = new int[r1];
        int r3 = 0;
        int r4 = 0;
    L6:
        if (r4 >= r1) goto L8;
        r2[r4] = addConstant(r13[r4]).index;
        r4 = r4 + 1;
        goto L6
    L8:
        int r42 = r0.length;
        r0.putShort(addConstantMethodHandle(r12.getTag(), r12.getOwner(), r12.getName(), r12.getDesc(), r12.isInterface()).index);
        r0.putShort(r1);
        int r11 = 0;
    L9:
        if (r11 >= r1) goto L11;
        r0.putShort(r2[r11]);
        r11 = r11 + 1;
        goto L9
    L11:
        int r112 = r0.length - r42;
        int r122 = r12.hashCode();
        int r02 = r13.length;
    L12:
        if (r3 >= r02) goto L15;
        r122 = r122 ^ r13[r3].hashCode();
        r3 = r3 + 1;
        goto L12
    L15:
        return addBootstrapMethod(r42, r112, r122 & Integer.MAX_VALUE);
    }

    public Symbol addConstant(Object r7) {
        if ((r7 instanceof Integer) == false) goto L7;
        return addConstantInteger(((Integer) r7).intValue());
    L7:
        if ((r7 instanceof Byte) == false) goto L11;
        return addConstantInteger(((Byte) r7).intValue());
    L11:
        if ((r7 instanceof Character) == false) goto L15;
        return addConstantInteger(((Character) r7).charValue());
    L15:
        if ((r7 instanceof Short) == false) goto L19;
        return addConstantInteger(((Short) r7).intValue());
    L19:
        if ((r7 instanceof Boolean) == false) goto L23;
        return addConstantInteger(((Boolean) r7).booleanValue() ? 1 : 0);
    L23:
        if ((r7 instanceof Float) == false) goto L27;
        return addConstantFloat(((Float) r7).floatValue());
    L27:
        if ((r7 instanceof Long) == false) goto L31;
        return addConstantLong(((Long) r7).longValue());
    L31:
        if ((r7 instanceof Double) == false) goto L35;
        return addConstantDouble(((Double) r7).doubleValue());
    L35:
        if ((r7 instanceof String) == false) goto L39;
        return addConstantString((String) r7);
    L39:
        if ((r7 instanceof Type) == false) goto L51;
        Type r72 = (Type) r7;
        int r0 = r72.getSort();
        if (r0 != 10) goto L45;
        return addConstantClass(r72.getInternalName());
    L45:
        if (r0 != 11) goto L49;
        return addConstantMethodType(r72.getDescriptor());
    L49:
        return addConstantClass(r72.getDescriptor());
    L51:
        if ((r7 instanceof Handle) == false) goto L55;
        Handle r73 = (Handle) r7;
        return addConstantMethodHandle(r73.getTag(), r73.getOwner(), r73.getName(), r73.getDesc(), r73.isInterface());
    L55:
        if ((r7 instanceof ConstantDynamic) == false) goto L58;
        ConstantDynamic r74 = (ConstantDynamic) r7;
        return addConstantDynamic(r74.getName(), r74.getDescriptor(), r74.getBootstrapMethod(), r74.getBootstrapMethodArgumentsUnsafe());
    L58:
        Native$$ExternalSyntheticBUOutline5.m("value ", r7);
        return null;
    }

    public Symbol addConstantClass(String r2) {
        return addConstantUtf8Reference(7, r2);
    }

    public Symbol addConstantDouble(double r2) {
        return addConstantLongOrDouble(6, Double.doubleToRawLongBits(r2));
    }

    public Symbol addConstantDynamic(String r1, String r2, Handle r3, Object... r4) {
        return addConstantDynamicOrInvokeDynamicReference(17, r1, r2, addBootstrapMethod(r3, r4).index);
    }

    public Symbol addConstantFieldref(String r2, String r3, String r4) {
        return addConstantMemberReference(9, r2, r3, r4);
    }

    public Symbol addConstantFloat(float r2) {
        return addConstantIntegerOrFloat(4, Float.floatToRawIntBits(r2));
    }

    public Symbol addConstantInteger(int r2) {
        return addConstantIntegerOrFloat(3, r2);
    }

    public Symbol addConstantInvokeDynamic(String r1, String r2, Handle r3, Object... r4) {
        return addConstantDynamicOrInvokeDynamicReference(18, r1, r2, addBootstrapMethod(r3, r4).index);
    }

    public Symbol addConstantLong(long r2) {
        return addConstantLongOrDouble(5, r2);
    }

    public Symbol addConstantMethodHandle(int r11, String r12, String r13, String r14, boolean r15) {
        int r9 = hash(15, r12, r13, r14, r11);
        Entry r1 = get(r9);
    L3:
        if (r1 == null) goto L18;
        if (r1.tag != 15) goto L17;
        if (r1.hashCode != r9) goto L17;
        if (r1.data != r11) goto L17;
        if (r1.owner.equals(r12) == false) goto L17;
        if (r1.name.equals(r13) == false) goto L17;
        if (r1.value.equals(r14) == false) goto L17;
        return r1;
    L17:
        r1 = r1.next;
        goto L3
    L18:
        ByteVector r16 = this.constantPool;
        if (r11 > 4) goto L21;
        r16.put112(15, r11, addConstantFieldref(r12, r13, r14).index);
    L22:
        int r2 = this.constantPoolCount;
        this.constantPoolCount = r2 + 1;
        return put(new Entry(r2, 15, r12, r13, r14, r11, r9));
    L21:
        r16.put112(15, r11, addConstantMethodref(r12, r13, r14, r15).index);
        goto L22
    }

    public Symbol addConstantMethodType(String r2) {
        return addConstantUtf8Reference(16, r2);
    }

    public Symbol addConstantMethodref(String r1, String r2, String r3, boolean r4) {
        if (r4 == false) goto L4;
        int r42 = 11;
    L6:
        return addConstantMemberReference(r42, r1, r2, r3);
    L4:
        r42 = 10;
        goto L6
    }

    public Symbol addConstantModule(String r2) {
        return addConstantUtf8Reference(19, r2);
    }

    public int addConstantNameAndType(String r8, String r9) {
        int r6 = hash(12, r8, r9);
        Entry r1 = get(r6);
    L3:
        if (r1 == null) goto L15;
        if (r1.tag != 12) goto L14;
        if (r1.hashCode != r6) goto L14;
        if (r1.name.equals(r8) == false) goto L14;
        if (r1.value.equals(r9) == false) goto L14;
        return r1.index;
    L14:
        r1 = r1.next;
        goto L3
    L15:
        this.constantPool.put122(12, addConstantUtf8(r8), addConstantUtf8(r9));
        int r2 = this.constantPoolCount;
        this.constantPoolCount = r2 + 1;
        return put(new Entry(r2, 12, r8, r9, r6)).index;
    }

    public Symbol addConstantPackage(String r2) {
        return addConstantUtf8Reference(20, r2);
    }

    public Symbol addConstantString(String r2) {
        return addConstantUtf8Reference(8, r2);
    }

    public int addConstantUtf8(String r6) {
        int r1 = hash(1, r6);
        Entry r2 = get(r1);
    L3:
        if (r2 == null) goto L13;
        if (r2.tag != 1) goto L12;
        if (r2.hashCode != r1) goto L12;
        if (r2.value.equals(r6) == false) goto L12;
        return r2.index;
    L12:
        r2 = r2.next;
        goto L3
    L13:
        this.constantPool.putByte(1).putUTF8(r6);
        int r3 = this.constantPoolCount;
        this.constantPoolCount = r3 + 1;
        return put(new Entry(r3, 1, r6, r1)).index;
    }

    public int addMergedType(int r9, int r10) {
        if (r9 >= r10) goto L6;
        long r1 = r9;
        long r3 = r10;
    L5:
        long r0 = r1 | (r3 << 32);
        int r7 = hash(130, r9 + r10);
        Entry r02 = get(r7);
    L8:
        if (r02 == null) goto L18;
        if (r02.tag != 130) goto L17;
        if (r02.hashCode != r7) goto L17;
        if (r02.data != r0) goto L17;
        return r02.info;
    L17:
        r02 = r02.next;
        goto L8
    L18:
        Entry[] r03 = this.typeTable;
        int r92 = addType(this.classWriter.getCommonSuperClass(r03[r9].value, r03[r10].value));
        put(new Entry(this.typeCount, 130, r0, r7)).info = r92;
        return r92;
    L6:
        r1 = r10;
        r3 = r9;
        goto L5
    }

    public int addType(String r5) {
        int r1 = hash(128, r5);
        Entry r2 = get(r1);
    L3:
        if (r2 == null) goto L14;
        if (r2.tag != 128) goto L12;
        if (r2.hashCode != r1) goto L12;
        if (r2.value.equals(r5) == false) goto L12;
        return r2.index;
    L12:
        r2 = r2.next;
        goto L3
    L14:
        return addTypeInternal(new Entry(this.typeCount, 128, r5, r1));
    }

    public int addUninitializedType(String r9, int r10) {
        int r7 = hash(129, r9, r10);
        Entry r1 = get(r7);
    L3:
        if (r1 == null) goto L16;
        if (r1.tag != 129) goto L14;
        if (r1.hashCode != r7) goto L14;
        if (r1.data != r10) goto L14;
        if (r1.value.equals(r9) == false) goto L14;
        return r1.index;
    L14:
        r1 = r1.next;
        goto L3
    L16:
        return addTypeInternal(new Entry(this.typeCount, 129, r9, r10, r7));
    }

    public int computeBootstrapMethodsSize() {
        if (this.bootstrapMethods == null) goto L6;
        addConstantUtf8(AttBootstrapMethods.ATTRIBUTE_NAME);
        return this.bootstrapMethods.length + 8;
    L6:
        return 0;
    }

    public String getClassName() {
        return this.className;
    }

    public int getConstantPoolCount() {
        return this.constantPoolCount;
    }

    public int getConstantPoolLength() {
        return this.constantPool.length;
    }

    public int getMajorVersion() {
        return this.majorVersion;
    }

    public ClassReader getSource() {
        return this.sourceClassReader;
    }

    public Symbol getType(int r1) {
        return this.typeTable[r1];
    }

    public void putBootstrapMethods(ByteVector r3) {
        if (this.bootstrapMethods == null) goto L6;
        ByteVector r32 = r3.putShort(addConstantUtf8(AttBootstrapMethods.ATTRIBUTE_NAME)).putInt(this.bootstrapMethods.length + 2).putShort(this.bootstrapMethodCount);
        ByteVector r2 = this.bootstrapMethods;
        r32.putByteArray(r2.data, 0, r2.length);
        return;
    }

    public void putConstantPool(ByteVector r3) {
        ByteVector r32 = r3.putShort(this.constantPoolCount);
        ByteVector r2 = this.constantPool;
        r32.putByteArray(r2.data, 0, r2.length);
    }

    public int setMajorVersionAndClassName(int r1, String r2) {
        this.majorVersion = r1;
        this.className = r2;
        return addConstantClass(r2).index;
    }
}
