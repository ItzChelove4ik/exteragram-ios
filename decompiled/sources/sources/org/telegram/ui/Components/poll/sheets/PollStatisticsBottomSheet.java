package org.telegram.ui.Components.poll.sheets;

import android.content.Context;
import java.util.ArrayList;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stats;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes7.dex */
public class PollStatisticsBottomSheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private final StatisticActivity.ChartViewData chartViewData;

    public static /* synthetic */ void $r8$lambda$CPtCpfixfQfvgZoEvcjkmP193dU(Utilities.Callback r0, TL_stats.TL_statsPollStats r1, TLRPC.TL_error r2) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$uQokE3ZEXgJO4DzEHCOe_QcOk8c(PollStatisticsBottomSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public PollStatisticsBottomSheet(Context r10, Theme.ResourcesProvider r11, TL_stats.TL_statsPollStats r12) {
        super(r10, null, true, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r11);
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, r11));
        this.occupyNavigationBar = true;
        this.drawNavigationBar = false;
        this.ignoreTouchActionBar = false;
        this.headerMoveTop = AndroidUtilities.dp(12.0f);
        this.chartViewData = StatisticActivity.createViewData(r12.votes_graph, LocaleController.getString(R.string.PollV2StatsVoteTimeline), 2);
        RecyclerListView r112 = this.recyclerListView;
        int r122 = this.backgroundPaddingLeft;
        r112.setPadding(r122, 0, r122, AndroidUtilities.navigationBarHeight);
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.setSections(true);
        ActionBarMenu r9 = this.actionBar.createMenu();
        r9.addItem(-1, R.drawable.ic_close_white);
        r9.setTranslationX(-AndroidUtilities.dp(5.0f));
        this.adapter.update(false);
    }

    private void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
        if (this.chartViewData == null) goto L6;
        r1.add(UItem.asSpace(AndroidUtilities.dp(12.0f)));
        r1.add(UItem.asChart(0, 0, this.chartViewData));
        return;
    }

    public static int loadStatistics(int r2, long r3, int r5, Utilities.Callback<TL_stats.TL_statsPollStats> r6) {
        TL_stats.TL_statsGetPollStats r0 = new TL_stats.TL_statsGetPollStats();
        r0.peer = MessagesController.getInstance(r2).getInputPeer(r3);
        r0.msg_id = r5;
        return ConnectionsManager.getInstance(r2).sendRequestTyped(r0, new AiTonesController$$ExternalSyntheticLambda0(), new PollStatisticsBottomSheet$$ExternalSyntheticLambda1(r6));
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new PollStatisticsBottomSheet$$ExternalSyntheticLambda0(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.PollV2StatsPollStats);
    }
}
