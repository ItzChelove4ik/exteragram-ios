package org.telegram.ui;

import java.util.HashSet;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.CallLogActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$9$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ HashSet f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;
    public final /* synthetic */ BaseFragment f$5;

    public /* synthetic */ CallLogActivity$9$$ExternalSyntheticLambda3(TLObject r1, int r2, boolean r3, HashSet r4, TLRPC.TL_error r5, BaseFragment r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CallLogActivity.AnonymousClass9.m6561$r8$lambda$HNxXsgbSysl7L8tDBtVrPFkeYc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
