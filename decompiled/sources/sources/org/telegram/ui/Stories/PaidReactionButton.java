package org.telegram.ui.Stories;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.blur3.StrokeDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stars.StarsReactionsSheet;

/* loaded from: classes7.dex */
public class PaidReactionButton extends View {
    private float accumulatedRippleIntensity;
    private final AnimatedFloat animatedFilled;
    private final AnimatedFloat animatedShowCounter;
    private final Paint backgroundPaint;
    private final Paint clearPaint;
    private final Path clipPath;
    private float countScale;
    private final AnimatedTextView.AnimatedTextDrawable countText;
    private final PaidReactionButtonEffectsView effectsView;
    private boolean filled;
    private final Drawable iconDrawable;
    private long lastRippleTime;
    private final StarsReactionsSheet.Particles particles;
    private final int[] pos;
    private final RectF rect;
    private final ColoredImageSpan span;
    private int stars;
    private final StrokeDrawable strokeDrawable;

    public static class PaidReactionButtonEffectsView extends View {
        private final ArrayList<Chip> chips;
        private final AnimatedTextView.AnimatedTextDrawable counter;
        private final AnimatedFloat counterAlpha;
        private boolean counterShown;
        public final int currentAccount;
        private final int[] effectAssets;
        private final ArrayList<RLottieDrawable> effects;
        private float focus;
        private ValueAnimator focusAnimator;
        public boolean hidden;
        private Runnable hideCounterRunnable;
        public final RectF reactionBounds;

        /* renamed from: org.telegram.ui.Stories.PaidReactionButton$PaidReactionButtonEffectsView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ PaidReactionButtonEffectsView this$0;
            final /* synthetic */ float val$dst;
            final /* synthetic */ Runnable val$whenDone;

            public AnonymousClass1(PaidReactionButtonEffectsView r1, float r2, Runnable r3) {
                this.this$0 = r1;
                this.val$dst = r2;
                this.val$whenDone = r3;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                PaidReactionButtonEffectsView.m19425$$Nest$fputfocus(this.this$0, this.val$dst);
                this.this$0.invalidate();
                if (r3 != PaidReactionButtonEffectsView.m19424$$Nest$fgetfocusAnimator(this.this$0)) goto L8;
                Runnable r2 = this.val$whenDone;
                if (r2 == null) goto L9;
                r2.run();
                return;
            L9:
                return;
            }
        }

        public class Chip {
            private final AvatarDrawable avatarDrawable;
            private final Paint backgroundPaint;
            public final long dialogId;
            private RLottieDrawable effect;
            private final ImageReceiver imageReceiver;
            private boolean isKilled;
            public final AnimatedFloat killProgress;
            public final AnimatedFloat progress;
            private final float randomRotation;
            private final float randomTranslation;
            public final int stars;
            private final Text text;
            final /* synthetic */ PaidReactionButtonEffectsView this$0;

            /* renamed from: org.telegram.ui.Stories.PaidReactionButton$PaidReactionButtonEffectsView$Chip$1, reason: invalid class name */
            public class AnonymousClass1 implements View.OnAttachStateChangeListener {
                final /* synthetic */ Chip this$1;
                final /* synthetic */ PaidReactionButtonEffectsView val$this$0;

                public AnonymousClass1(Chip r1, PaidReactionButtonEffectsView r2) {
                    this.this$1 = r1;
                    this.val$this$0 = r2;
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View r1) {
                    Chip.m19426$$Nest$fgetimageReceiver(this.this$1).onAttachedToWindow();
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View r1) {
                    Chip.m19426$$Nest$fgetimageReceiver(this.this$1).onDetachedFromWindow();
                }
            }

            /* renamed from: -$$Nest$fgetimageReceiver, reason: not valid java name */
            public static /* bridge */ /* synthetic */ ImageReceiver m19426$$Nest$fgetimageReceiver(Chip r0) {
                return r0.imageReceiver;
            }

