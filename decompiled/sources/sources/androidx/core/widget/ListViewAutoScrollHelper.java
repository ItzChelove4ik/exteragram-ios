package androidx.core.widget;

import android.widget.ListView;

/* loaded from: classes3.dex */
public class ListViewAutoScrollHelper extends AutoScrollHelper {
    private final ListView mTarget;

    public ListViewAutoScrollHelper(ListView r1) {
        super(r1);
        this.mTarget = r1;
    }

    @Override // androidx.core.widget.AutoScrollHelper
    public boolean canTargetScrollHorizontally(int r1) {
        return false;
    }

    @Override // androidx.core.widget.AutoScrollHelper
    public boolean canTargetScrollVertically(int r7) {
        ListView r6 = this.mTarget;
        int r0 = r6.getCount();
        if (r0 != 0) goto L5;
        return false;
    L5:
        int r2 = r6.getChildCount();
        int r3 = r6.getFirstVisiblePosition();
        int r4 = r3 + r2;
        if (r7 <= 0) goto L11;
        if (r4 >= r0) goto L9;
    L16:
        return true;
    L9:
        if (r6.getChildAt(r2 - 1).getBottom() > r6.getHeight()) goto L16;
        return false;
    L11:
        if (r7 >= 0) goto L17;
        if (r3 > 0) goto L16;
        if (r6.getChildAt(0).getTop() < 0) goto L16;
        return false;
    L17:
        return false;
    }

    @Override // androidx.core.widget.AutoScrollHelper
    public void scrollTargetBy(int r1, int r2) {
        this.mTarget.scrollListBy(r2);
    }
}
