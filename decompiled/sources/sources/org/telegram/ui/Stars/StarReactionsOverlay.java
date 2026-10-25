package org.telegram.ui.Stars;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.BaseCell;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.GradientClip;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes7.dex */
public class StarReactionsOverlay extends View {
    private float accumulatedRippleIntensity;
    private BaseCell cell;
    private final ChatActivity chatActivity;
    private final RectF clickBounds;
    private final GradientClip clip;
    private final AnimatedTextView.AnimatedTextDrawable counter;
    private final AnimatedFloat counterAlpha;
    private boolean counterShown;
    private final int[] effectAssets;
    private final ArrayList<RLottieDrawable> effects;
    private float focus;
    private ValueAnimator focusAnimator;
    public boolean hidden;
    private Runnable hideCounterRunnable;
    private long lastRippleTime;
    private final Runnable longPressRunnable;
    private int messageId;
    private final int[] pos;
    private final int[] pos2;
    private boolean pressed;
    private final RectF reactionBounds;
    private final Paint redPaint;
    private final Paint shadowPaint;

    /* renamed from: org.telegram.ui.Stars.StarReactionsOverlay$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ StarReactionsOverlay this$0;
        final /* synthetic */ float val$dst;
        final /* synthetic */ Runnable val$whenDone;

        public AnonymousClass1(StarReactionsOverlay r1, float r2, Runnable r3) {
            this.this$0 = r1;
            this.val$dst = r2;
            this.val$whenDone = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            StarReactionsOverlay.m19098$$Nest$fputfocus(this.this$0, this.val$dst);
            this.this$0.invalidate();
            if (r3 != StarReactionsOverlay.m19097$$Nest$fgetfocusAnimator(this.this$0)) goto L8;
            Runnable r2 = this.val$whenDone;
            if (r2 == null) goto L9;
            r2.run();
            return;
        L9:
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$5Spg5Ho8ng3DLDXe3I6d4t9tGTw(StarReactionsOverlay r0) {
        r0.lambda$hide$4();
    }

    /* renamed from: $r8$lambda$9RCbt42xC5rHdU2l1Lk-JzdIIxA, reason: not valid java name */
    public static /* synthetic */ void m19094$r8$lambda$9RCbt42xC5rHdU2l1LkJzdIIxA(StarReactionsOverlay r0, ChatActivity r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$JhazEXlEBNn1NLP9smaBj6o_-ko, reason: not valid java name */
    public static /* synthetic */ void m19095$r8$lambda$JhazEXlEBNn1NLP9smaBj6o_ko(StarReactionsOverlay r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$pmtxxGSzB-aGk_YbKPEP77u9gy4, reason: not valid java name */
    public static /* synthetic */ void m19096$r8$lambda$pmtxxGSzBaGk_YbKPEP77u9gy4(StarReactionsOverlay r0, ValueAnimator r1) {
        r0.lambda$focusTo$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$zG1XLreYjVcPgWsXQiAvBDXzSeY(StarReactionsOverlay r0, StarsController r1, MessageObject r2, long r3) {
        r0.lambda$checkBalance$2(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetfocusAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m19097$$Nest$fgetfocusAnimator(StarReactionsOverlay r0) {
        return r0.focusAnimator;
    }

    /* renamed from: -$$Nest$fputfocus, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19098$$Nest$fputfocus(StarReactionsOverlay r0, float r1) {
        r0.focus = r1;
    }

    public StarReactionsOverlay(ChatActivity r9) {
        super(r9.getContext());
        this.pos = new int[2];
        this.pos2 = new int[2];
        this.reactionBounds = new RectF();
        this.clickBounds = new RectF();
        this.shadowPaint = new Paint();
        this.redPaint = new Paint();
        this.counterAlpha = new AnimatedFloat(this, 0, 420, CubicBezierInterpolator.EASE_OUT_QUINT);
        AnimatedTextView.AnimatedTextDrawable r8 = new AnimatedTextView.AnimatedTextDrawable();
        this.counter = r8;
        this.clip = new GradientClip();
        this.effects = new ArrayList();
        this.effectAssets = new int[]{R.raw.star_reaction_effect1, R.raw.star_reaction_effect2, R.raw.star_reaction_effect3, R.raw.star_reaction_effect4, R.raw.star_reaction_effect5};
        this.chatActivity = r9;
        r8.setCallback(this);
        r8.setHacks(false, true, true);
        r8.setTextSize(AndroidUtilities.dp(40.0f));
        r8.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
        r8.setShadowLayer(AndroidUtilities.dp(12.0f), 0.0f, AndroidUtilities.dp(3.5f), 0);
        r8.setOverrideFullWidth(AndroidUtilities.displaySize.x);
        r8.setTextColor(-1);
        r8.setGravity(17);
        this.hideCounterRunnable = new StarReactionsOverlay$$ExternalSyntheticLambda2(this);
        this.longPressRunnable = new StarReactionsOverlay$$ExternalSyntheticLambda3(this, r9);
    }

    private void checkBalance() {
        if (getMessageObject() == null) goto L18;
        MessageObject r3 = getMessageObject();
        StarsController r2 = StarsController.getInstance(this.chatActivity.getCurrentAccount());
        long r4 = r2.getPendingPaidReactions(r3);
        if (r2.balanceAvailable() == true) goto L7;
        return;
    L7:
        if (r2.getBalance(false) >= r4) goto L20;
        StarsController.getInstance(this.chatActivity.getCurrentAccount()).undoPaidReaction();
        long r6 = this.chatActivity.getDialogId();
        ChatActivity r8 = this.chatActivity;
        if (r6 < 0) goto L12;
        String r0 = UserObject.getForcedFirstName(r8.getMessagesController().getUser(Long.valueOf(r6)));
    L11:
        String r10 = r0;
        new StarsIntroActivity.StarsNeededSheet(this.chatActivity.getContext(), this.chatActivity.getResourceProvider(), r4, 5, r10, new StarReactionsOverlay$$ExternalSyntheticLambda1(this, r2, r3, r4), 0).show();
        return;
    L12:
        TLRPC.Chat r02 = r8.getMessagesController().getChat(Long.valueOf(-r6));
        if (r02 != null) goto L15;
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L11
    L15:
        r0 = r02.title;
        goto L11
    L20:
        return;
    }

    private MessageObject getMessageObject() {
        BaseCell r1 = this.cell;
        if ((r1 instanceof ChatMessageCell) == false) goto L7;
        return ((ChatMessageCell) r1).getPrimaryMessageObject();
    L7:
        if ((r1 instanceof ChatActionCell) == true) goto L9;
        return null;
    L9:
        return ((ChatActionCell) r1).getMessageObject();
    }

    private /* synthetic */ void lambda$checkBalance$2(StarsController r9, MessageObject r10, long r11) {
        r9.sendPaidReaction(r10, this.chatActivity, r11, true, true, null);
    }

    private /* synthetic */ void lambda$focusTo$3(ValueAnimator r1) {
        this.focus = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$hide$4() {
        setMessageCell(null);
        clearEffects();
    }

    private /* synthetic */ void lambda$new$0() {
        this.counterShown = false;
        invalidate();
        checkBalance();
        hide();
    }

    private /* synthetic */ void lambda$new$1(ChatActivity r19) {
        BaseCell r1 = this.cell;
        if (r1 == null) goto L41;
        boolean r2 = false;
        r1.performHapticFeedback(0);     // Catch: Exception -> L37
    L7:
        onTouchEvent(MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0));
        BaseCell r12 = this.cell;
        ArrayList<TLRPC.MessageReactor> r4 = null;
        if ((r12 instanceof ChatMessageCell) == false) goto L19;
        MessageObject r13 = ((ChatMessageCell) r12).getPrimaryMessageObject();
        if (r13 == null) goto L42;
        TLRPC.Message r3 = r13.messageOwner;
        if (r3 == null) goto L17;
        TLRPC.TL_messageReactions r32 = r3.reactions;
        if (r32 == null) goto L17;
        r4 = r32.top_reactors;
    L17:
        MessageObject r11 = r13;
        ArrayList<TLRPC.MessageReactor> r122 = r4;
        StarsController.getInstance(r11.currentAccount).commitPaidReaction();
        TLRPC.ChatFull r14 = r19.getCurrentChatInfo();
        Context r6 = getContext();
        int r7 = r19.getCurrentAccount();
        long r8 = r19.getDialogId();
        if (r14 != null) goto L31;
    L34:
        r2 = true;
    L33:
        boolean r132 = r2;
        StarsReactionsSheet r5 = new StarsReactionsSheet(r6, r7, r8, r19, r11, r122, r132, false, 0, r19.getResourceProvider());
        r5.setMessageCell(r19, r11.getId(), this.cell);
        r5.show();
        return;
    L31:
        if (r14.paid_reactions_available == false) goto L33;
    L42:
        return;
    L19:
        if ((r12 instanceof ChatActionCell) == false) goto L40;
        r13 = ((ChatActionCell) r12).getMessageObject();
        if (r13 == null) goto L43;
        TLRPC.Message r33 = r13.messageOwner;
        if (r33 == null) goto L17;
        TLRPC.TL_messageReactions r34 = r33.reactions;
        if (r34 == null) goto L17;
        r4 = r34.top_reactors;
        goto L17
    L43:
        return;
    L40:
        return;
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
    public void dispatchDraw(Canvas r21) {
        BaseCell r2 = this.cell;
        if ((r2 instanceof ChatMessageCell) == false) goto L8;
        if (((ChatMessageCell) r2).isCellAttachedToWindow() == false) goto L62;
    L12:
        MessageObject r22 = getMessageObject();
        if (r22 == null) goto L15;
        int r23 = r22.getId();
    L17:
        if (r23 == this.messageId) goto L20;
        setMessageCell(null);
        return;
    L20:
        ReactionsLayoutInBubble r24 = getReactionsLayoutInBubble();
        if (r24 != null) goto L24;
        setMessageCell(null);
        return;
    L24:
        float r4 = AndroidUtilities.lerp(1.0f, 1.8f, this.focus);
        float r8 = this.chatActivity.getClipTop();
        float r9 = this.chatActivity.getClipBottom();
        r21.save();
        r21.clipRect(0.0f, r8 * (1.0f - this.focus), getWidth(), getHeight() - (r9 * (1.0f - this.focus)));
        getLocationInWindow(this.pos2);
        this.cell.getLocationInWindow(this.pos);
        int[] r82 = this.pos;
        r82[1] = r82[1] + ((int) this.chatActivity.drawingChatListViewYoffset);
        r21.save();
        ReactionsLayoutInBubble.ReactionButton r83 = r24.getReactionButton("stars");
        if (r83 == null) goto L27;
        int[] r13 = this.pos;
        int r14 = r13[0];
        int[] r15 = this.pos2;
        int r142 = ((r14 - r15[0]) + r24.x) + r83.x;
        int r7 = ((r13[1] - r15[1]) + r24.y) + r83.y;
        float r132 = r142;
        float r152 = r7;
        float r17 = 12.0f;
        this.reactionBounds.set(r132, r152, r142 + r83.width, r7 + r83.height);
        AndroidUtilities.scaleRect(this.reactionBounds, r4, (r83.width * 0.1f) + r132, (r83.height / 2.0f) + r152);
        this.shadowPaint.setColor(0);
        int r18 = 0;
        this.shadowPaint.setShadowLayer(AndroidUtilities.dp(12.0f), 0.0f, AndroidUtilities.dp(3.0f), Theme.multAlpha(1426063360, this.focus));
        RectF r25 = this.reactionBounds;
        r21.drawRoundRect(r25, r25.height() / 2.0f, this.reactionBounds.height() / 2.0f, this.shadowPaint);
        r21.scale(r4, r4, r132 + (r83.width * 0.1f), r152 + (r83.height / 2.0f));
        Integer r26 = Integer.valueOf(r83.reaction.hashCode());
    L28:
        int r72 = this.pos[r18];
        int[] r11 = this.pos2;
        r21.translate(r72 - r11[r18], (r3[1] - r11[1]) + this.cell.getPaddingTop());
        BaseCell r3 = this.cell;
        if ((r3 instanceof ChatMessageCell) == false) goto L32;
        ChatMessageCell r32 = (ChatMessageCell) r3;
        r32.setScrimReaction(null);
        r32.drawReactionsLayout(r21, 1.0f, r26);
        r32.drawReactionsLayoutOverlay(r21, 1.0f);
        r32.setScrimReaction(r26);
    L34:
        r21.restore();
        r21.restore();
        if (r83 == null) goto L55;
        this.clickBounds.set(this.reactionBounds);
        this.clickBounds.inset(-AndroidUtilities.dp(42.0f), -AndroidUtilities.dp(42.0f));
        int r27 = (int) (AndroidUtilities.dp(90.0f) * r4);
        int r33 = r18;
    L38:
        if (r33 >= this.effects.size()) goto L44;
        RLottieDrawable r5 = this.effects.get(r33);
        if (r5.getCurrentFrame() < r5.getFramesCount()) goto L42;
        this.effects.remove(r33);
        r33 = r33 - 1;
    L43:
        r33 = r33 + 1;
        goto L38
    L42:
        float r133 = r27 / 2.0f;
        r5.setBounds((int) ((this.reactionBounds.left + (AndroidUtilities.dp(15.0f) * r4)) - r133), (int) (this.reactionBounds.centerY() - r133), (int) ((this.reactionBounds.left + (AndroidUtilities.dp(15.0f) * r4)) + r133), (int) (this.reactionBounds.centerY() + r133));
        r5.setAlpha((int) (this.focus * 255.0f));
        r5.draw(r21);
        goto L43
    L44:
        float r28 = this.reactionBounds.centerX();
        float r34 = this.reactionBounds.top - AndroidUtilities.dp(36.0f);
        r21.save();
        float r42 = this.counterAlpha.set(this.counterShown);
        if (this.counterShown == false) goto L48;
        int r52 = AndroidUtilities.dp(60.0f);
    L49:
        r21.translate(0.0f, r52 * (1.0f - r42));
        if (this.counterShown == false) goto L52;
        float r53 = 1.8f;
    L53:
        float r54 = AndroidUtilities.lerp(r53, 1.0f, r42);
        r21.scale(r54, r54, r28, r34);
        this.counter.setAlpha((int) (255.0f * r42));
        this.counter.setShadowLayer(AndroidUtilities.dp(r17), 0.0f, AndroidUtilities.dp(3.5f), Theme.multAlpha(-1442840576, r42));
        this.counter.setBounds(r28 - AndroidUtilities.dp(100.0f), this.reactionBounds.top - AndroidUtilities.dp(48.0f), r28 + AndroidUtilities.dp(100.0f), this.reactionBounds.top - AndroidUtilities.dp(24.0f));
        this.counter.draw(r21);
        r21.restore();
        goto L55
    L52:
        r53 = 1.3f;
        goto L53
    L48:
        r52 = -AndroidUtilities.dp(30.0f);
    L55:
        if (this.counterShown == true) goto L57;
        checkBalance();
    L57:
        invalidate();
        return;
    L32:
        if ((r3 instanceof ChatActionCell) == false) goto L34;
        ChatActionCell r35 = (ChatActionCell) r3;
        r35.setScrimReaction(null);
        r35.drawReactionsLayout(r21, true, r26);
        r35.drawReactionsLayoutOverlay(r21, true);
        r35.setScrimReaction(r26);
        goto L34
    L27:
        r18 = 0;
        r17 = 12.0f;
        r26 = null;
        goto L28
    L15:
        r23 = 0;
        goto L17
    L62:
        return;
    L8:
        if ((r2 instanceof ChatActionCell) == false) goto L12;
        if (((ChatActionCell) r2).isCellAttachedToWindow() == true) goto L12;
    }

    public void focusTo(float r4, Runnable r5) {
        ValueAnimator r0 = this.focusAnimator;
        if (r0 == null) goto L5;
        this.focusAnimator = null;
        r0.cancel();
    L5:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.focus, r4});
        this.focusAnimator = r02;
        r02.addUpdateListener(new StarReactionsOverlay$$ExternalSyntheticLambda0(this));
        this.focusAnimator.addListener(new AnonymousClass1(this, r4, r5));
        this.focusAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.focusAnimator.setDuration(320);
        this.focusAnimator.start();
    }

    public ReactionsLayoutInBubble getReactionsLayoutInBubble() {
        BaseCell r1 = this.cell;
        if ((r1 instanceof ChatMessageCell) == false) goto L7;
        return ((ChatMessageCell) r1).reactionsLayoutInBubble;
    L7:
        if ((r1 instanceof ChatActionCell) == true) goto L9;
        return null;
    L9:
        return ((ChatActionCell) r1).reactionsLayoutInBubble;
    }

    public void hide() {
        this.hidden = true;
        AndroidUtilities.cancelRunOnUIThread(this.hideCounterRunnable);
        this.counter.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.counterShown = false;
        invalidate();
        focusTo(0.0f, new StarReactionsOverlay$$ExternalSyntheticLambda5(this));
    }

    public boolean isShowing(MessageObject r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.getId() != this.messageId) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r7) {
        if (this.cell != null) goto L5;
    L30:
        return false;
    L5:
        if (this.hidden == true) goto L30;
        ReactionsLayoutInBubble r0 = getReactionsLayoutInBubble();
        if (r0 != null) goto L11;
        return false;
    L11:
        if (r7.getAction() != 0) goto L19;
        if (this.clickBounds.contains(r7.getX(), r7.getY()) == false) goto L29;
        this.pressed = true;
        ReactionsLayoutInBubble.ReactionButton r72 = r0.getReactionButton("stars");
        if (r72 == null) goto L17;
        r72.bounce.setPressed(true);
    L17:
        AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
        AndroidUtilities.runOnUIThread(this.longPressRunnable, ViewConfiguration.getLongPressTimeout());
    L29:
        return this.pressed;
    L19:
        if (r7.getAction() != 1) goto L21;
    L22:
        ReactionsLayoutInBubble.ReactionButton r02 = r0.getReactionButton("stars");
        if (r7.getAction() != 1) goto L25;
        tap(r7.getX(), r7.getY(), true, true);
    L25:
        if (r02 == null) goto L27;
        r02.bounce.setPressed(false);
    L27:
        this.pressed = false;
        AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
        goto L29
    L21:
        if (r7.getAction() != 3) goto L29;
        goto L22
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

    public void setMessageCell(BaseCell r4) {
        BaseCell r0 = this.cell;
        if (r0 != r4) goto L6;
        return;
    L6:
        if ((r0 instanceof ChatMessageCell) == false) goto L9;
        ((ChatMessageCell) r0).setScrimReaction(null);
        ((ChatMessageCell) this.cell).setInvalidateListener(null);
        this.cell.invalidate();
    L11:
        this.cell = r4;
        if (getMessageObject() != null) goto L14;
        int r42 = 0;
    L15:
        this.messageId = r42;
        BaseCell r43 = this.cell;
        if ((r43 instanceof ChatMessageCell) == false) goto L19;
        r43.invalidate();
        ((ChatMessageCell) this.cell).setInvalidateListener(new StarReactionsOverlay$$ExternalSyntheticLambda4(this));
    L21:
        invalidate();
        return;
    L19:
        if ((r43 instanceof ChatActionCell) == false) goto L21;
        r43.invalidate();
        ((ChatActionCell) this.cell).setInvalidateListener(new StarReactionsOverlay$$ExternalSyntheticLambda4(this));
        goto L21
    L14:
        r42 = getMessageObject().getId();
        goto L15
    L9:
        if ((r0 instanceof ChatActionCell) == false) goto L11;
        ((ChatActionCell) r0).setScrimReaction(null);
        ((ChatActionCell) this.cell).setInvalidateListener(null);
        this.cell.invalidate();
        goto L11
    }

    public void show() {
        this.hidden = false;
        focusTo(1.0f, null);
    }

    public void tap(float r11, float r12, boolean r13, boolean r14) {
        if (this.cell != null) goto L5;
        return;
    L5:
        if (this.hidden == true) goto L39;
        MessageObject r2 = getMessageObject();
        ReactionsLayoutInBubble r0 = getReactionsLayoutInBubble();
        if (r2 == null) goto L36;
        if (r0 == null) goto L40;
        StarsController r9 = StarsController.getInstance(this.chatActivity.getCurrentAccount());
        playEffect();
        ReactionsLayoutInBubble.ReactionButton r02 = r0.getReactionButton("stars");
        if (r02 == null) goto L15;
        r02.startAnimation();
    L15:
        if (r13 == true) goto L33;
    L19:
        this.counter.cancelAnimation();
        this.counter.setText("+" + r9.getPendingPaidReactions(r2));
        this.counterShown = true;
        AndroidUtilities.cancelRunOnUIThread(this.hideCounterRunnable);
        AndroidUtilities.runOnUIThread(this.hideCounterRunnable, 1500);
        if (r14 == false) goto L37;
        long r132 = System.currentTimeMillis();
        long r3 = r132 - this.lastRippleTime;
        float r4 = this.accumulatedRippleIntensity;
        if (r3 >= 100) goto L24;
        this.accumulatedRippleIntensity = r4 + 0.5f;
        return;
    L24:
        this.accumulatedRippleIntensity = r4 * Utilities.clamp(1.0f - (((r132 - r1) - 100) / 200.0f), 1.0f, 0.0f);
        if (getMeasuredWidth() == 0) goto L27;
    L29:
        getLocationInWindow(this.pos2);
    L30:
        int[] r1 = this.pos2;
        LaunchActivity.makeRipple(r1[0] + r11, r1[1] + r12, Utilities.clamp(this.accumulatedRippleIntensity, 0.9f, 0.3f));
        this.accumulatedRippleIntensity = 0.0f;
        this.lastRippleTime = r132;
        return;
    L27:
        if (this.chatActivity.getLayoutContainer() == null) goto L29;
        this.chatActivity.getLayoutContainer().getLocationInWindow(this.pos2);
        goto L30
    L37:
        return;
    L33:
        performHapticFeedback(3, 1);     // Catch: Exception -> L32
    L18:
        StarsController.getInstance(this.chatActivity.getCurrentAccount()).sendPaidReaction(r2, this.chatActivity, 1, true, false, null);
        goto L19
    L40:
        return;
    L36:
        return;
    }
}
