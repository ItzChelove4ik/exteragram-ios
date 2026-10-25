package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.MaxFileSizeCell;
import org.telegram.ui.Cells.NotificationsCheckCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCheckBoxCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SlideChooseView;

/* loaded from: classes6.dex */
public class DataAutoDownloadActivity extends BaseFragment {
    private boolean animateChecked;

    @Keep
    private int autoDownloadRow;
    private int autoDownloadSectionRow;
    private int currentPresetNum;
    private int currentType;
    private DownloadController.Preset defaultPreset;

    @Keep
    private int filesRow;
    private DownloadController.Preset highPreset;
    private String key;
    private String key2;
    private LinearLayoutManager layoutManager;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private DownloadController.Preset lowPreset;
    private DownloadController.Preset mediumPreset;

    @Keep
    private int photosRow;
    private ArrayList<DownloadController.Preset> presets;
    private int rowCount;
    private int selectedPreset;

    @Keep
    private int storiesRow;
    private int typeHeaderRow;
    private DownloadController.Preset typePreset;
    private int typeSectionRow;
    private int usageHeaderRow;

    @Keep
    private int usageProgressRow;
    private int usageSectionRow;

    @Keep
    private int videosRow;
    private boolean wereAnyChanges;

    /* renamed from: org.telegram.ui.DataAutoDownloadActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ DataAutoDownloadActivity this$0;

        public AnonymousClass1(DataAutoDownloadActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.DataAutoDownloadActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ DataAutoDownloadActivity this$0;
        final /* synthetic */ AnimatorSet[] val$animatorSet;

