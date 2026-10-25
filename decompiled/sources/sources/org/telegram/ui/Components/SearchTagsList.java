package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.AlertDialogDecor;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CounterView;
import org.telegram.ui.Components.FloatingDebug.FloatingDebugView$$ExternalSyntheticLambda10;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public abstract class SearchTagsList extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private static AlertDialog currentDialog;
    private ValueAnimator actionBarTagsAnimator;
    private float actionBarTagsT;
    private final Adapter adapter;
    private BlurredBackgroundProvider blurredColorProvider;
    private BlurredBackgroundDrawableViewFactory blurredFactory;
    private long chosen;
    private final int currentAccount;
    private final BaseFragment fragment;
    private final ArrayList<Item> items;
    public final RecyclerListView listView;
    private final ArrayList<Item> oldItems;
    private LinearLayout premiumLayout;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean shownPremiumLayout;
    public float shownT;
    private final Paint strokePaint;
    private long topicId;

    /* renamed from: org.telegram.ui.Components.SearchTagsList$1, reason: invalid class name */
    public class AnonymousClass1 extends TextView {
        private final RectF bounds;
        private final Paint paint;
        private final Path path;
        final /* synthetic */ SearchTagsList this$0;

        public AnonymousClass1(SearchTagsList r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.path = new Path();
            this.bounds = new RectF();
            this.paint = new Paint();
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r5) {
            this.paint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText2, SearchTagsList.m12581$$Nest$fgetresourcesProvider(this.this$0)), 0.15f));
            this.bounds.set(0.0f, 0.0f, getWidth(), getHeight());
            ReactionsLayoutInBubble.fillTagPath(this.bounds, this.path);
            r5.drawPath(this.path, this.paint);
            super.dispatchDraw(r5);
        }

        @Override // android.widget.TextView, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r12 = getWidth();
            int r22 = 0;
            int r32 = 0;
        L4:
            if (r22 >= this.this$0.getChildCount()) goto L6;
            r12 = Math.min(r12, this.this$0.getChildAt(r22).getLeft());
            r32 = Math.max(r32, this.this$0.getChildAt(r22).getRight());
            r22 = r22 + 1;
            goto L4
        L6:
            setPivotX((r12 + r32) / 2.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchTagsList$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ SearchTagsList this$0;

        public AnonymousClass2(SearchTagsList r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (SearchTagsList.m12580$$Nest$fgetpremiumLayout(this.this$0) == null) goto L9;
            if (SearchTagsList.m12580$$Nest$fgetpremiumLayout(this.this$0).getAlpha() <= 0.5f) goto L9;
            return false;
        L9:
            return super.dispatchTouchEvent(r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public Integer getSelectorColor(int r1) {
            return 0;
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchTagsList$3, reason: invalid class name */
    public class AnonymousClass3 extends DefaultItemAnimator {
        final /* synthetic */ SearchTagsList this$0;

        public AnonymousClass3(SearchTagsList r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.SimpleItemAnimator
        public boolean animateMove(RecyclerView.ViewHolder r8, RecyclerView.ItemAnimator.ItemHolderInfo r9, int r10, int r11, int r12, int r13) {
            View r92 = r8.itemView;
            if ((r92 instanceof TagButton) == false) goto L5;
            ((TagButton) r92).startAnimate();
        L5:
            int r3 = r10 + ((int) r8.itemView.getTranslationX());
            int r4 = r11 + ((int) r8.itemView.getTranslationY());
            resetAnimation(r8);
            int r102 = r12 - r3;
            int r112 = r13 - r4;
            if (r102 != 0) goto L10;
            if (r112 != 0) goto L10;
            dispatchMoveFinished(r8);
            return false;
        L10:
            if (r102 == 0) goto L12;
            r92.setTranslationX(-r102);
        L12:
            if (r112 == 0) goto L14;
            r92.setTranslationY(-r112);
        L14:
            this.mPendingMoves.add(new DefaultItemAnimator.MoveInfo(r8, r3, r4, r12, r13));
            checkIsRunning();
            return true;
        }

        @Override // androidx.recyclerview.widget.SimpleItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
        public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder r1) {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchTagsList$4, reason: invalid class name */
    public class AnonymousClass4 extends EditTextBoldCursor {
        AnimatedTextView.AnimatedTextDrawable limit;
        AnimatedColor limitColor;
        private int limitCount;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass4(Context r8, Theme.ResourcesProvider r9) {
            this.val$resourcesProvider = r9;
            super(r8);
            this.limitColor = new AnimatedColor(this);
            AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
            this.limit = r0;
            r0.setAnimationProperties(0.2f, 0, 160, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.limit.setTextSize(AndroidUtilities.dp(15.33f));
            this.limit.setCallback(this);
            this.limit.setGravity(5);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            AnimatedTextView.AnimatedTextDrawable r0 = this.limit;
            AnimatedColor r1 = this.limitColor;
            if (this.limitCount >= 0) goto L5;
            int r2 = Theme.key_text_RedRegular;
        L6:
            r0.setTextColor(r1.set(Theme.getColor(r2, this.val$resourcesProvider)));
            this.limit.setBounds(getScrollX(), 0, getScrollX() + getWidth(), getHeight());
            this.limit.draw(r6);
            return;
        L5:
            r2 = Theme.key_dialogSearchHint;
            goto L6
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(36.0f), TLObject.FLAG_30));
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            super.onTextChanged(r1, r2, r3, r4);
            if (this.limit == null) goto L10;
            this.limitCount = 12 - r1.length();
            this.limit.cancelAnimation();
            AnimatedTextView.AnimatedTextDrawable r12 = this.limit;
            int r22 = this.limitCount;
            String r42 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r22 > 4) goto L8;
            r42 = _UrlKt.FRAGMENT_ENCODE_SET + this.limitCount;
        L8:
            r12.setText(r42);
            return;
        }

        @Override // android.widget.TextView, android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.limit) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchTagsList$5, reason: invalid class name */
    public class AnonymousClass5 implements TextView.OnEditorActionListener {
        final /* synthetic */ int val$currentAccount;
        final /* synthetic */ View val$currentFocus;
        final /* synthetic */ AlertDialog[] val$dialog;
        final /* synthetic */ EditTextBoldCursor val$editText;
        final /* synthetic */ TLRPC.Reaction val$reaction;

        public AnonymousClass5(EditTextBoldCursor r1, int r2, TLRPC.Reaction r3, AlertDialog[] r4, View r5) {
            this.val$editText = r1;
            this.val$currentAccount = r2;
            this.val$reaction = r3;
            this.val$dialog = r4;
            this.val$currentFocus = r5;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView r3, int r4, KeyEvent r5) {
            if (r4 != 6) goto L18;
            String r32 = this.val$editText.getText().toString();
            if (r32.length() <= 12) goto L8;
            AndroidUtilities.shakeView(this.val$editText);
            return true;
        L8:
            MessagesController.getInstance(this.val$currentAccount).renameSavedReactionTag(ReactionsLayoutInBubble.VisibleReaction.fromTL(this.val$reaction), r32);
            AlertDialog r33 = this.val$dialog[0];
            if (r33 == null) goto L12;
            r33.dismiss();
        L12:
            if (this.val$dialog[0] != SearchTagsList.m12584$$Nest$sfgetcurrentDialog()) goto L14;
            SearchTagsList.m12585$$Nest$sfputcurrentDialog(null);
        L14:
            View r2 = this.val$currentFocus;
            if (r2 == null) goto L17;
            r2.requestFocus();
        L17:
            return true;
        L18:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchTagsList$6, reason: invalid class name */
    public class AnonymousClass6 extends DiffUtil.Callback {
        final /* synthetic */ SearchTagsList this$0;

        public AnonymousClass6(SearchTagsList r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int r2, int r3) {
            return ((Item) SearchTagsList.m12579$$Nest$fgetoldItems(this.this$0).get(r2)).equals(SearchTagsList.m12578$$Nest$fgetitems(this.this$0).get(r3));
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int r3, int r4) {
            if (((Item) SearchTagsList.m12579$$Nest$fgetoldItems(this.this$0).get(r3)).hash() != ((Item) SearchTagsList.m12578$$Nest$fgetitems(this.this$0).get(r4)).hash()) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return SearchTagsList.m12578$$Nest$fgetitems(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return SearchTagsList.m12579$$Nest$fgetoldItems(this.this$0).size();
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchTagsList$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ SearchTagsList this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass7(SearchTagsList r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2 == SearchTagsList.m12572$$Nest$fgetactionBarTagsAnimator(this.this$0)) goto L5;
            return;
        L5:
            SearchTagsList r22 = this.this$0;
            if (this.val$show == false) goto L8;
            float r0 = 1.0f;
        L9:
            SearchTagsList.m12583$$Nest$fputactionBarTagsT(r22, r0);
            SearchTagsList r23 = this.this$0;
            r23.setShown(SearchTagsList.m12573$$Nest$fgetactionBarTagsT(r23));
            if (this.val$show == true) goto L12;
            this.this$0.setVisibility(8);
        L12:
            this.this$0.onShownUpdate(true);
            return;
        L8:
            r0 = 0.0f;
            goto L9
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ SearchTagsList this$0;

        public Adapter(SearchTagsList r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return SearchTagsList.m12578$$Nest$fgetitems(this.this$0).size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
            if (r6 >= 0) goto L4;
            return;
        L4:
            if (r6 >= SearchTagsList.m12578$$Nest$fgetitems(this.this$0).size()) goto L13;
            Item r62 = (Item) SearchTagsList.m12578$$Nest$fgetitems(this.this$0).get(r6);
            ((TagButton) r5.itemView).set(r62);
            TagButton r52 = (TagButton) r5.itemView;
            if (r62.hash() != SearchTagsList.m12576$$Nest$fgetchosen(this.this$0)) goto L9;
            boolean r4 = true;
        L10:
            r52.setChosen(r4, false);
            return;
        L9:
            r4 = false;
            goto L10
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            SearchTagsList r0 = this.this$0;
            return new RecyclerListView.Holder(new TagButton(r0, r0.getContext()));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r5) {
            super.onViewAttachedToWindow(r5);
            int r0 = r5.getAdapterPosition();
            if (r0 >= 0) goto L5;
            return;
        L5:
            if (r0 >= SearchTagsList.m12578$$Nest$fgetitems(this.this$0).size()) goto L14;
            Item r02 = (Item) SearchTagsList.m12578$$Nest$fgetitems(this.this$0).get(r0);
            TagButton r52 = (TagButton) r5.itemView;
            if (r02.hash() != SearchTagsList.m12576$$Nest$fgetchosen(this.this$0)) goto L10;
            boolean r4 = true;
        L11:
            r52.setChosen(r4, false);
            return;
        L10:
            r4 = false;
            goto L11
        }
    }

    public static class Item {
        int count;
        String name;
        int nameHash;
        ReactionsLayoutInBubble.VisibleReaction reaction;

        private Item() {
        }

        public static Item get(ReactionsLayoutInBubble.VisibleReaction r1, int r2, String r3) {
            Item r0 = new Item();
            r0.reaction = r1;
            r0.count = r2;
            r0.name = r3;
            if (r3 != null) goto L5;
            int r12 = -233;
        L6:
            r0.nameHash = r12;
            return r0;
        L5:
            r12 = r3.hashCode();
            goto L6
        }

        public boolean equals(Object r7) {
            if ((r7 instanceof Item) == true) goto L5;
            return false;
        L5:
            Item r72 = (Item) r7;
            if (this.count == r72.count) goto L8;
        L13:
            return false;
        L8:
            if (this.reaction.hash != r72.reaction.hash) goto L13;
            if (this.nameHash != r72.nameHash) goto L13;
            return true;
        }

        public long hash() {
            return this.reaction.hash;
        }
    }

    public class TagButton extends View {
        private boolean attached;
        private BlurredBackgroundDrawable blurredDrawable;
        private boolean chosen;
        private final Path clipPath;
        private final RectF clipPathRect;
        private final RectF clipPathTmpRect;
        private ReactionsLayoutInBubble.VisibleReaction lastReaction;
        private final AnimatedFloat progress;
        public ReactionsLayoutInBubble.ReactionButton reactionButton;
        final /* synthetic */ SearchTagsList this$0;

        /* renamed from: org.telegram.ui.Components.SearchTagsList$TagButton$1, reason: invalid class name */
        public class AnonymousClass1 extends ReactionsLayoutInBubble.ReactionButton {
            final /* synthetic */ TagButton this$1;

            public AnonymousClass1(TagButton r1, ReactionsLayoutInBubble.ReactionButton r2, int r3, View r4, TLRPC.ReactionCount r5, boolean r6, boolean r7, Theme.ResourcesProvider r8) {
                this.this$1 = r1;
                super(r2, r3, r4, r5, r6, r7, r8);
            }

            @Override // org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble.ReactionButton
            public boolean drawCounter() {
                if (this.count <= 0) goto L5;
                return true;
            L5:
                if (this.hasName == false) goto L7;
                return true;
            L7:
                if (this.counterDrawable.countChangeProgress != 1.0f) goto L14;
                return false;
            L14:
                return true;
            }

            @Override // org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble.ReactionButton
            public boolean drawTagDot() {
                return !drawCounter();
            }

            @Override // org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble.ReactionButton
            public boolean drawTextWithCounter() {
                return true;
            }

            @Override // org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble.ReactionButton
            public int getCacheType() {
                return 18;
            }

            @Override // org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble.ReactionButton
            public void updateColors(float r4) {
                int r0 = this.fromTextColor;
                if (TagButton.m12586$$Nest$fgetchosen(this.this$1) == false) goto L6;
                int r1 = Theme.key_chat_inReactionButtonTextSelected;
            L7:
                this.lastDrawnTextColor = ColorUtils.blendARGB(r0, Theme.getColor(r1, SearchTagsList.m12581$$Nest$fgetresourcesProvider(this.this$1.this$0)), r4);
                int r02 = this.fromBackgroundColor;
                if (TagButton.m12586$$Nest$fgetchosen(this.this$1) == false) goto L10;
                int r12 = Theme.getColor(Theme.key_chat_inReactionButtonBackground, SearchTagsList.m12581$$Nest$fgetresourcesProvider(this.this$1.this$0));
            L11:
                int r03 = ColorUtils.blendARGB(r02, r12, r4);
                this.lastDrawnBackgroundColor = r03;
                this.lastDrawnTextColor = Theme.blendOver(r03, this.lastDrawnTextColor);
                int r04 = this.fromTagDotColor;
                if (TagButton.m12586$$Nest$fgetchosen(this.this$1) == false) goto L14;
                int r13 = 1526726655;
            L15:
                this.lastDrawnTagDotColor = ColorUtils.blendARGB(r04, r13, r4);
                return;
            L14:
                r13 = Theme.getColor(Theme.key_actionBarActionModeReactionDot, SearchTagsList.m12581$$Nest$fgetresourcesProvider(this.this$1.this$0));
                goto L15
            L10:
                r12 = 0;
                goto L11
            L6:
                r1 = Theme.key_actionBarActionModeReactionText;
                goto L7
            }
        }

        /* renamed from: -$$Nest$fgetchosen, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m12586$$Nest$fgetchosen(TagButton r0) {
            return r0.chosen;
        }

        public TagButton(SearchTagsList r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            this.progress = new AnimatedFloat(this, 0, 260, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.clipPath = new Path();
            this.clipPathRect = new RectF();
            this.clipPathTmpRect = new RectF();
            ScaleStateListAnimator.apply(this);
            if (SearchTagsList.m12575$$Nest$fgetblurredFactory(r8) == null) goto L6;
            this.blurredDrawable = SearchTagsList.m12575$$Nest$fgetblurredFactory(r8).create(this).setColorProvider(SearchTagsList.m12574$$Nest$fgetblurredColorProvider(r8)).setThickness(AndroidUtilities.dp(5.0f)).setClipToOutline(false).setRadius(AndroidUtilities.dp(6.0f)).setPadding(AndroidUtilities.dp(4.0f));
            return;
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (this.attached == true) goto L9;
            ReactionsLayoutInBubble.ReactionButton r0 = this.reactionButton;
            if (r0 == null) goto L7;
            r0.attach();
        L7:
            this.attached = true;
            return;
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.attached == false) goto L9;
            ReactionsLayoutInBubble.ReactionButton r0 = this.reactionButton;
            if (r0 == null) goto L7;
            r0.detach();
        L7:
            this.attached = false;
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            int r2 = (getWidth() - this.reactionButton.width) / 2;
            int r3 = getHeight();
            ReactionsLayoutInBubble.ReactionButton r4 = this.reactionButton;
            int r5 = r4.height;
            int r32 = (r3 - r5) / 2;
            if (this.blurredDrawable == null) goto L17;
            Rect r6 = AndroidUtilities.rectTmp2;
            r6.set(r2, r32, r4.width + r2, r5 + r32);
            this.clipPathTmpRect.set(r6);
            if (this.clipPathTmpRect.equals(this.clipPathRect) == true) goto L7;
            this.clipPathRect.set(this.clipPathTmpRect);
            ReactionsLayoutInBubble.fillTagPath(this.clipPathRect, this.clipPathTmpRect, this.clipPath);
        L7:
            r6.inset(-AndroidUtilities.dp(4.0f), -AndroidUtilities.dp(4.0f));
            r6.right += AndroidUtilities.dp(1.0f);
            this.blurredDrawable.setBounds(r6);
            r10.save();
            r10.clipPath(this.clipPath);
            this.blurredDrawable.draw(r10);
            Paint r42 = SearchTagsList.m12582$$Nest$fgetstrokePaint(this.this$0);
            if (SearchTagsList.m12581$$Nest$fgetresourcesProvider(this.this$0) == null) goto L13;
            if (SearchTagsList.m12581$$Nest$fgetresourcesProvider(this.this$0).isDark() == true) goto L14;
        L15:
            int r52 = -1;
        L16:
            r42.setColor(r52);
            r10.drawPath(this.clipPath, SearchTagsList.m12582$$Nest$fgetstrokePaint(this.this$0));
            r10.restore();
        L14:
            r52 = 687865855;
            goto L16
        L13:
            if (Theme.isCurrentThemeDark() == false) goto L15;
        L17:
            this.reactionButton.draw(r10, r2, r32, this.progress.set(1.0f), 1.0f, false, false, 0.0f);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            int r22 = AndroidUtilities.dp(8.67f);
            ReactionsLayoutInBubble.ReactionButton r0 = this.reactionButton;
            if (r0 == null) goto L5;
            int r02 = r0.width;
        L6:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r22 + r02, TLObject.FLAG_30), r3);
            return;
        L5:
            r02 = AndroidUtilities.dp(44.33f);
            goto L6
        }

        public void set(Item r13) {
            ReactionsLayoutInBubble.VisibleReaction r0 = this.lastReaction;
            if (r0 != null) goto L5;
        L8:
            boolean r02 = true;
        L9:
            if (r02 == false) goto L11;
            TLRPC.TL_reactionCount r8 = new TLRPC.TL_reactionCount();
            r8.reaction = r13.reaction.toTLReaction();
            r8.count = r13.count;
            TagButton r4 = this;
            AnonymousClass1 r3 = new AnonymousClass1(r4, null, SearchTagsList.m12577$$Nest$fgetcurrentAccount(this.this$0), this, r8, false, true, SearchTagsList.m12581$$Nest$fgetresourcesProvider(this.this$0));
            r4.reactionButton = r3;
            r3.counterDrawable.setSize(AndroidUtilities.dp(29.0f), AndroidUtilities.dp(100.0f));
            ReactionsLayoutInBubble.ReactionButton r12 = r4.reactionButton;
            r12.drawBgOnlyIfChosen = true;
            r12.isTag = true;
        L12:
            r4.lastReaction = r13.reaction;
            if (r02 == true) goto L15;
            ReactionsLayoutInBubble.ReactionButton r122 = r4.reactionButton;
            r122.animateFromWidth = r122.width;
        L15:
            r4.reactionButton.width = AndroidUtilities.dp(44.33f);
            r4.reactionButton.hasName = true ^ TextUtils.isEmpty(r13.name);
            ReactionsLayoutInBubble.ReactionButton r123 = r4.reactionButton;
            if (r123.hasName == false) goto L18;
            AnimatedTextView.AnimatedTextDrawable r124 = r123.textDrawable;
            r124.setText(Emoji.replaceEmoji(r13.name, r124.getPaint().getFontMetricsInt(), false), !r02);
        L21:
            r4.reactionButton.countText = Integer.toString(r13.count);
            r4.reactionButton.counterDrawable.setCount(r13.count, !r02);
            ReactionsLayoutInBubble.ReactionButton r125 = r4.reactionButton;
            CounterView.CounterDrawable r132 = r125.counterDrawable;
            if (r132 != null) goto L24;
        L32:
            if (r02 == false) goto L34;
            ReactionsLayoutInBubble.ReactionButton r126 = r4.reactionButton;
            r126.animateFromWidth = r126.width;
        L34:
            r4.reactionButton.height = AndroidUtilities.dp(28.0f);
            ReactionsLayoutInBubble.ReactionButton r127 = r4.reactionButton;
            r127.choosen = r4.chosen;
            if (r4.attached == false) goto L37;
            r127.attach();
        L37:
            if (r02 == true) goto L40;
            r4.requestLayout();
            return;
        L40:
            return;
        L24:
            if (r125.count <= 0) goto L26;
        L27:
            float r1 = r125.width;
            int r133 = r132.getCurrentWidth();
            if (r4.reactionButton.hasName == false) goto L30;
            float r2 = 4.0f;
        L31:
            r125.width = (int) (r1 + ((r133 + AndroidUtilities.dp(r2)) + r4.reactionButton.textDrawable.getAnimateToWidth()));
            goto L32
        L30:
            r2 = 0.0f;
            goto L31
        L26:
            if (r125.hasName == false) goto L32;
        L18:
            AnimatedTextView.AnimatedTextDrawable r128 = r123.textDrawable;
            if (r128 == null) goto L21;
            r128.setText(_UrlKt.FRAGMENT_ENCODE_SET, !r02);
            goto L21
        L11:
            r4 = this;
            r4.reactionButton.count = r13.count;
            goto L12
        L5:
            if (r0.equals(r13.reaction) == false) goto L8;
            r02 = false;
            goto L9
        }

        public boolean setChosen(boolean r3, boolean r4) {
            if (this.chosen != r3) goto L6;
            return false;
        L6:
            this.chosen = r3;
            ReactionsLayoutInBubble.ReactionButton r0 = this.reactionButton;
            if (r0 == null) goto L13;
            r0.choosen = r3;
            if (r4 == false) goto L11;
            r0.fromTextColor = r0.lastDrawnTextColor;
            r0.fromBackgroundColor = r0.lastDrawnBackgroundColor;
            r0.fromTagDotColor = r0.lastDrawnTagDotColor;
            this.progress.set(0.0f, true);
        L12:
            invalidate();
            goto L13
        L11:
            this.progress.set(1.0f, true);
        L13:
            return true;
        }

        public void startAnimate() {
            ReactionsLayoutInBubble.ReactionButton r0 = this.reactionButton;
            if (r0 != null) goto L5;
            return;
        L5:
            r0.fromTextColor = r0.lastDrawnTextColor;
            r0.fromBackgroundColor = r0.lastDrawnBackgroundColor;
            r0.fromTagDotColor = r0.lastDrawnTagDotColor;
            this.progress.set(0.0f, true);
            invalidate();
        }
    }

    public static /* synthetic */ void $r8$lambda$2Zk8MIPxjzcD32wEZ_9HTnKvbqU(SearchTagsList r0, int r1, Item r2, Theme.ResourcesProvider r3) {
        r0.lambda$new$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$4wvGjXhRjmG_WOflWr5b0apO6zw(EditTextBoldCursor r2, int r3, TLRPC.Reaction r4, AlertDialog r5, int r6) {
        String r62 = r2.getText().toString();
        if (r62.length() <= 12) goto L6;
        AndroidUtilities.shakeView(r2);
        return;
    L6:
        MessagesController.getInstance(r3).renameSavedReactionTag(ReactionsLayoutInBubble.VisibleReaction.fromTL(r4), r62);
        r5.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$AIrlCZo9e97YPO_9DFpSvQUCLLw(View r2) {
        if ((r2 instanceof TagButton) == false) goto L6;
        ((TagButton) r2).setChosen(false, true);
        return;
    }

    public static /* synthetic */ void $r8$lambda$D5RcktXOCzc363DlSVseS0I5Td8(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    /* renamed from: $r8$lambda$OLnLpUb3UaAuj5-3QH1V-_UjVog, reason: not valid java name */
    public static /* synthetic */ void m12566$r8$lambda$OLnLpUb3UaAuj53QH1V_UjVog(EditTextBoldCursor r0, DialogInterface r1) {
        AndroidUtilities.hideKeyboard(r0);
    }

    /* renamed from: $r8$lambda$QWtQVpfjJxod0CvJz-meboPJIgY, reason: not valid java name */
    public static /* synthetic */ void m12567$r8$lambda$QWtQVpfjJxod0CvJzmeboPJIgY(View r0, DialogInterface r1) {
        currentDialog = null;
        r0.requestFocus();
    }

    /* renamed from: $r8$lambda$V-TGzQjaCMjua8GTH2VQ5dUDTxo, reason: not valid java name */
    public static /* synthetic */ void m12568$r8$lambda$VTGzQjaCMjua8GTH2VQ5dUDTxo(SearchTagsList r0, int r1, BaseFragment r2, View r3, int r4) {
        r0.lambda$new$2(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$YrlSp0bfoJ_mRO_53ufnygGz6VI(SearchTagsList r0) {
        r0.lambda$updateTags$12();
    }

    public static /* synthetic */ void $r8$lambda$ZVIFMK5YKMHE9zPrITMm8IiF_vQ(EditTextBoldCursor r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ boolean $r8$lambda$_JussU2fLaejB8XohbmLqyp52kw(SearchTagsList r0, int r1, BaseFragment r2, Theme.ResourcesProvider r3, View r4, int r5) {
        return r0.lambda$new$4(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$aFVKIhBTevSJSPj7-XzICJJBnGk, reason: not valid java name */
    public static /* synthetic */ void m12569$r8$lambda$aFVKIhBTevSJSPj7XzICJJBnGk(SearchTagsList r0, ValueAnimator r1) {
        r0.lambda$show$13(r1);
    }

    /* renamed from: $r8$lambda$bnK_fL57Bm_UKP-5v67jy7uz8dA, reason: not valid java name */
    public static /* synthetic */ void m12570$r8$lambda$bnK_fL57Bm_UKP5v67jy7uz8dA(View r2) {
        if ((r2 instanceof TagButton) == false) goto L6;
        ((TagButton) r2).setChosen(false, true);
        return;
    }

    public static /* synthetic */ void $r8$lambda$dh0TSP2Zj2uu1ZZr7I2uWapo21Q(EditTextBoldCursor r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    /* renamed from: $r8$lambda$ruzmPNXSMAUpRRDgTz-VNPwBwX8, reason: not valid java name */
    public static /* synthetic */ void m12571$r8$lambda$ruzmPNXSMAUpRRDgTzVNPwBwX8(SearchTagsList r0, View r1) {
        r0.lambda$createPremiumLayout$0(r1);
    }

    /* renamed from: -$$Nest$fgetactionBarTagsAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m12572$$Nest$fgetactionBarTagsAnimator(SearchTagsList r0) {
        return r0.actionBarTagsAnimator;
    }

    /* renamed from: -$$Nest$fgetactionBarTagsT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12573$$Nest$fgetactionBarTagsT(SearchTagsList r0) {
        return r0.actionBarTagsT;
    }

    /* renamed from: -$$Nest$fgetblurredColorProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundProvider m12574$$Nest$fgetblurredColorProvider(SearchTagsList r0) {
        return r0.blurredColorProvider;
    }

    /* renamed from: -$$Nest$fgetblurredFactory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundDrawableViewFactory m12575$$Nest$fgetblurredFactory(SearchTagsList r0) {
        return r0.blurredFactory;
    }

    /* renamed from: -$$Nest$fgetchosen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m12576$$Nest$fgetchosen(SearchTagsList r2) {
        return r2.chosen;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12577$$Nest$fgetcurrentAccount(SearchTagsList r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12578$$Nest$fgetitems(SearchTagsList r0) {
        return r0.items;
    }

    /* renamed from: -$$Nest$fgetoldItems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12579$$Nest$fgetoldItems(SearchTagsList r0) {
        return r0.oldItems;
    }

    /* renamed from: -$$Nest$fgetpremiumLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m12580$$Nest$fgetpremiumLayout(SearchTagsList r0) {
        return r0.premiumLayout;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m12581$$Nest$fgetresourcesProvider(SearchTagsList r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetstrokePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12582$$Nest$fgetstrokePaint(SearchTagsList r0) {
        return r0.strokePaint;
    }

    /* renamed from: -$$Nest$fputactionBarTagsT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12583$$Nest$fputactionBarTagsT(SearchTagsList r0, float r1) {
        r0.actionBarTagsT = r1;
    }

    /* renamed from: -$$Nest$sfgetcurrentDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AlertDialog m12584$$Nest$sfgetcurrentDialog() {
        return currentDialog;
    }

    /* renamed from: -$$Nest$sfputcurrentDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12585$$Nest$sfputcurrentDialog(AlertDialog r0) {
        currentDialog = r0;
    }

    public SearchTagsList(Context r3, BaseFragment r4, int r5, long r6, Theme.ResourcesProvider r8) {
        super(r3);
        this.oldItems = new ArrayList();
        this.items = new ArrayList();
        this.strokePaint = new Paint(1);
        this.currentAccount = r5;
        this.fragment = r4;
        this.resourcesProvider = r8;
        this.topicId = r6;
        ReactionsLayoutInBubble.initPaints(r8);
        AnonymousClass2 r62 = new AnonymousClass2(this, r3, r8);
        this.listView = r62;
        r62.setPadding(AndroidUtilities.dp(5.66f), 0, AndroidUtilities.dp(5.66f), 0);
        r62.setClipToPadding(false);
        LinearLayoutManager r7 = new LinearLayoutManager(r3);
        r7.setOrientation(0);
        r62.setLayoutManager(r7);
        Adapter r32 = new Adapter(this);
        this.adapter = r32;
        r62.setAdapter(r32);
        r62.setOverScrollMode(2);
        addView(r62, LayoutHelper.createFrame(-1, -1.0f));
        r62.setOnItemClickListener(new SearchTagsList$$ExternalSyntheticLambda0(this, r5, r4));
        r62.setOnItemLongClickListener(new SearchTagsList$$ExternalSyntheticLambda1(this, r5, r4, r8));
        AnonymousClass3 r33 = new AnonymousClass3(this);
        r33.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r33.setDurations(320);
        r62.setItemAnimator(r33);
        MediaDataController.getInstance(r5).loadSavedReactions(false);
        updateTags(false);
    }

    private void createPremiumLayout() {
        if (this.premiumLayout == null) goto L5;
        return;
    L5:
        LinearLayout r0 = new LinearLayout(getContext());
        this.premiumLayout = r0;
        r0.setOnClickListener(new SearchTagsList$$ExternalSyntheticLambda13(this));
        this.premiumLayout.setOrientation(0);
        ScaleStateListAnimator.apply(this.premiumLayout, 0.03f, 1.25f);
        AnonymousClass1 r02 = new AnonymousClass1(this, getContext());
        int r2 = Theme.key_windowBackgroundWhiteBlueText2;
        r02.setTextColor(Theme.getColor(r2, this.resourcesProvider));
        r02.setTextSize(1, 12.0f);
        r02.setTypeface(AndroidUtilities.bold());
        SpannableStringBuilder r5 = new SpannableStringBuilder();
        Drawable r6 = getContext().getResources().getDrawable(R.drawable.msg_mini_lock3).mutate();
        int r8 = Theme.key_chat_messageLinkIn;
        PorterDuff.Mode r9 = PorterDuff.Mode.SRC_IN;
        r6.setColorFilter(new PorterDuffColorFilter(r8, r9));
        ColoredImageSpan r7 = new ColoredImageSpan(r6);
        r7.setTranslateY(0.0f);
        r7.setTranslateX(0.0f);
        r7.setScale(0.94f, 0.94f);
        SpannableString r62 = new SpannableString("l");
        r62.setSpan(r7, 0, r62.length(), 17);
        r5.append(r62);
        r5.append(" ").append(LocaleController.getString(R.string.AddTagsToYourSavedMessages1));
        r02.setText(r5);
        r02.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(4.0f));
        TextView r63 = new TextView(getContext());
        r63.setTextColor(Theme.getColor(r2, this.resourcesProvider));
        r63.setTextSize(1, 12.0f);
        r63.setTypeface(AndroidUtilities.bold());
        SpannableStringBuilder r22 = new SpannableStringBuilder(LocaleController.getString(R.string.AddTagsToYourSavedMessages2));
        SpannableString r3 = new SpannableString(">");
        Drawable r4 = getContext().getResources().getDrawable(R.drawable.msg_arrowright).mutate();
        r4.setColorFilter(new PorterDuffColorFilter(r8, r9));
        ColoredImageSpan r72 = new ColoredImageSpan(r4);
        r72.setScale(0.76f, 0.76f);
        r72.setTranslateX(-AndroidUtilities.dp(1.0f));
        r72.setTranslateY(AndroidUtilities.dp(1.0f));
        r3.setSpan(r72, 0, r3.length(), 17);
        r22.append(r3);
        r63.setText(r22);
        r63.setPadding(AndroidUtilities.dp(5.66f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(4.0f));
        this.premiumLayout.addView(r02, LayoutHelper.createLinear(-2, -2, 16));
        this.premiumLayout.addView(r63, LayoutHelper.createLinear(-2, -2, 16));
        this.premiumLayout.setPadding(AndroidUtilities.dp(7.0f), 0, 0, 0);
        this.premiumLayout.setClipToPadding(false);
        addView(this.premiumLayout, LayoutHelper.createFrame(-2, -1.0f, 19, 5.0f, 0.0f, 5.0f, 0.0f));
    }

    private /* synthetic */ void lambda$createPremiumLayout$0(View r3) {
        new PremiumFeatureBottomSheet(this.fragment, 24, true).show();
    }

    private /* synthetic */ void lambda$new$2(int r6, BaseFragment r7, View r8, int r9) {
        if (r9 >= 0) goto L4;
        return;
    L4:
        if (r9 < this.items.size()) goto L7;
        return;
    L7:
        if (UserConfig.getInstance(r6).isPremium() == true) goto L10;
        new PremiumFeatureBottomSheet(r7, 24, true).show();
        return;
    L10:
        long r62 = this.items.get(r9).hash();
        if (this.chosen != r62) goto L13;
        ReactionsLayoutInBubble.VisibleReaction r92 = null;
    L15:
        if (setFilter(r92) == false) goto L47;
        int r1 = 0;
    L18:
        int r2 = this.listView.getChildCount();
        RecyclerListView r3 = this.listView;
        if (r1 >= r2) goto L34;
        if (r3.getChildAt(r1) != r8) goto L33;
        RecyclerListView r22 = this.listView;
        float r32 = 50.0f;
        if (r1 > 1) goto L28;
        if (r1 != 0) goto L26;
        r32 = 90.0f;
    L26:
        r22.smoothScrollBy(-AndroidUtilities.dp(r32), 0);
        goto L33
    L28:
        if (r1 < (r22.getChildCount() - 2)) goto L33;
        RecyclerListView r23 = this.listView;
        if (r1 != (r23.getChildCount() - 1)) goto L32;
        r32 = 80.0f;
    L32:
        r23.smoothScrollBy(AndroidUtilities.dp(r32), 0);
    L33:
        r1 = r1 + 1;
        goto L18
    L34:
        r3.forAllChild(new SearchTagsList$$ExternalSyntheticLambda3());
        if (this.chosen != r62) goto L38;
        this.chosen = 0;
        return;
    L38:
        this.chosen = r62;
        ((TagButton) r8).setChosen(true, true);
        return;
    L47:
        return;
    L13:
        r92 = this.items.get(r9).reaction;
        goto L15
    }

    private /* synthetic */ void lambda$new$3(int r2, Item r3, Theme.ResourcesProvider r4) {
        openRenameTagAlert(getContext(), r2, r3.reaction.toTLReaction(), r4, false);
    }

    private /* synthetic */ boolean lambda$new$4(int r4, BaseFragment r5, Theme.ResourcesProvider r6, View r7, int r8) {
        if (r8 >= 0) goto L4;
        return false;
    L4:
        if (r8 < this.items.size()) goto L6;
        return false;
    L6:
        if (UserConfig.getInstance(r4).isPremium() == true) goto L9;
        return false;
    L9:
        if (UserConfig.getInstance(r4).isPremium() == true) goto L12;
        new PremiumFeatureBottomSheet(r5, 24, true).show();
        return true;
    L12:
        ReactionsLayoutInBubble.ReactionButton r0 = ((TagButton) r7).reactionButton;
        if (r0 == null) goto L15;
        r0.startAnimation();
    L15:
        Item r82 = this.items.get(r8);
        ItemOptions r52 = ItemOptions.makeOptions(r5, r7).setGravity(3);
        int r72 = R.drawable.menu_tag_rename;
        if (TextUtils.isEmpty(r82.name) == false) goto L18;
        int r02 = R.string.SavedTagLabelTag;
    L19:
        r52.add(r72, LocaleController.getString(r02), new SearchTagsList$$ExternalSyntheticLambda4(this, r4, r82, r6)).show();
        return true;
    L18:
        r02 = R.string.SavedTagRenameTag;
        goto L19
    }

    private /* synthetic */ void lambda$show$13(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.actionBarTagsT = r12;
        setShown(r12);
        onShownUpdate(false);
    }

    private /* synthetic */ void lambda$updateTags$12() {
        this.premiumLayout.setVisibility(8);
    }

    public static boolean onBackPressedRenameTagAlert(boolean r1) {
        AlertDialog r0 = currentDialog;
        if (r0 == null) goto L8;
        if (r1 == false) goto L10;
        r0.dismiss();
        currentDialog = null;
        return false;
    L10:
        return false;
    L8:
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [org.telegram.ui.ActionBar.AlertDialog$Builder] */
    public static void openRenameTagAlert(Context r19, int r20, TLRPC.Reaction r21, Theme.ResourcesProvider r22, boolean r23) {
        BaseFragment r1 = LaunchActivity.getLastFragment();
        Activity r2 = AndroidUtilities.findActivity(r19);
        if (r2 == null) goto L5;
        View r6 = r2.getCurrentFocus();
    L7:
        if (r1 != null) goto L9;
    L14:
        boolean r11 = false;
    L15:
        AlertDialog[] r5 = new AlertDialog[1];
        if (r11 == false) goto L19;
        Object r12 = new AlertDialogDecor.Builder(r19, r22);
    L18:
        ?? r122 = r12;
        String r13 = MessagesController.getInstance(r20).getSavedTagName(r21);
        SpannableStringBuilder r14 = new SpannableStringBuilder(ReactionsLayoutInBubble.VisibleReaction.fromTL(r21).toCharSequence(20)).append("  ");
        if (TextUtils.isEmpty(r13) == false) goto L23;
        int r24 = R.string.SavedTagLabelTag;
    L24:
        r122.setTitle(r14.append(LocaleController.getString(r24)));
        AnonymousClass4 r25 = new AnonymousClass4(r19, r22);
        r25.setOnEditorActionListener(new AnonymousClass5(r25, r20, r21, r5, r6));
        MediaDataController.getInstance(r20).fetchNewEmojiKeywords(AndroidUtilities.getCurrentKeyboardLanguage(), true);
        r25.setTextSize(1, 18.0f);
        if (r13 != null) goto L27;
        r13 = _UrlKt.FRAGMENT_ENCODE_SET;
    L27:
        r25.setText(r13);
        int r15 = Theme.key_dialogTextBlack;
        r25.setTextColor(Theme.getColor(r15, r22));
        r25.setHintColor(Theme.getColor(Theme.key_groupcreate_hintText, r22));
        r25.setHintText(LocaleController.getString(R.string.SavedTagLabelPlaceholder));
        r25.setSingleLine(true);
        r25.setFocusable(true);
        r25.setInputType(16384);
        r25.setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField, r22), Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated, r22), Theme.getColor(Theme.key_text_RedRegular, r22));
        r25.setImeOptions(6);
        r25.setBackgroundDrawable(null);
        r25.setPadding(0, 0, AndroidUtilities.dp(42.0f), 0);
        LinearLayout r3 = new LinearLayout(r19);
        r3.setOrientation(1);
        TextView r8 = new TextView(r19);
        r8.setTextColor(Theme.getColor(r15, r22));
        r8.setTextSize(1, 16.0f);
        r8.setText(LocaleController.getString(R.string.SavedTagLabelTagText));
        r3.addView(r8, LayoutHelper.createLinear(-1, -2, 24.0f, 5.0f, 24.0f, 12.0f));
        r3.addView(r25, LayoutHelper.createLinear(-1, -2, 24.0f, 0.0f, 24.0f, 10.0f));
        r122.setView(r3);
        r122.setWidth(AndroidUtilities.dp(292.0f));
        r122.setPositiveButton(LocaleController.getString(R.string.Save), new SearchTagsList$$ExternalSyntheticLambda5(r25, r20, r21));
        r122.setNegativeButton(LocaleController.getString(R.string.Cancel), new SearchTagsList$$ExternalSyntheticLambda6());
        if (r11 == false) goto L30;
        AlertDialog r0 = r122.create();
        currentDialog = r0;
        r5[0] = r0;
        r0.setOnDismissListener(new SearchTagsList$$ExternalSyntheticLambda7(r6));
        currentDialog.setOnShowListener(new SearchTagsList$$ExternalSyntheticLambda8(r25));
        currentDialog.showDelayed(250);
    L31:
        r5[0].setDismissDialogByButtons(false);
        r25.setSelection(r25.getText().length());
        return;
    L30:
        AlertDialog r02 = r122.create();
        r5[0] = r02;
        r02.setOnDismissListener(new SearchTagsList$$ExternalSyntheticLambda9(r25));
        r5[0].setOnShowListener(new SearchTagsList$$ExternalSyntheticLambda10(r25));
        r5[0].show();
        goto L31
    L23:
        r24 = R.string.SavedTagRenameTag;
        goto L24
    L19:
        r12 = new AlertDialog.Builder(r19, r22);
        goto L18
    L9:
        if ((r1.getFragmentView() instanceof SizeNotifierFrameLayout) == false) goto L14;
        if (((SizeNotifierFrameLayout) r1.getFragmentView()).measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L14;
        if (r23 == true) goto L14;
        r11 = true;
        goto L15
    L5:
        r6 = null;
        goto L7
    }

    public void clear() {
        this.listView.forAllChild(new SearchTagsList$$ExternalSyntheticLambda12());
        this.chosen = 0;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.savedReactionTagsUpdate) goto L11;
        long r32 = ((Long) r5[0]).longValue();
        if (r32 != 0) goto L7;
    L8:
        updateTags(true);
        return;
    L7:
        if (r32 == this.topicId) goto L8;
        return;
    L11:
        if (r3 != NotificationCenter.emojiLoaded) goto L15;
        invalidate();
        AndroidUtilities.forEachViews(this.listView, new FloatingDebugView$$ExternalSyntheticLambda10());
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r3) {
        if (this.shownT >= 0.5f) goto L7;
        return false;
    L7:
        return super.dispatchTouchEvent(r3);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r10, View r11, long r12) {
        if (r11 != this.listView) goto L13;
        LinearLayout r0 = this.premiumLayout;
        if (r0 == null) goto L13;
        if (r0.getAlpha() < 1.0f) goto L10;
        return false;
    L10:
        r10.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) ((1.0f - this.premiumLayout.getAlpha()) * 255.0f), 31);
        boolean r9 = super.drawChild(r10, r11, r12);
        r10.restore();
        return r9;
    L13:
        return super.drawChild(r10, r11, r12);
    }

    public int getCurrentHeight() {
        return (int) (getMeasuredHeight() * this.shownT);
    }

    public boolean hasFilters() {
        if (this.items.isEmpty() == true) goto L5;
        return true;
    L5:
        if (this.shownPremiumLayout == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.savedReactionTagsUpdate);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.emojiLoaded);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.savedReactionTagsUpdate);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.emojiLoaded);
    }

    public abstract void onShownUpdate(boolean r1);

    public void setBlurredFactory(BlurredBackgroundDrawableViewFactory r4, BlurredBackgroundProvider r5) {
        this.blurredFactory = r4;
        this.blurredColorProvider = r5;
        this.strokePaint.setStrokeWidth(AndroidUtilities.dpf2(1.0f));
        this.strokePaint.setStyle(Paint.Style.STROKE);
        LinearLayout r52 = this.premiumLayout;
        if (r52 == null) goto L6;
        r52.setBackground(r4.create(r52).setColorProvider(BlurredBackgroundProviderImpl.topPanelChatActivity(this.resourcesProvider)).setRadius(AndroidUtilities.dp(9.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(9.0f)).setThickness(AndroidUtilities.dp(5.0f)).setPadding(AndroidUtilities.dp(4.0f)));
        return;
    }

    public void setChosen(ReactionsLayoutInBubble.VisibleReaction r7, boolean r8) {
        if (r7 != null) goto L8;
        this.chosen = 0;
        if (r8 == false) goto L6;
        setFilter(null);
    L6:
        this.adapter.notifyDataSetChanged();
        return;
    L8:
        int r0 = 0;
    L10:
        if (r0 >= this.items.size()) goto L19;
        Item r1 = this.items.get(r0);
        if (r7.hash == r1.reaction.hash) goto L13;
        r0 = r0 + 1;
        goto L10
    L13:
        this.chosen = r1.hash();
        if (r8 == false) goto L16;
        setFilter(r1.reaction);
    L16:
        this.adapter.notifyDataSetChanged();
        this.listView.scrollToPosition(r0);
        return;
    }

    public abstract boolean setFilter(ReactionsLayoutInBubble.VisibleReaction r1);

    public void setShown(float r5) {
        this.shownT = r5;
        this.listView.setPivotX(r0.getWidth() / 2.0f);
        this.listView.setPivotY(0.0f);
        this.listView.setScaleX(AndroidUtilities.lerp(0.8f, 1.0f, r5));
        this.listView.setScaleY(AndroidUtilities.lerp(0.8f, 1.0f, r5));
        setAlpha(r5);
        invalidate();
    }

    public void show(boolean r5) {
        ValueAnimator r0 = this.actionBarTagsAnimator;
        if (r0 == null) goto L6;
        this.actionBarTagsAnimator = null;
        r0.cancel();
    L6:
        if (r5 == false) goto L8;
        setVisibility(0);
    L8:
        float r1 = this.actionBarTagsT;
        if (r5 == false) goto L11;
        float r2 = 1.0f;
    L12:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{r1, r2});
        this.actionBarTagsAnimator = r02;
        r02.addUpdateListener(new SearchTagsList$$ExternalSyntheticLambda2(this));
        this.actionBarTagsAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.actionBarTagsAnimator.setDuration(320);
        this.actionBarTagsAnimator.addListener(new AnonymousClass7(this, r5));
        this.actionBarTagsAnimator.start();
        return;
    L11:
        r2 = 0.0f;
        goto L12
    }

    public boolean shown() {
        if (this.shownT <= 0.5f) goto L6;
        return true;
    L6:
        return false;
    }

    public void updateTags(boolean r15) {
        HashSet r0 = new HashSet();
        this.oldItems.clear();
        this.oldItems.addAll(this.items);
        this.items.clear();
        MessagesController r1 = MessagesController.getInstance(this.currentAccount);
        TLRPC.TL_messages_savedReactionsTags r2 = r1.getSavedReactionTags(this.topicId);
        if (r2 == null) goto L23;
        int r6 = 0;
        boolean r7 = false;
    L6:
        if (r6 >= r2.tags.size()) goto L24;
        TLRPC.TL_savedReactionTag r8 = r2.tags.get(r6);
        ReactionsLayoutInBubble.VisibleReaction r9 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r8.reaction);
        if (r0.contains(Long.valueOf(r9.hash)) == true) goto L22;
        long r10 = this.topicId;
        if (r10 != 0) goto L12;
    L14:
        int r12 = r8.count;
        if (r10 == 0) goto L17;
        String r82 = r1.getSavedTagName(r8.reaction);
    L18:
        Item r83 = Item.get(r9, r12, r82);
        if (r83.hash() != this.chosen) goto L21;
        r7 = true;
    L21:
        this.items.add(r83);
        r0.add(Long.valueOf(r9.hash));
        goto L22
    L17:
        r82 = r8.title;
        goto L18
    L12:
        if (r8.count > 0) goto L14;
    L22:
        r6 = r6 + 1;
    L24:
        if (r7 == false) goto L26;
    L28:
        if (r15 == false) goto L30;
        DiffUtil.calculateDiff(new AnonymousClass6(this)).dispatchUpdatesTo(this.adapter);
    L31:
        boolean r02 = UserConfig.getInstance(this.currentAccount).isPremium();
        this.shownPremiumLayout = !r02;
        if (r02 == true) goto L37;
        createPremiumLayout();
        if (r15 == true) goto L48;
        this.premiumLayout.setVisibility(0);
        this.premiumLayout.setAlpha(0.0f);
        this.premiumLayout.animate().alpha(1.0f).start();
        return;
    L48:
        return;
    L37:
        LinearLayout r03 = this.premiumLayout;
        if (r03 == null) goto L49;
        if (r15 == false) goto L42;
        r03.animate().alpha(0.0f).withEndAction(new SearchTagsList$$ExternalSyntheticLambda11(this)).start();
        return;
    L42:
        r03.setAlpha(1.0f);
        this.premiumLayout.setVisibility(0);
        return;
    L49:
        return;
    L30:
        this.adapter.notifyDataSetChanged();
        goto L31
    L26:
        if (this.chosen == 0) goto L28;
        this.chosen = 0;
        setFilter(null);
        goto L28
    L23:
        r7 = false;
        goto L24
    }
}
