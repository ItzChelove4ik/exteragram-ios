package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.view.View;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.ui.TextPaint;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;

/* loaded from: classes6.dex */
public class DialogMeUrlCell extends BaseCell {
    private AvatarDrawable avatarDrawable;
    private ImageReceiver avatarImage;
    private int avatarTop;
    private int currentAccount;
    private boolean drawNameLock;
    private boolean drawVerified;
    private boolean isSelected;
    private StaticLayout messageLayout;
    private int messageLeft;
    private int messageTop;
    private StaticLayout nameLayout;
    private int nameLeft;
    private int nameLockLeft;
    private int nameLockTop;
    private int nameMuteLeft;
    private TLRPC.RecentMeUrl recentMeUrl;
    public boolean useSeparator;

    public DialogMeUrlCell(Context r2) {
        super(r2);
        this.avatarImage = new ImageReceiver(this);
        this.avatarDrawable = new AvatarDrawable();
        this.messageTop = AndroidUtilities.dp(40.0f);
        this.avatarTop = AndroidUtilities.dp(10.0f);
        this.currentAccount = UserConfig.selectedAccount;
        Theme.createDialogsResources(r2);
        this.avatarImage.setRoundRadius(ExteraConfig.getAvatarCorners(52.0f));
    }

    public void buildLayout() {
        TextPaint r5 = Theme.dialogs_namePaint[0];
        TextPaint r11 = Theme.dialogs_messagePaint[0];
        this.drawNameLock = false;
        this.drawVerified = false;
        TLRPC.RecentMeUrl r0 = this.recentMeUrl;
        if ((r0 instanceof TLRPC.TL_recentMeUrlChat) == false) goto L10;
        TLRPC.Chat r02 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.recentMeUrl.chat_id));
        this.drawVerified = r02.verified;
        if (LocaleController.isRTL == true) goto L7;
        this.nameLockLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
        this.nameLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline + 4);
    L8:
        String r3 = r02.title;
        this.avatarDrawable.setInfo(this.currentAccount, r02);
        this.avatarImage.setForUserOrChat(r02, this.avatarDrawable, this.recentMeUrl);
    L53:
        String r12 = MessagesController.getInstance(this.currentAccount).linkPrefix + "/" + this.recentMeUrl.url;
        if (TextUtils.isEmpty(r3) == false) goto L57;
        r3 = LocaleController.getString(R.string.HiddenName);
    L57:
        if (LocaleController.isRTL == true) goto L60;
        int r03 = getMeasuredWidth() - this.nameLeft;
        int r4 = AndroidUtilities.dp(14.0f);
    L59:
        int r04 = r03 - r4;
        if (this.drawNameLock == false) goto L65;
        r04 = r04 - (AndroidUtilities.dp(4.0f) + Theme.dialogs_lockDrawable.getIntrinsicWidth());
    L65:
        if (this.drawVerified == false) goto L69;
        int r42 = AndroidUtilities.dp(6.0f) + Theme.dialogs_verifiedDrawable.getIntrinsicWidth();
        r04 = r04 - r42;
        if (LocaleController.isRTL == false) goto L69;
        this.nameLeft += r42;
    L69:
        int r6 = Math.max(AndroidUtilities.dp(12.0f), r04);
        StaticLayout r32 = new StaticLayout(TextUtils.ellipsize(r3.replace('\n', ' '), r5, r6 - AndroidUtilities.dp(12.0f), TextUtils.TruncateAt.END), r5, r6, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L76
        int r33 = r6;
        this.nameLayout = r32;     // Catch: Exception -> L74
    L79:
        int r05 = getMeasuredWidth() - AndroidUtilities.dp(AndroidUtilities.leftBaseline + 16);
        if (LocaleController.isRTL == true) goto L86;
        this.messageLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
        if (AndroidUtilities.isTablet() == false) goto L84;
        float r43 = 13.0f;
    L85:
        int r44 = AndroidUtilities.dp(r43);
    L91:
        this.avatarImage.setImageCoords(r44, this.avatarTop, AndroidUtilities.dp(52.0f), AndroidUtilities.dp(52.0f));
        int r9 = Math.max(AndroidUtilities.dp(12.0f), r05);
        this.messageLayout = new StaticLayout(TextUtils.ellipsize(r12, r11, r9 - AndroidUtilities.dp(12.0f), TextUtils.TruncateAt.END), r11, r9, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L94
    L96:
        boolean r06 = LocaleController.isRTL;
        StaticLayout r45 = this.nameLayout;
        if (r06 == true) goto L99;
        if (r45 != null) goto L121;
    L130:
        StaticLayout r07 = this.messageLayout;
        if (r07 != null) goto L133;
        return;
    L133:
        if (r07.getLineCount() > 0) goto L135;
        return;
    L135:
        if (this.messageLayout.getLineRight(0) != r9) goto L153;
        double r2 = Math.ceil(this.messageLayout.getLineWidth(0));
        double r46 = r9;
        if (r2 >= r46) goto L154;
        this.messageLeft = (int) (this.messageLeft - (r46 - r2));
        return;
    L154:
        return;
    L153:
        return;
    L121:
        if (r45.getLineCount() <= 0) goto L130;
        float r08 = this.nameLayout.getLineRight(0);
        if (r08 != r33) goto L128;
        double r47 = Math.ceil(this.nameLayout.getLineWidth(0));
        double r62 = r33;
        if (r47 >= r62) goto L128;
        this.nameLeft = (int) (this.nameLeft - (r62 - r47));
    L128:
        if (this.drawVerified == false) goto L130;
        this.nameMuteLeft = (int) ((this.nameLeft + r08) + AndroidUtilities.dp(6.0f));
        goto L130
    L99:
        if (r45 != null) goto L101;
    L110:
        StaticLayout r34 = this.messageLayout;
        if (r34 != null) goto L113;
        return;
    L113:
        if (r34.getLineCount() > 0) goto L115;
        return;
    L115:
        if (this.messageLayout.getLineLeft(0) != 0.0f) goto L148;
        double r22 = Math.ceil(this.messageLayout.getLineWidth(0));
        double r48 = r9;
        if (r22 >= r48) goto L149;
        this.messageLeft = (int) (this.messageLeft + (r48 - r22));
        return;
    L149:
        return;
    L148:
        return;
    L101:
        if (r45.getLineCount() <= 0) goto L110;
        float r49 = this.nameLayout.getLineLeft(0);
        double r52 = Math.ceil(this.nameLayout.getLineWidth(0));
        if (this.drawVerified == false) goto L106;
        this.nameMuteLeft = (int) (((this.nameLeft + (r33 - r52)) - AndroidUtilities.dp(6.0f)) - Theme.dialogs_verifiedDrawable.getIntrinsicWidth());
    L106:
        if (r49 != 0.0f) goto L110;
        double r35 = r33;
        if (r52 >= r35) goto L110;
        this.nameLeft = (int) (this.nameLeft + (r35 - r52));
    L94:
        e = move-exception;
        FileLog.e(e);
        goto L96
    L84:
        r43 = 9.0f;
        goto L85
    L86:
        this.messageLeft = AndroidUtilities.dp(16.0f);
        int r410 = getMeasuredWidth();
        if (AndroidUtilities.isTablet() == false) goto L89;
        float r53 = 65.0f;
    L90:
        r44 = r410 - AndroidUtilities.dp(r53);
        goto L91
    L89:
        r53 = 61.0f;
    L74:
        e = e;
    L78:
        FileLog.e(e);
    L76:
        e = e;
        r33 = r6;
        goto L78
    L60:
        r03 = getMeasuredWidth() - this.nameLeft;
        r4 = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
        goto L59
    L7:
        this.nameLockLeft = getMeasuredWidth() - AndroidUtilities.dp(AndroidUtilities.leftBaseline);
        this.nameLeft = AndroidUtilities.dp(14.0f);
        goto L8
    L10:
        if ((r0 instanceof TLRPC.TL_recentMeUrlUser) == false) goto L25;
        TLRPC.User r09 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.recentMeUrl.user_id));
        if (LocaleController.isRTL == true) goto L14;
        this.nameLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
    L15:
        if (r09 != null) goto L17;
    L23:
        r3 = UserObject.getUserName(r09);
        this.avatarDrawable.setInfo(this.currentAccount, r09);
        this.avatarImage.setForUserOrChat(r09, this.avatarDrawable, this.recentMeUrl);
        goto L53
    L17:
        if (r09.bot == false) goto L22;
        this.nameLockTop = AndroidUtilities.dp(16.5f);
        if (LocaleController.isRTL == true) goto L21;
        this.nameLockLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
        this.nameLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline + 4);
        goto L22
    L21:
        this.nameLockLeft = getMeasuredWidth() - AndroidUtilities.dp(AndroidUtilities.leftBaseline);
        this.nameLeft = AndroidUtilities.dp(14.0f);
    L22:
        this.drawVerified = r09.verified;
        goto L23
    L14:
        this.nameLeft = AndroidUtilities.dp(14.0f);
        goto L15
    L25:
        if ((r0 instanceof TLRPC.TL_recentMeUrlStickerSet) == false) goto L32;
        if (LocaleController.isRTL == true) goto L29;
        this.nameLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
    L30:
        r3 = this.recentMeUrl.set.set.title;
        this.avatarDrawable.setInfo(5, r3, null);
        this.avatarImage.setImage(ImageLocation.getForDocument(this.recentMeUrl.set.cover), null, this.avatarDrawable, null, this.recentMeUrl, 0);
        goto L53
    L29:
        this.nameLeft = AndroidUtilities.dp(14.0f);
        goto L30
    L32:
        if ((r0 instanceof TLRPC.TL_recentMeUrlChatInvite) == false) goto L46;
        if (LocaleController.isRTL == true) goto L36;
        this.nameLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
    L37:
        TLRPC.ChatInvite r010 = this.recentMeUrl.chat_invite;
        TLRPC.Chat r36 = r010.chat;
        if (r36 == null) goto L40;
        this.avatarDrawable.setInfo(this.currentAccount, r36);
        TLRPC.RecentMeUrl r011 = this.recentMeUrl;
        TLRPC.Chat r37 = r011.chat_invite.chat;
        String r63 = r37.title;
        this.drawVerified = r37.verified;
        this.avatarImage.setForUserOrChat(r37, this.avatarDrawable, r011);
        r3 = r63;
    L42:
        if (LocaleController.isRTL == true) goto L44;
        this.nameLockLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
        this.nameLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline + 4);
        goto L53
    L44:
        this.nameLockLeft = getMeasuredWidth() - AndroidUtilities.dp(AndroidUtilities.leftBaseline);
        this.nameLeft = AndroidUtilities.dp(14.0f);
        goto L53
    L40:
        String r012 = r010.title;
        this.avatarDrawable.setInfo(5, r012, null);
        this.avatarImage.setImage(ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(this.recentMeUrl.chat_invite.photo.sizes, 50), this.recentMeUrl.chat_invite.photo), "50_50", this.avatarDrawable, null, this.recentMeUrl, 0);
        r3 = r012;
        goto L42
    L36:
        this.nameLeft = AndroidUtilities.dp(14.0f);
        goto L37
    L46:
        if ((r0 instanceof TLRPC.TL_recentMeUrlUnknown) == true) goto L48;
        this.avatarImage.setImage(null, null, this.avatarDrawable, null, r0, 0);
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L53
    L48:
        if (LocaleController.isRTL == true) goto L50;
        this.nameLeft = AndroidUtilities.dp(AndroidUtilities.leftBaseline);
    L51:
        this.avatarImage.setImage(null, null, this.avatarDrawable, null, this.recentMeUrl, 0);
        r3 = "Url";
        goto L53
    L50:
        this.nameLeft = AndroidUtilities.dp(14.0f);
        goto L51
    }

    @Override // org.telegram.ui.Cells.BaseCell, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.avatarImage.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.avatarImage.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r14) {
        if (this.isSelected == false) goto L5;
        r14.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), Theme.dialogs_tabletSeletedPaint);
        Canvas r7 = r14;
    L7:
        if (this.drawNameLock == false) goto L10;
        BaseCell.setDrawableBounds(Theme.dialogs_lockDrawable, this.nameLockLeft, this.nameLockTop);
        Theme.dialogs_lockDrawable.draw(r7);
    L10:
        if (this.nameLayout == null) goto L13;
        r7.save();
        r7.translate(this.nameLeft, AndroidUtilities.dp(13.0f));
        this.nameLayout.draw(r7);
        r7.restore();
    L13:
        if (this.messageLayout == null) goto L21;
        r7.save();
        r7.translate(this.messageLeft, this.messageTop);
        this.messageLayout.draw(r7);     // Catch: Exception -> L17
    L19:
        r7.restore();
    L17:
        e = move-exception;
        FileLog.e(e);
    L21:
        if (this.drawVerified == false) goto L24;
        BaseCell.setDrawableBounds(Theme.dialogs_verifiedDrawable, this.nameMuteLeft, AndroidUtilities.dp(16.5f));
        BaseCell.setDrawableBounds(Theme.dialogs_verifiedCheckDrawable, this.nameMuteLeft, AndroidUtilities.dp(16.5f));
        Theme.dialogs_verifiedDrawable.draw(r7);
        Theme.dialogs_verifiedCheckDrawable.draw(r7);
    L24:
        if (this.useSeparator == true) goto L26;
    L29:
        this.avatarImage.draw(r7);
        return;
    L26:
        if (LocaleController.isRTL == false) goto L28;
        r7.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth() - AndroidUtilities.dp(AndroidUtilities.leftBaseline), getMeasuredHeight() - 1, Theme.dividerPaint);
        goto L29
    L28:
        r7.drawLine(AndroidUtilities.dp(AndroidUtilities.leftBaseline), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
        goto L29
    L5:
        r7 = r14;
        goto L7
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        if (r1 == false) goto L5;
        buildLayout();
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        setMeasuredDimension(View.MeasureSpec.getSize(r2), AndroidUtilities.dp(72.0f) + (this.useSeparator ? 1 : 0));
    }

    public void setDialogSelected(boolean r2) {
        if (this.isSelected == r2) goto L5;
        invalidate();
    L5:
        this.isSelected = r2;
    }

    public void setRecentMeUrl(TLRPC.RecentMeUrl r1) {
        this.recentMeUrl = r1;
        requestLayout();
    }
}
