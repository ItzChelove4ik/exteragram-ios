package org.telegram.ui.Adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.HashtagSearchController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Components.AvatarsDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoriesListPlaceProvider;

/* loaded from: classes6.dex */
public class MessagesSearchAdapter extends RecyclerListView.SelectionAdapter implements NotificationCenter.NotificationCenterDelegate {
    public boolean containsStories;
    private int currentAccount;
    public int flickerCount;
    private final BaseFragment fragment;
    private boolean isSavedMessages;
    private Runnable loadStories;
    public int loadedCount;
    private final Context mContext;
    private final HashSet<Integer> messageIds;
    private final Theme.ResourcesProvider resourcesProvider;
    private final ArrayList<MessageObject> searchResultMessages;
    private int searchType;
    public StoriesController.SearchStoriesList storiesList;
    public String storiesListQuery;

    /* renamed from: org.telegram.ui.Adapters.MessagesSearchAdapter$1, reason: invalid class name */
    public class AnonymousClass1 implements DialogCell.DialogCellDelegate {
        final /* synthetic */ MessagesSearchAdapter this$0;

        public AnonymousClass1(MessagesSearchAdapter r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public boolean canClickButtonInside() {
            return false;
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void onButtonClicked(DialogCell r1) {
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void onButtonLongPress(DialogCell r1) {
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void openHiddenStories() {
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void openStory(DialogCell r4, Runnable r5) {
            if (MessagesController.getInstance(MessagesSearchAdapter.m5938$$Nest$fgetcurrentAccount(this.this$0)).getStoriesController().hasStories(r4.getDialogId()) == false) goto L6;
            MessagesSearchAdapter.m5939$$Nest$fgetfragment(this.this$0).getOrCreateStoryViewer().doOnAnimationReady(r5);
            MessagesSearchAdapter.m5939$$Nest$fgetfragment(this.this$0).getOrCreateStoryViewer().open(MessagesSearchAdapter.m5940$$Nest$fgetmContext(this.this$0), r4.getDialogId(), StoriesListPlaceProvider.of((RecyclerListView) r4.getParent()));
            return;
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void showChatPreview(DialogCell r1) {
        }
    }

    public static class StoriesView extends FrameLayout {
        private final ImageView arrowView;
        private final AvatarsDrawable avatarsDrawable;
        private final Theme.ResourcesProvider resourcesProvider;
        private final TextView[] subtitleTextView;
        private final TextView[] titleTextView;
        private float transitValue;
        private ValueAnimator transitionAnimator;

        /* renamed from: org.telegram.ui.Adapters.MessagesSearchAdapter$StoriesView$1, reason: invalid class name */
        public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
            final /* synthetic */ StoriesView this$0;

            public AnonymousClass1(StoriesView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator r10) {
                StoriesView.m5944$$Nest$fputtransitValue(this.this$0, ((Float) r10.getAnimatedValue()).floatValue());
                this.this$0.invalidate();
                int r0 = 0;
            L4:
                if (r0 >= 2) goto L20;
                StoriesView.m5942$$Nest$fgettitleTextView(this.this$0)[r0].setTranslationX(AndroidUtilities.lerp(0, -AndroidUtilities.dp(62.0f), StoriesView.m5943$$Nest$fgettransitValue(this.this$0)));
                StoriesView.m5942$$Nest$fgettitleTextView(this.this$0)[r0].setVisibility(0);
                TextView r1 = StoriesView.m5942$$Nest$fgettitleTextView(this.this$0)[r0];
                float r3 = 0.0f;
                if (r0 != 0) goto L8;
                float r5 = 1.0f;
            L10:
                if (r0 != 1) goto L12;
                float r7 = 1.0f;
            L13:
                r1.setAlpha(AndroidUtilities.lerp(r5, r7, StoriesView.m5943$$Nest$fgettransitValue(this.this$0)));
                StoriesView.m5941$$Nest$fgetsubtitleTextView(this.this$0)[r0].setTranslationX(AndroidUtilities.lerp(0, -AndroidUtilities.dp(62.0f), StoriesView.m5943$$Nest$fgettransitValue(this.this$0)));
                StoriesView.m5941$$Nest$fgetsubtitleTextView(this.this$0)[r0].setVisibility(0);
                TextView r12 = StoriesView.m5941$$Nest$fgetsubtitleTextView(this.this$0)[r0];
                if (r0 != 0) goto L16;
                float r2 = 1.0f;
            L17:
                if (r0 != 1) goto L19;
                r3 = 1.0f;
            L19:
                r12.setAlpha(AndroidUtilities.lerp(r2, r3, StoriesView.m5943$$Nest$fgettransitValue(this.this$0)));
                r0 = r0 + 1;
                goto L4
            L16:
                r2 = 0.0f;
                goto L17
            L12:
                r7 = 0.0f;
                goto L13
            L8:
                r5 = 0.0f;
                goto L10
            }
        }

        /* renamed from: org.telegram.ui.Adapters.MessagesSearchAdapter$StoriesView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ StoriesView this$0;
            final /* synthetic */ boolean val$stories;

            public AnonymousClass2(StoriesView r1, boolean r2) {
                this.this$0 = r1;
                this.val$stories = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r11) {
                StoriesView r112 = this.this$0;
                if (this.val$stories == false) goto L5;
                float r0 = 1.0f;
            L6:
                StoriesView.m5944$$Nest$fputtransitValue(r112, r0);
                this.this$0.invalidate();
                int r02 = 0;
            L8:
                if (r02 >= 2) goto L39;
                StoriesView.m5942$$Nest$fgettitleTextView(this.this$0)[r02].setTranslationX(AndroidUtilities.lerp(0, -AndroidUtilities.dp(62.0f), StoriesView.m5943$$Nest$fgettransitValue(this.this$0)));
                TextView r3 = StoriesView.m5942$$Nest$fgettitleTextView(this.this$0)[r02];
                if (r02 != 1) goto L12;
                boolean r6 = true;
            L13:
                int r8 = 8;
                if (r6 != this.val$stories) goto L16;
                int r62 = 0;
            L17:
                r3.setVisibility(r62);
                TextView r32 = StoriesView.m5942$$Nest$fgettitleTextView(this.this$0)[r02];
                if (r02 != 0) goto L20;
                float r63 = 1.0f;
            L21:
                if (r02 != 1) goto L23;
                float r7 = 1.0f;
            L24:
                r32.setAlpha(AndroidUtilities.lerp(r63, r7, StoriesView.m5943$$Nest$fgettransitValue(this.this$0)));
                StoriesView.m5941$$Nest$fgetsubtitleTextView(this.this$0)[r02].setTranslationX(AndroidUtilities.lerp(0, -AndroidUtilities.dp(62.0f), StoriesView.m5943$$Nest$fgettransitValue(this.this$0)));
                TextView r33 = StoriesView.m5941$$Nest$fgetsubtitleTextView(this.this$0)[r02];
                if (r02 != 1) goto L27;
                boolean r4 = true;
            L29:
                if (r4 != this.val$stories) goto L31;
                r8 = 0;
            L31:
                r33.setVisibility(r8);
                TextView r34 = StoriesView.m5941$$Nest$fgetsubtitleTextView(this.this$0)[r02];
                if (r02 != 0) goto L34;
                float r42 = 1.0f;
            L35:
                if (r02 != 1) goto L37;
                float r5 = 1.0f;
            L38:
                r34.setAlpha(AndroidUtilities.lerp(r42, r5, StoriesView.m5943$$Nest$fgettransitValue(this.this$0)));
                r02 = r02 + 1;
                goto L8
            L37:
                r5 = 0.0f;
                goto L38
            L34:
                r42 = 0.0f;
                goto L35
            L27:
                r4 = false;
                goto L29
            L23:
                r7 = 0.0f;
                goto L24
            L20:
                r63 = 0.0f;
                goto L21
            L16:
                r62 = 8;
                goto L17
            L12:
                r6 = false;
                goto L13
            L39:
                return;
            L5:
                r0 = 0.0f;
                goto L6
            }
        }

        public static class Factory extends UItem.UItemFactory<StoriesView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asStoriesList(StoriesController.SearchStoriesList r1) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((StoriesView) r1).set((StoriesController.SearchStoriesList) r2.object);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public StoriesView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new StoriesView(r1, r5);
            }
        }

        /* renamed from: -$$Nest$fgetsubtitleTextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView[] m5941$$Nest$fgetsubtitleTextView(StoriesView r0) {
            return r0.subtitleTextView;
        }

        /* renamed from: -$$Nest$fgettitleTextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView[] m5942$$Nest$fgettitleTextView(StoriesView r0) {
            return r0.titleTextView;
        }

        /* renamed from: -$$Nest$fgettransitValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m5943$$Nest$fgettransitValue(StoriesView r0) {
            return r0.transitValue;
        }

        /* renamed from: -$$Nest$fputtransitValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5944$$Nest$fputtransitValue(StoriesView r0, float r1) {
            r0.transitValue = r1;
        }

        public StoriesView(Context r14, Theme.ResourcesProvider r15) {
            super(r14);
            this.titleTextView = new TextView[2];
            this.subtitleTextView = new TextView[2];
            this.resourcesProvider = r15;
            setWillNotDraw(false);
            AvatarsDrawable r2 = new AvatarsDrawable(this, false);
            this.avatarsDrawable = r2;
            r2.setCentered(true);
            r2.width = AndroidUtilities.dp(75.0f);
            r2.height = AndroidUtilities.dp(48.0f);
            r2.drawStoriesCircle = true;
            r2.setSize(AndroidUtilities.dp(22.0f));
            int r22 = 0;
        L3:
            if (r22 >= 2) goto L12;
            this.titleTextView[r22] = new TextView(r14);
            this.titleTextView[r22].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r15));
            this.titleTextView[r22].setTypeface(AndroidUtilities.bold());
            this.titleTextView[r22].setTextSize(1, 14.0f);
            TextView r4 = this.titleTextView[r22];
            int r5 = 8;
            if (r22 != 0) goto L7;
            int r6 = 0;
        L8:
            r4.setVisibility(r6);
            addView(this.titleTextView[r22], LayoutHelper.createFrame(-1, -2.0f, 48, 76.0f, 7.0f, 40.0f, 0.0f));
            this.subtitleTextView[r22] = new TextView(r14);
            this.subtitleTextView[r22].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r15));
            this.subtitleTextView[r22].setTextSize(1, 12.0f);
            TextView r42 = this.subtitleTextView[r22];
            if (r22 != 0) goto L11;
            r5 = 0;
        L11:
            r42.setVisibility(r5);
            addView(this.subtitleTextView[r22], LayoutHelper.createFrame(-1, -2.0f, 48, 76.0f, 26.33f, 40.0f, 0.0f));
            r22 = r22 + 1;
            goto L3
        L7:
            r6 = 8;
            goto L8
        L12:
            ImageView r0 = new ImageView(r14);
            this.arrowView = r0;
            r0.setImageResource(R.drawable.msg_arrowright);
            r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogSearchHint, r15), PorterDuff.Mode.SRC_IN));
            addView(r0, LayoutHelper.createFrame(24, 24.0f, 21, 0.0f, 0.0f, 8.66f, 0.0f));
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            if (this.transitValue <= 0.0f) goto L5;
            r10.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) ((1.0f - this.transitValue) * 255.0f), 31);
        L6:
            r10.translate(AndroidUtilities.lerp(0, -AndroidUtilities.dp(62.0f), this.transitValue), 0.0f);
            this.avatarsDrawable.onDraw(r10);
            r10.restore();
            super.onDraw(r10);
            Paint r2 = Theme.getThemePaint("paintDivider", this.resourcesProvider);
            if (r2 != null) goto L9;
            r2 = Theme.dividerPaint;
        L9:
            r10.drawRect(0.0f, getHeight() - 1, getWidth(), getHeight(), r2);
            return;
        L5:
            r10.save();
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        }

        public boolean set(StoriesController.SearchStoriesList r7) {
            int r1 = 0;
            int r2 = 0;
        L4:
            if (r1 >= r7.messageObjects.size()) goto L8;
            if (r2 >= 3) goto L8;
            MessageObject r3 = r7.messageObjects.get(r1);
            long r4 = r3.storyItem.dialogId;
            TextUtils.isEmpty(r7.username);
            this.avatarsDrawable.setObject(r2, r7.currentAccount, r3.storyItem);
            r2 = r2 + 1;
            r1 = r1 + 1;
        L8:
            this.avatarsDrawable.setCount(r2);
            this.avatarsDrawable.commitTransition(false);
            boolean r12 = TextUtils.isEmpty(r7.username);
            TextView[] r32 = this.titleTextView;
            if (r12 == true) goto L11;
            r32[0].setText(AndroidUtilities.replaceSingleLink(LocaleController.formatPluralStringSpaced("HashtagStoriesFoundChannel", r7.getCount(), new Object[]{"@" + r7.username}), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), null));
        L12:
            this.subtitleTextView[0].setText(LocaleController.formatString(R.string.HashtagStoriesFoundSubtitle, new Object[]{r7.query}));
            if (r2 <= 0) goto L16;
            return true;
        L16:
            return false;
        L11:
            r32[0].setText(LocaleController.formatPluralStringSpaced("HashtagStoriesFound", r7.getCount()));
            goto L12
        }

        public void setMessages(int r5, String r6, String r7) {
            boolean r0 = TextUtils.isEmpty(r7);
            TextView[] r1 = this.titleTextView;
            if (r0 == true) goto L5;
            r1[1].setText(AndroidUtilities.replaceSingleLink(LocaleController.formatPluralStringSpaced("HashtagMessagesFoundChannel", r5, new Object[]{"@" + r7}), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), null));
        L6:
            this.subtitleTextView[1].setText(LocaleController.formatString(R.string.HashtagMessagesFoundSubtitle, new Object[]{r6}));
            return;
        L5:
            r1[1].setText(LocaleController.formatPluralStringSpaced("HashtagMessagesFound", r5));
            goto L6
        }

        public void transition(boolean r5) {
            ValueAnimator r0 = this.transitionAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            float r02 = this.transitValue;
            if (r5 == false) goto L8;
            float r1 = 1.0f;
        L9:
            ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
            this.transitionAnimator = r03;
            r03.addUpdateListener(new AnonymousClass1(this));
            this.transitionAnimator.addListener(new AnonymousClass2(this, r5));
            this.transitionAnimator.setDuration(320);
            this.transitionAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.transitionAnimator.start();
            return;
        L8:
            r1 = 0.0f;
            goto L9
        }
    }

    public static /* synthetic */ void $r8$lambda$lw9t0D0zkQBTKIzzDoQaAG9usW0(MessagesSearchAdapter r0) {
        r0.lambda$new$0();
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5938$$Nest$fgetcurrentAccount(MessagesSearchAdapter r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m5939$$Nest$fgetfragment(MessagesSearchAdapter r0) {
        return r0.fragment;
    }

    /* renamed from: -$$Nest$fgetmContext, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Context m5940$$Nest$fgetmContext(MessagesSearchAdapter r0) {
        return r0.mContext;
    }

    public MessagesSearchAdapter(Context r2, BaseFragment r3, Theme.ResourcesProvider r4, int r5, boolean r6) {
        this.messageIds = new HashSet();
        this.searchResultMessages = new ArrayList();
        this.currentAccount = UserConfig.selectedAccount;
        this.loadStories = new MessagesSearchAdapter$$ExternalSyntheticLambda0(this);
        this.resourcesProvider = r4;
        this.mContext = r2;
        this.fragment = r3;
        this.searchType = r5;
        this.isSavedMessages = r6;
    }

    private /* synthetic */ void lambda$new$0() {
        StoriesController.SearchStoriesList r2 = this.storiesList;
        if (r2 == null) goto L6;
        r2.load(true, 3);
        return;
    }

    public void attach() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesListUpdated);
    }

    public void detach() {
        AndroidUtilities.cancelRunOnUIThread(this.loadStories);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesListUpdated);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.storiesListUpdated) goto L5;
        return;
    L5:
        if (r3[0] != this.storiesList) goto L9;
        notifyDataSetChanged();
        return;
    }

