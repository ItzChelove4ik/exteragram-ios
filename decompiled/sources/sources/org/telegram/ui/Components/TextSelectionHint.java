package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public abstract class TextSelectionHint extends View {

    /* renamed from: a, reason: collision with root package name */
    Animator f511a;
    int animateToEnd;
    int animateToStart;
    int currentEnd;
    int currentStart;
    Runnable dismissTunnable;
    int end;
    float endOffsetValue;
    float enterValue;
    private Interpolator interpolator;
    int lastW;
    int padding;
    Path path;
    float prepareProgress;
    private final Theme.ResourcesProvider resourcesProvider;
    Paint selectionPaint;
    private boolean showOnMeasure;
    boolean showing;
    int start;
    float startOffsetValue;
    StaticLayout textLayout;
    TextPaint textPaint;

    /* renamed from: org.telegram.ui.Components.TextSelectionHint$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ TextSelectionHint this$0;

        public AnonymousClass1(TextSelectionHint r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.setVisibility(4);
        }
    }

    public static /* synthetic */ void $r8$lambda$IkoOPtsHx81B7aUrBmnAbPjM_Tk(TextSelectionHint r0, ValueAnimator r1) {
        r0.lambda$show$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$P6Lq94h4iRYi87hIgFyQMyfN5RM(TextSelectionHint r0) {
        r0.hideInternal();
    }

    public static /* synthetic */ void $r8$lambda$dO6cD8AHluOEfT_mdaWHqYKtGFM(TextSelectionHint r0, ValueAnimator r1) {
        r0.lambda$show$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$tCbTwbTrIgb3xhxJk0DawDCshR8(TextSelectionHint r0, ValueAnimator r1) {
        r0.lambda$hideInternal$4(r1);
    }

    /* renamed from: $r8$lambda$uOrWGrHp1ThydsgSMU2u-FrfFQo, reason: not valid java name */
    public static /* synthetic */ void m13216$r8$lambda$uOrWGrHp1ThydsgSMU2uFrfFQo(TextSelectionHint r0, ValueAnimator r1) {
        r0.lambda$show$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$z6Kw1cbA3WXqSTC02a2jxo9x6ZM(TextSelectionHint r0, ValueAnimator r1) {
        r0.lambda$show$1(r1);
    }

    public TextSelectionHint(Context r5, Theme.ResourcesProvider r6) {
        super(r5);
        this.textPaint = new TextPaint(1);
        this.selectionPaint = new Paint(1);
        this.padding = AndroidUtilities.dp(24.0f);
        this.interpolator = new OvershootInterpolator();
        this.dismissTunnable = new TextSelectionHint$$ExternalSyntheticLambda5(this);
        this.path = new Path();
        this.resourcesProvider = r6;
        int r52 = getThemedColor(Theme.key_undo_infoColor);
        int r62 = Color.alpha(r52);
        this.textPaint.setTextSize(AndroidUtilities.dp(15.0f));
        this.textPaint.setColor(r52);
        this.selectionPaint.setColor(r52);
        this.selectionPaint.setAlpha((int) (r62 * 0.14d));
        setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(6.0f), getThemedColor(Theme.key_undo_background)));
    }

    private void drawSelection(Canvas r19, StaticLayout r20, int r21, int r22) {
        int r3 = r20.getLineForOffset(r21);
        int r4 = r20.getLineForOffset(r22);
        int r5 = (int) r20.getPrimaryHorizontal(r21);
        int r2 = (int) r20.getPrimaryHorizontal(r22);
        if (r3 != r4) goto L6;
        r19.drawRect(r5, r20.getLineTop(r3), r2, r20.getLineBottom(r3), this.selectionPaint);
        return;
    L6:
        r19.drawRect(r5, r20.getLineTop(r3), r20.getLineWidth(r3), r20.getLineBottom(r3), this.selectionPaint);
        r19.drawRect(0.0f, r20.getLineTop(r4), r2, r20.getLineBottom(r4), this.selectionPaint);
    L7:
        r3 = r3 + 1;
        if (r3 >= r4) goto L10;
        r19.drawRect(0.0f, r20.getLineTop(r3), r20.getLineWidth(r3), r20.getLineBottom(r3), this.selectionPaint);
        goto L7
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private void hideInternal() {
        Animator r0 = this.f511a;
        if (r0 == null) goto L5;
        r0.removeAllListeners();
        this.f511a.cancel();
    L5:
        this.showing = false;
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.prepareProgress, 0.0f});
        r02.addUpdateListener(new TextSelectionHint$$ExternalSyntheticLambda0(this));
        r02.addListener(new AnonymousClass1(this));
        this.f511a = r02;
        r02.start();
    }

    private /* synthetic */ void lambda$hideInternal$4(ValueAnimator r1) {
        this.prepareProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$show$0(ValueAnimator r1) {
        this.prepareProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$show$1(ValueAnimator r1) {
        this.enterValue = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$show$2(ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        this.startOffsetValue = r42;
        this.currentStart = (int) (this.animateToStart + ((this.start - r0) * r42));
        invalidate();
    }

    private /* synthetic */ void lambda$show$3(ValueAnimator r4) {
        this.endOffsetValue = ((Float) r4.getAnimatedValue()).floatValue();
        this.currentEnd = this.animateToEnd + ((int) Math.ceil((this.end - r0) * r4));
        invalidate();
    }

    private void roundedRect(Path r4, float r5, float r6, float r7, float r8, float r9, float r10, boolean r11, boolean r12) {
        r4.reset();
        if (r9 >= 0.0f) goto L6;
        r9 = 0.0f;
    L6:
        if (r10 >= 0.0f) goto L8;
        r10 = 0.0f;
    L8:
        float r52 = r7 - r5;
        float r82 = r8 - r6;
        float r1 = r52 / 2.0f;
        if (r9 <= r1) goto L11;
        r9 = r1;
    L11:
        float r13 = r82 / 2.0f;
        if (r10 <= r13) goto L14;
        r10 = r13;
    L14:
        float r53 = r52 - (r9 * 2.0f);
        float r83 = r82 - (2.0f * r10);
        r4.moveTo(r7, r6 + r10);
        if (r12 == false) goto L17;
        float r62 = -r10;
        r4.rQuadTo(0.0f, r62, -r9, r62);
    L18:
        r4.rLineTo(-r53, 0.0f);
        if (r11 == false) goto L21;
        float r63 = -r9;
        r4.rQuadTo(r63, 0.0f, r63, r10);
    L22:
        r4.rLineTo(0.0f, r83);
        r4.rLineTo(0.0f, r10);
        r4.rLineTo(r9, 0.0f);
        r4.rLineTo(r53, 0.0f);
        r4.rLineTo(r9, 0.0f);
        r4.rLineTo(0.0f, -r10);
        r4.rLineTo(0.0f, -r83);
        r4.close();
        return;
    L21:
        r4.rLineTo(-r9, 0.0f);
        r4.rLineTo(0.0f, r10);
        goto L22
    L17:
        r4.rLineTo(0.0f, -r10);
        r4.rLineTo(-r9, 0.0f);
        goto L18
    }

    public float getPrepareProgress() {
        return this.prepareProgress;
    }

    public void hide() {
        AndroidUtilities.cancelRunOnUIThread(this.dismissTunnable);
        hideInternal();
    }

    @Override // android.view.View
    public void onDraw(Canvas r22) {
        if (this.textLayout != null) goto L5;
        return;
    L5:
        super.onDraw(r22);
        r22.save();
        r22.translate(this.padding, (getMeasuredHeight() - this.textLayout.getHeight()) >> 1);
        if (this.enterValue == 0.0f) goto L8;
        drawSelection(r22, this.textLayout, this.currentStart, this.currentEnd);
    L8:
        this.textLayout.draw(r22);
        int r11 = AndroidUtilities.dp(14.0f);
        int r1 = this.textLayout.getLineForOffset(this.currentEnd);
        this.textLayout.getPrimaryHorizontal(this.currentEnd);
        int r12 = this.textLayout.getLineBottom(r1);
        int r2 = this.currentEnd;
        int r3 = this.animateToEnd;
        if (r2 != r3) goto L11;
        roundedRect(this.path, this.textLayout.getPrimaryHorizontal(r3), this.textLayout.getLineTop(r1), this.textLayout.getPrimaryHorizontal(this.animateToEnd) + AndroidUtilities.dpf2(4.0f), this.textLayout.getLineBottom(r1), AndroidUtilities.dpf2(4.0f), AndroidUtilities.dpf2(4.0f), false, true);
        r22.drawPath(this.path, this.selectionPaint);
    L11:
        float r14 = this.interpolator.getInterpolation(this.enterValue);
        int r13 = (int) ((this.textLayout.getPrimaryHorizontal(this.animateToEnd) + (AndroidUtilities.dpf2(4.0f) * (1.0f - this.endOffsetValue))) + ((this.textLayout.getPrimaryHorizontal(this.end) - this.textLayout.getPrimaryHorizontal(this.animateToEnd)) * this.endOffsetValue));
        r22.save();
        r22.translate(r13, r12);
        float r122 = r11;
        float r4 = r122 / 2.0f;
        r22.scale(r14, r14, r4, r4);
        this.path.reset();
        Path r15 = this.path;
        Path.Direction r7 = Path.Direction.CCW;
        r15.addCircle(r4, r4, r4, r7);
        this.path.addRect(0.0f, 0.0f, r4, r4, r7);
        r22.drawPath(this.path, this.textPaint);
        r22.restore();
        int r16 = this.textLayout.getLineForOffset(this.currentStart);
        this.textLayout.getPrimaryHorizontal(this.currentStart);
        int r23 = this.textLayout.getLineBottom(r16);
        if (this.currentStart != this.animateToStart) goto L14;
        float r19 = 4.0f;
        float r132 = r4;
        float r162 = 1.0f;
        Path.Direction r152 = r7;
        int r17 = r11;
        int r112 = r23;
        roundedRect(this.path, -AndroidUtilities.dp(4.0f), this.textLayout.getLineTop(r16), 0.0f, this.textLayout.getLineBottom(r16), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), true, false);
        r22.drawPath(this.path, this.selectionPaint);
    L15:
        r22.save();
        r22.translate(((int) ((this.textLayout.getPrimaryHorizontal(this.animateToStart) - (AndroidUtilities.dp(r19) * (r162 - this.startOffsetValue))) + ((this.textLayout.getPrimaryHorizontal(this.start) - this.textLayout.getPrimaryHorizontal(this.animateToStart)) * this.startOffsetValue))) - r17, r112);
        r22.scale(r14, r14, r132, r132);
        this.path.reset();
        this.path.addCircle(r132, r132, r132, r152);
        this.path.addRect(r132, 0.0f, r122, r132, r152);
        r22.drawPath(this.path, this.textPaint);
        r22.restore();
        r22.restore();
        return;
    L14:
        r17 = r11;
        r19 = 4.0f;
        r162 = 1.0f;
        r112 = r23;
        r132 = r4;
        r152 = r7;
        goto L15
    }

    @Override // android.view.View
    public void onMeasure(int r12, int r13) {
        super.onMeasure(r12, r13);
        if (getMeasuredWidth() == this.lastW) goto L5;
    L6:
        Animator r122 = this.f511a;
        if (r122 == null) goto L9;
        r122.removeAllListeners();
        this.f511a.cancel();
    L9:
        String r123 = LocaleController.getString(R.string.TextSelectionHint);
        Matcher r132 = Pattern.compile("\\*\\*.*\\*\\*").matcher(r123);
        if (r132.matches() == false) goto L12;
        String r133 = r132.group();
    L13:
        String r4 = r123.replace("**", _UrlKt.FRAGMENT_ENCODE_SET);
        this.textLayout = new StaticLayout(r4, this.textPaint, getMeasuredWidth() - (this.padding * 2), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.start = 0;
        this.end = 0;
        if (r133 == null) goto L16;
        this.start = r4.indexOf(r133);
    L16:
        int r1 = this.start;
        if (r1 <= 0) goto L19;
        this.end = r1 + r133.length();
    L32:
        if (this.end != 0) goto L34;
        this.end = r4.length();
    L34:
        this.animateToStart = 0;
        StaticLayout r134 = this.textLayout;
        int r135 = r134.getOffsetForHorizontal(r134.getLineForOffset(this.end), this.textLayout.getWidth() - 1);
        this.animateToEnd = r135;
        this.currentStart = this.start;
        this.currentEnd = this.end;
        if (this.showing == false) goto L38;
        this.prepareProgress = 1.0f;
        this.enterValue = 1.0f;
        this.currentStart = this.animateToStart;
        this.currentEnd = r135;
        this.startOffsetValue = 0.0f;
        this.endOffsetValue = 0.0f;
    L40:
        this.showOnMeasure = false;
        this.lastW = getMeasuredWidth();
    L41:
        int r124 = this.textLayout.getHeight() + (AndroidUtilities.dp(8.0f) * 2);
        if (r124 >= AndroidUtilities.dp(56.0f)) goto L44;
        r124 = AndroidUtilities.dp(56.0f);
    L44:
        setMeasuredDimension(getMeasuredWidth(), r124);
        return;
    L38:
        if (this.showOnMeasure == false) goto L40;
        show();
        goto L40
    L19:
        int r136 = 0;
        int r14 = 0;
    L21:
        if (r136 >= r4.length()) goto L32;
        if (r4.charAt(r136) != ' ') goto L30;
        r14 = r14 + 1;
        if (r14 != 2) goto L28;
        this.start = r136 + 1;
    L28:
        if (r14 != 3) goto L30;
        this.end = r136 - 1;
    L30:
        r136 = r136 + 1;
        goto L21
    L12:
        r133 = null;
        goto L13
    L5:
        if (this.textLayout != null) goto L41;
        goto L6
    }

    public void show() {
        AndroidUtilities.cancelRunOnUIThread(this.dismissTunnable);
        Animator r0 = this.f511a;
        if (r0 == null) goto L6;
        r0.removeAllListeners();
        this.f511a.cancel();
    L6:
        if (getMeasuredHeight() != 0) goto L8;
    L12:
        this.showOnMeasure = true;
        return;
    L8:
        if (getMeasuredWidth() == 0) goto L12;
        this.showing = true;
        setVisibility(0);
        this.prepareProgress = 0.0f;
        this.enterValue = 0.0f;
        this.currentStart = this.start;
        this.currentEnd = this.end;
        this.startOffsetValue = 1.0f;
        this.endOffsetValue = 1.0f;
        invalidate();
        ValueAnimator r3 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r3.addUpdateListener(new TextSelectionHint$$ExternalSyntheticLambda1(this));
        r3.setDuration(210);
        r3.setInterpolator(new DecelerateInterpolator());
        ValueAnimator r4 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r4.addUpdateListener(new TextSelectionHint$$ExternalSyntheticLambda2(this));
        r4.setStartDelay(600);
        r4.setDuration(250);
        ValueAnimator r5 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        r5.setStartDelay(500);
        r5.addUpdateListener(new TextSelectionHint$$ExternalSyntheticLambda3(this));
        CubicBezierInterpolator r8 = CubicBezierInterpolator.EASE_OUT;
        r5.setInterpolator(r8);
        r5.setDuration(500);
        ValueAnimator r6 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        r6.setStartDelay(400);
        r6.addUpdateListener(new TextSelectionHint$$ExternalSyntheticLambda4(this));
        r6.setInterpolator(r8);
        r6.setDuration(900);
        AnimatorSet r7 = new AnimatorSet();
        r7.playSequentially(new Animator[]{r3, r4, r5, r6});
        this.f511a = r7;
        r7.start();
        AndroidUtilities.runOnUIThread(this.dismissTunnable, 5000);
    }
}
