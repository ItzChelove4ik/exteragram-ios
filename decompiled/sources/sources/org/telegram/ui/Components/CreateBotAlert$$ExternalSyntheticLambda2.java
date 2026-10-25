package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class CreateBotAlert$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ String[] f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ Utilities.Callback f$10;
    public final /* synthetic */ BottomSheet f$11;
    public final /* synthetic */ Theme.ResourcesProvider f$12;
    public final /* synthetic */ Context f$13;
    public final /* synthetic */ EditTextCell f$2;
    public final /* synthetic */ int[] f$3;
    public final /* synthetic */ ButtonWithCounterView f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ TLRPC.User f$7;
    public final /* synthetic */ int[] f$8;
    public final /* synthetic */ boolean[] f$9;

    public /* synthetic */ CreateBotAlert$$ExternalSyntheticLambda2(String[] r1, Runnable r2, EditTextCell r3, int[] r4, ButtonWithCounterView r5, boolean r6, int r7, TLRPC.User r8, int[] r9, boolean[] r10, Utilities.Callback r11, BottomSheet r12, Theme.ResourcesProvider r13, Context r14) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
        this.f$10 = r11;
        this.f$11 = r12;
        this.f$12 = r13;
        this.f$13 = r14;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CreateBotAlert.$r8$lambda$Qpj78b_0NziE56xjf31rIY9Vfg8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13);
    }
}
