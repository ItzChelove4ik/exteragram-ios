package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.ViewPropertyAnimatorCompatSet;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.ViewPropertyAnimatorUpdateListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class WindowDecorActionBar extends ActionBar implements ActionBarOverlayLayout.ActionBarVisibilityCallback {
    private static final Interpolator sHideInterpolator = null;
    private static final Interpolator sShowInterpolator = null;
    ActionModeImpl mActionMode;
    private Activity mActivity;
    ActionBarContainer mContainerView;
    boolean mContentAnimations;
    View mContentView;
    Context mContext;
    ActionBarContextView mContextView;
    private int mCurWindowVisibility;
    ViewPropertyAnimatorCompatSet mCurrentShowAnim;
    DecorToolbar mDecorToolbar;
    ActionMode mDeferredDestroyActionMode;
    ActionMode.Callback mDeferredModeDestroyCallback;
    private boolean mDisplayHomeAsUpSet;
    private boolean mHasEmbeddedTabs;
    boolean mHiddenByApp;
    boolean mHiddenBySystem;
    final ViewPropertyAnimatorListener mHideListener;
    boolean mHideOnContentScroll;
    private boolean mLastMenuVisibility;
    private ArrayList<Object> mMenuVisibilityListeners;
    private boolean mNowShowing;
    ActionBarOverlayLayout mOverlayLayout;
    private int mSavedTabPosition;
    private boolean mShowHideAnimationEnabled;
    final ViewPropertyAnimatorListener mShowListener;
    private boolean mShowingForMode;
    private ArrayList<Object> mTabs;
    private Context mThemedContext;
    final ViewPropertyAnimatorUpdateListener mUpdateListener;

    /* renamed from: androidx.appcompat.app.WindowDecorActionBar$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewPropertyAnimatorListenerAdapter {
        final /* synthetic */ WindowDecorActionBar this$0;

        public AnonymousClass1(WindowDecorActionBar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View r2) {
            WindowDecorActionBar r22 = this.this$0;
            if (r22.mContentAnimations == false) goto L7;
            View r23 = r22.mContentView;
            if (r23 == null) goto L7;
            r23.setTranslationY(0.0f);
            this.this$0.mContainerView.setTranslationY(0.0f);
        L7:
            this.this$0.mContainerView.setVisibility(8);
            this.this$0.mContainerView.setTransitioning(false);
            WindowDecorActionBar r24 = this.this$0;
            r24.mCurrentShowAnim = null;
            r24.completeDeferredDestroyActionMode();
            ActionBarOverlayLayout r1 = this.this$0.mOverlayLayout;
            if (r1 == null) goto L11;
            ViewCompat.requestApplyInsets(r1);
            return;
        }
    }

    /* renamed from: androidx.appcompat.app.WindowDecorActionBar$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewPropertyAnimatorListenerAdapter {
        final /* synthetic */ WindowDecorActionBar this$0;

        public AnonymousClass2(WindowDecorActionBar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View r1) {
            WindowDecorActionBar r0 = this.this$0;
            r0.mCurrentShowAnim = null;
            r0.mContainerView.requestLayout();
        }
    }

    /* renamed from: androidx.appcompat.app.WindowDecorActionBar$3, reason: invalid class name */
    public class AnonymousClass3 implements ViewPropertyAnimatorUpdateListener {
        final /* synthetic */ WindowDecorActionBar this$0;

        public AnonymousClass3(WindowDecorActionBar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorUpdateListener
        public void onAnimationUpdate(View r1) {
            ((View) this.this$0.mContainerView.getParent()).invalidate();
        }
    }

    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
        private final Context mActionModeContext;
        private ActionMode.Callback mCallback;
        private WeakReference<View> mCustomView;
        private final MenuBuilder mMenu;
        final /* synthetic */ WindowDecorActionBar this$0;

        public ActionModeImpl(WindowDecorActionBar r1, Context r2, ActionMode.Callback r3) {
            this.this$0 = r1;
            this.mActionModeContext = r2;
            this.mCallback = r3;
            MenuBuilder r12 = new MenuBuilder(r2).setDefaultShowAsAction(1);
            this.mMenu = r12;
            r12.setCallback(this);
        }

        public boolean dispatchOnCreate() {
            this.mMenu.stopDispatchingItemsChanged();
            boolean r0 = this.mCallback.onCreateActionMode(this, this.mMenu);     // Catch: Throwable -> L6
            this.mMenu.startDispatchingItemsChanged();
            return r0;
        L6:
            th = move-exception;
            this.mMenu.startDispatchingItemsChanged();
            throw th;
        }

        @Override // androidx.appcompat.view.ActionMode
        public void finish() {
            WindowDecorActionBar r0 = this.this$0;
            if (r0.mActionMode == this) goto L6;
            return;
        L6:
            if (WindowDecorActionBar.checkShowingFlags(r0.mHiddenByApp, r0.mHiddenBySystem, false) == true) goto L8;
            WindowDecorActionBar r02 = this.this$0;
            r02.mDeferredDestroyActionMode = this;
            r02.mDeferredModeDestroyCallback = this.mCallback;
        L9:
            this.mCallback = null;
            this.this$0.animateToMode(false);
            this.this$0.mContextView.closeMode();
            WindowDecorActionBar r1 = this.this$0;
            r1.mOverlayLayout.setHideOnContentScrollEnabled(r1.mHideOnContentScroll);
            this.this$0.mActionMode = null;
            return;
        L8:
            this.mCallback.onDestroyActionMode(this);
            goto L9
        }

        @Override // androidx.appcompat.view.ActionMode
        public View getCustomView() {
            WeakReference<View> r0 = this.mCustomView;
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.get();
        }

        @Override // androidx.appcompat.view.ActionMode
        public Menu getMenu() {
            return this.mMenu;
        }

        @Override // androidx.appcompat.view.ActionMode
        public MenuInflater getMenuInflater() {
            return new SupportMenuInflater(this.mActionModeContext);
        }

        @Override // androidx.appcompat.view.ActionMode
        public CharSequence getSubtitle() {
            return this.this$0.mContextView.getSubtitle();
        }

        @Override // androidx.appcompat.view.ActionMode
        public CharSequence getTitle() {
            return this.this$0.mContextView.getTitle();
        }

        @Override // androidx.appcompat.view.ActionMode
        public void invalidate() {
            if (this.this$0.mActionMode == this) goto L5;
            return;
        L5:
            this.mMenu.stopDispatchingItemsChanged();
            this.mCallback.onPrepareActionMode(this, this.mMenu);     // Catch: Throwable -> L9
            this.mMenu.startDispatchingItemsChanged();
            return;
        L9:
            th = move-exception;
            this.mMenu.startDispatchingItemsChanged();
            throw th;
        }

        @Override // androidx.appcompat.view.ActionMode
        public boolean isTitleOptional() {
            return this.this$0.mContextView.isTitleOptional();
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder r1, MenuItem r2) {
            ActionMode.Callback r12 = this.mCallback;
            if (r12 != null) goto L5;
            return false;
        L5:
            return r12.onActionItemClicked(this, r2);
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder r1) {
            if (this.mCallback != null) goto L5;
            return;
        L5:
            invalidate();
            this.this$0.mContextView.showOverflowMenu();
        }

        @Override // androidx.appcompat.view.ActionMode
        public void setCustomView(View r2) {
            this.this$0.mContextView.setCustomView(r2);
            this.mCustomView = new WeakReference(r2);
        }

        @Override // androidx.appcompat.view.ActionMode
        public void setSubtitle(int r2) {
            setSubtitle(this.this$0.mContext.getResources().getString(r2));
        }

        @Override // androidx.appcompat.view.ActionMode
        public void setSubtitle(CharSequence r1) {
            this.this$0.mContextView.setSubtitle(r1);
        }

        @Override // androidx.appcompat.view.ActionMode
        public void setTitle(int r2) {
            setTitle(this.this$0.mContext.getResources().getString(r2));
        }

        @Override // androidx.appcompat.view.ActionMode
        public void setTitle(CharSequence r1) {
            this.this$0.mContextView.setTitle(r1);
        }

        @Override // androidx.appcompat.view.ActionMode
        public void setTitleOptionalHint(boolean r1) {
            super.setTitleOptionalHint(r1);
            this.this$0.mContextView.setTitleOptional(r1);
        }
    }

    static {
        sHideInterpolator = new AccelerateInterpolator();
        sShowInterpolator = new DecelerateInterpolator();
    }

    public WindowDecorActionBar(Activity r2, boolean r3) {
        this.mTabs = new ArrayList();
        this.mSavedTabPosition = -1;
        this.mMenuVisibilityListeners = new ArrayList();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new AnonymousClass1(this);
        this.mShowListener = new AnonymousClass2(this);
        this.mUpdateListener = new AnonymousClass3(this);
        this.mActivity = r2;
        View r22 = r2.getWindow().getDecorView();
        init(r22);
        if (r3 == true) goto L6;
        this.mContentView = r22.findViewById(R.id.content);
        return;
    }

    public WindowDecorActionBar(Dialog r2) {
        this.mTabs = new ArrayList();
        this.mSavedTabPosition = -1;
        this.mMenuVisibilityListeners = new ArrayList();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new AnonymousClass1(this);
        this.mShowListener = new AnonymousClass2(this);
        this.mUpdateListener = new AnonymousClass3(this);
        init(r2.getWindow().getDecorView());
    }

    public static boolean checkShowingFlags(boolean r1, boolean r2, boolean r3) {
        if (r3 == false) goto L5;
        return true;
    L5:
        if (r1 == true) goto L9;
        if (r2 == true) goto L11;
        return true;
    L11:
        return false;
    L9:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private DecorToolbar getDecorToolbar(View r2) {
        if ((r2 instanceof DecorToolbar) == false) goto L7;
        return (DecorToolbar) r2;
    L7:
        if ((r2 instanceof Toolbar) == false) goto L11;
        return ((Toolbar) r2).getWrapper();
    L11:
        if (r2 == 0) goto L13;
        String r22 = r2.getClass().getSimpleName();
    L15:
        throw new IllegalStateException("Can't make a decor toolbar out of ".concat(r22));
    L13:
        r22 = "null";
        goto L15
    }

    private void hideForActionMode() {
        if (this.mShowingForMode == false) goto L9;
        this.mShowingForMode = false;
        ActionBarOverlayLayout r1 = this.mOverlayLayout;
        if (r1 == null) goto L7;
        r1.setShowingForActionMode(false);
    L7:
        updateVisibility(false);
        return;
    }

    private void init(View r6) {
        ActionBarOverlayLayout r0 = (ActionBarOverlayLayout) r6.findViewById(R$id.decor_content_parent);
        this.mOverlayLayout = r0;
        if (r0 == null) goto L5;
        r0.setActionBarVisibilityCallback(this);
    L5:
        this.mDecorToolbar = getDecorToolbar(r6.findViewById(R$id.action_bar));
        this.mContextView = (ActionBarContextView) r6.findViewById(R$id.action_context_bar);
        ActionBarContainer r62 = (ActionBarContainer) r6.findViewById(R$id.action_bar_container);
        this.mContainerView = r62;
        DecorToolbar r02 = this.mDecorToolbar;
        if (r02 != null) goto L8;
    L30:
        Segment$$ExternalSyntheticBUOutline1.m(getClass().getSimpleName().concat(" can only be used with a compatible window decor layout"));
        return;
    L8:
        if (this.mContextView == null) goto L30;
        if (r62 == null) goto L30;
        this.mContext = r02.getContext();
        if ((this.mDecorToolbar.getDisplayOptions() & 4) == 0) goto L13;
        boolean r63 = true;
    L14:
        if (r63 == false) goto L16;
        this.mDisplayHomeAsUpSet = true;
    L16:
        ActionBarPolicy r2 = ActionBarPolicy.get(this.mContext);
        if (r2.enableHomeButtonByDefault() == true) goto L21;
        if (r63 == true) goto L21;
        boolean r64 = false;
    L22:
        setHomeButtonEnabled(r64);
        setHasEmbeddedTabs(r2.hasEmbeddedTabs());
        TypedArray r65 = this.mContext.obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        if (r65.getBoolean(R$styleable.ActionBar_hideOnContentScroll, false) == false) goto L25;
        setHideOnContentScrollEnabled(true);
    L25:
        int r03 = r65.getDimensionPixelSize(R$styleable.ActionBar_elevation, 0);
        if (r03 == 0) goto L28;
        setElevation(r03);
    L28:
        r65.recycle();
        return;
    L21:
        r64 = true;
        goto L22
    L13:
        r63 = false;
        goto L14
    }

    private void setHasEmbeddedTabs(boolean r5) {
        this.mHasEmbeddedTabs = r5;
        if (r5 == true) goto L5;
        this.mDecorToolbar.setEmbeddedTabView(null);
        this.mContainerView.setTabContainer(null);
    L6:
        boolean r1 = false;
        if (getNavigationMode() != 2) goto L9;
        boolean r52 = true;
    L10:
        DecorToolbar r0 = this.mDecorToolbar;
        if (this.mHasEmbeddedTabs == true) goto L14;
        if (r52 == false) goto L14;
        boolean r3 = true;
    L15:
        r0.setCollapsible(r3);
        ActionBarOverlayLayout r02 = this.mOverlayLayout;
        if (this.mHasEmbeddedTabs == true) goto L19;
        if (r52 == false) goto L19;
        r1 = true;
    L19:
        r02.setHasNonEmbeddedTabs(r1);
        return;
    L14:
        r3 = false;
        goto L15
    L9:
        r52 = false;
        goto L10
    L5:
        this.mContainerView.setTabContainer(null);
        this.mDecorToolbar.setEmbeddedTabView(null);
        goto L6
    }

    private boolean shouldAnimateContextView() {
        return this.mContainerView.isLaidOut();
    }

    private void showForActionMode() {
        if (this.mShowingForMode == true) goto L9;
        this.mShowingForMode = true;
        ActionBarOverlayLayout r1 = this.mOverlayLayout;
        if (r1 == null) goto L7;
        r1.setShowingForActionMode(true);
    L7:
        updateVisibility(false);
        return;
    }

    private void updateVisibility(boolean r4) {
        boolean r0 = checkShowingFlags(this.mHiddenByApp, this.mHiddenBySystem, this.mShowingForMode);
        boolean r1 = this.mNowShowing;
        if (r0 == false) goto L7;
        if (r1 == true) goto L10;
        this.mNowShowing = true;
        doShow(r4);
        return;
    L10:
        return;
    L7:
        if (r1 == false) goto L11;
        this.mNowShowing = false;
        doHide(r4);
        return;
    }

    public void animateToMode(boolean r10) {
        if (r10 == false) goto L4;
        showForActionMode();
    L6:
        if (shouldAnimateContextView() == false) goto L13;
        DecorToolbar r0 = this.mDecorToolbar;
        if (r10 == false) goto L10;
        ViewPropertyAnimatorCompat r102 = r0.setupAnimatorToVisibility(4, 100);
        ViewPropertyAnimatorCompat r9 = this.mContextView.setupAnimatorToVisibility(0, 200);
    L11:
        ViewPropertyAnimatorCompatSet r02 = new ViewPropertyAnimatorCompatSet();
        r02.playSequentially(r102, r9);
        r02.start();
        return;
    L10:
        ViewPropertyAnimatorCompat r103 = r0.setupAnimatorToVisibility(0, 200);
        r102 = this.mContextView.setupAnimatorToVisibility(8, 100);
        r9 = r103;
        goto L11
    L13:
        DecorToolbar r03 = this.mDecorToolbar;
        if (r10 == false) goto L17;
        r03.setVisibility(4);
        this.mContextView.setVisibility(0);
        return;
    L17:
        r03.setVisibility(0);
        this.mContextView.setVisibility(8);
        return;
    L4:
        hideForActionMode();
        goto L6
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean collapseActionView() {
        DecorToolbar r0 = this.mDecorToolbar;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.hasExpandedActionView() == false) goto L10;
        this.mDecorToolbar.collapseActionView();
        return true;
    L10:
        return false;
    }

    public void completeDeferredDestroyActionMode() {
        ActionMode.Callback r0 = this.mDeferredModeDestroyCallback;
        if (r0 == null) goto L6;
        r0.onDestroyActionMode(this.mDeferredDestroyActionMode);
        this.mDeferredDestroyActionMode = null;
        this.mDeferredModeDestroyCallback = null;
        return;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void dispatchMenuVisibilityChanged(boolean r2) {
        if (r2 == this.mLastMenuVisibility) goto L10;
        this.mLastMenuVisibility = r2;
        if (this.mMenuVisibilityListeners.size() > 0) goto L8;
        return;
    L8:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.mMenuVisibilityListeners.get(0));
        throw null;
    }

    public void doHide(boolean r5) {
        ViewPropertyAnimatorCompatSet r0 = this.mCurrentShowAnim;
        if (r0 == null) goto L6;
        r0.cancel();
    L6:
        if (this.mCurWindowVisibility == 0) goto L8;
    L20:
        this.mHideListener.onAnimationEnd(null);
        return;
    L8:
        if (this.mShowHideAnimationEnabled == true) goto L10;
        if (r5 == false) goto L20;
    L10:
        this.mContainerView.setAlpha(1.0f);
        this.mContainerView.setTransitioning(true);
        ViewPropertyAnimatorCompatSet r02 = new ViewPropertyAnimatorCompatSet();
        float r2 = -this.mContainerView.getHeight();
        if (r5 == false) goto L13;
        this.mContainerView.getLocationInWindow(new int[]{0, 0});
        r2 = r2 - r5[1];
    L13:
        ViewPropertyAnimatorCompat r52 = ViewCompat.animate(this.mContainerView).translationY(r2);
        r52.setUpdateListener(this.mUpdateListener);
        r02.play(r52);
        if (this.mContentAnimations == false) goto L18;
        View r53 = this.mContentView;
        if (r53 == null) goto L18;
        r02.play(ViewCompat.animate(r53).translationY(r2));
    L18:
        r02.setInterpolator(sHideInterpolator);
        r02.setDuration(250);
        r02.setListener(this.mHideListener);
        this.mCurrentShowAnim = r02;
        r02.start();
    }

    public void doShow(boolean r5) {
        ViewPropertyAnimatorCompatSet r0 = this.mCurrentShowAnim;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.mContainerView.setVisibility(0);
        if (this.mCurWindowVisibility == 0) goto L8;
    L19:
        this.mContainerView.setAlpha(1.0f);
        this.mContainerView.setTranslationY(0.0f);
        if (this.mContentAnimations == false) goto L24;
        View r52 = this.mContentView;
        if (r52 == null) goto L24;
        r52.setTranslationY(0.0f);
    L24:
        this.mShowListener.onAnimationEnd(null);
    L25:
        ActionBarOverlayLayout r4 = this.mOverlayLayout;
        if (r4 == null) goto L29;
        ViewCompat.requestApplyInsets(r4);
        return;
    L29:
        return;
    L8:
        if (this.mShowHideAnimationEnabled == true) goto L10;
        if (r5 == false) goto L19;
    L10:
        this.mContainerView.setTranslationY(0.0f);
        float r02 = -this.mContainerView.getHeight();
        if (r5 == false) goto L13;
        this.mContainerView.getLocationInWindow(new int[]{0, 0});
        r02 = r02 - r5[1];
    L13:
        this.mContainerView.setTranslationY(r02);
        ViewPropertyAnimatorCompatSet r53 = new ViewPropertyAnimatorCompatSet();
        ViewPropertyAnimatorCompat r1 = ViewCompat.animate(this.mContainerView).translationY(0.0f);
        r1.setUpdateListener(this.mUpdateListener);
        r53.play(r1);
        if (this.mContentAnimations == false) goto L18;
        View r12 = this.mContentView;
        if (r12 == null) goto L18;
        r12.setTranslationY(r02);
        r53.play(ViewCompat.animate(this.mContentView).translationY(0.0f));
    L18:
        r53.setInterpolator(sShowInterpolator);
        r53.setDuration(250);
        r53.setListener(this.mShowListener);
        this.mCurrentShowAnim = r53;
        r53.start();
        goto L25
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void enableContentAnimations(boolean r1) {
        this.mContentAnimations = r1;
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    public int getNavigationMode() {
        return this.mDecorToolbar.getNavigationMode();
    }

    @Override // androidx.appcompat.app.ActionBar
    public Context getThemedContext() {
        if (this.mThemedContext != null) goto L9;
        TypedValue r0 = new TypedValue();
        this.mContext.getTheme().resolveAttribute(R$attr.actionBarWidgetTheme, r0, true);
        int r02 = r0.resourceId;
        if (r02 == 0) goto L7;
        this.mThemedContext = new ContextThemeWrapper(this.mContext, r02);
        goto L9
    L7:
        this.mThemedContext = this.mContext;
    L9:
        return this.mThemedContext;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void hideForSystem() {
        if (this.mHiddenBySystem == true) goto L6;
        this.mHiddenBySystem = true;
        updateVisibility(true);
        return;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void onConfigurationChanged(Configuration r1) {
        setHasEmbeddedTabs(ActionBarPolicy.get(this.mContext).hasEmbeddedTabs());
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void onContentScrollStarted() {
        ViewPropertyAnimatorCompatSet r0 = this.mCurrentShowAnim;
        if (r0 == null) goto L6;
        r0.cancel();
        this.mCurrentShowAnim = null;
        return;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void onContentScrollStopped() {
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean onKeyShortcut(int r4, KeyEvent r5) {
        ActionModeImpl r3 = this.mActionMode;
        if (r3 != null) goto L5;
        return false;
    L5:
        Menu r32 = r3.getMenu();
        if (r32 == null) goto L16;
        if (r5 == null) goto L9;
        int r1 = r5.getDeviceId();
    L10:
        boolean r2 = true;
        if (KeyCharacterMap.load(r1).getKeyboardType() != 1) goto L14;
        r2 = false;
    L14:
        r32.setQwertyMode(r2);
        return r32.performShortcut(r4, r5, 0);
    L9:
        r1 = -1;
        goto L10
    L16:
        return false;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void onWindowVisibilityChanged(int r1) {
        this.mCurWindowVisibility = r1;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDefaultDisplayHomeAsUpEnabled(boolean r2) {
        if (this.mDisplayHomeAsUpSet == true) goto L6;
        setDisplayHomeAsUpEnabled(r2);
        return;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayHomeAsUpEnabled(boolean r2) {
        if (r2 == false) goto L5;
        int r22 = 4;
    L6:
        setDisplayOptions(r22, 4);
        return;
    L5:
        r22 = 0;
        goto L6
    }

    public void setDisplayOptions(int r3, int r4) {
        int r0 = this.mDecorToolbar.getDisplayOptions();
        if ((r4 & 4) == 0) goto L5;
        this.mDisplayHomeAsUpSet = true;
    L5:
        this.mDecorToolbar.setDisplayOptions((r3 & r4) | ((~r4) & r0));
    }

    public void setElevation(float r1) {
        ViewCompat.setElevation(this.mContainerView, r1);
    }

    public void setHideOnContentScrollEnabled(boolean r2) {
        if (r2 == true) goto L4;
    L8:
        this.mHideOnContentScroll = r2;
        this.mOverlayLayout.setHideOnContentScrollEnabled(r2);
        return;
    L4:
        if (this.mOverlayLayout.isInOverlayMode() == true) goto L8;
        Segment$$ExternalSyntheticBUOutline1.m("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setHomeAsUpIndicator(int r1) {
        this.mDecorToolbar.setNavigationIcon(r1);
    }

    public void setHomeButtonEnabled(boolean r1) {
        this.mDecorToolbar.setHomeButtonEnabled(r1);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setShowHideAnimationEnabled(boolean r1) {
        this.mShowHideAnimationEnabled = r1;
        if (r1 == true) goto L8;
        ViewPropertyAnimatorCompatSet r0 = this.mCurrentShowAnim;
        if (r0 == null) goto L9;
        r0.cancel();
        return;
    L9:
        return;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setSubtitle(CharSequence r1) {
        this.mDecorToolbar.setSubtitle(r1);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setTitle(CharSequence r1) {
        this.mDecorToolbar.setTitle(r1);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setWindowTitle(CharSequence r1) {
        this.mDecorToolbar.setWindowTitle(r1);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void showForSystem() {
        if (this.mHiddenBySystem == false) goto L6;
        this.mHiddenBySystem = false;
        updateVisibility(true);
        return;
    }

    @Override // androidx.appcompat.app.ActionBar
    public ActionMode startActionMode(ActionMode.Callback r3) {
        ActionModeImpl r0 = this.mActionMode;
        if (r0 == null) goto L5;
        r0.finish();
    L5:
        this.mOverlayLayout.setHideOnContentScrollEnabled(false);
        this.mContextView.killMode();
        ActionModeImpl r02 = new ActionModeImpl(this, this.mContextView.getContext(), r3);
        if (r02.dispatchOnCreate() == false) goto L9;
        this.mActionMode = r02;
        r02.invalidate();
        this.mContextView.initForMode(r02);
        animateToMode(true);
        return r02;
    L9:
        return null;
    }
}
