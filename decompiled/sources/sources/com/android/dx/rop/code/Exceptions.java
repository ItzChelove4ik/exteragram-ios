package com.android.dx.rop.code;

import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;

/* loaded from: classes.dex */
public final class Exceptions {
    public static final StdTypeList LIST_Error = null;
    public static final StdTypeList LIST_Error_ArithmeticException = null;
    public static final StdTypeList LIST_Error_ClassCastException = null;
    public static final StdTypeList LIST_Error_NegativeArraySizeException = null;
    public static final StdTypeList LIST_Error_NullPointerException = null;
    public static final StdTypeList LIST_Error_Null_ArrayIndexOutOfBounds = null;
    public static final StdTypeList LIST_Error_Null_ArrayIndex_ArrayStore = null;
    public static final StdTypeList LIST_Error_Null_IllegalMonitorStateException = null;
    public static final Type TYPE_ArithmeticException = null;
    public static final Type TYPE_ArrayIndexOutOfBoundsException = null;
    public static final Type TYPE_ArrayStoreException = null;
    public static final Type TYPE_ClassCastException = null;
    public static final Type TYPE_Error = null;
    public static final Type TYPE_IllegalMonitorStateException = null;
    public static final Type TYPE_NegativeArraySizeException = null;
    public static final Type TYPE_NullPointerException = null;

    static {
        Type r0 = Type.intern("Ljava/lang/ArithmeticException;");
        TYPE_ArithmeticException = r0;
        Type r1 = Type.intern("Ljava/lang/ArrayIndexOutOfBoundsException;");
        TYPE_ArrayIndexOutOfBoundsException = r1;
        Type r2 = Type.intern("Ljava/lang/ArrayStoreException;");
        TYPE_ArrayStoreException = r2;
        Type r3 = Type.intern("Ljava/lang/ClassCastException;");
        TYPE_ClassCastException = r3;
        Type r4 = Type.intern("Ljava/lang/Error;");
        TYPE_Error = r4;
        Type r5 = Type.intern("Ljava/lang/IllegalMonitorStateException;");
        TYPE_IllegalMonitorStateException = r5;
        Type r6 = Type.intern("Ljava/lang/NegativeArraySizeException;");
        TYPE_NegativeArraySizeException = r6;
        Type r7 = Type.intern("Ljava/lang/NullPointerException;");
        TYPE_NullPointerException = r7;
        LIST_Error = StdTypeList.make(r4);
        LIST_Error_ArithmeticException = StdTypeList.make(r4, r0);
        LIST_Error_ClassCastException = StdTypeList.make(r4, r3);
        LIST_Error_NegativeArraySizeException = StdTypeList.make(r4, r6);
        LIST_Error_NullPointerException = StdTypeList.make(r4, r7);
        LIST_Error_Null_ArrayIndexOutOfBounds = StdTypeList.make(r4, r7, r1);
        LIST_Error_Null_ArrayIndex_ArrayStore = StdTypeList.make(r4, r7, r1, r2);
        LIST_Error_Null_IllegalMonitorStateException = StdTypeList.make(r4, r7, r5);
    }

    private Exceptions() {
    }
}
