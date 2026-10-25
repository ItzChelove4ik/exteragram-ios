package org.telegram.ui.Components.Crop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.exteragram.messenger.utils.system.VibratorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes7.dex */
public class CropRotationWheel extends FrameLayout {
    private ImageView aspectRatioButton;
    private Paint bluePaint;
    private String degreesText;
    private TextPaint degreesTextPaint;
    private ImageView mirrorButton;
    private float prevX;
    protected float rotation;
    private ImageView rotation90Button;
    private RotationWheelListener rotationListener;
    private RectF tempRect;
    private Paint whitePaint;

    public interface RotationWheelListener {
        void aspectRatioPressed();

        boolean mirror();

        void onChange(float r1);

        void onEnd(float r1);

        void onStart();

        boolean rotate90Pressed();
    }

    public static /* synthetic */ void $r8$lambda$c4e9Lw0wu1cDvsDMGaIvnmqY1Xk(CropRotationWheel r0, View r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$gfKooQ7vfBTqOFXSn_8agc9jD8I(CropRotationWheel r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$m-qvQ2qi3wDsa2SxDIYIcIOc_wk, reason: not valid java name */
    public static /* synthetic */ void m10069$r8$lambda$mqvQ2qi3wDsa2SxDIYIcIOc_wk(CropRotationWheel r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$yqpUFMv9XRltdwpxU4KettyMz5Y(CropRotationWheel r0, View r1) {
        return r0.lambda$new$1(r1);
    }

    public CropRotationWheel(Context r11) {
        super(r11);
        this.tempRect = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        Paint r0 = new Paint();
        this.whitePaint = r0;
        Paint.Style r2 = Paint.Style.FILL;
        r0.setStyle(r2);
        this.whitePaint.setColor(-1);
        this.whitePaint.setAlpha(255);
        this.whitePaint.setAntiAlias(true);
        Paint r02 = new Paint();
        this.bluePaint = r02;
        r02.setStyle(r2);
        this.bluePaint.setColor(-11420173);
        this.bluePaint.setAlpha(255);
        this.bluePaint.setAntiAlias(true);
        ImageView r03 = new ImageView(r11);
        this.mirrorButton = r03;
        r03.setImageResource(R.drawable.msg_photo_flip);
        this.mirrorButton.setBackgroundDrawable(Theme.createSelectorDrawable(1090519039));
        ImageView r04 = this.mirrorButton;
        ImageView.ScaleType r4 = ImageView.ScaleType.CENTER;
        r04.setScaleType(r4);
        this.mirrorButton.setOnClickListener(new CropRotationWheel$$ExternalSyntheticLambda0(this));
        this.mirrorButton.setOnLongClickListener(new CropRotationWheel$$ExternalSyntheticLambda1(this));
        this.mirrorButton.setContentDescription(LocaleController.getString(R.string.AccDescrMirror));
        addView(this.mirrorButton, LayoutHelper.createFrame(70, 64, 19));
        ImageView r05 = new ImageView(r11);
        this.aspectRatioButton = r05;
        r05.setImageResource(R.drawable.msg_photo_cropfix);
        this.aspectRatioButton.setBackgroundDrawable(Theme.createSelectorDrawable(1090519039));
        this.aspectRatioButton.setScaleType(r4);
        this.aspectRatioButton.setOnClickListener(new CropRotationWheel$$ExternalSyntheticLambda2(this));
        this.aspectRatioButton.setVisibility(8);
        this.aspectRatioButton.setContentDescription(LocaleController.getString(R.string.AccDescrAspectRatio));
        addView(this.aspectRatioButton, LayoutHelper.createFrame(70, 64, 19));
        ImageView r06 = new ImageView(r11);
        this.rotation90Button = r06;
        r06.setImageResource(R.drawable.msg_photo_rotate);
        this.rotation90Button.setBackgroundDrawable(Theme.createSelectorDrawable(1090519039));
        this.rotation90Button.setScaleType(r4);
        this.rotation90Button.setOnClickListener(new CropRotationWheel$$ExternalSyntheticLambda3(this));
        this.rotation90Button.setContentDescription(LocaleController.getString(R.string.AccDescrRotate));
        addView(this.rotation90Button, LayoutHelper.createFrame(70, 64, 21));
        TextPaint r112 = new TextPaint(1);
        this.degreesTextPaint = r112;
        r112.setColor(-1);
        this.degreesTextPaint.setTextSize(AndroidUtilities.dp(14.0f));
        setWillNotDraw(false);
        setRotation(0.0f, false);
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        RotationWheelListener r12 = this.rotationListener;
        if (r12 == null) goto L6;
        setMirrored(r12.mirror());
        return;
    }

    private /* synthetic */ boolean lambda$new$1(View r1) {
        this.aspectRatioButton.callOnClick();
        return true;
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        RotationWheelListener r0 = this.rotationListener;
        if (r0 == null) goto L6;
        r0.aspectRatioPressed();
        return;
    }

    private /* synthetic */ void lambda$new$3(View r1) {
        RotationWheelListener r12 = this.rotationListener;
        if (r12 == null) goto L6;
        setRotated(r12.rotate90Pressed());
        return;
    }

    public void drawLine(Canvas r7, int r8, float r9, int r10, int r11, boolean r12, Paint r13) {
        int r0 = (int) ((r10 / 2.0f) - AndroidUtilities.dp(70.0f));
        int r102 = (r10 / 2) + ((int) (r0 * Math.cos(Math.toRadians(90.0f - ((r8 * 5) + r9)))));
        float r82 = Math.abs(r8) / r0;
        int r83 = Math.min(255, Math.max(0, (int) ((1.0f - (r82 * r82)) * 255.0f)));
        if (r12 == false) goto L5;
        r13 = this.bluePaint;
    L5:
        Paint r5 = r13;
        r5.setAlpha(r83);
        if (r12 == false) goto L8;
        int r6 = 4;
    L9:
        if (r12 == false) goto L12;
        float r84 = 16.0f;
    L11:
        int r85 = AndroidUtilities.dp(r84);
        int r62 = r6 / 2;
        r7.drawRect(r102 - r62, (r11 - r85) / 2, r102 + r62, (r11 + r85) / 2, r5);
        return;
    L12:
        r84 = 12.0f;
        goto L11
    L8:
        r6 = 2;
        goto L9
    }

    @Override // android.view.View
    public float getRotation() {
        return this.rotation;
    }

    @Override // android.view.View
    public void onDraw(Canvas r13) {
        super.onDraw(r13);
        int r4 = getWidth();
        int r5 = getHeight();
        float r3 = ((-this.rotation) * 2.0f) % 5.0f;
        int r9 = (int) Math.floor(r0 / 5.0f);
        int r2 = 0;
    L4:
        if (r2 >= 16) goto L35;
        Paint r0 = this.whitePaint;
        if (r2 < r9) goto L12;
        if (r2 == 0) goto L9;
    L11:
        Paint r7 = r0;
        if (r2 == r9) goto L21;
        if (r2 == 0) goto L17;
    L19:
        boolean r6 = false;
    L20:
        CropRotationWheel r02 = this;
        Canvas r1 = r13;
        r02.drawLine(r1, r2, r3, r4, r5, r6, r7);
        int r12 = r2;
        if (r12 == 0) goto L34;
        int r22 = -r12;
        if (r22 <= r9) goto L28;
        Paint r132 = r02.bluePaint;
    L27:
        Paint r72 = r132;
        if (r22 != (r9 + 1)) goto L32;
        boolean r62 = true;
    L33:
        r02.drawLine(r1, r22, r3, r4, r5, r62, r72);
        goto L34
    L32:
        r62 = false;
        goto L33
    L28:
        r132 = r02.whitePaint;
    L34:
        r2 = r12 + 1;
        CropRotationWheel this = r02;
        r13 = r1;
        goto L4
    L17:
        if (r9 != (-1)) goto L19;
    L21:
        r6 = true;
        goto L20
    L9:
        if (r3 >= 0.0f) goto L11;
    L12:
        r0 = this.bluePaint;
        goto L11
    L35:
        CropRotationWheel r03 = this;
        Canvas r14 = r13;
        r03.bluePaint.setAlpha(255);
        r03.tempRect.left = (r4 - AndroidUtilities.dp(2.5f)) / 2;
        r03.tempRect.top = (r5 - AndroidUtilities.dp(22.0f)) / 2;
        r03.tempRect.right = (AndroidUtilities.dp(2.5f) + r4) / 2;
        r03.tempRect.bottom = (r5 + AndroidUtilities.dp(22.0f)) / 2;
        r14.drawRoundRect(r03.tempRect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), r03.bluePaint);
        r14.drawText(r03.degreesText, (r4 - r03.degreesTextPaint.measureText(r03.degreesText)) / 2.0f, AndroidUtilities.dp(14.0f), r03.degreesTextPaint);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(r2), AndroidUtilities.dp(400.0f)), TLObject.FLAG_30), r3);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r9) {
        int r0 = r9.getActionMasked();
        float r92 = r9.getX();
        if (r0 != 0) goto L7;
        this.prevX = r92;
        RotationWheelListener r8 = this.rotationListener;
        if (r8 == null) goto L41;
        r8.onStart();
    L41:
        return true;
    L7:
        if (r0 != 1) goto L9;
    L37:
        RotationWheelListener r93 = this.rotationListener;
        if (r93 == null) goto L40;
        r93.onEnd(this.rotation);
    L40:
        AndroidUtilities.makeAccessibilityAnnouncement(String.format("%.1f°", new Object[]{Float.valueOf(this.rotation)}));
        goto L41
    L9:
        if (r0 == 3) goto L37;
        if (r0 != 2) goto L41;
        float r02 = this.prevX - r92;
        float r3 = Math.max(-45.0f, Math.min(45.0f, this.rotation + ((float) (((r02 / AndroidUtilities.density) / 3.141592653589793d) / 1.649999976158142d))));
        if (Build.VERSION.SDK_INT < 27) goto L29;
        if (Math.abs(r3 - 45.0f) >= 0.001f) goto L21;
        if (Math.abs(this.rotation - 45.0f) < 0.001f) goto L21;
    L24:
        performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Exception -> L42
    L21:
        if (Math.abs(r3 - (-45.0f)) >= 0.001f) goto L26;
        if (Math.abs(this.rotation - (-45.0f)) >= 0.001f) goto L24;
    L26:
        if (Math.floor(this.rotation / 2.5f) == Math.floor(r3 / 2.5f)) goto L29;
        AndroidUtilities.vibrateCursor(this);     // Catch: Exception -> L42
    L29:
        if (Math.abs(r3 - this.rotation) <= 0.001d) goto L41;
        if (Math.abs(r3) >= 0.05d) goto L33;
        r3 = 0.0f;
    L33:
        setRotation(r3, false);
        RotationWheelListener r03 = this.rotationListener;
        if (r03 == null) goto L36;
        r03.onChange(this.rotation);
    L36:
        this.prevX = r92;
        goto L41
    }

    public void reset(boolean r3) {
        setRotation(0.0f, false);
        if (r3 == false) goto L5;
        setMirrored(false);
    L5:
        setRotated(false);
    }

    public void setAspectLock(boolean r3) {
        ImageView r2 = this.aspectRatioButton;
        if (r3 == false) goto L5;
        PorterDuffColorFilter r32 = new PorterDuffColorFilter(-11420173, PorterDuff.Mode.MULTIPLY);
    L6:
        r2.setColorFilter(r32);
        return;
    L5:
        r32 = null;
        goto L6
    }

    public void setFreeform(boolean r1) {
    }

    public void setListener(RotationWheelListener r1) {
        this.rotationListener = r1;
    }

    public void setMirrored(boolean r3) {
        ImageView r2 = this.mirrorButton;
        if (r3 == false) goto L5;
        PorterDuffColorFilter r32 = new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_editMediaButton), PorterDuff.Mode.MULTIPLY);
    L6:
        r2.setColorFilter(r32);
        return;
    L5:
        r32 = null;
        goto L6
    }

    public void setRotated(boolean r3) {
        ImageView r2 = this.rotation90Button;
        if (r3 == false) goto L5;
        PorterDuffColorFilter r32 = new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_editMediaButton), PorterDuff.Mode.MULTIPLY);
    L6:
        r2.setColorFilter(r32);
        return;
    L5:
        r32 = null;
        goto L6
    }

    public void setRotation(float r5, boolean r6) {
        this.rotation = r5;
        if (Math.abs(r5) >= 0.099d) goto L5;
        r5 = Math.abs(r5);
    L5:
        this.degreesText = String.format("%.1fº", new Object[]{Float.valueOf(r5)});
        invalidate();
    }
}
