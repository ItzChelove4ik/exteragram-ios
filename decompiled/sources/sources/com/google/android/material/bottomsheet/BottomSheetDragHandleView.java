package com.google.android.material.bottomsheet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import com.google.android.material.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: classes5.dex */
public class BottomSheetDragHandleView extends AppCompatImageView implements AccessibilityManager.AccessibilityStateChangeListener {
    private static final int DEF_STYLE_RES = 0;
    private final AccessibilityManager accessibilityManager;
    private BottomSheetBehavior<?> bottomSheetBehavior;
    private final BottomSheetBehavior.BottomSheetCallback bottomSheetCallback;
    private final String clickToCollapseActionLabel;
    private boolean clickToExpand;
    private final String clickToExpandActionLabel;
    private final String clickToHalfExpandActionLabel;
    private final GestureDetector gestureDetector;
    private final GestureDetector.OnGestureListener gestureListener;
    private boolean hasClickListener;
    private boolean hasTouchListener;

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetDragHandleView$1, reason: invalid class name */
    public class AnonymousClass1 extends BottomSheetBehavior.BottomSheetCallback {
        final /* synthetic */ BottomSheetDragHandleView this$0;

        public AnonymousClass1(BottomSheetDragHandleView r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onSlide(View r1, float r2) {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onStateChanged(View r1, int r2) {
            BottomSheetDragHandleView.access$000(this.this$0, r2);
        }
    }

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetDragHandleView$2, reason: invalid class name */
    public class AnonymousClass2 extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ BottomSheetDragHandleView this$0;

        public AnonymousClass2(BottomSheetDragHandleView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent r2) {
            if (BottomSheetDragHandleView.access$200(this.this$0) == null) goto L9;
            if (BottomSheetDragHandleView.access$200(this.this$0).isHideable() == false) goto L9;
            BottomSheetDragHandleView.access$200(this.this$0).setState(5);
            return true;
        L9:
            return super.onDoubleTap(r2);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r1) {
            return this.this$0.isClickable();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent r2) {
            this.this$0.performLongClick(r2.getX(), r2.getY());
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent r1) {
            return BottomSheetDragHandleView.access$100(this.this$0);
        }
    }

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetDragHandleView$3, reason: invalid class name */
    public class AnonymousClass3 extends AccessibilityDelegateCompat {
        final /* synthetic */ BottomSheetDragHandleView this$0;

        public AnonymousClass3(BottomSheetDragHandleView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r3, AccessibilityNodeInfoCompat r4) {
            super.onInitializeAccessibilityNodeInfo(r3, r4);
            if (BottomSheetDragHandleView.access$300(this.this$0) == false) goto L24;
            CharSequence r32 = this.this$0.getContentDescription();
            int r0 = BottomSheetDragHandleView.access$200(this.this$0).getState();
            if (r0 != 3) goto L8;
            String r2 = this.this$0.getResources().getString(R.string.bottomsheet_state_expanded);
        L16:
            if (TextUtils.isEmpty(r2) == false) goto L18;
            return;
        L18:
            if (TextUtils.isEmpty(r32) == true) goto L21;
            r2 = r2 + ". " + r32;
        L21:
            r4.setContentDescription(r2);
            return;
        L8:
            if (r0 != 4) goto L10;
            r2 = this.this$0.getResources().getString(R.string.bottomsheet_state_collapsed);
            goto L16
        L10:
            if (r0 == 6) goto L12;
            r2 = null;
            goto L16
        L12:
            r2 = this.this$0.getResources().getString(R.string.bottomsheet_state_half_expanded);
            goto L16
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onPopulateAccessibilityEvent(View r1, AccessibilityEvent r2) {
            super.onPopulateAccessibilityEvent(r1, r2);
            if (r2.getEventType() != 1) goto L6;
            BottomSheetDragHandleView.access$100(this.this$0);
            return;
        }
    }

