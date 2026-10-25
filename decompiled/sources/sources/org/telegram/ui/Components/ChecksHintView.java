package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.cast.MediaError;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes7.dex */
public class ChecksHintView extends FrameLayout {
    private AnimatorSet animatorSet;
    private ImageView arrowImageView;
    private View currentView;
    private Runnable hideRunnable;
    private RLottieImageView[] imageView;
    private ChatMessageCell messageCell;
    private final Theme.ResourcesProvider resourcesProvider;
    private long showingDuration;
    private TextView[] textView;
    private float translationY;

    /* renamed from: org.telegram.ui.Components.ChecksHintView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ChecksHintView this$0;

        public static /* synthetic */ void $r8$lambda$wt9VrfbY4mCxjcKNi02ZYWZ4EUs(AnonymousClass1 r0) {
            r0.lambda$onAnimationEnd$0();
        }

        public AnonymousClass1(ChecksHintView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0() {
            this.this$0.hide();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ChecksHintView.m10011$$Nest$fputanimatorSet(this.this$0, null);
            ChecksHintView r22 = this.this$0;
            ChecksHintView$1$$ExternalSyntheticLambda0 r0 = new ChecksHintView$1$$ExternalSyntheticLambda0(this);
            ChecksHintView.m10013$$Nest$fputhideRunnable(r22, r0);
            AndroidUtilities.runOnUIThread(r0, 3000);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChecksHintView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ ChecksHintView this$0;
        final /* synthetic */ int val$num;

        public AnonymousClass2(ChecksHintView r1, int r2) {
            this.this$0 = r1;
            this.val$num = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            ChecksHintView.m10010$$Nest$fgettextView(this.this$0)[this.val$num].animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT).setStartDelay(0).setDuration(100).start();
        }
    }

