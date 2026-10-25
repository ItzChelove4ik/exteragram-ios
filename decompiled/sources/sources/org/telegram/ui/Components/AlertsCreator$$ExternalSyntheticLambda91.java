package org.telegram.ui.Components;

import android.view.View;
import java.util.Calendar;
import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.ActionBar.BottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda91 implements View.OnClickListener {
    public final /* synthetic */ long f$0;
    public final /* synthetic */ NumberPicker f$1;
    public final /* synthetic */ NumberPicker f$2;
    public final /* synthetic */ NumberPicker f$3;
    public final /* synthetic */ Calendar f$4;
    public final /* synthetic */ MessagesStorage.IntCallback f$5;
    public final /* synthetic */ BottomSheet.Builder f$6;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda91(long r1, NumberPicker r3, NumberPicker r4, NumberPicker r5, Calendar r6, MessagesStorage.IntCallback r7, BottomSheet.Builder r8) {
        this.f$0 = r1;
        this.f$1 = r3;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r10) {
        AlertsCreator.m8711$r8$lambda$AVvC_MenWh6xxqWt49ZWzfDP2w(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r10);
    }
}
