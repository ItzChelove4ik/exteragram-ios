package com.android.dx.cf.direct;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.attrib.RawAttribute;
import com.android.dx.cf.iface.Attribute;
import com.android.dx.cf.iface.ParseException;
import com.android.dx.cf.iface.ParseObserver;
import com.android.dx.rop.cst.ConstantPool;
import com.android.dx.rop.cst.CstString;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class AttributeFactory {
    public static final int CTX_CLASS = 0;
    public static final int CTX_CODE = 3;
    public static final int CTX_COUNT = 4;
    public static final int CTX_FIELD = 1;
    public static final int CTX_METHOD = 2;

    public AttributeFactory() {
    }

    public final Attribute parse(DirectClassFile r12, int r13, int r14, ParseObserver r15) {
        CstString r3 = null;
        if (r12 == null) goto L23;
        if (r13 >= 0) goto L6;
    L21:
        f$$ExternalSyntheticBUOutline1.m("bad context");
        return null;
    L6:
        if (r13 >= 4) goto L21;
        ByteArray r5 = r12.getBytes();     // Catch: ParseException -> L14
        ConstantPool r7 = r12.getConstantPool();     // Catch: ParseException -> L14
        int r8 = r5.getUnsignedShort(r14);     // Catch: ParseException -> L14
        int r9 = r14 + 2;     // Catch: ParseException -> L14
        int r10 = r5.getInt(r9);     // Catch: ParseException -> L14
        CstString r72 = (CstString) r7.get(r8);     // Catch: ParseException -> L14
        if (r15 != null) goto L25;
    L12:
        return parse0(r12, r13, r72.getString(), r14 + 6, r10, r15);
    L10:
        e = e;
        r3 = r72;
    L15:
        StringBuilder r1 = new StringBuilder("...while parsing ");
        if (r3 == null) goto L18;
        String r2 = r3.toHuman() + " ";
    L19:
        r1.append(r2);
        r1.append("attribute at offset ");
        r1.append(Hex.u4(r14));
        e.addContext(r1.toString());
        throw e;
    L18:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L19
    L25:
        r15.parsed(r5, r14, 2, "name: " + r72.toHuman());     // Catch: ParseException -> L10
        r15.parsed(r5, r9, 4, "length: " + Hex.u4(r10));     // Catch: ParseException -> L10
    L14:
        e = e;
        goto L15
    L23:
        f$$ExternalSyntheticBUOutline2.m("cf == null");
        return null;
    }

    public Attribute parse0(DirectClassFile r1, int r2, String r3, int r4, int r5, ParseObserver r6) {
        ByteArray r22 = r1.getBytes();
        RawAttribute r0 = new RawAttribute(r3, r22, r4, r5, r1.getConstantPool());
        if (r6 == null) goto L5;
        r6.parsed(r22, r4, r5, "attribute data");
    L5:
        return r0;
    }
}
