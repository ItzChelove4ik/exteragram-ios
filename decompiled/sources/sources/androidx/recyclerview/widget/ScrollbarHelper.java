package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
abstract class ScrollbarHelper {
    public static int computeScrollExtent(RecyclerView.State r1, OrientationHelper r2, View r3, View r4, RecyclerView.LayoutManager r5, boolean r6) {
        if (r5.getChildCount() != 0) goto L5;
        return 0;
    L5:
        if (r1.getItemCount() == 0) goto L16;
        if (r3 == null) goto L17;
        if (r4 == null) goto L18;
        if (r6 == false) goto L11;
        int r12 = r2.getDecoratedEnd(r4) - r2.getDecoratedStart(r3);
        return Math.min(r2.getTotalSpace(), r12);
    L11:
        return Math.abs(r5.getPosition(r3) - r5.getPosition(r4)) + 1;
    L18:
        return 0;
    L17:
        return 0;
    L16:
        return 0;
    }

    public static int computeScrollOffset(RecyclerView.State r4, OrientationHelper r5, View r6, View r7, RecyclerView.LayoutManager r8, boolean r9, boolean r10) {
        if (r8.getChildCount() != 0) goto L5;
    L17:
        return 0;
    L5:
        if (r4.getItemCount() == 0) goto L17;
        if (r6 == null) goto L17;
        if (r7 == null) goto L17;
        int r0 = Math.min(r8.getPosition(r6), r8.getPosition(r7));
        int r2 = Math.max(r8.getPosition(r6), r8.getPosition(r7));
        if (r10 == false) goto L12;
        int r42 = Math.max(0, (r4.getItemCount() - r2) - 1);
    L13:
        if (r9 == true) goto L16;
        return r42;
    L16:
        return Math.round((r42 * (Math.abs(r5.getDecoratedEnd(r7) - r5.getDecoratedStart(r6)) / (Math.abs(r8.getPosition(r6) - r8.getPosition(r7)) + 1))) + (r5.getStartAfterPadding() - r5.getDecoratedStart(r6)));
    L12:
        r42 = Math.max(0, r0);
        goto L13
    }

    public static int computeScrollRange(RecyclerView.State r1, OrientationHelper r2, View r3, View r4, RecyclerView.LayoutManager r5, boolean r6) {
        if (r5.getChildCount() != 0) goto L5;
        return 0;
    L5:
        if (r1.getItemCount() == 0) goto L16;
        if (r3 == null) goto L17;
        if (r4 == null) goto L18;
        if (r6 == true) goto L13;
        return r1.getItemCount();
    L13:
        return (int) (((r2.getDecoratedEnd(r4) - r2.getDecoratedStart(r3)) / (Math.abs(r5.getPosition(r3) - r5.getPosition(r4)) + 1)) * r1.getItemCount());
    L18:
        return 0;
    L17:
        return 0;
    L16:
        return 0;
    }
}
