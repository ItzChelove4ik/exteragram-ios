package org.telegram.ui.bots;

import android.view.View;
import java.util.ArrayList;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.bots.BotStorage;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStorage$$ExternalSyntheticLambda2 implements View.OnClickListener {
    public final /* synthetic */ String[] f$0;
    public final /* synthetic */ BotStorage.StorageConfig f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ ButtonWithCounterView f$3;

    public /* synthetic */ BotStorage$$ExternalSyntheticLambda2(String[] r1, BotStorage.StorageConfig r2, ArrayList r3, ButtonWithCounterView r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r4) {
        BotStorage.$r8$lambda$F0LUj9F8WXBbvF1WTUfplcMfhfg(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
