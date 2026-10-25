package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.Utilities;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda161 implements MediaDataController.KeywordResultCallback {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ MediaDataController.SearchStickersKey f$1;
    public final /* synthetic */ MediaDataController.SearchStickersResult f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda161(MediaDataController r1, MediaDataController.SearchStickersKey r2, MediaDataController.SearchStickersResult r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.MediaDataController.KeywordResultCallback
    public final void run(ArrayList r7, String r8) {
        MediaDataController.$r8$lambda$rtv3sQv11LEDGPEbXJEV0vc4GkE(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
