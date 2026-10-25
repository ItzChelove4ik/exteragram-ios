package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.TranslateController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.utils.GradientProtectionDrawable;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes7.dex */
public class PollVotesAlert extends BottomSheet {
    public static final Property<UserCell, Float> USER_CELL_PROPERTY = null;
    private final ActionBar actionBar;
    private AnimatorSet actionBarAnimation;
    private final View actionBarShadow;
    private float gradientWidth;
    private Adapter listAdapter;
    private RecyclerListView listView;
    private final HashSet<VotesList> loadingMore;
    private boolean loadingResults;
    private final TLRPC.TL_messageMediaPoll messageMediaPoll;
    private final MessageObject messageObject;
    private final TLRPC.InputPeer peer;
    private LinearGradient placeholderGradient;
    private Matrix placeholderMatrix;
    private final Paint placeholderPaint;
    private final TLRPC.Poll poll;
    private final ArrayList<Integer> queries;
    private final RectF rect;
    private int scrollOffsetY;
    private final Drawable shadowDrawable;
    private final AnimatedEmojiSpan.TextViewEmojis titleTextView;
    private float totalTranslation;
    private final ArrayList<VotesList> voters;
    private final HashMap<VotesList, Button> votesPercents;

    /* renamed from: org.telegram.ui.Components.PollVotesAlert$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimationProperties.FloatProperty<UserCell> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        public Float get(UserCell r1) {
            return Float.valueOf(r1.getPlaceholderAlpha());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((UserCell) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(UserCell r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(UserCell r1, float r2) {
            r1.setPlaceholderAlpha(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.PollVotesAlert$2, reason: invalid class name */
    public class AnonymousClass2 implements Comparator<VotesList> {
        final /* synthetic */ PollVotesAlert this$0;

        public AnonymousClass2(PollVotesAlert r1) {
            this.this$0 = r1;
        }

