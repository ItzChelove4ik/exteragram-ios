package com.google.android.material.navigation;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.activity.BackEventCompat;
import androidx.appcompat.R$attr;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import androidx.drawerlayout.widget.DrawerLayout;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuPresenter;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.WindowUtils;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.motion.MaterialSideContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeableDelegate;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.ref.WeakReference;
import java.util.Objects;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public class NavigationView extends ScrimInsetsFrameLayout implements MaterialBackHandler {
    private static final int[] CHECKED_STATE_SET = null;
    private static final int DEF_STYLE_RES = 0;
    private static final int[] DISABLED_STATE_SET = null;
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    private final DrawerLayout.DrawerListener backDrawerListener;
    private final MaterialBackOrchestrator backOrchestrator;
    private boolean bottomInsetScrimEnabled;
    private int drawerLayoutCornerSize;
    private final boolean drawerLayoutCornerSizeBackAnimationEnabled;
    private final int drawerLayoutCornerSizeBackAnimationMax;
    private boolean endInsetScrimEnabled;
    private final FocusDrawerListener focusDrawerListener;
    private ShapeAppearanceModel itemShapeAppearanceModel;
    OnNavigationItemSelectedListener listener;
    private final int maxWidth;
    private final NavigationMenu menu;
    private MenuInflater menuInflater;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private final NavigationMenuPresenter presenter;
    private final ShapeableDelegate shapeableDelegate;
    private final MaterialSideContainerBackHelper sideContainerBackHelper;
    private boolean startInsetScrimEnabled;
    private final int[] tmpLocation;
    private boolean topInsetScrimEnabled;

    /* renamed from: com.google.android.material.navigation.NavigationView$1, reason: invalid class name */
    public class AnonymousClass1 extends DrawerLayout.SimpleDrawerListener {
        final /* synthetic */ NavigationView this$0;

        public AnonymousClass1(NavigationView r1) {
            this.this$0 = r1;
        }

        public void onDrawerClosed(View r2) {
            NavigationView r0 = this.this$0;
            if (r2 != r0) goto L6;
            NavigationView.access$000(r0).stopListeningForBackCallbacks();
            NavigationView.access$100(this.this$0);
            return;
        }

        public void onDrawerOpened(View r2) {
            NavigationView r1 = this.this$0;
            if (r2 != r1) goto L6;
            MaterialBackOrchestrator r12 = NavigationView.access$000(r1);
            Objects.requireNonNull(r12);
            r2.post(new NavigationView$1$$ExternalSyntheticLambda0(r12));
            return;
        }
    }

    /* renamed from: com.google.android.material.navigation.NavigationView$2, reason: invalid class name */
    public class AnonymousClass2 implements MenuBuilder.Callback {
        final /* synthetic */ NavigationView this$0;

        public AnonymousClass2(NavigationView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder r1, MenuItem r2) {
            OnNavigationItemSelectedListener r0 = this.this$0.listener;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.onNavigationItemSelected(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder r1) {
        }
    }

    /* renamed from: com.google.android.material.navigation.NavigationView$3, reason: invalid class name */
    public class AnonymousClass3 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ NavigationView this$0;

        public AnonymousClass3(NavigationView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            NavigationView r0 = this.this$0;
            r0.getLocationOnScreen(NavigationView.access$300(r0));
            boolean r1 = true;
            if (NavigationView.access$300(this.this$0)[1] != 0) goto L5;
            boolean r02 = true;
        L6:
            NavigationView.access$400(this.this$0).setBehindStatusBar(r02);
            NavigationView r3 = this.this$0;
            if (r02 == true) goto L9;
        L11:
            boolean r03 = false;
        L12:
            r3.setDrawTopInsetForeground(r03);
            if (this.this$0.getLayoutDirection() != 1) goto L15;
            boolean r04 = true;
        L17:
            if (NavigationView.access$300(this.this$0)[0] != 0) goto L19;
        L22:
            boolean r32 = true;
        L23:
            NavigationView r4 = this.this$0;
            if (r32 == false) goto L32;
            if (r04 == false) goto L30;
            if (r4.isEndInsetScrimEnabled() == false) goto L32;
        L31:
            boolean r33 = true;
        L33:
            r4.setDrawLeftInsetForeground(r33);
            Activity r34 = ContextUtils.getActivity(this.this$0.getContext());
            if (r34 == null) goto L69;
            Rect r42 = WindowUtils.getCurrentWindowBounds(r34);
            if ((r42.height() - this.this$0.getHeight()) != NavigationView.access$300(this.this$0)[1]) goto L38;
            boolean r5 = true;
        L40:
            if (Color.alpha(EdgeToEdgeUtils.getNavigationBarColor(r34.getWindow())) == 0) goto L42;
            boolean r35 = true;
        L43:
            NavigationView r6 = this.this$0;
            if (r5 == false) goto L49;
            if (r35 == false) goto L49;
            if (r6.isBottomInsetScrimEnabled() == false) goto L49;
            boolean r36 = true;
        L50:
            r6.setDrawBottomInsetForeground(r36);
            if (r42.width() != NavigationView.access$300(this.this$0)[0]) goto L53;
        L56:
            boolean r37 = true;
        L57:
            NavigationView r7 = this.this$0;
            if (r37 == false) goto L66;
            if (r04 == false) goto L64;
            if (r7.isStartInsetScrimEnabled() == false) goto L66;
        L67:
            r7.setDrawRightInsetForeground(r1);
            return;
        L64:
            if (r7.isEndInsetScrimEnabled() == true) goto L67;
        L66:
            r1 = false;
            goto L67
        L53:
            if ((r42.width() - this.this$0.getWidth()) == NavigationView.access$300(this.this$0)[0]) goto L56;
            r37 = false;
        L49:
            r36 = false;
            goto L50
        L42:
            r35 = false;
            goto L43
        L38:
            r5 = false;
            goto L40
        L69:
            return;
        L30:
            if (r4.isStartInsetScrimEnabled() == true) goto L31;
        L32:
            r33 = false;
            goto L33
        L19:
            if ((NavigationView.access$300(this.this$0)[0] + this.this$0.getWidth()) == 0) goto L22;
            r32 = false;
            goto L23
        L15:
            r04 = false;
            goto L17
        L9:
            if (r3.isTopInsetScrimEnabled() == false) goto L11;
            r03 = true;
            goto L12
        L5:
            r02 = false;
            goto L6
        }
    }

    public class FocusDrawerListener extends DrawerLayout.SimpleDrawerListener {
        private WeakReference<View> lastFocusedView;
        final /* synthetic */ NavigationView this$0;

        private FocusDrawerListener(NavigationView r1) {
            this.this$0 = r1;
            this.lastFocusedView = null;
        }

        public /* synthetic */ FocusDrawerListener(NavigationView r1, AnonymousClass1 r2) {
            this(r1);
        }

        private View getCurrentFocus() {
            if (this.this$0.hasFocus() == false) goto L5;
            return null;
        L5:
            Activity r2 = ContextUtils.getActivity(this.this$0.getContext());
            if (r2 != null) goto L9;
            return null;
        L9:
            return r2.getCurrentFocus();
        }

        private void saveLastFocusedView() {
            if (this.lastFocusedView != null) goto L6;
            this.lastFocusedView = new WeakReference(getCurrentFocus());
            return;
        }

        private boolean shouldAutoRequestFocus(View r1) {
            if (r1.isAttachedToWindow() == true) goto L5;
            return false;
        L5:
            if (r1.isInTouchMode() == true) goto L10;
            return true;
        L10:
            return false;
        }

        public void clearLastFocusedView() {
            this.lastFocusedView = null;
        }

        public void onDrawerClosed(View r2) {
            if (r2 != this.this$0) goto L17;
            WeakReference<View> r22 = this.lastFocusedView;
            if (r22 != null) goto L8;
            return;
        L8:
            View r23 = r22.get();
            if (r23 != null) goto L11;
        L15:
            clearLastFocusedView();
            return;
        L11:
            if (shouldAutoRequestFocus(r23) == false) goto L15;
            r23.requestFocus();
            clearLastFocusedView();
            return;
        }

        public void onDrawerOpened(View r2) {
            if (r2 != this.this$0) goto L10;
            saveLastFocusedView();
            if (shouldAutoRequestFocus(r2) == false) goto L9;
            r2.requestFocus();
            return;
        L9:
            return;
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener
        public void onDrawerSlide(View r1, float r2) {
            if (r1 == this.this$0) goto L5;
            return;
        L5:
            r1.getParent();
            saveLastFocusedView();
        }
    }

    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(MenuItem r1);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        public Bundle menuState;

        /* renamed from: com.google.android.material.navigation.NavigationView$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.ClassLoaderCreator<SavedState> {
            public AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r2) {
                return new SavedState(r2, null);
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

        public SavedState(Parcel r1, ClassLoader r2) {
            super(r1, r2);
            this.menuState = r1.readBundle(r2);
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeBundle(this.menuState);
        }
    }

    /* renamed from: $r8$lambda$AHFKJHmjzrzMxPjc--xxWf1dl_U, reason: not valid java name */
    public static /* synthetic */ void m1885$r8$lambda$AHFKJHmjzrzMxPjcxxWf1dl_U(NavigationView r0, Canvas r1) {
        super.dispatchDraw(r1);
    }

    static {
        CHECKED_STATE_SET = new int[]{R.attr.state_checked};
        DISABLED_STATE_SET = new int[]{-16842910};
        DEF_STYLE_RES = com.google.android.material.R.style.Widget_Design_NavigationView;
    }

    public NavigationView(Context r2) {
        this(r2, null);
    }

    public NavigationView(Context r2, AttributeSet r3) {
        this(r2, r3, com.google.android.material.R.attr.navigationViewStyle);
    }

    public NavigationView(Context r17, AttributeSet r18, int r19) {
        int r5 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r17, r18, r19, r5), r18, r19);
        NavigationMenuPresenter r7 = new NavigationMenuPresenter();
        this.presenter = r7;
        this.tmpLocation = new int[2];
        this.topInsetScrimEnabled = true;
        this.bottomInsetScrimEnabled = true;
        this.startInsetScrimEnabled = true;
        this.endInsetScrimEnabled = true;
        this.drawerLayoutCornerSize = 0;
        this.shapeableDelegate = ShapeableDelegate.create(this);
        this.sideContainerBackHelper = new MaterialSideContainerBackHelper(this);
        this.backOrchestrator = new MaterialBackOrchestrator(this);
        this.backDrawerListener = new AnonymousClass1(this);
        this.focusDrawerListener = new FocusDrawerListener(this, null);
        Context r1 = getContext();
        NavigationMenu r11 = new NavigationMenu(r1);
        this.menu = r11;
        TintTypedArray r3 = ThemeEnforcement.obtainTintedStyledAttributes(r1, r18, com.google.android.material.R.styleable.NavigationView, r19, r5, new int[0]);
        if (r3.hasValue(com.google.android.material.R.styleable.NavigationView_android_background) == false) goto L5;
        setBackground(r3.getDrawable(com.google.android.material.R.styleable.NavigationView_android_background));
    L5:
        int r6 = r3.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_drawerLayoutCornerSize, 0);
        this.drawerLayoutCornerSize = r6;
        if (r6 != 0) goto L8;
        boolean r62 = true;
    L9:
        this.drawerLayoutCornerSizeBackAnimationEnabled = r62;
        this.drawerLayoutCornerSizeBackAnimationMax = getResources().getDimensionPixelSize(com.google.android.material.R.dimen.m3_navigation_drawer_layout_corner_size);
        Drawable r63 = getBackground();
        ColorStateList r12 = DrawableUtils.getColorStateListOrNull(r63);
        if (r63 == null) goto L12;
        if (r12 != null) goto L12;
    L17:
        if (r3.hasValue(com.google.android.material.R.styleable.NavigationView_elevation) == false) goto L19;
        setElevation(r3.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_elevation, 0));
    L19:
        setFitsSystemWindows(r3.getBoolean(com.google.android.material.R.styleable.NavigationView_android_fitsSystemWindows, false));
        this.maxWidth = r3.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_android_maxWidth, 0);
        if (r3.hasValue(com.google.android.material.R.styleable.NavigationView_subheaderColor) == false) goto L22;
        ColorStateList r2 = r3.getColorStateList(com.google.android.material.R.styleable.NavigationView_subheaderColor);
    L24:
        if (r3.hasValue(com.google.android.material.R.styleable.NavigationView_subheaderTextAppearance) == false) goto L26;
        int r4 = r3.getResourceId(com.google.android.material.R.styleable.NavigationView_subheaderTextAppearance, 0);
    L28:
        if (r4 != 0) goto L32;
        if (r2 != null) goto L32;
        r2 = createDefaultColorStateList(R.attr.textColorSecondary);
    L32:
        if (r3.hasValue(com.google.android.material.R.styleable.NavigationView_itemIconTint) == false) goto L34;
        ColorStateList r52 = r3.getColorStateList(com.google.android.material.R.styleable.NavigationView_itemIconTint);
    L36:
        if (r3.hasValue(com.google.android.material.R.styleable.NavigationView_itemTextAppearance) == false) goto L38;
        int r64 = r3.getResourceId(com.google.android.material.R.styleable.NavigationView_itemTextAppearance, 0);
    L39:
        boolean r122 = r3.getBoolean(com.google.android.material.R.styleable.NavigationView_itemTextAppearanceActiveBoldEnabled, true);
        if (r3.hasValue(com.google.android.material.R.styleable.NavigationView_itemIconSize) == false) goto L43;
        setItemIconSize(r3.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_itemIconSize, 0));
    L43:
        if (r3.hasValue(com.google.android.material.R.styleable.NavigationView_itemTextColor) == false) goto L45;
        ColorStateList r13 = r3.getColorStateList(com.google.android.material.R.styleable.NavigationView_itemTextColor);
    L46:
        if (r64 != 0) goto L49;
        if (r13 != null) goto L49;
        r13 = createDefaultColorStateList(R.attr.textColorPrimary);
    L49:
        Drawable r14 = r3.getDrawable(com.google.android.material.R.styleable.NavigationView_itemBackground);
        if (r14 != null) goto L60;
        if (hasShapeAppearance(r3) == false) goto L60;
        r14 = createDefaultItemBackground(r3);
        ColorStateList r15 = MaterialResources.getColorStateList(r1, r3, com.google.android.material.R.styleable.NavigationView_itemRippleColor);
        if (r15 == null) goto L60;
        RippleDrawable r9 = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(r15), null, createDefaultItemDrawable(r3, null));
        FocusRingDrawable r8 = FocusRingDrawable.layer(r1, r9);
        if (r8 == null) goto L58;
        r8.setFocusRingShapeAppearance(this.itemShapeAppearanceModel);
    L58:
        r7.setItemForeground(r9);
    L60:
        if (r3.hasValue(com.google.android.material.R.styleable.NavigationView_itemHorizontalPadding) == false) goto L62;
        int r92 = 0;
        setItemHorizontalPadding(r3.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_itemHorizontalPadding, 0));
    L64:
        if (r3.hasValue(com.google.android.material.R.styleable.NavigationView_itemVerticalPadding) == false) goto L66;
        setItemVerticalPadding(r3.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_itemVerticalPadding, r92));
    L66:
        setDividerInsetStart(r3.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_dividerInsetStart, r92));
        setDividerInsetEnd(r3.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_dividerInsetEnd, r92));
        setSubheaderInsetStart(r3.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_subheaderInsetStart, r92));
        setSubheaderInsetEnd(r3.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_subheaderInsetEnd, r92));
        setTopInsetScrimEnabled(r3.getBoolean(com.google.android.material.R.styleable.NavigationView_topInsetScrimEnabled, this.topInsetScrimEnabled));
        setBottomInsetScrimEnabled(r3.getBoolean(com.google.android.material.R.styleable.NavigationView_bottomInsetScrimEnabled, this.bottomInsetScrimEnabled));
        setStartInsetScrimEnabled(r3.getBoolean(com.google.android.material.R.styleable.NavigationView_startInsetScrimEnabled, this.startInsetScrimEnabled));
        setEndInsetScrimEnabled(r3.getBoolean(com.google.android.material.R.styleable.NavigationView_endInsetScrimEnabled, this.endInsetScrimEnabled));
        int r82 = r3.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_itemIconPadding, 0);
        setItemMaxLines(r3.getInt(com.google.android.material.R.styleable.NavigationView_itemMaxLines, 1));
        r11.setCallback(new AnonymousClass2(this));
        r7.setId(1);
        r7.initForMenu(r1, r11);
        if (r4 == 0) goto L69;
        r7.setSubheaderTextAppearance(r4);
    L69:
        r7.setSubheaderColor(r2);
        r7.setItemIconTintList(r52);
        r7.setOverScrollMode(getOverScrollMode());
        if (r64 == 0) goto L72;
        r7.setItemTextAppearance(r64);
    L72:
        r7.setItemTextAppearanceActiveBoldEnabled(r122);
        r7.setItemTextColor(r13);
        r7.setItemBackground(r14);
        r7.setItemIconPadding(r82);
        r11.addMenuPresenter(r7);
        addView((View) r7.getMenuView(this));
        if (r3.hasValue(com.google.android.material.R.styleable.NavigationView_menu) == false) goto L75;
        int r93 = 0;
        inflateMenu(r3.getResourceId(com.google.android.material.R.styleable.NavigationView_menu, 0));
    L77:
        if (r3.hasValue(com.google.android.material.R.styleable.NavigationView_headerLayout) == false) goto L79;
        inflateHeaderView(r3.getResourceId(com.google.android.material.R.styleable.NavigationView_headerLayout, r93));
    L79:
        r3.recycle();
        setupInsetScrimsListener();
        return;
    L75:
        r93 = 0;
        goto L77
    L62:
        r92 = 0;
        goto L64
    L45:
        r13 = null;
        goto L46
    L38:
        r64 = 0;
        goto L39
    L34:
        r52 = createDefaultColorStateList(R.attr.textColorSecondary);
        goto L36
    L26:
        r4 = 0;
        goto L28
    L22:
        r2 = null;
    L12:
        MaterialShapeDrawable r42 = new MaterialShapeDrawable(ShapeAppearanceModel.builder(r1, r18, r19, r5).build());
        if (r12 == null) goto L15;
        r42.setFillColor(r12);
    L15:
        r42.initializeElevationOverlay(r1);
        setBackground(r42);
        goto L17
    L8:
        r62 = false;
        goto L9
    }

    public static /* synthetic */ MaterialBackOrchestrator access$000(NavigationView r0) {
        return r0.backOrchestrator;
    }

    public static /* synthetic */ void access$100(NavigationView r0) {
        r0.maybeClearCornerSizeAnimationForDrawerLayout();
    }

    public static /* synthetic */ int[] access$300(NavigationView r0) {
        return r0.tmpLocation;
    }

    public static /* synthetic */ NavigationMenuPresenter access$400(NavigationView r0) {
        return r0.presenter;
    }

    private ColorStateList createDefaultColorStateList(int r6) {
        TypedValue r0 = new TypedValue();
        if (getContext().getTheme().resolveAttribute(r6, r0, true) == true) goto L5;
        return null;
    L5:
        ColorStateList r62 = AppCompatResources.getColorStateList(getContext(), r0.resourceId);
        if (getContext().getTheme().resolveAttribute(R$attr.colorPrimary, r0, true) == true) goto L8;
        return null;
    L8:
        int r5 = r0.data;
        int r02 = r62.getDefaultColor();
        int[] r2 = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{r2, CHECKED_STATE_SET, FrameLayout.EMPTY_STATE_SET}, new int[]{r62.getColorForState(r2, r02), r5, r02});
    }

    private Drawable createDefaultItemBackground(TintTypedArray r3) {
        return createDefaultItemDrawable(r3, MaterialResources.getColorStateList(getContext(), r3, com.google.android.material.R.styleable.NavigationView_itemShapeFillColor));
    }

    private Drawable createDefaultItemDrawable(TintTypedArray r9, ColorStateList r10) {
        int r0 = r9.getResourceId(com.google.android.material.R.styleable.NavigationView_itemShapeAppearance, 0);
        int r2 = r9.getResourceId(com.google.android.material.R.styleable.NavigationView_itemShapeAppearanceOverlay, 0);
        this.itemShapeAppearanceModel = ShapeAppearanceModel.builder(getContext(), r0, r2).build();
        MaterialShapeDrawable r3 = new MaterialShapeDrawable(this.itemShapeAppearanceModel);
        r3.setFillColor(r10);
        return new InsetDrawable(r3, r9.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_itemShapeInsetStart, 0), r9.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_itemShapeInsetTop, 0), r9.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_itemShapeInsetEnd, 0), r9.getDimensionPixelSize(com.google.android.material.R.styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater != null) goto L6;
        this.menuInflater = new SupportMenuInflater(getContext());
    L6:
        return this.menuInflater;
    }

    private boolean hasShapeAppearance(TintTypedArray r1) {
        if (r1.hasValue(com.google.android.material.R.styleable.NavigationView_itemShapeAppearance) == false) goto L5;
        return true;
    L5:
        if (r1.hasValue(com.google.android.material.R.styleable.NavigationView_itemShapeAppearanceOverlay) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private void maybeClearCornerSizeAnimationForDrawerLayout() {
        if (this.drawerLayoutCornerSizeBackAnimationEnabled == true) goto L5;
        return;
    L5:
        if (this.drawerLayoutCornerSize == 0) goto L9;
        this.drawerLayoutCornerSize = 0;
        maybeUpdateCornerSizeForDrawerLayout(getWidth(), getHeight());
        return;
    }

    private void maybeUpdateCornerSizeForDrawerLayout(int r1, int r2) {
        getParent();
    }

    private Pair<DrawerLayout, Object> requireDrawerLayoutParent() {
        getParent();
        getLayoutParams();
        throw new IllegalStateException("NavigationView back progress requires the direct parent view to be a DrawerLayout.");
    }

    private void setupInsetScrimsListener() {
        this.onGlobalLayoutListener = new AnonymousClass3(this);
        getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
    }

    public void addHeaderView(View r1) {
        this.presenter.addHeaderView(r1);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void cancelBackProgress() {
        requireDrawerLayoutParent();
        this.sideContainerBackHelper.cancelBackProgress();
        maybeClearCornerSizeAnimationForDrawerLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r3) {
        this.shapeableDelegate.maybeClip(r3, new NavigationView$$ExternalSyntheticLambda0(this));
    }

    public MaterialSideContainerBackHelper getBackHelper() {
        return this.sideContainerBackHelper;
    }

    public MenuItem getCheckedItem() {
        return this.presenter.getCheckedItem();
    }

    public int getDividerInsetEnd() {
        return this.presenter.getDividerInsetEnd();
    }

    public int getDividerInsetStart() {
        return this.presenter.getDividerInsetStart();
    }

    public int getHeaderCount() {
        return this.presenter.getHeaderCount();
    }

    public View getHeaderView(int r1) {
        return this.presenter.getHeaderView(r1);
    }

    public Drawable getItemBackground() {
        return this.presenter.getItemBackground();
    }

    public int getItemHorizontalPadding() {
        return this.presenter.getItemHorizontalPadding();
    }

    public int getItemIconPadding() {
        return this.presenter.getItemIconPadding();
    }

    public ColorStateList getItemIconTintList() {
        return this.presenter.getItemTintList();
    }

    public int getItemMaxLines() {
        return this.presenter.getItemMaxLines();
    }

    public ColorStateList getItemTextColor() {
        return this.presenter.getItemTextColor();
    }

    public int getItemVerticalPadding() {
        return this.presenter.getItemVerticalPadding();
    }

    public Menu getMenu() {
        return this.menu;
    }

    public int getSubheaderInsetEnd() {
        return this.presenter.getSubheaderInsetEnd();
    }

    public int getSubheaderInsetStart() {
        return this.presenter.getSubheaderInsetStart();
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void handleBackInvoked() {
        Pair<DrawerLayout, Object> r0 = requireDrawerLayoutParent();
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.first);
        if (this.sideContainerBackHelper.onHandleBackInvoked() != null) goto L5;
        throw null;
    L5:
        if (Build.VERSION.SDK_INT < 34) goto L10;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.second);
        throw null;
    L10:
        throw null;
    }

    public View inflateHeaderView(int r1) {
        return this.presenter.inflateHeaderView(r1);
    }

    public void inflateMenu(int r3) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(r3, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(false);
    }

    public boolean isBottomInsetScrimEnabled() {
        return this.bottomInsetScrimEnabled;
    }

    public boolean isEndInsetScrimEnabled() {
        return this.endInsetScrimEnabled;
    }

    public boolean isStartInsetScrimEnabled() {
        return this.startInsetScrimEnabled;
    }

    public boolean isTopInsetScrimEnabled() {
        return this.topInsetScrimEnabled;
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
        getParent();
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        getParent();
        this.backOrchestrator.stopListeningForBackCallbacks();
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout
    public void onInsetsChanged(WindowInsetsCompat r1) {
        this.presenter.dispatchApplyWindowInsets(r1);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        int r0 = View.MeasureSpec.getMode(r4);
        if (r0 == Integer.MIN_VALUE) goto L7;
        if (r0 != 0) goto L8;
        r4 = View.MeasureSpec.makeMeasureSpec(this.maxWidth, TLObject.FLAG_30);
    L8:
        super.onMeasure(r4, r5);
        return;
    L7:
        r4 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(r4), this.maxWidth), TLObject.FLAG_30);
        goto L8
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r2);
        return;
    L6:
        SavedState r22 = (SavedState) r2;
        super.onRestoreInstanceState(r22.getSuperState());
        this.menu.restorePresenterStates(r22.menuState);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r1 = new SavedState(super.onSaveInstanceState());
        Bundle r0 = new Bundle();
        r1.menuState = r0;
        this.menu.savePresenterStates(r0);
        return r1;
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        maybeUpdateCornerSizeForDrawerLayout(r1, r2);
    }

    public void removeHeaderView(View r1) {
        this.presenter.removeHeaderView(r1);
    }

    public void setBottomInsetScrimEnabled(boolean r1) {
        this.bottomInsetScrimEnabled = r1;
    }

    public void setCheckedItem(int r2) {
        MenuItem r22 = this.menu.findItem(r2);
        if (r22 == null) goto L6;
        this.presenter.setCheckedItem((MenuItemImpl) r22);
        return;
    }

    public void setCheckedItem(MenuItem r2) {
        MenuItem r22 = this.menu.findItem(r2.getItemId());
        if (r22 == null) goto L6;
        this.presenter.setCheckedItem((MenuItemImpl) r22);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }

    public void setDividerInsetEnd(int r1) {
        this.presenter.setDividerInsetEnd(r1);
    }

    public void setDividerInsetStart(int r1) {
        this.presenter.setDividerInsetStart(r1);
    }

    @Override // android.view.View
    public void setElevation(float r1) {
        super.setElevation(r1);
        MaterialShapeUtils.setElevation(this, r1);
    }

    public void setEndInsetScrimEnabled(boolean r1) {
        this.endInsetScrimEnabled = r1;
    }

    public void setForceCompatClippingEnabled(boolean r2) {
        this.shapeableDelegate.setForceCompatClippingEnabled(this, r2);
    }

    public void setItemBackground(Drawable r1) {
        this.presenter.setItemBackground(r1);
    }

    public void setItemBackgroundResource(int r2) {
        setItemBackground(getContext().getDrawable(r2));
    }

    public void setItemHorizontalPadding(int r1) {
        this.presenter.setItemHorizontalPadding(r1);
    }

    public void setItemHorizontalPaddingResource(int r2) {
        this.presenter.setItemHorizontalPadding(getResources().getDimensionPixelSize(r2));
    }

    public void setItemIconPadding(int r1) {
        this.presenter.setItemIconPadding(r1);
    }

    public void setItemIconPaddingResource(int r2) {
        this.presenter.setItemIconPadding(getResources().getDimensionPixelSize(r2));
    }

    public void setItemIconSize(int r1) {
        this.presenter.setItemIconSize(r1);
    }

    public void setItemIconTintList(ColorStateList r1) {
        this.presenter.setItemIconTintList(r1);
    }

    public void setItemMaxLines(int r1) {
        this.presenter.setItemMaxLines(r1);
    }

    public void setItemTextAppearance(int r1) {
        this.presenter.setItemTextAppearance(r1);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean r1) {
        this.presenter.setItemTextAppearanceActiveBoldEnabled(r1);
    }

    public void setItemTextColor(ColorStateList r1) {
        this.presenter.setItemTextColor(r1);
    }

    public void setItemVerticalPadding(int r1) {
        this.presenter.setItemVerticalPadding(r1);
    }

    public void setItemVerticalPaddingResource(int r2) {
        this.presenter.setItemVerticalPadding(getResources().getDimensionPixelSize(r2));
    }

    public void setNavigationItemSelectedListener(OnNavigationItemSelectedListener r1) {
        this.listener = r1;
    }

    @Override // android.view.View
    public void setOverScrollMode(int r1) {
        super.setOverScrollMode(r1);
        NavigationMenuPresenter r0 = this.presenter;
        if (r0 == null) goto L6;
        r0.setOverScrollMode(r1);
        return;
    }

    public void setStartInsetScrimEnabled(boolean r1) {
        this.startInsetScrimEnabled = r1;
    }

    public void setSubheaderInsetEnd(int r1) {
        this.presenter.setSubheaderInsetEnd(r1);
    }

    public void setSubheaderInsetStart(int r1) {
        this.presenter.setSubheaderInsetStart(r1);
    }

    public void setTopInsetScrimEnabled(boolean r1) {
        this.topInsetScrimEnabled = r1;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void startBackProgress(BackEventCompat r1) {
        requireDrawerLayoutParent();
        this.sideContainerBackHelper.startBackProgress(r1);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void updateBackProgress(BackEventCompat r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(requireDrawerLayoutParent().second);
        throw null;
    }
}
