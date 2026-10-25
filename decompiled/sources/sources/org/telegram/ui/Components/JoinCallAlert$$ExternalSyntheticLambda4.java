package org.telegram.ui.Components;

import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class JoinCallAlert$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ AccountInstance f$3;
    public final /* synthetic */ MessagesStorage.BooleanCallback f$4;

    public /* synthetic */ JoinCallAlert$$ExternalSyntheticLambda4(AlertDialog r1, TLObject r2, long r3, AccountInstance r5, MessagesStorage.BooleanCallback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        JoinCallAlert.m11164$r8$lambda$V1wauqt2O0Je46IaESMYiZZZ6M(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
