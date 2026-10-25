package com.exteragram.messenger.utils.chats;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaDataController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes4.dex */
public class SwipeActionsHelper {
    private final List<SwipeAction> actions;
    private float anchorY;
    private AnimatedEmojiDrawable animatedReaction;
    private long animatedReactionId;
    private boolean armed;
    private boolean beyondMax;
    private int direction;
    private float driftX;
    private float driftY;
    private final FloatValueHolder fill;
    private final SpringAnimation fillSpring;
    private boolean frameDark;
    private boolean frameGradient;
    private Paint framePaint;
    private ColorFilter iconFilter;
    private int iconFilterColor;
    private final SparseArray<Float> iconFits;
    private final SparseArray<Drawable> icons;
    private float lastX;
    private float lastY;
    private final AnimatedFloat liveAlpha;
    private boolean looped;
    private final Paint outlineDarkenPaint;
    private final Paint outlinePaint;
    private final View parent;
    private final Path path;
    private final FloatValueHolder position;
    private final SpringAnimation positionSpring;
    private Drawable reactionDrawable;
    private String reactionEmojicon;
    private ImageReceiver reactionImage;
    private final RectF rect;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean reversed;
    private final FloatValueHolder ring;
    private final SpringAnimation ringSpring;
    private boolean scrubbing;
    private int selected;
    private int slot;
    private final SparseArray<Drawable> spareIcons;
    private boolean stepped;
    private final FloatValueHolder visibility;
    private final SpringAnimation visibilitySpring;

