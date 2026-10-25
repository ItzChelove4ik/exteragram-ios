package org.telegram.ui.Components;

import android.view.View;
import android.view.ViewGroup;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.DefaultItemAnimator;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorBtnCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class AdminLogFilterAlert2 extends BottomSheetWithRecyclerListView {
    private final ButtonWithCounterView actionButton;
    private UniversalAdapter adapter;
    private final SelectorBtnCell buttonContainer;
    private ArrayList<TLRPC.ChannelParticipant> currentAdmins;
    private TLRPC.TL_channelAdminLogEventsFilter currentFilter;
    private AdminLogFilterAlertDelegate delegate;
    private boolean isMegagroup;
    private boolean sectionMembersExpanded;
    private boolean sectionMessagesExpanded;
    private boolean sectionSettingsExpanded;
    private LongSparseArray<TLRPC.User> selectedAdmins;

    public interface AdminLogFilterAlertDelegate {
        void didSelectRights(TLRPC.TL_channelAdminLogEventsFilter r1, LongSparseArray<TLRPC.User> r2);
    }

    public static /* synthetic */ void $r8$lambda$Lbg2Vnut8yTN1NLqjM7oAGM0z_4(AdminLogFilterAlert2 r0, View r1, int r2, float r3, float r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$NQF1lJepp3RgYN4PvegzwvvIAf0(AdminLogFilterAlert2 r0, int r1, View r2) {
        r0.lambda$getGroupClick$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$YZziXtFbX9DBALWSLAICKlbRqTk(AdminLogFilterAlert2 r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public AdminLogFilterAlert2(BaseFragment r10, TLRPC.TL_channelAdminLogEventsFilter r11, LongSparseArray<TLRPC.User> r12, boolean r13) {
        super(r10.getContext(), r10, false, false, false, true, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r10.getResourceProvider());
        this.currentFilter = new TLRPC.TL_channelAdminLogEventsFilter();
        this.sectionMembersExpanded = false;
        this.sectionSettingsExpanded = false;
        this.sectionMessagesExpanded = false;
        this.topPadding = 0.35f;
        fixNavigationBar();
        int r102 = Theme.key_dialogBackgroundGray;
        setBackgroundColor(Theme.getColor(r102, this.resourcesProvider));
        setSlidingActionBar();
        setShowHandle(true);
        TLRPC.TL_channelAdminLogEventsFilter r2 = this.currentFilter;
        if (r11 == null) goto L5;
        r2.join = r11.join;
        r2.leave = r11.leave;
        r2.edit_rank = r11.edit_rank;
        r2.invite = r11.invite;
        r2.ban = r11.ban;
        r2.unban = r11.unban;
        r2.kick = r11.kick;
        r2.unkick = r11.unkick;
        r2.promote = r11.promote;
        r2.demote = r11.demote;
        r2.info = r11.info;
        r2.settings = r11.settings;
        r2.pinned = r11.pinned;
        r2.edit = r11.edit;
        r2.delete = r11.delete;
        r2.group_call = r11.group_call;
        r2.invites = r11.invites;
    L6:
        if (r12 == null) goto L8;
        this.selectedAdmins = r12.clone();
    L8:
        this.isMegagroup = r13;
        this.adapter.update(false);
        DefaultItemAnimator r112 = new DefaultItemAnimator();
        r112.setSupportsChangeAnimations(false);
        r112.setDelayAnimations(false);
        r112.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r112.setDurations(350);
        this.recyclerListView.setItemAnimator(r112);
        this.recyclerListView.setOnItemClickListener(new AdminLogFilterAlert2$$ExternalSyntheticLambda0(this));
        SelectorBtnCell r113 = new SelectorBtnCell(getContext(), this.resourcesProvider, null);
        this.buttonContainer = r113;
        r113.setClickable(true);
        r113.setOrientation(1);
        r113.setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f));
        r113.setBackgroundColor(Theme.getColor(r102, this.resourcesProvider));
        ButtonWithCounterView r103 = new ButtonWithCounterView(getContext(), this.resourcesProvider);
        this.actionButton = r103;
        r103.setRound();
        r103.setText(LocaleController.getString(R.string.EventLogFilterApply), false);
        r103.setOnClickListener(new AdminLogFilterAlert2$$ExternalSyntheticLambda1(this));
        r113.addView(r103, LayoutHelper.createLinear(-1, 48, 87));
        ViewGroup r104 = this.containerView;
        int r4 = this.backgroundPaddingLeft;
        r104.addView(r113, LayoutHelper.createFrameMarginPx(-1, -2.0f, 87, r4, 0, r4, 0));
        RecyclerListView r105 = this.recyclerListView;
        int r114 = this.backgroundPaddingLeft;
        r105.setPadding(r114, 0, r114, AndroidUtilities.dp(68.0f));
        this.recyclerListView.setSections();
        return;
    L5:
        r2.join = true;
        r2.leave = true;
        r2.edit_rank = true;
        r2.invite = true;
        r2.ban = true;
        r2.unban = true;
        r2.kick = true;
        r2.unkick = true;
        r2.promote = true;
        r2.demote = true;
        r2.info = true;
        r2.settings = true;
        r2.pinned = true;
        r2.edit = true;
        r2.delete = true;
        r2.group_call = true;
        r2.invites = true;
        goto L6
    }

    private View.OnClickListener getGroupClick(int r2) {
        return new AdminLogFilterAlert2$$ExternalSyntheticLambda3(this, r2);
    }

    private String getGroupCount(int r7) {
        int r0 = 0;
        if (r7 == 0) goto L15;
        TLRPC.TL_channelAdminLogEventsFilter r6 = this.currentFilter;
        if (r7 == 1) goto L8;
        StringBuilder r72 = new StringBuilder();
        r72.append(((r6.delete ? 1 : 0) + (r6.edit ? 1 : 0)) + (r6.pinned ? 1 : 0));
        r72.append("/3");
        return r72.toString();
    L8:
        StringBuilder r73 = new StringBuilder();
        if (r6.info == false) goto L11;
    L12:
        r0 = 1;
    L13:
        r73.append((r0 + (r6.invites ? 1 : 0)) + (r6.group_call ? 1 : 0));
        r73.append("/3");
        return r73.toString();
    L11:
        if (r6.settings == false) goto L13;
    L15:
        StringBuilder r74 = new StringBuilder();
        TLRPC.TL_channelAdminLogEventsFilter r2 = this.currentFilter;
        if (r2.promote == false) goto L18;
    L21:
        int r3 = 1;
    L22:
        boolean r4 = this.isMegagroup;
        if (r4 == true) goto L25;
    L33:
        int r5 = 0;
    L34:
        int r32 = r3 + r5;
        if (r2.invite == false) goto L37;
    L40:
        int r52 = 1;
    L41:
        int r33 = (r32 + r52) + (r2.leave ? 1 : 0);
        if (r4 == true) goto L44;
    L46:
        r74.append(r33 + r0);
        r74.append("/");
        if (this.isMegagroup == false) goto L49;
        int r62 = 5;
    L50:
        r74.append(r62);
        return r74.toString();
    L49:
        r62 = 3;
        goto L50
    L44:
        if (r2.edit_rank == false) goto L46;
        r0 = 1;
        goto L46
    L37:
        if (r2.join == true) goto L40;
        r52 = 0;
        goto L41
    L25:
        if (r2.kick == false) goto L27;
    L32:
        r5 = 1;
        goto L34
    L27:
        if (r2.ban == true) goto L32;
        if (r2.unkick == true) goto L32;
        if (r2.unban == false) goto L33;
    L18:
        if (r2.demote == true) goto L21;
        r3 = 0;
        goto L22
    }

    private /* synthetic */ void lambda$getGroupClick$2(int r2, View r3) {
        if (r2 == 0) goto L10;
        if (r2 != 1) goto L6;
        this.sectionSettingsExpanded = !this.sectionSettingsExpanded;
    L11:
        this.adapter.update(true);
        applyScrolledPosition();
        return;
    L6:
        if (r2 != 2) goto L11;
        this.sectionMessagesExpanded = !this.sectionMessagesExpanded;
        goto L11
    L10:
        this.sectionMembersExpanded = !this.sectionMembersExpanded;
        goto L11
    }

    private /* synthetic */ void lambda$new$0(View r1, int r2, float r3, float r4) {
        onClick(this.adapter.getItem(r2 - 1), r1, r3);
    }

    private /* synthetic */ void lambda$new$1(View r3) {
        TLRPC.TL_channelAdminLogEventsFilter r32 = this.currentFilter;
        if (r32.join == true) goto L5;
    L37:
        LongSparseArray<TLRPC.User> r33 = this.selectedAdmins;
        if (r33 != null) goto L40;
    L44:
        this.delegate.didSelectRights(this.currentFilter, this.selectedAdmins);
        dismiss();
        return;
    L40:
        if (this.currentAdmins == null) goto L44;
        if (r33.size() < this.currentAdmins.size()) goto L44;
        this.selectedAdmins = null;
        goto L44
    L5:
        if (r32.leave == false) goto L37;
        if (r32.edit_rank == false) goto L37;
        if (r32.invite == false) goto L37;
        if (r32.ban == false) goto L37;
        if (r32.unban == false) goto L37;
        if (r32.kick == false) goto L37;
        if (r32.unkick == false) goto L37;
        if (r32.promote == false) goto L37;
        if (r32.demote == false) goto L37;
        if (r32.info == false) goto L37;
        if (r32.settings == false) goto L37;
        if (r32.pinned == false) goto L37;
        if (r32.edit == false) goto L37;
        if (r32.delete == false) goto L37;
        if (r32.group_call == false) goto L37;
        if (r32.invites == false) goto L37;
        this.currentFilter = null;
        goto L37
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView, org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return !this.recyclerListView.canScrollVertically(-1);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new AdminLogFilterAlert2$$ExternalSyntheticLambda2(this), this.resourcesProvider);
        this.adapter = r0;
        return r0;
    }

    public void fillItems(ArrayList<UItem> r7, UniversalAdapter r8) {
        if (this.currentFilter == null) goto L143;
        r7.add(UItem.asShadow(null));
        r7.add(UItem.asHeader(LocaleController.getString(R.string.EventLogFilterByActions)));
        if (this.isMegagroup == false) goto L8;
        int r0 = R.string.EventLogFilterSectionMembers;
    L9:
        UItem r02 = UItem.asRoundGroupCheckbox(2, LocaleController.getString(r0), getGroupCount(0));
        TLRPC.TL_channelAdminLogEventsFilter r2 = this.currentFilter;
        if (r2.promote == false) goto L12;
    L33:
        boolean r22 = true;
    L34:
        r7.add(r02.setChecked(r22).setCollapsed(!this.sectionMembersExpanded).setClickCallback(getGroupClick(0)));
        if (this.sectionMembersExpanded == false) goto L76;
        UItem r03 = UItem.asRoundCheckbox(3, LocaleController.getString(R.string.EventLogFilterSectionAdmin)).pad();
        TLRPC.TL_channelAdminLogEventsFilter r23 = this.currentFilter;
        if (r23.promote == false) goto L39;
    L42:
        boolean r24 = true;
    L43:
        r7.add(r03.setChecked(r24));
        if (this.isMegagroup == false) goto L58;
        UItem r04 = UItem.asRoundCheckbox(4, LocaleController.getString(R.string.EventLogFilterNewRestrictions)).pad();
        TLRPC.TL_channelAdminLogEventsFilter r25 = this.currentFilter;
        if (r25.kick == false) goto L48;
    L55:
        boolean r26 = true;
    L56:
        r7.add(r04.setChecked(r26));
        goto L58
    L48:
        if (r25.ban == true) goto L55;
        if (r25.unkick == true) goto L55;
        if (r25.unban == true) goto L55;
        r26 = false;
    L58:
        if (this.isMegagroup == false) goto L60;
        int r05 = R.string.EventLogFilterNewMembers;
    L61:
        UItem r06 = UItem.asRoundCheckbox(5, LocaleController.getString(r05)).pad();
        TLRPC.TL_channelAdminLogEventsFilter r27 = this.currentFilter;
        if (r27.invite == false) goto L64;
    L67:
        boolean r28 = true;
    L68:
        r7.add(r06.setChecked(r28));
        if (this.isMegagroup == false) goto L71;
        int r07 = R.string.EventLogFilterLeavingMembers2;
    L72:
        r7.add(UItem.asRoundCheckbox(6, LocaleController.getString(r07)).pad().setChecked(this.currentFilter.leave));
        if (this.isMegagroup == false) goto L76;
        r7.add(UItem.asRoundCheckbox(7, LocaleController.getString(R.string.EventLogFilterMembersRank)).pad().setChecked(this.currentFilter.edit_rank));
        goto L76
    L71:
        r07 = R.string.EventLogFilterLeavingSubscribers2;
        goto L72
    L64:
        if (r27.join == true) goto L67;
        r28 = false;
        goto L68
    L60:
        r05 = R.string.EventLogFilterNewSubscribers;
        goto L61
    L39:
        if (r23.demote == true) goto L42;
        r24 = false;
    L76:
        if (this.isMegagroup == false) goto L78;
        int r08 = R.string.EventLogFilterSectionGroupSettings;
    L79:
        UItem r09 = UItem.asRoundGroupCheckbox(8, LocaleController.getString(r08), getGroupCount(1));
        TLRPC.TL_channelAdminLogEventsFilter r29 = this.currentFilter;
        if (r29.info == false) goto L82;
    L89:
        boolean r210 = true;
    L90:
        r7.add(r09.setChecked(r210).setCollapsed(!this.sectionSettingsExpanded).setClickCallback(getGroupClick(1)));
        if (this.sectionSettingsExpanded == true) goto L93;
    L104:
        UItem r010 = UItem.asRoundGroupCheckbox(12, LocaleController.getString(R.string.EventLogFilterSectionMessages), getGroupCount(2));
        TLRPC.TL_channelAdminLogEventsFilter r211 = this.currentFilter;
        if (r211.delete == false) goto L107;
    L112:
        boolean r212 = true;
    L113:
        r7.add(r010.setChecked(r212).setCollapsed(!this.sectionMessagesExpanded).setClickCallback(getGroupClick(2)));
        if (this.sectionMessagesExpanded == false) goto L116;
        r7.add(UItem.asRoundCheckbox(13, LocaleController.getString(R.string.EventLogFilterDeletedMessages)).pad().setChecked(this.currentFilter.delete));
        r7.add(UItem.asRoundCheckbox(14, LocaleController.getString(R.string.EventLogFilterEditedMessages)).pad().setChecked(this.currentFilter.edit));
        r7.add(UItem.asRoundCheckbox(15, LocaleController.getString(R.string.EventLogFilterPinnedMessages)).pad().setChecked(this.currentFilter.pinned));
    L116:
        r7.add(UItem.asShadow(null));
        r7.add(UItem.asHeader(LocaleController.getString(R.string.EventLogFilterByAdmins)));
        UItem r82 = UItem.asRoundCheckbox(16, LocaleController.getString(R.string.EventLogFilterByAdminsAll));
        LongSparseArray<TLRPC.User> r011 = this.selectedAdmins;
        if (r011 != null) goto L119;
        int r012 = 0;
    L120:
        ArrayList<TLRPC.ChannelParticipant> r213 = this.currentAdmins;
        if (r213 != null) goto L123;
        int r214 = 0;
    L124:
        if (r012 < r214) goto L126;
        boolean r013 = true;
    L127:
        r7.add(r82.setChecked(r013));
        if (this.currentAdmins == null) goto L139;
        int r83 = 0;
    L131:
        if (r83 >= this.currentAdmins.size()) goto L144;
        long r215 = DialogObject.getPeerDialogId(this.currentAdmins.get(r83).peer);
        UItem r014 = UItem.asUserCheckbox((-1) - r83, MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r215))).pad();
        LongSparseArray<TLRPC.User> r4 = this.selectedAdmins;
        if (r4 != null) goto L135;
    L137:
        boolean r216 = false;
    L138:
        r7.add(r014.setChecked(r216));
        r83 = r83 + 1;
        goto L131
    L135:
        if (r4.containsKey(r215) == false) goto L137;
        r216 = true;
        goto L138
    L144:
        return;
    L139:
        return;
    L126:
        r013 = false;
        goto L127
    L123:
        r214 = r213.size();
        goto L124
    L119:
        r012 = r011.size();
        goto L120
    L107:
        if (r211.edit == true) goto L112;
        if (r211.pinned == true) goto L112;
        r212 = false;
        goto L113
    L93:
        if (this.isMegagroup == false) goto L95;
        int r015 = R.string.EventLogFilterGroupInfo;
    L96:
        UItem r016 = UItem.asRoundCheckbox(9, LocaleController.getString(r015)).pad();
        TLRPC.TL_channelAdminLogEventsFilter r217 = this.currentFilter;
        if (r217.info == false) goto L99;
    L102:
        boolean r218 = true;
    L103:
        r7.add(r016.setChecked(r218));
        r7.add(UItem.asRoundCheckbox(10, LocaleController.getString(R.string.EventLogFilterInvites)).pad().setChecked(this.currentFilter.invites));
        r7.add(UItem.asRoundCheckbox(11, LocaleController.getString(R.string.EventLogFilterCalls)).pad().setChecked(this.currentFilter.group_call));
        goto L104
    L99:
        if (r217.settings == true) goto L102;
        r218 = false;
        goto L103
    L95:
        r015 = R.string.EventLogFilterChannelInfo;
        goto L96
    L82:
        if (r29.settings == true) goto L89;
        if (r29.invites == true) goto L89;
        if (r29.group_call == true) goto L89;
        r210 = false;
        goto L90
    L78:
        r08 = R.string.EventLogFilterSectionChannelSettings;
        goto L79
    L12:
        if (r2.demote == true) goto L33;
        if (this.isMegagroup == false) goto L24;
        if (r2.kick == true) goto L33;
        if (r2.ban == true) goto L33;
        if (r2.unkick == true) goto L33;
        if (r2.unban == true) goto L33;
    L24:
        if (r2.invite == true) goto L33;
        if (r2.join == true) goto L33;
        if (r2.leave == true) goto L33;
        if (r2.edit_rank == true) goto L33;
        r22 = false;
        goto L34
    L8:
        r0 = R.string.EventLogFilterSectionSubscribers;
        goto L9
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.EventLog);
    }

    public void onClick(UItem r9, View r10, float r11) {
        if (r9 == null) goto L76;
        int r0 = r9.viewType;
        if (r0 != 41) goto L7;
    L8:
        if (r0 == 41) goto L10;
    L17:
        boolean r112 = false;
    L18:
        CheckBoxCell r02 = (CheckBoxCell) r10;
        if (r112 == true) goto L22;
        r02.setChecked(!r02.isChecked(), true);
    L22:
        switch(r9.id) {
            case 2: goto L51;
            case 3: goto L50;
            case 4: goto L49;
            case 5: goto L48;
            case 6: goto L47;
            case 7: goto L46;
            case 8: goto L43;
            case 9: goto L42;
            case 10: goto L41;
            case 11: goto L40;
            case 12: goto L37;
            case 13: goto L36;
            case 14: goto L35;
            case 15: goto L34;
            case 16: goto L25;
            default: goto L56;
        };
    L34:
        this.currentFilter.pinned = r02.isChecked();
        goto L56
    L35:
        this.currentFilter.edit = r02.isChecked();
        goto L56
    L36:
        this.currentFilter.delete = r02.isChecked();
        goto L56
    L37:
        if (r112 == false) goto L39;
        this.sectionMessagesExpanded = !this.sectionMessagesExpanded;
        goto L56
    L39:
        TLRPC.TL_channelAdminLogEventsFilter r113 = this.currentFilter;
        boolean r03 = r02.isChecked();
        r113.pinned = r03;
        r113.edit = r03;
        r113.delete = r03;
        goto L56
    L40:
        this.currentFilter.group_call = r02.isChecked();
        goto L56
    L41:
        this.currentFilter.invites = r02.isChecked();
        goto L56
    L42:
        TLRPC.TL_channelAdminLogEventsFilter r114 = this.currentFilter;
        boolean r04 = r02.isChecked();
        r114.settings = r04;
        r114.info = r04;
        goto L56
    L43:
        if (r112 == false) goto L45;
        this.sectionSettingsExpanded = !this.sectionSettingsExpanded;
        goto L56
    L45:
        TLRPC.TL_channelAdminLogEventsFilter r115 = this.currentFilter;
        boolean r05 = r02.isChecked();
        r115.group_call = r05;
        r115.invites = r05;
        r115.settings = r05;
        r115.info = r05;
        goto L56
    L46:
        this.currentFilter.edit_rank = r02.isChecked();
        goto L56
    L47:
        this.currentFilter.leave = r02.isChecked();
        goto L56
    L48:
        TLRPC.TL_channelAdminLogEventsFilter r116 = this.currentFilter;
        boolean r06 = r02.isChecked();
        r116.join = r06;
        r116.invite = r06;
        goto L56
    L49:
        TLRPC.TL_channelAdminLogEventsFilter r117 = this.currentFilter;
        boolean r07 = r02.isChecked();
        r117.unban = r07;
        r117.unkick = r07;
        r117.ban = r07;
        r117.kick = r07;
        goto L56
    L50:
        TLRPC.TL_channelAdminLogEventsFilter r118 = this.currentFilter;
        boolean r08 = r02.isChecked();
        r118.demote = r08;
        r118.promote = r08;
        goto L56
    L51:
        if (r112 == false) goto L53;
        this.sectionMembersExpanded = !this.sectionMembersExpanded;
        goto L56
    L53:
        TLRPC.TL_channelAdminLogEventsFilter r119 = this.currentFilter;
        boolean r1 = r02.isChecked();
        r119.edit_rank = r1;
        r119.leave = r1;
        r119.join = r1;
        r119.invite = r1;
        r119.demote = r1;
        r119.promote = r1;
        if (this.isMegagroup == false) goto L56;
        TLRPC.TL_channelAdminLogEventsFilter r1110 = this.currentFilter;
        boolean r09 = r02.isChecked();
        r1110.unban = r09;
        r1110.unkick = r09;
        r1110.ban = r09;
        r1110.kick = r09;
    L56:
        this.adapter.update(true);
    L57:
        int r92 = r9.id;
        if (r92 >= 0) goto L74;
        CheckBoxCell r102 = (CheckBoxCell) r10;
        int r93 = (-r92) - 1;
        if (r93 >= 0) goto L62;
        return;
    L62:
        if (r93 >= this.currentAdmins.size()) goto L77;
        long r010 = DialogObject.getPeerDialogId(this.currentAdmins.get(r93).peer);
        TLRPC.User r94 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r010));
        if (this.selectedAdmins != null) goto L67;
        this.selectedAdmins = new LongSparseArray();
    L67:
        boolean r1111 = this.selectedAdmins.containsKey(r010);
        LongSparseArray<TLRPC.User> r4 = this.selectedAdmins;
        if (r1111 == false) goto L70;
        r4.remove(r010);
        r102.setChecked(false, true);
    L71:
        this.adapter.update(true);
        return;
    L70:
        r4.put(r010, r94);
        r102.setChecked(true, true);
        goto L71
    L77:
        return;
    L74:
        return;
    L25:
        if (this.selectedAdmins != null) goto L27;
        this.selectedAdmins = new LongSparseArray();
    L27:
        this.selectedAdmins.clear();
        if (r02.isChecked() == false) goto L56;
        ArrayList<TLRPC.ChannelParticipant> r1112 = this.currentAdmins;
        if (r1112 == null) goto L56;
        int r011 = r1112.size();
        int r12 = 0;
    L32:
        if (r12 >= r011) goto L56;
        TLRPC.ChannelParticipant r42 = r1112.get(r12);
        r12 = r12 + 1;
        long r43 = DialogObject.getPeerDialogId(r42.peer);
        this.selectedAdmins.put(r43, MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r43)));
        goto L32
    L10:
        if (LocaleController.isRTL == false) goto L15;
        if (r11 >= (r10.getMeasuredWidth() - AndroidUtilities.dp(60.0f))) goto L17;
    L16:
        r112 = true;
        goto L18
    L15:
        if (r11 <= AndroidUtilities.dp(60.0f)) goto L17;
    L7:
        if (r0 != 35) goto L57;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onSmoothContainerViewLayout(float r1) {
        super.onSmoothContainerViewLayout(r1);
        this.buttonContainer.setTranslationY(-r1);
    }

    public void setAdminLogFilterAlertDelegate(AdminLogFilterAlertDelegate r1) {
        this.delegate = r1;
    }

    public void setCurrentAdmins(ArrayList<TLRPC.ChannelParticipant> r7) {
        this.currentAdmins = r7;
        if (r7 != null) goto L5;
    L9:
        UniversalAdapter r6 = this.adapter;
        if (r6 == null) goto L14;
        r6.update(true);
        return;
    L14:
        return;
    L5:
        if (this.selectedAdmins != null) goto L9;
        this.selectedAdmins = new LongSparseArray();
        ArrayList<TLRPC.ChannelParticipant> r72 = this.currentAdmins;
        int r0 = r72.size();
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L9;
        TLRPC.ChannelParticipant r2 = r72.get(r1);
        r1 = r1 + 1;
        long r22 = DialogObject.getPeerDialogId(r2.peer);
        TLRPC.User r4 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r22));
        this.selectedAdmins.put(r22, r4);
        goto L7
    }
}
