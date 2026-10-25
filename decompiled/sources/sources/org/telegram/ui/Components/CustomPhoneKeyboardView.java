package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextPaint;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.GestureDetectorCompat;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class CustomPhoneKeyboardView extends ViewGroup {
    private final ImageView backButton;
    private final Runnable detectLongClick;
    private boolean dispatchBackWhenEmpty;
    private EditText editText;
    private final Runnable onBackButton;
    private boolean postedLongClick;
    private boolean runningLongClick;
    private View viewToFindFocus;
    private final View[] views;

    /* renamed from: org.telegram.ui.Components.CustomPhoneKeyboardView$1, reason: invalid class name */
    public class AnonymousClass1 extends ImageView {
        final /* synthetic */ CustomPhoneKeyboardView this$0;
        final /* synthetic */ GestureDetectorCompat val$backDetector;

        public AnonymousClass1(CustomPhoneKeyboardView r1, Context r2, GestureDetectorCompat r3) {
            this.this$0 = r1;
            this.val$backDetector = r3;
            super(r2);
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r3) {
            if (r3.getAction() == 1) goto L7;
            if (r3.getAction() == 3) goto L7;
        L11:
            super.onTouchEvent(r3);
            return this.val$backDetector.onTouchEvent(r3);
        L7:
            if (CustomPhoneKeyboardView.m10103$$Nest$fgetpostedLongClick(this.this$0) == false) goto L9;
        L10:
            CustomPhoneKeyboardView.m10105$$Nest$fputpostedLongClick(this.this$0, false);
            CustomPhoneKeyboardView.m10106$$Nest$fputrunningLongClick(this.this$0, false);
            removeCallbacks(CustomPhoneKeyboardView.m10101$$Nest$fgetdetectLongClick(this.this$0));
            removeCallbacks(CustomPhoneKeyboardView.m10102$$Nest$fgetonBackButton(this.this$0));
            goto L11
        L9:
            if (CustomPhoneKeyboardView.m10104$$Nest$fgetrunningLongClick(this.this$0) == false) goto L11;
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.Components.CustomPhoneKeyboardView$2, reason: invalid class name */
    public class AnonymousClass2 extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ CustomPhoneKeyboardView this$0;
        final /* synthetic */ int val$touchSlop;

        public AnonymousClass2(CustomPhoneKeyboardView r1, int r2) {
            this.this$0 = r1;
            this.val$touchSlop = r2;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r5) {
            if (CustomPhoneKeyboardView.m10103$$Nest$fgetpostedLongClick(this.this$0) == false) goto L5;
            CustomPhoneKeyboardView r52 = this.this$0;
            r52.removeCallbacks(CustomPhoneKeyboardView.m10101$$Nest$fgetdetectLongClick(r52));
        L5:
            CustomPhoneKeyboardView.m10105$$Nest$fputpostedLongClick(this.this$0, true);
            CustomPhoneKeyboardView r53 = this.this$0;
            r53.postDelayed(CustomPhoneKeyboardView.m10101$$Nest$fgetdetectLongClick(r53), 200);
            CustomPhoneKeyboardView.m10102$$Nest$fgetonBackButton(this.this$0).run();
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent r1, MotionEvent r2, float r3, float r4) {
            if (CustomPhoneKeyboardView.m10103$$Nest$fgetpostedLongClick(this.this$0) == true) goto L7;
            if (CustomPhoneKeyboardView.m10104$$Nest$fgetrunningLongClick(this.this$0) == true) goto L7;
        L11:
            return false;
        L7:
            if (Math.abs(r3) < this.val$touchSlop) goto L9;
        L10:
            CustomPhoneKeyboardView.m10105$$Nest$fputpostedLongClick(this.this$0, false);
            CustomPhoneKeyboardView.m10106$$Nest$fputrunningLongClick(this.this$0, false);
            CustomPhoneKeyboardView r12 = this.this$0;
            r12.removeCallbacks(CustomPhoneKeyboardView.m10101$$Nest$fgetdetectLongClick(r12));
            CustomPhoneKeyboardView r0 = this.this$0;
            r0.removeCallbacks(CustomPhoneKeyboardView.m10102$$Nest$fgetonBackButton(r0));
            goto L11
        L9:
            if (Math.abs(r4) < this.val$touchSlop) goto L11;
            goto L10
        }
    }

    public static final class NumberButtonView extends View {
        private final String mNumber;
        private final String mSymbols;
        private final TextPaint numberTextPaint;
        private final Rect rect;
        private final TextPaint symbolsTextPaint;

        /* renamed from: -$$Nest$mupdateColors, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10107$$Nest$mupdateColors(NumberButtonView r0) {
            r0.updateColors();
        }

        public NumberButtonView(Context r3, String r4, String r5) {
            super(r3);
            TextPaint r32 = new TextPaint(1);
            this.numberTextPaint = r32;
            TextPaint r1 = new TextPaint(1);
            this.symbolsTextPaint = r1;
            this.rect = new Rect();
            this.mNumber = r4;
            this.mSymbols = r5;
            r32.setTextSize(AndroidUtilities.dp(24.0f));
            r1.setTextSize(AndroidUtilities.dp(14.0f));
            updateColors();
        }

        private void updateColors() {
            this.numberTextPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.symbolsTextPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            float r0 = this.symbolsTextPaint.measureText(this.mSymbols);
            float r1 = this.numberTextPaint.measureText(this.mNumber);
            TextPaint r2 = this.numberTextPaint;
            String r3 = this.mNumber;
            r2.getTextBounds(r3, 0, r3.length(), this.rect);
            TextPaint r4 = this.symbolsTextPaint;
            String r5 = this.mSymbols;
            r4.getTextBounds(r5, 0, r5.length(), this.rect);
            r10.drawText(this.mNumber, (getWidth() * 0.25f) - (r1 / 2.0f), (getHeight() / 2.0f) + (this.rect.height() / 2.0f), this.numberTextPaint);
            r10.drawText(this.mSymbols, (getWidth() * 0.7f) - (r0 / 2.0f), (getHeight() / 2.0f) + (this.rect.height() / 2.0f), this.symbolsTextPaint);
        }
    }

    /* renamed from: $r8$lambda$6OqS-L5_VjNohTkANEHGRrcZr5o, reason: not valid java name */
    public static /* synthetic */ void m10098$r8$lambda$6OqSL5_VjNohTkANEHGRrcZr5o(View r0) {
    }

    /* renamed from: $r8$lambda$Cyd993TP4UJpqS1iT-Dg9NCzpZg, reason: not valid java name */
    public static /* synthetic */ void m10099$r8$lambda$Cyd993TP4UJpqS1iTDg9NCzpZg(CustomPhoneKeyboardView r0, String r1, View r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_cIIe3qzBIvUs_doFMHjkiq21FE(CustomPhoneKeyboardView r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$qxV3WeCC9Y-q_vAi2CczTSZdGro, reason: not valid java name */
    public static /* synthetic */ void m10100$r8$lambda$qxV3WeCC9Yq_vAi2CczTSZdGro(CustomPhoneKeyboardView r0) {
        r0.lambda$new$1();
    }

    /* renamed from: -$$Nest$fgetdetectLongClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m10101$$Nest$fgetdetectLongClick(CustomPhoneKeyboardView r0) {
        return r0.detectLongClick;
    }

    /* renamed from: -$$Nest$fgetonBackButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m10102$$Nest$fgetonBackButton(CustomPhoneKeyboardView r0) {
        return r0.onBackButton;
    }

    /* renamed from: -$$Nest$fgetpostedLongClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10103$$Nest$fgetpostedLongClick(CustomPhoneKeyboardView r0) {
        return r0.postedLongClick;
    }

    /* renamed from: -$$Nest$fgetrunningLongClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10104$$Nest$fgetrunningLongClick(CustomPhoneKeyboardView r0) {
        return r0.runningLongClick;
    }

    /* renamed from: -$$Nest$fputpostedLongClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10105$$Nest$fputpostedLongClick(CustomPhoneKeyboardView r0, boolean r1) {
        r0.postedLongClick = r1;
    }

    /* renamed from: -$$Nest$fputrunningLongClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10106$$Nest$fputrunningLongClick(CustomPhoneKeyboardView r0, boolean r1) {
        r0.runningLongClick = r1;
    }

    public CustomPhoneKeyboardView(Context r7) {
        super(r7);
        this.views = new View[12];
        this.onBackButton = new CustomPhoneKeyboardView$$ExternalSyntheticLambda0(this);
        this.detectLongClick = new CustomPhoneKeyboardView$$ExternalSyntheticLambda1(this);
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= 11) goto L25;
        if (r1 == 9) goto L24;
        switch(r1) {
            case 1: goto L18;
            case 2: goto L17;
            case 3: goto L16;
            case 4: goto L15;
            case 5: goto L14;
            case 6: goto L13;
            case 7: goto L12;
            case 8: goto L11;
            case 9: goto L9;
            case 10: goto L10;
            default: goto L9;
        };
    L9:
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        if (r1 == 10) goto L22;
        int r3 = r1 + 1;
    L23:
        String r32 = String.valueOf(r3);
        this.views[r1] = new NumberButtonView(r7, r32, r2);
        this.views[r1].setOnClickListener(new CustomPhoneKeyboardView$$ExternalSyntheticLambda2(this, r32));
        addView(this.views[r1]);
        goto L24
    L22:
        r3 = 0;
        goto L23
    L10:
        r2 = "+";
        goto L20
    L11:
        r2 = "WXYZ";
        goto L20
    L12:
        r2 = "TUV";
        goto L20
    L13:
        r2 = "PQRS";
        goto L20
    L14:
        r2 = "MNO";
        goto L20
    L15:
        r2 = "JKL";
        goto L20
    L16:
        r2 = "GHI";
        goto L20
    L17:
        r2 = "DEF";
        goto L20
    L18:
        r2 = "ABC";
    L24:
        r1 = r1 + 1;
        goto L4
    L25:
        AnonymousClass1 r33 = new AnonymousClass1(this, r7, setupBackButtonDetector(r7));
        this.backButton = r33;
        r33.setImageResource(R.drawable.msg_clear_input);
        r33.setColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        int r72 = AndroidUtilities.dp(11.0f);
        r33.setPadding(r72, r72, r72, r72);
        r33.setOnClickListener(new CustomPhoneKeyboardView$$ExternalSyntheticLambda3());
        this.views[11] = r33;
        addView(r33);
    L26:
        View[] r73 = this.views;
        if (r0 >= r73.length) goto L33;
        View r74 = r73[r0];
        if (r74 == null) goto L32;
        ScaleStateListAnimator.apply(r74, 0.02f, 1.2f);
        r74.setBackground(getButtonDrawable(r0));
    L32:
        r0 = r0 + 1;
        goto L26
    }

    private static Drawable getButtonDrawable(int r12) {
        boolean r1 = false;
        if (r12 >= 3) goto L5;
        boolean r0 = true;
    L6:
        int r3 = r12 % 3;
        if (r3 != 0) goto L9;
        boolean r4 = true;
    L11:
        if (r3 != 2) goto L13;
        boolean r32 = true;
    L15:
        if (r12 <= 8) goto L17;
        r1 = true;
    L17:
        int r122 = Theme.key_listSelector;
        int r9 = Theme.getColor(r122);
        int r10 = ColorUtils.setAlphaComponent(Theme.getColor(r122), 30);
        float r123 = 12.0f;
        if (r4 == false) goto L21;
        if (r0 == false) goto L21;
        float r5 = 24.0f;
    L22:
        int r52 = AndroidUtilities.dp(r5);
        if (r32 == false) goto L26;
        if (r0 == false) goto L26;
        float r02 = 24.0f;
    L27:
        int r6 = AndroidUtilities.dp(r02);
        if (r32 == false) goto L31;
        if (r1 == false) goto L31;
        float r03 = 24.0f;
    L32:
        int r7 = AndroidUtilities.dp(r03);
        if (r4 == false) goto L37;
        if (r1 == false) goto L37;
        r123 = 24.0f;
    L37:
        return Theme.createSimpleSelectorRoundRectDrawable(r52, r6, r7, AndroidUtilities.dp(r123), r9, r10, r10);
    L31:
        r03 = 12.0f;
    L26:
        r02 = 12.0f;
    L21:
        r5 = 12.0f;
        goto L22
    L13:
        r32 = false;
        goto L15
    L9:
        r4 = false;
        goto L11
    L5:
        r0 = false;
        goto L6
    }

    private /* synthetic */ void lambda$new$0() {
        checkFindEditText();
        EditText r0 = this.editText;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.length() != 0) goto L16;
        if (this.dispatchBackWhenEmpty == true) goto L16;
        return;
    L16:
        performHapticFeedback(3, 2);     // Catch: Exception -> L15
        playSoundEffect(0);     // Catch: Exception -> L15
    L11:
        this.editText.dispatchKeyEvent(new KeyEvent(0, 67));
        this.editText.dispatchKeyEvent(new KeyEvent(1, 67));
        if (this.runningLongClick == false) goto L19;
        postDelayed(this.onBackButton, 50);
        return;
    }

    private /* synthetic */ void lambda$new$1() {
        this.postedLongClick = false;
        this.runningLongClick = true;
        this.onBackButton.run();
    }

    private /* synthetic */ void lambda$new$2(String r8, View r9) {
        checkFindEditText();
        if (this.editText != null) goto L28;
        return;
    L28:
        performHapticFeedback(3, 2);     // Catch: Exception -> L27
    L7:
        EditText r92 = this.editText;
        if ((r92 instanceof EditTextBoldCursor) == false) goto L10;
        ((EditTextBoldCursor) r92).setTextWatchersSuppressed(true, false);
    L10:
        Editable r93 = this.editText.getText();
        if (this.editText.getSelectionEnd() != this.editText.length()) goto L13;
        int r0 = -1;
    L15:
        if (this.editText.getSelectionStart() != (-1)) goto L17;
    L22:
        this.editText.setText(r8);
        EditText r82 = this.editText;
        r82.setSelection(r82.length());
    L23:
        EditText r7 = this.editText;
        if ((r7 instanceof EditTextBoldCursor) == false) goto L30;
        ((EditTextBoldCursor) r7).setTextWatchersSuppressed(false, true);
        return;
    L30:
        return;
    L17:
        if (this.editText.getSelectionEnd() == (-1)) goto L22;
        EditText r3 = this.editText;
        r3.setText(r93.replace(r3.getSelectionStart(), this.editText.getSelectionEnd(), r8));
        EditText r83 = this.editText;
        if (r0 != (-1)) goto L21;
        r0 = r83.length();
    L21:
        r83.setSelection(r0);
        goto L23
    L13:
        r0 = this.editText.getSelectionStart() + r8.length();
        goto L15
    }

    private GestureDetectorCompat setupBackButtonDetector(Context r4) {
        return new GestureDetectorCompat(r4, new AnonymousClass2(this, ViewConfiguration.get(r4).getScaledTouchSlop()));
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int r1) {
        return true;
    }

    public void checkFindEditText() {
        if (this.editText != null) goto L10;
        View r0 = this.viewToFindFocus;
        if (r0 == null) goto L11;
        View r02 = r0.findFocus();
        if ((r02 instanceof EditText) == false) goto L12;
        this.editText = (EditText) r02;
        return;
    L12:
        return;
    L11:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
        int r42 = (getWidth() - AndroidUtilities.dp(32.0f)) / 3;
        int r52 = (getHeight() - AndroidUtilities.dp(42.0f)) / 4;
        int r62 = 0;
    L4:
        if (r62 >= this.views.length) goto L9;
        int r72 = ((r62 % 3) * (AndroidUtilities.dp(6.0f) + r42)) + AndroidUtilities.dp(10.0f);
        int r82 = ((r62 / 3) * (AndroidUtilities.dp(6.0f) + r52)) + AndroidUtilities.dp(10.0f);
        View r0 = this.views[r62];
        if (r0 == null) goto L8;
        r0.layout(r72, r82, r72 + r42, r82 + r52);
    L8:
        r62 = r62 + 1;
        goto L4
    }

    @Override // android.view.View
    public void onMeasure(int r6, int r7) {
        setMeasuredDimension(View.MeasureSpec.getSize(r6), View.MeasureSpec.getSize(r7));
        int r62 = (getWidth() - AndroidUtilities.dp(32.0f)) / 3;
        int r72 = (getHeight() - AndroidUtilities.dp(42.0f)) / 4;
        View[] r5 = this.views;
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        View r2 = r5[r1];
        if (r2 == null) goto L7;
        r2.measure(View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30));
    L7:
        r1 = r1 + 1;
        goto L3
    }

    public void setDispatchBackWhenEmpty(boolean r1) {
        this.dispatchBackWhenEmpty = r1;
    }

    public void setEditText(EditText r1) {
        this.editText = r1;
        this.dispatchBackWhenEmpty = false;
    }

    public void setViewToFindFocus(View r1) {
        this.viewToFindFocus = r1;
    }

    public void updateColors() {
        this.backButton.setColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        int r0 = 0;
    L3:
        View[] r1 = this.views;
        if (r0 >= r1.length) goto L11;
        View r12 = r1[r0];
        if (r12 == null) goto L10;
        r12.setBackground(getButtonDrawable(r0));
        if ((r12 instanceof NumberButtonView) == false) goto L10;
        NumberButtonView.m10107$$Nest$mupdateColors((NumberButtonView) r12);
    L10:
        r0 = r0 + 1;
        goto L3
    }
}
