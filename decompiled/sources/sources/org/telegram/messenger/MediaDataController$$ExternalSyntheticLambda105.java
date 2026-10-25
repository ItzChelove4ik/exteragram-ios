package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.BackupImageView;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda105 implements Utilities.Callback {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ BackupImageView f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda105(String r1, BackupImageView r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        MediaDataController.m4392$r8$lambda$Ex_Ge2zW3aQweJAgm6QFrftidY(this.f$0, this.f$1, this.f$2, (TLRPC.TL_messages_stickerSet) r3);
    }
}
