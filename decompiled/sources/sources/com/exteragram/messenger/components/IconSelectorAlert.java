package com.exteragram.messenger.components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import com.exteragram.messenger.utils.ui.FolderIcons;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes4.dex */
public abstract class IconSelectorAlert {

    public interface OnIconSelectedListener {
        void onIconSelected(String r1);
    }

    public static /* synthetic */ void $r8$lambda$b3ZIL3otS284YS1EShI6wgNn894(ItemOptions r0, boolean r1, OnIconSelectedListener r2, String r3, View r4) {
        r0.dismiss();
        if (r1 == true) goto L6;
        r2.onIconSelected(r3);
        return;
    }

    public static void show(BaseFragment r20, View r21, String r22, OnIconSelectedListener r23) {
        Context r0 = r20.getContext();
        if (r0 != null) goto L5;
        return;
    L5:
        Theme.ResourcesProvider r1 = r20.getResourceProvider();
        int r2 = Theme.getColor(Theme.key_windowBackgroundWhiteValueText, r1);
        int r3 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon, r1);
        int r12 = Theme.getColor(Theme.key_listSelector, r1);
        View r4 = (View) r21.getParent();
        ItemOptions r5 = ItemOptions.makeOptions(r20, r4).setGravity(3).translate(r21.getX() - AndroidUtilities.dp(13.0f), 0.0f);
        if ((r4.getParent() instanceof RecyclerListView) == false) goto L8;
        r5.setScrimViewBackground(((RecyclerListView) r4.getParent()).getClipBackground(r4));
    L8:
        GridLayout r42 = new GridLayout(r0);
        int r6 = 6;
    L10:
        if (r6 <= 1) goto L14;
        if (AndroidUtilities.dp((r6 * 50) + 40) <= AndroidUtilities.displaySize.x) goto L14;
        r6 = r6 - 1;
    L14:
        r42.setColumnCount(r6);
        Iterator<String> r62 = FolderIcons.folderIcons.keySet().iterator();
    L16:
        if (r62.hasNext() == false) goto L26;
        String r7 = r62.next();
        boolean r9 = r7.equals(r22);
        ImageView r10 = new ImageView(r0);
        r10.setScaleType(ImageView.ScaleType.FIT_XY);
        r10.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));
        r10.setImageResource(FolderIcons.getTabIcon(r7));
        if (r9 == false) goto L20;
        int r122 = r2;
    L21:
        r10.setColorFilter(new PorterDuffColorFilter(r122, PorterDuff.Mode.MULTIPLY));
        if (r9 == false) goto L24;
        int r11 = Theme.multAlpha(r2, 0.15686275f);
    L25:
        r10.setBackground(Theme.createRadSelectorDrawable(r11, r12, 7, 7));
        r10.setOnClickListener(new IconSelectorAlert$$ExternalSyntheticLambda0(r5, r9, r23, r7));
        r42.addView(r10, LayoutHelper.createFrame(48, 48.0f, 17, 1.0f, 1.0f, 1.0f, 1.0f));
        goto L16
    L24:
        r11 = 0;
        goto L25
    L20:
        r122 = r3;
        goto L21
    L26:
        r5.addView(r42, LayoutHelper.createLinear(-2, -2, 4.0f, 4.0f, 4.0f, 4.0f));
        r5.show();
    }
}
