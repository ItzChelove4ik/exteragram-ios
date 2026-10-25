package org.telegram.ui.Components;

import org.telegram.messenger.SavedMessagesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class BulletinFactory$$ExternalSyntheticLambda7 implements Runnable {
    public /* synthetic */ BulletinFactory$$ExternalSyntheticLambda7() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        SavedMessagesController.openSavedMessagesReminders();
    }
}
