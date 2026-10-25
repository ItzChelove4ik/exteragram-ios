package org.telegram.ui.Components.Paint;

import android.graphics.Typeface;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.Paint.PaintTypeface;

/* loaded from: classes3.dex */
public final /* synthetic */ class PaintTypeface$$ExternalSyntheticLambda0 implements PaintTypeface.LazyTypeface.LazyTypefaceLoader {
    public /* synthetic */ PaintTypeface$$ExternalSyntheticLambda0() {
    }

    @Override // org.telegram.ui.Components.Paint.PaintTypeface.LazyTypeface.LazyTypefaceLoader
    public final Typeface load() {
        return AndroidUtilities.bold();
    }
}
