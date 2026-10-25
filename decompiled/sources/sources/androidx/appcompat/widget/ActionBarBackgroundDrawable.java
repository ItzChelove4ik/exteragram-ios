package androidx.appcompat.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;

/* loaded from: classes3.dex */
class ActionBarBackgroundDrawable extends Drawable {
    final ActionBarContainer mContainer;

    public static class Api21Impl {
        public static void getOutline(Drawable r0, Outline r1) {
            r0.getOutline(r1);
        }
    }

    public ActionBarBackgroundDrawable(ActionBarContainer r1) {
        this.mContainer = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r3) {
        ActionBarContainer r0 = this.mContainer;
        if (r0.mIsSplit == false) goto L8;
        Drawable r2 = r0.mSplitBackground;
        if (r2 == null) goto L17;
        r2.draw(r3);
        return;
    L17:
        return;
    L8:
        Drawable r02 = r0.mBackground;
        if (r02 == null) goto L11;
        r02.draw(r3);
    L11:
        ActionBarContainer r22 = this.mContainer;
        Drawable r03 = r22.mStackedBackground;
        if (r03 != null) goto L14;
        return;
    L14:
        if (r22.mIsStacked == false) goto L19;
        r03.draw(r3);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline r2) {
        ActionBarContainer r1 = this.mContainer;
        if (r1.mIsSplit == true) goto L5;
        Drawable r12 = r1.mBackground;
        if (r12 == null) goto L13;
        Api21Impl.getOutline(r12, r2);
        return;
    L13:
        return;
    L5:
        if (r1.mSplitBackground == null) goto L12;
        Api21Impl.getOutline(r1.mBackground, r2);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
