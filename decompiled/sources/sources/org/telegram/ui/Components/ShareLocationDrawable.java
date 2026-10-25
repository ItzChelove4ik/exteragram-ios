package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;

/* loaded from: classes7.dex */
public class ShareLocationDrawable extends Drawable {
    private int currentType;
    private Drawable drawable;
    private Drawable drawableLeft;
    private Drawable drawableRight;
    private long lastUpdateTime;
    private float[] progress;

    public ShareLocationDrawable(Context r3, int r4) {
        this.lastUpdateTime = 0;
        this.progress = new float[]{0.0f, -0.5f};
        this.currentType = r4;
        if (r4 != 4) goto L7;
        this.drawable = r3.getResources().getDrawable(R.drawable.filled_extend_location).mutate();
        this.drawableLeft = r3.getResources().getDrawable(R.drawable.smallanimationpinleft).mutate();
        this.drawableRight = r3.getResources().getDrawable(R.drawable.smallanimationpinright).mutate();
        return;
    L7:
        if (r4 != 5) goto L11;
        this.drawable = r3.getResources().getDrawable(R.drawable.filled_stop_location).mutate();
        this.drawableLeft = r3.getResources().getDrawable(R.drawable.smallanimationpinleft).mutate();
        this.drawableRight = r3.getResources().getDrawable(R.drawable.smallanimationpinright).mutate();
        return;
    L11:
        if (r4 != 1) goto L14;
        this.drawable = r3.getResources().getDrawable(R.drawable.smallanimationpin).mutate();
        this.drawableLeft = r3.getResources().getDrawable(R.drawable.smallanimationpinleft).mutate();
        this.drawableRight = r3.getResources().getDrawable(R.drawable.smallanimationpinright).mutate();
        return;
    L14:
        this.drawable = r3.getResources().getDrawable(R.drawable.animationpin).mutate();
        this.drawableLeft = r3.getResources().getDrawable(R.drawable.animationpinleft).mutate();
        this.drawableRight = r3.getResources().getDrawable(R.drawable.animationpinright).mutate();
    }

