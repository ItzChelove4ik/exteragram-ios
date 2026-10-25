package com.android.dx.dex.code;

import com.android.dex.util.ExceptionWithContext;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstBaseMethodRef;
import com.android.dx.rop.cst.CstCallSiteRef;
import com.android.dx.rop.cst.CstProtoRef;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.FixedSizeList;
import com.android.dx.util.IndentingWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class DalvInsnList extends FixedSizeList {
    private final int regCount;

    public DalvInsnList(int r1, int r2) {
        super(r1);
        this.regCount = r2;
    }

    public static DalvInsnList makeImmutable(ArrayList<DalvInsn> r3, int r4) {
        int r0 = r3.size();
        DalvInsnList r1 = new DalvInsnList(r0, r4);
        int r42 = 0;
    L3:
        if (r42 >= r0) goto L5;
        r1.set(r42, r3.get(r42));
        r42 = r42 + 1;
        goto L3
    L5:
        r1.setImmutable();
        return r1;
    }

    public int codeSize() {
        int r0 = size();
        if (r0 != 0) goto L7;
        return 0;
    L7:
        return get(r0 - 1).getNextAddress();
    }

    public void debugPrint(OutputStream r2, String r3, boolean r4) {
        OutputStreamWriter r0 = new OutputStreamWriter(r2);
        debugPrint(r0, r3, r4);
        r0.flush();     // Catch: IOException -> L5
        return;
    L5:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    public void debugPrint(Writer r5, String r6, boolean r7) {
        IndentingWriter r0 = new IndentingWriter(r5, 0, r6);
        int r52 = size();
        int r62 = 0;
    L3:
        if (r62 >= r52) goto L13;
        DalvInsn r2 = (DalvInsn) get0(r62);     // Catch: IOException -> L15
        if (r2.codeSize() != 0) goto L9;
        if (r7 == true) goto L9;
        String r22 = null;
    L10:
        if (r22 == null) goto L12;
        r0.write(r22);     // Catch: IOException -> L15
    L12:
        r62 = r62 + 1;     // Catch: IOException -> L15
    L9:
        r22 = r2.listingString(_UrlKt.FRAGMENT_ENCODE_SET, 0, r7);     // Catch: IOException -> L15
    L15:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return;
    L13:
        r0.flush();     // Catch: IOException -> L15
    }

    public DalvInsn get(int r1) {
        return (DalvInsn) get0(r1);
    }

    public int getOutsSize() {
        int r0 = size();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r0) goto L27;
        DalvInsn r4 = (DalvInsn) get0(r2);
        boolean r6 = true;
        if ((r4 instanceof CstInsn) == false) goto L18;
        Constant r5 = ((CstInsn) r4).getConstant();
        if ((r5 instanceof CstBaseMethodRef) == false) goto L14;
        CstBaseMethodRef r52 = (CstBaseMethodRef) r5;
        if (r4.getOpcode().getFamily() == 113) goto L12;
        r6 = false;
    L12:
        int r42 = r52.getParameterWordCount(r6);
    L22:
        if (r42 <= r3) goto L26;
        r3 = r42;
    L26:
        r2 = r2 + 1;
        goto L3
    L14:
        if ((r5 instanceof CstCallSiteRef) == false) goto L16;
        r42 = ((CstCallSiteRef) r5).getPrototype().getParameterTypes().getWordCount();
        goto L22
    L16:
        r42 = 0;
        goto L22
    L18:
        if ((r4 instanceof MultiCstInsn) == false) goto L26;
        if (r4.getOpcode().getFamily() != 250) goto L24;
        r42 = ((CstProtoRef) ((MultiCstInsn) r4).getConstant(1)).getPrototype().getParameterTypes().getWordCount() + 1;
        goto L22
    L24:
        GlShader$$ExternalSyntheticBUOutline1.m("Expecting invoke-polymorphic");
        return 0;
    L27:
        return r3;
    }

    public int getRegistersSize() {
        return this.regCount;
    }

    public void set(int r1, DalvInsn r2) {
        set0(r1, r2);
    }

    public void writeTo(AnnotatedOutput r11) {
        int r0 = r11.getCursor();
        int r1 = size();
        int r3 = 0;
        if (r11.annotates() == false) goto L17;
        boolean r2 = r11.isVerbose();
        int r4 = 0;
    L5:
        if (r4 >= r1) goto L17;
        DalvInsn r5 = (DalvInsn) get0(r4);
        int r6 = r5.codeSize() * 2;
        if (r6 != 0) goto L11;
        if (r2 == true) goto L11;
        String r52 = null;
    L12:
        if (r52 == null) goto L14;
        r11.annotate(r6, r52);
    L16:
        r4 = r4 + 1;
        goto L5
    L14:
        if (r6 == 0) goto L16;
        r11.annotate(r6, _UrlKt.FRAGMENT_ENCODE_SET);
    L11:
        r52 = r5.listingString("  ", r11.getAnnotationWidth(), true);
    L17:
        if (r3 >= r1) goto L24;
        DalvInsn r22 = (DalvInsn) get0(r3);
        r22.writeTo(r11);     // Catch: RuntimeException -> L21
        r3 = r3 + 1;
    L21:
        e = move-exception;
        throw ExceptionWithContext.withContext(e, "...while writing " + r22);
    L24:
        int r112 = (r11.getCursor() - r0) / 2;
        if (r112 != codeSize()) goto L27;
        return;
    L27:
        DalvInsnList$$ExternalSyntheticBUOutline0.m("write length mismatch; expected ", codeSize(), " but actually wrote ", r112);
    }
}