    public static /* synthetic */ void $r8$lambda$6rKofKNhh3SqEEW6TezwfYSOqJM(SwipeActionsHelper r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$new$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$JsLMCmaHE9vX3wrjbliyiSM3az8(SwipeActionsHelper r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Qe38TqhTYtY1s_7QmxUEWXLcMa8(SwipeActionsHelper r0) {
        r0.invalidate();
    }

    public static /* synthetic */ void $r8$lambda$QrpxjI26AgLoqmKWeow1pyk3nhY(SwipeActionsHelper r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$new$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$v3p5mZqsBD9EHenLBH23tcOcdHU(SwipeActionsHelper r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    public SwipeActionsHelper(View r11, Theme.ResourcesProvider r12) {
        this.actions = new ArrayList();
        this.icons = new SparseArray();
        this.spareIcons = new SparseArray();
        this.iconFits = new SparseArray();
        Paint r0 = new Paint(1);
        this.outlinePaint = r0;
        Paint r2 = new Paint(1);
        this.outlineDarkenPaint = r2;
        this.path = new Path();
        this.rect = new RectF();
        this.liveAlpha = new AnimatedFloat(new SwipeActionsHelper$$ExternalSyntheticLambda0(this), 0, 280, CubicBezierInterpolator.EASE_OUT);
        FloatValueHolder r1 = new FloatValueHolder(0.0f);
        this.position = r1;
        this.positionSpring = new SpringAnimation(r1).setSpring(new SpringForce(0.0f).setStiffness(430.0f).setDampingRatio(0.55f)).addUpdateListener(new SwipeActionsHelper$$ExternalSyntheticLambda1(this));
        FloatValueHolder r13 = new FloatValueHolder(0.0f);
        this.visibility = r13;
        this.visibilitySpring = new SpringAnimation(r13).setMinValue(0.0f).setMaxValue(2000.0f).setSpring(new SpringForce(0.0f).setStiffness(1500.0f).setDampingRatio(1.0f)).addUpdateListener(new SwipeActionsHelper$$ExternalSyntheticLambda2(this));
        FloatValueHolder r14 = new FloatValueHolder(0.0f);
        this.fill = r14;
        this.fillSpring = new SpringAnimation(r14).setMinValue(0.0f).setSpring(new SpringForce(0.0f).setStiffness(400.0f).setDampingRatio(0.5f)).addUpdateListener(new SwipeActionsHelper$$ExternalSyntheticLambda3(this));
        FloatValueHolder r15 = new FloatValueHolder(0.0f);
        this.ring = r15;
        this.ringSpring = new SpringAnimation(r15).setMinValue(0.0f).setSpring(new SpringForce(0.0f).setStiffness(200.0f).setDampingRatio(1.0f)).addUpdateListener(new SwipeActionsHelper$$ExternalSyntheticLambda4(this));
        this.parent = r11;
        this.resourcesProvider = r12;
        Paint.Style r10 = Paint.Style.STROKE;
        r0.setStyle(r10);
        Paint.Cap r112 = Paint.Cap.ROUND;
        r0.setStrokeCap(r112);
        r0.setStrokeWidth(AndroidUtilities.dp(2.0f));
        r2.setStyle(r10);
        r2.setStrokeCap(r112);
        r2.setStrokeWidth(AndroidUtilities.dp(2.0f));
    }

    private void applyServiceShaderMatrix() {
        int r0 = this.parent.getMeasuredWidth();
        float r1 = this.parent.getY() + this.rect.top;
        Theme.ResourcesProvider r4 = this.resourcesProvider;
        if (r4 == null) goto L6;
        r4.applyServiceShaderMatrix(r0, AndroidUtilities.displaySize.y, 0.0f, r1);
        return;
    L6:
        Theme.applyServiceShaderMatrix(r0, AndroidUtilities.displaySize.y, 0.0f, r1);
    }

    private int chooseDirection(float r4) {
        float r0 = AndroidUtilities.dp(44.0f) + ((this.actions.size() - 2) * AndroidUtilities.dp(36.0f));
        float r3 = this.parent.getHeight() - r4;
        if (r3 < r0) goto L5;
        return 1;
    L5:
        if (r3 >= r4) goto L11;
        return -1;
    L11:
        return 1;
    }

    private void drawBubble(Canvas r22, float r23, float r24, float r25, float r26, float r27, float r28, float r29, float r30, int r31, boolean r32) {
        if (r29 >= 2.0f) goto L89;
        float r2 = Math.max(0.0f, 1.0f - r29);
        float r3 = Math.max(r2, (2.0f - r29) * 0.45f);
        float r210 = ((r2 * 0.3f) + 0.7f) * ((CubicBezierInterpolator.EASE_OUT_BACK.getInterpolation(r30) * 0.15f) + 0.85f);
        float r13 = r25 * r210;
        float r14 = r26 * r210;
        float r15 = r27 * (r3 * r30);
        if (r15 <= 0.0f) goto L90;
        Paint r1 = this.framePaint;
        Paint r211 = Theme.chat_actionBackgroundGradientDarkenPaint;
        boolean r33 = this.frameGradient;
        boolean r4 = this.frameDark;
        float r5 = AndroidUtilities.dp(16.0f);
        if (r32 == true) goto L11;
    L21:
        boolean r20 = r33;
        boolean r18 = r4;
        float r16 = 2.0f;
        float r17 = 1.0f;
        float r19 = 0.0f;
        float r252 = r13;
        float r262 = r14;
        int r142 = -1;
        Paint r10 = r1;
        Paint r11 = r211;
        float r12 = r5;
    L20:
        Canvas r110 = r22;
    L22:
        float r52 = r12 * r252;
        setRect(r23, r24, r52);
        applyServiceShaderMatrix();
        this.path.rewind();
        Path r212 = this.path;
        RectF r34 = this.rect;
        Path.Direction r42 = Path.Direction.CW;
        r212.addRoundRect(r34, r52, r52, r42);
        int r213 = r10.getAlpha();
        float r35 = (0.6f * r15) * r28;
        r10.setAlpha((int) (r213 * r35));
        r110.drawPath(this.path, r10);
        r10.setAlpha(r213);
        if (r20 == false) goto L29;
        int r214 = r11.getAlpha();
        if (r18 == false) goto L27;
        r11.setColor(r142);
    L27:
        r11.setAlpha((int) (r35 * r214));
        r110.drawPath(this.path, r11);
        r11.setAlpha(r214);
    L29:
        if (r262 == r19) goto L31;
        setRect(r23, r24, r12 * r262);
        this.path.rewind();
        this.path.addRoundRect(this.rect, r12, r12, r42);
        r110.save();
        r110.clipPath(this.path, Region.Op.DIFFERENCE);
    L31:
        setRect(r23, r24, r52);
        applyServiceShaderMatrix();
        this.path.rewind();
        this.path.addRoundRect(this.rect, r52, r52, r42);
        int r36 = r10.getAlpha();
        float r53 = 0.4f * r15;
        r10.setAlpha((int) (r36 * r53));
        r110.drawPath(this.path, r10);
        r10.setAlpha(r36);
        if (r20 == false) goto L37;
        int r37 = r11.getAlpha();
        if (r18 == false) goto L36;
        r11.setColor(r142);
    L36:
        r11.setAlpha((int) (r53 * r37));
        r110.drawPath(this.path, r11);
        r11.setAlpha(r37);
    L37:
        if (r262 == r19) goto L39;
        r110.restore();
    L39:
        float r215 = this.ring.getValue() / 2000.0f;
        if (r32 == true) goto L42;
    L53:
        List<SwipeAction> r216 = this.actions;
        SwipeAction r217 = r216.get(Math.floorMod(r31, r216.size()));
        SwipeAction r38 = SwipeAction.REACTION;
        if (r217 != r38) goto L60;
        if (this.reactionImage == null) goto L60;
        float r218 = (AndroidUtilities.dp(20.0f) / r16) * r252;
        float r43 = r23 - r218;
        float r54 = r24 - r218;
        float r219 = r218 * r16;
        this.reactionImage.setImageCoords(r43, r54, r219, r219);
        this.reactionImage.setAlpha(r15);
        this.reactionImage.draw(r110);
        return;
    L60:
        if (r217 == r38) goto L62;
    L65:
        boolean r44 = false;
        if (this.actions.size() == 1) goto L68;
    L70:
        boolean r39 = false;
    L71:
        if (r39 == false) goto L74;
        Drawable r45 = Theme.getThemeDrawable("drawableReplyIcon", this.resourcesProvider);
    L77:
        if (r45 != null) goto L79;
        return;
    L79:
        Float r55 = this.iconFits.get(r217.iconRes);
        if (r39 == false) goto L82;
        float r132 = r252;
    L87:
        float r220 = (r45.getIntrinsicWidth() / r16) * r132;
        float r310 = (r45.getIntrinsicHeight() / r16) * r132;
        r45.setColorFilter(getIconFilter());
        r45.setAlpha((int) (r15 * 255.0f));
        r45.setBounds((int) (r23 - r220), (int) (r24 - r310), (int) (r220 + r23), (int) (r310 + r24));
        r45.draw(r110);
        return;
    L82:
        float r133 = r252 * r217.iconTrim;
        if (r55 == null) goto L85;
        float r112 = r55.floatValue();
    L86:
        r132 = r133 * r112;
        goto L87
    L85:
        r112 = r17;
        goto L86
    L74:
        if ((Math.floorDiv(r31, this.actions.size()) & 1) == 0) goto L76;
        r44 = true;
    L76:
        r45 = getIcon(r217, r44);
        goto L77
    L68:
        if (r217 != SwipeAction.REPLY) goto L70;
        r39 = true;
        goto L71
    L62:
        if (this.reactionDrawable == null) goto L65;
        float r221 = (AndroidUtilities.dp(20.0f) / r16) * r252;
        setRect(r23, r24, r221);
        this.path.rewind();
        float r9 = 0.35f * r221;
        this.path.addRoundRect(this.rect, r9, r9, r42);
        r110.save();
        r110.clipPath(this.path);
        this.reactionDrawable.setAlpha((int) (r15 * 255.0f));
        this.reactionDrawable.setBounds((int) (r23 - r221), (int) (r24 - r221), (int) (r23 + r221), (int) (r221 + r24));
        this.reactionDrawable.draw(r110);
        this.reactionDrawable.setAlpha(255);
        r110.restore();
        return;
    L42:
        if (r215 == r19) goto L53;
        if (r215 == r17) goto L53;
        float r113 = r215 + r17;
        float r311 = this.outlinePaint.getStrokeWidth();
        float r222 = (r17 - r215) * r311;
        if (r222 == r19) goto L53;
        float r56 = r12 * r113;
        setRect(r23, r24, r56 - r222);
        applyServiceShaderMatrix();
        int r6 = this.outlinePaint.getAlpha();
        this.outlinePaint.setAlpha((int) (r6 * r15));
        this.outlinePaint.setStrokeWidth(r222);
        r110.drawRoundRect(this.rect, r56, r56, this.outlinePaint);
        this.outlinePaint.setStrokeWidth(r311);
        this.outlinePaint.setAlpha(r6);
        if (r20 == false) goto L53;
        int r62 = this.outlineDarkenPaint.getAlpha();
        if (r18 == false) goto L52;
        this.outlineDarkenPaint.setColor(r142);
    L52:
        this.outlineDarkenPaint.setAlpha((int) (r62 * r15));
        this.outlineDarkenPaint.setStrokeWidth(r222);
        r110.drawRoundRect(this.rect, r56, r56, this.outlineDarkenPaint);
        this.outlineDarkenPaint.setStrokeWidth(r311);
        goto L53
    L11:
        if (r28 <= 0.0f) goto L21;
        if (this.fill.getValue() != 0.0f) goto L21;
        setRect(r23, r24, (r5 * r13) - (this.outlinePaint.getStrokeWidth() / 2.0f));
        applyServiceShaderMatrix();
        int r63 = this.outlinePaint.getAlpha();
        r16 = 2.0f;
        r17 = 1.0f;
        this.outlinePaint.setAlpha((int) (r63 * r15));
        float r114 = 360.0f * r28;
        r20 = r33;
        r252 = r13;
        r262 = r14;
        r142 = -1;
        r19 = 0.0f;
        r12 = r5;
        r18 = r4;
        r11 = r211;
        r10 = r1;
        r22.drawArc(this.rect, -90.0f, r114, false, this.outlinePaint);
        this.outlinePaint.setAlpha(r63);
        if (r20 == false) goto L20;
        int r111 = this.outlineDarkenPaint.getAlpha();
        if (r18 == false) goto L19;
        this.outlineDarkenPaint.setColor(-1);
    L19:
        this.outlineDarkenPaint.setAlpha((int) (r111 * r15));
        r110 = r22;
        r110.drawArc(this.rect, -90.0f, r114, false, this.outlineDarkenPaint);
        goto L22
    L90:
        return;
    }

    private Drawable getIcon(SwipeAction r4, boolean r5) {
        if (r5 == false) goto L4;
        SparseArray<Drawable> r52 = this.spareIcons;
    L5:
        Drawable r0 = r52.get(r4.iconRes);
        if (r0 != null) goto L16;
        Drawable r02 = this.parent.getContext().getDrawable(r4.iconRes);
        if (r02 != null) goto L12;
        return null;
    L12:
        if (this.iconFits.get(r4.iconRes) != null) goto L14;
        this.iconFits.put(r4.iconRes, Float.valueOf(measureIconFit(r02)));
    L14:
        Drawable r3 = r02.mutate();
        r52.put(r4.iconRes, r3);
        return r3;
    L16:
        return r0;
    L4:
        r52 = this.icons;
        goto L5
    }

    private ColorFilter getIconFilter() {
        int r0 = Theme.getColor(Theme.key_chat_serviceIcon, this.resourcesProvider);
        if (this.iconFilter != null) goto L5;
    L6:
        this.iconFilterColor = r0;
        this.iconFilter = new PorterDuffColorFilter(r0, PorterDuff.Mode.MULTIPLY);
    L8:
        return this.iconFilter;
    L5:
        if (this.iconFilterColor == r0) goto L8;
        goto L6
    }

    private void invalidate() {
        this.parent.invalidate();
    }

    private /* synthetic */ void lambda$new$0(DynamicAnimation r1, float r2, float r3) {
        invalidate();
    }

    private /* synthetic */ void lambda$new$1(DynamicAnimation r1, float r2, float r3) {
        invalidate();
    }

    private /* synthetic */ void lambda$new$2(DynamicAnimation r1, float r2, float r3) {
        invalidate();
    }

    private /* synthetic */ void lambda$new$3(DynamicAnimation r1, float r2, float r3) {
        invalidate();
    }

    private float measureIconFit(Drawable r12) {
        int r3 = r12.getIntrinsicWidth();
        int r7 = r12.getIntrinsicHeight();
        if (r3 <= 0) goto L33;
        if (r7 <= 0) goto L35;
        Bitmap r0 = Bitmap.createBitmap(r3, r7, Bitmap.Config.ARGB_8888);
        r12.setBounds(0, 0, r3, r7);
        r12.draw(new Canvas(r0));
        int[] r1 = new int[r3 * r7];
        r0.getPixels(r1, 0, r3, 0, 0, r3, r7);
        r0.recycle();
        int r122 = -1;
        int r02 = -1;
        int r2 = r3;
        int r4 = r7;
        int r5 = 0;
    L7:
        if (r5 >= r7) goto L23;
        int r6 = 0;
    L9:
        if (r6 >= r3) goto L22;
        if ((r1[(r5 * r3) + r6] >>> 24) < 8) goto L21;
        if (r6 >= r2) goto L15;
        r2 = r6;
    L15:
        if (r6 <= r122) goto L17;
        r122 = r6;
    L17:
        if (r5 >= r4) goto L19;
        r4 = r5;
    L19:
        if (r5 <= r02) goto L21;
        r02 = r5;
    L21:
        r6 = r6 + 1;
        goto L9
    L22:
        r5 = r5 + 1;
        goto L7
    L23:
        if (r122 < r2) goto L34;
        if (r02 >= r4) goto L27;
        return 1.0f;
    L27:
        return Math.min(AndroidUtilities.dpf2(15.0f) / ((r122 - r2) + 1), AndroidUtilities.dpf2(16.5f) / ((r02 - r4) + 1));
    L34:
        return 1.0f;
    L35:
        return 1.0f;
    L33:
        return 1.0f;
    }

    private void move(int r3) {
        int r0 = this.slot + r3;
        this.slot = r0;
        this.selected = Math.floorMod(r0, this.actions.size());
        this.parent.performHapticFeedback(4, 2);     // Catch: Exception -> L6
    L4:
        this.positionSpring.getSpring().setFinalPosition(this.slot * 2000.0f);
        this.positionSpring.start();
        invalidate();
    }

    private void setRect(float r3, float r4, float r5) {
        this.rect.set(r3 - r5, r4 - r5, r3 + r5, r4 + r5);
    }

    public void detach() {
        AnimatedEmojiDrawable r0 = this.animatedReaction;
        if (r0 == null) goto L5;
        r0.removeView(this.parent);
        this.animatedReaction = null;
        this.animatedReactionId = 0;
    L5:
        ImageReceiver r02 = this.reactionImage;
        if (r02 == null) goto L8;
        r02.onDetachedFromWindow();
        this.reactionImage = null;
        this.reactionEmojicon = null;
    L8:
        this.reactionDrawable = null;
    }

    public void draw(Canvas r18, float r19, boolean r20, float r21, float r22) {
        if (this.actions.isEmpty() == false) goto L5;
        return;
    L5:
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) goto L8;
        return;
    L8:
        if (this.direction != 0) goto L10;
        this.direction = chooseDirection(r21);
    L10:
        Paint r2 = Theme.getThemePaint("paintChatActionBackground", this.resourcesProvider);
        Paint r13 = Theme.chat_actionBackgroundGradientDarkenPaint;
        if (this.outlinePaint.getColor() == r2.getColor()) goto L14;
        this.outlinePaint.setColor(r2.getColor());
    L14:
        if (this.outlineDarkenPaint.getColor() == r13.getColor()) goto L17;
        this.outlineDarkenPaint.setColor(r13.getColor());
    L17:
        if (this.outlinePaint.getShader() == r2.getShader()) goto L20;
        this.outlinePaint.setShader(r2.getShader());
    L20:
        if (this.outlineDarkenPaint.getShader() == r13.getShader()) goto L22;
        this.outlineDarkenPaint.setShader(r13.getShader());
    L22:
        this.framePaint = r2;
        Theme.ResourcesProvider r23 = this.resourcesProvider;
        if (r23 != null) goto L25;
    L27:
        boolean r24 = false;
    L28:
        this.frameGradient = r24;
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider)) > 0.5d) goto L31;
        boolean r25 = true;
    L32:
        this.frameDark = r25;
        int r14 = this.outlineDarkenPaint.getColor();
        float r26 = this.fill.getValue() / 2000.0f;
        if (r26 <= 1.0f) goto L35;
        this.beyondMax = true;
    L35:
        float r8 = 0.0f;
        if (this.visibility.getValue() != 0.0f) goto L39;
        this.fillSpring.cancel();
        this.fillSpring.getSpring().setFinalPosition(0.0f);
        this.fill.setValue(0.0f);
        this.ringSpring.cancel();
        this.ringSpring.getSpring().setFinalPosition(0.0f);
        this.ring.setValue(0.0f);
        this.beyondMax = false;
    L39:
        if (this.fillSpring.getSpring().getFinalPosition() != 2000.0f) goto L41;
        boolean r7 = true;
    L43:
        if (r7 == false) goto L45;
        float r10 = 1.0f;
    L47:
        if (r10 != 1.0f) goto L51;
        if (r7 == true) goto L51;
        this.fillSpring.getSpring().setFinalPosition(2000.0f);
        this.fillSpring.start();
        this.ringSpring.getSpring().setFinalPosition(2000.0f);
        this.ringSpring.start();
    L51:
        if (r19 > (-AndroidUtilities.dp(20.0f))) goto L53;
        float r72 = 2000.0f;
    L55:
        if (r72 == this.visibilitySpring.getSpring().getFinalPosition()) goto L59;
        this.visibilitySpring.getSpring().setFinalPosition(r72);
        if (this.visibilitySpring.isRunning() == true) goto L59;
        this.visibilitySpring.start();
    L59:
        float r73 = this.visibility.getValue() / 2000.0f;
        float r9 = this.parent.getMeasuredWidth();
        if (r20 == false) goto L62;
        float r11 = 0.5f;
    L63:
        float r1 = Math.max((r19 * r11) + r9, Math.min((r22 + r9) / 2.0f, r9));
        boolean r92 = this.beyondMax;
        if (r92 == false) goto L66;
        float r4 = r26;
    L67:
        if (r92 == true) goto L70;
        r8 = 1.0f - r26;
    L70:
        float r16 = this.position.getValue() / 2000.0f;
        float r27 = AndroidUtilities.dp(36.0f) * this.direction;
        float r93 = this.liveAlpha.set(this.scrubbing);
        int r5 = this.actions.size();
        int r6 = (int) Math.ceil(r16 - 2.0f);
        int r112 = (int) Math.floor(r16 + 2.0f);
        if (this.looped == true) goto L73;
    L76:
        r6 = Math.max(r6, 0);
        r112 = Math.min(r112, r5 - 1);
    L75:
        int r12 = r112;
    L77:
        int r3 = this.slot;
        if (r6 > r12) goto L83;
        if (r6 == r3) goto L81;
        float r32 = r6 - r16;
        float r52 = r8;
        float r15 = r10;
        int r102 = r6;
        float r62 = r73;
        float r74 = r15;
        float r152 = r27;
        float r28 = r1;
        drawBubble(r18, r28, r21 + (r32 * r27), r4, r52, r62, r74, Math.abs(r32), r93, r102, false);
    L82:
        int r17 = r102 + 1;
        r8 = r52;
        r10 = r74;
        r73 = r62;
        r6 = r17;
        r1 = r28;
        r27 = r152;
        goto L77
    L81:
        float r53 = r10;
        r102 = r6;
        r62 = r73;
        r74 = r53;
        r152 = r27;
        r52 = r8;
        r28 = r1;
        goto L82
    L83:
        float r153 = r27;
        float r29 = r1;
        float r110 = r21 + ((r3 - r16) * r153);
        drawBubble(r18, r29, r110, r4, r8, r73, r10, Math.abs(r3 - r16), 1.0f, this.slot, true);
        this.outlineDarkenPaint.setColor(r14);
        r13.setColor(r14);
        return;
    L73:
        if (r5 >= 2) goto L75;
    L66:
        r4 = r73;
        goto L67
    L62:
        r11 = 1.0f;
        goto L63
    L53:
        r72 = 0.0f;
        goto L55
    L45:
        r10 = MathUtils.clamp(((-r19) - AndroidUtilities.dp(20.0f)) / AndroidUtilities.dp(30.0f), 0.0f, 1.0f);
        goto L47
    L41:
        r7 = false;
        goto L43
    L31:
        r25 = false;
        goto L32
    L25:
        if (r23.hasGradientService() == false) goto L27;
        r24 = true;
        goto L28
    }

