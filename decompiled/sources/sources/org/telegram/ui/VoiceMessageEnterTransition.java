package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.view.animation.LinearInterpolator;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.MessageEnterTransitionContainer;

/* loaded from: classes6.dex */
public class VoiceMessageEnterTransition implements MessageEnterTransitionContainer.Transition {
    private final ValueAnimator animator;
    final Paint circlePaint;
    MessageEnterTransitionContainer container;
    float fromRadius;
    private final Matrix gradientMatrix;
    private final Paint gradientPaint;
    private final LinearGradient gradientShader;
    float lastToCx;
    float lastToCy;
    private final RecyclerListView listView;
    private final int messageId;
    private final ChatMessageCell messageView;
    float progress;
    private final ChatActivityEnterView.RecordCircle recordCircle;
    private final Theme.ResourcesProvider resourcesProvider;

    /* renamed from: org.telegram.ui.VoiceMessageEnterTransition$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ VoiceMessageEnterTransition this$0;
        final /* synthetic */ MessageEnterTransitionContainer val$container;
        final /* synthetic */ ChatMessageCell val$messageView;

        public AnonymousClass1(VoiceMessageEnterTransition r1, ChatMessageCell r2, MessageEnterTransitionContainer r3) {
            this.this$0 = r1;
            this.val$messageView = r2;
            this.val$container = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            this.val$messageView.setEnterTransitionInProgress(false);
            this.val$container.removeTransition(this.this$0);
            if (VoiceMessageEnterTransition.m21225$$Nest$fgetrecordCircle(this.this$0) == null) goto L6;
            VoiceMessageEnterTransition.m21225$$Nest$fgetrecordCircle(this.this$0).skipDraw = false;
            return;
        }
    }

    /* renamed from: $r8$lambda$XFyAH7wcva1-MSutTnwMc7seadM, reason: not valid java name */
    public static /* synthetic */ void m21224$r8$lambda$XFyAH7wcva1MSutTnwMc7seadM(VoiceMessageEnterTransition r0, Canvas r1, float r2, float r3, float r4, float r5, float r6, float r7, float r8, float r9) {
        r0.lambda$onDraw$1(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$raLLVGaOp4ltFAb_PE9og98roqU(VoiceMessageEnterTransition r0, MessageEnterTransitionContainer r1, ValueAnimator r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetrecordCircle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityEnterView.RecordCircle m21225$$Nest$fgetrecordCircle(VoiceMessageEnterTransition r0) {
        return r0.recordCircle;
    }

    public VoiceMessageEnterTransition(ChatMessageCell r9, ChatActivityEnterView r10, RecyclerListView r11, MessageEnterTransitionContainer r12, Theme.ResourcesProvider r13) {
        this.circlePaint = new Paint(1);
        this.resourcesProvider = r13;
        this.messageView = r9;
        this.container = r12;
        this.listView = r11;
        r9.setEnterTransitionInProgress(true);
        ChatActivityEnterView.RecordCircle r102 = r10.getRecordCircle();
        this.recordCircle = r102;
        if (r102 == null) goto L5;
        this.fromRadius = r102.drawingCircleRadius;
        r102.voiceEnterTransitionInProgress = true;
        r102.skipDraw = true;
    L5:
        this.gradientMatrix = new Matrix();
        Paint r103 = new Paint(1);
        this.gradientPaint = r103;
        r103.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        LinearGradient r0 = new LinearGradient(0.0f, AndroidUtilities.dp(12.0f), 0.0f, 0.0f, 0, -16777216, Shader.TileMode.CLAMP);
        this.gradientShader = r0;
        r103.setShader(r0);
        this.messageId = r9.getMessageObject().stableId;
        r12.addTransition(this);
        ValueAnimator r104 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.animator = r104;
        r104.addUpdateListener(new VoiceMessageEnterTransition$$ExternalSyntheticLambda0(this, r12));
        r104.setInterpolator(new LinearInterpolator());
        r104.setDuration(220);
        r104.addListener(new AnonymousClass1(this, r9, r12));
        if (r9.getSeekBarWaveform() == null) goto L9;
        r9.getSeekBarWaveform().setSent();
        return;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$new$0(MessageEnterTransitionContainer r1, ValueAnimator r2) {
        this.progress = ((Float) r2.getAnimatedValue()).floatValue();
        r1.invalidate();
    }

    private /* synthetic */ void lambda$onDraw$1(Canvas r3, float r4, float r5, float r6, float r7, float r8, float r9, float r10, float r11) {
        this.messageView.getRadialProgress().draw(r3);
        r3.translate(-r4, -r5);
        float r1 = 1.0f / r6;
        r3.scale(r1, r1, r7, r8);
        ChatActivityEnterView.RecordCircle r2 = this.recordCircle;
        if (r2 == null) goto L5;
        r2.drawIcon(r3, (int) r9, (int) r10, 1.0f - r11);
    L5:
        r3.scale(r6, r6, r7, r8);
        r3.translate(r4, r5);
    }

    @Override // org.telegram.ui.MessageEnterTransitionContainer.Transition
    public void onDraw(Canvas r15) {
        float r10 = this.progress;
        if (r10 <= 0.6f) goto L5;
        float r0 = 1.0f;
    L6:
        ChatActivityEnterView.RecordCircle r1 = this.recordCircle;
        float r2 = 0.0f;
        if (r1 != null) goto L9;
        float r8 = 0.0f;
    L10:
        ChatActivityEnterView.RecordCircle r12 = this.recordCircle;
        if (r12 != null) goto L13;
    L12:
        float r9 = r2;
        if (this.messageView.getMessageObject().stableId == this.messageId) goto L17;
        float r13 = this.lastToCx;
        float r22 = this.lastToCy;
    L18:
        this.lastToCx = r13;
        this.lastToCy = r22;
        float r122 = CubicBezierInterpolator.DEFAULT.getInterpolation(r10);
        float r3 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(r10);
        float r6 = ((1.0f - r3) * r8) + (r13 * r3);
        float r14 = 1.0f - r122;
        float r7 = (r9 * r14) + (r22 * r122);
        float r23 = this.messageView.getRadialProgress().getProgressRect().height() / 2.0f;
        float r32 = (this.fromRadius * r14) + (r23 * r122);
        this.listView.getY();
        this.container.getY();
        this.listView.getMeasuredHeight();
        if (this.container.getMeasuredHeight() <= 0) goto L21;
        this.container.getMeasuredHeight();
    L21:
        int r16 = this.messageView.getRadialProgress().getCircleColorKey();
        Paint r4 = this.circlePaint;
        int r5 = Theme.key_chat_messagePanelVoiceBackground;
        int r132 = getThemedColor(r5);
        if (r16 >= 0) goto L24;
        r16 = r5;
    L24:
        r4.setColor(ColorUtils.blendARGB(r132, getThemedColor(r16), r122));
        ChatActivityEnterView.RecordCircle r17 = this.recordCircle;
        if (r17 == null) goto L27;
        r17.drawWaves(r15, r6, r7, 1.0f - r0);
    L27:
        r15.drawCircle(r6, r7, r32, this.circlePaint);
        r15.save();
        float r52 = r32 / r23;
        r15.scale(r52, r52, r6, r7);
        float r33 = r6 - this.messageView.getRadialProgress().getProgressRect().centerX();
        float r42 = r7 - this.messageView.getRadialProgress().getProgressRect().centerY();
        r15.translate(r33, r42);
        this.messageView.getRadialProgress().setOverrideAlpha(r122);
        this.messageView.getRadialProgress().setDrawBackground(false);
        this.messageView.drawVoiceOnce(r15, r122, new VoiceMessageEnterTransition$$ExternalSyntheticLambda1(this, r15, r33, r42, r52, r6, r7, r8, r9, r10));
        this.messageView.getRadialProgress().setDrawBackground(true);
        this.messageView.getRadialProgress().setOverrideAlpha(1.0f);
        r15.restore();
        return;
    L17:
        r22 = ((this.messageView.getRadialProgress().getProgressRect().centerY() + this.messageView.getY()) + this.listView.getY()) - this.container.getY();
        r13 = ((this.messageView.getRadialProgress().getProgressRect().centerX() + this.messageView.getX()) + this.listView.getX()) - this.container.getX();
        goto L18
    L13:
        r2 = (r12.drawingCy + r12.getY()) - this.container.getY();
        goto L12
    L9:
        r8 = (r1.drawingCx + r1.getX()) - this.container.getX();
        goto L10
    L5:
        r0 = r10 / 0.6f;
        goto L6
    }

    public void start() {
        this.animator.start();
    }
}
