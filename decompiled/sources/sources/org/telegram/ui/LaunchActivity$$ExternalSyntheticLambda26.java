package org.telegram.ui;

import android.content.Intent;
import org.telegram.messenger.ContactsLoadingObserver;

/* loaded from: classes3.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda26 implements ContactsLoadingObserver.Callback {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Intent f$1;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda26(LaunchActivity r1, Intent r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.ContactsLoadingObserver.Callback
    public final void onResult(boolean r2) {
        LaunchActivity.m15601$r8$lambda$N4tc0UkOC5kUQGl1147dXsz3SE(this.f$0, this.f$1, r2);
    }
}
