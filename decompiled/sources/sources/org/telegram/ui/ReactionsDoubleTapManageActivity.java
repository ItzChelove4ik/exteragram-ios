package org.telegram.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.AvailableReactionCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.ThemePreviewMessagesCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SimpleThemeDescription;
import org.telegram.ui.SelectAnimatedEmojiDialog;

/* loaded from: classes6.dex */
public class ReactionsDoubleTapManageActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private LinearLayout contentView;
    int infoRow;
    private RecyclerView.Adapter listAdapter;
    private RecyclerListView listView;
    int premiumReactionRow;
    int previewRow;
    int reactionsStartRow;
    int rowCount;
    private SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow selectAnimatedEmojiDialog;

    /* renamed from: org.telegram.ui.ReactionsDoubleTapManageActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ReactionsDoubleTapManageActivity this$0;

        public AnonymousClass1(ReactionsDoubleTapManageActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ReactionsDoubleTapManageActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ ReactionsDoubleTapManageActivity this$0;
        final /* synthetic */ Context val$context;

        /* renamed from: org.telegram.ui.ReactionsDoubleTapManageActivity$2$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ AnonymousClass2 this$1;

            public AnonymousClass1(AnonymousClass2 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(16.0f), TLObject.FLAG_30));
            }
        }

        public AnonymousClass2(ReactionsDoubleTapManageActivity r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            ReactionsDoubleTapManageActivity r2 = this.this$0;
            int r0 = r2.rowCount;
            if (r2.premiumReactionRow >= 0) goto L5;
            int r22 = ReactionsDoubleTapManageActivity.m18464$$Nest$mgetAvailableReactions(r2).size();
        L7:
            return (r0 + r22) + 1;
        L5:
            r22 = 0;
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            ReactionsDoubleTapManageActivity r0 = this.this$0;
            if (r3 != r0.previewRow) goto L7;
            return 0;
        L7:
            if (r3 != r0.infoRow) goto L11;
            return 2;
        L11:
            if (r3 != r0.premiumReactionRow) goto L15;
            return 3;
        L15:
            if (r3 != (getItemCount() - 1)) goto L18;
            return 4;
        L18:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() != 3) goto L5;
            return true;
        L5:
            if (r2.getItemViewType() == 2) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4) {
            if (getItemViewType(r4) == 1) goto L5;
            return;
        L5:
            AvailableReactionCell r32 = (AvailableReactionCell) r3.itemView;
            TLRPC.TL_availableReaction r42 = (TLRPC.TL_availableReaction) ReactionsDoubleTapManageActivity.m18464$$Nest$mgetAvailableReactions(this.this$0).get(r4 - this.this$0.reactionsStartRow);
            r32.bind(r42, r42.reaction.contains(MediaDataController.getInstance(ReactionsDoubleTapManageActivity.access$100(this.this$0)).getDoubleTapReaction()), ReactionsDoubleTapManageActivity.access$200(this.this$0));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r4, int r5) {
            if (r5 == 0) goto L12;
            if (r5 != 2) goto L6;
            TextInfoPrivacyCell r42 = new TextInfoPrivacyCell(this.val$context);
            r42.setText(LocaleController.getString(R.string.DoubleTapPreviewRational));
            View r43 = r42;
        L14:
            return new RecyclerListView.Holder(r43);
        L6:
            if (r5 == 3) goto L10;
            if (r5 == 4) goto L9;
            r43 = new AvailableReactionCell(this.val$context, true, true);
            goto L14
        L9:
            View r44 = new AnonymousClass1(this, this.val$context);
            r44.setTag(-33024);
            r43 = r44;
            goto L14
        L10:
            SetDefaultReactionCell r45 = new SetDefaultReactionCell(this.this$0, this.val$context);
            r45.update(false);
            r43 = r45;
            goto L14
        L12:
            ThemePreviewMessagesCell r52 = new ThemePreviewMessagesCell(this.val$context, ReactionsDoubleTapManageActivity.access$000(this.this$0), 2);
            r52.setImportantForAccessibility(4);
            r52.fragment = this.this$0;
            r43 = r52;
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.ReactionsDoubleTapManageActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends SelectAnimatedEmojiDialog {
        final /* synthetic */ ReactionsDoubleTapManageActivity this$0;
        final /* synthetic */ SetDefaultReactionCell val$cell;
        final /* synthetic */ SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] val$popup;

        public AnonymousClass3(ReactionsDoubleTapManageActivity r1, BaseFragment r2, Context r3, boolean r4, Integer r5, int r6, Theme.ResourcesProvider r7, SetDefaultReactionCell r8, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r9) {
            this.this$0 = r1;
            this.val$cell = r8;
            this.val$popup = r9;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public void onEmojiSelected(View r1, Long r2, TLRPC.Document r3, TL_stars.TL_starGiftUnique r4, Integer r5) {
            if (r2 == null) goto L12;
            MediaDataController.getInstance(ReactionsDoubleTapManageActivity.access$500(this.this$0)).setDoubleTapReaction("animated_" + r2);
            SetDefaultReactionCell r12 = this.val$cell;
            if (r12 == null) goto L8;
            r12.update(true);
        L8:
            if (this.val$popup[0] == null) goto L11;
            ReactionsDoubleTapManageActivity.m18463$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
            this.val$popup[0].dismiss();
            return;
        L11:
            return;
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public void onReactionClick(SelectAnimatedEmojiDialog.ImageViewEmoji r2, ReactionsLayoutInBubble.VisibleReaction r3) {
            MediaDataController.getInstance(ReactionsDoubleTapManageActivity.access$600(this.this$0)).setDoubleTapReaction(r3.emojicon);
            SetDefaultReactionCell r22 = this.val$cell;
            if (r22 == null) goto L6;
            r22.update(true);
        L6:
            if (this.val$popup[0] == null) goto L9;
            ReactionsDoubleTapManageActivity.m18463$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
            this.val$popup[0].dismiss();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ReactionsDoubleTapManageActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow {
        final /* synthetic */ ReactionsDoubleTapManageActivity this$0;

        public AnonymousClass4(ReactionsDoubleTapManageActivity r1, View r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow, android.widget.PopupWindow
        public void dismiss() {
            super.dismiss();
            ReactionsDoubleTapManageActivity.m18463$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
        }
    }

    public class SetDefaultReactionCell extends FrameLayout {
        private AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable imageDrawable;
        private TextView textView;
        final /* synthetic */ ReactionsDoubleTapManageActivity this$0;

        /* renamed from: -$$Nest$fgetimageDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m18465$$Nest$fgetimageDrawable(SetDefaultReactionCell r0) {
            return r0.imageDrawable;
        }

        public SetDefaultReactionCell(ReactionsDoubleTapManageActivity r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            TextView r0 = new TextView(r9);
            this.textView = r0;
            r0.setTextSize(1, 16.0f);
            this.textView.setTextColor(r8.getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
            this.textView.setTypeface(AndroidUtilities.regular());
            this.textView.setText(LocaleController.getString(R.string.DoubleTapSetting));
            addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 23, 20.0f, 0.0f, 48.0f, 0.0f));
            this.imageDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this, AndroidUtilities.dp(24.0f));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r1) {
            super.dispatchDraw(r1);
            updateImageBounds();
            this.imageDrawable.draw(r1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.imageDrawable.attach();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.imageDrawable.detach();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
        }

        public void update(boolean r5) {
            String r0 = MediaDataController.getInstance(ReactionsDoubleTapManageActivity.access$300(this.this$0)).getDoubleTapReaction();
            if (r0 != null) goto L5;
        L9:
            TLRPC.TL_availableReaction r02 = MediaDataController.getInstance(ReactionsDoubleTapManageActivity.access$400(this.this$0)).getReactionsMap().get(r0);
            if (r02 == null) goto L16;
            this.imageDrawable.set(r02.static_icon, r5);
            return;
        L16:
            return;
        L5:
            if (r0.startsWith("animated_") == false) goto L9;
            long r1 = Long.parseLong(r0.substring(9));     // Catch: Exception -> L13
            this.imageDrawable.set(r1, r5);     // Catch: Exception -> L13
        }

        public void updateImageBounds() {
            this.imageDrawable.setBounds((getWidth() - this.imageDrawable.getIntrinsicWidth()) - AndroidUtilities.dp(21.0f), (getHeight() - this.imageDrawable.getIntrinsicHeight()) / 2, getWidth() - AndroidUtilities.dp(21.0f), (getHeight() + this.imageDrawable.getIntrinsicHeight()) / 2);
        }
    }

    /* renamed from: $r8$lambda$D_-J7HG7-9CcHogS-nlEWUgbjvE, reason: not valid java name */
    public static /* synthetic */ void m18461$r8$lambda$D_J7HG79CcHogSnlEWUgbjvE(ReactionsDoubleTapManageActivity r0) {
        r0.updateColors();
    }

    /* renamed from: $r8$lambda$t6-xQp4ZaF-du5glh7KoKvYKKRE, reason: not valid java name */
    public static /* synthetic */ void m18462$r8$lambda$t6xQp4ZaFdu5glh7KoKvYKKRE(ReactionsDoubleTapManageActivity r0, View r1, int r2) {
        r0.lambda$createView$0(r1, r2);
    }

    /* renamed from: -$$Nest$fputselectAnimatedEmojiDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18463$$Nest$fputselectAnimatedEmojiDialog(ReactionsDoubleTapManageActivity r0, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow r1) {
        r0.selectAnimatedEmojiDialog = r1;
    }

    /* renamed from: -$$Nest$mgetAvailableReactions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m18464$$Nest$mgetAvailableReactions(ReactionsDoubleTapManageActivity r0) {
        return r0.getAvailableReactions();
    }

    public ReactionsDoubleTapManageActivity() {
        this.reactionsStartRow = -1;
    }

    public static /* synthetic */ INavigationLayout access$000(ReactionsDoubleTapManageActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ int access$100(ReactionsDoubleTapManageActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(ReactionsDoubleTapManageActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(ReactionsDoubleTapManageActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(ReactionsDoubleTapManageActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(ReactionsDoubleTapManageActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$600(ReactionsDoubleTapManageActivity r0) {
        return r0.currentAccount;
    }

    private List<TLRPC.TL_availableReaction> getAvailableReactions() {
        return getMediaDataController().getReactionsList();
    }

    private /* synthetic */ void lambda$createView$0(View r2, int r3) {
        if ((r2 instanceof AvailableReactionCell) == false) goto L13;
        AvailableReactionCell r22 = (AvailableReactionCell) r2;
        if (r22.locked == true) goto L7;
    L10:
        MediaDataController.getInstance(this.currentAccount).setDoubleTapReaction(r22.react.reaction);
        this.listView.getAdapter().notifyItemRangeChanged(0, this.listView.getAdapter().getItemCount());
        return;
    L7:
        if (getUserConfig().isPremium() == true) goto L10;
        showDialog(new PremiumFeatureBottomSheet(this, 4, true));
        return;
    L13:
        if ((r2 instanceof SetDefaultReactionCell) == false) goto L16;
        showSelectStatusDialog((SetDefaultReactionCell) r2);
        return;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void updateColors() {
        this.contentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.listAdapter.notifyDataSetChanged();
    }

    private void updateRows() {
        this.previewRow = 0;
        this.rowCount = 1 + 1;
        this.infoRow = 1;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L6;
        this.reactionsStartRow = -1;
        int r0 = this.rowCount;
        this.rowCount = r0 + 1;
        this.premiumReactionRow = r0;
        return;
    L6:
        this.premiumReactionRow = -1;
        this.reactionsStartRow = this.rowCount;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r4) {
        this.actionBar.setTitle(LocaleController.getString(R.string.Reactions));
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        LinearLayout r0 = new LinearLayout(r4);
        r0.setOrientation(1);
        RecyclerListView r1 = new RecyclerListView(r4);
        this.listView = r1;
        r1.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        ((DefaultItemAnimator) this.listView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.listView.setLayoutManager(new LinearLayoutManager(r4));
        RecyclerListView r12 = this.listView;
        AnonymousClass2 r2 = new AnonymousClass2(this, r4);
        this.listAdapter = r2;
        r12.setAdapter(r2);
        this.listView.setOnItemClickListener(new ReactionsDoubleTapManageActivity$$ExternalSyntheticLambda0(this));
        r0.addView(this.listView, LayoutHelper.createLinear(-1, -1));
        this.contentView = r0;
        this.fragmentView = r0;
        updateColors();
        updateRows();
        return this.contentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    @SuppressLint({"NotifyDataSetChanged"})
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r2 == this.currentAccount) goto L6;
        return;
    L6:
        if (r1 != NotificationCenter.reactionsDidLoad) goto L10;
        this.listAdapter.notifyDataSetChanged();
        return;
    L10:
        if (r1 != NotificationCenter.currentUserPremiumStatusChanged) goto L13;
        updateRows();
        this.listAdapter.notifyDataSetChanged();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        return SimpleThemeDescription.createThemeDescriptions(new ReactionsDoubleTapManageActivity$$ExternalSyntheticLambda1(this), new int[]{Theme.key_windowBackgroundWhite, Theme.key_windowBackgroundWhiteBlackText, Theme.key_windowBackgroundWhiteGrayText2, Theme.key_listSelector, Theme.key_windowBackgroundGray, Theme.key_windowBackgroundWhiteGrayText4, Theme.key_text_RedRegular, Theme.key_windowBackgroundChecked, Theme.key_windowBackgroundCheckText, Theme.key_switchTrackBlue, Theme.key_switchTrackBlueChecked, Theme.key_switchTrackBlueThumb, Theme.key_switchTrackBlueThumbChecked});
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.reactionsDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.reactionsDidLoad);
        getNotificationCenter().removeObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    public void showSelectStatusDialog(SetDefaultReactionCell r15) {
        if (this.selectAnimatedEmojiDialog == null) goto L5;
        return;
    L5:
        SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r10 = new SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[1];
        if (r15 == null) goto L11;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r1 = SetDefaultReactionCell.m18465$$Nest$fgetimageDrawable(r15);
        if (SetDefaultReactionCell.m18465$$Nest$fgetimageDrawable(r15) == null) goto L10;
        SetDefaultReactionCell.m18465$$Nest$fgetimageDrawable(r15).play();
        r15.updateImageBounds();
        Rect r2 = AndroidUtilities.rectTmp2;
        r2.set(SetDefaultReactionCell.m18465$$Nest$fgetimageDrawable(r15).getBounds());
        int r3 = (-(r15.getHeight() - r2.centerY())) - AndroidUtilities.dp(16.0f);
        int r22 = r2.centerX() - ((r15.getRight() - AndroidUtilities.dp(12.0f)) - ((int) Math.min(AndroidUtilities.dp(324.0f), AndroidUtilities.displaySize.x * 0.95f)));
        SetDefaultReactionCell r12 = r15;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r11 = r1;
        int r13 = r3;
    L12:
        AnonymousClass3 r14 = new AnonymousClass3(this, this, getContext(), false, Integer.valueOf(r22), 2, null, r15, r10);
        String r142 = getMediaDataController().getDoubleTapReaction();
        if (r142 != null) goto L15;
    L18:
        List<TLRPC.TL_availableReaction> r143 = getAvailableReactions();
        ArrayList r152 = new ArrayList(20);
        int r32 = 0;
    L20:
        if (r32 >= r143.size()) goto L22;
        ReactionsLayoutInBubble.VisibleReaction r4 = new ReactionsLayoutInBubble.VisibleReaction();
        r4.emojicon = r143.get(r32).reaction;
        r152.add(r4);
        r32 = r32 + 1;
        goto L20
    L22:
        r14.setRecentReactions(r152);
        r14.setSaveState(3);
        r14.setScrimDrawable(r11, r12);
        AnonymousClass4 r144 = new AnonymousClass4(this, r14, -2, -2);
        this.selectAnimatedEmojiDialog = r144;
        r10[0] = r144;
        r144.showAsDropDown(r15, 0, r13, 53);
        r10[0].dimBehind();
        return;
    L15:
        if (r142.startsWith("animated_") == false) goto L18;
        r14.setSelected(Long.valueOf(Long.parseLong(r142.substring(9))));     // Catch: Exception -> L24
        goto L18
    L10:
        r12 = r15;
        r22 = 0;
        r13 = 0;
        r11 = r1;
        goto L12
    L11:
        r22 = 0;
        r13 = 0;
        r11 = null;
        r12 = null;
        goto L12
    }
}
