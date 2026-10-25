package androidx.car.app.model.constraints;

import android.annotation.SuppressLint;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.CarText;
import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import com.sun.jna.Structure$$ExternalSyntheticBUOutline2;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public final class ActionsConstraints {
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_BODY = null;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_BODY_WITH_PRIMARY_ACTION = null;
    private static final ActionsConstraints ACTIONS_CONSTRAINTS_CONSERVATIVE = null;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_CONVERSATION_ITEM = null;

    @SuppressLint({"UnsafeOptInUsageError"})
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_FAB = null;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_HEADER = null;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_MAP = null;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_MULTI_HEADER = null;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_NAVIGATION = null;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_ROW = null;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_SIMPLE = null;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_TABS = null;
    private final Set<Integer> mAllowedActionTypes;
    private final Set<Integer> mDisallowedActionTypes;
    private final int mMaxActions;
    private final int mMaxCustomTitles;
    private final int mMaxPrimaryActions;
    private final boolean mOnClickListenerAllowed;
    private final boolean mRequireActionBackgroundColor;
    private final boolean mRequireActionIcons;
    private final Set<Integer> mRequiredActionTypes;
    private final boolean mRestrictBackgroundColorToPrimaryAction;
    private final CarTextConstraints mTitleTextConstraints;

    public static final class Builder {
        final Set<Integer> mAllowedActionTypes;
        final Set<Integer> mDisallowedActionTypes;
        int mMaxActions;
        int mMaxCustomTitles;
        int mMaxPrimaryActions;
        boolean mOnClickListenerAllowed;
        boolean mRequireActionBackgroundColor;
        boolean mRequireActionIcons;
        final Set<Integer> mRequiredActionTypes;
        boolean mRestrictBackgroundColorToPrimaryAction;
        CarTextConstraints mTitleTextConstraints;

        public Builder() {
            this.mRequiredActionTypes = new HashSet();
            this.mDisallowedActionTypes = new HashSet();
            this.mAllowedActionTypes = new HashSet();
            this.mMaxActions = Integer.MAX_VALUE;
            this.mMaxPrimaryActions = 0;
            this.mRestrictBackgroundColorToPrimaryAction = false;
            this.mTitleTextConstraints = CarTextConstraints.UNCONSTRAINED;
        }

        public Builder(ActionsConstraints r5) {
            HashSet r0 = new HashSet();
            this.mRequiredActionTypes = r0;
            HashSet r1 = new HashSet();
            this.mDisallowedActionTypes = r1;
            HashSet r2 = new HashSet();
            this.mAllowedActionTypes = r2;
            this.mMaxActions = Integer.MAX_VALUE;
            this.mMaxPrimaryActions = 0;
            this.mRestrictBackgroundColorToPrimaryAction = false;
            this.mTitleTextConstraints = CarTextConstraints.UNCONSTRAINED;
            Objects.requireNonNull(r5);
            this.mMaxActions = r5.getMaxActions();
            this.mMaxPrimaryActions = r5.getMaxPrimaryActions();
            this.mMaxCustomTitles = r5.getMaxCustomTitles();
            this.mTitleTextConstraints = r5.getTitleTextConstraints();
            r0.addAll(r5.getRequiredActionTypes());
            r1.addAll(r5.getDisallowedActionTypes());
            r2.addAll(r5.getAllowedActionTypes());
            this.mRequireActionIcons = r5.areActionIconsRequired();
            this.mRequireActionBackgroundColor = r5.isActionBackgroundColorRequired();
            this.mOnClickListenerAllowed = r5.isOnClickListenerAllowed();
            this.mRestrictBackgroundColorToPrimaryAction = r5.restrictBackgroundColorToPrimaryAction();
        }

        public Builder addAllowedActionType(int r2) {
            this.mAllowedActionTypes.add(Integer.valueOf(r2));
            return this;
        }

        public Builder addRequiredActionType(int r2) {
            this.mRequiredActionTypes.add(Integer.valueOf(r2));
            return this;
        }

        public ActionsConstraints build() {
            return new ActionsConstraints(this);
        }

        public Builder setMaxActions(int r1) {
            this.mMaxActions = r1;
            return this;
        }

        public Builder setMaxCustomTitles(int r1) {
            this.mMaxCustomTitles = r1;
            return this;
        }

        public Builder setMaxPrimaryActions(int r1) {
            this.mMaxPrimaryActions = r1;
            return this;
        }

        public Builder setOnClickListenerAllowed(boolean r1) {
            this.mOnClickListenerAllowed = r1;
            return this;
        }

        public Builder setRequireActionBackgroundColor(boolean r1) {
            this.mRequireActionBackgroundColor = r1;
            return this;
        }

        public Builder setRequireActionIcons(boolean r1) {
            this.mRequireActionIcons = r1;
            return this;
        }

        public Builder setRestrictBackgroundColorToPrimaryAction(boolean r1) {
            this.mRestrictBackgroundColorToPrimaryAction = r1;
            return this;
        }

        public Builder setTitleTextConstraints(CarTextConstraints r1) {
            this.mTitleTextConstraints = r1;
            return this;
        }
    }

    static {
        ActionsConstraints r0 = new Builder().setMaxActions(1).setRequireActionIcons(true).setOnClickListenerAllowed(false).build();
        ACTIONS_CONSTRAINTS_HEADER = r0;
        ACTIONS_CONSTRAINTS_MULTI_HEADER = new Builder().setMaxActions(2).setRequireActionIcons(true).setOnClickListenerAllowed(true).build();
        ActionsConstraints r2 = new Builder().setTitleTextConstraints(CarTextConstraints.CONSERVATIVE).setMaxActions(2).build();
        ACTIONS_CONSTRAINTS_CONSERVATIVE = r2;
        Builder r4 = new Builder(r2);
        CarTextConstraints r5 = CarTextConstraints.COLOR_ONLY;
        ACTIONS_CONSTRAINTS_BODY = r4.setTitleTextConstraints(r5).setMaxCustomTitles(2).setOnClickListenerAllowed(true).build();
        ACTIONS_CONSTRAINTS_BODY_WITH_PRIMARY_ACTION = new Builder(r2).setTitleTextConstraints(r5).setMaxCustomTitles(2).setMaxPrimaryActions(1).setOnClickListenerAllowed(true).build();
        ACTIONS_CONSTRAINTS_SIMPLE = new Builder(r2).setMaxCustomTitles(1).setTitleTextConstraints(CarTextConstraints.TEXT_ONLY).setOnClickListenerAllowed(true).setRestrictBackgroundColorToPrimaryAction(true).build();
        ACTIONS_CONSTRAINTS_NAVIGATION = new Builder(r2).setMaxActions(4).setMaxCustomTitles(4).setMaxPrimaryActions(1).setTitleTextConstraints(CarTextConstraints.TEXT_AND_ICON).setOnClickListenerAllowed(true).setRestrictBackgroundColorToPrimaryAction(true).build();
        ACTIONS_CONSTRAINTS_MAP = new Builder(r2).setMaxActions(4).setMaxPrimaryActions(1).setOnClickListenerAllowed(true).setRestrictBackgroundColorToPrimaryAction(true).build();
        ACTIONS_CONSTRAINTS_ROW = new Builder().setMaxActions(1).setMaxCustomTitles(1).addAllowedActionType(1).setRequireActionIcons(true).setOnClickListenerAllowed(true).build();
        ACTIONS_CONSTRAINTS_CONVERSATION_ITEM = new Builder().setMaxActions(1).setMaxCustomTitles(1).addAllowedActionType(1).setRequireActionIcons(true).setOnClickListenerAllowed(true).build();
        ACTIONS_CONSTRAINTS_FAB = new Builder().setMaxActions(2).addAllowedActionType(1).addAllowedActionType(Action.TYPE_COMPOSE_MESSAGE).setRequireActionIcons(true).setRequireActionBackgroundColor(true).setOnClickListenerAllowed(true).build();
        ACTIONS_CONSTRAINTS_TABS = new Builder(r0).addRequiredActionType(65538).build();
    }

    public ActionsConstraints(Builder r6) {
        int r0 = r6.mMaxActions;
        this.mMaxActions = r0;
        this.mMaxPrimaryActions = r6.mMaxPrimaryActions;
        this.mMaxCustomTitles = r6.mMaxCustomTitles;
        this.mTitleTextConstraints = r6.mTitleTextConstraints;
        this.mRequireActionIcons = r6.mRequireActionIcons;
        this.mRequireActionBackgroundColor = r6.mRequireActionBackgroundColor;
        this.mOnClickListenerAllowed = r6.mOnClickListenerAllowed;
        this.mRestrictBackgroundColorToPrimaryAction = r6.mRestrictBackgroundColorToPrimaryAction;
        HashSet r1 = new HashSet(r6.mRequiredActionTypes);
        this.mRequiredActionTypes = r1;
        HashSet r2 = new HashSet(r6.mAllowedActionTypes);
        this.mAllowedActionTypes = r2;
        HashSet r3 = new HashSet(r6.mDisallowedActionTypes);
        r3.retainAll(r1);
        if (r3.isEmpty() == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Disallowed action types cannot also be in the required set");
        throw null;
    L5:
        if (r6.mDisallowedActionTypes.isEmpty() == false) goto L7;
    L11:
        this.mDisallowedActionTypes = new HashSet(r6.mDisallowedActionTypes);
        if (r1.size() > r0) goto L14;
        return;
    L14:
        f$$ExternalSyntheticBUOutline1.m("Required action types exceeded max allowed actions");
        throw null;
    L7:
        if (r2.isEmpty() == true) goto L11;
        f$$ExternalSyntheticBUOutline1.m("Both disallowed and allowed action type set cannot be defined.");
        throw null;
    }

    public boolean areActionIconsRequired() {
        return this.mRequireActionIcons;
    }

    public Set<Integer> getAllowedActionTypes() {
        return this.mAllowedActionTypes;
    }

    public Set<Integer> getDisallowedActionTypes() {
        return this.mDisallowedActionTypes;
    }

    public int getMaxActions() {
        return this.mMaxActions;
    }

    public int getMaxCustomTitles() {
        return this.mMaxCustomTitles;
    }

    public int getMaxPrimaryActions() {
        return this.mMaxPrimaryActions;
    }

    public Set<Integer> getRequiredActionTypes() {
        return this.mRequiredActionTypes;
    }

    public CarTextConstraints getTitleTextConstraints() {
        return this.mTitleTextConstraints;
    }

    public boolean isActionBackgroundColorRequired() {
        return this.mRequireActionBackgroundColor;
    }

    public boolean isOnClickListenerAllowed() {
        return this.mOnClickListenerAllowed;
    }

    public boolean restrictBackgroundColorToPrimaryAction() {
        return this.mRestrictBackgroundColorToPrimaryAction;
    }

    public void validateOrThrow(List<Action> r9) {
        int r0 = this.mMaxActions;
        int r1 = this.mMaxPrimaryActions;
        int r2 = this.mMaxCustomTitles;
        if (this.mRequiredActionTypes.isEmpty() == false) goto L5;
        Set r3 = Collections.EMPTY_SET;
    L6:
        Iterator<Action> r92 = r9.iterator();
    L8:
        if (r92.hasNext() == false) goto L84;
        Action r4 = r92.next();
        if (this.mDisallowedActionTypes.isEmpty() == true) goto L17;
        if (this.mDisallowedActionTypes.contains(Integer.valueOf(r4.getType())) == false) goto L17;
        throw new IllegalArgumentException(Action.typeToString(r4.getType()) + " is disallowed");
    L17:
        if (this.mAllowedActionTypes.isEmpty() == false) goto L19;
    L23:
        r3.remove(Integer.valueOf(r4.getType()));
        CarText r5 = r4.getTitle();
        if (r5 != null) goto L26;
    L32:
        r0 = r0 - 1;
        if (r0 < 0) goto L81;
        if ((r4.getFlags() & 1) == 0) goto L42;
        r1 = r1 - 1;
        if (r1 >= 0) goto L42;
        Structure$$ExternalSyntheticBUOutline2.m("Action list exceeded max number of ", this.mMaxPrimaryActions, " primary actions");
        return;
    L42:
        if (this.mRequireActionIcons == false) goto L51;
        if (r4.getIcon() != null) goto L51;
        if (r4.isStandard() == true) goto L51;
        f$$ExternalSyntheticBUOutline1.m("Non-standard actions without an icon are disallowed");
        return;
    L51:
        if (this.mRequireActionBackgroundColor == false) goto L62;
        if (r4.getBackgroundColor() == null) goto L57;
        if (CarColor.DEFAULT.equals(r4.getBackgroundColor()) == false) goto L62;
    L57:
        if (r4.isStandard() == true) goto L62;
        f$$ExternalSyntheticBUOutline1.m("Non-standard actions without a background color are disallowed");
        return;
    L62:
        if (this.mRequireActionBackgroundColor == true) goto L73;
        if (CarColor.DEFAULT.equals(r4.getBackgroundColor()) == true) goto L73;
        if (this.mRestrictBackgroundColorToPrimaryAction == false) goto L73;
        if ((r4.getFlags() & 1) != 0) goto L73;
        f$$ExternalSyntheticBUOutline1.m("Background color can only be set for primary actions");
        return;
    L73:
        if (this.mOnClickListenerAllowed == true) goto L8;
        if (r4.getOnClickDelegate() == null) goto L8;
        if (r4.isStandard() == true) goto L8;
        f$$ExternalSyntheticBUOutline1.m("Setting a click listener for a custom action is disallowed");
        return;
    L81:
        Structure$$ExternalSyntheticBUOutline2.m("Action list exceeded max number of ", this.mMaxActions, " actions");
        return;
    L26:
        if (r5.isEmpty() == true) goto L32;
        r2 = r2 - 1;
        if (r2 < 0) goto L30;
        this.mTitleTextConstraints.validateOrThrow(r5);
        goto L32
    L30:
        Structure$$ExternalSyntheticBUOutline2.m("Action list exceeded max number of ", this.mMaxCustomTitles, " actions with custom titles");
        return;
    L19:
        if (this.mAllowedActionTypes.contains(Integer.valueOf(r4.getType())) == true) goto L23;
        throw new IllegalArgumentException(Action.typeToString(r4.getType()) + " is not allowed");
    L84:
        if (r3.isEmpty() == true) goto L108;
        StringBuilder r8 = new StringBuilder();
        Iterator r93 = r3.iterator();
    L87:
        if (r93.hasNext() == false) goto L89;
        r8.append(Action.typeToString(((Integer) r93.next()).intValue()));
        r8.append(",");
        goto L87
    L89:
        Native$$ExternalSyntheticBUOutline5.m("Missing required action types: ", r8);
        return;
    L108:
        return;
    L5:
        r3 = new HashSet(this.mRequiredActionTypes);
        goto L6
    }
}
