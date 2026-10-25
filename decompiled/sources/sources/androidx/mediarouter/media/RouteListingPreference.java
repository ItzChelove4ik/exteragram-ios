package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.media.RouteListingPreference;
import android.text.TextUtils;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class RouteListingPreference {

    @SuppressLint({"ActionValue"})
    public static final String ACTION_TRANSFER_MEDIA = "android.media.action.TRANSFER_MEDIA";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_ROUTE_ID = "android.media.extra.ROUTE_ID";
    private final boolean mIsSystemOrderingEnabled;
    private final List<Item> mItems;
    private final ComponentName mLinkedItemComponentName;

    public static class Api34Impl {
        private Api34Impl() {
        }

        public static RouteListingPreference.Item toPlatformItem(Item r2) {
            return new RouteListingPreference.Item.Builder(r2.getRouteId()).setFlags(r2.getFlags()).setSubText(r2.getSubText()).setCustomSubtextMessage(r2.getCustomSubtextMessage()).setSelectionBehavior(r2.getSelectionBehavior()).build();
        }

        public static android.media.RouteListingPreference toPlatformRouteListingPreference(RouteListingPreference r3) {
            ArrayList r0 = new ArrayList();
            Iterator<Item> r1 = r3.getItems().iterator();
        L4:
            if (r1.hasNext() == false) goto L7;
            r0.add(toPlatformItem(r1.next()));
            goto L4
        L7:
            return new RouteListingPreference.Builder().setItems(r0).setLinkedItemComponentName(r3.getLinkedItemComponentName()).setUseSystemOrdering(r3.isSystemOrderingEnabled()).build();
        }
    }

    public static final class Builder {
        boolean mIsSystemOrderingEnabled;
        List<Item> mItems;
        ComponentName mLinkedItemComponentName;

        public Builder() {
            this.mItems = Collections.EMPTY_LIST;
            this.mIsSystemOrderingEnabled = true;
        }

        public RouteListingPreference build() {
            return new RouteListingPreference(this);
        }

        public Builder setItems(List<Item> r2) {
            Objects.requireNonNull(r2);
            this.mItems = Collections.unmodifiableList(new ArrayList(r2));
            return this;
        }

        public Builder setLinkedItemComponentName(ComponentName r1) {
            this.mLinkedItemComponentName = r1;
            return this;
        }

        public Builder setSystemOrderingEnabled(boolean r1) {
            this.mIsSystemOrderingEnabled = r1;
            return this;
        }
    }

    public static final class Item {
        public static final int FLAG_ONGOING_SESSION = 1;
        public static final int FLAG_ONGOING_SESSION_MANAGED = 2;
        public static final int FLAG_SUGGESTED = 4;
        public static final int SELECTION_BEHAVIOR_GO_TO_APP = 2;
        public static final int SELECTION_BEHAVIOR_NONE = 0;
        public static final int SELECTION_BEHAVIOR_TRANSFER = 1;
        public static final int SUBTEXT_AD_ROUTING_DISALLOWED = 4;
        public static final int SUBTEXT_CUSTOM = 10000;
        public static final int SUBTEXT_DEVICE_LOW_POWER = 5;
        public static final int SUBTEXT_DOWNLOADED_CONTENT_ROUTING_DISALLOWED = 3;
        public static final int SUBTEXT_ERROR_UNKNOWN = 1;
        public static final int SUBTEXT_NONE = 0;
        public static final int SUBTEXT_SUBSCRIPTION_REQUIRED = 2;
        public static final int SUBTEXT_TRACK_UNSUPPORTED = 7;
        public static final int SUBTEXT_UNAUTHORIZED = 6;
        private final CharSequence mCustomSubtextMessage;
        private final int mFlags;
        private final String mRouteId;
        private final int mSelectionBehavior;
        private final int mSubText;

        public static final class Builder {
            CharSequence mCustomSubtextMessage;
            int mFlags;
            final String mRouteId;
            int mSelectionBehavior;
            int mSubText;

            public Builder(String r3) {
                Preconditions.checkArgument(!TextUtils.isEmpty(r3));
                this.mRouteId = r3;
                this.mSelectionBehavior = 1;
                this.mSubText = 0;
            }

            public Item build() {
                return new Item(this);
            }

            public Builder setCustomSubtextMessage(CharSequence r1) {
                this.mCustomSubtextMessage = r1;
                return this;
            }

            public Builder setFlags(int r1) {
                this.mFlags = r1;
                return this;
            }

            public Builder setSelectionBehavior(int r1) {
                this.mSelectionBehavior = r1;
                return this;
            }

            public Builder setSubText(int r1) {
                this.mSubText = r1;
                return this;
            }
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface Flags {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface SelectionBehavior {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface SubText {
        }

        public Item(Builder r2) {
            this.mRouteId = r2.mRouteId;
            this.mSelectionBehavior = r2.mSelectionBehavior;
            this.mFlags = r2.mFlags;
            this.mSubText = r2.mSubText;
            this.mCustomSubtextMessage = r2.mCustomSubtextMessage;
            validateCustomMessageSubtext();
        }

        private void validateCustomMessageSubtext() {
            if (this.mSubText == 10000) goto L5;
        L8:
            boolean r2 = true;
        L9:
            Preconditions.checkArgument(r2, "The custom subtext message cannot be null if subtext is SUBTEXT_CUSTOM.");
            return;
        L5:
            if (this.mCustomSubtextMessage != null) goto L8;
            r2 = false;
            goto L9
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof Item) == true) goto L8;
            return false;
        L8:
            Item r52 = (Item) r5;
            if (this.mRouteId.equals(r52.mRouteId) == true) goto L11;
        L19:
            return false;
        L11:
            if (this.mSelectionBehavior != r52.mSelectionBehavior) goto L19;
            if (this.mFlags != r52.mFlags) goto L19;
            if (this.mSubText != r52.mSubText) goto L19;
            if (TextUtils.equals(this.mCustomSubtextMessage, r52.mCustomSubtextMessage) == false) goto L19;
            return true;
        }

        public CharSequence getCustomSubtextMessage() {
            return this.mCustomSubtextMessage;
        }

        public int getFlags() {
            return this.mFlags;
        }

        public String getRouteId() {
            return this.mRouteId;
        }

        public int getSelectionBehavior() {
            return this.mSelectionBehavior;
        }

        public int getSubText() {
            return this.mSubText;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.mRouteId, Integer.valueOf(this.mSelectionBehavior), Integer.valueOf(this.mFlags), Integer.valueOf(this.mSubText), this.mCustomSubtextMessage});
        }
    }

    public RouteListingPreference(Builder r2) {
        this.mItems = r2.mItems;
        this.mIsSystemOrderingEnabled = r2.mIsSystemOrderingEnabled;
        this.mLinkedItemComponentName = r2.mLinkedItemComponentName;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof RouteListingPreference) == true) goto L8;
        return false;
    L8:
        RouteListingPreference r52 = (RouteListingPreference) r5;
        if (this.mItems.equals(r52.mItems) == true) goto L11;
    L15:
        return false;
    L11:
        if (this.mIsSystemOrderingEnabled != r52.mIsSystemOrderingEnabled) goto L15;
        if (Objects.equals(this.mLinkedItemComponentName, r52.mLinkedItemComponentName) == false) goto L15;
        return true;
    }

    public List<Item> getItems() {
        return this.mItems;
    }

    public ComponentName getLinkedItemComponentName() {
        return this.mLinkedItemComponentName;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mItems, Boolean.valueOf(this.mIsSystemOrderingEnabled), this.mLinkedItemComponentName});
    }

    public boolean isSystemOrderingEnabled() {
        return this.mIsSystemOrderingEnabled;
    }

    public android.media.RouteListingPreference toPlatformRouteListingPreference() {
        return Api34Impl.toPlatformRouteListingPreference(this);
    }
}
