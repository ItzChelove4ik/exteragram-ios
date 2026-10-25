package org.telegram.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SaveToGallerySettingsHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.StatsController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.LanguageCell;
import org.telegram.ui.Cells.NotificationsCheckCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.voip.VoIPHelper;

/* loaded from: classes6.dex */
public class DataSettingsActivity extends BaseFragment {
    private int autoplayGifsRow;
    private int autoplayHeaderRow;
    private int autoplaySectionRow;
    private int autoplayVideoRow;
    private int callsSection2Row;
    private int callsSectionRow;

    @Keep
    private int clearDraftsRow;
    private int clearDraftsSectionRow;
    private int dataUsageRow;
    private int enableAllStreamInfoRow;
    private int enableAllStreamRow;
    private int enableCacheStreamRow;
    private int enableMkvRow;
    private int enableStreamRow;
    private LinearLayoutManager layoutManager;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int mediaDownloadSection2Row;
    private int mediaDownloadSectionRow;
    private int mobileRow;

    @Keep
    private int proxyRow;
    private int proxySection2Row;
    private int proxySectionRow;
    private int quickRepliesRow;

    @Keep
    private int resetDownloadRow;
    private int roamingRow;
    private int rowCount;

    @Keep
    private int saveToGalleryChannelsRow;
    private int saveToGalleryDividerRow;

    @Keep
    private int saveToGalleryGroupsRow;

    @Keep
    private int saveToGalleryPeerRow;
    private int saveToGallerySectionRow;
    private ArrayList<File> storageDirs;
    private int storageNumRow;
    private boolean storageUsageLoading;
    private int storageUsageRow;
    private long storageUsageSize;
    private int streamSectionRow;
    private boolean updateStorageUsageAnimated;
    private boolean updateVoipUseLessData;
    private int usageSection2Row;
    private int usageSectionRow;

    @Keep
    private int useLessDataForCallsRow;
    private int wifiRow;

