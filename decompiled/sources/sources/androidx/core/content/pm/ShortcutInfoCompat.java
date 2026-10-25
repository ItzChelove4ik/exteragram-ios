package androidx.core.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.collection.ArraySet;
import androidx.core.app.Person;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.net.UriCompat;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class ShortcutInfoCompat {
    ComponentName mActivity;
    Set<String> mCategories;
    Context mContext;
    CharSequence mDisabledMessage;
    int mDisabledReason;
    int mExcludedSurfaces;
    PersistableBundle mExtras;
    boolean mHasKeyFieldsOnly;
    IconCompat mIcon;
    String mId;
    Intent[] mIntents;
    boolean mIsAlwaysBadged;
    boolean mIsCached;
    boolean mIsDeclaredInManifest;
    boolean mIsDynamic;
    boolean mIsEnabled;
    boolean mIsImmutable;
    boolean mIsLongLived;
    boolean mIsPinned;
    CharSequence mLabel;
    long mLastChangedTimestamp;
    LocusIdCompat mLocusId;
    CharSequence mLongLabel;
    String mPackageName;
    Person[] mPersons;
    int mRank;
    UserHandle mUser;

    public static class Api33Impl {
        public static void setExcludedFromSurfaces(ShortcutInfo.Builder r0, int r1) {
            r0.setExcludedFromSurfaces(r1);
        }
    }

    public static class Builder {
        private Map<String, Map<String, List<String>>> mCapabilityBindingParams;
        private Set<String> mCapabilityBindings;
        private final ShortcutInfoCompat mInfo;
        private boolean mIsConversation;
        private Uri mSliceUri;

        public Builder(Context r4, ShortcutInfo r5) {
            ShortcutInfoCompat r0 = new ShortcutInfoCompat();
            this.mInfo = r0;
            r0.mContext = r4;
            r0.mId = r5.getId();
            r0.mPackageName = r5.getPackage();
            Intent[] r3 = r5.getIntents();
            r0.mIntents = (Intent[]) Arrays.copyOf(r3, r3.length);
            r0.mActivity = r5.getActivity();
            r0.mLabel = r5.getShortLabel();
            r0.mLongLabel = r5.getLongLabel();
            r0.mDisabledMessage = r5.getDisabledMessage();
            int r32 = Build.VERSION.SDK_INT;
            if (r32 < 28) goto L6;
            r0.mDisabledReason = r5.getDisabledReason();
        L10:
            r0.mCategories = r5.getCategories();
            r0.mPersons = ShortcutInfoCompat.getPersonsFromExtra(r5.getExtras());
            r0.mUser = r5.getUserHandle();
            r0.mLastChangedTimestamp = r5.getLastChangedTimestamp();
            if (r32 < 30) goto L13;
            r0.mIsCached = r5.isCached();
        L13:
            r0.mIsDynamic = r5.isDynamic();
            r0.mIsPinned = r5.isPinned();
            r0.mIsDeclaredInManifest = r5.isDeclaredInManifest();
            r0.mIsImmutable = r5.isImmutable();
            r0.mIsEnabled = r5.isEnabled();
            r0.mHasKeyFieldsOnly = r5.hasKeyFieldsOnly();
            r0.mLocusId = ShortcutInfoCompat.getLocusId(r5);
            r0.mRank = r5.getRank();
            r0.mExtras = r5.getExtras();
            return;
        L6:
            if (r5.isEnabled() == false) goto L8;
            int r42 = 0;
        L9:
            r0.mDisabledReason = r42;
            goto L10
        L8:
            r42 = 3;
            goto L9
        }

        public Builder(Context r2, String r3) {
            ShortcutInfoCompat r0 = new ShortcutInfoCompat();
            this.mInfo = r0;
            r0.mContext = r2;
            r0.mId = r3;
        }

        public Builder(ShortcutInfoCompat r4) {
            ShortcutInfoCompat r0 = new ShortcutInfoCompat();
            this.mInfo = r0;
            r0.mContext = r4.mContext;
            r0.mId = r4.mId;
            r0.mPackageName = r4.mPackageName;
            Intent[] r3 = r4.mIntents;
            r0.mIntents = (Intent[]) Arrays.copyOf(r3, r3.length);
            r0.mActivity = r4.mActivity;
            r0.mLabel = r4.mLabel;
            r0.mLongLabel = r4.mLongLabel;
            r0.mDisabledMessage = r4.mDisabledMessage;
            r0.mDisabledReason = r4.mDisabledReason;
            r0.mIcon = r4.mIcon;
            r0.mIsAlwaysBadged = r4.mIsAlwaysBadged;
            r0.mUser = r4.mUser;
            r0.mLastChangedTimestamp = r4.mLastChangedTimestamp;
            r0.mIsCached = r4.mIsCached;
            r0.mIsDynamic = r4.mIsDynamic;
            r0.mIsPinned = r4.mIsPinned;
            r0.mIsDeclaredInManifest = r4.mIsDeclaredInManifest;
            r0.mIsImmutable = r4.mIsImmutable;
            r0.mIsEnabled = r4.mIsEnabled;
            r0.mLocusId = r4.mLocusId;
            r0.mIsLongLived = r4.mIsLongLived;
            r0.mHasKeyFieldsOnly = r4.mHasKeyFieldsOnly;
            r0.mRank = r4.mRank;
            Person[] r32 = r4.mPersons;
            if (r32 == null) goto L6;
            r0.mPersons = (Person[]) Arrays.copyOf(r32, r32.length);
        L6:
            if (r4.mCategories == null) goto L8;
            r0.mCategories = new HashSet(r4.mCategories);
        L8:
            PersistableBundle r33 = r4.mExtras;
            if (r33 == null) goto L11;
            r0.mExtras = r33;
        L11:
            r0.mExcludedSurfaces = r4.mExcludedSurfaces;
        }

        public ShortcutInfoCompat build() {
            if (TextUtils.isEmpty(this.mInfo.mLabel) == true) goto L46;
            ShortcutInfoCompat r0 = this.mInfo;
            Intent[] r2 = r0.mIntents;
            if (r2 != null) goto L7;
        L44:
            f$$ExternalSyntheticBUOutline1.m("Shortcut must have an intent");
            return null;
        L7:
            if (r2.length == 0) goto L44;
            if (this.mIsConversation == false) goto L15;
            if (r0.mLocusId != null) goto L13;
            r0.mLocusId = new LocusIdCompat(r0.mId);
        L13:
            this.mInfo.mIsLongLived = true;
        L15:
            if (this.mCapabilityBindings == null) goto L21;
            ShortcutInfoCompat r02 = this.mInfo;
            if (r02.mCategories != null) goto L19;
            r02.mCategories = new HashSet();
        L19:
            this.mInfo.mCategories.addAll(this.mCapabilityBindings);
        L21:
            if (this.mCapabilityBindingParams == null) goto L37;
            ShortcutInfoCompat r03 = this.mInfo;
            if (r03.mExtras != null) goto L25;
            r03.mExtras = new PersistableBundle();
        L25:
            Iterator<String> r04 = this.mCapabilityBindingParams.keySet().iterator();
        L27:
            if (r04.hasNext() == false) goto L37;
            String r1 = r04.next();
            Map<String, List<String>> r22 = this.mCapabilityBindingParams.get(r1);
            this.mInfo.mExtras.putStringArray(r1, (String[]) r22.keySet().toArray(new String[0]));
            Iterator<String> r3 = r22.keySet().iterator();
        L30:
            if (r3.hasNext() == false) goto L27;
            String r4 = r3.next();
            List<String> r6 = r22.get(r4);
            PersistableBundle r7 = this.mInfo.mExtras;
            String r42 = r1 + "/" + r4;
            if (r6 != null) goto L34;
            String[] r62 = new String[0];
        L35:
            r7.putStringArray(r42, r62);
            goto L30
        L34:
            r62 = (String[]) r6.toArray(new String[0]);
        L37:
            if (this.mSliceUri == null) goto L43;
            ShortcutInfoCompat r05 = this.mInfo;
            if (r05.mExtras != null) goto L41;
            r05.mExtras = new PersistableBundle();
        L41:
            this.mInfo.mExtras.putString("extraSliceUri", UriCompat.toSafeString(this.mSliceUri));
        L43:
            return this.mInfo;
        L46:
            f$$ExternalSyntheticBUOutline1.m("Shortcut must have a non-empty label");
            return null;
        }

        public Builder setActivity(ComponentName r2) {
            this.mInfo.mActivity = r2;
            return this;
        }

        public Builder setCategories(Set<String> r2) {
            ArraySet r0 = new ArraySet();
            r0.addAll(r2);
            this.mInfo.mCategories = r0;
            return this;
        }

        public Builder setDisabledMessage(CharSequence r2) {
            this.mInfo.mDisabledMessage = r2;
            return this;
        }

        public Builder setIcon(IconCompat r2) {
            this.mInfo.mIcon = r2;
            return this;
        }

        public Builder setIntent(Intent r1) {
            return setIntents(new Intent[]{r1});
        }

        public Builder setIntents(Intent[] r2) {
            this.mInfo.mIntents = r2;
            return this;
        }

        public Builder setLocusId(LocusIdCompat r2) {
            this.mInfo.mLocusId = r2;
            return this;
        }

        public Builder setLongLabel(CharSequence r2) {
            this.mInfo.mLongLabel = r2;
            return this;
        }

        public Builder setLongLived(boolean r2) {
            this.mInfo.mIsLongLived = r2;
            return this;
        }

        public Builder setPerson(Person r1) {
            return setPersons(new Person[]{r1});
        }

        public Builder setPersons(Person[] r2) {
            this.mInfo.mPersons = r2;
            return this;
        }

        public Builder setRank(int r2) {
            this.mInfo.mRank = r2;
            return this;
        }

        public Builder setShortLabel(CharSequence r2) {
            this.mInfo.mLabel = r2;
            return this;
        }
    }

    public ShortcutInfoCompat() {
        this.mIsEnabled = true;
    }

    private PersistableBundle buildLegacyExtrasBundle() {
        if (this.mExtras != null) goto L5;
        this.mExtras = new PersistableBundle();
    L5:
        Person[] r0 = this.mPersons;
        if (r0 != null) goto L8;
    L13:
        LocusIdCompat r02 = this.mLocusId;
        if (r02 == null) goto L16;
        this.mExtras.putString("extraLocusId", r02.getId());
    L16:
        this.mExtras.putBoolean("extraLongLived", this.mIsLongLived);
        return this.mExtras;
    L8:
        if (r0.length <= 0) goto L13;
        this.mExtras.putInt("extraPersonCount", r0.length);
        int r03 = 0;
    L11:
        if (r03 >= this.mPersons.length) goto L13;
        PersistableBundle r1 = this.mExtras;
        StringBuilder r2 = new StringBuilder("extraPerson_");
        int r3 = r03 + 1;
        r2.append(r3);
        r1.putPersistableBundle(r2.toString(), this.mPersons[r03].toPersistableBundle());
        r03 = r3;
        goto L11
    }

    public static List<ShortcutInfoCompat> fromShortcuts(Context r3, List<ShortcutInfo> r4) {
        ArrayList r0 = new ArrayList(r4.size());
        Iterator<ShortcutInfo> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L6;
        r0.add(new Builder(r3, ShortcutInfoCompat$$ExternalSyntheticApiModelOutline2.m(r42.next())).build());
        goto L4
    L6:
        return r0;
    }

    public static LocusIdCompat getLocusId(ShortcutInfo r2) {
        if (Build.VERSION.SDK_INT < 29) goto L11;
        if (r2.getLocusId() != null) goto L9;
        return null;
    L9:
        return LocusIdCompat.toLocusIdCompat(r2.getLocusId());
    L11:
        return getLocusIdFromExtra(r2.getExtras());
    }

    private static LocusIdCompat getLocusIdFromExtra(PersistableBundle r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        String r22 = r2.getString("extraLocusId");
        if (r22 != null) goto L9;
        return null;
    L9:
        return new LocusIdCompat(r22);
    }

    public static Person[] getPersonsFromExtra(PersistableBundle r5) {
        if (r5 != null) goto L4;
        return null;
    L4:
        if (r5.containsKey("extraPersonCount") == false) goto L13;
        int r0 = r5.getInt("extraPersonCount");
        Person[] r1 = new Person[r0];
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L9;
        StringBuilder r3 = new StringBuilder("extraPerson_");
        int r4 = r2 + 1;
        r3.append(r4);
        r1[r2] = Person.fromPersistableBundle(r5.getPersistableBundle(r3.toString()));
        r2 = r4;
        goto L7
    L9:
        return r1;
    L13:
        return null;
    }

    public Intent addToIntent(Intent r4) {
        r4.putExtra("android.intent.extra.shortcut.INTENT", this.mIntents[r0.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.mLabel.toString());
        if (this.mIcon == null) goto L12;
        Drawable r1 = null;
        if (this.mIsAlwaysBadged == false) goto L11;
        PackageManager r0 = this.mContext.getPackageManager();
        ComponentName r2 = this.mActivity;
        if (r2 != null) goto L14;
    L9:
        if (r1 != null) goto L11;
        r1 = this.mContext.getApplicationInfo().loadIcon(r0);
        goto L11
    L14:
        r1 = r0.getActivityIcon(r2);     // Catch: PackageManager.NameNotFoundException -> L13
    L11:
        this.mIcon.addToShortcutIntent(r4, r1, this.mContext);
    L12:
        return r4;
    }

    public ComponentName getActivity() {
        return this.mActivity;
    }

    public Set<String> getCategories() {
        return this.mCategories;
    }

    public CharSequence getDisabledMessage() {
        return this.mDisabledMessage;
    }

    public IconCompat getIcon() {
        return this.mIcon;
    }

    public String getId() {
        return this.mId;
    }

    public Intent getIntent() {
        return this.mIntents[r1.length - 1];
    }

    public Intent[] getIntents() {
        Intent[] r1 = this.mIntents;
        return (Intent[]) Arrays.copyOf(r1, r1.length);
    }

    public LocusIdCompat getLocusId() {
        return this.mLocusId;
    }

    public CharSequence getLongLabel() {
        return this.mLongLabel;
    }

    public int getRank() {
        return this.mRank;
    }

    public CharSequence getShortLabel() {
        return this.mLabel;
    }

    public boolean isExcludedFromSurfaces(int r1) {
        if ((this.mExcludedSurfaces & r1) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public ShortcutInfo toShortcutInfo() {
        ShortcutInfoCompat$$ExternalSyntheticApiModelOutline1.m();
        ShortcutInfo.Builder r0 = ShortcutInfoCompat$$ExternalSyntheticApiModelOutline0.m(this.mContext, this.mId).setShortLabel(this.mLabel).setIntents(this.mIntents);
        IconCompat r1 = this.mIcon;
        if (r1 == null) goto L6;
        r0.setIcon(r1.toIcon(this.mContext));
    L6:
        if (TextUtils.isEmpty(this.mLongLabel) == true) goto L9;
        r0.setLongLabel(this.mLongLabel);
    L9:
        if (TextUtils.isEmpty(this.mDisabledMessage) == true) goto L11;
        r0.setDisabledMessage(this.mDisabledMessage);
    L11:
        ComponentName r12 = this.mActivity;
        if (r12 == null) goto L14;
        r0.setActivity(r12);
    L14:
        Set<String> r13 = this.mCategories;
        if (r13 == null) goto L17;
        r0.setCategories(r13);
    L17:
        r0.setRank(this.mRank);
        PersistableBundle r14 = this.mExtras;
        if (r14 == null) goto L21;
        r0.setExtras(r14);
    L21:
        if (Build.VERSION.SDK_INT < 29) goto L34;
        Person[] r15 = this.mPersons;
        if (r15 != null) goto L25;
    L30:
        LocusIdCompat r16 = this.mLocusId;
        if (r16 == null) goto L33;
        r0.setLocusId(r16.toLocusId());
    L33:
        r0.setLongLived(this.mIsLongLived);
    L36:
        if (Build.VERSION.SDK_INT < 33) goto L39;
        Api33Impl.setExcludedFromSurfaces(r0, this.mExcludedSurfaces);
    L39:
        return r0.build();
    L25:
        if (r15.length <= 0) goto L30;
        int r17 = r15.length;
        android.app.Person[] r2 = new android.app.Person[r17];
        int r3 = 0;
    L27:
        if (r3 >= r17) goto L29;
        r2[r3] = this.mPersons[r3].toAndroidPerson();
        r3 = r3 + 1;
        goto L27
    L29:
        r0.setPersons(r2);
        goto L30
    L34:
        r0.setExtras(buildLegacyExtrasBundle());
        goto L36
    }
}
