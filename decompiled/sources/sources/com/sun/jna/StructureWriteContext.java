package com.sun.jna;

import java.lang.reflect.Field;

/* loaded from: classes5.dex */
public class StructureWriteContext extends ToNativeContext {
    private Field field;
    private Structure struct;

    public StructureWriteContext(Structure r1, Field r2) {
        this.struct = r1;
        this.field = r2;
    }

    public Field getField() {
        return this.field;
    }

    public Structure getStructure() {
        return this.struct;
    }
}
