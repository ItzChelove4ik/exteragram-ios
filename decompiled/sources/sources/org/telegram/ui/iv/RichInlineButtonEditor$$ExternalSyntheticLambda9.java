package org.telegram.ui.iv;

import android.content.Context;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.iv.RichEditorListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class RichInlineButtonEditor$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;
    public final /* synthetic */ RichEditorListView.BlockButtonEdit f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ RichInlineButtonEditor$$ExternalSyntheticLambda9(BaseFragment r1, Context r2, Theme.ResourcesProvider r3, RichEditorListView.BlockButtonEdit r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RichInlineButtonEditor.m21880$r8$lambda$yBW9ItDuAOpZHZsSd2izABCyuA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
