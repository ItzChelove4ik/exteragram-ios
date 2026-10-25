package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AvatarConstructorFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda30 implements AvatarConstructorFragment.Delegate {
    public final /* synthetic */ ChatAttachAlertPhotoLayout f$0;
    public final /* synthetic */ AvatarConstructorFragment f$1;

    public /* synthetic */ ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda30(ChatAttachAlertPhotoLayout r1, AvatarConstructorFragment r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.AvatarConstructorFragment.Delegate
    public final void onDone(AvatarConstructorFragment.BackgroundGradient r8, long r9, TLRPC.Document r11, AvatarConstructorFragment.PreviewView r12) {
        ChatAttachAlertPhotoLayout.m9684$r8$lambda$AUIMk3LpjOl63bBUfavYacv60U(this.f$0, this.f$1, r8, r9, r11, r12);
    }
}
