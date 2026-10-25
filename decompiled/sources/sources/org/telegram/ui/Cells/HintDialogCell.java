package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CounterView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumGradient;

/* loaded from: classes6.dex */
public class HintDialogCell extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private int backgroundColorKey;
    CheckBox2 checkBox;
    CounterView counterView;
    private int currentAccount;
    private TLRPC.User currentUser;
    private long dialogId;
    private final boolean drawCheckbox;
    private BackupImageView imageView;
    private int lastUnreadCount;
    private Drawable lockDrawable;
    private TextView nameTextView;
    private boolean premiumBlocked;
    private final AnimatedFloat premiumBlockedT;
    private PremiumGradient.PremiumGradientTools premiumGradient;
    private RectF rect;
    private Theme.ResourcesProvider resourcesProvider;
    float showOnlineProgress;
    private boolean showPremiumBlocked;
    private final AnimatedFloat starsBlockedT;
    private long starsPriceBlocked;
    boolean wasDraw;

    /* renamed from: org.telegram.ui.Cells.HintDialogCell$1, reason: invalid class name */
    public class AnonymousClass1 extends TextView {
        final /* synthetic */ HintDialogCell this$0;

        public AnonymousClass1(HintDialogCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r3, TextView.BufferType r4) {
            super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
        }
    }

    public static /* synthetic */ void $r8$lambda$c9TYRgPba4acoFSXn7u_A5dmCFI(HintDialogCell r0, Object[] r1) {
        r0.lambda$showPremiumBlocked$1(r1);
    }

    /* renamed from: $r8$lambda$ign6-O9Te1xxW7m1369bvvBpIa8, reason: not valid java name */
    public static /* synthetic */ void m6957$r8$lambda$ign6O9Te1xxW7m1369bvvBpIa8(HintDialogCell r0, float r1) {
        r0.lambda$new$0(r1);
    }

    public HintDialogCell(Context r11, boolean r12, Theme.ResourcesProvider r13) {
        super(r11);
        this.avatarDrawable = new AvatarDrawable();
        this.rect = new RectF();
        this.currentAccount = UserConfig.selectedAccount;
        CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.premiumBlockedT = new AnimatedFloat(this, 0, 350, r7);
        this.starsBlockedT = new AnimatedFloat(this, 0, 350, r7);
        this.backgroundColorKey = Theme.key_windowBackgroundWhite;
        this.drawCheckbox = r12;
        BackupImageView r10 = new BackupImageView(r11);
        this.imageView = r10;
        r10.setRoundRadius(ExteraConfig.getAvatarCorners(54.0f));
        addView(this.imageView, LayoutHelper.createFrame(54, 54.0f, 49, 0.0f, 7.0f, 0.0f, 0.0f));
        AnonymousClass1 r102 = new AnonymousClass1(this, r11);
        this.nameTextView = r102;
        NotificationCenter.listenEmojiLoading(r102);
        this.nameTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r13));
        this.nameTextView.setTextSize(1, 12.0f);
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setGravity(49);
        this.nameTextView.setLines(1);
        this.nameTextView.setEllipsize(TextUtils.TruncateAt.END);
        addView(this.nameTextView, LayoutHelper.createFrame(-1, -2.0f, 51, 6.0f, 64.0f, 6.0f, 0.0f));
        CounterView r103 = new CounterView(r11, r13);
        this.counterView = r103;
        addView(r103, LayoutHelper.createFrame(-1, 28.0f, 48, 0.0f, 4.0f, 0.0f, 0.0f));
        this.counterView.setColors(Theme.key_chats_unreadCounterText, Theme.key_chats_unreadCounter);
        this.counterView.setGravity(5);
        if (r12 == false) goto L6;
        CheckBox2 r104 = new CheckBox2(r11, 21, r13);
        this.checkBox = r104;
        r104.setColor(Theme.key_dialogRoundCheckBox, Theme.key_dialogBackground, Theme.key_dialogRoundCheckBoxCheck);
        this.checkBox.setDrawUnchecked(false);
        this.checkBox.setDrawBackgroundAsArc(4);
        this.checkBox.setProgressDelegate(new HintDialogCell$$ExternalSyntheticLambda1(this));
        addView(this.checkBox, LayoutHelper.createFrame(24, 24.0f, 49, 19.0f, 42.0f, 0.0f, 0.0f));
        this.checkBox.setChecked(false, false);
        setWillNotDraw(false);
        return;
    }

    private /* synthetic */ void lambda$new$0(float r2) {
        float r0 = 1.0f - (this.checkBox.getProgress() * 0.143f);
        this.imageView.setScaleX(r0);
        this.imageView.setScaleY(r0);
        invalidate();
    }

    private /* synthetic */ void lambda$showPremiumBlocked$1(Object[] r1) {
        updatePremiumBlocked(true);
    }

    private void updatePremiumBlocked(boolean r7) {
        if (this.showPremiumBlocked == true) goto L5;
    L7:
        TL_account.RequirementToContact r0 = null;
    L9:
        if (this.premiumBlocked == DialogObject.isPremiumBlocked(r0)) goto L11;
    L14:
        this.premiumBlocked = DialogObject.isPremiumBlocked(r0);
        this.starsPriceBlocked = DialogObject.getMessagesStarsPrice(r0);
        if (r7 == true) goto L21;
        this.premiumBlockedT.set(this.premiumBlocked, true);
        AnimatedFloat r72 = this.starsBlockedT;
        if (this.starsPriceBlocked <= 0) goto L19;
        boolean r02 = true;
    L20:
        r72.set(r02, true);
        goto L21
    L19:
        r02 = false;
    L21:
        invalidate();
        return;
    L11:
        if (this.starsPriceBlocked != DialogObject.getMessagesStarsPrice(r0)) goto L14;
        return;
    L5:
        if (this.currentUser == null) goto L7;
        r0 = MessagesController.getInstance(this.currentAccount).isUserContactBlocked(this.currentUser.id);
        goto L9
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r19, View r20, long r21) {
        boolean r2 = super.drawChild(r19, r20, r21);
        if (r20 == this.imageView) goto L5;
    L52:
        return r2;
    L5:
        if (this.premiumBlocked == true) goto L17;
        TLRPC.User r3 = this.currentUser;
        if (r3 == null) goto L17;
        if (r3.bot == true) goto L17;
        TLRPC.UserStatus r32 = r3.status;
        if (r32 == null) goto L15;
        if (r32.expires <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L15;
    L16:
        boolean r33 = true;
    L19:
        if (this.wasDraw == true) goto L25;
        if (r33 == false) goto L22;
        float r6 = 1.0f;
    L23:
        this.showOnlineProgress = r6;
        goto L25
    L22:
        r6 = 0.0f;
    L25:
        if (r33 == false) goto L32;
        float r9 = this.showOnlineProgress;
        if (r9 == 1.0f) goto L32;
        float r92 = r9 + 0.10666667f;
        this.showOnlineProgress = r92;
        if (r92 <= 1.0f) goto L31;
        this.showOnlineProgress = 1.0f;
    L31:
        invalidate();
    L39:
        float r34 = this.premiumBlockedT.set(this.premiumBlocked);
        if (r34 <= 0.0f) goto L49;
        float r62 = (r20.getY() + (r20.getHeight() / 2.0f)) + AndroidUtilities.dp(18.0f);
        float r93 = (r20.getX() + (r20.getWidth() / 2.0f)) + AndroidUtilities.dp(18.0f);
        r19.save();
        Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(this.backgroundColorKey, this.resourcesProvider));
        r19.drawCircle(r93, r62, AndroidUtilities.dp(11.33f) * r34, Theme.dialogs_onlineCirclePaint);
        if (this.premiumGradient != null) goto L44;
        this.premiumGradient = new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradient1, Theme.key_premiumGradient2, -1, -1, -1, this.resourcesProvider);
    L44:
        this.premiumGradient.gradientMatrix((int) (r93 - AndroidUtilities.dp(10.0f)), (int) (r62 - AndroidUtilities.dp(10.0f)), (int) (AndroidUtilities.dp(10.0f) + r93), (int) (AndroidUtilities.dp(10.0f) + r62), 0.0f, 0.0f);
        r19.drawCircle(r93, r62, AndroidUtilities.dp(10.0f) * r34, this.premiumGradient.paint);
        if (this.lockDrawable != null) goto L47;
        Drawable r4 = getContext().getResources().getDrawable(R.drawable.msg_mini_lock2).mutate();
        this.lockDrawable = r4;
        r4.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
    L47:
        this.lockDrawable.setBounds((int) (r93 - (((r4.getIntrinsicWidth() / 2.0f) * 0.875f) * r34)), (int) (r62 - (((this.lockDrawable.getIntrinsicHeight() / 2.0f) * 0.875f) * r34)), (int) (r93 + (((this.lockDrawable.getIntrinsicWidth() / 2.0f) * 0.875f) * r34)), (int) (r62 + (((this.lockDrawable.getIntrinsicHeight() / 2.0f) * 0.875f) * r34)));
        this.lockDrawable.setAlpha((int) (r34 * 255.0f));
        this.lockDrawable.draw(r19);
        r19.restore();
    L51:
        this.wasDraw = true;
        goto L52
    L49:
        if (this.showOnlineProgress == 0.0f) goto L51;
        int r35 = AndroidUtilities.dp(53.0f);
        int r42 = AndroidUtilities.dp(59.0f);
        r19.save();
        float r63 = this.showOnlineProgress;
        float r43 = r42;
        float r36 = r35;
        r19.scale(r63, r63, r43, r36);
        Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(this.backgroundColorKey));
        r19.drawCircle(r43, r36, AndroidUtilities.dp(7.0f), Theme.dialogs_onlineCirclePaint);
        Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_chats_onlineCircle));
        r19.drawCircle(r43, r36, AndroidUtilities.dp(5.0f), Theme.dialogs_onlineCirclePaint);
        r19.restore();
    L32:
        if (r33 == true) goto L39;
        float r37 = this.showOnlineProgress;
        if (r37 == 0.0f) goto L39;
        float r38 = r37 - 0.10666667f;
        this.showOnlineProgress = r38;
        if (r38 >= 0.0f) goto L38;
        this.showOnlineProgress = 0.0f;
    L38:
        invalidate();
    L15:
        if (MessagesController.getInstance(this.currentAccount).onlinePrivacy.containsKey(Long.valueOf(this.currentUser.id)) == true) goto L16;
    L17:
        r33 = false;
        goto L19
    }

    public long getDialogId() {
        return this.dialogId;
    }

    public boolean isBlocked() {
        return this.premiumBlocked;
    }

    @Override // android.view.View
    public void onDraw(Canvas r14) {
        if (this.drawCheckbox == false) goto L6;
        int r0 = this.imageView.getLeft() + (this.imageView.getMeasuredWidth() / 2);
        int r1 = this.imageView.getTop() + (this.imageView.getMeasuredHeight() / 2);
        Theme.checkboxSquare_checkPaint.setColor(Theme.getColor(Theme.key_dialogRoundCheckBox));
        Theme.checkboxSquare_checkPaint.setAlpha((int) (this.checkBox.getProgress() * 255.0f));
        float r2 = this.imageView.getScaleX();
        float r10 = Math.max(0.0f, ((this.imageView.getImageReceiver().getRoundRadius()[0] * r2) + AndroidUtilities.dp(28.0f)) - ((this.imageView.getMeasuredWidth() / 2.0f) * r2));
        r14.drawRoundRect(r0 - AndroidUtilities.dp(28.0f), r1 - AndroidUtilities.dp(28.0f), r0 + AndroidUtilities.dp(28.0f), r1 + AndroidUtilities.dp(28.0f), r10, r10, Theme.checkboxSquare_checkPaint);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(86.0f), TLObject.FLAG_30));
        this.counterView.counterDrawable.horizontalPadding = AndroidUtilities.dp(13.0f);
    }

    public void setChecked(boolean r2, boolean r3) {
        if (this.drawCheckbox == false) goto L6;
        this.checkBox.setChecked(r2, r3);
        return;
    }

    public void setColors(int r3, int r4) {
        this.nameTextView.setTextColor(Theme.getColor(r3, this.resourcesProvider));
        this.backgroundColorKey = r4;
        this.checkBox.setColor(Theme.key_dialogRoundCheckBox, r4, Theme.key_dialogRoundCheckBoxCheck);
    }

    public void setDialog(long r5, boolean r7, CharSequence r8) {
        if (this.dialogId == r5) goto L5;
        this.wasDraw = false;
        invalidate();
    L5:
        this.dialogId = r5;
        boolean r0 = DialogObject.isUserDialog(r5);
        int r2 = this.currentAccount;
        if (r0 == false) goto L15;
        TLRPC.User r52 = MessagesController.getInstance(r2).getUser(Long.valueOf(r5));
        this.currentUser = r52;
        if (r8 == null) goto L10;
        this.nameTextView.setText(r8);
    L14:
        this.avatarDrawable.setInfo(this.currentAccount, this.currentUser);
        this.imageView.setForUserOrChat(this.currentUser, this.avatarDrawable);
    L23:
        updatePremiumBlocked(false);
        if (r7 == false) goto L27;
        update(0);
        return;
    L27:
        return;
    L10:
        TextView r6 = this.nameTextView;
        if (r52 == null) goto L13;
        r6.setText(UserObject.getFirstName(r52));
        goto L14
    L13:
        r6.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L14
    L15:
        TLRPC.Chat r53 = MessagesController.getInstance(r2).getChat(Long.valueOf(-r5));
        if (r8 == null) goto L18;
        this.nameTextView.setText(r8);
    L22:
        this.avatarDrawable.setInfo(this.currentAccount, r53);
        this.currentUser = null;
        this.imageView.setForUserOrChat(r53, this.avatarDrawable);
        goto L23
    L18:
        TextView r62 = this.nameTextView;
        if (r53 == null) goto L21;
        r62.setText(r53.title);
        goto L22
    L21:
        r62.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L22
    }

    public void showPremiumBlocked() {
        if (this.showPremiumBlocked == false) goto L5;
        return;
    L5:
        this.showPremiumBlocked = true;
        NotificationCenter.getInstance(this.currentAccount).listen(this, NotificationCenter.userIsPremiumBlockedUpadted, new HintDialogCell$$ExternalSyntheticLambda0(this));
    }

    public void update() {
        boolean r0 = DialogObject.isUserDialog(this.dialogId);
        int r1 = this.currentAccount;
        if (r0 == false) goto L5;
        TLRPC.User r02 = MessagesController.getInstance(r1).getUser(Long.valueOf(this.dialogId));
        this.currentUser = r02;
        this.avatarDrawable.setInfo(this.currentAccount, r02);
    L6:
        updatePremiumBlocked(true);
        return;
    L5:
        TLRPC.Chat r03 = MessagesController.getInstance(r1).getChat(Long.valueOf(-this.dialogId));
        this.avatarDrawable.setInfo(this.currentAccount, r03);
        this.currentUser = null;
        goto L6
    }

    public void update(int r4) {
        if ((MessagesController.UPDATE_MASK_STATUS & r4) != 0) goto L5;
    L7:
        if (r4 != 0) goto L9;
    L13:
        TLRPC.Dialog r42 = MessagesController.getInstance(this.currentAccount).dialogs_dict.get(this.dialogId);
        if (r42 == null) goto L21;
        int r43 = r42.unread_count;
        if (r43 == 0) goto L21;
        if (this.lastUnreadCount == r43) goto L23;
        this.lastUnreadCount = r43;
        this.counterView.setCount(r43, this.wasDraw);
        return;
    L23:
        return;
    L21:
        this.lastUnreadCount = 0;
        this.counterView.setCount(0, this.wasDraw);
        return;
    L9:
        if ((MessagesController.UPDATE_MASK_READ_DIALOG_MESSAGE & r4) != 0) goto L13;
        if ((r4 & MessagesController.UPDATE_MASK_NEW_MESSAGE) != 0) goto L13;
        return;
    L5:
        if (this.currentUser == null) goto L7;
        this.currentUser = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.currentUser.id));
        this.imageView.invalidate();
        invalidate();
        goto L7
    }
}
