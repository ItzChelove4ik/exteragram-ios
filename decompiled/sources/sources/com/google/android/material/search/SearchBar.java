package com.google.android.material.search;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.material.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class SearchBar extends Toolbar {
    private static final float ADAPTIVE_MAX_WIDTH_PERCENT_AFTER_BREAKPOINT = 0.5f;
    private static final int DEFAULT_SCROLL_FLAGS = 53;
    private static final int DEF_STYLE_RES = 0;
    private static final String NAMESPACE_APP = "http://schemas.android.com/apk/res-auto";
    static final int NO_RES_ID = -1;
    private final boolean adaptiveMaxWidthEnabled;
    private final int adaptiveMaxWidthParentBreakpoint;
    private final int backgroundColor;
    private MaterialShapeDrawable backgroundShape;
    private View centerView;
    private final boolean defaultMarginsEnabled;
    private final Drawable defaultNavigationIcon;
    private boolean defaultScrollFlagsEnabled;
    private int endSiblingViewId;
    private final boolean forceDefaultNavigationOnClickListener;
    private final boolean layoutInflated;
    private final AppBarLayout.LiftOnScrollProgressListener liftColorListener;
    private boolean liftOnScroll;
    private final ColorStateList liftOnScrollColor;
    private int maxWidth;
    private int menuResId;
    private ActionMenuView menuView;
    private ImageButton navIconButton;
    private Integer navigationIconTint;
    private Drawable originalNavigationIconBackground;
    private final TextView placeholderTextView;
    private final SearchBarAnimationHelper searchBarAnimationHelper;
    private int startSiblingViewId;
    private boolean textCentered;
    private final TextView textView;
    private final FrameLayout textViewContainer;
    private final boolean tintNavigationIcon;

    /* renamed from: com.google.android.material.search.SearchBar$1, reason: invalid class name */
    public class AnonymousClass1 extends AppBarLayout.LiftOnScrollProgressListener {
        final /* synthetic */ SearchBar this$0;

        public AnonymousClass1(SearchBar r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.LiftOnScrollProgressListener
        public void onUpdate(float r1, int r2, float r3) {
            if (SearchBar.access$000(this.this$0) == null) goto L6;
            int r12 = MaterialColors.layer(SearchBar.access$100(this.this$0), SearchBar.access$000(this.this$0).getDefaultColor(), r3);
            SearchBar.access$200(this.this$0).setFillColor(ColorStateList.valueOf(r12));
            return;
        }
    }

    public static abstract class OnLoadAnimationCallback {
        public OnLoadAnimationCallback() {
        }

        public void onAnimationEnd() {
        }

        public void onAnimationStart() {
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        String text;

        /* renamed from: com.google.android.material.search.SearchBar$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.ClassLoaderCreator<SavedState> {
            public AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r1) {
                return new SavedState(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return new SavedState(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return createFromParcel(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public SavedState(Parcel r2) {
            this(r2, null);
        }

        public SavedState(Parcel r1, ClassLoader r2) {
            super(r1, r2);
            this.text = r1.readString();
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeString(this.text);
        }
    }

    public static class ScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {
        private boolean initialized;

        public ScrollingViewBehavior() {
            this.initialized = false;
        }

        public ScrollingViewBehavior(Context r1, AttributeSet r2) {
            super(r1, r2);
            this.initialized = false;
        }

        private void setAppBarLayoutTransparent(AppBarLayout r1) {
            r1.setBackgroundColor(0);
            r1.setTargetElevation(0.0f);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onDependentViewChanged(CoordinatorLayout r1, View r2, View r3) {
            boolean r12 = super.onDependentViewChanged(r1, r2, r3);
            if (this.initialized == false) goto L5;
        L7:
            return r12;
        L5:
            if ((r3 instanceof AppBarLayout) == false) goto L7;
            this.initialized = true;
            AppBarLayout r32 = (AppBarLayout) r3;
            r32.setTouchscreenBlocksFocus(false);
            setAppBarLayoutTransparent(r32);
            goto L7
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public boolean shouldHeaderOverlapScrollingChild() {
            return true;
        }
    }

    /* renamed from: $r8$lambda$vK_1DmOSac-arBKix9GwXnp1Ku4, reason: not valid java name */
    public static /* synthetic */ void m1888$r8$lambda$vK_1DmOSacarBKix9GwXnp1Ku4(SearchBar r1) {
        r1.searchBarAnimationHelper.startOnLoadAnimation(r1);
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Material3_SearchBar;
    }

    public SearchBar(Context r2) {
        this(r2, null);
    }

    public SearchBar(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.materialSearchBarStyle);
    }

    public SearchBar(Context r17, AttributeSet r18, int r19) {
        int r5 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r17, r18, r19, r5), r18, r19);
        this.menuResId = -1;
        this.liftColorListener = new AnonymousClass1(this);
        Context r1 = getContext();
        validateAttributes(r18);
        this.adaptiveMaxWidthParentBreakpoint = getResources().getDimensionPixelSize(R.dimen.m3_searchbar_parent_width_breakpoint);
        this.defaultNavigationIcon = AppCompatResources.getDrawable(r1, getDefaultNavigationIconResource());
        this.searchBarAnimationHelper = new SearchBarAnimationHelper();
        TypedArray r3 = ThemeEnforcement.obtainStyledAttributes(r1, r18, R.styleable.SearchBar, r19, r5, new int[0]);
        ShapeAppearanceModel r2 = ShapeAppearanceModel.builder(r1, r18, r19, r5).build();
        int r4 = r3.getColor(R.styleable.SearchBar_backgroundTint, 0);
        this.backgroundColor = r4;
        this.liftOnScrollColor = MaterialResources.getColorStateList(r1, r3, R.styleable.SearchBar_liftOnScrollColor);
        float r52 = r3.getDimension(R.styleable.SearchBar_elevation, 0.0f);
        this.defaultMarginsEnabled = r3.getBoolean(R.styleable.SearchBar_defaultMarginsEnabled, true);
        this.defaultScrollFlagsEnabled = r3.getBoolean(R.styleable.SearchBar_defaultScrollFlagsEnabled, true);
        boolean r6 = r3.getBoolean(R.styleable.SearchBar_hideNavigationIcon, false);
        this.forceDefaultNavigationOnClickListener = r3.getBoolean(R.styleable.SearchBar_forceDefaultNavigationOnClickListener, false);
        this.tintNavigationIcon = r3.getBoolean(R.styleable.SearchBar_tintNavigationIcon, true);
        if (r3.hasValue(R.styleable.SearchBar_navigationIconTint) == false) goto L5;
        this.navigationIconTint = Integer.valueOf(r3.getColor(R.styleable.SearchBar_navigationIconTint, -1));
    L5:
        int r10 = r3.getResourceId(R.styleable.SearchBar_android_textAppearance, -1);
        String r11 = r3.getString(R.styleable.SearchBar_android_text);
        String r12 = r3.getString(R.styleable.SearchBar_android_hint);
        float r13 = r3.getDimension(R.styleable.SearchBar_strokeWidth, -1.0f);
        int r14 = r3.getColor(R.styleable.SearchBar_strokeColor, 0);
        this.textCentered = r3.getBoolean(R.styleable.SearchBar_textCentered, false);
        this.liftOnScroll = r3.getBoolean(R.styleable.SearchBar_liftOnScroll, false);
        this.maxWidth = r3.getDimensionPixelSize(R.styleable.SearchBar_android_maxWidth, -1);
        this.adaptiveMaxWidthEnabled = r3.getBoolean(R.styleable.SearchBar_adaptiveMaxWidthEnabled, false);
        this.startSiblingViewId = r3.getResourceId(R.styleable.SearchBar_startSiblingViewId, -1);
        this.endSiblingViewId = r3.getResourceId(R.styleable.SearchBar_endSiblingViewId, -1);
        r3.recycle();
        if (r6 == true) goto L8;
        initNavigationIcon();
    L8:
        setClickable(true);
        setFocusable(true);
        LayoutInflater.from(r1).inflate(R.layout.mtrl_search_bar, this);
        this.layoutInflated = true;
        this.textView = (TextView) findViewById(R.id.open_search_bar_text_view);
        this.placeholderTextView = (TextView) findViewById(R.id.open_search_bar_placeholder_text_view);
        this.textViewContainer = (FrameLayout) findViewById(R.id.open_search_bar_text_view_container);
        setElevation(r52);
        initTextView(r10, r11, r12);
        initBackground(r2, r4, r52, r13, r14);
    }

    public static /* synthetic */ ColorStateList access$000(SearchBar r0) {
        return r0.liftOnScrollColor;
    }

    public static /* synthetic */ int access$100(SearchBar r0) {
        return r0.backgroundColor;
    }

    public static /* synthetic */ MaterialShapeDrawable access$200(SearchBar r0) {
        return r0.backgroundShape;
    }

    private void addLiftOnScrollProgressListener() {
        AppBarLayout r0 = getAppBarLayoutParentIfExists();
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.liftOnScrollColor == null) goto L9;
        r0.addLiftOnScrollProgressListener(this.liftColorListener);
        return;
    }

    private int defaultIfZero(int r1, int r2) {
        if (r1 != 0) goto L4;
        return r2;
    L4:
        return r1;
    }

    private ActionMenuView findOrGetMenuView() {
        if (this.menuView != null) goto L6;
        this.menuView = ToolbarUtils.getActionMenuView(this);
    L6:
        return this.menuView;
    }

    private ImageButton findOrGetNavView() {
        if (this.navIconButton != null) goto L6;
        this.navIconButton = ToolbarUtils.getNavigationIconButton(this);
    L6:
        return this.navIconButton;
    }

    private void initBackground(ShapeAppearanceModel r2, int r3, float r4, float r5, int r6) {
        MaterialShapeDrawable r0 = new MaterialShapeDrawable(r2);
        this.backgroundShape = r0;
        r0.initializeElevationOverlay(getContext());
        this.backgroundShape.setElevation(r4);
        if (r5 < 0.0f) goto L5;
        this.backgroundShape.setStroke(r5, r6);
    L5:
        int r22 = MaterialColors.getColor(this, R$attr.colorControlHighlight);
        this.backgroundShape.setFillColor(ColorStateList.valueOf(r3));
        ColorStateList r23 = ColorStateList.valueOf(r22);
        MaterialShapeDrawable r42 = this.backgroundShape;
        RippleDrawable r32 = new RippleDrawable(r23, r42, r42);
        FocusRingDrawable.layer(getContext(), r32, this.backgroundShape);
        setBackground(r32);
    }

    private void initNavigationIcon() {
        if (getNavigationIcon() != null) goto L5;
        Drawable r0 = this.defaultNavigationIcon;
    L6:
        setNavigationIcon(r0);
        setNavigationIconDecorative(true);
        return;
    L5:
        r0 = getNavigationIcon();
        goto L6
    }

    private void initTextView(int r2, String r3, String r4) {
        if (r2 == (-1)) goto L5;
        TextViewCompat.setTextAppearance(this.textView, r2);
        TextViewCompat.setTextAppearance(this.placeholderTextView, r2);
    L5:
        setText(r3);
        setHint(r4);
        setTextCentered(this.textCentered);
    }

    private void layoutChild(View r3, int r4, int r5, int r6, int r7) {
        if (getLayoutDirection() != 1) goto L6;
        r3.layout(getMeasuredWidth() - r6, r5, getMeasuredWidth() - r4, r7);
        return;
    L6:
        r3.layout(r4, r5, r6, r7);
    }

    private void layoutTextViewCenterAvoidToolbarViewsAndPadding() {
        int r0 = (getMeasuredWidth() / 2) - (this.textViewContainer.getMeasuredWidth() / 2);
        int r1 = this.textViewContainer.getMeasuredWidth() + r0;
        int r2 = (getMeasuredHeight() / 2) - (this.textViewContainer.getMeasuredHeight() / 2);
        int r3 = this.textViewContainer.getMeasuredHeight() + r2;
        boolean r6 = true;
        if (getLayoutDirection() == 1) goto L6;
        r6 = false;
    L6:
        ActionMenuView r4 = findOrGetMenuView();
        ImageButton r7 = findOrGetNavView();
        int r8 = (this.textViewContainer.getMeasuredWidth() / 2) - (this.textView.getMeasuredWidth() / 2);
        int r9 = this.textView.getMeasuredWidth() + r8;
        int r82 = r8 + r0;
        int r92 = r9 + r0;
        if (r6 == false) goto L9;
        ActionMenuView r10 = r4;
    L10:
        if (r6 == false) goto L12;
        r4 = r7;
    L12:
        if (r10 == null) goto L14;
        int r62 = Math.max(r10.getRight() - r82, 0);
    L15:
        int r83 = r82 + r62;
        int r93 = r92 + r62;
        if (r4 == null) goto L18;
        int r42 = Math.max(r93 - r4.getLeft(), 0);
    L19:
        int r84 = r83 - r42;
        int r94 = r93 - r42;
        int r72 = Math.max(getPaddingLeft() - r84, getContentInsetLeft() - r84);
        int r85 = Math.max(r94 - (getMeasuredWidth() - getPaddingRight()), r94 - (getMeasuredWidth() - getContentInsetRight()));
        int r63 = ((r62 - r42) + Math.max(r72, 0)) - Math.max(r85, 0);
        this.textViewContainer.layout(r0 + r63, r2, r1 + r63, r3);
        return;
    L18:
        r42 = 0;
        goto L19
    L14:
        r62 = 0;
        goto L15
    L9:
        r10 = r7;
        goto L10
    }

    private void layoutViewInCenter(View r10) {
        if (r10 != null) goto L4;
        return;
    L4:
        int r0 = r10.getMeasuredWidth();
        int r5 = (getMeasuredWidth() / 2) - (r0 / 2);
        int r7 = r5 + r0;
        int r02 = r10.getMeasuredHeight();
        int r6 = (getMeasuredHeight() / 2) - (r02 / 2);
        layoutChild(r10, r5, r6, r7, r6 + r02);
    }

    private Drawable maybeTintNavigationIcon(Drawable r2) {
        if (this.tintNavigationIcon == false) goto L16;
        if (r2 == null) goto L17;
        Integer r0 = this.navigationIconTint;
        if (r0 == null) goto L10;
        int r1 = r0.intValue();
    L14:
        Drawable r22 = DrawableCompat.wrap(r2.mutate());
        r22.setTint(r1);
        return r22;
    L10:
        if (r2 != this.defaultNavigationIcon) goto L12;
        int r02 = R.attr.colorOnSurfaceVariant;
    L13:
        r1 = MaterialColors.getColor(this, r02);
        goto L14
    L12:
        r02 = R.attr.colorOnSurface;
        goto L13
    L17:
        return r2;
    L16:
        return r2;
    }

    private void measureCenterView(int r1, int r2) {
        View r0 = this.centerView;
        if (r0 == null) goto L6;
        r0.measure(r1, r2);
        return;
    }

    private void removeLiftOnScrollProgressListener() {
        AppBarLayout r0 = getAppBarLayoutParentIfExists();
        if (r0 == null) goto L6;
        r0.removeLiftOnScrollProgressListener(this.liftColorListener);
        return;
    }

    private void setDefaultMargins() {
        if (this.defaultMarginsEnabled == true) goto L5;
        return;
    L5:
        if ((getLayoutParams() instanceof ViewGroup.MarginLayoutParams) == false) goto L9;
        Resources r0 = getResources();
        int r1 = r0.getDimensionPixelSize(R.dimen.m3_searchbar_margin_horizontal);
        int r02 = r0.getDimensionPixelSize(getDefaultMarginVerticalResource());
        ViewGroup.MarginLayoutParams r2 = (ViewGroup.MarginLayoutParams) getLayoutParams();
        r2.leftMargin = defaultIfZero(r2.leftMargin, r1);
        r2.topMargin = defaultIfZero(r2.topMargin, r02);
        r2.rightMargin = defaultIfZero(r2.rightMargin, r1);
        r2.bottomMargin = defaultIfZero(r2.bottomMargin, r02);
        return;
    }

    private void setHandwritingBoundsInsets() {
        if (Build.VERSION.SDK_INT >= 34) goto L5;
        return;
    L5:
        boolean r1 = true;
        int r2 = 0;
        if (getLayoutDirection() == 1) goto L9;
        r1 = false;
    L9:
        ImageButton r0 = ToolbarUtils.getNavigationIconButton(this);
        if (r0 != null) goto L12;
    L16:
        int r3 = 0;
    L17:
        ActionMenuView r02 = ToolbarUtils.getActionMenuView(this);
        if (r02 == null) goto L22;
        if (r1 == false) goto L21;
        r2 = r02.getRight();
        goto L22
    L21:
        r2 = getWidth() - r02.getLeft();
    L22:
        if (r1 == false) goto L24;
        int r03 = r2;
    L25:
        float r04 = -r03;
        if (r1 == true) goto L29;
        r3 = r2;
    L29:
        setHandwritingBoundsOffsets(r04, 0.0f, -r3, 0.0f);
        return;
    L24:
        r03 = r3;
        goto L25
    L12:
        if (r0.isClickable() == false) goto L16;
        if (r1 == false) goto L15;
        r3 = getWidth() - r0.getLeft();
        goto L17
    L15:
        r3 = r0.getRight();
        goto L17
    }

    private void setNavigationIconDecorative(boolean r3) {
        ImageButton r0 = ToolbarUtils.getNavigationIconButton(this);
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setClickable(!r3);
        r0.setFocusable(!r3);
        Drawable r1 = r0.getBackground();
        if (r1 == null) goto L8;
        this.originalNavigationIconBackground = r1;
    L8:
        if (r3 == false) goto L10;
        Drawable r32 = null;
    L11:
        r0.setBackgroundDrawable(r32);
        setHandwritingBoundsInsets();
        return;
    L10:
        r32 = this.originalNavigationIconBackground;
        goto L11
    }

    private void setOrClearDefaultScrollFlags() {
        if ((getLayoutParams() instanceof AppBarLayout.LayoutParams) == false) goto L14;
        AppBarLayout.LayoutParams r0 = (AppBarLayout.LayoutParams) getLayoutParams();
        if (this.defaultScrollFlagsEnabled == false) goto L11;
        if (r0.getScrollFlags() != 0) goto L15;
        r0.setScrollFlags(53);
        return;
    L15:
        return;
    L11:
        if (r0.getScrollFlags() != 53) goto L16;
        r0.setScrollFlags(0);
        return;
    L16:
        return;
    }

    private void validateAttributes(AttributeSet r2) {
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.getAttributeValue(NAMESPACE_APP, "title") == null) goto L7;
        ByteString$$ExternalSyntheticBUOutline0.m("SearchBar does not support title. Use hint or text instead.");
        return;
    L7:
        if (r2.getAttributeValue(NAMESPACE_APP, MediaTrack.ROLE_SUBTITLE) != null) goto L9;
        return;
    L9:
        ByteString$$ExternalSyntheticBUOutline0.m("SearchBar does not support subtitle. Use hint or text instead.");
    }

    public void addCollapseAnimationListener(AnimatorListenerAdapter r1) {
        this.searchBarAnimationHelper.addCollapseAnimationListener(r1);
    }

    public void addExpandAnimationListener(AnimatorListenerAdapter r1) {
        this.searchBarAnimationHelper.addExpandAnimationListener(r1);
    }

    public void addOnLoadAnimationCallback(OnLoadAnimationCallback r1) {
        this.searchBarAnimationHelper.addOnLoadAnimationCallback(r1);
    }

    @Override // android.view.ViewGroup
    public void addView(View r2, int r3, ViewGroup.LayoutParams r4) {
        if (this.layoutInflated == true) goto L5;
    L9:
        super.addView(r2, r3, r4);
        return;
    L5:
        if (this.centerView != null) goto L9;
        if ((r2 instanceof ActionMenuView) == true) goto L9;
        this.centerView = r2;
        r2.setAlpha(0.0f);
        goto L9
    }

    public void clearText() {
        this.textView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.placeholderTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    public boolean collapse(View r2) {
        return collapse(r2, null);
    }

    public boolean collapse(View r2, AppBarLayout r3) {
        return collapse(r2, r3, false);
    }

    public boolean collapse(View r2, AppBarLayout r3, boolean r4) {
        if (r2.getVisibility() != 0) goto L7;
        if (isCollapsing() == true) goto L7;
    L8:
        this.searchBarAnimationHelper.startCollapseAnimation(this, r2, r3, r4);
        return true;
    L7:
        if (isExpanding() == true) goto L8;
        return false;
    }

    public boolean expand(View r2) {
        return expand(r2, null);
    }

    public boolean expand(View r2, AppBarLayout r3) {
        return expand(r2, r3, false);
    }

    public boolean expand(View r2, AppBarLayout r3, boolean r4) {
        if (r2.getVisibility() == 0) goto L7;
        if (isExpanding() == true) goto L7;
    L8:
        this.searchBarAnimationHelper.startExpandAnimation(this, r2, r3, r4);
        return true;
    L7:
        if (isCollapsing() == true) goto L8;
        return false;
    }

    public AppBarLayout getAppBarLayoutParentIfExists() {
        ViewParent r1 = getParent();
    L3:
        if (r1 == null) goto L9;
        if ((r1 instanceof AppBarLayout) == true) goto L7;
        r1 = r1.getParent();
        goto L3
    L7:
        return (AppBarLayout) r1;
    L9:
        return null;
    }

    public View getCenterView() {
        return this.centerView;
    }

    public float getCompatElevation() {
        MaterialShapeDrawable r0 = this.backgroundShape;
        if (r0 == null) goto L7;
        return r0.getElevation();
    L7:
        return getElevation();
    }

    public float getCornerSize() {
        return this.backgroundShape.getTopLeftCornerResolvedSize();
    }

    public int getDefaultMarginVerticalResource() {
        return R.dimen.m3_searchbar_margin_vertical;
    }

    public int getDefaultNavigationIconResource() {
        return R.drawable.ic_search_black_24;
    }

    public int getEndSiblingViewId() {
        return this.endSiblingViewId;
    }

    public CharSequence getHint() {
        return this.textView.getHint();
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public int getMenuResId() {
        return this.menuResId;
    }

    public TextView getPlaceholderTextView() {
        return this.placeholderTextView;
    }

    public int getStartSiblingViewId() {
        return this.startSiblingViewId;
    }

    public int getStrokeColor() {
        return this.backgroundShape.getStrokeColor().getDefaultColor();
    }

    public float getStrokeWidth() {
        return this.backgroundShape.getStrokeWidth();
    }

    public CharSequence getText() {
        return this.textView.getText();
    }

    public boolean getTextCentered() {
        return this.textCentered;
    }

    public TextView getTextView() {
        return this.textView;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void inflateMenu(int r1) {
        super.inflateMenu(r1);
        this.menuResId = r1;
    }

    public boolean isCollapsing() {
        return this.searchBarAnimationHelper.isCollapsing();
    }

    public boolean isDefaultScrollFlagsEnabled() {
        return this.defaultScrollFlagsEnabled;
    }

    public boolean isExpanding() {
        return this.searchBarAnimationHelper.isExpanding();
    }

    public boolean isLiftOnScroll() {
        return this.liftOnScroll;
    }

    public boolean isOnLoadAnimationFadeInEnabled() {
        return this.searchBarAnimationHelper.isOnLoadAnimationFadeInEnabled();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.backgroundShape);
        setDefaultMargins();
        setOrClearDefaultScrollFlags();
        if (this.liftOnScroll == false) goto L6;
        addLiftOnScrollProgressListener();
        return;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeLiftOnScrollProgressListener();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r5) {
        super.onInitializeAccessibilityNodeInfo(r5);
        r5.setClassName(EditText.class.getCanonicalName());
        r5.setEditable(isEnabled());
        CharSequence r0 = getText();
        boolean r1 = TextUtils.isEmpty(r0);
        if (Build.VERSION.SDK_INT < 26) goto L5;
        r5.setHintText(getHint());
        r5.setShowingHintText(r1);
    L5:
        if (r1 == false) goto L7;
        r0 = getHint();
    L7:
        r5.setText(r0);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        View r12 = this.centerView;
        if (r12 == null) goto L5;
        layoutViewInCenter(r12);
    L5:
        setHandwritingBoundsInsets();
        if (this.textView != null) goto L8;
        return;
    L8:
        if (this.textCentered == false) goto L12;
        layoutTextViewCenterAvoidToolbarViewsAndPadding();
        return;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public void onMeasure(int r4, int r5) {
        int r0 = View.MeasureSpec.getSize(r4);
        int r1 = View.MeasureSpec.getMode(r4);
        int r2 = this.maxWidth;
        if (r2 < 0) goto L7;
        if (r0 <= r2) goto L7;
        r4 = View.MeasureSpec.makeMeasureSpec(r2, r1);
    L11:
        super.onMeasure(r4, r5);
        measureCenterView(r4, r5);
        return;
    L7:
        if (this.adaptiveMaxWidthEnabled == false) goto L11;
        int r22 = this.adaptiveMaxWidthParentBreakpoint;
        if (r0 <= r22) goto L11;
        r4 = View.MeasureSpec.makeMeasureSpec(Math.max(r22, Math.round(r0 * ADAPTIVE_MAX_WIDTH_PERCENT_AFTER_BREAKPOINT)), r1);
        goto L11
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r2);
        return;
    L6:
        SavedState r22 = (SavedState) r2;
        super.onRestoreInstanceState(r22.getSuperState());
        setText(r22.text);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r0 = new SavedState(super.onSaveInstanceState());
        CharSequence r2 = getText();
        if (r2 != null) goto L5;
        String r22 = null;
    L6:
        r0.text = r22;
        return r0;
    L5:
        r22 = r2.toString();
        goto L6
    }

    public boolean removeCollapseAnimationListener(AnimatorListenerAdapter r1) {
        return this.searchBarAnimationHelper.removeCollapseAnimationListener(r1);
    }

    public boolean removeExpandAnimationListener(AnimatorListenerAdapter r1) {
        return this.searchBarAnimationHelper.removeExpandAnimationListener(r1);
    }

    public boolean removeOnLoadAnimationCallback(OnLoadAnimationCallback r1) {
        return this.searchBarAnimationHelper.removeOnLoadAnimationCallback(r1);
    }

    public void setCenterView(View r2) {
        View r0 = this.centerView;
        if (r0 == null) goto L5;
        removeView(r0);
        this.centerView = null;
    L5:
        if (r2 == null) goto L8;
        addView(r2);
        return;
    }

    public void setDefaultScrollFlagsEnabled(boolean r1) {
        this.defaultScrollFlagsEnabled = r1;
        setOrClearDefaultScrollFlags();
    }

    @Override // android.view.View
    public void setElevation(float r1) {
        super.setElevation(r1);
        MaterialShapeDrawable r0 = this.backgroundShape;
        if (r0 == null) goto L6;
        r0.setElevation(r1);
        return;
    }

    public void setEndSiblingViewId(int r1) {
        this.endSiblingViewId = r1;
    }

    public void setHint(int r1) {
        this.textView.setHint(r1);
    }

    public void setHint(CharSequence r1) {
        this.textView.setHint(r1);
    }

    public void setLiftOnScroll(boolean r1) {
        this.liftOnScroll = r1;
        if (r1 == false) goto L6;
        addLiftOnScrollProgressListener();
        return;
    L6:
        removeLiftOnScrollProgressListener();
    }

    public void setMaxWidth(int r2) {
        if (this.maxWidth == r2) goto L6;
        this.maxWidth = r2;
        requestLayout();
        return;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable r1) {
        super.setNavigationIcon(maybeTintNavigationIcon(r1));
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationOnClickListener(View.OnClickListener r2) {
        if (this.forceDefaultNavigationOnClickListener == false) goto L5;
        return;
    L5:
        super.setNavigationOnClickListener(r2);
        if (r2 != null) goto L8;
        boolean r22 = true;
    L9:
        setNavigationIconDecorative(r22);
        return;
    L8:
        r22 = false;
        goto L9
    }

    public void setOnLoadAnimationFadeInEnabled(boolean r1) {
        this.searchBarAnimationHelper.setOnLoadAnimationFadeInEnabled(r1);
    }

    public void setPlaceholderText(String r1) {
        this.placeholderTextView.setText(r1);
    }

    public void setStartSiblingViewId(int r1) {
        this.startSiblingViewId = r1;
    }

    public void setStrokeColor(int r2) {
        if (getStrokeColor() == r2) goto L6;
        this.backgroundShape.setStrokeColor(ColorStateList.valueOf(r2));
        return;
    }

    public void setStrokeWidth(float r2) {
        if (getStrokeWidth() == r2) goto L6;
        this.backgroundShape.setStrokeWidth(r2);
        return;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence r1) {
    }

    public void setText(int r2) {
        this.textView.setText(r2);
        this.placeholderTextView.setText(r2);
    }

    public void setText(CharSequence r2) {
        this.textView.setText(r2);
        this.placeholderTextView.setText(r2);
    }

    public void setTextCentered(boolean r3) {
        this.textCentered = r3;
        TextView r0 = this.textView;
        if (r0 != null) goto L5;
        return;
    L5:
        FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) r0.getLayoutParams();
        if (r3 == false) goto L8;
        r02.gravity = 1;
        this.textView.setGravity(1);
    L9:
        this.textView.setLayoutParams(r02);
        this.placeholderTextView.setLayoutParams(r02);
        return;
    L8:
        r02.gravity = 0;
        this.textView.setGravity(0);
        goto L9
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence r1) {
    }

    public void startOnLoadAnimation() {
        post(new SearchBar$$ExternalSyntheticLambda0(this));
    }

    public void stopOnLoadAnimation() {
        this.searchBarAnimationHelper.stopOnLoadAnimation(this);
    }
}
