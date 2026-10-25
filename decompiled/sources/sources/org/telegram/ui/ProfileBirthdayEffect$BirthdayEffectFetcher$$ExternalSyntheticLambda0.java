package org.telegram.ui;

import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ProfileBirthdayEffect;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileBirthdayEffect$BirthdayEffectFetcher$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ ProfileBirthdayEffect.BirthdayEffectFetcher f$0;
    public final /* synthetic */ HashSet f$1;
    public final /* synthetic */ ArrayList f$2;

    public /* synthetic */ ProfileBirthdayEffect$BirthdayEffectFetcher$$ExternalSyntheticLambda0(ProfileBirthdayEffect.BirthdayEffectFetcher r1, HashSet r2, ArrayList r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        ProfileBirthdayEffect.BirthdayEffectFetcher.m18294$r8$lambda$OkcmfVnX0x3PPmSo9WADzCLEVM(this.f$0, this.f$1, this.f$2, (TLRPC.TL_messages_stickerSet) r3);
    }
}
