package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UnconfirmedAuthController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.SessionsActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes3.dex */
public class UnconfirmedAuthHintCell extends FrameLayout {
    private final LinearLayout buttonsLayout;
    private int height;
    private final LinearLayout linearLayout;
    private final TextView messageTextView;
    private final TextViewWithLoading noButton;
    private final TextView titleTextView;
    private final TextViewWithLoading yesButton;

    /* loaded from: classes6.dex */
    public static class TextViewWithLoading extends TextView {
        private boolean loading;
        private final AnimatedFloat loadingT;
        private CircularProgressDrawable progressDrawable;

        public TextViewWithLoading(Context r8) {
            super(r8);
            this.loadingT = new AnimatedFloat(this, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r14) {
            float r0 = this.loadingT.set(this.loading);
            if (r0 > 0.0f) goto L5;
            super.onDraw(r14);
            return;
        L5:
            if (r0 >= 1.0f) goto L7;
            Canvas r6 = r14;
            r6.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) ((1.0f - r0) * 255.0f), 31);
            float r142 = 1.0f - (0.2f * r0);
            r6.scale(r142, r142, getWidth() / 2.0f, getHeight() / 2.0f);
            r6.translate(0.0f, AndroidUtilities.dp(-12.0f) * r0);
            super.onDraw(r6);
            r6.restore();
        L9:
            if (this.progressDrawable != null) goto L11;
            CircularProgressDrawable r143 = new CircularProgressDrawable(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(2.0f), getCurrentTextColor());
            this.progressDrawable = r143;
            r143.setCallback(this);
        L11:
            this.progressDrawable.setColor(getCurrentTextColor());
            float r2 = 1.0f - r0;
            this.progressDrawable.setBounds(getWidth() / 2, (getHeight() / 2) + ((int) (AndroidUtilities.dp(12.0f) * r2)), getWidth() / 2, (getHeight() / 2) + ((int) (r2 * AndroidUtilities.dp(12.0f))));
            this.progressDrawable.setAlpha((int) (r0 * 255.0f));
            this.progressDrawable.draw(r6);
            invalidate();
            return;
        L7:
            r6 = r14;
            goto L9
        }

        public void setLoading(boolean r2) {
            setLoading(r2, true);
        }

        public void setLoading(boolean r2, boolean r3) {
            this.loading = r2;
            boolean r0 = true;
            if (r3 == true) goto L6;
            this.loadingT.set(r2, true);
        L6:
            if (isPressed() == true) goto L10;
            if (r2 == true) goto L10;
            r0 = false;
        L10:
            super.setPressed(r0);
            invalidate();
        }

        @Override // android.view.View
        public void setPressed(boolean r1) {
            if (r1 == false) goto L4;
        L7:
            boolean r12 = true;
        L8:
            super.setPressed(r12);
            return;
        L4:
            if (this.loading == true) goto L7;
            r12 = false;
            goto L8
        }

        @Override // android.widget.TextView, android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (this.progressDrawable != r2) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: $r8$lambda$BxLL04nNHETLUQ1-6lksruDUVQc, reason: not valid java name */
    public static /* synthetic */ void m7119$r8$lambda$BxLL04nNHETLUQ16lksruDUVQc(ArrayList r0) {
    }

