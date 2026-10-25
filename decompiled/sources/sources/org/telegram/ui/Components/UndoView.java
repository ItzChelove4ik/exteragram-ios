package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.CharacterStyle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.boosts.BoostRepository;
import org.telegram.ui.PaymentFormActivity;

@Deprecated
/* loaded from: classes3.dex */
public class UndoView extends FrameLayout {
    public static int ACTION_RINGTONE_ADDED = 83;
    private float additionalTranslationY;
    private BackupImageView avatarImageView;
    Drawable backgroundDrawable;
    private int bottomInset;
    private int currentAccount;
    private int currentAction;
    private Runnable currentActionRunnable;
    private Runnable currentCancelRunnable;
    private ArrayList<Long> currentDialogIds;
    private Object currentInfoObject;
    private Object currentInfoObject2;
    float enterOffset;
    private int enterOffsetMargin;
    private boolean fromTop;
    private int hideAnimationType;
    private CharSequence infoText;
    private LinkSpanDrawable.LinksTextView infoTextView;
    private int infoTextViewEmojiCacheType;
    private boolean isShown;
    private long lastUpdateTime;
    private RLottieImageView leftImageView;
    private BaseFragment parentFragment;
    private int prevSeconds;
    private Paint progressPaint;
    private RectF rect;
    private final Theme.ResourcesProvider resourcesProvider;
    private TextView subinfoTextView;
    private TextPaint textPaint;
    private int textWidth;
    int textWidthOut;
    StaticLayout timeLayout;
    StaticLayout timeLayoutOut;
    private long timeLeft;
    private String timeLeftString;
    float timeReplaceProgress;
    private LinearLayout undoButton;
    private ImageView undoImageView;
    private TextView undoTextView;
    private int undoViewHeight;

    /* renamed from: org.telegram.ui.Components.UndoView$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends LinkSpanDrawable.LinksTextView {
        final /* synthetic */ UndoView this$0;

