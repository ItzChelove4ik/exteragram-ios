package androidx.car.app.model;

import androidx.car.app.utils.CollectionUtils;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Pane {
    private final List<Action> mActionList;
    private final CarIcon mImage;
    private final boolean mIsLoading;
    private final List<Row> mRows;

    public static final class Builder {
    }

    private Pane() {
        List r0 = Collections.EMPTY_LIST;
        this.mRows = r0;
        this.mActionList = r0;
        this.mIsLoading = false;
        this.mImage = null;
    }

    public Pane(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Pane) == true) goto L8;
        return false;
    L8:
        Pane r52 = (Pane) r5;
        if (this.mIsLoading == r52.mIsLoading) goto L11;
    L17:
        return false;
    L11:
        if (Objects.equals(this.mActionList, r52.mActionList) == false) goto L17;
        if (Objects.equals(this.mRows, r52.mRows) == false) goto L17;
        if (Objects.equals(this.mImage, r52.mImage) == false) goto L17;
        return true;
    }

    public List<Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActionList);
    }

    public CarIcon getImage() {
        return this.mImage;
    }

    public List<Row> getRows() {
        return CollectionUtils.emptyIfNull(this.mRows);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mRows, this.mActionList, Boolean.valueOf(this.mIsLoading), this.mImage});
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("[ rows: ");
        List<Row> r1 = this.mRows;
        if (r1 == null) goto L5;
        String r12 = r1.toString();
    L6:
        r0.append(r12);
        r0.append(", action list: ");
        r0.append(this.mActionList);
        r0.append("]");
        return r0.toString();
    L5:
        r12 = null;
        goto L6
    }
}
