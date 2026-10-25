package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.exteragram.messenger.ExteraConfig;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
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
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.CheckBoxSquare;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class UserCell2 extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImageView;
    private CheckBox checkBox;
    private CheckBoxSquare checkBoxBig;
    private int currentAccount;
    private int currentDrawable;
    private int currentId;
    private CharSequence currentName;
    private TLObject currentObject;
    private CharSequence currentStatus;
    private ImageView imageView;
    private TLRPC.FileLocation lastAvatar;
    private String lastName;
    private int lastStatus;
    private SimpleTextView nameTextView;
    private Theme.ResourcesProvider resourcesProvider;
    private int statusColor;
    private int statusOnlineColor;
    private SimpleTextView statusTextView;

    /* renamed from: org.telegram.ui.Cells.UserCell2$1, reason: invalid class name */
    public class AnonymousClass1 extends SimpleTextView {
        final /* synthetic */ UserCell2 this$0;

        public AnonymousClass1(UserCell2 r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.ActionBar.SimpleTextView
        public boolean setText(CharSequence r3) {
            return super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false));
        }
    }

    public UserCell2(Context r2, int r3, int r4) {
        this(r2, r3, r4, null);
    }

    public UserCell2(Context r20, int r21, int r22, Theme.ResourcesProvider r23) {
        super(r20);
        this.currentAccount = UserConfig.selectedAccount;
        this.resourcesProvider = r23;
        this.statusColor = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r23);
        this.statusOnlineColor = Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, r23);
        this.avatarDrawable = new AvatarDrawable();
        BackupImageView r4 = new BackupImageView(r20);
        this.avatarImageView = r4;
        r4.setRoundRadius(ExteraConfig.getAvatarCorners(48.0f));
        View r42 = this.avatarImageView;
        boolean r5 = LocaleController.isRTL;
        int r6 = 3;
        if (r5 == false) goto L5;
        int r8 = 5;
    L6:
        int r11 = r8 | 48;
        float r82 = 0.0f;
        if (r5 == false) goto L9;
        float r12 = 0.0f;
    L10:
        if (r5 == false) goto L12;
        float r14 = r21 + 7;
    L13:
        addView(r42, LayoutHelper.createFrame(48, 48.0f, r11, r12, 11.0f, r14, 0.0f));
        AnonymousClass1 r43 = new AnonymousClass1(this, r20);
        this.nameTextView = r43;
        NotificationCenter.listenEmojiLoading(r43);
        this.nameTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r23));
        this.nameTextView.setTextSize(17);
        this.nameTextView.setTypeface(AndroidUtilities.bold());
        SimpleTextView r44 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L16;
        int r52 = 5;
    L17:
        r44.setGravity(r52 | 48);
        View r45 = this.nameTextView;
        boolean r53 = LocaleController.isRTL;
        if (r53 == false) goto L20;
        int r9 = 5;
    L21:
        int r122 = r9 | 48;
        int r92 = 18;
        if (r53 == false) goto L28;
        if (r22 != 2) goto L25;
        int r13 = 18;
    L26:
        int r132 = r13 + 28;
    L27:
        float r133 = r132;
        if (r53 == false) goto L32;
        float r54 = r21 + 68;
    L36:
        addView(r45, LayoutHelper.createFrame(-1, 20.0f, r122, r133, 14.5f, r54, 0.0f));
        SimpleTextView r46 = new SimpleTextView(r20);
        this.statusTextView = r46;
        r46.setTextSize(14);
        SimpleTextView r47 = this.statusTextView;
        if (LocaleController.isRTL == false) goto L39;
        int r10 = 5;
    L40:
        r47.setGravity(r10 | 48);
        View r48 = this.statusTextView;
        boolean r102 = LocaleController.isRTL;
        if (r102 == false) goto L43;
        int r112 = 5;
    L44:
        int r142 = r112 | 48;
        float r113 = 28.0f;
        if (r102 == false) goto L47;
        float r15 = 28.0f;
    L48:
        if (r102 == false) goto L50;
        r113 = r21 + 68;
    L50:
        addView(r48, LayoutHelper.createFrame(-1, 20.0f, r142, r15, 37.5f, r113, 0.0f));
        ImageView r49 = new ImageView(r20);
        this.imageView = r49;
        r49.setScaleType(ImageView.ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon, r23), PorterDuff.Mode.MULTIPLY));
        this.imageView.setVisibility(8);
        View r410 = this.imageView;
        boolean r103 = LocaleController.isRTL;
        if (r103 == false) goto L53;
        int r114 = 5;
    L54:
        int r143 = r114 | 16;
        if (r103 == false) goto L57;
        float r152 = 0.0f;
    L58:
        if (r103 == false) goto L60;
        float r17 = 16.0f;
    L61:
        addView(r410, LayoutHelper.createFrame(-2, -2.0f, r143, r152, 0.0f, r17, 0.0f));
        if (r22 != 2) goto L77;
        CheckBoxSquare r2 = new CheckBoxSquare(r20, false, r23);
        this.checkBoxBig = r2;
        boolean r1 = LocaleController.isRTL;
        if (r1 == true) goto L67;
        r6 = 5;
    L67:
        int r115 = r6 | 16;
        if (r1 == false) goto L70;
        float r123 = 19.0f;
    L71:
        if (r1 == false) goto L73;
        float r144 = 0.0f;
    L74:
        addView(r2, LayoutHelper.createFrame(18, 18.0f, r115, r123, 0.0f, r144, 0.0f));
        return;
    L73:
        r144 = 19.0f;
        goto L74
    L70:
        r123 = 0.0f;
        goto L71
    L77:
        if (r22 != 1) goto L89;
        CheckBox r24 = new CheckBox(r20, R.drawable.round_check2);
        this.checkBox = r24;
        r24.setVisibility(4);
        this.checkBox.setColor(Theme.getColor(Theme.key_checkbox, r23), Theme.getColor(Theme.key_checkboxCheck, r23));
        View r16 = this.checkBox;
        boolean r25 = LocaleController.isRTL;
        if (r25 == false) goto L81;
        r6 = 5;
    L81:
        int r116 = r6 | 48;
        if (r25 == false) goto L84;
        float r124 = 0.0f;
    L85:
        if (r25 == false) goto L87;
        r82 = r21 + 37;
    L87:
        addView(r16, LayoutHelper.createFrame(22, 22.0f, r116, r124, 41.0f, r82, 0.0f));
        return;
    L84:
        r124 = r21 + 37;
        goto L85
    L89:
        return;
    L60:
        r17 = 0.0f;
        goto L61
    L57:
        r152 = 16.0f;
        goto L58
    L53:
        r114 = 3;
        goto L54
    L47:
        r15 = r21 + 68;
        goto L48
    L43:
        r112 = 3;
        goto L44
    L39:
        r10 = 3;
        goto L40
    L32:
        if (r22 == 2) goto L35;
        r92 = 0;
    L35:
        r54 = r92 + 28;
        goto L36
    L25:
        r13 = 0;
        goto L26
    L28:
        r132 = r21 + 68;
        goto L27
    L20:
        r9 = 3;
        goto L21
    L16:
        r52 = 3;
        goto L17
    L12:
        r14 = 0.0f;
        goto L13
    L9:
        r12 = r21 + 7;
        goto L10
    L5:
        r8 = 3;
        goto L6
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

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(70.0f), TLObject.FLAG_30));
    }

    public void setCheckDisabled(boolean r1) {
        CheckBoxSquare r0 = this.checkBoxBig;
        if (r0 == null) goto L6;
        r0.setDisabled(r1);
        return;
    }

    public void setCurrentId(int r1) {
        this.currentId = r1;
    }

    public void setData(TLObject r1, CharSequence r2, CharSequence r3, int r4) {
        if (r1 != null) goto L7;
        if (r2 != null) goto L7;
        if (r3 != null) goto L7;
        this.currentStatus = null;
        this.currentName = null;
        this.currentObject = null;
        this.nameTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.statusTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.avatarImageView.setImageDrawable(null);
        return;
    L7:
        this.currentStatus = r3;
        this.currentName = r2;
        this.currentObject = r1;
        this.currentDrawable = r4;
        update(0);
    }

    public void setNameTypeface(Typeface r1) {
        this.nameTextView.setTypeface(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [org.telegram.tgnet.TLRPC$FileLocation] */
    public void update(int r12) {
        TLObject r0 = this.currentObject;
        if ((r0 instanceof TLRPC.User) == false) goto L10;
        TLRPC.User r02 = (TLRPC.User) r0;
        TLRPC.UserProfilePhoto r1 = r02.photo;
        if (r1 == null) goto L7;
        ?? r3 = r1.photo_small;
        TLRPC.Chat r13 = null;
    L16:
        boolean r4 = true;
        int r5 = 0;
        if (r12 != 0) goto L19;
        String r122 = null;
    L61:
        this.lastAvatar = r3;
        if (r02 == null) goto L67;
        this.avatarDrawable.setInfo(this.currentAccount, r02);
        TLRPC.UserStatus r32 = r02.status;
        if (r32 == null) goto L66;
        this.lastStatus = r32.expires;
    L73:
        CharSequence r33 = this.currentName;
        if (r33 == null) goto L76;
        this.lastName = null;
        this.nameTextView.setText(r33);
    L85:
        if (this.currentStatus == null) goto L89;
        this.statusTextView.setTextColor(this.statusColor);
        this.statusTextView.setText(this.currentStatus);
        BackupImageView r123 = this.avatarImageView;
        if (r123 == null) goto L132;
        r123.setForUserOrChat(r02, this.avatarDrawable);
    L132:
        BackupImageView r124 = this.avatarImageView;
        if (r13 != null) goto L135;
    L137:
        r4 = false;
    L138:
        r124.setRoundRadius(ExteraConfig.getAvatarCorners(48.0f, false, r4));
        if (this.imageView.getVisibility() != 0) goto L143;
        if (this.currentDrawable != 0) goto L143;
    L146:
        ImageView r125 = this.imageView;
        if (this.currentDrawable != 0) goto L149;
        r5 = 8;
    L149:
        r125.setVisibility(r5);
        this.imageView.setImageResource(this.currentDrawable);
        return;
    L143:
        if (this.imageView.getVisibility() == 8) goto L145;
        return;
    L145:
        if (this.currentDrawable != 0) goto L146;
        return;
    L135:
        if (r13.forum == false) goto L137;
    L89:
        if (r02 != null) goto L91;
        if (r13 == null) goto L131;
        this.statusTextView.setTextColor(this.statusColor);
        if (ChatObject.isChannel(r13) == true) goto L112;
    L120:
        int r126 = r13.participants_count;
        if (r126 == 0) goto L124;
        this.statusTextView.setText(LocaleController.formatPluralString("Members", r126, new Object[0]));
    L130:
        this.avatarImageView.setForUserOrChat(r13, this.avatarDrawable);
        goto L132
    L124:
        if (r13.has_geo == false) goto L126;
        this.statusTextView.setText(LocaleController.getString(R.string.MegaLocation));
        goto L130
    L126:
        boolean r127 = ChatObject.isPublic(r13);
        SimpleTextView r03 = this.statusTextView;
        if (r127 == true) goto L129;
        r03.setText(LocaleController.getString(R.string.MegaPrivate));
        goto L130
    L129:
        r03.setText(LocaleController.getString(R.string.MegaPublic));
        goto L130
    L112:
        if (r13.megagroup == true) goto L120;
        int r128 = r13.participants_count;
        if (r128 == 0) goto L116;
        this.statusTextView.setText(LocaleController.formatPluralString("Subscribers", r128, new Object[0]));
        goto L130
    L116:
        boolean r129 = ChatObject.isPublic(r13);
        SimpleTextView r04 = this.statusTextView;
        if (r129 == true) goto L119;
        r04.setText(LocaleController.getString(R.string.ChannelPrivate));
        goto L130
    L119:
        r04.setText(LocaleController.getString(R.string.ChannelPublic));
        goto L130
    L131:
        this.avatarImageView.setImageDrawable(this.avatarDrawable);
        goto L132
    L91:
        if (r02.bot == false) goto L97;
        this.statusTextView.setTextColor(this.statusColor);
        boolean r1210 = r02.bot_chat_history;
        SimpleTextView r2 = this.statusTextView;
        if (r1210 == false) goto L95;
        r2.setText(LocaleController.getString(R.string.BotStatusRead));
    L107:
        this.avatarImageView.setForUserOrChat(r02, this.avatarDrawable);
        goto L132
    L95:
        r2.setText(LocaleController.getString(R.string.BotStatusCantRead));
        goto L107
    L97:
        if (r02.id == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L106;
        TLRPC.UserStatus r1211 = r02.status;
        if (r1211 == null) goto L103;
        if (r1211.expires > ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L106;
    L103:
        if (MessagesController.getInstance(this.currentAccount).onlinePrivacy.containsKey(Long.valueOf(r02.id)) == true) goto L106;
        this.statusTextView.setTextColor(this.statusColor);
        this.statusTextView.setText(LocaleController.formatUserStatus(this.currentAccount, r02));
    L106:
        this.statusTextView.setTextColor(this.statusOnlineColor);
        this.statusTextView.setText(LocaleController.getString(R.string.Online));
        goto L107
    L76:
        if (r02 == null) goto L80;
        if (r122 != null) goto L79;
        r122 = UserObject.getUserName(r02);
    L79:
        this.lastName = r122;
    L83:
        this.nameTextView.setText(this.lastName);
        goto L85
    L80:
        if (r122 != null) goto L82;
        r122 = r13.title;
    L82:
        this.lastName = r122;
        goto L83
    L66:
        this.lastStatus = 0;
        goto L73
    L67:
        if (r13 == null) goto L69;
        this.avatarDrawable.setInfo(this.currentAccount, r13);
        goto L73
    L69:
        CharSequence r34 = this.currentName;
        AvatarDrawable r6 = this.avatarDrawable;
        if (r34 == null) goto L72;
        r6.setInfo(this.currentId, r34.toString(), null);
        goto L73
    L72:
        r6.setInfo(this.currentId, "#", null);
        goto L73
    L19:
        if ((MessagesController.UPDATE_MASK_AVATAR & r12) == 0) goto L32;
        TLRPC.FileLocation r62 = this.lastAvatar;
        if (r62 == null) goto L23;
        if (r3 != 0) goto L23;
    L31:
        boolean r63 = true;
    L33:
        if (r02 == null) goto L44;
        if (r63 == true) goto L44;
        if ((MessagesController.UPDATE_MASK_STATUS & r12) == 0) goto L44;
        TLRPC.UserStatus r7 = r02.status;
        if (r7 == null) goto L40;
        int r72 = r7.expires;
    L42:
        if (r72 == this.lastStatus) goto L44;
        r63 = true;
        goto L44
    L40:
        r72 = 0;
    L44:
        if (r63 == false) goto L46;
    L57:
        r122 = null;
    L58:
        if (r63 == true) goto L61;
        return;
    L46:
        if (this.currentName != null) goto L57;
        if (this.lastName == null) goto L57;
        if ((r12 & MessagesController.UPDATE_MASK_NAME) == 0) goto L57;
        if (r02 == null) goto L53;
        r122 = UserObject.getUserName(r02);
    L55:
        if (r122.equals(this.lastName) == true) goto L58;
        r63 = true;
        goto L58
    L53:
        r122 = r13.title;
    L23:
        if (r62 != null) goto L25;
        if (r3 != 0) goto L31;
    L25:
        if (r62 == null) goto L32;
        if (r3 == 0) goto L32;
        if (r62.volume_id != r3.volume_id) goto L31;
        if (r62.local_id != r3.local_id) goto L31;
    L32:
        r63 = false;
        goto L33
    L7:
        r13 = null;
    L8:
        r3 = r13;
        goto L16
    L10:
        if ((r0 instanceof TLRPC.Chat) == false) goto L15;
        TLRPC.Chat r05 = (TLRPC.Chat) r0;
        TLRPC.ChatPhoto r14 = r05.photo;
        if (r14 == null) goto L14;
        r3 = r14.photo_small;
        r13 = r05;
        r02 = null;
        goto L16
    L14:
        r13 = r05;
        r02 = null;
        r3 = 0;
        goto L16
    L15:
        r02 = null;
        r13 = null;
        goto L8
    }
}
