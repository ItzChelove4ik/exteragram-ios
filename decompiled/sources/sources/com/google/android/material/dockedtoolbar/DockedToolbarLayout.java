package com.google.android.material.dockedtoolbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.TintTypedArray;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public class DockedToolbarLayout extends FrameLayout {
    private static final int DEF_STYLE_RES = 0;
    private static final String TAG = "DockedToolbarLayout";
    private Boolean paddingBottomSystemWindowInsets;
    private Boolean paddingTopSystemWindowInsets;

    /* renamed from: com.google.android.material.dockedtoolbar.DockedToolbarLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewUtils.OnApplyWindowInsetsListener {
        final /* synthetic */ DockedToolbarLayout this$0;

        public AnonymousClass1(DockedToolbarLayout r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r8, WindowInsetsCompat r9, ViewUtils.RelativePadding r10) {
            if (DockedToolbarLayout.access$000(this.this$0) != null) goto L5;
        L11:
            Insets r0 = r9.getInsets((WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout()) | WindowInsetsCompat.Type.ime());
            int r1 = r0.bottom;
            int r02 = r0.top;
            ViewGroup.LayoutParams r2 = r8.getLayoutParams();
            if (DockedToolbarLayout.access$200(this.this$0, r2, 48) == true) goto L14;
        L18:
            int r3 = 0;
        L20:
            if (DockedToolbarLayout.access$200(this.this$0, r2, 80) == true) goto L22;
        L26:
            int r22 = 0;
        L28:
            if (DockedToolbarLayout.access$100(this.this$0) == null) goto L35;
            if (DockedToolbarLayout.access$100(this.this$0).booleanValue() == true) goto L33;
            r1 = 0;
        L33:
            r22 = r1;
        L35:
            if (DockedToolbarLayout.access$000(this.this$0) != null) goto L37;
        L41:
            r10.top += r3;
            r10.bottom += r22;
            r10.applyToView(r8);
            return r9;
        L37:
            if (DockedToolbarLayout.access$000(this.this$0).booleanValue() == true) goto L40;
            r02 = 0;
        L40:
            r3 = r02;
            goto L41
        L22:
            if (DockedToolbarLayout.access$100(this.this$0) != null) goto L26;
            if (this.this$0.getFitsSystemWindows() == false) goto L26;
            r22 = r1;
            goto L28
        L14:
            if (DockedToolbarLayout.access$000(this.this$0) != null) goto L18;
            if (this.this$0.getFitsSystemWindows() == false) goto L18;
            r3 = r02;
            goto L20
        L5:
            if (DockedToolbarLayout.access$100(this.this$0) == null) goto L11;
            if (DockedToolbarLayout.access$000(this.this$0).booleanValue() == true) goto L11;
            if (DockedToolbarLayout.access$100(this.this$0).booleanValue() == true) goto L11;
            return r9;
        }
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Material3_DockedToolbar;
    }

    public DockedToolbarLayout(Context r2) {
        this(r2, null);
    }

    public DockedToolbarLayout(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.dockedToolbarStyle);
    }

    public DockedToolbarLayout(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, r4, DEF_STYLE_RES);
    }

    public DockedToolbarLayout(Context r7, AttributeSet r8, int r9, int r10) {
        super(MaterialThemeOverlay.wrap(r7, r8, r9, r10), r8, r9);
        Context r0 = getContext();
        TintTypedArray r82 = ThemeEnforcement.obtainTintedStyledAttributes(r0, r8, R.styleable.DockedToolbar, r9, r10, new int[0]);
        if (r82.hasValue(R.styleable.DockedToolbar_backgroundTint) == false) goto L6;
        int r72 = r82.getColor(R.styleable.DockedToolbar_backgroundTint, 0);
        MaterialShapeDrawable r102 = new MaterialShapeDrawable(ShapeAppearanceModel.builder(r0, r8, r9, r10).build());
        r102.setFillColor(ColorStateList.valueOf(r72));
        setBackground(r102);
    L6:
        if (r82.hasValue(R.styleable.DockedToolbar_paddingTopSystemWindowInsets) == false) goto L9;
        this.paddingTopSystemWindowInsets = Boolean.valueOf(r82.getBoolean(R.styleable.DockedToolbar_paddingTopSystemWindowInsets, true));
    L9:
        if (r82.hasValue(R.styleable.DockedToolbar_paddingBottomSystemWindowInsets) == false) goto L11;
        this.paddingBottomSystemWindowInsets = Boolean.valueOf(r82.getBoolean(R.styleable.DockedToolbar_paddingBottomSystemWindowInsets, true));
    L11:
        ViewUtils.doOnApplyWindowInsets(this, new AnonymousClass1(this));
        setImportantForAccessibility(1);
        r82.recycle();
    }

    public static /* synthetic */ Boolean access$000(DockedToolbarLayout r0) {
        return r0.paddingTopSystemWindowInsets;
    }

    public static /* synthetic */ Boolean access$100(DockedToolbarLayout r0) {
        return r0.paddingBottomSystemWindowInsets;
    }

    public static /* synthetic */ boolean access$200(DockedToolbarLayout r0, ViewGroup.LayoutParams r1, int r2) {
        return r0.hasGravity(r1, r2);
    }

    private boolean hasGravity(ViewGroup.LayoutParams r3, int r4) {
        if ((r3 instanceof CoordinatorLayout.LayoutParams) == false) goto L9;
        if ((((CoordinatorLayout.LayoutParams) r3).gravity & r4) != r4) goto L7;
        return true;
    L7:
        return false;
    L9:
        if ((r3 instanceof FrameLayout.LayoutParams) == true) goto L11;
    L13:
        return false;
    L11:
        if ((((FrameLayout.LayoutParams) r3).gravity & r4) != r4) goto L13;
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r6, int r7) {
        super.onMeasure(r6, r7);
        if (View.MeasureSpec.getMode(r7) == 1073741824) goto L10;
        int r72 = getChildCount();
        int r1 = Math.max(getMeasuredHeight(), (getSuggestedMinimumHeight() + getPaddingTop()) + getPaddingBottom());
        int r2 = 0;
    L5:
        if (r2 >= r72) goto L7;
        measureChild(getChildAt(r2), r6, View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30));
        r2 = r2 + 1;
        goto L5
    L7:
        setMeasuredDimension(getMeasuredWidth(), r1);
        return;
    }
}
