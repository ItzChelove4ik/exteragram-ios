package androidx.car.app.model;

import androidx.car.app.model.constraints.CarTextConstraints;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class SectionedItemList {
    private final CarText mHeader;
    private final ItemList mItemList;

    private SectionedItemList() {
        this.mItemList = null;
        this.mHeader = null;
    }

    private SectionedItemList(ItemList r1, CarText r2) {
        this.mItemList = r1;
        this.mHeader = r2;
    }

    public static SectionedItemList create(ItemList r1, CharSequence r2) {
        Objects.requireNonNull(r2);
        CarText r22 = CarText.create(r2);
        CarTextConstraints.TEXT_ONLY.validateOrThrow(r22);
        Objects.requireNonNull(r1);
        return new SectionedItemList(r1, r22);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof SectionedItemList) == true) goto L8;
        return false;
    L8:
        SectionedItemList r52 = (SectionedItemList) r5;
        if (Objects.equals(this.mItemList, r52.mItemList) == true) goto L11;
    L13:
        return false;
    L11:
        if (Objects.equals(this.mHeader, r52.mHeader) == false) goto L13;
        return true;
    }

    public CarText getHeader() {
        CarText r0 = this.mHeader;
        Objects.requireNonNull(r0);
        return r0;
    }

    public ItemList getItemList() {
        ItemList r0 = this.mItemList;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mItemList, this.mHeader});
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("[ items: ");
        r0.append(this.mItemList);
        r0.append(", has header: ");
        if (this.mHeader == null) goto L5;
        boolean r2 = true;
    L6:
        r0.append(r2);
        r0.append("]");
        return r0.toString();
    L5:
        r2 = false;
        goto L6
    }
}
