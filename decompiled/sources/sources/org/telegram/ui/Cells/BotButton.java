package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.animation.OvershootInterpolator;
import java.util.Arrays;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotInlineKeyboard;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.TLKeyboardHelper;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.Text;

/* loaded from: classes6.dex */
class BotButton {
    public int angle;
    public AnimatedEmojiDrawable animatedEmojiDrawable;
    public TL_keyboard.KeyboardInlineButton button;
    public BotInlineKeyboard.ButtonCustom buttonCustom;
    public BotInlineKeyboard.Button buttonImpl;
    public int height;
    public Drawable iconDrawable;
    public final Runnable invalidateRunnable;
    public boolean isInviteButton;
    public boolean isLocked;
    public boolean isSeparator;
    public boolean isWebAppLink;
    public long lastUpdateTime;
    public LoadingDrawable loadingDrawable;
    private final RectF loadingRect;
    private final Paint paint;
    private final Path path;
    public int positionFlags;
    public ValueAnimator pressAnimator;
    public float pressT;
    public boolean pressed;
    public float progressAlpha;
    private final float[] radii;
    public Drawable selectorDrawable;
    public Text title;
    public float width;
    public float x;
    public int y;

    /* renamed from: org.telegram.ui.Cells.BotButton$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ BotButton this$0;

        public AnonymousClass1(BotButton r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            this.this$0.pressAnimator = null;
        }
    }

    /* renamed from: org.telegram.ui.Cells.BotButton$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$messenger$BotInlineKeyboard$BackgroundColor = null;

        static {
            int[] r0 = new int[BotInlineKeyboard.BackgroundColor.values().length];
            $SwitchMap$org$telegram$messenger$BotInlineKeyboard$BackgroundColor = r0;
            r0[BotInlineKeyboard.BackgroundColor.DANGER.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$org$telegram$messenger$BotInlineKeyboard$BackgroundColor[BotInlineKeyboard.BackgroundColor.SUCCESS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$org$telegram$messenger$BotInlineKeyboard$BackgroundColor[BotInlineKeyboard.BackgroundColor.PRIMARY.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$1F4VL2uMpfqgTpLH4RgAF6Y_o_4(BotButton r0, ValueAnimator r1) {
        r0.lambda$setPressed$0(r1);
    }

    public BotButton(Runnable r3) {
        this.path = new Path();
        this.paint = new Paint(1);
        this.loadingRect = new RectF();
        this.radii = new float[8];
        this.invalidateRunnable = r3;
    }

    private /* synthetic */ void lambda$setPressed$0(ValueAnimator r1) {
        this.pressT = ((Float) r1.getAnimatedValue()).floatValue();
        this.invalidateRunnable.run();
    }

