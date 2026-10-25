package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.viewpager.widget.ViewPager;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class BottomPagesView extends View {
    private int colorKey;
    private int currentPage;
    private DecelerateInterpolator decelerateInterpolator;
    private int pagesCount;
    private Paint paint;
    private float progress;
    private RectF rect;
    private int scrollPosition;
    private int selectedColorKey;
    private ViewPager viewPager;

    public BottomPagesView(Context r2, ViewPager r3, int r4) {
        super(r2);
        this.paint = new Paint(1);
        this.decelerateInterpolator = new DecelerateInterpolator();
        this.rect = new RectF();
        this.colorKey = -1;
        this.selectedColorKey = -1;
        this.viewPager = r3;
        this.pagesCount = r4;
    }

    @Override // android.view.View
    public void onDraw(Canvas r10) {
        AndroidUtilities.dp(5.0f);
        int r1 = this.colorKey;
        Paint r2 = this.paint;
        if (r1 < 0) goto L6;
        r2.setColor((Theme.getColor(r1) & 16777215) | (-1275068416));
    L10:
        this.currentPage = this.viewPager.getCurrentItem();
        int r12 = 0;
    L12:
        if (r12 >= this.pagesCount) goto L18;
        if (r12 == this.currentPage) goto L17;
        this.rect.set(AndroidUtilities.dp(11.0f) * r12, 0.0f, r2 + AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f));
        r10.drawRoundRect(this.rect, AndroidUtilities.dp(2.5f), AndroidUtilities.dp(2.5f), this.paint);
    L17:
        r12 = r12 + 1;
        goto L12
    L18:
        int r13 = this.selectedColorKey;
        Paint r22 = this.paint;
        if (r13 < 0) goto L21;
        r22.setColor(Theme.getColor(r13));
    L22:
        int r14 = this.currentPage * AndroidUtilities.dp(11.0f);
        if (this.progress == 0.0f) goto L28;
        int r23 = this.scrollPosition;
        int r6 = this.currentPage;
        RectF r7 = this.rect;
        if (r23 < r6) goto L27;
        r7.set(r14, 0.0f, (r14 + AndroidUtilities.dp(5.0f)) + (AndroidUtilities.dp(11.0f) * this.progress), AndroidUtilities.dp(5.0f));
    L29:
        r10.drawRoundRect(this.rect, AndroidUtilities.dp(2.5f), AndroidUtilities.dp(2.5f), this.paint);
        return;
    L27:
        r7.set(r14 - (AndroidUtilities.dp(11.0f) * (1.0f - this.progress)), 0.0f, r14 + AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f));
        goto L29
    L28:
        this.rect.set(r14, 0.0f, r14 + AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f));
        goto L29
    L21:
        r22.setColor(-14509328);
        goto L22
    L6:
        if (Theme.getCurrentTheme().isDark() == false) goto L8;
        int r15 = -11184811;
    L9:
        r2.setColor(r15);
        goto L10
    L8:
        r15 = -4473925;
        goto L9
    }

    public void setColor(int r1, int r2) {
        this.colorKey = r1;
        this.selectedColorKey = r2;
    }

    public void setCurrentPage(int r1) {
        this.currentPage = r1;
        invalidate();
    }

    public void setPageOffset(int r1, float r2) {
        this.progress = r2;
        this.scrollPosition = r1;
        invalidate();
    }
}
