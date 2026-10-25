package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GroupCreateUserCell;

/* loaded from: classes3.dex */
public class GroupCreateSpan extends View {
    private static Paint backPaint;
    private static TextPaint textPaint;
    private AvatarDrawable avatarDrawable;
    private int[] colors;
    private String countryIso2;
    private ContactsController.Contact currentContact;
    private Drawable deleteDrawable;
    private boolean deleting;
    private boolean drawAvatarBackground;
    private ImageReceiver imageReceiver;
    public boolean isFlag;
    private String key;
    private long lastUpdateTime;
    private StaticLayout nameLayout;
    private float progress;
    private RectF rect;
    private Theme.ResourcesProvider resourcesProvider;
    private boolean small;
    private int textWidth;
    private float textX;
    private long uid;

    static {
        textPaint = new TextPaint(1);
        backPaint = new Paint(1);
    }

    public GroupCreateSpan(Context r2, Object r3) {
        this(r2, r3, null);
    }

    public GroupCreateSpan(Context r2, Object r3, ContactsController.Contact r4) {
        this(r2, r3, r4, null);
    }

    public GroupCreateSpan(Context r7, Object r8, ContactsController.Contact r9, Theme.ResourcesProvider r10) {
        this(r7, r8, r9, false, r10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GroupCreateSpan(Context r32, Object r33, ContactsController.Contact r34, boolean r35, Theme.ResourcesProvider r36) {
        super(r32);
        this.rect = new RectF();
        this.colors = new int[8];
        this.drawAvatarBackground = true;
        this.resourcesProvider = r36;
        this.small = r35;
        this.isFlag = false;
        this.currentContact = r34;
        this.deleteDrawable = getResources().getDrawable(R.drawable.delete);
        TextPaint r8 = textPaint;
        if (r35 == false) goto L5;
        float r9 = 13.0f;
    L6:
        r8.setTextSize(AndroidUtilities.dp(r9));
        AvatarDrawable r82 = new AvatarDrawable();
        this.avatarDrawable = r82;
        r82.setTextSize(AndroidUtilities.dp(20.0f));
        boolean r83 = r33 instanceof String;
        if (r83 == false) goto L48;
        String r2 = (String) r33;
        this.avatarDrawable.setScaleSize(0.8f);
        switch(r2.hashCode()) {
            case -1716307998: goto L44;
            case -1359418551: goto L42;
            case -1237460524: goto L39;
            case -1197490811: goto L36;
            case -567451565: goto L33;
            case -318452137: goto L30;
            case -268161860: goto L27;
            case 3029900: goto L24;
            case 3496342: goto L21;
            case 104264043: goto L18;
            case 151051367: goto L15;
            case 1432626128: goto L12;
            default: goto L45;
        };
    L44:
        r2.equals("archived");
    L45:
        this.avatarDrawable.setAvatarType(11);
        this.uid = -9223372036854775801L;
        String r22 = LocaleController.getString(R.string.FilterArchived);
    L46:
        ImageLocation r15 = null;
        TLRPC.Chat r21 = null;
    L71:
        ImageReceiver r4 = new ImageReceiver();
        this.imageReceiver = r4;
        if (r35 == false) goto L74;
        float r14 = 28.0f;
    L75:
        r4.setRoundRadius(ExteraConfig.getAvatarCorners(r14));
        this.imageReceiver.setParentView(this);
        ImageReceiver r42 = this.imageReceiver;
        if (this.drawAvatarBackground == false) goto L78;
        float r142 = 0.0f;
    L79:
        if (r35 == false) goto L81;
        float r16 = 28.0f;
    L82:
        float r12 = AndroidUtilities.dp(r16);
        if (r35 == false) goto L85;
        float r162 = 28.0f;
    L86:
        r42.setImageCoords(r142, 0.0f, r12, AndroidUtilities.dp(r162));
        int r5 = 28;
        if (AndroidUtilities.isTablet() == false) goto L92;
        if (r35 == true) goto L91;
        r5 = 32;
    L91:
        int r3 = AndroidUtilities.dp(398 - r5) / 2;
    L97:
        StaticLayout r222 = new StaticLayout(TextUtils.ellipsize(Emoji.replaceEmoji(r22.replace('\n', ' '), textPaint.getFontMetricsInt(), false), textPaint, r3, TextUtils.TruncateAt.END), textPaint, 1000, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.nameLayout = r222;
        if (r222.getLineCount() <= 0) goto L100;
        this.textWidth = (int) Math.ceil(this.nameLayout.getLineWidth(0));
        this.textX = -this.nameLayout.getLineLeft(0);
    L100:
        if (r83 == false) goto L105;
        if ("premium".equals((String) r33) == false) goto L105;
        this.imageReceiver.setImageBitmap(GroupCreateUserCell.makePremiumUsersDrawable(getContext(), true));
    L110:
        updateColors();
        NotificationCenter.listenEmojiLoading(this);
        return;
    L105:
        if (r83 == true) goto L107;
    L109:
        this.imageReceiver.setImage(r15, "50_50", this.avatarDrawable, 0, null, r21, 1);
        goto L110
    L107:
        if ("miniapps".equals((String) r33) == false) goto L109;
        this.imageReceiver.setImageBitmap(GroupCreateUserCell.makeMiniAppsDrawable(getContext(), true));
        goto L110
    L92:
        Point r43 = AndroidUtilities.displaySize;
        int r44 = Math.min(r43.x, r43.y);
        if (r35 == true) goto L96;
        r5 = 32;
    L96:
        r3 = (r44 - AndroidUtilities.dp(r5 + 132)) / 2;
        goto L97
    L85:
        r162 = 32.0f;
        goto L86
    L81:
        r16 = 32.0f;
        goto L82
    L78:
        r142 = AndroidUtilities.dp(4.0f);
        goto L79
    L74:
        r14 = 32.0f;
        goto L75
    L12:
        if (r2.equals("channels") == false) goto L45;
        this.avatarDrawable.setAvatarType(7);
        this.uid = -9223372036854775805L;
        r22 = LocaleController.getString(R.string.FilterChannels);
        goto L46
    L15:
        if (r2.equals("existing_chats") == false) goto L45;
        this.avatarDrawable.setAvatarType(23);
        this.uid = -9223372036854775800L;
        r22 = LocaleController.getString(R.string.FilterExistingChats);
        goto L46
    L18:
        if (r2.equals("muted") == false) goto L45;
        this.avatarDrawable.setAvatarType(9);
        this.uid = -9223372036854775803L;
        r22 = LocaleController.getString(R.string.FilterMuted);
        goto L46
    L21:
        if (r2.equals("read") == false) goto L45;
        this.avatarDrawable.setAvatarType(10);
        this.uid = -9223372036854775802L;
        r22 = LocaleController.getString(R.string.FilterRead);
        goto L46
    L24:
        if (r2.equals("bots") == false) goto L45;
        this.avatarDrawable.setAvatarType(8);
        this.uid = -9223372036854775804L;
        r22 = LocaleController.getString(R.string.FilterBots);
        goto L46
    L27:
        if (r2.equals("new_chats") == false) goto L45;
        this.avatarDrawable.setAvatarType(24);
        this.uid = -9223372036854775799L;
        r22 = LocaleController.getString(R.string.FilterNewChats);
        goto L46
    L30:
        if (r2.equals("premium") == false) goto L45;
        this.isFlag = true;
        this.avatarDrawable.setColor(Theme.getColor(Theme.key_premiumGradientBackground2, r36));
        r22 = LocaleController.getString(R.string.PrivacyPremium);
        goto L46
    L33:
        if (r2.equals("contacts") == false) goto L45;
        this.avatarDrawable.setAvatarType(4);
        this.uid = Long.MIN_VALUE;
        r22 = LocaleController.getString(R.string.FilterContacts);
        goto L46
    L36:
        if (r2.equals("non_contacts") == false) goto L45;
        this.avatarDrawable.setAvatarType(5);
        this.uid = -9223372036854775807L;
        r22 = LocaleController.getString(R.string.FilterNonContacts);
        goto L46
    L39:
        if (r2.equals("groups") == false) goto L45;
        this.avatarDrawable.setAvatarType(6);
        this.uid = -9223372036854775806L;
        r22 = LocaleController.getString(R.string.FilterGroups);
        goto L46
    L42:
        if (r2.equals("miniapps") == false) goto L45;
        this.isFlag = true;
        this.avatarDrawable.setColor(Theme.getColor(Theme.key_avatar_backgroundBlue, r36), Theme.getColor(Theme.key_avatar_background2Blue, r36));
        r22 = LocaleController.getString(R.string.PrivacyMiniapps);
        goto L46
    L48:
        if ((r33 instanceof TLRPC.User) == false) goto L62;
        TLRPC.User r23 = (TLRPC.User) r33;
        this.uid = r23.id;
        if (UserObject.isReplyUser(r23) == false) goto L54;
        r22 = LocaleController.getString(R.string.RepliesTitle);
        this.avatarDrawable.setScaleSize(0.8f);
        this.avatarDrawable.setAvatarType(12);
    L52:
        TLRPC.Chat r45 = null;
        ImageLocation r143 = null;
    L60:
        r21 = r45;
        r15 = r143;
        goto L71
    L54:
        if (UserObject.isUserSelf(r23) == false) goto L56;
        r22 = LocaleController.getString(R.string.SavedMessages);
        this.avatarDrawable.setScaleSize(0.8f);
        this.avatarDrawable.setAvatarType(1);
        goto L52
    L56:
        this.avatarDrawable.setInfo(r23);
        String r46 = UserObject.getFirstName(r23);
        int r52 = r46.indexOf(32);
        if (r52 < 0) goto L59;
        r46 = r46.substring(0, r52);
    L59:
        r143 = ImageLocation.getForUserOrChat(r23, 1);
        String r30 = r46;
        r45 = r23;
        r22 = r30;
        goto L60
    L62:
        if ((r33 instanceof TLRPC.Chat) == false) goto L65;
        TLRPC.Chat r144 = (TLRPC.Chat) r33;
        this.avatarDrawable.setInfo(r144);
        this.uid = -r144.id;
        r22 = r144.title;
        r15 = ImageLocation.getForUserOrChat(r144, 1);
        r21 = r144;
        goto L71
    L65:
        if ((r33 instanceof TLRPC.TL_help_country) == false) goto L67;
        TLRPC.TL_help_country r24 = (TLRPC.TL_help_country) r33;
        String r18 = LocaleController.getLanguageFlag(r24.iso2);
        String r53 = r24.default_name;
        this.avatarDrawable.setAvatarType(17);
        this.avatarDrawable.setTextSize(AndroidUtilities.dp(24.0f));
        this.avatarDrawable.setInfo(0, r18, null, null);
        this.avatarDrawable.setColor(Theme.multAlpha(Theme.getColor(Theme.key_text_RedRegular, r36), 0.7f));
        AvatarDrawable r47 = this.avatarDrawable;
        this.drawAvatarBackground = false;
        r47.setDrawAvatarBackground(false);
        this.uid = r24.default_name.hashCode();
        this.countryIso2 = r24.iso2;
        r22 = r53;
        goto L46
    L67:
        this.avatarDrawable.setInfo(r34.contact_id, r34.first_name, r34.last_name);
        this.uid = r34.contact_id;
        this.key = r34.key;
        if (TextUtils.isEmpty(r34.first_name) == true) goto L70;
        r22 = r34.first_name;
        goto L46
    L70:
        r22 = r34.last_name;
        goto L46
    L5:
        r9 = 14.0f;
        goto L6
    }

    public void cancelDeleteAnimation() {
        if (this.deleting == true) goto L5;
        return;
    L5:
        this.deleting = false;
        this.lastUpdateTime = System.currentTimeMillis();
        invalidate();
    }

    public ContactsController.Contact getContact() {
        return this.currentContact;
    }

    public String getCountryIso2() {
        return this.countryIso2;
    }

    public String getKey() {
        return this.key;
    }

    public long getUid() {
        return this.uid;
    }

    public boolean isDeleting() {
        return this.deleting;
    }

    @Override // android.view.View
    public void onDraw(Canvas r15) {
        boolean r2 = this.deleting;
        if (r2 == true) goto L5;
    L6:
        if (r2 == false) goto L8;
    L23:
        r15.save();
        RectF r22 = this.rect;
        float r4 = getMeasuredWidth();
        float r6 = 32.0f;
        if (this.small == false) goto L26;
        float r5 = 28.0f;
    L27:
        r22.set(0.0f, 0.0f, r4, AndroidUtilities.dp(r5));
        Paint r23 = backPaint;
        int[] r42 = this.colors;
        int r52 = r42[6];
        float r8 = r42[7] - r52;
        float r10 = this.progress;
        r23.setColor(Color.argb(r52 + ((int) (r8 * r10)), r42[0] + ((int) ((r42[1] - r8) * r10)), r42[2] + ((int) ((r42[3] - r11) * r10)), r42[4] + ((int) ((r42[5] - r12) * r10))));
        RectF r24 = this.rect;
        if (this.small == false) goto L30;
        float r43 = 28.0f;
    L31:
        float r44 = ExteraConfig.getAvatarCorners(r43);
        if (this.small == false) goto L34;
        float r53 = 28.0f;
    L35:
        r15.drawRoundRect(r24, r44, ExteraConfig.getAvatarCorners(r53), backPaint);
        if (this.progress == 1.0f) goto L39;
        this.imageReceiver.draw(r15);
    L39:
        if (this.progress == 0.0f) goto L70;
        backPaint.setColor(this.avatarDrawable.getColor());
        backPaint.setAlpha((int) ((this.progress * 255.0f) * (Color.alpha(r2) / 255.0f)));
        if (this.small == false) goto L43;
        float r25 = 28.0f;
    L44:
        float r45 = AndroidUtilities.dp(r25);
        if (this.small == false) goto L47;
        float r26 = 28.0f;
    L48:
        float r54 = AndroidUtilities.dp(r26);
        if (this.small == false) goto L51;
        float r27 = 28.0f;
    L52:
        float r28 = ExteraConfig.getAvatarCorners(r27);
        if (this.small == false) goto L55;
        r6 = 28.0f;
    L55:
        r15.drawRoundRect(0.0f, 0.0f, r45, r54, r28, ExteraConfig.getAvatarCorners(r6), backPaint);
        r15.save();
        r15.rotate((1.0f - this.progress) * 45.0f, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        Drawable r29 = this.deleteDrawable;
        float r46 = 11.0f;
        if (this.small == false) goto L58;
        float r3 = 9.0f;
    L59:
        int r32 = AndroidUtilities.dp(r3);
        if (this.small == false) goto L62;
        r46 = 9.0f;
    L62:
        int r47 = AndroidUtilities.dp(r46);
        float r62 = 21.0f;
        if (this.small == false) goto L65;
        float r55 = 19.0f;
    L66:
        int r56 = AndroidUtilities.dp(r55);
        if (this.small == false) goto L69;
        r62 = 19.0f;
    L69:
        r29.setBounds(r32, r47, r56, AndroidUtilities.dp(r62));
        this.deleteDrawable.setAlpha((int) (this.progress * 255.0f));
        this.deleteDrawable.draw(r15);
        r15.restore();
        goto L70
    L65:
        r55 = 21.0f;
        goto L66
    L58:
        r3 = 11.0f;
        goto L59
    L51:
        r27 = 32.0f;
        goto L52
    L47:
        r26 = 32.0f;
        goto L48
    L43:
        r25 = 32.0f;
    L70:
        float r210 = this.textX;
        if (this.small == false) goto L73;
        int r33 = 26;
    L74:
        float r211 = r210 + AndroidUtilities.dp(r33 + 9);
        if (this.small == false) goto L77;
        float r34 = 6.0f;
    L78:
        r15.translate(r211, AndroidUtilities.dp(r34));
        textPaint.setColor(ColorUtils.blendARGB(Theme.getColor(Theme.key_groupcreate_spanText, this.resourcesProvider), Theme.getColor(Theme.key_avatar_text, this.resourcesProvider), this.progress));
        this.nameLayout.draw(r15);
        r15.restore();
        return;
    L77:
        r34 = 8.0f;
        goto L78
    L73:
        r33 = 32;
        goto L74
    L34:
        r53 = 32.0f;
        goto L35
    L30:
        r43 = 32.0f;
        goto L31
    L26:
        r5 = 32.0f;
        goto L27
    L8:
        if (this.progress == 0.0f) goto L23;
    L9:
        long r48 = System.currentTimeMillis() - this.lastUpdateTime;
        if (r48 >= 0) goto L12;
    L13:
        r48 = 17;
    L14:
        boolean r212 = this.deleting;
        float r63 = this.progress;
        if (r212 == false) goto L19;
        float r64 = r63 + (r48 / 120.0f);
        this.progress = r64;
        if (r64 < 1.0f) goto L22;
        this.progress = 1.0f;
    L22:
        invalidate();
        goto L23
    L19:
        float r65 = r63 - (r48 / 120.0f);
        this.progress = r65;
        if (r65 >= 0.0f) goto L22;
        this.progress = 0.0f;
        goto L22
    L12:
        if (r48 <= 17) goto L14;
    L5:
        if (this.progress != 1.0f) goto L9;
        goto L6
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        r3.setText(this.nameLayout.getText());
        if (isDeleting() == false) goto L6;
        r3.addAction(new AccessibilityNodeInfo.AccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK.getId(), LocaleController.getString(R.string.Delete)));
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        if (this.small == false) goto L5;
        int r12 = 20;
    L6:
        int r13 = AndroidUtilities.dp(r12 + 25) + this.textWidth;
        if (this.small == false) goto L9;
        float r22 = 28.0f;
    L10:
        setMeasuredDimension(r13, AndroidUtilities.dp(r22));
        return;
    L9:
        r22 = 32.0f;
        goto L10
    L5:
        r12 = 32;
        goto L6
    }

    public void startDeleteAnimation() {
        if (this.deleting == false) goto L5;
        return;
    L5:
        this.deleting = true;
        this.lastUpdateTime = System.currentTimeMillis();
        invalidate();
    }

    public void updateColors() {
        int r0 = this.avatarDrawable.getColor();
        int r1 = Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider), 0.05f);
        int r2 = Theme.getColor(Theme.key_groupcreate_spanDelete, this.resourcesProvider);
        this.colors[0] = Color.red(r1);
        this.colors[1] = Color.red(r0);
        this.colors[2] = Color.green(r1);
        this.colors[3] = Color.green(r0);
        this.colors[4] = Color.blue(r1);
        this.colors[5] = Color.blue(r0);
        this.colors[6] = Color.alpha(r1);
        this.colors[7] = Color.alpha(r0);
        this.deleteDrawable.setColorFilter(new PorterDuffColorFilter(r2, PorterDuff.Mode.MULTIPLY));
        backPaint.setColor(r1);
    }
}
