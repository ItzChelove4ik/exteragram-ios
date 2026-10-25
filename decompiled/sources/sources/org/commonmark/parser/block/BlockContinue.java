package org.commonmark.parser.block;

import org.commonmark.internal.BlockContinueImpl;

/* loaded from: classes5.dex */
public abstract class BlockContinue {
    public BlockContinue() {
    }

    public static BlockContinue atColumn(int r3) {
        return new BlockContinueImpl(-1, r3, false);
    }

    public static BlockContinue atIndex(int r3) {
        return new BlockContinueImpl(r3, -1, false);
    }

    public static BlockContinue finished() {
        return new BlockContinueImpl(-1, -1, true);
    }

    public static BlockContinue none() {
        return null;
    }
}
