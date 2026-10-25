package androidx.car.app.navigation.model;

import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.Header;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.Pane;
import androidx.car.app.model.Template;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class MapTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Header mHeader;
    private final ItemList mItemList;
    private final MapController mMapController;
    private final Pane mPane;

    public static final class Builder {
    }

    private MapTemplate() {
        this.mMapController = null;
        this.mPane = null;
        this.mItemList = null;
        this.mHeader = null;
        this.mActionStrip = null;
    }

    public MapTemplate(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof MapTemplate) == true) goto L8;
        return false;
    L8:
        MapTemplate r52 = (MapTemplate) r5;
        if (Objects.equals(this.mPane, r52.mPane) == true) goto L11;
    L19:
        return false;
    L11:
        if (Objects.equals(this.mItemList, r52.mItemList) == false) goto L19;
        if (Objects.equals(this.mHeader, r52.mHeader) == false) goto L19;
        if (Objects.equals(this.mMapController, r52.mMapController) == false) goto L19;
        if (Objects.equals(this.mActionStrip, r52.mActionStrip) == false) goto L19;
        return true;
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    public Header getHeader() {
        return this.mHeader;
    }

    public ItemList getItemList() {
        return this.mItemList;
    }

    public MapController getMapController() {
        return this.mMapController;
    }

    public Pane getPane() {
        return this.mPane;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mMapController, this.mPane, this.mItemList, this.mHeader, this.mActionStrip});
    }
}
