package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class SearchViewPager$$ExternalSyntheticLambda3 implements DialogsActivity.DialogsActivityDelegate {
    public final /* synthetic */ SearchViewPager f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ SearchViewPager$$ExternalSyntheticLambda3(SearchViewPager r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public final boolean didSelectDialogs(DialogsActivity r11, ArrayList r12, CharSequence r13, boolean r14, boolean r15, int r16, int r17, TopicsFragment r18) {
        return SearchViewPager.$r8$lambda$HXzSiwlNEzD9EbeK7omJ49KuHak(this.f$0, this.f$1, r11, r12, r13, r14, r15, r16, r17, r18);
    }
}
