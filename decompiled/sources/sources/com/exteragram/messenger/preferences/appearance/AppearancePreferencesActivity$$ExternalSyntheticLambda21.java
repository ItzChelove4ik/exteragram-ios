package com.exteragram.messenger.preferences.appearance;

import org.telegram.messenger.SharedConfig;

/* loaded from: classes4.dex */
public final /* synthetic */ class AppearancePreferencesActivity$$ExternalSyntheticLambda21 implements Runnable {
    public /* synthetic */ AppearancePreferencesActivity$$ExternalSyntheticLambda21() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        SharedConfig.toggleChatBlur();
    }
}
