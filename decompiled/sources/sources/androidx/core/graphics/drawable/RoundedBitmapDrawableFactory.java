package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;

/* loaded from: classes3.dex */
public abstract class RoundedBitmapDrawableFactory {
    public static RoundedBitmapDrawable create(Resources r1, Bitmap r2) {
        return new RoundedBitmapDrawable21(r1, r2);
    }
}
