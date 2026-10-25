package com.android.dx.cf.direct;

import com.android.dx.cf.iface.AttributeList;
import com.android.dx.cf.iface.Member;
import com.android.dx.cf.iface.StdMethod;
import com.android.dx.cf.iface.StdMethodList;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstType;

/* loaded from: classes4.dex */
final class MethodListParser extends MemberListParser {
    private final StdMethodList methods;

    public MethodListParser(DirectClassFile r1, CstType r2, int r3, AttributeFactory r4) {
        super(r1, r2, r3, r4);
        this.methods = new StdMethodList(getCount());
    }

    @Override // com.android.dx.cf.direct.MemberListParser
    public int getAttributeContext() {
        return 2;
    }

    public StdMethodList getList() {
        parseIfNecessary();
        return this.methods;
    }

    @Override // com.android.dx.cf.direct.MemberListParser
    public String humanAccessFlags(int r1) {
        return AccessFlags.methodString(r1);
    }

    @Override // com.android.dx.cf.direct.MemberListParser
    public String humanName() {
        return "method";
    }

    @Override // com.android.dx.cf.direct.MemberListParser
    public Member set(int r3, int r4, CstNat r5, AttributeList r6) {
        StdMethod r0 = new StdMethod(getDefiner(), r4, r5, r6);
        this.methods.set(r3, r0);
        return r0;
    }
}
