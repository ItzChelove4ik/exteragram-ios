package org.telegram.ui.Components.ListView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.View;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public abstract class RecyclerListViewWithOverlayDraw extends RecyclerListView {
    boolean invalidated;

    public interface OverlayView {
        float getX();

        float getY();

        void preDraw(View r1, Canvas r2);
    }

    public RecyclerListViewWithOverlayDraw(Context r1) {
        super(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.telegram.ui.Components.RecyclerListView
    public void captureChildren(Canvas r6, RectF r7, long r8) {
        super.captureChildren(r6, r7, r8);
        int r82 = getChildCount();
        int r9 = 0;
    L3:
        if (r9 >= r82) goto L12;
        View r0 = getChildAt(r9);
        if ((r0 instanceof OverlayView) == false) goto L11;
        float r1 = r0.getX();
        float r2 = r0.getY();
        if (r7.intersects(r1, r2, r0.getWidth() + r1, r0.getHeight() + r2) == false) goto L11;
        r6.save();
        r6.translate(r1, r2);
        ((OverlayView) r0).preDraw(this, r6);
        r6.restore();
    L11:
        r9 = r9 + 1;
        goto L3
    }

    @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        int r0 = 0;
        this.invalidated = false;
    L4:
        if (r0 >= getChildCount()) goto L9;
        if ((getChildAt(r0) instanceof OverlayView) == false) goto L8;
        OverlayView r1 = (OverlayView) getChildAt(r0);
        r5.save();
        r5.translate(r1.getX(), r1.getY());
        r1.preDraw(this, r5);
        r5.restore();
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        super.dispatchDraw(r5);
    }

    @Override // android.view.View
    public void invalidate() {
        if (this.invalidated == false) goto L5;
        return;
    L5:
        super.invalidate();
        this.invalidated = true;
    }
}
