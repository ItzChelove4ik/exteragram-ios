package com.exteragram.messenger.preferences.chats.components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.preferences.components.CustomPreferenceCell;
import com.exteragram.messenger.preferences.components.PreviewColors;
import com.exteragram.messenger.utils.chats.DoubleTapUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes4.dex */
public class DoubleTapCell extends LinearLayout implements CustomPreferenceCell {
    private static final int[] ICON_WIDTH = null;
    private final int[] actionIcon;
    private final ValueAnimator[] animator;
    private final ValueAnimator[] circleAnimator;
    private final Paint[] circleOutlinePaint;
    private final float[] circleProgress;
    private final ValueAnimator[] circleSizeAnimator;
    private final float[] circleSizeProgress;
    private final float[] iconChangingProgress;
    private final MessageDrawable[] messages;
    private final Paint outlinePaint;
    private final FrameLayout preview;
    private final RectF rect;

    /* renamed from: com.exteragram.messenger.preferences.chats.components.DoubleTapCell$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ DoubleTapCell this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass1(DoubleTapCell r1, Context r2, Context r3) {
            this.this$0 = r1;
            this.val$context = r3;
            super(r2);
        }

        @Override // android.view.View
        @SuppressLint({"DrawAllocation"})
        public void onDraw(Canvas r18) {
            Rect r2 = new Rect();
            float r3 = DoubleTapCell.m1694$$Nest$fgetoutlinePaint(this.this$0).getStrokeWidth() / 2.0f;
            int r6 = 0;
        L4:
            if (r6 >= 2) goto L25;
            if (r6 != 0) goto L8;
            DoubleTapCell.m1695$$Nest$fgetrect(this.this$0).set(AndroidUtilities.dp(8.0f) + r3, AndroidUtilities.dp(10.0f) + r3, ((getMeasuredWidth() / 2.0f) - AndroidUtilities.dp(8.0f)) - r3, AndroidUtilities.dp(75.0f) - r3);
        L9:
            DoubleTapCell.m1695$$Nest$fgetrect(this.this$0).round(r2);
            DoubleTapCell.m1693$$Nest$fgetmessages(this.this$0)[r6].setBounds(r2);
            Theme.dialogs_onlineCirclePaint.setColor(PreviewColors.getBackgroundColor());
            DoubleTapCell.m1693$$Nest$fgetmessages(this.this$0)[r6].draw(r18, Theme.dialogs_onlineCirclePaint);
            DoubleTapCell.m1693$$Nest$fgetmessages(this.this$0)[r6].draw(r18, DoubleTapCell.m1694$$Nest$fgetoutlinePaint(this.this$0));
            int r8 = 0;
        L10:
            float r9 = 3.0f;
            int r10 = 3;
            if (r8 >= 2) goto L20;
            DoubleTapCell.m1689$$Nest$fgetcircleOutlinePaint(this.this$0)[r8] = new Paint(1);
            DoubleTapCell.m1689$$Nest$fgetcircleOutlinePaint(this.this$0)[r8].setStyle(Paint.Style.STROKE);
            int r16 = r6 + (r8 * 2);
            DoubleTapCell.m1689$$Nest$fgetcircleOutlinePaint(this.this$0)[r8].setColor(ColorUtils.blendARGB(0, PreviewColors.getMockColor(true), DoubleTapCell.m1690$$Nest$fgetcircleProgress(this.this$0)[r16]));
            DoubleTapCell.m1689$$Nest$fgetcircleOutlinePaint(this.this$0)[r8].setStrokeWidth((AndroidUtilities.dp(1.5f) * DoubleTapCell.m1690$$Nest$fgetcircleProgress(this.this$0)[r16]) * DoubleTapCell.m1690$$Nest$fgetcircleProgress(this.this$0)[r16]);
            if (r6 != 0) goto L15;
            r10 = 1;
        L15:
            float r102 = (r10 * getMeasuredWidth()) / 4.0f;
            float r12 = getMeasuredHeight() / 4.0f;
            if (r6 == 0) goto L19;
            r9 = -2.0f;
        L19:
            r18.drawCircle(r102, r12 + AndroidUtilities.dpf2(r9), AndroidUtilities.dp(25 - (r8 * 6)) * DoubleTapCell.m1691$$Nest$fgetcircleSizeProgress(this.this$0)[r16], DoubleTapCell.m1689$$Nest$fgetcircleOutlinePaint(this.this$0)[r8]);
            r8 = r8 + 1;
            goto L10
        L20:
            Drawable r7 = ContextCompat.getDrawable(this.val$context, DoubleTapCell.m1686$$Nest$fgetactionIcon(this.this$0)[r6]);
            if (r6 != 0) goto L23;
            r7.setBounds((getMeasuredWidth() / 4) - DoubleTapCell.m1696$$Nest$sfgetICON_WIDTH()[r6], (int) (((getMeasuredHeight() / 4) - DoubleTapCell.m1696$$Nest$sfgetICON_WIDTH()[r6]) + AndroidUtilities.dpf2(3.0f)), (getMeasuredWidth() / 4) + DoubleTapCell.m1696$$Nest$sfgetICON_WIDTH()[r6], (int) (((getMeasuredHeight() / 4) + DoubleTapCell.m1696$$Nest$sfgetICON_WIDTH()[r6]) + AndroidUtilities.dpf2(3.0f)));
        L24:
            r7.setBounds(r7.getBounds().left - AndroidUtilities.dp(4.0f - (DoubleTapCell.m1692$$Nest$fgeticonChangingProgress(this.this$0)[r6] * 4.0f)), r7.getBounds().top - AndroidUtilities.dp(4.0f - (DoubleTapCell.m1692$$Nest$fgeticonChangingProgress(this.this$0)[r6] * 4.0f)), r7.getBounds().right + AndroidUtilities.dp(4.0f - (DoubleTapCell.m1692$$Nest$fgeticonChangingProgress(this.this$0)[r6] * 4.0f)), r7.getBounds().bottom + AndroidUtilities.dp(4.0f - (DoubleTapCell.m1692$$Nest$fgeticonChangingProgress(this.this$0)[r6] * 4.0f)));
            r7.setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(0, Theme.getColor(Theme.key_chats_menuItemIcon), DoubleTapCell.m1692$$Nest$fgeticonChangingProgress(this.this$0)[r6]), PorterDuff.Mode.MULTIPLY));
            r7.draw(r18);
            r6 = r6 + 1;
            goto L4
        L23:
            r7.setBounds(((getMeasuredWidth() * 3) / 4) - DoubleTapCell.m1696$$Nest$sfgetICON_WIDTH()[r6], (int) (((getMeasuredHeight() / 4) - DoubleTapCell.m1696$$Nest$sfgetICON_WIDTH()[r6]) - AndroidUtilities.dpf2(2.0f)), ((getMeasuredWidth() * 3) / 4) + DoubleTapCell.m1696$$Nest$sfgetICON_WIDTH()[r6], (int) (((getMeasuredHeight() / 4) + DoubleTapCell.m1696$$Nest$sfgetICON_WIDTH()[r6]) - AndroidUtilities.dpf2(2.0f)));
            goto L24
        L8:
            r18.translate(0.0f, AndroidUtilities.dp(80.0f));
            DoubleTapCell.m1695$$Nest$fgetrect(this.this$0).set(((getMeasuredWidth() / 2.0f) + r3) + AndroidUtilities.dp(8.0f), AndroidUtilities.dp(5.0f) + r3, (getMeasuredWidth() - AndroidUtilities.dp(8.0f)) - r3, AndroidUtilities.dp(70.0f) - r3);
            goto L9
        }
    }

    /* renamed from: com.exteragram.messenger.preferences.chats.components.DoubleTapCell$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ DoubleTapCell this$0;
        final /* synthetic */ int val$finalJ;

