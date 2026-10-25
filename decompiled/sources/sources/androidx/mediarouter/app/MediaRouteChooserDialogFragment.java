package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.mediarouter.media.MediaRouteSelector;
import c.f$$ExternalSyntheticBUOutline1;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class MediaRouteChooserDialogFragment extends DialogFragment {
    private static final String ARGUMENT_SELECTOR = "selector";
    private Dialog mDialog;
    private MediaRouteSelector mSelector;
    private boolean mUseDynamicGroup;

    public MediaRouteChooserDialogFragment() {
        this.mUseDynamicGroup = false;
        setCancelable(true);
    }

    private void ensureRouteSelector() {
        if (this.mSelector != null) goto L11;
        Bundle r0 = getArguments();
        if (r0 == null) goto L8;
        this.mSelector = MediaRouteSelector.fromBundle(r0.getBundle("selector"));
    L8:
        if (this.mSelector != null) goto L12;
        this.mSelector = MediaRouteSelector.EMPTY;
        return;
    L12:
        return;
    }

    public MediaRouteSelector getRouteSelector() {
        ensureRouteSelector();
        return this.mSelector;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        Dialog r12 = this.mDialog;
        if (r12 != null) goto L6;
        return;
    L6:
        if (this.mUseDynamicGroup == false) goto L9;
        ((MediaRouteDynamicChooserDialog) r12).updateLayout();
        return;
    L9:
        ((MediaRouteChooserDialog) r12).updateLayout();
    }

    public MediaRouteChooserDialog onCreateChooserDialog(Context r1, Bundle r2) {
        return new MediaRouteChooserDialog(r1);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle r2) {
        if (this.mUseDynamicGroup == false) goto L5;
        MediaRouteDynamicChooserDialog r22 = onCreateDynamicChooserDialog(getContext());
        this.mDialog = r22;
        r22.setRouteSelector(getRouteSelector());
    L7:
        return this.mDialog;
    L5:
        MediaRouteChooserDialog r23 = onCreateChooserDialog(getContext(), r2);
        this.mDialog = r23;
        r23.setRouteSelector(getRouteSelector());
        goto L7
    }

    public MediaRouteDynamicChooserDialog onCreateDynamicChooserDialog(Context r1) {
        return new MediaRouteDynamicChooserDialog(r1);
    }

    public void setRouteSelector(MediaRouteSelector r4) {
        if (r4 == null) goto L16;
        ensureRouteSelector();
        if (this.mSelector.equals(r4) == true) goto L18;
        this.mSelector = r4;
        Bundle r0 = getArguments();
        if (r0 != null) goto L8;
        r0 = new Bundle();
    L8:
        r0.putBundle("selector", r4.asBundle());
        setArguments(r0);
        Dialog r02 = this.mDialog;
        if (r02 != null) goto L11;
        return;
    L11:
        if (this.mUseDynamicGroup == false) goto L14;
        ((MediaRouteDynamicChooserDialog) r02).setRouteSelector(r4);
        return;
    L14:
        ((MediaRouteChooserDialog) r02).setRouteSelector(r4);
        return;
    L18:
        return;
    L16:
        f$$ExternalSyntheticBUOutline1.m("selector must not be null");
    }

    public void setUseDynamicGroup(boolean r2) {
        if (this.mDialog != null) goto L6;
        this.mUseDynamicGroup = r2;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("This must be called before creating dialog");
    }
}
