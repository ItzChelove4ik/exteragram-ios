package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.view.View;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.regdate.RegDateController;
import java.util.ArrayList;
import java.util.Calendar;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AvatarsDrawable;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.Text;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public class UserInfoCell extends View implements NotificationCenter.NotificationCenterDelegate {
    private boolean animating;
    private int backgroundHeight;
    private MessagesController.CommonChatsList commonChats;
    private final int currentAccount;
    private long dialogId;
    private Text footer;
    private final ButtonBounce fullBounce;
    private final RectF fullBounds;
    private final Drawable groupsArrow;
    private final AvatarsDrawable groupsAvatars;
    private final ButtonBounce groupsBounce;
    private final RectF groupsBounds;
    private final Drawable groupsRipple;
    private Row groupsRow;
    private float height;
    private final Theme.ResourcesProvider resourcesProvider;
    private final ArrayList<Row> rows;
    private float rowsKeysWidth;
    private float rowsValuesWidth;
    private float rowsWidth;
    private Text subtitle;
    private final int textColor;
    private boolean timestampSent;
    private Text title;
    private float viewTop;
    private float width;

    public class Row {
        public boolean avatars;
        public final RectF bounds;
        public Text key;
        final /* synthetic */ UserInfoCell this$0;
        public Text value;

        public Row(UserInfoCell r2, CharSequence r3, CharSequence r4, boolean r5) {
            this.this$0 = r2;
            this.bounds = new RectF();
            this.key = new Text(r3, 12.0f);
            this.value = new Text(r4, 12.0f, AndroidUtilities.bold());
            this.avatars = r5;
        }
    }

    /* renamed from: $r8$lambda$YM6n_l-hdmcTOaVmJNI5iXpaJR4, reason: not valid java name */
    public static /* synthetic */ void m7124$r8$lambda$YM6n_lhdmcTOaVmJNI5iXpaJR4(String r0) {
    }

    public UserInfoCell(Context r4, int r5, Theme.ResourcesProvider r6) {
        super(r4);
        this.rows = new ArrayList();
        this.fullBounds = new RectF();
        this.fullBounce = new ButtonBounce(this);
        this.groupsBounds = new RectF();
        this.groupsBounce = new ButtonBounce(this);
        AvatarsDrawable r0 = new AvatarsDrawable(this, false);
        this.groupsAvatars = r0;
        this.timestampSent = false;
        this.currentAccount = r5;
        this.resourcesProvider = r6;
        int r52 = Theme.getColor(Theme.key_chat_serviceText, r6);
        this.textColor = r52;
        Drawable r62 = Theme.createRadSelectorDrawable(822083583, 8, 8);
        this.groupsRipple = r62;
        r62.setCallback(this);
        r0.width = AndroidUtilities.dp(50.0f);
        r0.height = AndroidUtilities.dp(13.0f);
        r0.drawStoriesCircle = false;
        r0.setSize(AndroidUtilities.dp(13.0f));
        r0.setAvatarsTextSize(AndroidUtilities.dp(18.0f));
        Drawable r42 = r4.getResources().getDrawable(R.drawable.msg_mini_forumarrow).mutate();
        this.groupsArrow = r42;
        r42.setColorFilter(new PorterDuffColorFilter(r52, PorterDuff.Mode.SRC_IN));
    }

    private Row addRow(CharSequence r3, CharSequence r4, boolean r5) {
        if (this.rows.isEmpty() == true) goto L5;
        this.height += AndroidUtilities.dp(7.0f);
    L5:
        Row r0 = new Row(this, r3, r4, r5);
        this.rows.add(r0);
        this.height += AndroidUtilities.dp(14.0f);
        this.rowsKeysWidth = Math.max(this.rowsKeysWidth, r0.key.getCurrentWidth());
        float r32 = this.rowsValuesWidth;
        float r42 = r0.value.getCurrentWidth();
        if (r5 == false) goto L8;
        int r52 = AndroidUtilities.dp(38.0f);
    L9:
        this.rowsValuesWidth = Math.max(r32, r42 + r52);
        return r0;
    L8:
        r52 = 0;
        goto L9
    }

    public static String displayDate(String r2) {
        return LocaleController.formatYearMont(getTimestamp(r2), true);
    }

    public static long getTimestamp(String r10) {
        String[] r102 = r10.split("\\.");
        if (r102.length == 2) goto L6;
        return 0;
    L6:
        int r1 = Integer.parseInt(r102[0]);
        int r4 = Integer.parseInt(r102[1]);
        Calendar r3 = Calendar.getInstance();
        r3.set(r4, r1 - 1, 1, 0, 0, 0);
        r3.set(14, 0);
        return r3.getTimeInMillis() / 1000;
    }

    public static boolean isEmpty(TLRPC.PeerSettings r1) {
        if (r1 != null) goto L4;
        return true;
    L4:
        if (r1.phone_country == null) goto L6;
        return false;
    L6:
        if (r1.registration_month == null) goto L13;
        return false;
    L13:
        return true;
    }

    public boolean animating() {
        return this.animating;
    }

    public void applyServiceShaderMatrix() {
        applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, getX(), this.viewTop);
    }

    public void applyServiceShaderMatrix(int r1, int r2, float r3, float r4) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L6;
        r0.applyServiceShaderMatrix(r1, r2, r3, r4);
        return;
    L6:
        Theme.applyServiceShaderMatrix(r1, r2, r3, r4);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        int r0 = 0;
        if (r4 != NotificationCenter.userInfoDidLoad) goto L9;
        long r42 = ((Long) r6[0]).longValue();
        long r02 = this.dialogId;
        if (r42 != r02) goto L25;
        set(r02, MessagesController.getInstance(this.currentAccount).getPeerSettings(this.dialogId));
        return;
    L25:
        return;
    L9:
        if (r4 == NotificationCenter.commonChatsLoaded) goto L11;
        return;
    L11:
        if (((Long) r6[0]).longValue() != this.dialogId) goto L27;
        MessagesController.CommonChatsList r43 = MessagesController.getInstance(this.currentAccount).getCommonChats(this.dialogId);
        this.commonChats = r43;
        int r44 = r43.getCount();
        Row r52 = this.groupsRow;
        if (r52 == null) goto L21;
        if (r44 <= 0) goto L21;
        r52.value = new Text(LocaleController.formatPluralString("Groups", r44, new Object[0]), 12.0f, AndroidUtilities.bold());
        this.groupsAvatars.setCount(Math.min(3, this.commonChats.chats.size()));
    L17:
        int r45 = Math.min(3, this.commonChats.chats.size());
        AvatarsDrawable r53 = this.groupsAvatars;
        if (r0 >= r45) goto L20;
        r53.setObject(r0, this.currentAccount, this.commonChats.chats.get(r0));
        r0 = r0 + 1;
        goto L17
    L20:
        r53.commitTransition(true);
    L22:
        invalidate();
        return;
    L21:
        set(this.dialogId, MessagesController.getInstance(this.currentAccount).getPeerSettings(this.dialogId));
        requestLayout();
        goto L22
    }

    public boolean hasGradientService() {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        return r0.hasGradientService();
    L7:
        return Theme.hasGradientService();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.userInfoDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.commonChatsLoaded);
        this.groupsAvatars.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.userInfoDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.commonChatsLoaded);
        this.groupsAvatars.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r23) {
        super.onDraw(r23);
        r23.save();
        float r7 = 2.0f;
        float r8 = getWidth() / 2.0f;
        this.fullBounds.set((getWidth() - this.width) / 2.0f, (getHeight() - this.height) / 2.0f, (getWidth() + this.width) / 2.0f, (getHeight() + this.height) / 2.0f);
        float r1 = this.fullBounce.getScale(0.025f);
        r23.scale(r1, r1, this.fullBounds.centerX(), this.fullBounds.centerY());
        applyServiceShaderMatrix();
        r23.drawRoundRect(this.fullBounds, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), Theme.getThemePaint("paintChatActionBackground", this.resourcesProvider));
        if (hasGradientService() == false) goto L5;
        r23.drawRoundRect(this.fullBounds, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), Theme.getThemePaint("paintChatActionBackgroundDarken", this.resourcesProvider));
    L5:
        r23.translate(0.0f, (getHeight() - this.height) / 2.0f);
        float r12 = ((getHeight() - this.height) / 2.0f) + 0.0f;
        float r11 = 14.0f;
        r23.translate(0.0f, AndroidUtilities.dp(14.0f));
        this.title.ellipsize(this.width - AndroidUtilities.dp(32.0f)).draw(r23, r8 - (this.title.getWidth() / 2.0f), this.title.getHeight() / 2.0f, this.textColor, 1.0f);
        r23.translate(0.0f, this.title.getHeight() + AndroidUtilities.dp(3.0f));
        float r122 = (r12 + AndroidUtilities.dp(14.0f)) + (this.title.getHeight() + AndroidUtilities.dp(3.0f));
        this.subtitle.ellipsize(this.width - AndroidUtilities.dp(32.0f)).draw(r23, r8 - (this.subtitle.getWidth() / 2.0f), this.subtitle.getHeight() / 2.0f, this.textColor, 0.7f);
        r23.translate(0.0f, this.subtitle.getHeight() + AndroidUtilities.dp(11.0f));
        float r123 = r122 + (this.subtitle.getHeight() + AndroidUtilities.dp(11.0f));
        float r14 = r8 - (this.rowsWidth / 2.0f);
        int r13 = 0;
    L7:
        if (r13 >= this.rows.size()) goto L23;
        if (r13 <= 0) goto L10;
        r23.translate(0.0f, AndroidUtilities.dp(7.0f));
        r123 = r123 + AndroidUtilities.dp(7.0f);
    L10:
        r23.save();
        Row r3 = this.rows.get(r13);
        float r4 = (this.rowsKeysWidth + r14) - r3.key.getCurrentWidth();
        float r5 = (this.rowsKeysWidth + r14) + AndroidUtilities.dp(7.66f);
        float r18 = r7;
        int r17 = r13;
        float r16 = r11;
        r3.key.ellipsize((r5 - r4) - AndroidUtilities.dp(7.66f)).draw(r23, r4, r3.key.getHeight() / r18, this.textColor, 0.7f);
        RectF r15 = r3.bounds;
        float r52 = r5 + r3.value.getCurrentWidth();
        if (r3.avatars == false) goto L13;
        float r32 = (AndroidUtilities.dp(5.0f) + (this.groupsArrow.getIntrinsicWidth() * 0.8f)) + this.groupsAvatars.getMaxX();
    L14:
        r15.set(r5, r123, r52 + r32, r3.value.getHeight() + r123);
        if (this.groupsRow != r3) goto L19;
        this.groupsBounds.set(r3.bounds);
        this.groupsBounds.inset(-AndroidUtilities.dp(4.0f), -AndroidUtilities.dp(r18));
        float r19 = this.groupsBounce.getScale(0.025f);
        r23.scale(r19, r19, this.groupsBounds.centerX(), r3.value.getHeight() / r18);
        Drawable r110 = this.groupsRipple;
        if (r110 == null) goto L19;
        RectF r33 = this.groupsBounds;
        r110.setBounds((int) r33.left, (int) (r33.top - r123), (int) r33.right, (int) (r33.bottom - r123));
        this.groupsRipple.draw(r23);
    L19:
        r3.value.ellipsize((((this.width / r18) + r8) - AndroidUtilities.dp(8.0f)) - r5).draw(r23, r5, r3.value.getHeight() / r18, this.textColor, 1.0f);
        if (r3.avatars == false) goto L22;
        r23.save();
        r23.translate((r5 + r3.value.getCurrentWidth()) + AndroidUtilities.dp(4.0f), AndroidUtilities.dp(1.0f));
        this.groupsAvatars.onDraw(r23);
        r23.translate(this.groupsAvatars.getMaxX() + AndroidUtilities.dp(1.0f), AndroidUtilities.dp(13.0f) / r18);
        this.groupsArrow.setBounds(0, (int) (((-r1.getIntrinsicHeight()) * 0.8f) / r18), (int) (this.groupsArrow.getIntrinsicWidth() * 0.8f), (int) ((this.groupsArrow.getIntrinsicHeight() * 0.8f) / r18));
        this.groupsArrow.draw(r23);
        r23.restore();
    L22:
        r23.restore();
        r23.translate(0.0f, AndroidUtilities.dp(r16));
        r123 = r123 + AndroidUtilities.dp(r16);
        r13 = r17 + 1;
        r11 = r16;
        r7 = r18;
        goto L7
    L13:
        r32 = 0.0f;
        goto L14
    L23:
        float r182 = r7;
        if (this.footer == null) goto L29;
        r23.translate(0.0f, AndroidUtilities.dp(12.0f));
        boolean r111 = this.footer.isMultiline();
        Text r34 = this.footer;
        if (r111 == false) goto L28;
        r34.draw(r23, r8 - (r34.getWidth() / r182), 0.0f, this.textColor, 0.7f);
        goto L29
    L28:
        r34.ellipsize(this.width - AndroidUtilities.dp(32.0f)).draw(r23, r8 - (this.footer.getWidth() / r182), this.footer.getHeight() / r182, this.textColor, 0.7f);
    L29:
        r23.restore();
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        setMeasuredDimension(View.MeasureSpec.getSize(r2), Math.max(0, ((int) this.height) + AndroidUtilities.dp(16.0f)));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        if (this.groupsRow != null) goto L5;
    L7:
        boolean r0 = false;
    L8:
        if (r0 == false) goto L10;
    L12:
        boolean r3 = false;
    L14:
        if (r8.getAction() != 0) goto L21;
        this.fullBounce.setPressed(r3);
        this.groupsBounce.setPressed(r0);
        Drawable r82 = this.groupsRipple;
        if (r0 == false) goto L18;
        int[] r02 = {android.R.attr.state_pressed, android.R.attr.state_enabled};
    L19:
        r82.setState(r02);
    L42:
        if (this.groupsBounce.isPressed() == false) goto L44;
    L47:
        return true;
    L44:
        if (this.fullBounce.isPressed() == true) goto L47;
        return false;
    L18:
        r02 = new int[0];
        goto L19
    L21:
        if (r8.getAction() != 1) goto L39;
        if (this.fullBounce.isPressed() == false) goto L28;
        BaseFragment r83 = LaunchActivity.getSafeLastFragment();
        if ((r83 instanceof ChatActivity) == false) goto L37;
        ((ChatActivity) r83).openThisProfile();
    L37:
        this.groupsBounce.setPressed(false);
        this.fullBounce.setPressed(false);
        this.groupsRipple.setState(new int[0]);
        goto L42
    L28:
        if (this.groupsBounce.isPressed() == false) goto L37;
        BaseFragment r84 = LaunchActivity.getSafeLastFragment();
        if (r84 == null) goto L36;
        Bundle r03 = new Bundle();
        long r32 = this.dialogId;
        if (r32 < 0) goto L34;
        r03.putLong("user_id", r32);
    L35:
        r03.putBoolean("open_common", true);
        r84.presentFragment(new ProfileActivity(r03));
        goto L36
    L34:
        r03.putLong("chat_id", -r32);
    L36:
        invalidate();
        goto L37
    L39:
        if (r8.getAction() != 3) goto L42;
        this.groupsBounce.setPressed(false);
        this.fullBounce.setPressed(false);
        this.groupsRipple.setState(new int[0]);
        goto L42
    L10:
        if (this.fullBounds.contains(r8.getX(), r8.getY()) == false) goto L12;
        r3 = true;
        goto L14
    L5:
        if (this.groupsBounds.contains(r8.getX(), r8.getY()) == false) goto L7;
        r0 = true;
        goto L8
    }

    public void set(long r19, TLRPC.PeerSettings r21) {
        this.dialogId = r19;
        this.width = 0.0f;
        this.height = 0.0f;
        this.rowsKeysWidth = 0.0f;
        this.rowsValuesWidth = 0.0f;
        this.rows.clear();
        int r8 = (int) (AndroidUtilities.displaySize.x * 0.95f);
        this.height += AndroidUtilities.dp(14.0f);
        Text r1 = new Text(DialogObject.getName(r19), 14.0f, AndroidUtilities.bold());
        this.title = r1;
        this.height += r1.getHeight() + AndroidUtilities.dp(3.0f);
        if (ContactsController.getInstance(this.currentAccount).isContact(r19) == false) goto L5;
        int r4 = R.string.ContactInfoIsContact;
    L6:
        Text r12 = new Text(LocaleController.getString(r4), 14.0f);
        this.subtitle = r12;
        this.height += r12.getHeight() + AndroidUtilities.dp(11.0f);
        if (r21 == null) goto L12;
        if (r21.phone_country == null) goto L12;
        addRow(LocaleController.getString(R.string.ContactInfoPhone), LocaleController.getCountryWithFlag(r21.phone_country, 12, R.string.ContactInfoPhoneFragment), false);
    L12:
        if (r21 == null) goto L20;
        if (r21.registration_month == null) goto L20;
        if (this.timestampSent == true) goto L18;
        RegDateController.getInstance(this.currentAccount).addRegistrationDate(r19, getTimestamp(r21.registration_month), new UserInfoCell$$ExternalSyntheticLambda0());
        this.timestampSent = true;
    L18:
        addRow(LocaleController.getString(R.string.ContactInfoRegistration), displayDate(r21.registration_month), false);
    L20:
        if (r19 >= 0) goto L22;
        TLRPC.User r7 = null;
    L23:
        if (r19 >= 0) goto L25;
        TLRPC.UserFull r122 = null;
    L26:
        if (r122 != null) goto L29;
        if (r19 <= 0) goto L29;
        MessagesController.getInstance(this.currentAccount).loadUserInfo(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r19)), true, 0);
    L29:
        if (r122 == null) goto L38;
        MessagesController.CommonChatsList r13 = MessagesController.getInstance(this.currentAccount).getCommonChats(r19);
        this.commonChats = r13;
        int r14 = Math.max(r122.common_chats_count, r13.getCount());
        if (r14 <= 0) goto L37;
        this.groupsRow = addRow(LocaleController.getString(R.string.ContactInfoCommonGroups), LocaleController.formatPluralString("Groups", r14, new Object[0]), true);
        this.groupsAvatars.setCount(Math.min(3, this.commonChats.chats.size()));
        int r15 = 0;
    L33:
        int r2 = Math.min(3, this.commonChats.chats.size());
        AvatarsDrawable r132 = this.groupsAvatars;
        if (r15 >= r2) goto L36;
        r132.setObject(r15, this.currentAccount, this.commonChats.chats.get(r15));
        r15 = r15 + 1;
        goto L33
    L36:
        r132.commitTransition(true);
    L39:
        this.rowsWidth = (this.rowsKeysWidth + AndroidUtilities.dp(7.66f)) + this.rowsValuesWidth;
        if (r7 != null) goto L42;
    L63:
        this.footer = null;
        this.height += AndroidUtilities.dp(14.0f);
    L64:
        float r16 = Math.max(this.width, this.title.getWidth());
        this.width = r16;
        float r17 = Math.max(r16, this.subtitle.getWidth());
        this.width = r17;
        float r18 = Math.max(r17, this.rowsWidth);
        this.width = r18;
        this.width = Math.min(r18 + AndroidUtilities.dp(32.0f), r8);
        return;
    L42:
        if (r7.verified == true) goto L63;
        if (UserObject.isService(r7.id) == true) goto L63;
        BadgesController r110 = BadgesController.INSTANCE;
        if (r110.isDeveloper(r7) == false) goto L56;
        BadgeDTO r111 = r110.getBadge(r7);
        if (r111 == null) goto L54;
        SpannableStringBuilder r22 = new SpannableStringBuilder("i  ");
        this.footer = new Text(r22, 12.0f);
        r22.setSpan(new AnimatedEmojiSpan(r111.getDocumentId(), this.footer.getFontMetricsInt()), 0, 1, 33);
        if (r111.getText() == null) goto L52;
        String r112 = r111.getText();
    L53:
        r22.append(AndroidUtilities.replaceTags(r112));
        Text r113 = new Text(r22, 12.0f).align(Layout.Alignment.ALIGN_CENTER).multiline(5);
        Point r23 = AndroidUtilities.displaySize;
        this.footer = r113.setMaxWidth(Math.min(r23.x, r23.y) * 0.5f).supportAnimatedEmojis(this);
        this.height += (AndroidUtilities.dp(12.0f) + this.footer.getHeight()) + AndroidUtilities.dp(15.33f);
        goto L64
    L52:
        r112 = LocaleController.getString(R.string.DeveloperCompact);
        goto L53
    L54:
        this.footer = null;
        this.height += AndroidUtilities.dp(14.0f);
        goto L64
    L56:
        if (r7.bot_verification_icon == 0) goto L62;
        if (r122 == null) goto L61;
        TL_bots.botVerification r114 = r122.bot_verification;
        if (r114 == null) goto L61;
        SpannableStringBuilder r24 = new SpannableStringBuilder("i  ");
        this.footer = new Text(r24, 12.0f);
        r24.setSpan(new AnimatedEmojiSpan(r114.icon, this.footer.getFontMetricsInt()), 0, 1, 33);
        r24.append(r114.description);
        Text r115 = new Text(r24, 12.0f).align(Layout.Alignment.ALIGN_CENTER).multiline(5);
        Point r25 = AndroidUtilities.displaySize;
        this.footer = r115.setMaxWidth(Math.min(r25.x, r25.y) * 0.5f).supportAnimatedEmojis(this);
        this.height += (AndroidUtilities.dp(12.0f) + this.footer.getHeight()) + AndroidUtilities.dp(15.33f);
    L61:
        this.footer = null;
        this.height += AndroidUtilities.dp(14.0f);
        goto L64
    L62:
        SpannableStringBuilder r116 = new SpannableStringBuilder("i  ");
        ColoredImageSpan r26 = new ColoredImageSpan(R.drawable.filled_info);
        r26.setScale(0.55f, -0.55f);
        r26.translate(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(-1.0f));
        r116.setSpan(r26, 0, 1, 33);
        r116.append(LocaleController.getString(R.string.ContactInfoNotVerified));
        this.footer = new Text(r116, 12.0f);
        this.height += (AndroidUtilities.dp(12.0f) + this.footer.getHeight()) + AndroidUtilities.dp(15.33f);
        goto L64
    L37:
        this.commonChats = null;
        this.groupsRow = null;
        goto L39
    L38:
        this.commonChats = null;
        this.groupsRow = null;
        goto L39
    L25:
        r122 = MessagesController.getInstance(this.currentAccount).getUserFull(r19);
        goto L26
    L22:
        r7 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r19));
        goto L23
    L5:
        r4 = R.string.ContactInfoIsNotContact;
        goto L6
    }

    public void setAnimating(boolean r1) {
        this.animating = r1;
    }

    public void setVisiblePart(float r3, int r4) {
        if (Math.abs(this.viewTop - r3) <= 0.01f) goto L5;
    L6:
        invalidate();
    L7:
        this.backgroundHeight = r4;
        this.viewTop = r3;
        return;
    L5:
        if (r4 == this.backgroundHeight) goto L7;
        goto L6
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.groupsRipple) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
