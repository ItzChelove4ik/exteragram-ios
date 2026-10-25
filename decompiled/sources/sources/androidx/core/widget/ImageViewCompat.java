package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.widget.ImageView;

/* loaded from: classes3.dex */
public abstract class ImageViewCompat {
    public static ColorStateList getImageTintList(ImageView r0) {
        return r0.getImageTintList();
    }

    public static PorterDuff.Mode getImageTintMode(ImageView r0) {
        return r0.getImageTintMode();
    }

    public static void setImageTintList(ImageView r0, ColorStateList r1) {
        r0.setImageTintList(r1);
    }

    public static void setImageTintMode(ImageView r0, PorterDuff.Mode r1) {
        r0.setImageTintMode(r1);
    }
}
