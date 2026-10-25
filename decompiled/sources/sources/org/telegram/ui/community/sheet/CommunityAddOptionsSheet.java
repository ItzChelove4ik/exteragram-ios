package org.telegram.ui.community.sheet;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.RadioButtonCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class CommunityAddOptionsSheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private final FrameLayout cell;
    private final TLRPC.Chat chat;
    private final boolean isBot;
    private final boolean isChannel;
    private boolean isHidden;
    private final ProfileSearchCell searchCell;
    private final TLRPC.User user;
    private int visibleRow;

    public static /* synthetic */ void $r8$lambda$EzG1s0j4DV6juaisoKiflbj3tJ0(CommunityAddOptionsSheet r0, Utilities.Callback r1, boolean r2) {
        r0.lambda$apply$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FAZTDXAiatUAWckzHFZjdmocVNo(CommunityAddOptionsSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ee7N7Gare0zI2GraDSuKNl1gSRM(CommunityAddOptionsSheet r0, View r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kp04ysdLTZ2Czn2WC0unCJ6S480(CommunityAddOptionsSheet r0, Utilities.Callback r1, TLRPC.Chat r2, View r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public CommunityAddOptionsSheet(Context r21, TLRPC.Chat r22, long r23, Utilities.Callback<Boolean> r25) {
        super(r21, null, false, true, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, null);
        TLRPC.User r12 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r23));
        this.user = r12;
        TLRPC.Chat r14 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r23));
        this.chat = r14;
        this.isBot = UserObject.isBot(r12);
        this.isChannel = ChatObject.isChannelAndNotMegaGroup(r14);
        this.ignoreTouchActionBar = false;
        this.headerMoveTop = AndroidUtilities.dp(12.0f);
        this.actionBar.setTitle(getTitle());
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider));
        FrameLayout r4 = new FrameLayout(r21);
        this.cell = r4;
        r4.setPadding(0, AndroidUtilities.dp(3.0f), 0, AndroidUtilities.dp(3.0f));
        r4.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        ProfileSearchCell r11 = new ProfileSearchCell(r21);
        this.searchCell = r11;
        if (r14 == null) goto L5;
        r11.setData(r14, null, r14.title, LocaleController.formatPluralStringSpaced("Members", r14.participants_count), false, false);
    L7:
        r4.addView(r11, LayoutHelper.createFrame(-1, -2.0f));
        RecyclerListView r42 = this.recyclerListView;
        int r5 = this.backgroundPaddingLeft;
        r42.setPadding(r5, 0, r5, AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(64.0f));
        this.recyclerListView.setSections();
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.setOnItemClickListener(new CommunityAddOptionsSheet$$ExternalSyntheticLambda0(this));
        ButtonWithCounterView r43 = new ButtonWithCounterView(r21, this.resourcesProvider);
        if (r22 != null) goto L10;
        r43.setText(LocaleController.getString(R.string.CommunityCreateCommunity));
    L15:
        r43.setRound();
        r43.setOnClickListener(new CommunityAddOptionsSheet$$ExternalSyntheticLambda1(this, r25, r22));
        this.containerView.addView(r43, LayoutHelper.createFrameMarginPx(-1, 48.0f, 80, AndroidUtilities.dp(12.0f) + this.backgroundPaddingLeft, 0, AndroidUtilities.dp(12.0f) + this.backgroundPaddingLeft, AndroidUtilities.dp(12.0f) + AndroidUtilities.navigationBarHeight));
        this.adapter.update(false);
        return;
    L10:
        if (ChatObject.canAddChatToCommunity(r22) == false) goto L12;
        int r1 = R.string.CommunityAddToCommunityButton;
    L13:
        r43.setText(LocaleController.getString(r1));
        goto L15
    L12:
        r1 = R.string.CommunityAddToCommunityRequestButton;
        goto L13
    L5:
        if (r12 == null) goto L7;
        r11.setData(r12, null, DialogObject.getName(r12), LocaleController.getString(R.string.Bot), false, false);
        goto L7
    }

    private void apply(Utilities.Callback<Boolean> r8, boolean r9, boolean r10) {
        if (r10 == false) goto L12;
        if (r9 == true) goto L12;
        if (this.isBot == true) goto L12;
        Context r0 = getContext();
        Theme.ResourcesProvider r1 = this.resourcesProvider;
        String r2 = LocaleController.getString(R.string.CommunityAddToCommunityTitle);
        if (this.isChannel == false) goto L9;
        int r102 = R.string.CommunityAddToCommunityChannelMessage;
    L10:
        AlertsCreator.showSimpleConfirmAlert(r0, r1, r2, LocaleController.getString(r102), LocaleController.getString(R.string.Add), false, new CommunityAddOptionsSheet$$ExternalSyntheticLambda3(this, r8, r9));
        return;
    L9:
        r102 = R.string.CommunityAddToCommunityGroupMessage;
    L12:
        r8.run(Boolean.valueOf(r9));
        dismiss();
    }

    private void fillItems(ArrayList<UItem> r4, UniversalAdapter r5) {
        r4.add(UItem.asSpace(0, AndroidUtilities.dp(12.0f)));
        r4.add(UItem.asCustom(1, this.cell));
        r4.add(UItem.asSpace(2, AndroidUtilities.dp(12.0f)));
        r4.add(UItem.asHeader(3, LocaleController.getString(R.string.CommunityChatVisibilitySection)));
        this.visibleRow = r4.size();
        String r52 = LocaleController.getString(R.string.CommunityChatVisibilityVisible);
        if (this.isBot == false) goto L5;
        int r0 = R.string.CommunityChatVisibilityVisibleBotInfo;
    L6:
        r4.add(UItem.asRadio2(151, r52, LocaleController.getString(r0)).setChecked(!this.isHidden));
        String r53 = LocaleController.getString(R.string.CommunityChatVisibilityHidden);
        if (this.isBot == false) goto L9;
        int r02 = R.string.CommunityChatVisibilityHiddenBotInfo;
    L10:
        r4.add(UItem.asRadio2(150, r53, LocaleController.getString(r02)).setChecked(this.isHidden));
        r4.add(UItem.asShadow(6, LocaleController.getString(R.string.CommunityChatVisibilityCannotChange)));
        return;
    L9:
        r02 = R.string.CommunityChatVisibilityHiddenInfo;
        goto L10
    L5:
        r0 = R.string.CommunityChatVisibilityVisibleInfo;
        goto L6
    }

    private /* synthetic */ void lambda$apply$2(Utilities.Callback r2, boolean r3) {
        apply(r2, r3, false);
    }

    private /* synthetic */ void lambda$new$0(View r2, int r3) {
        int r22 = this.adapter.getItem(r3 - 1).id;
        if (r22 != 151) goto L7;
        setIsHidden(false);
        return;
    L7:
        if (r22 != 150) goto L10;
        setIsHidden(true);
        return;
    }

    private /* synthetic */ void lambda$new$1(Utilities.Callback r1, TLRPC.Chat r2, View r3) {
        boolean r32 = this.isHidden;
        if (r2 != null) goto L5;
    L7:
        boolean r22 = false;
    L8:
        apply(r1, r32, r22);
        return;
    L5:
        if (ChatObject.canAddChatToCommunity(r2) == true) goto L7;
        r22 = true;
        goto L8
    }

    private void setIsHidden(boolean r5) {
        if (this.isHidden == r5) goto L17;
        this.isHidden = r5;
        View r0 = this.recyclerListView.findViewByPosition(this.visibleRow + 1);
        if ((r0 instanceof RadioButtonCell) == false) goto L8;
        ((RadioButtonCell) r0).setChecked(!r5, true);
        boolean r02 = false;
    L9:
        View r1 = this.recyclerListView.findViewByPosition(this.visibleRow + 2);
        if ((r1 instanceof RadioButtonCell) == false) goto L12;
        ((RadioButtonCell) r1).setChecked(r5, true);
    L13:
        if (r02 == false) goto L16;
        this.adapter.update(true);
        return;
    L16:
        return;
    L12:
        r02 = true;
        goto L13
    L8:
        r02 = true;
        goto L9
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, false, new CommunityAddOptionsSheet$$ExternalSyntheticLambda2(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        if (this.isBot == false) goto L5;
        int r0 = R.string.CommunityAddBotTitle;
    L7:
        return LocaleController.getString(r0);
    L5:
        r0 = R.string.CommunityAddChatTitle;
        goto L7
    }
}
