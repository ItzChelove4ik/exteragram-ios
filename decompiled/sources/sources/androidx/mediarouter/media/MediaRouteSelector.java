package androidx.mediarouter.media;

import android.content.IntentFilter;
import android.os.Bundle;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class MediaRouteSelector {
    public static final MediaRouteSelector EMPTY = null;
    static final String KEY_CONTROL_CATEGORIES = "controlCategories";
    private final Bundle mBundle;
    List<String> mControlCategories;

    public static final class Builder {
        private ArrayList<String> mControlCategories;

        public Builder() {
        }

        public Builder(MediaRouteSelector r2) {
            if (r2 == null) goto L8;
            r2.ensureControlCategories();
            if (r2.mControlCategories.isEmpty() == true) goto L10;
            this.mControlCategories = new ArrayList(r2.mControlCategories);
            return;
        L10:
            return;
        L8:
            f$$ExternalSyntheticBUOutline1.m("selector must not be null");
            throw null;
        }

        public Builder addControlCategories(Collection<String> r2) {
            if (r2 != null) goto L4;
            f$$ExternalSyntheticBUOutline1.m("categories must not be null");
            return null;
        L4:
            if (r2.isEmpty() == true) goto L9;
            Iterator<String> r22 = r2.iterator();
        L7:
            if (r22.hasNext() == false) goto L9;
            addControlCategory(r22.next());
        L9:
            return this;
        }

        public Builder addControlCategory(String r2) {
            if (r2 != null) goto L4;
            f$$ExternalSyntheticBUOutline1.m("category must not be null");
            return null;
        L4:
            if (this.mControlCategories != null) goto L7;
            this.mControlCategories = new ArrayList();
        L7:
            if (this.mControlCategories.contains(r2) == true) goto L9;
            this.mControlCategories.add(r2);
        L9:
            return this;
        }

        public Builder addSelector(MediaRouteSelector r1) {
            if (r1 == null) goto L5;
            addControlCategories(r1.getControlCategories());
            return this;
        L5:
            f$$ExternalSyntheticBUOutline1.m("selector must not be null");
            return null;
        }

        public MediaRouteSelector build() {
            if (this.mControlCategories == null) goto L5;
            Bundle r0 = new Bundle();
            r0.putStringArrayList(MediaRouteSelector.KEY_CONTROL_CATEGORIES, this.mControlCategories);
            return new MediaRouteSelector(r0, this.mControlCategories);
        L5:
            return MediaRouteSelector.EMPTY;
        }
    }

    static {
        EMPTY = new MediaRouteSelector(new Bundle(), null);
    }

    public MediaRouteSelector(Bundle r1, List<String> r2) {
        this.mBundle = r1;
        this.mControlCategories = r2;
    }

    public static MediaRouteSelector fromBundle(Bundle r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        return new MediaRouteSelector(r2, null);
    }

    public Bundle asBundle() {
        return this.mBundle;
    }

    public boolean contains(MediaRouteSelector r1) {
        if (r1 != null) goto L5;
        return false;
    L5:
        ensureControlCategories();
        r1.ensureControlCategories();
        return this.mControlCategories.containsAll(r1.mControlCategories);
    }

    public void ensureControlCategories() {
        if (this.mControlCategories != null) goto L10;
        ArrayList<String> r0 = this.mBundle.getStringArrayList(KEY_CONTROL_CATEGORIES);
        this.mControlCategories = r0;
        if (r0 != null) goto L7;
    L8:
        this.mControlCategories = Collections.EMPTY_LIST;
        return;
    L7:
        if (r0.isEmpty() == true) goto L8;
        return;
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof MediaRouteSelector) == false) goto L6;
        MediaRouteSelector r22 = (MediaRouteSelector) r2;
        ensureControlCategories();
        r22.ensureControlCategories();
        return this.mControlCategories.equals(r22.mControlCategories);
    L6:
        return false;
    }

    public List<String> getControlCategories() {
        ensureControlCategories();
        return new ArrayList(this.mControlCategories);
    }

    public boolean hasControlCategory(String r5) {
        if (r5 != null) goto L5;
        return false;
    L5:
        ensureControlCategories();
        int r1 = this.mControlCategories.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        if (this.mControlCategories.get(r2).equals(r5) == true) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    public int hashCode() {
        ensureControlCategories();
        return this.mControlCategories.hashCode();
    }

    public boolean isEmpty() {
        ensureControlCategories();
        return this.mControlCategories.isEmpty();
    }

    public boolean isValid() {
        ensureControlCategories();
        if (this.mControlCategories.contains(null) == false) goto L6;
        return false;
    L6:
        return true;
    }

    public boolean matchesControlFilters(List<IntentFilter> r5) {
        if (r5 != null) goto L5;
        return false;
    L5:
        ensureControlCategories();
        if (this.mControlCategories.isEmpty() == false) goto L8;
        return false;
    L8:
        Iterator<IntentFilter> r52 = r5.iterator();
    L10:
        if (r52.hasNext() == false) goto L21;
        IntentFilter r1 = r52.next();
        if (r1 == null) goto L10;
        Iterator<String> r2 = this.mControlCategories.iterator();
    L16:
        if (r2.hasNext() == false) goto L10;
        if (r1.hasCategory(r2.next()) == false) goto L16;
        return true;
    L21:
        return false;
    }

    public String toString() {
        return "MediaRouteSelector{ controlCategories=" + Arrays.toString(getControlCategories().toArray()) + " }";
    }
}
