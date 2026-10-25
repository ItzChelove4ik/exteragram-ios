package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.tgnet.TLObject;

/* loaded from: classes6.dex */
public class EmptyCell extends FrameLayout {
    private int cellHeight;

    public EmptyCell(Context r2) {
        this(r2, 8);
    }

    public EmptyCell(Context r1, int r2) {
        super(r1);
        this.cellHeight = r2;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.cellHeight, TLObject.FLAG_30));
    }

    public void setHeight(int r2) {
        if (this.cellHeight == r2) goto L6;
        this.cellHeight = r2;
        requestLayout();
        return;
    }
}
