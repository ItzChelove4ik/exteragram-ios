package com.google.android.material.tabs;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SimplePool;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.tgnet.TLObject;

@ViewPager.DecorView
/* loaded from: classes5.dex */
public class TabLayout extends HorizontalScrollView {
    private static final int ANIMATION_DURATION = 300;
    static final int DEFAULT_GAP_TEXT_ICON = 8;
    private static final int DEFAULT_HEIGHT = 48;
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
    private static final int DEF_STYLE_RES = 0;
    static final int FIXED_WRAP_GUTTER_MIN = 16;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    public static final int GRAVITY_START = 2;
    public static final int INDICATOR_ANIMATION_MODE_ELASTIC = 1;
    public static final int INDICATOR_ANIMATION_MODE_FADE = 2;
    public static final int INDICATOR_ANIMATION_MODE_LINEAR = 0;
    public static final int INDICATOR_GRAVITY_BOTTOM = 0;
    public static final int INDICATOR_GRAVITY_CENTER = 1;
    public static final int INDICATOR_GRAVITY_STRETCH = 3;
    public static final int INDICATOR_GRAVITY_TOP = 2;
    private static final int INVALID_WIDTH = -1;
    private static final String LOG_TAG = "TabLayout";
    public static final int MODE_AUTO = 2;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    private static final int SELECTED_INDICATOR_HEIGHT_DEFAULT = -1;
    public static final int TAB_LABEL_VISIBILITY_LABELED = 1;
    public static final int TAB_LABEL_VISIBILITY_UNLABELED = 0;
    private static final int TAB_MIN_WIDTH_MARGIN = 56;
    private static final Pools$Pool<Tab> tabPool = null;
    private AdapterChangeListener adapterChangeListener;
    private int contentInsetStart;
    private BaseOnTabSelectedListener currentVpSelectedListener;
    private final int defaultTabTextAppearance;
    int indicatorPosition;
    boolean inlineLabel;
    int mode;
    private TabLayoutOnPageChangeListener pageChangeListener;
    private PagerAdapter pagerAdapter;
    private DataSetObserver pagerAdapterObserver;
    private final int requestedTabMaxWidth;
    private final int requestedTabMinWidth;
    private ValueAnimator scrollAnimator;
    private final int scrollableTabMinWidth;
    private BaseOnTabSelectedListener selectedListener;
    private final ArrayList<BaseOnTabSelectedListener> selectedListeners;
    private Tab selectedTab;
    private int selectedTabTextAppearance;
    float selectedTabTextSize;
    private boolean setupViewPagerImplicitly;
    final SlidingTabIndicator slidingTabIndicator;
    final int tabBackgroundResId;
    int tabGravity;
    ColorStateList tabIconTint;
    PorterDuff.Mode tabIconTintMode;
    int tabIndicatorAnimationDuration;
    int tabIndicatorAnimationMode;
    boolean tabIndicatorFullWidth;
    int tabIndicatorGravity;
    int tabIndicatorHeight;
    private TabIndicatorInterpolator tabIndicatorInterpolator;
    private final TimeInterpolator tabIndicatorTimeInterpolator;
    int tabMaxWidth;
    int tabPaddingBottom;
    int tabPaddingEnd;
    int tabPaddingStart;
    int tabPaddingTop;
    ColorStateList tabRippleColorStateList;
    Drawable tabSelectedIndicator;
    private int tabSelectedIndicatorColor;
    private final int tabTextAppearance;
    ColorStateList tabTextColors;
    float tabTextMultiLineSize;
    float tabTextSize;
    private final Pools$Pool<TabView> tabViewPool;
    private final ArrayList<Tab> tabs;
    boolean unboundedRipple;
    ViewPager viewPager;
    private int viewPagerScrollState;

    /* renamed from: com.google.android.material.tabs.TabLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ TabLayout this$0;

        public AnonymousClass1(TabLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            this.this$0.scrollTo(((Integer) r2.getAnimatedValue()).intValue(), 0);
        }
    }

    public class AdapterChangeListener implements ViewPager.OnAdapterChangeListener {
        private boolean autoRefresh;
        final /* synthetic */ TabLayout this$0;

        public AdapterChangeListener(TabLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
        public void onAdapterChanged(ViewPager r2, PagerAdapter r3, PagerAdapter r4) {
            TabLayout r32 = this.this$0;
            if (r32.viewPager != r2) goto L6;
            r32.setPagerAdapter(r4, this.autoRefresh);
            return;
        }

        public void setAutoRefresh(boolean r1) {
            this.autoRefresh = r1;
        }
    }

    @Deprecated
    public interface BaseOnTabSelectedListener<T extends Tab> {
        void onTabReselected(T r1);

        void onTabSelected(T r1);

        void onTabUnselected(T r1);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LabelVisibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public interface OnTabSelectedListener extends BaseOnTabSelectedListener<Tab> {
    }

    public class PagerAdapterObserver extends DataSetObserver {
        final /* synthetic */ TabLayout this$0;

        public PagerAdapterObserver(TabLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            this.this$0.populateFromPagerAdapter();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            this.this$0.populateFromPagerAdapter();
        }
    }

    public class SlidingTabIndicator extends LinearLayout {
        ValueAnimator indicatorAnimator;
        private int layoutDirection;
        final /* synthetic */ TabLayout this$0;

        /* renamed from: com.google.android.material.tabs.TabLayout$SlidingTabIndicator$1, reason: invalid class name */
        public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
            final /* synthetic */ SlidingTabIndicator this$1;
            final /* synthetic */ View val$currentView;
            final /* synthetic */ View val$targetView;

            public AnonymousClass1(SlidingTabIndicator r1, View r2, View r3) {
                this.this$1 = r1;
                this.val$currentView = r2;
                this.val$targetView = r3;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator r3) {
                SlidingTabIndicator.access$1600(this.this$1, this.val$currentView, this.val$targetView, r3.getAnimatedFraction());
            }
        }

        public SlidingTabIndicator(TabLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.layoutDirection = -1;
            setWillNotDraw(false);
        }

        public static /* synthetic */ void access$100(SlidingTabIndicator r0) {
            r0.jumpIndicatorToSelectedPosition();
        }

        public static /* synthetic */ void access$1600(SlidingTabIndicator r0, View r1, View r2, float r3) {
            r0.tweenIndicatorPosition(r1, r2, r3);
        }

        private void jumpIndicatorToIndicatorPosition() {
            TabLayout r0 = this.this$0;
            if (r0.indicatorPosition != (-1)) goto L5;
            r0.indicatorPosition = r0.getSelectedTabPosition();
        L5:
            jumpIndicatorToPosition(this.this$0.indicatorPosition);
        }

        private void jumpIndicatorToPosition(int r5) {
            if (TabLayout.access$1400(this.this$0) != 0) goto L5;
        L9:
            View r0 = getChildAt(r5);
            TabIndicatorInterpolator r1 = TabLayout.access$1500(this.this$0);
            TabLayout r2 = this.this$0;
            r1.setIndicatorBoundsForTab(r2, r0, r2.tabSelectedIndicator);
            this.this$0.indicatorPosition = r5;
            return;
        L5:
            if (this.this$0.getTabSelectedIndicator().getBounds().left == (-1)) goto L7;
            return;
        L7:
            if (this.this$0.getTabSelectedIndicator().getBounds().right == (-1)) goto L9;
        }

        private void jumpIndicatorToSelectedPosition() {
            jumpIndicatorToPosition(this.this$0.getSelectedTabPosition());
        }

        private void tweenIndicatorPosition(View r8, View r9, float r10) {
            if (r8 != null) goto L4;
        L6:
            Drawable r82 = this.this$0.tabSelectedIndicator;
            r82.setBounds(-1, r82.getBounds().top, -1, this.this$0.tabSelectedIndicator.getBounds().bottom);
        L7:
            postInvalidateOnAnimation();
            return;
        L4:
            if (r8.getWidth() <= 0) goto L6;
            TabIndicatorInterpolator r1 = TabLayout.access$1500(this.this$0);
            TabLayout r2 = this.this$0;
            r1.updateIndicatorForOffset(r2, r8, r9, r10, r2.tabSelectedIndicator);
            goto L7
        }

        private void updateOrRecreateIndicatorAnimation(boolean r4, int r5, int r6) {
            TabLayout r0 = this.this$0;
            if (r0.indicatorPosition != r5) goto L5;
            return;
        L5:
            View r02 = getChildAt(r0.getSelectedTabPosition());
            View r1 = getChildAt(r5);
            if (r1 != null) goto L9;
            jumpIndicatorToSelectedPosition();
            return;
        L9:
            this.this$0.indicatorPosition = r5;
            AnonymousClass1 r52 = new AnonymousClass1(this, r02, r1);
            if (r4 == false) goto L13;
            ValueAnimator r42 = new ValueAnimator();
            this.indicatorAnimator = r42;
            r42.setInterpolator(TabLayout.access$1700(this.this$0));
            r42.setDuration(r6);
            r42.setFloatValues(new float[]{0.0f, 1.0f});
            r42.addUpdateListener(r52);
            r42.start();
            return;
        L13:
            this.indicatorAnimator.removeAllUpdateListeners();
            this.indicatorAnimator.addUpdateListener(r52);
        }

        public void animateIndicatorToPosition(int r2, int r3) {
            ValueAnimator r0 = this.indicatorAnimator;
            if (r0 != null) goto L5;
        L9:
            updateOrRecreateIndicatorAnimation(true, r2, r3);
            return;
        L5:
            if (r0.isRunning() == false) goto L9;
            if (this.this$0.indicatorPosition == r2) goto L9;
            this.indicatorAnimator.cancel();
            goto L9
        }

        public boolean childrenNeedLayout() {
            int r0 = getChildCount();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L9;
            if (getChildAt(r2).getWidth() <= 0) goto L6;
            r2 = r2 + 1;
            goto L3
        L6:
            return true;
        L9:
            return false;
        }

