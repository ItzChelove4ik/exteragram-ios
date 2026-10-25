package org.mvel2.ast;

import androidx.mediarouter.media.GlobalMediaRouter;
import java.io.Serializable;
import okhttp3.internal.url._UrlKt;
import org.mvel2.ParserConfiguration;
import org.mvel2.ParserContext;
import org.mvel2.PropertyAccessor;
import org.mvel2.compiler.Accessor;
import org.mvel2.debug.DebugTools;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.AccessorOptimizer;
import org.mvel2.optimizers.OptimizationNotSupported;
import org.mvel2.optimizers.OptimizerFactory;
import org.mvel2.util.CompilerTools;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class ASTNode implements Cloneable, Serializable {
    public static final int ARRAY_TYPE_LITERAL = 67108864;
    public static final int ASSIGN = 128;
    public static final int BLOCK_DO = 65536;
    public static final int BLOCK_DO_UNTIL = 131072;
    public static final int BLOCK_FOR = 262144;
    public static final int BLOCK_FOREACH = 4096;
    public static final int BLOCK_IF = 2048;
    public static final int BLOCK_UNTIL = 16384;
    public static final int BLOCK_WHILE = 32768;
    public static final int BLOCK_WITH = 8192;
    public static final int COLLECTION = 256;
    public static final int COMPILE_IMMEDIATE = 16;
    public static final int DEEP_PROPERTY = 2;
    public static final int DEFERRED_TYPE_RES = 8388608;
    public static final int DEOP = 268435456;
    public static final int DISCARD = 536870912;
    public static final int FQCN = 1048576;
    public static final int IDENTIFIER = 8;
    public static final int INLINE_COLLECTION = 1024;
    public static final int INVERT = 64;
    public static final int LITERAL = 1;
    public static final int NOJIT = 134217728;
    public static final int NUMERIC = 32;
    public static final int OPERATOR = 4;
    public static final int OPT_SUBTR = 524288;
    public static final int PCTX_STORED = 33554432;
    public static final int STACKLANG = 4194304;
    public static final int STRONG_TYPING = 16777216;
    public static final int THISREF = 512;
    protected volatile transient Accessor accessor;
    protected int cursorPosition;
    protected Class egressType;
    protected int endOfName;
    protected char[] expr;
    public int fields;
    protected int firstUnion;
    protected Object literal;
    protected String nameCache;
    public ASTNode nextASTNode;
    protected int offset;
    protected ParserContext pCtx;
    protected volatile Accessor safeAccessor;
    protected int start;

    public ASTNode(ParserContext r2) {
        this.fields = 0;
        this.pCtx = r2;
    }

    public ASTNode(char[] r1, int r2, int r3, int r4, ParserContext r5) {
        this(r5);
        this.fields = r4;
        this.expr = r1;
        this.start = r2;
        this.offset = r3;
        setName(r1);
    }

    private Object deop(Object r3, Object r4, VariableResolverFactory r5, RuntimeException r6) {
        if ((this.fields & 268435456) != 0) goto L12;
        this.accessor = null;
        this.fields |= 402653184;
        monitor-enter(this);
        Object r32 = getReducedValueAccelerated(r3, r4, r5);     // Catch: Throwable -> L9
        monitor-exit(this);     // Catch: Throwable -> L9
        return r32;
    L9:
        th = move-exception;
        throw th;
    L12:
        throw r6;
    }

    private int getAbsoluteFirstPart() {
        int r0 = this.fields;
        if ((r0 & 256) == 0) goto L13;
        int r02 = this.firstUnion;
        if (r02 < 0) goto L11;
        if (this.endOfName < r02) goto L11;
        return r02;
    L11:
        return this.endOfName;
    L13:
        if ((r0 & 2) != 0) goto L15;
        return -1;
    L15:
        return this.firstUnion;
    }

    private Object optimize(Object r12, Object r13, VariableResolverFactory r14) {
        int r1 = this.fields;
        if ((r1 & 268435456) == 0) goto L6;
        this.fields = r1 ^ 268435456;
    L6:
        if ((this.fields & 134217728) != 0) goto L12;
        if (r14 != null) goto L9;
    L11:
        AccessorOptimizer r15 = OptimizerFactory.getDefaultAccessorCompiler();
    L14:
        if ((this.fields & 33554432) == 0) goto L16;
        ParserContext r2 = (ParserContext) this.literal;
    L28:
        r2.optimizationNotify();     // Catch: OptimizationNotSupported -> L19
        setAccessor(r15.optimizeAccessor(r2, this.expr, this.start, this.offset, r12, r13, r14, true, this.egressType));     // Catch: OptimizationNotSupported -> L19
    L21:
        if (this.accessor != null) goto L24;
        ParserContext r6 = r2;
        return PropertyAccessor.get(this.expr, this.start, this.offset, r12, r14, r13, r6);
    L24:
        Object r22 = r15.getResultOptPass();
        if (this.egressType != null) goto L27;
        this.egressType = r15.getEgressType();
    L27:
        return r22;
    L19:
        r15 = OptimizerFactory.getAccessorCompiler(OptimizerFactory.SAFE_REFLECTIVE);
        setAccessor(r15.optimizeAccessor(r2, this.expr, this.start, this.offset, r12, r13, r14, true, null));
        goto L21
    L16:
        r2 = new ParserContext(new ParserConfiguration(CompilerTools.getInjectedImports(r14), null));
        goto L28
    L9:
        if (r14.isResolveable(getName()) == false) goto L11;
    L12:
        r15 = OptimizerFactory.getAccessorCompiler(OptimizerFactory.SAFE_REFLECTIVE);
        goto L14
    }

    public boolean canSerializeAccessor() {
        if (this.safeAccessor == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void discard() {
        this.fields |= 536870912;
    }

    public String getAbsoluteName() {
        int r0 = this.firstUnion;
        int r1 = this.start;
        if (r0 <= r1) goto L7;
        return new String(this.expr, r1, getAbsoluteFirstPart() - this.start);
    L7:
        return getName();
    }

    public String getAbsoluteRootElement() {
        if ((this.fields & GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED) == 0) goto L7;
        return new String(this.expr, this.start, getAbsoluteFirstPart());
    L7:
        return this.nameCache;
    }

    public Accessor getAccessor() {
        return this.accessor;
    }

    public ClassLoader getClassLoader() {
        ParserContext r0 = this.pCtx;
        if (r0 == null) goto L7;
        return r0.getClassLoader();
    L7:
        return Thread.currentThread().getContextClassLoader();
    }

    public int getCursorPosition() {
        return this.cursorPosition;
    }

    public Class getEgressType() {
        return this.egressType;
    }

    public char[] getExpr() {
        return this.expr;
    }

    public int getFields() {
        return this.fields;
    }

    public Object getLiteralValue() {
        return this.literal;
    }

    public String getName() {
        String r0 = this.nameCache;
        if (r0 == null) goto L5;
        return r0;
    L5:
        char[] r02 = this.expr;
        if (r02 == null) goto L9;
        String r1 = new String(r02, this.start, this.offset);
        this.nameCache = r1;
        return r1;
    L9:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public char[] getNameAsArray() {
        char[] r0 = this.expr;
        int r1 = this.start;
        return ParseTools.subArray(r0, r1, this.offset + r1);
    }

    public int getOffset() {
        return this.offset;
    }

    public Integer getOperator() {
        return -1;
    }

    public Object getReducedValue(Object r8, Object r9, VariableResolverFactory r10) {
        if ((this.fields & 1) == 0) goto L7;
        return this.literal;
    L7:
        return PropertyAccessor.get(this.expr, this.start, this.offset, r8, r10, r9, this.pCtx);
    }

    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        if (this.accessor == null) goto L10;
        return this.accessor.getValue(r2, r3, r4);
    L6:
        e = move-exception;
        return this.deop(r2, r3, r4, e);
    L10:
        return optimize(r2, r3, r4);
    }

    public int getStart() {
        return this.start;
    }

    public boolean isAssignment() {
        if ((this.fields & 128) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isCollection() {
        if ((this.fields & 256) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isDebuggingSymbol() {
        if (this.fields != (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isDeepProperty() {
        if ((this.fields & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isDiscard() {
        int r1 = this.fields;
        if (r1 != (-1)) goto L5;
        return false;
    L5:
        if ((r1 & 536870912) == 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isFQCN() {
        if ((this.fields & 1048576) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isIdentifier() {
        if ((this.fields & 8) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isLiteral() {
        if ((this.fields & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isOperator() {
        if ((this.fields & 4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isOperator(Integer r2) {
        if ((this.fields & 4) != 0) goto L5;
        return false;
    L5:
        if (r2.equals(this.literal) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isThisVal() {
        if ((this.fields & 512) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public Accessor setAccessor(Accessor r1) {
        this.accessor = r1;
        return r1;
    }

    public void setAsFQCNReference() {
        this.fields |= 1048576;
    }

    public void setAsLiteral() {
        this.fields |= 1;
    }

    public void setCursorPosition(int r1) {
        this.cursorPosition = r1;
    }

    public void setEgressType(Class r1) {
        this.egressType = r1;
    }

    public void setLiteralValue(Object r1) {
        this.literal = r1;
        this.fields |= 1;
    }

    public void setName(char[] r5) {
        boolean r0 = ParseTools.isNumber(r5, this.start, this.offset);
        int r1 = this.start;
        if (r0 == false) goto L10;
        Object r52 = ParseTools.handleNumericConversion(r5, r1, this.offset);
        this.literal = r52;
        this.egressType = r52.getClass();
        int r53 = this.fields | 41;
        this.fields = r53;
        if ((r53 & 64) != 0) goto L42;
        return;
    L42:
        this.literal = Integer.valueOf(~((Integer) this.literal).intValue());     // Catch: ClassCastException -> L8
        return;
    L8:
        Sign$$ExternalSyntheticBUOutline0.m("bitwise (~) operator can only be applied to integers", this.expr, this.start);
        return;
    L10:
        this.literal = new String(r5, r1, this.offset);
        int r02 = this.start;
        int r12 = this.offset + r02;
    L11:
        if (r02 >= r12) goto L33;
        char r2 = r5[r02];
        if (r2 == '(') goto L23;
        if (r2 == '.') goto L20;
        if (r2 == '[') goto L23;
    L32:
        r02 = r02 + 1;
        goto L11
    L20:
        if (this.firstUnion != 0) goto L32;
        this.firstUnion = r02;
    L23:
        if (this.firstUnion != 0) goto L26;
        this.firstUnion = r02;
    L26:
        if (this.endOfName != 0) goto L32;
        this.endOfName = r02;
        if (r02 >= r5.length) goto L33;
        if (r5[r02 + 1] != ']') goto L33;
        this.fields |= 67108864;
    L33:
        int r54 = this.fields;
        if ((r54 & 1024) == 0) goto L37;
        return;
    L37:
        if (this.firstUnion <= this.start) goto L40;
        this.fields = r54 | 10;
        return;
    L40:
        this.fields = r54 | 8;
    }

    public void storeInLiteralRegister(Object r1) {
        this.literal = r1;
    }

    public void storePctx() {
        this.fields |= 33554432;
    }

    public void strongTyping() {
        this.fields |= 16777216;
    }

    public String toString() {
        if (isOperator() == false) goto L7;
        return "<<" + DebugTools.getOperatorName(getOperator().intValue()) + ">>";
    L7:
        if ((33554432 & this.fields) == 0) goto L11;
        return this.nameCache;
    L11:
        return new String(this.expr, this.start, this.offset);
    }
}
