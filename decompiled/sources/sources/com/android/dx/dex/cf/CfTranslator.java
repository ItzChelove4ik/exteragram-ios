package com.android.dx.dex.cf;

import com.android.dex.util.ExceptionWithContext;
import com.android.dx.cf.code.BootstrapMethodsList;
import com.android.dx.cf.code.ConcreteMethod;
import com.android.dx.cf.code.Ropper;
import com.android.dx.cf.direct.DirectClassFile;
import com.android.dx.cf.iface.Field;
import com.android.dx.cf.iface.FieldList;
import com.android.dx.cf.iface.Method;
import com.android.dx.cf.iface.MethodList;
import com.android.dx.command.dexer.DxContext;
import com.android.dx.dex.DexOptions;
import com.android.dx.dex.code.DalvCode;
import com.android.dx.dex.code.RopTranslator;
import com.android.dx.dex.file.CallSiteIdsSection;
import com.android.dx.dex.file.ClassDefItem;
import com.android.dx.dex.file.DexFile;
import com.android.dx.dex.file.EncodedField;
import com.android.dx.dex.file.EncodedMethod;
import com.android.dx.dex.file.FieldIdsSection;
import com.android.dx.dex.file.MethodHandlesSection;
import com.android.dx.dex.file.MethodIdsSection;
import com.android.dx.rop.annotation.Annotations;
import com.android.dx.rop.annotation.AnnotationsList;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.code.DexTranslationAdvice;
import com.android.dx.rop.code.LocalVariableExtractor;
import com.android.dx.rop.code.LocalVariableInfo;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.ConstantPool;
import com.android.dx.rop.cst.CstBaseMethodRef;
import com.android.dx.rop.cst.CstBoolean;
import com.android.dx.rop.cst.CstByte;
import com.android.dx.rop.cst.CstCallSite;
import com.android.dx.rop.cst.CstCallSiteRef;
import com.android.dx.rop.cst.CstChar;
import com.android.dx.rop.cst.CstEnumRef;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstInterfaceMethodRef;
import com.android.dx.rop.cst.CstInvokeDynamic;
import com.android.dx.rop.cst.CstMethodHandle;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstShort;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.cst.TypedConstant;
import com.android.dx.rop.type.Type;
import com.android.dx.ssa.Optimizer;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class CfTranslator {
    private static final boolean DEBUG = false;

    /* renamed from: com.android.dx.dex.cf.CfTranslator$1, reason: invalid class name */
    public static class AnonymousClass1 implements DalvCode.AssignIndicesCallback {
        public AnonymousClass1() {
        }

        @Override // com.android.dx.dex.code.DalvCode.AssignIndicesCallback
        public int getIndex(Constant r1) {
            return 0;
        }
    }

    private CfTranslator() {
    }

    private static TypedConstant coerceConstant(TypedConstant r3, Type r4) {
        if (r3.getType().equals(r4) == false) goto L5;
        return r3;
    L5:
        int r0 = r4.getBasicType();
        if (r0 == 1) goto L22;
        if (r0 == 2) goto L20;
        if (r0 == 3) goto L18;
        if (r0 != 8) goto L16;
        return CstShort.make(((CstInteger) r3).getValue());
    L16:
        throw new UnsupportedOperationException("can't coerce " + r3 + " to " + r4);
    L18:
        return CstChar.make(((CstInteger) r3).getValue());
    L20:
        return CstByte.make(((CstInteger) r3).getValue());
    L22:
        return CstBoolean.make(((CstInteger) r3).getValue());
    }

    private static void processFields(DirectClassFile r8, ClassDefItem r9, DexFile r10) {
        CstType r0 = r8.getThisClass();
        FieldList r82 = r8.getFields();
        int r1 = r82.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L21;
        Field r3 = r82.get(r2);
        CstFieldRef r4 = new CstFieldRef(r0, r3.getNat());     // Catch: RuntimeException -> L10
        int r5 = r3.getAccessFlags();     // Catch: RuntimeException -> L10
        if (AccessFlags.isStatic(r5) == false) goto L13;
        TypedConstant r6 = r3.getConstantValue();     // Catch: RuntimeException -> L10
        EncodedField r7 = new EncodedField(r4, r5);     // Catch: RuntimeException -> L10
        if (r6 == null) goto L12;
        r6 = coerceConstant(r6, r4.getType());     // Catch: RuntimeException -> L10
    L12:
        r9.addStaticField(r7, r6);     // Catch: RuntimeException -> L10
    L14:
        Annotations r52 = AttributeTranslator.getAnnotations(r3.getAttributes());     // Catch: RuntimeException -> L10
        if (r52.size() == 0) goto L17;
        r9.addFieldAnnotations(r4, r52, r10);     // Catch: RuntimeException -> L10
    L17:
        r10.getFieldIds().intern(r4);     // Catch: RuntimeException -> L10
        r2 = r2 + 1;
        goto L3
    L13:
        r9.addInstanceField(new EncodedField(r4, r5));     // Catch: RuntimeException -> L10
    L10:
        e = move-exception;
        throw ExceptionWithContext.withContext(e, "...while processing " + r3.getName().toHuman() + " " + r3.getDescriptor().toHuman());
    }

    private static void processMethods(DxContext r25, DirectClassFile r26, CfOptions r27, DexOptions r28, ClassDefItem r29, DexFile r30) {
        DxContext r0 = r25;
        CfOptions r1 = r27;
        DexOptions r2 = r28;
        CstType r10 = r26.getThisClass();
        MethodList r11 = r26.getMethods();
        int r12 = r11.size();
        int r14 = 0;
    L3:
        if (r14 >= r12) goto L66;
        Method r15 = r11.get(r14);
        CstMethodRef r3 = new CstMethodRef(r10, r15.getNat());     // Catch: RuntimeException -> L11
        int r16 = r15.getAccessFlags();     // Catch: RuntimeException -> L11
        boolean r4 = AccessFlags.isStatic(r16);     // Catch: RuntimeException -> L11
        boolean r17 = AccessFlags.isPrivate(r16);     // Catch: RuntimeException -> L11
        boolean r18 = AccessFlags.isNative(r16);     // Catch: RuntimeException -> L11
        boolean r5 = AccessFlags.isAbstract(r16);     // Catch: RuntimeException -> L11
        boolean r7 = true;
        if (r3.isInstanceInit() == false) goto L8;
    L13:
        boolean r19 = true;
    L14:
        if (r18 == true) goto L16;
        if (r5 == true) goto L16;
        if (r1.positionInfo != 1) goto L21;
        r7 = false;
    L21:
        ConcreteMethod r52 = new ConcreteMethod(r15, r26, r7, r1.localInfo);     // Catch: RuntimeException -> L11
        DexTranslationAdvice r6 = DexTranslationAdvice.THE_ONE;     // Catch: RuntimeException -> L11
        RopMethod r72 = Ropper.convert(r52, r6, r11, r2);     // Catch: RuntimeException -> L11
        int r53 = r3.getParameterWordCount(r4);     // Catch: RuntimeException -> L11
        StringBuilder r32 = new StringBuilder();     // Catch: RuntimeException -> L11
        CstType r24 = r10;
        r32.append(r10.getClassType().getDescriptor());     // Catch: RuntimeException -> L11
        r32.append(".");     // Catch: RuntimeException -> L11
        r32.append(r15.getName().getString());     // Catch: RuntimeException -> L11
        String r33 = r32.toString();     // Catch: RuntimeException -> L11
        if (r1.optimize == true) goto L24;
    L28:
        RopMethod r34 = r72;
        r72 = null;
    L30:
        if (r1.localInfo == false) goto L32;
        LocalVariableInfo r62 = LocalVariableExtractor.extract(r34);     // Catch: RuntimeException -> L11
    L33:
        DalvCode r102 = RopTranslator.translate(r34, r1.positionInfo, r62, r53, r2);     // Catch: RuntimeException -> L11
        if (r1.statistics == false) goto L37;
        if (r72 == null) goto L37;
        boolean r21 = r4;
        RopMethod r42 = r72;
        DalvCode r20 = r102;
        CstMethodRef r103 = r3;
        updateDexStatistics(r25, r1, r2, r34, r42, r62, r53, r52.getCode().size());     // Catch: RuntimeException -> L11
    L38:
        DalvCode r63 = r20;
    L41:
        if (AccessFlags.isSynchronized(r16) == false) goto L45;
        r16 = r16 | 131072;     // Catch: RuntimeException -> L11
        if (r18 == true) goto L45;
        r16 = r16 & (-33);     // Catch: RuntimeException -> L11
    L45:
        if (r19 == false) goto L47;
        r16 = r16 | 65536;     // Catch: RuntimeException -> L11
    L47:
        EncodedMethod r22 = new EncodedMethod(r103, r16, r63, AttributeTranslator.getExceptions(r15));     // Catch: RuntimeException -> L11
        if (r103.isInstanceInit() == false) goto L50;
    L55:
        r29.addDirectMethod(r22);     // Catch: RuntimeException -> L11
    L56:
        Annotations r02 = AttributeTranslator.getMethodAnnotations(r15);     // Catch: RuntimeException -> L11
        if (r02.size() == 0) goto L59;
        r29.addMethodAnnotations(r103, r02, r30);     // Catch: RuntimeException -> L11
    L59:
        AnnotationsList r03 = AttributeTranslator.getParameterAnnotations(r15);     // Catch: RuntimeException -> L11
        if (r03.size() == 0) goto L62;
        r29.addParameterAnnotations(r103, r03, r30);     // Catch: RuntimeException -> L11
    L62:
        r30.getMethodIds().intern(r103);     // Catch: RuntimeException -> L11
        r14 = r14 + 1;
        r0 = r25;
        r1 = r27;
        r2 = r28;
        r10 = r24;
        goto L3
    L50:
        if (r103.isClassInit() == true) goto L55;
        if (r21 == true) goto L55;
        if (r17 == true) goto L55;
        r29.addVirtualMethod(r22);     // Catch: RuntimeException -> L11
    L37:
        r21 = r4;
        r20 = r102;
        r103 = r3;
        goto L38
    L32:
        r62 = null;
        goto L33
    L24:
        if (r0.optimizerOptions.shouldOptimize(r33) == false) goto L28;
        r34 = Optimizer.optimize(r72, r53, r4, r1.localInfo, r6);     // Catch: RuntimeException -> L11
        if (r1.statistics == false) goto L30;
        r0.codeStatistics.updateRopStatistics(r72, r34);     // Catch: RuntimeException -> L11
    L16:
        r21 = r4;
        r24 = r10;
        r103 = r3;
        r63 = null;
        goto L41
    L8:
        if (r3.isClassInit() == true) goto L13;
        r19 = false;
    L11:
        e = move-exception;
        throw ExceptionWithContext.withContext(e, "...while processing " + r15.getName().toHuman() + " " + r15.getDescriptor().toHuman());
    }

    public static ClassDefItem translate(DxContext r1, DirectClassFile r2, byte[] r3, CfOptions r4, DexOptions r5, DexFile r6) {
        return translate0(r1, r2, r3, r4, r5, r6);
    L4:
        e = move-exception;
        throw ExceptionWithContext.withContext(e, "...while processing " + r2.getFilePath());
    }

    private static ClassDefItem translate0(DxContext r8, DirectClassFile r9, byte[] r10, CfOptions r11, DexOptions r12, DexFile r13) {
        r8.optimizerOptions.loadOptimizeLists(r11.optimizeListFile, r11.dontOptimizeListFile);
        CstType r3 = r9.getThisClass();
        int r4 = r9.getAccessFlags() & (-33);
        if (r11.positionInfo != 1) goto L6;
        CstString r102 = null;
    L5:
        CstString r7 = r102;
        ClassDefItem r2 = new ClassDefItem(r3, r4, r9.getSuperclass(), r9.getInterfaces(), r7);
        Annotations r103 = AttributeTranslator.getClassAnnotations(r9, r11);
        if (r103.size() == 0) goto L10;
        r2.setClassAnnotations(r103, r13);
    L10:
        FieldIdsSection r0 = r13.getFieldIds();
        MethodIdsSection r1 = r13.getMethodIds();
        MethodHandlesSection r32 = r13.getMethodHandles();
        CallSiteIdsSection r42 = r13.getCallSiteIds();
        processFields(r9, r2, r13);
        processMethods(r8, r9, r11, r12, r2, r13);
        ConstantPool r82 = r9.getConstantPool();
        int r104 = r82.size();
        int r112 = 0;
    L11:
        if (r112 >= r104) goto L34;
        Constant r122 = r82.getOrNull(r112);
        if ((r122 instanceof CstMethodRef) == false) goto L16;
        r1.intern((CstBaseMethodRef) r122);
    L33:
        r112 = r112 + 1;
        goto L11
    L16:
        if ((r122 instanceof CstInterfaceMethodRef) == false) goto L19;
        r1.intern(((CstInterfaceMethodRef) r122).toMethodRef());
        goto L33
    L19:
        if ((r122 instanceof CstFieldRef) == false) goto L22;
        r0.intern((CstFieldRef) r122);
        goto L33
    L22:
        if ((r122 instanceof CstEnumRef) == false) goto L25;
        r0.intern(((CstEnumRef) r122).getFieldRef());
        goto L33
    L25:
        if ((r122 instanceof CstMethodHandle) == false) goto L28;
        r32.intern((CstMethodHandle) r122);
        goto L33
    L28:
        if ((r122 instanceof CstInvokeDynamic) == false) goto L33;
        CstInvokeDynamic r123 = (CstInvokeDynamic) r122;
        int r132 = r123.getBootstrapMethodIndex();
        BootstrapMethodsList.Item r133 = r9.getBootstrapMethods().get(r132);
        CstCallSite r134 = CstCallSite.make(r133.getBootstrapMethodHandle(), r123.getNat(), r133.getBootstrapMethodArguments());
        r123.setDeclaringClass(r9.getThisClass());
        r123.setCallSite(r134);
        Iterator<CstCallSiteRef> r124 = r123.getReferences().iterator();
    L31:
        if (r124.hasNext() == false) goto L33;
        r42.intern(r124.next());
        goto L31
    L34:
        return r2;
    L6:
        r102 = r9.getSourceFile();
        goto L5
    }

    private static void updateDexStatistics(DxContext r1, CfOptions r2, DexOptions r3, RopMethod r4, RopMethod r5, LocalVariableInfo r6, int r7, int r8) {
        DalvCode r42 = RopTranslator.translate(r4, r2.positionInfo, r6, r7, r3);
        DalvCode r22 = RopTranslator.translate(r5, r2.positionInfo, r6, r7, r3);
        AnonymousClass1 r32 = new AnonymousClass1();
        r42.assignIndices(r32);
        r22.assignIndices(r32);
        r1.codeStatistics.updateDexStatistics(r22, r42);
        r1.codeStatistics.updateOriginalByteCount(r8);
    }
}
