package org.commonmark.internal;

import org.commonmark.parser.block.BlockContinue;

/* loaded from: classes5.dex */
public class BlockContinueImpl extends BlockContinue {
    private final boolean finalize;
    private final int newColumn;
    private final int newIndex;

    public BlockContinueImpl(int r1, int r2, boolean r3) {
        this.newIndex = r1;
        this.newColumn = r2;
        this.finalize = r3;
    }

    public int getNewColumn() {
        return this.newColumn;
    }

    public int getNewIndex() {
        return this.newIndex;
    }

    public boolean isFinalize() {
        return this.finalize;
    }
}
