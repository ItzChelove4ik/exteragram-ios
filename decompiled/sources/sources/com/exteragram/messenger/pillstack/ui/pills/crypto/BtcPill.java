package com.exteragram.messenger.pillstack.ui.pills.crypto;

import android.annotation.SuppressLint;
import android.content.Context;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.pillstack.core.PillType;
import com.exteragram.messenger.pillstack.ui.pills.crypto.RatePill;
import com.exteragram.messenger.pillstack.ui.pills.crypto.utils.ColoredBackground;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class BtcPill extends RatePill {
    private static final RatePill.RateCache CACHE = null;

    static {
        CACHE = new RatePill.RateCache();
    }

    public BtcPill(Context r9, Theme.ResourcesProvider r10) {
        super(r9, r10, CACHE, "BTC", 2, R.drawable.pillstack_btc, new ColoredBackground(-1071598, -1608430));
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public int getPillId() {
        return PillType.BTC.getId();
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.crypto.RatePill
    public String getTargetSelection() {
        return PillStackConfig.getBtcTargetCurrency();
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.crypto.RatePill
    public void setTargetSelection(String r1) {
        PillStackConfig.setBtcTargetCurrency(r1);
    }
}
