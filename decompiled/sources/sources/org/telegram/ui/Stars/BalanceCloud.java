package org.telegram.ui.Stars;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes3.dex */
public class BalanceCloud extends LinearLayout implements NotificationCenter.NotificationCenterDelegate {
    private final ColoredImageSpan[] coloredImageSpansTon;
    private AmountUtils$Currency currency;
    private final int currentAccount;
    private final Theme.ResourcesProvider resourcesProvider;
    private final TextView textView1;
    private final LinkSpanDrawable.LinksTextView textView2;

    public static /* synthetic */ void $r8$lambda$HRAZuAymy9_ChvkWyKU_utQrKQ8(Context r1, Theme.ResourcesProvider r2) {
        new StarsIntroActivity.StarsOptionsSheet(r1, r2).show();
    }

    public static /* synthetic */ void $r8$lambda$WyMiH1uE5hUdaMxA8Snh_clR2pg(BalanceCloud r0) {
        r0.lambda$updateBalance$1();
    }

    public BalanceCloud(Context r10, int r11, AmountUtils$Currency r12, Theme.ResourcesProvider r13) {
        super(r10);
        this.coloredImageSpansTon = new ColoredImageSpan[1];
        this.currentAccount = r11;
        this.resourcesProvider = r13;
        this.currency = r12;
        setOrientation(1);
        setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(9.0f));
        setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(24.0f), Theme.getColor(Theme.key_undo_background, r13)));
        TextView r112 = new TextView(r10);
        this.textView1 = r112;
        r112.setTextSize(1, 13.0f);
        r112.setTextColor(Theme.getColor(Theme.key_undo_infoColor, r13));
        r112.setGravity(17);
        addView(r112, LayoutHelper.createLinear(-2, -2, 0.0f, 17, 0, 0, 0, 0));
        LinkSpanDrawable.LinksTextView r113 = new LinkSpanDrawable.LinksTextView(r10, r13);
        this.textView2 = r113;
        r113.setTextSize(1, 12.0f);
        r113.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2MessageStarsInfoLink), new BalanceCloud$$ExternalSyntheticLambda0(r10, r13)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.0f)));
        r113.setGravity(17);
        addView(r113, LayoutHelper.createLinear(-2, -2, 0.0f, 17, 0, 1, 0, 0));
        updateBalance(false);
    }

    public BalanceCloud(Context r2, int r3, Theme.ResourcesProvider r4) {
        this(r2, r3, AmountUtils$Currency.STARS, r4);
    }

    private /* synthetic */ void lambda$updateBalance$1() {
        new StarsIntroActivity.StarsOptionsSheet(getContext(), this.resourcesProvider).show();
    }

    private void updateBalance(boolean r7) {
        AmountUtils$Amount r72 = StarsController.getInstance(this.currentAccount, this.currency).getBalanceAmount();
        AmountUtils$Currency r0 = this.currency;
        if (r0 != AmountUtils$Currency.STARS) goto L7;
        this.textView1.setText(StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatString(R.string.Gift2MessageStarsInfo, new Object[]{LocaleController.formatNumber(r72.asDecimal(), ',')}), 0.6f));
        LinkSpanDrawable.LinksTextView r73 = this.textView2;
        int r02 = Theme.key_undo_cancelColor;
        r73.setTextColor(Theme.getColor(r02, this.resourcesProvider));
        this.textView2.setLinkTextColor(Theme.getColor(r02, this.resourcesProvider));
        this.textView2.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2MessageStarsInfoLink), new BalanceCloud$$ExternalSyntheticLambda1(this)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.0f)));
        return;
    L7:
        if (r0 != AmountUtils$Currency.TON) goto L10;
        this.textView1.setText(StarsIntroActivity.replaceStarsWithPlain(true, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2MessageStarsInfoTON, new Object[]{r72.asDecimalString()})), 0.6f, this.coloredImageSpansTon));
        this.coloredImageSpansTon[0].setColorKey(Theme.key_undo_cancelColor);
        StringBuilder r03 = new StringBuilder(10);
        r03.append('~');
        r03.append(BillingController.getInstance().formatCurrency((long) ((r72.asDouble() * MessagesController.getInstance(this.currentAccount).config.tonUsdRate.get()) * 100.0d), "USD", 2));
        LinkSpanDrawable.LinksTextView r74 = this.textView2;
        int r1 = Theme.key_undo_infoColor;
        int r2 = Theme.getColor(r1, this.resourcesProvider);
        int r3 = Theme.key_undo_background;
        r74.setTextColor(ColorUtils.blendARGB(r2, Theme.getColor(r3, this.resourcesProvider), 0.33f));
        this.textView2.setLinkTextColor(ColorUtils.blendARGB(Theme.getColor(r1, this.resourcesProvider), Theme.getColor(r3, this.resourcesProvider), 0.33f));
        this.textView2.setText(r03);
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.starBalanceUpdated) goto L6;
        updateBalance(true);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (isEnabled() == true) goto L5;
        return false;
    L5:
        if (super.dispatchTouchEvent(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateBalance(false);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starBalanceUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.botStarsUpdated);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starBalanceUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.botStarsUpdated);
    }

    public void setCurrency(AmountUtils$Currency r2, boolean r3) {
        if (this.currency == r2) goto L6;
        this.currency = r2;
        updateBalance(r3);
        return;
    }
}
