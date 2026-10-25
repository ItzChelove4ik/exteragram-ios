package com.android.dx;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.dex.file.ClassDefItem;
import com.android.dx.rop.annotation.Annotation;
import com.android.dx.rop.annotation.AnnotationVisibility;
import com.android.dx.rop.annotation.Annotations;
import com.android.dx.rop.annotation.NameValuePair;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstEnumRef;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import java.lang.annotation.ElementType;
import java.util.HashMap;
import java.util.Iterator;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
public final class AnnotationId<D, V> {
    private final ElementType annotatedElement;
    private final TypeId<D> declaringType;
    private final HashMap<String, NameValuePair> elements;
    private final TypeId<V> type;

    public static final class Element {
        private final String name;
        private final Object value;

        public Element(String r2, Object r3) {
            if (r2 == null) goto L9;
            if (r3 == null) goto L7;
            this.name = r2;
            this.value = r3;
            return;
        L7:
            f$$ExternalSyntheticBUOutline2.m("value == null");
            throw null;
        L9:
            f$$ExternalSyntheticBUOutline2.m("name == null");
            throw null;
        }

        public static Constant toConstant(Object r2) {
            Class<?> r0 = r2.getClass();
            if (r0.isEnum() == false) goto L7;
            CstString r1 = new CstString(TypeId.get(r0).getName());
            return new CstEnumRef(new CstNat(new CstString(((Enum) r2).name()), r1));
        L7:
            if (r0.isArray() == false) goto L9;
            ByteString$$ExternalSyntheticBUOutline0.m("Array is not supported yet");
            return null;
        L9:
            if ((r2 instanceof TypeId) == false) goto L11;
            ByteString$$ExternalSyntheticBUOutline0.m("TypeId is not supported yet");
            return null;
        L11:
            return Constants.getConstant(r2);
        }

        public boolean equals(Object r4) {
            if ((r4 instanceof Element) == true) goto L5;
            return false;
        L5:
            Element r42 = (Element) r4;
            if (this.name.equals(r42.name) == true) goto L8;
        L11:
            return false;
        L8:
            if (this.value.equals(r42.value) == false) goto L11;
            return true;
        }

        public String getName() {
            return this.name;
        }

        public Object getValue() {
            return this.value;
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.value.hashCode();
        }

        public String toString() {
            return "[" + this.name + ", " + this.value + "]";
        }
    }

    private AnnotationId(TypeId<D> r1, TypeId<V> r2, ElementType r3) {
        this.declaringType = r1;
        this.type = r2;
        this.annotatedElement = r3;
        this.elements = new HashMap();
    }

    public static <D, V> AnnotationId<D, V> get(TypeId<D> r1, TypeId<V> r2, ElementType r3) {
        if (r3 == ElementType.TYPE) goto L14;
        if (r3 == ElementType.METHOD) goto L14;
        if (r3 == ElementType.FIELD) goto L14;
        if (r3 == ElementType.PARAMETER) goto L14;
        f$$ExternalSyntheticBUOutline1.m("element type is not supported to annotate yet.");
        return null;
    L14:
        return new AnnotationId(r1, r2, r3);
    }

    public void addToMethod(DexMaker r5, MethodId<?, ?> r6) {
        if (this.annotatedElement == ElementType.METHOD) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("This annotation is not for method");
        return;
    L5:
        if (r6.declaringType.equals(this.declaringType) == false) goto L20;
        ClassDefItem r0 = r5.getTypeDeclaration(this.declaringType).toClassDefItem();
        if (r0 == null) goto L18;
        CstMethodRef r62 = r6.constant;
        if (r62 == null) goto L16;
        Annotation r2 = new Annotation(CstType.intern(this.type.ropType), AnnotationVisibility.RUNTIME);
        Annotations r1 = new Annotations();
        Iterator<NameValuePair> r4 = this.elements.values().iterator();
    L12:
        if (r4.hasNext() == false) goto L14;
        r2.add(r4.next());
        goto L12
    L14:
        r1.add(r2);
        r0.addMethodAnnotations(r62, r1, r5.getDexFile());
        return;
    L16:
        f$$ExternalSyntheticBUOutline2.m("Method reference is NULL");
        return;
    L18:
        f$$ExternalSyntheticBUOutline2.m("No class defined item is found");
        return;
    L20:
        Utils$$ExternalSyntheticBUOutline2.m("Method", r6, "'s declaring type is inconsistent with", this);
    }

    public void set(Element r4) {
        if (r4 == null) goto L5;
        NameValuePair r2 = new NameValuePair(new CstString(r4.getName()), Element.toConstant(r4.getValue()));
        this.elements.put(r4.getName(), r2);
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("element == null");
    }
}
