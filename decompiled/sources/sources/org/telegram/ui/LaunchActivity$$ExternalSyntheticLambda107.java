package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.DialogsActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda107 implements DialogsActivity.DialogsActivityDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ DialogsActivity f$5;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda107(LaunchActivity r1, int r2, TLRPC.User r3, String r4, String r5, DialogsActivity r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public final boolean didSelectDialogs(DialogsActivity r15, ArrayList r16, CharSequence r17, boolean r18, boolean r19, int r20, int r21, TopicsFragment r22) {
        return LaunchActivity.$r8$lambda$GG9lmjRIT5yRH4MkNvlHRGQ2Mjw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r15, r16, r17, r18, r19, r20, r21, r22);
    }
}
