package org.telegram.ui.Adapters;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import org.telegram.ui.Adapters.DialogsSearchAdapter;

/* loaded from: classes3.dex */
public final /* synthetic */ class DialogsSearchAdapter$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ DialogsSearchAdapter.OnRecentSearchLoaded f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ LongSparseArray f$2;

    public /* synthetic */ DialogsSearchAdapter$$ExternalSyntheticLambda2(DialogsSearchAdapter.OnRecentSearchLoaded r1, ArrayList r2, LongSparseArray r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DialogsSearchAdapter.m5882$r8$lambda$VZMV9FdQaJMqRxeN_J72fQS67w(this.f$0, this.f$1, this.f$2);
    }
}
