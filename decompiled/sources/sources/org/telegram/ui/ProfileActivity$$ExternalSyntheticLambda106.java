package org.telegram.ui;

import com.exteragram.messenger.nowplaying.ui.components.NowPlayingCardData;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda106 implements NowPlayingCardData.Callback {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ Long f$2;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda106(ProfileActivity r1, TLRPC.User r2, Long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.exteragram.messenger.nowplaying.ui.components.NowPlayingCardData.Callback
    public final void onDataLoaded(NowPlayingCardData r3) {
        ProfileActivity.$r8$lambda$ORwDzS0xE6L9BOvZy60XXBbDw7A(this.f$0, this.f$1, this.f$2, r3);
    }
}
