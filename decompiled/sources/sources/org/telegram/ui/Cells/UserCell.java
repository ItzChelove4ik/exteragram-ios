package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.exteragram.messenger.AvatarCornerType;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.badges.BadgesController;
import java.util.ArrayList;
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
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CheckBoxSquare;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.NotificationsSettingsActivity;
import org.telegram.ui.Stories.StoriesListPlaceProvider;
import org.telegram.ui.Stories.StoriesUtilities;

/* loaded from: classes3.dex */
public class UserCell extends FrameLayout implements NotificationCenter.NotificationCenterDelegate, Theme.Colorable {
    private TextView addButton;
    private TextView adminTextView;
    protected AvatarDrawable avatarDrawable;
    public BackupImageView avatarImageView;
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable botVerification;
    private boolean callCellStyle;
    private CheckBox2 checkBox;
    private ImageView checkBox3;
    private CheckBoxSquare checkBoxBig;
    private ImageView closeView;
    private int currentAccount;
    private int currentDrawable;
    private int currentId;
    private CharSequence currentName;
    private Object currentObject;
    private CharSequence currentStatus;
    protected long dialogId;
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable emojiStatus;
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable emojiStatus2;
    private TLRPC.EncryptedChat encryptedChat;
    private ImageView imageView;
    private boolean isAdmin;
    private boolean isCommunity;
    private boolean isOwner;
    private TLRPC.FileLocation lastAvatar;
    private String lastName;
    private int lastStatus;
    private boolean mutual;
    private final ImageView mutualContactView;
    protected SimpleTextView nameTextView;
    public boolean needDivider;
    private final int padding;
    private Drawable premiumDrawable;
    private String query;
    protected Theme.ResourcesProvider resourcesProvider;
    private boolean selfAsSavedMessages;
    private int statusColor;
    private int statusOnlineColor;
    protected SimpleTextView statusTextView;
    private boolean storiable;
    public StoriesUtilities.AvatarStoryParams storyParams;

    /* renamed from: org.telegram.ui.Cells.UserCell$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends StoriesUtilities.AvatarStoryParams {
        final /* synthetic */ UserCell this$0;

        public AnonymousClass1(UserCell r1, boolean r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Stories.StoriesUtilities.AvatarStoryParams
        public void openStory(long r1, Runnable r3) {
            this.this$0.openStory(r1, r3);
        }
    }

    /* renamed from: org.telegram.ui.Cells.UserCell$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends BackupImageView {
        final /* synthetic */ UserCell this$0;

        public AnonymousClass2(UserCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r5) {
            if (UserCell.m7123$$Nest$fgetstoriable(this.this$0) == false) goto L6;
            this.this$0.storyParams.originalAvatarRect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            UserCell r0 = this.this$0;
            StoriesUtilities.drawAvatarWithStory(r0.dialogId, r5, this.imageReceiver, r0.storyParams);
            return;
        L6:
            super.onDraw(r5);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.storyParams.checkOnTouchEvent(r2, this) == false) goto L7;
            return true;
        L7:
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Cells.UserCell$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends AnimatedEmojiDrawable.WrapSizeDrawable {
        final /* synthetic */ UserCell this$0;

        public AnonymousClass3(UserCell r1, Drawable r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.AnimatedEmojiDrawable.WrapSizeDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            r3.save();
            r3.translate(0.0f, AndroidUtilities.dp(1.0f));
            super.draw(r3);
            r3.restore();
        }
    }

