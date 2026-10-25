package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;

/* loaded from: classes3.dex */
public abstract class CompoundButtonCompat {
    public static Drawable getButtonDrawable(CompoundButton r0) {
        return r0.getButtonDrawable();
    }

    public static ColorStateList getButtonTintList(CompoundButton r0) {
        return r0.getButtonTintList();
    }

    public static PorterDuff.Mode getButtonTintMode(CompoundButton r0) {
        return r0.getButtonTintMode();
    }

    public static void setButtonTintList(CompoundButton r0, ColorStateList r1) {
        r0.setButtonTintList(r1);
    }

    public static void setButtonTintMode(CompoundButton r0, PorterDuff.Mode r1) {
        r0.setButtonTintMode(r1);
    }
}
