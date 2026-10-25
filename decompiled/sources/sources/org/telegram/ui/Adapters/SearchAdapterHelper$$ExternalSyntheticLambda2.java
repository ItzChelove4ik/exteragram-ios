package org.telegram.ui.Adapters;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final /* synthetic */ class SearchAdapterHelper$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ SearchAdapterHelper f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ AtomicInteger f$3;
    public final /* synthetic */ AtomicInteger f$4;
    public final /* synthetic */ ArrayList f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ Runnable f$7;

    public /* synthetic */ SearchAdapterHelper$$ExternalSyntheticLambda2(SearchAdapterHelper r1, ArrayList r2, int r3, AtomicInteger r4, AtomicInteger r5, ArrayList r6, int r7, Runnable r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        SearchAdapterHelper.$r8$lambda$OggLCBwhnURzNW_4igqukaKyivo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r11, r12);
    }
}
