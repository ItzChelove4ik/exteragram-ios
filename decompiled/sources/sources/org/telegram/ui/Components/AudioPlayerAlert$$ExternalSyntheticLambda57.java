package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AudioPlayerAlert$$ExternalSyntheticLambda57 implements RequestDelegate {
    public final /* synthetic */ AudioPlayerAlert f$0;
    public final /* synthetic */ AlertDialog f$1;

    public /* synthetic */ AudioPlayerAlert$$ExternalSyntheticLambda57(AudioPlayerAlert r1, AlertDialog r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        AudioPlayerAlert.m8864$r8$lambda$vmb8W5hNil1BRBjRfjmEaU1_Wk(this.f$0, this.f$1, r2, r3);
    }
}
