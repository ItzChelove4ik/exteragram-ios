package com.google.android.gms.cast.framework.media.widget;

import android.widget.ImageView;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;

/* loaded from: classes4.dex */
public interface ControlButtonsContainer {
    ImageView getButtonImageViewAt(int r1);

    int getButtonSlotCount();

    int getButtonTypeAt(int r1);

    UIMediaController getUIMediaController();
}
