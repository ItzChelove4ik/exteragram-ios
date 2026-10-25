package org.telegram.ui.Components.Premium;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class DoubleLimitsPageView extends BaseListPageView {
    DoubledLimitsBottomSheet$Adapter adapter;

    public DoubleLimitsPageView(Context r1, Theme.ResourcesProvider r2) {
        super(r1, r2);
    }

    @Override // org.telegram.ui.Components.Premium.BaseListPageView
    public RecyclerView.Adapter createAdapter() {
        DoubledLimitsBottomSheet$Adapter r0 = new DoubledLimitsBottomSheet$Adapter(UserConfig.selectedAccount, true, this.resourcesProvider);
        this.adapter = r0;
        r0.containerView = this;
        return r0;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, r3);
        this.adapter.measureGradient(getContext(), getMeasuredWidth(), getMeasuredHeight());
    }
}
