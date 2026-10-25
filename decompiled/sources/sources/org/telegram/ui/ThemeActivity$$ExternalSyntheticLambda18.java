package org.telegram.ui;

import android.app.TimePickerDialog;
import android.widget.TimePicker;
import org.telegram.ui.Cells.TextSettingsCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ThemeActivity$$ExternalSyntheticLambda18 implements TimePickerDialog.OnTimeSetListener {
    public final /* synthetic */ ThemeActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TextSettingsCell f$2;

    public /* synthetic */ ThemeActivity$$ExternalSyntheticLambda18(ThemeActivity r1, int r2, TextSettingsCell r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.app.TimePickerDialog.OnTimeSetListener
    public final void onTimeSet(TimePicker r7, int r8, int r9) {
        ThemeActivity.$r8$lambda$Ztl4Fwxp0664fxoajR0BsGe0Jg8(this.f$0, this.f$1, this.f$2, r7, r8, r9);
    }
}
