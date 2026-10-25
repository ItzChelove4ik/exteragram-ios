package org.telegram.ui;

import android.view.MotionEvent;
import org.telegram.messenger.IMapsProvider;

/* loaded from: classes6.dex */
public final /* synthetic */ class LocationActivity$$ExternalSyntheticLambda5 implements IMapsProvider.ITouchInterceptor {
    public final /* synthetic */ LocationActivity f$0;

    public /* synthetic */ LocationActivity$$ExternalSyntheticLambda5(LocationActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.IMapsProvider.ITouchInterceptor
    public final boolean onInterceptTouchEvent(MotionEvent r1, IMapsProvider.ICallableMethod r2) {
        return LocationActivity.$r8$lambda$I07Zeh_ozgpBbvmvtKgP33kCXQo(this.f$0, r1, r2);
    }
}
