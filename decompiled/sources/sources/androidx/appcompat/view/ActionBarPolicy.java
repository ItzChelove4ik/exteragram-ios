package androidx.appcompat.view;

import android.content.Context;
import android.content.res.Configuration;
import androidx.appcompat.R$bool;

/* loaded from: classes3.dex */
public class ActionBarPolicy {
    private Context mContext;

    private ActionBarPolicy(Context r1) {
        this.mContext = r1;
    }

    public static ActionBarPolicy get(Context r1) {
        return new ActionBarPolicy(r1);
    }

    public boolean enableHomeButtonByDefault() {
        if (this.mContext.getApplicationInfo().targetSdkVersion >= 14) goto L6;
        return true;
    L6:
        return false;
    }

    public int getEmbeddedMenuWidthLimit() {
        return this.mContext.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public int getMaxActionButtons() {
        Configuration r3 = this.mContext.getResources().getConfiguration();
        int r0 = r3.screenWidthDp;
        int r1 = r3.screenHeightDp;
        if (r3.smallestScreenWidthDp > 600) goto L27;
        if (r0 <= 600) goto L6;
        return 5;
    L6:
        if (r0 <= 960) goto L8;
        if (r1 <= 720) goto L8;
        return 5;
    L8:
        if (r0 <= 720) goto L12;
        if (r1 <= 960) goto L12;
        return 5;
    L12:
        if (r0 < 500) goto L14;
        return 4;
    L14:
        if (r0 <= 640) goto L16;
        if (r1 <= 480) goto L16;
        return 4;
    L16:
        if (r0 <= 480) goto L20;
        if (r1 <= 640) goto L20;
        return 4;
    L20:
        if (r0 < 360) goto L23;
        return 3;
    L23:
        return 2;
    L27:
        return 5;
    }

    public boolean hasEmbeddedTabs() {
        return this.mContext.getResources().getBoolean(R$bool.abc_action_bar_embed_tabs);
    }

    public boolean showsOverflowMenuButton() {
        return true;
    }
}
