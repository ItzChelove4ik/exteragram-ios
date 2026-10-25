package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public abstract class CodeFieldContainer extends LinearLayout {
    Paint bitmapPaint;
    public CodeNumberField[] codeField;
    public boolean ignoreOnTextChange;
    public boolean isFocusSuppressed;
    Paint paint;
    float strokeWidth;

    /* renamed from: org.telegram.ui.CodeFieldContainer$1, reason: invalid class name */
    public class AnonymousClass1 extends CodeNumberField {
        final /* synthetic */ CodeFieldContainer this$0;
        final /* synthetic */ int val$length;
        final /* synthetic */ int val$num;

        public AnonymousClass1(CodeFieldContainer r1, Context r2, int r3, int r4) {
            this.this$0 = r1;
            this.val$num = r3;
            this.val$length = r4;
            super(r2);
        }

        @Override // com.exteragram.messenger.components.ReceiveContentEditText, android.view.View
        public boolean dispatchKeyEvent(KeyEvent r7) {
            int r2 = 0;
            if (r7.getKeyCode() != 4) goto L5;
            return false;
        L5:
            int r0 = r7.getKeyCode();
            if (this.val$num < this.this$0.codeField.length) goto L9;
            return false;
        L9:
            if (r7.getAction() != 1) goto L50;
            if (r0 == 67) goto L13;
        L16:
            if (r0 != 67) goto L32;
            if (this.this$0.codeField[this.val$num].length() != 0) goto L32;
            int r3 = this.val$num;
            if (r3 <= 0) goto L32;
            CodeNumberField[] r02 = this.this$0.codeField;
            r02[r3 - 1].setSelection(r02[r3 - 1].length());
        L22:
            int r03 = this.val$num;
            if (r2 >= r03) goto L29;
            int r32 = r03 - 1;
            CodeFieldContainer r4 = this.this$0;
            if (r2 != r32) goto L27;
            r4.codeField[r03 - 1].requestFocus();
        L28:
            r2 = r2 + 1;
            goto L22
        L27:
            r4.codeField[r2].clearFocus();
            goto L28
        L29:
            this.this$0.codeField[r03 - 1].startExitAnimation();
            this.this$0.codeField[this.val$num - 1].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return true;
        L32:
            if (r0 >= 7) goto L34;
        L48:
            return true;
        L34:
            if (r0 > 16) goto L48;
            String r72 = Integer.toString(r0 - 7);
            if (this.this$0.codeField[this.val$num].getText() == null) goto L45;
            if (r72.equals(this.this$0.codeField[this.val$num].getText().toString()) == false) goto L45;
            int r73 = this.val$num;
            int r04 = this.val$length - 1;
            CodeFieldContainer r6 = this.this$0;
            if (r73 < r04) goto L42;
            r6.processNextPressed();
        L43:
            return true;
        L42:
            r6.codeField[r73 + 1].requestFocus();
        L45:
            if (this.this$0.codeField[this.val$num].length() <= 0) goto L47;
            this.this$0.codeField[this.val$num].startExitAnimation();
        L47:
            this.this$0.codeField[this.val$num].setText(r72);
            goto L48
        L13:
            if (this.this$0.codeField[this.val$num].length() != 1) goto L16;
            this.this$0.codeField[this.val$num].startExitAnimation();
            this.this$0.codeField[this.val$num].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return true;
        L50:
            return isFocused();
        }
    }

    /* renamed from: org.telegram.ui.CodeFieldContainer$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        final /* synthetic */ CodeFieldContainer this$0;
        final /* synthetic */ int val$length;
        final /* synthetic */ int val$num;

        public AnonymousClass2(CodeFieldContainer r1, int r2, int r3) {
            this.this$0 = r1;
            this.val$num = r2;
            this.val$length = r3;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r10) {
            if (this.this$0.ignoreOnTextChange == true) goto L41;
            int r0 = r10.length();
            if (r0 < 1) goto L37;
            int r2 = this.val$num;
            if (r0 <= 1) goto L19;
            String r3 = r10.toString();
            this.this$0.ignoreOnTextChange = true;
            int r4 = 0;
        L11:
            if (r4 >= Math.min(this.val$length - this.val$num, r0)) goto L18;
            if (r4 != 0) goto L14;
            r10.replace(0, r0, r3.substring(r4, r4 + 1));
        L17:
            r4 = r4 + 1;
            goto L11
        L14:
            r2 = r2 + 1;
            int r5 = this.val$num;
            int r6 = r5 + r4;
            CodeNumberField[] r7 = this.this$0.codeField;
            if (r6 >= r7.length) goto L17;
            r7[r5 + r4].setText(r3.substring(r4, r4 + 1));
            goto L17
        L18:
            this.this$0.ignoreOnTextChange = false;
        L19:
            int r102 = r2 + 1;
            if (r102 < 0) goto L24;
            CodeNumberField[] r1 = this.this$0.codeField;
            if (r102 >= r1.length) goto L24;
            CodeNumberField r12 = r1[r102];
            r12.setSelection(r12.length());
            this.this$0.codeField[r102].requestFocus();
        L24:
            int r103 = this.val$length;
            if (r2 == (r103 - 1)) goto L30;
            if (r2 != (r103 - 2)) goto L38;
            if (r0 >= 2) goto L30;
            return;
        L38:
            return;
        L30:
            if (this.this$0.getCode().length() != this.val$length) goto L40;
            this.this$0.processNextPressed();
            return;
        L40:
            return;
        L37:
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: $r8$lambda$T-ntps9p9Do_pL3e7_5cnm2BCOM, reason: not valid java name */
    public static /* synthetic */ boolean m8653$r8$lambda$Tntps9p9Do_pL3e7_5cnm2BCOM(CodeFieldContainer r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$setNumbersCount$0(r1, r2, r3);
    }

    public CodeFieldContainer(Context r2) {
        super(r2);
        this.paint = new Paint(1);
        this.bitmapPaint = new Paint(1);
        this.paint.setStyle(Paint.Style.STROKE);
        setOrientation(0);
    }

    private /* synthetic */ boolean lambda$setNumbersCount$0(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L6;
        processNextPressed();
        return true;
    L6:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r11) {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L20;
        View r1 = getChildAt(r0);
        if ((r1 instanceof CodeNumberField) == false) goto L19;
        CodeNumberField r2 = (CodeNumberField) r1;
        if (this.isFocusSuppressed == false) goto L10;
    L15:
        float r3 = r2.getSuccessProgress();
        this.paint.setColor(ColorUtils.blendARGB(ColorUtils.blendARGB(ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhiteInputField), Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated), r2.getFocusedProgress()), Theme.getColor(Theme.key_text_RedBold), r2.getErrorProgress()), Theme.getColor(Theme.key_checkbox), r3));
        RectF r6 = AndroidUtilities.rectTmp;
        r6.set(r1.getLeft(), r1.getTop(), r1.getRight(), r1.getBottom());
        float r12 = this.strokeWidth;
        r6.inset(r12, r12);
        if (r3 == 0.0f) goto L18;
        float r13 = -Math.max(0.0f, this.strokeWidth * (r2.getSuccessScaleProgress() - 1.0f));
        r6.inset(r13, r13);
    L18:
        r11.drawRoundRect(r6, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.paint);
        goto L19
    L10:
        if (r1.isFocused() == false) goto L13;
        r2.animateFocusedProgress(1.0f);
        goto L15
    L13:
        if (r1.isFocused() == true) goto L15;
        r2.animateFocusedProgress(0.0f);
    L19:
        r0 = r0 + 1;
        goto L4
    L20:
        super.dispatchDraw(r11);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r9, View r10, long r11) {
        if ((r10 instanceof CodeNumberField) == false) goto L14;
        CodeNumberField r0 = (CodeNumberField) r10;
        r9.save();
        float r1 = r0.enterAnimation;
        RectF r2 = AndroidUtilities.rectTmp;
        r2.set(r10.getX(), r10.getY(), r10.getX() + r10.getMeasuredWidth(), r10.getY() + r10.getMeasuredHeight());
        float r3 = this.strokeWidth;
        r2.inset(r3, r3);
        r9.clipRect(r2);
        if (r0.replaceAnimation == false) goto L7;
        float r22 = (r1 * 0.5f) + 0.5f;
        r10.setAlpha(r1);
        r9.scale(r22, r22, r0.getX() + (r0.getMeasuredWidth() / 2.0f), r0.getY() + (r0.getMeasuredHeight() / 2.0f));
    L8:
        super.drawChild(r9, r10, r11);
        r9.restore();
        float r102 = r0.exitAnimation;
        if (r102 >= 1.0f) goto L15;
        r9.save();
        float r32 = 1.0f - r102;
        float r103 = (r32 * 0.5f) + 0.5f;
        r9.scale(r103, r103, r0.getX() + (r0.getMeasuredWidth() / 2.0f), r0.getY() + (r0.getMeasuredHeight() / 2.0f));
        this.bitmapPaint.setAlpha((int) (r32 * 255.0f));
        r9.drawBitmap(r0.exitBitmap, r0.getX(), r0.getY(), this.bitmapPaint);
        r9.restore();
        return true;
    L15:
        return true;
    L7:
        r10.setAlpha(1.0f);
        r9.translate(0.0f, r10.getMeasuredHeight() * (1.0f - r1));
        goto L8
    L14:
        return super.drawChild(r9, r10, r11);
    }

    public String getCode() {
        if (this.codeField != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L7:
        CodeNumberField[] r2 = this.codeField;
        if (r1 >= r2.length) goto L11;
        r0.append(PhoneFormat.stripExceptNumbers(r2[r1].getText().toString()));
        r1 = r1 + 1;
        goto L7
    L11:
        return r0.toString();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        Paint r12 = this.paint;
        float r22 = AndroidUtilities.dp(1.5f);
        this.strokeWidth = r22;
        r12.setStrokeWidth(r22);
    }

    public abstract void processNextPressed();

    public void setCode(String r2) {
        this.codeField[0].setText(r2);
    }

    public void setNumbersCount(int r13, int r14) {
        CodeNumberField[] r0 = this.codeField;
        int r1 = 0;
        if (r0 != null) goto L5;
    L10:
        if (r0 == null) goto L14;
        int r2 = r0.length;
        int r3 = 0;
    L12:
        if (r3 >= r2) goto L14;
        removeView(r0[r3]);
        r3 = r3 + 1;
    L14:
        this.codeField = new CodeNumberField[r13];
        int r02 = 0;
    L15:
        if (r02 >= r13) goto L39;
        this.codeField[r02] = new AnonymousClass1(this, getContext(), r02, r13);
        this.codeField[r02].setImeOptions(268435461);
        this.codeField[r02].setTextSize(1, 20.0f);
        this.codeField[r02].setMaxLines(1);
        this.codeField[r02].setTypeface(AndroidUtilities.bold());
        this.codeField[r02].setPadding(0, 0, 0, 0);
        this.codeField[r02].setGravity(17);
        CodeNumberField[] r22 = this.codeField;
        if (r14 != 3) goto L19;
        r22[r02].setEnabled(false);
        this.codeField[r02].setInputType(0);
        this.codeField[r02].setVisibility(8);
    L20:
        int r23 = 42;
        int r32 = 10;
        if (r14 != 10) goto L24;
        int r4 = 47;
    L23:
        int r5 = r23;
        int r6 = r4;
    L28:
        CodeNumberField r24 = this.codeField[r02];
        if (r02 == (r13 - 1)) goto L31;
        int r10 = r32;
    L32:
        addView(r24, LayoutHelper.createLinear(r5, r6, 1, 0, 0, r10, 0));
        this.codeField[r02].addTextChangedListener(new AnonymousClass2(this, r02, r13));
        this.codeField[r02].setOnEditorActionListener(new CodeFieldContainer$$ExternalSyntheticLambda0(this));
        r02 = r02 + 1;
        goto L15
    L31:
        r10 = 0;
        goto L32
    L24:
        r4 = 34;
        if (r14 != 11) goto L27;
        r23 = 28;
        r32 = 5;
        goto L23
    L27:
        r32 = 7;
        r6 = 42;
        r5 = 34;
        goto L28
    L19:
        r22[r02].setInputType(3);
        goto L20
    L39:
        return;
    L5:
        if (r0.length != r13) goto L10;
    L7:
        CodeNumberField[] r132 = this.codeField;
        if (r1 >= r132.length) goto L33;
        r132[r1].setText(_UrlKt.FRAGMENT_ENCODE_SET);
        r1 = r1 + 1;
        goto L7
    }

    public void setText(String r2) {
        setText(r2, false);
    }

    public void setText(String r4, boolean r5) {
        if (this.codeField == null) goto L18;
        int r0 = 0;
        if (r5 == false) goto L14;
        int r52 = 0;
    L8:
        CodeNumberField[] r1 = this.codeField;
        if (r52 >= r1.length) goto L14;
        if (r1[r52].isFocused() == true) goto L12;
        r52 = r52 + 1;
        goto L8
    L12:
        r0 = r52;
    L14:
        int r53 = r0;
    L16:
        if (r53 >= Math.min(this.codeField.length, r4.length() + r0)) goto L22;
        this.codeField[r53].setText(Character.toString(r4.charAt(r53 - r0)));
        r53 = r53 + 1;
        goto L16
    L22:
        return;
    }
}
