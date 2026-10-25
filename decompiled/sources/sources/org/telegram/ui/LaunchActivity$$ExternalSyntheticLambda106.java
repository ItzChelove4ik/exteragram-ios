package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.DialogsActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda106 implements DialogsActivity.DialogsActivityDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.User f$3;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda106(LaunchActivity r1, String r2, int r3, TLRPC.User r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public final boolean didSelectDialogs(DialogsActivity r13, ArrayList r14, CharSequence r15, boolean r16, boolean r17, int r18, int r19, TopicsFragment r20) {
        return LaunchActivity.m15639$r8$lambda$poDpH5Shvt6JNfvyx0gSpLrA4Y(this.f$0, this.f$1, this.f$2, this.f$3, r13, r14, r15, r16, r17, r18, r19, r20);
    }
}
