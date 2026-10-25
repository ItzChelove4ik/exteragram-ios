package androidx.core.view;

import android.view.ViewConfiguration;
import androidx.core.util.Supplier;

/* loaded from: classes3.dex */
public final /* synthetic */ class ViewConfigurationCompat$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ ViewConfiguration f$0;

    public /* synthetic */ ViewConfigurationCompat$$ExternalSyntheticLambda0(ViewConfiguration r1) {
        this.f$0 = r1;
    }

    @Override // androidx.core.util.Supplier
    public final Object get() {
        return Integer.valueOf(this.f$0.getScaledMaximumFlingVelocity());
    }
}
