package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.SystemClock;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;

/* loaded from: classes7.dex */
public class ColorPicker extends FrameLayout {
    private ImageView addButton;
    private Paint circlePaint;
    private boolean circlePressed;
    private ImageView clearButton;
    private EditTextBoldCursor[] colorEditText;
    private LinearGradient colorGradient;
    private float[] colorHSV;
    private boolean colorPressed;
    private Bitmap colorWheelBitmap;
    private Paint colorWheelPaint;
    private int colorWheelWidth;
    private AnimatorSet colorsAnimator;
    private int colorsCount;
    private int currentResetType;
    private final ColorPickerDelegate delegate;
    private float[] hsvTemp;
    boolean ignoreTextChange;
    private long lastUpdateTime;
    private Paint linePaint;
    private LinearLayout linearLayout;
    private float maxBrightness;
    private int maxColorsCount;
    private float maxHsvBrightness;
    private ActionBarMenuItem menuItem;
    private float minBrightness;
    private float minHsvBrightness;
    private boolean myMessagesColor;
    private float pressedMoveProgress;
    private int prevSelectedColor;
    private RadioButton[] radioButton;
    private FrameLayout radioContainer;
    private TextView resetButton;
    Theme.ResourcesProvider resourcesProvider;
    private int selectedColor;
    private RectF sliderRect;
    private Paint valueSliderPaint;

    /* renamed from: org.telegram.ui.Components.ColorPicker$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayout {
        private Paint paint;
        private RectF rect;
        final /* synthetic */ ColorPicker this$0;

