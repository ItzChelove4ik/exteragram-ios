package org.mvel2.ast;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class Sign extends ASTNode {
    private Signer signer;
    private ExecutableStatement stmt;

    /* renamed from: org.mvel2.ast.Sign$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public class BigDecSigner implements Signer {
        final /* synthetic */ Sign this$0;

        private BigDecSigner(Sign r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ BigDecSigner(Sign r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // org.mvel2.ast.Sign.Signer
        public Object sign(Object r3) {
            return new BigDecimal(-((BigDecimal) r3).doubleValue());
        }
    }

    public class BigIntSigner implements Signer {
        final /* synthetic */ Sign this$0;

        private BigIntSigner(Sign r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ BigIntSigner(Sign r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // org.mvel2.ast.Sign.Signer
        public Object sign(Object r3) {
            return new BigInteger(String.valueOf(-((BigInteger) r3).longValue()));
        }
    }

    public class DoubleSigner implements Signer {
        final /* synthetic */ Sign this$0;

        private DoubleSigner(Sign r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ DoubleSigner(Sign r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // org.mvel2.ast.Sign.Signer
        public Object sign(Object r1) {
            return Double.valueOf(-((Double) r1).doubleValue());
        }
    }

    public class FloatSigner implements Signer {
        final /* synthetic */ Sign this$0;

        private FloatSigner(Sign r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ FloatSigner(Sign r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // org.mvel2.ast.Sign.Signer
        public Object sign(Object r1) {
            return Float.valueOf(-((Float) r1).floatValue());
        }
    }

    public class IntegerSigner implements Signer {
        final /* synthetic */ Sign this$0;

        private IntegerSigner(Sign r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ IntegerSigner(Sign r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // org.mvel2.ast.Sign.Signer
        public Object sign(Object r1) {
            return Integer.valueOf(-((Integer) r1).intValue());
        }
    }

    public class LongSigner implements Signer {
        final /* synthetic */ Sign this$0;

        private LongSigner(Sign r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ LongSigner(Sign r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // org.mvel2.ast.Sign.Signer
        public Object sign(Object r1) {
            return Long.valueOf(-((Long) r1).longValue());
        }
    }

    public class ShortSigner implements Signer {
        final /* synthetic */ Sign this$0;

        private ShortSigner(Sign r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ShortSigner(Sign r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // org.mvel2.ast.Sign.Signer
        public Object sign(Object r1) {
            return Integer.valueOf(-((Short) r1).shortValue());
        }
    }

    public interface Signer extends Serializable {
        Object sign(Object r1);
    }

    public Sign(char[] r1, int r2, int r3, int r4, ParserContext r5) {
        super(r5);
        this.expr = r1;
        int r22 = r2 + 1;
        this.start = r22;
        int r32 = r3 - 1;
        this.offset = r32;
        this.fields = r4;
        if ((r4 & 16) == 0) goto L10;
        ExecutableStatement r12 = (ExecutableStatement) ParseTools.subCompileExpression(r1, r22, r32, r5);
        this.stmt = r12;
        Class r13 = r12.getKnownEgressType();
        this.egressType = r13;
        if (r13 != null) goto L7;
        return;
    L7:
        if (r13 == Object.class) goto L12;
        initSigner(r13);
        return;
    L12:
        return;
    }

    private void initSigner(Class r3) {
        Class<?> r32 = ParseTools.boxPrimitive(r3);
        if (Integer.class.isAssignableFrom(r32) == false) goto L7;
        this.signer = new IntegerSigner(this, null);
        return;
    L7:
        if (Double.class.isAssignableFrom(r32) == false) goto L11;
        this.signer = new DoubleSigner(this, null);
        return;
    L11:
        if (Long.class.isAssignableFrom(r32) == false) goto L15;
        this.signer = new LongSigner(this, null);
        return;
    L15:
        if (Float.class.isAssignableFrom(r32) == false) goto L19;
        this.signer = new FloatSigner(this, null);
        return;
    L19:
        if (Short.class.isAssignableFrom(r32) == false) goto L23;
        this.signer = new ShortSigner(this, null);
        return;
    L23:
        if (BigInteger.class.isAssignableFrom(r32) == false) goto L27;
        this.signer = new BigIntSigner(this, null);
        return;
    L27:
        if (BigDecimal.class.isAssignableFrom(r32) == false) goto L30;
        this.signer = new BigDecSigner(this, null);
        return;
    L30:
        Sign$$ExternalSyntheticBUOutline0.m("illegal use of '-': cannot be applied to: ".concat(r32.getName()), this.expr, this.start);
    }

    private Object sign(Object r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        if (this.signer != null) goto L14;
        Class r0 = this.egressType;
        if (r0 != null) goto L10;
    L11:
        this.egressType = r3.getClass();
    L12:
        initSigner(this.egressType);
        goto L14
    L10:
        if (r0 != Object.class) goto L12;
    L14:
        return this.signer.sign(r3);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r3, Object r4, VariableResolverFactory r5) {
        return sign(MVEL.eval(this.expr, this.start, this.offset, r4, r5));
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        return sign(this.stmt.getValue(r2, r3, r4));
    }

    public ExecutableStatement getStatement() {
        return this.stmt;
    }

    @Override // org.mvel2.ast.ASTNode
    public boolean isIdentifier() {
        return false;
    }
}