    public SwipeAction getSelected() {
        int r0 = this.selected;
        if (r0 >= 0) goto L5;
        return null;
    L5:
        if (r0 < this.actions.size()) goto L7;
        return null;
    L7:
        return this.actions.get(this.selected);
    }

    public boolean remove(SwipeAction r4) {
        boolean r1 = false;
        if (this.scrubbing == false) goto L5;
    L18:
        return r1;
    L5:
        if (this.actions.size() < 2) goto L18;
        int r42 = this.actions.indexOf(r4);
        if (r42 < 0) goto L18;
        if (r42 == this.selected) goto L12;
    L14:
        this.actions.remove(r42);
        r1 = true;
        if (this.selected < this.actions.size()) goto L17;
        int r43 = this.actions.size() - 1;
        this.selected = r43;
        this.slot = r43;
        this.positionSpring.cancel();
        this.positionSpring.getSpring().setFinalPosition(this.selected * 2000.0f);
        this.position.setValue(this.selected * 2000.0f);
    L17:
        invalidate();
        goto L18
    L12:
        if (this.visibility.getValue() == 0.0f) goto L14;
        goto L14
    }

    public void select(int r3) {
        this.scrubbing = true;
        if (r3 == this.selected) goto L14;
        if (r3 >= 0) goto L6;
        return;
    L6:
        if (r3 >= this.actions.size()) goto L16;
        int r0 = r3 - this.selected;
        if (this.looped == false) goto L12;
        r0 = Math.floorMod(r0, this.actions.size());
        if (r0 <= (this.actions.size() / 2)) goto L12;
        r0 = r0 - this.actions.size();
    L12:
        this.selected = r3;
        this.slot += r0;
        this.positionSpring.getSpring().setFinalPosition(this.slot * 2000.0f);
        this.positionSpring.start();
        invalidate();
        return;
    L16:
        return;
    }

