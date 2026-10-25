package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.ClassImportResolverFactory;
import org.mvel2.integration.impl.ImmutableDefaultFactory;
import org.mvel2.integration.impl.StackResetResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class ImportNode extends ASTNode {
    private static final char[] WC_TEST = null;
    private int _offset;
    private Class importClass;
    private boolean packageImport;

    static {
        WC_TEST = new char[]{'.', '*'};
    }

    public ImportNode(char[] r5, int r6, int r7, ParserContext r8) {
        super(r8);
        this.expr = r5;
        this.start = r6;
        this.offset = r7;
        this.pCtx = r8;
        if (ParseTools.endsWith(r5, r6, r7, WC_TEST) == false) goto L8;
        this.packageImport = true;
        short r52 = (short) ParseTools.findLast(r5, r6, r7, '.');
        this._offset = r52;
        if (r52 != (-1)) goto L20;
        this._offset = 0;
        return;
    L20:
        return;
    L8:
        String r82 = new String(r5, r6, r7);
        ClassLoader r72 = getClassLoader();
        this.importClass = Class.forName(r82, true, r72);     // Catch: ClassNotFoundException -> L11
        return;
    L11:
        StringBuilder r3 = new StringBuilder();
        int r1 = r82.lastIndexOf(46);
        r3.append(r82.substring(0, r1));
        r3.append("$");
        r3.append(r82.substring(r1 + 1));
        this.importClass = Class.forName(r3.toString().trim(), true, r72);     // Catch: ClassNotFoundException -> L14
        return;
    L14:
        Sign$$ExternalSyntheticBUOutline0.m("class not found: ".concat(new String(r5)), r5, r6);
        throw null;
    }

    public Class getImportClass() {
        return this.importClass;
    }

    public String getPackageImport() {
        char[] r1 = this.expr;
        int r2 = this.start;
        return new String(r1, r2, this._offset - r2);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        return getReducedValueAccelerated(r1, r2, r3);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        if (this.packageImport == true) goto L11;
        if (MVEL.COMPILER_OPT_ALLOCATE_TYPE_LITERALS_TO_SHARED_SYMBOL_TABLE == false) goto L9;
        r4.createVariable(this.importClass.getSimpleName(), this.importClass);
        return this.importClass;
    L9:
        return ParseTools.findClassImportResolverFactory(r4, this.pCtx).addClass(this.importClass);
    L11:
        if ((r4 instanceof ImmutableDefaultFactory) == false) goto L13;
        return null;
    L13:
        if ((r4 instanceof StackResetResolverFactory) == true) goto L15;
    L16:
        ClassImportResolverFactory r22 = ParseTools.findClassImportResolverFactory(r4, this.pCtx);
        char[] r42 = this.expr;
        int r0 = this.start;
        r22.addPackageImport(new String(r42, r0, this._offset - r0));
        return null;
    L15:
        if ((((StackResetResolverFactory) r4).getDelegate() instanceof ImmutableDefaultFactory) == false) goto L16;
        return null;
    }

    public boolean isPackageImport() {
        return this.packageImport;
    }

    public void setPackageImport(boolean r1) {
        this.packageImport = r1;
    }
}
