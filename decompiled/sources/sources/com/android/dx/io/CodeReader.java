package com.android.dx.io;

import com.android.dx.io.instructions.DecodedInstruction;

/* loaded from: classes4.dex */
public final class CodeReader {
    private Visitor callSiteVisitor;
    private Visitor fallbackVisitor;
    private Visitor fieldVisitor;
    private Visitor methodAndProtoVisitor;
    private Visitor methodVisitor;
    private Visitor stringVisitor;
    private Visitor typeVisitor;

    /* renamed from: com.android.dx.io.CodeReader$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$dx$io$IndexType = null;

        static {
            int[] r0 = new int[IndexType.values().length];
            $SwitchMap$com$android$dx$io$IndexType = r0;
            r0[IndexType.STRING_REF.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
        L16:
            $SwitchMap$com$android$dx$io$IndexType[IndexType.TYPE_REF.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
        L20:
            $SwitchMap$com$android$dx$io$IndexType[IndexType.FIELD_REF.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
        L26:
            $SwitchMap$com$android$dx$io$IndexType[IndexType.METHOD_REF.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
        L18:
            $SwitchMap$com$android$dx$io$IndexType[IndexType.METHOD_AND_PROTO_REF.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
        L22:
            $SwitchMap$com$android$dx$io$IndexType[IndexType.CALL_SITE_REF.ordinal()] = 6;     // Catch: NoSuchFieldError -> L15
            return;
        }
    }

    public interface Visitor {
        void visit(DecodedInstruction[] r1, DecodedInstruction r2);
    }

    public CodeReader() {
        this.fallbackVisitor = null;
        this.stringVisitor = null;
        this.typeVisitor = null;
        this.fieldVisitor = null;
        this.methodVisitor = null;
        this.methodAndProtoVisitor = null;
        this.callSiteVisitor = null;
    }

    private void callVisit(DecodedInstruction[] r3, DecodedInstruction r4) {
        switch(AnonymousClass1.$SwitchMap$com$android$dx$io$IndexType[OpcodeInfo.getIndexType(r4.getOpcode()).ordinal()]) {
            case 1: goto L10;
            case 2: goto L9;
            case 3: goto L8;
            case 4: goto L7;
            case 5: goto L6;
            case 6: goto L5;
            default: goto L4;
        };
    L4:
        Visitor r0 = null;
    L11:
        if (r0 != null) goto L13;
        r0 = this.fallbackVisitor;
    L13:
        if (r0 == null) goto L16;
        r0.visit(r3, r4);
        return;
    L16:
        return;
    L5:
        r0 = this.callSiteVisitor;
        goto L11
    L6:
        r0 = this.methodAndProtoVisitor;
        goto L11
    L7:
        r0 = this.methodVisitor;
        goto L11
    L8:
        r0 = this.fieldVisitor;
        goto L11
    L9:
        r0 = this.typeVisitor;
        goto L11
    L10:
        r0 = this.stringVisitor;
        goto L11
    }

    public void setAllVisitors(Visitor r1) {
        this.fallbackVisitor = r1;
        this.stringVisitor = r1;
        this.typeVisitor = r1;
        this.fieldVisitor = r1;
        this.methodVisitor = r1;
        this.methodAndProtoVisitor = r1;
        this.callSiteVisitor = r1;
    }

    public void setCallSiteVisitor(Visitor r1) {
        this.callSiteVisitor = r1;
    }

    public void setFallbackVisitor(Visitor r1) {
        this.fallbackVisitor = r1;
    }

    public void setFieldVisitor(Visitor r1) {
        this.fieldVisitor = r1;
    }

    public void setMethodAndProtoVisitor(Visitor r1) {
        this.methodAndProtoVisitor = r1;
    }

    public void setMethodVisitor(Visitor r1) {
        this.methodVisitor = r1;
    }

    public void setStringVisitor(Visitor r1) {
        this.stringVisitor = r1;
    }

    public void setTypeVisitor(Visitor r1) {
        this.typeVisitor = r1;
    }

    public void visitAll(DecodedInstruction[] r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        DecodedInstruction r2 = r4[r1];
        if (r2 == null) goto L8;
        callVisit(r4, r2);
    L8:
        r1 = r1 + 1;
        goto L3
    }

    public void visitAll(short[] r1) {
        visitAll(DecodedInstruction.decodeAll(r1));
    }
}
