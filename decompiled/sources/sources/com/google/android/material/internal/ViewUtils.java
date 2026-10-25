package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.google.android.material.R;
import com.google.android.material.drawable.DrawableUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ViewUtils {
    public static final int EDGE_TO_EDGE_FLAGS = 768;

    /* renamed from: com.google.android.material.internal.ViewUtils$1, reason: invalid class name */
    public class AnonymousClass1 implements OnApplyWindowInsetsListener {
        final /* synthetic */ OnApplyWindowInsetsListener val$listener;
        final /* synthetic */ boolean val$paddingBottomSystemWindowInsets;
        final /* synthetic */ boolean val$paddingLeftSystemWindowInsets;
        final /* synthetic */ boolean val$paddingRightSystemWindowInsets;

        public AnonymousClass1(boolean r1, boolean r2, boolean r3, OnApplyWindowInsetsListener r4) {
            this.val$paddingBottomSystemWindowInsets = r1;
            this.val$paddingLeftSystemWindowInsets = r2;
            this.val$paddingRightSystemWindowInsets = r3;
            this.val$listener = r4;
        }

        @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r4, WindowInsetsCompat r5, RelativePadding r6) {
            if (this.val$paddingBottomSystemWindowInsets == false) goto L5;
            r6.bottom += r5.getSystemWindowInsetBottom();
        L5:
            boolean r0 = ViewUtils.isLayoutRtl(r4);
            if (this.val$paddingLeftSystemWindowInsets == false) goto L11;
            if (r0 == false) goto L9;
            r6.end += r5.getSystemWindowInsetLeft();
            goto L11
        L9:
            r6.start += r5.getSystemWindowInsetLeft();
        L11:
            if (this.val$paddingRightSystemWindowInsets == false) goto L15;
            if (r0 == false) goto L14;
            r6.start += r5.getSystemWindowInsetRight();
            goto L15
        L14:
            r6.end += r5.getSystemWindowInsetRight();
        L15:
            r6.applyToView(r4);
            OnApplyWindowInsetsListener r3 = this.val$listener;
            if (r3 != null) goto L18;
            return r5;
        L18:
            return r3.onApplyWindowInsets(r4, r5, r6);
        }
    }

    /* renamed from: com.google.android.material.internal.ViewUtils$2, reason: invalid class name */
    public class AnonymousClass2 implements androidx.core.view.OnApplyWindowInsetsListener {
        final /* synthetic */ RelativePadding val$initialPadding;
        final /* synthetic */ OnApplyWindowInsetsListener val$listener;

        public AnonymousClass2(OnApplyWindowInsetsListener r1, RelativePadding r2) {
            this.val$listener = r1;
            this.val$initialPadding = r2;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r3, WindowInsetsCompat r4) {
            return this.val$listener.onApplyWindowInsets(r3, r4, new RelativePadding(this.val$initialPadding));
        }
    }

    /* renamed from: com.google.android.material.internal.ViewUtils$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnAttachStateChangeListener {
        public AnonymousClass3() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            r1.removeOnAttachStateChangeListener(this);
            r1.requestApplyInsets();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
        }
    }

    /* renamed from: com.google.android.material.internal.ViewUtils$4, reason: invalid class name */
    public class AnonymousClass4 implements ViewOverlayImpl {
        final /* synthetic */ View val$view;

        public AnonymousClass4(View r1) {
            this.val$view = r1;
        }

        @Override // com.google.android.material.internal.ViewOverlayImpl
        public void add(Drawable r1) {
            this.val$view.getOverlay().add(r1);
        }

        @Override // com.google.android.material.internal.ViewOverlayImpl
        public void remove(Drawable r1) {
            this.val$view.getOverlay().remove(r1);
        }
    }

    public interface OnApplyWindowInsetsListener {
        WindowInsetsCompat onApplyWindowInsets(View r1, WindowInsetsCompat r2, RelativePadding r3);
    }

    public static class RelativePadding {
        public int bottom;
        public int end;
        public int start;
        public int top;

        public RelativePadding(int r1, int r2, int r3, int r4) {
            this.start = r1;
            this.top = r2;
            this.end = r3;
            this.bottom = r4;
        }

        public RelativePadding(RelativePadding r2) {
            this.start = r2.start;
            this.top = r2.top;
            this.end = r2.end;
            this.bottom = r2.bottom;
        }

        public void applyToView(View r4) {
            r4.setPaddingRelative(this.start, this.top, this.end, this.bottom);
        }
    }

    public static /* synthetic */ void $r8$lambda$sF8kuJD8RuqXLovurHk0qlx1QLI(View r0, boolean r1) {
        showKeyboard(r0, r1);
    }

    private ViewUtils() {
    }

    public static void addOnGlobalLayoutListener(View r0, ViewTreeObserver.OnGlobalLayoutListener r1) {
        if (r0 == null) goto L5;
        r0.getViewTreeObserver().addOnGlobalLayoutListener(r1);
        return;
    }

    public static Rect calculateOffsetRectFromBounds(View r5, View r6) {
        int[] r1 = new int[2];
        r6.getLocationOnScreen(r1);
        int r3 = r1[0];
        int r12 = r1[1];
        int[] r0 = new int[2];
        r5.getLocationOnScreen(r0);
        int r52 = r0[0];
        int r32 = r3 - r52;
        int r13 = r12 - r0[1];
        return new Rect(r32, r13, r6.getWidth() + r32, r6.getHeight() + r13);
    }

    public static Rect calculateRectFromBounds(View r1) {
        return calculateRectFromBounds(r1, 0);
    }

    public static Rect calculateRectFromBounds(View r4, int r5) {
        return new Rect(r4.getLeft(), r4.getTop() + r5, r4.getRight(), r4.getBottom() + r5);
    }

    public static void doOnApplyWindowInsets(View r1, AttributeSet r2, int r3, int r4) {
        doOnApplyWindowInsets(r1, r2, r3, r4, null);
    }

    public static void doOnApplyWindowInsets(View r2, AttributeSet r3, int r4, int r5, OnApplyWindowInsetsListener r6) {
        TypedArray r32 = r2.getContext().obtainStyledAttributes(r3, R.styleable.Insets, r4, r5);
        boolean r42 = r32.getBoolean(R.styleable.Insets_paddingBottomSystemWindowInsets, false);
        boolean r0 = r32.getBoolean(R.styleable.Insets_paddingLeftSystemWindowInsets, false);
        boolean r52 = r32.getBoolean(R.styleable.Insets_paddingRightSystemWindowInsets, false);
        r32.recycle();
        doOnApplyWindowInsets(r2, new AnonymousClass1(r42, r0, r52, r6));
    }

    public static void doOnApplyWindowInsets(View r5, OnApplyWindowInsetsListener r6) {
        ViewCompat.setOnApplyWindowInsetsListener(r5, new AnonymousClass2(r6, new RelativePadding(r5.getPaddingStart(), r5.getPaddingTop(), r5.getPaddingEnd(), r5.getPaddingBottom())));
        requestApplyInsetsWhenAttached(r5);
    }

    public static float dpToPx(Context r1, int r2) {
        return TypedValue.applyDimension(1, r2, r1.getResources().getDisplayMetrics());
    }

    public static Integer getBackgroundColor(View r0) {
        ColorStateList r02 = DrawableUtils.getColorStateListOrNull(r0.getBackground());
        if (r02 != null) goto L5;
        return null;
    L5:
        return Integer.valueOf(r02.getDefaultColor());
    }

    public static List<View> getChildren(View r3) {
        ArrayList r0 = new ArrayList();
        if ((r3 instanceof ViewGroup) == false) goto L8;
        ViewGroup r32 = (ViewGroup) r3;
        int r1 = 0;
    L6:
        if (r1 >= r32.getChildCount()) goto L8;
        r0.add(r32.getChildAt(r1));
        r1 = r1 + 1;
    L8:
        return r0;
    }

    public static ViewGroup getContentView(View r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        View r1 = r3.getRootView();
        ViewGroup r2 = (ViewGroup) r1.findViewById(android.R.id.content);
        if (r2 == null) goto L8;
        return r2;
    L8:
        if (r1 != r3) goto L10;
    L13:
        return null;
    L10:
        if ((r1 instanceof ViewGroup) == false) goto L13;
        return (ViewGroup) r1;
    }

    @Deprecated
    public static ViewOverlayImpl getContentViewOverlay(View r0) {
        return getOverlay(getContentView(r0));
    }

    private static InputMethodManager getInputMethodManager(View r1) {
        return (InputMethodManager) ContextCompat.getSystemService(r1.getContext(), InputMethodManager.class);
    }

    @Deprecated
    public static ViewOverlayImpl getOverlay(View r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return new AnonymousClass4(r1);
    }

    public static float getParentAbsoluteElevation(View r2) {
        ViewParent r22 = r2.getParent();
        float r0 = 0.0f;
    L4:
        if ((r22 instanceof View) == false) goto L6;
        r0 = r0 + ((View) r22).getElevation();
        r22 = r22.getParent();
        goto L4
    L6:
        return r0;
    }

    public static void hideKeyboard(View r1) {
        hideKeyboard(r1, true);
    }

    public static void hideKeyboard(View r1, boolean r2) {
        if (r2 == false) goto L7;
        WindowInsetsControllerCompat r22 = ViewCompat.getWindowInsetsController(r1);
        if (r22 == null) goto L7;
        r22.hide(WindowInsetsCompat.Type.ime());
        return;
    L7:
        InputMethodManager r23 = getInputMethodManager(r1);
        if (r23 == null) goto L11;
        r23.hideSoftInputFromWindow(r1.getWindowToken(), 0);
        return;
    }

    public static boolean isLayoutRtl(View r1) {
        if (r1.getLayoutDirection() != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public static PorterDuff.Mode parseTintMode(int r1, PorterDuff.Mode r2) {
        if (r1 == 3) goto L21;
        if (r1 == 5) goto L19;
        if (r1 == 9) goto L17;
        switch(r1) {
            case 14: goto L15;
            case 15: goto L13;
            case 16: goto L11;
            default: goto L9;
        };
    L9:
        return r2;
    L11:
        return PorterDuff.Mode.ADD;
    L13:
        return PorterDuff.Mode.SCREEN;
    L15:
        return PorterDuff.Mode.MULTIPLY;
    L17:
        return PorterDuff.Mode.SRC_ATOP;
    L19:
        return PorterDuff.Mode.SRC_IN;
    L21:
        return PorterDuff.Mode.SRC_OVER;
    }

    public static void removeOnGlobalLayoutListener(View r0, ViewTreeObserver.OnGlobalLayoutListener r1) {
        if (r0 == null) goto L5;
        removeOnGlobalLayoutListener(r0.getViewTreeObserver(), r1);
        return;
    }

    public static void removeOnGlobalLayoutListener(ViewTreeObserver r0, ViewTreeObserver.OnGlobalLayoutListener r1) {
        r0.removeOnGlobalLayoutListener(r1);
    }

    public static void requestApplyInsetsWhenAttached(View r1) {
        if (r1.isAttachedToWindow() == false) goto L6;
        r1.requestApplyInsets();
        return;
    L6:
        r1.addOnAttachStateChangeListener(new AnonymousClass3());
    }

    public static void requestFocusAndShowKeyboard(View r1) {
        requestFocusAndShowKeyboard(r1, true);
    }

    public static void requestFocusAndShowKeyboard(View r1, boolean r2) {
        r1.requestFocus();
        r1.post(new ViewUtils$$ExternalSyntheticLambda0(r1, r2));
    }

    public static void setBoundsFromRect(View r1, Rect r2) {
        r1.setLeft(r2.left);
        r1.setTop(r2.top);
        r1.setRight(r2.right);
        r1.setBottom(r2.bottom);
    }

    public static void showKeyboard(View r1) {
        showKeyboard(r1, true);
    }

    public static void showKeyboard(View r1, boolean r2) {
        if (r2 == false) goto L7;
        WindowInsetsControllerCompat r22 = ViewCompat.getWindowInsetsController(r1);
        if (r22 == null) goto L7;
        r22.show(WindowInsetsCompat.Type.ime());
        return;
    L7:
        getInputMethodManager(r1).showSoftInput(r1, 1);
    }
}
