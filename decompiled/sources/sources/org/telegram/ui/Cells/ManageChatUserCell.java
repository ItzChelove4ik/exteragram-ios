package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.exteragram.messenger.ExteraConfig;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Stories.StoriesUtilities;

/* loaded from: classes6.dex */
public class ManageChatUserCell extends FrameLayout {
    private final AvatarDrawable avatarDrawable;
    private final BackupImageView avatarImageView;
    private final int currentAccount;
    private CharSequence currentName;
    private Object currentObject;
    private CharSequence currentStatus;
    private ImageView customImageView;
    private ManageChatUserCellDelegate delegate;
    private int dividerColor;
    private boolean isAdmin;
    private TLRPC.FileLocation lastAvatar;
    private String lastName;
    private int lastStatus;
    private final int namePadding;
    private final SimpleTextView nameTextView;
    private boolean needDivider;
    private ImageView optionsButton;
    private final Theme.ResourcesProvider resourcesProvider;
    private int statusColor;
    private int statusOnlineColor;
    private final SimpleTextView statusTextView;
    private final StoriesUtilities.AvatarStoryParams storyAvatarParams;
    private TL_stories.StoryItem storyItem;
    private boolean subtitleUsername;

    /* renamed from: org.telegram.ui.Cells.ManageChatUserCell$1, reason: invalid class name */
    public class AnonymousClass1 extends BackupImageView {
        final /* synthetic */ ManageChatUserCell this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass1(ManageChatUserCell r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            this.val$resourcesProvider = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r6) {
            if (ManageChatUserCell.m6960$$Nest$fgetstoryItem(this.this$0) == null) goto L6;
            float r2 = AndroidUtilities.dp(1.0f);
            ManageChatUserCell.m6959$$Nest$fgetstoryAvatarParams(this.this$0).originalAvatarRect.set(r2, r2, getMeasuredWidth() - r0, getMeasuredHeight() - r0);
            ManageChatUserCell.m6959$$Nest$fgetstoryAvatarParams(this.this$0).drawSegments = false;
            ManageChatUserCell.m6959$$Nest$fgetstoryAvatarParams(this.this$0).animate = false;
            ManageChatUserCell.m6959$$Nest$fgetstoryAvatarParams(this.this$0).drawInside = true;
            ManageChatUserCell.m6959$$Nest$fgetstoryAvatarParams(this.this$0).isArchive = false;
            ManageChatUserCell.m6959$$Nest$fgetstoryAvatarParams(this.this$0).resourcesProvider = this.val$resourcesProvider;
            ManageChatUserCell.m6959$$Nest$fgetstoryAvatarParams(this.this$0).storyItem = ManageChatUserCell.m6960$$Nest$fgetstoryItem(this.this$0);
            StoriesUtilities.drawAvatarWithStory(ManageChatUserCell.m6960$$Nest$fgetstoryItem(this.this$0).dialogId, r6, this.imageReceiver, ManageChatUserCell.m6959$$Nest$fgetstoryAvatarParams(this.this$0));
            return;
        L6:
            super.onDraw(r6);
        }
    }

    public interface ManageChatUserCellDelegate {
        boolean onOptionsButtonCheck(ManageChatUserCell r1, boolean r2);
    }