        public AnonymousClass2(DoubleTapCell r1, int r2) {
            this.this$0 = r1;
            this.val$finalJ = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            super.onAnimationEnd(r3);
            DoubleTapCell.m1688$$Nest$fgetcircleAnimator(this.this$0)[this.val$finalJ].setFloatValues(new float[]{1.0f, 0.0f});
            DoubleTapCell.m1688$$Nest$fgetcircleAnimator(this.this$0)[this.val$finalJ].setDuration(700);
            DoubleTapCell.m1688$$Nest$fgetcircleAnimator(this.this$0)[this.val$finalJ].removeAllListeners();
            DoubleTapCell.m1688$$Nest$fgetcircleAnimator(this.this$0)[this.val$finalJ].start();
        }
    }

    /* renamed from: com.exteragram.messenger.preferences.chats.components.DoubleTapCell$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ DoubleTapCell this$0;
        final /* synthetic */ int val$finalI;

        /* renamed from: com.exteragram.messenger.preferences.chats.components.DoubleTapCell$3$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                super.onAnimationEnd(r2);
                this.this$1.this$0.performHapticFeedback(3, 2);
            }
        }

        public AnonymousClass3(DoubleTapCell r1, int r2) {
            this.this$0 = r1;
            this.val$finalI = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            super.onAnimationEnd(r5);
            int[] r52 = DoubleTapCell.m1686$$Nest$fgetactionIcon(this.this$0);
            int r0 = this.val$finalI;
            if (r0 != 0) goto L5;
            int r1 = ExteraConfig.getDoubleTapAction();
        L6:
            boolean r3 = true;
            if (this.val$finalI == 1) goto L10;
            r3 = false;
        L10:
            r52[r0] = DoubleTapUtils.getDoubleTapActionIcon(r1, r3);
            DoubleTapCell.m1687$$Nest$fgetanimator(this.this$0)[this.val$finalI].setFloatValues(new float[]{0.0f, 1.0f});
            DoubleTapCell.m1687$$Nest$fgetanimator(this.this$0)[this.val$finalI].removeAllListeners();
            DoubleTapCell.m1687$$Nest$fgetanimator(this.this$0)[this.val$finalI].addListener(new AnonymousClass1(this));
            DoubleTapCell.m1687$$Nest$fgetanimator(this.this$0)[this.val$finalI].start();
            return;
        L5:
            r1 = ExteraConfig.getDoubleTapActionOutOwner();
            goto L6
        }
    }

    /* renamed from: $r8$lambda$2P0xLMkAvokLxJ-FZqsk7B_gT1g, reason: not valid java name */
    public static /* synthetic */ void m1684$r8$lambda$2P0xLMkAvokLxJFZqsk7B_gT1g(DoubleTapCell r0, int r1, ValueAnimator r2) {
        r0.lambda$updateIcons$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$a0o09FFBmrEEmm2bwUyXlaXr8vU(DoubleTapCell r0, int r1, int r2, ValueAnimator r3) {
        r0.lambda$updateIcons$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$j-iI6StPq77gH7HAoun4fNQuY3w, reason: not valid java name */
    public static /* synthetic */ void m1685$r8$lambda$jiI6StPq77gH7HAoun4fNQuY3w(DoubleTapCell r0, int r1, int r2, ValueAnimator r3) {
        r0.lambda$updateIcons$1(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetactionIcon, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m1686$$Nest$fgetactionIcon(DoubleTapCell r0) {
        return r0.actionIcon;
    }

    /* renamed from: -$$Nest$fgetanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator[] m1687$$Nest$fgetanimator(DoubleTapCell r0) {
        return r0.animator;
    }

    /* renamed from: -$$Nest$fgetcircleAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator[] m1688$$Nest$fgetcircleAnimator(DoubleTapCell r0) {
        return r0.circleAnimator;
    }

    /* renamed from: -$$Nest$fgetcircleOutlinePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint[] m1689$$Nest$fgetcircleOutlinePaint(DoubleTapCell r0) {
        return r0.circleOutlinePaint;
    }

    /* renamed from: -$$Nest$fgetcircleProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m1690$$Nest$fgetcircleProgress(DoubleTapCell r0) {
        return r0.circleProgress;
    }

    /* renamed from: -$$Nest$fgetcircleSizeProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m1691$$Nest$fgetcircleSizeProgress(DoubleTapCell r0) {
        return r0.circleSizeProgress;
    }

    /* renamed from: -$$Nest$fgeticonChangingProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m1692$$Nest$fgeticonChangingProgress(DoubleTapCell r0) {
        return r0.iconChangingProgress;
    }

    /* renamed from: -$$Nest$fgetmessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable[] m1693$$Nest$fgetmessages(DoubleTapCell r0) {
        return r0.messages;
    }

    /* renamed from: -$$Nest$fgetoutlinePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m1694$$Nest$fgetoutlinePaint(DoubleTapCell r0) {
        return r0.outlinePaint;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m1695$$Nest$fgetrect(DoubleTapCell r0) {
        return r0.rect;
    }

    /* renamed from: -$$Nest$sfgetICON_WIDTH, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m1696$$Nest$sfgetICON_WIDTH() {
        return ICON_WIDTH;
    }

    static {
        ICON_WIDTH = new int[]{AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f)};
    }

    public DoubleTapCell(Context r7) {
        super(r7);
        this.rect = new RectF();
        Paint r0 = new Paint(1);
        this.outlinePaint = r0;
        this.circleOutlinePaint = new Paint[2];
        this.messages = new MessageDrawable[]{new MessageDrawable(0, false, false), new MessageDrawable(0, true, false)};
        this.animator = new ValueAnimator[2];
        this.circleAnimator = new ValueAnimator[2];
        this.circleSizeAnimator = new ValueAnimator[2];
        this.circleSizeProgress = new float[4];
        this.iconChangingProgress = new float[2];
        this.circleProgress = new float[4];
        this.actionIcon = new int[2];
        setWillNotDraw(false);
        setOrientation(1);
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        setPadding(AndroidUtilities.dp(13.0f), 0, AndroidUtilities.dp(13.0f), AndroidUtilities.dp(10.0f));
        r0.setStyle(Paint.Style.STROKE);
        r0.setStrokeWidth(AndroidUtilities.dp(1.0f) / 2.0f);
        r0.setColor(PreviewColors.getOutlineColor());
        AnonymousClass1 r02 = new AnonymousClass1(this, r7, r7);
        this.preview = r02;
        r02.setWillNotDraw(false);
        addView(r02, LayoutHelper.createFrame(-1, -1.0f));
        updateIcons(0, false);
    }

    private /* synthetic */ void lambda$updateIcons$0(int r2, int r3, ValueAnimator r4) {
        this.circleSizeProgress[(r2 * 2) + r3] = ((Float) r4.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$updateIcons$1(int r2, int r3, ValueAnimator r4) {
        this.circleProgress[(r2 * 2) + r3] = ((Float) r4.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$updateIcons$2(int r2, ValueAnimator r3) {
        this.iconChangingProgress[r2] = ((Float) r3.getAnimatedValue()).floatValue();
        invalidate();
    }

    @Override // com.exteragram.messenger.preferences.components.CustomPreferenceCell
    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof DoubleTapCell) == true) goto L8;
    L10:
        return false;
    L8:
        if (this.actionIcon != ((DoubleTapCell) r4).actionIcon) goto L10;
        return true;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        this.preview.invalidate();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas r8) {
        super.onDraw(r8);
        r8.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(170.0f), TLObject.FLAG_30));
    }

    @SuppressLint({"Recycle"})
    public void updateIcons(int r12, boolean r13) {
        int r1 = 0;
    L4:
        if (r1 >= 2) goto L25;
        if (r1 != 0) goto L7;
        if (r12 != 2) goto L7;
    L24:
        r1 = r1 + 1;
    L7:
        boolean r3 = true;
        if (r1 != 1) goto L11;
        if (r12 == 1) goto L24;
    L11:
        if (r13 == false) goto L16;
        int r32 = 0;
    L13:
        if (r32 >= 2) goto L15;
        this.circleSizeAnimator[r32] = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(1300);
        long r5 = r32;
        this.circleSizeAnimator[r32].setStartDelay(60 * r5);
        ValueAnimator r4 = this.circleSizeAnimator[r32];
        Interpolator r7 = Easings.easeInOutQuad;
        r4.setInterpolator(r7);
        this.circleSizeAnimator[r32].addUpdateListener(new DoubleTapCell$$ExternalSyntheticLambda0(this, r32, r1));
        this.circleAnimator[r32] = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(700);
        this.circleAnimator[r32].setStartDelay((r5 * 80) + 150);
        this.circleAnimator[r32].setInterpolator(r7);
        this.circleAnimator[r32].addUpdateListener(new DoubleTapCell$$ExternalSyntheticLambda1(this, r32, r1));
        this.circleAnimator[r32].addListener(new AnonymousClass2(this, r32));
        this.circleSizeAnimator[r32].start();
        this.circleAnimator[r32].start();
        r32 = r32 + 1;
        goto L13
    L15:
        this.animator[r1] = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f}).setDuration(250);
        this.animator[r1].setInterpolator(Easings.easeInOutQuad);
        this.animator[r1].addUpdateListener(new DoubleTapCell$$ExternalSyntheticLambda2(this, r1));
        this.animator[r1].addListener(new AnonymousClass3(this, r1));
        this.animator[r1].start();
        goto L24
    L16:
        this.circleSizeProgress[r1] = 0.0f;
        this.circleProgress[r1] = 0.0f;
        this.iconChangingProgress[r1] = 1.0f;
        int[] r2 = this.actionIcon;
        if (r1 != 0) goto L19;
        int r42 = ExteraConfig.getDoubleTapAction();
    L20:
        if (r1 == 1) goto L23;
        r3 = false;
    L23:
        r2[r1] = DoubleTapUtils.getDoubleTapActionIcon(r42, r3);
        invalidate();
        goto L24
    L19:
        r42 = ExteraConfig.getDoubleTapActionOutOwner();
        goto L20
    }
}
