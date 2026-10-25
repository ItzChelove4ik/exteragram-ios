package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CheckedTextView;

/* loaded from: classes3.dex */
public abstract class CheckedTextViewCompat {
    @Deprecated
    public static Drawable getCheckMarkDrawable(CheckedTextView r0) {
        return r0.getCheckMarkDrawable();
    }

    public static void setCheckMarkTintList(CheckedTextView r0, ColorStateList r1) {
        r0.setCheckMarkTintList(r1);
    }

    public static void setCheckMarkTintMode(CheckedTextView r0, PorterDuff.Mode r1) {
        r0.setCheckMarkTintMode(r1);
    }
}
