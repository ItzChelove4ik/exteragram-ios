package org.telegram.ui.Components.conference.message;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import me.vkryl.android.util.ClickHelper;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.voip.GroupCallMessage;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.Components.voip.CellFlickerDrawable;

/* loaded from: classes7.dex */
public class GroupCallMessageCell extends ViewGroup implements ClickHelper.Delegate, NotificationCenter.NotificationCenterDelegate, FactorAnimator.Target {
    private static final Rect tmpRect = null;
    private AnimatedEmojiDrawable animatedReactionDrawable;
    private final ImageReceiver animatedReactionReceiver;
    private final ImageReceiver avatarReceiver;
    private final Paint bgPaint;
    private View blurRoot;
    private final ClickHelper clickHelper;
    private Delegate delegate;
    private final Paint errPaint;
    private final CellFlickerDrawable flickerDrawable;
    private GroupCallMessage groupCallMessage;
    private final BoolAnimator isSendDelayedAnimator;
    private final BoolAnimator isSendErrorAnimator;
    private Layout layout;
    private boolean layoutInvalidated;
    private ReactionsLayoutInBubble.VisibleReaction messageReaction;
    private final SpoilersTextView messageTextView;
    private final Runnable onMessageStateUpdateListener;
    private RenderNode renderNode;
    private float renderNodeScale;
    private final ClickableSpan senderNameSpan;
    private final RectF tmpRectF;

    /* renamed from: org.telegram.ui.Components.conference.message.GroupCallMessageCell$1, reason: invalid class name */
    public class AnonymousClass1 extends ClickableSpan {
        final /* synthetic */ GroupCallMessageCell this$0;

        public AnonymousClass1(GroupCallMessageCell r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r2) {
            if (GroupCallMessageCell.m13701$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return;
        L5:
            if (GroupCallMessageCell.m13702$$Nest$fgetgroupCallMessage(this.this$0) == null) goto L9;
            Delegate r22 = GroupCallMessageCell.m13701$$Nest$fgetdelegate(this.this$0);
            GroupCallMessageCell r1 = this.this$0;
            r22.didClickSenderName(r1, GroupCallMessageCell.m13702$$Nest$fgetgroupCallMessage(r1));
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
        }
    }

    public interface Delegate {
        void didClickAvatar(GroupCallMessageCell r1, GroupCallMessage r2, float r3, float r4);

        void didClickSenderName(GroupCallMessageCell r1, GroupCallMessage r2);
    }

    public static class Layout {
        public final RectF avatar;
        public final RectF bubble;
        public final Path bubblePath;
        public final RectF reaction;
        public final PointF text;
        public int viewHeight;
        public int viewWidth;

        private Layout() {
            this.bubble = new RectF();
            this.bubblePath = new Path();
            this.avatar = new RectF();
            this.reaction = new RectF();
            this.text = new PointF();
        }

