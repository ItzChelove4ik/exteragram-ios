package org.telegram.ui.Stories.recorder;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;

@Deprecated(since = "use insets listener !!!")
/* loaded from: classes7.dex */
public class KeyboardNotifier {
    private boolean awaitingKeyboard;
    public boolean ignoring;
    private int keyboardHeight;
    private int lastKeyboardHeight;
    private final Utilities.Callback<Integer> listener;
    private boolean mMinusNavBar;
    private boolean mUseInsets;
    private final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private final View.OnLayoutChangeListener onLayoutChangeListener;
    private View realRootView;
    private final Rect rect;
    private final View rootView;

    /* renamed from: org.telegram.ui.Stories.recorder.KeyboardNotifier$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ KeyboardNotifier this$0;
        final /* synthetic */ boolean val$getRootView;
        final /* synthetic */ View val$rootView;

        public AnonymousClass1(KeyboardNotifier r1, boolean r2, View r3) {
            this.this$0 = r1;
            this.val$getRootView = r2;
            this.val$rootView = r3;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r2) {
            if (this.val$getRootView == false) goto L5;
            KeyboardNotifier.m20048$$Nest$fputrealRootView(this.this$0, r2.getRootView());
        L5:
            this.val$rootView.getViewTreeObserver().addOnGlobalLayoutListener(KeyboardNotifier.m20046$$Nest$fgetonGlobalLayoutListener(this.this$0));
            this.val$rootView.addOnLayoutChangeListener(KeyboardNotifier.m20047$$Nest$fgetonLayoutChangeListener(this.this$0));
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r2) {
            this.val$rootView.getViewTreeObserver().removeOnGlobalLayoutListener(KeyboardNotifier.m20046$$Nest$fgetonGlobalLayoutListener(this.this$0));
            this.val$rootView.removeOnLayoutChangeListener(KeyboardNotifier.m20047$$Nest$fgetonLayoutChangeListener(this.this$0));
        }
    }

    public static /* synthetic */ void $r8$lambda$1QiSqDzDhauRGkPBsxlv73IRtpQ(KeyboardNotifier r0) {
        r0.update();
    }

    public static /* synthetic */ void $r8$lambda$a7DatkGrEBSvb14EsQcu4NtBNgc(KeyboardNotifier r0, View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        r0.lambda$new$0(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    /* renamed from: -$$Nest$fgetonGlobalLayoutListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener m20046$$Nest$fgetonGlobalLayoutListener(KeyboardNotifier r0) {
        return r0.onGlobalLayoutListener;
    }

    /* renamed from: -$$Nest$fgetonLayoutChangeListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View.OnLayoutChangeListener m20047$$Nest$fgetonLayoutChangeListener(KeyboardNotifier r0) {
        return r0.onLayoutChangeListener;
    }

    /* renamed from: -$$Nest$fputrealRootView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20048$$Nest$fputrealRootView(KeyboardNotifier r0, View r1) {
        r0.realRootView = r1;
    }

    public KeyboardNotifier(View r2, Utilities.Callback<Integer> r3) {
        this(r2, false, r3);
    }

    public KeyboardNotifier(View r3, boolean r4, Utilities.Callback<Integer> r5) {
        this.rect = new Rect();
        KeyboardNotifier$$ExternalSyntheticLambda0 r0 = new KeyboardNotifier$$ExternalSyntheticLambda0(this);
        this.onLayoutChangeListener = r0;
        KeyboardNotifier$$ExternalSyntheticLambda1 r1 = new KeyboardNotifier$$ExternalSyntheticLambda1(this);
        this.onGlobalLayoutListener = r1;
        this.rootView = r3;
        this.listener = r5;
        this.realRootView = r3;
        if (r3.isAttachedToWindow() == false) goto L5;
        r3.getViewTreeObserver().addOnGlobalLayoutListener(r1);
        r3.addOnLayoutChangeListener(r0);
    L5:
        r3.addOnAttachStateChangeListener(new AnonymousClass1(this, r4, r3));
    }

    private /* synthetic */ void lambda$new$0(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        update();
    }

    private void update() {
        if (this.ignoring == true) goto L30;
        boolean r1 = false;
        if (this.mUseInsets == false) goto L15;
        View r0 = this.realRootView;
        if (r0 != null) goto L10;
        r0 = this.rootView;
    L10:
        WindowInsetsCompat r02 = ViewCompat.getRootWindowInsets(r0);
        if (r02 == null) goto L13;
        int r03 = r02.getInsets(WindowInsetsCompat.Type.ime()).bottom;
    L14:
        this.keyboardHeight = r03;
    L20:
        if (this.mMinusNavBar == false) goto L22;
        this.keyboardHeight = Math.max(0, this.keyboardHeight - AndroidUtilities.navigationBarHeight);
    L22:
        int r04 = this.lastKeyboardHeight;
        int r2 = this.keyboardHeight;
        if (r04 == r2) goto L25;
        r1 = true;
    L25:
        this.lastKeyboardHeight = r2;
        if (r1 == false) goto L29;
        fire();
        return;
    L29:
        return;
    L13:
        r03 = 0;
        goto L14
    L15:
        this.rootView.getWindowVisibleDisplayFrame(this.rect);
        View r05 = this.realRootView;
        if (r05 != null) goto L18;
        r05 = this.rootView;
    L18:
        this.keyboardHeight = r05.getHeight() - this.rect.bottom;
        goto L20
    }

    public void awaitKeyboard() {
        this.awaitingKeyboard = true;
    }

    public void fire() {
        if (this.awaitingKeyboard == true) goto L5;
    L8:
        Utilities.Callback<Integer> r0 = this.listener;
        if (r0 == null) goto L12;
        r0.run(Integer.valueOf(this.keyboardHeight));
        return;
    L12:
        return;
    L5:
        if (this.keyboardHeight < (AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(20.0f))) goto L13;
        this.awaitingKeyboard = false;
        goto L8
    }

    public int getKeyboardHeight() {
        return this.keyboardHeight;
    }

    public void ignore(boolean r1) {
        this.ignoring = r1;
        update();
    }

    public boolean keyboardVisible() {
        if (this.keyboardHeight <= (AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(20.0f))) goto L5;
        return true;
    L5:
        if (this.awaitingKeyboard == true) goto L11;
        return false;
    L11:
        return true;
    }

    public KeyboardNotifier useInsets() {
        this.mUseInsets = true;
        return this;
    }

    public KeyboardNotifier useMinusNavbar() {
        this.mMinusNavBar = true;
        return this;
    }
}
