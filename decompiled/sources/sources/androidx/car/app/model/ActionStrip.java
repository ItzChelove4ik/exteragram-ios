package androidx.car.app.model;

import androidx.car.app.utils.CollectionUtils;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class ActionStrip {
    private final List<Action> mActions;

    public static final class Builder {
    }

    private ActionStrip() {
        this.mActions = Collections.EMPTY_LIST;
    }

    public ActionStrip(Builder r1) {
        throw null;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof ActionStrip) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mActions, ((ActionStrip) r2).mActions);
    }

    public List<Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActions);
    }

    public Action getFirstActionOfType(int r3) {
        Iterator<Action> r2 = this.mActions.iterator();
    L4:
        if (r2.hasNext() == false) goto L10;
        Action r0 = r2.next();
        if ((r0 instanceof Action) == false) goto L4;
        Action r02 = r0;
        if (r02.getType() != r3) goto L4;
        return r02;
    L10:
        return null;
    }

    public int hashCode() {
        return Objects.hashCode(this.mActions);
    }

    public String toString() {
        return "[action count: " + this.mActions.size() + "]";
    }
}
