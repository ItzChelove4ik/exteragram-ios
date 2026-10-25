package com.exteragram.messenger.updater;

import android.content.Intent;
import androidx.arch.core.util.Function;
import com.exteragram.messenger.updater.UpdaterUtils;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes4.dex */
public final /* synthetic */ class UpdaterUtils$InstallReceiver$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ Intent f$0;

    public /* synthetic */ UpdaterUtils$InstallReceiver$$ExternalSyntheticLambda0(Intent r1) {
        this.f$0 = r1;
    }

    @Override // androidx.arch.core.util.Function
    public final Object apply(Object r1) {
        return UpdaterUtils.InstallReceiver.$r8$lambda$8VpP5GnMgbgrw4pDVy2xfm1rYm4(this.f$0, (BulletinFactory) r1);
    }
}
