package androidx.car.app.navigation.model;

import androidx.car.app.utils.CollectionUtils;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Lane {
    private final List<LaneDirection> mDirections;

    private Lane() {
        this.mDirections = Collections.EMPTY_LIST;
    }

    public Lane(List<LaneDirection> r1) {
        this.mDirections = CollectionUtils.unmodifiableCopy(r1);
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof Lane) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mDirections, ((Lane) r2).mDirections);
    }

    public List<LaneDirection> getDirections() {
        return CollectionUtils.emptyIfNull(this.mDirections);
    }

    public int hashCode() {
        return Objects.hashCode(this.mDirections);
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("[direction count: ");
        List<LaneDirection> r2 = this.mDirections;
        if (r2 == null) goto L5;
        int r22 = r2.size();
    L6:
        r0.append(r22);
        r0.append("]");
        return r0.toString();
    L5:
        r22 = 0;
        goto L6
    }
}
