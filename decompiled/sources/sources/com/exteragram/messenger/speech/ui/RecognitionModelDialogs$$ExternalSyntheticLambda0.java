package com.exteragram.messenger.speech.ui;

import com.exteragram.messenger.utils.ui.PopupUtils;
import java.util.List;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes4.dex */
public final /* synthetic */ class RecognitionModelDialogs$$ExternalSyntheticLambda0 implements PopupUtils.OnItemClickListener {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ RecognitionModelDialogs$$ExternalSyntheticLambda0(List r1, BaseFragment r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.exteragram.messenger.utils.ui.PopupUtils.OnItemClickListener
    public final void onClick(int r3) {
        RecognitionModelDialogs.$r8$lambda$eBXgfm79L1j0AVJI557h5mvN69s(this.f$0, this.f$1, this.f$2, r3);
    }
}
