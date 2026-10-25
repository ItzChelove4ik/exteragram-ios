package androidx.mediarouter.app;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ActionProvider;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.mediarouter.media.MediaRouterParams;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class MediaRouteActionProvider extends ActionProvider {
    private static final String TAG = "MRActionProvider";
    private MediaRouteButton mButton;
    private MediaRouteDialogFactory mDialogFactory;
    private final MediaRouter mRouter;
    private MediaRouteSelector mSelector;

    public MediaRouteActionProvider(Context r2) {
        super(r2);
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mDialogFactory = MediaRouteDialogFactory.getDefault();
        this.mRouter = MediaRouter.getInstance(r2);
    }

    @Deprecated
    public void enableDynamicGroup() {
        MediaRouterParams r0 = this.mRouter.getRouterParams();
        if (r0 != null) goto L5;
        MediaRouterParams.Builder r02 = new MediaRouterParams.Builder();
    L6:
        r02.setDialogType(2);
        this.mRouter.setRouterParams(r02.build());
        return;
    L5:
        r02 = new MediaRouterParams.Builder(r0);
        goto L6
    }

    public MediaRouteDialogFactory getDialogFactory() {
        return this.mDialogFactory;
    }

    public MediaRouteButton getMediaRouteButton() {
        return this.mButton;
    }

    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }

    @Override // androidx.core.view.ActionProvider
    public View onCreateActionView() {
        if (this.mButton == null) goto L5;
        Log.e(TAG, "onCreateActionView: this ActionProvider is already associated with a menu item. Don't reuse MediaRouteActionProvider instances! Abandoning the old menu item...");
    L5:
        MediaRouteButton r0 = onCreateMediaRouteButton();
        this.mButton = r0;
        r0.setCheatSheetEnabled(true);
        this.mButton.setRouteSelector(this.mSelector);
        this.mButton.setDialogFactory(this.mDialogFactory);
        this.mButton.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
        return this.mButton;
    }

    public MediaRouteButton onCreateMediaRouteButton() {
        return new MediaRouteButton(getContext());
    }

    @Override // androidx.core.view.ActionProvider
    public boolean onPerformDefaultAction() {
        MediaRouteButton r0 = this.mButton;
        if (r0 != null) goto L5;
        return false;
    L5:
        return r0.showDialog();
    }

    @Deprecated
    public void setAlwaysVisible(boolean r1) {
    }

    public void setDialogFactory(MediaRouteDialogFactory r2) {
        if (r2 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("factory must not be null");
        return;
    L4:
        if (this.mDialogFactory == r2) goto L11;
        this.mDialogFactory = r2;
        MediaRouteButton r1 = this.mButton;
        if (r1 == null) goto L12;
        r1.setDialogFactory(r2);
        return;
    L12:
        return;
    }

    public void setRouteSelector(MediaRouteSelector r2) {
        if (r2 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("selector must not be null");
        return;
    L4:
        if (this.mSelector.equals(r2) == true) goto L11;
        this.mSelector = r2;
        MediaRouteButton r1 = this.mButton;
        if (r1 == null) goto L12;
        r1.setRouteSelector(r2);
        return;
    L12:
        return;
    }
}
