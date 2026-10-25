package org.telegram.ui.ActionBar;

import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;

/* loaded from: classes3.dex */
public final /* synthetic */ class ActionBarLayout$$ExternalSyntheticLambda0 implements OnApplyWindowInsetsListener {
    public final /* synthetic */ BaseFragment f$0;

    public /* synthetic */ ActionBarLayout$$ExternalSyntheticLambda0(BaseFragment r1) {
        this.f$0 = r1;
    }

    @Override // androidx.core.view.OnApplyWindowInsetsListener
    public final WindowInsetsCompat onApplyWindowInsets(View r1, WindowInsetsCompat r2) {
        return this.f$0.onInsetsInternal(r1, r2);
    }
}
