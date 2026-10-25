package org.mvel2.asm;

/* loaded from: classes5.dex */
public final class ClassTooLargeException extends IndexOutOfBoundsException {
    private static final long serialVersionUID = 160715609518896765L;
    private final String className;
    private final int constantPoolCount;

    public ClassTooLargeException(String r3, int r4) {
        super("Class too large: " + r3);
        this.className = r3;
        this.constantPoolCount = r4;
    }

    public String getClassName() {
        return this.className;
    }

    public int getConstantPoolCount() {
        return this.constantPoolCount;
    }
}
