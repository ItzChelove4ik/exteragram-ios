package org.telegram.ui.Business;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.TextWatcherImpl;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Business.QuickRepliesActivity;
import org.telegram.ui.Business.QuickRepliesController;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.FillLastLinearLayoutManager;
import org.telegram.ui.Components.FragmentSearchField;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public class ChatAttachAlertQuickRepliesLayout extends ChatAttachAlert.AttachAlertLayout implements NotificationCenter.NotificationCenterDelegate, FactorAnimator.Target {
    private final BoolAnimator animatorFadeVisible;
    private final EmptyTextProgressView emptyView;
    private final View fadeView;
    private final FrameLayout frameLayout;
    private final FillLastLinearLayoutManager layoutManager;
    private final ShareAdapter listAdapter;
    private final RecyclerListView listView;
    private final ShareSearchAdapter searchAdapter;
    private final FragmentSearchField searchField;
    private final HashSet<Integer> selectedReplies;

    /* renamed from: org.telegram.ui.Business.ChatAttachAlertQuickRepliesLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcherImpl {
        final /* synthetic */ ChatAttachAlertQuickRepliesLayout this$0;

        public AnonymousClass1(ChatAttachAlertQuickRepliesLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r4) {
            String r42 = r4.toString();
            boolean r0 = r42.isEmpty();
            ChatAttachAlertQuickRepliesLayout r1 = this.this$0;
            if (r0 == true) goto L8;
            if (ChatAttachAlertQuickRepliesLayout.m6337$$Nest$fgetemptyView(r1) == null) goto L13;
            ChatAttachAlertQuickRepliesLayout.m6337$$Nest$fgetemptyView(this.this$0).setText(LocaleController.getString(R.string.NoResult));
        L13:
            if (ChatAttachAlertQuickRepliesLayout.m6341$$Nest$fgetsearchAdapter(this.this$0) == null) goto L16;
            ChatAttachAlertQuickRepliesLayout.m6341$$Nest$fgetsearchAdapter(this.this$0).search(r42);
            return;
        L16:
            return;
        L8:
            if (ChatAttachAlertQuickRepliesLayout.m6340$$Nest$fgetlistView(r1).getAdapter() == ChatAttachAlertQuickRepliesLayout.m6339$$Nest$fgetlistAdapter(this.this$0)) goto L13;
            int r02 = ChatAttachAlertQuickRepliesLayout.m6343$$Nest$mgetCurrentTop(this.this$0);
            ChatAttachAlertQuickRepliesLayout.m6337$$Nest$fgetemptyView(this.this$0).showTextView();
            ChatAttachAlertQuickRepliesLayout.m6340$$Nest$fgetlistView(this.this$0).setAdapter(ChatAttachAlertQuickRepliesLayout.m6339$$Nest$fgetlistAdapter(this.this$0));
            ChatAttachAlertQuickRepliesLayout.m6339$$Nest$fgetlistAdapter(this.this$0).notifyDataSetChanged();
            if (r02 <= 0) goto L13;
            ChatAttachAlertQuickRepliesLayout.m6338$$Nest$fgetlayoutManager(this.this$0).scrollToPositionWithOffset(0, -r02);
            goto L13
        }
    }

    /* renamed from: org.telegram.ui.Business.ChatAttachAlertQuickRepliesLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ ChatAttachAlertQuickRepliesLayout this$0;

        public AnonymousClass2(ChatAttachAlertQuickRepliesLayout r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean allowSelectChildAtPosition(float r3, float r4) {
            int r32 = ChatAttachAlertQuickRepliesLayout.access$000(this.this$0).scrollOffsetY[0] + AndroidUtilities.dp(30.0f);
            if (ChatAttachAlertQuickRepliesLayout.access$100(this.this$0).inBubbleMode == true) goto L5;
            int r2 = AndroidUtilities.statusBarHeight;
        L7:
            if (r4 < (r32 + r2)) goto L10;
            return true;
        L10:
            return false;
        L5:
            r2 = 0;
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrolled(int r1, int r2) {
            super.onScrolled(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Business.ChatAttachAlertQuickRepliesLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends FillLastLinearLayoutManager {
        final /* synthetic */ ChatAttachAlertQuickRepliesLayout this$0;

        /* renamed from: org.telegram.ui.Business.ChatAttachAlertQuickRepliesLayout$3$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearSmoothScroller {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDyToMakeVisible(View r1, int r2) {
                return super.calculateDyToMakeVisible(r1, r2) - ((ChatAttachAlertQuickRepliesLayout.m6340$$Nest$fgetlistView(this.this$1.this$0).getPaddingTop() - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(8.0f));
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateTimeForDeceleration(int r1) {
                return super.calculateTimeForDeceleration(r1) * 2;
            }
        }

        public AnonymousClass3(ChatAttachAlertQuickRepliesLayout r1, Context r2, int r3, boolean r4, int r5, RecyclerView r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView r1, RecyclerView.State r2, int r3) {
            AnonymousClass1 r22 = new AnonymousClass1(this, r1.getContext());
            r22.setTargetPosition(r3);
            startSmoothScroll(r22);
        }
    }

    /* renamed from: org.telegram.ui.Business.ChatAttachAlertQuickRepliesLayout$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatAttachAlertQuickRepliesLayout this$0;

        public AnonymousClass4(ChatAttachAlertQuickRepliesLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            ChatAttachAlertQuickRepliesLayout.access$200(this.this$0).updateLayout(this.this$0, true, r4);
            ChatAttachAlertQuickRepliesLayout.m6345$$Nest$mupdateEmptyViewPosition(this.this$0);
        }
    }

    public class ShareAdapter extends RecyclerListView.SectionsAdapter {
        private int currentAccount;
        private Context mContext;
        private ArrayList<QuickRepliesController.QuickReply> replies;
        final /* synthetic */ ChatAttachAlertQuickRepliesLayout this$0;

        public ShareAdapter(ChatAttachAlertQuickRepliesLayout r2, Context r3) {
            this.this$0 = r2;
            ArrayList<QuickRepliesController.QuickReply> r22 = new ArrayList();
            this.replies = r22;
            int r0 = UserConfig.selectedAccount;
            this.currentAccount = r0;
            this.mContext = r3;
            r22.addAll(QuickRepliesController.getInstance(r0).getFilteredReplies());
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getCountForSection(int r3) {
            if (r3 != 0) goto L5;
        L9:
            return 1;
        L5:
            if (r3 == (getSectionCount() - 1)) goto L9;
            return this.replies.size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public Object getItem(int r2, int r3) {
            if (r2 != 0) goto L5;
            return null;
        L5:
            if (r3 >= 0) goto L7;
        L11:
            return null;
        L7:
            if (r3 >= this.replies.size()) goto L11;
            return this.replies.get(r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getItemViewType(int r1, int r2) {
            if (r1 != 0) goto L6;
            return 1;
        L6:
            if (r1 != (getSectionCount() - 1)) goto L9;
            return 2;
        L9:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public String getLetter(int r1) {
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void getPositionForScrollProgress(RecyclerListView r1, float r2, int[] r3) {
            r3[0] = 0;
            r3[1] = 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getSectionCount() {
            return 3;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public View getSectionHeaderView(int r1, View r2) {
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3, int r4, int r5) {
            if (r4 != 0) goto L5;
        L10:
            return false;
        L5:
            if (r4 == (getSectionCount() - 1)) goto L10;
            if (r5 >= this.replies.size()) goto L10;
            return true;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            this.replies.clear();
            this.replies.addAll(QuickRepliesController.getInstance(this.currentAccount).getFilteredReplies());
            super.notifyDataSetChanged();
            ChatAttachAlertQuickRepliesLayout.m6344$$Nest$mupdateEmptyView(this.this$0);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public void onBindViewHolder(int r5, int r6, RecyclerView.ViewHolder r7) {
            if (r7.getItemViewType() != 0) goto L14;
            QuickRepliesActivity.QuickReplyView r72 = (QuickRepliesActivity.QuickReplyView) r7.itemView;
            Object r0 = getItem(r5, r6);
            boolean r3 = true;
            if (r5 != (getSectionCount() - 2)) goto L11;
            if (r6 != (getCountForSection(r5) - 1)) goto L11;
            r3 = false;
        L11:
            if ((r0 instanceof QuickRepliesController.QuickReply) == false) goto L15;
            QuickRepliesController.QuickReply r02 = (QuickRepliesController.QuickReply) r0;
            r72.set(r02, null, r3);
            r72.setChecked(ChatAttachAlertQuickRepliesLayout.m6342$$Nest$fgetselectedReplies(this.this$0).contains(Integer.valueOf(r02.id)), false);
            return;
        L15:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L5;
            View r42 = new QuickRepliesActivity.QuickReplyView(this.mContext, false, ChatAttachAlertQuickRepliesLayout.access$300(this.this$0));
        L10:
            return new RecyclerListView.Holder(r42);
        L5:
            if (r4 == 1) goto L7;
            r42 = new View(this.mContext);
            r42.setTag(-33024);
            goto L10
        L7:
            r42 = new View(this.mContext);
            r42.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(56.0f)));
            r42.setTag(-33024);
            goto L10
        }
    }

    public class ShareSearchAdapter extends RecyclerListView.SelectionAdapter {
        public String lastQuery;
        private Context mContext;
        private ArrayList<QuickRepliesController.QuickReply> searchResult;
        private Runnable searchRunnable;
        final /* synthetic */ ChatAttachAlertQuickRepliesLayout this$0;

        public ShareSearchAdapter(ChatAttachAlertQuickRepliesLayout r1, Context r2) {
            this.this$0 = r1;
            this.searchResult = new ArrayList();
            this.mContext = r2;
        }

        public Object getItem(int r2) {
            int r22 = r2 - 1;
            if (r22 >= 0) goto L5;
            return null;
        L5:
            if (r22 < this.searchResult.size()) goto L8;
            return null;
        L8:
            return this.searchResult.get(r22);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.searchResult.size() + 2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != 0) goto L6;
            return 1;
        L6:
            if (r2 != (getItemCount() - 1)) goto L9;
            return 2;
        L9:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            ChatAttachAlertQuickRepliesLayout.m6344$$Nest$mupdateEmptyView(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
            if (r4.getItemViewType() != 0) goto L12;
            QuickRepliesActivity.QuickReplyView r42 = (QuickRepliesActivity.QuickReplyView) r4.itemView;
            if (r5 == (getItemCount() - 2)) goto L7;
            boolean r0 = true;
        L8:
            Object r52 = getItem(r5);
            if ((r52 instanceof QuickRepliesController.QuickReply) == false) goto L13;
            QuickRepliesController.QuickReply r53 = (QuickRepliesController.QuickReply) r52;
            r42.set(r53, this.lastQuery, r0);
            r42.setChecked(ChatAttachAlertQuickRepliesLayout.m6342$$Nest$fgetselectedReplies(this.this$0).contains(Integer.valueOf(r53.id)), false);
            return;
        L13:
            return;
        L7:
            r0 = false;
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L5;
            View r42 = new QuickRepliesActivity.QuickReplyView(this.mContext, false, ChatAttachAlertQuickRepliesLayout.access$400(this.this$0));
        L10:
            return new RecyclerListView.Holder(r42);
        L5:
            if (r4 == 1) goto L7;
            r42 = new View(this.mContext);
            r42.setTag(-33024);
            goto L10
        L7:
            r42 = new View(this.mContext);
            r42.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(56.0f)));
            r42.setTag(-33024);
            goto L10
        }

        public void search(String r6) {
            if (this.searchRunnable == null) goto L5;
            Utilities.searchQueue.cancelRunnable(this.searchRunnable);
            this.searchRunnable = null;
        L5:
            this.searchResult.clear();
            this.lastQuery = r6;
            if (r6 == null) goto L23;
            String r62 = AndroidUtilities.translitSafe(r6);
            if (r62.startsWith("/") == false) goto L10;
            r62 = r62.substring(1);
        L10:
            QuickRepliesController r0 = QuickRepliesController.getInstance(UserConfig.selectedAccount);
            int r1 = 0;
        L12:
            if (r1 >= r0.replies.size()) goto L23;
            QuickRepliesController.QuickReply r2 = r0.replies.get(r1);
            if (r2.isSpecial() == true) goto L21;
            String r3 = AndroidUtilities.translitSafe(r2.name);
            if (r3.startsWith(r62) == false) goto L19;
        L20:
            this.searchResult.add(r2);
            goto L21
        L19:
            if (r3.contains(" ".concat(r62)) == true) goto L20;
        L21:
            r1 = r1 + 1;
        L23:
            if (ChatAttachAlertQuickRepliesLayout.m6340$$Nest$fgetlistView(this.this$0).getAdapter() == ChatAttachAlertQuickRepliesLayout.m6341$$Nest$fgetsearchAdapter(this.this$0)) goto L25;
            ChatAttachAlertQuickRepliesLayout.m6340$$Nest$fgetlistView(this.this$0).setAdapter(ChatAttachAlertQuickRepliesLayout.m6341$$Nest$fgetsearchAdapter(this.this$0));
        L25:
            notifyDataSetChanged();
        }
    }

    public static class UserCell extends FrameLayout {
    }

    public static /* synthetic */ void $r8$lambda$2ar2opa7bnORSoN1DvnfBWuUny8(ChatAttachAlertQuickRepliesLayout r0, View r1, int r2) {
        r0.lambda$new$3(r1, r2);
    }

    /* renamed from: $r8$lambda$4j1n1jw9lcB2eEjWPBvxKY-otgg, reason: not valid java name */
    public static /* synthetic */ void m6334$r8$lambda$4j1n1jw9lcB2eEjWPBvxKYotgg(ChatAttachAlertQuickRepliesLayout r0, Object r1, Long r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$YffYMdHv80uASx0pxSq7Xd2z6zc(ChatAttachAlertQuickRepliesLayout r0, Object[] r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$igtI_5gWR3Tq7Z9Pm-9xKI49MsM, reason: not valid java name */
    public static /* synthetic */ void m6335$r8$lambda$igtI_5gWR3Tq7Z9Pm9xKI49MsM(ChatAttachAlertQuickRepliesLayout r0) {
        r0.lambda$getThemeDescriptions$4();
    }

    /* renamed from: $r8$lambda$j9_Sf6RM1-FEv0l3jKEMb1HhS5Y, reason: not valid java name */
    public static /* synthetic */ void m6336$r8$lambda$j9_Sf6RM1FEv0l3jKEMb1HhS5Y(View r1) {
        if ((r1 instanceof QuickRepliesActivity.QuickReplyView) == false) goto L6;
        ((QuickRepliesActivity.QuickReplyView) r1).invalidateEmojis();
        return;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmptyTextProgressView m6337$$Nest$fgetemptyView(ChatAttachAlertQuickRepliesLayout r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FillLastLinearLayoutManager m6338$$Nest$fgetlayoutManager(ChatAttachAlertQuickRepliesLayout r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ShareAdapter m6339$$Nest$fgetlistAdapter(ChatAttachAlertQuickRepliesLayout r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m6340$$Nest$fgetlistView(ChatAttachAlertQuickRepliesLayout r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ShareSearchAdapter m6341$$Nest$fgetsearchAdapter(ChatAttachAlertQuickRepliesLayout r0) {
        return r0.searchAdapter;
    }

    /* renamed from: -$$Nest$fgetselectedReplies, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashSet m6342$$Nest$fgetselectedReplies(ChatAttachAlertQuickRepliesLayout r0) {
        return r0.selectedReplies;
    }

    /* renamed from: -$$Nest$mgetCurrentTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6343$$Nest$mgetCurrentTop(ChatAttachAlertQuickRepliesLayout r0) {
        return r0.getCurrentTop();
    }

    /* renamed from: -$$Nest$mupdateEmptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6344$$Nest$mupdateEmptyView(ChatAttachAlertQuickRepliesLayout r0) {
        r0.updateEmptyView();
    }

    /* renamed from: -$$Nest$mupdateEmptyViewPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6345$$Nest$mupdateEmptyViewPosition(ChatAttachAlertQuickRepliesLayout r0) {
        r0.updateEmptyViewPosition();
    }

    public ChatAttachAlertQuickRepliesLayout(ChatAttachAlert r18, Context r19, Theme.ResourcesProvider r20) {
        super(r18, r19, r20);
        this.animatorFadeVisible = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 380);
        this.selectedReplies = new HashSet();
        this.searchAdapter = new ShareSearchAdapter(this, r19);
        ChatAttachAlert.SearchFadeView r0 = new ChatAttachAlert.SearchFadeView(r19, Theme.key_windowBackgroundWhite, r20);
        this.fadeView = r0;
        r0.setVisibility(4);
        FrameLayout r8 = new FrameLayout(r19);
        this.frameLayout = r8;
        ChatAttachAlert.AttachSearchField r2 = new ChatAttachAlert.AttachSearchField(r19, this.parentAlert, r20);
        this.searchField = r2;
        r2.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        r2.editText.addTextChangedListener(new AnonymousClass1(this));
        r2.editText.setHint(LocaleController.getString(R.string.BusinessRepliesSearch));
        r8.addView(r0, LayoutHelper.createFrameMatchParent());
        FrameLayout.LayoutParams r02 = LayoutHelper.createFrame(-1, 48.0f, 51, 7.0f, 8.0f, 7.0f, 4.0f);
        ((ViewGroup.MarginLayoutParams) r02).topMargin += AndroidUtilities.statusBarHeight;
        r8.addView(r2, r02);
        EmptyTextProgressView r03 = new EmptyTextProgressView(r19, null, r20);
        this.emptyView = r03;
        r03.showTextView();
        addView(r03, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 52.0f, 0.0f, 0.0f));
        AnonymousClass2 r04 = new AnonymousClass2(this, r19, r20);
        this.listView = r04;
        r04.setSections();
        this.iBlur3Capture = r04;
        this.iBlur3CaptureView = r04;
        this.occupyStatusBar = true;
        this.occupyNavigationBar = true;
        NotificationCenter.getGlobalInstance().listen(r04, NotificationCenter.emojiLoaded, new ChatAttachAlertQuickRepliesLayout$$ExternalSyntheticLambda1(this));
        r04.setClipToPadding(false);
        AnonymousClass3 r05 = new AnonymousClass3(this, getContext(), 1, false, AndroidUtilities.dp(9.0f), r04);
        this.layoutManager = r05;
        r04.setLayoutManager(r05);
        r05.setBind(false);
        r04.setHorizontalScrollBarEnabled(false);
        r04.setVerticalScrollBarEnabled(false);
        r04.setClipToPadding(false);
        addView(r04, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        ShareAdapter r06 = new ShareAdapter(this, r19);
        this.listAdapter = r06;
        r04.setAdapter(r06);
        r04.setGlowColor(getThemedColor(Theme.key_dialogScrollGlow));
        r04.setOnItemClickListener(new ChatAttachAlertQuickRepliesLayout$$ExternalSyntheticLambda2(this));
        r04.setOnScrollListener(new AnonymousClass4(this));
        FrameLayout.LayoutParams r07 = LayoutHelper.createFrame(-1, 60, 51);
        ((ViewGroup.MarginLayoutParams) r07).height += AndroidUtilities.statusBarHeight;
        addView(r8, r07);
        updateEmptyView();
    }

    public static /* synthetic */ ChatAttachAlert access$000(ChatAttachAlertQuickRepliesLayout r0) {
        return r0.parentAlert;
    }

    public static /* synthetic */ ChatAttachAlert access$100(ChatAttachAlertQuickRepliesLayout r0) {
        return r0.parentAlert;
    }

    public static /* synthetic */ ChatAttachAlert access$200(ChatAttachAlertQuickRepliesLayout r0) {
        return r0.parentAlert;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(ChatAttachAlertQuickRepliesLayout r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$400(ChatAttachAlertQuickRepliesLayout r0) {
        return r0.resourcesProvider;
    }

    private int getCurrentTop() {
        if (this.listView.getChildCount() == 0) goto L13;
        int r1 = 0;
        View r0 = this.listView.getChildAt(0);
        RecyclerListView.Holder r2 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r0);
        if (r2 == null) goto L15;
        int r3 = this.listView.getPaddingTop();
        if (r2.getAdapterPosition() != 0) goto L12;
        if (r0.getTop() < 0) goto L12;
        r1 = r0.getTop();
    L12:
        return r3 - r1;
    L15:
        return -1000;
    L13:
        return -1000;
    }

    private /* synthetic */ void lambda$getThemeDescriptions$4() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L7;
        int r02 = r0.getChildCount();
        int r1 = 0;
    L5:
        if (r1 >= r02) goto L9;
        this.listView.getChildAt(r1);
        r1 = r1 + 1;
        goto L5
    L9:
        return;
    }

    private /* synthetic */ void lambda$new$1(Object[] r1) {
        AndroidUtilities.forEachViews(this.listView, new ChatAttachAlertQuickRepliesLayout$$ExternalSyntheticLambda3());
    }

    private /* synthetic */ void lambda$new$2(Object r3, Long r4) {
        QuickRepliesController.getInstance(UserConfig.selectedAccount).sendQuickReplyTo(this.parentAlert.getDialogId(), (QuickRepliesController.QuickReply) r3);
        this.parentAlert.dismiss();
    }

    private /* synthetic */ void lambda$new$3(View r9, int r10) {
        RecyclerView.Adapter r92 = this.listView.getAdapter();
        ShareSearchAdapter r0 = this.searchAdapter;
        if (r92 != r0) goto L5;
        Object r93 = r0.getItem(r10);
    L11:
        if ((r93 instanceof QuickRepliesController.QuickReply) == false) goto L21;
        boolean r102 = UserConfig.getInstance(this.parentAlert.currentAccount).isPremium();
        ChatAttachAlert r02 = this.parentAlert;
        if (r102 == false) goto L15;
        AlertsCreator.ensurePaidMessageConfirmation(r02.currentAccount, r02.getDialogId(), ((QuickRepliesController.QuickReply) r93).getMessagesCount(), new ChatAttachAlertQuickRepliesLayout$$ExternalSyntheticLambda4(this, r93));
        return;
    L15:
        if (r02.baseFragment == null) goto L22;
        new PremiumFeatureBottomSheet(this.parentAlert.baseFragment, getContext(), this.parentAlert.currentAccount, true, 31, false, null).show();
        return;
    L22:
        return;
    L21:
        return;
    L5:
        int r94 = this.listAdapter.getSectionForPosition(r10);
        int r103 = this.listAdapter.getPositionInSectionForPosition(r10);
        if (r103 < 0) goto L20;
        if (r94 < 0) goto L23;
        r93 = this.listAdapter.getItem(r94, r103);
        goto L11
    L23:
        return;
    }

    private void updateEmptyView() {
        int r2 = 0;
        if (this.listView.getAdapter().getItemCount() != 2) goto L5;
        boolean r0 = true;
    L6:
        EmptyTextProgressView r1 = this.emptyView;
        if (r0 == true) goto L10;
        r2 = 8;
    L10:
        r1.setVisibility(r2);
        updateEmptyViewPosition();
        return;
    L5:
        r0 = false;
        goto L6
    }

    private void updateEmptyViewPosition() {
        if (this.emptyView.getVisibility() == 0) goto L6;
        return;
    L6:
        if (this.listView.getChildAt(0) != null) goto L8;
        return;
    L8:
        this.emptyView.setTranslationY(((r1.getMeasuredHeight() - getMeasuredHeight()) + r0.getTop()) / 2);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCurrentItemTop() {
        if (this.listView.getChildCount() > 0) goto L6;
        return Integer.MAX_VALUE;
    L6:
        View r0 = this.listView.getChildAt(0);
        RecyclerListView.Holder r2 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r0);
        int r02 = (r0.getTop() - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(8.0f);
        if (r02 <= 0) goto L12;
        if (r2 == null) goto L12;
        if (r2.getAdapterPosition() != 0) goto L12;
        int r3 = r02;
    L14:
        if (r02 < 0) goto L19;
        if (r2 == null) goto L19;
        if (r2.getAdapterPosition() != 0) goto L19;
        this.animatorFadeVisible.setValue(false, true);
    L20:
        this.frameLayout.setTranslationY(r02);
        return r02 + AndroidUtilities.dp(12.0f);
    L19:
        this.animatorFadeVisible.setValue(true, true);
        r02 = r3;
    L12:
        r3 = 0;
        goto L14
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getFirstOffset() {
        return getListTopPadding() + AndroidUtilities.dp(4.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getListTopPadding() {
        return this.listView.getPaddingTop();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getSelectedItemsCount() {
        return 0;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ChatAttachAlertQuickRepliesLayout$$ExternalSyntheticLambda0 r6 = new ChatAttachAlertQuickRepliesLayout$$ExternalSyntheticLambda0(this);
        ArrayList<ThemeDescription> r10 = new ArrayList();
        r10.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder));
        r10.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle));
        r10.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_dialogScrollGlow));
        r10.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r10.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        int r24 = Theme.key_dialogTextGray2;
        r10.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"nameTextView"}, null, null, null, r24));
        r10.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"statusTextView"}, null, null, r6, r24));
        r10.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundRed));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundOrange));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundViolet));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundGreen));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundCyan));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundBlue));
        r10.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_avatar_backgroundPink));
        return r10;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onDestroy() {
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updateEmptyViewPosition();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPreMeasure(int r3, int r4) {
        if (this.parentAlert.sizeNotifierFrameLayout.measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L6;
        int r32 = AndroidUtilities.dp(8.0f);
        this.parentAlert.setAllowNestedScroll(false);
    L12:
        this.listView.setPaddingWithoutRequestLayout(0, r32 + AndroidUtilities.statusBarHeight, 0, this.listPaddingBottom);
        return;
    L6:
        if (AndroidUtilities.isTablet() == true) goto L10;
        Point r33 = AndroidUtilities.displaySize;
        if (r33.x <= r33.y) goto L10;
        r32 = (int) (r4 / 3.5f);
    L11:
        this.parentAlert.setAllowNestedScroll(true);
    L10:
        r32 = (r4 / 5) * 2;
        goto L11
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShow(ChatAttachAlert.AttachAlertLayout r1) {
        this.layoutManager.scrollToPositionWithOffset(0, 0);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void scrollToTop() {
        this.listView.smoothScrollToPosition(0);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean sendSelectedItems(boolean r1, int r2, int r3, long r4, boolean r6) {
        return false;
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        this.parentAlert.getSheetContainer().invalidate();
    }

    public void setupBlurredSearchField(BlurredBackgroundDrawableViewFactory r2) {
        FragmentSearchField r0 = this.searchField;
        if (r0 == null) goto L6;
        r0.setupBlurredBackground(r2.create(r0, BlurredBackgroundProviderImpl.attachMenuSearch(this.resourcesProvider)));
        return;
    }
}
