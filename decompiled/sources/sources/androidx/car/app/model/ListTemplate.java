package androidx.car.app.model;

import androidx.car.app.messaging.model.ConversationItem;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.model.constraints.RowListConstraints;
import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class ListTemplate implements Template {
    static final int MAX_ALLOWED_ITEMS = 100;
    static final int MAX_MESSAGES_PER_CONVERSATION = 10;
    private final ActionStrip mActionStrip;
    private final List<Action> mActions;
    private final Action mHeaderAction;
    private final boolean mIsLoading;
    private final List<SectionedItemList> mSectionedLists;
    private final ItemList mSingleList;
    private final CarText mTitle;

    public static final class Builder {
        ActionStrip mActionStrip;
        final List<Action> mActions;
        boolean mHasSelectableList;
        Action mHeaderAction;
        boolean mIsLoading;
        final List<SectionedItemList> mSectionedLists;
        ItemList mSingleList;
        CarText mTitle;

        public Builder() {
            this.mSectionedLists = new ArrayList();
            this.mActions = new ArrayList();
        }

        public Builder(ListTemplate r3) {
            this.mIsLoading = r3.isLoading();
            this.mHeaderAction = r3.getHeaderAction();
            this.mTitle = r3.getTitle();
            this.mSingleList = r3.getSingleList();
            this.mSectionedLists = new ArrayList(r3.getSectionedLists());
            this.mActionStrip = r3.getActionStrip();
            this.mActions = new ArrayList(r3.getActions());
        }

        public ListTemplate build() {
            if (this.mSingleList == null) goto L5;
        L8:
            boolean r0 = true;
        L10:
            if (this.mIsLoading == r0) goto L26;
            if (r0 == false) goto L19;
            if (this.mSectionedLists.isEmpty() == true) goto L15;
            RowListConstraints.ROW_LIST_CONSTRAINTS_FULL_LIST.validateOrThrow(this.mSectionedLists);
            goto L19
        L15:
            ItemList r02 = this.mSingleList;
            if (r02 == null) goto L19;
            RowListConstraints.ROW_LIST_CONSTRAINTS_FULL_LIST.validateOrThrow(r02);
        L19:
            if (this.mSectionedLists.isEmpty() == true) goto L21;
            List<SectionedItemList> r03 = ListTemplate.getTruncatedCopy(this.mSectionedLists);
            this.mSectionedLists.clear();
            this.mSectionedLists.addAll(r03);
        L25:
            return new ListTemplate(this);
        L21:
            ItemList r04 = this.mSingleList;
            if (r04 == null) goto L25;
            this.mSingleList = ListTemplate.truncate(r04, new TruncateCounter(100));
            goto L25
        L26:
            Segment$$ExternalSyntheticBUOutline1.m("Template is in a loading state but lists are added, or vice versa");
            return null;
        L5:
            if (this.mSectionedLists.isEmpty() == false) goto L8;
            r0 = false;
            goto L10
        }

        public Builder setHeaderAction(Action r3) {
            ActionsConstraints r0 = ActionsConstraints.ACTIONS_CONSTRAINTS_HEADER;
            Objects.requireNonNull(r3);
            r0.validateOrThrow(Collections.singletonList(r3));
            this.mHeaderAction = r3;
            return this;
        }

        public Builder setLoading(boolean r1) {
            this.mIsLoading = r1;
            return this;
        }

        public Builder setSingleList(ItemList r1) {
            Objects.requireNonNull(r1);
            this.mSingleList = r1;
            this.mSectionedLists.clear();
            this.mHasSelectableList = false;
            return this;
        }

        public Builder setTitle(CharSequence r2) {
            Objects.requireNonNull(r2);
            CarText r22 = CarText.create(r2);
            this.mTitle = r22;
            CarTextConstraints.TEXT_ONLY.validateOrThrow(r22);
            return this;
        }
    }

    public static class TruncateCounter {
        private int mRemainingItems;

        public TruncateCounter(int r1) {
            this.mRemainingItems = r1;
        }

        public boolean canFit(int r1) {
            if (this.mRemainingItems < r1) goto L6;
            return true;
        L6:
            return false;
        }

        public int decrement() {
            int r0 = this.mRemainingItems - 1;
            this.mRemainingItems = r0;
            return r0;
        }

        public int decrement(int r2) {
            int r0 = this.mRemainingItems - r2;
            this.mRemainingItems = r0;
            return r0;
        }

        public int remainingItems() {
            return this.mRemainingItems;
        }
    }

    private ListTemplate() {
        this.mIsLoading = false;
        this.mTitle = null;
        this.mHeaderAction = null;
        this.mSingleList = null;
        List r1 = Collections.EMPTY_LIST;
        this.mSectionedLists = r1;
        this.mActionStrip = null;
        this.mActions = r1;
    }

    public ListTemplate(Builder r2) {
        this.mIsLoading = r2.mIsLoading;
        this.mTitle = r2.mTitle;
        this.mHeaderAction = r2.mHeaderAction;
        this.mSingleList = r2.mSingleList;
        this.mSectionedLists = CollectionUtils.unmodifiableCopy(r2.mSectionedLists);
        this.mActionStrip = r2.mActionStrip;
        this.mActions = CollectionUtils.unmodifiableCopy(r2.mActions);
    }

    public static List<SectionedItemList> getTruncatedCopy(List<SectionedItemList> r4) {
        TruncateCounter r0 = new TruncateCounter(100);
        ArrayList r1 = new ArrayList();
        Iterator<SectionedItemList> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L7;
        SectionedItemList r2 = r42.next();
        r1.add(SectionedItemList.create(truncate(r2.getItemList(), r0), r2.getHeader().toCharSequence()));
        if (r0.remainingItems() > 0) goto L4;
    L7:
        return r1;
    }

    public static ItemList truncate(ItemList r6, TruncateCounter r7) {
        ItemList.Builder r0 = new ItemList.Builder(r6);
        r0.clearItems();
        Iterator<Item> r62 = r6.getItems().iterator();
    L4:
        if (r62.hasNext() == false) goto L16;
        Item r1 = r62.next();
        if ((r1 instanceof ConversationItem) == false) goto L8;
        ConversationItem r12 = (ConversationItem) r1;
        if (r7.canFit(2) == false) goto L16;
        ConversationItem.Builder r2 = new ConversationItem.Builder(r12);
        int r3 = Math.min(r7.decrement(), 10);
        int r4 = r12.getMessages().size();
        int r32 = Math.min(r4, r3);
        r2.setMessages(r12.getMessages().subList(r4 - r32, r4));
        r0.addItem(r2.build());
        r7.decrement(r32);
        goto L4
    L8:
        if (r7.canFit(1) == false) goto L16;
        r0.addItem(r1);
        r7.decrement();
    L16:
        return r0.build();
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof ListTemplate) == true) goto L8;
        return false;
    L8:
        ListTemplate r52 = (ListTemplate) r5;
        if (this.mIsLoading == r52.mIsLoading) goto L11;
    L23:
        return false;
    L11:
        if (Objects.equals(this.mTitle, r52.mTitle) == false) goto L23;
        if (Objects.equals(this.mHeaderAction, r52.mHeaderAction) == false) goto L23;
        if (Objects.equals(this.mSingleList, r52.mSingleList) == false) goto L23;
        if (Objects.equals(this.mSectionedLists, r52.mSectionedLists) == false) goto L23;
        if (Objects.equals(this.mActionStrip, r52.mActionStrip) == false) goto L23;
        if (Objects.equals(this.mActions, r52.mActions) == false) goto L23;
        return true;
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    public List<Action> getActions() {
        return this.mActions;
    }

    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    public List<SectionedItemList> getSectionedLists() {
        return CollectionUtils.emptyIfNull(this.mSectionedLists);
    }

    public ItemList getSingleList() {
        return this.mSingleList;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(this.mIsLoading), this.mTitle, this.mHeaderAction, this.mSingleList, this.mSectionedLists, this.mActionStrip});
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public String toString() {
        return "ListTemplate";
    }
}
