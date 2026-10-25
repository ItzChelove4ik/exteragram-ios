package org.aspectj.runtime.reflect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;

/* loaded from: classes5.dex */
class JoinPointImpl implements JoinPoint {
    Object _this;
    Object[] args;
    JoinPoint.StaticPart staticPart;
    Object target;

    public static class StaticPartImpl implements JoinPoint.StaticPart {
        private int id;
        String kind;
        Signature signature;
        SourceLocation sourceLocation;

        public StaticPartImpl(int r1, String r2, Signature r3, SourceLocation r4) {
            this.kind = r2;
            this.signature = r3;
            this.sourceLocation = r4;
            this.id = r1;
        }

        public String getKind() {
            return this.kind;
        }

        public Signature getSignature() {
            return this.signature;
        }

        @Override // org.aspectj.lang.JoinPoint.StaticPart
        public final String toString() {
            return toString(StringMaker.middleStringMaker);
        }

        public String toString(StringMaker r3) {
            StringBuffer r0 = new StringBuffer();
            r0.append(r3.makeKindName(getKind()));
            r0.append("(");
            r0.append(((SignatureImpl) getSignature()).toString(r3));
            r0.append(")");
            return r0.toString();
        }
    }

    public JoinPointImpl(JoinPoint.StaticPart r1, Object r2, Object r3, Object[] r4) {
        this.staticPart = r1;
        this._this = r2;
        this.target = r3;
        this.args = r4;
    }

    @Override // org.aspectj.lang.JoinPoint
    public Object getTarget() {
        return this.target;
    }

    public final String toString() {
        return this.staticPart.toString();
    }
}
