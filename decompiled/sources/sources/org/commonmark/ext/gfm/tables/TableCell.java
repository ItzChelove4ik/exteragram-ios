package org.commonmark.ext.gfm.tables;

import org.commonmark.node.CustomNode;

/* loaded from: classes5.dex */
public class TableCell extends CustomNode {
    private Alignment alignment;
    private boolean header;

    public enum Alignment extends Enum<Alignment> {
        private static final /* synthetic */ Alignment[] $VALUES = null;
        public static final Alignment CENTER = null;
        public static final Alignment LEFT = null;
        public static final Alignment RIGHT = null;

        static {
            Alignment r0 = new Alignment("LEFT", 0);
            LEFT = r0;
            Alignment r1 = new Alignment("CENTER", 1);
            CENTER = r1;
            Alignment r2 = new Alignment("RIGHT", 2);
            RIGHT = r2;
            $VALUES = new Alignment[]{r0, r1, r2};
        }

        Alignment(String r1, int r2) {
        }

        public static Alignment valueOf(String r1) {
            return (Alignment) Enum.valueOf(Alignment.class, r1);
        }

        public static Alignment[] values() {
            return (Alignment[]) $VALUES.clone();
        }
    }

    public TableCell() {
    }

    public Alignment getAlignment() {
        return this.alignment;
    }

    public boolean isHeader() {
        return this.header;
    }

    public void setAlignment(Alignment r1) {
        this.alignment = r1;
    }

    public void setHeader(boolean r1) {
        this.header = r1;
    }
}
