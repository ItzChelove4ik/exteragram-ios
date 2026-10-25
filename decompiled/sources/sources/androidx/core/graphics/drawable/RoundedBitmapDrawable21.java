package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.view.Gravity;

/* loaded from: classes3.dex */
class RoundedBitmapDrawable21 extends RoundedBitmapDrawable {
    public RoundedBitmapDrawable21(Resources r1, Bitmap r2) {
        super(r1, r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline r2) {
        updateDstRect();
        r2.setRoundRect(this.mDstRect, getCornerRadius());
    }

    @Override // androidx.core.graphics.drawable.RoundedBitmapDrawable
    public void gravityCompatApply(int r1, int r2, int r3, Rect r4, Rect r5) {
        Gravity.apply(r1, r2, r3, r4, r5, 0);
    }
}
