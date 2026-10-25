package org.telegram.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import org.telegram.messenger.MediaDataController;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectAnimatedEmojiDialog$$ExternalSyntheticLambda36 implements MediaDataController.KeywordResultCallback {
    public final /* synthetic */ SelectAnimatedEmojiDialog f$0;
    public final /* synthetic */ LinkedHashSet f$1;
    public final /* synthetic */ HashMap f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ SelectAnimatedEmojiDialog$$ExternalSyntheticLambda36(SelectAnimatedEmojiDialog r1, LinkedHashSet r2, HashMap r3, ArrayList r4, Runnable r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.MediaDataController.KeywordResultCallback
    public final void run(ArrayList r8, String r9) {
        SelectAnimatedEmojiDialog.m18613$r8$lambda$GIYyWF4QWJObs3FyNSJshjwPCQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
