package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda6 implements Utilities.Callback {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ BackupImageView f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda6(String r1, BackupImageView r2, int r3, Theme.ResourcesProvider r4, String r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        MediaDataController.m4423$r8$lambda$Wjnt49L2HqOE5kQtSiwsjuVreY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (TLRPC.TL_messages_stickerSet) r7);
    }
}
