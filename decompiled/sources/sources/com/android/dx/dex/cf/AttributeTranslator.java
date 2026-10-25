package com.android.dx.dex.cf;

import com.android.dx.cf.attrib.AttAnnotationDefault;
import com.android.dx.cf.attrib.AttEnclosingMethod;
import com.android.dx.cf.attrib.AttExceptions;
import com.android.dx.cf.attrib.AttInnerClasses;
import com.android.dx.cf.attrib.AttRuntimeInvisibleAnnotations;
import com.android.dx.cf.attrib.AttRuntimeInvisibleParameterAnnotations;
import com.android.dx.cf.attrib.AttRuntimeVisibleAnnotations;
import com.android.dx.cf.attrib.AttRuntimeVisibleParameterAnnotations;
import com.android.dx.cf.attrib.AttSignature;
import com.android.dx.cf.attrib.AttSourceDebugExtension;
import com.android.dx.cf.attrib.InnerClassList;
import com.android.dx.cf.direct.DirectClassFile;
import com.android.dx.cf.iface.AttributeList;
import com.android.dx.cf.iface.Method;
import com.android.dx.cf.iface.MethodList;
import com.android.dx.dex.file.AnnotationUtils;
import com.android.dx.rop.annotation.Annotation;
import com.android.dx.rop.annotation.AnnotationVisibility;
import com.android.dx.rop.annotation.Annotations;
import com.android.dx.rop.annotation.AnnotationsList;
import com.android.dx.rop.annotation.NameValuePair;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.Warning;
import java.util.ArrayList;

/* loaded from: classes4.dex */
class AttributeTranslator {
    private AttributeTranslator() {
    }

    public static Annotations getAnnotations(AttributeList r2) {
        Annotations r0 = getAnnotations0(r2);
        Annotation r1 = getSignature(r2);
        Annotation r22 = getSourceDebugExtension(r2);
        if (r1 == null) goto L5;
        r0 = Annotations.combine(r0, r1);
    L5:
        if (r22 != null) goto L7;
        return r0;
    L7:
        return Annotations.combine(r0, r22);
    }

    private static Annotations getAnnotations0(AttributeList r2) {
        AttRuntimeVisibleAnnotations r0 = (AttRuntimeVisibleAnnotations) r2.findFirst(AttRuntimeVisibleAnnotations.ATTRIBUTE_NAME);
        AttRuntimeInvisibleAnnotations r22 = (AttRuntimeInvisibleAnnotations) r2.findFirst(AttRuntimeInvisibleAnnotations.ATTRIBUTE_NAME);
        if (r0 != null) goto L9;
        if (r22 != null) goto L8;
        return Annotations.EMPTY;
    L8:
        return r22.getAnnotations();
    L9:
        if (r22 != null) goto L13;
        return r0.getAnnotations();
    L13:
        return Annotations.combine(r0.getAnnotations(), r22.getAnnotations());
    }

    public static Annotations getClassAnnotations(DirectClassFile r5, CfOptions r6) {
        CstType r0 = r5.getThisClass();
        AttributeList r1 = r5.getAttributes();
        Annotations r2 = getAnnotations(r1);
        Annotation r3 = translateEnclosingMethod(r1);
        if (r3 != null) goto L5;
        boolean r4 = true;
    L20:
        Annotations r02 = translateInnerClasses(r0, r1, r4);     // Catch: Warning -> L10
        if (r02 == null) goto L12;
        r2 = Annotations.combine(r2, r02);     // Catch: Warning -> L10
    L12:
        if (r3 == null) goto L15;
        r2 = Annotations.combine(r2, r3);
    L15:
        if (AccessFlags.isAnnotation(r5.getAccessFlags()) == false) goto L22;
        Annotation r52 = translateAnnotationDefaults(r5);
        if (r52 != null) goto L19;
        return r2;
    L19:
        return Annotations.combine(r2, r52);
    L22:
        return r2;
    L10:
        e = move-exception;
        r6.warn.println("warning: " + e.getMessage());
        goto L12
    L5:
        r4 = false;
        goto L20
    }

    public static TypeList getExceptions(Method r1) {
        AttExceptions r12 = (AttExceptions) r1.getAttributes().findFirst(AttExceptions.ATTRIBUTE_NAME);
        if (r12 != null) goto L7;
        return StdTypeList.EMPTY;
    L7:
        return r12.getExceptions();
    }

    public static Annotations getMethodAnnotations(Method r2) {
        Annotations r0 = getAnnotations(r2.getAttributes());
        TypeList r22 = getExceptions(r2);
        if (r22.size() != 0) goto L5;
        return r0;
    L5:
        return Annotations.combine(r0, AnnotationUtils.makeThrows(r22));
    }

    public static AnnotationsList getParameterAnnotations(Method r2) {
        AttributeList r22 = r2.getAttributes();
        AttRuntimeVisibleParameterAnnotations r0 = (AttRuntimeVisibleParameterAnnotations) r22.findFirst(AttRuntimeVisibleParameterAnnotations.ATTRIBUTE_NAME);
        AttRuntimeInvisibleParameterAnnotations r23 = (AttRuntimeInvisibleParameterAnnotations) r22.findFirst(AttRuntimeInvisibleParameterAnnotations.ATTRIBUTE_NAME);
        if (r0 != null) goto L9;
        if (r23 != null) goto L8;
        return AnnotationsList.EMPTY;
    L8:
        return r23.getParameterAnnotations();
    L9:
        if (r23 != null) goto L13;
        return r0.getParameterAnnotations();
    L13:
        return AnnotationsList.combine(r0.getParameterAnnotations(), r23.getParameterAnnotations());
    }

