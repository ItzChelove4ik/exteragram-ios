package org.telegram.ui.Stories.recorder;

import java.util.ArrayList;
import org.telegram.messenger.MediaDataController;
import org.telegram.ui.Stories.recorder.EmojiBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiBottomSheet$Page$Adapter$$ExternalSyntheticLambda3 implements MediaDataController.KeywordResultCallback {
    public final /* synthetic */ EmojiBottomSheet.Page.Adapter f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ MediaDataController f$2;

    public /* synthetic */ EmojiBottomSheet$Page$Adapter$$ExternalSyntheticLambda3(EmojiBottomSheet.Page.Adapter r1, String r2, MediaDataController r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.MediaDataController.KeywordResultCallback
    public final void run(ArrayList r3, String r4) {
        EmojiBottomSheet.Page.Adapter.$r8$lambda$G18FCpCGB4eibwiEBMXbcPVTaOU(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
