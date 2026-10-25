package org.mvel2.asm;

import com.android.dx.cf.attrib.AttDeprecated;
import com.android.dx.cf.attrib.AttEnclosingMethod;
import com.android.dx.cf.attrib.AttInnerClasses;
import com.android.dx.cf.attrib.AttRuntimeInvisibleAnnotations;
import com.android.dx.cf.attrib.AttRuntimeVisibleAnnotations;
import com.android.dx.cf.attrib.AttSignature;
import com.android.dx.cf.attrib.AttSourceDebugExtension;
import com.android.dx.cf.attrib.AttSourceFile;
import com.android.dx.cf.attrib.AttSynthetic;
import org.mvel2.asm.Attribute;

/* loaded from: classes5.dex */
public class ClassWriter extends ClassVisitor {
    public static final int COMPUTE_FRAMES = 2;
    public static final int COMPUTE_MAXS = 1;
    private int accessFlags;
    private int compute;
    private ByteVector debugExtension;
    private int enclosingClassIndex;
    private int enclosingMethodIndex;
    private Attribute firstAttribute;
    private FieldWriter firstField;
    private MethodWriter firstMethod;
    private RecordComponentWriter firstRecordComponent;
    private final int flags;
    private ByteVector innerClasses;
    private int interfaceCount;
    private int[] interfaces;
    private FieldWriter lastField;
    private MethodWriter lastMethod;
    private RecordComponentWriter lastRecordComponent;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private ModuleWriter moduleWriter;
    private int nestHostClassIndex;
    private ByteVector nestMemberClasses;
    private int numberOfInnerClasses;
    private int numberOfNestMemberClasses;
    private int numberOfPermittedSubclasses;
    private ByteVector permittedSubclasses;
    private int signatureIndex;
    private int sourceFileIndex;
    private int superClass;
    private final SymbolTable symbolTable;
    private int thisClass;
    private int version;

    public ClassWriter(int r2) {
        this(null, r2);
    }

    public ClassWriter(ClassReader r2, int r3) {
        super(Opcodes.ASM9);
        this.flags = r3;
        if (r2 != null) goto L5;
        SymbolTable r22 = new SymbolTable(this);
    L6:
        this.symbolTable = r22;
        if ((r3 & 2) == 0) goto L11;
        this.compute = 4;
        return;
    L11:
        if ((r3 & 1) == 0) goto L14;
        this.compute = 1;
        return;
    L14:
        this.compute = 0;
        return;
    L5:
        r22 = new SymbolTable(this, r2);
        goto L6
    }

    private Attribute[] getAttributePrototypes() {
        Attribute.Set r0 = new Attribute.Set();
        r0.addAttributes(this.firstAttribute);
        FieldWriter r1 = this.firstField;
    L3:
        if (r1 == null) goto L5;
        r1.collectAttributePrototypes(r0);
        r1 = (FieldWriter) r1.fv;
        goto L3
    L5:
        MethodWriter r12 = this.firstMethod;
    L6:
        if (r12 == null) goto L8;
        r12.collectAttributePrototypes(r0);
        r12 = (MethodWriter) r12.mv;
        goto L6
    L8:
        RecordComponentWriter r2 = this.firstRecordComponent;
    L9:
        if (r2 == null) goto L12;
        r2.collectAttributePrototypes(r0);
        r2 = (RecordComponentWriter) r2.delegate;
        goto L9
    L12:
        return r0.toArray();
    }

    private byte[] replaceAsmInstructions(byte[] r4, boolean r5) {
        Attribute[] r0 = getAttributePrototypes();
        this.firstField = null;
        this.lastField = null;
        this.firstMethod = null;
        this.lastMethod = null;
        this.lastRuntimeVisibleAnnotation = null;
        this.lastRuntimeInvisibleAnnotation = null;
        this.lastRuntimeVisibleTypeAnnotation = null;
        this.lastRuntimeInvisibleTypeAnnotation = null;
        this.moduleWriter = null;
        int r2 = 0;
        this.nestHostClassIndex = 0;
        this.numberOfNestMemberClasses = 0;
        this.nestMemberClasses = null;
        this.numberOfPermittedSubclasses = 0;
        this.permittedSubclasses = null;
        this.firstRecordComponent = null;
        this.lastRecordComponent = null;
        this.firstAttribute = null;
        if (r5 == false) goto L5;
        int r1 = 3;
    L6:
        this.compute = r1;
        ClassReader r12 = new ClassReader(r4, 0, false);
        if (r5 == false) goto L9;
        r2 = 8;
    L9:
        r12.accept(this, r0, r2 | 256);
        return toByteArray();
    L5:
        r1 = 0;
        goto L6
    }

