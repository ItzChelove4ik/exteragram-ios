package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CounterView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.LetterDrawable;
import org.telegram.ui.Components.StaticLayoutEx;

/* loaded from: classes6.dex */
public class ChatPullingDownDrawable implements NotificationCenter.NotificationCenterDelegate {
    boolean animateCheck;
    public boolean animateSwipeToRelease;
    private AnimatedEmojiDrawable animatedEmojiDrawable;
    Paint arrowPaint;
    float bounceProgress;
    StaticLayout chatNameLayout;
    int chatNameWidth;
    float checkProgress;
    float circleRadius;
    CounterView.CounterDrawable counterDrawable;
    private final int currentAccount;
    private final long currentDialog;
    public int dialogFilterId;
    public int dialogFolderId;
    boolean drawFolderBackground;
    boolean emptyStub;
    private final int filterId;
    private final int folderId;
    private final View fragmentView;
    private final ImageReceiver imageReceiver;
    private final boolean isTopic;
    long lastHapticTime;
    float lastProgress;
    public long lastShowingReleaseTime;
    int lastWidth;
    private long lastWidthTopicId;
    StaticLayout layout1;
    int layout1Width;
    StaticLayout layout2;
    int layout2Width;
    TLRPC.Chat nextChat;
    public long nextDialogId;
    TLRPC.TL_forumTopic nextTopic;
    Runnable onAnimationFinishRunnable;
    int[] params;
    View parentView;
    Path path;
    float progressToBottomPanel;
    boolean recommendedChannel;
    private final Theme.ResourcesProvider resourcesProvider;
    AnimatorSet showReleaseAnimator;
    float swipeToReleaseProgress;
    TextPaint textPaint;
    TextPaint textPaint2;
    private final long topicId;
    private boolean visibleCounterDrawable;
    private Paint xRefPaint;

    /* renamed from: org.telegram.ui.ChatPullingDownDrawable$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatPullingDownDrawable this$0;
        final /* synthetic */ View val$parent;