            public Chip(PaidReactionButtonEffectsView r8, View r9, int r10, long r11, int r13, int r14, boolean r15) {
                this.this$0 = r8;
                Paint r142 = new Paint(1);
                this.backgroundPaint = r142;
                this.dialogId = r11;
                this.stars = r13;
                this.randomTranslation = Utilities.clamp01(Utilities.fastRandom.nextFloat());
                this.randomRotation = Utilities.clamp01(Utilities.fastRandom.nextFloat());
                if (r15 == false) goto L5;
                RLottieDrawable r2 = new RLottieDrawable(PaidReactionButtonEffectsView.m19423$$Nest$fgeteffectAssets(r8)[Utilities.fastRandom.nextInt(PaidReactionButtonEffectsView.m19423$$Nest$fgeteffectAssets(r8).length)], AndroidUtilities.dp(70.0f), AndroidUtilities.dp(70.0f));
                this.effect = r2;
                r2.setMasterParent(r9);
                this.effect.setAllowDecodeSingleFrame(true);
                this.effect.setAutoRepeat(0);
                this.effect.start();
            L5:
                TLObject r102 = MessagesController.getInstance(r10).getUserOrChat(r11);
                AvatarDrawable r112 = new AvatarDrawable();
                this.avatarDrawable = r112;
                r112.setInfo(r102);
                ImageReceiver r12 = new ImageReceiver(r9);
                this.imageReceiver = r12;
                r12.setImageCoords(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f));
                r12.setRoundRadius(AndroidUtilities.dp(7.0f));
                r12.setForUserOrChat(r102, r112);
                r9.addOnAttachStateChangeListener(new AnonymousClass1(this, r8));
                if (r9.isAttachedToWindow() == false) goto L8;
                r12.onAttachedToWindow();
            L8:
                r142.setColor(-1135603);
                SpannableStringBuilder r82 = new SpannableStringBuilder("⭐️");
                ColoredImageSpan r103 = new ColoredImageSpan(R.drawable.star);
                r103.spaceScaleX = 0.875f;
                r82.setSpan(r103, 0, r82.length(), 33);
                r82.append(" ");
                r82.append(LocaleController.formatNumber(r13, ','));
                this.text = new Text(r82, 10.0f, AndroidUtilities.getTypeface("fonts/num.otf"));
                AnimatedFloat r83 = new AnimatedFloat(r9, 2000, new LinearInterpolator());
                this.progress = r83;
                r83.force(0.0f);
                r83.set(1.0f);
                this.killProgress = new AnimatedFloat(r9, 350, 240, CubicBezierInterpolator.EASE_OUT_QUINT);
            }

            public void detach() {
                this.imageReceiver.onDetachedFromWindow();
            }

