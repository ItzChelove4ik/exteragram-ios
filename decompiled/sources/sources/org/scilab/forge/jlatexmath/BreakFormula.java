package org.scilab.forge.jlatexmath;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/* loaded from: classes5.dex */
public final class BreakFormula {

    public static class Position {
        HorizontalBox hbox;
        int index;

        public Position(int r1, HorizontalBox r2) {
            this.index = r1;
            this.hbox = r2;
        }
    }

    public BreakFormula() {
    }

    private static float canBreak(Stack<Position> r10, HorizontalBox r11, float r12) {
        LinkedList<Box> r0 = r11.children;
        float[] r1 = new float[r0.size() + 1];
        int r3 = 0;
        r1[0] = 0.0f;
    L4:
        if (r3 >= r0.size()) goto L21;
        Box r2 = r0.get(r3);
        int r4 = r3 + 1;
        float r5 = r1[r3] + r2.width;
        r1[r4] = r5;
        if (r5 <= r12) goto L19;
        int r52 = getBreakPosition(r11, r3);
        if ((r2 instanceof HorizontalBox) == false) goto L16;
        Stack r6 = new Stack();
        float r8 = canBreak(r6, (HorizontalBox) r2, r12 - r1[r3]);
        if (r8 == r2.width) goto L16;
        if ((r1[r3] + r8) <= r12) goto L14;
        if (r52 != (-1)) goto L16;
    L14:
        r10.push(new Position(r3 - 1, r11));
        r10.addAll(r6);
        return r1[r3] + r8;
    L16:
        if (r52 == (-1)) goto L19;
        r10.push(new Position(r52, r11));
        return r1[r52];
    L19:
        r3 = r4;
        goto L4
    L21:
        return r11.width;
    }

    private static int getBreakPosition(HorizontalBox r5, int r6) {
        List<Integer> r0 = r5.breakPositions;
        if (r0 != null) goto L5;
        return -1;
    L5:
        int r2 = 0;
        if (r0.size() != 1) goto L11;
        if (r5.breakPositions.get(0).intValue() > r6) goto L11;
        return r5.breakPositions.get(0).intValue();
    L11:
        int r02 = r5.breakPositions.size();
        List<Integer> r4 = r5.breakPositions;
        if (r2 >= r02) goto L21;
        if (r4.get(r2).intValue() > r6) goto L15;
        r2 = r2 + 1;
        goto L11
    L15:
        if (r2 != 0) goto L18;
        return -1;
    L18:
        return r5.breakPositions.get(r2 - 1).intValue();
    L21:
        return r4.get(r2 - 1).intValue();
    }

    public static Box split(Box r1, float r2, float r3) {
        if ((r1 instanceof HorizontalBox) == false) goto L7;
        return split((HorizontalBox) r1, r2, r3);
    L7:
        if ((r1 instanceof VerticalBox) == true) goto L9;
        return r1;
    L9:
        return split((VerticalBox) r1, r2, r3);
    }

    public static Box split(HorizontalBox r7, float r8, float r9) {
        VerticalBox r0 = new VerticalBox();
        Stack r1 = new Stack();
        Box r2 = null;
        HorizontalBox r72 = r7;
    L4:
        if (r72.width <= r8) goto L12;
        if (canBreak(r1, r72, r8) == r72.width) goto L12;
        Position r73 = (Position) r1.pop();
        HorizontalBox[] r74 = r73.hbox.split(r73.index - 1);
        HorizontalBox r4 = r74[0];
        HorizontalBox r42 = r4;
        HorizontalBox r75 = r74[1];
    L9:
        if (r1.isEmpty() == true) goto L11;
        Position r5 = (Position) r1.pop();
        HorizontalBox[] r52 = r5.hbox.splitRemove(r5.index);
        r52[0].add(r42);
        r52[1].add(0, r75);
        HorizontalBox r43 = r52[0];
        r42 = r43;
        r75 = r52[1];
        goto L9
    L11:
        r0.add(r42, r9);
        r2 = r75;
        r72 = r75;
    L12:
        if (r2 == null) goto L15;
        r0.add(r2, r9);
        return r0;
    L15:
        return r72;
    }

    private static Box split(VerticalBox r2, float r3, float r4) {
        VerticalBox r0 = new VerticalBox();
        Iterator<Box> r22 = r2.children.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r0.add(split(r22.next(), r3, r4));
        goto L4
    L6:
        return r0;
    }
}
