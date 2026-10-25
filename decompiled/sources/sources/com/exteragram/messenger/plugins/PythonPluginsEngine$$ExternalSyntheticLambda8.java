package com.exteragram.messenger.plugins;

import com.exteragram.messenger.plugins.PythonPluginsEngine;

/* loaded from: classes.dex */
public final /* synthetic */ class PythonPluginsEngine$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ PythonPluginsEngine.Updater.Companion f$0;

    public /* synthetic */ PythonPluginsEngine$$ExternalSyntheticLambda8(PythonPluginsEngine.Updater.Companion r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.checkUpdates();
    }
}
