package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.exteragram.messenger.AvatarCornerType;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.badges.BadgesController;
import com.google.android.material.timepicker.TimeModel;
import java.util.Locale;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CanvasButton;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.PhotoBubbleClip;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.Text;
import org.telegram.ui.FilterCreateActivity;
import org.telegram.ui.Stories.StoriesUtilities;

/* loaded from: classes3.dex */
public class ProfileSearchCell extends BaseCell implements NotificationCenter.NotificationCenterDelegate, Theme.Colorable {
    CanvasButton actionButton;
    private StaticLayout actionLayout;
    private int actionLeft;
    private TLRPC.TL_sponsoredPeer ad;
    private Paint adBackgroundPaint;
    private final ButtonBounce adBounce;
    private final RectF adBounds;
    private Text adText;
    private boolean allowBotOpenButton;
    private boolean allowEmojiStatus;
    private AvatarDrawable avatarDrawable;
    public ImageReceiver avatarImage;
    public StoriesUtilities.AvatarStoryParams avatarStoryParams;
    private AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable botVerificationDrawable;
    private PhotoBubbleClip bubbleClip;
    private boolean callCellStyle;
    private TLRPC.Chat chat;
    CheckBox2 checkBox;
    private ContactsController.Contact contact;
    private StaticLayout countLayout;
    private int countLeft;
    private int countTop;
    private int countWidth;
    private int currentAccount;
    private CharSequence currentName;
    private boolean customPaints;
    private long dialog_id;
    public boolean dontDrawAvatar;
    private boolean drawCheck;
    private boolean drawCount;
    private boolean drawNameLock;
    private boolean drawPremium;
    private TLRPC.EncryptedChat encryptedChat;
    private boolean[] isOnline;
    private TLRPC.FileLocation lastAvatar;
    private String lastName;
    private int lastStatus;
    private int lastUnreadCount;
    private Drawable lockDrawable;
    private StaticLayout nameLayout;
    private int nameLeft;
    private int nameLockLeft;
    private int nameLockTop;
    private TextPaint namePaint;
    private int nameTop;
    private int nameWidth;
    private Utilities.Callback<TLRPC.User> onOpenButtonClick;
    private Utilities.Callback2<ProfileSearchCell, TLRPC.TL_sponsoredPeer> onSponsoredOptionsClick;
    private boolean openBot;
    private final Paint openButtonBackgroundPaint;
    private final ButtonBounce openButtonBounce;
    private final RectF openButtonRect;
    private Text openButtonText;
    private boolean premiumBlocked;
    private final AnimatedFloat premiumBlockedT;
    private PremiumGradient.PremiumGradientTools premiumGradient;
    private RectF rect;
    private boolean rectangularAvatar;
    private Theme.ResourcesProvider resourcesProvider;
    private boolean savedMessages;
    private boolean showPremiumBlocked;
    private final AnimatedFloat starsBlockedT;
    private long starsPriceBlocked;
    private AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable statusDrawable;
    private StaticLayout statusLayout;
    private int statusLeft;
    private TextPaint statusPaint;
    private CharSequence subLabel;
    private int sublabelOffsetX;
    private int sublabelOffsetY;
    public boolean useSeparator;
    private TLRPC.User user;

    public static /* synthetic */ void $r8$lambda$gUyulQ_GiqCQcaym_VtuKjcDBIg(ProfileSearchCell r0) {
        r0.lambda$buildLayout$0();
    }

    public ProfileSearchCell(Context r2) {
        this(r2, null);
    }

