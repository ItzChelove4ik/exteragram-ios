package com.android.dx.rop.cst;

import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.Type;

/* loaded from: classes.dex */
public abstract class CstBaseMethodRef extends CstMemberRef {
    private Prototype instancePrototype;
    private final Prototype prototype;

    public CstBaseMethodRef(CstType r1, CstNat r2) {
        super(r1, r2);
        String r12 = getNat().getDescriptor().getString();
        if (isSignaturePolymorphic() == false) goto L5;
        this.prototype = Prototype.fromDescriptor(r12);
    L6:
        this.instancePrototype = null;
        return;
    L5:
        this.prototype = Prototype.intern(r12);
        goto L6
    }

    @Override // com.android.dx.rop.cst.CstMemberRef, com.android.dx.rop.cst.Constant
    public final int compareTo0(Constant r2) {
        int r0 = super.compareTo0(r2);
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return this.prototype.compareTo2(((CstBaseMethodRef) r2).prototype);
    }

    public final int getParameterWordCount(boolean r1) {
        return getPrototype(r1).getParameterTypes().getWordCount();
    }

    public final Prototype getPrototype() {
        return this.prototype;
    }

    public final Prototype getPrototype(boolean r2) {
        if (r2 == false) goto L6;
        return this.prototype;
    L6:
        if (this.instancePrototype != null) goto L9;
        Type r22 = getDefiningClass().getClassType();
        this.instancePrototype = this.prototype.withFirstParameter(r22);
    L9:
        return this.instancePrototype;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public final Type getType() {
        return this.prototype.getReturnType();
    }

    public final boolean isClassInit() {
        return getNat().isClassInit();
    }

    public final boolean isInstanceInit() {
        return getNat().isInstanceInit();
    }

    public final boolean isSignaturePolymorphic() {
        CstType r0 = getDefiningClass();
        if (r0.equals(CstType.METHOD_HANDLE) == false) goto L11;
        String r4 = getNat().getName().getString();
        r4.getClass();
        if (r4.equals("invoke") == false) goto L7;
    L9:
        return true;
    L7:
        if (r4.equals("invokeExact") == true) goto L9;
    L142:
        return false;
    L11:
        if (r0.equals(CstType.VAR_HANDLE) == false) goto L142;
        String r42 = getNat().getName().getString();
        r42.getClass();
        char r1 = 65535;
        switch(r42.hashCode()) {
            case -1946504908: goto L136;
            case -1686727776: goto L132;
            case -1671098288: goto L128;
            case -1292078254: goto L124;
            case -1117944904: goto L120;
            case -1103072857: goto L116;
            case -1032914329: goto L112;
            case -1032892181: goto L108;
            case -794517348: goto L104;
            case -567150350: goto L100;
            case -240822786: goto L96;
            case -230706875: goto L92;
            case -127361888: goto L88;
            case -37641530: goto L84;
            case 102230: goto L80;
            case 113762: goto L76;
            case 93645315: goto L72;
            case 101293086: goto L68;
            case 189872914: goto L64;
            case 282707520: goto L60;
            case 282724865: goto L56;
            case 353422447: goto L52;
            case 470702883: goto L48;
            case 685319959: goto L44;
            case 748071969: goto L40;
            case 937077366: goto L36;
            case 1245632875: goto L32;
            case 1352153939: goto L28;
            case 1483964149: goto L24;
            case 2002508693: goto L20;
            case 2013994287: goto L16;
            default: goto L139;
        };
    L139:
        switch(r1) {
            case 0: goto L141;
            case 1: goto L141;
            case 2: goto L141;
            case 3: goto L141;
            case 4: goto L141;
            case 5: goto L141;
            case 6: goto L141;
            case 7: goto L141;
            case 8: goto L141;
            case 9: goto L141;
            case 10: goto L141;
            case 11: goto L141;
            case 12: goto L141;
            case 13: goto L141;
            case 14: goto L141;
            case 15: goto L141;
            case 16: goto L141;
            case 17: goto L141;
            case 18: goto L141;
            case 19: goto L141;
            case 20: goto L141;
            case 21: goto L141;
            case 22: goto L141;
            case 23: goto L141;
            case 24: goto L141;
            case 25: goto L141;
            case 26: goto L141;
            case 27: goto L141;
            case 28: goto L141;
            case 29: goto L141;
            case 30: goto L141;
            default: goto L142;
        };
    L141:
        return true;
    L16:
        if (r42.equals("weakCompareAndSetRelease") == false) goto L139;
        r1 = 30;
        goto L139
    L20:
        if (r42.equals("getAndSetAcquire") == false) goto L139;
        r1 = 29;
        goto L139
    L24:
        if (r42.equals("compareAndExchange") == false) goto L139;
        r1 = 28;
        goto L139
    L28:
        if (r42.equals("getAndBitwiseOr") == false) goto L139;
        r1 = 27;
        goto L139
    L32:
        if (r42.equals("getAndBitwiseXorAcquire") == false) goto L139;
        r1 = 26;
        goto L139
    L36:
        if (r42.equals("getAndAddAcquire") == false) goto L139;
        r1 = 25;
        goto L139
    L40:
        if (r42.equals("compareAndExchangeAcquire") == false) goto L139;
        r1 = 24;
        goto L139
    L44:
        if (r42.equals("getOpaque") == false) goto L139;
        r1 = 23;
        goto L139
    L48:
        if (r42.equals("setOpaque") == false) goto L139;
        r1 = 22;
        goto L139
    L52:
        if (r42.equals("getAndBitwiseAndAcquire") == false) goto L139;
        r1 = 21;
        goto L139
    L56:
        if (r42.equals("getAndSet") == false) goto L139;
        r1 = 20;
        goto L139
    L60:
        if (r42.equals("getAndAdd") == false) goto L139;
        r1 = 19;
        goto L139
    L64:
        if (r42.equals("getVolatile") == false) goto L139;
        r1 = 18;
        goto L139
    L68:
        if (r42.equals("setVolatile") == false) goto L139;
        r1 = 17;
        goto L139
    L72:
        if (r42.equals("getAndBitwiseOrAcquire") == false) goto L139;
        r1 = 16;
        goto L139
    L76:
        if (r42.equals("set") == false) goto L139;
        r1 = 15;
        goto L139
    L80:
        if (r42.equals("get") == false) goto L139;
        r1 = 14;
        goto L139
    L84:
        if (r42.equals("getAndSetRelease") == false) goto L139;
        r1 = '\r';
        goto L139
    L88:
        if (r42.equals("getAcquire") == false) goto L139;
        r1 = '\f';
        goto L139
    L92:
        if (r42.equals("setRelease") == false) goto L139;
        r1 = 11;
        goto L139
    L96:
        if (r42.equals("weakCompareAndSetAcquire") == false) goto L139;
        r1 = '\n';
        goto L139
    L100:
        if (r42.equals("weakCompareAndSetPlain") == false) goto L139;
        r1 = '\t';
        goto L139
    L104:
        if (r42.equals("getAndBitwiseXorRelease") == false) goto L139;
        r1 = '\b';
        goto L139
    L108:
        if (r42.equals("getAndBitwiseXor") == false) goto L139;
        r1 = 7;
        goto L139
    L112:
        if (r42.equals("getAndBitwiseAnd") == false) goto L139;
        r1 = 6;
        goto L139
    L116:
        if (r42.equals("getAndAddRelease") == false) goto L139;
        r1 = 5;
        goto L139
    L120:
        if (r42.equals("weakCompareAndSet") == false) goto L139;
        r1 = 4;
        goto L139
    L124:
        if (r42.equals("compareAndExchangeRelease") == false) goto L139;
        r1 = 3;
        goto L139
    L128:
        if (r42.equals("compareAndSet") == false) goto L139;
        r1 = 2;
        goto L139
    L132:
        if (r42.equals("getAndBitwiseAndRelease") == false) goto L139;
        r1 = 1;
        goto L139
    L136:
        if (r42.equals("getAndBitwiseOrRelease") == false) goto L139;
        r1 = 0;
        goto L139
    }
}
