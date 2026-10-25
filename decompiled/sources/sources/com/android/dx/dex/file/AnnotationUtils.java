package com.android.dx.dex.file;

import com.android.dx.rop.annotation.Annotation;
import com.android.dx.rop.annotation.AnnotationVisibility;
import com.android.dx.rop.annotation.NameValuePair;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstAnnotation;
import com.android.dx.rop.cst.CstArray;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstKnownNull;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class AnnotationUtils {
    private static final CstString ACCESS_FLAGS_STRING = null;
    private static final CstType ANNOTATION_DEFAULT_TYPE = null;
    private static final CstType ENCLOSING_CLASS_TYPE = null;
    private static final CstType ENCLOSING_METHOD_TYPE = null;
    private static final CstType INNER_CLASS_TYPE = null;
    private static final CstType MEMBER_CLASSES_TYPE = null;
    private static final CstString NAME_STRING = null;
    private static final CstType SIGNATURE_TYPE = null;
    private static final CstType SOURCE_DEBUG_EXTENSION_TYPE = null;
    private static final CstType THROWS_TYPE = null;
    private static final CstString VALUE_STRING = null;

    static {
        ANNOTATION_DEFAULT_TYPE = CstType.intern(Type.intern("Ldalvik/annotation/AnnotationDefault;"));
        ENCLOSING_CLASS_TYPE = CstType.intern(Type.intern("Ldalvik/annotation/EnclosingClass;"));
        ENCLOSING_METHOD_TYPE = CstType.intern(Type.intern("Ldalvik/annotation/EnclosingMethod;"));
        INNER_CLASS_TYPE = CstType.intern(Type.intern("Ldalvik/annotation/InnerClass;"));
        MEMBER_CLASSES_TYPE = CstType.intern(Type.intern("Ldalvik/annotation/MemberClasses;"));
        SIGNATURE_TYPE = CstType.intern(Type.intern("Ldalvik/annotation/Signature;"));
        SOURCE_DEBUG_EXTENSION_TYPE = CstType.intern(Type.intern("Ldalvik/annotation/SourceDebugExtension;"));
        THROWS_TYPE = CstType.intern(Type.intern("Ldalvik/annotation/Throws;"));
        ACCESS_FLAGS_STRING = new CstString("accessFlags");
        NAME_STRING = new CstString("name");
        VALUE_STRING = new CstString("value");
    }

    private AnnotationUtils() {
    }

    public static Annotation makeAnnotationDefault(Annotation r4) {
        Annotation r0 = new Annotation(ANNOTATION_DEFAULT_TYPE, AnnotationVisibility.SYSTEM);
        r0.put(new NameValuePair(VALUE_STRING, new CstAnnotation(r4)));
        r0.setImmutable();
        return r0;
    }

    private static CstArray makeCstArray(TypeList r4) {
        int r0 = r4.size();
        CstArray.List r1 = new CstArray.List(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1.set(r2, CstType.intern(r4.getType(r2)));
        r2 = r2 + 1;
        goto L3
    L5:
        r1.setImmutable();
        return new CstArray(r1);
    }

    public static Annotation makeEnclosingClass(CstType r3) {
        Annotation r0 = new Annotation(ENCLOSING_CLASS_TYPE, AnnotationVisibility.SYSTEM);
        r0.put(new NameValuePair(VALUE_STRING, r3));
        r0.setImmutable();
        return r0;
    }

    public static Annotation makeEnclosingMethod(CstMethodRef r3) {
        Annotation r0 = new Annotation(ENCLOSING_METHOD_TYPE, AnnotationVisibility.SYSTEM);
        r0.put(new NameValuePair(VALUE_STRING, r3));
        r0.setImmutable();
        return r0;
    }

    public static Annotation makeInnerClass(CstString r3, int r4) {
        Annotation r0 = new Annotation(INNER_CLASS_TYPE, AnnotationVisibility.SYSTEM);
        Constant r32 = r3;
        if (r3 != null) goto L6;
        r32 = CstKnownNull.THE_ONE;
    L6:
        r0.put(new NameValuePair(NAME_STRING, r32));
        r0.put(new NameValuePair(ACCESS_FLAGS_STRING, CstInteger.make(r4)));
        r0.setImmutable();
        return r0;
    }

    public static Annotation makeMemberClasses(TypeList r3) {
        CstArray r32 = makeCstArray(r3);
        Annotation r0 = new Annotation(MEMBER_CLASSES_TYPE, AnnotationVisibility.SYSTEM);
        r0.put(new NameValuePair(VALUE_STRING, r32));
        r0.setImmutable();
        return r0;
    }

    public static Annotation makeSignature(CstString r8) {
        Annotation r0 = new Annotation(SIGNATURE_TYPE, AnnotationVisibility.SYSTEM);
        String r82 = r8.getString();
        int r1 = r82.length();
        ArrayList r2 = new ArrayList(20);
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r1) goto L20;
        int r6 = r4 + 1;
        if (r82.charAt(r4) != 'L') goto L14;
    L6:
        if (r6 >= r1) goto L19;
        char r5 = r82.charAt(r6);
        if (r5 == ';') goto L9;
        if (r5 == '<') goto L19;
        r6 = r6 + 1;
        goto L6
    L9:
        r6 = r6 + 1;
    L19:
        r2.add(r82.substring(r4, r6));
        r4 = r6;
    L14:
        if (r6 >= r1) goto L19;
        if (r82.charAt(r6) == 'L') goto L19;
        r6 = r6 + 1;
        goto L14
    L20:
        int r83 = r2.size();
        CstArray.List r12 = new CstArray.List(r83);
    L21:
        if (r3 >= r83) goto L23;
        r12.set(r3, new CstString((String) r2.get(r3)));
        r3 = r3 + 1;
        goto L21
    L23:
        r12.setImmutable();
        r0.put(new NameValuePair(VALUE_STRING, new CstArray(r12)));
        r0.setImmutable();
        return r0;
    }

    public static Annotation makeSourceDebugExtension(CstString r3) {
        Annotation r0 = new Annotation(SOURCE_DEBUG_EXTENSION_TYPE, AnnotationVisibility.SYSTEM);
        r0.put(new NameValuePair(VALUE_STRING, r3));
        r0.setImmutable();
        return r0;
    }

    public static Annotation makeThrows(TypeList r3) {
        CstArray r32 = makeCstArray(r3);
        Annotation r0 = new Annotation(THROWS_TYPE, AnnotationVisibility.SYSTEM);
        r0.put(new NameValuePair(VALUE_STRING, r32));
        r0.setImmutable();
        return r0;
    }
}
