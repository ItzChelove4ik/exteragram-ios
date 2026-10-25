package com.exteragram.messenger.utils.chats;

import com.exteragram.messenger.utils.chats.MainMenuHelper;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes4.dex */
public final /* synthetic */ class MainMenuHelper$$ExternalSyntheticLambda22 implements RequestDelegate {
    public final /* synthetic */ TLRPC.TL_attachMenuBot f$0;
    public final /* synthetic */ LaunchActivity f$1;
    public final /* synthetic */ MainMenuHelper.MenuContext f$2;

    public /* synthetic */ MainMenuHelper$$ExternalSyntheticLambda22(TLRPC.TL_attachMenuBot r1, LaunchActivity r2, MainMenuHelper.MenuContext r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        MainMenuHelper.$r8$lambda$J1OwKnqXdzc6kbDpmVdBOkDhjxc(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
