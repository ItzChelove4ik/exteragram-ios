package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public abstract class DayViewDecorator implements Parcelable {
    public DayViewDecorator() {
    }

    public ColorStateList getBackgroundColor(Context r1, int r2, int r3, int r4, boolean r5, boolean r6) {
        return null;
    }

    public Drawable getCompoundDrawableBottom(Context r1, int r2, int r3, int r4, boolean r5, boolean r6) {
        return null;
    }

    public Drawable getCompoundDrawableLeft(Context r1, int r2, int r3, int r4, boolean r5, boolean r6) {
        return null;
    }

    public Drawable getCompoundDrawableRight(Context r1, int r2, int r3, int r4, boolean r5, boolean r6) {
        return null;
    }

    public Drawable getCompoundDrawableTop(Context r1, int r2, int r3, int r4, boolean r5, boolean r6) {
        return null;
    }

    public CharSequence getContentDescription(Context r1, int r2, int r3, int r4, boolean r5, boolean r6, CharSequence r7) {
        return r7;
    }

    public ColorStateList getTextColor(Context r1, int r2, int r3, int r4, boolean r5, boolean r6) {
        return null;
    }

    public void initialize(Context r1) {
    }
}
