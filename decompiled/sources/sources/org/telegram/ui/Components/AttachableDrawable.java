package org.telegram.ui.Components;

import android.view.View;
import org.telegram.messenger.ImageReceiver;

/* loaded from: classes3.dex */
public interface AttachableDrawable {
    void onAttachedToWindow(ImageReceiver r1);

    void onDetachedFromWindow(ImageReceiver r1);

    default void setParent(View r1) {
    }
}
