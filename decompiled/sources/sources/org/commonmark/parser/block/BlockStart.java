package org.commonmark.parser.block;

import org.commonmark.internal.BlockStartImpl;

/* loaded from: classes5.dex */
public abstract class BlockStart {
    public BlockStart() {
    }

    public static BlockStart none() {
        return null;
    }

    public static BlockStart of(BlockParser... r1) {
        return new BlockStartImpl(r1);
    }

    public abstract BlockStart atColumn(int r1);

    public abstract BlockStart atIndex(int r1);

    public abstract BlockStart replaceActiveBlockParser();
}
