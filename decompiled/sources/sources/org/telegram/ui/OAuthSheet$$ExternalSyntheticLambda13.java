package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.BackupImageView;

/* loaded from: classes6.dex */
public final /* synthetic */ class OAuthSheet$$ExternalSyntheticLambda13 implements Utilities.Callback {
    public final /* synthetic */ ArrayList f$0;
    public final /* synthetic */ BackupImageView[] f$1;

    public /* synthetic */ OAuthSheet$$ExternalSyntheticLambda13(ArrayList r1, BackupImageView[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        OAuthSheet.$r8$lambda$pfTlQM9HwhspZJEfT70vEXzhlY8(this.f$0, this.f$1, (TLRPC.TL_messages_stickerSet) r2);
    }
}
