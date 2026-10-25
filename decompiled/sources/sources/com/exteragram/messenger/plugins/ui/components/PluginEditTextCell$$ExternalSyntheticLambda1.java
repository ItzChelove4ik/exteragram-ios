package com.exteragram.messenger.plugins.ui.components;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public final /* synthetic */ class PluginEditTextCell$$ExternalSyntheticLambda1 implements InputFilter {
    public final /* synthetic */ Pattern f$0;

    public /* synthetic */ PluginEditTextCell$$ExternalSyntheticLambda1(Pattern r1) {
        this.f$0 = r1;
    }

    @Override // android.text.InputFilter
    public final CharSequence filter(CharSequence r1, int r2, int r3, Spanned r4, int r5, int r6) {
        return PluginEditTextCell.m1609$r8$lambda$yi_0eYqsHGvmzLBjgazYnBufaA(this.f$0, r1, r2, r3, r4, r5, r6);
    }
}
