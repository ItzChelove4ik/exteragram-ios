package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.utils.ui.TextPaint;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.CheckBoxBase;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EmptyStubSpan;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ReplyMessageLine;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.telegram.ui.MessageEnterTransitionContainer;
import org.telegram.ui.recyclerview.ChatListItemAnimator;

/* loaded from: classes6.dex */
public class TextMessageEnterTransition implements MessageEnterTransitionContainer.Transition {
    private AnimatedEmojiSpan.EmojiGroupedSpans animatedEmojiStack;
    private ValueAnimator animator;
    Paint bitmapPaint;
    boolean changeColor;
    private ChatActivity chatActivity;
    MessageEnterTransitionContainer container;
    boolean crossfade;
    Bitmap crossfadeTextBitmap;
    float crossfadeTextOffset;
    private final int currentAccount;
    MessageObject currentMessageObject;
    boolean drawBitmaps;
    private float drawableFromBottom;
    float drawableFromTop;
    ChatActivityEnterView enterView;
    int fromColor;
    Drawable fromMessageDrawable;
    float fromRadius;
    private float fromStartX;
    private float fromStartY;
    private Matrix gradientMatrix;
    private Paint gradientPaint;
    private LinearGradient gradientShader;
    boolean hasReply;
    boolean initBitmaps;
    float lastMessageX;
    float lastMessageY;
    StaticLayout layout;
    RecyclerListView listView;
    private float listViewTargetBottomPadding;
    private int messageId;
    private final RectF messageReplySelectorRect;
    ChatMessageCell messageView;
    private AnimationNotificationsLocker notificationsLocker;
    float progress;
    int replayFromColor;
    int replayObjectFromColor;
    float replyFromObjectStartY;
    float replyFromStartWidth;
    float replyFromStartX;
    float replyFromStartY;
    float replyNameDx;
    private final RectF replySelectorRect;
    private final Theme.ResourcesProvider resourcesProvider;
    private float[] roundRectRadii;
    StaticLayout rtlLayout;
    private float scaleFrom;
    private float scaleY;
    Bitmap textLayoutBitmap;
    Bitmap textLayoutBitmapRtl;
    MessageObject.TextLayoutBlock textLayoutBlock;
    float textX;
    float textY;
    private final PointF tmpPointF;
    int toColor;
    float toXOffset;
    float toXOffsetRtl;

    /* renamed from: org.telegram.ui.TextMessageEnterTransition$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ TextMessageEnterTransition this$0;
        final /* synthetic */ ChatActivity val$chatActivity;
        final /* synthetic */ ChatActivityEnterView val$chatActivityEnterView;
        final /* synthetic */ MessageEnterTransitionContainer val$container;
        final /* synthetic */ ChatMessageCell val$messageView;

