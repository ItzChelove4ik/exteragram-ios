package com.google.android.material.bottomsheet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.insets.Protection;
import androidx.core.view.insets.ProtectionLayout;
import com.google.android.material.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.util.List;

/* loaded from: classes5.dex */
public class BottomSheetDialog extends AppCompatDialog {
    private MaterialBackOrchestrator backOrchestrator;
    private BottomSheetBehavior<FrameLayout> behavior;
    private FrameLayout bottomSheet;
    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback;
    boolean cancelable;
    private boolean canceledOnTouchOutside;
    private boolean canceledOnTouchOutsideSet;
    private FrameLayout container;
    private CoordinatorLayout coordinator;
    boolean dismissWithAnimation;
    private EdgeToEdgeCallback edgeToEdgeCallback;
    private boolean edgeToEdgeEnabled;
    private ProtectionLayout protectionLayout;
    private List<Protection> protectionsList;

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetDialog$1, reason: invalid class name */
    public class AnonymousClass1 implements OnApplyWindowInsetsListener {
        final /* synthetic */ BottomSheetDialog this$0;

        public AnonymousClass1(BottomSheetDialog r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r4, WindowInsetsCompat r5) {
            if (BottomSheetDialog.access$000(this.this$0) == null) goto L5;
            BottomSheetDialog.access$100(this.this$0).removeBottomSheetCallback(BottomSheetDialog.access$000(this.this$0));
        L5:
            if (r5 == null) goto L7;
            BottomSheetDialog r42 = this.this$0;
            BottomSheetDialog.access$002(r42, new EdgeToEdgeCallback(BottomSheetDialog.access$200(r42), r5, null));
            BottomSheetDialog.access$000(this.this$0).setWindow(this.this$0.getWindow());
            BottomSheetDialog.access$100(this.this$0).addBottomSheetCallback(BottomSheetDialog.access$000(this.this$0));
        L7:
            return r5;
        }
    }

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetDialog$2, reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ BottomSheetDialog this$0;

