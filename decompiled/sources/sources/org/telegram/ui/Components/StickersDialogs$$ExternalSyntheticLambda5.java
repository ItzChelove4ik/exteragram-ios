package org.telegram.ui.Components;

import android.view.View;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersDialogs$$ExternalSyntheticLambda5 implements View.OnClickListener {
    public final /* synthetic */ ActionBarPopupWindow f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ TLRPC.TL_messages_stickerSet f$2;
    public final /* synthetic */ BaseFragment f$3;
    public final /* synthetic */ Theme.ResourcesProvider f$4;

    public /* synthetic */ StickersDialogs$$ExternalSyntheticLambda5(ActionBarPopupWindow r1, ArrayList r2, TLRPC.TL_messages_stickerSet r3, BaseFragment r4, Theme.ResourcesProvider r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r7) {
        StickersDialogs.m13133$r8$lambda$KwNe0gshzMmIwENIJT3PkIZ_Wc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
