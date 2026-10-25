package androidx.car.app.model.constraints;

import androidx.car.app.messaging.model.ConversationItem;
import androidx.car.app.model.Item;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.Row;
import androidx.car.app.model.SectionedItemList;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class RowListConstraints {
    public static final RowListConstraints MAP_ROW_LIST_CONSTRAINTS_ALLOW_SELECTABLE = null;
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_CONSERVATIVE = null;
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_FULL_LIST = null;
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_PANE = null;

    @Deprecated
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_ROUTE_PREVIEW = null;
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_SIMPLE = null;
    private final boolean mAllowSelectableLists;
    private final int mMaxActions;
    private final RowConstraints mRowConstraints;

    public static final class Builder {
        boolean mAllowSelectableLists;
        int mMaxActions;
        RowConstraints mRowConstraints;

        public Builder() {
            this.mRowConstraints = RowConstraints.UNCONSTRAINED;
        }

        public Builder(RowListConstraints r2) {
            this.mRowConstraints = RowConstraints.UNCONSTRAINED;
            Objects.requireNonNull(r2);
            this.mMaxActions = r2.getMaxActions();
            this.mRowConstraints = r2.getRowConstraints();
            this.mAllowSelectableLists = r2.isAllowSelectableLists();
        }

        public RowListConstraints build() {
            return new RowListConstraints(this);
        }

        public Builder setAllowSelectableLists(boolean r1) {
            this.mAllowSelectableLists = r1;
            return this;
        }

        public Builder setMaxActions(int r1) {
            this.mMaxActions = r1;
            return this;
        }

        public Builder setRowConstraints(RowConstraints r1) {
            this.mRowConstraints = r1;
            return this;
        }
    }

    static {
        RowListConstraints r0 = new Builder().setMaxActions(0).setRowConstraints(RowConstraints.ROW_CONSTRAINTS_CONSERVATIVE).setAllowSelectableLists(false).build();
        ROW_LIST_CONSTRAINTS_CONSERVATIVE = r0;
        ROW_LIST_CONSTRAINTS_PANE = new Builder(r0).setMaxActions(2).setRowConstraints(RowConstraints.ROW_CONSTRAINTS_PANE).setAllowSelectableLists(false).build();
        Builder r1 = new Builder(r0);
        RowConstraints r2 = RowConstraints.ROW_CONSTRAINTS_SIMPLE;
        ROW_LIST_CONSTRAINTS_SIMPLE = r1.setRowConstraints(r2).build();
        ROW_LIST_CONSTRAINTS_ROUTE_PREVIEW = new Builder(r0).setRowConstraints(r2).setAllowSelectableLists(true).build();
        MAP_ROW_LIST_CONSTRAINTS_ALLOW_SELECTABLE = new Builder(r0).setRowConstraints(r2).setAllowSelectableLists(true).build();
        ROW_LIST_CONSTRAINTS_FULL_LIST = new Builder(r0).setRowConstraints(RowConstraints.ROW_CONSTRAINTS_FULL_LIST).setAllowSelectableLists(true).build();
    }

    public RowListConstraints(Builder r2) {
        this.mMaxActions = r2.mMaxActions;
        this.mRowConstraints = r2.mRowConstraints;
        this.mAllowSelectableLists = r2.mAllowSelectableLists;
    }

    private void validateRows(List<? extends Item> r3) {
        Iterator<? extends Item> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L20;
        Item r0 = r32.next();
        if ((r0 instanceof Row) == true) goto L7;
        if ((r0 instanceof ConversationItem) == true) goto L4;
        FieldSet$$ExternalSyntheticBUOutline0.m("Unsupported item type: %s", new Object[]{r0.getClass().getSimpleName()});
        return;
    L7:
        this.mRowConstraints.validateOrThrow((Row) r0);
        goto L4
    }

    public int getMaxActions() {
        return this.mMaxActions;
    }

    public RowConstraints getRowConstraints() {
        return this.mRowConstraints;
    }

    public boolean isAllowSelectableLists() {
        return this.mAllowSelectableLists;
    }

    public void validateOrThrow(ItemList r2) {
        if (r2.getOnSelectedDelegate() != null) goto L5;
    L9:
        validateRows(r2.getItems());
        return;
    L5:
        if (this.mAllowSelectableLists == true) goto L9;
        f$$ExternalSyntheticBUOutline1.m("Selectable lists are not allowed");
    }

    public void validateOrThrow(List<SectionedItemList> r4) {
        ArrayList r0 = new ArrayList();
        Iterator<SectionedItemList> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L13;
        ItemList r1 = r42.next().getItemList();
        if (r1.getOnSelectedDelegate() == null) goto L12;
        if (this.mAllowSelectableLists == true) goto L12;
        f$$ExternalSyntheticBUOutline1.m("Selectable lists are not allowed");
        return;
    L12:
        r0.addAll(r1.getItems());
        goto L4
    L13:
        validateRows(r0);
    }
}
