package com.android.dex;

/* loaded from: classes4.dex */
public final class ClassDef {
    private final int accessFlags;
    private final int annotationsOffset;
    private final Dex buffer;
    private final int classDataOffset;
    private final int interfacesOffset;
    private final int offset;
    private final int sourceFileIndex;
    private final int staticValuesOffset;
    private final int supertypeIndex;
    private final int typeIndex;

    public ClassDef(Dex r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10) {
        this.buffer = r1;
        this.offset = r2;
        this.typeIndex = r3;
        this.accessFlags = r4;
        this.supertypeIndex = r5;
        this.interfacesOffset = r6;
        this.sourceFileIndex = r7;
        this.annotationsOffset = r8;
        this.classDataOffset = r9;
        this.staticValuesOffset = r10;
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    public int getAnnotationsOffset() {
        return this.annotationsOffset;
    }

    public int getClassDataOffset() {
        return this.classDataOffset;
    }

    public short[] getInterfaces() {
        return this.buffer.readTypeList(this.interfacesOffset).getTypes();
    }

    public int getInterfacesOffset() {
        return this.interfacesOffset;
    }

    public int getOffset() {
        return this.offset;
    }

    public int getSourceFileIndex() {
        return this.sourceFileIndex;
    }

    public int getStaticValuesOffset() {
        return this.staticValuesOffset;
    }

    public int getSupertypeIndex() {
        return this.supertypeIndex;
    }

    public int getTypeIndex() {
        return this.typeIndex;
    }

    public String toString() {
        if (this.buffer == null) goto L5;
        StringBuilder r0 = new StringBuilder();
        r0.append(this.buffer.typeNames().get(this.typeIndex));
        if (this.supertypeIndex == (-1)) goto L10;
        r0.append(" extends ");
        r0.append(this.buffer.typeNames().get(this.supertypeIndex));
    L10:
        return r0.toString();
    L5:
        return this.typeIndex + " " + this.supertypeIndex;
    }
}