            public boolean draw(Canvas r30) {
                float r10 = this.progress.set(1.0f);
                float r11 = this.killProgress.set(this.isKilled);
                float r4 = AndroidUtilities.dp(23.0f) + this.text.getCurrentWidth();
                float r5 = AndroidUtilities.dp(18.0f);
                float r14 = AndroidUtilities.lerp(0.0f, AndroidUtilities.lerp(1.0f, 0.0f, r11), Utilities.clamp01(Math.min(AndroidUtilities.ilerp(r10, 1.0f, 0.85f), AndroidUtilities.ilerp(r10, 0.0f, 0.12f))));
                int r3 = (int) (255.0f * r14);
                this.backgroundPaint.setAlpha(r3);
                RLottieDrawable r2 = this.effect;
                if (r2 == null) goto L5;
                r2.setAlpha(r3);
            L5:
                this.imageReceiver.setAlpha(r14);
                r30.save();
                double r22 = r10;
                float r9 = (float) Math.sin((Math.pow(r22, 0.44999998807907104d) * 3.141592653589793d) * 3.0d);
                r30.translate(AndroidUtilities.dp(4.0f) * ((this.randomTranslation * 2.0f) - 1.0f), 0.0f);
                r30.rotate(((this.randomRotation * 2.0f) - 1.0f) * 1.5f);
                r30.translate(0.0f, (-AndroidUtilities.dp(200.0f)) * ((float) Math.pow(r22, 0.800000011920929d)));
                r30.translate((AndroidUtilities.dp(5.0f) * r9) * ((float) Math.pow(r22, 0.5d)), 0.0f);
                r30.rotate(((float) (Math.sin(((Math.pow(r22, 0.44999998807907104d) - 0.15000000596046448d) * 3.141592653589793d) * 3.0d) * Utilities.clamp01((float) Math.pow(r22, 0.20000000298023224d)))) * (-6.0f));
                float r6 = AndroidUtilities.lerp(0.4f, 1.0f, r14);
                r30.scale(r6, r6);
                r30.translate((-r4) / 2.0f, (-r5) / 2.0f);
                float r62 = r5 / 2.0f;
                r30.drawRoundRect(0.0f, 0.0f, r4, r5, r62, r62, this.backgroundPaint);
                this.imageReceiver.draw(r30);
                this.text.draw(r30, AndroidUtilities.dp(18.0f), r62, -1, r14);
                r30.restore();
                if (this.effect == null) goto L9;
                r30.save();
                r30.translate(AndroidUtilities.dp(4.0f) * ((this.randomTranslation * 2.0f) - 1.0f), 0.0f);
                r30.rotate(((this.randomRotation * 2.0f) - 1.0f) * 1.5f);
                r30.translate(0.0f, (-AndroidUtilities.dp(200.0f)) * ((float) Math.pow(r22, 0.800000011920929d)));
                r30.translate((r9 * AndroidUtilities.dp(5.0f)) * ((float) Math.pow(r22, 0.5d)), 0.0f);
                int r23 = AndroidUtilities.dp(90.0f);
                int r42 = (-r23) / 2;
                int r24 = r23 / 2;
                this.effect.setBounds(r42, AndroidUtilities.dp(8.0f) + r42, r24, AndroidUtilities.dp(8.0f) + r24);
                this.effect.draw(r30);
                r30.restore();
            L9:
                if (r10 < 1.0f) goto L11;
                return true;
            L11:
                if (r11 >= 1.0f) goto L17;
                return false;
            L17:
                return true;
            }

