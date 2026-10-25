package com.exteragram.messenger.preferences.chats.components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.preferences.components.CustomPreferenceCell;
import com.exteragram.messenger.utils.chats.SwipeAction;
import com.exteragram.messenger.utils.chats.SwipeActionsHelper;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.BackgroundGradientDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;

/* loaded from: classes4.dex */
public class SwipeActionsCell extends FrameLayout implements CustomPreferenceCell {
    private final List<SwipeAction> actions;
    private Drawable backgroundDrawable;
    private BackgroundGradientDrawable.Disposable backgroundGradientDisposable;
    private ValueAnimator cycle;
    private final SwipeActionsHelper helper;
    private boolean looped;
    private final ChatMessageCell messageCell;
    private final Drawable monetBackgroundDrawable;
    private List<SwipeAction> pending;
    private int selected;
    private final Drawable shadowDrawable;
    private float slide;

    /* renamed from: com.exteragram.messenger.preferences.chats.components.SwipeActionsCell$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ SwipeActionsCell this$0;

        public static /* synthetic */ void $r8$lambda$2GUQUy5arKKItkDPzDxTbtPU3iM(AnonymousClass1 r0, List r1) {
            r0.lambda$onAnimationRepeat$0(r1);
        }

        public AnonymousClass1(SwipeActionsCell r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAnimationRepeat$0(List r1) {
            SwipeActionsCell.m1707$$Nest$mapplyActions(this.this$0, r1);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator r3) {
            List r32 = SwipeActionsCell.m1704$$Nest$fgetpending(this.this$0);
            SwipeActionsCell r0 = this.this$0;
            if (r32 == null) goto L6;
            List r33 = SwipeActionsCell.m1704$$Nest$fgetpending(r0);
            SwipeActionsCell.m1705$$Nest$fputpending(this.this$0, null);
            AndroidUtilities.runOnUIThread(new SwipeActionsCell$1$$ExternalSyntheticLambda0(this, r33));
            return;
        L6:
            SwipeActionsCell.m1706$$Nest$fputselected(r0, 0);
            SwipeActionsCell.m1703$$Nest$fgethelper(this.this$0).start(SwipeActionsCell.m1702$$Nest$fgetactions(this.this$0));
            SwipeActionsCell.m1703$$Nest$fgethelper(this.this$0).select(0);
        }
    }

