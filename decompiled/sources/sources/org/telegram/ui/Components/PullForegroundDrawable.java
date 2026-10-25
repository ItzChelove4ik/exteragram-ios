package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes3.dex */
public abstract class PullForegroundDrawable {
    private ValueAnimator accentRevalAnimatorIn;
    private ValueAnimator accentRevalAnimatorOut;
    private float accentRevalProgress;
    private float accentRevalProgressOut;
    private boolean animateOut;
    private boolean animateToColorize;
    private boolean animateToEndText;
    private boolean animateToTextIn;
    private boolean arrowAnimateTo;
    private final ArrowDrawable arrowDrawable;
    private ValueAnimator arrowRotateAnimator;
    private float arrowRotateProgress;
    private int avatarBackgroundColorKey;
    private int backgroundActiveColorKey;
    private int backgroundColorKey;
    private final Paint backgroundPaint;
    private boolean bounceIn;
    private float bounceProgress;
    private View cell;
    private boolean changeAvatarColor;
    private final Path circleClipPath;
    private Drawable generalTopicDrawable;
    private boolean isOut;
    private int lastWidth;
    private RecyclerListView listView;
    private AnimatorSet outAnimator;
    public float outCx;
    public float outCy;
    public float outImageSize;
    public float outOverScroll;
    public float outProgress;
    public float outRadius;
    private final Paint paintBackgroundAccent;
    private final Paint paintSecondary;
    private final Paint paintWhite;
    private float pullProgress;
    private StaticLayout pullTooltipLayout;
    private float pullTooltipLayoutLeft;
    private float pullTooltipLayoutScale;
    private float pullTooltipLayoutWidth;
    private final CharSequence pullTooltipText;
    private final RectF rectF;
    private StaticLayout releaseTooltipLayout;
    private float releaseTooltipLayoutLeft;
    private float releaseTooltipLayoutScale;
    private float releaseTooltipLayoutWidth;
    private final CharSequence releaseTooltipText;
    public int scrollDy;
    private float textInProgress;
    Runnable textInRunnable;
    private ValueAnimator.AnimatorUpdateListener textInUpdateListener;
    private ValueAnimator textIntAnimator;
    private float textSwappingProgress;
    private ValueAnimator.AnimatorUpdateListener textSwappingUpdateListener;
    private ValueAnimator textSwipingAnimator;
    private final TextPaint tooltipTextPaint;
    private float touchSlop;
    boolean wasSendCallback;
    private boolean willDraw;

    /* renamed from: org.telegram.ui.Components.PullForegroundDrawable$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ PullForegroundDrawable this$0;

        public AnonymousClass1(PullForegroundDrawable r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            PullForegroundDrawable.m12270$$Nest$fputanimateToTextIn(this.this$0, true);
            if (PullForegroundDrawable.m12269$$Nest$fgettextIntAnimator(this.this$0) == null) goto L5;
            PullForegroundDrawable.m12269$$Nest$fgettextIntAnimator(this.this$0).cancel();
        L5:
            PullForegroundDrawable.m12271$$Nest$fputtextInProgress(this.this$0, 0.0f);
            PullForegroundDrawable.m12272$$Nest$fputtextIntAnimator(this.this$0, ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}));
            PullForegroundDrawable.m12269$$Nest$fgettextIntAnimator(this.this$0).addUpdateListener(PullForegroundDrawable.m12268$$Nest$fgettextInUpdateListener(this.this$0));
            PullForegroundDrawable.m12269$$Nest$fgettextIntAnimator(this.this$0).setInterpolator(new LinearInterpolator());
            PullForegroundDrawable.m12269$$Nest$fgettextIntAnimator(this.this$0).setDuration(150);
            PullForegroundDrawable.m12269$$Nest$fgettextIntAnimator(this.this$0).start();
        }
    }

    /* renamed from: org.telegram.ui.Components.PullForegroundDrawable$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ PullForegroundDrawable this$0;

        public AnonymousClass2(PullForegroundDrawable r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.doNotShow();
        }
    }

    public class ArrowDrawable extends Drawable {
        private float lastDensity;
        private Paint paint;
        private Path path;
        final /* synthetic */ PullForegroundDrawable this$0;

        public ArrowDrawable(PullForegroundDrawable r2) {
            this.this$0 = r2;
            this.path = new Path();
            this.paint = new Paint(1);
            updatePath();
        }