    public Object getItem(int r2) {
        if (this.containsStories == false) goto L5;
        r2 = r2 - 1;
    L5:
        if (r2 >= 0) goto L7;
        return null;
    L7:
        if (r2 < this.searchResultMessages.size()) goto L10;
        return null;
    L10:
        return this.searchResultMessages.get(r2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        boolean r0 = this.containsStories;
        int r1 = this.searchResultMessages.size();
        return ((r0 ? 1 : 0) + r1) + this.flickerCount;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r2) {
        if (this.containsStories == false) goto L9;
        r2 = r2 - 1;
        if (r2 != (-1)) goto L9;
        return 2;
    L9:
        if (r2 >= this.searchResultMessages.size()) goto L12;
        return 0;
    L12:
        return 1;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r1) {
        if (r1.getItemViewType() != 0) goto L5;
        return true;
    L5:
        if (r1.getItemViewType() == 2) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void notifyDataSetChanged() {
        int r0 = getItemCount();
        int r1 = 0;
        this.containsStories = false;
        this.searchResultMessages.clear();
        this.messageIds.clear();
        int r2 = this.searchType;
        if (r2 != 0) goto L5;
        ArrayList<MessageObject> r22 = MediaDataController.getInstance(this.currentAccount).getFoundMessageObjects();
    L8:
        int r4 = 0;
    L10:
        if (r4 >= r22.size()) goto L19;
        MessageObject r5 = r22.get(r4);
        if (r5.hasValidGroupId() == false) goto L16;
        if (r5.isPrimaryGroupMessage == true) goto L16;
    L18:
        r4 = r4 + 1;
    L16:
        if (this.messageIds.contains(Integer.valueOf(r5.getId())) == true) goto L18;
        this.searchResultMessages.add(r5);
        this.messageIds.add(Integer.valueOf(r5.getId()));
        goto L18
    L19:
        int r23 = this.flickerCount;
        this.loadedCount = this.searchResultMessages.size();
        int r42 = this.searchType;
        if (r42 != 4) goto L22;
        this.flickerCount = 0;
    L36:
        int r12 = getItemCount();
        if (r0 >= r12) goto L42;
        if (r23 <= 0) goto L40;
        notifyItemRangeChanged(r0 - r23, r23);
    L40:
        notifyItemRangeInserted(r0, r12 - r0);
        return;
    L42:
        super.notifyDataSetChanged();
        return;
    L22:
        int r3 = this.currentAccount;
        if (r42 == 0) goto L31;
        if (HashtagSearchController.getInstance(r3).isEndReached(this.searchType) == false) goto L27;
    L29:
        this.flickerCount = r1;
        goto L36
    L27:
        if (this.loadedCount == 0) goto L29;
        r1 = Utilities.clamp(HashtagSearchController.getInstance(this.currentAccount).getCount(this.searchType) - this.loadedCount, 3, 0);
        goto L29
    L31:
        if (MediaDataController.getInstance(r3).searchEndReached() == false) goto L33;
    L35:
        this.flickerCount = r1;
        goto L36
    L33:
        if (this.loadedCount == 0) goto L35;
        r1 = Utilities.clamp(MediaDataController.getInstance(this.currentAccount).getSearchCount() - this.loadedCount, 3, 0);
        goto L35
    L5:
        if (r2 != 4) goto L7;
        r22 = new ArrayList();
        goto L8
    L7:
        r22 = HashtagSearchController.getInstance(this.currentAccount).getMessages(this.searchType);
        goto L8
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
        if (r8.getItemViewType() != 0) goto L26;
        DialogCell r0 = (DialogCell) r8.itemView;
        r0.useSeparator = true;
        MessageObject r3 = (MessageObject) getItem(r9);
        long r1 = r3.getFromChatId();
        int r92 = r3.messageOwner.date;
        if (this.isSavedMessages == false) goto L18;
        r0.isSavedDialog = true;
        long r82 = r3.getSavedDialogId();
        TLRPC.Message r12 = r3.messageOwner;
        TLRPC.MessageFwdHeader r2 = r12.fwd_from;
        if (r2 == null) goto L16;
        int r5 = r2.date;
        if (r5 == 0) goto L11;
    L12:
        if (r5 != 0) goto L15;
        int r13 = r2.saved_date;
    L14:
        boolean r52 = false;
        int r4 = r13;
        r1 = r82;
    L23:
        r0.setDialog(r1, r3, r4, r52, false);
        r0.setDialogCellDelegate(new AnonymousClass1(this));
        return;
    L15:
        r13 = r5;
        goto L14
    L11:
        if (r2.saved_date != 0) goto L12;
    L16:
        r13 = r12.date;
        goto L14
    L18:
        if (r3.isOutOwner() == false) goto L20;
    L21:
        r1 = r3.getFromChatId();
    L22:
        r52 = true;
        r4 = r92;
        goto L23
    L20:
        if (ChatObject.isMonoForum(this.currentAccount, r1) == false) goto L22;
    L26:
        if (r8.getItemViewType() != 2) goto L29;
        ((StoriesView) r8.itemView).set(this.storiesList);
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r8, int r9) {
        if (r9 != 0) goto L4;
        View r7 = new DialogCell(null, this.mContext, false, true, this.currentAccount, this.resourcesProvider);
    L11:
        r7.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        return new RecyclerListView.Holder(r7);
    L4:
        if (r9 != 1) goto L6;
        FlickerLoadingView r92 = new FlickerLoadingView(this.mContext, this.resourcesProvider);
        r92.setIsSingleCell(true);
        r92.setViewType(7);
        r7 = r92;
        goto L11
    L6:
        if (r9 == 2) goto L8;
        r7 = null;
        goto L11
    L8:
        r7 = new StoriesView(this.mContext, this.resourcesProvider);
        goto L11
    }

    public void searchStories(String r8, boolean r9) {
        if (TextUtils.equals(this.storiesListQuery, r8) == true) goto L32;
        String r0 = r8.trim();
        boolean r1 = false;
        String r5 = null;
        if (r0.charAt(0) != '$') goto L8;
    L11:
        int r2 = r0.indexOf(64);
        if (r2 < 0) goto L14;
        String r3 = r0.substring(0, r2);
        r5 = r0.substring(r2 + 1);
        r0 = r3;
    L14:
        boolean r22 = this.containsStories;
        AndroidUtilities.cancelRunOnUIThread(this.loadStories);
        StoriesController.SearchStoriesList r32 = this.storiesList;
        if (r32 == null) goto L18;
        r32.cancel();
    L18:
        if (TextUtils.isEmpty(r0) == true) goto L23;
        this.storiesListQuery = r8;
        this.storiesList = new StoriesController.SearchStoriesList(this.currentAccount, r5, r0);
        Runnable r82 = this.loadStories;
        if (r9 == false) goto L22;
        r82.run();
        goto L23
    L22:
        AndroidUtilities.runOnUIThread(r82, 1000);
    L23:
        StoriesController.SearchStoriesList r83 = this.storiesList;
        if (r83 != null) goto L26;
    L28:
        if (r1 == r22) goto L31;
        notifyDataSetChanged();
        return;
    L31:
        return;
    L26:
        if (r83.getCount() <= 0) goto L28;
        r1 = true;
        goto L28
    L8:
        if (r0.charAt(0) == '#') goto L11;
        r0 = null;
        goto L14
    }
}
