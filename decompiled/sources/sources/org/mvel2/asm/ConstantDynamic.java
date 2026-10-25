package org.mvel2.asm;

import java.util.Arrays;

/* loaded from: classes5.dex */
public final class ConstantDynamic {
    private final Handle bootstrapMethod;
    private final Object[] bootstrapMethodArguments;
    private final String descriptor;
    private final String name;

    public ConstantDynamic(String r1, String r2, Handle r3, Object... r4) {
        this.name = r1;
        this.descriptor = r2;
        this.bootstrapMethod = r3;
        this.bootstrapMethodArguments = r4;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ConstantDynamic) == true) goto L8;
        return false;
    L8:
        ConstantDynamic r52 = (ConstantDynamic) r5;
        if (this.name.equals(r52.name) == true) goto L11;
    L17:
        return false;
    L11:
        if (this.descriptor.equals(r52.descriptor) == false) goto L17;
        if (this.bootstrapMethod.equals(r52.bootstrapMethod) == false) goto L17;
        if (Arrays.equals(this.bootstrapMethodArguments, r52.bootstrapMethodArguments) == false) goto L17;
        return true;
    }

    public Handle getBootstrapMethod() {
        return this.bootstrapMethod;
    }

    public Object getBootstrapMethodArgument(int r1) {
        return this.bootstrapMethodArguments[r1];
    }

    public int getBootstrapMethodArgumentCount() {
        return this.bootstrapMethodArguments.length;
    }

    public Object[] getBootstrapMethodArgumentsUnsafe() {
        return this.bootstrapMethodArguments;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        char r1 = this.descriptor.charAt(0);
        if (r1 != 'J') goto L5;
        return 2;
    L5:
        if (r1 == 'D') goto L11;
        return 1;
    L11:
        return 2;
    }

    public int hashCode() {
        int r0 = (this.name.hashCode() ^ Integer.rotateLeft(this.descriptor.hashCode(), 8)) ^ Integer.rotateLeft(this.bootstrapMethod.hashCode(), 16);
        return Integer.rotateLeft(Arrays.hashCode(this.bootstrapMethodArguments), 24) ^ r0;
    }

    public String toString() {
        return this.name + " : " + this.descriptor + ' ' + this.bootstrapMethod + ' ' + Arrays.toString(this.bootstrapMethodArguments);
    }
}
