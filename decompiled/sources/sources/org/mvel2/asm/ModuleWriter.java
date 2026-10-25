package org.mvel2.asm;

/* loaded from: classes5.dex */
final class ModuleWriter extends ModuleVisitor {
    private final ByteVector exports;
    private int exportsCount;
    private int mainClassIndex;
    private final int moduleFlags;
    private final int moduleNameIndex;
    private final int moduleVersionIndex;
    private final ByteVector opens;
    private int opensCount;
    private int packageCount;
    private final ByteVector packageIndex;
    private final ByteVector provides;
    private int providesCount;
    private final ByteVector requires;
    private int requiresCount;
    private final SymbolTable symbolTable;
    private int usesCount;
    private final ByteVector usesIndex;

    public ModuleWriter(SymbolTable r2, int r3, int r4, int r5) {
        super(Opcodes.ASM9);
        this.symbolTable = r2;
        this.moduleNameIndex = r3;
        this.moduleFlags = r4;
        this.moduleVersionIndex = r5;
        this.requires = new ByteVector();
        this.exports = new ByteVector();
        this.opens = new ByteVector();
        this.usesIndex = new ByteVector();
        this.provides = new ByteVector();
        this.packageIndex = new ByteVector();
    }

    public int computeAttributesSize() {
        this.symbolTable.addConstantUtf8("Module");
        int r0 = ((((this.requires.length + 22) + this.exports.length) + this.opens.length) + this.usesIndex.length) + this.provides.length;
        if (this.packageCount <= 0) goto L6;
        this.symbolTable.addConstantUtf8("ModulePackages");
        r0 = r0 + (this.packageIndex.length + 8);
    L6:
        if (this.mainClassIndex <= 0) goto L9;
        this.symbolTable.addConstantUtf8("ModuleMainClass");
        return r0 + 8;
    L9:
        return r0;
    }

    public int getAttributeCount() {
        int r1 = 0;
        if (this.packageCount <= 0) goto L5;
        int r0 = 1;
    L6:
        int r02 = r0 + 1;
        if (this.mainClassIndex <= 0) goto L10;
        r1 = 1;
    L10:
        return r02 + r1;
    L5:
        r0 = 0;
        goto L6
    }

    public void putAttributes(ByteVector r6) {
        int r0 = ((((this.requires.length + 16) + this.exports.length) + this.opens.length) + this.usesIndex.length) + this.provides.length;
        ByteVector r02 = r6.putShort(this.symbolTable.addConstantUtf8("Module")).putInt(r0).putShort(this.moduleNameIndex).putShort(this.moduleFlags).putShort(this.moduleVersionIndex).putShort(this.requiresCount);
        ByteVector r1 = this.requires;
        ByteVector r03 = r02.putByteArray(r1.data, 0, r1.length).putShort(this.exportsCount);
        ByteVector r12 = this.exports;
        ByteVector r04 = r03.putByteArray(r12.data, 0, r12.length).putShort(this.opensCount);
        ByteVector r13 = this.opens;
        ByteVector r05 = r04.putByteArray(r13.data, 0, r13.length).putShort(this.usesCount);
        ByteVector r14 = this.usesIndex;
        ByteVector r06 = r05.putByteArray(r14.data, 0, r14.length).putShort(this.providesCount);
        ByteVector r15 = this.provides;
        r06.putByteArray(r15.data, 0, r15.length);
        if (this.packageCount <= 0) goto L6;
        ByteVector r07 = r6.putShort(this.symbolTable.addConstantUtf8("ModulePackages")).putInt(this.packageIndex.length + 2).putShort(this.packageCount);
        ByteVector r2 = this.packageIndex;
        r07.putByteArray(r2.data, 0, r2.length);
    L6:
        if (this.mainClassIndex <= 0) goto L9;
        r6.putShort(this.symbolTable.addConstantUtf8("ModuleMainClass")).putInt(2).putShort(this.mainClassIndex);
        return;
    }

    @Override // org.mvel2.asm.ModuleVisitor
    public void visitEnd() {
    }

    @Override // org.mvel2.asm.ModuleVisitor
    public void visitExport(String r4, int r5, String... r6) {
        this.exports.putShort(this.symbolTable.addConstantPackage(r4).index).putShort(r5);
        ByteVector r42 = this.exports;
        int r52 = 0;
        if (r6 != null) goto L5;
        r42.putShort(0);
    L8:
        this.exportsCount++;
        return;
    L5:
        r42.putShort(r6.length);
        int r43 = r6.length;
    L6:
        if (r52 >= r43) goto L8;
        this.exports.putShort(this.symbolTable.addConstantModule(r6[r52]).index);
        r52 = r52 + 1;
        goto L6
    }

    @Override // org.mvel2.asm.ModuleVisitor
    public void visitMainClass(String r2) {
        this.mainClassIndex = this.symbolTable.addConstantClass(r2).index;
    }

    @Override // org.mvel2.asm.ModuleVisitor
    public void visitOpen(String r4, int r5, String... r6) {
        this.opens.putShort(this.symbolTable.addConstantPackage(r4).index).putShort(r5);
        ByteVector r42 = this.opens;
        int r52 = 0;
        if (r6 != null) goto L5;
        r42.putShort(0);
    L8:
        this.opensCount++;
        return;
    L5:
        r42.putShort(r6.length);
        int r43 = r6.length;
    L6:
        if (r52 >= r43) goto L8;
        this.opens.putShort(this.symbolTable.addConstantModule(r6[r52]).index);
        r52 = r52 + 1;
        goto L6
    }

    @Override // org.mvel2.asm.ModuleVisitor
    public void visitPackage(String r3) {
        this.packageIndex.putShort(this.symbolTable.addConstantPackage(r3).index);
        this.packageCount++;
    }

    @Override // org.mvel2.asm.ModuleVisitor
    public void visitProvide(String r5, String... r6) {
        this.provides.putShort(this.symbolTable.addConstantClass(r5).index);
        this.provides.putShort(r6.length);
        int r52 = r6.length;
        int r0 = 0;
    L3:
        if (r0 >= r52) goto L5;
        this.provides.putShort(this.symbolTable.addConstantClass(r6[r0]).index);
        r0 = r0 + 1;
        goto L3
    L5:
        this.providesCount++;
    }

    @Override // org.mvel2.asm.ModuleVisitor
    public void visitRequire(String r3, int r4, String r5) {
        ByteVector r32 = this.requires.putShort(this.symbolTable.addConstantModule(r3).index).putShort(r4);
        if (r5 != null) goto L5;
        int r42 = 0;
    L6:
        r32.putShort(r42);
        this.requiresCount++;
        return;
    L5:
        r42 = this.symbolTable.addConstantUtf8(r5);
        goto L6
    }

    @Override // org.mvel2.asm.ModuleVisitor
    public void visitUse(String r3) {
        this.usesIndex.putShort(this.symbolTable.addConstantClass(r3).index);
        this.usesCount++;
    }
}