    public boolean draw(Canvas r18, RectF r19, boolean r20, boolean r21, Theme.ResourcesProvider r22) {
        float r4 = getPressScale();
        r18.save();
        float r5 = 1.0f;
        if (r4 == 1.0f) goto L5;
        r18.scale(r4, r4, r19.centerX(), r19.centerY());
    L5:
        Arrays.fill(this.radii, AndroidUtilities.dp(Math.min(6.75f, SharedConfig.bubbleRadius)));
        if (hasPositionFlag(9) == false) goto L9;
        float[] r42 = this.radii;
        float r6 = AndroidUtilities.dp(SharedConfig.bubbleRadius);
        r42[7] = r6;
        r42[6] = r6;
    L9:
        if (hasPositionFlag(10) == false) goto L11;
        float[] r43 = this.radii;
        float r62 = AndroidUtilities.dp(SharedConfig.bubbleRadius);
        r43[5] = r62;
        r43[4] = r62;
    L11:
        this.path.rewind();
        this.path.addRoundRect(r19, this.radii, Path.Direction.CW);
        r18.drawPath(this.path, Theme.getThemePaint("paintChatActionBackground", r22));
        BotInlineKeyboard.Button r44 = this.buttonImpl;
        if (r44 == null) goto L14;
        BotInlineKeyboard.BackgroundColor r45 = r44.getColor();
    L15:
        BotInlineKeyboard.BackgroundColor r63 = BotInlineKeyboard.BackgroundColor.NONE;
        boolean r7 = true;
        if (r45 == r63) goto L28;
        int r8 = AnonymousClass2.$SwitchMap$org$telegram$messenger$BotInlineKeyboard$BackgroundColor[r45.ordinal()];
        if (r8 != 1) goto L20;
        this.paint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_botKeyboard_button_danger, r22), 0.7f));
    L27:
        r18.drawPath(this.path, this.paint);
        goto L28
    L20:
        if (r8 != 2) goto L22;
        this.paint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_botKeyboard_button_success, r22), 0.7f));
        goto L27
    L22:
        if (r8 != 3) goto L27;
        this.paint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_botKeyboard_button_primary, r22), 0.7f));
    L28:
        if (r22 == null) goto L30;
        boolean r82 = r22.hasGradientService();
    L31:
        if (r82 == false) goto L37;
        if (r45 == r63) goto L36;
        if (r22 == null) goto L37;
        if (r22.isDark() == false) goto L37;
    L36:
        r18.drawPath(this.path, Theme.chat_actionBackgroundGradientDarkenPaint);
    L37:
        r18.save();
        r18.clipPath(this.path);
        LoadingDrawable r46 = this.loadingDrawable;
        if (r20 == false) goto L46;
        if (r46 != null) goto L42;
        LoadingDrawable r47 = new LoadingDrawable();
        this.loadingDrawable = r47;
        r47.setRadiiDp(5.5f);
        this.loadingDrawable.setAppearByGradient(true);
        this.loadingDrawable.strokePaint.setStrokeWidth(AndroidUtilities.dpf2(1.25f));
    L52:
        LoadingDrawable r48 = this.loadingDrawable;
        int r9 = 0;
        if (r48 == null) goto L58;
        if (r20 == false) goto L56;
    L57:
        this.loadingRect.set(r19);
        this.loadingRect.inset(AndroidUtilities.dpf2(0.625f), AndroidUtilities.dpf2(0.625f));
        this.loadingDrawable.setRadii(this.radii);
        this.loadingDrawable.setBounds(this.loadingRect);
        LoadingDrawable r49 = this.loadingDrawable;
        int r10 = Theme.key_chat_serviceBackgroundSelector;
        r49.setColors(Theme.multAlpha(Theme.getColor(r10, r22), 1.0f), Theme.multAlpha(Theme.getColor(r10, r22), 2.5f), Theme.multAlpha(Theme.getColor(r10, r22), 3.0f), Theme.multAlpha(Theme.getColor(r10, r22), 10.0f));
        this.loadingDrawable.setAlpha(255);
        this.loadingDrawable.draw(r18);
    L59:
        Drawable r410 = this.selectorDrawable;
        if (r410 == null) goto L62;
        r410.setBounds((int) r19.left, (int) r19.top, (int) r19.right, (int) r19.bottom);
        this.selectorDrawable.setAlpha(255);
        this.selectorDrawable.draw(r18);
    L62:
        r18.restore();
        r18.save();
        if (this.iconDrawable == null) goto L65;
    L68:
        int r411 = AndroidUtilities.dp(26.0f);
    L69:
        float r102 = r19.left;
        float r11 = r19.width();
        float r12 = this.title.getWidth();
        if (this.iconDrawable == null) goto L72;
        r9 = AndroidUtilities.dp(4.0f);
    L72:
        float r412 = r411;
        float r103 = r102 + (((r11 - (r12 + r9)) - r412) / 2.0f);
        int r122 = 128;
        if (this.animatedEmojiDrawable == null) goto L81;
        int r92 = (int) (r19.centerY() - (AndroidUtilities.dp(20.0f) / 2.0f));
        int r15 = (int) r103;
        this.animatedEmojiDrawable.setBounds(r15, r92, r15 + AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f) + r92);
        AnimatedEmojiDrawable r64 = this.animatedEmojiDrawable;
        if (this.isLocked == true) goto L78;
        r122 = 255;
    L78:
        r64.setAlpha(r122);
        this.animatedEmojiDrawable.draw(r18);
    L79:
        r103 = r103 + r412;
    L87:
        this.title.ellipsize(Math.max(1.0f, (r19.width() - AndroidUtilities.dp(15.0f)) - r412));
        Text r413 = this.title;
        float r65 = r19.centerY();
        if (this.isLocked == false) goto L90;
        r5 = 0.5f;
    L90:
        r413.draw(r18, r103, r65, r5);
        r18.restore();
        TL_keyboard.TL_inlineButtonTypeUrl r414 = (TL_keyboard.TL_inlineButtonTypeUrl) TLKeyboardHelper.getType(this.button, TL_keyboard.TL_inlineButtonTypeUrl.class);
        if (this.buttonCustom == null) goto L96;
        if (this.isLocked == false) goto L117;
        Drawable r0 = Theme.getThemeDrawable("drawableBotLock", r22);
        BaseCell.setDrawableBounds(r0, (((int) r19.right) - AndroidUtilities.dp(3.0f)) - r0.getIntrinsicWidth(), r19.top + AndroidUtilities.dp(3.0f));
        r0.draw(r18);
    L117:
        r18.restore();
        return r7;
    L96:
        if (TLKeyboardHelper.isButtonWebView(this.button) == false) goto L98;
        Drawable r02 = Theme.getThemeDrawable("drawableBotWebView", r22);
        BaseCell.setDrawableBounds(r02, (((int) r19.right) - AndroidUtilities.dp(3.0f)) - r02.getIntrinsicWidth(), r19.top + AndroidUtilities.dp(3.0f));
        r02.draw(r18);
        goto L117
    L98:
        if (r414 == null) goto L108;
        if (this.isWebAppLink == false) goto L103;
        Drawable r03 = Theme.getThemeDrawable("drawableBotWebView", r22);
    L106:
        BaseCell.setDrawableBounds(r03, (((int) r19.right) - AndroidUtilities.dp(3.0f)) - r03.getIntrinsicWidth(), r19.top + AndroidUtilities.dp(3.0f));
        r03.draw(r18);
        goto L117
    L103:
        if (this.isInviteButton == false) goto L105;
        r03 = Theme.getThemeDrawable("drawable_botInvite", r22);
        goto L106
    L105:
        r03 = Theme.getThemeDrawable("drawableBotLink", r22);
        goto L106
    L108:
        if (TLKeyboardHelper.isType(this.button, TL_keyboard.TL_inlineButtonTypeSwitchInline.class) == false) goto L110;
    L116:
        Drawable r04 = Theme.getThemeDrawable("drawableBotInline", r22);
        BaseCell.setDrawableBounds(r04, (((int) r19.right) - AndroidUtilities.dp(3.0f)) - r04.getIntrinsicWidth(), r19.top + AndroidUtilities.dp(3.0f));
        r04.draw(r18);
        goto L117
    L110:
        if (TLKeyboardHelper.isType(this.button, TL_keyboard.TL_buttonTypeRequestPeer.class) == true) goto L116;
        if (TLKeyboardHelper.isType(this.button, TL_keyboard.TL_inlineButtonTypeBuy.class) == false) goto L117;
        if (r21 == false) goto L117;
        BaseCell.setDrawableBounds(Theme.chat_botCardDrawable, (((int) r19.right) - AndroidUtilities.dp(5.0f)) - Theme.chat_botCardDrawable.getIntrinsicWidth(), r19.top + AndroidUtilities.dp(4.0f));
        Theme.chat_botCardDrawable.draw(r18);
        goto L117
    L81:
        if (this.iconDrawable == null) goto L87;
        int r66 = (int) (r19.centerY() - (AndroidUtilities.dp(24.0f) / 2.0f));
        int r13 = (int) r103;
        this.iconDrawable.setBounds(r13, r66, AndroidUtilities.dp(24.0f) + r13, AndroidUtilities.dp(24.0f) + r66);
        Drawable r67 = this.iconDrawable;
        if (this.isLocked == true) goto L86;
        r122 = 255;
    L86:
        r67.setAlpha(r122);
        this.iconDrawable.draw(r18);
        goto L79
    L65:
        if (this.animatedEmojiDrawable != null) goto L68;
        r411 = 0;
        goto L69
    L56:
        if (r48.isDisappearing() == true) goto L57;
    L58:
        r7 = false;
        goto L59
    L42:
        if (r46.isDisappeared() == false) goto L44;
    L45:
        this.loadingDrawable.reset();
        this.loadingDrawable.resetDisappear();
        goto L52
    L44:
        if (this.loadingDrawable.isDisappearing() == false) goto L52;
    L46:
        if (r46 == null) goto L52;
        if (r46.isDisappearing() == true) goto L52;
        if (this.loadingDrawable.isDisappeared() == true) goto L52;
        this.loadingDrawable.disappear();
        goto L52
    L30:
        r82 = Theme.hasGradientService();
        goto L31
    L14:
        r45 = BotInlineKeyboard.BackgroundColor.NONE;
        goto L15
    }

    public float getPressScale() {
        if (this.pressed == false) goto L8;
        float r0 = this.pressT;
        if (r0 == 1.0f) goto L8;
        float r02 = r0 + (Math.min(40.0f, 1000.0f / AndroidUtilities.screenRefreshRate) / 100.0f);
        this.pressT = r02;
        this.pressT = Utilities.clamp(r02, 1.0f, 0.0f);
        this.invalidateRunnable.run();
    L8:
        return ((1.0f - this.pressT) * 0.04f) + 0.96f;
    }

    public boolean hasPositionFlag(int r1) {
        if ((this.positionFlags & r1) != r1) goto L6;
        return true;
    L6:
        return false;
    }

    public void setPressed(boolean r4) {
        if (this.pressed == r4) goto L14;
        this.pressed = r4;
        this.invalidateRunnable.run();
        if (r4 == false) goto L9;
        ValueAnimator r0 = this.pressAnimator;
        if (r0 == null) goto L9;
        r0.removeAllListeners();
        this.pressAnimator.cancel();
    L9:
        if (r4 == true) goto L15;
        float r42 = this.pressT;
        if (r42 == 0.0f) goto L16;
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r42, 0.0f});
        this.pressAnimator = r43;
        r43.addUpdateListener(new BotButton$$ExternalSyntheticLambda0(this));
        this.pressAnimator.addListener(new AnonymousClass1(this));
        this.pressAnimator.setInterpolator(new OvershootInterpolator(2.0f));
        this.pressAnimator.setDuration(350);
        this.pressAnimator.start();
        return;
    L16:
        return;
    L15:
        return;
    }
}
