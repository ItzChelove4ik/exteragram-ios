package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.SystemClock;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import java.util.Collections;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public class VideoPlayerSeekBar {
    private static Paint paint;
    private static Paint strokePaint;
    private static int thumbWidth;
    private static Path tmpPath;
    private static float[] tmpRadii;
    private final float TIMESTAMP_GAP;
    private float animateFromBufferedProgress;
    private boolean animateResetBuffering;
    private AnimatedFloat animateThumbLoopBackProgress;
    private float animateThumbProgress;
    private float animatedThumbX;
    private int backgroundColor;
    private int backgroundSelectedColor;
    private float bufferedAnimationValue;
    private float bufferedProgress;
    private int cacheColor;
    private int circleColor;
    private float currentRadius;
    private int currentTimestamp;
    private SeekBarDelegate delegate;
    private int draggingThumbX;
    private int fromThumbX;
    private int height;
    private int horizontalPadding;
    private CharSequence lastCaption;
    private int lastTimestamp;
    private long lastTimestampUpdate;
    private long lastTimestampsAppearingUpdate;
    private long lastUpdateTime;
    private long lastVideoDuration;
    private float lastWidth;
    private int lineHeight;
    private float loopBackWasThumbX;
    private View parentView;
    private boolean pressed;
    private boolean pressedDelayed;
    private float progress;
    private int progressColor;
    private RectF rect;
    private boolean selected;
    private int smallLineColor;
    private int smallLineHeight;
    private int thumbDX;
    private int thumbX;
    private int timestampChangeDirection;
    private float timestampChangeT;
    private StaticLayout[] timestampLabel;
    private TextPaint timestampLabelPaint;
    private ArrayList<Pair<Float, CharSequence>> timestamps;
    private float timestampsAppearing;
    private float transitionProgress;
    private int width;

    public interface SeekBarDelegate {
        void onSeekBarContinuousDrag(float r1);

        void onSeekBarDrag(float r1);
    }

    public static /* synthetic */ void $r8$lambda$ZomRFpctdt61zmHEUTRZWmNMx6s(VideoPlayerSeekBar r0) {
        r0.lambda$onTouch$0();
    }

    /* renamed from: $r8$lambda$o3EHnMr9BcAI-6WfiUzSrQzpwFA, reason: not valid java name */
    public static /* synthetic */ int m13512$r8$lambda$o3EHnMr9BcAI6WfiUzSrQzpwFA(Pair r2, Pair r3) {
        if (((Float) r2.first).floatValue() <= ((Float) r3.first).floatValue()) goto L7;
        return 1;
    L7:
        if (((Float) r3.first).floatValue() <= ((Float) r2.first).floatValue()) goto L10;
        return -1;
    L10:
        return 0;
    }

    public VideoPlayerSeekBar(View r10) {
        this.thumbX = 0;
        this.animatedThumbX = 0.0f;
        this.draggingThumbX = 0;
        this.thumbDX = 0;
        this.pressed = false;
        this.pressedDelayed = false;
        this.rect = new RectF();
        this.bufferedAnimationValue = 1.0f;
        this.lineHeight = AndroidUtilities.dp(4.0f);
        this.smallLineHeight = AndroidUtilities.dp(2.0f);
        this.fromThumbX = 0;
        this.animateThumbProgress = 1.0f;
        this.timestampsAppearing = 0.0f;
        this.TIMESTAMP_GAP = 1.0f;
        this.currentTimestamp = -1;
        this.lastTimestamp = -1;
        this.timestampChangeT = 1.0f;
        this.lastWidth = -1.0f;
        if (paint != null) goto L5;
        paint = new Paint(1);
        Paint r0 = new Paint(1);
        strokePaint = r0;
        r0.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(-16777216);
        strokePaint.setStrokeWidth(1.0f);
    L5:
        this.parentView = r10;
        thumbWidth = AndroidUtilities.dp(24.0f);
        this.currentRadius = AndroidUtilities.dp(6.0f);
        this.animateThumbLoopBackProgress = new AnimatedFloat(0.0f, r10, 0, 300, CubicBezierInterpolator.EASE_OUT_QUINT);
    }

    private void drawProgressBar(Canvas r26, RectF r27, Paint r28) {
        VideoPlayerSeekBar r0 = this;
        char r5 = 2;
        boolean r6 = true;
        float r4 = AndroidUtilities.dp(AndroidUtilities.lerp(2, 1, r0.transitionProgress));
        ArrayList<Pair<Float, CharSequence>> r7 = r0.timestamps;
        if (r7 != null) goto L5;
    L87:
        r26.drawRoundRect(r27, r4, r4, r28);
        return;
    L5:
        if (r7.isEmpty() == true) goto L87;
        float r72 = r27.bottom;
        float r11 = 0.0f;
        float r73 = r0.horizontalPadding + AndroidUtilities.lerp(thumbWidth / 2.0f, 0.0f, r0.transitionProgress);
        float r8 = r0.horizontalPadding + AndroidUtilities.lerp(r0.width - (thumbWidth / 2.0f), r0.parentView.getWidth() - (r0.horizontalPadding * 2.0f), r0.transitionProgress);
        AndroidUtilities.rectTmp.set(r27);
        float r12 = 1.0f;
        float r10 = AndroidUtilities.dp(r0.timestampsAppearing * 1.0f) / 2.0f;
        if (tmpPath != null) goto L10;
        tmpPath = new Path();
    L10:
        tmpPath.reset();
        float r9 = AndroidUtilities.dp(4.0f) / (r8 - r73);
        int r14 = 0;
    L11:
        int r16 = -1;
        if (r14 >= r0.timestamps.size()) goto L17;
        if (((Float) r0.timestamps.get(r14).first).floatValue() >= r9) goto L18;
        r14 = r14 + 1;
    L18:
        if (r14 >= 0) goto L20;
        r14 = 0;
    L20:
        int r15 = r0.timestamps.size() - 1;
    L21:
        char r17 = r5;
        if (r15 < 0) goto L27;
        if ((1.0f - ((Float) r0.timestamps.get(r15).first).floatValue()) >= r9) goto L25;
        r15 = r15 - 1;
        r5 = r17;
        goto L21
    L25:
        r16 = r15 + 1;
    L27:
        if (r16 >= 0) goto L29;
        r16 = r0.timestamps.size();
    L29:
        int r52 = r16;
        int r152 = r14;
    L30:
        if (r152 > r52) goto L85;
        boolean r162 = r6;
        if (r152 != r14) goto L34;
        float r62 = r11;
    L35:
        if (r152 != r52) goto L37;
        float r112 = r12;
    L38:
        if (r152 == r52) goto L45;
        if (r152 == 0) goto L45;
        if (r152 >= (r0.timestamps.size() - 1)) goto L45;
        if ((((Float) r0.timestamps.get(r152).first).floatValue() - r62) > r9) goto L45;
        r152 = r152 + 1;
        r112 = ((Float) r0.timestamps.get(r152).first).floatValue();
    L45:
        RectF r122 = AndroidUtilities.rectTmp;
        float r63 = AndroidUtilities.lerp(r73, r8, r62);
        if (r152 <= 0) goto L48;
        float r18 = r10;
    L49:
        r122.left = r63 + r18;
        float r64 = AndroidUtilities.lerp(r73, r8, r112);
        if (r152 >= r52) goto L52;
        float r113 = r10;
    L53:
        float r65 = r64 - r113;
        r122.right = r65;
        float r114 = r27.right;
        if (r65 <= r114) goto L56;
        boolean r66 = r162 ? 1 : 0;
    L57:
        if (r66 == false) goto L59;
        r122.right = r114;
    L59:
        float r115 = r122.right;
        float r13 = r27.left;
        if (r115 < r13) goto L84;
        if (r122.left >= r13) goto L66;
        r122.left = r13;
    L66:
        if (tmpRadii != null) goto L69;
        tmpRadii = new float[8];
    L69:
        if (r152 == r14) goto L79;
        if (r66 == false) goto L74;
        char r24 = 4;
        if (r122.left >= r27.left) goto L80;
    L75:
        float r116 = r0.timestampsAppearing;
        if (r152 < r52) goto L78;
        float[] r132 = tmpRadii;
        float r20 = (0.7f * r4) * r116;
        r132[7] = r20;
        r132[6] = r20;
        r132[r162 ? 1 : 0] = r20;
        r132[0] = r20;
        r132[5] = r4;
        r132[r24] = r4;
        r132[3] = r4;
        r132[r17] = r4;
    L81:
        tmpPath.addRoundRect(r122, tmpRadii, Path.Direction.CW);
        if (r66 == false) goto L84;
    L78:
        float[] r133 = tmpRadii;
        float r202 = (0.7f * r4) * r116;
        r133[5] = r202;
        r133[r24] = r202;
        r133[3] = r202;
        r133[r17] = r202;
        r133[7] = r202;
        r133[6] = r202;
        r133[r162 ? 1 : 0] = r202;
        r133[0] = r202;
    L80:
        float[] r117 = tmpRadii;
        r117[7] = r4;
        r117[6] = r4;
        r117[r162 ? 1 : 0] = r4;
        r117[0] = r4;
        float r203 = (0.7f * r4) * r0.timestampsAppearing;
        r117[5] = r203;
        r117[r24] = r203;
        r117[3] = r203;
        r117[r17] = r203;
        goto L81
    L74:
        r24 = 4;
        goto L75
    L79:
        r24 = 4;
    L84:
        r152 = r152 + 1;
        r11 = 0.0f;
        r12 = 1.0f;
        r0 = this;
        r6 = r162 ? 1 : 0;
        goto L30
    L56:
        r66 = false;
        goto L57
    L52:
        r113 = 0.0f;
        goto L53
    L48:
        r18 = 0.0f;
        goto L49
    L37:
        r112 = ((Float) r0.timestamps.get(r152).first).floatValue();
        goto L38
    L34:
        r62 = ((Float) r0.timestamps.get(r152 - 1).first).floatValue();
    L85:
        r26.drawPath(tmpPath, r28);
        return;
    L17:
        r14 = -1;
        goto L18
    }

    private void drawTimestampLabel(Canvas r18) {
        ArrayList<Pair<Float, CharSequence>> r2 = this.timestamps;
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.isEmpty() == false) goto L8;
        return;
    L8:
        if (this.pressed == false) goto L10;
    L14:
        float r22 = this.draggingThumbX;
    L13:
        float r23 = r22 / (this.width - thumbWidth);
        int r3 = this.timestamps.size() - 1;
    L17:
        if (r3 < 0) goto L22;
        if ((((Float) this.timestamps.get(r3).first).floatValue() - 0.001f) <= r23) goto L24;
        r3 = r3 - 1;
    L24:
        if (this.timestampLabel != null) goto L26;
        this.timestampLabel = new StaticLayout[2];
    L26:
        float r24 = this.horizontalPadding + AndroidUtilities.lerp(thumbWidth / 2.0f, 0.0f, this.transitionProgress);
        float r6 = this.horizontalPadding + AndroidUtilities.lerp(this.width - (thumbWidth / 2.0f), this.parentView.getWidth() - (this.horizontalPadding * 2.0f), this.transitionProgress);
        float r8 = this.horizontalPadding + (this.width - (thumbWidth / 2.0f));
        float r10 = Math.abs(r24 - r8) - AndroidUtilities.dp(16.0f);
        float r12 = this.lastWidth;
        if (r12 > 0.0f) goto L29;
    L36:
        this.lastWidth = r10;
        if (r3 == this.currentTimestamp) goto L62;
        StaticLayout[] r122 = this.timestampLabel;
        r122[1] = r122[0];
        if (this.pressed == false) goto L42;
        AndroidUtilities.vibrateCursor(this.parentView);
    L42:
        if (r3 >= 0) goto L44;
    L49:
        this.timestampLabel[0] = null;
    L50:
        this.timestampChangeT = 0.0f;
        if (r3 != (-1)) goto L53;
        this.timestampChangeDirection = -1;
    L60:
        this.lastTimestamp = this.currentTimestamp;
        this.currentTimestamp = r3;
        goto L62
    L53:
        int r102 = this.currentTimestamp;
        if (r102 != (-1)) goto L56;
        this.timestampChangeDirection = 1;
        goto L60
    L56:
        if (r3 >= r102) goto L58;
        this.timestampChangeDirection = -1;
        goto L60
    L58:
        if (r3 <= r102) goto L60;
        this.timestampChangeDirection = 1;
        goto L60
    L44:
        if (r3 >= this.timestamps.size()) goto L49;
        CharSequence r13 = (CharSequence) this.timestamps.get(r3).second;
        StaticLayout[] r15 = this.timestampLabel;
        if (r13 != null) goto L48;
        r15[0] = null;
        goto L50
    L48:
        r15[0] = makeStaticLayout(r13, (int) r10);
    L62:
        if (this.timestampChangeT >= 1.0f) goto L68;
        float r103 = r8;
        long r7 = Math.min(17, Math.abs(SystemClock.elapsedRealtime() - this.lastTimestampUpdate));
        float r16 = 2.0f;
        if (this.timestamps.size() <= 8) goto L66;
        float r32 = 160.0f;
    L67:
        this.timestampChangeT = Math.min(this.timestampChangeT + (r7 / r32), 1.0f);
        this.parentView.invalidate();
        this.lastTimestampUpdate = SystemClock.elapsedRealtime();
    L70:
        if (this.timestampsAppearing >= 1.0f) goto L72;
        char r33 = 0;
        this.timestampsAppearing = Math.min(this.timestampsAppearing + (Math.min(17, Math.abs(SystemClock.elapsedRealtime() - this.lastTimestampUpdate)) / 200.0f), 1.0f);
        this.parentView.invalidate();
        this.lastTimestampsAppearingUpdate = SystemClock.elapsedRealtime();
    L73:
        float r72 = CubicBezierInterpolator.DEFAULT.getInterpolation(this.timestampChangeT);
        r18.save();
        int r82 = this.height;
        r18.translate(r24 + ((r6 - r103) * this.transitionProgress), AndroidUtilities.lerp((this.lineHeight + r82) / r16, r82 - AndroidUtilities.dp(3.0f), this.transitionProgress) + AndroidUtilities.dp(12.0f));
        if (this.timestampLabel[1] == null) goto L80;
        r18.save();
        if (this.timestampChangeDirection == 0) goto L78;
        r18.translate(AndroidUtilities.dp(8.0f) + ((AndroidUtilities.dp(16.0f) * (-this.timestampChangeDirection)) * r72), 0.0f);
    L78:
        r18.translate(0.0f, (-this.timestampLabel[1].getHeight()) / r16);
        this.timestampLabelPaint.setAlpha((int) ((((1.0f - this.transitionProgress) * 255.0f) * (1.0f - r72)) * this.timestampsAppearing));
        this.timestampLabel[1].draw(r18);
        r18.restore();
    L80:
        if (this.timestampLabel[r33] == null) goto L85;
        r18.save();
        if (this.timestampChangeDirection == 0) goto L84;
        r18.translate(AndroidUtilities.dp(8.0f) + ((AndroidUtilities.dp(16.0f) * this.timestampChangeDirection) * (1.0f - r72)), 0.0f);
    L84:
        r18.translate(0.0f, (-this.timestampLabel[r33].getHeight()) / r16);
        this.timestampLabelPaint.setAlpha((int) ((((1.0f - this.transitionProgress) * 255.0f) * r72) * this.timestampsAppearing));
        this.timestampLabel[r33].draw(r18);
        r18.restore();
    L85:
        r18.restore();
        return;
    L72:
        r33 = 0;
        goto L73
    L66:
        r32 = 220.0f;
        goto L67
    L68:
        r16 = 2.0f;
        r103 = r8;
        goto L70
    L29:
        if (Math.abs(r12 - r10) <= 0.01f) goto L36;
        StaticLayout[] r123 = this.timestampLabel;
        StaticLayout r132 = r123[0];
        if (r132 == null) goto L33;
        r123[0] = makeStaticLayout(r132.getText(), (int) r10);
    L33:
        StaticLayout[] r124 = this.timestampLabel;
        StaticLayout r133 = r124[1];
        if (r133 == null) goto L36;
        r124[1] = makeStaticLayout(r133.getText(), (int) r10);
        goto L36
    L22:
        r3 = -1;
        goto L24
    L10:
        if (this.pressedDelayed == true) goto L14;
        r22 = this.animatedThumbX;
        goto L13
    }

    private /* synthetic */ void lambda$onTouch$0() {
        this.pressedDelayed = false;
    }

    private StaticLayout makeStaticLayout(CharSequence r4, int r5) {
        if (this.timestampLabelPaint != null) goto L5;
        TextPaint r0 = new TextPaint(1);
        this.timestampLabelPaint = r0;
        r0.setTextSize(AndroidUtilities.dp(12.0f));
        this.timestampLabelPaint.setColor(-1);
    L5:
        if (r4 != null) goto L8;
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        return StaticLayout.Builder.obtain(r4, 0, r4.length(), this.timestampLabelPaint, r5).setMaxLines(1).setAlignment(Layout.Alignment.ALIGN_CENTER).setEllipsize(TextUtils.TruncateAt.END).setEllipsizedWidth(Math.min(AndroidUtilities.dp(400.0f), r5)).build();
    }

    private void setPaintColor(int r1, float r2) {
        if (r2 >= 1.0f) goto L5;
        r1 = ColorUtils.setAlphaComponent(r1, (int) (Color.alpha(r1) * r2));
    L5:
        paint.setColor(r1);
    }

    public void clearTimestamps() {
        this.timestamps = null;
        this.currentTimestamp = -1;
        this.timestampsAppearing = 0.0f;
        StaticLayout[] r1 = this.timestampLabel;
        if (r1 == null) goto L5;
        r1[1] = null;
        r1[0] = null;
    L5:
        this.lastCaption = null;
        this.lastVideoDuration = -1;
    }

    public void draw(Canvas r18, View r19) {
        this.rect.left = this.horizontalPadding + AndroidUtilities.lerp(thumbWidth / 2.0f, 0.0f, this.transitionProgress);
        RectF r2 = this.rect;
        int r3 = this.height;
        r2.top = AndroidUtilities.lerp((r3 - this.lineHeight) / 2.0f, (r3 - AndroidUtilities.dp(3.0f)) - this.smallLineHeight, this.transitionProgress);
        RectF r22 = this.rect;
        int r32 = this.height;
        r22.bottom = AndroidUtilities.lerp((this.lineHeight + r32) / 2.0f, r32 - AndroidUtilities.dp(3.0f), this.transitionProgress);
        float r23 = this.thumbX;
        float r33 = Math.min(this.animatedThumbX, r23);
        this.animatedThumbX = r33;
        float r34 = AndroidUtilities.lerp(r33, r23, 0.5f);
        this.animatedThumbX = r34;
        if (Math.abs(r23 - r34) <= 0.005f) goto L5;
        this.parentView.invalidate();
    L5:
        float r24 = this.animatedThumbX;
        float r35 = this.animateThumbProgress;
        if (r35 == 1.0f) goto L11;
        float r36 = r35 + 0.07272727f;
        this.animateThumbProgress = r36;
        if (r36 < 1.0f) goto L10;
        this.animateThumbProgress = 1.0f;
        goto L11
    L10:
        r19.invalidate();
        float r37 = CubicBezierInterpolator.DEFAULT.getInterpolation(this.animateThumbProgress);
        r24 = (r24 * r37) + (this.fromThumbX * (1.0f - r37));
    L11:
        float r38 = this.animateThumbLoopBackProgress.set(0.0f);
        if (this.pressed == false) goto L14;
        r38 = 0.0f;
    L14:
        this.rect.right = this.horizontalPadding + AndroidUtilities.lerp(this.width - (thumbWidth / 2.0f), this.parentView.getWidth() - (this.horizontalPadding * 2.0f), this.transitionProgress);
        if (this.selected == false) goto L17;
        int r6 = this.backgroundSelectedColor;
    L18:
        setPaintColor(r6, 1.0f - this.transitionProgress);
        drawProgressBar(r18, this.rect, paint);
        float r62 = this.bufferedAnimationValue;
        if (r62 == 1.0f) goto L24;
        float r63 = r62 + 0.16f;
        this.bufferedAnimationValue = r63;
        if (r63 <= 1.0f) goto L23;
        this.bufferedAnimationValue = 1.0f;
        goto L24
    L23:
        this.parentView.invalidate();
    L24:
        boolean r64 = this.animateResetBuffering;
        float r8 = this.animateFromBufferedProgress;
        if (r64 == true) goto L27;
        float r65 = this.bufferedAnimationValue;
        float r82 = (r8 * (1.0f - r65)) + (this.bufferedProgress * r65);
        if (r82 <= 0.0f) goto L48;
        this.rect.right = this.horizontalPadding + AndroidUtilities.lerp((thumbWidth / 2.0f) + (r82 * (this.width - r10)), this.parentView.getWidth() - (this.horizontalPadding * 2.0f), this.transitionProgress);
        if (this.selected == false) goto L45;
        int r66 = this.backgroundSelectedColor;
    L46:
        setPaintColor(r66, 1.0f - this.transitionProgress);
        drawProgressBar(r18, this.rect, paint);
        goto L48
    L45:
        r66 = this.cacheColor;
    L48:
        if (this.pressed == false) goto L50;
        float r67 = 8.0f;
    L51:
        float r68 = AndroidUtilities.dp(r67);
        if (this.currentRadius == r68) goto L67;
        long r83 = SystemClock.elapsedRealtime();
        long r10 = r83 - this.lastUpdateTime;
        this.lastUpdateTime = r83;
        if (r10 <= 18) goto L56;
        r10 = 16;
    L56:
        float r84 = this.currentRadius;
        if (r84 >= r68) goto L61;
        float r85 = r84 + (AndroidUtilities.dp(1.0f) * (r10 / 60.0f));
        this.currentRadius = r85;
        if (r85 <= r68) goto L64;
        this.currentRadius = r68;
    L64:
        View r69 = this.parentView;
        if (r69 == null) goto L67;
        r69.invalidate();
        goto L67
    L61:
        float r86 = r84 - (AndroidUtilities.dp(1.0f) * (r10 / 60.0f));
        this.currentRadius = r86;
        if (r86 >= r68) goto L64;
        this.currentRadius = r68;
    L67:
        float r610 = AndroidUtilities.lerp(this.currentRadius, 0.0f, this.transitionProgress);
        int r9 = 0;
        if (r38 <= 0.0f) goto L79;
        RectF r87 = this.rect;
        float r12 = r87.left;
        float r16 = 2.0f;
        r87.right = this.horizontalPadding + AndroidUtilities.lerp((thumbWidth / 2.0f) + (this.width - r14), this.parentView.getWidth() - (this.horizontalPadding * 2.0f), this.transitionProgress);
        RectF r5 = this.rect;
        r5.left = AndroidUtilities.lerp(r12, r5.right, 1.0f - r38);
        if (this.transitionProgress > 0.0f) goto L72;
    L74:
        setPaintColor(ColorUtils.blendARGB(this.progressColor, this.smallLineColor, this.transitionProgress), 1.0f);
        drawProgressBar(r18, this.rect, paint);
        this.rect.left = r12;
        int r52 = this.circleColor;
        if (getProgress() != 0.0f) goto L77;
        int r88 = 0;
    L78:
        setPaintColor(ColorUtils.blendARGB(r52, r88, this.transitionProgress), 1.0f - this.transitionProgress);
        r18.drawCircle(this.horizontalPadding + AndroidUtilities.lerp((thumbWidth / 2.0f) + this.loopBackWasThumbX, (this.parentView.getWidth() - (this.horizontalPadding * 2.0f)) * (this.loopBackWasThumbX / (this.width - thumbWidth)), this.transitionProgress), this.rect.centerY(), r610 * r38, paint);
    L80:
        RectF r53 = this.rect;
        float r89 = this.horizontalPadding;
        float r122 = thumbWidth / r16;
        if (this.pressed == false) goto L83;
        r24 = this.draggingThumbX;
    L83:
        r53.right = r89 + AndroidUtilities.lerp(r122 + r24, (this.parentView.getWidth() - (this.horizontalPadding * r16)) * getProgress(), this.transitionProgress);
        if (this.transitionProgress > 0.0f) goto L86;
    L88:
        setPaintColor(ColorUtils.blendARGB(this.progressColor, this.smallLineColor, this.transitionProgress), 1.0f);
        drawProgressBar(r18, this.rect, paint);
        int r25 = this.circleColor;
        if (getProgress() == 0.0f) goto L92;
        r9 = this.smallLineColor;
    L92:
        setPaintColor(ColorUtils.blendARGB(r25, r9, this.transitionProgress), 1.0f - this.transitionProgress);
        RectF r26 = this.rect;
        r18.drawCircle(r26.right, r26.centerY(), r610 * (1.0f - r38), paint);
        drawTimestampLabel(r18);
        return;
    L86:
        if (this.rect.width() <= 0.0f) goto L88;
        strokePaint.setAlpha((int) ((this.transitionProgress * 255.0f) * 0.2f));
        drawProgressBar(r18, this.rect, strokePaint);
        goto L88
    L77:
        r88 = this.smallLineColor;
        goto L78
    L72:
        if (this.rect.width() <= 0.0f) goto L74;
        strokePaint.setAlpha((int) ((this.transitionProgress * 255.0f) * 0.2f));
        drawProgressBar(r18, this.rect, strokePaint);
        goto L74
    L79:
        r16 = 2.0f;
        goto L80
    L50:
        r67 = 6.0f;
        goto L51
    L27:
        if (r8 <= 0.0f) goto L33;
        this.rect.right = this.horizontalPadding + AndroidUtilities.lerp((thumbWidth / 2.0f) + (r8 * (this.width - r10)), this.parentView.getWidth() - (this.horizontalPadding * 2.0f), this.transitionProgress);
        if (this.selected == false) goto L31;
        int r611 = this.backgroundSelectedColor;
    L32:
        setPaintColor(r611, (1.0f - this.transitionProgress) * (1.0f - this.bufferedAnimationValue));
        drawProgressBar(r18, this.rect, paint);
        goto L33
    L31:
        r611 = this.cacheColor;
    L33:
        float r612 = this.bufferedProgress;
        if (r612 <= 0.0f) goto L48;
        this.rect.right = this.horizontalPadding + AndroidUtilities.lerp((thumbWidth / 2.0f) + (r612 * (this.width - r10)), this.parentView.getWidth() - (this.horizontalPadding * 2.0f), this.transitionProgress);
        if (this.selected == false) goto L38;
        int r613 = this.backgroundSelectedColor;
    L39:
        setPaintColor(r613, 1.0f - this.transitionProgress);
        drawProgressBar(r18, this.rect, paint);
        goto L48
    L38:
        r613 = this.cacheColor;
        goto L39
    L17:
        r6 = this.backgroundColor;
        goto L18
    }

    public float getProgress() {
        return this.thumbX / (this.width - thumbWidth);
    }

    public int getThumbX() {
        if (this.pressed == false) goto L5;
        int r1 = this.draggingThumbX;
    L7:
        return r1 + (thumbWidth / 2);
    L5:
        r1 = this.thumbX;
        goto L7
    }

    public int getWidth() {
        return this.width - thumbWidth;
    }

    public boolean isDragging() {
        return this.pressed;
    }

    public boolean onTouch(int r8, float r9, float r10) {
        if (r8 == 0) goto L5;
        if (r8 == 1) goto L46;
        if (r8 == 3) goto L46;
        if (r8 == 2) goto L34;
    L54:
        return false;
    L34:
        if (this.pressed == false) goto L54;
        int r82 = (int) (r9 - this.thumbDX);
        this.draggingThumbX = r82;
        if (r82 >= 0) goto L38;
        this.draggingThumbX = 0;
    L41:
        SeekBarDelegate r83 = this.delegate;
        if (r83 == null) goto L44;
        r83.onSeekBarContinuousDrag(this.draggingThumbX / (this.width - thumbWidth));
    L44:
        return true;
    L38:
        int r92 = this.width;
        int r102 = thumbWidth;
        if (r82 <= (r92 - r102)) goto L41;
        this.draggingThumbX = r92 - r102;
    L46:
        if (this.pressed == false) goto L54;
        int r93 = this.draggingThumbX;
        this.thumbX = r93;
        this.animatedThumbX = r93;
        if (r8 != 1) goto L52;
        SeekBarDelegate r84 = this.delegate;
        if (r84 == null) goto L52;
        r84.onSeekBarDrag(r93 / (this.width - thumbWidth));
    L52:
        this.pressed = false;
        AndroidUtilities.runOnUIThread(new VideoPlayerSeekBar$$ExternalSyntheticLambda1(this), 50);
        return true;
    L5:
        if (this.transitionProgress <= 0.0f) goto L7;
        return false;
    L7:
        int r85 = this.height;
        int r4 = thumbWidth;
        int r5 = (r85 - r4) / 2;
        if (r9 < (-r5)) goto L54;
        int r0 = this.width;
        if (r9 > (r0 + r5)) goto L54;
        if (r10 < 0.0f) goto L54;
        if (r10 > r85) goto L54;
        int r86 = this.thumbX;
        if ((r86 - r5) <= r9) goto L18;
    L19:
        int r87 = ((int) r9) - (r4 / 2);
        this.thumbX = r87;
        if (r87 >= 0) goto L23;
        this.thumbX = 0;
    L25:
        this.animatedThumbX = this.thumbX;
    L26:
        this.pressedDelayed = true;
        this.pressed = true;
        int r88 = this.thumbX;
        this.draggingThumbX = r88;
        this.thumbDX = (int) (r9 - r88);
        return true;
    L23:
        if (r87 <= (r0 - r4)) goto L25;
        this.thumbX = r4 - r0;
        goto L25
    L18:
        if (r9 <= ((r86 + r4) + r5)) goto L26;
        goto L19
    }

    public void setBufferedProgress(float r3) {
        float r0 = this.bufferedProgress;
        if (r3 == r0) goto L10;
        this.animateFromBufferedProgress = r0;
        if (r3 >= r0) goto L7;
        boolean r02 = true;
    L8:
        this.animateResetBuffering = r02;
        this.bufferedProgress = r3;
        this.bufferedAnimationValue = 0.0f;
        return;
    L7:
        r02 = false;
        goto L8
    }

    public void setColors(int r1, int r2, int r3, int r4, int r5, int r6) {
        this.backgroundColor = r1;
        this.cacheColor = r2;
        this.circleColor = r4;
        this.progressColor = r3;
        this.backgroundSelectedColor = r5;
        this.smallLineColor = r6;
    }

    public void setDelegate(SeekBarDelegate r1) {
        this.delegate = r1;
    }

    public void setHorizontalPadding(int r1) {
        this.horizontalPadding = r1;
    }

    public void setProgress(float r2) {
        setProgress(r2, false);
    }

    public void setProgress(float r5, boolean r6) {
        if (Math.abs(this.progress - 1.0f) < 0.04f) goto L5;
    L7:
        this.progress = r5;
        int r52 = (int) Math.ceil((this.width - thumbWidth) * r5);
        if (r6 == true) goto L10;
    L15:
        this.thumbX = r52;
        if (r52 >= 0) goto L18;
        this.thumbX = 0;
    L22:
        if (Math.abs(this.animatedThumbX - this.thumbX) <= AndroidUtilities.dp(8.0f)) goto L25;
        this.animatedThumbX = this.thumbX;
        return;
    L25:
        return;
    L18:
        int r62 = this.width;
        int r0 = thumbWidth;
        if (r52 <= (r62 - r0)) goto L22;
        this.thumbX = r62 - r0;
        goto L22
    L10:
        if (Math.abs(r52 - this.thumbX) <= AndroidUtilities.dp(10.0f)) goto L13;
        float r63 = CubicBezierInterpolator.DEFAULT.getInterpolation(this.animateThumbProgress);
        this.fromThumbX = (int) ((this.thumbX * r63) + (this.fromThumbX * (1.0f - r63)));
        this.animateThumbProgress = 0.0f;
        goto L15
    L13:
        if (this.animateThumbProgress != 1.0f) goto L15;
        this.animateThumbProgress = 0.0f;
        this.fromThumbX = this.thumbX;
        goto L15
    L5:
        if (Math.abs(r5) >= 0.04f) goto L7;
        this.animateThumbLoopBackProgress.set(1.0f, true);
        this.loopBackWasThumbX = this.thumbX;
        goto L7
    }

    public void setSize(int r1, int r2) {
        this.width = r1;
        this.height = r2;
        View r0 = this.parentView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    public void setTransitionProgress(float r2) {
        if (this.transitionProgress == r2) goto L6;
        this.transitionProgress = r2;
        this.parentView.invalidate();
        return;
    }

    public void updateTimestamps(MessageObject r9, long r10) {
        if (r9 != null) goto L4;
    L52:
        clearTimestamps();
        return;
    L4:
        if (r10 < 0) goto L52;
        CharSequence r0 = r9.caption;
        if (r9.isYouTubeVideo() == false) goto L15;
        if (r9.youtubeDescription != null) goto L13;
        String r02 = r9.messageOwner.media.webpage.description;
        if (r02 == null) goto L13;
        r9.youtubeDescription = SpannableString.valueOf(r02);
        MessageObject.addUrlsByPattern(r9.isOut(), r9.youtubeDescription, false, 3, (int) r10, false);
    L13:
        r0 = r9.youtubeDescription;
    L15:
        if (r0 == this.lastCaption) goto L17;
    L19:
        this.lastCaption = r0;
        this.lastVideoDuration = r10;
        if ((r0 instanceof Spanned) == true) goto L25;
        this.timestamps = null;
        this.currentTimestamp = -1;
        this.timestampsAppearing = 0.0f;
        StaticLayout[] r8 = this.timestampLabel;
        if (r8 == null) goto L63;
        r8[1] = null;
        r8[0] = null;
        return;
    L63:
        return;
    L25:
        Spanned r03 = (Spanned) r0;
        URLSpanNoUnderline[] r92 = (URLSpanNoUnderline[]) r03.getSpans(0, r03.length(), URLSpanNoUnderline.class);     // Catch: Exception -> L47
        this.timestamps = new ArrayList();
        this.timestampsAppearing = 0.0f;
        if (this.timestampLabelPaint != null) goto L30;
        TextPaint r04 = new TextPaint(1);
        this.timestampLabelPaint = r04;
        r04.setTextSize(AndroidUtilities.dp(12.0f));
        this.timestampLabelPaint.setColor(-1);
    L30:
        int r05 = 0;
    L32:
        if (r05 >= r92.length) goto L45;
        URLSpanNoUnderline r1 = r92[r05];
        if (r1 == null) goto L44;
        if (r1.getURL() == null) goto L44;
        if (r1.label == null) goto L44;
        if (r1.getURL().startsWith("video?") == false) goto L44;
        if (Utilities.parseInt(r1.getURL().substring(6)) < 0) goto L44;
        SpannableStringBuilder r3 = new SpannableStringBuilder(r1.label);
        Emoji.replaceEmoji(r3, this.timestampLabelPaint.getFontMetricsInt(), false);
        this.timestamps.add(new Pair(Float.valueOf((r2 * 1000) / r10), r3));
    L44:
        r05 = r05 + 1;
        goto L32
    L45:
        Collections.sort(this.timestamps, new VideoPlayerSeekBar$$ExternalSyntheticLambda0());
        return;
    L47:
        e = move-exception;
        FileLog.e(e);
        this.timestamps = null;
        this.currentTimestamp = -1;
        this.timestampsAppearing = 0.0f;
        StaticLayout[] r82 = this.timestampLabel;
        if (r82 == null) goto L64;
        r82[1] = null;
        r82[0] = null;
        return;
    L64:
        return;
    L17:
        if (this.lastVideoDuration != r10) goto L19;
    }
}
