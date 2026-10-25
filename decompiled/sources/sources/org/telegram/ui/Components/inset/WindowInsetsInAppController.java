package org.telegram.ui.Components.inset;

import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes3.dex */
public interface WindowInsetsInAppController {
    void requestInAppKeyboardHeight(int r1);

    default void requestInAppKeyboardHeightIncludeNavbar(int r2) {
        if (r2 <= 0) goto L5;
        requestInAppKeyboardHeight(r2 + AndroidUtilities.navigationBarHeight);
        return;
    L5:
        resetInAppKeyboardHeight(true);
    }

    void resetInAppKeyboardHeight(boolean r1);
}
