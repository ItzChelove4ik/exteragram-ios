package org.telegram.ui;

import com.exteragram.messenger.api.dto.NowPlayingDTO;
import java.util.function.BiConsumer;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda69 implements BiConsumer {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLRPC.Document f$1;
    public final /* synthetic */ TLRPC.User f$2;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda69(ProfileActivity r1, TLRPC.Document r2, TLRPC.User r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object r3, Object r4) {
        ProfileActivity.$r8$lambda$OXnNUtBdw1iCiHUzo87wGDc2lis(this.f$0, this.f$1, this.f$2, (NowPlayingDTO) r3, (Long) r4);
    }
}
