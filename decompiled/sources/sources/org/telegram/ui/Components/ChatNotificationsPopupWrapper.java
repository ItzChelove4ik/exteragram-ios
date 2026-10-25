package org.telegram.ui.Components;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.pillstack.ui.pills.crypto.RatePill$$ExternalSyntheticLambda1;
import java.util.HashSet;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ProfileNotificationsActivity;

/* loaded from: classes7.dex */
public class ChatNotificationsPopupWrapper {
    View backItem;
    Callback callback;
    int currentAccount;
    private final View gap;
    private final boolean isProfile;
    long lastDismissTime;
    ActionBarMenuSubItem muteForLastSelected;
    private int muteForLastSelected1Time;
    ActionBarMenuSubItem muteForLastSelected2;
    private int muteForLastSelected2Time;
    ActionBarMenuSubItem muteUnmuteButton;
    ActionBarPopupWindow popupWindow;
    ActionBarMenuSubItem soundToggle;
    private final TextView topicsExceptionsTextView;
    public int type;
    public ActionBarPopupWindow.ActionBarPopupWindowLayout windowLayout;

    /* renamed from: org.telegram.ui.Components.ChatNotificationsPopupWrapper$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBarPopupWindow.ActionBarPopupWindowLayout {
        Path path;
        final /* synthetic */ ChatNotificationsPopupWrapper this$0;

