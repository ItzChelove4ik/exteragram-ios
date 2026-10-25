package org.mvel2.asm;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.cf.attrib.AttAnnotationDefault;
import com.android.dx.cf.attrib.AttCode;
import com.android.dx.cf.attrib.AttExceptions;
import com.android.dx.cf.attrib.AttLineNumberTable;
import com.android.dx.cf.attrib.AttLocalVariableTable;
import com.android.dx.cf.attrib.AttLocalVariableTypeTable;
import com.android.dx.cf.attrib.AttRuntimeInvisibleParameterAnnotations;
import com.android.dx.cf.attrib.AttRuntimeVisibleParameterAnnotations;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.Attribute;

/* loaded from: classes5.dex */
final class MethodWriter extends MethodVisitor {
    static final int COMPUTE_ALL_FRAMES = 4;
    static final int COMPUTE_INSERTED_FRAMES = 3;
    static final int COMPUTE_MAX_STACK_AND_LOCAL = 1;
    static final int COMPUTE_MAX_STACK_AND_LOCAL_FROM_FRAMES = 2;
    static final int COMPUTE_NOTHING = 0;
    private static final int NA = 0;
    private static final int[] STACK_SIZE_DELTA = null;
    private final int accessFlags;
    private final ByteVector code;
    private final int compute;
    private Label currentBasicBlock;
    private int[] currentFrame;
    private int currentLocals;
    private ByteVector defaultValue;
    private final String descriptor;
    private final int descriptorIndex;
    private final int[] exceptionIndexTable;
    private Attribute firstAttribute;
    private Label firstBasicBlock;
    private Attribute firstCodeAttribute;
    private Handler firstHandler;
    private boolean hasAsmInstructions;
    private boolean hasSubroutines;
    private int invisibleAnnotableParameterCount;
    private Label lastBasicBlock;
    private int lastBytecodeOffset;
    private AnnotationWriter lastCodeRuntimeInvisibleTypeAnnotation;
    private AnnotationWriter lastCodeRuntimeVisibleTypeAnnotation;
    private Handler lastHandler;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private AnnotationWriter[] lastRuntimeInvisibleParameterAnnotations;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter[] lastRuntimeVisibleParameterAnnotations;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private ByteVector lineNumberTable;
    private int lineNumberTableLength;
    private ByteVector localVariableTable;
    private int localVariableTableLength;
    private ByteVector localVariableTypeTable;
    private int localVariableTypeTableLength;
    private int maxLocals;
    private int maxRelativeStackSize;
    private int maxStack;
    private final String name;
    private final int nameIndex;
    private final int numberOfExceptions;
    private ByteVector parameters;
    private int parametersCount;
    private int[] previousFrame;
    private int previousFrameOffset;
    private int relativeStackSize;
    private final int signatureIndex;
    private int sourceLength;
    private int sourceOffset;
    private ByteVector stackMapTableEntries;
    private int stackMapTableNumberOfEntries;
    private final SymbolTable symbolTable;
    private int visibleAnnotableParameterCount;

