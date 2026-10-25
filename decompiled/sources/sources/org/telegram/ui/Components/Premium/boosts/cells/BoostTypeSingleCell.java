package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class BoostTypeSingleCell extends BoostTypeCell {
    public BoostTypeSingleCell(Context r1, Theme.ResourcesProvider r2) {
        super(r1, r2);
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BoostTypeCell, org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public boolean needCheck() {
        return false;
    }

    public void setGiveaway(TL_stories.PrepaidGiveaway r6) {
        this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray3, this.resourcesProvider));
        if ((r6 instanceof TL_stories.TL_prepaidStarsGiveaway) == false) goto L6;
        TL_stories.TL_prepaidStarsGiveaway r62 = (TL_stories.TL_prepaidStarsGiveaway) r6;
        this.avatarDrawable.setAvatarType(26);
        this.titleTextView.setText(LocaleController.formatPluralStringComma("BoostingStarsPreparedGiveawaySubscriptionsPlural", (int) r62.stars));
        setSubtitle(LocaleController.formatPluralString("AmongWinners", r62.quantity, new Object[0]));
    L15:
        this.imageView.setImageDrawable(this.avatarDrawable);
        this.imageView.setRoundRadius(AndroidUtilities.dp(20.0f));
        return;
    L6:
        if ((r6 instanceof TL_stories.TL_prepaidGiveaway) == false) goto L15;
        this.titleTextView.setText(LocaleController.getString(R.string.BoostingPreparedGiveawayOne));
        this.avatarDrawable.setAvatarType(16);
        TL_stories.TL_prepaidGiveaway r0 = (TL_stories.TL_prepaidGiveaway) r6;
        int r2 = r0.months;
        if (r2 != 12) goto L10;
        this.avatarDrawable.setColor(-31392, -2796986);
    L14:
        setSubtitle(LocaleController.formatPluralString("BoostingPreparedGiveawaySubscriptionsPlural", r6.quantity, new Object[]{LocaleController.formatPluralString("Months", r0.months, new Object[0])}));
        goto L15
    L10:
        AvatarDrawable r3 = this.avatarDrawable;
        if (r2 != 6) goto L13;
        r3.setColor(-10703110, -12481584);
        goto L14
    L13:
        r3.setColor(-6631068, -11945404);
        goto L14
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BoostTypeCell, org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public void updateLayouts() {
        BackupImageView r0 = this.imageView;
        int r2 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r1 = 5;
    L6:
        r0.setLayoutParams(LayoutHelper.createFrame(40, 40.0f, r1 | 16, 16.0f, 0.0f, 16.0f, 0.0f));
        SimpleTextView r02 = this.titleTextView;
        boolean r12 = LocaleController.isRTL;
        if (r12 == false) goto L9;
        int r4 = 5;
    L10:
        int r7 = r4 | 16;
        if (r12 == false) goto L13;
        float r8 = 20.0f;
    L14:
        if (r12 == false) goto L16;
        float r10 = 69.0f;
    L17:
        r02.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r7, r8, 0.0f, r10, 0.0f));
        SimpleTextView r13 = this.subtitleTextView;
        boolean r03 = LocaleController.isRTL;
        if (r03 == false) goto L20;
        r2 = 5;
    L20:
        int r72 = r2 | 16;
        if (r03 == false) goto L23;
        float r82 = 20.0f;
    L24:
        if (r03 == false) goto L26;
        float r102 = 69.0f;
    L27:
        r13.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r72, r82, 0.0f, r102, 0.0f));
        return;
    L26:
        r102 = 20.0f;
        goto L27
    L23:
        r82 = 69.0f;
        goto L24
    L16:
        r10 = 20.0f;
        goto L17
    L13:
        r8 = 69.0f;
        goto L14
    L9:
        r4 = 3;
        goto L10
    L5:
        r1 = 3;
        goto L6
    }
}