        public AnonymousClass1(UndoView r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView
        public int emojiCacheType() {
            return UndoView.m13477$$Nest$fgetinfoTextViewEmojiCacheType(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.UndoView$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ UndoView this$0;

        public AnonymousClass2(UndoView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.setVisibility(4);
            this.this$0.setScaleX(1.0f);
            this.this$0.setScaleY(1.0f);
            this.this$0.setAlpha(1.0f);
        }
    }

    /* loaded from: classes7.dex */
    public class LinkMovementMethodMy extends LinkMovementMethod {
        final /* synthetic */ UndoView this$0;

        public LinkMovementMethodMy(UndoView r1) {
            this.this$0 = r1;
        }

        @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
        public boolean onTouchEvent(TextView r5, Spannable r6, MotionEvent r7) {
        L11:
            e = move-exception;
            FileLog.e(e);
            return false;
        L5:
            if (r7.getAction() != 0) goto L15;
            CharacterStyle[] r1 = (CharacterStyle[]) r6.getSpans(r5.getSelectionStart(), r5.getSelectionEnd(), CharacterStyle.class);     // Catch: Exception -> L11
            if (r1 != null) goto L9;
        L13:
            return false;
        L9:
            if (r1.length == 0) goto L13;
        L15:
            if (r7.getAction() != 1) goto L24;
            CharacterStyle[] r52 = (CharacterStyle[]) r6.getSpans(r5.getSelectionStart(), r5.getSelectionEnd(), CharacterStyle.class);     // Catch: Exception -> L11
            if (r52 != null) goto L19;
        L21:
            Selection.removeSelection(r6);     // Catch: Exception -> L11
            return true;
        L19:
            if (r52.length <= 0) goto L21;
            this.this$0.didPressUrl(r52[0]);     // Catch: Exception -> L11
            goto L21
        L24:
            return super.onTouchEvent(r5, r6, r7);
        }
    }

    public static /* synthetic */ void $r8$lambda$B7sJajO_R1JT4S8FKJ31EHxhcwU(UndoView r0) {
        r0.lambda$showWithAction$8();
    }

    public static /* synthetic */ void $r8$lambda$E6zBwLnxhEKtx1u1QXgvyZGX15M(UndoView r0, TLObject r1) {
        r0.lambda$showWithAction$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$L1jUFoyovK0vaJ33tzr4uDtEY3I(UndoView r0, View r1) {
        r0.lambda$showWithAction$2(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$ObNJKqvGU8xXsV6D0ktsjRDi0mw(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$TU5MmGUgN7S1eYjqCZW6dSQfE3o(UndoView r0, TLRPC.Message r1, View r2) {
        r0.lambda$showWithAction$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ke2GkMc8i9xdqgwNxqwaq_q8L_Y(UndoView r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$l8XtLSzZxmJyp89hFOLv7CHxCiE(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$lb3Rg2E-B7pVQ2Aaibkq7dDDyZo, reason: not valid java name */
    public static /* synthetic */ void m13476$r8$lambda$lb3Rg2EB7pVQ2Aaibkq7dDDyZo(UndoView r0, long r1) {
        r0.lambda$showWithAction$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$svmU10HvztcemtPEGVxBBnlGpSw(UndoView r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$showWithAction$5(r1, r2);
    }

    /* renamed from: -$$Nest$fgetinfoTextViewEmojiCacheType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13477$$Nest$fgetinfoTextViewEmojiCacheType(UndoView r0) {
        return r0.infoTextViewEmojiCacheType;
    }

    static {
    }

    public UndoView(Context r3) {
        this(r3, null, false, null);
    }

    public UndoView(Context r3, BaseFragment r4) {
        this(r3, r4, false, null);
    }

    public UndoView(Context r19, BaseFragment r20, boolean r21, Theme.ResourcesProvider r22) {
        super(r19);
        this.infoTextViewEmojiCacheType = 0;
        this.currentAccount = UserConfig.selectedAccount;
        this.currentAction = -1;
        this.bottomInset = 0;
        this.hideAnimationType = 1;
        this.enterOffsetMargin = AndroidUtilities.dp(8.0f);
        this.timeReplaceProgress = 1.0f;
        this.resourcesProvider = r22;
        this.parentFragment = r20;
        this.fromTop = r21;
        AnonymousClass1 r5 = new AnonymousClass1(this, r19, r22);
        this.infoTextView = r5;
        r5.setTextSize(1, 15.0f);
        LinkSpanDrawable.LinksTextView r52 = this.infoTextView;
        int r6 = Theme.key_undo_infoColor;
        r52.setTextColor(getThemedColor(r6));
        LinkSpanDrawable.LinksTextView r53 = this.infoTextView;
        int r7 = Theme.key_undo_cancelColor;
        r53.setLinkTextColor(getThemedColor(r7));
        this.infoTextView.setMovementMethod(new LinkMovementMethodMy(this));
        addView(this.infoTextView, LayoutHelper.createFrame(-2, -2.0f, 51, 45.0f, 13.0f, 0.0f, 0.0f));
        TextView r54 = new TextView(r19);
        this.subinfoTextView = r54;
        r54.setTextSize(1, 13.0f);
        this.subinfoTextView.setTextColor(getThemedColor(r6));
        this.subinfoTextView.setLinkTextColor(getThemedColor(r7));
        this.subinfoTextView.setHighlightColor(0);
        this.subinfoTextView.setSingleLine(true);
        this.subinfoTextView.setEllipsize(TextUtils.TruncateAt.END);
        this.subinfoTextView.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        addView(this.subinfoTextView, LayoutHelper.createFrame(-2, -2.0f, 51, 58.0f, 27.0f, 8.0f, 0.0f));
        RLottieImageView r55 = new RLottieImageView(r19);
        this.leftImageView = r55;
        r55.setScaleType(ImageView.ScaleType.CENTER);
        RLottieImageView r56 = this.leftImageView;
        int r8 = Theme.key_undo_background;
        r56.setLayerColor("info1", getThemedColor(r8) | (-16777216));
        this.leftImageView.setLayerColor("info2", getThemedColor(r8) | (-16777216));
        this.leftImageView.setLayerColor("luc12", getThemedColor(r6));
        this.leftImageView.setLayerColor("luc11", getThemedColor(r6));
        this.leftImageView.setLayerColor("luc10", getThemedColor(r6));
        this.leftImageView.setLayerColor("luc9", getThemedColor(r6));
        this.leftImageView.setLayerColor("luc8", getThemedColor(r6));
        this.leftImageView.setLayerColor("luc7", getThemedColor(r6));
        this.leftImageView.setLayerColor("luc6", getThemedColor(r6));
        this.leftImageView.setLayerColor("luc5", getThemedColor(r6));
        this.leftImageView.setLayerColor("luc4", getThemedColor(r6));
        this.leftImageView.setLayerColor("luc3", getThemedColor(r6));
        this.leftImageView.setLayerColor("luc2", getThemedColor(r6));
        this.leftImageView.setLayerColor("luc1", getThemedColor(r6));
        this.leftImageView.setLayerColor("Oval", getThemedColor(r6));
        addView(this.leftImageView, LayoutHelper.createFrame(54, -2.0f, 19, 3.0f, 0.0f, 0.0f, 0.0f));
        BackupImageView r57 = new BackupImageView(r19);
        this.avatarImageView = r57;
        r57.setRoundRadius(ExteraConfig.getAvatarCorners(30.0f));
        addView(this.avatarImageView, LayoutHelper.createFrame(30, 30.0f, 19, 15.0f, 0.0f, 0.0f, 0.0f));
        LinearLayout r58 = new LinearLayout(r19);
        this.undoButton = r58;
        r58.setOrientation(0);
        this.undoButton.setBackground(Theme.createRadSelectorDrawable(getThemedColor(r7) & 587202559, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f)));
        addView(this.undoButton, LayoutHelper.createFrame(-2, -2.0f, 21, 0.0f, 0.0f, 11.0f, 0.0f));
        this.undoButton.setOnClickListener(new UndoView$$ExternalSyntheticLambda0(this));
        ImageView r59 = new ImageView(r19);
        this.undoImageView = r59;
        r59.setImageResource(R.drawable.chats_undo);
        this.undoImageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(r7), PorterDuff.Mode.MULTIPLY));
        this.undoButton.addView(this.undoImageView, LayoutHelper.createLinear(-2, -2, 19, 4, 4, 0, 4));
        TextView r510 = new TextView(r19);
        this.undoTextView = r510;
        r510.setTextSize(1, 14.0f);
        this.undoTextView.setTypeface(AndroidUtilities.bold());
        this.undoTextView.setTextColor(getThemedColor(r7));
        this.undoTextView.setText(LocaleController.getString(R.string.UndoNoCaps));
        this.undoButton.addView(this.undoTextView, LayoutHelper.createLinear(-2, -2, 19, 6, 4, 8, 4));
        this.rect = new RectF(AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(33.0f), AndroidUtilities.dp(33.0f));
        Paint r1 = new Paint(1);
        this.progressPaint = r1;
        r1.setStyle(Paint.Style.STROKE);
        this.progressPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.progressPaint.setStrokeCap(Paint.Cap.ROUND);
        this.progressPaint.setColor(getThemedColor(r6));
        TextPaint r12 = new TextPaint(1);
        this.textPaint = r12;
        r12.setTextSize(AndroidUtilities.dp(12.0f));
        this.textPaint.setTypeface(AndroidUtilities.bold());
        this.textPaint.setColor(getThemedColor(r6));
        setWillNotDraw(false);
        this.backgroundDrawable = Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), getThemedColor(r8));
        setOnTouchListener(new UndoView$$ExternalSyntheticLambda1());
        setVisibility(4);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private boolean hasSubInfo() {
        int r0 = this.currentAction;
        if (r0 != 11) goto L5;
        return true;
    L5:
        if (r0 != 24) goto L7;
        return true;
    L7:
        if (r0 != 6) goto L9;
        return true;
    L9:
        if (r0 != 3) goto L11;
        return true;
    L11:
        if (r0 != 5) goto L13;
        return true;
    L13:
        if (r0 != 13) goto L15;
        return true;
    L15:
        if (r0 != 14) goto L17;
        return true;
    L17:
        if (r0 != 74) goto L19;
        return true;
    L19:
        if (r0 == 7) goto L21;
    L22:
        int r02 = this.currentAction;
        if (r02 != ACTION_RINGTONE_ADDED) goto L25;
        return true;
    L25:
        if (r02 != 85) goto L27;
        return true;
    L27:
        if (r02 != 88) goto L33;
        Object r2 = this.currentInfoObject2;
        if (r2 != null) goto L31;
        return false;
    L31:
        if (((Integer) r2).intValue() > 0) goto L49;
        return false;
    L49:
        return true;
    L33:
        return false;
    L21:
        if (MessagesController.getInstance(this.currentAccount).dialogFilters.isEmpty() == false) goto L22;
        return true;
    }

    private boolean isTooltipAction() {
        int r1 = this.currentAction;
        if (r1 != 6) goto L5;
        return true;
    L5:
        if (r1 != 3) goto L7;
        return true;
    L7:
        if (r1 != 5) goto L9;
        return true;
    L9:
        if (r1 != 7) goto L11;
        return true;
    L11:
        if (r1 != 8) goto L13;
        return true;
    L13:
        if (r1 != 87) goto L15;
        return true;
    L15:
        if (r1 != 9) goto L17;
        return true;
    L17:
        if (r1 != 10) goto L19;
        return true;
    L19:
        if (r1 != 13) goto L21;
        return true;
    L21:
        if (r1 != 14) goto L23;
        return true;
    L23:
        if (r1 != 19) goto L25;
        return true;
    L25:
        if (r1 != 20) goto L27;
        return true;
    L27:
        if (r1 != 21) goto L29;
        return true;
    L29:
        if (r1 != 22) goto L31;
        return true;
    L31:
        if (r1 != 23) goto L33;
        return true;
    L33:
        if (r1 != 30) goto L35;
        return true;
    L35:
        if (r1 != 31) goto L37;
        return true;
    L37:
        if (r1 != 32) goto L39;
        return true;
    L39:
        if (r1 != 102) goto L41;
        return true;
    L41:
        if (r1 != 33) goto L43;
        return true;
    L43:
        if (r1 != 34) goto L45;
        return true;
    L45:
        if (r1 != 35) goto L47;
        return true;
    L47:
        if (r1 != 36) goto L49;
        return true;
    L49:
        if (r1 != 74) goto L51;
        return true;
    L51:
        if (r1 != 37) goto L53;
        return true;
    L53:
        if (r1 != 38) goto L55;
        return true;
    L55:
        if (r1 != 39) goto L57;
        return true;
    L57:
        if (r1 != 40) goto L59;
        return true;
    L59:
        if (r1 != 42) goto L61;
        return true;
    L61:
        if (r1 != 43) goto L63;
        return true;
    L63:
        if (r1 != 77) goto L65;
        return true;
    L65:
        if (r1 != 44) goto L67;
        return true;
    L67:
        if (r1 != 78) goto L69;
        return true;
    L69:
        if (r1 != 79) goto L71;
        return true;
    L71:
        if (r1 != 100) goto L73;
        return true;
    L73:
        if (r1 != 101) goto L75;
        return true;
    L75:
        if (r1 == ACTION_RINGTONE_ADDED) goto L116;
        return false;
    L116:
        return true;
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        if (canUndo() == true) goto L5;
        return;
    L5:
        hide(false, 1);
    }

    private /* synthetic */ void lambda$showWithAction$2(View r2) {
        hide(false, 1);
    }

    private /* synthetic */ void lambda$showWithAction$4(TLObject r2) {
        if ((r2 instanceof TLRPC.PaymentReceipt) == false) goto L6;
        this.parentFragment.presentFragment(new PaymentFormActivity((TLRPC.PaymentReceipt) r2));
        return;
    }

    private /* synthetic */ void lambda$showWithAction$5(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new UndoView$$ExternalSyntheticLambda8(this, r1));
    }

    private /* synthetic */ void lambda$showWithAction$6(TLRPC.Message r2, View r3) {
        hide(true, 1);
        TLRPC.TL_payments_getPaymentReceipt r32 = new TLRPC.TL_payments_getPaymentReceipt();
        r32.msg_id = r2.id;
        r32.peer = this.parentFragment.getMessagesController().getInputPeer(r2.peer_id);
        this.parentFragment.getConnectionsManager().sendRequest(r32, new UndoView$$ExternalSyntheticLambda7(this), 2);
    }

    private /* synthetic */ void lambda$showWithAction$7(long r1) {
        BaseFragment r0 = this.parentFragment;
        if (r0 == null) goto L6;
        r0.presentFragment(ChatActivity.of(r1));
        return;
    }

    private /* synthetic */ void lambda$showWithAction$8() {
        this.leftImageView.performHapticFeedback(3, 2);     // Catch: Exception -> L4
        return;
    }

    private void updatePosition() {
        setTranslationY(((this.enterOffset - this.enterOffsetMargin) + AndroidUtilities.dp(8.0f)) - this.additionalTranslationY);
        invalidate();
    }

    public boolean canUndo() {
        return true;
    }

    public void didPressUrl(CharacterStyle r1) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r4) {
        if (this.additionalTranslationY == 0.0f) goto L9;
        r4.save();
        float r0 = (getMeasuredHeight() - this.enterOffset) + AndroidUtilities.dp(9.0f);
        if (r0 <= 0.0f) goto L7;
        r4.clipRect(0.0f, 0.0f, getMeasuredWidth(), r0);
        super.dispatchDraw(r4);
    L7:
        r4.restore();
        return;
    L9:
        super.dispatchDraw(r4);
    }

    @Override // android.view.View
    public Drawable getBackground() {
        return this.backgroundDrawable;
    }

    public Object getCurrentInfoObject() {
        return this.currentInfoObject;
    }

    @Keep
    public float getEnterOffset() {
        return this.enterOffset;
    }

    public void hide(boolean r9, int r10) {
        if (getVisibility() == 0) goto L5;
        return;
    L5:
        if (this.isShown == false) goto L55;
        this.currentInfoObject = null;
        this.currentInfoObject2 = null;
        this.isShown = false;
        Runnable r2 = this.currentActionRunnable;
        if (r2 == null) goto L12;
        if (r9 == false) goto L11;
        r2.run();
    L11:
        this.currentActionRunnable = null;
    L12:
        Runnable r22 = this.currentCancelRunnable;
        if (r22 == null) goto L17;
        if (r9 == true) goto L16;
        r22.run();
    L16:
        this.currentCancelRunnable = null;
    L17:
        int r0 = this.currentAction;
        if (r0 == 0) goto L25;
        if (r0 == 1) goto L25;
        if (r0 == 95) goto L25;
        if (r0 == 26) goto L25;
        if (r0 == 27) goto L25;
    L35:
        float r92 = 1.0f;
        if (r10 == 0) goto L47;
        AnimatorSet r23 = new AnimatorSet();
        if (r10 == 1) goto L40;
        r23.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, View.SCALE_X, new float[]{0.8f}), ObjectAnimator.ofFloat(this, View.SCALE_Y, new float[]{0.8f}), ObjectAnimator.ofFloat(this, View.ALPHA, new float[]{0.0f})});
        r23.setDuration(180);
    L44:
        r23.setInterpolator(new DecelerateInterpolator());
        r23.addListener(new AnonymousClass2(this));
        r23.start();
        return;
    L40:
        if (this.fromTop == false) goto L42;
        r92 = -1.0f;
    L42:
        r23.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "enterOffset", new float[]{r92 * ((this.enterOffsetMargin + this.undoViewHeight) + this.bottomInset)})});
        r23.setDuration(250);
        goto L44
    L47:
        if (this.fromTop == false) goto L49;
        r92 = -1.0f;
    L49:
        setEnterOffset(r92 * ((this.enterOffsetMargin + this.undoViewHeight) + this.bottomInset));
        setVisibility(4);
        return;
    L25:
        int r02 = 0;
    L27:
        if (r02 >= this.currentDialogIds.size()) goto L35;
        long r4 = this.currentDialogIds.get(r02).longValue();
        MessagesController r6 = MessagesController.getInstance(this.currentAccount);
        int r7 = this.currentAction;
        if (r7 == 0) goto L33;
        if (r7 == 26) goto L33;
        boolean r72 = false;
    L34:
        r6.removeDialogAction(r4, r72, r9);
        onRemoveDialogAction(r4, this.currentAction);
        r02 = r02 + 1;
    L33:
        r72 = true;
        goto L34
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        this.infoTextView.invalidate();
        this.leftImageView.invalidate();
    }

    public boolean isMultilineSubInfo() {
        int r1 = this.currentAction;
        if (r1 != 12) goto L5;
        return true;
    L5:
        if (r1 != 15) goto L7;
        return true;
    L7:
        if (r1 != 24) goto L9;
        return true;
    L9:
        if (r1 != 74) goto L11;
        return true;
    L11:
        if (r1 == ACTION_RINGTONE_ADDED) goto L20;
        return false;
    L20:
        return true;
    }

    @Override // android.view.View
    public void onDraw(Canvas r15) {
        if (this.additionalTranslationY == 0.0f) goto L8;
        r15.save();
        float r0 = ((getMeasuredHeight() - this.enterOffset) + this.enterOffsetMargin) + AndroidUtilities.dp(1.0f);
        if (r0 <= 0.0f) goto L7;
        r15.clipRect(0.0f, 0.0f, getMeasuredWidth(), r0);
        super.dispatchDraw(r15);
    L7:
        this.backgroundDrawable.draw(r15);
        r15.restore();
    L9:
        int r02 = this.currentAction;
        if (r02 == 1) goto L23;
        if (r02 == 95) goto L23;
        if (r02 == 0) goto L23;
        if (r02 == 27) goto L23;
        if (r02 == 26) goto L23;
        if (r02 == 81) goto L23;
        if (r02 == 88) goto L23;
    L53:
        long r03 = SystemClock.elapsedRealtime();
        long r8 = this.timeLeft - (r03 - this.lastUpdateTime);
        this.timeLeft = r8;
        this.lastUpdateTime = r03;
        if (r8 > 0) goto L57;
        hide(true, this.hideAnimationType);
    L57:
        if (this.currentAction == 82) goto L60;
        invalidate();
        return;
    L60:
        return;
    L23:
        if (this.timeLeft <= 0) goto L25;
        int r04 = (int) Math.ceil(r6 / 1000.0f);
    L27:
        if (this.prevSeconds == r04) goto L32;
        this.prevSeconds = r04;
        this.timeLeftString = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(Math.max(1, r04))});
        StaticLayout r6 = this.timeLayout;
        if (r6 == null) goto L31;
        this.timeLayoutOut = r6;
        this.timeReplaceProgress = 0.0f;
        this.textWidthOut = this.textWidth;
    L31:
        this.textWidth = (int) Math.ceil(this.textPaint.measureText(r0));
        this.timeLayout = new StaticLayout(this.timeLeftString, this.textPaint, Integer.MAX_VALUE, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
    L32:
        float r05 = this.timeReplaceProgress;
        if (r05 >= 1.0f) goto L38;
        float r06 = r05 + 0.10666667f;
        this.timeReplaceProgress = r06;
        if (r06 <= 1.0f) goto L37;
        this.timeReplaceProgress = 1.0f;
        goto L38
    L37:
        invalidate();
    L38:
        int r07 = this.textPaint.getAlpha();
        if (this.timeLayoutOut == null) goto L44;
        float r1 = this.timeReplaceProgress;
        if (r1 >= 1.0f) goto L44;
        this.textPaint.setAlpha((int) (r07 * (1.0f - r1)));
        r15.save();
        r15.translate(this.rect.centerX() - (this.textWidth / 2), AndroidUtilities.dp(17.2f) + (AndroidUtilities.dp(10.0f) * this.timeReplaceProgress));
        this.timeLayoutOut.draw(r15);
        this.textPaint.setAlpha(r07);
        r15.restore();
    L44:
        if (this.timeLayout == null) goto L52;
        float r12 = this.timeReplaceProgress;
        if (r12 == 1.0f) goto L48;
        this.textPaint.setAlpha((int) (r07 * r12));
    L48:
        r15.save();
        r15.translate(this.rect.centerX() - (this.textWidth / 2), AndroidUtilities.dp(17.2f) - (AndroidUtilities.dp(10.0f) * (1.0f - this.timeReplaceProgress)));
        this.timeLayout.draw(r15);
        if (this.timeReplaceProgress == 1.0f) goto L51;
        this.textPaint.setAlpha(r07);
    L51:
        r15.restore();
    L52:
        r15.drawArc(this.rect, -90.0f, (this.timeLeft / 5000.0f) * (-360.0f), false, this.progressPaint);
        goto L53
    L25:
        r04 = 0;
        goto L27
    L8:
        this.backgroundDrawable.draw(r15);
        goto L9
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.undoViewHeight, TLObject.FLAG_30));
        this.backgroundDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public void onRemoveDialogAction(long r1, int r3) {
    }

