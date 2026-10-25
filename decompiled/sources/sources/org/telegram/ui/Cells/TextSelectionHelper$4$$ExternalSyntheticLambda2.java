package org.telegram.ui.Cells;

import android.view.Menu;
import org.telegram.messenger.LanguageDetector;
import org.telegram.ui.Cells.TextSelectionHelper;

/* loaded from: classes6.dex */
public final /* synthetic */ class TextSelectionHelper$4$$ExternalSyntheticLambda2 implements LanguageDetector.ExceptionCallback {
    public final /* synthetic */ TextSelectionHelper.AnonymousClass4 f$0;
    public final /* synthetic */ Menu f$1;

    public /* synthetic */ TextSelectionHelper$4$$ExternalSyntheticLambda2(TextSelectionHelper.AnonymousClass4 r1, Menu r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.LanguageDetector.ExceptionCallback
    public final void run(Exception r2) {
        TextSelectionHelper.AnonymousClass4.$r8$lambda$eEPR_GqdxxqRHZuG_JPz_Tff7C4(this.f$0, this.f$1, r2);
    }
}
