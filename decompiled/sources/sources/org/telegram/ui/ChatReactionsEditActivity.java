package org.telegram.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.AvailableReactionCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.RadioCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SimpleThemeDescription;

/* loaded from: classes6.dex */
public class ChatReactionsEditActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private RadioCell allReactions;
    private ArrayList<TLRPC.TL_availableReaction> availableReactions;
    private long chatId;
    private List<String> chatReactions;
    private LinearLayout contentView;
    LinearLayout contorlsLayout;
    private TLRPC.Chat currentChat;
    private RadioCell disableReactions;
    private TextCheckCell enableReactionsCell;
    private TLRPC.ChatFull info;
    boolean isChannel;
    private RecyclerView.Adapter listAdapter;
    private RecyclerListView listView;
    ArrayList<RadioCell> radioCells;
    int selectedType;
    private RadioCell someReactions;
    int startFromType;

    /* renamed from: org.telegram.ui.ChatReactionsEditActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChatReactionsEditActivity this$0;

        public AnonymousClass1(ChatReactionsEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatReactionsEditActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ ChatReactionsEditActivity this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass2(ChatReactionsEditActivity r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            ChatReactionsEditActivity r0 = this.this$0;
            int r2 = 0;
            if (r0.isChannel == false) goto L10;
            if (ChatReactionsEditActivity.m8390$$Nest$fgetchatReactions(r0).isEmpty() == true) goto L8;
            r2 = ChatReactionsEditActivity.m8389$$Nest$fgetavailableReactions(this.this$0).size() + 1;
        L8:
            return r2 + 1;
        L10:
            if (ChatReactionsEditActivity.m8390$$Nest$fgetchatReactions(r0).isEmpty() == true) goto L13;
            r2 = ChatReactionsEditActivity.m8389$$Nest$fgetavailableReactions(this.this$0).size() + 1;
        L13:
            return r2 + 3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r4) {
            if (this.this$0.isChannel == false) goto L10;
            if (r4 != 0) goto L6;
            return 0;
        L6:
            if (r4 != 1) goto L8;
            return 1;
        L8:
            return 2;
        L10:
            if (r4 != 1) goto L12;
            return 3;
        L12:
            if (r4 != 2) goto L14;
            return 0;
        L14:
            if (r4 == 0) goto L18;
            if (r4 == 3) goto L18;
            return 2;
        L18:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 2) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
            int r0 = getItemViewType(r5);
            int r2 = 2;
            if (r0 == 0) goto L19;
            if (r0 == 1) goto L13;
            if (r0 != 2) goto L38;
            AvailableReactionCell r42 = (AvailableReactionCell) r4.itemView;
            ArrayList r02 = ChatReactionsEditActivity.m8389$$Nest$fgetavailableReactions(this.this$0);
            if (this.this$0.isChannel == true) goto L11;
            r2 = 4;
        L11:
            TLRPC.TL_availableReaction r52 = (TLRPC.TL_availableReaction) r02.get(r5 - r2);
            r42.bind(r52, ChatReactionsEditActivity.m8390$$Nest$fgetchatReactions(this.this$0).contains(r52.reaction), ChatReactionsEditActivity.access$000(this.this$0));
            return;
        L38:
            return;
        L13:
            HeaderCell r3 = (HeaderCell) r4.itemView;
            if (r5 != 0) goto L16;
            r3.setText(LocaleController.getString(R.string.AvailableReactions));
        L17:
            r3.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            return;
        L16:
            r3.setText(LocaleController.getString(R.string.OnlyAllowThisReactions));
            goto L17
        L19:
            TextInfoPrivacyCell r43 = (TextInfoPrivacyCell) r4.itemView;
            r43.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
            ChatReactionsEditActivity r32 = this.this$0;
            if (r32.isChannel == true) goto L22;
            int r33 = r32.selectedType;
            if (r33 != 1) goto L31;
            r43.setText(LocaleController.getString(R.string.EnableSomeReactionsInfo));
            return;
        L31:
            if (r33 != 0) goto L34;
            r43.setText(LocaleController.getString(R.string.EnableAllReactionsInfo));
            return;
        L34:
            if (r33 != 2) goto L37;
            r43.setText(LocaleController.getString(R.string.DisableReactionsInfo));
            return;
        L37:
            return;
        L22:
            if (ChatObject.isChannelAndNotMegaGroup(ChatReactionsEditActivity.m8391$$Nest$fgetcurrentChat(r32)) == false) goto L24;
            String r34 = LocaleController.getString(R.string.EnableReactionsChannelInfo);
        L25:
            r43.setText(r34);
            return;
        L24:
            r34 = LocaleController.getString(R.string.EnableReactionsGroupInfo);
            goto L25
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 == 0) goto L17;
            if (r3 == 1) goto L15;
            if (r3 != 3) goto L8;
            FrameLayout r22 = new FrameLayout(this.val$context);
            if (this.this$0.contorlsLayout.getParent() == null) goto L12;
            ((ViewGroup) this.this$0.contorlsLayout.getParent()).removeView(this.this$0.contorlsLayout);
        L12:
            r22.addView(this.this$0.contorlsLayout);
            r22.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r22);
        L8:
            return new RecyclerListView.Holder(new AvailableReactionCell(this.val$context, false, false));
        L15:
            return new RecyclerListView.Holder(new HeaderCell(this.val$context, 23));
        L17:
            return new RecyclerListView.Holder(new TextInfoPrivacyCell(this.val$context));
        }
    }

    /* renamed from: $r8$lambda$1DqyZ_rD7-zXjpTfqDcFEiD5R5A, reason: not valid java name */
    public static /* synthetic */ void m8384$r8$lambda$1DqyZ_rD7zXjpTfqDcFEiD5R5A(ChatReactionsEditActivity r0) {
        r0.lambda$createView$3();
    }

    public static /* synthetic */ void $r8$lambda$6t65hdI0LajG17kINj4Zt3UJXs0(ChatReactionsEditActivity r0) {
        r0.lambda$createView$1();
    }

    /* renamed from: $r8$lambda$E-S0LQOc0sMTzZZbmesiC5CGLVs, reason: not valid java name */
    public static /* synthetic */ void m8385$r8$lambda$ES0LQOc0sMTzZZbmesiC5CGLVs(ChatReactionsEditActivity r0) {
        r0.updateColors();
    }

    /* renamed from: $r8$lambda$FuH1V8BqrchZfXSH688XZe_-HiM, reason: not valid java name */
    public static /* synthetic */ void m8386$r8$lambda$FuH1V8BqrchZfXSH688XZe_HiM(ChatReactionsEditActivity r0) {
        r0.lambda$createView$5();
    }

    /* renamed from: $r8$lambda$HA-Sc_FgNVPt7514mmgSRHvOdpw, reason: not valid java name */
    public static /* synthetic */ void m8387$r8$lambda$HASc_FgNVPt7514mmgSRHvOdpw(ChatReactionsEditActivity r0, View r1, int r2) {
        r0.lambda$createView$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$IZPcp0PTJzrMEgwakb1bnYGpBVM(ChatReactionsEditActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$YtUAcFdAB3UHfkVzbyUFZlcycWQ(ChatReactionsEditActivity r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$nosgPgjPX7isMw1nLiwlvq3Kk70(ChatReactionsEditActivity r0, View r1) {
        r0.lambda$createView$6(r1);
    }

    /* renamed from: $r8$lambda$z_Cj7r1RI4TFlO3hCccIci-bHPc, reason: not valid java name */
    public static /* synthetic */ void m8388$r8$lambda$z_Cj7r1RI4TFlO3hCccIcibHPc(ChatReactionsEditActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    /* renamed from: -$$Nest$fgetavailableReactions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m8389$$Nest$fgetavailableReactions(ChatReactionsEditActivity r0) {
        return r0.availableReactions;
    }

    /* renamed from: -$$Nest$fgetchatReactions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m8390$$Nest$fgetchatReactions(ChatReactionsEditActivity r0) {
        return r0.chatReactions;
    }

    /* renamed from: -$$Nest$fgetcurrentChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Chat m8391$$Nest$fgetcurrentChat(ChatReactionsEditActivity r0) {
        return r0.currentChat;
    }

    public ChatReactionsEditActivity(Bundle r4) {
        super(r4);
        this.chatReactions = new ArrayList();
        this.availableReactions = new ArrayList();
        this.selectedType = -1;
        this.radioCells = new ArrayList();
        this.chatId = r4.getLong("chat_id", 0);
    }

    public static /* synthetic */ int access$000(ChatReactionsEditActivity r0) {
        return r0.currentAccount;
    }

    private /* synthetic */ void lambda$createView$0(View r2) {
        if (this.enableReactionsCell.isChecked() == false) goto L5;
        int r22 = 2;
    L6:
        setCheckedEnableReactionCell(r22, true);
        return;
    L5:
        r22 = 1;
        goto L6
    }

    private /* synthetic */ void lambda$createView$1() {
        setCheckedEnableReactionCell(0, true);
    }

    private /* synthetic */ void lambda$createView$2(View r1) {
        AndroidUtilities.runOnUIThread(new ChatReactionsEditActivity$$ExternalSyntheticLambda6(this));
    }

    private /* synthetic */ void lambda$createView$3() {
        setCheckedEnableReactionCell(1, true);
    }

    private /* synthetic */ void lambda$createView$4(View r1) {
        AndroidUtilities.runOnUIThread(new ChatReactionsEditActivity$$ExternalSyntheticLambda7(this));
    }

    private /* synthetic */ void lambda$createView$5() {
        setCheckedEnableReactionCell(2, true);
    }

    private /* synthetic */ void lambda$createView$6(View r1) {
        AndroidUtilities.runOnUIThread(new ChatReactionsEditActivity$$ExternalSyntheticLambda8(this));
    }

    private /* synthetic */ void lambda$createView$7(View r7, int r8) {
        boolean r0 = this.isChannel;
        int r1 = 3;
        if (r0 == false) goto L5;
        int r3 = 1;
    L6:
        if (r8 > r3) goto L8;
        return;
    L8:
        AvailableReactionCell r72 = (AvailableReactionCell) r7;
        ArrayList<TLRPC.TL_availableReaction> r32 = this.availableReactions;
        if (r0 == false) goto L11;
        int r02 = 2;
    L12:
        TLRPC.TL_availableReaction r82 = r32.get(r8 - r02);
        boolean r03 = this.chatReactions.contains(r82.reaction);
        boolean r33 = !r03;
        List<String> r5 = this.chatReactions;
        if (r03 == true) goto L15;
        r5.add(r82.reaction);
    L24:
        r72.setChecked(r33, true);
        return;
    L15:
        r5.remove(r82.reaction);
        if (this.chatReactions.isEmpty() == false) goto L24;
        RecyclerView.Adapter r83 = this.listAdapter;
        if (r83 != null) goto L20;
    L23:
        setCheckedEnableReactionCell(2, true);
        goto L24
    L20:
        if (this.isChannel == false) goto L22;
        r1 = 1;
    L22:
        r83.notifyItemRangeRemoved(r1, this.availableReactions.size() + 1);
        goto L23
    L11:
        r02 = 4;
        goto L12
    L5:
        r3 = 3;
        goto L6
    }

    private void setCheckedEnableReactionCell(int r10, boolean r11) {
        if (this.selectedType == r10) goto L81;
        TextCheckCell r0 = this.enableReactionsCell;
        if (r0 == null) goto L20;
        if (r10 == 1) goto L11;
        if (r10 == 0) goto L11;
        boolean r3 = false;
    L12:
        r0.setChecked(r3);
        if (r3 == false) goto L15;
        int r02 = Theme.key_windowBackgroundChecked;
    L16:
        int r03 = Theme.getColor(r02);
        TextCheckCell r4 = this.enableReactionsCell;
        if (r3 == false) goto L19;
        r4.setBackgroundColorAnimated(r3, r03);
        goto L20
    L19:
        r4.setBackgroundColorAnimatedReverse(r03);
        goto L20
    L15:
        r02 = Theme.key_windowBackgroundUnchecked;
    L11:
        r3 = true;
    L20:
        this.selectedType = r10;
        int r04 = 0;
    L22:
        if (r04 >= this.radioCells.size()) goto L28;
        RadioCell r32 = this.radioCells.get(r04);
        if (r10 != r04) goto L26;
        boolean r42 = true;
    L27:
        r32.setChecked(r42, r11);
        r04 = r04 + 1;
        goto L22
    L26:
        r42 = false;
        goto L27
    L28:
        int r05 = 3;
        if (r10 != 1) goto L51;
        if (r11 == false) goto L43;
        this.chatReactions.clear();
        ArrayList<TLRPC.TL_availableReaction> r102 = this.availableReactions;
        int r43 = r102.size();
        int r5 = 0;
    L32:
        if (r5 >= r43) goto L39;
        TLRPC.TL_availableReaction r6 = r102.get(r5);
        r5 = r5 + 1;
        TLRPC.TL_availableReaction r62 = r6;
        if (r62.reaction.equals("👍") == true) goto L37;
        if (r62.reaction.equals("👎") == false) goto L32;
    L37:
        this.chatReactions.add(r62.reaction);
        goto L32
    L39:
        if (this.chatReactions.isEmpty() == false) goto L43;
        if (this.availableReactions.size() < 2) goto L43;
        this.chatReactions.add(this.availableReactions.get(0).reaction);
        this.chatReactions.add(this.availableReactions.get(1).reaction);
    L43:
        RecyclerView.Adapter r103 = this.listAdapter;
        if (r103 == null) goto L60;
        if (r11 == false) goto L60;
        if (this.isChannel == false) goto L49;
        r05 = 1;
    L49:
        r103.notifyItemRangeInserted(r05, this.availableReactions.size() + 1);
    L60:
        if (this.isChannel == true) goto L65;
        RecyclerView.Adapter r104 = this.listAdapter;
        if (r104 == null) goto L65;
        if (r11 == false) goto L65;
        r104.notifyItemChanged(2);
    L65:
        RecyclerView.Adapter r9 = this.listAdapter;
        if (r9 == null) goto L79;
        if (r11 == true) goto L80;
        r9.notifyDataSetChanged();
        return;
    L80:
        return;
    L79:
        return;
    L51:
        if (this.chatReactions.isEmpty() == true) goto L60;
        this.chatReactions.clear();
        RecyclerView.Adapter r105 = this.listAdapter;
        if (r105 == null) goto L60;
        if (r11 == false) goto L60;
        if (this.isChannel == false) goto L58;
        r05 = 1;
    L58:
        r105.notifyItemRangeRemoved(r05, this.availableReactions.size() + 1);
        goto L60
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void updateColors() {
        this.contentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        TextCheckCell r2 = this.enableReactionsCell;
        if (r2 == null) goto L5;
        r2.setColors(Theme.key_windowBackgroundCheckText, Theme.key_switchTrackBlue, Theme.key_switchTrackBlueChecked, Theme.key_switchTrackBlueThumb, Theme.key_switchTrackBlueThumbChecked);
    L5:
        this.listAdapter.notifyDataSetChanged();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r9) {
        this.isChannel = ChatObject.isChannelAndNotMegaGroup(this.chatId, this.currentAccount);
        this.actionBar.setTitle(LocaleController.getString(R.string.Reactions));
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        LinearLayout r0 = new LinearLayout(r9);
        r0.setOrientation(1);
        this.availableReactions.addAll(getMediaDataController().getEnabledReactionsList());
        if (this.isChannel == false) goto L9;
        TextCheckCell r2 = new TextCheckCell(r9);
        this.enableReactionsCell = r2;
        r2.setHeight(56);
        this.enableReactionsCell.setTextAndCheck(LocaleController.getString(R.string.EnableReactions), !this.chatReactions.isEmpty(), false);
        TextCheckCell r22 = this.enableReactionsCell;
        if (r22.isChecked() == false) goto L7;
        int r6 = Theme.key_windowBackgroundChecked;
    L8:
        r22.setBackgroundColor(Theme.getColor(r6));
        this.enableReactionsCell.setTypeface(AndroidUtilities.bold());
        this.enableReactionsCell.setOnClickListener(new ChatReactionsEditActivity$$ExternalSyntheticLambda0(this));
        r0.addView(this.enableReactionsCell, LayoutHelper.createLinear(-1, -2));
        goto L9
    L7:
        r6 = Theme.key_windowBackgroundUnchecked;
    L9:
        LinearLayout r23 = new LinearLayout(r9);
        this.contorlsLayout = r23;
        r23.setOrientation(1);
        RadioCell r24 = new RadioCell(r9);
        this.allReactions = r24;
        r24.setText(LocaleController.getString(R.string.AllReactions), false, true);
        RadioCell r25 = new RadioCell(r9);
        this.someReactions = r25;
        r25.setText(LocaleController.getString(R.string.SomeReactions), false, true);
        RadioCell r1 = new RadioCell(r9);
        this.disableReactions = r1;
        r1.setText(LocaleController.getString(R.string.NoReactions), false, false);
        this.contorlsLayout.addView(this.allReactions, LayoutHelper.createLinear(-1, -2));
        this.contorlsLayout.addView(this.someReactions, LayoutHelper.createLinear(-1, -2));
        this.contorlsLayout.addView(this.disableReactions, LayoutHelper.createLinear(-1, -2));
        this.radioCells.clear();
        this.radioCells.add(this.allReactions);
        this.radioCells.add(this.someReactions);
        this.radioCells.add(this.disableReactions);
        this.allReactions.setOnClickListener(new ChatReactionsEditActivity$$ExternalSyntheticLambda1(this));
        this.someReactions.setOnClickListener(new ChatReactionsEditActivity$$ExternalSyntheticLambda2(this));
        this.disableReactions.setOnClickListener(new ChatReactionsEditActivity$$ExternalSyntheticLambda3(this));
        RadioCell r12 = this.allReactions;
        int r26 = Theme.key_windowBackgroundWhite;
        int r3 = Theme.getColor(r26);
        int r62 = Theme.key_listSelector;
        r12.setBackground(Theme.createSelectorWithBackgroundDrawable(r3, Theme.getColor(r62)));
        this.someReactions.setBackground(Theme.createSelectorWithBackgroundDrawable(Theme.getColor(r26), Theme.getColor(r62)));
        this.disableReactions.setBackground(Theme.createSelectorWithBackgroundDrawable(Theme.getColor(r26), Theme.getColor(r62)));
        setCheckedEnableReactionCell(this.startFromType, false);
        RecyclerListView r13 = new RecyclerListView(r9);
        this.listView = r13;
        r13.setLayoutManager(new LinearLayoutManager(r9));
        RecyclerListView r14 = this.listView;
        AnonymousClass2 r27 = new AnonymousClass2(this, r9);
        this.listAdapter = r27;
        r14.setAdapter(r27);
        this.listView.setOnItemClickListener(new ChatReactionsEditActivity$$ExternalSyntheticLambda4(this));
        r0.addView(this.listView, LayoutHelper.createLinear(-1, 0, 1.0f));
        this.listView.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.contentView = r0;
        this.fragmentView = r0;
        updateColors();
        return this.contentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    @SuppressLint({"NotifyDataSetChanged"})
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r4 == this.currentAccount) goto L6;
        return;
    L6:
        if (r3 != NotificationCenter.reactionsDidLoad) goto L10;
        this.availableReactions.clear();
        this.availableReactions.addAll(getMediaDataController().getEnabledReactionsList());
        this.listAdapter.notifyDataSetChanged();
        return;
    L10:
        if (r3 == NotificationCenter.dialogDeleted) goto L12;
        return;
    L12:
        if (((Long) r5[0]).longValue() != (-this.chatId)) goto L22;
        INavigationLayout r32 = this.parentLayout;
        if (r32 != null) goto L16;
    L19:
        removeSelfFromStack();
        return;
    L16:
        if (r32.getLastFragment() != this) goto L19;
        finishFragment();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ChatReactionsEditActivity$$ExternalSyntheticLambda5 r0 = new ChatReactionsEditActivity$$ExternalSyntheticLambda5(this);
        int r1 = Theme.key_windowBackgroundWhite;
        return SimpleThemeDescription.createThemeDescriptions(r0, new int[]{r1, Theme.key_windowBackgroundWhiteBlackText, Theme.key_windowBackgroundWhiteGrayText2, Theme.key_listSelector, Theme.key_windowBackgroundGray, Theme.key_windowBackgroundWhiteGrayText4, Theme.key_text_RedRegular, Theme.key_windowBackgroundChecked, Theme.key_windowBackgroundCheckText, Theme.key_switchTrackBlue, Theme.key_switchTrackBlueChecked, Theme.key_switchTrackBlueThumb, Theme.key_switchTrackBlueThumbChecked, Theme.key_switchTrack, Theme.key_switchTrackChecked, r1});
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.chatId));
        this.currentChat = r0;
        if (r0 != null) goto L11;
        TLRPC.Chat r02 = MessagesStorage.getInstance(this.currentAccount).getChatSync(this.chatId);
        this.currentChat = r02;
        if (r02 == null) goto L10;
        getMessagesController().putChat(this.currentChat, true);
        if (this.info != null) goto L11;
        TLRPC.ChatFull r03 = MessagesStorage.getInstance(this.currentAccount).loadChatInfo(this.chatId, ChatObject.isChannel(this.currentChat), new CountDownLatch(1), false, false);
        this.info = r03;
        if (r03 != null) goto L11;
    L10:
        return false;
    L11:
        getNotificationCenter().addObserver(this, NotificationCenter.reactionsDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.dialogDeleted);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getMessagesController().setChatReactions(this.chatId, this.selectedType, this.chatReactions);
        getNotificationCenter().removeObserver(this, NotificationCenter.reactionsDidLoad);
        getNotificationCenter().removeObserver(this, NotificationCenter.dialogDeleted);
    }

    public void setInfo(TLRPC.ChatFull r4) {
        this.info = r4;
        if (r4 != null) goto L5;
        return;
    L5:
        if (this.currentChat != null) goto L7;
        this.currentChat = getMessagesController().getChat(Long.valueOf(this.chatId));
    L7:
        this.chatReactions = new ArrayList();
        TLRPC.ChatReactions r42 = r4.available_reactions;
        int r1 = 0;
        if ((r42 instanceof TLRPC.TL_chatReactionsAll) == false) goto L12;
        this.startFromType = 0;
        return;
    L12:
        if ((r42 instanceof TLRPC.TL_chatReactionsNone) == false) goto L16;
        this.startFromType = 2;
        return;
    L16:
        if ((r42 instanceof TLRPC.TL_chatReactionsSome) == false) goto L30;
        TLRPC.TL_chatReactionsSome r43 = (TLRPC.TL_chatReactionsSome) r42;
    L19:
        if (r1 >= r43.reactions.size()) goto L24;
        if ((r43.reactions.get(r1) instanceof TLRPC.TL_reactionEmoji) == false) goto L23;
        this.chatReactions.add(((TLRPC.TL_reactionEmoji) r43.reactions.get(r1)).emoticon);
    L23:
        r1 = r1 + 1;
        goto L19
    L24:
        this.startFromType = 1;
        return;
    }
}