        @Override // android.view.View
        public void draw(Canvas r6) {
            int r0 = this.this$0.tabSelectedIndicator.getBounds().height();
            if (r0 >= 0) goto L5;
            r0 = this.this$0.tabSelectedIndicator.getIntrinsicHeight();
        L5:
            int r1 = this.this$0.tabIndicatorGravity;
            if (r1 != 0) goto L8;
            int r2 = getHeight() - r0;
            r0 = getHeight();
        L18:
            if (this.this$0.tabSelectedIndicator.getBounds().width() <= 0) goto L20;
            Rect r12 = this.this$0.tabSelectedIndicator.getBounds();
            this.this$0.tabSelectedIndicator.setBounds(r12.left, r2, r12.right, r0);
            this.this$0.tabSelectedIndicator.draw(r6);
        L20:
            super.draw(r6);
            return;
        L8:
            if (r1 == 1) goto L15;
            r2 = 0;
            if (r1 == 2) goto L18;
            if (r1 == 3) goto L14;
            r0 = 0;
            goto L18
        L14:
            r0 = getHeight();
            goto L18
        L15:
            r2 = (getHeight() - r0) / 2;
            r0 = (getHeight() + r0) / 2;
            goto L18
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            ValueAnimator r12 = this.indicatorAnimator;
            if (r12 != null) goto L5;
        L8:
            jumpIndicatorToIndicatorPosition();
            return;
        L5:
            if (r12.isRunning() == false) goto L8;
            updateOrRecreateIndicatorAnimation(false, this.this$0.getSelectedTabPosition(), -1);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            super.onMeasure(r9, r10);
            if (View.MeasureSpec.getMode(r9) != 1073741824) goto L40;
            TabLayout r0 = this.this$0;
            boolean r3 = true;
            if (r0.tabGravity != 1) goto L8;
        L9:
            int r02 = getChildCount();
            int r1 = 0;
            int r4 = 0;
            int r5 = 0;
        L10:
            if (r4 >= r02) goto L15;
            View r6 = getChildAt(r4);
            if (r6.getVisibility() != 0) goto L14;
            r5 = Math.max(r5, r6.getMeasuredWidth());
        L14:
            r4 = r4 + 1;
            goto L10
        L15:
            if (r5 > 0) goto L18;
            return;
        L18:
            if ((r5 * r02) > (getMeasuredWidth() - (((int) ViewUtils.dpToPx(getContext(), 16)) * 2))) goto L28;
            boolean r2 = false;
        L20:
            if (r1 >= r02) goto L27;
            LinearLayout.LayoutParams r42 = (LinearLayout.LayoutParams) getChildAt(r1).getLayoutParams();
            if (r42.width == r5) goto L24;
        L25:
            r42.width = r5;
            r42.weight = 0.0f;
            r2 = true;
        L26:
            r1 = r1 + 1;
            goto L20
        L24:
            if (r42.weight == 0.0f) goto L26;
        L27:
            r3 = r2;
        L29:
            if (r3 == false) goto L39;
            super.onMeasure(r9, r10);
            return;
        L39:
            return;
        L28:
            TabLayout r03 = this.this$0;
            r03.tabGravity = 0;
            r03.updateTabViews(false);
            goto L29
        L8:
            if (r0.mode == 2) goto L9;
            return;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int r1) {
            super.onRtlPropertiesChanged(r1);
        }

        public void setIndicatorPositionFromTabPosition(int r3, float r4) {
            this.this$0.indicatorPosition = Math.round(r3 + r4);
            ValueAnimator r0 = this.indicatorAnimator;
            if (r0 != null) goto L5;
        L7:
            tweenIndicatorPosition(getChildAt(r3), getChildAt(r3 + 1), r4);
            return;
        L5:
            if (r0.isRunning() == false) goto L7;
            this.indicatorAnimator.cancel();
            goto L7
        }

        public void setSelectedIndicatorHeight(int r5) {
            Rect r0 = this.this$0.tabSelectedIndicator.getBounds();
            this.this$0.tabSelectedIndicator.setBounds(r0.left, 0, r0.right, r5);
            requestLayout();
        }
    }

    public static class Tab {
        public static final int INVALID_POSITION = -1;
        private CharSequence contentDesc;
        private View customView;
        private Drawable icon;
        private int id;
        private int labelVisibilityMode;
        public TabLayout parent;
        private int position;
        private Object tag;
        private CharSequence text;
        public TabView view;

        public Tab() {
            this.position = -1;
            this.labelVisibilityMode = 1;
            this.id = -1;
        }

        public static /* synthetic */ int access$000(Tab r0) {
            return r0.id;
        }

        public static /* synthetic */ int access$1300(Tab r0) {
            return r0.labelVisibilityMode;
        }

        public static /* synthetic */ CharSequence access$300(Tab r0) {
            return r0.contentDesc;
        }

        public static /* synthetic */ CharSequence access$400(Tab r0) {
            return r0.text;
        }

        public BadgeDrawable getBadge() {
            return TabView.access$800(this.view);
        }

        public CharSequence getContentDescription() {
            TabView r0 = this.view;
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.getContentDescription();
        }

        public View getCustomView() {
            return this.customView;
        }

        public Drawable getIcon() {
            return this.icon;
        }

        public int getId() {
            return this.id;
        }

        public BadgeDrawable getOrCreateBadge() {
            return TabView.access$600(this.view);
        }

        public int getPosition() {
            return this.position;
        }

        public int getTabLabelVisibility() {
            return this.labelVisibilityMode;
        }

        public Object getTag() {
            return this.tag;
        }

        public CharSequence getText() {
            return this.text;
        }

        public boolean isSelected() {
            TabLayout r0 = this.parent;
            if (r0 == null) goto L12;
            int r02 = r0.getSelectedTabPosition();
            if (r02 != (-1)) goto L7;
            return false;
        L7:
            if (r02 != this.position) goto L14;
            return true;
        L14:
            return false;
        L12:
            f$$ExternalSyntheticBUOutline1.m("Tab not attached to a TabLayout");
            return false;
        }

        public void removeBadge() {
            TabView.access$700(this.view);
        }

        public void reset() {
            this.parent = null;
            this.view = null;
            this.tag = null;
            this.icon = null;
            this.id = -1;
            this.text = null;
            this.contentDesc = null;
            this.position = -1;
            this.customView = null;
        }

        public void select() {
            TabLayout r0 = this.parent;
            if (r0 == null) goto L6;
            r0.selectTab(this);
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("Tab not attached to a TabLayout");
        }

        public Tab setContentDescription(int r2) {
            TabLayout r0 = this.parent;
            if (r0 != null) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Tab not attached to a TabLayout");
            return null;
        L5:
            return setContentDescription(r0.getResources().getText(r2));
        }

        public Tab setContentDescription(CharSequence r1) {
            this.contentDesc = r1;
            updateView();
            return this;
        }

        public Tab setCustomView(int r4) {
            return setCustomView(LayoutInflater.from(this.view.getContext()).inflate(r4, this.view, false));
        }

        public Tab setCustomView(View r1) {
            this.customView = r1;
            updateView();
            return this;
        }

        public Tab setIcon(int r2) {
            TabLayout r0 = this.parent;
            if (r0 != null) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Tab not attached to a TabLayout");
            return null;
        L5:
            return setIcon(AppCompatResources.getDrawable(r0.getContext(), r2));
        }

        public Tab setIcon(Drawable r4) {
            this.icon = r4;
            TabLayout r42 = this.parent;
            if (r42.tabGravity != 1) goto L5;
        L6:
            r42.updateTabViews(true);
        L7:
            updateView();
            return this;
        L5:
            if (r42.mode != 2) goto L7;
            goto L6
        }

        public Tab setId(int r2) {
            this.id = r2;
            TabView r0 = this.view;
            if (r0 == null) goto L5;
            r0.setId(r2);
        L5:
            return this;
        }

        public void setPosition(int r1) {
            this.position = r1;
        }

        public Tab setTabLabelVisibility(int r4) {
            this.labelVisibilityMode = r4;
            TabLayout r42 = this.parent;
            if (r42.tabGravity != 1) goto L5;
        L6:
            r42.updateTabViews(true);
        L7:
            updateView();
            return this;
        L5:
            if (r42.mode != 2) goto L7;
            goto L6
        }

        public Tab setTag(Object r1) {
            this.tag = r1;
            return this;
        }

        public Tab setText(int r2) {
            TabLayout r0 = this.parent;
            if (r0 != null) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Tab not attached to a TabLayout");
            return null;
        L5:
            return setText(r0.getResources().getText(r2));
        }

        public Tab setText(CharSequence r2) {
            if (TextUtils.isEmpty(this.contentDesc) == true) goto L5;
        L7:
            this.text = r2;
            updateView();
            return this;
        L5:
            if (TextUtils.isEmpty(r2) == true) goto L7;
            this.view.setContentDescription(r2);
            goto L7
        }

        public void updateView() {
            TabView r0 = this.view;
            if (r0 == null) goto L6;
            r0.update();
            return;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabGravity {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabIndicatorAnimationMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabIndicatorGravity {
    }

    public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private int previousScrollState;
        private int scrollState;
        private final WeakReference<TabLayout> tabLayoutRef;

        public TabLayoutOnPageChangeListener(TabLayout r2) {
            this.tabLayoutRef = new WeakReference(r2);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r2) {
            this.previousScrollState = this.scrollState;
            this.scrollState = r2;
            TabLayout r22 = this.tabLayoutRef.get();
            if (r22 == null) goto L6;
            r22.updateViewPagerScrollState(this.scrollState);
            return;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r7, float r8, int r9) {
            TabLayout r0 = this.tabLayoutRef.get();
            if (r0 == null) goto L18;
            int r92 = this.scrollState;
            boolean r3 = true;
            if (r92 == 2) goto L7;
        L10:
            boolean r4 = true;
        L11:
            if (r92 == 2) goto L13;
        L16:
            r0.setScrollPosition(r7, r8, r3, r4, false);
            return;
        L13:
            if (this.previousScrollState != 0) goto L16;
            r4 = false;
            goto L16
        L7:
            if (this.previousScrollState == 1) goto L10;
            r4 = true;
            r3 = false;
            goto L11
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r4) {
            TabLayout r0 = this.tabLayoutRef.get();
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.getSelectedTabPosition() != r4) goto L7;
            return;
        L7:
            if (r4 >= r0.getTabCount()) goto L21;
            int r1 = this.scrollState;
            if (r1 != 0) goto L11;
        L16:
            boolean r3 = true;
        L17:
            r0.selectTab(r0.getTabAt(r4), r3);
            return;
        L11:
            if (r1 == 2) goto L13;
        L15:
            r3 = false;
            goto L17
        L13:
            if (this.previousScrollState != 0) goto L15;
        }

        public void reset() {
            this.scrollState = 0;
            this.previousScrollState = 0;
        }
    }