    /* renamed from: $r8$lambda$rjQ-bWx2Lp42RMLZthGILoZt50U, reason: not valid java name */
    public static /* synthetic */ void m7122$r8$lambda$rjQbWx2Lp42RMLZthGILoZt50U(View r3) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.getString(R.string.MutualContactInfo)});
    }

    /* renamed from: -$$Nest$fgetstoriable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7123$$Nest$fgetstoriable(UserCell r0) {
        return r0.storiable;
    }

    public UserCell(Context r8, int r9, int r10, boolean r11) {
        this(r8, r9, r10, r11, false, null);
    }

    public UserCell(Context r8, int r9, int r10, boolean r11, Theme.ResourcesProvider r12) {
        this(r8, r9, r10, r11, false, r12);
    }

    public UserCell(Context r8, int r9, int r10, boolean r11, boolean r12) {
        this(r8, r9, r10, r11, r12, null);
    }

    public UserCell(Context r31, int r32, int r33, boolean r34, boolean r35, Theme.ResourcesProvider r36) {
        super(r31);
        this.currentAccount = UserConfig.selectedAccount;
        this.storyParams = new AnonymousClass1(this, false);
        this.resourcesProvider = r36;
        if (r35 == false) goto L16;
        TextView r11 = new TextView(r31);
        this.addButton = r11;
        r11.setGravity(17);
        this.addButton.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, r36));
        this.addButton.setTextSize(1, 14.0f);
        this.addButton.setTypeface(AndroidUtilities.bold());
        this.addButton.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{14.0f}));
        this.addButton.setText(LocaleController.getString(R.string.Add));
        this.addButton.setPadding(AndroidUtilities.dp(17.0f), 0, AndroidUtilities.dp(17.0f), 0);
        View r112 = this.addButton;
        boolean r12 = LocaleController.isRTL;
        if (r12 == false) goto L7;
        int r13 = 3;
    L8:
        int r16 = r13 | 48;
        if (r12 == false) goto L11;
        float r17 = 14.0f;
    L12:
        if (r12 == false) goto L14;
        float r19 = 0.0f;
    L15:
        addView(r112, LayoutHelper.createFrame(-2, 28.0f, r16, r17, 15.0f, r19, 0.0f));
        int r113 = (int) Math.ceil((this.addButton.getPaint().measureText(this.addButton.getText().toString()) + AndroidUtilities.dp(48.0f)) / AndroidUtilities.density);
    L17:
        this.padding = r32;
        this.statusColor = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r36);
        this.statusOnlineColor = Theme.getColor(Theme.key_telegram_color_text, r36);
        this.avatarDrawable = new AvatarDrawable();
        this.avatarImageView = new AnonymousClass2(this, r31);
        setAvatarRadius(ExteraConfig.getAvatarCorners(46.0f));
        View r122 = this.avatarImageView;
        boolean r132 = LocaleController.isRTL;
        if (r132 == false) goto L20;
        int r14 = 5;
    L21:
        int r172 = r14 | 48;
        if (r132 == false) goto L24;
        float r18 = 0.0f;
    L25:
        if (r132 == false) goto L27;
        float r20 = r32 + 7;
    L28:
        addView(r122, LayoutHelper.createFrame(46, 46.0f, r172, r18, 6.0f, r20, 0.0f));
        setClipChildren(false);
        SimpleTextView r123 = new SimpleTextView(r31);
        this.nameTextView = r123;
        r123.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r36));
        this.nameTextView.setTypeface(AndroidUtilities.bold());
        this.nameTextView.setTextSize(16);
        SimpleTextView r124 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L31;
        int r142 = 5;
    L32:
        r124.setGravity(r142 | 48);
        View r125 = this.nameTextView;
        boolean r143 = LocaleController.isRTL;
        if (r143 == false) goto L35;
        int r15 = 5;
    L36:
        int r182 = r15 | 48;
        int r152 = 18;
        if (r143 == false) goto L43;
        if (r33 != 2) goto L40;
        int r162 = 18;
    L41:
        int r10 = (r162 + 28) + r113;
    L42:
        float r102 = r10;
        if (r143 == false) goto L47;
        float r103 = r32 + 64;
    L51:
        addView(r125, LayoutHelper.createFrame(-1, 20.0f, r182, r102, 10.0f, r103, 0.0f));
        this.botVerification = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.nameTextView, AndroidUtilities.dp(20.0f));
        this.emojiStatus = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.nameTextView, AndroidUtilities.dp(20.0f));
        this.emojiStatus2 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.nameTextView, AndroidUtilities.dp(20.0f));
        SimpleTextView r104 = new SimpleTextView(r31);
        this.statusTextView = r104;
        r104.setTextSize(15);
        SimpleTextView r105 = this.statusTextView;
        if (LocaleController.isRTL == false) goto L54;
        int r126 = 5;
    L55:
        r105.setGravity(r126 | 48);
        View r106 = this.statusTextView;
        boolean r127 = LocaleController.isRTL;
        if (r127 == false) goto L58;
        int r144 = 5;
    L59:
        int r173 = r144 | 48;
        if (r127 == false) goto L63;
        int r145 = r113 + 28;
    L62:
        float r146 = r145;
        if (r127 == false) goto L67;
        int r114 = r32 + 64;
    L68:
        addView(r106, LayoutHelper.createFrame(-1, 20.0f, r173, r146, 32.0f, r114, 0.0f));
        ImageView r107 = new ImageView(r31);
        this.imageView = r107;
        ImageView.ScaleType r115 = ImageView.ScaleType.CENTER;
        r107.setScaleType(r115);
        ImageView r108 = this.imageView;
        int r147 = Theme.key_windowBackgroundWhiteGrayIcon;
        int r153 = Theme.getColor(r147, r36);
        PorterDuff.Mode r133 = PorterDuff.Mode.MULTIPLY;
        r108.setColorFilter(new PorterDuffColorFilter(r153, r133));
        this.imageView.setVisibility(8);
        View r109 = this.imageView;
        boolean r154 = LocaleController.isRTL;
        if (r154 == false) goto L71;
        int r163 = 5;
    L72:
        int r25 = r163 | 16;
        if (r154 == false) goto L75;
        float r26 = 0.0f;
    L76:
        if (r154 == false) goto L78;
        float r28 = 16.0f;
    L79:
        addView(r109, LayoutHelper.createFrame(-2, -2.0f, r25, r26, 0.0f, r28, 0.0f));
        if (r33 != 2) goto L93;
        CheckBoxSquare r2 = new CheckBoxSquare(r31, false);
        this.checkBoxBig = r2;
        boolean r3 = LocaleController.isRTL;
        if (r3 == false) goto L84;
        int r6 = 3;
    L85:
        int r174 = r6 | 16;
        if (r3 == false) goto L88;
        float r183 = 19.0f;
    L89:
        if (r3 == false) goto L91;
        float r202 = 0.0f;
    L92:
        addView(r2, LayoutHelper.createFrame(18, 18.0f, r174, r183, 0.0f, r202, 0.0f));
    L119:
        if (r34 == false) goto L132;
        TextView r22 = new TextView(r31);
        this.adminTextView = r22;
        ScaleStateListAnimator.apply(r22, 0.05f, 1.2f);
        this.adminTextView.setTextSize(1, 14.0f);
        this.adminTextView.setTextColor(Theme.getColor(Theme.key_profile_creatorIcon, r36));
        this.adminTextView.setImportantForAccessibility(2);
        View r23 = this.adminTextView;
        boolean r37 = LocaleController.isRTL;
        if (r37 == false) goto L123;
        int r4 = 3;
    L124:
        int r175 = r4 | 48;
        if (r37 == false) goto L127;
        float r184 = 23.0f;
    L128:
        if (r37 == false) goto L130;
        float r203 = 0.0f;
    L131:
        addView(r23, LayoutHelper.createFrame(-2, -2.0f, r175, r184, 10.0f, r203, 0.0f));
        goto L132
    L130:
        r203 = 23.0f;
        goto L131
    L127:
        r184 = 0.0f;
        goto L128
    L123:
        r4 = 5;
    L132:
        ImageView r24 = new ImageView(r31);
        this.mutualContactView = r24;
        r24.setImageResource(R.drawable.msg_switch);
        r24.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_player_actionBarSelector)));
        r24.setScaleType(r115);
        r24.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r147), r133));
        r24.setVisibility(8);
        r24.setOnClickListener(new UserCell$$ExternalSyntheticLambda0());
        boolean r1 = LocaleController.isRTL;
        if (r1 == false) goto L135;
        int r8 = 3;
    L136:
        int r116 = r8 | 16;
        if (r1 == false) goto L139;
        float r128 = 10.0f;
    L140:
        if (r1 == false) goto L142;
        float r148 = 0.0f;
    L143:
        addView(r24, LayoutHelper.createFrame(40, 40.0f, r116, r128, 0.0f, r148, 0.0f));
        setFocusable(true);
        return;
    L142:
        r148 = 10.0f;
        goto L143
    L139:
        r128 = 0.0f;
        goto L140
    L135:
        r8 = 5;
        goto L136
    L91:
        r202 = 19.0f;
        goto L92
    L88:
        r183 = 0.0f;
        goto L89
    L84:
        r6 = 5;
        goto L85
    L93:
        if (r33 != 1) goto L106;
        CheckBox2 r38 = new CheckBox2(r31, 21, r36);
        this.checkBox = r38;
        r38.setDrawUnchecked(false);
        this.checkBox.setDrawBackgroundAsArc(3);
        this.checkBox.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
        View r39 = this.checkBox;
        boolean r62 = LocaleController.isRTL;
        if (r62 == false) goto L97;
        int r1010 = 5;
    L98:
        int r176 = r1010 | 48;
        if (r62 == false) goto L101;
        float r185 = 0.0f;
    L102:
        if (r62 == false) goto L104;
        float r204 = r32 + 24;
    L105:
        addView(r39, LayoutHelper.createFrame(24, 24.0f, r176, r185, 36.0f, r204, 0.0f));
        goto L119
    L104:
        r204 = 0.0f;
        goto L105
    L101:
        r185 = r32 + 24;
        goto L102
    L97:
        r1010 = 3;
        goto L98
    L106:
        if (r33 != 3) goto L119;
        ImageView r310 = new ImageView(r31);
        this.checkBox3 = r310;
        r310.setScaleType(r115);
        this.checkBox3.setImageResource(R.drawable.account_check);
        this.checkBox3.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_featuredStickers_addButton, r36), r133));
        this.checkBox3.setVisibility(8);
        View r311 = this.checkBox3;
        boolean r63 = LocaleController.isRTL;
        if (r63 == false) goto L110;
        int r1011 = 3;
    L111:
        int r177 = r1011 | 16;
        if (r63 == false) goto L114;
        float r186 = r32 + 10;
    L115:
        if (r63 == false) goto L117;
        float r205 = 0.0f;
    L118:
        addView(r311, LayoutHelper.createFrame(24, 24.0f, r177, r186, 0.0f, r205, 0.0f));
        goto L119
    L117:
        r205 = r32 + 10;
        goto L118
    L114:
        r186 = 0.0f;
        goto L115
    L110:
        r1011 = 5;
        goto L111
    L78:
        r28 = 0.0f;
        goto L79
    L75:
        r26 = 16.0f;
        goto L76
    L71:
        r163 = 3;
        goto L72
    L67:
        r114 = r113 + 28;
        goto L68
    L63:
        r145 = r32 + 64;
        goto L62
    L58:
        r144 = 3;
        goto L59
    L54:
        r126 = 3;
        goto L55
    L47:
        if (r33 == 2) goto L50;
        r152 = 0;
    L50:
        r103 = (r152 + 28) + r113;
        goto L51
    L40:
        r162 = 0;
        goto L41
    L43:
        r10 = r32 + 64;
        goto L42
    L35:
        r15 = 3;
        goto L36
    L31:
        r142 = 3;
        goto L32
    L27:
        r20 = 0.0f;
        goto L28
    L24:
        r18 = r32 + 7;
        goto L25
    L20:
        r14 = 3;
        goto L21
    L14:
        r19 = 14.0f;
        goto L15
    L11:
        r17 = 0.0f;
        goto L12
    L7:
        r13 = 5;
        goto L8
    L16:
        r113 = 0;
        goto L17
    }

    private void setAvatarRadius(int r3) {
        this.storyParams.avatarRadius = r3;
        this.avatarImageView.setRoundRadius(r3);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.emojiLoaded) goto L6;
        this.nameTextView.invalidate();
        return;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r6, View r7, long r8) {
        if (this.isCommunity == false) goto L8;
        if (r7 != this.avatarImageView) goto L8;
        DrawableUtils.drawCommunityCardDrawable(r6, Theme.dialogs_communityCardsDrawable, r7.getX() + (r7.getWidth() / 2.0f), r7.getY() + (r7.getHeight() / 2.0f), r7.getHeight());
    L8:
        return super.drawChild(r6, r7, r8);
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public Object getCurrentObject() {
        return this.currentObject;
    }

    public long getDialogId() {
        return this.dialogId;
    }

    public CharSequence getName() {
        return this.nameTextView.getText();
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        CheckBoxSquare r0 = this.checkBoxBig;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        this.emojiStatus.attach();
        this.emojiStatus2.attach();
        this.botVerification.attach();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        this.emojiStatus.detach();
        this.emojiStatus2.detach();
        this.botVerification.detach();
        this.storyParams.onDetachFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (this.needDivider == true) goto L5;
        return;
    L5:
        if (LocaleController.isRTL == false) goto L8;
        float r0 = 0.0f;
    L7:
        float r3 = r0;
        float r4 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L12;
        int r1 = AndroidUtilities.dp(68.0f);
    L13:
        r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(68.0f);
        goto L7
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r5) {
        super.onInitializeAccessibilityNodeInfo(r5);
        CheckBoxSquare r0 = this.checkBoxBig;
        if (r0 != null) goto L5;
    L7:
        CheckBox2 r02 = this.checkBox;
        if (r02 != null) goto L10;
    L12:
        StringBuilder r03 = new StringBuilder();
        SimpleTextView r1 = this.nameTextView;
        if (r1 == null) goto L17;
        CharSequence r12 = r1.getText();
        if (TextUtils.isEmpty(r12) == true) goto L17;
        r03.append(r12);
    L17:
        TextView r13 = this.adminTextView;
        if (r13 != null) goto L20;
    L27:
        SimpleTextView r4 = this.statusTextView;
        if (r4 == null) goto L36;
        CharSequence r42 = r4.getText();
        if (TextUtils.isEmpty(r42) == true) goto L36;
        if (r03.length() <= 0) goto L34;
        r03.append(", ");
    L34:
        r03.append(r42);
    L36:
        if (r03.length() <= 0) goto L39;
        r5.setContentDescription(r03);
        return;
    L39:
        return;
    L20:
        if (r13.getVisibility() != 0) goto L27;
        CharSequence r14 = this.adminTextView.getText();
        if (TextUtils.isEmpty(r14) == true) goto L27;
        if (r03.length() <= 0) goto L26;
        r03.append(", ");
    L26:
        r03.append(r14);
        goto L27
    L10:
        if (r02.getVisibility() != 0) goto L12;
        r5.setCheckable(true);
        r5.setChecked(this.checkBox.isChecked());
        r5.setClassName("android.widget.CheckBox");
        goto L12
    L5:
        if (r0.getVisibility() != 0) goto L7;
        r5.setCheckable(true);
        r5.setChecked(this.checkBoxBig.isChecked());
        r5.setClassName("android.widget.CheckBox");
        goto L12
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        int r32 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30);
        if (this.callCellStyle == false) goto L5;
        float r0 = 56.0f;
    L6:
        super.onMeasure(r32, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r0) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        return;
    L5:
        r0 = 58.0f;
        goto L6
    }

    public void openStory(long r3, Runnable r5) {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 == null) goto L6;
        r0.getOrCreateStoryViewer().doOnAnimationReady(r5);
        r0.getOrCreateStoryViewer().open(getContext(), r3, StoriesListPlaceProvider.of((RecyclerListView) getParent()));
        return;
    }

    public void setAddButtonVisible(boolean r1) {
        TextView r0 = this.addButton;
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

    public void setAdminRole(String r9, boolean r10, boolean r11, boolean r12, View.OnClickListener r13) {
        if (this.adminTextView != null) goto L5;
        return;
    L5:
        this.isAdmin = r10;
        this.isOwner = r11;
        if (r11 == false) goto L8;
        int r0 = Theme.getColor(Theme.key_chat_tagCreator, this.resourcesProvider);
    L15:
        this.adminTextView.setTextColor(r0);
        if (r10 == true) goto L24;
        if (r11 == true) goto L24;
        if (r12 == true) goto L21;
    L23:
        this.adminTextView.setText(r9);
        this.adminTextView.setPadding(0, 0, 0, 0);
        this.adminTextView.setTranslationX(0.0f);
        this.adminTextView.setBackground(null);
        this.adminTextView.setOnClickListener(r13);
    L25:
        TextView r102 = this.adminTextView;
        if (r9 != null) goto L30;
        if (r12 == true) goto L30;
        int r112 = 8;
    L31:
        r102.setVisibility(r112);
        if (r9 != null) goto L37;
        if (r12 == true) goto L37;
        setRightPadding(0, true, false);
        return;
    L37:
        CharSequence r92 = this.adminTextView.getText();
        setRightPadding((int) Math.ceil(this.adminTextView.getPaint().measureText(r92, 0, r92.length())), true, false);
        return;
    L30:
        r112 = 0;
        goto L31
    L21:
        if (TextUtils.isEmpty(r9) == false) goto L23;
        this.adminTextView.setText(LocaleController.getString(R.string.AddTag));
        this.adminTextView.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(0.66f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(1.0f));
        this.adminTextView.setTranslationX(AndroidUtilities.dp(6.0f));
        this.adminTextView.setBackground(Theme.createRadSelectorDrawable(Theme.multAlpha(r0, 0.12f), AndroidUtilities.dp(32.0f), AndroidUtilities.dp(32.0f)));
        this.adminTextView.setOnClickListener(r13);
    L24:
        this.adminTextView.setText(r9);
        this.adminTextView.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(0.66f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(1.0f));
        this.adminTextView.setTranslationX(AndroidUtilities.dp(6.0f));
        this.adminTextView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(32.0f), Theme.multAlpha(r0, 0.12f)));
        this.adminTextView.setOnClickListener(r13);
        goto L25
    L8:
        if (r10 == false) goto L10;
        r0 = Theme.getColor(Theme.key_chat_tagAdmin, this.resourcesProvider);
        goto L15
    L10:
        if (r12 == true) goto L12;
    L14:
        r0 = Theme.getColor(Theme.key_chat_inAdminText, this.resourcesProvider);
        goto L15
    L12:
        if (TextUtils.isEmpty(r9) == false) goto L14;
        r0 = Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider);
        goto L15
    }

    public void setAvatarPadding(int r2) {
        setAvatarPadding(r2, 0);
    }

    public void setAvatarPadding(int r6, int r7) {
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.avatarImageView.getLayoutParams();
        float r2 = 0.0f;
        if (LocaleController.isRTL == false) goto L5;
        float r1 = 0.0f;
    L6:
        r0.leftMargin = AndroidUtilities.dp(r1);
        if (LocaleController.isRTL == false) goto L9;
        float r12 = r6 + 7;
    L10:
        r0.rightMargin = AndroidUtilities.dp(r12);
        this.avatarImageView.setLayoutParams(r0);
        FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) this.nameTextView.getLayoutParams();
        int r3 = 0;
        if (LocaleController.isRTL == true) goto L13;
        int r13 = (r6 + 64) + r7;
    L19:
        r02.leftMargin = AndroidUtilities.dp(r13);
        if (LocaleController.isRTL == false) goto L23;
        float r14 = (r6 + 64) + r7;
    L26:
        r02.rightMargin = AndroidUtilities.dp(r14);
        FrameLayout.LayoutParams r03 = (FrameLayout.LayoutParams) this.statusTextView.getLayoutParams();
        float r32 = 28.0f;
        if (LocaleController.isRTL == false) goto L29;
        float r15 = 28.0f;
    L30:
        r03.leftMargin = AndroidUtilities.dp(r15);
        if (LocaleController.isRTL == false) goto L33;
        r32 = (r6 + 64) + r7;
    L33:
        r03.rightMargin = AndroidUtilities.dp(r32);
        CheckBox2 r5 = this.checkBox;
        if (r5 == null) goto L44;
        FrameLayout.LayoutParams r52 = (FrameLayout.LayoutParams) r5.getLayoutParams();
        if (LocaleController.isRTL == false) goto L38;
        float r04 = 0.0f;
    L39:
        r52.leftMargin = AndroidUtilities.dp(r04);
        if (LocaleController.isRTL == false) goto L42;
        r2 = (r6 + 32) + r7;
    L42:
        r52.rightMargin = AndroidUtilities.dp(r2);
        return;
    L38:
        r04 = (r6 + 32) + r7;
        goto L39
    L44:
        return;
    L29:
        r15 = (r6 + 64) + r7;
        goto L30
    L23:
        if (this.checkBoxBig == null) goto L25;
        r3 = 18;
    L25:
        r14 = r3 + 28;
        goto L26
    L13:
        if (this.checkBoxBig == null) goto L15;
        int r16 = 18;
    L16:
        r13 = r16 + 28;
        goto L19
    L15:
        r16 = 0;
        goto L16
    L9:
        r12 = 0.0f;
        goto L10
    L5:
        r1 = r6 + 7;
        goto L6
    }

    public void setCallCellStyle(int r14) {
        this.callCellStyle = true;
        this.nameTextView.setTextSize(15);
        SimpleTextView r0 = this.nameTextView;
        boolean r1 = LocaleController.isRTL;
        int r2 = 3;
        if (r1 == false) goto L5;
        int r4 = 5;
    L6:
        int r7 = r4 | 48;
        float r42 = 30.0f;
        if (r1 == false) goto L9;
        float r8 = 30.0f;
    L10:
        if (r1 == false) goto L12;
        float r10 = r14 + 66;
    L13:
        r0.setLayoutParams(LayoutHelper.createFrame(-1, 20.0f, r7, r8, 10.0f, r10, 0.0f));
        this.statusTextView.setTextSize(13);
        SimpleTextView r02 = this.statusTextView;
        boolean r12 = LocaleController.isRTL;
        if (r12 == false) goto L16;
        int r5 = 5;
    L17:
        int r82 = r5 | 48;
        if (r12 == false) goto L20;
        float r9 = 30.0f;
    L21:
        if (r12 == false) goto L23;
        r42 = r14 + 66;
    L23:
        r02.setLayoutParams(LayoutHelper.createFrame(-1, 20.0f, r82, r9, 32.0f, r42, 0.0f));
        this.avatarImageView.setRoundRadius(AndroidUtilities.dp(22.0f));
        BackupImageView r03 = this.avatarImageView;
        boolean r13 = LocaleController.isRTL;
        if (r13 == false) goto L26;
        int r43 = 5;
    L27:
        int r72 = r43 | 48;
        float r44 = 0.0f;
        if (r13 == false) goto L30;
        float r83 = 0.0f;
    L31:
        if (r13 == false) goto L33;
        float r102 = r14 + 8;
    L34:
        r03.setLayoutParams(LayoutHelper.createFrame(44, 44.0f, r72, r83, 6.0f, r102, 0.0f));
        CheckBox2 r132 = this.checkBox;
        if (r132 == null) goto L47;
        boolean r04 = LocaleController.isRTL;
        if (r04 == false) goto L39;
        r2 = 5;
    L39:
        int r73 = r2 | 48;
        if (r04 == false) goto L42;
        float r84 = 0.0f;
    L43:
        if (r04 == false) goto L45;
        r44 = r14 + 37;
    L45:
        r132.setLayoutParams(LayoutHelper.createFrame(24, 24.0f, r73, r84, 32.0f, r44, 0.0f));
        return;
    L42:
        r84 = r14 + 37;
        goto L43
    L47:
        return;
    L33:
        r102 = 0.0f;
        goto L34
    L30:
        r83 = r14 + 8;
        goto L31
    L26:
        r43 = 3;
        goto L27
    L20:
        r9 = r14 + 66;
        goto L21
    L16:
        r5 = 3;
        goto L17
    L12:
        r10 = 30.0f;
        goto L13
    L9:
        r8 = r14 + 66;
        goto L10
    L5:
        r4 = 3;
        goto L6
    }

    public void setCheckDisabled(boolean r1) {
        CheckBoxSquare r0 = this.checkBoxBig;
        if (r0 == null) goto L6;
        r0.setDisabled(r1);
        return;
    }

    public void setChecked(boolean r3, boolean r4) {
        CheckBox2 r0 = this.checkBox;
        int r1 = 0;
        if (r0 != null) goto L5;
        CheckBoxSquare r02 = this.checkBoxBig;
        if (r02 != null) goto L12;
        ImageView r2 = this.checkBox3;
        if (r2 == null) goto L23;
        if (r3 == true) goto L21;
        r1 = 8;
    L21:
        r2.setVisibility(r1);
        return;
    L23:
        return;
    L12:
        if (r02.getVisibility() == 0) goto L14;
        this.checkBoxBig.setVisibility(0);
    L14:
        this.checkBoxBig.setChecked(r3, r4);
        return;
    L5:
        if (r0.getVisibility() == 0) goto L7;
        this.checkBox.setVisibility(0);
    L7:
        this.checkBox.setChecked(r3, r4);
    }

    public void setCloseIcon(View.OnClickListener r11) {
        ImageView r0 = this.closeView;
        if (r11 != null) goto L7;
        if (r0 == null) goto L21;
        removeView(r0);
        this.closeView = null;
        return;
    L21:
        return;
    L7:
        if (r0 != null) goto L19;
        ImageView r02 = new ImageView(getContext());
        this.closeView = r02;
        r02.setScaleType(ImageView.ScaleType.CENTER);
        ScaleStateListAnimator.apply(this.closeView);
        this.closeView.setImageResource(R.drawable.ic_close_white);
        this.closeView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
        int r2 = 5;
        this.closeView.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector, this.resourcesProvider), 5));
        ImageView r03 = this.closeView;
        boolean r1 = LocaleController.isRTL;
        if (r1 == false) goto L11;
        r2 = 3;
    L11:
        int r5 = r2 | 16;
        if (r1 == false) goto L14;
        float r6 = 14.0f;
    L15:
        if (r1 == false) goto L17;
        float r8 = 0.0f;
    L18:
        addView(r03, LayoutHelper.createFrame(30, 30.0f, r5, r6, 0.0f, r8, 0.0f));
        goto L19
    L17:
        r8 = 14.0f;
        goto L18
    L14:
        r6 = 0.0f;
    L19:
        this.closeView.setOnClickListener(r11);
    }

    public void setCurrentId(int r1) {
        this.currentId = r1;
    }

    public void setData(Object r8, CharSequence r9, CharSequence r10, int r11) {
        setData(r8, null, r9, r10, r11, false);
    }

    public void setData(Object r8, CharSequence r9, CharSequence r10, int r11, boolean r12) {
        setData(r8, null, r9, r10, r11, r12);
    }

    public void setData(Object r2, TLRPC.EncryptedChat r3, CharSequence r4, CharSequence r5, int r6, boolean r7) {
        if (r2 != null) goto L8;
        if (r4 != null) goto L8;
        if (r5 != null) goto L8;
        this.currentStatus = null;
        this.currentName = null;
        this.storiable = false;
        this.currentObject = null;
        this.nameTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.statusTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.avatarImageView.setImageDrawable(null);
        return;
    L8:
        this.encryptedChat = r3;
        this.currentStatus = r5;
        if (r4 != null) goto L16;
    L13:
        this.currentName = r4;
        this.storiable = !(r2 instanceof String);
        this.currentObject = r2;
        this.currentDrawable = r6;
        this.needDivider = r7;
        setWillNotDraw(!r7);
        update(0);
        return;
    L16:
        SimpleTextView r32 = this.nameTextView;     // Catch: Exception -> L15
        if (r32 == null) goto L13;
        r4 = Emoji.replaceEmoji(r4, r32.getPaint().getFontMetricsInt(), false);     // Catch: Exception -> L15
        goto L13
    }

    public void setException(NotificationsSettingsActivity.NotificationException r9, CharSequence r10, boolean r11) {
        if (r9.story == false) goto L13;
        int r0 = r9.notify;
        if (r0 <= 0) goto L7;
    L10:
        if (r0 > 0) goto L12;
        String r02 = LocaleController.getString(R.string.NotificationEnabled);
    L9:
        String r5 = r02;
        if (DialogObject.isEncryptedDialog(r9.did) == false) goto L57;
        TLRPC.EncryptedChat r3 = MessagesController.getInstance(this.currentAccount).getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r9.did)));
        if (r3 == null) goto L67;
        TLRPC.User r2 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r3.user_id));
        if (r2 == null) goto L68;
        setData(r2, r3, r10, r5, 0, false);
        return;
    L68:
        return;
    L67:
        return;
    L57:
        boolean r8 = DialogObject.isUserDialog(r9.did);
        int r102 = this.currentAccount;
        if (r8 == false) goto L63;
        TLRPC.User r22 = MessagesController.getInstance(r102).getUser(Long.valueOf(r9.did));
        if (r22 == null) goto L69;
        setData(r22, null, r10, r5, 0, r11);
        return;
    L69:
        return;
    L63:
        TLRPC.Chat r23 = MessagesController.getInstance(r102).getChat(Long.valueOf(-r9.did));
        if (r23 == null) goto L70;
        setData(r23, null, r10, r5, 0, r11);
        return;
    L70:
        return;
    L12:
        r02 = LocaleController.getString(R.string.NotificationDisabled);
        goto L9
    L7:
        if (r9.auto == false) goto L10;
        r02 = LocaleController.getString(R.string.NotificationEnabledAutomatically);
        goto L9
    L13:
        boolean r03 = r9.hasCustom;
        int r1 = r9.notify;
        int r24 = r9.muteUntil;
        boolean r4 = false;
        if (r1 != 3) goto L33;
        if (r24 == Integer.MAX_VALUE) goto L33;
        int r25 = r24 - ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        if (r25 > 0) goto L23;
        if (r03 == false) goto L21;
        r02 = LocaleController.getString(R.string.NotificationsCustom);
    L44:
        if (r02 != null) goto L47;
        r02 = LocaleController.getString(R.string.NotificationsOff);
    L47:
        if (r9.auto == false) goto L9;
        r02 = r02 + ", Auto";
        goto L9
    L21:
        r02 = LocaleController.getString(R.string.NotificationsUnmuted);
        goto L44
    L23:
        if (r25 >= 3600) goto L26;
        r02 = LocaleController.formatString("WillUnmuteIn", R.string.WillUnmuteIn, new Object[]{LocaleController.formatPluralString("Minutes", r25 / 60, new Object[0])});
        goto L44
    L26:
        if (r25 >= 86400) goto L29;
        r02 = LocaleController.formatString("WillUnmuteIn", R.string.WillUnmuteIn, new Object[]{LocaleController.formatPluralString("Hours", (int) Math.ceil((r25 / 60.0f) / 60.0f), new Object[0])});
        goto L44
    L29:
        if (r25 >= 31536000) goto L31;
        r02 = LocaleController.formatString("WillUnmuteIn", R.string.WillUnmuteIn, new Object[]{LocaleController.formatPluralString("Days", (int) Math.ceil(((r25 / 60.0f) / 60.0f) / 24.0f), new Object[0])});
        goto L44
    L31:
        r02 = null;
    L33:
        if (r1 != 0) goto L35;
    L34:
        r4 = true;
    L37:
        if (r4 == false) goto L40;
        if (r03 == false) goto L40;
        r02 = LocaleController.getString(R.string.NotificationsCustom);
    L40:
        if (r4 == false) goto L42;
        int r04 = R.string.NotificationsUnmuted;
    L43:
        r02 = LocaleController.getString(r04);
        goto L44
    L42:
        r04 = R.string.NotificationsMuted;
        goto L43
    L35:
        if (r1 != 1) goto L37;
        goto L37
    }

    public void setFromUItem(int r12, UItem r13, boolean r14) {
        Object r1 = r13.chatType;
        if (r1 == null) goto L6;
        setData(r1, r13.text, null, 0, r14);
        return;
    L6:
        long r132 = r13.dialogId;
        if (r132 <= 0) goto L23;
        TLRPC.User r6 = MessagesController.getInstance(r12).getUser(Long.valueOf(r132));
        String r11 = UserObject.getPublicUsername(r6);
        if (r6 != null) goto L11;
        return;
    L11:
        if (TextUtils.isEmpty(r11) == true) goto L15;
        CharSequence r112 = "@" + r11;
    L21:
        setData(r6, null, r112, 0, r14);
        return;
    L15:
        if (r6.bot == false) goto L18;
        r112 = LocaleController.getString(R.string.Bot);
        goto L21
    L18:
        if (r6.contact == false) goto L20;
        r112 = LocaleController.getString(R.string.FilterContact);
        goto L21
    L20:
        r112 = LocaleController.getString(R.string.FilterNonContact);
        goto L21
    L23:
        TLRPC.Chat r62 = MessagesController.getInstance(r12).getChat(Long.valueOf(-r132));
        if (r62 != null) goto L26;
        return;
    L26:
        if (r62.participants_count == 0) goto L33;
        boolean r113 = ChatObject.isChannelAndNotMegaGroup(r62);
        int r122 = r62.participants_count;
        if (r113 == false) goto L31;
        CharSequence r114 = LocaleController.formatPluralStringComma("Subscribers", r122);
    L46:
        setData(r62, null, r114, 0, r14);
        return;
    L31:
        r114 = LocaleController.formatPluralStringComma("Members", r122);
        goto L46
    L33:
        if (ChatObject.isPublic(r62) == true) goto L41;
        if (ChatObject.isChannel(r62) == true) goto L37;
    L39:
        r114 = LocaleController.getString(R.string.MegaPrivate);
        goto L46
    L37:
        if (r62.megagroup == true) goto L39;
        r114 = LocaleController.getString(R.string.ChannelPrivate);
        goto L46
    L41:
        if (ChatObject.isChannel(r62) == true) goto L43;
    L45:
        r114 = LocaleController.getString(R.string.MegaPublic);
        goto L46
    L43:
        if (r62.megagroup == true) goto L45;
        r114 = LocaleController.getString(R.string.ChannelPublic);
        goto L46
    }

    public void setMutual(boolean r1) {
        this.mutual = r1;
    }

    public void setNameTypeface(Typeface r1) {
        this.nameTextView.setTypeface(r1);
    }

    public void setQuery(String r1) {
        this.query = r1;
        update(0);
    }

    public void setRightPadding(int r4, boolean r5, boolean r6) {
        if (r4 <= 0) goto L5;
        r4 = r4 + AndroidUtilities.dp(6.0f);
    L5:
        if (r5 == false) goto L14;
        SimpleTextView r52 = this.nameTextView;
        boolean r1 = LocaleController.isRTL;
        if (r1 == false) goto L9;
        int r2 = r4;
    L10:
        if (r1 == true) goto L12;
        int r12 = r4;
    L13:
        r52.setPadding(r2, 0, r12, 0);
        goto L14
    L12:
        r12 = 0;
        goto L13
    L9:
        r2 = 0;
    L14:
        if (r6 == false) goto L24;
        SimpleTextView r3 = this.statusTextView;
        boolean r53 = LocaleController.isRTL;
        if (r53 == false) goto L18;
        int r62 = r4;
    L19:
        if (r53 == false) goto L22;
        r4 = 0;
    L22:
        r3.setPadding(r62, 0, r4, 0);
        return;
    L18:
        r62 = 0;
        goto L19
    }

    public void setSelfAsSavedMessages(boolean r1) {
        this.selfAsSavedMessages = r1;
    }

    public void update(int r20) {
        this.dialogId = 0;
        this.isCommunity = false;
        Object r4 = this.currentObject;
        Drawable r6 = null;
        if ((r4 instanceof TLRPC.User) == false) goto L10;
        TLRPC.User r42 = (TLRPC.User) r4;
        TLRPC.UserProfilePhoto r5 = r42.photo;
        if (r5 == null) goto L7;
        TLRPC.FileLocation r52 = r5.photo_small;
    L8:
        this.dialogId = r42.id;
        TLRPC.FileLocation r7 = r52;
        TLRPC.Chat r53 = null;
    L18:
        if (r20 != 0) goto L20;
        String r11 = null;
    L64:
        boolean r10 = this.currentObject instanceof String;
        SimpleTextView r12 = this.nameTextView;
        int r15 = 8;
        if (r10 == false) goto L123;
        ((FrameLayout.LayoutParams) r12.getLayoutParams()).topMargin = AndroidUtilities.dp(19.0f);
        String r102 = (String) this.currentObject;
        r102.getClass();
        long r16 = 0;
        char r18 = 65535;
        switch(r102.hashCode()) {
            case -1716307998: goto L107;
            case -1237460524: goto L103;
            case -1197490811: goto L98;
            case -567451565: goto L94;
            case -268161860: goto L90;
            case 3029900: goto L86;
            case 3496342: goto L82;
            case 104264043: goto L78;
            case 151051367: goto L74;
            case 1432626128: goto L70;
            default: goto L110;
        };
    L110:
        switch(r18) {
            case 0: goto L121;
            case 1: goto L120;
            case 2: goto L119;
            case 3: goto L118;
            case 4: goto L117;
            case 5: goto L116;
            case 6: goto L115;
            case 7: goto L114;
            case 8: goto L113;
            case 9: goto L112;
            default: goto L122;
        };
    L112:
        this.avatarDrawable.setAvatarType(7);
        goto L122
    L113:
        this.avatarDrawable.setAvatarType(23);
        goto L122
    L114:
        this.avatarDrawable.setAvatarType(9);
        goto L122
    L115:
        this.avatarDrawable.setAvatarType(10);
        goto L122
    L116:
        this.avatarDrawable.setAvatarType(8);
        goto L122
    L117:
        this.avatarDrawable.setAvatarType(24);
        goto L122
    L118:
        this.avatarDrawable.setAvatarType(4);
        goto L122
    L119:
        this.avatarDrawable.setAvatarType(5);
        goto L122
    L120:
        this.avatarDrawable.setAvatarType(6);
        goto L122
    L121:
        this.avatarDrawable.setAvatarType(11);
    L122:
        this.avatarImageView.setImage(null, "50_50", this.avatarDrawable);
        this.currentStatus = _UrlKt.FRAGMENT_ENCODE_SET;
    L141:
        CharSequence r1 = this.currentName;
        if (r1 == null) goto L149;
        this.lastName = null;
        String r2 = this.query;
        if (r2 == null) goto L146;
        r1 = AndroidUtilities.highlightText(r1, r2, this.resourcesProvider);
    L146:
        if (r1 != null) goto L284;
    L148:
        this.nameTextView.setText(r1);
    L165:
        if (r42 == null) goto L167;
        long r13 = DialogObject.getBotVerificationIcon(r42);
    L170:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r8 = this.botVerification;
        if (r13 != r16) goto L173;
        r8.set(null, false);
        this.nameTextView.setLeftDrawable(null);
    L174:
        ArrayList r14 = new ArrayList();
        BadgeDTO r22 = BadgesController.INSTANCE.getBadge(r42);
        if (r22 == null) goto L177;
        this.emojiStatus2.set(r22.getDocumentId(), false);
        this.emojiStatus2.setParticles(true, false);
        this.emojiStatus2.setColor(Integer.valueOf(Theme.getColor(Theme.key_chats_verifiedBackground, this.resourcesProvider)));
        r14.add(this.emojiStatus2);
    L177:
        if (r42 != null) goto L179;
    L184:
        if (r42 == null) goto L195;
        if (MessagesController.getInstance(this.currentAccount).isPremiumUser(r42) == false) goto L195;
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == true) goto L195;
        if (this.premiumDrawable != null) goto L192;
        this.premiumDrawable = getContext().getResources().getDrawable(R.drawable.msg_premium_liststar).mutate();
        AnonymousClass3 r3 = new AnonymousClass3(this, this.premiumDrawable, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f));
        this.premiumDrawable = r3;
        r3.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_verifiedBackground, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
    L192:
        if (r22 != null) goto L195;
        r14.add(this.premiumDrawable);
    L195:
        if (r14.isEmpty() == true) goto L197;
        Drawable r23 = (Drawable) r14.get(0);
    L198:
        boolean r82 = true;
        if (r14.size() <= 1) goto L201;
        r6 = (Drawable) r14.get(1);
    L201:
        this.nameTextView.setRightDrawable(r23);
        this.nameTextView.setRightDrawable2(r6);
        this.nameTextView.setRightDrawableTopPadding(-AndroidUtilities.dp(0.5f));
        if (this.currentStatus == null) goto L207;
        this.statusTextView.setTextColor(this.statusColor);
        CharSequence r17 = this.currentStatus;
        String r24 = this.query;
        if (r24 == null) goto L206;
        r17 = AndroidUtilities.highlightText(r17, r24, this.resourcesProvider);
    L206:
        this.statusTextView.setText(r17);
    L231:
        if (this.imageView.getVisibility() != 0) goto L235;
        if (this.currentDrawable != 0) goto L235;
    L238:
        ImageView r19 = this.imageView;
        if (this.currentDrawable != 0) goto L241;
        int r25 = 8;
    L242:
        r19.setVisibility(r25);
        this.imageView.setImageResource(this.currentDrawable);
    L243:
        this.lastAvatar = r7;
        if (r42 == null) goto L246;
        this.avatarImageView.setForUserOrChat(r42, this.avatarDrawable);
    L250:
        boolean r110 = this.isCommunity;
        boolean r26 = this.callCellStyle;
        float r32 = 46.0f;
        if (r110 == false) goto L255;
        if (r26 == false) goto L254;
        r32 = 44.0f;
    L254:
        int r111 = ExteraConfig.getAvatarCorners(r32, false, AvatarCornerType.COMMUNITY);
    L264:
        setAvatarRadius(r111);
        this.nameTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        ImageView r112 = this.mutualContactView;
        if (this.mutual == false) goto L267;
        r15 = 0;
    L267:
        r112.setVisibility(r15);
        if (this.mutual == false) goto L286;
        SimpleTextView r0 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L272;
        int r113 = AndroidUtilities.dp(33.0f);
    L274:
        if (LocaleController.isRTL == true) goto L277;
        int r27 = AndroidUtilities.dp(33.0f);
    L278:
        r0.setPadding(r113, 0, r27, 0);
        return;
    L277:
        r27 = 0;
        goto L278
    L272:
        r113 = 0;
        goto L274
    L286:
        return;
    L255:
        if (r26 == false) goto L257;
        r32 = 44.0f;
    L257:
        if (r53 != null) goto L259;
    L262:
        boolean r9 = false;
    L263:
        r111 = ExteraConfig.getAvatarCorners(r32, false, r9);
        goto L264
    L259:
        if (r53.forum == false) goto L262;
        r9 = r82;
        goto L263
    L246:
        BackupImageView r114 = this.avatarImageView;
        if (r53 == null) goto L249;
        r114.setForUserOrChat(r53, this.avatarDrawable);
        goto L250
    L249:
        r114.setImageDrawable(this.avatarDrawable);
        goto L250
    L241:
        r25 = 0;
    L235:
        if (this.imageView.getVisibility() != 8) goto L243;
        if (this.currentDrawable == 0) goto L243;
    L207:
        if (r42 == null) goto L231;
        if (r42.bot == false) goto L220;
        this.statusTextView.setTextColor(this.statusColor);
        if (r42.bot_chat_history == true) goto L218;
        TextView r115 = this.adminTextView;
        if (r115 != null) goto L215;
    L217:
        this.statusTextView.setText(LocaleController.getString(R.string.BotStatusCantRead));
        goto L231
    L215:
        if (r115.getVisibility() != 0) goto L217;
    L218:
        this.statusTextView.setText(LocaleController.getString(R.string.BotStatusRead));
        goto L231
    L220:
        if (r42.id == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L229;
        TLRPC.UserStatus r116 = r42.status;
        if (r116 == null) goto L226;
        if (r116.expires > ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L229;
    L226:
        if (MessagesController.getInstance(this.currentAccount).onlinePrivacy.containsKey(Long.valueOf(r42.id)) == true) goto L229;
        this.statusTextView.setTextColor(this.statusColor);
        this.statusTextView.setText(LocaleController.formatUserStatus(this.currentAccount, r42));
    L229:
        this.statusTextView.setTextColor(this.statusOnlineColor);
        this.statusTextView.setText(LocaleController.getString(R.string.Online));
        goto L231
    L197:
        r23 = null;
        goto L198
    L179:
        if (DialogObject.getEmojiStatusDocumentId(r42.emoji_status) == r16) goto L184;
        this.emojiStatus.set(DialogObject.getEmojiStatusDocumentId(r42.emoji_status), false);
        this.emojiStatus.setColor(Integer.valueOf(Theme.getColor(Theme.key_chats_verifiedBackground, this.resourcesProvider)));
        r23 = this.emojiStatus;
        if (r14.isEmpty() == true) goto L183;
        r6 = (Drawable) r14.get(0);
    L183:
        r82 = true;
        goto L201
    L173:
        r8.set(r13, false);
        this.botVerification.setColor(Integer.valueOf(Theme.getColor(Theme.key_chats_verifiedBackground, this.resourcesProvider)));
        this.nameTextView.setLeftDrawable(this.botVerification);
        goto L174
    L167:
        if (r53 == null) goto L169;
        r13 = DialogObject.getBotVerificationIcon(r53);
        goto L170
    L169:
        r13 = r16;
        goto L170
    L284:
        r1 = Emoji.replaceEmoji(r1, this.nameTextView.getPaint().getFontMetricsInt(), false);     // Catch: Exception -> L280
        goto L148
    L149:
        if (r42 == null) goto L154;
        if (r11 != null) goto L152;
        String r117 = UserObject.getUserName(r42);
    L153:
        this.lastName = r117;
    L159:
        CharSequence r118 = this.lastName;
        String r28 = this.query;
        if (r28 == null) goto L162;
        r118 = AndroidUtilities.highlightText(r118, r28, this.resourcesProvider);
    L162:
        if (r118 != null) goto L282;
    L164:
        this.nameTextView.setText(r118);
        goto L165
    L282:
        r118 = Emoji.replaceEmoji(r118, this.nameTextView.getPaint().getFontMetricsInt(), false);     // Catch: Exception -> L281
        goto L164
    L152:
        r117 = AndroidUtilities.removeRTL(AndroidUtilities.removeDiacritics(r11));
        goto L153
    L154:
        if (r53 == null) goto L158;
        if (r11 != null) goto L157;
        r11 = r53.title;
    L157:
        this.lastName = AndroidUtilities.removeRTL(AndroidUtilities.removeDiacritics(r11));
        goto L159
    L158:
        this.lastName = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L159
    L70:
        if (r102.equals("channels") == false) goto L110;
        r18 = '\t';
        goto L110
    L74:
        if (r102.equals("existing_chats") == false) goto L110;
        r18 = '\b';
        goto L110
    L78:
        if (r102.equals("muted") == false) goto L110;
        r18 = 7;
        goto L110
    L82:
        if (r102.equals("read") == false) goto L110;
        r18 = 6;
        goto L110
    L86:
        if (r102.equals("bots") == false) goto L110;
        r18 = 5;
        goto L110
    L90:
        if (r102.equals("new_chats") == false) goto L110;
        r18 = 4;
        goto L110
    L94:
        if (r102.equals("contacts") == false) goto L110;
        char r103 = 3;
    L101:
        r18 = r103;
        goto L110
    L98:
        if (r102.equals("non_contacts") == false) goto L110;
        r103 = 2;
        goto L101
    L103:
        if (r102.equals("groups") == false) goto L110;
        r18 = 1;
        goto L110
    L107:
        if (r102.equals("archived") == false) goto L110;
        r18 = 0;
        goto L110
    L123:
        r16 = 0;
        ((FrameLayout.LayoutParams) r12.getLayoutParams()).topMargin = AndroidUtilities.dp(10.0f);
        if (r42 != null) goto L126;
        if (r53 == null) goto L137;
        this.avatarDrawable.setInfo(this.currentAccount, r53);
        goto L141
    L137:
        CharSequence r119 = this.currentName;
        AvatarDrawable r29 = this.avatarDrawable;
        if (r119 == null) goto L140;
        r29.setInfo(this.currentId, r119.toString(), null);
        goto L141
    L140:
        r29.setInfo(this.currentId, "#", null);
        goto L141
    L126:
        if (this.selfAsSavedMessages == true) goto L128;
    L131:
        this.avatarDrawable.setInfo(this.currentAccount, r42);
        TLRPC.UserStatus r120 = r42.status;
        if (r120 == null) goto L134;
        this.lastStatus = r120.expires;
        goto L141
    L134:
        this.lastStatus = 0;
        goto L141
    L128:
        if (UserObject.isUserSelf(r42) == false) goto L131;
        this.nameTextView.setText(LocaleController.getString(R.string.SavedMessages), true);
        this.statusTextView.setText(null);
        this.avatarDrawable.setAvatarType(1);
        this.avatarImageView.setImage(null, "50_50", this.avatarDrawable, r42);
        ((FrameLayout.LayoutParams) this.nameTextView.getLayoutParams()).topMargin = AndroidUtilities.dp(19.0f);
        return;
    L20:
        if ((r20 & MessagesController.UPDATE_MASK_AVATAR) == 0) goto L32;
        TLRPC.FileLocation r104 = this.lastAvatar;
        if (r104 == null) goto L24;
        if (r7 != null) goto L24;
    L31:
        boolean r105 = true;
    L33:
        if (r42 == null) goto L44;
        if (r105 == true) goto L44;
        if ((r20 & MessagesController.UPDATE_MASK_STATUS) == 0) goto L44;
        TLRPC.UserStatus r1110 = r42.status;
        if (r1110 == null) goto L40;
        int r1111 = r1110.expires;
    L42:
        if (r1111 == this.lastStatus) goto L44;
        r105 = true;
        goto L44
    L40:
        r1111 = 0;
    L44:
        if (r105 == false) goto L46;
    L60:
        r11 = null;
    L61:
        if (r105 == true) goto L64;
        return;
    L46:
        if (this.currentName != null) goto L60;
        if (this.lastName == null) goto L60;
        if ((r20 & MessagesController.UPDATE_MASK_NAME) == 0) goto L60;
        if (r42 == null) goto L53;
        r11 = AndroidUtilities.removeRTL(AndroidUtilities.removeDiacritics(UserObject.getUserName(r42)));
    L58:
        if (r11.equals(this.lastName) == true) goto L61;
        r105 = true;
        goto L61
    L53:
        if (r53 != null) goto L55;
        String r1112 = _UrlKt.FRAGMENT_ENCODE_SET;
    L56:
        r11 = AndroidUtilities.removeRTL(AndroidUtilities.removeDiacritics(r1112));
        goto L58
    L55:
        r1112 = r53.title;
    L24:
        if (r104 != null) goto L26;
        if (r7 != null) goto L31;
    L26:
        if (r104 == null) goto L32;
        if (r104.volume_id != r7.volume_id) goto L31;
        if (r104.local_id != r7.local_id) goto L31;
    L32:
        r105 = false;
        goto L33
    L7:
        r52 = null;
        goto L8
    L10:
        if ((r4 instanceof TLRPC.Chat) == false) goto L16;
        TLRPC.Chat r43 = (TLRPC.Chat) r4;
        TLRPC.ChatPhoto r54 = r43.photo;
        if (r54 == null) goto L14;
        TLRPC.FileLocation r55 = r54.photo_small;
    L15:
        this.dialogId = r43.id;
        this.isCommunity = ChatObject.isCommunity(r43);
        r7 = r55;
        r53 = r43;
        r42 = null;
        goto L18
    L14:
        r55 = null;
        goto L15
    L16:
        r42 = null;
        r53 = null;
        r7 = null;
        goto L18
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
    }
}