        public AnonymousClass1(ColorPicker r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new RectF();
            this.paint = new Paint(1);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onDraw(Canvas r6) {
            this.paint.setColor(ColorPicker.m10037$$Nest$mgetThemedColor(this.this$0, Theme.key_dialogBackgroundGray));
            int r0 = ColorPicker.m10027$$Nest$fgetcolorEditText(this.this$0)[0].getLeft() - AndroidUtilities.dp(13.0f);
            float r1 = AndroidUtilities.dp(91.0f);
            if (ColorPicker.m10026$$Nest$fgetclearButton(this.this$0).getVisibility() != 0) goto L5;
            float r2 = AndroidUtilities.dp(25.0f) * ColorPicker.m10026$$Nest$fgetclearButton(this.this$0).getAlpha();
        L6:
            int r12 = (int) (r1 + r2);
            this.rect.set(r0, AndroidUtilities.dp(5.0f), r0 + r12, AndroidUtilities.dp(37.0f));
            r6.drawRoundRect(this.rect, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), this.paint);
            return;
        L5:
            r2 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.ColorPicker$2, reason: invalid class name */
    public class AnonymousClass2 extends EditTextBoldCursor {
        final /* synthetic */ ColorPicker this$0;
        final /* synthetic */ int val$num;

        public AnonymousClass2(ColorPicker r1, Context r2, int r3) {
            this.this$0 = r1;
            this.val$num = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (getAlpha() == 1.0f) goto L6;
            return false;
        L6:
            if (r3.getAction() != 0) goto L11;
            boolean r32 = ColorPicker.m10027$$Nest$fgetcolorEditText(this.this$0)[this.val$num + 1].isFocused();
            ColorPicker r0 = this.this$0;
            if (r32 == false) goto L10;
            AndroidUtilities.showKeyboard(ColorPicker.m10027$$Nest$fgetcolorEditText(r0)[this.val$num + 1]);
            goto L11
        L10:
            ColorPicker.m10027$$Nest$fgetcolorEditText(r0)[this.val$num + 1].requestFocus();
        L11:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.ColorPicker$3, reason: invalid class name */
    public class AnonymousClass3 extends EditTextBoldCursor {
        final /* synthetic */ ColorPicker this$0;
        final /* synthetic */ int val$num;

        public AnonymousClass3(ColorPicker r1, Context r2, int r3) {
            this.this$0 = r1;
            this.val$num = r3;
            super(r2);
        }

        @Override // android.view.View
        public boolean getGlobalVisibleRect(Rect r2, Point r3) {
            boolean r1 = super.getGlobalVisibleRect(r2, r3);
            r2.bottom += AndroidUtilities.dp(40.0f);
            return r1;
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            ColorPicker.m10027$$Nest$fgetcolorEditText(this.this$0)[this.val$num - 1].invalidate();
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (getAlpha() == 1.0f) goto L6;
            return false;
        L6:
            if (isFocused() == true) goto L9;
            requestFocus();
            return false;
        L9:
            AndroidUtilities.showKeyboard(this);
            return super.onTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.ColorPicker$4, reason: invalid class name */
    public class AnonymousClass4 implements TextWatcher {
        final /* synthetic */ ColorPicker this$0;
        final /* synthetic */ int val$num;

        public AnonymousClass4(ColorPicker r1, int r2) {
            this.this$0 = r1;
            this.val$num = r2;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r8) {
            ColorPicker r0 = this.this$0;
            if (r0.ignoreTextChange == false) goto L5;
            return;
        L5:
            r0.ignoreTextChange = true;
            int r2 = 0;
        L7:
            if (r2 >= r8.length()) goto L22;
            char r3 = r8.charAt(r2);
            if (r3 < '0') goto L13;
            if (r3 > '9') goto L13;
        L21:
            r2 = r2 + 1;
        L13:
            if (r3 < 'a') goto L17;
            if (r3 <= 'f') goto L21;
        L17:
            if (r3 >= 'A') goto L19;
        L20:
            r8.replace(r2, r2 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r2 = r2 - 1;
            goto L21
        L19:
            if (r3 <= 'F') goto L21;
        L22:
            int r22 = r8.length();
            ColorPicker r32 = this.this$0;
            if (r22 != 0) goto L26;
            r32.ignoreTextChange = false;
            return;
        L26:
            ColorPicker.m10038$$Nest$msetColorInner(r32, ColorPicker.m10036$$Nest$mgetFieldColor(r32, this.val$num, -1));
            int r23 = this.this$0.getColor();
            if (r8.length() != 6) goto L29;
            r8.replace(0, r8.length(), String.format("%02x%02x%02x", new Object[]{Byte.valueOf((byte) Color.red(r23)), Byte.valueOf((byte) Color.green(r23)), Byte.valueOf((byte) Color.blue(r23))}).toUpperCase());
            ColorPicker.m10027$$Nest$fgetcolorEditText(this.this$0)[this.val$num].setSelection(r8.length());
        L29:
            ColorPicker.m10032$$Nest$fgetradioButton(this.this$0)[ColorPicker.m10034$$Nest$fgetselectedColor(this.this$0)].setColor(r23);
            ColorPicker.m10029$$Nest$fgetdelegate(this.this$0).setColor(r23, ColorPicker.m10034$$Nest$fgetselectedColor(this.this$0), true);
            this.this$0.ignoreTextChange = false;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.ColorPicker$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ ColorPicker this$0;

        public AnonymousClass5(ColorPicker r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ColorPicker.m10028$$Nest$fgetcolorsCount(this.this$0) != ColorPicker.m10031$$Nest$fgetmaxColorsCount(this.this$0)) goto L5;
            ColorPicker.m10025$$Nest$fgetaddButton(this.this$0).setVisibility(4);
        L5:
            ColorPicker.m10035$$Nest$fputcolorsAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.ColorPicker$6, reason: invalid class name */
    public class AnonymousClass6 extends ImageView {
        final /* synthetic */ ColorPicker this$0;

        public AnonymousClass6(ColorPicker r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            ColorPicker.m10030$$Nest$fgetlinearLayout(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.ColorPicker$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ ColorPicker this$0;

        public AnonymousClass7(ColorPicker r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            if (ColorPicker.m10028$$Nest$fgetcolorsCount(this.this$0) != 1) goto L5;
            ColorPicker.m10026$$Nest$fgetclearButton(this.this$0).setVisibility(4);
        L5:
            int r42 = 0;
        L6:
            int r0 = ColorPicker.m10032$$Nest$fgetradioButton(this.this$0).length;
            ColorPicker r2 = this.this$0;
            if (r42 >= r0) goto L12;
            if (ColorPicker.m10032$$Nest$fgetradioButton(r2)[r42].getTag(R.id.index_tag) != null) goto L11;
            ColorPicker.m10032$$Nest$fgetradioButton(this.this$0)[r42].setVisibility(4);
        L11:
            r42 = r42 + 1;
            goto L6
        L12:
            ColorPicker.m10035$$Nest$fputcolorsAnimator(r2, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.ColorPicker$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ ColorPicker this$0;
        final /* synthetic */ boolean val$value;

        public AnonymousClass8(ColorPicker r1, boolean r2) {
            this.this$0 = r1;
            this.val$value = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$value == true) goto L6;
            ColorPicker.m10033$$Nest$fgetresetButton(this.this$0).setVisibility(8);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ColorPicker$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ ColorPicker this$0;
        final /* synthetic */ int val$maxColorsCount;

        public AnonymousClass9(ColorPicker r1, int r2) {
            this.this$0 = r1;
            this.val$maxColorsCount = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.val$maxColorsCount > 1) goto L6;
            ColorPicker.m10026$$Nest$fgetclearButton(this.this$0).setVisibility(8);
            return;
        }
    }

    public interface ColorPickerDelegate {
        default void deleteTheme() {
        }

        default int getDefaultColor(int r1) {
            return 0;
        }

        default void openThemeCreate(boolean r1) {
        }

        void setColor(int r1, int r2, boolean r3);
    }

    public static class RadioButton extends View {
        private ObjectAnimator checkAnimator;
        private boolean checked;
        private float checkedState;
        private int currentColor;
        private final Paint paint;

        public RadioButton(Context r2) {
            super(r2);
            this.paint = new Paint(1);
        }

        @Keep
        public float getCheckedState() {
            return this.checkedState;
        }

        public int getColor() {
            return this.currentColor;
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            updateCheckedState(false);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            float r0 = AndroidUtilities.dp(15.0f);
            float r1 = getMeasuredWidth() * 0.5f;
            float r3 = getMeasuredHeight() * 0.5f;
            this.paint.setColor(this.currentColor);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(AndroidUtilities.dp(3.0f));
            this.paint.setAlpha(Math.round(this.checkedState * 255.0f));
            r8.drawCircle(r1, r3, r0 - (this.paint.getStrokeWidth() * 0.5f), this.paint);
            this.paint.setAlpha(255);
            this.paint.setStyle(Paint.Style.FILL);
            r8.drawCircle(r1, r3, r0 - (AndroidUtilities.dp(5.0f) * this.checkedState), this.paint);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            r2.setText(LocaleController.getString(R.string.ColorPickerMainColor));
            r2.setClassName(Button.class.getName());
            r2.setChecked(this.checked);
            r2.setCheckable(true);
            r2.setEnabled(true);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(30.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(30.0f), TLObject.FLAG_30));
        }

        public void setChecked(boolean r1, boolean r2) {
            this.checked = r1;
            updateCheckedState(r2);
        }

        @Keep
        public void setCheckedState(float r1) {
            this.checkedState = r1;
            invalidate();
        }

        public void setColor(int r1) {
            this.currentColor = r1;
            invalidate();
        }

        public void updateCheckedState(boolean r4) {
            ObjectAnimator r0 = this.checkAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            boolean r02 = this.checked;
            float r1 = 0.0f;
            if (r4 == false) goto L11;
            if (r02 == false) goto L9;
            r1 = 1.0f;
        L9:
            ObjectAnimator r42 = ObjectAnimator.ofFloat(this, "checkedState", new float[]{r1});
            this.checkAnimator = r42;
            r42.setDuration(200);
            this.checkAnimator.start();
            return;
        L11:
            if (r02 == false) goto L13;
            r1 = 1.0f;
        L13:
            setCheckedState(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$6i0YKfMp5wvAYiw6zgouxlslHjs(ColorPicker r0, View r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$InqtIePNoE4CsS2SG6_Q5sSXZIs(TextView r0, int r1, KeyEvent r2) {
        if (r1 != 6) goto L6;
        AndroidUtilities.hideKeyboard(r0);
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ void $r8$lambda$MKFloAoZQIqBkwJxoCpBUNw1G_4(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$d7SZ5xrA8qFmfP_8SIHss9cyvTQ(ColorPicker r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$hkUGUSAWHfcHbmQMW8MHsbiQQoY(ColorPicker r0, int r1) {
        r0.lambda$new$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$rAhaymTtEhpKsvD_6JwPkacVUhI(ColorPicker r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$rjpnKps7i2XOFMjXyjriXE-sSOc, reason: not valid java name */
    public static /* synthetic */ void m10024$r8$lambda$rjpnKps7i2XOFMjXyjriXEsSOc(ColorPicker r0, View r1) {
        r0.lambda$new$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$y_E7eP_Acbm85kGSp6iOFvSpILc(ColorPicker r0) {
        r0.lambda$provideThemeDescriptions$7();
    }

    /* renamed from: -$$Nest$fgetaddButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m10025$$Nest$fgetaddButton(ColorPicker r0) {
        return r0.addButton;
    }

    /* renamed from: -$$Nest$fgetclearButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m10026$$Nest$fgetclearButton(ColorPicker r0) {
        return r0.clearButton;
    }

    /* renamed from: -$$Nest$fgetcolorEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor[] m10027$$Nest$fgetcolorEditText(ColorPicker r0) {
        return r0.colorEditText;
    }

    /* renamed from: -$$Nest$fgetcolorsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10028$$Nest$fgetcolorsCount(ColorPicker r0) {
        return r0.colorsCount;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ColorPickerDelegate m10029$$Nest$fgetdelegate(ColorPicker r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetlinearLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m10030$$Nest$fgetlinearLayout(ColorPicker r0) {
        return r0.linearLayout;
    }

    /* renamed from: -$$Nest$fgetmaxColorsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10031$$Nest$fgetmaxColorsCount(ColorPicker r0) {
        return r0.maxColorsCount;
    }

    /* renamed from: -$$Nest$fgetradioButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadioButton[] m10032$$Nest$fgetradioButton(ColorPicker r0) {
        return r0.radioButton;
    }

    /* renamed from: -$$Nest$fgetresetButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m10033$$Nest$fgetresetButton(ColorPicker r0) {
        return r0.resetButton;
    }

    /* renamed from: -$$Nest$fgetselectedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10034$$Nest$fgetselectedColor(ColorPicker r0) {
        return r0.selectedColor;
    }

    /* renamed from: -$$Nest$fputcolorsAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10035$$Nest$fputcolorsAnimator(ColorPicker r0, AnimatorSet r1) {
        r0.colorsAnimator = r1;
    }

    /* renamed from: -$$Nest$mgetFieldColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10036$$Nest$mgetFieldColor(ColorPicker r0, int r1, int r2) {
        return r0.getFieldColor(r1, r2);
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10037$$Nest$mgetThemedColor(ColorPicker r0, int r1) {
        return r0.getThemedColor(r1);
    }

    /* renamed from: -$$Nest$msetColorInner, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10038$$Nest$msetColorInner(ColorPicker r0, int r1) {
        r0.setColorInner(r1);
    }

    public ColorPicker(Context r23, boolean r24, ColorPickerDelegate r25) {
        super(r23);
        this.sliderRect = new RectF();
        this.radioButton = new RadioButton[4];
        this.colorsCount = 1;
        this.maxColorsCount = 1;
        this.colorHSV = new float[]{0.0f, 0.0f, 1.0f};
        this.hsvTemp = new float[3];
        this.pressedMoveProgress = 1.0f;
        this.minBrightness = 0.0f;
        this.maxBrightness = 1.0f;
        this.minHsvBrightness = 0.0f;
        this.maxHsvBrightness = 1.0f;
        this.delegate = r25;
        this.colorEditText = new EditTextBoldCursor[2];
        setWillNotDraw(false);
        this.circlePaint = new Paint(1);
        this.colorWheelPaint = new Paint(5);
        this.valueSliderPaint = new Paint(5);
        Paint r8 = new Paint();
        this.linePaint = r8;
        r8.setColor(301989888);
        setClipChildren(false);
        AnonymousClass1 r82 = new AnonymousClass1(this, r23);
        this.linearLayout = r82;
        r82.setOrientation(0);
        addView(this.linearLayout, LayoutHelper.createFrame(-1, 54.0f, 51, 27.0f, -6.0f, 17.0f, 0.0f));
        this.linearLayout.setWillNotDraw(false);
        FrameLayout r83 = new FrameLayout(r23);
        this.radioContainer = r83;
        r83.setClipChildren(false);
        addView(this.radioContainer, LayoutHelper.createFrame(174, 30.0f, 49, 72.0f, 1.0f, 0.0f, 0.0f));
        int r84 = 0;
    L3:
        if (r84 >= 4) goto L9;
        this.radioButton[r84] = new RadioButton(r23);
        RadioButton r9 = this.radioButton[r84];
        if (this.selectedColor != r84) goto L7;
        boolean r10 = true;
    L8:
        r9.setChecked(r10, false);
        this.radioContainer.addView(this.radioButton[r84], LayoutHelper.createFrame(30, 30.0f, 48, 0.0f, 0.0f, 0.0f, 0.0f));
        this.radioButton[r84].setOnClickListener(new ColorPicker$$ExternalSyntheticLambda0(this));
        r84 = r84 + 1;
        goto L3
    L7:
        r10 = false;
        goto L8
    L9:
        int r85 = 0;
    L10:
        EditTextBoldCursor[] r92 = this.colorEditText;
        if (r85 >= r92.length) goto L23;
        if ((r85 % 2) != 0) goto L15;
        r92[r85] = new AnonymousClass2(this, r23, r85);
        this.colorEditText[r85].setBackgroundDrawable(null);
        this.colorEditText[r85].setText("#");
        this.colorEditText[r85].setEnabled(false);
        this.colorEditText[r85].setFocusable(false);
        this.colorEditText[r85].setPadding(0, AndroidUtilities.dp(5.0f), 0, AndroidUtilities.dp(16.0f));
        this.linearLayout.addView(this.colorEditText[r85], LayoutHelper.createLinear(-2, -1, 0.0f, 0.0f, 0.0f, 0.0f));
    L16:
        this.colorEditText[r85].setTextSize(1, 16.0f);
        this.colorEditText[r85].setHintTextColor(getThemedColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r93 = this.colorEditText[r85];
        int r102 = Theme.key_windowBackgroundWhiteBlackText;
        r93.setTextColor(getThemedColor(r102));
        this.colorEditText[r85].setCursorColor(getThemedColor(r102));
        this.colorEditText[r85].setCursorSize(AndroidUtilities.dp(18.0f));
        this.colorEditText[r85].setCursorWidth(1.5f);
        this.colorEditText[r85].setSingleLine(true);
        this.colorEditText[r85].setGravity(19);
        this.colorEditText[r85].setHeaderHintColor(getThemedColor(Theme.key_windowBackgroundWhiteBlueHeader));
        this.colorEditText[r85].setTransformHintToHeader(true);
        this.colorEditText[r85].setInputType(524416);
        this.colorEditText[r85].setImeOptions(268435462);
        if (r85 != 1) goto L19;
        this.colorEditText[r85].requestFocus();
    L22:
        r85 = r85 + 1;
        goto L10
    L19:
        if (r85 == 2) goto L21;
        if (r85 != 3) goto L22;
    L21:
        this.colorEditText[r85].setVisibility(8);
        goto L22
    L15:
        r92[r85] = new AnonymousClass3(this, r23, r85);
        this.colorEditText[r85].setBackgroundDrawable(null);
        this.colorEditText[r85].setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        this.colorEditText[r85].setHint("8BC6ED");
        this.colorEditText[r85].setPadding(0, AndroidUtilities.dp(5.0f), 0, AndroidUtilities.dp(16.0f));
        this.linearLayout.addView(this.colorEditText[r85], LayoutHelper.createLinear(71, -1, 0.0f, 0.0f, 0.0f, 0.0f));
        this.colorEditText[r85].addTextChangedListener(new AnonymousClass4(this, r85));
        this.colorEditText[r85].setOnEditorActionListener(new ColorPicker$$ExternalSyntheticLambda1());
        goto L16
    L23:
        ImageView r86 = new ImageView(getContext());
        this.addButton = r86;
        int r94 = Theme.key_dialogButtonSelector;
        r86.setBackground(Theme.createSelectorDrawable(getThemedColor(r94), 1));
        this.addButton.setImageResource(R.drawable.msg_add);
        ImageView r87 = this.addButton;
        int r12 = Theme.key_windowBackgroundWhiteBlackText;
        int r13 = getThemedColor(r12);
        PorterDuff.Mode r14 = PorterDuff.Mode.MULTIPLY;
        r87.setColorFilter(new PorterDuffColorFilter(r13, r14));
        ImageView r88 = this.addButton;
        ImageView.ScaleType r103 = ImageView.ScaleType.CENTER;
        r88.setScaleType(r103);
        this.addButton.setOnClickListener(new ColorPicker$$ExternalSyntheticLambda2(this));
        this.addButton.setContentDescription(LocaleController.getString(R.string.Add));
        addView(this.addButton, LayoutHelper.createFrame(30, 30.0f, 49, 36.0f, 1.0f, 0.0f, 0.0f));
        AnonymousClass6 r89 = new AnonymousClass6(this, getContext());
        this.clearButton = r89;
        r89.setBackground(Theme.createSelectorDrawable(getThemedColor(r94), 1));
        this.clearButton.setImageResource(R.drawable.msg_close);
        this.clearButton.setColorFilter(new PorterDuffColorFilter(getThemedColor(r12), r14));
        this.clearButton.setAlpha(0.0f);
        this.clearButton.setScaleX(0.0f);
        this.clearButton.setScaleY(0.0f);
        this.clearButton.setScaleType(r103);
        this.clearButton.setVisibility(4);
        this.clearButton.setOnClickListener(new ColorPicker$$ExternalSyntheticLambda3(this));
        this.clearButton.setContentDescription(LocaleController.getString(R.string.ClearButton));
        addView(this.clearButton, LayoutHelper.createFrame(30, 30.0f, 51, 97.0f, 1.0f, 0.0f, 0.0f));
        TextView r2 = new TextView(r23);
        this.resetButton = r2;
        r2.setTextSize(1, 15.0f);
        this.resetButton.setTypeface(AndroidUtilities.bold());
        this.resetButton.setGravity(17);
        this.resetButton.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        this.resetButton.setTextColor(getThemedColor(r12));
        addView(this.resetButton, LayoutHelper.createFrame(-2, 36.0f, 53, 0.0f, 3.0f, 14.0f, 0.0f));
        this.resetButton.setOnClickListener(new ColorPicker$$ExternalSyntheticLambda4());
        if (r24 == false) goto L26;
        ActionBarMenuItem r22 = new ActionBarMenuItem(r23, null, 0, getThemedColor(r12));
        this.menuItem = r22;
        r22.setLongClickEnabled(false);
        this.menuItem.setIcon(R.drawable.ic_ab_other);
        this.menuItem.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        this.menuItem.addSubItem(1, R.drawable.msg_edit, LocaleController.getString(R.string.OpenInEditor));
        this.menuItem.addSubItem(2, R.drawable.msg_share, LocaleController.getString(R.string.ShareTheme));
        this.menuItem.addSubItem(3, R.drawable.msg_delete, LocaleController.getString(R.string.DeleteTheme));
        this.menuItem.setMenuYOffset(-AndroidUtilities.dp(80.0f));
        this.menuItem.setSubMenuOpenSide(2);
        this.menuItem.setDelegate(new ColorPicker$$ExternalSyntheticLambda5(this));
        this.menuItem.setAdditionalYOffset(AndroidUtilities.dp(72.0f));
        this.menuItem.setTranslationX(AndroidUtilities.dp(6.0f));
        this.menuItem.setBackgroundDrawable(Theme.createSelectorDrawable(getThemedColor(r94), 1));
        addView(this.menuItem, LayoutHelper.createFrame(30, 30.0f, 53, 0.0f, 2.0f, 10.0f, 0.0f));
        this.menuItem.setOnClickListener(new ColorPicker$$ExternalSyntheticLambda6(this));
    L26:
        updateColorsPosition(null, 0, false, getMeasuredWidth());
    }

    private Bitmap createColorWheelBitmap(int r19, int r20) {
        Bitmap r3 = Bitmap.createBitmap(r19, r20, Bitmap.Config.ARGB_8888);
        float r7 = r19;
        int[] r9 = {Opcodes.V_PREVIEW, -256, -16711936, -16711681, -16776961, -65281, Opcodes.V_PREVIEW};
        Shader.TileMode r17 = Shader.TileMode.CLAMP;
        float r14 = r20;
        this.colorWheelPaint.setShader(new ComposeShader(new LinearGradient(0.0f, r20 / 3, 0.0f, r14, new int[]{-1, 0}, null, r17), new LinearGradient(0.0f, 0.0f, r7, 0.0f, r9, null, r17), PorterDuff.Mode.MULTIPLY));
        new Canvas(r3).drawRect(0.0f, 0.0f, r7, r14, this.colorWheelPaint);
        return r3;
    }

    private void drawPointerArrow(Canvas r3, int r4, int r5, int r6, boolean r7) {
        if (r7 == false) goto L4;
        float r0 = 12.0f;
    L5:
        AndroidUtilities.dp(r0);
        this.circlePaint.setColor(-9408400);
        float r42 = r4;
        float r52 = r5;
        if (r7 == false) goto L8;
        float r02 = 11.5f;
    L9:
        r3.drawCircle(r42, r52, AndroidUtilities.dp(r02), this.circlePaint);
        this.circlePaint.setColor(-1);
        if (r7 == false) goto L12;
        float r03 = 11.0f;
    L13:
        r3.drawCircle(r42, r52, AndroidUtilities.dp(r03), this.circlePaint);
        this.circlePaint.setColor(r6);
        if (r7 == false) goto L16;
        float r62 = 9.0f;
    L17:
        r3.drawCircle(r42, r52, AndroidUtilities.dp(r62), this.circlePaint);
        return;
    L16:
        r62 = 13.0f;
        goto L17
    L12:
        r03 = 15.0f;
        goto L13
    L8:
        r02 = 15.5f;
        goto L9
    L4:
        r0 = 16.0f;
        goto L5
    }

    public static int generateGradientColors(int r4) {
        float[] r0 = new float[3];
        Color.colorToHSV(r4, r0);
        float r1 = r0[1];
        if (r1 <= 0.5f) goto L5;
        r0[1] = r1 - 0.15f;
    L6:
        float r12 = r0[0];
        if (r12 <= 180.0f) goto L9;
        r0[0] = r12 - 20.0f;
    L11:
        return Color.HSVToColor(255, r0);
    L9:
        r0[0] = r12 + 20.0f;
        goto L11
    L5:
        r0[1] = r1 + 0.15f;
        goto L6
    }

    private float getBrightness() {
        return Math.max(this.minHsvBrightness, Math.min(this.colorHSV[2], this.maxHsvBrightness));
    }

    private int getFieldColor(int r1, int r2) {
        return Integer.parseInt(this.colorEditText[r1].getText().toString(), 16) | (-16777216);
    L5:
        return r2;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$new$0(View r6) {
        RadioButton r62 = (RadioButton) r6;
        int r1 = 0;
    L3:
        RadioButton[] r2 = this.radioButton;
        if (r1 >= r2.length) goto L13;
        RadioButton r22 = r2[r1];
        if (r22 != r62) goto L8;
        boolean r3 = true;
    L9:
        r22.setChecked(r3, true);
        if (r3 == false) goto L12;
        this.prevSelectedColor = this.selectedColor;
        this.selectedColor = r1;
    L12:
        r1 = r1 + 1;
        goto L3
    L8:
        r3 = false;
        goto L9
    L13:
        int r63 = r62.getColor();
        setColorInner(r63);
        this.colorEditText[1].setText(String.format("%02x%02x%02x", new Object[]{Byte.valueOf((byte) Color.red(r63)), Byte.valueOf((byte) Color.green(r63)), Byte.valueOf((byte) Color.blue(r63))}).toUpperCase());
    }

    private /* synthetic */ void lambda$new$2(View r14) {
        if (this.colorsAnimator != null) goto L42;
        int r142 = this.colorsCount;
        if (r142 != 1) goto L15;
        if (this.radioButton[1].getColor() != 0) goto L11;
        RadioButton[] r143 = this.radioButton;
        r143[1].setColor(generateGradientColors(r143[0].getColor()));
    L11:
        if (this.myMessagesColor == false) goto L13;
        this.delegate.setColor(this.radioButton[0].getColor(), 0, true);
    L13:
        this.delegate.setColor(this.radioButton[1].getColor(), 1, true);
        this.colorsCount = 2;
    L29:
        ArrayList<Animator> r144 = new ArrayList();
        int r0 = this.colorsCount;
        int r3 = this.maxColorsCount;
        ImageView r4 = this.addButton;
        Property r7 = View.TRANSLATION_X;
        Property r8 = View.SCALE_Y;
        Property r9 = View.SCALE_X;
        Property r10 = View.ALPHA;
        if (r0 >= r3) goto L32;
        r144.add(ObjectAnimator.ofFloat(r4, r10, new float[]{1.0f}));
        r144.add(ObjectAnimator.ofFloat(this.addButton, r9, new float[]{1.0f}));
        r144.add(ObjectAnimator.ofFloat(this.addButton, r8, new float[]{1.0f}));
        r144.add(ObjectAnimator.ofFloat(this.addButton, r7, new float[]{(AndroidUtilities.dp(30.0f) * (this.colorsCount - 1)) + (AndroidUtilities.dp(13.0f) * (this.colorsCount - 1))}));
    L34:
        if (this.colorsCount > 1) goto L36;
    L39:
        this.radioButton[this.colorsCount - 1].callOnClick();
        this.colorsAnimator = new AnimatorSet();
        updateColorsPosition(r144, 0, false, getMeasuredWidth());
        this.colorsAnimator.playTogether(r144);
        this.colorsAnimator.setDuration(180);
        this.colorsAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.colorsAnimator.addListener(new AnonymousClass5(this));
        this.colorsAnimator.start();
        return;
    L36:
        if (this.clearButton.getVisibility() == 0) goto L38;
        this.clearButton.setScaleX(0.0f);
        this.clearButton.setScaleY(0.0f);
    L38:
        this.clearButton.setVisibility(0);
        r144.add(ObjectAnimator.ofFloat(this.clearButton, r10, new float[]{1.0f}));
        r144.add(ObjectAnimator.ofFloat(this.clearButton, r9, new float[]{1.0f}));
        r144.add(ObjectAnimator.ofFloat(this.clearButton, r8, new float[]{1.0f}));
        goto L39
    L32:
        r144.add(ObjectAnimator.ofFloat(r4, r7, new float[]{(AndroidUtilities.dp(30.0f) * (this.colorsCount - 1)) + (AndroidUtilities.dp(13.0f) * (this.colorsCount - 1))}));
        r144.add(ObjectAnimator.ofFloat(this.addButton, r10, new float[]{0.0f}));
        r144.add(ObjectAnimator.ofFloat(this.addButton, r9, new float[]{0.0f}));
        r144.add(ObjectAnimator.ofFloat(this.addButton, r8, new float[]{0.0f}));
        goto L34
    L15:
        if (r142 != 2) goto L24;
        this.colorsCount = 3;
        if (this.radioButton[2].getColor() != 0) goto L23;
        float[] r32 = new float[3];
        Color.colorToHSV(this.radioButton[0].getColor(), r32);
        float r145 = r32[0];
        if (r145 <= 180.0f) goto L21;
        r32[0] = r145 - 60.0f;
    L22:
        this.radioButton[2].setColor(Color.HSVToColor(255, r32));
        goto L23
    L21:
        r32[0] = r145 + 60.0f;
    L23:
        this.delegate.setColor(this.radioButton[2].getColor(), 2, true);
        goto L29
    L24:
        if (r142 != 3) goto L41;
        this.colorsCount = 4;
        if (this.radioButton[3].getColor() != 0) goto L28;
        RadioButton[] r146 = this.radioButton;
        r146[3].setColor(generateGradientColors(r146[2].getColor()));
    L28:
        this.delegate.setColor(this.radioButton[3].getColor(), 3, true);
        goto L29
    L41:
        return;
    }

    private /* synthetic */ void lambda$new$3(View r14) {
        if (this.colorsAnimator != null) goto L54;
        ArrayList<Animator> r142 = new ArrayList();
        int r0 = this.colorsCount;
        Property r1 = View.TRANSLATION_X;
        Property r2 = View.SCALE_Y;
        Property r3 = View.SCALE_X;
        Property r4 = View.ALPHA;
        if (r0 != 2) goto L9;
        this.colorsCount = 1;
        r142.add(ObjectAnimator.ofFloat(this.clearButton, r4, new float[]{0.0f}));
        r142.add(ObjectAnimator.ofFloat(this.clearButton, r3, new float[]{0.0f}));
        r142.add(ObjectAnimator.ofFloat(this.clearButton, r2, new float[]{0.0f}));
        r142.add(ObjectAnimator.ofFloat(this.addButton, r1, new float[]{0.0f}));
    L14:
        int r02 = this.colorsCount;
        int r12 = this.maxColorsCount;
        ImageView r6 = this.addButton;
        if (r02 >= r12) goto L17;
        r6.setVisibility(0);
        r142.add(ObjectAnimator.ofFloat(this.addButton, r4, new float[]{1.0f}));
        r142.add(ObjectAnimator.ofFloat(this.addButton, r3, new float[]{1.0f}));
        r142.add(ObjectAnimator.ofFloat(this.addButton, r2, new float[]{1.0f}));
    L18:
        int r03 = this.selectedColor;
        if (r03 == 3) goto L25;
        RadioButton r13 = this.radioButton[r03];
        int r04 = r03 + 1;
    L21:
        RadioButton[] r22 = this.radioButton;
        if (r04 >= r22.length) goto L24;
        r22[r04 - 1] = r22[r04];
        r04 = r04 + 1;
        goto L21
    L24:
        r22[3] = r13;
    L25:
        int r05 = this.prevSelectedColor;
        if (r05 >= 0) goto L28;
    L30:
        this.radioButton[this.colorsCount - 1].callOnClick();
    L31:
        int r06 = 0;
    L32:
        RadioButton[] r15 = this.radioButton;
        if (r06 >= r15.length) goto L47;
        int r23 = this.colorsCount;
        ColorPickerDelegate r32 = this.delegate;
        if (r06 >= r23) goto L42;
        int r16 = r15[r06].getColor();
        if (r06 != (this.radioButton.length - 1)) goto L39;
        boolean r24 = true;
    L40:
        r32.setColor(r16, r06, r24);
    L46:
        r06 = r06 + 1;
        goto L32
    L39:
        r24 = false;
        goto L40
    L42:
        if (r06 != (r15.length - 1)) goto L44;
        boolean r17 = true;
    L45:
        r32.setColor(0, r06, r17);
        goto L46
    L44:
        r17 = false;
        goto L45
    L47:
        this.colorsAnimator = new AnimatorSet();
        updateColorsPosition(r142, this.selectedColor, true, getMeasuredWidth());
        this.colorsAnimator.playTogether(r142);
        this.colorsAnimator.setDuration(180);
        this.colorsAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.colorsAnimator.addListener(new AnonymousClass7(this));
        this.colorsAnimator.start();
        return;
    L28:
        if (r05 >= this.selectedColor) goto L30;
        this.radioButton[r05].callOnClick();
        goto L31
    L17:
        r142.add(ObjectAnimator.ofFloat(r6, r4, new float[]{0.0f}));
        r142.add(ObjectAnimator.ofFloat(this.addButton, r3, new float[]{0.0f}));
        r142.add(ObjectAnimator.ofFloat(this.addButton, r2, new float[]{0.0f}));
        goto L18
    L9:
        if (r0 != 3) goto L12;
        this.colorsCount = 2;
        r142.add(ObjectAnimator.ofFloat(this.addButton, r1, new float[]{AndroidUtilities.dp(30.0f) + AndroidUtilities.dp(13.0f)}));
        goto L14
    L12:
        if (r0 != 4) goto L53;
        this.colorsCount = 3;
        r142.add(ObjectAnimator.ofFloat(this.addButton, r1, new float[]{(AndroidUtilities.dp(30.0f) * 2) + (AndroidUtilities.dp(13.0f) * 2)}));
        goto L14
    L53:
        return;
    }

    private /* synthetic */ void lambda$new$5(int r3) {
        boolean r1 = true;
        if (r3 == 1) goto L10;
        if (r3 == 2) goto L10;
        if (r3 != 3) goto L16;
        this.delegate.deleteTheme();
        return;
    L16:
        return;
    L10:
        ColorPickerDelegate r2 = this.delegate;
        if (r3 == 2) goto L14;
        r1 = false;
    L14:
        r2.openThemeCreate(r1);
    }

    private /* synthetic */ void lambda$new$6(View r1) {
        this.menuItem.toggleSubMenu();
    }

    private /* synthetic */ void lambda$provideThemeDescriptions$7() {
        this.menuItem.setIconColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        Theme.setDrawableColor(this.menuItem.getBackground(), getThemedColor(Theme.key_dialogButtonSelector));
        this.menuItem.setPopupItemsColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem), false);
        this.menuItem.setPopupItemsColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon), true);
        this.menuItem.redrawPopup(getThemedColor(Theme.key_actionBarDefaultSubmenuBackground));
    }

    private void setColorInner(int r3) {
        Color.colorToHSV(r3, this.colorHSV);
        int r0 = this.delegate.getDefaultColor(this.selectedColor);
        if (r0 == 0) goto L5;
        if (r0 != r3) goto L5;
    L6:
        this.colorGradient = null;
        invalidate();
        return;
    L5:
        updateHsvMinMaxBrightness();
        goto L6
    }

    private void updateColorsPosition(ArrayList<Animator> r17, int r18, boolean r19, int r20) {
        int r2 = this.colorsCount;
        int r22 = this.radioContainer.getLeft() + ((AndroidUtilities.dp(30.0f) * r2) + ((r2 - 1) * AndroidUtilities.dp(13.0f)));
        if (this.currentResetType != 1) goto L5;
        float r4 = 50.0f;
    L6:
        int r42 = r20 - AndroidUtilities.dp(r4);
        if (r22 <= r42) goto L9;
        float r23 = r22 - r42;
    L10:
        FrameLayout r43 = this.radioContainer;
        Property r8 = View.TRANSLATION_X;
        if (r17 == null) goto L13;
        r17.add(ObjectAnimator.ofFloat(r43, r8, new float[]{-r23}));
    L14:
        int r24 = 0;
        int r44 = 0;
    L15:
        RadioButton[] r10 = this.radioButton;
        if (r24 >= r10.length) goto L49;
        if (r10[r24].getTag(R.id.index_tag) == null) goto L20;
        boolean r102 = true;
    L21:
        int r11 = this.colorsCount;
        Property r12 = View.SCALE_Y;
        Property r13 = View.SCALE_X;
        Property r14 = View.ALPHA;
        if (r24 >= r11) goto L39;
        this.radioButton[r24].setVisibility(0);
        if (r17 == null) goto L34;
        if (r102 == true) goto L27;
        r17.add(ObjectAnimator.ofFloat(this.radioButton[r24], r14, new float[]{1.0f}));
        r17.add(ObjectAnimator.ofFloat(this.radioButton[r24], r13, new float[]{1.0f}));
        r17.add(ObjectAnimator.ofFloat(this.radioButton[r24], r12, new float[]{1.0f}));
    L27:
        if (r19 == true) goto L33;
        if (r19 == false) goto L30;
    L32:
        this.radioButton[r24].setTranslationX(r44);
    L38:
        this.radioButton[r24].setTag(R.id.index_tag, 1);
    L48:
        r44 = r44 + (AndroidUtilities.dp(30.0f) + AndroidUtilities.dp(13.0f));
        r24 = r24 + 1;
        goto L15
    L30:
        if (r24 == (this.colorsCount - 1)) goto L32;
    L33:
        r17.add(ObjectAnimator.ofFloat(this.radioButton[r24], r8, new float[]{r44}));
        goto L38
    L34:
        this.radioButton[r24].setVisibility(0);
        if (this.colorsAnimator != null) goto L37;
        this.radioButton[r24].setAlpha(1.0f);
        this.radioButton[r24].setScaleX(1.0f);
        this.radioButton[r24].setScaleY(1.0f);
    L37:
        this.radioButton[r24].setTranslationX(r44);
        goto L38
    L39:
        if (r17 == null) goto L42;
        if (r102 == false) goto L45;
        r17.add(ObjectAnimator.ofFloat(this.radioButton[r24], r14, new float[]{0.0f}));
        r17.add(ObjectAnimator.ofFloat(this.radioButton[r24], r13, new float[]{0.0f}));
        r17.add(ObjectAnimator.ofFloat(this.radioButton[r24], r12, new float[]{0.0f}));
    L45:
        if (r19 == true) goto L47;
        this.radioButton[r24].setTranslationX(r44);
    L47:
        this.radioButton[r24].setTag(R.id.index_tag, null);
        goto L48
    L42:
        this.radioButton[r24].setVisibility(4);
        if (this.colorsAnimator != null) goto L45;
        this.radioButton[r24].setAlpha(0.0f);
        this.radioButton[r24].setScaleX(0.0f);
        this.radioButton[r24].setScaleY(0.0f);
        goto L45
    L20:
        r102 = false;
        goto L21
    L49:
        return;
    L13:
        r43.setTranslationX(-r23);
        goto L14
    L9:
        r23 = 0.0f;
        goto L10
    L5:
        r4 = 0.0f;
        goto L6
    }

    private void updateHsvMinMaxBrightness() {
        ImageView r0 = this.clearButton;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.getTag() == null) goto L8;
        float r02 = 0.0f;
    L10:
        if (this.clearButton.getTag() == null) goto L12;
        float r2 = 1.0f;
    L13:
        float[] r4 = this.colorHSV;
        float r6 = r4[2];
        if (r02 == 0.0f) goto L16;
    L19:
        r4[2] = 1.0f;
        int r42 = Color.HSVToColor(r4);
        this.colorHSV[2] = r6;
        float r43 = AndroidUtilities.computePerceivedBrightness(r42);
        float r03 = Math.max(0.0f, Math.min(r02 / r43, 1.0f));
        this.minHsvBrightness = r03;
        this.maxHsvBrightness = Math.max(r03, Math.min(r2 / r43, 1.0f));
        return;
    L16:
        if (r2 != 1.0f) goto L19;
        this.minHsvBrightness = 0.0f;
        this.maxHsvBrightness = 1.0f;
        return;
    L12:
        r2 = this.maxBrightness;
        goto L13
    L8:
        r02 = this.minBrightness;
        goto L10
    }

    public int getColor() {
        float[] r0 = this.hsvTemp;
        float[] r1 = this.colorHSV;
        r0[0] = r1[0];
        r0[1] = r1[1];
        r0[2] = getBrightness();
        return (Color.HSVToColor(this.hsvTemp) & 16777215) | (-16777216);
    }

    public void hideKeyboard() {
        AndroidUtilities.hideKeyboard(this.colorEditText[1]);
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        this.linearLayout.invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas r20) {
        int r7 = AndroidUtilities.dp(45.0f);
        float r3 = r7;
        r20.drawBitmap(this.colorWheelBitmap, 0.0f, r3, null);
        int r8 = r7 + this.colorWheelBitmap.getHeight();
        r20.drawRect(0.0f, r3, getMeasuredWidth(), r7 + 1, this.linePaint);
        r20.drawRect(0.0f, r8 - 1, getMeasuredWidth(), r8, this.linePaint);
        float[] r1 = this.hsvTemp;
        float[] r2 = this.colorHSV;
        r1[0] = r2[0];
        r1[1] = r2[1];
        r1[2] = 1.0f;
        int r12 = (int) ((r2[0] * getMeasuredWidth()) / 360.0f);
        int r22 = (int) (r3 + (this.colorWheelBitmap.getHeight() * (1.0f - this.colorHSV[1])));
        if (this.circlePressed == true) goto L14;
        int r32 = AndroidUtilities.dp(16.0f);
        float r4 = CubicBezierInterpolator.EASE_OUT.getInterpolation(this.pressedMoveProgress);
        if (r12 >= r32) goto L9;
        float r5 = r12 + ((r32 - r12) * r4);
    L7:
        r12 = (int) r5;
    L12:
        if (r22 >= (r7 + r32)) goto L16;
        r22 = (int) (r22 + (r4 * (r5 - r22)));
        goto L14
    L16:
        if (r22 <= ((this.colorWheelBitmap.getHeight() + r7) - r32)) goto L14;
        r22 = (int) (r22 - (r4 * (r22 - ((r7 + this.colorWheelBitmap.getHeight()) - r32))));
        goto L14
    L9:
        if (r12 <= (getMeasuredWidth() - r32)) goto L12;
        r5 = r12 - ((r12 - (getMeasuredWidth() - r32)) * r4);
    L14:
        int r33 = r22;
        drawPointerArrow(r20, r12, r33, Color.HSVToColor(this.hsvTemp), false);
        this.sliderRect.set(AndroidUtilities.dp(22.0f), AndroidUtilities.dp(26.0f) + r8, getMeasuredWidth() - AndroidUtilities.dp(22.0f), r8 + AndroidUtilities.dp(34.0f));
        if (this.colorGradient != null) goto L21;
        float[] r23 = this.hsvTemp;
        r23[2] = this.minHsvBrightness;
        int r24 = Color.HSVToColor(r23);
        float[] r34 = this.hsvTemp;
        r34[2] = this.maxHsvBrightness;
        int r35 = Color.HSVToColor(r34);
        RectF r42 = this.sliderRect;
        float r122 = r42.left;
        float r13 = r42.top;
        LinearGradient r11 = new LinearGradient(r122, r13, r42.right, r13, new int[]{r35, r24}, null, Shader.TileMode.CLAMP);
        this.colorGradient = r11;
        this.valueSliderPaint.setShader(r11);
    L21:
        r20.drawRoundRect(this.sliderRect, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.valueSliderPaint);
        if (this.minHsvBrightness != this.maxHsvBrightness) goto L24;
        float r25 = 0.5f;
    L25:
        RectF r36 = this.sliderRect;
        drawPointerArrow(r20, (int) (r36.left + ((1.0f - r25) * r36.width())), (int) this.sliderRect.centerY(), getColor(), true);
        if (this.circlePressed == false) goto L28;
        return;
    L28:
        if (this.pressedMoveProgress >= 1.0f) goto L35;
        long r14 = SystemClock.elapsedRealtime();
        long r37 = r14 - this.lastUpdateTime;
        this.lastUpdateTime = r14;
        float r15 = this.pressedMoveProgress + (r37 / 180.0f);
        this.pressedMoveProgress = r15;
        if (r15 <= 1.0f) goto L32;
        this.pressedMoveProgress = 1.0f;
    L32:
        invalidate();
        return;
    L35:
        return;
    L24:
        float r26 = getBrightness();
        float r38 = this.minHsvBrightness;
        r25 = (r26 - r38) / (this.maxHsvBrightness - r38);
        goto L25
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updateColorsPosition(null, 0, false, getMeasuredWidth());
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        if (this.colorWheelWidth == r1) goto L6;
        this.colorWheelWidth = r1;
        this.colorWheelBitmap = createColorWheelBitmap(r1, AndroidUtilities.dp(180.0f));
        this.colorGradient = null;
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r12) {
        int r0 = r12.getAction();
        if (r0 == 0) goto L10;
        if (r0 == 1) goto L7;
        if (r0 == 2) goto L10;
    L9:
        return super.onTouchEvent(r12);
    L7:
        this.colorPressed = false;
        this.circlePressed = false;
        this.lastUpdateTime = SystemClock.elapsedRealtime();
        invalidate();
    L10:
        int r02 = (int) r12.getX();
        int r122 = (int) r12.getY();
        int r4 = AndroidUtilities.dp(45.0f);
        float r6 = 0.0f;
        if (this.circlePressed == true) goto L18;
        if (this.colorPressed == true) goto L26;
        if (r122 < r4) goto L26;
        if (r122 <= (this.colorWheelBitmap.getHeight() + r4)) goto L18;
    L26:
        if (this.colorPressed == false) goto L28;
    L37:
        float r123 = r02;
        RectF r03 = this.sliderRect;
        float r124 = 1.0f - ((r123 - r03.left) / r03.width());
        if (r124 >= 0.0f) goto L41;
    L44:
        this.colorHSV[2] = (this.minHsvBrightness * (1.0f - r6)) + (this.maxHsvBrightness * r6);
        if (this.colorPressed == true) goto L47;
        getParent().requestDisallowInterceptTouchEvent(true);
    L47:
        this.colorPressed = true;
    L49:
        if (this.colorPressed == false) goto L51;
    L52:
        int r125 = getColor();
        if (this.ignoreTextChange == true) goto L55;
        int r04 = Color.red(r125);
        int r1 = Color.green(r125);
        int r42 = Color.blue(r125);
        this.ignoreTextChange = true;
        String r05 = String.format("%02x%02x%02x", new Object[]{Byte.valueOf((byte) r04), Byte.valueOf((byte) r1), Byte.valueOf((byte) r42)}).toUpperCase();
        Editable r13 = this.colorEditText[1].getText();
        r13.replace(0, r13.length(), r05);
        this.radioButton[this.selectedColor].setColor(r125);
        this.ignoreTextChange = false;
    L55:
        this.delegate.setColor(r125, this.selectedColor, false);
        invalidate();
    L56:
        return true;
    L51:
        if (this.circlePressed == false) goto L56;
    L41:
        if (r124 <= 1.0f) goto L43;
        r6 = 1.0f;
        goto L44
    L43:
        r6 = r124;
        goto L44
    L28:
        if (this.circlePressed == true) goto L49;
        float r43 = r02;
        RectF r5 = this.sliderRect;
        if (r43 < r5.left) goto L49;
        if (r43 > r5.right) goto L49;
        float r126 = r122;
        if (r126 < (r5.top - AndroidUtilities.dp(7.0f))) goto L49;
        if (r126 > (this.sliderRect.bottom + AndroidUtilities.dp(7.0f))) goto L49;
    L18:
        if (this.circlePressed == true) goto L20;
        getParent().requestDisallowInterceptTouchEvent(true);
    L20:
        this.circlePressed = true;
        this.pressedMoveProgress = 0.0f;
        this.lastUpdateTime = SystemClock.elapsedRealtime();
        r02 = Math.max(0, Math.min(r02, this.colorWheelBitmap.getWidth()));
        r122 = Math.max(r4, Math.min(r122, this.colorWheelBitmap.getHeight() + r4));
        if (this.minHsvBrightness != this.maxHsvBrightness) goto L23;
        float r52 = 0.5f;
    L24:
        this.colorHSV[0] = (r02 * 360.0f) / this.colorWheelBitmap.getWidth();
        this.colorHSV[1] = 1.0f - ((1.0f / this.colorWheelBitmap.getHeight()) * (r122 - r4));
        updateHsvMinMaxBrightness();
        this.colorHSV[2] = (this.minHsvBrightness * (1.0f - r52)) + (this.maxHsvBrightness * r52);
        this.colorGradient = null;
        goto L26
    L23:
        float r53 = getBrightness();
        float r8 = this.minHsvBrightness;
        r52 = (r53 - r8) / (this.maxHsvBrightness - r8);
        goto L24
    }

    public void provideThemeDescriptions(List<ThemeDescription> r28) {
        int r2 = 0;
    L4:
        if (r2 >= this.colorEditText.length) goto L6;
        EditTextBoldCursor r5 = this.colorEditText[r2];
        int r6 = ThemeDescription.FLAG_TEXTCOLOR;
        int r14 = Theme.key_windowBackgroundWhiteBlackText;
        r28.add(new ThemeDescription(r5, r6, null, null, null, null, r14));
        r28.add(new ThemeDescription(this.colorEditText[r2], ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, r14));
        r28.add(new ThemeDescription(this.colorEditText[r2], ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        r28.add(new ThemeDescription(this.colorEditText[r2], ThemeDescription.FLAG_HINTTEXTCOLOR | ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r28.add(new ThemeDescription(this.colorEditText[r2], ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_windowBackgroundWhiteInputField));
        r28.add(new ThemeDescription(this.colorEditText[r2], ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_windowBackgroundWhiteInputFieldActivated));
        r2 = r2 + 1;
        goto L4
    L6:
        ImageView r4 = this.clearButton;
        int r52 = ThemeDescription.FLAG_IMAGECOLOR;
        int r10 = Theme.key_windowBackgroundWhiteBlackText;
        r28.add(new ThemeDescription(r4, r52, null, null, null, null, r10));
        ImageView r12 = this.clearButton;
        int r13 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        int r18 = Theme.key_dialogButtonSelector;
        r28.add(new ThemeDescription(r12, r13, null, null, null, null, r18));
        if (this.menuItem == null) goto L11;
        ColorPicker$$ExternalSyntheticLambda7 r8 = new ColorPicker$$ExternalSyntheticLambda7(this);
        r28.add(new ThemeDescription(this.menuItem, 0, null, null, null, r8, r10));
        r28.add(new ThemeDescription(this.menuItem, 0, null, null, null, r8, r18));
        r28.add(new ThemeDescription(this.menuItem, 0, null, null, null, r8, Theme.key_actionBarDefaultSubmenuItem));
        r28.add(new ThemeDescription(this.menuItem, 0, null, null, null, r8, Theme.key_actionBarDefaultSubmenuItemIcon));
        r28.add(new ThemeDescription(this.menuItem, 0, null, null, null, r8, Theme.key_actionBarDefaultSubmenuBackground));
        return;
    }

    public void setColor(int r5, int r6) {
        if (this.ignoreTextChange == true) goto L8;
        this.ignoreTextChange = true;
        if (this.selectedColor != r6) goto L7;
        String r1 = String.format("%02x%02x%02x", new Object[]{Byte.valueOf((byte) Color.red(r5)), Byte.valueOf((byte) Color.green(r5)), Byte.valueOf((byte) Color.blue(r5))}).toUpperCase();
        this.colorEditText[1].setText(r1);
        this.colorEditText[1].setSelection(r1.length());
    L7:
        this.radioButton[r6].setColor(r5);
        this.ignoreTextChange = false;
    L8:
        setColorInner(r5);
    }

    public void setHasChanges(boolean r7) {
        if (r7 == true) goto L4;
    L5:
        if (r7 == true) goto L9;
        if (this.resetButton.getTag() != null) goto L9;
        return;
    L9:
        if (this.clearButton.getTag() != null) goto L25;
        TextView r0 = this.resetButton;
        if (r7 == false) goto L14;
        Integer r2 = 1;
    L15:
        r0.setTag(r2);
        AnimatorSet r02 = new AnimatorSet();
        ArrayList r22 = new ArrayList();
        if (r7 == false) goto L18;
        this.resetButton.setVisibility(0);
    L18:
        TextView r4 = this.resetButton;
        if (r7 == false) goto L21;
        float r5 = 1.0f;
    L22:
        r22.add(ObjectAnimator.ofFloat(r4, View.ALPHA, new float[]{r5}));
        r02.addListener(new AnonymousClass8(this, r7));
        r02.playTogether(r22);
        r02.setDuration(180);
        r02.start();
        return;
    L21:
        r5 = 0.0f;
        goto L22
    L14:
        r2 = null;
        goto L15
    L25:
        return;
    L4:
        if (this.resetButton.getTag() == null) goto L5;
    }

    public void setMaxBrightness(float r1) {
        this.maxBrightness = r1;
        updateHsvMinMaxBrightness();
    }

    public void setMinBrightness(float r1) {
        this.minBrightness = r1;
        updateHsvMinMaxBrightness();
    }

    public void setResourcesProvider(Theme.ResourcesProvider r1) {
        this.resourcesProvider = r1;
    }

    public void setType(int r6, boolean r7, int r8, int r9, boolean r10, int r11, boolean r12) {
        if (r6 == this.currentResetType) goto L12;
        this.prevSelectedColor = 0;
        this.selectedColor = 0;
        int r72 = 0;
    L6:
        if (r72 >= 4) goto L12;
        RadioButton r1 = this.radioButton[r72];
        if (r72 != this.selectedColor) goto L10;
        boolean r2 = true;
    L11:
        r1.setChecked(r2, true);
        r72 = r72 + 1;
        goto L6
    L10:
        r2 = false;
    L12:
        this.maxColorsCount = r8;
        this.currentResetType = r6;
        this.myMessagesColor = r10;
        this.colorsCount = r9;
        if (r9 != 1) goto L16;
        this.addButton.setTranslationX(0.0f);
    L22:
        ActionBarMenuItem r102 = this.menuItem;
        if (r102 == null) goto L27;
        if (r6 != 1) goto L26;
        r102.setVisibility(0);
        goto L27
    L26:
        r102.setVisibility(8);
        this.clearButton.setTranslationX(0.0f);
    L27:
        if (r8 > 1) goto L29;
        this.addButton.setVisibility(8);
        this.clearButton.setVisibility(8);
    L37:
        this.linearLayout.invalidate();
        ArrayList r73 = null;
        updateColorsPosition(null, 0, false, getMeasuredWidth());
        if (r12 == false) goto L40;
        r73 = new ArrayList();
    L40:
        if (r73 != null) goto L42;
        return;
    L42:
        if (r73.isEmpty() == true) goto L49;
        AnimatorSet r62 = new AnimatorSet();
        r62.playTogether(r73);
        r62.setDuration(180);
        r62.addListener(new AnonymousClass9(this, r8));
        r62.start();
        return;
    L49:
        return;
    L29:
        ImageView r63 = this.addButton;
        if (r9 >= r8) goto L32;
        r63.setVisibility(0);
        this.addButton.setScaleX(1.0f);
        this.addButton.setScaleY(1.0f);
        this.addButton.setAlpha(1.0f);
    L33:
        ImageView r64 = this.clearButton;
        if (r9 <= 1) goto L36;
        r64.setVisibility(0);
        this.clearButton.setScaleX(1.0f);
        this.clearButton.setScaleY(1.0f);
        this.clearButton.setAlpha(1.0f);
        goto L37
    L36:
        r64.setVisibility(8);
        goto L37
    L32:
        r63.setVisibility(8);
        goto L33
    L16:
        if (r9 != 2) goto L18;
        this.addButton.setTranslationX(AndroidUtilities.dp(30.0f) + AndroidUtilities.dp(13.0f));
        goto L22
    L18:
        ImageView r3 = this.addButton;
        if (r9 != 3) goto L21;
        r3.setTranslationX((AndroidUtilities.dp(30.0f) * 2) + (AndroidUtilities.dp(13.0f) * 2));
        goto L22
    L21:
        r3.setTranslationX((AndroidUtilities.dp(30.0f) * 3) + (AndroidUtilities.dp(13.0f) * 3));
        goto L22
    }
}
