package com.android.dx.cf.direct;

import com.android.dx.cf.iface.AttributeList;
import com.android.dx.cf.iface.Member;
import com.android.dx.cf.iface.StdField;
import com.android.dx.cf.iface.StdFieldList;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstType;

/* loaded from: classes4.dex */
final class FieldListParser extends MemberListParser {
    private final StdFieldList fields;

    public FieldListParser(DirectClassFile r1, CstType r2, int r3, AttributeFactory r4) {
        super(r1, r2, r3, r4);
        this.fields = new StdFieldList(getCount());
    }

    @Override // com.android.dx.cf.direct.MemberListParser
    public int getAttributeContext() {
        return 1;
    }

    public StdFieldList getList() {
        parseIfNecessary();
        return this.fields;
    }

    @Override // com.android.dx.cf.direct.MemberListParser
    public String humanAccessFlags(int r1) {
        return AccessFlags.fieldString(r1);
    }

    @Override // com.android.dx.cf.direct.MemberListParser
    public String humanName() {
        return "field";
    }

    @Override // com.android.dx.cf.direct.MemberListParser
    public Member set(int r3, int r4, CstNat r5, AttributeList r6) {
        StdField r0 = new StdField(getDefiner(), r4, r5, r6);
        this.fields.set(r3, r0);
        return r0;
    }
}