    public ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }

    public String getCommonSuperClass(String r6, String r7) {
        ClassLoader r5 = getClassLoader();
        Class<?> r2 = Class.forName(r6.replace('/', '.'), false, r5);     // Catch: ClassNotFoundException -> L25
        Class<?> r52 = Class.forName(r7.replace('/', '.'), false, r5);     // Catch: ClassNotFoundException -> L22
        if (r2.isAssignableFrom(r52) == false) goto L9;
        return r6;
    L9:
        if (r52.isAssignableFrom(r2) == false) goto L12;
        return r7;
    L12:
        if (r2.isInterface() == false) goto L14;
        return "java/lang/Object";
    L14:
        if (r52.isInterface() == true) goto L34;
    L16:
        r2 = r2.getSuperclass();
        if (r2.isAssignableFrom(r52) == false) goto L16;
        return r2.getName().replace('.', '/');
    L34:
        return "java/lang/Object";
    L22:
        e = move-exception;
        throw new TypeNotPresentException(r7, e);
    L25:
        e = move-exception;
        throw new TypeNotPresentException(r6, e);
    }

    public boolean hasFlags(int r1) {
        if ((this.flags & r1) != r1) goto L6;
        return true;
    L6:
        return false;
    }

    public int newClass(String r1) {
        return this.symbolTable.addConstantClass(r1).index;
    }

    public int newConst(Object r1) {
        return this.symbolTable.addConstant(r1).index;
    }

    public int newConstantDynamic(String r1, String r2, Handle r3, Object... r4) {
        return this.symbolTable.addConstantDynamic(r1, r2, r3, r4).index;
    }

    public int newField(String r1, String r2, String r3) {
        return this.symbolTable.addConstantFieldref(r1, r2, r3).index;
    }

    @Deprecated
    public int newHandle(int r8, String r9, String r10, String r11) {
        if (r8 != 9) goto L6;
        boolean r0 = true;
    L8:
        return newHandle(r8, r9, r10, r11, r0);
    L6:
        r0 = false;
        goto L8
    }

    public int newHandle(int r1, String r2, String r3, String r4, boolean r5) {
        return this.symbolTable.addConstantMethodHandle(r1, r2, r3, r4, r5).index;
    }

    public int newInvokeDynamic(String r1, String r2, Handle r3, Object... r4) {
        return this.symbolTable.addConstantInvokeDynamic(r1, r2, r3, r4).index;
    }

    public int newMethod(String r1, String r2, String r3, boolean r4) {
        return this.symbolTable.addConstantMethodref(r1, r2, r3, r4).index;
    }

    public int newMethodType(String r1) {
        return this.symbolTable.addConstantMethodType(r1).index;
    }

    public int newModule(String r1) {
        return this.symbolTable.addConstantModule(r1).index;
    }

    public int newNameType(String r1, String r2) {
        return this.symbolTable.addConstantNameAndType(r1, r2);
    }

    public int newPackage(String r1) {
        return this.symbolTable.addConstantPackage(r1).index;
    }

    public int newUTF8(String r1) {
        return this.symbolTable.addConstantUtf8(r1);
    }

    public byte[] toByteArray() {
        int r1 = (this.interfaceCount * 2) + 24;
        FieldWriter r3 = this.firstField;
        int r5 = 0;
    L3:
        if (r3 == null) goto L5;
        r5 = r5 + 1;
        r1 = r1 + r3.computeFieldInfoSize();
        r3 = (FieldWriter) r3.fv;
        goto L3
    L5:
        MethodWriter r32 = this.firstMethod;
        int r6 = 0;
    L6:
        if (r32 == null) goto L8;
        r6 = r6 + 1;
        r1 = r1 + r32.computeMethodInfoSize();
        r32 = (MethodWriter) r32.mv;
        goto L6
    L8:
        ByteVector r33 = this.innerClasses;
        if (r33 == null) goto L11;
        r1 = r1 + (r33.length + 8);
        this.symbolTable.addConstantUtf8(AttInnerClasses.ATTRIBUTE_NAME);
        int r34 = 1;
    L13:
        if (this.enclosingClassIndex == 0) goto L16;
        r34 = r34 + 1;
        r1 = r1 + 10;
        this.symbolTable.addConstantUtf8(AttEnclosingMethod.ATTRIBUTE_NAME);
    L16:
        if ((this.accessFlags & 4096) == 0) goto L21;
        if ((this.version & 65535) >= 49) goto L21;
        r34 = r34 + 1;
        r1 = r1 + 6;
        this.symbolTable.addConstantUtf8(AttSynthetic.ATTRIBUTE_NAME);
    L21:
        if (this.signatureIndex == 0) goto L24;
        r34 = r34 + 1;
        r1 = r1 + 8;
        this.symbolTable.addConstantUtf8(AttSignature.ATTRIBUTE_NAME);
    L24:
        if (this.sourceFileIndex == 0) goto L26;
        r34 = r34 + 1;
        r1 = r1 + 8;
        this.symbolTable.addConstantUtf8(AttSourceFile.ATTRIBUTE_NAME);
    L26:
        ByteVector r8 = this.debugExtension;
        if (r8 == null) goto L30;
        r34 = r34 + 1;
        r1 = r1 + (r8.length + 6);
        this.symbolTable.addConstantUtf8(AttSourceDebugExtension.ATTRIBUTE_NAME);
    L30:
        if ((this.accessFlags & 131072) == 0) goto L32;
        r34 = r34 + 1;
        r1 = r1 + 6;
        this.symbolTable.addConstantUtf8(AttDeprecated.ATTRIBUTE_NAME);
    L32:
        AnnotationWriter r82 = this.lastRuntimeVisibleAnnotation;
        if (r82 == null) goto L35;
        r34 = r34 + 1;
        r1 = r1 + r82.computeAnnotationsSize(AttRuntimeVisibleAnnotations.ATTRIBUTE_NAME);
    L35:
        AnnotationWriter r4 = this.lastRuntimeInvisibleAnnotation;
        if (r4 == null) goto L38;
        r34 = r34 + 1;
        r1 = r1 + r4.computeAnnotationsSize(AttRuntimeInvisibleAnnotations.ATTRIBUTE_NAME);
    L38:
        AnnotationWriter r42 = this.lastRuntimeVisibleTypeAnnotation;
        if (r42 == null) goto L41;
        r34 = r34 + 1;
        r1 = r1 + r42.computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
    L41:
        AnnotationWriter r43 = this.lastRuntimeInvisibleTypeAnnotation;
        if (r43 == null) goto L45;
        r34 = r34 + 1;
        r1 = r1 + r43.computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
    L45:
        if (this.symbolTable.computeBootstrapMethodsSize() <= 0) goto L47;
        r34 = r34 + 1;
        r1 = r1 + this.symbolTable.computeBootstrapMethodsSize();
    L47:
        ModuleWriter r44 = this.moduleWriter;
        if (r44 == null) goto L51;
        r34 = r34 + r44.getAttributeCount();
        r1 = r1 + this.moduleWriter.computeAttributesSize();
    L51:
        if (this.nestHostClassIndex == 0) goto L53;
        r34 = r34 + 1;
        r1 = r1 + 8;
        this.symbolTable.addConstantUtf8("NestHost");
    L53:
        ByteVector r45 = this.nestMemberClasses;
        if (r45 == null) goto L56;
        r34 = r34 + 1;
        r1 = r1 + (r45.length + 8);
        this.symbolTable.addConstantUtf8("NestMembers");
    L56:
        ByteVector r46 = this.permittedSubclasses;
        if (r46 == null) goto L59;
        r34 = r34 + 1;
        r1 = r1 + (r46.length + 8);
        this.symbolTable.addConstantUtf8("PermittedSubclasses");
    L59:
        int r20 = r1;
        if ((this.accessFlags & 65536) == 0) goto L62;
    L65:
        RecordComponentWriter r47 = this.firstRecordComponent;
        int r21 = 0;
        int r22 = 0;
    L66:
        if (r47 == null) goto L68;
        r22 = r22 + 1;
        r21 = r21 + r47.computeRecordComponentInfoSize();
        r47 = (RecordComponentWriter) r47.delegate;
        goto L66
    L68:
        int r48 = r20 + (r21 + 8);
        int r202 = r34 + 1;
        this.symbolTable.addConstantUtf8("Record");
        int r35 = r22;
        int r222 = r48;
    L69:
        Attribute r49 = this.firstAttribute;
        if (r49 == null) goto L72;
        int r410 = r202 + r49.getAttributeCount();
        int r23 = r35;
        int r36 = r410;
        int r203 = r222 + this.firstAttribute.computeAttributesSize(this.symbolTable);
    L73:
        int r411 = r203 + this.symbolTable.getConstantPoolLength();
        int r12 = this.symbolTable.getConstantPoolCount();
        if (r12 > 65535) goto L142;
        ByteVector r13 = new ByteVector(r411);
        r13.putInt(-889275714).putInt(this.version);
        this.symbolTable.putConstantPool(r13);
        if ((this.version & 65535) >= 49) goto L78;
        int r412 = 4096;
    L79:
        r13.putShort((~r412) & this.accessFlags).putShort(this.thisClass).putShort(this.superClass);
        r13.putShort(this.interfaceCount);
        int r413 = 0;
    L81:
        if (r413 >= this.interfaceCount) goto L83;
        r13.putShort(this.interfaces[r413]);
        r413 = r413 + 1;
        goto L81
    L83:
        r13.putShort(r5);
        FieldWriter r414 = this.firstField;
    L84:
        if (r414 == null) goto L86;
        r414.putFieldInfo(r13);
        r414 = (FieldWriter) r414.fv;
        goto L84
    L86:
        r13.putShort(r6);
        MethodWriter r415 = this.firstMethod;
        boolean r52 = false;
        boolean r62 = false;
    L87:
        if (r415 == null) goto L89;
        r52 = r52 | r415.hasFrames();
        r62 = r62 | r415.hasAsmInstructions();
        r415.putMethodInfo(r13);
        r415 = (MethodWriter) r415.mv;
        goto L87
    L89:
        r13.putShort(r36);
        if (this.innerClasses == null) goto L93;
        ByteVector r37 = r13.putShort(this.symbolTable.addConstantUtf8(AttInnerClasses.ATTRIBUTE_NAME)).putInt(this.innerClasses.length + 2).putShort(this.numberOfInnerClasses);
        ByteVector r416 = this.innerClasses;
        r37.putByteArray(r416.data, 0, r416.length);
    L93:
        if (this.enclosingClassIndex == 0) goto L96;
        r13.putShort(this.symbolTable.addConstantUtf8(AttEnclosingMethod.ATTRIBUTE_NAME)).putInt(4).putShort(this.enclosingClassIndex).putShort(this.enclosingMethodIndex);
    L96:
        if ((this.accessFlags & 4096) == 0) goto L101;
        if ((this.version & 65535) >= 49) goto L101;
        r13.putShort(this.symbolTable.addConstantUtf8(AttSynthetic.ATTRIBUTE_NAME)).putInt(0);
    L101:
        if (this.signatureIndex == 0) goto L103;
        int r417 = 2;
        r13.putShort(this.symbolTable.addConstantUtf8(AttSignature.ATTRIBUTE_NAME)).putInt(2).putShort(this.signatureIndex);
    L105:
        if (this.sourceFileIndex == 0) goto L107;
        r13.putShort(this.symbolTable.addConstantUtf8(AttSourceFile.ATTRIBUTE_NAME)).putInt(r417).putShort(this.sourceFileIndex);
    L107:
        ByteVector r38 = this.debugExtension;
        if (r38 == null) goto L110;
        int r39 = r38.length;
        int r132 = 0;
        r13.putShort(this.symbolTable.addConstantUtf8(AttSourceDebugExtension.ATTRIBUTE_NAME)).putInt(r39).putByteArray(this.debugExtension.data, 0, r39);
    L112:
        if ((this.accessFlags & 131072) == 0) goto L114;
        r13.putShort(this.symbolTable.addConstantUtf8(AttDeprecated.ATTRIBUTE_NAME)).putInt(r132);
    L114:
        AnnotationWriter.putAnnotations(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, r13);
        this.symbolTable.putBootstrapMethods(r13);
        ModuleWriter r2 = this.moduleWriter;
        if (r2 == null) goto L118;
        r2.putAttributes(r13);
    L118:
        if (this.nestHostClassIndex == 0) goto L121;
        r13.putShort(this.symbolTable.addConstantUtf8("NestHost")).putInt(2).putShort(this.nestHostClassIndex);
    L121:
        if (this.nestMemberClasses == null) goto L124;
        ByteVector r24 = r13.putShort(this.symbolTable.addConstantUtf8("NestMembers")).putInt(this.nestMemberClasses.length + 2).putShort(this.numberOfNestMemberClasses);
        ByteVector r310 = this.nestMemberClasses;
        r24.putByteArray(r310.data, 0, r310.length);
    L124:
        if (this.permittedSubclasses == null) goto L127;
        ByteVector r25 = r13.putShort(this.symbolTable.addConstantUtf8("PermittedSubclasses")).putInt(this.permittedSubclasses.length + 2).putShort(this.numberOfPermittedSubclasses);
        ByteVector r311 = this.permittedSubclasses;
        r25.putByteArray(r311.data, 0, r311.length);
    L127:
        if ((this.accessFlags & 65536) == 0) goto L129;
    L130:
        r13.putShort(this.symbolTable.addConstantUtf8("Record")).putInt(r21 + 2).putShort(r23);
        RecordComponentWriter r26 = this.firstRecordComponent;
    L131:
        if (r26 == null) goto L133;
        r26.putRecordComponentInfo(r13);
        r26 = (RecordComponentWriter) r26.delegate;
    L133:
        Attribute r27 = this.firstAttribute;
        if (r27 == null) goto L136;
        r27.putAttributes(this.symbolTable, r13);
    L136:
        byte[] r14 = r13.data;
        if (r62 == true) goto L139;
        return r14;
    L139:
        return replaceAsmInstructions(r14, r52);
    L129:
        if (this.firstRecordComponent == null) goto L133;
    L110:
        r132 = 0;
        goto L112
    L103:
        r417 = 2;
        goto L105
    L78:
        r412 = 0;
        goto L79
    L142:
        throw new ClassTooLargeException(this.symbolTable.getClassName(), r12);
    L72:
        r23 = r35;
        r36 = r202;
        r203 = r222;
        goto L73
    L62:
        if (this.firstRecordComponent != null) goto L65;
        r222 = r20;
        r21 = 0;
        r202 = r34;
        r35 = 0;
        goto L69
    L11:
        r34 = 0;
        goto L13
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final void visit(int r2, int r3, String r4, String r5, String r6, String[] r7) {
        this.version = r2;
        this.accessFlags = r3;
        int r22 = r2 & 65535;
        this.thisClass = this.symbolTable.setMajorVersionAndClassName(r22, r4);
        if (r5 == null) goto L5;
        this.signatureIndex = this.symbolTable.addConstantUtf8(r5);
    L5:
        int r32 = 0;
        if (r6 != null) goto L8;
        int r42 = 0;
    L9:
        this.superClass = r42;
        if (r7 == null) goto L18;
        if (r7.length <= 0) goto L18;
        int r43 = r7.length;
        this.interfaceCount = r43;
        this.interfaces = new int[r43];
    L15:
        if (r32 >= this.interfaceCount) goto L18;
        this.interfaces[r32] = this.symbolTable.addConstantClass(r7[r32]).index;
        r32 = r32 + 1;
    L18:
        if (this.compute == 1) goto L20;
        return;
    L20:
        if (r22 < 51) goto L25;
        this.compute = 2;
        return;
    L25:
        return;
    L8:
        r42 = this.symbolTable.addConstantClass(r6).index;
        goto L9
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final AnnotationVisitor visitAnnotation(String r2, boolean r3) {
        SymbolTable r0 = this.symbolTable;
        if (r3 == false) goto L6;
        AnnotationWriter r22 = AnnotationWriter.create(r0, r2, this.lastRuntimeVisibleAnnotation);
        this.lastRuntimeVisibleAnnotation = r22;
        return r22;
    L6:
        AnnotationWriter r23 = AnnotationWriter.create(r0, r2, this.lastRuntimeInvisibleAnnotation);
        this.lastRuntimeInvisibleAnnotation = r23;
        return r23;
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final void visitAttribute(Attribute r2) {
        r2.nextAttribute = this.firstAttribute;
        this.firstAttribute = r2;
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final void visitEnd() {
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final FieldVisitor visitField(int r8, String r9, String r10, String r11, Object r12) {
        FieldWriter r0 = new FieldWriter(this.symbolTable, r8, r9, r10, r11, r12);
        if (this.firstField != null) goto L5;
        this.firstField = r0;
    L6:
        this.lastField = r0;
        return r0;
    L5:
        this.lastField.fv = r0;
        goto L6
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final void visitInnerClass(String r4, String r5, String r6, int r7) {
        if (this.innerClasses != null) goto L5;
        this.innerClasses = new ByteVector();
    L5:
        Symbol r42 = this.symbolTable.addConstantClass(r4);
        if (r42.info != 0) goto L17;
        this.numberOfInnerClasses++;
        this.innerClasses.putShort(r42.index);
        ByteVector r0 = this.innerClasses;
        int r1 = 0;
        if (r5 != null) goto L10;
        int r52 = 0;
    L11:
        r0.putShort(r52);
        ByteVector r53 = this.innerClasses;
        if (r6 == null) goto L15;
        r1 = this.symbolTable.addConstantUtf8(r6);
    L15:
        r53.putShort(r1);
        this.innerClasses.putShort(r7);
        r42.info = this.numberOfInnerClasses;
        return;
    L10:
        r52 = this.symbolTable.addConstantClass(r5).index;
        goto L11
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final MethodVisitor visitMethod(int r9, String r10, String r11, String r12, String[] r13) {
        MethodWriter r0 = new MethodWriter(this.symbolTable, r9, r10, r11, r12, r13, this.compute);
        if (this.firstMethod != null) goto L5;
        this.firstMethod = r0;
    L6:
        this.lastMethod = r0;
        return r0;
    L5:
        this.lastMethod.mv = r0;
        goto L6
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final ModuleVisitor visitModule(String r4, int r5, String r6) {
        SymbolTable r1 = this.symbolTable;
        int r42 = r1.addConstantModule(r4).index;
        if (r6 != null) goto L5;
        int r62 = 0;
    L6:
        ModuleWriter r0 = new ModuleWriter(r1, r42, r5, r62);
        this.moduleWriter = r0;
        return r0;
    L5:
        r62 = this.symbolTable.addConstantUtf8(r6);
        goto L6
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final void visitNestHost(String r2) {
        this.nestHostClassIndex = this.symbolTable.addConstantClass(r2).index;
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final void visitNestMember(String r2) {
        if (this.nestMemberClasses != null) goto L5;
        this.nestMemberClasses = new ByteVector();
    L5:
        this.numberOfNestMemberClasses++;
        this.nestMemberClasses.putShort(this.symbolTable.addConstantClass(r2).index);
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final void visitOuterClass(String r2, String r3, String r4) {
        this.enclosingClassIndex = this.symbolTable.addConstantClass(r2).index;
        if (r3 == null) goto L7;
        if (r4 == null) goto L8;
        this.enclosingMethodIndex = this.symbolTable.addConstantNameAndType(r3, r4);
        return;
    L8:
        return;
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final void visitPermittedSubclass(String r2) {
        if (this.permittedSubclasses != null) goto L5;
        this.permittedSubclasses = new ByteVector();
    L5:
        this.numberOfPermittedSubclasses++;
        this.permittedSubclasses.putShort(this.symbolTable.addConstantClass(r2).index);
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final RecordComponentVisitor visitRecordComponent(String r3, String r4, String r5) {
        RecordComponentWriter r0 = new RecordComponentWriter(this.symbolTable, r3, r4, r5);
        if (this.firstRecordComponent != null) goto L5;
        this.firstRecordComponent = r0;
    L6:
        this.lastRecordComponent = r0;
        return r0;
    L5:
        this.lastRecordComponent.delegate = r0;
        goto L6
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final void visitSource(String r3, String r4) {
        if (r3 == null) goto L4;
        this.sourceFileIndex = this.symbolTable.addConstantUtf8(r3);
    L4:
        if (r4 == null) goto L7;
        this.debugExtension = new ByteVector().encodeUtf8(r4, 0, Integer.MAX_VALUE);
        return;
    }

    @Override // org.mvel2.asm.ClassVisitor
    public final AnnotationVisitor visitTypeAnnotation(int r2, TypePath r3, String r4, boolean r5) {
        SymbolTable r0 = this.symbolTable;
        if (r5 == false) goto L6;
        AnnotationWriter r22 = AnnotationWriter.create(r0, r2, r3, r4, this.lastRuntimeVisibleTypeAnnotation);
        this.lastRuntimeVisibleTypeAnnotation = r22;
        return r22;
    L6:
        AnnotationWriter r23 = AnnotationWriter.create(r0, r2, r3, r4, this.lastRuntimeInvisibleTypeAnnotation);
        this.lastRuntimeInvisibleTypeAnnotation = r23;
        return r23;
    }
}
