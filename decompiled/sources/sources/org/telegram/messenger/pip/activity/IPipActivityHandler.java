package org.telegram.messenger.pip.activity;

import android.app.PictureInPictureParams;
import android.app.PictureInPictureUiState;
import android.content.res.Configuration;

/* loaded from: classes.dex */
public interface IPipActivityHandler {
    void onConfigurationChanged(Configuration r1);

    void onPause();

    void onPictureInPictureModeChanged(boolean r1, Configuration r2);

    void onPictureInPictureRequested();

    void onPictureInPictureUiStateChanged(PictureInPictureUiState r1);

    void onResume();

    void onStart();

    void onStop();

    void onUserLeaveHint();

    void setPictureInPictureParams(PictureInPictureParams r1);
}