    public void setReaction(int r8, String r9, long r10) {
        if (r10 != 0) goto L5;
        Emoji.EmojiDrawable r102 = null;
        if (r9 == null) goto L12;
        TLRPC.TL_availableReaction r5 = MediaDataController.getInstance(r8).getReactionsMap().get(r9);
    L13:
        if (r5 != null) goto L15;
    L24:
        detach();
        if (r9 == null) goto L27;
        r102 = Emoji.getEmojiDrawable(r9);
    L27:
        this.reactionDrawable = r102;
        return;
    L15:
        if (r5.center_icon == null) goto L24;
        if (this.reactionImage != null) goto L19;
    L22:
        detach();
        ImageReceiver r82 = new ImageReceiver(this.parent);
        this.reactionImage = r82;
        r82.ignoreNotifications = true;
        r82.onAttachedToWindow();
        this.reactionImage.setImage(ImageLocation.getForDocument(r5.center_icon), "40_40_lastreactframe", DocumentObject.getSvgThumb(r5.static_icon, Theme.key_windowBackgroundGray, 1.0f), "webp", r5, 1);
        this.reactionEmojicon = r9;
        return;
    L19:
        if (TextUtils.equals(this.reactionEmojicon, r9) == false) goto L22;
        return;
    L12:
        r5 = null;
        goto L13
    L5:
        if (this.animatedReactionId == r10) goto L7;
        detach();
        AnimatedEmojiDrawable r83 = AnimatedEmojiDrawable.make(r8, 0, r10);
        this.animatedReaction = r83;
        r83.addView(this.parent);
        this.animatedReactionId = r10;
    L7:
        this.reactionDrawable = this.animatedReaction;
    }

