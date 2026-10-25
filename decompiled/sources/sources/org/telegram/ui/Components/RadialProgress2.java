package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec;
import com.google.android.material.progressindicator.DeterminateDrawable;
import com.google.android.material.progressindicator.IndeterminateDrawable;
import java.util.Locale;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class RadialProgress2 implements Drawable.Callback {
    private int backgroundStroke;
    private float circleCheckProgress;
    private int circleColor;
    private int circleColorKey;
    private int circleCrossfadeColorKey;
    private float circleCrossfadeColorProgress;
    private Paint circleMiniPaint;
    public Paint circlePaint;
    private int circlePressedColor;
    private int circlePressedColorKey;
    private int circleRadius;
    private boolean drawBackground;
    private boolean drawMiniIcon;
    public float drawScale;
    public int iconColor;
    public int iconColorKey;
    private int iconPressedColor;
    private int iconPressedColorKey;
    public float iconScale;
    private IndeterminateDrawable<CircularProgressIndicatorSpec> indeterminateDrawable;
    private int indicatorColor;
    private int indicatorPressedColor;
    private boolean invertColors;
    private boolean isPressed;
    private boolean isPressedMini;
    private int lastMainProgressLevel;
    private int lastMiniProgressLevel;
    private int maxIconSize;
    public MediaActionDrawable mediaActionDrawable;
    private Bitmap miniDrawBitmap;
    private Canvas miniDrawCanvas;
    private float miniIconScale;
    private IndeterminateDrawable<CircularProgressIndicatorSpec> miniIndeterminateDrawable;
    private MediaActionDrawable miniMediaActionDrawable;
    private Paint miniProgressBackgroundPaint;
    private DeterminateDrawable<CircularProgressIndicatorSpec> miniProgressDrawable;
    private CircularProgressIndicatorSpec miniProgressSpec;
    private boolean needDrawBackground;
    private float overlayImageAlpha;
    public ImageReceiver overlayImageView;
    private Paint overlayPaint;
    private float overrideAlpha;
    public float overrideCircleAlpha;
    private View parent;
    private int progressColor;
    private DeterminateDrawable<CircularProgressIndicatorSpec> progressDrawable;
    public RectF progressRect;
    private CircularProgressIndicatorSpec progressSpec;
    private Theme.ResourcesProvider resourcesProvider;
    protected int style;
    private int trackColor;
    private int trackPressedColor;
    private int waveAmplitude;

    public RadialProgress2(View r2) {
        this(r2, null);
    }

    public RadialProgress2(View r5, Theme.ResourcesProvider r6) {
        this.progressRect = new RectF();
        this.progressColor = -1;
        this.overlayPaint = new Paint(1);
        this.circlePaint = new Paint(1);
        this.circleMiniPaint = new Paint(1);
        this.needDrawBackground = true;
        this.invertColors = false;
        this.lastMainProgressLevel = -1;
        this.lastMiniProgressLevel = -1;
        this.indicatorColor = -1;
        this.indicatorPressedColor = -1;
        this.trackColor = -1;
        this.trackPressedColor = -1;
        this.miniIconScale = 1.0f;
        this.circleColorKey = -1;
        this.circleCrossfadeColorKey = -1;
        this.circleCheckProgress = 1.0f;
        this.circlePressedColorKey = -1;
        this.iconColorKey = -1;
        this.iconPressedColorKey = -1;
        this.overrideCircleAlpha = 1.0f;
        this.drawBackground = true;
        this.overrideAlpha = 1.0f;
        this.overlayImageAlpha = 1.0f;
        this.iconScale = 1.0f;
        this.drawScale = 1.0f;
        this.resourcesProvider = r6;
        this.miniProgressBackgroundPaint = new Paint(1);
        this.parent = r5;
        ImageReceiver r62 = new ImageReceiver(r5);
        this.overlayImageView = r62;
        r62.setInvalidateAll(true);
        this.mediaActionDrawable = new MediaActionDrawable();
        MediaActionDrawable r63 = new MediaActionDrawable();
        this.miniMediaActionDrawable = r63;
        r63.setMini(true);
        this.miniMediaActionDrawable.setIcon(4, false);
        int r64 = AndroidUtilities.dp(22.0f);
        this.circleRadius = r64;
        this.overlayImageView.setRoundRadius(r64);
        this.overlayPaint.setColor(1677721600);
        if (r5 == null) goto L6;
        this.mediaActionDrawable.setDelegate(new RadialProgress2$$ExternalSyntheticLambda0(r5));
        this.miniMediaActionDrawable.setDelegate(new RadialProgress2$$ExternalSyntheticLambda0(r5));
        return;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private int getTrackAlpha() {
        if (Theme.isCurrentThemeDark() == false) goto L5;
        float r1 = 0.325f;
    L7:
        return (int) (r1 * 255.0f);
    L5:
        r1 = 0.275f;
        goto L7
    }

    private float getWaveScale(int r3, int r4) {
        float r0 = 0.0f;
        if (iconIsCancel(r3) == false) goto L5;
        float r32 = 1.0f;
    L7:
        if (iconIsCancel(r4) == false) goto L10;
        r0 = 1.0f;
    L10:
        return r0 + ((r32 - r0) * this.mediaActionDrawable.getTransitionProgress());
    L5:
        r32 = 0.0f;
        goto L7
    }

    private static boolean iconIsCancel(int r1) {
        if (r1 != 3) goto L5;
        return true;
    L5:
        if (r1 != 14) goto L7;
        return true;
    L7:
        if (r1 != 12) goto L9;
        return true;
    L9:
        if (r1 == 13) goto L17;
        return false;
    L17:
        return true;
    }

    private void initMdcDrawables(Context r7) {
        this.progressSpec = new CircularProgressIndicatorSpec(r7, null);
        CircularProgressIndicatorSpec r0 = new CircularProgressIndicatorSpec(r7, null);
        this.miniProgressSpec = r0;
        CircularProgressIndicatorSpec r1 = this.progressSpec;
        r1.hideAnimationBehavior = 1;
        r1.showAnimationBehavior = 2;
        r0.hideAnimationBehavior = 1;
        r0.showAnimationBehavior = 2;
        setSpecValues(this.circleRadius * 2, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(4.0f));
        setMiniSpecValues(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
        DeterminateDrawable<CircularProgressIndicatorSpec> r02 = DeterminateDrawable.createCircularDrawable(r7, this.progressSpec);
        this.progressDrawable = r02;
        r02.setCallback(this);
        this.progressDrawable.setVisible(false, false, false);
        IndeterminateDrawable<CircularProgressIndicatorSpec> r03 = IndeterminateDrawable.createCircularDrawable(r7, this.progressSpec);
        this.indeterminateDrawable = r03;
        r03.setCallback(this);
        this.indeterminateDrawable.setVisible(false, false, false);
        DeterminateDrawable<CircularProgressIndicatorSpec> r04 = DeterminateDrawable.createCircularDrawable(r7, this.miniProgressSpec);
        this.miniProgressDrawable = r04;
        r04.setCallback(this);
        this.miniProgressDrawable.setVisible(false, false, false);
        IndeterminateDrawable<CircularProgressIndicatorSpec> r72 = IndeterminateDrawable.createCircularDrawable(r7, this.miniProgressSpec);
        this.miniIndeterminateDrawable = r72;
        r72.setCallback(this);
        this.miniIndeterminateDrawable.setVisible(false, false, false);
        updateM3Colors();
    }

    private void invalidateParent() {
        int r0 = AndroidUtilities.dp(2.0f);
        View r1 = this.parent;
        RectF r5 = this.progressRect;
        int r2 = ((int) r5.left) - r0;
        int r3 = ((int) r5.top) - r0;
        int r02 = r0 * 2;
        r1.invalidate(r2, r3, ((int) r5.right) + r02, ((int) r5.bottom) + r02);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void stopMdcDrawable(Drawable r1, boolean r2) {
        if (r1 != 0) goto L5;
        return;
    L5:
        if ((r1 instanceof Animatable) == false) goto L7;
        ((Animatable) r1).stop();
    L8:
        if (r2 == false) goto L11;
        r1.setCallback(null);
        return;
    L11:
        return;
    L7:
        r1.setVisible(false, false);
        goto L8
    }

    private void updateM3Colors() {
        if (isMaterial3Style() == true) goto L6;
        return;
    L6:
        if (this.progressSpec == null) goto L79;
        if (this.isPressed == false) goto L17;
        int r0 = this.iconPressedColorKey;
        if (r0 < 0) goto L12;
        int r02 = getThemedColor(r0);
    L13:
        int r3 = this.circlePressedColorKey;
        if (r3 < 0) goto L16;
        int r32 = getThemedColor(r3);
    L26:
        if (this.overlayImageView.hasBitmapImage() == false) goto L32;
        float r4 = this.overlayImageView.getCurrentAlpha();
        if (r4 < 1.0f) goto L30;
        r02 = -1;
        goto L32
    L30:
        int r6 = Color.red(r02);
        int r7 = Color.green(r02);
        int r8 = Color.blue(r02);
        r02 = Color.argb(Color.alpha(r02) + ((int) ((255 - r0) * r4)), r6 + ((int) ((255 - r6) * r4)), r7 + ((int) ((255 - r7) * r4)), r8 + ((int) ((255 - r8) * r4)));
    L32:
        if (this.isPressed == false) goto L36;
        int r42 = this.indicatorPressedColor;
        if (r42 == (-1)) goto L36;
    L35:
        r02 = r42;
    L40:
        if (this.overlayImageView.hasBitmapImage() == false) goto L44;
        if (this.overlayImageView.getCurrentAlpha() < 1.0f) goto L44;
    L50:
        boolean r43 = false;
    L52:
        if (this.invertColors == false) goto L55;
        if (r43 == true) goto L55;
    L57:
        if (this.isPressed == false) goto L61;
        int r03 = this.trackPressedColor;
        if (r03 == (-1)) goto L61;
    L65:
        CircularProgressIndicatorSpec r44 = this.progressSpec;
        int[] r5 = r44.indicatorColors;
        if (r5.length != 0) goto L68;
    L69:
        r44.indicatorColors = new int[]{r32};
        PorterDuffColorFilter r45 = new PorterDuffColorFilter(r32, PorterDuff.Mode.SRC_IN);
        IndeterminateDrawable<CircularProgressIndicatorSpec> r33 = this.indeterminateDrawable;
        if (r33 == null) goto L72;
        r33.setColorFilter(r45);
    L72:
        DeterminateDrawable<CircularProgressIndicatorSpec> r34 = this.progressDrawable;
        if (r34 == null) goto L75;
        r34.setColorFilter(r45);
    L75:
        CircularProgressIndicatorSpec r35 = this.progressSpec;
        if (r35.trackColor == r03) goto L79;
        r35.trackColor = r03;
        goto L79
    L68:
        if (r5[0] == r32) goto L75;
    L61:
        r03 = this.trackColor;
        if (r03 != (-1)) goto L65;
        r03 = ColorUtils.setAlphaComponent(r32, getTrackAlpha());
    L55:
        r32 = r02;
    L44:
        if (this.drawBackground == false) goto L50;
        if (isMaterial3Style() == true) goto L48;
    L49:
        r43 = true;
        goto L52
    L48:
        if (this.needDrawBackground == false) goto L50;
    L36:
        r42 = this.indicatorColor;
        if (r42 == (-1)) goto L40;
    L16:
        r32 = this.circlePressedColor;
        goto L26
    L12:
        r02 = this.iconPressedColor;
        goto L13
    L17:
        int r04 = this.iconColorKey;
        if (r04 < 0) goto L20;
        r02 = getThemedColor(r04);
    L21:
        int r36 = this.circleColorKey;
        if (r36 < 0) goto L24;
        r32 = getThemedColor(r36);
        goto L26
    L24:
        r32 = this.circleColor;
        goto L26
    L20:
        r02 = this.iconColor;
    L79:
        if (this.miniProgressSpec != null) goto L81;
        return;
    L81:
        if (this.isPressedMini == true) goto L83;
    L88:
        int r05 = this.iconColorKey;
        if (r05 < 0) goto L91;
        int r06 = getThemedColor(r05);
    L92:
        boolean r37 = this.isPressedMini;
        if (r37 == false) goto L97;
        int r46 = this.indicatorPressedColor;
        if (r46 == (-1)) goto L97;
    L96:
        r06 = r46;
    L100:
        if (r37 == false) goto L104;
        int r38 = this.trackPressedColor;
        if (r38 == (-1)) goto L104;
    L108:
        CircularProgressIndicatorSpec r13 = this.miniProgressSpec;
        int[] r2 = r13.indicatorColors;
        if (r2.length != 0) goto L111;
    L112:
        r13.indicatorColors = new int[]{r06};
    L114:
        if (r13.trackColor == r38) goto L118;
        r13.trackColor = r38;
        return;
    L118:
        return;
    L111:
        if (r2[0] == r06) goto L114;
    L104:
        r38 = this.trackColor;
        if (r38 != (-1)) goto L108;
        r38 = ColorUtils.setAlphaComponent(r06, getTrackAlpha());
    L97:
        r46 = this.indicatorColor;
        if (r46 == (-1)) goto L100;
    L91:
        r06 = this.iconColor;
        goto L92
    L83:
        if (this.circleCrossfadeColorKey >= 0) goto L88;
        int r07 = this.iconPressedColorKey;
        if (r07 < 0) goto L87;
        r06 = getThemedColor(r07);
        goto L92
    L87:
        r06 = this.iconPressedColor;
        goto L92
    }

    private void validateVisibleDrawables(float r4, int r5, DeterminateDrawable<CircularProgressIndicatorSpec> r6, IndeterminateDrawable<CircularProgressIndicatorSpec> r7, boolean r8) {
        if (r4 >= 1.0f) goto L31;
        if (iconIsCancel(r5) == true) goto L11;
        if (r4 <= 0.04f) goto L31;
        if (r5 != 2) goto L31;
    L11:
        if (r4 > 0.04f) goto L24;
        if (r7.isVisible() == true) goto L20;
        View r3 = this.parent;
        if (r3 == null) goto L20;
        if (r3.isAttachedToWindow() == false) goto L20;
        r7.setVisible(true, true, true);
    L20:
        if (r6.isVisible() == false) goto L37;
        r6.setVisible(false, false, r8);
        return;
    L37:
        return;
    L24:
        if (r7.isVisible() == false) goto L27;
        r7.setVisible(false, false, false);
    L27:
        if (r6.isVisible() == true) goto L38;
        r6.setVisible(true, false, false);
        return;
    L38:
        return;
    L31:
        if (r6.isVisible() == false) goto L34;
        r6.setVisible(false, false, r8);
    L34:
        if (r7.isVisible() == false) goto L39;
        r7.setVisible(false, false, r8);
        return;
    }

    private void validateVisibleDrawables(int r4, int r5, float r6, DeterminateDrawable<CircularProgressIndicatorSpec> r7, IndeterminateDrawable<CircularProgressIndicatorSpec> r8, boolean r9) {
        if (iconIsCancel(r4) == true) goto L21;
        if (r6 <= 0.04f) goto L14;
        if (r6 >= 1.0f) goto L14;
        if (iconIsCancel(r5) == false) goto L14;
        if (r4 == 2) goto L21;
    L14:
        if (r7.isVisible() == false) goto L17;
        r7.setVisible(false, false, r9);
    L17:
        if (r8.isVisible() == false) goto L36;
        r8.setVisible(false, false, r9);
        return;
    L36:
        return;
    L21:
        if (r6 > 0.04f) goto L23;
        boolean r42 = true;
    L25:
        if (r7.isVisible() != r42) goto L28;
        r7.setVisible(!r42, false, false);
    L28:
        if (r8.isVisible() == r42) goto L37;
        if (r42 == false) goto L34;
        View r3 = this.parent;
        if (r3 != null) goto L33;
        return;
    L33:
        if (r3.isAttachedToWindow() == true) goto L34;
        return;
    L34:
        r8.setVisible(r42, true, r42);
        return;
    L37:
        return;
    L23:
        r42 = false;
        goto L25
    }

    @Keep
    public void draw(Canvas r25) {
        if (this.mediaActionDrawable.getCurrentIcon() != 4) goto L7;
        if (this.mediaActionDrawable.getTransitionProgress() < 1.0f) goto L7;
        return;
    L7:
        if (this.progressRect.isEmpty() == true) goto L267;
        boolean r5 = false;
        if (this.drawScale == 1.0f) goto L12;
        boolean r2 = true;
    L13:
        if (r2 == false) goto L15;
        r25.save();
        float r7 = this.drawScale;
        r25.scale(r7, r7, this.progressRect.centerX(), this.progressRect.centerY());
    L15:
        int r72 = this.mediaActionDrawable.getCurrentIcon();
        int r8 = this.mediaActionDrawable.getPreviousIcon();
        float r9 = getWholeAlpha();
        if (this.isPressedMini == true) goto L18;
    L27:
        int r10 = this.iconColorKey;
        MediaActionDrawable r11 = this.miniMediaActionDrawable;
        if (r10 < 0) goto L30;
        r11.setColor(getThemedColor(r10));
    L31:
        int r102 = this.circleColorKey;
        if (r102 < 0) goto L37;
        int r112 = this.circleCrossfadeColorKey;
        Paint r12 = this.circleMiniPaint;
        if (r112 < 0) goto L36;
        r12.setColor(AndroidUtilities.getOffsetColor(getThemedColor(r102), getThemedColor(this.circleCrossfadeColorKey), this.circleCrossfadeColorProgress, this.circleCheckProgress));
    L39:
        if (this.isPressed == false) goto L48;
        int r103 = this.iconPressedColorKey;
        MediaActionDrawable r113 = this.mediaActionDrawable;
        if (r103 < 0) goto L43;
        int r104 = getThemedColor(r103);
        r113.setColor(r104);
        this.mediaActionDrawable.setBackColor(getThemedColor(this.circlePressedColorKey));
    L44:
        int r114 = this.circlePressedColorKey;
        Paint r122 = this.circlePaint;
        if (r114 < 0) goto L47;
        r122.setColor(getThemedColor(r114));
    L57:
        if (this.drawMiniIcon == true) goto L61;
        if (this.circleCrossfadeColorKey >= 0) goto L61;
    L63:
        this.circlePaint.setAlpha((int) (((this.circlePaint.getAlpha() * r9) * this.overrideAlpha) * this.overrideCircleAlpha));
        this.circleMiniPaint.setAlpha((int) ((this.circleMiniPaint.getAlpha() * r9) * this.overrideAlpha));
        if (this.drawMiniIcon == true) goto L68;
        if (this.circleCrossfadeColorKey >= 0) goto L68;
    L70:
        int r115 = (int) this.progressRect.centerX();
        int r123 = (int) this.progressRect.centerY();
    L72:
        if (this.overlayImageView.hasBitmapImage() == false) goto L78;
        float r13 = this.overlayImageView.getCurrentAlpha();
        float r17 = 1.0f;
        this.overlayPaint.setAlpha((int) (this.overrideAlpha * ((100.0f * r13) * r9)));
        if (r13 < 1.0f) goto L76;
        int r3 = -1;
        boolean r19 = r2;
        int r18 = 2;
    L77:
        this.mediaActionDrawable.setColor(r3);
        ImageReceiver r22 = this.overlayImageView;
        int r32 = this.circleRadius;
        r22.setImageCoords(r115 - r32, r123 - r32, r32 * 2, r32 * 2);
    L79:
        Canvas r23 = this.miniDrawCanvas;
        if (r23 != null) goto L82;
    L86:
        int r24 = Integer.MIN_VALUE;
    L87:
        if (r5 == true) goto L89;
    L95:
        boolean r4 = false;
    L97:
        if (r4 == false) goto L115;
        if (this.drawMiniIcon == false) goto L101;
    L104:
        Canvas r6 = this.miniDrawCanvas;
        if (r6 == null) goto L107;
        r6.drawCircle(r115, r123, this.circleRadius, this.circlePaint);
    L107:
        if (r72 == 4) goto L109;
    L110:
        int r62 = this.backgroundStroke;
        int r105 = this.circleRadius;
        if (r62 == 0) goto L113;
        r25.drawCircle(r115, r123, r105 - AndroidUtilities.dp(3.5f), this.circlePaint);
        goto L115
    L113:
        r25.drawCircle(r115, r123, r105, this.circlePaint);
        goto L115
    L109:
        if (r9 == 0.0f) goto L115;
    L101:
        if (this.circleCrossfadeColorKey < 0) goto L107;
    L115:
        if (this.overlayImageView.hasBitmapImage() == false) goto L124;
        this.overlayImageView.setAlpha((this.overrideAlpha * r9) * this.overlayImageAlpha);
        if (this.drawMiniIcon == false) goto L119;
    L120:
        Canvas r63 = this.miniDrawCanvas;
        if (r63 == null) goto L123;
        this.overlayImageView.draw(r63);
        this.miniDrawCanvas.drawCircle(r115, r123, this.circleRadius, this.overlayPaint);
    L123:
        this.overlayImageView.draw(r25);
        r25.drawCircle(r115, r123, this.circleRadius, this.overlayPaint);
        goto L124
    L119:
        if (this.circleCrossfadeColorKey < 0) goto L123;
    L124:
        int r64 = this.circleRadius;
        int r106 = this.maxIconSize;
        if (r106 <= 0) goto L129;
        if (r64 <= r106) goto L129;
        r64 = r106;
    L129:
        if (this.iconScale == r17) goto L131;
        r25.save();
        float r107 = this.iconScale;
        r25.scale(r107, r107, r115, r123);
    L131:
        int r132 = r115 - r64;
        int r14 = r123 - r64;
        int r116 = r115 + r64;
        int r124 = r123 + r64;
        this.mediaActionDrawable.setBounds(r132, r14, r116, r124);
        this.mediaActionDrawable.setHasOverlayImage(this.overlayImageView.hasBitmapImage());
        if (this.drawMiniIcon == false) goto L134;
    L135:
        Canvas r65 = this.miniDrawCanvas;
        if (r65 != null) goto L140;
    L138:
        r65 = r25;
    L140:
        if (ExteraConfig.getNewLoadingStyle() == true) goto L142;
    L144:
        boolean r108 = false;
    L145:
        float r15 = getProgress();
        int r52 = this.miniMediaActionDrawable.getCurrentIcon();
        if (r108 == true) goto L148;
    L165:
        boolean r33 = false;
    L167:
        if (this.drawMiniIcon == true) goto L173;
        IndeterminateDrawable<CircularProgressIndicatorSpec> r53 = this.indeterminateDrawable;
        if (r53 == null) goto L173;
        if (r53.isVisible() == false) goto L173;
    L178:
        boolean r54 = true;
    L180:
        if (r33 == false) goto L202;
        float r222 = 255.0f;
        if (this.drawMiniIcon == true) goto L201;
        updateM3Colors();
        boolean r232 = r33;
        int r34 = 2;
        if (this.style != 2) goto L190;
        CircularProgressIndicatorSpec r109 = this.progressSpec;
        if (r4 == false) goto L188;
        int r42 = 0;
    L189:
        r109.waveAmplitude = r42;
        goto L190
    L188:
        r42 = (int) (this.waveAmplitude * getWaveScale(r72, r8));
    L190:
        int r43 = (int) ((r9 * 255.0f) * this.overrideAlpha);
        if (r15 > 0.04f) goto L198;
        if (r54 == true) goto L198;
        View r73 = this.parent;
        if (r73 == null) goto L198;
        if (r73.isAttachedToWindow() == false) goto L198;
        this.indeterminateDrawable.setVisible(true, true, true);
        r54 = true;
    L198:
        if (r54 == true) goto L200;
        this.progressDrawable.setBounds(r132, r14, r116, r124);
        this.progressDrawable.setAlpha(r43);
        this.progressDrawable.draw(r65);
    L204:
        if (this.drawMiniIcon == false) goto L206;
    L210:
        Canvas r44 = this.miniDrawCanvas;
        MediaActionDrawable r55 = this.mediaActionDrawable;
        if (r44 == null) goto L213;
        r55.draw(r44);
    L214:
        if (r24 == Integer.MIN_VALUE) goto L219;
        Canvas r45 = this.miniDrawCanvas;
        if (r45 == null) goto L219;
        r45.restoreToCount(r24);
    L219:
        if (this.drawMiniIcon == false) goto L221;
    L222:
        float r26 = Math.abs(this.progressRect.width() - AndroidUtilities.dp(44.0f));
        float r46 = AndroidUtilities.density;
        RectF r47 = this.progressRect;
        if (r26 >= r46) goto L225;
        float r27 = r47.centerX() + AndroidUtilities.dp(16.0f);
        float r48 = this.progressRect.centerY() + AndroidUtilities.dp(16.0f);
        int r35 = 20;
        int r142 = 0;
    L226:
        int r56 = r35 / 2;
        if (this.drawMiniIcon == true) goto L229;
        float r66 = r17;
    L236:
        Canvas r74 = this.miniDrawCanvas;
        if (r74 == null) goto L239;
        float r36 = (r35 + 18) + r142;
        r74.drawCircle(AndroidUtilities.dp(r36), AndroidUtilities.dp(r36), (AndroidUtilities.dp(r56 + 1) * r66) * this.miniIconScale, Theme.checkboxSquare_eraserPaint);
    L241:
        if (this.miniDrawCanvas == null) goto L244;
        Bitmap r37 = this.miniDrawBitmap;
        RectF r75 = this.progressRect;
        r25.drawBitmap(r37, (int) r75.left, (int) r75.top, null);
    L244:
        if (this.miniIconScale >= r17) goto L246;
        int r38 = r25.save();
        float r76 = this.miniIconScale;
        r25.scale(r76, r76, r27, r48);
    L247:
        float r57 = r56;
        r25.drawCircle(r27, r48, (AndroidUtilities.dp(r57) * r66) + (AndroidUtilities.dp(r17) * (r17 - this.circleCheckProgress)), this.circleMiniPaint);
        if (this.drawMiniIcon == false) goto L257;
        this.miniMediaActionDrawable.setBounds((int) (r27 - (AndroidUtilities.dp(r57) * r66)), (int) (r48 - (AndroidUtilities.dp(r57) * r66)), (int) ((AndroidUtilities.dp(r57) * r66) + r27), (int) ((AndroidUtilities.dp(r57) * r66) + r48));
        if (r232 == false) goto L255;
        updateM3Colors();
        int r58 = AndroidUtilities.dp(12.0f);
        if (r15 > 0.04f) goto L254;
        float r59 = r58 * r66;
        this.miniIndeterminateDrawable.setBounds((int) (r27 - r59), (int) (r48 - r59), (int) (r27 + r59), (int) (r48 + r59));
        this.miniIndeterminateDrawable.setAlpha((int) (r66 * r222));
        this.miniIndeterminateDrawable.draw(r25);
        goto L255
    L254:
        float r510 = r58 * r66;
        this.miniProgressDrawable.setBounds((int) (r27 - r510), (int) (r48 - r510), (int) (r27 + r510), (int) (r48 + r510));
        this.miniProgressDrawable.setAlpha((int) (r66 * r222));
        this.miniProgressDrawable.draw(r25);
    L255:
        this.miniMediaActionDrawable.draw(r25);
    L257:
        if (r38 == Integer.MIN_VALUE) goto L260;
        r25.restoreToCount(r38);
    L260:
        if (this.iconScale == r17) goto L262;
        r25.restore();
    L262:
        if (r19 == false) goto L266;
        r25.restore();
        return;
    L266:
        return;
    L246:
        r38 = Integer.MIN_VALUE;
        goto L247
    L239:
        this.miniProgressBackgroundPaint.setColor(this.progressColor);
        r25.drawCircle(r27, r48, AndroidUtilities.dp(12.0f), this.miniProgressBackgroundPaint);
        goto L241
    L229:
        if (this.miniMediaActionDrawable.getCurrentIcon() == 4) goto L231;
        r66 = r17;
    L233:
        if (r66 != 0.0f) goto L236;
        this.drawMiniIcon = false;
        goto L236
    L231:
        r66 = r17 - this.miniMediaActionDrawable.getTransitionProgress();
        goto L233
    L225:
        r27 = r47.centerX() + AndroidUtilities.dp(18.0f);
        r48 = AndroidUtilities.dp(18.0f) + this.progressRect.centerY();
        r142 = r34;
        r35 = 22;
        goto L226
    L221:
        if (this.circleCrossfadeColorKey < 0) goto L260;
    L213:
        r55.draw(r25);
        goto L214
    L206:
        if (this.circleCrossfadeColorKey >= 0) goto L210;
        this.mediaActionDrawable.setOverrideAlpha(this.overrideAlpha);
        this.mediaActionDrawable.draw(r25);
        goto L214
    L200:
        this.indeterminateDrawable.setBounds(r132, r14, r116, r124);
        this.indeterminateDrawable.setAlpha(r43);
        this.indeterminateDrawable.draw(r65);
        goto L204
    L201:
        r232 = r33;
        r34 = 2;
        goto L204
    L202:
        r232 = r33;
        r34 = 2;
        r222 = 255.0f;
    L173:
        if (this.drawMiniIcon == false) goto L179;
        IndeterminateDrawable<CircularProgressIndicatorSpec> r511 = this.miniIndeterminateDrawable;
        if (r511 == null) goto L179;
        if (r511.isVisible() == true) goto L178;
    L179:
        r54 = false;
        goto L180
    L148:
        if (this.drawMiniIcon == true) goto L152;
        if (iconIsCancel(r72) == false) goto L152;
    L164:
        r33 = true;
    L152:
        if (this.drawMiniIcon == false) goto L156;
        if (iconIsCancel(r52) == true) goto L164;
    L156:
        if (r15 <= 0.04f) goto L165;
        if (r15 >= r17) goto L165;
        boolean r1010 = this.drawMiniIcon;
        int r39 = r18;
        if (r1010 == false) goto L162;
        if (r52 == r39) goto L164;
    L162:
        if (r1010 == true) goto L165;
        if (r72 != r39) goto L165;
    L142:
        if (isMaterial3Style() == false) goto L144;
        r108 = true;
        goto L145
    L134:
        if (this.circleCrossfadeColorKey < 0) goto L138;
    L89:
        if (this.drawBackground == false) goto L95;
        if (isMaterial3Style() == true) goto L93;
    L94:
        r4 = true;
        goto L97
    L93:
        if (this.needDrawBackground == false) goto L95;
    L82:
        if (this.circleCrossfadeColorKey < 0) goto L86;
        if (this.circleCheckProgress == r17) goto L86;
        r24 = r23.save();
        float r49 = r17 - ((r17 - this.circleCheckProgress) * 0.1f);
        this.miniDrawCanvas.scale(r49, r49, r115, r123);
        goto L87
    L76:
        int r310 = Color.red(r104);
        int r152 = Color.green(r104);
        int r512 = Color.blue(r104);
        r18 = 2;
        r19 = r2;
        r3 = Color.argb(Color.alpha(r104) + ((int) ((255 - r10) * r13)), r310 + ((int) ((255 - r310) * r13)), r152 + ((int) ((255 - r152) * r13)), r512 + ((int) ((255 - r512) * r13)));
        r5 = true;
        goto L77
    L78:
        r19 = r2;
        r17 = 1.0f;
        r18 = 2;
        r5 = true;
    L68:
        if (this.miniDrawCanvas == null) goto L70;
        r115 = (int) Math.ceil(this.progressRect.width() / 2.0f);
        r123 = (int) Math.ceil(this.progressRect.height() / 2.0f);
    L61:
        if (this.miniDrawCanvas == null) goto L63;
        this.miniDrawBitmap.eraseColor(0);
        goto L63
    L47:
        r122.setColor(this.circlePressedColor);
        goto L57
    L43:
        r104 = this.iconPressedColor;
        r113.setColor(r104);
        this.mediaActionDrawable.setBackColor(this.circlePressedColor);
        goto L44
    L48:
        int r1011 = this.iconColorKey;
        MediaActionDrawable r117 = this.mediaActionDrawable;
        if (r1011 < 0) goto L51;
        r104 = getThemedColor(r1011);
        r117.setColor(r104);
        this.mediaActionDrawable.setBackColor(getThemedColor(this.circleColorKey));
    L52:
        int r118 = this.circleColorKey;
        Paint r125 = this.circlePaint;
        if (r118 < 0) goto L55;
        r125.setColor(getThemedColor(r118));
        goto L57
    L55:
        r125.setColor(this.circleColor);
        goto L57
    L51:
        r104 = this.iconColor;
        r117.setColor(r104);
        this.mediaActionDrawable.setBackColor(this.circleColor);
        goto L52
    L36:
        r12.setColor(getThemedColor(r102));
        goto L39
    L37:
        this.circleMiniPaint.setColor(this.circleColor);
        goto L39
    L30:
        r11.setColor(this.iconColor);
        goto L31
    L18:
        if (this.circleCrossfadeColorKey >= 0) goto L27;
        int r1012 = this.iconPressedColorKey;
        MediaActionDrawable r119 = this.miniMediaActionDrawable;
        if (r1012 < 0) goto L22;
        r119.setColor(getThemedColor(r1012));
    L23:
        int r1013 = this.circlePressedColorKey;
        Paint r1110 = this.circleMiniPaint;
        if (r1013 < 0) goto L26;
        r1110.setColor(getThemedColor(r1013));
        goto L39
    L26:
        r1110.setColor(this.circlePressedColor);
        goto L39
    L22:
        r119.setColor(this.iconPressedColor);
        goto L23
    L12:
        r2 = false;
        goto L13
    }

    public int getCircleColorKey() {
        return this.circleColorKey;
    }

    public int getIcon() {
        return this.mediaActionDrawable.getCurrentIcon();
    }

    public int getMiniIcon() {
        return this.miniMediaActionDrawable.getCurrentIcon();
    }

    public float getOverrideAlpha() {
        return this.overrideAlpha;
    }

    public float getProgress() {
        if (this.drawMiniIcon == false) goto L7;
        MediaActionDrawable r1 = this.miniMediaActionDrawable;
    L6:
        return r1.getProgress();
    L7:
        r1 = this.mediaActionDrawable;
        goto L6
    }

    public RectF getProgressRect() {
        return this.progressRect;
    }

    public int getRadius() {
        return this.circleRadius;
    }

    @Keep
    public int getStyle() {
        return this.style;
    }

    public float getTransitionProgress() {
        if (this.drawMiniIcon == false) goto L7;
        MediaActionDrawable r1 = this.miniMediaActionDrawable;
    L6:
        return r1.getTransitionProgress();
    L7:
        r1 = this.mediaActionDrawable;
        goto L6
    }

    public float getWholeAlpha() {
        int r0 = this.mediaActionDrawable.getCurrentIcon();
        int r1 = this.mediaActionDrawable.getPreviousIcon();
        if (this.backgroundStroke == 0) goto L13;
        if (r0 != 3) goto L8;
        float r5 = this.mediaActionDrawable.getTransitionProgress();
    L7:
        return 1.0f - r5;
    L8:
        if (r1 == 3) goto L10;
        return 1.0f;
    L10:
        return this.mediaActionDrawable.getTransitionProgress();
    L13:
        if (r0 != 3) goto L15;
    L21:
        if (r1 == 4) goto L23;
    L24:
        if (r0 == 4) goto L26;
        return 1.0f;
    L26:
        r5 = this.mediaActionDrawable.getTransitionProgress();
        goto L7
    L23:
        return this.mediaActionDrawable.getTransitionProgress();
    L15:
        if (r0 == 6) goto L21;
        if (r0 == 10) goto L21;
        if (r0 == 8) goto L21;
        if (r0 != 0) goto L24;
        goto L21
    }

    public void initMiniIcons() {
        if (this.miniDrawBitmap == null) goto L8;
        return;
    L8:
        this.miniDrawBitmap = Bitmap.createBitmap(AndroidUtilities.dp(48.0f), AndroidUtilities.dp(48.0f), Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L7
        this.miniDrawCanvas = new Canvas(this.miniDrawBitmap);     // Catch: Throwable -> L7
        return;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r1) {
        invalidateParent();
    }

    @Keep
    public boolean isInvertColors() {
        return this.invertColors;
    }

    public boolean isMaterial3Style() {
        int r2 = this.style;
        if (r2 != 1) goto L5;
    L9:
        return true;
    L5:
        if (r2 == 2) goto L9;
        return false;
    }

    @Keep
    public boolean isNeedDrawBackground() {
        return this.needDrawBackground;
    }

    public void onAttachedToWindow() {
        this.overlayImageView.onAttachedToWindow();
    }

    public void onDetachedFromWindow() {
        stopMdcDrawable(this.progressDrawable, false);
        stopMdcDrawable(this.indeterminateDrawable, false);
        stopMdcDrawable(this.miniProgressDrawable, false);
        stopMdcDrawable(this.miniIndeterminateDrawable, false);
        this.overlayImageView.onDetachedFromWindow();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
        View r0 = this.parent;
        if (r0 == null) goto L6;
        r0.scheduleDrawable(r1, r2, r3);
        return;
    }

    @Keep
    public void setAsMini() {
        this.mediaActionDrawable.setMini(true);
    }

    public void setBackgroundDrawable(MessageDrawable r2) {
        this.mediaActionDrawable.setBackgroundDrawable(r2);
        this.miniMediaActionDrawable.setBackgroundDrawable(r2);
    }

    @Keep
    public void setBackgroundGradientDrawable(LinearGradient r2) {
        this.mediaActionDrawable.setBackgroundGradientDrawable(r2);
        this.miniMediaActionDrawable.setBackgroundGradientDrawable(r2);
    }

    public void setCircleCrossfadeColor(int r1, float r2, float r3) {
        this.circleCrossfadeColorKey = r1;
        this.circleCrossfadeColorProgress = r2;
        this.circleCheckProgress = r3;
        this.miniIconScale = 1.0f;
        if (r1 < 0) goto L6;
        initMiniIcons();
        return;
    }

    @Keep
    public void setCircleRadius(int r2) {
        this.circleRadius = r2;
        this.overlayImageView.setRoundRadius(r2);
        if (isMaterial3Style() == false) goto L6;
        CircularProgressIndicatorSpec r22 = this.progressSpec;
        r22.indicatorSize = this.circleRadius * 2;
        return;
    }

    public void setColorKeys(int r1, int r2, int r3, int r4) {
        this.circleColorKey = r1;
        this.circlePressedColorKey = r2;
        this.iconColorKey = r3;
        this.iconPressedColorKey = r4;
    }

    @Keep
    public void setColors(int r1, int r2, int r3, int r4) {
        this.circleColor = r1;
        this.circlePressedColor = r2;
        this.iconColor = r3;
        this.iconPressedColor = r4;
        this.circleColorKey = -1;
        this.circlePressedColorKey = -1;
        this.iconColorKey = -1;
        this.iconPressedColorKey = -1;
    }

    public void setDrawBackground(boolean r1) {
        this.drawBackground = r1;
    }

    @Keep
    public void setIcon(int r8, boolean r9, boolean r10) {
        if (r8 != this.mediaActionDrawable.getCurrentIcon()) goto L5;
        boolean r0 = true;
    L6:
        if (r9 == false) goto L10;
        if (r0 == false) goto L10;
        return;
    L10:
        if (isMaterial3Style() == false) goto L17;
        if (this.mediaActionDrawable.getProgress() <= 0.999f) goto L17;
        if (iconIsCancel(r8) == false) goto L17;
        setProgress(0.0f, false, true);
    L17:
        if (isMaterial3Style() == false) goto L20;
        if (r0 == true) goto L20;
        updateM3Colors();
        RadialProgress2 r02 = this;
        int r1 = r8;
        boolean r6 = r10;
        r02.validateVisibleDrawables(r1, this.mediaActionDrawable.getCurrentIcon(), getProgress(), this.progressDrawable, this.indeterminateDrawable, r6);
    L21:
        r02.mediaActionDrawable.setIcon(r1, r6);
        View r7 = r02.parent;
        if (r7 == null) goto L28;
        if (r6 == true) goto L26;
        r7.invalidate();
        return;
    L26:
        r02.invalidateParent();
        return;
    L28:
        return;
    L20:
        r02 = this;
        r1 = r8;
        r6 = r10;
        goto L21
    L5:
        r0 = false;
        goto L6
    }

    public void setImageOverlay(Bitmap r1) {
        this.overlayImageView.setImageBitmap(r1);
    }

    public void setImageOverlay(String r8) {
        ImageReceiver r0 = this.overlayImageView;
        if (r8 == null) goto L6;
        String r7 = String.format(Locale.US, "%d_%d", new Object[]{Integer.valueOf(this.circleRadius * 2), Integer.valueOf(this.circleRadius * 2)});
    L7:
        r0.setImage(r8, r7, null, null, -1);
        return;
    L6:
        r7 = null;
        goto L7
    }

    public void setImageOverlay(TLRPC.PhotoSize r11, TLRPC.Document r12, Object r13) {
        String r5 = String.format(Locale.US, "%d_%d", new Object[]{Integer.valueOf(this.circleRadius * 2), Integer.valueOf(this.circleRadius * 2)});
        this.overlayImageView.setImage(ImageLocation.getForDocument(r11, r12), r5, null, null, r13, 1);
    }

    public void setImageOverlay(TLRPC.PhotoSize r17, TLRPC.PhotoSize r18, TLRPC.Document r19, Object r20) {
        String r7 = String.format(Locale.US, "%d_%d", new Object[]{Integer.valueOf(this.circleRadius * 2), Integer.valueOf(this.circleRadius * 2)});
        ImageReceiver r5 = this.overlayImageView;
        ImageLocation r0 = null;
        if (r17 != null) goto L5;
        ImageLocation r6 = null;
    L6:
        if (r18 == null) goto L9;
        r0 = ImageLocation.getForDocument(r18, r19);
    L9:
        r5.setImage(r6, r7, r0, r7, null, 0, null, r20, 1);
        return;
    L5:
        r6 = ImageLocation.getForDocument(r17, r19);
        goto L6
    }

    public void setInvertColors(boolean r1) {
        this.invertColors = r1;
    }

    @Keep
    public void setM3Colors(int r1, int r2) {
        setM3Colors(r1, r1, r2, r2);
    }

    @Keep
    public void setM3Colors(int r1, int r2, int r3, int r4) {
        this.indicatorColor = r1;
        this.indicatorPressedColor = r2;
        this.trackColor = r3;
        this.trackPressedColor = r4;
    }

    public void setMaxIconSize(int r1) {
        this.maxIconSize = r1;
    }

    public void setMiniIcon(int r12, boolean r13, boolean r14) {
        if (r12 != 2) goto L5;
    L8:
        boolean r2 = false;
        if (r12 != this.miniMediaActionDrawable.getCurrentIcon()) goto L11;
        boolean r0 = true;
    L12:
        if (r13 == false) goto L16;
        if (r0 == false) goto L16;
        return;
    L16:
        if (this.drawMiniIcon == true) goto L18;
    L21:
        RadialProgress2 r4 = this;
        int r5 = r12;
        boolean r10 = r14;
    L22:
        r4.miniMediaActionDrawable.setIcon(r5, r10);
        if (r5 == 4) goto L25;
    L26:
        r2 = true;
    L27:
        r4.drawMiniIcon = r2;
        if (r2 == false) goto L30;
        r4.initMiniIcons();
    L30:
        if (r10 == true) goto L33;
        r4.parent.invalidate();
        return;
    L33:
        r4.invalidateParent();
        return;
    L25:
        if (r4.miniMediaActionDrawable.getTransitionProgress() >= 1.0f) goto L27;
    L18:
        if (isMaterial3Style() == false) goto L21;
        if (r0 == true) goto L21;
        updateM3Colors();
        r4 = this;
        r5 = r12;
        r10 = r14;
        r4.validateVisibleDrawables(r5, this.miniMediaActionDrawable.getCurrentIcon(), getProgress(), this.miniProgressDrawable, this.miniIndeterminateDrawable, r10);
        goto L22
    L11:
        r0 = false;
        goto L12
    L5:
        if (r12 == 3) goto L8;
        if (r12 == 4) goto L8;
    }

    public void setMiniIconScale(float r1) {
        this.miniIconScale = r1;
    }

    public void setMiniProgressBackgroundColor(int r1) {
        this.miniProgressBackgroundPaint.setColor(r1);
    }

    @Keep
    public void setMiniSpecValues(int r2, int r3, int r4, int r5) {
        if (isMaterial3Style() == true) goto L5;
        return;
    L5:
        CircularProgressIndicatorSpec r1 = this.miniProgressSpec;
        r1.indicatorSize = r2;
        r1.trackThickness = r3;
        r1.trackCornerRadius = r4;
        r1.useRelativeTrackCornerRadius = true;
        r1.trackCornerRadiusFraction = 0.5f;
        r1.indicatorTrackGapSize = r5;
    }

    public void setNeedDrawBackground(boolean r1) {
        this.needDrawBackground = r1;
    }

    public void setOverlayImageAlpha(float r1) {
        this.overlayImageAlpha = r1;
    }

    public void setOverrideAlpha(float r1) {
        this.overrideAlpha = r1;
    }

    public void setParent(View r3) {
        this.parent = r3;
        this.overlayImageView.setParentView(r3);
        MediaActionDrawable r0 = this.mediaActionDrawable;
        Objects.requireNonNull(r3);
        r0.setDelegate(new RadialProgress2$$ExternalSyntheticLambda0(r3));
        this.miniMediaActionDrawable.setDelegate(new RadialProgress2$$ExternalSyntheticLambda0(r3));
    }

    public void setPressed(boolean r1, boolean r2) {
        if (r2 == false) goto L4;
        this.isPressedMini = r1;
    L5:
        updateM3Colors();
        invalidateParent();
        return;
    L4:
        this.isPressed = r1;
        goto L5
    }

    public void setProgress(float r2, boolean r3) {
        setProgress(r2, r3, false);
    }

    public void setProgress(float r13, boolean r14, boolean r15) {
        if (r15 == false) goto L4;
    L10:
        if (r15 == false) goto L12;
    L14:
        int r152 = (int) (10000.0f * r13);
        if (this.drawMiniIcon == false) goto L22;
        this.miniMediaActionDrawable.setProgress(r13, r14);
        if (isMaterial3Style() == false) goto L19;
        this.miniProgressDrawable.setLevel(r152);
        updateM3Colors();
        RadialProgress2 r1 = this;
        r1.validateVisibleDrawables(r13, getMiniIcon(), this.miniProgressDrawable, this.miniIndeterminateDrawable, r14);
    L20:
        r1.lastMiniProgressLevel = r152;
        return;
    L19:
        r1 = this;
        goto L20
    L22:
        this.mediaActionDrawable.setProgress(r13, r14);
        if (isMaterial3Style() == false) goto L25;
        this.progressDrawable.setLevel(r152);
        updateM3Colors();
        validateVisibleDrawables(r13, getIcon(), this.progressDrawable, this.indeterminateDrawable, r14);
    L25:
        this.lastMainProgressLevel = r152;
        return;
    L12:
        if (r13 != getProgress()) goto L14;
        return;
    L4:
        if (isMaterial3Style() == false) goto L10;
        if (r13 != 0.0f) goto L10;
        if (getProgress() <= 0.0f) goto L10;
    }

    public void setProgressColor(int r1) {
        this.progressColor = r1;
    }

    public void setProgressRect(float r1, float r2, float r3, float r4) {
        this.progressRect.set(r1, r2, r3, r4);
    }

    public void setProgressRect(int r1, int r2, int r3, int r4) {
        this.progressRect.set(r1, r2, r3, r4);
    }

    public void setResourcesProvider(Theme.ResourcesProvider r1) {
        this.resourcesProvider = r1;
    }

    @Keep
    public void setSpecValues(int r2, int r3, int r4, int r5) {
        if (isMaterial3Style() == true) goto L5;
        return;
    L5:
        CircularProgressIndicatorSpec r1 = this.progressSpec;
        r1.indicatorSize = r2;
        r1.trackThickness = r3;
        r1.trackCornerRadius = r4;
        r1.useRelativeTrackCornerRadius = true;
        r1.trackCornerRadiusFraction = 0.5f;
        r1.indicatorTrackGapSize = r5;
    }

    @Keep
    public void setStyle(int r6) {
        boolean r1 = false;
        if (ExteraConfig.getNewLoadingStyle() == true) goto L7;
        if (r6 == 0) goto L7;
        r6 = 0;
    L7:
        if (this.style == r6) goto L28;
        this.style = r6;
        if (r6 != 0) goto L14;
        this.mediaActionDrawable.drawProgressCircle = true;
        this.miniMediaActionDrawable.drawProgressCircle = true;
        stopMdcDrawable(this.progressDrawable, true);
        stopMdcDrawable(this.indeterminateDrawable, true);
        stopMdcDrawable(this.miniProgressDrawable, true);
        stopMdcDrawable(this.miniIndeterminateDrawable, true);
        this.progressDrawable = null;
        this.indeterminateDrawable = null;
        this.miniProgressDrawable = null;
        this.miniIndeterminateDrawable = null;
        this.progressSpec = null;
        this.miniProgressSpec = null;
        return;
    L14:
        if (r6 == 1) goto L18;
        if (r6 == 2) goto L18;
        return;
    L18:
        this.mediaActionDrawable.drawProgressCircle = false;
        this.miniMediaActionDrawable.drawProgressCircle = false;
        initMdcDrawables(this.parent.getContext());
        int r3 = this.lastMainProgressLevel;
        if (r3 < 0) goto L21;
        this.progressDrawable.setLevel(r3);
    L21:
        int r32 = this.lastMiniProgressLevel;
        if (r32 < 0) goto L24;
        this.miniProgressDrawable.setLevel(r32);
    L24:
        if (r6 != 2) goto L26;
        r1 = true;
    L26:
        setWavy(r1);
        return;
    }

    @Keep
    public void setWavy(boolean r4) {
        if (this.style == 2) goto L6;
        return;
    L6:
        if (r4 == false) goto L9;
        this.miniProgressSpec.indicatorInset = 0;
        this.progressSpec.indicatorInset = 0;
        setWavyValues(AndroidUtilities.dp(15.0f), AndroidUtilities.dp(1.6f), AndroidUtilities.dp(5.0f), 0.05f);
        return;
    L9:
        CircularProgressIndicatorSpec r42 = this.progressSpec;
        r42.indicatorInset = AndroidUtilities.dp(4.0f);
        CircularProgressIndicatorSpec r43 = this.miniProgressSpec;
        r43.indicatorInset = AndroidUtilities.dp(4.0f);
        setWavyValues(0, 0, 0, 1.0f);
    }

    public void setWavyValues(int r3, int r4, int r5, float r6) {
        if (this.style == 2) goto L5;
        return;
    L5:
        CircularProgressIndicatorSpec r0 = this.progressSpec;
        r0.wavelengthDeterminate = r3;
        r0.wavelengthIndeterminate = r3;
        this.waveAmplitude = r4;
        r0.waveAmplitude = r4;
        r0.waveSpeed = r5;
        r0.waveAmplitudeRampProgressMin = r6;
        invalidateParent();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable r1, Runnable r2) {
        View r0 = this.parent;
        if (r0 == null) goto L6;
        r0.unscheduleDrawable(r1, r2);
        return;
    }
}
