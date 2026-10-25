package com.android.dx.rop.cst;

import com.android.dx.rop.type.Type;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.Buffer$$ExternalSyntheticBUOutline4;

/* loaded from: classes.dex */
public final class CstMethodHandle extends TypedConstant {
    public static final int METHOD_HANDLE_TYPE_INSTANCE_GET = 3;
    public static final int METHOD_HANDLE_TYPE_INSTANCE_PUT = 2;
    public static final int METHOD_HANDLE_TYPE_INVOKE_CONSTRUCTOR = 6;
    public static final int METHOD_HANDLE_TYPE_INVOKE_DIRECT = 7;
    public static final int METHOD_HANDLE_TYPE_INVOKE_INSTANCE = 5;
    public static final int METHOD_HANDLE_TYPE_INVOKE_INTERFACE = 8;
    public static final int METHOD_HANDLE_TYPE_INVOKE_STATIC = 4;
    public static final int METHOD_HANDLE_TYPE_STATIC_GET = 1;
    public static final int METHOD_HANDLE_TYPE_STATIC_PUT = 0;
    private static final String[] TYPE_NAMES = null;
    private final Constant ref;
    private final int type;

    static {
        TYPE_NAMES = new String[]{"static-put", "static-get", "instance-put", "instance-get", "invoke-static", "invoke-instance", "invoke-constructor", "invoke-direct", "invoke-interface"};
    }

    private CstMethodHandle(int r1, Constant r2) {
        this.type = r1;
        this.ref = r2;
    }

    public static String getMethodHandleTypeName(int r1) {
        return TYPE_NAMES[r1];
    }

    public static boolean isAccessor(int r2) {
        if (r2 == 0) goto L11;
        if (r2 == 1) goto L11;
        if (r2 == 2) goto L11;
        if (r2 == 3) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isInvocation(int r0) {
        switch(r0) {
            case 4: goto L5;
            case 5: goto L5;
            case 6: goto L5;
            case 7: goto L5;
            case 8: goto L5;
            default: goto L3;
        };
    L3:
        return false;
    L5:
        return true;
    }

    public static CstMethodHandle make(int r2, Constant r3) {
        if (isAccessor(r2) == false) goto L11;
        if ((r3 instanceof CstFieldRef) == true) goto L15;
        Buffer$$ExternalSyntheticBUOutline4.m("ref has wrong type: ", r3.getClass());
        return null;
    L15:
        return new CstMethodHandle(r2, r3);
    L11:
        if (isInvocation(r2) == true) goto L13;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("type is out of range: ", r2);
        return null;
    L13:
        if ((r3 instanceof CstBaseMethodRef) == true) goto L15;
        Buffer$$ExternalSyntheticBUOutline4.m("ref has wrong type: ", r3.getClass());
        return null;
    }

    @Override // com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r3) {
        CstMethodHandle r32 = (CstMethodHandle) r3;
        if (getMethodHandleType() != r32.getMethodHandleType()) goto L7;
        return getRef().compareTo2(r32.getRef());
    L7:
        return Integer.compare(getMethodHandleType(), r32.getMethodHandleType());
    }

    public int getMethodHandleType() {
        return this.type;
    }

    public Constant getRef() {
        return this.ref;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.METHOD_HANDLE;
    }

    public boolean isAccessor() {
        return isAccessor(this.type);
    }

    @Override // com.android.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    public boolean isInvocation() {
        return isInvocation(this.type);
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return getMethodHandleTypeName(this.type) + "," + this.ref.toString();
    }

    public String toString() {
        return "method-handle{" + toHuman() + "}";
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "method handle";
    }
}
