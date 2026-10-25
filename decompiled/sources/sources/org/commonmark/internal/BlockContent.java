package org.commonmark.internal;

/* loaded from: classes5.dex */
class BlockContent {
    private int lineCount;
    private final StringBuilder sb;

    public BlockContent() {
        this.lineCount = 0;
        this.sb = new StringBuilder();
    }

    public void add(CharSequence r3) {
        if (this.lineCount == 0) goto L5;
        this.sb.append('\n');
    L5:
        this.sb.append(r3);
        this.lineCount++;
    }

    public String getString() {
        return this.sb.toString();
    }
}
