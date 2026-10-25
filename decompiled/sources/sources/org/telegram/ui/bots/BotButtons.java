package org.telegram.ui.bots;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import kotlin.jvm.internal.CharCompanionObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedColor;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.voip.CellFlickerDrawable;

/* loaded from: classes7.dex */
public abstract class BotButtons extends FrameLayout {
    public final AnimatedColor background;
    private final Paint backgroundPaint;
    public final Button[] buttons;
    public final AnimatedFloat height;
    private Button pressedButton;
    private final Paint separatorPaint;
    public ButtonsState state;
    private Utilities.Callback<Boolean> whenClicked;
    private Runnable whenResized;

    public class Button {
        public final AnimatedFloat alpha;
        public final AnimatedColor backgroundColor;
        public final Paint backgroundPaint;
        public final ButtonBounce bounce;
        public final RectF bounds;
        public final CellFlickerDrawable flicker;
        public final AnimatedFloat flickerAlpha;
        public final CircularProgressDrawable progress;
        public final AnimatedFloat progressAlpha;
        public final Drawable ripple;
        public int rippleColor;
        public final AnimatedColor textColor;
        public final AnimatedTextView.AnimatedTextDrawable textDrawable;
        final /* synthetic */ BotButtons this$0;
        public final AnimatedFloat w;
        public final AnimatedFloat x;
        public final AnimatedFloat y;

        private Button(BotButtons r10) {
            this.this$0 = r10;
            this.bounds = new RectF();
            CubicBezierInterpolator r8 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.alpha = new AnimatedFloat(r10, 0, 320, r8);
            this.x = new AnimatedFloat(r10, 0, 320, r8);
            this.y = new AnimatedFloat(r10, 0, 320, r8);
            this.w = new AnimatedFloat(r10, 0, 320, r8);
            this.backgroundColor = new AnimatedColor(r10, 0, 320, r8);
            this.textColor = new AnimatedColor(r10, 0, 320, r8);
            this.progressAlpha = new AnimatedFloat(r10, 0, 320, r8);
            this.flickerAlpha = new AnimatedFloat(r10, 0, 320, r8);
            this.bounce = new ButtonBounce(r10);
            this.backgroundPaint = new Paint(1);
            AnimatedTextView.AnimatedTextDrawable r102 = new AnimatedTextView.AnimatedTextDrawable(true, false, true);
            this.textDrawable = r102;
            Drawable r1 = Theme.createRadSelectorDrawable(0, 9, 9);
            this.ripple = r1;
            CircularProgressDrawable r2 = new CircularProgressDrawable();
            this.progress = r2;
            CellFlickerDrawable r4 = new CellFlickerDrawable();
            this.flicker = r4;
            r102.setGravity(17);
            r102.setTextSize(AndroidUtilities.dp(14.0f));
            r102.setTypeface(AndroidUtilities.bold());
            r102.setOverrideFullWidth(AndroidUtilities.displaySize.x * 4);
            r102.setEllipsizeByGradient(true);
            r102.setCallback(r10);
            r2.setCallback(r10);
            r1.setCallback(r10);
            r4.frameInside = true;
            r4.repeatProgress = 2.0f;
        }

        public /* synthetic */ Button(BotButtons r1, BotButtonsIA r2) {
            this(r1);
        }
    }

    public static class ButtonState {
        public boolean active;
        public int color;
        public long emojiId;
        public String position;
        public boolean progressVisible;
        public boolean shineEffect;
        public String text;
        public int textColor;
        public boolean visible;

        public ButtonState() {
        }

        public static ButtonState of(boolean r10, boolean r11, boolean r12, boolean r13, String r14, long r15, int r17, int r18) {
            return of(r10, r11, r12, r13, r14, r15, r17, r18, null);
        }

