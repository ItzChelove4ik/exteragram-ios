package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public final class MediaRouteDescriptor {
    static final String IS_DYNAMIC_GROUP_ROUTE = "isDynamicGroupRoute";
    static final String KEY_ALLOWED_PACKAGES = "allowedPackages";
    static final String KEY_CAN_DISCONNECT = "canDisconnect";
    static final String KEY_CONNECTING = "connecting";
    static final String KEY_CONNECTION_STATE = "connectionState";
    static final String KEY_CONTROL_FILTERS = "controlFilters";
    static final String KEY_DEDUPLICATION_IDS = "deduplicationIds";
    static final String KEY_DESCRIPTION = "status";
    static final String KEY_DEVICE_TYPE = "deviceType";
    static final String KEY_ENABLED = "enabled";
    static final String KEY_EXTRAS = "extras";
    static final String KEY_GROUP_MEMBER_IDS = "groupMemberIds";
    static final String KEY_ICON_URI = "iconUri";
    static final String KEY_ID = "id";
    static final String KEY_IS_SYSTEM_ROUTE = "isSystemRoute";
    static final String KEY_IS_VISIBILITY_PUBLIC = "isVisibilityPublic";
    static final String KEY_MAX_CLIENT_VERSION = "maxClientVersion";
    static final String KEY_MIN_CLIENT_VERSION = "minClientVersion";
    static final String KEY_NAME = "name";
    static final String KEY_PLAYBACK_STREAM = "playbackStream";
    static final String KEY_PLAYBACK_TYPE = "playbackType";
    static final String KEY_PRESENTATION_DISPLAY_ID = "presentationDisplayId";
    static final String KEY_SETTINGS_INTENT = "settingsIntent";
    static final String KEY_VOLUME = "volume";
    static final String KEY_VOLUME_HANDLING = "volumeHandling";
    static final String KEY_VOLUME_MAX = "volumeMax";
    final Bundle mBundle;

    public static final class Builder {
        private Set<String> mAllowedPackages;
        private final Bundle mBundle;
        private List<IntentFilter> mControlFilters;
        private List<String> mGroupMemberIds;

        public Builder(MediaRouteDescriptor r3) {
            this.mGroupMemberIds = new ArrayList();
            this.mControlFilters = new ArrayList();
            this.mAllowedPackages = new HashSet();
            if (r3 == null) goto L6;
            this.mBundle = new Bundle(r3.mBundle);
            this.mGroupMemberIds = r3.getGroupMemberIds();
            this.mControlFilters = r3.getControlFilters();
            this.mAllowedPackages = r3.getAllowedPackages();
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("descriptor must not be null");
            throw null;
        }

        public Builder(String r2, String r3) {
            this.mGroupMemberIds = new ArrayList();
            this.mControlFilters = new ArrayList();
            this.mAllowedPackages = new HashSet();
            this.mBundle = new Bundle();
            setId(r2);
            setName(r3);
        }

        public Builder addControlFilter(IntentFilter r2) {
            if (r2 != null) goto L4;
            f$$ExternalSyntheticBUOutline1.m("filter must not be null");
            return null;
        L4:
            if (this.mControlFilters.contains(r2) == true) goto L6;
            this.mControlFilters.add(r2);
        L6:
            return this;
        }

        public Builder addControlFilters(Collection<IntentFilter> r2) {
            if (r2 != null) goto L4;
            f$$ExternalSyntheticBUOutline1.m("filters must not be null");
            return null;
        L4:
            if (r2.isEmpty() == true) goto L11;
            Iterator<IntentFilter> r22 = r2.iterator();
        L7:
            if (r22.hasNext() == false) goto L11;
            IntentFilter r0 = r22.next();
            if (r0 == null) goto L7;
            addControlFilter(r0);
        L11:
            return this;
        }

        public Builder addGroupMemberId(String r2) {
            if (TextUtils.isEmpty(r2) == false) goto L5;
            f$$ExternalSyntheticBUOutline1.m("groupMemberId must not be empty");
            return null;
        L5:
            if (this.mGroupMemberIds.contains(r2) == true) goto L7;
            this.mGroupMemberIds.add(r2);
        L7:
            return this;
        }

        public Builder addGroupMemberIds(Collection<String> r2) {
            if (r2 != null) goto L4;
            f$$ExternalSyntheticBUOutline1.m("groupMemberIds must not be null");
            return null;
        L4:
            if (r2.isEmpty() == true) goto L9;
            Iterator<String> r22 = r2.iterator();
        L7:
            if (r22.hasNext() == false) goto L9;
            addGroupMemberId(r22.next());
        L9:
            return this;
        }

        public MediaRouteDescriptor build() {
            this.mBundle.putParcelableArrayList(MediaRouteDescriptor.KEY_CONTROL_FILTERS, new ArrayList(this.mControlFilters));
            this.mBundle.putStringArrayList(MediaRouteDescriptor.KEY_GROUP_MEMBER_IDS, new ArrayList(this.mGroupMemberIds));
            this.mBundle.putStringArrayList(MediaRouteDescriptor.KEY_ALLOWED_PACKAGES, new ArrayList(this.mAllowedPackages));
            return new MediaRouteDescriptor(this.mBundle);
        }

        public Builder clearControlFilters() {
            this.mControlFilters.clear();
            return this;
        }

        public Builder clearGroupMemberIds() {
            this.mGroupMemberIds.clear();
            return this;
        }

        public Builder removeGroupMemberId(String r2) {
            if (TextUtils.isEmpty(r2) == true) goto L6;
            this.mGroupMemberIds.remove(r2);
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("memberRouteId must not be empty");
            return null;
        }

        public Builder setCanDisconnect(boolean r3) {
            this.mBundle.putBoolean(MediaRouteDescriptor.KEY_CAN_DISCONNECT, r3);
            return this;
        }

        @Deprecated
        public Builder setConnecting(boolean r3) {
            this.mBundle.putBoolean(MediaRouteDescriptor.KEY_CONNECTING, r3);
            return this;
        }

        public Builder setConnectionState(int r3) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_CONNECTION_STATE, r3);
            return this;
        }

        public Builder setDeduplicationIds(Set<String> r3) {
            this.mBundle.putStringArrayList(MediaRouteDescriptor.KEY_DEDUPLICATION_IDS, new ArrayList(r3));
            return this;
        }

        public Builder setDescription(String r3) {
            this.mBundle.putString(MediaRouteDescriptor.KEY_DESCRIPTION, r3);
            return this;
        }

        public Builder setDeviceType(int r3) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_DEVICE_TYPE, r3);
            return this;
        }

        public Builder setEnabled(boolean r3) {
            this.mBundle.putBoolean(MediaRouteDescriptor.KEY_ENABLED, r3);
            return this;
        }

        public Builder setExtras(Bundle r4) {
            Bundle r0 = this.mBundle;
            if (r4 != null) goto L6;
            r0.putBundle(MediaRouteDescriptor.KEY_EXTRAS, null);
            return this;
        L6:
            r0.putBundle(MediaRouteDescriptor.KEY_EXTRAS, new Bundle(r4));
            return this;
        }

        public Builder setIconUri(Uri r3) {
            if (r3 == null) goto L5;
            this.mBundle.putString(MediaRouteDescriptor.KEY_ICON_URI, r3.toString());
            return this;
        L5:
            f$$ExternalSyntheticBUOutline1.m("iconUri must not be null");
            return null;
        }

        public Builder setId(String r3) {
            if (r3 == null) goto L5;
            this.mBundle.putString(MediaRouteDescriptor.KEY_ID, r3);
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("id must not be null");
            return null;
        }

        public Builder setIsDynamicGroupRoute(boolean r3) {
            this.mBundle.putBoolean(MediaRouteDescriptor.IS_DYNAMIC_GROUP_ROUTE, r3);
            return this;
        }

        public Builder setIsSystemRoute(boolean r3) {
            this.mBundle.putBoolean(MediaRouteDescriptor.KEY_IS_SYSTEM_ROUTE, r3);
            return this;
        }

        public Builder setMaxClientVersion(int r3) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_MAX_CLIENT_VERSION, r3);
            return this;
        }

        public Builder setMinClientVersion(int r3) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_MIN_CLIENT_VERSION, r3);
            return this;
        }

        public Builder setName(String r3) {
            if (r3 == null) goto L5;
            this.mBundle.putString(MediaRouteDescriptor.KEY_NAME, r3);
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("name must not be null");
            return null;
        }

        public Builder setPlaybackStream(int r3) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_PLAYBACK_STREAM, r3);
            return this;
        }

        public Builder setPlaybackType(int r3) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_PLAYBACK_TYPE, r3);
            return this;
        }

        public Builder setPresentationDisplayId(int r3) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_PRESENTATION_DISPLAY_ID, r3);
            return this;
        }

        public Builder setSettingsActivity(IntentSender r3) {
            this.mBundle.putParcelable(MediaRouteDescriptor.KEY_SETTINGS_INTENT, r3);
            return this;
        }

        @SuppressLint({"MissingGetterMatchingBuilder"})
        public Builder setVisibilityPublic() {
            this.mBundle.putBoolean(MediaRouteDescriptor.KEY_IS_VISIBILITY_PUBLIC, true);
            this.mAllowedPackages.clear();
            return this;
        }

        @SuppressLint({"MissingGetterMatchingBuilder"})
        public Builder setVisibilityRestricted(Set<String> r4) {
            this.mBundle.putBoolean(MediaRouteDescriptor.KEY_IS_VISIBILITY_PUBLIC, false);
            this.mAllowedPackages = new HashSet(r4);
            return this;
        }

        public Builder setVolume(int r3) {
            this.mBundle.putInt("volume", r3);
            return this;
        }

        public Builder setVolumeHandling(int r3) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_VOLUME_HANDLING, r3);
            return this;
        }

        public Builder setVolumeMax(int r3) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_VOLUME_MAX, r3);
            return this;
        }
    }

    public MediaRouteDescriptor(Bundle r1) {
        this.mBundle = r1;
    }

    public static MediaRouteDescriptor fromBundle(Bundle r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        return new MediaRouteDescriptor(r1);
    }

    public Bundle asBundle() {
        return this.mBundle;
    }

    public boolean canDisconnectAndKeepPlaying() {
        return this.mBundle.getBoolean(KEY_CAN_DISCONNECT, false);
    }

    public Set<String> getAllowedPackages() {
        if (this.mBundle.containsKey(KEY_ALLOWED_PACKAGES) == true) goto L7;
        return new HashSet();
    L7:
        return new HashSet(this.mBundle.getStringArrayList(KEY_ALLOWED_PACKAGES));
    }

    public int getConnectionState() {
        return this.mBundle.getInt(KEY_CONNECTION_STATE, 0);
    }

    public List<IntentFilter> getControlFilters() {
        if (this.mBundle.containsKey(KEY_CONTROL_FILTERS) == true) goto L7;
        return new ArrayList();
    L7:
        return new ArrayList(this.mBundle.getParcelableArrayList(KEY_CONTROL_FILTERS));
    }

    public Set<String> getDeduplicationIds() {
        ArrayList<String> r1 = this.mBundle.getStringArrayList(KEY_DEDUPLICATION_IDS);
        if (r1 == null) goto L7;
        return Collections.unmodifiableSet(new HashSet(r1));
    L7:
        return Collections.EMPTY_SET;
    }

    public String getDescription() {
        return this.mBundle.getString(KEY_DESCRIPTION);
    }

    public int getDeviceType() {
        return this.mBundle.getInt(KEY_DEVICE_TYPE);
    }

    public Bundle getExtras() {
        return this.mBundle.getBundle(KEY_EXTRAS);
    }

    public List<String> getGroupMemberIds() {
        if (this.mBundle.containsKey(KEY_GROUP_MEMBER_IDS) == true) goto L7;
        return new ArrayList();
    L7:
        return new ArrayList(this.mBundle.getStringArrayList(KEY_GROUP_MEMBER_IDS));
    }

    public Uri getIconUri() {
        String r1 = this.mBundle.getString(KEY_ICON_URI);
        if (r1 != null) goto L7;
        return null;
    L7:
        return Uri.parse(r1);
    }

    public String getId() {
        return this.mBundle.getString(KEY_ID);
    }

    public int getMaxClientVersion() {
        return this.mBundle.getInt(KEY_MAX_CLIENT_VERSION, Integer.MAX_VALUE);
    }

    public int getMinClientVersion() {
        return this.mBundle.getInt(KEY_MIN_CLIENT_VERSION, 1);
    }

    public String getName() {
        return this.mBundle.getString(KEY_NAME);
    }

    public int getPlaybackStream() {
        return this.mBundle.getInt(KEY_PLAYBACK_STREAM, -1);
    }

    public int getPlaybackType() {
        return this.mBundle.getInt(KEY_PLAYBACK_TYPE, 1);
    }

    public int getPresentationDisplayId() {
        return this.mBundle.getInt(KEY_PRESENTATION_DISPLAY_ID, -1);
    }

    public IntentSender getSettingsActivity() {
        return (IntentSender) this.mBundle.getParcelable(KEY_SETTINGS_INTENT);
    }

    public int getVolume() {
        return this.mBundle.getInt("volume");
    }

    public int getVolumeHandling() {
        return this.mBundle.getInt(KEY_VOLUME_HANDLING, 0);
    }

    public int getVolumeMax() {
        return this.mBundle.getInt(KEY_VOLUME_MAX);
    }

    @Deprecated
    public boolean isConnecting() {
        return this.mBundle.getBoolean(KEY_CONNECTING, false);
    }

    public boolean isDynamicGroupRoute() {
        return this.mBundle.getBoolean(IS_DYNAMIC_GROUP_ROUTE, false);
    }

    public boolean isEnabled() {
        return this.mBundle.getBoolean(KEY_ENABLED, true);
    }

    public boolean isSystemRoute() {
        return this.mBundle.getBoolean(KEY_IS_SYSTEM_ROUTE, false);
    }

    public boolean isValid() {
        if (TextUtils.isEmpty(getId()) == false) goto L5;
        return false;
    L5:
        if (TextUtils.isEmpty(getName()) == false) goto L7;
        return false;
    L7:
        if (getControlFilters().contains(null) == true) goto L14;
        return true;
    L14:
        return false;
    }

    public boolean isVisibilityPublic() {
        return this.mBundle.getBoolean(KEY_IS_VISIBILITY_PUBLIC, true);
    }

    public String toString() {
        return "MediaRouteDescriptor{ id=" + getId() + ", groupMemberIds=" + getGroupMemberIds() + ", name=" + getName() + ", description=" + getDescription() + ", iconUri=" + getIconUri() + ", isEnabled=" + isEnabled() + ", isSystemRoute=" + isSystemRoute() + ", connectionState=" + getConnectionState() + ", controlFilters=" + Arrays.toString(getControlFilters().toArray()) + ", playbackType=" + getPlaybackType() + ", playbackStream=" + getPlaybackStream() + ", deviceType=" + getDeviceType() + ", volume=" + getVolume() + ", volumeMax=" + getVolumeMax() + ", volumeHandling=" + getVolumeHandling() + ", presentationDisplayId=" + getPresentationDisplayId() + ", extras=" + getExtras() + ", isValid=" + isValid() + ", minClientVersion=" + getMinClientVersion() + ", maxClientVersion=" + getMaxClientVersion() + ", isVisibilityPublic=" + isVisibilityPublic() + ", allowedPackages=" + Arrays.toString(getAllowedPackages().toArray()) + " }";
    }
}
