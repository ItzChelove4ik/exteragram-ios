package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;

/* loaded from: classes5.dex */
public class BlockNode extends ASTNode {
    protected int blockOffset;
    protected int blockStart;
    protected ExecutableStatement compiledBlock;

    public BlockNode(ParserContext r1) {
        super(r1);
    }

    public int getBlockOffset() {
        return this.blockOffset;
    }

    public int getBlockStart() {
        return this.blockStart;
    }

    public ExecutableStatement getCompiledBlock() {
        return this.compiledBlock;
    }
}
