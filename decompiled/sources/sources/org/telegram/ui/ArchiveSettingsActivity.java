package org.telegram.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class ArchiveSettingsActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private ListAdapter adapter;
    private boolean changed;
    private final ArrayList<ItemInner> items;
    private RecyclerListView listView;
    private final ArrayList<ItemInner> oldItems;
    private TLRPC.GlobalPrivacySettings settings;
    private int shiftDp;

    /* renamed from: org.telegram.ui.ArchiveSettingsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ArchiveSettingsActivity this$0;

        public AnonymousClass1(ArchiveSettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ArchiveSettingsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayoutManager {
        final /* synthetic */ ArchiveSettingsActivity this$0;

        public AnonymousClass2(ArchiveSettingsActivity r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    public static class ItemInner extends AdapterWithDiffUtils.Item {
        public int id;
        public CharSequence text;

        public ItemInner(int r2, int r3, CharSequence r4) {
            super(r2, false);
            this.id = r3;
            this.text = r4;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L15:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L15;
            ItemInner r52 = (ItemInner) r5;
            if (this.id != r52.id) goto L15;
            if (Objects.equals(this.text, r52.text) == false) goto L15;
            return true;
        }
    }

    public class ListAdapter extends AdapterWithDiffUtils {
        final /* synthetic */ ArchiveSettingsActivity this$0;

        private ListAdapter(ArchiveSettingsActivity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ListAdapter(ArchiveSettingsActivity r1, ArchiveSettingsActivityIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return ArchiveSettingsActivity.m5966$$Nest$fgetitems(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 >= 0) goto L4;
            return 0;
        L4:
            if (r2 < ArchiveSettingsActivity.m5966$$Nest$fgetitems(this.this$0).size()) goto L7;
            return 0;
        L7:
            return ((ItemInner) ArchiveSettingsActivity.m5966$$Nest$fgetitems(this.this$0).get(r2)).viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() != 2) goto L5;
            return false;
        L5:
            if (r2.getItemViewType() == 0) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r6, int r7) {
            if (r7 >= 0) goto L4;
            return;
        L4:
            if (r7 >= ArchiveSettingsActivity.m5966$$Nest$fgetitems(this.this$0).size()) goto L46;
            ItemInner r0 = (ItemInner) ArchiveSettingsActivity.m5966$$Nest$fgetitems(this.this$0).get(r7);
            int r72 = r7 + 1;
            int r3 = 0;
            if (r72 < ArchiveSettingsActivity.m5966$$Nest$fgetitems(this.this$0).size()) goto L9;
        L11:
            boolean r73 = false;
        L13:
            if (r6.getItemViewType() != 0) goto L17;
            ((HeaderCell) r6.itemView).setText(r0.text);
            return;
        L17:
            if (r6.getItemViewType() != 2) goto L25;
            TextInfoPrivacyCell r5 = (TextInfoPrivacyCell) r6.itemView;
            if (TextUtils.isEmpty(r0.text) == false) goto L22;
            r5.setFixedSize(12);
            r5.setText(null);
            return;
        L22:
            r5.setFixedSize(0);
            r5.setText(r0.text);
            return;
        L25:
            if (r6.getItemViewType() != 1) goto L44;
            TextCheckCell r62 = (TextCheckCell) r6.itemView;
            int r2 = r0.id;
            if (r2 != 1) goto L30;
            boolean r52 = ArchiveSettingsActivity.m5967$$Nest$fgetsettings(this.this$0).keep_archived_unmuted;
            r62.setCheckBoxIcon(0);
        L41:
            r62.setTextAndCheck(r0.text, r52, r73);
            return;
        L30:
            if (r2 != 4) goto L33;
            r52 = ArchiveSettingsActivity.m5967$$Nest$fgetsettings(this.this$0).keep_archived_folders;
            r62.setCheckBoxIcon(0);
            goto L41
        L33:
            if (r2 != 7) goto L45;
            boolean r1 = ArchiveSettingsActivity.m5967$$Nest$fgetsettings(this.this$0).archive_and_mute_new_noncontact_peers;
            if (this.this$0.getUserConfig().isPremium() == false) goto L37;
        L40:
            r62.setCheckBoxIcon(r3);
            r52 = r1;
            goto L41
        L37:
            if (this.this$0.getMessagesController().autoarchiveAvailable == true) goto L40;
            r3 = R.drawable.permission_locked;
            goto L40
        L45:
            return;
        L44:
            return;
        L9:
            if (((ItemInner) ArchiveSettingsActivity.m5966$$Nest$fgetitems(this.this$0).get(r72)).viewType != r0.viewType) goto L11;
            r73 = true;
            goto L13
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            if (r2 != 0) goto L5;
            View r12 = new HeaderCell(this.this$0.getContext());
        L9:
            return new RecyclerListView.Holder(r12);
        L5:
            if (r2 != 1) goto L7;
            r12 = new TextCheckCell(this.this$0.getContext());
            goto L9
        L7:
            r12 = new TextInfoPrivacyCell(this.this$0.getContext());
            goto L9
        }
    }

    /* renamed from: $r8$lambda$K9Z3_9vQKMEbeW05TFUkwTL-bWk, reason: not valid java name */
    public static /* synthetic */ void m5965$r8$lambda$K9Z3_9vQKMEbeW05TFUkwTLbWk(ArchiveSettingsActivity r0, View r1, int r2) {
        r0.lambda$createView$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$hxj99VMmMkMY_7d1fcryWz7xeC4(ArchiveSettingsActivity r0) {
        r0.lambda$createView$0();
    }

    public static /* synthetic */ void $r8$lambda$x7PpSkVpp4iYCjJtWj0kvH2q9_Q(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5966$$Nest$fgetitems(ArchiveSettingsActivity r0) {
        return r0.items;
    }

    /* renamed from: -$$Nest$fgetsettings, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.GlobalPrivacySettings m5967$$Nest$fgetsettings(ArchiveSettingsActivity r0) {
        return r0.settings;
    }

    public ArchiveSettingsActivity() {
        this.changed = false;
        this.shiftDp = -3;
        this.oldItems = new ArrayList();
        this.items = new ArrayList();
    }

    private /* synthetic */ void lambda$createView$0() {
        presentFragment(new PremiumPreviewFragment("settings"));
    }

    private /* synthetic */ void lambda$createView$1(View r6, int r7) {
        if (r7 >= 0) goto L4;
        return;
    L4:
        if (r7 >= this.items.size()) goto L28;
        int r72 = this.items.get(r7).id;
        if (r72 != 1) goto L11;
        TLRPC.GlobalPrivacySettings r73 = this.settings;
        boolean r1 = !r73.keep_archived_unmuted;
        r73.keep_archived_unmuted = r1;
        ((TextCheckCell) r6).setChecked(r1);
        this.changed = true;
        return;
    L11:
        if (r72 != 4) goto L15;
        TLRPC.GlobalPrivacySettings r74 = this.settings;
        boolean r12 = !r74.keep_archived_folders;
        r74.keep_archived_folders = r12;
        ((TextCheckCell) r6).setChecked(r12);
        this.changed = true;
        return;
    L15:
        if (r72 == 7) goto L17;
        return;
    L17:
        if (getUserConfig().isPremium() == false) goto L19;
    L24:
        TLRPC.GlobalPrivacySettings r75 = this.settings;
        boolean r13 = !r75.archive_and_mute_new_noncontact_peers;
        r75.archive_and_mute_new_noncontact_peers = r13;
        ((TextCheckCell) r6).setChecked(r13);
        this.changed = true;
        return;
    L19:
        if (getMessagesController().autoarchiveAvailable == true) goto L24;
        if (this.settings.archive_and_mute_new_noncontact_peers == true) goto L24;
        Bulletin.SimpleLayout r76 = new Bulletin.SimpleLayout(getContext(), getResourceProvider());
        r76.textView.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.UnlockPremium), Theme.key_undo_cancelColor, 0, new ArchiveSettingsActivity$$ExternalSyntheticLambda2(this)));
        r76.textView.setSingleLine(false);
        r76.textView.setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f));
        r76.imageView.setImageResource(R.drawable.msg_settings_premium);
        Bulletin.make(this, r76, 3500).show();
        int r77 = -this.shiftDp;
        this.shiftDp = r77;
        AndroidUtilities.shakeViewSpring(r6, r77);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        return;
    }

    private void updateItems(boolean r8) {
        this.oldItems.clear();
        this.oldItems.addAll(this.items);
        this.items.clear();
        this.items.add(new ItemInner(0, 0, LocaleController.getString("ArchiveSettingUnmutedFolders")));
        this.items.add(new ItemInner(1, 1, LocaleController.getString("ArchiveSettingUnmutedFoldersCheck")));
        this.items.add(new ItemInner(2, 2, LocaleController.getString("ArchiveSettingUnmutedFoldersInfo")));
        if (getMessagesController().getDialogFilters().size() <= 1) goto L5;
        this.items.add(new ItemInner(0, 3, LocaleController.getString("ArchiveSettingUnmutedChats")));
        this.items.add(new ItemInner(1, 4, LocaleController.getString("ArchiveSettingUnmutedChatsCheck")));
        this.items.add(new ItemInner(2, 5, LocaleController.getString("ArchiveSettingUnmutedChatsInfo")));
    L5:
        this.items.add(new ItemInner(0, 6, LocaleController.getString("NewChatsFromNonContacts")));
        this.items.add(new ItemInner(1, 7, LocaleController.getString("NewChatsFromNonContactsCheck")));
        this.items.add(new ItemInner(2, 8, LocaleController.getString("ArchiveAndMuteInfo")));
        ListAdapter r0 = this.adapter;
        if (r0 != null) goto L8;
        return;
    L8:
        if (r8 == false) goto L11;
        r0.setItems(this.oldItems, this.items);
        return;
    L11:
        r0.notifyDataSetChanged();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r6) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.ArchiveSettings));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        FrameLayout r0 = new FrameLayout(r6);
        this.fragmentView = r0;
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        RecyclerListView r2 = new RecyclerListView(r6);
        this.listView = r2;
        r2.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setLayoutManager(new AnonymousClass2(this, r6, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setLayoutAnimation(null);
        RecyclerListView r62 = this.listView;
        ListAdapter r22 = new ListAdapter(this, null);
        this.adapter = r22;
        r62.setAdapter(r22);
        DefaultItemAnimator r63 = new DefaultItemAnimator();
        r63.setDurations(350);
        r63.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r63.setDelayAnimations(false);
        r63.setSupportsChangeAnimations(false);
        this.listView.setItemAnimator(r63);
        r0.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setOnItemClickListener(new ArchiveSettingsActivity$$ExternalSyntheticLambda1(this));
        getContactsController().loadGlobalPrivacySetting();
        TLRPC.GlobalPrivacySettings r64 = getContactsController().getGlobalPrivacySettings();
        this.settings = r64;
        if (r64 != null) goto L5;
        this.settings = new TLRPC.TL_globalPrivacySettings();
    L5:
        updateItems(false);
        return this.fragmentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        if (r4 != NotificationCenter.privacyRulesUpdated) goto L30;
        TLRPC.GlobalPrivacySettings r42 = getContactsController().getGlobalPrivacySettings();
        this.settings = r42;
        if (r42 != null) goto L8;
        this.settings = new TLRPC.TL_globalPrivacySettings();
    L8:
        if (this.listView == null) goto L27;
        int r43 = 0;
    L11:
        if (r43 >= this.listView.getChildCount()) goto L27;
        View r0 = this.listView.getChildAt(r43);
        int r1 = this.listView.getChildAdapterPosition(r0);
        if (r1 < 0) goto L26;
        if (r1 >= this.items.size()) goto L26;
        int r12 = this.items.get(r1).id;
        if (r12 != 1) goto L21;
        ((TextCheckCell) r0).setChecked(this.settings.keep_archived_unmuted);
        goto L26
    L21:
        if (r12 != 4) goto L24;
        ((TextCheckCell) r0).setChecked(this.settings.keep_archived_folders);
        goto L26
    L24:
        if (r12 != 7) goto L26;
        ((TextCheckCell) r0).setChecked(this.settings.archive_and_mute_new_noncontact_peers);
    L26:
        r43 = r43 + 1;
    L27:
        this.changed = false;
        return;
    L30:
        if (r4 != NotificationCenter.dialogFiltersUpdated) goto L40;
        updateItems(true);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.privacyRulesUpdated);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.privacyRulesUpdated);
        super.onFragmentDestroy();
        if (this.changed == false) goto L6;
        TL_account.setGlobalPrivacySettings r0 = new TL_account.setGlobalPrivacySettings();
        r0.settings = this.settings;
        getConnectionsManager().sendRequest(r0, new ArchiveSettingsActivity$$ExternalSyntheticLambda0());
        this.changed = false;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }
}