        private int getIndex(VotesList r6) {
            int r0 = PollVotesAlert.m11883$$Nest$fgetpoll(this.this$0).answers.size();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L8;
            if (Arrays.equals(PollVotesAlert.m11883$$Nest$fgetpoll(this.this$0).answers.get(r2).option, r6.option) == true) goto L6;
            r2 = r2 + 1;
            goto L3
        L6:
            return r2;
        L8:
            return 0;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(VotesList r1, VotesList r2) {
            return compare2(r1, r2);
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(VotesList r1, VotesList r2) {
            int r12 = getIndex(r1);
            int r0 = getIndex(r2);
            if (r12 <= r0) goto L6;
            return 1;
        L6:
            if (r12 >= r0) goto L9;
            return -1;
        L9:
            return 0;
        }
    }

    /* renamed from: org.telegram.ui.Components.PollVotesAlert$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        private boolean ignoreLayout;
        private RectF rect;
        final /* synthetic */ PollVotesAlert this$0;

        public AnonymousClass3(PollVotesAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.ignoreLayout = false;
            this.rect = new RectF();
        }

        @Override // android.view.View
        public void onDraw(Canvas r11) {
            int r0 = AndroidUtilities.dp(13.0f);
            int r1 = (PollVotesAlert.m11886$$Nest$fgetscrollOffsetY(this.this$0) - PollVotesAlert.access$800(this.this$0)) - r0;
            if (PollVotesAlert.access$900(this.this$0) != 1) goto L5;
            r1 = (int) (r1 + PollVotesAlert.m11877$$Nest$fgetlistView(this.this$0).getTranslationY());
        L5:
            int r2 = AndroidUtilities.dp(20.0f) + r1;
            int r3 = (getMeasuredHeight() + AndroidUtilities.dp(15.0f)) + PollVotesAlert.access$1000(this.this$0);
            if ((PollVotesAlert.access$1100(this.this$0) + r1) >= ActionBar.getCurrentActionBarHeight()) goto L8;
            float r02 = r0 + AndroidUtilities.dp(4.0f);
            float r4 = Math.min(1.0f, ((ActionBar.getCurrentActionBarHeight() - r1) - PollVotesAlert.access$1200(this.this$0)) / r02);
            int r03 = (int) ((ActionBar.getCurrentActionBarHeight() - r02) * r4);
            r1 = r1 - r03;
            r2 = r2 - r03;
            r3 = r3 + r03;
            float r04 = 1.0f - r4;
        L9:
            int r42 = AndroidUtilities.statusBarHeight;
            int r22 = r2 + r42;
            PollVotesAlert.m11887$$Nest$fgetshadowDrawable(this.this$0).setBounds(0, r1 + r42, getMeasuredWidth(), r3);
            PollVotesAlert.m11887$$Nest$fgetshadowDrawable(this.this$0).draw(r11);
            if (r04 == 1.0f) goto L13;
            Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_dialogBackground));
            this.rect.set(PollVotesAlert.access$1300(this.this$0), PollVotesAlert.access$1400(this.this$0) + r1, getMeasuredWidth() - PollVotesAlert.access$1500(this.this$0), (PollVotesAlert.access$1600(this.this$0) + r1) + AndroidUtilities.dp(24.0f));
            r11.drawRoundRect(this.rect, AndroidUtilities.dp(12.0f) * r04, AndroidUtilities.dp(12.0f) * r04, Theme.dialogs_onlineCirclePaint);
        L13:
            if (r04 == 0.0f) goto L15;
            int r12 = AndroidUtilities.dp(36.0f);
            this.rect.set((getMeasuredWidth() - r12) / 2, r22, (getMeasuredWidth() + r12) / 2, r22 + AndroidUtilities.dp(4.0f));
            int r13 = Theme.getColor(Theme.key_sheet_scrollUp);
            int r23 = Color.alpha(r13);
            Theme.dialogs_onlineCirclePaint.setColor(r13);
            Theme.dialogs_onlineCirclePaint.setAlpha((int) ((r23 * 1.0f) * r04));
            r11.drawRoundRect(this.rect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
        L15:
            int r05 = Theme.getColor(Theme.key_dialogBackground);
            Theme.dialogs_onlineCirclePaint.setColor(Color.argb((int) (PollVotesAlert.m11873$$Nest$fgetactionBar(this.this$0).getAlpha() * 255.0f), Color.red(r05), Color.green(r05), Color.blue(r05)));
            r11.drawRect(PollVotesAlert.access$1700(this.this$0), 0.0f, getMeasuredWidth() - PollVotesAlert.access$1800(this.this$0), AndroidUtilities.statusBarHeight, Theme.dialogs_onlineCirclePaint);
            return;
        L8:
            r04 = 1.0f;
            goto L9
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            if (r4.getAction() != 0) goto L13;
            if (PollVotesAlert.m11886$$Nest$fgetscrollOffsetY(this.this$0) == 0) goto L13;
            if (r4.getY() >= (PollVotesAlert.m11886$$Nest$fgetscrollOffsetY(this.this$0) + AndroidUtilities.dp(12.0f))) goto L13;
            if (PollVotesAlert.m11873$$Nest$fgetactionBar(this.this$0).getAlpha() != 0.0f) goto L13;
            this.this$0.dismiss();
            return true;
        L13:
            return super.onInterceptTouchEvent(r4);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            PollVotesAlert.m11894$$Nest$mupdateLayout(this.this$0, false);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r11, int r12) {
            int r0 = View.MeasureSpec.getSize(r12);
            if (PollVotesAlert.access$300(this.this$0) == true) goto L5;
            this.ignoreLayout = true;
            setPadding(PollVotesAlert.access$400(this.this$0), AndroidUtilities.statusBarHeight, PollVotesAlert.access$500(this.this$0), 0);
            this.ignoreLayout = false;
        L5:
            int r1 = r0 - getPaddingTop();
            ((FrameLayout.LayoutParams) PollVotesAlert.m11877$$Nest$fgetlistView(this.this$0).getLayoutParams()).topMargin = ActionBar.getCurrentActionBarHeight();
            ((FrameLayout.LayoutParams) PollVotesAlert.m11874$$Nest$fgetactionBarShadow(this.this$0).getLayoutParams()).topMargin = ActionBar.getCurrentActionBarHeight();
            int r4 = (PollVotesAlert.access$600(this.this$0) + AndroidUtilities.dp(15.0f)) + AndroidUtilities.statusBarHeight;
            int r5 = PollVotesAlert.m11876$$Nest$fgetlistAdapter(this.this$0).getSectionCount();
            int r6 = 0;
        L7:
            if (r6 >= r5) goto L13;
            PollVotesAlert r8 = this.this$0;
            if (r6 != 0) goto L11;
            PollVotesAlert.m11888$$Nest$fgettitleTextView(r8).measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r11 - (PollVotesAlert.access$700(this.this$0) * 2)), TLObject.FLAG_30), r12);
            r4 = r4 + PollVotesAlert.m11888$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight();
        L12:
            r6 = r6 + 1;
            goto L7
        L11:
            r4 = r4 + (AndroidUtilities.dp(32.0f) + (AndroidUtilities.dp(50.0f) * (PollVotesAlert.m11876$$Nest$fgetlistAdapter(r8).getCountForSection(r6) - 1)));
            goto L12
        L13:
            if (r4 >= r1) goto L15;
            int r13 = r1 - r4;
        L16:
            int r14 = r13 + AndroidUtilities.dp(8.0f);
            if (PollVotesAlert.m11877$$Nest$fgetlistView(this.this$0).getPaddingTop() == r14) goto L19;
            this.ignoreLayout = true;
            PollVotesAlert.m11877$$Nest$fgetlistView(this.this$0).setPinnedSectionOffsetY(-r14);
            PollVotesAlert.m11877$$Nest$fgetlistView(this.this$0).setPadding(0, r14, 0, AndroidUtilities.navigationBarHeight);
            this.ignoreLayout = false;
        L19:
            super.onMeasure(r11, View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30));
            return;
        L15:
            r13 = r1 - ((r1 / 5) * 3);
            goto L16
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.isDismissed() == false) goto L5;
            return false;
        L5:
            if (super.onTouchEvent(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.PollVotesAlert$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerListView {
        private final GradientProtectionDrawable gradientProtectionDrawable;
        long lastUpdateTime;
        final /* synthetic */ PollVotesAlert this$0;

        public AnonymousClass4(PollVotesAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.gradientProtectionDrawable = new GradientProtectionDrawable(8);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean allowSelectChildAtPosition(float r1, float r2) {
            if (r2 < (PollVotesAlert.m11886$$Nest$fgetscrollOffsetY(this.this$0) + AndroidUtilities.statusBarHeight)) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            if (PollVotesAlert.m11878$$Nest$fgetloadingResults(this.this$0) == false) goto L12;
            long r0 = SystemClock.elapsedRealtime();
            long r2 = Math.abs(this.lastUpdateTime - r0);
            if (r2 <= 17) goto L7;
            r2 = 16;
        L7:
            this.lastUpdateTime = r0;
            PollVotesAlert r02 = this.this$0;
            PollVotesAlert.m11893$$Nest$fputtotalTranslation(r02, PollVotesAlert.m11889$$Nest$fgettotalTranslation(r02) + ((r2 * PollVotesAlert.m11875$$Nest$fgetgradientWidth(this.this$0)) / 1800.0f));
        L8:
            float r03 = PollVotesAlert.m11889$$Nest$fgettotalTranslation(this.this$0);
            float r1 = PollVotesAlert.m11875$$Nest$fgetgradientWidth(this.this$0) * 2.0f;
            PollVotesAlert r12 = this.this$0;
            if (r03 < r1) goto L11;
            PollVotesAlert.m11893$$Nest$fputtotalTranslation(r12, PollVotesAlert.m11889$$Nest$fgettotalTranslation(r12) - (PollVotesAlert.m11875$$Nest$fgetgradientWidth(this.this$0) * 2.0f));
            goto L8
        L11:
            PollVotesAlert.m11881$$Nest$fgetplaceholderMatrix(r12).setTranslate(PollVotesAlert.m11889$$Nest$fgettotalTranslation(this.this$0), 0.0f);
            PollVotesAlert.m11880$$Nest$fgetplaceholderGradient(this.this$0).setLocalMatrix(PollVotesAlert.m11881$$Nest$fgetplaceholderMatrix(this.this$0));
            invalidateViews();
            invalidate();
        L12:
            super.dispatchDraw(r7);
            this.gradientProtectionDrawable.setBounds(0, getMeasuredHeight() - AndroidUtilities.navigationBarHeight, getMeasuredWidth(), getMeasuredHeight());
            this.gradientProtectionDrawable.setColor(getThemedColor(Theme.key_dialogBackgroundGray));
            this.gradientProtectionDrawable.draw(r7);
        }
    }

    /* renamed from: org.telegram.ui.Components.PollVotesAlert$5, reason: invalid class name */
    public class AnonymousClass5 extends LinearLayoutManager {
        final /* synthetic */ PollVotesAlert this$0;

        public AnonymousClass5(PollVotesAlert r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public int getExtraLayoutSpace(RecyclerView.State r1) {
            return AndroidUtilities.dp(4000.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.PollVotesAlert$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.OnScrollListener {
        final /* synthetic */ PollVotesAlert this$0;

        public AnonymousClass6(PollVotesAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r4, int r5) {
            if (r5 != 0) goto L13;
            int r42 = AndroidUtilities.dp(13.0f);
            if ((((PollVotesAlert.m11886$$Nest$fgetscrollOffsetY(this.this$0) - PollVotesAlert.access$1900(this.this$0)) - r42) + PollVotesAlert.access$2000(this.this$0)) < ActionBar.getCurrentActionBarHeight()) goto L6;
            return;
        L6:
            if (PollVotesAlert.m11877$$Nest$fgetlistView(this.this$0).canScrollVertically(1) == false) goto L15;
            PollVotesAlert.m11877$$Nest$fgetlistView(this.this$0).getChildAt(0);
            RecyclerListView.Holder r43 = (RecyclerListView.Holder) PollVotesAlert.m11877$$Nest$fgetlistView(this.this$0).findViewHolderForAdapterPosition(0);
            if (r43 != null) goto L10;
            return;
        L10:
            if (r43.itemView.getTop() <= AndroidUtilities.dp(7.0f)) goto L17;
            PollVotesAlert.m11877$$Nest$fgetlistView(this.this$0).smoothScrollBy(0, r43.itemView.getTop() - AndroidUtilities.dp(7.0f));
            return;
        L17:
            return;
        L15:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (PollVotesAlert.m11877$$Nest$fgetlistView(this.this$0).getChildCount() > 0) goto L5;
            return;
        L5:
            PollVotesAlert.m11894$$Nest$mupdateLayout(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.Components.PollVotesAlert$7, reason: invalid class name */
    public class AnonymousClass7 extends ActionBar {
        final /* synthetic */ PollVotesAlert this$0;

        public AnonymousClass7(PollVotesAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            PollVotesAlert.access$2100(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.PollVotesAlert$8, reason: invalid class name */
    public class AnonymousClass8 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PollVotesAlert this$0;

        public AnonymousClass8(PollVotesAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.dismiss();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.PollVotesAlert$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ PollVotesAlert this$0;

        public AnonymousClass9(PollVotesAlert r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            PollVotesAlert.m11892$$Nest$fputactionBarAnimation(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
        }
    }

    public class Adapter extends RecyclerListView.SectionsAdapter {
        private final int currentAccount;
        private final Context mContext;
        final /* synthetic */ PollVotesAlert this$0;

        /* renamed from: org.telegram.ui.Components.PollVotesAlert$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 extends SectionCell {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass1(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r1.this$0, r2);
            }

            @Override // org.telegram.ui.Components.PollVotesAlert.SectionCell
            public void onCollapseClick() {
                VotesList r0 = (VotesList) getTag(R.id.object_tag);
                if (r0.votes.size() > 15) goto L5;
                return;
            L5:
                boolean r1 = r0.collapsed;
                r0.collapsed = !r1;
                if (r1 == true) goto L8;
                r0.collapsedCount = 10;
            L8:
                this.this$1.this$0.animateSectionUpdates(this);
                PollVotesAlert.m11876$$Nest$fgetlistAdapter(this.this$1.this$0).update(true);
            }
        }

        public Adapter(PollVotesAlert r1, Context r2) {
            this.this$0 = r1;
            this.currentAccount = UserConfig.selectedAccount;
            this.mContext = r2;
        }

        private SectionCell createSectionCell() {
            return new AnonymousClass1(this, this.mContext);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getCountForSection(int r3) {
            int r0 = 1;
            if (r3 != 0) goto L5;
            return 1;
        L5:
            VotesList r2 = (VotesList) PollVotesAlert.m11890$$Nest$fgetvoters(this.this$0).get(r3 - 1);
            int r32 = r2.getCount() + 1;
            if (TextUtils.isEmpty(r2.next_offset) == false) goto L11;
            if (r2.collapsed == true) goto L11;
            r0 = 0;
        L11:
            return r32 + r0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public Object getItem(int r2, int r3) {
            if (r2 == 0) goto L4;
            int r22 = r2 - 1;
            if (r3 == 0) goto L8;
            if (r22 < 0) goto L17;
            if (r22 >= PollVotesAlert.m11890$$Nest$fgetvoters(this.this$0).size()) goto L17;
            int r32 = r3 - 1;
            if (r32 >= ((VotesList) PollVotesAlert.m11890$$Nest$fgetvoters(this.this$0).get(r22)).getCount()) goto L17;
            return Integer.valueOf(Objects.hash(new Object[]{Long.valueOf(DialogObject.getPeerDialogId(((VotesList) PollVotesAlert.m11890$$Nest$fgetvoters(this.this$0).get(r22)).votes.get(r32).peer))}));
        L17:
            return -182734;
        L8:
            return -928312;
        L4:
            return 293145;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getItemViewType(int r1, int r2) {
            if (r1 != 0) goto L5;
            return 1;
        L5:
            if (r2 != 0) goto L9;
            return 2;
        L9:
            if ((r2 - 1) >= ((VotesList) PollVotesAlert.m11890$$Nest$fgetvoters(this.this$0).get(r1 - 1)).getCount()) goto L12;
            return 0;
        L12:
            return 3;
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
            return PollVotesAlert.m11890$$Nest$fgetvoters(this.this$0).size() + 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public View getSectionHeaderView(int r9, View r10) {
            if (r10 != null) goto L4;
            r10 = createSectionCell();
        L4:
            SectionCell r0 = (SectionCell) r10;
            if (r9 != 0) goto L8;
            r0.setAlpha(0.0f);
            return r10;
        L8:
            r10.setAlpha(1.0f);
            VotesList r92 = (VotesList) PollVotesAlert.m11890$$Nest$fgetvoters(this.this$0).get(r9 - 1);
            int r1 = PollVotesAlert.m11883$$Nest$fgetpoll(this.this$0).answers.size();
            int r2 = 0;
            int r3 = 0;
        L9:
            if (r3 >= r1) goto L38;
            TLRPC.PollAnswer r4 = PollVotesAlert.m11883$$Nest$fgetpoll(this.this$0).answers.get(r3);
            if (Arrays.equals(r4.option, r92.option) == false) goto L37;
            if (((Button) PollVotesAlert.m11891$$Nest$fgetvotesPercents(this.this$0).get(r92)) == null) goto L37;
            TLRPC.TL_textWithEntities r12 = r4.text;
            if (PollVotesAlert.m11879$$Nest$fgetmessageObject(this.this$0) != null) goto L18;
        L29:
            if (r12 != null) goto L31;
            String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
        L32:
            if (r12 != null) goto L34;
            ArrayList<TLRPC.MessageEntity> r13 = null;
        L35:
            r0.setText(r22, r13, this.this$0.calcPercent(r92.option), r92.count, r92.getCollapsed(), false);
            r0.setTag(R.id.object_tag, r92);
            return r10;
        L34:
            r13 = r12.entities;
            goto L35
        L31:
            r22 = r12.text;
            goto L32
        L18:
            if (PollVotesAlert.m11879$$Nest$fgetmessageObject(this.this$0).translated == false) goto L29;
            if (PollVotesAlert.m11879$$Nest$fgetmessageObject(this.this$0).messageOwner == null) goto L29;
            if (PollVotesAlert.m11879$$Nest$fgetmessageObject(this.this$0).messageOwner.translatedPoll == null) goto L29;
        L24:
            if (r2 >= PollVotesAlert.m11879$$Nest$fgetmessageObject(this.this$0).messageOwner.translatedPoll.answers.size()) goto L29;
            TLRPC.PollAnswer r32 = PollVotesAlert.m11879$$Nest$fgetmessageObject(this.this$0).messageOwner.translatedPoll.answers.get(r2);
            if (Arrays.equals(r32.option, r4.option) == true) goto L27;
            r2 = r2 + 1;
            goto L24
        L27:
            r12 = r32.text;
        L37:
            r3 = r3 + 1;
            goto L9
        L38:
            return r10;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1, int r2, int r3) {
            if (r2 == 0) goto L11;
            if (r3 != 0) goto L5;
            return false;
        L5:
            if (PollVotesAlert.m11884$$Nest$fgetqueries(this.this$0) != null) goto L7;
            return true;
        L7:
            if (PollVotesAlert.m11884$$Nest$fgetqueries(this.this$0).isEmpty() == false) goto L15;
            return true;
        L15:
            return false;
        L11:
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public void onBindViewHolder(int r10, int r11, RecyclerView.ViewHolder r12) {
            int r112 = r12.getItemViewType();
            int r1 = 0;
            if (r112 != 2) goto L5;
            SectionCell r2 = (SectionCell) r12.itemView;
            VotesList r102 = (VotesList) PollVotesAlert.m11890$$Nest$fgetvoters(this.this$0).get(r10 - 1);
            TLRPC.MessagePeerVote r113 = r102.votes.get(0);
            int r114 = PollVotesAlert.m11883$$Nest$fgetpoll(this.this$0).answers.size();
            int r122 = 0;
        L10:
            if (r122 >= r114) goto L48;
            TLRPC.PollAnswer r0 = PollVotesAlert.m11883$$Nest$fgetpoll(this.this$0).answers.get(r122);
            if (Arrays.equals(r0.option, r102.option) == false) goto L40;
            if (((Button) PollVotesAlert.m11891$$Nest$fgetvotesPercents(this.this$0).get(r102)) == null) goto L40;
            TLRPC.TL_textWithEntities r115 = r0.text;
            if (PollVotesAlert.m11879$$Nest$fgetmessageObject(this.this$0) != null) goto L19;
        L30:
            if (r115 != null) goto L33;
            String r123 = _UrlKt.FRAGMENT_ENCODE_SET;
        L32:
            String r3 = r123;
            if (r115 != null) goto L37;
            ArrayList<TLRPC.MessageEntity> r116 = null;
        L38:
            r2.setText(r3, r116, this.this$0.calcPercent(r102.option), r102.count, r102.getCollapsed(), false);
            r2.setTag(R.id.object_tag, r102);
            return;
        L37:
            r116 = r115.entities;
            goto L38
        L33:
            r123 = r115.text;
            goto L32
        L19:
            if (PollVotesAlert.m11879$$Nest$fgetmessageObject(this.this$0).translated == false) goto L30;
            if (PollVotesAlert.m11879$$Nest$fgetmessageObject(this.this$0).messageOwner == null) goto L30;
            if (PollVotesAlert.m11879$$Nest$fgetmessageObject(this.this$0).messageOwner.translatedPoll == null) goto L30;
        L25:
            if (r1 >= PollVotesAlert.m11879$$Nest$fgetmessageObject(this.this$0).messageOwner.translatedPoll.answers.size()) goto L30;
            TLRPC.PollAnswer r124 = PollVotesAlert.m11879$$Nest$fgetmessageObject(this.this$0).messageOwner.translatedPoll.answers.get(r1);
            if (Arrays.equals(r124.option, r0.option) == true) goto L28;
            r1 = r1 + 1;
            goto L25
        L28:
            r115 = r124.text;
        L40:
            r122 = r122 + 1;
            goto L10
        L48:
            return;
        L5:
            if (r112 != 3) goto L41;
            TextCell r117 = (TextCell) r12.itemView;
            VotesList r9 = (VotesList) PollVotesAlert.m11890$$Nest$fgetvoters(this.this$0).get(r10 - 1);
            r117.setTextAndIcon(LocaleController.formatPluralString("ShowVotes", r9.count - r9.getCount(), new Object[0]), R.drawable.arrow_more, false);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L4;
            View r42 = new UserCell(this.this$0, this.mContext);
        L15:
            return new RecyclerListView.Holder(r42);
        L4:
            if (r4 == 1) goto L10;
            if (r4 == 2) goto L8;
            TextCell r43 = new TextCell(this.mContext, 23, true);
            r43.setOffsetFromImage(65);
            r43.setBackgroundColor(PollVotesAlert.access$2200(this.this$0, Theme.key_dialogBackground));
            r43.setColors(Theme.key_switchTrackChecked, Theme.key_windowBackgroundWhiteBlueText4);
            r42 = r43;
            goto L15
        L8:
            View r44 = createSectionCell();
            r44.setTag(-33024);
            r42 = r44;
            goto L15
        L10:
            if (PollVotesAlert.m11888$$Nest$fgettitleTextView(this.this$0).getParent() == null) goto L12;
            ((ViewGroup) PollVotesAlert.m11888$$Nest$fgettitleTextView(this.this$0).getParent()).removeView(PollVotesAlert.m11888$$Nest$fgettitleTextView(this.this$0));
        L12:
            r42 = PollVotesAlert.m11888$$Nest$fgettitleTextView(this.this$0);
            goto L15
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r6) {
            if (r6.getItemViewType() != 0) goto L14;
            int r0 = r6.getAdapterPosition();
            int r1 = getSectionForPosition(r0);
            int r02 = getPositionInSectionForPosition(r0) - 1;
            UserCell r62 = (UserCell) r6.itemView;
            VotesList r12 = (VotesList) PollVotesAlert.m11890$$Nest$fgetvoters(this.this$0).get(r1 - 1);
            TLRPC.MessagePeerVote r2 = r12.votes.get(r02);
            TLObject r5 = this.this$0.getMessagesController().getUserOrChat(DialogObject.getPeerDialogId(r2.peer));
            int r22 = r2.date;
            boolean r4 = true;
            if (r02 == (r12.getCount() - 1)) goto L7;
        L12:
            r62.setData(r5, r22, r02, r4);
            return;
        L7:
            if (TextUtils.isEmpty(r12.next_offset) == false) goto L12;
            if (r12.collapsed == true) goto L12;
            r4 = false;
            goto L12
        }
    }

    public static class Button {
        private float decimal;
        private int percent;
        private int votesCount;

        /* renamed from: -$$Nest$fgetdecimal, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11895$$Nest$fgetdecimal(Button r0) {
            return r0.decimal;
        }

        /* renamed from: -$$Nest$fgetpercent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m11896$$Nest$fgetpercent(Button r0) {
            return r0.percent;
        }

        /* renamed from: -$$Nest$fputdecimal, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11897$$Nest$fputdecimal(Button r0, float r1) {
            r0.decimal = r1;
        }

        /* renamed from: -$$Nest$fputpercent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11898$$Nest$fputpercent(Button r0, int r1) {
            r0.percent = r1;
        }

        /* renamed from: -$$Nest$fputvotesCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11899$$Nest$fputvotesCount(Button r0, int r1) {
            r0.votesCount = r1;
        }

        private Button() {
        }

        public /* synthetic */ Button(PollVotesAlertIA r1) {
            this();
        }
    }

    public class SectionCell extends FrameLayout {
        private final TextView middleTextView;
        private final AnimatedTextView righTextView;
        private final AnimatedEmojiSpan.TextViewEmojis textView;
        final /* synthetic */ PollVotesAlert this$0;

        /* renamed from: org.telegram.ui.Components.PollVotesAlert$SectionCell$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatedTextView {
            final /* synthetic */ SectionCell this$1;
            final /* synthetic */ PollVotesAlert val$this$0;

            public AnonymousClass1(SectionCell r1, Context r2, PollVotesAlert r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.View
            public void invalidate() {
                super.invalidate();
                SectionCell r0 = this.this$1;
                if (r0 != PollVotesAlert.m11877$$Nest$fgetlistView(r0.this$0).getPinnedHeader()) goto L6;
                PollVotesAlert.m11877$$Nest$fgetlistView(this.this$1.this$0).invalidate();
                return;
            }

            @Override // android.view.View
            public boolean post(Runnable r1) {
                return PollVotesAlert.access$000(this.this$1.this$0).post(r1);
            }

            @Override // android.view.View
            public boolean postDelayed(Runnable r1, long r2) {
                return PollVotesAlert.access$100(this.this$1.this$0).postDelayed(r1, r2);
            }
        }

        /* renamed from: $r8$lambda$Ys-aNUcAsU22Z7J0y6WQ8knGcOQ, reason: not valid java name */
        public static /* synthetic */ void m11900$r8$lambda$YsaNUcAsU22Z7J0y6WQ8knGcOQ(SectionCell r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public SectionCell(PollVotesAlert r18, Context r19) {
            this.this$0 = r18;
            super(r19);
            setBackgroundColor(Theme.getColor(Theme.key_dialogBackgroundGray));
            AnimatedEmojiSpan.TextViewEmojis r2 = new AnimatedEmojiSpan.TextViewEmojis(getContext());
            this.textView = r2;
            r2.setTextSize(1, 14.0f);
            r2.setTypeface(AndroidUtilities.bold());
            int r5 = Theme.key_graySectionText;
            r2.setTextColor(Theme.getColor(r5));
            r2.setSingleLine(true);
            r2.setEllipsize(TextUtils.TruncateAt.END);
            int r7 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r6 = 5;
        L6:
            int r9 = 16;
            r2.setGravity(r6 | 16);
            TextView r62 = new TextView(getContext());
            this.middleTextView = r62;
            r62.setTextSize(1, 14.0f);
            r62.setTextColor(Theme.getColor(r5));
            if (LocaleController.isRTL == false) goto L9;
            int r3 = 5;
        L10:
            r62.setGravity(r3 | 16);
            AnonymousClass1 r32 = new AnonymousClass1(this, getContext(), r18);
            this.righTextView = r32;
            r32.setTextSize(AndroidUtilities.dp(14.0f));
            r32.setTextColor(Theme.getColor(r5));
            if (LocaleController.isRTL == false) goto L13;
            int r1 = 3;
        L14:
            r32.setGravity(r1);
            r32.setOnClickListener(new PollVotesAlert$SectionCell$$ExternalSyntheticLambda0(this));
            boolean r12 = LocaleController.isRTL;
            if (r12 == false) goto L17;
            int r4 = 5;
        L18:
            int r122 = r4 | 48;
            if (r12 == false) goto L21;
            int r52 = 0;
        L22:
            float r13 = r52;
            if (r12 == true) goto L26;
            r9 = 0;
        L26:
            addView(r2, LayoutHelper.createFrame(-2, -1.0f, r122, r13, 0.0f, r9, 0.0f));
            if (LocaleController.isRTL == false) goto L29;
            int r14 = 5;
        L30:
            addView(r62, LayoutHelper.createFrame(-2, -1.0f, r14 | 48, 0.0f, 0.0f, 0.0f, 0.0f));
            if (LocaleController.isRTL == true) goto L34;
            r7 = 5;
        L34:
            addView(r32, LayoutHelper.createFrame(-2, -1.0f, r7 | 48, 16.0f, 0.0f, 16.0f, 0.0f));
            return;
        L29:
            r14 = 3;
            goto L30
        L21:
            r52 = 16;
            goto L22
        L17:
            r4 = 3;
            goto L18
        L13:
            r1 = 5;
            goto L14
        L9:
            r3 = 3;
            goto L10
        L5:
            r6 = 3;
            goto L6
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            onCollapseClick();
        }

        public abstract void onCollapseClick();

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            boolean r12 = LocaleController.isRTL;
            AnimatedEmojiSpan.TextViewEmojis r22 = this.textView;
            if (r12 == false) goto L6;
            int r13 = r22.getLeft() - this.middleTextView.getMeasuredWidth();
            TextView r23 = this.middleTextView;
            r23.layout(r13, r23.getTop(), this.middleTextView.getMeasuredWidth() + r13, this.middleTextView.getBottom());
            return;
        L6:
            int r14 = r22.getRight();
            TextView r24 = this.middleTextView;
            r24.layout(r14, r24.getTop(), this.middleTextView.getMeasuredWidth() + r14, this.middleTextView.getBottom());
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            int r6 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30);
            measureChildWithMargins(this.middleTextView, r9, 0, r6, 0);
            measureChildWithMargins(this.righTextView, r9, 0, r6, 0);
            measureChildWithMargins(this.textView, r9, (this.middleTextView.getMeasuredWidth() + this.righTextView.getMeasuredWidth()) + AndroidUtilities.dp(32.0f), r6, 0);
            setMeasuredDimension(View.MeasureSpec.getSize(r9), AndroidUtilities.dp(32.0f));
        }

        public void setText(CharSequence r4, ArrayList<TLRPC.MessageEntity> r5, int r6, int r7, int r8, boolean r9) {
            AnimatedEmojiSpan.TextViewEmojis r0 = this.textView;
            if (r5 == null) goto L5;
            NotificationCenter.listenEmojiLoading(r0);
            SpannableStringBuilder r02 = new SpannableStringBuilder(r4);
            MediaDataController.addTextStyleRuns(r5, r4, r02);
            CharSequence r42 = Emoji.replaceEmoji(r02, this.textView.getPaint().getFontMetricsInt(), false);
            MessageObject.replaceAnimatedEmoji(r42, r5, this.textView.getPaint().getFontMetricsInt());
            this.textView.setText(r42);
        L6:
            String r43 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r6)});
            if (LocaleController.isRTL == false) goto L9;
            SpannableStringBuilder r52 = new SpannableStringBuilder(String.format("%s%% – ", new Object[]{Integer.valueOf(r6)}));
        L10:
            r52.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 3, r43.length() + 3, 33);
            this.middleTextView.setText(r52);
            if (r8 != 0) goto L18;
            boolean r44 = PollVotesAlert.m11883$$Nest$fgetpoll(this.this$0).quiz;
            AnimatedTextView r3 = this.righTextView;
            if (r44 == false) goto L16;
            r3.setText(LocaleController.formatPluralString("Answer", r7, new Object[0]), r9);
            return;
        L16:
            r3.setText(LocaleController.formatPluralString("Vote", r7, new Object[0]), r9);
            return;
        L18:
            AnimatedTextView r32 = this.righTextView;
            if (r8 != 1) goto L22;
            r32.setText(LocaleController.getString(R.string.PollExpand), r9);
            return;
        L22:
            r32.setText(LocaleController.getString(R.string.PollCollapse), r9);
            return;
        L9:
            r52 = new SpannableStringBuilder(String.format(" – %s%%", new Object[]{Integer.valueOf(r6)}));
            goto L10
        L5:
            r0.setText(Emoji.replaceEmoji(r4, r0.getPaint().getFontMetricsInt(), false));
            goto L6
        }
    }

    public class UserCell extends LinearLayout {
        private ArrayList<Animator> animators;
        private final AvatarDrawable avatarDrawable;
        private final BackupImageView avatarImageView;
        private final int currentAccount;
        private TLRPC.Chat currentChat;
        private TLRPC.User currentUser;
        private final TextView dateTextView;
        private boolean drawPlaceholder;
        private TLRPC.FileLocation lastAvatar;
        private CharSequence lastName;
        private int lastStatus;
        private final SimpleTextView nameTextView;
        private boolean needDivider;
        private float placeholderAlpha;
        private int placeholderNum;
        private final StatusBadgeComponent statusBadgeComponent;
        final /* synthetic */ PollVotesAlert this$0;
        private final TextView timeTextView;

        /* renamed from: -$$Nest$fgetcurrentChat, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.Chat m11901$$Nest$fgetcurrentChat(UserCell r0) {
            return r0.currentChat;
        }

        /* renamed from: -$$Nest$fgetcurrentUser, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.User m11902$$Nest$fgetcurrentUser(UserCell r0) {
            return r0.currentUser;
        }

        /* renamed from: -$$Nest$fputanimators, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11903$$Nest$fputanimators(UserCell r0, ArrayList r1) {
            r0.animators = r1;
        }

        public UserCell(PollVotesAlert r13, Context r14) {
            this.this$0 = r13;
            super(r14);
            this.currentAccount = UserConfig.selectedAccount;
            this.placeholderAlpha = 1.0f;
            setOrientation(0);
            int r0 = 3;
            setLayoutDirection(3);
            setWillNotDraw(false);
            setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
            this.avatarDrawable = new AvatarDrawable();
            BackupImageView r132 = new BackupImageView(r14);
            this.avatarImageView = r132;
            r132.setRoundRadius(ExteraConfig.getAvatarCorners(34.0f));
            addView(r132, LayoutHelper.createLinear(34, 34, 16, 0, 0, 11, 0));
            SimpleTextView r133 = new SimpleTextView(r14);
            this.nameTextView = r133;
            int r1 = Theme.key_dialogTextBlack;
            r133.setTextColor(Theme.getColor(r1));
            r133.setTypeface(AndroidUtilities.bold());
            r133.setTextSize(16);
            if (LocaleController.isRTL == false) goto L5;
            r0 = 5;
        L5:
            r133.setGravity(r0 | 16);
            addView(r133, LayoutHelper.createLinear(0, 24, 1.0f, 16, 0, 0, 0, 0));
            TextView r02 = new TextView(r14);
            this.dateTextView = r02;
            r02.setTextColor(Theme.getColor(Theme.key_dialogTextGray3));
            r02.setTextSize(1, 13.0f);
            addView(r02, LayoutHelper.createLinear(-2, -2, 0.0f, 21, 4, 0, 2, 0));
            TextView r03 = new TextView(r14);
            this.timeTextView = r03;
            r03.setTextColor(Theme.getColor(r1));
            r03.setTextSize(1, 13.0f);
            addView(r03, LayoutHelper.createLinear(-2, -2, 0.0f, 21, 2, 0, 4, 0));
            this.statusBadgeComponent = new StatusBadgeComponent(r133, 20);
        }

        @Keep
        public float getPlaceholderAlpha() {
            return this.placeholderAlpha;
        }

        @Override // android.view.View
        public boolean hasOverlappingRendering() {
            return false;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.statusBadgeComponent.onAttachedToWindow();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            this.statusBadgeComponent.onDetachedFromWindow();
            super.onDetachedFromWindow();
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onDraw(Canvas r10) {
            float r1 = 0.0f;
            if (this.drawPlaceholder == false) goto L5;
        L6:
            PollVotesAlert.m11882$$Nest$fgetplaceholderPaint(this.this$0).setAlpha((int) (this.placeholderAlpha * 255.0f));
            r10.drawCircle(this.avatarImageView.getLeft() + (this.avatarImageView.getMeasuredWidth() / 2), this.avatarImageView.getTop() + (this.avatarImageView.getMeasuredHeight() / 2), this.avatarImageView.getMeasuredWidth() / 2, PollVotesAlert.m11882$$Nest$fgetplaceholderPaint(this.this$0));
            if ((this.placeholderNum % 2) != 0) goto L9;
            int r0 = AndroidUtilities.dp(65.0f);
            int r4 = AndroidUtilities.dp(48.0f);
        L11:
            if (LocaleController.isRTL == false) goto L13;
            r0 = (getMeasuredWidth() - r0) - r4;
        L13:
            PollVotesAlert.m11885$$Nest$fgetrect(this.this$0).set(r0, r2 - AndroidUtilities.dp(4.0f), r0 + r4, AndroidUtilities.dp(4.0f) + r2);
            r10.drawRoundRect(PollVotesAlert.m11885$$Nest$fgetrect(this.this$0), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), PollVotesAlert.m11882$$Nest$fgetplaceholderPaint(this.this$0));
            if ((this.placeholderNum % 2) != 0) goto L16;
            int r02 = AndroidUtilities.dp(119.0f);
            int r3 = AndroidUtilities.dp(60.0f);
        L18:
            if (LocaleController.isRTL == false) goto L20;
            r02 = (getMeasuredWidth() - r02) - r3;
        L20:
            PollVotesAlert.m11885$$Nest$fgetrect(this.this$0).set(r02, r2 - AndroidUtilities.dp(4.0f), r02 + r3, r2 + AndroidUtilities.dp(4.0f));
            r10.drawRoundRect(PollVotesAlert.m11885$$Nest$fgetrect(this.this$0), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), PollVotesAlert.m11882$$Nest$fgetplaceholderPaint(this.this$0));
        L22:
            if (this.needDivider == true) goto L24;
            return;
        L24:
            if (LocaleController.isRTL == false) goto L26;
        L25:
            float r42 = r1;
            float r5 = getMeasuredHeight() - 1;
            int r03 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L30;
            int r12 = AndroidUtilities.dp(64.0f);
        L31:
            r10.drawLine(r42, r5, r03 - r12, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L30:
            r12 = 0;
            goto L31
        L26:
            r1 = AndroidUtilities.dp(64.0f);
            goto L25
        L16:
            r02 = AndroidUtilities.dp(131.0f);
            r3 = AndroidUtilities.dp(80.0f);
            goto L18
        L9:
            r0 = AndroidUtilities.dp(65.0f);
            r4 = AndroidUtilities.dp(60.0f);
            goto L11
        L5:
            if (this.placeholderAlpha == 0.0f) goto L22;
            goto L6
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        }

        public void setData(TLObject r8, int r9, int r10, boolean r11) {
            if ((r8 instanceof TLRPC.User) == false) goto L6;
            this.currentUser = (TLRPC.User) r8;
            this.currentChat = null;
        L9:
            long r3 = r9;
            this.timeTextView.setText(LocaleController.getInstance().getFormatterDay().format(1000 * r3));
            boolean r0 = true;
            this.dateTextView.setText(LocaleController.formatDate(r3, true));
            this.needDivider = r11;
            if (r8 == null) goto L13;
            r0 = false;
        L13:
            this.drawPlaceholder = r0;
            this.placeholderNum = r10;
            if (r8 != null) goto L16;
            this.nameTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.avatarImageView.setImageDrawable(null);
        L17:
            ArrayList<Animator> r82 = this.animators;
            if (r82 == null) goto L22;
            Property r02 = View.ALPHA;
            r82.add(ObjectAnimator.ofFloat(this.avatarImageView, r02, new float[]{0.0f, 1.0f}));
            this.animators.add(ObjectAnimator.ofFloat(this.nameTextView, r02, new float[]{0.0f, 1.0f}));
            this.animators.add(ObjectAnimator.ofFloat(this, PollVotesAlert.USER_CELL_PROPERTY, new float[]{1.0f, 0.0f}));
            return;
        L22:
            if (this.drawPlaceholder == true) goto L25;
            this.placeholderAlpha = 0.0f;
            return;
        L25:
            return;
        L16:
            update(0);
            goto L17
        L6:
            if ((r8 instanceof TLRPC.Chat) == false) goto L8;
            this.currentChat = (TLRPC.Chat) r8;
            this.currentUser = null;
            goto L9
        L8:
            this.currentUser = null;
            this.currentChat = null;
            goto L9
        }

        @Keep
        public void setPlaceholderAlpha(float r1) {
            this.placeholderAlpha = r1;
            invalidate();
        }

        public void update(int r11) {
            TLRPC.User r0 = this.currentUser;
            String r1 = null;
            if (r0 == null) goto L7;
            TLRPC.UserProfilePhoto r2 = r0.photo;
            if (r2 == null) goto L7;
            TLRPC.FileLocation r22 = r2.photo_small;
        L14:
            if (r11 == 0) goto L57;
            boolean r5 = true;
            if ((MessagesController.UPDATE_MASK_AVATAR & r11) == 0) goto L29;
            TLRPC.FileLocation r4 = this.lastAvatar;
            if (r4 == null) goto L20;
            if (r22 != null) goto L20;
        L28:
            boolean r42 = true;
        L30:
            if (r0 == null) goto L41;
            if (r42 == true) goto L41;
            if ((MessagesController.UPDATE_MASK_STATUS & r11) == 0) goto L41;
            TLRPC.UserStatus r6 = r0.status;
            if (r6 == null) goto L37;
            int r62 = r6.expires;
        L39:
            if (r62 == this.lastStatus) goto L41;
            r42 = true;
            goto L41
        L37:
            r62 = 0;
        L41:
            if (r42 == false) goto L43;
        L54:
            r5 = r42;
        L55:
            if (r5 == true) goto L57;
            return;
        L43:
            if (this.lastName == null) goto L54;
            if ((r11 & MessagesController.UPDATE_MASK_NAME) == 0) goto L54;
            if (r0 == null) goto L48;
            r1 = UserObject.getUserName(r0);
        L52:
            if (TextUtils.equals(r1, this.lastName) == true) goto L54;
        L48:
            TLRPC.Chat r112 = this.currentChat;
            if (r112 == null) goto L52;
            r1 = r112.title;
        L20:
            if (r4 != null) goto L22;
            if (r22 != null) goto L28;
        L22:
            if (r4 == null) goto L29;
            if (r22 == null) goto L29;
            if (r4.volume_id != r22.volume_id) goto L28;
            if (r4.local_id != r22.local_id) goto L28;
        L29:
            r42 = false;
        L57:
            TLRPC.User r113 = this.currentUser;
            if (r113 == null) goto L63;
            this.avatarDrawable.setInfo(this.currentAccount, r113);
            TLRPC.UserStatus r114 = this.currentUser.status;
            if (r114 == null) goto L62;
            this.lastStatus = r114.expires;
        L66:
            TLRPC.User r115 = this.currentUser;
            if (r115 == null) goto L71;
            if (r1 != null) goto L70;
            r1 = UserObject.getUserName(r115);
        L70:
            this.lastName = r1;
            this.lastName = Emoji.replaceEmoji(r1, this.nameTextView.getPaint().getFontMetricsInt(), false);
        L75:
            this.nameTextView.setText(this.lastName);
            this.nameTextView.setRightDrawable(this.statusBadgeComponent.updateDrawable(this.currentUser, this.currentChat, Theme.getColor(Theme.key_chats_verifiedBackground, PollVotesAlert.access$200(this.this$0)), false));
            this.lastAvatar = r22;
            TLRPC.Chat r116 = this.currentChat;
            if (r116 == null) goto L79;
            this.avatarImageView.setForUserOrChat(r116, this.avatarDrawable);
            return;
        L79:
            TLRPC.User r117 = this.currentUser;
            BackupImageView r02 = this.avatarImageView;
            if (r117 == null) goto L83;
            r02.setForUserOrChat(r117, this.avatarDrawable);
            return;
        L83:
            r02.setImageDrawable(this.avatarDrawable);
            return;
        L71:
            TLRPC.Chat r118 = this.currentChat;
            if (r118 == null) goto L74;
            String r119 = r118.title;
            this.lastName = r119;
            this.lastName = Emoji.replaceEmoji(r119, this.nameTextView.getPaint().getFontMetricsInt(), false);
            goto L75
        L74:
            this.lastName = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L75
        L62:
            this.lastStatus = 0;
            goto L66
        L63:
            TLRPC.Chat r1110 = this.currentChat;
            if (r1110 == null) goto L66;
            this.avatarDrawable.setInfo(this.currentAccount, r1110);
        L7:
            TLRPC.Chat r23 = this.currentChat;
            if (r23 == null) goto L12;
            TLRPC.ChatPhoto r24 = r23.photo;
            if (r24 == null) goto L12;
            r22 = r24.photo_small;
        L12:
            r22 = null;
            goto L14
        }
    }

    public static class VotesList {
        public boolean collapsed;
        public int collapsedCount;
        public int count;
        public String next_offset;
        public byte[] option;
        public ArrayList<TLRPC.User> users;
        public ArrayList<TLRPC.MessagePeerVote> votes;

        public VotesList(TLRPC.TL_messages_votesList r2, byte[] r3) {
            this.collapsedCount = 10;
            this.count = r2.count;
            this.votes = r2.votes;
            this.users = r2.users;
            this.next_offset = r2.next_offset;
            this.option = r3;
        }

        public int getCollapsed() {
            if (this.votes.size() > 15) goto L7;
            return 0;
        L7:
            if (this.collapsed == false) goto L10;
            return 1;
        L10:
            return 2;
        }

        public int getCount() {
            if (this.collapsed == false) goto L7;
            return Math.min(this.collapsedCount, this.votes.size());
        L7:
            return this.votes.size();
        }
    }

    public static /* synthetic */ void $r8$lambda$3H46L0BZr1XW6v1GYDB006itbgI(PollVotesAlert r0, Context r1, View r2, int r3) {
        r0.lambda$new$4(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$F0dbjmTSf-6JLct_cEk4CmPQdUc, reason: not valid java name */
    public static /* synthetic */ void m11869$r8$lambda$F0dbjmTSf6JLct_cEk4CmPQdUc(PollVotesAlert r0, VotesList r1, TLObject r2) {
        r0.lambda$new$2(r1, r2);
    }

    /* renamed from: $r8$lambda$FQIb_wws-2uU5VAs1x2U4THvRXQ, reason: not valid java name */
    public static /* synthetic */ int m11870$r8$lambda$FQIb_wws2uU5VAs1x2U4THvRXQ(Button r2, Button r3) {
        if (Button.m11895$$Nest$fgetdecimal(r2) <= Button.m11895$$Nest$fgetdecimal(r3)) goto L7;
        return -1;
    L7:
        if (Button.m11895$$Nest$fgetdecimal(r2) >= Button.m11895$$Nest$fgetdecimal(r3)) goto L10;
        return 1;
    L10:
        return 0;
    }

    /* renamed from: $r8$lambda$UswOuijxR1K-nid7qzR6OAGBp-M, reason: not valid java name */
    public static /* synthetic */ void m11871$r8$lambda$UswOuijxR1Knid7qzR6OAGBpM(PollVotesAlert r0) {
        r0.updatePlaceholder();
    }

    public static /* synthetic */ void $r8$lambda$VyOXvVbh60tbrIOvgxhTNooiX_Y(PollVotesAlert r0, Integer[] r1, int r2, ArrayList r3, TLRPC.PollAnswerVoters r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$new$1(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$dKA7Zw2tlXvjQ-WswPmyDwci8ms, reason: not valid java name */
    public static /* synthetic */ void m11872$r8$lambda$dKA7Zw2tlXvjQWswPmyDwci8ms(PollVotesAlert r0, VotesList r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$new$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$pP9FYE42IrkER1vnleHVSCNdqH8(PollVotesAlert r0, Integer[] r1, int r2, TLObject r3, ArrayList r4, TLRPC.PollAnswerVoters r5) {
        r0.lambda$new$0(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$fgetactionBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBar m11873$$Nest$fgetactionBar(PollVotesAlert r0) {
        return r0.actionBar;
    }

    /* renamed from: -$$Nest$fgetactionBarShadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m11874$$Nest$fgetactionBarShadow(PollVotesAlert r0) {
        return r0.actionBarShadow;
    }

    /* renamed from: -$$Nest$fgetgradientWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11875$$Nest$fgetgradientWidth(PollVotesAlert r0) {
        return r0.gradientWidth;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Adapter m11876$$Nest$fgetlistAdapter(PollVotesAlert r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m11877$$Nest$fgetlistView(PollVotesAlert r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetloadingResults, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11878$$Nest$fgetloadingResults(PollVotesAlert r0) {
        return r0.loadingResults;
    }

    /* renamed from: -$$Nest$fgetmessageObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m11879$$Nest$fgetmessageObject(PollVotesAlert r0) {
        return r0.messageObject;
    }

    /* renamed from: -$$Nest$fgetplaceholderGradient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearGradient m11880$$Nest$fgetplaceholderGradient(PollVotesAlert r0) {
        return r0.placeholderGradient;
    }

    /* renamed from: -$$Nest$fgetplaceholderMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m11881$$Nest$fgetplaceholderMatrix(PollVotesAlert r0) {
        return r0.placeholderMatrix;
    }

    /* renamed from: -$$Nest$fgetplaceholderPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11882$$Nest$fgetplaceholderPaint(PollVotesAlert r0) {
        return r0.placeholderPaint;
    }

    /* renamed from: -$$Nest$fgetpoll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Poll m11883$$Nest$fgetpoll(PollVotesAlert r0) {
        return r0.poll;
    }

    /* renamed from: -$$Nest$fgetqueries, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11884$$Nest$fgetqueries(PollVotesAlert r0) {
        return r0.queries;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m11885$$Nest$fgetrect(PollVotesAlert r0) {
        return r0.rect;
    }

    /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11886$$Nest$fgetscrollOffsetY(PollVotesAlert r0) {
        return r0.scrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetshadowDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m11887$$Nest$fgetshadowDrawable(PollVotesAlert r0) {
        return r0.shadowDrawable;
    }

    /* renamed from: -$$Nest$fgettitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedEmojiSpan.TextViewEmojis m11888$$Nest$fgettitleTextView(PollVotesAlert r0) {
        return r0.titleTextView;
    }

    /* renamed from: -$$Nest$fgettotalTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11889$$Nest$fgettotalTranslation(PollVotesAlert r0) {
        return r0.totalTranslation;
    }

    /* renamed from: -$$Nest$fgetvoters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11890$$Nest$fgetvoters(PollVotesAlert r0) {
        return r0.voters;
    }

    /* renamed from: -$$Nest$fgetvotesPercents, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m11891$$Nest$fgetvotesPercents(PollVotesAlert r0) {
        return r0.votesPercents;
    }

    /* renamed from: -$$Nest$fputactionBarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11892$$Nest$fputactionBarAnimation(PollVotesAlert r0, AnimatorSet r1) {
        r0.actionBarAnimation = r1;
    }

    /* renamed from: -$$Nest$fputtotalTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11893$$Nest$fputtotalTranslation(PollVotesAlert r0, float r1) {
        r0.totalTranslation = r1;
    }

    /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11894$$Nest$mupdateLayout(PollVotesAlert r0, boolean r1) {
        r0.updateLayout(r1);
    }

    static {
        USER_CELL_PROPERTY = new AnonymousClass1("placeholderAlpha");
    }

    public PollVotesAlert(Context r17, int r18, MessageObject r19, Theme.ResourcesProvider r20) {
        super(r17, true, r20);
        this.loadingMore = new HashSet();
        this.votesPercents = new HashMap();
        this.voters = new ArrayList();
        this.queries = new ArrayList();
        this.placeholderPaint = new Paint(1);
        this.loadingResults = true;
        this.rect = new RectF();
        this.currentAccount = r18;
        this.occupyNavigationBar = true;
        fixNavigationBar();
        this.messageObject = r19;
        TLRPC.TL_messageMediaPoll r8 = (TLRPC.TL_messageMediaPoll) r19.messageOwner.media;
        this.messageMediaPoll = r8;
        this.poll = r8.poll;
        this.peer = getMessagesController().getInputPeer(r19.getDialogId());
        ArrayList r4 = new ArrayList();
        int r9 = r8.results.results.size();
        Integer[] r2 = new Integer[r9];
        int r3 = 0;
    L3:
        if (r3 >= r9) goto L24;
        TLRPC.PollAnswerVoters r5 = r8.results.results.get(r3);
        if (r5.voters == 0) goto L23;
        TLRPC.TL_messages_votesList r0 = new TLRPC.TL_messages_votesList();
        int r11 = r5.voters;
        if (r11 <= 15) goto L11;
        r11 = 10;
    L11:
        int r14 = 0;
    L12:
        if (r14 >= r11) goto L14;
        r0.votes.add(new TLRPC.TL_messagePeerVoteInputOption());
        r14 = r14 + 1;
        goto L12
    L14:
        int r12 = r5.voters;
        if (r11 >= r12) goto L17;
        String r112 = "empty";
    L18:
        r0.next_offset = r112;
        r0.count = r12;
        this.voters.add(new VotesList(r0, r5.option));
        TLRPC.TL_messages_getPollVotes r113 = new TLRPC.TL_messages_getPollVotes();
        r113.peer = this.peer;
        r113.id = this.messageObject.getId();
        if (r5.voters > 15) goto L21;
        int r122 = 15;
    L22:
        r113.limit = r122;
        r113.flags |= 1;
        r113.option = r5.option;
        Integer r02 = Integer.valueOf(getConnectionsManager().sendRequest(r113, new PollVotesAlert$$ExternalSyntheticLambda0(this, r2, r3, r4, r5)));
        r2[r3] = r02;
        this.queries.add(r02);
        goto L23
    L21:
        r122 = 10;
        goto L22
    L17:
        r112 = null;
    L23:
        r3 = r3 + 1;
        goto L3
    L24:
        updateButtons();
        Collections.sort(this.voters, new AnonymousClass2(this));
        updatePlaceholder();
        Drawable r03 = r17.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        this.shadowDrawable = r03;
        r03.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogBackgroundGray), PorterDuff.Mode.MULTIPLY));
        AnonymousClass3 r04 = new AnonymousClass3(this, r17);
        this.containerView = r04;
        r04.setWillNotDraw(false);
        ViewGroup r05 = this.containerView;
        int r22 = this.backgroundPaddingLeft;
        r05.setPadding(r22, 0, r22, 0);
        AnonymousClass4 r06 = new AnonymousClass4(this, r17);
        this.listView = r06;
        r06.setSections(false);
        DefaultItemAnimator r07 = new DefaultItemAnimator();
        r07.setAddDuration(150);
        r07.setMoveDuration(350);
        r07.setChangeDuration(0);
        r07.setRemoveDuration(0);
        r07.setDelayAnimations(false);
        r07.setMoveInterpolator(new OvershootInterpolator(1.1f));
        r07.setTranslationInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.listView.setItemAnimator(r07);
        this.listView.setClipToPadding(false);
        this.listView.setLayoutManager(new AnonymousClass5(this, getContext(), 1, false));
        this.listView.setHorizontalScrollBarEnabled(false);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setSectionsType(2);
        this.containerView.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        RecyclerListView r08 = this.listView;
        Adapter r23 = new Adapter(this, r17);
        this.listAdapter = r23;
        r08.setAdapter(r23);
        this.listView.setGlowColor(Theme.getColor(Theme.key_dialogScrollGlow));
        this.listView.setOnItemClickListener(new PollVotesAlert$$ExternalSyntheticLambda1(this, r17));
        this.listView.setOnScrollListener(new AnonymousClass6(this));
        AnimatedEmojiSpan.TextViewEmojis r09 = new AnimatedEmojiSpan.TextViewEmojis(r17);
        this.titleTextView = r09;
        r09.setTextSize(1, 18.0f);
        r09.setTypeface(AndroidUtilities.bold());
        r09.setPadding(AndroidUtilities.dp(21.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(21.0f));
        int r24 = Theme.key_dialogTextBlack;
        r09.setTextColor(Theme.getColor(r24));
        r09.setTag(-33024);
        r09.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        TLRPC.TL_textWithEntities r32 = this.poll.question;
        if (r32 == null) goto L38;
        MessageObject r52 = this.messageObject;
        if (r52 != null) goto L29;
    L37:
        NotificationCenter.listenEmojiLoading(r09);
        SpannableStringBuilder r53 = new SpannableStringBuilder(r32.text);
        MediaDataController.addTextStyleRuns(r32.entities, r32.text, r53);
        CharSequence r54 = Emoji.replaceEmoji(r53, r09.getPaint().getFontMetricsInt(), false);
        MessageObject.replaceAnimatedEmoji(r54, r32.entities, r09.getPaint().getFontMetricsInt());
        r09.setText(r54);
        goto L38
    L29:
        if (r52.translated == false) goto L37;
        TLRPC.Message r55 = r52.messageOwner;
        if (r55 == null) goto L37;
        TranslateController.PollText r56 = r55.translatedPoll;
        if (r56 == null) goto L37;
        TLRPC.TL_textWithEntities r57 = r56.question;
        if (r57 == null) goto L37;
        r32 = r57;
    L38:
        AnonymousClass7 r010 = new AnonymousClass7(this, r17);
        this.actionBar = r010;
        r010.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground));
        r010.setBackButtonImage(R.drawable.ic_ab_back);
        r010.setItemsColor(Theme.getColor(r24), false);
        r010.setItemsBackgroundColor(Theme.getColor(Theme.key_dialogButtonSelector), false);
        r010.setTitleColor(Theme.getColor(r24));
        r010.setSubtitleColor(Theme.getColor(Theme.key_player_actionBarSubtitle));
        r010.setOccupyStatusBar(false);
        r010.setAlpha(0.0f);
        r010.setTitle(LocaleController.getString(R.string.PollResults));
        boolean r33 = this.poll.quiz;
        TLRPC.PollResults r58 = r8.results;
        if (r33 == false) goto L41;
        r010.setSubtitle(LocaleController.formatPluralString("Answer", r58.total_voters, new Object[0]));
    L42:
        this.containerView.addView(r010, LayoutHelper.createFrame(-1, -2.0f));
        r010.setActionBarMenuOnItemClick(new AnonymousClass8(this));
        View r011 = new View(r17);
        this.actionBarShadow = r011;
        r011.setAlpha(0.0f);
        r011.setBackgroundColor(Theme.getColor(Theme.key_dialogShadowLine));
        this.containerView.addView(r011, LayoutHelper.createFrame(-1, 1.0f));
        return;
    L41:
        r010.setSubtitle(LocaleController.formatPluralString("Vote", r58.total_voters, new Object[0]));
        goto L42
    }

    public static /* synthetic */ ViewGroup access$000(PollVotesAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$100(PollVotesAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$1000(PollVotesAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1100(PollVotesAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1200(PollVotesAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1300(PollVotesAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1400(PollVotesAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1500(PollVotesAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1600(PollVotesAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1700(PollVotesAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1800(PollVotesAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1900(PollVotesAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(PollVotesAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$2000(PollVotesAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ ViewGroup access$2100(PollVotesAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$2200(PollVotesAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ boolean access$300(PollVotesAlert r0) {
        return r0.isFullscreen;
    }

    public static /* synthetic */ int access$400(PollVotesAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$500(PollVotesAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$600(PollVotesAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$700(PollVotesAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$800(PollVotesAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$900(PollVotesAlert r0) {
        return r0.currentSheetAnimationType;
    }

    private /* synthetic */ void lambda$new$0(Integer[] r8, int r9, TLObject r10, ArrayList r11, TLRPC.PollAnswerVoters r12) {
        this.queries.remove(r8[r9]);
        if (r10 == null) goto L51;
        TLRPC.TL_messages_votesList r102 = (TLRPC.TL_messages_votesList) r10;
        getMessagesController().putUsers(r102.users, false);
        if (r102.votes.isEmpty() == true) goto L8;
        r11.add(new VotesList(r102, r12.option));
    L8:
        if (this.queries.isEmpty() == false) goto L61;
        int r82 = r11.size();
        int r92 = 0;
        boolean r103 = false;
    L11:
        if (r92 >= r82) goto L24;
        VotesList r1 = (VotesList) r11.get(r92);
        int r2 = this.voters.size();
        int r3 = 0;
    L13:
        if (r3 >= r2) goto L23;
        VotesList r4 = this.voters.get(r3);
        if (Arrays.equals(r1.option, r4.option) == true) goto L16;
        r3 = r3 + 1;
        goto L13
    L16:
        r4.next_offset = r1.next_offset;
        if (r4.count == r1.count) goto L19;
    L20:
        r103 = true;
    L21:
        r4.count = r1.count;
        r4.users = r1.users;
        r4.votes = r1.votes;
        goto L23
    L19:
        if (r4.votes.size() == r1.votes.size()) goto L21;
    L23:
        r92 = r92 + 1;
        goto L11
    L24:
        this.loadingResults = false;
        RecyclerListView r83 = this.listView;
        if (r83 != null) goto L27;
        return;
    L27:
        if (this.currentSheetAnimationType == 0) goto L29;
    L47:
        if (r103 == false) goto L49;
        updateButtons();
    L49:
        this.listAdapter.notifyDataSetChanged();
        return;
    L29:
        if (this.startAnimationRunnable != null) goto L47;
        if (r103 == true) goto L47;
        int r84 = r83.getChildCount();
        ArrayList r93 = new ArrayList();
        int r104 = 0;
    L33:
        if (r104 >= r84) goto L43;
        View r112 = this.listView.getChildAt(r104);
        if ((r112 instanceof UserCell) == false) goto L41;
        RecyclerView.ViewHolder r13 = this.listView.findContainingViewHolder(r112);
        if (r13 == null) goto L41;
        UserCell r113 = (UserCell) r112;
        UserCell.m11903$$Nest$fputanimators(r113, r93);
        r113.setEnabled(true);
        this.listAdapter.onViewAttachedToWindow(r13);
        UserCell.m11903$$Nest$fputanimators(r113, null);
    L41:
        r104 = r104 + 1;
        goto L33
    L43:
        if (r93.isEmpty() == true) goto L45;
        AnimatorSet r85 = new AnimatorSet();
        r85.playTogether(r93);
        r85.setDuration(180);
        r85.start();
    L45:
        this.loadingResults = false;
        return;
    L61:
        return;
    L51:
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(Integer[] r1, int r2, ArrayList r3, TLRPC.PollAnswerVoters r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new PollVotesAlert$$ExternalSyntheticLambda4(this, r1, r2, r5, r3, r4));
    }

    private /* synthetic */ void lambda$new$2(VotesList r4, TLObject r5) {
        if (isShowing() == false) goto L10;
        this.loadingMore.remove(r4);
        if (r5 == null) goto L9;
        TLRPC.TL_messages_votesList r52 = (TLRPC.TL_messages_votesList) r5;
        getMessagesController().putUsers(r52.users, false);
        r4.votes.addAll(r52.votes);
        r4.next_offset = r52.next_offset;
        animateSectionUpdates(null);
        this.listAdapter.update(true);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$new$3(VotesList r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new PollVotesAlert$$ExternalSyntheticLambda6(this, r1, r2));
    }

    private /* synthetic */ void lambda$new$4(Context r5, View r6, int r7) {
        if (AndroidUtilities.isContextSafe(r5) == false) goto L63;
        ArrayList<Integer> r52 = this.queries;
        if (r52 != null) goto L7;
    L9:
        int r0 = 0;
        if ((r6 instanceof TextCell) == false) goto L32;
        int r53 = this.listAdapter.getSectionForPosition(r7) - 1;
        int r62 = this.listAdapter.getPositionInSectionForPosition(r7) - 1;
        if (r62 <= 0) goto L64;
        if (r53 < 0) goto L68;
        VotesList r54 = this.voters.get(r53);
        if (r62 == r54.getCount()) goto L18;
        return;
    L18:
        if (this.loadingMore.contains(r54) == false) goto L21;
        return;
    L21:
        if (r54.collapsed == true) goto L23;
    L29:
        this.loadingMore.add(r54);
        TLRPC.TL_messages_getPollVotes r63 = new TLRPC.TL_messages_getPollVotes();
        r63.peer = this.peer;
        r63.id = this.messageObject.getId();
        r63.limit = 50;
        int r72 = r63.flags;
        r63.option = r54.option;
        r63.flags = r72 | 3;
        r63.offset = r54.next_offset;
        getConnectionsManager().sendRequest(r63, new PollVotesAlert$$ExternalSyntheticLambda5(this, r54));
        return;
    L23:
        if (r54.collapsedCount >= r54.votes.size()) goto L29;
        int r64 = Math.min(r54.collapsedCount + 50, r54.votes.size());
        r54.collapsedCount = r64;
        if (r64 != r54.votes.size()) goto L27;
        r54.collapsed = false;
    L27:
        animateSectionUpdates(null);
        this.listAdapter.update(true);
        return;
    L68:
        return;
    L64:
        return;
    L32:
        if ((r6 instanceof UserCell) == false) goto L66;
        UserCell r65 = (UserCell) r6;
        if (UserCell.m11902$$Nest$fgetcurrentUser(r65) == null) goto L36;
    L38:
        Bundle r55 = new Bundle();
        if (UserCell.m11902$$Nest$fgetcurrentUser(r65) == null) goto L41;
        r55.putLong("user_id", UserCell.m11902$$Nest$fgetcurrentUser(r65).id);
    L42:
        dismiss();
        BaseFragment r4 = LaunchActivity.getSafeLastFragment();
        if (r4 == null) goto L71;
        ProfileActivity r73 = new ProfileActivity(r55);
        if ((r4 instanceof ChatActivity) == true) goto L48;
    L61:
        r4.presentFragment(r73);
        return;
    L48:
        if (UserCell.m11902$$Nest$fgetcurrentUser(r65) == null) goto L55;
        TLRPC.User r56 = ((ChatActivity) r4).getCurrentUser();
        if (r56 != null) goto L52;
    L54:
        r73.setPlayProfileAnimation(r0);
        goto L61
    L52:
        if (r56.id != UserCell.m11902$$Nest$fgetcurrentUser(r65).id) goto L54;
        r0 = 1;
        goto L54
    L55:
        TLRPC.Chat r57 = ((ChatActivity) r4).getCurrentChat();
        if (r57 != null) goto L58;
    L60:
        r73.setPlayProfileAnimation(r0);
        goto L61
    L58:
        if (r57.id != UserCell.m11901$$Nest$fgetcurrentChat(r65).id) goto L60;
        r0 = 1;
        goto L60
    L71:
        return;
    L41:
        r55.putLong("chat_id", UserCell.m11901$$Nest$fgetcurrentChat(r65).id);
        goto L42
    L36:
        if (UserCell.m11901$$Nest$fgetcurrentChat(r65) != null) goto L38;
        return;
    L66:
        return;
    L7:
        if (r52.isEmpty() == true) goto L9;
        return;
    }

    public static void showForPoll(BaseFragment r4, MessageObject r5) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r4.getParentActivity() == null) goto L9;
        r4.showDialog(new PollVotesAlert(r4.getContext(), r4.getCurrentAccount(), r5, r4.getResourceProvider()));
        return;
    }

    private void updateButtons() {
        this.votesPercents.clear();
        TLRPC.TL_messageMediaPoll r1 = (TLRPC.TL_messageMediaPoll) this.messageObject.messageOwner.media;
        ArrayList r2 = new ArrayList();
        int r3 = this.voters.size();
        int r4 = 100;
        int r6 = 0;
        boolean r7 = false;
        int r8 = 0;
        int r9 = 0;
    L3:
        if (r6 >= r3) goto L22;
        VotesList r11 = this.voters.get(r6);
        Button r12 = new Button(null);
        r2.add(r12);
        this.votesPercents.put(r11, r12);
        if (r1.results.results.isEmpty() == true) goto L20;
        int r13 = r1.results.results.size();
        int r14 = 0;
    L7:
        if (r14 >= r13) goto L20;
        TLRPC.PollAnswerVoters r15 = r1.results.results.get(r14);
        if (Arrays.equals(r11.option, r15.option) == true) goto L10;
        r14 = r14 + 1;
        goto L7
    L10:
        Button.m11899$$Nest$fputvotesCount(r12, r15.voters);
        Button.m11897$$Nest$fputdecimal(r12, (r15.voters / r1.results.total_voters) * 100.0f);
        Button.m11898$$Nest$fputpercent(r12, (int) Button.m11895$$Nest$fgetdecimal(r12));
        Button.m11897$$Nest$fputdecimal(r12, Button.m11895$$Nest$fgetdecimal(r12) - Button.m11896$$Nest$fgetpercent(r12));
        if (r8 != 0) goto L14;
        r8 = Button.m11896$$Nest$fgetpercent(r12);
    L18:
        r4 = r4 - Button.m11896$$Nest$fgetpercent(r12);
        r9 = Math.max(Button.m11896$$Nest$fgetpercent(r12), r9);
        goto L20
    L14:
        if (Button.m11896$$Nest$fgetpercent(r12) == 0) goto L18;
        if (r8 == Button.m11896$$Nest$fgetpercent(r12)) goto L18;
        r7 = true;
    L20:
        r6 = r6 + 1;
        goto L3
    L22:
        if (r7 == false) goto L27;
        if (r4 == 0) goto L34;
        Collections.sort(r2, new PollVotesAlert$$ExternalSyntheticLambda3());
        int r0 = Math.min(r4, r2.size());
        int r5 = 0;
    L25:
        if (r5 >= r0) goto L35;
        Button r16 = (Button) r2.get(r5);
        Button.m11898$$Nest$fputpercent(r16, Button.m11896$$Nest$fgetpercent(r16) + 1);
        r5 = r5 + 1;
        goto L25
    L35:
        return;
    L34:
        return;
    }

    private void updateLayout(boolean r11) {
        int r112 = this.listView.getChildCount();
        RecyclerListView r0 = this.listView;
        if (r112 > 0) goto L6;
        int r113 = r0.getPaddingTop();
        this.scrollOffsetY = r113;
        r0.setTopGlowOffset(r113);
        this.containerView.invalidate();
        return;
    L6:
        View r02 = r0.getChildAt(0);
        RecyclerListView.Holder r1 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r02);
        int r03 = r02.getTop();
        int r3 = AndroidUtilities.dp(7.0f);
        if (r03 < AndroidUtilities.dp(7.0f)) goto L12;
        if (r1 == null) goto L12;
        if (r1.getAdapterPosition() != 0) goto L12;
    L14:
        if (r03 > AndroidUtilities.dp(12.0f)) goto L16;
        boolean r12 = true;
    L17:
        if (r12 == true) goto L19;
    L20:
        if (r12 == false) goto L22;
    L38:
        FrameLayout.LayoutParams r114 = (FrameLayout.LayoutParams) this.listView.getLayoutParams();
        int r04 = r03 + (r114.topMargin - AndroidUtilities.dp(11.0f));
        if (this.scrollOffsetY == r04) goto L42;
        RecyclerListView r13 = this.listView;
        this.scrollOffsetY = r04;
        r13.setTopGlowOffset(r04 - r114.topMargin);
        this.containerView.invalidate();
        return;
    L42:
        return;
    L22:
        if (this.actionBar.getTag() == null) goto L38;
    L23:
        ActionBar r32 = this.actionBar;
        if (r12 == false) goto L26;
        Integer r5 = 1;
    L27:
        r32.setTag(r5);
        AnimatorSet r33 = this.actionBarAnimation;
        if (r33 == null) goto L30;
        r33.cancel();
        this.actionBarAnimation = null;
    L30:
        AnimatorSet r34 = new AnimatorSet();
        this.actionBarAnimation = r34;
        r34.setDuration(180);
        AnimatorSet r35 = this.actionBarAnimation;
        ActionBar r4 = this.actionBar;
        Property r52 = View.ALPHA;
        float r6 = 0.0f;
        if (r12 == false) goto L33;
        float r8 = 1.0f;
    L34:
        ObjectAnimator r42 = ObjectAnimator.ofFloat(r4, r52, new float[]{r8});
        View r82 = this.actionBarShadow;
        if (r12 == false) goto L37;
        r6 = 1.0f;
    L37:
        r35.playTogether(new Animator[]{r42, ObjectAnimator.ofFloat(r82, r52, new float[]{r6})});
        this.actionBarAnimation.addListener(new AnonymousClass9(this));
        this.actionBarAnimation.start();
        goto L38
    L33:
        r8 = 0.0f;
        goto L34
    L26:
        r5 = null;
        goto L27
    L19:
        if (this.actionBar.getTag() == null) goto L23;
    L16:
        r12 = false;
    L12:
        r03 = r3;
        goto L14
    }

    private void updatePlaceholder() {
        if (this.placeholderPaint != null) goto L5;
        return;
    L5:
        int r0 = Theme.getColor(Theme.key_dialogBackground);
        int r1 = Theme.getColor(Theme.key_dialogBackgroundGray);
        int r02 = AndroidUtilities.getAverageColor(r1, r0);
        this.placeholderPaint.setColor(r1);
        float r6 = AndroidUtilities.dp(500.0f);
        this.gradientWidth = r6;
        LinearGradient r3 = new LinearGradient(0.0f, 0.0f, r6, 0.0f, new int[]{r1, r02, r1}, new float[]{0.0f, 0.18f, 0.36f}, Shader.TileMode.REPEAT);
        this.placeholderGradient = r3;
        this.placeholderPaint.setShader(r3);
        Matrix r03 = new Matrix();
        this.placeholderMatrix = r03;
        this.placeholderGradient.setLocalMatrix(r03);
    }

    public void animateSectionUpdates(View r12) {
        int r1 = -2;
    L4:
        if (r1 >= this.listView.getChildCount()) goto L46;
        if (r1 != (-2)) goto L7;
        View r2 = r12;
    L12:
        if ((r2 instanceof SectionCell) == false) goto L45;
        if ((r2.getTag(R.id.object_tag) instanceof VotesList) == false) goto L45;
        SectionCell r4 = (SectionCell) r2;
        VotesList r22 = (VotesList) r2.getTag(R.id.object_tag);
        int r3 = this.poll.answers.size();
        int r5 = 0;
        int r6 = 0;
    L16:
        if (r6 >= r3) goto L45;
        TLRPC.PollAnswer r7 = this.poll.answers.get(r6);
        if (Arrays.equals(r7.option, r22.option) == false) goto L44;
        if (this.votesPercents.get(r22) == null) goto L44;
        TLRPC.TL_textWithEntities r32 = r7.text;
        MessageObject r62 = this.messageObject;
        if (r62 != null) goto L25;
    L36:
        if (r32 != null) goto L38;
        String r52 = _UrlKt.FRAGMENT_ENCODE_SET;
    L39:
        if (r32 != null) goto L42;
        ArrayList<TLRPC.MessageEntity> r33 = null;
    L43:
        r4.setText(r52, r33, calcPercent(r22.option), r22.count, r22.getCollapsed(), true);
        r4.setTag(R.id.object_tag, r22);
        goto L45
    L42:
        r33 = r32.entities;
        goto L43
    L38:
        r52 = r32.text;
        goto L39
    L25:
        if (r62.translated == false) goto L36;
        TLRPC.Message r63 = r62.messageOwner;
        if (r63 == null) goto L36;
        if (r63.translatedPoll == null) goto L36;
    L31:
        if (r5 >= this.messageObject.messageOwner.translatedPoll.answers.size()) goto L36;
        TLRPC.PollAnswer r64 = this.messageObject.messageOwner.translatedPoll.answers.get(r5);
        if (Arrays.equals(r64.option, r7.option) == true) goto L34;
        r5 = r5 + 1;
        goto L31
    L34:
        r32 = r64.text;
    L44:
        r6 = r6 + 1;
    L45:
        r1 = r1 + 1;
        goto L4
    L7:
        RecyclerListView r23 = this.listView;
        if (r1 != (-1)) goto L10;
        r2 = r23.getPinnedHeader();
        goto L12
    L10:
        r2 = r23.getChildAt(r1);
        goto L12
    L46:
        this.listView.relayoutPinnedHeader();
        this.listView.invalidate();
    }

    public int calcPercent(byte[] r7) {
        if (r7 != null) goto L5;
        return 0;
    L5:
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
    L7:
        if (r1 >= this.voters.size()) goto L14;
        VotesList r4 = this.voters.get(r1);
        if (r4 == null) goto L13;
        r2 = r2 + r4.count;
        if (Arrays.equals(r4.option, r7) == false) goto L13;
        r3 = r3 + r4.count;
    L13:
        r1 = r1 + 1;
        goto L7
    L14:
        TLRPC.TL_messageMediaPoll r6 = this.messageMediaPoll;
        if (r6.poll.multiple_choice == false) goto L17;
        r2 = r6.results.total_voters;
    L17:
        if (r2 > 0) goto L20;
        return 0;
    L20:
        return Math.round((r3 / r2) * 100.0f);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        int r0 = this.queries.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        getConnectionsManager().cancelRequest(this.queries.get(r1).intValue(), true);
        r1 = r1 + 1;
        goto L3
    L5:
        super.dismissInternal();
    }

    public ConnectionsManager getConnectionsManager() {
        return ConnectionsManager.getInstance(this.currentAccount);
    }

    public MessagesController getMessagesController() {
        return MessagesController.getInstance(this.currentAccount);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        PollVotesAlert$$ExternalSyntheticLambda2 r9 = new PollVotesAlert$$ExternalSyntheticLambda2(this);
        r1.add(new ThemeDescription(this.containerView, 0, null, null, null, null, Theme.key_sheet_scrollUp));
        ViewGroup r12 = this.containerView;
        Drawable[] r16 = {this.shadowDrawable};
        int r18 = Theme.key_dialogBackground;
        r1.add(new ThemeDescription(r12, 0, null, null, r16, null, r18));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r18));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_dialogScrollGlow));
        ActionBar r2 = this.actionBar;
        int r21 = ThemeDescription.FLAG_AB_ITEMSCOLOR;
        int r17 = Theme.key_dialogTextBlack;
        r1.add(new ThemeDescription(r2, r21, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBTITLECOLOR, null, null, null, null, Theme.key_player_actionBarSubtitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.titleTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.actionBarShadow, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_dialogShadowLine));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, null, null, null, r9, r18));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, null, null, null, r9, Theme.key_dialogBackgroundGray));
        int r31 = Theme.key_graySectionText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SECTIONS, new Class[]{SectionCell.class}, new String[]{"textView"}, null, null, null, r31));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SECTIONS, new Class[]{SectionCell.class}, new String[]{"middleTextView"}, null, null, null, r31));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SECTIONS, new Class[]{SectionCell.class}, new String[]{"righTextView"}, null, null, null, r31));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR | ThemeDescription.FLAG_SECTIONS, new Class[]{SectionCell.class}, null, null, null, Theme.key_graySection));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"nameTextView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueText4));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_switchTrackChecked));
        return r1;
    }
}