        public AnonymousClass1(ChatPullingDownDrawable r1, View r2) {
            this.this$0 = r1;
            this.val$parent = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ChatPullingDownDrawable r22 = this.this$0;
            r22.bounceProgress = 0.0f;
            r22.swipeToReleaseProgress = 1.0f;
            this.val$parent.invalidate();
            ChatPullingDownDrawable.m8383$$Nest$fgetfragmentView(this.this$0).invalidate();
            Runnable r23 = this.this$0.onAnimationFinishRunnable;
            if (r23 == null) goto L6;
            r23.run();
            this.this$0.onAnimationFinishRunnable = null;
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatPullingDownDrawable$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatPullingDownDrawable this$0;

        public AnonymousClass2(ChatPullingDownDrawable r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ChatPullingDownDrawable r22 = this.this$0;
            r22.bounceProgress = 0.0f;
            r22.swipeToReleaseProgress = 1.0f;
            View r23 = r22.parentView;
            if (r23 == null) goto L5;
            r23.invalidate();
        L5:
            ChatPullingDownDrawable.m8383$$Nest$fgetfragmentView(this.this$0).invalidate();
            Runnable r24 = this.this$0.onAnimationFinishRunnable;
            if (r24 == null) goto L9;
            r24.run();
            this.this$0.onAnimationFinishRunnable = null;
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$1jB9sw1tKCBNYD7eO5TXjc96q44(ChatPullingDownDrawable r0, View r1, ValueAnimator r2) {
        r0.lambda$showReleaseState$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$39r8fIkDobgMqALACYM6Lfw_fqY(ChatPullingDownDrawable r0, View r1, ValueAnimator r2) {
        r0.lambda$showReleaseState$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$4HPltggxTmv4EXrEFVsiboz9Dbo(ChatPullingDownDrawable r0, ValueAnimator r1) {
        r0.lambda$runOnAnimationFinish$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$7sgncxBVjAPFnMpAbMZ5bq3TyjE(ChatPullingDownDrawable r0, View r1, ValueAnimator r2) {
        r0.lambda$showReleaseState$3(r1, r2);
    }

    /* renamed from: $r8$lambda$D8hVLqwqOvClm2sy-OTcM_dg8nk, reason: not valid java name */
    public static /* synthetic */ void m8382$r8$lambda$D8hVLqwqOvClm2syOTcM_dg8nk(ChatPullingDownDrawable r0, View r1, ValueAnimator r2) {
        r0.lambda$showReleaseState$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$QLdqsrP16p86TYpdRpA6zwgpU04(ChatPullingDownDrawable r0, ValueAnimator r1) {
        r0.lambda$runOnAnimationFinish$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$s36nAuZo1L4iFuy0dtGIU5BoadE(ChatPullingDownDrawable r0, View r1, ValueAnimator r2) {
        r0.lambda$showReleaseState$2(r1, r2);
    }

    /* renamed from: -$$Nest$fgetfragmentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m8383$$Nest$fgetfragmentView(ChatPullingDownDrawable r0) {
        return r0.fragmentView;
    }

    public ChatPullingDownDrawable(int r5, View r6, long r7, int r9, int r10, long r11, Theme.ResourcesProvider r13) {
        this.arrowPaint = new Paint(1);
        this.textPaint = new TextPaint(1);
        this.textPaint2 = new TextPaint(1);
        this.xRefPaint = new Paint(1);
        this.path = new Path();
        this.lastWidthTopicId = 0;
        this.visibleCounterDrawable = true;
        this.counterDrawable = new CounterView.CounterDrawable(null, true, null);
        this.params = new int[3];
        this.fragmentView = r6;
        this.currentAccount = r5;
        this.currentDialog = r7;
        this.folderId = r9;
        this.filterId = r10;
        this.topicId = r11;
        this.isTopic = MessagesController.getInstance(r5).isForum(r7);
        this.resourcesProvider = r13;
        this.imageReceiver = new ImageReceiver(r6);
        this.arrowPaint.setStrokeWidth(AndroidUtilities.dpf2(2.8f));
        this.arrowPaint.setStrokeCap(Paint.Cap.ROUND);
        CounterView.CounterDrawable r52 = this.counterDrawable;
        r52.gravity = 3;
        r52.setType(1);
        CounterView.CounterDrawable r53 = this.counterDrawable;
        r53.addServiceGradient = true;
        r53.circlePaint = getThemedPaint("paintChatActionBackground");
        CounterView.CounterDrawable r54 = this.counterDrawable;
        TextPaint r62 = this.textPaint;
        r54.textPaint = r62;
        r62.setTextSize(AndroidUtilities.dp(13.0f));
        this.textPaint.setTypeface(AndroidUtilities.bold());
        this.textPaint2.setTextSize(AndroidUtilities.dp(14.0f));
        this.xRefPaint.setColor(-16777216);
        this.xRefPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    private void drawArrow(Canvas r11, float r12, float r13, float r14) {
        r11.save();
        float r3 = r14 / AndroidUtilities.dpf2(24.0f);
        r11.scale(r3, r3, r12, r13 - AndroidUtilities.dp(20.0f));
        r11.translate(r12 - AndroidUtilities.dp2(12.0f), r13 - AndroidUtilities.dp(12.0f));
        r11.drawLine(AndroidUtilities.dpf2(12.5f), AndroidUtilities.dpf2(4.0f), AndroidUtilities.dpf2(12.5f), AndroidUtilities.dpf2(22.0f), this.arrowPaint);
        r11.drawLine(AndroidUtilities.dpf2(3.5f), AndroidUtilities.dpf2(12.0f), AndroidUtilities.dpf2(12.5f), AndroidUtilities.dpf2(3.5f), this.arrowPaint);
        r11.drawLine(AndroidUtilities.dpf2(21.5f), AndroidUtilities.dpf2(12.0f), AndroidUtilities.dpf2(12.5f), AndroidUtilities.dpf2(3.5f), this.arrowPaint);
        r11.restore();
    }

    private void drawBackground(Canvas r17, RectF r18, float r19) {
        if (this.drawFolderBackground == false) goto L8;
        this.path.reset();
        float r3 = r18.width() * 0.2f;
        float r5 = r18.width() * 0.1f;
        float r6 = r18.width() * 0.03f;
        float r8 = r5 / 2.0f;
        float r9 = r18.height() - r5;
        this.path.moveTo(r18.right, (r18.top + r3) + r5);
        float r11 = -r3;
        this.path.rQuadTo(0.0f, r11, r11, r11);
        float r14 = r3 * 2.0f;
        float r15 = r8 * 2.0f;
        this.path.rLineTo((((-(r18.width() - r14)) / 2.0f) + r15) - r6, 0.0f);
        float r82 = -r8;
        float r13 = r82 / 2.0f;
        float r83 = r82 * 2.0f;
        float r7 = (-r5) / 2.0f;
        this.path.rQuadTo(r13, 0.0f, r83, r7);
        this.path.rQuadTo(r13, r7, r83, r7);
        this.path.rLineTo((((-(r18.width() - r14)) / 2.0f) + r15) + r6, 0.0f);
        this.path.rQuadTo(r11, 0.0f, r11, r3);
        this.path.rLineTo(0.0f, (r5 + r9) - r14);
        this.path.rQuadTo(0.0f, r3, r3, r3);
        this.path.rLineTo(r18.width() - r14, 0.0f);
        this.path.rQuadTo(r3, 0.0f, r3, r11);
        this.path.rLineTo(0.0f, -(r9 - r14));
        this.path.close();
        r17.drawPath(this.path, getThemedPaint("paintChatActionBackground"));
        if (hasGradientService() == false) goto L12;
        r17.drawPath(this.path, Theme.chat_actionBackgroundGradientDarkenPaint);
        return;
    L12:
        return;
    L8:
        int r2 = getThemedPaint("paintChatActionBackground").getAlpha();
        getThemedPaint("paintChatActionBackground").setAlpha((int) (r2 * r19));
        RectF r32 = AndroidUtilities.rectTmp;
        r17.drawRoundRect(r32, ExteraConfig.getAvatarCorners(r32.width(), true), ExteraConfig.getAvatarCorners(r32.width(), true), getThemedPaint("paintChatActionBackground"));
        getThemedPaint("paintChatActionBackground").setAlpha(r2);
        if (hasGradientService() == false) goto L13;
        int r0 = Theme.chat_actionBackgroundGradientDarkenPaint.getAlpha();
        Theme.chat_actionBackgroundGradientDarkenPaint.setAlpha((int) (r0 * r19));
        r17.drawRoundRect(r32, ExteraConfig.getAvatarCorners(r32.width(), true), ExteraConfig.getAvatarCorners(r32.width(), true), Theme.chat_actionBackgroundGradientDarkenPaint);
        Theme.chat_actionBackgroundGradientDarkenPaint.setAlpha(r0);
        return;
    }

    private void drawCheck(Canvas r15, float r16, float r17) {
        if (this.animateCheck == true) goto L5;
        return;
    L5:
        float r1 = this.checkProgress;
        if (r1 >= 1.0f) goto L10;
        float r12 = r1 + 0.07272727f;
        this.checkProgress = r12;
        if (r12 <= 1.0f) goto L10;
        this.checkProgress = 1.0f;
    L10:
        float r13 = this.checkProgress;
        if (r13 <= 0.5f) goto L13;
        float r3 = 1.0f;
    L15:
        if (r13 >= 0.5f) goto L17;
        float r8 = 0.0f;
    L18:
        r15.save();
        r15.clipRect(AndroidUtilities.rectTmp);
        r15.translate(r16 - AndroidUtilities.dp(24.0f), r17 - AndroidUtilities.dp(24.0f));
        float r14 = AndroidUtilities.dp(16.0f);
        float r2 = AndroidUtilities.dp(26.0f);
        float r9 = AndroidUtilities.dp(22.0f);
        float r10 = AndroidUtilities.dp(32.0f);
        float r11 = AndroidUtilities.dp(32.0f);
        float r122 = AndroidUtilities.dp(20.0f);
        float r4 = 1.0f - r3;
        r15.drawLine(r14, r2, (r14 * r4) + (r9 * r3), (r4 * r2) + (r3 * r10), this.arrowPaint);
        if (r8 <= 0.0f) goto L21;
        float r6 = 1.0f - r8;
        r15.drawLine(r9, r10, (r9 * r6) + (r11 * r8), (r6 * r10) + (r122 * r8), this.arrowPaint);
    L21:
        r15.restore();
        return;
    L17:
        r8 = (r13 - 0.5f) / 0.5f;
        goto L18
    L13:
        r3 = r13 / 0.5f;
        goto L15
    }

    public static TLRPC.Dialog getNextUnreadDialog(long r5, int r7, int r8, boolean r9, int[] r10) {
        MessagesController r92 = AccountInstance.getInstance(UserConfig.selectedAccount).getMessagesController();
        if (r10 == null) goto L6;
        r10[0] = 0;
        r10[1] = r7;
        r10[2] = r8;
    L6:
        if (r8 == 0) goto L11;
        MessagesController.DialogFilter r72 = r92.dialogFiltersById.get(r8);
        if (r72 != null) goto L10;
        return null;
    L10:
        ArrayList<TLRPC.Dialog> r73 = r72.dialogs;
    L12:
        if (r73 != null) goto L14;
        return null;
    L14:
        int r82 = 0;
    L16:
        if (r82 >= r73.size()) goto L33;
        TLRPC.Dialog r1 = r73.get(r82);
        TLRPC.Chat r2 = r92.getChat(Long.valueOf(-r1.id));
        if (r2 == null) goto L32;
        if (r1.id == r5) goto L32;
        if (r1.unread_count <= 0) goto L32;
        if (DialogObject.isChannel(r1) == false) goto L32;
        if (r2.megagroup == true) goto L32;
        if (r92.isPromoDialog(r1.id, false) == true) goto L32;
        if (r92.getRestrictionReason(r2.restriction_reason) != null) goto L32;
        return r1;
    L32:
        r82 = r82 + 1;
        goto L16
    L33:
        return null;
    L11:
        r73 = r92.getDialogs(r7);
        goto L12
    }

    private TLRPC.TL_forumTopic getNextUnreadTopic(long r7) {
        ArrayList<TLRPC.TL_forumTopic> r72 = MessagesController.getInstance(this.currentAccount).getTopicsController().getTopics(r7);
        TLRPC.TL_forumTopic r8 = null;
        if (r72 != null) goto L5;
    L24:
        return r8;
    L5:
        if (r72.size() <= 1) goto L24;
        int r0 = 0;
    L8:
        if (r0 >= r72.size()) goto L24;
        TLRPC.TL_forumTopic r1 = r72.get(r0);
        if (r1.id == this.topicId) goto L23;
        if (r1.hidden == true) goto L23;
        if (r1.unread_count <= 0) goto L23;
        if (r8 == null) goto L22;
        TLRPC.Message r2 = r1.topMessage;
        if (r2 == null) goto L23;
        TLRPC.Message r3 = r8.topMessage;
        if (r3 == null) goto L23;
        if (r2.date <= r3.date) goto L23;
    L22:
        r8 = r1;
    L23:
        r0 = r0 + 1;
        goto L8
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private Paint getThemedPaint(String r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Paint r02 = r0.getPaint(r1);
    L6:
        if (r02 == null) goto L9;
        return r02;
    L9:
        return Theme.getThemePaint(r1);
    L5:
        r02 = null;
        goto L6
    }

    private boolean hasGradientService() {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        return r0.hasGradientService();
    L7:
        return Theme.hasGradientService();
    }

    private /* synthetic */ void lambda$runOnAnimationFinish$5(ValueAnimator r1) {
        this.swipeToReleaseProgress = ((Float) r1.getAnimatedValue()).floatValue();
        this.fragmentView.invalidate();
        View r0 = this.parentView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$runOnAnimationFinish$6(ValueAnimator r1) {
        this.bounceProgress = ((Float) r1.getAnimatedValue()).floatValue();
        View r0 = this.parentView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$showReleaseState$0(View r1, ValueAnimator r2) {
        this.swipeToReleaseProgress = ((Float) r2.getAnimatedValue()).floatValue();
        r1.invalidate();
        this.fragmentView.invalidate();
    }

    private /* synthetic */ void lambda$showReleaseState$1(View r1, ValueAnimator r2) {
        this.bounceProgress = ((Float) r2.getAnimatedValue()).floatValue();
        r1.invalidate();
    }

    private /* synthetic */ void lambda$showReleaseState$2(View r1, ValueAnimator r2) {
        this.bounceProgress = ((Float) r2.getAnimatedValue()).floatValue();
        r1.invalidate();
    }

    private /* synthetic */ void lambda$showReleaseState$3(View r1, ValueAnimator r2) {
        this.bounceProgress = ((Float) r2.getAnimatedValue()).floatValue();
        r1.invalidate();
    }

    private /* synthetic */ void lambda$showReleaseState$4(View r1, ValueAnimator r2) {
        this.swipeToReleaseProgress = ((Float) r2.getAnimatedValue()).floatValue();
        this.fragmentView.invalidate();
        r1.invalidate();
    }

    private void showReleaseState(boolean r10, View r11) {
        AnimatorSet r0 = this.showReleaseAnimator;
        if (r0 == null) goto L5;
        r0.removeAllListeners();
        this.showReleaseAnimator.cancel();
    L5:
        float r02 = this.swipeToReleaseProgress;
        if (r10 == false) goto L9;
        ValueAnimator r102 = ValueAnimator.ofFloat(new float[]{r02, 1.0f});
        r102.addUpdateListener(new ChatPullingDownDrawable$$ExternalSyntheticLambda2(this, r11));
        r102.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r102.setDuration(250);
        this.bounceProgress = 0.0f;
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r03.addUpdateListener(new ChatPullingDownDrawable$$ExternalSyntheticLambda3(this, r11));
        CubicBezierInterpolator r4 = CubicBezierInterpolator.EASE_BOTH;
        r03.setInterpolator(r4);
        r03.setDuration(180);
        ValueAnimator r5 = ValueAnimator.ofFloat(new float[]{1.0f, -0.5f});
        r5.addUpdateListener(new ChatPullingDownDrawable$$ExternalSyntheticLambda4(this, r11));
        r5.setInterpolator(r4);
        r5.setDuration(120);
        ValueAnimator r6 = ValueAnimator.ofFloat(new float[]{-0.5f, 0.0f});
        r6.addUpdateListener(new ChatPullingDownDrawable$$ExternalSyntheticLambda5(this, r11));
        r6.setInterpolator(r4);
        r6.setDuration(100);
        AnimatorSet r42 = new AnimatorSet();
        this.showReleaseAnimator = r42;
        r42.addListener(new AnonymousClass1(this, r11));
        AnimatorSet r112 = new AnimatorSet();
        r112.playSequentially(new Animator[]{r03, r5, r6});
        this.showReleaseAnimator.playTogether(new Animator[]{r102, r112});
        this.showReleaseAnimator.start();
        return;
    L9:
        ValueAnimator r103 = ValueAnimator.ofFloat(new float[]{r02, 0.0f});
        r103.addUpdateListener(new ChatPullingDownDrawable$$ExternalSyntheticLambda6(this, r11));
        r103.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r103.setDuration(220);
        AnimatorSet r113 = new AnimatorSet();
        this.showReleaseAnimator = r113;
        r113.playTogether(new Animator[]{r103});
        this.showReleaseAnimator.start();
    }

    public boolean animationIsRunning() {
        if (this.swipeToReleaseProgress == 1.0f) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (this.nextDialogId == 0) goto L14;
        TLRPC.Dialog r32 = MessagesController.getInstance(this.currentAccount).dialogs_dict.get(this.nextDialogId);
        if (r32 == null) goto L15;
        int r33 = r32.unread_count;
        boolean r52 = true;
        this.counterDrawable.setCount(r33, true);
        if (r33 > 0) goto L10;
        r52 = false;
    L10:
        this.visibleCounterDrawable = r52;
        View r2 = this.parentView;
        if (r2 == null) goto L16;
        r2.invalidate();
        return;
    L16:
        return;
    L15:
        return;
    }

    public void draw(Canvas r27, View r28, float r29, float r30) {
        if (this.parentView == r28) goto L7;
        this.parentView = r28;
        AnimatedEmojiDrawable r4 = this.animatedEmojiDrawable;
        if (r4 == null) goto L7;
        r4.addView(r28);
    L7:
        this.counterDrawable.setParent(r28);
        float r42 = AndroidUtilities.dp(110.0f) * r29;
        if (r42 >= AndroidUtilities.dp(8.0f)) goto L11;
        return;
    L11:
        if (r29 >= 0.2f) goto L13;
        float r6 = (5.0f * r29) * r30;
    L14:
        Theme.applyServiceShaderMatrix(this.lastWidth, r28.getMeasuredHeight(), 0.0f, r28.getMeasuredHeight() - r42);
        TextPaint r7 = this.textPaint;
        int r8 = Theme.key_chat_serviceText;
        r7.setColor(getThemedColor(r8));
        this.arrowPaint.setColor(getThemedColor(r8));
        this.textPaint2.setColor(getThemedColor(Theme.key_chat_messagePanelHint));
        int r9 = getThemedPaint("paintChatActionBackground").getAlpha();
        int r11 = Theme.chat_actionBackgroundGradientDarkenPaint.getAlpha();
        int r12 = this.textPaint.getAlpha();
        int r13 = this.arrowPaint.getAlpha();
        Theme.chat_actionBackgroundGradientDarkenPaint.setAlpha((int) (r11 * r6));
        int r14 = (int) (r9 * r6);
        getThemedPaint("paintChatActionBackground").setAlpha(r14);
        int r15 = (int) (r12 * r6);
        this.textPaint.setAlpha(r15);
        if (r29 < 1.0f) goto L21;
        if (this.lastProgress >= 1.0f) goto L21;
    L19:
        String r302 = "paintChatActionBackground";
        long r72 = System.currentTimeMillis();
        float r19 = 1.0f;
        float r18 = r6;
        if ((r72 - this.lastHapticTime) > 100) goto L102;
    L30:
        this.lastProgress = r29;
    L33:
        if (r29 == 1.0f) goto L35;
    L37:
        if (r29 != 1.0f) goto L39;
    L41:
        float r2 = this.lastWidth / 2.0f;
        float r62 = this.bounceProgress * (-AndroidUtilities.dp(4.0f));
        if (this.emptyStub == false) goto L44;
        r42 = r42 - r62;
    L44:
        float r16 = r42 / 2.0f;
        float r5 = Math.max(0.0f, Math.min(this.circleRadius, (r16 - (AndroidUtilities.dp(16.0f) * r29)) - AndroidUtilities.dp(4.0f)));
        float r73 = ((Math.max(0.0f, Math.min(this.circleRadius * r29, r16 - (AndroidUtilities.dp(8.0f) * r29))) * 2.0f) - AndroidUtilities.dp2(16.0f)) * (r19 - this.swipeToReleaseProgress);
        float r82 = AndroidUtilities.dp(56.0f);
        float r10 = this.swipeToReleaseProgress;
        float r74 = r73 + (r82 * r10);
        if (r10 >= r19) goto L47;
    L50:
        float r23 = 36.0f;
        float r102 = -r42;
        float r83 = ((-AndroidUtilities.dp(8.0f)) * (r19 - this.swipeToReleaseProgress)) + (this.swipeToReleaseProgress * (AndroidUtilities.dp(56.0f) + r102));
        RectF r52 = AndroidUtilities.rectTmp;
        float r162 = r62;
        int r25 = r12;
        r52.set(r2 - r5, r102, r2 + r5, r83);
        if (this.swipeToReleaseProgress > 0.0f) goto L53;
    L55:
        float r63 = r19;
    L56:
        drawBackground(r27, r52, r63);
        float r84 = ((AndroidUtilities.dp(24.0f) + r102) + (AndroidUtilities.dp(8.0f) * (r19 - r29))) - (AndroidUtilities.dp(36.0f) * this.swipeToReleaseProgress);
        r27.save();
        r52.inset(AndroidUtilities.dp(r19), AndroidUtilities.dp(r19));
        r27.clipRect(r52);
        float r53 = this.swipeToReleaseProgress;
        if (r53 <= 0.0f) goto L59;
        this.arrowPaint.setAlpha((int) ((r19 - r53) * 255.0f));
    L59:
        drawArrow(r27, r2, r84, AndroidUtilities.dp(24.0f) * r29);
        if (this.emptyStub == false) goto L62;
        float r54 = (((((-AndroidUtilities.dp(8.0f)) - (AndroidUtilities.dp2(8.0f) * r29)) - r74) * (r19 - this.swipeToReleaseProgress)) + ((r102 - AndroidUtilities.dp(2.0f)) * this.swipeToReleaseProgress)) + r162;
        this.arrowPaint.setAlpha(r13);
        r27.save();
        r27.scale(r29, r29, r2, AndroidUtilities.dp(28.0f) + r54);
        drawCheck(r27, r2, r54 + AndroidUtilities.dp(28.0f));
        r27.restore();
    L62:
        r27.restore();
    L64:
        if (this.chatNameLayout != null) goto L66;
    L71:
        String r85 = r302;
    L73:
        if (this.emptyStub == false) goto L75;
    L99:
        getThemedPaint(r85).setAlpha(r9);
        Theme.chat_actionBackgroundGradientDarkenPaint.setAlpha(r11);
        this.textPaint.setAlpha(r25);
        this.arrowPaint.setAlpha(r13);
        return;
    L75:
        if (r74 <= 0.0f) goto L99;
        float r103 = (((((-AndroidUtilities.dp(8.0f)) - (AndroidUtilities.dp2(8.0f) * r29)) - r74) * (r19 - this.swipeToReleaseProgress)) + (((-r42) + AndroidUtilities.dp(4.0f)) * this.swipeToReleaseProgress)) + r162;
        AnimatedEmojiDrawable r43 = this.animatedEmojiDrawable;
        if (r43 != null) goto L79;
    L82:
        ImageReceiver r44 = this.imageReceiver;
    L81:
        ImageReceiver r122 = r44;
        r122.setAlpha(r18);
        r122.setRoundRadius(ExteraConfig.getAvatarCorners(r74, true));
        r122.setImageCoords(r2 - (r74 / 2.0f), r103, r74, r74);
        if (this.isTopic == false) goto L93;
        if (r122.getDrawable() == null) goto L93;
        if ((r122.getDrawable() instanceof CombinedDrawable) == false) goto L93;
        if ((((CombinedDrawable) r122.getDrawable()).getIcon() instanceof LetterDrawable) == false) goto L93;
        ((LetterDrawable) ((CombinedDrawable) r122.getDrawable()).getIcon()).scale = r29;
    L93:
        if (this.swipeToReleaseProgress > 0.0f) goto L95;
    L97:
        float r152 = r19;
        r122.draw(r27);
    L98:
        r122.setAlpha(r152);
        goto L99
    L95:
        if (this.visibleCounterDrawable == false) goto L97;
        r152 = r19;
        r27.saveLayerAlpha(r122.getImageX(), r122.getImageY(), r122.getImageX() + r122.getImageWidth(), r122.getImageY() + r122.getImageHeight(), 255, 31);
        r122.draw(r27);
        float r22 = this.swipeToReleaseProgress;
        r27.scale(r22, r22, (AndroidUtilities.dp(12.0f) + r2) + this.counterDrawable.getCenterX(), (r103 - AndroidUtilities.dp(6.0f)) + AndroidUtilities.dp(14.0f));
        r27.translate(AndroidUtilities.dp(12.0f) + r2, r103 - AndroidUtilities.dp(6.0f));
        this.counterDrawable.updateBackgroundRect();
        this.counterDrawable.rectF.inset(-AndroidUtilities.dp(2.0f), -AndroidUtilities.dp(2.0f));
        RectF r24 = this.counterDrawable.rectF;
        r27.drawRoundRect(r24, r24.height() / 2.0f, this.counterDrawable.rectF.height() / 2.0f, this.xRefPaint);
        r27.restore();
        r27.save();
        float r26 = this.swipeToReleaseProgress;
        r27.scale(r26, r26, (AndroidUtilities.dp(12.0f) + r2) + this.counterDrawable.getCenterX(), (r103 - AndroidUtilities.dp(6.0f)) + AndroidUtilities.dp(14.0f));
        r27.translate(AndroidUtilities.dp(12.0f) + r2, r103 - AndroidUtilities.dp(6.0f));
        this.counterDrawable.draw(r27);
        r27.restore();
        goto L98
    L79:
        if (r43.getImageReceiver() == null) goto L82;
        r44 = this.animatedEmojiDrawable.getImageReceiver();
        goto L81
    L66:
        if (this.swipeToReleaseProgress <= 0.0f) goto L71;
        r85 = r302;
        getThemedPaint(r85).setAlpha(r14);
        this.textPaint.setAlpha(r15);
        float r55 = ((AndroidUtilities.dp(20.0f) * (r19 - this.swipeToReleaseProgress)) - (AndroidUtilities.dp(r23) * this.swipeToReleaseProgress)) + r162;
        RectF r64 = AndroidUtilities.rectTmp;
        int r104 = this.lastWidth;
        int r123 = this.chatNameWidth;
        r64.set((r104 - r123) / 2.0f, r55, r104 - ((r104 - r123) / 2.0f), this.chatNameLayout.getHeight() + r55);
        r64.inset(-AndroidUtilities.dp(8.0f), -AndroidUtilities.dp(4.0f));
        r27.drawRoundRect(r64, ExteraConfig.getAvatarCorners(r64.width(), true), ExteraConfig.getAvatarCorners(r64.width(), true), getThemedPaint(r85));
        if (hasGradientService() == false) goto L70;
        r27.drawRoundRect(r64, ExteraConfig.getAvatarCorners(r64.width(), true), ExteraConfig.getAvatarCorners(r64.width(), true), Theme.chat_actionBackgroundGradientDarkenPaint);
    L70:
        r27.save();
        r27.translate((this.lastWidth - this.chatNameWidth) / 2.0f, r55);
        this.chatNameLayout.draw(r27);
        r27.restore();
        goto L73
    L53:
        if (this.emptyStub == true) goto L55;
        float r65 = AndroidUtilities.dp(16.0f) * this.swipeToReleaseProgress;
        r52.inset(r65, r65);
        r63 = r19 - this.swipeToReleaseProgress;
        goto L56
    L47:
        if (this.emptyStub == true) goto L50;
        r162 = r62;
        r25 = r12;
        r23 = 36.0f;
        goto L64
    L39:
        if (this.animateSwipeToRelease == false) goto L41;
        this.animateSwipeToRelease = false;
        showReleaseState(false, r28);
        goto L41
    L35:
        if (this.animateSwipeToRelease == true) goto L37;
        this.animateSwipeToRelease = true;
        this.animateCheck = true;
        showReleaseState(true, r28);
        this.lastShowingReleaseTime = System.currentTimeMillis();
        goto L41
    L102:
        r28.performHapticFeedback(3, 2);     // Catch: Exception -> L101
    L29:
        this.lastHapticTime = r72;
    L21:
        if (r29 < 1.0f) goto L23;
    L31:
        r18 = r6;
        r19 = 1.0f;
        r302 = "paintChatActionBackground";
        goto L33
    L23:
        if (this.lastProgress != 1.0f) goto L31;
    L13:
        r6 = r30;
        goto L14
    }

    public void drawBottomPanel(Canvas r9, int r10, int r11, int r12) {
        this.textPaint2.setColor(getThemedColor(Theme.key_glass_defaultText));
        Paint r122 = getThemedPaint("paintChatComposeBackground");
        int r0 = r122.getAlpha();
        int r1 = this.textPaint2.getAlpha();
        r122.setAlpha((int) (r0 * this.progressToBottomPanel));
        if (this.layout1 == null) goto L8;
        float r2 = this.swipeToReleaseProgress;
        if (r2 >= 1.0f) goto L8;
        this.textPaint2.setAlpha((int) ((r1 * (1.0f - r2)) * this.progressToBottomPanel));
        float r22 = (r10 + (((r11 - r10) - this.layout1.getHeight()) / 2.0f)) - (AndroidUtilities.dp(10.0f) * this.swipeToReleaseProgress);
        r9.save();
        r9.translate((this.lastWidth - this.layout1Width) / 2.0f, r22);
        this.layout1.draw(r9);
        r9.restore();
    L8:
        if (this.layout2 == null) goto L12;
        float r23 = this.swipeToReleaseProgress;
        if (r23 <= 0.0f) goto L12;
        this.textPaint2.setAlpha((int) ((r1 * r23) * this.progressToBottomPanel));
        float r24 = (r10 + (((r11 - r10) - this.layout2.getHeight()) / 2.0f)) + (AndroidUtilities.dp(10.0f) * (1.0f - this.swipeToReleaseProgress));
        r9.save();
        r9.translate((this.lastWidth - this.layout2Width) / 2.0f, r24);
        this.layout2.draw(r9);
        r9.restore();
    L12:
        this.textPaint2.setAlpha(r1);
        r122.setAlpha(r0);
    }

    public long getChatId() {
        TLRPC.Chat r2 = this.nextChat;
        if (r2 != null) goto L7;
        return 0;
    L7:
        return r2.id;
    }

    public TLRPC.TL_forumTopic getTopic() {
        return this.nextTopic;
    }

    public boolean needDrawBottomPanel() {
        if (this.progressToBottomPanel > 0.0f) goto L5;
        return false;
    L5:
        if (this.emptyStub == true) goto L10;
        return true;
    L10:
        return false;
    }

    public void onAttach() {
        this.imageReceiver.onAttachedToWindow();
        AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
        if (r0 == null) goto L7;
        View r1 = this.parentView;
        if (r1 == null) goto L7;
        r0.addView(r1);
    L7:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
    }

    public void onDetach() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
        this.imageReceiver.onDetachedFromWindow();
        AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
        if (r0 == null) goto L7;
        View r1 = this.parentView;
        if (r1 == null) goto L7;
        r0.removeView(r1);
    L7:
        this.lastProgress = 0.0f;
        this.lastHapticTime = 0;
    }

    public void reset() {
        this.checkProgress = 0.0f;
        this.animateCheck = false;
    }

    public void runOnAnimationFinish(Runnable r7) {
        AnimatorSet r0 = this.showReleaseAnimator;
        if (r0 == null) goto L5;
        r0.removeAllListeners();
        this.showReleaseAnimator.cancel();
    L5:
        this.onAnimationFinishRunnable = r7;
        this.showReleaseAnimator = new AnimatorSet();
        ValueAnimator r72 = ValueAnimator.ofFloat(new float[]{this.swipeToReleaseProgress, 1.0f});
        r72.addUpdateListener(new ChatPullingDownDrawable$$ExternalSyntheticLambda0(this));
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{this.bounceProgress, 0.0f});
        r1.addUpdateListener(new ChatPullingDownDrawable$$ExternalSyntheticLambda1(this));
        this.showReleaseAnimator.addListener(new AnonymousClass2(this));
        this.showReleaseAnimator.playTogether(new Animator[]{r72, r1});
        this.showReleaseAnimator.setDuration(120);
        this.showReleaseAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.showReleaseAnimator.start();
    }

    public void setWidth(int r14) {
        if (r14 == this.lastWidth) goto L5;
    L10:
        this.circleRadius = AndroidUtilities.dp(56.0f) / 2.0f;
        this.lastWidth = r14;
        TLRPC.Chat r142 = this.nextChat;
        if (r142 == null) goto L14;
        String r143 = r142.title;
    L13:
        String r2 = r143;
        int r144 = (int) this.textPaint.measureText(r2, 0, r2.length());
        this.chatNameWidth = r144;
        int r4 = Math.min(r144, this.lastWidth - AndroidUtilities.dp(60.0f));
        this.chatNameWidth = r4;
        this.chatNameLayout = StaticLayoutEx.createStaticLayout(r2, this.textPaint, r4, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, r4, 1);
        if (this.recommendedChannel == false) goto L26;
        String r145 = LocaleController.getString(R.string.SwipeToGoNextRecommendedChannel);
        String r0 = LocaleController.getString(R.string.ReleaseToGoNextRecommendedChannel);
    L24:
        String r3 = r145;
        int r146 = (int) this.textPaint2.measureText(r3);
        this.layout1Width = r146;
        this.layout1Width = Math.min(r146, this.lastWidth - AndroidUtilities.dp(60.0f));
        TextPaint r42 = this.textPaint2;
        int r5 = this.layout1Width;
        Layout.Alignment r6 = Layout.Alignment.ALIGN_CENTER;
        this.layout1 = new StaticLayout(r3, r42, r5, r6, 1.0f, 0.0f, false);
        int r147 = (int) this.textPaint2.measureText(r0);
        this.layout2Width = r147;
        this.layout2Width = Math.min(r147, this.lastWidth - AndroidUtilities.dp(60.0f));
        this.layout2 = new StaticLayout(r0, this.textPaint2, this.layout2Width, r6, 1.0f, 0.0f, false);
        this.imageReceiver.setImageCoords((this.lastWidth / 2.0f) - (AndroidUtilities.dp(40.0f) / 2.0f), (AndroidUtilities.dp(12.0f) + this.circleRadius) - (AndroidUtilities.dp(40.0f) / 2.0f), AndroidUtilities.dp(40.0f), AndroidUtilities.dp(40.0f));
        this.imageReceiver.setRoundRadius(ExteraConfig.getAvatarCorners(40.0f));
        this.counterDrawable.setSize(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(100.0f));
        if (this.isTopic == false) goto L48;
        TLRPC.TL_forumTopic r148 = this.nextTopic;
        if (r148 != null) goto L42;
        long r02 = 0;
    L43:
        this.lastWidthTopicId = r02;
        return;
    L42:
        r02 = r148.id;
        goto L43
    L48:
        return;
    L26:
        if (this.isTopic == false) goto L28;
        r145 = LocaleController.getString(R.string.SwipeToGoNextUnreadTopic);
        r0 = LocaleController.getString(R.string.ReleaseToGoNextUnreadTopic);
        goto L24
    L28:
        boolean r149 = this.drawFolderBackground;
        if (r149 == false) goto L34;
        int r03 = this.dialogFolderId;
        if (r03 == this.folderId) goto L34;
        if (r03 == 0) goto L34;
        r145 = LocaleController.getString(R.string.SwipeToGoNextArchive);
        r0 = LocaleController.getString(R.string.ReleaseToGoNextArchive);
    L34:
        if (r149 == false) goto L36;
        r145 = LocaleController.getString(R.string.SwipeToGoNextFolder);
        r0 = LocaleController.getString(R.string.ReleaseToGoNextFolder);
        goto L24
    L36:
        r145 = LocaleController.getString(R.string.SwipeToGoNextChannel);
        r0 = LocaleController.getString(R.string.ReleaseToGoNextChannel);
        goto L24
    L14:
        TLRPC.TL_forumTopic r1410 = this.nextTopic;
        if (r1410 == null) goto L18;
        r143 = r1410.title;
        goto L13
    L18:
        if (this.isTopic == false) goto L20;
        r143 = LocaleController.formatString(R.string.SwipeToGoNextTopicEnd, new Object[]{MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.currentDialog)).title});
        goto L13
    L20:
        r143 = LocaleController.getString(R.string.SwipeToGoNextChannelEnd);
        goto L13
    L5:
        if (this.isTopic == true) goto L7;
        return;
    L7:
        if (this.nextTopic != null) goto L9;
        return;
    L9:
        if (this.lastWidthTopicId != r0.id) goto L10;
    }

    public void updateDialog() {
        boolean r0 = false;
        this.recommendedChannel = false;
        this.nextTopic = null;
        TLRPC.Dialog r2 = getNextUnreadDialog(this.currentDialog, this.folderId, this.filterId, true, this.params);
        if (r2 == null) goto L16;
        this.nextDialogId = r2.id;
        int[] r4 = this.params;
        if (r4[0] != 1) goto L7;
        boolean r5 = true;
    L8:
        this.drawFolderBackground = r5;
        this.dialogFolderId = r4[1];
        this.dialogFilterId = r4[2];
        this.emptyStub = false;
        TLRPC.Chat r42 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r2.id));
        this.nextChat = r42;
        if (r42 != null) goto L11;
        this.nextChat = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r2.id));
    L11:
        AvatarDrawable r8 = new AvatarDrawable();
        r8.setInfo(this.currentAccount, this.nextChat);
        this.imageReceiver.setImage(ImageLocation.getForChat(this.nextChat, 1), "50_50", r8, null, UserConfig.getInstance(0).getCurrentUser(), 0);
        MessagesController.getInstance(this.currentAccount).ensureMessagesLoaded(r2.id, 0, null);
        int r1 = r2.unread_count;
        this.counterDrawable.setCount(r1, false);
        if (r1 <= 0) goto L14;
        r0 = true;
    L14:
        this.visibleCounterDrawable = r0;
        return;
    L7:
        r5 = false;
        goto L8
    L16:
        this.nextChat = null;
        this.drawFolderBackground = false;
        this.emptyStub = true;
    }

    public void updateDialog(TLRPC.Chat r12) {
        if (r12 != null) goto L5;
        updateDialog();
        return;
    L5:
        this.nextDialogId = -r12.id;
        int[] r0 = this.params;
        boolean r1 = false;
        if (r0[0] != 1) goto L8;
        boolean r2 = true;
    L9:
        this.drawFolderBackground = r2;
        this.dialogFolderId = r0[1];
        this.dialogFilterId = r0[2];
        this.emptyStub = false;
        this.nextChat = r12;
        AvatarDrawable r7 = new AvatarDrawable();
        r7.setInfo(this.currentAccount, this.nextChat);
        this.imageReceiver.setImage(ImageLocation.getForChat(this.nextChat, 1), "50_50", r7, null, UserConfig.getInstance(0).getCurrentUser(), 0);
        MessagesController.getInstance(this.currentAccount).ensureMessagesLoaded(-r12.id, 0, null);
        TLRPC.Dialog r122 = MessagesController.getInstance(this.currentAccount).getDialog(-r12.id);
        if (r122 != null) goto L12;
        int r123 = 0;
    L13:
        this.counterDrawable.setCount(r123, false);
        if (r123 <= 0) goto L16;
        r1 = true;
    L16:
        this.visibleCounterDrawable = r1;
        this.recommendedChannel = true;
        this.nextTopic = null;
        return;
    L12:
        r123 = r122.unread_count;
        goto L13
    L8:
        r2 = false;
        goto L9
    }

    public void updateTopic() {
        boolean r0 = false;
        this.recommendedChannel = false;
        this.drawFolderBackground = false;
        this.nextChat = null;
        this.nextDialogId = 0;
        this.imageReceiver.clearImage();
        TLRPC.TL_forumTopic r4 = getNextUnreadTopic(-this.currentDialog);
        if (r4 == null) goto L41;
        this.emptyStub = false;
        this.nextTopic = r4;
        if (r4.id != 1) goto L13;
        View r2 = this.parentView;
        if (r2 == null) goto L11;
        AnimatedEmojiDrawable r3 = this.animatedEmojiDrawable;
        if (r3 == null) goto L11;
        r3.removeView(r2);
    L11:
        this.animatedEmojiDrawable = null;
        this.imageReceiver.setImageBitmap(ForumUtilities.createGeneralTopicDrawable(this.fragmentView.getContext(), 1.0f, getThemedColor(Theme.key_chat_inMenu), false, true));
    L36:
        int r1 = r4.unread_count;
        this.counterDrawable.setCount(r1, false);
        if (r1 <= 0) goto L39;
        r0 = true;
    L39:
        this.visibleCounterDrawable = r0;
        return;
    L13:
        if (r4.icon_emoji_id == 0) goto L30;
        AnimatedEmojiDrawable r22 = this.animatedEmojiDrawable;
        if (r22 != null) goto L17;
    L18:
        AnimatedEmojiDrawable r23 = this.animatedEmojiDrawable;
        if (r23 == null) goto L23;
        View r32 = this.parentView;
        if (r32 == null) goto L23;
        r23.removeView(r32);
    L23:
        AnimatedEmojiDrawable r24 = new AnimatedEmojiDrawable(22, this.currentAccount, r4.icon_emoji_id);
        this.animatedEmojiDrawable = r24;
        r24.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_serviceText), PorterDuff.Mode.SRC_IN));
    L24:
        AnimatedEmojiDrawable r25 = this.animatedEmojiDrawable;
        if (r25 == null) goto L29;
        View r33 = this.parentView;
        if (r33 == null) goto L29;
        r25.addView(r33);
    L29:
        this.imageReceiver.setImageBitmap(null);
        goto L36
    L17:
        if (r22.getDocumentId() == r4.icon_emoji_id) goto L24;
    L30:
        View r26 = this.parentView;
        if (r26 == null) goto L35;
        AnimatedEmojiDrawable r34 = this.animatedEmojiDrawable;
        if (r34 == null) goto L35;
        r34.removeView(r26);
    L35:
        this.animatedEmojiDrawable = null;
        this.imageReceiver.setImageBitmap(ForumUtilities.createTopicDrawable(r4, false));
        goto L36
    L41:
        this.nextTopic = null;
        this.emptyStub = true;
    }
}
