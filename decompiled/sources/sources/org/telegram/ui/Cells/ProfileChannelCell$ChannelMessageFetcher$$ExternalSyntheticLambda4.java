package org.telegram.ui.Cells;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Cells.ProfileChannelCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileChannelCell$ChannelMessageFetcher$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ ProfileChannelCell.ChannelMessageFetcher f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ MessagesStorage f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ ProfileChannelCell$ChannelMessageFetcher$$ExternalSyntheticLambda4(ProfileChannelCell.ChannelMessageFetcher r1, int r2, TLObject r3, MessagesStorage r4, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ProfileChannelCell.ChannelMessageFetcher.$r8$lambda$4GaFD12QWPuSINXyCdFUe08goNA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
