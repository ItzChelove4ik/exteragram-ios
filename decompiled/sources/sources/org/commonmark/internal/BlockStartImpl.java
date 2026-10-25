package org.commonmark.internal;

import org.commonmark.parser.block.BlockParser;
import org.commonmark.parser.block.BlockStart;

/* loaded from: classes5.dex */
public class BlockStartImpl extends BlockStart {
    private final BlockParser[] blockParsers;
    private int newColumn;
    private int newIndex;
    private boolean replaceActiveBlockParser;

    public BlockStartImpl(BlockParser... r2) {
        this.newIndex = -1;
        this.newColumn = -1;
        this.replaceActiveBlockParser = false;
        this.blockParsers = r2;
    }

    @Override // org.commonmark.parser.block.BlockStart
    public BlockStart atColumn(int r1) {
        this.newColumn = r1;
        return this;
    }

    @Override // org.commonmark.parser.block.BlockStart
    public BlockStart atIndex(int r1) {
        this.newIndex = r1;
        return this;
    }

    public BlockParser[] getBlockParsers() {
        return this.blockParsers;
    }

    public int getNewColumn() {
        return this.newColumn;
    }

    public int getNewIndex() {
        return this.newIndex;
    }

    public boolean isReplaceActiveBlockParser() {
        return this.replaceActiveBlockParser;
    }

    @Override // org.commonmark.parser.block.BlockStart
    public BlockStart replaceActiveBlockParser() {
        this.replaceActiveBlockParser = true;
        return this;
    }
}