    private void update() {
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        if (r2 <= 16) goto L5;
        r2 = 16;
    L5:
        int r02 = 0;
    L7:
        if (r02 >= 2) goto L15;
        float[] r1 = this.progress;
        if (r1[r02] < 1.0f) goto L11;
        r1[r02] = 0.0f;
    L11:
        float r4 = r1[r02] + (r2 / 1300.0f);
        r1[r02] = r4;
        if (r4 <= 1.0f) goto L14;
        r1[r02] = 1.0f;
    L14:
        r02 = r02 + 1;
        goto L7
    L15:
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r24) {
        int r2 = this.drawable.getIntrinsicWidth();
        int r3 = this.drawable.getIntrinsicHeight();
        int r4 = this.currentType;
        int r5 = 3;
        int r6 = 5;
        int r7 = 1;
        if (r4 == 4) goto L13;
        if (r4 == 5) goto L13;
        if (r4 != 3) goto L8;
        int r42 = AndroidUtilities.dp(44.0f);
    L14:
        int r10 = getBounds().top + ((getIntrinsicHeight() - r42) / 2);
        int r11 = getBounds().left + ((getIntrinsicWidth() - r42) / 2);
        int r22 = r2 + r11;
        this.drawable.setBounds(r11, r10, r22, r10 + r3);
        this.drawable.draw(r24);
        int r43 = 0;
    L15:
        if (r43 >= 2) goto L40;
        float r12 = this.progress[r43];
        if (r12 < 0.0f) goto L39;
        float r122 = (r12 * 0.5f) + 0.5f;
        int r14 = this.currentType;
        if (r14 == 4) goto L33;
        if (r14 == r6) goto L33;
        if (r14 != r5) goto L27;
        int r142 = AndroidUtilities.dp(5.0f * r122);
        int r123 = AndroidUtilities.dp(r122 * 18.0f);
        int r15 = AndroidUtilities.dp(this.progress[r43] * 15.0f);
        int r16 = (r11 + AndroidUtilities.dp(2.0f)) - r15;
        int r17 = (r10 + (r3 / 2)) - AndroidUtilities.dp(7.0f);
        int r18 = AndroidUtilities.dp(2.0f);
    L26:
        int r182 = (r22 - r18) + r15;
    L34:
        float r52 = this.progress[r43];
        if (r52 >= 0.5f) goto L37;
        float r53 = r52 / 0.5f;
    L38:
        int r54 = (int) (r53 * 255.0f);
        this.drawableLeft.setAlpha(r54);
        int r62 = r17 - r123;
        int r124 = r17 + r123;
        this.drawableLeft.setBounds(r16 - r142, r62, r16 + r142, r124);
        this.drawableLeft.draw(r24);
        this.drawableRight.setAlpha(r54);
        this.drawableRight.setBounds(r182 - r142, r62, r182 + r142, r124);
        this.drawableRight.draw(r24);
        goto L39
    L37:
        r53 = 1.0f - ((r52 - 0.5f) / 0.5f);
        goto L38
    L27:
        if (r14 != 2) goto L29;
        r142 = AndroidUtilities.dp(5.0f * r122);
        r123 = AndroidUtilities.dp(r122 * 18.0f);
        r15 = AndroidUtilities.dp(this.progress[r43] * 15.0f);
        r16 = (r11 + AndroidUtilities.dp(2.0f)) - r15;
        r17 = r10 + (r3 / 2);
        r18 = AndroidUtilities.dp(2.0f);
        goto L26
    L29:
        if (r14 != r7) goto L32;
        r142 = AndroidUtilities.dp(2.5f * r122);
        r123 = AndroidUtilities.dp(r122 * 6.5f);
        int r55 = AndroidUtilities.dp(this.progress[r43] * 6.0f);
        r16 = (AndroidUtilities.dp(7.0f) + r11) - r55;
        r17 = r10 + (r3 / 2);
        int r152 = AndroidUtilities.dp(7.0f);
    L31:
        r182 = (r22 - r152) + r55;
        goto L34
    L32:
        r142 = AndroidUtilities.dp(5.0f * r122);
        r123 = AndroidUtilities.dp(r122 * 18.0f);
        r55 = AndroidUtilities.dp(this.progress[r43] * 15.0f);
        r16 = (r11 + AndroidUtilities.dp(42.0f)) - r55;
        r17 = (r10 + (r3 / 2)) - AndroidUtilities.dp(7.0f);
        r152 = AndroidUtilities.dp(42.0f);
    L33:
        r142 = AndroidUtilities.dp(2.5f * r122);
        r123 = AndroidUtilities.dp(r122 * 6.5f);
        r55 = AndroidUtilities.dp(this.progress[r43] * 6.0f);
        r16 = (r11 + AndroidUtilities.dp(3.0f)) - r55;
        r17 = (r10 + (r3 / 2)) - AndroidUtilities.dp(2.0f);
        r152 = AndroidUtilities.dp(3.0f);
    L39:
        r43 = r43 + 1;
        r5 = 3;
        r6 = 5;
        r7 = 1;
        goto L15
    L40:
        update();
        return;
    L8:
        if (r4 != 2) goto L10;
        r42 = AndroidUtilities.dp(32.0f);
        goto L14
    L10:
        if (r4 != 1) goto L12;
        r42 = AndroidUtilities.dp(30.0f);
        goto L14
    L12:
        r42 = AndroidUtilities.dp(120.0f);
    L13:
        r42 = AndroidUtilities.dp(24.0f);
        goto L14
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        int r1 = this.currentType;
        if (r1 == 4) goto L22;
        if (r1 == 5) goto L22;
        if (r1 != 3) goto L12;
        return AndroidUtilities.dp(100.0f);
    L12:
        if (r1 != 2) goto L16;
        return AndroidUtilities.dp(74.0f);
    L16:
        if (r1 != 1) goto L20;
        return AndroidUtilities.dp(40.0f);
    L20:
        return AndroidUtilities.dp(180.0f);
    L22:
        return AndroidUtilities.dp(42.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        int r1 = this.currentType;
        if (r1 == 4) goto L22;
        if (r1 == 5) goto L22;
        if (r1 != 3) goto L12;
        return AndroidUtilities.dp(100.0f);
    L12:
        if (r1 != 2) goto L16;
        return AndroidUtilities.dp(74.0f);
    L16:
        if (r1 != 1) goto L20;
        return AndroidUtilities.dp(40.0f);
    L20:
        return AndroidUtilities.dp(120.0f);
    L22:
        return AndroidUtilities.dp(42.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.drawable.setColorFilter(r2);
        this.drawableLeft.setColorFilter(r2);
        this.drawableRight.setColorFilter(r2);
    }
}