    /* renamed from: org.telegram.ui.Components.ChecksHintView$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ ChecksHintView this$0;

        public AnonymousClass3(ChecksHintView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.setVisibility(4);
            ChecksHintView.m10012$$Nest$fputcurrentView(this.this$0, null);
            ChecksHintView.m10014$$Nest$fputmessageCell(this.this$0, null);
            ChecksHintView.m10011$$Nest$fputanimatorSet(this.this$0, null);
        }
    }

    /* renamed from: -$$Nest$fgettextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView[] m10010$$Nest$fgettextView(ChecksHintView r0) {
        return r0.textView;
    }

    /* renamed from: -$$Nest$fputanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10011$$Nest$fputanimatorSet(ChecksHintView r0, AnimatorSet r1) {
        r0.animatorSet = r1;
    }

    /* renamed from: -$$Nest$fputcurrentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10012$$Nest$fputcurrentView(ChecksHintView r0, View r1) {
        r0.currentView = r1;
    }

    /* renamed from: -$$Nest$fputhideRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10013$$Nest$fputhideRunnable(ChecksHintView r0, Runnable r1) {
        r0.hideRunnable = r1;
    }

    /* renamed from: -$$Nest$fputmessageCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10014$$Nest$fputmessageCell(ChecksHintView r0, ChatMessageCell r1) {
        r0.messageCell = r1;
    }

    public ChecksHintView(Context r13, Theme.ResourcesProvider r14) {
        super(r13);
        this.textView = new TextView[2];
        this.imageView = new RLottieImageView[2];
        this.showingDuration = 2000;
        this.resourcesProvider = r14;
        FrameLayout r142 = new FrameLayout(r13);
        r142.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(6.0f), getThemedColor(Theme.key_chat_gifSaveHintBackground)));
        int r4 = 0;
        r142.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
        addView(r142, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 0.0f, 0.0f, 6.0f));
    L3:
        if (r4 >= 2) goto L18;
        this.imageView[r4] = new RLottieImageView(r13);
        this.imageView[r4].setScaleType(ImageView.ScaleType.CENTER);
        RLottieImageView r1 = this.imageView[r4];
        if (r4 != 0) goto L7;
        float r9 = 0.0f;
    L8:
        r142.addView(r1, LayoutHelper.createFrame(24, 24.0f, 51, 0.0f, r9, 0.0f, 0.0f));
        this.textView[r4] = new TextView(r13);
        this.textView[r4].setTextColor(getThemedColor(Theme.key_chat_gifSaveHintText));
        this.textView[r4].setTextSize(1, 14.0f);
        this.textView[r4].setMaxLines(1);
        this.textView[r4].setSingleLine(true);
        this.textView[r4].setMaxWidth(AndroidUtilities.dp(250.0f));
        this.textView[r4].setGravity(51);
        this.textView[r4].setPivotX(0.0f);
        TextView r12 = this.textView[r4];
        if (r4 != 0) goto L12;
        float r2 = 2.0f;
    L13:
        r142.addView(r12, LayoutHelper.createFrame(-2, -2.0f, 51, 32.0f, r2, 10.0f, 0.0f));
        RLottieImageView[] r15 = this.imageView;
        if (r4 != 0) goto L16;
        r15[r4].setAnimation(R.raw.ticks_single, 24, 24);
        this.textView[r4].setText(LocaleController.getString(R.string.HintSent));
    L17:
        this.imageView[r4].playAnimation();
        r4 = r4 + 1;
        goto L3
    L16:
        r15[r4].setAnimation(R.raw.ticks_double, 24, 24);
        this.textView[r4].setText(LocaleController.getString(R.string.HintRead));
        goto L17
    L12:
        r2 = 26.0f;
        goto L13
    L7:
        r9 = 24.0f;
        goto L8
    L18:
        ImageView r143 = new ImageView(r13);
        this.arrowImageView = r143;
        r143.setImageResource(R.drawable.tooltip_arrow);
        this.arrowImageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_gifSaveHintBackground), PorterDuff.Mode.MULTIPLY));
        addView(this.arrowImageView, LayoutHelper.createFrame(14, 6.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public float getBaseTranslationY() {
        return this.translationY;
    }

    public void hide() {
        if (getTag() != null) goto L5;
        return;
    L5:
        setTag(null);
        Runnable r1 = this.hideRunnable;
        if (r1 == null) goto L8;
        AndroidUtilities.cancelRunOnUIThread(r1);
        this.hideRunnable = null;
    L8:
        AnimatorSet r12 = this.animatorSet;
        if (r12 == null) goto L11;
        r12.cancel();
        this.animatorSet = null;
    L11:
        AnimatorSet r0 = new AnimatorSet();
        this.animatorSet = r0;
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, View.ALPHA, new float[]{0.0f}), ObjectAnimator.ofFloat(this, View.SCALE_X, new float[]{0.0f}), ObjectAnimator.ofFloat(this, View.SCALE_Y, new float[]{0.0f})});
        this.animatorSet.addListener(new AnonymousClass3(this));
        this.animatorSet.setDuration(180);
        this.animatorSet.start();
    }

    public boolean showForMessageCell(ChatMessageCell r12, boolean r13) {
        Runnable r0 = this.hideRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.hideRunnable = null;
    L5:
        int[] r2 = new int[2];
        r12.getLocationInWindow(r2);
        int r4 = r2[1];
        ((View) getParent()).getLocationInWindow(r2);
        int r42 = r4 - r2[1];
        View r22 = (View) r12.getParent();
        measure(View.MeasureSpec.makeMeasureSpec(1000, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(1000, Integer.MIN_VALUE));
        int r7 = 0;
        if (r42 > (getMeasuredHeight() + AndroidUtilities.dp(10.0f))) goto L8;
        return false;
    L8:
        int r43 = r42 + (r12.getChecksY() + AndroidUtilities.dp(6.0f));
        int r5 = r12.getChecksX() + AndroidUtilities.dp(5.0f);
        int r8 = r22.getMeasuredWidth();
        float r44 = r43 - getMeasuredHeight();
        this.translationY = r44;
        setTranslationY(r44);
        int r45 = r12.getLeft() + r5;
        int r9 = AndroidUtilities.dp(15.0f);
        if (r45 <= (r22.getMeasuredWidth() / 2)) goto L11;
        int r82 = (r8 - getMeasuredWidth()) - AndroidUtilities.dp(20.0f);
        setTranslationX(r82);
        r9 = r9 + r82;
    L12:
        float r52 = ((r12.getLeft() + r5) - r9) - (this.arrowImageView.getMeasuredWidth() / 2);
        this.arrowImageView.setTranslationX(r52);
        if (r45 <= (r22.getMeasuredWidth() / 2)) goto L18;
        if (r52 >= AndroidUtilities.dp(10.0f)) goto L23;
        float r23 = r52 - AndroidUtilities.dp(10.0f);
        setTranslationX(getTranslationX() + r23);
        this.arrowImageView.setTranslationX(r52 - r23);
    L23:
        setPivotX(r52);
        setPivotY(getMeasuredHeight());
        this.messageCell = r12;
        AnimatorSet r122 = this.animatorSet;
        if (r122 == null) goto L26;
        r122.cancel();
        this.animatorSet = null;
    L26:
        setTag(1);
        setVisibility(0);
        if (r13 == false) goto L35;
        AnimatorSet r123 = new AnimatorSet();
        this.animatorSet = r123;
        r123.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, View.ALPHA, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(this, View.SCALE_X, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(this, View.SCALE_Y, new float[]{0.0f, 1.0f})});
        this.animatorSet.addListener(new AnonymousClass1(this));
        this.animatorSet.setDuration(180);
        this.animatorSet.start();
    L29:
        if (r7 >= 2) goto L36;
        ViewPropertyAnimator r124 = this.textView[r7].animate().scaleX(1.04f).scaleY(1.04f).setInterpolator(CubicBezierInterpolator.EASE_IN);
        if (r7 != 0) goto L33;
        int r132 = 132;
    L34:
        r124.setStartDelay(r132 + 140).setDuration(100).setListener(new AnonymousClass2(this, r7)).start();
        r7 = r7 + 1;
        goto L29
    L33:
        r132 = MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
    L36:
        return true;
    L35:
        setAlpha(1.0f);
        goto L36
    L18:
        if (r52 <= (getMeasuredWidth() - AndroidUtilities.dp(24.0f))) goto L21;
        float r24 = (r52 - getMeasuredWidth()) + AndroidUtilities.dp(24.0f);
        setTranslationX(r24);
        this.arrowImageView.setTranslationX(r52 - r24);
        goto L23
    L21:
        if (r52 >= AndroidUtilities.dp(10.0f)) goto L23;
        float r25 = r52 - AndroidUtilities.dp(10.0f);
        setTranslationX(getTranslationX() + r25);
        this.arrowImageView.setTranslationX(r52 - r25);
        goto L23
    L11:
        setTranslationX(0.0f);
        goto L12
    }
}
