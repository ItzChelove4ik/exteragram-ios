package androidx.customview.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import androidx.customview.widget.FocusStrategy;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.List;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    private static final String DEFAULT_CLASS_NAME = "android.view.View";
    public static final int HOST_ID = -1;
    private static final Rect INVALID_BOUNDS = null;
    public static final int INVALID_ID = Integer.MIN_VALUE;
    private static final FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat> NODE_ADAPTER = null;
    private static final FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> SPARSE_VALUES_ADAPTER = null;
    int mAccessibilityFocusedVirtualViewId;
    private final View mHost;
    private int mHoveredVirtualViewId;
    int mKeyboardFocusedVirtualViewId;
    private final AccessibilityManager mManager;
    private MyNodeProvider mNodeProvider;
    private final int[] mTempGlobalRect;
    private final Rect mTempParentRect;
    private final Rect mTempScreenRect;
    private final Rect mTempVisibleRect;

    /* renamed from: androidx.customview.widget.ExploreByTouchHelper$1, reason: invalid class name */
    public class AnonymousClass1 implements FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat> {
        public AnonymousClass1() {
        }

        /* renamed from: obtainBounds, reason: avoid collision after fix types in other method */
        public void obtainBounds2(AccessibilityNodeInfoCompat r1, Rect r2) {
            r1.getBoundsInScreen(r2);
        }

        @Override // androidx.customview.widget.FocusStrategy.BoundsAdapter
        public /* bridge */ /* synthetic */ void obtainBounds(AccessibilityNodeInfoCompat r1, Rect r2) {
            obtainBounds2(r1, r2);
        }
    }

    /* renamed from: androidx.customview.widget.ExploreByTouchHelper$2, reason: invalid class name */
    public class AnonymousClass2 implements FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> {
        public AnonymousClass2() {
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public AccessibilityNodeInfoCompat get2(SparseArrayCompat<AccessibilityNodeInfoCompat> r1, int r2) {
            return r1.valueAt(r2);
        }

        @Override // androidx.customview.widget.FocusStrategy.CollectionAdapter
        public /* bridge */ /* synthetic */ AccessibilityNodeInfoCompat get(SparseArrayCompat<AccessibilityNodeInfoCompat> r1, int r2) {
            return get2(r1, r2);
        }

        /* renamed from: size, reason: avoid collision after fix types in other method */
        public int size2(SparseArrayCompat<AccessibilityNodeInfoCompat> r1) {
            return r1.size();
        }

        @Override // androidx.customview.widget.FocusStrategy.CollectionAdapter
        public /* bridge */ /* synthetic */ int size(SparseArrayCompat<AccessibilityNodeInfoCompat> r1) {
            return size2(r1);
        }
    }

    public class MyNodeProvider extends AccessibilityNodeProviderCompat {
        final /* synthetic */ ExploreByTouchHelper this$0;

        public MyNodeProvider(ExploreByTouchHelper r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int r1) {
            return AccessibilityNodeInfoCompat.obtain(this.this$0.obtainAccessibilityNodeInfo(r1));
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat findFocus(int r3) {
            ExploreByTouchHelper r0 = this.this$0;
            if (r3 != 2) goto L5;
            int r32 = r0.mAccessibilityFocusedVirtualViewId;
        L7:
            if (r32 != Integer.MIN_VALUE) goto L11;
            return null;
        L11:
            return createAccessibilityNodeInfo(r32);
        L5:
            r32 = r0.mKeyboardFocusedVirtualViewId;
            goto L7
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public boolean performAction(int r1, int r2, Bundle r3) {
            return this.this$0.performAction(r1, r2, r3);
        }
    }

    static {
        INVALID_BOUNDS = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        NODE_ADAPTER = new AnonymousClass1();
        SPARSE_VALUES_ADAPTER = new AnonymousClass2();
    }

    public ExploreByTouchHelper(View r3) {
        this.mTempScreenRect = new Rect();
        this.mTempParentRect = new Rect();
        this.mTempVisibleRect = new Rect();
        this.mTempGlobalRect = new int[2];
        this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        this.mHoveredVirtualViewId = Integer.MIN_VALUE;
        if (r3 == null) goto L8;
        this.mHost = r3;
        this.mManager = (AccessibilityManager) r3.getContext().getSystemService("accessibility");
        r3.setFocusable(true);
        if (r3.getImportantForAccessibility() != 0) goto L10;
        r3.setImportantForAccessibility(1);
        return;
    L10:
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("View may not be null");
        throw null;
    }

    private boolean clearAccessibilityFocus(int r2) {
        if (this.mAccessibilityFocusedVirtualViewId != r2) goto L6;
        this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
        this.mHost.invalidate();
        sendEventForVirtualView(r2, 65536);
        return true;
    L6:
        return false;
    }

    private boolean clickKeyboardFocusedVirtualView() {
        int r0 = this.mKeyboardFocusedVirtualViewId;
        if (r0 != Integer.MIN_VALUE) goto L5;
        return false;
    L5:
        if (onPerformActionForVirtualView(r0, 16, null) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private AccessibilityEvent createEvent(int r2, int r3) {
        if (r2 == (-1)) goto L7;
        return createEventForChild(r2, r3);
    L7:
        return createEventForHost(r3);
    }

    private AccessibilityEvent createEventForChild(int r4, int r5) {
        AccessibilityEvent r52 = AccessibilityEvent.obtain(r5);
        AccessibilityNodeInfoCompat r0 = obtainAccessibilityNodeInfo(r4);
        r52.getText().add(r0.getText());
        r52.setContentDescription(r0.getContentDescription());
        r52.setScrollable(r0.isScrollable());
        r52.setPassword(r0.isPassword());
        r52.setEnabled(r0.isEnabled());
        r52.setChecked(r0.isChecked());
        onPopulateEventForVirtualView(r4, r52);
        if (r52.getText().isEmpty() == true) goto L5;
    L9:
        r52.setClassName(r0.getClassName());
        AccessibilityRecordCompat.setSource(r52, this.mHost, r4);
        r52.setPackageName(this.mHost.getContext().getPackageName());
        return r52;
    L5:
        if (r52.getContentDescription() != null) goto L9;
        GlShader$$ExternalSyntheticBUOutline1.m("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        return null;
    }

    private AccessibilityEvent createEventForHost(int r1) {
        AccessibilityEvent r12 = AccessibilityEvent.obtain(r1);
        this.mHost.onInitializeAccessibilityEvent(r12);
        return r12;
    }

    private AccessibilityNodeInfoCompat createNodeForChild(int r7) {
        AccessibilityNodeInfoCompat r0 = AccessibilityNodeInfoCompat.obtain();
        r0.setEnabled(true);
        r0.setFocusable(true);
        r0.setClassName(DEFAULT_CLASS_NAME);
        Rect r2 = INVALID_BOUNDS;
        r0.setBoundsInParent(r2);
        r0.setBoundsInScreen(r2);
        r0.setParent(this.mHost);
        onPopulateNodeForVirtualView(r7, r0);
        if (r0.getText() == null) goto L5;
    L9:
        r0.getBoundsInParent(this.mTempParentRect);
        r0.getBoundsInScreen(this.mTempScreenRect);
        if (this.mTempParentRect.equals(r2) == true) goto L12;
    L16:
        int r3 = r0.getActions();
        if ((r3 & 64) == 0) goto L19;
        GlShader$$ExternalSyntheticBUOutline1.m("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        return null;
    L19:
        if ((r3 & 128) != 0) goto L44;
        r0.setPackageName(this.mHost.getContext().getPackageName());
        r0.setSource(this.mHost, r7);
        if (this.mAccessibilityFocusedVirtualViewId != r7) goto L23;
        r0.setAccessibilityFocused(true);
        r0.addAction(128);
    L25:
        if (this.mKeyboardFocusedVirtualViewId != r7) goto L27;
        boolean r72 = true;
    L28:
        if (r72 == false) goto L31;
        r0.addAction(2);
    L33:
        r0.setFocused(r72);
        this.mHost.getLocationOnScreen(this.mTempGlobalRect);
        if (this.mTempScreenRect.equals(r2) == false) goto L37;
        setBoundsInScreenFromBoundsInParent(r0, this.mTempParentRect);
        r0.getBoundsInScreen(this.mTempScreenRect);
    L37:
        if (this.mHost.getLocalVisibleRect(this.mTempVisibleRect) == false) goto L43;
        this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
        if (this.mTempScreenRect.intersect(this.mTempVisibleRect) == false) goto L43;
        r0.setBoundsInScreen(this.mTempScreenRect);
        if (isVisibleToUser(this.mTempScreenRect) == false) goto L43;
        r0.setVisibleToUser(true);
    L43:
        return r0;
    L31:
        if (r0.isFocusable() == false) goto L33;
        r0.addAction(1);
        goto L33
    L27:
        r72 = false;
        goto L28
    L23:
        r0.setAccessibilityFocused(false);
        r0.addAction(64);
        goto L25
    L44:
        GlShader$$ExternalSyntheticBUOutline1.m("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        return null;
    L12:
        if (this.mTempScreenRect.equals(r2) == false) goto L16;
        GlShader$$ExternalSyntheticBUOutline1.m("Callbacks must set parent bounds or screen bounds in populateNodeForVirtualViewId()");
        return null;
    L5:
        if (r0.getContentDescription() != null) goto L9;
        GlShader$$ExternalSyntheticBUOutline1.m("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        return null;
    }

    private AccessibilityNodeInfoCompat createNodeForHost() {
        AccessibilityNodeInfoCompat r0 = AccessibilityNodeInfoCompat.obtain(this.mHost);
        ViewCompat.onInitializeAccessibilityNodeInfo(this.mHost, r0);
        ArrayList r1 = new ArrayList();
        getVisibleVirtualViews(r1);
        if (r0.getChildCount() > 0) goto L5;
    L9:
        int r2 = r1.size();
        int r3 = 0;
    L10:
        if (r3 >= r2) goto L12;
        r0.addChild(this.mHost, ((Integer) r1.get(r3)).intValue());
        r3 = r3 + 1;
        goto L10
    L12:
        return r0;
    L5:
        if (r1.size() <= 0) goto L9;
        GlShader$$ExternalSyntheticBUOutline1.m("Views cannot have both real and virtual children");
        return null;
    }

    private SparseArrayCompat<AccessibilityNodeInfoCompat> getAllNodes() {
        ArrayList r0 = new ArrayList();
        getVisibleVirtualViews(r0);
        SparseArrayCompat<AccessibilityNodeInfoCompat> r1 = new SparseArrayCompat();
        int r2 = 0;
    L4:
        if (r2 >= r0.size()) goto L6;
        AccessibilityNodeInfoCompat r3 = createNodeForChild(r0.get(r2).intValue());
        r1.put(r0.get(r2).intValue(), r3);
        r2 = r2 + 1;
        goto L4
    L6:
        return r1;
    }

    private void getBoundsInScreen(int r1, Rect r2) {
        obtainAccessibilityNodeInfo(r1).getBoundsInScreen(r2);
    }

    private static Rect guessPreviouslyFocusedRect(View r4, int r5, Rect r6) {
        int r0 = r4.getWidth();
        int r42 = r4.getHeight();
        if (r5 != 17) goto L5;
        r6.set(r0, 0, r0, r42);
        return r6;
    L5:
        if (r5 != 33) goto L7;
        r6.set(0, r42, r0, r42);
        return r6;
    L7:
        if (r5 != 66) goto L9;
        r6.set(-1, 0, -1, r42);
        return r6;
    L9:
        if (r5 != 130) goto L12;
        r6.set(0, -1, r0, -1);
        return r6;
    L12:
        f$$ExternalSyntheticBUOutline1.m("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        return null;
    }

    private boolean isVisibleToUser(Rect r3) {
        if (r3 != null) goto L5;
    L23:
        return false;
    L5:
        if (r3.isEmpty() == true) goto L23;
        if (this.mHost.getWindowVisibility() == 0) goto L10;
        return false;
    L10:
        Object r2 = this.mHost.getParent();
    L12:
        if ((r2 instanceof View) == false) goto L20;
        View r22 = (View) r2;
        if (r22.getAlpha() <= 0.0f) goto L19;
        if (r22.getVisibility() != 0) goto L19;
        r2 = r22.getParent();
    L19:
        return false;
    L20:
        if (r2 == null) goto L23;
        return true;
    }

    private static int keyToDirection(int r1) {
        if (r1 != 19) goto L5;
        return 33;
    L5:
        if (r1 != 21) goto L7;
        return 17;
    L7:
        if (r1 == 22) goto L10;
        return 130;
    L10:
        return 66;
    }

    private boolean moveFocus(int r9, Rect r10) {
        SparseArrayCompat<AccessibilityNodeInfoCompat> r0 = getAllNodes();
        int r1 = this.mKeyboardFocusedVirtualViewId;
        int r7 = Integer.MIN_VALUE;
        if (r1 != Integer.MIN_VALUE) goto L6;
        AccessibilityNodeInfoCompat r12 = null;
    L5:
        AccessibilityNodeInfoCompat r3 = r12;
        if (r9 == 1) goto L30;
        if (r9 == 2) goto L30;
        if (r9 != 17) goto L14;
    L22:
        Rect r4 = new Rect();
        int r13 = this.mKeyboardFocusedVirtualViewId;
        if (r13 == Integer.MIN_VALUE) goto L25;
        getBoundsInScreen(r13, r4);
    L28:
        AccessibilityNodeInfoCompat r92 = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInAbsoluteDirection(r0, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, r3, r4, r9);
    L34:
        if (r92 == null) goto L38;
        r7 = r0.keyAt(r0.indexOfValue(r92));
    L38:
        return requestKeyboardFocusForVirtualView(r7);
    L25:
        if (r10 == null) goto L27;
        r4.set(r10);
        goto L28
    L27:
        guessPreviouslyFocusedRect(this.mHost, r9, r4);
        goto L28
    L14:
        if (r9 == 33) goto L22;
        if (r9 == 66) goto L22;
        if (r9 == 130) goto L22;
        f$$ExternalSyntheticBUOutline1.m("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        return false;
    L30:
        if (this.mHost.getLayoutDirection() != 1) goto L32;
        boolean r5 = true;
    L33:
        r92 = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInRelativeDirection(r0, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, r3, r9, r5, false);
        goto L34
    L32:
        r5 = false;
        goto L33
    L6:
        r12 = r0.get(r1);
        goto L5
    }

    private boolean performActionForChild(int r2, int r3, Bundle r4) {
        if (r3 == 1) goto L19;
        if (r3 == 2) goto L17;
        if (r3 == 64) goto L15;
        if (r3 == 128) goto L13;
        return onPerformActionForVirtualView(r2, r3, r4);
    L13:
        return clearAccessibilityFocus(r2);
    L15:
        return requestAccessibilityFocus(r2);
    L17:
        return clearKeyboardFocusForVirtualView(r2);
    L19:
        return requestKeyboardFocusForVirtualView(r2);
    }

    private boolean performActionForHost(int r1, Bundle r2) {
        return this.mHost.performAccessibilityAction(r1, r2);
    }

    private boolean requestAccessibilityFocus(int r3) {
        if (this.mManager.isEnabled() == true) goto L5;
    L14:
        return false;
    L5:
        if (this.mManager.isTouchExplorationEnabled() == false) goto L14;
        int r0 = this.mAccessibilityFocusedVirtualViewId;
        if (r0 == r3) goto L14;
        if (r0 == Integer.MIN_VALUE) goto L12;
        clearAccessibilityFocus(r0);
    L12:
        this.mAccessibilityFocusedVirtualViewId = r3;
        this.mHost.invalidate();
        sendEventForVirtualView(r3, 32768);
        return true;
    }

    private void updateHoveredVirtualView(int r3) {
        int r0 = this.mHoveredVirtualViewId;
        if (r0 != r3) goto L5;
        return;
    L5:
        this.mHoveredVirtualViewId = r3;
        sendEventForVirtualView(r3, 128);
        sendEventForVirtualView(r0, 256);
    }

    public final boolean clearKeyboardFocusForVirtualView(int r3) {
        if (this.mKeyboardFocusedVirtualViewId == r3) goto L5;
        return false;
    L5:
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        onVirtualViewKeyboardFocusChanged(r3, false);
        sendEventForVirtualView(r3, 8);
        return true;
    }

    public final boolean dispatchHoverEvent(MotionEvent r6) {
        if (this.mManager.isEnabled() == true) goto L5;
    L22:
        return false;
    L5:
        if (this.mManager.isTouchExplorationEnabled() == false) goto L22;
        int r0 = r6.getAction();
        if (r0 != 7) goto L10;
    L19:
        int r62 = getVirtualViewAt(r6.getX(), r6.getY());
        updateHoveredVirtualView(r62);
        if (r62 == Integer.MIN_VALUE) goto L22;
        return true;
    L10:
        if (r0 == 9) goto L19;
        if (r0 == 10) goto L15;
        return false;
    L15:
        if (this.mHoveredVirtualViewId == Integer.MIN_VALUE) goto L18;
        updateHoveredVirtualView(Integer.MIN_VALUE);
        return true;
    L18:
        return false;
    }

    public final boolean dispatchKeyEvent(KeyEvent r7) {
        int r1 = 0;
        if (r7.getAction() == 1) goto L32;
        int r0 = r7.getKeyCode();
        if (r0 == 61) goto L25;
        if (r0 == 66) goto L19;
        switch(r0) {
            case 19: goto L11;
            case 20: goto L11;
            case 21: goto L11;
            case 22: goto L11;
            case 23: goto L19;
            default: goto L32;
        };
    L11:
        if (r7.hasNoModifiers() == false) goto L32;
        int r02 = keyToDirection(r0);
        int r72 = r7.getRepeatCount() + 1;
        boolean r3 = false;
    L13:
        if (r1 >= r72) goto L17;
        if (moveFocus(r02, null) == false) goto L17;
        r1 = r1 + 1;
        r3 = true;
    L17:
        return r3;
    L19:
        if (r7.hasNoModifiers() == false) goto L32;
        if (r7.getRepeatCount() != 0) goto L32;
        clickKeyboardFocusedVirtualView();
        return true;
    L25:
        if (r7.hasNoModifiers() == false) goto L29;
        return moveFocus(2, null);
    L29:
        if (r7.hasModifiers(1) == false) goto L32;
        return moveFocus(1, null);
    L32:
        return false;
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.mAccessibilityFocusedVirtualViewId;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View r1) {
        if (this.mNodeProvider != null) goto L6;
        this.mNodeProvider = new MyNodeProvider(this);
    L6:
        return this.mNodeProvider;
    }

    @Deprecated
    public int getFocusedVirtualView() {
        return getAccessibilityFocusedVirtualViewId();
    }

    public final int getKeyboardFocusedVirtualViewId() {
        return this.mKeyboardFocusedVirtualViewId;
    }

    public abstract int getVirtualViewAt(float r1, float r2);

    public abstract void getVisibleVirtualViews(List<Integer> r1);

    public final void invalidateRoot() {
        invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int r2) {
        invalidateVirtualView(r2, 0);
    }

    public final void invalidateVirtualView(int r3, int r4) {
        if (r3 != Integer.MIN_VALUE) goto L5;
        return;
    L5:
        if (this.mManager.isEnabled() == false) goto L11;
        ViewParent r0 = this.mHost.getParent();
        if (r0 == null) goto L12;
        AccessibilityEvent r32 = createEvent(r3, 2048);
        AccessibilityEventCompat.setContentChangeTypes(r32, r4);
        r0.requestSendAccessibilityEvent(this.mHost, r32);
        return;
    L12:
        return;
    }

    public AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int r2) {
        if (r2 != (-1)) goto L7;
        return createNodeForHost();
    L7:
        return createNodeForChild(r2);
    }

    public final void onFocusChanged(boolean r3, int r4, Rect r5) {
        int r0 = this.mKeyboardFocusedVirtualViewId;
        if (r0 == Integer.MIN_VALUE) goto L5;
        clearKeyboardFocusForVirtualView(r0);
    L5:
        if (r3 == false) goto L8;
        moveFocus(r4, r5);
        return;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View r1, AccessibilityEvent r2) {
        super.onInitializeAccessibilityEvent(r1, r2);
        onPopulateEventForHost(r2);
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
        super.onInitializeAccessibilityNodeInfo(r1, r2);
        onPopulateNodeForHost(r2);
    }

    public abstract boolean onPerformActionForVirtualView(int r1, int r2, Bundle r3);

    public void onPopulateEventForHost(AccessibilityEvent r1) {
    }

    public void onPopulateEventForVirtualView(int r1, AccessibilityEvent r2) {
    }

    public void onPopulateNodeForHost(AccessibilityNodeInfoCompat r1) {
    }

    public abstract void onPopulateNodeForVirtualView(int r1, AccessibilityNodeInfoCompat r2);

    public void onVirtualViewKeyboardFocusChanged(int r1, boolean r2) {
    }

    public boolean performAction(int r2, int r3, Bundle r4) {
        if (r2 == (-1)) goto L7;
        return performActionForChild(r2, r3, r4);
    L7:
        return performActionForHost(r3, r4);
    }

    public final boolean requestKeyboardFocusForVirtualView(int r4) {
        if (this.mHost.isFocused() == false) goto L5;
    L7:
        int r0 = this.mKeyboardFocusedVirtualViewId;
        if (r0 != r4) goto L11;
        return false;
    L11:
        if (r0 == Integer.MIN_VALUE) goto L13;
        clearKeyboardFocusForVirtualView(r0);
    L13:
        if (r4 != Integer.MIN_VALUE) goto L15;
        return false;
    L15:
        this.mKeyboardFocusedVirtualViewId = r4;
        onVirtualViewKeyboardFocusChanged(r4, true);
        sendEventForVirtualView(r4, 8);
        return true;
    L5:
        if (this.mHost.requestFocus() == true) goto L7;
        return false;
    }

    public final boolean sendEventForVirtualView(int r3, int r4) {
        if (r3 != Integer.MIN_VALUE) goto L5;
    L12:
        return false;
    L5:
        if (this.mManager.isEnabled() == false) goto L12;
        ViewParent r0 = this.mHost.getParent();
        if (r0 != null) goto L10;
        return false;
    L10:
        AccessibilityEvent r32 = createEvent(r3, r4);
        return r0.requestSendAccessibilityEvent(this.mHost, r32);
    }

    public final void setBoundsInScreenFromBoundsInParent(AccessibilityNodeInfoCompat r6, Rect r7) {
        r6.setBoundsInParent(r7);
        Rect r0 = new Rect();
        r0.set(r7);
        if (r6.mParentVirtualDescendantId == (-1)) goto L8;
        AccessibilityNodeInfoCompat r72 = AccessibilityNodeInfoCompat.obtain();
        Rect r2 = new Rect();
        int r3 = r6.mParentVirtualDescendantId;
    L5:
        if (r3 == (-1)) goto L7;
        r72.setParent(this.mHost, -1);
        r72.setBoundsInParent(INVALID_BOUNDS);
        onPopulateNodeForVirtualView(r3, r72);
        r72.getBoundsInParent(r2);
        r0.offset(r2.left, r2.top);
        r3 = r72.mParentVirtualDescendantId;
        goto L5
    L7:
        r72.recycle();
    L8:
        this.mHost.getLocationOnScreen(this.mTempGlobalRect);
        r0.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
        r6.setBoundsInScreen(r0);
    }
}
