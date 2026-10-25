package org.mvel2.asm;

/* loaded from: classes5.dex */
public final class MethodTooLargeException extends IndexOutOfBoundsException {
    private static final long serialVersionUID = 6807380416709738314L;
    private final String className;
    private final int codeSize;
    private final String descriptor;
    private final String methodName;

    public MethodTooLargeException(String r3, String r4, String r5, int r6) {
        super("Method too large: " + r3 + "." + r4 + " " + r5);
        this.className = r3;
        this.methodName = r4;
        this.descriptor = r5;
        this.codeSize = r6;
    }

    public String getClassName() {
        return this.className;
    }

    public int getCodeSize() {
        return this.codeSize;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public String getMethodName() {
        return this.methodName;
    }
}
