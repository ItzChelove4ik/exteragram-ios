package androidx.car.app.navigation.model;

import androidx.car.app.model.ActionStrip;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class MapController {
    private final ActionStrip mMapActionStrip;
    private final PanModeDelegate mPanModeDelegate;

    public static final class Builder {
    }

    private MapController() {
        this.mPanModeDelegate = null;
        this.mMapActionStrip = null;
    }

    public MapController(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof MapController) == true) goto L8;
        return false;
    L8:
        MapController r52 = (MapController) r5;
        if (this.mPanModeDelegate != null) goto L11;
        boolean r1 = true;
    L12:
        Boolean r12 = Boolean.valueOf(r1);
        if (r52.mPanModeDelegate != null) goto L15;
        boolean r3 = true;
    L17:
        if (r12.equals(Boolean.valueOf(r3)) == true) goto L19;
    L21:
        return false;
    L19:
        if (Objects.equals(this.mMapActionStrip, r52.mMapActionStrip) == false) goto L21;
        return true;
    L15:
        r3 = false;
        goto L17
    L11:
        r1 = false;
        goto L12
    }

    public ActionStrip getMapActionStrip() {
        return this.mMapActionStrip;
    }

    public PanModeDelegate getPanModeDelegate() {
        return this.mPanModeDelegate;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mPanModeDelegate, this.mMapActionStrip});
    }
}
