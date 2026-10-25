package com.exteragram.messenger.appicons.ui.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import com.exteragram.messenger.appicons.AppIcon;
import com.google.android.material.navigation.NavigationBarView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class AppIconBulletinLayout extends Bulletin.ButtonLayout {
    public AppIconBulletinLayout(Context r9, AppIcon r10, Theme.ResourcesProvider r11) {
        super(r9, r11);
        AppIconPreviewView r0 = new AppIconPreviewView(r9, r11);
        r0.setIcon(r10);
        addView(r0, LayoutHelper.createFrameRelatively(40.0f, 40.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 10.0f, 8.0f, 10.0f, 8.0f));
        TextView r112 = new TextView(r9);
        r112.setGravity(PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES);
        r112.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
        r112.setTextColor(getThemedColor(Theme.key_undo_infoColor));
        r112.setTextSize(1, 15.0f);
        r112.setTypeface(AndroidUtilities.regular());
        r112.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AppIconChangedTo, new Object[]{"**" + r10.getTitle() + "**"})));
        addView(r112, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 62.0f, 0.0f, 16.0f, 0.0f));
    }
}
