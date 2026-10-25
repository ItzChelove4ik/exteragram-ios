package org.telegram.ui;

import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.MediaDataController;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectAnimatedEmojiDialog$$ExternalSyntheticLambda34 implements MediaDataController.KeywordResultCallback {
    public final /* synthetic */ HashMap f$0;
    public final /* synthetic */ HashMap f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ SelectAnimatedEmojiDialog$$ExternalSyntheticLambda34(HashMap r1, HashMap r2, ArrayList r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.MediaDataController.KeywordResultCallback
    public final void run(ArrayList r7, String r8) {
        SelectAnimatedEmojiDialog.m18614$r8$lambda$OoEVRIOoZ9luIKqwgXBgPt8cmk(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
