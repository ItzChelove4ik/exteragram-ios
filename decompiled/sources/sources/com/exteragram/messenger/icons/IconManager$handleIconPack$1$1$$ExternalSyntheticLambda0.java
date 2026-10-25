package com.exteragram.messenger.icons;

import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.icons.ui.components.InstallIconPackBottomSheet;
import java.io.File;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes4.dex */
public final /* synthetic */ class IconManager$handleIconPack$1$1$$ExternalSyntheticLambda0 implements InstallIconPackBottomSheet.InstallDelegate {
    public final /* synthetic */ File f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ IconPack f$2;

    public /* synthetic */ IconManager$handleIconPack$1$1$$ExternalSyntheticLambda0(File r1, BaseFragment r2, IconPack r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.exteragram.messenger.icons.ui.components.InstallIconPackBottomSheet.InstallDelegate
    public final void onInstall(boolean r3, boolean r4) {
        IconManager.C01411.C00191.m1440$r8$lambda$f6uYi7_KIxph7kqQsGpPNgvjfM(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
