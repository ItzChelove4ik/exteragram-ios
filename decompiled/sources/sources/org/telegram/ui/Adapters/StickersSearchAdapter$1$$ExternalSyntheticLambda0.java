package org.telegram.ui.Adapters;

import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.MediaDataController;
import org.telegram.ui.Adapters.StickersSearchAdapter;

/* loaded from: classes6.dex */
public final /* synthetic */ class StickersSearchAdapter$1$$ExternalSyntheticLambda0 implements MediaDataController.KeywordResultCallback {
    public final /* synthetic */ StickersSearchAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ HashMap f$2;

    public /* synthetic */ StickersSearchAdapter$1$$ExternalSyntheticLambda0(StickersSearchAdapter.AnonymousClass1 r1, int r2, HashMap r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.MediaDataController.KeywordResultCallback
    public final void run(ArrayList r3, String r4) {
        StickersSearchAdapter.AnonymousClass1.m5964$r8$lambda$2AP5NqkEQl95boJyH8XfgvvmRo(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