        public AnonymousClass2(DataAutoDownloadActivity r1, AnimatorSet[] r2) {
            this.this$0 = r1;
            this.val$animatorSet = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (r3.equals(this.val$animatorSet[0]) == false) goto L6;
            this.val$animatorSet[0] = null;
            return;
        }
    }

    /* renamed from: org.telegram.ui.DataAutoDownloadActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends MaxFileSizeCell {
        final /* synthetic */ DataAutoDownloadActivity this$0;
        final /* synthetic */ AnimatorSet[] val$animatorSet;
        final /* synthetic */ TextCheckCell[] val$checkCell;
        final /* synthetic */ TextInfoPrivacyCell val$infoCell;
        final /* synthetic */ int val$position;

        /* renamed from: org.telegram.ui.DataAutoDownloadActivity$3$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                if (r3.equals(this.this$1.val$animatorSet[0]) == false) goto L6;
                this.this$1.val$animatorSet[0] = null;
                return;
            }
        }

        public AnonymousClass3(DataAutoDownloadActivity r1, Context r2, int r3, TextInfoPrivacyCell r4, TextCheckCell[] r5, AnimatorSet[] r6) {
            this.this$0 = r1;
            this.val$position = r3;
            this.val$infoCell = r4;
            this.val$checkCell = r5;
            this.val$animatorSet = r6;
            super(r2);
        }

        @Override // org.telegram.ui.Cells.MaxFileSizeCell
        public void didChangedSizeValue(int r5) {
            if (this.val$position != DataAutoDownloadActivity.m14193$$Nest$fgetvideosRow(this.this$0)) goto L15;
            this.val$infoCell.setText(LocaleController.formatString("AutoDownloadPreloadVideoInfo", R.string.AutoDownloadPreloadVideoInfo, new Object[]{AndroidUtilities.formatFileSize(r5)}));
            if (r5 <= 2097152) goto L7;
            boolean r52 = true;
        L9:
            if (r52 == this.val$checkCell[0].isEnabled()) goto L16;
            ArrayList<Animator> r0 = new ArrayList();
            this.val$checkCell[0].setEnabled(r52, r0);
            AnimatorSet r53 = this.val$animatorSet[0];
            if (r53 == null) goto L13;
            r53.cancel();
            this.val$animatorSet[0] = null;
        L13:
            this.val$animatorSet[0] = new AnimatorSet();
            this.val$animatorSet[0].playTogether(r0);
            this.val$animatorSet[0].addListener(new AnonymousClass1(this));
            this.val$animatorSet[0].setDuration(150);
            this.val$animatorSet[0].start();
            return;
        L16:
            return;
        L7:
            r52 = false;
            goto L9
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ DataAutoDownloadActivity this$0;

        public static /* synthetic */ void $r8$lambda$lx5CrF_HBIkR0loHQHo_pgdwj98(ListAdapter r0, int r1) {
            r0.lambda$onCreateViewHolder$0(r1);
        }

        public ListAdapter(DataAutoDownloadActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(int r5) {
            DownloadController.Preset r52 = (DownloadController.Preset) DataAutoDownloadActivity.m14184$$Nest$fgetpresets(this.this$0).get(r5);
            DownloadController.Preset r0 = DataAutoDownloadActivity.m14181$$Nest$fgetlowPreset(this.this$0);
            DataAutoDownloadActivity r1 = this.this$0;
            int r2 = 0;
            if (r52 != r0) goto L5;
            DataAutoDownloadActivity.m14194$$Nest$fputcurrentPresetNum(r1, 0);
        L12:
            int r53 = DataAutoDownloadActivity.m14175$$Nest$fgetcurrentType(this.this$0);
            DataAutoDownloadActivity r02 = this.this$0;
            if (r53 != 0) goto L15;
            DownloadController r54 = DownloadController.getInstance(DataAutoDownloadActivity.access$300(r02));
            r54.currentMobilePreset = DataAutoDownloadActivity.m14174$$Nest$fgetcurrentPresetNum(this.this$0);
        L19:
            SharedPreferences.Editor r55 = MessagesController.getMainSettings(DataAutoDownloadActivity.access$600(this.this$0)).edit();
            r55.putInt(DataAutoDownloadActivity.m14178$$Nest$fgetkey2(this.this$0), DataAutoDownloadActivity.m14174$$Nest$fgetcurrentPresetNum(this.this$0));
            r55.apply();
            DownloadController.getInstance(DataAutoDownloadActivity.access$700(this.this$0)).checkAutodownloadSettings();
        L20:
            DataAutoDownloadActivity r56 = this.this$0;
            if (r2 >= 4) goto L26;
            RecyclerView.ViewHolder r57 = DataAutoDownloadActivity.m14180$$Nest$fgetlistView(r56).findViewHolderForAdapterPosition(DataAutoDownloadActivity.m14183$$Nest$fgetphotosRow(this.this$0) + r2);
            if (r57 == null) goto L25;
            DataAutoDownloadActivity.m14179$$Nest$fgetlistAdapter(this.this$0).onBindViewHolder(r57, DataAutoDownloadActivity.m14183$$Nest$fgetphotosRow(this.this$0) + r2);
        L25:
            r2 = r2 + 1;
            goto L20
        L26:
            DataAutoDownloadActivity.m14195$$Nest$fputwereAnyChanges(r56, true);
            return;
        L15:
            int r58 = DataAutoDownloadActivity.m14175$$Nest$fgetcurrentType(r02);
            DataAutoDownloadActivity r03 = this.this$0;
            if (r58 != 1) goto L18;
            DownloadController r59 = DownloadController.getInstance(DataAutoDownloadActivity.access$400(r03));
            r59.currentWifiPreset = DataAutoDownloadActivity.m14174$$Nest$fgetcurrentPresetNum(this.this$0);
            goto L19
        L18:
            DownloadController r510 = DownloadController.getInstance(DataAutoDownloadActivity.access$500(r03));
            r510.currentRoamingPreset = DataAutoDownloadActivity.m14174$$Nest$fgetcurrentPresetNum(this.this$0);
            goto L19
        L5:
            DownloadController.Preset r04 = DataAutoDownloadActivity.m14182$$Nest$fgetmediumPreset(r1);
            DataAutoDownloadActivity r12 = this.this$0;
            if (r52 != r04) goto L8;
            DataAutoDownloadActivity.m14194$$Nest$fputcurrentPresetNum(r12, 1);
            goto L12
        L8:
            DownloadController.Preset r05 = DataAutoDownloadActivity.m14177$$Nest$fgethighPreset(r12);
            DataAutoDownloadActivity r13 = this.this$0;
            if (r52 != r05) goto L11;
            DataAutoDownloadActivity.m14194$$Nest$fputcurrentPresetNum(r13, 2);
            goto L12
        L11:
            DataAutoDownloadActivity.m14194$$Nest$fputcurrentPresetNum(r13, 3);
            goto L12
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return DataAutoDownloadActivity.m14185$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != DataAutoDownloadActivity.m14172$$Nest$fgetautoDownloadRow(this.this$0)) goto L7;
            return 0;
        L7:
            if (r2 != DataAutoDownloadActivity.m14192$$Nest$fgetusageSectionRow(this.this$0)) goto L11;
            return 1;
        L11:
            if (r2 != DataAutoDownloadActivity.m14190$$Nest$fgetusageHeaderRow(this.this$0)) goto L13;
            return 2;
        L13:
            if (r2 != DataAutoDownloadActivity.m14187$$Nest$fgettypeHeaderRow(this.this$0)) goto L16;
            return 2;
        L16:
            if (r2 != DataAutoDownloadActivity.m14191$$Nest$fgetusageProgressRow(this.this$0)) goto L20;
            return 3;
        L20:
            if (r2 != DataAutoDownloadActivity.m14183$$Nest$fgetphotosRow(this.this$0)) goto L22;
            return 4;
        L22:
            if (r2 != DataAutoDownloadActivity.m14193$$Nest$fgetvideosRow(this.this$0)) goto L24;
            return 4;
        L24:
            if (r2 != DataAutoDownloadActivity.m14176$$Nest$fgetfilesRow(this.this$0)) goto L26;
            return 4;
        L26:
            if (r2 == DataAutoDownloadActivity.m14186$$Nest$fgetstoriesRow(this.this$0)) goto L36;
            return 5;
        L36:
            return 4;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            int r22 = r2.getAdapterPosition();
            if (r22 != DataAutoDownloadActivity.m14183$$Nest$fgetphotosRow(this.this$0)) goto L5;
            return true;
        L5:
            if (r22 != DataAutoDownloadActivity.m14193$$Nest$fgetvideosRow(this.this$0)) goto L7;
            return true;
        L7:
            if (r22 != DataAutoDownloadActivity.m14176$$Nest$fgetfilesRow(this.this$0)) goto L9;
            return true;
        L9:
            if (r22 == DataAutoDownloadActivity.m14186$$Nest$fgetstoriesRow(this.this$0)) goto L17;
            return false;
        L17:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r18, int r19) {
            int r3 = r18.getItemViewType();
            if (r3 != 0) goto L5;
            TextCheckCell r1 = (TextCheckCell) r18.itemView;
            if (r19 != DataAutoDownloadActivity.m14172$$Nest$fgetautoDownloadRow(this.this$0)) goto L133;
            r1.setDrawCheckRipple(true);
            r1.setTextAndCheck(LocaleController.getString(R.string.AutoDownloadMedia), DataAutoDownloadActivity.m14188$$Nest$fgettypePreset(this.this$0).enabled, false);
            if (DataAutoDownloadActivity.m14188$$Nest$fgettypePreset(this.this$0).enabled == false) goto L121;
            int r2 = Theme.key_windowBackgroundChecked;
        L122:
            r1.setTag(Integer.valueOf(r2));
            if (DataAutoDownloadActivity.m14188$$Nest$fgettypePreset(this.this$0).enabled == false) goto L125;
            int r0 = Theme.key_windowBackgroundChecked;
        L126:
            r1.setBackgroundColor(Theme.getColor(r0));
            return;
        L125:
            r0 = Theme.key_windowBackgroundUnchecked;
            goto L126
        L121:
            r2 = Theme.key_windowBackgroundUnchecked;
            goto L122
        L133:
            return;
        L5:
            if (r3 != 2) goto L7;
            HeaderCell r12 = (HeaderCell) r18.itemView;
            if (r19 != DataAutoDownloadActivity.m14190$$Nest$fgetusageHeaderRow(this.this$0)) goto L113;
            r12.setText(LocaleController.getString(R.string.AutoDownloadDataUsage));
            return;
        L113:
            if (r19 != DataAutoDownloadActivity.m14187$$Nest$fgettypeHeaderRow(this.this$0)) goto L132;
            r12.setText(LocaleController.getString(R.string.AutoDownloadTypes));
            return;
        L132:
            return;
        L7:
            if (r3 == 3) goto L106;
            int r8 = -1;
            if (r3 != 4) goto L11;
            NotificationsCheckCell r10 = (NotificationsCheckCell) r18.itemView;
            r10.setDrawLine(true);
            if (r19 != DataAutoDownloadActivity.m14183$$Nest$fgetphotosRow(this.this$0)) goto L38;
            String r11 = LocaleController.getString(R.string.AutoDownloadPhotos);
            r8 = 1;
        L45:
            int r13 = DataAutoDownloadActivity.m14175$$Nest$fgetcurrentType(this.this$0);
            DataAutoDownloadActivity r32 = this.this$0;
            if (r13 != 0) goto L48;
            DownloadController.Preset r14 = DownloadController.getInstance(DataAutoDownloadActivity.access$000(r32)).getCurrentMobilePreset();
        L52:
            long r122 = r14.sizes[DownloadController.typeToIndex(r8)];
            StringBuilder r33 = new StringBuilder();
            if (r19 == DataAutoDownloadActivity.m14186$$Nest$fgetstoriesRow(this.this$0)) goto L55;
            int r142 = 0;
            int r15 = 0;
        L59:
            int[] r4 = r14.mask;
            if (r142 >= r4.length) goto L78;
            if ((r4[r142] & r8) == 0) goto L76;
            if (r33.length() == 0) goto L66;
            r33.append(", ");
        L66:
            if (r142 == 0) goto L74;
            if (r142 == 1) goto L73;
            if (r142 == 2) goto L72;
            if (r142 != 3) goto L75;
            r33.append(LocaleController.getString(R.string.AutoDownloadChannels));
        L75:
            r15 = r15 + 1;
            goto L76
        L72:
            r33.append(LocaleController.getString(R.string.AutoDownloadGroups));
            goto L75
        L73:
            r33.append(LocaleController.getString(R.string.AutoDownloadPm));
            goto L75
        L74:
            r33.append(LocaleController.getString(R.string.AutoDownloadContacts));
        L76:
            r142 = r142 + 1;
            goto L59
        L78:
            if (r15 != 4) goto L83;
            r33.setLength(0);
            if (r19 != DataAutoDownloadActivity.m14183$$Nest$fgetphotosRow(this.this$0)) goto L82;
            r33.append(LocaleController.getString(R.string.AutoDownloadOnAllChats));
        L85:
            StringBuilder r123 = r33;
        L92:
            if (DataAutoDownloadActivity.m14171$$Nest$fgetanimateChecked(this.this$0) == false) goto L97;
            if (r15 == 0) goto L95;
            boolean r16 = true;
        L96:
            r10.setChecked(r16);
            goto L97
        L95:
            r16 = false;
        L97:
            if (r15 == 0) goto L99;
            boolean r132 = true;
        L101:
            if (r19 == DataAutoDownloadActivity.m14186$$Nest$fgetstoriesRow(this.this$0)) goto L103;
            boolean r162 = true;
        L104:
            r10.setTextAndValueAndCheck(r11, r123, r132, 0, true, r162);
            return;
        L103:
            r162 = false;
            goto L104
        L99:
            r132 = false;
            goto L101
        L82:
            r33.append(LocaleController.formatString("AutoDownloadUpToOnAllChats", R.string.AutoDownloadUpToOnAllChats, new Object[]{AndroidUtilities.formatFileSize(r122)}));
            goto L85
        L83:
            if (r15 != 0) goto L87;
            r33.append(LocaleController.getString(R.string.AutoDownloadOff));
            goto L85
        L87:
            if (r19 != DataAutoDownloadActivity.m14183$$Nest$fgetphotosRow(this.this$0)) goto L90;
            StringBuilder r17 = new StringBuilder(LocaleController.formatString("AutoDownloadOnFor", R.string.AutoDownloadOnFor, new Object[]{r33.toString()}));
        L89:
            r123 = r17;
            goto L92
        L90:
            r17 = new StringBuilder(LocaleController.formatString("AutoDownloadOnUpToFor", R.string.AutoDownloadOnUpToFor, new Object[]{AndroidUtilities.formatFileSize(r122), r33.toString()}));
            goto L89
        L55:
            if (r14.preloadStories == false) goto L57;
            r123 = new StringBuilder(LocaleController.formatString("AutoDownloadOn", R.string.AutoDownloadOn, new Object[]{r33.toString()}));
            r15 = 1;
            goto L92
        L57:
            r123 = new StringBuilder(LocaleController.formatString("AutoDownloadOff", R.string.AutoDownloadOff, new Object[]{r33.toString()}));
            r15 = 0;
            goto L92
        L48:
            int r110 = DataAutoDownloadActivity.m14175$$Nest$fgetcurrentType(r32);
            DataAutoDownloadActivity r34 = this.this$0;
            if (r110 != 1) goto L51;
            r14 = DownloadController.getInstance(DataAutoDownloadActivity.access$100(r34)).getCurrentWiFiPreset();
            goto L52
        L51:
            r14 = DownloadController.getInstance(DataAutoDownloadActivity.access$200(r34)).getCurrentRoamingPreset();
            goto L52
        L38:
            if (r19 != DataAutoDownloadActivity.m14193$$Nest$fgetvideosRow(this.this$0)) goto L41;
            r11 = LocaleController.getString(R.string.AutoDownloadVideos);
            r8 = 4;
            goto L45
        L41:
            if (r19 != DataAutoDownloadActivity.m14186$$Nest$fgetstoriesRow(this.this$0)) goto L44;
            String r111 = LocaleController.getString(R.string.AutoDownloadStories);
            r10.setDrawLine(false);
        L43:
            r11 = r111;
            goto L45
        L44:
            r111 = LocaleController.getString(R.string.AutoDownloadFiles);
            r8 = 8;
            goto L43
        L11:
            if (r3 != 5) goto L134;
            TextInfoPrivacyCell r112 = (TextInfoPrivacyCell) r18.itemView;
            if (r19 != DataAutoDownloadActivity.m14189$$Nest$fgettypeSectionRow(this.this$0)) goto L18;
            r112.setText(LocaleController.getString(R.string.AutoDownloadAudioInfo));
            r112.setFixedSize(0);
            r112.setImportantForAccessibility(1);
            return;
        L18:
            if (r19 == DataAutoDownloadActivity.m14173$$Nest$fgetautoDownloadSectionRow(this.this$0)) goto L20;
            return;
        L20:
            if (DataAutoDownloadActivity.m14190$$Nest$fgetusageHeaderRow(this.this$0) == (-1)) goto L22;
            r112.setText(null);
            r112.setFixedSize(12);
            r112.setImportantForAccessibility(4);
            return;
        L22:
            if (DataAutoDownloadActivity.m14175$$Nest$fgetcurrentType(this.this$0) != 0) goto L25;
            r112.setText(LocaleController.getString(R.string.AutoDownloadOnMobileDataInfo));
        L30:
            r112.setImportantForAccessibility(1);
            return;
        L25:
            if (DataAutoDownloadActivity.m14175$$Nest$fgetcurrentType(this.this$0) != 1) goto L28;
            r112.setText(LocaleController.getString(R.string.AutoDownloadOnWiFiDataInfo));
            goto L30
        L28:
            if (DataAutoDownloadActivity.m14175$$Nest$fgetcurrentType(this.this$0) != 2) goto L30;
            r112.setText(LocaleController.getString(R.string.AutoDownloadOnRoamingDataInfo));
            goto L30
        L134:
            return;
        L106:
            DataAutoDownloadActivity.m14196$$Nest$mupdatePresetChoseView(this.this$0, (SlideChooseView) r18.itemView);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            if (r8 != 0) goto L4;
            TextCheckCell r0 = new TextCheckCell(this.mContext);
            r0.setColors(Theme.key_windowBackgroundCheckText, Theme.key_switchTrackBlue, Theme.key_switchTrackBlueChecked, Theme.key_switchTrackBlueThumb, Theme.key_switchTrackBlueThumbChecked);
            r0.setTypeface(AndroidUtilities.bold());
            r0.setHeight(56);
            View r72 = r0;
        L17:
            r72.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r72);
        L4:
            if (r8 != 1) goto L6;
            r72 = new ShadowSectionCell(this.mContext);
            goto L17
        L6:
            if (r8 != 2) goto L8;
            r72 = new HeaderCell(this.mContext);
            goto L17
        L8:
            if (r8 != 3) goto L10;
            SlideChooseView r73 = new SlideChooseView(this.mContext);
            r73.setCallback(new DataAutoDownloadActivity$ListAdapter$$ExternalSyntheticLambda0(this));
            r72 = r73;
            goto L17
        L10:
            if (r8 == 4) goto L12;
            r72 = new TextInfoPrivacyCell(this.mContext);
            goto L17
        L12:
            r72 = new NotificationsCheckCell(this.mContext);
            goto L17
        }
    }

    public static /* synthetic */ int $r8$lambda$ChB6XYg_q_avlxVIMtdo7gbjLdU(DownloadController.Preset r13, DownloadController.Preset r14) {
        int r0 = DownloadController.typeToIndex(4);
        int r1 = DownloadController.typeToIndex(8);
        int r3 = 0;
        boolean r4 = false;
        boolean r5 = false;
    L3:
        int[] r6 = r13.mask;
        if (r3 >= r6.length) goto L15;
        int r62 = r6[r3];
        r4 = r4;
        if ((r62 & 4) == 0) goto L8;
        r4 = true;
    L8:
        r5 = r5;
        if ((r62 & 8) == 0) goto L11;
        r5 = true;
    L11:
        if (r4 == false) goto L14;
        if (r5 == true) goto L15;
    L14:
        r3 = r3 + 1;
        r4 = r4;
        r5 = r5;
    L15:
        int r32 = 0;
        boolean r63 = false;
        boolean r7 = false;
    L16:
        int[] r9 = r14.mask;
        if (r32 >= r9.length) goto L28;
        int r92 = r9[r32];
        r63 = r63;
        if ((r92 & 4) == 0) goto L21;
        r63 = true;
    L21:
        r7 = r7;
        if ((r92 & 8) == 0) goto L24;
        r7 = true;
    L24:
        if (r63 == false) goto L27;
        if (r7 == true) goto L28;
    L27:
        r32 = r32 + 1;
        r63 = r63;
        r7 = r7;
    L28:
        long r93 = 0;
        if (r4 == false) goto L31;
        long r11 = r13.sizes[r0];
    L32:
        if (r5 == false) goto L34;
        long r42 = r13.sizes[r1];
    L35:
        long r112 = (r11 + r42) + (r13.preloadStories ? 1 : 0);
        if (r63 == false) goto L38;
        long r33 = r14.sizes[r0];
    L39:
        if (r7 == false) goto L41;
        r93 = r14.sizes[r1];
    L41:
        long r34 = (r33 + r93) + (r14.preloadStories ? 1 : 0);
        if (r112 <= r34) goto L44;
        return 1;
    L44:
        if (r112 >= r34) goto L47;
        return -1;
    L47:
        return 0;
    L38:
        r33 = 0;
        goto L39
    L34:
        r42 = 0;
        goto L35
    L31:
        r11 = 0;
        goto L32
    }

    public static /* synthetic */ void $r8$lambda$DVrpoLY_LuS88H0712WO4Bwwpfo(TextCheckCell[] r0, View r1) {
        r0[0].setChecked(!r0.isChecked());
    }

    public static /* synthetic */ void $r8$lambda$ErQZEt8xkc_ratcpYkjDxfMQcfI(DataAutoDownloadActivity r0, View r1, int r2, float r3, float r4) {
        r0.lambda$createView$4(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$MlGlJIIFlDaAZH6ZTTDzqbotR9c(DataAutoDownloadActivity r0, TextCheckBoxCell r1, TextCheckBoxCell[] r2, int r3, MaxFileSizeCell[] r4, TextCheckCell[] r5, AnimatorSet[] r6, View r7) {
        r0.lambda$createView$0(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$anH-i3UmdNM1UoTTZzL9ZkGheX0, reason: not valid java name */
    public static /* synthetic */ void m14169$r8$lambda$anHi3UmdNM1UoTTZzL9ZkGheX0(BottomSheet.Builder r0, View r1) {
        r0.getDismissRunnable().run();
    }

    /* renamed from: $r8$lambda$vkvSuztL4T8VDFn9-XnGXN-Vkbg, reason: not valid java name */
    public static /* synthetic */ void m14170$r8$lambda$vkvSuztL4T8VDFn9XnGXNVkbg(DataAutoDownloadActivity r0, TextCheckBoxCell[] r1, int r2, MaxFileSizeCell[] r3, int r4, TextCheckCell[] r5, int r6, String r7, String r8, BottomSheet.Builder r9, View r10, View r11) {
        r0.lambda$createView$3(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    /* renamed from: -$$Nest$fgetanimateChecked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14171$$Nest$fgetanimateChecked(DataAutoDownloadActivity r0) {
        return r0.animateChecked;
    }

    /* renamed from: -$$Nest$fgetautoDownloadRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14172$$Nest$fgetautoDownloadRow(DataAutoDownloadActivity r0) {
        return r0.autoDownloadRow;
    }

    /* renamed from: -$$Nest$fgetautoDownloadSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14173$$Nest$fgetautoDownloadSectionRow(DataAutoDownloadActivity r0) {
        return r0.autoDownloadSectionRow;
    }

    /* renamed from: -$$Nest$fgetcurrentPresetNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14174$$Nest$fgetcurrentPresetNum(DataAutoDownloadActivity r0) {
        return r0.currentPresetNum;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14175$$Nest$fgetcurrentType(DataAutoDownloadActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetfilesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14176$$Nest$fgetfilesRow(DataAutoDownloadActivity r0) {
        return r0.filesRow;
    }

    /* renamed from: -$$Nest$fgethighPreset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownloadController.Preset m14177$$Nest$fgethighPreset(DataAutoDownloadActivity r0) {
        return r0.highPreset;
    }

    /* renamed from: -$$Nest$fgetkey2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m14178$$Nest$fgetkey2(DataAutoDownloadActivity r0) {
        return r0.key2;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m14179$$Nest$fgetlistAdapter(DataAutoDownloadActivity r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m14180$$Nest$fgetlistView(DataAutoDownloadActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlowPreset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownloadController.Preset m14181$$Nest$fgetlowPreset(DataAutoDownloadActivity r0) {
        return r0.lowPreset;
    }

    /* renamed from: -$$Nest$fgetmediumPreset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownloadController.Preset m14182$$Nest$fgetmediumPreset(DataAutoDownloadActivity r0) {
        return r0.mediumPreset;
    }

    /* renamed from: -$$Nest$fgetphotosRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14183$$Nest$fgetphotosRow(DataAutoDownloadActivity r0) {
        return r0.photosRow;
    }

    /* renamed from: -$$Nest$fgetpresets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m14184$$Nest$fgetpresets(DataAutoDownloadActivity r0) {
        return r0.presets;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14185$$Nest$fgetrowCount(DataAutoDownloadActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetstoriesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14186$$Nest$fgetstoriesRow(DataAutoDownloadActivity r0) {
        return r0.storiesRow;
    }

    /* renamed from: -$$Nest$fgettypeHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14187$$Nest$fgettypeHeaderRow(DataAutoDownloadActivity r0) {
        return r0.typeHeaderRow;
    }

    /* renamed from: -$$Nest$fgettypePreset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownloadController.Preset m14188$$Nest$fgettypePreset(DataAutoDownloadActivity r0) {
        return r0.typePreset;
    }

    /* renamed from: -$$Nest$fgettypeSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14189$$Nest$fgettypeSectionRow(DataAutoDownloadActivity r0) {
        return r0.typeSectionRow;
    }

    /* renamed from: -$$Nest$fgetusageHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14190$$Nest$fgetusageHeaderRow(DataAutoDownloadActivity r0) {
        return r0.usageHeaderRow;
    }

    /* renamed from: -$$Nest$fgetusageProgressRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14191$$Nest$fgetusageProgressRow(DataAutoDownloadActivity r0) {
        return r0.usageProgressRow;
    }

    /* renamed from: -$$Nest$fgetusageSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14192$$Nest$fgetusageSectionRow(DataAutoDownloadActivity r0) {
        return r0.usageSectionRow;
    }

    /* renamed from: -$$Nest$fgetvideosRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14193$$Nest$fgetvideosRow(DataAutoDownloadActivity r0) {
        return r0.videosRow;
    }

    /* renamed from: -$$Nest$fputcurrentPresetNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14194$$Nest$fputcurrentPresetNum(DataAutoDownloadActivity r0, int r1) {
        r0.currentPresetNum = r1;
    }

    /* renamed from: -$$Nest$fputwereAnyChanges, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14195$$Nest$fputwereAnyChanges(DataAutoDownloadActivity r0, boolean r1) {
        r0.wereAnyChanges = r1;
    }

    /* renamed from: -$$Nest$mupdatePresetChoseView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14196$$Nest$mupdatePresetChoseView(DataAutoDownloadActivity r0, SlideChooseView r1) {
        r0.updatePresetChoseView(r1);
    }

    public DataAutoDownloadActivity(int r3) {
        this.presets = new ArrayList();
        this.selectedPreset = 1;
        this.currentType = r3;
        this.lowPreset = DownloadController.getInstance(this.currentAccount).lowPreset;
        this.mediumPreset = DownloadController.getInstance(this.currentAccount).mediumPreset;
        this.highPreset = DownloadController.getInstance(this.currentAccount).highPreset;
        int r32 = this.currentType;
        if (r32 != 0) goto L6;
        this.currentPresetNum = DownloadController.getInstance(this.currentAccount).currentMobilePreset;
        this.typePreset = DownloadController.getInstance(this.currentAccount).mobilePreset;
        this.defaultPreset = this.mediumPreset;
        this.key = "mobilePreset";
        this.key2 = "currentMobilePreset";
        return;
    L6:
        int r1 = this.currentAccount;
        if (r32 != 1) goto L10;
        this.currentPresetNum = DownloadController.getInstance(r1).currentWifiPreset;
        this.typePreset = DownloadController.getInstance(this.currentAccount).wifiPreset;
        this.defaultPreset = this.highPreset;
        this.key = "wifiPreset";
        this.key2 = "currentWifiPreset";
        return;
    L10:
        this.currentPresetNum = DownloadController.getInstance(r1).currentRoamingPreset;
        this.typePreset = DownloadController.getInstance(this.currentAccount).roamingPreset;
        this.defaultPreset = this.lowPreset;
        this.key = "roamingPreset";
        this.key2 = "currentRoamingPreset";
    }

    public static /* synthetic */ int access$000(DataAutoDownloadActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(DataAutoDownloadActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(DataAutoDownloadActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(DataAutoDownloadActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(DataAutoDownloadActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(DataAutoDownloadActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$600(DataAutoDownloadActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$700(DataAutoDownloadActivity r0) {
        return r0.currentAccount;
    }

    private void fillPresets() {
        this.presets.clear();
        this.presets.add(this.lowPreset);
        this.presets.add(this.mediumPreset);
        this.presets.add(this.highPreset);
        if (this.typePreset.equals(this.lowPreset) == false) goto L5;
    L9:
        Collections.sort(this.presets, new DataAutoDownloadActivity$$ExternalSyntheticLambda0());
        int r0 = this.currentPresetNum;
        if (r0 != 0) goto L12;
    L31:
        this.selectedPreset = this.presets.indexOf(this.lowPreset);
    L32:
        RecyclerListView r02 = this.listView;
        if (r02 == null) goto L42;
        RecyclerView.ViewHolder r03 = r02.findViewHolderForAdapterPosition(this.usageProgressRow);
        if (r03 == null) goto L40;
        View r04 = r03.itemView;
        if ((r04 instanceof SlideChooseView) == false) goto L40;
        updatePresetChoseView((SlideChooseView) r04);
        return;
    L40:
        this.listAdapter.notifyItemChanged(this.usageProgressRow);
        return;
    L42:
        return;
    L12:
        if (r0 == 3) goto L14;
    L16:
        int r05 = this.currentPresetNum;
        if (r05 == 1) goto L30;
        if (r05 == 3) goto L20;
    L22:
        int r06 = this.currentPresetNum;
        if (r06 == 2) goto L29;
        if (r06 == 3) goto L26;
    L28:
        this.selectedPreset = this.presets.indexOf(this.typePreset);
        goto L32
    L26:
        if (this.typePreset.equals(this.highPreset) == false) goto L28;
    L29:
        this.selectedPreset = this.presets.indexOf(this.highPreset);
        goto L32
    L20:
        if (this.typePreset.equals(this.mediumPreset) == false) goto L22;
    L30:
        this.selectedPreset = this.presets.indexOf(this.mediumPreset);
        goto L32
    L14:
        if (this.typePreset.equals(this.lowPreset) == false) goto L16;
    L5:
        if (this.typePreset.equals(this.mediumPreset) == true) goto L9;
        if (this.typePreset.equals(this.highPreset) == true) goto L9;
        this.presets.add(this.typePreset);
        goto L9
    }

    private /* synthetic */ void lambda$createView$0(TextCheckBoxCell r4, TextCheckBoxCell[] r5, int r6, MaxFileSizeCell[] r7, TextCheckCell[] r8, AnimatorSet[] r9, View r10) {
        if (r10.isEnabled() == false) goto L29;
        boolean r0 = true;
        r4.setChecked(!r4.isChecked());
        int r102 = 0;
    L7:
        if (r102 >= r5.length) goto L12;
        if (r5[r102].isChecked() == true) goto L14;
        r102 = r102 + 1;
    L14:
        if (r6 == this.videosRow) goto L16;
        return;
    L16:
        if (r7[0].isEnabled() == r0) goto L28;
        ArrayList<Animator> r52 = new ArrayList();
        r7[0].setEnabled(r0, r52);
        if (r7[0].getSize() <= 2097152) goto L20;
        r8[0].setEnabled(r0, r52);
    L20:
        AnimatorSet r62 = r9[0];
        if (r62 == null) goto L23;
        r62.cancel();
        r9[0] = null;
    L23:
        AnimatorSet r63 = new AnimatorSet();
        r9[0] = r63;
        r63.playTogether(r52);
        r9[0].addListener(new AnonymousClass2(this, r9));
        r9[0].setDuration(150);
        r9[0].start();
        return;
    L28:
        return;
    L12:
        r0 = false;
        goto L14
    }

    private /* synthetic */ void lambda$createView$3(TextCheckBoxCell[] r8, int r9, MaxFileSizeCell[] r10, int r11, TextCheckCell[] r12, int r13, String r14, String r15, BottomSheet.Builder r16, View r17, View r18) {
        int r0 = this.currentPresetNum;
        if (r0 == 3) goto L11;
        if (r0 != 0) goto L6;
        this.typePreset.set(this.lowPreset);
        goto L11
    L6:
        if (r0 != 1) goto L9;
        this.typePreset.set(this.mediumPreset);
        goto L11
    L9:
        if (r0 != 2) goto L11;
        this.typePreset.set(this.highPreset);
    L11:
        int r3 = 0;
    L13:
        if (r3 >= 4) goto L19;
        boolean r4 = r8[r3].isChecked();
        DownloadController.Preset r5 = this.typePreset;
        if (r4 == false) goto L17;
        int[] r42 = r5.mask;
        r42[r3] = r42[r3] | r9;
    L18:
        r3 = r3 + 1;
        goto L13
    L17:
        int[] r43 = r5.mask;
        r43[r3] = r43[r3] & (~r9);
        goto L18
    L19:
        MaxFileSizeCell r82 = r10[0];
        if (r82 == null) goto L22;
        r82.getSize();
        this.typePreset.sizes[r11] = (int) r10[0].getSize();
    L22:
        TextCheckCell r83 = r12[0];
        if (r83 == null) goto L28;
        int r92 = this.videosRow;
        DownloadController.Preset r102 = this.typePreset;
        if (r13 != r92) goto L27;
        r102.preloadVideo = r83.isChecked();
        goto L28
    L27:
        r102.preloadMusic = r83.isChecked();
    L28:
        SharedPreferences.Editor r84 = MessagesController.getMainSettings(this.currentAccount).edit();
        r84.putString(r14, this.typePreset.toString());
        this.currentPresetNum = 3;
        r84.putInt(r15, 3);
        int r93 = this.currentType;
        if (r93 != 0) goto L31;
        DownloadController.getInstance(this.currentAccount).currentMobilePreset = this.currentPresetNum;
    L35:
        r84.apply();
        r16.getDismissRunnable().run();
        RecyclerView.ViewHolder r85 = this.listView.findContainingViewHolder(r17);
        if (r85 == null) goto L38;
        this.animateChecked = true;
        this.listAdapter.onBindViewHolder(r85, r13);
        this.animateChecked = false;
    L38:
        DownloadController.getInstance(this.currentAccount).checkAutodownloadSettings();
        this.wereAnyChanges = true;
        fillPresets();
        return;
    L31:
        int r103 = this.currentAccount;
        if (r93 != 1) goto L34;
        DownloadController.getInstance(r103).currentWifiPreset = this.currentPresetNum;
        goto L35
    L34:
        DownloadController.getInstance(r103).currentRoamingPreset = this.currentPresetNum;
        goto L35
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    private /* synthetic */ void lambda$createView$4(android.view.View r26, int r27, float r28, float r29) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: IGET (r0v64 ?? I:int) = (r25v0 'this' org.telegram.ui.DataAutoDownloadActivity A[IMMUTABLE_TYPE, THIS]) A[DECLARE_VAR] org.telegram.ui.DataAutoDownloadActivity.currentPresetNum int in method: org.telegram.ui.DataAutoDownloadActivity.lambda$createView$4(android.view.View, int, float, float):void, file: classes6.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:355)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.ArgType.getPrimitiveType()" because "type" is null
            	at jadx.core.codegen.ClassGen.useType(ClassGen.java:554)
            	at jadx.core.codegen.InsnGen.useType(InsnGen.java:269)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:166)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:159)
            	at jadx.core.codegen.InsnGen.assignVar(InsnGen.java:152)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:299)
            	... 11 more
            */
        /*
            Method dump skipped, instructions count: 1367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.telegram.ui.DataAutoDownloadActivity.lambda$createView$4(android.view.View, int, float, float):void");
    }

    private void updatePresetChoseView(SlideChooseView r5) {
        String[] r0 = new String[this.presets.size()];
        int r1 = 0;
    L4:
        if (r1 >= this.presets.size()) goto L16;
        DownloadController.Preset r2 = this.presets.get(r1);
        if (r2 != this.lowPreset) goto L9;
        r0[r1] = LocaleController.getString(R.string.AutoDownloadLow);
    L15:
        r1 = r1 + 1;
        goto L4
    L9:
        if (r2 != this.mediumPreset) goto L12;
        r0[r1] = LocaleController.getString(R.string.AutoDownloadMedium);
        goto L15
    L12:
        if (r2 != this.highPreset) goto L14;
        r0[r1] = LocaleController.getString(R.string.AutoDownloadHigh);
        goto L15
    L14:
        r0[r1] = LocaleController.getString(R.string.AutoDownloadCustom);
        goto L15
    L16:
        r5.setOptions(this.selectedPreset, r0);
    }

    private void updateRows() {
        this.autoDownloadRow = 0;
        int r1 = 1 + 1;
        this.rowCount = r1;
        this.autoDownloadSectionRow = 1;
        if (this.typePreset.enabled == false) goto L6;
        this.usageHeaderRow = r1;
        this.usageProgressRow = r1 + 1;
        this.usageSectionRow = r1 + 2;
        this.typeHeaderRow = r1 + 3;
        this.photosRow = r1 + 4;
        this.videosRow = r1 + 5;
        this.filesRow = r1 + 6;
        int r2 = r1 + 8;
        this.storiesRow = r1 + 7;
        this.rowCount = r1 + 9;
        this.typeSectionRow = r2;
        return;
    L6:
        this.usageHeaderRow = -1;
        this.usageProgressRow = -1;
        this.usageSectionRow = -1;
        this.typeHeaderRow = -1;
        this.photosRow = -1;
        this.videosRow = -1;
        this.filesRow = -1;
        this.storiesRow = -1;
        this.typeSectionRow = -1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r6) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        int r0 = this.currentType;
        if (r0 != 0) goto L5;
        this.actionBar.setTitle(LocaleController.getString(R.string.AutoDownloadOnMobileData));
    L10:
        INavigationLayout r02 = this.parentLayout;
        if (r02 != null) goto L13;
    L15:
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.listAdapter = new ListAdapter(this, r6);
        FrameLayout r03 = new FrameLayout(r6);
        this.fragmentView = r03;
        r03.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        FrameLayout r04 = (FrameLayout) this.fragmentView;
        RecyclerListView r3 = new RecyclerListView(r6);
        this.listView = r3;
        r3.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setVerticalScrollBarEnabled(false);
        ((DefaultItemAnimator) this.listView.getItemAnimator()).setDelayAnimations(false);
        RecyclerListView r32 = this.listView;
        LinearLayoutManager r4 = new LinearLayoutManager(r6, 1, false);
        this.layoutManager = r4;
        r32.setLayoutManager(r4);
        r04.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new DataAutoDownloadActivity$$ExternalSyntheticLambda1(this));
        return this.fragmentView;
    L13:
        if (r02.isLayersLayout() == false) goto L15;
        this.actionBar.setOccupyStatusBar(false);
        goto L15
    L5:
        if (r0 != 1) goto L8;
        this.actionBar.setTitle(LocaleController.getString(R.string.AutoDownloadOnWiFiData));
        goto L10
    L8:
        if (r0 != 2) goto L10;
        this.actionBar.setTitle(LocaleController.getString(R.string.AutoDownloadOnRoamingData));
        goto L10
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{HeaderCell.class, NotificationsCheckCell.class, SlideChooseView.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{TextCheckCell.class}, null, null, null, Theme.key_windowBackgroundChecked));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{TextCheckCell.class}, null, null, null, Theme.key_windowBackgroundUnchecked));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundCheckText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrackBlue));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrackBlueChecked));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrackBlueThumb));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrackBlueThumbChecked));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrackBlueSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrackBlueSelectorChecked));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        int r22 = Theme.key_switchTrack;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"checkBox"}, null, null, null, r22));
        int r31 = Theme.key_switchTrackChecked;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{NotificationsCheckCell.class}, new String[]{"checkBox"}, null, null, null, r31));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{SlideChooseView.class}, null, null, null, r22));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{SlideChooseView.class}, null, null, null, r31));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{SlideChooseView.class}, null, null, null, Theme.key_windowBackgroundWhiteGrayText));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        fillPresets();
        updateRows();
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        if (this.wereAnyChanges == false) goto L6;
        DownloadController.getInstance(this.currentAccount).savePresetToServer(this.currentType);
        this.wereAnyChanges = false;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }
}
