package com.android.dx.cf.direct;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.iface.Attribute;
import com.android.dx.cf.iface.ParseException;
import com.android.dx.cf.iface.ParseObserver;
import com.android.dx.cf.iface.StdAttributeList;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;

/* loaded from: classes4.dex */
final class AttributeListParser {
    private final AttributeFactory attributeFactory;
    private final DirectClassFile cf;
    private final int context;
    private int endOffset;
    private final StdAttributeList list;
    private ParseObserver observer;
    private final int offset;

    public AttributeListParser(DirectClassFile r2, int r3, int r4, AttributeFactory r5) {
        if (r2 == null) goto L9;
        if (r5 == null) goto L7;
        int r0 = r2.getBytes().getUnsignedShort(r4);
        this.cf = r2;
        this.context = r3;
        this.offset = r4;
        this.attributeFactory = r5;
        this.list = new StdAttributeList(r0);
        this.endOffset = -1;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("attributeFactory == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("cf == null");
        throw null;
    }

    private void parse() {
        int r2 = this.list.size();
        int r3 = this.offset + 2;
        ByteArray r5 = this.cf.getBytes();
        ParseObserver r6 = this.observer;
        if (r6 == null) goto L5;
        r6.parsed(r5, this.offset, 2, "attributes_count: " + Hex.u2(r2));
    L5:
        int r62 = 0;
    L6:
        if (r62 >= r2) goto L22;
        ParseObserver r7 = this.observer;     // Catch: RuntimeException -> L10 ParseException -> L12
        if (r7 == null) goto L14;
        r7.parsed(r5, r3, 0, "\nattributes[" + r62 + "]:\n");     // Catch: RuntimeException -> L10 ParseException -> L12
        this.observer.changeIndent(1);     // Catch: RuntimeException -> L10 ParseException -> L12
    L14:
        Attribute r72 = this.attributeFactory.parse(this.cf, this.context, r3, this.observer);     // Catch: RuntimeException -> L10 ParseException -> L12
        r3 = r3 + r72.byteLength();     // Catch: RuntimeException -> L10 ParseException -> L12
        this.list.set(r62, r72);     // Catch: RuntimeException -> L10 ParseException -> L12
        ParseObserver r73 = this.observer;     // Catch: RuntimeException -> L10 ParseException -> L12
        if (r73 == null) goto L17;
        r73.changeIndent(-1);     // Catch: RuntimeException -> L10 ParseException -> L12
        this.observer.parsed(r5, r3, 0, "end attributes[" + r62 + "]\n");     // Catch: RuntimeException -> L10 ParseException -> L12
    L17:
        r62 = r62 + 1;
        goto L6
    L12:
        e = move-exception;
        e.addContext("...while parsing attributes[" + r62 + "]");
        throw e;
    L10:
        e = move-exception;
        ParseException r22 = new ParseException(e);
        r22.addContext("...while parsing attributes[" + r62 + "]");
        throw r22;
    L22:
        this.endOffset = r3;
    }

    private void parseIfNecessary() {
        if (this.endOffset >= 0) goto L6;
        parse();
        return;
    }

    public int getEndOffset() {
        parseIfNecessary();
        return this.endOffset;
    }

    public StdAttributeList getList() {
        parseIfNecessary();
        return this.list;
    }

    public void setObserver(ParseObserver r1) {
        this.observer = r1;
    }
}
