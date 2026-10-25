package androidx.mediarouter.app;

/* loaded from: classes4.dex */
public class MediaRouteDialogFactory {
    private static final MediaRouteDialogFactory sDefault = null;

    static {
        sDefault = new MediaRouteDialogFactory();
    }

    public MediaRouteDialogFactory() {
    }

    public static MediaRouteDialogFactory getDefault() {
        return sDefault;
    }

    public MediaRouteChooserDialogFragment onCreateChooserDialogFragment() {
        return new MediaRouteChooserDialogFragment();
    }

    public MediaRouteControllerDialogFragment onCreateControllerDialogFragment() {
        return new MediaRouteControllerDialogFragment();
    }
}
