package org.telegram.ui.Components.Premium;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.voip.CellFlickerDrawable;

/* loaded from: classes7.dex */
public class PremiumLockIconView extends ImageView {
    public static int TYPE_GIFT_LOCK = 3;
    public static int TYPE_GIFT_PIN = 4;
    public static int TYPE_REACTIONS = 0;
    public static int TYPE_REACTIONS_LOCK = 2;
    public static int TYPE_STICKERS_PREMIUM_LOCKED = 1;
    boolean attachedToWindow;
    private Integer blendColor;
    CellFlickerDrawable cellFlickerDrawable;
    int color1;
    int color2;
    private float[] colorFloat;
    boolean colorRetrieved;
    public int currentColor;
    AnimatedEmojiDrawable emojiDrawable;
    private float iconScale;
    ImageReceiver imageReceiver;
    public boolean isEnter;
    private boolean locked;
    Paint oldShaderPaint;
    public Paint paint;
    Path path;
    private Theme.ResourcesProvider resourcesProvider;
    Shader shader;
    float shaderCrossfadeProgress;
    StarParticlesView.Drawable starParticles;
    private final int type;
    boolean waitingImage;
    boolean wasDrawn;

    static {
    }

    public PremiumLockIconView(Context r2, int r3) {
        this(r2, r3, null);
    }

    public PremiumLockIconView(Context r4, int r5, Theme.ResourcesProvider r6) {
        super(r4);
        this.colorFloat = new float[3];
        this.iconScale = 1.0f;
        this.colorRetrieved = false;
        this.currentColor = -1;
        this.shader = null;
        this.path = new Path();
        this.paint = new Paint(1);
        this.shaderCrossfadeProgress = 1.0f;
        this.type = r5;
        this.resourcesProvider = r6;
        if (r5 != TYPE_REACTIONS) goto L5;
        int r42 = R.drawable.msg_premium_lock2;
    L6:
        setImageResource(r42);
        if (r5 != TYPE_REACTIONS) goto L11;
        StarParticlesView.Drawable r43 = new StarParticlesView.Drawable(5);
        this.starParticles = r43;
        r43.updateColors();
        StarParticlesView.Drawable r3 = this.starParticles;
        r3.roundEffect = false;
        r3.size2 = 4;
        r3.size3 = 4;
        r3.size1 = 2;
        r3.speedScale = 0.1f;
        r3.init();
        return;
    L11:
        if (r5 != TYPE_REACTIONS_LOCK) goto L15;
        this.iconScale = 0.8f;
        this.paint.setColor(Theme.getColor(Theme.key_windowBackgroundGray));
        return;
    L15:
        if (r5 != TYPE_GIFT_LOCK) goto L19;
        setScaleType(ImageView.ScaleType.CENTER);
        setImageResource(R.drawable.msg_archive_hide);
        return;
    L19:
        if (r5 != TYPE_GIFT_PIN) goto L22;
        setScaleType(ImageView.ScaleType.CENTER);
        setImageResource(R.drawable.msg_limit_pin);
        return;
    L22:
        return;
    L5:
        r42 = R.drawable.msg_mini_premiumlock;
        goto L6
    }

    private void updateGradient() {
        if (this.attachedToWindow == true) goto L6;
        return;
    L6:
        if (getMeasuredHeight() != 0) goto L8;
        return;
    L8:
        if (getMeasuredWidth() == 0) goto L28;
        Color.colorToHSV(this.currentColor, this.colorFloat);
        float[] r0 = this.colorFloat;
        float r2 = r0[1];
        if (this.locked == false) goto L12;
        float r3 = 2.0f;
    L13:
        r0[1] = r2 * r3;
        if (r0[2] <= 0.7f) goto L16;
        r0[2] = 0.7f;
    L16:
        int r02 = Color.HSVToColor(r0);
        int r22 = Theme.key_windowBackgroundWhite;
        int r32 = ColorUtils.blendARGB(r02, Theme.getColor(r22, this.resourcesProvider), 0.5f);
        int r03 = ColorUtils.blendARGB(r02, Theme.getColor(r22, this.resourcesProvider), 0.4f);
        if (this.shader == null) goto L23;
        if (this.color1 != r03) goto L23;
        if (this.color2 != r32) goto L23;
        return;
    L23:
        if (this.wasDrawn == false) goto L25;
        Paint r23 = this.paint;
        this.oldShaderPaint = r23;
        r23.setAlpha(255);
        this.shaderCrossfadeProgress = 0.0f;
    L25:
        this.paint = new Paint(1);
        float r6 = getMeasuredHeight();
        this.color1 = r03;
        this.color2 = r32;
        LinearGradient r4 = new LinearGradient(0.0f, r6, 0.0f, 0.0f, new int[]{r03, r32}, null, Shader.TileMode.CLAMP);
        this.shader = r4;
        this.paint.setShader(r4);
        invalidate();
        return;
    L12:
        r3 = 1.0f;
        goto L13
    }