            public void kill() {
                this.isKilled = true;
            }
        }

        /* renamed from: $r8$lambda$_W--Kh7WJeV4ZO9KSfV5bhl4HXg, reason: not valid java name */
        public static /* synthetic */ void m19421$r8$lambda$_WKh7WJeV4ZO9KSfV5bhl4HXg(PaidReactionButtonEffectsView r0) {
            r0.lambda$hide$2();
        }

        public static /* synthetic */ void $r8$lambda$knyW4rPhZqZzw8aK7QuSPwY4BLc(PaidReactionButtonEffectsView r0) {
            r0.lambda$new$1();
        }

        /* renamed from: $r8$lambda$z3pfeL-aowQyJscYz9zv6eTZIh8, reason: not valid java name */
        public static /* synthetic */ void m19422$r8$lambda$z3pfeLaowQyJscYz9zv6eTZIh8(PaidReactionButtonEffectsView r0, ValueAnimator r1) {
            r0.lambda$focusTo$0(r1);
        }

        /* renamed from: -$$Nest$fgeteffectAssets, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int[] m19423$$Nest$fgeteffectAssets(PaidReactionButtonEffectsView r0) {
            return r0.effectAssets;
        }

        /* renamed from: -$$Nest$fgetfocusAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ValueAnimator m19424$$Nest$fgetfocusAnimator(PaidReactionButtonEffectsView r0) {
            return r0.focusAnimator;
        }

        /* renamed from: -$$Nest$fputfocus, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19425$$Nest$fputfocus(PaidReactionButtonEffectsView r0, float r1) {
            r0.focus = r1;
        }

        public PaidReactionButtonEffectsView(Context r8, int r9) {
            super(r8);
            this.reactionBounds = new RectF();
            this.counterAlpha = new AnimatedFloat(this, 0, 420, CubicBezierInterpolator.EASE_OUT_QUINT);
            AnimatedTextView.AnimatedTextDrawable r7 = new AnimatedTextView.AnimatedTextDrawable();
            this.counter = r7;
            this.effects = new ArrayList();
            this.effectAssets = new int[]{R.raw.star_reaction_effect1, R.raw.star_reaction_effect2, R.raw.star_reaction_effect3, R.raw.star_reaction_effect4, R.raw.star_reaction_effect5};
            this.chips = new ArrayList();
            this.hidden = true;
            this.currentAccount = r9;
            r7.setCallback(this);
            r7.setHacks(false, true, true);
            r7.setTextSize(AndroidUtilities.dp(40.0f));
            r7.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
            r7.setShadowLayer(AndroidUtilities.dp(12.0f), 0.0f, AndroidUtilities.dp(3.5f), 0);
            r7.setOverrideFullWidth(AndroidUtilities.displaySize.x);
            r7.setTextColor(-1);
            r7.setGravity(17);
            this.hideCounterRunnable = new PaidReactionButton$PaidReactionButtonEffectsView$$ExternalSyntheticLambda0(this);
        }

        private /* synthetic */ void lambda$focusTo$0(ValueAnimator r1) {
            this.focus = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        private /* synthetic */ void lambda$hide$2() {
            clearEffects();
        }

        private /* synthetic */ void lambda$new$1() {
            this.counterShown = false;
            invalidate();
            hide();
        }

        public void clearEffects() {
            ArrayList<RLottieDrawable> r0 = this.effects;
            int r1 = r0.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            RLottieDrawable r3 = r0.get(r2);
            r2 = r2 + 1;
            r3.recycle(true);
            goto L3
        L5:
            this.effects.clear();
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r12) {
            float r0 = AndroidUtilities.lerp(1.0f, 1.8f, this.focus);
            int r1 = (int) (AndroidUtilities.dp(90.0f) * r0);
            int r3 = 0;
            int r4 = 0;
        L4:
            if (r4 >= this.effects.size()) goto L10;
            RLottieDrawable r5 = this.effects.get(r4);
            if (r5.getCurrentFrame() < r5.getFramesCount()) goto L8;
            this.effects.remove(r4);
            r4 = r4 - 1;
        L9:
            r4 = r4 + 1;
            goto L4
        L8:
            float r8 = r1 / 2.0f;
            r5.setBounds((int) ((this.reactionBounds.left + (AndroidUtilities.dp(15.0f) * r0)) - r8), (int) (this.reactionBounds.centerY() - r8), (int) ((this.reactionBounds.left + (AndroidUtilities.dp(15.0f) * r0)) + r8), (int) (this.reactionBounds.centerY() + r8));
            r5.setAlpha((int) (this.focus * 255.0f));
            r5.draw(r12);
            goto L9
        L10:
            float r02 = this.reactionBounds.centerX();
            float r13 = this.reactionBounds.top - AndroidUtilities.dp(1.0f);
            r12.save();
            r12.translate(r02, r13);
        L12:
            if (r3 >= this.chips.size()) goto L17;
            if (this.chips.get(r3).draw(r12) == false) goto L16;
            this.chips.get(r3).detach();
            this.chips.remove(r3);
            r3 = r3 - 1;
        L16:
            r3 = r3 + 1;
            goto L12
        L17:
            r12.restore();
        }

        public void focusTo(float r4, Runnable r5) {
            ValueAnimator r0 = this.focusAnimator;
            if (r0 == null) goto L5;
            this.focusAnimator = null;
            r0.cancel();
        L5:
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.focus, r4});
            this.focusAnimator = r02;
            r02.addUpdateListener(new PaidReactionButton$PaidReactionButtonEffectsView$$ExternalSyntheticLambda2(this));
            this.focusAnimator.addListener(new AnonymousClass1(this, r4, r5));
            this.focusAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.focusAnimator.setDuration(320);
            this.focusAnimator.start();
        }

        public void hide() {
            this.hidden = true;
            AndroidUtilities.cancelRunOnUIThread(this.hideCounterRunnable);
            this.counter.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.counterShown = false;
            invalidate();
            focusTo(0.0f, new PaidReactionButton$PaidReactionButtonEffectsView$$ExternalSyntheticLambda1(this));
        }

        public void playEffect() {
        L3:
            if (this.effects.size() <= 4) goto L5;
            this.effects.remove(0).recycle(true);
            goto L3
        L5:
            int[] r0 = this.effectAssets;
            RLottieDrawable r1 = new RLottieDrawable(r0[Utilities.fastRandom.nextInt(r0.length)], AndroidUtilities.dp(70.0f), AndroidUtilities.dp(70.0f));
            r1.setMasterParent(this);
            r1.setAllowDecodeSingleFrame(true);
            r1.setAutoRepeat(0);
            r1.start();
            this.effects.add(r1);
            invalidate();
        }

        public void pushChip(long r10, int r12, int r13) {
            int r3 = this.currentAccount;
            if (this.chips.size() >= 5) goto L6;
            boolean r1 = true;
        L7:
            Chip r0 = new Chip(this, this, r3, r10, r13, r12, r1);
            this.chips.add(r0);
            invalidate();
            return;
        L6:
            r1 = false;
            goto L7
        }

        public void removeChipsFrom(long r4) {
            int r0 = 0;
        L4:
            if (r0 >= this.chips.size()) goto L9;
            if (this.chips.get(r0).dialogId != r4) goto L8;
            this.chips.get(r0).kill();
        L8:
            r0 = r0 + 1;
            goto L4
        }

        public void show() {
            this.hidden = false;
            focusTo(1.0f, null);
        }

        public void showCounter(long r4) {
            this.counter.cancelAnimation();
            this.counter.setText("+" + LocaleController.formatNumber(r4, ','));
            this.counterShown = true;
            AndroidUtilities.cancelRunOnUIThread(this.hideCounterRunnable);
            AndroidUtilities.runOnUIThread(this.hideCounterRunnable, 1500);
        }

        public void updatePosition(PaidReactionButton r6) {
            this.reactionBounds.set(r6.getX() - getX(), r6.getY() - getY(), (r6.getX() - getX()) + r6.getWidth(), (r6.getY() - getY()) + r6.getHeight());
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.counter) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public PaidReactionButton(Context r5, PaidReactionButtonEffectsView r6, BlurredBackgroundColorProvider r7) {
        super(r5);
        this.rect = new RectF();
        this.clipPath = new Path();
        CubicBezierInterpolator r1 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatedFilled = new AnimatedFloat(this, 320, r1);
        this.animatedShowCounter = new AnimatedFloat(this, 320, r1);
        Paint r0 = new Paint(1);
        this.backgroundPaint = r0;
        Paint r2 = new Paint(1);
        this.clearPaint = r2;
        this.pos = new int[2];
        this.countScale = 1.0f;
        this.effectsView = r6;
        ScaleStateListAnimator.apply(this);
        this.iconDrawable = r5.getResources().getDrawable(R.drawable.star).mutate();
        StrokeDrawable r52 = new StrokeDrawable();
        this.strokeDrawable = r52;
        r52.setColorProvider(r7);
        AnimatedTextView.AnimatedTextDrawable r53 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.countText = r53;
        r53.setTextColor(-9866632);
        r53.setTextSize(AndroidUtilities.dp(9.0f));
        r53.setCallback(this);
        r53.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
        r53.setAllowCancel(true);
        r0.setColor(-14670806);
        r2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        ColoredImageSpan r54 = new ColoredImageSpan(R.drawable.star);
        this.span = r54;
        r54.setScale(1.8f, 1.8f);
        setCount(0);
        this.particles = new StarsReactionsSheet.Particles(1, 50);
    }

    private void ripple() {
        getLocationInWindow(this.pos);
        long r0 = System.currentTimeMillis();
        long r4 = r0 - this.lastRippleTime;
        float r5 = this.accumulatedRippleIntensity;
        if (r4 >= 100) goto L6;
        this.accumulatedRippleIntensity = r5 + 0.5f;
        return;
    L6:
        this.accumulatedRippleIntensity = r5 * Utilities.clamp(1.0f - (((r0 - r2) - 100) / 200.0f), 1.0f, 0.0f);
        LaunchActivity.makeRipple(this.pos[0] + (getWidth() / 2.0f), this.pos[1] + (getHeight() / 2.0f), Utilities.clamp(this.accumulatedRippleIntensity, 0.9f, 0.3f));
        this.accumulatedRippleIntensity = 0.0f;
        this.lastRippleTime = r0;
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r12) {
        float r0 = AndroidUtilities.dp(38.0f);
        float r1 = this.animatedFilled.set(this.filled);
        AnimatedFloat r2 = this.animatedShowCounter;
        if (this.stars <= 0) goto L5;
        boolean r3 = true;
    L6:
        float r22 = r2.set(r3);
        this.rect.set((getWidth() - r0) / 2.0f, (getHeight() - r0) / 2.0f, (getWidth() + r0) / 2.0f, (getHeight() + r0) / 2.0f);
        int r02 = ColorUtils.blendARGB(-14670806, -548067, r1);
        this.backgroundPaint.setColor(r02);
        StrokeDrawable r32 = this.strokeDrawable;
        RectF r4 = this.rect;
        r32.setBounds((int) r4.left, (int) r4.top, (int) r4.right, (int) r4.bottom);
        this.strokeDrawable.setBackgroundColor(r02);
        this.strokeDrawable.draw(r12);
        int r03 = AndroidUtilities.dp(20.0f);
        this.iconDrawable.setBounds((getWidth() - r03) / 2, (getHeight() - r03) / 2, (getWidth() + r03) / 2, (getHeight() + r03) / 2);
        this.iconDrawable.draw(r12);
        r12.save();
        this.clipPath.rewind();
        Path r04 = this.clipPath;
        RectF r33 = this.rect;
        r04.addRoundRect(r33, r33.height() / 2.0f, this.rect.height() / 2.0f, Path.Direction.CW);
        r12.clipPath(this.clipPath);
        this.particles.setSpeed(AndroidUtilities.lerp(5.0f, 15.0f, r1));
        this.particles.setBounds(this.rect);
        this.particles.process();
        this.particles.draw(r12, -1, AndroidUtilities.lerp(0.5f, 1.0f, r1));
        invalidate();
        r12.restore();
        if (r22 <= 0.0f) goto L10;
        float r34 = Math.max(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f) + this.countText.getCurrentWidth());
        float r6 = (this.countScale * this.countText.isNotEmpty()) * r22;
        r12.save();
        RectF r23 = AndroidUtilities.rectTmp;
        r23.set(getWidth() - r34, 0.0f, getWidth(), AndroidUtilities.dp(13.0f));
        r12.scale(r6, r6, r23.centerX(), r23.centerY());
        r23.inset(-AndroidUtilities.dp(2.0f), -AndroidUtilities.dp(2.0f));
        r12.drawRoundRect(r23, r23.height() / 2.0f, r23.height() / 2.0f, this.clearPaint);
        r23.set(getWidth() - r34, 0.0f, getWidth(), AndroidUtilities.dp(13.0f));
        r12.drawRoundRect(r23, r23.height() / 2.0f, r23.height() / 2.0f, this.backgroundPaint);
        r12.translate(r23.left + ((r34 - this.countText.getCurrentWidth()) / 2.0f), AndroidUtilities.dp(6.33f));
        this.countText.setTextColor(ColorUtils.blendARGB(-9866632, -1, r1));
        this.countText.draw(r12);
        r12.restore();
        return;
    L10:
        return;
    L5:
        r3 = false;
        goto L6
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        this.effectsView.updatePosition(this);
    }

    public void playEffect(long r3) {
        this.effectsView.updatePosition(this);
        PaidReactionButtonEffectsView r0 = this.effectsView;
        if (r0.hidden == false) goto L5;
        r0.show();
    L5:
        this.effectsView.playEffect();
        this.effectsView.showCounter(r3);
        ripple();
    }

    public void setCount(int r4) {
        this.stars = r4;
        AnimatedTextView.AnimatedTextDrawable r0 = this.countText;
        if (r4 <= 50000) goto L5;
        r0.setText(AndroidUtilities.formatWholeNumber(r4, 0));
    L6:
        invalidate();
        requestLayout();
        return;
    L5:
        r0.setText(LocaleController.formatNumber(r4, ','));
        goto L6
    }

    public void setFilled(boolean r2) {
        if (this.filled != r2) goto L5;
        return;
    L5:
        this.filled = r2;
        invalidate();
    }

    public void stopEffects() {
        this.effectsView.updatePosition(this);
        this.effectsView.hide();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.countText != r2) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