        private void updatePath() {
            int r0 = AndroidUtilities.dp(18.0f);
            this.path.reset();
            float r2 = r0 >> 1;
            this.path.moveTo(r2, AndroidUtilities.dpf2(4.98f));
            this.path.lineTo(AndroidUtilities.dpf2(4.95f), AndroidUtilities.dpf2(9.0f));
            this.path.lineTo(r0 - AndroidUtilities.dpf2(4.95f), AndroidUtilities.dpf2(9.0f));
            this.path.lineTo(r2, AndroidUtilities.dpf2(4.98f));
            this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
            this.paint.setStrokeJoin(Paint.Join.ROUND);
            this.paint.setStrokeWidth(AndroidUtilities.dpf2(1.0f));
            this.lastDensity = AndroidUtilities.density;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r9) {
            if (this.lastDensity == AndroidUtilities.density) goto L5;
            updatePath();
        L5:
            r9.save();
            r9.translate(getBounds().left, getBounds().top);
            r9.drawPath(this.path, this.paint);
            r9.drawRect(AndroidUtilities.dpf2(7.56f), AndroidUtilities.dpf2(8.0f), AndroidUtilities.dp(18.0f) - AndroidUtilities.dpf2(7.56f), AndroidUtilities.dpf2(11.1f), this.paint);
            r9.restore();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(18.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return getIntrinsicHeight();
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        public void setColor(int r1) {
            this.paint.setColor(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* renamed from: $r8$lambda$-clMQNzwAbbb7Jxcw-19TqK75MQ, reason: not valid java name */
    public static /* synthetic */ void m12266$r8$lambda$clMQNzwAbbb7Jxcw19TqK75MQ(PullForegroundDrawable r0, ValueAnimator r1) {
        r0.lambda$updateTextProgress$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$BRwy1CkV_JXVwiqT7nWk1kf0cGQ(PullForegroundDrawable r0, ValueAnimator r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$NOJzuoomPzLMj8s2jImCgWvczJA(PullForegroundDrawable r0, ValueAnimator r1) {
        r0.lambda$colorize$3(r1);
    }

    /* renamed from: $r8$lambda$R-ajqvyg5uPVe_bgV0zncn5nd0Q, reason: not valid java name */
    public static /* synthetic */ void m12267$r8$lambda$Rajqvyg5uPVe_bgV0zncn5nd0Q(PullForegroundDrawable r0, ValueAnimator r1) {
        r0.lambda$startOutAnimation$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$a2Ygjp0IzH3VG1E_BmIhdqVWUao(PullForegroundDrawable r0, ValueAnimator r1) {
        r0.lambda$startOutAnimation$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$bfspXXOFLaa4D3JaCv0yYsE3eu8(PullForegroundDrawable r0, ValueAnimator r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$pcHdgNzhO0Fj4NUPtBahPKAH5cQ(PullForegroundDrawable r0, ValueAnimator r1) {
        r0.lambda$startOutAnimation$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$u21EDS8bgGEDCf07cC0w6fZWp00(PullForegroundDrawable r0, ValueAnimator r1) {
        r0.lambda$colorize$4(r1);
    }

    /* renamed from: -$$Nest$fgettextInUpdateListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator.AnimatorUpdateListener m12268$$Nest$fgettextInUpdateListener(PullForegroundDrawable r0) {
        return r0.textInUpdateListener;
    }

    /* renamed from: -$$Nest$fgettextIntAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m12269$$Nest$fgettextIntAnimator(PullForegroundDrawable r0) {
        return r0.textIntAnimator;
    }

    /* renamed from: -$$Nest$fputanimateToTextIn, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12270$$Nest$fputanimateToTextIn(PullForegroundDrawable r0, boolean r1) {
        r0.animateToTextIn = r1;
    }

    /* renamed from: -$$Nest$fputtextInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12271$$Nest$fputtextInProgress(PullForegroundDrawable r0, float r1) {
        r0.textInProgress = r1;
    }

    /* renamed from: -$$Nest$fputtextIntAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12272$$Nest$fputtextIntAnimator(PullForegroundDrawable r0, ValueAnimator r1) {
        r0.textIntAnimator = r1;
    }

    public PullForegroundDrawable(CharSequence r3, CharSequence r4) {
        this.backgroundColorKey = Theme.key_chats_archivePullDownBackground;
        this.backgroundActiveColorKey = Theme.key_chats_archivePullDownBackgroundActive;
        this.avatarBackgroundColorKey = Theme.key_avatar_backgroundArchivedHidden;
        this.changeAvatarColor = true;
        this.paintSecondary = new Paint(1);
        this.paintWhite = new Paint(1);
        this.paintBackgroundAccent = new Paint(1);
        this.backgroundPaint = new Paint();
        this.rectF = new RectF();
        TextPaint r1 = new TextPaint(1);
        this.tooltipTextPaint = r1;
        this.arrowDrawable = new ArrowDrawable(this);
        this.circleClipPath = new Path();
        this.textSwappingProgress = 1.0f;
        this.arrowRotateProgress = 1.0f;
        this.accentRevalProgress = 1.0f;
        this.accentRevalProgressOut = 1.0f;
        this.pullTooltipLayoutScale = 1.0f;
        this.releaseTooltipLayoutScale = 1.0f;
        this.textSwappingUpdateListener = new PullForegroundDrawable$$ExternalSyntheticLambda1(this);
        this.textInUpdateListener = new PullForegroundDrawable$$ExternalSyntheticLambda2(this);
        this.textInRunnable = new AnonymousClass1(this);
        this.wasSendCallback = false;
        r1.setTypeface(AndroidUtilities.bold());
        r1.setTextSize(AndroidUtilities.dp(16.0f));
        this.touchSlop = ViewConfiguration.get(ApplicationLoader.applicationContext).getScaledTouchSlop();
        this.pullTooltipText = r3;
        this.releaseTooltipText = r4;
        this.generalTopicDrawable = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.msg_filled_general).mutate();     // Catch: Exception -> L5
        return;
    }

    private void checkTextLayouts(int r18) {
        if (r18 == this.lastWidth) goto L32;
        this.pullTooltipLayout = new StaticLayout(this.pullTooltipText, this.tooltipTextPaint, AndroidUtilities.displaySize.x, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        int r2 = 0;
        int r4 = 0;
        float r5 = 0.0f;
    L6:
        if (r4 >= this.pullTooltipLayout.getLineCount()) goto L8;
        r5 = Math.max(r5, this.pullTooltipLayout.getLineWidth(r4));
        r4 = r4 + 1;
        goto L6
    L8:
        float r42 = r18;
        this.pullTooltipLayoutScale = Math.min(1.0f, r42 / r5);
        int r52 = (int) Math.ceil(r5);
        if (this.pullTooltipLayoutScale >= 0.8f) goto L11;
        this.pullTooltipLayoutScale = 0.8f;
        r52 = HintView2.cutInFancyHalf(this.pullTooltipText, this.tooltipTextPaint);
    L11:
        int r12 = r52;
        this.pullTooltipLayout = new StaticLayout(this.pullTooltipText, this.tooltipTextPaint, r12, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        this.pullTooltipLayoutLeft = r12;
        this.pullTooltipLayoutWidth = 0.0f;
        int r53 = 0;
    L13:
        if (r53 >= this.pullTooltipLayout.getLineCount()) goto L15;
        this.pullTooltipLayoutLeft = Math.min(this.pullTooltipLayoutLeft, this.pullTooltipLayout.getLineLeft(r53));
        this.pullTooltipLayoutWidth = Math.max(this.pullTooltipLayoutWidth, this.pullTooltipLayout.getLineWidth(r53));
        r53 = r53 + 1;
        goto L13
    L15:
        this.releaseTooltipLayout = new StaticLayout(this.releaseTooltipText, this.tooltipTextPaint, AndroidUtilities.displaySize.x, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        int r54 = 0;
        float r6 = 0.0f;
    L17:
        if (r54 >= this.releaseTooltipLayout.getLineCount()) goto L19;
        r6 = Math.max(r6, this.releaseTooltipLayout.getLineWidth(r54));
        r54 = r54 + 1;
        goto L17
    L19:
        this.releaseTooltipLayoutScale = Math.min(1.0f, r42 / r6);
        int r43 = (int) Math.ceil(r6);
        if (this.releaseTooltipLayoutScale >= 0.8f) goto L22;
        this.releaseTooltipLayoutScale = 0.8f;
        r43 = HintView2.cutInFancyHalf(this.releaseTooltipText, this.tooltipTextPaint);
    L22:
        int r8 = r43;
        this.releaseTooltipLayout = new StaticLayout(this.releaseTooltipText, this.tooltipTextPaint, r8, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        this.releaseTooltipLayoutLeft = r8;
        this.releaseTooltipLayoutWidth = 0.0f;
    L24:
        if (r2 >= this.releaseTooltipLayout.getLineCount()) goto L26;
        this.releaseTooltipLayoutLeft = Math.min(this.releaseTooltipLayoutLeft, this.releaseTooltipLayout.getLineLeft(r2));
        this.releaseTooltipLayoutWidth = Math.max(this.releaseTooltipLayoutWidth, this.releaseTooltipLayout.getLineWidth(r2));
        r2 = r2 + 1;
        goto L24
    L26:
        this.lastWidth = r18;
        return;
    }

    public static int getMaxOverscroll() {
        return AndroidUtilities.dp(72.0f);
    }

    private /* synthetic */ void lambda$colorize$3(ValueAnimator r1) {
        this.accentRevalProgress = ((Float) r1.getAnimatedValue()).floatValue();
        View r12 = this.cell;
        if (r12 == null) goto L5;
        r12.invalidate();
    L5:
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L9;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$colorize$4(ValueAnimator r1) {
        this.accentRevalProgressOut = ((Float) r1.getAnimatedValue()).floatValue();
        View r12 = this.cell;
        if (r12 == null) goto L5;
        r12.invalidate();
    L5:
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L9;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$new$0(ValueAnimator r1) {
        this.textSwappingProgress = ((Float) r1.getAnimatedValue()).floatValue();
        View r0 = this.cell;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$new$1(ValueAnimator r1) {
        this.textInProgress = ((Float) r1.getAnimatedValue()).floatValue();
        View r0 = this.cell;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$startOutAnimation$5(ValueAnimator r1) {
        setOutProgress(((Float) r1.getAnimatedValue()).floatValue());
        View r0 = this.cell;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$startOutAnimation$6(ValueAnimator r1) {
        this.bounceProgress = ((Float) r1.getAnimatedValue()).floatValue();
        this.bounceIn = true;
        View r0 = this.cell;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$startOutAnimation$7(ValueAnimator r1) {
        this.bounceProgress = ((Float) r1.getAnimatedValue()).floatValue();
        this.bounceIn = false;
        View r0 = this.cell;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$updateTextProgress$2(ValueAnimator r1) {
        this.arrowRotateProgress = ((Float) r1.getAnimatedValue()).floatValue();
        View r0 = this.cell;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private void setOutProgress(float r4) {
        this.outProgress = r4;
        int r42 = ColorUtils.blendARGB(Theme.getNonAnimatedColor(this.avatarBackgroundColorKey), Theme.getNonAnimatedColor(this.backgroundActiveColorKey), 1.0f - this.outProgress);
        this.paintBackgroundAccent.setColor(r42);
        if (this.changeAvatarColor == true) goto L5;
        return;
    L5:
        if (isDraw() == false) goto L9;
        Theme.dialogs_archiveAvatarDrawable.beginApplyLayerColors();
        Theme.dialogs_archiveAvatarDrawable.setLayerColor("Arrow1", r42);
        Theme.dialogs_archiveAvatarDrawable.setLayerColor("Arrow2", r42);
        Theme.dialogs_archiveAvatarDrawable.commitApplyLayerColors();
        Theme.dialogs_archiveAvatarDrawableRecolored = true;
        return;
    }

    private void textIn() {
        if (this.animateToTextIn == false) goto L5;
        return;
    L5:
        if (Math.abs(this.scrollDy) < (this.touchSlop * 0.5f)) goto L7;
        this.wasSendCallback = true;
        this.cell.removeCallbacks(this.textInRunnable);
        this.cell.postDelayed(this.textInRunnable, 200);
        return;
    L7:
        if (this.wasSendCallback == true) goto L13;
        this.textInProgress = 1.0f;
        this.animateToTextIn = true;
        return;
    }

    private void updateTextProgress(float r9) {
        if (r9 <= 0.85f) goto L5;
        boolean r92 = true;
    L6:
        float r4 = 1.0f;
        if (this.animateToEndText == r92) goto L24;
        this.animateToEndText = r92;
        float r2 = this.textInProgress;
        ValueAnimator r6 = this.textSwipingAnimator;
        if (r2 != 0.0f) goto L16;
        if (r6 == null) goto L12;
        r6.cancel();
    L12:
        if (r92 == false) goto L14;
        float r22 = 0.0f;
    L15:
        this.textSwappingProgress = r22;
        goto L24
    L14:
        r22 = 1.0f;
        goto L15
    L16:
        if (r6 == null) goto L18;
        r6.cancel();
    L18:
        float r23 = this.textSwappingProgress;
        if (r92 == false) goto L21;
        float r62 = 0.0f;
    L22:
        ValueAnimator r24 = ValueAnimator.ofFloat(new float[]{r23, r62});
        this.textSwipingAnimator = r24;
        r24.addUpdateListener(this.textSwappingUpdateListener);
        this.textSwipingAnimator.setInterpolator(new LinearInterpolator());
        this.textSwipingAnimator.setDuration(170);
        this.textSwipingAnimator.start();
        goto L24
    L21:
        r62 = 1.0f;
    L24:
        if (r92 == this.arrowAnimateTo) goto L33;
        this.arrowAnimateTo = r92;
        ValueAnimator r93 = this.arrowRotateAnimator;
        if (r93 == null) goto L28;
        r93.cancel();
    L28:
        float r94 = this.arrowRotateProgress;
        if (this.arrowAnimateTo == false) goto L31;
        r4 = 0.0f;
    L31:
        ValueAnimator r95 = ValueAnimator.ofFloat(new float[]{r94, r4});
        this.arrowRotateAnimator = r95;
        r95.addUpdateListener(new PullForegroundDrawable$$ExternalSyntheticLambda0(this));
        this.arrowRotateAnimator.setInterpolator(CubicBezierInterpolator.EASE_BOTH);
        this.arrowRotateAnimator.setDuration(250);
        this.arrowRotateAnimator.start();
        return;
    L33:
        return;
    L5:
        r92 = false;
        goto L6
    }

    public void colorize(boolean r6) {
        if (this.animateToColorize == r6) goto L16;
        this.animateToColorize = r6;
        if (r6 == false) goto L11;
        ValueAnimator r62 = this.accentRevalAnimatorIn;
        if (r62 == null) goto L9;
        r62.cancel();
        this.accentRevalAnimatorIn = null;
    L9:
        this.accentRevalProgress = 0.0f;
        ValueAnimator r63 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.accentRevalAnimatorIn = r63;
        r63.addUpdateListener(new PullForegroundDrawable$$ExternalSyntheticLambda6(this));
        this.accentRevalAnimatorIn.setInterpolator(AndroidUtilities.accelerateInterpolator);
        this.accentRevalAnimatorIn.setDuration(230);
        this.accentRevalAnimatorIn.start();
        return;
    L11:
        ValueAnimator r64 = this.accentRevalAnimatorOut;
        if (r64 == null) goto L14;
        r64.cancel();
        this.accentRevalAnimatorOut = null;
    L14:
        this.accentRevalProgressOut = 0.0f;
        ValueAnimator r65 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.accentRevalAnimatorOut = r65;
        r65.addUpdateListener(new PullForegroundDrawable$$ExternalSyntheticLambda7(this));
        this.accentRevalAnimatorOut.setInterpolator(AndroidUtilities.accelerateInterpolator);
        this.accentRevalAnimatorOut.setDuration(230);
        this.accentRevalAnimatorOut.start();
        return;
    }

    public void doNotShow() {
        ValueAnimator r0 = this.textSwipingAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        ValueAnimator r02 = this.textIntAnimator;
        if (r02 == null) goto L8;
        r02.cancel();
    L8:
        View r03 = this.cell;
        if (r03 == null) goto L11;
        r03.removeCallbacks(this.textInRunnable);
    L11:
        ValueAnimator r04 = this.accentRevalAnimatorIn;
        if (r04 == null) goto L14;
        r04.cancel();
    L14:
        this.textSwappingProgress = 1.0f;
        this.arrowRotateProgress = 1.0f;
        this.animateToEndText = false;
        this.arrowAnimateTo = false;
        this.animateToTextIn = false;
        this.wasSendCallback = false;
        this.textInProgress = 0.0f;
        this.isOut = true;
        setOutProgress(1.0f);
        this.animateToColorize = false;
        this.accentRevalProgress = 0.0f;
    }

    public void draw(Canvas r2) {
        draw(r2, false);
    }

    /* JADX WARN: Type inference failed for: r14v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v6 */
    public void draw(Canvas r35, boolean r36) {
        Canvas r1 = r35;
        if (this.willDraw == true) goto L5;
        return;
    L5:
        if (this.isOut == true) goto L137;
        View r2 = this.cell;
        if (r2 != null) goto L9;
        return;
    L9:
        if (this.listView == null) goto L142;
        boolean r9 = r2 instanceof TopicsFragment.TopicDialogCell;
        if (r9 == false) goto L14;
        float r22 = 15.0f;
    L15:
        int r10 = AndroidUtilities.dp(r22);
        int r12 = AndroidUtilities.dp(8.0f);
        int r13 = AndroidUtilities.dp(9.0f);
        int r14 = AndroidUtilities.dp(18.0f);
        int r15 = (int) getViewOffset();
        int r23 = (int) (this.cell.getHeight() * getPullProgress());
        boolean r3 = this.bounceIn;
        float r4 = this.bounceProgress;
        if (r3 == false) goto L19;
        float r42 = (r4 * 0.07f) - 0.05f;
    L18:
        float r16 = r42;
        checkTextLayouts((this.cell.getWidth() - (r10 * 4)) - AndroidUtilities.dp(16.0f));
        updateTextProgress(getPullProgress());
        float r32 = this.outProgress * 2.0f;
        if (r32 <= 1.0f) goto L23;
        float r20 = 1.0f;
    L24:
        float r33 = this.outCx;
        float r43 = this.outCy;
        if (r36 == false) goto L27;
        r43 = r43 + r15;
    L27:
        int r5 = r10 + r13;
        int r6 = (this.cell.getMeasuredHeight() - r12) - r13;
        if (r36 == false) goto L30;
        r6 = r6 + r15;
    L30:
        int r7 = (r12 * 2) + r14;
        if (r23 <= r7) goto L33;
        float r21 = 8.0f;
        float r11 = 1.0f;
    L34:
        r1.save();
        if (r36 == false) goto L37;
        int r232 = r23;
        float r24 = r33;
        r1.clipRect(0, 0, this.listView.getMeasuredWidth(), r15 + 1);
    L39:
        if (this.outProgress == 0.0f) goto L41;
        float r25 = this.outRadius;
        float r27 = (r25 + (r25 * r16)) + ((this.cell.getWidth() - this.outRadius) * (1.0f - this.outProgress));
        if (this.accentRevalProgress != 1.0f) goto L49;
    L51:
        float r30 = r43;
        boolean r31 = r9;
        float r222 = r11;
        int r26 = r15;
        int r322 = r232;
        float r28 = r24;
        float r252 = 0.0f;
        int r152 = r5;
        int r92 = r6;
        int r112 = r7;
        int r242 = r14;
        boolean r142 = true;
    L52:
        this.circleClipPath.reset();
        this.rectF.set(r28 - r27, r30 - r27, r28 + r27, r30 + r27);
        float r44 = r27 * 2.0f;
        this.circleClipPath.addRoundRect(this.rectF, ExteraConfig.getAvatarCorners(r44, r142, false), ExteraConfig.getAvatarCorners(r44, r142, false), Path.Direction.CW);
        r1.clipPath(this.circleClipPath);
        ?? r143 = r142;
    L54:
        if (this.animateToColorize == false) goto L63;
        if (this.accentRevalProgressOut <= this.accentRevalProgress) goto L59;
        r1.save();
        float r29 = r152;
        float r45 = this.outProgress;
        float r52 = r92;
        r1.translate((r28 - r29) * r45, (r30 - r52) * r45);
        r1.drawRoundRect(r29 - (this.cell.getWidth() * this.accentRevalProgressOut), r52 - (this.cell.getWidth() * this.accentRevalProgressOut), r29 + (this.cell.getWidth() * this.accentRevalProgressOut), r52 + (this.cell.getWidth() * this.accentRevalProgressOut), ExteraConfig.getAvatarCorners(this.cell.getWidth() * 2, r143, false) * this.accentRevalProgressOut, ExteraConfig.getAvatarCorners(this.cell.getWidth() * 2, r143, false) * this.accentRevalProgressOut, this.backgroundPaint);
        r1.restore();
    L59:
        if (this.accentRevalProgress <= r252) goto L61;
        r1.save();
        float r210 = r152;
        float r46 = this.outProgress;
        float r53 = r92;
        r1.translate((r28 - r210) * r46, (r30 - r53) * r46);
        r1.drawRoundRect(r210 - (this.cell.getWidth() * this.accentRevalProgress), r53 - (this.cell.getWidth() * this.accentRevalProgress), r210 + (this.cell.getWidth() * this.accentRevalProgress), r53 + (this.cell.getWidth() * this.accentRevalProgress), ExteraConfig.getAvatarCorners(this.cell.getWidth() * 2, r143, false) * this.accentRevalProgress, ExteraConfig.getAvatarCorners(this.cell.getWidth() * 2, r143, false) * this.accentRevalProgress, this.paintBackgroundAccent);
        r1.restore();
    L61:
        int r233 = 0;
    L69:
        if (r322 <= r112) goto L75;
        this.paintSecondary.setAlpha((int) ((((1.0f - r20) * 0.4f) * r222) * 255.0f));
        RectF r34 = this.rectF;
        if (r36 == false) goto L73;
        r34.set(r10, r12, r10 + r242, (r12 + r26) + r13);
    L74:
        float r37 = r13;
        r1.drawRoundRect(this.rectF, r37, r37, this.paintSecondary);
        goto L75
    L73:
        r34.set(r10, ((this.cell.getHeight() - r2) + r12) - r26, r10 + r242, this.cell.getHeight() - r12);
    L75:
        if (r36 == false) goto L78;
        r1.restore();
        return;
    L78:
        if (r31 == false) goto L80;
        int r62 = (int) (r92 - ((this.cell.getMeasuredHeight() - AndroidUtilities.dp(41.0f)) * this.outProgress));
    L81:
        float r211 = this.outProgress;
        if (r211 == r252) goto L86;
        if (r31 == true) goto L86;
        float r202 = 255.0f;
    L95:
        if (getPullProgress() <= 0.0f) goto L97;
        textIn();
    L97:
        float r8 = (this.cell.getHeight() - (r112 / 2.0f)) + AndroidUtilities.dp(6.0f);
        int r212 = this.cell.getWidth();
        if (r31 == false) goto L100;
        int r38 = r10 * 2;
    L101:
        float r93 = (r212 + r38) / 2.0f;
        if (this.pullTooltipLayout == null) goto L114;
        float r213 = this.textSwappingProgress;
        if (r213 > 0.0f) goto L106;
    L108:
        r1.saveLayerAlpha(0.0f, 0.0f, this.cell.getMeasuredWidth(), this.cell.getMeasuredHeight(), (int) (((this.textSwappingProgress * r202) * r222) * this.textInProgress), 31);
        r1.translate((r93 - this.pullTooltipLayoutLeft) - (this.pullTooltipLayoutWidth / 2.0f), ((AndroidUtilities.dp(r21) * (1.0f - this.textSwappingProgress)) + r8) - this.pullTooltipLayout.getHeight());
        float r214 = this.pullTooltipLayoutScale;
        r1.scale(r214, r214, this.pullTooltipLayoutLeft + (this.pullTooltipLayoutWidth / 2.0f), this.pullTooltipLayout.getHeight());
        this.pullTooltipLayout.draw(r1);
        r1.restore();
        float r215 = this.textSwappingProgress;
        if (r215 <= 0.0f) goto L114;
        if (r215 >= 1.0f) goto L114;
        r1.restore();
        goto L114
    L106:
        if (r213 >= 1.0f) goto L108;
        r1.save();
        float r39 = (this.textSwappingProgress * 0.2f) + 0.8f;
        r1.scale(r39, r39, r93, (AndroidUtilities.dp(16.0f) * (1.0f - this.textSwappingProgress)) + r8);
    L114:
        if (this.releaseTooltipLayout == null) goto L125;
        float r216 = this.textSwappingProgress;
        if (r216 > 0.0f) goto L118;
    L120:
        r1.saveLayerAlpha(0.0f, 0.0f, this.cell.getMeasuredWidth(), this.cell.getMeasuredHeight(), (int) ((((1.0f - this.textSwappingProgress) * r202) * r222) * this.textInProgress), 31);
        r1.translate((r93 - this.releaseTooltipLayoutLeft) - (this.releaseTooltipLayoutWidth / 2.0f), (r8 + (AndroidUtilities.dp(r21) * this.textSwappingProgress)) - this.releaseTooltipLayout.getHeight());
        float r217 = this.releaseTooltipLayoutScale;
        r1.scale(r217, r217, this.releaseTooltipLayoutLeft + (this.releaseTooltipLayoutWidth / 2.0f), this.releaseTooltipLayout.getHeight());
        this.releaseTooltipLayout.draw(r1);
        r1.restore();
        float r218 = this.textSwappingProgress;
        if (r218 <= 0.0f) goto L125;
        if (r218 >= 1.0f) goto L125;
        r1.restore();
        goto L125
    L118:
        if (r216 >= 1.0f) goto L120;
        r1.save();
        float r219 = ((1.0f - this.textSwappingProgress) * 0.1f) + 0.9f;
        r1.scale(r219, r219, r93, r8 - (AndroidUtilities.dp(r21) * this.textSwappingProgress));
    L125:
        r1.restore();
        if (r31 == false) goto L128;
        return;
    L128:
        if (this.changeAvatarColor == true) goto L130;
        return;
    L130:
        if (this.outProgress <= 0.0f) goto L141;
        r1.save();
        int r220 = Theme.dialogs_archiveAvatarDrawable.getIntrinsicWidth();
        int r310 = (this.cell.getHeight() - r12) - r13;
        float r221 = r220;
        float r47 = AndroidUtilities.dp(24.0f) / r221;
        float r63 = this.outProgress;
        float r48 = (r47 + ((1.0f - r47) * r63)) + r16;
        r1.translate((r152 - r28) * (1.0f - r63), (r310 - r30) * (1.0f - r63));
        float r311 = r28;
        float r82 = r30;
        r1.scale(r48, r48, r311, r82);
        Theme.dialogs_archiveAvatarDrawable.setProgress(0.0f);
        if (Theme.dialogs_archiveAvatarDrawableRecolored == true) goto L134;
        Theme.dialogs_archiveAvatarDrawable.beginApplyLayerColors();
        Theme.dialogs_archiveAvatarDrawable.setLayerColor("Arrow1", Theme.getNonAnimatedColor(this.avatarBackgroundColorKey));
        Theme.dialogs_archiveAvatarDrawable.setLayerColor("Arrow2", Theme.getNonAnimatedColor(this.avatarBackgroundColorKey));
        Theme.dialogs_archiveAvatarDrawable.commitApplyLayerColors();
        Theme.dialogs_archiveAvatarDrawableRecolored = r143;
    L134:
        float r223 = r221 / 2.0f;
        Theme.dialogs_archiveAvatarDrawable.setBounds((int) (r311 - r223), (int) (r82 - r223), (int) (r311 + r223), (int) (r223 + r82));
        Theme.dialogs_archiveAvatarDrawable.draw(r1);
        r1.restore();
        return;
    L141:
        return;
    L100:
        r38 = r233;
    L86:
        this.paintWhite.setAlpha((int) ((r222 * 255.0f) * (1.0f - r211)));
        float r224 = r152;
        float r312 = r62;
        r1.drawCircle(r224, r312, r13, this.paintWhite);
        int r49 = this.arrowDrawable.getIntrinsicHeight();
        int r54 = this.arrowDrawable.getIntrinsicWidth() >> r143;
        int r410 = r49 >> r143;
        r202 = 255.0f;
        this.arrowDrawable.setBounds(r152 - r54, r62 - r410, r54 + r152, r62 + r410);
        float r411 = 1.0f - this.arrowRotateProgress;
        if (r411 >= r252) goto L89;
        r411 = r252;
    L89:
        float r412 = 1.0f - r411;
        r1.save();
        r1.rotate(180.0f * r412, r224, r312);
        r1.translate(r252, (AndroidUtilities.dpf2(1.0f) * 1.0f) - r412);
        ArrowDrawable r225 = this.arrowDrawable;
        if (this.animateToColorize == false) goto L92;
        int r313 = this.paintBackgroundAccent.getColor();
    L93:
        r225.setColor(r313);
        this.arrowDrawable.setAlpha((int) ((1.0f - this.outProgress) * 255.0f));
        this.arrowDrawable.draw(r1);
        r1.restore();
        goto L95
    L92:
        r313 = Theme.getColor(this.backgroundColorKey);
        goto L93
    L80:
        r62 = r92;
        goto L81
    L63:
        if (this.accentRevalProgress <= this.accentRevalProgressOut) goto L66;
        r1.save();
        float r226 = r152;
        float r413 = this.outProgress;
        float r55 = r92;
        r1.translate((r28 - r226) * r413, (r30 - r55) * r413);
        r1.drawRoundRect(r226 - (this.cell.getWidth() * this.accentRevalProgress), r55 - (this.cell.getWidth() * this.accentRevalProgress), r226 + (this.cell.getWidth() * this.accentRevalProgress), r55 + (this.cell.getWidth() * this.accentRevalProgress), ExteraConfig.getAvatarCorners(this.cell.getWidth() * 2, r143, false) * this.accentRevalProgress, ExteraConfig.getAvatarCorners(this.cell.getWidth() * 2, r143, false) * this.accentRevalProgress, this.paintBackgroundAccent);
        r1.restore();
    L66:
        if (this.accentRevalProgressOut <= r252) goto L61;
        r1.save();
        float r227 = r152;
        float r414 = this.outProgress;
        float r56 = r92;
        r1.translate((r28 - r227) * r414, (r30 - r56) * r414);
        r233 = 0;
        r1.drawRoundRect(r227 - (this.cell.getWidth() * this.accentRevalProgressOut), r56 - (this.cell.getWidth() * this.accentRevalProgressOut), r227 + (this.cell.getWidth() * this.accentRevalProgressOut), r56 + (this.cell.getWidth() * this.accentRevalProgressOut), ExteraConfig.getAvatarCorners(this.cell.getWidth() * 2, r143, false) * this.accentRevalProgressOut, this.accentRevalProgressOut * ExteraConfig.getAvatarCorners(this.cell.getWidth() * 2, r143, false), this.backgroundPaint);
        r1.restore();
        goto L69
    L49:
        if (this.accentRevalProgressOut == 1.0f) goto L51;
        float r83 = r43;
        float r17 = r27 * 2.0f;
        r30 = r83;
        r31 = r9;
        r322 = r232;
        r252 = 0.0f;
        r92 = r6;
        r26 = r15;
        r152 = r5;
        r28 = r24;
        r242 = r14;
        r142 = true;
        r222 = r11;
        r112 = r7;
        r1 = r35;
        r1.drawRoundRect(r24 - r27, r43 - r27, r24 + r27, r83 + r27, ExteraConfig.getAvatarCorners(r17, true, false), ExteraConfig.getAvatarCorners(r17, true, false), this.backgroundPaint);
        goto L52
    L41:
        if (this.accentRevalProgress != 1.0f) goto L43;
    L45:
        r252 = 0.0f;
        r30 = r43;
        r31 = r9;
        r222 = r11;
        r26 = r15;
        r322 = r232;
        r28 = r24;
        r152 = r5;
        r92 = r6;
        r112 = r7;
        r242 = r14;
        r143 = 1;
        goto L54
    L43:
        if (this.accentRevalProgressOut == 1.0f) goto L45;
        r1.drawPaint(this.backgroundPaint);
        goto L45
    L37:
        r232 = r23;
        r24 = r33;
        goto L39
    L33:
        r21 = 8.0f;
        r11 = r23 / r7;
        goto L34
    L23:
        r20 = r32;
        goto L24
    L19:
        r42 = r4 * 0.02f;
        goto L18
    L14:
        r22 = 28.0f;
        goto L15
    L142:
        return;
    }

    public void drawOverScroll(Canvas r2) {
        draw(r2, true);
    }

    public float getPullProgress() {
        return this.pullProgress;
    }

    public abstract float getViewOffset();

    public boolean isDraw() {
        if (this.willDraw == true) goto L5;
        return false;
    L5:
        if (this.isOut == true) goto L10;
        return true;
    L10:
        return false;
    }

    public void resetText() {
        ValueAnimator r0 = this.textIntAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        View r02 = this.cell;
        if (r02 == null) goto L8;
        r02.removeCallbacks(this.textInRunnable);
    L8:
        this.textInProgress = 0.0f;
        this.animateToTextIn = false;
        this.wasSendCallback = false;
    }

    public void setCell(View r1) {
        this.cell = r1;
        updateColors();
    }

    public void setListView(RecyclerListView r1) {
        this.listView = r1;
    }

    public void setPullProgress(float r2) {
        if (this.pullProgress == r2) goto L8;
        this.pullProgress = r2;
        View r1 = this.cell;
        if (r1 == null) goto L9;
        r1.invalidate();
        return;
    L9:
        return;
    }

    public void setWillDraw(boolean r1) {
        this.willDraw = r1;
    }

    public void showHidden() {
        AnimatorSet r0 = this.outAnimator;
        if (r0 == null) goto L5;
        r0.removeAllListeners();
        this.outAnimator.cancel();
    L5:
        setOutProgress(0.0f);
        this.isOut = false;
        this.animateOut = false;
    }

    public void startOutAnimation() {
        if (this.animateOut == false) goto L5;
        return;
    L5:
        if (this.listView == null) goto L13;
        AnimatorSet r0 = this.outAnimator;
        if (r0 == null) goto L10;
        r0.removeAllListeners();
        this.outAnimator.cancel();
    L10:
        this.animateOut = true;
        this.bounceIn = true;
        this.bounceProgress = 0.0f;
        this.outOverScroll = this.listView.getTranslationY() / AndroidUtilities.dp(100.0f);
        ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r2.addUpdateListener(new PullForegroundDrawable$$ExternalSyntheticLambda3(this));
        r2.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r2.setDuration(250);
        ValueAnimator r3 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r3.addUpdateListener(new PullForegroundDrawable$$ExternalSyntheticLambda4(this));
        CubicBezierInterpolator r4 = CubicBezierInterpolator.EASE_BOTH;
        r3.setInterpolator(r4);
        r3.setDuration(150);
        ValueAnimator r5 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        r5.addUpdateListener(new PullForegroundDrawable$$ExternalSyntheticLambda5(this));
        r5.setInterpolator(r4);
        r5.setDuration(135);
        AnimatorSet r42 = new AnimatorSet();
        this.outAnimator = r42;
        r42.addListener(new AnonymousClass2(this));
        AnimatorSet r43 = new AnimatorSet();
        r43.playSequentially(new Animator[]{r3, r5});
        r43.setStartDelay(180);
        this.outAnimator.playTogether(new Animator[]{r2, r43});
        this.outAnimator.start();
        return;
    }

    public void updateColors() {
        int r0 = Theme.getColor(this.backgroundColorKey);
        this.tooltipTextPaint.setColor(-1);
        this.paintWhite.setColor(-1);
        this.paintSecondary.setColor(ColorUtils.setAlphaComponent(-1, 100));
        this.backgroundPaint.setColor(r0);
        this.arrowDrawable.setColor(r0);
        this.paintBackgroundAccent.setColor(Theme.getColor(this.avatarBackgroundColorKey));
    }
}
