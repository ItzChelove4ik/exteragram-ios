package com.exteragram.messenger.components;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.exteragram.messenger.components.ReverseImageSearchSheet;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.PopupSwipeBackLayout;
import org.telegram.ui.Stories.DarkThemeResourceProvider;

/* loaded from: classes4.dex */
public class SearchPhotoPopupWrapper {
    public ActionBarPopupWindow.ActionBarPopupWindowLayout searchSwipeBackLayout;

    /* renamed from: com.exteragram.messenger.components.SearchPhotoPopupWrapper$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ SearchPhotoPopupWrapper this$0;

        public AnonymousClass1(SearchPhotoPopupWrapper r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
        }
    }

    /* renamed from: com.exteragram.messenger.components.SearchPhotoPopupWrapper$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ SearchPhotoPopupWrapper this$0;

        public AnonymousClass2(SearchPhotoPopupWrapper r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$BLCNa3Egdiqh3F5WbrL0hHeAreo(Utilities.Callback r0, View r1) {
        r0.run(ReverseImageSearchSheet.Provider.BING);
    }

    public static /* synthetic */ void $r8$lambda$I217GNeQuON34dQ202L9obOSFkQ(PopupSwipeBackLayout r0, View r1) {
        r0.closeForeground();
    }

    public static /* synthetic */ void $r8$lambda$IOHEpQ6S7nuy5q3N_bLQefmiNGQ(Utilities.Callback r0, View r1) {
        r0.run(ReverseImageSearchSheet.Provider.YANDEX);
    }

    /* renamed from: $r8$lambda$JMk7EeBGwt4UiWnuY_Ye-YafWdE, reason: not valid java name */
    public static /* synthetic */ void m1230$r8$lambda$JMk7EeBGwt4UiWnuY_YeYafWdE(Utilities.Callback r0, View r1) {
        r0.run(ReverseImageSearchSheet.Provider.TINEYE);
    }

    public static /* synthetic */ void $r8$lambda$RajmxCMa3DfaXEOVL16mnnmrbT4(Utilities.Callback r0, View r1) {
        r0.run(ReverseImageSearchSheet.Provider.GOOGLE);
    }

    public SearchPhotoPopupWrapper(Context r11, PopupSwipeBackLayout r12, Utilities.Callback<ReverseImageSearchSheet.Provider> r13) {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r11, 0, null);
        this.searchSwipeBackLayout = r0;
        r0.setFitItems(true);
        ActionBarMenuSubItem r02 = ActionBarMenuItem.addItem(this.searchSwipeBackLayout, R.drawable.msg_arrow_back, LocaleController.getString(R.string.Back), false, null);
        r02.setOnClickListener(new SearchPhotoPopupWrapper$$ExternalSyntheticLambda0(r12));
        r02.setColors(-328966, -328966);
        r02.setSelectorColor(268435455);
        AnonymousClass1 r03 = new AnonymousClass1(this, r11);
        r03.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r03.setBackgroundColor(-15198184);
        this.searchSwipeBackLayout.addView(r03);
        LinearLayout.LayoutParams r7 = (LinearLayout.LayoutParams) r03.getLayoutParams();
        if (LocaleController.isRTL == false) goto L5;
        r7.gravity = 5;
    L5:
        r7.width = -1;
        r7.height = AndroidUtilities.dp(8.0f);
        r03.setLayoutParams(r7);
        ActionBarMenuSubItem r04 = ActionBarMenuItem.addItem(this.searchSwipeBackLayout, 0, "Yandex", false, null);
        r04.setColors(-328966, -328966);
        r04.setOnClickListener(new SearchPhotoPopupWrapper$$ExternalSyntheticLambda1(r13));
        r04.setSelectorColor(268435455);
        ActionBarMenuSubItem r05 = ActionBarMenuItem.addItem(this.searchSwipeBackLayout, 0, "Google", false, null);
        r05.setColors(-328966, -328966);
        r05.setOnClickListener(new SearchPhotoPopupWrapper$$ExternalSyntheticLambda2(r13));
        r05.setSelectorColor(268435455);
        ActionBarMenuSubItem r06 = ActionBarMenuItem.addItem(this.searchSwipeBackLayout, 0, "Bing", false, null);
        r06.setColors(-328966, -328966);
        r06.setOnClickListener(new SearchPhotoPopupWrapper$$ExternalSyntheticLambda3(r13));
        r06.setSelectorColor(268435455);
        ActionBarMenuSubItem r07 = ActionBarMenuItem.addItem(this.searchSwipeBackLayout, 0, "TinEye", false, null);
        r07.setColors(-328966, -328966);
        r07.setOnClickListener(new SearchPhotoPopupWrapper$$ExternalSyntheticLambda4(r13));
        r07.setSelectorColor(268435455);
        AnonymousClass2 r122 = new AnonymousClass2(this, r11);
        r122.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r122.setBackgroundColor(-15198184);
        this.searchSwipeBackLayout.addView(r122);
        r122.setLayoutParams(r7);
        LinkSpanDrawable.LinksTextView r123 = new LinkSpanDrawable.LinksTextView(r11);
        r123.setTag(R.id.fit_width_tag, 1);
        r123.setPadding(AndroidUtilities.dp(13.0f), 0, AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f));
        r123.setTextSize(1, 12.0f);
        r123.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, new DarkThemeResourceProvider()));
        r123.setMovementMethod(LinkMovementMethod.getInstance());
        r123.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText, new DarkThemeResourceProvider()));
        r123.setText(LocaleController.getString(R.string.SearchPhotoInfo));
        this.searchSwipeBackLayout.addView(r123, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 8, 0, 0));
    }
}
