package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RLottieDrawable;

/* loaded from: classes7.dex */
public class TrashView extends View {
    private final ButtonBounce bounce;
    private final Paint circlePaint;
    private boolean dragged;
    private final AnimatedFloat draggedT;
    private final RLottieDrawable drawable;
    private final Paint greyPaint;
    private final AnimatedTextView.AnimatedTextDrawable textDrawable;

    public TrashView(Context r18) {
        super(r18);
        Paint r7 = new Paint(1);
        this.circlePaint = r7;
        Paint r9 = new Paint(1);
        this.greyPaint = r9;
        this.bounce = new ButtonBounce(this);
        CubicBezierInterpolator r16 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.draggedT = new AnimatedFloat(this, 0, 240, r16);
        r7.setColor(-1);
        r7.setStyle(Paint.Style.STROKE);
        r7.setStrokeWidth(AndroidUtilities.dpf2(2.66f));
        r7.setShadowLayer(AndroidUtilities.dpf2(3.0f), 0.0f, AndroidUtilities.dp(1.66f), 805306368);
        r9.setColor(855638016);
        RLottieDrawable r10 = new RLottieDrawable(R.raw.group_pip_delete_icon, AndroidUtilities.dp(48.0f), AndroidUtilities.dp(48.0f), true, null);
        this.drawable = r10;
        r10.setMasterParent(this);
        r10.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.MULTIPLY));
        r10.setPlayInDirectionOfCustomEndFrame(true);
        r10.setCustomEndFrame(0);
        r10.setAllowDecodeSingleFrame(true);
        r10.start();
        AnimatedTextView.AnimatedTextDrawable r102 = new AnimatedTextView.AnimatedTextDrawable(true, true, false);
        this.textDrawable = r102;
        r102.setAnimationProperties(0.3f, 0, 250, r16);
        r102.setOverrideFullWidth(AndroidUtilities.displaySize.x);
        r102.setTextSize(AndroidUtilities.dp(14.0f));
        r102.setTextColor(-1);
        r102.setShadowLayer(AndroidUtilities.dpf2(1.33f), 0.0f, AndroidUtilities.dp(1.0f), TLObject.FLAG_30);
        r102.setText(LocaleController.getString(R.string.TrashHintDrag));
        r102.setGravity(17);
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r8) {
        float r0 = AndroidUtilities.dp(30.0f);
        float r1 = getWidth() / 2.0f;
        float r3 = getHeight() / 2.0f;
        float r4 = (AndroidUtilities.dp(3.0f) * this.draggedT.set(this.dragged)) + r0;
        r8.drawCircle(r1, r3, r4, this.greyPaint);
        r8.drawCircle(r1, r3, r4, this.circlePaint);
        float r42 = AndroidUtilities.dp(48.0f) / 2.0f;
        this.drawable.setBounds((int) (r1 - r42), (int) (r3 - r42), (int) (r1 + r42), (int) (r42 + r3));
        this.drawable.draw(r8);
        this.textDrawable.setBounds(0, (int) ((r3 + r0) + AndroidUtilities.dp(7.0f)), getWidth(), getHeight());
        this.textDrawable.draw(r8);
    }

    public void onDragInfo(boolean r3, boolean r4) {
        this.bounce.setPressed(r3);
        AnimatedTextView.AnimatedTextDrawable r0 = this.textDrawable;
        if (r3 == true) goto L8;
        if (r4 == true) goto L8;
        int r1 = R.string.TrashHintDrag;
    L9:
        r0.setText(LocaleController.getString(r1));
        int r02 = 0;
        if (r3 == false) goto L13;
        if (r4 == true) goto L13;
        boolean r32 = true;
    L14:
        this.dragged = r32;
        RLottieDrawable r12 = this.drawable;
        if (r32 == true) goto L17;
        if (r4 == false) goto L22;
        r02 = 66;
    L22:
        r12.setCustomEndFrame(r02);
        this.drawable.start();
    L23:
        invalidate();
        return;
    L17:
        if (r12.getCurrentFrame() <= 34) goto L19;
        this.drawable.setCurrentFrame(0, false);
    L19:
        this.drawable.setCustomEndFrame(33);
        this.drawable.start();
    L13:
        r32 = false;
    L8:
        r1 = R.string.TrashHintRelease;
        goto L9
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        setMeasuredDimension(r1, AndroidUtilities.dp(120.0f));
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.textDrawable) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
