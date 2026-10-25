package org.mvel2.ast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.url._UrlKt;
import org.mvel2.CompileException;
import org.mvel2.DataConversion;
import org.mvel2.ErrorDetail;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.PropertyAccessor;
import org.mvel2.compiler.Accessor;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.compiler.PropertyVerifier;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.AccessorOptimizer;
import org.mvel2.optimizers.OptimizerFactory;
import org.mvel2.util.ArrayTools;
import org.mvel2.util.CompilerTools;
import org.mvel2.util.ErrorUtil;
import org.mvel2.util.ParseTools;
import org.mvel2.util.ReflectionUtil;

/* loaded from: classes5.dex */
public class NewObjectNode extends ASTNode {
    private static final Class[] EMPTYCLS = null;
    private static final Logger LOG = null;
    private char[] name;
    private transient Accessor newObjectOptimizer;
    private TypeDescriptor typeDescr;

    public static class NewObjectArray implements Accessor, Serializable {
        private Class arrayType;
        private ExecutableStatement[] sizes;

        public NewObjectArray(Class r1, ExecutableStatement[] r2) {
            this.arrayType = r1;
            this.sizes = r2;
        }

        @Override // org.mvel2.compiler.Accessor
        public Class getKnownEgressType() {
            return Class.forName("[L" + this.arrayType.getName() + ";");
        L5:
            return null;
        }

        @Override // org.mvel2.compiler.Accessor
        public Object getValue(Object r6, Object r7, VariableResolverFactory r8) {
            int r0 = this.sizes.length;
            int[] r1 = new int[r0];
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L6;
            r1[r2] = ((Integer) DataConversion.convert(this.sizes[r2].getValue(r6, r7, r8), Integer.class)).intValue();
            r2 = r2 + 1;
            goto L3
        L6:
            return Array.newInstance(this.arrayType, r1);
        }

