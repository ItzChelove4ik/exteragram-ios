package com.google.android.material.search;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.BackEventCompat;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import com.google.android.material.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.internal.FadeThroughDrawable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.motion.MaterialMainContainerBackHelper;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class SearchView extends FrameLayout implements CoordinatorLayout.AttachedBehavior, MaterialBackHandler {
    private static final int DEF_STYLE_RES = 0;
    private static final long TALKBACK_FOCUS_CHANGE_DELAY_MS = 100;
    private boolean animatedMenuItems;
    private boolean animatedNavigationIcon;
    private boolean autoShowKeyboard;
    private final boolean backHandlingEnabled;
    private final MaterialBackOrchestrator backOrchestrator;
    private final int backgroundColor;
    final View backgroundView;
    private Map<View, Integer> childImportantForAccessibilityMap;
    final ImageButton clearButton;
    private final boolean containedAnimationEnabled;
    final TouchObserverFrameLayout contentContainer;
    private TransitionState currentTransitionState;
    final View divider;
    private final boolean dividerVisible;
    final TextView dummyTextView;
    final Toolbar dummyToolbar;
    final EditText editText;
    private final ElevationOverlayProvider elevationOverlayProvider;
    final FrameLayout headerContainer;
    private final boolean layoutInflated;
    final ClippableRoundedCornerLayout rootView;
    final View scrim;
    private SearchBar searchBar;
    final TextView searchPrefix;
    private final SearchViewAnimationHelper searchViewAnimationHelper;
    private int softInputMode;
    final View statusBarSpacer;
    private boolean statusBarSpacerEnabledOverride;
    final LinearLayout textContainer;
    final MaterialToolbar toolbar;
    final FrameLayout toolbarContainer;
    private final ViewTreeObserver.OnTouchModeChangeListener touchModeChangeListener;
    private final Set<TransitionListener> transitionListeners;
    private boolean useWindowInsetsController;

    /* renamed from: com.google.android.material.search.SearchView$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewTreeObserver.OnTouchModeChangeListener {
        final /* synthetic */ SearchView this$0;

        public AnonymousClass1(SearchView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
        public void onTouchModeChanged(boolean r1) {
            if (r1 == false) goto L4;
            return;
        L4:
            if (this.this$0.hasWindowFocus() == true) goto L6;
            return;
        L6:
            if (this.this$0.isShowing() == false) goto L15;
            EditText r12 = this.this$0.editText;
            if (r12 != null) goto L10;
            return;
        L10:
            if (r12.isFocused() == true) goto L17;
            EditText r0 = this.this$0.editText;
            Objects.requireNonNull(r0);
            r0.post(new SearchView$1$$ExternalSyntheticLambda0(r0));
            return;
        L17:
            return;
        }
    }

    /* renamed from: com.google.android.material.search.SearchView$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        final /* synthetic */ SearchView this$0;

        public AnonymousClass2(SearchView r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            ImageButton r0 = this.this$0.clearButton;
            if (r1.length() <= 0) goto L5;
            int r12 = 0;
        L6:
            r0.setVisibility(r12);
            return;
        L5:
            r12 = 8;
            goto L6
        }
    }

    public static class Behavior extends CoordinatorLayout.Behavior<SearchView> {
        public Behavior() {
        }

        public Behavior(Context r1, AttributeSet r2) {
            super(r1, r2);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onDependentViewChanged(CoordinatorLayout r1, View r2, View r3) {
            return onDependentViewChanged(r1, (SearchView) r2, r3);
        }

        public boolean onDependentViewChanged(CoordinatorLayout r1, SearchView r2, View r3) {
            if (r2.isSetupWithSearchBar() == false) goto L5;
            return false;
        L5:
            if ((r3 instanceof SearchBar) == false) goto L10;
            r2.setupWithSearchBar((SearchBar) r3);
            return false;
        L10:
            return false;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        String text;
        int visibility;

        /* renamed from: com.google.android.material.search.SearchView$SavedState$1, reason: invalid class name */
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
            this.visibility = r1.readInt();
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeString(this.text);
            r1.writeInt(this.visibility);
        }
    }

    public interface TransitionListener {
        void onStateChanged(SearchView r1, TransitionState r2, TransitionState r3);
    }

    public enum TransitionState extends Enum<TransitionState> {
        private static final /* synthetic */ TransitionState[] $VALUES = null;
        public static final TransitionState HIDDEN = null;
        public static final TransitionState HIDING = null;
        public static final TransitionState SHOWING = null;
        public static final TransitionState SHOWN = null;

        private static /* synthetic */ TransitionState[] $values() {
            return new TransitionState[]{HIDING, HIDDEN, SHOWING, SHOWN};
        }

        static {
            HIDING = new TransitionState("HIDING", 0);
            HIDDEN = new TransitionState("HIDDEN", 1);
            SHOWING = new TransitionState("SHOWING", 2);
            SHOWN = new TransitionState("SHOWN", 3);
            $VALUES = $values();
        }

        TransitionState(String r1, int r2) {
        }

        public static TransitionState valueOf(String r1) {
            return (TransitionState) Enum.valueOf(TransitionState.class, r1);
        }

        public static TransitionState[] values() {
            return (TransitionState[]) $VALUES.clone();
        }
    }

    public static /* synthetic */ void $r8$lambda$FtRxa6ENwBmIB2T1T1NhMPVVspQ(SearchView r0, View r1) {
        r0.clearText();
        r0.requestFocusAndShowKeyboardIfNeeded();
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$G8B5rsxcXvRyDj7fvUDVNwFxJ5w(SearchView r3, View r4, WindowInsetsCompat r5, ViewUtils.RelativePadding r6) {
        boolean r42 = ViewUtils.isLayoutRtl(r3.toolbar);
        if (r42 == false) goto L5;
        int r0 = r6.end;
    L6:
        if (r42 == false) goto L8;
        int r43 = r6.start;
    L9:
        Insets r1 = r5.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
        int r02 = r0 + r1.left;
        int r44 = r43 + r1.right;
        r3.toolbar.setPadding(r02, r6.top, r44, r6.bottom);
        return r5;
    L8:
        r43 = r6.end;
        goto L9
    L5:
        r0 = r6.start;
        goto L6
    }

    /* renamed from: $r8$lambda$QJgI_sbnqkAOLmyBZDJ799L-dzU, reason: not valid java name */
    public static /* synthetic */ WindowInsetsCompat m1890$r8$lambda$QJgI_sbnqkAOLmyBZDJ799LdzU(SearchView r1, View r2, WindowInsetsCompat r3) {
        r1.getClass();
        int r22 = r3.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout()).top;
        r1.setUpStatusBarSpacer(r22);
        if (r1.statusBarSpacerEnabledOverride == true) goto L8;
        if (r22 <= 0) goto L6;
        boolean r23 = true;
    L7:
        r1.setStatusBarSpacerEnabledInternal(r23);
        goto L8
    L6:
        r23 = false;
    L8:
        return r3;
    }

    public static /* synthetic */ void $r8$lambda$RQfxeWRUAdTWUxjov0FKB36PJvc(SearchView r0, View r1) {
        r0.hide();
    }

    /* renamed from: $r8$lambda$V2RQ-pw_MdBeYrPTySzvJ6KI2P4, reason: not valid java name */
    public static /* synthetic */ void m1891$r8$lambda$V2RQpw_MdBeYrPTySzvJ6KI2P4(SearchView r0, View r1) {
        r0.show();
    }

    public static /* synthetic */ void $r8$lambda$WUeSk6ghHnMkVgC1iE4anOjbXG0(SearchView r0, View r1, boolean r2) {
        if (r2 == true) goto L5;
        ViewUtils.hideKeyboard(r1, r0.useWindowInsetsController);
        return;
    L5:
        r0.getClass();
    }

    /* renamed from: $r8$lambda$_tQAfRiHH28A-laRoXnUlvtpr60, reason: not valid java name */
    public static /* synthetic */ void m1892$r8$lambda$_tQAfRiHH28AlaRoXnUlvtpr60(SearchView r1) {
        r1.editText.clearFocus();
        ViewUtils.hideKeyboard(r1.editText, r1.useWindowInsetsController);
    }

    public static /* synthetic */ void $r8$lambda$kSdhGhsAFWaJsWajoyQZ6PYmWOM(SearchView r1) {
        if (r1.editText.requestFocus() == false) goto L6;
        r1.editText.sendAccessibilityEvent(8);
        return;
    }

    public static /* synthetic */ boolean $r8$lambda$kv0oFltobJWB4bU6YEBJTxq6BP0(SearchView r0, View r1, MotionEvent r2) {
        if (r0.isAdjustNothingSoftInputMode() == false) goto L7;
        r0.editText.clearFocus();
        return false;
    L7:
        return false;
    }

    /* renamed from: $r8$lambda$m5jol66nZ-NnGCtW5-81117_ung, reason: not valid java name */
    public static /* synthetic */ boolean m1893$r8$lambda$m5jol66nZNnGCtW581117_ung(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$pfxc05DoMwXdGzLboNjxhQUHZkY(SearchView r2) {
        if (r2.editText.requestFocus() == false) goto L5;
        r2.editText.sendAccessibilityEvent(8);
    L5:
        ViewUtils.showKeyboard(r2.editText, r2.useWindowInsetsController);
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$tBnLxbkKv_XuGC2_Ev51IggFIhU(ViewGroup.MarginLayoutParams r1, int r2, int r3, View r4, WindowInsetsCompat r5) {
        Insets r42 = r5.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
        r1.leftMargin = r2 + r42.left;
        r1.rightMargin = r3 + r42.right;
        return r5;
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Material3_SearchView;
    }

    public SearchView(Context r2) {
        this(r2, null);
    }

    public SearchView(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.materialSearchViewStyle);
    }

    public SearchView(Context r11, AttributeSet r12, int r13) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r11, r12, r13, r4), r12, r13);
        this.backOrchestrator = new MaterialBackOrchestrator(this);
        this.transitionListeners = new LinkedHashSet();
        this.softInputMode = 16;
        this.currentTransitionState = TransitionState.HIDDEN;
        this.touchModeChangeListener = new AnonymousClass1(this);
        Context r0 = getContext();
        TypedArray r122 = ThemeEnforcement.obtainStyledAttributes(r0, r12, R.styleable.SearchView, r13, r4, new int[0]);
        this.backgroundColor = r122.getColor(R.styleable.SearchView_backgroundTint, 0);
        int r132 = r122.getResourceId(R.styleable.SearchView_headerLayout, -1);
        int r1 = r122.getResourceId(R.styleable.SearchView_android_textAppearance, -1);
        String r2 = r122.getString(R.styleable.SearchView_android_text);
        String r3 = r122.getString(R.styleable.SearchView_android_hint);
        String r42 = r122.getString(R.styleable.SearchView_searchPrefixText);
        boolean r5 = r122.getBoolean(R.styleable.SearchView_useDrawerArrowDrawable, false);
        this.animatedNavigationIcon = r122.getBoolean(R.styleable.SearchView_animateNavigationIcon, true);
        this.animatedMenuItems = r122.getBoolean(R.styleable.SearchView_animateMenuItems, true);
        boolean r6 = r122.getBoolean(R.styleable.SearchView_hideNavigationIcon, false);
        this.autoShowKeyboard = r122.getBoolean(R.styleable.SearchView_autoShowKeyboard, true);
        this.backHandlingEnabled = r122.getBoolean(R.styleable.SearchView_backHandlingEnabled, true);
        this.dividerVisible = r122.getBoolean(R.styleable.SearchView_dividerVisible, true);
        boolean r8 = r122.getBoolean(R.styleable.SearchView_containedAnimationEnabled, false);
        this.containedAnimationEnabled = r8;
        r122.recycle();
        LayoutInflater.from(r0).inflate(R.layout.mtrl_search_view, this);
        this.layoutInflated = true;
        this.scrim = findViewById(R.id.open_search_view_scrim);
        this.rootView = (ClippableRoundedCornerLayout) findViewById(R.id.open_search_view_root);
        this.backgroundView = findViewById(R.id.open_search_view_background);
        this.statusBarSpacer = findViewById(R.id.open_search_view_status_bar_spacer);
        this.headerContainer = (FrameLayout) findViewById(R.id.open_search_view_header_container);
        this.toolbarContainer = (FrameLayout) findViewById(R.id.open_search_view_toolbar_container);
        this.toolbar = (MaterialToolbar) findViewById(R.id.open_search_view_toolbar);
        this.dummyToolbar = (Toolbar) findViewById(R.id.open_search_view_dummy_toolbar);
        this.dummyTextView = (TextView) findViewById(R.id.open_search_view_dummy_text_view);
        this.searchPrefix = (TextView) findViewById(R.id.open_search_view_search_prefix);
        this.textContainer = (LinearLayout) findViewById(R.id.open_search_view_text_container);
        this.editText = (EditText) findViewById(R.id.open_search_view_edit_text);
        this.clearButton = (ImageButton) findViewById(R.id.open_search_view_clear_button);
        this.divider = findViewById(R.id.open_search_view_divider);
        this.contentContainer = (TouchObserverFrameLayout) findViewById(R.id.open_search_view_content_container);
        this.searchViewAnimationHelper = new SearchViewAnimationHelper(r0, this, r8);
        this.elevationOverlayProvider = new ElevationOverlayProvider(r0);
        setUpRootView();
        setUpBackgroundViewElevationOverlay();
        setUpHeaderLayout(r132);
        setSearchPrefixText(r42);
        setUpEditText(r1, r2, r3);
        setUpBackButton(r5, r6);
        setUpClearButton();
        setUpDivider();
        setUpContentOnTouchListener();
        setUpInsetListeners();
        setToolbarTouchscreenBlocksFocus(false);
        if (r8 == false) goto L6;
        setUpDummyToolbarForContainedAnimation();
        setUpDummyTextForContainedAnimation(r1, r2, r3);
        return;
    }

    private Window getActivityWindow() {
        Activity r0 = ContextUtils.getActivity(getContext());
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getWindow();
    }

    private float getOverlayElevation() {
        SearchBar r0 = this.searchBar;
        if (r0 == null) goto L7;
        return r0.getCompatElevation();
    L7:
        return getResources().getDimension(R.dimen.m3_searchview_elevation);
    }

    private int getStatusBarHeight() {
        int r0 = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (r0 > 0) goto L5;
        return 0;
    L5:
        return getResources().getDimensionPixelSize(r0);
    }

    private boolean isHiddenOrHiding() {
        if (this.currentTransitionState.equals(TransitionState.HIDDEN) == false) goto L5;
        return true;
    L5:
        if (this.currentTransitionState.equals(TransitionState.HIDING) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isNavigationIconDrawerArrowDrawable(Toolbar r1) {
        return DrawableCompat.unwrap(r1.getNavigationIcon()) instanceof DrawerArrowDrawable;
    }

    private void setStatusBarSpacerEnabledInternal(boolean r1) {
        View r0 = this.statusBarSpacer;
        if (r1 == false) goto L5;
        int r12 = 0;
    L6:
        r0.setVisibility(r12);
        return;
    L5:
        r12 = 8;
        goto L6
    }

    private void setTransitionState(TransitionState r3, boolean r4) {
        if (this.currentTransitionState.equals(r3) == true) goto L20;
        if (r4 == false) goto L7;
        updateModalForAccessibility(r3);
    L7:
        TransitionState r42 = this.currentTransitionState;
        this.currentTransitionState = r3;
        Iterator r0 = new LinkedHashSet(this.transitionListeners).iterator();
    L9:
        if (r0.hasNext() == false) goto L11;
        ((TransitionListener) r0.next()).onStateChanged(this, r42, r3);
        goto L9
    L11:
        updateListeningForBackCallbacks(r3);
        SearchBar r2 = this.searchBar;
        if (r2 != null) goto L14;
        return;
    L14:
        if (r3 != TransitionState.HIDDEN) goto L19;
        r2.sendAccessibilityEvent(8);
        return;
    L19:
        return;
    }

    private void setUpBackButton(boolean r2, boolean r3) {
        MaterialToolbar r0 = this.toolbar;
        if (r3 == false) goto L6;
        r0.setNavigationIcon(null);
        return;
    L6:
        r0.setNavigationOnClickListener(new SearchView$$ExternalSyntheticLambda10(this));
        if (r2 == false) goto L10;
        DrawerArrowDrawable r22 = new DrawerArrowDrawable(getContext());
        r22.setColor(MaterialColors.getColor(this, R.attr.colorOnSurface));
        this.toolbar.setNavigationIcon(r22);
        return;
    }

    private void setUpBackgroundViewElevationOverlay() {
        setUpBackgroundViewElevationOverlay(getOverlayElevation());
    }

    private void setUpBackgroundViewElevationOverlay(float r3) {
        ElevationOverlayProvider r0 = this.elevationOverlayProvider;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.backgroundView == null) goto L10;
        int r32 = r0.compositeOverlayIfNeeded(this.backgroundColor, r3);
        this.backgroundView.setBackgroundColor(r32);
        return;
    }

    private void setUpClearButton() {
        this.clearButton.setOnClickListener(new SearchView$$ExternalSyntheticLambda13(this));
        this.editText.addTextChangedListener(new AnonymousClass2(this));
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void setUpContentOnTouchListener() {
        this.contentContainer.setOnTouchListener(new SearchView$$ExternalSyntheticLambda8(this));
    }

    private void setUpDivider() {
        View r0 = this.divider;
        if (this.dividerVisible == false) goto L5;
        int r1 = 0;
    L6:
        r0.setVisibility(r1);
        return;
    L5:
        r1 = 8;
        goto L6
    }

    private void setUpDividerInsetListener() {
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) this.divider.getLayoutParams();
        int r1 = r0.leftMargin;
        int r2 = r0.rightMargin;
        ViewCompat.setOnApplyWindowInsetsListener(this.divider, new SearchView$$ExternalSyntheticLambda1(r0, r1, r2));
    }

    private void setUpDummyTextForContainedAnimation(int r2, String r3, String r4) {
        if (r2 == (-1)) goto L5;
        TextViewCompat.setTextAppearance(this.dummyTextView, r2);
    L5:
        this.dummyTextView.setText(r3);
        this.dummyTextView.setHint(r4);
    }

    private void setUpDummyToolbarForContainedAnimation() {
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.dummyToolbar.getLayoutParams();
        r0.gravity = PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES;
        this.dummyToolbar.setLayoutParams(r0);
        this.dummyToolbar.setVisibility(4);
    }

    private void setUpEditText(int r2, String r3, String r4) {
        if (r2 == (-1)) goto L5;
        TextViewCompat.setTextAppearance(this.editText, r2);
    L5:
        this.editText.setText(r3);
        this.editText.setHint(r4);
        this.editText.setOnFocusChangeListener(new SearchView$$ExternalSyntheticLambda0(this));
    }

    private void setUpHeaderLayout(int r4) {
        if (r4 == (-1)) goto L6;
        addHeaderView(LayoutInflater.from(getContext()).inflate(r4, this.headerContainer, false));
        return;
    }

    private void setUpInsetListeners() {
        setUpToolbarInsetListener();
        setUpDividerInsetListener();
        setUpStatusBarSpacerInsetListener();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void setUpRootView() {
        this.rootView.setOnTouchListener(new SearchView$$ExternalSyntheticLambda7());
    }

    private void setUpStatusBarSpacer(int r2) {
        if (this.statusBarSpacer.getLayoutParams().height == r2) goto L6;
        this.statusBarSpacer.getLayoutParams().height = r2;
        this.statusBarSpacer.requestLayout();
        return;
    }

    private void setUpStatusBarSpacerInsetListener() {
        setUpStatusBarSpacer(getStatusBarHeight());
        ViewCompat.setOnApplyWindowInsetsListener(this.statusBarSpacer, new SearchView$$ExternalSyntheticLambda4(this));
    }

    private void setUpToolbarInsetListener() {
        ViewUtils.doOnApplyWindowInsets(this.toolbar, new SearchView$$ExternalSyntheticLambda3(this));
    }

    @SuppressLint({"InlinedApi"})
    private void updateChildImportantForAccessibility(ViewGroup r5, boolean r6) {
        int r0 = 0;
    L4:
        if (r0 >= r5.getChildCount()) goto L19;
        View r1 = r5.getChildAt(r0);
        if (r1 == this) goto L18;
        if (r1.findViewById(this.rootView.getId()) == null) goto L11;
        updateChildImportantForAccessibility((ViewGroup) r1, r6);
        goto L18
    L11:
        Map<View, Integer> r2 = this.childImportantForAccessibilityMap;
        if (r6 == true) goto L17;
        if (r2 == null) goto L18;
        if (r2.containsKey(r1) == false) goto L18;
        r1.setImportantForAccessibility(this.childImportantForAccessibilityMap.get(r1).intValue());
        goto L18
    L17:
        r2.put(r1, Integer.valueOf(r1.getImportantForAccessibility()));
        r1.setImportantForAccessibility(4);
    L18:
        r0 = r0 + 1;
        goto L4
    }

    private void updateListeningForBackCallbacks(TransitionState r2) {
        if (this.searchBar != null) goto L5;
        return;
    L5:
        if (this.backHandlingEnabled == true) goto L7;
        return;
    L7:
        if (r2.equals(TransitionState.SHOWN) == false) goto L11;
        this.backOrchestrator.startListeningForBackCallbacks();
        return;
    L11:
        if (r2.equals(TransitionState.HIDDEN) == false) goto L16;
        this.backOrchestrator.stopListeningForBackCallbacks();
        return;
    }

    private void updateModalForAccessibility(TransitionState r2) {
        if (r2 != TransitionState.SHOWN) goto L7;
        setModalForAccessibility(true);
        return;
    L7:
        if (r2 != TransitionState.HIDDEN) goto L10;
        setModalForAccessibility(false);
        return;
    }

    private void updateNavigationIconIfNeeded() {
        MaterialToolbar r0 = this.toolbar;
        if (r0 != null) goto L6;
        return;
    L6:
        if (isNavigationIconDrawerArrowDrawable(r0) == false) goto L8;
        return;
    L8:
        int r02 = getDefaultNavigationIconResource();
        if (this.searchBar != null) goto L12;
        this.toolbar.setNavigationIcon(r02);
        return;
    L12:
        Drawable r03 = DrawableCompat.wrap(AppCompatResources.getDrawable(getContext(), r02).mutate());
        if (this.toolbar.getNavigationIconTint() == null) goto L15;
        r03.setTint(this.toolbar.getNavigationIconTint().intValue());
    L15:
        DrawableCompat.setLayoutDirection(r03, getLayoutDirection());
        this.toolbar.setNavigationIcon(new FadeThroughDrawable(this.searchBar.getNavigationIcon(), r03));
        updateNavigationIconProgressIfNeeded();
    }

    private void updateNavigationIconProgressIfNeeded() {
        ImageButton r0 = ToolbarUtils.getNavigationIconButton(this.toolbar);
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.rootView.getVisibility() != 0) goto L8;
        int r3 = 1;
    L9:
        Drawable r02 = DrawableCompat.unwrap(r0.getDrawable());
        if ((r02 instanceof DrawerArrowDrawable) == false) goto L13;
        ((DrawerArrowDrawable) r02).setProgress(r3);
    L13:
        if ((r02 instanceof FadeThroughDrawable) == false) goto L16;
        ((FadeThroughDrawable) r02).setProgress(r3);
        return;
    L16:
        return;
    L8:
        r3 = 0;
        goto L9
    }

    public void addHeaderView(View r2) {
        this.headerContainer.addView(r2);
        this.headerContainer.setVisibility(0);
    }

    public void addTransitionListener(TransitionListener r1) {
        this.transitionListeners.add(r1);
    }

    @Override // android.view.ViewGroup
    public void addView(View r2, int r3, ViewGroup.LayoutParams r4) {
        if (this.layoutInflated == false) goto L6;
        this.contentContainer.addView(r2, r3, r4);
        return;
    L6:
        super.addView(r2, r3, r4);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void cancelBackProgress() {
        if (isHiddenOrHiding() == false) goto L5;
        return;
    L5:
        if (this.searchBar != null) goto L7;
        return;
    L7:
        if (Build.VERSION.SDK_INT < 34) goto L13;
        this.searchViewAnimationHelper.cancelBackProgress();
        return;
    }

    public void clearFocusAndHideKeyboard() {
        this.editText.post(new SearchView$$ExternalSyntheticLambda11(this));
    }

    public void clearText() {
        this.editText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.dummyTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    public MaterialMainContainerBackHelper getBackHelper() {
        return this.searchViewAnimationHelper.getBackHelper();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public CoordinatorLayout.Behavior<SearchView> getBehavior() {
        return new Behavior();
    }

    public TransitionState getCurrentTransitionState() {
        return this.currentTransitionState;
    }

    public int getDefaultNavigationIconResource() {
        return R.drawable.ic_arrow_back_black_24;
    }

    public EditText getEditText() {
        return this.editText;
    }

    public CharSequence getHint() {
        return this.editText.getHint();
    }

    public View getSearchContainer() {
        return this.rootView;
    }

    public TextView getSearchPrefix() {
        return this.searchPrefix;
    }

    public CharSequence getSearchPrefixText() {
        return this.searchPrefix.getText();
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public int getSoftInputMode() {
        return this.softInputMode;
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public Editable getText() {
        return this.editText.getText();
    }

    public Toolbar getToolbar() {
        return this.toolbar;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void handleBackInvoked() {
        if (isHiddenOrHiding() == false) goto L5;
        return;
    L5:
        BackEventCompat r0 = this.searchViewAnimationHelper.onHandleBackInvoked();
        if (Build.VERSION.SDK_INT >= 34) goto L8;
    L12:
        hide();
        return;
    L8:
        if (this.searchBar == null) goto L12;
        if (r0 == null) goto L12;
        this.searchViewAnimationHelper.finishBackProgress();
    }

    public void hide() {
        if (this.currentTransitionState.equals(TransitionState.HIDDEN) == false) goto L5;
        return;
    L5:
        if (this.currentTransitionState.equals(TransitionState.HIDING) == true) goto L19;
        SearchBar r0 = this.searchBar;
        if (r0 != null) goto L10;
    L16:
        this.searchViewAnimationHelper.hide();
        return;
    L10:
        if (r0.isAttachedToWindow() == false) goto L16;
        if (this.containedAnimationEnabled == true) goto L14;
        this.searchBar.setPlaceholderText(this.editText.getText().toString());
    L14:
        SearchBar r02 = this.searchBar;
        SearchViewAnimationHelper r2 = this.searchViewAnimationHelper;
        Objects.requireNonNull(r2);
        r02.post(new SearchView$$ExternalSyntheticLambda12(r2));
        return;
    }

    public void inflateMenu(int r1) {
        this.toolbar.inflateMenu(r1);
    }

    public boolean isAdjustNothingSoftInputMode() {
        if (this.softInputMode != 48) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isAnimatedNavigationIcon() {
        return this.animatedNavigationIcon;
    }

    public boolean isAutoShowKeyboard() {
        return this.autoShowKeyboard;
    }

    public boolean isMenuItemsAnimated() {
        return this.animatedMenuItems;
    }

    public boolean isSetupWithSearchBar() {
        if (this.searchBar == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isShowing() {
        if (this.currentTransitionState.equals(TransitionState.SHOWN) == false) goto L5;
        return true;
    L5:
        if (this.currentTransitionState.equals(TransitionState.SHOWING) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isUseWindowInsetsController() {
        return this.useWindowInsetsController;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
        TransitionState r0 = getCurrentTransitionState();
        updateModalForAccessibility(r0);
        updateListeningForBackCallbacks(r0);
        getViewTreeObserver().addOnTouchModeChangeListener(this.touchModeChangeListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setModalForAccessibility(false);
        this.backOrchestrator.stopListeningForBackCallbacks();
        getViewTreeObserver().removeOnTouchModeChangeListener(this.touchModeChangeListener);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        updateSoftInputMode();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r2);
        return;
    L6:
        SavedState r22 = (SavedState) r2;
        super.onRestoreInstanceState(r22.getSuperState());
        setText(r22.text);
        if (r22.visibility != 0) goto L9;
        boolean r23 = true;
    L10:
        setVisible(r23);
        return;
    L9:
        r23 = false;
        goto L10
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r0 = new SavedState(super.onSaveInstanceState());
        Editable r1 = getText();
        if (r1 != null) goto L5;
        String r12 = null;
    L6:
        r0.text = r12;
        r0.visibility = this.rootView.getVisibility();
        return r0;
    L5:
        r12 = r1.toString();
        goto L6
    }

    public void removeAllHeaderViews() {
        this.headerContainer.removeAllViews();
        this.headerContainer.setVisibility(8);
    }

    public void removeHeaderView(View r2) {
        this.headerContainer.removeView(r2);
        if (this.headerContainer.getChildCount() != 0) goto L6;
        this.headerContainer.setVisibility(8);
        return;
    }

    public void removeTransitionListener(TransitionListener r1) {
        this.transitionListeners.remove(r1);
    }

    public void requestFocusAndShowKeyboard() {
        this.editText.postDelayed(new SearchView$$ExternalSyntheticLambda2(this), TALKBACK_FOCUS_CHANGE_DELAY_MS);
    }

    public void requestFocusAndShowKeyboardIfNeeded() {
        if (this.autoShowKeyboard == false) goto L7;
        requestFocusAndShowKeyboard();
        return;
    L7:
        if (isInTouchMode() == true) goto L10;
        this.editText.postDelayed(new SearchView$$ExternalSyntheticLambda9(this), TALKBACK_FOCUS_CHANGE_DELAY_MS);
        return;
    }

    public void setAnimatedNavigationIcon(boolean r1) {
        this.animatedNavigationIcon = r1;
    }

    public void setAutoShowKeyboard(boolean r1) {
        this.autoShowKeyboard = r1;
    }

    @Override // android.view.View
    public void setElevation(float r1) {
        super.setElevation(r1);
        setUpBackgroundViewElevationOverlay(r1);
    }

    public void setHint(int r2) {
        this.editText.setHint(r2);
        this.dummyTextView.setHint(r2);
    }

    public void setHint(CharSequence r2) {
        this.editText.setHint(r2);
        this.dummyTextView.setHint(r2);
    }

    public void setMenuItemsAnimated(boolean r1) {
        this.animatedMenuItems = r1;
    }

    public void setModalForAccessibility(boolean r4) {
        ViewGroup r0 = (ViewGroup) getRootView();
        if (r4 == false) goto L5;
        this.childImportantForAccessibilityMap = new HashMap(r0.getChildCount());
    L5:
        updateChildImportantForAccessibility(r0, r4);
        if (r4 == true) goto L9;
        this.childImportantForAccessibilityMap = null;
        return;
    }

    public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener r1) {
        this.toolbar.setOnMenuItemClickListener(r1);
    }

    public void setSearchPrefixText(CharSequence r2) {
        this.searchPrefix.setText(r2);
        TextView r1 = this.searchPrefix;
        if (TextUtils.isEmpty(r2) == false) goto L5;
        int r22 = 8;
    L6:
        r1.setVisibility(r22);
        return;
    L5:
        r22 = 0;
        goto L6
    }

    public void setStatusBarSpacerEnabled(boolean r2) {
        this.statusBarSpacerEnabledOverride = true;
        setStatusBarSpacerEnabledInternal(r2);
    }

    public void setText(int r2) {
        this.editText.setText(r2);
        this.dummyTextView.setText(r2);
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public void setText(CharSequence r2) {
        this.editText.setText(r2);
        this.dummyTextView.setText(r2);
    }

    public void setToolbarTouchscreenBlocksFocus(boolean r1) {
        this.toolbar.setTouchscreenBlocksFocus(r1);
    }

    public void setTransitionState(TransitionState r2) {
        setTransitionState(r2, true);
    }

    public void setUseWindowInsetsController(boolean r1) {
        this.useWindowInsetsController = r1;
    }

    public void setVisible(boolean r6) {
        boolean r1 = true;
        if (this.rootView.getVisibility() != 0) goto L5;
        boolean r0 = true;
    L6:
        ClippableRoundedCornerLayout r3 = this.rootView;
        if (r6 == false) goto L9;
        int r4 = 0;
    L10:
        r3.setVisibility(r4);
        updateNavigationIconProgressIfNeeded();
        if (r6 == false) goto L13;
        TransitionState r32 = TransitionState.SHOWN;
    L14:
        if (r0 != r6) goto L17;
        r1 = false;
    L17:
        setTransitionState(r32, r1);
        return;
    L13:
        r32 = TransitionState.HIDDEN;
        goto L14
    L9:
        r4 = 8;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    public void setupWithSearchBar(SearchBar r3) {
        this.searchBar = r3;
        this.searchViewAnimationHelper.setSearchBar(r3);
        if (r3 == null) goto L7;
        r3.setOnClickListener(new SearchView$$ExternalSyntheticLambda5(this));
        if (Build.VERSION.SDK_INT < 34) goto L7;
        r3.setHandwritingDelegatorCallback(new SearchView$$ExternalSyntheticLambda6(this));     // Catch: LinkageError -> L9
        this.editText.setIsHandwritingDelegate(true);     // Catch: LinkageError -> L9
    L7:
        updateNavigationIconIfNeeded();
        setUpBackgroundViewElevationOverlay();
        updateListeningForBackCallbacks(getCurrentTransitionState());
    }

    public void show() {
        if (this.currentTransitionState.equals(TransitionState.SHOWN) == false) goto L5;
        return;
    L5:
        if (this.currentTransitionState.equals(TransitionState.SHOWING) == true) goto L10;
        this.searchViewAnimationHelper.show();
        return;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void startBackProgress(BackEventCompat r3) {
        if (isHiddenOrHiding() == true) goto L12;
        SearchBar r0 = this.searchBar;
        if (r0 != null) goto L8;
        return;
    L8:
        if (this.containedAnimationEnabled == true) goto L10;
        r0.setPlaceholderText(this.editText.getText().toString());
    L10:
        this.searchViewAnimationHelper.startBackProgress(r3);
        return;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void updateBackProgress(BackEventCompat r3) {
        if (isHiddenOrHiding() == false) goto L5;
        return;
    L5:
        if (this.searchBar != null) goto L7;
        return;
    L7:
        if (Build.VERSION.SDK_INT < 34) goto L13;
        this.searchViewAnimationHelper.updateBackProgress(r3);
        return;
    }

    public void updateSoftInputMode() {
        Window r0 = getActivityWindow();
        if (r0 == null) goto L6;
        this.softInputMode = r0.getAttributes().softInputMode;
        return;
    }
}
