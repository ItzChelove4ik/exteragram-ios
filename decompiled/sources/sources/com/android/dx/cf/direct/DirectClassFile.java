package com.android.dx.cf.direct;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.attrib.AttBootstrapMethods;
import com.android.dx.cf.attrib.AttSourceFile;
import com.android.dx.cf.code.BootstrapMethodsList;
import com.android.dx.cf.cst.ConstantPoolParser;
import com.android.dx.cf.iface.Attribute;
import com.android.dx.cf.iface.AttributeList;
import com.android.dx.cf.iface.ClassFile;
import com.android.dx.cf.iface.FieldList;
import com.android.dx.cf.iface.MethodList;
import com.android.dx.cf.iface.ParseException;
import com.android.dx.cf.iface.ParseObserver;
import com.android.dx.cf.iface.StdAttributeList;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.cst.ConstantPool;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.cst.StdConstantPool;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class DirectClassFile implements ClassFile {
    private static final int CLASS_FILE_MAGIC = -889275714;
    private static final int CLASS_FILE_MAX_MAJOR_VERSION = 53;
    private static final int CLASS_FILE_MAX_MINOR_VERSION = 0;
    private static final int CLASS_FILE_MIN_MAJOR_VERSION = 45;
    private int accessFlags;
    private AttributeFactory attributeFactory;
    private StdAttributeList attributes;
    private final ByteArray bytes;
    private FieldList fields;
    private final String filePath;
    private TypeList interfaces;
    private MethodList methods;
    private ParseObserver observer;
    private StdConstantPool pool;
    private final boolean strictParse;
    private CstType superClass;
    private CstType thisClass;

    public static class DcfTypeList implements TypeList {
        private final ByteArray bytes;
        private final StdConstantPool pool;
        private final int size;

        public DcfTypeList(ByteArray r5, int r6, int r7, StdConstantPool r8, ParseObserver r9) {
            if (r7 < 0) goto L15;
            ByteArray r52 = r5.slice(r6, (r7 * 2) + r6);
            this.bytes = r52;
            this.size = r7;
            this.pool = r8;
            int r4 = 0;
        L5:
            if (r4 >= r7) goto L14;
            int r62 = r4 * 2;
            CstType r1 = (CstType) r8.get(r52.getUnsignedShort(r62));     // Catch: ClassCastException -> L11
            if (r9 == null) goto L10;
            r9.parsed(r52, r62, 2, "  " + r1);
        L10:
            r4 = r4 + 1;
        L11:
            e = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("bogus class cpi", e);
            throw null;
        L14:
            return;
        L15:
            f$$ExternalSyntheticBUOutline1.m("size < 0");
            throw null;
        }

        @Override // com.android.dx.rop.type.TypeList
        public Type getType(int r2) {
            int r22 = this.bytes.getUnsignedShort(r2 * 2);
            return ((CstType) this.pool.get(r22)).getClassType();
        }

        @Override // com.android.dx.rop.type.TypeList
        public int getWordCount() {
            return this.size;
        }

        @Override // com.android.dx.rop.type.TypeList
        public boolean isMutable() {
            return false;
        }

        @Override // com.android.dx.rop.type.TypeList
        public int size() {
            return this.size;
        }

        @Override // com.android.dx.rop.type.TypeList
        public TypeList withAddedType(Type r1) {
            throw new UnsupportedOperationException("unsupported");
        }
    }

    public DirectClassFile(ByteArray r2, String r3, boolean r4) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        this.filePath = r3;
        this.bytes = r2;
        this.strictParse = r4;
        this.accessFlags = -1;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("filePath == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("bytes == null");
        throw null;
    }

    public DirectClassFile(byte[] r2, String r3, boolean r4) {
        this(new ByteArray(r2), r3, r4);
    }

    private boolean isGoodMagic(int r1) {
        if (r1 != CLASS_FILE_MAGIC) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isGoodVersion(int r2, int r3) {
        if (r2 >= 0) goto L4;
        return false;
    L4:
        if (r3 != 53) goto L7;
        if (r2 > 0) goto L13;
        return true;
    L13:
        return false;
    L7:
        if (r3 < 53) goto L9;
        return false;
    L9:
        if (r3 < 45) goto L15;
        return true;
    L15:
        return false;
    }

    private void parse() {
        parse0();     // Catch: RuntimeException -> L5 ParseException -> L8
        return;
    L8:
        e = move-exception;
        e.addContext("...while parsing " + this.filePath);
        throw e;
    L5:
        e = move-exception;
        ParseException r2 = new ParseException(e);
        r2.addContext("...while parsing " + this.filePath);
        throw r2;
    }

    private void parse0() {
        if (this.bytes.size() < 10) goto L43;
        ParseObserver r0 = this.observer;
        if (r0 == null) goto L8;
        r0.parsed(this.bytes, 0, 0, "begin classfile");
        this.observer.parsed(this.bytes, 0, 4, "magic: " + Hex.u4(getMagic0()));
        this.observer.parsed(this.bytes, 4, 2, "minor_version: " + Hex.u2(getMinorVersion0()));
        this.observer.parsed(this.bytes, 6, 2, "major_version: " + Hex.u2(getMajorVersion0()));
    L8:
        if (this.strictParse == true) goto L10;
    L18:
        ConstantPoolParser r02 = new ConstantPoolParser(this.bytes);
        r02.setObserver(this.observer);
        StdConstantPool r5 = r02.getPool();
        this.pool = r5;
        r5.setImmutable();
        int r03 = r02.getEndOffset();
        int r52 = this.bytes.getUnsignedShort(r03);
        int r7 = r03 + 2;
        this.thisClass = (CstType) this.pool.get(this.bytes.getUnsignedShort(r7));
        int r8 = r03 + 4;
        this.superClass = (CstType) this.pool.get0Ok(this.bytes.getUnsignedShort(r8));
        int r9 = r03 + 6;
        int r6 = this.bytes.getUnsignedShort(r9);
        ParseObserver r10 = this.observer;
        if (r10 == null) goto L23;
        r10.parsed(this.bytes, r03, 2, "access_flags: " + AccessFlags.classString(r52));
        this.observer.parsed(this.bytes, r7, 2, "this_class: " + this.thisClass);
        this.observer.parsed(this.bytes, r8, 2, "super_class: " + stringOrNone(this.superClass));
        this.observer.parsed(this.bytes, r9, 2, "interfaces_count: " + Hex.u2(r6));
        if (r6 == 0) goto L23;
        this.observer.parsed(this.bytes, r03 + 8, 0, "interfaces:");
    L23:
        int r04 = r03 + 8;
        this.interfaces = makeTypeList(r04, r6);
        int r05 = r04 + (r6 * 2);
        if (this.strictParse == false) goto L34;
        String r3 = this.thisClass.getClassType().getClassName();
        if (this.filePath.endsWith(".class") == false) goto L33;
        if (this.filePath.startsWith(r3) == false) goto L33;
        if (this.filePath.length() == (r3.length() + 6)) goto L34;
    L33:
        throw new ParseException("class name (" + r3 + ") does not match path (" + this.filePath + ")");
    L34:
        this.accessFlags = r52;
        FieldListParser r1 = new FieldListParser(this, this.thisClass, r05, this.attributeFactory);
        r1.setObserver(this.observer);
        this.fields = r1.getList();
        MethodListParser r12 = new MethodListParser(this, this.thisClass, r1.getEndOffset(), this.attributeFactory);
        r12.setObserver(this.observer);
        this.methods = r12.getList();
        AttributeListParser r13 = new AttributeListParser(this, 0, r12.getEndOffset(), this.attributeFactory);
        r13.setObserver(this.observer);
        StdAttributeList r06 = r13.getList();
        this.attributes = r06;
        r06.setImmutable();
        int r07 = r13.getEndOffset();
        if (r07 != this.bytes.size()) goto L41;
        ParseObserver r14 = this.observer;
        if (r14 == null) goto L44;
        r14.parsed(this.bytes, r07, 0, "end classfile");
        return;
    L44:
        return;
    L41:
        throw new ParseException("extra bytes at end of class file, at offset " + Hex.u4(r07));
    L10:
        if (isGoodMagic(getMagic0()) == false) goto L17;
        if (isGoodVersion(getMinorVersion0(), getMajorVersion0()) == true) goto L18;
        throw new ParseException("unsupported class file version " + getMajorVersion0() + "." + getMinorVersion0());
    L17:
        throw new ParseException("bad class file magic (" + Hex.u4(getMagic0()) + ")");
    L43:
        throw new ParseException("severely truncated class file");
    }

    private void parseToEndIfNecessary() {
        if (this.attributes != null) goto L6;
        parse();
        return;
    }

    private void parseToInterfacesIfNecessary() {
        if (this.accessFlags != (-1)) goto L6;
        parse();
        return;
    }

    public static String stringOrNone(Object r0) {
        if (r0 != null) goto L6;
        return "(none)";
    L6:
        return r0.toString();
    }

    @Override // com.android.dx.cf.iface.ClassFile
    public int getAccessFlags() {
        parseToInterfacesIfNecessary();
        return this.accessFlags;
    }

    @Override // com.android.dx.cf.iface.ClassFile, com.android.dx.cf.iface.HasAttribute
    public AttributeList getAttributes() {
        parseToEndIfNecessary();
        return this.attributes;
    }

    @Override // com.android.dx.cf.iface.ClassFile
    public BootstrapMethodsList getBootstrapMethods() {
        AttBootstrapMethods r1 = (AttBootstrapMethods) getAttributes().findFirst(AttBootstrapMethods.ATTRIBUTE_NAME);
        if (r1 == null) goto L7;
        return r1.getBootstrapMethods();
    L7:
        return BootstrapMethodsList.EMPTY;
    }

    public ByteArray getBytes() {
        return this.bytes;
    }

    @Override // com.android.dx.cf.iface.ClassFile
    public ConstantPool getConstantPool() {
        parseToInterfacesIfNecessary();
        return this.pool;
    }

    @Override // com.android.dx.cf.iface.ClassFile
    public FieldList getFields() {
        parseToEndIfNecessary();
        return this.fields;
    }

    public String getFilePath() {
        return this.filePath;
    }

    @Override // com.android.dx.cf.iface.ClassFile
    public TypeList getInterfaces() {
        parseToInterfacesIfNecessary();
        return this.interfaces;
    }

    @Override // com.android.dx.cf.iface.ClassFile
    public int getMagic() {
        parseToInterfacesIfNecessary();
        return getMagic0();
    }

    public int getMagic0() {
        return this.bytes.getInt(0);
    }

    @Override // com.android.dx.cf.iface.ClassFile
    public int getMajorVersion() {
        parseToInterfacesIfNecessary();
        return getMajorVersion0();
    }

    public int getMajorVersion0() {
        return this.bytes.getUnsignedShort(6);
    }

    @Override // com.android.dx.cf.iface.ClassFile
    public MethodList getMethods() {
        parseToEndIfNecessary();
        return this.methods;
    }

    @Override // com.android.dx.cf.iface.ClassFile
    public int getMinorVersion() {
        parseToInterfacesIfNecessary();
        return getMinorVersion0();
    }

    public int getMinorVersion0() {
        return this.bytes.getUnsignedShort(4);
    }

    @Override // com.android.dx.cf.iface.ClassFile
    public CstString getSourceFile() {
        Attribute r1 = getAttributes().findFirst(AttSourceFile.ATTRIBUTE_NAME);
        if ((r1 instanceof AttSourceFile) == true) goto L5;
        return null;
    L5:
        return ((AttSourceFile) r1).getSourceFile();
    }

    @Override // com.android.dx.cf.iface.ClassFile
    public CstType getSuperclass() {
        parseToInterfacesIfNecessary();
        return this.superClass;
    }

    @Override // com.android.dx.cf.iface.ClassFile
    public CstType getThisClass() {
        parseToInterfacesIfNecessary();
        return this.thisClass;
    }

    public TypeList makeTypeList(int r7, int r8) {
        if (r8 == 0) goto L4;
        StdConstantPool r4 = this.pool;
        if (r4 != null) goto L8;
        Segment$$ExternalSyntheticBUOutline1.m("pool not yet initialized");
        return null;
    L8:
        return new DcfTypeList(this.bytes, r7, r8, r4, this.observer);
    L4:
        return StdTypeList.EMPTY;
    }

    public void setAttributeFactory(AttributeFactory r1) {
        if (r1 == null) goto L5;
        this.attributeFactory = r1;
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("attributeFactory == null");
    }

    public void setObserver(ParseObserver r1) {
        this.observer = r1;
    }
}
