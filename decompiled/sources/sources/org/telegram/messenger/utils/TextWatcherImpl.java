package org.telegram.messenger.utils;

import android.text.TextWatcher;

/* loaded from: classes6.dex */
public interface TextWatcherImpl extends TextWatcher {
    @Override // android.text.TextWatcher
    default void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
    }

    @Override // android.text.TextWatcher
    default void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
    }
}