    public int size() {
        return this.actions.size();
    }

    public void start(List<SwipeAction> r4) {
        this.actions.clear();
        this.actions.addAll(r4);
        this.looped = ExteraConfig.getSwipeActionsLoop();
        this.reversed = ExteraConfig.getSwipeActionsReversed();
        this.selected = 0;
        this.slot = 0;
        this.armed = false;
        this.stepped = false;
        this.scrubbing = false;
        this.liveAlpha.set(0.0f, true);
        this.anchorY = 0.0f;
        this.driftX = 0.0f;
        this.driftY = 0.0f;
        this.direction = 0;
        this.positionSpring.cancel();
        this.positionSpring.getSpring().setFinalPosition(0.0f);
        this.position.setValue(0.0f);
        this.visibilitySpring.cancel();
        this.visibilitySpring.getSpring().setFinalPosition(0.0f);
        this.visibility.setValue(0.0f);
        this.fillSpring.cancel();
        this.fillSpring.getSpring().setFinalPosition(0.0f);
        this.fill.setValue(0.0f);
        this.ringSpring.cancel();
        this.ringSpring.getSpring().setFinalPosition(0.0f);
        this.ring.setValue(0.0f);
        this.beyondMax = false;
    }

    public void update(float r5, float r6, float r7) {
        if (this.actions.size() >= 2) goto L6;
        return;
    L6:
        if (Math.abs(r5) >= AndroidUtilities.dp(50.0f)) goto L10;
        this.armed = false;
        this.scrubbing = false;
        return;
    L10:
        if (this.armed == true) goto L13;
        this.armed = true;
        this.lastX = r6;
        this.lastY = r7;
        this.driftX = 0.0f;
        this.driftY = 0.0f;
        return;
    L13:
        float r52 = Math.abs(r6 - this.lastX);
        float r1 = r7 - this.lastY;
        this.lastX = r6;
        this.lastY = r7;
        if (this.scrubbing == true) goto L22;
        this.driftX = (this.driftX * 0.7f) + (r52 * 0.3f);
        float r53 = (this.driftY * 0.7f) + (Math.abs(r1) * 0.3f);
        this.driftY = r53;
        if (r53 >= AndroidUtilities.dp(0.5f)) goto L18;
        return;
    L18:
        if (this.driftY <= (this.driftX * 1.5f)) goto L55;
        this.scrubbing = true;
        this.anchorY = r7;
        return;
    L55:
        return;
    L22:
        float r54 = r7 - this.anchorY;
        if (this.direction >= 0) goto L25;
        int r62 = -1;
    L26:
        float r55 = r54 * r62;
        if (this.reversed == false) goto L29;
        int r63 = -1;
    L30:
        float r56 = r55 * r63;
        if (this.stepped == false) goto L33;
        float r64 = 36.0f;
    L34:
        float r65 = AndroidUtilities.dp(r64);
        if (r56 < r65) goto L43;
        this.anchorY = r7;
        this.stepped = true;
        if (this.looped == false) goto L39;
    L40:
        move(1);
        return;
    L39:
        if (this.selected < (this.actions.size() - 1)) goto L40;
        return;
    L43:
        if (r56 > (-r65)) goto L52;
        this.anchorY = r7;
        this.stepped = true;
        if (this.looped == false) goto L47;
    L48:
        move(-1);
        return;
    L47:
        if (this.selected > 0) goto L48;
        return;
    L52:
        return;
    L33:
        r64 = 44.0f;
        goto L34
    L29:
        r63 = 1;
        goto L30
    L25:
        r62 = 1;
        goto L26
    }
}
