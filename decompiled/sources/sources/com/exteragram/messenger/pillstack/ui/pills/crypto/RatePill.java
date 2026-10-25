package com.exteragram.messenger.pillstack.ui.pills.crypto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.pillstack.ui.PillStackPreferencesActivity;
import com.exteragram.messenger.pillstack.ui.pills.BasePill;
import com.exteragram.messenger.pillstack.ui.pills.crypto.utils.ColoredBackground;
import com.exteragram.messenger.pillstack.ui.pills.crypto.utils.ExchangeRates;
import com.exteragram.messenger.pillstack.ui.pills.crypto.utils.PillStackCurrencies;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.LaunchActivity;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public abstract class RatePill extends BasePill implements NotificationCenter.NotificationCenterDelegate {
    private final ColoredBackground background;
    private final String baseCurrency;
    private final RateCache cache;
    private final int iconResId;
    private final ImageView iconView;
    private final LinearLayout layout;
    private boolean requestInFlight;
    private final int scale;
    private final AnimatedTextView textView;

    public static final class RateCache {
        private final AtomicReference<String> cachedCurrency;
        private final AtomicReference<String> cachedPrice;

        /* renamed from: -$$Nest$fgetcachedCurrency, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AtomicReference m1527$$Nest$fgetcachedCurrency(RateCache r0) {
            return r0.cachedCurrency;
        }

        /* renamed from: -$$Nest$fgetcachedPrice, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AtomicReference m1528$$Nest$fgetcachedPrice(RateCache r0) {
            return r0.cachedPrice;
        }

        public RateCache() {
            this.cachedPrice = new AtomicReference();
            this.cachedCurrency = new AtomicReference();
        }
    }

    public static /* synthetic */ void $r8$lambda$9HYjY4ck4ESxWjWYhOrwqRWPG9c(RatePill r0, ItemOptions r1, String r2, String r3) {
        r0.lambda$onPillLongClicked$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$BdQANwcWvQfh8zMo4f-r_hLYinY, reason: not valid java name */
    public static /* synthetic */ void m1525$r8$lambda$BdQANwcWvQfh8zMo4fr_hLYinY(BaseFragment r1) {
        r1.presentFragment(new PillStackPreferencesActivity());
    }

    public static /* synthetic */ void $r8$lambda$aOvc9kfKRxqx4UTn09bT7iJRFXI(RatePill r0, String r1, ExchangeRates.State r2) {
        r0.lambda$onUpdateData$4(r1, r2);
    }

    /* renamed from: $r8$lambda$hfIn9m2_z-j8MxbvQ0pHRw3-_5g, reason: not valid java name */
    public static /* synthetic */ void m1526$r8$lambda$hfIn9m2_zj8MxbvQ0pHRw3_5g(RatePill r0) {
        r0.lambda$onPillLongClicked$2();
    }

    public static /* synthetic */ void $r8$lambda$ogQZbID7gaB1z_UV8x1NAjaJjaI(ItemOptions r0, ItemOptions r1, View r2) {
        r0.openSwipeback(r1);
    }

    public RatePill(Context r9, Theme.ResourcesProvider r10, RateCache r11, String r12, int r13, int r14, ColoredBackground r15) {
        super(r9, r10);
        this.cache = r11;
        this.baseCurrency = r12;
        this.scale = r13;
        this.iconResId = r14;
        this.background = r15;
        LinearLayout r102 = new LinearLayout(r9);
        this.layout = r102;
        r102.setOrientation(0);
        r102.setGravity(17);
        r102.setMinimumWidth(AndroidUtilities.dp(48.0f));
        r102.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        if (LocaleController.isRTL == false) goto L5;
        int r132 = 3;
    L6:
        addView(r102, LayoutHelper.createFrame(-2, 28, r132 | 16));
        ImageView r133 = new ImageView(r9);
        this.iconView = r133;
        r133.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        r102.addView(r133, LayoutHelper.createLinear(16, 16, 16, 0, 0, 4, 0));
        AnimatedTextView r134 = new AnimatedTextView(r9, true, true, true);
        this.textView = r134;
        r134.setTextSize(AndroidUtilities.dp(13.0f));
        r134.setIncludeFontPadding(false);
        r134.setTypeface(AndroidUtilities.bold());
        r134.adaptWidth = true;
        r102.addView(r134, LayoutHelper.createLinear(-2, -2, 16));
        setLoadingTargetView(r102);
        updateColors();
        ScaleStateListAnimator.apply(r102);
        String r92 = (String) RateCache.m1528$$Nest$fgetcachedPrice(r11).get();
        if (r92 == null) goto L10;
        setData(r92, false);
        return;
    L10:
        return;
    L5:
        r132 = 5;
        goto L6
    }

    private /* synthetic */ void lambda$onPillLongClicked$0(ItemOptions r1, String r2, String r3) {
        r1.dismiss();
        if (r2.equalsIgnoreCase(r3) == false) goto L5;
        return;
    L5:
        setTargetSelection(r2);
        onUpdateData(false);
    }

    private /* synthetic */ void lambda$onPillLongClicked$2() {
        onUpdateData(true);
    }

    private /* synthetic */ void lambda$onUpdateData$4(String r3, ExchangeRates.State r4) {
        this.requestInFlight = false;
        if (r4 != null) goto L10;
        String r32 = (String) RateCache.m1528$$Nest$fgetcachedPrice(this.cache).get();
        if (r32 == null) goto L8;
        setData(r32, true);
        return;
    L8:
        setErrorState(true);
        return;
    L10:
        BigDecimal r42 = r4.getRate(this.baseCurrency, r3);
        if (r42 != null) goto L18;
        String r33 = (String) RateCache.m1528$$Nest$fgetcachedPrice(this.cache).get();
        if (r33 == null) goto L16;
        setData(r33, true);
        return;
    L16:
        setErrorState(true);
        return;
    L18:
        String r43 = formatPrice(r42, r3);
        RateCache.m1528$$Nest$fgetcachedPrice(this.cache).set(r43);
        RateCache.m1527$$Nest$fgetcachedCurrency(this.cache).set(r3);
        setData(r43, true);
        markDataUpdated();
    }

    private void setData(String r3, boolean r4) {
        stopLoading();
        if (r4 == false) goto L5;
        animateSizeChange();
    L5:
        this.iconView.setImageResource(this.iconResId);
        this.iconView.setVisibility(0);
        this.textView.setText(r3, r4);
        this.textView.setVisibility(0);
    }

    private void setErrorState(boolean r4) {
        stopLoading();
        if (r4 == false) goto L5;
        animateSizeChange();
    L5:
        this.iconView.setImageResource(R.drawable.msg_retry);
        this.iconView.setVisibility(0);
        this.textView.setText(LocaleController.getString(R.string.Retry), r4);
        this.textView.setVisibility(0);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.pillStackSettingsChanged) goto L5;
        return;
    L5:
        if (PillStackConfig.shouldUpdatePill(r3, new int[]{getPillId()}) == true) goto L7;
        return;
    L7:
        if (getTargetSelection().equals("AUTO") == false) goto L13;
        PillStackConfig.checkAndClearPendingUpdate(getPillId());
        onUpdateData(true);
        return;
    }

    public String formatPrice(BigDecimal r2, String r3) {
        String r0 = PillStackCurrencies.formatFiatPrice(r2, r3);
        if (r0 == null) goto L6;
        return r0;
    L6:
        return r2.setScale(this.scale, RoundingMode.HALF_UP).toPlainString() + " " + r3;
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public long getRefreshInterval() {
        return 300000;
    }

    public String[] getTargetCurrencies() {
        return PillStackCurrencies.TARGET_CURRENCIES;
    }

    public abstract String getTargetSelection();

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pillStackSettingsChanged);
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pillStackSettingsChanged);
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public void onPillClicked() {
        if (this.iconView.getVisibility() == 0) goto L5;
    L10:
        onPillLongClicked();
        return;
    L5:
        if (this.textView.getText() == null) goto L10;
        if (TextUtils.equals(this.textView.getText(), LocaleController.getString(R.string.Retry)) == false) goto L10;
        onUpdateData(true);
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public boolean onPillLongClicked() {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L13;
        ItemOptions r3 = ItemOptions.makeOptions(r0, this, true);
        ItemOptions r4 = r3.makeSwipeback(true).add(R.drawable.ic_ab_back, LocaleController.getString(R.string.Back), new RatePill$$ExternalSyntheticLambda1(r3)).addGap();
        String r5 = getTargetSelection();
        String[] r6 = getTargetCurrencies();
        int r7 = r6.length;
        int r8 = 0;
    L5:
        if (r8 >= r7) goto L7;
        String r9 = r6[r8];
        r4.addChecked(r9.equalsIgnoreCase(r5), PillStackCurrencies.getTargetCurrencyLabel(r9), new RatePill$$ExternalSyntheticLambda2(this, r3, r9, r5));
        r8 = r8 + 1;
        goto L5
    L7:
        ActionBarMenuSubItem r52 = new ActionBarMenuSubItem(r3.getContext(), false, false, this.resourcesProvider);
        r52.setTextAndIcon(LocaleController.getString(R.string.CryptoPillTargetCurrency), R.drawable.msg_language);
        r52.setSubtext(PillStackCurrencies.getTargetCurrencySubtext(getTargetSelection()));
        r52.setItemHeight(56);
        r52.setOnClickListener(new RatePill$$ExternalSyntheticLambda3(r3, r4));
        ItemOptions r13 = r3.add(r52).addGap().add(R.drawable.msg_retry, LocaleController.getString(R.string.Refresh), new RatePill$$ExternalSyntheticLambda4(this)).add(R.drawable.msg_settings, LocaleController.getString(R.string.Settings), new RatePill$$ExternalSyntheticLambda5(r0)).setSwipebackGravity(!LocaleController.isRTL, false).forceBelowScrim(true).setDrawScrim(false);
        if (LocaleController.isRTL == false) goto L10;
        int r02 = 3;
    L11:
        r13.setGravity(r02).setDimAlpha(0).show();
        return true;
    L10:
        r02 = 5;
        goto L11
    L13:
        return false;
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public void onUpdateData(boolean r5) {
        String r0 = getTargetSelection();
        String r02 = ExchangeRates.resolveTargetCurrency(UserConfig.selectedAccount, r0);
        String r1 = (String) RateCache.m1528$$Nest$fgetcachedPrice(this.cache).get();
        if (TextUtils.equals(r02, (CharSequence) RateCache.m1527$$Nest$fgetcachedCurrency(this.cache).get()) == true) goto L6;
        r1 = null;
    L6:
        if (r5 == true) goto L13;
        if (r1 == null) goto L13;
        if (isRefreshDue() == true) goto L13;
        setData(r1, false);
        return;
    L13:
        if (this.requestInFlight == false) goto L15;
        return;
    L15:
        this.requestInFlight = true;
        if (r5 == false) goto L18;
        animateSizeChange();
    L18:
        startLoading();
        if (r1 == null) goto L21;
    L23:
        this.iconView.setImageResource(this.iconResId);
        this.iconView.setVisibility(0);
        this.textView.setVisibility(0);
    L24:
        if (r5 == false) goto L26;
        ExchangeRates.clearCache();
    L26:
        ExchangeRates.fetch(new RatePill$$ExternalSyntheticLambda0(this, r02));
        return;
    L21:
        if (RateCache.m1528$$Nest$fgetcachedPrice(this.cache).get() != null) goto L23;
        this.iconView.setVisibility(8);
        this.textView.setVisibility(8);
        goto L24
    }

    @Override // android.view.View
    public void setPressed(boolean r2) {
        if (this.loading == false) goto L5;
        r2 = false;
    L5:
        super.setPressed(r2);
        this.layout.setPressed(r2);
    }

    public abstract void setTargetSelection(String r1);

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public void updateColors() {
        this.layout.setBackground(this.background);
        this.textView.setTextColor(-1);
        this.iconView.setColorFilter(-1);
        updateLoadingColors();
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public void updateLoadingColors() {
        LoadingDrawable r5 = this.loadingDrawable;
        if (r5 == null) goto L6;
        r5.setColors(Theme.multAlpha(-1, 0.1f), Theme.multAlpha(-1, 0.3f), Theme.multAlpha(-1, 0.2f), Theme.multAlpha(-1, 0.45f));
        return;
    }
}
