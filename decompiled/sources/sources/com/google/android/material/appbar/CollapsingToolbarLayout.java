package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.appcompat.R$style;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public class CollapsingToolbarLayout extends FrameLayout {
    private static final int COLLAPSED_TITLE_GRAVITY_AVAILABLE_SPACE = 1;
    private static final int COLLAPSED_TITLE_GRAVITY_ENTIRE_SPACE = 0;
    private static final int DEFAULT_SCRIM_ANIMATION_DURATION = 600;
    private static final int DEF_STYLE_RES = 0;
    public static final int TITLE_COLLAPSE_MODE_FADE = 1;
    public static final int TITLE_COLLAPSE_MODE_SCALE = 0;
    private final int collapsedTitleGravityMode;
    final CollapsingTextHelper collapsingSubtitleHelper;
    private boolean collapsingTitleEnabled;
    final CollapsingTextHelper collapsingTitleHelper;
    private Drawable contentScrim;
    int currentOffset;
    private boolean drawCollapsingTitle;
    private View dummyView;
    final ElevationOverlayProvider elevationOverlayProvider;
    private int expandedMarginBottom;
    private int expandedMarginEnd;
    private int expandedMarginStart;
    private int expandedMarginTop;
    private int expandedTitleSpacing;
    private int extraHeightForTitles;
    private boolean extraMultilineHeightEnabled;
    private int extraMultilineSubtitleHeight;
    private int extraMultilineTitleHeight;
    private boolean forceApplySystemWindowInsetTop;
    WindowInsetsCompat lastInsets;
    private AppBarLayout.OnOffsetChangedListener onOffsetChangedListener;
    private boolean refreshToolbar;
    private int screenOrientation;
    private int scrimAlpha;
    private long scrimAnimationDuration;
    private final TimeInterpolator scrimAnimationFadeInInterpolator;
    private final TimeInterpolator scrimAnimationFadeOutInterpolator;
    private ValueAnimator scrimAnimator;
    private int scrimVisibleHeightTrigger;
    private boolean scrimsAreShown;
    Drawable statusBarScrim;
    private int titleCollapseMode;
    private final Rect tmpRect;
    private ViewGroup toolbar;
    private View toolbarDirectChild;
    private int toolbarId;
    private int topInsetApplied;

    /* renamed from: com.google.android.material.appbar.CollapsingToolbarLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements OnApplyWindowInsetsListener {
        final /* synthetic */ CollapsingToolbarLayout this$0;

        public AnonymousClass1(CollapsingToolbarLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r1, WindowInsetsCompat r2) {
            return this.this$0.onWindowInsetChanged(r2);
        }
    }

    /* renamed from: com.google.android.material.appbar.CollapsingToolbarLayout$2, reason: invalid class name */
    public class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ CollapsingToolbarLayout this$0;

        public AnonymousClass2(CollapsingToolbarLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r1) {
            this.this$0.setScrimAlpha(((Integer) r1.getAnimatedValue()).intValue());
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CollapsedTitleGravityMode {
    }

    public static class LayoutParams extends FrameLayout.LayoutParams {
        public static final int COLLAPSE_MODE_OFF = 0;
        public static final int COLLAPSE_MODE_PARALLAX = 2;
        public static final int COLLAPSE_MODE_PIN = 1;
        private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5f;
        int collapseMode;
        float parallaxMult;

        public LayoutParams(int r1, int r2) {
            super(r1, r2);
            this.collapseMode = 0;
            this.parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public LayoutParams(int r1, int r2, int r3) {
            super(r1, r2, r3);
            this.collapseMode = 0;
            this.parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public LayoutParams(Context r4, AttributeSet r5) {
            super(r4, r5);
            this.collapseMode = 0;
            this.parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
            TypedArray r42 = r4.obtainStyledAttributes(r5, R.styleable.CollapsingToolbarLayout_Layout);
            this.collapseMode = r42.getInt(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            setParallaxMultiplier(r42.getFloat(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, DEFAULT_PARALLAX_MULTIPLIER));
            r42.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams r1) {
            super(r1);
            this.collapseMode = 0;
            this.parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams r1) {
            super(r1);
            this.collapseMode = 0;
            this.parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public LayoutParams(FrameLayout.LayoutParams r1) {
            super(r1);
            this.collapseMode = 0;
            this.parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public LayoutParams(LayoutParams r2) {
            super(r2);
            this.collapseMode = 0;
            this.parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
            this.collapseMode = r2.collapseMode;
            this.parallaxMult = r2.parallaxMult;
        }

        public int getCollapseMode() {
            return this.collapseMode;
        }

        public float getParallaxMultiplier() {
            return this.parallaxMult;
        }

        public void setCollapseMode(int r1) {
            this.collapseMode = r1;
        }

        public void setParallaxMultiplier(float r1) {
            this.parallaxMult = r1;
        }
    }

    public class OffsetUpdateListener implements AppBarLayout.OnOffsetChangedListener {
        final /* synthetic */ CollapsingToolbarLayout this$0;

        public OffsetUpdateListener(CollapsingToolbarLayout r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        public void onOffsetChanged(AppBarLayout r9, int r10) {
            CollapsingToolbarLayout r92 = this.this$0;
            r92.currentOffset = r10;
            WindowInsetsCompat r93 = r92.lastInsets;
            if (r93 == null) goto L5;
            int r94 = r93.getSystemWindowInsetTop();
        L6:
            int r1 = this.this$0.getChildCount();
            int r2 = 0;
        L7:
            CollapsingToolbarLayout r3 = this.this$0;
            if (r2 >= r1) goto L17;
            View r32 = r3.getChildAt(r2);
            LayoutParams r4 = (LayoutParams) r32.getLayoutParams();
            ViewOffsetHelper r5 = CollapsingToolbarLayout.getViewOffsetHelper(r32);
            int r6 = r4.collapseMode;
            if (r6 != 1) goto L12;
            r5.setTopAndBottomOffset(MathUtils.clamp(-r10, 0, this.this$0.getMaxOffsetForPinChild(r32)));
        L16:
            r2 = r2 + 1;
            goto L7
        L12:
            if (r6 != 2) goto L16;
            r5.setTopAndBottomOffset(Math.round((-r10) * r4.parallaxMult));
            goto L16
        L17:
            r3.updateScrimVisibility();
            CollapsingToolbarLayout r0 = this.this$0;
            if (r0.statusBarScrim == null) goto L21;
            if (r94 <= 0) goto L21;
            r0.postInvalidateOnAnimation();
        L21:
            int r02 = this.this$0.getHeight();
            int r12 = (r02 - this.this$0.getMinimumHeight()) - r94;
            int r03 = r02 - this.this$0.getScrimVisibleHeightTrigger();
            int r95 = this.this$0.currentOffset + r12;
            float r13 = r12;
            float r102 = Math.abs(r10) / r13;
            float r04 = r03 / r13;
            this.this$0.collapsingTitleHelper.setFadeModeStartFraction(Math.min(1.0f, r04));
            this.this$0.collapsingTitleHelper.setCurrentOffsetY(r95);
            this.this$0.collapsingTitleHelper.setExpansionFraction(r102);
            this.this$0.collapsingSubtitleHelper.setFadeModeStartFraction(Math.min(1.0f, r04));
            this.this$0.collapsingSubtitleHelper.setCurrentOffsetY(r95);
            this.this$0.collapsingSubtitleHelper.setExpansionFraction(r102);
            return;
        L5:
            r94 = 0;
            goto L6
        }
    }

    public interface StaticLayoutBuilderConfigurer extends com.google.android.material.internal.StaticLayoutBuilderConfigurer {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TitleCollapseMode {
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Design_CollapsingToolbar;
    }

    public CollapsingToolbarLayout(Context r2) {
        this(r2, null);
    }

    public CollapsingToolbarLayout(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.collapsingToolbarLayoutStyle);
    }

    public CollapsingToolbarLayout(Context r11, AttributeSet r12, int r13) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r11, r12, r13, r4), r12, r13);
        this.refreshToolbar = true;
        this.tmpRect = new Rect();
        this.scrimVisibleHeightTrigger = -1;
        this.topInsetApplied = 0;
        this.extraMultilineTitleHeight = 0;
        this.extraMultilineSubtitleHeight = 0;
        this.extraHeightForTitles = 0;
        Context r0 = getContext();
        this.screenOrientation = getResources().getConfiguration().orientation;
        CollapsingTextHelper r8 = new CollapsingTextHelper(this);
        this.collapsingTitleHelper = r8;
        TimeInterpolator r9 = AnimationUtils.DECELERATE_INTERPOLATOR;
        r8.setTextSizeInterpolator(r9);
        r8.setRtlTextDirectionHeuristicsEnabled(false);
        this.elevationOverlayProvider = new ElevationOverlayProvider(r0);
        TypedArray r122 = ThemeEnforcement.obtainStyledAttributes(r0, r12, R.styleable.CollapsingToolbarLayout, r13, r4, new int[0]);
        int r132 = r122.getInt(R.styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691);
        int r1 = r122.getInt(R.styleable.CollapsingToolbarLayout_collapsedTitleGravity, NavigationBarView.ITEM_GRAVITY_START_CENTER);
        this.collapsedTitleGravityMode = r122.getInt(R.styleable.CollapsingToolbarLayout_collapsedTitleGravityMode, 1);
        r8.setExpandedTextGravity(r132);
        r8.setCollapsedTextGravity(r1);
        int r2 = r122.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.expandedMarginBottom = r2;
        this.expandedMarginEnd = r2;
        this.expandedMarginTop = r2;
        this.expandedMarginStart = r2;
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart) == false) goto L6;
        this.expandedMarginStart = r122.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
    L6:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd) == false) goto L9;
        this.expandedMarginEnd = r122.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
    L9:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop) == false) goto L12;
        this.expandedMarginTop = r122.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
    L12:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom) == false) goto L15;
        this.expandedMarginBottom = r122.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
    L15:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleSpacing) == false) goto L17;
        this.expandedTitleSpacing = r122.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleSpacing, 0);
    L17:
        this.collapsingTitleEnabled = r122.getBoolean(R.styleable.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(r122.getText(R.styleable.CollapsingToolbarLayout_title));
        r8.setExpandedTextAppearance(R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
        r8.setCollapsedTextAppearance(R$style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance) == false) goto L21;
        r8.setExpandedTextAppearance(r122.getResourceId(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
    L21:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance) == false) goto L24;
        r8.setCollapsedTextAppearance(r122.getResourceId(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
    L24:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_titleTextEllipsize) == false) goto L27;
        setTitleEllipsize(convertEllipsizeToTruncateAt(r122.getInt(R.styleable.CollapsingToolbarLayout_titleTextEllipsize, -1)));
    L27:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleTextColor) == false) goto L30;
        r8.setExpandedTextColor(MaterialResources.getColorStateList(r0, r122, R.styleable.CollapsingToolbarLayout_expandedTitleTextColor));
    L30:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_collapsedTitleTextColor) == false) goto L32;
        r8.setCollapsedTextColor(MaterialResources.getColorStateList(r0, r122, R.styleable.CollapsingToolbarLayout_collapsedTitleTextColor));
    L32:
        this.scrimVisibleHeightTrigger = r122.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_titleMaxLines) == false) goto L36;
        r8.setExpandedMaxLines(r122.getInt(R.styleable.CollapsingToolbarLayout_titleMaxLines, 1));
    L39:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_titlePositionInterpolator) == false) goto L41;
        r8.setPositionInterpolator(android.view.animation.AnimationUtils.loadInterpolator(r0, r122.getResourceId(R.styleable.CollapsingToolbarLayout_titlePositionInterpolator, 0)));
    L41:
        CollapsingTextHelper r22 = new CollapsingTextHelper(this);
        this.collapsingSubtitleHelper = r22;
        r22.setTextSizeInterpolator(r9);
        r22.setRtlTextDirectionHeuristicsEnabled(false);
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_subtitle) == false) goto L44;
        setSubtitle(r122.getText(R.styleable.CollapsingToolbarLayout_subtitle));
    L44:
        r22.setExpandedTextGravity(r132);
        r22.setCollapsedTextGravity(r1);
        r22.setExpandedTextAppearance(R$style.TextAppearance_AppCompat_Headline);
        r22.setCollapsedTextAppearance(R$style.TextAppearance_AppCompat_Widget_ActionBar_Subtitle);
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_expandedSubtitleTextAppearance) == false) goto L48;
        r22.setExpandedTextAppearance(r122.getResourceId(R.styleable.CollapsingToolbarLayout_expandedSubtitleTextAppearance, 0));
    L48:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_collapsedSubtitleTextAppearance) == false) goto L51;
        r22.setCollapsedTextAppearance(r122.getResourceId(R.styleable.CollapsingToolbarLayout_collapsedSubtitleTextAppearance, 0));
    L51:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_expandedSubtitleTextColor) == false) goto L54;
        r22.setExpandedTextColor(MaterialResources.getColorStateList(r0, r122, R.styleable.CollapsingToolbarLayout_expandedSubtitleTextColor));
    L54:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_collapsedSubtitleTextColor) == false) goto L57;
        r22.setCollapsedTextColor(MaterialResources.getColorStateList(r0, r122, R.styleable.CollapsingToolbarLayout_collapsedSubtitleTextColor));
    L57:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_subtitleMaxLines) == false) goto L60;
        r22.setExpandedMaxLines(r122.getInt(R.styleable.CollapsingToolbarLayout_subtitleMaxLines, 1));
    L60:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_titlePositionInterpolator) == false) goto L62;
        r22.setPositionInterpolator(android.view.animation.AnimationUtils.loadInterpolator(r0, r122.getResourceId(R.styleable.CollapsingToolbarLayout_titlePositionInterpolator, 0)));
    L62:
        this.scrimAnimationDuration = r122.getInt(R.styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
        this.scrimAnimationFadeInInterpolator = MotionUtils.resolveThemeInterpolator(r0, R.attr.motionEasingStandardInterpolator, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        this.scrimAnimationFadeOutInterpolator = MotionUtils.resolveThemeInterpolator(r0, R.attr.motionEasingStandardInterpolator, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        setContentScrim(r122.getDrawable(R.styleable.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(r122.getDrawable(R.styleable.CollapsingToolbarLayout_statusBarScrim));
        setTitleCollapseMode(r122.getInt(R.styleable.CollapsingToolbarLayout_titleCollapseMode, 0));
        this.toolbarId = r122.getResourceId(R.styleable.CollapsingToolbarLayout_toolbarId, -1);
        this.forceApplySystemWindowInsetTop = r122.getBoolean(R.styleable.CollapsingToolbarLayout_forceApplySystemWindowInsetTop, false);
        this.extraMultilineHeightEnabled = r122.getBoolean(R.styleable.CollapsingToolbarLayout_extraMultilineHeightEnabled, false);
        r122.recycle();
        setWillNotDraw(false);
        ViewCompat.setOnApplyWindowInsetsListener(this, new AnonymousClass1(this));
        return;
    L36:
        if (r122.hasValue(R.styleable.CollapsingToolbarLayout_maxLines) == false) goto L39;
        r8.setExpandedMaxLines(r122.getInt(R.styleable.CollapsingToolbarLayout_maxLines, 1));
        goto L39
    }

    private void animateScrim(int r4) {
        ensureToolbar();
        ValueAnimator r0 = this.scrimAnimator;
        if (r0 != null) goto L10;
        ValueAnimator r02 = new ValueAnimator();
        this.scrimAnimator = r02;
        if (r4 <= this.scrimAlpha) goto L7;
        TimeInterpolator r1 = this.scrimAnimationFadeInInterpolator;
    L8:
        r02.setInterpolator(r1);
        this.scrimAnimator.addUpdateListener(new AnonymousClass2(this));
    L12:
        this.scrimAnimator.setDuration(this.scrimAnimationDuration);
        this.scrimAnimator.setIntValues(new int[]{this.scrimAlpha, r4});
        this.scrimAnimator.start();
        return;
    L7:
        r1 = this.scrimAnimationFadeOutInterpolator;
        goto L8
    L10:
        if (r0.isRunning() == false) goto L12;
        this.scrimAnimator.cancel();
        goto L12
    }

    private TextUtils.TruncateAt convertEllipsizeToTruncateAt(int r1) {
        if (r1 == 0) goto L14;
        if (r1 == 1) goto L12;
        if (r1 == 3) goto L10;
        return TextUtils.TruncateAt.END;
    L10:
        return TextUtils.TruncateAt.MARQUEE;
    L12:
        return TextUtils.TruncateAt.MIDDLE;
    L14:
        return TextUtils.TruncateAt.START;
    }

    private void disableLiftOnScrollIfNeeded(AppBarLayout r1) {
        if (isTitleCollapseFadeMode() == false) goto L6;
        r1.setLiftOnScroll(false);
        return;
    }

    private void ensureToolbar() {
        if (this.refreshToolbar == true) goto L5;
        return;
    L5:
        ViewGroup r0 = null;
        this.toolbar = null;
        this.toolbarDirectChild = null;
        int r1 = this.toolbarId;
        if (r1 == (-1)) goto L11;
        ViewGroup r12 = (ViewGroup) findViewById(r1);
        this.toolbar = r12;
        if (r12 == null) goto L11;
        this.toolbarDirectChild = findDirectChild(r12);
    L11:
        if (this.toolbar != null) goto L19;
        int r13 = getChildCount();
        int r3 = 0;
    L13:
        if (r3 >= r13) goto L18;
        View r4 = getChildAt(r3);
        if (isToolbar(r4) == true) goto L16;
        r3 = r3 + 1;
        goto L13
    L16:
        r0 = (ViewGroup) r4;
    L18:
        this.toolbar = r0;
    L19:
        updateDummyView();
        this.refreshToolbar = false;
    }

    private View findDirectChild(View r3) {
        ViewParent r0 = r3.getParent();
    L3:
        if (r0 == this) goto L9;
        if (r0 == null) goto L9;
        if ((r0 instanceof View) == false) goto L8;
        r3 = r0;
    L8:
        r0 = r0.getParent();
    L9:
        return r3;
    }

    private int getDefaultContentScrimColorForTitleCollapseFadeMode() {
        ColorStateList r0 = MaterialColors.getColorStateListOrNull(getContext(), R.attr.colorSurfaceContainer);
        if (r0 != null) goto L5;
        float r02 = getResources().getDimension(R.dimen.design_appbar_elevation);
        return this.elevationOverlayProvider.compositeOverlayWithThemeSurfaceColorIfNeeded(r02);
    L5:
        return r0.getDefaultColor();
    }

    private static int getHeightWithMargins(View r2) {
        ViewGroup.LayoutParams r0 = r2.getLayoutParams();
        if ((r0 instanceof ViewGroup.MarginLayoutParams) == false) goto L7;
        ViewGroup.MarginLayoutParams r02 = (ViewGroup.MarginLayoutParams) r0;
        return (r2.getMeasuredHeight() + r02.topMargin) + r02.bottomMargin;
    L7:
        return r2.getMeasuredHeight();
    }

    private static CharSequence getToolbarSubtitle(View r1) {
        if ((r1 instanceof Toolbar) == false) goto L7;
        return ((Toolbar) r1).getSubtitle();
    L7:
        if ((r1 instanceof android.widget.Toolbar) == true) goto L9;
        return null;
    L9:
        return ((android.widget.Toolbar) r1).getSubtitle();
    }

    private static CharSequence getToolbarTitle(View r1) {
        if ((r1 instanceof Toolbar) == false) goto L7;
        return ((Toolbar) r1).getTitle();
    L7:
        if ((r1 instanceof android.widget.Toolbar) == true) goto L9;
        return null;
    L9:
        return ((android.widget.Toolbar) r1).getTitle();
    }

    public static ViewOffsetHelper getViewOffsetHelper(View r2) {
        ViewOffsetHelper r0 = (ViewOffsetHelper) r2.getTag(R.id.view_offset_helper);
        if (r0 != null) goto L6;
        ViewOffsetHelper r02 = new ViewOffsetHelper(r2);
        r2.setTag(R.id.view_offset_helper, r02);
        return r02;
    L6:
        return r0;
    }

    private boolean isTitleCollapseFadeMode() {
        if (this.titleCollapseMode != 1) goto L5;
        return true;
    L5:
        return false;
    }

    private static boolean isToolbar(View r1) {
        if ((r1 instanceof Toolbar) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof android.widget.Toolbar) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isToolbarChild(View r4) {
        View r0 = this.toolbarDirectChild;
        if (r0 == null) goto L10;
        if (r0 == this) goto L10;
        if (r4 != r0) goto L8;
        return true;
    L8:
        return false;
    L10:
        if (r4 != this.toolbar) goto L12;
        return true;
    L12:
        return false;
    }

    private void maybeSetPendingActionCollapsed() {
        ViewParent r1 = getParent();
        if ((r1 instanceof AppBarLayout) == false) goto L8;
        AppBarLayout r12 = (AppBarLayout) r1;
        if (r12.getPendingAction() != 0) goto L9;
        r12.setPendingAction(2);
        return;
    L9:
        return;
    }

    private void updateCollapsedBounds(boolean r11) {
        View r0 = this.toolbarDirectChild;
        if (r0 != null) goto L6;
        r0 = this.toolbar;
    L6:
        int r02 = getMaxOffsetForPinChild(r0);
        DescendantOffsetUtils.getDescendantRect(this, this.dummyView, this.tmpRect);
        ViewGroup r1 = this.toolbar;
        if ((r1 instanceof Toolbar) == false) goto L10;
        Toolbar r12 = (Toolbar) r1;
        int r2 = r12.getTitleMarginStart();
        int r3 = r12.getTitleMarginEnd();
        int r4 = r12.getTitleMarginTop();
        int r13 = r12.getTitleMarginBottom();
    L13:
        Rect r5 = this.tmpRect;
        int r6 = r5.left;
        if (r11 == false) goto L16;
        int r7 = r3;
    L17:
        int r62 = r6 + r7;
        int r72 = r5.right;
        if (r11 == false) goto L20;
        int r8 = r2;
    L21:
        int r73 = r72 - r8;
        int r82 = (r5.top + r02) + r4;
        int r42 = (r5.bottom + r02) - r13;
        int r03 = (int) (r42 - this.collapsingSubtitleHelper.getCollapsedFullSingleLineHeight());
        int r14 = (int) (r82 + this.collapsingTitleHelper.getCollapsedFullSingleLineHeight());
        boolean r52 = TextUtils.isEmpty(this.collapsingSubtitleHelper.getText());
        CollapsingTextHelper r9 = this.collapsingTitleHelper;
        if (r52 == false) goto L24;
        r9.setCollapsedBounds(r62, r82, r73, r42);
    L26:
        if (this.collapsedTitleGravityMode != 0) goto L41;
        DescendantOffsetUtils.getDescendantRect(this, this, this.tmpRect);
        Rect r53 = this.tmpRect;
        int r63 = r53.left;
        if (r11 == false) goto L30;
        int r74 = r3;
    L31:
        int r64 = r63 + r74;
        int r54 = r53.right;
        if (r11 == true) goto L35;
        r2 = r3;
    L35:
        int r55 = r54 - r2;
        boolean r112 = TextUtils.isEmpty(this.collapsingSubtitleHelper.getText());
        CollapsingTextHelper r22 = this.collapsingTitleHelper;
        if (r112 == false) goto L39;
        r22.setCollapsedBoundsForOffsets(r64, r82, r55, r42);
        return;
    L39:
        r22.setCollapsedBoundsForOffsets(r64, r82, r55, r03);
        this.collapsingSubtitleHelper.setCollapsedBoundsForOffsets(r64, r14, r55, r42);
        return;
    L30:
        r74 = r2;
        goto L31
    L41:
        return;
    L24:
        r9.setCollapsedBounds(r62, r82, r73, r03);
        this.collapsingSubtitleHelper.setCollapsedBounds(r62, r14, r73, r42);
        goto L26
    L20:
        r8 = r3;
        goto L21
    L16:
        r7 = r2;
        goto L17
    L10:
        if ((r1 instanceof android.widget.Toolbar) == false) goto L12;
        android.widget.Toolbar r15 = (android.widget.Toolbar) r1;
        r2 = r15.getTitleMarginStart();
        r3 = r15.getTitleMarginEnd();
        r4 = r15.getTitleMarginTop();
        r13 = r15.getTitleMarginBottom();
        goto L13
    L12:
        r2 = 0;
        r13 = 0;
        r3 = 0;
        r4 = 0;
        goto L13
    }

    private void updateContentDescriptionFromTitle() {
        setContentDescription(getTitle());
    }

    private void updateContentScrimBounds(Drawable r2, int r3, int r4) {
        updateContentScrimBounds(r2, this.toolbar, r3, r4);
    }

    private void updateContentScrimBounds(Drawable r2, View r3, int r4, int r5) {
        if (isTitleCollapseFadeMode() == false) goto L8;
        if (r3 == null) goto L8;
        if (this.collapsingTitleEnabled == false) goto L8;
        r5 = r3.getBottom();
    L8:
        r2.setBounds(0, 0, r4, r5);
    }

    private void updateDummyView() {
        if (this.collapsingTitleEnabled == true) goto L10;
        View r0 = this.dummyView;
        if (r0 == null) goto L10;
        ViewParent r02 = r0.getParent();
        if ((r02 instanceof ViewGroup) == false) goto L10;
        ((ViewGroup) r02).removeView(this.dummyView);
    L10:
        if (this.collapsingTitleEnabled == true) goto L12;
        return;
    L12:
        if (this.toolbar != null) goto L14;
        return;
    L14:
        if (this.dummyView != null) goto L17;
        this.dummyView = new View(getContext());
    L17:
        if (this.dummyView.getParent() != null) goto L22;
        this.toolbar.addView(this.dummyView, -1, -1);
        return;
    }

    private void updateTextBounds(int r9, int r10, int r11, int r12, boolean r13) {
        if (this.collapsingTitleEnabled == false) goto L33;
        View r0 = this.dummyView;
        if (r0 == null) goto L34;
        boolean r1 = false;
        if (r0.isAttachedToWindow() == true) goto L9;
    L11:
        boolean r02 = false;
    L12:
        this.drawCollapsingTitle = r02;
        if (r02 == true) goto L16;
        if (r13 == true) goto L16;
        return;
    L16:
        if (getLayoutDirection() != 1) goto L18;
        r1 = true;
    L18:
        updateCollapsedBounds(r1);
        if (r1 == false) goto L22;
        int r03 = this.expandedMarginEnd;
    L21:
        int r3 = r03;
        int r4 = this.tmpRect.top + this.expandedMarginTop;
        int r112 = r11 - r9;
        if (r1 == false) goto L26;
        int r92 = this.expandedMarginStart;
    L27:
        int r5 = r112 - r92;
        int r122 = (r12 - r10) - this.expandedMarginBottom;
        boolean r93 = TextUtils.isEmpty(this.collapsingSubtitleHelper.getText());
        CollapsingTextHelper r2 = this.collapsingTitleHelper;
        if (r93 == false) goto L31;
        r2.setExpandedBounds(r3, r4, r5, r122);
        this.collapsingTitleHelper.recalculate(r13);
        return;
    L31:
        r2.setExpandedBounds(r3, r4, r5, (int) ((r122 - (this.collapsingSubtitleHelper.getExpandedTextFullSingleLineHeight() + this.extraMultilineSubtitleHeight)) - this.expandedTitleSpacing), false);
        this.collapsingSubtitleHelper.setExpandedBounds(r3, (int) ((r4 + (this.collapsingTitleHelper.getExpandedTextFullSingleLineHeight() + this.extraMultilineTitleHeight)) + this.expandedTitleSpacing), r5, r122, false);
        this.collapsingTitleHelper.recalculate(r13);
        this.collapsingSubtitleHelper.recalculate(r13);
        return;
    L26:
        r92 = this.expandedMarginEnd;
        goto L27
    L22:
        r03 = this.expandedMarginStart;
        goto L21
    L9:
        if (this.dummyView.getVisibility() != 0) goto L11;
        r02 = true;
        goto L12
    L34:
        return;
    }

    private void updateTitleFromToolbarIfNeeded() {
        ViewGroup r0 = this.toolbar;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.collapsingTitleEnabled == false) goto L18;
        CharSequence r02 = getToolbarTitle(r0);
        if (TextUtils.isEmpty(this.collapsingTitleHelper.getText()) == true) goto L9;
    L11:
        CharSequence r03 = getToolbarSubtitle(this.toolbar);
        if (TextUtils.isEmpty(this.collapsingSubtitleHelper.getText()) == true) goto L14;
        return;
    L14:
        if (TextUtils.isEmpty(r03) == true) goto L20;
        setSubtitle(r03);
        return;
    L20:
        return;
    L9:
        if (TextUtils.isEmpty(r02) == true) goto L11;
        setTitle(r02);
        goto L11
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams r1) {
        return r1 instanceof LayoutParams;
    }

    @Override // android.view.View
    public void draw(Canvas r7) {
        super.draw(r7);
        ensureToolbar();
        if (this.toolbar != null) goto L10;
        Drawable r0 = this.contentScrim;
        if (r0 == null) goto L10;
        if (this.scrimAlpha <= 0) goto L10;
        r0.mutate().setAlpha(this.scrimAlpha);
        this.contentScrim.draw(r7);
    L10:
        if (this.collapsingTitleEnabled == false) goto L26;
        if (this.drawCollapsingTitle == false) goto L26;
        if (this.toolbar != null) goto L16;
    L24:
        this.collapsingTitleHelper.draw(r7);
        this.collapsingSubtitleHelper.draw(r7);
        goto L26
    L16:
        if (this.contentScrim == null) goto L24;
        if (this.scrimAlpha <= 0) goto L24;
        if (isTitleCollapseFadeMode() == false) goto L24;
        if (this.collapsingTitleHelper.getExpansionFraction() >= this.collapsingTitleHelper.getFadeModeThresholdFraction()) goto L24;
        int r02 = r7.save();
        r7.clipRect(this.contentScrim.getBounds(), Region.Op.DIFFERENCE);
        this.collapsingTitleHelper.draw(r7);
        this.collapsingSubtitleHelper.draw(r7);
        r7.restoreToCount(r02);
    L26:
        if (this.statusBarScrim != null) goto L28;
        return;
    L28:
        if (this.scrimAlpha <= 0) goto L37;
        WindowInsetsCompat r03 = this.lastInsets;
        if (r03 == null) goto L32;
        int r04 = r03.getSystemWindowInsetTop();
    L33:
        if (r04 <= 0) goto L38;
        this.statusBarScrim.setBounds(0, -this.currentOffset, getWidth(), r04 - this.currentOffset);
        this.statusBarScrim.mutate().setAlpha(this.scrimAlpha);
        this.statusBarScrim.draw(r7);
        return;
    L38:
        return;
    L32:
        r04 = 0;
        goto L33
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r6, View r7, long r8) {
        if (this.contentScrim != null) goto L5;
    L9:
        boolean r0 = false;
    L11:
        if (super.drawChild(r6, r7, r8) == true) goto L15;
        if (r0 == true) goto L15;
        return false;
    L15:
        return true;
    L5:
        if (this.scrimAlpha <= 0) goto L9;
        if (isToolbarChild(r7) == false) goto L9;
        updateContentScrimBounds(this.contentScrim, r7, getWidth(), getHeight());
        this.contentScrim.mutate().setAlpha(this.scrimAlpha);
        this.contentScrim.draw(r6);
        r0 = true;
        goto L11
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] r0 = getDrawableState();
        Drawable r1 = this.statusBarScrim;
        if (r1 != null) goto L5;
    L7:
        boolean r12 = false;
    L8:
        Drawable r2 = this.contentScrim;
        if (r2 != null) goto L11;
    L13:
        CollapsingTextHelper r22 = this.collapsingTitleHelper;
        if (r22 == null) goto L16;
        r12 = r12 | r22.setState(r0);
    L16:
        if (r12 == false) goto L19;
        invalidate();
        return;
    L19:
        return;
    L11:
        if (r2.isStateful() == false) goto L13;
        r12 = r12 | r2.setState(r0);
        goto L13
    L5:
        if (r1.isStateful() == false) goto L7;
        r12 = r1.setState(r0);
        goto L8
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ FrameLayout.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet r2) {
        return new LayoutParams(getContext(), r2);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return new LayoutParams(r1);
    }

    public float getCollapsedSubtitleTextSize() {
        return this.collapsingSubtitleHelper.getCollapsedTextSize();
    }

    public Typeface getCollapsedSubtitleTypeface() {
        return this.collapsingSubtitleHelper.getCollapsedTypeface();
    }

    public int getCollapsedTitleGravity() {
        return this.collapsingTitleHelper.getCollapsedTextGravity();
    }

    public float getCollapsedTitleTextSize() {
        return this.collapsingTitleHelper.getCollapsedTextSize();
    }

    public Typeface getCollapsedTitleTypeface() {
        return this.collapsingTitleHelper.getCollapsedTypeface();
    }

    public Drawable getContentScrim() {
        return this.contentScrim;
    }

    public float getExpandedSubtitleTextSize() {
        return this.collapsingSubtitleHelper.getExpandedTextSize();
    }

    public Typeface getExpandedSubtitleTypeface() {
        return this.collapsingSubtitleHelper.getExpandedTypeface();
    }

    public int getExpandedTitleGravity() {
        return this.collapsingTitleHelper.getExpandedTextGravity();
    }

    public int getExpandedTitleMarginBottom() {
        return this.expandedMarginBottom;
    }

    public int getExpandedTitleMarginEnd() {
        return this.expandedMarginEnd;
    }

    public int getExpandedTitleMarginStart() {
        return this.expandedMarginStart;
    }

    public int getExpandedTitleMarginTop() {
        return this.expandedMarginTop;
    }

    public int getExpandedTitleSpacing() {
        return this.expandedTitleSpacing;
    }

    public float getExpandedTitleTextSize() {
        return this.collapsingTitleHelper.getExpandedTextSize();
    }

    public Typeface getExpandedTitleTypeface() {
        return this.collapsingTitleHelper.getExpandedTypeface();
    }

    public int getHyphenationFrequency() {
        return this.collapsingTitleHelper.getHyphenationFrequency();
    }

    public int getLineCount() {
        return this.collapsingTitleHelper.getLineCount();
    }

    public float getLineSpacingAdd() {
        return this.collapsingTitleHelper.getLineSpacingAdd();
    }

    public float getLineSpacingMultiplier() {
        return this.collapsingTitleHelper.getLineSpacingMultiplier();
    }

    public int getMaxLines() {
        return this.collapsingTitleHelper.getExpandedMaxLines();
    }

    public final int getMaxOffsetForPinChild(View r3) {
        ViewOffsetHelper r0 = getViewOffsetHelper(r3);
        LayoutParams r1 = (LayoutParams) r3.getLayoutParams();
        return ((getHeight() - r0.getLayoutTop()) - r3.getHeight()) - ((FrameLayout.LayoutParams) r1).bottomMargin;
    }

    public int getScrimAlpha() {
        return this.scrimAlpha;
    }

    public long getScrimAnimationDuration() {
        return this.scrimAnimationDuration;
    }

    public int getScrimVisibleHeightTrigger() {
        int r0 = this.scrimVisibleHeightTrigger;
        if (r0 >= 0) goto L5;
        WindowInsetsCompat r02 = this.lastInsets;
        if (r02 == null) goto L9;
        int r03 = r02.getSystemWindowInsetTop();
    L10:
        int r1 = getMinimumHeight();
        if (r1 <= 0) goto L15;
        return Math.min((r1 * 2) + r03, getHeight());
    L15:
        return getHeight() / 3;
    L9:
        r03 = 0;
        goto L10
    L5:
        return (((r0 + this.topInsetApplied) + this.extraMultilineTitleHeight) + this.extraMultilineSubtitleHeight) + this.extraHeightForTitles;
    }

    public Drawable getStatusBarScrim() {
        return this.statusBarScrim;
    }

    public CharSequence getSubtitle() {
        if (this.collapsingTitleEnabled == true) goto L5;
        return null;
    L5:
        return this.collapsingSubtitleHelper.getText();
    }

    public CharSequence getTitle() {
        if (this.collapsingTitleEnabled == true) goto L5;
        return null;
    L5:
        return this.collapsingTitleHelper.getText();
    }

    public int getTitleCollapseMode() {
        return this.titleCollapseMode;
    }

    public TimeInterpolator getTitlePositionInterpolator() {
        return this.collapsingTitleHelper.getPositionInterpolator();
    }

    public TextUtils.TruncateAt getTitleTextEllipsize() {
        return this.collapsingTitleHelper.getTitleTextEllipsize();
    }

    public boolean isExtraMultilineHeightEnabled() {
        return this.extraMultilineHeightEnabled;
    }

    public boolean isForceApplySystemWindowInsetTop() {
        return this.forceApplySystemWindowInsetTop;
    }

    public boolean isRtlTextDirectionHeuristicsEnabled() {
        return this.collapsingTitleHelper.isRtlTextDirectionHeuristicsEnabled();
    }

    public boolean isTitleEnabled() {
        return this.collapsingTitleEnabled;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent r0 = getParent();
        if ((r0 instanceof AppBarLayout) == false) goto L9;
        AppBarLayout r02 = (AppBarLayout) r0;
        disableLiftOnScrollIfNeeded(r02);
        setFitsSystemWindows(r02.getFitsSystemWindows());
        if (this.onOffsetChangedListener != null) goto L7;
        this.onOffsetChangedListener = new OffsetUpdateListener(this);
    L7:
        r02.addOnOffsetChangedListener(this.onOffsetChangedListener);
        requestApplyInsets();
        return;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration r3) {
        super.onConfigurationChanged(r3);
        this.collapsingTitleHelper.maybeUpdateFontWeightAdjustment(r3);
        if (this.screenOrientation != r3.orientation) goto L5;
    L9:
        this.screenOrientation = r3.orientation;
        return;
    L5:
        if (this.extraMultilineHeightEnabled == false) goto L9;
        if (this.collapsingTitleHelper.getExpansionFraction() != 1.0f) goto L9;
        maybeSetPendingActionCollapsed();
        goto L9
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        ViewParent r0 = getParent();
        AppBarLayout.OnOffsetChangedListener r1 = this.onOffsetChangedListener;
        if (r1 != null) goto L5;
    L7:
        super.onDetachedFromWindow();
        return;
    L5:
        if ((r0 instanceof AppBarLayout) == false) goto L7;
        ((AppBarLayout) r0).removeOnOffsetChangedListener(r1);
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
        super.onLayout(r6, r7, r8, r9, r10);
        WindowInsetsCompat r102 = this.lastInsets;
        int r0 = 0;
        if (r102 == null) goto L12;
        int r103 = r102.getSystemWindowInsetTop();
        int r1 = getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L12;
        View r3 = getChildAt(r2);
        if (r3.getFitsSystemWindows() == true) goto L11;
        if (r3.getTop() >= r103) goto L11;
        ViewCompat.offsetTopAndBottom(r3, r103);
    L11:
        r2 = r2 + 1;
    L12:
        int r104 = getChildCount();
        int r12 = 0;
    L13:
        if (r12 >= r104) goto L15;
        getViewOffsetHelper(getChildAt(r12)).onViewLayout();
        r12 = r12 + 1;
        goto L13
    L15:
        updateTextBounds(r7, r8, r9, r10, false);
        updateTitleFromToolbarIfNeeded();
        updateScrimVisibility();
        int r62 = getChildCount();
    L16:
        if (r0 >= r62) goto L18;
        getViewOffsetHelper(getChildAt(r0)).applyOffsets();
        r0 = r0 + 1;
        goto L16
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r10, int r11) {
        ensureToolbar();
        super.onMeasure(r10, r11);
        int r112 = View.MeasureSpec.getMode(r11);
        WindowInsetsCompat r0 = this.lastInsets;
        if (r0 == null) goto L5;
        int r02 = r0.getSystemWindowInsetTop();
    L7:
        if (r112 != 0) goto L9;
    L10:
        if (r02 <= 0) goto L12;
        this.topInsetApplied = r02;
        super.onMeasure(r10, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + r02, TLObject.FLAG_30));
    L12:
        updateTitleFromToolbarIfNeeded();
        if (this.collapsingTitleEnabled == true) goto L15;
    L41:
        CollapsingToolbarLayout r3 = this;
    L42:
        ViewGroup r9 = r3.toolbar;
        if (r9 == null) goto L51;
        View r102 = r3.toolbarDirectChild;
        if (r102 == null) goto L49;
        if (r102 == r3) goto L49;
        r3.setMinimumHeight(getHeightWithMargins(r102));
    L49:
        r3.setMinimumHeight(getHeightWithMargins(r9));
    L51:
        if (r3.extraMultilineHeightEnabled == true) goto L53;
        return;
    L53:
        if (r3.collapsingTitleHelper.getExpandedMaxLines() > 1) goto L55;
        return;
    L55:
        if (r3.collapsingTitleHelper.getExpansionFraction() != 1.0f) goto L60;
        r3.maybeSetPendingActionCollapsed();
        return;
    L60:
        return;
    L15:
        if (TextUtils.isEmpty(this.collapsingTitleHelper.getText()) == true) goto L41;
        int r7 = getMeasuredHeight();
        r3 = this;
        r3.updateTextBounds(0, 0, getMeasuredWidth(), r7, true);
        float r92 = (r3.topInsetApplied + r3.expandedMarginTop) + r3.collapsingTitleHelper.getExpandedTextFullSingleLineHeight();
        if (TextUtils.isEmpty(r3.collapsingSubtitleHelper.getText()) == false) goto L19;
        float r113 = 0.0f;
    L20:
        int r93 = (int) ((r92 + r113) + r3.expandedMarginBottom);
        if (r93 <= r7) goto L23;
        r3.extraHeightForTitles = r93 - r7;
    L25:
        if (r3.extraMultilineHeightEnabled == true) goto L27;
    L38:
        int r94 = r3.extraHeightForTitles;
        int r114 = r3.extraMultilineTitleHeight;
        int r4 = r3.extraMultilineSubtitleHeight;
        if (((r94 + r114) + r4) <= 0) goto L42;
        super.onMeasure(r10, View.MeasureSpec.makeMeasureSpec(((r7 + r94) + r114) + r4, TLObject.FLAG_30));
        goto L42
    L27:
        if (r3.collapsingTitleHelper.getExpandedMaxLines() <= 1) goto L33;
        int r95 = r3.collapsingTitleHelper.getExpandedLineCount();
        if (r95 <= 1) goto L31;
        r3.extraMultilineTitleHeight = Math.round(r3.collapsingTitleHelper.getExpandedTextFullSingleLineHeight()) * (r95 - 1);
        goto L33
    L31:
        r3.extraMultilineTitleHeight = 0;
    L33:
        if (r3.collapsingSubtitleHelper.getExpandedMaxLines() <= 1) goto L38;
        int r96 = r3.collapsingSubtitleHelper.getExpandedLineCount();
        if (r96 <= 1) goto L37;
        r3.extraMultilineSubtitleHeight = Math.round(r3.collapsingSubtitleHelper.getExpandedTextFullSingleLineHeight()) * (r96 - 1);
        goto L38
    L37:
        r3.extraMultilineSubtitleHeight = 0;
        goto L38
    L23:
        r3.extraHeightForTitles = 0;
        goto L25
    L19:
        r113 = r3.expandedTitleSpacing + r3.collapsingSubtitleHelper.getExpandedTextFullSingleLineHeight();
        goto L20
    L9:
        if (this.forceApplySystemWindowInsetTop == false) goto L12;
    L5:
        r02 = 0;
        goto L7
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        Drawable r32 = this.contentScrim;
        if (r32 == null) goto L6;
        updateContentScrimBounds(r32, r1, r2);
        return;
    }

    public WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat r3) {
        if (getFitsSystemWindows() == false) goto L5;
        WindowInsetsCompat r0 = r3;
    L7:
        if (ObjectsCompat.equals(this.lastInsets, r0) == true) goto L10;
        this.lastInsets = r0;
        requestLayout();
    L10:
        return r3.consumeSystemWindowInsets();
    L5:
        r0 = null;
        goto L7
    }

    public void setCollapsedSubtitleTextAppearance(int r1) {
        this.collapsingSubtitleHelper.setCollapsedTextAppearance(r1);
    }

    public void setCollapsedSubtitleTextColor(int r1) {
        setCollapsedSubtitleTextColor(ColorStateList.valueOf(r1));
    }

    public void setCollapsedSubtitleTextColor(ColorStateList r1) {
        this.collapsingSubtitleHelper.setCollapsedTextColor(r1);
    }

    public void setCollapsedSubtitleTextSize(float r1) {
        this.collapsingSubtitleHelper.setCollapsedTextSize(r1);
    }

    public void setCollapsedSubtitleTypeface(Typeface r1) {
        this.collapsingSubtitleHelper.setCollapsedTypeface(r1);
    }

    public void setCollapsedTitleGravity(int r2) {
        this.collapsingTitleHelper.setCollapsedTextGravity(r2);
        this.collapsingSubtitleHelper.setCollapsedTextGravity(r2);
    }

    public void setCollapsedTitleTextAppearance(int r1) {
        this.collapsingTitleHelper.setCollapsedTextAppearance(r1);
    }

    public void setCollapsedTitleTextColor(int r1) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(r1));
    }

    public void setCollapsedTitleTextColor(ColorStateList r1) {
        this.collapsingTitleHelper.setCollapsedTextColor(r1);
    }

    public void setCollapsedTitleTextSize(float r1) {
        this.collapsingTitleHelper.setCollapsedTextSize(r1);
    }

    public void setCollapsedTitleTypeface(Typeface r1) {
        this.collapsingTitleHelper.setCollapsedTypeface(r1);
    }

    public void setContentScrim(Drawable r3) {
        Drawable r0 = this.contentScrim;
        if (r0 == r3) goto L14;
        Drawable r1 = null;
        if (r0 == null) goto L7;
        r0.setCallback(null);
    L7:
        if (r3 == null) goto L9;
        r1 = r3.mutate();
    L9:
        this.contentScrim = r1;
        if (r1 == null) goto L12;
        updateContentScrimBounds(r1, getWidth(), getHeight());
        this.contentScrim.setCallback(this);
        this.contentScrim.setAlpha(this.scrimAlpha);
    L12:
        postInvalidateOnAnimation();
        return;
    }

    public void setContentScrimColor(int r2) {
        setContentScrim(new ColorDrawable(r2));
    }

    public void setContentScrimResource(int r2) {
        setContentScrim(getContext().getDrawable(r2));
    }

    public void setExpandedSubtitleColor(int r1) {
        setExpandedSubtitleTextColor(ColorStateList.valueOf(r1));
    }

    public void setExpandedSubtitleTextAppearance(int r1) {
        this.collapsingSubtitleHelper.setExpandedTextAppearance(r1);
    }

    public void setExpandedSubtitleTextColor(ColorStateList r1) {
        this.collapsingSubtitleHelper.setExpandedTextColor(r1);
    }

    public void setExpandedSubtitleTextSize(float r1) {
        this.collapsingSubtitleHelper.setExpandedTextSize(r1);
    }

    public void setExpandedSubtitleTypeface(Typeface r1) {
        this.collapsingSubtitleHelper.setExpandedTypeface(r1);
    }

    public void setExpandedTitleColor(int r1) {
        setExpandedTitleTextColor(ColorStateList.valueOf(r1));
    }

    public void setExpandedTitleGravity(int r2) {
        this.collapsingTitleHelper.setExpandedTextGravity(r2);
        this.collapsingSubtitleHelper.setExpandedTextGravity(r2);
    }

    public void setExpandedTitleMargin(int r1, int r2, int r3, int r4) {
        this.expandedMarginStart = r1;
        this.expandedMarginTop = r2;
        this.expandedMarginEnd = r3;
        this.expandedMarginBottom = r4;
        requestLayout();
    }

    public void setExpandedTitleMarginBottom(int r1) {
        this.expandedMarginBottom = r1;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int r1) {
        this.expandedMarginEnd = r1;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int r1) {
        this.expandedMarginStart = r1;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int r1) {
        this.expandedMarginTop = r1;
        requestLayout();
    }

    public void setExpandedTitleSpacing(int r1) {
        this.expandedTitleSpacing = r1;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(int r1) {
        this.collapsingTitleHelper.setExpandedTextAppearance(r1);
    }

    public void setExpandedTitleTextColor(ColorStateList r1) {
        this.collapsingTitleHelper.setExpandedTextColor(r1);
    }

    public void setExpandedTitleTextSize(float r1) {
        this.collapsingTitleHelper.setExpandedTextSize(r1);
    }

    public void setExpandedTitleTypeface(Typeface r1) {
        this.collapsingTitleHelper.setExpandedTypeface(r1);
    }

    public void setExtraMultilineHeightEnabled(boolean r1) {
        this.extraMultilineHeightEnabled = r1;
    }

    public void setForceApplySystemWindowInsetTop(boolean r1) {
        this.forceApplySystemWindowInsetTop = r1;
    }

    public void setHyphenationFrequency(int r1) {
        this.collapsingTitleHelper.setHyphenationFrequency(r1);
    }

    public void setLineSpacingAdd(float r1) {
        this.collapsingTitleHelper.setLineSpacingAdd(r1);
    }

    public void setLineSpacingMultiplier(float r1) {
        this.collapsingTitleHelper.setLineSpacingMultiplier(r1);
    }

    public void setMaxLines(int r2) {
        this.collapsingTitleHelper.setExpandedMaxLines(r2);
        this.collapsingSubtitleHelper.setExpandedMaxLines(r2);
    }

    public void setRtlTextDirectionHeuristicsEnabled(boolean r1) {
        this.collapsingTitleHelper.setRtlTextDirectionHeuristicsEnabled(r1);
    }

    public void setScrimAlpha(int r2) {
        if (r2 != this.scrimAlpha) goto L5;
        return;
    L5:
        if (this.contentScrim == null) goto L9;
        ViewGroup r0 = this.toolbar;
        if (r0 == null) goto L9;
        r0.postInvalidateOnAnimation();
    L9:
        this.scrimAlpha = r2;
        postInvalidateOnAnimation();
    }

    public void setScrimAnimationDuration(long r1) {
        this.scrimAnimationDuration = r1;
    }

    public void setScrimVisibleHeightTrigger(int r2) {
        if (this.scrimVisibleHeightTrigger == r2) goto L6;
        this.scrimVisibleHeightTrigger = r2;
        updateScrimVisibility();
        return;
    }

    public void setScrimsShown(boolean r2) {
        if (isLaidOut() == true) goto L5;
    L7:
        boolean r0 = false;
    L8:
        setScrimsShown(r2, r0);
        return;
    L5:
        if (isInEditMode() == true) goto L7;
        r0 = true;
        goto L8
    }

    public void setScrimsShown(boolean r3, boolean r4) {
        if (this.scrimsAreShown == r3) goto L14;
        int r0 = 0;
        if (r4 == false) goto L9;
        if (r3 == false) goto L8;
        r0 = 255;
    L8:
        animateScrim(r0);
    L12:
        this.scrimsAreShown = r3;
        return;
    L9:
        if (r3 == false) goto L11;
        r0 = 255;
    L11:
        setScrimAlpha(r0);
        goto L12
    }

    public void setStaticLayoutBuilderConfigurer(StaticLayoutBuilderConfigurer r1) {
        this.collapsingTitleHelper.setStaticLayoutBuilderConfigurer(r1);
    }

    public void setStatusBarScrim(Drawable r3) {
        Drawable r0 = this.statusBarScrim;
        if (r0 == r3) goto L21;
        Drawable r1 = null;
        if (r0 == null) goto L7;
        r0.setCallback(null);
    L7:
        if (r3 == null) goto L9;
        r1 = r3.mutate();
    L9:
        this.statusBarScrim = r1;
        if (r1 != null) goto L12;
    L19:
        postInvalidateOnAnimation();
        return;
    L12:
        if (r1.isStateful() == false) goto L14;
        this.statusBarScrim.setState(getDrawableState());
    L14:
        DrawableCompat.setLayoutDirection(this.statusBarScrim, getLayoutDirection());
        Drawable r32 = this.statusBarScrim;
        if (getVisibility() != 0) goto L17;
        boolean r02 = true;
    L18:
        r32.setVisible(r02, false);
        this.statusBarScrim.setCallback(this);
        this.statusBarScrim.setAlpha(this.scrimAlpha);
        goto L19
    L17:
        r02 = false;
        goto L18
    }

    public void setStatusBarScrimColor(int r2) {
        setStatusBarScrim(new ColorDrawable(r2));
    }

    public void setStatusBarScrimResource(int r2) {
        setStatusBarScrim(getContext().getDrawable(r2));
    }

    public void setSubtitle(CharSequence r1) {
        this.collapsingSubtitleHelper.setText(r1);
    }

    public void setTitle(CharSequence r2) {
        this.collapsingTitleHelper.setText(r2);
        updateContentDescriptionFromTitle();
    }

    public void setTitleCollapseMode(int r3) {
        this.titleCollapseMode = r3;
        boolean r32 = isTitleCollapseFadeMode();
        this.collapsingTitleHelper.setFadeModeEnabled(r32);
        this.collapsingSubtitleHelper.setFadeModeEnabled(r32);
        ViewParent r0 = getParent();
        if ((r0 instanceof AppBarLayout) == false) goto L5;
        disableLiftOnScrollIfNeeded((AppBarLayout) r0);
    L5:
        if (r32 == true) goto L7;
        return;
    L7:
        if (this.contentScrim != null) goto L11;
        setContentScrimColor(getDefaultContentScrimColorForTitleCollapseFadeMode());
        return;
    }

    public void setTitleEllipsize(TextUtils.TruncateAt r1) {
        this.collapsingTitleHelper.setTitleTextEllipsize(r1);
    }

    public void setTitleEnabled(boolean r2) {
        if (r2 == this.collapsingTitleEnabled) goto L6;
        this.collapsingTitleEnabled = r2;
        updateContentDescriptionFromTitle();
        updateDummyView();
        requestLayout();
        return;
    }

    public void setTitlePositionInterpolator(TimeInterpolator r1) {
        this.collapsingTitleHelper.setPositionInterpolator(r1);
    }

    @Override // android.view.View
    public void setVisibility(int r3) {
        super.setVisibility(r3);
        if (r3 != 0) goto L5;
        boolean r32 = true;
    L6:
        Drawable r1 = this.statusBarScrim;
        if (r1 != null) goto L9;
    L11:
        Drawable r12 = this.contentScrim;
        if (r12 != null) goto L14;
        return;
    L14:
        if (r12.isVisible() == r32) goto L18;
        this.contentScrim.setVisible(r32, false);
        return;
    L18:
        return;
    L9:
        if (r1.isVisible() == r32) goto L11;
        this.statusBarScrim.setVisible(r32, false);
        goto L11
    L5:
        r32 = false;
        goto L6
    }

    public final void updateScrimVisibility() {
        if (this.contentScrim != null) goto L9;
        if (this.statusBarScrim != null) goto L9;
        return;
    L9:
        if ((getHeight() + this.currentOffset) >= getScrimVisibleHeightTrigger()) goto L11;
        boolean r0 = true;
    L12:
        setScrimsShown(r0);
        return;
    L11:
        r0 = false;
        goto L12
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (r2 != this.contentScrim) goto L7;
        return true;
    L7:
        if (r2 == this.statusBarScrim) goto L14;
        return false;
    L14:
        return true;
    }
}
