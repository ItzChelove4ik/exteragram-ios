package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class PollItemMenu$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ PollItemMenu f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ TLRPC.PollAnswer f$4;

    public /* synthetic */ PollItemMenu$$ExternalSyntheticLambda4(PollItemMenu r1, boolean r2, BaseFragment r3, ArrayList r4, TLRPC.PollAnswer r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PollItemMenu.$r8$lambda$gCokgN7G4KUOCadcyRAuDWYaDiU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