    public final class TabView extends LinearLayout {
        private View badgeAnchorView;
        private BadgeDrawable badgeDrawable;
        private Drawable baseBackgroundDrawable;
        private ImageView customIconView;
        private TextView customTextView;
        private View customView;
        private int defaultMaxLines;
        private ImageView iconView;
        private Tab tab;
        private TextView textView;
        final /* synthetic */ TabLayout this$0;

        /* renamed from: com.google.android.material.tabs.TabLayout$TabView$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnLayoutChangeListener {
            final /* synthetic */ TabView this$1;
            final /* synthetic */ View val$view;

            public AnonymousClass1(TabView r1, View r2) {
                this.this$1 = r1;
                this.val$view = r2;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
                if (this.val$view.getVisibility() != 0) goto L6;
                TabView.access$1200(this.this$1, this.val$view);
                return;
            }
        }

        public TabView(TabLayout r4, Context r5) {
            this.this$0 = r4;
            super(r5);
            this.defaultMaxLines = 2;
            updateBackgroundDrawable(r5);
            setPaddingRelative(r4.tabPaddingStart, r4.tabPaddingTop, r4.tabPaddingEnd, r4.tabPaddingBottom);
            setGravity(17);
            setOrientation(!r4.inlineLabel ? 1 : 0);
            setClickable(true);
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), 1002));
        }

        public static /* synthetic */ void access$1200(TabView r0, View r1) {
            r0.tryUpdateBadgeDrawableBounds(r1);
        }

        public static /* synthetic */ void access$200(TabView r0, Context r1) {
            r0.updateBackgroundDrawable(r1);
        }

        public static /* synthetic */ void access$500(TabView r0, Canvas r1) {
            r0.drawBackground(r1);
        }

        public static /* synthetic */ BadgeDrawable access$600(TabView r0) {
            return r0.getOrCreateBadge();
        }

        public static /* synthetic */ void access$700(TabView r0) {
            r0.removeBadge();
        }

        public static /* synthetic */ BadgeDrawable access$800(TabView r0) {
            return r0.getBadge();
        }

        private void addOnLayoutChangeListener(View r2) {
            if (r2 != null) goto L4;
            return;
        L4:
            r2.addOnLayoutChangeListener(new AnonymousClass1(this, r2));
        }

        private float approximateLineWidth(Layout r1, int r2, float r3) {
            return r1.getLineWidth(r2) * (r3 / r1.getPaint().getTextSize());
        }

        private void clipViewToPaddingForBadge(boolean r1) {
            setClipChildren(r1);
            setClipToPadding(r1);
            ViewGroup r0 = (ViewGroup) getParent();
            if (r0 == null) goto L6;
            r0.setClipChildren(r1);
            r0.setClipToPadding(r1);
            return;
        }

        private void drawBackground(Canvas r6) {
            Drawable r0 = this.baseBackgroundDrawable;
            if (r0 == null) goto L6;
            r0.setBounds(getLeft(), getTop(), getRight(), getBottom());
            this.baseBackgroundDrawable.draw(r6);
            return;
        }

        private BadgeDrawable getBadge() {
            return this.badgeDrawable;
        }

        private BadgeDrawable getOrCreateBadge() {
            if (this.badgeDrawable != null) goto L5;
            this.badgeDrawable = BadgeDrawable.create(getContext());
        L5:
            tryUpdateBadgeAnchor();
            BadgeDrawable r1 = this.badgeDrawable;
            if (r1 == null) goto L8;
            return r1;
        L8:
            Segment$$ExternalSyntheticBUOutline1.m("Unable to create badge");
            return null;
        }

        private boolean hasBadgeDrawable() {
            if (this.badgeDrawable == null) goto L6;
            return true;
        L6:
            return false;
        }

        private void inflateAndAddDefaultIconView() {
            ImageView r0 = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_icon, this, false);
            this.iconView = r0;
            addView(r0, 0);
        }

        private void inflateAndAddDefaultTextView() {
            TextView r0 = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_text, this, false);
            this.textView = r0;
            addView(r0);
        }

        private void removeBadge() {
            if (this.badgeAnchorView == null) goto L5;
            tryRemoveBadgeFromAnchor();
        L5:
            this.badgeDrawable = null;
        }

        private void tryAttachBadgeToAnchor(View r3) {
            if (hasBadgeDrawable() == false) goto L9;
            if (r3 == null) goto L8;
            clipViewToPaddingForBadge(false);
            BadgeUtils.attachBadgeDrawable(this.badgeDrawable, r3, null);
            this.badgeAnchorView = r3;
            return;
        L8:
            return;
        }

        private void tryRemoveBadgeFromAnchor() {
            if (hasBadgeDrawable() == false) goto L10;
            clipViewToPaddingForBadge(true);
            View r0 = this.badgeAnchorView;
            if (r0 == null) goto L9;
            BadgeUtils.detachBadgeDrawable(this.badgeDrawable, r0);
            this.badgeAnchorView = null;
            return;
        L9:
            return;
        }

        private void tryUpdateBadgeAnchor() {
            if (hasBadgeDrawable() == true) goto L6;
            return;
        L6:
            if (this.customView == null) goto L10;
            tryRemoveBadgeFromAnchor();
            return;
        L10:
            if (this.iconView == null) goto L22;
            Tab r0 = this.tab;
            if (r0 == null) goto L22;
            if (r0.getIcon() == null) goto L22;
            View r02 = this.badgeAnchorView;
            ImageView r1 = this.iconView;
            if (r02 == r1) goto L19;
            tryRemoveBadgeFromAnchor();
            tryAttachBadgeToAnchor(this.iconView);
            return;
        L19:
            tryUpdateBadgeDrawableBounds(r1);
            return;
        L22:
            if (this.textView == null) goto L33;
            Tab r03 = this.tab;
            if (r03 == null) goto L33;
            if (r03.getTabLabelVisibility() != 1) goto L33;
            View r04 = this.badgeAnchorView;
            TextView r12 = this.textView;
            if (r04 == r12) goto L31;
            tryRemoveBadgeFromAnchor();
            tryAttachBadgeToAnchor(this.textView);
            return;
        L31:
            tryUpdateBadgeDrawableBounds(r12);
            return;
        L33:
            tryRemoveBadgeFromAnchor();
        }

        private void tryUpdateBadgeDrawableBounds(View r2) {
            if (hasBadgeDrawable() == true) goto L5;
            return;
        L5:
            if (r2 != this.badgeAnchorView) goto L9;
            BadgeUtils.setBadgeDrawableBounds(this.badgeDrawable, r2, null);
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [android.graphics.drawable.LayerDrawable, android.graphics.drawable.RippleDrawable] */
        /* JADX WARN: Type inference failed for: r5v0, types: [android.view.View, com.google.android.material.tabs.TabLayout$TabView] */
        /* JADX WARN: Type inference failed for: r6v1, types: [android.graphics.drawable.Drawable] */
        private void updateBackgroundDrawable(Context r6) {
            int r0 = this.this$0.tabBackgroundResId;
            if (r0 == 0) goto L9;
            Drawable r02 = AppCompatResources.getDrawable(r6, r0);
            this.baseBackgroundDrawable = r02;
            if (r02 != null) goto L7;
        L10:
            GradientDrawable r03 = new GradientDrawable();
            r03.setColor(0);
            if (this.this$0.tabRippleColorStateList == null) goto L16;
            GradientDrawable r2 = new GradientDrawable();
            r2.setCornerRadius(1.0E-5f);
            r2.setColor(-1);
            ColorStateList r3 = RippleUtils.convertToRippleDrawableColor(this.this$0.tabRippleColorStateList);
            if (this.this$0.unboundedRipple == false) goto L15;
            r03 = FocusRingDrawable.wrap(r6, new RippleDrawable(r3, null, null));
            goto L16
        L15:
            ?? r1 = new RippleDrawable(r3, r03, r2);
            FocusRingDrawable.layer(r6, r1);
            r03 = r1;
        L16:
            setBackground(r03);
            this.this$0.invalidate();
            return;
        L7:
            if (r02.isStateful() == false) goto L10;
            this.baseBackgroundDrawable.setState(getDrawableState());
            goto L10
        L9:
            this.baseBackgroundDrawable = null;
            goto L10
        }

        private void updateTextAndIcon(TextView r8, ImageView r9, boolean r10) {
            Tab r0 = this.tab;
            CharSequence r1 = null;
            if (r0 != null) goto L5;
        L7:
            Drawable r02 = null;
        L8:
            if (r02 == null) goto L12;
            r02.setTintList(this.this$0.tabIconTint);
            PorterDuff.Mode r2 = this.this$0.tabIconTintMode;
            if (r2 == null) goto L12;
            r02.setTintMode(r2);
        L12:
            Tab r22 = this.tab;
            if (r22 == null) goto L15;
            CharSequence r23 = r22.getText();
        L17:
            if (r9 == null) goto L21;
            if (r02 == null) goto L20;
            r9.setImageDrawable(r02);
            r9.setVisibility(0);
            setVisibility(0);
            goto L21
        L20:
            r9.setVisibility(8);
            r9.setImageDrawable(null);
        L21:
            boolean r03 = TextUtils.isEmpty(r23);
            if (r8 == null) goto L38;
            if (r03 == true) goto L27;
            boolean r6 = true;
            if (Tab.access$1300(this.tab) != 1) goto L27;
        L28:
            if (r03 == true) goto L30;
            CharSequence r5 = r23;
        L31:
            r8.setText(r5);
            if (r6 == false) goto L34;
            int r52 = 0;
        L35:
            r8.setVisibility(r52);
            if (r03 == true) goto L39;
            setVisibility(0);
        L39:
            if (r10 == false) goto L55;
            if (r9 == null) goto L55;
            ViewGroup.MarginLayoutParams r82 = (ViewGroup.MarginLayoutParams) r9.getLayoutParams();
            if (r6 == true) goto L44;
        L46:
            int r102 = 0;
        L48:
            if (this.this$0.inlineLabel == false) goto L53;
            if (r102 == r82.getMarginEnd()) goto L55;
            r82.setMarginEnd(r102);
            r82.bottomMargin = 0;
            r9.setLayoutParams(r82);
            r9.requestLayout();
            goto L55
        L53:
            if (r102 == r82.bottomMargin) goto L55;
            r82.bottomMargin = r102;
            r82.setMarginEnd(0);
            r9.setLayoutParams(r82);
            r9.requestLayout();
            goto L55
        L44:
            if (r9.getVisibility() != 0) goto L46;
            r102 = (int) ViewUtils.dpToPx(getContext(), 8);
        L55:
            Tab r83 = this.tab;
            if (r83 == null) goto L58;
            r1 = Tab.access$300(r83);
        L58:
            if (r03 == false) goto L61;
            r23 = r1;
        L61:
            TooltipCompat.setTooltipText(this, r23);
            return;
        L34:
            r52 = 8;
            goto L35
        L30:
            r5 = null;
        L27:
            r6 = false;
            goto L28
        L38:
            r6 = false;
            goto L39
        L15:
            r23 = null;
            goto L17
        L5:
            if (r0.getIcon() == null) goto L7;
            r02 = DrawableCompat.wrap(this.tab.getIcon()).mutate();
            goto L8
        }

        @Override // android.view.ViewGroup, android.view.View
        public void drawableStateChanged() {
            super.drawableStateChanged();
            int[] r0 = getDrawableState();
            Drawable r1 = this.baseBackgroundDrawable;
            if (r1 != null) goto L5;
        L7:
            boolean r02 = false;
        L8:
            if (r02 == false) goto L11;
            invalidate();
            this.this$0.invalidate();
            return;
        L11:
            return;
        L5:
            if (r1.isStateful() == false) goto L7;
            r02 = this.baseBackgroundDrawable.setState(r0);
            goto L8
        }

        public int getContentHeight() {
            int r4 = 0;
            View[] r3 = {this.textView, this.iconView, this.customView};
            int r8 = 0;
            int r1 = 0;
            boolean r5 = false;
        L3:
            if (r4 >= 3) goto L17;
            View r6 = r3[r4];
            if (r6 == null) goto L15;
            if (r6.getVisibility() != 0) goto L15;
            if (r5 == false) goto L10;
            r1 = Math.min(r1, r6.getTop());
        L11:
            if (r5 == false) goto L13;
            r8 = Math.max(r8, r6.getBottom());
        L14:
            r5 = true;
            goto L15
        L13:
            r8 = r6.getBottom();
            goto L14
        L10:
            r1 = r6.getTop();
        L15:
            r4 = r4 + 1;
            goto L3
        L17:
            return r8 - r1;
        }

        public int getContentWidth() {
            int r4 = 0;
            View[] r3 = {this.textView, this.iconView, this.customView};
            int r8 = 0;
            int r1 = 0;
            boolean r5 = false;
        L3:
            if (r4 >= 3) goto L17;
            View r6 = r3[r4];
            if (r6 == null) goto L15;
            if (r6.getVisibility() != 0) goto L15;
            if (r5 == false) goto L10;
            r1 = Math.min(r1, r6.getLeft());
        L11:
            if (r5 == false) goto L13;
            r8 = Math.max(r8, r6.getRight());
        L14:
            r5 = true;
            goto L15
        L13:
            r8 = r6.getRight();
            goto L14
        L10:
            r1 = r6.getLeft();
        L15:
            r4 = r4 + 1;
            goto L3
        L17:
            return r8 - r1;
        }

        public Tab getTab() {
            return this.tab;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r8) {
            super.onInitializeAccessibilityNodeInfo(r8);
            AccessibilityNodeInfoCompat r82 = AccessibilityNodeInfoCompat.wrap(r8);
            BadgeDrawable r0 = this.badgeDrawable;
            if (r0 != null) goto L5;
        L7:
            r82.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, this.tab.getPosition(), 1, false, isSelected()));
            if (isSelected() == false) goto L10;
            r82.setClickable(false);
            r82.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        L10:
            r82.setRoleDescription(getResources().getString(R.string.item_view_role_description));
            return;
        L5:
            if (r0.isVisible() == false) goto L7;
            r82.setContentDescription(this.badgeDrawable.getContentDescription());
            goto L7
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            int r0 = View.MeasureSpec.getSize(r8);
            int r1 = View.MeasureSpec.getMode(r8);
            int r2 = this.this$0.getTabMaxWidth();
            if (r2 <= 0) goto L7;
            if (r1 == 0) goto L6;
            if (r0 <= r2) goto L7;
        L6:
            r8 = View.MeasureSpec.makeMeasureSpec(this.this$0.tabMaxWidth, Integer.MIN_VALUE);
        L7:
            super.onMeasure(r8, r9);
            if (this.textView == null) goto L40;
            float r02 = this.this$0.tabTextSize;
            if (isSelected() == true) goto L12;
        L14:
            int r12 = this.defaultMaxLines;
            ImageView r22 = this.iconView;
            if (r22 != null) goto L17;
        L19:
            TextView r23 = this.textView;
            if (r23 != null) goto L22;
        L24:
            float r24 = this.textView.getTextSize();
            int r4 = this.textView.getLineCount();
            int r5 = this.textView.getMaxLines();
            if (r02 != r24) goto L29;
            if (r5 < 0) goto L41;
            if (r12 != r5) goto L29;
            return;
        L41:
            return;
        L29:
            if (this.this$0.mode != 1) goto L37;
            if (r02 <= r24) goto L37;
            if (r4 != 1) goto L37;
            Layout r25 = this.textView.getLayout();
            if (r25 != null) goto L35;
            return;
        L35:
            if (approximateLineWidth(r25, 0, r02) <= ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) goto L37;
            return;
        L37:
            this.textView.setTextSize(0, r02);
            this.textView.setMaxLines(r12);
            super.onMeasure(r8, r9);
            return;
        L22:
            if (r23.getLineCount() <= 1) goto L24;
            r02 = this.this$0.tabTextMultiLineSize;
            goto L24
        L17:
            if (r22.getVisibility() != 0) goto L19;
            r12 = 1;
            goto L24
        L12:
            if (TabLayout.access$900(this.this$0) == (-1)) goto L14;
            r02 = this.this$0.selectedTabTextSize;
            goto L14
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean r0 = super.performClick();
            if (this.tab == null) goto L8;
            if (r0 == true) goto L6;
            playSoundEffect(0);
        L6:
            this.tab.select();
            return true;
        L8:
            return r0;
        }

        public void reset() {
            setTab(null);
            setSelected(false);
        }

        @Override // android.view.View
        public void setSelected(boolean r2) {
            isSelected();
            super.setSelected(r2);
            TextView r0 = this.textView;
            if (r0 == null) goto L5;
            r0.setSelected(r2);
        L5:
            ImageView r02 = this.iconView;
            if (r02 == null) goto L8;
            r02.setSelected(r2);
        L8:
            View r1 = this.customView;
            if (r1 == null) goto L12;
            r1.setSelected(r2);
            return;
        }

        public void setTab(Tab r2) {
            if (r2 == this.tab) goto L6;
            this.tab = r2;
            update();
            return;
        }

        public final void update() {
            updateTab();
            Tab r0 = this.tab;
            if (r0 != null) goto L5;
        L7:
            boolean r02 = false;
        L8:
            setSelected(r02);
            return;
        L5:
            if (r0.isSelected() == false) goto L7;
            r02 = true;
            goto L8
        }

        public final void updateOrientation() {
            setOrientation(!this.this$0.inlineLabel ? 1 : 0);
            TextView r0 = this.customTextView;
            if (r0 == null) goto L5;
        L9:
            updateTextAndIcon(r0, this.customIconView, false);
            return;
        L5:
            if (this.customIconView != null) goto L9;
            updateTextAndIcon(this.textView, this.iconView, true);
        }

        public final void updateTab() {
            Tab r0 = this.tab;
            if (r0 == null) goto L5;
            View r2 = r0.getCustomView();
        L6:
            if (r2 == null) goto L27;
            ViewParent r3 = r2.getParent();
            if (r3 == this) goto L17;
            if (r3 == null) goto L11;
            ((ViewGroup) r3).removeView(r2);
        L11:
            View r32 = this.customView;
            if (r32 == null) goto L16;
            ViewParent r33 = r32.getParent();
            if (r33 == null) goto L16;
            ((ViewGroup) r33).removeView(this.customView);
        L16:
            addView(r2);
        L17:
            this.customView = r2;
            TextView r34 = this.textView;
            if (r34 == null) goto L20;
            r34.setVisibility(8);
        L20:
            ImageView r35 = this.iconView;
            if (r35 == null) goto L23;
            r35.setVisibility(8);
            this.iconView.setImageDrawable(null);
        L23:
            TextView r1 = (TextView) r2.findViewById(android.R.id.text1);
            this.customTextView = r1;
            if (r1 == null) goto L26;
            this.defaultMaxLines = r1.getMaxLines();
        L26:
            this.customIconView = (ImageView) r2.findViewById(android.R.id.icon);
        L32:
            if (this.customView == null) goto L34;
            TextView r12 = this.customTextView;
            if (r12 == null) goto L52;
        L53:
            updateTextAndIcon(r12, this.customIconView, false);
        L54:
            if (r0 != null) goto L56;
            return;
        L56:
            if (TextUtils.isEmpty(Tab.access$300(r0)) == true) goto L60;
            setContentDescription(Tab.access$300(r0));
            return;
        L60:
            return;
        L52:
            if (this.customIconView == null) goto L54;
        L34:
            if (this.iconView != null) goto L37;
            inflateAndAddDefaultIconView();
        L37:
            if (this.textView != null) goto L39;
            inflateAndAddDefaultTextView();
            this.defaultMaxLines = this.textView.getMaxLines();
        L39:
            TextViewCompat.setTextAppearance(this.textView, TabLayout.access$1000(this.this$0));
            if (isSelected() == true) goto L42;
        L44:
            TextViewCompat.setTextAppearance(this.textView, TabLayout.access$1100(this.this$0));
        L45:
            ColorStateList r13 = this.this$0.tabTextColors;
            if (r13 == null) goto L48;
            this.textView.setTextColor(r13);
        L48:
            updateTextAndIcon(this.textView, this.iconView, true);
            tryUpdateBadgeAnchor();
            addOnLayoutChangeListener(this.iconView);
            addOnLayoutChangeListener(this.textView);
            goto L54
        L42:
            if (TabLayout.access$900(this.this$0) == (-1)) goto L44;
            TextViewCompat.setTextAppearance(this.textView, TabLayout.access$900(this.this$0));
            goto L45
        L27:
            View r22 = this.customView;
            if (r22 == null) goto L30;
            removeView(r22);
            this.customView = null;
        L30:
            this.customTextView = null;
            this.customIconView = null;
            goto L32
        L5:
            r2 = null;
            goto L6
        }
    }

    public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private final ViewPager viewPager;

        public ViewPagerOnTabSelectedListener(ViewPager r1) {
            this.viewPager = r1;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(Tab r1) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(Tab r1) {
            this.viewPager.setCurrentItem(r1.getPosition());
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(Tab r1) {
        }
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Design_TabLayout;
        tabPool = new Pools$SynchronizedPool(16);
    }

    public TabLayout(Context r2) {
        this(r2, null);
    }

    public TabLayout(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.tabStyle);
    }

    public TabLayout(Context r9, AttributeSet r10, int r11) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r9, r10, r11, r4), r10, r11);
        this.indicatorPosition = -1;
        this.tabs = new ArrayList();
        this.selectedTabTextAppearance = -1;
        this.tabSelectedIndicatorColor = 0;
        this.tabMaxWidth = Integer.MAX_VALUE;
        this.tabIndicatorHeight = -1;
        this.selectedListeners = new ArrayList();
        this.tabViewPool = new Pools$SimplePool(12);
        Context r0 = getContext();
        setHorizontalScrollBarEnabled(false);
        SlidingTabIndicator r7 = new SlidingTabIndicator(this, r0);
        this.slidingTabIndicator = r7;
        super.addView(r7, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray r102 = ThemeEnforcement.obtainStyledAttributes(r0, r10, R.styleable.TabLayout, r11, r4, new int[]{R.styleable.TabLayout_tabTextAppearance});
        ColorStateList r112 = DrawableUtils.getColorStateListOrNull(getBackground());
        if (r112 == null) goto L5;
        MaterialShapeDrawable r1 = new MaterialShapeDrawable();
        r1.setFillColor(r112);
        r1.initializeElevationOverlay(r0);
        r1.setElevation(getElevation());
        setBackground(r1);
    L5:
        setSelectedTabIndicator(MaterialResources.getDrawable(r0, r102, R.styleable.TabLayout_tabIndicator));
        setSelectedTabIndicatorColor(r102.getColor(R.styleable.TabLayout_tabIndicatorColor, 0));
        r7.setSelectedIndicatorHeight(r102.getDimensionPixelSize(R.styleable.TabLayout_tabIndicatorHeight, -1));
        setSelectedTabIndicatorGravity(r102.getInt(R.styleable.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorAnimationMode(r102.getInt(R.styleable.TabLayout_tabIndicatorAnimationMode, 0));
        setTabIndicatorFullWidth(r102.getBoolean(R.styleable.TabLayout_tabIndicatorFullWidth, true));
        int r113 = r102.getDimensionPixelSize(R.styleable.TabLayout_tabPadding, 0);
        this.tabPaddingBottom = r113;
        this.tabPaddingEnd = r113;
        this.tabPaddingTop = r113;
        this.tabPaddingStart = r113;
        this.tabPaddingStart = r102.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingStart, r113);
        this.tabPaddingTop = r102.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingTop, this.tabPaddingTop);
        this.tabPaddingEnd = r102.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingEnd, this.tabPaddingEnd);
        this.tabPaddingBottom = r102.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingBottom, this.tabPaddingBottom);
        if (ThemeEnforcement.isMaterial3Theme(r0) == false) goto L8;
        this.defaultTabTextAppearance = R.attr.textAppearanceTitleSmall;
    L9:
        int r114 = r102.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab);
        this.tabTextAppearance = r114;
        TypedArray r2 = r0.obtainStyledAttributes(r114, R$styleable.TextAppearance);
        this.tabTextSize = r2.getDimensionPixelSize(R$styleable.TextAppearance_android_textSize, 0);     // Catch: Throwable -> L34
        this.tabTextColors = MaterialResources.getColorStateList(r0, r2, R$styleable.TextAppearance_android_textColor);     // Catch: Throwable -> L34
        r2.recycle();
        if (r102.hasValue(R.styleable.TabLayout_tabSelectedTextAppearance) == false) goto L14;
        this.selectedTabTextAppearance = r102.getResourceId(R.styleable.TabLayout_tabSelectedTextAppearance, r114);
    L14:
        int r115 = this.selectedTabTextAppearance;
        if (r115 == (-1)) goto L27;
        TypedArray r116 = r0.obtainStyledAttributes(r115, R$styleable.TextAppearance);
        this.selectedTabTextSize = r116.getDimensionPixelSize(R$styleable.TextAppearance_android_textSize, (int) this.tabTextSize);     // Catch: Throwable -> L21
        ColorStateList r22 = MaterialResources.getColorStateList(r0, r116, R$styleable.TextAppearance_android_textColor);     // Catch: Throwable -> L21
        if (r22 == null) goto L23;
        this.tabTextColors = createColorStateList(this.tabTextColors.getDefaultColor(), r22.getColorForState(new int[]{android.R.attr.state_selected}, r22.getDefaultColor()));     // Catch: Throwable -> L21
    L23:
        r116.recycle();
    L21:
        th = move-exception;
        r116.recycle();
        throw th;
    L27:
        if (r102.hasValue(R.styleable.TabLayout_tabTextColor) == false) goto L30;
        this.tabTextColors = MaterialResources.getColorStateList(r0, r102, R.styleable.TabLayout_tabTextColor);
    L30:
        if (r102.hasValue(R.styleable.TabLayout_tabSelectedTextColor) == false) goto L32;
        this.tabTextColors = createColorStateList(this.tabTextColors.getDefaultColor(), r102.getColor(R.styleable.TabLayout_tabSelectedTextColor, 0));
    L32:
        this.tabIconTint = MaterialResources.getColorStateList(r0, r102, R.styleable.TabLayout_tabIconTint);
        this.tabIconTintMode = ViewUtils.parseTintMode(r102.getInt(R.styleable.TabLayout_tabIconTintMode, -1), null);
        this.tabRippleColorStateList = MaterialResources.getColorStateList(r0, r102, R.styleable.TabLayout_tabRippleColor);
        this.tabIndicatorAnimationDuration = r102.getInt(R.styleable.TabLayout_tabIndicatorAnimationDuration, 300);
        this.tabIndicatorTimeInterpolator = MotionUtils.resolveThemeInterpolator(r0, R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        this.requestedTabMinWidth = r102.getDimensionPixelSize(R.styleable.TabLayout_tabMinWidth, -1);
        this.requestedTabMaxWidth = r102.getDimensionPixelSize(R.styleable.TabLayout_tabMaxWidth, -1);
        this.tabBackgroundResId = r102.getResourceId(R.styleable.TabLayout_tabBackground, 0);
        this.contentInsetStart = r102.getDimensionPixelSize(R.styleable.TabLayout_tabContentStart, 0);
        this.mode = r102.getInt(R.styleable.TabLayout_tabMode, 1);
        this.tabGravity = r102.getInt(R.styleable.TabLayout_tabGravity, 0);
        this.inlineLabel = r102.getBoolean(R.styleable.TabLayout_tabInlineLabel, false);
        this.unboundedRipple = r102.getBoolean(R.styleable.TabLayout_tabUnboundedRipple, false);
        r102.recycle();
        Resources r92 = getResources();
        this.tabTextMultiLineSize = r92.getDimensionPixelSize(R.dimen.design_tab_text_size_2line);
        this.scrollableTabMinWidth = r92.getDimensionPixelSize(R.dimen.design_tab_scrollable_min_width);
        applyModeAndGravity();
        return;
    L34:
        th = move-exception;
        r2.recycle();
        throw th;
    L8:
        this.defaultTabTextAppearance = R.attr.textAppearanceButton;
        goto L9
    }

    public static /* synthetic */ int access$1000(TabLayout r0) {
        return r0.defaultTabTextAppearance;
    }

    public static /* synthetic */ int access$1100(TabLayout r0) {
        return r0.tabTextAppearance;
    }

    public static /* synthetic */ int access$1400(TabLayout r0) {
        return r0.viewPagerScrollState;
    }

    public static /* synthetic */ TabIndicatorInterpolator access$1500(TabLayout r0) {
        return r0.tabIndicatorInterpolator;
    }

    public static /* synthetic */ TimeInterpolator access$1700(TabLayout r0) {
        return r0.tabIndicatorTimeInterpolator;
    }

    public static /* synthetic */ int access$900(TabLayout r0) {
        return r0.selectedTabTextAppearance;
    }

    private void addTabFromItemView(TabItem r3) {
        Tab r0 = newTab();
        CharSequence r1 = r3.text;
        if (r1 == null) goto L5;
        r0.setText(r1);
    L5:
        Drawable r12 = r3.icon;
        if (r12 == null) goto L8;
        r0.setIcon(r12);
    L8:
        int r13 = r3.customLayout;
        if (r13 == 0) goto L12;
        r0.setCustomView(r13);
    L12:
        if (TextUtils.isEmpty(r3.getContentDescription()) == true) goto L14;
        r0.setContentDescription(r3.getContentDescription());
    L14:
        addTab(r0);
    }

    private void addTabView(Tab r3) {
        TabView r0 = r3.view;
        r0.setSelected(false);
        r0.setActivated(false);
        this.slidingTabIndicator.addView(r0, r3.getPosition(), createLayoutParamsForTabs());
    }

    private void addViewInternal(View r2) {
        if ((r2 instanceof TabItem) == false) goto L6;
        addTabFromItemView((TabItem) r2);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Only TabItem instances can be added to TabLayout");
    }

    private void animateToTab(int r4) {
        if (r4 != (-1)) goto L6;
        return;
    L6:
        if (getWindowToken() != null) goto L8;
    L17:
        setScrollPosition(r4, 0.0f, true);
        return;
    L8:
        if (isLaidOut() == false) goto L17;
        if (this.slidingTabIndicator.childrenNeedLayout() == true) goto L17;
        int r0 = getScrollX();
        int r1 = calculateScrollXForTab(r4, 0.0f);
        if (r0 == r1) goto L15;
        ensureScrollAnimator();
        this.scrollAnimator.setIntValues(new int[]{r0, r1});
        this.scrollAnimator.start();
    L15:
        this.slidingTabIndicator.animateIndicatorToPosition(r4, this.tabIndicatorAnimationDuration);
    }

    private void applyGravityForModeScrollable(int r2) {
        if (r2 != 0) goto L4;
        Log.w(LOG_TAG, "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
    L11:
        this.slidingTabIndicator.setGravity(PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES);
        return;
    L4:
        if (r2 != 1) goto L6;
        this.slidingTabIndicator.setGravity(1);
        return;
    L6:
        if (r2 == 2) goto L11;
    }

    private void applyModeAndGravity() {
        int r0 = this.mode;
        if (r0 == 0) goto L7;
        if (r0 == 2) goto L7;
        int r02 = 0;
    L8:
        this.slidingTabIndicator.setPaddingRelative(r02, 0, 0, 0);
        int r03 = this.mode;
        if (r03 == 0) goto L17;
        if (r03 == 1) goto L14;
        if (r03 == 2) goto L14;
    L18:
        updateTabViews(true);
        return;
    L14:
        if (this.tabGravity != 2) goto L16;
        Log.w(LOG_TAG, "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
    L16:
        this.slidingTabIndicator.setGravity(1);
        goto L18
    L17:
        applyGravityForModeScrollable(this.tabGravity);
    L7:
        r02 = Math.max(0, this.contentInsetStart - this.tabPaddingStart);
        goto L8
    }

    private int calculateScrollXForTab(int r5, float r6) {
        int r0 = this.mode;
        int r1 = 0;
        if (r0 == 0) goto L7;
        if (r0 == 2) goto L7;
        return 0;
    L7:
        View r02 = this.slidingTabIndicator.getChildAt(r5);
        if (r02 != null) goto L10;
        return 0;
    L10:
        int r52 = r5 + 1;
        if (r52 >= this.slidingTabIndicator.getChildCount()) goto L13;
        View r53 = this.slidingTabIndicator.getChildAt(r52);
    L14:
        int r3 = r02.getWidth();
        if (r53 == null) goto L17;
        r1 = r53.getWidth();
    L17:
        int r54 = (r02.getLeft() + (r3 / 2)) - (getWidth() / 2);
        int r62 = (int) (((r3 + r1) * 0.5f) * r6);
        if (getLayoutDirection() != 0) goto L22;
        return r54 + r62;
    L22:
        return r54 - r62;
    L13:
        r53 = null;
        goto L14
    }

    private void configureTab(Tab r4, int r5) {
        r4.setPosition(r5);
        this.tabs.add(r5, r4);
        int r42 = this.tabs.size();
        int r52 = r5 + 1;
        int r0 = -1;
    L3:
        if (r52 >= r42) goto L8;
        if (this.tabs.get(r52).getPosition() != this.indicatorPosition) goto L7;
        r0 = r52;
    L7:
        this.tabs.get(r52).setPosition(r52);
        r52 = r52 + 1;
        goto L3
    L8:
        this.indicatorPosition = r0;
    }

    private static ColorStateList createColorStateList(int r4, int r5) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{r5, r4});
    }

    private LinearLayout.LayoutParams createLayoutParamsForTabs() {
        LinearLayout.LayoutParams r0 = new LinearLayout.LayoutParams(-2, -1);
        updateTabViewLayoutParams(r0);
        return r0;
    }

    private TabView createTabView(Tab r3) {
        Pools$Pool<TabView> r0 = this.tabViewPool;
        if (r0 == null) goto L5;
        TabView r02 = r0.acquire();
    L6:
        if (r02 != null) goto L8;
        r02 = new TabView(this, getContext());
    L8:
        r02.setTab(r3);
        r02.setFocusable(true);
        r02.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(Tab.access$300(r3)) == false) goto L12;
        r02.setContentDescription(Tab.access$400(r3));
        return r02;
    L12:
        r02.setContentDescription(Tab.access$300(r3));
        return r02;
    L5:
        r02 = null;
        goto L6
    }

    private void dispatchTabReselected(Tab r3) {
        int r0 = this.selectedListeners.size() - 1;
    L3:
        if (r0 < 0) goto L5;
        this.selectedListeners.get(r0).onTabReselected(r3);
        r0 = r0 - 1;
        goto L3
    }

    private void dispatchTabSelected(Tab r3) {
        int r0 = this.selectedListeners.size() - 1;
    L3:
        if (r0 < 0) goto L5;
        this.selectedListeners.get(r0).onTabSelected(r3);
        r0 = r0 - 1;
        goto L3
    }

    private void dispatchTabUnselected(Tab r3) {
        int r0 = this.selectedListeners.size() - 1;
    L3:
        if (r0 < 0) goto L5;
        this.selectedListeners.get(r0).onTabUnselected(r3);
        r0 = r0 - 1;
        goto L3
    }

    private void ensureScrollAnimator() {
        if (this.scrollAnimator != null) goto L6;
        ValueAnimator r0 = new ValueAnimator();
        this.scrollAnimator = r0;
        r0.setInterpolator(this.tabIndicatorTimeInterpolator);
        this.scrollAnimator.setDuration(this.tabIndicatorAnimationDuration);
        this.scrollAnimator.addUpdateListener(new AnonymousClass1(this));
        return;
    }

    private int getDefaultHeight() {
        int r0 = this.tabs.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L22;
        Tab r2 = this.tabs.get(r1);
        if (r2 == null) goto L14;
        if (r2.getIcon() == null) goto L14;
        if (TextUtils.isEmpty(r2.getText()) == true) goto L14;
        if (this.inlineLabel == true) goto L15;
        return 72;
    L15:
        return 48;
    L14:
        r1 = r1 + 1;
        goto L3
    L22:
        return 48;
    }

    private int getTabMinWidth() {
        int r0 = this.requestedTabMinWidth;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        int r02 = this.mode;
        if (r02 == 0) goto L13;
        if (r02 == 2) goto L13;
        return 0;
    L13:
        return this.scrollableTabMinWidth;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.slidingTabIndicator.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private boolean isScrollingEnabled() {
        if (getTabMode() != 0) goto L5;
        return true;
    L5:
        if (getTabMode() == 2) goto L11;
        return false;
    L11:
        return true;
    }

    private void removeTabViewAt(int r3) {
        TabView r0 = (TabView) this.slidingTabIndicator.getChildAt(r3);
        this.slidingTabIndicator.removeViewAt(r3);
        if (r0 == null) goto L5;
        r0.reset();
        this.tabViewPool.release(r0);
    L5:
        requestLayout();
    }

    private void setSelectedTabView(int r7) {
        int r0 = this.slidingTabIndicator.getChildCount();
        if (r7 >= r0) goto L32;
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L37;
        View r3 = this.slidingTabIndicator.getChildAt(r2);
        boolean r4 = true;
        if (r2 == r7) goto L9;
    L10:
        if (r2 != r7) goto L12;
    L23:
        if (r2 != r7) goto L25;
        boolean r5 = true;
    L26:
        r3.setSelected(r5);
        if (r2 == r7) goto L30;
        r4 = false;
    L30:
        r3.setActivated(r4);
    L31:
        r2 = r2 + 1;
        goto L5
    L25:
        r5 = false;
        goto L26
    L12:
        if (r3.isSelected() == false) goto L23;
    L13:
        if (r2 != r7) goto L15;
        boolean r52 = true;
    L16:
        r3.setSelected(r52);
        if (r2 == r7) goto L20;
        r4 = false;
    L20:
        r3.setActivated(r4);
        if ((r3 instanceof TabView) == false) goto L31;
        ((TabView) r3).updateTab();
        goto L31
    L15:
        r52 = false;
        goto L16
    L9:
        if (r3.isSelected() == false) goto L13;
    L37:
        return;
    }

    private void setupWithViewPager(ViewPager r3, boolean r4, boolean r5) {
        ViewPager r0 = this.viewPager;
        if (r0 == null) goto L10;
        TabLayoutOnPageChangeListener r1 = this.pageChangeListener;
        if (r1 == null) goto L7;
        r0.removeOnPageChangeListener(r1);
    L7:
        AdapterChangeListener r02 = this.adapterChangeListener;
        if (r02 == null) goto L10;
        this.viewPager.removeOnAdapterChangeListener(r02);
    L10:
        BaseOnTabSelectedListener r03 = this.currentVpSelectedListener;
        if (r03 == null) goto L13;
        removeOnTabSelectedListener(r03);
        this.currentVpSelectedListener = null;
    L13:
        if (r3 == null) goto L24;
        this.viewPager = r3;
        if (this.pageChangeListener != null) goto L17;
        this.pageChangeListener = new TabLayoutOnPageChangeListener(this);
    L17:
        this.pageChangeListener.reset();
        r3.addOnPageChangeListener(this.pageChangeListener);
        ViewPagerOnTabSelectedListener r04 = new ViewPagerOnTabSelectedListener(r3);
        this.currentVpSelectedListener = r04;
        addOnTabSelectedListener(r04);
        PagerAdapter r05 = r3.getAdapter();
        if (r05 == null) goto L21;
        setPagerAdapter(r05, r4);
    L21:
        if (this.adapterChangeListener != null) goto L23;
        this.adapterChangeListener = new AdapterChangeListener(this);
    L23:
        this.adapterChangeListener.setAutoRefresh(r4);
        r3.addOnAdapterChangeListener(this.adapterChangeListener);
        setScrollPosition(r3.getCurrentItem(), 0.0f, true);
    L25:
        this.setupViewPagerImplicitly = r5;
        return;
    L24:
        this.viewPager = null;
        setPagerAdapter(null, false);
        goto L25
    }

    private void updateAllTabs() {
        int r0 = this.tabs.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.tabs.get(r1).updateView();
        r1 = r1 + 1;
        goto L3
    }

    private void updateTabViewLayoutParams(LinearLayout.LayoutParams r3) {
        if (this.mode == 1) goto L5;
    L8:
        r3.width = -2;
        r3.weight = 0.0f;
        return;
    L5:
        if (this.tabGravity != 0) goto L8;
        r3.width = 0;
        r3.weight = 1.0f;
    }

    @Deprecated
    public void addOnTabSelectedListener(BaseOnTabSelectedListener r2) {
        if (this.selectedListeners.contains(r2) == true) goto L6;
        this.selectedListeners.add(r2);
        return;
    }

    public void addOnTabSelectedListener(OnTabSelectedListener r1) {
        addOnTabSelectedListener(r1);
    }

    public void addTab(Tab r2) {
        addTab(r2, this.tabs.isEmpty());
    }

    public void addTab(Tab r2, int r3) {
        addTab(r2, r3, this.tabs.isEmpty());
    }

    public void addTab(Tab r2, int r3, boolean r4) {
        if (r2.parent != this) goto L8;
        configureTab(r2, r3);
        addTabView(r2);
        if (r4 == false) goto L10;
        r2.select();
        return;
    L10:
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("Tab belongs to a different TabLayout.");
    }

    public void addTab(Tab r2, boolean r3) {
        addTab(r2, this.tabs.size(), r3);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View r1) {
        addViewInternal(r1);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View r1, int r2) {
        addViewInternal(r1);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View r1, int r2, ViewGroup.LayoutParams r3) {
        addViewInternal(r1);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View r1, ViewGroup.LayoutParams r2) {
        addViewInternal(r1);
    }

    public void clearOnTabSelectedListeners() {
        this.selectedListeners.clear();
    }

    public Tab createTabFromPool() {
        Tab r0 = tabPool.acquire();
        if (r0 == null) goto L5;
        return r0;
    L5:
        return new Tab();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateDefaultLayoutParams();
    }

    public int getSelectedTabPosition() {
        Tab r0 = this.selectedTab;
        if (r0 != null) goto L5;
        return -1;
    L5:
        return r0.getPosition();
    }

    public Tab getTabAt(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 < getTabCount()) goto L7;
        return null;
    L7:
        return this.tabs.get(r2);
    }

    public int getTabCount() {
        return this.tabs.size();
    }

    public int getTabGravity() {
        return this.tabGravity;
    }

    public ColorStateList getTabIconTint() {
        return this.tabIconTint;
    }

    public int getTabIndicatorAnimationMode() {
        return this.tabIndicatorAnimationMode;
    }

    public int getTabIndicatorGravity() {
        return this.tabIndicatorGravity;
    }

    public int getTabMaxWidth() {
        return this.tabMaxWidth;
    }

    public int getTabMode() {
        return this.mode;
    }

    public ColorStateList getTabRippleColor() {
        return this.tabRippleColorStateList;
    }

    public Drawable getTabSelectedIndicator() {
        return this.tabSelectedIndicator;
    }

    public ColorStateList getTabTextColors() {
        return this.tabTextColors;
    }

    public boolean hasUnboundedRipple() {
        return this.unboundedRipple;
    }

    public boolean isInlineLabel() {
        return this.inlineLabel;
    }

    public boolean isTabIndicatorFullWidth() {
        return this.tabIndicatorFullWidth;
    }

    public Tab newTab() {
        Tab r0 = createTabFromPool();
        r0.parent = this;
        r0.view = createTabView(r0);
        if (Tab.access$000(r0) == (-1)) goto L5;
        r0.view.setId(Tab.access$000(r0));
    L5:
        return r0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
        if (this.viewPager != null) goto L8;
        ViewParent r0 = getParent();
        if ((r0 instanceof ViewPager) == false) goto L9;
        setupWithViewPager((ViewPager) r0, true, true);
        return;
    L9:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.setupViewPagerImplicitly == false) goto L6;
        setupWithViewPager(null);
        this.setupViewPagerImplicitly = false;
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.slidingTabIndicator.getChildCount()) goto L9;
        View r1 = this.slidingTabIndicator.getChildAt(r0);
        if ((r1 instanceof TabView) == false) goto L8;
        TabView.access$500((TabView) r1, r4);
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        super.onDraw(r4);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        AccessibilityNodeInfoCompat.wrap(r3).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, getTabCount(), false, 1));
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r2) {
        if (isScrollingEnabled() == true) goto L5;
        return false;
    L5:
        if (super.onInterceptTouchEvent(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int r7, int r8) {
        int r0 = Math.round(ViewUtils.dpToPx(getContext(), getDefaultHeight()));
        int r1 = View.MeasureSpec.getMode(r8);
        if (r1 == Integer.MIN_VALUE) goto L8;
        if (r1 != 0) goto L12;
        r8 = View.MeasureSpec.makeMeasureSpec((r0 + getPaddingTop()) + getPaddingBottom(), TLObject.FLAG_30);
    L12:
        int r02 = View.MeasureSpec.getSize(r7);
        if (View.MeasureSpec.getMode(r7) == 0) goto L19;
        int r12 = this.requestedTabMaxWidth;
        if (r12 > 0) goto L18;
        r12 = (int) (r02 - ViewUtils.dpToPx(getContext(), 56));
    L18:
        this.tabMaxWidth = r12;
    L19:
        super.onMeasure(r7, r8);
        if (getChildCount() != 1) goto L35;
        View r72 = getChildAt(0);
        int r03 = this.mode;
        if (r03 == 0) goto L32;
        if (r03 == 1) goto L28;
        if (r03 == 2) goto L32;
        return;
    L28:
        if (r72.getMeasuredWidth() != getMeasuredWidth()) goto L33;
        return;
    L33:
        r72.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TLObject.FLAG_30), ViewGroup.getChildMeasureSpec(r8, getPaddingTop() + getPaddingBottom(), r72.getLayoutParams().height));
        return;
    L32:
        if (r72.getMeasuredWidth() < getMeasuredWidth()) goto L33;
        return;
    L35:
        return;
    L8:
        if (getChildCount() != 1) goto L12;
        if (View.MeasureSpec.getSize(r8) < r0) goto L12;
        getChildAt(0).setMinimumHeight(r0);
        goto L12
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r3) {
        if (r3.getActionMasked() != 8) goto L9;
        if (isScrollingEnabled() == true) goto L9;
        return false;
    L9:
        return super.onTouchEvent(r3);
    }

    public void populateFromPagerAdapter() {
        removeAllTabs();
        PagerAdapter r0 = this.pagerAdapter;
        if (r0 == null) goto L17;
        int r02 = r0.getCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L7;
        addTab(newTab().setText(this.pagerAdapter.getPageTitle(r2)), false);
        r2 = r2 + 1;
        goto L5
    L7:
        ViewPager r1 = this.viewPager;
        if (r1 == null) goto L18;
        if (r02 <= 0) goto L19;
        int r03 = r1.getCurrentItem();
        if (r03 != getSelectedTabPosition()) goto L13;
        return;
    L13:
        if (r03 >= getTabCount()) goto L21;
        selectTab(getTabAt(r03));
        return;
    L21:
        return;
    L19:
        return;
    L18:
        return;
    }

    public boolean releaseFromTabPool(Tab r1) {
        return tabPool.release(r1);
    }

    public void removeAllTabs() {
        int r0 = this.slidingTabIndicator.getChildCount() - 1;
    L3:
        if (r0 < 0) goto L5;
        removeTabViewAt(r0);
        r0 = r0 - 1;
        goto L3
    L5:
        Iterator<Tab> r02 = this.tabs.iterator();
    L7:
        if (r02.hasNext() == false) goto L9;
        Tab r1 = r02.next();
        r02.remove();
        r1.reset();
        releaseFromTabPool(r1);
        goto L7
    L9:
        this.selectedTab = null;
    }

    @Deprecated
    public void removeOnTabSelectedListener(BaseOnTabSelectedListener r1) {
        this.selectedListeners.remove(r1);
    }

    public void removeOnTabSelectedListener(OnTabSelectedListener r1) {
        removeOnTabSelectedListener(r1);
    }

    public void removeTab(Tab r2) {
        if (r2.parent != this) goto L6;
        removeTabAt(r2.getPosition());
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Tab does not belong to this TabLayout.");
    }

    public void removeTabAt(int r8) {
        Tab r0 = this.selectedTab;
        if (r0 == null) goto L5;
        int r02 = r0.getPosition();
    L6:
        removeTabViewAt(r8);
        Tab r2 = this.tabs.remove(r8);
        if (r2 == null) goto L9;
        r2.reset();
        releaseFromTabPool(r2);
    L9:
        int r22 = this.tabs.size();
        int r3 = -1;
        int r4 = r8;
    L10:
        if (r4 >= r22) goto L15;
        if (this.tabs.get(r4).getPosition() != this.indicatorPosition) goto L14;
        r3 = r4;
    L14:
        this.tabs.get(r4).setPosition(r4);
        r4 = r4 + 1;
        goto L10
    L15:
        this.indicatorPosition = r3;
        if (r02 == r8) goto L18;
        return;
    L18:
        if (this.tabs.isEmpty() == false) goto L20;
        Tab r82 = null;
    L21:
        selectTab(r82);
        return;
    L20:
        r82 = this.tabs.get(Math.max(0, r8 - 1));
        goto L21
    L5:
        r02 = 0;
        goto L6
    }

    public void selectTab(Tab r2) {
        selectTab(r2, true);
    }

    public void selectTab(Tab r5, boolean r6) {
        Tab r0 = this.selectedTab;
        if (r0 != r5) goto L8;
        if (r0 == null) goto L28;
        dispatchTabReselected(r5);
        animateToTab(r5.getPosition());
        return;
    L28:
        return;
    L8:
        if (r5 == null) goto L10;
        int r2 = r5.getPosition();
    L11:
        if (r6 == false) goto L20;
        if (r0 != null) goto L14;
    L15:
        if (r2 == (-1)) goto L17;
        setScrollPosition(r2, 0.0f, true);
    L18:
        if (r2 == (-1)) goto L20;
        setSelectedTabView(r2);
    L17:
        animateToTab(r2);
        goto L18
    L14:
        if (r0.getPosition() != (-1)) goto L17;
    L20:
        this.selectedTab = r5;
        if (r0 != null) goto L23;
    L25:
        if (r5 == null) goto L29;
        dispatchTabSelected(r5);
        return;
    L29:
        return;
    L23:
        if (r0.parent == null) goto L25;
        dispatchTabUnselected(r0);
        goto L25
    L10:
        r2 = -1;
        goto L11
    }

    @Override // android.view.View
    public void setElevation(float r1) {
        super.setElevation(r1);
        MaterialShapeUtils.setElevation(this, r1);
    }

    public void setInlineLabel(boolean r3) {
        if (this.inlineLabel == r3) goto L16;
        this.inlineLabel = r3;
        int r32 = 0;
    L6:
        if (r32 >= this.slidingTabIndicator.getChildCount()) goto L11;
        View r0 = this.slidingTabIndicator.getChildAt(r32);
        if ((r0 instanceof TabView) == false) goto L10;
        ((TabView) r0).updateOrientation();
    L10:
        r32 = r32 + 1;
        goto L6
    L11:
        applyModeAndGravity();
        return;
    }

    public void setInlineLabelResource(int r2) {
        setInlineLabel(getResources().getBoolean(r2));
    }

    @Deprecated
    public void setOnTabSelectedListener(BaseOnTabSelectedListener r2) {
        BaseOnTabSelectedListener r0 = this.selectedListener;
        if (r0 == null) goto L5;
        removeOnTabSelectedListener(r0);
    L5:
        this.selectedListener = r2;
        if (r2 == null) goto L9;
        addOnTabSelectedListener(r2);
        return;
    }

    @Deprecated
    public void setOnTabSelectedListener(OnTabSelectedListener r1) {
        setOnTabSelectedListener(r1);
    }

    public void setPagerAdapter(PagerAdapter r3, boolean r4) {
        PagerAdapter r0 = this.pagerAdapter;
        if (r0 == null) goto L7;
        DataSetObserver r1 = this.pagerAdapterObserver;
        if (r1 == null) goto L7;
        r0.unregisterDataSetObserver(r1);
    L7:
        this.pagerAdapter = r3;
        if (r4 == false) goto L14;
        if (r3 == null) goto L14;
        if (this.pagerAdapterObserver != null) goto L13;
        this.pagerAdapterObserver = new PagerAdapterObserver(this);
    L13:
        r3.registerDataSetObserver(this.pagerAdapterObserver);
    L14:
        populateFromPagerAdapter();
    }

    public void setScrollAnimatorListener(Animator.AnimatorListener r1) {
        ensureScrollAnimator();
        this.scrollAnimator.addListener(r1);
    }

    public void setScrollPosition(int r2, float r3, boolean r4) {
        setScrollPosition(r2, r3, r4, true);
    }

    public void setScrollPosition(int r7, float r8, boolean r9, boolean r10) {
        setScrollPosition(r7, r8, r9, r10, true);
    }

    public void setScrollPosition(int r6, float r7, boolean r8, boolean r9, boolean r10) {
        int r0 = Math.round(r6 + r7);
        if (r0 >= 0) goto L5;
        return;
    L5:
        if (r0 >= this.slidingTabIndicator.getChildCount()) goto L48;
        if (r9 == false) goto L9;
        this.slidingTabIndicator.setIndicatorPositionFromTabPosition(r6, r7);
    L9:
        ValueAnimator r92 = this.scrollAnimator;
        if (r92 != null) goto L12;
    L14:
        int r72 = calculateScrollXForTab(r6, r7);
        int r93 = getScrollX();
        if (r6 >= getSelectedTabPosition()) goto L18;
        if (r72 < r93) goto L18;
    L22:
        boolean r1 = true;
    L25:
        if (getLayoutDirection() == 1) goto L27;
    L36:
        if (r1 == false) goto L38;
    L40:
        if (r6 >= 0) goto L42;
        r72 = 0;
    L42:
        scrollTo(r72, 0);
    L43:
        if (r8 == false) goto L47;
        setSelectedTabView(r0);
        return;
    L47:
        return;
    L38:
        if (this.viewPagerScrollState == 1) goto L40;
        if (r10 == false) goto L43;
    L27:
        if (r6 >= getSelectedTabPosition()) goto L30;
        if (r72 > r93) goto L30;
    L34:
        r1 = true;
    L30:
        if (r6 <= getSelectedTabPosition()) goto L33;
        if (r72 >= r93) goto L34;
    L33:
        if (r6 == getSelectedTabPosition()) goto L34;
        r1 = false;
    L18:
        if (r6 <= getSelectedTabPosition()) goto L21;
        if (r72 <= r93) goto L22;
    L21:
        if (r6 == getSelectedTabPosition()) goto L22;
        r1 = false;
        goto L25
    L12:
        if (r92.isRunning() == false) goto L14;
        this.scrollAnimator.cancel();
        goto L14
    }

    public void setSelectedTabIndicator(int r2) {
        if (r2 == 0) goto L5;
        setSelectedTabIndicator(AppCompatResources.getDrawable(getContext(), r2));
        return;
    L5:
        setSelectedTabIndicator(null);
    }

    public void setSelectedTabIndicator(Drawable r2) {
        if (r2 != null) goto L4;
        r2 = new GradientDrawable();
    L4:
        Drawable r22 = DrawableCompat.wrap(r2).mutate();
        this.tabSelectedIndicator = r22;
        DrawableUtils.setTint(r22, this.tabSelectedIndicatorColor);
        int r23 = this.tabIndicatorHeight;
        if (r23 != (-1)) goto L7;
        r23 = this.tabSelectedIndicator.getIntrinsicHeight();
    L7:
        this.slidingTabIndicator.setSelectedIndicatorHeight(r23);
    }

    public void setSelectedTabIndicatorColor(int r2) {
        this.tabSelectedIndicatorColor = r2;
        DrawableUtils.setTint(this.tabSelectedIndicator, r2);
        updateTabViews(false);
    }

    public void setSelectedTabIndicatorGravity(int r2) {
        if (this.tabIndicatorGravity == r2) goto L6;
        this.tabIndicatorGravity = r2;
        this.slidingTabIndicator.postInvalidateOnAnimation();
        return;
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int r1) {
        this.tabIndicatorHeight = r1;
        this.slidingTabIndicator.setSelectedIndicatorHeight(r1);
    }

    public void setTabGravity(int r2) {
        if (this.tabGravity == r2) goto L6;
        this.tabGravity = r2;
        applyModeAndGravity();
        return;
    }

    public void setTabIconTint(ColorStateList r2) {
        if (this.tabIconTint == r2) goto L6;
        this.tabIconTint = r2;
        updateAllTabs();
        return;
    }

    public void setTabIconTintResource(int r2) {
        setTabIconTint(AppCompatResources.getColorStateList(getContext(), r2));
    }

    public void setTabIndicatorAnimationMode(int r2) {
        this.tabIndicatorAnimationMode = r2;
        if (r2 != 0) goto L5;
        this.tabIndicatorInterpolator = new TabIndicatorInterpolator();
        return;
    L5:
        if (r2 != 1) goto L7;
        this.tabIndicatorInterpolator = new ElasticTabIndicatorInterpolator();
        return;
    L7:
        if (r2 != 2) goto L11;
        this.tabIndicatorInterpolator = new FadeTabIndicatorInterpolator();
        return;
    L11:
        throw new IllegalArgumentException(r2 + " is not a valid TabIndicatorAnimationMode");
    }

    public void setTabIndicatorFullWidth(boolean r1) {
        this.tabIndicatorFullWidth = r1;
        SlidingTabIndicator.access$100(this.slidingTabIndicator);
        this.slidingTabIndicator.postInvalidateOnAnimation();
    }

    public void setTabMode(int r2) {
        if (r2 == this.mode) goto L6;
        this.mode = r2;
        applyModeAndGravity();
        return;
    }

    public void setTabRippleColor(ColorStateList r3) {
        if (this.tabRippleColorStateList == r3) goto L11;
        this.tabRippleColorStateList = r3;
        int r32 = 0;
    L6:
        if (r32 >= this.slidingTabIndicator.getChildCount()) goto L15;
        View r0 = this.slidingTabIndicator.getChildAt(r32);
        if ((r0 instanceof TabView) == false) goto L10;
        TabView.access$200((TabView) r0, getContext());
    L10:
        r32 = r32 + 1;
        goto L6
    L15:
        return;
    }

    public void setTabRippleColorResource(int r2) {
        setTabRippleColor(AppCompatResources.getColorStateList(getContext(), r2));
    }

    public void setTabTextColors(int r1, int r2) {
        setTabTextColors(createColorStateList(r1, r2));
    }

    public void setTabTextColors(ColorStateList r2) {
        if (this.tabTextColors == r2) goto L6;
        this.tabTextColors = r2;
        updateAllTabs();
        return;
    }

    @Deprecated
    public void setTabsFromPagerAdapter(PagerAdapter r2) {
        setPagerAdapter(r2, false);
    }

    public void setUnboundedRipple(boolean r3) {
        if (this.unboundedRipple == r3) goto L11;
        this.unboundedRipple = r3;
        int r32 = 0;
    L6:
        if (r32 >= this.slidingTabIndicator.getChildCount()) goto L15;
        View r0 = this.slidingTabIndicator.getChildAt(r32);
        if ((r0 instanceof TabView) == false) goto L10;
        TabView.access$200((TabView) r0, getContext());
    L10:
        r32 = r32 + 1;
        goto L6
    L15:
        return;
    }

    public void setUnboundedRippleResource(int r2) {
        setUnboundedRipple(getResources().getBoolean(r2));
    }

    public void setupWithViewPager(ViewPager r2) {
        setupWithViewPager(r2, true);
    }

    public void setupWithViewPager(ViewPager r2, boolean r3) {
        setupWithViewPager(r2, r3, false);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        if (getTabScrollRange() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void updateTabViews(boolean r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.slidingTabIndicator.getChildCount()) goto L9;
        View r1 = this.slidingTabIndicator.getChildAt(r0);
        r1.setMinimumWidth(getTabMinWidth());
        updateTabViewLayoutParams((LinearLayout.LayoutParams) r1.getLayoutParams());
        if (r4 == false) goto L8;
        r1.requestLayout();
    L8:
        r0 = r0 + 1;
        goto L4
    }

    public void updateViewPagerScrollState(int r1) {
        this.viewPagerScrollState = r1;
    }
}
