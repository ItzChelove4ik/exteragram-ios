package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.material.navigation.NavigationBarView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.DotDividerSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MessageSeenCheckDrawable;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.StatusBadgeComponent;
import org.telegram.ui.Stories.StoriesUtilities;

/* loaded from: classes6.dex */
public class ReactedUserHolderView extends FrameLayout {
    public static int STYLE_DEFAULT = 0;
    public static int STYLE_STORY = 1;
    public static final MessageSeenCheckDrawable forwardDrawable = null;
    public static final MessageSeenCheckDrawable reactDrawable = null;
    public static final MessageSeenCheckDrawable repostDrawable = null;
    public static final MessageSeenCheckDrawable seenDrawable = null;
    private ValueAnimator alphaAnimator;
    private float alphaInternal;
    AvatarDrawable avatarDrawable;
    public BackupImageView avatarView;
    int currentAccount;
    public long dialogId;
    public boolean drawDivider;
    View overlaySelectorView;
    public StoriesUtilities.AvatarStoryParams params;
    BackupImageView reactView;
    Theme.ResourcesProvider resourcesProvider;
    StatusBadgeComponent statusBadgeComponent;
    public int storyId;
    public BackupImageView storyPreviewView;
    int style;
    SimpleTextView subtitleView;
    SimpleTextView titleView;

    /* renamed from: org.telegram.ui.Cells.ReactedUserHolderView$1, reason: invalid class name */
    public class AnonymousClass1 extends StoriesUtilities.AvatarStoryParams {
        final /* synthetic */ ReactedUserHolderView this$0;

        public AnonymousClass1(ReactedUserHolderView r1, boolean r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Stories.StoriesUtilities.AvatarStoryParams
        public void openStory(long r1, Runnable r3) {
            this.this$0.openStory(r1, r3);
        }
    }

    /* renamed from: org.telegram.ui.Cells.ReactedUserHolderView$2, reason: invalid class name */
    public class AnonymousClass2 extends BackupImageView {
        final /* synthetic */ ReactedUserHolderView this$0;
        final /* synthetic */ int val$style;

        public AnonymousClass2(ReactedUserHolderView r1, Context r2, int r3) {
            this.this$0 = r1;
            this.val$style = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r5) {
            if (this.val$style != ReactedUserHolderView.STYLE_STORY) goto L6;
            this.this$0.params.originalAvatarRect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            StoriesUtilities.drawAvatarWithStory(this.this$0.dialogId, r5, getImageReceiver(), this.this$0.params);
            return;
        L6:
            super.onDraw(r5);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            return this.this$0.params.checkOnTouchEvent(r2, this);
        }
    }

    /* renamed from: org.telegram.ui.Cells.ReactedUserHolderView$3, reason: invalid class name */
    public class AnonymousClass3 extends SimpleTextView {
        final /* synthetic */ ReactedUserHolderView this$0;

        public AnonymousClass3(ReactedUserHolderView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.ActionBar.SimpleTextView
        public boolean setText(CharSequence r3) {
            return super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false));
        }
    }

    /* renamed from: org.telegram.ui.Cells.ReactedUserHolderView$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ ReactedUserHolderView this$0;
        final /* synthetic */ float val$alpha;