        public AnonymousClass2(BottomSheetDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r2) {
            BottomSheetDialog r22 = this.this$0;
            if (r22.cancelable == true) goto L5;
            return;
        L5:
            if (r22.isShowing() == true) goto L7;
            return;
        L7:
            if (this.this$0.shouldWindowCloseOnTouchOutside() == false) goto L12;
            this.this$0.cancel();
            return;
        }
    }

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetDialog$3, reason: invalid class name */
    public class AnonymousClass3 extends AccessibilityDelegateCompat {
        final /* synthetic */ BottomSheetDialog this$0;

        public AnonymousClass3(BottomSheetDialog r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            if (this.this$0.cancelable == false) goto L6;
            r2.addAction(1048576);
            r2.setDismissable(true);
            return;
        L6:
            r2.setDismissable(false);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View r3, int r4, Bundle r5) {
            if (r4 != 1048576) goto L9;
            BottomSheetDialog r0 = this.this$0;
            if (r0.cancelable == false) goto L9;
            r0.cancel();
            return true;
        L9:
            return super.performAccessibilityAction(r3, r4, r5);
        }
    }

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetDialog$4, reason: invalid class name */
    public class AnonymousClass4 implements View.OnTouchListener {
        final /* synthetic */ BottomSheetDialog this$0;

        public AnonymousClass4(BottomSheetDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View r1, MotionEvent r2) {
            return true;
        }
    }

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetDialog$5, reason: invalid class name */
    public class AnonymousClass5 extends BottomSheetBehavior.BottomSheetCallback {
        final /* synthetic */ BottomSheetDialog this$0;

        public AnonymousClass5(BottomSheetDialog r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onSlide(View r1, float r2) {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onStateChanged(View r1, int r2) {
            if (r2 != 5) goto L6;
            this.this$0.cancel();
            return;
        }
    }

    public static class EdgeToEdgeCallback extends BottomSheetBehavior.BottomSheetCallback {
        private final WindowInsetsCompat insetsCompat;
        private final Boolean lightBottomSheet;
        private boolean lightStatusBar;
        private Window window;

        private EdgeToEdgeCallback(View r1, WindowInsetsCompat r2) {
            this.insetsCompat = r2;
            MaterialShapeDrawable r22 = BottomSheetBehavior.from(r1).getMaterialShapeDrawable();
            if (r22 == null) goto L5;
            ColorStateList r23 = r22.getFillColor();
        L6:
            if (r23 == null) goto L9;
            this.lightBottomSheet = Boolean.valueOf(MaterialColors.isColorLight(r23.getDefaultColor()));
            return;
        L9:
            Integer r12 = ViewUtils.getBackgroundColor(r1);
            if (r12 == null) goto L13;
            this.lightBottomSheet = Boolean.valueOf(MaterialColors.isColorLight(r12.intValue()));
            return;
        L13:
            this.lightBottomSheet = null;
            return;
        L5:
            r23 = r1.getBackgroundTintList();
            goto L6
        }

        public /* synthetic */ EdgeToEdgeCallback(View r1, WindowInsetsCompat r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        private void setPaddingForPosition(View r4) {
            if (r4.getTop() >= this.insetsCompat.getSystemWindowInsetTop()) goto L14;
            Window r0 = this.window;
            if (r0 == null) goto L11;
            Boolean r1 = this.lightBottomSheet;
            if (r1 != null) goto L9;
            boolean r12 = this.lightStatusBar;
        L10:
            EdgeToEdgeUtils.setLightStatusBar(r0, r12);
            goto L11
        L9:
            r12 = r1.booleanValue();
        L11:
            r4.setPadding(r4.getPaddingLeft(), this.insetsCompat.getSystemWindowInsetTop() - r4.getTop(), r4.getPaddingRight(), r4.getPaddingBottom());
            return;
        L14:
            if (r4.getTop() == 0) goto L20;
            Window r02 = this.window;
            if (r02 == null) goto L18;
            EdgeToEdgeUtils.setLightStatusBar(r02, this.lightStatusBar);
        L18:
            r4.setPadding(r4.getPaddingLeft(), 0, r4.getPaddingRight(), r4.getPaddingBottom());
            return;
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onLayout(View r1) {
            setPaddingForPosition(r1);
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onSlide(View r1, float r2) {
            setPaddingForPosition(r1);
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onStateChanged(View r1, int r2) {
            setPaddingForPosition(r1);
        }

        public void setWindow(Window r2) {
            if (this.window == r2) goto L10;
            this.window = r2;
            if (r2 == null) goto L9;
            this.lightStatusBar = WindowCompat.getInsetsController(r2, r2.getDecorView()).isAppearanceLightStatusBars();
            return;
        L9:
            return;
        }
    }

    public BottomSheetDialog(Context r2) {
        this(r2, 0);
        initialize();
    }

    public BottomSheetDialog(Context r1, int r2) {
        super(r1, getThemeResId(r1, r2));
        this.cancelable = true;
        this.canceledOnTouchOutside = true;
        this.bottomSheetCallback = new AnonymousClass5(this);
        supportRequestWindowFeature(1);
        initialize();
    }

    public BottomSheetDialog(Context r1, boolean r2, DialogInterface.OnCancelListener r3) {
        super(r1, r2, r3);
        this.cancelable = true;
        this.canceledOnTouchOutside = true;
        this.bottomSheetCallback = new AnonymousClass5(this);
        supportRequestWindowFeature(1);
        this.cancelable = r2;
        initialize();
    }

    public static /* synthetic */ EdgeToEdgeCallback access$000(BottomSheetDialog r0) {
        return r0.edgeToEdgeCallback;
    }

    public static /* synthetic */ EdgeToEdgeCallback access$002(BottomSheetDialog r0, EdgeToEdgeCallback r1) {
        r0.edgeToEdgeCallback = r1;
        return r1;
    }

    public static /* synthetic */ BottomSheetBehavior access$100(BottomSheetDialog r0) {
        return r0.behavior;
    }

    public static /* synthetic */ FrameLayout access$200(BottomSheetDialog r0) {
        return r0.bottomSheet;
    }

    private FrameLayout ensureContainerAndBehavior() {
        if (this.container != null) goto L9;
        FrameLayout r0 = (FrameLayout) View.inflate(getContext(), R.layout.design_bottom_sheet_dialog, null);
        this.container = r0;
        this.protectionLayout = (ProtectionLayout) r0.findViewById(R.id.protection_layout);
        List<Protection> r02 = this.protectionsList;
        if (r02 == null) goto L7;
        setProtections(r02);
    L7:
        this.coordinator = (CoordinatorLayout) this.container.findViewById(R.id.coordinator);
        FrameLayout r03 = (FrameLayout) this.container.findViewById(R.id.design_bottom_sheet);
        this.bottomSheet = r03;
        BottomSheetBehavior<FrameLayout> r04 = BottomSheetBehavior.from(r03);
        this.behavior = r04;
        r04.addBottomSheetCallback(this.bottomSheetCallback);
        this.behavior.setHideable(this.cancelable);
        this.backOrchestrator = new MaterialBackOrchestrator(this.behavior, this.bottomSheet);
    L9:
        return this.container;
    }

    private static int getThemeResId(Context r2, int r3) {
        if (r3 != 0) goto L9;
        TypedValue r32 = new TypedValue();
        if (r2.getTheme().resolveAttribute(R.attr.bottomSheetDialogTheme, r32, true) == false) goto L8;
        return r32.resourceId;
    L8:
        return R.style.Theme_Design_Light_BottomSheetDialog;
    L9:
        return r3;
    }

    private void initialize() {
        TypedArray r0 = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.enableEdgeToEdge});
        this.edgeToEdgeEnabled = r0.getBoolean(0, false);
        r0.recycle();
    }

    @Deprecated
    public static void setLightStatusBar(View r1, boolean r2) {
        int r0 = r1.getSystemUiVisibility();
        if (r2 == false) goto L5;
        int r22 = r0 | 8192;
    L6:
        r1.setSystemUiVisibility(r22);
        return;
    L5:
        r22 = r0 & (-8193);
        goto L6
    }

    private void updateListeningForBackCallbacks() {
        MaterialBackOrchestrator r0 = this.backOrchestrator;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.cancelable == false) goto L9;
        r0.startListeningForBackCallbacks();
        return;
    L9:
        r0.stopListeningForBackCallbacks();
    }

    private View wrapInBottomSheet(int r3, View r4, ViewGroup.LayoutParams r5) {
        ensureContainerAndBehavior();
        CoordinatorLayout r0 = (CoordinatorLayout) this.container.findViewById(R.id.coordinator);
        if (r3 == 0) goto L7;
        if (r4 != null) goto L7;
        r4 = getLayoutInflater().inflate(r3, r0, false);
    L7:
        if (this.edgeToEdgeEnabled == false) goto L9;
        ViewCompat.setOnApplyWindowInsetsListener(this.container, new AnonymousClass1(this));
    L9:
        this.bottomSheet.removeAllViews();
        FrameLayout r32 = this.bottomSheet;
        if (r5 != null) goto L12;
        r32.addView(r4);
    L13:
        r0.findViewById(R.id.touch_outside).setOnClickListener(new AnonymousClass2(this));
        ViewCompat.setAccessibilityDelegate(this.bottomSheet, new AnonymousClass3(this));
        this.bottomSheet.setOnTouchListener(new AnonymousClass4(this));
        return this.container;
    L12:
        r32.addView(r4, r5);
        goto L13
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        BottomSheetBehavior<FrameLayout> r0 = getBehavior();
        if (this.dismissWithAnimation == true) goto L5;
    L9:
        super.cancel();
        return;
    L5:
        if (r0.getState() == 5) goto L9;
        r0.setState(5);
    }

    public BottomSheetBehavior<FrameLayout> getBehavior() {
        if (this.behavior != null) goto L6;
        ensureContainerAndBehavior();
    L6:
        return this.behavior;
    }

    public boolean getDismissWithAnimation() {
        return this.dismissWithAnimation;
    }

    public boolean getEdgeToEdgeEnabled() {
        return this.edgeToEdgeEnabled;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window r0 = getWindow();
        if (r0 != null) goto L5;
    L19:
        updateListeningForBackCallbacks();
        return;
    L5:
        if (this.edgeToEdgeEnabled == true) goto L7;
    L9:
        boolean r1 = false;
    L10:
        FrameLayout r3 = this.container;
        if (r3 == null) goto L13;
        r3.setFitsSystemWindows(!r1);
    L13:
        CoordinatorLayout r32 = this.coordinator;
        if (r32 == null) goto L16;
        r32.setFitsSystemWindows(!r1);
    L16:
        WindowCompat.setDecorFitsSystemWindows(r0, !r1);
        EdgeToEdgeCallback r12 = this.edgeToEdgeCallback;
        if (r12 == null) goto L19;
        r12.setWindow(r0);
        goto L19
    L7:
        if (Color.alpha(EdgeToEdgeUtils.getNavigationBarColor(r0)) >= 255) goto L9;
        r1 = true;
        goto L10
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle r1) {
        super.onCreate(r1);
        Window r0 = getWindow();
        if (r0 == null) goto L6;
        EdgeToEdgeUtils.setStatusBarColor(r0, 0);
        r0.addFlags(Integer.MIN_VALUE);
        r0.setLayout(-1, -1);
        return;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        EdgeToEdgeCallback r0 = this.edgeToEdgeCallback;
        if (r0 == null) goto L5;
        r0.setWindow(null);
    L5:
        MaterialBackOrchestrator r2 = this.backOrchestrator;
        if (r2 == null) goto L9;
        r2.stopListeningForBackCallbacks();
        return;
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public void onStart() {
        super.onStart();
        BottomSheetBehavior<FrameLayout> r0 = this.behavior;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getState() != 5) goto L9;
        this.behavior.setState(4);
        return;
    }

    public void removeDefaultCallback() {
        this.behavior.removeBottomSheetCallback(this.bottomSheetCallback);
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean r2) {
        super.setCancelable(r2);
        if (this.cancelable == r2) goto L11;
        this.cancelable = r2;
        BottomSheetBehavior<FrameLayout> r0 = this.behavior;
        if (r0 == null) goto L8;
        r0.setHideable(r2);
    L8:
        if (getWindow() == null) goto L12;
        updateListeningForBackCallbacks();
        return;
    L12:
        return;
    }

    @Override // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean r3) {
        super.setCanceledOnTouchOutside(r3);
        if (r3 == true) goto L5;
    L7:
        this.canceledOnTouchOutside = r3;
        this.canceledOnTouchOutsideSet = true;
        return;
    L5:
        if (this.cancelable == true) goto L7;
        this.cancelable = true;
        goto L7
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(int r2) {
        super.setContentView(wrapInBottomSheet(r2, null, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(View r3) {
        super.setContentView(wrapInBottomSheet(0, r3, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(View r2, ViewGroup.LayoutParams r3) {
        super.setContentView(wrapInBottomSheet(0, r2, r3));
    }

    public void setDismissWithAnimation(boolean r1) {
        this.dismissWithAnimation = r1;
    }

    public void setProtections(List<Protection> r2) {
        this.protectionsList = r2;
        ProtectionLayout r0 = this.protectionLayout;
        if (r0 == null) goto L10;
        r0.setProtections(r2);
        ProtectionLayout r1 = this.protectionLayout;
        if (r2.isEmpty() == false) goto L7;
        int r22 = 8;
    L8:
        r1.setVisibility(r22);
        return;
    L7:
        r22 = 0;
        goto L8
    }

    public boolean shouldWindowCloseOnTouchOutside() {
        if (this.canceledOnTouchOutsideSet == true) goto L6;
        TypedArray r0 = getContext().obtainStyledAttributes(new int[]{android.R.attr.windowCloseOnTouchOutside});
        this.canceledOnTouchOutside = r0.getBoolean(0, true);
        r0.recycle();
        this.canceledOnTouchOutsideSet = true;
    L6:
        return this.canceledOnTouchOutside;
    }
}
