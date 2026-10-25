package androidx.mediarouter.media;

import android.os.Build;
import android.os.Bundle;
import c.f$$ExternalSyntheticBUOutline2;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes4.dex */
public class MediaRouterParams {
    public static final int DIALOG_TYPE_DEFAULT = 1;
    public static final int DIALOG_TYPE_DYNAMIC_GROUP = 2;
    public static final String ENABLE_GROUP_VOLUME_UX = "androidx.mediarouter.media.MediaRouterParams.ENABLE_GROUP_VOLUME_UX";
    public static final String EXTRAS_KEY_FIXED_CAST_ICON = "androidx.mediarouter.media.MediaRouterParams.FIXED_CAST_ICON";
    final int mDialogType;
    final Bundle mExtras;
    final boolean mMediaTransferReceiverEnabled;
    final boolean mMediaTransferRestrictedToSelfProviders;
    final boolean mOutputSwitcherEnabled;
    final boolean mTransferToLocalEnabled;

    public static final class Builder {
        int mDialogType;
        Bundle mExtras;
        boolean mMediaTransferEnabled;
        boolean mMediaTransferRestrictedToSelfProviders;
        boolean mOutputSwitcherEnabled;
        boolean mTransferToLocalEnabled;

        public Builder() {
            boolean r0 = true;
            this.mDialogType = 1;
            if (Build.VERSION.SDK_INT >= 30) goto L6;
            r0 = false;
        L6:
            this.mMediaTransferEnabled = r0;
        }

        public Builder(MediaRouterParams r4) {
            boolean r0 = true;
            this.mDialogType = 1;
            if (Build.VERSION.SDK_INT >= 30) goto L6;
            r0 = false;
        L6:
            this.mMediaTransferEnabled = r0;
            Bundle r02 = null;
            if (r4 == null) goto L14;
            this.mDialogType = r4.mDialogType;
            this.mOutputSwitcherEnabled = r4.mOutputSwitcherEnabled;
            this.mTransferToLocalEnabled = r4.mTransferToLocalEnabled;
            this.mMediaTransferEnabled = r4.mMediaTransferReceiverEnabled;
            this.mMediaTransferRestrictedToSelfProviders = r4.mMediaTransferRestrictedToSelfProviders;
            if (r4.mExtras == null) goto L12;
            r02 = new Bundle(r4.mExtras);
        L12:
            this.mExtras = r02;
            return;
        L14:
            f$$ExternalSyntheticBUOutline2.m("params should not be null!");
            throw null;
        }

        public MediaRouterParams build() {
            return new MediaRouterParams(this);
        }

        public Builder setDialogType(int r1) {
            this.mDialogType = r1;
            return this;
        }

        public Builder setExtras(Bundle r2) {
            if (r2 != null) goto L4;
            Bundle r22 = null;
        L5:
            this.mExtras = r22;
            return this;
        L4:
            r22 = new Bundle(r2);
            goto L5
        }

        public Builder setMediaTransferReceiverEnabled(boolean r3) {
            if (Build.VERSION.SDK_INT < 30) goto L5;
            this.mMediaTransferEnabled = r3;
        L5:
            return this;
        }

        public Builder setMediaTransferRestrictedToSelfProviders(boolean r3) {
            if (Build.VERSION.SDK_INT < 30) goto L5;
            this.mMediaTransferRestrictedToSelfProviders = r3;
        L5:
            return this;
        }

        public Builder setOutputSwitcherEnabled(boolean r3) {
            if (Build.VERSION.SDK_INT < 30) goto L5;
            this.mOutputSwitcherEnabled = r3;
        L5:
            return this;
        }

        public Builder setTransferToLocalEnabled(boolean r3) {
            if (Build.VERSION.SDK_INT < 30) goto L5;
            this.mTransferToLocalEnabled = r3;
        L5:
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DialogType {
    }

    public MediaRouterParams(Builder r2) {
        this.mDialogType = r2.mDialogType;
        this.mMediaTransferReceiverEnabled = r2.mMediaTransferEnabled;
        this.mOutputSwitcherEnabled = r2.mOutputSwitcherEnabled;
        this.mTransferToLocalEnabled = r2.mTransferToLocalEnabled;
        this.mMediaTransferRestrictedToSelfProviders = r2.mMediaTransferRestrictedToSelfProviders;
        Bundle r22 = r2.mExtras;
        if (r22 != null) goto L5;
        Bundle r23 = Bundle.EMPTY;
    L6:
        this.mExtras = r23;
        return;
    L5:
        r23 = new Bundle(r22);
        goto L6
    }

    public int getDialogType() {
        return this.mDialogType;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public boolean isMediaTransferReceiverEnabled() {
        return this.mMediaTransferReceiverEnabled;
    }

    public boolean isMediaTransferRestrictedToSelfProviders() {
        return this.mMediaTransferRestrictedToSelfProviders;
    }

    public boolean isOutputSwitcherEnabled() {
        return this.mOutputSwitcherEnabled;
    }

    public boolean isTransferToLocalEnabled() {
        return this.mTransferToLocalEnabled;
    }
}
