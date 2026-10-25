package org.telegram.ui.bots;

import android.content.Context;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Stars.BotStarsController;
import org.telegram.ui.bots.ChannelAffiliateProgramsFragment;

/* loaded from: classes7.dex */
public class SuggestedAffiliateProgramsFragment extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private BackDrawable backDrawable;
    private final long dialogId;
    private UniversalRecyclerView listView;

    /* renamed from: org.telegram.ui.bots.SuggestedAffiliateProgramsFragment$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ SuggestedAffiliateProgramsFragment this$0;

        public AnonymousClass1(SuggestedAffiliateProgramsFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    public SuggestedAffiliateProgramsFragment(long r1) {
        this.dialogId = r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r6) {
        ActionBar r0 = this.actionBar;
        BackDrawable r1 = new BackDrawable(false);
        this.backDrawable = r1;
        r0.setBackButtonDrawable(r1);
        this.backDrawable.setAnimationTime(240.0f);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.actionBar.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        ActionBar r02 = this.actionBar;
        int r12 = Theme.key_windowBackgroundWhiteBlackText;
        r02.setItemsColor(Theme.getColor(r12), false);
        this.actionBar.setItemsColor(Theme.getColor(r12), true);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarActionModeDefaultSelector), false);
        this.actionBar.setTitleColor(Theme.getColor(r12));
        this.actionBar.setTitle(LocaleController.getString(R.string.ChannelAffiliatePrograms));
        SizeNotifierFrameLayout r03 = new SizeNotifierFrameLayout(r6);
        UniversalRecyclerView r62 = new UniversalRecyclerView(this, new SuggestedAffiliateProgramsFragment$$ExternalSyntheticLambda0(this), new SuggestedAffiliateProgramsFragment$$ExternalSyntheticLambda1(this), null);
        this.listView = r62;
        r03.addView(r62, LayoutHelper.createFrame(-1, -1, 119));
        this.fragmentView = r03;
        return r03;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 == NotificationCenter.channelSuggestedBotsUpdate) goto L5;
        return;
    L5:
        if (((Long) r5[0]).longValue() != this.dialogId) goto L13;
        UniversalRecyclerView r32 = this.listView;
        if (r32 != null) goto L9;
        return;
    L9:
        if ((r32.getAdapter() instanceof UniversalAdapter) == false) goto L15;
        ((UniversalAdapter) this.listView.getAdapter()).update(true);
        return;
    L15:
        return;
    }

    public void fillItems(ArrayList<UItem> r3, UniversalAdapter r4) {
        BotStarsController.ChannelSuggestedBots r2 = BotStarsController.getInstance(this.currentAccount).getChannelSuggestedBots(this.dialogId);
        int r0 = 0;
    L4:
        if (r0 >= r2.bots.size()) goto L7;
        r3.add(ChannelAffiliateProgramsFragment.BotCell.Factory.as(r2.bots.get(r0), false));
        r0 = r0 + 1;
        goto L4
    L7:
        if (r2.isLoading() == false) goto L11;
        r3.add(UItem.asFlicker(29));
        r3.add(UItem.asFlicker(29));
        r3.add(UItem.asFlicker(29));
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (getLastStoryViewer() != null) goto L5;
    L7:
        int r0 = Theme.getColor(Theme.key_windowBackgroundWhite);
        if (this.actionBar.isActionModeShowed() == false) goto L11;
        r0 = Theme.getColor(Theme.key_actionBarActionModeDefault);
    L11:
        if (ColorUtils.calculateLuminance(r0) <= 0.699999988079071d) goto L14;
        return true;
    L14:
        return false;
    L5:
        if (getLastStoryViewer().isShown() == false) goto L7;
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.channelSuggestedBotsUpdate);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.channelSuggestedBotsUpdate);
        super.onFragmentDestroy();
    }

    public void onItemClick(UItem r8, View r9, int r10, float r11, float r12) {
        Object r82 = r8.object;
        if ((r82 instanceof TL_payments.starRefProgram) == false) goto L6;
        Context r0 = getContext();
        int r1 = this.currentAccount;
        ChannelAffiliateProgramsFragment.showConnectAffiliateAlert(r0, r1, (TL_payments.starRefProgram) r82, this.dialogId, this.resourceProvider, false);
        return;
    }
}
