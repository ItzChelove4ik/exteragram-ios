package org.telegram.ui.ActionBar;

import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;

/* loaded from: classes6.dex */
public final /* synthetic */ class BottomSheet$$ExternalSyntheticLambda9 implements OnApplyWindowInsetsListener {
    public final /* synthetic */ BottomSheet f$0;

    public /* synthetic */ BottomSheet$$ExternalSyntheticLambda9(BottomSheet r1) {
        this.f$0 = r1;
    }

    @Override // androidx.core.view.OnApplyWindowInsetsListener
    public final WindowInsetsCompat onApplyWindowInsets(View r1, WindowInsetsCompat r2) {
        return this.f$0.onApplyWindowInsetsToRoot(r1, r2);
    }
}