        public static Layout build(int r7, int r8, int r9, SpoilersTextView r10, ReactionsLayoutInBubble.VisibleReaction r11) {
            r10.measure(View.MeasureSpec.makeMeasureSpec(((r7 - r8) - r9) - AndroidUtilities.dp(44.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
            float r82 = r10.getMeasuredWidth();
            if (r11 != null) goto L5;
            int r112 = ((int) Math.ceil(r82)) + AndroidUtilities.dp(44.0f);
        L6:
            int r1 = Math.max(AndroidUtilities.dp(28.0f), r10.getMeasuredHeight() + AndroidUtilities.dp(8.0f));
            Layout r2 = new Layout();
            r2.viewWidth = r7;
            r2.viewHeight = r1;
            r2.bubble.set(0.0f, 0.0f, r112, r1);
            r2.bubble.offset((r7 - r112) / 2.0f, 0.0f);
            r2.bubblePath.addRoundRect(r2.bubble, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), Path.Direction.CW);
            if (r10.getLayout().getParagraphDirection(0) != (-1)) goto L9;
            boolean r72 = true;
        L10:
            r2.avatar.set(0.0f, 0.0f, AndroidUtilities.dp(22.0f), AndroidUtilities.dp(22.0f));
            RectF r113 = r2.avatar;
            if (r72 == false) goto L13;
            RectF r4 = r2.bubble;
            r113.offset(r4.right, r4.top);
            r2.avatar.offset((-AndroidUtilities.dp(4.0f)) - r2.avatar.width(), AndroidUtilities.dp(3.0f));
        L14:
            r2.reaction.set(0.0f, 0.0f, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f));
            RectF r92 = r2.reaction;
            if (r72 == false) goto L17;
            r92.offset(r2.bubble.left + AndroidUtilities.dp(5.0f), 0.0f);
        L18:
            r2.reaction.inset(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f));
            r2.text.set(0.0f, (r2.bubble.top + AndroidUtilities.dp(19.0f)) - r10.getLayout().getLineBaseline(0));
            PointF r93 = r2.text;
            if (r72 == false) goto L22;
            r93.offset((r2.bubble.right - AndroidUtilities.dp(32.0f)) - r82, 0.0f);
            return r2;
        L22:
            r93.offset(r2.bubble.left + AndroidUtilities.dp(32.0f), 0.0f);
            return r2;
        L17:
            r92.offset(r2.bubble.right - AndroidUtilities.dp(33.0f), 0.0f);
            goto L18
        L13:
            RectF r42 = r2.bubble;
            r113.offset(r42.left, r42.top);
            r2.avatar.offset(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(3.0f));
            goto L14
        L9:
            r72 = false;
            goto L10
        L5:
            r112 = AndroidUtilities.dp(70.0f) + ((int) Math.ceil(r82));
            goto L6
        }
    }

    public static class VH extends RecyclerView.ViewHolder {
        public final GroupCallMessageCell cell;

        public VH(GroupCallMessageCell r1) {
            super(r1);
            this.cell = r1;
        }
    }