    /* renamed from: org.telegram.ui.DataSettingsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ DataSettingsActivity this$0;

        public AnonymousClass1(DataSettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.DataSettingsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ DataSettingsActivity this$0;

        public AnonymousClass2(DataSettingsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public Integer getSelectorColor(int r2) {
            if (r2 != DataSettingsActivity.m14220$$Nest$fgetresetDownloadRow(this.this$0)) goto L7;
            return Integer.valueOf(Theme.multAlpha(getThemedColor(Theme.key_text_RedRegular), 0.1f));
        L7:
            return Integer.valueOf(getThemedColor(Theme.key_listSelector));
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ DataSettingsActivity this$0;

        public ListAdapter(DataSettingsActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return DataSettingsActivity.m14222$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != DataSettingsActivity.m14213$$Nest$fgetmediaDownloadSection2Row(this.this$0)) goto L5;
            return 0;
        L5:
            if (r2 != DataSettingsActivity.m14236$$Nest$fgetusageSection2Row(this.this$0)) goto L7;
            return 0;
        L7:
            if (r2 != DataSettingsActivity.m14203$$Nest$fgetcallsSection2Row(this.this$0)) goto L9;
            return 0;
        L9:
            if (r2 != DataSettingsActivity.m14217$$Nest$fgetproxySection2Row(this.this$0)) goto L11;
            return 0;
        L11:
            if (r2 != DataSettingsActivity.m14201$$Nest$fgetautoplaySectionRow(this.this$0)) goto L13;
            return 0;
        L13:
            if (r2 != DataSettingsActivity.m14206$$Nest$fgetclearDraftsSectionRow(this.this$0)) goto L15;
            return 0;
        L15:
            if (r2 != DataSettingsActivity.m14224$$Nest$fgetsaveToGalleryDividerRow(this.this$0)) goto L18;
            return 0;
        L18:
            if (r2 != DataSettingsActivity.m14214$$Nest$fgetmediaDownloadSectionRow(this.this$0)) goto L20;
            return 2;
        L20:
            if (r2 != DataSettingsActivity.m14233$$Nest$fgetstreamSectionRow(this.this$0)) goto L22;
            return 2;
        L22:
            if (r2 != DataSettingsActivity.m14204$$Nest$fgetcallsSectionRow(this.this$0)) goto L24;
            return 2;
        L24:
            if (r2 != DataSettingsActivity.m14237$$Nest$fgetusageSectionRow(this.this$0)) goto L26;
            return 2;
        L26:
            if (r2 != DataSettingsActivity.m14218$$Nest$fgetproxySectionRow(this.this$0)) goto L28;
            return 2;
        L28:
            if (r2 != DataSettingsActivity.m14200$$Nest$fgetautoplayHeaderRow(this.this$0)) goto L30;
            return 2;
        L30:
            if (r2 != DataSettingsActivity.m14227$$Nest$fgetsaveToGallerySectionRow(this.this$0)) goto L33;
            return 2;
        L33:
            if (r2 != DataSettingsActivity.m14210$$Nest$fgetenableCacheStreamRow(this.this$0)) goto L35;
            return 3;
        L35:
            if (r2 != DataSettingsActivity.m14212$$Nest$fgetenableStreamRow(this.this$0)) goto L37;
            return 3;
        L37:
            if (r2 != DataSettingsActivity.m14209$$Nest$fgetenableAllStreamRow(this.this$0)) goto L39;
            return 3;
        L39:
            if (r2 != DataSettingsActivity.m14211$$Nest$fgetenableMkvRow(this.this$0)) goto L41;
            return 3;
        L41:
            if (r2 != DataSettingsActivity.m14199$$Nest$fgetautoplayGifsRow(this.this$0)) goto L43;
            return 3;
        L43:
            if (r2 != DataSettingsActivity.m14202$$Nest$fgetautoplayVideoRow(this.this$0)) goto L46;
            return 3;
        L46:
            if (r2 != DataSettingsActivity.m14208$$Nest$fgetenableAllStreamInfoRow(this.this$0)) goto L50;
            return 4;
        L50:
            if (r2 != DataSettingsActivity.m14215$$Nest$fgetmobileRow(this.this$0)) goto L52;
            return 5;
        L52:
            if (r2 != DataSettingsActivity.m14239$$Nest$fgetwifiRow(this.this$0)) goto L54;
            return 5;
        L54:
            if (r2 != DataSettingsActivity.m14221$$Nest$fgetroamingRow(this.this$0)) goto L56;
            return 5;
        L56:
            if (r2 != DataSettingsActivity.m14225$$Nest$fgetsaveToGalleryGroupsRow(this.this$0)) goto L58;
            return 5;
        L58:
            if (r2 != DataSettingsActivity.m14226$$Nest$fgetsaveToGalleryPeerRow(this.this$0)) goto L60;
            return 5;
        L60:
            if (r2 != DataSettingsActivity.m14223$$Nest$fgetsaveToGalleryChannelsRow(this.this$0)) goto L63;
            return 5;
        L63:
            if (r2 != DataSettingsActivity.m14231$$Nest$fgetstorageUsageRow(this.this$0)) goto L65;
            return 6;
        L65:
            if (r2 != DataSettingsActivity.m14207$$Nest$fgetdataUsageRow(this.this$0)) goto L67;
            return 6;
        L67:
            if (r2 == DataSettingsActivity.m14229$$Nest$fgetstorageNumRow(this.this$0)) goto L82;
            return 1;
        L82:
            return 6;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return isRowEnabled(r1.getAdapterPosition());
        }

        public boolean isRowEnabled(int r2) {
            if (r2 != DataSettingsActivity.m14215$$Nest$fgetmobileRow(this.this$0)) goto L5;
            return true;
        L5:
            if (r2 != DataSettingsActivity.m14221$$Nest$fgetroamingRow(this.this$0)) goto L7;
            return true;
        L7:
            if (r2 != DataSettingsActivity.m14239$$Nest$fgetwifiRow(this.this$0)) goto L9;
            return true;
        L9:
            if (r2 != DataSettingsActivity.m14231$$Nest$fgetstorageUsageRow(this.this$0)) goto L11;
            return true;
        L11:
            if (r2 != DataSettingsActivity.m14238$$Nest$fgetuseLessDataForCallsRow(this.this$0)) goto L13;
            return true;
        L13:
            if (r2 != DataSettingsActivity.m14207$$Nest$fgetdataUsageRow(this.this$0)) goto L15;
            return true;
        L15:
            if (r2 != DataSettingsActivity.m14216$$Nest$fgetproxyRow(this.this$0)) goto L17;
            return true;
        L17:
            if (r2 != DataSettingsActivity.m14205$$Nest$fgetclearDraftsRow(this.this$0)) goto L19;
            return true;
        L19:
            if (r2 != DataSettingsActivity.m14210$$Nest$fgetenableCacheStreamRow(this.this$0)) goto L21;
            return true;
        L21:
            if (r2 != DataSettingsActivity.m14212$$Nest$fgetenableStreamRow(this.this$0)) goto L23;
            return true;
        L23:
            if (r2 != DataSettingsActivity.m14209$$Nest$fgetenableAllStreamRow(this.this$0)) goto L25;
            return true;
        L25:
            if (r2 != DataSettingsActivity.m14211$$Nest$fgetenableMkvRow(this.this$0)) goto L27;
            return true;
        L27:
            if (r2 != DataSettingsActivity.m14219$$Nest$fgetquickRepliesRow(this.this$0)) goto L29;
            return true;
        L29:
            if (r2 != DataSettingsActivity.m14202$$Nest$fgetautoplayVideoRow(this.this$0)) goto L31;
            return true;
        L31:
            if (r2 != DataSettingsActivity.m14199$$Nest$fgetautoplayGifsRow(this.this$0)) goto L33;
            return true;
        L33:
            if (r2 != DataSettingsActivity.m14229$$Nest$fgetstorageNumRow(this.this$0)) goto L35;
            return true;
        L35:
            if (r2 != DataSettingsActivity.m14225$$Nest$fgetsaveToGalleryGroupsRow(this.this$0)) goto L37;
            return true;
        L37:
            if (r2 != DataSettingsActivity.m14226$$Nest$fgetsaveToGalleryPeerRow(this.this$0)) goto L39;
            return true;
        L39:
            if (r2 != DataSettingsActivity.m14223$$Nest$fgetsaveToGalleryChannelsRow(this.this$0)) goto L41;
            return true;
        L41:
            if (r2 == DataSettingsActivity.m14220$$Nest$fgetresetDownloadRow(this.this$0)) goto L65;
            return false;
        L65:
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r18, int r19) {
            String r5 = null;
            DownloadController.Preset r52 = null;
            r52 = null;
            r52 = null;
            boolean r7 = true;
            int r8 = 0;
            switch(r18.getItemViewType()) {
                case 1: goto L171;
                case 2: goto L143;
                case 3: goto L116;
                case 4: goto L112;
                case 5: goto L45;
                case 6: goto L5;
                default: goto L213;
            };
        L5:
            TextCell r9 = (TextCell) r18.itemView;
            int r1 = DataSettingsActivity.m14231$$Nest$fgetstorageUsageRow(this.this$0);
            DataSettingsActivity r3 = this.this$0;
            if (r19 == r1) goto L8;
            int r12 = DataSettingsActivity.m14207$$Nest$fgetdataUsageRow(r3);
            DataSettingsActivity r32 = this.this$0;
            if (r19 != r12) goto L27;
            StatsController r13 = StatsController.getInstance(DataSettingsActivity.access$000(r32));
            long r10 = ((((r13.getReceivedBytesCount(0, 6) + r13.getReceivedBytesCount(1, 6)) + r13.getReceivedBytesCount(2, 6)) + r13.getSentBytesCount(0, 6)) + r13.getSentBytesCount(1, 6)) + r13.getSentBytesCount(2, 6);
            String r14 = LocaleController.getString(R.string.NetworkUsage);
            String r11 = AndroidUtilities.formatFileSize(r10);
            int r132 = R.drawable.msg_filled_datausage;
            if (DataSettingsActivity.m14229$$Nest$fgetstorageNumRow(this.this$0) == (-1)) goto L23;
            boolean r16 = true;
        L24:
            r9.setTextAndValueAndColorfulIcon(r14, r11, true, r132, -11154873, -14175180, r16);
            return;
        L23:
            r16 = false;
            goto L24
        L27:
            if (r19 != DataSettingsActivity.m14229$$Nest$fgetstorageNumRow(r32)) goto L208;
            String r15 = ((File) DataSettingsActivity.m14228$$Nest$fgetstorageDirs(this.this$0).get(0)).getAbsolutePath();
            if (TextUtils.isEmpty(SharedConfig.storageCacheDir) == true) goto L36;
            int r2 = DataSettingsActivity.m14228$$Nest$fgetstorageDirs(this.this$0).size();
        L31:
            if (r8 >= r2) goto L36;
            String r33 = ((File) DataSettingsActivity.m14228$$Nest$fgetstorageDirs(this.this$0).get(r8)).getAbsolutePath();
            if (r33.startsWith(SharedConfig.storageCacheDir) == true) goto L34;
            r8 = r8 + 1;
            goto L31
        L34:
            r15 = r33;
        L36:
            if (r15 != null) goto L38;
        L42:
            int r0 = R.string.InternalStorage;
        L43:
            r9.setTextAndValueAndColorfulIcon(LocaleController.getString(R.string.StoragePath), LocaleController.getString(r0), true, R.drawable.msg_filled_sdcard, -1007845, -1996271, false);
            return;
        L38:
            if (r15.contains("/storage/emulated/") == true) goto L42;
            r0 = R.string.SdCard;
            goto L43
        L208:
            return;
        L8:
            if (DataSettingsActivity.m14230$$Nest$fgetstorageUsageLoading(r3) == false) goto L10;
            r9.setTextAndValueAndColorfulIcon(LocaleController.getString(R.string.StorageUsage), _UrlKt.FRAGMENT_ENCODE_SET, false, R.drawable.msg_filled_storageusage, -11565578, -13276952, true);
            r9.setDrawLoading(true, 45, DataSettingsActivity.m14234$$Nest$fgetupdateStorageUsageAnimated(this.this$0));
        L16:
            DataSettingsActivity.m14240$$Nest$fputupdateStorageUsageAnimated(this.this$0, false);
            return;
        L10:
            String r102 = LocaleController.getString(R.string.StorageUsage);
            if (DataSettingsActivity.m14232$$Nest$fgetstorageUsageSize(this.this$0) > 0) goto L14;
            String r17 = _UrlKt.FRAGMENT_ENCODE_SET;
        L15:
            r9.setTextAndValueAndColorfulIcon(r102, r17, true, R.drawable.msg_filled_storageusage, -11565578, -13276952, true);
            r9.setDrawLoading(false, 45, DataSettingsActivity.m14234$$Nest$fgetupdateStorageUsageAnimated(this.this$0));
            goto L16
        L14:
            r17 = AndroidUtilities.formatFileSize(DataSettingsActivity.m14232$$Nest$fgetstorageUsageSize(this.this$0));
            goto L15
        L45:
            NotificationsCheckCell r92 = (NotificationsCheckCell) r18.itemView;
            if (r19 != DataSettingsActivity.m14226$$Nest$fgetsaveToGalleryPeerRow(this.this$0)) goto L50;
            CharSequence r110 = LocaleController.getString(R.string.SaveToGalleryPrivate);
            CharSequence r02 = SaveToGallerySettingsHelper.user.createDescription(DataSettingsActivity.access$100(this.this$0));
            boolean r22 = SaveToGallerySettingsHelper.user.enabled();
            CharSequence r03 = r02;
        L48:
            CharSequence r103 = r110;
            boolean r152 = true;
            CharSequence r04 = r03;
        L67:
            if (r52 == null) goto L109;
            StringBuilder r05 = new StringBuilder();
            int r111 = 0;
            boolean r34 = false;
            int r4 = 0;
            boolean r6 = false;
            boolean r112 = false;
        L69:
            int[] r122 = r52.mask;
            boolean r35 = r34;
            if (r111 >= r122.length) goto L85;
            if (r34 == true) goto L75;
            r35 = r34;
            if ((r122[r111] & 1) == 0) goto L75;
            r4 = r4 + 1;
            r35 = true;
        L75:
            if (r6 == false) goto L77;
        L79:
            if (r112 == true) goto L83;
            if ((r122[r111] & 8) == 0) goto L83;
            r4 = r4 + 1;
            r112 = true;
        L83:
            r111 = r111 + 1;
            r34 = r35;
            r6 = r6;
            r112 = r112;
            goto L69
        L77:
            if ((r122[r111] & 4) == 0) goto L79;
            r4 = r4 + 1;
            r6 = true;
            goto L79
        L85:
            if (r52.enabled == false) goto L101;
            if (r4 == 0) goto L101;
            if (r34 == false) goto L90;
            r05.append(LocaleController.getString(R.string.AutoDownloadPhotosOn));
        L90:
            if (r6 == true) goto L92;
            int r182 = 8;
            boolean r172 = r152;
        L96:
            if (r112 == true) goto L98;
        L102:
            if (r34 == true) goto L105;
            if (r6 == true) goto L105;
            if (r112 == true) goto L105;
        L107:
            boolean r123 = r8;
            CharSequence r06 = r05;
        L108:
            CharSequence r113 = r06;
            r92.setAnimationsEnabled(true);
            r92.setTextAndValueAndCheck(r103, r113, r123, 0, true, r172);
            return;
        L105:
            if (r22 == false) goto L107;
            r8 = 1;
            goto L107
        L98:
            if (r05.length() <= 0) goto L100;
            r05.append(", ");
        L100:
            r05.append(LocaleController.getString(R.string.AutoDownloadFilesOn));
            r05.append(String.format(" (%1$s)", new Object[]{AndroidUtilities.formatFileSize(r52.sizes[DownloadController.typeToIndex(r182)], true, false)}));
            goto L102
        L92:
            if (r05.length() <= 0) goto L94;
            r05.append(", ");
        L94:
            r05.append(LocaleController.getString(R.string.AutoDownloadVideosOn));
            r182 = 8;
            r172 = r152;
            r05.append(String.format(" (%1$s)", new Object[]{AndroidUtilities.formatFileSize(r52.sizes[DownloadController.typeToIndex(4)], true, false)}));
        L101:
            r172 = r152;
            r05.append(LocaleController.getString(R.string.NoMediaAutoDownload));
            goto L102
        L109:
            r172 = r152;
            r123 = r22;
            r06 = r04;
            goto L108
        L50:
            if (r19 != DataSettingsActivity.m14225$$Nest$fgetsaveToGalleryGroupsRow(this.this$0)) goto L53;
            r110 = LocaleController.getString(R.string.SaveToGalleryGroups);
            CharSequence r07 = SaveToGallerySettingsHelper.groups.createDescription(DataSettingsActivity.access$200(this.this$0));
            r22 = SaveToGallerySettingsHelper.groups.enabled();
            r03 = r07;
            goto L48
        L53:
            if (r19 != DataSettingsActivity.m14223$$Nest$fgetsaveToGalleryChannelsRow(this.this$0)) goto L56;
            CharSequence r114 = LocaleController.getString(R.string.SaveToGalleryChannels);
            CharSequence r08 = SaveToGallerySettingsHelper.channels.createDescription(DataSettingsActivity.access$300(this.this$0));
            r22 = SaveToGallerySettingsHelper.channels.enabled();
            r103 = r114;
            r152 = false;
            r04 = r08;
            goto L67
        L56:
            if (r19 != DataSettingsActivity.m14215$$Nest$fgetmobileRow(this.this$0)) goto L60;
            r110 = LocaleController.getString(R.string.WhenUsingMobileData);
            r22 = DownloadController.getInstance(DataSettingsActivity.access$400(this.this$0)).mobilePreset.enabled;
            DownloadController.Preset r09 = DownloadController.getInstance(DataSettingsActivity.access$500(this.this$0)).getCurrentMobilePreset();
        L58:
            r52 = r09;
            r03 = null;
            goto L48
        L60:
            if (r19 != DataSettingsActivity.m14239$$Nest$fgetwifiRow(this.this$0)) goto L62;
            r110 = LocaleController.getString(R.string.WhenConnectedOnWiFi);
            r22 = DownloadController.getInstance(DataSettingsActivity.access$600(this.this$0)).wifiPreset.enabled;
            r09 = DownloadController.getInstance(DataSettingsActivity.access$700(this.this$0)).getCurrentWiFiPreset();
            goto L58
        L62:
            CharSequence r115 = LocaleController.getString(R.string.WhenRoaming);
            r22 = DownloadController.getInstance(DataSettingsActivity.access$800(this.this$0)).roamingPreset.enabled;
            DownloadController.Preset r36 = DownloadController.getInstance(DataSettingsActivity.access$900(this.this$0)).getCurrentRoamingPreset();
            if (DataSettingsActivity.m14220$$Nest$fgetresetDownloadRow(this.this$0) < 0) goto L65;
            boolean r010 = true;
        L66:
            r152 = r010;
            r103 = r115;
            r04 = null;
            r52 = r36;
            goto L67
        L65:
            r010 = false;
            goto L66
        L112:
            TextInfoPrivacyCell r116 = (TextInfoPrivacyCell) r18.itemView;
            if (r19 != DataSettingsActivity.m14208$$Nest$fgetenableAllStreamInfoRow(this.this$0)) goto L209;
            r116.setText(LocaleController.getString(R.string.EnableAllStreamingInfo));
            return;
        L209:
            return;
        L116:
            TextCheckCell r117 = (TextCheckCell) r18.itemView;
            if (r19 != DataSettingsActivity.m14212$$Nest$fgetenableStreamRow(this.this$0)) goto L125;
            String r23 = LocaleController.getString(R.string.EnableStreaming);
            boolean r37 = SharedConfig.streamMedia;
            if (DataSettingsActivity.m14209$$Nest$fgetenableAllStreamRow(this.this$0) != (-1)) goto L122;
            r7 = false;
        L122:
            r117.setTextAndCheck(r23, r37, r7);
            return;
        L125:
            if (r19 != DataSettingsActivity.m14210$$Nest$fgetenableCacheStreamRow(this.this$0)) goto L128;
            return;
        L128:
            if (r19 != DataSettingsActivity.m14211$$Nest$fgetenableMkvRow(this.this$0)) goto L132;
            r117.setTextAndCheck("(beta only) Show MKV as Video", SharedConfig.streamMkv, true);
            return;
        L132:
            if (r19 != DataSettingsActivity.m14209$$Nest$fgetenableAllStreamRow(this.this$0)) goto L136;
            r117.setTextAndCheck("(beta only) Stream All Videos", SharedConfig.streamAllVideo, false);
            return;
        L136:
            if (r19 != DataSettingsActivity.m14199$$Nest$fgetautoplayGifsRow(this.this$0)) goto L140;
            r117.setTextAndCheck(LocaleController.getString(R.string.AutoplayGIF), SharedConfig.isAutoplayGifs(), true);
            return;
        L140:
            if (r19 != DataSettingsActivity.m14202$$Nest$fgetautoplayVideoRow(this.this$0)) goto L210;
            r117.setTextAndCheck(LocaleController.getString(R.string.AutoplayVideo), SharedConfig.isAutoplayVideo(), false);
            return;
        L210:
            return;
        L143:
            HeaderCell r118 = (HeaderCell) r18.itemView;
            if (r19 != DataSettingsActivity.m14214$$Nest$fgetmediaDownloadSectionRow(this.this$0)) goto L148;
            r118.setText(LocaleController.getString(R.string.AutomaticMediaDownload));
            return;
        L148:
            if (r19 != DataSettingsActivity.m14237$$Nest$fgetusageSectionRow(this.this$0)) goto L152;
            r118.setText(LocaleController.getString(R.string.DataUsage));
            return;
        L152:
            if (r19 != DataSettingsActivity.m14204$$Nest$fgetcallsSectionRow(this.this$0)) goto L156;
            r118.setText(LocaleController.getString(R.string.Calls));
            return;
        L156:
            if (r19 != DataSettingsActivity.m14218$$Nest$fgetproxySectionRow(this.this$0)) goto L160;
            r118.setText(LocaleController.getString(R.string.Proxy));
            return;
        L160:
            if (r19 != DataSettingsActivity.m14233$$Nest$fgetstreamSectionRow(this.this$0)) goto L164;
            r118.setText(LocaleController.getString(R.string.Streaming));
            return;
        L164:
            if (r19 != DataSettingsActivity.m14200$$Nest$fgetautoplayHeaderRow(this.this$0)) goto L168;
            r118.setText(LocaleController.getString(R.string.AutoplayMedia));
            return;
        L168:
            if (r19 != DataSettingsActivity.m14227$$Nest$fgetsaveToGallerySectionRow(this.this$0)) goto L211;
            r118.setText(LocaleController.getString(R.string.SaveToGallerySettings));
            return;
        L211:
            return;
        L171:
            TextSettingsCell r119 = (TextSettingsCell) r18.itemView;
            r119.setCanDisable(false);
            r119.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            if (r19 != DataSettingsActivity.m14238$$Nest$fgetuseLessDataForCallsRow(this.this$0)) goto L187;
            r119.setIcon(0);
            int r24 = MessagesController.getGlobalMainSettings().getInt("VoipDataSaving", VoIPHelper.getDataSavingDefault());
            if (r24 == 0) goto L183;
            if (r24 == 1) goto L182;
            if (r24 != 2) goto L178;
            r5 = LocaleController.getString(R.string.UseLessDataAlways);
        L184:
            r119.setTextAndValue(LocaleController.getString(R.string.VoipUseLessData), r5, DataSettingsActivity.m14235$$Nest$fgetupdateVoipUseLessData(this.this$0), false);
            DataSettingsActivity.m14241$$Nest$fputupdateVoipUseLessData(this.this$0, false);
            return;
        L178:
            if (r24 != 3) goto L184;
            r5 = LocaleController.getString(R.string.UseLessDataOnRoaming);
            goto L184
        L182:
            r5 = LocaleController.getString(R.string.UseLessDataOnMobile);
            goto L184
        L183:
            r5 = LocaleController.getString(R.string.UseLessDataNever);
            goto L184
        L187:
            if (r19 != DataSettingsActivity.m14216$$Nest$fgetproxyRow(this.this$0)) goto L191;
            r119.setIcon(0);
            r119.setText(LocaleController.getString(R.string.ProxySettings), false);
            return;
        L191:
            if (r19 != DataSettingsActivity.m14220$$Nest$fgetresetDownloadRow(this.this$0)) goto L195;
            r119.setIcon(0);
            r119.setCanDisable(true);
            r119.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
            r119.setText(LocaleController.getString(R.string.ResetAutomaticMediaDownload), false);
            return;
        L195:
            if (r19 != DataSettingsActivity.m14219$$Nest$fgetquickRepliesRow(this.this$0)) goto L199;
            r119.setIcon(0);
            r119.setText(LocaleController.getString(R.string.VoipQuickReplies), false);
            return;
        L199:
            if (r19 != DataSettingsActivity.m14205$$Nest$fgetclearDraftsRow(this.this$0)) goto L212;
            r119.setIcon(0);
            r119.setText(LocaleController.getString(R.string.PrivacyDeleteCloudDrafts), false);
            return;
        L212:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L4;
            View r22 = new ShadowSectionCell(this.mContext);
        L20:
            r22.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r22);
        L4:
            if (r3 != 1) goto L6;
            r22 = new TextSettingsCell(this.mContext);
            goto L20
        L6:
            if (r3 != 2) goto L8;
            r22 = new HeaderCell(this.mContext, 22);
            goto L20
        L8:
            if (r3 != 3) goto L10;
            r22 = new TextCheckCell(this.mContext);
            goto L20
        L10:
            if (r3 != 4) goto L12;
            r22 = new TextInfoPrivacyCell(this.mContext);
            goto L20
        L12:
            if (r3 == 5) goto L14;
            r22 = new TextCell(this.mContext);
            goto L20
        L14:
            r22 = new NotificationsCheckCell(this.mContext);
            goto L20
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r3) {
            if (r3.getItemViewType() != 3) goto L28;
            TextCheckCell r0 = (TextCheckCell) r3.itemView;
            int r32 = r3.getAdapterPosition();
            if (r32 != DataSettingsActivity.m14210$$Nest$fgetenableCacheStreamRow(this.this$0)) goto L9;
            r0.setChecked(SharedConfig.saveStreamMedia);
            return;
        L9:
            if (r32 != DataSettingsActivity.m14212$$Nest$fgetenableStreamRow(this.this$0)) goto L13;
            r0.setChecked(SharedConfig.streamMedia);
            return;
        L13:
            if (r32 != DataSettingsActivity.m14209$$Nest$fgetenableAllStreamRow(this.this$0)) goto L17;
            r0.setChecked(SharedConfig.streamAllVideo);
            return;
        L17:
            if (r32 != DataSettingsActivity.m14211$$Nest$fgetenableMkvRow(this.this$0)) goto L21;
            r0.setChecked(SharedConfig.streamMkv);
            return;
        L21:
            if (r32 != DataSettingsActivity.m14199$$Nest$fgetautoplayGifsRow(this.this$0)) goto L25;
            r0.setChecked(SharedConfig.isAutoplayGifs());
            return;
        L25:
            if (r32 != DataSettingsActivity.m14202$$Nest$fgetautoplayVideoRow(this.this$0)) goto L29;
            r0.setChecked(SharedConfig.isAutoplayVideo());
            return;
        L29:
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$03rLYlvgdEqRi5D1HI18A1J61t0(DataSettingsActivity r0) {
        r0.lambda$setStorageDirectory$10();
    }

    public static /* synthetic */ void $r8$lambda$0JK9jfmDJYxCEZJR990aEGZujT8(DataSettingsActivity r0, String r1, boolean r2, AlertDialog.Builder r3, View r4) {
        r0.lambda$createView$5(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$2_W6TtE4ZAsNjffj8HunhLqzmJc(DataSettingsActivity r0) {
        r0.lambda$loadCacheSize$0();
    }

    public static /* synthetic */ void $r8$lambda$4D1__uhmZD5pmXvdOapTq6x9_iA(DataSettingsActivity r0) {
        r0.lambda$createView$6();
    }

    public static /* synthetic */ void $r8$lambda$CYgPMvPn5XAkCVxEioDkxfe5tQQ(DataSettingsActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createView$7(r1, r2);
    }

    /* renamed from: $r8$lambda$D-62bYnUqrYleSjAHqQss0u6Jlk, reason: not valid java name */
    public static /* synthetic */ void m14197$r8$lambda$D62bYnUqrYleSjAHqQss0u6Jlk(DataSettingsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$8(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$G_kCCV1z0VKdV6iTwgbiYya0YGA(DataSettingsActivity r0, Runnable r1, long r2, Long r4) {
        r0.lambda$loadCacheSize$1(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$SIMov302ZiLVcraBftYsS9KcDHw(DataSettingsActivity r0, SharedPreferences r1, int r2, DialogInterface r3, int r4) {
        r0.lambda$createView$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$TRZjucAfvelBgeltLr8gcVSkTGY(DataSettingsActivity r0, String r1, AlertDialog.Builder r2, AlertDialog r3, int r4) {
        r0.lambda$createView$4(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$k5igjFDbAeImcVEoUlD3wkvDdx4(DataSettingsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$2(r1, r2);
    }

    /* renamed from: $r8$lambda$vexlVgA5-JNCPkebBSOgpXrJ36w, reason: not valid java name */
    public static /* synthetic */ void m14198$r8$lambda$vexlVgA5JNCPkebBSOgpXrJ36w(DataSettingsActivity r0, Context r1, View r2, int r3, float r4, float r5) {
        r0.lambda$createView$9(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$fgetautoplayGifsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14199$$Nest$fgetautoplayGifsRow(DataSettingsActivity r0) {
        return r0.autoplayGifsRow;
    }

    /* renamed from: -$$Nest$fgetautoplayHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14200$$Nest$fgetautoplayHeaderRow(DataSettingsActivity r0) {
        return r0.autoplayHeaderRow;
    }

    /* renamed from: -$$Nest$fgetautoplaySectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14201$$Nest$fgetautoplaySectionRow(DataSettingsActivity r0) {
        return r0.autoplaySectionRow;
    }

    /* renamed from: -$$Nest$fgetautoplayVideoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14202$$Nest$fgetautoplayVideoRow(DataSettingsActivity r0) {
        return r0.autoplayVideoRow;
    }

    /* renamed from: -$$Nest$fgetcallsSection2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14203$$Nest$fgetcallsSection2Row(DataSettingsActivity r0) {
        return r0.callsSection2Row;
    }

    /* renamed from: -$$Nest$fgetcallsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14204$$Nest$fgetcallsSectionRow(DataSettingsActivity r0) {
        return r0.callsSectionRow;
    }

    /* renamed from: -$$Nest$fgetclearDraftsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14205$$Nest$fgetclearDraftsRow(DataSettingsActivity r0) {
        return r0.clearDraftsRow;
    }

    /* renamed from: -$$Nest$fgetclearDraftsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14206$$Nest$fgetclearDraftsSectionRow(DataSettingsActivity r0) {
        return r0.clearDraftsSectionRow;
    }

    /* renamed from: -$$Nest$fgetdataUsageRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14207$$Nest$fgetdataUsageRow(DataSettingsActivity r0) {
        return r0.dataUsageRow;
    }

    /* renamed from: -$$Nest$fgetenableAllStreamInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14208$$Nest$fgetenableAllStreamInfoRow(DataSettingsActivity r0) {
        return r0.enableAllStreamInfoRow;
    }

    /* renamed from: -$$Nest$fgetenableAllStreamRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14209$$Nest$fgetenableAllStreamRow(DataSettingsActivity r0) {
        return r0.enableAllStreamRow;
    }

    /* renamed from: -$$Nest$fgetenableCacheStreamRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14210$$Nest$fgetenableCacheStreamRow(DataSettingsActivity r0) {
        return r0.enableCacheStreamRow;
    }

    /* renamed from: -$$Nest$fgetenableMkvRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14211$$Nest$fgetenableMkvRow(DataSettingsActivity r0) {
        return r0.enableMkvRow;
    }

    /* renamed from: -$$Nest$fgetenableStreamRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14212$$Nest$fgetenableStreamRow(DataSettingsActivity r0) {
        return r0.enableStreamRow;
    }

    /* renamed from: -$$Nest$fgetmediaDownloadSection2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14213$$Nest$fgetmediaDownloadSection2Row(DataSettingsActivity r0) {
        return r0.mediaDownloadSection2Row;
    }

    /* renamed from: -$$Nest$fgetmediaDownloadSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14214$$Nest$fgetmediaDownloadSectionRow(DataSettingsActivity r0) {
        return r0.mediaDownloadSectionRow;
    }

    /* renamed from: -$$Nest$fgetmobileRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14215$$Nest$fgetmobileRow(DataSettingsActivity r0) {
        return r0.mobileRow;
    }

    /* renamed from: -$$Nest$fgetproxyRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14216$$Nest$fgetproxyRow(DataSettingsActivity r0) {
        return r0.proxyRow;
    }

    /* renamed from: -$$Nest$fgetproxySection2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14217$$Nest$fgetproxySection2Row(DataSettingsActivity r0) {
        return r0.proxySection2Row;
    }

    /* renamed from: -$$Nest$fgetproxySectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14218$$Nest$fgetproxySectionRow(DataSettingsActivity r0) {
        return r0.proxySectionRow;
    }

    /* renamed from: -$$Nest$fgetquickRepliesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14219$$Nest$fgetquickRepliesRow(DataSettingsActivity r0) {
        return r0.quickRepliesRow;
    }

    /* renamed from: -$$Nest$fgetresetDownloadRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14220$$Nest$fgetresetDownloadRow(DataSettingsActivity r0) {
        return r0.resetDownloadRow;
    }

    /* renamed from: -$$Nest$fgetroamingRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14221$$Nest$fgetroamingRow(DataSettingsActivity r0) {
        return r0.roamingRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14222$$Nest$fgetrowCount(DataSettingsActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsaveToGalleryChannelsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14223$$Nest$fgetsaveToGalleryChannelsRow(DataSettingsActivity r0) {
        return r0.saveToGalleryChannelsRow;
    }

    /* renamed from: -$$Nest$fgetsaveToGalleryDividerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14224$$Nest$fgetsaveToGalleryDividerRow(DataSettingsActivity r0) {
        return r0.saveToGalleryDividerRow;
    }

    /* renamed from: -$$Nest$fgetsaveToGalleryGroupsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14225$$Nest$fgetsaveToGalleryGroupsRow(DataSettingsActivity r0) {
        return r0.saveToGalleryGroupsRow;
    }

    /* renamed from: -$$Nest$fgetsaveToGalleryPeerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14226$$Nest$fgetsaveToGalleryPeerRow(DataSettingsActivity r0) {
        return r0.saveToGalleryPeerRow;
    }

    /* renamed from: -$$Nest$fgetsaveToGallerySectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14227$$Nest$fgetsaveToGallerySectionRow(DataSettingsActivity r0) {
        return r0.saveToGallerySectionRow;
    }

    /* renamed from: -$$Nest$fgetstorageDirs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m14228$$Nest$fgetstorageDirs(DataSettingsActivity r0) {
        return r0.storageDirs;
    }

    /* renamed from: -$$Nest$fgetstorageNumRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14229$$Nest$fgetstorageNumRow(DataSettingsActivity r0) {
        return r0.storageNumRow;
    }

    /* renamed from: -$$Nest$fgetstorageUsageLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14230$$Nest$fgetstorageUsageLoading(DataSettingsActivity r0) {
        return r0.storageUsageLoading;
    }

    /* renamed from: -$$Nest$fgetstorageUsageRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14231$$Nest$fgetstorageUsageRow(DataSettingsActivity r0) {
        return r0.storageUsageRow;
    }

    /* renamed from: -$$Nest$fgetstorageUsageSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m14232$$Nest$fgetstorageUsageSize(DataSettingsActivity r2) {
        return r2.storageUsageSize;
    }

    /* renamed from: -$$Nest$fgetstreamSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14233$$Nest$fgetstreamSectionRow(DataSettingsActivity r0) {
        return r0.streamSectionRow;
    }

    /* renamed from: -$$Nest$fgetupdateStorageUsageAnimated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14234$$Nest$fgetupdateStorageUsageAnimated(DataSettingsActivity r0) {
        return r0.updateStorageUsageAnimated;
    }

    /* renamed from: -$$Nest$fgetupdateVoipUseLessData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14235$$Nest$fgetupdateVoipUseLessData(DataSettingsActivity r0) {
        return r0.updateVoipUseLessData;
    }

    /* renamed from: -$$Nest$fgetusageSection2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14236$$Nest$fgetusageSection2Row(DataSettingsActivity r0) {
        return r0.usageSection2Row;
    }

    /* renamed from: -$$Nest$fgetusageSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14237$$Nest$fgetusageSectionRow(DataSettingsActivity r0) {
        return r0.usageSectionRow;
    }

    /* renamed from: -$$Nest$fgetuseLessDataForCallsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14238$$Nest$fgetuseLessDataForCallsRow(DataSettingsActivity r0) {
        return r0.useLessDataForCallsRow;
    }

    /* renamed from: -$$Nest$fgetwifiRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14239$$Nest$fgetwifiRow(DataSettingsActivity r0) {
        return r0.wifiRow;
    }

    /* renamed from: -$$Nest$fputupdateStorageUsageAnimated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14240$$Nest$fputupdateStorageUsageAnimated(DataSettingsActivity r0, boolean r1) {
        r0.updateStorageUsageAnimated = r1;
    }

    /* renamed from: -$$Nest$fputupdateVoipUseLessData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14241$$Nest$fputupdateVoipUseLessData(DataSettingsActivity r0, boolean r1) {
        r0.updateVoipUseLessData = r1;
    }

    public DataSettingsActivity() {
        this.resetDownloadRow = -1;
        this.autoplayHeaderRow = -1;
        this.autoplayGifsRow = -1;
        this.autoplayVideoRow = -1;
        this.autoplaySectionRow = -1;
        this.quickRepliesRow = -1;
    }

    public static /* synthetic */ int access$000(DataSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(DataSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(DataSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(DataSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(DataSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(DataSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$600(DataSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$700(DataSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$800(DataSettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$900(DataSettingsActivity r0) {
        return r0.currentAccount;
    }

    private /* synthetic */ void lambda$createView$2(AlertDialog r6, int r7) {
        SharedPreferences.Editor r62 = MessagesController.getMainSettings(this.currentAccount).edit();
        int r0 = 0;
    L4:
        if (r0 >= 3) goto L12;
        if (r0 != 0) goto L7;
        DownloadController.Preset r2 = DownloadController.getInstance(this.currentAccount).mobilePreset;
        DownloadController.Preset r3 = DownloadController.getInstance(this.currentAccount).mediumPreset;
        String r4 = "mobilePreset";
    L11:
        r2.set(r3);
        r2.enabled = r3.isEnabled();
        DownloadController.getInstance(this.currentAccount).currentMobilePreset = 3;
        r62.putInt("currentMobilePreset", 3);
        DownloadController.getInstance(this.currentAccount).currentWifiPreset = 3;
        r62.putInt("currentWifiPreset", 3);
        DownloadController.getInstance(this.currentAccount).currentRoamingPreset = 3;
        r62.putInt("currentRoamingPreset", 3);
        r62.putString(r4, r2.toString());
        r0 = r0 + 1;
        goto L4
    L7:
        int r22 = this.currentAccount;
        if (r0 != 1) goto L10;
        r2 = DownloadController.getInstance(r22).wifiPreset;
        r3 = DownloadController.getInstance(this.currentAccount).highPreset;
        r4 = "wifiPreset";
        goto L11
    L10:
        r2 = DownloadController.getInstance(r22).roamingPreset;
        r3 = DownloadController.getInstance(this.currentAccount).lowPreset;
        r4 = "roamingPreset";
        goto L11
    L12:
        r62.apply();
        DownloadController.getInstance(this.currentAccount).checkAutodownloadSettings();
        int r63 = 0;
    L13:
        if (r63 >= 3) goto L15;
        DownloadController.getInstance(this.currentAccount).savePresetToServer(r63);
        r63 = r63 + 1;
        goto L13
    L15:
        this.listAdapter.notifyItemRangeChanged(this.mobileRow, 4);
        updateRows(false);
    }

    private /* synthetic */ void lambda$createView$3(SharedPreferences r4, int r5, DialogInterface r6, int r7) {
        if (r7 == 0) goto L12;
        int r1 = 3;
        if (r7 != 1) goto L7;
    L13:
        if (r1 == (-1)) goto L15;
        r4.edit().putInt("VoipDataSaving", r1).apply();
        this.updateVoipUseLessData = true;
    L15:
        ListAdapter r3 = this.listAdapter;
        if (r3 == null) goto L19;
        r3.notifyItemChanged(r5);
        return;
    L19:
        return;
    L7:
        if (r7 == 2) goto L11;
        if (r7 == 3) goto L10;
        r1 = -1;
        goto L13
    L10:
        r1 = 2;
        goto L13
    L11:
        r1 = 1;
        goto L13
    L12:
        r1 = 0;
        goto L13
    }

    private /* synthetic */ void lambda$createView$4(String r1, AlertDialog.Builder r2, AlertDialog r3, int r4) {
        setStorageDirectory(r1);
        r2.getDismissRunnable().run();
    }

    private /* synthetic */ void lambda$createView$5(String r2, boolean r3, AlertDialog.Builder r4, View r5) {
        if (TextUtils.equals(SharedConfig.storageCacheDir, r2) == true) goto L9;
        if (r3 == true) goto L7;
        AlertDialog.Builder r32 = new AlertDialog.Builder(getContext());
        r32.setTitle(LocaleController.getString(R.string.DecreaseSpeed));
        r32.setMessage(LocaleController.getString(R.string.SdCardAlert));
        r32.setPositiveButton(LocaleController.getString(R.string.Proceed), new DataSettingsActivity$$ExternalSyntheticLambda7(this, r2, r4));
        r32.setNegativeButton(LocaleController.getString(R.string.Back), null);
        r32.show();
        return;
    L7:
        setStorageDirectory(r2);
        r4.getDismissRunnable().run();
        return;
    }

    private /* synthetic */ void lambda$createView$6() {
        getMediaDataController().clearAllDrafts(true);
    }

    private /* synthetic */ void lambda$createView$7(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new DataSettingsActivity$$ExternalSyntheticLambda10(this));
    }

    private /* synthetic */ void lambda$createView$8(AlertDialog r2, int r3) {
        TLRPC.TL_messages_clearAllDrafts r22 = new TLRPC.TL_messages_clearAllDrafts();
        getConnectionsManager().sendRequest(r22, new DataSettingsActivity$$ExternalSyntheticLambda8(this));
    }

    private /* synthetic */ void lambda$createView$9(Context r20, View r21, int r22, float r23, float r24) {
        int r4 = this.saveToGalleryGroupsRow;
        int r6 = 2;
        boolean r7 = true;
        if (r22 != r4) goto L5;
    L154:
        if (r22 == r4) goto L161;
        if (r22 != this.saveToGalleryChannelsRow) goto L159;
        r6 = 4;
        goto L161
    L159:
        r6 = 1;
    L161:
        if (LocaleController.isRTL == false) goto L165;
        if (r23 > AndroidUtilities.dp(76.0f)) goto L165;
    L168:
        SaveToGallerySettingsHelper.getSettings(r6).toggle();
        AndroidUtilities.updateVisibleRows(this.listView);
        return;
    L165:
        if (LocaleController.isRTL == false) goto L167;
    L170:
        Bundle r1 = new Bundle();
        r1.putInt("type", r6);
        presentFragment(new SaveToGallerySettingsActivity(r1));
        return;
    L167:
        if (r23 < (r21.getMeasuredWidth() - AndroidUtilities.dp(76.0f))) goto L170;
    L5:
        if (r22 == this.saveToGalleryChannelsRow) goto L154;
        if (r22 == this.saveToGalleryPeerRow) goto L154;
        if (r22 == this.mobileRow) goto L122;
        if (r22 == this.roamingRow) goto L122;
        if (r22 == this.wifiRow) goto L122;
        if (r22 != this.resetDownloadRow) goto L28;
        if (getParentActivity() != null) goto L21;
        return;
    L21:
        if (r21.isEnabled() == false) goto L183;
        AlertDialog.Builder r12 = new AlertDialog.Builder(getParentActivity());
        r12.setTitle(LocaleController.getString(R.string.ResetAutomaticMediaDownloadAlertTitle));
        r12.setMessage(LocaleController.getString(R.string.ResetAutomaticMediaDownloadAlert));
        r12.setPositiveButton(LocaleController.getString(R.string.Reset), new DataSettingsActivity$$ExternalSyntheticLambda3(this));
        r12.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r13 = r12.create();
        showDialog(r13);
        TextView r0 = (TextView) r13.getButton(-1);
        if (r0 == null) goto L178;
        r0.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L178:
        return;
    L183:
        return;
    L28:
        if (r22 != this.storageUsageRow) goto L32;
        presentFragment(new CacheControlActivity());
        return;
    L32:
        if (r22 != this.useLessDataForCallsRow) goto L44;
        SharedPreferences r14 = MessagesController.getGlobalMainSettings();
        int r3 = r14.getInt("VoipDataSaving", VoIPHelper.getDataSavingDefault());
        if (r3 == 0) goto L38;
        if (r3 == 1) goto L41;
        if (r3 == 2) goto L40;
        if (r3 != 3) goto L38;
        r6 = 1;
        goto L41
    L40:
        r6 = 3;
    L41:
        Dialog r15 = AlertsCreator.createSingleChoiceDialog(getParentActivity(), new String[]{LocaleController.getString(R.string.UseLessDataNever), LocaleController.getString(R.string.UseLessDataOnRoaming), LocaleController.getString(R.string.UseLessDataOnMobile), LocaleController.getString(R.string.UseLessDataAlways)}, LocaleController.getString(R.string.VoipUseLessData), r6, new DataSettingsActivity$$ExternalSyntheticLambda4(this, r14, r22));
        setVisibleDialog(r15);
        r15.show();
        return;
    L38:
        r6 = 0;
        goto L41
    L44:
        if (r22 != this.dataUsageRow) goto L48;
        presentFragment(new DataUsage2Activity());
        return;
    L48:
        if (r22 != this.storageNumRow) goto L80;
        AlertDialog.Builder r16 = new AlertDialog.Builder(getParentActivity());
        r16.setTitle(LocaleController.getString(R.string.StoragePath));
        LinearLayout r2 = new LinearLayout(getParentActivity());
        r2.setOrientation(1);
        r16.setView(r2);
        String r42 = this.storageDirs.get(0).getAbsolutePath();
        if (TextUtils.isEmpty(SharedConfig.storageCacheDir) == true) goto L173;
        int r5 = this.storageDirs.size();
        int r8 = 0;
    L52:
        if (r8 >= r5) goto L173;
        String r11 = this.storageDirs.get(r8).getAbsolutePath();
        if (r11.startsWith(SharedConfig.storageCacheDir) == true) goto L55;
        r8 = r8 + 1;
        goto L52
    L55:
        r42 = r11;
    L173:
        if (this.storageDirs.size() != 2) goto L63;
        if (this.storageDirs.get(0).getAbsolutePath().contains("/storage/emulated/") == this.storageDirs.get(1).getAbsolutePath().contains("/storage/emulated/")) goto L63;
        r7 = false;
    L63:
        int r52 = this.storageDirs.size();
        int r82 = 0;
    L64:
        if (r82 >= r52) goto L77;
        File r112 = this.storageDirs.get(r82);
        String r122 = r112.getAbsolutePath();
        LanguageCell r132 = new LanguageCell(r20);
        r132.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r132.setTag(Integer.valueOf(r82));
        boolean r10 = r122.contains("/storage/emulated/");
        if (r7 == false) goto L69;
        if (r10 == true) goto L69;
        String r113 = LocaleController.formatString(R.string.StoragePathFreeValueExternal, new Object[]{AndroidUtilities.formatFileSize(r112.getFreeSpace()), r122});
    L72:
        if (r10 == false) goto L75;
        int r152 = R.string.InternalStorage;
    L76:
        r132.setValue(LocaleController.getString(r152), r113);
        r132.setLanguageSelected(r122.startsWith(r42), false);
        r132.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_dialogButtonSelector), 2));
        r2.addView(r132);
        r132.setOnClickListener(new DataSettingsActivity$$ExternalSyntheticLambda5(this, r122, r10, r16));
        r82 = r82 + 1;
        goto L64
    L75:
        r152 = R.string.SdCard;
    L69:
        if (r10 == false) goto L71;
        r113 = LocaleController.formatString(R.string.StoragePathFreeInternal, new Object[]{AndroidUtilities.formatFileSize(r112.getFreeSpace())});
        goto L72
    L71:
        r113 = LocaleController.formatString(R.string.StoragePathFreeExternal, new Object[]{AndroidUtilities.formatFileSize(r112.getFreeSpace())});
        goto L72
    L77:
        r16.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r16.create());
        return;
    L80:
        if (r22 != this.proxyRow) goto L84;
        presentFragment(new ProxyListActivity());
        return;
    L84:
        if (r22 != this.enableStreamRow) goto L88;
        SharedConfig.toggleStreamMedia();
        ((TextCheckCell) r21).setChecked(SharedConfig.streamMedia);
        return;
    L88:
        if (r22 != this.enableAllStreamRow) goto L92;
        SharedConfig.toggleStreamAllVideo();
        ((TextCheckCell) r21).setChecked(SharedConfig.streamAllVideo);
        return;
    L92:
        if (r22 != this.enableMkvRow) goto L96;
        SharedConfig.toggleStreamMkv();
        ((TextCheckCell) r21).setChecked(SharedConfig.streamMkv);
        return;
    L96:
        if (r22 != this.enableCacheStreamRow) goto L100;
        SharedConfig.toggleSaveStreamMedia();
        ((TextCheckCell) r21).setChecked(SharedConfig.saveStreamMedia);
        return;
    L100:
        if (r22 != this.quickRepliesRow) goto L104;
        presentFragment(new QuickRepliesSettingsActivity());
        return;
    L104:
        if (r22 != this.autoplayGifsRow) goto L110;
        SharedConfig.toggleAutoplayGifs();
        if ((r21 instanceof TextCheckCell) == false) goto L179;
        ((TextCheckCell) r21).setChecked(SharedConfig.isAutoplayGifs());
        return;
    L179:
        return;
    L110:
        if (r22 != this.autoplayVideoRow) goto L116;
        SharedConfig.toggleAutoplayVideo();
        if ((r21 instanceof TextCheckCell) == false) goto L180;
        ((TextCheckCell) r21).setChecked(SharedConfig.isAutoplayVideo());
        return;
    L180:
        return;
    L116:
        if (r22 != this.clearDraftsRow) goto L181;
        AlertDialog.Builder r17 = new AlertDialog.Builder(getParentActivity());
        r17.setTitle(LocaleController.getString(R.string.AreYouSureClearDraftsTitle));
        r17.setMessage(LocaleController.getString(R.string.AreYouSureClearDrafts));
        r17.setPositiveButton(LocaleController.getString(R.string.Delete), new DataSettingsActivity$$ExternalSyntheticLambda6(this));
        r17.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r18 = r17.create();
        showDialog(r18);
        TextView r02 = (TextView) r18.getButton(-1);
        if (r02 == null) goto L182;
        r02.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L182:
        return;
    L181:
        return;
    L122:
        if (LocaleController.isRTL == false) goto L126;
        if (r23 > AndroidUtilities.dp(76.0f)) goto L126;
    L129:
        this.listAdapter.isRowEnabled(this.resetDownloadRow);
        NotificationsCheckCell r32 = (NotificationsCheckCell) r21;
        boolean r43 = r32.isChecked();
        if (r22 != this.mobileRow) goto L132;
        DownloadController.Preset r53 = DownloadController.getInstance(this.currentAccount).mobilePreset;
        DownloadController.Preset r62 = DownloadController.getInstance(this.currentAccount).mediumPreset;
        String r123 = "currentMobilePreset";
        String r114 = "mobilePreset";
        int r102 = 0;
    L136:
        if (r43 == false) goto L138;
    L140:
        r53.enabled = !r53.enabled;
    L141:
        SharedPreferences.Editor r63 = MessagesController.getMainSettings(this.currentAccount).edit();
        r63.putString(r114, r53.toString());
        r63.putInt(r123, 3);
        r63.apply();
        r32.setChecked(!r43);
        RecyclerView.ViewHolder r19 = this.listView.findContainingViewHolder(r21);
        if (r19 == null) goto L144;
        this.listAdapter.onBindViewHolder(r19, r22);
    L144:
        DownloadController.getInstance(this.currentAccount).checkAutodownloadSettings();
        DownloadController.getInstance(this.currentAccount).savePresetToServer(r102);
        updateRows(false);
        return;
    L138:
        if (r53.enabled == false) goto L140;
        r53.set(r62);
        goto L141
    L132:
        int r54 = this.wifiRow;
        int r103 = this.currentAccount;
        if (r22 != r54) goto L135;
        r53 = DownloadController.getInstance(r103).wifiPreset;
        r62 = DownloadController.getInstance(this.currentAccount).highPreset;
        r123 = "currentWifiPreset";
        r114 = "wifiPreset";
        r102 = 1;
        goto L136
    L135:
        r53 = DownloadController.getInstance(r103).roamingPreset;
        r114 = "roamingPreset";
        r123 = "currentRoamingPreset";
        r102 = 2;
        r62 = DownloadController.getInstance(this.currentAccount).lowPreset;
    L126:
        if (LocaleController.isRTL == true) goto L147;
        if (r23 >= (r21.getMeasuredWidth() - AndroidUtilities.dp(76.0f))) goto L129;
    L147:
        if (r22 != this.mobileRow) goto L150;
        r6 = 0;
    L152:
        presentFragment(new DataAutoDownloadActivity(r6));
        return;
    L150:
        if (r22 != this.wifiRow) goto L152;
        r6 = 1;
        goto L152
    }

    private /* synthetic */ void lambda$loadCacheSize$0() {
        this.storageUsageLoading = true;
        if (this.listAdapter == null) goto L8;
        int r0 = this.storageUsageRow;
        if (r0 < 0) goto L9;
        rebind(r0);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$loadCacheSize$1(Runnable r4, long r5, Long r7) {
        AndroidUtilities.cancelRunOnUIThread(r4);
        if (this.updateStorageUsageAnimated == false) goto L5;
    L8:
        boolean r42 = true;
    L9:
        this.updateStorageUsageAnimated = r42;
        this.storageUsageSize = r7.longValue();
        this.storageUsageLoading = false;
        if (this.listAdapter == null) goto L15;
        int r43 = this.storageUsageRow;
        if (r43 < 0) goto L16;
        rebind(r43);
        return;
    L16:
        return;
    L15:
        return;
    L5:
        if ((System.currentTimeMillis() - r5) > 120) goto L8;
        r42 = false;
        goto L9
    }

    private /* synthetic */ void lambda$setStorageDirectory$10() {
        CacheControlActivity.resetCalculatedTotalSIze();
        loadCacheSize();
    }

    private void loadCacheSize() {
        DataSettingsActivity$$ExternalSyntheticLambda1 r0 = new DataSettingsActivity$$ExternalSyntheticLambda1(this);
        AndroidUtilities.runOnUIThread(r0, 100);
        CacheControlActivity.calculateTotalSize(new DataSettingsActivity$$ExternalSyntheticLambda2(this, r0, System.currentTimeMillis()));
    }

    private void rebind(int r4) {
        if (this.listView != null) goto L5;
        return;
    L5:
        if (this.listAdapter == null) goto L22;
        int r0 = 0;
    L9:
        if (r0 >= this.listView.getChildCount()) goto L23;
        View r1 = this.listView.getChildAt(r0);
        RecyclerView.ViewHolder r12 = this.listView.getChildViewHolder(r1);
        if (r12 == null) goto L16;
        if (r12.getAdapterPosition() != r4) goto L16;
        this.listAdapter.onBindViewHolder(r12, r4);
        return;
    L16:
        r0 = r0 + 1;
        goto L9
    L23:
        return;
    }

    private void rebindAll() {
        if (this.listView != null) goto L5;
        return;
    L5:
        if (this.listAdapter == null) goto L18;
        int r0 = 0;
    L9:
        if (r0 >= this.listView.getChildCount()) goto L19;
        View r1 = this.listView.getChildAt(r0);
        RecyclerView.ViewHolder r2 = this.listView.getChildViewHolder(r1);
        if (r2 == null) goto L13;
        this.listAdapter.onBindViewHolder(r2, this.listView.getChildAdapterPosition(r1));
    L13:
        r0 = r0 + 1;
        goto L9
    L19:
        return;
    }

    private void setStorageDirectory(String r2) {
        SharedConfig.storageCacheDir = r2;
        SharedConfig.saveConfig();
        if (r2 == null) goto L5;
        SharedConfig.readOnlyStorageDirAlertShowed = false;
    L5:
        rebind(this.storageNumRow);
        ImageLoader.getInstance().checkMediaPaths(new DataSettingsActivity$$ExternalSyntheticLambda9(this));
    }

    private void updateRows(boolean r7) {
        boolean r0 = false;
        this.usageSectionRow = 0;
        int r2 = 1 + 1;
        this.storageUsageRow = 1;
        this.rowCount = r2 + 1;
        this.dataUsageRow = r2;
        this.storageNumRow = -1;
        ArrayList<File> r3 = AndroidUtilities.getRootDirs();
        this.storageDirs = r3;
        if (r3.size() <= 1) goto L5;
        int r32 = this.rowCount;
        this.rowCount = r32 + 1;
        this.storageNumRow = r32;
    L5:
        int r33 = this.rowCount;
        this.usageSection2Row = r33;
        this.mediaDownloadSectionRow = r33 + 1;
        this.mobileRow = r33 + 2;
        this.wifiRow = r33 + 3;
        this.rowCount = r33 + 5;
        this.roamingRow = r33 + 4;
        DownloadController r34 = getDownloadController();
        if (r34.lowPreset.equals(r34.getCurrentRoamingPreset()) == true) goto L8;
    L18:
        int r35 = this.resetDownloadRow;
        if (r0 == false) goto L21;
        int r02 = -1;
    L22:
        this.resetDownloadRow = r02;
        ListAdapter r4 = this.listAdapter;
        if (r4 == null) goto L32;
        if (r7 == true) goto L32;
        if (r35 >= 0) goto L28;
        if (r02 < 0) goto L28;
        r4.notifyItemChanged(this.roamingRow);
        this.listAdapter.notifyItemInserted(this.resetDownloadRow);
    L28:
        if (r35 < 0) goto L31;
        if (r02 >= 0) goto L31;
        r4.notifyItemChanged(this.roamingRow);
        this.listAdapter.notifyItemRemoved(r35);
    L31:
        r7 = true;
    L32:
        int r03 = this.rowCount;
        this.mediaDownloadSection2Row = r03;
        this.saveToGallerySectionRow = r03 + 1;
        this.saveToGalleryPeerRow = r03 + 2;
        this.saveToGalleryGroupsRow = r03 + 3;
        this.saveToGalleryChannelsRow = r03 + 4;
        this.saveToGalleryDividerRow = r03 + 5;
        this.streamSectionRow = r03 + 6;
        int r36 = r03 + 8;
        this.rowCount = r36;
        this.enableStreamRow = r03 + 7;
        if (BuildVars.DEBUG_VERSION == false) goto L35;
        this.enableMkvRow = r36;
        this.rowCount = r03 + 10;
        this.enableAllStreamRow = r03 + 9;
    L36:
        int r04 = this.rowCount;
        this.enableAllStreamInfoRow = r04;
        this.enableCacheStreamRow = -1;
        this.callsSectionRow = r04 + 1;
        this.useLessDataForCallsRow = r04 + 2;
        this.callsSection2Row = r04 + 3;
        this.proxySectionRow = r04 + 4;
        this.proxyRow = r04 + 5;
        this.proxySection2Row = r04 + 6;
        this.clearDraftsRow = r04 + 7;
        this.rowCount = r04 + 9;
        this.clearDraftsSectionRow = r04 + 8;
        ListAdapter r6 = this.listAdapter;
        if (r6 == null) goto L41;
        if (r7 == false) goto L42;
        r6.notifyDataSetChanged();
        return;
    L42:
        return;
    L41:
        return;
    L35:
        this.enableAllStreamRow = -1;
        this.enableMkvRow = -1;
        goto L36
    L21:
        r02 = this.rowCount;
        this.rowCount = r02 + 1;
        goto L22
    L8:
        if (r34.lowPreset.isEnabled() != r34.roamingPreset.enabled) goto L18;
        if (r34.mediumPreset.equals(r34.getCurrentMobilePreset()) == false) goto L18;
        if (r34.mediumPreset.isEnabled() != r34.mobilePreset.enabled) goto L18;
        if (r34.highPreset.equals(r34.getCurrentWiFiPreset()) == false) goto L18;
        if (r34.highPreset.isEnabled() != r34.wifiPreset.enabled) goto L18;
        r0 = true;
        goto L18
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r6) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setTitle(LocaleController.getString(R.string.DataSettings));
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
    L7:
        this.listAdapter = new ListAdapter(this, r6);
        FrameLayout r02 = new FrameLayout(r6);
        this.fragmentView = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        FrameLayout r03 = (FrameLayout) this.fragmentView;
        AnonymousClass2 r2 = new AnonymousClass2(this, r6);
        this.listView = r2;
        r2.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setVerticalScrollBarEnabled(false);
        RecyclerListView r22 = this.listView;
        LinearLayoutManager r4 = new LinearLayoutManager(r6, 1, false);
        this.layoutManager = r4;
        r22.setLayoutManager(r4);
        r03.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new DataSettingsActivity$$ExternalSyntheticLambda0(this, r6));
        DefaultItemAnimator r62 = new DefaultItemAnimator();
        r62.setDurations(350);
        r62.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r62.setDelayAnimations(false);
        r62.setSupportsChangeAnimations(false);
        this.listView.setItemAnimator(r62);
        return this.fragmentView;
    L5:
        if (r0.isRightLayout() == false) goto L7;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_close);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextSettingsCell.class, TextCheckCell.class, HeaderCell.class, NotificationsCheckCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        int r28 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"textView"}, null, null, null, r28));
        int r37 = Theme.key_windowBackgroundWhiteGrayText2;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"valueTextView"}, null, null, null, r37));
        int r22 = Theme.key_switchTrack;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"checkBox"}, null, null, null, r22));
        int r23 = Theme.key_switchTrackChecked;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"checkBox"}, null, null, null, r23));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r28));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, r28));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"valueTextView"}, null, null, null, r37));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, r22));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, r23));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onDialogDismiss(Dialog r1) {
        DownloadController.getInstance(this.currentAccount).checkAutodownloadSettings();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        DownloadController.getInstance(this.currentAccount).loadAutoDownloadConfig(true);
        updateRows(true);
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        CacheControlActivity.canceled = true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        loadCacheSize();
        rebindAll();
        updateRows(false);
    }
}