    private static Annotation getSignature(AttributeList r1) {
        AttSignature r12 = (AttSignature) r1.findFirst(AttSignature.ATTRIBUTE_NAME);
        if (r12 != null) goto L7;
        return null;
    L7:
        return AnnotationUtils.makeSignature(r12.getSignature());
    }

    private static Annotation getSourceDebugExtension(AttributeList r1) {
        AttSourceDebugExtension r12 = (AttSourceDebugExtension) r1.findFirst(AttSourceDebugExtension.ATTRIBUTE_NAME);
        if (r12 != null) goto L7;
        return null;
    L7:
        return AnnotationUtils.makeSourceDebugExtension(r12.getSmapString());
    }

    private static Annotation translateAnnotationDefaults(DirectClassFile r7) {
        CstType r0 = r7.getThisClass();
        MethodList r72 = r7.getMethods();
        int r1 = r72.size();
        Annotation r2 = new Annotation(r0, AnnotationVisibility.EMBEDDED);
        int r02 = 0;
        boolean r3 = false;
    L3:
        if (r02 >= r1) goto L8;
        Method r4 = r72.get(r02);
        AttAnnotationDefault r5 = (AttAnnotationDefault) r4.getAttributes().findFirst(AttAnnotationDefault.ATTRIBUTE_NAME);
        if (r5 == null) goto L7;
        r2.add(new NameValuePair(r4.getNat().getName(), r5.getValue()));
        r3 = true;
    L7:
        r02 = r02 + 1;
        goto L3
    L8:
        if (r3 == true) goto L11;
        return null;
    L11:
        r2.setImmutable();
        return AnnotationUtils.makeAnnotationDefault(r2);
    }

    private static Annotation translateEnclosingMethod(AttributeList r2) {
        AttEnclosingMethod r22 = (AttEnclosingMethod) r2.findFirst(AttEnclosingMethod.ATTRIBUTE_NAME);
        if (r22 != null) goto L6;
        return null;
    L6:
        CstType r0 = r22.getEnclosingClass();
        CstNat r23 = r22.getMethod();
        if (r23 != null) goto L11;
        return AnnotationUtils.makeEnclosingClass(r0);
    L11:
        return AnnotationUtils.makeEnclosingMethod(new CstMethodRef(r0, r23));
    }

    private static Annotations translateInnerClasses(CstType r9, AttributeList r10, boolean r11) {
        AttInnerClasses r102 = (AttInnerClasses) r10.findFirst(AttInnerClasses.ATTRIBUTE_NAME);
        if (r102 != null) goto L5;
        return null;
    L5:
        InnerClassList r103 = r102.getInnerClasses();
        int r1 = r103.size();
        ArrayList r2 = new ArrayList();
        int r3 = 0;
        InnerClassList.Item r5 = null;
        int r4 = 0;
    L6:
        if (r4 >= r1) goto L14;
        InnerClassList.Item r6 = r103.get(r4);
        CstType r7 = r6.getInnerClass();
        if (r7.equals(r9) == false) goto L11;
        r5 = r6;
    L13:
        r4 = r4 + 1;
        goto L6
    L11:
        if (r9.equals(r6.getOuterClass()) == false) goto L13;
        r2.add(r7.getClassType());
        goto L13
    L14:
        int r104 = r2.size();
        if (r5 != null) goto L18;
        if (r104 != 0) goto L18;
        return null;
    L18:
        Annotations r0 = new Annotations();
        if (r5 == null) goto L27;
        r0.add(AnnotationUtils.makeInnerClass(r5.getInnerName(), r5.getAccessFlags()));
        if (r11 == false) goto L27;
        if (r5.getOuterClass() == null) goto L26;
        r0.add(AnnotationUtils.makeEnclosingClass(r5.getOuterClass()));
        goto L27
    L26:
        throw new Warning("Ignoring InnerClasses attribute for an anonymous inner class\n(" + r9.toHuman() + ") that doesn't come with an\nassociated EnclosingMethod attribute. This class was probably produced by a\ncompiler that did not target the modern .class file format. The recommended\nsolution is to recompile the class from source, using an up-to-date compiler\nand without specifying any \"-target\" type options. The consequence of ignoring\nthis warning is that reflective operations on this class will incorrectly\nindicate that it is *not* an inner class.");
    L27:
        if (r104 == 0) goto L32;
        StdTypeList r92 = new StdTypeList(r104);
    L29:
        if (r3 >= r104) goto L31;
        r92.set(r3, (Type) r2.get(r3));
        r3 = r3 + 1;
        goto L29
    L31:
        r92.setImmutable();
        r0.add(AnnotationUtils.makeMemberClasses(r92));
    L32:
        r0.setImmutable();
        return r0;
    }
}