    /* renamed from: $r8$lambda$kcYefU7mDsxaDDUrYFwGm-Fzkow, reason: not valid java name */
    public static /* synthetic */ boolean m1821$r8$lambda$kcYefU7mDsxaDDUrYFwGmFzkow(BottomSheetDragHandleView r0, View r1, AccessibilityViewCommand.CommandArguments r2) {
        return r0.expandOrCollapseBottomSheetIfPossible();
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Material3_BottomSheet_DragHandle;
    }

    public BottomSheetDragHandleView(Context r2) {
        this(r2, null);
    }

    public BottomSheetDragHandleView(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.bottomSheetDragHandleStyle);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public BottomSheetDragHandleView(Context r3, AttributeSet r4, int r5) {
        super(MaterialThemeOverlay.wrap(r3, r4, r5, DEF_STYLE_RES), r4, r5);
        this.hasTouchListener = false;
        this.hasClickListener = false;
        this.clickToExpandActionLabel = getResources().getString(R.string.bottomsheet_action_expand_description);
        this.clickToHalfExpandActionLabel = getResources().getString(R.string.bottomsheet_action_half_expand_description);
        this.clickToCollapseActionLabel = getResources().getString(R.string.bottomsheet_action_collapse_description);
        this.bottomSheetCallback = new AnonymousClass1(this);
        AnonymousClass2 r32 = new AnonymousClass2(this);
        this.gestureListener = r32;
        Context r42 = getContext();
        if (Build.VERSION.SDK_INT < 26) goto L5;
        setTooltipText(getResources().getString(R.string.bottomsheet_drag_handle_content_description));
    L5:
        this.gestureDetector = new GestureDetector(r42, r32, new Handler(Looper.getMainLooper()));
        this.accessibilityManager = (AccessibilityManager) r42.getSystemService("accessibility");
        ViewCompat.setAccessibilityDelegate(this, new AnonymousClass3(this));
    }

    public static /* synthetic */ void access$000(BottomSheetDragHandleView r0, int r1) {
        r0.onBottomSheetStateChanged(r1);
    }

    public static /* synthetic */ boolean access$100(BottomSheetDragHandleView r0) {
        return r0.expandOrCollapseBottomSheetIfPossible();
    }

    public static /* synthetic */ BottomSheetBehavior access$200(BottomSheetDragHandleView r0) {
        return r0.bottomSheetBehavior;
    }

    public static /* synthetic */ boolean access$300(BottomSheetDragHandleView r0) {
        return r0.hasAttachedBehavior();
    }

    private boolean expandOrCollapseBottomSheetIfPossible() {
        if (hasAttachedBehavior() == true) goto L6;
        return false;
    L6:
        int r0 = getNextState();
        if (r0 == (-1)) goto L11;
        this.bottomSheetBehavior.setState(r0);
        return true;
    L11:
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.google.android.material.bottomsheet.BottomSheetDragHandleView] */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r2v2, types: [android.view.View] */
    private BottomSheetBehavior<?> findParentBottomSheetBehavior() {
    L2:
        BottomSheetDragHandleView this = getParentView(this);
        if (this == 0) goto L10;
        ViewGroup.LayoutParams r0 = this.getLayoutParams();
        if ((r0 instanceof CoordinatorLayout.LayoutParams) == false) goto L2;
        CoordinatorLayout.Behavior r02 = ((CoordinatorLayout.LayoutParams) r0).getBehavior();
        if ((r02 instanceof BottomSheetBehavior) == false) goto L2;
        return (BottomSheetBehavior) r02;
    L10:
        return null;
    }

    private int getNextState() {
        if (hasAttachedBehavior() == true) goto L6;
        return -1;
    L6:
        if (this.bottomSheetBehavior.isFitToContents() == false) goto L8;
    L10:
        boolean r0 = false;
    L11:
        int r1 = this.bottomSheetBehavior.getState();
        if (r1 == 3) goto L25;
        if (r1 == 4) goto L22;
        if (r1 == 6) goto L17;
        return -1;
    L17:
        if (this.clickToExpand == false) goto L20;
        return 3;
    L20:
        if (this.bottomSheetBehavior.canCollapse() == false) goto L30;
        return 4;
    L30:
        return -1;
    L22:
        if (r0 == false) goto L24;
        return 6;
    L24:
        return 3;
    L25:
        if (r0 == false) goto L28;
        return 6;
    L28:
        if (this.bottomSheetBehavior.canCollapse() == false) goto L32;
        return 4;
    L32:
        return -1;
    L8:
        if (this.bottomSheetBehavior.shouldSkipHalfExpandedStateWhenDragging() == true) goto L10;
        r0 = true;
        goto L11
    }

