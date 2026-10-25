package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextPaint;
import android.view.View;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.GenericProvider;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class CheckBoxBase {
    private static Paint forbidPaint;
    private static Paint paint;
    private float alpha;
    public long animationDuration;
    private boolean attachedToWindow;
    private int background2ColorKey;
    private float backgroundAlpha;
    private int backgroundColor;
    private int backgroundColorKey;
    private Paint backgroundPaint;
    private int backgroundType;
    public Rect bounds;
    private ObjectAnimator checkAnimator;
    private int checkColorKey;
    private Paint checkPaint;
    public float checkScale;
    private String checkedText;
    private GenericProvider<Void, Paint> circlePaintProvider;
    private float customRadius;
    private float customRadiusFactor;
    private boolean cutCheck;
    private boolean drawUnchecked;
    private boolean enabled;
    private boolean forbidden;
    private boolean isChecked;
    private MessageDrawable messageDrawable;
    private View parentView;
    private Path path;
    private float progress;
    private ProgressDelegate progressDelegate;
    private RectF rect;
    private Theme.ResourcesProvider resourcesProvider;
    private float size;
    private int strokeBackgroundKey;
    private int strokeBackgroundWidth;
    private TextPaint textPaint;
    private boolean useDefaultCheck;

    /* renamed from: org.telegram.ui.Components.CheckBoxBase$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ CheckBoxBase this$0;

        public AnonymousClass1(CheckBoxBase r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(CheckBoxBase.m10006$$Nest$fgetcheckAnimator(this.this$0)) == false) goto L6;
            CheckBoxBase.m10008$$Nest$fputcheckAnimator(this.this$0, null);
        L6:
            if (CheckBoxBase.m10007$$Nest$fgetisChecked(this.this$0) == true) goto L9;
            CheckBoxBase.m10009$$Nest$fputcheckedText(this.this$0, null);
            return;
        }
    }

    public interface ProgressDelegate {
        void setProgress(float r1);
    }

    /* renamed from: $r8$lambda$jYImu91pPxs1Bzccu4bB-G9Tg5s, reason: not valid java name */
    public static /* synthetic */ Paint m10005$r8$lambda$jYImu91pPxs1Bzccu4bBG9Tg5s(Void r0) {
        return paint;
    }

    /* renamed from: -$$Nest$fgetcheckAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ObjectAnimator m10006$$Nest$fgetcheckAnimator(CheckBoxBase r0) {
        return r0.checkAnimator;
    }

    /* renamed from: -$$Nest$fgetisChecked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10007$$Nest$fgetisChecked(CheckBoxBase r0) {
        return r0.isChecked;
    }

    /* renamed from: -$$Nest$fputcheckAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10008$$Nest$fputcheckAnimator(CheckBoxBase r0, ObjectAnimator r1) {
        r0.checkAnimator = r1;
    }

    /* renamed from: -$$Nest$fputcheckedText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10009$$Nest$fputcheckedText(CheckBoxBase r0, String r1) {
        r0.checkedText = r1;
    }

    public CheckBoxBase(View r5, int r6, Theme.ResourcesProvider r7) {
        this.bounds = new Rect();
        this.rect = new RectF();
        this.checkScale = 1.0f;
        this.alpha = 1.0f;
        this.path = new Path();
        this.enabled = true;
        this.backgroundAlpha = 1.0f;
        this.checkColorKey = Theme.key_checkboxCheck;
        int r2 = Theme.key_chat_serviceBackground;
        this.backgroundColorKey = r2;
        this.background2ColorKey = r2;
        this.strokeBackgroundKey = Theme.key_dialogBackground;
        this.strokeBackgroundWidth = -1;
        this.customRadius = 0.0f;
        this.customRadiusFactor = 1.0f;
        this.drawUnchecked = true;
        this.circlePaintProvider = new CheckBoxBase$$ExternalSyntheticLambda0();
        this.animationDuration = 200;
        this.resourcesProvider = r7;
        this.parentView = r5;
        this.size = r6;
        if (paint != null) goto L5;
        paint = new Paint(1);
    L5:
        Paint r52 = new Paint(1);
        this.checkPaint = r52;
        r52.setStrokeCap(Paint.Cap.ROUND);
        Paint r53 = this.checkPaint;
        Paint.Style r62 = Paint.Style.STROKE;
        r53.setStyle(r62);
        this.checkPaint.setStrokeJoin(Paint.Join.ROUND);
        this.checkPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        Paint r54 = new Paint(1);
        this.backgroundPaint = r54;
        r54.setStyle(r62);
        this.backgroundPaint.setStrokeWidth(AndroidUtilities.dp(1.2f));
    }

    private void animateToCheckedState(boolean r3) {
        if (r3 == false) goto L4;
        float r32 = 1.0f;
    L5:
        ObjectAnimator r33 = ObjectAnimator.ofFloat(this, "progress", new float[]{r32});
        this.checkAnimator = r33;
        r33.addListener(new AnonymousClass1(this));
        this.checkAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.checkAnimator.setDuration(this.animationDuration);
        this.checkAnimator.start();
        return;
    L4:
        r32 = 0.0f;
        goto L5
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private void invalidate() {
        View r0 = this.parentView;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.getParent() == null) goto L8;
        ((View) this.parentView.getParent()).invalidate();
    L8:
        this.parentView.invalidate();
    }

    public void cancelCheckAnimator() {
        ObjectAnimator r0 = this.checkAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
        this.checkAnimator = null;
        return;
    }

    public void draw(Canvas r32) {
        float r1 = AndroidUtilities.dp(this.size / 2.0f);
        int r2 = this.backgroundType;
        if (r2 == 12) goto L10;
        if (r2 == 13) goto L10;
        if (r2 == 0) goto L9;
        if (r2 == 11) goto L9;
        float r14 = r1;
        float r15 = r1 - AndroidUtilities.dp(0.2f);
    L12:
        if (this.forbidden == false) goto L14;
        float r12 = 1.0f;
    L16:
        if (r12 < 0.5f) goto L18;
        float r17 = 1.0f;
    L19:
        int r3 = this.bounds.centerX();
        int r4 = this.bounds.centerY();
        if (this.cutCheck == true) goto L22;
    L29:
        boolean r19 = false;
    L30:
        if (r19 == false) goto L32;
        float r22 = r3;
        float r6 = r4;
        float r10 = r12;
        float r20 = 0.0f;
        int r11 = r3;
        int r24 = r4;
        Canvas r13 = r32;
        r13.saveLayerAlpha(r22 - r14, r6 - r14, r22 + r14, r6 + r14, 255, 31);
    L33:
        int r23 = this.backgroundColorKey;
        boolean r33 = this.drawUnchecked;
        if (r23 < 0) goto L56;
        if (r33 == false) goto L51;
        int r34 = this.backgroundType;
        if (r34 == 12) goto L50;
        if (r34 == 13) goto L50;
        if (r34 == 6) goto L49;
        if (r34 == 7) goto L49;
        if (r34 == 10) goto L48;
        if (r34 == 14) goto L48;
        paint.setColor((Theme.getServiceMessageColor() & 16777215) | 671088640);
        this.backgroundPaint.setColor(getThemedColor(this.checkColorKey));
    L47:
        float r26 = 1.0f;
    L71:
        if (this.drawUnchecked == false) goto L76;
        int r25 = this.backgroundType;
        if (r25 < 0) goto L76;
        if (r25 == 12) goto L76;
        if (r25 == 13) goto L76;
        if (r25 == 8) goto L82;
        if (r25 == 10) goto L82;
        if (r25 == 14) goto L82;
        if (r25 == 6) goto L85;
        if (r25 == 7) goto L85;
        int r35 = r24;
        r13.drawCircle(r11, r35, r14, paint);
    L87:
        int r9 = r35;
        float r30 = r26;
        int r122 = 7;
    L77:
        int r132 = -1;
    L93:
        paint.setColor(getThemedColor(this.checkColorKey));
        int r27 = this.backgroundType;
        if (r27 == r132) goto L135;
        if (r27 == r122) goto L135;
        if (r27 == 8) goto L135;
        if (r27 == 9) goto L135;
        if (r27 == 10) goto L135;
        if (r27 == 14) goto L135;
        if (r27 != 12) goto L106;
    L128:
        this.backgroundPaint.setStyle(Paint.Style.FILL);
        MessageDrawable r28 = this.messageDrawable;
        if (r28 != null) goto L131;
    L133:
        this.backgroundPaint.setShader(null);
    L134:
        r13.drawCircle(r11, r9, (r14 - AndroidUtilities.dp(r30)) * this.backgroundAlpha, this.backgroundPaint);
        this.backgroundPaint.setStyle(Paint.Style.STROKE);
        goto L135
    L131:
        if (r28.hasGradient() == false) goto L133;
        Shader r29 = this.messageDrawable.getGradientShader();
        Matrix r36 = this.messageDrawable.getMatrix();
        r36.reset();
        this.messageDrawable.applyMatrixScale();
        r36.postTranslate(r20, (-this.messageDrawable.getTopY()) + this.bounds.top);
        r29.setLocalMatrix(r36);
        this.backgroundPaint.setShader(r29);
        goto L134
    L106:
        if (r27 == 13) goto L128;
        if (r27 != 0) goto L110;
    L127:
        r13.drawCircle(r11, r9, r14, this.backgroundPaint);
    L137:
        if (r17 > r20) goto L139;
    L256:
        if (r19 == false) goto L259;
        r13.restore();
        return;
    L259:
        return;
    L139:
        if (r10 >= 0.5f) goto L141;
        float r102 = 0.0f;
    L142:
        int r210 = this.backgroundType;
        if (r210 != 9) goto L146;
        paint.setColor(getThemedColor(this.background2ColorKey));
    L169:
        if (this.forbidden == false) goto L172;
        paint.setColor(this.backgroundPaint.getColor());
    L175:
        if (this.useDefaultCheck == true) goto L179;
        int r211 = this.checkColorKey;
        if (r211 < 0) goto L179;
        this.checkPaint.setColor(getThemedColor(r211));
    L181:
        if (this.alpha >= r30) goto L186;
        if (Theme.isCurrentThemeDark() == false) goto L186;
        this.checkPaint.setColor(ColorUtils.blendARGB(paint.getColor(), this.checkPaint.getColor(), this.alpha));
    L186:
        if (this.backgroundType == (-1)) goto L216;
        float r123 = AndroidUtilities.dp(this.size) / 2.0f;
        int r133 = r13.save();
        r13.translate(r11 - r123, r9 - r123);
        if (r17 >= r30) goto L190;
        boolean r152 = true;
    L191:
        if (r152 == false) goto L193;
        r13.saveLayerAlpha(0.0f, 0.0f, AndroidUtilities.dp(this.size), AndroidUtilities.dp(this.size), 255, 31);
    L193:
        Paint r8 = this.circlePaintProvider.provide(null);
        int r16 = this.backgroundType;
        if (r16 != 12) goto L196;
    L197:
        r13 = r32;
        int r212 = r8.getAlpha();
        r8.setAlpha((int) (r17 * 255.0f));
        r13.drawCircle(r123, r123, r14 * r17, r8);
        if (r8 == paint) goto L214;
        r8.setAlpha(r212);
    L214:
        r13.restoreToCount(r133);
        goto L216
    L196:
        if (r16 == 13) goto L197;
        if (this.customRadius <= 0.0f) goto L206;
        float r142 = r14 - AndroidUtilities.dp(0.5f);
        float r62 = AndroidUtilities.lerp(r142, this.customRadius, this.customRadiusFactor);
        float r213 = r123 - r142;
        float r42 = r123 + r142;
        r32.drawRoundRect(r213, r213, r42, r42, r62, r62, r8);
        float r143 = r142 * (r30 - r17);
        float r63 = AndroidUtilities.lerp(r143, this.customRadius, this.customRadiusFactor);
        if (r152 == true) goto L203;
    L205:
        r13 = r32;
        goto L214
    L203:
        if (r143 <= 0.0f) goto L205;
        float r214 = r123 - r143;
        float r43 = r123 + r143;
        r13 = r32;
        r13.drawRoundRect(r214, r214, r43, r43, r63, r63, Theme.PAINT_CLEAR);
        goto L214
    L206:
        r13 = r32;
        float r144 = r14 - AndroidUtilities.dp(0.5f);
        r13.drawCircle(r123, r123, r144, r8);
        float r145 = r144 * (r30 - r17);
        if (r152 == false) goto L214;
        if (r145 <= 0.0f) goto L214;
        r13.drawCircle(r123, r123, r145, Theme.PAINT_CLEAR);
        goto L214
    L190:
        r152 = false;
    L216:
        if (this.forbidden == false) goto L222;
        if (forbidPaint != null) goto L220;
        Paint r215 = new Paint(1);
        forbidPaint = r215;
        r215.setStyle(Paint.Style.STROKE);
        forbidPaint.setStrokeCap(Paint.Cap.ROUND);
        forbidPaint.setStrokeJoin(Paint.Join.ROUND);
        forbidPaint.setPathEffect(new DashPathEffect(new float[]{AndroidUtilities.dp(0.66f), AndroidUtilities.dp(4.0f)}, 0.0f));
    L220:
        forbidPaint.setStrokeWidth(AndroidUtilities.dp(1.66f));
        forbidPaint.setColor(getThemedColor(Theme.key_switchTrack));
        r13.drawCircle(r11, r9, AndroidUtilities.dp(9.0f), forbidPaint);
        goto L256
    L222:
        if (r102 == 0.0f) goto L256;
        if (this.checkedText != null) goto L226;
        float r7 = r30;
        boolean r64 = true;
        this.path.reset();
        int r216 = this.backgroundType;
        if (r216 != (-1)) goto L243;
        float r82 = 1.4f;
    L246:
        float r217 = AndroidUtilities.dp(9.0f * r82) * r102;
        float r37 = AndroidUtilities.dp(r82 * 4.0f) * r102;
        int r44 = r11 - AndroidUtilities.dp(1.5f);
        int r5 = AndroidUtilities.dp(4.0f) + r9;
        float r38 = (float) Math.sqrt((r37 * r37) / 2.0f);
        float r45 = r44;
        float r52 = r5;
        this.path.moveTo(r45 - r38, r52 - r38);
        this.path.lineTo(r45, r52);
        float r218 = (float) Math.sqrt((r217 * r217) / 2.0f);
        this.path.lineTo(r45 + r218, r52 - r218);
        if (r19 == false) goto L249;
    L252:
        r13.save();
        float r219 = this.checkScale;
        r13.scale(r219, r219, r11, r9);
    L253:
        r13.drawPath(this.path, this.checkPaint);
        if (r64 == false) goto L256;
        r13.restore();
        goto L256
    L249:
        if (this.checkScale != r7) goto L252;
        r64 = false;
        goto L253
    L243:
        if (r216 != 5) goto L245;
        r82 = 0.8f;
        goto L246
    L245:
        r82 = r7;
        goto L246
    L226:
        if (this.textPaint != null) goto L228;
        int r65 = 1;
        TextPaint r220 = new TextPaint(1);
        this.textPaint = r220;
        r220.setTypeface(AndroidUtilities.bold());
    L229:
        int r221 = this.checkedText.length();
        if (r221 == 0) goto L237;
        if (r221 == r65) goto L237;
        if (r221 == 2) goto L237;
        if (r221 == 3) goto L236;
        float r222 = 8.0f;
        float r39 = 15.75f;
    L238:
        this.textPaint.setTextSize(AndroidUtilities.dp(r222));
        this.textPaint.setColor(getThemedColor(this.checkColorKey));
        r13.save();
        float r223 = r11;
        r13.scale(r102, r30, r223, r9);
        String r46 = this.checkedText;
        r13.drawText(r46, r223 - (this.textPaint.measureText(r46) / 2.0f), AndroidUtilities.dp(r39), this.textPaint);
        r13.restore();
        goto L256
    L236:
        r39 = 16.5f;
        r222 = 10.0f;
    L237:
        r222 = 14.0f;
        r39 = 18.0f;
        goto L238
    L228:
        r65 = 1;
    L179:
        this.checkPaint.setColor(getThemedColor(Theme.key_checkboxCheck));
        goto L181
    L172:
        if (this.alpha >= r30) goto L175;
        paint.setColor(ColorUtils.blendARGB(this.backgroundPaint.getColor(), paint.getColor(), this.alpha));
        goto L175
    L146:
        if (r210 != 11) goto L148;
    L167:
        paint.setColor(getThemedColor(this.backgroundColorKey));
        goto L169
    L148:
        if (r210 == 6) goto L167;
        if (r210 == r122) goto L167;
        if (r210 == 10) goto L167;
        if (this.drawUnchecked == true) goto L157;
        if (this.backgroundColorKey >= 0) goto L167;
    L157:
        if (r210 == 14) goto L167;
        int r224 = this.backgroundColor;
        if (r224 == 0) goto L162;
        paint.setColor(r224);
        goto L169
    L162:
        Paint r225 = paint;
        if (this.enabled == false) goto L165;
        int r310 = Theme.key_checkbox;
    L166:
        r225.setColor(getThemedColor(r310));
        goto L169
    L165:
        r310 = Theme.key_checkboxDisabled;
        goto L166
    L141:
        r102 = (r10 - 0.5f) / 0.5f;
        goto L142
    L110:
        if (r27 == 11) goto L127;
        float r53 = r11;
        float r311 = r9;
        this.rect.set(r53 - r15, r311 - r15, r53 + r15, r311 + r15);
        int r226 = this.backgroundType;
        if (r226 != 6) goto L117;
        int r153 = (int) ((-360.0f) * r10);
        int r312 = 0;
    L123:
        if (r226 != 6) goto L125;
        int r227 = getThemedColor(this.strokeBackgroundKey);
        int r47 = Color.alpha(r227);
        this.backgroundPaint.setColor(r227);
        this.backgroundPaint.setAlpha((int) (r47 * r10));
        int r54 = r312;
        int r134 = r54;
        r13.drawArc(this.rect, r54, r153, false, this.backgroundPaint);
        int r18 = getThemedColor(Theme.key_chat_attachPhotoBackground);
        int r228 = Color.alpha(r18);
        this.backgroundPaint.setColor(r18);
        this.backgroundPaint.setAlpha((int) (r228 * r10));
    L126:
        r13 = r32;
        r13.drawArc(this.rect, r134, r153, false, this.backgroundPaint);
        goto L137
    L125:
        r134 = r312;
        goto L126
    L117:
        if (r226 != 1) goto L119;
        r153 = (int) ((-360.0f) * r10);
        r312 = -90;
        goto L123
    L119:
        int r313 = (int) (360.0f * r10);
        if (LocaleController.isRTL == false) goto L122;
        r313 = -r313;
    L122:
        r153 = r313;
        r312 = 90;
    L135:
        r20 = 0.0f;
    L85:
        r35 = r24;
        float r229 = r11;
        float r48 = r35;
        r13.drawCircle(r229, r48, r14 - AndroidUtilities.dp(r26), paint);
        r13.drawCircle(r229, r48, r14 - AndroidUtilities.dp(1.5f), this.backgroundPaint);
    L82:
        int r314 = r24;
        if (this.customRadius <= r20) goto L92;
        float r230 = r14 - AndroidUtilities.dp(1.5f);
        float r49 = AndroidUtilities.lerp(r230, this.customRadius, this.customRadiusFactor);
        float r55 = r11;
        float r66 = r314;
        r30 = r26;
        r9 = r314;
        r122 = 7;
        r132 = -1;
        r13.drawRoundRect(r55 - r230, r66 - r230, r55 + r230, r66 + r230, r49, r49, this.backgroundPaint);
        goto L93
    L92:
        r9 = r314;
        r30 = r26;
        r122 = 7;
        r132 = -1;
        r13.drawCircle(r11, r9, r14 - AndroidUtilities.dp(1.5f), this.backgroundPaint);
    L76:
        r122 = 7;
        r9 = r24;
        r30 = r26;
    L48:
        this.backgroundPaint.setColor(getThemedColor(this.background2ColorKey));
    L49:
        paint.setColor(getThemedColor(this.background2ColorKey));
        this.backgroundPaint.setColor(getThemedColor(this.checkColorKey));
    L50:
        paint.setColor(getThemedColor(r23));
        paint.setAlpha((int) (this.backgroundAlpha * 255.0f));
        this.backgroundPaint.setColor(getThemedColor(this.checkColorKey));
        goto L47
    L51:
        Paint r231 = this.backgroundPaint;
        int r315 = this.background2ColorKey;
        if (r315 >= 0) goto L55;
        r315 = this.checkColorKey;
    L55:
        r26 = 1.0f;
        r231.setColor(AndroidUtilities.getOffsetColor(16777215, getThemedColor(r315), r10, this.backgroundAlpha));
        goto L71
    L56:
        r26 = 1.0f;
        if (r33 == false) goto L62;
        paint.setColor(Color.argb((int) (this.backgroundAlpha * 25.0f), 0, 0, 0));
        int r232 = this.backgroundType;
        Paint r316 = this.backgroundPaint;
        if (r232 != 8) goto L61;
        r316.setColor(getThemedColor(this.background2ColorKey));
        goto L71
    L61:
        r316.setColor(AndroidUtilities.getOffsetColor(-1, getThemedColor(this.checkColorKey), r10, this.backgroundAlpha));
        goto L71
    L62:
        int r233 = this.backgroundColor;
        Paint r317 = this.backgroundPaint;
        if (r233 == 0) goto L65;
        r317.setColor(0);
        goto L71
    L65:
        int r234 = this.background2ColorKey;
        if (r234 >= 0) goto L69;
        r234 = this.checkColorKey;
    L69:
        r317.setColor(AndroidUtilities.getOffsetColor(16777215, getThemedColor(r234), r10, this.backgroundAlpha));
        goto L71
    L32:
        r10 = r12;
        r11 = r3;
        r24 = r4;
        r20 = 0.0f;
        r13 = r32;
        goto L33
    L22:
        if (r17 <= 0.0f) goto L29;
        if (r12 < 0.5f) goto L29;
        if (this.forbidden == true) goto L29;
        if (this.checkedText != null) goto L29;
        r19 = true;
        goto L30
    L18:
        r17 = r12 / 0.5f;
        goto L19
    L14:
        r12 = this.progress;
    L9:
        r14 = r1;
        r15 = r14;
    L10:
        r1 = AndroidUtilities.dp(10.0f);
        goto L9
    }

    public boolean getDrawUnchecked() {
        return this.drawUnchecked;
    }

    public View getParentView() {
        return this.parentView;
    }

    @Keep
    public float getProgress() {
        return this.progress;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void onAttachedToWindow() {
        this.attachedToWindow = true;
    }

    public void onDetachedFromWindow() {
        this.attachedToWindow = false;
    }

    public void setAlpha(float r2) {
        if (this.alpha != r2) goto L5;
        return;
    L5:
        this.alpha = r2;
        invalidate();
    }

    public void setBackgroundAlpha(float r2) {
        if (this.backgroundAlpha != r2) goto L5;
        return;
    L5:
        this.backgroundAlpha = r2;
        invalidate();
    }

    public void setBackgroundColor(int r2) {
        if (this.backgroundColor != r2) goto L5;
        return;
    L5:
        this.backgroundColor = r2;
        invalidate();
    }

    public void setBackgroundDrawable(MessageDrawable r2) {
        if (this.messageDrawable != r2) goto L5;
        return;
    L5:
        this.messageDrawable = r2;
        invalidate();
    }

    public void setBackgroundType(int r5) {
        if (this.backgroundType != r5) goto L5;
        return;
    L5:
        this.backgroundType = r5;
        if (r5 != 12) goto L8;
    L22:
        this.backgroundPaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
    L23:
        invalidate();
        return;
    L8:
        if (r5 == 13) goto L22;
        if (r5 == 4) goto L19;
        if (r5 == 5) goto L19;
        if (r5 != 3) goto L17;
        this.backgroundPaint.setStrokeWidth(AndroidUtilities.dp(3.0f));
        goto L23
    L17:
        if (r5 == 0) goto L23;
        this.backgroundPaint.setStrokeWidth(AndroidUtilities.dp(1.5f));
    L19:
        this.backgroundPaint.setStrokeWidth(AndroidUtilities.dp(1.9f));
        if (r5 != 5) goto L23;
        this.checkPaint.setStrokeWidth(AndroidUtilities.dp(1.5f));
        goto L23
    }

    public void setBounds(int r3, int r4, int r5, int r6) {
        int r52 = r5 + r3;
        int r62 = r6 + r4;
        Rect r0 = this.bounds;
        if (r0.left == r3) goto L5;
    L11:
        r0.left = r3;
        r0.top = r4;
        r0.right = r52;
        r0.bottom = r62;
        invalidate();
        return;
    L5:
        if (r0.top != r4) goto L11;
        if (r0.right != r52) goto L11;
        if (r0.bottom != r62) goto L11;
    }

    public void setChecked(int r3, boolean r4, boolean r5) {
        if (r3 < 0) goto L9;
        String r32 = _UrlKt.FRAGMENT_ENCODE_SET + (r3 + 1);
        String r0 = this.checkedText;
        if (r0 != null) goto L6;
    L7:
        this.checkedText = r32;
        invalidate();
        goto L9
    L6:
        if (r0.equals(r32) == false) goto L7;
    L9:
        if (r4 != this.isChecked) goto L11;
        return;
    L11:
        this.isChecked = r4;
        if (this.attachedToWindow == false) goto L16;
        if (r5 == false) goto L16;
        animateToCheckedState(r4);
        return;
    L16:
        cancelCheckAnimator();
        if (r4 == false) goto L19;
        float r33 = 1.0f;
    L20:
        setProgress(r33);
        return;
    L19:
        r33 = 0.0f;
        goto L20
    }

    public void setChecked(boolean r2, boolean r3) {
        setChecked(-1, r2, r3);
    }

    public void setCirclePaintProvider(GenericProvider<Void, Paint> r2) {
        if (this.circlePaintProvider != r2) goto L5;
        return;
    L5:
        this.circlePaintProvider = r2;
        invalidate();
    }

    public void setColor(int r2, int r3, int r4) {
        if (this.backgroundColorKey == r2) goto L5;
    L9:
        this.backgroundColorKey = r2;
        this.background2ColorKey = r3;
        this.checkColorKey = r4;
        invalidate();
        return;
    L5:
        if (this.background2ColorKey != r3) goto L9;
        if (this.checkColorKey != r4) goto L9;
    }

    public void setCustomRadius(float r2) {
        if (this.customRadius != r2) goto L5;
        return;
    L5:
        this.customRadius = r2;
        invalidate();
    }

    public void setCustomRadiusFactor(float r2) {
        if (this.customRadiusFactor != r2) goto L5;
        return;
    L5:
        this.customRadiusFactor = r2;
        invalidate();
    }

    public void setCuttingCheck(boolean r3) {
        if (this.cutCheck != r3) goto L5;
        return;
    L5:
        this.cutCheck = r3;
        Paint r0 = this.checkPaint;
        if (r3 == false) goto L8;
        PorterDuffXfermode r32 = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    L9:
        r0.setXfermode(r32);
        invalidate();
        return;
    L8:
        r32 = null;
        goto L9
    }

    public void setDrawUnchecked(boolean r2) {
        if (this.drawUnchecked != r2) goto L5;
        return;
    L5:
        this.drawUnchecked = r2;
        invalidate();
    }

    public void setEnabled(boolean r2) {
        if (this.enabled != r2) goto L5;
        return;
    L5:
        this.enabled = r2;
        invalidate();
    }

    public void setForbidden(boolean r2) {
        if (this.forbidden != r2) goto L5;
        return;
    L5:
        this.forbidden = r2;
        invalidate();
    }

    public void setNum(int r3) {
        if (r3 < 0) goto L5;
        String r32 = _UrlKt.FRAGMENT_ENCODE_SET + (r3 + 1);
    L8:
        String r0 = this.checkedText;
        if (r0 != null) goto L13;
        if (r32 == null) goto L17;
    L15:
        this.checkedText = r32;
        invalidate();
        return;
    L17:
        return;
    L13:
        if (r0.equals(r32) == false) goto L15;
        return;
    L5:
        if (this.checkAnimator == null) goto L7;
        r32 = this.checkedText;
        goto L8
    L7:
        r32 = null;
        goto L8
    }

    public void setParentView(View r1) {
        this.parentView = r1;
    }

    @Keep
    public void setProgress(float r2) {
        if (this.progress == r2) goto L10;
        this.progress = r2;
        invalidate();
        ProgressDelegate r1 = this.progressDelegate;
        if (r1 == null) goto L9;
        r1.setProgress(r2);
        return;
    L9:
        return;
    }

    public void setProgressDelegate(ProgressDelegate r1) {
        this.progressDelegate = r1;
    }

    public void setResourcesProvider(Theme.ResourcesProvider r2) {
        if (this.resourcesProvider != r2) goto L5;
        return;
    L5:
        this.resourcesProvider = r2;
        invalidate();
    }

    public void setSize(float r2) {
        if (this.size != r2) goto L5;
        return;
    L5:
        this.size = r2;
        invalidate();
    }

    public void setStrokeBackgroundColor(int r2) {
        if (this.strokeBackgroundKey != r2) goto L5;
        return;
    L5:
        this.strokeBackgroundKey = r2;
        invalidate();
    }

    public void setUseDefaultCheck(boolean r2) {
        if (this.useDefaultCheck != r2) goto L5;
        return;
    L5:
        this.useDefaultCheck = r2;
        invalidate();
    }
}
