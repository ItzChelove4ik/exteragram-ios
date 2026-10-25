package com.android.dex;

/* loaded from: classes4.dex */
public final class ClassData {
    private final Method[] directMethods;
    private final Field[] instanceFields;
    private final Field[] staticFields;
    private final Method[] virtualMethods;

    public static class Field {
        private final int accessFlags;
        private final int fieldIndex;

        public Field(int r1, int r2) {
            this.fieldIndex = r1;
            this.accessFlags = r2;
        }

        public int getAccessFlags() {
            return this.accessFlags;
        }

        public int getFieldIndex() {
            return this.fieldIndex;
        }
    }

    public static class Method {
        private final int accessFlags;
        private final int codeOffset;
        private final int methodIndex;

        public Method(int r1, int r2, int r3) {
            this.methodIndex = r1;
            this.accessFlags = r2;
            this.codeOffset = r3;
        }

        public int getAccessFlags() {
            return this.accessFlags;
        }

        public int getCodeOffset() {
            return this.codeOffset;
        }

        public int getMethodIndex() {
            return this.methodIndex;
        }
    }

    public ClassData(Field[] r1, Field[] r2, Method[] r3, Method[] r4) {
        this.staticFields = r1;
        this.instanceFields = r2;
        this.directMethods = r3;
        this.virtualMethods = r4;
    }

    public Field[] allFields() {
        Field[] r0 = this.staticFields;
        Field[] r1 = new Field[r0.length + this.instanceFields.length];
        System.arraycopy(r0, 0, r1, 0, r0.length);
        Field[] r02 = this.instanceFields;
        System.arraycopy(r02, 0, r1, this.staticFields.length, r02.length);
        return r1;
    }

    public Method[] allMethods() {
        Method[] r0 = this.directMethods;
        Method[] r1 = new Method[r0.length + this.virtualMethods.length];
        System.arraycopy(r0, 0, r1, 0, r0.length);
        Method[] r02 = this.virtualMethods;
        System.arraycopy(r02, 0, r1, this.directMethods.length, r02.length);
        return r1;
    }

    public Method[] getDirectMethods() {
        return this.directMethods;
    }

    public Field[] getInstanceFields() {
        return this.instanceFields;
    }

    public Field[] getStaticFields() {
        return this.staticFields;
    }

    public Method[] getVirtualMethods() {
        return this.virtualMethods;
    }
}
