package org.telegram.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Keep;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.RadioColorCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.bots.BotBiometry;
import org.telegram.ui.bots.BotBiometrySettings;

/* loaded from: classes6.dex */
public class PrivacySettingsActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private static SpannableString premiumStar;
    private int advancedSectionRow;
    private boolean archiveChats;

    @Keep
    private int autoDeleteMesages;

    @Keep
    private int bioRow;
    private final ArrayList<BotBiometry.Bot> biometryBots;

    @Keep
    private int birthdayRow;

    @Keep
    private int blockedRow;
    private int botsAndWebsitesShadowRow;
    private int botsBiometryRow;
    private int botsDetailRow;
    private int botsSectionRow;

    @Keep
    private int callsRow;
    private boolean[] clear;

    @Keep
    private int contactsDeleteRow;
    private int contactsDetailRow;
    private int contactsSectionRow;

    @Keep
    private int contactsSuggestRow;

    @Keep
    private int contactsSyncRow;
    public ArrayList<TL_account.Passkey> currentPasskeys;
    private TL_account.Password currentPassword;
    private boolean currentSuggest;
    private boolean currentSync;
    private int deleteAccountDetailRow;

    @Keep
    private int deleteAccountRow;
    private boolean deleteAccountUpdate;
    private SessionsActivity devicesActivityPreload;

    @Keep
    private int emailLoginRow;
    private boolean feeValue;

    @Keep
    private int forwardsRow;

    @Keep
    private int giftsRow;
    private int groupsDetailRow;
    private int groupsRow;

    @Keep
    private int lastSeenRow;
    private LinearLayoutManager layoutManager;
    private ListAdapter listAdapter;
    private RecyclerListView listView;

    @Keep
    private int musicRow;
    private int newChatsHeaderRow;

    @Keep
    private int newChatsRow;
    private int newChatsSectionRow;
    private boolean newSuggest;
    private boolean newSync;

    @Keep
    private int noncontactsRow;
    private boolean noncontactsValue;

    @Keep
    private int passcodeRow;

    @Keep
    private int passkeysRow;
    private int passportRow;

    @Keep
    private int passwordRow;

    @Keep
    private int paymentsClearRow;

    @Keep
    private int phoneNumberRow;
    private int privacySectionRow;
    private int privacyShadowRow;

    @Keep
    private int profilePhotoRow;
    private AlertDialog progressDialog;
    private int rowCount;
    private int secretDetailRow;

    @Keep
    private int secretMapRow;
    private boolean secretMapUpdate;
    private int secretSectionRow;

    @Keep
    private int secretWebpageRow;
    private int securitySectionRow;
    private int sessionsDetailRow;
    private int sessionsRow;

    @Keep
    private int voicesRow;
    private SessionsActivity webSessionsActivityPreload;

    @Keep
    private int webSessionsRow;

    /* renamed from: org.telegram.ui.PrivacySettingsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PrivacySettingsActivity this$0;

        public AnonymousClass1(PrivacySettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.PrivacySettingsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayoutManager {
        final /* synthetic */ PrivacySettingsActivity this$0;

        public AnonymousClass2(PrivacySettingsActivity r1, Context r2, int r3, boolean r4) {
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
        final /* synthetic */ PrivacySettingsActivity this$0;

        public ListAdapter(PrivacySettingsActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return PrivacySettingsActivity.m17762$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != PrivacySettingsActivity.m17755$$Nest$fgetpassportRow(this.this$0)) goto L5;
        L92:
            return 0;
        L5:
            if (r3 == PrivacySettingsActivity.m17744$$Nest$fgetlastSeenRow(this.this$0)) goto L92;
            if (r3 == PrivacySettingsActivity.m17758$$Nest$fgetphoneNumberRow(this.this$0)) goto L92;
            if (r3 == PrivacySettingsActivity.m17735$$Nest$fgetdeleteAccountRow(this.this$0)) goto L92;
            if (r3 == PrivacySettingsActivity.m17772$$Nest$fgetwebSessionsRow(this.this$0)) goto L92;
            if (r3 == PrivacySettingsActivity.m17743$$Nest$fgetgroupsRow(this.this$0)) goto L92;
            if (r3 == PrivacySettingsActivity.m17757$$Nest$fgetpaymentsClearRow(this.this$0)) goto L92;
            if (r3 == PrivacySettingsActivity.m17764$$Nest$fgetsecretMapRow(this.this$0)) goto L92;
            if (r3 == PrivacySettingsActivity.m17728$$Nest$fgetcontactsDeleteRow(this.this$0)) goto L92;
            if (r3 == PrivacySettingsActivity.m17724$$Nest$fgetbotsBiometryRow(this.this$0)) goto L92;
            if (r3 != PrivacySettingsActivity.m17760$$Nest$fgetprivacyShadowRow(this.this$0)) goto L26;
            return 1;
        L26:
            if (r3 != PrivacySettingsActivity.m17734$$Nest$fgetdeleteAccountDetailRow(this.this$0)) goto L28;
            return 1;
        L28:
            if (r3 != PrivacySettingsActivity.m17742$$Nest$fgetgroupsDetailRow(this.this$0)) goto L30;
            return 1;
        L30:
            if (r3 != PrivacySettingsActivity.m17769$$Nest$fgetsessionsDetailRow(this.this$0)) goto L32;
            return 1;
        L32:
            if (r3 != PrivacySettingsActivity.m17763$$Nest$fgetsecretDetailRow(this.this$0)) goto L34;
            return 1;
        L34:
            if (r3 != PrivacySettingsActivity.m17725$$Nest$fgetbotsDetailRow(this.this$0)) goto L36;
            return 1;
        L36:
            if (r3 != PrivacySettingsActivity.m17729$$Nest$fgetcontactsDetailRow(this.this$0)) goto L38;
            return 1;
        L38:
            if (r3 != PrivacySettingsActivity.m17748$$Nest$fgetnewChatsSectionRow(this.this$0)) goto L41;
            return 1;
        L41:
            if (r3 != PrivacySettingsActivity.m17768$$Nest$fgetsecuritySectionRow(this.this$0)) goto L43;
            return 2;
        L43:
            if (r3 != PrivacySettingsActivity.m17717$$Nest$fgetadvancedSectionRow(this.this$0)) goto L45;
            return 2;
        L45:
            if (r3 != PrivacySettingsActivity.m17759$$Nest$fgetprivacySectionRow(this.this$0)) goto L47;
            return 2;
        L47:
            if (r3 != PrivacySettingsActivity.m17766$$Nest$fgetsecretSectionRow(this.this$0)) goto L49;
            return 2;
        L49:
            if (r3 != PrivacySettingsActivity.m17726$$Nest$fgetbotsSectionRow(this.this$0)) goto L51;
            return 2;
        L51:
            if (r3 != PrivacySettingsActivity.m17730$$Nest$fgetcontactsSectionRow(this.this$0)) goto L53;
            return 2;
        L53:
            if (r3 != PrivacySettingsActivity.m17746$$Nest$fgetnewChatsHeaderRow(this.this$0)) goto L56;
            return 2;
        L56:
            if (r3 != PrivacySettingsActivity.m17767$$Nest$fgetsecretWebpageRow(this.this$0)) goto L58;
            return 3;
        L58:
            if (r3 != PrivacySettingsActivity.m17732$$Nest$fgetcontactsSyncRow(this.this$0)) goto L60;
            return 3;
        L60:
            if (r3 != PrivacySettingsActivity.m17731$$Nest$fgetcontactsSuggestRow(this.this$0)) goto L62;
            return 3;
        L62:
            if (r3 != PrivacySettingsActivity.m17747$$Nest$fgetnewChatsRow(this.this$0)) goto L65;
            return 3;
        L65:
            if (r3 != PrivacySettingsActivity.m17723$$Nest$fgetbotsAndWebsitesShadowRow(this.this$0)) goto L69;
            return 4;
        L69:
            if (r3 != PrivacySettingsActivity.m17719$$Nest$fgetautoDeleteMesages(this.this$0)) goto L71;
            return 5;
        L71:
            if (r3 != PrivacySettingsActivity.m17770$$Nest$fgetsessionsRow(this.this$0)) goto L73;
            return 5;
        L73:
            if (r3 != PrivacySettingsActivity.m17738$$Nest$fgetemailLoginRow(this.this$0)) goto L75;
            return 5;
        L75:
            if (r3 != PrivacySettingsActivity.m17756$$Nest$fgetpasswordRow(this.this$0)) goto L77;
            return 5;
        L77:
            if (r3 != PrivacySettingsActivity.m17754$$Nest$fgetpasskeysRow(this.this$0)) goto L79;
            return 5;
        L79:
            if (r3 != PrivacySettingsActivity.m17753$$Nest$fgetpasscodeRow(this.this$0)) goto L81;
            return 5;
        L81:
            if (r3 == PrivacySettingsActivity.m17722$$Nest$fgetblockedRow(this.this$0)) goto L98;
            return 0;
        L98:
            return 5;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r5) {
            int r52 = r5.getAdapterPosition();
            if (r52 != PrivacySettingsActivity.m17753$$Nest$fgetpasscodeRow(this.this$0)) goto L5;
        L90:
            return true;
        L5:
            if (r52 == PrivacySettingsActivity.m17756$$Nest$fgetpasswordRow(this.this$0)) goto L90;
            if (r52 == PrivacySettingsActivity.m17754$$Nest$fgetpasskeysRow(this.this$0)) goto L90;
            if (r52 == PrivacySettingsActivity.m17722$$Nest$fgetblockedRow(this.this$0)) goto L90;
            if (r52 == PrivacySettingsActivity.m17770$$Nest$fgetsessionsRow(this.this$0)) goto L90;
            if (r52 == PrivacySettingsActivity.m17767$$Nest$fgetsecretWebpageRow(this.this$0)) goto L90;
            if (r52 == PrivacySettingsActivity.m17772$$Nest$fgetwebSessionsRow(this.this$0)) goto L90;
            if (r52 != PrivacySettingsActivity.m17743$$Nest$fgetgroupsRow(this.this$0)) goto L21;
            if (PrivacySettingsActivity.access$000(this.this$0).getLoadingPrivacyInfo(1) == false) goto L90;
        L21:
            if (r52 != PrivacySettingsActivity.m17744$$Nest$fgetlastSeenRow(this.this$0)) goto L25;
            if (PrivacySettingsActivity.access$100(this.this$0).getLoadingPrivacyInfo(0) == false) goto L90;
        L25:
            if (r52 != PrivacySettingsActivity.m17727$$Nest$fgetcallsRow(this.this$0)) goto L29;
            if (PrivacySettingsActivity.access$200(this.this$0).getLoadingPrivacyInfo(2) == false) goto L90;
        L29:
            if (r52 != PrivacySettingsActivity.m17761$$Nest$fgetprofilePhotoRow(this.this$0)) goto L33;
            if (PrivacySettingsActivity.access$300(this.this$0).getLoadingPrivacyInfo(4) == false) goto L90;
        L33:
            if (r52 != PrivacySettingsActivity.m17720$$Nest$fgetbioRow(this.this$0)) goto L37;
            if (PrivacySettingsActivity.access$400(this.this$0).getLoadingPrivacyInfo(9) == false) goto L90;
        L37:
            if (r52 != PrivacySettingsActivity.m17745$$Nest$fgetmusicRow(this.this$0)) goto L41;
            if (PrivacySettingsActivity.access$500(this.this$0).getLoadingPrivacyInfo(14) == false) goto L90;
        L41:
            if (r52 != PrivacySettingsActivity.m17721$$Nest$fgetbirthdayRow(this.this$0)) goto L45;
            if (PrivacySettingsActivity.access$600(this.this$0).getLoadingPrivacyInfo(11) == false) goto L90;
        L45:
            if (r52 != PrivacySettingsActivity.m17741$$Nest$fgetgiftsRow(this.this$0)) goto L49;
            if (PrivacySettingsActivity.access$700(this.this$0).getLoadingPrivacyInfo(12) == false) goto L90;
        L49:
            if (r52 != PrivacySettingsActivity.m17740$$Nest$fgetforwardsRow(this.this$0)) goto L53;
            if (PrivacySettingsActivity.access$800(this.this$0).getLoadingPrivacyInfo(5) == false) goto L90;
        L53:
            if (r52 != PrivacySettingsActivity.m17758$$Nest$fgetphoneNumberRow(this.this$0)) goto L57;
            if (PrivacySettingsActivity.access$900(this.this$0).getLoadingPrivacyInfo(6) == false) goto L90;
        L57:
            if (r52 != PrivacySettingsActivity.m17771$$Nest$fgetvoicesRow(this.this$0)) goto L61;
            if (PrivacySettingsActivity.access$1000(this.this$0).getLoadingPrivacyInfo(8) == false) goto L90;
        L61:
            if (r52 == PrivacySettingsActivity.m17751$$Nest$fgetnoncontactsRow(this.this$0)) goto L90;
            if (r52 != PrivacySettingsActivity.m17735$$Nest$fgetdeleteAccountRow(this.this$0)) goto L67;
            if (PrivacySettingsActivity.access$1100(this.this$0).getLoadingDeleteInfo() == false) goto L90;
        L67:
            if (r52 != PrivacySettingsActivity.m17747$$Nest$fgetnewChatsRow(this.this$0)) goto L71;
            if (PrivacySettingsActivity.access$1200(this.this$0).getLoadingGlobalSettings() == false) goto L90;
        L71:
            if (r52 == PrivacySettingsActivity.m17738$$Nest$fgetemailLoginRow(this.this$0)) goto L90;
            if (r52 == PrivacySettingsActivity.m17757$$Nest$fgetpaymentsClearRow(this.this$0)) goto L90;
            if (r52 == PrivacySettingsActivity.m17764$$Nest$fgetsecretMapRow(this.this$0)) goto L90;
            if (r52 == PrivacySettingsActivity.m17732$$Nest$fgetcontactsSyncRow(this.this$0)) goto L90;
            if (r52 == PrivacySettingsActivity.m17755$$Nest$fgetpassportRow(this.this$0)) goto L90;
            if (r52 == PrivacySettingsActivity.m17728$$Nest$fgetcontactsDeleteRow(this.this$0)) goto L90;
            if (r52 == PrivacySettingsActivity.m17731$$Nest$fgetcontactsSuggestRow(this.this$0)) goto L90;
            if (r52 == PrivacySettingsActivity.m17719$$Nest$fgetautoDeleteMesages(this.this$0)) goto L90;
            if (r52 == PrivacySettingsActivity.m17724$$Nest$fgetbotsBiometryRow(this.this$0)) goto L90;
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r18, int r19) {
            int r3 = r18.getItemViewType();
            String r4 = null;
            int r5 = 16;
            boolean r9 = false;
            boolean r10 = true;
            if (r3 == 0) goto L176;
            if (r3 == 1) goto L143;
            if (r3 != 2) goto L7;
            HeaderCell r1 = (HeaderCell) r18.itemView;
            if (r19 != PrivacySettingsActivity.m17759$$Nest$fgetprivacySectionRow(this.this$0)) goto L120;
            r1.setText(LocaleController.getString("PrivacyTitle", R.string.PrivacyTitle));
            return;
        L120:
            if (r19 != PrivacySettingsActivity.m17768$$Nest$fgetsecuritySectionRow(this.this$0)) goto L124;
            r1.setText(LocaleController.getString("SecurityTitle", R.string.SecurityTitle));
            return;
        L124:
            if (r19 != PrivacySettingsActivity.m17717$$Nest$fgetadvancedSectionRow(this.this$0)) goto L128;
            r1.setText(LocaleController.getString("DeleteMyAccount", R.string.DeleteMyAccount));
            return;
        L128:
            if (r19 != PrivacySettingsActivity.m17766$$Nest$fgetsecretSectionRow(this.this$0)) goto L132;
            r1.setText(LocaleController.getString("SecretChat", R.string.SecretChat));
            return;
        L132:
            if (r19 != PrivacySettingsActivity.m17726$$Nest$fgetbotsSectionRow(this.this$0)) goto L136;
            r1.setText(LocaleController.getString("PrivacyBots", R.string.PrivacyBots));
            return;
        L136:
            if (r19 != PrivacySettingsActivity.m17730$$Nest$fgetcontactsSectionRow(this.this$0)) goto L140;
            r1.setText(LocaleController.getString("Contacts", R.string.Contacts));
            return;
        L140:
            if (r19 != PrivacySettingsActivity.m17746$$Nest$fgetnewChatsHeaderRow(this.this$0)) goto L337;
            r1.setText(LocaleController.getString("NewChatsFromNonContacts", R.string.NewChatsFromNonContacts));
            return;
        L337:
            return;
        L7:
            if (r3 == 3) goto L95;
            if (r3 != 5) goto L339;
            View r32 = r18.itemView;
            TextCell r11 = (TextCell) r32;
            if (r32.getTag() != null) goto L13;
        L15:
            boolean r33 = false;
        L16:
            r18.itemView.setTag(Integer.valueOf(r19));
            r11.setPrioritizeTitleOverValue(false);
            int r12 = PrivacySettingsActivity.m17719$$Nest$fgetautoDeleteMesages(this.this$0);
            PrivacySettingsActivity r6 = this.this$0;
            if (r19 != r12) goto L26;
            int r0 = r6.getUserConfig().getGlobalTTl();
            if (r0 != (-1)) goto L21;
            String r13 = null;
            r9 = true;
        L25:
            r11.setTextAndValueAndIcon(LocaleController.getString("AutoDeleteMessages", R.string.AutoDeleteMessages), r13, true, R.drawable.msg2_autodelete, true);
        L93:
            r11.setDrawLoading(r9, 16, r33);
            return;
        L21:
            if (r0 <= 0) goto L24;
            String r42 = LocaleController.formatTTLString(r0 * 60);
        L23:
            r13 = r42;
            goto L25
        L24:
            r42 = LocaleController.getString("PasswordOff", R.string.PasswordOff);
            goto L23
        L26:
            int r14 = PrivacySettingsActivity.m17770$$Nest$fgetsessionsRow(r6);
            PrivacySettingsActivity r43 = this.this$0;
            String r7 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r19 == r14) goto L29;
            int r15 = PrivacySettingsActivity.m17738$$Nest$fgetemailLoginRow(r43);
            PrivacySettingsActivity r44 = this.this$0;
            if (r19 == r15) goto L40;
            int r16 = PrivacySettingsActivity.m17756$$Nest$fgetpasswordRow(r44);
            PrivacySettingsActivity r45 = this.this$0;
            if (r19 != r16) goto L59;
            int r17 = R.drawable.menu_2sv;
            if (PrivacySettingsActivity.m17733$$Nest$fgetcurrentPassword(r45) != null) goto L54;
            int r152 = r17;
            String r132 = _UrlKt.FRAGMENT_ENCODE_SET;
            r9 = true;
        L58:
            r11.setTextAndValueAndIcon(LocaleController.getString(R.string.TwoStepVerification), r132, true, r152, true);
            goto L93
        L54:
            if (PrivacySettingsActivity.m17733$$Nest$fgetcurrentPassword(this.this$0).has_password == false) goto L57;
            r17 = R.drawable.menu_2sv_on;
            String r72 = LocaleController.getString(R.string.PasswordOn);
        L56:
            r152 = r17;
            r132 = r72;
            goto L58
        L57:
            r72 = LocaleController.getString(R.string.PasswordOff);
            goto L56
        L59:
            int r110 = PrivacySettingsActivity.m17754$$Nest$fgetpasskeysRow(r45);
            PrivacySettingsActivity r46 = this.this$0;
            if (r19 != r110) goto L76;
            ArrayList<TL_account.Passkey> r111 = r46.currentPasskeys;
            if (r111 != null) goto L65;
            String r133 = _UrlKt.FRAGMENT_ENCODE_SET;
            r9 = true;
        L74:
            r11.setTextAndValueAndIcon(LocaleController.getString(R.string.Passkey), r133, true, R.drawable.msg2_permissions, true);
            goto L93
        L65:
            if (r111.size() != 1) goto L71;
            if (r11.valueTextView.getPaint().measureText(this.this$0.currentPasskeys.get(0).name) >= (AndroidUtilities.displaySize.x / 3.0f)) goto L71;
            String r73 = this.this$0.currentPasskeys.get(0).name;
        L69:
            r133 = r73;
        L71:
            if (this.this$0.currentPasskeys.size() <= 0) goto L73;
            r73 = this.this$0.currentPasskeys.size() + _UrlKt.FRAGMENT_ENCODE_SET;
            goto L69
        L73:
            r73 = LocaleController.getString(R.string.PasswordOff);
            goto L69
        L76:
            if (r19 != PrivacySettingsActivity.m17753$$Nest$fgetpasscodeRow(r46)) goto L84;
            if (SharedConfig.passcodeHash.length() == 0) goto L81;
            String r02 = LocaleController.getString(R.string.PasswordOn);
            int r112 = R.drawable.msg2_secret;
        L82:
            r11.setTextAndValueAndIcon(LocaleController.getString(R.string.Passcode), r02, true, r112, true);
            goto L93
        L81:
            r02 = LocaleController.getString(R.string.PasswordOff);
            r112 = R.drawable.msg2_secret;
            goto L82
        L84:
            if (r19 != PrivacySettingsActivity.m17722$$Nest$fgetblockedRow(this.this$0)) goto L93;
            int r03 = this.this$0.getMessagesController().totalBlockedCount;
            if (r03 != 0) goto L89;
            String r74 = LocaleController.getString("BlockedEmpty", R.string.BlockedEmpty);
        L88:
            String r134 = r74;
        L92:
            r11.setTextAndValueAndIcon(LocaleController.getString("BlockedUsers", R.string.BlockedUsers), r134, true, R.drawable.msg2_block2, true);
            goto L93
        L89:
            if (r03 <= 0) goto L91;
            r74 = String.format(LocaleController.getInstance().getCurrentLocale(), TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r03)});
            goto L88
        L91:
            r134 = _UrlKt.FRAGMENT_ENCODE_SET;
            r9 = true;
            goto L92
        L40:
            if (PrivacySettingsActivity.m17733$$Nest$fgetcurrentPassword(r44) != null) goto L42;
            r9 = true;
            String r75 = r7;
        L47:
            r11.setPrioritizeTitleOverValue(true);
            r11.setTextAndSpoilersValueAndIcon(LocaleController.getString(R.string.EmailLogin), r75, R.drawable.msg2_email, true);
            goto L93
        L42:
            SpannableStringBuilder r76 = SpannableStringBuilder.valueOf(PrivacySettingsActivity.m17733$$Nest$fgetcurrentPassword(this.this$0).login_email_pattern);
            int r113 = PrivacySettingsActivity.m17733$$Nest$fgetcurrentPassword(this.this$0).login_email_pattern.indexOf(42);
            int r04 = PrivacySettingsActivity.m17733$$Nest$fgetcurrentPassword(this.this$0).login_email_pattern.lastIndexOf(42);
            r75 = r76;
            r75 = r76;
            r75 = r76;
            if (r113 == r04) goto L47;
            if (r113 == (-1)) goto L47;
            if (r04 == (-1)) goto L47;
            TextStyleSpan.TextStyleRun r2 = new TextStyleSpan.TextStyleRun();
            r2.flags |= 256;
            r2.start = r113;
            int r05 = r04 + 1;
            r2.end = r05;
            r76.setSpan(new TextStyleSpan(r2), r113, r05, 0);
            r75 = r76;
            goto L47
        L29:
            if (PrivacySettingsActivity.m17737$$Nest$fgetdevicesActivityPreload(r43).getSessionsCount() == 0) goto L31;
            String r77 = String.format(LocaleController.getInstance().getCurrentLocale(), TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(PrivacySettingsActivity.m17737$$Nest$fgetdevicesActivityPreload(this.this$0).getSessionsCount())});
        L34:
            String r135 = r77;
        L36:
            this.this$0.getMessagesController().lastKnownSessionsCount = PrivacySettingsActivity.m17737$$Nest$fgetdevicesActivityPreload(this.this$0).getSessionsCount();
            r11.setTextAndValueAndIcon(LocaleController.getString(R.string.SessionsTitle), r135, true, R.drawable.msg2_devices, false);
            goto L93
        L31:
            if (this.this$0.getMessagesController().lastKnownSessionsCount != 0) goto L33;
            r135 = _UrlKt.FRAGMENT_ENCODE_SET;
            r9 = true;
            goto L36
        L33:
            r77 = String.format(LocaleController.getInstance().getCurrentLocale(), TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(this.this$0.getMessagesController().lastKnownSessionsCount)});
            goto L34
        L13:
            if (((Integer) r18.itemView.getTag()).intValue() != r19) goto L15;
            r33 = true;
            goto L16
        L339:
            return;
        L95:
            TextCheckCell r114 = (TextCheckCell) r18.itemView;
            if (r19 != PrivacySettingsActivity.m17767$$Nest$fgetsecretWebpageRow(this.this$0)) goto L104;
            String r22 = LocaleController.getString("SecretWebPage", R.string.SecretWebPage);
            if (this.this$0.getMessagesController().secretWebpagePreview == 1) goto L101;
            r10 = false;
        L101:
            r114.setTextAndCheck(r22, r10, false);
            return;
        L104:
            if (r19 != PrivacySettingsActivity.m17732$$Nest$fgetcontactsSyncRow(this.this$0)) goto L108;
            r114.setTextAndCheck(LocaleController.getString("SyncContacts", R.string.SyncContacts), PrivacySettingsActivity.m17750$$Nest$fgetnewSync(this.this$0), true);
            return;
        L108:
            if (r19 != PrivacySettingsActivity.m17731$$Nest$fgetcontactsSuggestRow(this.this$0)) goto L112;
            r114.setTextAndCheck(LocaleController.getString("SuggestContacts", R.string.SuggestContacts), PrivacySettingsActivity.m17749$$Nest$fgetnewSuggest(this.this$0), false);
            return;
        L112:
            if (r19 != PrivacySettingsActivity.m17747$$Nest$fgetnewChatsRow(this.this$0)) goto L336;
            r114.setTextAndCheck(LocaleController.getString("ArchiveAndMute", R.string.ArchiveAndMute), PrivacySettingsActivity.m17718$$Nest$fgetarchiveChats(this.this$0), false);
            return;
        L336:
            return;
        L143:
            TextInfoPrivacyCell r115 = (TextInfoPrivacyCell) r18.itemView;
            getItemCount();
            if (r19 != PrivacySettingsActivity.m17734$$Nest$fgetdeleteAccountDetailRow(this.this$0)) goto L148;
            r115.setText(LocaleController.getString("DeleteAccountHelp", R.string.DeleteAccountHelp));
            return;
        L148:
            if (r19 != PrivacySettingsActivity.m17742$$Nest$fgetgroupsDetailRow(this.this$0)) goto L152;
            r115.setText(LocaleController.getString("GroupsAndChannelsHelp", R.string.GroupsAndChannelsHelp));
            return;
        L152:
            if (r19 != PrivacySettingsActivity.m17769$$Nest$fgetsessionsDetailRow(this.this$0)) goto L156;
            r115.setText(LocaleController.getString("SessionsSettingsInfo", R.string.SessionsSettingsInfo));
            return;
        L156:
            if (r19 != PrivacySettingsActivity.m17763$$Nest$fgetsecretDetailRow(this.this$0)) goto L160;
            r115.setText(LocaleController.getString("SecretWebPageInfo", R.string.SecretWebPageInfo));
            return;
        L160:
            if (r19 != PrivacySettingsActivity.m17725$$Nest$fgetbotsDetailRow(this.this$0)) goto L164;
            r115.setText(LocaleController.getString("PrivacyBotsInfo", R.string.PrivacyBotsInfo));
            return;
        L164:
            if (r19 != PrivacySettingsActivity.m17760$$Nest$fgetprivacyShadowRow(this.this$0)) goto L168;
            r115.setText(LocaleController.getString(R.string.PrivacyInvitesInfo));
            return;
        L168:
            if (r19 != PrivacySettingsActivity.m17729$$Nest$fgetcontactsDetailRow(this.this$0)) goto L172;
            r115.setText(LocaleController.getString("SuggestContactsInfo", R.string.SuggestContactsInfo));
            return;
        L172:
            if (r19 != PrivacySettingsActivity.m17748$$Nest$fgetnewChatsSectionRow(this.this$0)) goto L338;
            r115.setText(LocaleController.getString("ArchiveAndMuteInfo", R.string.ArchiveAndMuteInfo));
            return;
        L338:
            return;
        L176:
            if (r18.itemView.getTag() != null) goto L178;
        L180:
            boolean r34 = false;
        L181:
            r18.itemView.setTag(Integer.valueOf(r19));
            TextSettingsCell r116 = (TextSettingsCell) r18.itemView;
            r116.setBetterLayout(true);
            if (r19 != PrivacySettingsActivity.m17772$$Nest$fgetwebSessionsRow(this.this$0)) goto L184;
            r116.setText(LocaleController.getString("WebSessionsTitle", R.string.WebSessionsTitle), false);
        L334:
            r116.setDrawLoading(r9, r5, r34);
            return;
        L184:
            int r117 = PrivacySettingsActivity.m17758$$Nest$fgetphoneNumberRow(this.this$0);
            PrivacySettingsActivity r122 = this.this$0;
            if (r19 == r117) goto L187;
            int r118 = PrivacySettingsActivity.m17744$$Nest$fgetlastSeenRow(r122);
            PrivacySettingsActivity r123 = this.this$0;
            if (r19 == r118) goto L194;
            int r119 = PrivacySettingsActivity.m17743$$Nest$fgetgroupsRow(r123);
            PrivacySettingsActivity r124 = this.this$0;
            if (r19 == r119) goto L201;
            int r1110 = PrivacySettingsActivity.m17727$$Nest$fgetcallsRow(r124);
            PrivacySettingsActivity r125 = this.this$0;
            if (r19 == r1110) goto L209;
            int r1111 = PrivacySettingsActivity.m17761$$Nest$fgetprofilePhotoRow(r125);
            PrivacySettingsActivity r126 = this.this$0;
            if (r19 == r1111) goto L216;
            int r1112 = PrivacySettingsActivity.m17720$$Nest$fgetbioRow(r126);
            PrivacySettingsActivity r127 = this.this$0;
            if (r19 == r1112) goto L223;
            int r1113 = PrivacySettingsActivity.m17745$$Nest$fgetmusicRow(r127);
            PrivacySettingsActivity r128 = this.this$0;
            if (r19 == r1113) goto L230;
            int r1114 = PrivacySettingsActivity.m17721$$Nest$fgetbirthdayRow(r128);
            PrivacySettingsActivity r129 = this.this$0;
            if (r19 == r1114) goto L237;
            int r1115 = PrivacySettingsActivity.m17741$$Nest$fgetgiftsRow(r129);
            PrivacySettingsActivity r1210 = this.this$0;
            if (r19 == r1115) goto L244;
            int r1116 = PrivacySettingsActivity.m17740$$Nest$fgetforwardsRow(r1210);
            PrivacySettingsActivity r1211 = this.this$0;
            if (r19 == r1116) goto L251;
            int r62 = PrivacySettingsActivity.m17771$$Nest$fgetvoicesRow(r1211);
            PrivacySettingsActivity r1117 = this.this$0;
            if (r19 == r62) goto L258;
            int r63 = PrivacySettingsActivity.m17751$$Nest$fgetnoncontactsRow(r1117);
            PrivacySettingsActivity r1118 = this.this$0;
            if (r19 != r63) goto L290;
            if (PrivacySettingsActivity.m17739$$Nest$fgetfeeValue(r1118) == false) goto L275;
            int r23 = R.string.ContactsAndFee;
        L278:
            String r24 = LocaleController.getString(r23);
            if (this.this$0.getMessagesController().newNoncontactPeersRequirePremiumWithoutOwnpremium == true) goto L281;
        L283:
            CharSequence r47 = PrivacySettingsActivity.addPremiumStar(LocaleController.getString(R.string.PrivacyMessages));
        L285:
            if (PrivacySettingsActivity.m17745$$Nest$fgetmusicRow(this.this$0) != (-1)) goto L288;
            r10 = false;
        L288:
            r116.setTextAndValue(r47, r24, r10);
            goto L334
        L281:
            if (this.this$0.getMessagesController().starsPaidMessagesAvailable == true) goto L283;
            r47 = LocaleController.getString(R.string.PrivacyMessages);
            goto L285
        L275:
            if (PrivacySettingsActivity.m17752$$Nest$fgetnoncontactsValue(this.this$0) == false) goto L277;
            r23 = R.string.ContactsAndPremium;
            goto L278
        L277:
            r23 = R.string.P2PEverybody;
            goto L278
        L290:
            if (r19 != PrivacySettingsActivity.m17755$$Nest$fgetpassportRow(r1118)) goto L292;
            r116.setText(LocaleController.getString("TelegramPassport", R.string.TelegramPassport), true);
            goto L334
        L292:
            int r64 = PrivacySettingsActivity.m17735$$Nest$fgetdeleteAccountRow(this.this$0);
            PrivacySettingsActivity r8 = this.this$0;
            if (r19 != r64) goto L315;
            if (PrivacySettingsActivity.access$2400(r8).getLoadingDeleteInfo() == true) goto L313;
            int r25 = PrivacySettingsActivity.access$2500(this.this$0).getDeleteAccountTTL();
            if (r25 > 182) goto L302;
            r4 = LocaleController.formatPluralString("Months", r25 / 30, new Object[0]);
        L300:
            r10 = false;
            goto L313
        L302:
            if (r25 != 365) goto L305;
            r4 = LocaleController.formatPluralString("Months", 12, new Object[0]);
            goto L300
        L305:
            if (r25 != 548) goto L308;
            r4 = LocaleController.formatPluralString("Months", 18, new Object[0]);
            goto L300
        L308:
            if (r25 != 730) goto L310;
            r4 = LocaleController.formatPluralString("Months", 24, new Object[0]);
            goto L300
        L310:
            if (r25 <= 30) goto L312;
            r4 = LocaleController.formatPluralString("Months", (int) Math.round(r25 / 30.0d), new Object[0]);
            goto L300
        L312:
            r4 = LocaleController.formatPluralString("Days", r25, new Object[0]);
        L313:
            r116.setTextAndValue(LocaleController.getString("DeleteAccountIfAwayFor3", R.string.DeleteAccountIfAwayFor3), r4, PrivacySettingsActivity.m17736$$Nest$fgetdeleteAccountUpdate(this.this$0), false);
            PrivacySettingsActivity.m17773$$Nest$fputdeleteAccountUpdate(this.this$0, false);
        L205:
            r9 = r10;
            goto L334
        L315:
            if (r19 != PrivacySettingsActivity.m17757$$Nest$fgetpaymentsClearRow(r8)) goto L318;
            r116.setText(LocaleController.getString("PrivacyPaymentsClear", R.string.PrivacyPaymentsClear), true);
            goto L334
        L318:
            if (r19 != PrivacySettingsActivity.m17724$$Nest$fgetbotsBiometryRow(this.this$0)) goto L321;
            r116.setText(LocaleController.getString(R.string.PrivacyBiometryBotsButton), true);
            goto L334
        L321:
            if (r19 != PrivacySettingsActivity.m17764$$Nest$fgetsecretMapRow(this.this$0)) goto L332;
            int r26 = SharedConfig.mapPreviewType;
            if (r26 == 0) goto L329;
            if (r26 == 1) goto L328;
            if (r26 == 2) goto L327;
            String r27 = LocaleController.getString("MapPreviewProviderYandex", R.string.MapPreviewProviderYandex);
        L330:
            r116.setTextAndValue(LocaleController.getString("MapPreviewProvider", R.string.MapPreviewProvider), r27, PrivacySettingsActivity.m17765$$Nest$fgetsecretMapUpdate(this.this$0), true);
            PrivacySettingsActivity.m17774$$Nest$fputsecretMapUpdate(this.this$0, false);
            goto L334
        L327:
            r27 = LocaleController.getString("MapPreviewProviderNobody", R.string.MapPreviewProviderNobody);
            goto L330
        L328:
            r27 = LocaleController.getString("MapPreviewProviderGoogle", R.string.MapPreviewProviderGoogle);
            goto L330
        L329:
            r27 = LocaleController.getString("MapPreviewProviderTelegram", R.string.MapPreviewProviderTelegram);
            goto L330
        L332:
            if (r19 != PrivacySettingsActivity.m17728$$Nest$fgetcontactsDeleteRow(this.this$0)) goto L334;
            r116.setText(LocaleController.getString("SyncContactsDelete", R.string.SyncContactsDelete), true);
            goto L334
        L258:
            if (PrivacySettingsActivity.access$2300(r1117).getLoadingPrivacyInfo(8) == false) goto L261;
            boolean r28 = true;
            r5 = 30;
        L265:
            CharSequence r65 = PrivacySettingsActivity.addPremiumStar(LocaleController.getString(R.string.PrivacyVoiceMessages));
            if (PrivacySettingsActivity.m17751$$Nest$fgetnoncontactsRow(this.this$0) == (-1)) goto L268;
            r9 = true;
        L268:
            r116.setTextAndValue(r65, r4, r9);
            r116.getValueImageView().setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon), PorterDuff.Mode.MULTIPLY));
            r9 = r28;
            goto L334
        L261:
            if (this.this$0.getUserConfig().isPremium() == true) goto L264;
            r4 = LocaleController.getString(R.string.P2PEverybody);
        L263:
            r28 = false;
            goto L265
        L264:
            r4 = PrivacySettingsActivity.formatRulesString(this.this$0.getAccountInstance(), 8);
            goto L263
        L251:
            if (PrivacySettingsActivity.access$2200(r1211).getLoadingPrivacyInfo(5) == false) goto L253;
            r9 = true;
            r5 = 30;
        L254:
            r116.setTextAndValue(LocaleController.getString("PrivacyForwards", R.string.PrivacyForwards), r4, true);
            goto L334
        L253:
            r4 = PrivacySettingsActivity.formatRulesString(this.this$0.getAccountInstance(), 5);
            goto L254
        L244:
            if (PrivacySettingsActivity.access$2100(r1210).getLoadingPrivacyInfo(12) == false) goto L246;
            r9 = true;
            r5 = 30;
        L247:
            r116.setTextAndValue(LocaleController.getString(R.string.PrivacyGifts), r4, true);
            goto L334
        L246:
            r4 = PrivacySettingsActivity.formatRulesString(this.this$0.getAccountInstance(), 12);
            goto L247
        L237:
            if (PrivacySettingsActivity.access$2000(r129).getLoadingPrivacyInfo(11) == false) goto L239;
            r9 = true;
            r5 = 30;
        L240:
            r116.setTextAndValue(LocaleController.getString(R.string.PrivacyBirthday), r4, true);
            goto L334
        L239:
            r4 = PrivacySettingsActivity.formatRulesString(this.this$0.getAccountInstance(), 11);
            goto L240
        L230:
            if (PrivacySettingsActivity.access$1900(r128).getLoadingPrivacyInfo(14) == false) goto L232;
            r9 = true;
            r5 = 30;
        L233:
            r116.setTextAndValue(LocaleController.getString(R.string.PrivacyMusic), r4, true);
            goto L334
        L232:
            r4 = PrivacySettingsActivity.formatRulesString(this.this$0.getAccountInstance(), 14);
            goto L233
        L223:
            if (PrivacySettingsActivity.access$1800(r127).getLoadingPrivacyInfo(9) == false) goto L225;
            r9 = true;
            r5 = 30;
        L226:
            r116.setTextAndValue(LocaleController.getString("PrivacyBio", R.string.PrivacyBio), r4, true);
            goto L334
        L225:
            r4 = PrivacySettingsActivity.formatRulesString(this.this$0.getAccountInstance(), 9);
            goto L226
        L216:
            if (PrivacySettingsActivity.access$1700(r126).getLoadingPrivacyInfo(4) == false) goto L218;
            r9 = true;
            r5 = 30;
        L219:
            r116.setTextAndValue(LocaleController.getString("PrivacyProfilePhoto", R.string.PrivacyProfilePhoto), r4, true);
            goto L334
        L218:
            r4 = PrivacySettingsActivity.formatRulesString(this.this$0.getAccountInstance(), 4);
            goto L219
        L209:
            if (PrivacySettingsActivity.access$1600(r125).getLoadingPrivacyInfo(2) == false) goto L211;
            r9 = true;
            r5 = 30;
        L212:
            r116.setTextAndValue(LocaleController.getString("Calls", R.string.Calls), r4, true);
            goto L334
        L211:
            r4 = PrivacySettingsActivity.formatRulesString(this.this$0.getAccountInstance(), 2);
            goto L212
        L201:
            if (PrivacySettingsActivity.access$1500(r124).getLoadingPrivacyInfo(1) == false) goto L203;
            r5 = 30;
        L204:
            r116.setTextAndValue(LocaleController.getString(R.string.PrivacyInvites), r4, false);
            goto L205
        L203:
            r4 = PrivacySettingsActivity.formatRulesString(this.this$0.getAccountInstance(), 1);
            r10 = false;
            goto L204
        L194:
            if (PrivacySettingsActivity.access$1400(r123).getLoadingPrivacyInfo(0) == false) goto L196;
            r9 = true;
            r5 = 30;
        L197:
            r116.setTextAndValue(LocaleController.getString("PrivacyLastSeen", R.string.PrivacyLastSeen), r4, true);
            goto L334
        L196:
            r4 = PrivacySettingsActivity.formatRulesString(this.this$0.getAccountInstance(), 0);
            goto L197
        L187:
            if (PrivacySettingsActivity.access$1300(r122).getLoadingPrivacyInfo(6) == false) goto L189;
            r9 = true;
            r5 = 30;
        L190:
            r116.setTextAndValue(LocaleController.getString("PrivacyPhone", R.string.PrivacyPhone), r4, true);
            goto L334
        L189:
            r4 = PrivacySettingsActivity.formatRulesString(this.this$0.getAccountInstance(), 6);
            goto L190
        L178:
            if (((Integer) r18.itemView.getTag()).intValue() != r19) goto L180;
            r34 = true;
            goto L181
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            if (r2 != 0) goto L4;
            View r12 = new TextSettingsCell(this.mContext);
        L18:
            return new RecyclerListView.Holder(r12);
        L4:
            if (r2 != 1) goto L6;
            r12 = new TextInfoPrivacyCell(this.mContext);
            goto L18
        L6:
            if (r2 != 2) goto L8;
            r12 = new HeaderCell(this.mContext);
            goto L18
        L8:
            if (r2 != 4) goto L10;
            r12 = new ShadowSectionCell(this.mContext);
            goto L18
        L10:
            if (r2 == 5) goto L12;
            r12 = new TextCheckCell(this.mContext);
            goto L18
        L12:
            r12 = new TextCell(this.mContext);
            goto L18
        }
    }

    public static /* synthetic */ void $r8$lambda$1cnHm4Rsgo1izGcQwiq0ynYgWAY(PrivacySettingsActivity r0, View r1) {
        r0.lambda$createView$16(r1);
    }

    /* renamed from: $r8$lambda$2bInNxaSuXSHzPgnMbf4Bcr-vEg, reason: not valid java name */
    public static /* synthetic */ void m17709$r8$lambda$2bInNxaSuXSHzPgnMbf4BcrvEg(PrivacySettingsActivity r0, AlertDialog.Builder r1, View r2) {
        r0.lambda$createView$6(r1, r2);
    }

    /* renamed from: $r8$lambda$2qbr58jGKx2oeZ87pF-Z8fnqVBo, reason: not valid java name */
    public static /* synthetic */ void m17710$r8$lambda$2qbr58jGKx2oeZ87pFZ8fnqVBo(PrivacySettingsActivity r0) {
        r0.lambda$createView$10();
    }

    public static /* synthetic */ void $r8$lambda$3dhBJTAc470lYK6N_CU5rbp2w1M(PrivacySettingsActivity r0, TL_account.Password r1) {
        r0.lambda$loadPasswordSettings$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$6tM66M8fYAphlC4o33E5HWxc930(PrivacySettingsActivity r0, TL_account.Passkeys r1, TLRPC.TL_error r2) {
        r0.lambda$loadPasskeys$24(r1, r2);
    }

    /* renamed from: $r8$lambda$7czsUpWG7j-37HeEtypf6rh-Pyo, reason: not valid java name */
    public static /* synthetic */ void m17711$r8$lambda$7czsUpWG7j37HeEtypf6rhPyo(PrivacySettingsActivity r0) {
        r0.lambda$createView$9();
    }

    /* renamed from: $r8$lambda$DPP8-PtFa-lvDYsSlbRJJHb1FeA, reason: not valid java name */
    public static /* synthetic */ void m17712$r8$lambda$DPP8PtFalvDYsSlbRJJHb1FeA(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$Krb9AQsYKcIdMaf6USXsZ323DXQ(PrivacySettingsActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadPasswordSettings$23(r1, r2);
    }

    /* renamed from: $r8$lambda$MEfk8yq7_9EulFHE7FC_OM-cOGY, reason: not valid java name */
    public static /* synthetic */ void m17713$r8$lambda$MEfk8yq7_9EulFHE7FC_OMcOGY(PrivacySettingsActivity r0, TextCheckCell r1, AlertDialog r2, int r3) {
        r0.lambda$createView$14(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Ot_dLFdXNNJxpnJ502-DrfJnkk4, reason: not valid java name */
    public static /* synthetic */ void m17714$r8$lambda$Ot_dLFdXNNJxpnJ502DrfJnkk4(PrivacySettingsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$8(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Qo2Ia02P3PunutkjU759z41m4TY(PrivacySettingsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$TbCpifjfFg58q91BkxQAW__hsb8(PrivacySettingsActivity r0, AlertDialog r1, TLObject r2, TL_account.setAccountTTL r3) {
        r0.lambda$createView$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$VcobjhZWboRxZAiAltI45G2egHI(PrivacySettingsActivity r0) {
        r0.lambda$createView$7();
    }

    public static /* synthetic */ void $r8$lambda$YJxV2B3O4Gxwidnpe9hjduwW5fc(PrivacySettingsActivity r0) {
        r0.lambda$onFragmentCreate$1();
    }

    public static /* synthetic */ void $r8$lambda$_ROrzV_QIHwj6awTi9NLspbxPwI(PrivacySettingsActivity r0, TextCheckCell r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$createView$13(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$bQjitvoXaCUubuiYRbmANLB1EAE(PrivacySettingsActivity r0, AlertDialog r1, TL_account.setAccountTTL r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$createView$5(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$nCUBhXGADMz2X7VlwI6Kii7dtoM(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$ozrsefcIfWZf5i3CX7ZHyh19qJA(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$pleyTE38w8CiuUNF2DEVgGpMjNQ(PrivacySettingsActivity r0, ArrayList r1) {
        r0.lambda$createView$21(r1);
    }

    /* renamed from: $r8$lambda$sQjYztcjqA_KKu1utMkYjzl-Dog, reason: not valid java name */
    public static /* synthetic */ void m17715$r8$lambda$sQjYztcjqA_KKu1utMkYjzlDog(PrivacySettingsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$19(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$v2lR5qtKdQHPXjWlD1wlykYEZ7c(PrivacySettingsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$18(r1, r2);
    }

    /* renamed from: $r8$lambda$xNG_f-E1A9zBLo8bIIDEdiKRT1c, reason: not valid java name */
    public static /* synthetic */ void m17716$r8$lambda$xNG_fE1A9zBLo8bIIDEdiKRT1c(PrivacySettingsActivity r0, Context r1, View r2, int r3) {
        r0.lambda$createView$20(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$xzYW5WrrMS0MxuH4rWdwof1nmFI(PrivacySettingsActivity r0, TextCheckCell r1) {
        r0.lambda$createView$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$yD4lVSTKmcb6hzPAtv741b78HgY(PrivacySettingsActivity r0) {
        r0.lambda$createView$15();
    }

    public static /* synthetic */ void $r8$lambda$zlJeq_JmJLqI9U_gLhd_hzcYZLA(PrivacySettingsActivity r0) {
        r0.lambda$onFragmentCreate$0();
    }

    /* renamed from: -$$Nest$fgetadvancedSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17717$$Nest$fgetadvancedSectionRow(PrivacySettingsActivity r0) {
        return r0.advancedSectionRow;
    }

    /* renamed from: -$$Nest$fgetarchiveChats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17718$$Nest$fgetarchiveChats(PrivacySettingsActivity r0) {
        return r0.archiveChats;
    }

    /* renamed from: -$$Nest$fgetautoDeleteMesages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17719$$Nest$fgetautoDeleteMesages(PrivacySettingsActivity r0) {
        return r0.autoDeleteMesages;
    }

    /* renamed from: -$$Nest$fgetbioRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17720$$Nest$fgetbioRow(PrivacySettingsActivity r0) {
        return r0.bioRow;
    }

    /* renamed from: -$$Nest$fgetbirthdayRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17721$$Nest$fgetbirthdayRow(PrivacySettingsActivity r0) {
        return r0.birthdayRow;
    }

    /* renamed from: -$$Nest$fgetblockedRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17722$$Nest$fgetblockedRow(PrivacySettingsActivity r0) {
        return r0.blockedRow;
    }

    /* renamed from: -$$Nest$fgetbotsAndWebsitesShadowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17723$$Nest$fgetbotsAndWebsitesShadowRow(PrivacySettingsActivity r0) {
        return r0.botsAndWebsitesShadowRow;
    }

    /* renamed from: -$$Nest$fgetbotsBiometryRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17724$$Nest$fgetbotsBiometryRow(PrivacySettingsActivity r0) {
        return r0.botsBiometryRow;
    }

    /* renamed from: -$$Nest$fgetbotsDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17725$$Nest$fgetbotsDetailRow(PrivacySettingsActivity r0) {
        return r0.botsDetailRow;
    }

    /* renamed from: -$$Nest$fgetbotsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17726$$Nest$fgetbotsSectionRow(PrivacySettingsActivity r0) {
        return r0.botsSectionRow;
    }

    /* renamed from: -$$Nest$fgetcallsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17727$$Nest$fgetcallsRow(PrivacySettingsActivity r0) {
        return r0.callsRow;
    }

    /* renamed from: -$$Nest$fgetcontactsDeleteRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17728$$Nest$fgetcontactsDeleteRow(PrivacySettingsActivity r0) {
        return r0.contactsDeleteRow;
    }

    /* renamed from: -$$Nest$fgetcontactsDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17729$$Nest$fgetcontactsDetailRow(PrivacySettingsActivity r0) {
        return r0.contactsDetailRow;
    }

    /* renamed from: -$$Nest$fgetcontactsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17730$$Nest$fgetcontactsSectionRow(PrivacySettingsActivity r0) {
        return r0.contactsSectionRow;
    }

    /* renamed from: -$$Nest$fgetcontactsSuggestRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17731$$Nest$fgetcontactsSuggestRow(PrivacySettingsActivity r0) {
        return r0.contactsSuggestRow;
    }

    /* renamed from: -$$Nest$fgetcontactsSyncRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17732$$Nest$fgetcontactsSyncRow(PrivacySettingsActivity r0) {
        return r0.contactsSyncRow;
    }

    /* renamed from: -$$Nest$fgetcurrentPassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_account.Password m17733$$Nest$fgetcurrentPassword(PrivacySettingsActivity r0) {
        return r0.currentPassword;
    }

    /* renamed from: -$$Nest$fgetdeleteAccountDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17734$$Nest$fgetdeleteAccountDetailRow(PrivacySettingsActivity r0) {
        return r0.deleteAccountDetailRow;
    }

    /* renamed from: -$$Nest$fgetdeleteAccountRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17735$$Nest$fgetdeleteAccountRow(PrivacySettingsActivity r0) {
        return r0.deleteAccountRow;
    }

    /* renamed from: -$$Nest$fgetdeleteAccountUpdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17736$$Nest$fgetdeleteAccountUpdate(PrivacySettingsActivity r0) {
        return r0.deleteAccountUpdate;
    }

    /* renamed from: -$$Nest$fgetdevicesActivityPreload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SessionsActivity m17737$$Nest$fgetdevicesActivityPreload(PrivacySettingsActivity r0) {
        return r0.devicesActivityPreload;
    }

    /* renamed from: -$$Nest$fgetemailLoginRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17738$$Nest$fgetemailLoginRow(PrivacySettingsActivity r0) {
        return r0.emailLoginRow;
    }

    /* renamed from: -$$Nest$fgetfeeValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17739$$Nest$fgetfeeValue(PrivacySettingsActivity r0) {
        return r0.feeValue;
    }

    /* renamed from: -$$Nest$fgetforwardsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17740$$Nest$fgetforwardsRow(PrivacySettingsActivity r0) {
        return r0.forwardsRow;
    }

    /* renamed from: -$$Nest$fgetgiftsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17741$$Nest$fgetgiftsRow(PrivacySettingsActivity r0) {
        return r0.giftsRow;
    }

    /* renamed from: -$$Nest$fgetgroupsDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17742$$Nest$fgetgroupsDetailRow(PrivacySettingsActivity r0) {
        return r0.groupsDetailRow;
    }

    /* renamed from: -$$Nest$fgetgroupsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17743$$Nest$fgetgroupsRow(PrivacySettingsActivity r0) {
        return r0.groupsRow;
    }

    /* renamed from: -$$Nest$fgetlastSeenRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17744$$Nest$fgetlastSeenRow(PrivacySettingsActivity r0) {
        return r0.lastSeenRow;
    }

    /* renamed from: -$$Nest$fgetmusicRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17745$$Nest$fgetmusicRow(PrivacySettingsActivity r0) {
        return r0.musicRow;
    }

    /* renamed from: -$$Nest$fgetnewChatsHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17746$$Nest$fgetnewChatsHeaderRow(PrivacySettingsActivity r0) {
        return r0.newChatsHeaderRow;
    }

    /* renamed from: -$$Nest$fgetnewChatsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17747$$Nest$fgetnewChatsRow(PrivacySettingsActivity r0) {
        return r0.newChatsRow;
    }

    /* renamed from: -$$Nest$fgetnewChatsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17748$$Nest$fgetnewChatsSectionRow(PrivacySettingsActivity r0) {
        return r0.newChatsSectionRow;
    }

    /* renamed from: -$$Nest$fgetnewSuggest, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17749$$Nest$fgetnewSuggest(PrivacySettingsActivity r0) {
        return r0.newSuggest;
    }

    /* renamed from: -$$Nest$fgetnewSync, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17750$$Nest$fgetnewSync(PrivacySettingsActivity r0) {
        return r0.newSync;
    }

    /* renamed from: -$$Nest$fgetnoncontactsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17751$$Nest$fgetnoncontactsRow(PrivacySettingsActivity r0) {
        return r0.noncontactsRow;
    }

    /* renamed from: -$$Nest$fgetnoncontactsValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17752$$Nest$fgetnoncontactsValue(PrivacySettingsActivity r0) {
        return r0.noncontactsValue;
    }

    /* renamed from: -$$Nest$fgetpasscodeRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17753$$Nest$fgetpasscodeRow(PrivacySettingsActivity r0) {
        return r0.passcodeRow;
    }

    /* renamed from: -$$Nest$fgetpasskeysRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17754$$Nest$fgetpasskeysRow(PrivacySettingsActivity r0) {
        return r0.passkeysRow;
    }

    /* renamed from: -$$Nest$fgetpassportRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17755$$Nest$fgetpassportRow(PrivacySettingsActivity r0) {
        return r0.passportRow;
    }

    /* renamed from: -$$Nest$fgetpasswordRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17756$$Nest$fgetpasswordRow(PrivacySettingsActivity r0) {
        return r0.passwordRow;
    }

    /* renamed from: -$$Nest$fgetpaymentsClearRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17757$$Nest$fgetpaymentsClearRow(PrivacySettingsActivity r0) {
        return r0.paymentsClearRow;
    }

    /* renamed from: -$$Nest$fgetphoneNumberRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17758$$Nest$fgetphoneNumberRow(PrivacySettingsActivity r0) {
        return r0.phoneNumberRow;
    }

    /* renamed from: -$$Nest$fgetprivacySectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17759$$Nest$fgetprivacySectionRow(PrivacySettingsActivity r0) {
        return r0.privacySectionRow;
    }

    /* renamed from: -$$Nest$fgetprivacyShadowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17760$$Nest$fgetprivacyShadowRow(PrivacySettingsActivity r0) {
        return r0.privacyShadowRow;
    }

    /* renamed from: -$$Nest$fgetprofilePhotoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17761$$Nest$fgetprofilePhotoRow(PrivacySettingsActivity r0) {
        return r0.profilePhotoRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17762$$Nest$fgetrowCount(PrivacySettingsActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsecretDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17763$$Nest$fgetsecretDetailRow(PrivacySettingsActivity r0) {
        return r0.secretDetailRow;
    }

    /* renamed from: -$$Nest$fgetsecretMapRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17764$$Nest$fgetsecretMapRow(PrivacySettingsActivity r0) {
        return r0.secretMapRow;
    }

    /* renamed from: -$$Nest$fgetsecretMapUpdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17765$$Nest$fgetsecretMapUpdate(PrivacySettingsActivity r0) {
        return r0.secretMapUpdate;
    }

    /* renamed from: -$$Nest$fgetsecretSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17766$$Nest$fgetsecretSectionRow(PrivacySettingsActivity r0) {
        return r0.secretSectionRow;
    }

    /* renamed from: -$$Nest$fgetsecretWebpageRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17767$$Nest$fgetsecretWebpageRow(PrivacySettingsActivity r0) {
        return r0.secretWebpageRow;
    }

    /* renamed from: -$$Nest$fgetsecuritySectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17768$$Nest$fgetsecuritySectionRow(PrivacySettingsActivity r0) {
        return r0.securitySectionRow;
    }

    /* renamed from: -$$Nest$fgetsessionsDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17769$$Nest$fgetsessionsDetailRow(PrivacySettingsActivity r0) {
        return r0.sessionsDetailRow;
    }

    /* renamed from: -$$Nest$fgetsessionsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17770$$Nest$fgetsessionsRow(PrivacySettingsActivity r0) {
        return r0.sessionsRow;
    }

    /* renamed from: -$$Nest$fgetvoicesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17771$$Nest$fgetvoicesRow(PrivacySettingsActivity r0) {
        return r0.voicesRow;
    }

    /* renamed from: -$$Nest$fgetwebSessionsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17772$$Nest$fgetwebSessionsRow(PrivacySettingsActivity r0) {
        return r0.webSessionsRow;
    }

    /* renamed from: -$$Nest$fputdeleteAccountUpdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17773$$Nest$fputdeleteAccountUpdate(PrivacySettingsActivity r0, boolean r1) {
        r0.deleteAccountUpdate = r1;
    }

    /* renamed from: -$$Nest$fputsecretMapUpdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17774$$Nest$fputsecretMapUpdate(PrivacySettingsActivity r0, boolean r1) {
        r0.secretMapUpdate = r1;
    }

    public PrivacySettingsActivity() {
        this.biometryBots = new ArrayList();
        this.clear = new boolean[2];
    }

    public static /* synthetic */ ContactsController access$000(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$100(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$1000(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$1100(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$1200(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$1300(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$1400(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$1500(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$1600(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$1700(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$1800(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$1900(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$200(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$2000(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$2100(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$2200(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$2300(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$2400(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$2500(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$300(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$400(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$500(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$600(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$700(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$800(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ ContactsController access$900(PrivacySettingsActivity r0) {
        return r0.getContactsController();
    }

    public static CharSequence addPremiumStar(String r5) {
        if (premiumStar != null) goto L6;
        premiumStar = new SpannableString("★");
        AnimatedEmojiDrawable.WrapSizeDrawable r0 = new AnimatedEmojiDrawable.WrapSizeDrawable(PremiumGradient.getInstance().premiumStarMenuDrawable, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        r0.setBounds(0, 0, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        premiumStar.setSpan(new ImageSpan(r0, 2), 0, premiumStar.length(), 17);
    L6:
        return new SpannableStringBuilder(r5).append(" \u2009").append(premiumStar);
    }

    public static String formatRulesString(AccountInstance r16, int r17) {
        ArrayList<TLRPC.PrivacyRule> r1 = r16.getContactsController().getPrivacyRules(r17);
        TLRPC.GlobalPrivacySettings r2 = r16.getContactsController().getGlobalPrivacySettings();
        if (r1 != null) goto L5;
    L206:
        if (r17 != 3) goto L210;
        return LocaleController.getString(R.string.P2PNobody);
    L210:
        return LocaleController.getString(R.string.LastSeenNobody);
    L5:
        if (r1.size() == 0) goto L206;
        Boolean r6 = null;
        char r10 = 65535;
        int r7 = 0;
        int r8 = 0;
        int r9 = 0;
        boolean r11 = false;
    L9:
        if (r7 >= r1.size()) goto L54;
        TLRPC.PrivacyRule r12 = r1.get(r7);
        if ((r12 instanceof TLRPC.TL_privacyValueAllowChatParticipants) == false) goto L20;
        TLRPC.TL_privacyValueAllowChatParticipants r122 = (TLRPC.TL_privacyValueAllowChatParticipants) r12;
        int r13 = r122.chats.size();
        int r14 = 0;
    L13:
        if (r14 >= r13) goto L52;
        TLRPC.Chat r5 = r16.getMessagesController().getChat(r122.chats.get(r14));
        if (r5 == null) goto L18;
        r8 = r8 + r5.participants_count;
    L18:
        r14 = r14 + 1;
    L52:
        r7 = r7 + 1;
        goto L9
    L20:
        if ((r12 instanceof TLRPC.TL_privacyValueDisallowChatParticipants) == false) goto L29;
        TLRPC.TL_privacyValueDisallowChatParticipants r123 = (TLRPC.TL_privacyValueDisallowChatParticipants) r12;
        int r52 = r123.chats.size();
        int r132 = 0;
    L22:
        if (r132 >= r52) goto L52;
        TLRPC.Chat r142 = r16.getMessagesController().getChat(r123.chats.get(r132));
        if (r142 == null) goto L27;
        r9 = r9 + r142.participants_count;
    L27:
        r132 = r132 + 1;
        goto L22
    L29:
        if ((r12 instanceof TLRPC.TL_privacyValueAllowUsers) == false) goto L32;
        r8 = r8 + ((TLRPC.TL_privacyValueAllowUsers) r12).users.size();
        goto L52
    L32:
        if ((r12 instanceof TLRPC.TL_privacyValueDisallowUsers) == false) goto L35;
        r9 = r9 + ((TLRPC.TL_privacyValueDisallowUsers) r12).users.size();
        goto L52
    L35:
        if ((r12 instanceof TLRPC.TL_privacyValueAllowPremium) == false) goto L38;
        r11 = true;
        goto L52
    L38:
        if ((r12 instanceof TLRPC.TL_privacyValueAllowBots) == false) goto L42;
        Boolean r53 = Boolean.TRUE;
    L40:
        r6 = r53;
        goto L52
    L42:
        if ((r12 instanceof TLRPC.TL_privacyValueDisallowBots) == false) goto L44;
        r53 = Boolean.FALSE;
        goto L40
    L44:
        if (r10 != 65535) goto L52;
        if ((r12 instanceof TLRPC.TL_privacyValueAllowAll) == false) goto L49;
        r10 = 0;
        goto L52
    L49:
        if ((r12 instanceof TLRPC.TL_privacyValueDisallowAll) == false) goto L51;
        r10 = 1;
        goto L52
    L51:
        r10 = 2;
        goto L52
    L54:
        if (r17 != 12) goto L68;
        if (r2 == null) goto L68;
        TLRPC.DisallowedGiftsSettings r54 = r2.disallowed_stargifts;
        if (r54 == null) goto L68;
        if (r54.disallow_unique_stargifts == false) goto L68;
        if (r54.disallow_unlimited_stargifts == false) goto L68;
        if (r54.disallow_limited_stargifts == false) goto L68;
        if (r54.disallow_premium_gifts == true) goto L68;
        return LocaleController.getString(R.string.PrivacyValueGiftsOnlyPremium);
    L68:
        if (r17 != 12) goto L82;
        if (r2 == null) goto L82;
        TLRPC.DisallowedGiftsSettings r22 = r2.disallowed_stargifts;
        if (r22 == null) goto L82;
        if (r22.disallow_unique_stargifts == false) goto L82;
        if (r22.disallow_unlimited_stargifts == false) goto L82;
        if (r22.disallow_limited_stargifts == false) goto L82;
        if (r22.disallow_premium_gifts == false) goto L82;
        return LocaleController.getString(R.string.PrivacyValueGiftsNone);
    L82:
        if (r10 == 0) goto L179;
        if (r10 != 65535) goto L86;
        if (r9 > 0) goto L179;
    L86:
        if (r10 == 2) goto L123;
        if (r10 != 65535) goto L91;
        if (r9 <= 0) goto L91;
        if (r8 > 0) goto L123;
    L91:
        if (r10 == 1) goto L101;
        if (r8 > 0) goto L101;
        if (r6 != null) goto L96;
        return "unknown";
    L96:
        if (r6.booleanValue() == true) goto L98;
        return "unknown";
    L98:
        return LocaleController.getString(R.string.PrivacyValueOnlyBots);
    L101:
        if (r17 != 3) goto L107;
        if (r8 != 0) goto L106;
        return LocaleController.getString(R.string.P2PNobody);
    L106:
        return LocaleController.formatString(R.string.P2PNobodyPlus, new Object[]{Integer.valueOf(r8)});
    L107:
        if (r8 != 0) goto L118;
        if (r11 == true) goto L110;
        if (r6 == null) goto L117;
        if (r6.booleanValue() == false) goto L117;
        return LocaleController.getString(R.string.PrivacyValueOnlyBots);
    L117:
        return LocaleController.getString(R.string.LastSeenNobody);
    L110:
        return LocaleController.getString(R.string.LastSeenNobodyPremium);
    L118:
        if (r11 == false) goto L120;
        int r0 = R.string.LastSeenNobodyPremiumPlus;
    L122:
        return LocaleController.formatString(r0, new Object[]{Integer.valueOf(r8)});
    L120:
        r0 = R.string.LastSeenNobodyPlus;
    L123:
        if (r17 != 3) goto L137;
        if (r8 != 0) goto L128;
        if (r9 != 0) goto L128;
        return LocaleController.getString("P2PContacts", R.string.P2PContacts);
    L128:
        if (r8 == 0) goto L132;
        if (r9 == 0) goto L132;
        return LocaleController.formatString(R.string.P2PContactsMinusPlus, new Object[]{Integer.valueOf(r9), Integer.valueOf(r8)});
    L132:
        if (r9 == 0) goto L136;
        return LocaleController.formatString(R.string.P2PContactsMinus, new Object[]{Integer.valueOf(r9)});
    L136:
        return LocaleController.formatString(R.string.P2PContactsPlus, new Object[]{Integer.valueOf(r8)});
    L137:
        if (r8 != 0) goto L149;
        if (r9 != 0) goto L149;
        if (r11 == true) goto L141;
        if (r6 == null) goto L148;
        if (r6.booleanValue() == false) goto L148;
        return LocaleController.getString(R.string.PrivacyContactsAndBotUsers);
    L148:
        return LocaleController.getString(R.string.LastSeenContacts);
    L141:
        return LocaleController.getString(R.string.LastSeenContactsPremium);
    L149:
        if (r8 == 0) goto L160;
        if (r9 == 0) goto L160;
        if (r6 != null) goto L153;
    L155:
        if (r11 == false) goto L157;
        int r02 = R.string.LastSeenContactsPremiumMinusPlus;
    L159:
        return LocaleController.formatString(r02, new Object[]{Integer.valueOf(r9), Integer.valueOf(r8)});
    L157:
        r02 = R.string.LastSeenContactsMinusPlus;
        goto L159
    L153:
        if (r6.booleanValue() == false) goto L155;
        r02 = R.string.PrivacyContactsAndBotUsersMinusPlus;
    L160:
        if (r9 == 0) goto L170;
        if (r6 != null) goto L163;
    L165:
        if (r11 == false) goto L167;
        int r03 = R.string.LastSeenContactsPremiumMinus;
    L169:
        return LocaleController.formatString(r03, new Object[]{Integer.valueOf(r9)});
    L167:
        r03 = R.string.LastSeenContactsMinus;
        goto L169
    L163:
        if (r6.booleanValue() == false) goto L165;
        r03 = R.string.PrivacyContactsAndBotUsersMinus;
        goto L169
    L170:
        if (r6 != null) goto L172;
    L174:
        if (r11 == false) goto L176;
        int r04 = R.string.LastSeenContactsPremiumPlus;
    L178:
        return LocaleController.formatString(r04, new Object[]{Integer.valueOf(r8)});
    L176:
        r04 = R.string.LastSeenContactsPlus;
        goto L178
    L172:
        if (r6.booleanValue() == false) goto L174;
        r04 = R.string.PrivacyContactsAndBotUsersPlus;
    L179:
        if (r17 != 3) goto L185;
        if (r9 != 0) goto L184;
        return LocaleController.getString(R.string.P2PEverybody);
    L184:
        return LocaleController.formatString(R.string.P2PEverybodyMinus, new Object[]{Integer.valueOf(r9)});
    L185:
        if (r17 != 12) goto L201;
        if (r9 != 0) goto L194;
        if (r6 != null) goto L189;
    L191:
        int r05 = R.string.LastSeenEverybody;
    L193:
        return LocaleController.getString(r05);
    L189:
        if (r6.booleanValue() == true) goto L191;
        r05 = R.string.PrivacyValueEveryoneExceptBots;
        goto L193
    L194:
        if (r6 != null) goto L196;
    L198:
        int r06 = R.string.LastSeenEverybodyMinus;
    L200:
        return LocaleController.formatString(r06, new Object[]{Integer.valueOf(r9)});
    L196:
        if (r6.booleanValue() == true) goto L198;
        r06 = R.string.PrivacyValueEveryoneExceptBotsMinus;
        goto L200
    L201:
        if (r9 != 0) goto L205;
        return LocaleController.getString(R.string.LastSeenEverybody);
    L205:
        return LocaleController.formatString(R.string.LastSeenEverybodyMinus, new Object[]{Integer.valueOf(r9)});
    }

    private void initPassword() {
        TwoStepVerificationActivity.initPasswordNewAlgo(this.currentPassword);
        boolean r1 = true;
        if (getUserConfig().hasSecureData == false) goto L5;
    L8:
        TL_account.Password r0 = this.currentPassword;
        if (r0 == null) goto L26;
        int r3 = this.emailLoginRow;
        String r02 = r0.login_email_pattern;
        if (r02 == null) goto L14;
        if (r3 != (-1)) goto L14;
        boolean r5 = true;
    L15:
        if (r02 != null) goto L18;
        if (r3 == (-1)) goto L18;
    L19:
        if (r5 == true) goto L21;
        if (r1 == false) goto L26;
    L21:
        updateRows(false);
        ListAdapter r03 = this.listAdapter;
        if (r03 == null) goto L26;
        if (r5 == false) goto L25;
        r03.notifyItemInserted(this.emailLoginRow);
        goto L26
    L25:
        r03.notifyItemRemoved(r3);
    L18:
        r1 = false;
    L14:
        r5 = false;
    L26:
        ListAdapter r04 = this.listAdapter;
        if (r04 == null) goto L30;
        r04.notifyItemChanged(this.passwordRow);
        return;
    L30:
        return;
    L5:
        if (this.currentPassword.has_secure_values == false) goto L8;
        getUserConfig().hasSecureData = true;
        getUserConfig().saveConfig(false);
        updateRows();
    }

    private /* synthetic */ void lambda$createView$10() {
        this.progressDialog.dismiss();
    }

    private /* synthetic */ void lambda$createView$11(AlertDialog r3, int r4) {
        AlertDialog r32 = new AlertDialog.Builder(getParentActivity(), 3, null).show();
        this.progressDialog = r32;
        r32.setCanCancel(false);
        if (this.currentSync == this.newSync) goto L5;
        UserConfig r33 = getUserConfig();
        boolean r0 = this.newSync;
        r33.syncContacts = r0;
        this.currentSync = r0;
        getUserConfig().saveConfig(false);
    L5:
        getContactsController().deleteAllContacts(new PrivacySettingsActivity$$ExternalSyntheticLambda18(this));
    }

    private /* synthetic */ void lambda$createView$12(TextCheckCell r2) {
        boolean r0 = !this.newSuggest;
        this.newSuggest = r0;
        r2.setChecked(r0);
    }

    private /* synthetic */ void lambda$createView$13(TextCheckCell r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new PrivacySettingsActivity$$ExternalSyntheticLambda22(this, r1));
    }

    private /* synthetic */ void lambda$createView$14(TextCheckCell r3, AlertDialog r4, int r5) {
        TLRPC.TL_payments_clearSavedInfo r42 = new TLRPC.TL_payments_clearSavedInfo();
        boolean[] r52 = this.clear;
        r42.credentials = r52[1];
        r42.info = r52[0];
        getUserConfig().tmpPassword = null;
        getUserConfig().saveConfig(false);
        getConnectionsManager().sendRequest(r42, new PrivacySettingsActivity$$ExternalSyntheticLambda20(this, r3));
    }

    private /* synthetic */ void lambda$createView$15() {
        this.listAdapter.notifyDataSetChanged();
        this.secretMapUpdate = true;
    }

    private /* synthetic */ void lambda$createView$16(View r4) {
        CheckBoxCell r42 = (CheckBoxCell) r4;
        int r0 = ((Integer) r42.getTag()).intValue();
        boolean[] r3 = this.clear;
        boolean r1 = !r3[r0];
        r3[r0] = r1;
        r42.setChecked(r1, true);
    }

    private /* synthetic */ void lambda$createView$18(AlertDialog r4, int r5) {
        TLRPC.TL_payments_clearSavedInfo r42 = new TLRPC.TL_payments_clearSavedInfo();
        boolean[] r52 = this.clear;
        r42.credentials = r52[1];
        r42.info = r52[0];
        getUserConfig().tmpPassword = null;
        getUserConfig().saveConfig(false);
        getConnectionsManager().sendRequest(r42, new PrivacySettingsActivity$$ExternalSyntheticLambda24());
        boolean[] r43 = this.clear;
        boolean r53 = r43[0];
        if (r53 == true) goto L5;
    L7:
        if (r53 == false) goto L10;
        String r44 = LocaleController.getString("PrivacyPaymentsShippingInfoCleared", R.string.PrivacyPaymentsShippingInfoCleared);
    L12:
        BulletinFactory.of(this).createSimpleBulletin(R.raw.chats_infotip, r44).show();
        return;
    L10:
        if (r43[1] == false) goto L14;
        r44 = LocaleController.getString("PrivacyPaymentsPaymentInfoCleared", R.string.PrivacyPaymentsPaymentInfoCleared);
        goto L12
    L14:
        return;
    L5:
        if (r43[1] == false) goto L7;
        r44 = LocaleController.getString("PrivacyPaymentsPaymentShippingCleared", R.string.PrivacyPaymentsPaymentShippingCleared);
        goto L12
    }

    private /* synthetic */ void lambda$createView$19(AlertDialog r2, int r3) {
        Dialog r22 = this.visibleDialog;     // Catch: Exception -> L6
        if (r22 == null) goto L8;
        r22.dismiss();     // Catch: Exception -> L6
    L8:
        AlertDialog.Builder r23 = new AlertDialog.Builder(getParentActivity());
        r23.setTitle(LocaleController.getString("PrivacyPaymentsClearAlertTitle", R.string.PrivacyPaymentsClearAlertTitle));
        r23.setMessage(LocaleController.getString("PrivacyPaymentsClearAlert", R.string.PrivacyPaymentsClearAlert));
        r23.setPositiveButton(LocaleController.getString("ClearButton", R.string.ClearButton), new PrivacySettingsActivity$$ExternalSyntheticLambda17(this));
        r23.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(r23.create());
        AlertDialog r24 = r23.create();
        showDialog(r24);
        TextView r1 = (TextView) r24.getButton(-1);
        if (r1 == null) goto L14;
        r1.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L14:
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    private /* synthetic */ void lambda$createView$20(Context r21, View r22, int r23) {
        if (r22.isEnabled() == true) goto L6;
        return;
    L6:
        if (r23 != this.autoDeleteMesages) goto L12;
        if (getUserConfig().getGlobalTTl() < 0) goto L231;
        presentFragment(new AutoDeleteMessagesActivity());
        return;
    L231:
        return;
    L12:
        if (r23 != this.blockedRow) goto L16;
        presentFragment(new PrivacyUsersActivity());
        return;
    L16:
        if (r23 != this.sessionsRow) goto L20;
        this.devicesActivityPreload.resetFragment();
        presentFragment(this.devicesActivityPreload);
        return;
    L20:
        if (r23 != this.webSessionsRow) goto L23;
        this.webSessionsActivityPreload.resetFragment();
        presentFragment(this.webSessionsActivityPreload);
        return;
    L23:
        int r6 = 4;
        int r7 = 5;
        boolean r12 = true;
        if (r23 != this.deleteAccountRow) goto L54;
        if (getParentActivity() == null) goto L241;
        int r1 = getContactsController().getDeleteAccountTTL();
        if (r1 > 31) goto L32;
        r6 = 0;
    L44:
        AlertDialog.Builder r13 = new AlertDialog.Builder(getParentActivity());
        r13.setTitle(LocaleController.getString("DeleteAccountTitle", R.string.DeleteAccountTitle));
        String[] r2 = {LocaleController.formatPluralString("Months", 1, new Object[0]), LocaleController.formatPluralString("Months", 3, new Object[0]), LocaleController.formatPluralString("Months", 6, new Object[0]), LocaleController.formatPluralString("Months", 12, new Object[0]), LocaleController.formatPluralString("Months", 18, new Object[0]), LocaleController.formatPluralString("Months", 24, new Object[0])};
        LinearLayout r3 = new LinearLayout(getParentActivity());
        r3.setOrientation(1);
        r13.setView(r3);
        int r4 = 0;
    L45:
        if (r4 >= 6) goto L51;
        RadioColorCell r5 = new RadioColorCell(getParentActivity());
        r5.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r5.setTag(Integer.valueOf(r4));
        r5.setCheckColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
        String r72 = r2[r4];
        if (r6 != r4) goto L49;
        boolean r8 = true;
    L50:
        r5.setTextAndValue(r72, r8);
        r3.addView(r5);
        r5.setOnClickListener(new PrivacySettingsActivity$$ExternalSyntheticLambda9(this, r13));
        r4 = r4 + 1;
        goto L45
    L49:
        r8 = false;
        goto L50
    L51:
        r13.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r13.create());
        return;
    L32:
        if (r1 > 93) goto L35;
        r6 = 1;
        goto L44
    L35:
        if (r1 > 182) goto L38;
        r6 = 2;
        goto L44
    L38:
        if (r1 == 548) goto L44;
        if (r1 != 730) goto L43;
        r6 = 5;
        goto L44
    L43:
        r6 = 3;
        goto L44
    L241:
        return;
    L54:
        if (r23 != this.lastSeenRow) goto L58;
        presentFragment(new PrivacyControlActivity(0));
        return;
    L58:
        if (r23 != this.phoneNumberRow) goto L62;
        presentFragment(new PrivacyControlActivity(6));
        return;
    L62:
        if (r23 != this.groupsRow) goto L66;
        presentFragment(new PrivacyControlActivity(1));
        return;
    L66:
        if (r23 != this.callsRow) goto L70;
        presentFragment(new PrivacyControlActivity(2));
        return;
    L70:
        if (r23 != this.profilePhotoRow) goto L74;
        presentFragment(new PrivacyControlActivity(4));
        return;
    L74:
        if (r23 != this.bioRow) goto L78;
        presentFragment(new PrivacyControlActivity(9));
        return;
    L78:
        if (r23 != this.musicRow) goto L82;
        presentFragment(new PrivacyControlActivity(14));
        return;
    L82:
        if (r23 != this.birthdayRow) goto L86;
        presentFragment(new PrivacyControlActivity(11));
        return;
    L86:
        if (r23 != this.giftsRow) goto L90;
        presentFragment(new PrivacyControlActivity(12));
        return;
    L90:
        if (r23 != this.forwardsRow) goto L94;
        presentFragment(new PrivacyControlActivity(5));
        return;
    L94:
        if (r23 != this.voicesRow) goto L98;
        presentFragment(new PrivacyControlActivity(8));
        return;
    L98:
        if (r23 != this.noncontactsRow) goto L102;
        presentFragment(new PrivacyControlActivity(10));
        return;
    L102:
        if (r23 != this.emailLoginRow) goto L116;
        TL_account.Password r24 = this.currentPassword;
        if (r24 == null) goto L232;
        String r25 = r24.login_email_pattern;
        if (r25 == null) goto L242;
        SpannableStringBuilder r26 = SpannableStringBuilder.valueOf(r25);
        int r32 = this.currentPassword.login_email_pattern.indexOf(42);
        int r42 = this.currentPassword.login_email_pattern.lastIndexOf(42);
        if (r32 == r42) goto L113;
        if (r32 == (-1)) goto L113;
        if (r42 == (-1)) goto L113;
        TextStyleSpan.TextStyleRun r52 = new TextStyleSpan.TextStyleRun();
        r52.flags |= 256;
        r52.start = r32;
        int r43 = r42 + 1;
        r52.end = r43;
        r26.setSpan(new TextStyleSpan(r52), r32, r43, 0);
    L113:
        new AlertDialog.Builder(r21).setTitle(r26).setMessage(LocaleController.getString(R.string.EmailLoginChangeMessage)).setPositiveButton(LocaleController.getString(R.string.ChangeEmail), new PrivacySettingsActivity$$ExternalSyntheticLambda10(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
        return;
    L242:
        return;
    L232:
        return;
    L116:
        if (r23 != this.passwordRow) goto L133;
        TL_account.Password r14 = this.currentPassword;
        if (r14 != null) goto L121;
        return;
    L121:
        if (TwoStepVerificationActivity.canHandleCurrentPassword(r14, false) == true) goto L123;
        AlertsCreator.showUpdateAppAlert(getParentActivity(), LocaleController.getString("UpdateAppAlert", R.string.UpdateAppAlert), true);
    L123:
        TL_account.Password r15 = this.currentPassword;
        if (r15.has_password == false) goto L128;
        TwoStepVerificationActivity r16 = new TwoStepVerificationActivity();
        r16.setPassword(this.currentPassword);
        presentFragment(r16);
        return;
    L128:
        if (TextUtils.isEmpty(r15.email_unconfirmed_pattern) == false) goto L130;
        r7 = 6;
    L130:
        presentFragment(new TwoStepVerificationSetupActivity(r7, this.currentPassword));
        return;
    L133:
        if (r23 != this.passkeysRow) goto L155;
        if (Build.VERSION.SDK_INT >= 28) goto L137;
        return;
    L137:
        if (BuildVars.SUPPORTS_PASSKEYS == false) goto L244;
        TL_account.Password r27 = this.currentPassword;
        if (r27 != null) goto L142;
    L152:
        BulletinFactory.of(this).createSimpleBulletin(R.raw.chats_infotip, AndroidUtilities.replaceTags(LocaleController.getString(R.string.PaymentCardSavePaymentInformationInfoLine2).replace("*", "**")), LocaleController.getString(R.string.Enable), new PrivacySettingsActivity$$ExternalSyntheticLambda11(this)).show();
        return;
    L142:
        if (r27.has_password == false) goto L152;
        ArrayList<TL_account.Passkey> r28 = this.currentPasskeys;
        if (r28 != null) goto L147;
    L150:
        PasskeysActivity.showLearnSheet(r21, this.currentAccount, this.resourceProvider, true);
        return;
    L147:
        if (r28.isEmpty() == true) goto L150;
        presentFragment(new PasskeysActivity(this.currentPasskeys));
        return;
    L244:
        return;
    L155:
        if (r23 != this.passcodeRow) goto L159;
        presentFragment(PasscodeActivity.determineOpenFragment());
        return;
    L159:
        if (r23 != this.secretWebpageRow) goto L173;
        if (getMessagesController().secretWebpagePreview != 1) goto L163;
        getMessagesController().secretWebpagePreview = 0;
    L164:
        MessagesController.getGlobalMainSettings().edit().putInt("secretWebpage2", getMessagesController().secretWebpagePreview).apply();
        if ((r22 instanceof TextCheckCell) == false) goto L234;
        TextCheckCell r17 = (TextCheckCell) r22;
        if (getMessagesController().secretWebpagePreview == 1) goto L170;
        r12 = false;
    L170:
        r17.setChecked(r12);
        return;
    L234:
        return;
    L163:
        getMessagesController().secretWebpagePreview = 1;
        goto L164
    L173:
        if (r23 != this.contactsDeleteRow) goto L182;
        if (getParentActivity() == null) goto L245;
        AlertDialog.Builder r18 = new AlertDialog.Builder(getParentActivity());
        r18.setTitle(LocaleController.getString("SyncContactsDeleteTitle", R.string.SyncContactsDeleteTitle));
        r18.setMessage(AndroidUtilities.replaceTags(LocaleController.getString("SyncContactsDeleteText", R.string.SyncContactsDeleteText)));
        r18.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        r18.setPositiveButton(LocaleController.getString("Delete", R.string.Delete), new PrivacySettingsActivity$$ExternalSyntheticLambda12(this));
        AlertDialog r19 = r18.create();
        showDialog(r19);
        TextView r0 = (TextView) r19.getButton(-1);
        if (r0 == null) goto L235;
        r0.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L235:
        return;
    L245:
        return;
    L182:
        if (r23 != this.contactsSuggestRow) goto L192;
        TextCheckCell r110 = (TextCheckCell) r22;
        if (this.newSuggest == false) goto L189;
        AlertDialog.Builder r29 = new AlertDialog.Builder(getParentActivity());
        r29.setTitle(LocaleController.getString("SuggestContactsTitle", R.string.SuggestContactsTitle));
        r29.setMessage(LocaleController.getString("SuggestContactsAlert", R.string.SuggestContactsAlert));
        r29.setPositiveButton(LocaleController.getString("MuteDisable", R.string.MuteDisable), new PrivacySettingsActivity$$ExternalSyntheticLambda13(this, r110));
        r29.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        AlertDialog r111 = r29.create();
        showDialog(r111);
        TextView r02 = (TextView) r111.getButton(-1);
        if (r02 == null) goto L236;
        r02.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L236:
        return;
    L189:
        this.newSuggest = true;
        r110.setChecked(true);
        return;
    L192:
        if (r23 != this.newChatsRow) goto L196;
        boolean r210 = !this.archiveChats;
        this.archiveChats = r210;
        ((TextCheckCell) r22).setChecked(r210);
        return;
    L196:
        if (r23 != this.contactsSyncRow) goto L202;
        boolean r112 = !this.newSync;
        this.newSync = r112;
        if ((r22 instanceof TextCheckCell) == false) goto L237;
        ((TextCheckCell) r22).setChecked(r112);
        return;
    L237:
        return;
    L202:
        if (r23 != this.secretMapRow) goto L206;
        AlertsCreator.showSecretLocationAlert(getParentActivity(), this.currentAccount, new PrivacySettingsActivity$$ExternalSyntheticLambda14(this), false, null);
        return;
    L206:
        if (r23 != this.paymentsClearRow) goto L218;
        AlertDialog.Builder r113 = new AlertDialog.Builder(getParentActivity());
        r113.setTitle(LocaleController.getString("PrivacyPaymentsClearAlertTitle", R.string.PrivacyPaymentsClearAlertTitle));
        r113.setMessage(LocaleController.getString("PrivacyPaymentsClearAlertText", R.string.PrivacyPaymentsClearAlertText));
        LinearLayout r211 = new LinearLayout(getParentActivity());
        r211.setOrientation(1);
        r113.setView(r211);
        int r33 = 0;
    L208:
        if (r33 >= 2) goto L213;
        if (r33 != 0) goto L211;
        String r62 = LocaleController.getString("PrivacyClearShipping", R.string.PrivacyClearShipping);
    L212:
        this.clear[r33] = true;
        CheckBoxCell r73 = new CheckBoxCell(getParentActivity(), 1, 21, null);
        r73.setTag(Integer.valueOf(r33));
        r73.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r73.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r211.addView(r73, LayoutHelper.createLinear(-1, 50));
        r73.setText(r62, null, true, false);
        r73.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r73.setOnClickListener(new PrivacySettingsActivity$$ExternalSyntheticLambda15(this));
        r33 = r33 + 1;
        goto L208
    L211:
        r62 = LocaleController.getString("PrivacyClearPayment", R.string.PrivacyClearPayment);
        goto L212
    L213:
        r113.setPositiveButton(LocaleController.getString("ClearButton", R.string.ClearButton), new PrivacySettingsActivity$$ExternalSyntheticLambda16(this));
        r113.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(r113.create());
        AlertDialog r114 = r113.create();
        showDialog(r114);
        TextView r03 = (TextView) r114.getButton(-1);
        if (r03 == null) goto L238;
        r03.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L238:
        return;
    L218:
        if (r23 != this.passportRow) goto L222;
        presentFragment(new PassportActivity(5, 0, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, null, null, null, null, null));
        return;
    L222:
        if (r23 != this.botsBiometryRow) goto L239;
        presentFragment(new BotBiometrySettings());
        return;
    }

    private /* synthetic */ void lambda$createView$21(ArrayList r2) {
        this.biometryBots.clear();
        this.biometryBots.addAll(r2);
        updateRows(true);
    }

    private /* synthetic */ void lambda$createView$4(AlertDialog r1, TLObject r2, TL_account.setAccountTTL r3) {
        r1.dismiss();     // Catch: Exception -> L4
    L7:
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L12;
        this.deleteAccountUpdate = true;
        getContactsController().setDeleteAccountTTL(r3.ttl.days);
        this.listAdapter.notifyDataSetChanged();
        return;
    L12:
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$createView$5(AlertDialog r1, TL_account.setAccountTTL r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new PrivacySettingsActivity$$ExternalSyntheticLambda23(this, r1, r3, r2));
    }

    private /* synthetic */ void lambda$createView$6(AlertDialog.Builder r4, View r5) {
        r4.getDismissRunnable().run();
        Integer r42 = (Integer) r5.getTag();
        if (r42.intValue() != 0) goto L6;
        int r43 = 30;
    L21:
        AlertDialog r52 = new AlertDialog(getParentActivity(), 3);
        r52.setCanCancel(false);
        r52.show();
        TL_account.setAccountTTL r0 = new TL_account.setAccountTTL();
        TLRPC.TL_accountDaysTTL r1 = new TLRPC.TL_accountDaysTTL();
        r0.ttl = r1;
        r1.days = r43;
        getConnectionsManager().sendRequest(r0, new PrivacySettingsActivity$$ExternalSyntheticLambda21(this, r52, r0));
        return;
    L6:
        if (r42.intValue() != 1) goto L9;
        r43 = 90;
        goto L21
    L9:
        if (r42.intValue() != 2) goto L12;
        r43 = 182;
        goto L21
    L12:
        if (r42.intValue() != 3) goto L15;
        r43 = 365;
        goto L21
    L15:
        if (r42.intValue() != 4) goto L18;
        r43 = 548;
        goto L21
    L18:
        if (r42.intValue() != 5) goto L20;
        r43 = 730;
        goto L21
    L20:
        r43 = 0;
        goto L21
    }

    private /* synthetic */ void lambda$createView$7() {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(getContext(), null);
        r0.setAnimation(R.raw.email_check_inbox, new String[0]);
        r0.textView.setText(LocaleController.getString(R.string.YourLoginEmailChangedSuccess));
        Bulletin.make(this, r0, 1500).show();
        this.fragmentView.performHapticFeedback(3, 2);     // Catch: Exception -> L6
    L4:
        loadPasswordSettings();
    }

    private /* synthetic */ void lambda$createView$8(AlertDialog r1, int r2) {
        presentFragment(new LoginActivity().changeEmail(new PrivacySettingsActivity$$ExternalSyntheticLambda19(this)));
    }

    private /* synthetic */ void lambda$createView$9() {
        if (TextUtils.isEmpty(this.currentPassword.email_unconfirmed_pattern) == false) goto L5;
        int r1 = 6;
    L6:
        presentFragment(new TwoStepVerificationSetupActivity(r1, this.currentPassword));
        return;
    L5:
        r1 = 5;
        goto L6
    }

    private /* synthetic */ void lambda$loadPasskeys$24(TL_account.Passkeys r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        this.currentPasskeys = r1.passkeys;
        updateRows();
        return;
    }

    private /* synthetic */ void lambda$loadPasswordSettings$22(TL_account.Password r1) {
        this.currentPassword = r1;
        initPassword();
    }

    private /* synthetic */ void lambda$loadPasswordSettings$23(TLObject r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        AndroidUtilities.runOnUIThread(new PrivacySettingsActivity$$ExternalSyntheticLambda8(this, (TL_account.Password) r1));
        return;
    }

    private /* synthetic */ void lambda$onFragmentCreate$0() {
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L8;
        int r1 = this.sessionsRow;
        if (r1 < 0) goto L9;
        r0.notifyItemChanged(r1);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$onFragmentCreate$1() {
        if (this.listAdapter == null) goto L9;
        int r0 = this.webSessionsActivityPreload.getSessionsCount();
        if (this.webSessionsRow >= 0) goto L10;
        if (r0 <= 0) goto L11;
        updateRows();
        return;
    L11:
        return;
    L10:
        return;
    }

    private void loadPasskeys() {
        getConnectionsManager().sendRequestTyped(new TL_account.getPasskeys(), new AiTonesController$$ExternalSyntheticLambda0(), new PrivacySettingsActivity$$ExternalSyntheticLambda7(this));
    }

    private void loadPasswordSettings() {
        TL_account.getPassword r0 = new TL_account.getPassword();
        getConnectionsManager().sendRequest(r0, new PrivacySettingsActivity$$ExternalSyntheticLambda4(this), 10);
    }

    private void updateRows() {
        updateRows(true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r6) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacySettings));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
    L7:
        this.listAdapter = new ListAdapter(this, r6);
        FrameLayout r02 = new FrameLayout(r6);
        this.fragmentView = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        RecyclerListView r2 = new RecyclerListView(r6);
        this.listView = r2;
        r2.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        RecyclerListView r22 = this.listView;
        AnonymousClass2 r3 = new AnonymousClass2(this, r6, 1, false);
        this.layoutManager = r3;
        r22.setLayoutManager(r3);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setLayoutAnimation(null);
        this.listView.setItemAnimator(null);
        r02.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new PrivacySettingsActivity$$ExternalSyntheticLambda2(this, r6));
        BotBiometry.getBots(getContext(), this.currentAccount, new PrivacySettingsActivity$$ExternalSyntheticLambda3(this));
        return this.fragmentView;
    L5:
        if (r0.isRightLayout() == false) goto L7;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_close);
        goto L7
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        boolean r0 = false;
        if (r2 != NotificationCenter.privacyRulesUpdated) goto L14;
        TLRPC.GlobalPrivacySettings r32 = getContactsController().getGlobalPrivacySettings();
        if (r32 == null) goto L10;
        this.archiveChats = r32.archive_and_mute_new_noncontact_peers;
        this.noncontactsValue = r32.new_noncontact_peers_require_premium;
        if ((r32.flags & 32) == 0) goto L9;
        r0 = true;
    L9:
        this.feeValue = r0;
    L10:
        ListAdapter r33 = this.listAdapter;
        if (r33 == null) goto L25;
        r33.notifyDataSetChanged();
    L25:
        if (r2 != NotificationCenter.didUpdateGlobalAutoDeleteTimer) goto L30;
        ListAdapter r22 = this.listAdapter;
        if (r22 == null) goto L31;
        r22.notifyItemChanged(this.autoDeleteMesages);
        return;
    L31:
        return;
    L30:
        return;
    L14:
        if (r2 != NotificationCenter.blockedUsersDidLoad) goto L17;
        this.listAdapter.notifyItemChanged(this.blockedRow);
        goto L25
    L17:
        if (r2 != NotificationCenter.didSetOrRemoveTwoStepPassword) goto L25;
        if (r4.length <= 0) goto L23;
        this.currentPassword = (TL_account.Password) r4[0];
        ListAdapter r34 = this.listAdapter;
        if (r34 == null) goto L25;
        r34.notifyItemChanged(this.passwordRow);
        goto L25
    L23:
        this.currentPassword = null;
        loadPasswordSettings();
        updateRows();
        goto L25
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextSettingsCell.class, HeaderCell.class, TextCheckCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        int r22 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r22));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, r22));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrack));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrackChecked));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        getContactsController().loadPrivacySettings();
        getMessagesController().getBlockedPeers(true);
        boolean r0 = getUserConfig().syncContacts;
        this.newSync = r0;
        this.currentSync = r0;
        boolean r02 = getUserConfig().suggestContacts;
        this.newSuggest = r02;
        this.currentSuggest = r02;
        TLRPC.GlobalPrivacySettings r03 = getContactsController().getGlobalPrivacySettings();
        if (r03 == null) goto L9;
        this.archiveChats = r03.archive_and_mute_new_noncontact_peers;
        this.noncontactsValue = r03.new_noncontact_peers_require_premium;
        if ((r03.flags & 32) == 0) goto L7;
        boolean r04 = true;
    L8:
        this.feeValue = r04;
        goto L9
    L7:
        r04 = false;
    L9:
        updateRows();
        loadPasswordSettings();
        loadPasskeys();
        getNotificationCenter().addObserver(this, NotificationCenter.privacyRulesUpdated);
        getNotificationCenter().addObserver(this, NotificationCenter.blockedUsersDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.didSetOrRemoveTwoStepPassword);
        getNotificationCenter().addObserver(this, NotificationCenter.didUpdateGlobalAutoDeleteTimer);
        getUserConfig().loadGlobalTTl();
        SessionsActivity r05 = new SessionsActivity(0);
        this.devicesActivityPreload = r05;
        r05.setDelegate(new PrivacySettingsActivity$$ExternalSyntheticLambda5(this));
        this.devicesActivityPreload.loadSessions(false);
        SessionsActivity r06 = new SessionsActivity(1);
        this.webSessionsActivityPreload = r06;
        r06.setDelegate(new PrivacySettingsActivity$$ExternalSyntheticLambda6(this));
        this.webSessionsActivityPreload.loadSessions(false);
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.privacyRulesUpdated);
        getNotificationCenter().removeObserver(this, NotificationCenter.blockedUsersDidLoad);
        getNotificationCenter().removeObserver(this, NotificationCenter.didSetOrRemoveTwoStepPassword);
        getNotificationCenter().removeObserver(this, NotificationCenter.didUpdateGlobalAutoDeleteTimer);
        boolean r2 = true;
        if (this.currentSync == this.newSync) goto L12;
        UserConfig r0 = getUserConfig();
        boolean r1 = this.newSync;
        r0.syncContacts = r1;
        if (r1 == true) goto L7;
    L11:
        boolean r02 = true;
    L13:
        boolean r12 = this.newSuggest;
        if (r12 == this.currentSuggest) goto L18;
        if (r12 == true) goto L17;
        getMediaDataController().clearTopPeers();
    L17:
        getUserConfig().suggestContacts = this.newSuggest;
        TLRPC.TL_contacts_toggleTopPeers r03 = new TLRPC.TL_contacts_toggleTopPeers();
        r03.enabled = this.newSuggest;
        getConnectionsManager().sendRequest(r03, new PrivacySettingsActivity$$ExternalSyntheticLambda0());
        r02 = true;
    L18:
        TLRPC.GlobalPrivacySettings r13 = getContactsController().getGlobalPrivacySettings();
        if (r13 == null) goto L26;
        boolean r4 = r13.archive_and_mute_new_noncontact_peers;
        boolean r5 = this.archiveChats;
        if (r4 == r5) goto L26;
        r13.archive_and_mute_new_noncontact_peers = r5;
        TL_account.setGlobalPrivacySettings r04 = new TL_account.setGlobalPrivacySettings();
        TLRPC.GlobalPrivacySettings r14 = getContactsController().getGlobalPrivacySettings();
        r04.settings = r14;
        if (r14 != null) goto L25;
        r04.settings = new TLRPC.TL_globalPrivacySettings();
    L25:
        r04.settings.archive_and_mute_new_noncontact_peers = this.archiveChats;
        getConnectionsManager().sendRequest(r04, new PrivacySettingsActivity$$ExternalSyntheticLambda1());
    L27:
        if (r2 == false) goto L30;
        getUserConfig().saveConfig(false);
        return;
    L30:
        return;
    L26:
        r2 = r02;
        goto L27
    L7:
        if (ContactsController.hasContactsPermission() == false) goto L11;
        getContactsController().forceImportContacts();
        if (getParentActivity() == null) goto L11;
        Toast.makeText(getParentActivity(), LocaleController.getString("SyncContactsAdded", R.string.SyncContactsAdded), 0).show();
        goto L11
    L12:
        r02 = false;
        goto L13
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }

    public PrivacySettingsActivity setCurrentPassword(TL_account.Password r1) {
        this.currentPassword = r1;
        if (r1 == null) goto L5;
        initPassword();
    L5:
        return this;
    }

    public void updateRows(boolean r7) {
        this.passkeysRow = -1;
        boolean r1 = false;
        this.securitySectionRow = 0;
        int r3 = 1 + 1;
        this.passwordRow = 1;
        this.autoDeleteMesages = r3;
        this.rowCount = r3 + 2;
        this.passcodeRow = r3 + 1;
        if (getMessagesController().config.settingsDisplayPasskeys.get() == true) goto L5;
    L9:
        TL_account.Password r32 = this.currentPassword;
        if (r32 == null) goto L15;
        if (r32.login_email_pattern != null) goto L16;
    L17:
        this.emailLoginRow = -1;
    L18:
        int r4 = this.rowCount;
        this.rowCount = r4 + 1;
        this.blockedRow = r4;
        if (r32 != null) goto L21;
    L26:
        int r12 = this.rowCount;
        this.sessionsRow = r12;
        this.sessionsDetailRow = r12 + 1;
        this.privacySectionRow = r12 + 2;
        this.phoneNumberRow = r12 + 3;
        this.lastSeenRow = r12 + 4;
        this.profilePhotoRow = r12 + 5;
        this.forwardsRow = r12 + 6;
        this.rowCount = r12 + 8;
        this.callsRow = r12 + 7;
        this.groupsDetailRow = -1;
        if (getMessagesController().premiumFeaturesBlocked() == true) goto L29;
    L32:
        int r13 = this.rowCount;
        this.voicesRow = r13;
        this.rowCount = r13 + 2;
        this.noncontactsRow = r13 + 1;
    L33:
        int r14 = this.rowCount;
        this.birthdayRow = r14;
        this.giftsRow = r14 + 1;
        this.bioRow = r14 + 2;
        this.musicRow = r14 + 3;
        this.groupsRow = r14 + 4;
        this.rowCount = r14 + 6;
        this.privacyShadowRow = r14 + 5;
        if (getMessagesController().autoarchiveAvailable == false) goto L36;
    L39:
        int r15 = this.rowCount;
        this.newChatsHeaderRow = r15;
        this.newChatsRow = r15 + 1;
        this.rowCount = r15 + 3;
        this.newChatsSectionRow = r15 + 2;
    L40:
        int r16 = this.rowCount;
        this.advancedSectionRow = r16;
        this.deleteAccountRow = r16 + 1;
        this.deleteAccountDetailRow = r16 + 2;
        this.rowCount = r16 + 4;
        this.botsSectionRow = r16 + 3;
        if (getUserConfig().hasSecureData == false) goto L43;
        int r17 = this.rowCount;
        this.rowCount = r17 + 1;
        this.passportRow = r17;
    L44:
        int r18 = this.rowCount;
        this.rowCount = r18 + 1;
        this.paymentsClearRow = r18;
        if (this.biometryBots.isEmpty() == true) goto L47;
        int r19 = this.rowCount;
        this.rowCount = r19 + 1;
        this.botsBiometryRow = r19;
    L48:
        SessionsActivity r110 = this.webSessionsActivityPreload;
        if (r110 != null) goto L51;
    L53:
        this.webSessionsRow = -1;
        this.botsDetailRow = -1;
        int r0 = this.rowCount;
        this.rowCount = r0 + 1;
        this.botsAndWebsitesShadowRow = r0;
    L54:
        int r02 = this.rowCount;
        this.contactsSectionRow = r02;
        this.contactsDeleteRow = r02 + 1;
        this.contactsSyncRow = r02 + 2;
        this.contactsSuggestRow = r02 + 3;
        this.contactsDetailRow = r02 + 4;
        this.secretSectionRow = r02 + 5;
        this.secretMapRow = r02 + 6;
        this.secretWebpageRow = r02 + 7;
        this.rowCount = r02 + 9;
        this.secretDetailRow = r02 + 8;
        ListAdapter r6 = this.listAdapter;
        if (r6 == null) goto L59;
        if (r7 == false) goto L60;
        r6.notifyDataSetChanged();
        return;
    L60:
        return;
    L59:
        return;
    L51:
        if (r110.getSessionsCount() <= 0) goto L53;
        int r111 = this.rowCount;
        this.webSessionsRow = r111;
        this.rowCount = r111 + 2;
        this.botsDetailRow = r111 + 1;
        this.botsAndWebsitesShadowRow = -1;
        goto L54
    L47:
        this.botsBiometryRow = -1;
        goto L48
    L43:
        this.passportRow = -1;
        goto L44
    L36:
        if (getUserConfig().isPremium() == true) goto L39;
        this.newChatsHeaderRow = -1;
        this.newChatsRow = -1;
        this.newChatsSectionRow = -1;
        goto L40
    L29:
        if (getUserConfig().isPremium() == true) goto L32;
        this.voicesRow = -1;
        this.noncontactsRow = -1;
        goto L33
    L21:
        if (r32.login_email_pattern == null) goto L24;
        r1 = true;
    L24:
        if (SharedConfig.hasEmailLogin == r1) goto L26;
        SharedConfig.hasEmailLogin = r1;
        SharedConfig.saveConfig();
    L16:
        int r42 = this.rowCount;
        this.rowCount = r42 + 1;
        this.emailLoginRow = r42;
        goto L18
    L15:
        if (SharedConfig.hasEmailLogin == false) goto L17;
    L5:
        if (Build.VERSION.SDK_INT < 28) goto L9;
        if (BuildVars.SUPPORTS_PASSKEYS == false) goto L9;
        int r33 = this.rowCount;
        this.rowCount = r33 + 1;
        this.passkeysRow = r33;
        goto L9
    }
}
