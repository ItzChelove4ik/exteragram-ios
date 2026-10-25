package com.android.dx.cf.direct;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.iface.AttributeList;
import com.android.dx.cf.iface.Member;
import com.android.dx.cf.iface.ParseException;
import com.android.dx.cf.iface.ParseObserver;
import com.android.dx.cf.iface.StdAttributeList;
import com.android.dx.rop.cst.ConstantPool;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;

/* loaded from: classes4.dex */
abstract class MemberListParser {
    private final AttributeFactory attributeFactory;
    private final DirectClassFile cf;
    private final CstType definer;
    private int endOffset;
    private ParseObserver observer;
    private final int offset;

    public MemberListParser(DirectClassFile r2, CstType r3, int r4, AttributeFactory r5) {
        if (r2 == null) goto L12;
        if (r4 < 0) goto L10;
        if (r5 == null) goto L8;
        this.cf = r2;
        this.definer = r3;
        this.offset = r4;
        this.attributeFactory = r5;
        this.endOffset = -1;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("attributeFactory == null");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline1.m("offset < 0");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("cf == null");
        throw null;
    }

    private void parse() {
        int r0 = getAttributeContext();
        int r5 = getCount();
        int r6 = this.offset + 2;
        ByteArray r10 = this.cf.getBytes();
        ConstantPool r8 = this.cf.getConstantPool();
        ParseObserver r9 = this.observer;
        if (r9 == null) goto L5;
        r9.parsed(r10, this.offset, 2, humanName() + "s_count: " + Hex.u2(r5));
    L5:
        int r92 = 0;
    L6:
        if (r92 >= r5) goto L31;
        int r11 = r10.getUnsignedShort(r6);     // Catch: RuntimeException -> L10 ParseException -> L12
        int r12 = r6 + 2;     // Catch: RuntimeException -> L10 ParseException -> L12
        int r13 = r10.getUnsignedShort(r12);     // Catch: RuntimeException -> L10 ParseException -> L12
        int r14 = r6 + 4;     // Catch: RuntimeException -> L10 ParseException -> L12
        int r7 = r10.getUnsignedShort(r14);     // Catch: RuntimeException -> L10 ParseException -> L12
        CstString r132 = (CstString) r8.get(r13);     // Catch: RuntimeException -> L10 ParseException -> L12
        CstString r72 = (CstString) r8.get(r7);     // Catch: RuntimeException -> L10 ParseException -> L12
        ParseObserver r15 = this.observer;     // Catch: RuntimeException -> L10 ParseException -> L12
        if (r15 == null) goto L14;
        int r17 = r5;
        ConstantPool r18 = r8;
        r15.startParsingMember(r10, r6, r132.getString(), r72.getString());     // Catch: RuntimeException -> L10 ParseException -> L12
        this.observer.parsed(r10, r6, 0, "\n" + humanName() + "s[" + r92 + "]:\n");     // Catch: RuntimeException -> L10 ParseException -> L12
        this.observer.changeIndent(1);     // Catch: RuntimeException -> L10 ParseException -> L12
        ParseObserver r52 = this.observer;     // Catch: RuntimeException -> L10 ParseException -> L12
        StringBuilder r82 = new StringBuilder();     // Catch: RuntimeException -> L10 ParseException -> L12
        r82.append("access_flags: ");     // Catch: RuntimeException -> L10 ParseException -> L12
        r82.append(humanAccessFlags(r11));     // Catch: RuntimeException -> L10 ParseException -> L12
        r52.parsed(r10, r6, 2, r82.toString());     // Catch: RuntimeException -> L10 ParseException -> L12
        this.observer.parsed(r10, r12, 2, "name: " + r132.toHuman());     // Catch: RuntimeException -> L10 ParseException -> L12
        char r152 = 2;
        this.observer.parsed(r10, r14, 2, "descriptor: " + r72.toHuman());     // Catch: RuntimeException -> L10 ParseException -> L12
    L15:
        AttributeListParser r53 = new AttributeListParser(this.cf, r0, r6 + 6, this.attributeFactory);     // Catch: RuntimeException -> L10 ParseException -> L12
        r53.setObserver(this.observer);     // Catch: RuntimeException -> L10 ParseException -> L12
        int r62 = r53.getEndOffset();     // Catch: RuntimeException -> L10 ParseException -> L12
        StdAttributeList r54 = r53.getList();     // Catch: RuntimeException -> L10 ParseException -> L12
        r54.setImmutable();     // Catch: RuntimeException -> L10 ParseException -> L12
        Member r142 = set(r92, r11, new CstNat(r132, r72), r54);     // Catch: RuntimeException -> L10 ParseException -> L12
        ParseObserver r55 = this.observer;     // Catch: RuntimeException -> L10 ParseException -> L12
        if (r55 == null) goto L25;
        r55.changeIndent(-1);     // Catch: RuntimeException -> L10 ParseException -> L12
        this.observer.parsed(r10, r62, 0, "end " + humanName() + "s[" + r92 + "]\n");     // Catch: RuntimeException -> L10 ParseException -> L12
        int r56 = r92;
        int r112 = r62;
        this.observer.endParsingMember(r10, r112, r132.getString(), r72.getString(), r142);     // Catch: RuntimeException -> L21 ParseException -> L23
    L26:
        r92 = r56 + 1;
        r6 = r112;
        r5 = r17;
        r8 = r18;
    L23:
        e = e;
    L29:
        e.addContext("...while parsing " + humanName() + "s[" + r56 + "]");
        throw e;
    L21:
        e = e;
    L27:
        ParseException r63 = new ParseException(e);
        r63.addContext("...while parsing " + humanName() + "s[" + r56 + "]");
        throw r63;
    L25:
        r112 = r62;
        r56 = r92;
        goto L26
    L14:
        r17 = r5;
        r18 = r8;
        r152 = 2;
        goto L15
    L12:
        e = e;
        r56 = r92;
    L10:
        e = e;
        r56 = r92;
        goto L27
    L31:
        this.endOffset = r6;
    }

    public abstract int getAttributeContext();

    public final int getCount() {
        return this.cf.getBytes().getUnsignedShort(this.offset);
    }

    public final CstType getDefiner() {
        return this.definer;
    }

    public int getEndOffset() {
        parseIfNecessary();
        return this.endOffset;
    }

    public abstract String humanAccessFlags(int r1);

    public abstract String humanName();

    public final void parseIfNecessary() {
        if (this.endOffset >= 0) goto L6;
        parse();
        return;
    }

    public abstract Member set(int r1, int r2, CstNat r3, AttributeList r4);

    public final void setObserver(ParseObserver r1) {
        this.observer = r1;
    }
}
