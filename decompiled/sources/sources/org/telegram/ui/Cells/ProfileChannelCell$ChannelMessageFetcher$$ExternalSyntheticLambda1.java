package org.telegram.ui.Cells;

import java.util.ArrayList;
import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.Cells.ProfileChannelCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileChannelCell$ChannelMessageFetcher$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ProfileChannelCell.ChannelMessageFetcher f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ MessagesStorage f$5;

    public /* synthetic */ ProfileChannelCell$ChannelMessageFetcher$$ExternalSyntheticLambda1(ProfileChannelCell.ChannelMessageFetcher r1, int r2, ArrayList r3, long r4, int r6, MessagesStorage r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ProfileChannelCell.ChannelMessageFetcher.$r8$lambda$bef_t3o84U4N8DSCyizInc5e4BA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
