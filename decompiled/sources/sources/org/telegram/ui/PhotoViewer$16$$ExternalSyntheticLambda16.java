package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$16$$ExternalSyntheticLambda16 implements DialogsActivity.DialogsActivityDelegate {
    public final /* synthetic */ PhotoViewer.AnonymousClass16 f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ ChatActivity f$3;

    public /* synthetic */ PhotoViewer$16$$ExternalSyntheticLambda16(PhotoViewer.AnonymousClass16 r1, ArrayList r2, boolean r3, ChatActivity r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public final boolean didSelectDialogs(DialogsActivity r13, ArrayList r14, CharSequence r15, boolean r16, boolean r17, int r18, int r19, TopicsFragment r20) {
        return PhotoViewer.AnonymousClass16.$r8$lambda$UvkaJW620ilPX1Hbr4GBbm2FCUI(this.f$0, this.f$1, this.f$2, this.f$3, r13, r14, r15, r16, r17, r18, r19, r20);
    }
}
