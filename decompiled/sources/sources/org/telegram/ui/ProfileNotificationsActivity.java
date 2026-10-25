package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.NotificationsSettingsFacade;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.RadioCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCheckBoxCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextColorCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Cells.UserCell2;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.ChatAvatarContainer;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.NotificationsSettingsActivity;

/* loaded from: classes6.dex */
public class ProfileNotificationsActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private ListAdapter adapter;
    private boolean addingException;
    private AnimatorSet animatorSet;
    ChatAvatarContainer avatarContainer;
    private int avatarRow;
    private int avatarSectionRow;
    private int callsRow;
    private int callsVibrateRow;
    private int colorRow;
    private int customResetRow;
    private int customResetShadowRow;
    private ProfileNotificationsActivityDelegate delegate;
    private long dialogId;
    private int enableRow;
    private int generalRow;
    private boolean isInTop5Peers;
    private int ledInfoRow;
    private int ledRow;
    private RecyclerListView listView;
    private boolean needReset;
    private boolean notificationsEnabled;
    private int popupDisabledRow;
    private int popupEnabledRow;
    private int popupInfoRow;
    private int popupRow;
    private int previewRow;
    private int priorityInfoRow;
    private int priorityRow;
    private Theme.ResourcesProvider resourcesProvider;
    private int ringtoneInfoRow;
    private int ringtoneRow;
    private int rowCount;
    private int smartRow;
    private int soundRow;
    private int storiesRow;
    private long topicId;
    private int vibrateRow;

    /* renamed from: org.telegram.ui.ProfileNotificationsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ProfileNotificationsActivity this$0;
        final /* synthetic */ String val$key;

        public AnonymousClass1(ProfileNotificationsActivity r1, String r2) {
            this.this$0 = r1;
            this.val$key = r2;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r12) {
            if (r12 != (-1)) goto L10;
            if (ProfileNotificationsActivity.m18300$$Nest$fgetaddingException(this.this$0) == false) goto L7;
        L29:
            this.this$0.finishFragment();
            return;
        L7:
            if (ProfileNotificationsActivity.m18316$$Nest$fgetnotificationsEnabled(this.this$0) == false) goto L29;
            MessagesController.getNotificationsSettings(ProfileNotificationsActivity.access$000(this.this$0)).edit().putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + this.val$key, 0).apply();
            goto L29
        L10:
            if (r12 != 1) goto L29;
            SharedPreferences r122 = MessagesController.getNotificationsSettings(ProfileNotificationsActivity.access$100(this.this$0));
            SharedPreferences.Editor r3 = r122.edit();
            r3.putBoolean(NotificationsSettingsFacade.PROPERTY_CUSTOM + this.val$key, true);
            TLRPC.Dialog r4 = MessagesController.getInstance(ProfileNotificationsActivity.access$200(this.this$0)).dialogs_dict.get(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0));
            boolean r5 = ProfileNotificationsActivity.m18316$$Nest$fgetnotificationsEnabled(this.this$0);
            String r6 = this.val$key;
            if (r5 == false) goto L18;
            r3.putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + r6, 0);
            if (ProfileNotificationsActivity.m18331$$Nest$fgettopicId(this.this$0) != 0) goto L23;
            MessagesStorage.getInstance(ProfileNotificationsActivity.access$300(this.this$0)).setDialogFlags(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0), 0);
            if (r4 == null) goto L23;
            r4.notify_settings = new TLRPC.TL_peerNotifySettings();
        L23:
            r3.apply();
            NotificationsController.getInstance(ProfileNotificationsActivity.access$600(this.this$0)).updateServerNotificationsSettings(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0), ProfileNotificationsActivity.m18331$$Nest$fgettopicId(this.this$0));
            if (ProfileNotificationsActivity.m18309$$Nest$fgetdelegate(this.this$0) == null) goto L29;
            NotificationsSettingsActivity.NotificationException r32 = new NotificationsSettingsActivity.NotificationException();
            r32.did = ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0);
            r32.hasCustom = true;
            int r0 = r122.getInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + this.val$key, 0);
            r32.notify = r0;
            if (r0 == 0) goto L28;
            r32.muteUntil = r122.getInt(NotificationsSettingsFacade.PROPERTY_NOTIFY_UNTIL + this.val$key, 0);
        L28:
            ProfileNotificationsActivity.m18309$$Nest$fgetdelegate(this.this$0).didCreateNewException(r32);
            goto L29
        L18:
            r3.putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + r6, 2);
            if (ProfileNotificationsActivity.m18331$$Nest$fgettopicId(this.this$0) != 0) goto L23;
            NotificationsController.getInstance(ProfileNotificationsActivity.access$400(this.this$0)).removeNotificationsForDialog(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0));
            MessagesStorage.getInstance(ProfileNotificationsActivity.access$500(this.this$0)).setDialogFlags(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0), 1);
            if (r4 == null) goto L23;
            TLRPC.TL_peerNotifySettings r52 = new TLRPC.TL_peerNotifySettings();
            r4.notify_settings = r52;
            r52.mute_until = Integer.MAX_VALUE;
            goto L23
        }
    }

    /* renamed from: org.telegram.ui.ProfileNotificationsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayoutManager {
        final /* synthetic */ ProfileNotificationsActivity this$0;

        public AnonymousClass2(ProfileNotificationsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.ProfileNotificationsActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ ProfileNotificationsActivity this$0;

        public AnonymousClass3(ProfileNotificationsActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(ProfileNotificationsActivity.m18301$$Nest$fgetanimatorSet(this.this$0)) == false) goto L6;
            ProfileNotificationsActivity.m18333$$Nest$fputanimatorSet(this.this$0, null);
            return;
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context context;
        final /* synthetic */ ProfileNotificationsActivity this$0;

        public ListAdapter(ProfileNotificationsActivity r1, Context r2) {
            this.this$0 = r1;
            this.context = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return ProfileNotificationsActivity.m18327$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != ProfileNotificationsActivity.m18312$$Nest$fgetgeneralRow(this.this$0)) goto L5;
        L71:
            return 0;
        L5:
            if (r3 == ProfileNotificationsActivity.m18320$$Nest$fgetpopupRow(this.this$0)) goto L71;
            if (r3 == ProfileNotificationsActivity.m18315$$Nest$fgetledRow(this.this$0)) goto L71;
            if (r3 == ProfileNotificationsActivity.m18304$$Nest$fgetcallsRow(this.this$0)) goto L71;
            if (r3 != ProfileNotificationsActivity.m18329$$Nest$fgetsoundRow(this.this$0)) goto L14;
            return 1;
        L14:
            if (r3 != ProfileNotificationsActivity.m18332$$Nest$fgetvibrateRow(this.this$0)) goto L16;
            return 1;
        L16:
            if (r3 != ProfileNotificationsActivity.m18323$$Nest$fgetpriorityRow(this.this$0)) goto L18;
            return 1;
        L18:
            if (r3 != ProfileNotificationsActivity.m18328$$Nest$fgetsmartRow(this.this$0)) goto L20;
            return 1;
        L20:
            if (r3 != ProfileNotificationsActivity.m18326$$Nest$fgetringtoneRow(this.this$0)) goto L22;
            return 1;
        L22:
            if (r3 != ProfileNotificationsActivity.m18305$$Nest$fgetcallsVibrateRow(this.this$0)) goto L24;
            return 1;
        L24:
            if (r3 != ProfileNotificationsActivity.m18307$$Nest$fgetcustomResetRow(this.this$0)) goto L27;
            return 1;
        L27:
            if (r3 != ProfileNotificationsActivity.m18319$$Nest$fgetpopupInfoRow(this.this$0)) goto L29;
            return 2;
        L29:
            if (r3 != ProfileNotificationsActivity.m18314$$Nest$fgetledInfoRow(this.this$0)) goto L31;
            return 2;
        L31:
            if (r3 != ProfileNotificationsActivity.m18322$$Nest$fgetpriorityInfoRow(this.this$0)) goto L33;
            return 2;
        L33:
            if (r3 != ProfileNotificationsActivity.m18325$$Nest$fgetringtoneInfoRow(this.this$0)) goto L36;
            return 2;
        L36:
            if (r3 != ProfileNotificationsActivity.m18306$$Nest$fgetcolorRow(this.this$0)) goto L40;
            return 3;
        L40:
            if (r3 != ProfileNotificationsActivity.m18318$$Nest$fgetpopupEnabledRow(this.this$0)) goto L42;
            return 4;
        L42:
            if (r3 != ProfileNotificationsActivity.m18317$$Nest$fgetpopupDisabledRow(this.this$0)) goto L45;
            return 4;
        L45:
            if (r3 != ProfileNotificationsActivity.m18302$$Nest$fgetavatarRow(this.this$0)) goto L49;
            return 5;
        L49:
            if (r3 != ProfileNotificationsActivity.m18303$$Nest$fgetavatarSectionRow(this.this$0)) goto L51;
            return 6;
        L51:
            if (r3 != ProfileNotificationsActivity.m18308$$Nest$fgetcustomResetShadowRow(this.this$0)) goto L54;
            return 6;
        L54:
            if (r3 != ProfileNotificationsActivity.m18311$$Nest$fgetenableRow(this.this$0)) goto L56;
            return 7;
        L56:
            if (r3 != ProfileNotificationsActivity.m18321$$Nest$fgetpreviewRow(this.this$0)) goto L58;
            return 7;
        L58:
            if (r3 == ProfileNotificationsActivity.m18330$$Nest$fgetstoriesRow(this.this$0)) goto L73;
            return 0;
        L73:
            return 7;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r4) {
            if (r4.getAdapterPosition() != ProfileNotificationsActivity.m18321$$Nest$fgetpreviewRow(this.this$0)) goto L7;
            return ProfileNotificationsActivity.m18316$$Nest$fgetnotificationsEnabled(this.this$0);
        L7:
            if (r4.getAdapterPosition() != ProfileNotificationsActivity.m18307$$Nest$fgetcustomResetRow(this.this$0)) goto L10;
            return true;
        L10:
            switch(r4.getItemViewType()) {
                case 0: goto L14;
                case 1: goto L13;
                case 2: goto L14;
                case 3: goto L13;
                case 4: goto L13;
                case 5: goto L14;
                case 6: goto L14;
                default: goto L11;
            };
        L11:
            return true;
        L14:
            return false;
        L13:
            return ProfileNotificationsActivity.m18316$$Nest$fgetnotificationsEnabled(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
            boolean r3 = false;
            boolean r4 = true;
            switch(r10.getItemViewType()) {
                case 0: goto L217;
                case 1: goto L100;
                case 2: goto L80;
                case 3: goto L64;
                case 4: goto L39;
                case 5: goto L33;
                case 6: goto L24;
                case 7: goto L5;
                default: goto L243;
            };
        L5:
            TextCheckCell r102 = (TextCheckCell) r10.itemView;
            SharedPreferences r0 = MessagesController.getNotificationsSettings(ProfileNotificationsActivity.access$1200(this.this$0));
            if (r11 != ProfileNotificationsActivity.m18311$$Nest$fgetenableRow(this.this$0)) goto L9;
            r102.setTextAndCheck(LocaleController.getString(R.string.Notifications), ProfileNotificationsActivity.m18316$$Nest$fgetnotificationsEnabled(this.this$0), true);
            return;
        L9:
            int r1 = ProfileNotificationsActivity.m18321$$Nest$fgetpreviewRow(this.this$0);
            ProfileNotificationsActivity r2 = this.this$0;
            if (r11 != r1) goto L14;
            String r9 = NotificationsController.getSharedPrefKey(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(r2), ProfileNotificationsActivity.m18331$$Nest$fgettopicId(this.this$0));
            r102.setTextAndCheck(LocaleController.getString(R.string.MessagePreview), r0.getBoolean(NotificationsSettingsFacade.PROPERTY_CONTENT_PREVIEW + r9, true), true);
            return;
        L14:
            if (r11 != ProfileNotificationsActivity.m18330$$Nest$fgetstoriesRow(r2)) goto L235;
            String r112 = NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + NotificationsController.getSharedPrefKey(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0), ProfileNotificationsActivity.m18331$$Nest$fgettopicId(this.this$0));
            if (ProfileNotificationsActivity.m18313$$Nest$fgetisInTop5Peers(this.this$0) == false) goto L18;
        L21:
            r3 = true;
        L22:
            r102.setTextAndCheck(LocaleController.getString(R.string.StoriesSoundEnabled), r0.getBoolean(r112, r3), true);
            return;
        L18:
            if (r0.contains("EnableAllStories") == false) goto L22;
            if (r0.getBoolean("EnableAllStories", true) == false) goto L22;
        L235:
            return;
        L24:
            ShadowSectionCell r103 = (ShadowSectionCell) r10.itemView;
            if (r11 <= 0) goto L27;
            boolean r02 = true;
        L29:
            if (r11 >= (getItemCount() - 1)) goto L31;
            r3 = true;
        L31:
            r103.setTopBottom(r02, r3);
            return;
        L27:
            r02 = false;
            goto L29
        L33:
            UserCell2 r104 = (UserCell2) r10.itemView;
            boolean r113 = DialogObject.isUserDialog(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0));
            ProfileNotificationsActivity r03 = this.this$0;
            if (r113 == false) goto L36;
            TLObject r92 = MessagesController.getInstance(ProfileNotificationsActivity.access$1000(r03)).getUser(Long.valueOf(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0)));
        L37:
            r104.setData(r92, null, null, 0);
            return;
        L36:
            r92 = MessagesController.getInstance(ProfileNotificationsActivity.access$1100(r03)).getChat(Long.valueOf(-ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0)));
            goto L37
        L39:
            RadioCell r105 = (RadioCell) r10.itemView;
            SharedPreferences r04 = MessagesController.getNotificationsSettings(ProfileNotificationsActivity.access$900(this.this$0));
            int r22 = r04.getInt("popup_" + NotificationsController.getSharedPrefKey(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0), ProfileNotificationsActivity.m18331$$Nest$fgettopicId(this.this$0)), 0);
            if (r22 != 0) goto L50;
            if (DialogObject.isChatDialog(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0)) == false) goto L44;
            String r23 = "popupGroup";
        L46:
            if (r04.getInt(r23, 0) == 0) goto L48;
            r22 = 1;
            goto L50
        L48:
            r22 = 2;
            goto L50
        L44:
            r23 = "popupAll";
        L50:
            if (r11 != ProfileNotificationsActivity.m18318$$Nest$fgetpopupEnabledRow(this.this$0)) goto L57;
            String r93 = LocaleController.getString(R.string.PopupEnabled);
            if (r22 != 1) goto L54;
            r3 = true;
        L54:
            r105.setText(r93, r3, true);
            r105.setTag(1);
            return;
        L57:
            if (r11 != ProfileNotificationsActivity.m18317$$Nest$fgetpopupDisabledRow(this.this$0)) goto L236;
            String r94 = LocaleController.getString(R.string.PopupDisabled);
            if (r22 == 2) goto L62;
            r4 = false;
        L62:
            r105.setText(r94, r4, false);
            r105.setTag(2);
            return;
        L236:
            return;
        L64:
            TextColorCell r106 = (TextColorCell) r10.itemView;
            String r114 = NotificationsController.getSharedPrefKey(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0), ProfileNotificationsActivity.m18331$$Nest$fgettopicId(this.this$0));
            SharedPreferences r05 = MessagesController.getNotificationsSettings(ProfileNotificationsActivity.access$800(this.this$0));
            if (r05.contains("color_" + r114) == false) goto L68;
            int r95 = r05.getInt("color_" + r114, -16776961);
        L71:
            int r115 = 0;
        L73:
            if (r115 >= 9) goto L78;
            if (TextColorCell.colorsToSave[r115] == r95) goto L76;
            r115 = r115 + 1;
            goto L73
        L76:
            r95 = TextColorCell.colors[r115];
        L78:
            r106.setTextAndColor(LocaleController.getString(R.string.NotificationsLedColor), r95, false);
            return;
        L68:
            if (DialogObject.isChatDialog(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0)) == false) goto L70;
            r95 = r05.getInt("GroupLed", -16776961);
            goto L71
        L70:
            r95 = r05.getInt("MessagesLed", -16776961);
            goto L71
        L80:
            TextInfoPrivacyCell r107 = (TextInfoPrivacyCell) r10.itemView;
            r107.setFixedSize(0);
            if (r11 != ProfileNotificationsActivity.m18319$$Nest$fgetpopupInfoRow(this.this$0)) goto L85;
            r107.setText(LocaleController.getString(R.string.ProfilePopupNotificationInfo));
            return;
        L85:
            if (r11 != ProfileNotificationsActivity.m18314$$Nest$fgetledInfoRow(this.this$0)) goto L88;
            r107.setText(LocaleController.getString(R.string.NotificationsLedInfo));
            return;
        L88:
            int r06 = ProfileNotificationsActivity.m18322$$Nest$fgetpriorityInfoRow(this.this$0);
            ProfileNotificationsActivity r96 = this.this$0;
            if (r11 != r06) goto L97;
            if (ProfileNotificationsActivity.m18323$$Nest$fgetpriorityRow(r96) != (-1)) goto L94;
            r107.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L94:
            r107.setText(LocaleController.getString(R.string.PriorityInfo));
            return;
        L97:
            if (r11 != ProfileNotificationsActivity.m18325$$Nest$fgetringtoneInfoRow(r96)) goto L237;
            r107.setText(LocaleController.getString(R.string.VoipRingtoneInfo));
            return;
        L237:
            return;
        L100:
            TextSettingsCell r108 = (TextSettingsCell) r10.itemView;
            String r07 = NotificationsController.getSharedPrefKey(ProfileNotificationsActivity.m18310$$Nest$fgetdialogId(this.this$0), ProfileNotificationsActivity.m18331$$Nest$fgettopicId(this.this$0));
            SharedPreferences r5 = MessagesController.getNotificationsSettings(ProfileNotificationsActivity.access$700(this.this$0));
            if (r11 != ProfileNotificationsActivity.m18307$$Nest$fgetcustomResetRow(this.this$0)) goto L104;
            r108.setText(LocaleController.getString(R.string.ResetCustomNotifications), false);
            r108.setTextColor(this.this$0.getThemedColor(Theme.key_text_RedBold));
            return;
        L104:
            r108.setTextColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
            if (r11 != ProfileNotificationsActivity.m18329$$Nest$fgetsoundRow(this.this$0)) goto L122;
            String r116 = r5.getString("sound_" + r07, LocaleController.getString(R.string.SoundDefault));
            long r52 = r5.getLong("sound_document_id_" + r07, 0);
            if (r52 == 0) goto L114;
            TLRPC.Document r97 = this.this$0.getMediaDataController().ringtoneDataStore.getDocument(r52);
            if (r97 != null) goto L112;
            String r98 = LocaleController.getString(R.string.CustomSound);
        L111:
            r116 = r98;
        L119:
            r108.setTextAndValue(LocaleController.getString(R.string.Sound), r116, true);
            return;
        L112:
            r98 = NotificationsSoundActivity.trimTitle(r97, r97.file_name_fixed);
            goto L111
        L114:
            if (r116.equals("NoSound") == false) goto L117;
            r116 = LocaleController.getString(R.string.NoSound);
            goto L119
        L117:
            if (r116.equals("Default") == false) goto L119;
            r116 = LocaleController.getString(R.string.SoundDefault);
            goto L119
        L122:
            if (r11 != ProfileNotificationsActivity.m18326$$Nest$fgetringtoneRow(this.this$0)) goto L129;
            String r99 = r5.getString("ringtone_" + r07, LocaleController.getString(R.string.DefaultRingtone));
            if (r99.equals("NoSound") == false) goto L126;
            r99 = LocaleController.getString(R.string.NoSound);
        L126:
            r108.setTextAndValue(LocaleController.getString(R.string.VoipSettingsRingtone), r99, false);
            return;
        L129:
            if (r11 != ProfileNotificationsActivity.m18332$$Nest$fgetvibrateRow(this.this$0)) goto L166;
            int r117 = r5.getInt("vibrate_" + r07, 0);
            if (r117 == 0) goto L158;
            if (r117 == 4) goto L158;
            if (r117 != 1) goto L142;
            String r118 = LocaleController.getString(R.string.Vibrate);
            String r08 = LocaleController.getString(R.string.Short);
            if (ProfileNotificationsActivity.m18328$$Nest$fgetsmartRow(this.this$0) == (-1)) goto L138;
        L139:
            r3 = true;
        L140:
            r108.setTextAndValue(r118, r08, r3);
            return;
        L138:
            if (ProfileNotificationsActivity.m18323$$Nest$fgetpriorityRow(this.this$0) == (-1)) goto L140;
        L142:
            if (r117 != 2) goto L150;
            String r119 = LocaleController.getString(R.string.Vibrate);
            String r09 = LocaleController.getString(R.string.VibrationDisabled);
            if (ProfileNotificationsActivity.m18328$$Nest$fgetsmartRow(this.this$0) == (-1)) goto L146;
        L147:
            r3 = true;
        L148:
            r108.setTextAndValue(r119, r09, r3);
            return;
        L146:
            if (ProfileNotificationsActivity.m18323$$Nest$fgetpriorityRow(this.this$0) == (-1)) goto L148;
        L150:
            if (r117 != 3) goto L238;
            String r1110 = LocaleController.getString(R.string.Vibrate);
            String r010 = LocaleController.getString(R.string.Long);
            if (ProfileNotificationsActivity.m18328$$Nest$fgetsmartRow(this.this$0) == (-1)) goto L154;
        L155:
            r3 = true;
        L156:
            r108.setTextAndValue(r1110, r010, r3);
            return;
        L154:
            if (ProfileNotificationsActivity.m18323$$Nest$fgetpriorityRow(this.this$0) == (-1)) goto L156;
        L238:
            return;
        L158:
            String r1111 = LocaleController.getString(R.string.Vibrate);
            String r011 = LocaleController.getString(R.string.VibrationDefault);
            if (ProfileNotificationsActivity.m18328$$Nest$fgetsmartRow(this.this$0) == (-1)) goto L161;
        L162:
            r3 = true;
        L163:
            r108.setTextAndValue(r1111, r011, r3);
            return;
        L161:
            if (ProfileNotificationsActivity.m18323$$Nest$fgetpriorityRow(this.this$0) == (-1)) goto L163;
        L166:
            if (r11 != ProfileNotificationsActivity.m18323$$Nest$fgetpriorityRow(this.this$0)) goto L187;
            int r910 = r5.getInt("priority_" + r07, 3);
            if (r910 != 0) goto L171;
            r108.setTextAndValue(LocaleController.getString(R.string.NotificationsImportance), LocaleController.getString(R.string.NotificationsPriorityHigh), false);
            return;
        L171:
            if (r910 == 1) goto L184;
            if (r910 == 2) goto L184;
            if (r910 != 3) goto L177;
            r108.setTextAndValue(LocaleController.getString(R.string.NotificationsImportance), LocaleController.getString(R.string.NotificationsPrioritySettings), false);
            return;
        L177:
            if (r910 != 4) goto L181;
            r108.setTextAndValue(LocaleController.getString(R.string.NotificationsImportance), LocaleController.getString(R.string.NotificationsPriorityLow), false);
            return;
        L181:
            if (r910 != 5) goto L239;
            r108.setTextAndValue(LocaleController.getString(R.string.NotificationsImportance), LocaleController.getString(R.string.NotificationsPriorityMedium), false);
            return;
        L239:
            return;
        L184:
            r108.setTextAndValue(LocaleController.getString(R.string.NotificationsImportance), LocaleController.getString(R.string.NotificationsPriorityUrgent), false);
            return;
        L187:
            if (r11 != ProfileNotificationsActivity.m18328$$Nest$fgetsmartRow(this.this$0)) goto L201;
            int r1112 = r5.getInt("smart_max_count_" + r07, 2);
            int r012 = r5.getInt("smart_delay_" + r07, 180);
            if (r1112 != 0) goto L195;
            String r1113 = LocaleController.getString(R.string.SmartNotifications);
            String r013 = LocaleController.getString(R.string.SmartNotificationsDisabled);
            if (ProfileNotificationsActivity.m18323$$Nest$fgetpriorityRow(this.this$0) == (-1)) goto L193;
            r3 = true;
        L193:
            r108.setTextAndValue(r1113, r013, r3);
            return;
        L195:
            String r014 = LocaleController.formatPluralString("Minutes", r012 / 60, new Object[0]);
            String r12 = LocaleController.getString(R.string.SmartNotifications);
            String r1114 = LocaleController.formatString("SmartNotificationsInfo", R.string.SmartNotificationsInfo, new Object[]{Integer.valueOf(r1112), r014});
            if (ProfileNotificationsActivity.m18323$$Nest$fgetpriorityRow(this.this$0) == (-1)) goto L198;
            r3 = true;
        L198:
            r108.setTextAndValue(r12, r1114, r3);
            return;
        L201:
            if (r11 != ProfileNotificationsActivity.m18305$$Nest$fgetcallsVibrateRow(this.this$0)) goto L240;
            int r911 = r5.getInt("calls_vibrate_" + r07, 0);
            if (r911 == 0) goto L215;
            if (r911 == 4) goto L215;
            if (r911 != 1) goto L209;
            r108.setTextAndValue(LocaleController.getString(R.string.Vibrate), LocaleController.getString(R.string.Short), true);
            return;
        L209:
            if (r911 != 2) goto L212;
            r108.setTextAndValue(LocaleController.getString(R.string.Vibrate), LocaleController.getString(R.string.VibrationDisabled), true);
            return;
        L212:
            if (r911 != 3) goto L241;
            r108.setTextAndValue(LocaleController.getString(R.string.Vibrate), LocaleController.getString(R.string.Long), true);
            return;
        L241:
            return;
        L215:
            r108.setTextAndValue(LocaleController.getString(R.string.Vibrate), LocaleController.getString(R.string.VibrationDefault), true);
            return;
        L240:
            return;
        L217:
            HeaderCell r109 = (HeaderCell) r10.itemView;
            if (r11 != ProfileNotificationsActivity.m18312$$Nest$fgetgeneralRow(this.this$0)) goto L222;
            r109.setText(LocaleController.getString(R.string.General));
            return;
        L222:
            if (r11 != ProfileNotificationsActivity.m18320$$Nest$fgetpopupRow(this.this$0)) goto L226;
            r109.setText(LocaleController.getString(R.string.ProfilePopupNotification));
            return;
        L226:
            if (r11 != ProfileNotificationsActivity.m18315$$Nest$fgetledRow(this.this$0)) goto L230;
            r109.setText(LocaleController.getString(R.string.NotificationsLed));
            return;
        L230:
            if (r11 != ProfileNotificationsActivity.m18304$$Nest$fgetcallsRow(this.this$0)) goto L242;
            r109.setText(LocaleController.getString(R.string.VoipNotificationSettings));
            return;
        L242:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r4, int r5) {
            switch(r5) {
                case 0: goto L10;
                case 1: goto L9;
                case 2: goto L8;
                case 3: goto L7;
                case 4: goto L6;
                case 5: goto L5;
                case 6: goto L4;
                default: goto L3;
            };
        L3:
            View r42 = new TextCheckCell(this.context, ProfileNotificationsActivity.m18324$$Nest$fgetresourcesProvider(this.this$0));
            r42.setBackgroundColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
        L11:
            r42.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r42);
        L4:
            r42 = new ShadowSectionCell(this.context, ProfileNotificationsActivity.m18324$$Nest$fgetresourcesProvider(this.this$0));
            goto L11
        L5:
            r42 = new UserCell2(this.context, 4, 0, ProfileNotificationsActivity.m18324$$Nest$fgetresourcesProvider(this.this$0));
            r42.setBackgroundColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            goto L11
        L6:
            r42 = new RadioCell(this.context, ProfileNotificationsActivity.m18324$$Nest$fgetresourcesProvider(this.this$0));
            r42.setBackgroundColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            goto L11
        L7:
            r42 = new TextColorCell(this.context, ProfileNotificationsActivity.m18324$$Nest$fgetresourcesProvider(this.this$0));
            r42.setBackgroundColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            goto L11
        L8:
            r42 = new TextInfoPrivacyCell(this.context, ProfileNotificationsActivity.m18324$$Nest$fgetresourcesProvider(this.this$0));
            goto L11
        L9:
            r42 = new TextSettingsCell(this.context, ProfileNotificationsActivity.m18324$$Nest$fgetresourcesProvider(this.this$0));
            r42.setBackgroundColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            goto L11
        L10:
            r42 = new HeaderCell(this.context, ProfileNotificationsActivity.m18324$$Nest$fgetresourcesProvider(this.this$0));
            r42.setBackgroundColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            goto L11
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r6) {
            int r0 = r6.getItemViewType();
            if (r0 != 0) goto L5;
            ((HeaderCell) r6.itemView).setEnabled(ProfileNotificationsActivity.m18316$$Nest$fgetnotificationsEnabled(this.this$0), null);
            return;
        L5:
            if (r0 != 1) goto L7;
            TextSettingsCell r02 = (TextSettingsCell) r6.itemView;
            if (r6.getAdapterPosition() != ProfileNotificationsActivity.m18307$$Nest$fgetcustomResetRow(this.this$0)) goto L35;
            r02.setEnabled(true, null);
            return;
        L35:
            r02.setEnabled(ProfileNotificationsActivity.m18316$$Nest$fgetnotificationsEnabled(this.this$0), null);
            return;
        L7:
            if (r0 != 2) goto L9;
            ((TextInfoPrivacyCell) r6.itemView).setEnabled(ProfileNotificationsActivity.m18316$$Nest$fgetnotificationsEnabled(this.this$0), null);
            return;
        L9:
            if (r0 != 3) goto L11;
            ((TextColorCell) r6.itemView).setEnabled(ProfileNotificationsActivity.m18316$$Nest$fgetnotificationsEnabled(this.this$0), null);
            return;
        L11:
            if (r0 != 4) goto L13;
            ((RadioCell) r6.itemView).setEnabled(ProfileNotificationsActivity.m18316$$Nest$fgetnotificationsEnabled(this.this$0), null);
            return;
        L13:
            if (r0 == 7) goto L15;
            return;
        L15:
            TextCheckCell r03 = (TextCheckCell) r6.itemView;
            if (r6.getAdapterPosition() != ProfileNotificationsActivity.m18321$$Nest$fgetpreviewRow(this.this$0)) goto L20;
            r03.setEnabled(ProfileNotificationsActivity.m18316$$Nest$fgetnotificationsEnabled(this.this$0), null);
            return;
        L20:
            if (r6.getAdapterPosition() != ProfileNotificationsActivity.m18330$$Nest$fgetstoriesRow(this.this$0)) goto L23;
            r03.setEnabled(ProfileNotificationsActivity.m18316$$Nest$fgetnotificationsEnabled(this.this$0), null);
            return;
        L23:
            r03.setEnabled(true, null);
        }
    }

    public interface ProfileNotificationsActivityDelegate {
        void didCreateNewException(NotificationsSettingsActivity.NotificationException r1);

        default void didRemoveException(long r1) {
        }
    }

    /* renamed from: $r8$lambda$3aWiIA2vX9rZY4hi4v-L73xuOZc, reason: not valid java name */
    public static /* synthetic */ void m18298$r8$lambda$3aWiIA2vX9rZY4hi4vL73xuOZc(ProfileNotificationsActivity r0) {
        r0.lambda$getThemeDescriptions$7();
    }

    /* renamed from: $r8$lambda$64U6cIAXNmbVx6RFt-c2KartqK8, reason: not valid java name */
    public static /* synthetic */ void m18299$r8$lambda$64U6cIAXNmbVx6RFtc2KartqK8(ProfileNotificationsActivity r0) {
        r0.lambda$createView$5();
    }

    public static /* synthetic */ void $r8$lambda$7aM88OPdIsvK02y9MeHOwDZBli8(ProfileNotificationsActivity r0, String r1, AlertDialog r2, int r3) {
        r0.lambda$createView$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$QLQgCbZ7wsa7op2BRWD3ucFoVh8(ProfileNotificationsActivity r0, Context r1, String r2, View r3, int r4) {
        r0.lambda$createView$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$YwP5uk0IbigjU78u1rjsZMj5z6c(ProfileNotificationsActivity r0) {
        r0.lambda$createView$3();
    }

    public static /* synthetic */ void $r8$lambda$aAhwPvus3AOt3PNWF86zhBbkbk8(ProfileNotificationsActivity r0, String r1, int r2, int r3) {
        r0.lambda$createView$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$wTyVAKfMrXOHCJETHlZ7PzD6xuE(ProfileNotificationsActivity r0) {
        r0.lambda$createView$1();
    }

    public static /* synthetic */ void $r8$lambda$zMBxO9sY_SuCodoG6_Vvq4yfaD4(ProfileNotificationsActivity r0) {
        r0.lambda$createView$2();
    }

    /* renamed from: -$$Nest$fgetaddingException, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18300$$Nest$fgetaddingException(ProfileNotificationsActivity r0) {
        return r0.addingException;
    }

    /* renamed from: -$$Nest$fgetanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m18301$$Nest$fgetanimatorSet(ProfileNotificationsActivity r0) {
        return r0.animatorSet;
    }

    /* renamed from: -$$Nest$fgetavatarRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18302$$Nest$fgetavatarRow(ProfileNotificationsActivity r0) {
        return r0.avatarRow;
    }

    /* renamed from: -$$Nest$fgetavatarSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18303$$Nest$fgetavatarSectionRow(ProfileNotificationsActivity r0) {
        return r0.avatarSectionRow;
    }

    /* renamed from: -$$Nest$fgetcallsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18304$$Nest$fgetcallsRow(ProfileNotificationsActivity r0) {
        return r0.callsRow;
    }

    /* renamed from: -$$Nest$fgetcallsVibrateRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18305$$Nest$fgetcallsVibrateRow(ProfileNotificationsActivity r0) {
        return r0.callsVibrateRow;
    }

    /* renamed from: -$$Nest$fgetcolorRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18306$$Nest$fgetcolorRow(ProfileNotificationsActivity r0) {
        return r0.colorRow;
    }

    /* renamed from: -$$Nest$fgetcustomResetRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18307$$Nest$fgetcustomResetRow(ProfileNotificationsActivity r0) {
        return r0.customResetRow;
    }

    /* renamed from: -$$Nest$fgetcustomResetShadowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18308$$Nest$fgetcustomResetShadowRow(ProfileNotificationsActivity r0) {
        return r0.customResetShadowRow;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ProfileNotificationsActivityDelegate m18309$$Nest$fgetdelegate(ProfileNotificationsActivity r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m18310$$Nest$fgetdialogId(ProfileNotificationsActivity r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetenableRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18311$$Nest$fgetenableRow(ProfileNotificationsActivity r0) {
        return r0.enableRow;
    }

    /* renamed from: -$$Nest$fgetgeneralRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18312$$Nest$fgetgeneralRow(ProfileNotificationsActivity r0) {
        return r0.generalRow;
    }

    /* renamed from: -$$Nest$fgetisInTop5Peers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18313$$Nest$fgetisInTop5Peers(ProfileNotificationsActivity r0) {
        return r0.isInTop5Peers;
    }

    /* renamed from: -$$Nest$fgetledInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18314$$Nest$fgetledInfoRow(ProfileNotificationsActivity r0) {
        return r0.ledInfoRow;
    }

    /* renamed from: -$$Nest$fgetledRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18315$$Nest$fgetledRow(ProfileNotificationsActivity r0) {
        return r0.ledRow;
    }

    /* renamed from: -$$Nest$fgetnotificationsEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18316$$Nest$fgetnotificationsEnabled(ProfileNotificationsActivity r0) {
        return r0.notificationsEnabled;
    }

    /* renamed from: -$$Nest$fgetpopupDisabledRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18317$$Nest$fgetpopupDisabledRow(ProfileNotificationsActivity r0) {
        return r0.popupDisabledRow;
    }

    /* renamed from: -$$Nest$fgetpopupEnabledRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18318$$Nest$fgetpopupEnabledRow(ProfileNotificationsActivity r0) {
        return r0.popupEnabledRow;
    }

    /* renamed from: -$$Nest$fgetpopupInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18319$$Nest$fgetpopupInfoRow(ProfileNotificationsActivity r0) {
        return r0.popupInfoRow;
    }

    /* renamed from: -$$Nest$fgetpopupRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18320$$Nest$fgetpopupRow(ProfileNotificationsActivity r0) {
        return r0.popupRow;
    }

    /* renamed from: -$$Nest$fgetpreviewRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18321$$Nest$fgetpreviewRow(ProfileNotificationsActivity r0) {
        return r0.previewRow;
    }

    /* renamed from: -$$Nest$fgetpriorityInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18322$$Nest$fgetpriorityInfoRow(ProfileNotificationsActivity r0) {
        return r0.priorityInfoRow;
    }

    /* renamed from: -$$Nest$fgetpriorityRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18323$$Nest$fgetpriorityRow(ProfileNotificationsActivity r0) {
        return r0.priorityRow;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m18324$$Nest$fgetresourcesProvider(ProfileNotificationsActivity r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetringtoneInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18325$$Nest$fgetringtoneInfoRow(ProfileNotificationsActivity r0) {
        return r0.ringtoneInfoRow;
    }

    /* renamed from: -$$Nest$fgetringtoneRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18326$$Nest$fgetringtoneRow(ProfileNotificationsActivity r0) {
        return r0.ringtoneRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18327$$Nest$fgetrowCount(ProfileNotificationsActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsmartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18328$$Nest$fgetsmartRow(ProfileNotificationsActivity r0) {
        return r0.smartRow;
    }

    /* renamed from: -$$Nest$fgetsoundRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18329$$Nest$fgetsoundRow(ProfileNotificationsActivity r0) {
        return r0.soundRow;
    }

    /* renamed from: -$$Nest$fgetstoriesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18330$$Nest$fgetstoriesRow(ProfileNotificationsActivity r0) {
        return r0.storiesRow;
    }

    /* renamed from: -$$Nest$fgettopicId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m18331$$Nest$fgettopicId(ProfileNotificationsActivity r2) {
        return r2.topicId;
    }

    /* renamed from: -$$Nest$fgetvibrateRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18332$$Nest$fgetvibrateRow(ProfileNotificationsActivity r0) {
        return r0.vibrateRow;
    }

    /* renamed from: -$$Nest$fputanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18333$$Nest$fputanimatorSet(ProfileNotificationsActivity r0, AnimatorSet r1) {
        r0.animatorSet = r1;
    }

    public ProfileNotificationsActivity(Bundle r2) {
        this(r2, null);
    }

    public ProfileNotificationsActivity(Bundle r3, Theme.ResourcesProvider r4) {
        super(r3);
        this.resourcesProvider = r4;
        this.dialogId = r3.getLong("dialog_id");
        this.topicId = r3.getLong("topic_id");
        this.addingException = r3.getBoolean("exception", false);
    }

    public static /* synthetic */ int access$000(ProfileNotificationsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(ProfileNotificationsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1000(ProfileNotificationsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1100(ProfileNotificationsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1200(ProfileNotificationsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(ProfileNotificationsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(ProfileNotificationsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(ProfileNotificationsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(ProfileNotificationsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$600(ProfileNotificationsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$700(ProfileNotificationsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$800(ProfileNotificationsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$900(ProfileNotificationsActivity r0) {
        return r0.currentAccount;
    }

    private void checkRowsEnabled() {
        int r0 = this.listView.getChildCount();
        ArrayList<Animator> r1 = new ArrayList();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L30;
        View r3 = this.listView.getChildAt(r2);
        RecyclerListView.Holder r32 = (RecyclerListView.Holder) this.listView.getChildViewHolder(r3);
        int r4 = r32.getItemViewType();
        int r5 = r32.getAdapterPosition();
        if (r5 == this.enableRow) goto L28;
        if (r5 == this.customResetRow) goto L28;
        if (r4 != 0) goto L10;
        ((HeaderCell) r32.itemView).setEnabled(this.notificationsEnabled, r1);
        goto L28
    L10:
        if (r4 != 1) goto L12;
        ((TextSettingsCell) r32.itemView).setEnabled(this.notificationsEnabled, r1);
        goto L28
    L12:
        if (r4 != 2) goto L14;
        ((TextInfoPrivacyCell) r32.itemView).setEnabled(this.notificationsEnabled, r1);
        goto L28
    L14:
        if (r4 != 3) goto L16;
        ((TextColorCell) r32.itemView).setEnabled(this.notificationsEnabled, r1);
        goto L28
    L16:
        if (r4 != 4) goto L18;
        ((RadioCell) r32.itemView).setEnabled(this.notificationsEnabled, r1);
        goto L28
    L18:
        if (r4 != 7) goto L28;
        if (r5 != this.previewRow) goto L28;
        ((TextCheckCell) r32.itemView).setEnabled(this.notificationsEnabled, r1);
    L28:
        r2 = r2 + 1;
        goto L3
    L30:
        if (r1.isEmpty() == true) goto L47;
        AnimatorSet r02 = this.animatorSet;
        if (r02 == null) goto L34;
        r02.cancel();
    L34:
        AnimatorSet r03 = new AnimatorSet();
        this.animatorSet = r03;
        r03.playTogether(r1);
        this.animatorSet.addListener(new AnonymousClass3(this));
        this.animatorSet.setDuration(150);
        this.animatorSet.start();
        return;
    }

    private /* synthetic */ void lambda$createView$0(String r2, AlertDialog r3, int r4) {
        this.needReset = true;
        MessagesController.getNotificationsSettings(this.currentAccount).edit().putBoolean(NotificationsSettingsFacade.PROPERTY_CUSTOM + r2, false).remove(NotificationsSettingsFacade.PROPERTY_NOTIFY + r2).apply();
        finishFragment();
        ProfileNotificationsActivityDelegate r22 = this.delegate;
        if (r22 == null) goto L6;
        r22.didRemoveException(this.dialogId);
        return;
    }

    private /* synthetic */ void lambda$createView$1() {
        ListAdapter r0 = this.adapter;
        if (r0 == null) goto L6;
        r0.notifyItemChanged(this.vibrateRow);
        return;
    }

    private /* synthetic */ void lambda$createView$2() {
        ListAdapter r0 = this.adapter;
        if (r0 == null) goto L6;
        r0.notifyItemChanged(this.callsVibrateRow);
        return;
    }

    private /* synthetic */ void lambda$createView$3() {
        ListAdapter r0 = this.adapter;
        if (r0 == null) goto L6;
        r0.notifyItemChanged(this.priorityRow);
        return;
    }

    private /* synthetic */ void lambda$createView$4(String r4, int r5, int r6) {
        MessagesController.getNotificationsSettings(this.currentAccount).edit().putInt("smart_max_count_" + r4, r5).putInt("smart_delay_" + r4, r6).apply();
        ListAdapter r42 = this.adapter;
        if (r42 == null) goto L6;
        r42.notifyItemChanged(this.smartRow);
        return;
    }

    private /* synthetic */ void lambda$createView$5() {
        ListAdapter r0 = this.adapter;
        if (r0 == null) goto L6;
        r0.notifyItemChanged(this.colorRow);
        return;
    }

    private /* synthetic */ void lambda$createView$6(Context r12, String r13, View r14, int r15) {
        if (r14.isEnabled() == false) goto L99;
        Parcelable r2 = null;
        if (r15 != this.customResetRow) goto L12;
        AlertDialog r122 = new AlertDialog.Builder(r12, this.resourcesProvider).setTitle(LocaleController.getString(R.string.ResetCustomNotificationsAlertTitle)).setMessage(LocaleController.getString(R.string.ResetCustomNotificationsAlert)).setPositiveButton(LocaleController.getString(R.string.Reset), new ProfileNotificationsActivity$$ExternalSyntheticLambda2(this, r13)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
        showDialog(r122);
        TextView r11 = (TextView) r122.getButton(-1);
        if (r11 == null) goto L95;
        r11.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L95:
        return;
    L12:
        if (r15 != this.soundRow) goto L16;
        Bundle r123 = new Bundle();
        r123.putLong("dialog_id", this.dialogId);
        r123.putLong("topic_id", this.topicId);
        presentFragment(new NotificationsSoundActivity(r123, this.resourcesProvider));
        return;
    L16:
        if (r15 != this.ringtoneRow) goto L35;
        Intent r124 = new Intent("android.intent.action.RINGTONE_PICKER");     // Catch: Exception -> L31
        r124.putExtra("android.intent.extra.ringtone.TYPE", 1);     // Catch: Exception -> L31
        r124.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", true);     // Catch: Exception -> L31
        r124.putExtra("android.intent.extra.ringtone.SHOW_SILENT", true);     // Catch: Exception -> L31
        r124.putExtra("android.intent.extra.ringtone.DEFAULT_URI", RingtoneManager.getDefaultUri(1));     // Catch: Exception -> L31
        SharedPreferences r142 = MessagesController.getNotificationsSettings(this.currentAccount);     // Catch: Exception -> L31
        Uri r152 = Settings.System.DEFAULT_NOTIFICATION_URI;     // Catch: Exception -> L31
        if (r152 == null) goto L20;
        String r1 = r152.getPath();     // Catch: Exception -> L31
    L21:
        String r132 = r142.getString("ringtone_path_" + r13, r1);     // Catch: Exception -> L31
        if (r132 != null) goto L24;
    L29:
        r124.putExtra("android.intent.extra.ringtone.EXISTING_URI", r2);     // Catch: Exception -> L31
        startActivityForResult(r124, 13);     // Catch: Exception -> L31
        return;
    L24:
        if (r132.equals("NoSound") == true) goto L29;
        if (r132.equals(r1) == false) goto L28;
        r2 = r152;
        goto L29
    L28:
        r2 = Uri.parse(r132);     // Catch: Exception -> L31
        goto L29
    L20:
        r1 = null;
    L31:
        e = move-exception;
        FileLog.e(e);
        return;
    L35:
        if (r15 != this.vibrateRow) goto L39;
        showDialog(AlertsCreator.createVibrationSelectDialog(getParentActivity(), this.dialogId, this.topicId, false, false, new ProfileNotificationsActivity$$ExternalSyntheticLambda3(this), this.resourcesProvider));
        return;
    L39:
        if (r15 != this.enableRow) goto L43;
        TextCheckCell r143 = (TextCheckCell) r14;
        boolean r125 = !r143.isChecked();
        this.notificationsEnabled = r125;
        r143.setChecked(r125);
        checkRowsEnabled();
        return;
    L43:
        if (r15 != this.previewRow) goto L47;
        TextCheckCell r144 = (TextCheckCell) r14;
        MessagesController.getNotificationsSettings(this.currentAccount).edit().putBoolean(NotificationsSettingsFacade.PROPERTY_CONTENT_PREVIEW + r13, !r144.isChecked()).apply();
        r144.setChecked(r144.isChecked() ^ true);
        return;
    L47:
        if (r15 != this.callsVibrateRow) goto L51;
        showDialog(AlertsCreator.createVibrationSelectDialog(getParentActivity(), this.dialogId, this.topicId, "calls_vibrate_" + r13, new ProfileNotificationsActivity$$ExternalSyntheticLambda4(this), this.resourcesProvider));
        return;
    L51:
        if (r15 != this.priorityRow) goto L54;
        showDialog(AlertsCreator.createPrioritySelectDialog(getParentActivity(), this.dialogId, this.topicId, -1, new ProfileNotificationsActivity$$ExternalSyntheticLambda5(this), this.resourcesProvider));
        return;
    L54:
        int r0 = 2;
        if (r15 != this.smartRow) goto L66;
        if (getParentActivity() == null) goto L100;
        SharedPreferences r126 = MessagesController.getNotificationsSettings(this.currentAccount);
        int r145 = r126.getInt("smart_max_count_" + r13, 2);
        int r127 = r126.getInt("smart_delay_" + r13, 180);
        if (r145 == 0) goto L63;
        r0 = r145;
    L63:
        AlertsCreator.createSoundFrequencyPickerDialog(getParentActivity(), r0, r127, new ProfileNotificationsActivity$$ExternalSyntheticLambda6(this, r13), this.resourcesProvider);
        return;
    L100:
        return;
    L66:
        if (r15 != this.colorRow) goto L73;
        if (getParentActivity() == null) goto L101;
        showDialog(AlertsCreator.createColorSelectDialog(getParentActivity(), this.dialogId, this.topicId, -1, new ProfileNotificationsActivity$$ExternalSyntheticLambda7(this), this.resourcesProvider));
        return;
    L101:
        return;
    L73:
        if (r15 != this.popupEnabledRow) goto L79;
        MessagesController.getNotificationsSettings(this.currentAccount).edit().putInt("popup_" + r13, 1).apply();
        ((RadioCell) r14).setChecked(true, true);
        View r112 = this.listView.findViewWithTag(2);
        if (r112 == null) goto L96;
        ((RadioCell) r112).setChecked(false, true);
        return;
    L96:
        return;
    L79:
        if (r15 != this.popupDisabledRow) goto L85;
        MessagesController.getNotificationsSettings(this.currentAccount).edit().putInt("popup_" + r13, 2).apply();
        ((RadioCell) r14).setChecked(true, true);
        View r113 = this.listView.findViewWithTag(1);
        if (r113 == null) goto L97;
        ((RadioCell) r113).setChecked(false, true);
        return;
    L97:
        return;
    L85:
        if (r15 != this.storiesRow) goto L98;
        TextCheckCell r146 = (TextCheckCell) r14;
        boolean r128 = r146.isChecked();
        boolean r153 = !r128;
        r146.setChecked(r153);
        SharedPreferences.Editor r147 = MessagesController.getNotificationsSettings(this.currentAccount).edit();
        if (this.isInTop5Peers == false) goto L90;
        if (r128 == true) goto L90;
        r147.remove(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r13);
    L91:
        r147.apply();
        getNotificationsController().updateServerNotificationsSettings(this.dialogId, this.topicId);
        return;
    L90:
        r147.putBoolean(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r13, r153);
        goto L91
    L98:
        return;
    }

    private /* synthetic */ void lambda$getThemeDescriptions$7() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L14;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof UserCell2) == false) goto L9;
        ((UserCell2) r3).update(0);
    L9:
        r2 = r2 + 1;
        goto L5
    L14:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r15) {
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_avatar_actionBarSelectorBlue, this.resourcesProvider), false);
        this.actionBar.setItemsColor(Theme.getColor(Theme.key_actionBarDefaultIcon, this.resourcesProvider), false);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        String r0 = NotificationsController.getSharedPrefKey(this.dialogId, this.topicId);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this, r0));
        ChatAvatarContainer r1 = new ChatAvatarContainer(r15, null, false, this.resourcesProvider);
        this.avatarContainer = r1;
        r1.setOccupyStatusBar(!AndroidUtilities.isTablet());
        ActionBar r12 = this.actionBar;
        ChatAvatarContainer r3 = this.avatarContainer;
        if (this.inPreviewMode == true) goto L6;
        float r6 = 56.0f;
    L7:
        r12.addView(r3, 0, LayoutHelper.createFrame(-2, -1.0f, 51, r6, 0.0f, 40.0f, 0.0f));
        this.actionBar.setAllowOverlayTitle(false);
        if (this.dialogId < 0) goto L10;
        TLRPC.User r13 = getMessagesController().getUser(Long.valueOf(this.dialogId));
        if (r13 == null) goto L16;
        this.avatarContainer.setUserAvatar(r13);
        this.avatarContainer.setTitle(ContactsController.formatName(r13.first_name, r13.last_name));
    L16:
        boolean r14 = this.addingException;
        ChatAvatarContainer r2 = this.avatarContainer;
        if (r14 == false) goto L19;
        r2.setSubtitle(LocaleController.getString(R.string.NotificationsNewException));
        this.actionBar.createMenu().addItem(1, LocaleController.getString(R.string.Done).toUpperCase());
    L20:
        FrameLayout r16 = new FrameLayout(r15);
        this.fragmentView = r16;
        r16.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider));
        RecyclerListView r22 = new RecyclerListView(r15);
        this.listView = r22;
        r22.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        r16.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r17 = this.listView;
        ListAdapter r23 = new ListAdapter(this, r15);
        this.adapter = r23;
        r17.setAdapter(r23);
        this.listView.setItemAnimator(null);
        this.listView.setLayoutAnimation(null);
        this.listView.setLayoutManager(new AnonymousClass2(this, r15));
        this.listView.setOnItemClickListener(new ProfileNotificationsActivity$$ExternalSyntheticLambda0(this, r15, r0));
        return this.fragmentView;
    L19:
        r2.setSubtitle(LocaleController.getString(R.string.CustomNotifications));
        goto L20
    L10:
        if (this.topicId == 0) goto L12;
        TLRPC.TL_forumTopic r18 = getMessagesController().getTopicsController().findTopic(-this.dialogId, this.topicId);
        ForumUtilities.setTopicIcon(this.avatarContainer.getAvatarImageView(), r18, false, true, this.resourcesProvider);
        this.avatarContainer.setTitle(r18.title);
        goto L16
    L12:
        TLRPC.Chat r19 = getMessagesController().getChat(Long.valueOf(-this.dialogId));
        this.avatarContainer.setChatAvatar(r19);
        this.avatarContainer.setTitle(r19.title);
        goto L16
    L6:
        r6 = 0.0f;
        goto L7
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    @SuppressLint({"NotifyDataSetChanged"})
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.notificationsSettingsUpdated) goto L9;
        this.adapter.notifyDataSetChanged();     // Catch: Exception -> L6
        return;
    L10:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public Theme.ResourcesProvider getResourceProvider() {
        return this.resourcesProvider;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        ProfileNotificationsActivity$$ExternalSyntheticLambda1 r8 = new ProfileNotificationsActivity$$ExternalSyntheticLambda1(this);
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{HeaderCell.class, TextSettingsCell.class, TextColorCell.class, RadioCell.class, UserCell2.class, TextCheckCell.class, TextCheckBoxCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        ActionBar r12 = this.actionBar;
        int r13 = ThemeDescription.FLAG_BACKGROUND;
        int r18 = Theme.key_actionBarDefault;
        r1.add(new ThemeDescription(r12, r13, null, null, null, null, r18));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r18));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        int r26 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r26));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextColorCell.class}, new String[]{"textView"}, null, null, null, r26));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{RadioCell.class}, new String[]{"textView"}, null, null, null, r26));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKBOX, new Class[]{RadioCell.class}, new String[]{"radioButton"}, null, null, null, Theme.key_radioBackground));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{RadioCell.class}, new String[]{"radioButton"}, null, null, null, Theme.key_radioBackgroundChecked));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, r26));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrack));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrackChecked));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell2.class}, new String[]{"nameTextView"}, null, null, null, r26));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell2.class}, new String[]{"statusColor"}, null, null, r8, Theme.key_windowBackgroundWhiteGrayText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell2.class}, new String[]{"statusOnlineColor"}, null, null, r8, Theme.key_windowBackgroundWhiteBlueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell2.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r7, int r8, Intent r9) {
        if (r8 != (-1)) goto L40;
        if (r9 == null) goto L42;
        Uri r82 = (Uri) r9.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
        if (r82 == null) goto L20;
        Ringtone r0 = RingtoneManager.getRingtone(ApplicationLoader.applicationContext, r82);
        if (r0 == null) goto L20;
        if (r7 != 13) goto L16;
        if (r82.equals(Settings.System.DEFAULT_RINGTONE_URI) == false) goto L14;
        String r1 = LocaleController.getString(R.string.DefaultRingtone);
    L19:
        r0.stop();
    L21:
        SharedPreferences.Editor r02 = MessagesController.getNotificationsSettings(this.currentAccount).edit();
        String r2 = NotificationsController.getSharedPrefKey(this.dialogId, this.topicId);
        if (r7 == 12) goto L24;
        if (r7 == 13) goto L30;
    L33:
        r02.apply();
        ListAdapter r83 = this.adapter;
        if (r83 == null) goto L41;
        if (r7 != 13) goto L37;
        int r6 = this.ringtoneRow;
    L38:
        r83.notifyItemChanged(r6);
        return;
    L37:
        r6 = this.soundRow;
        goto L38
    L41:
        return;
    L30:
        if (r1 == null) goto L32;
        r02.putString("ringtone_" + r2, r1);
        r02.putString("ringtone_path_" + r2, r82.toString());
        goto L33
    L32:
        r02.putString("ringtone_" + r2, "NoSound");
        r02.putString("ringtone_path_" + r2, "NoSound");
        goto L33
    L24:
        if (r1 == null) goto L26;
        r02.putString("sound_" + r2, r1);
        r02.putString("sound_path_" + r2, r82.toString());
    L27:
        getNotificationsController().deleteNotificationChannel(this.dialogId, this.topicId);
        goto L33
    L26:
        r02.putString("sound_" + r2, "NoSound");
        r02.putString("sound_path_" + r2, "NoSound");
        goto L27
    L14:
        r1 = r0.getTitle(getParentActivity());
        goto L19
    L16:
        if (r82.equals(Settings.System.DEFAULT_NOTIFICATION_URI) == false) goto L18;
        r1 = LocaleController.getString(R.string.SoundDefault);
        goto L19
    L18:
        r1 = r0.getTitle(getParentActivity());
    L20:
        r1 = null;
        goto L21
    L42:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        if (DialogObject.isUserDialog(this.dialogId) == false) goto L16;
        ArrayList<TLRPC.TL_topPeer> r0 = getMediaDataController().hints;
        int r4 = 0;
    L6:
        if (r4 >= r0.size()) goto L16;
        TLRPC.Peer r5 = r0.get(r4).peer;
        if ((r5 instanceof TLRPC.TL_peerUser) == false) goto L15;
        if (r5.user_id != this.dialogId) goto L15;
        if (r4 >= 5) goto L13;
        boolean r02 = true;
    L14:
        this.isInTop5Peers = r02;
        goto L16
    L13:
        r02 = false;
    L15:
        r4 = r4 + 1;
    L16:
        this.rowCount = 0;
        boolean r03 = this.addingException;
        if (r03 == false) goto L19;
        this.avatarRow = 0;
        this.rowCount = 1 + 1;
        this.avatarSectionRow = 1;
    L20:
        int r52 = this.rowCount;
        int r6 = r52 + 1;
        this.rowCount = r6;
        this.generalRow = r52;
        if (r03 == false) goto L23;
    L26:
        this.rowCount = r52 + 2;
        this.enableRow = r6;
    L27:
        this.storiesRow = -1;
        if (DialogObject.isEncryptedDialog(this.dialogId) == true) goto L32;
        int r04 = this.rowCount;
        this.rowCount = r04 + 1;
        this.previewRow = r04;
        if (DialogObject.isUserDialog(this.dialogId) == false) goto L33;
        int r05 = this.rowCount;
        this.rowCount = r05 + 1;
        this.storiesRow = r05;
    L33:
        int r06 = this.rowCount;
        this.soundRow = r06;
        this.rowCount = r06 + 2;
        this.vibrateRow = r06 + 1;
        if (DialogObject.isChatDialog(this.dialogId) == false) goto L36;
        int r07 = this.rowCount;
        this.rowCount = r07 + 1;
        this.smartRow = r07;
    L37:
        int r08 = this.rowCount;
        this.priorityRow = r08;
        this.rowCount = r08 + 2;
        this.priorityInfoRow = r08 + 1;
        if (DialogObject.isChatDialog(this.dialogId) == false) goto L44;
        TLRPC.Chat r09 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        if (ChatObject.isChannel(r09) == false) goto L44;
        if (r09.megagroup == true) goto L44;
        boolean r010 = true;
    L46:
        if (DialogObject.isEncryptedDialog(this.dialogId) == true) goto L49;
        if (r010 == true) goto L49;
        int r011 = this.rowCount;
        this.popupRow = r011;
        this.popupEnabledRow = r011 + 1;
        this.popupDisabledRow = r011 + 2;
        this.rowCount = r011 + 4;
        this.popupInfoRow = r011 + 3;
    L51:
        if (DialogObject.isUserDialog(this.dialogId) == false) goto L53;
        int r012 = this.rowCount;
        this.callsRow = r012;
        this.callsVibrateRow = r012 + 1;
        this.ringtoneRow = r012 + 2;
        this.rowCount = r012 + 4;
        this.ringtoneInfoRow = r012 + 3;
    L54:
        int r013 = this.rowCount;
        this.ledRow = r013;
        this.colorRow = r013 + 1;
        int r53 = r013 + 3;
        this.rowCount = r53;
        this.ledInfoRow = r013 + 2;
        if (this.addingException == true) goto L57;
        this.customResetRow = r53;
        this.rowCount = r013 + 5;
        this.customResetShadowRow = r013 + 4;
    L58:
        boolean r014 = NotificationsController.getInstance(this.currentAccount).isGlobalNotificationsEnabled(this.dialogId, false, false);
        if (this.addingException == false) goto L61;
        this.notificationsEnabled = !r014;
    L71:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.notificationsSettingsUpdated);
        return super.onFragmentCreate();
    L61:
        SharedPreferences r015 = MessagesController.getNotificationsSettings(this.currentAccount);
        String r1 = NotificationsController.getSharedPrefKey(this.dialogId, this.topicId);
        boolean r42 = r015.contains(NotificationsSettingsFacade.PROPERTY_NOTIFY + r1);
        int r016 = r015.getInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + r1, 0);
        if (r016 != 0) goto L66;
        if (r42 == false) goto L65;
        this.notificationsEnabled = true;
        goto L71
    L65:
        this.notificationsEnabled = NotificationsController.getInstance(this.currentAccount).isGlobalNotificationsEnabled(this.dialogId, false, false);
        goto L71
    L66:
        if (r016 != 1) goto L68;
        this.notificationsEnabled = true;
        goto L71
    L68:
        if (r016 != 2) goto L70;
        this.notificationsEnabled = false;
        goto L71
    L70:
        this.notificationsEnabled = false;
        goto L71
    L57:
        this.customResetRow = -1;
        this.customResetShadowRow = -1;
        goto L58
    L53:
        this.callsRow = -1;
        this.callsVibrateRow = -1;
        this.ringtoneRow = -1;
        this.ringtoneInfoRow = -1;
    L49:
        this.popupRow = -1;
        this.popupEnabledRow = -1;
        this.popupDisabledRow = -1;
        this.popupInfoRow = -1;
    L44:
        r010 = false;
        goto L46
    L36:
        this.smartRow = -1;
        goto L37
    L32:
        this.previewRow = -1;
        goto L33
    L23:
        if (this.topicId != 0) goto L26;
        this.enableRow = -1;
        goto L27
    L19:
        this.avatarRow = -1;
        this.avatarSectionRow = -1;
        goto L20
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (this.needReset == true) goto L5;
        String r0 = NotificationsController.getSharedPrefKey(this.dialogId, this.topicId);
        MessagesController.getNotificationsSettings(this.currentAccount).edit().putBoolean(NotificationsSettingsFacade.PROPERTY_CUSTOM + r0, true).apply();
    L5:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.notificationsSettingsUpdated);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    public void setDelegate(ProfileNotificationsActivityDelegate r1) {
        this.delegate = r1;
    }
}
