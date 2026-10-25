package com.exteragram.messenger.plugins;

import com.exteragram.messenger.plugins.pip.PipController;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class PythonPluginsEngine$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ Plugin f$0;
    public final /* synthetic */ PythonPluginsEngine f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ PipController.InstallerDelegate f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ PythonPluginsEngine$$ExternalSyntheticLambda9(Plugin r1, PythonPluginsEngine r2, String r3, PipController.InstallerDelegate r4, Utilities.Callback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PythonPluginsEngine.m1563$r8$lambda$NGT_LVDpZfx1FtJ6x4iJPoreg0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
