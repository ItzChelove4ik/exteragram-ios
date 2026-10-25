package org.telegram.ui.Cells;

import org.telegram.ui.Components.BackupImageView;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoAttachPhotoCell$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ BackupImageView f$0;

    public /* synthetic */ PhotoAttachPhotoCell$$ExternalSyntheticLambda1(BackupImageView r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidate();
    }
}