        public AnonymousClass1(ChatNotificationsPopupWrapper r1, Context r2, int r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
            this.path = new Path();
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r6, View r7, long r8) {
            r6.save();
            this.path.rewind();
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(r7.getLeft(), r7.getTop(), r7.getRight(), r7.getBottom());
            this.path.addRoundRect(r0, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), Path.Direction.CW);
            r6.clipPath(this.path);
            boolean r5 = super.drawChild(r6, r7, r8);
            r6.restore();
            return r5;
        }
    }

    public interface Callback {
        default void dismiss() {
        }

        void muteFor(int r1);

        default void openExceptions() {
        }

        void showCustomize();

        void toggleMute();

        void toggleSound();
    }

    /* renamed from: $r8$lambda$26-sB8j7y7eHkAnMF_Ht1pyBRts, reason: not valid java name */
    public static /* synthetic */ void m9946$r8$lambda$26sB8j7y7eHkAnMF_Ht1pyBRts(ChatNotificationsPopupWrapper r0, Callback r1, View r2) {
        r0.lambda$new$7(r1, r2);
    }

    /* renamed from: $r8$lambda$2E5Qdzb2TlshZLi5DNrM-yDRQJs, reason: not valid java name */
    public static /* synthetic */ void m9947$r8$lambda$2E5Qdzb2TlshZLi5DNrMyDRQJs(int r2, int r3, Utilities.Callback r4) {
        if (r2 == 0) goto L4;
        SharedPreferences r32 = MessagesController.getNotificationsSettings(r3);
        int r0 = r32.getInt("last_selected_mute_until_time", 0);
        r32.edit().putInt("last_selected_mute_until_time", r2).putInt("last_selected_mute_until_time2", r0).apply();
    L4:
        r4.run(Integer.valueOf(r2));
    }

    public static /* synthetic */ void $r8$lambda$31RerRr0yK3qaJutUXuPZrSTK8M(ItemOptions r3, int r4, long r5, long r7, ItemOptions r9, BaseFragment r10, Theme.ResourcesProvider r11) {
        r3.dismiss();
        SharedPreferences r32 = MessagesController.getNotificationsSettings(r4);
        boolean r42 = r32.getBoolean("sound_enabled_" + NotificationsController.getSharedPrefKey(r5, r7), true);
        boolean r1 = !r42;
        r32.edit().putBoolean("sound_enabled_" + NotificationsController.getSharedPrefKey(r5, r7), r1).apply();
        r9.dismiss();
        if (BulletinFactory.canShowBulletin(r10) == false) goto L6;
        BulletinFactory.createSoundEnabledBulletin(r10, r42 ? 1 : 0, r11).show();
        return;
    }

    /* renamed from: $r8$lambda$8uXFMfYpDsZYmw-QDJxZxLDz-ck, reason: not valid java name */
    public static /* synthetic */ void m9948$r8$lambda$8uXFMfYpDsZYmwQDJxZxLDzck(PopupSwipeBackLayout r0, View r1) {
        r0.closeForeground();
    }

    public static /* synthetic */ void $r8$lambda$99jwGva_EFC_FHEqFr4J7466z3Y(ItemOptions r6, int r7, long r8, long r10, BaseFragment r12, Theme.ResourcesProvider r13) {
        r6.dismiss();
        boolean r62 = MessagesController.getInstance(r7).isDialogMuted(r8, r10);
        NotificationsController.getInstance(r7).muteDialog(r8, r10, !r62);
        if (BulletinFactory.canShowBulletin(r12) == false) goto L12;
        if (r62 == true) goto L6;
        int r72 = 3;
    L7:
        if (r62 == true) goto L9;
        int r63 = Integer.MAX_VALUE;
    L10:
        BulletinFactory.createMuteBulletin(r12, r72, r63, r13).show();
        return;
    L9:
        r63 = 0;
        goto L10
    L6:
        r72 = 4;
        goto L7
    }

    public static /* synthetic */ void $r8$lambda$GL1jLmp7VwzdQMBdqoGTjmbUW1A(ChatNotificationsPopupWrapper r0, Context r1, Theme.ResourcesProvider r2, int r3, Callback r4, View r5) {
        r0.lambda$new$6(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$HpI9IfjCPRMZ9OgU4wbbr7DuRbU(ChatNotificationsPopupWrapper r0, Callback r1, View r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$LQlRXeN2bibA12lU_SKdik6SC0o(ChatNotificationsPopupWrapper r0, Callback r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$MmGBCcUrgMl_GNh7mfwWJe51zlU(Callback r0) {
        r0.toggleMute();
    }

    /* renamed from: $r8$lambda$MqndO9hCbUqnGJIc-B7f7LZp_vk, reason: not valid java name */
    public static /* synthetic */ void m9949$r8$lambda$MqndO9hCbUqnGJIcB7f7LZp_vk(ChatNotificationsPopupWrapper r0, Callback r1, View r2) {
        r0.lambda$new$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$PDt9kyx7Y0qd3G_qIhw_NMiTG_Y(ItemOptions r1, Theme.ResourcesProvider r2, int r3, Utilities.Callback r4) {
        AlertsCreator.createMuteForPickerDialog(r1.getContext(), r2, new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda18(r3, r4));
    }

    /* renamed from: $r8$lambda$W9y4g7Fj60-UPp_Wh5mszARHYMg, reason: not valid java name */
    public static /* synthetic */ void m9950$r8$lambda$W9y4g7Fj60UPp_Wh5mszARHYMg(ChatNotificationsPopupWrapper r0, long r1, long r3, HashSet r5) {
        r0.lambda$update$11(r1, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$_WHFIA39ymnz5txwO8Ws6ecXwFk(ItemOptions r1, long r2, long r4, BaseFragment r6, Theme.ResourcesProvider r7) {
        r1.dismiss();
        Bundle r12 = new Bundle();
        r12.putLong("dialog_id", r2);
        r12.putLong("topic_id", r4);
        r6.presentFragment(new ProfileNotificationsActivity(r12, r7));
    }

    public static /* synthetic */ void $r8$lambda$aS3nDMDQw8wnJZR5VS7dQrqJSc8(ItemOptions r6, int r7, long r8, long r10, BaseFragment r12, Theme.ResourcesProvider r13, Integer r14) {
        r6.dismiss();
        if (r14.intValue() == 0) goto L5;
        NotificationsController.getInstance(r7).muteUntil(r8, r10, r14.intValue());
        if (BulletinFactory.canShowBulletin(r12) == false) goto L16;
        BulletinFactory.createMuteBulletin(r12, 5, r14.intValue(), r13).show();
        return;
    L16:
        return;
    L5:
        if (MessagesController.getInstance(r7).isDialogMuted(r8, r10) == false) goto L8;
        NotificationsController.getInstance(r7).muteDialog(r8, r10, false);
    L8:
        if (BulletinFactory.canShowBulletin(r12) == false) goto L15;
        BulletinFactory.createMuteBulletin(r12, 4, r14.intValue(), r13).show();
        return;
    }

    public static /* synthetic */ void $r8$lambda$fhnqzJooIOZUku0kCFld3YKmf40(ChatNotificationsPopupWrapper r0, Callback r1, View r2) {
        r0.lambda$new$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ftWnAS9G184DyN12n3Oo6rHnzcU(ChatNotificationsPopupWrapper r0, Callback r1, View r2) {
        r0.lambda$new$3(r1, r2);
    }

    /* renamed from: $r8$lambda$jhiS2GRG2Zdjm9uB5Jr-zKUjxx0, reason: not valid java name */
    public static /* synthetic */ void m9951$r8$lambda$jhiS2GRG2Zdjm9uB5JrzKUjxx0(int r2, int r3, Callback r4) {
        if (r2 == 0) goto L4;
        SharedPreferences r32 = MessagesController.getNotificationsSettings(r3);
        int r0 = r32.getInt("last_selected_mute_until_time", 0);
        r32.edit().putInt("last_selected_mute_until_time", r2).putInt("last_selected_mute_until_time2", r0).apply();
    L4:
        r4.muteFor(r2);
    }

    /* renamed from: $r8$lambda$jslqYv3H4CQpj7lso-lTF0FqDk4, reason: not valid java name */
    public static /* synthetic */ void m9952$r8$lambda$jslqYv3H4CQpj7lsolTF0FqDk4(int r2, long r3, long r5, ActionBarMenuSubItem r7, ActionBarMenuSubItem r8) {
        if (MessagesController.getInstance(r2).isDialogMuted(r3, r5) == false) goto L5;
        r7.setTextAndIcon(LocaleController.getString(R.string.UnmuteNotifications), R.drawable.msg_unmute);
        int r22 = Theme.getColor(Theme.key_windowBackgroundWhiteGreenText2);
        r8.setVisibility(8);
    L10:
        r7.setColors(r22, r22);
        r7.setSelectorColor(Theme.multAlpha(r22, 0.1f));
        return;
    L5:
        r7.setTextAndIcon(LocaleController.getString(R.string.MuteNotifications), R.drawable.msg_mute);
        int r0 = Theme.getColor(Theme.key_text_RedBold);
        r8.setVisibility(0);
        if (MessagesController.getInstance(r2).isDialogNotificationsSoundEnabled(r3, r5) == false) goto L8;
        r8.setTextAndIcon(LocaleController.getString(R.string.SoundOff), R.drawable.msg_tone_off);
    L9:
        r22 = r0;
        goto L10
    L8:
        r8.setTextAndIcon(LocaleController.getString(R.string.SoundOn), R.drawable.msg_tone_on);
        goto L9
    }

    public static /* synthetic */ void $r8$lambda$opmPkaODgju1UTHqqmVkZMVRdmg(int r0, Utilities.Callback r1, boolean r2, int r3, int r4) {
        AndroidUtilities.runOnUIThread(new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda19(r3, r0, r1), 16);
    }

    /* renamed from: $r8$lambda$ulLMLuGNd29IMfjHSiZb-A83a0c, reason: not valid java name */
    public static /* synthetic */ void m9953$r8$lambda$ulLMLuGNd29IMfjHSiZbA83a0c(int r0, Callback r1, boolean r2, int r3, int r4) {
        AndroidUtilities.runOnUIThread(new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda11(r3, r0, r1), 16);
    }

    public ChatNotificationsPopupWrapper(Context r12, int r13, PopupSwipeBackLayout r14, boolean r15, boolean r16, Callback r17, Theme.ResourcesProvider r18) {
        this.currentAccount = r13;
        this.callback = r17;
        this.isProfile = r16;
        if (r15 == false) goto L5;
        int r2 = R.drawable.popup_fixed_alert;
    L6:
        AnonymousClass1 r1 = new AnonymousClass1(this, r12, r2, r18);
        this.windowLayout = r1;
        r1.setFitItems(true);
        if (r14 == null) goto L9;
        ActionBarMenuSubItem r19 = ActionBarMenuItem.addItem(this.windowLayout, R.drawable.msg_arrow_back, LocaleController.getString(R.string.Back), false, r18);
        this.backItem = r19;
        r19.setOnClickListener(new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda1(r14));
    L9:
        ActionBarMenuSubItem r0 = ActionBarMenuItem.addItem(this.windowLayout, R.drawable.msg_tone_on, LocaleController.getString(R.string.SoundOn), false, r18);
        this.soundToggle = r0;
        r0.setOnClickListener(new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda2(this, r17));
        ActionBarMenuSubItem r02 = ActionBarMenuItem.addItem(this.windowLayout, R.drawable.msg_mute_1h, LocaleController.getString(R.string.MuteFor1h), false, r18);
        this.muteForLastSelected = r02;
        r02.setOnClickListener(new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda3(this, r17));
        ActionBarMenuSubItem r03 = ActionBarMenuItem.addItem(this.windowLayout, R.drawable.msg_mute_1h, LocaleController.getString(R.string.MuteFor1h), false, r18);
        this.muteForLastSelected2 = r03;
        r03.setOnClickListener(new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda4(this, r17));
        ActionBarMenuItem.addItem(this.windowLayout, R.drawable.msg_mute_period, LocaleController.getString(R.string.MuteForPopup), false, r18).setOnClickListener(new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda5(this, r12, r18, r13, r17));
        ActionBarMenuItem.addItem(this.windowLayout, R.drawable.msg_customize, LocaleController.getString(R.string.NotificationsCustomize), false, r18).setOnClickListener(new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda6(this, r17));
        ActionBarMenuSubItem r132 = ActionBarMenuItem.addItem(this.windowLayout, 0, _UrlKt.FRAGMENT_ENCODE_SET, false, r18);
        this.muteUnmuteButton = r132;
        r132.setOnClickListener(new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda7(this, r17));
        FrameLayout r133 = new FrameLayout(r12);
        this.gap = r133;
        r133.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuSeparator, r18));
        this.windowLayout.addView(r133, LayoutHelper.createLinear(-1, 8));
        TextView r04 = new TextView(r12);
        this.topicsExceptionsTextView = r04;
        r04.setPadding(AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f));
        r04.setTextSize(1, 13.0f);
        r04.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r18));
        r133.setTag(R.id.fit_width_tag, 1);
        r04.setTag(R.id.fit_width_tag, 1);
        this.windowLayout.addView(r04, LayoutHelper.createLinear(-2, -2));
        r04.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_dialogButtonSelector, r18), 0, 6));
        r04.setOnClickListener(new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda8(this, r17));
        return;
    L5:
        r2 = 0;
        goto L6
    }

    public static ItemOptions addAsItemOptions(BaseFragment r13, ItemOptions r14, long r15, long r17) {
        int r2 = r13.getCurrentAccount();
        Theme.ResourcesProvider r8 = r13.getResourceProvider();
        ChatNotificationsPopupWrapper$$ExternalSyntheticLambda12 r0 = new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda12(r14, r2, r15, r17, r13, r8);
        ItemOptions r7 = r14.makeSwipeback();
        r7.add(R.drawable.msg_arrow_back, LocaleController.getString(R.string.Back), new RatePill$$ExternalSyntheticLambda1(r14));
        r7.add(R.drawable.msg_tone_on, LocaleController.getString(R.string.SoundOn), new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda13(r14, r2, r15, r17, r7, r13, r8));
        ActionBarMenuSubItem r11 = r7.getLast();
        r7.add(R.drawable.msg_mute_period, LocaleController.getString(R.string.MuteForPopup), new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda14(r14, r8, r2, r0));
        r7.add(R.drawable.msg_customize, LocaleController.getString(R.string.NotificationsCustomize), new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda15(r14, r15, r17, r13, r8));
        r7.add(0, _UrlKt.FRAGMENT_ENCODE_SET, new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda16(r14, r2, r15, r17, r13, r8));
        new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda17(r2, r15, r17, r7.getLast(), r11).run();
        return r7;
    }

    private void dismiss() {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 == null) goto L5;
        r0.dismiss();
        this.popupWindow.dismiss();
    L5:
        this.callback.dismiss();
        this.lastDismissTime = System.currentTimeMillis();
    }

    private String formatMuteForTime(int r4) {
        StringBuilder r3 = new StringBuilder();
        int r1 = r4 / 86400;
        int r42 = r4 - (86400 * r1);
        int r0 = r42 / 3600;
        int r43 = (r42 - (r0 * 3600)) / 60;
        if (r1 == 0) goto L6;
        r3.append(r1);
        r3.append(LocaleController.getString(R.string.SecretChatTimerDays));
    L6:
        if (r0 != 0) goto L8;
    L11:
        if (r43 == 0) goto L17;
        if (r3.length() <= 0) goto L15;
        r3.append(" ");
    L15:
        r3.append(r43);
        r3.append(LocaleController.getString(R.string.SecretChatTimerMinutes));
    L17:
        return LocaleController.formatString("MuteForButton", R.string.MuteForButton, new Object[]{r3.toString()});
    L8:
        if (r3.length() <= 0) goto L10;
        r3.append(" ");
    L10:
        r3.append(r0);
        r3.append(LocaleController.getString(R.string.SecretChatTimerHours));
        goto L11
    }

    private /* synthetic */ void lambda$new$1(Callback r1, View r2) {
        dismiss();
        r1.toggleSound();
    }

    private /* synthetic */ void lambda$new$10(Callback r1, View r2) {
        if (r1 == null) goto L4;
        r1.openExceptions();
    L4:
        dismiss();
    }

    private /* synthetic */ void lambda$new$2(Callback r1, View r2) {
        dismiss();
        r1.muteFor(this.muteForLastSelected1Time);
    }

    private /* synthetic */ void lambda$new$3(Callback r1, View r2) {
        dismiss();
        r1.muteFor(this.muteForLastSelected2Time);
    }

    private /* synthetic */ void lambda$new$6(Context r1, Theme.ResourcesProvider r2, int r3, Callback r4, View r5) {
        dismiss();
        AlertsCreator.createMuteForPickerDialog(r1, r2, new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda10(r3, r4));
    }

    private /* synthetic */ void lambda$new$7(Callback r1, View r2) {
        dismiss();
        r1.showCustomize();
    }

    private /* synthetic */ void lambda$new$9(Callback r1, View r2) {
        dismiss();
        AndroidUtilities.runOnUIThread(new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda9(r1));
    }

    private /* synthetic */ void lambda$update$11(long r1, long r3, HashSet r5) {
        update(r1, r3, r5);
    }

    public void showAsOptions(BaseFragment r6, View r7, float r8, float r9, boolean r10) {
        if (r6 != null) goto L4;
        return;
    L4:
        if (r6.getFragmentView() == null) goto L22;
        ActionBarPopupWindow r0 = new ActionBarPopupWindow(this.windowLayout, -2, -2);
        this.popupWindow = r0;
        r0.setPauseNotifications(true);
        this.popupWindow.setDismissAnimationDuration(Opcodes.REM_INT_LIT8);
        this.popupWindow.setOutsideTouchable(true);
        this.popupWindow.setClippingEnabled(true);
        this.popupWindow.setAnimationStyle(R.style.PopupContextAnimation);
        this.popupWindow.setFocusable(true);
        this.windowLayout.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
        this.popupWindow.setInputMethodMode(2);
        this.popupWindow.getContentView().setFocusableInTouchMode(true);
    L8:
        if (r7 == r6.getFragmentView()) goto L13;
        if (r7.getParent() == null) goto L23;
        r8 = r8 + r7.getX();
        r9 = r9 + r7.getY();
        r7 = (View) r7.getParent();
        goto L8
    L23:
        return;
    L13:
        if (r10 == false) goto L16;
        float r82 = r8 - AndroidUtilities.dpf2(8.0f);
        float r72 = AndroidUtilities.dpf2(16.0f);
    L17:
        this.popupWindow.showAtLocation(r6.getFragmentView(), 0, (int) r82, (int) (r9 - r72));
        this.popupWindow.dimBehind();
        return;
    L16:
        r82 = r8 - (this.windowLayout.getMeasuredWidth() / 2.0f);
        r72 = this.windowLayout.getMeasuredHeight() / 2.0f;
        goto L17
    }

    public void update(long r9, long r11, HashSet<Integer> r13) {
        if ((System.currentTimeMillis() - this.lastDismissTime) >= 200) goto L6;
        AndroidUtilities.runOnUIThread(new ChatNotificationsPopupWrapper$$ExternalSyntheticLambda0(this, r9, r11, r13));
        return;
    L6:
        boolean r8 = MessagesController.getInstance(this.currentAccount).isDialogMuted(r9, r11);
        ActionBarMenuSubItem r92 = this.muteUnmuteButton;
        if (r8 == false) goto L9;
        r92.setTextAndIcon(LocaleController.getString(R.string.UnmuteNotifications), R.drawable.msg_unmute);
        int r93 = Theme.getColor(Theme.key_windowBackgroundWhiteGreenText2);
        this.soundToggle.setVisibility(8);
    L14:
        if (this.type != 1) goto L16;
        this.backItem.setVisibility(8);
    L16:
        if (r8 == false) goto L18;
    L21:
        int r82 = 0;
        int r12 = 0;
    L22:
        if (r12 == 0) goto L24;
        this.muteForLastSelected1Time = r12;
        this.muteForLastSelected.setVisibility(0);
        this.muteForLastSelected.getImageView().setImageDrawable(TimerDrawable.getTtlIcon(r12));
        this.muteForLastSelected.setText(formatMuteForTime(r12));
    L25:
        if (r82 == 0) goto L27;
        this.muteForLastSelected2Time = r82;
        this.muteForLastSelected2.setVisibility(0);
        this.muteForLastSelected2.getImageView().setImageDrawable(TimerDrawable.getTtlIcon(r82));
        this.muteForLastSelected2.setText(formatMuteForTime(r82));
    L28:
        this.muteUnmuteButton.setColors(r93, r93);
        this.muteUnmuteButton.setSelectorColor(Theme.multAlpha(r93, 0.1f));
        if (r13 != null) goto L31;
    L35:
        this.gap.setVisibility(8);
        this.topicsExceptionsTextView.setVisibility(8);
        return;
    L31:
        if (r13.isEmpty() == true) goto L35;
        this.gap.setVisibility(0);
        this.topicsExceptionsTextView.setVisibility(0);
        this.topicsExceptionsTextView.setText(AndroidUtilities.replaceSingleTag(LocaleController.formatPluralString("TopicNotificationsExceptions", r13.size(), new Object[0]), Theme.key_windowBackgroundWhiteBlueText, 1, null));
        return;
    L27:
        this.muteForLastSelected2.setVisibility(8);
        goto L28
    L24:
        this.muteForLastSelected.setVisibility(8);
        goto L25
    L18:
        if (this.type == 1) goto L21;
        SharedPreferences r83 = MessagesController.getNotificationsSettings(this.currentAccount);
        r12 = r83.getInt("last_selected_mute_until_time", 0);
        r82 = r83.getInt("last_selected_mute_until_time2", 0);
        goto L22
    L9:
        r92.setTextAndIcon(LocaleController.getString(R.string.MuteNotifications), R.drawable.msg_mute);
        r93 = Theme.getColor(Theme.key_text_RedBold);
        this.soundToggle.setVisibility(0);
        boolean r122 = MessagesController.getInstance(this.currentAccount).isDialogNotificationsSoundEnabled(r9, r11);
        ActionBarMenuSubItem r132 = this.soundToggle;
        if (r122 == false) goto L12;
        r132.setTextAndIcon(LocaleController.getString(R.string.SoundOff), R.drawable.msg_tone_off);
        goto L14
    L12:
        r132.setTextAndIcon(LocaleController.getString(R.string.SoundOn), R.drawable.msg_tone_on);
        goto L14
    }
}