        public static ButtonState of(boolean r1, boolean r2, boolean r3, boolean r4, String r5, long r6, int r8, int r9, String r10) {
            ButtonState r0 = new ButtonState();
            r0.visible = r1;
            r0.active = r2;
            r0.progressVisible = r3;
            r0.shineEffect = r4;
            r0.text = r5;
            r0.emojiId = r6;
            r0.color = r8;
            r0.textColor = r9;
            r0.position = r10;
            return r0;
        }
    }

    public static class ButtonsState {
        public int backgroundColor;
        public ButtonState main;
        public ButtonState secondary;

        public ButtonsState() {
            this.main = new ButtonState();
            this.secondary = new ButtonState();
        }
    }

    public BotButtons(Context r11, Theme.ResourcesProvider r12) {
        super(r11);
        Paint r112 = new Paint(1);
        this.backgroundPaint = r112;
        Paint r1 = new Paint(1);
        this.separatorPaint = r1;
        CubicBezierInterpolator r8 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.height = new AnimatedFloat(this, 0, 320, r8);
        this.background = new AnimatedColor(this, 0, 320, r8);
        this.state = new ButtonsState();
        this.buttons = new Button[]{new Button(this, null), new Button(this, null)};
        setWillNotDraw(false);
        r1.setColor(Theme.multAlpha(-16777216, 0.1f));
        ButtonsState r13 = this.state;
        int r122 = Theme.getColor(Theme.key_windowBackgroundWhite, r12);
        r13.backgroundColor = r122;
        r112.setColor(r122);
    }

    private Button getHitButton(float r4, float r5) {
        int r0 = 0;
    L3:
        Button[] r1 = this.buttons;
        if (r0 >= r1.length) goto L18;
        ButtonsState r2 = this.state;
        if (r0 != 0) goto L8;
        ButtonState r22 = r2.main;
    L10:
        if (r1[r0].bounds.contains(r4, r5) == false) goto L17;
        if (r22.visible == false) goto L17;
        if (r22.active == false) goto L17;
        return this.buttons[r0];
    L17:
        r0 = r0 + 1;
        goto L3
    L8:
        r22 = r2.secondary;
        goto L10
    L18:
        return null;
    }

