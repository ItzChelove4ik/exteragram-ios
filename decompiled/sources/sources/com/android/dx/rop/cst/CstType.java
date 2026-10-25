package com.android.dx.rop.cst;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.android.dx.rop.type.Type;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class CstType extends TypedConstant {
    public static final CstType BOOLEAN = null;
    public static final CstType BOOLEAN_ARRAY = null;
    public static final CstType BYTE = null;
    public static final CstType BYTE_ARRAY = null;
    public static final CstType CHARACTER = null;
    public static final CstType CHAR_ARRAY = null;
    public static final CstType DOUBLE = null;
    public static final CstType DOUBLE_ARRAY = null;
    public static final CstType FLOAT = null;
    public static final CstType FLOAT_ARRAY = null;
    public static final CstType INTEGER = null;
    public static final CstType INT_ARRAY = null;
    public static final CstType LONG = null;
    public static final CstType LONG_ARRAY = null;
    public static final CstType METHOD_HANDLE = null;
    public static final CstType OBJECT = null;
    public static final CstType SHORT = null;
    public static final CstType SHORT_ARRAY = null;
    public static final CstType VAR_HANDLE = null;
    public static final CstType VOID = null;
    private static final ConcurrentMap<Type, CstType> interns = null;
    private CstString descriptor;
    private final Type type;

    static {
        interns = new ConcurrentHashMap(1000, 0.75f);
        OBJECT = new CstType(Type.OBJECT);
        BOOLEAN = new CstType(Type.BOOLEAN_CLASS);
        BYTE = new CstType(Type.BYTE_CLASS);
        CHARACTER = new CstType(Type.CHARACTER_CLASS);
        DOUBLE = new CstType(Type.DOUBLE_CLASS);
        FLOAT = new CstType(Type.FLOAT_CLASS);
        LONG = new CstType(Type.LONG_CLASS);
        INTEGER = new CstType(Type.INTEGER_CLASS);
        SHORT = new CstType(Type.SHORT_CLASS);
        VOID = new CstType(Type.VOID_CLASS);
        BOOLEAN_ARRAY = new CstType(Type.BOOLEAN_ARRAY);
        BYTE_ARRAY = new CstType(Type.BYTE_ARRAY);
        CHAR_ARRAY = new CstType(Type.CHAR_ARRAY);
        DOUBLE_ARRAY = new CstType(Type.DOUBLE_ARRAY);
        FLOAT_ARRAY = new CstType(Type.FLOAT_ARRAY);
        LONG_ARRAY = new CstType(Type.LONG_ARRAY);
        INT_ARRAY = new CstType(Type.INT_ARRAY);
        SHORT_ARRAY = new CstType(Type.SHORT_ARRAY);
        METHOD_HANDLE = new CstType(Type.METHOD_HANDLE);
        VAR_HANDLE = new CstType(Type.VAR_HANDLE);
        initInterns();
    }

    public CstType(Type r3) {
        if (r3 != null) goto L5;
        f$$ExternalSyntheticBUOutline2.m("type == null");
        throw null;
    L5:
        if (r3 == Type.KNOWN_NULL) goto L8;
        this.type = r3;
        this.descriptor = null;
        return;
    L8:
        ByteString$$ExternalSyntheticBUOutline0.m("KNOWN_NULL is not representable");
        throw null;
    }

    public static void clearInternTable() {
        interns.clear();
        initInterns();
    }

    public static CstType forBoxedPrimitiveType(Type r1) {
        switch(r1.getBasicType()) {
            case 0: goto L23;
            case 1: goto L21;
            case 2: goto L19;
            case 3: goto L17;
            case 4: goto L15;
            case 5: goto L13;
            case 6: goto L11;
            case 7: goto L9;
            case 8: goto L7;
            default: goto L4;
        };
    L4:
        Native$$ExternalSyntheticBUOutline5.m("not primitive: ", r1);
        return null;
    L7:
        return SHORT;
    L9:
        return LONG;
    L11:
        return INTEGER;
    L13:
        return FLOAT;
    L15:
        return DOUBLE;
    L17:
        return CHARACTER;
    L19:
        return BYTE;
    L21:
        return BOOLEAN;
    L23:
        return VOID;
    }

    private static void initInterns() {
        internInitial(OBJECT);
        internInitial(BOOLEAN);
        internInitial(BYTE);
        internInitial(CHARACTER);
        internInitial(DOUBLE);
        internInitial(FLOAT);
        internInitial(LONG);
        internInitial(INTEGER);
        internInitial(SHORT);
        internInitial(VOID);
        internInitial(BOOLEAN_ARRAY);
        internInitial(BYTE_ARRAY);
        internInitial(CHAR_ARRAY);
        internInitial(DOUBLE_ARRAY);
        internInitial(FLOAT_ARRAY);
        internInitial(LONG_ARRAY);
        internInitial(INT_ARRAY);
        internInitial(SHORT_ARRAY);
        internInitial(METHOD_HANDLE);
    }

    public static CstType intern(Type r2) {
        CstType r0 = new CstType(r2);
        CstType r22 = interns.putIfAbsent(r2, r0);
        if (r22 == null) goto L5;
        return r22;
    L5:
        return r0;
    }

    private static void internInitial(CstType r2) {
        if (interns.putIfAbsent(r2.getClassType(), r2) != null) goto L5;
        return;
    L5:
        DexMaker$$ExternalSyntheticBUOutline0.m("Attempted re-init of ", r2);
    }

    @Override // com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r1) {
        return this.type.getDescriptor().compareTo(((CstType) r1).type.getDescriptor());
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof CstType) == true) goto L6;
        return false;
    L6:
        if (this.type != ((CstType) r3).type) goto L9;
        return true;
    L9:
        return false;
    }

    public Type getClassType() {
        return this.type;
    }

    public CstString getDescriptor() {
        if (this.descriptor != null) goto L6;
        this.descriptor = new CstString(this.type.getDescriptor());
    L6:
        return this.descriptor;
    }

    public String getPackageName() {
        String r4 = getDescriptor().getString();
        int r1 = r4.lastIndexOf(47);
        int r2 = r4.lastIndexOf(91);
        if (r1 != (-1)) goto L7;
        return PluginsConstants.Settings.DEFAULT;
    L7:
        return r4.substring(r2 + 2, r1).replace('/', '.');
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.CLASS;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    @Override // com.android.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return this.type.toHuman();
    }

    public String toString() {
        return "type{" + toHuman() + '}';
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "type";
    }
}
