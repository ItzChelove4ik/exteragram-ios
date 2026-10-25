package org.mvel2.util;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: classes5.dex */
public class LineMapper {
    private char[] expr;
    private ArrayList<Node> lineMapping;
    private Set<Integer> lines;

    /* renamed from: org.mvel2.util.LineMapper$1, reason: invalid class name */
    public class AnonymousClass1 implements LineLookup {
        final /* synthetic */ LineMapper this$0;

        public AnonymousClass1(LineMapper r1) {
            this.this$0 = r1;
        }

        @Override // org.mvel2.util.LineMapper.LineLookup
        public int getLineFromCursor(int r5) {
            ArrayList r4 = LineMapper.access$100(this.this$0);
            int r0 = r4.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            Object r2 = r4.get(r1);
            r1 = r1 + 1;
            Node r22 = (Node) r2;
            if (r22.isInRange(r5) == false) goto L3;
            return r22.getLine();
        L8:
            return -1;
        }

        @Override // org.mvel2.util.LineMapper.LineLookup
        public boolean hasLine(int r1) {
            return LineMapper.access$200(this.this$0).contains(Integer.valueOf(r1));
        }
    }

    public interface LineLookup {
        int getLineFromCursor(int r1);

        boolean hasLine(int r1);
    }

    public static class Node implements Comparable<Node> {
        private int cursorEnd;
        private int cursorStart;
        private int line;

        private Node(int r1, int r2, int r3) {
            this.cursorStart = r1;
            this.cursorEnd = r2;
            this.line = r3;
        }

        public /* synthetic */ Node(int r1, int r2, int r3, AnonymousClass1 r4) {
            this(r1, r2, r3);
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Node r1) {
            return compareTo2(r1);
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(Node r3) {
            if (r3.cursorStart < this.cursorEnd) goto L7;
            return 1;
        L7:
            if (r3.cursorEnd >= this.cursorStart) goto L10;
            return -1;
        L10:
            return 0;
        }

        public int getLine() {
            return this.line;
        }

        public boolean isInRange(int r2) {
            if (r2 >= this.cursorStart) goto L5;
            return false;
        L5:
            if (r2 > this.cursorEnd) goto L10;
            return true;
        L10:
            return false;
        }
    }

    public LineMapper(char[] r1) {
        this.expr = r1;
    }

    public static /* synthetic */ ArrayList access$100(LineMapper r0) {
        return r0.lineMapping;
    }

    public static /* synthetic */ Set access$200(LineMapper r0) {
        return r0.lines;
    }

    public LineLookup map() {
        this.lineMapping = new ArrayList();
        this.lines = new TreeSet();
        int r0 = 0;
        int r2 = 1;
        int r1 = 0;
    L3:
        char[] r3 = this.expr;
        if (r0 >= r3.length) goto L10;
        if (r3[r0] != '\n') goto L9;
        this.lines.add(Integer.valueOf(r2));
        this.lineMapping.add(new Node(r1, r0, r2, null));
        r1 = r0 + 1;
        r2 = r2 + 1;
    L9:
        r0 = r0 + 1;
        goto L3
    L10:
        if (r0 <= r1) goto L13;
        this.lines.add(Integer.valueOf(r2));
        this.lineMapping.add(new Node(r1, r0, r2, null));
    L13:
        return new AnonymousClass1(this);
    }
}