    public void setAdditionalTranslationY(float r2) {
        if (this.additionalTranslationY == r2) goto L6;
        this.additionalTranslationY = r2;
        updatePosition();
        return;
    }

    public void setBottomInset(int r1) {
        this.bottomInset = r1;
    }

    public void setColors(int r2, int r3) {
        Theme.setDrawableColor(this.backgroundDrawable, r2);
        this.infoTextView.setTextColor(r3);
        this.subinfoTextView.setTextColor(r3);
        int r22 = r2 | (-16777216);
        this.leftImageView.setLayerColor("info1", r22);
        this.leftImageView.setLayerColor("info2", r22);
    }

    @Keep
    public void setEnterOffset(float r2) {
        if (this.enterOffset == r2) goto L6;
        this.enterOffset = r2;
        updatePosition();
        return;
    }

    public void setEnterOffsetMargin(int r1) {
        this.enterOffsetMargin = r1;
    }

    public void setHideAnimationType(int r1) {
        this.hideAnimationType = r1;
    }

    public void setInfoText(CharSequence r1) {
        this.infoText = r1;
    }

    public void showWithAction(long r9, int r11, Object r12) {
        showWithAction(r9, r11, r12, null, null, null);
    }

    public void showWithAction(long r3, int r5, Object r6, Object r7, Runnable r8, Runnable r9) {
        ArrayList<Long> r32 = new ArrayList();
        r32.add(Long.valueOf(r3));
        showWithAction(r32, r5, r6, r7, r8, r9);
    }

    public void showWithAction(long r9, int r11, Object r12, Runnable r13, Runnable r14) {
        showWithAction(r9, r11, r12, null, r13, r14);
    }

    public void showWithAction(long r9, int r11, Runnable r12) {
        showWithAction(r9, r11, null, null, r12, null);
    }

    public void showWithAction(long r9, int r11, Runnable r12, Runnable r13) {
        showWithAction(r9, r11, null, null, r12, r13);
    }

