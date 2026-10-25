package org.telegram.ui.Components;

import android.view.View;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.Components.BackButtonMenu;

/* loaded from: classes7.dex */
public final /* synthetic */ class BackButtonMenu$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ AtomicReference f$0;
    public final /* synthetic */ BackButtonMenu.PulledDialog f$1;
    public final /* synthetic */ INavigationLayout f$2;
    public final /* synthetic */ TLRPC.TL_forumTopic f$3;
    public final /* synthetic */ BaseFragment f$4;

    public /* synthetic */ BackButtonMenu$$ExternalSyntheticLambda0(AtomicReference r1, BackButtonMenu.PulledDialog r2, INavigationLayout r3, TLRPC.TL_forumTopic r4, BaseFragment r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r7) {
        BackButtonMenu.$r8$lambda$LjwJnyIy4aTYeceLn6PSUidUlns(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
