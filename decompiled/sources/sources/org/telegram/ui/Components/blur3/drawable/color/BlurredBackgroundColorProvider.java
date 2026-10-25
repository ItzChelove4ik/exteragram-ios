package org.telegram.ui.Components.blur3.drawable.color;

import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public interface BlurredBackgroundColorProvider {
    int getBackgroundColor();

    int getShadowColor();

    int getStrokeColorBottom();

    default int getStrokeColorFull() {
        return Theme.getDividerColor(null);
    }

    int getStrokeColorTop();
}
