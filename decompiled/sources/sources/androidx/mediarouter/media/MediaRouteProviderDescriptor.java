package androidx.mediarouter.media;

import android.os.Bundle;
import android.os.Parcelable;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class MediaRouteProviderDescriptor {
    private static final String KEY_ROUTES = "routes";
    private static final String KEY_SUPPORTS_DYNAMIC_GROUP_ROUTE = "supportsDynamicGroupRoute";
    Bundle mBundle;
    final List<MediaRouteDescriptor> mRoutes;
    final boolean mSupportsDynamicGroupRoute;

    public static final class Builder {
        private final List<MediaRouteDescriptor> mRoutes;
        private boolean mSupportsDynamicGroupRoute;

        public Builder() {
            this.mRoutes = new ArrayList();
            this.mSupportsDynamicGroupRoute = false;
        }

        public Builder(MediaRouteProviderDescriptor r3) {
            ArrayList r0 = new ArrayList();
            this.mRoutes = r0;
            this.mSupportsDynamicGroupRoute = false;
            if (r3 == null) goto L6;
            r0.addAll(r3.getRoutes());
            this.mSupportsDynamicGroupRoute = r3.mSupportsDynamicGroupRoute;
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("descriptor must not be null");
            throw null;
        }

        public Builder addRoute(MediaRouteDescriptor r3) {
            if (r3 != null) goto L5;
            f$$ExternalSyntheticBUOutline1.m("route must not be null");
            return null;
        L5:
            if (this.mRoutes.contains(r3) == true) goto L8;
            this.mRoutes.add(r3);
            return this;
        L8:
            f$$ExternalSyntheticBUOutline1.m("route descriptor already added");
            return null;
        }

        public Builder addRoutes(Collection<MediaRouteDescriptor> r2) {
            if (r2 != null) goto L4;
            f$$ExternalSyntheticBUOutline1.m("routes must not be null");
            return null;
        L4:
            if (r2.isEmpty() == true) goto L9;
            Iterator<MediaRouteDescriptor> r22 = r2.iterator();
        L7:
            if (r22.hasNext() == false) goto L9;
            addRoute(r22.next());
        L9:
            return this;
        }

        public MediaRouteProviderDescriptor build() {
            return new MediaRouteProviderDescriptor(this.mRoutes, this.mSupportsDynamicGroupRoute);
        }

        public Builder setRoutes(Collection<MediaRouteDescriptor> r2) {
            this.mRoutes.clear();
            if (r2 == null) goto L5;
            this.mRoutes.addAll(r2);
        L5:
            return this;
        }

        public Builder setSupportsDynamicGroupRoute(boolean r1) {
            this.mSupportsDynamicGroupRoute = r1;
            return this;
        }
    }

    public MediaRouteProviderDescriptor(List<MediaRouteDescriptor> r2, boolean r3) {
        if (r2.isEmpty() == false) goto L5;
        this.mRoutes = Collections.EMPTY_LIST;
    L6:
        this.mSupportsDynamicGroupRoute = r3;
        return;
    L5:
        this.mRoutes = Collections.unmodifiableList(new ArrayList(r2));
        goto L6
    }

    public static MediaRouteProviderDescriptor fromBundle(Bundle r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        ArrayList r0 = new ArrayList();
        ArrayList r1 = r5.getParcelableArrayList(KEY_ROUTES);
        if (r1 == null) goto L12;
        int r3 = 0;
    L9:
        if (r3 >= r1.size()) goto L12;
        r0.add(MediaRouteDescriptor.fromBundle((Bundle) r1.get(r3)));
        r3 = r3 + 1;
    L12:
        return new MediaRouteProviderDescriptor(r0, r5.getBoolean(KEY_SUPPORTS_DYNAMIC_GROUP_ROUTE, false));
    }

    public Bundle asBundle() {
        Bundle r0 = this.mBundle;
        if (r0 == null) goto L5;
        return r0;
    L5:
        this.mBundle = new Bundle();
        if (this.mRoutes.isEmpty() == true) goto L11;
        int r02 = this.mRoutes.size();
        ArrayList<? extends Parcelable> r1 = new ArrayList(r02);
        int r2 = 0;
    L8:
        if (r2 >= r02) goto L10;
        r1.add(this.mRoutes.get(r2).asBundle());
        r2 = r2 + 1;
        goto L8
    L10:
        this.mBundle.putParcelableArrayList(KEY_ROUTES, r1);
    L11:
        this.mBundle.putBoolean(KEY_SUPPORTS_DYNAMIC_GROUP_ROUTE, this.mSupportsDynamicGroupRoute);
        return this.mBundle;
    }

    public List<MediaRouteDescriptor> getRoutes() {
        return this.mRoutes;
    }

    public boolean isValid() {
        int r0 = getRoutes().size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        MediaRouteDescriptor r3 = this.mRoutes.get(r2);
        if (r3 == null) goto L10;
        if (r3.isValid() == false) goto L10;
        r2 = r2 + 1;
    L10:
        return false;
    L11:
        return true;
    }

    public boolean supportsDynamicGroupRoute() {
        return this.mSupportsDynamicGroupRoute;
    }

    public String toString() {
        return "MediaRouteProviderDescriptor{ routes=" + Arrays.toString(getRoutes().toArray()) + ", isValid=" + isValid() + " }";
    }
}
