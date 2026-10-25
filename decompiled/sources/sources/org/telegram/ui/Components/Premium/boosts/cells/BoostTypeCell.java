package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class BoostTypeCell extends BaseCell {
    public static int TYPE_GIVEAWAY = 0;
    public static int TYPE_PREMIUM = 2;
    public static int TYPE_SPECIFIC_USERS = 1;
    public static int TYPE_STARS = 3;
    private int selectedType;

    static {
    }

    public BoostTypeCell(Context r1, Theme.ResourcesProvider r2) {
        super(r1, r2);
        this.titleTextView.setTypeface(AndroidUtilities.bold());
    }

    public int getSelectedType() {
        return this.selectedType;
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public boolean needCheck() {
        return true;
    }

    public void setType(int r8, int r9, TLRPC.User r10, boolean r11) {
        if (this.selectedType != r8) goto L5;
        boolean r0 = true;
    L6:
        this.selectedType = r8;
        if (r8 != TYPE_GIVEAWAY) goto L10;
        this.titleTextView.setText(LocaleController.getString(R.string.BoostingCreateGiveaway));
        setSubtitle(LocaleController.getString(R.string.BoostingWinnersRandomly));
        this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray3, this.resourcesProvider));
        this.avatarDrawable.setAvatarType(16);
        this.avatarDrawable.setColor(-15292942, -15630089);
        setDivider(true);
        setBackground(Theme.getThemedDrawableByKey(getContext(), R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
    L32:
        this.radioButton.setChecked(r11, r0);
        this.imageView.setImageDrawable(this.avatarDrawable);
        this.imageView.setRoundRadius(AndroidUtilities.dp(20.0f));
        return;
    L10:
        if (r8 != TYPE_SPECIFIC_USERS) goto L20;
        this.titleTextView.setText(LocaleController.getString(R.string.BoostingAwardSpecificUsers));
        if (r9 != 1) goto L15;
        if (r10 == null) goto L15;
        setSubtitle(withArrow(Emoji.replaceEmoji(UserObject.getUserName(r10), this.subtitleTextView.getPaint().getFontMetricsInt(), false)));
    L18:
        this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2, this.resourcesProvider));
        this.avatarDrawable.setAvatarType(6);
        this.avatarDrawable.setColor(-3905294, -6923014);
        setDivider(false);
        setBackground(Theme.getThemedDrawableByKey(getContext(), R.drawable.greydivider_top, Theme.key_windowBackgroundGrayShadow));
    L15:
        if (r9 <= 0) goto L17;
        setSubtitle(withArrow(LocaleController.formatPluralString("Recipient", r9, new Object[0])));
        goto L18
    L17:
        setSubtitle(withArrow(LocaleController.getString(R.string.BoostingSelectRecipients)));
        goto L18
    L20:
        if (r8 != TYPE_PREMIUM) goto L30;
        this.titleTextView.setText(LocaleController.getString(R.string.BoostingPremium));
        if (r9 != 1) goto L25;
        if (r10 == null) goto L25;
        setSubtitle(withArrow(Emoji.replaceEmoji(UserObject.getUserName(r10), this.subtitleTextView.getPaint().getFontMetricsInt(), false)));
    L28:
        this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2, this.resourcesProvider));
        this.avatarDrawable.setAvatarType(25);
        this.avatarDrawable.setColor(-3905294, -6923014);
        setDivider(true);
        setBackground(Theme.getThemedDrawableByKey(getContext(), R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
    L25:
        if (r9 <= 0) goto L27;
        setSubtitle(withArrow(LocaleController.formatPluralString("Recipient", r9, new Object[0])));
        goto L28
    L27:
        setSubtitle(withArrow(LocaleController.getString(R.string.BoostingWinnersRandomly)));
        goto L28
    L30:
        if (r8 != TYPE_STARS) goto L32;
        this.titleTextView.setText(TextCell.applyNewSpan(LocaleController.getString(R.string.BoostingStars)));
        setSubtitle(LocaleController.getString(R.string.BoostingWinnersRandomly));
        this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray3, this.resourcesProvider));
        this.avatarDrawable.setAvatarType(26);
        this.avatarDrawable.setColor(-146917, -625593);
        setDivider(false);
        setBackground(Theme.getThemedDrawableByKey(getContext(), R.drawable.greydivider_top, Theme.key_windowBackgroundGrayShadow));
        goto L32
    L5:
        r0 = false;
        goto L6
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public void updateLayouts() {
        BackupImageView r1 = this.imageView;
        int r3 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r2 = 5;
    L6:
        r1.setLayoutParams(LayoutHelper.createFrame(40, 40.0f, r2 | 16, 57.0f, 0.0f, 57.0f, 0.0f));
        SimpleTextView r12 = this.titleTextView;
        boolean r22 = LocaleController.isRTL;
        if (r22 == false) goto L9;
        int r5 = 5;
    L10:
        int r8 = r5 | 16;
        if (r22 == false) goto L13;
        float r9 = 20.0f;
    L14:
        if (r22 == false) goto L16;
        float r11 = 109.0f;
    L17:
        r12.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r8, r9, 0.0f, r11, 0.0f));
        SimpleTextView r13 = this.subtitleTextView;
        boolean r23 = LocaleController.isRTL;
        if (r23 == false) goto L20;
        int r6 = 5;
    L21:
        int r16 = r6 | 16;
        if (r23 == false) goto L24;
        float r17 = 20.0f;
    L25:
        if (r23 == false) goto L27;
        float r19 = 109.0f;
    L28:
        r13.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r16, r17, 0.0f, r19, 0.0f));
        RadioButton r0 = this.radioButton;
        if (LocaleController.isRTL == false) goto L31;
        r3 = 5;
    L31:
        r0.setLayoutParams(LayoutHelper.createFrame(22, 22.0f, r3 | 16, 16.0f, 0.0f, 15.0f, 0.0f));
        return;
    L27:
        r19 = 20.0f;
        goto L28
    L24:
        r17 = 109.0f;
        goto L25
    L20:
        r6 = 3;
        goto L21
    L16:
        r11 = 20.0f;
        goto L17
    L13:
        r9 = 109.0f;
        goto L14
    L9:
        r5 = 3;
        goto L10
    L5:
        r2 = 3;
        goto L6
    }
}
