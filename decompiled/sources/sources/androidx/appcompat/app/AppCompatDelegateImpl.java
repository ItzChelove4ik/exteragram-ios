package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$style;
import androidx.appcompat.R$styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.StandaloneActionMode;
import androidx.appcompat.view.SupportActionModeWrapper;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.WindowCallbackWrapper;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.DecorContentParent;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.appcompat.widget.ViewUtils;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.os.LocaleListCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.PopupWindowCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import kotlin.time.DurationKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.webrtc.MediaStreamTrack;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes3.dex */
class AppCompatDelegateImpl extends AppCompatDelegate implements MenuBuilder.Callback, LayoutInflater.Factory2 {
    private static final boolean IS_PRE_LOLLIPOP = false;
    private static final boolean sCanReturnDifferentContext = false;
    private static final SimpleArrayMap<String, Integer> sLocalNightModes = null;
    private static final int[] sWindowBackgroundStyleable = null;
    ActionBar mActionBar;
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    private int mActivityHandlesConfigFlags;
    private boolean mActivityHandlesConfigFlagsChecked;
    final AppCompatCallback mAppCompatCallback;
    private AppCompatViewInflater mAppCompatViewInflater;
    private AppCompatWindowCallback mAppCompatWindowCallback;
    private AutoNightModeManager mAutoBatteryNightModeManager;
    private AutoNightModeManager mAutoTimeNightModeManager;
    private OnBackInvokedCallback mBackCallback;
    private boolean mBaseContextAttached;
    private boolean mClosingActionMenu;
    final Context mContext;
    private boolean mCreated;
    private DecorContentParent mDecorContentParent;
    boolean mDestroyed;
    private OnBackInvokedDispatcher mDispatcher;
    private Configuration mEffectiveConfiguration;
    private boolean mEnableDefaultActionBarUp;
    ViewPropertyAnimatorCompat mFadeAnim;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    private boolean mHandleNativeActionModes;
    boolean mHasActionBar;
    final Object mHost;
    int mInvalidatePanelMenuFeatures;
    boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable;
    boolean mIsFloating;
    private LayoutIncludeDetector mLayoutIncludeDetector;
    private int mLocalNightMode;
    private boolean mLongPressBackDown;
    MenuInflater mMenuInflater;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    private PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private int mThemeResId;
    private CharSequence mTitle;
    private TextView mTitleView;
    Window mWindow;
    boolean mWindowNoTitle;

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        public AnonymousClass2(AppCompatDelegateImpl r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl r0 = this.this$0;
            if ((r0.mInvalidatePanelMenuFeatures & 1) == 0) goto L5;
            r0.doInvalidatePanelMenu(0);
        L5:
            AppCompatDelegateImpl r02 = this.this$0;
            if ((r02.mInvalidatePanelMenuFeatures & 4096) == 0) goto L8;
            r02.doInvalidatePanelMenu(108);
        L8:
            AppCompatDelegateImpl r3 = this.this$0;
            r3.mInvalidatePanelMenuPosted = false;
            r3.mInvalidatePanelMenuFeatures = 0;
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$3, reason: invalid class name */
    public class AnonymousClass3 implements OnApplyWindowInsetsListener {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        public AnonymousClass3(AppCompatDelegateImpl r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r4, WindowInsetsCompat r5) {
            int r0 = r5.getSystemWindowInsetTop();
            int r3 = this.this$0.updateStatusGuard(r5, null);
            if (r0 == r3) goto L6;
            r5 = r5.replaceSystemWindowInsets(r5.getSystemWindowInsetLeft(), r3, r5.getSystemWindowInsetRight(), r5.getSystemWindowInsetBottom());
        L6:
            return ViewCompat.onApplyWindowInsets(r4, r5);
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$5, reason: invalid class name */
    public class AnonymousClass5 implements ContentFrameLayout.OnAttachListener {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        public AnonymousClass5(AppCompatDelegateImpl r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.OnAttachListener
        public void onAttachedFromWindow() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.OnAttachListener
        public void onDetachedFromWindow() {
            this.this$0.dismissPopups();
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$6, reason: invalid class name */
    public class AnonymousClass6 implements Runnable {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$6$1, reason: invalid class name */
        public class AnonymousClass1 extends ViewPropertyAnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass6 this$1;

            public AnonymousClass1(AnonymousClass6 r1) {
                this.this$1 = r1;
            }

            @Override // androidx.core.view.ViewPropertyAnimatorListener
            public void onAnimationEnd(View r2) {
                this.this$1.this$0.mActionModeView.setAlpha(1.0f);
                this.this$1.this$0.mFadeAnim.setListener(null);
                this.this$1.this$0.mFadeAnim = null;
            }

            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void onAnimationStart(View r1) {
                this.this$1.this$0.mActionModeView.setVisibility(0);
            }
        }

        public AnonymousClass6(AppCompatDelegateImpl r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl r0 = this.this$0;
            r0.mActionModePopup.showAtLocation(r0.mActionModeView, 55, 0, 0);
            this.this$0.endOnGoingFadeAnimation();
            boolean r02 = this.this$0.shouldAnimateActionModeView();
            AppCompatDelegateImpl r1 = this.this$0;
            if (r02 == false) goto L6;
            r1.mActionModeView.setAlpha(0.0f);
            AppCompatDelegateImpl r03 = this.this$0;
            r03.mFadeAnim = ViewCompat.animate(r03.mActionModeView).alpha(1.0f);
            this.this$0.mFadeAnim.setListener(new AnonymousClass1(this));
            return;
        L6:
            r1.mActionModeView.setAlpha(1.0f);
            this.this$0.mActionModeView.setVisibility(0);
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$7, reason: invalid class name */
    public class AnonymousClass7 extends ViewPropertyAnimatorListenerAdapter {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        public AnonymousClass7(AppCompatDelegateImpl r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View r2) {
            this.this$0.mActionModeView.setAlpha(1.0f);
            this.this$0.mFadeAnim.setListener(null);
            this.this$0.mFadeAnim = null;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart(View r2) {
            this.this$0.mActionModeView.setVisibility(0);
            if ((this.this$0.mActionModeView.getParent() instanceof View) == false) goto L6;
            ViewCompat.requestApplyInsets((View) this.this$0.mActionModeView.getParent());
            return;
        }
    }

    public class ActionBarDrawableToggleImpl implements ActionBarDrawerToggle$Delegate {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        public ActionBarDrawableToggleImpl(AppCompatDelegateImpl r1) {
            this.this$0 = r1;
        }
    }

    public interface ActionBarMenuCallback {
        View onCreatePanelView(int r1);

        boolean onPreparePanel(int r1);
    }

    public final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        public ActionMenuPresenterCallback(AppCompatDelegateImpl r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder r1, boolean r2) {
            this.this$0.checkCloseActionMenu(r1);
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder r2) {
            Window.Callback r1 = this.this$0.getWindowCallback();
            if (r1 == null) goto L7;
            r1.onMenuOpened(108, r2);
            return true;
        L7:
            return true;
        }
    }

    public class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
        private ActionMode.Callback mWrapped;
        final /* synthetic */ AppCompatDelegateImpl this$0;

        /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9$1, reason: invalid class name */
        public class AnonymousClass1 extends ViewPropertyAnimatorListenerAdapter {
            final /* synthetic */ ActionModeCallbackWrapperV9 this$1;

            public AnonymousClass1(ActionModeCallbackWrapperV9 r1) {
                this.this$1 = r1;
            }

            @Override // androidx.core.view.ViewPropertyAnimatorListener
            public void onAnimationEnd(View r2) {
                this.this$1.this$0.mActionModeView.setVisibility(8);
                AppCompatDelegateImpl r22 = this.this$1.this$0;
                PopupWindow r0 = r22.mActionModePopup;
                if (r0 == null) goto L6;
                r0.dismiss();
            L8:
                this.this$1.this$0.mActionModeView.killMode();
                this.this$1.this$0.mFadeAnim.setListener(null);
                AppCompatDelegateImpl r1 = this.this$1.this$0;
                r1.mFadeAnim = null;
                ViewCompat.requestApplyInsets(r1.mSubDecor);
                return;
            L6:
                if ((r22.mActionModeView.getParent() instanceof View) == false) goto L8;
                ViewCompat.requestApplyInsets((View) this.this$1.this$0.mActionModeView.getParent());
                goto L8
            }
        }

        public ActionModeCallbackWrapperV9(AppCompatDelegateImpl r1, ActionMode.Callback r2) {
            this.this$0 = r1;
            this.mWrapped = r2;
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r1, MenuItem r2) {
            return this.mWrapped.onActionItemClicked(r1, r2);
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r1, Menu r2) {
            return this.mWrapped.onCreateActionMode(r1, r2);
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r3) {
            this.mWrapped.onDestroyActionMode(r3);
            AppCompatDelegateImpl r32 = this.this$0;
            if (r32.mActionModePopup == null) goto L5;
            r32.mWindow.getDecorView().removeCallbacks(this.this$0.mShowActionModePopup);
        L5:
            AppCompatDelegateImpl r33 = this.this$0;
            if (r33.mActionModeView == null) goto L8;
            r33.endOnGoingFadeAnimation();
            AppCompatDelegateImpl r34 = this.this$0;
            r34.mFadeAnim = ViewCompat.animate(r34.mActionModeView).alpha(0.0f);
            this.this$0.mFadeAnim.setListener(new AnonymousClass1(this));
        L8:
            AppCompatDelegateImpl r35 = this.this$0;
            AppCompatCallback r0 = r35.mAppCompatCallback;
            if (r0 == null) goto L11;
            r0.onSupportActionModeFinished(r35.mActionMode);
        L11:
            AppCompatDelegateImpl r36 = this.this$0;
            r36.mActionMode = null;
            ViewCompat.requestApplyInsets(r36.mSubDecor);
            this.this$0.updateBackInvokedCallbackState();
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r2, Menu r3) {
            ViewCompat.requestApplyInsets(this.this$0.mSubDecor);
            return this.mWrapped.onPrepareActionMode(r2, r3);
        }
    }

    public static class Api21Impl {
        public static boolean isPowerSaveMode(PowerManager r0) {
            return r0.isPowerSaveMode();
        }

        public static String toLanguageTag(Locale r0) {
            return r0.toLanguageTag();
        }
    }

    public static class Api24Impl {
        public static void generateConfigDelta_locale(Configuration r1, Configuration r2, Configuration r3) {
            LocaleList r12 = r1.getLocales();
            LocaleList r0 = r2.getLocales();
            if (r12.equals(r0) == true) goto L6;
            r3.setLocales(r0);
            r3.locale = r2.locale;
            return;
        }

        public static LocaleListCompat getLocales(Configuration r0) {
            return LocaleListCompat.forLanguageTags(r0.getLocales().toLanguageTags());
        }

        public static void setDefaultLocales(LocaleListCompat r0) {
            LocaleList.setDefault(LocaleList.forLanguageTags(r0.toLanguageTags()));
        }

        public static void setLocales(Configuration r0, LocaleListCompat r1) {
            r0.setLocales(LocaleList.forLanguageTags(r1.toLanguageTags()));
        }
    }

    public static class Api26Impl {
        public static void generateConfigDelta_colorMode(Configuration r3, Configuration r4, Configuration r5) {
            int r0 = AppCompatDelegateImpl$Api26Impl$$ExternalSyntheticApiModelOutline0.m(r3) & 3;
            int r1 = AppCompatDelegateImpl$Api26Impl$$ExternalSyntheticApiModelOutline0.m(r4);
            if (r0 == (r1 & 3)) goto L5;
            AppCompatDelegateImpl$Api26Impl$$ExternalSyntheticApiModelOutline1.m(r5, AppCompatDelegateImpl$Api26Impl$$ExternalSyntheticApiModelOutline0.m(r5) | (r1 & 3));
        L5:
            int r32 = AppCompatDelegateImpl$Api26Impl$$ExternalSyntheticApiModelOutline0.m(r3) & 12;
            int r42 = AppCompatDelegateImpl$Api26Impl$$ExternalSyntheticApiModelOutline0.m(r4);
            if (r32 == (r42 & 12)) goto L9;
            AppCompatDelegateImpl$Api26Impl$$ExternalSyntheticApiModelOutline1.m(r5, AppCompatDelegateImpl$Api26Impl$$ExternalSyntheticApiModelOutline0.m(r5) | (r42 & 12));
            return;
        }
    }

    public static class Api33Impl {
        public static OnBackInvokedDispatcher getOnBackInvokedDispatcher(Activity r0) {
            return r0.getOnBackInvokedDispatcher();
        }

        public static OnBackInvokedCallback registerOnBackPressedCallback(Object r1, AppCompatDelegateImpl r2) {
            Objects.requireNonNull(r2);
            AppCompatDelegateImpl$Api33Impl$$ExternalSyntheticLambda2 r0 = new AppCompatDelegateImpl$Api33Impl$$ExternalSyntheticLambda2(r2);
            AppCompatDelegateImpl$Api33Impl$$ExternalSyntheticApiModelOutline1.m(r1).registerOnBackInvokedCallback(DurationKt.NANOS_IN_MILLIS, r0);
            return r0;
        }

        public static void unregisterOnBackInvokedCallback(Object r0, Object r1) {
            OnBackInvokedCallback r12 = AppCompatDelegateImpl$Api33Impl$$ExternalSyntheticApiModelOutline0.m(r1);
            AppCompatDelegateImpl$Api33Impl$$ExternalSyntheticApiModelOutline1.m(r0).unregisterOnBackInvokedCallback(r12);
        }
    }

    public class AppCompatWindowCallback extends WindowCallbackWrapper {
        private ActionBarMenuCallback mActionBarCallback;
        private boolean mDispatchKeyEventBypassEnabled;
        private boolean mOnContentChangedBypassEnabled;
        private boolean mOnPanelClosedBypassEnabled;
        final /* synthetic */ AppCompatDelegateImpl this$0;

        public AppCompatWindowCallback(AppCompatDelegateImpl r1, Window.Callback r2) {
            this.this$0 = r1;
            super(r2);
        }

        public boolean bypassDispatchKeyEvent(Window.Callback r3, KeyEvent r4) {
            this.mDispatchKeyEventBypassEnabled = true;     // Catch: Throwable -> L6
            boolean r32 = r3.dispatchKeyEvent(r4);     // Catch: Throwable -> L6
            this.mDispatchKeyEventBypassEnabled = false;
            return r32;
        L6:
            th = move-exception;
            this.mDispatchKeyEventBypassEnabled = false;
            throw th;
        }

        public void bypassOnContentChanged(Window.Callback r3) {
            this.mOnContentChangedBypassEnabled = true;     // Catch: Throwable -> L6
            r3.onContentChanged();     // Catch: Throwable -> L6
            this.mOnContentChangedBypassEnabled = false;
            return;
        L6:
            th = move-exception;
            this.mOnContentChangedBypassEnabled = false;
            throw th;
        }

        public void bypassOnPanelClosed(Window.Callback r3, int r4, Menu r5) {
            this.mOnPanelClosedBypassEnabled = true;     // Catch: Throwable -> L6
            r3.onPanelClosed(r4, r5);     // Catch: Throwable -> L6
            this.mOnPanelClosedBypassEnabled = false;
            return;
        L6:
            th = move-exception;
            this.mOnPanelClosedBypassEnabled = false;
            throw th;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent r2) {
            if (this.mDispatchKeyEventBypassEnabled == false) goto L7;
            return getWrapped().dispatchKeyEvent(r2);
        L7:
            if (this.this$0.dispatchKeyEvent(r2) == false) goto L9;
            return true;
        L9:
            if (super.dispatchKeyEvent(r2) == true) goto L15;
            return false;
        L15:
            return true;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent r2) {
            if (super.dispatchKeyShortcutEvent(r2) == false) goto L5;
            return true;
        L5:
            if (this.this$0.onKeyShortcut(r2.getKeyCode(), r2) == true) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // android.view.Window.Callback
        public void onContentChanged() {
            if (this.mOnContentChangedBypassEnabled == false) goto L6;
            getWrapped().onContentChanged();
            return;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean onCreatePanelMenu(int r2, Menu r3) {
            if (r2 != 0) goto L8;
            if ((r3 instanceof MenuBuilder) == true) goto L8;
            return false;
        L8:
            return super.onCreatePanelMenu(r2, r3);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public View onCreatePanelView(int r2) {
            ActionBarMenuCallback r0 = this.mActionBarCallback;
            if (r0 == null) goto L8;
            View r02 = r0.onCreatePanelView(r2);
            if (r02 == null) goto L8;
            return r02;
        L8:
            return super.onCreatePanelView(r2);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean onMenuOpened(int r1, Menu r2) {
            super.onMenuOpened(r1, r2);
            this.this$0.onMenuOpened(r1);
            return true;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public void onPanelClosed(int r2, Menu r3) {
            if (this.mOnPanelClosedBypassEnabled == false) goto L6;
            getWrapped().onPanelClosed(r2, r3);
            return;
        L6:
            super.onPanelClosed(r2, r3);
            this.this$0.onPanelClosed(r2);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean onPreparePanel(int r5, View r6, Menu r7) {
            if ((r7 instanceof MenuBuilder) == false) goto L5;
            MenuBuilder r0 = (MenuBuilder) r7;
        L7:
            if (r5 != 0) goto L10;
            if (r0 != null) goto L10;
            return false;
        L10:
            boolean r2 = true;
            if (r0 == null) goto L13;
            r0.setOverrideVisibleItems(true);
        L13:
            ActionBarMenuCallback r3 = this.mActionBarCallback;
            if (r3 != null) goto L16;
        L18:
            r2 = false;
        L19:
            if (r2 == true) goto L21;
            r2 = super.onPreparePanel(r5, r6, r7);
        L21:
            if (r0 == null) goto L23;
            r0.setOverrideVisibleItems(false);
        L23:
            return r2;
        L16:
            if (r3.onPreparePanel(r5) == false) goto L18;
        L5:
            r0 = null;
            goto L7
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> r4, Menu r5, int r6) {
            PanelFeatureState r0 = this.this$0.getPanelState(0, true);
            if (r0 == null) goto L8;
            MenuBuilder r02 = r0.menu;
            if (r02 == null) goto L8;
            super.onProvideKeyboardShortcuts(r4, r02, r6);
            return;
        L8:
            super.onProvideKeyboardShortcuts(r4, r5, r6);
        }

        @Override // android.view.Window.Callback
        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback r1) {
            return null;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback r2, int r3) {
            if (this.this$0.isHandleNativeActionModesEnabled() == false) goto L9;
            if (r3 != 0) goto L9;
            return startAsSupportActionMode(r2);
        L9:
            return super.onWindowStartingActionMode(r2, r3);
        }

        public void setActionBarCallback(ActionBarMenuCallback r1) {
            this.mActionBarCallback = r1;
        }

        public final android.view.ActionMode startAsSupportActionMode(ActionMode.Callback r3) {
            SupportActionModeWrapper.CallbackWrapper r0 = new SupportActionModeWrapper.CallbackWrapper(this.this$0.mContext, r3);
            androidx.appcompat.view.ActionMode r2 = this.this$0.startSupportActionMode(r0);
            if (r2 != null) goto L5;
            return null;
        L5:
            return r0.getActionModeWrapper(r2);
        }
    }

    public class AutoBatteryNightModeManager extends AutoNightModeManager {
        private final PowerManager mPowerManager;
        final /* synthetic */ AppCompatDelegateImpl this$0;

        public AutoBatteryNightModeManager(AppCompatDelegateImpl r1, Context r2) {
            this.this$0 = r1;
            super(r1);
            this.mPowerManager = (PowerManager) r2.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public IntentFilter createIntentFilterForBroadcastReceiver() {
            IntentFilter r1 = new IntentFilter();
            r1.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return r1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public int getApplyableNightMode() {
            if (Api21Impl.isPowerSaveMode(this.mPowerManager) == false) goto L6;
            return 2;
        L6:
            return 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public void onChange() {
            this.this$0.applyDayNight();
        }
    }

    public abstract class AutoNightModeManager {
        private BroadcastReceiver mReceiver;
        final /* synthetic */ AppCompatDelegateImpl this$0;

        /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$AutoNightModeManager$1, reason: invalid class name */
        public class AnonymousClass1 extends BroadcastReceiver {
            final /* synthetic */ AutoNightModeManager this$1;

            public AnonymousClass1(AutoNightModeManager r1) {
                this.this$1 = r1;
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context r1, Intent r2) {
                this.this$1.onChange();
            }
        }

        public AutoNightModeManager(AppCompatDelegateImpl r1) {
            this.this$0 = r1;
        }

        public void cleanup() {
            BroadcastReceiver r0 = this.mReceiver;
            if (r0 == null) goto L10;
            this.this$0.mContext.unregisterReceiver(r0);     // Catch: IllegalArgumentException -> L7
        L5:
            this.mReceiver = null;
            return;
        }

        public abstract IntentFilter createIntentFilterForBroadcastReceiver();

        public abstract int getApplyableNightMode();

        public abstract void onChange();

        public void setup() {
            cleanup();
            IntentFilter r0 = createIntentFilterForBroadcastReceiver();
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.countActions() != 0) goto L8;
            return;
        L8:
            if (this.mReceiver != null) goto L10;
            this.mReceiver = new AnonymousClass1(this);
        L10:
            this.this$0.mContext.registerReceiver(this.mReceiver, r0);
        }
    }

    public class AutoTimeNightModeManager extends AutoNightModeManager {
        private final TwilightManager mTwilightManager;
        final /* synthetic */ AppCompatDelegateImpl this$0;

        public AutoTimeNightModeManager(AppCompatDelegateImpl r1, TwilightManager r2) {
            this.this$0 = r1;
            super(r1);
            this.mTwilightManager = r2;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public IntentFilter createIntentFilterForBroadcastReceiver() {
            IntentFilter r1 = new IntentFilter();
            r1.addAction("android.intent.action.TIME_SET");
            r1.addAction("android.intent.action.TIMEZONE_CHANGED");
            r1.addAction("android.intent.action.TIME_TICK");
            return r1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public int getApplyableNightMode() {
            if (this.mTwilightManager.isNight() == false) goto L6;
            return 2;
        L6:
            return 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public void onChange() {
            this.this$0.applyDayNight();
        }
    }

    public class ListMenuDecorView extends ContentFrameLayout {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        public ListMenuDecorView(AppCompatDelegateImpl r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private boolean isOutOfBounds(int r2, int r3) {
            if (r2 < (-5)) goto L12;
            if (r3 >= (-5)) goto L6;
            return true;
        L6:
            if (r2 <= (getWidth() + 5)) goto L8;
            return true;
        L8:
            if (r3 > (getHeight() + 5)) goto L16;
            return false;
        L16:
            return true;
        L12:
            return true;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent r2) {
            if (this.this$0.dispatchKeyEvent(r2) == false) goto L5;
            return true;
        L5:
            if (super.dispatchKeyEvent(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L9;
            if (isOutOfBounds((int) r3.getX(), (int) r3.getY()) == false) goto L9;
            this.this$0.closePanel(0);
            return true;
        L9:
            return super.onInterceptTouchEvent(r3);
        }

        @Override // android.view.View
        public void setBackgroundResource(int r2) {
            setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), r2));
        }
    }

    public static final class PanelFeatureState {
        int background;
        View createdPanelView;
        ViewGroup decorView;
        int featureId;
        Bundle frozenActionViewState;
        int gravity;
        boolean isHandled;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        Context listPresenterContext;
        MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView;
        boolean refreshMenuContent;
        View shownPanelView;
        int windowAnimations;
        int x;
        int y;

        public PanelFeatureState(int r1) {
            this.featureId = r1;
            this.refreshDecorView = false;
        }

        public MenuView getListMenuView(MenuPresenter.Callback r4) {
            if (this.menu != null) goto L7;
            return null;
        L7:
            if (this.listMenuPresenter != null) goto L10;
            ListMenuPresenter r0 = new ListMenuPresenter(this.listPresenterContext, R$layout.abc_list_menu_item_layout);
            this.listMenuPresenter = r0;
            r0.setCallback(r4);
            this.menu.addMenuPresenter(this.listMenuPresenter);
        L10:
            return this.listMenuPresenter.getMenuView(this.decorView);
        }

        public boolean hasPanelItems() {
            if (this.shownPanelView != null) goto L6;
            return false;
        L6:
            if (this.createdPanelView == null) goto L9;
            return true;
        L9:
            if (this.listMenuPresenter.getAdapter().getCount() <= 0) goto L11;
            return true;
        L11:
            return false;
        }

        public void setMenu(MenuBuilder r3) {
            MenuBuilder r0 = this.menu;
            if (r3 == r0) goto L15;
            if (r0 == null) goto L7;
            r0.removeMenuPresenter(this.listMenuPresenter);
        L7:
            this.menu = r3;
            if (r3 == null) goto L13;
            ListMenuPresenter r2 = this.listMenuPresenter;
            if (r2 == null) goto L14;
            r3.addMenuPresenter(r2);
            return;
        L14:
            return;
        L13:
            return;
        }

        public void setStyle(Context r5) {
            TypedValue r0 = new TypedValue();
            Resources.Theme r1 = r5.getResources().newTheme();
            r1.setTo(r5.getTheme());
            r1.resolveAttribute(R$attr.actionBarPopupTheme, r0, true);
            int r2 = r0.resourceId;
            if (r2 == 0) goto L5;
            r1.applyStyle(r2, true);
        L5:
            r1.resolveAttribute(R$attr.panelMenuListTheme, r0, true);
            int r02 = r0.resourceId;
            if (r02 == 0) goto L8;
            r1.applyStyle(r02, true);
        L9:
            ContextThemeWrapper r03 = new ContextThemeWrapper(r5, 0);
            r03.getTheme().setTo(r1);
            this.listPresenterContext = r03;
            TypedArray r52 = r03.obtainStyledAttributes(R$styleable.AppCompatTheme);
            this.background = r52.getResourceId(R$styleable.AppCompatTheme_panelBackground, 0);
            this.windowAnimations = r52.getResourceId(R$styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            r52.recycle();
            return;
        L8:
            r1.applyStyle(R$style.Theme_AppCompat_CompactMenu, true);
            goto L9
        }
    }

    public final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
        final /* synthetic */ AppCompatDelegateImpl this$0;

        public PanelMenuPresenterCallback(AppCompatDelegateImpl r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder r5, boolean r6) {
            MenuBuilder r0 = r5.getRootMenu();
            if (r0 == r5) goto L5;
            boolean r2 = true;
        L6:
            AppCompatDelegateImpl r3 = this.this$0;
            if (r2 == false) goto L9;
            r5 = r0;
        L9:
            PanelFeatureState r52 = r3.findMenuPanel(r5);
            if (r52 == null) goto L17;
            AppCompatDelegateImpl r32 = this.this$0;
            if (r2 == false) goto L15;
            r32.callOnPanelClosed(r52.featureId, r52, r0);
            this.this$0.closePanel(r52, true);
            return;
        L15:
            r32.closePanel(r52, r6);
            return;
        L17:
            return;
        L5:
            r2 = false;
            goto L6
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder r3) {
            if (r3 != r3.getRootMenu()) goto L13;
            AppCompatDelegateImpl r0 = this.this$0;
            if (r0.mHasActionBar == false) goto L14;
            Window.Callback r02 = r0.getWindowCallback();
            if (r02 != null) goto L9;
            return true;
        L9:
            if (this.this$0.mDestroyed == true) goto L16;
            r02.onMenuOpened(108, r3);
            return true;
        L16:
            return true;
        L14:
            return true;
        L13:
            return true;
        }
    }

    static {
        sLocalNightModes = new SimpleArrayMap();
        IS_PRE_LOLLIPOP = false;
        sWindowBackgroundStyleable = new int[]{R.attr.windowBackground};
        sCanReturnDifferentContext = !"robolectric".equals(Build.FINGERPRINT);
    }

    public AppCompatDelegateImpl(Activity r2, AppCompatCallback r3) {
        this(r2, null, r3, r2);
    }

    public AppCompatDelegateImpl(Dialog r3, AppCompatCallback r4) {
        this(r3.getContext(), r3.getWindow(), r4, r3);
    }

    private AppCompatDelegateImpl(Context r3, Window r4, AppCompatCallback r5, Object r6) {
        this.mFadeAnim = null;
        this.mHandleNativeActionModes = true;
        this.mLocalNightMode = -100;
        this.mInvalidatePanelMenuRunnable = new AnonymousClass2(this);
        this.mContext = r3;
        this.mAppCompatCallback = r5;
        this.mHost = r6;
        if (this.mLocalNightMode != (-100)) goto L10;
        if ((r6 instanceof Dialog) == false) goto L10;
        AppCompatActivity r32 = tryUnwrapContext();
        if (r32 == null) goto L10;
        this.mLocalNightMode = r32.getDelegate().getLocalNightMode();
    L10:
        if (this.mLocalNightMode != (-100)) goto L14;
        SimpleArrayMap<String, Integer> r33 = sLocalNightModes;
        Integer r52 = r33.get(r6.getClass().getName());
        if (r52 == null) goto L14;
        this.mLocalNightMode = r52.intValue();
        r33.remove(r6.getClass().getName());
    L14:
        if (r4 == null) goto L16;
        attachToWindow(r4);
    L16:
        AppCompatDrawableManager.preload();
    }

    private boolean applyApplicationSpecificConfig(boolean r2) {
        return applyApplicationSpecificConfig(r2, true);
    }

    private boolean applyApplicationSpecificConfig(boolean r5, boolean r6) {
        if (this.mDestroyed == false) goto L6;
        return false;
    L6:
        int r0 = calculateNightMode();
        int r1 = mapNightMode(this.mContext, r0);
        if (Build.VERSION.SDK_INT >= 33) goto L9;
        LocaleListCompat r2 = calculateApplicationLocales(this.mContext);
    L10:
        if (r6 == true) goto L13;
        if (r2 == null) goto L13;
        r2 = getConfigurationLocales(this.mContext.getResources().getConfiguration());
    L13:
        boolean r52 = updateAppConfiguration(r1, r2, r5);
        if (r0 != 0) goto L16;
        getAutoTimeNightModeManager(this.mContext).setup();
    L20:
        if (r0 != 3) goto L23;
        getAutoBatteryNightModeManager(this.mContext).setup();
        return r52;
    L23:
        AutoNightModeManager r4 = this.mAutoBatteryNightModeManager;
        if (r4 == null) goto L26;
        r4.cleanup();
    L26:
        return r52;
    L16:
        AutoNightModeManager r62 = this.mAutoTimeNightModeManager;
        if (r62 == null) goto L20;
        r62.cleanup();
        goto L20
    L9:
        r2 = null;
        goto L10
    }

    private void applyFixedSizeWindow() {
        ContentFrameLayout r0 = (ContentFrameLayout) this.mSubDecor.findViewById(R.id.content);
        View r1 = this.mWindow.getDecorView();
        r0.setDecorPadding(r1.getPaddingLeft(), r1.getPaddingTop(), r1.getPaddingRight(), r1.getPaddingBottom());
        TypedArray r5 = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme);
        r5.getValue(R$styleable.AppCompatTheme_windowMinWidthMajor, r0.getMinWidthMajor());
        r5.getValue(R$styleable.AppCompatTheme_windowMinWidthMinor, r0.getMinWidthMinor());
        if (r5.hasValue(R$styleable.AppCompatTheme_windowFixedWidthMajor) == false) goto L6;
        r5.getValue(R$styleable.AppCompatTheme_windowFixedWidthMajor, r0.getFixedWidthMajor());
    L6:
        if (r5.hasValue(R$styleable.AppCompatTheme_windowFixedWidthMinor) == false) goto L9;
        r5.getValue(R$styleable.AppCompatTheme_windowFixedWidthMinor, r0.getFixedWidthMinor());
    L9:
        if (r5.hasValue(R$styleable.AppCompatTheme_windowFixedHeightMajor) == false) goto L12;
        r5.getValue(R$styleable.AppCompatTheme_windowFixedHeightMajor, r0.getFixedHeightMajor());
    L12:
        if (r5.hasValue(R$styleable.AppCompatTheme_windowFixedHeightMinor) == false) goto L14;
        r5.getValue(R$styleable.AppCompatTheme_windowFixedHeightMinor, r0.getFixedHeightMinor());
    L14:
        r5.recycle();
        r0.requestLayout();
    }

    private void attachToWindow(Window r4) {
        if (this.mWindow != null) goto L17;
        Window.Callback r0 = r4.getCallback();
        if ((r0 instanceof AppCompatWindowCallback) == true) goto L15;
        AppCompatWindowCallback r1 = new AppCompatWindowCallback(this, r0);
        this.mAppCompatWindowCallback = r1;
        r4.setCallback(r1);
        TintTypedArray r02 = TintTypedArray.obtainStyledAttributes(this.mContext, null, sWindowBackgroundStyleable);
        Drawable r12 = r02.getDrawableIfKnown(0);
        if (r12 == null) goto L9;
        r4.setBackgroundDrawable(r12);
    L9:
        r02.recycle();
        this.mWindow = r4;
        if (Build.VERSION.SDK_INT >= 33) goto L12;
        return;
    L12:
        if (this.mDispatcher != null) goto L20;
        setOnBackInvokedDispatcher(null);
        return;
    L20:
        return;
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("AppCompat has already installed itself into the Window");
        return;
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("AppCompat has already installed itself into the Window");
    }

    private int calculateNightMode() {
        int r1 = this.mLocalNightMode;
        if (r1 == (-100)) goto L6;
        return r1;
    L6:
        return AppCompatDelegate.getDefaultNightMode();
    }

    private void cleanupAutoManagers() {
        AutoNightModeManager r0 = this.mAutoTimeNightModeManager;
        if (r0 == null) goto L5;
        r0.cleanup();
    L5:
        AutoNightModeManager r1 = this.mAutoBatteryNightModeManager;
        if (r1 == null) goto L9;
        r1.cleanup();
        return;
    }

    private Configuration createOverrideAppConfiguration(Context r2, int r3, LocaleListCompat r4, Configuration r5, boolean r6) {
        if (r3 != 1) goto L5;
        int r22 = 16;
    L11:
        Configuration r32 = new Configuration();
        r32.fontScale = 0.0f;
        if (r5 == null) goto L14;
        r32.setTo(r5);
    L14:
        r32.uiMode = r22 | (r32.uiMode & (-49));
        if (r4 == null) goto L17;
        setConfigurationLocales(r32, r4);
    L17:
        return r32;
    L5:
        if (r3 == 2) goto L9;
        if (r6 == false) goto L8;
        r22 = 0;
        goto L11
    L8:
        r22 = r2.getApplicationContext().getResources().getConfiguration().uiMode & 48;
        goto L11
    L9:
        r22 = 32;
        goto L11
    }

    private ViewGroup createSubDecor() {
        TypedArray r0 = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme);
        if (r0.hasValue(R$styleable.AppCompatTheme_windowActionBar) == true) goto L5;
        r0.recycle();
        Segment$$ExternalSyntheticBUOutline1.m("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        return null;
    L5:
        if (r0.getBoolean(R$styleable.AppCompatTheme_windowNoTitle, false) == false) goto L8;
        requestWindowFeature(1);
    L11:
        if (r0.getBoolean(R$styleable.AppCompatTheme_windowActionBarOverlay, false) == false) goto L14;
        requestWindowFeature(109);
    L14:
        if (r0.getBoolean(R$styleable.AppCompatTheme_windowActionModeOverlay, false) == false) goto L16;
        requestWindowFeature(10);
    L16:
        this.mIsFloating = r0.getBoolean(R$styleable.AppCompatTheme_android_windowIsFloating, false);
        r0.recycle();
        ensureWindow();
        this.mWindow.getDecorView();
        LayoutInflater r02 = LayoutInflater.from(this.mContext);
        if (this.mWindowNoTitle == true) goto L38;
        if (this.mIsFloating == false) goto L22;
        ViewGroup r03 = (ViewGroup) r02.inflate(R$layout.abc_dialog_title_material, null);
        this.mOverlayActionBar = false;
        this.mHasActionBar = false;
    L41:
        if (r03 == null) goto L56;
        ViewCompat.setOnApplyWindowInsetsListener(r03, new AnonymousClass3(this));
        if (this.mDecorContentParent != null) goto L45;
        this.mTitleView = (TextView) r03.findViewById(R$id.title);
    L45:
        ViewUtils.makeOptionalFitsSystemWindows(r03);
        ContentFrameLayout r1 = (ContentFrameLayout) r03.findViewById(R$id.action_bar_activity_content);
        ViewGroup r4 = (ViewGroup) this.mWindow.findViewById(R.id.content);
        if (r4 != null) goto L48;
    L53:
        this.mWindow.setContentView(r03);
        r1.setAttachListener(new AnonymousClass5(this));
        return r03;
    L48:
        if (r4.getChildCount() <= 0) goto L50;
        View r6 = r4.getChildAt(0);
        r4.removeViewAt(0);
        r1.addView(r6);
        goto L48
    L50:
        r4.setId(-1);
        r1.setId(R.id.content);
        if ((r4 instanceof FrameLayout) == false) goto L53;
        ((FrameLayout) r4).setForeground(null);
        goto L53
    L56:
        throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.mHasActionBar + ", windowActionBarOverlay: " + this.mOverlayActionBar + ", android:windowIsFloating: " + this.mIsFloating + ", windowActionModeOverlay: " + this.mOverlayActionMode + ", windowNoTitle: " + this.mWindowNoTitle + " }");
    L22:
        if (this.mHasActionBar == false) goto L36;
        TypedValue r04 = new TypedValue();
        this.mContext.getTheme().resolveAttribute(R$attr.actionBarTheme, r04, true);
        if (r04.resourceId == 0) goto L26;
        Context r12 = new ContextThemeWrapper(this.mContext, r04.resourceId);
    L27:
        r03 = (ViewGroup) LayoutInflater.from(r12).inflate(R$layout.abc_screen_toolbar, null);
        DecorContentParent r13 = (DecorContentParent) r03.findViewById(R$id.decor_content_parent);
        this.mDecorContentParent = r13;
        r13.setWindowCallback(getWindowCallback());
        if (this.mOverlayActionBar == false) goto L31;
        this.mDecorContentParent.initFeature(109);
    L31:
        if (this.mFeatureProgress == false) goto L34;
        this.mDecorContentParent.initFeature(2);
    L34:
        if (this.mFeatureIndeterminateProgress == false) goto L41;
        this.mDecorContentParent.initFeature(5);
        goto L41
    L26:
        r12 = this.mContext;
        goto L27
    L36:
        r03 = null;
        goto L41
    L38:
        if (this.mOverlayActionMode == false) goto L40;
        r03 = (ViewGroup) r02.inflate(R$layout.abc_screen_simple_overlay_action_mode, null);
        goto L41
    L40:
        r03 = (ViewGroup) r02.inflate(R$layout.abc_screen_simple, null);
        goto L41
    L8:
        if (r0.getBoolean(R$styleable.AppCompatTheme_windowActionBar, false) == false) goto L11;
        requestWindowFeature(108);
        goto L11
    }

    private void ensureSubDecor() {
        if (this.mSubDecorInstalled == true) goto L22;
        this.mSubDecor = createSubDecor();
        CharSequence r0 = getTitle();
        if (TextUtils.isEmpty(r0) == true) goto L15;
        DecorContentParent r1 = this.mDecorContentParent;
        if (r1 == null) goto L10;
        r1.setWindowTitle(r0);
        goto L15
    L10:
        if (peekSupportActionBar() == null) goto L12;
        peekSupportActionBar().setWindowTitle(r0);
        goto L15
    L12:
        TextView r12 = this.mTitleView;
        if (r12 == null) goto L15;
        r12.setText(r0);
    L15:
        applyFixedSizeWindow();
        onSubDecorInstalled(this.mSubDecor);
        this.mSubDecorInstalled = true;
        PanelFeatureState r02 = getPanelState(0, false);
        if (this.mDestroyed == true) goto L23;
        if (r02 != null) goto L19;
    L20:
        invalidatePanelMenu(108);
        return;
    L19:
        if (r02.menu == null) goto L20;
        return;
    L23:
        return;
    }

    private void ensureWindow() {
        if (this.mWindow != null) goto L8;
        Object r0 = this.mHost;
        if ((r0 instanceof Activity) == false) goto L8;
        attachToWindow(((Activity) r0).getWindow());
    L8:
        if (this.mWindow == null) goto L10;
        return;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("We have not been given a Window");
    }

    private static Configuration generateConfigDelta(Configuration r4, Configuration r5) {
        Configuration r0 = new Configuration();
        r0.fontScale = 0.0f;
        if (r5 != null) goto L5;
    L67:
        return r0;
    L5:
        if (r4.diff(r5) == 0) goto L67;
        float r1 = r4.fontScale;
        float r2 = r5.fontScale;
        if (r1 == r2) goto L10;
        r0.fontScale = r2;
    L10:
        int r12 = r4.mcc;
        int r22 = r5.mcc;
        if (r12 == r22) goto L13;
        r0.mcc = r22;
    L13:
        int r13 = r4.mnc;
        int r23 = r5.mnc;
        if (r13 == r23) goto L16;
        r0.mnc = r23;
    L16:
        Api24Impl.generateConfigDelta_locale(r4, r5, r0);
        int r14 = r4.touchscreen;
        int r24 = r5.touchscreen;
        if (r14 == r24) goto L19;
        r0.touchscreen = r24;
    L19:
        int r15 = r4.keyboard;
        int r25 = r5.keyboard;
        if (r15 == r25) goto L22;
        r0.keyboard = r25;
    L22:
        int r16 = r4.keyboardHidden;
        int r26 = r5.keyboardHidden;
        if (r16 == r26) goto L25;
        r0.keyboardHidden = r26;
    L25:
        int r17 = r4.navigation;
        int r27 = r5.navigation;
        if (r17 == r27) goto L28;
        r0.navigation = r27;
    L28:
        int r18 = r4.navigationHidden;
        int r28 = r5.navigationHidden;
        if (r18 == r28) goto L31;
        r0.navigationHidden = r28;
    L31:
        int r19 = r4.orientation;
        int r29 = r5.orientation;
        if (r19 == r29) goto L34;
        r0.orientation = r29;
    L34:
        int r110 = r4.screenLayout & 15;
        int r210 = r5.screenLayout;
        if (r110 == (r210 & 15)) goto L37;
        r0.screenLayout |= r210 & 15;
    L37:
        int r111 = r4.screenLayout & 192;
        int r211 = r5.screenLayout;
        if (r111 == (r211 & 192)) goto L40;
        r0.screenLayout |= r211 & 192;
    L40:
        int r112 = r4.screenLayout & 48;
        int r212 = r5.screenLayout;
        if (r112 == (r212 & 48)) goto L43;
        r0.screenLayout |= r212 & 48;
    L43:
        int r113 = r4.screenLayout & 768;
        int r213 = r5.screenLayout;
        if (r113 == (r213 & 768)) goto L47;
        r0.screenLayout |= r213 & 768;
    L47:
        if (Build.VERSION.SDK_INT < 26) goto L49;
        Api26Impl.generateConfigDelta_colorMode(r4, r5, r0);
    L49:
        int r114 = r4.uiMode & 15;
        int r214 = r5.uiMode;
        if (r114 == (r214 & 15)) goto L52;
        r0.uiMode |= r214 & 15;
    L52:
        int r115 = r4.uiMode & 48;
        int r215 = r5.uiMode;
        if (r115 == (r215 & 48)) goto L55;
        r0.uiMode |= r215 & 48;
    L55:
        int r116 = r4.screenWidthDp;
        int r216 = r5.screenWidthDp;
        if (r116 == r216) goto L58;
        r0.screenWidthDp = r216;
    L58:
        int r117 = r4.screenHeightDp;
        int r217 = r5.screenHeightDp;
        if (r117 == r217) goto L61;
        r0.screenHeightDp = r217;
    L61:
        int r118 = r4.smallestScreenWidthDp;
        int r218 = r5.smallestScreenWidthDp;
        if (r118 == r218) goto L64;
        r0.smallestScreenWidthDp = r218;
    L64:
        int r42 = r4.densityDpi;
        int r52 = r5.densityDpi;
        if (r42 == r52) goto L67;
        r0.densityDpi = r52;
        goto L67
    }

    private int getActivityHandlesConfigChangesFlags(Context r6) {
        if (this.mActivityHandlesConfigFlagsChecked == false) goto L5;
    L19:
        this.mActivityHandlesConfigFlagsChecked = true;
        return this.mActivityHandlesConfigFlags;
    L5:
        if ((this.mHost instanceof Activity) == false) goto L19;
        PackageManager r0 = r6.getPackageManager();
        if (r0 != null) goto L21;
        return 0;
    L21:
    L17:
        e = move-exception;
        Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e);
        this.mActivityHandlesConfigFlags = 0;
        goto L19
    L10:
        if (Build.VERSION.SDK_INT < 29) goto L12;
        int r2 = 269221888;
    L13:
        ActivityInfo r62 = r0.getActivityInfo(new ComponentName(r6, this.mHost.getClass()), r2);     // Catch: PackageManager.NameNotFoundException -> L17
        if (r62 == null) goto L19;
        this.mActivityHandlesConfigFlags = r62.configChanges;     // Catch: PackageManager.NameNotFoundException -> L17
        goto L19
    L12:
        r2 = 786432;
        goto L13
    }

    private AutoNightModeManager getAutoBatteryNightModeManager(Context r2) {
        if (this.mAutoBatteryNightModeManager != null) goto L6;
        this.mAutoBatteryNightModeManager = new AutoBatteryNightModeManager(this, r2);
    L6:
        return this.mAutoBatteryNightModeManager;
    }

    private AutoNightModeManager getAutoTimeNightModeManager(Context r2) {
        if (this.mAutoTimeNightModeManager != null) goto L6;
        this.mAutoTimeNightModeManager = new AutoTimeNightModeManager(this, TwilightManager.getInstance(r2));
    L6:
        return this.mAutoTimeNightModeManager;
    }

    private void initWindowDecorActionBar() {
        ensureSubDecor();
        if (this.mHasActionBar == true) goto L5;
        return;
    L5:
        if (this.mActionBar != null) goto L19;
        Object r0 = this.mHost;
        if ((r0 instanceof Activity) == false) goto L11;
        this.mActionBar = new WindowDecorActionBar((Activity) this.mHost, this.mOverlayActionBar);
    L13:
        ActionBar r02 = this.mActionBar;
        if (r02 == null) goto L18;
        r02.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
        return;
    L18:
        return;
    L11:
        if ((r0 instanceof Dialog) == false) goto L13;
        this.mActionBar = new WindowDecorActionBar((Dialog) this.mHost);
        goto L13
    }

    private boolean initializePanelContent(PanelFeatureState r4) {
        View r0 = r4.createdPanelView;
        if (r0 == null) goto L7;
        r4.shownPanelView = r0;
        return true;
    L7:
        if (r4.menu != null) goto L10;
        return false;
    L10:
        if (this.mPanelMenuPresenterCallback != null) goto L12;
        this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback(this);
    L12:
        View r3 = (View) r4.getListMenuView(this.mPanelMenuPresenterCallback);
        r4.shownPanelView = r3;
        if (r3 == null) goto L15;
        return true;
    L15:
        return false;
    }

    private boolean initializePanelDecor(PanelFeatureState r3) {
        r3.setStyle(getActionBarThemedContext());
        r3.decorView = new ListMenuDecorView(this, r3.listPresenterContext);
        r3.gravity = 81;
        return true;
    }

    private boolean initializePanelMenu(PanelFeatureState r7) {
        Context r0 = this.mContext;
        int r1 = r7.featureId;
        if (r1 == 0) goto L7;
        if (r1 == 108) goto L7;
    L19:
        MenuBuilder r12 = new MenuBuilder(r0);
        r12.setCallback(this);
        r7.setMenu(r12);
        return true;
    L7:
        if (this.mDecorContentParent == null) goto L19;
        TypedValue r13 = new TypedValue();
        Resources.Theme r3 = r0.getTheme();
        r3.resolveAttribute(R$attr.actionBarTheme, r13, true);
        if (r13.resourceId == 0) goto L11;
        Resources.Theme r4 = r0.getResources().newTheme();
        r4.setTo(r3);
        r4.applyStyle(r13.resourceId, true);
        r4.resolveAttribute(R$attr.actionBarWidgetTheme, r13, true);
    L13:
        if (r13.resourceId == 0) goto L17;
        if (r4 != null) goto L16;
        r4 = r0.getResources().newTheme();
        r4.setTo(r3);
    L16:
        r4.applyStyle(r13.resourceId, true);
    L17:
        if (r4 == null) goto L19;
        ContextThemeWrapper r14 = new ContextThemeWrapper(r0, 0);
        r14.getTheme().setTo(r4);
        r0 = r14;
        goto L19
    L11:
        r3.resolveAttribute(R$attr.actionBarWidgetTheme, r13, true);
        r4 = null;
        goto L13
    }

    private void invalidatePanelMenu(int r3) {
        int r32 = 1 << r3;
        this.mInvalidatePanelMenuFeatures = r32 | this.mInvalidatePanelMenuFeatures;
        if (this.mInvalidatePanelMenuPosted == true) goto L6;
        ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
        this.mInvalidatePanelMenuPosted = true;
        return;
    }

    private boolean onKeyDownPanel(int r2, KeyEvent r3) {
        if (r3.getRepeatCount() != 0) goto L8;
        PanelFeatureState r22 = getPanelState(r2, true);
        if (r22.isOpen == false) goto L7;
        return false;
    L7:
        return preparePanel(r22, r3);
    L8:
        return false;
    }

    private boolean onKeyUpPanel(int r5, KeyEvent r6) {
        if (this.mActionMode == null) goto L5;
        return false;
    L5:
        boolean r0 = true;
        PanelFeatureState r2 = getPanelState(r5, true);
        if (r5 != 0) goto L21;
        DecorContentParent r52 = this.mDecorContentParent;
        if (r52 == null) goto L21;
        if (r52.canShowOverflowMenu() == false) goto L21;
        if (ViewConfiguration.get(this.mContext).hasPermanentMenuKey() == true) goto L21;
        if (this.mDecorContentParent.isOverflowMenuShowing() == false) goto L16;
        r0 = this.mDecorContentParent.hideOverflowMenu();
    L36:
        if (r0 == false) goto L42;
        AudioManager r4 = (AudioManager) this.mContext.getApplicationContext().getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (r4 == null) goto L41;
        r4.playSoundEffect(0);
        return r0;
    L41:
        Log.w("AppCompatDelegate", "Couldn't get audio manager");
    L42:
        return r0;
    L16:
        if (this.mDestroyed == false) goto L18;
    L34:
        r0 = false;
        goto L36
    L18:
        if (preparePanel(r2, r6) == false) goto L34;
        r0 = this.mDecorContentParent.showOverflowMenu();
    L21:
        boolean r53 = r2.isOpen;
        if (r53 == false) goto L24;
    L35:
        closePanel(r2, true);
        r0 = r53;
        goto L36
    L24:
        if (r2.isHandled == true) goto L35;
        if (r2.isPrepared == false) goto L34;
        if (r2.refreshMenuContent == false) goto L31;
        r2.isPrepared = false;
        boolean r54 = preparePanel(r2, r6);
    L32:
        if (r54 == false) goto L34;
        openPanel(r2, r6);
        goto L36
    L31:
        r54 = true;
        goto L32
    }

    private void openPanel(PanelFeatureState r12, KeyEvent r13) {
        if (r12.isOpen == false) goto L5;
        return;
    L5:
        if (this.mDestroyed == false) goto L8;
        return;
    L8:
        if (r12.featureId == 0) goto L10;
    L12:
        Window.Callback r0 = getWindowCallback();
        if (r0 != null) goto L15;
    L18:
        WindowManager r02 = (WindowManager) this.mContext.getSystemService("window");
        if (r02 != null) goto L22;
        return;
    L22:
        if (preparePanel(r12, r13) == false) goto L73;
        ViewGroup r132 = r12.decorView;
        if (r132 != null) goto L27;
    L36:
        if (r132 != null) goto L43;
        if (initializePanelDecor(r12) == true) goto L40;
        return;
    L40:
        if (r12.decorView != null) goto L48;
        return;
    L48:
        if (initializePanelContent(r12) == true) goto L50;
    L66:
        r12.refreshDecorView = true;
        return;
    L50:
        if (r12.hasPanelItems() == false) goto L66;
        ViewGroup.LayoutParams r133 = r12.shownPanelView.getLayoutParams();
        if (r133 != null) goto L55;
        r133 = new ViewGroup.LayoutParams(-2, -2);
    L55:
        r12.decorView.setBackgroundResource(r12.background);
        ViewParent r3 = r12.shownPanelView.getParent();
        if ((r3 instanceof ViewGroup) == false) goto L58;
        ((ViewGroup) r3).removeView(r12.shownPanelView);
    L58:
        r12.decorView.addView(r12.shownPanelView, r133);
        if (r12.shownPanelView.hasFocus() == true) goto L61;
        r12.shownPanelView.requestFocus();
    L61:
        int r4 = -2;
    L62:
        r12.isHandled = false;
        WindowManager.LayoutParams r32 = new WindowManager.LayoutParams(r4, -2, r12.x, r12.y, 1002, 8519680, -3);
        r32.gravity = r12.gravity;
        r32.windowAnimations = r12.windowAnimations;
        r02.addView(r12.decorView, r32);
        r12.isOpen = true;
        if (r12.featureId != 0) goto L70;
        updateBackInvokedCallbackState();
        return;
    L70:
        return;
    L43:
        if (r12.refreshDecorView == false) goto L48;
        if (r132.getChildCount() <= 0) goto L48;
        r12.decorView.removeAllViews();
        goto L48
    L27:
        if (r12.refreshDecorView == true) goto L36;
        View r134 = r12.createdPanelView;
        if (r134 == null) goto L61;
        ViewGroup.LayoutParams r135 = r134.getLayoutParams();
        if (r135 == null) goto L61;
        if (r135.width != (-1)) goto L61;
        r4 = -1;
        goto L62
    L73:
        return;
    L15:
        if (r0.onMenuOpened(r12.featureId, r12.menu) == true) goto L18;
        closePanel(r12, true);
        return;
    L10:
        if ((this.mContext.getResources().getConfiguration().screenLayout & 15) != 4) goto L12;
    }

    private boolean performPanelShortcut(PanelFeatureState r3, int r4, KeyEvent r5, int r6) {
        boolean r1 = false;
        if (r5.isSystem() == false) goto L6;
        return false;
    L6:
        if (r3.isPrepared == false) goto L8;
    L9:
        MenuBuilder r0 = r3.menu;
        if (r0 == null) goto L12;
        r1 = r0.performShortcut(r4, r5, r6);
    L12:
        if (r1 == true) goto L14;
    L18:
        return r1;
    L14:
        if ((r6 & 1) != 0) goto L18;
        if (this.mDecorContentParent != null) goto L18;
        closePanel(r3, true);
        goto L18
    L8:
        if (preparePanel(r3, r5) == false) goto L12;
        goto L9
    }

    private boolean preparePanel(PanelFeatureState r9, KeyEvent r10) {
        if (this.mDestroyed == false) goto L6;
        return false;
    L6:
        if (r9.isPrepared == false) goto L8;
        return true;
    L8:
        PanelFeatureState r0 = this.mPreparedPanel;
        if (r0 == null) goto L12;
        if (r0 == r9) goto L12;
        closePanel(r0, false);
    L12:
        Window.Callback r02 = getWindowCallback();
        if (r02 == null) goto L15;
        r9.createdPanelView = r02.onCreatePanelView(r9.featureId);
    L15:
        int r3 = r9.featureId;
        if (r3 != 0) goto L18;
    L21:
        boolean r32 = true;
    L22:
        if (r32 == false) goto L27;
        DecorContentParent r4 = this.mDecorContentParent;
        if (r4 == null) goto L27;
        r4.setMenuPrepared();
    L27:
        if (r9.createdPanelView != null) goto L76;
        if (r32 == true) goto L30;
    L31:
        MenuBuilder r42 = r9.menu;
        if (r42 != null) goto L34;
    L35:
        if (r42 == null) goto L37;
    L41:
        if (r32 == true) goto L43;
    L48:
        r9.menu.stopDispatchingItemsChanged();
        if (r02.onCreatePanelMenu(r9.featureId, r9.menu) == true) goto L56;
        r9.setMenu(null);
        if (r32 == false) goto L55;
        DecorContentParent r92 = this.mDecorContentParent;
        if (r92 == null) goto L55;
        r92.setMenu(null, this.mActionMenuPresenterCallback);
    L55:
        return false;
    L56:
        r9.refreshMenuContent = false;
    L57:
        r9.menu.stopDispatchingItemsChanged();
        Bundle r43 = r9.frozenActionViewState;
        if (r43 == null) goto L61;
        r9.menu.restoreActionViewStates(r43);
        r9.frozenActionViewState = null;
    L61:
        if (r02.onPreparePanel(0, r9.createdPanelView, r9.menu) == true) goto L68;
        if (r32 == false) goto L66;
        DecorContentParent r102 = this.mDecorContentParent;
        if (r102 == null) goto L66;
        r102.setMenu(null, this.mActionMenuPresenterCallback);
    L66:
        r9.menu.startDispatchingItemsChanged();
        return false;
    L68:
        if (r10 == null) goto L70;
        int r103 = r10.getDeviceId();
    L72:
        if (KeyCharacterMap.load(r103).getKeyboardType() == 1) goto L74;
        boolean r104 = true;
    L75:
        r9.qwertyMode = r104;
        r9.menu.setQwertyMode(r104);
        r9.menu.startDispatchingItemsChanged();
        goto L76
    L74:
        r104 = false;
        goto L75
    L70:
        r103 = -1;
        goto L72
    L43:
        if (this.mDecorContentParent == null) goto L48;
        if (this.mActionMenuPresenterCallback != null) goto L47;
        this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback(this);
    L47:
        this.mDecorContentParent.setMenu(r9.menu, this.mActionMenuPresenterCallback);
        goto L48
    L37:
        if (initializePanelMenu(r9) == true) goto L39;
    L40:
        return false;
    L39:
        if (r9.menu != null) goto L41;
    L34:
        if (r9.refreshMenuContent == false) goto L57;
    L30:
        if ((peekSupportActionBar() instanceof ToolbarActionBar) == false) goto L31;
    L76:
        r9.isPrepared = true;
        r9.isHandled = false;
        this.mPreparedPanel = r9;
        return true;
    L18:
        if (r3 == 108) goto L21;
        r32 = false;
        goto L22
    }

    private void reopenMenu(boolean r6) {
        DecorContentParent r0 = this.mDecorContentParent;
        if (r0 != null) goto L5;
    L34:
        PanelFeatureState r62 = getPanelState(0, true);
        r62.refreshDecorView = true;
        closePanel(r62, false);
        openPanel(r62, null);
        return;
    L5:
        if (r0.canShowOverflowMenu() == false) goto L34;
        if (ViewConfiguration.get(this.mContext).hasPermanentMenuKey() == true) goto L9;
    L10:
        Window.Callback r02 = getWindowCallback();
        if (this.mDecorContentParent.isOverflowMenuShowing() == false) goto L18;
        if (r6 == false) goto L18;
        this.mDecorContentParent.hideOverflowMenu();
        if (this.mDestroyed == true) goto L36;
        r02.onPanelClosed(108, getPanelState(0, true).menu);
        return;
    L36:
        return;
    L18:
        if (r02 != null) goto L20;
        return;
    L20:
        if (this.mDestroyed == false) goto L22;
        return;
    L22:
        if (this.mInvalidatePanelMenuPosted == true) goto L24;
    L26:
        PanelFeatureState r63 = getPanelState(0, true);
        MenuBuilder r1 = r63.menu;
        if (r1 != null) goto L29;
        return;
    L29:
        if (r63.refreshMenuContent == false) goto L31;
        return;
    L31:
        if (r02.onPreparePanel(0, r63.createdPanelView, r1) == false) goto L41;
        r02.onMenuOpened(108, r63.menu);
        this.mDecorContentParent.showOverflowMenu();
        return;
    L41:
        return;
    L24:
        if ((this.mInvalidatePanelMenuFeatures & 1) == 0) goto L26;
        this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
        this.mInvalidatePanelMenuRunnable.run();
        goto L26
    L9:
        if (this.mDecorContentParent.isOverflowMenuShowPending() == false) goto L34;
        goto L10
    }

    private int sanitizeWindowFeatureId(int r2) {
        if (r2 != 8) goto L7;
        Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
        return 108;
    L7:
        if (r2 != 9) goto L10;
        Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
        return 109;
    L10:
        return r2;
    }

    private boolean shouldInheritContext(ViewParent r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        View r2 = this.mWindow.getDecorView();
    L6:
        if (r3 == null) goto L7;
        if (r3 == r2) goto L16;
        if ((r3 instanceof View) == false) goto L16;
        if (((View) r3).isAttachedToWindow() == true) goto L16;
        r3 = r3.getParent();
    L16:
        return false;
    L7:
        return true;
    }

    private void throwFeatureRequestIfSubDecorInstalled() {
        if (this.mSubDecorInstalled == true) goto L6;
        return;
    L6:
        throw new AndroidRuntimeException("Window feature must be requested before adding content");
    }

    private AppCompatActivity tryUnwrapContext() {
        Context r2 = this.mContext;
    L4:
        if (r2 == null) goto L12;
        if ((r2 instanceof AppCompatActivity) == true) goto L8;
        if ((r2 instanceof ContextWrapper) == false) goto L12;
        r2 = ((ContextWrapper) r2).getBaseContext();
        goto L4
    L8:
        return (AppCompatActivity) r2;
    L12:
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updateActivityConfiguration(Configuration r3) {
        Activity r0 = (Activity) this.mHost;
        if ((r0 instanceof LifecycleOwner) == false) goto L9;
        if (((LifecycleOwner) r0).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED) == false) goto L14;
        r0.onConfigurationChanged(r3);
        return;
    L14:
        return;
    L9:
        if (this.mCreated == true) goto L11;
        return;
    L11:
        if (this.mDestroyed == true) goto L16;
        r0.onConfigurationChanged(r3);
        return;
    }

    private boolean updateAppConfiguration(int r10, LocaleListCompat r11, boolean r12) {
        Configuration r9 = createOverrideAppConfiguration(this.mContext, r10, r11, null, false);
        int r102 = getActivityHandlesConfigChangesFlags(this.mContext);
        Configuration r112 = this.mEffectiveConfiguration;
        if (r112 != null) goto L5;
        r112 = this.mContext.getResources().getConfiguration();
    L5:
        int r1 = r112.uiMode & 48;
        int r4 = r9.uiMode & 48;
        LocaleListCompat r113 = getConfigurationLocales(r112);
        if (r11 != null) goto L8;
        LocaleListCompat r6 = null;
    L9:
        boolean r7 = false;
        if (r1 == r4) goto L12;
        int r13 = 512;
    L13:
        if (r6 != null) goto L15;
    L17:
        boolean r8 = true;
        if (((~r102) & r13) == 0) goto L36;
        if (r12 == false) goto L36;
        if (this.mBaseContextAttached == false) goto L36;
        if (sCanReturnDifferentContext == false) goto L25;
    L26:
        Object r114 = this.mHost;
        if ((r114 instanceof Activity) == false) goto L36;
        if (((Activity) r114).isChild() == true) goto L36;
        if (Build.VERSION.SDK_INT >= 31) goto L33;
    L35:
        ActivityCompat.recreate((Activity) this.mHost);
        boolean r92 = true;
    L37:
        if (r92 == true) goto L43;
        if (r13 == 0) goto L43;
        if ((r13 & r102) != r13) goto L42;
        r7 = true;
    L42:
        updateResourcesConfiguration(r4, r6, r7, null);
    L44:
        if (r8 == false) goto L53;
        Object r93 = this.mHost;
        if ((r93 instanceof AppCompatActivity) == false) goto L53;
        if ((r13 & 512) == 0) goto L51;
        ((AppCompatActivity) r93).onNightModeChanged(r10);
    L51:
        if ((r13 & 4) == 0) goto L53;
        ((AppCompatActivity) this.mHost).onLocalesChanged(r11);
    L53:
        if (r6 == null) goto L55;
        setDefaultLocalesForLocaleList(getConfigurationLocales(this.mContext.getResources().getConfiguration()));
    L55:
        return r8;
    L43:
        r8 = r92;
        goto L44
    L33:
        if ((r13 & 8192) == 0) goto L35;
        ((Activity) this.mHost).getWindow().getDecorView().setLayoutDirection(r9.getLayoutDirection());
        goto L35
    L25:
        if (this.mCreated == true) goto L26;
    L36:
        r92 = false;
        goto L37
    L15:
        if (r113.equals(r6) == true) goto L17;
        r13 = r13 | 8196;
        goto L17
    L12:
        r13 = 0;
        goto L13
    L8:
        r6 = getConfigurationLocales(r9);
        goto L9
    }

    private void updateResourcesConfiguration(int r4, LocaleListCompat r5, boolean r6, Configuration r7) {
        Resources r0 = this.mContext.getResources();
        Configuration r1 = new Configuration(r0.getConfiguration());
        if (r7 == null) goto L5;
        r1.updateFrom(r7);
    L5:
        r1.uiMode = r4 | (r0.getConfiguration().uiMode & (-49));
        if (r5 == null) goto L8;
        setConfigurationLocales(r1, r5);
    L8:
        r0.updateConfiguration(r1, null);
        if (Build.VERSION.SDK_INT >= 26) goto L11;
        ResourcesFlusher.flush(r0);
    L11:
        int r42 = this.mThemeResId;
        if (r42 == 0) goto L14;
        this.mContext.setTheme(r42);
        this.mContext.getTheme().applyStyle(this.mThemeResId, true);
    L14:
        if (r6 == true) goto L16;
        return;
    L16:
        if ((this.mHost instanceof Activity) == false) goto L20;
        updateActivityConfiguration(r1);
        return;
    }

    private void updateStatusGuardColor(View r2) {
        int r0 = ViewCompat.getWindowSystemUiVisibility(r2) & 8192;
        Context r1 = this.mContext;
        if (r0 == 0) goto L5;
        int r12 = ContextCompat.getColor(r1, R$color.abc_decor_view_status_guard_light);
    L6:
        r2.setBackgroundColor(r12);
        return;
    L5:
        r12 = ContextCompat.getColor(r1, R$color.abc_decor_view_status_guard);
        goto L6
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void addContentView(View r3, ViewGroup.LayoutParams r4) {
        ensureSubDecor();
        ((ViewGroup) this.mSubDecor.findViewById(R.id.content)).addView(r3, r4);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    public boolean applyDayNight() {
        return applyApplicationSpecificConfig(true);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public Context attachBaseContext2(Context r8) {
        this.mBaseContextAttached = true;
        int r3 = mapNightMode(r8, calculateNightMode());
        if (AppCompatDelegate.isAutoStorageOptedIn(r8) == false) goto L5;
        AppCompatDelegate.syncRequestedAndStoredLocales(r8);
    L5:
        LocaleListCompat r4 = calculateApplicationLocales(r8);
        if ((r8 instanceof android.view.ContextThemeWrapper) == false) goto L10;
        AppCompatDelegateImpl r1 = this;
        Context r2 = r8;
        ((android.view.ContextThemeWrapper) r2).applyOverrideConfiguration(r1.createOverrideAppConfiguration(r2, r3, r4, null, false));     // Catch: IllegalStateException -> L31
        return r2;
    L12:
        if ((r2 instanceof ContextThemeWrapper) == false) goto L17;
        ((ContextThemeWrapper) r2).applyOverrideConfiguration(r1.createOverrideAppConfiguration(r2, r3, r4, null, false));     // Catch: IllegalStateException -> L32
        return r2;
    L17:
        if (sCanReturnDifferentContext == false) goto L19;
        Configuration r7 = new Configuration();
        r7.uiMode = -1;
        r7.fontScale = 0.0f;
        Configuration r72 = r2.createConfigurationContext(r7).getResources().getConfiguration();
        Configuration r82 = r2.getResources().getConfiguration();
        r72.uiMode = r82.uiMode;
        if (r72.equals(r82) == true) goto L24;
        Configuration r73 = generateConfigDelta(r72, r82);
    L25:
        Configuration r74 = r1.createOverrideAppConfiguration(r2, r3, r4, r73, true);
        ContextThemeWrapper r83 = new ContextThemeWrapper(r2, R$style.Theme_AppCompat_Empty);
        r83.applyOverrideConfiguration(r74);
        if (r2.getTheme() == null) goto L30;
        ResourcesCompat.ThemeCompat.rebase(r83.getTheme());
    L30:
        return super.attachBaseContext2(r83);
    L24:
        r73 = null;
        goto L25
    L19:
        return super.attachBaseContext2(r2);
    L10:
        r1 = this;
        r2 = r8;
        goto L12
    }

    public LocaleListCompat calculateApplicationLocales(Context r4) {
        if (Build.VERSION.SDK_INT < 33) goto L5;
        return null;
    L5:
        LocaleListCompat r0 = AppCompatDelegate.getRequestedAppLocales();
        if (r0 != null) goto L8;
        return null;
    L8:
        LocaleListCompat r3 = getConfigurationLocales(r4.getApplicationContext().getResources().getConfiguration());
        LocaleListCompat r42 = LocaleOverlayHelper.combineLocalesIfOverlayExists(r0, r3);
        if (r42.isEmpty() == false) goto L11;
        return r3;
    L11:
        return r42;
    }

    public void callOnPanelClosed(int r3, PanelFeatureState r4, Menu r5) {
        if (r5 != null) goto L10;
        if (r4 != null) goto L8;
        if (r3 < 0) goto L8;
        PanelFeatureState[] r0 = this.mPanels;
        if (r3 >= r0.length) goto L8;
        r4 = r0[r3];
    L8:
        if (r4 == null) goto L10;
        r5 = r4.menu;
    L10:
        if (r4 == null) goto L15;
        if (r4.isOpen == true) goto L15;
        return;
    L15:
        if (this.mDestroyed == true) goto L18;
        this.mAppCompatWindowCallback.bypassOnPanelClosed(this.mWindow.getCallback(), r3, r5);
        return;
    }

    public void checkCloseActionMenu(MenuBuilder r3) {
        if (this.mClosingActionMenu == false) goto L5;
        return;
    L5:
        this.mClosingActionMenu = true;
        this.mDecorContentParent.dismissPopups();
        Window.Callback r0 = getWindowCallback();
        if (r0 != null) goto L8;
    L10:
        this.mClosingActionMenu = false;
        return;
    L8:
        if (this.mDestroyed == true) goto L10;
        r0.onPanelClosed(108, r3);
        goto L10
    }

    public void closePanel(int r2) {
        closePanel(getPanelState(r2, true), true);
    }

    public void closePanel(PanelFeatureState r4, boolean r5) {
        if (r5 == true) goto L4;
    L11:
        WindowManager r0 = (WindowManager) this.mContext.getSystemService("window");
        if (r0 != null) goto L14;
    L20:
        r4.isPrepared = false;
        r4.isHandled = false;
        r4.isOpen = false;
        r4.shownPanelView = null;
        r4.refreshDecorView = true;
        if (this.mPreparedPanel != r4) goto L24;
        this.mPreparedPanel = null;
    L24:
        if (r4.featureId != 0) goto L27;
        updateBackInvokedCallbackState();
        return;
    L27:
        return;
    L14:
        if (r4.isOpen == false) goto L20;
        ViewGroup r2 = r4.decorView;
        if (r2 == null) goto L20;
        r0.removeView(r2);
        if (r5 == false) goto L20;
        callOnPanelClosed(r4.featureId, r4, null);
        goto L20
    L4:
        if (r4.featureId != 0) goto L11;
        DecorContentParent r02 = this.mDecorContentParent;
        if (r02 == null) goto L11;
        if (r02.isOverflowMenuShowing() == false) goto L11;
        checkCloseActionMenu(r4.menu);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View createView(View r11, String r12, Context r13, AttributeSet r14) {
        if (this.mAppCompatViewInflater != null) goto L11;
        TypedArray r0 = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme);
        String r1 = r0.getString(R$styleable.AppCompatTheme_viewInflaterClass);
        r0.recycle();
        if (r1 != null) goto L28;
        this.mAppCompatViewInflater = new AppCompatViewInflater();
        goto L11
    L28:
        this.mAppCompatViewInflater = (AppCompatViewInflater) this.mContext.getClassLoader().loadClass(r1).getDeclaredConstructor(null).newInstance(null);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + r1 + ". Falling back to default.", th);
        this.mAppCompatViewInflater = new AppCompatViewInflater();
    L11:
        boolean r7 = IS_PRE_LOLLIPOP;
        boolean r02 = false;
        if (r7 == true) goto L14;
    L25:
        boolean r6 = r02;
    L27:
        return this.mAppCompatViewInflater.createView(r11, r12, r13, r14, r6, r7, true, VectorEnabledTintResources.shouldBeUsed());
    L14:
        if (this.mLayoutIncludeDetector != null) goto L17;
        this.mLayoutIncludeDetector = new LayoutIncludeDetector();
    L17:
        if (this.mLayoutIncludeDetector.detect(r14) == false) goto L20;
        r6 = true;
        goto L27
    L20:
        if ((r14 instanceof XmlPullParser) == true) goto L22;
        r02 = shouldInheritContext((ViewParent) r11);
        goto L25
    L22:
        if (((XmlPullParser) r14).getDepth() <= 1) goto L25;
        r02 = true;
        goto L25
    }

    public void dismissPopups() {
        DecorContentParent r0 = this.mDecorContentParent;
        if (r0 == null) goto L6;
        r0.dismissPopups();
    L6:
        if (this.mActionModePopup == null) goto L11;
        this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
        if (this.mActionModePopup.isShowing() == true) goto L18;
    L10:
        this.mActionModePopup = null;
        goto L11
    L18:
        this.mActionModePopup.dismiss();     // Catch: IllegalArgumentException -> L17
    L11:
        endOnGoingFadeAnimation();
        PanelFeatureState r2 = getPanelState(0, false);
        if (r2 == null) goto L20;
        MenuBuilder r22 = r2.menu;
        if (r22 == null) goto L21;
        r22.close();
        return;
    L21:
        return;
    }

    public boolean dispatchKeyEvent(KeyEvent r4) {
        Object r0 = this.mHost;
        if ((r0 instanceof KeyEventDispatcher.Component) == false) goto L5;
    L6:
        View r02 = this.mWindow.getDecorView();
        if (r02 != null) goto L9;
    L12:
        if (r4.getKeyCode() == 82) goto L14;
    L16:
        int r03 = r4.getKeyCode();
        if (r4.getAction() != 0) goto L21;
        return onKeyDown(r03, r4);
    L21:
        return onKeyUp(r03, r4);
    L14:
        if (this.mAppCompatWindowCallback.bypassDispatchKeyEvent(this.mWindow.getCallback(), r4) == false) goto L16;
        return true;
    L9:
        if (KeyEventDispatcher.dispatchBeforeHierarchy(r02, r4) == false) goto L12;
        return true;
    L5:
        if ((r0 instanceof AppCompatDialog) == false) goto L12;
        goto L6
    }

    public void doInvalidatePanelMenu(int r5) {
        PanelFeatureState r1 = getPanelState(r5, true);
        if (r1.menu == null) goto L8;
        Bundle r2 = new Bundle();
        r1.menu.saveActionViewStates(r2);
        if (r2.size() <= 0) goto L7;
        r1.frozenActionViewState = r2;
    L7:
        r1.menu.stopDispatchingItemsChanged();
        r1.menu.clear();
    L8:
        r1.refreshMenuContent = true;
        r1.refreshDecorView = true;
        if (r5 == 108) goto L12;
        if (r5 == 0) goto L12;
        return;
    L12:
        if (this.mDecorContentParent == null) goto L18;
        PanelFeatureState r0 = getPanelState(0, false);
        if (r0 == null) goto L19;
        r0.isPrepared = false;
        preparePanel(r0, null);
        return;
    L19:
        return;
    }

    public void endOnGoingFadeAnimation() {
        ViewPropertyAnimatorCompat r0 = this.mFadeAnim;
        if (r0 == null) goto L6;
        r0.cancel();
        return;
    }

    public PanelFeatureState findMenuPanel(Menu r5) {
        PanelFeatureState[] r4 = this.mPanels;
        int r0 = 0;
        if (r4 == null) goto L5;
        int r1 = r4.length;
    L6:
        if (r0 >= r1) goto L13;
        PanelFeatureState r2 = r4[r0];
        if (r2 == null) goto L12;
        if (r2.menu != r5) goto L12;
        return r2;
    L12:
        r0 = r0 + 1;
        goto L6
    L13:
        return null;
    L5:
        r1 = 0;
        goto L6
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public <T extends View> T findViewById(int r1) {
        ensureSubDecor();
        return (T) this.mWindow.findViewById(r1);
    }

    public final Context getActionBarThemedContext() {
        ActionBar r0 = getSupportActionBar();
        if (r0 == null) goto L5;
        Context r02 = r0.getThemedContext();
    L6:
        if (r02 == null) goto L8;
        return r02;
    L8:
        return this.mContext;
    L5:
        r02 = null;
        goto L6
    }

    public LocaleListCompat getConfigurationLocales(Configuration r1) {
        return Api24Impl.getLocales(r1);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public Context getContextForDelegate() {
        return this.mContext;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final ActionBarDrawerToggle$Delegate getDrawerToggleDelegate() {
        return new ActionBarDrawableToggleImpl(this);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public int getLocalNightMode() {
        return this.mLocalNightMode;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public MenuInflater getMenuInflater() {
        if (this.mMenuInflater != null) goto L10;
        initWindowDecorActionBar();
        ActionBar r1 = this.mActionBar;
        if (r1 == null) goto L7;
        Context r12 = r1.getThemedContext();
    L8:
        this.mMenuInflater = new SupportMenuInflater(r12);
        goto L10
    L7:
        r12 = this.mContext;
    L10:
        return this.mMenuInflater;
    }

    public PanelFeatureState getPanelState(int r4, boolean r5) {
        PanelFeatureState[] r52 = this.mPanels;
        if (r52 != null) goto L5;
    L6:
        PanelFeatureState[] r0 = new PanelFeatureState[r4 + 1];
        if (r52 == null) goto L9;
        System.arraycopy(r52, 0, r0, 0, r52.length);
    L9:
        this.mPanels = r0;
        r52 = r0;
    L10:
        PanelFeatureState r3 = r52[r4];
        if (r3 != null) goto L14;
        PanelFeatureState r32 = new PanelFeatureState(r4);
        r52[r4] = r32;
        return r32;
    L14:
        return r3;
    L5:
        if (r52.length > r4) goto L10;
        goto L6
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public ActionBar getSupportActionBar() {
        initWindowDecorActionBar();
        return this.mActionBar;
    }

    public final CharSequence getTitle() {
        Object r0 = this.mHost;
        if ((r0 instanceof Activity) == false) goto L7;
        return ((Activity) r0).getTitle();
    L7:
        return this.mTitle;
    }

    public final Window.Callback getWindowCallback() {
        return this.mWindow.getCallback();
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void installViewFactory() {
        LayoutInflater r0 = LayoutInflater.from(this.mContext);
        if (r0.getFactory() != null) goto L7;
        LayoutInflaterCompat.setFactory2(r0, this);
        return;
    L7:
        if ((r0.getFactory2() instanceof AppCompatDelegateImpl) == true) goto L10;
        Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        return;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void invalidateOptionsMenu() {
        if (peekSupportActionBar() != null) goto L5;
        return;
    L5:
        if (getSupportActionBar().invalidateOptionsMenu() == true) goto L10;
        invalidatePanelMenu(0);
        return;
    }

    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }

    public int mapNightMode(Context r3, int r4) {
        if (r4 == (-100)) goto L22;
        if (r4 == (-1)) goto L21;
        if (r4 == 0) goto L17;
        if (r4 == 1) goto L21;
        if (r4 == 2) goto L21;
        if (r4 == 3) goto L13;
        Segment$$ExternalSyntheticBUOutline1.m("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
        return 0;
    L13:
        return getAutoBatteryNightModeManager(r3).getApplyableNightMode();
    L17:
        if (((UiModeManager) r3.getApplicationContext().getSystemService("uimode")).getNightMode() != 0) goto L20;
        return -1;
    L20:
        return getAutoTimeNightModeManager(r3).getApplyableNightMode();
    L21:
        return r4;
    L22:
        return -1;
    }

    public boolean onBackPressed() {
        boolean r0 = this.mLongPressBackDown;
        this.mLongPressBackDown = false;
        PanelFeatureState r2 = getPanelState(0, false);
        if (r2 != null) goto L5;
    L9:
        androidx.appcompat.view.ActionMode r02 = this.mActionMode;
        if (r02 == null) goto L13;
        r02.finish();
        return true;
    L13:
        ActionBar r5 = getSupportActionBar();
        if (r5 != null) goto L16;
    L18:
        return false;
    L16:
        if (r5.collapseActionView() == false) goto L18;
        return true;
    L5:
        if (r2.isOpen == false) goto L9;
        if (r0 == true) goto L8;
        closePanel(r2, true);
    L8:
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onConfigurationChanged(Configuration r2) {
        if (this.mHasActionBar == true) goto L5;
    L9:
        AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
        this.mEffectiveConfiguration = new Configuration(this.mContext.getResources().getConfiguration());
        applyApplicationSpecificConfig(false, false);
        return;
    L5:
        if (this.mSubDecorInstalled == false) goto L9;
        ActionBar r0 = getSupportActionBar();
        if (r0 == null) goto L9;
        r0.onConfigurationChanged(r2);
        goto L9
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onCreate(Bundle r3) {
        this.mBaseContextAttached = true;
        applyApplicationSpecificConfig(false);
        ensureWindow();
        Object r0 = this.mHost;
        if ((r0 instanceof Activity) == true) goto L15;
    L13:
        this.mEffectiveConfiguration = new Configuration(this.mContext.getResources().getConfiguration());
        this.mCreated = true;
        return;
    L15:
        String r02 = NavUtils.getParentActivityName((Activity) r0);     // Catch: IllegalArgumentException -> L6
    L7:
        if (r02 == null) goto L12;
        ActionBar r03 = peekSupportActionBar();
        if (r03 != null) goto L11;
        this.mEnableDefaultActionBarUp = true;
        goto L12
    L11:
        r03.setDefaultDisplayHomeAsUpEnabled(true);
    L12:
        AppCompatDelegate.addActiveDelegate(this);
    L6:
        r02 = null;
        goto L7
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View r1, String r2, Context r3, AttributeSet r4) {
        return createView(r1, r2, r3, r4);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String r2, Context r3, AttributeSet r4) {
        return onCreateView(null, r2, r3, r4);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onDestroy() {
        if ((this.mHost instanceof Activity) == false) goto L6;
        AppCompatDelegate.removeActivityDelegate(this);
    L6:
        if (this.mInvalidatePanelMenuPosted == false) goto L8;
        this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
    L8:
        this.mDestroyed = true;
        if (this.mLocalNightMode == (-100)) goto L15;
        Object r0 = this.mHost;
        if ((r0 instanceof Activity) == false) goto L15;
        if (((Activity) r0).isChangingConfigurations() == false) goto L15;
        sLocalNightModes.put(this.mHost.getClass().getName(), Integer.valueOf(this.mLocalNightMode));
    L16:
        ActionBar r02 = this.mActionBar;
        if (r02 == null) goto L19;
        r02.onDestroy();
    L19:
        cleanupAutoManagers();
        return;
    L15:
        sLocalNightModes.remove(this.mHost.getClass().getName());
        goto L16
    }

    public boolean onKeyDown(int r4, KeyEvent r5) {
        boolean r1 = true;
        if (r4 == 4) goto L10;
        if (r4 != 82) goto L14;
        onKeyDownPanel(0, r5);
        return true;
    L14:
        return false;
    L10:
        if ((r5.getFlags() & 128) != 0) goto L13;
        r1 = false;
    L13:
        this.mLongPressBackDown = r1;
        goto L14
    }

    public boolean onKeyShortcut(int r4, KeyEvent r5) {
        ActionBar r0 = getSupportActionBar();
        if (r0 != null) goto L5;
    L7:
        PanelFeatureState r42 = this.mPreparedPanel;
        if (r42 == null) goto L16;
        if (performPanelShortcut(r42, r5.getKeyCode(), r5, 1) == false) goto L16;
        PanelFeatureState r3 = this.mPreparedPanel;
        if (r3 == null) goto L14;
        r3.isHandled = true;
    L14:
        return true;
    L16:
        if (this.mPreparedPanel != null) goto L20;
        PanelFeatureState r43 = getPanelState(0, true);
        preparePanel(r43, r5);
        boolean r32 = performPanelShortcut(r43, r5.getKeyCode(), r5, 1);
        r43.isPrepared = false;
        if (r32 == false) goto L20;
        return true;
    L20:
        return false;
    L5:
        if (r0.onKeyShortcut(r4, r5) == false) goto L7;
        return true;
    }

    public boolean onKeyUp(int r4, KeyEvent r5) {
        if (r4 == 4) goto L10;
        if (r4 != 82) goto L12;
        onKeyUpPanel(0, r5);
        return true;
    L12:
        return false;
    L10:
        if (onBackPressed() == false) goto L12;
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public boolean onMenuItemSelected(MenuBuilder r3, MenuItem r4) {
        Window.Callback r0 = getWindowCallback();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (this.mDestroyed == true) goto L12;
        PanelFeatureState r2 = findMenuPanel(r3.getRootMenu());
        if (r2 != null) goto L9;
        return false;
    L9:
        return r0.onMenuItemSelected(r2.featureId, r4);
    L12:
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public void onMenuModeChange(MenuBuilder r1) {
        reopenMenu(true);
    }

    public void onMenuOpened(int r2) {
        if (r2 != 108) goto L8;
        ActionBar r1 = getSupportActionBar();
        if (r1 == null) goto L9;
        r1.dispatchMenuVisibilityChanged(true);
        return;
    L9:
        return;
    }

    public void onPanelClosed(int r3) {
        if (r3 != 108) goto L8;
        ActionBar r2 = getSupportActionBar();
        if (r2 == null) goto L13;
        r2.dispatchMenuVisibilityChanged(false);
        return;
    L13:
        return;
    L8:
        if (r3 != 0) goto L14;
        PanelFeatureState r32 = getPanelState(r3, true);
        if (r32.isOpen == false) goto L15;
        closePanel(r32, false);
        return;
    L15:
        return;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onPostCreate(Bundle r1) {
        ensureSubDecor();
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onPostResume() {
        ActionBar r1 = getSupportActionBar();
        if (r1 == null) goto L6;
        r1.setShowHideAnimationEnabled(true);
        return;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onSaveInstanceState(Bundle r1) {
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onStart() {
        applyApplicationSpecificConfig(true, false);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onStop() {
        ActionBar r1 = getSupportActionBar();
        if (r1 == null) goto L6;
        r1.setShowHideAnimationEnabled(false);
        return;
    }

    public void onSubDecorInstalled(ViewGroup r1) {
    }

    public final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public boolean requestWindowFeature(int r5) {
        int r52 = sanitizeWindowFeatureId(r5);
        if (this.mWindowNoTitle == false) goto L7;
        if (r52 != 108) goto L7;
        return false;
    L7:
        if (this.mHasActionBar == false) goto L10;
        if (r52 != 1) goto L10;
        this.mHasActionBar = false;
    L10:
        if (r52 != 1) goto L12;
        throwFeatureRequestIfSubDecorInstalled();
        this.mWindowNoTitle = true;
        return true;
    L12:
        if (r52 != 2) goto L14;
        throwFeatureRequestIfSubDecorInstalled();
        this.mFeatureProgress = true;
        return true;
    L14:
        if (r52 != 5) goto L16;
        throwFeatureRequestIfSubDecorInstalled();
        this.mFeatureIndeterminateProgress = true;
        return true;
    L16:
        if (r52 == 10) goto L26;
        if (r52 != 108) goto L19;
        throwFeatureRequestIfSubDecorInstalled();
        this.mHasActionBar = true;
        return true;
    L19:
        if (r52 != 109) goto L21;
        throwFeatureRequestIfSubDecorInstalled();
        this.mOverlayActionBar = true;
        return true;
    L21:
        return this.mWindow.requestFeature(r52);
    L26:
        throwFeatureRequestIfSubDecorInstalled();
        this.mOverlayActionMode = true;
        return true;
    }

    public void setConfigurationLocales(Configuration r1, LocaleListCompat r2) {
        Api24Impl.setLocales(r1, r2);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void setContentView(int r3) {
        ensureSubDecor();
        ViewGroup r0 = (ViewGroup) this.mSubDecor.findViewById(R.id.content);
        r0.removeAllViews();
        LayoutInflater.from(this.mContext).inflate(r3, r0);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void setContentView(View r3) {
        ensureSubDecor();
        ViewGroup r0 = (ViewGroup) this.mSubDecor.findViewById(R.id.content);
        r0.removeAllViews();
        r0.addView(r3);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void setContentView(View r3, ViewGroup.LayoutParams r4) {
        ensureSubDecor();
        ViewGroup r0 = (ViewGroup) this.mSubDecor.findViewById(R.id.content);
        r0.removeAllViews();
        r0.addView(r3, r4);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    public void setDefaultLocalesForLocaleList(LocaleListCompat r1) {
        Api24Impl.setDefaultLocales(r1);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void setOnBackInvokedDispatcher(OnBackInvokedDispatcher r3) {
        super.setOnBackInvokedDispatcher(r3);
        OnBackInvokedDispatcher r0 = this.mDispatcher;
        if (r0 == null) goto L7;
        OnBackInvokedCallback r1 = this.mBackCallback;
        if (r1 == null) goto L7;
        Api33Impl.unregisterOnBackInvokedCallback(r0, r1);
        this.mBackCallback = null;
    L7:
        if (r3 != null) goto L13;
        Object r02 = this.mHost;
        if ((r02 instanceof Activity) == false) goto L13;
        if (((Activity) r02).getWindow() == null) goto L13;
        this.mDispatcher = Api33Impl.getOnBackInvokedDispatcher((Activity) this.mHost);
    L14:
        updateBackInvokedCallbackState();
        return;
    L13:
        this.mDispatcher = r3;
        goto L14
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void setSupportActionBar(Toolbar r4) {
        if ((this.mHost instanceof Activity) == true) goto L5;
        return;
    L5:
        ActionBar r0 = getSupportActionBar();
        if ((r0 instanceof WindowDecorActionBar) == true) goto L16;
        this.mMenuInflater = null;
        if (r0 == null) goto L10;
        r0.onDestroy();
    L10:
        this.mActionBar = null;
        if (r4 == null) goto L13;
        ToolbarActionBar r02 = new ToolbarActionBar(r4, getTitle(), this.mAppCompatWindowCallback);
        this.mActionBar = r02;
        this.mAppCompatWindowCallback.setActionBarCallback(r02.mMenuCallback);
        r4.setBackInvokedCallbackEnabled(true);
    L14:
        invalidateOptionsMenu();
        return;
    L13:
        this.mAppCompatWindowCallback.setActionBarCallback(null);
        goto L14
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void setTheme(int r1) {
        this.mThemeResId = r1;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void setTitle(CharSequence r2) {
        this.mTitle = r2;
        DecorContentParent r0 = this.mDecorContentParent;
        if (r0 == null) goto L7;
        r0.setWindowTitle(r2);
        return;
    L7:
        if (peekSupportActionBar() == null) goto L10;
        peekSupportActionBar().setWindowTitle(r2);
        return;
    L10:
        TextView r1 = this.mTitleView;
        if (r1 == null) goto L14;
        r1.setText(r2);
        return;
    }

    public final boolean shouldAnimateActionModeView() {
        if (this.mSubDecorInstalled == false) goto L10;
        ViewGroup r1 = this.mSubDecor;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.isLaidOut() == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean shouldRegisterBackInvokedCallback() {
        if (this.mDispatcher != null) goto L5;
        return false;
    L5:
        PanelFeatureState r0 = getPanelState(0, false);
        if (r0 == null) goto L11;
        if (r0.isOpen == false) goto L11;
        return true;
    L11:
        if (this.mActionMode == null) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public androidx.appcompat.view.ActionMode startSupportActionMode(ActionMode.Callback r3) {
        if (r3 == null) goto L18;
        androidx.appcompat.view.ActionMode r0 = this.mActionMode;
        if (r0 == null) goto L6;
        r0.finish();
    L6:
        ActionModeCallbackWrapperV9 r02 = new ActionModeCallbackWrapperV9(this, r3);
        ActionBar r32 = getSupportActionBar();
        if (r32 == null) goto L14;
        androidx.appcompat.view.ActionMode r33 = r32.startActionMode(r02);
        this.mActionMode = r33;
        if (r33 == null) goto L14;
        AppCompatCallback r1 = this.mAppCompatCallback;
        if (r1 == null) goto L14;
        r1.onSupportActionModeStarted(r33);
    L14:
        if (this.mActionMode != null) goto L16;
        this.mActionMode = startSupportActionModeFromWindow(r02);
    L16:
        updateBackInvokedCallbackState();
        return this.mActionMode;
    L18:
        f$$ExternalSyntheticBUOutline1.m("ActionMode callback can not be null.");
        return null;
    }

    public androidx.appcompat.view.ActionMode startSupportActionModeFromWindow(ActionMode.Callback r8) {
        endOnGoingFadeAnimation();
        androidx.appcompat.view.ActionMode r0 = this.mActionMode;
        if (r0 == null) goto L6;
        r0.finish();
    L6:
        if ((r8 instanceof ActionModeCallbackWrapperV9) == true) goto L8;
        r8 = new ActionModeCallbackWrapperV9(this, r8);
    L8:
        AppCompatCallback r02 = this.mAppCompatCallback;
        if (r02 != null) goto L11;
    L14:
        androidx.appcompat.view.ActionMode r03 = null;
    L15:
        if (r03 == null) goto L17;
        this.mActionMode = r03;
    L46:
        androidx.appcompat.view.ActionMode r82 = this.mActionMode;
        if (r82 == null) goto L51;
        AppCompatCallback r04 = this.mAppCompatCallback;
        if (r04 == null) goto L51;
        r04.onSupportActionModeStarted(r82);
    L51:
        updateBackInvokedCallbackState();
        return this.mActionMode;
    L17:
        boolean r3 = true;
        if (this.mActionModeView != null) goto L29;
        if (this.mIsFloating == false) goto L25;
        TypedValue r05 = new TypedValue();
        Resources.Theme r4 = this.mContext.getTheme();
        r4.resolveAttribute(R$attr.actionBarTheme, r05, true);
        int r5 = r05.resourceId;
        Context r6 = this.mContext;
        if (r5 == 0) goto L24;
        Resources.Theme r52 = r6.getResources().newTheme();
        r52.setTo(r4);
        r52.applyStyle(r05.resourceId, true);
        r6 = new ContextThemeWrapper(this.mContext, 0);
        r6.getTheme().setTo(r52);
    L24:
        this.mActionModeView = new ActionBarContextView(r6);
        PopupWindow r42 = new PopupWindow(r6, null, R$attr.actionModePopupWindowStyle);
        this.mActionModePopup = r42;
        PopupWindowCompat.setWindowLayoutType(r42, 2);
        this.mActionModePopup.setContentView(this.mActionModeView);
        this.mActionModePopup.setWidth(-1);
        r6.getTheme().resolveAttribute(R$attr.actionBarSize, r05, true);
        this.mActionModeView.setContentHeight(TypedValue.complexToDimensionPixelSize(r05.data, r6.getResources().getDisplayMetrics()));
        this.mActionModePopup.setHeight(-2);
        this.mShowActionModePopup = new AnonymousClass6(this);
        goto L29
    L25:
        ViewStubCompat r06 = (ViewStubCompat) this.mSubDecor.findViewById(R$id.action_mode_bar_stub);
        if (r06 == null) goto L29;
        r06.setLayoutInflater(LayoutInflater.from(getActionBarThemedContext()));
        this.mActionModeView = (ActionBarContextView) r06.inflate();
    L29:
        if (this.mActionModeView == null) goto L46;
        endOnGoingFadeAnimation();
        this.mActionModeView.killMode();
        Context r43 = this.mActionModeView.getContext();
        ActionBarContextView r53 = this.mActionModeView;
        if (this.mActionModePopup == null) goto L34;
        r3 = false;
    L34:
        StandaloneActionMode r07 = new StandaloneActionMode(r43, r53, r8, r3);
        if (r8.onCreateActionMode(r07, r07.getMenu()) == false) goto L45;
        r07.invalidate();
        this.mActionModeView.initForMode(r07);
        this.mActionMode = r07;
        boolean r83 = shouldAnimateActionModeView();
        ActionBarContextView r08 = this.mActionModeView;
        if (r83 == false) goto L39;
        r08.setAlpha(0.0f);
        ViewPropertyAnimatorCompat r84 = ViewCompat.animate(this.mActionModeView).alpha(1.0f);
        this.mFadeAnim = r84;
        r84.setListener(new AnonymousClass7(this));
    L43:
        if (this.mActionModePopup == null) goto L46;
        this.mWindow.getDecorView().post(this.mShowActionModePopup);
        goto L46
    L39:
        r08.setAlpha(1.0f);
        this.mActionModeView.setVisibility(0);
        if ((this.mActionModeView.getParent() instanceof View) == false) goto L43;
        ViewCompat.requestApplyInsets((View) this.mActionModeView.getParent());
        goto L43
    L45:
        this.mActionMode = null;
        goto L46
    L11:
        if (this.mDestroyed == true) goto L14;
        r03 = r02.onWindowStartingSupportActionMode(r8);     // Catch: AbstractMethodError -> L53
        goto L15
    }

    public void updateBackInvokedCallbackState() {
        if (Build.VERSION.SDK_INT < 33) goto L15;
        boolean r0 = shouldRegisterBackInvokedCallback();
        if (r0 == true) goto L7;
    L10:
        if (r0 == true) goto L16;
        OnBackInvokedCallback r02 = this.mBackCallback;
        if (r02 == null) goto L17;
        Api33Impl.unregisterOnBackInvokedCallback(this.mDispatcher, r02);
        this.mBackCallback = null;
        return;
    L17:
        return;
    L16:
        return;
    L7:
        if (this.mBackCallback != null) goto L10;
        this.mBackCallback = Api33Impl.registerOnBackPressedCallback(this.mDispatcher, this);
        return;
    }

    public final int updateStatusGuard(WindowInsetsCompat r11, Rect r12) {
        int r0 = 0;
        if (r11 == null) goto L5;
        int r1 = r11.getSystemWindowInsetTop();
    L8:
        ActionBarContextView r2 = this.mActionModeView;
        if (r2 != null) goto L11;
    L69:
        boolean r112 = false;
    L70:
        View r10 = this.mStatusGuard;
        if (r10 == null) goto L76;
        if (r112 == true) goto L75;
        r0 = 8;
    L75:
        r10.setVisibility(r0);
    L76:
        return r1;
    L11:
        if ((r2.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) == false) goto L69;
        ViewGroup.MarginLayoutParams r22 = (ViewGroup.MarginLayoutParams) this.mActionModeView.getLayoutParams();
        boolean r5 = true;
        if (this.mActionModeView.isShown() == false) goto L64;
        if (this.mTempRect1 != null) goto L17;
        this.mTempRect1 = new Rect();
        this.mTempRect2 = new Rect();
    L17:
        Rect r4 = this.mTempRect1;
        Rect r6 = this.mTempRect2;
        if (r11 != null) goto L20;
        r4.set(r12);
    L21:
        ViewUtils.computeFitSystemWindows(this.mSubDecor, r4, r6);
        int r113 = r4.top;
        int r122 = r4.left;
        int r42 = r4.right;
        WindowInsetsCompat r62 = ViewCompat.getRootWindowInsets(this.mSubDecor);
        if (r62 != null) goto L24;
        int r7 = 0;
    L25:
        if (r62 != null) goto L27;
        int r63 = 0;
    L29:
        if (r22.topMargin == r113) goto L31;
    L36:
        r22.topMargin = r113;
        r22.leftMargin = r122;
        r22.rightMargin = r42;
        boolean r123 = true;
    L37:
        if (r113 > 0) goto L39;
    L41:
        View r114 = this.mStatusGuard;
        if (r114 == null) goto L50;
        ViewGroup.MarginLayoutParams r115 = (ViewGroup.MarginLayoutParams) r114.getLayoutParams();
        int r43 = r115.height;
        int r8 = r22.topMargin;
        if (r43 == r8) goto L46;
    L49:
        r115.height = r8;
        r115.leftMargin = r7;
        r115.rightMargin = r63;
        this.mStatusGuard.setLayoutParams(r115);
        goto L50
    L46:
        if (r115.leftMargin != r7) goto L49;
        if (r115.rightMargin != r63) goto L49;
    L50:
        View r116 = this.mStatusGuard;
        if (r116 != null) goto L54;
        r5 = false;
    L54:
        if (r5 == false) goto L59;
        if (r116.getVisibility() == 0) goto L59;
        updateStatusGuardColor(this.mStatusGuard);
    L59:
        if (this.mOverlayActionMode == true) goto L62;
        if (r5 == false) goto L62;
        r1 = 0;
    L62:
        r112 = r5;
        r5 = r123;
    L67:
        if (r5 == false) goto L70;
        this.mActionModeView.setLayoutParams(r22);
        goto L70
    L39:
        if (this.mStatusGuard != null) goto L41;
        View r117 = new View(this.mContext);
        this.mStatusGuard = r117;
        r117.setVisibility(8);
        FrameLayout.LayoutParams r118 = new FrameLayout.LayoutParams(-1, r22.topMargin, 51);
        r118.leftMargin = r7;
        r118.rightMargin = r63;
        this.mSubDecor.addView(this.mStatusGuard, -1, r118);
        goto L50
    L31:
        if (r22.leftMargin != r122) goto L36;
        if (r22.rightMargin != r42) goto L36;
        r123 = false;
        goto L37
    L27:
        r63 = r62.getSystemWindowInsetRight();
        goto L29
    L24:
        r7 = r62.getSystemWindowInsetLeft();
        goto L25
    L20:
        r4.set(r11.getSystemWindowInsetLeft(), r11.getSystemWindowInsetTop(), r11.getSystemWindowInsetRight(), r11.getSystemWindowInsetBottom());
        goto L21
    L64:
        if (r22.topMargin == 0) goto L66;
        r22.topMargin = 0;
        r112 = false;
        goto L67
    L66:
        r112 = false;
        r5 = false;
        goto L67
    L5:
        if (r12 == null) goto L7;
        r1 = r12.top;
        goto L8
    L7:
        r1 = 0;
        goto L8
    }
}