    private static View getParentView(View r1) {
        Object r12 = r1.getParent();
        if ((r12 instanceof View) == true) goto L5;
        return null;
    L5:
        return (View) r12;
    }

    private boolean hasAttachedBehavior() {
        if (this.bottomSheetBehavior == null) goto L6;
        return true;
    L6:
        return false;
    }

    private void onBottomSheetStateChanged(int r3) {
        if (r3 != 4) goto L5;
        this.clickToExpand = true;
    L7:
        int r32 = getNextState();
        if (r32 == 3) goto L15;
        if (r32 != 4) goto L11;
        String r33 = this.clickToCollapseActionLabel;
    L16:
        ViewCompat.replaceAccessibilityAction(this, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK, r33, new BottomSheetDragHandleView$$ExternalSyntheticLambda0(this));
        return;
    L11:
        if (r32 == 6) goto L13;
        r33 = null;
        goto L16
    L13:
        r33 = this.clickToHalfExpandActionLabel;
        goto L16
    L15:
        r33 = this.clickToExpandActionLabel;
        goto L16
    L5:
        if (r3 != 3) goto L7;
        this.clickToExpand = false;
        goto L7
    }

    private void setBottomSheetBehavior(BottomSheetBehavior<?> r3) {
        BottomSheetBehavior<?> r0 = this.bottomSheetBehavior;
        if (r0 == null) goto L5;
        r0.removeBottomSheetCallback(this.bottomSheetCallback);
        this.bottomSheetBehavior.setAccessibilityDelegateView(null);
        this.bottomSheetBehavior.setDragHandleView(null);
    L5:
        this.bottomSheetBehavior = r3;
        if (r3 == null) goto L8;
        r3.setAccessibilityDelegateView(this);
        this.bottomSheetBehavior.setDragHandleView(this);
        onBottomSheetStateChanged(this.bottomSheetBehavior.getState());
        this.bottomSheetBehavior.addBottomSheetCallback(this.bottomSheetCallback);
    L8:
        setClickable(hasAttachedBehavior());
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public void onAccessibilityStateChanged(boolean r1) {
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setBottomSheetBehavior(findParentBottomSheetBehavior());
        AccessibilityManager r0 = this.accessibilityManager;
        if (r0 == null) goto L6;
        r0.addAccessibilityStateChangeListener(this);
        onAccessibilityStateChanged(this.accessibilityManager.isEnabled());
        return;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        AccessibilityManager r0 = this.accessibilityManager;
        if (r0 == null) goto L5;
        r0.removeAccessibilityStateChangeListener(this);
    L5:
        setBottomSheetBehavior(null);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r2, KeyEvent r3) {
        if (isEnabled() == true) goto L7;
        return super.onKeyDown(r2, r3);
    L7:
        if (r2 == 23) goto L13;
        if (r2 == 66) goto L13;
        return super.onKeyDown(r2, r3);
    L13:
        if (this.hasClickListener == false) goto L17;
        return performClick();
    L17:
        return expandOrCollapseBottomSheetIfPossible();
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r2) {
        if (this.hasClickListener == true) goto L10;
        if (this.hasTouchListener == true) goto L10;
        return this.gestureDetector.onTouchEvent(r2);
    L10:
        return super.onTouchEvent(r2);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener r2) {
        if (r2 == null) goto L4;
        boolean r0 = true;
    L5:
        this.hasClickListener = r0;
        super.setOnClickListener(r2);
        return;
    L4:
        r0 = false;
        goto L5
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public void setOnTouchListener(View.OnTouchListener r2) {
        if (r2 == null) goto L4;
        boolean r0 = true;
    L5:
        this.hasTouchListener = r0;
        super.setOnTouchListener(r2);
        return;
    L4:
        r0 = false;
        goto L5
    }
}
