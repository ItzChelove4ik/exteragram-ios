package com.exteragram.messenger.pillstack.ui.pills.crypto;

import android.annotation.SuppressLint;
import android.content.Context;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.pillstack.core.PillType;
import com.exteragram.messenger.pillstack.ui.pills.crypto.RatePill;
import com.exteragram.messenger.pillstack.ui.pills.crypto.utils.ColoredBackground;
import com.exteragram.messenger.pillstack.ui.pills.crypto.utils.PillStackCurrencies;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class UsdPill extends RatePill {
    private static final RatePill.RateCache CACHE = null;

    static {
        CACHE = new RatePill.RateCache();
    }

    public UsdPill(Context r9, Theme.ResourcesProvider r10) {
        super(r9, r10, CACHE, "USD", 2, R.drawable.pillstack_usd, new ColoredBackground(-14840995, -15172775));
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public int getPillId() {
        return PillType.USD.getId();
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.crypto.RatePill
    public String[] getTargetCurrencies() {
        return PillStackCurrencies.getTargetCurrencies("USD");
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.crypto.RatePill
    public String getTargetSelection() {
        if ("USD".equalsIgnoreCase(PillStackConfig.getUsdTargetCurrency()) == false) goto L7;
        return "AUTO";
    L7:
        return PillStackConfig.getUsdTargetCurrency();
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.crypto.RatePill
    public void setTargetSelection(String r1) {
        PillStackConfig.setUsdTargetCurrency(r1);
    }
}
