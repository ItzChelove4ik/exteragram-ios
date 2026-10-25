package com.google.android.material.animation;

import android.graphics.drawable.Drawable;
import android.util.Property;

/* loaded from: classes5.dex */
public class DrawableAlphaProperty extends Property<Drawable, Integer> {
    public static final Property<Drawable, Integer> DRAWABLE_ALPHA_COMPAT = null;

    static {
        DRAWABLE_ALPHA_COMPAT = new DrawableAlphaProperty();
    }

    private DrawableAlphaProperty() {
        super(Integer.class, "drawableAlphaCompat");
    }

    /* renamed from: get, reason: avoid collision after fix types in other method */
    public Integer get2(Drawable r1) {
        return Integer.valueOf(r1.getAlpha());
    }

    @Override // android.util.Property
    public /* bridge */ /* synthetic */ Integer get(Drawable r1) {
        return get2(r1);
    }

    /* renamed from: set, reason: avoid collision after fix types in other method */
    public void set2(Drawable r1, Integer r2) {
        r1.setAlpha(r2.intValue());
    }

    @Override // android.util.Property
    public /* bridge */ /* synthetic */ void set(Drawable r1, Integer r2) {
        set2(r1, r2);
    }
}
