package com.google.android.material.floatingtoolbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: classes5.dex */
public class FloatingToolbarLayout extends FrameLayout {
    private static final int DEF_STYLE_RES = 0;
    private static final String TAG = "FloatingToolbarLayout";
    private int bottomMarginWindowInset;
    private int leftMarginWindowInset;
    private boolean marginBottomSystemWindowInsets;
    private boolean marginLeftSystemWindowInsets;
    private boolean marginRightSystemWindowInsets;
    private boolean marginTopSystemWindowInsets;
    private Rect originalMargins;
    private int rightMarginWindowInset;
    private int topMarginWindowInset;

    /* renamed from: com.google.android.material.floatingtoolbar.FloatingToolbarLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements OnApplyWindowInsetsListener {
        final /* synthetic */ FloatingToolbarLayout this$0;

        public AnonymousClass1(FloatingToolbarLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r3, WindowInsetsCompat r4) {
            if (FloatingToolbarLayout.access$000(this.this$0) == false) goto L5;
        L11:
            Insets r32 = r4.getInsets((WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout()) | WindowInsetsCompat.Type.ime());
            FloatingToolbarLayout.access$402(this.this$0, r32.bottom);
            FloatingToolbarLayout.access$502(this.this$0, r32.top);
            FloatingToolbarLayout.access$602(this.this$0, r32.right);
            FloatingToolbarLayout.access$702(this.this$0, r32.left);
            FloatingToolbarLayout.access$800(this.this$0);
            return r4;
        L5:
            if (FloatingToolbarLayout.access$100(this.this$0) == true) goto L11;
            if (FloatingToolbarLayout.access$200(this.this$0) == true) goto L11;
            if (FloatingToolbarLayout.access$300(this.this$0) == true) goto L11;
            return r4;
        }
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Material3_FloatingToolbar;
    }

    public FloatingToolbarLayout(Context r2) {
        this(r2, null);
    }

    public FloatingToolbarLayout(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.floatingToolbarStyle);
    }

    public FloatingToolbarLayout(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, r4, DEF_STYLE_RES);
    }

    public FloatingToolbarLayout(Context r7, AttributeSet r8, int r9, int r10) {
        super(MaterialThemeOverlay.wrap(r7, r8, r9, r10), r8, r9);
        Context r0 = getContext();
        TintTypedArray r82 = ThemeEnforcement.obtainTintedStyledAttributes(r0, r8, R.styleable.FloatingToolbar, r9, r10, new int[0]);
        if (r82.hasValue(R.styleable.FloatingToolbar_backgroundTint) == false) goto L5;
        int r92 = r82.getColor(R.styleable.FloatingToolbar_backgroundTint, 0);
        MaterialShapeDrawable r02 = new MaterialShapeDrawable(ShapeAppearanceModel.builder(r0, r8, r9, r10).build());
        r02.setFillColor(ColorStateList.valueOf(r92));
        setBackground(r02);
    L5:
        this.marginLeftSystemWindowInsets = r82.getBoolean(R.styleable.FloatingToolbar_marginLeftSystemWindowInsets, true);
        this.marginTopSystemWindowInsets = r82.getBoolean(R.styleable.FloatingToolbar_marginTopSystemWindowInsets, false);
        this.marginRightSystemWindowInsets = r82.getBoolean(R.styleable.FloatingToolbar_marginRightSystemWindowInsets, true);
        this.marginBottomSystemWindowInsets = r82.getBoolean(R.styleable.FloatingToolbar_marginBottomSystemWindowInsets, true);
        ViewCompat.setOnApplyWindowInsetsListener(this, new AnonymousClass1(this));
        r82.recycle();
    }

    public static /* synthetic */ boolean access$000(FloatingToolbarLayout r0) {
        return r0.marginLeftSystemWindowInsets;
    }

    public static /* synthetic */ boolean access$100(FloatingToolbarLayout r0) {
        return r0.marginRightSystemWindowInsets;
    }

    public static /* synthetic */ boolean access$200(FloatingToolbarLayout r0) {
        return r0.marginTopSystemWindowInsets;
    }

    public static /* synthetic */ boolean access$300(FloatingToolbarLayout r0) {
        return r0.marginBottomSystemWindowInsets;
    }

    public static /* synthetic */ int access$402(FloatingToolbarLayout r0, int r1) {
        r0.bottomMarginWindowInset = r1;
        return r1;
    }

    public static /* synthetic */ int access$502(FloatingToolbarLayout r0, int r1) {
        r0.topMarginWindowInset = r1;
        return r1;
    }

    public static /* synthetic */ int access$602(FloatingToolbarLayout r0, int r1) {
        r0.rightMarginWindowInset = r1;
        return r1;
    }

    public static /* synthetic */ int access$702(FloatingToolbarLayout r0, int r1) {
        r0.leftMarginWindowInset = r1;
        return r1;
    }

    public static /* synthetic */ void access$800(FloatingToolbarLayout r0) {
        r0.updateMargins();
    }

    private void updateMargins() {
        ViewGroup.LayoutParams r0 = getLayoutParams();
        Rect r1 = this.originalMargins;
        if (r1 != null) goto L6;
        Log.w(TAG, "Unable to update margins because original view margins are not set");
        return;
    L6:
        int r2 = r1.left;
        int r4 = 0;
        if (this.marginLeftSystemWindowInsets == false) goto L9;
        int r3 = this.leftMarginWindowInset;
    L10:
        int r22 = r2 + r3;
        int r32 = r1.right;
        if (this.marginRightSystemWindowInsets == false) goto L13;
        int r5 = this.rightMarginWindowInset;
    L14:
        int r33 = r32 + r5;
        int r52 = r1.top;
        if (this.marginTopSystemWindowInsets == false) goto L17;
        int r6 = this.topMarginWindowInset;
    L18:
        int r53 = r52 + r6;
        int r12 = r1.bottom;
        if (this.marginBottomSystemWindowInsets == false) goto L21;
        r4 = this.bottomMarginWindowInset;
    L21:
        int r13 = r12 + r4;
        ViewGroup.MarginLayoutParams r02 = (ViewGroup.MarginLayoutParams) r0;
        if (r02.bottomMargin == r13) goto L24;
    L31:
        r02.bottomMargin = r13;
        r02.leftMargin = r22;
        r02.rightMargin = r33;
        r02.topMargin = r53;
        requestLayout();
        return;
    L24:
        if (r02.leftMargin != r22) goto L31;
        if (r02.rightMargin != r33) goto L31;
        if (r02.topMargin != r53) goto L31;
        return;
    L17:
        r6 = 0;
        goto L18
    L13:
        r5 = 0;
        goto L14
    L9:
        r3 = 0;
        goto L10
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams r5) {
        super.setLayoutParams(r5);
        if ((r5 instanceof ViewGroup.MarginLayoutParams) == false) goto L6;
        ViewGroup.MarginLayoutParams r52 = (ViewGroup.MarginLayoutParams) r5;
        this.originalMargins = new Rect(r52.leftMargin, r52.topMargin, r52.rightMargin, r52.bottomMargin);
        updateMargins();
        return;
    L6:
        this.originalMargins = null;
    }
}
