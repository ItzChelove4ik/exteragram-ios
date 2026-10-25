package org.telegram.messenger.pip.source;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/* loaded from: classes.dex */
public interface IPipSourceDelegate {
    View pipCreatePictureInPictureView();

    Bitmap pipCreatePictureInPictureViewBitmap();

    Bitmap pipCreatePrimaryWindowViewBitmap();

    void pipHidePrimaryWindowView(Runnable r1);

    default boolean pipIsAvailable() {
        return true;
    }

    default void pipRenderBackground(Canvas r1) {
    }

    default void pipRenderForeground(Canvas r1) {
    }

    void pipShowPrimaryWindowView(Runnable r1);
}