        @Override // org.mvel2.compiler.Accessor
        public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
            return null;
        }
    }

    static {
        LOG = Logger.getLogger(NewObjectNode.class.getName());
        EMPTYCLS = new Class[0];
    }

    public NewObjectNode(TypeDescriptor r9, int r10, ParserContext r11) {
        super(r11);
        this.typeDescr = r9;
        this.fields = r10;
        this.expr = r9.getExpr();
        this.start = r9.getStart();
        int r0 = r9.getOffset();
        this.offset = r0;
        char[] r1 = this.expr;
        if (r0 >= r1.length) goto L5;
        int r2 = this.start;
        this.name = ParseTools.subArray(r1, r2, r0 + r2);
    L7:
        if ((r10 & 16) == 0) goto L57;
        int r12 = 0;
        if (r11 != null) goto L11;
    L54:
        this.egressType = Class.forName(r9.getClassName(), true, getClassLoader());     // Catch: ClassNotFoundException -> L48
    L15:
        if (this.egressType == null) goto L27;
        rewriteClassReferenceToFQCN(r10);
        if (r9.isArray() == false) goto L27;
    L21:
        e = move-exception;
        LOG.log(Level.WARNING, _UrlKt.FRAGMENT_ENCODE_SET, e);
        goto L27
    L19:
        if (this.egressType.isPrimitive() == false) goto L23;
        Class<?> r102 = ReflectionUtil.toPrimitiveArrayType(this.egressType);     // Catch: Exception -> L21
    L24:
        this.egressType = r102;     // Catch: Exception -> L21
        goto L27
    L23:
        r102 = ParseTools.findClass(null, ParseTools.repeatChar('[', r9.getArrayLength()) + "L" + this.egressType.getName() + ";", r11);     // Catch: Exception -> L21
    L27:
        if (r11 != null) goto L29;
        return;
    L29:
        if (this.egressType != null) goto L33;
        r11.addError(new ErrorDetail(this.expr, this.start, true, "could not resolve class: " + r9.getClassName()));
        return;
    L33:
        if (r9.isArray() == true) goto L59;
        String[] r103 = ParseTools.captureContructorAndResidual(this.expr, this.start, this.offset);
        List<char[]> r02 = ParseTools.parseMethodOrConstructor(r103[0].toCharArray());
        int r3 = r02.size();
        Class[] r4 = new Class[r3];
    L35:
        if (r12 >= r3) goto L38;
        r4[r12] = MVEL.analyze(r02.get(r12), r11);
        r12 = r12 + 1;
        goto L35
    L38:
        if (ParseTools.getBestConstructorCandidate(r4, this.egressType, true) != null) goto L43;
        if (r11.isStrongTyping() == false) goto L43;
        r11.addError(new ErrorDetail(this.expr, this.start, r11.isStrongTyping(), "could not resolve constructor " + r9.getClassName() + Arrays.toString(r4)));
    L43:
        if (r103.length != 2) goto L60;
        String r92 = r103[1].trim();
        if (r92.length() == 0) goto L63;
        this.egressType = new PropertyVerifier(r92, r11, this.egressType).analyze();
        return;
    L63:
        return;
    L60:
        return;
    L59:
        return;
    L49:
        if (r11.isStrongTyping() == false) goto L62;
        r11.addError(new ErrorDetail(this.expr, this.start, true, "could not resolve class: " + r9.getClassName()));
        return;
    L62:
        return;
    L11:
        if (r11.hasImport(r9.getClassName()) == false) goto L54;
        r11.setAllowBootstrapBypass(false);
        this.egressType = r11.getImport(r9.getClassName());
        goto L15
    L57:
        return;
    L5:
        this.name = r1;
        goto L7
    }

    private Object createPrototypalObject(Object r2, Object r3, VariableResolverFactory r4) {
        return this.pCtx.getFunction(this.typeDescr.getClassName()).getReducedValueAccelerated(r2, r3, r4);
    }

    private boolean isPrototypeFunction() {
        return this.pCtx.getFunctions().containsKey(this.typeDescr.getClassName());
    }

    private void rewriteClassReferenceToFQCN(int r10) {
        String r0 = this.egressType.getName();
        if (this.typeDescr.getClassName().indexOf(46) != (-1)) goto L22;
        char[] r1 = this.name;
        int r12 = ArrayTools.findFirst('(', 0, r1.length, r1);
        char[] r02 = r0.toCharArray();
        if (r12 != (-1)) goto L9;
        int r13 = r02.length;
        this.name = new char[r13];
        int r2 = 0;
    L7:
        if (r2 >= r13) goto L17;
        this.name[r2] = r02[r2];
        r2 = r2 + 1;
    L17:
        TypeDescriptor r03 = this.typeDescr;
        char[] r9 = this.name;
        r03.updateClassName(r9, 0, r9.length, r10);
        return;
    L9:
        char[] r22 = new char[r02.length + (this.name.length - r12)];
        int r3 = 0;
    L11:
        if (r3 >= r02.length) goto L13;
        r22[r3] = r02[r3];
        r3 = r3 + 1;
        goto L11
    L13:
        int r32 = this.name.length - r12;
        int r04 = r02.length;
        int r4 = 0;
    L14:
        if (r4 >= r32) goto L16;
        r22[r4 + r04] = this.name[r4 + r12];
        r4 = r4 + 1;
        goto L14
    L16:
        this.name = r22;
        goto L17
    }

    public Accessor getNewObjectOptimizer() {
        return this.newObjectOptimizer;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r11, Object r12, VariableResolverFactory r13) {
        int r2 = 0;
        if (this.typeDescr.isArray() == false) goto L16;
        Class r122 = ParseTools.findClass(r13, this.typeDescr.getClassName(), this.pCtx);     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        int r1 = this.typeDescr.getArrayLength();     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        int[] r3 = new int[r1];     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        ArraySize[] r4 = this.typeDescr.getArraySize();     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
    L6:
        if (r2 >= r1) goto L15;
        r3[r2] = ((Integer) DataConversion.convert(MVEL.eval(r4[r2].value, r11, r13), Integer.class)).intValue();     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        r2 = r2 + 1;     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        goto L6
    L15:
        return Array.newInstance(r122, r3);
    L16:
        char[] r14 = this.name;     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        String[] r15 = ParseTools.captureContructorAndResidual(r14, 0, r14.length);     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        List<char[]> r32 = ParseTools.parseMethodOrConstructor(r15[0].toCharArray());     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        if (r32 == null) goto L34;
        char[] r6 = this.name;     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        Class r5 = ParseTools.findClass(r13, new String(ParseTools.subset(r6, 0, ArrayTools.findFirst('(', 0, r6.length, r6))).trim(), this.pCtx);     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        int r62 = r32.size();     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        Object[] r7 = new Object[r62];     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        int r8 = 0;
    L20:
        if (r8 >= r32.size()) goto L22;
        r7[r8] = MVEL.eval(r32.get(r8), r11, r13);     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        r8 = r8 + 1;     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        goto L20
    L22:
        Constructor r112 = ParseTools.getBestConstructorCandidate(r7, r5, false);     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        if (r112 == null) goto L33;
    L24:
        if (r2 >= r62) goto L27;
        r7[r2] = DataConversion.convert(r7[r2], r112.getParameterTypes()[r2]);     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        r2 = r2 + 1;     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        goto L24
    L27:
        if (r15.length <= 1) goto L31;
        return PropertyAccessor.get(r15[1], r112.newInstance(r7), r13, r12, this.pCtx);
    L31:
        return r112.newInstance(r7);
    L33:
        throw new CompileException("unable to find constructor for: " + r5.getName(), this.expr, this.start);     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
    L34:
        Constructor<?> r113 = Class.forName(this.typeDescr.getClassName(), true, this.pCtx.getParserConfiguration().getClassLoader()).getConstructor(EMPTYCLS);     // Catch: Exception -> L8 NoSuchMethodException -> L10 ClassNotFoundException -> L12 CompileException -> L46
        if (r15.length <= 1) goto L39;
        return PropertyAccessor.get(r15[1], r113.newInstance(null), r13, r12, this.pCtx);
    L39:
        return r113.newInstance(null);
    L12:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("unable to resolve class: " + e.getMessage(), this.expr, this.start, e);
        return null;
    L10:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("cannot resolve constructor: " + e.getMessage(), this.expr, this.start, e);
        return null;
    L46:
        e = move-exception;
        throw e;
    L8:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("could not instantiate class: " + e.getMessage(), this.expr, this.start, e);
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r9, Object r10, VariableResolverFactory r11) {
        if (this.newObjectOptimizer == null) goto L5;
        Object r5 = r9;
        Object r6 = r10;
        VariableResolverFactory r7 = r11;
    L38:
        return this.newObjectOptimizer.getValue(r5, r6, r7);
    L5:
        if (this.egressType != null) goto L17;
        if (r11 == null) goto L17;
        if (r11.isResolveable(this.typeDescr.getClassName()) == false) goto L17;
        this.egressType = (Class) r11.getVariableResolver(this.typeDescr.getClassName()).getValue();     // Catch: ClassCastException -> L13
        rewriteClassReferenceToFQCN(16);     // Catch: ClassCastException -> L13
        if (this.typeDescr.isArray() == false) goto L17;
        this.egressType = ParseTools.findClass(r11, ParseTools.repeatChar('[', this.typeDescr.getArrayLength()) + "L" + this.egressType.getName() + ";", this.pCtx);     // Catch: Exception -> L39
    L13:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("cannot construct object: " + this.typeDescr.getClassName() + " is not a class reference", this.expr, this.start, e);
        return null;
    L17:
        if (this.typeDescr.isArray() == false) goto L44;
        NewObjectArray r0 = new NewObjectArray(ParseTools.getBaseComponentType(this.egressType.getComponentType()), this.typeDescr.getCompiledArraySize());
        this.newObjectOptimizer = r0;
        return r0.getValue(r9, r10, r11);
    L44:
        AccessorOptimizer r02 = OptimizerFactory.getThreadAccessorOptimizer();     // Catch: CompileException -> L23 Throwable -> L33
        ParserContext r1 = this.pCtx;     // Catch: CompileException -> L23 Throwable -> L33
        if (r1 != null) goto L25;
        r1 = new ParserContext();     // Catch: CompileException -> L23 Throwable -> L33
        r1.getParserConfiguration().setAllImports(CompilerTools.getInjectedImports(r11));     // Catch: CompileException -> L23 Throwable -> L33
    L25:
        char[] r2 = this.name;     // Catch: CompileException -> L23 Throwable -> L33
        r5 = r9;
        r6 = r10;
        r7 = r11;
        this.newObjectOptimizer = r02.optimizeObjectCreation(r1, r2, 0, r2.length, r5, r6, r7);     // Catch: CompileException -> L23 Throwable -> L33
        if (r02.getResultOptPass() == null) goto L30;
        this.egressType = r02.getEgressType();     // Catch: CompileException -> L23 Throwable -> L33
        Object r8 = r02.getResultOptPass();     // Catch: CompileException -> L23 Throwable -> L33
        OptimizerFactory.clearThreadAccessorOptimizer();
        return r8;
    L30:
        OptimizerFactory.clearThreadAccessorOptimizer();
    L23:
        e = move-exception;
        throw ErrorUtil.rewriteIfNeeded(e, this.expr, this.start);     // Catch: Throwable -> L33
    L33:
        th = move-exception;
        OptimizerFactory.clearThreadAccessorOptimizer();
        throw th;
    }

    public TypeDescriptor getTypeDescr() {
        return this.typeDescr;
    }
}