    public static /* synthetic */ void $r8$lambda$G3ND7zKv7oJ2az6zV8DAYxb0_sw(SwipeActionsCell r0, int r1, ValueAnimator r2) {
        r0.lambda$restartCycle$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetactions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m1702$$Nest$fgetactions(SwipeActionsCell r0) {
        return r0.actions;
    }

    /* renamed from: -$$Nest$fgethelper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SwipeActionsHelper m1703$$Nest$fgethelper(SwipeActionsCell r0) {
        return r0.helper;
    }

    /* renamed from: -$$Nest$fgetpending, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m1704$$Nest$fgetpending(SwipeActionsCell r0) {
        return r0.pending;
    }

    /* renamed from: -$$Nest$fputpending, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1705$$Nest$fputpending(SwipeActionsCell r0, List r1) {
        r0.pending = r1;
    }

    /* renamed from: -$$Nest$fputselected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1706$$Nest$fputselected(SwipeActionsCell r0, int r1) {
        r0.selected = r1;
    }

    /* renamed from: -$$Nest$mapplyActions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1707$$Nest$mapplyActions(SwipeActionsCell r0, List r1) {
        r0.applyActions(r1);
    }

    public SwipeActionsCell(Context r18) {
        super(r18);
        this.actions = new ArrayList();
        setWillNotDraw(false);
        this.monetBackgroundDrawable = new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundGray));
        this.shadowDrawable = Theme.getThemedDrawable(r18, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow);
        this.helper = new SwipeActionsHelper(this, null);
        TLRPC.TL_message r3 = new TLRPC.TL_message();
        r3.message = LocaleController.getString(R.string.SwipeActionsPreviewMessage);
        r3.date = ((int) (System.currentTimeMillis() / 1000)) - 3600;
        r3.dialog_id = 1;
        r3.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
        TLRPC.TL_peerUser r7 = new TLRPC.TL_peerUser();
        r3.from_id = r7;
        r7.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
        r3.id = 1;
        r3.out = false;
        TLRPC.TL_peerUser r8 = new TLRPC.TL_peerUser();
        r3.peer_id = r8;
        r8.user_id = 0;
        TLRPC.TL_message r82 = new TLRPC.TL_message();
        r82.message = LocaleController.getString(R.string.SwipeActionsPreviewReply);
        r82.date = r3.date;
        r82.dialog_id = 1;
        r82.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
        TLRPC.TL_peerUser r6 = new TLRPC.TL_peerUser();
        r82.from_id = r6;
        r6.user_id = r3.from_id.user_id;
        r82.id = 2;
        r82.media = new TLRPC.TL_messageMediaEmpty();
        r82.out = true;
        TLRPC.TL_peerUser r62 = new TLRPC.TL_peerUser();
        r82.peer_id = r62;
        r62.user_id = 1;
        TLRPC.TL_messageReplyHeader r63 = new TLRPC.TL_messageReplyHeader();
        r3.reply_to = r63;
        r63.flags |= 16;
        r63.reply_to_msg_id = r82.id;
        r3.flags |= 8;
        MessageObject r12 = new MessageObject(UserConfig.selectedAccount, r3, true, false);
        r12.replyMessageObject = new MessageObject(UserConfig.selectedAccount, r82, true, false);
        r12.customReplyName = LocaleController.getString(R.string.FromYou);
        r12.resetLayout();
        r12.eventId = 1;
        ChatMessageCell r11 = new ChatMessageCell(r18, UserConfig.selectedAccount);
        this.messageCell = r11;
        r11.isChat = false;
        r11.setFullyDraw(true);
        r11.setMessageObject(r12, null, false, false, false);
        addView(r11, LayoutHelper.createFrame(-1, -2, 16));
        updateActions();
    }

    private void applyActions(List<SwipeAction> r6) {
        this.actions.clear();
        this.actions.addAll(r6);
        this.looped = ExteraConfig.getSwipeActionsLoop();
        this.selected = 0;
        this.helper.start(this.actions);
        this.helper.select(0);
        if (this.actions.contains(SwipeAction.REACTION) == false) goto L7;
        String r62 = SwipeAction.quickReactionEmoticon(UserConfig.selectedAccount);
        if (r62 == null) goto L7;
        ReactionsLayoutInBubble.VisibleReaction r63 = ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(r62);
        this.helper.setReaction(UserConfig.selectedAccount, r63.emojicon, r63.documentId);
    L7:
        restartCycle();
    }

    private void drawWallpaper(Canvas r6, Drawable r7) {
        if ((r7 instanceof ColorDrawable) == false) goto L5;
    L17:
        r7.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        if ((r7 instanceof BackgroundGradientDrawable) == false) goto L21;
        this.backgroundGradientDisposable = ((BackgroundGradientDrawable) r7).drawExactBoundsSize(r6, this);
        return;
    L21:
        r7.draw(r6);
        return;
    L5:
        if ((r7 instanceof GradientDrawable) == true) goto L17;
        if ((r7 instanceof MotionBackgroundDrawable) == true) goto L17;
        if ((r7 instanceof BitmapDrawable) == false) goto L23;
        r6.save();
        if (((BitmapDrawable) r7).getTileModeX() != Shader.TileMode.REPEAT) goto L14;
        float r0 = 2.0f / AndroidUtilities.density;
        r6.scale(r0, r0);
        r7.setBounds(0, 0, (int) Math.ceil(getMeasuredWidth() / r0), (int) Math.ceil(getMeasuredHeight() / r0));
    L15:
        r7.draw(r6);
        r6.restore();
        return;
    L14:
        float r02 = Math.max(getMeasuredWidth() / r7.getIntrinsicWidth(), getMeasuredHeight() / r7.getIntrinsicHeight());
        int r2 = (int) Math.ceil(r7.getIntrinsicWidth() * r02);
        int r03 = (int) Math.ceil(r7.getIntrinsicHeight() * r02);
        r6.clipRect(0, 0, r2, getMeasuredHeight());
        r7.setBounds((getMeasuredWidth() - r2) / 2, (getMeasuredHeight() - r03) / 2, ((getMeasuredWidth() - r2) / 2) + r2, ((getMeasuredHeight() - r03) / 2) + r03);
        goto L15
    }

    private /* synthetic */ void lambda$restartCycle$0(int r5, ValueAnimator r6) {
        float r62 = ((Float) r6.getAnimatedValue()).floatValue();
        float r0 = 1.0f / (r5 + 2);
        if (r62 >= r0) goto L5;
        this.slide = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(r62 / r0);
    L10:
        if (r62 >= r0) goto L12;
    L16:
        this.messageCell.setSlidingOffset((-this.slide) * AndroidUtilities.dp(72.0f));
        invalidate();
        return;
    L12:
        if (r62 > (1.0f - r0)) goto L16;
        int r52 = Math.max(0, Math.min(r5 - 1, (int) ((r62 - r0) / r0))) % this.actions.size();
        if (r52 == this.selected) goto L16;
        this.selected = r52;
        this.helper.select(r52);
        goto L16
    L5:
        float r2 = 1.0f - r0;
        if (r62 <= r2) goto L8;
        this.slide = 1.0f - CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation((r62 - r2) / r0);
        goto L10
    L8:
        this.slide = 1.0f;
        goto L10
    }

    private void restartCycle() {
        ValueAnimator r0 = this.cycle;
        if (r0 == null) goto L5;
        r0.cancel();
        this.cycle = null;
    L5:
        this.slide = 0.0f;
        this.messageCell.setSlidingOffset(0.0f);
        if (this.actions.isEmpty() == false) goto L8;
    L18:
        invalidate();
        return;
    L8:
        if (isAttachedToWindow() == false) goto L18;
        int r02 = this.actions.size();
        if (this.looped == false) goto L15;
        int r2 = 1;
        if (this.actions.size() <= 1) goto L15;
    L16:
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.cycle = r1;
        r1.setDuration((r0 + 2) * 1100);
        this.cycle.setInterpolator(new LinearInterpolator());
        this.cycle.setRepeatCount(-1);
        this.cycle.addUpdateListener(new SwipeActionsCell$$ExternalSyntheticLambda0(this, r02 + r2));
        this.cycle.addListener(new AnonymousClass1(this));
        this.cycle.start();
        return;
    L15:
        r2 = 0;
        goto L16
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r9) {
        super.dispatchDraw(r9);
        if (this.actions.isEmpty() == false) goto L5;
        return;
    L5:
        float r4 = (-this.slide) * AndroidUtilities.dp(72.0f);
        this.helper.draw(r9, r4, false, this.messageCell.getTop() + (this.messageCell.getMeasuredHeight() / 2.0f), this.messageCell.getBackgroundDrawableRight() + r4);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSetPressed(boolean r1) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r1) {
        return false;
    }

    @Override // com.exteragram.messenger.preferences.components.CustomPreferenceCell
    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof SwipeActionsCell) == true) goto L8;
        return false;
    L8:
        return this.actions.equals(((SwipeActionsCell) r2).actions);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.cycle != null) goto L6;
        restartCycle();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator r0 = this.cycle;
        if (r0 == null) goto L5;
        r0.cancel();
        this.cycle = null;
    L5:
        this.helper.detach();
        BackgroundGradientDrawable.Disposable r02 = this.backgroundGradientDisposable;
        if (r02 == null) goto L9;
        r02.dispose();
        this.backgroundGradientDisposable = null;
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r5) {
        if (Theme.isCurrentThemeMonet() == false) goto L5;
        Drawable r0 = this.monetBackgroundDrawable;
    L7:
        if (r0 == this.backgroundDrawable) goto L13;
        if (r0 == null) goto L13;
        BackgroundGradientDrawable.Disposable r1 = this.backgroundGradientDisposable;
        if (r1 == null) goto L12;
        r1.dispose();
        this.backgroundGradientDisposable = null;
    L12:
        this.backgroundDrawable = r0;
    L13:
        Drawable r02 = this.backgroundDrawable;
        if (r02 != null) goto L16;
    L20:
        this.shadowDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.shadowDrawable.draw(r5);
        return;
    L16:
        if (r02 != this.monetBackgroundDrawable) goto L18;
        int r12 = 150;
    L19:
        r02.setAlpha(r12);
        drawWallpaper(r5, this.backgroundDrawable);
        goto L20
    L18:
        r12 = 255;
        goto L19
    L5:
        r0 = Theme.getCachedWallpaperNonBlocking();
        goto L7
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r1) {
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(150.0f), TLObject.FLAG_30));
    }

    public void updateActions() {
        List<SwipeAction> r0 = SwipeAction.enabled();
        if (r0.equals(this.actions) == false) goto L8;
        if (this.looped != ExteraConfig.getSwipeActionsLoop()) goto L8;
        return;
    L8:
        if (this.cycle != null) goto L10;
    L16:
        applyActions(r0);
        return;
    L10:
        if (this.actions.isEmpty() == true) goto L16;
        if (r0.isEmpty() == true) goto L16;
        this.pending = r0;
    }
}
