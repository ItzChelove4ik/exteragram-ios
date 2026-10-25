package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.FiltersSetupActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ FiltersSetupActivity.ListAdapter f$0;
    public final /* synthetic */ TLRPC.TL_dialogFilterSuggested f$1;

    public /* synthetic */ FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda5(FiltersSetupActivity.ListAdapter r1, TLRPC.TL_dialogFilterSuggested r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FiltersSetupActivity.ListAdapter.$r8$lambda$nyUJlsLNvrvTNxxQQiN64_YW8BQ(this.f$0, this.f$1);
    }
}
