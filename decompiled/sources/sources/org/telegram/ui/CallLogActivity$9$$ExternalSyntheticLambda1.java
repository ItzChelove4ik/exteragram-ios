package org.telegram.ui;

import java.util.HashSet;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.CallLogActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$9$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ HashSet f$2;
    public final /* synthetic */ BaseFragment f$3;

    public /* synthetic */ CallLogActivity$9$$ExternalSyntheticLambda1(int r1, boolean r2, HashSet r3, BaseFragment r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        CallLogActivity.AnonymousClass9.m6560$r8$lambda$F00SFbmKv03BnxNOavxCXE6D3U(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
