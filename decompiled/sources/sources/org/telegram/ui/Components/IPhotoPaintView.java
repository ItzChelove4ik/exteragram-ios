package org.telegram.ui.Components;

import android.view.View;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes7.dex */
public interface IPhotoPaintView {
    /* JADX WARN: Multi-variable type inference failed */
    default View getView() {
        if ((this instanceof View) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("You should override getView() if you're not inheriting from it.");
        return null;
    L5:
        return (View) this;
    }

    default void setOffsetTranslationX(float r1) {
    }
}
