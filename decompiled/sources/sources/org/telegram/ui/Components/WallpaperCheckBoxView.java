package org.telegram.ui.Components;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.Property;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimationProperties;

/* loaded from: classes7.dex */
public class WallpaperCheckBoxView extends View {
    public final Property<WallpaperCheckBoxView, Float> PROGRESS_PROPERTY;
    private Paint backgroundPaint;
    private ObjectAnimator checkAnimator;
    private Paint checkPaint;
    private int[] colors;
    private String currentText;
    private int currentTextSize;
    private float dimAmount;
    private final Paint dimPaint;
    private Bitmap drawBitmap;
    private Canvas drawCanvas;
    private Paint eraserPaint;
    private boolean isChecked;
    private int maxTextSize;
    private View parentView;
    private float progress;
    private RectF rect;
    Theme.ResourcesProvider resourcesProvider;
    private TextPaint textPaint;

    /* renamed from: org.telegram.ui.Components.WallpaperCheckBoxView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimationProperties.FloatProperty<WallpaperCheckBoxView> {
        final /* synthetic */ WallpaperCheckBoxView this$0;

        public AnonymousClass1(WallpaperCheckBoxView r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        public Float get(WallpaperCheckBoxView r1) {
            return Float.valueOf(WallpaperCheckBoxView.m13581$$Nest$fgetprogress(this.this$0));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((WallpaperCheckBoxView) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(WallpaperCheckBoxView r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(WallpaperCheckBoxView r1, float r2) {
            WallpaperCheckBoxView.m13582$$Nest$fputprogress(this.this$0, r2);
            this.this$0.invalidate();
        }
    }

    /* renamed from: -$$Nest$fgetprogress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13581$$Nest$fgetprogress(WallpaperCheckBoxView r0) {
        return r0.progress;
    }

    /* renamed from: -$$Nest$fputprogress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13582$$Nest$fputprogress(WallpaperCheckBoxView r0, float r1) {
        r0.progress = r1;
    }

    public WallpaperCheckBoxView(Context r2, boolean r3, View r4, Theme.ResourcesProvider r5) {
        super(r2);
        this.colors = new int[4];
        this.PROGRESS_PROPERTY = new AnonymousClass1(this, "progress");
        this.dimPaint = new Paint(1);
        this.resourcesProvider = r5;
        this.rect = new RectF();
        if (r3 == false) goto L5;
        this.drawBitmap = Bitmap.createBitmap(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), Bitmap.Config.ARGB_4444);
        this.drawCanvas = new Canvas(this.drawBitmap);
    L5:
        this.parentView = r4;
        TextPaint r22 = new TextPaint(1);
        this.textPaint = r22;
        r22.setTextSize(AndroidUtilities.dp(14.0f));
        this.textPaint.setTypeface(AndroidUtilities.bold());
        Paint r23 = new Paint(1);
        this.checkPaint = r23;
        r23.setStyle(Paint.Style.STROKE);
        this.checkPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.checkPaint.setColor(0);
        this.checkPaint.setStrokeCap(Paint.Cap.ROUND);
        Paint r24 = this.checkPaint;
        PorterDuff.Mode r52 = PorterDuff.Mode.CLEAR;
        r24.setXfermode(new PorterDuffXfermode(r52));
        Paint r25 = new Paint(1);
        this.eraserPaint = r25;
        r25.setColor(0);
        this.eraserPaint.setXfermode(new PorterDuffXfermode(r52));
        this.backgroundPaint = new Paint(1);
    }

    private void animateToCheckedState(boolean r4) {
        Property<WallpaperCheckBoxView, Float> r0 = this.PROGRESS_PROPERTY;
        if (r4 == false) goto L5;
        float r42 = 1.0f;
    L6:
        ObjectAnimator r43 = ObjectAnimator.ofFloat(this, r0, new float[]{r42});
        this.checkAnimator = r43;
        r43.setDuration(300);
        this.checkAnimator.start();
        return;
    L5:
        r42 = 0.0f;
        goto L6
    }

    private void cancelCheckAnimator() {
        ObjectAnimator r0 = this.checkAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
        return;
    }

    private Paint getThemedPaint(String r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Paint r02 = r0.getPaint(r1);
    L6:
        if (r02 == null) goto L9;
        return r02;
    L9:
        return Theme.getThemePaint(r1);
    L5:
        r02 = null;
        goto L6
    }

    private void setProgress(float r2) {
        if (this.progress != r2) goto L5;
        return;
    L5:
        this.progress = r2;
        invalidate();
    }

    public TextPaint getTextPaint() {
        return this.textPaint;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    @Override // android.view.View
    public void onDraw(Canvas r19) {
        Canvas r1 = r19;
        this.rect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        Theme.applyServiceShaderMatrixForView(this, this.parentView, this.resourcesProvider);
        r1.drawRoundRect(this.rect, getMeasuredHeight() / 2, getMeasuredHeight() / 2, getThemedPaint("paintChatActionBackground"));
        Theme.ResourcesProvider r2 = this.resourcesProvider;
        if (r2 != null) goto L5;
        boolean r22 = Theme.hasGradientService();
    L6:
        if (r22 == false) goto L9;
        r1.drawRoundRect(this.rect, getMeasuredHeight() / 2, getMeasuredHeight() / 2, getThemedPaint("paintChatActionBackgroundDarken"));
    L9:
        if (this.dimAmount <= 0.0f) goto L11;
        r1.drawRoundRect(this.rect, getMeasuredHeight() / 2, getMeasuredHeight() / 2, this.dimPaint);
    L11:
        TextPaint r23 = this.textPaint;
        int r3 = Theme.key_chat_serviceText;
        r23.setColor(Theme.getColor(r3, this.resourcesProvider));
        int r24 = ((getMeasuredWidth() - this.currentTextSize) - AndroidUtilities.dp(28.0f)) / 2;
        r1.drawText(this.currentText, AndroidUtilities.dp(28.0f) + r24, AndroidUtilities.dp(21.0f), this.textPaint);
        r1.save();
        r1.translate(r24, AndroidUtilities.dp(7.0f));
        int r8 = 0;
        if (this.drawBitmap == null) goto L24;
        float r25 = this.progress;
        if (r25 > 0.5f) goto L16;
        float r26 = r25 / 0.5f;
        float r10 = r26;
    L17:
        float r12 = AndroidUtilities.dp(1.0f) * r26;
        this.rect.set(r12, r12, AndroidUtilities.dp(18.0f) - r12, AndroidUtilities.dp(18.0f) - r12);
        this.drawBitmap.eraseColor(0);
        this.backgroundPaint.setColor(Theme.getColor(r3, this.resourcesProvider));
        Canvas r32 = this.drawCanvas;
        RectF r6 = this.rect;
        r32.drawRoundRect(r6, r6.width() / 2.0f, this.rect.height() / 2.0f, this.backgroundPaint);
        if (r10 == 1.0f) goto L21;
        float r33 = Math.min(AndroidUtilities.dp(7.0f), (AndroidUtilities.dp(7.0f) * r10) + r12);
        this.rect.set(AndroidUtilities.dp(2.0f) + r33, AndroidUtilities.dp(2.0f) + r33, AndroidUtilities.dp(16.0f) - r33, AndroidUtilities.dp(16.0f) - r33);
        Canvas r34 = this.drawCanvas;
        RectF r4 = this.rect;
        r34.drawRoundRect(r4, r4.width() / 2.0f, this.rect.height() / 2.0f, this.eraserPaint);
    L21:
        if (this.progress <= 0.5f) goto L23;
        float r11 = 1.0f - r26;
        this.drawCanvas.drawLine(AndroidUtilities.dp(7.3f), AndroidUtilities.dp(13.0f), (int) (AndroidUtilities.dp(7.3f) - (AndroidUtilities.dp(2.5f) * r11)), (int) (AndroidUtilities.dp(13.0f) - (AndroidUtilities.dp(2.5f) * r11)), this.checkPaint);
        this.drawCanvas.drawLine(AndroidUtilities.dp(7.3f), AndroidUtilities.dp(13.0f), (int) (AndroidUtilities.dp(7.3f) + (AndroidUtilities.dp(6.0f) * r11)), (int) (AndroidUtilities.dp(13.0f) - (AndroidUtilities.dp(6.0f) * r11)), this.checkPaint);
    L23:
        r1.drawBitmap(this.drawBitmap, 0.0f, 0.0f, null);
    L39:
        r1.restore();
        return;
    L16:
        r26 = 2.0f - (r25 / 0.5f);
        r10 = 1.0f;
        goto L17
    L24:
        this.rect.set(0.0f, 0.0f, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f));
        int[] r27 = this.colors;
        if (r27[3] == 0) goto L31;
    L27:
        if (r8 >= 4) goto L29;
        this.backgroundPaint.setColor(this.colors[r8]);
        r1.drawArc(this.rect, (r8 * 90) - 90, 90.0f, true, this.backgroundPaint);
        r8 = r8 + 1;
        r1 = r19;
    L29:
        r1 = r19;
        goto L39
    L31:
        if (r27[2] == 0) goto L35;
    L32:
        if (r8 >= 3) goto L29;
        this.backgroundPaint.setColor(this.colors[r8]);
        r19.drawArc(this.rect, (r8 * 120) - 90, 120.0f, true, this.backgroundPaint);
        r8 = r8 + 1;
        goto L32
    L35:
        if (r27[1] == 0) goto L38;
    L36:
        if (r8 >= 2) goto L29;
        this.backgroundPaint.setColor(this.colors[r8]);
        r19.drawArc(this.rect, (r8 * 180) - 90, 180.0f, true, this.backgroundPaint);
        r8 = r8 + 1;
        goto L36
    L38:
        r1 = r19;
        this.backgroundPaint.setColor(r27[0]);
        RectF r28 = this.rect;
        r1.drawRoundRect(r28, r28.width() / 2.0f, this.rect.height() / 2.0f, this.backgroundPaint);
        goto L39
    L5:
        r22 = r2.hasGradientService();
        goto L6
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.maxTextSize + AndroidUtilities.dp(56.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30));
    }

    public void setChecked(boolean r2, boolean r3) {
        if (r2 != this.isChecked) goto L5;
        return;
    L5:
        this.isChecked = r2;
        if (r3 == false) goto L9;
        animateToCheckedState(r2);
        return;
    L9:
        cancelCheckAnimator();
        if (r2 == false) goto L12;
        float r22 = 1.0f;
    L13:
        this.progress = r22;
        invalidate();
        return;
    L12:
        r22 = 0.0f;
        goto L13
    }

    public void setColor(int r2, int r3) {
        if (this.colors != null) goto L5;
        this.colors = new int[4];
    L5:
        this.colors[r2] = r3;
        invalidate();
    }

    public void setDimAmount(float r3) {
        this.dimAmount = r3;
        this.dimPaint.setColor(ColorUtils.setAlphaComponent(-16777216, (int) (r3 * 255.0f)));
        invalidate();
    }

    public void setText(String r1, int r2, int r3) {
        this.currentText = r1;
        this.currentTextSize = r2;
        this.maxTextSize = r3;
    }
}
