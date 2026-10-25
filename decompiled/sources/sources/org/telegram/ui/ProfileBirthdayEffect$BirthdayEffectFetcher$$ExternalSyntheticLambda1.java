package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ProfileBirthdayEffect;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileBirthdayEffect$BirthdayEffectFetcher$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ ProfileBirthdayEffect.BirthdayEffectFetcher f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ ProfileBirthdayEffect$BirthdayEffectFetcher$$ExternalSyntheticLambda1(ProfileBirthdayEffect.BirthdayEffectFetcher r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ProfileBirthdayEffect.BirthdayEffectFetcher.m18295$r8$lambda$cbp0Ce47FZAicALOKJ6QDim4TY(this.f$0, this.f$1, (TLRPC.TL_messages_stickerSet) r2);
    }
}
