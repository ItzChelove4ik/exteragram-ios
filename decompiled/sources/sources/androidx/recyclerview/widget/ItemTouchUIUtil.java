package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.view.View;

/* loaded from: classes4.dex */
public interface ItemTouchUIUtil {
    void clearView(View r1);

    void onDraw(Canvas r1, RecyclerView r2, View r3, float r4, float r5, int r6, boolean r7);

    void onDrawOver(Canvas r1, RecyclerView r2, View r3, float r4, float r5, int r6, boolean r7);

    void onSelected(View r1);
}
