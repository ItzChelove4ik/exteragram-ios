package com.google.android.material.animation;

import android.util.Property;
import android.view.ViewGroup;
import com.google.android.material.R;

/* loaded from: classes5.dex */
public class ChildrenAlphaProperty extends Property<ViewGroup, Float> {
    public static final Property<ViewGroup, Float> CHILDREN_ALPHA = null;

    static {
        CHILDREN_ALPHA = new ChildrenAlphaProperty("childrenAlpha");
    }

    private ChildrenAlphaProperty(String r2) {
        super(Float.class, r2);
    }

    /* renamed from: get, reason: avoid collision after fix types in other method */
    public Float get2(ViewGroup r1) {
        Float r0 = (Float) r1.getTag(R.id.mtrl_internal_children_alpha_tag);
        if (r0 == null) goto L6;
        return r0;
    L6:
        return Float.valueOf(1.0f);
    }

    @Override // android.util.Property
    public /* bridge */ /* synthetic */ Float get(ViewGroup r1) {
        return get2(r1);
    }

    /* renamed from: set, reason: avoid collision after fix types in other method */
    public void set2(ViewGroup r3, Float r4) {
        float r2 = r4.floatValue();
        r3.setTag(R.id.mtrl_internal_children_alpha_tag, r4);
        int r42 = r3.getChildCount();
        int r0 = 0;
    L3:
        if (r0 >= r42) goto L5;
        r3.getChildAt(r0).setAlpha(r2);
        r0 = r0 + 1;
        goto L3
    }

    @Override // android.util.Property
    public /* bridge */ /* synthetic */ void set(ViewGroup r1, Float r2) {
        set2(r1, r2);
    }
}
