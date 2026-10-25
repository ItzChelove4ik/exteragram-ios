package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;

/* loaded from: classes3.dex */
public class NotificationPermissionDialog extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    private CounterView counterView;
    private RLottieImageView rLottieImageView;
    private long showTime;
    private Utilities.Callback<Boolean> whenGranted;

    /* loaded from: classes6.dex */
    public static class CounterView extends View {
        private final AnimatedFloat alpha;
        private ValueAnimator countAnimator;
        private float countScale;
        private final Paint fillPaint;
        private int lastCount;
        private final Paint strokePaint;
        AnimatedTextView.AnimatedTextDrawable textDrawable;

        /* renamed from: org.telegram.ui.NotificationPermissionDialog$CounterView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ CounterView this$0;

            public AnonymousClass1(CounterView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                CounterView.m16258$$Nest$fputcountScale(this.this$0, 1.0f);
                this.this$0.invalidate();
            }
        }

        /* renamed from: $r8$lambda$jAJmBUDBbQWKMRCT8MDx-Je5mfg, reason: not valid java name */
        public static /* synthetic */ void m16257$r8$lambda$jAJmBUDBbQWKMRCT8MDxJe5mfg(CounterView r0, ValueAnimator r1) {
            r0.lambda$animateBounce$0(r1);
        }

        /* renamed from: -$$Nest$fputcountScale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16258$$Nest$fputcountScale(CounterView r0, float r1) {
            r0.countScale = r1;
        }

        public CounterView(Context r11) {
            super(r11);
            Paint r112 = new Paint(1);
            this.fillPaint = r112;
            Paint r1 = new Paint(1);
            this.strokePaint = r1;
            CubicBezierInterpolator r8 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.alpha = new AnimatedFloat(this, 0, 320, r8);
            this.textDrawable = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
            this.countScale = 1.0f;
            r112.setColor(Theme.getColor(Theme.key_featuredStickers_addButton));
            r1.setColor(Theme.getColor(Theme.key_dialogBackground));
            r1.setStyle(Paint.Style.STROKE);
            r1.setStrokeWidth(AndroidUtilities.dp(4.0f));
            this.textDrawable.setCallback(this);
            this.textDrawable.setAnimationProperties(0.35f, 0, 200, r8);
            this.textDrawable.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
            this.textDrawable.getPaint().setStrokeWidth(AndroidUtilities.dp(0.24f));
            this.textDrawable.getPaint().setStrokeJoin(Paint.Join.ROUND);
            this.textDrawable.setTextSize(AndroidUtilities.dp(13.3f));
            this.textDrawable.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
            this.textDrawable.setOverrideFullWidth(AndroidUtilities.dp(64.0f));
            this.textDrawable.setGravity(1);
        }

        private void animateBounce() {
            ValueAnimator r0 = this.countAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
            this.countAnimator = null;
        L5:
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.countAnimator = r02;
            r02.addUpdateListener(new NotificationPermissionDialog$CounterView$$ExternalSyntheticLambda0(this));
            this.countAnimator.addListener(new AnonymousClass1(this));
            this.countAnimator.setInterpolator(new OvershootInterpolator(2.0f));
            this.countAnimator.setDuration(200);
            this.countAnimator.start();
        }

        private /* synthetic */ void lambda$animateBounce$0(ValueAnimator r2) {
            this.countScale = Math.max(1.0f, ((Float) r2.getAnimatedValue()).floatValue());
            invalidate();
        }

        @Override // android.view.View
        public void onDraw(Canvas r11) {
            AnimatedFloat r0 = this.alpha;
            if (this.lastCount <= 0) goto L5;
            float r1 = 1.0f;
        L6:
            float r02 = r0.set(r1);
            r11.save();
            float r12 = this.countScale;
            r11.scale(r12 * r02, r12 * r02, getWidth() / 2.0f, getHeight() / 2.0f);
            float r13 = this.textDrawable.getCurrentWidth() + AndroidUtilities.dpf2(12.66f);
            float r4 = AndroidUtilities.dpf2(20.3f);
            RectF r5 = AndroidUtilities.rectTmp;
            r5.set((getWidth() - r13) / 2.0f, (getHeight() - r4) / 2.0f, (getWidth() + r13) / 2.0f, (getHeight() + r4) / 2.0f);
            int r03 = (int) (r02 * 255.0f);
            this.strokePaint.setAlpha(r03);
            r11.drawRoundRect(r5, AndroidUtilities.dp(30.0f), AndroidUtilities.dp(30.0f), this.strokePaint);
            this.fillPaint.setAlpha(r03);
            r11.drawRoundRect(r5, AndroidUtilities.dp(30.0f), AndroidUtilities.dp(30.0f), this.fillPaint);
            r11.save();
            r11.translate(0.0f, -AndroidUtilities.dp(1.0f));
            this.textDrawable.setBounds(0, 0, getWidth(), getHeight());
            this.textDrawable.draw(r11);
            r11.restore();
            r11.restore();
            return;
        L5:
            r1 = 0.0f;
            goto L6
        }

        public boolean setCount(int r5) {
            int r0 = this.lastCount;
            boolean r1 = false;
            if (r0 != r5) goto L5;
        L13:
            return r1;
        L5:
            if (r0 >= r5) goto L7;
            r1 = true;
        L7:
            this.lastCount = r5;
            AnimatedTextView.AnimatedTextDrawable r02 = this.textDrawable;
            String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r5 <= 0) goto L10;
            r3 = _UrlKt.FRAGMENT_ENCODE_SET + this.lastCount;
        L10:
            r02.setText(r3, true);
            if (r1 == false) goto L13;
            animateBounce();
            goto L13
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.textDrawable) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* loaded from: classes6.dex */
    public static class SectionView extends FrameLayout {
        public SectionView(Context r17, int r18, CharSequence r19) {
            super(r17);
            setPadding(0, AndroidUtilities.dp(7.0f), 0, AndroidUtilities.dp(7.0f));
            ImageView r2 = new ImageView(r17);
            r2.setImageResource(r18);
            r2.setScaleType(ImageView.ScaleType.CENTER);
            int r4 = Theme.key_dialogTextBlack;
            r2.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r4), PorterDuff.Mode.MULTIPLY));
            boolean r3 = LocaleController.isRTL;
            int r5 = 3;
            if (r3 == false) goto L5;
            int r7 = 5;
        L6:
            int r10 = r7 | 16;
            if (r3 == false) goto L9;
            float r11 = 0.0f;
        L10:
            if (r3 == false) goto L12;
            float r13 = 22.0f;
        L13:
            addView(r2, LayoutHelper.createFrame(24, 24.0f, r10, r11, 0.0f, r13, 0.0f));
            TextView r22 = new TextView(r17);
            r22.setTextColor(Theme.getColor(r4));
            r22.setTextSize(1, 14.0f);
            if (LocaleController.isRTL == false) goto L16;
            r5 = 5;
        L16:
            r22.setGravity(r5);
            r22.setText(r19);
            boolean r1 = LocaleController.isRTL;
            if (r1 == false) goto L19;
            float r72 = 0.0f;
        L20:
            if (r1 == false) goto L22;
            float r9 = 61.0f;
        L23:
            addView(r22, LayoutHelper.createFrame(-1, -2.0f, 23, r72, 0.0f, r9, 0.0f));
            return;
        L22:
            r9 = 0.0f;
            goto L23
        L19:
            r72 = 61.0f;
            goto L20
        L12:
            r13 = 0.0f;
            goto L13
        L9:
            r11 = 22.0f;
            goto L10
        L5:
            r7 = 3;
            goto L6
        }
    }

    /* renamed from: $r8$lambda$W-meIy-cTOl1eMn7is4ioJcuPRU, reason: not valid java name */
    public static /* synthetic */ void m16256$r8$lambda$WmeIycTOl1eMn7is4ioJcuPRU(NotificationPermissionDialog r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$hkSSUvunPQawJyI75SM1Q28hbXU(NotificationPermissionDialog r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public NotificationPermissionDialog(Context r18, boolean r19, Utilities.Callback<Boolean> r20) {
        int r2 = 0;
        super(r18, false);
        this.whenGranted = r20;
        LinearLayout r3 = new LinearLayout(r18);
        r3.setOrientation(1);
        FrameLayout r5 = new FrameLayout(r18);
        RLottieImageView r6 = new RLottieImageView(r18);
        this.rLottieImageView = r6;
        r6.setScaleType(ImageView.ScaleType.CENTER);
        this.rLottieImageView.setAnimation(R.raw.silent_unmute, 46, 46);
        this.rLottieImageView.playAnimation();
        RLottieImageView r62 = this.rLottieImageView;
        int r7 = AndroidUtilities.dp(72.0f);
        int r8 = Theme.key_featuredStickers_addButton;
        r62.setBackground(Theme.createCircleDrawable(r7, Theme.getColor(r8)));
        r5.addView(this.rLottieImageView, LayoutHelper.createFrame(72, 72, 17));
        CounterView r63 = new CounterView(r18);
        this.counterView = r63;
        r5.addView(r63, LayoutHelper.createFrame(64, 32.0f, 49, 29.0f, 16.0f, 0.0f, 0.0f));
        this.counterView.setCount(0);
        r5.setOnClickListener(new NotificationPermissionDialog$$ExternalSyntheticLambda0(this));
        r3.addView(r5, LayoutHelper.createLinear(-1, 110));
        TextView r52 = new TextView(r18);
        int r64 = Theme.key_dialogTextBlack;
        r52.setTextColor(Theme.getColor(r64));
        r52.setTypeface(AndroidUtilities.bold());
        r52.setTextSize(1, 20.0f);
        r52.setGravity(1);
        r52.setText(LocaleController.getString(R.string.NotificationsPermissionAlertTitle));
        r52.setPadding(AndroidUtilities.dp(30.0f), 0, AndroidUtilities.dp(30.0f), 0);
        r3.addView(r52, LayoutHelper.createLinear(-1, -2));
        TextView r53 = new TextView(r18);
        r53.setTextColor(Theme.getColor(r64));
        r53.setTextSize(1, 14.0f);
        r53.setGravity(1);
        r53.setText(LocaleController.getString(R.string.NotificationsPermissionAlertSubtitle));
        r53.setPadding(AndroidUtilities.dp(30.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(30.0f), AndroidUtilities.dp(21.0f));
        r3.addView(r53, LayoutHelper.createLinear(-1, -2));
        r3.addView(new SectionView(r18, R.drawable.msg_message_s, LocaleController.getString(R.string.NotificationsPermissionAlert1)), LayoutHelper.createLinear(-1, -2));
        r3.addView(new SectionView(r18, R.drawable.msg_members_list2, LocaleController.getString(R.string.NotificationsPermissionAlert2)), LayoutHelper.createLinear(-1, -2));
        r3.addView(new SectionView(r18, R.drawable.msg_customize_s, LocaleController.getString(R.string.NotificationsPermissionAlert3)), LayoutHelper.createLinear(-1, -2));
        setCustomView(r3);
        fixNavigationBar(getThemedColor(Theme.key_dialogBackground));
        TextView r54 = new TextView(r18);
        if (r19 == false) goto L5;
        int r1 = R.string.NotificationsPermissionSettings;
    L6:
        r54.setText(LocaleController.getString(r1));
        r54.setGravity(17);
        r54.setTypeface(AndroidUtilities.bold());
        r54.setTextSize(1, 14.0f);
        r54.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        r54.setBackground(Theme.AdaptiveRipple.filledRect(Theme.getColor(r8), new float[]{24.0f}));
        r54.setOnClickListener(new NotificationPermissionDialog$$ExternalSyntheticLambda1(this));
        r3.addView(r54, LayoutHelper.createLinear(-1, 48, 14.0f, 14.0f, 14.0f, 10.0f));
    L8:
        if (r2 >= 16) goto L11;
        NotificationCenter.getInstance(r2).addObserver(this, NotificationCenter.updateInterfaces);     // Catch: Exception -> L12
    L10:
        r2 = r2 + 1;
        goto L8
    L11:
        return;
    L5:
        r1 = R.string.NotificationsPermissionContinue;
        goto L6
    }

    public static void askLater() {
        long r0 = MessagesController.getGlobalMainSettings().getLong("askNotificationsDuration", DurationKt.MILLIS_IN_DAY);
        long r4 = System.currentTimeMillis() + r0;
        long r6 = 259200000;
        if (r0 < 259200000) goto L9;
        r6 = 604800000;
        if (r0 < 604800000) goto L9;
        r6 = 2592000000L;
    L9:
        MessagesController.getGlobalMainSettings().edit().putLong("askNotificationsAfter", r4).putLong("askNotificationsDuration", r6).apply();
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        if (this.rLottieImageView.isPlaying() == true) goto L6;
        this.rLottieImageView.setProgress(0.0f);
        this.rLottieImageView.playAnimation();
        return;
    }

    private /* synthetic */ void lambda$new$1(View r2) {
        Utilities.Callback<Boolean> r22 = this.whenGranted;
        if (r22 == null) goto L5;
        r22.run(Boolean.TRUE);
        this.whenGranted = null;
    L5:
        dismiss();
    }

    public static boolean shouldAsk(Activity r5) {
        if (r5 != null) goto L5;
    L15:
        return false;
    L5:
        if (r5.checkSelfPermission("android.permission.POST_NOTIFICATIONS") == 0) goto L15;
        long r1 = MessagesController.getGlobalMainSettings().getLong("askNotificationsAfter", -1);
        if (r1 == (-2)) goto L15;
        if (r1 >= 0) goto L12;
        return true;
    L12:
        if (System.currentTimeMillis() < r1) goto L15;
        return true;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.updateInterfaces) goto L5;
        return;
    L5:
        if ((((Integer) r3[0]).intValue() & MessagesController.UPDATE_MASK_READ_DIALOG_MESSAGE) < 0) goto L9;
        updateCounter();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        Utilities.Callback<Boolean> r0 = this.whenGranted;
        if (r0 == null) goto L5;
        r0.run(Boolean.FALSE);
        this.whenGranted = null;
        askLater();
    L5:
        int r02 = 0;
    L7:
        if (r02 >= 16) goto L10;
        NotificationCenter.getInstance(r02).removeObserver(this, NotificationCenter.updateInterfaces);     // Catch: Exception -> L11
    L9:
        r02 = r02 + 1;
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        this.showTime = System.currentTimeMillis();
    }

    public void updateCounter() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= 16) goto L10;
        MessagesStorage r2 = MessagesStorage.getInstance(r0);
        if (r2 == null) goto L8;
        r1 = r1 + r2.getMainUnreadCount();
    L8:
        r0 = r0 + 1;
        goto L4
    L10:
        if (this.counterView.setCount(r1) == true) goto L12;
        return;
    L12:
        if (this.rLottieImageView.isPlaying() == true) goto L19;
        this.rLottieImageView.setProgress(0.0f);
        this.rLottieImageView.playAnimation();
        return;
    }
}
