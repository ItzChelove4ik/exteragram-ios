package org.telegram.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Keep;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.notifications.AccountNotifications;
import com.exteragram.messenger.notifications.AccountNotificationsActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.NotificationsSettingsFacade;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.NotificationsCheckCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextDetailSettingsCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class NotificationsSettingsActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {

    @Keep
    private int accountsAllRow;
    private int accountsInfoRow;
    private int accountsListRow;
    private int accountsSectionRow;
    private ListAdapter adapter;
    private int androidAutoAlertRow;

    @Keep
    private int badgeNumberMessagesRow;

    @Keep
    private int badgeNumberMutedRow;
    private int badgeNumberSection;
    private int badgeNumberSection2Row;

    @Keep
    private int badgeNumberShowRow;
    private int callsRingtoneRow;
    private int callsSection2Row;
    private int callsSectionRow;
    private int callsVibrateRow;

    @Keep
    private int channelsRow;

    @Keep
    private int contactJoinedRow;
    private int eventsSection2Row;
    private int eventsSectionRow;
    private ArrayList<NotificationException> exceptionAutoStories;
    private ArrayList<NotificationException> exceptionChannels;
    private ArrayList<NotificationException> exceptionChats;
    private ArrayList<NotificationException> exceptionStories;
    private ArrayList<NotificationException> exceptionUsers;

    @Keep
    private int groupRow;

    @Keep
    private int inappPreviewRow;

    @Keep
    private int inappPriorityRow;
    private int inappSectionRow;

    @Keep
    private int inappSoundRow;

    @Keep
    private int inappVibrateRow;

    @Keep
    private int inchatSoundRow;
    private LinearLayoutManager layoutManager;
    private RecyclerListView listView;
    private int notificationsSection2Row;
    private int notificationsSectionRow;
    private int notificationsServiceConnectionRow;
    private int notificationsServiceRow;
    private int otherSection2Row;
    private int otherSectionRow;

    @Keep
    private int pinnedMessageRow;

    @Keep
    private int privateRow;

    @Keep
    private int reactionsRow;
    private int repeatRow;

    @Keep
    private int resetNotificationsRow;
    private int resetNotificationsSectionRow;
    private int resetSection2Row;
    private int resetSectionRow;
    private boolean reseting;
    private int rowCount;

    @Keep
    private int storiesRow;
    private boolean updateRepeatNotifications;
    private boolean updateRingtone;
    private boolean updateVibrate;

    /* renamed from: org.telegram.ui.NotificationsSettingsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ NotificationsSettingsActivity this$0;

        public AnonymousClass1(NotificationsSettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.NotificationsSettingsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayoutManager {
        final /* synthetic */ NotificationsSettingsActivity this$0;

        public AnonymousClass2(NotificationsSettingsActivity r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ NotificationsSettingsActivity this$0;

        public ListAdapter(NotificationsSettingsActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return NotificationsSettingsActivity.m16334$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != NotificationsSettingsActivity.m16307$$Nest$fgeteventsSectionRow(this.this$0)) goto L5;
            return 0;
        L5:
            if (r2 != NotificationsSettingsActivity.m16325$$Nest$fgetotherSectionRow(this.this$0)) goto L7;
            return 0;
        L7:
            if (r2 != NotificationsSettingsActivity.m16333$$Nest$fgetresetSectionRow(this.this$0)) goto L9;
            return 0;
        L9:
            if (r2 != NotificationsSettingsActivity.m16302$$Nest$fgetcallsSectionRow(this.this$0)) goto L11;
            return 0;
        L11:
            if (r2 != NotificationsSettingsActivity.m16297$$Nest$fgetbadgeNumberSection(this.this$0)) goto L13;
            return 0;
        L13:
            if (r2 != NotificationsSettingsActivity.m16316$$Nest$fgetinappSectionRow(this.this$0)) goto L15;
            return 0;
        L15:
            if (r2 != NotificationsSettingsActivity.m16321$$Nest$fgetnotificationsSectionRow(this.this$0)) goto L17;
            return 0;
        L17:
            if (r2 != NotificationsSettingsActivity.m16293$$Nest$fgetaccountsSectionRow(this.this$0)) goto L20;
            return 0;
        L20:
            if (r2 != NotificationsSettingsActivity.m16317$$Nest$fgetinappSoundRow(this.this$0)) goto L22;
            return 1;
        L22:
            if (r2 != NotificationsSettingsActivity.m16318$$Nest$fgetinappVibrateRow(this.this$0)) goto L24;
            return 1;
        L24:
            if (r2 != NotificationsSettingsActivity.m16322$$Nest$fgetnotificationsServiceConnectionRow(this.this$0)) goto L26;
            return 1;
        L26:
            if (r2 != NotificationsSettingsActivity.m16314$$Nest$fgetinappPreviewRow(this.this$0)) goto L28;
            return 1;
        L28:
            if (r2 != NotificationsSettingsActivity.m16305$$Nest$fgetcontactJoinedRow(this.this$0)) goto L30;
            return 1;
        L30:
            if (r2 != NotificationsSettingsActivity.m16326$$Nest$fgetpinnedMessageRow(this.this$0)) goto L32;
            return 1;
        L32:
            if (r2 != NotificationsSettingsActivity.m16323$$Nest$fgetnotificationsServiceRow(this.this$0)) goto L34;
            return 1;
        L34:
            if (r2 != NotificationsSettingsActivity.m16296$$Nest$fgetbadgeNumberMutedRow(this.this$0)) goto L36;
            return 1;
        L36:
            if (r2 != NotificationsSettingsActivity.m16295$$Nest$fgetbadgeNumberMessagesRow(this.this$0)) goto L38;
            return 1;
        L38:
            if (r2 != NotificationsSettingsActivity.m16299$$Nest$fgetbadgeNumberShowRow(this.this$0)) goto L40;
            return 1;
        L40:
            if (r2 != NotificationsSettingsActivity.m16315$$Nest$fgetinappPriorityRow(this.this$0)) goto L42;
            return 1;
        L42:
            if (r2 != NotificationsSettingsActivity.m16319$$Nest$fgetinchatSoundRow(this.this$0)) goto L44;
            return 1;
        L44:
            if (r2 != NotificationsSettingsActivity.m16294$$Nest$fgetandroidAutoAlertRow(this.this$0)) goto L46;
            return 1;
        L46:
            if (r2 != NotificationsSettingsActivity.m16290$$Nest$fgetaccountsAllRow(this.this$0)) goto L49;
            return 1;
        L49:
            if (r2 != NotificationsSettingsActivity.m16330$$Nest$fgetresetNotificationsRow(this.this$0)) goto L53;
            return 2;
        L53:
            if (r2 != NotificationsSettingsActivity.m16327$$Nest$fgetprivateRow(this.this$0)) goto L55;
            return 3;
        L55:
            if (r2 != NotificationsSettingsActivity.m16313$$Nest$fgetgroupRow(this.this$0)) goto L57;
            return 3;
        L57:
            if (r2 != NotificationsSettingsActivity.m16304$$Nest$fgetchannelsRow(this.this$0)) goto L59;
            return 3;
        L59:
            if (r2 != NotificationsSettingsActivity.m16335$$Nest$fgetstoriesRow(this.this$0)) goto L61;
            return 3;
        L61:
            if (r2 != NotificationsSettingsActivity.m16328$$Nest$fgetreactionsRow(this.this$0)) goto L64;
            return 3;
        L64:
            if (r2 != NotificationsSettingsActivity.m16306$$Nest$fgeteventsSection2Row(this.this$0)) goto L66;
            return 4;
        L66:
            if (r2 != NotificationsSettingsActivity.m16320$$Nest$fgetnotificationsSection2Row(this.this$0)) goto L68;
            return 4;
        L68:
            if (r2 != NotificationsSettingsActivity.m16324$$Nest$fgetotherSection2Row(this.this$0)) goto L70;
            return 4;
        L70:
            if (r2 != NotificationsSettingsActivity.m16332$$Nest$fgetresetSection2Row(this.this$0)) goto L72;
            return 4;
        L72:
            if (r2 != NotificationsSettingsActivity.m16301$$Nest$fgetcallsSection2Row(this.this$0)) goto L74;
            return 4;
        L74:
            if (r2 != NotificationsSettingsActivity.m16298$$Nest$fgetbadgeNumberSection2Row(this.this$0)) goto L76;
            return 4;
        L76:
            if (r2 != NotificationsSettingsActivity.m16331$$Nest$fgetresetNotificationsSectionRow(this.this$0)) goto L79;
            return 4;
        L79:
            if (r2 != NotificationsSettingsActivity.m16291$$Nest$fgetaccountsInfoRow(this.this$0)) goto L82;
            return 6;
        L82:
            return 5;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            int r22 = r2.getAdapterPosition();
            if (r22 != NotificationsSettingsActivity.m16321$$Nest$fgetnotificationsSectionRow(this.this$0)) goto L5;
            return false;
        L5:
            if (r22 != NotificationsSettingsActivity.m16320$$Nest$fgetnotificationsSection2Row(this.this$0)) goto L7;
            return false;
        L7:
            if (r22 != NotificationsSettingsActivity.m16316$$Nest$fgetinappSectionRow(this.this$0)) goto L9;
            return false;
        L9:
            if (r22 != NotificationsSettingsActivity.m16307$$Nest$fgeteventsSectionRow(this.this$0)) goto L11;
            return false;
        L11:
            if (r22 != NotificationsSettingsActivity.m16325$$Nest$fgetotherSectionRow(this.this$0)) goto L13;
            return false;
        L13:
            if (r22 != NotificationsSettingsActivity.m16333$$Nest$fgetresetSectionRow(this.this$0)) goto L15;
            return false;
        L15:
            if (r22 != NotificationsSettingsActivity.m16297$$Nest$fgetbadgeNumberSection(this.this$0)) goto L17;
            return false;
        L17:
            if (r22 != NotificationsSettingsActivity.m16324$$Nest$fgetotherSection2Row(this.this$0)) goto L19;
            return false;
        L19:
            if (r22 != NotificationsSettingsActivity.m16332$$Nest$fgetresetSection2Row(this.this$0)) goto L21;
            return false;
        L21:
            if (r22 != NotificationsSettingsActivity.m16301$$Nest$fgetcallsSection2Row(this.this$0)) goto L23;
            return false;
        L23:
            if (r22 != NotificationsSettingsActivity.m16302$$Nest$fgetcallsSectionRow(this.this$0)) goto L25;
            return false;
        L25:
            if (r22 != NotificationsSettingsActivity.m16298$$Nest$fgetbadgeNumberSection2Row(this.this$0)) goto L27;
            return false;
        L27:
            if (r22 != NotificationsSettingsActivity.m16293$$Nest$fgetaccountsSectionRow(this.this$0)) goto L29;
            return false;
        L29:
            if (r22 != NotificationsSettingsActivity.m16291$$Nest$fgetaccountsInfoRow(this.this$0)) goto L31;
            return false;
        L31:
            if (r22 != NotificationsSettingsActivity.m16331$$Nest$fgetresetNotificationsSectionRow(this.this$0)) goto L33;
            return false;
        L33:
            if (r22 != NotificationsSettingsActivity.m16306$$Nest$fgeteventsSection2Row(this.this$0)) goto L35;
            return false;
        L35:
            if (r22 == NotificationsSettingsActivity.m16292$$Nest$fgetaccountsListRow(this.this$0)) goto L37;
            return true;
        L37:
            if (SharedConfig.showNotificationsForAllAccounts == false) goto L58;
            return true;
        L58:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r21, int r22) {
            int r3 = r21.getItemViewType();
            if (r3 == 0) goto L213;
            boolean r5 = false;
            if (r3 != 1) goto L7;
            TextCheckCell r52 = (TextCheckCell) r21.itemView;
            SharedPreferences r1 = MessagesController.getNotificationsSettings(NotificationsSettingsActivity.access$600(this.this$0));
            if (r22 != NotificationsSettingsActivity.m16317$$Nest$fgetinappSoundRow(this.this$0)) goto L158;
            r52.setTextAndCheck(LocaleController.getString(R.string.InAppSounds), r1.getBoolean("EnableInAppSounds", true), true);
            return;
        L158:
            if (r22 != NotificationsSettingsActivity.m16318$$Nest$fgetinappVibrateRow(this.this$0)) goto L162;
            r52.setTextAndCheck(LocaleController.getString(R.string.InAppVibrate), r1.getBoolean("EnableInAppVibrate", true), true);
            return;
        L162:
            if (r22 != NotificationsSettingsActivity.m16314$$Nest$fgetinappPreviewRow(this.this$0)) goto L166;
            r52.setTextAndCheck(LocaleController.getString(R.string.InAppPreview), r1.getBoolean("EnableInAppPreview", true), true);
            return;
        L166:
            if (r22 != NotificationsSettingsActivity.m16315$$Nest$fgetinappPriorityRow(this.this$0)) goto L170;
            r52.setTextAndValueAndCheck(LocaleController.getString(R.string.InAppPopup), LocaleController.getString(R.string.InAppPopupInfo), r1.getBoolean("EnableInAppPopup", true), true, false);
            return;
        L170:
            if (r22 != NotificationsSettingsActivity.m16305$$Nest$fgetcontactJoinedRow(this.this$0)) goto L174;
            r52.setTextAndCheck(LocaleController.getString("ContactJoined", R.string.ContactJoined), r1.getBoolean("EnableContactJoined", true), true);
            return;
        L174:
            if (r22 != NotificationsSettingsActivity.m16326$$Nest$fgetpinnedMessageRow(this.this$0)) goto L178;
            r52.setTextAndCheck(LocaleController.getString("PinnedMessages", R.string.PinnedMessages), r1.getBoolean("PinnedMessages", true), false);
            return;
        L178:
            if (r22 != NotificationsSettingsActivity.m16294$$Nest$fgetandroidAutoAlertRow(this.this$0)) goto L182;
            r52.setTextAndCheck("Android Auto", r1.getBoolean("EnableAutoNotifications", false), true);
            return;
        L182:
            if (r22 != NotificationsSettingsActivity.m16323$$Nest$fgetnotificationsServiceRow(this.this$0)) goto L186;
            r52.setTextAndValueAndCheck(LocaleController.getString("NotificationsService", R.string.NotificationsService), LocaleController.getString("NotificationsServiceInfo", R.string.NotificationsServiceInfo), r1.getBoolean("pushService", this.this$0.getMessagesController().keepAliveService), true, true);
            return;
        L186:
            if (r22 != NotificationsSettingsActivity.m16322$$Nest$fgetnotificationsServiceConnectionRow(this.this$0)) goto L190;
            r52.setTextAndValueAndCheck(LocaleController.getString("NotificationsServiceConnection", R.string.NotificationsServiceConnection), LocaleController.getString("NotificationsServiceConnectionInfo", R.string.NotificationsServiceConnectionInfo), r1.getBoolean("pushConnection", this.this$0.getMessagesController().backgroundConnection), true, true);
            return;
        L190:
            if (r22 != NotificationsSettingsActivity.m16299$$Nest$fgetbadgeNumberShowRow(this.this$0)) goto L194;
            r52.setTextAndCheck(LocaleController.getString("BadgeNumberShow", R.string.BadgeNumberShow), NotificationsSettingsActivity.access$700(this.this$0).showBadgeNumber, true);
            return;
        L194:
            if (r22 != NotificationsSettingsActivity.m16296$$Nest$fgetbadgeNumberMutedRow(this.this$0)) goto L198;
            r52.setTextAndCheck(LocaleController.getString("BadgeNumberMutedChats", R.string.BadgeNumberMutedChats), NotificationsSettingsActivity.access$800(this.this$0).showBadgeMuted, true);
            return;
        L198:
            if (r22 != NotificationsSettingsActivity.m16295$$Nest$fgetbadgeNumberMessagesRow(this.this$0)) goto L202;
            r52.setTextAndCheck(LocaleController.getString("BadgeNumberUnread", R.string.BadgeNumberUnread), NotificationsSettingsActivity.access$900(this.this$0).showBadgeMessages, false);
            return;
        L202:
            if (r22 != NotificationsSettingsActivity.m16319$$Nest$fgetinchatSoundRow(this.this$0)) goto L206;
            r52.setTextAndCheck(LocaleController.getString("InChatSound", R.string.InChatSound), r1.getBoolean("EnableInChatSound", true), true);
            return;
        L206:
            if (r22 != NotificationsSettingsActivity.m16303$$Nest$fgetcallsVibrateRow(this.this$0)) goto L210;
            r52.setTextAndCheck(LocaleController.getString("Vibrate", R.string.Vibrate), r1.getBoolean("EnableCallVibrate", true), true);
            return;
        L210:
            if (r22 != NotificationsSettingsActivity.m16290$$Nest$fgetaccountsAllRow(this.this$0)) goto L247;
            r52.setTextAndCheck(LocaleController.getString("AllAccounts", R.string.AllAccounts), MessagesController.getGlobalNotificationsSettings().getBoolean("AllAccounts", true), true);
            return;
        L247:
            return;
        L7:
            if (r3 != 2) goto L9;
            TextDetailSettingsCell r12 = (TextDetailSettingsCell) r21.itemView;
            r12.setMultilineDetail(true);
            if (r22 != NotificationsSettingsActivity.m16330$$Nest$fgetresetNotificationsRow(this.this$0)) goto L246;
            r12.setTextAndValue(LocaleController.getString("ResetAllNotifications", R.string.ResetAllNotifications), LocaleController.getString("UndoAllCustom", R.string.UndoAllCustom), false);
            return;
        L246:
            return;
        L9:
            if (r3 != 3) goto L11;
            NotificationsCheckCell r8 = (NotificationsCheckCell) r21.itemView;
            SharedPreferences r13 = MessagesController.getNotificationsSettings(NotificationsSettingsActivity.access$1000(this.this$0));
            int r32 = ConnectionsManager.getInstance(NotificationsSettingsActivity.access$1100(this.this$0)).getCurrentTime();
            ArrayList r122 = null;
            if (r22 != NotificationsSettingsActivity.m16327$$Nest$fgetprivateRow(this.this$0)) goto L64;
            String r4 = LocaleController.getString(R.string.NotificationsPrivateChats);
            ArrayList r132 = NotificationsSettingsActivity.m16312$$Nest$fgetexceptionUsers(this.this$0);
            int r14 = r13.getInt("EnableAll2", 0);
            int r15 = R.drawable.msg_openprofile;
        L62:
            int r19 = r14;
            ArrayList r142 = null;
            r122 = r132;
            int r133 = r19;
        L83:
            if (r133 >= r32) goto L85;
            boolean r16 = true;
        L87:
            if (r16 == false) goto L90;
        L88:
            int r7 = 0;
        L93:
            StringBuilder r53 = new StringBuilder();
            String r212 = r4;
            int r18 = r7;
            if (r22 != NotificationsSettingsActivity.m16328$$Nest$fgetreactionsRow(this.this$0)) goto L108;
            if (r133 <= 0) goto L98;
            r53.append(LocaleController.getString("NotificationsOff", R.string.NotificationsOff));
            boolean r123 = false;
        L143:
            int r11 = r15;
            if (r22 == NotificationsSettingsActivity.m16328$$Nest$fgetreactionsRow(this.this$0)) goto L146;
            boolean r152 = true;
        L147:
            r8.setTextAndValueAndIconAndCheck(r212, r53, r11, r123, r18, false, r152);
            return;
        L146:
            r152 = false;
            goto L147
        L98:
            if (r13.getBoolean("EnableReactionsMessages", true) == false) goto L101;
            r53.append(LocaleController.getString(R.string.NotificationReactionsMessages));
        L101:
            if (r13.getBoolean("EnableReactionsStories", true) == true) goto L103;
        L106:
            r123 = true;
            goto L143
        L103:
            if (r53.length() <= 0) goto L105;
            r53.append(", ");
        L105:
            r53.append(LocaleController.getString(R.string.NotificationReactionsStories));
            goto L106
        L108:
            if (r122 != null) goto L110;
        L131:
            if (r142 != null) goto L133;
        L142:
            r53.append(LocaleController.getString("TapToChange", R.string.TapToChange));
        L130:
            r123 = r16;
            goto L143
        L133:
            if (r142.isEmpty() == true) goto L142;
            if (r133 <= 0) goto L136;
            r53.append(LocaleController.getString("NotificationsOff", R.string.NotificationsOff));
        L138:
            if (r142.isEmpty() == true) goto L130;
            if (r13.contains("EnableAllStories") == true) goto L130;
            r53.append(", ");
            r53.append(LocaleController.formatPluralString("AutoException", r142.size(), new Object[0]));
            goto L130
        L136:
            r53.append(LocaleController.getString("NotificationsOn", R.string.NotificationsOn));
            goto L138
        L110:
            if (r122.isEmpty() == true) goto L131;
            if (r133 >= r32) goto L113;
            r16 = true;
        L114:
            if (r16 == false) goto L117;
            r53.append(LocaleController.getString("NotificationsOn", R.string.NotificationsOn));
        L121:
            if (r53.length() == 0) goto L123;
            r53.append(", ");
        L123:
            int r33 = r122.size();
            if (r22 == NotificationsSettingsActivity.m16335$$Nest$fgetstoriesRow(this.this$0)) goto L126;
        L129:
            r53.append(LocaleController.formatPluralString("Exception", r33, new Object[0]));
            goto L130
        L126:
            if (r13.contains("EnableAllStories") == true) goto L129;
            if (r142 == null) goto L129;
            r33 = r33 + r142.size();
            goto L129
        L117:
            if ((r133 - 31536000) < r32) goto L119;
            r53.append(LocaleController.getString("NotificationsOff", R.string.NotificationsOff));
            goto L121
        L119:
            r53.append(LocaleController.formatString("NotificationsOffUntil", R.string.NotificationsOffUntil, new Object[]{LocaleController.stringForMessageListDate(r133)}));
            goto L121
        L113:
            r16 = false;
            goto L114
        L90:
            if ((r133 - 31536000) >= r32) goto L88;
            r7 = 2;
            goto L93
        L85:
            r16 = false;
            goto L87
        L64:
            if (r22 != NotificationsSettingsActivity.m16313$$Nest$fgetgroupRow(this.this$0)) goto L66;
            r4 = LocaleController.getString(R.string.NotificationsGroups);
            r132 = NotificationsSettingsActivity.m16310$$Nest$fgetexceptionChats(this.this$0);
            r14 = r13.getInt("EnableGroup2", 0);
            r15 = R.drawable.msg_groups;
            goto L62
        L66:
            r133 = Integer.MAX_VALUE;
            if (r22 != NotificationsSettingsActivity.m16335$$Nest$fgetstoriesRow(this.this$0)) goto L73;
            r4 = LocaleController.getString(R.string.NotificationStories);
            r122 = NotificationsSettingsActivity.m16311$$Nest$fgetexceptionStories(this.this$0);
            r142 = NotificationsSettingsActivity.m16308$$Nest$fgetexceptionAutoStories(this.this$0);
            if (r13.getBoolean("EnableAllStories", false) == false) goto L71;
            r133 = 0;
        L71:
            r15 = R.drawable.msg_menu_stories;
            goto L83
        L73:
            if (r22 != NotificationsSettingsActivity.m16328$$Nest$fgetreactionsRow(this.this$0)) goto L82;
            r4 = LocaleController.getString(R.string.NotificationReactions);
            if (r13.getBoolean("EnableReactionsMessages", true) == false) goto L77;
        L80:
            int r143 = 0;
        L81:
            r15 = R.drawable.msg_reactions;
            r133 = r143;
            r142 = null;
            goto L83
        L77:
            if (r13.getBoolean("EnableReactionsStories", true) == true) goto L80;
            r143 = Integer.MAX_VALUE;
            goto L81
        L82:
            r4 = LocaleController.getString(R.string.NotificationsChannels);
            r132 = NotificationsSettingsActivity.m16309$$Nest$fgetexceptionChannels(this.this$0);
            r14 = r13.getInt("EnableChannel2", 0);
            r15 = R.drawable.msg_channel;
            goto L62
        L11:
            if (r3 != 5) goto L13;
            TextSettingsCell r17 = (TextSettingsCell) r21.itemView;
            SharedPreferences r34 = MessagesController.getNotificationsSettings(NotificationsSettingsActivity.access$1200(this.this$0));
            if (r22 != NotificationsSettingsActivity.m16292$$Nest$fgetaccountsListRow(this.this$0)) goto L23;
            r17.setTextAndValue(LocaleController.getString(R.string.AccountNotifications), LocaleController.formatString(R.string.AccountNotificationsCount, new Object[]{Integer.valueOf(AccountNotifications.getEnabledCount()), Integer.valueOf(AccountNotifications.getAccounts().size())}), false);
        L53:
            if (r22 == NotificationsSettingsActivity.m16292$$Nest$fgetaccountsListRow(this.this$0)) goto L55;
        L56:
            r5 = true;
        L57:
            r17.setEnabled(r5);
            return;
        L55:
            if (SharedConfig.showNotificationsForAllAccounts == false) goto L57;
        L23:
            if (r22 != NotificationsSettingsActivity.m16300$$Nest$fgetcallsRingtoneRow(this.this$0)) goto L29;
            String r35 = r34.getString("CallsRingtone", LocaleController.getString("DefaultRingtone", R.string.DefaultRingtone));
            if (r35.equals("NoSound") == false) goto L27;
            r35 = LocaleController.getString("NoSound", R.string.NoSound);
        L27:
            r17.setTextAndValue(LocaleController.getString("VoipSettingsRingtone", R.string.VoipSettingsRingtone), r35, NotificationsSettingsActivity.m16337$$Nest$fgetupdateRingtone(this.this$0), false);
            NotificationsSettingsActivity.m16340$$Nest$fputupdateRingtone(this.this$0, false);
            goto L53
        L29:
            if (r22 != NotificationsSettingsActivity.m16303$$Nest$fgetcallsVibrateRow(this.this$0)) goto L44;
            int r36 = r34.getInt("vibrate_calls", 0);
            if (r36 != 0) goto L33;
            r17.setTextAndValue(LocaleController.getString("Vibrate", R.string.Vibrate), LocaleController.getString("VibrationDefault", R.string.VibrationDefault), NotificationsSettingsActivity.m16338$$Nest$fgetupdateVibrate(this.this$0), true);
        L42:
            NotificationsSettingsActivity.m16341$$Nest$fputupdateVibrate(this.this$0, false);
            goto L53
        L33:
            if (r36 != 1) goto L35;
            r17.setTextAndValue(LocaleController.getString("Vibrate", R.string.Vibrate), LocaleController.getString("Short", R.string.Short), NotificationsSettingsActivity.m16338$$Nest$fgetupdateVibrate(this.this$0), true);
            goto L42
        L35:
            if (r36 != 2) goto L37;
            r17.setTextAndValue(LocaleController.getString("Vibrate", R.string.Vibrate), LocaleController.getString("VibrationDisabled", R.string.VibrationDisabled), NotificationsSettingsActivity.m16338$$Nest$fgetupdateVibrate(this.this$0), true);
            goto L42
        L37:
            if (r36 != 3) goto L40;
            r17.setTextAndValue(LocaleController.getString("Vibrate", R.string.Vibrate), LocaleController.getString("Long", R.string.Long), NotificationsSettingsActivity.m16338$$Nest$fgetupdateVibrate(this.this$0), true);
            goto L42
        L40:
            if (r36 != 4) goto L42;
            r17.setTextAndValue(LocaleController.getString("Vibrate", R.string.Vibrate), LocaleController.getString("OnlyIfSilent", R.string.OnlyIfSilent), NotificationsSettingsActivity.m16338$$Nest$fgetupdateVibrate(this.this$0), true);
            goto L42
        L44:
            if (r22 != NotificationsSettingsActivity.m16329$$Nest$fgetrepeatRow(this.this$0)) goto L53;
            int r37 = r34.getInt("repeat_messages", 60);
            if (r37 != 0) goto L48;
            String r38 = LocaleController.getString("RepeatNotificationsNever", R.string.RepeatNotificationsNever);
        L51:
            r17.setTextAndValue(LocaleController.getString("RepeatNotifications", R.string.RepeatNotifications), r38, NotificationsSettingsActivity.m16336$$Nest$fgetupdateRepeatNotifications(this.this$0), false);
            NotificationsSettingsActivity.m16339$$Nest$fputupdateRepeatNotifications(this.this$0, false);
            goto L53
        L48:
            if (r37 >= 60) goto L50;
            r38 = LocaleController.formatPluralString("Minutes", r37, new Object[0]);
            goto L51
        L50:
            r38 = LocaleController.formatPluralString("Hours", r37 / 60, new Object[0]);
            goto L51
        L13:
            if (r3 != 6) goto L249;
            TextInfoPrivacyCell r110 = (TextInfoPrivacyCell) r21.itemView;
            if (r22 != NotificationsSettingsActivity.m16291$$Nest$fgetaccountsInfoRow(this.this$0)) goto L245;
            r110.setText(LocaleController.getString("ShowNotificationsForInfo", R.string.ShowNotificationsForInfo));
            return;
        L245:
            return;
        L249:
            return;
        L213:
            HeaderCell r111 = (HeaderCell) r21.itemView;
            if (r22 != NotificationsSettingsActivity.m16321$$Nest$fgetnotificationsSectionRow(this.this$0)) goto L218;
            r111.setText(LocaleController.getString("NotificationsForChats", R.string.NotificationsForChats));
            return;
        L218:
            if (r22 != NotificationsSettingsActivity.m16316$$Nest$fgetinappSectionRow(this.this$0)) goto L222;
            r111.setText(LocaleController.getString("InAppNotifications", R.string.InAppNotifications));
            return;
        L222:
            if (r22 != NotificationsSettingsActivity.m16307$$Nest$fgeteventsSectionRow(this.this$0)) goto L226;
            r111.setText(LocaleController.getString("Events", R.string.Events));
            return;
        L226:
            if (r22 != NotificationsSettingsActivity.m16325$$Nest$fgetotherSectionRow(this.this$0)) goto L230;
            r111.setText(LocaleController.getString("NotificationsOther", R.string.NotificationsOther));
            return;
        L230:
            if (r22 != NotificationsSettingsActivity.m16333$$Nest$fgetresetSectionRow(this.this$0)) goto L234;
            r111.setText(LocaleController.getString("Reset", R.string.Reset));
            return;
        L234:
            if (r22 != NotificationsSettingsActivity.m16302$$Nest$fgetcallsSectionRow(this.this$0)) goto L238;
            r111.setText(LocaleController.getString("VoipNotificationSettings", R.string.VoipNotificationSettings));
            return;
        L238:
            if (r22 != NotificationsSettingsActivity.m16297$$Nest$fgetbadgeNumberSection(this.this$0)) goto L242;
            r111.setText(LocaleController.getString("BadgeNumber", R.string.BadgeNumber));
            return;
        L242:
            if (r22 != NotificationsSettingsActivity.m16293$$Nest$fgetaccountsSectionRow(this.this$0)) goto L248;
            r111.setText(LocaleController.getString("ShowNotificationsFor", R.string.ShowNotificationsFor));
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            if (r8 != 0) goto L4;
            View r72 = new HeaderCell(this.mContext, NotificationsSettingsActivity.access$000(this.this$0));
        L21:
            return new RecyclerListView.Holder(r72);
        L4:
            if (r8 != 1) goto L6;
            r72 = new TextCheckCell(this.mContext, NotificationsSettingsActivity.access$100(this.this$0));
            goto L21
        L6:
            if (r8 != 2) goto L8;
            r72 = new TextDetailSettingsCell(this.mContext);
            goto L21
        L8:
            if (r8 != 3) goto L10;
            r72 = new NotificationsCheckCell(this.mContext, 21, 64, true, NotificationsSettingsActivity.access$200(this.this$0));
            goto L21
        L10:
            if (r8 != 4) goto L12;
            r72 = new ShadowSectionCell(this.mContext, NotificationsSettingsActivity.access$300(this.this$0));
            goto L21
        L12:
            if (r8 == 5) goto L14;
            r72 = new TextInfoPrivacyCell(this.mContext, NotificationsSettingsActivity.access$500(this.this$0));
            goto L21
        L14:
            TextSettingsCell r82 = new TextSettingsCell(this.mContext, NotificationsSettingsActivity.access$400(this.this$0));
            r82.setCanDisable(true);
            r72 = r82;
            goto L21
        }
    }

    public static class NotificationException {
        public boolean auto;
        public long did;
        public boolean hasCustom;
        public int muteUntil;
        public int notify;
        public boolean story;

        public NotificationException() {
        }
    }

    public static /* synthetic */ void $r8$lambda$2cG0USEySZIKyLNkAaHFqsVue7E(NotificationsSettingsActivity r0, ArrayList r1, Runnable r2) {
        r0.lambda$loadExceptions$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$3x9pmBJjCdSRiKy15x0TKplUGo0(NotificationsSettingsActivity r0, int r1) {
        r0.lambda$createView$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$7ZNYj78bykHM0WIbpkrRbdvz1UY(NotificationsSettingsActivity r0, int r1, DialogInterface r2, int r3) {
        r0.lambda$createView$9(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Cqg0arv2vofF3OmxQVOd9ZKvEmA(NotificationsSettingsActivity r0, ArrayList r1, ArrayList r2, ArrayList r3, ArrayList r4, ArrayList r5, ArrayList r6, ArrayList r7, ArrayList r8, Runnable r9) {
        r0.lambda$loadExceptions$1(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$Izc6KvOLQKZxtNX5UDORiVBlHNM(NotificationsSettingsActivity r0, View r1, int r2, float r3, float r4) {
        r0.lambda$createView$10(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$NLO7wYLEVKU9wTAFL-ch-Llf-Sc, reason: not valid java name */
    public static /* synthetic */ double m16286$r8$lambda$NLO7wYLEVKU9wTAFLchLlfSc(TLRPC.TL_topPeer r2) {
        return r2.rating;
    }

    public static /* synthetic */ void $r8$lambda$PFhlxTyrCcsP_isWPbKXuM89fN8(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$RGbZLVYB4RpCo_ExmRkGeVhp1HA(NotificationsSettingsActivity r0) {
        r0.lambda$createView$4();
    }

    /* renamed from: $r8$lambda$Ubk8Cz97Kvq92rbkmMnOLC-JpbI, reason: not valid java name */
    public static /* synthetic */ void m16287$r8$lambda$Ubk8Cz97Kvq92rbkmMnOLCJpbI(NotificationsSettingsActivity r0, int r1, boolean r2, NotificationsCheckCell r3, int r4) {
        r0.lambda$createView$3(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$clP3ez816ifWJv5YoeN-x7aZXFY, reason: not valid java name */
    public static /* synthetic */ void m16288$r8$lambda$clP3ez816ifWJv5YoeNx7aZXFY(NotificationsSettingsActivity r0, ArrayList r1, ArrayList r2, AlertDialog r3, int r4) {
        r0.lambda$showExceptionsAlert$11(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$eR7G9sz8efY4KH1VnVkrxbfRuPc(NotificationsSettingsActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createView$5(r1, r2);
    }

    /* renamed from: $r8$lambda$wuTfkg-xIw3u-Q0fwlwVuE7ykYw, reason: not valid java name */
    public static /* synthetic */ void m16289$r8$lambda$wuTfkgxIw3uQ0fwlwVuE7ykYw(NotificationsSettingsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$z8728ZbQxis9p3M0Wak40C7BT78(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: -$$Nest$fgetaccountsAllRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16290$$Nest$fgetaccountsAllRow(NotificationsSettingsActivity r0) {
        return r0.accountsAllRow;
    }

    /* renamed from: -$$Nest$fgetaccountsInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16291$$Nest$fgetaccountsInfoRow(NotificationsSettingsActivity r0) {
        return r0.accountsInfoRow;
    }

    /* renamed from: -$$Nest$fgetaccountsListRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16292$$Nest$fgetaccountsListRow(NotificationsSettingsActivity r0) {
        return r0.accountsListRow;
    }

    /* renamed from: -$$Nest$fgetaccountsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16293$$Nest$fgetaccountsSectionRow(NotificationsSettingsActivity r0) {
        return r0.accountsSectionRow;
    }

    /* renamed from: -$$Nest$fgetandroidAutoAlertRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16294$$Nest$fgetandroidAutoAlertRow(NotificationsSettingsActivity r0) {
        return r0.androidAutoAlertRow;
    }

    /* renamed from: -$$Nest$fgetbadgeNumberMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16295$$Nest$fgetbadgeNumberMessagesRow(NotificationsSettingsActivity r0) {
        return r0.badgeNumberMessagesRow;
    }

    /* renamed from: -$$Nest$fgetbadgeNumberMutedRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16296$$Nest$fgetbadgeNumberMutedRow(NotificationsSettingsActivity r0) {
        return r0.badgeNumberMutedRow;
    }

    /* renamed from: -$$Nest$fgetbadgeNumberSection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16297$$Nest$fgetbadgeNumberSection(NotificationsSettingsActivity r0) {
        return r0.badgeNumberSection;
    }

    /* renamed from: -$$Nest$fgetbadgeNumberSection2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16298$$Nest$fgetbadgeNumberSection2Row(NotificationsSettingsActivity r0) {
        return r0.badgeNumberSection2Row;
    }

    /* renamed from: -$$Nest$fgetbadgeNumberShowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16299$$Nest$fgetbadgeNumberShowRow(NotificationsSettingsActivity r0) {
        return r0.badgeNumberShowRow;
    }

    /* renamed from: -$$Nest$fgetcallsRingtoneRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16300$$Nest$fgetcallsRingtoneRow(NotificationsSettingsActivity r0) {
        return r0.callsRingtoneRow;
    }

    /* renamed from: -$$Nest$fgetcallsSection2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16301$$Nest$fgetcallsSection2Row(NotificationsSettingsActivity r0) {
        return r0.callsSection2Row;
    }

    /* renamed from: -$$Nest$fgetcallsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16302$$Nest$fgetcallsSectionRow(NotificationsSettingsActivity r0) {
        return r0.callsSectionRow;
    }

    /* renamed from: -$$Nest$fgetcallsVibrateRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16303$$Nest$fgetcallsVibrateRow(NotificationsSettingsActivity r0) {
        return r0.callsVibrateRow;
    }

    /* renamed from: -$$Nest$fgetchannelsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16304$$Nest$fgetchannelsRow(NotificationsSettingsActivity r0) {
        return r0.channelsRow;
    }

    /* renamed from: -$$Nest$fgetcontactJoinedRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16305$$Nest$fgetcontactJoinedRow(NotificationsSettingsActivity r0) {
        return r0.contactJoinedRow;
    }

    /* renamed from: -$$Nest$fgeteventsSection2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16306$$Nest$fgeteventsSection2Row(NotificationsSettingsActivity r0) {
        return r0.eventsSection2Row;
    }

    /* renamed from: -$$Nest$fgeteventsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16307$$Nest$fgeteventsSectionRow(NotificationsSettingsActivity r0) {
        return r0.eventsSectionRow;
    }

    /* renamed from: -$$Nest$fgetexceptionAutoStories, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16308$$Nest$fgetexceptionAutoStories(NotificationsSettingsActivity r0) {
        return r0.exceptionAutoStories;
    }

    /* renamed from: -$$Nest$fgetexceptionChannels, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16309$$Nest$fgetexceptionChannels(NotificationsSettingsActivity r0) {
        return r0.exceptionChannels;
    }

    /* renamed from: -$$Nest$fgetexceptionChats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16310$$Nest$fgetexceptionChats(NotificationsSettingsActivity r0) {
        return r0.exceptionChats;
    }

    /* renamed from: -$$Nest$fgetexceptionStories, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16311$$Nest$fgetexceptionStories(NotificationsSettingsActivity r0) {
        return r0.exceptionStories;
    }

    /* renamed from: -$$Nest$fgetexceptionUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16312$$Nest$fgetexceptionUsers(NotificationsSettingsActivity r0) {
        return r0.exceptionUsers;
    }

    /* renamed from: -$$Nest$fgetgroupRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16313$$Nest$fgetgroupRow(NotificationsSettingsActivity r0) {
        return r0.groupRow;
    }

    /* renamed from: -$$Nest$fgetinappPreviewRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16314$$Nest$fgetinappPreviewRow(NotificationsSettingsActivity r0) {
        return r0.inappPreviewRow;
    }

    /* renamed from: -$$Nest$fgetinappPriorityRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16315$$Nest$fgetinappPriorityRow(NotificationsSettingsActivity r0) {
        return r0.inappPriorityRow;
    }

    /* renamed from: -$$Nest$fgetinappSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16316$$Nest$fgetinappSectionRow(NotificationsSettingsActivity r0) {
        return r0.inappSectionRow;
    }

    /* renamed from: -$$Nest$fgetinappSoundRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16317$$Nest$fgetinappSoundRow(NotificationsSettingsActivity r0) {
        return r0.inappSoundRow;
    }

    /* renamed from: -$$Nest$fgetinappVibrateRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16318$$Nest$fgetinappVibrateRow(NotificationsSettingsActivity r0) {
        return r0.inappVibrateRow;
    }

    /* renamed from: -$$Nest$fgetinchatSoundRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16319$$Nest$fgetinchatSoundRow(NotificationsSettingsActivity r0) {
        return r0.inchatSoundRow;
    }

    /* renamed from: -$$Nest$fgetnotificationsSection2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16320$$Nest$fgetnotificationsSection2Row(NotificationsSettingsActivity r0) {
        return r0.notificationsSection2Row;
    }

    /* renamed from: -$$Nest$fgetnotificationsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16321$$Nest$fgetnotificationsSectionRow(NotificationsSettingsActivity r0) {
        return r0.notificationsSectionRow;
    }

    /* renamed from: -$$Nest$fgetnotificationsServiceConnectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16322$$Nest$fgetnotificationsServiceConnectionRow(NotificationsSettingsActivity r0) {
        return r0.notificationsServiceConnectionRow;
    }

    /* renamed from: -$$Nest$fgetnotificationsServiceRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16323$$Nest$fgetnotificationsServiceRow(NotificationsSettingsActivity r0) {
        return r0.notificationsServiceRow;
    }

    /* renamed from: -$$Nest$fgetotherSection2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16324$$Nest$fgetotherSection2Row(NotificationsSettingsActivity r0) {
        return r0.otherSection2Row;
    }

    /* renamed from: -$$Nest$fgetotherSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16325$$Nest$fgetotherSectionRow(NotificationsSettingsActivity r0) {
        return r0.otherSectionRow;
    }

    /* renamed from: -$$Nest$fgetpinnedMessageRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16326$$Nest$fgetpinnedMessageRow(NotificationsSettingsActivity r0) {
        return r0.pinnedMessageRow;
    }

    /* renamed from: -$$Nest$fgetprivateRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16327$$Nest$fgetprivateRow(NotificationsSettingsActivity r0) {
        return r0.privateRow;
    }

    /* renamed from: -$$Nest$fgetreactionsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16328$$Nest$fgetreactionsRow(NotificationsSettingsActivity r0) {
        return r0.reactionsRow;
    }

    /* renamed from: -$$Nest$fgetrepeatRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16329$$Nest$fgetrepeatRow(NotificationsSettingsActivity r0) {
        return r0.repeatRow;
    }

    /* renamed from: -$$Nest$fgetresetNotificationsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16330$$Nest$fgetresetNotificationsRow(NotificationsSettingsActivity r0) {
        return r0.resetNotificationsRow;
    }

    /* renamed from: -$$Nest$fgetresetNotificationsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16331$$Nest$fgetresetNotificationsSectionRow(NotificationsSettingsActivity r0) {
        return r0.resetNotificationsSectionRow;
    }

    /* renamed from: -$$Nest$fgetresetSection2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16332$$Nest$fgetresetSection2Row(NotificationsSettingsActivity r0) {
        return r0.resetSection2Row;
    }

    /* renamed from: -$$Nest$fgetresetSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16333$$Nest$fgetresetSectionRow(NotificationsSettingsActivity r0) {
        return r0.resetSectionRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16334$$Nest$fgetrowCount(NotificationsSettingsActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetstoriesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16335$$Nest$fgetstoriesRow(NotificationsSettingsActivity r0) {
        return r0.storiesRow;
    }

    /* renamed from: -$$Nest$fgetupdateRepeatNotifications, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16336$$Nest$fgetupdateRepeatNotifications(NotificationsSettingsActivity r0) {
        return r0.updateRepeatNotifications;
    }

    /* renamed from: -$$Nest$fgetupdateRingtone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16337$$Nest$fgetupdateRingtone(NotificationsSettingsActivity r0) {
        return r0.updateRingtone;
    }

    /* renamed from: -$$Nest$fgetupdateVibrate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16338$$Nest$fgetupdateVibrate(NotificationsSettingsActivity r0) {
        return r0.updateVibrate;
    }

    /* renamed from: -$$Nest$fputupdateRepeatNotifications, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16339$$Nest$fputupdateRepeatNotifications(NotificationsSettingsActivity r0, boolean r1) {
        r0.updateRepeatNotifications = r1;
    }

    /* renamed from: -$$Nest$fputupdateRingtone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16340$$Nest$fputupdateRingtone(NotificationsSettingsActivity r0, boolean r1) {
        r0.updateRingtone = r1;
    }

    /* renamed from: -$$Nest$fputupdateVibrate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16341$$Nest$fputupdateVibrate(NotificationsSettingsActivity r0, boolean r1) {
        r0.updateVibrate = r1;
    }

    public NotificationsSettingsActivity() {
        this.reseting = false;
        this.exceptionUsers = null;
        this.exceptionChats = null;
        this.exceptionChannels = null;
        this.exceptionStories = null;
        this.exceptionAutoStories = null;
        this.rowCount = 0;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(NotificationsSettingsActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(NotificationsSettingsActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$1000(NotificationsSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1100(NotificationsSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1200(NotificationsSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(NotificationsSettingsActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(NotificationsSettingsActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$400(NotificationsSettingsActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$500(NotificationsSettingsActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$600(NotificationsSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ NotificationsController access$700(NotificationsSettingsActivity r0) {
        return r0.getNotificationsController();
    }

    public static /* synthetic */ NotificationsController access$800(NotificationsSettingsActivity r0) {
        return r0.getNotificationsController();
    }

    public static /* synthetic */ NotificationsController access$900(NotificationsSettingsActivity r0) {
        return r0.getNotificationsController();
    }

    private /* synthetic */ void lambda$createView$10(View r20, int r21, float r22, float r23) {
        if (getParentActivity() == null) goto L141;
        int r0 = this.privateRow;
        ArrayList<NotificationException> r7 = null;
        String r72 = null;
        Parcelable r73 = null;
        r73 = null;
        boolean r9 = false;
        if (r21 == r0) goto L103;
        if (r21 == this.groupRow) goto L103;
        if (r21 == this.channelsRow) goto L103;
        if (r21 == this.storiesRow) goto L103;
        if (r21 == this.reactionsRow) goto L103;
        if (r21 != this.callsRingtoneRow) goto L36;
        SharedPreferences r02 = MessagesController.getNotificationsSettings(this.currentAccount);     // Catch: Exception -> L21
        Intent r2 = new Intent("android.intent.action.RINGTONE_PICKER");     // Catch: Exception -> L21
        r2.putExtra("android.intent.extra.ringtone.TYPE", 1);     // Catch: Exception -> L21
        r2.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", true);     // Catch: Exception -> L21
        r2.putExtra("android.intent.extra.ringtone.SHOW_SILENT", true);     // Catch: Exception -> L21
        r2.putExtra("android.intent.extra.ringtone.DEFAULT_URI", RingtoneManager.getDefaultUri(1));     // Catch: Exception -> L21
        Uri r3 = Settings.System.DEFAULT_RINGTONE_URI;     // Catch: Exception -> L21
        if (r3 == null) goto L23;
        String r4 = r3.getPath();     // Catch: Exception -> L21
    L24:
        String r03 = r02.getString("CallsRingtonePath", r4);     // Catch: Exception -> L21
        if (r03 != null) goto L27;
    L32:
        r2.putExtra("android.intent.extra.ringtone.EXISTING_URI", r73);     // Catch: Exception -> L21
        startActivityForResult(r2, r21);     // Catch: Exception -> L21
    L135:
        if ((r20 instanceof TextCheckCell) == false) goto L140;
        ((TextCheckCell) r20).setChecked(!r9);
        return;
    L140:
        return;
    L27:
        if (r03.equals("NoSound") == true) goto L32;
        if (r03.equals(r4) == false) goto L31;
        r73 = r3;
        goto L32
    L31:
        r73 = Uri.parse(r03);     // Catch: Exception -> L21
        goto L32
    L23:
        r4 = null;
    L21:
        e = move-exception;
        FileLog.e(e);
        goto L135
    L36:
        if (r21 != this.resetNotificationsRow) goto L41;
        AlertDialog.Builder r04 = new AlertDialog.Builder(getParentActivity());
        r04.setTitle(LocaleController.getString("ResetNotificationsAlertTitle", R.string.ResetNotificationsAlertTitle));
        r04.setMessage(LocaleController.getString("ResetNotificationsAlert", R.string.ResetNotificationsAlert));
        r04.setPositiveButton(LocaleController.getString("Reset", R.string.Reset), new NotificationsSettingsActivity$$ExternalSyntheticLambda5(this));
        r04.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        AlertDialog r05 = r04.create();
        showDialog(r05);
        TextView r06 = (TextView) r05.getButton(-1);
        if (r06 == null) goto L135;
        r06.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        goto L135
    L41:
        if (r21 != this.inappSoundRow) goto L44;
        SharedPreferences r07 = MessagesController.getNotificationsSettings(this.currentAccount);
        SharedPreferences.Editor r1 = r07.edit();
        r9 = r07.getBoolean("EnableInAppSounds", true);
        r1.putBoolean("EnableInAppSounds", !r9);
        r1.apply();
        goto L135
    L44:
        if (r21 != this.inappVibrateRow) goto L47;
        SharedPreferences r08 = MessagesController.getNotificationsSettings(this.currentAccount);
        SharedPreferences.Editor r12 = r08.edit();
        r9 = r08.getBoolean("EnableInAppVibrate", true);
        r12.putBoolean("EnableInAppVibrate", !r9);
        r12.apply();
        goto L135
    L47:
        if (r21 != this.inappPreviewRow) goto L50;
        SharedPreferences r09 = MessagesController.getNotificationsSettings(this.currentAccount);
        SharedPreferences.Editor r13 = r09.edit();
        r9 = r09.getBoolean("EnableInAppPreview", true);
        r13.putBoolean("EnableInAppPreview", !r9);
        r13.apply();
        goto L135
    L50:
        if (r21 != this.inchatSoundRow) goto L53;
        SharedPreferences r010 = MessagesController.getNotificationsSettings(this.currentAccount);
        SharedPreferences.Editor r24 = r010.edit();
        r9 = r010.getBoolean("EnableInChatSound", true);
        r24.putBoolean("EnableInChatSound", !r9);
        r24.apply();
        getNotificationsController().setInChatSoundEnabled(!r9);
        goto L135
    L53:
        if (r21 != this.inappPriorityRow) goto L56;
        SharedPreferences r011 = MessagesController.getNotificationsSettings(this.currentAccount);
        SharedPreferences.Editor r14 = r011.edit();
        r9 = r011.getBoolean("EnableInAppPopup", true);
        r14.putBoolean("EnableInAppPopup", !r9);
        r14.apply();
        goto L135
    L56:
        if (r21 != this.contactJoinedRow) goto L59;
        SharedPreferences r012 = MessagesController.getNotificationsSettings(this.currentAccount);
        SharedPreferences.Editor r25 = r012.edit();
        r9 = r012.getBoolean("EnableContactJoined", true);
        MessagesController.getInstance(this.currentAccount).enableJoined = !r9;
        r25.putBoolean("EnableContactJoined", !r9);
        r25.apply();
        TL_account.setContactSignUpNotification r013 = new TL_account.setContactSignUpNotification();
        r013.silent = r9;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r013, new NotificationsSettingsActivity$$ExternalSyntheticLambda6());
        goto L135
    L59:
        if (r21 != this.pinnedMessageRow) goto L62;
        SharedPreferences r014 = MessagesController.getNotificationsSettings(this.currentAccount);
        SharedPreferences.Editor r15 = r014.edit();
        r9 = r014.getBoolean("PinnedMessages", true);
        r15.putBoolean("PinnedMessages", !r9);
        r15.apply();
        goto L135
    L62:
        if (r21 != this.androidAutoAlertRow) goto L65;
        SharedPreferences r015 = MessagesController.getNotificationsSettings(this.currentAccount);
        SharedPreferences.Editor r16 = r015.edit();
        r9 = r015.getBoolean("EnableAutoNotifications", false);
        r16.putBoolean("EnableAutoNotifications", !r9);
        r16.apply();
        goto L135
    L65:
        if (r21 != this.badgeNumberShowRow) goto L68;
        SharedPreferences.Editor r016 = MessagesController.getNotificationsSettings(this.currentAccount).edit();
        r9 = getNotificationsController().showBadgeNumber;
        getNotificationsController().showBadgeNumber = !r9;
        r016.putBoolean("badgeNumber", getNotificationsController().showBadgeNumber);
        r016.apply();
        getNotificationsController().updateBadge();
        goto L135
    L68:
        if (r21 != this.badgeNumberMutedRow) goto L71;
        SharedPreferences.Editor r017 = MessagesController.getNotificationsSettings(this.currentAccount).edit();
        r9 = getNotificationsController().showBadgeMuted;
        getNotificationsController().showBadgeMuted = !r9;
        r017.putBoolean("badgeNumberMuted", getNotificationsController().showBadgeMuted);
        r017.apply();
        getNotificationsController().updateBadge();
        getMessagesStorage().updateMutedDialogsFiltersCounters();
        goto L135
    L71:
        if (r21 != this.badgeNumberMessagesRow) goto L74;
        SharedPreferences.Editor r018 = MessagesController.getNotificationsSettings(this.currentAccount).edit();
        r9 = getNotificationsController().showBadgeMessages;
        getNotificationsController().showBadgeMessages = !r9;
        r018.putBoolean("badgeNumberMessages", getNotificationsController().showBadgeMessages);
        r018.apply();
        getNotificationsController().updateBadge();
        goto L135
    L74:
        if (r21 != this.notificationsServiceConnectionRow) goto L81;
        SharedPreferences r019 = MessagesController.getNotificationsSettings(this.currentAccount);
        boolean r26 = r019.getBoolean("pushConnection", getMessagesController().backgroundConnection);
        SharedPreferences.Editor r020 = r019.edit();
        r020.putBoolean("pushConnection", !r26);
        r020.apply();
        int r021 = this.currentAccount;
        if (r26 == true) goto L78;
        ConnectionsManager.getInstance(r021).setPushConnectionEnabled(true);
    L79:
        r9 = r26;
        goto L135
    L78:
        ConnectionsManager.getInstance(r021).setPushConnectionEnabled(false);
        goto L79
    L81:
        if (r21 != this.accountsAllRow) goto L84;
        SharedPreferences r022 = MessagesController.getGlobalNotificationsSettings();
        r9 = r022.getBoolean("AllAccounts", true);
        SharedPreferences.Editor r023 = r022.edit();
        r023.putBoolean("AllAccounts", !r9);
        r023.apply();
        SharedConfig.showNotificationsForAllAccounts = !r9;
        AccountNotifications.applyAll();
        this.adapter.notifyItemChanged(this.accountsListRow);
        goto L135
    L84:
        if (r21 != this.accountsListRow) goto L89;
        if (SharedConfig.showNotificationsForAllAccounts == false) goto L135;
        presentFragment(new AccountNotificationsActivity());
        goto L135
    L89:
        if (r21 != this.notificationsServiceRow) goto L92;
        SharedPreferences r024 = MessagesController.getNotificationsSettings(this.currentAccount);
        r9 = r024.getBoolean("pushService", getMessagesController().keepAliveService);
        SharedPreferences.Editor r025 = r024.edit();
        r025.putBoolean("pushService", !r9);
        r025.apply();
        ApplicationLoader.startPushService();
        goto L135
    L92:
        if (r21 != this.callsVibrateRow) goto L101;
        if (getParentActivity() != null) goto L97;
        return;
    L97:
        if (r21 != this.callsVibrateRow) goto L99;
        r72 = "vibrate_calls";
    L99:
        showDialog(AlertsCreator.createVibrationSelectDialog(getParentActivity(), 0, 0, r72, new NotificationsSettingsActivity$$ExternalSyntheticLambda7(this, r21)));
        goto L135
    L101:
        if (r21 != this.repeatRow) goto L135;
        AlertDialog.Builder r026 = new AlertDialog.Builder(getParentActivity());
        r026.setTitle(LocaleController.getString("RepeatNotifications", R.string.RepeatNotifications));
        r026.setItems(new CharSequence[]{LocaleController.getString("RepeatDisabled", R.string.RepeatDisabled), LocaleController.formatPluralString("Minutes", 5, new Object[0]), LocaleController.formatPluralString("Minutes", 10, new Object[0]), LocaleController.formatPluralString("Minutes", 30, new Object[0]), LocaleController.formatPluralString("Hours", 1, new Object[0]), LocaleController.formatPluralString("Hours", 2, new Object[0]), LocaleController.formatPluralString("Hours", 4, new Object[0])}, new NotificationsSettingsActivity$$ExternalSyntheticLambda8(this, r21));
        r026.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(r026.create());
    L103:
        if (r21 != r0) goto L106;
        r7 = this.exceptionUsers;
        ArrayList<NotificationException> r027 = null;
        boolean r32 = getNotificationsController().isGlobalNotificationsEnabled(1);
        int r27 = 1;
    L120:
        if (r7 != null) goto L123;
        if (r27 == 4) goto L123;
        return;
    L123:
        NotificationsCheckCell r42 = (NotificationsCheckCell) r20;
        if (LocaleController.isRTL == false) goto L128;
        if (r22 > AndroidUtilities.dp(76.0f)) goto L128;
    L131:
        showExceptionsAlert(r21, new NotificationsSettingsActivity$$ExternalSyntheticLambda4(this, r27, r32, r42, r21));
    L133:
        r9 = r32;
    L128:
        if (LocaleController.isRTL == false) goto L130;
    L132:
        presentFragment(new NotificationsCustomSettingsActivity(r27, r7, r027));
        goto L133
    L130:
        if (r22 < (r20.getMeasuredWidth() - AndroidUtilities.dp(76.0f))) goto L132;
    L106:
        if (r21 != this.groupRow) goto L109;
        r7 = this.exceptionChats;
        r027 = null;
        r32 = getNotificationsController().isGlobalNotificationsEnabled(0);
        r27 = 0;
        goto L120
    L109:
        if (r21 != this.storiesRow) goto L112;
        r7 = this.exceptionStories;
        r027 = this.exceptionAutoStories;
        r32 = getNotificationsSettings().getBoolean("EnableAllStories", false);
        r27 = 3;
        goto L120
    L112:
        if (r21 == this.reactionsRow) goto L114;
        r7 = this.exceptionChannels;
        r027 = null;
        r32 = getNotificationsController().isGlobalNotificationsEnabled(2);
        r27 = 2;
        goto L120
    L114:
        if (getNotificationsSettings().getBoolean("EnableReactionsMessages", true) == false) goto L116;
    L117:
        r9 = true;
    L118:
        r27 = 4;
        r027 = null;
        r32 = r9;
        goto L120
    L116:
        if (getNotificationsSettings().getBoolean("EnableReactionsStories", true) == false) goto L118;
    }

    private /* synthetic */ void lambda$createView$3(int r6, boolean r7, NotificationsCheckCell r8, int r9) {
        if (r6 != 3) goto L10;
        SharedPreferences.Editor r0 = getNotificationsSettings().edit();
        if (r7 == false) goto L7;
        r0.remove("EnableAllStories");
    L8:
        r0.apply();
        getNotificationsController().updateServerNotificationsSettings(r6);
    L24:
        r8.setChecked(!r7, 0);
        this.adapter.notifyItemChanged(r9);
        return;
    L7:
        r0.putBoolean("EnableAllStories", true);
        goto L8
    L10:
        if (r6 != 4) goto L12;
    L19:
        SharedPreferences.Editor r02 = getNotificationsSettings().edit();
        if (r7 == false) goto L22;
        r02.putBoolean("EnableReactionsMessages", false);
        r02.putBoolean("EnableReactionsStories", false);
    L23:
        r02.apply();
        getNotificationsController().updateServerNotificationsSettings(r6);
        getNotificationsController().deleteNotificationChannelGlobal(r6);
        goto L24
    L22:
        r02.putBoolean("EnableReactionsMessages", true);
        r02.putBoolean("EnableReactionsStories", true);
        goto L23
    L12:
        if (r6 == 5) goto L19;
        NotificationsController r03 = getNotificationsController();
        if (r7 == true) goto L17;
        int r3 = 0;
    L18:
        r03.setGlobalNotificationsEnabled(r6, r3);
        goto L24
    L17:
        r3 = Integer.MAX_VALUE;
        goto L18
    }

    private /* synthetic */ void lambda$createView$4() {
        getMessagesController().enableJoined = true;
        this.reseting = false;
        SharedPreferences.Editor r1 = MessagesController.getNotificationsSettings(this.currentAccount).edit();
        r1.clear();
        r1.apply();
        this.exceptionChats.clear();
        this.exceptionUsers.clear();
        this.adapter.notifyDataSetChanged();
        if (getParentActivity() == null) goto L5;
        Toast.makeText(getParentActivity(), LocaleController.getString("ResetNotificationsText", R.string.ResetNotificationsText), 0).show();
    L5:
        getMessagesStorage().updateMutedDialogsFiltersCounters();
    }

    private /* synthetic */ void lambda$createView$5(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new NotificationsSettingsActivity$$ExternalSyntheticLambda12(this));
    }

    private /* synthetic */ void lambda$createView$6(AlertDialog r2, int r3) {
        if (this.reseting == false) goto L5;
        return;
    L5:
        this.reseting = true;
        TL_account.resetNotifySettings r22 = new TL_account.resetNotifySettings();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r22, new NotificationsSettingsActivity$$ExternalSyntheticLambda11(this));
    }

    private /* synthetic */ void lambda$createView$8(int r2) {
        this.updateVibrate = true;
        this.adapter.notifyItemChanged(r2);
    }

    private /* synthetic */ void lambda$createView$9(int r3, DialogInterface r4, int r5) {
        int r42 = 5;
        if (r5 != 1) goto L6;
    L20:
        MessagesController.getNotificationsSettings(this.currentAccount).edit().putInt("repeat_messages", r42).apply();
        this.updateRepeatNotifications = true;
        this.adapter.notifyItemChanged(r3);
        return;
    L6:
        if (r5 != 2) goto L9;
        r42 = 10;
        goto L20
    L9:
        if (r5 != 3) goto L12;
        r42 = 30;
        goto L20
    L12:
        if (r5 != 4) goto L14;
        r42 = 60;
        goto L20
    L14:
        if (r5 != 5) goto L17;
        r42 = 120;
        goto L20
    L17:
        if (r5 != 6) goto L19;
        r42 = 240;
        goto L20
    L19:
        r42 = 0;
        goto L20
    }

    private /* synthetic */ void lambda$loadExceptions$1(ArrayList r3, ArrayList r4, ArrayList r5, ArrayList r6, ArrayList r7, ArrayList r8, ArrayList r9, ArrayList r10, Runnable r11) {
        MessagesController.getInstance(this.currentAccount).putUsers(r3, true);
        MessagesController.getInstance(this.currentAccount).putChats(r4, true);
        MessagesController.getInstance(this.currentAccount).putEncryptedChats(r5, true);
        this.exceptionUsers = r6;
        this.exceptionChats = r7;
        this.exceptionChannels = r8;
        this.exceptionStories = r9;
        this.exceptionAutoStories = r10;
        ListAdapter r32 = this.adapter;
        if (r32 == null) goto L5;
        r32.notifyItemChanged(this.privateRow);
        this.adapter.notifyItemChanged(this.groupRow);
        this.adapter.notifyItemChanged(this.channelsRow);
        this.adapter.notifyItemChanged(this.storiesRow);
    L5:
        if (r11 == null) goto L8;
        r11.run();
        return;
    }

    private /* synthetic */ void lambda$loadExceptions$2(ArrayList r29, Runnable r30) {
        ArrayList r5 = new ArrayList();
        ArrayList r6 = new ArrayList();
        ArrayList r7 = new ArrayList();
        ArrayList r8 = new ArrayList();
        ArrayList r9 = new ArrayList();
        LongSparseArray r2 = new LongSparseArray();
        ArrayList<Long> r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList r10 = new ArrayList();
        ArrayList<TLRPC.User> r11 = new ArrayList();
        ArrayList<TLRPC.Chat> r12 = new ArrayList();
        ArrayList<TLRPC.EncryptedChat> r13 = new ArrayList();
        long r14 = UserConfig.getInstance(this.currentAccount).clientUserId;
        SharedPreferences r142 = MessagesController.getNotificationsSettings(this.currentAccount);
        Map<String, ?> r15 = r142.getAll();
        Iterator<Map.Entry<String, ?>> r18 = r15.entrySet().iterator();
    L3:
        ArrayList<TLRPC.Chat> r22 = r12;
        if (r18.hasNext() == false) goto L60;
        Map.Entry<String, ?> r19 = r18.next();
        String r122 = r19.getKey();
        ArrayList<TLRPC.User> r23 = r11;
        if (r122.startsWith(NotificationsSettingsFacade.PROPERTY_NOTIFY) == false) goto L58;
        ArrayList<TLRPC.EncryptedChat> r24 = r13;
        String r112 = r122.replace(NotificationsSettingsFacade.PROPERTY_NOTIFY, _UrlKt.FRAGMENT_ENCODE_SET);
        if (r112.contains("_") == true) goto L9;
        Long r123 = Utilities.parseLong(r112);
        ArrayList r132 = r8;
        ArrayList r25 = r9;
        long r82 = r123.longValue();
        if (r82 == 0) goto L57;
        if (r82 == r14) goto L57;
        ArrayList r26 = r132;
        NotificationException r133 = new NotificationException();
        r133.did = r82;
        ArrayList r27 = r6;
        r133.hasCustom = r142.getBoolean(NotificationsSettingsFacade.PROPERTY_CUSTOM + r82, false);
        int r0 = ((Integer) r19.getValue()).intValue();
        r133.notify = r0;
        if (r0 == 0) goto L20;
        Integer r02 = (Integer) r15.get(NotificationsSettingsFacade.PROPERTY_NOTIFY_UNTIL.concat(r112));
        if (r02 == null) goto L20;
        r133.muteUntil = r02.intValue();
    L20:
        if (DialogObject.isEncryptedDialog(r82) == false) goto L32;
        int r03 = DialogObject.getEncryptedChatId(r82);
        TLRPC.EncryptedChat r62 = MessagesController.getInstance(this.currentAccount).getEncryptedChat(Integer.valueOf(r03));
        if (r62 == null) goto L23;
        TLRPC.User r04 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r62.user_id));
        if (r04 == null) goto L26;
        if (r04.deleted == false) goto L30;
    L29:
        r12 = r22;
        r11 = r23;
        r13 = r24;
        r9 = r25;
        r8 = r26;
        r6 = r27;
    L30:
        r5.add(r133);
    L31:
        r6 = r27;
    L59:
        r12 = r22;
        r11 = r23;
        r13 = r24;
        r9 = r25;
        r8 = r26;
        goto L3
    L26:
        r3.add(Long.valueOf(r62.user_id));
        r2.put(r62.user_id, r133);
        goto L30
    L23:
        r10.add(Integer.valueOf(r03));
        r2.put(r82, r133);
        goto L30
    L32:
        boolean r05 = DialogObject.isUserDialog(r82);
        int r63 = this.currentAccount;
        if (r05 == false) goto L41;
        TLRPC.User r06 = MessagesController.getInstance(r63).getUser(r123);
        if (r06 == null) goto L36;
        if (r06.deleted == true) goto L29;
    L40:
        r5.add(r133);
        goto L31
    L36:
        r3.add(r123);
        r2.put(r82, r133);
        goto L40
    L41:
        long r113 = -r82;
        TLRPC.Chat r07 = MessagesController.getInstance(r63).getChat(Long.valueOf(r113));
        if (r07 == null) goto L43;
        if (r07.left == true) goto L29;
        if (r07.kicked == true) goto L29;
        if (r07.migrated_to != null) goto L29;
        if (ChatObject.isChannel(r07) == true) goto L54;
    L56:
        r6 = r27;
        r6.add(r133);
        goto L59
    L54:
        if (r07.megagroup == true) goto L56;
        r7.add(r133);
        goto L31
    L43:
        r4.add(Long.valueOf(r113));
        r2.put(r82, r133);
    L57:
        r26 = r132;
        goto L59
    L9:
        r12 = r22;
        r11 = r23;
        r13 = r24;
        goto L3
    L58:
        r26 = r8;
        r25 = r9;
        r24 = r13;
        goto L59
    L60:
        ArrayList r262 = r8;
        ArrayList r252 = r9;
        ArrayList<TLRPC.User> r232 = r11;
        ArrayList<TLRPC.EncryptedChat> r242 = r13;
        HashSet r08 = new HashSet();
        Iterator<Map.Entry<String, ?>> r83 = r15.entrySet().iterator();
    L61:
        boolean r114 = true;
        if (r83.hasNext() == false) goto L87;
        Map.Entry<String, ?> r92 = r83.next();
        String r124 = r92.getKey();
        if (r124.startsWith(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY) == false) goto L85;
        Long r125 = Utilities.parseLong(r124.substring(8));     // Catch: Exception -> L180
        long r134 = r125.longValue();     // Catch: Exception -> L180
        if (r134 == 0) goto L85;
        if (r134 == r14) goto L85;
        NotificationException r152 = new NotificationException();     // Catch: Exception -> L180
        r152.did = r134;     // Catch: Exception -> L180
        if (((Boolean) r92.getValue()).booleanValue() == false) goto L73;
        int r93 = 0;
    L74:
        r152.notify = r93;     // Catch: Exception -> L180
        r152.story = true;     // Catch: Exception -> L180
        if (DialogObject.isUserDialog(r134) == false) goto L85;
        TLRPC.User r94 = getMessagesController().getUser(r125);     // Catch: Exception -> L180
        if (r94 == null) goto L78;
        if (r94.deleted == true) goto L61;
    L79:
        ArrayList r135 = r262;
        r135.add(r152);     // Catch: Exception -> L179
        r08.add(r125);     // Catch: Exception -> L179
    L86:
        r262 = r135;
        goto L61
    L78:
        r3.add(r125);     // Catch: Exception -> L180
        r2.put(r134, r152);     // Catch: Exception -> L180
        goto L79
    L73:
        r93 = Integer.MAX_VALUE;
    L85:
        r135 = r262;
        goto L86
    L87:
        ArrayList r136 = r262;
        if (r29 == null) goto L106;
        ArrayList r95 = r29;
        Collections.sort(r95, Comparator.comparingDouble(new NotificationsSettingsActivity$$ExternalSyntheticLambda2()));
        int r84 = Math.max(0, r95.size() - 5);
    L91:
        if (r84 >= r95.size()) goto L106;
        long r143 = DialogObject.getPeerDialogId(((TLRPC.TL_topPeer) r95.get(r84)).peer);
        if (r08.contains(Long.valueOf(r143)) == true) goto L104;
        NotificationException r126 = new NotificationException();
        r126.did = r143;
        int r16 = r84;
        r126.notify = 0;
        r126.auto = r114;
        r126.story = r114;
        if (DialogObject.isUserDialog(r143) == false) goto L102;
        TLRPC.User r85 = getMessagesController().getUser(Long.valueOf(r143));
        if (r85 != null) goto L101;
        r3.add(Long.valueOf(r143));
        r2.put(r143, r126);
    L99:
        ArrayList r96 = r252;
        r96.add(0, r126);
        r08.add(Long.valueOf(r143));
    L105:
        r252 = r96;
        r84 = r16 + 1;
        r114 = true;
        r95 = r29;
        goto L91
    L101:
        if (r85.deleted == false) goto L99;
    L102:
        r96 = r252;
        goto L105
    L104:
        r16 = r84;
    L106:
        ArrayList r97 = r252;
        if (r2.size() != 0) goto L191;
        ArrayList r253 = r97;
        ArrayList<TLRPC.Chat> r42 = r22;
        ArrayList<TLRPC.User> r102 = r232;
        ArrayList<TLRPC.EncryptedChat> r127 = r242;
    L177:
        AndroidUtilities.runOnUIThread(new NotificationsSettingsActivity$$ExternalSyntheticLambda3(this, r102, r42, r127, r5, r6, r7, r136, r253, r30));
        return;
    L191:
        if (r10.isEmpty() == false) goto L189;
        r127 = r242;
    L119:
        if (r3.isEmpty() == false) goto L193;
        r102 = r232;
    L130:
        if (r4.isEmpty() == true) goto L137;
        MessagesStorage r09 = MessagesStorage.getInstance(this.currentAccount);     // Catch: Exception -> L124
        String r32 = TextUtils.join(",", r4);     // Catch: Exception -> L124
        r42 = r22;
        r09.getChatsInternal(r32, r42);     // Catch: Exception -> L135
    L141:
        int r010 = r42.size();
        int r33 = 0;
    L142:
        if (r33 >= r010) goto L159;
        TLRPC.Chat r115 = r42.get(r33);
        if (r115.left == false) goto L146;
    L149:
        ArrayList r254 = r97;
    L158:
        r33 = r33 + 1;
        r97 = r254;
        goto L142
    L146:
        if (r115.kicked == true) goto L149;
        if (r115.migrated_to != null) goto L149;
        NotificationException r144 = (NotificationException) r2.get(-r115.id);
        r254 = r97;
        r2.remove(-r115.id);
        if (r144 == null) goto L158;
        if (ChatObject.isChannel(r115) == true) goto L155;
    L157:
        r6.add(r144);
        goto L158
    L155:
        if (r115.megagroup == true) goto L157;
        r7.add(r144);
        goto L158
    L159:
        r253 = r97;
        int r011 = r102.size();
        int r34 = 0;
    L160:
        if (r34 >= r011) goto L166;
        TLRPC.User r86 = r102.get(r34);
        if (r86.deleted == true) goto L165;
        r2.remove(r86.id);
    L165:
        r34 = r34 + 1;
        goto L160
    L166:
        int r012 = r127.size();
        int r35 = 0;
    L167:
        if (r35 >= r012) goto L169;
        r2.remove(DialogObject.makeEncryptedDialogId(r127.get(r35).id));
        r35 = r35 + 1;
        goto L167
    L169:
        int r013 = r2.size();
        int r36 = 0;
    L170:
        if (r36 >= r013) goto L177;
        if (DialogObject.isChatDialog(r2.keyAt(r36)) == false) goto L174;
        r6.remove(r2.valueAt(r36));
        r7.remove(r2.valueAt(r36));
    L175:
        r36 = r36 + 1;
        goto L170
    L174:
        r5.remove(r2.valueAt(r36));
    L135:
        e = e;
    L140:
        FileLog.e(e);
    L124:
        e = e;
    L125:
        r42 = r22;
        goto L140
    L137:
        r42 = r22;
        goto L141
    L193:
        r102 = r232;
        MessagesStorage.getInstance(this.currentAccount).getUsersInternal(r3, r102);     // Catch: Exception -> L124
    L126:
        e = e;
        r102 = r232;
    L114:
        e = e;
    L115:
        r42 = r22;
        r102 = r232;
        goto L140
    L189:
        r127 = r242;
        MessagesStorage.getInstance(this.currentAccount).getEncryptedChatsInternal(TextUtils.join(",", r10), r127, r3);     // Catch: Exception -> L114
    L116:
        e = e;
        r127 = r242;
    L138:
        e = e;
        r42 = r22;
        r102 = r232;
        r127 = r242;
        goto L140
    }

    private /* synthetic */ void lambda$showExceptionsAlert$11(ArrayList r1, ArrayList r2, AlertDialog r3, int r4) {
        presentFragment(new NotificationsCustomSettingsActivity(-1, r1, r2));
    }

    private void showExceptionsAlert(int r7, Runnable r8) {
        String r3 = null;
        if (r7 != this.storiesRow) goto L10;
        ArrayList<NotificationException> r72 = this.exceptionStories;
        ArrayList<NotificationException> r0 = this.exceptionAutoStories;
        if (r72 != null) goto L7;
    L34:
        if (r3 != null) goto L37;
        r8.run();
        return;
    L37:
        AlertDialog.Builder r1 = new AlertDialog.Builder(getParentActivity());
        if (r72.size() != 1) goto L40;
        r1.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.NotificationsExceptionsSingleAlert, new Object[]{r3})));
    L41:
        r1.setTitle(LocaleController.getString("NotificationsExceptions", R.string.NotificationsExceptions));
        r1.setNeutralButton(LocaleController.getString("ViewExceptions", R.string.ViewExceptions), new NotificationsSettingsActivity$$ExternalSyntheticLambda9(this, r72, r0));
        r1.setNegativeButton(LocaleController.getString("OK", R.string.OK), new NotificationsSettingsActivity$$ExternalSyntheticLambda10(r8));
        showDialog(r1.create());
        return;
    L40:
        r1.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.NotificationsExceptionsAlert, new Object[]{r3})));
        goto L41
    L7:
        if (r72.isEmpty() == true) goto L34;
        r3 = LocaleController.formatPluralString("ChatsException", r72.size(), new Object[0]);
        goto L34
    L10:
        if (r7 != this.privateRow) goto L19;
        r72 = this.exceptionUsers;
        if (r72 != null) goto L14;
    L17:
        r0 = null;
        goto L34
    L14:
        if (r72.isEmpty() == true) goto L17;
        String r02 = LocaleController.formatPluralString("ChatsException", r72.size(), new Object[0]);
    L16:
        r3 = r02;
        r0 = null;
        goto L34
    L19:
        if (r7 != this.groupRow) goto L26;
        r72 = this.exceptionChats;
        if (r72 == null) goto L17;
        if (r72.isEmpty() == true) goto L17;
        r02 = LocaleController.formatPluralString("Groups", r72.size(), new Object[0]);
        goto L16
    L26:
        if (r7 != this.reactionsRow) goto L29;
        r8.run();
        return;
    L29:
        r72 = this.exceptionChannels;
        if (r72 == null) goto L17;
        if (r72.isEmpty() == true) goto L17;
        r02 = LocaleController.formatPluralString("Channels", r72.size(), new Object[0]);
        goto L16
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r6) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.NotificationsAndSounds));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
    L7:
        FrameLayout r02 = new FrameLayout(r6);
        this.fragmentView = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        RecyclerListView r2 = new RecyclerListView(r6);
        this.listView = r2;
        r2.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setItemAnimator(null);
        this.listView.setLayoutAnimation(null);
        RecyclerListView r22 = this.listView;
        AnonymousClass2 r3 = new AnonymousClass2(this, r6, 1, false);
        this.layoutManager = r3;
        r22.setLayoutManager(r3);
        this.listView.setVerticalScrollBarEnabled(false);
        r02.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r03 = this.listView;
        ListAdapter r1 = new ListAdapter(this, r6);
        this.adapter = r1;
        r03.setAdapter(r1);
        this.listView.setOnItemClickListener(new NotificationsSettingsActivity$$ExternalSyntheticLambda0(this));
        return this.fragmentView;
    L5:
        if (r0.isRightLayout() == false) goto L7;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_close);
        goto L7
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.notificationsSettingsUpdated) goto L6;
        this.adapter.notifyDataSetChanged();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{HeaderCell.class, TextCheckCell.class, TextDetailSettingsCell.class, TextSettingsCell.class, NotificationsCheckCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        int r28 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"textView"}, null, null, null, r28));
        int r37 = Theme.key_windowBackgroundWhiteGrayText2;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"valueTextView"}, null, null, null, r37));
        int r23 = Theme.key_switchTrack;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"checkBox"}, null, null, null, r23));
        int r55 = Theme.key_switchTrackChecked;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"checkBox"}, null, null, null, r55));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, r28));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"valueTextView"}, null, null, null, r37));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, r23));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, r55));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r28));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextDetailSettingsCell.class}, new String[]{"textView"}, null, null, null, r28));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextDetailSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, r37));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LINKCOLOR, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteLinkText));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    public void loadExceptions(Runnable r4) {
        MediaDataController.getInstance(this.currentAccount).loadHints(true);
        ArrayList r0 = new ArrayList(MediaDataController.getInstance(this.currentAccount).hints);
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new NotificationsSettingsActivity$$ExternalSyntheticLambda1(this, r0, r4));
    }

    public NotificationsCustomSettingsActivity makeNotificationsCustomSettingsActivity(int r4) {
        ArrayList<NotificationException> r1 = null;
        if (r4 != 1) goto L6;
        ArrayList<NotificationException> r3 = this.exceptionUsers;
    L5:
        r1 = r3;
        ArrayList<NotificationException> r32 = null;
    L16:
        return new NotificationsCustomSettingsActivity(r4, r1, r32);
    L6:
        if (r4 != 0) goto L9;
        r3 = this.exceptionChats;
        goto L5
    L9:
        if (r4 != 4) goto L12;
        r32 = null;
        goto L16
    L12:
        if (r4 != 3) goto L14;
        r1 = this.exceptionStories;
        r32 = this.exceptionAutoStories;
        goto L16
    L14:
        r3 = this.exceptionChannels;
        goto L5
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r4, int r5, Intent r6) {
        super.onActivityResultFragment(r4, r5, r6);
        if (r5 != (-1)) goto L30;
        Uri r52 = (Uri) r6.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
        if (r52 == null) goto L19;
        Ringtone r62 = RingtoneManager.getRingtone(getParentActivity(), r52);
        if (r62 == null) goto L19;
        if (r4 != this.callsRingtoneRow) goto L15;
        if (r52.equals(Settings.System.DEFAULT_RINGTONE_URI) == false) goto L13;
        String r0 = LocaleController.getString("DefaultRingtone", R.string.DefaultRingtone);
    L18:
        r62.stop();
    L20:
        SharedPreferences.Editor r63 = MessagesController.getNotificationsSettings(this.currentAccount).edit();
        if (r4 == this.callsRingtoneRow) goto L23;
    L28:
        r63.apply();
        this.adapter.notifyItemChanged(r4);
        return;
    L23:
        if (r0 == null) goto L26;
        if (r52 == null) goto L26;
        r63.putString("CallsRingtone", r0);
        r63.putString("CallsRingtonePath", r52.toString());
    L27:
        this.updateRingtone = true;
    L26:
        r63.putString("CallsRingtone", "NoSound");
        r63.putString("CallsRingtonePath", "NoSound");
        goto L27
    L13:
        r0 = r62.getTitle(getParentActivity());
        goto L18
    L15:
        if (r52.equals(Settings.System.DEFAULT_NOTIFICATION_URI) == false) goto L17;
        r0 = LocaleController.getString("SoundDefault", R.string.SoundDefault);
        goto L18
    L17:
        r0 = r62.getTitle(getParentActivity());
    L19:
        r0 = null;
        goto L20
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        MessagesController.getInstance(this.currentAccount).loadSignUpNotificationsSettings();
        loadExceptions(null);
        if (UserConfig.getActivatedAccountsCount() <= 1) goto L5;
        int r0 = this.rowCount;
        this.accountsSectionRow = r0;
        this.accountsAllRow = r0 + 1;
        int r1 = r0 + 3;
        this.accountsListRow = r0 + 2;
        this.rowCount = r0 + 4;
        this.accountsInfoRow = r1;
    L6:
        int r02 = this.rowCount;
        this.notificationsSectionRow = r02;
        this.privateRow = r02 + 1;
        this.groupRow = r02 + 2;
        this.channelsRow = r02 + 3;
        this.storiesRow = r02 + 4;
        this.reactionsRow = r02 + 5;
        this.notificationsSection2Row = r02 + 6;
        this.callsSectionRow = r02 + 7;
        this.callsVibrateRow = r02 + 8;
        this.callsRingtoneRow = r02 + 9;
        this.eventsSection2Row = r02 + 10;
        this.badgeNumberSection = r02 + 11;
        this.badgeNumberShowRow = r02 + 12;
        this.badgeNumberMutedRow = r02 + 13;
        this.badgeNumberMessagesRow = r02 + 14;
        this.badgeNumberSection2Row = r02 + 15;
        this.inappSectionRow = r02 + 16;
        this.inappSoundRow = r02 + 17;
        this.inappVibrateRow = r02 + 18;
        this.inappPreviewRow = r02 + 19;
        this.inchatSoundRow = r02 + 20;
        this.inappPriorityRow = r02 + 21;
        this.callsSection2Row = r02 + 22;
        this.eventsSectionRow = r02 + 23;
        this.contactJoinedRow = r02 + 24;
        this.pinnedMessageRow = r02 + 25;
        this.otherSection2Row = r02 + 26;
        this.otherSectionRow = r02 + 27;
        this.notificationsServiceRow = r02 + 28;
        this.notificationsServiceConnectionRow = r02 + 29;
        this.androidAutoAlertRow = -1;
        this.repeatRow = r02 + 30;
        this.resetSection2Row = r02 + 31;
        this.resetSectionRow = r02 + 32;
        int r2 = r02 + 34;
        this.resetNotificationsRow = r02 + 33;
        this.rowCount = r02 + 35;
        this.resetNotificationsSectionRow = r2;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.notificationsSettingsUpdated);
        getMessagesController().reloadReactionsNotifySettings();
        return super.onFragmentCreate();
    L5:
        this.accountsSectionRow = -1;
        this.accountsAllRow = -1;
        this.accountsListRow = -1;
        this.accountsInfoRow = -1;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.notificationsSettingsUpdated);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.adapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }
}
