package org.telegram.ui.Adapters;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final /* synthetic */ class SearchAdapterHelper$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ SearchAdapterHelper f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ long f$6;
    public final /* synthetic */ String f$7;

    public /* synthetic */ SearchAdapterHelper$$ExternalSyntheticLambda1(SearchAdapterHelper r1, int r2, boolean r3, boolean r4, boolean r5, boolean r6, long r7, String r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r9;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r12, TLRPC.TL_error r13) {
        SearchAdapterHelper.$r8$lambda$l6VjP7qW0qvuqm_zOjFDhTizDTA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r12, r13);
    }
}
