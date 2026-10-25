package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.collection.ArraySet;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.chaquo.python.internal.Common;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LruCache;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stats;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.EmptyCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.LoadingCell;
import org.telegram.ui.Cells.ManageChatUserCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Charts.BaseChartView;
import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.Charts.data.StackLinearChartData;
import org.telegram.ui.Charts.view_data.ChartHeaderView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatAvatarContainer;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.StatisticActivity;
import org.telegram.ui.Stories.StoriesListPlaceProvider;

/* loaded from: classes6.dex */
public class MessageStatisticActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private ChatAvatarContainer avatarContainer;
    private TLRPC.ChatFull chat;
    private final long chatId;
    private LruCache<ChartData> childDataCache;
    boolean drawPlay;
    private int emptyRow;
    private EmptyTextProgressView emptyView;
    private boolean endReached;
    private int endRow;
    private boolean firstLoaded;
    boolean hasThumb;
    private int headerRow;
    private RLottieImageView imageView;
    private int interactionsChartRow;
    private StatisticActivity.ChartViewData interactionsViewData;
    private StatisticActivity.ZoomCancelable lastCancelable;
    private LinearLayoutManager layoutManager;
    private FrameLayout listContainer;
    private RecyclerListView listView;
    private ListAdapter listViewAdapter;
    private boolean loading;
    private int loadingRow;
    private final int messageId;
    private MessageObject messageObject;
    private ArrayList<MessageObject> messages;
    private boolean needActionbarMenu;
    private String nextOffset;
    private int overviewHeaderRow;
    private int overviewRow;
    private LinearLayout progressLayout;
    private int publicChats;
    private int reactionsByEmotionChartRow;
    private StatisticActivity.ChartViewData reactionsByEmotionData;
    private StatisticActivity.RecentPostInfo recentPostInfo;
    private int rowCount;
    ArraySet<Integer> shadowDivideCells;
    private BaseChartView.SharedUiComponents sharedUi;
    private final Runnable showProgressbar;
    private int startRow;
    private boolean statsLoaded;
    ImageReceiver thumbImage;

    /* renamed from: org.telegram.ui.MessageStatisticActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ MessageStatisticActivity this$0;

        public AnonymousClass1(MessageStatisticActivity r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            MessageStatisticActivity.m16205$$Nest$fgetprogressLayout(this.this$0).animate().alpha(1.0f).setDuration(230);
        }
    }

    /* renamed from: org.telegram.ui.MessageStatisticActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ MessageStatisticActivity this$0;

        public AnonymousClass2(MessageStatisticActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            MessageStatisticActivity.m16205$$Nest$fgetprogressLayout(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.MessageStatisticActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ MessageStatisticActivity this$0;

        public AnonymousClass3(MessageStatisticActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            int r32 = MessageStatisticActivity.m16197$$Nest$fgetlayoutManager(this.this$0).findFirstVisibleItemPosition();
            if (r32 != (-1)) goto L5;
            int r42 = 0;
        L6:
            int r22 = r2.getAdapter().getItemCount();
            if (r42 > 0) goto L9;
            return;
        L9:
            if (MessageStatisticActivity.m16191$$Nest$fgetendReached(this.this$0) == false) goto L11;
            return;
        L11:
            if (MessageStatisticActivity.m16199$$Nest$fgetloading(this.this$0) == false) goto L13;
            return;
        L13:
            if (MessageStatisticActivity.m16202$$Nest$fgetmessages(this.this$0).isEmpty() == false) goto L15;
            return;
        L15:
            if ((r32 + r42) >= (r22 - 5)) goto L17;
            return;
        L17:
            if (MessageStatisticActivity.m16212$$Nest$fgetstatsLoaded(this.this$0) == false) goto L25;
            MessageStatisticActivity.m16216$$Nest$mloadChats(this.this$0, 100);
            return;
        L25:
            return;
        L5:
            r42 = Math.abs(MessageStatisticActivity.m16197$$Nest$fgetlayoutManager(this.this$0).findLastVisibleItemPosition() - r32) + 1;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.MessageStatisticActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ChatAvatarContainer {
        final /* synthetic */ MessageStatisticActivity this$0;

        public AnonymousClass4(MessageStatisticActivity r1, Context r2, BaseFragment r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.ChatAvatarContainer, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            MessageStatisticActivity r0 = this.this$0;
            r0.thumbImage.setImageCoords(MessageStatisticActivity.m16186$$Nest$fgetavatarContainer(r0).getAvatarImageView().getX(), MessageStatisticActivity.m16186$$Nest$fgetavatarContainer(this.this$0).getAvatarImageView().getY(), MessageStatisticActivity.m16186$$Nest$fgetavatarContainer(this.this$0).getAvatarImageView().getWidth(), MessageStatisticActivity.m16186$$Nest$fgetavatarContainer(this.this$0).getAvatarImageView().getHeight());
            if (this.this$0.hasThumb == false) goto L5;
            r6.save();
            r6.scale(0.9f, 0.9f, this.this$0.thumbImage.getCenterX(), this.this$0.thumbImage.getCenterY());
            this.this$0.thumbImage.draw(r6);
            r6.restore();
        L5:
            MessageStatisticActivity r02 = this.this$0;
            if (r02.drawPlay == false) goto L9;
            int r03 = (int) (r02.thumbImage.getCenterX() - (Theme.dialogs_playDrawable.getIntrinsicWidth() / 2));
            int r5 = (int) (this.this$0.thumbImage.getCenterY() - (Theme.dialogs_playDrawable.getIntrinsicHeight() / 2));
            Drawable r1 = Theme.dialogs_playDrawable;
            r1.setBounds(r03, r5, r1.getIntrinsicWidth() + r03, Theme.dialogs_playDrawable.getIntrinsicHeight() + r5);
            Theme.dialogs_playDrawable.draw(r6);
            return;
        }

        @Override // org.telegram.ui.Components.ChatAvatarContainer, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.this$0.thumbImage.onAttachedToWindow();
        }

        @Override // org.telegram.ui.Components.ChatAvatarContainer, android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.this$0.thumbImage.onDetachedFromWindow();
        }
    }

    /* renamed from: org.telegram.ui.MessageStatisticActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ MessageStatisticActivity this$0;

        public AnonymousClass5(MessageStatisticActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r4) {
            if (r4 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r4 != 1) goto L10;
            Bundle r42 = new Bundle();
            r42.putLong("chat_id", MessageStatisticActivity.m16188$$Nest$fgetchatId(this.this$0));
            this.this$0.presentFragment(new StatisticActivity(r42));
            return;
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ MessageStatisticActivity this$0;

        /* renamed from: org.telegram.ui.MessageStatisticActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends StatisticActivity.BaseChartCell {
            final /* synthetic */ ListAdapter this$1;

            public static /* synthetic */ void $r8$lambda$m2bJFpMzz7a1yoLG0V7OiZP8Aww(AnonymousClass1 r0, String r1, StatisticActivity.ZoomCancelable r2, TLObject r3, TLRPC.TL_error r4) {
                r0.lambda$onZoomed$1(r1, r2, r3, r4);
            }

            /* renamed from: $r8$lambda$rPS94xomKNV6Pt0h-x7ipdNAAyA, reason: not valid java name */
            public static /* synthetic */ void m16217$r8$lambda$rPS94xomKNV6Pt0hx7ipdNAAyA(AnonymousClass1 r0, ChartData r1, String r2, StatisticActivity.ZoomCancelable r3) {
                r0.lambda$onZoomed$0(r1, r2, r3);
            }

            public AnonymousClass1(ListAdapter r1, Context r2, int r3, BaseChartView.SharedUiComponents r4, Theme.ResourcesProvider r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            private /* synthetic */ void lambda$onZoomed$0(ChartData r2, String r3, StatisticActivity.ZoomCancelable r4) {
                if (r2 == null) goto L4;
                MessageStatisticActivity.m16189$$Nest$fgetchildDataCache(this.this$1.this$0).put(r3, r2);
            L4:
                if (r2 != null) goto L6;
            L12:
                zoomCanceled();
                return;
            L6:
                if (r4.canceled == true) goto L12;
                if (r4.adapterPosition < 0) goto L12;
                View r32 = MessageStatisticActivity.m16197$$Nest$fgetlayoutManager(this.this$1.this$0).findViewByPosition(r4.adapterPosition);
                if ((r32 instanceof StatisticActivity.BaseChartCell) == false) goto L12;
                this.data.childChartData = r2;
                StatisticActivity.BaseChartCell r33 = (StatisticActivity.BaseChartCell) r32;
                r33.chartView.legendSignatureView.showProgress(false, false);
                r33.zoomChart(false);
                goto L12
            }

            private /* synthetic */ void lambda$onZoomed$1(String r3, StatisticActivity.ZoomCancelable r4, TLObject r5, TLRPC.TL_error r6) {
                ChartData r0 = null;
                if ((r5 instanceof TL_stats.TL_statsGraph) == false) goto L10;
                r0 = StatisticActivity.createChartData(new JSONObject(((TL_stats.TL_statsGraph) r5).json.data), this.data.graphType, false);     // Catch: JSONException -> L7
            L12:
                AndroidUtilities.runOnUIThread(new MessageStatisticActivity$ListAdapter$1$$ExternalSyntheticLambda1(this, r0, r3, r4));
                return;
            L7:
                e = move-exception;
                e.printStackTrace();
                goto L12
            L10:
                if ((r5 instanceof TL_stats.TL_statsGraphError) == false) goto L12;
                Toast.makeText(getContext(), ((TL_stats.TL_statsGraphError) r5).error, 1).show();
                goto L12
            }

            @Override // org.telegram.ui.StatisticActivity.BaseChartCell
            public void loadData(StatisticActivity.ChartViewData r1) {
            }

            @Override // org.telegram.ui.StatisticActivity.BaseChartCell
            public void onZoomed() {
                if (this.data.activeZoom > 0) goto L24;
                performClick();
                BaseChartView r1 = this.chartView;
                if (r1.legendSignatureView.canGoZoom == false) goto L25;
                long r12 = r1.getSelectedDate();
                int r5 = this.chartType;
                StatisticActivity.ChartViewData r6 = this.data;
                if (r5 != 4) goto L13;
                r6.childChartData = new StackLinearChartData(r6.chartData, r12);
                zoomChart(false);
                return;
            L13:
                if (r6.zoomToken != null) goto L15;
                return;
            L15:
                zoomCanceled();
                String r52 = this.data.zoomToken + "_" + r12;
                ChartData r62 = (ChartData) MessageStatisticActivity.m16189$$Nest$fgetchildDataCache(this.this$1.this$0).get(r52);
                if (r62 == null) goto L19;
                this.data.childChartData = r62;
                zoomChart(false);
                return;
            L19:
                TL_stats.TL_loadAsyncGraph r9 = new TL_stats.TL_loadAsyncGraph();
                r9.token = this.data.zoomToken;
                if (r12 == 0) goto L22;
                r9.x = r12;
                r9.flags |= 1;
            L22:
                MessageStatisticActivity r13 = this.this$1.this$0;
                StatisticActivity.ZoomCancelable r2 = new StatisticActivity.ZoomCancelable();
                MessageStatisticActivity.m16213$$Nest$fputlastCancelable(r13, r2);
                r2.adapterPosition = MessageStatisticActivity.m16198$$Nest$fgetlistView(this.this$1.this$0).getChildAdapterPosition(this);
                this.chartView.legendSignatureView.showProgress(true, false);
                ConnectionsManager.getInstance(MessageStatisticActivity.access$200(this.this$1.this$0)).bindRequestToGuid(ConnectionsManager.getInstance(MessageStatisticActivity.access$000(this.this$1.this$0)).sendRequest(r9, new MessageStatisticActivity$ListAdapter$1$$ExternalSyntheticLambda0(this, r52, r2), null, null, 0, MessageStatisticActivity.m16187$$Nest$fgetchat(this.this$1.this$0).stats_dc, 1, true), MessageStatisticActivity.access$100(this.this$1.this$0));
                return;
            L25:
                return;
            }

            @Override // org.telegram.ui.StatisticActivity.BaseChartCell
            public void zoomCanceled() {
                if (MessageStatisticActivity.m16196$$Nest$fgetlastCancelable(this.this$1.this$0) == null) goto L5;
                MessageStatisticActivity.m16196$$Nest$fgetlastCancelable(this.this$1.this$0).canceled = true;
            L5:
                int r0 = MessageStatisticActivity.m16198$$Nest$fgetlistView(this.this$1.this$0).getChildCount();
                int r3 = 0;
            L6:
                if (r3 >= r0) goto L11;
                View r4 = MessageStatisticActivity.m16198$$Nest$fgetlistView(this.this$1.this$0).getChildAt(r3);
                if ((r4 instanceof StatisticActivity.BaseChartCell) == false) goto L10;
                ((StatisticActivity.BaseChartCell) r4).chartView.legendSignatureView.showProgress(false, true);
            L10:
                r3 = r3 + 1;
                goto L6
            }
        }

        public static /* synthetic */ void $r8$lambda$w6O3azHmXYS3cOKKFvDejJR18LE(ListAdapter r0, MessageObject r1, View r2) {
            r0.lambda$onBindViewHolder$0(r1, r2);
        }

        public ListAdapter(MessageStatisticActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ void lambda$onBindViewHolder$0(MessageObject r2, View r3) {
            if (MessageStatisticActivity.m16215$$Nest$mcheckIsDeletedStory(this.this$0, r2) == false) goto L5;
            return;
        L5:
            this.this$0.getOrCreateStoryViewer().open(this.this$0.getContext(), r2.storyItem, StoriesListPlaceProvider.of(MessageStatisticActivity.m16198$$Nest$fgetlistView(this.this$0)));
        }

        public MessageObject getItem(int r2) {
            if (r2 >= MessageStatisticActivity.m16211$$Nest$fgetstartRow(this.this$0)) goto L5;
            return null;
        L5:
            if (r2 < MessageStatisticActivity.m16192$$Nest$fgetendRow(this.this$0)) goto L7;
            return null;
        L7:
            return (MessageObject) MessageStatisticActivity.m16202$$Nest$fgetmessages(this.this$0).get(r2 - MessageStatisticActivity.m16211$$Nest$fgetstartRow(this.this$0));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return MessageStatisticActivity.m16210$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (this.this$0.shadowDivideCells.contains(Integer.valueOf(r3)) == false) goto L7;
            return 1;
        L7:
            if (r3 != MessageStatisticActivity.m16193$$Nest$fgetheaderRow(this.this$0)) goto L9;
            return 2;
        L9:
            if (r3 != MessageStatisticActivity.m16203$$Nest$fgetoverviewHeaderRow(this.this$0)) goto L12;
            return 2;
        L12:
            if (r3 != MessageStatisticActivity.m16200$$Nest$fgetloadingRow(this.this$0)) goto L16;
            return 3;
        L16:
            if (r3 != MessageStatisticActivity.m16194$$Nest$fgetinteractionsChartRow(this.this$0)) goto L20;
            return 4;
        L20:
            if (r3 != MessageStatisticActivity.m16204$$Nest$fgetoverviewRow(this.this$0)) goto L24;
            return 5;
        L24:
            if (r3 != MessageStatisticActivity.m16190$$Nest$fgetemptyRow(this.this$0)) goto L28;
            return 6;
        L28:
            if (r3 != MessageStatisticActivity.m16207$$Nest$fgetreactionsByEmotionChartRow(this.this$0)) goto L31;
            return 7;
        L31:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() == 0) goto L5;
            return false;
        L5:
            return ((ManageChatUserCell) r1.itemView).getCurrentObject() instanceof TLObject;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r11, int r12) {
            int r0 = r11.getItemViewType();
            boolean r1 = true;
            if (r0 == 0) goto L28;
            if (r0 != 1) goto L6;
            r11.itemView.setBackgroundDrawable(Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
            return;
        L6:
            if (r0 != 2) goto L8;
            HeaderCell r112 = (HeaderCell) r11.itemView;
            if (r12 != MessageStatisticActivity.m16203$$Nest$fgetoverviewHeaderRow(this.this$0)) goto L24;
            r112.setTopMargin(9);
            r112.setPadding(0, 0, 0, AndroidUtilities.dp(8.0f));
            r112.setText(LocaleController.formatString("StatisticOverview", R.string.StatisticOverview, new Object[0]));
            return;
        L24:
            r112.setTopMargin(11);
            r112.setPadding(0, 0, 0, 0);
            r112.setText(LocaleController.formatString("PublicShares", R.string.PublicShares, new Object[0]));
            return;
        L8:
            if (r0 != 4) goto L10;
            StatisticActivity.BaseChartCell r113 = (StatisticActivity.BaseChartCell) r11.itemView;
            r113.updateData(MessageStatisticActivity.m16195$$Nest$fgetinteractionsViewData(this.this$0), false);
            r113.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return;
        L10:
            if (r0 != 5) goto L12;
            ((OverviewCell) r11.itemView).setData();
            return;
        L12:
            if (r0 != 7) goto L69;
            StatisticActivity.BaseChartCell r114 = (StatisticActivity.BaseChartCell) r11.itemView;
            r114.updateData(MessageStatisticActivity.m16208$$Nest$fgetreactionsByEmotionData(this.this$0), false);
            r114.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return;
        L69:
            return;
        L28:
            ManageChatUserCell r115 = (ManageChatUserCell) r11.itemView;
            MessageObject r02 = getItem(r12);
            long r3 = MessageObject.getDialogId(r02.messageOwner);
            if (r02.isStory() == false) goto L47;
            boolean r5 = DialogObject.isUserDialog(r3);
            MessageStatisticActivity r8 = this.this$0;
            if (r5 == false) goto L33;
            Object r32 = r8.getMessagesController().getUser(Long.valueOf(r3));
        L34:
            TL_stories.StoryViews r4 = r02.storyItem.views;
            if (r4 == null) goto L40;
            int r42 = r4.views_count;
            if (r42 == 0) goto L40;
            String r43 = LocaleController.formatPluralString("Views", r42, new Object[0]);
        L42:
            if (r12 != (MessageStatisticActivity.m16192$$Nest$fgetendRow(this.this$0) - 1)) goto L45;
            r1 = false;
        L45:
            r115.setData(r32, null, r43, r1);
            r115.setStoryItem(r02.storyItem, new MessageStatisticActivity$ListAdapter$$ExternalSyntheticLambda0(this, r02));
            return;
        L40:
            r43 = LocaleController.getString(R.string.NoViews);
            goto L42
        L33:
            r32 = r8.getMessagesController().getChat(Long.valueOf(-r3));
            goto L34
        L47:
            r115.setStoryItem(null, null);
            boolean r52 = DialogObject.isUserDialog(r3);
            MessageStatisticActivity r82 = this.this$0;
            if (r52 == false) goto L51;
            TLRPC.User r03 = r82.getMessagesController().getUser(Long.valueOf(r3));
        L50:
            String r33 = null;
        L61:
            if (r03 != null) goto L63;
            return;
        L63:
            if (r12 != (MessageStatisticActivity.m16192$$Nest$fgetendRow(this.this$0) - 1)) goto L66;
            r1 = false;
        L66:
            r115.setData(r03, null, r33, r1);
            return;
        L51:
            TLRPC.Chat r34 = r82.getMessagesController().getChat(Long.valueOf(-r3));
            if (ChatObject.isChannel(r34) == true) goto L54;
        L57:
            int r44 = r34.participants_count;
            if (r44 == 0) goto L60;
            String r04 = String.format("%1$s, %2$s", new Object[]{LocaleController.formatPluralString("Members", r44, new Object[0]), LocaleController.formatPluralString("Views", r02.messageOwner.views, new Object[0])});
        L56:
            r33 = r04;
            r03 = r34;
            goto L61
        L60:
            r03 = r34;
            goto L50
        L54:
            if (r34.megagroup == true) goto L57;
            r04 = LocaleController.formatPluralString("Views", r02.messageOwner.views, new Object[0]);
            goto L56
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r11, int r12) {
            if (r12 != 0) goto L4;
            ManageChatUserCell r0 = new ManageChatUserCell(this.mContext, 6, 2, false, this.this$0.getResourceProvider());
            r0.setDividerColor(Theme.key_divider);
            ManageChatUserCell r02 = r0;
        L24:
            View r112 = r02;
        L28:
            return new RecyclerListView.Holder(r112);
        L4:
            if (r12 != 1) goto L6;
            r112 = new ShadowSectionCell(this.mContext, this.this$0.getResourceProvider());
            goto L28
        L6:
            if (r12 != 2) goto L8;
            HeaderCell r03 = new HeaderCell(this.mContext, Theme.key_windowBackgroundWhiteBlackText, 16, 11, false, this.this$0.getResourceProvider());
            r03.setHeight(43);
            r02 = r03;
            goto L24
        L8:
            if (r12 != 4) goto L10;
        L18:
            Context r6 = this.mContext;
            if (r12 != 4) goto L21;
            int r7 = 1;
        L22:
            MessageStatisticActivity r113 = this.this$0;
            BaseChartView.SharedUiComponents r8 = new BaseChartView.SharedUiComponents(r113.getResourceProvider());
            MessageStatisticActivity.m16214$$Nest$fputsharedUi(r113, r8);
            r112 = new AnonymousClass1(this, r6, r7, r8, this.this$0.getResourceProvider());
            goto L28
        L21:
            r7 = 2;
            goto L22
        L10:
            if (r12 != 5) goto L12;
            r112 = new OverviewCell(this.this$0, this.mContext);
            r112.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            goto L28
        L12:
            if (r12 != 6) goto L14;
            r112 = new EmptyCell(this.mContext, 16);
            r112.setLayoutParams(new RecyclerView.LayoutParams(-1, 16));
            goto L28
        L14:
            if (r12 == 7) goto L18;
            r112 = new LoadingCell(this.mContext, AndroidUtilities.dp(40.0f), AndroidUtilities.dp(120.0f));
            goto L28
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r1) {
            View r0 = r1.itemView;
            if ((r0 instanceof ManageChatUserCell) == false) goto L6;
            ((ManageChatUserCell) r0).recycle();
            return;
        }
    }

    public class OverviewCell extends LinearLayout {
        TextView[] primary;
        final /* synthetic */ MessageStatisticActivity this$0;
        TextView[] title;

        /* renamed from: -$$Nest$mupdateColors, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16218$$Nest$mupdateColors(OverviewCell r0) {
            r0.updateColors();
        }

        public OverviewCell(MessageStatisticActivity r13, Context r14) {
            this.this$0 = r13;
            super(r14);
            this.primary = new TextView[4];
            this.title = new TextView[4];
            setOrientation(1);
            setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            int r1 = 0;
        L4:
            if (r1 >= 2) goto L12;
            LinearLayout r3 = new LinearLayout(r14);
            r3.setOrientation(0);
            int r5 = 0;
        L6:
            if (r5 >= 2) goto L8;
            LinearLayout r6 = new LinearLayout(r14);
            r6.setOrientation(1);
            LinearLayout r7 = new LinearLayout(r14);
            r7.setOrientation(0);
            int r9 = (r1 * 2) + r5;
            this.primary[r9] = new TextView(r14);
            this.title[r9] = new TextView(r14);
            this.primary[r9].setTypeface(AndroidUtilities.bold());
            this.primary[r9].setTextSize(1, 17.0f);
            this.title[r9].setTextSize(1, 13.0f);
            this.title[r9].setGravity(3);
            r7.addView(this.primary[r9]);
            r6.addView(r7);
            r6.addView(this.title[r9]);
            r3.addView(r6, LayoutHelper.createLinear(-1, -2, 1.0f));
            r5 = r5 + 1;
            goto L6
        L8:
            if (r1 != 0) goto L10;
            float r11 = 16.0f;
        L11:
            addView(r3, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 0.0f, 0.0f, r11));
            r1 = r1 + 1;
            goto L4
        L10:
            r11 = 0.0f;
            goto L11
        }

        private void updateColors() {
            int r0 = 0;
        L4:
            if (r0 >= 4) goto L6;
            this.primary[r0].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.this$0.getResourceProvider()));
            this.title[r0].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.this$0.getResourceProvider()));
            r0 = r0 + 1;
            goto L4
        }

        public void setData() {
            StatisticActivity.RecentPostInfo r0 = MessageStatisticActivity.m16209$$Nest$fgetrecentPostInfo(this.this$0);
            MessageStatisticActivity r1 = this.this$0;
            if (r0 == null) goto L5;
            int r02 = MessageStatisticActivity.m16209$$Nest$fgetrecentPostInfo(r1).getViews();
            int r12 = MessageStatisticActivity.m16209$$Nest$fgetrecentPostInfo(this.this$0).getForwards();
            int r3 = MessageStatisticActivity.m16209$$Nest$fgetrecentPostInfo(this.this$0).getReactions();
        L23:
            this.primary[0].setText(AndroidUtilities.formatWholeNumber(r02, 0));
            this.title[0].setText(LocaleController.getString(R.string.StatisticViews));
            this.primary[1].setText(AndroidUtilities.formatWholeNumber(MessageStatisticActivity.m16206$$Nest$fgetpublicChats(this.this$0), 0));
            this.title[1].setText(LocaleController.formatString("PublicShares", R.string.PublicShares, new Object[0]));
            this.primary[2].setText(AndroidUtilities.formatWholeNumber(r3, 0));
            this.title[2].setText(LocaleController.formatString("Reactions", R.string.Reactions, new Object[0]));
            if (MessageStatisticActivity.m16187$$Nest$fgetchat(this.this$0) != null) goto L26;
        L29:
            this.primary[3].setText(AndroidUtilities.formatWholeNumber(Math.max(0, r12 - MessageStatisticActivity.m16206$$Nest$fgetpublicChats(this.this$0)), 0));
            this.title[3].setText(LocaleController.formatString("PrivateShares", R.string.PrivateShares, new Object[0]));
            updateColors();
            return;
        L26:
            if ((MessageStatisticActivity.m16187$$Nest$fgetchat(this.this$0).available_reactions instanceof TLRPC.TL_chatReactionsNone) == false) goto L29;
            if (r3 != 0) goto L29;
            ((ViewGroup) this.title[2].getParent()).setVisibility(8);
            goto L29
        L5:
            boolean r03 = MessageStatisticActivity.m16201$$Nest$fgetmessageObject(r1).isStory();
            MessageStatisticActivity r13 = this.this$0;
            if (r03 == false) goto L8;
            r02 = MessageStatisticActivity.m16201$$Nest$fgetmessageObject(r13).storyItem.views.views_count;
        L9:
            boolean r14 = MessageStatisticActivity.m16201$$Nest$fgetmessageObject(this.this$0).isStory();
            MessageStatisticActivity r32 = this.this$0;
            if (r14 == false) goto L12;
            r12 = MessageStatisticActivity.m16201$$Nest$fgetmessageObject(r32).storyItem.views.forwards_count;
        L13:
            boolean r33 = MessageStatisticActivity.m16201$$Nest$fgetmessageObject(this.this$0).isStory();
            MessageStatisticActivity r4 = this.this$0;
            if (r33 == false) goto L17;
            r3 = MessageStatisticActivity.m16201$$Nest$fgetmessageObject(r4).storyItem.views.reactions_count;
            goto L23
        L17:
            if (MessageStatisticActivity.m16201$$Nest$fgetmessageObject(r4).messageOwner.reactions == null) goto L22;
            r3 = 0;
            int r42 = 0;
        L20:
            if (r42 >= MessageStatisticActivity.m16201$$Nest$fgetmessageObject(this.this$0).messageOwner.reactions.results.size()) goto L23;
            r3 = r3 + MessageStatisticActivity.m16201$$Nest$fgetmessageObject(this.this$0).messageOwner.reactions.results.get(r42).count;
            r42 = r42 + 1;
            goto L20
        L22:
            r3 = 0;
            goto L23
        L12:
            r12 = MessageStatisticActivity.m16201$$Nest$fgetmessageObject(r32).messageOwner.forwards;
            goto L13
        L8:
            r02 = MessageStatisticActivity.m16201$$Nest$fgetmessageObject(r13).messageOwner.views;
            goto L9
        }
    }

    /* renamed from: $r8$lambda$3Tn4P4EVPhVcP-3hdSaBLaVz7JU, reason: not valid java name */
    public static /* synthetic */ void m16179$r8$lambda$3Tn4P4EVPhVcP3hdSaBLaVz7JU(MessageStatisticActivity r0, String r1, TL_stats.TL_loadAsyncGraph r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadStat$10(r1, r2, r3, r4);
    }

    public static /* synthetic */ boolean $r8$lambda$7txFL0EqoUoMMtVx80_uiKhBxVs(MessageStatisticActivity r0, View r1, int r2) {
        return r0.lambda$createView$2(r1, r2);
    }

    /* renamed from: $r8$lambda$9-0SBjg5oIKE_2J3LgqTgokyda8, reason: not valid java name */
    public static /* synthetic */ void m16180$r8$lambda$90SBjg5oIKE_2J3LgqTgokyda8(MessageStatisticActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$loadStat$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FY6G72oFyVU6ZzJZ0Yxu8RZoQxo(MessageStatisticActivity r0, TLRPC.TL_error r1, ChartData r2, String r3, TL_stats.TL_loadAsyncGraph r4) {
        r0.lambda$loadStat$9(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$HxjgMWuSjUIXMF-hiBRe0epeY1E, reason: not valid java name */
    public static /* synthetic */ void m16181$r8$lambda$HxjgMWuSjUIXMFhiBRe0epeY1E(MessageStatisticActivity r0, View r1, int r2) {
        r0.lambda$createView$0(r1, r2);
    }

    /* renamed from: $r8$lambda$Vcc-rFP_2GB4ApCetKZ-cP2QfLQ, reason: not valid java name */
    public static /* synthetic */ void m16182$r8$lambda$VccrFP_2GB4ApCetKZcP2QfLQ(MessageStatisticActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadChats$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_BvznTRz27wGRhNUZ1IgJuRh8Ak(MessageStatisticActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadChats$5(r1, r2);
    }

    /* renamed from: $r8$lambda$amisx6_EZSFDlWs8AqfqG-TdCP0, reason: not valid java name */
    public static /* synthetic */ void m16183$r8$lambda$amisx6_EZSFDlWs8AqfqGTdCP0(MessageStatisticActivity r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    /* renamed from: $r8$lambda$cOIxShdGPu6qcIDvC-QgTnzreLE, reason: not valid java name */
    public static /* synthetic */ void m16184$r8$lambda$cOIxShdGPu6qcIDvCQgTnzreLE(MessageStatisticActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$loadChats$4(r1, r2);
    }

    /* renamed from: $r8$lambda$hc5vwLYtu2b726_zzl9C-WebbeI, reason: not valid java name */
    public static /* synthetic */ void m16185$r8$lambda$hc5vwLYtu2b726_zzl9CWebbeI(MessageStatisticActivity r0) {
        r0.lambda$getThemeDescriptions$13();
    }

    public static /* synthetic */ void $r8$lambda$lp53p_ZQesHaD3DK3TsH6MMYQPk(MessageStatisticActivity r0, TLObject r1) {
        r0.lambda$loadStat$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$o9ahcW5Kci4Yh7Fgo95TXojspGA(MessageStatisticActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$loadChats$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sv1bRYTidS73XcK4xYlOisRY9SU(MessageStatisticActivity r0, MessageObject r1, boolean r2, long r3, DialogInterface r5, int r6) {
        r0.lambda$createView$1(r1, r2, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$vbHlTkONcj1KN9c4PWEQsKTYA54(MessageStatisticActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadStat$12(r1, r2);
    }

    /* renamed from: -$$Nest$fgetavatarContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAvatarContainer m16186$$Nest$fgetavatarContainer(MessageStatisticActivity r0) {
        return r0.avatarContainer;
    }

    /* renamed from: -$$Nest$fgetchat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.ChatFull m16187$$Nest$fgetchat(MessageStatisticActivity r0) {
        return r0.chat;
    }

    /* renamed from: -$$Nest$fgetchatId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m16188$$Nest$fgetchatId(MessageStatisticActivity r2) {
        return r2.chatId;
    }

    /* renamed from: -$$Nest$fgetchildDataCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LruCache m16189$$Nest$fgetchildDataCache(MessageStatisticActivity r0) {
        return r0.childDataCache;
    }

    /* renamed from: -$$Nest$fgetemptyRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16190$$Nest$fgetemptyRow(MessageStatisticActivity r0) {
        return r0.emptyRow;
    }

    /* renamed from: -$$Nest$fgetendReached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16191$$Nest$fgetendReached(MessageStatisticActivity r0) {
        return r0.endReached;
    }

    /* renamed from: -$$Nest$fgetendRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16192$$Nest$fgetendRow(MessageStatisticActivity r0) {
        return r0.endRow;
    }

    /* renamed from: -$$Nest$fgetheaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16193$$Nest$fgetheaderRow(MessageStatisticActivity r0) {
        return r0.headerRow;
    }

    /* renamed from: -$$Nest$fgetinteractionsChartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16194$$Nest$fgetinteractionsChartRow(MessageStatisticActivity r0) {
        return r0.interactionsChartRow;
    }

    /* renamed from: -$$Nest$fgetinteractionsViewData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StatisticActivity.ChartViewData m16195$$Nest$fgetinteractionsViewData(MessageStatisticActivity r0) {
        return r0.interactionsViewData;
    }

    /* renamed from: -$$Nest$fgetlastCancelable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StatisticActivity.ZoomCancelable m16196$$Nest$fgetlastCancelable(MessageStatisticActivity r0) {
        return r0.lastCancelable;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m16197$$Nest$fgetlayoutManager(MessageStatisticActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m16198$$Nest$fgetlistView(MessageStatisticActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetloading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16199$$Nest$fgetloading(MessageStatisticActivity r0) {
        return r0.loading;
    }

    /* renamed from: -$$Nest$fgetloadingRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16200$$Nest$fgetloadingRow(MessageStatisticActivity r0) {
        return r0.loadingRow;
    }

    /* renamed from: -$$Nest$fgetmessageObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m16201$$Nest$fgetmessageObject(MessageStatisticActivity r0) {
        return r0.messageObject;
    }

    /* renamed from: -$$Nest$fgetmessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16202$$Nest$fgetmessages(MessageStatisticActivity r0) {
        return r0.messages;
    }

    /* renamed from: -$$Nest$fgetoverviewHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16203$$Nest$fgetoverviewHeaderRow(MessageStatisticActivity r0) {
        return r0.overviewHeaderRow;
    }

    /* renamed from: -$$Nest$fgetoverviewRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16204$$Nest$fgetoverviewRow(MessageStatisticActivity r0) {
        return r0.overviewRow;
    }

    /* renamed from: -$$Nest$fgetprogressLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m16205$$Nest$fgetprogressLayout(MessageStatisticActivity r0) {
        return r0.progressLayout;
    }

    /* renamed from: -$$Nest$fgetpublicChats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16206$$Nest$fgetpublicChats(MessageStatisticActivity r0) {
        return r0.publicChats;
    }

    /* renamed from: -$$Nest$fgetreactionsByEmotionChartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16207$$Nest$fgetreactionsByEmotionChartRow(MessageStatisticActivity r0) {
        return r0.reactionsByEmotionChartRow;
    }

    /* renamed from: -$$Nest$fgetreactionsByEmotionData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StatisticActivity.ChartViewData m16208$$Nest$fgetreactionsByEmotionData(MessageStatisticActivity r0) {
        return r0.reactionsByEmotionData;
    }

    /* renamed from: -$$Nest$fgetrecentPostInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StatisticActivity.RecentPostInfo m16209$$Nest$fgetrecentPostInfo(MessageStatisticActivity r0) {
        return r0.recentPostInfo;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16210$$Nest$fgetrowCount(MessageStatisticActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetstartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16211$$Nest$fgetstartRow(MessageStatisticActivity r0) {
        return r0.startRow;
    }

    /* renamed from: -$$Nest$fgetstatsLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16212$$Nest$fgetstatsLoaded(MessageStatisticActivity r0) {
        return r0.statsLoaded;
    }

    /* renamed from: -$$Nest$fputlastCancelable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16213$$Nest$fputlastCancelable(MessageStatisticActivity r0, StatisticActivity.ZoomCancelable r1) {
        r0.lastCancelable = r1;
    }

    /* renamed from: -$$Nest$fputsharedUi, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16214$$Nest$fputsharedUi(MessageStatisticActivity r0, BaseChartView.SharedUiComponents r1) {
        r0.sharedUi = r1;
    }

    /* renamed from: -$$Nest$mcheckIsDeletedStory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16215$$Nest$mcheckIsDeletedStory(MessageStatisticActivity r0, MessageObject r1) {
        return r0.checkIsDeletedStory(r1);
    }

    /* renamed from: -$$Nest$mloadChats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16216$$Nest$mloadChats(MessageStatisticActivity r0, int r1) {
        r0.loadChats(r1);
    }

    public MessageStatisticActivity(MessageObject r3) {
        this.childDataCache = new LruCache(15);
        this.messages = new ArrayList();
        this.nextOffset = null;
        this.shadowDivideCells = new ArraySet();
        this.showProgressbar = new AnonymousClass1(this);
        this.messageObject = r3;
        if (r3.messageOwner.fwd_from != null) goto L5;
        this.chatId = r3.getChatId();
        this.messageId = this.messageObject.getId();
    L6:
        this.chat = getMessagesController().getChatFull(this.chatId);
        return;
    L5:
        this.chatId = -r3.getFromChatId();
        this.messageId = this.messageObject.messageOwner.fwd_msg_id;
        goto L6
    }

    public MessageStatisticActivity(MessageObject r3, long r4, boolean r6) {
        this.childDataCache = new LruCache(15);
        this.messages = new ArrayList();
        this.nextOffset = null;
        this.shadowDivideCells = new ArraySet();
        this.showProgressbar = new AnonymousClass1(this);
        this.messageObject = r3;
        this.messageId = 0;
        this.chatId = r4;
        this.chat = getMessagesController().getChatFull(r4);
        this.needActionbarMenu = r6;
    }

    public MessageStatisticActivity(StatisticActivity.RecentPostInfo r2, long r3, boolean r5) {
        this(r2.message, r3, r5);
        this.recentPostInfo = r2;
    }

    public static /* synthetic */ int access$000(MessageStatisticActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(MessageStatisticActivity r0) {
        return r0.classGuid;
    }

    public static /* synthetic */ int access$200(MessageStatisticActivity r0) {
        return r0.currentAccount;
    }

    private boolean checkIsDeletedStory(MessageObject r3) {
        if (r3 != null) goto L5;
    L11:
        return false;
    L5:
        if (r3.isStory() == false) goto L11;
        if ((r3.storyItem instanceof TL_stories.TL_storyItemDeleted) == false) goto L11;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.story_bomb1, LocaleController.getString(R.string.StoryNotFound)).show();
        return true;
    }

    private /* synthetic */ void lambda$createView$0(View r4, int r5) {
        int r42 = this.startRow;
        if (r5 >= r42) goto L5;
        return;
    L5:
        if (r5 >= this.endRow) goto L22;
        MessageObject r43 = this.messages.get(r5 - r42);
        if (r43.isStory() == true) goto L9;
        long r0 = MessageObject.getDialogId(r43.messageOwner);
        Bundle r52 = new Bundle();
        if (DialogObject.isUserDialog(r0) == false) goto L16;
        r52.putLong("user_id", r0);
    L17:
        r52.putInt("message_id", r43.getId());
        r52.putBoolean("need_remove_previous_same_chat_activity", false);
        if (getMessagesController().checkCanOpenChat(r52, this) == false) goto L23;
        presentFragment(new ChatActivity(r52));
        return;
    L23:
        return;
    L16:
        r52.putLong("chat_id", -r0);
        goto L17
    L9:
        if (checkIsDeletedStory(r43) == true) goto L24;
        getOrCreateStoryViewer().open(getContext(), r43.storyItem, StoriesListPlaceProvider.of(this.listView));
        return;
    L24:
        return;
    }

    private /* synthetic */ void lambda$createView$1(MessageObject r1, boolean r2, long r3, DialogInterface r5, int r6) {
        if (r1.isStory() == false) goto L9;
        if (r2 == false) goto L6;
        BaseFragment r12 = ProfileActivity.of(r3);
    L7:
        presentFragment(r12);
        return;
    L6:
        r12 = ChatActivity.of(r3);
        goto L7
    L9:
        Bundle r52 = new Bundle();
        if (r2 == false) goto L12;
        r52.putLong("user_id", r3);
    L13:
        r52.putInt("message_id", r1.getId());
        r52.putBoolean("need_remove_previous_same_chat_activity", false);
        if (getMessagesController().checkCanOpenChat(r52, this) == false) goto L17;
        presentFragment(new ChatActivity(r52));
        return;
    L17:
        return;
    L12:
        r52.putLong("chat_id", -r3);
        goto L13
    }

    private /* synthetic */ boolean lambda$createView$2(View r10, int r11) {
        if (r11 >= this.startRow) goto L5;
    L21:
        return false;
    L5:
        if (r11 >= this.endRow) goto L21;
        r10.performHapticFeedback(0, 2);     // Catch: Exception -> L22
    L8:
        MessageObject r4 = this.messages.get(r11 - this.startRow);
        long r6 = MessageObject.getDialogId(r4.messageOwner);
        boolean r5 = DialogObject.isUserDialog(r6);
        ArrayList r102 = new ArrayList();
        ArrayList r112 = new ArrayList();
        ArrayList r0 = new ArrayList();
        AlertDialog.Builder r8 = new AlertDialog.Builder(getParentActivity(), getResourceProvider());
        if (r4.isStory() == false) goto L19;
        if (r5 == false) goto L13;
        int r2 = R.string.OpenProfile;
    L14:
        r102.add(LocaleController.getString(r2));
        if (r5 == false) goto L17;
        int r22 = R.drawable.msg_openprofile;
    L18:
        r0.add(Integer.valueOf(r22));
    L20:
        r112.add(0);
        r8.setItems((CharSequence[]) r102.toArray(new CharSequence[r112.size()]), AndroidUtilities.toIntArray(r0), new MessageStatisticActivity$$ExternalSyntheticLambda8(this, r4, r5, r6));
        showDialog(r8.create());
        goto L21
    L17:
        r22 = R.drawable.msg_channel;
        goto L18
    L13:
        r2 = R.string.OpenChannel2;
        goto L14
    L19:
        r102.add(LocaleController.getString(R.string.ViewMessage));
        r0.add(Integer.valueOf(R.drawable.msg_msgbubble3));
        goto L20
    }

    private /* synthetic */ void lambda$createView$3(View r5) {
        if (this.messageObject.isStory() == false) goto L6;
        return;
    L6:
        if (getParentLayout().getFragmentStack().size() <= 1) goto L13;
        BaseFragment r52 = getParentLayout().getFragmentStack().get(getParentLayout().getFragmentStack().size() - 2);
        if ((r52 instanceof ChatActivity) == false) goto L13;
        if (((ChatActivity) r52).getCurrentChat().id != this.chatId) goto L13;
        finishFragment();
        return;
    L13:
        Bundle r53 = new Bundle();
        r53.putLong("chat_id", this.chatId);
        r53.putInt("message_id", this.messageId);
        r53.putBoolean("need_remove_previous_same_chat_activity", false);
        presentFragment(new ChatActivity(r53));
    }

    private /* synthetic */ void lambda$getThemeDescriptions$13() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L21;
        int r02 = r0.getChildCount();
        int r1 = 0;
        int r2 = 0;
    L5:
        RecyclerListView r3 = this.listView;
        if (r2 >= r02) goto L8;
        recolorRecyclerItem(r3.getChildAt(r2));
        r2 = r2 + 1;
        goto L5
    L8:
        int r03 = r3.getHiddenChildCount();
        int r22 = 0;
    L9:
        RecyclerListView r32 = this.listView;
        if (r22 >= r03) goto L12;
        recolorRecyclerItem(r32.getHiddenChildAt(r22));
        r22 = r22 + 1;
        goto L9
    L12:
        int r04 = r32.getCachedChildCount();
        int r23 = 0;
    L13:
        RecyclerListView r33 = this.listView;
        if (r23 >= r04) goto L16;
        recolorRecyclerItem(r33.getCachedChildAt(r23));
        r23 = r23 + 1;
        goto L13
    L16:
        int r05 = r33.getAttachedScrapChildCount();
    L17:
        RecyclerListView r24 = this.listView;
        if (r1 >= r05) goto L20;
        recolorRecyclerItem(r24.getAttachedScrapChildAt(r1));
        r1 = r1 + 1;
        goto L17
    L20:
        r24.getRecycledViewPool().clear();
    L21:
        BaseChartView.SharedUiComponents r06 = this.sharedUi;
        if (r06 == null) goto L24;
        r06.invalidate();
    L24:
        View r07 = this.avatarContainer.getSubtitleTextView();
        if ((r07 instanceof SimpleTextView) == false) goto L32;
        ((SimpleTextView) r07).setLinkTextColor(Theme.getColor(Theme.key_player_actionBarSubtitle, getResourceProvider()));
        return;
    }

    private /* synthetic */ void lambda$loadChats$4(TLRPC.TL_error r8, TLObject r9) {
        if (r8 != null) goto L29;
        TL_stats.TL_publicForwards r92 = (TL_stats.TL_publicForwards) r9;
        if ((r92.flags & 1) == 0) goto L7;
        this.nextOffset = r92.next_offset;
    L8:
        int r82 = r92.count;
        if (r82 == 0) goto L12;
        this.publicChats = r82;
    L15:
        if (this.nextOffset != null) goto L17;
        boolean r83 = true;
    L18:
        this.endReached = r83;
        getMessagesController().putChats(r92.chats, false);
        getMessagesController().putUsers(r92.users, false);
        ArrayList<TL_stats.PublicForward> r84 = r92.forwards;
        int r93 = r84.size();
        int r2 = 0;
    L19:
        if (r2 >= r93) goto L26;
        TL_stats.PublicForward r3 = r84.get(r2);
        r2 = r2 + 1;
        TL_stats.PublicForward r32 = r3;
        if ((r32 instanceof TL_stories.TL_publicForwardStory) == true) goto L22;
        if ((r32 instanceof TL_stats.TL_publicForwardMessage) == false) goto L19;
        this.messages.add(new MessageObject(this.currentAccount, ((TL_stats.TL_publicForwardMessage) r32).message, false, true));
        goto L19
    L22:
        TL_stories.TL_publicForwardStory r33 = (TL_stories.TL_publicForwardStory) r32;
        r33.story.dialogId = DialogObject.getPeerDialogId(r33.peer);
        TL_stories.StoryItem r4 = r33.story;
        r4.messageId = r4.id;
        MessageObject r42 = new MessageObject(this.currentAccount, r33.story);
        r42.generateThumbs(false);
        this.messages.add(r42);
        goto L19
    L26:
        EmptyTextProgressView r85 = this.emptyView;
        if (r85 == null) goto L29;
        r85.showTextView();
        goto L29
    L17:
        r83 = false;
        goto L18
    L12:
        if (this.publicChats != 0) goto L15;
        this.publicChats = r92.forwards.size();
        goto L15
    L7:
        this.nextOffset = null;
    L29:
        this.firstLoaded = true;
        this.loading = false;
        updateRows();
    }

    private /* synthetic */ void lambda$loadChats$5(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new MessageStatisticActivity$$ExternalSyntheticLambda7(this, r3, r2));
    }

    private /* synthetic */ void lambda$loadChats$6(TLRPC.TL_error r8, TLObject r9) {
        if (r8 != null) goto L29;
        TL_stats.TL_publicForwards r92 = (TL_stats.TL_publicForwards) r9;
        if ((r92.flags & 1) == 0) goto L7;
        this.nextOffset = r92.next_offset;
    L8:
        int r82 = r92.count;
        if (r82 == 0) goto L12;
        this.publicChats = r82;
    L15:
        if (this.nextOffset != null) goto L17;
        boolean r83 = true;
    L18:
        this.endReached = r83;
        getMessagesController().putChats(r92.chats, false);
        getMessagesController().putUsers(r92.users, false);
        ArrayList<TL_stats.PublicForward> r84 = r92.forwards;
        int r93 = r84.size();
        int r2 = 0;
    L19:
        if (r2 >= r93) goto L26;
        TL_stats.PublicForward r3 = r84.get(r2);
        r2 = r2 + 1;
        TL_stats.PublicForward r32 = r3;
        if ((r32 instanceof TL_stories.TL_publicForwardStory) == true) goto L22;
        if ((r32 instanceof TL_stats.TL_publicForwardMessage) == false) goto L19;
        this.messages.add(new MessageObject(this.currentAccount, ((TL_stats.TL_publicForwardMessage) r32).message, false, true));
        goto L19
    L22:
        TL_stories.TL_publicForwardStory r33 = (TL_stories.TL_publicForwardStory) r32;
        r33.story.dialogId = DialogObject.getPeerDialogId(r33.peer);
        TL_stories.StoryItem r4 = r33.story;
        r4.messageId = r4.id;
        MessageObject r42 = new MessageObject(this.currentAccount, r33.story);
        r42.generateThumbs(false);
        this.messages.add(r42);
        goto L19
    L26:
        EmptyTextProgressView r85 = this.emptyView;
        if (r85 == null) goto L29;
        r85.showTextView();
        goto L29
    L17:
        r83 = false;
        goto L18
    L12:
        if (this.publicChats != 0) goto L15;
        this.publicChats = r92.forwards.size();
        goto L15
    L7:
        this.nextOffset = null;
    L29:
        this.firstLoaded = true;
        this.loading = false;
        updateRows();
    }

    private /* synthetic */ void lambda$loadChats$7(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new MessageStatisticActivity$$ExternalSyntheticLambda9(this, r3, r2));
    }

    private /* synthetic */ void lambda$loadStat$10(String r9, TL_stats.TL_loadAsyncGraph r10, TLObject r11, TLRPC.TL_error r12) {
        ChartData r1 = null;
        if ((r11 instanceof TL_stats.TL_statsGraph) == false) goto L11;
        r1 = StatisticActivity.createChartData(new JSONObject(((TL_stats.TL_statsGraph) r11).json.data), 1, false);     // Catch: JSONException -> L7
    L13:
        AndroidUtilities.runOnUIThread(new MessageStatisticActivity$$ExternalSyntheticLambda13(this, r12, r1, r9, r10));
        return;
    L7:
        e = move-exception;
        e.printStackTrace();
        goto L13
    L11:
        if ((r11 instanceof TL_stats.TL_statsGraphError) == false) goto L13;
        AndroidUtilities.runOnUIThread(new MessageStatisticActivity$$ExternalSyntheticLambda12(this, r11));
        goto L13
    }

    private /* synthetic */ void lambda$loadStat$11(TLRPC.TL_error r13, TLObject r14) {
        this.statsLoaded = true;
        if (r13 == null) goto L7;
        updateRows();
        return;
    L7:
        if ((r14 instanceof TL_stories.TL_stats_storyStats) == false) goto L9;
        TL_stories.TL_stats_storyStats r142 = (TL_stories.TL_stats_storyStats) r14;
        TL_stats.StatsGraph r132 = r142.views_graph;
        TL_stats.StatsGraph r143 = r142.reactions_by_emotion_graph;
    L10:
        this.interactionsViewData = StatisticActivity.createViewData(r132, LocaleController.getString(R.string.ViewsAndSharesChartTitle), 1, false);
        this.reactionsByEmotionData = StatisticActivity.createViewData(r143, LocaleController.getString(R.string.ReactionsByEmotionChartTitle), 2, false);
        StatisticActivity.ChartViewData r133 = this.interactionsViewData;
        if (r133 != null) goto L13;
    L16:
        updateRows();
        return;
    L13:
        if (r133.chartData.x.length > 5) goto L16;
        this.statsLoaded = false;
        TL_stats.TL_loadAsyncGraph r4 = new TL_stats.TL_loadAsyncGraph();
        StatisticActivity.ChartViewData r134 = this.interactionsViewData;
        r4.token = r134.zoomToken;
        long[] r135 = r134.chartData.x;
        r4.x = r135[r135.length - 1];
        r4.flags |= 1;
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(ConnectionsManager.getInstance(this.currentAccount).sendRequest(r4, new MessageStatisticActivity$$ExternalSyntheticLambda11(this, this.interactionsViewData.zoomToken + "_" + r4.x, r4), null, null, 0, this.chat.stats_dc, 1, true), this.classGuid);
        return;
    L9:
        TL_stats.TL_messageStats r144 = (TL_stats.TL_messageStats) r14;
        r132 = r144.views_graph;
        r143 = r144.reactions_by_emotion_graph;
        goto L10
    }

    private /* synthetic */ void lambda$loadStat$12(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new MessageStatisticActivity$$ExternalSyntheticLambda10(this, r3, r2));
    }

    private /* synthetic */ void lambda$loadStat$8(TLObject r2) {
        if (getParentActivity() == null) goto L6;
        Toast.makeText(getParentActivity(), ((TL_stats.TL_statsGraphError) r2).error, 1).show();
        return;
    }

    private /* synthetic */ void lambda$loadStat$9(TLRPC.TL_error r2, ChartData r3, String r4, TL_stats.TL_loadAsyncGraph r5) {
        this.statsLoaded = true;
        if (r2 != null) goto L8;
        if (r3 == null) goto L8;
        this.childDataCache.put(r4, r3);
        StatisticActivity.ChartViewData r22 = this.interactionsViewData;
        r22.childChartData = r3;
        r22.activeZoom = r5.x;
        updateRows();
        return;
    L8:
        updateRows();
    }

    private void loadChats(int r12) {
        if (this.loading == false) goto L5;
        return;
    L5:
        this.loading = true;
        ListAdapter r0 = this.listViewAdapter;
        if (r0 == null) goto L8;
        r0.notifyDataSetChanged();
    L8:
        boolean r02 = this.messageObject.isStory();
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r02 == false) goto L16;
        TL_stats.TL_getStoryPublicForwards r3 = new TL_stats.TL_getStoryPublicForwards();
        r3.limit = r12;
        r3.id = this.messageObject.storyItem.id;
        r3.peer = getMessagesController().getInputPeer(-this.chatId);
        String r122 = this.nextOffset;
        if (r122 == null) goto L14;
        r1 = r122;
    L14:
        r3.offset = r1;
        int r123 = getConnectionsManager().sendRequest(r3, new MessageStatisticActivity$$ExternalSyntheticLambda5(this), null, null, 0, this.chat.stats_dc, 1, true);
        getConnectionsManager().bindRequestToGuid(r123, this.classGuid);
        return;
    L16:
        TL_stats.TL_getMessagePublicForwards r2 = new TL_stats.TL_getMessagePublicForwards();
        r2.limit = r12;
        MessageObject r124 = this.messageObject;
        TLRPC.MessageFwdHeader r03 = r124.messageOwner.fwd_from;
        if (r03 == null) goto L19;
        r2.msg_id = r03.saved_from_msg_id;
        r2.channel = getMessagesController().getInputChannel(-this.messageObject.getFromChatId());
    L20:
        String r125 = this.nextOffset;
        if (r125 == null) goto L24;
        r1 = r125;
    L24:
        r2.offset = r1;
        int r126 = getConnectionsManager().sendRequest(r2, new MessageStatisticActivity$$ExternalSyntheticLambda6(this), null, null, 0, this.chat.stats_dc, 1, true);
        getConnectionsManager().bindRequestToGuid(r126, this.classGuid);
        return;
    L19:
        r2.msg_id = r124.getId();
        r2.channel = getMessagesController().getInputChannel(-this.messageObject.getDialogId());
        goto L20
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void loadStat() {
        if (this.messageObject.isStory() == false) goto L6;
        TL_stories.TL_stats_getStoryStats r0 = new TL_stories.TL_stats_getStoryStats();
        r0.id = this.messageObject.storyItem.id;
        r0.peer = getMessagesController().getInputPeer(-this.chatId);
        TL_stats.TL_getMessageStats r02 = r0;
    L5:
        TL_stats.TL_getMessageStats r3 = r02;
        getConnectionsManager().sendRequest(r3, new MessageStatisticActivity$$ExternalSyntheticLambda4(this), null, null, 0, this.chat.stats_dc, 1, true);
        return;
    L6:
        TL_stats.TL_getMessageStats r03 = new TL_stats.TL_getMessageStats();
        MessageObject r1 = this.messageObject;
        TLRPC.MessageFwdHeader r2 = r1.messageOwner.fwd_from;
        if (r2 == null) goto L9;
        r03.msg_id = r2.saved_from_msg_id;
        r03.channel = getMessagesController().getInputChannel(-this.messageObject.getFromChatId());
        r02 = r03;
        goto L5
    L9:
        r03.msg_id = r1.getId();
        r03.channel = getMessagesController().getInputChannel(-this.messageObject.getDialogId());
        r02 = r03;
        goto L5
    }

    private void recolorRecyclerItem(View r6) {
        if ((r6 instanceof ManageChatUserCell) == false) goto L6;
        ((ManageChatUserCell) r6).update(0);
    L18:
        if ((r6 instanceof EmptyCell) == false) goto L21;
        r6.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, getResourceProvider()));
        return;
    L21:
        return;
    L6:
        if ((r6 instanceof StatisticActivity.BaseChartCell) == false) goto L9;
        ((StatisticActivity.BaseChartCell) r6).recolor();
        r6.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, getResourceProvider()));
        goto L18
    L9:
        if ((r6 instanceof ShadowSectionCell) == false) goto L12;
        Drawable r0 = Theme.getThemedDrawableByKey(ApplicationLoader.applicationContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow);
        CombinedDrawable r3 = new CombinedDrawable(new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundGray, getResourceProvider())), r0, 0, 0);
        r3.setFullsize(true);
        r6.setBackground(r3);
        goto L18
    L12:
        if ((r6 instanceof ChartHeaderView) == false) goto L15;
        ((ChartHeaderView) r6).recolor();
        goto L18
    L15:
        if ((r6 instanceof OverviewCell) == false) goto L18;
        OverviewCell.m16218$$Nest$mupdateColors((OverviewCell) r6);
        r6.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, getResourceProvider()));
        goto L18
    }

    private void setAvatarAndTitle() {
        boolean r0 = this.messageObject.isStory();
        ChatAvatarContainer r1 = this.avatarContainer;
        if (r0 == false) goto L8;
        r1.setTitle(LocaleController.getString(R.string.StoryStatistics));
        this.avatarContainer.hideSubtitle();
        ChatAvatarContainer r02 = this.avatarContainer;
        r02.allowDrawStories = true;
        r02.setStoriesForceState(1);
        ArrayList<TLRPC.PhotoSize> r03 = this.messageObject.photoThumbs;
        if (r03 == null) goto L14;
        TLRPC.PhotoSize r04 = FileLoader.getClosestPhotoSizeWithSize(r03, AndroidUtilities.getPhotoSize());
        TLRPC.PhotoSize r12 = FileLoader.getClosestPhotoSizeWithSize(this.messageObject.photoThumbs, 50);
        this.avatarContainer.getAvatarImageView().setImage(ImageLocation.getForObject(r04, this.messageObject.photoThumbsObject), "50_50", ImageLocation.getForObject(r12, this.messageObject.photoThumbsObject), "b1", 0, this.messageObject);
        this.avatarContainer.setClipChildren(false);
        this.avatarContainer.getAvatarImageView().setScaleX(0.96f);
        this.avatarContainer.getAvatarImageView().setScaleY(0.96f);
        return;
    L14:
        return;
    L8:
        r1.setTitle(LocaleController.getString(R.string.PostStatistics));
        TLRPC.Chat r05 = getMessagesController().getChat(Long.valueOf(this.chatId));
        if (r05 != null) goto L11;
        return;
    L11:
        if (this.hasThumb == true) goto L16;
        this.avatarContainer.setChatAvatar(r05);
        return;
    }

    private void updateMenu() {
        if (this.needActionbarMenu == false) goto L13;
        TLRPC.ChatFull r0 = this.chat;
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.can_view_stats == false) goto L12;
        ActionBarMenu r3 = this.actionBar.createMenu();
        r3.clearItems();
        r3.addItem(0, R.drawable.ic_ab_other).addSubItem(1, R.drawable.msg_stats, LocaleController.getString(R.string.ViewChannelStats));
        return;
    L12:
        return;
    }

    private void updateRows() {
        this.shadowDivideCells.clear();
        this.headerRow = -1;
        this.startRow = -1;
        this.endRow = -1;
        this.loadingRow = -1;
        this.interactionsChartRow = -1;
        this.reactionsByEmotionChartRow = -1;
        this.overviewHeaderRow = -1;
        this.overviewRow = -1;
        this.rowCount = 0;
        if (this.firstLoaded == true) goto L5;
    L20:
        ListAdapter r4 = this.listViewAdapter;
        if (r4 == null) goto L24;
        r4.notifyDataSetChanged();
        return;
    L24:
        return;
    L5:
        if (this.statsLoaded == false) goto L20;
        AndroidUtilities.cancelRunOnUIThread(this.showProgressbar);
        if (this.listContainer.getVisibility() != 8) goto L9;
        this.progressLayout.animate().alpha(0.0f).setListener(new AnonymousClass2(this));
        this.listContainer.setVisibility(0);
        this.listContainer.setAlpha(0.0f);
        this.listContainer.animate().alpha(1.0f).start();
    L9:
        int r0 = this.rowCount;
        this.overviewHeaderRow = r0;
        int r2 = r0 + 2;
        this.overviewRow = r0 + 1;
        ArraySet<Integer> r1 = this.shadowDivideCells;
        this.rowCount = r0 + 3;
        r1.add(Integer.valueOf(r2));
        if (this.interactionsViewData == null) goto L13;
        int r02 = this.rowCount;
        int r12 = r02 + 1;
        this.interactionsChartRow = r02;
        ArraySet<Integer> r22 = this.shadowDivideCells;
        this.rowCount = r02 + 2;
        r22.add(Integer.valueOf(r12));
    L13:
        if (this.reactionsByEmotionData == null) goto L16;
        int r03 = this.rowCount;
        int r13 = r03 + 1;
        this.reactionsByEmotionChartRow = r03;
        ArraySet<Integer> r23 = this.shadowDivideCells;
        this.rowCount = r03 + 2;
        r23.add(Integer.valueOf(r13));
    L16:
        if (this.messages.isEmpty() == true) goto L20;
        int r04 = this.rowCount;
        int r14 = r04 + 1;
        this.rowCount = r14;
        this.headerRow = r04;
        this.startRow = r14;
        int r15 = r14 + this.messages.size();
        this.endRow = r15;
        int r05 = r15 + 1;
        this.emptyRow = r15;
        ArraySet<Integer> r24 = this.shadowDivideCells;
        this.rowCount = r15 + 2;
        r24.add(Integer.valueOf(r05));
        if (this.endReached == true) goto L20;
        int r06 = this.rowCount;
        this.rowCount = r06 + 1;
        this.loadingRow = r06;
        goto L20
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r22) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        FrameLayout r2 = new FrameLayout(r22);
        this.fragmentView = r2;
        r2.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, getResourceProvider()));
        FrameLayout r23 = (FrameLayout) this.fragmentView;
        EmptyTextProgressView r3 = new EmptyTextProgressView(r22);
        this.emptyView = r3;
        r3.setText(LocaleController.getString(R.string.NoResult));
        this.emptyView.setVisibility(8);
        LinearLayout r32 = new LinearLayout(r22);
        this.progressLayout = r32;
        r32.setOrientation(1);
        RLottieImageView r33 = new RLottieImageView(r22);
        this.imageView = r33;
        r33.setAutoRepeat(true);
        this.imageView.setAnimation(R.raw.statistic_preload, 120, 120);
        this.imageView.playAnimation();
        TextView r34 = new TextView(r22);
        r34.setTextSize(1, 20.0f);
        r34.setTypeface(AndroidUtilities.bold());
        int r6 = Theme.key_player_actionBarTitle;
        r34.setTextColor(Theme.getColor(r6, getResourceProvider()));
        r34.setTag(Integer.valueOf(r6));
        r34.setText(LocaleController.getString(R.string.LoadingStats));
        r34.setGravity(1);
        TextView r7 = new TextView(r22);
        r7.setTextSize(1, 15.0f);
        int r8 = Theme.key_player_actionBarSubtitle;
        r7.setTextColor(Theme.getColor(r8, getResourceProvider()));
        r7.setTag(Integer.valueOf(r8));
        r7.setText(LocaleController.getString(R.string.LoadingStatsDescription));
        r7.setGravity(1);
        this.progressLayout.addView(this.imageView, LayoutHelper.createLinear(120, 120, 1, 0, 0, 0, 20));
        this.progressLayout.addView(r34, LayoutHelper.createLinear(-2, -2, 1, 0, 0, 0, 10));
        this.progressLayout.addView(r7, LayoutHelper.createLinear(-2, -2, 1));
        float r72 = 0.0f;
        this.progressLayout.setAlpha(0.0f);
        r23.addView(this.progressLayout, LayoutHelper.createFrame(240, -2.0f, 17, 0.0f, 0.0f, 0.0f, 30.0f));
        RecyclerListView r35 = new RecyclerListView(r22, getResourceProvider());
        this.listView = r35;
        r35.setSections();
        RecyclerListView r36 = this.listView;
        LinearLayoutManager r9 = new LinearLayoutManager(r22, 1, false);
        this.layoutManager = r9;
        r36.setLayoutManager(r9);
        ((SimpleItemAnimator) this.listView.getItemAnimator()).setSupportsChangeAnimations(false);
        RecyclerListView r37 = this.listView;
        ListAdapter r92 = new ListAdapter(this, r22);
        this.listViewAdapter = r92;
        r37.setAdapter(r92);
        RecyclerListView r38 = this.listView;
        if (LocaleController.isRTL == false) goto L5;
        int r93 = 1;
    L6:
        r38.setVerticalScrollbarPosition(r93);
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setOnItemClickListener(new MessageStatisticActivity$$ExternalSyntheticLambda0(this));
        this.listView.setOnItemLongClickListener(new MessageStatisticActivity$$ExternalSyntheticLambda1(this));
        this.listView.setOnScrollListener(new AnonymousClass3(this));
        this.emptyView.showTextView();
        FrameLayout r39 = new FrameLayout(r22);
        this.listContainer = r39;
        r39.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listContainer.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        this.listContainer.setVisibility(8);
        r23.addView(this.listContainer, LayoutHelper.createFrame(-1, -1.0f));
        AndroidUtilities.runOnUIThread(this.showProgressbar, 300);
        updateRows();
        this.listView.setEmptyView(this.emptyView);
        TLRPC.PhotoSize r310 = null;
        this.avatarContainer = new AnonymousClass4(this, r22, null, false);
        ImageReceiver r1 = new ImageReceiver();
        this.thumbImage = r1;
        r1.setParentView(this.avatarContainer);
        this.thumbImage.setRoundRadius(AndroidUtilities.dp(9.0f));
        this.hasThumb = false;
        int r24 = 50;
        if (this.messageObject.isStory() == true) goto L66;
        if (this.messageObject.needDrawBluredPreview() == false) goto L11;
    L48:
        boolean r12 = TextUtils.isEmpty(this.messageObject.caption);
        MessageObject r311 = this.messageObject;
        if (r12 == true) goto L51;
        CharSequence r13 = r311.caption;
    L59:
        if (this.messageObject.isVideo() == false) goto L61;
    L64:
        this.avatarContainer.hideSubtitle();
        goto L66
    L61:
        if (this.messageObject.isPhoto() == true) goto L64;
        this.avatarContainer.setSubtitle(r13);
        goto L66
    L51:
        boolean r14 = TextUtils.isEmpty(r311.messageOwner.message);
        MessageObject r312 = this.messageObject;
        if (r14 == true) goto L57;
        CharSequence r15 = r312.messageText;
        if (r15.length() <= 150) goto L56;
        r15 = r15.subSequence(0, 150);
    L56:
        r13 = Emoji.replaceEmoji(r15, this.avatarContainer.getSubtitlePaint().getFontMetricsInt(), false);
        goto L59
    L57:
        r13 = r312.messageText;
        goto L59
    L11:
        if (this.messageObject.isPhoto() == true) goto L17;
        if (this.messageObject.isNewGif() == true) goto L17;
        if (this.messageObject.isVideo() == false) goto L48;
    L17:
        if (this.messageObject.isWebpage() == false) goto L19;
        String r16 = this.messageObject.messageOwner.media.webpage.type;
    L21:
        if (Common.ASSET_APP.equals(r16) == true) goto L48;
        if ("profile".equals(r16) == true) goto L48;
        if ("article".equals(r16) == true) goto L48;
        if (r16 != null) goto L28;
    L29:
        TLRPC.PhotoSize r17 = FileLoader.getClosestPhotoSizeWithSize(this.messageObject.photoThumbs, 50);
        TLRPC.PhotoSize r4 = FileLoader.getClosestPhotoSizeWithSize(this.messageObject.photoThumbs, AndroidUtilities.getPhotoSize());
        if (r17 == r4) goto L33;
        r310 = r4;
    L33:
        if (r17 == null) goto L48;
        this.hasThumb = true;
        this.drawPlay = this.messageObject.isVideo();
        String r42 = FileLoader.getAttachFileName(r310);
        if (this.messageObject.mediaExists == false) goto L37;
    L42:
        MessageObject r43 = this.messageObject;
        if (r43.type != 1) goto L46;
        if (r310 == null) goto L46;
        int r5 = r310.size;
    L47:
        this.thumbImage.setImage(ImageLocation.getForObject(r310, r43.photoThumbsObject), "50_50", ImageLocation.getForObject(r17, this.messageObject.photoThumbsObject), "50_50", r5, null, this.messageObject, 0);
    L46:
        r5 = 0;
        goto L47
    L37:
        if (DownloadController.getInstance(this.currentAccount).canDownloadMedia(this.messageObject) == true) goto L42;
        if (FileLoader.getInstance(this.currentAccount).isLoadingFile(r42) == true) goto L42;
        this.thumbImage.setImage(null, null, ImageLocation.getForObject(r17, this.messageObject.photoThumbsObject), "50_50", null, this.messageObject, 0);
        goto L48
    L28:
        if (r16.startsWith("telegram_") == true) goto L48;
    L19:
        r16 = null;
    L66:
        if (this.hasThumb == false) goto L68;
    L71:
        this.avatarContainer.setRightAvatarPadding(-AndroidUtilities.dp(3.0f));
    L72:
        ActionBar r18 = this.actionBar;
        ChatAvatarContainer r313 = this.avatarContainer;
        if (this.inPreviewMode == true) goto L75;
        r72 = r24;
    L75:
        r18.addView(r313, 0, LayoutHelper.createFrame(-2, -1.0f, 51, r72, 0.0f, 40.0f, 0.0f));
        setAvatarAndTitle();
        this.avatarContainer.setTitleColors(Theme.getColor(r6, getResourceProvider()), Theme.getColor(r8, getResourceProvider()));
        View r19 = this.avatarContainer.getSubtitleTextView();
        if ((r19 instanceof SimpleTextView) == false) goto L78;
        ((SimpleTextView) r19).setLinkTextColor(Theme.getColor(r8, getResourceProvider()));
    L78:
        this.actionBar.setItemsColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, getResourceProvider()), false);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarActionModeDefaultSelector, getResourceProvider()), false);
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass5(this));
        this.avatarContainer.setOnClickListener(new MessageStatisticActivity$$ExternalSyntheticLambda2(this));
        updateMenu();
        return this.fragmentView;
    L68:
        if (this.messageObject.isStory() == true) goto L71;
        r24 = 56;
        goto L72
    L5:
        r93 = 2;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.chatInfoDidLoad) goto L10;
        TLRPC.ChatFull r32 = (TLRPC.ChatFull) r5[0];
        if (this.chat == null) goto L7;
        return;
    L7:
        if (r32.id != this.chatId) goto L12;
        setAvatarAndTitle();
        this.chat = r32;
        loadStat();
        loadChats(100);
        updateMenu();
        return;
    L12:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        MessageStatisticActivity$$ExternalSyntheticLambda3 r8 = new MessageStatisticActivity$$ExternalSyntheticLambda3(this);
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{HeaderCell.class, ManageChatUserCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        ChatAvatarContainer r4 = this.avatarContainer;
        View r5 = null;
        if (r4 == null) goto L5;
        SimpleTextView r12 = r4.getTitleTextView();
    L6:
        r1.add(new ThemeDescription(r12, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_player_actionBarTitle));
        ChatAvatarContainer r42 = this.avatarContainer;
        if (r42 == null) goto L9;
        r5 = r42.getSubtitleTextView();
    L9:
        r1.add(new ThemeDescription(r5, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, Theme.key_player_actionBarSubtitle, null));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_statisticChartLineEmpty));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"nameTextView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"statusColor"}, null, null, r8, Theme.key_windowBackgroundWhiteGrayText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"statusOnlineColor"}, null, null, r8, Theme.key_windowBackgroundWhiteBlueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUBACKGROUND, null, null, null, null, Theme.key_actionBarDefaultSubmenuBackground));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM, null, null, null, null, Theme.key_actionBarDefaultSubmenuItem));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM | ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_actionBarDefaultSubmenuItemIcon));
        StatisticActivity.putColorFromData(this.interactionsViewData, r1, r8);
        StatisticActivity.putColorFromData(this.reactionsByEmotionData, r1, r8);
        return r1;
    L5:
        r12 = null;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_windowBackgroundWhite, getResourceProvider())) <= 0.699999988079071d) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        if (this.chat == null) goto L5;
        loadStat();
        loadChats(100);
    L6:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatInfoDidLoad);
        return true;
    L5:
        MessagesController.getInstance(this.currentAccount).loadFullChat(this.chatId, this.classGuid, true);
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatInfoDidLoad);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        ListAdapter r2 = this.listViewAdapter;
        if (r2 == null) goto L6;
        r2.notifyDataSetChanged();
        return;
    }
}
