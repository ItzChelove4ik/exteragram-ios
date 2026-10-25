package com.google.android.material.textview;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: classes5.dex */
public class MaterialTextView extends AppCompatTextView {
    public MaterialTextView(Context r2) {
        this(r2, null);
    }

    public MaterialTextView(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.textViewStyle);
    }

    public MaterialTextView(Context r2, AttributeSet r3, int r4) {
        super(MaterialThemeOverlay.wrap(r2, r3, r4, 0), r3, r4);
        initialize(r3, r4, 0);
    }

    @Deprecated
    public MaterialTextView(Context r1, AttributeSet r2, int r3, int r4) {
        super(MaterialThemeOverlay.wrap(r1, r2, r3, r4), r2, r3);
        initialize(r2, r3, r4);
    }

    private void applyLineHeightFromViewAppearance(Resources.Theme r3, int r4) {
        TypedArray r32 = r3.obtainStyledAttributes(r4, com.google.android.material.R.styleable.MaterialTextAppearance);
        int r42 = readFirstAvailableDimension(getContext(), r32, new int[]{com.google.android.material.R.styleable.MaterialTextAppearance_android_lineHeight, com.google.android.material.R.styleable.MaterialTextAppearance_lineHeight});
        r32.recycle();
        if (r42 < 0) goto L6;
        setLineHeight(r42);
        return;
    }

    private static boolean canApplyTextAppearanceLineHeight(Context r2) {
        return MaterialAttributes.resolveBoolean(r2, com.google.android.material.R.attr.textAppearanceLineHeightEnabled, true);
    }

    private static int findViewAppearanceResourceId(Resources.Theme r1, AttributeSet r2, int r3, int r4) {
        TypedArray r12 = r1.obtainStyledAttributes(r2, com.google.android.material.R.styleable.MaterialTextView, r3, r4);
        int r22 = r12.getResourceId(com.google.android.material.R.styleable.MaterialTextView_android_textAppearance, -1);
        r12.recycle();
        return r22;
    }

    private void initialize(AttributeSet r3, int r4, int r5) {
        Context r0 = getContext();
        if (canApplyTextAppearanceLineHeight(r0) == false) goto L10;
        Resources.Theme r1 = r0.getTheme();
        if (viewAttrsHasLineHeight(r0, r1, r3, r4, r5) == true) goto L11;
        int r32 = findViewAppearanceResourceId(r1, r3, r4, r5);
        if (r32 == (-1)) goto L12;
        applyLineHeightFromViewAppearance(r1, r32);
        return;
    L12:
        return;
    L11:
        return;
    }

    private static int readFirstAvailableDimension(Context r4, TypedArray r5, int... r6) {
        int r1 = 0;
        int r2 = -1;
    L4:
        if (r1 >= r6.length) goto L7;
        if (r2 >= 0) goto L7;
        r2 = MaterialResources.getDimensionPixelSize(r4, r5, r6[r1], -1);
        r1 = r1 + 1;
    L7:
        return r2;
    }

    private static boolean viewAttrsHasLineHeight(Context r1, Resources.Theme r2, AttributeSet r3, int r4, int r5) {
        TypedArray r22 = r2.obtainStyledAttributes(r3, com.google.android.material.R.styleable.MaterialTextView, r4, r5);
        int r12 = readFirstAvailableDimension(r1, r22, new int[]{com.google.android.material.R.styleable.MaterialTextView_android_lineHeight, com.google.android.material.R.styleable.MaterialTextView_lineHeight});
        r22.recycle();
        if (r12 == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public void setTextAppearance(Context r2, int r3) {
        super.setTextAppearance(r2, r3);
        if (canApplyTextAppearanceLineHeight(r2) == false) goto L6;
        applyLineHeightFromViewAppearance(r2.getTheme(), r3);
        return;
    }
}
