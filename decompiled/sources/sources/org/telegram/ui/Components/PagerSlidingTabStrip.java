package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.viewpager.widget.ViewPager;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class PagerSlidingTabStrip extends HorizontalScrollView {
    private int currentPosition;
    private float currentPositionOffset;
    private LinearLayout.LayoutParams defaultTabLayoutParams;
    public ViewPager.OnPageChangeListener delegatePageListener;
    private int dividerPadding;
    private int indicatorColor;
    private int indicatorHeight;
    private int lastScrollX;
    private AnimatedFloat lineLeftAnimated;
    private AnimatedFloat lineRightAnimated;
    private final PageListener pageListener;
    private ViewPager pager;
    private Paint rectPaint;
    private final Theme.ResourcesProvider resourcesProvider;
    private int scrollOffset;
    private boolean shouldExpand;
    private int tabCount;
    private int tabPadding;
    private LinearLayout tabsContainer;
    private int underlineColor;
    private int underlineHeight;

    /* renamed from: org.telegram.ui.Components.PagerSlidingTabStrip$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ PagerSlidingTabStrip this$0;

        public AnonymousClass1(PagerSlidingTabStrip r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            this.this$0.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            PagerSlidingTabStrip r0 = this.this$0;
            PagerSlidingTabStrip.m11324$$Nest$fputcurrentPosition(r0, PagerSlidingTabStrip.m11322$$Nest$fgetpager(r0).getCurrentItem());
            PagerSlidingTabStrip r2 = this.this$0;
            PagerSlidingTabStrip.m11327$$Nest$mscrollToChild(r2, PagerSlidingTabStrip.m11321$$Nest$fgetcurrentPosition(r2), 0);
        }
    }

    /* renamed from: org.telegram.ui.Components.PagerSlidingTabStrip$2, reason: invalid class name */
    public class AnonymousClass2 extends ImageView {
        final /* synthetic */ PagerSlidingTabStrip this$0;
        final /* synthetic */ int val$position;

        public AnonymousClass2(PagerSlidingTabStrip r1, Context r2, int r3) {
            this.this$0 = r1;
            this.val$position = r3;
            super(r2);
        }

        @Override // android.widget.ImageView, android.view.View
        public void onDraw(Canvas r3) {
            super.onDraw(r3);
            if ((PagerSlidingTabStrip.m11322$$Nest$fgetpager(this.this$0).getAdapter() instanceof IconTabProvider) == false) goto L6;
            ((IconTabProvider) PagerSlidingTabStrip.m11322$$Nest$fgetpager(this.this$0).getAdapter()).customOnDraw(r3, this, this.val$position);
            return;
        }

        @Override // android.widget.ImageView, android.view.View
        public void setSelected(boolean r2) {
            super.setSelected(r2);
            Drawable r0 = getBackground();
            if (r0 == null) goto L10;
            PagerSlidingTabStrip r1 = this.this$0;
            if (r2 == false) goto L7;
            float r22 = 0.1f;
        L8:
            Theme.setSelectorDrawableColor(r0, PagerSlidingTabStrip.m11326$$Nest$mgetGlassIconColor(r1, r22), true);
            return;
        L7:
            r22 = 0.05f;
            goto L8
        }
    }

    public interface IconTabProvider {
        boolean canScrollToTab(int r1);

        void customOnDraw(Canvas r1, View r2, int r3);

        Drawable getPageIconDrawable(int r1);

        int getTabPadding(int r1);
    }

    public class PageListener implements ViewPager.OnPageChangeListener {
        final /* synthetic */ PagerSlidingTabStrip this$0;

        private PageListener(PagerSlidingTabStrip r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ PageListener(PagerSlidingTabStrip r1, PagerSlidingTabStripIA r2) {
            this(r1);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r4) {
            if (r4 != 0) goto L4;
            PagerSlidingTabStrip r0 = this.this$0;
            PagerSlidingTabStrip.m11327$$Nest$mscrollToChild(r0, PagerSlidingTabStrip.m11322$$Nest$fgetpager(r0).getCurrentItem(), 0);
        L4:
            ViewPager.OnPageChangeListener r3 = this.this$0.delegatePageListener;
            if (r3 == null) goto L8;
            r3.onPageScrollStateChanged(r4);
            return;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r3, float r4, int r5) {
            PagerSlidingTabStrip.m11324$$Nest$fputcurrentPosition(this.this$0, r3);
            PagerSlidingTabStrip.m11325$$Nest$fputcurrentPositionOffset(this.this$0, r4);
            if (PagerSlidingTabStrip.m11323$$Nest$fgettabsContainer(this.this$0).getChildAt(r3) == null) goto L8;
            PagerSlidingTabStrip.m11327$$Nest$mscrollToChild(this.this$0, r3, (int) (PagerSlidingTabStrip.m11323$$Nest$fgettabsContainer(r0).getChildAt(r3).getWidth() * r4));
            this.this$0.invalidate();
            ViewPager.OnPageChangeListener r2 = this.this$0.delegatePageListener;
            if (r2 == null) goto L9;
            r2.onPageScrolled(r3, r4, r5);
            return;
        L9:
            return;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r5) {
            ViewPager.OnPageChangeListener r0 = this.this$0.delegatePageListener;
            if (r0 == null) goto L5;
            r0.onPageSelected(r5);
        L5:
            int r1 = 0;
        L7:
            if (r1 >= PagerSlidingTabStrip.m11323$$Nest$fgettabsContainer(this.this$0).getChildCount()) goto L13;
            View r2 = PagerSlidingTabStrip.m11323$$Nest$fgettabsContainer(this.this$0).getChildAt(r1);
            if (r1 != r5) goto L11;
            boolean r3 = true;
        L12:
            r2.setSelected(r3);
            r1 = r1 + 1;
            goto L7
        L11:
            r3 = false;
            goto L12
        }
    }

    public class TextTab extends TextView {
        final int position;
        final /* synthetic */ PagerSlidingTabStrip this$0;

        public TextTab(PagerSlidingTabStrip r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2);
            this.position = r3;
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r3) {
            super.onDraw(r3);
            if ((PagerSlidingTabStrip.m11322$$Nest$fgetpager(this.this$0).getAdapter() instanceof IconTabProvider) == false) goto L6;
            ((IconTabProvider) PagerSlidingTabStrip.m11322$$Nest$fgetpager(this.this$0).getAdapter()).customOnDraw(r3, this, this.position);
            return;
        }

        @Override // android.widget.TextView, android.view.View
        public void setSelected(boolean r4) {
            super.setSelected(r4);
            Drawable r0 = getBackground();
            if (r0 == null) goto L9;
            PagerSlidingTabStrip r1 = this.this$0;
            if (r4 == false) goto L7;
            float r2 = 0.1f;
        L8:
            Theme.setSelectorDrawableColor(r0, PagerSlidingTabStrip.m11326$$Nest$mgetGlassIconColor(r1, r2), true);
            goto L9
        L7:
            r2 = 0.05f;
        L9:
            PagerSlidingTabStrip r02 = this.this$0;
            if (r4 == false) goto L12;
            float r42 = 0.8f;
        L13:
            setTextColor(PagerSlidingTabStrip.m11326$$Nest$mgetGlassIconColor(r02, r42));
            return;
        L12:
            r42 = 0.4f;
            goto L13
        }

        public void setSelectedProgress(float r4) {
            setTextColor(PagerSlidingTabStrip.m11326$$Nest$mgetGlassIconColor(this.this$0, AndroidUtilities.lerp(0.4f, 0.8f, r4)));
        }
    }

    /* renamed from: $r8$lambda$UsSCtYFnnGLTvKhf9_IDQ9Xs-2o, reason: not valid java name */
    public static /* synthetic */ void m11320$r8$lambda$UsSCtYFnnGLTvKhf9_IDQ9Xs2o(PagerSlidingTabStrip r0, int r1, View r2) {
        r0.lambda$addIconTab$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$gvFuApqcmEr2Fbwd4EPFo1dbXYM(PagerSlidingTabStrip r0, int r1, View r2) {
        r0.lambda$addTab$1(r1, r2);
    }

    /* renamed from: -$$Nest$fgetcurrentPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11321$$Nest$fgetcurrentPosition(PagerSlidingTabStrip r0) {
        return r0.currentPosition;
    }

    /* renamed from: -$$Nest$fgetpager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPager m11322$$Nest$fgetpager(PagerSlidingTabStrip r0) {
        return r0.pager;
    }

    /* renamed from: -$$Nest$fgettabsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m11323$$Nest$fgettabsContainer(PagerSlidingTabStrip r0) {
        return r0.tabsContainer;
    }

    /* renamed from: -$$Nest$fputcurrentPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11324$$Nest$fputcurrentPosition(PagerSlidingTabStrip r0, int r1) {
        r0.currentPosition = r1;
    }

    /* renamed from: -$$Nest$fputcurrentPositionOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11325$$Nest$fputcurrentPositionOffset(PagerSlidingTabStrip r0, float r1) {
        r0.currentPositionOffset = r1;
    }

    /* renamed from: -$$Nest$mgetGlassIconColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11326$$Nest$mgetGlassIconColor(PagerSlidingTabStrip r0, float r1) {
        return r0.getGlassIconColor(r1);
    }

    /* renamed from: -$$Nest$mscrollToChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11327$$Nest$mscrollToChild(PagerSlidingTabStrip r0, int r1, int r2) {
        r0.scrollToChild(r1, r2);
    }

    public PagerSlidingTabStrip(Context r6, Theme.ResourcesProvider r7) {
        super(r6);
        this.pageListener = new PageListener(this, null);
        this.currentPosition = 0;
        this.currentPositionOffset = 0.0f;
        this.indicatorColor = -10066330;
        this.underlineColor = 436207616;
        this.shouldExpand = false;
        this.scrollOffset = AndroidUtilities.dp(52.0f);
        this.indicatorHeight = AndroidUtilities.dp(8.0f);
        this.underlineHeight = AndroidUtilities.dp(2.0f);
        this.dividerPadding = AndroidUtilities.dp(12.0f);
        this.tabPadding = AndroidUtilities.dp(24.0f);
        this.lastScrollX = 0;
        CubicBezierInterpolator r2 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.lineLeftAnimated = new AnimatedFloat(this, 350, r2);
        this.lineRightAnimated = new AnimatedFloat(this, 350, r2);
        this.resourcesProvider = r7;
        setFillViewport(true);
        setWillNotDraw(false);
        LinearLayout r1 = new LinearLayout(r6);
        this.tabsContainer = r1;
        r1.setOrientation(0);
        this.tabsContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.tabsContainer);
        Paint r62 = new Paint();
        this.rectPaint = r62;
        r62.setAntiAlias(true);
        this.rectPaint.setStyle(Paint.Style.FILL);
        this.defaultTabLayoutParams = new LinearLayout.LayoutParams(-2, -1);
    }

    private void addIconTab(int r5, Drawable r6, CharSequence r7) {
        AnonymousClass2 r0 = new AnonymousClass2(this, getContext(), r5);
        boolean r1 = true;
        r0.setFocusable(true);
        RippleDrawable r2 = (RippleDrawable) Theme.createSelectorDrawable(getGlassIconColor(0.05f), 1, AndroidUtilities.dp(18.0f));
        Theme.setRippleDrawableForceSoftware(r2);
        r0.setBackground(r2);
        r0.setImageDrawable(r6);
        r0.setScaleType(ImageView.ScaleType.CENTER);
        r0.setOnClickListener(new PagerSlidingTabStrip$$ExternalSyntheticLambda0(this, r5));
        this.tabsContainer.addView(r0);
        if (r5 == this.currentPosition) goto L6;
        r1 = false;
    L6:
        r0.setSelected(r1);
        r0.setContentDescription(r7);
    }

    private void addTab(int r11, CharSequence r12) {
        TextTab r0 = new TextTab(this, getContext(), r11);
        boolean r2 = true;
        r0.setTextSize(1, 14.0f);
        r0.setTypeface(AndroidUtilities.bold());
        r0.setTextColor(getGlassIconColor(0.6f));
        r0.setFocusable(true);
        r0.setGravity(17);
        r0.setText(r12);
        ScaleStateListAnimator.apply(r0, 0.025f, 1.2f);
        r0.setOnClickListener(new PagerSlidingTabStrip$$ExternalSyntheticLambda2(this, r11));
        r0.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        this.tabsContainer.addView(r0, LayoutHelper.createLinear(-2, -2, 10.0f, 0.0f, 10.0f, 0.0f));
        if (r11 == this.currentPosition) goto L6;
        r2 = false;
    L6:
        r0.setSelected(r2);
    }

    private int getGlassIconColor(float r2) {
        return ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_glass_defaultIcon, this.resourcesProvider), (int) (r2 * 255.0f));
    }

    private /* synthetic */ void lambda$addIconTab$0(int r1, View r2) {
        if ((this.pager.getAdapter() instanceof IconTabProvider) == true) goto L5;
    L7:
        this.pager.setCurrentItem(r1, false);
        return;
    L5:
        if (((IconTabProvider) this.pager.getAdapter()).canScrollToTab(r1) == true) goto L7;
    }

    private /* synthetic */ void lambda$addTab$1(int r1, View r2) {
        if ((this.pager.getAdapter() instanceof IconTabProvider) == true) goto L5;
    L7:
        this.pager.setCurrentItem(r1, false);
        return;
    L5:
        if (((IconTabProvider) this.pager.getAdapter()).canScrollToTab(r1) == true) goto L7;
    }

    private void scrollToChild(int r2, int r3) {
        if (this.tabCount == 0) goto L17;
        View r0 = this.tabsContainer.getChildAt(r2);
        if (r0 == null) goto L18;
        int r02 = r0.getLeft() + r3;
        if (r2 > 0) goto L11;
        if (r3 > 0) goto L11;
    L13:
        if (r02 == this.lastScrollX) goto L16;
        this.lastScrollX = r02;
        scrollTo(r02, 0);
        return;
    L16:
        return;
    L11:
        r02 = r02 - this.scrollOffset;
        goto L13
    L18:
        return;
    }

    private void updateTabStyles() {
        int r1 = 0;
    L4:
        if (r1 >= this.tabCount) goto L13;
        View r2 = this.tabsContainer.getChildAt(r1);
        r2.setLayoutParams(this.defaultTabLayoutParams);
        if (this.shouldExpand == false) goto L9;
        r2.setPadding(0, 0, 0, 0);
        r2.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 1.0f));
    L12:
        r1 = r1 + 1;
        goto L4
    L9:
        if ((this.pager.getAdapter() instanceof IconTabProvider) == false) goto L11;
        int r3 = ((IconTabProvider) this.pager.getAdapter()).getTabPadding(r1);
        r2.setPadding(r3, 0, r3, 0);
        goto L12
    L11:
        int r32 = this.tabPadding;
        r2.setPadding(r32, 0, r32, 0);
        goto L12
    }

    public int getDividerPadding() {
        return this.dividerPadding;
    }

    public int getIndicatorColor() {
        return this.indicatorColor;
    }

    public int getIndicatorHeight() {
        return this.indicatorHeight;
    }

    public int getScrollOffset() {
        return this.scrollOffset;
    }

    public boolean getShouldExpand() {
        return this.shouldExpand;
    }

    public View getTab(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 < this.tabsContainer.getChildCount()) goto L7;
        return null;
    L7:
        return this.tabsContainer.getChildAt(r2);
    }

    public int getTabPaddingLeftRight() {
        return this.tabPadding;
    }

    public int getUnderlineColor() {
        return this.underlineColor;
    }

    public int getUnderlineHeight() {
        return this.underlineHeight;
    }

    public void notifyDataSetChanged() {
        this.tabsContainer.removeAllViews();
        this.tabCount = this.pager.getAdapter().getCount();
        int r0 = 0;
    L4:
        if (r0 >= this.tabCount) goto L13;
        boolean r1 = this.pager.getAdapter() instanceof IconTabProvider;
        ViewPager r2 = this.pager;
        if (r1 == false) goto L11;
        Drawable r12 = ((IconTabProvider) r2.getAdapter()).getPageIconDrawable(r0);
        ViewPager r22 = this.pager;
        if (r12 == null) goto L10;
        addIconTab(r0, r12, r22.getAdapter().getPageTitle(r0));
    L12:
        r0 = r0 + 1;
        goto L4
    L10:
        addTab(r0, r22.getAdapter().getPageTitle(r0));
        goto L12
    L11:
        addTab(r0, r2.getAdapter().getPageTitle(r0));
        goto L12
    L13:
        updateTabStyles();
        getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass1(this));
    }

    @Override // android.view.View
    public void onDraw(Canvas r14) {
        if (isInEditMode() == false) goto L5;
    L28:
        super.onDraw(r14);
        return;
    L5:
        if (this.tabCount == 0) goto L28;
        int r0 = getHeight();
        if (this.underlineHeight == 0) goto L10;
        this.rectPaint.setColor(this.underlineColor);
        RectF r1 = AndroidUtilities.rectTmp;
        r1.set(0.0f, r0 - this.underlineHeight, this.tabsContainer.getWidth(), r0);
        int r4 = this.underlineHeight;
        r14.drawRoundRect(r1, r4 / 2.0f, r4 / 2.0f, this.rectPaint);
    L10:
        View r12 = this.tabsContainer.getChildAt(this.currentPosition);
        if (r12 == null) goto L26;
        float r42 = r12.getLeft() + r12.getPaddingLeft();
        float r5 = r12.getRight() - r12.getPaddingRight();
        if (this.currentPositionOffset <= 0.0f) goto L22;
        int r6 = this.currentPosition;
        if (r6 >= (this.tabCount - 1)) goto L22;
        View r62 = this.tabsContainer.getChildAt(r6 + 1);
        float r7 = r62.getLeft() + r62.getPaddingLeft();
        float r9 = r62.getRight() - r62.getPaddingRight();
        float r10 = this.currentPositionOffset;
        float r72 = (r7 * r10) + ((1.0f - r10) * r42);
        float r92 = (r9 * r10) + ((1.0f - r10) * r5);
        this.lineLeftAnimated.set(r72, true);
        this.lineRightAnimated.set(r92, true);
        if ((r12 instanceof TextTab) == false) goto L20;
        ((TextTab) r12).setSelectedProgress(1.0f - this.currentPositionOffset);
    L20:
        if ((r62 instanceof TextTab) == false) goto L24;
        ((TextTab) r62).setSelectedProgress(this.currentPositionOffset);
    L24:
        if (this.indicatorHeight == 0) goto L26;
        this.rectPaint.setColor(getGlassIconColor(0.065f));
        RectF r13 = AndroidUtilities.rectTmp;
        r13.set(r72 - AndroidUtilities.dp(11.0f), getPaddingTop(), r92 + AndroidUtilities.dp(11.0f), r0 - getPaddingBottom());
        r13.offset(getPaddingLeft(), 0.0f);
        r14.drawRoundRect(r13, r13.height() / 2.0f, r13.height() / 2.0f, this.rectPaint);
    L22:
        r72 = this.lineLeftAnimated.set(r42);
        r92 = this.lineRightAnimated.set(r5);
    L26:
        super.onDraw(r14);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, r3);
        if (this.shouldExpand == true) goto L5;
        return;
    L5:
        if (View.MeasureSpec.getMode(r2) == 0) goto L10;
        int r22 = getMeasuredWidth();
        this.tabsContainer.measure(r22 | TLObject.FLAG_30, r3);
        return;
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        if (this.shouldExpand == true) goto L6;
        post(new PagerSlidingTabStrip$$ExternalSyntheticLambda1(this));
        return;
    }

    public void setDividerPadding(int r1) {
        this.dividerPadding = r1;
        invalidate();
    }

    public void setIndicatorColor(int r1) {
        this.indicatorColor = r1;
        invalidate();
    }

    public void setIndicatorColorResource(int r2) {
        this.indicatorColor = getResources().getColor(r2);
        invalidate();
    }

    public void setIndicatorHeight(int r1) {
        this.indicatorHeight = r1;
        invalidate();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener r1) {
        this.delegatePageListener = r1;
    }

    public void setScrollOffset(int r1) {
        this.scrollOffset = r1;
        invalidate();
    }

    public void setShouldExpand(boolean r3) {
        this.shouldExpand = r3;
        this.tabsContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        updateTabStyles();
        requestLayout();
    }

    public void setTabPaddingLeftRight(int r1) {
        this.tabPadding = r1;
        updateTabStyles();
    }

    public void setUnderlineColor(int r1) {
        this.underlineColor = r1;
        invalidate();
    }

    public void setUnderlineColorResource(int r2) {
        this.underlineColor = getResources().getColor(r2);
        invalidate();
    }

    public void setUnderlineHeight(int r1) {
        this.underlineHeight = r1;
        invalidate();
    }

    public void setViewPager(ViewPager r2) {
        this.pager = r2;
        if (r2.getAdapter() == null) goto L6;
        r2.setOnPageChangeListener(this.pageListener);
        notifyDataSetChanged();
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("ViewPager does not have adapter instance.");
    }
}
