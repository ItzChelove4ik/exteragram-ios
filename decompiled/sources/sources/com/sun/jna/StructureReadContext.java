package com.sun.jna;

import java.lang.reflect.Field;

/* loaded from: classes5.dex */
public class StructureReadContext extends FromNativeContext {
    private Field field;
    private Structure structure;

    public StructureReadContext(Structure r2, Field r3) {
        super(r3.getType());
        this.structure = r2;
        this.field = r3;
    }

    public Field getField() {
        return this.field;
    }

    public Structure getStructure() {
        return this.structure;
    }
}
