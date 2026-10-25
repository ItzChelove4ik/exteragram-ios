package org.telegram.ui.Components.Premium;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public abstract class BaseListPageView extends FrameLayout implements PagerHeaderView {
    RecyclerView.Adapter adapter;
    final LinearLayoutManager layoutManager;
    final RecyclerListView recyclerListView;
    final Theme.ResourcesProvider resourcesProvider;

    public BaseListPageView(Context r4, Theme.ResourcesProvider r5) {
        super(r4);
        this.resourcesProvider = r5;
        RecyclerListView r0 = new RecyclerListView(r4, r5);
        this.recyclerListView = r0;
        r0.setNestedScrollingEnabled(true);
        RecyclerView.Adapter r1 = createAdapter();
        this.adapter = r1;
        r0.setAdapter(r1);
        LinearLayoutManager r12 = new LinearLayoutManager(r4, 1, false);
        this.layoutManager = r12;
        r0.setLayoutManager(r12);
        r0.setClipToPadding(false);
        addView(r0, LayoutHelper.createFrame(-1, -1.0f));
    }

    public abstract RecyclerView.Adapter createAdapter();

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        super.dispatchDraw(r8);
        Paint r0 = Theme.getThemePaint("paintDivider", this.resourcesProvider);
        if (r0 != null) goto L5;
        r0 = Theme.dividerPaint;
    L5:
        r8.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, r0);
    }

    @Override // org.telegram.ui.Components.Premium.PagerHeaderView
    public void setOffset(float r3) {
        if (Math.abs(r3 / getMeasuredWidth()) == 1.0f) goto L5;
        return;
    L5:
        if (this.recyclerListView.findViewHolderForAdapterPosition(0) != null) goto L7;
    L8:
        this.recyclerListView.scrollToPosition(0);
        return;
    L7:
        if (this.recyclerListView.findViewHolderForAdapterPosition(0).itemView.getTop() != this.recyclerListView.getPaddingTop()) goto L8;
    }

    public void setTopOffset(int r2) {
        this.recyclerListView.setPadding(0, r2, 0, 0);
    }
}
