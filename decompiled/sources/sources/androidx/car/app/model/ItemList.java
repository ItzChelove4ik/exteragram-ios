package androidx.car.app.model;

import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class ItemList {
    private final List<Item> mItems;
    private final CarText mNoItemsMessage;
    private final OnItemVisibilityChangedDelegate mOnItemVisibilityChangedDelegate;
    private final OnSelectedDelegate mOnSelectedDelegate;
    private final int mSelectedIndex;

    public static final class Builder {
        final List<Item> mItems;
        CarText mNoItemsMessage;
        OnItemVisibilityChangedDelegate mOnItemVisibilityChangedDelegate;
        OnSelectedDelegate mOnSelectedDelegate;
        int mSelectedIndex;

        public Builder() {
            this.mItems = new ArrayList();
        }

        public Builder(ItemList r2) {
            this.mSelectedIndex = r2.getSelectedIndex();
            this.mOnSelectedDelegate = r2.getOnSelectedDelegate();
            this.mOnItemVisibilityChangedDelegate = r2.getOnItemVisibilityChangedDelegate();
            this.mNoItemsMessage = r2.getNoItemsMessage();
            this.mItems = new ArrayList(r2.getItems());
        }

        public Builder addItem(Item r2) {
            List<Item> r0 = this.mItems;
            Objects.requireNonNull(r2);
            r0.add(r2);
            return this;
        }

        public ItemList build() {
            if (this.mOnSelectedDelegate == null) goto L25;
            int r0 = this.mItems.size();
            if (r0 != 0) goto L7;
            Segment$$ExternalSyntheticBUOutline1.m("A selectable list cannot be empty");
            return null;
        L7:
            if (this.mSelectedIndex >= r0) goto L21;
            Iterator<Item> r02 = this.mItems.iterator();
        L10:
            if (r02.hasNext() == false) goto L25;
            Item r2 = r02.next();
            if (ItemList.getOnClickDelegate(r2) != null) goto L18;
            if (ItemList.getToggle(r2) == null) goto L10;
            Segment$$ExternalSyntheticBUOutline1.m("Items that belong to selectable lists can't have a toggle");
            return null;
        L18:
            Segment$$ExternalSyntheticBUOutline1.m("Items that belong to selectable lists can't have an onClickListener. Use the OnSelectedListener of the list instead");
            return null;
        L21:
            throw new IllegalStateException("The selected item index (" + this.mSelectedIndex + ") is larger than the size of the list (" + r0 + ")");
        L25:
            return new ItemList(this);
        }

        public Builder clearItems() {
            this.mItems.clear();
            return this;
        }
    }

    public interface OnItemVisibilityChangedListener {
    }

    public interface OnSelectedListener {
    }

    private ItemList() {
        this.mSelectedIndex = 0;
        this.mItems = Collections.EMPTY_LIST;
        this.mNoItemsMessage = null;
        this.mOnSelectedDelegate = null;
        this.mOnItemVisibilityChangedDelegate = null;
    }

    public ItemList(Builder r2) {
        this.mSelectedIndex = r2.mSelectedIndex;
        this.mItems = CollectionUtils.unmodifiableCopy(r2.mItems);
        this.mNoItemsMessage = r2.mNoItemsMessage;
        this.mOnSelectedDelegate = r2.mOnSelectedDelegate;
        this.mOnItemVisibilityChangedDelegate = r2.mOnItemVisibilityChangedDelegate;
    }

    public static OnClickDelegate getOnClickDelegate(Item r1) {
        if ((r1 instanceof Row) == false) goto L7;
        return ((Row) r1).getOnClickDelegate();
    L7:
        if ((r1 instanceof GridItem) == true) goto L9;
        return null;
    L9:
        return ((GridItem) r1).getOnClickDelegate();
    }

    public static Toggle getToggle(Item r1) {
        if ((r1 instanceof Row) == true) goto L5;
        return null;
    L5:
        return ((Row) r1).getToggle();
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof ItemList) == true) goto L8;
        return false;
    L8:
        ItemList r52 = (ItemList) r5;
        if (this.mSelectedIndex == r52.mSelectedIndex) goto L11;
    L35:
        return false;
    L11:
        if (Objects.equals(this.mItems, r52.mItems) == false) goto L35;
        if (this.mOnSelectedDelegate != null) goto L15;
        boolean r1 = true;
    L16:
        Boolean r12 = Boolean.valueOf(r1);
        if (r52.mOnSelectedDelegate != null) goto L19;
        boolean r3 = true;
    L21:
        if (r12.equals(Boolean.valueOf(r3)) == false) goto L35;
        if (this.mOnItemVisibilityChangedDelegate != null) goto L25;
        boolean r13 = true;
    L26:
        Boolean r14 = Boolean.valueOf(r13);
        if (r52.mOnItemVisibilityChangedDelegate != null) goto L29;
        boolean r32 = true;
    L31:
        if (r14.equals(Boolean.valueOf(r32)) == false) goto L35;
        if (Objects.equals(this.mNoItemsMessage, r52.mNoItemsMessage) == false) goto L35;
        return true;
    L29:
        r32 = false;
        goto L31
    L25:
        r13 = false;
        goto L26
    L19:
        r3 = false;
        goto L21
    L15:
        r1 = false;
        goto L16
    }

    public List<Item> getItems() {
        return CollectionUtils.emptyIfNull(this.mItems);
    }

    public CarText getNoItemsMessage() {
        return this.mNoItemsMessage;
    }

    public OnItemVisibilityChangedDelegate getOnItemVisibilityChangedDelegate() {
        return this.mOnItemVisibilityChangedDelegate;
    }

    public OnSelectedDelegate getOnSelectedDelegate() {
        return this.mOnSelectedDelegate;
    }

    public int getSelectedIndex() {
        return this.mSelectedIndex;
    }

    public int hashCode() {
        Integer r0 = Integer.valueOf(this.mSelectedIndex);
        List<Item> r1 = this.mItems;
        boolean r3 = false;
        if (this.mOnSelectedDelegate != null) goto L5;
        boolean r2 = true;
    L6:
        Boolean r22 = Boolean.valueOf(r2);
        if (this.mOnItemVisibilityChangedDelegate != null) goto L10;
        r3 = true;
    L10:
        return Objects.hash(new Object[]{r0, r1, r22, Boolean.valueOf(r3), this.mNoItemsMessage});
    L5:
        r2 = false;
        goto L6
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("[ items: ");
        List<Item> r1 = this.mItems;
        if (r1 == null) goto L5;
        String r12 = r1.toString();
    L6:
        r0.append(r12);
        r0.append(", selected: ");
        r0.append(this.mSelectedIndex);
        r0.append("]");
        return r0.toString();
    L5:
        r12 = null;
        goto L6
    }
}