        public AnonymousClass4(ReactedUserHolderView r1, float r2) {
            this.this$0 = r1;
            this.val$alpha = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ReactedUserHolderView.m7003$$Nest$fputalphaInternal(this.this$0, this.val$alpha);
            this.this$0.invalidate();
        }
    }

    public static /* synthetic */ void $r8$lambda$_mkOedITnrx1QflQZ6VUrbACVh4(ReactedUserHolderView r0, ValueAnimator r1) {
        r0.lambda$animateAlpha$0(r1);
    }

    /* renamed from: -$$Nest$fputalphaInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7003$$Nest$fputalphaInternal(ReactedUserHolderView r0, float r1) {
        r0.alphaInternal = r1;
    }

    static {
        int r1 = R.drawable.msg_mini_checks;
        int r4 = Theme.key_windowBackgroundWhiteGrayText;
        seenDrawable = new MessageSeenCheckDrawable(r1, r4);
        reactDrawable = new MessageSeenCheckDrawable(R.drawable.msg_reactions, r4, 16, 16, 5.66f);
        int r12 = R.drawable.mini_repost_story;
        int r2 = Theme.key_stories_circle1;
        repostDrawable = new MessageSeenCheckDrawable(r12, r2);
        forwardDrawable = new MessageSeenCheckDrawable(R.drawable.mini_forward_story, r2);
    }

    public ReactedUserHolderView(int r25, int r26, Context r27, Theme.ResourcesProvider r28, boolean r29, boolean r30) {
        super(r27);
        this.avatarDrawable = new AvatarDrawable();
        this.alphaInternal = 1.0f;
        this.style = r25;
        this.currentAccount = r26;
        this.resourcesProvider = r28;
        this.params = new AnonymousClass1(this, false, r28);
        setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(50.0f)));
        if (r25 != STYLE_STORY) goto L5;
        int r4 = 48;
    L6:
        AnonymousClass2 r6 = new AnonymousClass2(this, r27, r25);
        this.avatarView = r6;
        float r8 = r4;
        r6.setRoundRadius(ExteraConfig.getAvatarCorners(r8));
        addView(this.avatarView, LayoutHelper.createFrameRelatively(r8, r8, NavigationBarView.ITEM_GRAVITY_START_CENTER, 10.0f, 0.0f, 0.0f, 0.0f));
        if (r25 != STYLE_STORY) goto L9;
        setClipChildren(false);
    L9:
        AnonymousClass3 r42 = new AnonymousClass3(this, r27);
        this.titleView = r42;
        NotificationCenter.listenEmojiLoading(r42);
        this.titleView.setTextSize(16);
        this.titleView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r28));
        this.titleView.setEllipsizeByGradient(true);
        this.titleView.setImportantForAccessibility(2);
        this.titleView.setRightPadding(AndroidUtilities.dp(30.0f));
        SimpleTextView r43 = this.titleView;
        float r11 = 0.0f;
        if (LocaleController.isRTL == false) goto L12;
        float r10 = AndroidUtilities.dp(30.0f);
    L13:
        r43.setTranslationX(r10);
        this.titleView.setRightDrawableOutside(true);
        int r44 = STYLE_STORY;
        if (r25 != r44) goto L17;
        float r102 = 7.66f;
    L16:
        float r16 = r102;
        if (r25 != r44) goto L21;
        float r45 = 73.0f;
    L20:
        float r15 = r45;
        addView(this.titleView, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, 55, r15, r16, 12.0f, 0.0f));
        this.statusBadgeComponent = new StatusBadgeComponent(this);
        this.titleView.setDrawablePadding(AndroidUtilities.dp(3.0f));
        this.titleView.setRightDrawable(this.statusBadgeComponent.getDrawable());
        SimpleTextView r46 = new SimpleTextView(r27);
        this.subtitleView = r46;
        r46.setTextSize(13);
        this.subtitleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r28));
        this.subtitleView.setEllipsizeByGradient(true);
        this.subtitleView.setImportantForAccessibility(2);
        SimpleTextView r3 = this.subtitleView;
        if (LocaleController.isRTL == false) goto L25;
        r11 = AndroidUtilities.dp(30.0f);
    L25:
        r3.setTranslationX(r11);
        if (r25 != STYLE_STORY) goto L29;
        float r1 = 24.0f;
    L30:
        addView(this.subtitleView, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, 55, r15, r1, 20.0f, 0.0f));
        if (r30 == false) goto L33;
        BackupImageView r12 = new BackupImageView(r27);
        this.reactView = r12;
        addView(r12, LayoutHelper.createFrameRelatively(24.0f, 24.0f, 8388629, 0.0f, 0.0f, 12.0f, 0.0f));
        BackupImageView r13 = new BackupImageView(r27);
        this.storyPreviewView = r13;
        addView(r13, LayoutHelper.createFrameRelatively(22.0f, 35.0f, 8388629, 0.0f, 0.0f, 12.0f, 0.0f));
    L33:
        if (r29 == false) goto L36;
        View r14 = new View(r27);
        this.overlaySelectorView = r14;
        r14.setBackground(Theme.getSelectorDrawable(false));
        addView(this.overlaySelectorView, LayoutHelper.createFrame(-1, -1.0f));
        return;
    L36:
        return;
    L29:
        r1 = 19.0f;
        goto L30
    L21:
        r45 = 55.0f;
        goto L20
    L17:
        r102 = 5.33f;
        goto L16
    L12:
        r10 = 0.0f;
        goto L13
    L5:
        r4 = 34;
        goto L6
    }

    private /* synthetic */ void lambda$animateAlpha$0(ValueAnimator r1) {
        this.alphaInternal = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    public void animateAlpha(float r3, boolean r4) {
        ValueAnimator r0 = this.alphaAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.alphaAnimator = null;
    L5:
        if (r4 == false) goto L8;
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{this.alphaInternal, r3});
        this.alphaAnimator = r42;
        r42.addUpdateListener(new ReactedUserHolderView$$ExternalSyntheticLambda0(this));
        this.alphaAnimator.addListener(new AnonymousClass4(this, r3));
        this.alphaAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.alphaAnimator.setDuration(420);
        this.alphaAnimator.start();
        return;
    L8:
        this.alphaInternal = r3;
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r17) {
        if (this.alphaInternal >= 1.0f) goto L5;
        r17.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (this.alphaInternal * 255.0f), 31);
        boolean r1 = true;
    L6:
        super.dispatchDraw(r17);
        if (this.drawDivider == true) goto L9;
    L16:
        if (r1 == false) goto L19;
        r17.restore();
        return;
    L19:
        return;
    L9:
        if (this.style != STYLE_STORY) goto L11;
        float r3 = 73.0f;
    L12:
        float r11 = AndroidUtilities.dp(r3);
        if (LocaleController.isRTL == false) goto L15;
        r17.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth() - r11, getMeasuredHeight() - 1, Theme.getThemePaint("paintDivider", this.resourcesProvider));
        goto L16
    L15:
        r17.drawLine(r11, getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.getThemePaint("paintDivider", this.resourcesProvider));
        goto L16
    L11:
        r3 = 55.0f;
        goto L12
    L5:
        r1 = false;
        goto L6
    }

    public float getAlphaInternal() {
        return this.alphaInternal;
    }

    public Theme.ResourcesProvider getResourcesProvider() {
        return this.resourcesProvider;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.statusBadgeComponent.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.statusBadgeComponent.onDetachedFromWindow();
        this.params.onDetachFromWindow();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
        super.onInitializeAccessibilityNodeInfo(r1);
        r1.setEnabled(true);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        if (this.style != STYLE_DEFAULT) goto L5;
        int r32 = 50;
    L6:
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r32), TLObject.FLAG_30));
        return;
    L5:
        r32 = 58;
        goto L6
    }

    public void openStory(long r1, Runnable r3) {
    }

    public void setUserReaction(TLRPC.MessagePeerReaction r16) {
        if (r16 != null) goto L5;
        return;
    L5:
        long r1 = MessageObject.getPeerId(r16.peer_id);
        int r5 = this.currentAccount;
        TLRPC.Chat r6 = null;
        if (r1 <= 0) goto L8;
        TLRPC.User r52 = MessagesController.getInstance(r5).getUser(Long.valueOf(r1));
    L9:
        setUserReaction(r52, r6, r16.reaction, false, r16.date, null, false, r16.dateIsSeen, false);
        return;
    L8:
        TLRPC.Chat r12 = MessagesController.getInstance(r5).getChat(Long.valueOf(-r1));
        r52 = null;
        r6 = r12;
        goto L9
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setUserReaction(TLRPC.User r19, TLRPC.Chat r20, TLRPC.Reaction r21, boolean r22, long r23, TL_stories.StoryItem r25, boolean r26, boolean r27, boolean r28) {
        if (r19 != null) goto L5;
        TLRPC.User r3 = r20;
    L6:
        if (r3 != null) goto L9;
        return;
    L9:
        if (this.style != STYLE_STORY) goto L11;
        int r4 = Theme.key_windowBackgroundWhiteBlackText;
    L12:
        this.statusBadgeComponent.updateDrawable(r19, r20, Theme.getColor(r4, this.resourcesProvider), false);
        this.avatarDrawable.setInfo(this.currentAccount, r3);
        if (r19 == null) goto L15;
        this.dialogId = r19.id;
        this.titleView.setText(UserObject.getUserName(r19));
    L16:
        Drawable r42 = this.avatarDrawable;
        if (r19 == null) goto L23;
        TLRPC.UserProfilePhoto r1 = r19.photo;
        if (r1 == null) goto L28;
        Drawable r12 = r1.strippedBitmap;
        if (r12 == null) goto L28;
    L22:
        r42 = r12;
    L28:
        this.avatarView.setImage(ImageLocation.getForUserOrChat(this.currentAccount, r3, 1), "50_50", r42, r3);
        if (r22 == false) goto L34;
        BackupImageView r2 = this.reactView;
        if (r2 == null) goto L33;
        r2.setAnimatedEmojiDrawable(null);
    L33:
        Drawable r24 = ContextCompat.getDrawable(getContext(), R.drawable.media_like_active).mutate();
        r24.setColorFilter(new PorterDuffColorFilter(-53704, PorterDuff.Mode.MULTIPLY));
        this.reactView.setImageDrawable(r24);
        String r11 = LocaleController.formatString("AccDescrLike", R.string.AccDescrLike, new Object[0]);
        boolean r122 = true;
    L61:
        if (r25 == null) goto L77;
        this.storyId = r25.id;
        if (this.storyPreviewView == null) goto L75;
        TLRPC.MessageMedia r29 = r25.media;
        if (r29 == null) goto L69;
        TLRPC.Photo r43 = r29.photo;
        if (r43 == null) goto L69;
        this.storyPreviewView.setImage(ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r43.sizes, 35, false, null, true), r25.media.photo), "22_35", null, null, -1, r25);
    L73:
        this.storyPreviewView.setRoundRadius(AndroidUtilities.dp(3.33f));
    L69:
        if (r29 == null) goto L73;
        TLRPC.Document r210 = r29.document;
        if (r210 == null) goto L73;
        this.storyPreviewView.setImage(ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r210.thumbs, 35, false, null, true), r25.media.document), "22_35", null, null, -1, r25);
    L75:
        if (r23 > 0) goto L80;
        long r13 = r25.date;
    L82:
        if (r13 == 0) goto L84;
        r11 = r11 + " " + LocaleController.formatSeenDate(r13);
    L84:
        setContentDescription(r11);
        SimpleTextView r44 = this.subtitleView;
        float r6 = 0.0f;
        if (r13 == 0) goto L114;
        r44.setVisibility(0);
        if (r25 == null) goto L91;
        if (r26 == false) goto L90;
        MessageSeenCheckDrawable r32 = forwardDrawable;
    L94:
        SpannableStringBuilder r45 = new SpannableStringBuilder();
        r45.append(r32.getSpanned(getContext(), this.resourcesProvider));
        r45.append(LocaleController.formatSeenDate(r13));
        if (r26 == true) goto L100;
        if (r25 == null) goto L100;
        if (TextUtils.isEmpty(r25.caption) == true) goto L100;
        r45.append("\u2004");
        r45.append(".");
        DotDividerSpan r7 = new DotDividerSpan();
        r7.setSize(2.33333f);
        r7.setTopPadding(AndroidUtilities.dp(5.0f));
        r45.setSpan(r7, r45.length() - 1, r45.length(), 33);
        r45.append("\u2004");
        int r211 = r45.length();
        r45.append(LocaleController.getString(R.string.StoryRepostCommented));
        r45.setSpan(new RelativeSizeSpan(0.95f), r211, r45.length(), 33);
    L107:
        this.subtitleView.setText(r45);
        SimpleTextView r14 = this.subtitleView;
        if (r27 == true) goto L110;
        float r212 = AndroidUtilities.dp(-1.0f);
    L111:
        r14.setTranslationY(r212);
        this.titleView.setTranslationY(0.0f);
        if (r28 == false) goto L115;
        this.titleView.setTranslationY(AndroidUtilities.dp(9.0f));
        this.titleView.animate().translationY(0.0f);
        this.subtitleView.setAlpha(0.0f);
        this.subtitleView.animate().alpha(1.0f);
    L115:
        SimpleTextView r15 = this.titleView;
        if (r122 == false) goto L118;
        float r33 = 30.0f;
    L119:
        r15.setRightPadding(AndroidUtilities.dp(r33));
        SimpleTextView r16 = this.titleView;
        if (r122 == true) goto L122;
    L124:
        float r34 = 0.0f;
    L125:
        r16.setTranslationX(r34);
        ViewGroup.MarginLayoutParams r17 = (ViewGroup.MarginLayoutParams) this.subtitleView.getLayoutParams();
        if (r122 == true) goto L128;
    L130:
        float r35 = 12.0f;
    L131:
        r17.rightMargin = AndroidUtilities.dp(r35);
        SimpleTextView r0 = this.subtitleView;
        if (r122 == true) goto L134;
    L136:
        r0.setTranslationX(r6);
        return;
    L134:
        if (LocaleController.isRTL == false) goto L136;
        r6 = AndroidUtilities.dp(30.0f);
        goto L136
    L128:
        if (LocaleController.isRTL == true) goto L130;
        r35 = 36.0f;
        goto L131
    L122:
        if (LocaleController.isRTL == false) goto L124;
        r34 = AndroidUtilities.dp(30.0f);
        goto L125
    L118:
        r33 = 0.0f;
        goto L119
    L110:
        r212 = 0.0f;
    L100:
        if (r26 == true) goto L107;
        if (r25 == null) goto L107;
        TL_stories.StoryFwdHeader r72 = r25.fwd_from;
        if (r72 == null) goto L107;
        if (r72.modified == false) goto L107;
        r45.append("\u2004");
        r45.append(".");
        DotDividerSpan r73 = new DotDividerSpan();
        r73.setSize(2.33333f);
        r73.setTopPadding(AndroidUtilities.dp(5.0f));
        r45.setSpan(r73, r45.length() - 1, r45.length(), 33);
        r45.append("\u2004");
        int r213 = r45.length();
        r45.append("edited");
        r45.setSpan(new RelativeSizeSpan(0.95f), r213, r45.length(), 33);
        goto L107
    L90:
        r32 = repostDrawable;
        goto L94
    L91:
        if (r27 == false) goto L93;
        r32 = seenDrawable;
        goto L94
    L93:
        r32 = reactDrawable;
        goto L94
    L114:
        r44.setVisibility(8);
        this.titleView.setTranslationY(AndroidUtilities.dp(9.0f));
    L80:
        r13 = r23;
        goto L82
    L77:
        this.storyId = -1;
        BackupImageView r214 = this.storyPreviewView;
        if (r214 == null) goto L80;
        r214.setImageDrawable(null);
        goto L80
    L34:
        if (r21 == null) goto L56;
        ReactionsLayoutInBubble.VisibleReaction r215 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r21);
        if (r215.emojicon == null) goto L47;
        BackupImageView r36 = this.reactView;
        if (r36 == null) goto L40;
        r36.setAnimatedEmojiDrawable(null);
    L40:
        TLRPC.TL_availableReaction r37 = MediaDataController.getInstance(this.currentAccount).getReactionsMap().get(r215.emojicon);
        BackupImageView r46 = this.reactView;
        if (r46 == null) goto L46;
        if (r37 == null) goto L44;
        this.reactView.setImage(ImageLocation.getForDocument(r37.center_icon), "40_40_lastreactframe", "webp", DocumentObject.getSvgThumb(r37.static_icon.thumbs, Theme.key_windowBackgroundGray, 1.0f), r37);
        boolean r38 = true;
    L45:
        this.reactView.setColorFilter(null);
    L51:
        int r47 = R.string.AccDescrReactedWith;
        CharSequence r5 = this.titleView.getText();
        Object r216 = r215.emojicon;
        if (r216 != null) goto L55;
        r216 = r21;
    L55:
        r11 = LocaleController.formatString("AccDescrReactedWith", r47, new Object[]{r5, r216});
        r122 = r38;
        goto L61
    L44:
        r46.setImageDrawable(null);
        r38 = false;
        goto L45
    L46:
        r38 = false;
        goto L51
    L47:
        AnimatedEmojiDrawable r39 = new AnimatedEmojiDrawable(0, this.currentAccount, r215.documentId);
        r39.setColorFilter(Theme.getAnimatedEmojiColorFilter(this.resourcesProvider));
        BackupImageView r48 = this.reactView;
        if (r48 == null) goto L50;
        r48.setAnimatedEmojiDrawable(r39);
    L50:
        r38 = true;
        goto L51
    L56:
        BackupImageView r217 = this.reactView;
        if (r217 == null) goto L59;
        r217.setAnimatedEmojiDrawable(null);
        this.reactView.setImageDrawable(null);
    L59:
        r11 = LocaleController.formatString("AccDescrPersonHasSeen", R.string.AccDescrPersonHasSeen, new Object[]{this.titleView.getText()});
        r122 = false;
        goto L61
    L23:
        TLRPC.ChatPhoto r18 = r20.photo;
        if (r18 == null) goto L28;
        r12 = r18.strippedBitmap;
        if (r12 == null) goto L28;
    L15:
        this.dialogId = -r20.id;
        this.titleView.setText(r20.title);
        goto L16
    L11:
        r4 = Theme.key_chats_verifiedBackground;
        goto L12
    L5:
        r3 = r19;
        goto L6
    }
}
