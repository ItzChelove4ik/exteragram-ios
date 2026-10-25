package org.telegram.ui.Stars;

import android.view.KeyEvent;
import android.widget.TextView;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsIntroActivity$$ExternalSyntheticLambda62 implements TextView.OnEditorActionListener {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ ButtonWithCounterView f$2;
    public final /* synthetic */ EditTextBoldCursor f$3;
    public final /* synthetic */ BottomSheet[] f$4;

    public /* synthetic */ StarsIntroActivity$$ExternalSyntheticLambda62(boolean[] r1, Utilities.Callback2 r2, ButtonWithCounterView r3, EditTextBoldCursor r4, BottomSheet[] r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView r9, int r10, KeyEvent r11) {
        return StarsIntroActivity.m19169$r8$lambda$PUcLBJ7bNknzyrHru5RbTP3EEQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10, r11);
    }
}