    static {
        STACK_SIZE_DELTA = new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 0, 0, 1, 2, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, -1, 0, -1, -1, -1, -1, -1, -2, -1, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, -4, -3, -4, -3, -3, -3, -3, -1, -2, 1, 1, 1, 2, 2, 2, 0, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -2, -1, -2, -1, -2, 0, 1, 0, 1, -1, -1, 0, 0, 1, 1, -1, 0, -1, 0, 0, 0, -3, -1, -1, -3, -3, -1, -1, -1, -1, -1, -1, -2, -2, -2, -2, -2, -2, -2, -2, 0, 1, 0, -1, -1, -1, -2, -1, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1, 0, 0};
    }

    public MethodWriter(SymbolTable r2, int r3, String r4, String r5, String r6, String[] r7, int r8) {
        super(Opcodes.ASM9);
        this.code = new ByteVector();
        this.symbolTable = r2;
        if ("<init>".equals(r4) == false) goto L5;
        int r0 = 262144 | r3;
    L6:
        this.accessFlags = r0;
        this.nameIndex = r2.addConstantUtf8(r4);
        this.name = r4;
        this.descriptorIndex = r2.addConstantUtf8(r5);
        this.descriptor = r5;
        int r42 = 0;
        if (r6 != null) goto L9;
        int r62 = 0;
    L10:
        this.signatureIndex = r62;
        if (r7 != null) goto L13;
    L18:
        this.numberOfExceptions = 0;
        this.exceptionIndexTable = null;
    L19:
        this.compute = r8;
        if (r8 == 0) goto L27;
        int r22 = Type.getArgumentsAndReturnSizes(r5) >> 2;
        if ((r3 & 8) == 0) goto L24;
        r22 = r22 - 1;
    L24:
        this.maxLocals = r22;
        this.currentLocals = r22;
        Label r23 = new Label();
        this.firstBasicBlock = r23;
        visitLabel(r23);
        return;
    L27:
        return;
    L13:
        if (r7.length <= 0) goto L18;
        int r63 = r7.length;
        this.numberOfExceptions = r63;
        this.exceptionIndexTable = new int[r63];
    L16:
        if (r42 >= this.numberOfExceptions) goto L19;
        this.exceptionIndexTable[r42] = r2.addConstantClass(r7[r42]).index;
        r42 = r42 + 1;
        goto L16
    L9:
        r62 = r2.addConstantUtf8(r6);
        goto L10
    L5:
        r0 = r3;
        goto L6
    }

    private void addSuccessorToCurrentBasicBlock(int r3, Label r4) {
        Label r2 = this.currentBasicBlock;
        r2.outgoingEdges = new Edge(r3, r4, r2.outgoingEdges);
    }

    private void computeAllFrames() {
        Handler r0 = this.firstHandler;
    L3:
        String r1 = "java/lang/Throwable";
        if (r0 == null) goto L13;
        String r2 = r0.catchTypeDescriptor;
        if (r2 == null) goto L9;
        r1 = r2;
    L9:
        int r12 = Frame.getAbstractTypeFromInternalName(this.symbolTable, r1);
        Label r22 = r0.handlerPc.getCanonicalInstance();
        r22.flags = (short) (r22.flags | 2);
        Label r3 = r0.startPc.getCanonicalInstance();
        Label r4 = r0.endPc.getCanonicalInstance();
    L10:
        if (r3 == r4) goto L12;
        r3.outgoingEdges = new Edge(r12, r22, r3.outgoingEdges);
        r3 = r3.nextBasicBlock;
        goto L10
    L12:
        r0 = r0.nextHandler;
        goto L3
    L13:
        Frame r02 = this.firstBasicBlock.frame;
        r02.setInputFrameFromDescriptor(this.symbolTable, this.accessFlags, this.descriptor, this.maxLocals);
        r02.accept(this);
        Label r03 = this.firstBasicBlock;
        r03.nextListElement = Label.EMPTY_LIST;
        int r32 = 0;
    L15:
        if (r03 == Label.EMPTY_LIST) goto L28;
        Label r42 = r03.nextListElement;
        r03.nextListElement = null;
        r03.flags = (short) (r03.flags | 8);
        int r5 = r03.frame.getInputStackSize() + r03.outputStackMax;
        if (r5 <= r32) goto L19;
        r32 = r5;
    L19:
        Edge r52 = r03.outgoingEdges;
    L20:
        if (r52 == null) goto L27;
        Label r6 = r52.successor.getCanonicalInstance();
        if (r03.frame.merge(this.symbolTable, r6.frame, r52.info) == false) goto L26;
        if (r6.nextListElement != null) goto L26;
        r6.nextListElement = r42;
        r42 = r6;
    L26:
        r52 = r52.nextEdge;
        goto L20
    L27:
        r03 = r42;
        goto L15
    L28:
        Label r04 = this.firstBasicBlock;
    L29:
        if (r04 == null) goto L47;
        if ((r04.flags & 10) != 10) goto L34;
        r04.frame.accept(this);
    L34:
        if ((r04.flags & 8) != 0) goto L46;
        Label r43 = r04.nextBasicBlock;
        int r53 = r04.bytecodeOffset;
        if (r43 != null) goto L38;
        int r62 = this.code.length;
    L39:
        int r63 = r62 - 1;
        if (r63 < r53) goto L46;
        int r8 = r53;
    L42:
        ByteVector r9 = this.code;
        if (r8 >= r63) goto L45;
        r9.data[r8] = 0;
        r8 = r8 + 1;
        goto L42
    L45:
        r9.data[r63] = -65;
        this.currentFrame[visitFrameStart(r53, 0, 1)] = Frame.getAbstractTypeFromInternalName(this.symbolTable, "java/lang/Throwable");
        visitFrameEnd();
        this.firstHandler = Handler.removeRange(this.firstHandler, r04, r43);
        r32 = Math.max(r32, 1);
        goto L46
    L38:
        r62 = r43.bytecodeOffset;
    L46:
        r04 = r04.nextBasicBlock;
        goto L29
    L47:
        this.maxStack = r32;
    }

    private void computeMaxStackAndLocal() {
        Handler r0 = this.firstHandler;
    L4:
        if (r0 == null) goto L14;
        Label r2 = r0.handlerPc;
        Label r3 = r0.startPc;
        Label r4 = r0.endPc;
    L6:
        if (r3 == r4) goto L12;
        int r5 = r3.flags & 16;
        Edge r6 = r3.outgoingEdges;
        if (r5 != 0) goto L10;
        r3.outgoingEdges = new Edge(Integer.MAX_VALUE, r2, r6);
    L11:
        r3 = r3.nextBasicBlock;
        goto L6
    L10:
        Edge r52 = r6.nextEdge;
        r52.nextEdge = new Edge(Integer.MAX_VALUE, r2, r52.nextEdge);
        goto L11
    L12:
        r0 = r0.nextHandler;
        goto L4
    L14:
        if (this.hasSubroutines == false) goto L33;
        this.firstBasicBlock.markSubroutine(1);
        short r02 = 1;
        short r32 = 1;
    L16:
        Label r42 = this.firstBasicBlock;
        if (r02 > r32) goto L28;
    L18:
        if (r42 == null) goto L27;
        if ((r42.flags & 16) == 0) goto L26;
        if (r42.subroutineId != r02) goto L26;
        Label r53 = r42.outgoingEdges.nextEdge.successor;
        if (r53.subroutineId != 0) goto L26;
        r32 = (short) (r32 + 1);
        r53.markSubroutine(r32);
    L26:
        r42 = r42.nextBasicBlock;
        goto L18
    L27:
        r02 = (short) (r02 + 1);
    L28:
        if (r42 == null) goto L33;
        if ((r42.flags & 16) == 0) goto L32;
        r42.outgoingEdges.nextEdge.successor.addSubroutineRetSuccessors(r42);
    L32:
        r42 = r42.nextBasicBlock;
    L33:
        Label r03 = this.firstBasicBlock;
        r03.nextListElement = Label.EMPTY_LIST;
        int r33 = this.maxStack;
    L35:
        if (r03 == Label.EMPTY_LIST) goto L52;
        Label r43 = r03.nextListElement;
        short r54 = r03.inputStackSize;
        int r62 = r03.outputStackMax + r54;
        if (r62 <= r33) goto L39;
        r33 = r62;
    L39:
        Edge r63 = r03.outgoingEdges;
        if ((r03.flags & 16) == 0) goto L42;
        r63 = r63.nextEdge;
    L42:
        r03 = r43;
    L43:
        if (r63 == null) goto L35;
        Label r44 = r63.successor;
        if (r44.nextListElement != null) goto L51;
        int r7 = r63.info;
        if (r7 != Integer.MAX_VALUE) goto L49;
        int r72 = 1;
    L50:
        r44.inputStackSize = (short) r72;
        r44.nextListElement = r03;
        r03 = r44;
        goto L51
    L49:
        r72 = r7 + r54;
    L51:
        r63 = r63.nextEdge;
        goto L43
    L52:
        this.maxStack = r33;
    }

    private void endCurrentBasicBlockWithNoSuccessor() {
        int r0 = this.compute;
        if (r0 != 4) goto L7;
        Label r02 = new Label();
        r02.frame = new Frame(r02);
        ByteVector r1 = this.code;
        r02.resolve(r1.data, r1.length);
        this.lastBasicBlock.nextBasicBlock = r02;
        this.lastBasicBlock = r02;
        this.currentBasicBlock = null;
        return;
    L7:
        if (r0 != 1) goto L10;
        Label r03 = this.currentBasicBlock;
        r03.outputStackMax = (short) this.maxRelativeStackSize;
        this.currentBasicBlock = null;
        return;
    }

    private void putAbstractTypes(int r4, int r5) {
    L2:
        if (r4 >= r5) goto L4;
        Frame.putAbstractType(this.symbolTable, this.currentFrame[r4], this.stackMapTableEntries);
        r4 = r4 + 1;
        goto L2
    }

    private void putFrame() {
        int[] r1 = this.currentFrame;
        int r3 = r1[1];
        int r12 = r1[2];
        int r6 = 0;
        int r7 = 3;
        if (this.symbolTable.getMajorVersion() >= 50) goto L6;
        this.stackMapTableEntries.putShort(this.currentFrame[0]).putShort(r3);
        int r32 = r3 + 3;
        putAbstractTypes(3, r32);
        this.stackMapTableEntries.putShort(r12);
        putAbstractTypes(r32, r12 + r32);
        return;
    L6:
        int r4 = this.stackMapTableNumberOfEntries;
        int[] r5 = this.currentFrame;
        if (r4 != 0) goto L9;
        int r42 = r5[0];
    L10:
        int r52 = this.previousFrame[1];
        int r8 = r3 - r52;
        if (r12 != 0) goto L19;
        switch(r8) {
            case -3: goto L18;
            case -2: goto L18;
            case -1: goto L18;
            case 0: goto L15;
            case 1: goto L14;
            case 2: goto L14;
            case 3: goto L14;
            default: goto L25;
        };
    L14:
        char r2 = 252;
    L26:
        if (r2 == 255) goto L34;
        int r15 = 3;
    L28:
        if (r6 >= r52) goto L34;
        if (r6 >= r3) goto L34;
        int r16 = r7;
        if (this.currentFrame[r15] != this.previousFrame[r15]) goto L32;
        r15 = r15 + 1;
        r6 = r6 + 1;
        r7 = r16;
        goto L28
    L32:
        r2 = 255;
    L35:
        if (r2 == 0) goto L54;
        if (r2 == '@') goto L52;
        if (r2 == 247) goto L50;
        if (r2 == 248) goto L48;
        if (r2 == 251) goto L46;
        ByteVector r62 = this.stackMapTableEntries;
        if (r2 == 252) goto L44;
        r62.putByte(255).putShort(r42).putShort(r3);
        int r33 = r3 + 3;
        putAbstractTypes(r16, r33);
        this.stackMapTableEntries.putShort(r12);
        putAbstractTypes(r33, r12 + r33);
        return;
    L44:
        int r22 = r16;
        r62.putByte(r8 + com.android.dx.io.Opcodes.INVOKE_POLYMORPHIC_RANGE).putShort(r42);
        putAbstractTypes(r52 + r22, r3 + r22);
        return;
    L46:
        this.stackMapTableEntries.putByte(com.android.dx.io.Opcodes.INVOKE_POLYMORPHIC_RANGE).putShort(r42);
        return;
    L48:
        this.stackMapTableEntries.putByte(r8 + com.android.dx.io.Opcodes.INVOKE_POLYMORPHIC_RANGE).putShort(r42);
        return;
    L50:
        this.stackMapTableEntries.putByte(247).putShort(r42);
        putAbstractTypes(r3 + 3, r3 + 4);
        return;
    L52:
        this.stackMapTableEntries.putByte(r42 + 64);
        putAbstractTypes(r3 + 3, r3 + 4);
        return;
    L54:
        this.stackMapTableEntries.putByte(r42);
        return;
    L34:
        r16 = r7;
        goto L35
    L15:
        if (r42 >= 64) goto L17;
        r2 = 0;
        goto L26
    L17:
        r2 = 251;
        goto L26
    L18:
        r2 = 248;
    L25:
        r2 = 255;
        goto L26
    L19:
        if (r8 != 0) goto L25;
        if (r12 != 1) goto L25;
        if (r42 >= 63) goto L24;
        r2 = '@';
        goto L26
    L24:
        r2 = 247;
        goto L26
    L9:
        r42 = (r5[0] - this.previousFrame[0]) - 1;
        goto L10
    }

    private void putFrameType(Object r3) {
        if ((r3 instanceof Integer) == false) goto L6;
        this.stackMapTableEntries.putByte(((Integer) r3).intValue());
        return;
    L6:
        boolean r0 = r3 instanceof String;
        ByteVector r1 = this.stackMapTableEntries;
        if (r0 == false) goto L10;
        r1.putByte(7).putShort(this.symbolTable.addConstantClass((String) r3).index);
        return;
    L10:
        r1.putByte(8).putShort(((Label) r3).bytecodeOffset);
    }

    private void visitSwitchInsn(Label r5, Label[] r6) {
        Label r0 = this.currentBasicBlock;
        if (r0 == null) goto L18;
        int r1 = this.compute;
        int r3 = 0;
        if (r1 != 4) goto L10;
        r0.frame.execute(171, 0, null, null);
        addSuccessorToCurrentBasicBlock(0, r5);
        Label r52 = r5.getCanonicalInstance();
        r52.flags = (short) (r52.flags | 2);
        int r53 = r6.length;
        int r02 = 0;
    L7:
        if (r02 >= r53) goto L14;
        Label r12 = r6[r02];
        addSuccessorToCurrentBasicBlock(0, r12);
        Label r13 = r12.getCanonicalInstance();
        r13.flags = (short) (r13.flags | 2);
        r02 = r02 + 1;
    L14:
        endCurrentBasicBlockWithNoSuccessor();
        return;
    L10:
        if (r1 != 1) goto L14;
        int r14 = this.relativeStackSize - 1;
        this.relativeStackSize = r14;
        addSuccessorToCurrentBasicBlock(r14, r5);
        int r54 = r6.length;
    L12:
        if (r3 >= r54) goto L14;
        addSuccessorToCurrentBasicBlock(this.relativeStackSize, r6[r3]);
        r3 = r3 + 1;
        goto L12
    }

    public boolean canCopyMethodAttributes(ClassReader r3, boolean r4, boolean r5, int r6, int r7, int r8) {
        if (r3 == this.symbolTable.getSource()) goto L5;
    L36:
        return false;
    L5:
        if (r6 != this.descriptorIndex) goto L36;
        if (r7 != this.signatureIndex) goto L36;
        if ((this.accessFlags & 131072) == 0) goto L11;
        boolean r62 = true;
    L12:
        if (r5 != r62) goto L36;
        if (this.symbolTable.getMajorVersion() < 49) goto L17;
    L19:
        boolean r52 = false;
    L20:
        if (r4 == r52) goto L22;
        return false;
    L22:
        if (r8 != 0) goto L27;
        if (this.numberOfExceptions == 0) goto L35;
        return false;
    L35:
        return true;
    L27:
        if (r3.readUnsignedShort(r8) != this.numberOfExceptions) goto L35;
        int r82 = r8 + 2;
        int r42 = 0;
    L30:
        if (r42 >= this.numberOfExceptions) goto L35;
        if (r3.readUnsignedShort(r82) != this.exceptionIndexTable[r42]) goto L33;
        r82 = r82 + 2;
        r42 = r42 + 1;
        goto L30
    L33:
        return false;
    L17:
        if ((this.accessFlags & 4096) == 0) goto L19;
        r52 = true;
        goto L20
    L11:
        r62 = false;
        goto L12
    }

    public final void collectAttributePrototypes(Attribute.Set r2) {
        r2.addAttributes(this.firstAttribute);
        r2.addAttributes(this.firstCodeAttribute);
    }

    public int computeMethodInfoSize() {
        if (this.sourceOffset != 0) goto L5;
        int r0 = this.code.length;
        if (r0 <= 0) goto L41;
        SymbolTable r2 = this.symbolTable;
        if (r0 > 65535) goto L40;
        r2.addConstantUtf8(AttCode.ATTRIBUTE_NAME);
        int r02 = ((this.code.length + 16) + Handler.getExceptionTableSize(this.firstHandler)) + 8;
        if (this.stackMapTableEntries == null) goto L22;
        if (this.symbolTable.getMajorVersion() < 50) goto L15;
        boolean r22 = true;
    L16:
        SymbolTable r3 = this.symbolTable;
        if (r22 == false) goto L19;
        String r23 = "StackMapTable";
    L20:
        r3.addConstantUtf8(r23);
        r02 = r02 + (this.stackMapTableEntries.length + 8);
        goto L22
    L19:
        r23 = "StackMap";
        goto L20
    L15:
        r22 = false;
    L22:
        if (this.lineNumberTable == null) goto L25;
        this.symbolTable.addConstantUtf8(AttLineNumberTable.ATTRIBUTE_NAME);
        r02 = r02 + (this.lineNumberTable.length + 8);
    L25:
        if (this.localVariableTable == null) goto L28;
        this.symbolTable.addConstantUtf8(AttLocalVariableTable.ATTRIBUTE_NAME);
        r02 = r02 + (this.localVariableTable.length + 8);
    L28:
        if (this.localVariableTypeTable == null) goto L30;
        this.symbolTable.addConstantUtf8(AttLocalVariableTypeTable.ATTRIBUTE_NAME);
        r02 = r02 + (this.localVariableTypeTable.length + 8);
    L30:
        AnnotationWriter r24 = this.lastCodeRuntimeVisibleTypeAnnotation;
        if (r24 == null) goto L33;
        r02 = r02 + r24.computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
    L33:
        AnnotationWriter r25 = this.lastCodeRuntimeInvisibleTypeAnnotation;
        if (r25 == null) goto L36;
        r02 = r02 + r25.computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
    L36:
        Attribute r26 = this.firstCodeAttribute;
        if (r26 == null) goto L43;
        SymbolTable r32 = this.symbolTable;
        ByteVector r4 = this.code;
        r02 = r02 + r26.computeAttributesSize(r32, r4.data, r4.length, this.maxStack, this.maxLocals);
    L43:
        if (this.numberOfExceptions <= 0) goto L45;
        this.symbolTable.addConstantUtf8(AttExceptions.ATTRIBUTE_NAME);
        r02 = r02 + ((this.numberOfExceptions * 2) + 8);
    L45:
        int r03 = (r02 + Attribute.computeAttributesSize(this.symbolTable, this.accessFlags, this.signatureIndex)) + AnnotationWriter.computeAnnotationsSize(this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation);
        AnnotationWriter[] r1 = this.lastRuntimeVisibleParameterAnnotations;
        if (r1 == null) goto L51;
        int r27 = this.visibleAnnotableParameterCount;
        if (r27 != 0) goto L50;
        r27 = r1.length;
    L50:
        r03 = r03 + AnnotationWriter.computeParameterAnnotationsSize(AttRuntimeVisibleParameterAnnotations.ATTRIBUTE_NAME, r1, r27);
    L51:
        AnnotationWriter[] r12 = this.lastRuntimeInvisibleParameterAnnotations;
        if (r12 == null) goto L58;
        int r28 = this.invisibleAnnotableParameterCount;
        if (r28 != 0) goto L56;
        r28 = r12.length;
    L56:
        r03 = r03 + AnnotationWriter.computeParameterAnnotationsSize(AttRuntimeInvisibleParameterAnnotations.ATTRIBUTE_NAME, r12, r28);
    L58:
        if (this.defaultValue == null) goto L61;
        this.symbolTable.addConstantUtf8(AttAnnotationDefault.ATTRIBUTE_NAME);
        r03 = r03 + (this.defaultValue.length + 6);
    L61:
        if (this.parameters == null) goto L63;
        this.symbolTable.addConstantUtf8("MethodParameters");
        r03 = r03 + (this.parameters.length + 7);
    L63:
        Attribute r13 = this.firstAttribute;
        if (r13 != null) goto L66;
        return r03;
    L66:
        return r03 + r13.computeAttributesSize(this.symbolTable);
    L40:
        throw new MethodTooLargeException(r2.getClassName(), this.name, this.descriptor, this.code.length);
    L41:
        r02 = 8;
        goto L43
    L5:
        return this.sourceLength + 6;
    }

    public boolean hasAsmInstructions() {
        return this.hasAsmInstructions;
    }

    public boolean hasFrames() {
        if (this.stackMapTableNumberOfEntries <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void putMethodInfo(ByteVector r17) {
        ByteVector r6 = r17;
        if (this.symbolTable.getMajorVersion() >= 49) goto L5;
        boolean r1 = true;
    L6:
        if (r1 == false) goto L8;
        int r2 = 4096;
    L9:
        r6.putShort((~r2) & this.accessFlags).putShort(this.nameIndex).putShort(this.descriptorIndex);
        if (this.sourceOffset == 0) goto L14;
        r6.putByteArray(this.symbolTable.getSource().classFileBuffer, this.sourceOffset, this.sourceLength);
        return;
    L14:
        if (this.code.length <= 0) goto L16;
        int r22 = 1;
    L18:
        if (this.numberOfExceptions <= 0) goto L20;
        r22 = r22 + 1;
    L20:
        int r3 = this.accessFlags;
        if ((r3 & 4096) == 0) goto L25;
        if (r1 == false) goto L25;
        r22 = r22 + 1;
    L25:
        if (this.signatureIndex == 0) goto L28;
        r22 = r22 + 1;
    L28:
        if ((131072 & r3) == 0) goto L31;
        r22 = r22 + 1;
    L31:
        if (this.lastRuntimeVisibleAnnotation == null) goto L34;
        r22 = r22 + 1;
    L34:
        if (this.lastRuntimeInvisibleAnnotation == null) goto L37;
        r22 = r22 + 1;
    L37:
        if (this.lastRuntimeVisibleParameterAnnotations == null) goto L40;
        r22 = r22 + 1;
    L40:
        if (this.lastRuntimeInvisibleParameterAnnotations == null) goto L43;
        r22 = r22 + 1;
    L43:
        if (this.lastRuntimeVisibleTypeAnnotation == null) goto L46;
        r22 = r22 + 1;
    L46:
        if (this.lastRuntimeInvisibleTypeAnnotation == null) goto L49;
        r22 = r22 + 1;
    L49:
        if (this.defaultValue == null) goto L52;
        r22 = r22 + 1;
    L52:
        if (this.parameters == null) goto L54;
        r22 = r22 + 1;
    L54:
        Attribute r12 = this.firstAttribute;
        if (r12 == null) goto L57;
        r22 = r22 + r12.getAttributeCount();
    L57:
        r6.putShort(r22);
        int r13 = this.code.length;
        if (r13 <= 0) goto L111;
        int r14 = (r13 + 10) + Handler.getExceptionTableSize(this.firstHandler);
        ByteVector r23 = this.stackMapTableEntries;
        if (r23 == null) goto L62;
        r14 = r14 + (r23.length + 8);
        int r24 = 1;
    L63:
        ByteVector r32 = this.lineNumberTable;
        if (r32 == null) goto L66;
        r14 = r14 + (r32.length + 8);
        r24 = r24 + 1;
    L66:
        ByteVector r33 = this.localVariableTable;
        if (r33 == null) goto L69;
        r14 = r14 + (r33.length + 8);
        r24 = r24 + 1;
    L69:
        ByteVector r34 = this.localVariableTypeTable;
        if (r34 == null) goto L72;
        r14 = r14 + (r34.length + 8);
        r24 = r24 + 1;
    L72:
        AnnotationWriter r35 = this.lastCodeRuntimeVisibleTypeAnnotation;
        if (r35 == null) goto L75;
        r14 = r14 + r35.computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
        r24 = r24 + 1;
    L75:
        AnnotationWriter r36 = this.lastCodeRuntimeInvisibleTypeAnnotation;
        if (r36 == null) goto L78;
        r14 = r14 + r36.computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
        r24 = r24 + 1;
    L78:
        Attribute r10 = this.firstCodeAttribute;
        if (r10 == null) goto L81;
        SymbolTable r11 = this.symbolTable;
        ByteVector r37 = this.code;
        r14 = r14 + r10.computeAttributesSize(r11, r37.data, r37.length, this.maxStack, this.maxLocals);
        r24 = r24 + this.firstCodeAttribute.getAttributeCount();
    L81:
        ByteVector r15 = r6.putShort(this.symbolTable.addConstantUtf8(AttCode.ATTRIBUTE_NAME)).putInt(r14).putShort(this.maxStack).putShort(this.maxLocals).putInt(this.code.length);
        ByteVector r38 = this.code;
        r15.putByteArray(r38.data, 0, r38.length);
        Handler.putExceptionTable(this.firstHandler, r6);
        r6.putShort(r24);
        if (this.stackMapTableEntries == null) goto L93;
        if (this.symbolTable.getMajorVersion() < 50) goto L86;
        boolean r16 = true;
    L87:
        SymbolTable r25 = this.symbolTable;
        if (r16 == false) goto L90;
        String r18 = "StackMapTable";
    L91:
        ByteVector r19 = r6.putShort(r25.addConstantUtf8(r18)).putInt(this.stackMapTableEntries.length + 2).putShort(this.stackMapTableNumberOfEntries);
        ByteVector r26 = this.stackMapTableEntries;
        r19.putByteArray(r26.data, 0, r26.length);
        goto L93
    L90:
        r18 = "StackMap";
        goto L91
    L86:
        r16 = false;
    L93:
        if (this.lineNumberTable == null) goto L96;
        ByteVector r110 = r6.putShort(this.symbolTable.addConstantUtf8(AttLineNumberTable.ATTRIBUTE_NAME)).putInt(this.lineNumberTable.length + 2).putShort(this.lineNumberTableLength);
        ByteVector r27 = this.lineNumberTable;
        r110.putByteArray(r27.data, 0, r27.length);
    L96:
        if (this.localVariableTable == null) goto L99;
        ByteVector r111 = r6.putShort(this.symbolTable.addConstantUtf8(AttLocalVariableTable.ATTRIBUTE_NAME)).putInt(this.localVariableTable.length + 2).putShort(this.localVariableTableLength);
        ByteVector r28 = this.localVariableTable;
        r111.putByteArray(r28.data, 0, r28.length);
    L99:
        if (this.localVariableTypeTable == null) goto L101;
        ByteVector r112 = r6.putShort(this.symbolTable.addConstantUtf8(AttLocalVariableTypeTable.ATTRIBUTE_NAME)).putInt(this.localVariableTypeTable.length + 2).putShort(this.localVariableTypeTableLength);
        ByteVector r29 = this.localVariableTypeTable;
        r112.putByteArray(r29.data, 0, r29.length);
    L101:
        AnnotationWriter r113 = this.lastCodeRuntimeVisibleTypeAnnotation;
        if (r113 == null) goto L104;
        r113.putAnnotations(this.symbolTable.addConstantUtf8("RuntimeVisibleTypeAnnotations"), r6);
    L104:
        AnnotationWriter r114 = this.lastCodeRuntimeInvisibleTypeAnnotation;
        if (r114 == null) goto L107;
        r114.putAnnotations(this.symbolTable.addConstantUtf8("RuntimeInvisibleTypeAnnotations"), r6);
    L107:
        Attribute r115 = this.firstCodeAttribute;
        if (r115 == null) goto L111;
        SymbolTable r210 = this.symbolTable;
        ByteVector r39 = this.code;
        r115.putAttributes(r210, r39.data, r39.length, this.maxStack, this.maxLocals, r17);
        r6 = r17;
        goto L111
    L62:
        r24 = 0;
    L111:
        if (this.numberOfExceptions <= 0) goto L115;
        r6.putShort(this.symbolTable.addConstantUtf8(AttExceptions.ATTRIBUTE_NAME)).putInt((this.numberOfExceptions * 2) + 2).putShort(this.numberOfExceptions);
        int[] r116 = this.exceptionIndexTable;
        int r211 = r116.length;
        int r310 = 0;
    L113:
        if (r310 >= r211) goto L115;
        r6.putShort(r116[r310]);
        r310 = r310 + 1;
    L115:
        Attribute.putAttributes(this.symbolTable, this.accessFlags, this.signatureIndex, r6);
        AnnotationWriter.putAnnotations(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, r6);
        if (this.lastRuntimeVisibleParameterAnnotations == null) goto L122;
        int r117 = this.symbolTable.addConstantUtf8(AttRuntimeVisibleParameterAnnotations.ATTRIBUTE_NAME);
        AnnotationWriter[] r212 = this.lastRuntimeVisibleParameterAnnotations;
        int r311 = this.visibleAnnotableParameterCount;
        if (r311 != 0) goto L120;
        r311 = r212.length;
    L120:
        AnnotationWriter.putParameterAnnotations(r117, r212, r311, r6);
    L122:
        if (this.lastRuntimeInvisibleParameterAnnotations == null) goto L128;
        int r118 = this.symbolTable.addConstantUtf8(AttRuntimeInvisibleParameterAnnotations.ATTRIBUTE_NAME);
        AnnotationWriter[] r213 = this.lastRuntimeInvisibleParameterAnnotations;
        int r312 = this.invisibleAnnotableParameterCount;
        if (r312 != 0) goto L126;
        r312 = r213.length;
    L126:
        AnnotationWriter.putParameterAnnotations(r118, r213, r312, r6);
    L128:
        if (this.defaultValue == null) goto L131;
        ByteVector r119 = r6.putShort(this.symbolTable.addConstantUtf8(AttAnnotationDefault.ATTRIBUTE_NAME)).putInt(this.defaultValue.length);
        ByteVector r214 = this.defaultValue;
        r119.putByteArray(r214.data, 0, r214.length);
    L131:
        if (this.parameters == null) goto L133;
        ByteVector r120 = r6.putShort(this.symbolTable.addConstantUtf8("MethodParameters")).putInt(this.parameters.length + 1).putByte(this.parametersCount);
        ByteVector r215 = this.parameters;
        r120.putByteArray(r215.data, 0, r215.length);
    L133:
        Attribute r121 = this.firstAttribute;
        if (r121 == null) goto L138;
        r121.putAttributes(this.symbolTable, r6);
        return;
    L138:
        return;
    L16:
        r22 = 0;
        goto L18
    L8:
        r2 = 0;
        goto L9
    L5:
        r1 = false;
        goto L6
    }

    public void setMethodAttributesSource(int r1, int r2) {
        this.sourceOffset = r1 + 6;
        this.sourceLength = r2 - 6;
    }

    public void visitAbstractType(int r1, int r2) {
        this.currentFrame[r1] = r2;
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitAnnotableParameterCount(int r1, boolean r2) {
        if (r2 == false) goto L5;
        this.visibleAnnotableParameterCount = r1;
        return;
    L5:
        this.invisibleAnnotableParameterCount = r1;
    }

    @Override // org.mvel2.asm.MethodVisitor
    public AnnotationVisitor visitAnnotation(String r2, boolean r3) {
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

    @Override // org.mvel2.asm.MethodVisitor
    public AnnotationVisitor visitAnnotationDefault() {
        ByteVector r0 = new ByteVector();
        this.defaultValue = r0;
        return new AnnotationWriter(this.symbolTable, false, r0, null);
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitAttribute(Attribute r2) {
        if (r2.isCodeAttribute() == false) goto L6;
        r2.nextAttribute = this.firstCodeAttribute;
        this.firstCodeAttribute = r2;
        return;
    L6:
        r2.nextAttribute = this.firstAttribute;
        this.firstAttribute = r2;
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitCode() {
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitEnd() {
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitFieldInsn(int r4, String r5, String r6, String r7) {
        this.lastBytecodeOffset = this.code.length;
        Symbol r52 = this.symbolTable.addConstantFieldref(r5, r6, r7);
        this.code.put12(r4, r52.index);
        Label r62 = this.currentBasicBlock;
        if (r62 == null) goto L38;
        int r0 = this.compute;
        int r2 = 0;
        if (r0 != 4) goto L7;
    L36:
        r62.frame.execute(r4, 0, r52, this.symbolTable);
        return;
    L7:
        if (r0 == 3) goto L36;
        char r53 = r7.charAt(0);
        int r63 = -2;
        int r72 = 1;
        switch(r4) {
            case 178: goto L26;
            case 179: goto L21;
            case 180: goto L16;
            default: goto L11;
        };
    L11:
        int r42 = this.relativeStackSize;
        if (r53 == 'D') goto L14;
        if (r53 == 'J') goto L14;
    L15:
        int r43 = r42 + r63;
    L32:
        if (r43 <= this.maxRelativeStackSize) goto L34;
        this.maxRelativeStackSize = r43;
    L34:
        this.relativeStackSize = r43;
        return;
    L14:
        r63 = -3;
        goto L15
    L16:
        int r44 = this.relativeStackSize;
        if (r53 == 'D') goto L19;
        if (r53 == 'J') goto L19;
    L20:
        r43 = r44 + r2;
    L19:
        r2 = 1;
        goto L20
    L21:
        r42 = this.relativeStackSize;
        if (r53 == 'D') goto L15;
        if (r53 == 'J') goto L15;
        r63 = -1;
        goto L15
    L26:
        int r45 = this.relativeStackSize;
        if (r53 == 'D') goto L29;
        if (r53 == 'J') goto L29;
    L30:
        r43 = r45 + r72;
    L29:
        r72 = 2;
        goto L30
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitFrame(int r11, int r12, Object[] r13, int r14, Object[] r15) {
        int r0 = this.compute;
        if (r0 == 4) goto L89;
        int r7 = 0;
        if (r0 != 3) goto L13;
        Label r02 = this.currentBasicBlock;
        Frame r1 = r02.frame;
        if (r1 != null) goto L10;
        r02.frame = new CurrentFrame(r02);
        this.currentBasicBlock.frame.setInputFrameFromDescriptor(this.symbolTable, this.accessFlags, this.descriptor, r12);
        this.currentBasicBlock.frame.accept(this);
    L65:
        if (this.compute != 2) goto L77;
        this.relativeStackSize = r14;
    L67:
        if (r7 >= r14) goto L74;
        Object r112 = r15[r7];
        if (r112 != Opcodes.LONG) goto L71;
    L72:
        this.relativeStackSize++;
    L73:
        r7 = r7 + 1;
        goto L67
    L71:
        if (r112 != Opcodes.DOUBLE) goto L73;
    L74:
        int r113 = this.relativeStackSize;
        if (r113 <= this.maxRelativeStackSize) goto L77;
        this.maxRelativeStackSize = r113;
    L77:
        this.maxStack = Math.max(this.maxStack, r14);
        this.maxLocals = Math.max(this.maxLocals, this.currentLocals);
        return;
    L10:
        if (r11 != (-1)) goto L12;
        r1.setInputFrameFromApiFormat(this.symbolTable, r12, r13, r14, r15);
    L12:
        this.currentBasicBlock.frame.accept(this);
        goto L65
    L13:
        if (r11 != (-1)) goto L25;
        if (this.previousFrame != null) goto L17;
        int r114 = Type.getArgumentsAndReturnSizes(this.descriptor) >> 2;
        Frame r16 = new Frame(new Label());
        r16.setInputFrameFromDescriptor(this.symbolTable, this.accessFlags, this.descriptor, r114);
        r16.accept(this);
    L17:
        this.currentLocals = r12;
        int r115 = visitFrameStart(this.code.length, r12, r14);
        int r17 = 0;
    L18:
        if (r17 >= r12) goto L20;
        this.currentFrame[r115] = Frame.getAbstractTypeFromApiFormat(this.symbolTable, r13[r17]);
        r17 = r17 + 1;
        r115 = r115 + 1;
        goto L18
    L20:
        int r122 = 0;
    L21:
        if (r122 >= r14) goto L23;
        this.currentFrame[r115] = Frame.getAbstractTypeFromApiFormat(this.symbolTable, r15[r122]);
        r122 = r122 + 1;
        r115 = r115 + 1;
        goto L21
    L23:
        visitFrameEnd();
        goto L65
    L25:
        if (this.symbolTable.getMajorVersion() >= 50) goto L27;
        f$$ExternalSyntheticBUOutline1.m("Class versions V1_5 or less must use F_NEW frames.");
        return;
    L27:
        if (this.stackMapTableEntries != null) goto L29;
        this.stackMapTableEntries = new ByteVector();
        int r2 = this.code.length;
    L35:
        if (r11 == 0) goto L57;
        if (r11 != 1) goto L38;
        this.currentLocals += r12;
        this.stackMapTableEntries.putByte(r12 + com.android.dx.io.Opcodes.INVOKE_POLYMORPHIC_RANGE).putShort(r2);
        int r116 = 0;
    L55:
        if (r116 >= r12) goto L63;
        putFrameType(r13[r116]);
        r116 = r116 + 1;
    L63:
        this.previousFrameOffset = this.code.length;
        this.stackMapTableNumberOfEntries++;
        goto L65
    L38:
        if (r11 != 2) goto L40;
        this.currentLocals -= r12;
        this.stackMapTableEntries.putByte(com.android.dx.io.Opcodes.INVOKE_POLYMORPHIC_RANGE - r12).putShort(r2);
        goto L63
    L40:
        if (r11 == 3) goto L49;
        if (r11 != 4) goto L47;
        ByteVector r117 = this.stackMapTableEntries;
        if (r2 >= 64) goto L45;
        r117.putByte(r2 + 64);
    L46:
        putFrameType(r15[0]);
        goto L63
    L45:
        r117.putByte(247).putShort(r2);
        goto L46
    L47:
        Segment$$ExternalSyntheticBUOutline0.m();
        return;
    L49:
        ByteVector r118 = this.stackMapTableEntries;
        if (r2 >= 64) goto L52;
        r118.putByte(r2);
        goto L63
    L52:
        r118.putByte(com.android.dx.io.Opcodes.INVOKE_POLYMORPHIC_RANGE).putShort(r2);
        goto L63
    L57:
        this.currentLocals = r12;
        this.stackMapTableEntries.putByte(255).putShort(r2).putShort(r12);
        int r119 = 0;
    L58:
        if (r119 >= r12) goto L60;
        putFrameType(r13[r119]);
        r119 = r119 + 1;
        goto L58
    L60:
        this.stackMapTableEntries.putShort(r14);
        int r1110 = 0;
    L61:
        if (r1110 >= r14) goto L63;
        putFrameType(r15[r1110]);
        r1110 = r1110 + 1;
        goto L61
    L29:
        r2 = (this.code.length - this.previousFrameOffset) - 1;
        if (r2 >= 0) goto L35;
        if (r11 != 3) goto L33;
        return;
    L33:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    }

    public void visitFrameEnd() {
        if (this.previousFrame != null) goto L5;
    L8:
        this.previousFrame = this.currentFrame;
        this.currentFrame = null;
        return;
    L5:
        if (this.stackMapTableEntries != null) goto L7;
        this.stackMapTableEntries = new ByteVector();
    L7:
        putFrame();
        this.stackMapTableNumberOfEntries++;
        goto L8
    }

    public int visitFrameStart(int r3, int r4, int r5) {
        int r0 = (r4 + 3) + r5;
        int[] r1 = this.currentFrame;
        if (r1 != null) goto L5;
    L6:
        this.currentFrame = new int[r0];
    L7:
        int[] r2 = this.currentFrame;
        r2[0] = r3;
        r2[1] = r4;
        r2[2] = r5;
        return 3;
    L5:
        if (r1.length >= r0) goto L7;
        goto L6
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitIincInsn(int r4, int r5) {
        ByteVector r0 = this.code;
        this.lastBytecodeOffset = r0.length;
        if (r4 <= 255) goto L5;
    L10:
        r0.putByte(196).put12(132, r4).putShort(r5);
    L11:
        Label r52 = this.currentBasicBlock;
        if (r52 == null) goto L19;
        int r02 = this.compute;
        if (r02 != 4) goto L16;
    L17:
        r52.frame.execute(132, r4, null, null);
        goto L19
    L16:
        if (r02 == 3) goto L17;
    L19:
        if (this.compute == 0) goto L24;
        int r42 = r4 + 1;
        if (r42 <= this.maxLocals) goto L25;
        this.maxLocals = r42;
        return;
    L25:
        return;
    L24:
        return;
    L5:
        if (r5 > 127) goto L10;
        if (r5 < (-128)) goto L10;
        r0.putByte(132).put11(r4, r5);
        goto L11
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitInsn(int r4) {
        ByteVector r0 = this.code;
        this.lastBytecodeOffset = r0.length;
        r0.putByte(r4);
        Label r02 = this.currentBasicBlock;
        if (r02 == null) goto L22;
        int r1 = this.compute;
        if (r1 != 4) goto L7;
    L13:
        r02.frame.execute(r4, 0, null, null);
    L15:
        if (r4 < 172) goto L19;
        if (r4 > 177) goto L19;
    L20:
        endCurrentBasicBlockWithNoSuccessor();
        return;
    L19:
        if (r4 == 191) goto L20;
        return;
    L7:
        if (r1 == 3) goto L13;
        int r03 = this.relativeStackSize + STACK_SIZE_DELTA[r4];
        if (r03 <= this.maxRelativeStackSize) goto L12;
        this.maxRelativeStackSize = r03;
    L12:
        this.relativeStackSize = r03;
        goto L15
    }

    @Override // org.mvel2.asm.MethodVisitor
    public AnnotationVisitor visitInsnAnnotation(int r3, TypePath r4, String r5, boolean r6) {
        SymbolTable r0 = this.symbolTable;
        if (r6 == false) goto L6;
        AnnotationWriter r32 = AnnotationWriter.create(r0, (r3 & (-16776961)) | (this.lastBytecodeOffset << 8), r4, r5, this.lastCodeRuntimeVisibleTypeAnnotation);
        this.lastCodeRuntimeVisibleTypeAnnotation = r32;
        return r32;
    L6:
        AnnotationWriter r33 = AnnotationWriter.create(r0, (r3 & (-16776961)) | (this.lastBytecodeOffset << 8), r4, r5, this.lastCodeRuntimeInvisibleTypeAnnotation);
        this.lastCodeRuntimeInvisibleTypeAnnotation = r33;
        return r33;
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitIntInsn(int r4, int r5) {
        ByteVector r0 = this.code;
        this.lastBytecodeOffset = r0.length;
        if (r4 != 17) goto L5;
        r0.put12(r4, r5);
    L6:
        Label r02 = this.currentBasicBlock;
        if (r02 == null) goto L22;
        int r1 = this.compute;
        if (r1 != 4) goto L11;
    L20:
        r02.frame.execute(r4, r5, null, null);
        return;
    L11:
        if (r1 == 3) goto L20;
        if (r4 == 188) goto L23;
        int r42 = this.relativeStackSize + 1;
        if (r42 <= this.maxRelativeStackSize) goto L18;
        this.maxRelativeStackSize = r42;
    L18:
        this.relativeStackSize = r42;
        return;
    L23:
        return;
    L22:
        return;
    L5:
        r0.put11(r4, r5);
        goto L6
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String r3, String r4, Handle r5, Object... r6) {
        this.lastBytecodeOffset = this.code.length;
        Symbol r32 = this.symbolTable.addConstantInvokeDynamic(r3, r4, r5, r6);
        this.code.put12(186, r32.index);
        this.code.putShort(0);
        Label r42 = this.currentBasicBlock;
        if (r42 == null) goto L16;
        int r0 = this.compute;
        if (r0 != 4) goto L7;
    L14:
        r42.frame.execute(186, 0, r32, this.symbolTable);
        return;
    L7:
        if (r0 == 3) goto L14;
        int r33 = r32.getArgumentsAndReturnSizes();
        int r43 = ((r33 & 3) - (r33 >> 2)) + 1;
        int r34 = this.relativeStackSize + r43;
        if (r34 <= this.maxRelativeStackSize) goto L12;
        this.maxRelativeStackSize = r34;
    L12:
        this.relativeStackSize = r34;
        return;
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitJumpInsn(int r11, Label r12) {
        ByteVector r0 = this.code;
        int r1 = r0.length;
        this.lastBytecodeOffset = r1;
        if (r11 < 200) goto L5;
        int r3 = r11 - 33;
    L7:
        if ((r12.flags & 4) != 0) goto L9;
    L21:
        if (r3 == r11) goto L23;
        r0.putByte(r11);
        ByteVector r112 = this.code;
        r12.put(r112, r112.length - 1, true);
    L24:
        boolean r113 = false;
    L25:
        Label r02 = this.currentBasicBlock;
        if (r02 == null) goto L50;
        int r13 = this.compute;
        Label r4 = null;
        if (r13 != 4) goto L33;
        r02.frame.execute(r3, 0, null, null);
        Label r03 = r12.getCanonicalInstance();
        r03.flags = (short) (r03.flags | 2);
        addSuccessorToCurrentBasicBlock(0, r12);
        if (r3 == 167) goto L43;
        r4 = new Label();
    L43:
        if (r4 == null) goto L47;
        if (r113 == false) goto L46;
        r4.flags = (short) (r4.flags | 2);
    L46:
        visitLabel(r4);
    L47:
        if (r3 != 167) goto L51;
        endCurrentBasicBlockWithNoSuccessor();
        return;
    L51:
        return;
    L33:
        if (r13 != 3) goto L35;
        r02.frame.execute(r3, 0, null, null);
        goto L43
    L35:
        if (r13 != 2) goto L37;
        this.relativeStackSize += STACK_SIZE_DELTA[r3];
        goto L43
    L37:
        if (r3 != 168) goto L42;
        short r14 = r12.flags;
        if ((r14 & 32) != 0) goto L41;
        r12.flags = (short) (r14 | 32);
        this.hasSubroutines = true;
    L41:
        r02.flags = (short) (r02.flags | 16);
        addSuccessorToCurrentBasicBlock(this.relativeStackSize + 1, r12);
        r4 = new Label();
        goto L43
    L42:
        int r04 = this.relativeStackSize + STACK_SIZE_DELTA[r3];
        this.relativeStackSize = r04;
        addSuccessorToCurrentBasicBlock(r04, r12);
        goto L43
    L50:
        return;
    L23:
        r0.putByte(r3);
        ByteVector r114 = this.code;
        r12.put(r114, r114.length - 1, false);
        goto L24
    L9:
        if ((r12.bytecodeOffset - r1) >= (-32768)) goto L21;
        if (r3 != 167) goto L12;
        r0.putByte(200);
    L14:
        r113 = false;
    L20:
        ByteVector r05 = this.code;
        r12.put(r05, r05.length - 1, true);
        goto L25
    L12:
        if (r3 != 168) goto L16;
        r0.putByte(201);
        goto L14
    L16:
        if (r3 < 198) goto L18;
        int r115 = r3 ^ 1;
    L19:
        r0.putByte(r115);
        this.code.putShort(8);
        this.code.putByte(com.android.dx.io.Opcodes.REM_INT_LIT8);
        this.hasAsmInstructions = true;
        r113 = true;
        goto L20
    L18:
        r115 = ((r3 + 1) ^ 1) - 1;
        goto L19
    L5:
        r3 = r11;
        goto L7
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitLabel(Label r7) {
        boolean r0 = this.hasAsmInstructions;
        ByteVector r1 = this.code;
        this.hasAsmInstructions = r0 | r7.resolve(r1.data, r1.length);
        short r02 = r7.flags;
        if ((r02 & 1) != 0) goto L48;
        int r12 = this.compute;
        if (r12 != 4) goto L24;
        Label r13 = this.currentBasicBlock;
        if (r13 != null) goto L10;
    L14:
        Label r03 = this.lastBasicBlock;
        if (r03 != null) goto L17;
    L21:
        this.lastBasicBlock = r7;
        this.currentBasicBlock = r7;
        r7.frame = new Frame(r7);
        return;
    L17:
        if (r7.bytecodeOffset != r03.bytecodeOffset) goto L20;
        r03.flags = (short) (r03.flags | (r7.flags & 2));
        r7.frame = r03.frame;
        this.currentBasicBlock = r03;
        return;
    L20:
        r03.nextBasicBlock = r7;
        goto L21
    L10:
        if (r7.bytecodeOffset != r13.bytecodeOffset) goto L13;
        r13.flags = (short) (r13.flags | (r02 & 2));
        r7.frame = r13.frame;
        return;
    L13:
        addSuccessorToCurrentBasicBlock(0, r7);
        goto L14
    L24:
        if (r12 != 3) goto L32;
        Label r04 = this.currentBasicBlock;
        if (r04 != null) goto L29;
        this.currentBasicBlock = r7;
        return;
    L29:
        r04.frame.owner = r7;
        return;
    L32:
        if (r12 != 1) goto L41;
        Label r05 = this.currentBasicBlock;
        if (r05 == null) goto L36;
        r05.outputStackMax = (short) this.maxRelativeStackSize;
        addSuccessorToCurrentBasicBlock(this.relativeStackSize, r7);
    L36:
        this.currentBasicBlock = r7;
        this.relativeStackSize = 0;
        this.maxRelativeStackSize = 0;
        Label r06 = this.lastBasicBlock;
        if (r06 == null) goto L39;
        r06.nextBasicBlock = r7;
    L39:
        this.lastBasicBlock = r7;
        return;
    L41:
        if (r12 == 2) goto L43;
        return;
    L43:
        if (this.currentBasicBlock != null) goto L47;
        this.currentBasicBlock = r7;
        return;
    L47:
        return;
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitLdcInsn(Object r8) {
        this.lastBytecodeOffset = this.code.length;
        Symbol r82 = this.symbolTable.addConstant(r8);
        int r0 = r82.index;
        int r1 = r82.tag;
        int r3 = 1;
        if (r1 != 5) goto L5;
    L14:
        boolean r12 = true;
    L16:
        if (r12 == false) goto L18;
        this.code.put12(20, r0);
    L22:
        Label r02 = this.currentBasicBlock;
        if (r02 == null) goto L39;
        int r5 = this.compute;
        if (r5 != 4) goto L27;
    L37:
        r02.frame.execute(18, 0, r82, this.symbolTable);
        return;
    L27:
        if (r5 == 3) goto L37;
        int r83 = this.relativeStackSize;
        if (r12 == false) goto L32;
        r3 = 2;
    L32:
        int r84 = r83 + r3;
        if (r84 <= this.maxRelativeStackSize) goto L35;
        this.maxRelativeStackSize = r84;
    L35:
        this.relativeStackSize = r84;
        return;
    L39:
        return;
    L18:
        ByteVector r52 = this.code;
        if (r0 < 256) goto L21;
        r52.put12(19, r0);
        goto L22
    L21:
        r52.put11(18, r0);
        goto L22
    L5:
        if (r1 == 6) goto L14;
        if (r1 != 17) goto L13;
        char r13 = r82.value.charAt(0);
        if (r13 == 'J') goto L14;
        if (r13 == 'D') goto L14;
    L13:
        r12 = false;
        goto L16
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitLineNumber(int r2, Label r3) {
        if (this.lineNumberTable != null) goto L5;
        this.lineNumberTable = new ByteVector();
    L5:
        this.lineNumberTableLength++;
        this.lineNumberTable.putShort(r3.bytecodeOffset);
        this.lineNumberTable.putShort(r2);
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitLocalVariable(String r5, String r6, String r7, Label r8, Label r9, int r10) {
        int r0 = 1;
        if (r7 == null) goto L9;
        if (this.localVariableTypeTable != null) goto L7;
        this.localVariableTypeTable = new ByteVector();
    L7:
        this.localVariableTypeTableLength++;
        this.localVariableTypeTable.putShort(r8.bytecodeOffset).putShort(r9.bytecodeOffset - r8.bytecodeOffset).putShort(this.symbolTable.addConstantUtf8(r5)).putShort(this.symbolTable.addConstantUtf8(r7)).putShort(r10);
    L9:
        if (this.localVariableTable != null) goto L11;
        this.localVariableTable = new ByteVector();
    L11:
        this.localVariableTableLength++;
        this.localVariableTable.putShort(r8.bytecodeOffset).putShort(r9.bytecodeOffset - r8.bytecodeOffset).putShort(this.symbolTable.addConstantUtf8(r5)).putShort(this.symbolTable.addConstantUtf8(r6)).putShort(r10);
        if (this.compute == 0) goto L22;
        char r52 = r6.charAt(0);
        if (r52 != 'J') goto L16;
    L17:
        r0 = 2;
    L18:
        int r102 = r10 + r0;
        if (r102 <= this.maxLocals) goto L23;
        this.maxLocals = r102;
        return;
    L23:
        return;
    L16:
        if (r52 != 'D') goto L18;
    }

    @Override // org.mvel2.asm.MethodVisitor
    public AnnotationVisitor visitLocalVariableAnnotation(int r6, TypePath r7, Label[] r8, Label[] r9, int[] r10, String r11, boolean r12) {
        ByteVector r0 = new ByteVector();
        r0.putByte(r6 >>> 24).putShort(r8.length);
        int r1 = 0;
    L4:
        if (r1 >= r8.length) goto L6;
        r0.putShort(r8[r1].bytecodeOffset).putShort(r9[r1].bytecodeOffset - r8[r1].bytecodeOffset).putShort(r10[r1]);
        r1 = r1 + 1;
        goto L4
    L6:
        TypePath.put(r7, r0);
        r0.putShort(this.symbolTable.addConstantUtf8(r11)).putShort(0);
        SymbolTable r62 = this.symbolTable;
        if (r12 == false) goto L10;
        AnnotationWriter r82 = new AnnotationWriter(r62, true, r0, this.lastCodeRuntimeVisibleTypeAnnotation);
        this.lastCodeRuntimeVisibleTypeAnnotation = r82;
        return r82;
    L10:
        AnnotationWriter r83 = new AnnotationWriter(r62, true, r0, this.lastCodeRuntimeInvisibleTypeAnnotation);
        this.lastCodeRuntimeInvisibleTypeAnnotation = r83;
        return r83;
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label r6, int[] r7, Label[] r8) {
        ByteVector r0 = this.code;
        this.lastBytecodeOffset = r0.length;
        int r3 = 0;
        r0.putByte(171).putByteArray(null, 0, (4 - (this.code.length % 4)) % 4);
        r6.put(this.code, this.lastBytecodeOffset, true);
        this.code.putInt(r8.length);
    L4:
        if (r3 >= r8.length) goto L6;
        this.code.putInt(r7[r3]);
        r8[r3].put(this.code, this.lastBytecodeOffset, true);
        r3 = r3 + 1;
        goto L4
    L6:
        visitSwitchInsn(r6, r8);
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitMaxs(int r3, int r4) {
        int r0 = this.compute;
        if (r0 != 4) goto L7;
        computeAllFrames();
        return;
    L7:
        if (r0 != 1) goto L11;
        computeMaxStackAndLocal();
        return;
    L11:
        if (r0 != 2) goto L14;
        this.maxStack = this.maxRelativeStackSize;
        return;
    L14:
        this.maxStack = r3;
        this.maxLocals = r4;
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitMethodInsn(int r2, String r3, String r4, String r5, boolean r6) {
        this.lastBytecodeOffset = this.code.length;
        Symbol r32 = this.symbolTable.addConstantMethodref(r3, r4, r5, r6);
        ByteVector r42 = this.code;
        if (r2 != 185) goto L5;
        r42.put12(185, r32.index).put11(r32.getArgumentsAndReturnSizes() >> 2, 0);
    L6:
        Label r43 = this.currentBasicBlock;
        if (r43 == null) goto L24;
        int r62 = this.compute;
        if (r62 != 4) goto L11;
    L22:
        r43.frame.execute(r2, 0, r32, this.symbolTable);
        return;
    L11:
        if (r62 == 3) goto L22;
        int r33 = r32.getArgumentsAndReturnSizes();
        int r44 = (r33 & 3) - (r33 >> 2);
        int r34 = this.relativeStackSize;
        if (r2 != 184) goto L16;
        int r35 = (r34 + r44) + 1;
    L18:
        if (r35 <= this.maxRelativeStackSize) goto L20;
        this.maxRelativeStackSize = r35;
    L20:
        this.relativeStackSize = r35;
        return;
    L16:
        r35 = r34 + r44;
        goto L18
    L24:
        return;
    L5:
        r42.put12(r2, r32.index);
        goto L6
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String r5, int r6) {
        this.lastBytecodeOffset = this.code.length;
        Symbol r52 = this.symbolTable.addConstantClass(r5);
        this.code.put12(197, r52.index).putByte(r6);
        Label r0 = this.currentBasicBlock;
        if (r0 == null) goto L13;
        int r1 = this.compute;
        if (r1 != 4) goto L7;
    L11:
        r0.frame.execute(197, r6, r52, this.symbolTable);
        return;
    L7:
        if (r1 == 3) goto L11;
        this.relativeStackSize += 1 - r6;
        return;
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitParameter(String r2, int r3) {
        if (this.parameters != null) goto L5;
        this.parameters = new ByteVector();
    L5:
        this.parametersCount++;
        ByteVector r0 = this.parameters;
        if (r2 != null) goto L8;
        int r1 = 0;
    L9:
        r0.putShort(r1).putShort(r3);
        return;
    L8:
        r1 = this.symbolTable.addConstantUtf8(r2);
        goto L9
    }

    @Override // org.mvel2.asm.MethodVisitor
    public AnnotationVisitor visitParameterAnnotation(int r2, String r3, boolean r4) {
        if (r4 == false) goto L9;
        if (this.lastRuntimeVisibleParameterAnnotations != null) goto L6;
        this.lastRuntimeVisibleParameterAnnotations = new AnnotationWriter[Type.getArgumentTypes(this.descriptor).length];
    L6:
        AnnotationWriter[] r42 = this.lastRuntimeVisibleParameterAnnotations;
        AnnotationWriter r1 = AnnotationWriter.create(this.symbolTable, r3, r42[r2]);
        r42[r2] = r1;
        return r1;
    L9:
        if (this.lastRuntimeInvisibleParameterAnnotations != null) goto L11;
        this.lastRuntimeInvisibleParameterAnnotations = new AnnotationWriter[Type.getArgumentTypes(this.descriptor).length];
    L11:
        AnnotationWriter[] r43 = this.lastRuntimeInvisibleParameterAnnotations;
        AnnotationWriter r12 = AnnotationWriter.create(this.symbolTable, r3, r43[r2]);
        r43[r2] = r12;
        return r12;
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitTableSwitchInsn(int r5, int r6, Label r7, Label... r8) {
        ByteVector r0 = this.code;
        this.lastBytecodeOffset = r0.length;
        int r3 = 0;
        r0.putByte(170).putByteArray(null, 0, (4 - (this.code.length % 4)) % 4);
        r7.put(this.code, this.lastBytecodeOffset, true);
        this.code.putInt(r5).putInt(r6);
        int r52 = r8.length;
    L3:
        if (r3 >= r52) goto L5;
        r8[r3].put(this.code, this.lastBytecodeOffset, true);
        r3 = r3 + 1;
        goto L3
    L5:
        visitSwitchInsn(r7, r8);
    }

    @Override // org.mvel2.asm.MethodVisitor
    public AnnotationVisitor visitTryCatchAnnotation(int r2, TypePath r3, String r4, boolean r5) {
        SymbolTable r0 = this.symbolTable;
        if (r5 == false) goto L6;
        AnnotationWriter r22 = AnnotationWriter.create(r0, r2, r3, r4, this.lastCodeRuntimeVisibleTypeAnnotation);
        this.lastCodeRuntimeVisibleTypeAnnotation = r22;
        return r22;
    L6:
        AnnotationWriter r23 = AnnotationWriter.create(r0, r2, r3, r4, this.lastCodeRuntimeInvisibleTypeAnnotation);
        this.lastCodeRuntimeInvisibleTypeAnnotation = r23;
        return r23;
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitTryCatchBlock(Label r7, Label r8, Label r9, String r10) {
        if (r10 == null) goto L6;
        int r1 = this.symbolTable.addConstantClass(r10).index;
    L7:
        Handler r0 = new Handler(r7, r8, r9, r1, r10);
        if (this.firstHandler != null) goto L10;
        this.firstHandler = r0;
    L11:
        this.lastHandler = r0;
        return;
    L10:
        this.lastHandler.nextHandler = r0;
        goto L11
    L6:
        r1 = 0;
        goto L7
    }

    @Override // org.mvel2.asm.MethodVisitor
    public AnnotationVisitor visitTypeAnnotation(int r2, TypePath r3, String r4, boolean r5) {
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

    @Override // org.mvel2.asm.MethodVisitor
    public void visitTypeInsn(int r4, String r5) {
        this.lastBytecodeOffset = this.code.length;
        Symbol r52 = this.symbolTable.addConstantClass(r5);
        this.code.put12(r4, r52.index);
        Label r0 = this.currentBasicBlock;
        if (r0 == null) goto L18;
        int r1 = this.compute;
        if (r1 != 4) goto L7;
    L16:
        r0.frame.execute(r4, this.lastBytecodeOffset, r52, this.symbolTable);
        return;
    L7:
        if (r1 == 3) goto L16;
        if (r4 != 187) goto L19;
        int r42 = this.relativeStackSize + 1;
        if (r42 <= this.maxRelativeStackSize) goto L14;
        this.maxRelativeStackSize = r42;
    L14:
        this.relativeStackSize = r42;
        return;
    L19:
        return;
    }

    @Override // org.mvel2.asm.MethodVisitor
    public void visitVarInsn(int r7, int r8) {
        ByteVector r0 = this.code;
        this.lastBytecodeOffset = r0.length;
        if (r8 >= 4) goto L11;
        if (r7 == 169) goto L11;
        if (r7 >= 54) goto L8;
        int r4 = ((r7 - 21) << 2) + 26;
    L9:
        r0.putByte(r4 + r8);
    L14:
        Label r02 = this.currentBasicBlock;
        if (r02 == null) goto L28;
        int r42 = this.compute;
        if (r42 != 4) goto L19;
    L27:
        r02.frame.execute(r7, r8, null, null);
        goto L28
    L19:
        if (r42 == 3) goto L27;
        if (r7 != 169) goto L23;
        r02.flags = (short) (r02.flags | 64);
        r02.outputStackSize = (short) this.relativeStackSize;
        endCurrentBasicBlockWithNoSuccessor();
        goto L28
    L23:
        int r03 = this.relativeStackSize + STACK_SIZE_DELTA[r7];
        if (r03 <= this.maxRelativeStackSize) goto L26;
        this.maxRelativeStackSize = r03;
    L26:
        this.relativeStackSize = r03;
    L28:
        int r04 = this.compute;
        if (r04 != 0) goto L31;
    L44:
        if (r7 < 54) goto L50;
        if (r04 == 4) goto L47;
        return;
    L47:
        if (this.firstHandler == null) goto L52;
        visitLabel(new Label());
        return;
    L52:
        return;
    L50:
        return;
    L31:
        if (r7 != 22) goto L33;
    L40:
        int r82 = r8 + 2;
    L42:
        if (r82 <= this.maxLocals) goto L44;
        this.maxLocals = r82;
        goto L44
    L33:
        if (r7 == 24) goto L40;
        if (r7 == 55) goto L40;
        if (r7 == 57) goto L40;
        r82 = r8 + 1;
        goto L42
    L8:
        r4 = ((r7 - 54) << 2) + 59;
    L11:
        if (r8 < 256) goto L13;
        r0.putByte(196).put12(r7, r8);
        goto L14
    L13:
        r0.put11(r7, r8);
        goto L14
    }
}