    public static /* synthetic */ void $r8$lambda$CoT7sFn0QhR4NmvQ6s7IaX4q66U(ManageChatUserCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetstoryAvatarParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesUtilities.AvatarStoryParams m6959$$Nest$fgetstoryAvatarParams(ManageChatUserCell r0) {
        return r0.storyAvatarParams;
    }

    /* renamed from: -$$Nest$fgetstoryItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_stories.StoryItem m6960$$Nest$fgetstoryItem(ManageChatUserCell r0) {
        return r0.storyItem;
    }

    public ManageChatUserCell(Context r7, int r8, int r9, boolean r10) {
        this(r7, r8, r9, r10, null);
    }

    public ManageChatUserCell(Context r19, int r20, int r21, boolean r22, Theme.ResourcesProvider r23) {
        super(r19);
        this.dividerColor = -1;
        this.currentAccount = UserConfig.selectedAccount;
        this.storyAvatarParams = new StoriesUtilities.AvatarStoryParams(false);
        this.resourcesProvider = r23;
        this.statusColor = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r23);
        this.statusOnlineColor = Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, r23);
        this.namePadding = r21;
        this.avatarDrawable = new AvatarDrawable();
        AnonymousClass1 r4 = new AnonymousClass1(this, r19, r23);
        this.avatarImageView = r4;
        float r6 = 46.0f;
        r4.setRoundRadius(ExteraConfig.getAvatarCorners(46.0f));
        boolean r7 = LocaleController.isRTL;
        int r8 = 3;
        if (r7 == false) goto L5;
        int r10 = 5;
    L6:
        int r13 = r10 | 48;
        float r102 = 0.0f;
        if (r7 == false) goto L9;
        float r14 = 0.0f;
    L10:
        if (r7 == false) goto L12;
        r102 = r20 + 7;
    L12:
        addView(r4, LayoutHelper.createFrame(46, 46.0f, r13, r14, 8.0f, r102, 0.0f));
        SimpleTextView r42 = new SimpleTextView(r19);
        this.nameTextView = r42;
        r42.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r23));
        r42.setTextSize(17);
        r42.setTypeface(AndroidUtilities.bold());
        if (LocaleController.isRTL == false) goto L15;
        int r72 = 5;
    L16:
        r42.setGravity(r72 | 48);
        boolean r73 = LocaleController.isRTL;
        if (r73 == false) goto L19;
        int r103 = 5;
    L20:
        int r132 = r103 | 48;
        if (r73 == false) goto L23;
        float r142 = 46.0f;
    L24:
        if (r73 == false) goto L26;
        r6 = r21 + 68;
    L26:
        addView(r42, LayoutHelper.createFrame(-1, 20.0f, r132, r142, 11.5f, r6, 0.0f));
        NotificationCenter.listenEmojiLoading(r42);
        SimpleTextView r43 = new SimpleTextView(r19);
        this.statusTextView = r43;
        r43.setTextSize(14);
        if (LocaleController.isRTL == false) goto L29;
        int r62 = 5;
    L30:
        r43.setGravity(r62 | 48);
        boolean r63 = LocaleController.isRTL;
        if (r63 == false) goto L33;
        int r74 = 5;
    L34:
        int r12 = r74 | 48;
        float r75 = 28.0f;
        if (r63 == false) goto L37;
        float r133 = 28.0f;
    L38:
        if (r63 == false) goto L40;
        r75 = r21 + 68;
    L40:
        addView(r43, LayoutHelper.createFrame(-1, 20.0f, r12, r133, 34.5f, r75, 0.0f));
        if (r22 == false) goto L48;
        ImageView r2 = new ImageView(r19);
        this.optionsButton = r2;
        r2.setFocusable(false);
        this.optionsButton.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(Theme.key_stickers_menuSelector, r23)));
        this.optionsButton.setImageResource(R.drawable.ic_ab_other);
        this.optionsButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_stickers_menu, r23), PorterDuff.Mode.MULTIPLY));
        this.optionsButton.setScaleType(ImageView.ScaleType.CENTER);
        View r1 = this.optionsButton;
        if (LocaleController.isRTL == true) goto L46;
        r8 = 5;
    L46:
        addView(r1, LayoutHelper.createFrame(60, 64, r8 | 48));
        this.optionsButton.setOnClickListener(new ManageChatUserCell$$ExternalSyntheticLambda0(this));
        this.optionsButton.setContentDescription(LocaleController.getString(R.string.AccDescrUserOptions));
        return;
    L48:
        return;
    L37:
        r133 = r21 + 68;
        goto L38
    L33:
        r74 = 3;
        goto L34
    L29:
        r62 = 3;
        goto L30
    L23:
        r142 = r21 + 68;
        goto L24
    L19:
        r103 = 3;
        goto L20
    L15:
        r72 = 3;
        goto L16
    L9:
        r14 = r20 + 7;
        goto L10
    L5:
        r10 = 3;
        goto L6
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        this.delegate.onOptionsButtonCheck(this, true);
    }

    public BackupImageView getAvatarImageView() {
        return this.avatarImageView;
    }

    public Object getCurrentObject() {
        return this.currentObject;
    }

    public StoriesUtilities.AvatarStoryParams getStoryAvatarParams() {
        return this.storyAvatarParams;
    }

    public TL_stories.StoryItem getStoryItem() {
        return this.storyItem;
    }

    public long getUserId() {
        Object r2 = this.currentObject;
        if ((r2 instanceof TLRPC.User) == true) goto L5;
        return 0;
    L5:
        return ((TLRPC.User) r2).id;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (this.needDivider == false) goto L23;
        int r0 = this.dividerColor;
        if (r0 < 0) goto L8;
        Theme.dividerExtraPaint.setColor(Theme.getColor(r0, this.resourcesProvider));
    L8:
        if (LocaleController.isRTL == false) goto L11;
        float r02 = 0.0f;
    L10:
        float r3 = r02;
        float r4 = getMeasuredHeight() - 1;
        int r03 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L15;
        int r1 = AndroidUtilities.dp(68.0f);
    L16:
        float r5 = r03 - r1;
        float r6 = getMeasuredHeight() - 1;
        if (this.dividerColor < 0) goto L20;
        Paint r8 = Theme.dividerExtraPaint;
    L21:
        r9.drawLine(r3, r4, r5, r6, r8);
        return;
    L20:
        r8 = Theme.dividerPaint;
        goto L21
    L15:
        r1 = 0;
        goto L16
    L11:
        r02 = AndroidUtilities.dp(68.0f);
        goto L10
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
    }

    public void recycle() {
        this.avatarImageView.getImageReceiver().cancelLoadImage();
    }

    public void setCustomImageVisible(boolean r1) {
        ImageView r0 = this.customImageView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r1 == false) goto L7;
        int r12 = 0;
    L8:
        r0.setVisibility(r12);
        return;
    L7:
        r12 = 8;
        goto L8
    }

    public void setCustomRightImage(int r4) {
        ImageView r0 = new ImageView(getContext());
        this.customImageView = r0;
        r0.setImageResource(r4);
        this.customImageView.setScaleType(ImageView.ScaleType.CENTER);
        this.customImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_voipgroup_mutedIconUnscrolled, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
        ImageView r42 = this.customImageView;
        if (LocaleController.isRTL == false) goto L5;
        int r02 = 3;
    L6:
        addView(r42, LayoutHelper.createFrame(52, 64, r02 | 48));
        return;
    L5:
        r02 = 5;
        goto L6
    }

    public void setData(Object r22, CharSequence r23, CharSequence r24, boolean r25) {
        if (r22 != null) goto L6;
        this.currentStatus = null;
        this.currentName = null;
        this.currentObject = null;
        this.nameTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.statusTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.avatarImageView.setImageDrawable(null);
        return;
    L6:
        this.currentStatus = r24;
        this.currentName = r23;
        this.currentObject = r22;
        int r7 = 3;
        int r10 = 28;
        if (this.optionsButton == null) goto L53;
        boolean r1 = this.delegate.onOptionsButtonCheck(this, false);
        ImageView r11 = this.optionsButton;
        if (r1 == false) goto L11;
        int r12 = 0;
    L12:
        r11.setVisibility(r12);
        SimpleTextView r112 = this.nameTextView;
        boolean r122 = LocaleController.isRTL;
        if (r122 == false) goto L15;
        int r13 = 5;
    L16:
        int r16 = r13 | 48;
        if (r122 == false) goto L22;
        if (r1 == false) goto L20;
        int r123 = 46;
    L21:
        float r124 = r123;
        if (r24 != null) goto L25;
    L28:
        float r18 = 11.5f;
    L30:
        if (LocaleController.isRTL == false) goto L33;
        int r2 = this.namePadding + 68;
    L36:
        r112.setLayoutParams(LayoutHelper.createFrame(-1, 20.0f, r16, r124, r18, r2, 0.0f));
        SimpleTextView r26 = this.statusTextView;
        boolean r5 = LocaleController.isRTL;
        if (r5 == false) goto L39;
        r7 = 5;
    L39:
        int r162 = r7 | 48;
        if (r5 == false) goto L45;
        if (r1 == false) goto L43;
        int r6 = 46;
    L44:
        float r62 = r6;
        if (r5 == false) goto L49;
        float r14 = this.namePadding + 68;
    L52:
        r26.setLayoutParams(LayoutHelper.createFrame(-1, 20.0f, r162, r62, 34.5f, r14, 0.0f));
    L100:
        this.needDivider = r25;
        setWillNotDraw(!r25);
        update(0);
        return;
    L49:
        if (r1 == false) goto L51;
        r10 = 46;
    L51:
        r14 = r10;
        goto L52
    L43:
        r6 = 28;
        goto L44
    L45:
        r6 = this.namePadding + 68;
        goto L44
    L33:
        if (r1 == false) goto L35;
        r2 = 46;
        goto L36
    L35:
        r2 = 28;
        goto L36
    L25:
        if (r24.length() > 0) goto L28;
        r18 = 20.5f;
        goto L30
    L20:
        r123 = 28;
        goto L21
    L22:
        r123 = this.namePadding + 68;
        goto L21
    L15:
        r13 = 3;
        goto L16
    L11:
        r12 = 4;
        goto L12
    L53:
        ImageView r15 = this.customImageView;
        if (r15 == null) goto L100;
        if (r15.getVisibility() != 0) goto L58;
        boolean r17 = true;
    L59:
        SimpleTextView r113 = this.nameTextView;
        boolean r125 = LocaleController.isRTL;
        if (r125 == false) goto L62;
        int r132 = 5;
    L63:
        int r163 = r132 | 48;
        if (r125 == false) goto L69;
        if (r17 == false) goto L67;
        int r126 = 54;
    L68:
        float r127 = r126;
        if (r24 != null) goto L72;
    L75:
        float r182 = 11.5f;
    L77:
        if (LocaleController.isRTL == false) goto L80;
        int r27 = this.namePadding + 68;
    L83:
        r113.setLayoutParams(LayoutHelper.createFrame(-1, 20.0f, r163, r127, r182, r27, 0.0f));
        SimpleTextView r28 = this.statusTextView;
        boolean r52 = LocaleController.isRTL;
        if (r52 == false) goto L86;
        r7 = 5;
    L86:
        int r164 = r7 | 48;
        if (r52 == false) goto L92;
        if (r17 == false) goto L90;
        int r63 = 54;
    L91:
        float r64 = r63;
        if (r52 == false) goto L96;
        float r19 = this.namePadding + 68;
    L99:
        r28.setLayoutParams(LayoutHelper.createFrame(-1, 20.0f, r164, r64, 34.5f, r19, 0.0f));
        goto L100
    L96:
        if (r17 == false) goto L98;
        r10 = 54;
    L98:
        r19 = r10;
        goto L99
    L90:
        r63 = 28;
        goto L91
    L92:
        r63 = this.namePadding + 68;
        goto L91
    L80:
        if (r17 == false) goto L82;
        r27 = 54;
        goto L83
    L82:
        r27 = 28;
        goto L83
    L72:
        if (r24.length() > 0) goto L75;
        r182 = 20.5f;
        goto L77
    L67:
        r126 = 28;
        goto L68
    L69:
        r126 = this.namePadding + 68;
        goto L68
    L62:
        r132 = 3;
        goto L63
    L58:
        r17 = false;
        goto L59
    }

    public void setDelegate(ManageChatUserCellDelegate r1) {
        this.delegate = r1;
    }

    public void setDividerColor(int r1) {
        this.dividerColor = r1;
    }

    public void setIsAdmin(boolean r1) {
        this.isAdmin = r1;
    }

    public void setNameColor(int r1) {
        this.nameTextView.setTextColor(r1);
    }

    public void setStatusColors(int r1, int r2) {
        this.statusColor = r1;
        this.statusOnlineColor = r2;
    }

    public void setStoryItem(TL_stories.StoryItem r1, View.OnClickListener r2) {
        this.storyItem = r1;
        this.avatarImageView.setOnClickListener(r2);
    }

    public void setUsernameSubtitle() {
        this.subtitleUsername = true;
    }

    public void update(int r11) {
        Object r0 = this.currentObject;
        if (r0 == null) goto L163;
        boolean r2 = true;
        if ((r0 instanceof TLRPC.User) == false) goto L97;
        TLRPC.User r02 = (TLRPC.User) r0;
        TLRPC.UserProfilePhoto r1 = r02.photo;
        if (r1 == null) goto L10;
        TLRPC.FileLocation r12 = r1.photo_small;
    L11:
        if (r11 != 0) goto L13;
        String r112 = null;
    L51:
        this.avatarDrawable.setInfo(this.currentAccount, r02);
        TLRPC.UserStatus r22 = r02.status;
        if (r22 == null) goto L54;
        this.lastStatus = r22.expires;
    L55:
        CharSequence r23 = this.currentName;
        if (r23 == null) goto L58;
        this.lastName = null;
        this.nameTextView.setText(r23);
    L62:
        if (this.currentStatus == null) goto L64;
        this.statusTextView.setTextColor(this.statusColor);
        this.statusTextView.setText(this.currentStatus);
    L94:
        this.lastAvatar = r12;
        this.avatarImageView.setForUserOrChat(r02, this.avatarDrawable);
        return;
    L64:
        String r113 = DialogObject.getPublicUsername(r02);
        if (r02.bot == false) goto L79;
        this.statusTextView.setTextColor(this.statusColor);
        if (this.subtitleUsername == false) goto L72;
        if (TextUtils.isEmpty(r113) == true) goto L72;
        this.statusTextView.setText(r113);
    L72:
        if (r02.bot_chat_history == false) goto L74;
    L77:
        this.statusTextView.setText(LocaleController.getString(R.string.BotStatusRead));
        goto L94
    L74:
        if (this.isAdmin == true) goto L77;
        this.statusTextView.setText(LocaleController.getString(R.string.BotStatusCantRead));
        goto L94
    L79:
        if (this.subtitleUsername == false) goto L84;
        if (TextUtils.isEmpty(r113) == true) goto L84;
        this.statusTextView.setText(r113);
        this.statusTextView.setTextColor(this.statusColor);
    L84:
        if (r02.id == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L93;
        TLRPC.UserStatus r114 = r02.status;
        if (r114 == null) goto L90;
        if (r114.expires > ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L93;
    L90:
        if (MessagesController.getInstance(this.currentAccount).onlinePrivacy.containsKey(Long.valueOf(r02.id)) == true) goto L93;
        this.statusTextView.setTextColor(this.statusColor);
        this.statusTextView.setText(LocaleController.formatUserStatus(this.currentAccount, r02));
    L93:
        this.statusTextView.setTextColor(this.statusOnlineColor);
        this.statusTextView.setText(LocaleController.getString(R.string.Online));
        goto L94
    L58:
        if (r112 != null) goto L60;
        r112 = UserObject.getUserName(r02);
    L60:
        this.lastName = r112;
        SimpleTextView r24 = this.nameTextView;
        r24.setText(Emoji.replaceEmoji(r112, r24.getPaint().getFontMetricsInt(), false));
        goto L62
    L54:
        this.lastStatus = 0;
        goto L55
    L13:
        if ((MessagesController.UPDATE_MASK_AVATAR & r11) == 0) goto L25;
        TLRPC.FileLocation r5 = this.lastAvatar;
        if (r5 == null) goto L17;
        if (r12 != null) goto L17;
    L24:
        boolean r52 = true;
    L26:
        if (r52 == false) goto L28;
    L36:
        if (r52 == false) goto L38;
    L47:
        r112 = null;
    L46:
        r2 = r52;
    L48:
        if (r2 == true) goto L51;
        return;
    L38:
        if (this.currentName != null) goto L47;
        if (this.lastName == null) goto L47;
        if ((r11 & MessagesController.UPDATE_MASK_NAME) == 0) goto L47;
        r112 = UserObject.getUserName(r02);
        if (r112.equals(this.lastName) == true) goto L46;
    L28:
        if ((MessagesController.UPDATE_MASK_STATUS & r11) == 0) goto L36;
        TLRPC.UserStatus r6 = r02.status;
        if (r6 == null) goto L32;
        int r62 = r6.expires;
    L34:
        if (r62 == this.lastStatus) goto L36;
        r52 = true;
        goto L36
    L32:
        r62 = 0;
    L17:
        if (r5 != null) goto L19;
        if (r12 != null) goto L24;
    L19:
        if (r5 == null) goto L25;
        if (r5.volume_id != r12.volume_id) goto L24;
        if (r5.local_id != r12.local_id) goto L24;
    L25:
        r52 = false;
        goto L26
    L10:
        r12 = null;
        goto L11
    L97:
        if ((r0 instanceof TLRPC.Chat) == false) goto L159;
        TLRPC.Chat r03 = (TLRPC.Chat) r0;
        TLRPC.ChatPhoto r13 = r03.photo;
        if (r13 == null) goto L101;
        TLRPC.FileLocation r14 = r13.photo_small;
    L102:
        if (r11 != 0) goto L104;
        String r115 = null;
    L132:
        this.avatarDrawable.setInfo(this.currentAccount, r03);
        CharSequence r25 = this.currentName;
        if (r25 == null) goto L135;
        this.lastName = null;
        this.nameTextView.setText(r25);
    L138:
        CharSequence r116 = this.currentStatus;
        SimpleTextView r26 = this.statusTextView;
        if (r116 == null) goto L141;
        r26.setTextColor(this.statusColor);
        this.statusTextView.setText(this.currentStatus);
    L156:
        this.lastAvatar = r14;
        this.avatarImageView.setForUserOrChat(r03, this.avatarDrawable);
        return;
    L141:
        r26.setTextColor(this.statusColor);
        if (r03.participants_count == 0) goto L150;
        if (ChatObject.isChannel(r03) == true) goto L146;
    L148:
        this.statusTextView.setText(LocaleController.formatPluralString("Members", r03.participants_count, new Object[0]));
        goto L156
    L146:
        if (r03.megagroup == true) goto L148;
        this.statusTextView.setText(LocaleController.formatPluralString("Subscribers", r03.participants_count, new Object[0]));
        goto L156
    L150:
        if (r03.has_geo == false) goto L152;
        this.statusTextView.setText(LocaleController.getString(R.string.MegaLocation));
        goto L156
    L152:
        boolean r117 = ChatObject.isPublic(r03);
        SimpleTextView r27 = this.statusTextView;
        if (r117 == true) goto L155;
        r27.setText(LocaleController.getString(R.string.MegaPrivate));
        goto L156
    L155:
        r27.setText(LocaleController.getString(R.string.MegaPublic));
        goto L156
    L135:
        if (r115 != null) goto L137;
        r115 = r03.title;
    L137:
        this.lastName = r115;
        this.nameTextView.setText(r115);
        goto L138
    L104:
        if ((MessagesController.UPDATE_MASK_AVATAR & r11) == 0) goto L116;
        TLRPC.FileLocation r53 = this.lastAvatar;
        if (r53 == null) goto L108;
        if (r14 != null) goto L108;
    L115:
        boolean r54 = true;
    L117:
        if (r54 == false) goto L119;
    L128:
        r115 = null;
    L127:
        r2 = r54;
    L129:
        if (r2 == true) goto L132;
        return;
    L119:
        if (this.currentName != null) goto L128;
        String r63 = this.lastName;
        if (r63 == null) goto L128;
        if ((r11 & MessagesController.UPDATE_MASK_NAME) == 0) goto L128;
        r115 = r03.title;
        if (r115.equals(r63) == true) goto L127;
    L108:
        if (r53 != null) goto L110;
        if (r14 != null) goto L115;
    L110:
        if (r53 == null) goto L116;
        if (r53.volume_id != r14.volume_id) goto L115;
        if (r53.local_id != r14.local_id) goto L115;
    L116:
        r54 = false;
        goto L117
    L101:
        r14 = null;
        goto L102
    L159:
        if ((r0 instanceof Integer) == false) goto L162;
        this.nameTextView.setText(this.currentName);
        this.statusTextView.setTextColor(this.statusColor);
        this.statusTextView.setText(this.currentStatus);
        this.avatarDrawable.setAvatarType(3);
        this.avatarImageView.setImage(null, "50_50", this.avatarDrawable);
        return;
    L162:
        return;
    }
}
