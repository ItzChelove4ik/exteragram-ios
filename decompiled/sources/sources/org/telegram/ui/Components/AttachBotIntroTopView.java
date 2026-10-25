package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class AttachBotIntroTopView extends View {
    private Drawable attachDrawable;
    private Paint backgroundPaint;
    private ImageReceiver imageReceiver;
    private Paint paint;

    public static /* synthetic */ void $r8$lambda$UdQiWO_Y9nvOaf5d_vsFewsS3a8(AttachBotIntroTopView r0, ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        r0.lambda$new$1(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$YLnSTO_hu9S4bRjoNy2qmRsraLw(AttachBotIntroTopView r0, ValueAnimator r1) {
        r0.lambda$new$0(r1);
    }

    public AttachBotIntroTopView(Context r3) {
        super(r3);
        this.paint = new Paint(1);
        this.backgroundPaint = new Paint(1);
        ImageReceiver r0 = new ImageReceiver(this);
        this.imageReceiver = r0;
        r0.setAlpha(0.0f);
        this.imageReceiver.setDelegate(new AttachBotIntroTopView$$ExternalSyntheticLambda0(this));
        this.attachDrawable = ContextCompat.getDrawable(r3, R.drawable.input_attach).mutate().getConstantState().newDrawable();
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(AndroidUtilities.dp(3.0f));
        this.paint.setStrokeCap(Paint.Cap.ROUND);
    }

    private /* synthetic */ void lambda$new$0(ValueAnimator r2) {
        this.imageReceiver.setAlpha(((Float) r2.getAnimatedValue()).floatValue());
        invalidate();
    }

    private /* synthetic */ void lambda$new$1(ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(150);
        r12.addUpdateListener(new AttachBotIntroTopView$$ExternalSyntheticLambda1(this));
        r12.start();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.imageReceiver.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.imageReceiver.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r12) {
        super.onDraw(r12);
        RectF r2 = AndroidUtilities.rectTmp;
        r2.set(0.0f, 0.0f, getWidth(), getHeight() + AndroidUtilities.dp(10.0f));
        r12.drawRoundRect(r2, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.backgroundPaint);
        this.imageReceiver.setImageCoords((getWidth() / 2.0f) - AndroidUtilities.dp(66.0f), (getHeight() / 2.0f) - (AndroidUtilities.dp(42.0f) / 2.0f), AndroidUtilities.dp(42.0f), AndroidUtilities.dp(42.0f));
        this.imageReceiver.draw(r12);
        r12.drawLine((getWidth() / 2.0f) - AndroidUtilities.dp(8.0f), getHeight() / 2.0f, (getWidth() / 2.0f) + AndroidUtilities.dp(8.0f), getHeight() / 2.0f, this.paint);
        r12.drawLine(getWidth() / 2.0f, (getHeight() / 2.0f) - AndroidUtilities.dp(8.0f), getWidth() / 2.0f, AndroidUtilities.dp(8.0f) + (getHeight() / 2.0f), this.paint);
        this.attachDrawable.setBounds((getWidth() / 2) + AndroidUtilities.dp(24.0f), (getHeight() / 2) - (AndroidUtilities.dp(42.0f) / 2), (getWidth() / 2) + AndroidUtilities.dp(66.0f), (getHeight() / 2) + (AndroidUtilities.dp(42.0f) / 2));
        this.attachDrawable.draw(r12);
    }

    public void setAttachBot(TLRPC.TL_attachMenuBot r9) {
        TLRPC.TL_attachMenuBotIcon r0 = MediaDataController.getStaticAttachMenuBotIcon(r9);
        if (r0 == null) goto L6;
        this.imageReceiver.setImage(ImageLocation.getForDocument(r0.icon), "42_42", DocumentObject.getSvgThumb(r0.icon, Theme.key_dialogTextGray2, 1.0f), "svg", r9, 0);
        return;
    }

    @Override // android.view.View
    public void setBackgroundColor(int r1) {
        this.backgroundPaint.setColor(r1);
    }

    public void setColor(int r3) {
        Drawable r0 = this.attachDrawable;
        PorterDuff.Mode r1 = PorterDuff.Mode.SRC_IN;
        r0.setColorFilter(r3, r1);
        this.paint.setColor(r3);
        this.imageReceiver.setColorFilter(new PorterDuffColorFilter(r3, r1));
    }
}
