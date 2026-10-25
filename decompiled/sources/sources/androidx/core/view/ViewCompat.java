package androidx.core.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContentInfo;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.core.R$id;
import androidx.core.util.Preconditions;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@SuppressLint({"PrivateConstructorForUtilityClass"})
/* loaded from: classes.dex */
public abstract class ViewCompat {
    private static final int[] ACCESSIBILITY_ACTIONS_RESOURCE_IDS = null;
    private static final OnReceiveContentViewBehavior NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR = null;
    private static boolean sAccessibilityDelegateCheckFailed = false;
    private static Field sAccessibilityDelegateField;
    private static final AccessibilityPaneVisibilityManager sAccessibilityPaneVisibilityManager = null;
    private static WeakHashMap<View, ViewPropertyAnimatorCompat> sViewPropertyAnimatorMap;

    /* renamed from: androidx.core.view.ViewCompat$1, reason: invalid class name */
    public class AnonymousClass1 extends AccessibilityViewProperty<Boolean> {
        public AnonymousClass1(int r1, Class r2, int r3) {
            super(r1, r2, r3);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public Boolean frameworkGet(View r1) {
            return Boolean.valueOf(Api28Impl.isScreenReaderFocusable(r1));
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public /* bridge */ /* synthetic */ Boolean frameworkGet(View r1) {
            return frameworkGet(r1);
        }

        /* renamed from: frameworkSet, reason: avoid collision after fix types in other method */
        public void frameworkSet2(View r1, Boolean r2) {
            Api28Impl.setScreenReaderFocusable(r1, r2.booleanValue());
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public /* bridge */ /* synthetic */ void frameworkSet(View r1, Boolean r2) {
            frameworkSet2(r1, r2);
        }

        /* renamed from: shouldUpdate, reason: avoid collision after fix types in other method */
        public boolean shouldUpdate2(Boolean r1, Boolean r2) {
            return !booleanNullToFalseEquals(r1, r2);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public /* bridge */ /* synthetic */ boolean shouldUpdate(Boolean r1, Boolean r2) {
            return shouldUpdate2(r1, r2);
        }
    }

    /* renamed from: androidx.core.view.ViewCompat$2, reason: invalid class name */
    public class AnonymousClass2 extends AccessibilityViewProperty<CharSequence> {
        public AnonymousClass2(int r1, Class r2, int r3, int r4) {
            super(r1, r2, r3, r4);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public CharSequence frameworkGet(View r1) {
            return Api28Impl.getAccessibilityPaneTitle(r1);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public /* bridge */ /* synthetic */ CharSequence frameworkGet(View r1) {
            return frameworkGet(r1);
        }

        /* renamed from: frameworkSet, reason: avoid collision after fix types in other method */
        public void frameworkSet2(View r1, CharSequence r2) {
            Api28Impl.setAccessibilityPaneTitle(r1, r2);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public /* bridge */ /* synthetic */ void frameworkSet(View r1, CharSequence r2) {
            frameworkSet2(r1, r2);
        }

        /* renamed from: shouldUpdate, reason: avoid collision after fix types in other method */
        public boolean shouldUpdate2(CharSequence r1, CharSequence r2) {
            return !TextUtils.equals(r1, r2);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public /* bridge */ /* synthetic */ boolean shouldUpdate(CharSequence r1, CharSequence r2) {
            return shouldUpdate2(r1, r2);
        }
    }

    /* renamed from: androidx.core.view.ViewCompat$3, reason: invalid class name */
    public class AnonymousClass3 extends AccessibilityViewProperty<CharSequence> {
        public AnonymousClass3(int r1, Class r2, int r3, int r4) {
            super(r1, r2, r3, r4);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public CharSequence frameworkGet(View r1) {
            return Api30Impl.getStateDescription(r1);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public /* bridge */ /* synthetic */ CharSequence frameworkGet(View r1) {
            return frameworkGet(r1);
        }

        /* renamed from: frameworkSet, reason: avoid collision after fix types in other method */
        public void frameworkSet2(View r1, CharSequence r2) {
            Api30Impl.setStateDescription(r1, r2);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public /* bridge */ /* synthetic */ void frameworkSet(View r1, CharSequence r2) {
            frameworkSet2(r1, r2);
        }

        /* renamed from: shouldUpdate, reason: avoid collision after fix types in other method */
        public boolean shouldUpdate2(CharSequence r1, CharSequence r2) {
            return !TextUtils.equals(r1, r2);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public /* bridge */ /* synthetic */ boolean shouldUpdate(CharSequence r1, CharSequence r2) {
            return shouldUpdate2(r1, r2);
        }
    }

    /* renamed from: androidx.core.view.ViewCompat$4, reason: invalid class name */
    public class AnonymousClass4 extends AccessibilityViewProperty<Boolean> {
        public AnonymousClass4(int r1, Class r2, int r3) {
            super(r1, r2, r3);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public Boolean frameworkGet(View r1) {
            return Boolean.valueOf(Api28Impl.isAccessibilityHeading(r1));
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public /* bridge */ /* synthetic */ Boolean frameworkGet(View r1) {
            return frameworkGet(r1);
        }

        /* renamed from: frameworkSet, reason: avoid collision after fix types in other method */
        public void frameworkSet2(View r1, Boolean r2) {
            Api28Impl.setAccessibilityHeading(r1, r2.booleanValue());
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public /* bridge */ /* synthetic */ void frameworkSet(View r1, Boolean r2) {
            frameworkSet2(r1, r2);
        }

        /* renamed from: shouldUpdate, reason: avoid collision after fix types in other method */
        public boolean shouldUpdate2(Boolean r1, Boolean r2) {
            return !booleanNullToFalseEquals(r1, r2);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public /* bridge */ /* synthetic */ boolean shouldUpdate(Boolean r1, Boolean r2) {
            return shouldUpdate2(r1, r2);
        }
    }

    public static class AccessibilityPaneVisibilityManager implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {
        private final WeakHashMap<View, Boolean> mPanesToVisible;

        public AccessibilityPaneVisibilityManager() {
            this.mPanesToVisible = new WeakHashMap();
        }

        private void checkPaneVisibility(Map.Entry<View, Boolean> r3) {
            View r2 = r3.getKey();
            boolean r0 = r3.getValue().booleanValue();
            if (r2.isShown() == true) goto L5;
        L7:
            boolean r1 = false;
        L8:
            if (r0 == r1) goto L14;
            if (r1 == false) goto L11;
            int r02 = 16;
        L12:
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(r2, r02);
            r3.setValue(Boolean.valueOf(r1));
            return;
        L11:
            r02 = 32;
            goto L12
        L14:
            return;
        L5:
            if (r2.getWindowVisibility() != 0) goto L7;
            r1 = true;
            goto L8
        }

        private void registerForLayoutCallback(View r1) {
            r1.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        private void unregisterForLayoutCallback(View r1) {
            r1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }

        public void addAccessibilityPane(View r3) {
            WeakHashMap<View, Boolean> r0 = this.mPanesToVisible;
            if (r3.isShown() == true) goto L5;
        L7:
            boolean r1 = false;
        L8:
            r0.put(r3, Boolean.valueOf(r1));
            r3.addOnAttachStateChangeListener(this);
            if (r3.isAttachedToWindow() == false) goto L12;
            registerForLayoutCallback(r3);
            return;
        L12:
            return;
        L5:
            if (r3.getWindowVisibility() != 0) goto L7;
            r1 = true;
            goto L8
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (Build.VERSION.SDK_INT >= 28) goto L8;
            Iterator<Map.Entry<View, Boolean>> r0 = this.mPanesToVisible.entrySet().iterator();
        L6:
            if (r0.hasNext() == false) goto L10;
            checkPaneVisibility(r0.next());
            goto L6
        L10:
            return;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            registerForLayoutCallback(r1);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
        }

        public void removeAccessibilityPane(View r2) {
            this.mPanesToVisible.remove(r2);
            r2.removeOnAttachStateChangeListener(this);
            unregisterForLayoutCallback(r2);
        }
    }

    public static abstract class AccessibilityViewProperty<T> {
        private final int mContentChangeType;
        private final int mFrameworkMinimumSdk;
        private final int mTagKey;
        private final Class<T> mType;

        public AccessibilityViewProperty(int r2, Class<T> r3, int r4) {
            this(r2, r3, 0, r4);
        }

        public AccessibilityViewProperty(int r1, Class<T> r2, int r3, int r4) {
            this.mTagKey = r1;
            this.mType = r2;
            this.mContentChangeType = r3;
            this.mFrameworkMinimumSdk = r4;
        }

        private boolean frameworkAvailable() {
            if (Build.VERSION.SDK_INT < this.mFrameworkMinimumSdk) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean booleanNullToFalseEquals(Boolean r2, Boolean r3) {
            if (r2 != null) goto L5;
        L7:
            boolean r22 = false;
        L8:
            if (r3 != null) goto L10;
        L12:
            boolean r32 = false;
        L13:
            if (r22 != r32) goto L15;
            return true;
        L15:
            return false;
        L10:
            if (r3.booleanValue() == false) goto L12;
            r32 = true;
            goto L13
        L5:
            if (r2.booleanValue() == false) goto L7;
            r22 = true;
            goto L8
        }

        public abstract T frameworkGet(View r1);

        public abstract void frameworkSet(View r1, T r2);

        public T get(View r2) {
            if (frameworkAvailable() == true) goto L5;
            T r22 = (T) r2.getTag(this.mTagKey);
            if (this.mType.isInstance(r22) == false) goto L9;
            return r22;
        L9:
            return null;
        L5:
            return frameworkGet(r2);
        }

        public void set(View r2, T r3) {
            if (frameworkAvailable() == false) goto L7;
            frameworkSet(r2, r3);
            return;
        L7:
            if (shouldUpdate(get(r2), r3) == false) goto L10;
            ViewCompat.ensureAccessibilityDelegateCompat(r2);
            r2.setTag(this.mTagKey, r3);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(r2, this.mContentChangeType);
            return;
        }

        public abstract boolean shouldUpdate(T r1, T r2);
    }

    /* loaded from: classes3.dex */
    public static class Api20Impl {
        public static WindowInsets dispatchApplyWindowInsets(View r1, WindowInsets r2) {
            if (ViewGroupCompat.sCompatInsetsDispatchInstalled == false) goto L7;
            return ViewGroupCompat.dispatchApplyWindowInsets(r1, r2);
        L7:
            return r1.dispatchApplyWindowInsets(r2);
        }
    }

    public static class Api21Impl {

        /* renamed from: androidx.core.view.ViewCompat$Api21Impl$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnApplyWindowInsetsListener {
            WindowInsetsCompat mLastInsets;
            final /* synthetic */ OnApplyWindowInsetsListener val$listener;
            final /* synthetic */ View val$v;

            public AnonymousClass1(View r1, OnApplyWindowInsetsListener r2) {
                this.val$v = r1;
                this.val$listener = r2;
                this.mLastInsets = null;
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(View r5, WindowInsets r6) {
                WindowInsetsCompat r0 = WindowInsetsCompat.toWindowInsetsCompat(r6, r5);
                int r1 = Build.VERSION.SDK_INT;
                if (r1 >= 30) goto L8;
                Api21Impl.callCompatInsetAnimationCallback(r6, this.val$v);
                if (r0.equals(this.mLastInsets) == false) goto L8;
                return this.val$listener.onApplyWindowInsets(r5, r0).toWindowInsets();
            L8:
                this.mLastInsets = r0;
                WindowInsetsCompat r4 = this.val$listener.onApplyWindowInsets(r5, r0);
                if (r1 >= 30) goto L11;
                ViewCompat.requestApplyInsets(r5);
                return r4.toWindowInsets();
            L11:
                return r4.toWindowInsets();
            }
        }

        public static void callCompatInsetAnimationCallback(WindowInsets r1, View r2) {
            View.OnApplyWindowInsetsListener r0 = (View.OnApplyWindowInsetsListener) r2.getTag(R$id.tag_window_insets_animation_callback);
            if (r0 == null) goto L6;
            r0.onApplyWindowInsets(r2, r1);
            return;
        }

        public static WindowInsetsCompat computeSystemWindowInsets(View r1, WindowInsetsCompat r2, Rect r3) {
            WindowInsets r0 = r2.toWindowInsets();
            if (r0 != null) goto L5;
            r3.setEmpty();
            return r2;
        L5:
            return WindowInsetsCompat.toWindowInsetsCompat(r1.computeSystemWindowInsets(r0, r3), r1);
        }

        public static void setOnApplyWindowInsetsListener(View r2, OnApplyWindowInsetsListener r3) {
            if (r3 == null) goto L4;
            AnonymousClass1 r0 = new AnonymousClass1(r2, r3);
        L6:
            if (Build.VERSION.SDK_INT >= 30) goto L9;
            r2.setTag(R$id.tag_on_apply_window_listener, r0);
        L9:
            if (r2.getTag(R$id.tag_compat_insets_dispatch) == null) goto L11;
            return;
        L11:
            if (r0 == null) goto L14;
            r2.setOnApplyWindowInsetsListener(r0);
            return;
        L14:
            r2.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) r2.getTag(R$id.tag_window_insets_animation_callback));
            return;
        L4:
            r0 = null;
            goto L6
        }
    }

    public static class Api23Impl {
        public static WindowInsetsCompat getRootWindowInsets(View r1) {
            WindowInsets r0 = r1.getRootWindowInsets();
            if (r0 != null) goto L6;
            return null;
        L6:
            WindowInsetsCompat r02 = WindowInsetsCompat.toWindowInsetsCompat(r0);
            r02.setRootWindowInsets(r02);
            r02.init(r1.getRootView());
            return r02;
        }
    }

    /* loaded from: classes3.dex */
    public static class Api24Impl {
        public static void setPointerIcon(View r0, PointerIcon r1) {
            r0.setPointerIcon(r1);
        }
    }

    public static class Api26Impl {
        public static int getImportantForAutofill(View r0) {
            return r0.getImportantForAutofill();
        }

        public static void setImportantForAutofill(View r0, int r1) {
            r0.setImportantForAutofill(r1);
        }
    }

    public static class Api28Impl {
        public static CharSequence getAccessibilityPaneTitle(View r0) {
            return r0.getAccessibilityPaneTitle();
        }

        public static boolean isAccessibilityHeading(View r0) {
            return r0.isAccessibilityHeading();
        }

        public static boolean isScreenReaderFocusable(View r0) {
            return r0.isScreenReaderFocusable();
        }

        public static void setAccessibilityHeading(View r0, boolean r1) {
            r0.setAccessibilityHeading(r1);
        }

        public static void setAccessibilityPaneTitle(View r0, CharSequence r1) {
            r0.setAccessibilityPaneTitle(r1);
        }

        public static void setScreenReaderFocusable(View r0, boolean r1) {
            r0.setScreenReaderFocusable(r1);
        }
    }

    public static class Api29Impl {
        public static View.AccessibilityDelegate getAccessibilityDelegate(View r0) {
            return r0.getAccessibilityDelegate();
        }

        public static void saveAttributeDataForStyleable(View r0, Context r1, int[] r2, AttributeSet r3, TypedArray r4, int r5, int r6) {
            r0.saveAttributeDataForStyleable(r1, r2, r3, r4, r5, r6);
        }

        public static void setSystemGestureExclusionRects(View r0, List<Rect> r1) {
            r0.setSystemGestureExclusionRects(r1);
        }
    }

    public static class Api30Impl {
        public static WindowInsets dispatchApplyWindowInsets(View r0, WindowInsets r1) {
            return r0.dispatchApplyWindowInsets(r1);
        }

        public static CharSequence getStateDescription(View r0) {
            return r0.getStateDescription();
        }

        public static WindowInsetsControllerCompat getWindowInsetsController(View r0) {
            WindowInsetsController r02 = r0.getWindowInsetsController();
            if (r02 != null) goto L5;
            return null;
        L5:
            return WindowInsetsControllerCompat.toWindowInsetsControllerCompat(r02);
        }

        public static void setStateDescription(View r0, CharSequence r1) {
            r0.setStateDescription(r1);
        }
    }

    /* loaded from: classes3.dex */
    public static final class Api31Impl {
        public static String[] getReceiveContentMimeTypes(View r0) {
            return r0.getReceiveContentMimeTypes();
        }

        public static ContentInfoCompat performReceiveContent(View r1, ContentInfoCompat r2) {
            ContentInfo r0 = r2.toContentInfo();
            ContentInfo r12 = r1.performReceiveContent(r0);
            if (r12 != null) goto L6;
            return null;
        L6:
            if (r12 != r0) goto L9;
            return r2;
        L9:
            return ContentInfoCompat.toContentInfoCompat(r12);
        }

        public static void setOnReceiveContentListener(View r1, String[] r2, OnReceiveContentListener r3) {
            if (r3 != null) goto L5;
            r1.setOnReceiveContentListener(r2, null);
            return;
        L5:
            r1.setOnReceiveContentListener(r2, new OnReceiveContentListenerAdapter(r3));
        }
    }

    /* loaded from: classes3.dex */
    public static final class OnReceiveContentListenerAdapter implements android.view.OnReceiveContentListener {
        private final OnReceiveContentListener mJetpackListener;

        public OnReceiveContentListenerAdapter(OnReceiveContentListener r1) {
            this.mJetpackListener = r1;
        }

        public ContentInfo onReceiveContent(View r2, ContentInfo r3) {
            ContentInfoCompat r0 = ContentInfoCompat.toContentInfoCompat(r3);
            ContentInfoCompat r1 = this.mJetpackListener.onReceiveContent(r2, r0);
            if (r1 != null) goto L6;
            return null;
        L6:
            if (r1 != r0) goto L9;
            return r3;
        L9:
            return r1.toContentInfo();
        }
    }

    /* loaded from: classes3.dex */
    public static class UnhandledKeyEventManager {
        private static final ArrayList<WeakReference<View>> sViewsWithListeners = null;
        private SparseArray<WeakReference<View>> mCapturedKeys;
        private WeakReference<KeyEvent> mLastDispatchedPreViewKeyEvent;
        private WeakHashMap<View, Boolean> mViewsContainingListeners;

        static {
            sViewsWithListeners = new ArrayList();
        }

        public UnhandledKeyEventManager() {
            this.mViewsContainingListeners = null;
            this.mCapturedKeys = null;
            this.mLastDispatchedPreViewKeyEvent = null;
        }

        public static UnhandledKeyEventManager at(View r2) {
            UnhandledKeyEventManager r0 = (UnhandledKeyEventManager) r2.getTag(R$id.tag_unhandled_key_event_manager);
            if (r0 != null) goto L6;
            UnhandledKeyEventManager r02 = new UnhandledKeyEventManager();
            r2.setTag(R$id.tag_unhandled_key_event_manager, r02);
            return r02;
        L6:
            return r0;
        }

        private View dispatchInOrder(View r5, KeyEvent r6) {
            WeakHashMap<View, Boolean> r0 = this.mViewsContainingListeners;
            if (r0 != null) goto L5;
        L18:
            return null;
        L5:
            if (r0.containsKey(r5) == false) goto L18;
            if ((r5 instanceof ViewGroup) == false) goto L16;
            ViewGroup r02 = (ViewGroup) r5;
            int r2 = r02.getChildCount() - 1;
        L10:
            if (r2 < 0) goto L16;
            View r3 = dispatchInOrder(r02.getChildAt(r2), r6);
            if (r3 != null) goto L13;
            r2 = r2 - 1;
            goto L10
        L13:
            return r3;
        L16:
            if (onUnhandledKeyEvent(r5, r6) == false) goto L18;
            return r5;
        }

        private SparseArray<WeakReference<View>> getCapturedKeys() {
            if (this.mCapturedKeys != null) goto L6;
            this.mCapturedKeys = new SparseArray();
        L6:
            return this.mCapturedKeys;
        }

        private boolean onUnhandledKeyEvent(View r1, KeyEvent r2) {
            ArrayList r0 = (ArrayList) r1.getTag(R$id.tag_unhandled_key_listeners);
            if (r0 == null) goto L9;
            int r12 = r0.size() - 1;
            if (r12 < 0) goto L11;
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.get(r12));
            throw null;
        L11:
            return false;
        L9:
            return false;
        }

        private void recalcViewsWithUnhandled() {
            WeakHashMap<View, Boolean> r0 = this.mViewsContainingListeners;
            if (r0 == null) goto L5;
            r0.clear();
        L5:
            ArrayList<WeakReference<View>> r02 = sViewsWithListeners;
            if (r02.isEmpty() == false) goto L8;
            return;
        L8:
            monitor-enter(r02);
        L12:
            th = move-exception;
            throw th;
        L10:
            if (this.mViewsContainingListeners != null) goto L14;
            this.mViewsContainingListeners = new WeakHashMap();     // Catch: Throwable -> L12
        L14:
            int r1 = r02.size() - 1;
        L15:
            if (r1 < 0) goto L24;
            ArrayList<WeakReference<View>> r2 = sViewsWithListeners;     // Catch: Throwable -> L12
            View r3 = r2.get(r1).get();     // Catch: Throwable -> L12
            if (r3 != null) goto L19;
            r2.remove(r1);     // Catch: Throwable -> L12
        L23:
            r1 = r1 - 1;
            goto L15
        L19:
            this.mViewsContainingListeners.put(r3, Boolean.TRUE);     // Catch: Throwable -> L12
            ViewParent r22 = r3.getParent();     // Catch: Throwable -> L12
        L21:
            if ((r22 instanceof View) == false) goto L23;
            this.mViewsContainingListeners.put((View) r22, Boolean.TRUE);     // Catch: Throwable -> L12
            r22 = r22.getParent();     // Catch: Throwable -> L12
            goto L21
        L24:
            monitor-exit(r02);     // Catch: Throwable -> L12
        }

        public boolean dispatch(View r2, KeyEvent r3) {
            if (r3.getAction() != 0) goto L5;
            recalcViewsWithUnhandled();
        L5:
            View r22 = dispatchInOrder(r2, r3);
            if (r3.getAction() != 0) goto L12;
            int r32 = r3.getKeyCode();
            if (r22 == null) goto L12;
            if (KeyEvent.isModifierKey(r32) == true) goto L12;
            getCapturedKeys().put(r32, new WeakReference(r22));
        L12:
            if (r22 == null) goto L15;
            return true;
        L15:
            return false;
        }

        public boolean preDispatch(KeyEvent r6) {
            WeakReference<KeyEvent> r0 = this.mLastDispatchedPreViewKeyEvent;
            if (r0 != null) goto L5;
        L7:
            this.mLastDispatchedPreViewKeyEvent = new WeakReference(r6);
            SparseArray<WeakReference<View>> r02 = getCapturedKeys();
            if (r6.getAction() != 1) goto L12;
            int r2 = r02.indexOfKey(r6.getKeyCode());
            if (r2 < 0) goto L12;
            WeakReference<View> r4 = r02.valueAt(r2);
            r02.removeAt(r2);
        L13:
            if (r4 != null) goto L15;
            r4 = r02.get(r6.getKeyCode());
        L15:
            if (r4 == null) goto L22;
            View r03 = r4.get();
            if (r03 != null) goto L19;
        L21:
            return true;
        L19:
            if (r03.isAttachedToWindow() == false) goto L21;
            onUnhandledKeyEvent(r03, r6);
            goto L21
        L22:
            return false;
        L12:
            r4 = null;
            goto L13
        L5:
            if (r0.get() != r6) goto L7;
            return false;
        }
    }

    public static /* synthetic */ ContentInfoCompat $r8$lambda$mRexKut8PHPxTytR38MAzVT9ekI(ContentInfoCompat r0) {
        return r0;
    }

    static {
        ACCESSIBILITY_ACTIONS_RESOURCE_IDS = new int[]{R$id.accessibility_custom_action_0, R$id.accessibility_custom_action_1, R$id.accessibility_custom_action_2, R$id.accessibility_custom_action_3, R$id.accessibility_custom_action_4, R$id.accessibility_custom_action_5, R$id.accessibility_custom_action_6, R$id.accessibility_custom_action_7, R$id.accessibility_custom_action_8, R$id.accessibility_custom_action_9, R$id.accessibility_custom_action_10, R$id.accessibility_custom_action_11, R$id.accessibility_custom_action_12, R$id.accessibility_custom_action_13, R$id.accessibility_custom_action_14, R$id.accessibility_custom_action_15, R$id.accessibility_custom_action_16, R$id.accessibility_custom_action_17, R$id.accessibility_custom_action_18, R$id.accessibility_custom_action_19, R$id.accessibility_custom_action_20, R$id.accessibility_custom_action_21, R$id.accessibility_custom_action_22, R$id.accessibility_custom_action_23, R$id.accessibility_custom_action_24, R$id.accessibility_custom_action_25, R$id.accessibility_custom_action_26, R$id.accessibility_custom_action_27, R$id.accessibility_custom_action_28, R$id.accessibility_custom_action_29, R$id.accessibility_custom_action_30, R$id.accessibility_custom_action_31};
        NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR = new ViewCompat$$ExternalSyntheticLambda0();
        sAccessibilityPaneVisibilityManager = new AccessibilityPaneVisibilityManager();
    }

    private static AccessibilityViewProperty<Boolean> accessibilityHeadingProperty() {
        return new AnonymousClass4(R$id.tag_accessibility_heading, Boolean.class, 28);
    }

    public static int addAccessibilityAction(View r2, CharSequence r3, AccessibilityViewCommand r4) {
        int r0 = getAvailableActionIdFromResources(r2, r3);
        if (r0 == (-1)) goto L5;
        addAccessibilityAction(r2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(r0, r3, r4));
    L5:
        return r0;
    }

    private static void addAccessibilityAction(View r1, AccessibilityNodeInfoCompat.AccessibilityActionCompat r2) {
        ensureAccessibilityDelegateCompat(r1);
        removeActionWithId(r2.getId(), r1);
        getActionList(r1).add(r2);
        notifyViewAccessibilityStateChangedIfNeeded(r1, 0);
    }

    @Deprecated
    public static ViewPropertyAnimatorCompat animate(View r2) {
        if (sViewPropertyAnimatorMap != null) goto L5;
        sViewPropertyAnimatorMap = new WeakHashMap();
    L5:
        ViewPropertyAnimatorCompat r0 = sViewPropertyAnimatorMap.get(r2);
        if (r0 != null) goto L9;
        ViewPropertyAnimatorCompat r02 = new ViewPropertyAnimatorCompat(r2);
        sViewPropertyAnimatorMap.put(r2, r02);
        return r02;
    L9:
        return r0;
    }

    public static WindowInsetsCompat computeSystemWindowInsets(View r0, WindowInsetsCompat r1, Rect r2) {
        return Api21Impl.computeSystemWindowInsets(r0, r1, r2);
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View r3, WindowInsetsCompat r4) {
        WindowInsets r0 = r4.toWindowInsets();
        if (r0 != null) goto L5;
    L12:
        return r4;
    L5:
        if (Build.VERSION.SDK_INT < 30) goto L7;
        WindowInsets r1 = Api30Impl.dispatchApplyWindowInsets(r3, r0);
    L9:
        if (r1.equals(r0) == true) goto L12;
        return WindowInsetsCompat.toWindowInsetsCompat(r1, r3);
    L7:
        r1 = Api20Impl.dispatchApplyWindowInsets(r3, r0);
        goto L9
    }

    public static boolean dispatchUnhandledKeyEventBeforeCallback(View r2, KeyEvent r3) {
        if (Build.VERSION.SDK_INT < 28) goto L7;
        return false;
    L7:
        return UnhandledKeyEventManager.at(r2).dispatch(r2, r3);
    }

    public static boolean dispatchUnhandledKeyEventBeforeHierarchy(View r2, KeyEvent r3) {
        if (Build.VERSION.SDK_INT < 28) goto L7;
        return false;
    L7:
        return UnhandledKeyEventManager.at(r2).preDispatch(r3);
    }

    public static void ensureAccessibilityDelegateCompat(View r1) {
        AccessibilityDelegateCompat r0 = getAccessibilityDelegate(r1);
        if (r0 != null) goto L5;
        r0 = new AccessibilityDelegateCompat();
    L5:
        setAccessibilityDelegate(r1, r0);
    }

    public static AccessibilityDelegateCompat getAccessibilityDelegate(View r1) {
        View.AccessibilityDelegate r12 = getAccessibilityDelegateInternal(r1);
        if (r12 != null) goto L7;
        return null;
    L7:
        if ((r12 instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter) == false) goto L11;
        return ((AccessibilityDelegateCompat.AccessibilityDelegateAdapter) r12).mCompat;
    L11:
        return new AccessibilityDelegateCompat(r12);
    }

    private static View.AccessibilityDelegate getAccessibilityDelegateInternal(View r2) {
        if (Build.VERSION.SDK_INT < 29) goto L7;
        return Api29Impl.getAccessibilityDelegate(r2);
    L7:
        return getAccessibilityDelegateThroughReflection(r2);
    }

    private static View.AccessibilityDelegate getAccessibilityDelegateThroughReflection(View r4) {
        if (sAccessibilityDelegateCheckFailed == false) goto L6;
        return null;
    L6:
        if (sAccessibilityDelegateField != null) goto L20;
        Field r0 = View.class.getDeclaredField("mAccessibilityDelegate");     // Catch: Throwable -> L9
        sAccessibilityDelegateField = r0;     // Catch: Throwable -> L9
        r0.setAccessible(true);     // Catch: Throwable -> L9
    L9:
        sAccessibilityDelegateCheckFailed = true;
        return null;
    L20:
        Object r42 = sAccessibilityDelegateField.get(r4);     // Catch: Throwable -> L16
        if ((r42 instanceof View.AccessibilityDelegate) == false) goto L15;
        return (View.AccessibilityDelegate) r42;
    L15:
        return null;
    L16:
        sAccessibilityDelegateCheckFailed = true;
        return null;
    }

    public static CharSequence getAccessibilityPaneTitle(View r1) {
        return paneTitleProperty().get(r1);
    }

    private static List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> getActionList(View r2) {
        ArrayList r0 = (ArrayList) r2.getTag(R$id.tag_accessibility_actions);
        if (r0 != null) goto L6;
        ArrayList r02 = new ArrayList();
        r2.setTag(R$id.tag_accessibility_actions, r02);
        return r02;
    L6:
        return r0;
    }

    private static int getAvailableActionIdFromResources(View r8, CharSequence r9) {
        List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> r82 = getActionList(r8);
        int r1 = 0;
    L4:
        if (r1 >= r82.size()) goto L10;
        if (TextUtils.equals(r9, r82.get(r1).getLabel()) == true) goto L8;
        r1 = r1 + 1;
        goto L4
    L8:
        return r82.get(r1).getId();
    L10:
        int r2 = -1;
        int r12 = 0;
    L11:
        int[] r3 = ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
        if (r12 >= r3.length) goto L25;
        if (r2 != (-1)) goto L25;
        int r32 = r3[r12];
        int r5 = 0;
        boolean r6 = true;
    L16:
        if (r5 >= r82.size()) goto L22;
        if (r82.get(r5).getId() == r32) goto L20;
        boolean r7 = true;
    L21:
        r6 = r6 & r7;
        r5 = r5 + 1;
        goto L16
    L20:
        r7 = false;
        goto L21
    L22:
        if (r6 == false) goto L24;
        r2 = r32;
    L24:
        r12 = r12 + 1;
    L25:
        return r2;
    }

    public static ColorStateList getBackgroundTintList(View r0) {
        return r0.getBackgroundTintList();
    }

    public static PorterDuff.Mode getBackgroundTintMode(View r0) {
        return r0.getBackgroundTintMode();
    }

    @Deprecated
    public static Display getDisplay(View r0) {
        return r0.getDisplay();
    }

    public static float getElevation(View r0) {
        return r0.getElevation();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static OnReceiveContentViewBehavior getFallback(View r1) {
        if ((r1 instanceof OnReceiveContentViewBehavior) == false) goto L7;
        return (OnReceiveContentViewBehavior) r1;
    L7:
        return NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR;
    }

    @Deprecated
    public static boolean getFitsSystemWindows(View r0) {
        return r0.getFitsSystemWindows();
    }

    @Deprecated
    public static int getImportantForAccessibility(View r0) {
        return r0.getImportantForAccessibility();
    }

    @SuppressLint({"InlinedApi"})
    public static int getImportantForAutofill(View r2) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return 0;
    L5:
        return Api26Impl.getImportantForAutofill(r2);
    }

    @Deprecated
    public static int getLayoutDirection(View r0) {
        return r0.getLayoutDirection();
    }

    @Deprecated
    public static int getMinimumHeight(View r0) {
        return r0.getMinimumHeight();
    }

    @Deprecated
    public static int getMinimumWidth(View r0) {
        return r0.getMinimumWidth();
    }

    public static String[] getOnReceiveContentMimeTypes(View r2) {
        if (Build.VERSION.SDK_INT < 31) goto L7;
        return Api31Impl.getReceiveContentMimeTypes(r2);
    L7:
        return (String[]) r2.getTag(R$id.tag_on_receive_content_mime_types);
    }

    @Deprecated
    public static int getPaddingEnd(View r0) {
        return r0.getPaddingEnd();
    }

    @Deprecated
    public static int getPaddingStart(View r0) {
        return r0.getPaddingStart();
    }

    public static WindowInsetsCompat getRootWindowInsets(View r0) {
        return Api23Impl.getRootWindowInsets(r0);
    }

    public static CharSequence getStateDescription(View r1) {
        return stateDescriptionProperty().get(r1);
    }

    public static String getTransitionName(View r0) {
        return r0.getTransitionName();
    }

    public static float getTranslationZ(View r0) {
        return r0.getTranslationZ();
    }

    @Deprecated
    public static WindowInsetsControllerCompat getWindowInsetsController(View r3) {
        if (Build.VERSION.SDK_INT >= 30) goto L5;
        Context r0 = r3.getContext();
    L8:
        if ((r0 instanceof ContextWrapper) == false) goto L17;
        if ((r0 instanceof Activity) == true) goto L11;
        r0 = ((ContextWrapper) r0).getBaseContext();
        goto L8
    L11:
        Window r02 = ((Activity) r0).getWindow();
        if (r02 != null) goto L14;
        return null;
    L14:
        return WindowCompat.getInsetsController(r02, r3);
    L17:
        return null;
    L5:
        return Api30Impl.getWindowInsetsController(r3);
    }

    @Deprecated
    public static int getWindowSystemUiVisibility(View r0) {
        return r0.getWindowSystemUiVisibility();
    }

    public static float getZ(View r0) {
        return r0.getZ();
    }

    public static boolean hasAccessibilityDelegate(View r0) {
        if (getAccessibilityDelegateInternal(r0) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Deprecated
    public static boolean hasTransientState(View r0) {
        return r0.hasTransientState();
    }

    public static boolean isAccessibilityHeading(View r1) {
        Boolean r12 = accessibilityHeadingProperty().get(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        if (r12.booleanValue() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Deprecated
    public static boolean isAttachedToWindow(View r0) {
        return r0.isAttachedToWindow();
    }

    @Deprecated
    public static boolean isLaidOut(View r0) {
        return r0.isLaidOut();
    }

    public static boolean isScreenReaderFocusable(View r1) {
        Boolean r12 = screenReaderFocusableProperty().get(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        if (r12.booleanValue() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static void notifyViewAccessibilityStateChangedIfNeeded(View r4, int r5) {
        AccessibilityManager r0 = (AccessibilityManager) r4.getContext().getSystemService("accessibility");
        if (r0.isEnabled() == true) goto L6;
        return;
    L6:
        if (getAccessibilityPaneTitle(r4) != null) goto L8;
    L12:
        boolean r1 = false;
    L13:
        int r3 = 32;
        if (r4.getAccessibilityLiveRegion() != 0) goto L28;
        if (r1 == true) goto L28;
        if (r5 != 32) goto L21;
        AccessibilityEvent r12 = AccessibilityEvent.obtain();
        r4.onInitializeAccessibilityEvent(r12);
        r12.setEventType(32);
        r12.setContentChangeTypes(r5);
        r12.setSource(r4);
        r4.onPopulateAccessibilityEvent(r12);
        r12.getText().add(getAccessibilityPaneTitle(r4));
        r0.sendAccessibilityEvent(r12);
        return;
    L21:
        if (r4.getParent() != null) goto L37;
        return;
    L37:
        r4.getParent().notifySubtreeAccessibilityStateChanged(r4, r4, r5);     // Catch: AbstractMethodError -> L25
        return;
    L25:
        e = move-exception;
        Log.e("ViewCompat", r4.getParent().getClass().getSimpleName().concat(" does not fully implement ViewParent"), e);
        return;
    L28:
        AccessibilityEvent r02 = AccessibilityEvent.obtain();
        if (r1 == true) goto L32;
        r3 = 2048;
    L32:
        r02.setEventType(r3);
        r02.setContentChangeTypes(r5);
        if (r1 == false) goto L35;
        r02.getText().add(getAccessibilityPaneTitle(r4));
        setImportantForAccessibilityIfNeeded(r4);
    L35:
        r4.sendAccessibilityEventUnchecked(r02);
        return;
    L8:
        if (r4.isShown() == false) goto L12;
        if (r4.getWindowVisibility() != 0) goto L12;
        r1 = true;
        goto L13
    }

    public static void offsetLeftAndRight(View r0, int r1) {
        r0.offsetLeftAndRight(r1);
    }

    public static void offsetTopAndBottom(View r0, int r1) {
        r0.offsetTopAndBottom(r1);
    }

    public static WindowInsetsCompat onApplyWindowInsets(View r2, WindowInsetsCompat r3) {
        WindowInsets r0 = r3.toWindowInsets();
        if (r0 == null) goto L8;
        WindowInsets r1 = r2.onApplyWindowInsets(r0);
        if (r1.equals(r0) == true) goto L8;
        return WindowInsetsCompat.toWindowInsetsCompat(r1, r2);
    L8:
        return r3;
    }

    @Deprecated
    public static void onInitializeAccessibilityNodeInfo(View r0, AccessibilityNodeInfoCompat r1) {
        r0.onInitializeAccessibilityNodeInfo(r1.unwrap());
    }

    private static AccessibilityViewProperty<CharSequence> paneTitleProperty() {
        return new AnonymousClass2(R$id.tag_accessibility_pane_title, CharSequence.class, 8, 28);
    }

    public static boolean performHapticFeedback(View r1, int r2) {
        int r22 = HapticFeedbackConstantsCompat.getFeedbackConstantOrFallback(r2);
        if (r22 != (-1)) goto L7;
        return false;
    L7:
        return r1.performHapticFeedback(r22);
    }

    public static ContentInfoCompat performReceiveContent(View r3, ContentInfoCompat r4) {
        if (Log.isLoggable("ViewCompat", 3) == false) goto L6;
        Log.d("ViewCompat", "performReceiveContent: " + r4 + ", view=" + r3.getClass().getSimpleName() + "[" + r3.getId() + "]");
    L6:
        if (Build.VERSION.SDK_INT >= 31) goto L8;
        OnReceiveContentListener r0 = (OnReceiveContentListener) r3.getTag(R$id.tag_on_receive_content_listener);
        if (r0 == null) goto L18;
        ContentInfoCompat r42 = r0.onReceiveContent(r3, r4);
        if (r42 != null) goto L16;
        return null;
    L16:
        return getFallback(r3).onReceiveContent(r42);
    L18:
        return getFallback(r3).onReceiveContent(r4);
    L8:
        return Api31Impl.performReceiveContent(r3, r4);
    }

    @Deprecated
    public static void postInvalidateOnAnimation(View r0) {
        r0.postInvalidateOnAnimation();
    }

    @Deprecated
    public static void postOnAnimation(View r0, Runnable r1) {
        r0.postOnAnimation(r1);
    }

    @SuppressLint({"LambdaLast"})
    @Deprecated
    public static void postOnAnimationDelayed(View r0, Runnable r1, long r2) {
        r0.postOnAnimationDelayed(r1, r2);
    }

    public static void removeAccessibilityAction(View r0, int r1) {
        removeActionWithId(r1, r0);
        notifyViewAccessibilityStateChangedIfNeeded(r0, 0);
    }

    private static void removeActionWithId(int r2, View r3) {
        List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> r32 = getActionList(r3);
        int r0 = 0;
    L4:
        if (r0 >= r32.size()) goto L10;
        if (r32.get(r0).getId() == r2) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        r32.remove(r0);
        return;
    }

    public static void replaceAccessibilityAction(View r0, AccessibilityNodeInfoCompat.AccessibilityActionCompat r1, CharSequence r2, AccessibilityViewCommand r3) {
        if (r3 != null) goto L6;
        if (r2 != null) goto L6;
        removeAccessibilityAction(r0, r1.getId());
        return;
    L6:
        addAccessibilityAction(r0, r1.createReplacementAction(r2, r3));
    }

    public static void requestApplyInsets(View r0) {
        r0.requestApplyInsets();
    }

    public static void saveAttributeDataForStyleable(View r2, @SuppressLint({"ContextFirst"}) Context r3, int[] r4, AttributeSet r5, TypedArray r6, int r7, int r8) {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        Api29Impl.saveAttributeDataForStyleable(r2, r3, r4, r5, r6, r7, r8);
        return;
    }

    private static AccessibilityViewProperty<Boolean> screenReaderFocusableProperty() {
        return new AnonymousClass1(R$id.tag_screen_reader_focusable, Boolean.class, 28);
    }

    public static void setAccessibilityDelegate(View r1, AccessibilityDelegateCompat r2) {
        if (r2 == null) goto L4;
    L6:
        setImportantForAccessibilityIfNeeded(r1);
        if (r2 != null) goto L9;
        View.AccessibilityDelegate r22 = null;
    L10:
        r1.setAccessibilityDelegate(r22);
        return;
    L9:
        r22 = r2.getBridge();
        goto L10
    L4:
        if ((getAccessibilityDelegateInternal(r1) instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter) == false) goto L6;
        r2 = new AccessibilityDelegateCompat();
        goto L6
    }

    public static void setAccessibilityHeading(View r1, boolean r2) {
        accessibilityHeadingProperty().set(r1, Boolean.valueOf(r2));
    }

    public static void setAccessibilityPaneTitle(View r1, CharSequence r2) {
        paneTitleProperty().set(r1, r2);
        if (r2 == null) goto L6;
        sAccessibilityPaneVisibilityManager.addAccessibilityPane(r1);
        return;
    L6:
        sAccessibilityPaneVisibilityManager.removeAccessibilityPane(r1);
    }

    public static void setBackgroundTintList(View r0, ColorStateList r1) {
        r0.setBackgroundTintList(r1);
    }

    public static void setBackgroundTintMode(View r0, PorterDuff.Mode r1) {
        r0.setBackgroundTintMode(r1);
    }

    public static void setElevation(View r0, float r1) {
        r0.setElevation(r1);
    }

    @Deprecated
    public static void setImportantForAccessibility(View r0, int r1) {
        r0.setImportantForAccessibility(r1);
    }

    private static void setImportantForAccessibilityIfNeeded(View r1) {
        if (r1.getImportantForAccessibility() != 0) goto L6;
        r1.setImportantForAccessibility(1);
        return;
    }

    public static void setImportantForAutofill(View r2, int r3) {
        if (Build.VERSION.SDK_INT < 26) goto L6;
        Api26Impl.setImportantForAutofill(r2, r3);
        return;
    }

    public static void setOnApplyWindowInsetsListener(View r0, OnApplyWindowInsetsListener r1) {
        Api21Impl.setOnApplyWindowInsetsListener(r0, r1);
    }

    public static void setOnReceiveContentListener(View r6, String[] r7, OnReceiveContentListener r8) {
        if (Build.VERSION.SDK_INT < 31) goto L6;
        Api31Impl.setOnReceiveContentListener(r6, r7, r8);
        return;
    L6:
        if (r7 != null) goto L8;
    L9:
        r7 = null;
    L10:
        boolean r0 = false;
        if (r8 == null) goto L16;
        if (r7 == null) goto L14;
        boolean r2 = true;
    L15:
        Preconditions.checkArgument(r2, "When the listener is set, MIME types must also be set");
        goto L16
    L14:
        r2 = false;
    L16:
        if (r7 == null) goto L24;
        int r22 = r7.length;
        int r3 = 0;
    L18:
        if (r3 >= r22) goto L23;
        if (r7[r3].startsWith("*") == true) goto L21;
        r3 = r3 + 1;
        goto L18
    L21:
        r0 = true;
    L23:
        Preconditions.checkArgument(!r0, "A MIME type set here must not start with *: " + Arrays.toString(r7));
    L24:
        r6.setTag(R$id.tag_on_receive_content_mime_types, r7);
        r6.setTag(R$id.tag_on_receive_content_listener, r8);
        return;
    L8:
        if (r7.length != 0) goto L10;
        goto L9
    }

    public static void setPointerIcon(View r0, PointerIconCompat r1) {
        if (r1 == null) goto L4;
        Object r12 = r1.getPointerIcon();
    L5:
        Api24Impl.setPointerIcon(r0, (PointerIcon) r12);
        return;
    L4:
        r12 = null;
        goto L5
    }

    public static void setScreenReaderFocusable(View r1, boolean r2) {
        screenReaderFocusableProperty().set(r1, Boolean.valueOf(r2));
    }

    public static void setScrollIndicators(View r0, int r1, int r2) {
        r0.setScrollIndicators(r1, r2);
    }

    public static void setStateDescription(View r1, CharSequence r2) {
        stateDescriptionProperty().set(r1, r2);
    }

    public static void setSystemGestureExclusionRects(View r2, List<Rect> r3) {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        Api29Impl.setSystemGestureExclusionRects(r2, r3);
        return;
    }

    public static void setTransitionName(View r0, String r1) {
        r0.setTransitionName(r1);
    }

    public static void setTranslationZ(View r0, float r1) {
        r0.setTranslationZ(r1);
    }

    public static void setWindowInsetsAnimationCallback(View r0, WindowInsetsAnimationCompat.Callback r1) {
        WindowInsetsAnimationCompat.setCallback(r0, r1);
    }

    public static void setZ(View r0, float r1) {
        r0.setZ(r1);
    }

    private static AccessibilityViewProperty<CharSequence> stateDescriptionProperty() {
        return new AnonymousClass3(R$id.tag_state_description, CharSequence.class, 64, 30);
    }

    public static void stopNestedScroll(View r0) {
        r0.stopNestedScroll();
    }
}
