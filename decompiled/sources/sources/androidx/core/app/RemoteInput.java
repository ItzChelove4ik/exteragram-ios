package androidx.core.app;

import android.app.RemoteInput;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public final class RemoteInput {
    private final boolean mAllowFreeFormTextInput;
    private final Set<String> mAllowedDataTypes;
    private final CharSequence[] mChoices;
    private final int mEditChoicesBeforeSending;
    private final Bundle mExtras;
    private final CharSequence mLabel;
    private final String mResultKey;

    public static class Api20Impl {
        public static android.app.RemoteInput fromCompat(RemoteInput r4) {
            RemoteInput.Builder r0 = new RemoteInput.Builder(r4.getResultKey()).setLabel(r4.getLabel()).setChoices(r4.getChoices()).setAllowFreeFormInput(r4.getAllowFreeFormInput()).addExtras(r4.getExtras());
            if (Build.VERSION.SDK_INT < 26) goto L11;
            Set<String> r1 = r4.getAllowedDataTypes();
            if (r1 == null) goto L11;
            Iterator<String> r12 = r1.iterator();
        L8:
            if (r12.hasNext() == false) goto L11;
            Api26Impl.setAllowDataType(r0, r12.next(), true);
        L11:
            if (Build.VERSION.SDK_INT < 29) goto L14;
            Api29Impl.setEditChoicesBeforeSending(r0, r4.getEditChoicesBeforeSending());
        L14:
            return r0.build();
        }
    }

    /* loaded from: classes3.dex */
    public static class Api26Impl {
        public static RemoteInput.Builder setAllowDataType(RemoteInput.Builder r0, String r1, boolean r2) {
            return r0.setAllowDataType(r1, r2);
        }
    }

    public static class Api29Impl {
        public static RemoteInput.Builder setEditChoicesBeforeSending(RemoteInput.Builder r0, int r1) {
            return r0.setEditChoicesBeforeSending(r1);
        }
    }

    public static final class Builder {
        private boolean mAllowFreeFormTextInput;
        private final Set<String> mAllowedDataTypes;
        private CharSequence[] mChoices;
        private int mEditChoicesBeforeSending;
        private final Bundle mExtras;
        private CharSequence mLabel;
        private final String mResultKey;

        public Builder(String r2) {
            this.mAllowedDataTypes = new HashSet();
            this.mExtras = new Bundle();
            this.mAllowFreeFormTextInput = true;
            this.mEditChoicesBeforeSending = 0;
            if (r2 == null) goto L6;
            this.mResultKey = r2;
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("Result key can't be null");
            throw null;
        }

        public RemoteInput build() {
            return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormTextInput, this.mEditChoicesBeforeSending, this.mExtras, this.mAllowedDataTypes);
        }

        public Builder setLabel(CharSequence r1) {
            this.mLabel = r1;
            return this;
        }
    }

    public RemoteInput(String r1, CharSequence r2, CharSequence[] r3, boolean r4, int r5, Bundle r6, Set<String> r7) {
        this.mResultKey = r1;
        this.mLabel = r2;
        this.mChoices = r3;
        this.mAllowFreeFormTextInput = r4;
        this.mEditChoicesBeforeSending = r5;
        this.mExtras = r6;
        this.mAllowedDataTypes = r7;
        if (getEditChoicesBeforeSending() == 2) goto L5;
        return;
    L5:
        if (getAllowFreeFormInput() == true) goto L10;
        f$$ExternalSyntheticBUOutline1.m("setEditChoicesBeforeSending requires setAllowFreeFormInput");
        throw null;
    }

    public static android.app.RemoteInput fromCompat(RemoteInput r0) {
        return Api20Impl.fromCompat(r0);
    }

    public static android.app.RemoteInput[] fromCompat(RemoteInput[] r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        android.app.RemoteInput[] r0 = new android.app.RemoteInput[r3.length];
        int r1 = 0;
    L7:
        if (r1 >= r3.length) goto L9;
        r0[r1] = fromCompat(r3[r1]);
        r1 = r1 + 1;
        goto L7
    L9:
        return r0;
    }

    public static Bundle getResultsFromIntent(Intent r0) {
        return android.app.RemoteInput.getResultsFromIntent(r0);
    }

    public boolean getAllowFreeFormInput() {
        return this.mAllowFreeFormTextInput;
    }

    public Set<String> getAllowedDataTypes() {
        return this.mAllowedDataTypes;
    }

    public CharSequence[] getChoices() {
        return this.mChoices;
    }

    public int getEditChoicesBeforeSending() {
        return this.mEditChoicesBeforeSending;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public CharSequence getLabel() {
        return this.mLabel;
    }

    public String getResultKey() {
        return this.mResultKey;
    }

    public boolean isDataOnly() {
        if (getAllowFreeFormInput() == false) goto L5;
        return false;
    L5:
        if (getChoices() == null) goto L9;
        if (getChoices().length == 0) goto L9;
        return false;
    L9:
        if (getAllowedDataTypes() != null) goto L11;
        return false;
    L11:
        if (getAllowedDataTypes().isEmpty() == true) goto L18;
        return true;
    L18:
        return false;
    }
}