    public void showWithAction(ArrayList<Long> r29, int r30, Object r31, Object r32, Runnable r33, Runnable r34) {
        if (AndroidUtilities.shouldShowClipboardToast() == true) goto L15;
        int r8 = this.currentAction;
        if (r8 == 52) goto L724;
        if (r8 == 56) goto L725;
        if (r8 == 57) goto L726;
        if (r8 == 58) goto L727;
        if (r8 == 59) goto L728;
        if (r8 != 60) goto L12;
        return;
    L12:
        if (r8 == 80) goto L730;
        if (r8 != 33) goto L15;
        return;
    L730:
        return;
    L728:
        return;
    L727:
        return;
    L726:
        return;
    L725:
        return;
    L724:
        return;
    L15:
        Runnable r82 = this.currentActionRunnable;
        if (r82 == null) goto L18;
        r82.run();
    L18:
        this.isShown = true;
        this.currentActionRunnable = r33;
        this.currentCancelRunnable = r34;
        this.currentDialogIds = r29;
        Long r12 = r29.get(0);
        long r13 = r12.longValue();
        this.currentAction = r30;
        this.timeLeft = 5000;
        this.currentInfoObject = r31;
        this.currentInfoObject2 = r32;
        this.lastUpdateTime = SystemClock.elapsedRealtime();
        this.undoTextView.setText(LocaleController.getString(R.string.UndoNoCaps));
        this.undoImageView.setVisibility(0);
        this.leftImageView.setPadding(0, 0, 0, 0);
        this.leftImageView.setScaleX(1.0f);
        this.leftImageView.setScaleY(1.0f);
        this.infoTextView.setTextSize(1, 15.0f);
        this.avatarImageView.setVisibility(8);
        this.infoTextView.setGravity(51);
        ((FrameLayout.LayoutParams) this.subinfoTextView.getLayoutParams()).leftMargin = AndroidUtilities.dp(58.0f);
        FrameLayout.LayoutParams r9 = (FrameLayout.LayoutParams) this.infoTextView.getLayoutParams();
        r9.height = -2;
        r9.topMargin = AndroidUtilities.dp(13.0f);
        r9.bottomMargin = 0;
        this.leftImageView.setScaleType(ImageView.ScaleType.CENTER);
        FrameLayout.LayoutParams r83 = (FrameLayout.LayoutParams) this.leftImageView.getLayoutParams();
        r83.gravity = 19;
        r83.bottomMargin = 0;
        r83.topMargin = 0;
        r83.leftMargin = AndroidUtilities.dp(3.0f);
        r83.width = AndroidUtilities.dp(54.0f);
        r83.height = -2;
        this.infoTextView.setMinHeight(0);
        CharSequence r15 = null;
        if (r33 != null) goto L22;
        if (r34 != null) goto L22;
    L23:
        setOnClickListener(new UndoView$$ExternalSyntheticLambda2(this));
        setOnTouchListener(null);
    L25:
        this.infoTextView.setMovementMethod(null);
        int r10 = 36;
        if (isTooltipAction() == true) goto L28;
        String r6 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r5 = this.currentAction;
        if (r5 != 45) goto L311;
    L518:
        this.undoImageView.setVisibility(8);
        this.leftImageView.setVisibility(0);
        this.infoTextView.setTypeface(Typeface.DEFAULT);
        int r0 = this.currentAction;
        long r26 = -1;
        if (r0 != 76) goto L523;
        this.infoTextView.setText(LocaleController.getString(R.string.BroadcastGroupConvertSuccess));
        this.leftImageView.setAnimation(R.raw.gigagroup_convert, 36, 36);
        r9.topMargin = AndroidUtilities.dp(9.0f);
        float r2 = 14.0f;
        this.infoTextView.setTextSize(1, 14.0f);
    L521:
        long r3 = -1;
        boolean r02 = true;
    L660:
        this.subinfoTextView.setVisibility(8);
        this.undoTextView.setTextColor(getThemedColor(Theme.key_undo_cancelColor));
        this.undoButton.setVisibility(8);
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        r9.rightMargin = AndroidUtilities.dp(8.0f);
        this.leftImageView.setProgress(0.0f);
        this.leftImageView.playAnimation();
        if (r3 <= 0) goto L663;
        this.leftImageView.postDelayed(new UndoView$$ExternalSyntheticLambda6(this), r3);
    L663:
        StringBuilder r35 = new StringBuilder();
        r35.append(this.infoTextView.getText());
        if (this.subinfoTextView.getVisibility() != 0) goto L666;
        String r11 = ". " + this.subinfoTextView.getText();
    L667:
        r35.append(r11);
        AndroidUtilities.makeAccessibilityAnnouncement(r35.toString());
        if (isMultilineSubInfo() == false) goto L674;
        int r03 = ((ViewGroup) getParent()).getMeasuredWidth();
        if (r03 != 0) goto L672;
        r03 = AndroidUtilities.displaySize.x;
    L672:
        measureChildWithMargins(this.subinfoTextView, View.MeasureSpec.makeMeasureSpec(r03 - AndroidUtilities.dp(16.0f), TLObject.FLAG_30), 0, View.MeasureSpec.makeMeasureSpec(0, 0), 0);
        this.undoViewHeight = this.subinfoTextView.getMeasuredHeight() + AndroidUtilities.dp(37.0f);
    L703:
        if (getVisibility() == 0) goto L731;
        setVisibility(0);
        if (this.fromTop == false) goto L707;
        float r04 = -1.0f;
    L708:
        setEnterOffset(r04 * ((this.enterOffsetMargin + this.undoViewHeight) + this.bottomInset));
        AnimatorSet r05 = new AnimatorSet();
        boolean r36 = this.fromTop;
        if (r36 == false) goto L711;
        float r4 = -1.0f;
    L712:
        float r42 = r4 * ((this.enterOffsetMargin + this.undoViewHeight) + this.bottomInset);
        if (r36 == false) goto L716;
        float r102 = 1.0f;
    L717:
        r05.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "enterOffset", new float[]{r42, r102})});
        r05.setInterpolator(new DecelerateInterpolator());
        r05.setDuration(180);
        r05.start();
        return;
    L716:
        r102 = -1.0f;
        goto L717
    L711:
        r4 = 1.0f;
        goto L712
    L707:
        r04 = 1.0f;
        goto L708
    L731:
        return;
    L674:
        if (hasSubInfo() == false) goto L677;
        this.undoViewHeight = AndroidUtilities.dp(52.0f);
        goto L703
    L677:
        if ((getParent() instanceof ViewGroup) == false) goto L703;
        ViewGroup r37 = (ViewGroup) getParent();
        int r43 = (r37.getMeasuredWidth() - r37.getPaddingLeft()) - r37.getPaddingRight();
        if (r43 > 0) goto L681;
        r43 = AndroidUtilities.displaySize.x;
    L681:
        measureChildWithMargins(this.infoTextView, View.MeasureSpec.makeMeasureSpec(r43 - AndroidUtilities.dp(16.0f), TLObject.FLAG_30), 0, View.MeasureSpec.makeMeasureSpec(0, 0), 0);
        int r38 = this.infoTextView.getMeasuredHeight();
        int r44 = this.currentAction;
        if (r44 != 16) goto L684;
    L693:
        float r103 = r2;
    L694:
        int r39 = r38 + AndroidUtilities.dp(r103);
        this.undoViewHeight = r39;
        int r22 = this.currentAction;
        if (r22 != 18) goto L698;
        this.undoViewHeight = Math.max(r39, AndroidUtilities.dp(52.0f));
        goto L703
    L698:
        if (r22 != 25) goto L700;
        this.undoViewHeight = Math.max(r39, AndroidUtilities.dp(50.0f));
        goto L703
    L700:
        if (r02 == false) goto L703;
        this.undoViewHeight = r39 - AndroidUtilities.dp(8.0f);
        goto L703
    L684:
        if (r44 == 17) goto L693;
        if (r44 == 18) goto L693;
        if (r44 == 84) goto L693;
        if (r44 == 86) goto L693;
        r103 = 28.0f;
        goto L694
    L666:
        r11 = r6;
        goto L667
    L523:
        if (r0 != 75) goto L526;
        this.infoTextView.setText(LocaleController.getString(R.string.GigagroupConvertCancelHint));
        this.leftImageView.setAnimation(R.raw.chats_infotip, 36, 36);
        r9.topMargin = AndroidUtilities.dp(9.0f);
        r2 = 14.0f;
        this.infoTextView.setTextSize(1, 14.0f);
        goto L521
    L526:
        if (r30 != 70) goto L529;
        TLRPC.User r06 = (TLRPC.User) r31;
        int r07 = ((Integer) r32).intValue();
        this.subinfoTextView.setSingleLine(false);
        this.infoTextView.setText(LocaleController.formatString("AutoDeleteHintOnText", R.string.AutoDeleteHintOnText, new Object[]{LocaleController.formatTTLString(r07)}));
        this.leftImageView.setAnimation(R.raw.fire_on, 36, 36);
        r9.topMargin = AndroidUtilities.dp(9.0f);
        this.timeLeft = 4000;
        this.leftImageView.setPadding(0, 0, 0, AndroidUtilities.dp(3.0f));
        r3 = -1;
        r02 = true;
        r2 = 14.0f;
        goto L660
    L529:
        if (r0 != 71) goto L532;
        this.infoTextView.setText(LocaleController.getString(R.string.AutoDeleteHintOffText));
        this.leftImageView.setAnimation(R.raw.fire_off, 36, 36);
        this.infoTextView.setTextSize(1, 14.0f);
        this.timeLeft = 3000;
        this.leftImageView.setPadding(0, 0, 0, AndroidUtilities.dp(4.0f));
        r2 = 14.0f;
    L621:
        r3 = r26;
        r02 = false;
        goto L660
    L532:
        if (r0 != 45) goto L535;
        this.infoTextView.setText(LocaleController.getString(R.string.ImportMutualError));
        this.leftImageView.setAnimation(R.raw.error, 36, 36);
        r9.topMargin = AndroidUtilities.dp(9.0f);
        r2 = 14.0f;
        this.infoTextView.setTextSize(1, 14.0f);
        goto L521
    L535:
        if (r0 != 46) goto L538;
        this.infoTextView.setText(LocaleController.getString(R.string.ImportNotAdmin));
        this.leftImageView.setAnimation(R.raw.error, 36, 36);
        r9.topMargin = AndroidUtilities.dp(9.0f);
        r2 = 14.0f;
        this.infoTextView.setTextSize(1, 14.0f);
        goto L521
    L538:
        if (r0 != 47) goto L540;
        this.infoTextView.setText(LocaleController.getString(R.string.ImportedInfo));
        this.leftImageView.setAnimation(R.raw.imported, 36, 36);
        this.leftImageView.setPadding(0, 0, 0, AndroidUtilities.dp(5.0f));
        r9.topMargin = AndroidUtilities.dp(9.0f);
        r2 = 14.0f;
        this.infoTextView.setTextSize(1, 14.0f);
        goto L521
    L540:
        r2 = 14.0f;
        if (r0 == 52) goto L638;
        if (r0 == 56) goto L638;
        if (r0 == 57) goto L638;
        if (r0 == 58) goto L638;
        if (r0 == 59) goto L638;
        if (r0 == 60) goto L638;
        if (r0 == 80) goto L638;
        if (r0 != 54) goto L559;
        this.infoTextView.setText(LocaleController.getString(R.string.ChannelNotifyMembersInfoOn));
        this.leftImageView.setAnimation(R.raw.silent_unmute, 30, 30);
        this.timeLeft = 3000;
        this.infoTextView.setTextSize(1, 15.0f);
        goto L621
    L559:
        if (r0 != 55) goto L562;
        this.infoTextView.setText(LocaleController.getString(R.string.ChannelNotifyMembersInfoOff));
        this.leftImageView.setAnimation(R.raw.silent_mute, 30, 30);
        this.timeLeft = 3000;
        this.infoTextView.setTextSize(1, 15.0f);
        goto L621
    L562:
        if (r0 != 41) goto L574;
        if (r32 == null) goto L565;
        this.infoTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("InvLinkToChats", R.string.InvLinkToChats, new Object[]{LocaleController.formatPluralString("Chats", ((Integer) r32).intValue(), new Object[0])})));
    L572:
        this.leftImageView.setAnimation(R.raw.contact_check, 36, 36);
        this.timeLeft = 3000;
        goto L621
    L565:
        if (r13 != UserConfig.getInstance(this.currentAccount).clientUserId) goto L567;
        this.infoTextView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.InvLinkToSavedMessages)));
        goto L572
    L567:
        boolean r08 = DialogObject.isChatDialog(r13);
        int r310 = this.currentAccount;
        if (r08 == false) goto L570;
        this.infoTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("InvLinkToGroup", R.string.InvLinkToGroup, new Object[]{MessagesController.getInstance(r310).getChat(Long.valueOf(-r13)).title})));
        goto L572
    L570:
        this.infoTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("InvLinkToUser", R.string.InvLinkToUser, new Object[]{UserObject.getFirstName(MessagesController.getInstance(r310).getUser(r12))})));
        goto L572
    L574:
        if (r0 != 53) goto L623;
        Integer r09 = (Integer) r31;
        if (r32 == null) goto L588;
        if ((r32 instanceof TLRPC.TL_forumTopic) == true) goto L588;
        int r311 = ((Integer) r32).intValue();
        int r010 = r09.intValue();
        LinkSpanDrawable.LinksTextView r45 = this.infoTextView;
        if (r010 != 1) goto L583;
        r45.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralString("FwdMessageToManyChats", r311, new Object[0])));
    L584:
        this.leftImageView.setAnimation(R.raw.forward, 30, 30);
    L585:
        r26 = 300;
    L620:
        this.timeLeft = 3000;
        goto L621
    L583:
        r45.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralString("FwdMessagesToManyChats", r311, new Object[0])));
    L588:
        if (r13 != UserConfig.getInstance(this.currentAccount).clientUserId) goto L594;
        int r011 = r09.intValue();
        LinkSpanDrawable.LinksTextView r312 = this.infoTextView;
        if (r011 != 1) goto L592;
        r312.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.FwdMessageToSavedMessages), -1, 2, new BulletinFactory$$ExternalSyntheticLambda5()));
    L593:
        this.leftImageView.setAnimation(R.raw.saved_messages, 30, 30);
        goto L620
    L592:
        r312.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.FwdMessagesToSavedMessages), -1, 2, new BulletinFactory$$ExternalSyntheticLambda5()));
        goto L593
    L594:
        UndoView$$ExternalSyntheticLambda5 r313 = new UndoView$$ExternalSyntheticLambda5(this, r13);
        boolean r52 = DialogObject.isChatDialog(r13);
        int r7 = this.currentAccount;
        if (r52 == false) goto L615;
        TLRPC.Chat r53 = MessagesController.getInstance(r7).getChat(Long.valueOf(-r13));
        TLRPC.TL_forumTopic r46 = (TLRPC.TL_forumTopic) r32;
        if (ChatObject.isMonoForum(r53) == false) goto L599;
        r15 = ForumUtilities.getMonoForumTitle(this.currentAccount, r53);
    L599:
        int r012 = r09.intValue();
        LinkSpanDrawable.LinksTextView r72 = this.infoTextView;
        if (r012 != 1) goto L608;
        int r013 = R.string.FwdMessageToGroup;
        if (r15 != null) goto L607;
        if (r46 == null) goto L606;
        r15 = r46.title;
        goto L607
    L606:
        r15 = r53.title;
    L607:
        r72.setText(AndroidUtilities.replaceSingleTag(LocaleController.formatString("FwdMessageToGroup", r013, new Object[]{r15}), -1, 2, r313));
    L619:
        this.leftImageView.setAnimation(R.raw.forward, 30, 30);
        goto L585
    L608:
        int r014 = R.string.FwdMessagesToGroup;
        if (r15 != null) goto L614;
        if (r46 == null) goto L613;
        r15 = r46.title;
        goto L614
    L613:
        r15 = r53.title;
    L614:
        r72.setText(AndroidUtilities.replaceSingleTag(LocaleController.formatString("FwdMessagesToGroup", r014, new Object[]{r15}), -1, 2, r313));
        goto L619
    L615:
        TLRPC.User r47 = MessagesController.getInstance(r7).getUser(r12);
        int r015 = r09.intValue();
        LinkSpanDrawable.LinksTextView r54 = this.infoTextView;
        if (r015 != 1) goto L618;
        r54.setText(AndroidUtilities.replaceSingleTag(LocaleController.formatString("FwdMessageToUser", R.string.FwdMessageToUser, new Object[]{UserObject.getFirstName(r47)}), -1, 2, r313));
        goto L619
    L618:
        r54.setText(AndroidUtilities.replaceSingleTag(LocaleController.formatString("FwdMessagesToUser", R.string.FwdMessagesToUser, new Object[]{UserObject.getFirstName(r47)}), -1, 2, r313));
        goto L619
    L623:
        if (r0 != 61) goto L621;
        Integer r016 = (Integer) r31;
        if (r32 == null) goto L627;
        this.infoTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("BackgroundToChats", R.string.BackgroundToChats, new Object[]{LocaleController.formatPluralString("Chats", ((Integer) r32).intValue(), new Object[0])})));
        this.leftImageView.setAnimation(R.raw.forward, 30, 30);
    L636:
        this.timeLeft = 3000;
        goto L621
    L627:
        if (r13 != UserConfig.getInstance(this.currentAccount).clientUserId) goto L630;
        this.infoTextView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.BackgroundToSavedMessages)));
        this.leftImageView.setAnimation(R.raw.saved_messages, 30, 30);
        goto L636
    L630:
        boolean r017 = DialogObject.isChatDialog(r13);
        int r314 = this.currentAccount;
        if (r017 == false) goto L633;
        this.infoTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("BackgroundToGroup", R.string.BackgroundToGroup, new Object[]{MessagesController.getInstance(r314).getChat(Long.valueOf(-r13)).title})));
    L634:
        this.leftImageView.setAnimation(R.raw.forward, 30, 30);
        goto L636
    L633:
        this.infoTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("BackgroundToUser", R.string.BackgroundToUser, new Object[]{UserObject.getFirstName(MessagesController.getInstance(r314).getUser(r12))})));
    L638:
        if (AndroidUtilities.shouldShowClipboardToast() == false) goto L733;
        int r018 = R.raw.copy;
        int r315 = this.currentAction;
        if (r315 != 80) goto L644;
        this.infoTextView.setText(LocaleController.getString(R.string.EmailCopied));
    L659:
        this.leftImageView.setAnimation(r018, 30, 30);
        this.timeLeft = 3000;
        this.infoTextView.setTextSize(1, 15.0f);
        goto L621
    L644:
        if (r315 != 60) goto L647;
        this.infoTextView.setText(LocaleController.getString(R.string.PhoneCopied));
        goto L659
    L647:
        if (r315 != 56) goto L650;
        this.infoTextView.setText(LocaleController.getString(R.string.UsernameCopied));
        goto L659
    L650:
        if (r315 != 57) goto L653;
        this.infoTextView.setText(LocaleController.getString(R.string.HashtagCopied));
        goto L659
    L653:
        if (r315 != 52) goto L655;
        this.infoTextView.setText(LocaleController.getString(R.string.MessageCopied));
        goto L659
    L655:
        LinkSpanDrawable.LinksTextView r48 = this.infoTextView;
        if (r315 != 59) goto L658;
        r018 = R.raw.voip_invite;
        r48.setText(LocaleController.getString(R.string.LinkCopied));
        goto L659
    L658:
        r48.setText(LocaleController.getString(R.string.TextCopied));
        goto L659
    L733:
        return;
    L311:
        if (r5 == 46) goto L518;
        if (r5 == 47) goto L518;
        if (r5 == 52) goto L518;
        if (r5 == 53) goto L518;
        if (r5 == 54) goto L518;
        if (r5 == 55) goto L518;
        if (r5 == 56) goto L518;
        if (r5 == 57) goto L518;
        if (r5 == 58) goto L518;
        if (r5 == 59) goto L518;
        if (r5 == 60) goto L518;
        if (r5 == 71) goto L518;
        if (r5 == 70) goto L518;
        if (r5 == 75) goto L518;
        if (r5 == 76) goto L518;
        if (r5 == 41) goto L518;
        if (r5 == 78) goto L518;
        if (r5 == 79) goto L518;
        if (r5 == 61) goto L518;
        if (r5 == 80) goto L518;
        if (r5 == 24) goto L508;
        if (r5 == 25) goto L508;
        if (r5 != 11) goto L360;
        this.infoTextView.setText(LocaleController.getString(R.string.AuthAnotherClientOk));
        this.leftImageView.setAnimation(R.raw.contact_check, 36, 36);
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        r9.topMargin = AndroidUtilities.dp(6.0f);
        this.subinfoTextView.setText(((TLRPC.TL_authorization) r31).app_name);
        this.subinfoTextView.setVisibility(0);
        this.infoTextView.setTextSize(1, 14.0f);
        this.infoTextView.setTypeface(AndroidUtilities.bold());
        this.undoTextView.setTextColor(getThemedColor(Theme.key_text_RedRegular));
        this.undoImageView.setVisibility(8);
        this.undoButton.setVisibility(0);
        this.leftImageView.setVisibility(0);
        this.leftImageView.setProgress(0.0f);
        this.leftImageView.playAnimation();
    L517:
        r02 = false;
        r2 = 14.0f;
        goto L663
    L360:
        if (r5 != 15) goto L368;
        this.timeLeft = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        this.undoTextView.setText(LocaleController.getString(R.string.Open));
        this.infoTextView.setText(LocaleController.getString(R.string.FilterAvailableTitle));
        this.leftImageView.setAnimation(R.raw.filter_new, 36, 36);
        int r019 = ((int) Math.ceil(this.undoTextView.getPaint().measureText(this.undoTextView.getText().toString()))) + AndroidUtilities.dp(26.0f);
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        r9.rightMargin = r019;
        r9.topMargin = AndroidUtilities.dp(6.0f);
        ((FrameLayout.LayoutParams) this.subinfoTextView.getLayoutParams()).rightMargin = r019;
        String r020 = LocaleController.getString(R.string.FilterAvailableText);
        SpannableStringBuilder r23 = new SpannableStringBuilder(r020);
        int r316 = r020.indexOf(42);
        int r021 = r020.lastIndexOf(42);
        if (r316 < 0) goto L366;
        if (r021 < 0) goto L366;
        if (r316 == r021) goto L366;
        r23.replace(r021, r021 + 1, r6);
        r23.replace(r316, r316 + 1, r6);
        r23.setSpan(new URLSpanNoUnderline("tg://settings/folders"), r316, r021 - 1, 33);
    L366:
        this.subinfoTextView.setText(r23);
        this.subinfoTextView.setVisibility(0);
        this.subinfoTextView.setSingleLine(false);
        this.subinfoTextView.setMaxLines(2);
        this.undoButton.setVisibility(0);
        this.undoImageView.setVisibility(8);
        this.leftImageView.setVisibility(0);
        this.leftImageView.setProgress(0.0f);
        this.leftImageView.playAnimation();
        goto L517
    L368:
        if (r5 == 16) goto L492;
        if (r5 == 17) goto L492;
        if (r5 != 18) goto L376;
        this.timeLeft = Math.max(4000, Math.min((r0.length() / 50) * 1600, 10000));
        this.infoTextView.setTextSize(1, 14.0f);
        this.infoTextView.setGravity(16);
        this.infoTextView.setText((CharSequence) r31);
        this.undoTextView.setVisibility(8);
        this.undoButton.setVisibility(8);
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        r9.rightMargin = AndroidUtilities.dp(8.0f);
        r9.topMargin = AndroidUtilities.dp(6.0f);
        r9.bottomMargin = AndroidUtilities.dp(7.0f);
        r9.height = -1;
        r83.gravity = 51;
        int r022 = AndroidUtilities.dp(8.0f);
        r83.bottomMargin = r022;
        r83.topMargin = r022;
        this.leftImageView.setVisibility(0);
        this.leftImageView.setAnimation(R.raw.chats_infotip, 36, 36);
        this.leftImageView.setProgress(0.0f);
        this.leftImageView.playAnimation();
        this.infoTextView.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        goto L517
    L376:
        if (r5 != 12) goto L384;
        this.infoTextView.setText(LocaleController.getString(R.string.ColorThemeChanged));
        this.leftImageView.setImageResource(R.drawable.toast_pallete);
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        r9.rightMargin = AndroidUtilities.dp(48.0f);
        r9.topMargin = AndroidUtilities.dp(6.0f);
        ((FrameLayout.LayoutParams) this.subinfoTextView.getLayoutParams()).rightMargin = AndroidUtilities.dp(48.0f);
        String r023 = LocaleController.getString(R.string.ColorThemeChangedInfo);
        SpannableStringBuilder r24 = new SpannableStringBuilder(r023);
        int r317 = r023.indexOf(42);
        int r024 = r023.lastIndexOf(42);
        if (r317 < 0) goto L382;
        if (r024 < 0) goto L382;
        if (r317 == r024) goto L382;
        r24.replace(r024, r024 + 1, r6);
        r24.replace(r317, r317 + 1, r6);
        r24.setSpan(new URLSpanNoUnderline("tg://settings/themes"), r317, r024 - 1, 33);
    L382:
        this.subinfoTextView.setText(r24);
        this.subinfoTextView.setVisibility(0);
        this.subinfoTextView.setSingleLine(false);
        this.subinfoTextView.setMaxLines(2);
        this.undoTextView.setVisibility(8);
        this.undoButton.setVisibility(0);
        this.leftImageView.setVisibility(0);
        goto L517
    L384:
        if (r5 != 84) goto L387;
        this.infoTextView.setVisibility(0);
        this.infoTextView.setTextSize(1, 15.0f);
        this.infoTextView.setTypeface(Typeface.DEFAULT);
        this.infoTextView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.UnlockPremiumTranscriptionHint)));
        this.leftImageView.setVisibility(0);
        this.leftImageView.setAnimation(R.raw.voice_to_text, 36, 36);
        this.leftImageView.setProgress(0.0f);
        this.leftImageView.playAnimation();
        this.undoTextView.setText(LocaleController.getString(R.string.PremiumMore));
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        r9.rightMargin = ((int) Math.ceil(this.undoTextView.getPaint().measureText(this.undoTextView.getText().toString()))) + AndroidUtilities.dp(26.0f);
        int r025 = AndroidUtilities.dp(6.0f);
        r9.bottomMargin = r025;
        r9.topMargin = r025;
        r9.height = -2;
        this.avatarImageView.setVisibility(8);
        this.subinfoTextView.setVisibility(8);
        this.undoTextView.setVisibility(0);
        this.undoButton.setVisibility(0);
        this.undoImageView.setVisibility(8);
        goto L517
    L387:
        if (r5 != 85) goto L390;
        this.infoTextView.setVisibility(0);
        this.infoTextView.setTextSize(1, 15.0f);
        this.infoTextView.setTypeface(AndroidUtilities.bold());
        this.infoTextView.setText(LocaleController.getString(R.string.SwipeToReplyHint));
        this.leftImageView.setVisibility(0);
        this.leftImageView.setAnimation(R.raw.hint_swipe_reply, 64, 64);
        this.leftImageView.setProgress(0.0f);
        this.leftImageView.playAnimation();
        this.subinfoTextView.setVisibility(0);
        this.subinfoTextView.setText(LocaleController.getString(R.string.SwipeToReplyHintMessage));
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        r9.rightMargin = ((int) Math.ceil(this.undoTextView.getPaint().measureText(this.undoTextView.getText().toString()))) + AndroidUtilities.dp(26.0f);
        r9.topMargin = AndroidUtilities.dp(6.0f);
        r9.height = -2;
        this.avatarImageView.setVisibility(8);
        this.undoButton.setVisibility(8);
        goto L517
    L390:
        if (r5 != 90) goto L392;
    L484:
        switch(r5) {
            case 90: goto L490;
            case 91: goto L489;
            case 92: goto L488;
            case 93: goto L487;
            case 94: goto L486;
            default: goto L491;
        };
    L486:
        this.infoTextView.setText(LocaleController.getString(R.string.BoostingOnlyRecipientCode));
        goto L491
    L487:
        this.infoTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingWaitWarningPlural", BoostRepository.boostsPerSentGift(), new Object[0])));
        goto L491
    L488:
        this.infoTextView.setText(LocaleController.formatPluralString("BoostingSelectUpToWarningCountriesPlural", (int) BoostRepository.giveawayCountriesMax(), new Object[0]));
        goto L491
    L489:
        this.infoTextView.setText(LocaleController.getString(R.string.BoostingSelectUpToWarningUsers));
        goto L491
    L490:
        this.infoTextView.setText(LocaleController.formatPluralString("BoostingSelectUpToWarningChannelsGroupsPlural", (int) BoostRepository.giveawayAddPeersMax(), new Object[0]));
    L491:
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        r9.rightMargin = AndroidUtilities.dp(8.0f);
        this.infoTextView.setTextSize(1, 15.0f);
        this.undoButton.setVisibility(8);
        this.infoTextView.setTypeface(Typeface.DEFAULT);
        this.subinfoTextView.setVisibility(8);
        this.leftImageView.setVisibility(0);
        this.leftImageView.setAnimation(R.raw.chats_infotip, 36, 36);
        this.leftImageView.setProgress(0.0f);
        this.leftImageView.playAnimation();
        goto L517
    L392:
        if (r5 == 91) goto L484;
        if (r5 == 92) goto L484;
        if (r5 == 93) goto L484;
        if (r5 == 94) goto L484;
        if (r5 != 2) goto L403;
    L479:
        LinkSpanDrawable.LinksTextView r026 = this.infoTextView;
        if (r30 != 2) goto L482;
        r026.setText(LocaleController.getString(R.string.ChatArchived));
    L483:
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        r9.topMargin = AndroidUtilities.dp(13.0f);
        r9.rightMargin = 0;
        this.infoTextView.setTextSize(1, 15.0f);
        this.undoButton.setVisibility(0);
        this.infoTextView.setTypeface(Typeface.DEFAULT);
        this.subinfoTextView.setVisibility(8);
        this.leftImageView.setVisibility(0);
        this.leftImageView.setAnimation(R.raw.chats_archived, 36, 36);
        this.leftImageView.setProgress(0.0f);
        this.leftImageView.playAnimation();
        goto L517
    L482:
        r026.setText(LocaleController.getString(R.string.ChatsArchived));
        goto L483
    L403:
        if (r5 == 4) goto L479;
        if (r30 != 82) goto L422;
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        MediaController.PhotoEntry r027 = (MediaController.PhotoEntry) r31;
        LinkSpanDrawable.LinksTextView r25 = this.infoTextView;
        if (r027.isVideo == false) goto L411;
        int r318 = R.string.AttachMediaVideoDeselected;
    L412:
        r25.setText(LocaleController.getString(r318));
        this.undoButton.setVisibility(0);
        this.infoTextView.setTextSize(1, 15.0f);
        this.infoTextView.setTypeface(Typeface.DEFAULT);
        this.subinfoTextView.setVisibility(8);
        this.avatarImageView.setVisibility(0);
        this.avatarImageView.setRoundRadius(AndroidUtilities.dp(2.0f));
        String r27 = r027.thumbPath;
        if (r27 == null) goto L415;
        this.avatarImageView.setImage(r27, null, Theme.chat_attachEmptyDrawable);
        goto L517
    L415:
        String r28 = r027.path;
        BackupImageView r319 = this.avatarImageView;
        if (r28 == null) goto L421;
        r319.setOrientation(r027.orientation, r027.invert, true);
        boolean r210 = r027.isVideo;
        BackupImageView r320 = this.avatarImageView;
        if (r210 == false) goto L420;
        r320.setImage("vthumb://" + r027.imageId + ":" + r027.path, null, Theme.chat_attachEmptyDrawable);
        goto L517
    L420:
        r320.setImage("thumb://" + r027.imageId + ":" + r027.path, null, Theme.chat_attachEmptyDrawable);
        goto L517
    L421:
        r319.setImageDrawable(Theme.chat_attachEmptyDrawable);
        goto L517
    L411:
        r318 = R.string.AttachMediaPhotoDeselected;
        goto L412
    L422:
        r9.leftMargin = AndroidUtilities.dp(45.0f);
        r9.topMargin = AndroidUtilities.dp(13.0f);
        r9.rightMargin = 0;
        this.infoTextView.setTextSize(1, 15.0f);
        this.undoButton.setVisibility(0);
        LinkSpanDrawable.LinksTextView r211 = this.infoTextView;
        Typeface r55 = Typeface.DEFAULT;
        r211.setTypeface(r55);
        this.subinfoTextView.setVisibility(8);
        this.leftImageView.setVisibility(8);
        int r212 = this.currentAction;
        if (r212 != 88) goto L432;
        String r213 = (String) r31;
        int r321 = ((Integer) r32).intValue();
        if (r321 <= 0) goto L427;
        int r49 = ((int) Math.ceil(this.undoTextView.getPaint().measureText(this.undoTextView.getText().toString()))) + AndroidUtilities.dp(26.0f);
        r9.leftMargin = AndroidUtilities.dp(48.0f);
        r9.rightMargin = r49;
        r9.topMargin = AndroidUtilities.dp(6.0f);
        FrameLayout.LayoutParams r56 = (FrameLayout.LayoutParams) this.subinfoTextView.getLayoutParams();
        r56.leftMargin = AndroidUtilities.dp(48.0f);
        r56.rightMargin = r49;
        this.infoTextView.setText(LocaleController.formatString("FolderLinkDeletedTitle", R.string.FolderLinkDeletedTitle, new Object[]{r213}));
        this.infoTextView.setTypeface(AndroidUtilities.bold());
        this.subinfoTextView.setVisibility(0);
        this.subinfoTextView.setText(LocaleController.formatPluralString("FolderLinkDeletedSubtitle", r321, new Object[0]));
    L468:
        if (this.currentAction == 81) goto L517;
        int r214 = 0;
    L471:
        if (r214 >= r29.size()) goto L517;
        MessagesController r322 = MessagesController.getInstance(this.currentAccount);
        long r410 = r29.get(r214).longValue();
        int r73 = this.currentAction;
        if (r73 == 0) goto L477;
        if (r73 == 26) goto L477;
        boolean r74 = false;
    L478:
        r322.addDialogAction(r410, r74);
        r214 = r214 + 1;
    L477:
        r74 = true;
        goto L478
    L427:
        this.infoTextView.setTypeface(r55);
        LinkSpanDrawable.LinksTextView r323 = this.infoTextView;
        int r411 = R.string.FolderLinkDeleted;
        if (r213 != null) goto L430;
        r213 = r6;
    L430:
        r323.setText(AndroidUtilities.replaceTags(LocaleController.formatString("FolderLinkDeleted", r411, new Object[]{r213.replace('*', 10033)})));
        goto L468
    L432:
        if (r212 == 81) goto L466;
        if (r212 == 0) goto L466;
        if (r212 == 26) goto L466;
        if (r212 != 27) goto L441;
        this.infoTextView.setText(LocaleController.getString(R.string.ChatsDeletedUndo));
        goto L468
    L441:
        if (r212 != 95) goto L455;
        if (DialogObject.isChatDialog(r13) == false) goto L453;
        TLRPC.Chat r215 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r13));
        if (ChatObject.isMonoForum(r215) == false) goto L448;
        this.infoTextView.setText(LocaleController.getString(R.string.MonoforumDeletedUndo));
        goto L468
    L448:
        if (ChatObject.isChannel(r215) == true) goto L450;
    L452:
        this.infoTextView.setText(LocaleController.getString(R.string.GroupLeftUndo));
        goto L468
    L450:
        if (r215.megagroup == true) goto L452;
        this.infoTextView.setText(LocaleController.getString(R.string.ChannelLeftUndo));
        goto L468
    L453:
        this.infoTextView.setText(LocaleController.getString(R.string.ChatDeletedUndo));
        goto L468
    L455:
        if (DialogObject.isChatDialog(r13) == false) goto L465;
        TLRPC.Chat r216 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r13));
        if (ChatObject.isMonoForum(r216) == false) goto L460;
        this.infoTextView.setText(LocaleController.getString(R.string.MonoforumDeletedUndo));
        goto L468
    L460:
        if (ChatObject.isChannel(r216) == true) goto L462;
    L464:
        this.infoTextView.setText(LocaleController.getString(R.string.GroupDeletedUndo));
        goto L468
    L462:
        if (r216.megagroup == true) goto L464;
        this.infoTextView.setText(LocaleController.getString(R.string.ChannelDeletedUndo));
        goto L468
    L465:
        this.infoTextView.setText(LocaleController.getString(R.string.ChatDeletedUndo));
    L466:
        this.infoTextView.setText(LocaleController.getString(R.string.HistoryClearedUndo));
    L492:
        this.timeLeft = 4000;
        this.infoTextView.setTextSize(1, 14.0f);
        this.infoTextView.setGravity(16);
        this.infoTextView.setMinHeight(AndroidUtilities.dp(30.0f));
        String r028 = (String) r31;
        if ("🎲".equals(r028) == false) goto L496;
        this.infoTextView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.DiceInfo2)));
        this.leftImageView.setImageResource(R.drawable.dice);
    L503:
        this.undoTextView.setText(LocaleController.getString(R.string.SendDice));
        if (this.currentAction != 16) goto L506;
        int r029 = ((int) Math.ceil(this.undoTextView.getPaint().measureText(this.undoTextView.getText().toString()))) + AndroidUtilities.dp(26.0f);
        this.undoTextView.setVisibility(0);
        this.undoTextView.setTextColor(getThemedColor(Theme.key_undo_cancelColor));
        this.undoImageView.setVisibility(8);
        this.undoButton.setVisibility(0);
    L507:
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        r9.rightMargin = r029;
        r9.topMargin = AndroidUtilities.dp(6.0f);
        r9.bottomMargin = AndroidUtilities.dp(7.0f);
        r9.height = -1;
        this.subinfoTextView.setVisibility(8);
        this.leftImageView.setVisibility(0);
        goto L517
    L506:
        r029 = AndroidUtilities.dp(8.0f);
        this.undoTextView.setVisibility(8);
        this.undoButton.setVisibility(8);
        goto L507
    L496:
        if ("🎯".equals(r028) == false) goto L498;
        this.infoTextView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.DartInfo)));
    L502:
        this.leftImageView.setImageDrawable(Emoji.getEmojiDrawable(r028));
        this.leftImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        r9.topMargin = AndroidUtilities.dp(14.0f);
        r9.bottomMargin = AndroidUtilities.dp(14.0f);
        r83.leftMargin = AndroidUtilities.dp(14.0f);
        r83.width = AndroidUtilities.dp(26.0f);
        r83.height = AndroidUtilities.dp(26.0f);
        goto L503
    L498:
        String r217 = LocaleController.getServerString("DiceEmojiInfo_" + r028);
        boolean r324 = TextUtils.isEmpty(r217);
        LinkSpanDrawable.LinksTextView r412 = this.infoTextView;
        if (r324 == true) goto L501;
        r412.setText(Emoji.replaceEmoji(r217, r412.getPaint().getFontMetricsInt(), false));
        goto L502
    L501:
        r412.setText(Emoji.replaceEmoji(LocaleController.formatString("DiceEmojiInfo", R.string.DiceEmojiInfo, new Object[]{r028}), this.infoTextView.getPaint().getFontMetricsInt(), false));
    L508:
        int r030 = ((Integer) r31).intValue();
        TLRPC.User r325 = (TLRPC.User) r32;
        this.undoImageView.setVisibility(8);
        this.leftImageView.setVisibility(0);
        LinkSpanDrawable.LinksTextView r218 = this.infoTextView;
        if (r030 == 0) goto L515;
        r218.setTypeface(AndroidUtilities.bold());
        this.infoTextView.setTextSize(1, 14.0f);
        this.leftImageView.clearLayerColors();
        RLottieImageView r219 = this.leftImageView;
        int r57 = Theme.key_undo_infoColor;
        r219.setLayerColor("BODY", getThemedColor(r57));
        this.leftImageView.setLayerColor("Wibe Big", getThemedColor(r57));
        this.leftImageView.setLayerColor("Wibe Big 3", getThemedColor(r57));
        this.leftImageView.setLayerColor("Wibe Small", getThemedColor(r57));
        this.infoTextView.setText(LocaleController.getString(R.string.ProximityAlertSet));
        this.leftImageView.setAnimation(R.raw.ic_unmute, 28, 28);
        this.subinfoTextView.setVisibility(0);
        this.subinfoTextView.setSingleLine(false);
        this.subinfoTextView.setMaxLines(3);
        TextView r220 = this.subinfoTextView;
        if (r325 == null) goto L513;
        r220.setText(LocaleController.formatString("ProximityAlertSetInfoUser", R.string.ProximityAlertSetInfoUser, new Object[]{UserObject.getFirstName(r325), LocaleController.formatDistance(r030, 2)}));
    L514:
        this.undoButton.setVisibility(8);
        r9.topMargin = AndroidUtilities.dp(6.0f);
    L516:
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        this.leftImageView.setProgress(0.0f);
        this.leftImageView.playAnimation();
        goto L517
    L513:
        r220.setText(LocaleController.formatString("ProximityAlertSetInfoGroup2", R.string.ProximityAlertSetInfoGroup2, new Object[]{LocaleController.formatDistance(r030, 2)}));
        goto L514
    L515:
        r218.setTypeface(Typeface.DEFAULT);
        this.infoTextView.setTextSize(1, 15.0f);
        this.leftImageView.clearLayerColors();
        RLottieImageView r031 = this.leftImageView;
        int r221 = Theme.key_undo_infoColor;
        r031.setLayerColor("Body Main", getThemedColor(r221));
        this.leftImageView.setLayerColor("Body Top", getThemedColor(r221));
        this.leftImageView.setLayerColor("Line", getThemedColor(r221));
        this.leftImageView.setLayerColor("Curve Big", getThemedColor(r221));
        this.leftImageView.setLayerColor("Curve Small", getThemedColor(r221));
        r9.topMargin = AndroidUtilities.dp(14.0f);
        this.infoTextView.setText(LocaleController.getString(R.string.ProximityAlertCancelled));
        this.leftImageView.setAnimation(R.raw.ic_mute, 28, 28);
        this.subinfoTextView.setVisibility(8);
        this.undoTextView.setTextColor(getThemedColor(Theme.key_undo_cancelColor));
        this.undoButton.setVisibility(0);
        goto L516
    L28:
        if (r30 != ACTION_RINGTONE_ADDED) goto L33;
        this.subinfoTextView.setSingleLine(false);
        CharSequence r032 = LocaleController.getString(R.string.SoundAdded);
        CharSequence r222 = AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.SoundAddedSubtitle), r33);
        this.currentActionRunnable = null;
        int r326 = R.raw.sound_download;
        this.timeLeft = 4000;
        r15 = r222;
    L30:
        int r223 = r326;
    L31:
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
    L300:
        this.infoTextView.setText(r032);
        RLottieImageView r033 = this.leftImageView;
        if (r223 == 0) goto L303;
        r033.setAnimation(r223, r10, r10);
        RLottieDrawable r034 = this.leftImageView.getAnimatedDrawable();
        r034.setPlayInDirectionOfCustomEndFrame(false);
        r034.setCustomEndFrame(r034.getFramesCount());
        this.leftImageView.setVisibility(0);
        this.leftImageView.setProgress(0.0f);
        this.leftImageView.playAnimation();
    L304:
        if (r15 == null) goto L306;
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        r9.topMargin = AndroidUtilities.dp(6.0f);
        r9.rightMargin = AndroidUtilities.dp(8.0f);
        ((FrameLayout.LayoutParams) this.subinfoTextView.getLayoutParams()).rightMargin = AndroidUtilities.dp(8.0f);
        this.subinfoTextView.setText(r15);
        this.subinfoTextView.setVisibility(0);
        this.infoTextView.setTextSize(1, 14.0f);
        this.infoTextView.setTypeface(AndroidUtilities.bold());
        int r224 = 8;
    L307:
        this.undoButton.setVisibility(r224);
        goto L517
    L306:
        r9.leftMargin = AndroidUtilities.dp(58.0f);
        r9.topMargin = AndroidUtilities.dp(13.0f);
        r9.rightMargin = AndroidUtilities.dp(8.0f);
        r224 = 8;
        this.subinfoTextView.setVisibility(8);
        this.infoTextView.setTextSize(1, 15.0f);
        this.infoTextView.setTypeface(Typeface.DEFAULT);
        goto L307
    L303:
        r033.setVisibility(8);
        goto L304
    L33:
        if (r30 != 74) goto L36;
        this.subinfoTextView.setSingleLine(false);
        r032 = LocaleController.getString(R.string.ReportChatSent);
        r15 = LocaleController.formatString("ReportSentInfo", R.string.ReportSentInfo, new Object[0]);
        r223 = R.raw.ic_admin;
        this.timeLeft = 4000;
        goto L31
    L36:
        if (r30 != 34) goto L44;
        TLRPC.User r035 = (TLRPC.User) r31;
        if (ChatObject.isChannelOrGiga((TLRPC.Chat) r32) == false) goto L40;
        SpannableStringBuilder r225 = AndroidUtilities.replaceTags(LocaleController.formatString("VoipChannelInvitedUser", R.string.VoipChannelInvitedUser, new Object[]{UserObject.getFirstName(r035)}));
    L41:
        AvatarDrawable r327 = new AvatarDrawable();
        r327.setTextSize(AndroidUtilities.dp(12.0f));
        r327.setInfo(this.currentAccount, r035);
        this.avatarImageView.setForUserOrChat(r035, r327);
        this.avatarImageView.setVisibility(0);
        this.timeLeft = 3000;
        r032 = r225;
    L42:
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
        r223 = 0;
        goto L300
    L40:
        r225 = AndroidUtilities.replaceTags(LocaleController.formatString("VoipGroupInvitedUser", R.string.VoipGroupInvitedUser, new Object[]{UserObject.getFirstName(r035)}));
        goto L41
    L44:
        if (r30 != 44) goto L60;
        TLRPC.Chat r036 = (TLRPC.Chat) r32;
        if ((r31 instanceof TLRPC.User) == false) goto L52;
        TLRPC.User r226 = (TLRPC.User) r31;
        if (ChatObject.isChannelOrGiga(r036) == false) goto L50;
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.VoipChannelUserJoined, new Object[]{UserObject.getFirstName(r226)}));
    L58:
        AvatarDrawable r227 = new AvatarDrawable();
        r227.setTextSize(AndroidUtilities.dp(12.0f));
        TLObject r328 = (TLObject) r31;
        r227.setInfo(this.currentAccount, r328);
        this.avatarImageView.setForUserOrChat(r328, r227);
        this.avatarImageView.setVisibility(0);
        this.timeLeft = 3000;
        goto L42
    L50:
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.VoipChatUserJoined, new Object[]{UserObject.getFirstName(r226)}));
        goto L58
    L52:
        if ((r31 instanceof TLRPC.Chat) == false) goto L57;
        boolean r037 = ChatObject.isChannelOrGiga(r036);
        String r228 = ((TLRPC.Chat) r31).title;
        if (r037 == false) goto L56;
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.VoipChannelChatJoined, new Object[]{r228}));
        goto L58
    L56:
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.VoipChatChatJoined, new Object[]{r228}));
        goto L58
    L57:
        r032 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L58
    L60:
        if (r30 != 37) goto L71;
        AvatarDrawable r038 = new AvatarDrawable();
        r038.setTextSize(AndroidUtilities.dp(12.0f));
        if ((r31 instanceof TLRPC.User) == false) goto L64;
        TLRPC.User r229 = (TLRPC.User) r31;
        r038.setInfo(this.currentAccount, r229);
        this.avatarImageView.setForUserOrChat(r229, r038);
        String r039 = ContactsController.formatName(r229.first_name, r229.last_name);
    L66:
        if (ChatObject.isChannelOrGiga((TLRPC.Chat) r32) == false) goto L68;
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString("VoipChannelUserChanged", R.string.VoipChannelUserChanged, new Object[]{r039}));
    L69:
        this.avatarImageView.setVisibility(0);
        this.timeLeft = 3000;
        goto L42
    L68:
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString("VoipGroupUserChanged", R.string.VoipGroupUserChanged, new Object[]{r039}));
        goto L69
    L64:
        TLRPC.Chat r230 = (TLRPC.Chat) r31;
        r038.setInfo(this.currentAccount, r230);
        this.avatarImageView.setForUserOrChat(r230, r038);
        r039 = r230.title;
        goto L66
    L71:
        if (r30 != 33) goto L74;
        r032 = LocaleController.getString(R.string.VoipGroupCopyInviteLinkCopied);
        r223 = R.raw.voip_invite;
        this.timeLeft = 3000;
        goto L31
    L74:
        if (r30 != 77) goto L81;
        r032 = (CharSequence) r31;
        r223 = R.raw.payment_success;
        this.timeLeft = 5000;
        if (this.parentFragment == null) goto L31;
        if ((r32 instanceof TLRPC.Message) == false) goto L31;
        setOnTouchListener(null);
        this.infoTextView.setMovementMethod(null);
        setOnClickListener(new UndoView$$ExternalSyntheticLambda4(this, (TLRPC.Message) r32));
        goto L31
    L81:
        if (r30 != 30) goto L88;
        if ((r31 instanceof TLRPC.User) == false) goto L85;
        String r040 = UserObject.getFirstName((TLRPC.User) r31);
    L86:
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString("VoipGroupUserCantNowSpeak", R.string.VoipGroupUserCantNowSpeak, new Object[]{r040}));
        r223 = R.raw.voip_muted;
        this.timeLeft = 3000;
        goto L31
    L85:
        r040 = ((TLRPC.Chat) r31).title;
        goto L86
    L88:
        if (r30 != 35) goto L98;
        if ((r31 instanceof TLRPC.User) == false) goto L93;
        String r041 = UserObject.getFirstName((TLRPC.User) r31);
    L96:
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString("VoipGroupUserCantNowSpeakForYou", R.string.VoipGroupUserCantNowSpeakForYou, new Object[]{r041}));
        r223 = R.raw.voip_muted;
        this.timeLeft = 3000;
        goto L31
    L93:
        if ((r31 instanceof TLRPC.Chat) == false) goto L95;
        r041 = ((TLRPC.Chat) r31).title;
        goto L96
    L95:
        r041 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L96
    L98:
        if (r30 != 31) goto L105;
        if ((r31 instanceof TLRPC.User) == false) goto L102;
        String r042 = UserObject.getFirstName((TLRPC.User) r31);
    L103:
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString("VoipGroupUserCanNowSpeak", R.string.VoipGroupUserCanNowSpeak, new Object[]{r042}));
        r223 = R.raw.voip_unmuted;
        this.timeLeft = 3000;
        goto L31
    L102:
        r042 = ((TLRPC.Chat) r31).title;
        goto L103
    L105:
        if (r30 != 38) goto L112;
        if ((r31 instanceof TLRPC.Chat) == false) goto L109;
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString("VoipGroupYouCanNowSpeakIn", R.string.VoipGroupYouCanNowSpeakIn, new Object[]{((TLRPC.Chat) r31).title}));
    L110:
        r223 = R.raw.voip_allow_talk;
        this.timeLeft = 3000;
        goto L31
    L109:
        r032 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.VoipGroupYouCanNowSpeak));
        goto L110
    L112:
        if (r30 != 42) goto L119;
        if (ChatObject.isChannelOrGiga((TLRPC.Chat) r31) == false) goto L116;
        r032 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.VoipChannelSoundMuted));
    L117:
        r223 = R.raw.ic_mute;
        this.timeLeft = 3000;
        goto L31
    L116:
        r032 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.VoipGroupSoundMuted));
        goto L117
    L119:
        if (r30 == 43) goto L121;
        int r58 = this.currentAction;
        if (r58 != 39) goto L128;
    L129:
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r58 != 39) goto L298;
        int r043 = R.string.VoipGroupAudioRecordStarted;
    L299:
        r032 = AndroidUtilities.replaceTags(LocaleController.getString(r043));
        r223 = R.raw.voip_record_start;
        this.timeLeft = 3000;
        goto L300
    L298:
        r043 = R.string.VoipGroupVideoRecordStarted;
        goto L299
    L128:
        if (r58 == 100) goto L129;
        if (r58 == 40) goto L279;
        if (r58 == 101) goto L279;
        if (r30 != 36) goto L142;
        if ((r31 instanceof TLRPC.User) == false) goto L139;
        String r044 = UserObject.getFirstName((TLRPC.User) r31);
    L140:
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString("VoipGroupUserCanNowSpeakForYou", R.string.VoipGroupUserCanNowSpeakForYou, new Object[]{r044}));
        r223 = R.raw.voip_unmuted;
        this.timeLeft = 3000;
        goto L31
    L139:
        r044 = ((TLRPC.Chat) r31).title;
        goto L140
    L142:
        if (r30 == 32) goto L270;
        if (r30 == 102) goto L270;
        if (r30 != 9) goto L149;
    L264:
        TLRPC.User r045 = (TLRPC.User) r31;
        if (r30 != 9) goto L267;
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString("EditAdminTransferChannelToast", R.string.EditAdminTransferChannelToast, new Object[]{UserObject.getFirstName(r045)}));
    L268:
        r223 = R.raw.contact_check;
        goto L31
    L267:
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString("EditAdminTransferGroupToast", R.string.EditAdminTransferGroupToast, new Object[]{UserObject.getFirstName(r045)}));
        goto L268
    L149:
        if (r30 == 10) goto L264;
        if (r30 != 8) goto L155;
        r032 = LocaleController.formatString("NowInContacts", R.string.NowInContacts, new Object[]{UserObject.getFirstName((TLRPC.User) r31)});
        r223 = R.raw.contact_check;
        goto L31
    L155:
        if (r30 != 87) goto L158;
        r032 = LocaleController.formatString(R.string.ProxyAddedSuccess, new Object[0]);
        r223 = R.raw.contact_check;
        goto L31
    L158:
        if (r30 != 22) goto L176;
        if (DialogObject.isUserDialog(r13) == false) goto L164;
        if (r31 != null) goto L163;
        r032 = LocaleController.getString(R.string.MainProfilePhotoSetHint);
    L174:
        r223 = R.raw.contact_check;
        goto L31
    L163:
        r032 = LocaleController.getString(R.string.MainProfileVideoSetHint);
        goto L174
    L164:
        TLRPC.Chat r046 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r13));
        if (ChatObject.isChannel(r046) == true) goto L167;
    L171:
        if (r31 != null) goto L173;
        r032 = LocaleController.getString(R.string.MainGroupProfilePhotoSetHint);
        goto L174
    L173:
        r032 = LocaleController.getString(R.string.MainGroupProfileVideoSetHint);
        goto L174
    L167:
        if (r046.megagroup == true) goto L171;
        if (r31 != null) goto L170;
        r032 = LocaleController.getString(R.string.MainChannelProfilePhotoSetHint);
        goto L174
    L170:
        r032 = LocaleController.getString(R.string.MainChannelProfileVideoSetHint);
        goto L174
    L176:
        if (r30 != 23) goto L179;
        r032 = LocaleController.getString(R.string.ChatWasMovedToMainList);
        r223 = R.raw.contact_check;
        goto L31
    L179:
        if (r30 != 6) goto L182;
        r032 = LocaleController.getString(R.string.ArchiveHidden);
        r15 = LocaleController.getString(R.string.ArchiveHiddenInfo);
        r223 = R.raw.chats_swipearchive;
        r10 = 48;
        goto L31
    L182:
        if (r58 != 13) goto L186;
        String r231 = LocaleController.getString(R.string.QuizWellDone);
        r15 = LocaleController.getString(R.string.QuizWellDoneInfo);
        r326 = R.raw.wallet_congrats;
    L184:
        r10 = 44;
        r032 = r231;
        goto L30
    L186:
        if (r58 != 14) goto L189;
        r231 = LocaleController.getString(R.string.QuizWrongAnswer);
        r15 = LocaleController.getString(R.string.QuizWrongAnswerInfo);
        r326 = R.raw.wallet_science;
        goto L184
    L189:
        if (r30 != 7) goto L195;
        r032 = LocaleController.getString(R.string.ArchivePinned);
        if (MessagesController.getInstance(this.currentAccount).dialogFilters.isEmpty() == false) goto L193;
        r15 = LocaleController.getString(R.string.ArchivePinnedInfo);
    L193:
        r223 = R.raw.chats_infotip;
        goto L31
    L195:
        if (r30 != 20) goto L197;
    L233:
        MessagesController.DialogFilter r413 = (MessagesController.DialogFilter) r32;
        Spannable r59 = MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(r413.name, this.infoTextView.getPaint().getFontMetricsInt(), false), r413.entities, this.infoTextView.getPaint().getFontMetricsInt());
        if (r413.title_noanimate == false) goto L236;
        int r414 = 26;
    L237:
        this.infoTextViewEmojiCacheType = r414;
        if (r13 != 0) goto L240;
        if (r30 != 20) goto L259;
        CharSequence r329 = AndroidUtilities.replaceTags(LocaleController.formatSpannable(R.string.FilterChatsAddedToExisting, new Object[]{LocaleController.formatPluralString("ChatsSelected", ((Integer) r31).intValue(), new Object[0]), r59}));
    L260:
        if (r30 != 20) goto L262;
        int r047 = R.raw.folder_in;
    L263:
        r223 = r047;
        r032 = r329;
        goto L31
    L262:
        r047 = R.raw.folder_out;
        goto L263
    L259:
        r329 = AndroidUtilities.replaceTags(LocaleController.formatSpannable(R.string.FilterChatsRemovedFrom, new Object[]{LocaleController.formatPluralString("ChatsSelected", ((Integer) r31).intValue(), new Object[0]), r59}));
        goto L260
    L240:
        if (DialogObject.isEncryptedDialog(r13) == false) goto L242;
        r13 = MessagesController.getInstance(this.currentAccount).getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r13))).user_id;
    L242:
        boolean r330 = DialogObject.isUserDialog(r13);
        int r415 = this.currentAccount;
        if (r330 == false) goto L253;
        TLRPC.User r331 = MessagesController.getInstance(r415).getUser(Long.valueOf(r13));
        String r416 = UserObject.getFirstName(r331);
        if (UserObject.isUserSelf(r331) == false) goto L248;
        r416 = LocaleController.getString(R.string.SavedMessages);
    L250:
        if (r30 != 20) goto L252;
        r329 = AndroidUtilities.replaceTags(LocaleController.formatSpannable(R.string.FilterUserAddedToExisting, new Object[]{r416, r59}));
        goto L260
    L252:
        r329 = AndroidUtilities.replaceTags(LocaleController.formatSpannable(R.string.FilterUserRemovedFrom, new Object[]{r416, r59}));
        goto L260
    L248:
        if (UserObject.isReplyUser(r331) == false) goto L250;
        r416 = LocaleController.getString(R.string.RepliesTitle);
        goto L250
    L253:
        TLRPC.Chat r332 = MessagesController.getInstance(r415).getChat(Long.valueOf(-r13));
        if (r30 != 20) goto L256;
        r329 = AndroidUtilities.replaceTags(LocaleController.formatSpannable(R.string.FilterChatAddedToExisting, new Object[]{r332.title, r59}));
        goto L260
    L256:
        r329 = AndroidUtilities.replaceTags(LocaleController.formatSpannable(R.string.FilterChatRemovedFrom, new Object[]{r332.title, r59}));
        goto L260
    L236:
        r414 = 0;
        goto L237
    L197:
        if (r30 == 21) goto L233;
        if (r30 != 19) goto L203;
        r032 = this.infoText;
        r223 = R.raw.ic_delete;
        goto L31
    L203:
        if (r30 != 82) goto L210;
        if (((MediaController.PhotoEntry) r31).isVideo == false) goto L208;
        int r048 = R.string.AttachMediaVideoDeselected;
    L207:
        r032 = LocaleController.getString(r048);
        goto L42
    L208:
        r048 = R.string.AttachMediaPhotoDeselected;
        goto L207
    L210:
        if (r30 != 78) goto L212;
    L222:
        int r049 = ((Integer) r31).intValue();
        if (r30 != 78) goto L225;
        r032 = LocaleController.formatPluralString("PinnedDialogsCount", r049, new Object[0]);
    L227:
        if (this.currentAction != 78) goto L229;
        r223 = R.raw.ic_pin;
    L231:
        if ((r32 instanceof Integer) == false) goto L31;
        this.timeLeft = ((Integer) r32).intValue();
        goto L31
    L229:
        r223 = R.raw.ic_unpin;
        goto L231
    L225:
        r032 = LocaleController.formatPluralString("UnpinnedDialogsCount", r049, new Object[0]);
        goto L227
    L212:
        if (r30 == 79) goto L222;
        if (r30 != 3) goto L217;
        r032 = LocaleController.getString(R.string.ChatArchived);
    L219:
        if (MessagesController.getInstance(this.currentAccount).dialogFilters.isEmpty() == false) goto L221;
        r15 = LocaleController.getString(R.string.ChatArchivedInfo);
    L221:
        r223 = R.raw.chats_infotip;
        goto L31
    L217:
        r032 = LocaleController.getString(R.string.ChatsArchived);
    L270:
        if ((r31 instanceof TLRPC.User) == false) goto L272;
        String r050 = UserObject.getFirstName((TLRPC.User) r31);
    L274:
        if (r30 != 102) goto L276;
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.VoipConferenceKicked, new Object[]{r050}));
    L277:
        r223 = R.raw.ic_ban;
        this.timeLeft = 3000;
        goto L31
    L276:
        r032 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.VoipGroupRemovedFromGroup, new Object[]{r050}));
        goto L277
    L272:
        r050 = ((TLRPC.Chat) r31).title;
    L279:
        if (r58 != 40) goto L282;
        int r051 = R.string.VoipGroupAudioRecordSaved;
    L281:
        String r052 = LocaleController.getString(r051);
        r223 = R.raw.voip_record_saved;
        this.timeLeft = 4000;
        this.infoTextView.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        SpannableStringBuilder r333 = new SpannableStringBuilder(r052);
        int r417 = r052.indexOf("**");
        int r053 = r052.lastIndexOf("**");
        if (r417 < 0) goto L292;
        if (r053 < 0) goto L292;
        if (r417 == r053) goto L292;
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
        r333.replace(r053, r053 + 2, r6);
        r333.replace(r417, r417 + 2, r6);
        r333.setSpan(new URLSpanNoUnderline("tg://openmessage?user_id=" + UserConfig.getInstance(this.currentAccount).getClientUserId()), r417, r053 - 2, 33);     // Catch: Exception -> L290
    L293:
        r032 = r333;
    L290:
        e = move-exception;
        FileLog.e(e);
    L292:
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L293
    L282:
        r051 = R.string.VoipGroupVideoRecordSaved;
        goto L281
    L121:
        if (ChatObject.isChannelOrGiga((TLRPC.Chat) r31) == false) goto L123;
        r032 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.VoipChannelSoundUnmuted));
    L124:
        r223 = R.raw.ic_unmute;
        this.timeLeft = 3000;
        goto L31
    L123:
        r032 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.VoipGroupSoundUnmuted));
    L22:
        if (r30 == ACTION_RINGTONE_ADDED) goto L23;
        setOnClickListener(null);
        setOnTouchListener(new UndoView$$ExternalSyntheticLambda3());
        goto L25
    }
}
