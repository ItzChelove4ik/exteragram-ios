package com.sun.jna;

import com.sun.jna.Structure;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

/* loaded from: classes5.dex */
public abstract class Union extends Structure {
    private Structure.StructField activeField;

    public Union() {
    }

    public Union(Pointer r1) {
        super(r1);
    }

    public Union(Pointer r1, int r2) {
        super(r1, r2);
    }

    public Union(Pointer r1, int r2, TypeMapper r3) {
        super(r1, r2, r3);
    }

    public Union(TypeMapper r1) {
        super(r1);
    }

    private Structure.StructField findField(Class<?> r3) {
        ensureAllocated();
        Iterator<Structure.StructField> r2 = fields().values().iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        Structure.StructField r0 = r2.next();
        if (r0.type.isAssignableFrom(r3) == false) goto L4;
        return r0;
    L8:
        return null;
    }

    @Override // com.sun.jna.Structure
    public List<String> getFieldOrder() {
        List<Field> r2 = getFieldList();
        ArrayList r0 = new ArrayList(r2.size());
        Iterator<Field> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r0.add(r22.next().getName());
        goto L4
    L6:
        return r0;
    }

    @Override // com.sun.jna.Structure
    public int getNativeAlignment(Class<?> r1, Object r2, boolean r3) {
        return super.getNativeAlignment(r1, r2, true);
    }

    public Object getTypedValue(Class<?> r4) {
        ensureAllocated();
        Iterator<Structure.StructField> r0 = fields().values().iterator();
    L4:
        if (r0.hasNext() == false) goto L9;
        Structure.StructField r1 = r0.next();
        if (r1.type != r4) goto L4;
        this.activeField = r1;
        read();
        return getFieldValue(this.activeField.field);
    L9:
        Utils$$ExternalSyntheticBUOutline2.m("No field of type ", r4, " in ", this);
        return null;
    }

    @Override // com.sun.jna.Structure
    public Object readField(Structure.StructField r3) {
        if (r3 == this.activeField) goto L14;
        if (Structure.class.isAssignableFrom(r3.type) == false) goto L7;
        return null;
    L7:
        if (String.class.isAssignableFrom(r3.type) == false) goto L9;
        return null;
    L9:
        if (WString.class.isAssignableFrom(r3.type) == false) goto L14;
        return null;
    L14:
        return super.readField(r3);
    }

    @Override // com.sun.jna.Structure
    public Object readField(String r1) {
        ensureAllocated();
        setType(r1);
        return super.readField(r1);
    }

    public void setType(Class<?> r4) {
        ensureAllocated();
        Iterator<Structure.StructField> r0 = fields().values().iterator();
    L4:
        if (r0.hasNext() == false) goto L9;
        Structure.StructField r1 = r0.next();
        if (r1.type != r4) goto L4;
        this.activeField = r1;
        return;
    L9:
        Utils$$ExternalSyntheticBUOutline2.m("No field of type ", r4, " in ", this);
    }

    public void setType(String r3) {
        ensureAllocated();
        Structure.StructField r0 = fields().get(r3);
        if (r0 == null) goto L6;
        this.activeField = r0;
        return;
    L6:
        Utils$$ExternalSyntheticBUOutline2.m("No field named ", r3, " in ", this);
    }

    public Object setTypedValue(Object r3) {
        Structure.StructField r0 = findField(r3.getClass());
        if (r0 == null) goto L6;
        this.activeField = r0;
        setFieldValue(r0.field, r3);
        return this;
    L6:
        Union$$ExternalSyntheticBUOutline0.m("No field of type ", r3.getClass(), " in ", this);
        return null;
    }

    @Override // com.sun.jna.Structure
    public void writeField(Structure.StructField r2) {
        if (r2 != this.activeField) goto L6;
        super.writeField(r2);
        return;
    }

    @Override // com.sun.jna.Structure
    public void writeField(String r1) {
        ensureAllocated();
        setType(r1);
        super.writeField(r1);
    }

    @Override // com.sun.jna.Structure
    public void writeField(String r1, Object r2) {
        ensureAllocated();
        setType(r1);
        super.writeField(r1, r2);
    }
}
