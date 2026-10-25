package org.telegram.ui.Components;

import android.view.MotionEvent;
import android.view.View;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TrendingStickersLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class TrendingStickersLayout$$ExternalSyntheticLambda1 implements View.OnTouchListener {
    public final /* synthetic */ TrendingStickersLayout f$0;
    public final /* synthetic */ TrendingStickersLayout.Delegate f$1;
    public final /* synthetic */ RecyclerListView.OnItemClickListener f$2;

    public /* synthetic */ TrendingStickersLayout$$ExternalSyntheticLambda1(TrendingStickersLayout r1, TrendingStickersLayout.Delegate r2, RecyclerListView.OnItemClickListener r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View r3, MotionEvent r4) {
        return TrendingStickersLayout.$r8$lambda$5lg4jukpj8C0UiabiwEIat04v84(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
