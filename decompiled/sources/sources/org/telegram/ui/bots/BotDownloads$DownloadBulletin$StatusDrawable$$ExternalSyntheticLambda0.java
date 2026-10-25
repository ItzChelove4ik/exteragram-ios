package org.telegram.ui.bots;

import org.telegram.ui.bots.BotDownloads;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotDownloads$DownloadBulletin$StatusDrawable$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ BotDownloads.DownloadBulletin.StatusDrawable f$0;

    public /* synthetic */ BotDownloads$DownloadBulletin$StatusDrawable$$ExternalSyntheticLambda0(BotDownloads.DownloadBulletin.StatusDrawable r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidateSelf();
    }
}
