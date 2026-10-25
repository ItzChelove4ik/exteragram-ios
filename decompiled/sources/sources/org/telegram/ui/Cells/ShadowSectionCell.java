package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public class ShadowSectionCell extends View {
    private int backgroundColor;
    private boolean bottom;
    private Theme.ResourcesProvider resourcesProvider;
    private int size;
    private boolean top;

    public ShadowSectionCell(Context r3) {
        this(r3, 12, null);
    }

    public ShadowSectionCell(Context r2, int r3) {
        this(r2, r3, null);
    }

    public ShadowSectionCell(Context r2, int r3, int r4) {
        this(r2, r3, r4, null);
    }

    public ShadowSectionCell(Context r1, int r2, int r3, Theme.ResourcesProvider r4) {
        super(r1);
        this.top = true;
        this.bottom = true;
        this.resourcesProvider = r4;
        this.backgroundColor = r3;
        this.size = r2;
        updateBackground();
    }

    public ShadowSectionCell(Context r1, int r2, Theme.ResourcesProvider r3) {
        super(r1);
        this.top = true;
        this.bottom = true;
        this.resourcesProvider = r3;
        this.size = r2;
        updateBackground();
    }

    public ShadowSectionCell(Context r2, Theme.ResourcesProvider r3) {
        this(r2, 12, r3);
    }

    private int getBackgroundResId() {
        boolean r0 = this.top;
        if (r0 == true) goto L5;
    L8:
        if (r0 == false) goto L12;
        return R.drawable.greydivider_bottom;
    L12:
        if (this.bottom == false) goto L16;
        return R.drawable.greydivider_top;
    L16:
        return R.drawable.transparent;
    L5:
        if (this.bottom == false) goto L8;
        return R.drawable.greydivider;
    }

    private void updateBackground() {
        int r0 = this.backgroundColor;
        if (r0 != 0) goto L6;
        setBackground(null);
        return;
    L6:
        setBackgroundColor(r0);
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.size), TLObject.FLAG_30));
    }

    public void setSize(int r1) {
        this.size = r1;
    }

    public void setTopBottom(boolean r2, boolean r3) {
        if (this.top == r2) goto L5;
    L8:
        this.top = r2;
        this.bottom = r3;
        updateBackground();
        return;
    L5:
        if (this.bottom != r3) goto L8;
    }
}