    public static /* synthetic */ void $r8$lambda$Uu7GpJ8DrFy_w_Kqj5K3woxakvM(GroupCallMessageCell r0) {
        r0.lambda$new$0();
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m13701$$Nest$fgetdelegate(GroupCallMessageCell r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetgroupCallMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallMessage m13702$$Nest$fgetgroupCallMessage(GroupCallMessageCell r0) {
        return r0.groupCallMessage;
    }

    static {
        tmpRect = new Rect();
    }

    public GroupCallMessageCell(Context r8) {
        super(r8);
        CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.isSendDelayedAnimator = new BoolAnimator(0, this, r3, 320);
        this.isSendErrorAnimator = new BoolAnimator(1, this, r3, 320);
        this.clickHelper = new ClickHelper(this);
        Paint r7 = new Paint(1);
        this.bgPaint = r7;
        Paint r1 = new Paint(1);
        this.errPaint = r1;
        CellFlickerDrawable r32 = new CellFlickerDrawable();
        this.flickerDrawable = r32;
        this.onMessageStateUpdateListener = new GroupCallMessageCell$$ExternalSyntheticLambda0(this);
        this.senderNameSpan = new AnonymousClass1(this);
        this.tmpRectF = new RectF();
        SpoilersTextView r4 = new SpoilersTextView(r8);
        this.messageTextView = r4;
        r4.setDisablePaddingsOffset(true);
        r4.setTextSize(14.0f);
        r4.setTextColor(-1);
        r4.setLinkTextColor(-11683585);
        r4.setHintTextColor(-1);
        addView(r4);
        r7.setColor(-13946053);
        r1.setColor(Opcodes.V_PREVIEW);
        r1.setAlpha(0);
        ImageReceiver r82 = new ImageReceiver(this);
        this.avatarReceiver = r82;
        r82.setRoundRadius(AndroidUtilities.dp(11.0f));
        r32.setStrokeWidth(AndroidUtilities.dp(1.0f));
        this.animatedReactionReceiver = new ImageReceiver(this);
        setWillNotDraw(false);
    }

    public static CharSequence concat(CharSequence r3, CharSequence r4) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        boolean r1 = isRtlByFirstStrong(r3);
        boolean r2 = isRtlByFirstStrong(r4);
        if (r1 == r2) goto L8;
        if (r2 == false) goto L6;
        char r12 = 8295;
    L7:
        r0.append(r12);
        r0.append(r3);
        r0.append(8297);
    L9:
        r0.append("  ");
        r0.append(r4);
        return r0;
    L6:
        r12 = 8294;
        goto L7
    L8:
        r0.append(r3);
        goto L9
    }

    private int getClickTarget(float r5, float r6) {
        Layout r0 = this.layout;
        if (r0 != null) goto L5;
        return -1;
    L5:
        this.tmpRectF.set(r0.avatar);
        this.tmpRectF.inset(-AndroidUtilities.dp(5.0f), -AndroidUtilities.dp(5.0f));
        if (this.tmpRectF.contains(r5, r6) == false) goto L10;
        return 1;
    L10:
        if (this.layout.bubble.contains(r5, r6) == false) goto L13;
        return 0;
    L13:
        return -1;
    }

    private static boolean isRtlByFirstStrong(CharSequence r6) {
        int r0 = r6.length();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L12;
        int r3 = Character.codePointAt(r6, r2);
        r2 = r2 + Character.charCount(r3);
        byte r32 = Character.getDirectionality(r3);
        if (r32 == 0) goto L12;
        if (r32 == 1) goto L11;
        if (r32 != 2) goto L3;
    L11:
        return true;
    L12:
        return false;
    }

    private /* synthetic */ void lambda$new$0() {
        onMessageStateUpdate(true);
    }

    private void onMessageStateUpdate(boolean r3) {
        GroupCallMessage r0 = this.groupCallMessage;
        if (r0 == null) goto L6;
        this.isSendDelayedAnimator.setValue(r0.isSendDelayed(), r3);
        this.isSendErrorAnimator.setValue(this.groupCallMessage.isSendError(), r3);
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.emojiLoaded) goto L6;
        invalidate();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        Layout r0 = this.layout;
        if (r0 == null) goto L31;
        r5.drawPath(r0.bubblePath, this.bgPaint);
        if (Build.VERSION.SDK_INT < 29) goto L19;
        if (this.renderNode == null) goto L19;
        if (r5.isHardwareAccelerated() == false) goto L19;
        View r02 = this;
        float r1 = 0.0f;
    L13:
        if (r02 == this.blurRoot) goto L17;
        r1 = r1 + r02.getY();
        Object r03 = r02.getParent();
        if ((r03 instanceof View) == false) goto L32;
        r02 = (View) r03;
        goto L13
    L32:
        return;
    L17:
        r5.save();
        r5.clipPath(this.layout.bubblePath);
        r5.translate(0.0f, -r1);
        float r04 = this.renderNodeScale;
        r5.scale(r04, r04);
        r5.drawRenderNode(this.renderNode);
        r5.restore();
    L19:
        if (this.errPaint.getAlpha() <= 0) goto L22;
        r5.drawPath(this.layout.bubblePath, this.errPaint);
    L22:
        if (this.isSendDelayedAnimator.getFloatValue() <= 0.0f) goto L24;
        this.tmpRectF.set(this.layout.bubble);
        this.tmpRectF.inset(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f));
        this.flickerDrawable.draw(r5, this.tmpRectF, AndroidUtilities.dp(14.0f), null);
        invalidate();
    L24:
        super.dispatchDraw(r5);
        this.avatarReceiver.draw(r5);
        this.animatedReactionReceiver.draw(r5);
        AnimatedEmojiDrawable r4 = this.animatedReactionDrawable;
        if (r4 == null) goto L30;
        r4.draw(r5);
        return;
    L30:
        return;
    }

