package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes4.dex */
class ItemTouchUIUtilImpl implements ItemTouchUIUtil {
    static final ItemTouchUIUtil INSTANCE = null;

    static {
        INSTANCE = new ItemTouchUIUtilImpl();
    }

    public ItemTouchUIUtilImpl() {
    }

    private static float findMaxElevation(RecyclerView r5, View r6) {
        int r0 = r5.getChildCount();
        float r1 = 0.0f;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        View r3 = r5.getChildAt(r2);
        if (r3 == r6) goto L10;
        float r32 = ViewCompat.getElevation(r3);
        if (r32 <= r1) goto L10;
        r1 = r32;
    L10:
        r2 = r2 + 1;
        goto L3
    L11:
        return r1;
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void clearView(View r2) {
        Object r1 = r2.getTag();
        if ((r1 instanceof Float) == false) goto L5;
        ViewCompat.setElevation(r2, ((Float) r1).floatValue());
    L5:
        r2.setTag(null);
        r2.setTranslationX(0.0f);
        r2.setTranslationY(0.0f);
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void onDraw(Canvas r1, RecyclerView r2, View r3, float r4, float r5, int r6, boolean r7) {
        if (r7 == true) goto L4;
    L6:
        r3.setTranslationX(r4);
        r3.setTranslationY(r5);
        return;
    L4:
        if (r3.getTag() != null) goto L6;
        Float r0 = Float.valueOf(ViewCompat.getElevation(r3));
        ViewCompat.setElevation(r3, findMaxElevation(r2, r3) + 1.0f);
        r3.setTag(r0);
        goto L6
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void onDrawOver(Canvas r1, RecyclerView r2, View r3, float r4, float r5, int r6, boolean r7) {
    }

    @Override // androidx.recyclerview.widget.ItemTouchUIUtil
    public void onSelected(View r1) {
    }
}
