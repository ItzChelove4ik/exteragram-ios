package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AudioPlayerAlert$$ExternalSyntheticLambda47 implements Utilities.Callback {
    public final /* synthetic */ AudioPlayerAlert f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLRPC.Document f$2;

    public /* synthetic */ AudioPlayerAlert$$ExternalSyntheticLambda47(AudioPlayerAlert r1, AlertDialog r2, TLRPC.Document r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        AudioPlayerAlert.$r8$lambda$GfXVko_Uc8RDJXQMRPAW2Pw6OYg(this.f$0, this.f$1, this.f$2, (TLRPC.InputFile) r3);
    }
}