    @Override // me.vkryl.android.util.ClickHelper.Delegate
    public /* bridge */ /* synthetic */ long getLongPressDuration() {
        return super.getLongPressDuration();
    }

    public GroupCallMessage getMessage() {
        return this.groupCallMessage;
    }

    public float getReactionCenterX() {
        Layout r0 = this.layout;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.reaction.centerX();
    }

    public boolean isInsideBubble(float r1, float r2) {
        Layout r0 = this.layout;
        if (r0 != null) goto L7;
        return false;
    L7:
        return r0.bubble.contains(r1, r2);
    }

    @Override // me.vkryl.android.util.ClickHelper.Delegate
    public boolean needClickAt(View r1, float r2, float r3) {
        if (getClickTarget(r2, r3) != 1) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.avatarReceiver.onAttachedToWindow();
        this.animatedReactionReceiver.onAttachedToWindow();
        AnimatedEmojiDrawable r0 = this.animatedReactionDrawable;
        if (r0 == null) goto L5;
        r0.addView(this);
    L5:
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        GroupCallMessage r02 = this.groupCallMessage;
        if (r02 == null) goto L9;
        r02.subscribeToStateUpdates(this.onMessageStateUpdateListener);
        return;
    }

    @Override // me.vkryl.android.util.ClickHelper.Delegate
    public void onClickAt(View r2, float r3, float r4) {
        if (getClickTarget(r3, r4) != 1) goto L10;
        Delegate r22 = this.delegate;
        if (r22 == null) goto L11;
        GroupCallMessage r0 = this.groupCallMessage;
        if (r0 == null) goto L12;
        r22.didClickAvatar(this, r0, r3, r4);
        return;
    L12:
        return;
    L11:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.avatarReceiver.onDetachedFromWindow();
        this.animatedReactionReceiver.onDetachedFromWindow();
        AnimatedEmojiDrawable r0 = this.animatedReactionDrawable;
        if (r0 == null) goto L5;
        r0.removeView(this);
    L5:
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        GroupCallMessage r02 = this.groupCallMessage;
        if (r02 == null) goto L9;
        r02.unsubscribeFromStateUpdates(this.onMessageStateUpdateListener);
        return;
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        this.errPaint.setAlpha(Math.round(this.isSendErrorAnimator.getFloatValue() * 100.0f));
        this.flickerDrawable.setAlpha(Math.round(this.isSendDelayedAnimator.getFloatValue() * 220.0f));
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        Layout r12 = this.layout;
        if (r12 != null) goto L5;
        return;
    L5:
        int r13 = Math.round(r12.text.x);
        int r22 = Math.round(this.layout.text.y);
        SpoilersTextView r32 = this.messageTextView;
        r32.layout(r13, r22, r32.getMeasuredWidth() + r13, this.messageTextView.getMeasuredHeight() + r22);
    }

