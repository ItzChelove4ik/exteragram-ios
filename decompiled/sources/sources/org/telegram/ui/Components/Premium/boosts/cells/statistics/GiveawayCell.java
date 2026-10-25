package org.telegram.ui.Components.Premium.boosts.cells.statistics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.Premium.boosts.BoostRepository;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class GiveawayCell extends UserCell {
    private CounterDrawable counterDrawable;
    private TL_stories.PrepaidGiveaway prepaidGiveaway;

    public GiveawayCell(Context r1, int r2, int r3, boolean r4) {
        super(r1, r2, r3, r4);
        init(r1);
    }

    private void init(Context r2) {
        this.counterDrawable = new CounterDrawable(r2);
    }

    @Override // org.telegram.ui.Cells.UserCell, org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public TL_stories.PrepaidGiveaway getPrepaidGiveaway() {
        return this.prepaidGiveaway;
    }

    @Override // org.telegram.ui.Cells.UserCell, android.view.View
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
        int r1 = AndroidUtilities.dp(70.0f);
    L13:
        r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(70.0f);
        goto L7
    }

    public void setImage(TL_stories.PrepaidGiveaway r5) {
        this.prepaidGiveaway = r5;
        if ((r5 instanceof TL_stories.TL_prepaidStarsGiveaway) == false) goto L6;
        this.avatarDrawable.setAvatarType(26);
        String r52 = String.valueOf(((TL_stories.TL_prepaidStarsGiveaway) r5).stars / 500);
        this.counterDrawable.setText(r52);
    L15:
        this.nameTextView.setRightDrawable(this.counterDrawable);
        return;
    L6:
        if ((r5 instanceof TL_stories.TL_prepaidGiveaway) == false) goto L15;
        this.avatarDrawable.setAvatarType(16);
        int r0 = ((TL_stories.TL_prepaidGiveaway) r5).months;
        if (r0 != 12) goto L10;
        this.avatarDrawable.setColor(-31392, -2796986);
    L14:
        String r53 = String.valueOf(r5.quantity * BoostRepository.giveawayBoostsPerPremium());
        this.counterDrawable.setText(r53);
        goto L15
    L10:
        AvatarDrawable r1 = this.avatarDrawable;
        if (r0 != 6) goto L13;
        r1.setColor(-10703110, -12481584);
        goto L14
    L13:
        r1.setColor(-6631068, -11945404);
        goto L14
    }
}
