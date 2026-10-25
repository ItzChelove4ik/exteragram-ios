package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ ArrayList f$4;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda4(int r1, TLRPC.Chat r2, ArrayList r3, ArrayList r4, ArrayList r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AlertsCreator.$r8$lambda$dZYrZBKHFMAi_w_wUcwOfwQ_nJo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