    public ProfileSearchCell(Context r10, Theme.ResourcesProvider r11) {
        super(r10);
        this.currentAccount = UserConfig.selectedAccount;
        this.countTop = AndroidUtilities.dp(19.0f);
        CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.premiumBlockedT = new AnimatedFloat(this, 0, 350, r7);
        this.starsBlockedT = new AnimatedFloat(this, 0, 350, r7);
        this.avatarStoryParams = new StoriesUtilities.AvatarStoryParams(false);
        this.adBounds = new RectF();
        this.adBounce = new ButtonBounce(this);
        this.rect = new RectF();
        this.allowEmojiStatus = true;
        this.openButtonBounce = new ButtonBounce(this);
        this.openButtonBackgroundPaint = new Paint(1);
        this.openButtonRect = new RectF();
        this.resourcesProvider = r11;
        this.avatarImage = new ImageReceiver(this);
        setAvatarRadius(ExteraConfig.getAvatarCorners(46.0f));
        this.avatarDrawable = new AvatarDrawable();
        CheckBox2 r9 = new CheckBox2(r10, 21, r11);
        this.checkBox = r9;
        r9.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
        this.checkBox.setDrawUnchecked(false);
        this.checkBox.setDrawBackgroundAsArc(3);
        addView(this.checkBox);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r92 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this, AndroidUtilities.dp(20.0f));
        this.botVerificationDrawable = r92;
        r92.setCallback(this);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r93 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this, AndroidUtilities.dp(20.0f));
        this.statusDrawable = r93;
        r93.setCallback(this);
    }

    private /* synthetic */ void lambda$buildLayout$0() {
        if ((getParent() instanceof RecyclerListView) == false) goto L6;
        RecyclerListView r0 = (RecyclerListView) getParent();
        r0.getOnItemClickListener().onItemClick(this, r0.getChildAdapterPosition(this));
        return;
    L6:
        callOnClick();
    }

    private void setAvatarRadius(int r3) {
        this.avatarStoryParams.avatarRadius = r3;
        this.avatarImage.setRoundRadius(r3);
    }

    public ProfileSearchCell allowBotOpenButton(boolean r1, Utilities.Callback<TLRPC.User> r2) {
        this.allowBotOpenButton = r1;
        this.onOpenButtonClick = r2;
        return this;
    }

    public void buildLayout() {
        this.drawNameLock = false;
        this.drawCheck = false;
        this.drawPremium = false;
        float r7 = 11.0f;
        if (this.encryptedChat == null) goto L9;
        this.drawNameLock = true;
        this.dialog_id = DialogObject.makeEncryptedDialogId(r1.id);
        if (LocaleController.isRTL == true) goto L7;
        this.nameLockLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
        this.nameLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline + 4) + Theme.dialogs_lockDrawable.getIntrinsicWidth();
    L8:
        this.nameLockTop = AndroidUtilities.dp(22.0f);
        updateStatus(false, null, null, null, false);
    L44:
        if (LocaleController.isRTL == true) goto L46;
        this.statusLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
    L48:
        if (this.ad != null) goto L50;
    L55:
        CharSequence r1 = this.currentName;
        CharSequence r12 = r1;
        if (r1 != null) goto L59;
        r12 = null;
    L59:
        TLRPC.Chat r9 = this.chat;
        CharSequence r13 = r12;
        if (r9 == null) goto L70;
        r13 = r12;
        if (r9.monoforum == false) goto L68;
        TLRPC.Chat r92 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.chat.linked_monoforum_id));
        r13 = r12;
        if (r92 == null) goto L66;
        SpannableStringBuilder r14 = new SpannableStringBuilder(AndroidUtilities.escape(r92.title));
        r14.append(" ");
        int r93 = r14.length();
        r14.append(LocaleController.getString(R.string.MonoforumSpan));
        r14.setSpan(new FilterCreateActivity.TextSpan(LocaleController.getString(R.string.MonoforumSpan), 9.33f, Theme.key_windowBackgroundWhiteGrayText, this.resourcesProvider), r93, r14.length(), 33);
        r13 = r14;
    L74:
        CharSequence r15 = AndroidUtilities.replaceNewLines(r13);
        if (TextUtils.isEmpty(r15) == false) goto L86;
        if (ExteraConfig.getHidePhoneNumber() == false) goto L79;
        r15 = LocaleController.getString(R.string.MobileHidden);
        goto L86
    L79:
        TLRPC.User r16 = this.user;
        if (r16 != null) goto L82;
    L84:
        r15 = LocaleController.getString(R.string.HiddenName);
        goto L86
    L82:
        if (TextUtils.isEmpty(r16.phone) == true) goto L84;
        r15 = PhoneFormat.getInstance().format("+" + this.user.phone);
    L86:
        if (this.customPaints == false) goto L101;
        if (this.namePaint != null) goto L90;
        TextPaint r2 = new TextPaint(1);
        this.namePaint = r2;
        r2.setTypeface(AndroidUtilities.bold());
    L90:
        TextPaint r22 = this.namePaint;
        if (this.callCellStyle == false) goto L93;
        float r11 = 15.0f;
    L94:
        r22.setTextSize(AndroidUtilities.dp(r11));
        TLRPC.EncryptedChat r23 = this.encryptedChat;
        TextPaint r112 = this.namePaint;
        if (r23 == null) goto L97;
        r112.setColor(Theme.getColor(Theme.key_chats_secretName, this.resourcesProvider));
    L98:
        TextPaint r24 = this.namePaint;
    L99:
        TextPaint r132 = r24;
        if (LocaleController.isRTL == true) goto L107;
        int r25 = (getMeasuredWidth() - this.nameLeft) - AndroidUtilities.dp(14.0f);
        this.nameWidth = r25;
    L108:
        float r20 = 6.0f;
        if (this.drawNameLock == false) goto L112;
        this.nameWidth -= AndroidUtilities.dp(6.0f) + Theme.dialogs_lockDrawable.getIntrinsicWidth();
    L112:
        if (this.ad == null) goto L117;
        int r113 = ((int) this.adText.getCurrentWidth()) + AndroidUtilities.dp(20.66f);
        this.nameWidth -= r113;
        if (LocaleController.isRTL == false) goto L117;
        this.nameLeft += r113;
    L117:
        if (this.contact == null) goto L123;
        int r114 = (int) (Theme.dialogs_countTextPaint.measureText(LocaleController.getString(R.string.Invite)) + 1.0f);
        this.actionLayout = new StaticLayout(LocaleController.getString(R.string.Invite), Theme.dialogs_countTextPaint, r114, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        if (LocaleController.isRTL == true) goto L121;
        this.actionLeft = ((getMeasuredWidth() - r114) - AndroidUtilities.dp(19.0f)) - AndroidUtilities.dp(16.0f);
    L122:
        this.nameWidth -= AndroidUtilities.dp(32.0f) + r114;
        goto L123
    L121:
        this.actionLeft = AndroidUtilities.dp(19.0f) + AndroidUtilities.dp(16.0f);
        this.nameLeft += r114;
        this.statusLeft += r114;
    L123:
        this.nameWidth -= getPaddingLeft() + getPaddingRight();
        int r26 = r25 - (getPaddingLeft() + getPaddingRight());
        if (this.drawCount == false) goto L133;
        int r94 = MessagesController.getInstance(this.currentAccount).getDialogUnreadCount(MessagesController.getInstance(this.currentAccount).dialogs_dict.get(this.dialog_id));
        if (r94 == 0) goto L131;
        this.lastUnreadCount = r94;
        String r95 = String.format(Locale.US, TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r94)});
        this.countWidth = Math.max(AndroidUtilities.dp(12.0f), (int) Math.ceil(Theme.dialogs_countTextPaint.measureText(r95)));
        this.countLayout = new StaticLayout(r95, Theme.dialogs_countTextPaint, this.countWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        int r96 = this.countWidth + AndroidUtilities.dp(18.0f);
        this.nameWidth -= r96;
        r26 = r26 - r96;
        if (LocaleController.isRTL == true) goto L130;
        this.countLeft = (getMeasuredWidth() - this.countWidth) - AndroidUtilities.dp(19.0f);
    L132:
        int r262 = r26;
        if (this.botVerificationDrawable.isEmpty() == true) goto L141;
        if (LocaleController.isRTL == false) goto L139;
        this.nameWidth -= this.botVerificationDrawable.getIntrinsicWidth();
        goto L141
    L139:
        this.nameLeft += this.botVerificationDrawable.getIntrinsicWidth();
    L141:
        if (this.statusDrawable.isEmpty() == true) goto L147;
        if (LocaleController.isRTL == true) goto L147;
        this.nameWidth -= this.statusDrawable.getIntrinsicWidth();
    L147:
        if (this.nameWidth >= 0) goto L149;
        this.nameWidth = 0;
    L149:
        float r27 = this.nameWidth - AndroidUtilities.dp(12.0f);
        TextUtils.TruncateAt r97 = TextUtils.TruncateAt.END;
        CharSequence r17 = TextUtils.ellipsize(r15, r132, r27, r97);
        if (r17 == null) goto L152;
        r17 = Emoji.replaceEmoji(r17, r132.getFontMetricsInt(), false);
    L152:
        int r142 = this.nameWidth;
        Layout.Alignment r152 = Layout.Alignment.ALIGN_NORMAL;
        this.nameLayout = new StaticLayout(r17, r132, r142, r152, 1.0f, 0.0f, false);
        TextPaint r18 = Theme.dialogs_offlinePaint;
        TLRPC.Chat r28 = this.chat;
        if (r28 != null) goto L155;
    L183:
        CharSequence r29 = this.subLabel;
        if (r29 != null) goto L221;
        TLRPC.User r210 = this.user;
        if (r210 != null) goto L189;
        r29 = null;
        goto L221
    L189:
        if (MessagesController.isSupportUser(r210) == false) goto L191;
        r29 = LocaleController.getString(R.string.SupportStatus);
        goto L221
    L191:
        TLRPC.User r211 = this.user;
        boolean r122 = r211.bot;
        if (r122 == false) goto L196;
        int r133 = r211.bot_active_users;
        if (r133 == 0) goto L196;
        r29 = LocaleController.formatPluralStringSpaced("BotUsersShort", r133);
    L196:
        if (r122 == false) goto L198;
        r29 = LocaleController.getString(R.string.Bot);
        goto L221
    L198:
        long r123 = r211.id;
        if (r123 != UserObject.VERIFY) goto L202;
        r29 = LocaleController.getString(R.string.VerifyCodesNotifications);
        goto L221
    L202:
        if (UserObject.isService(r123) == false) goto L205;
        r29 = LocaleController.getString(R.string.ServiceNotifications);
        goto L221
    L205:
        if (this.isOnline != null) goto L207;
        this.isOnline = new boolean[1];
    L207:
        boolean[] r212 = this.isOnline;
        r212[0] = false;
        r29 = LocaleController.formatUserStatus(this.currentAccount, this.user, r212);
        if (this.isOnline[0] == false) goto L210;
        r18 = Theme.dialogs_onlinePaint;
    L210:
        TLRPC.User r124 = this.user;
        if (r124 == null) goto L221;
        if (r124.id == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L218;
        TLRPC.UserStatus r125 = this.user.status;
        if (r125 == null) goto L221;
        if (r125.expires <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L221;
    L218:
        r18 = Theme.dialogs_onlinePaint;
        r29 = LocaleController.getString(R.string.Online);
    L221:
        if (this.savedMessages == false) goto L223;
    L224:
        this.nameTop = AndroidUtilities.dp(20.0f);
        r29 = null;
    L226:
        if (this.customPaints == false) goto L242;
        if (this.statusPaint != null) goto L230;
        this.statusPaint = new TextPaint(1);
    L230:
        TextPaint r8 = this.statusPaint;
        if (this.callCellStyle == false) goto L233;
        float r126 = 13.0f;
    L234:
        r8.setTextSize(AndroidUtilities.dp(r126));
        if (r18 != Theme.dialogs_offlinePaint) goto L238;
        this.statusPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, this.resourcesProvider));
    L240:
        r18 = this.statusPaint;
        goto L242
    L238:
        if (r18 != Theme.dialogs_onlinePaint) goto L240;
        this.statusPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText3, this.resourcesProvider));
        goto L240
    L233:
        r126 = 15.0f;
    L242:
        if (TextUtils.isEmpty(r29) == true) goto L244;
        StaticLayout r232 = new StaticLayout(TextUtils.ellipsize(r29, r18, r262 - AndroidUtilities.dp(12.0f), r97), r18, r262, r152, 1.0f, 0.0f, false);
        int r213 = r262;
        this.statusLayout = r232;
        this.nameTop = AndroidUtilities.dp(9.0f);
        this.nameLockTop -= AndroidUtilities.dp(10.0f);
    L246:
        if (LocaleController.isRTL == false) goto L249;
        int r19 = (getMeasuredWidth() - AndroidUtilities.dp(57.0f)) - getPaddingRight();
    L255:
        RectF r4 = this.avatarStoryParams.originalAvatarRect;
        float r5 = r19;
        if (this.callCellStyle == false) goto L258;
        float r72 = 6.0f;
    L259:
        float r73 = AndroidUtilities.dp(r72);
        float r10 = 46.0f;
        if (this.callCellStyle == false) goto L263;
        float r98 = 44.0f;
    L266:
        float r110 = r19 + AndroidUtilities.dp(r98);
        if (this.callCellStyle == true) goto L270;
        r20 = 7.0f;
    L270:
        int r82 = AndroidUtilities.dp(r20);
        if (this.callCellStyle == false) goto L273;
        r10 = 44.0f;
    L273:
        r4.set(r5, r73, r110, r82 + AndroidUtilities.dp(r10));
        boolean r111 = LocaleController.isRTL;
        StaticLayout r42 = this.nameLayout;
        if (r111 == false) goto L292;
        if (r42.getLineCount() > 0) goto L278;
    L282:
        StaticLayout r115 = this.statusLayout;
        if (r115 != null) goto L285;
    L307:
        this.nameLeft += getPaddingLeft();
        this.statusLeft += getPaddingLeft();
        this.nameLockLeft += getPaddingLeft();
        if (this.callCellStyle == false) goto L311;
        this.nameLeft += AndroidUtilities.dp(1.0f);
        this.nameTop += AndroidUtilities.dp(1.0f);
        return;
    L311:
        return;
    L285:
        if (r115.getLineCount() <= 0) goto L307;
        if (this.statusLayout.getLineLeft(0) != 0.0f) goto L307;
        double r3 = Math.ceil(this.statusLayout.getLineWidth(0));
        double r116 = r213;
        if (r3 >= r116) goto L307;
        this.statusLeft = (int) (this.statusLeft + (r116 - r3));
        goto L307
    L278:
        if (this.nameLayout.getLineLeft(0) != 0.0f) goto L282;
        double r43 = Math.ceil(this.nameLayout.getLineWidth(0));
        int r117 = this.nameWidth;
        if (r43 >= r117) goto L282;
        this.nameLeft = (int) (this.nameLeft + (r117 - r43));
        goto L282
    L292:
        if (r42.getLineCount() > 0) goto L294;
    L298:
        StaticLayout r118 = this.statusLayout;
        if (r118 == null) goto L307;
        if (r118.getLineCount() <= 0) goto L307;
        if (this.statusLayout.getLineRight(0) != r213) goto L307;
        double r32 = Math.ceil(this.statusLayout.getLineWidth(0));
        double r119 = r213;
        if (r32 >= r119) goto L307;
        this.statusLeft = (int) (this.statusLeft - (r119 - r32));
        goto L307
    L294:
        if (this.nameLayout.getLineRight(0) != this.nameWidth) goto L298;
        double r33 = Math.ceil(this.nameLayout.getLineWidth(0));
        int r120 = this.nameWidth;
        if (r33 >= r120) goto L298;
        this.nameLeft = (int) (this.nameLeft - (r120 - r33));
        goto L298
    L263:
        if (this.rectangularAvatar == false) goto L265;
        r98 = 42.0f;
        goto L266
    L265:
        r98 = 46.0f;
        goto L266
    L258:
        r72 = 7.0f;
        goto L259
    L249:
        if (this.callCellStyle == false) goto L252;
        r7 = 14.0f;
    L254:
        r19 = AndroidUtilities.dp(r7) + getPaddingLeft();
        goto L255
    L252:
        if (this.rectangularAvatar == false) goto L254;
        r7 = 15.0f;
        goto L254
    L244:
        r213 = r262;
        this.nameTop = AndroidUtilities.dp(20.0f);
        this.statusLayout = null;
        goto L246
    L223:
        if (UserObject.isReplyUser(this.user) == false) goto L226;
    L155:
        if (this.subLabel != null) goto L183;
        if (ChatObject.isCommunity(r28) == false) goto L160;
        r29 = LocaleController.getString(R.string.Community).toLowerCase();
    L182:
        this.nameTop = AndroidUtilities.dp(19.0f);
        goto L226
    L160:
        boolean r214 = ChatObject.isChannelAndNotMegaGroup(this.chat);
        TLRPC.Chat r127 = this.chat;
        if (r214 == false) goto L169;
        int r215 = r127.participants_count;
        if (r215 == 0) goto L166;
        r29 = LocaleController.formatPluralStringComma("Subscribers", r215);
        goto L182
    L166:
        if (ChatObject.isPublic(r127) == true) goto L168;
        r29 = LocaleController.getString(R.string.ChannelPrivate).toLowerCase();
        goto L182
    L168:
        r29 = LocaleController.getString(R.string.ChannelPublic).toLowerCase();
        goto L182
    L169:
        int r216 = r127.participants_count;
        if (r216 == 0) goto L173;
        r29 = LocaleController.formatPluralStringComma("Members", r216);
        goto L182
    L173:
        if (r127.has_geo == false) goto L176;
        r29 = LocaleController.getString(R.string.MegaLocation);
        goto L182
    L176:
        if (ChatObject.isMonoForum(r127) == false) goto L179;
        r29 = LocaleController.getString(R.string.MonoforumMessages);
        goto L182
    L179:
        if (ChatObject.isPublic(this.chat) == true) goto L181;
        r29 = LocaleController.getString(R.string.MegaPrivate).toLowerCase();
        goto L182
    L181:
        r29 = LocaleController.getString(R.string.MegaPublic).toLowerCase();
        goto L182
    L130:
        this.countLeft = AndroidUtilities.dp(19.0f);
        this.nameLeft += r96;
        this.statusLeft += r96;
        goto L132
    L131:
        this.lastUnreadCount = 0;
        this.countLayout = null;
        goto L132
    L133:
        this.lastUnreadCount = 0;
        this.countLayout = null;
        goto L132
    L107:
        r25 = (getMeasuredWidth() - this.nameLeft) - AndroidUtilities.dp(AndroidUtilities.leftBaseline);
        this.nameWidth = r25;
        goto L108
    L97:
        r112.setColor(Theme.getColor(Theme.key_chats_name, this.resourcesProvider));
        goto L98
    L93:
        r11 = 16.0f;
        goto L94
    L101:
        if (this.encryptedChat == null) goto L103;
        r24 = Theme.dialogs_searchNameEncryptedPaint;
        goto L99
    L103:
        r24 = Theme.dialogs_searchNamePaint;
        goto L99
    L66:
        if (r12 != null) goto L74;
        r13 = AndroidUtilities.removeRTL(AndroidUtilities.removeDiacritics(this.chat.title));
        goto L74
    L68:
        if (r12 != null) goto L74;
        r13 = AndroidUtilities.removeRTL(AndroidUtilities.removeDiacritics(r9.title));
        goto L74
    L70:
        if (r12 != null) goto L74;
        TLRPC.User r217 = this.user;
        r13 = r12;
        if (r217 == null) goto L74;
        r13 = AndroidUtilities.removeRTL(AndroidUtilities.removeDiacritics(UserObject.getUserName(r217)));
        goto L74
    L50:
        if (this.adText != null) goto L53;
        SpannableStringBuilder r121 = new SpannableStringBuilder(LocaleController.getString(R.string.SearchAd)).append(" i");
        ColoredImageSpan r52 = new ColoredImageSpan(R.drawable.ic_ab_other);
        r52.setScale(0.55f, 0.55f);
        r52.spaceScaleX = 0.7f;
        r52.translate(-AndroidUtilities.dp(2.0f), 0.0f);
        r121.setSpan(r52, r121.length() - 1, r121.length(), 33);
        this.adText = new Text(r121, 12.0f);
    L53:
        if (this.adBackgroundPaint != null) goto L55;
        this.adBackgroundPaint = new Paint(1);
        goto L55
    L46:
        this.statusLeft = AndroidUtilities.dp(11.0f);
        goto L48
    L7:
        this.nameLockLeft = (getMeasuredWidth() - AndroidUtilities.dp(AndroidUtilities.leftBaseline + 2)) - Theme.dialogs_lockDrawable.getIntrinsicWidth();
        this.nameLeft = AndroidUtilities.dp(11.0f);
        goto L8
    L9:
        TLRPC.Chat r128 = this.chat;
        if (r128 == null) goto L21;
        this.dialog_id = -r128.id;
        this.drawCheck = r128.verified;
        if (r128.monoforum == false) goto L17;
        TLRPC.Chat r129 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.chat.linked_monoforum_id));
        if (r129 == null) goto L17;
        this.drawCheck = r129.verified;
    L17:
        if (LocaleController.isRTL == true) goto L19;
        this.nameLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
    L20:
        updateStatus(this.drawCheck, BadgesController.INSTANCE.getBadge(this.chat), null, this.chat, false);
        goto L44
    L19:
        this.nameLeft = AndroidUtilities.dp(11.0f);
        goto L20
    L21:
        TLRPC.User r130 = this.user;
        if (r130 == null) goto L35;
        this.dialog_id = r130.id;
        if (LocaleController.isRTL == true) goto L26;
        this.nameLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
    L27:
        this.nameLockTop = AndroidUtilities.dp(21.0f);
        this.drawCheck = this.user.verified;
        if (this.savedMessages == false) goto L30;
    L32:
        boolean r131 = false;
    L33:
        this.drawPremium = r131;
        updateStatus(this.drawCheck, BadgesController.INSTANCE.getBadge(this.user), this.user, null, false);
        goto L44
    L30:
        if (MessagesController.getInstance(this.currentAccount).isPremiumUser(this.user) == false) goto L32;
        r131 = true;
        goto L33
    L26:
        this.nameLeft = AndroidUtilities.dp(11.0f);
        goto L27
    L35:
        if (this.contact == null) goto L44;
        this.dialog_id = 0;
        if (LocaleController.isRTL == true) goto L39;
        this.nameLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
    L41:
        if (this.actionButton != null) goto L44;
        CanvasButton r134 = new CanvasButton(this);
        this.actionButton = r134;
        r134.setDelegate(new ProfileSearchCell$$ExternalSyntheticLambda0(this));
        goto L44
    L39:
        this.nameLeft = AndroidUtilities.dp(11.0f);
        goto L41
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r5, int r6, Object... r7) {
        if (r5 != NotificationCenter.emojiLoaded) goto L7;
        invalidate();
        return;
    L7:
        if (r5 != NotificationCenter.userIsPremiumBlockedUpadted) goto L29;
        TL_account.RequirementToContact r62 = null;
        if (this.user != null) goto L11;
        TLRPC.Chat r52 = this.chat;
        if (r52 == null) goto L16;
        r62 = ChatObject.getRequirementToContact(r52);
    L24:
        if (this.premiumBlocked == DialogObject.isPremiumBlocked(r62)) goto L26;
    L27:
        this.premiumBlocked = DialogObject.isPremiumBlocked(r62);
        this.starsPriceBlocked = DialogObject.getMessagesStarsPrice(r62);
        invalidate();
        return;
    L26:
        if (this.starsPriceBlocked != DialogObject.getMessagesStarsPrice(r62)) goto L27;
        return;
    L16:
        ContactsController.Contact r53 = this.contact;
        if (r53 != null) goto L19;
        return;
    L19:
        if (this.showPremiumBlocked == false) goto L24;
        if (r53.user == null) goto L24;
        r62 = MessagesController.getInstance(this.currentAccount).isUserContactBlocked(this.contact.user.id);
        goto L24
    L11:
        if (this.showPremiumBlocked == false) goto L24;
        r62 = MessagesController.getInstance(this.currentAccount).isUserContactBlocked(this.user.id);
        goto L24
    }

    public TLRPC.Chat getChat() {
        return this.chat;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public long getDialogId() {
        return this.dialog_id;
    }

    public TLRPC.User getUser() {
        return this.user;
    }

    public boolean isBlocked() {
        return this.premiumBlocked;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.avatarImage.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        if (this.showPremiumBlocked == false) goto L5;
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.userIsPremiumBlockedUpadted);
    L5:
        this.statusDrawable.attach();
        this.botVerificationDrawable.attach();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.avatarImage.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        if (this.showPremiumBlocked == false) goto L5;
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.userIsPremiumBlockedUpadted);
    L5:
        this.statusDrawable.detach();
        this.botVerificationDrawable.detach();
    }

    @Override // android.view.View
    public void onDraw(Canvas r15) {
        if (this.user != null) goto L12;
        if (this.chat != null) goto L12;
        if (this.encryptedChat != null) goto L12;
        if (this.contact != null) goto L12;
        return;
    L12:
        if (this.useSeparator == true) goto L14;
        Canvas r2 = r15;
    L27:
        if (this.drawNameLock == false) goto L29;
        BaseCell.setDrawableBounds(Theme.dialogs_lockDrawable, this.nameLockLeft, this.nameLockTop);
        Theme.dialogs_lockDrawable.draw(r2);
    L29:
        StaticLayout r152 = this.nameLayout;
        if (r152 == null) goto L47;
        if (LocaleController.isRTL == false) goto L35;
        int r153 = (int) ((this.nameLeft + r152.getLineRight(0)) + AndroidUtilities.dp(6.0f));
    L38:
        BaseCell.setDrawableBounds(this.botVerificationDrawable, r153, this.nameTop + ((this.nameLayout.getHeight() - this.botVerificationDrawable.getIntrinsicHeight()) / 2.0f));
        this.botVerificationDrawable.draw(r2);
        r2.save();
        r2.translate(this.nameLeft, this.nameTop);
        this.nameLayout.draw(r2);
        r2.restore();
        if (LocaleController.isRTL == true) goto L41;
        int r154 = (int) ((this.nameLeft + this.nameLayout.getLineRight(0)) + AndroidUtilities.dp(6.0f));
    L45:
        BaseCell.setDrawableBounds(this.statusDrawable, r154, this.nameTop + ((this.nameLayout.getHeight() - this.statusDrawable.getIntrinsicHeight()) / 2.0f));
        this.statusDrawable.draw(r2);
        goto L47
    L41:
        if (this.nameLayout.getLineLeft(0) != 0.0f) goto L43;
        r154 = (this.nameLeft - AndroidUtilities.dp(3.0f)) - this.statusDrawable.getIntrinsicWidth();
        goto L45
    L43:
        r154 = (int) ((((this.nameLeft + this.nameWidth) - Math.ceil(this.nameLayout.getLineWidth(0))) - AndroidUtilities.dp(3.0f)) - this.statusDrawable.getIntrinsicWidth());
        goto L45
    L35:
        if (r152.getLineLeft(0) != 0.0f) goto L37;
        r153 = (this.nameLeft - AndroidUtilities.dp(3.0f)) - this.botVerificationDrawable.getIntrinsicWidth();
        goto L38
    L37:
        r153 = (int) ((((this.nameLeft + this.nameWidth) - Math.ceil(this.nameLayout.getLineWidth(0))) - AndroidUtilities.dp(3.0f)) - this.botVerificationDrawable.getIntrinsicWidth());
    L47:
        if (this.ad == null) goto L58;
        if (this.adText == null) goto L58;
        if (this.adBackgroundPaint == null) goto L58;
        int r12 = Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider);
        this.adBackgroundPaint.setColor(Theme.multAlpha(r12, 0.1f));
        int r155 = ((int) this.adText.getWidth()) + AndroidUtilities.dp(12.66f);
        int r6 = AndroidUtilities.dp(17.33f);
        if (LocaleController.isRTL == false) goto L55;
        int r7 = AndroidUtilities.dp(12.0f);
    L56:
        float r9 = r7;
        this.adBounds.set(r9, this.nameTop, r7 + r155, r10 + r6);
        this.adBounds.inset(-AndroidUtilities.dp(6.0f), -AndroidUtilities.dp(6.0f));
        r2.save();
        float r0 = this.adBounce.getScale(0.1f);
        r2.scale(r0, r0, this.adBounds.centerX(), this.adBounds.centerY());
        r2.translate(r9, this.nameTop);
        RectF r02 = AndroidUtilities.rectTmp;
        float r5 = r6;
        r02.set(0.0f, 0.0f, r155, r5);
        float r11 = r5 / 2.0f;
        r2.drawRoundRect(r02, r11, r11, this.adBackgroundPaint);
        this.adText.draw(r2, AndroidUtilities.dp(6.33f), r11, r12, 1.0f);
        r2.restore();
        goto L58
    L55:
        r7 = (getWidth() - AndroidUtilities.dp(12.0f)) - r155;
    L58:
        if (this.statusLayout == null) goto L65;
        r2.save();
        float r156 = this.statusLeft + this.sublabelOffsetX;
        if (this.callCellStyle == false) goto L62;
        float r03 = 35.0f;
    L63:
        r2.translate(r156, AndroidUtilities.dp(r03) + this.sublabelOffsetY);
        this.statusLayout.draw(r2);
        r2.restore();
        goto L65
    L62:
        r03 = 33.0f;
    L65:
        if (this.countLayout == null) goto L72;
        this.rect.set(this.countLeft - AndroidUtilities.dp(5.5f), this.countTop, (r15 + this.countWidth) + AndroidUtilities.dp(11.0f), this.countTop + AndroidUtilities.dp(23.0f));
        RectF r157 = this.rect;
        float r62 = AndroidUtilities.density;
        float r8 = r62 * 11.5f;
        float r63 = r62 * 11.5f;
        if (MessagesController.getInstance(this.currentAccount).isDialogMuted(this.dialog_id, 0) == false) goto L69;
        Paint r72 = Theme.dialogs_countGrayPaint;
    L70:
        r2.drawRoundRect(r157, r8, r63, r72);
        r2.save();
        r2.translate(this.countLeft, this.countTop + AndroidUtilities.dp(4.0f));
        this.countLayout.draw(r2);
        r2.restore();
        goto L72
    L69:
        r72 = Theme.dialogs_countPaint;
    L72:
        if (this.actionLayout == null) goto L75;
        this.actionButton.setColor(Theme.getColor(Theme.key_chats_unreadCounter), Theme.getColor(Theme.key_chats_unreadCounterText));
        RectF r158 = AndroidUtilities.rectTmp;
        r158.set(this.actionLeft, this.countTop, r7 + this.actionLayout.getWidth(), this.countTop + AndroidUtilities.dp(23.0f));
        r158.inset(-AndroidUtilities.dp(16.0f), -AndroidUtilities.dp(4.0f));
        this.actionButton.setRect(r158);
        this.actionButton.setRounded(true);
        this.actionButton.draw(r2);
        r2.save();
        r2.translate(this.actionLeft, this.countTop + AndroidUtilities.dp(4.0f));
        this.actionLayout.draw(r2);
        r2.restore();
    L75:
        if (this.dontDrawAvatar == true) goto L94;
        TLRPC.Chat r159 = this.chat;
        if (r159 != null) goto L80;
    L85:
        TLRPC.User r04 = this.user;
        if (r04 == null) goto L88;
        StoriesUtilities.drawAvatarWithStory(r04.id, r2, this.avatarImage, this.avatarStoryParams);
        goto L94
    L88:
        if (r159 != null) goto L90;
        this.avatarImage.setImageCoords(this.avatarStoryParams.originalAvatarRect);
        this.avatarImage.draw(r2);
        goto L94
    L90:
        if (ChatObject.isCommunity(r159) == false) goto L92;
        DrawableUtils.drawCommunityCardDrawable(r2, Theme.dialogs_communityCardsDrawable, this.avatarStoryParams.originalAvatarRect.centerX(), this.avatarStoryParams.originalAvatarRect.centerY(), this.avatarStoryParams.originalAvatarRect.width());
    L92:
        StoriesUtilities.drawAvatarWithStory(-this.chat.id, r2, this.avatarImage, this.avatarStoryParams);
        goto L94
    L80:
        if (r159.monoforum == false) goto L85;
        if (this.bubbleClip != null) goto L84;
        this.bubbleClip = new PhotoBubbleClip();
    L84:
        this.bubbleClip.setBounds((int) this.avatarStoryParams.originalAvatarRect.centerX(), (int) this.avatarStoryParams.originalAvatarRect.centerY(), (int) (this.avatarStoryParams.originalAvatarRect.width() / 2.0f));
        r2.save();
        r2.clipPath(this.bubbleClip);
        this.avatarImage.setImageCoords(this.avatarStoryParams.originalAvatarRect);
        this.avatarImage.draw(r2);
        r2.restore();
    L94:
        float r1510 = this.premiumBlockedT.set(this.premiumBlocked);
        if (r1510 <= 0.0f) goto L104;
        float r05 = this.avatarImage.getCenterY() + AndroidUtilities.dp(14.0f);
        float r3 = this.avatarImage.getCenterX() + AndroidUtilities.dp(16.0f);
        r2.save();
        Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
        r2.drawCircle(r3, r05, AndroidUtilities.dp(11.33f) * r1510, Theme.dialogs_onlineCirclePaint);
        if (this.premiumGradient != null) goto L99;
        this.premiumGradient = new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradient1, Theme.key_premiumGradient2, -1, -1, -1, this.resourcesProvider);
    L99:
        this.premiumGradient.gradientMatrix((int) (r3 - AndroidUtilities.dp(10.0f)), (int) (r05 - AndroidUtilities.dp(10.0f)), (int) (AndroidUtilities.dp(10.0f) + r3), (int) (AndroidUtilities.dp(10.0f) + r05), 0.0f, 0.0f);
        r2.drawCircle(r3, r05, AndroidUtilities.dp(10.0f) * r1510, this.premiumGradient.paint);
        if (this.lockDrawable != null) goto L102;
        Drawable r52 = getContext().getResources().getDrawable(R.drawable.msg_mini_lock2).mutate();
        this.lockDrawable = r52;
        r52.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
    L102:
        this.lockDrawable.setBounds((int) (r3 - (((r5.getIntrinsicWidth() / 2.0f) * 0.875f) * r1510)), (int) (r05 - (((this.lockDrawable.getIntrinsicHeight() / 2.0f) * 0.875f) * r1510)), (int) (r3 + (((this.lockDrawable.getIntrinsicWidth() / 2.0f) * 0.875f) * r1510)), (int) (r05 + (((this.lockDrawable.getIntrinsicHeight() / 2.0f) * 0.875f) * r1510)));
        this.lockDrawable.setAlpha((int) (r1510 * 255.0f));
        this.lockDrawable.draw(r2);
        r2.restore();
    L104:
        if (this.openBot == true) goto L106;
        return;
    L106:
        if (this.openButtonText == null) goto L114;
        float r06 = AndroidUtilities.dp(28.0f) + this.openButtonText.getCurrentWidth();
        if (LocaleController.isRTL == false) goto L110;
        float r32 = AndroidUtilities.dp(15.0f);
    L111:
        float r1511 = AndroidUtilities.dp(28.0f);
        this.openButtonBackgroundPaint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton));
        this.openButtonRect.set(r32, (getHeight() - r1511) / 2.0f, r06 + r32, (getHeight() + r1511) / 2.0f);
        r2.save();
        float r1512 = this.openButtonBounce.getScale(0.06f);
        r2.scale(r1512, r1512, this.openButtonRect.centerX(), this.openButtonRect.centerY());
        RectF r1513 = this.openButtonRect;
        r2.drawRoundRect(r1513, r1513.height() / 2.0f, this.openButtonRect.height() / 2.0f, this.openButtonBackgroundPaint);
        this.openButtonText.draw(r2, r32 + AndroidUtilities.dp(14.0f), getHeight() / 2.0f, -1, 1.0f);
        r2.restore();
        return;
    L110:
        r32 = (getWidth() - r06) - AndroidUtilities.dp(15.0f);
        goto L111
    L114:
        return;
    L14:
        if (this.customPaints == false) goto L18;
        Theme.ResourcesProvider r07 = this.resourcesProvider;
        if (r07 == null) goto L18;
        Paint r08 = r07.getPaint("paintDivider");
    L19:
        if (r08 != null) goto L21;
        r08 = Theme.dividerPaint;
    L21:
        Paint r73 = r08;
        if (LocaleController.isRTL == false) goto L24;
        r2 = r15;
        r2.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth() - AndroidUtilities.dp(AndroidUtilities.leftBaseline), getMeasuredHeight() - 1, r73);
        goto L27
    L24:
        r2 = r15;
        r2.drawLine(AndroidUtilities.dp(AndroidUtilities.leftBaseline), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, r73);
    L18:
        r08 = null;
        goto L19
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
        super.onInitializeAccessibilityNodeInfo(r4);
        StringBuilder r0 = new StringBuilder();
        StaticLayout r1 = this.nameLayout;
        if (r1 == null) goto L6;
        r0.append(r1.getText());
    L6:
        if (this.drawCheck == false) goto L9;
        r0.append(", ");
        r0.append(LocaleController.getString(R.string.AccDescrVerified));
        r0.append("\n");
    L9:
        if (this.statusLayout != null) goto L11;
    L14:
        r4.setText(r0.toString());
        if (this.checkBox.isChecked() == false) goto L18;
        r4.setCheckable(true);
        r4.setChecked(this.checkBox.isChecked());
        r4.setClassName("android.widget.CheckBox");
        return;
    L18:
        return;
    L11:
        if (r0.length() <= 0) goto L13;
        r0.append(", ");
    L13:
        r0.append(this.statusLayout.getText());
        goto L14
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r1) {
        return onTouchEvent(r1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        if (this.user != null) goto L12;
        if (this.chat != null) goto L12;
        if (this.encryptedChat != null) goto L12;
        if (this.contact != null) goto L12;
        return;
    L12:
        if (this.checkBox != null) goto L14;
    L18:
        if (r2 == false) goto L21;
        buildLayout();
        return;
    L21:
        return;
    L14:
        if (LocaleController.isRTL == false) goto L16;
        int r52 = (r5 - r3) - AndroidUtilities.dp(42.0f);
    L17:
        int r32 = AndroidUtilities.dp(36.0f);
        CheckBox2 r42 = this.checkBox;
        r42.layout(r52, r32, r42.getMeasuredWidth() + r52, this.checkBox.getMeasuredHeight() + r32);
        goto L18
    L16:
        r52 = AndroidUtilities.dp(42.0f);
        goto L17
    }

    @Override // android.view.View
    public void onMeasure(int r4, int r5) {
        CheckBox2 r52 = this.checkBox;
        if (r52 == null) goto L5;
        r52.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30));
    L5:
        int r42 = View.MeasureSpec.getSize(r4);
        if (this.callCellStyle == false) goto L8;
        int r53 = AndroidUtilities.dp(56.0f);
    L9:
        setMeasuredDimension(r42, r53);
        return;
    L8:
        r53 = AndroidUtilities.dp(60.0f) + (this.useSeparator ? 1 : 0);
        goto L9
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        if (this.openBot == false) goto L30;
        if (this.onOpenButtonClick == null) goto L30;
        if (this.user == null) goto L30;
        boolean r0 = this.openButtonRect.contains(r8.getX(), r8.getY());
        if (r8.getAction() != 0) goto L11;
    L24:
        this.openButtonBounce.setPressed(r0);
    L25:
        if (r0 == false) goto L27;
    L28:
        return true;
    L27:
        if (this.openButtonBounce.isPressed() == true) goto L28;
    L55:
        if (this.user != null) goto L59;
        if (this.chat != null) goto L59;
    L61:
        CanvasButton r02 = this.actionButton;
        if (r02 == null) goto L67;
        if (r02.checkTouchEvent(r8) == false) goto L67;
        return true;
    L67:
        return super.onTouchEvent(r8);
    L59:
        if (this.avatarStoryParams.checkOnTouchEvent(r8, this) == false) goto L61;
        return true;
    L11:
        if (r8.getAction() == 2) goto L24;
        if (r8.getAction() != 1) goto L21;
        if (this.openButtonBounce.isPressed() == false) goto L18;
        this.onOpenButtonClick.run(this.user);
    L18:
        this.openButtonBounce.setPressed(false);
        return true;
    L21:
        if (r8.getAction() != 3) goto L25;
        this.openButtonBounce.setPressed(false);
        return true;
    L30:
        if (this.ad == null) goto L55;
        if (this.onSponsoredOptionsClick == null) goto L55;
        boolean r03 = this.adBounds.contains(r8.getX(), r8.getY());
        if (r8.getAction() != 0) goto L36;
    L49:
        this.adBounce.setPressed(r03);
    L50:
        if (r03 == false) goto L52;
    L53:
        return true;
    L52:
        if (this.adBounce.isPressed() == false) goto L55;
    L36:
        if (r8.getAction() == 2) goto L49;
        if (r8.getAction() != 1) goto L46;
        if (this.adBounce.isPressed() == false) goto L43;
        this.onSponsoredOptionsClick.run(this, this.ad);
    L43:
        this.adBounce.setPressed(false);
        return true;
    L46:
        if (r8.getAction() != 3) goto L50;
        this.adBounce.setPressed(false);
        return true;
    }

    public void setAd(TLRPC.TL_sponsoredPeer r1) {
        this.ad = r1;
    }

    public void setAllowEmojiStatus(boolean r1) {
        this.allowEmojiStatus = r1;
    }

    public void setCallCellStyle() {
        this.callCellStyle = true;
        this.customPaints = true;
    }

    public void setChecked(boolean r1, boolean r2) {
        CheckBox2 r0 = this.checkBox;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setChecked(r1, r2);
    }

    public void setData(Object r4, TLRPC.EncryptedChat r5, CharSequence r6, CharSequence r7, boolean r8, boolean r9) {
        this.currentName = r6;
        TL_account.RequirementToContact r1 = null;
        if ((r4 instanceof TLRPC.User) == false) goto L15;
        this.user = (TLRPC.User) r4;
        this.chat = null;
        this.contact = null;
        if (this.showPremiumBlocked == false) goto L7;
        r1 = MessagesController.getInstance(this.currentAccount).isUserContactBlocked(this.user.id);
    L7:
        this.premiumBlocked = DialogObject.isPremiumBlocked(r1);
        this.starsPriceBlocked = DialogObject.getMessagesStarsPrice(r1);
        if (this.allowBotOpenButton == true) goto L10;
    L12:
        boolean r42 = false;
    L13:
        setOpenBotButton(r42);
    L26:
        this.encryptedChat = r5;
        this.subLabel = r7;
        this.drawCount = r8;
        this.savedMessages = r9;
        update(0);
        return;
    L10:
        if (this.user.bot_has_main_app == false) goto L12;
        r42 = true;
        goto L13
    L15:
        if ((r4 instanceof TLRPC.Chat) == false) goto L18;
        TLRPC.Chat r43 = (TLRPC.Chat) r4;
        this.chat = r43;
        this.user = null;
        this.contact = null;
        TL_account.RequirementToContact r44 = ChatObject.getRequirementToContact(r43);
        this.premiumBlocked = DialogObject.isPremiumBlocked(r44);
        this.starsPriceBlocked = DialogObject.getMessagesStarsPrice(r44);
        setOpenBotButton(false);
        goto L26
    L18:
        if ((r4 instanceof ContactsController.Contact) == false) goto L25;
        ContactsController.Contact r45 = (ContactsController.Contact) r4;
        this.contact = r45;
        this.chat = null;
        this.user = null;
        if (this.showPremiumBlocked == true) goto L22;
    L24:
        this.premiumBlocked = DialogObject.isPremiumBlocked(r1);
        this.starsPriceBlocked = DialogObject.getMessagesStarsPrice(r1);
        setOpenBotButton(false);
        goto L26
    L22:
        if (r45.user == null) goto L24;
        r1 = MessagesController.getInstance(this.currentAccount).isUserContactBlocked(this.contact.user.id);
        goto L24
    L25:
        setOpenBotButton(false);
        goto L26
    }

    public void setOnSponsoredOptionsClick(Utilities.Callback2<ProfileSearchCell, TLRPC.TL_sponsoredPeer> r1) {
        this.onSponsoredOptionsClick = r1;
    }

    public void setOpenBotButton(boolean r5) {
        if (this.openBot != r5) goto L6;
        return;
    L6:
        if (this.openButtonText != null) goto L9;
        this.openButtonText = new Text(LocaleController.getString(R.string.BotOpen), 14.0f, AndroidUtilities.bold());
    L9:
        if (r5 == false) goto L11;
        int r1 = ((int) this.openButtonText.getCurrentWidth()) + AndroidUtilities.dp(30.0f);
    L12:
        boolean r2 = LocaleController.isRTL;
        if (r2 == false) goto L15;
        int r3 = r1;
    L16:
        if (r2 == false) goto L18;
        r1 = 0;
    L18:
        setPadding(r3, 0, r1, 0);
        this.openBot = r5;
        this.openButtonBounce.setPressed(false);
        return;
    L15:
        r3 = 0;
        goto L16
    L11:
        r1 = 0;
        goto L12
    }

    public void setRectangularAvatar(boolean r1) {
        this.rectangularAvatar = r1;
    }

    public void setSubLabel(CharSequence r1) {
        this.subLabel = r1;
        update(0);
    }

    public void setSublabelOffset(int r1, int r2) {
        this.sublabelOffsetX = r1;
        this.sublabelOffsetY = r2;
    }

    public ProfileSearchCell showPremiumBlock(boolean r1) {
        this.showPremiumBlocked = r1;
        return this;
    }

    public void update(int r24) {
        TLRPC.User r1 = this.user;
        boolean r6 = true;
        TLRPC.FileLocation r3 = null;
        if (r1 == null) goto L18;
        this.avatarDrawable.setInfo(this.currentAccount, r1);
        if (UserObject.isReplyUser(this.user) == false) goto L7;
        this.avatarDrawable.setAvatarType(12);
        this.avatarImage.setImage(null, null, this.avatarDrawable, null, null, 0);
    L17:
        TLRPC.FileLocation r7 = r3;
        if (this.callCellStyle == false) goto L38;
        int r12 = 44;
    L42:
        if (ChatObject.isCommunity(this.chat) == false) goto L44;
        int r13 = ExteraConfig.getAvatarCorners(r12, false, AvatarCornerType.COMMUNITY);
    L58:
        setAvatarRadius(r13);
        if (r24 != 0) goto L61;
    L140:
        TLRPC.User r14 = this.user;
        if (r14 == null) goto L147;
        TLRPC.UserStatus r15 = r14.status;
        if (r15 == null) goto L145;
        this.lastStatus = r15.expires;
    L146:
        this.lastName = this.user.first_name + this.user.last_name;
    L154:
        this.lastAvatar = r7;
        if (getMeasuredWidth() == 0) goto L157;
    L160:
        buildLayout();
    L161:
        postInvalidate();
        return;
    L157:
        if (getMeasuredHeight() != 0) goto L160;
        requestLayout();
        goto L161
    L145:
        this.lastStatus = 0;
        goto L146
    L147:
        TLRPC.Chat r16 = this.chat;
        if (r16 == null) goto L154;
        if (r16.monoforum == false) goto L152;
        String r17 = ForumUtilities.getMonoForumTitle(this.currentAccount, r16);
    L153:
        this.lastName = r17;
        goto L154
    L152:
        r17 = r16.title;
        goto L153
    L61:
        if ((r24 & MessagesController.UPDATE_MASK_AVATAR) == 0) goto L65;
        if (this.user == null) goto L65;
    L68:
        TLRPC.FileLocation r18 = this.lastAvatar;
        if (r18 == null) goto L71;
        if (r7 != null) goto L71;
    L78:
        boolean r19 = true;
    L80:
        if (r19 == false) goto L82;
    L92:
        boolean r9 = r19;
    L93:
        if (r9 == false) goto L95;
    L108:
        if (r9 == true) goto L114;
        if ((r24 & MessagesController.UPDATE_MASK_NAME) == 0) goto L114;
        if (this.user == null) goto L114;
    L118:
        if (this.user == null) goto L120;
        String r110 = this.user.first_name + this.user.last_name;
    L125:
        if (r110.equals(this.lastName) == true) goto L127;
        r9 = true;
    L127:
        if (r9 == false) goto L129;
    L137:
        r6 = r9;
    L138:
        if (r6 == true) goto L140;
        return;
    L129:
        if (this.drawCount == false) goto L137;
        if ((r24 & MessagesController.UPDATE_MASK_READ_DIALOG_MESSAGE) == 0) goto L137;
        TLRPC.Dialog r111 = MessagesController.getInstance(this.currentAccount).dialogs_dict.get(this.dialog_id);
        if (r111 == null) goto L137;
        if (MessagesController.getInstance(this.currentAccount).getDialogUnreadCount(r111) == this.lastUnreadCount) goto L137;
    L120:
        TLRPC.Chat r112 = this.chat;
        if (r112.monoforum == false) goto L123;
        r110 = ForumUtilities.getMonoForumTitle(this.currentAccount, r112);
        goto L125
    L123:
        r110 = r112.title;
    L114:
        if ((r24 & MessagesController.UPDATE_MASK_CHAT_NAME) == 0) goto L127;
        if (this.chat == null) goto L127;
    L95:
        if ((r24 & MessagesController.UPDATE_MASK_EMOJI_STATUS) == 0) goto L108;
        TLRPC.User r113 = this.user;
        if (r113 == null) goto L99;
    L100:
        if (r113 == null) goto L102;
        boolean r2 = r113.verified;
    L103:
        BadgesController r32 = BadgesController.INSTANCE;
        TLObject r114 = r113;
        if (r113 != null) goto L107;
        r114 = this.chat;
    L107:
        updateStatus(r2, r32.getBadge(r114), this.user, this.chat, true);
        goto L108
    L102:
        r2 = this.chat.verified;
        goto L103
    L99:
        if (this.chat == null) goto L108;
    L82:
        if ((r24 & MessagesController.UPDATE_MASK_STATUS) == 0) goto L92;
        TLRPC.User r22 = this.user;
        if (r22 == null) goto L92;
        TLRPC.UserStatus r23 = r22.status;
        if (r23 == null) goto L88;
        int r25 = r23.expires;
    L90:
        if (r25 == this.lastStatus) goto L92;
        r9 = true;
        goto L93
    L88:
        r25 = 0;
    L71:
        if (r18 != null) goto L73;
        if (r7 != null) goto L78;
    L73:
        if (r18 != null) goto L75;
    L79:
        r19 = false;
        goto L80
    L75:
        if (r18.volume_id != r7.volume_id) goto L78;
        if (r18.local_id == r7.local_id) goto L79;
    L65:
        if ((r24 & MessagesController.UPDATE_MASK_CHAT_AVATAR) == 0) goto L79;
        if (this.chat == null) goto L79;
    L44:
        TLRPC.Chat r26 = this.chat;
        if (r26 != null) goto L47;
    L49:
        float r115 = r12;
        if (r26 == null) goto L54;
        if (r26.forum == false) goto L54;
    L55:
        boolean r27 = true;
    L57:
        r13 = ExteraConfig.getAvatarCorners(r115, false, r27);
    L54:
        if (this.rectangularAvatar == true) goto L55;
        r27 = false;
        goto L57
    L47:
        if (r26.monoforum == false) goto L49;
        r13 = 0;
        goto L58
    L38:
        if (this.rectangularAvatar == false) goto L40;
        r12 = 42;
        goto L42
    L40:
        r12 = 46;
        goto L42
    L7:
        boolean r116 = this.savedMessages;
        AvatarDrawable r4 = this.avatarDrawable;
        if (r116 == false) goto L10;
        r4.setAvatarType(1);
        this.avatarImage.setImage(null, null, this.avatarDrawable, null, null, 0);
        goto L17
    L10:
        TLRPC.User r117 = this.user;
        TLRPC.UserProfilePhoto r5 = r117.photo;
        if (r5 == null) goto L15;
        r3 = r5.photo_small;
        Drawable r52 = r5.strippedBitmap;
        if (r52 == null) goto L15;
        Drawable r122 = r52;
    L16:
        this.avatarImage.setImage(ImageLocation.getForUserOrChat(this.currentAccount, r117, 1), "50_50", ImageLocation.getForUserOrChat(this.user, 2), "50_50", r122, this.user, 0);
    L15:
        r122 = r4;
        goto L16
    L18:
        TLRPC.Chat r118 = this.chat;
        if (r118 == null) goto L30;
        AvatarDrawable r42 = this.avatarDrawable;
        TLRPC.ChatPhoto r53 = r118.photo;
        if (r53 == null) goto L25;
        r3 = r53.photo_small;
        Drawable r54 = r53.strippedBitmap;
        if (r54 == null) goto L25;
        Drawable r123 = r54;
    L26:
        boolean r55 = r118.monoforum;
        int r72 = this.currentAccount;
        if (r55 == false) goto L29;
        ForumUtilities.setMonoForumAvatar(r72, r118, r42, this.avatarImage);
        goto L17
    L29:
        r42.setInfo(r72, r118);
        this.avatarImage.setImage(ImageLocation.getForUserOrChat(this.currentAccount, this.chat, 1), "50_50", ImageLocation.getForUserOrChat(this.chat, 2), "50_50", r123, this.chat, 0);
    L25:
        r123 = r42;
        goto L26
    L30:
        ContactsController.Contact r119 = this.contact;
        AvatarDrawable r28 = this.avatarDrawable;
        if (r119 == null) goto L33;
        r28.setInfo(0, r119.first_name, r119.last_name);
        this.avatarImage.setImage(null, null, this.avatarDrawable, null, null, 0);
        goto L17
    L33:
        r28.setInfo(0, null, null);
        this.avatarImage.setImage(null, null, this.avatarDrawable, null, null, 0);
        goto L17
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        if (this.nameLayout != null) goto L5;
        return;
    L5:
        if (getMeasuredWidth() <= 0) goto L9;
        buildLayout();
        return;
    }

    public void updateStatus(boolean r8, BadgeDTO r9, TLRPC.User r10, TLRPC.Chat r11, boolean r12) {
        this.statusDrawable.center = LocaleController.isRTL;
        if (this.allowEmojiStatus == false) goto L11;
        if (r10 == null) goto L11;
        if (this.savedMessages == true) goto L11;
        if (DialogObject.getEmojiStatusDocumentId(r10.emoji_status) == 0) goto L11;
        this.statusDrawable.set(DialogObject.getEmojiStatusDocumentId(r10.emoji_status), r12);
        this.statusDrawable.setColor(Integer.valueOf(Theme.getColor(Theme.key_chats_verifiedBackground, this.resourcesProvider)));
    L34:
        if (r9 != null) goto L36;
    L37:
        this.statusDrawable.setParticles(false, false);
    L38:
        if (r10 == null) goto L40;
        long r82 = DialogObject.getBotVerificationIcon(r10);
    L44:
        if (r82 != 0) goto L46;
    L49:
        this.botVerificationDrawable.set(null, r12);
    L50:
        this.botVerificationDrawable.setColor(Integer.valueOf(Theme.getColor(Theme.key_chats_verifiedBackground, this.resourcesProvider)));
        return;
    L46:
        if (this.savedMessages == true) goto L49;
        this.botVerificationDrawable.set(r82, r12);
        goto L50
    L40:
        if (r11 == null) goto L42;
        r82 = DialogObject.getBotVerificationIcon(r11);
        goto L44
    L42:
        r82 = 0;
        goto L44
    L36:
        if (this.savedMessages == false) goto L38;
    L11:
        if (this.allowEmojiStatus == false) goto L18;
        if (r11 == null) goto L18;
        if (this.savedMessages == true) goto L18;
        if (DialogObject.getEmojiStatusDocumentId(r11.emoji_status) == 0) goto L18;
        this.statusDrawable.set(DialogObject.getEmojiStatusDocumentId(r11.emoji_status), r12);
        this.statusDrawable.setColor(Integer.valueOf(Theme.getColor(Theme.key_chats_verifiedBackground, this.resourcesProvider)));
    L18:
        boolean r0 = this.allowEmojiStatus;
        if (r0 == false) goto L22;
        if (r8 == false) goto L22;
        this.statusDrawable.set(new CombinedDrawable(Theme.dialogs_verifiedDrawable, Theme.dialogs_verifiedCheckDrawable, 0, 0), r12);
        this.statusDrawable.setColor(null);
    L22:
        if (r9 != null) goto L24;
    L26:
        if (r0 == false) goto L33;
        if (r10 == null) goto L33;
        if (this.savedMessages == true) goto L33;
        if (MessagesController.getInstance(this.currentAccount).isPremiumUser(r10) == false) goto L33;
        this.statusDrawable.set(PremiumGradient.getInstance().premiumStarDrawableMini, r12);
        this.statusDrawable.setColor(Integer.valueOf(Theme.getColor(Theme.key_chats_verifiedBackground, this.resourcesProvider)));
    L33:
        this.statusDrawable.set(null, r12);
        this.statusDrawable.setColor(Integer.valueOf(Theme.getColor(Theme.key_chats_verifiedBackground, this.resourcesProvider)));
        goto L34
    L24:
        if (this.savedMessages == true) goto L26;
        this.statusDrawable.set(r9.getDocumentId(), r12);
        this.statusDrawable.setParticles(true, false);
        this.statusDrawable.setColor(Integer.valueOf(Theme.getColor(Theme.key_chats_verifiedBackground, this.resourcesProvider)));
        goto L34
    }

    public ProfileSearchCell useCustomPaints() {
        this.customPaints = true;
        return this;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.statusDrawable != r2) goto L5;
        return true;
    L5:
        if (this.botVerificationDrawable != r2) goto L7;
        return true;
    L7:
        if (super.verifyDrawable(r2) == true) goto L14;
        return false;
    L14:
        return true;
    }
}
