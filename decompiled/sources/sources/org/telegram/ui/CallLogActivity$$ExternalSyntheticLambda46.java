package org.telegram.ui;

import android.content.Context;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$$ExternalSyntheticLambda46 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ TL_phone.exportGroupCallInvite f$4;
    public final /* synthetic */ Theme.ResourcesProvider f$5;
    public final /* synthetic */ Runnable f$6;

    public /* synthetic */ CallLogActivity$$ExternalSyntheticLambda46(TLObject r1, AlertDialog r2, Context r3, int r4, TL_phone.exportGroupCallInvite r5, Theme.ResourcesProvider r6, Runnable r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CallLogActivity.m6530$r8$lambda$k3EfdJ6Yoam0W237sh_2inFJEA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
