package org.telegram.ui.Components;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes7.dex */
public class AutoDeletePopupWrapper {
    View backItem;
    Callback callback;
    private final ActionBarMenuSubItem disableItem;
    long lastDismissTime;
    public TextView textView;
    public ActionBarPopupWindow.ActionBarPopupWindowLayout windowLayout;

    public interface Callback {
        void dismiss();

        void setAutoDeleteHistory(int r1, int r2);

        default void showGlobalAutoDeleteScreen() {
        }
    }

    public static /* synthetic */ void $r8$lambda$Q2nh5jChhwsIXDDSrnYNEUIiVFI(AutoDeletePopupWrapper r0, Callback r1, View r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$RmtylxBbX0rYfB0ENSMmHc2_zl4(AutoDeletePopupWrapper r0, Callback r1, View r2) {
        r0.lambda$new$3(r1, r2);
    }

    /* renamed from: $r8$lambda$USlcm-sxkAc3uqj0FYHVq5bRzA8, reason: not valid java name */
    public static /* synthetic */ void m8928$r8$lambda$USlcmsxkAc3uqj0FYHVq5bRzA8(PopupSwipeBackLayout r0, View r1) {
        r0.closeForeground();
    }

    /* renamed from: $r8$lambda$YQFUOFFzeW6FfyoOWpd-kfggaHk, reason: not valid java name */
    public static /* synthetic */ void m8929$r8$lambda$YQFUOFFzeW6FfyoOWpdkfggaHk(AutoDeletePopupWrapper r0, Context r1, int r2, Theme.ResourcesProvider r3, Callback r4, View r5) {
        r0.lambda$new$5(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$i-s9WtHaGe-2VWVaMZVmEm58t8k, reason: not valid java name */
    public static /* synthetic */ void m8930$r8$lambda$is9WtHaGe2VWVaMZVmEm58t8k(AutoDeletePopupWrapper r0, Callback r1, View r2) {
        r0.lambda$new$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ig1Q43uSiMGmQ1UTE_nPDQaIV70(Callback r0, boolean r1, int r2, int r3) {
        int r12 = r2 * 60;
        if (r2 != 0) goto L5;
        int r22 = 71;
    L6:
        r0.setAutoDeleteHistory(r12, r22);
        return;
    L5:
        r22 = 70;
        goto L6
    }

    /* renamed from: $r8$lambda$pkOlM_OG3YC-99Pie21Izrle9VI, reason: not valid java name */
    public static /* synthetic */ void m8931$r8$lambda$pkOlM_OG3YC99Pie21Izrle9VI(AutoDeletePopupWrapper r0, Callback r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$r4YAIQTe0yKPjoAU5JGI0r2cXk0(AutoDeletePopupWrapper r0, int r1) {
        r0.lambda$updateItems$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$uAmaaXUYo0FuYyIWyVJz21uOJyo(AutoDeletePopupWrapper r0) {
        r0.lambda$allowExtendedHint$8();
    }

    public AutoDeletePopupWrapper(Context r11, PopupSwipeBackLayout r12, Callback r13, boolean r14, int r15, Theme.ResourcesProvider r16) {
        if (r14 == false) goto L5;
        int r1 = R.drawable.popup_fixed_alert;
    L6:
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r11, r1, r16);
        this.windowLayout = r0;
        r0.setFitItems(true);
        this.callback = r13;
        if (r12 == null) goto L9;
        ActionBarMenuSubItem r02 = ActionBarMenuItem.addItem(this.windowLayout, R.drawable.msg_arrow_back, LocaleController.getString(R.string.Back), false, r16);
        this.backItem = r02;
        r02.setOnClickListener(new AutoDeletePopupWrapper$$ExternalSyntheticLambda2(r12));
    L9:
        ActionBarMenuItem.addItem(this.windowLayout, R.drawable.msg_autodelete_1d, LocaleController.getString(R.string.AutoDelete1Day), false, r16).setOnClickListener(new AutoDeletePopupWrapper$$ExternalSyntheticLambda3(this, r13));
        ActionBarMenuItem.addItem(this.windowLayout, R.drawable.msg_autodelete_1w, LocaleController.getString(R.string.AutoDelete7Days), false, r16).setOnClickListener(new AutoDeletePopupWrapper$$ExternalSyntheticLambda4(this, r13));
        ActionBarMenuItem.addItem(this.windowLayout, R.drawable.msg_autodelete_1m, LocaleController.getString(R.string.AutoDelete1Month), false, r16).setOnClickListener(new AutoDeletePopupWrapper$$ExternalSyntheticLambda5(this, r13));
        String r122 = LocaleController.getString(R.string.AutoDeleteCustom);
        if (r15 != 1) goto L12;
        r122 = LocaleController.getString(R.string.AutoDeleteCustom2);
    L12:
        ActionBarMenuItem.addItem(this.windowLayout, R.drawable.msg_customize, r122, false, r16).setOnClickListener(new AutoDeletePopupWrapper$$ExternalSyntheticLambda6(this, r11, r15, r16, r13));
        ActionBarMenuSubItem r123 = ActionBarMenuItem.addItem(this.windowLayout, R.drawable.msg_disable, LocaleController.getString(R.string.AutoDeleteDisable), false, r16);
        this.disableItem = r123;
        r123.setOnClickListener(new AutoDeletePopupWrapper$$ExternalSyntheticLambda7(this, r13));
        if (r15 == 1) goto L15;
        int r132 = Theme.key_text_RedBold;
        r123.setColors(Theme.getColor(r132), Theme.getColor(r132));
    L15:
        if (r15 == 1) goto L18;
        FrameLayout r124 = new FrameLayout(r11);
        r124.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuSeparator, r16));
        View r133 = new View(r11);
        r133.setBackground(Theme.getThemedDrawableByKey(r11, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow, r16));
        r124.addView(r133, LayoutHelper.createFrame(-1, -1.0f));
        r124.setTag(R.id.fit_width_tag, 1);
        this.windowLayout.addView(r124, LayoutHelper.createLinear(-1, 8));
        LinkSpanDrawable.LinksTextView r125 = new LinkSpanDrawable.LinksTextView(r11);
        this.textView = r125;
        r125.setTag(R.id.fit_width_tag, 1);
        this.textView.setPadding(AndroidUtilities.dp(13.0f), 0, AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f));
        this.textView.setTextSize(1, 13.0f);
        this.textView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        this.textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.textView.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
        this.textView.setText(LocaleController.getString(R.string.AutoDeletePopupDescription));
        this.windowLayout.addView(this.textView, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 8, 0, 0));
        return;
    L18:
        return;
    L5:
        r1 = 0;
        goto L6
    }

    private void dismiss() {
        this.callback.dismiss();
        this.lastDismissTime = System.currentTimeMillis();
    }

    private /* synthetic */ void lambda$allowExtendedHint$8() {
        this.callback.showGlobalAutoDeleteScreen();
    }

    private /* synthetic */ void lambda$new$1(Callback r1, View r2) {
        dismiss();
        r1.setAutoDeleteHistory(86400, 70);
    }

    private /* synthetic */ void lambda$new$2(Callback r1, View r2) {
        dismiss();
        r1.setAutoDeleteHistory(604800, 70);
    }

    private /* synthetic */ void lambda$new$3(Callback r1, View r2) {
        dismiss();
        r1.setAutoDeleteHistory(2678400, 70);
    }

    private /* synthetic */ void lambda$new$5(Context r1, int r2, Theme.ResourcesProvider r3, Callback r4, View r5) {
        dismiss();
        AlertsCreator.createAutoDeleteDatePickerDialog(r1, r2, r3, new AutoDeletePopupWrapper$$ExternalSyntheticLambda8(r4));
    }

    private /* synthetic */ void lambda$new$6(Callback r1, View r2) {
        dismiss();
        r1.setAutoDeleteHistory(0, 71);
    }

    private /* synthetic */ void lambda$updateItems$7(int r1) {
        updateItems(r1);
    }

    public void allowExtendedHint(int r4) {
        if (this.textView != null) goto L5;
        return;
    L5:
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        r0.append(LocaleController.getString(R.string.AutoDeletePopupDescription));
        r0.append("\n\n");
        r0.append(AndroidUtilities.replaceSingleLink(LocaleController.getString(R.string.AutoDeletePopupDescription2), r4, new AutoDeletePopupWrapper$$ExternalSyntheticLambda0(this)));
        this.textView.setText(r0);
    }

    public void updateItems(int r5) {
        if ((System.currentTimeMillis() - this.lastDismissTime) >= 200) goto L6;
        AndroidUtilities.runOnUIThread(new AutoDeletePopupWrapper$$ExternalSyntheticLambda1(this, r5));
        return;
    L6:
        ActionBarMenuSubItem r4 = this.disableItem;
        if (r5 != 0) goto L10;
        r4.setVisibility(8);
        return;
    L10:
        r4.setVisibility(0);
    }
}
