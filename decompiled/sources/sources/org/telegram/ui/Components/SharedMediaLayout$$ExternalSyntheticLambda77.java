package org.telegram.ui.Components;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$$ExternalSyntheticLambda77 implements Runnable {
    public final /* synthetic */ SharedMediaLayout f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ MessageObject f$4;
    public final /* synthetic */ TLRPC.WebPage f$5;

    public /* synthetic */ SharedMediaLayout$$ExternalSyntheticLambda77(SharedMediaLayout r1, AlertDialog r2, boolean[] r3, boolean r4, MessageObject r5, TLRPC.WebPage r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SharedMediaLayout.m12786$r8$lambda$zWKbh7cssTyx53EBjEkoBssnXw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
