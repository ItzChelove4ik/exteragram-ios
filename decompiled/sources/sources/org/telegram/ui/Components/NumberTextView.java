package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import androidx.annotation.Keep;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes3.dex */
public class NumberTextView extends View {
    private boolean addNumber;
    private ObjectAnimator animator;
    private boolean center;
    private int currentNumber;
    private ArrayList<StaticLayout> letters;
    private ArrayList<StaticLayout> oldLetters;
    private float oldTextWidth;
    private float progress;
    private TextPaint textPaint;
    private float textWidth;

    /* renamed from: org.telegram.ui.Components.NumberTextView$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ NumberTextView this$0;

        public AnonymousClass1(NumberTextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            NumberTextView.m11314$$Nest$fputanimator(this.this$0, null);
            NumberTextView.m11313$$Nest$fgetoldLetters(this.this$0).clear();
        }
    }

    /* loaded from: classes7.dex */
    public interface OnTextWidthProgressChangedListener {
    }

    /* renamed from: -$$Nest$fgetoldLetters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11313$$Nest$fgetoldLetters(NumberTextView r0) {
        return r0.oldLetters;
    }

    /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11314$$Nest$fputanimator(NumberTextView r0, ObjectAnimator r1) {
        r0.animator = r1;
    }

    public NumberTextView(Context r2) {
        super(r2);
        this.letters = new ArrayList();
        this.oldLetters = new ArrayList();
        this.textPaint = new TextPaint(1);
        this.progress = 0.0f;
        this.currentNumber = 1;
    }

    public float getOldTextWidth() {
        return this.oldTextWidth;
    }

    @Keep
    public float getProgress() {
        return this.progress;
    }

    public float getTextWidth() {
        return this.textWidth;
    }

    @Override // android.view.View
    public void onDraw(Canvas r14) {
        if (this.letters.isEmpty() == false) goto L5;
        return;
    L5:
        float r0 = this.letters.get(0).getHeight();
        if (this.addNumber == false) goto L8;
        float r2 = AndroidUtilities.dp(4.0f);
    L10:
        if (this.center == false) goto L12;
        float r3 = (getMeasuredWidth() - this.textWidth) / 2.0f;
        float r6 = ((getMeasuredWidth() - this.oldTextWidth) / 2.0f) - r3;
    L13:
        r14.save();
        r14.translate(getPaddingLeft() + r3, (getMeasuredHeight() - r0) / 2.0f);
        int r02 = Math.max(this.letters.size(), this.oldLetters.size());
        int r32 = 0;
    L14:
        if (r32 >= r02) goto L54;
        r14.save();
        StaticLayout r7 = null;
        if (r32 >= this.oldLetters.size()) goto L18;
        StaticLayout r4 = this.oldLetters.get(r32);
    L20:
        if (r32 >= this.letters.size()) goto L22;
        r7 = this.letters.get(r32);
    L22:
        float r8 = this.progress;
        if (r8 <= 0.0f) goto L31;
        TextPaint r9 = this.textPaint;
        if (r4 == null) goto L29;
        r9.setAlpha((int) (r8 * 255.0f));
        r14.save();
        r14.translate(r6, (this.progress - 1.0f) * r2);
        r4.draw(r14);
        r14.restore();
        if (r7 == null) goto L43;
        this.textPaint.setAlpha((int) ((1.0f - this.progress) * 255.0f));
        r14.translate(0.0f, this.progress * r2);
    L43:
        if (r7 == null) goto L45;
        r7.draw(r14);
    L45:
        r14.restore();
        if (r7 == null) goto L48;
        float r82 = r7.getLineWidth(0);
    L49:
        r14.translate(r82, 0.0f);
        if (r7 == null) goto L53;
        if (r4 == null) goto L53;
        r6 = r6 + (r4.getLineWidth(0) - r7.getLineWidth(0));
    L53:
        r32 = r32 + 1;
        goto L14
    L48:
        r82 = r4.getLineWidth(0) + AndroidUtilities.dp(1.0f);
        goto L49
    L29:
        r9.setAlpha(255);
        goto L43
    L31:
        if (r8 >= 0.0f) goto L41;
        if (r4 == null) goto L34;
        this.textPaint.setAlpha((int) ((-r8) * 255.0f));
        r14.save();
        r14.translate(r6, (this.progress + 1.0f) * r2);
        r4.draw(r14);
        r14.restore();
    L34:
        if (r7 == null) goto L43;
        if (r32 == (r02 - 1)) goto L40;
        if (r4 != null) goto L40;
        this.textPaint.setAlpha(255);
    L40:
        this.textPaint.setAlpha((int) ((this.progress + 1.0f) * 255.0f));
        r14.translate(0.0f, this.progress * r2);
        goto L43
    L41:
        if (r7 == null) goto L43;
        this.textPaint.setAlpha(255);
        goto L43
    L18:
        r4 = null;
        goto L20
    L54:
        r14.restore();
        return;
    L12:
        r3 = 0.0f;
        r6 = 0.0f;
        goto L13
    L8:
        r2 = r0;
        goto L10
    }

    public void setAddNumber() {
        this.addNumber = true;
    }

    public void setCenterAlign(boolean r1) {
        this.center = r1;
    }

    public void setNumber(int r22, boolean r23) {
        if (this.currentNumber != r22) goto L6;
        if (r23 == false) goto L6;
        return;
    L6:
        ObjectAnimator r2 = this.animator;
        if (r2 == null) goto L9;
        r2.cancel();
        this.animator = null;
    L9:
        this.oldLetters.clear();
        this.oldLetters.addAll(this.letters);
        this.letters.clear();
        if (this.addNumber == false) goto L15;
        Locale r24 = Locale.US;
        String r6 = String.format(r24, "#%d", new Object[]{Integer.valueOf(this.currentNumber)});
        String r25 = String.format(r24, "#%d", new Object[]{Integer.valueOf(r22)});
        if (r22 >= this.currentNumber) goto L14;
    L13:
        boolean r7 = true;
    L18:
        this.textWidth = this.textPaint.measureText(r25);
        float r8 = this.textPaint.measureText(r6);
        this.oldTextWidth = r8;
        if (this.center == true) goto L21;
    L23:
        boolean r82 = false;
    L24:
        this.currentNumber = r22;
        this.progress = 0.0f;
        int r9 = 0;
    L26:
        if (r9 >= r25.length()) goto L43;
        int r10 = r9 + 1;
        String r12 = r25.substring(r9, r10);
        if (this.oldLetters.isEmpty() == false) goto L30;
    L32:
        String r11 = null;
    L33:
        if (r82 == true) goto L38;
        if (r11 == null) goto L38;
        if (r11.equals(r12) == false) goto L38;
        this.letters.add(this.oldLetters.get(r9));
        this.oldLetters.set(r9, null);
    L42:
        r9 = r10;
    L38:
        if (r82 == false) goto L41;
        if (r11 != null) goto L41;
        this.oldLetters.add(new StaticLayout(_UrlKt.FRAGMENT_ENCODE_SET, this.textPaint, 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false));
    L41:
        this.letters.add(new StaticLayout(r12, this.textPaint, (int) Math.ceil(r13.measureText(r12)), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false));
        goto L42
    L30:
        if (r9 >= r6.length()) goto L32;
        r11 = r6.substring(r9, r10);
        goto L33
    L43:
        if (r23 == true) goto L45;
    L54:
        invalidate();
        return;
    L45:
        if (this.oldLetters.isEmpty() == true) goto L54;
        if (r7 == false) goto L48;
        float r26 = -1.0f;
    L49:
        ObjectAnimator r1 = ObjectAnimator.ofFloat(this, "progress", new float[]{r26, 0.0f});
        this.animator = r1;
        if (this.addNumber == false) goto L52;
        long r27 = 180;
    L53:
        r1.setDuration(r27);
        this.animator.addListener(new AnonymousClass1(this));
        this.animator.start();
        goto L54
    L52:
        r27 = 150;
        goto L53
    L48:
        r26 = 1.0f;
        goto L49
    L21:
        if (this.textWidth == r8) goto L23;
        r82 = true;
    L14:
        r7 = false;
        goto L18
    L15:
        Locale r28 = Locale.US;
        r6 = String.format(r28, TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(this.currentNumber)});
        r25 = String.format(r28, TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r22)});
        if (r22 <= this.currentNumber) goto L14;
        goto L14
    }

    public void setOnTextWidthProgressChangedListener(OnTextWidthProgressChangedListener r1) {
    }

    @Keep
    public void setProgress(float r2) {
        if (this.progress != r2) goto L5;
        return;
    L5:
        this.progress = r2;
        invalidate();
    }

    public void setTextColor(int r2) {
        this.textPaint.setColor(r2);
        invalidate();
    }

    public void setTextSize(int r2) {
        this.textPaint.setTextSize(AndroidUtilities.dp(r2));
        this.oldLetters.clear();
        this.letters.clear();
        setNumber(this.currentNumber, false);
    }

    public void setTypeface(Typeface r2) {
        this.textPaint.setTypeface(r2);
        this.oldLetters.clear();
        this.letters.clear();
        setNumber(this.currentNumber, false);
    }
}
