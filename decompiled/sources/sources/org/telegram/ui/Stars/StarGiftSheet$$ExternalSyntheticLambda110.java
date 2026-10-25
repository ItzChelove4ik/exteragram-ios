package org.telegram.ui.Stars;

import java.util.ArrayList;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda110 implements DialogsActivity.DialogsActivityDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$1;
    public final /* synthetic */ DialogsActivity f$2;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda110(StarGiftSheet r1, TL_stars.TL_starGiftUnique r2, DialogsActivity r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public final boolean didSelectDialogs(DialogsActivity r12, ArrayList r13, CharSequence r14, boolean r15, boolean r16, int r17, int r18, TopicsFragment r19) {
        return StarGiftSheet.$r8$lambda$Zjtfq5CPZsYHnXpGy7nbos107zc(this.f$0, this.f$1, this.f$2, r12, r13, r14, r15, r16, r17, r18, r19);
    }
}
