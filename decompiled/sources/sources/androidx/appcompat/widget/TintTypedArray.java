package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

/* loaded from: classes3.dex */
public class TintTypedArray {
    private final Context mContext;
    private TypedValue mTypedValue;
    private final TypedArray mWrapped;

    private TintTypedArray(Context r1, TypedArray r2) {
        this.mContext = r1;
        this.mWrapped = r2;
    }

    public static TintTypedArray obtainStyledAttributes(Context r1, int r2, int[] r3) {
        return new TintTypedArray(r1, r1.obtainStyledAttributes(r2, r3));
    }

    public static TintTypedArray obtainStyledAttributes(Context r1, AttributeSet r2, int[] r3) {
        return new TintTypedArray(r1, r1.obtainStyledAttributes(r2, r3));
    }

    public static TintTypedArray obtainStyledAttributes(Context r1, AttributeSet r2, int[] r3, int r4, int r5) {
        return new TintTypedArray(r1, r1.obtainStyledAttributes(r2, r3, r4, r5));
    }

    public boolean getBoolean(int r1, boolean r2) {
        return this.mWrapped.getBoolean(r1, r2);
    }

    public int getColor(int r1, int r2) {
        return this.mWrapped.getColor(r1, r2);
    }

    public ColorStateList getColorStateList(int r3) {
        if (this.mWrapped.hasValue(r3) == false) goto L10;
        int r0 = this.mWrapped.getResourceId(r3, 0);
        if (r0 == 0) goto L10;
        ColorStateList r02 = AppCompatResources.getColorStateList(this.mContext, r0);
        if (r02 == null) goto L10;
        return r02;
    L10:
        return this.mWrapped.getColorStateList(r3);
    }

    public float getDimension(int r1, float r2) {
        return this.mWrapped.getDimension(r1, r2);
    }

    public int getDimensionPixelOffset(int r1, int r2) {
        return this.mWrapped.getDimensionPixelOffset(r1, r2);
    }

    public int getDimensionPixelSize(int r1, int r2) {
        return this.mWrapped.getDimensionPixelSize(r1, r2);
    }

    public Drawable getDrawable(int r3) {
        if (this.mWrapped.hasValue(r3) == false) goto L9;
        int r0 = this.mWrapped.getResourceId(r3, 0);
        if (r0 == 0) goto L9;
        return AppCompatResources.getDrawable(this.mContext, r0);
    L9:
        return this.mWrapped.getDrawable(r3);
    }

    public Drawable getDrawableIfKnown(int r3) {
        if (this.mWrapped.hasValue(r3) == false) goto L8;
        int r32 = this.mWrapped.getResourceId(r3, 0);
        if (r32 != 0) goto L7;
        return null;
    L7:
        return AppCompatDrawableManager.get().getDrawable(this.mContext, r32, true);
    L8:
        return null;
    }

    public float getFloat(int r1, float r2) {
        return this.mWrapped.getFloat(r1, r2);
    }

    public Typeface getFont(int r3, int r4, ResourcesCompat.FontCallback r5) {
        int r32 = this.mWrapped.getResourceId(r3, 0);
        if (r32 != 0) goto L7;
        return null;
    L7:
        if (this.mTypedValue != null) goto L10;
        this.mTypedValue = new TypedValue();
    L10:
        return ResourcesCompat.getFont(this.mContext, r32, this.mTypedValue, r4, r5);
    }

    public int getInt(int r1, int r2) {
        return this.mWrapped.getInt(r1, r2);
    }

    public int getInteger(int r1, int r2) {
        return this.mWrapped.getInteger(r1, r2);
    }

    public int getLayoutDimension(int r1, int r2) {
        return this.mWrapped.getLayoutDimension(r1, r2);
    }

    public int getResourceId(int r1, int r2) {
        return this.mWrapped.getResourceId(r1, r2);
    }

    public String getString(int r1) {
        return this.mWrapped.getString(r1);
    }

    public CharSequence getText(int r1) {
        return this.mWrapped.getText(r1);
    }

    public CharSequence[] getTextArray(int r1) {
        return this.mWrapped.getTextArray(r1);
    }

    public TypedArray getWrappedTypeArray() {
        return this.mWrapped;
    }

    public boolean hasValue(int r1) {
        return this.mWrapped.hasValue(r1);
    }

    public TypedValue peekValue(int r1) {
        return this.mWrapped.peekValue(r1);
    }

    public void recycle() {
        this.mWrapped.recycle();
    }
}