        public AnonymousClass1(TextMessageEnterTransition r1, MessageEnterTransitionContainer r2, ChatMessageCell r3, ChatActivityEnterView r4, ChatActivity r5) {
            this.this$0 = r1;
            this.val$container = r2;
            this.val$messageView = r3;
            this.val$chatActivityEnterView = r4;
            this.val$chatActivity = r5;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r6) {
            TextMessageEnterTransition.m20607$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            this.val$container.removeTransition(this.this$0);
            this.val$messageView.setEnterTransitionInProgress(false);
            this.val$messageView.getTransitionParams().lastDrawingBackgroundRect.set(this.val$messageView.getBackgroundDrawableLeft(), this.val$messageView.getBackgroundDrawableTop(), this.val$messageView.getBackgroundDrawableRight(), this.val$messageView.getBackgroundDrawableBottom());
            this.val$chatActivityEnterView.setTextTransitionIsRunning(false);
            this.val$chatActivityEnterView.getEditField().setAlpha(1.0f);
            this.val$chatActivity.getReplyNameTextView().setAlpha(1.0f);
            this.val$chatActivity.getReplyObjectTextView().setAlpha(1.0f);
            AnimatedEmojiSpan.release(null, TextMessageEnterTransition.m20606$$Nest$fgetanimatedEmojiStack(this.this$0));
        }
    }

    /* renamed from: $r8$lambda$6vnSl-CW6akk5wcBuICaajIguRk, reason: not valid java name */
    public static /* synthetic */ void m20605$r8$lambda$6vnSlCW6akk5wcBuICaajIguRk(TextMessageEnterTransition r0, ChatActivityEnterView r1, MessageEnterTransitionContainer r2, ValueAnimator r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetanimatedEmojiStack, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedEmojiSpan.EmojiGroupedSpans m20606$$Nest$fgetanimatedEmojiStack(TextMessageEnterTransition r0) {
        return r0.animatedEmojiStack;
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m20607$$Nest$fgetnotificationsLocker(TextMessageEnterTransition r0) {
        return r0.notificationsLocker;
    }

    @SuppressLint({"WrongConstant"})
    public TextMessageEnterTransition(ChatMessageCell r28, ChatActivity r29, RecyclerListView r30, MessageEnterTransitionContainer r31, Theme.ResourcesProvider r32) {
        this.bitmapPaint = new Paint(1);
        this.initBitmaps = false;
        this.drawBitmaps = false;
        this.notificationsLocker = new AnimationNotificationsLocker();
        PointF r4 = new PointF();
        this.tmpPointF = r4;
        this.replySelectorRect = new RectF();
        this.messageReplySelectorRect = new RectF();
        this.resourcesProvider = r32;
        this.currentAccount = UserConfig.selectedAccount;
        if (r28.getMessageObject().textLayoutBlocks != null) goto L5;
        return;
    L5:
        if (r28.getMessageObject().textLayoutBlocks.size() <= 1) goto L7;
        return;
    L7:
        if (r28.getMessageObject().textLayoutBlocks.isEmpty() == false) goto L9;
        return;
    L9:
        if (r28.getMessageObject().textLayoutBlocks.get(0).textLayout.getLineCount() > 10) goto L178;
        this.messageView = r28;
        this.listView = r30;
        this.container = r31;
        this.chatActivity = r29;
        this.enterView = r29.getChatActivityEnterView();
        ChatActivityEnterView r8 = r29.getChatActivityEnterView();
        if (r8 != null) goto L14;
        return;
    L14:
        if (r8.getEditField() != null) goto L16;
        return;
    L16:
        if (r8.getEditField().getLayout() == null) goto L179;
        ChatActivityEnterView.RecordCircle r9 = r8.getRecordCircle();
        if (r9 != null) goto L21;
        float r92 = 0.0f;
    L22:
        this.fromRadius = r92;
        this.bitmapPaint.setFilterBitmap(true);
        this.currentMessageObject = r28.getMessageObject();
        if (r28.getTransitionParams().wasDraw == true) goto L25;
        r28.draw(new Canvas());
    L25:
        r28.setEnterTransitionInProgress(true);
        Editable r93 = r8.getEditText();
        CharSequence r11 = r28.getMessageObject().messageText;
        this.crossfade = false;
        int r12 = r8.getEditField().getLayout().getHeight();
        TextPaint r13 = Theme.chat_msgTextPaint;
        AndroidUtilities.dp(20.0f);
        if (r28.getMessageObject().getEmojiOnlyCount() == 0) goto L58;
        if (r28.getMessageObject().emojiOnlyCount != r28.getMessageObject().animatedEmojiCount) goto L30;
        boolean r132 = true;
    L32:
        switch(Math.max(r28.getMessageObject().emojiOnlyCount, r28.getMessageObject().animatedEmojiCount)) {
            case 0: goto L51;
            case 1: goto L51;
            case 2: goto L51;
            case 3: goto L47;
            case 4: goto L43;
            case 5: goto L39;
            case 6: goto L35;
            default: goto L33;
        };
    L33:
        TextPaint r10 = Theme.chat_msgTextPaintEmoji[5];
    L34:
        r13 = r10;
        if (r13 == null) goto L58;
        r13.getTextSize();
        AndroidUtilities.dp(4.0f);
        goto L58
    L35:
        TextPaint[] r102 = Theme.chat_msgTextPaintEmoji;
        if (r132 == false) goto L38;
        r10 = r102[4];
        goto L34
    L38:
        r10 = r102[5];
        goto L34
    L39:
        TextPaint[] r103 = Theme.chat_msgTextPaintEmoji;
        if (r132 == false) goto L42;
        r10 = r103[3];
        goto L34
    L42:
        r10 = r103[5];
        goto L34
    L43:
        TextPaint[] r104 = Theme.chat_msgTextPaintEmoji;
        if (r132 == false) goto L46;
        r10 = r104[2];
        goto L34
    L46:
        r10 = r104[4];
        goto L34
    L47:
        TextPaint[] r105 = Theme.chat_msgTextPaintEmoji;
        if (r132 == false) goto L50;
        r10 = r105[1];
        goto L34
    L50:
        r10 = r105[3];
        goto L34
    L51:
        TextPaint[] r106 = Theme.chat_msgTextPaintEmoji;
        if (r132 == false) goto L54;
        r10 = r106[0];
        goto L34
    L54:
        r10 = r106[2];
        goto L34
    L30:
        r132 = false;
    L58:
        if ((r11 instanceof Spannable) == false) goto L64;
        Object[] r107 = ((Spannable) r11).getSpans(0, r11.length(), Object.class);
        if (r107 == null) goto L64;
        if (r107.length <= 0) goto L64;
        boolean r108 = true;
    L66:
        if (r93.length() != r11.length()) goto L70;
        if (r108 == true) goto L70;
        int r6 = 0;
    L75:
        this.scaleFrom = r8.getEditField().getTextSize() / r13.getTextSize();
        int r94 = r8.getEditField().getLayout().getLineCount();
        int r109 = (int) (r8.getEditField().getLayout().getWidth() / this.scaleFrom);
        StaticLayout r0 = StaticLayout.Builder.obtain(r11, 0, r11.length(), r13, r109).setBreakStrategy(1).setHyphenationFrequency(0).setAlignment(Layout.Alignment.ALIGN_NORMAL).build();
        this.layout = r0;
        this.animatedEmojiStack = AnimatedEmojiSpan.update(2, null, this.animatedEmojiStack, new Layout[]{r0});
        ViewPositionWatcher.computeCoordinatesInParent(r8.getEditField(), r29.contentView, r4);
        float r02 = r4.y;
        this.fromStartX = r4.x;
        this.fromStartY = ((AndroidUtilities.dp(10.0f) + r02) - r8.getEditField().getScrollY()) + r6;
        this.toXOffset = 0.0f;
        float r7 = Float.MAX_VALUE;
        int r62 = 0;
    L77:
        if (r62 >= this.layout.getLineCount()) goto L83;
        float r14 = this.layout.getLineLeft(r62);
        if (r14 >= r7) goto L81;
        r7 = r14;
    L81:
        r62 = r62 + 1;
        goto L77
    L83:
        if (r7 == Float.MAX_VALUE) goto L85;
        this.toXOffset = r7;
    L85:
        this.scaleY = r12 / (this.layout.getHeight() * this.scaleFrom);
        this.drawableFromTop = AndroidUtilities.dp(4.0f) + r02;
        float r72 = 12.0f;
        if (this.enterView.isTopViewVisible() == false) goto L88;
        this.drawableFromTop -= AndroidUtilities.dp(12.0f);
    L88:
        this.drawableFromBottom = r02 + r8.getEditField().getMeasuredHeight();
        MessageObject.TextLayoutBlock r03 = r28.getMessageObject().textLayoutBlocks.get(0);
        this.textLayoutBlock = r03;
        StaticLayout r04 = r03.textLayout;
        if (this.currentMessageObject.isOutOwner() == false) goto L91;
        int r63 = Theme.key_chat_messageTextOut;
    L92:
        int r64 = getThemedColor(r63);
        int r122 = getThemedColor(Theme.key_chat_messagePanelText);
        if (Math.abs(ColorUtils.calculateLuminance(r64) - ColorUtils.calculateLuminance(r122)) <= 0.20000000298023224d) goto L95;
        this.crossfade = true;
        this.changeColor = true;
    L95:
        this.fromColor = r122;
        this.toColor = r64;
        if (r04.getLineCount() != this.layout.getLineCount()) goto L108;
        r94 = r04.getLineCount();
        int r65 = 0;
        int r123 = 0;
        int r142 = 0;
    L98:
        if (r65 >= r94) goto L110;
        if (isRtlLine(this.layout, r65) == false) goto L102;
        r142 = r142 + 1;
    L104:
        if (r04.getLineEnd(r65) != this.layout.getLineEnd(r65)) goto L105;
        r65 = r65 + 1;
        goto L98
    L105:
        this.crossfade = true;
        goto L110
    L102:
        r123 = r123 + 1;
    L110:
        if (this.crossfade == true) goto L124;
        if (r142 <= 0) goto L124;
        if (r123 <= 0) goto L124;
        SpannableString r05 = new SpannableString(r11);
        SpannableString r42 = new SpannableString(r11);
        float r66 = Float.MAX_VALUE;
        int r112 = 0;
    L114:
        if (r112 >= r94) goto L123;
        if (isRtlLine(this.layout, r112) == false) goto L121;
        float r322 = r72;
        r05.setSpan(new EmptyStubSpan(), this.layout.getLineStart(r112), this.layout.getLineEnd(r112), 0);
        float r73 = this.layout.getLineLeft(r112);
        if (r73 >= r66) goto L122;
        r66 = r73;
    L122:
        r112 = r112 + 1;
        r72 = r322;
        goto L114
    L121:
        r322 = r72;
        r42.setSpan(new EmptyStubSpan(), this.layout.getLineStart(r112), this.layout.getLineEnd(r112), 0);
        goto L122
    L123:
        float r323 = r72;
        StaticLayout.Builder r06 = StaticLayout.Builder.obtain(r05, 0, r05.length(), r13, r109).setBreakStrategy(1).setHyphenationFrequency(0);
        Layout.Alignment r74 = Layout.Alignment.ALIGN_NORMAL;
        this.layout = r06.setAlignment(r74).build();
        this.rtlLayout = StaticLayout.Builder.obtain(r42, 0, r42.length(), r13, r109).setBreakStrategy(1).setHyphenationFrequency(0).setAlignment(r74).build();
    L125:
        this.toXOffsetRtl = this.layout.getWidth() - r28.getMessageObject().textLayoutBlocks.get(0).textLayout.getWidth();
        if (this.drawBitmaps == false) goto L141;
        int r07 = this.layout.getWidth();     // Catch: Exception -> L131
        int r43 = this.layout.getHeight();     // Catch: Exception -> L131
        Bitmap.Config r67 = Bitmap.Config.ARGB_8888;     // Catch: Exception -> L131
        this.textLayoutBitmap = Bitmap.createBitmap(r07, r43, r67);     // Catch: Exception -> L131
        this.layout.draw(new Canvas(this.textLayoutBitmap));     // Catch: Exception -> L131
        StaticLayout r08 = this.rtlLayout;     // Catch: Exception -> L131
        if (r08 == null) goto L133;
        this.textLayoutBitmapRtl = Bitmap.createBitmap(r08.getWidth(), this.rtlLayout.getHeight(), r67);     // Catch: Exception -> L131
        this.rtlLayout.draw(new Canvas(this.textLayoutBitmapRtl));     // Catch: Exception -> L131
    L133:
        if (this.crossfade == false) goto L141;
        if (r28.getMeasuredHeight() >= r30.getMeasuredHeight()) goto L137;
        this.crossfadeTextOffset = 0.0f;     // Catch: Exception -> L131
        this.crossfadeTextBitmap = Bitmap.createBitmap(r28.getMeasuredWidth(), r28.getMeasuredHeight(), r67);     // Catch: Exception -> L131
        goto L141
    L137:
        this.crossfadeTextOffset = r28.getTop();     // Catch: Exception -> L131
        this.crossfadeTextBitmap = Bitmap.createBitmap(r28.getMeasuredWidth(), r30.getMeasuredHeight(), r67);     // Catch: Exception -> L131
    L141:
        if (r28.getMessageObject().getReplyMsgId() != 0) goto L143;
    L145:
        boolean r09 = false;
    L146:
        this.hasReply = r09;
        if (r09 == false) goto L149;
        SimpleTextView r010 = r29.getReplyNameTextView();
        ViewPositionWatcher.computeCoordinatesInParent(r010, r29.contentView, this.tmpPointF);
        PointF r44 = this.tmpPointF;
        this.replyFromStartX = r44.x;
        this.replyFromStartY = r44.y;
        this.replyFromStartWidth = ((View) r010.getParent()).getWidth();
        SimpleTextView r45 = r29.getReplyObjectTextView();
        ViewPositionWatcher.computeCoordinatesInParent(r45, r29.contentView, this.tmpPointF);
        this.replyFromObjectStartY = this.tmpPointF.y;
        this.replayFromColor = r010.getTextColor();
        this.replayObjectFromColor = r45.getTextColor();
        this.drawableFromTop -= AndroidUtilities.dp(46.0f);
    L149:
        this.listViewTargetBottomPadding = r30.getPaddingBottom() - (r29.getInputIslandHeightTarget() - AndroidUtilities.dp(48.0f));
        this.gradientMatrix = new Matrix();
        Paint r011 = new Paint(1);
        this.gradientPaint = r011;
        r011.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        LinearGradient r19 = new LinearGradient(0.0f, AndroidUtilities.dp(r323), 0.0f, 0.0f, 0, -16777216, Shader.TileMode.CLAMP);
        this.gradientShader = r19;
        this.gradientPaint.setShader(r19);
        this.messageId = r28.getMessageObject().stableId;
        r8.getEditField().setAlpha(0.0f);
        r8.setTextTransitionIsRunning(true);
        StaticLayout r012 = r28.replyNameLayout;
        if (r012 != null) goto L152;
    L156:
        ValueAnimator r013 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.animator = r013;
        r013.addUpdateListener(new TextMessageEnterTransition$$ExternalSyntheticLambda0(this, r8, r31));
        this.animator.setInterpolator(new LinearInterpolator());
        this.animator.setDuration(250);
        r31.addTransition(this);
        this.notificationsLocker.lock();
        this.animator.addListener(new AnonymousClass1(this, r31, r28, r8, r29));
        if (SharedConfig.getDevicePerformanceClass() != 2) goto L176;
        MessageDrawable r014 = r28.getCurrentBackgroundDrawable(true);
        if (r014 == null) goto L177;
        this.fromMessageDrawable = r014.getTransitionDrawable(getThemedColor(Theme.key_chat_messagePanelBackground));
        return;
    L177:
        return;
    L176:
        return;
    L152:
        if (r012.getText().length() <= 1) goto L156;
        if (r28.replyNameLayout.getPrimaryHorizontal(0) == 0.0f) goto L156;
        this.replyNameDx = r28.replyNameLayout.getWidth() - r28.replyNameLayout.getLineWidth(0);
        goto L156
    L143:
        if (r28.replyNameLayout == null) goto L145;
        r09 = true;
        goto L146
    L139:
        this.drawBitmaps = false;
    L124:
        r323 = 12.0f;
        goto L125
    L108:
        this.crossfade = true;
        r123 = 0;
        r142 = 0;
        goto L110
    L91:
        r63 = Theme.key_chat_messageTextIn;
    L70:
        this.crossfade = true;
        int[] r1010 = new int[1];
        CharSequence r143 = AndroidUtilities.trim(r93, r1010);
        if (r1010[0] <= 0) goto L73;
        r6 = r8.getEditField().getLayout().getLineTop(r8.getEditField().getLayout().getLineForOffset(r1010[0]));
        r12 = r8.getEditField().getLayout().getLineBottom(r8.getEditField().getLayout().getLineForOffset(r1010[0] + r143.length())) - r6;
    L74:
        AnimatedEmojiSpan.cloneSpans(r11);
        r11 = Emoji.replaceEmoji(r93, r13.getFontMetricsInt(), false);
        goto L75
    L73:
        r6 = 0;
    L64:
        r108 = false;
        goto L66
    L21:
        r92 = r9.drawingCircleRadius;
        goto L22
    L179:
        return;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private boolean isRtlLine(Layout r2, int r3) {
        if (r2.getLineRight(r3) == r2.getWidth()) goto L5;
        return false;
    L5:
        if (r2.getLineLeft(r3) == 0.0f) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$new$0(ChatActivityEnterView r1, MessageEnterTransitionContainer r2, ValueAnimator r3) {
        this.progress = ((Float) r3.getAnimatedValue()).floatValue();
        r1.getEditField().setAlpha(this.progress);
        r2.invalidate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.telegram.ui.MessageEnterTransitionContainer.Transition
    public void onDraw(Canvas r46) {
        if (this.drawBitmaps == true) goto L5;
    L14:
        float r1 = (this.listView.getY() - this.container.getY()) + this.listView.getMeasuredHeight();
        float r12 = this.fromStartX - this.container.getX();
        float r13 = this.fromStartY - this.container.getY();
        this.textX = this.messageView.getTextX();
        this.textY = this.messageView.getTextY();
        if (this.messageView.getMessageObject().stableId != this.messageId) goto L299;
        float r9 = (this.messageView.getX() + this.listView.getX()) - this.container.getX();
        float r14 = (((this.messageView.getTop() + this.messageView.getPaddingTop()) + this.listView.getTop()) - this.container.getY()) - (this.listViewTargetBottomPadding - this.listView.getPaddingBottom());
        this.lastMessageX = r9;
        this.lastMessageY = r14;
        float r15 = ChatListItemAnimator.DEFAULT_INTERPOLATOR.getInterpolation(this.progress);
        float r2 = this.progress;
        if (r2 <= 0.4f) goto L20;
        float r4 = 1.0f;
    L21:
        float r22 = CubicBezierInterpolator.EASE_OUT.getInterpolation(CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(r2));
        float r17 = r9 + this.textX;
        float r18 = r14 + this.textY;
        float r19 = 1.0f - r22;
        int r16 = (int) ((this.container.getMeasuredHeight() * r19) + (r1 * r22));
        if ((this.messageView.getBottom() - AndroidUtilities.dp(4.0f)) > this.listView.getMeasuredHeight()) goto L24;
    L28:
        Object[] r222 = false;
    L29:
        if (r222 == false) goto L31;
        int r28 = r16;
        float r26 = 1.0f;
        float r10 = r4;
        float r27 = r22;
        Canvas r110 = r46;
        r110.saveLayerAlpha(0.0f, Math.max(0.0f, r14), this.container.getMeasuredWidth(), this.container.getMeasuredHeight(), 255, 31);
    L32:
        r110.save();
        r110.clipRect(0.0f, ((this.listView.getY() + this.chatActivity.getChatListViewPadding()) - this.container.getY()) - AndroidUtilities.dp(3.0f), this.container.getMeasuredWidth(), this.container.getMeasuredHeight());
        r110.save();
        float r7 = (this.messageView.getBackgroundDrawableLeft() + r9) + ((r12 - (r17 - this.toXOffset)) * r19);
        float r23 = this.messageView.getBackgroundDrawableTop() + r14;
        float r25 = r26 - r15;
        float r3 = ((this.drawableFromTop - this.container.getY()) * r25) + (r23 * r15);
        float r24 = ((r23 + (this.messageView.getBackgroundDrawableBottom() - this.messageView.getBackgroundDrawableTop())) * r15) + ((this.drawableFromBottom - this.container.getY()) * r25);
        int r42 = (int) ((this.messageView.getBackgroundDrawableRight() + r9) + (AndroidUtilities.dp(4.0f) * r19));
        if (this.currentMessageObject.isAnimatedEmojiStickers() == true) goto L35;
        MessageDrawable r5 = this.messageView.getCurrentBackgroundDrawable(true);
    L37:
        if (r5 == null) goto L48;
        this.messageView.setBackgroundTopY(this.container.getTop() - this.listView.getTop());
        Drawable r6 = r5.getShadowDrawable();
        if (r10 == r26) goto L43;
        Drawable r8 = this.fromMessageDrawable;
        if (r8 == null) goto L43;
        float r33 = r12;
        float r34 = r13;
        r8.setBounds((int) r7, (int) r3, r42, (int) r24);
        this.fromMessageDrawable.draw(r110);
    L44:
        float r11 = r27;
        if (r6 == null) goto L47;
        r6.setAlpha((int) (r11 * 255.0f));
        r6.setBounds((int) r7, (int) r3, r42, (int) r24);
        r6.draw(r110);
        r6.setAlpha(255);
    L47:
        r5.setAlpha((int) (r10 * 255.0f));
        r5.setBounds((int) r7, (int) r3, r42, (int) r24);
        r5.setDrawFullBubble(true);
        r5.draw(r110);
        r5.setDrawFullBubble(false);
        r5.setAlpha(255);
    L49:
        r110.restore();
        r110.save();
        if (r5 != null) goto L52;
    L55:
        float r132 = ((r34 - r18) * r25) + r14;
        r110.translate((this.messageView.getLeft() + this.listView.getX()) - this.container.getX(), r132);
        this.messageView.drawTime(r110, r10, false);
        this.messageView.drawNamesLayout(r110, r10);
        this.messageView.drawCommentButton(r110, r10);
        this.messageView.drawCaptionLayout(r110, false, r10);
        this.messageView.drawReactionsLayout(r110, r10, null);
        this.messageView.drawCommentLayout(r110, r10);
        this.messageView.drawLinkPreview(r110, r10);
        r110.restore();
        if (this.hasReply == false) goto L227;
        this.chatActivity.getReplyNameTextView().setAlpha(0.0f);
        this.chatActivity.getReplyObjectTextView().setAlpha(0.0f);
        AndroidUtilities.lerp(AndroidUtilities.dp(35.0f), this.messageView.replyHeight, r11);
        int r52 = AndroidUtilities.dp(10.0f);
        float r62 = this.replyFromStartX - this.container.getX();
        float r82 = this.replyFromStartY - this.container.getY();
        ChatMessageCell r122 = this.messageView;
        float r111 = r122.replyStartX + r9;
        float r35 = r24;
        float r142 = r14 + r122.replyStartY;
        if (r122.replyLine != null) goto L60;
        r122.replyLine = new ReplyMessageLine(r122);
    L60:
        ChatMessageCell r29 = this.messageView;
        r29.replyLine.check(r29.getMessageObject(), this.messageView.getCurrentUser(), this.messageView.getCurrentChat(), this.resourcesProvider, 0);
        if (this.currentMessageObject.shouldDrawWithoutBackground() == false) goto L63;
        int r210 = getThemedColor(Theme.key_chat_stickerReplyNameText);
        getThemedColor(Theme.key_chat_stickerReplyLine);
    L71:
        if (this.currentMessageObject.shouldDrawWithoutBackground() == true) goto L115;
        boolean r123 = this.currentMessageObject.isOutOwner();
        float r36 = r3;
        MessageObject r32 = this.currentMessageObject;
        if (r123 == true) goto L75;
        int r38 = r42;
        if (r32.isReplyToStory() == false) goto L98;
    L97:
        int r37 = r210;
    L130:
        Theme.chat_replyTextPaint.setColor(ColorUtils.blendARGB(this.replayObjectFromColor, r37, r15));
        Theme.chat_replyNamePaint.setColor(ColorUtils.blendARGB(this.replayFromColor, r210, r15));
        if (this.messageView.needReplyImage == false) goto L133;
        r62 = r62 - AndroidUtilities.dp(48.0f);
    L133:
        float r124 = r62;
        float r211 = AndroidUtilities.lerp(r124, r111, r11);
        float r143 = AndroidUtilities.lerp((AndroidUtilities.dp(12.0f) * r15) + r82, r142, r15);
        if (this.roundRectRadii != null) goto L136;
        this.roundRectRadii = new float[]{r4, r4, 0.0f, 0.0f, 0.0f, 0.0f, r4, r4};
        float r43 = AndroidUtilities.dp(4.0f);
        float[] r39 = this.roundRectRadii;
        r39[5] = 0.0f;
        r39[4] = 0.0f;
        r39[3] = 0.0f;
        r39[2] = 0.0f;
    L136:
        RectF r310 = AndroidUtilities.rectTmp;
        r310.set(r124, r82, this.replyFromStartWidth + r124, AndroidUtilities.dp(35.0f) + r82);
        r310.offset(0.0f, AndroidUtilities.dp(12.0f) * r15);
        this.messageReplySelectorRect.set(this.messageView.replySelectorRect);
        this.messageReplySelectorRect.offset(r9, r132);
        AndroidUtilities.lerp(r310, this.messageReplySelectorRect, r11, this.replySelectorRect);
        ChatMessageCell r311 = this.messageView;
        float r44 = r10;
        r311.replyLine.drawBackground(r46, this.replySelectorRect, r44, r311.isReplyQuote, r311.getMessageObject().shouldDrawWithoutBackground());
        r110 = r46;
        this.messageView.replyLine.drawLine(r110, this.replySelectorRect, r44);
        if (this.messageView.needReplyImage == false) goto L151;
        r110.save();
        float r312 = this.replySelectorRect.height() - AndroidUtilities.dp(10.0f);
        if (this.messageView.isReplyQuote == false) goto L141;
        float r45 = 3.0f;
    L142:
        float r313 = AndroidUtilities.lerp(AndroidUtilities.dp(35.0f), Math.min(r312, (AndroidUtilities.dp(r45) + Theme.chat_replyNamePaint.getTextSize()) + Theme.chat_replyTextPaint.getTextSize()), r11);
        ImageReceiver r47 = this.messageView.replyImageReceiver;
        float r53 = AndroidUtilities.lerp(r211, this.replySelectorRect.left + AndroidUtilities.dp(8.0f), r11);
        float r63 = this.replySelectorRect.top;
        ChatMessageCell r102 = this.messageView;
        float r272 = r44;
        if (r102.isReplyQuote == false) goto L149;
        StaticLayout r212 = r102.replyTextLayout;
        if (r212 == null) goto L149;
        if (r212.getLineCount() > 1) goto L149;
        int r213 = 2;
    L150:
        r47.setImageCoords(r53, AndroidUtilities.lerp(r143, r63 + AndroidUtilities.dp(r213 + 5), r11), r313, r313);
        this.messageView.replyImageReceiver.draw(r110);
        r110.restore();
        float r103 = r313;
    L152:
        r110.save();
        float r83 = r52 * r11;
        r110.translate(r83, 0.0f);
        MessageObject r214 = this.currentMessageObject;
        if (r214 != null) goto L155;
    L158:
        int r215 = AndroidUtilities.dp(r26);
    L157:
        float r216 = -r215;
        MessageObject r314 = this.currentMessageObject;
        if (r314 != null) goto L162;
    L165:
        int r315 = AndroidUtilities.dp(3.0f);
    L164:
        float r316 = r315;
        int r317 = this.messageView.replyTextOffset;
        float r48 = (r111 - r317) + r216;
        float r54 = (r111 - this.replyNameDx) + r216;
        AndroidUtilities.lerp(r124 - r317, r48, r11);
        float r217 = AndroidUtilities.lerp(r124, r54, r11);
        if (this.messageView.needReplyImage == false) goto L169;
        float r318 = AndroidUtilities.dp(3.0f) + r103;
    L170:
        float r218 = r217 + r318;
        if (this.messageView.replyNameLayout == null) goto L173;
        r110.save();
        r110.translate(r218, (r316 * r11) + r143);
        int r219 = Theme.chat_replyNamePaint.getAlpha();
        Theme.chat_replyNamePaint.setAlpha((int) (r219 * r11));
        this.messageView.replyNameLayout.draw(r110);
        Theme.chat_replyNamePaint.setAlpha(r219);
        SimpleTextView r92 = this.chatActivity.getReplyNameTextView();
        float r41 = r48;
        float r422 = r7;
        float r432 = r272;
        r110.saveLayerAlpha(0.0f, 0.0f, r92.getWidth(), r92.getHeight(), (int) (r19 * 255.0f), 31);
        r92.setAlpha(r26);
        r92.draw(r110);
        r92.setAlpha(0.0f);
        r110.restore();
        r110.restore();
    L174:
        ChatMessageCell r220 = this.messageView;
        if (r220.isReplyQuote == false) goto L183;
        if (r220.replyQuoteDrawable == null) goto L183;
        int r221 = r220.replyLine.getColor();
        ChatMessageCell r319 = this.messageView;
        if (r221 == r319.replyQuoteDrawableColor) goto L181;
        Drawable r223 = r319.replyQuoteDrawable;
        ChatMessageCell r49 = this.messageView;
        int r55 = r49.replyLine.getColor();
        r49.replyQuoteDrawableColor = r55;
        r223.setColorFilter(new PorterDuffColorFilter(r55, PorterDuff.Mode.SRC_IN));
    L181:
        ChatMessageCell r224 = this.messageView;
        r224.replyQuoteDrawable.setBounds((int) (((this.replySelectorRect.right - r83) - AndroidUtilities.dp((!r224.drawPinnedTop ? 1 : 0) + 2)) - this.messageView.replyQuoteDrawable.getIntrinsicWidth()), (int) (this.replySelectorRect.top + AndroidUtilities.dp((!this.messageView.drawPinnedTop ? 1 : 0) + 2)), (int) ((this.replySelectorRect.right - r83) - AndroidUtilities.dp((!this.messageView.drawPinnedTop ? 1 : 0) + 2)), (int) ((this.replySelectorRect.top + AndroidUtilities.dp((!this.messageView.drawPinnedTop ? 1 : 0) + 2)) + this.messageView.replyQuoteDrawable.getIntrinsicHeight()));
        this.messageView.replyQuoteDrawable.setAlpha((int) (r11 * 255.0f));
        this.messageView.replyQuoteDrawable.draw(r110);
    L183:
        if (this.messageView.replyTextLayout == null) goto L226;
        r110.save();
        float r144 = r143 + AndroidUtilities.lerp(AndroidUtilities.dp(19.0f), (Theme.chat_replyNamePaint.getTextSize() + AndroidUtilities.dp(4.0f)) + r316, r11);
        ChatMessageCell r225 = this.messageView;
        if (r225.isReplyQuote == true) goto L187;
    L189:
        float r226 = r41;
    L190:
        ChatMessageCell r320 = this.messageView;
        if (r320.needReplyImage == true) goto L193;
    L197:
        ChatMessageCell r321 = this.messageView;
        if (r321.isReplyTaskOrPollOption == false) goto L211;
        if (r321.replyTaskCheckbox == null) goto L211;
        float r322 = AndroidUtilities.lerp(r124 - r321.replyTextOffset, r226, r11);
        this.messageView.replyTaskCheckbox.setBounds((int) r322, ((int) r144) + AndroidUtilities.dp(2.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
        Paint r410 = Theme.chat_instantViewRectPaint;
        if (this.currentMessageObject.isOutOwner() == false) goto L204;
        int r56 = Theme.key_chat_outMenu;
    L205:
        r410.setColor(getThemedColor(r56));
        r110.drawCircle(r322 + AndroidUtilities.dp(6.0f), AndroidUtilities.dp(8.0f) + r144, AndroidUtilities.dp(5.0f), Theme.chat_instantViewRectPaint);
        CheckBoxBase r323 = this.messageView.replyTaskCheckbox;
        if (this.currentMessageObject.isOutOwner() == false) goto L208;
        int r411 = Theme.key_chat_outAudioSeekbarFill;
    L209:
        r323.setColor(-1, r411, Theme.key_checkboxCheck);
        this.messageView.replyTaskCheckbox.setAlpha(r15);
        this.messageView.replyTaskCheckbox.draw(r110);
        goto L211
    L208:
        r411 = Theme.key_chat_inAudioSeekbarFill;
        goto L209
    L204:
        r56 = Theme.key_chat_inMenu;
    L211:
        if (this.messageView.isReplyTaskOrPollOption == false) goto L213;
        r226 = r226 + AndroidUtilities.dp(16.0f);
    L213:
        ChatMessageCell r324 = this.messageView;
        if (r324.replyTextRTL == true) goto L216;
    L218:
        r110.translate(AndroidUtilities.lerp(r124 - this.messageView.replyTextOffset, r226, r11), r144);
        r110.save();
        SpoilerEffect.clipOutCanvas(r110, this.messageView.replySpoilers);
        ChatMessageCell r227 = this.messageView;
        AnimatedEmojiSpan.drawAnimatedEmojis(r110, r227.replyTextLayout, r227.animatedEmojiReplyStack, 0.0f, r227.replySpoilers, 0.0f, 0.0f, 0.0f, 1.0f);
        this.messageView.replyTextLayout.draw(r110);
        r110.restore();
        Iterator<SpoilerEffect> r228 = this.messageView.replySpoilers.iterator();
    L220:
        if (r228.hasNext() == false) goto L225;
        SpoilerEffect r325 = r228.next();
        if (r325.shouldInvalidateColor() == false) goto L224;
        r325.setColor(this.messageView.replyTextLayout.getPaint().getColor());
    L224:
        r325.draw(r110);
        goto L220
    L225:
        r110.restore();
        goto L226
    L216:
        if (r324.replyTextOffset <= 0) goto L218;
        r226 = ((this.replySelectorRect.right - AndroidUtilities.dp(8.0f)) - this.messageView.replyTextLayout.getWidth()) - r83;
        goto L218
    L193:
        if (r320.isReplyQuote == true) goto L195;
    L196:
        r226 = r226 + (r103 + AndroidUtilities.dp(3.0f));
        goto L197
    L195:
        if (r320.replyTextRTL == false) goto L197;
    L187:
        if (r225.needReplyImage == false) goto L189;
        r226 = r41 - AndroidUtilities.dp(2.0f);
    L226:
        r110.restore();
    L228:
        r110.save();
        if (this.messageView.getMessageObject() != null) goto L231;
    L232:
        r110.clipRect(r422 + AndroidUtilities.dp(4.0f), r36 + AndroidUtilities.dp(4.0f), r38 - AndroidUtilities.dp(4.0f), r35 - AndroidUtilities.dp(4.0f));
    L233:
        float r104 = r11 + (this.scaleFrom * r19);
        if (this.drawBitmaps == false) goto L236;
        float r229 = (this.scaleY * r19) + r11;
    L237:
        r110.save();
        float r125 = r33 * r19;
        float r145 = r34 * r25;
        r110.translate(((r17 - this.toXOffset) * r11) + r125, ((r18 + this.textLayoutBlock.textYOffset(this.messageView.getMessageObject().textLayoutBlocks, this.messageView.transitionParams)) * r15) + r145);
        float r84 = r104 * r229;
        r110.scale(r104, r84, 0.0f, 0.0f);
        boolean r230 = this.drawBitmaps;
        boolean r326 = this.crossfade;
        if (r230 == false) goto L243;
        if (r326 == false) goto L241;
        float r93 = r432;
        this.bitmapPaint.setAlpha((int) ((1.0f - r93) * 255.0f));
    L242:
        r110.drawBitmap(this.textLayoutBitmap, 0.0f, 0.0f, this.bitmapPaint);
        float r252 = r11;
        float r20 = r125;
        float r126 = r84;
        float r112 = r93;
    L252:
        r110.restore();
        if (this.rtlLayout == null) goto L269;
        r110.save();
        r110.translate(r20 + ((r17 - this.toXOffsetRtl) * r252), r145 + ((r18 + this.textLayoutBlock.textYOffset(this.messageView.getMessageObject().textLayoutBlocks, this.messageView.transitionParams)) * r15));
        r110.scale(r104, r126, 0.0f, 0.0f);
        boolean r231 = this.drawBitmaps;
        boolean r327 = this.crossfade;
        if (r231 == false) goto L259;
        if (r327 == false) goto L258;
        this.bitmapPaint.setAlpha((int) ((1.0f - r112) * 255.0f));
    L258:
        r110.drawBitmap(this.textLayoutBitmapRtl, 0.0f, 0.0f, this.bitmapPaint);
    L267:
        r110.restore();
        goto L269
    L259:
        if (r327 == true) goto L261;
    L263:
        StaticLayout r232 = this.rtlLayout;
        if (r327 == false) goto L266;
        int r233 = r232.getPaint().getAlpha();
        this.rtlLayout.getPaint().setAlpha((int) (r233 * (1.0f - r112)));
        this.rtlLayout.draw(r110);
        this.rtlLayout.getPaint().setAlpha(r233);
        goto L267
    L266:
        r232.draw(r110);
        goto L267
    L261:
        if (this.changeColor == false) goto L263;
        int r234 = this.rtlLayout.getPaint().getColor();
        this.rtlLayout.getPaint().setColor(ColorUtils.setAlphaComponent(ColorUtils.blendARGB(this.fromColor, this.toColor, r112), (int) (Color.alpha(r234) * (1.0f - r112))));
        this.rtlLayout.draw(r110);
        this.rtlLayout.getPaint().setColor(r234);
    L269:
        if (this.crossfade == false) goto L277;
        r110.save();
        r110.translate(((this.messageView.getLeft() + this.listView.getX()) - this.container.getX()) + ((r33 - r17) * r19), r132);
        r110.scale(r104, r126, this.messageView.getTextX(), this.messageView.getTextY());
        r110.translate(0.0f, -this.crossfadeTextOffset);
        if (this.crossfadeTextBitmap == null) goto L273;
        this.bitmapPaint.setAlpha((int) (r112 * 255.0f));
        r110.drawBitmap(this.crossfadeTextBitmap, 0.0f, 0.0f, this.bitmapPaint);
    L276:
        r110.restore();
        goto L277
    L273:
        int r72 = Theme.chat_msgTextPaint.getColor();
        Theme.chat_msgTextPaint.setColor(this.toColor);
        ChatMessageCell r113 = this.messageView;
        float r57 = r112;
        r113.drawMessageText(r46, r113.getMessageObject().textLayoutBlocks, true, r57, true);
        r110 = r46;
        this.messageView.drawAnimatedEmojis(r110, r57);
        if (Theme.chat_msgTextPaint.getColor() == r72) goto L276;
        Theme.chat_msgTextPaint.setColor(r72);
    L277:
        r110.restore();
        if (r222 == false) goto L280;
        float r328 = r28;
        this.gradientMatrix.setTranslate(0.0f, r328);
        this.gradientShader.setLocalMatrix(this.gradientMatrix);
        r110.drawRect(0.0f, r328, this.container.getMeasuredWidth(), this.container.getMeasuredHeight(), this.gradientPaint);
        r110.restore();
    L280:
        float r235 = this.progress;
        if (r235 <= 0.4f) goto L284;
        float r236 = 1.0f;
    L286:
        if (r236 != 1.0f) goto L289;
        this.enterView.setTextTransitionIsRunning(false);
    L289:
        if (this.enterView.getSendButton().getVisibility() == 0) goto L291;
        return;
    L291:
        if (r236 >= 1.0f) goto L298;
        ViewPositionWatcher.computeCoordinatesInParent(this.enterView.getSendButton(), this.chatActivity.contentView, this.tmpPointF);
        r110.save();
        r110.translate(this.tmpPointF.x - this.container.getX(), this.tmpPointF.y - this.container.getY());
        View r64 = this.enterView.getSendButton();
        r46.saveLayerAlpha(0.0f, 0.0f, r64.getWidth(), r64.getHeight(), (int) ((1.0f - r236) * 255.0f));
        r64.draw(r46);
        r46.restore();
        r46.restore();
        r46.restore();
        return;
    L298:
        return;
    L284:
        r236 = r235 / 0.4f;
        goto L286
    L241:
        r93 = r432;
        goto L242
    L243:
        float r94 = r432;
        if (r326 == true) goto L246;
    L248:
        r252 = r11;
        r20 = r125;
        r126 = r84;
        r112 = r94;
        StaticLayout r114 = this.layout;
        if (r326 == false) goto L251;
        float r95 = 1.0f - r112;
        r110 = r46;
        r110.saveLayerAlpha(0.0f, 0.0f, r114.getWidth(), this.layout.getHeight(), (int) (r95 * 255.0f), 31);
        this.layout.draw(r110);
        AnimatedEmojiSpan.drawAnimatedEmojis(r110, this.layout, this.animatedEmojiStack, 0.0f, null, 0.0f, 0.0f, 0.0f, r95);
        r110.restore();
        goto L252
    L251:
        r114.draw(r46);
        r110 = r46;
        AnimatedEmojiSpan.drawAnimatedEmojis(r110, this.layout, this.animatedEmojiStack, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f);
        goto L252
    L246:
        if (this.changeColor == false) goto L248;
        int r237 = this.layout.getPaint().getColor();
        this.layout.getPaint().setColor(ColorUtils.blendARGB(this.fromColor, this.toColor, r94));
        float r96 = 1.0f - r94;
        r110.saveLayerAlpha(0.0f, 0.0f, this.layout.getWidth(), this.layout.getHeight(), (int) (r96 * 255.0f), 31);
        this.layout.draw(r110);
        r20 = r125;
        r126 = r84;
        r252 = r11;
        r112 = r94;
        AnimatedEmojiSpan.drawAnimatedEmojis(r110, this.layout, this.animatedEmojiStack, 0.0f, null, 0.0f, 0.0f, 0.0f, r96);
        this.layout.getPaint().setColor(r237);
        r46.restore();
        r110 = r46;
        goto L252
    L236:
        r229 = 1.0f;
        goto L237
    L231:
        if (this.messageView.getMessageObject().type == 19) goto L233;
    L173:
        r41 = r48;
        r422 = r7;
        r432 = r272;
        goto L174
    L169:
        r318 = 0.0f;
        goto L170
    L162:
        if (r314.shouldDrawWithoutBackground() == false) goto L165;
        r315 = AndroidUtilities.dp(r26);
        goto L164
    L155:
        if (r214.shouldDrawWithoutBackground() == false) goto L158;
        r215 = AndroidUtilities.dp(6.0f);
    L149:
        r213 = 0;
        goto L150
    L141:
        r45 = 7.0f;
        goto L142
    L151:
        r272 = r44;
        r103 = 0.0f;
        goto L152
    L98:
        int r329 = getThemedColor(Theme.key_chat_inReplyMessageText);
        MessageObject r412 = this.currentMessageObject;
        if (r412.forceAvatar == false) goto L101;
    L113:
        float r413 = 0.0f;
    L114:
        r37 = ColorUtils.blendARGB(r329, Theme.adaptHue(r329, r210), r413);
        goto L130
    L101:
        if (r412.hasValidReplyMessageObject() == false) goto L111;
        MessageObject r414 = this.currentMessageObject.replyMessageObject;
        if (r414.type == 0) goto L107;
        if (TextUtils.isEmpty(r414.caption) == true) goto L111;
    L107:
        if ((MessageObject.getMedia(this.currentMessageObject.replyMessageObject.messageOwner) instanceof TLRPC.TL_messageMediaGame) == true) goto L111;
        if ((MessageObject.getMedia(this.currentMessageObject.replyMessageObject.messageOwner) instanceof TLRPC.TL_messageMediaInvoice) == false) goto L113;
    L111:
        if (this.messageView.isReplyQuote == true) goto L113;
        r329 = getThemedColor(Theme.key_chat_inReplyMediaMessageText);
        r413 = 0.6f;
        goto L114
    L75:
        if (r32.isReplyToStory() == false) goto L77;
        r38 = r42;
        goto L97
    L77:
        int r330 = getThemedColor(Theme.key_chat_outReplyMessageText);
        MessageObject r127 = this.currentMessageObject;
        if (r127.forceAvatar == false) goto L80;
    L93:
        int r331 = r330;
        float r128 = 0.0f;
    L92:
        r38 = r42;
        r37 = ColorUtils.blendARGB(r331, Theme.adaptHue(r331, r210), r128);
        goto L130
    L80:
        if (r127.hasValidReplyMessageObject() == false) goto L90;
        MessageObject r332 = this.currentMessageObject.replyMessageObject;
        if (r332.type == 0) goto L86;
        if (TextUtils.isEmpty(r332.caption) == true) goto L90;
    L86:
        if ((MessageObject.getMedia(this.currentMessageObject.replyMessageObject.messageOwner) instanceof TLRPC.TL_messageMediaGame) == true) goto L90;
        if ((MessageObject.getMedia(this.currentMessageObject.replyMessageObject.messageOwner) instanceof TLRPC.TL_messageMediaInvoice) == false) goto L93;
    L90:
        if (this.messageView.isReplyQuote == true) goto L93;
        r331 = getThemedColor(Theme.key_chat_outReplyMediaMessageText);
        r128 = 0.6f;
        goto L92
    L115:
        r36 = r3;
        r38 = r42;
        ReplyMessageLine r333 = this.messageView.replyLine;
        if (r333 == null) goto L119;
        r37 = r333.nameColorAnimated.get();
        goto L130
    L119:
        if (this.currentMessageObject.hasValidReplyMessageObject() == false) goto L129;
        MessageObject r334 = this.currentMessageObject.replyMessageObject;
        if (r334.type != 0) goto L123;
    L124:
        TLRPC.MessageMedia r335 = this.currentMessageObject.replyMessageObject.messageOwner.media;
        if ((r335 instanceof TLRPC.TL_messageMediaGame) == true) goto L129;
        if ((r335 instanceof TLRPC.TL_messageMediaInvoice) == true) goto L129;
        r37 = getThemedColor(Theme.key_chat_outReplyMessageText);
        goto L130
    L123:
        if (TextUtils.isEmpty(r334.caption) == false) goto L124;
    L129:
        r37 = getThemedColor(Theme.key_chat_outReplyMediaMessageText);
        goto L130
    L63:
        ReplyMessageLine r238 = this.messageView.replyLine;
        if (r238 == null) goto L67;
        r210 = r238.nameColorAnimated.get();
        this.messageView.replyLine.getColor();
        goto L71
    L67:
        if (this.currentMessageObject.isOutOwner() == false) goto L69;
        r210 = getThemedColor(Theme.key_chat_outReplyNameText);
        getThemedColor(Theme.key_chat_outReplyLine);
        goto L71
    L69:
        r210 = getThemedColor(Theme.key_chat_inReplyNameText);
        getThemedColor(Theme.key_chat_inReplyLine);
        goto L71
    L227:
        r35 = r24;
        r36 = r3;
        r38 = r42;
        r422 = r7;
        r432 = r10;
        goto L228
    L52:
        if (this.currentMessageObject.isOutOwner() == false) goto L54;
        r110.clipRect(AndroidUtilities.dp(4.0f) + r7, AndroidUtilities.dp(4.0f) + r3, r42 - AndroidUtilities.dp(10.0f), r24 - AndroidUtilities.dp(4.0f));
        goto L55
    L54:
        r110.clipRect(AndroidUtilities.dp(4.0f) + r7, AndroidUtilities.dp(4.0f) + r3, r42 - AndroidUtilities.dp(4.0f), r24 - AndroidUtilities.dp(4.0f));
    L43:
        r33 = r12;
        r34 = r13;
        goto L44
    L48:
        r33 = r12;
        r34 = r13;
        r11 = r27;
        goto L49
    L35:
        r5 = null;
        goto L37
    L31:
        r28 = r16;
        r27 = r22;
        r26 = 1.0f;
        r110 = r46;
        r10 = r4;
        goto L32
    L24:
        if (((this.messageView.getMeasuredHeight() + r14) - AndroidUtilities.dp(8.0f)) <= r16) goto L28;
        if (this.container.getMeasuredHeight() <= 0) goto L28;
        r222 = true;
        goto L29
    L20:
        r4 = r2 / 0.4f;
        goto L21
    L299:
        return;
    L5:
        if (this.initBitmaps == true) goto L14;
        if (this.crossfadeTextBitmap == null) goto L14;
        if (this.messageView.getTransitionParams().wasDraw == false) goto L14;
        this.initBitmaps = true;
        Canvas r336 = new Canvas(this.crossfadeTextBitmap);
        r336.translate(0.0f, this.crossfadeTextOffset);
        AnimatedEmojiSpan.EmojiGroupedSpans r115 = this.messageView.animatedEmojiStack;
        if (r115 == null) goto L13;
        r115.clearPositions();
    L13:
        ChatMessageCell r239 = this.messageView;
        r239.drawMessageText(r336, r239.getMessageObject().textLayoutBlocks, true, 1.0f, true);
        this.messageView.drawAnimatedEmojis(r336, 1.0f);
        goto L14
    }

    public void start() {
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L6;
        r0.start();
        return;
    }
}
