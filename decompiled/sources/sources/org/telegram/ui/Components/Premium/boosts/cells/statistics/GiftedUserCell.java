package org.telegram.ui.Components.Premium.boosts.cells.statistics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.Date;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class GiftedUserCell extends UserCell {
    private FrameLayout badgeLayout;
    private TextView badgeTextView;
    private TL_stories.Boost boost;
    private CounterDrawable counterDrawable;
    private Drawable giftDrawable;
    private Drawable giveawayDrawable;

    public GiftedUserCell(Context r1, int r2, int r3, boolean r4) {
        super(r1, r2, r3, r4);
        init();
    }

    private void init() {
        this.counterDrawable = new CounterDrawable(getContext());
        this.badgeLayout = new FrameLayout(getContext());
        TextView r0 = new TextView(getContext());
        this.badgeTextView = r0;
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        this.badgeTextView.setTypeface(AndroidUtilities.bold());
        this.badgeTextView.setTextSize(12.0f);
        this.badgeTextView.setGravity(17);
        this.badgeLayout.addView(this.badgeTextView, LayoutHelper.createFrame(-2, 22.0f));
        int r3 = 0;
        this.badgeLayout.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        FrameLayout r02 = this.badgeLayout;
        boolean r1 = LocaleController.isRTL;
        if (r1 == false) goto L5;
        int r2 = 3;
    L6:
        int r6 = r2 | 48;
        if (r1 == false) goto L9;
        int r4 = 9;
    L10:
        float r7 = r4;
        if (r1 == true) goto L14;
        r3 = 9;
    L14:
        addView(r02, LayoutHelper.createFrame(-2, -2.0f, r6, r7, 9.0f, r3, 0.0f));
        return;
    L9:
        r4 = 0;
        goto L10
    L5:
        r2 = 5;
        goto L6
    }

    private void setAvatarColorByMonths(int r2) {
        if (r2 != 12) goto L6;
        this.avatarDrawable.setColor(-31392, -2796986);
        return;
    L6:
        AvatarDrawable r1 = this.avatarDrawable;
        if (r2 != 6) goto L10;
        r1.setColor(-10703110, -12481584);
        return;
    L10:
        r1.setColor(-6631068, -11945404);
    }

    public TL_stories.Boost getBoost() {
        return this.boost;
    }

    @Override // org.telegram.ui.Cells.UserCell, org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
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

    public void setStatus(TL_stories.Boost r12) {
        this.boost = r12;
        int r1 = 0;
        if (r12.gift == false) goto L5;
    L8:
        this.badgeLayout.setVisibility(0);
        int r0 = ((r12.expires - r12.date) / 30) / 86400;
        long r3 = r12.stars;
        if (r3 <= 0) goto L12;
        this.nameTextView.setText(LocaleController.formatPluralString("BoostingBoostStars", (int) r3, new Object[0]));
        this.avatarDrawable.setAvatarType(26);
        this.avatarImageView.setForUserOrChat(null, this.avatarDrawable);
        this.nameTextView.setRightDrawable(null);
    L17:
        String r02 = LocaleController.getInstance().getFormatterBoostExpired().format(new Date(r12.expires * 1000));
        long r32 = r12.stars;
        SimpleTextView r4 = this.statusTextView;
        if (r32 <= 0) goto L20;
        r4.setText(LocaleController.formatString(R.string.BoostingStarsExpires, new Object[]{r02}));
    L22:
        if (r12.gift == false) goto L28;
        if (this.giftDrawable != null) goto L26;
        Drawable r03 = getResources().getDrawable(R.drawable.mini_gift);
        this.giftDrawable = r03;
        r03.setColorFilter(new PorterDuffColorFilter(-3240417, PorterDuff.Mode.MULTIPLY));
    L26:
        this.badgeTextView.setTextColor(-3240417);
        this.badgeTextView.setCompoundDrawablesWithIntrinsicBounds(this.giftDrawable, null, null, null);
        this.badgeTextView.setCompoundDrawablePadding(AndroidUtilities.dp(4.0f));
        this.badgeTextView.setText(LocaleController.getString(R.string.BoostingGift));
        this.badgeLayout.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), Theme.multAlpha(-3240417, 0.2f)));
    L28:
        if (r12.giveaway == true) goto L30;
    L33:
        int r122 = r12.multiplier;
        if (r122 <= 0) goto L36;
        this.counterDrawable.setText(String.valueOf(r122));
        this.nameTextView.setRightDrawable(this.counterDrawable);
    L38:
        if (this.badgeLayout.getVisibility() != 0) goto L49;
        int r123 = ((int) this.badgeTextView.getPaint().measureText(this.badgeTextView.getText().toString())) + AndroidUtilities.dp(22.0f);
        SimpleTextView r04 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L42;
        int r2 = r123;
    L43:
        int r33 = r04.getPaddingTop();
        if (LocaleController.isRTL == true) goto L47;
        r1 = r123;
    L47:
        r04.setPadding(r2, r33, r1, this.nameTextView.getPaddingBottom());
        return;
    L42:
        r2 = 0;
        goto L43
    L49:
        SimpleTextView r124 = this.nameTextView;
        r124.setPadding(0, r124.getPaddingTop(), 0, this.nameTextView.getPaddingBottom());
        return;
    L36:
        this.nameTextView.setRightDrawable(null);
        goto L38
    L30:
        if (this.giveawayDrawable != null) goto L32;
        Drawable r05 = getResources().getDrawable(R.drawable.mini_giveaway);
        this.giveawayDrawable = r05;
        r05.setColorFilter(new PorterDuffColorFilter(-13397548, PorterDuff.Mode.MULTIPLY));
    L32:
        this.badgeTextView.setTextColor(-13397548);
        this.badgeTextView.setCompoundDrawablesWithIntrinsicBounds(this.giveawayDrawable, null, null, null);
        this.badgeTextView.setCompoundDrawablePadding(AndroidUtilities.dp(4.0f));
        this.badgeTextView.setText(LocaleController.getString(R.string.BoostingGiveaway));
        this.badgeLayout.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), Theme.multAlpha(-13397548, 0.2f)));
        goto L33
    L20:
        r4.setText(LocaleController.formatString(R.string.BoostingExpires, new Object[]{r02}));
        goto L22
    L12:
        if (r12.unclaimed == false) goto L15;
        this.nameTextView.setText(LocaleController.getString(R.string.BoostingUnclaimed));
        this.avatarDrawable.setAvatarType(18);
        setAvatarColorByMonths(r0);
        this.avatarImageView.setForUserOrChat(null, this.avatarDrawable);
        this.nameTextView.setRightDrawable(null);
        goto L17
    L15:
        if (r12.user_id != (-1)) goto L17;
        this.nameTextView.setText(LocaleController.getString(R.string.BoostingToBeDistributed));
        this.avatarDrawable.setAvatarType(19);
        setAvatarColorByMonths(r0);
        this.avatarImageView.setForUserOrChat(null, this.avatarDrawable);
        this.nameTextView.setRightDrawable(null);
        goto L17
    L5:
        if (r12.giveaway == true) goto L8;
        this.badgeLayout.setVisibility(8);
        goto L33
    }
}