    @Override // android.view.View
    public void onMeasure(int r4, int r5) {
        int r42 = View.MeasureSpec.getSize(r4);
        Layout r52 = this.layout;
        if (r52 != null) goto L5;
    L8:
        Layout r53 = Layout.build(r42, getPaddingLeft(), getPaddingRight(), this.messageTextView, this.messageReaction);
        this.layout = r53;
        this.avatarReceiver.setImageCoords(r53.avatar);
        this.animatedReactionReceiver.setImageCoords(this.layout.reaction);
        if (this.animatedReactionDrawable == null) goto L11;
        RectF r54 = this.layout.reaction;
        Rect r0 = tmpRect;
        r54.round(r0);
        this.animatedReactionDrawable.setBounds(r0);
    L11:
        setMeasuredDimension(r42, this.layout.viewHeight);
        this.flickerDrawable.setParentWidth(Math.round(this.layout.bubble.width() + AndroidUtilities.dp(48.0f)));
        return;
    L5:
        if (this.layoutInvalidated == true) goto L8;
        if (r52.viewWidth == r42) goto L11;
        goto L8
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r2) {
        return this.clickHelper.onTouchEvent(this, r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.CharSequence] */
    public void set(GroupCallMessage r13) {
        if (isAttachedToWindow() == false) goto L7;
        GroupCallMessage r0 = this.groupCallMessage;
        if (r0 == null) goto L7;
        r0.unsubscribeFromStateUpdates(this.onMessageStateUpdateListener);
    L7:
        this.groupCallMessage = r13;
        if (isAttachedToWindow() == false) goto L12;
        GroupCallMessage r02 = this.groupCallMessage;
        if (r02 == null) goto L12;
        r02.subscribeToStateUpdates(this.onMessageStateUpdateListener);
    L12:
        onMessageStateUpdate(false);
        TLObject r1 = MessagesController.getInstance(UserConfig.selectedAccount).getUserOrChat(r13.fromId);
        String r2 = DialogObject.getName(r1);
        AvatarDrawable r3 = new AvatarDrawable();
        r3.setInfo(r13.currentAccount, r1);
        this.avatarReceiver.setForUserOrChat(r1, r3);
        this.animatedReactionReceiver.setImage(null, null, null, null, null, 0);
        if (this.animatedReactionDrawable != null) goto L15;
    L17:
        this.animatedReactionDrawable = null;
        SpannableStringBuilder r12 = new SpannableStringBuilder(r2);
        r12.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r12.length(), 33);
        r12.setSpan(this.senderNameSpan, 0, r12.length(), 33);
        ReactionsLayoutInBubble.VisibleReaction r22 = r13.visibleReaction;
        if (r22 != null) goto L21;
        SpannableStringBuilder r14 = concat(r12, MessageObject.formatTextWithEntities(r13.message, false, true, this.messageTextView.getPaint()));
    L30:
        this.messageReaction = r13.visibleReaction;
        this.layoutInvalidated = true;
        this.messageTextView.setText(r14);
        requestLayout();
        return;
    L21:
        if (r22.emojicon == null) goto L25;
        TLRPC.TL_availableReaction r03 = MediaDataController.getInstance(r13.currentAccount).getReactionsMap().get(r13.visibleReaction.emojicon);
        r14 = r12;
        if (r03 == null) goto L30;
        TLRPC.Document r04 = r03.select_animation;
        this.animatedReactionReceiver.setImage(ImageLocation.getForDocument(r04), "28_28", null, null, null, 0);
        r14 = r12;
        goto L30
    L25:
        r14 = r12;
        if (r22.documentId == 0) goto L30;
        AnimatedEmojiDrawable r23 = new AnimatedEmojiDrawable(0, r13.currentAccount, r13.visibleReaction.documentId);
        this.animatedReactionDrawable = r23;
        r23.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        r14 = r12;
        if (isAttachedToWindow() == false) goto L30;
        this.animatedReactionDrawable.addView(this);
        r14 = r12;
        goto L30
    L15:
        if (isAttachedToWindow() == false) goto L17;
        this.animatedReactionDrawable.removeView(this);
        goto L17
    }

    @Override // android.view.View
    public void setBackgroundColor(int r1) {
        this.bgPaint.setColor(r1);
    }

    public void setDelegate(Delegate r1) {
        this.delegate = r1;
    }

    public void setRenderNode(View r1, RenderNode r2, float r3) {
        this.blurRoot = r1;
        this.renderNode = r2;
        this.renderNodeScale = r3;
    }

    public void setSingleLine() {
        this.messageTextView.setMaxLines(1);
        this.messageTextView.setSingleLine(true);
        this.messageTextView.setEllipsize(TextUtils.TruncateAt.END);
    }
}
