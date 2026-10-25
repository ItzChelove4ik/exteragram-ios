package org.telegram.messenger.support.customtabs;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.BundleCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public final class CustomTabsIntent {
    public final Intent intent;
    public final Bundle startAnimationBundle;

    public static final class Builder {
        private ArrayList<Bundle> mActionButtons;
        private boolean mInstantAppsEnabled;
        private final Intent mIntent;
        private ArrayList<Bundle> mMenuItems;
        private Bundle mStartAnimationBundle;

        public Builder(CustomTabsSession r4) {
            Intent r0 = new Intent("android.intent.action.VIEW");
            this.mIntent = r0;
            IBinder r1 = null;
            this.mMenuItems = null;
            this.mStartAnimationBundle = null;
            this.mActionButtons = null;
            this.mInstantAppsEnabled = true;
            if (r4 == null) goto L5;
            r0.setPackage(r4.getComponentName().getPackageName());
        L5:
            Bundle r3 = new Bundle();
            if (r4 == null) goto L9;
            r1 = r4.getBinder();
        L9:
            BundleCompat.putBinder(r3, "android.support.customtabs.extra.SESSION", r1);
            r0.putExtras(r3);
        }

        public Builder addMenuItem(String r3, PendingIntent r4) {
            if (this.mMenuItems != null) goto L5;
            this.mMenuItems = new ArrayList();
        L5:
            Bundle r0 = new Bundle();
            r0.putString("android.support.customtabs.customaction.MENU_ITEM_TITLE", r3);
            r0.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", r4);
            this.mMenuItems.add(r0);
            return this;
        }

        public CustomTabsIntent build() {
            ArrayList<Bundle> r0 = this.mMenuItems;
            if (r0 == null) goto L5;
            this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.MENU_ITEMS", r0);
        L5:
            ArrayList<Bundle> r02 = this.mActionButtons;
            if (r02 == null) goto L8;
            this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.TOOLBAR_ITEMS", r02);
        L8:
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", this.mInstantAppsEnabled);
            return new CustomTabsIntent(this.mIntent, this.mStartAnimationBundle, null);
        }

        public Builder setActionButton(Bitmap r4, String r5, PendingIntent r6, boolean r7) {
            Bundle r0 = new Bundle();
            r0.putInt("android.support.customtabs.customaction.ID", 0);
            r0.putParcelable("android.support.customtabs.customaction.ICON", r4);
            r0.putString("android.support.customtabs.customaction.DESCRIPTION", r5);
            r0.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", r6);
            this.mIntent.putExtra("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", r0);
            this.mIntent.putExtra("android.support.customtabs.extra.TINT_ACTION_BUTTON", r7);
            return this;
        }

        public Builder setShowTitle(boolean r3) {
            this.mIntent.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", r3 ? 1 : 0);
            return this;
        }

        public Builder setToolbarColor(int r3) {
            this.mIntent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", r3);
            return this;
        }
    }

    private CustomTabsIntent(Intent r1, Bundle r2) {
        this.intent = r1;
        this.startAnimationBundle = r2;
    }

    public /* synthetic */ CustomTabsIntent(Intent r1, Bundle r2, CustomTabsIntentIA r3) {
        this(r1, r2);
    }

    public void launchUrl(Context r2, Uri r3) {
        this.intent.setData(r3);
        ContextCompat.startActivity(r2, this.intent, this.startAnimationBundle);
    }

    public void setUseNewTask() {
        this.intent.addFlags(268435456);
    }
}
