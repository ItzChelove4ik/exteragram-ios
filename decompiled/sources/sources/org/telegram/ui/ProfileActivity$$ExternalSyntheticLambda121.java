package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda121 implements Utilities.Callback {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLRPC.ChannelParticipant f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ TLRPC.ChatParticipant f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ String f$5;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda121(ProfileActivity r1, TLRPC.ChannelParticipant r2, TLRPC.User r3, TLRPC.ChatParticipant r4, boolean r5, String r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        ProfileActivity.m17825$r8$lambda$e8tcUasvzL3_TWQhGslUl3MMGE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (Integer) r8);
    }
}