    public boolean done() {
        return this.colorRetrieved;
    }

    public ImageReceiver getImageReceiver() {
        return this.imageReceiver;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
        if (this.type == TYPE_REACTIONS) goto L6;
        updateGradient();
        return;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
        Paint r1 = this.paint;
        if (r1 != null) goto L5;
    L7:
        this.shader = null;
        this.wasDrawn = false;
        return;
    L5:
        if (this.type == TYPE_REACTIONS_LOCK) goto L7;
        r1.setShader(null);
        this.paint = null;
        goto L7
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas r13) {
        boolean r1 = false;
        if (this.waitingImage == false) goto L16;
        ImageReceiver r0 = this.imageReceiver;
        if (r0 != null) goto L7;
    L9:
        AnimatedEmojiDrawable r02 = this.emojiDrawable;
        if (r02 == null) goto L15;
        int r03 = AnimatedEmojiDrawable.getDominantColor(r02);
        if (r03 == 0) goto L14;
        this.waitingImage = false;
        setColor(r03);
        goto L16
    L14:
        invalidate();
        goto L16
    L15:
        invalidate();
        goto L16
    L7:
        if (r0.getBitmap() == null) goto L9;
        this.waitingImage = false;
        setColor(AndroidUtilities.getDominantColor(this.imageReceiver.getBitmap()));
    L16:
        Paint r04 = this.paint;
        if (r04 == null) goto L42;
        int r4 = this.type;
        if (r4 != TYPE_REACTIONS_LOCK) goto L22;
        float r05 = getMeasuredWidth() / 2.0f;
        r13.drawCircle(r05, getMeasuredHeight() / 2.0f, r05, this.paint);
        goto L42
    L22:
        if (r4 == TYPE_REACTIONS) goto L24;
        float r06 = getMeasuredWidth() / 2.0f;
        float r42 = getMeasuredHeight() / 2.0f;
        if (this.oldShaderPaint != null) goto L34;
        this.shaderCrossfadeProgress = 1.0f;
    L34:
        float r5 = this.shaderCrossfadeProgress;
        Paint r7 = this.paint;
        if (r5 == 1.0f) goto L40;
        r7.setAlpha((int) (r5 * 255.0f));
        r13.drawCircle(r06, r42, r06, this.oldShaderPaint);
        r13.drawCircle(r06, r42, r06, this.paint);
        float r07 = this.shaderCrossfadeProgress + 0.10666667f;
        this.shaderCrossfadeProgress = r07;
        if (r07 <= 1.0f) goto L39;
        this.shaderCrossfadeProgress = 1.0f;
        this.oldShaderPaint = null;
    L39:
        invalidate();
        this.paint.setAlpha(255);
        goto L42
    L40:
        r13.drawCircle(r06, r42, r06, r7);
        goto L42
    L24:
        if (this.currentColor == 0) goto L26;
        r13.drawPath(this.path, r04);
    L28:
        if (this.cellFlickerDrawable != null) goto L30;
        this.cellFlickerDrawable = new CellFlickerDrawable();
    L30:
        this.cellFlickerDrawable.setParentWidth(getMeasuredWidth() / 2);
        CellFlickerDrawable r08 = this.cellFlickerDrawable;
        r08.drawFrame = false;
        r08.draw(r13, this.path, this);
        r13.save();
        r13.clipPath(this.path);
        this.starParticles.onDraw(r13);
        r13.restore();
        invalidate();
        goto L42
    L26:
        PremiumGradient.getInstance().updateMainGradientMatrix(0, 0, getMeasuredWidth(), getMeasuredHeight(), -AndroidUtilities.dp(24.0f), 0.0f);
        r13.drawPath(this.path, PremiumGradient.getInstance().getMainGradientPaint());
    L42:
        if (this.iconScale == 1.0f) goto L44;
        r1 = true;
    L44:
        if (r1 == false) goto L46;
        r13.save();
        float r09 = this.iconScale;
        r13.scale(r09, r09, getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f);
    L46:
        super.onDraw(r13);
        if (r1 == false) goto L49;
        r13.restore();
    L49:
        this.wasDrawn = true;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int r7, int r8) {
        super.onMeasure(r7, r8);
        if (this.type != TYPE_REACTIONS) goto L6;
        this.path.rewind();
        RectF r72 = AndroidUtilities.rectTmp;
        r72.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        Path r82 = this.path;
        float r0 = r72.width() / 2.0f;
        float r3 = r72.centerY();
        float r4 = r72.width() / 2.0f;
        Path.Direction r5 = Path.Direction.CW;
        r82.addCircle(r0, r3, r4, r5);
        r72.set((getMeasuredWidth() / 2.0f) + AndroidUtilities.dp(2.5f), (getMeasuredHeight() / 2.0f) + AndroidUtilities.dpf2(5.7f), getMeasuredWidth() - AndroidUtilities.dpf2(0.2f), getMeasuredHeight());
        this.path.addRoundRect(r72, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), r5);
        this.path.close();
        this.starParticles.rect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.starParticles.rect.inset(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));
        return;
    L6:
        updateGradient();
    }

    public void play(int r3) {
        this.isEnter = true;
        CellFlickerDrawable r0 = this.cellFlickerDrawable;
        if (r0 == null) goto L5;
        r0.progress = 0.0f;
        r0.repeatEnabled = false;
    L5:
        invalidate();
        animate().scaleX(1.1f).scaleY(1.1f).setStartDelay(r3).setInterpolator(AndroidUtilities.overshootInterpolator).setDuration(300);
    }

    public void resetAnimation() {
        this.isEnter = false;
        setScaleX(0.0f);
        setScaleY(0.0f);
    }

    public void resetColor() {
        this.colorRetrieved = false;
        this.currentColor = -1;
        if (this.type != TYPE_REACTIONS_LOCK) goto L8;
        Paint r2 = this.paint;
        if (r2 == null) goto L9;
        r2.setColor(Theme.getColor(Theme.key_windowBackgroundGray));
        return;
    L9:
        return;
    }

    public void setAnimatedEmojiDrawable(AnimatedEmojiDrawable r1) {
        this.emojiDrawable = r1;
        if (r1 == null) goto L6;
        this.waitingImage = true;
        invalidate();
        return;
    }

    public void setBlendWithColor(Integer r1) {
        this.blendColor = r1;
    }

    public void setColor(int r3) {
        this.colorRetrieved = true;
        Integer r0 = this.blendColor;
        if (r0 == null) goto L6;
        r3 = Theme.blendOver(r3, r0.intValue());
    L6:
        if (this.currentColor == r3) goto L18;
        this.currentColor = r3;
        int r02 = this.type;
        if (r02 != TYPE_REACTIONS) goto L10;
    L13:
        Paint r03 = this.paint;
        if (r03 == null) goto L16;
        r03.setColor(r3);
    L16:
        invalidate();
        return;
    L10:
        if (r02 == TYPE_REACTIONS_LOCK) goto L13;
        updateGradient();
        goto L16
    }

    public void setImageReceiver(ImageReceiver r1) {
        this.imageReceiver = r1;
        if (r1 == null) goto L6;
        this.waitingImage = true;
        invalidate();
        return;
    }

    public void setLocked(boolean r3) {
        if (this.type == TYPE_REACTIONS) goto L9;
        if (r3 == false) goto L6;
        int r32 = R.drawable.msg_mini_premiumlock;
    L7:
        setImageResource(r32);
        return;
    L6:
        r32 = R.drawable.msg_mini_stickerstar;
        goto L7
    }

    public void setWaitingImage() {
        this.waitingImage = true;
        this.wasDrawn = false;
        invalidate();
    }
}
