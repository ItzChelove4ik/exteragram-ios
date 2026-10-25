package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.FiltersSetupActivity;

/* loaded from: classes7.dex */
public class SectionsScrollView extends ScrollView {
    private ArrayList<View> children;
    private final Path clipPath;
    private LinearLayout contentView;
    private ArrayList<Runnable> onScroll;
    private Theme.ResourcesProvider resourcesProvider;
    private float sectionRadius;
    private float[] sectionRadiusBottom;
    private float[] sectionRadiusTop;

    public static class SectionsLinearLayout extends LinearLayout {
        public SectionsLinearLayout(Context r1) {
            super(r1);
            setWillNotDraw(false);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if ((getParent() instanceof SectionsScrollView) == false) goto L7;
            SectionsScrollView r0 = (SectionsScrollView) getParent();
            r2.save();
            SectionsScrollView.m12608$$Nest$mclipChild(r0, r2, r3);
            boolean r1 = super.drawChild(r2, r3, r4);
            r2.restore();
            return r1;
        L7:
            return super.drawChild(r2, r3, r4);
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if ((getParent() instanceof SectionsScrollView) == false) goto L6;
            ((SectionsScrollView) getParent()).invalidate();
            return;
        }
    }

    /* renamed from: -$$Nest$mclipChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12608$$Nest$mclipChild(SectionsScrollView r0, Canvas r1, View r2) {
        r0.clipChild(r1, r2);
    }

    public SectionsScrollView(Context r2, LinearLayout r3, Theme.ResourcesProvider r4) {
        this(r2, r3, r4, true);
    }

    public SectionsScrollView(Context r9, LinearLayout r10, Theme.ResourcesProvider r11, boolean r12) {
        super(r9);
        this.sectionRadius = AndroidUtilities.dp(ExteraConfig.getSectionRadiusDp());
        this.onScroll = new ArrayList();
        this.children = new ArrayList();
        this.clipPath = new Path();
        this.resourcesProvider = r11;
        this.contentView = r10;
        setWillNotDraw(false);
        LinearLayout r102 = this.contentView;
        int r0 = AndroidUtilities.dp(12.0f);
        if (r12 == false) goto L5;
        float r122 = 12.0f;
    L6:
        r102.setPadding(r0, AndroidUtilities.dp(r122), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
        float r103 = this.sectionRadius;
        this.sectionRadiusTop = new float[]{r103, r103, r103, r103, 0.0f, 0.0f, 0.0f, 0.0f};
        this.sectionRadiusBottom = new float[]{0.0f, 0.0f, 0.0f, 0.0f, r103, r103, r103, r103};
        return;
    L5:
        r122 = 4.0f;
        goto L6
    }

    private void clipChild(Canvas r12, View r13) {
        if (r13 != null) goto L4;
        return;
    L4:
        if (isSectionView(r13) == false) goto L56;
        int r0 = this.contentView.indexOfChild(r13);
        int r1 = r0 - 1;
        View r2 = null;
        if (r1 >= 0) goto L9;
        View r14 = null;
    L10:
        boolean r3 = true;
        int r02 = r0 + 1;
        if (r02 >= this.contentView.getChildCount()) goto L15;
        r2 = this.contentView.getChildAt(r02);
    L15:
        if (r14 != null) goto L17;
    L19:
        boolean r15 = false;
    L20:
        if (r2 != null) goto L22;
    L24:
        boolean r22 = false;
    L25:
        RectF r4 = AndroidUtilities.rectTmp;
        r4.set(r13.getX(), Math.max(getScrollY() - this.sectionRadius, this.contentView.getY() + r13.getY()), r13.getX() + r13.getWidth(), Math.min((getHeight() + getScrollY()) + this.sectionRadius, (this.contentView.getY() + r13.getY()) + r13.getHeight()));
        if (r15 == false) goto L40;
        if (r22 == false) goto L40;
        if (r13.getY() < r4.top) goto L31;
        r15 = true;
    L33:
        if ((r13.getY() + r13.getHeight()) <= r4.bottom) goto L36;
        r3 = false;
    L36:
        if (r15 == false) goto L39;
        if (r3 == false) goto L39;
        return;
    L39:
        r22 = r3;
        goto L40
    L31:
        r15 = false;
    L40:
        if (r15 == true) goto L48;
        if (r22 == true) goto L48;
        this.clipPath.rewind();
        if (RecyclerListView.isRoundSectionView(r13) == false) goto L45;
        float r132 = getSingleSectionRadius();
    L46:
        this.clipPath.addRoundRect(r4, r132, r132, Path.Direction.CW);
        r12.clipPath(this.clipPath);
        return;
    L45:
        r132 = this.sectionRadius;
    L48:
        if (r15 == true) goto L51;
        this.clipPath.rewind();
        this.clipPath.addRoundRect(r4, this.sectionRadiusTop, Path.Direction.CW);
        r12.clipPath(this.clipPath);
        return;
    L51:
        if (r22 == true) goto L55;
        this.clipPath.rewind();
        this.clipPath.addRoundRect(r4, this.sectionRadiusBottom, Path.Direction.CW);
        r12.clipPath(this.clipPath);
        return;
    L55:
        return;
    L22:
        if (isSectionView(r2) == false) goto L24;
        r22 = true;
        goto L25
    L17:
        if (isSectionView(r14) == false) goto L19;
        r15 = true;
        goto L20
    L9:
        r14 = this.contentView.getChildAt(r1);
        goto L10
    }

    private void drawSectionBackground(Canvas r12, View r13, View r14) {
        if (r13 == null) goto L27;
        if (r14 == null) goto L28;
        ViewGroup.LayoutParams r0 = r13.getLayoutParams();
        ViewGroup.LayoutParams r1 = r14.getLayoutParams();
        float r4 = 0.0f;
        if (r13.getParent() != this.contentView) goto L8;
    L10:
        float r02 = 0.0f;
    L11:
        ViewParent r2 = r14.getParent();
        LinearLayout r3 = this.contentView;
        if (r2 != r3) goto L14;
    L16:
        RectF r6 = AndroidUtilities.rectTmp;
        r6.set(r3.getX() + getChildX(r13), Math.max(getScrollY() - this.sectionRadius, (this.contentView.getY() + getChildY(r13)) - r02), (this.contentView.getX() + getChildX(r13)) + r13.getWidth(), Math.min((getHeight() + this.sectionRadius) + getScrollY(), ((this.contentView.getY() + getChildY(r14)) + r14.getHeight()) + r4));
        if (r6.bottom < r6.top) goto L29;
        if (r13 == r14) goto L21;
    L24:
        float r142 = this.sectionRadius;
    L23:
        float r7 = r142;
        RecyclerListView.drawBackgroundRect(r12, r6, r7, r7, r13.getAlpha(), this.resourcesProvider);
        return;
    L21:
        if (RecyclerListView.isRoundSectionView(r13) == false) goto L24;
        r142 = getSingleSectionRadius();
        goto L23
    L29:
        return;
    L14:
        if ((r1 instanceof ViewGroup.MarginLayoutParams) == false) goto L16;
        r4 = ((ViewGroup.MarginLayoutParams) r1).topMargin;
        goto L16
    L8:
        if ((r0 instanceof ViewGroup.MarginLayoutParams) == false) goto L10;
        r02 = ((ViewGroup.MarginLayoutParams) r0).topMargin;
        goto L11
    L28:
        return;
    }

    private void drawSectionsBackgrounds(Canvas r10) {
        this.children.clear();
        gatherChildren(this.contentView, 0.0f, 0.0f);
        ArrayList<View> r0 = this.children;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        View r4 = null;
        View r5 = null;
    L4:
        if (r3 >= r1) goto L15;
        View r6 = r0.get(r3);
        r3 = r3 + 1;
        View r62 = r6;
        if (isSectionView(r62) == false) goto L7;
        if (r4 != null) goto L10;
    L12:
        if (r4 != null) goto L14;
        r4 = r62;
    L14:
        r5 = r62;
        goto L4
    L10:
        if (Math.abs(r5.getAlpha() - r62.getAlpha()) <= 0.1f) goto L12;
        drawSectionBackground(r10, r4, r5);
        r4 = null;
        goto L12
    L7:
        drawSectionBackground(r10, r4, r5);
        goto L3
    L15:
        drawSectionBackground(r10, r4, r5);
    }

    private void gatherChildren(ViewGroup r7, float r8, float r9) {
        int r0 = 0;
    L4:
        if (r0 >= r7.getChildCount()) goto L19;
        View r1 = r7.getChildAt(r0);
        if (r1.getVisibility() != 0) goto L18;
        if ((r1 instanceof LinearLayout) == false) goto L17;
        LinearLayout r2 = (LinearLayout) r1;
        if (r2.getOrientation() != 1) goto L17;
        if ((r1.getX() + r8) > this.contentView.getPaddingLeft()) goto L17;
        if (((r1.getX() + r8) + r1.getWidth()) < (this.contentView.getWidth() - this.contentView.getPaddingRight())) goto L17;
        gatherChildren(r2, r1.getX() + r8, r1.getY() + r9);
    L17:
        this.children.add(r1);
    L18:
        r0 = r0 + 1;
        goto L4
    }

    private float getChildX(View r2) {
        if (r2 == this.contentView) goto L10;
        if ((r2.getParent() instanceof View) == false) goto L10;
        return getChildX((View) r2.getParent()) + r2.getX();
    L10:
        return r2.getX();
    }

    private float getChildY(View r2) {
        if (r2 == this.contentView) goto L10;
        if ((r2.getParent() instanceof View) == false) goto L10;
        return getChildY((View) r2.getParent()) + r2.getY();
    L10:
        return r2.getY();
    }

    private float getSingleSectionRadius() {
        return AndroidUtilities.rectTmp.height() / 2.0f;
    }

    public static boolean isSectionView(View r2) {
        if (Objects.equals(r2.getTag(), -33024) == false) goto L5;
        return false;
    L5:
        if ((r2 instanceof TextInfoPrivacyCell) == false) goto L7;
        return false;
    L7:
        if ((r2 instanceof ShadowSectionCell) == false) goto L9;
        return false;
    L9:
        if ((r2 instanceof FiltersSetupActivity.HintInnerCell) == true) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r1) {
        drawSectionsBackgrounds(r1);
        super.dispatchDraw(r1);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r1, View r2, long r3) {
        return super.drawChild(r1, r2, r3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ViewParent invalidateChildInParent(int[] r1, Rect r2) {
        postInvalidateOnAnimation();
        return super.invalidateChildInParent(r1, r2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onDescendantInvalidated(View r1, View r2) {
        super.onDescendantInvalidated(r1, r2);
        invalidate();
    }

    public void onScroll(Runnable r1) {
        this.onScroll.add(r1);
    }

    @Override // android.view.View
    public void onScrollChanged(int r1, int r2, int r3, int r4) {
        super.onScrollChanged(r1, r2, r3, r4);
        ArrayList<Runnable> r12 = this.onScroll;
        int r22 = r12.size();
        int r32 = 0;
    L3:
        if (r32 >= r22) goto L5;
        Runnable r42 = r12.get(r32);
        r32 = r32 + 1;
        r42.run();
        goto L3
    L5:
        invalidate();
        this.contentView.invalidate();
    }
}
