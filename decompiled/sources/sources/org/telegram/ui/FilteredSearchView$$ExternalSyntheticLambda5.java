package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Adapters.FiltersView;

/* loaded from: classes6.dex */
public final /* synthetic */ class FilteredSearchView$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ FilteredSearchView f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$10;
    public final /* synthetic */ ArrayList f$11;
    public final /* synthetic */ ArrayList f$12;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLRPC.messages_Messages f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ String f$6;
    public final /* synthetic */ ArrayList f$7;
    public final /* synthetic */ FiltersView.MediaFilterData f$8;
    public final /* synthetic */ long f$9;

    public /* synthetic */ FilteredSearchView$$ExternalSyntheticLambda5(FilteredSearchView r1, int r2, TLRPC.TL_error r3, TLRPC.messages_Messages r4, int r5, boolean r6, String r7, ArrayList r8, FiltersView.MediaFilterData r9, long r10, long r12, ArrayList r14, ArrayList r15) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
        this.f$10 = r12;
        this.f$11 = r14;
        this.f$12 = r15;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FilteredSearchView.$r8$lambda$a6X3WBjYTLU0oJQ_EMrBThVTieI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12);
    }
}