    private static void setText(AnimatedTextView.AnimatedTextDrawable r5, ButtonState r6, boolean r7) {
        r5.cancelAnimation();
        if (r6.emojiId == 0) goto L6;
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        r0.append("* ");
        r0.append(r6.text);
        r0.setSpan(new AnimatedEmojiSpan(r6.emojiId, 1.4f, r5.getPaint().getFontMetricsInt()), 0, 1, 33);
        r5.setText(r0, r7);
        return;
    L6:
        r5.setText(r6.text, r7);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r22) {
        float r3 = getHeight() - this.height.set(getTotalHeight());
        r22.drawRect(0.0f, r3 - 1.0f, getWidth(), r3, this.separatorPaint);
        this.backgroundPaint.setColor(this.background.set(this.state.backgroundColor));
        r22.drawRect(0.0f, r3, getWidth(), getHeight(), this.backgroundPaint);
        String r2 = this.state.secondary.position;
        int r4 = 1;
        if (this.buttons[1].alpha.get() >= this.buttons[0].alpha.get()) goto L5;
        int r32 = 1;
    L6:
        int r6 = r32;
    L7:
        if (r32 == 0) goto L10;
        if (r6 < 0) goto L86;
    L11:
        Button r9 = this.buttons[r6];
        ButtonsState r10 = this.state;
        if (r6 != 0) goto L14;
        ButtonState r102 = r10.main;
    L15:
        float r11 = r9.alpha.set(r102.visible);
        boolean r12 = r102.visible;
        AnimatedFloat r13 = r9.x;
        if (r12 == true) goto L18;
        float r122 = r13.get();
    L34:
        boolean r42 = r102.visible;
        AnimatedFloat r132 = r9.y;
        if (r42 == true) goto L37;
        float r43 = r132.get();
    L53:
        boolean r8 = r102.visible;
        AnimatedFloat r133 = r9.w;
        if (r8 == true) goto L56;
        float r82 = r133.get();
    L67:
        float r7 = AndroidUtilities.lerp((getWidth() - AndroidUtilities.dp(26.0f)) / 2.0f, getWidth() - AndroidUtilities.dp(16.0f), r82);
        float r83 = AndroidUtilities.dp(44.0f);
        float r72 = r7 / 2.0f;
        float r123 = AndroidUtilities.lerp(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(18.0f) + ((getWidth() - AndroidUtilities.dp(26.0f)) / 2.0f), r122) + r72;
        float r84 = r83 / 2.0f;
        float r44 = (AndroidUtilities.lerp(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(58.0f), r43) + r84) + r3;
        r9.bounds.set(r123 - r72, r44 - r84, r72 + r123, r84 + r44);
        float r73 = r9.progressAlpha.set(r102.progressVisible);
        float r85 = r9.flickerAlpha.set(r102.shineEffect);
        r22.save();
        float r134 = r9.bounce.getScale(0.02f) * AndroidUtilities.lerp(0.7f, 1.0f, r11);
        r22.scale(r134, r134, r123, r44);
        r9.backgroundPaint.setColor(Theme.multAlpha(r9.backgroundColor.set(r102.color), r11));
        String r17 = r2;
        r22.drawRoundRect(r9.bounds, AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), r9.backgroundPaint);
        if (r73 >= 1.0f) goto L71;
        r22.save();
        float r23 = 1.0f - r73;
        float r14 = AndroidUtilities.lerp(0.75f, 1.0f, r23);
        r22.scale(r14, r14, r123, r44);
        r22.translate(0.0f, AndroidUtilities.dp(-10.0f) * r73);
        float r24 = r23 * r11;
        r9.textDrawable.setEmojiColor(Theme.multAlpha(r9.textColor.set(r102.textColor), r24));
        r9.textDrawable.setTextColor(Theme.multAlpha(r9.textColor.set(r102.textColor), r24));
        r9.textDrawable.setBounds(r9.bounds);
        r9.textDrawable.draw(r22);
        r22.restore();
    L71:
        if (r73 <= 0.0f) goto L73;
        r22.save();
        char r135 = CharCompanionObject.MIN_VALUE;
        float r25 = AndroidUtilities.lerp(0.75f, 1.0f, r73);
        r22.scale(r25, r25, r123, r44);
        r22.translate(0.0f, AndroidUtilities.dp(10.0f) * (1.0f - r73));
        r9.progress.setColor(Theme.multAlpha(r9.textColor.set(r102.textColor), r73 * r11));
        CircularProgressDrawable r26 = r9.progress;
        RectF r45 = r9.bounds;
        r26.setBounds((int) r45.left, (int) r45.top, (int) r45.right, (int) r45.bottom);
        r9.progress.draw(r22);
        r22.restore();
        float r16 = 0.0f;
    L75:
        if (r85 <= r16) goto L78;
        r9.flicker.setColors(Theme.multAlpha(r9.textColor.set(r102.textColor), r11 * r85));
        r9.flicker.draw(r22, r9.bounds, AndroidUtilities.dp(8.0f), this);
    L78:
        if (r9.rippleColor == Theme.multAlpha(r102.textColor, 0.15f)) goto L80;
        Drawable r27 = r9.ripple;
        int r46 = Theme.multAlpha(r102.textColor, 0.15f);
        r9.rippleColor = r46;
        int r74 = 1;
        Theme.setSelectorDrawableColor(r27, r46, true);
    L81:
        Drawable r28 = r9.ripple;
        RectF r47 = r9.bounds;
        r28.setBounds((int) r47.left, (int) r47.top, (int) r47.right, (int) r47.bottom);
        r9.ripple.draw(r22);
        r22.restore();
        if (r32 == 0) goto L84;
        int r29 = -1;
    L85:
        r6 = r6 + r29;
        r4 = r74;
        r2 = r17;
        goto L7
    L84:
        r29 = r74;
        goto L85
    L80:
        r74 = 1;
        goto L81
    L73:
        r135 = CharCompanionObject.MIN_VALUE;
        r16 = 0.0f;
        goto L75
    L56:
        ButtonsState r86 = this.state;
        if (r86.secondary.visible == true) goto L59;
    L65:
        float r75 = 1.0f;
    L66:
        r82 = r133.set(r75);
        goto L67
    L59:
        if (r86.main.visible == false) goto L65;
        if ("left".equalsIgnoreCase(r2) == false) goto L63;
    L64:
        r75 = 0.0f;
        goto L66
    L63:
        if ("right".equalsIgnoreCase(r2) == false) goto L65;
    L37:
        ButtonsState r48 = this.state;
        if (r48.secondary.visible == true) goto L40;
    L51:
        float r49 = 0.0f;
    L52:
        r43 = r132.set(r49);
        goto L53
    L40:
        if (r48.main.visible == false) goto L51;
        if ("top".equalsIgnoreCase(r2) == false) goto L47;
        if (r6 != 0) goto L45;
    L44:
        int r410 = 1;
    L50:
        r49 = r410;
    L45:
        r410 = 0;
        goto L50
    L47:
        if ("bottom".equalsIgnoreCase(r2) == false) goto L45;
        if (r6 != 0) goto L44;
    L18:
        ButtonsState r124 = this.state;
        if (r124.secondary.visible == true) goto L21;
    L32:
        float r411 = 0.0f;
    L33:
        r122 = r13.set(r411);
        goto L34
    L21:
        if (r124.main.visible == false) goto L32;
        if ("left".equalsIgnoreCase(r2) == false) goto L28;
        if (r6 != 0) goto L26;
    L25:
        int r412 = 1;
    L31:
        r411 = r412;
    L26:
        r412 = 0;
        goto L31
    L28:
        if ("right".equalsIgnoreCase(r2) == false) goto L26;
        if (r6 != 0) goto L25;
    L14:
        r102 = r10.secondary;
        goto L15
    L86:
        return;
    L10:
        if (r6 <= r4) goto L11;
        return;
    L5:
        r32 = 0;
        goto L6
    }

    public float getAnimatedTotalHeight() {
        return this.height.get();
    }

    public int getTotalHeight() {
        ButtonsState r0 = this.state;
        boolean r1 = r0.main.visible;
        if (r1 == false) goto L5;
    L8:
        int r4 = 1;
    L9:
        if (r1 == false) goto L17;
        ButtonState r02 = r0.secondary;
        if (r02.visible == false) goto L17;
        if ("top".equalsIgnoreCase(r02.position) == false) goto L15;
    L16:
        r4 = r4 + 1;
        goto L17
    L15:
        if ("bottom".equalsIgnoreCase(this.state.secondary.position) == true) goto L16;
    L17:
        if (r4 != 0) goto L19;
        return 0;
    L19:
        if (r4 != 1) goto L23;
        return AndroidUtilities.dp(58.0f);
    L23:
        return AndroidUtilities.dp(109.0f);
    L5:
        if (r0.secondary.visible == true) goto L8;
        r4 = 0;
        goto L9
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(109.0f) + 1, TLObject.FLAG_30));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r5) {
        if (r5.getAction() != 0) goto L8;
        Button r0 = getHitButton(r5.getX(), r5.getY());
        this.pressedButton = r0;
        if (r0 == null) goto L26;
        r0.bounce.setPressed(true);
        this.pressedButton.ripple.setHotspot(r5.getX(), r5.getY());
        this.pressedButton.ripple.setState(new int[]{R.attr.state_pressed, R.attr.state_enabled});
    L26:
        if (this.pressedButton == null) goto L28;
        return true;
    L28:
        return false;
    L8:
        if (r5.getAction() == 1) goto L12;
        if (r5.getAction() != 3) goto L26;
    L12:
        if (this.pressedButton == null) goto L26;
        if (r5.getAction() != 1) goto L24;
        Button r52 = getHitButton(r5.getX(), r5.getY());
        Button r02 = this.pressedButton;
        if (r52 != r02) goto L24;
        Utilities.Callback<Boolean> r53 = this.whenClicked;
        if (r53 == null) goto L24;
        if (r02 != this.buttons[0]) goto L22;
        boolean r03 = true;
    L23:
        r53.run(Boolean.valueOf(r03));
        goto L24
    L22:
        r03 = false;
    L24:
        this.pressedButton.bounce.setPressed(false);
        this.pressedButton.ripple.setState(new int[0]);
        this.pressedButton = null;
        goto L26
    }

    public void setBackgroundColor(int r3, boolean r4) {
        Paint r0 = this.backgroundPaint;
        this.state.backgroundColor = r3;
        r0.setColor(r3);
        if (r4 == true) goto L6;
        this.background.set(r3, true);
        return;
    }

    public void setMainState(ButtonState r8, boolean r9) {
        int r0 = getTotalHeight();
        this.state.main = r8;
        this.buttons[0].textDrawable.cancelAnimation();
        if (r8.emojiId == 0) goto L5;
        SpannableStringBuilder r1 = new SpannableStringBuilder();
        r1.append("* ");
        r1.append(r8.text);
        r1.setSpan(new AnimatedEmojiSpan(r8.emojiId, 1.4f, this.buttons[0].textDrawable.getPaint().getFontMetricsInt()), 0, 1, 33);
        this.buttons[0].textDrawable.setText(r1, r9);
    L6:
        invalidate();
        if (r0 != getTotalHeight()) goto L9;
        return;
    L9:
        if (this.whenResized == null) goto L17;
        int r82 = getTotalHeight();
        Runnable r7 = this.whenResized;
        if (r0 >= r82) goto L14;
        AndroidUtilities.runOnUIThread(r7, 200);
        return;
    L14:
        r7.run();
        return;
    L17:
        return;
    L5:
        this.buttons[0].textDrawable.setText(r8.text, r9);
        goto L6
    }

    public void setOnButtonClickListener(Utilities.Callback<Boolean> r1) {
        this.whenClicked = r1;
    }

    public void setOnResizeListener(Runnable r1) {
        this.whenResized = r1;
    }

    public void setSecondaryState(ButtonState r4, boolean r5) {
        int r0 = getTotalHeight();
        this.state.secondary = r4;
        setText(this.buttons[1].textDrawable, r4, r5);
        invalidate();
        if (r0 != getTotalHeight()) goto L5;
        return;
    L5:
        if (this.whenResized == null) goto L13;
        int r42 = getTotalHeight();
        Runnable r3 = this.whenResized;
        if (r0 >= r42) goto L10;
        AndroidUtilities.runOnUIThread(r3, 200);
        return;
    L10:
        r3.run();
        return;
    }

    public void setState(ButtonsState r4, boolean r5) {
        int r0 = getTotalHeight();
        this.state = r4;
        setText(this.buttons[0].textDrawable, r4.main, r5);
        setText(this.buttons[1].textDrawable, r4.secondary, r5);
        invalidate();
        if (r0 != getTotalHeight()) goto L5;
    L10:
        setBackgroundColor(r4.backgroundColor, r5);
        return;
    L5:
        if (this.whenResized == null) goto L10;
        int r1 = getTotalHeight();
        Runnable r2 = this.whenResized;
        if (r0 >= r1) goto L9;
        AndroidUtilities.runOnUIThread(r2, 200);
        goto L10
    L9:
        r2.run();
        goto L10
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r6) {
        Button[] r0 = this.buttons;
        Button r2 = r0[0];
        if (r2.ripple != r6) goto L5;
    L14:
        return true;
    L5:
        if (r2.progress == r6) goto L14;
        Button r02 = r0[1];
        if (r02.ripple == r6) goto L14;
        if (r02.progress == r6) goto L14;
        if (super.verifyDrawable(r6) == true) goto L14;
        return false;
    }
}
