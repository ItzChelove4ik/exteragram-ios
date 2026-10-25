package org.telegram.ui;

import com.exteragram.messenger.components.ReverseImageSearchSheet;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda66 implements Utilities.Callback {
    public final /* synthetic */ PhotoViewer f$0;
    public final /* synthetic */ Theme.ResourcesProvider f$1;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda66(PhotoViewer r1, Theme.ResourcesProvider r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        PhotoViewer.$r8$lambda$wK_CZyVuf8ZXBm3aQoTILvffxzQ(this.f$0, this.f$1, (ReverseImageSearchSheet.Provider) r2);
    }
}
