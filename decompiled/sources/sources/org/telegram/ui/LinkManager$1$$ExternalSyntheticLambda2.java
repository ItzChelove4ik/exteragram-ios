package org.telegram.ui;

import java.util.HashSet;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LinkManager;

/* loaded from: classes6.dex */
public final /* synthetic */ class LinkManager$1$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ LinkManager.AnonymousClass1 f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ HashSet f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;

    public /* synthetic */ LinkManager$1$$ExternalSyntheticLambda2(LinkManager.AnonymousClass1 r1, TLObject r2, boolean r3, HashSet r4, TLRPC.TL_error r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LinkManager.AnonymousClass1.$r8$lambda$scsJQTli3FoTbFF9KZEezGK3FXQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