    /* renamed from: $r8$lambda$FmPsg7Mq8dur9rHWnN-TbvXVYpo, reason: not valid java name */
    public static /* synthetic */ void m7120$r8$lambda$FmPsg7Mq8dur9rHWnNTbvXVYpo(ButtonWithCounterView r0, BottomSheet r1, View r2) {
        if (r0.isTimerActive() == false) goto L6;
        AndroidUtilities.shakeViewSpring(r0, 3.0f);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        return;
    L6:
        r1.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$ZEICsbdb9MQnlCJ70APeGLR_RYQ(BaseFragment r6, boolean r7, int r8, ArrayList r9, View r10) {
        String r102 = LocaleController.getString(R.string.UnconfirmedAuthConfirmedMessage);
        int r0 = Theme.key_undo_cancelColor;
        SpannableStringBuilder r103 = AndroidUtilities.replaceSingleTag(r102, r0, 0, new UnconfirmedAuthHintCell$$ExternalSyntheticLambda2(r6));
        SpannableString r1 = new SpannableString(">");
        ColoredImageSpan r4 = new ColoredImageSpan(R.drawable.attach_arrow_right);
        r4.setOverrideColor(Theme.getColor(r0));
        r4.setScale(0.7f, 0.7f);
        r4.setWidth(AndroidUtilities.dp(12.0f));
        r1.setSpan(r4, 0, r1.length(), 33);
        AndroidUtilities.replaceCharSequence(">", r103, r1);
        BulletinFactory r62 = BulletinFactory.of(r6);
        int r02 = R.raw.contact_check;
        if (r7 == false) goto L5;
        int r72 = R.string.UnconfirmedAuthConfirmedBot;
    L6:
        r62.createSimpleBulletin(r02, LocaleController.getString(r72), r103).show();
        MessagesController.getInstance(r8).getUnconfirmedAuthController().confirm(r9, new UnconfirmedAuthHintCell$$ExternalSyntheticLambda3());
        MessagesController.getInstance(r8).getUnconfirmedAuthController().cleanup();
        return;
    L5:
        r72 = R.string.UnconfirmedAuthConfirmed;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$aR9eE_PuztcHAvmgfUDXWLIRfig(BaseFragment r2) {
        Bulletin.hideVisible();
        r2.presentFragment(new SessionsActivity(0));
    }

    public static /* synthetic */ void $r8$lambda$g0aXKZB7IIA37ft39oKIODNwhlc(UnconfirmedAuthHintCell r0, int r1, ArrayList r2) {
        r0.lambda$set$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$oTva23Nkx_8k1MXppnfn3JsdEug(UnconfirmedAuthHintCell r0, int r1, ArrayList r2, View r3) {
        r0.lambda$set$4(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$sjRWXBHKvFpazvxwtiyIF-nxmFs, reason: not valid java name */
    public static /* synthetic */ void m7121$r8$lambda$sjRWXBHKvFpazvxwtiyIFnxmFs(BottomSheet r1) {
        r1.setCanDismissWithSwipe(true);
        r1.setCanDismissWithTouchOutside(true);
    }

    public UnconfirmedAuthHintCell(Context r15) {
        super(r15);
        setClickable(true);
        LinearLayout r1 = new LinearLayout(r15);
        this.linearLayout = r1;
        r1.setOrientation(1);
        TextView r2 = new TextView(r15);
        this.titleTextView = r2;
        r2.setGravity(17);
        r2.setTextSize(1, 14.0f);
        r2.setTypeface(AndroidUtilities.bold());
        r2.setText(LocaleController.getString(R.string.UnconfirmedAuthTitle));
        r1.addView(r2, LayoutHelper.createLinear(-1, -2, 0.0f, 55, 28, 8, 28, 0));
        TextView r22 = new TextView(r15);
        this.messageTextView = r22;
        r22.setGravity(17);
        r22.setTextSize(1, 13.0f);
        r22.setLineSpacing(AndroidUtilities.dpf2(2.0f), 1.0f);
        r1.addView(r22, LayoutHelper.createLinear(-1, -2, 0.0f, 55, 28, 2, 28, 0));
        LinearLayout r23 = new LinearLayout(r15);
        this.buttonsLayout = r23;
        r23.setOrientation(0);
        r23.setGravity(17);
        r23.addView(new Space(r15), LayoutHelper.createLinear(-2, 1, 17.0f, 1));
        TextViewWithLoading r3 = new TextViewWithLoading(r15);
        this.yesButton = r3;
        r3.setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(7.0f));
        r3.setTypeface(AndroidUtilities.bold());
        r3.setTextSize(1, 14.22f);
        r3.setText(LocaleController.getString(R.string.UnconfirmedAuthConfirm));
        r23.addView(r3, LayoutHelper.createLinear(-2, 30));
        r23.addView(new Space(r15), LayoutHelper.createLinear(-2, 1, 17.0f, 1));
        TextViewWithLoading r32 = new TextViewWithLoading(r15);
        this.noButton = r32;
        r32.setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(7.0f));
        r32.setTypeface(AndroidUtilities.bold());
        r32.setTextSize(1, 14.22f);
        r32.setText(LocaleController.getString(R.string.UnconfirmedAuthDeny));
        r23.addView(r32, LayoutHelper.createLinear(-2, 30));
        r23.addView(new Space(r15), LayoutHelper.createLinear(-2, 1, 17.0f, 1));
        r1.addView(r23, LayoutHelper.createLinear(-1, -2, 28.0f, 4.0f, 28.0f, 8.0f));
        addView(r1, LayoutHelper.createFrame(-1, -1, 119));
        updateColors();
    }

    private static String from(UnconfirmedAuthController.UnconfirmedAuth r2) {
        if (r2 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET + r2.device;
        if (TextUtils.isEmpty(r2.location) == true) goto L11;
        if (r0.isEmpty() == true) goto L11;
        r0 = r0.concat(", ");
    L11:
        return r0 + r2.location;
    }

    private /* synthetic */ void lambda$set$3(int r2, ArrayList r3) {
        if (LaunchActivity.isActive == false) goto L5;
        showLoginPreventedSheet(r3);
    L5:
        this.noButton.setLoading(false);
        MessagesController.getInstance(r2).getUnconfirmedAuthController().cleanup();
    }

    private /* synthetic */ void lambda$set$4(int r2, ArrayList r3, View r4) {
        this.noButton.setLoading(true);
        MessagesController.getInstance(r2).getUnconfirmedAuthController().deny(r3, new UnconfirmedAuthHintCell$$ExternalSyntheticLambda4(this, r2));
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        int r62 = View.MeasureSpec.getSize(r5);
        if (r62 > 0) goto L5;
        r62 = AndroidUtilities.displaySize.x;
    L5:
        this.linearLayout.measure(View.MeasureSpec.makeMeasureSpec((r62 - getPaddingLeft()) - getPaddingRight(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.y, Integer.MIN_VALUE));
        int r63 = ((this.linearLayout.getMeasuredHeight() + getPaddingTop()) + getPaddingBottom()) + 1;
        this.height = r63;
        super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec(r63, TLObject.FLAG_30));
    }

    public void set(BaseFragment r10, int r11) {
        ArrayList<UnconfirmedAuthController.UnconfirmedAuth> r0 = MessagesController.getInstance(r11).getUnconfirmedAuthController().auths;
        this.yesButton.setText(LocaleController.getString(R.string.UnconfirmedAuthConfirm));
        boolean r2 = false;
        this.yesButton.setLoading(false, false);
        this.noButton.setText(LocaleController.getString(R.string.UnconfirmedAuthDeny));
        this.noButton.setLoading(false, false);
        int r1 = 1;
        if (r0 != null) goto L5;
    L19:
        if (r0 != null) goto L21;
    L33:
        this.yesButton.setOnClickListener(new UnconfirmedAuthHintCell$$ExternalSyntheticLambda0(r10, r2, r11, r0));
        this.noButton.setOnClickListener(new UnconfirmedAuthHintCell$$ExternalSyntheticLambda1(this, r11, r0));
        return;
    L21:
        if (r0.size() <= 1) goto L33;
        this.titleTextView.setText(LocaleController.getString(R.string.UnconfirmedAuthTitle));
        String r3 = r0.get(0).location;
    L24:
        if (r1 >= r0.size()) goto L29;
        if (TextUtils.equals(r3, r0.get(r1).location) == false) goto L27;
        r1 = r1 + 1;
        goto L24
    L27:
        r3 = null;
    L29:
        TextView r12 = this.messageTextView;
        if (r3 != null) goto L32;
        r12.setText(LocaleController.formatPluralString("UnconfirmedAuthMultiple", r0.size(), new Object[0]));
        goto L33
    L32:
        r12.setText(LocaleController.formatPluralString("UnconfirmedAuthMultipleFrom", r0.size(), new Object[]{r3}));
        goto L33
    L5:
        if (r0.size() != 1) goto L19;
        UnconfirmedAuthController.UnconfirmedAuth r32 = r0.get(0);
        TextView r4 = this.titleTextView;
        if (r32.bot == false) goto L9;
        int r5 = R.string.UnconfirmedAuthTitleBot;
    L10:
        r4.setText(LocaleController.getString(r5));
        String r42 = _UrlKt.FRAGMENT_ENCODE_SET + r32.device;
        if (TextUtils.isEmpty(r32.location) == false) goto L13;
    L15:
        String r43 = r42 + r32.location;
        boolean r52 = r32.bot;
        TextView r6 = this.messageTextView;
        if (r52 == false) goto L18;
        r6.setText(LocaleController.formatString(R.string.UnconfirmedAuthSingleBot, new Object[]{"@" + DialogObject.getShortName(r32.bot_id), r43}));
        r2 = true;
        goto L33
    L18:
        r6.setText(LocaleController.formatString(R.string.UnconfirmedAuthSingle, new Object[]{r43}));
        goto L33
    L13:
        if (r42.isEmpty() == true) goto L15;
        r42 = r42.concat(", ");
        goto L15
    L9:
        r5 = R.string.UnconfirmedAuthTitle;
        goto L10
    }

    public void showLoginPreventedSheet(ArrayList<UnconfirmedAuthController.UnconfirmedAuth> r15) {
        if (r15 != null) goto L5;
    L21:
        BulletinFactory.of(Bulletin.BulletinWindow.make(getContext()), null).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
        return;
    L5:
        if (r15.size() == 0) goto L21;
        LinearLayout r1 = new LinearLayout(getContext());
        r1.setOrientation(1);
        RLottieImageView r3 = new RLottieImageView(getContext());
        r3.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        r3.setAnimation(R.raw.ic_ban, 50, 50);
        r3.playAnimation();
        r3.setScaleType(ImageView.ScaleType.CENTER);
        r3.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(Theme.key_windowBackgroundWhiteValueText)));
        r1.addView(r3, LayoutHelper.createLinear(80, 80, 17, 0, 14, 0, 0));
        TextView r32 = new TextView(getContext());
        r32.setTypeface(AndroidUtilities.bold());
        r32.setTextSize(1, 20.0f);
        r32.setGravity(17);
        r32.setText(LocaleController.formatPluralString("UnconfirmedAuthDeniedTitle", r15.size(), new Object[0]));
        r32.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r1.addView(r32, LayoutHelper.createLinear(-1, -2, 28.0f, 14.0f, 28.0f, 0.0f));
        TextView r33 = new TextView(getContext());
        r33.setTextSize(1, 14.0f);
        r33.setGravity(17);
        if (r15.size() != 1) goto L10;
        r33.setText(LocaleController.formatString(R.string.UnconfirmedAuthDeniedMessageSingle, new Object[]{from(r15.get(0))}));
    L15:
        r33.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r1.addView(r33, LayoutHelper.createLinear(-1, -2, 40.0f, 9.0f, 40.0f, 0.0f));
        FrameLayout r152 = new FrameLayout(getContext());
        r152.setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(10.0f));
        int r34 = AndroidUtilities.dp(12.0f);
        int r8 = Theme.key_text_RedBold;
        int r9 = Theme.getColor(r8);
        if (Theme.isCurrentThemeDark() == false) goto L18;
        float r10 = 0.2f;
    L19:
        r152.setBackground(Theme.createRoundRectDrawable(r34, Theme.multAlpha(r9, r10)));
        TextView r35 = new TextView(getContext());
        r35.setTypeface(AndroidUtilities.bold());
        r35.setTextSize(1, 14.0f);
        r35.setGravity(17);
        r35.setTextColor(Theme.getColor(r8));
        r35.setText(LocaleController.getString(R.string.UnconfirmedAuthDeniedWarning));
        r152.addView(r35, LayoutHelper.createFrame(-1, -1, 119));
        r1.addView(r152, LayoutHelper.createLinear(-1, -2, 14.0f, 19.0f, 14.0f, 0.0f));
        ButtonWithCounterView r153 = new ButtonWithCounterView(getContext(), null).setRound();
        ScaleStateListAnimator.apply(r153, 0.02f, 1.5f);
        r153.setText(LocaleController.getString(R.string.GotIt), false);
        r1.addView(r153, LayoutHelper.createLinear(-1, 48, 14.0f, 20.0f, 14.0f, 4.0f));
        BottomSheet r14 = new BottomSheet.Builder(getContext()).setCustomView(r1).show();
        r14.setCanDismissWithSwipe(false);
        r14.setCanDismissWithTouchOutside(false);
        r153.setTimer(5, new UnconfirmedAuthHintCell$$ExternalSyntheticLambda5(r14));
        r153.setOnClickListener(new UnconfirmedAuthHintCell$$ExternalSyntheticLambda6(r153, r14));
        return;
    L18:
        r10 = 0.15f;
        goto L19
    L10:
        int r92 = 0;
        String r102 = "\n";
    L12:
        if (r92 >= Math.min(r15.size(), 10)) goto L14;
        r102 = r102 + "• " + from(r15.get(r92)) + "\n";
        r92 = r92 + 1;
        goto L12
    L14:
        r33.setText(LocaleController.formatString(R.string.UnconfirmedAuthDeniedMessageMultiple, new Object[]{r102}));
        goto L15
    }

    public void updateColors() {
        this.titleTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.messageTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        TextViewWithLoading r0 = this.yesButton;
        int r1 = Theme.key_windowBackgroundWhiteValueText;
        r0.setTextColor(Theme.getColor(r1));
        TextViewWithLoading r02 = this.yesButton;
        int r12 = Theme.getColor(r1);
        float r3 = 0.15f;
        if (Theme.isCurrentThemeDark() == false) goto L5;
        float r2 = 0.3f;
    L6:
        r02.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(r12, r2), 7, AndroidUtilities.dp(8.0f)));
        TextViewWithLoading r03 = this.noButton;
        int r13 = Theme.key_text_RedBold;
        r03.setTextColor(Theme.getColor(r13));
        TextViewWithLoading r7 = this.noButton;
        int r04 = Theme.getColor(r13);
        if (Theme.isCurrentThemeDark() == false) goto L9;
        r3 = 0.3f;
    L9:
        r7.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(r04, r3), 7, AndroidUtilities.dp(8.0f)));
        return;
    L5:
        r2 = 0.15f;
        goto L6
    }
}
