package org.telegram.ui.Components.Paint;

import androidx.core.util.Consumer;
import org.telegram.ui.Components.Paint.ColorPickerBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class ColorPickerBottomSheet$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ ColorPickerBottomSheet.PipetteDelegate f$0;

    public /* synthetic */ ColorPickerBottomSheet$$ExternalSyntheticLambda2(ColorPickerBottomSheet.PipetteDelegate r1) {
        this.f$0 = r1;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r1) {
        this.f$0.onColorSelected(((Integer) r1).intValue());
    }
}
