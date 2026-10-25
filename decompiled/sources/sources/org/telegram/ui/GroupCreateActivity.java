package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.SearchAdapterHelper;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.GroupCreateSectionCell;
import org.telegram.ui.Cells.GroupCreateUserCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.FragmentSearchField;
import org.telegram.ui.Components.FragmentSpansContainer;
import org.telegram.ui.Components.GroupCreateSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PermanentLinkBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ShareAlert$$ExternalSyntheticLambda16;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.ViewGroupPartRenderer;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes3.dex */
public class GroupCreateActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate, FactorAnimator.Target, View.OnClickListener, WindowAnimatedInsetsProvider.Listener {
    private final int ADDITIONAL_LIST_HEIGHT_DP;
    private View actionBarBackgroundView;
    private GroupCreateAdapter adapter;
    private final boolean addToGroup;
    private ArrayList<GroupCreateSpan> allSpans;
    private final boolean allowMiniApps;
    private final boolean allowPremium;
    private final BoolAnimator animatorCallButtonsVisible;
    private final FactorAnimator animatorSelectorContainerHeight;
    private FrameLayout buttonsContainer;
    private final long channelId;
    private final int chatAddType;
    private final long chatId;
    private final int chatType;
    private GroupCreateSpan currentDeletingSpan;
    private String customTitle;
    private GroupCreateActivityDelegate delegate;
    private ContactsAddActivityDelegate delegate2;
    private boolean doneButtonVisible;
    private StickerEmptyView emptyView;
    private FragmentFloatingButton floatingButton;
    private final boolean forImport;
    private HeaderShadowView headerShadowView;
    private IBlur3Capture iBlur3Capture;
    private boolean iBlur3Invalidated;
    private final RectF iBlur3PositionActionBar;
    private final RectF iBlur3PositionBottomBar;
    private final ArrayList<RectF> iBlur3Positions;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlassFrosted;
    private LongSparseArray<TLObject> ignoreUsers;
    private int imeInsetAnimatedHeight;
    private TLRPC.ChatFull info;
    private final HashSet<Long> initialIds;
    private boolean initialMiniApps;
    private boolean initialPremium;
    private final boolean isAlwaysShare;
    private final boolean isCall;
    private final boolean isNeverShare;
    private LinearLayoutManager layoutManager;
    private RecyclerListView listView;
    private final int maxCount;
    int maxSize;
    private int navigationBarHeight;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    private FragmentSearchField searchField;
    private boolean searchWas;
    private boolean searching;
    private LongSparseArray<GroupCreateSpan> selectedContacts;
    private GroupCreateSpan selectedMiniApps;
    private GroupCreateSpan selectedPremium;
    private PermanentLinkBottomSheet sharedLinkBottomSheet;
    private int shiftDp;
    private boolean showDiscardConfirm;
    private FragmentSpansContainer spansContainer;
    private final Rect tmpClipRect;
    private ArrayList<Long> toSelectIds;
    private boolean toSelectMiniApps;
    private boolean toSelectPremium;

    /* renamed from: org.telegram.ui.GroupCreateActivity$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ GroupCreateActivity this$0;

        public AnonymousClass1(GroupCreateActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            GroupCreateActivity.m15391$$Nest$monDonePressed(this.this$0, true);
            return;
        L12:
            return;
        L5:
            if (GroupCreateActivity.m15381$$Nest$mcheckDiscard(this.this$0, true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateActivity$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ GroupCreateActivity this$0;

        public AnonymousClass2(GroupCreateActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            if (Build.VERSION.SDK_INT >= 31) goto L5;
        L16:
            super.dispatchDraw(r4);
            AndroidUtilities.drawNavigationBarProtection(r4, this, this.this$0.getThemedColor(Theme.key_windowBackgroundWhite), GroupCreateActivity.m15368$$Nest$fgetnavigationBarHeight(this.this$0));
            return;
        L5:
            if (GroupCreateActivity.m15369$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L16;
            GroupCreateActivity.m15380$$Nest$mblur3_InvalidateBlur(this.this$0);
            int r0 = getMeasuredWidth();
            int r1 = getMeasuredHeight();
            if (GroupCreateActivity.m15361$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) != null) goto L9;
        L15:
            GroupCreateActivity.m15375$$Nest$fputiBlur3Invalidated(this.this$0, false);
            goto L16
        L9:
            if (GroupCreateActivity.m15361$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).inRecording() == true) goto L15;
            if (GroupCreateActivity.m15361$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).needUpdateDisplayList(r0, r1) == false) goto L13;
        L14:
            RecordingCanvas r02 = GroupCreateActivity.m15361$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).beginRecording(r0, r1);
            GroupCreateActivity.m15369$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r02, -3);
            GroupCreateActivity.m15361$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).endRecording();
            goto L15
        L13:
            if (GroupCreateActivity.m15360$$Nest$fgetiBlur3Invalidated(this.this$0) == false) goto L15;
            goto L14
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            GroupCreateActivity.m15382$$Nest$mcheckUi_bottomButtons(this.this$0);
            GroupCreateActivity.m15383$$Nest$mcheckUi_floatingButton(this.this$0);
            GroupCreateActivity.m15387$$Nest$mcheckUi_searchFieldY(this.this$0);
            GroupCreateActivity.m15385$$Nest$mcheckUi_listClip(this.this$0);
            GroupCreateActivity.m15384$$Nest$mcheckUi_headerShadowY(this.this$0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            int r0 = View.MeasureSpec.getSize(r8);
            int r1 = View.MeasureSpec.getSize(r9);
            if (AndroidUtilities.isTablet() == true) goto L7;
            if (r1 > r0) goto L7;
            GroupCreateActivity r02 = this.this$0;
            r02.maxSize = AndroidUtilities.dp(56.0f);
        L8:
            measureChildWithMargins(GroupCreateActivity.access$000(this.this$0), r8, 0, r9, 0);
            ViewGroup.MarginLayoutParams r7 = (ViewGroup.MarginLayoutParams) GroupCreateActivity.m15358$$Nest$fgetemptyView(this.this$0).getLayoutParams();
            r7.topMargin = GroupCreateActivity.access$100(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(48.0f);
            ViewGroup.MarginLayoutParams r72 = (ViewGroup.MarginLayoutParams) GroupCreateActivity.m15359$$Nest$fgetheaderShadowView(this.this$0).getLayoutParams();
            r72.topMargin = GroupCreateActivity.access$200(this.this$0).getMeasuredHeight();
            ViewGroup.MarginLayoutParams r73 = (ViewGroup.MarginLayoutParams) GroupCreateActivity.m15370$$Nest$fgetsearchField(this.this$0).getLayoutParams();
            r73.topMargin = GroupCreateActivity.access$300(this.this$0).getMeasuredHeight();
            ViewGroup.MarginLayoutParams r74 = (ViewGroup.MarginLayoutParams) GroupCreateActivity.m15374$$Nest$fgetspansContainer(this.this$0).getLayoutParams();
            r74.topMargin = GroupCreateActivity.access$400(this.this$0).getMeasuredHeight();
            ViewGroup.LayoutParams r75 = GroupCreateActivity.m15374$$Nest$fgetspansContainer(this.this$0).getLayoutParams();
            GroupCreateActivity r82 = this.this$0;
            r75.height = r82.maxSize;
            ViewGroup.MarginLayoutParams r76 = (ViewGroup.MarginLayoutParams) GroupCreateActivity.m15349$$Nest$fgetactionBarBackgroundView(r82).getLayoutParams();
            int r83 = GroupCreateActivity.access$500(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(53.0f);
            GroupCreateActivity r92 = this.this$0;
            r76.height = r83 + r92.maxSize;
            GroupCreateActivity.m15386$$Nest$mcheckUi_listViewPadding(r92);
            super.onMeasure(r8, r9);
            return;
        L7:
            GroupCreateActivity r03 = this.this$0;
            r03.maxSize = AndroidUtilities.dp(144.0f);
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateActivity$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends FragmentSpansContainer {
        final /* synthetic */ GroupCreateActivity this$0;

        public AnonymousClass3(GroupCreateActivity r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.FragmentSpansContainer
        public void addSpan(GroupCreateSpan r1) {
            super.addSpan(r1);
            GroupCreateActivity.m15393$$Nest$mupdateButtonsVisibility(this.this$0);
        }

        @Override // org.telegram.ui.Components.FragmentSpansContainer
        public void removeAllSpans(boolean r1) {
            super.removeAllSpans(r1);
            GroupCreateActivity.m15393$$Nest$mupdateButtonsVisibility(this.this$0);
        }

        @Override // org.telegram.ui.Components.FragmentSpansContainer
        public void removeSpan(GroupCreateSpan r3) {
            if (r3 != GroupCreateActivity.m15373$$Nest$fgetselectedPremium(this.this$0)) goto L6;
            GroupCreateActivity.m15379$$Nest$fputselectedPremium(this.this$0, null);
        L6:
            if (r3 != GroupCreateActivity.m15372$$Nest$fgetselectedMiniApps(this.this$0)) goto L8;
            GroupCreateActivity.m15378$$Nest$fputselectedMiniApps(this.this$0, null);
        L8:
            super.removeSpan(r3);
            GroupCreateActivity.m15393$$Nest$mupdateButtonsVisibility(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateActivity$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 implements View.OnKeyListener {
        final /* synthetic */ GroupCreateActivity this$0;
        private boolean wasEmpty;

        public AnonymousClass4(GroupCreateActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View r2, int r3, KeyEvent r4) {
            if (r3 != 67) goto L19;
            boolean r32 = true;
            if (r4.getAction() != 0) goto L12;
            if (GroupCreateActivity.m15370$$Nest$fgetsearchField(this.this$0).editText.length() == 0) goto L10;
            r32 = false;
        L10:
            this.wasEmpty = r32;
            goto L19
        L12:
            if (r4.getAction() != 1) goto L19;
            if (this.wasEmpty == false) goto L19;
            if (GroupCreateActivity.m15352$$Nest$fgetallSpans(this.this$0).isEmpty() == true) goto L19;
            GroupCreateActivity.m15374$$Nest$fgetspansContainer(this.this$0).removeSpan((GroupCreateSpan) GroupCreateActivity.m15352$$Nest$fgetallSpans(this.this$0).get(GroupCreateActivity.m15352$$Nest$fgetallSpans(this.this$0).size() - 1));
            GroupCreateActivity.m15395$$Nest$mupdateHint(this.this$0);
            GroupCreateActivity.m15388$$Nest$mcheckVisibleRows(this.this$0);
            return true;
        L19:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateActivity$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 implements TextWatcher {
        final /* synthetic */ GroupCreateActivity this$0;

        public AnonymousClass5(GroupCreateActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r4) {
            int r42 = GroupCreateActivity.m15370$$Nest$fgetsearchField(this.this$0).editText.length();
            GroupCreateActivity r0 = this.this$0;
            if (r42 != 0) goto L5;
            GroupCreateActivity.m15389$$Nest$mcloseSearch(r0);
            return;
        L5:
            if (GroupCreateAdapter.m15403$$Nest$fgetsearching(GroupCreateActivity.m15350$$Nest$fgetadapter(r0)) == true) goto L7;
            GroupCreateActivity.m15377$$Nest$fputsearching(this.this$0, true);
            GroupCreateActivity.m15376$$Nest$fputsearchWas(this.this$0, true);
            GroupCreateActivity.m15350$$Nest$fgetadapter(this.this$0).setSearching(true);
            GroupCreateActivity.m15367$$Nest$fgetlistView(this.this$0).setFastScrollVisible(false);
            GroupCreateActivity.m15367$$Nest$fgetlistView(this.this$0).setVerticalScrollBarEnabled(true);
        L7:
            GroupCreateActivity.m15350$$Nest$fgetadapter(this.this$0).searchDialogs(GroupCreateActivity.m15370$$Nest$fgetsearchField(this.this$0).editText.getText().toString());
            GroupCreateActivity.m15358$$Nest$fgetemptyView(this.this$0).showProgress(true, false);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateActivity$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends RecyclerView.OnScrollListener {
        final /* synthetic */ GroupCreateActivity this$0;

        public AnonymousClass6(GroupCreateActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            GroupCreateActivity.m15370$$Nest$fgetsearchField(this.this$0).editText.hideActionMode();
            AndroidUtilities.hideKeyboard(GroupCreateActivity.m15370$$Nest$fgetsearchField(this.this$0).editText);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r4, int r5, int r6) {
            int r42 = GroupCreateActivity.m15366$$Nest$fgetlayoutManager(this.this$0).findFirstVisibleItemPosition();
            boolean r1 = false;
            View r0 = GroupCreateActivity.m15367$$Nest$fgetlistView(this.this$0).getChildAt(0);
            if (r0 == null) goto L5;
            int r02 = r0.getTop();
        L7:
            if (r42 == 0) goto L9;
        L10:
            r1 = true;
        L11:
            GroupCreateActivity.m15359$$Nest$fgetheaderShadowView(this.this$0).setShadowVisible(r1, true);
            if (Build.VERSION.SDK_INT >= 31) goto L14;
            return;
        L14:
            if (GroupCreateActivity.m15369$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L18;
            GroupCreateActivity.m15369$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r5, r6);
            GroupCreateActivity.m15380$$Nest$mblur3_InvalidateBlur(this.this$0);
            return;
        L18:
            return;
        L9:
            if (r02 >= GroupCreateActivity.m15367$$Nest$fgetlistView(this.this$0).getPaddingTop()) goto L11;
        L5:
            r02 = 0;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateActivity$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends FrameLayout {
        private final Paint paint;
        private final RectF rectTmp;
        final /* synthetic */ GroupCreateActivity this$0;

        public AnonymousClass7(GroupCreateActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rectTmp = new RectF();
            this.paint = new Paint(1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            this.paint.setColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            r5.save();
            r5.translate(0.0f, -getTop());
            this.rectTmp.set(0.0f, 0.0f, getWidth(), getHeight());
            this.rectTmp.offset(0.0f, getTop());
            GroupCreateActivity.m15390$$Nest$mdrawBlurRect(this.this$0, r5, this.rectTmp, this.paint);
            r5.restore();
            super.dispatchDraw(r5);
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateActivity$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 extends View {
        private final Paint paint;
        private final RectF rectTmp;
        final /* synthetic */ GroupCreateActivity this$0;

        public AnonymousClass8(GroupCreateActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rectTmp = new RectF();
            this.paint = new Paint(1);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r5) {
            super.dispatchDraw(r5);
            int r0 = AndroidUtilities.dp(48.0f) + ((int) GroupCreateActivity.m15355$$Nest$fgetanimatorSelectorContainerHeight(this.this$0).getFactor());
            this.paint.setColor(this.this$0.getThemedColor(Theme.key_actionBarDefault));
            this.rectTmp.set(0.0f, 0.0f, getMeasuredWidth(), GroupCreateActivity.access$600(this.this$0).getMeasuredHeight() + r0);
            GroupCreateActivity.m15390$$Nest$mdrawBlurRect(this.this$0, r5, this.rectTmp, this.paint);
        }
    }

    public static class Comparator implements java.util.Comparator<TLObject> {
        /* renamed from: -$$Nest$smgetName, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m15396$$Nest$smgetName(TLObject r0) {
            return getName(r0);
        }

        public Comparator() {
        }

        private static String getName(TLObject r1) {
            if ((r1 instanceof TLRPC.User) == false) goto L7;
            TLRPC.User r12 = (TLRPC.User) r1;
            return ContactsController.formatName(r12.first_name, r12.last_name);
        L7:
            if ((r1 instanceof TLRPC.Chat) == true) goto L9;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L9:
            return ((TLRPC.Chat) r1).title;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(TLObject r1, TLObject r2) {
            return compare2(r1, r2);
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(TLObject r1, TLObject r2) {
            return getName(r1).compareTo(getName(r2));
        }
    }

    public interface ContactsAddActivityDelegate {
        void didSelectUsers(ArrayList<TLRPC.User> r1, int r2);

        default void needAddBot(TLRPC.User r1) {
        }
    }

    public interface GroupCreateActivityDelegate {
        void didSelectUsers(boolean r1, boolean r2, ArrayList<Long> r3);
    }

    public class GroupCreateAdapter extends RecyclerListView.FastScrollAdapter {
        private final ArrayList<TLObject> contacts;
        private final Context context;
        private int createCallLinkRow;
        private int currentItemsCount;
        private int firstSectionRow;
        private int inviteViaLink;
        private int miniAppsRow;
        private int noContactsStubRow;
        private int premiumRow;
        private final SearchAdapterHelper searchAdapterHelper;
        private ArrayList<Object> searchResult;
        private ArrayList<CharSequence> searchResultNames;
        private Runnable searchRunnable;
        private boolean searching;
        final /* synthetic */ GroupCreateActivity this$0;
        private int userTypesHeaderRow;
        private int usersStartRow;

        /* renamed from: org.telegram.ui.GroupCreateActivity$GroupCreateAdapter$1, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass1 extends StickerEmptyView {
            final /* synthetic */ GroupCreateAdapter this$1;

            public AnonymousClass1(GroupCreateAdapter r1, Context r2, View r3, int r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Components.StickerEmptyView, android.view.ViewGroup, android.view.View
            public void onAttachedToWindow() {
                super.onAttachedToWindow();
                this.stickerView.getImageReceiver().startAnimation();
            }
        }

        public static /* synthetic */ void $r8$lambda$OIySeqS4ewPstYVlDemwkgnTaxk(GroupCreateAdapter r0, String r1) {
            r0.lambda$searchDialogs$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$Qo7b59WIAM7iHRoIMZMdUXwGSRg(GroupCreateAdapter r0, String r1) {
            r0.lambda$searchDialogs$3(r1);
        }

        /* renamed from: $r8$lambda$RH3IAntsBIY-51eLkygd5x5rY20, reason: not valid java name */
        public static /* synthetic */ void m15397$r8$lambda$RH3IAntsBIY51eLkygd5x5rY20(GroupCreateAdapter r0, String r1) {
            r0.lambda$searchDialogs$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$TSC6Gel0kDnRp5tYVozyIfmOTR0(GroupCreateAdapter r0, int r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$j6JNJrPLz8Niyd9O99j8ZgedbAk(GroupCreateAdapter r0, View r1) {
            r0.lambda$onBindViewHolder$1(r1);
        }

        /* renamed from: $r8$lambda$pjaTpKsIzaiIVjjBCCA6M-dkQsY, reason: not valid java name */
        public static /* synthetic */ void m15398$r8$lambda$pjaTpKsIzaiIVjjBCCA6MdkQsY(GroupCreateAdapter r0, ArrayList r1, ArrayList r2) {
            r0.lambda$updateSearchResults$5(r1, r2);
        }

        /* renamed from: -$$Nest$fgetcreateCallLinkRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15399$$Nest$fgetcreateCallLinkRow(GroupCreateAdapter r0) {
            return r0.createCallLinkRow;
        }

        /* renamed from: -$$Nest$fgetfirstSectionRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15400$$Nest$fgetfirstSectionRow(GroupCreateAdapter r0) {
            return r0.firstSectionRow;
        }

        /* renamed from: -$$Nest$fgetinviteViaLink, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15401$$Nest$fgetinviteViaLink(GroupCreateAdapter r0) {
            return r0.inviteViaLink;
        }

        /* renamed from: -$$Nest$fgetnoContactsStubRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15402$$Nest$fgetnoContactsStubRow(GroupCreateAdapter r0) {
            return r0.noContactsStubRow;
        }

        /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m15403$$Nest$fgetsearching(GroupCreateAdapter r0) {
            return r0.searching;
        }

        public GroupCreateAdapter(GroupCreateActivity r9, Context r10) {
            this.this$0 = r9;
            this.searchResult = new ArrayList();
            this.searchResultNames = new ArrayList();
            this.contacts = new ArrayList();
            this.context = r10;
            HashSet r102 = new HashSet();
            ArrayList<TLRPC.TL_contact> r0 = GroupCreateActivity.access$700(r9).contacts;
            int r2 = 0;
        L4:
            if (r2 >= r0.size()) goto L15;
            TLRPC.User r3 = r9.getMessagesController().getUser(Long.valueOf(r0.get(r2).user_id));
            if (r3 == null) goto L13;
            if (r3.self == true) goto L13;
            if (r3.deleted == true) goto L13;
            this.contacts.add(r3);
            r102.add(Long.valueOf(r3.id));
        L13:
            r2 = r2 + 1;
            goto L4
        L15:
            if (GroupCreateActivity.m15365$$Nest$fgetisNeverShare(r9) == false) goto L17;
        L20:
            ArrayList<TLRPC.Dialog> r02 = r9.getMessagesController().getAllDialogs();
            if (GroupCreateActivity.m15364$$Nest$fgetisCall(r9) == false) goto L44;
            int r22 = r02.size();
            int r32 = 0;
        L23:
            if (r32 >= r22) goto L60;
            TLRPC.Dialog r4 = r02.get(r32);
            if (DialogObject.isUserDialog(r4.id) == false) goto L43;
            if (r102.contains(Long.valueOf(r4.id)) == true) goto L43;
            TLRPC.User r5 = r9.getMessagesController().getUser(Long.valueOf(r4.id));
            if (r5 == null) goto L43;
            if (UserObject.isDeleted(r5) == true) goto L43;
            if (UserObject.isUserSelf(r5) == true) goto L43;
            if (UserObject.isBot(r5) == true) goto L43;
            if (UserObject.isService(r4.id) == true) goto L43;
            if (MessagesController.isSupportUser(r5) == true) goto L43;
            this.contacts.add(r5);
            r102.add(Long.valueOf(r5.id));
        L43:
            r32 = r32 + 1;
        L60:
            Collections.sort(this.contacts, new Comparator());
            TLObject r92 = null;
            int r103 = 0;
        L62:
            if (r103 >= this.contacts.size()) goto L69;
            TLObject r03 = this.contacts.get(r103);
            if (r92 != null) goto L66;
        L67:
            this.contacts.add(r103, new Letter(firstLetter(Comparator.m15396$$Nest$smgetName(r03))));
        L68:
            r103 = r103 + 1;
            r92 = r03;
            goto L62
        L66:
            if (firstLetter(Comparator.m15396$$Nest$smgetName(r92)).equals(firstLetter(Comparator.m15396$$Nest$smgetName(r03))) == true) goto L68;
        L69:
            SearchAdapterHelper r93 = new SearchAdapterHelper(false);
            this.searchAdapterHelper = r93;
            r93.setDelegate(new GroupCreateActivity$GroupCreateAdapter$$ExternalSyntheticLambda1(this));
            return;
        L44:
            int r104 = r02.size();
            int r23 = 0;
        L45:
            if (r23 >= r104) goto L60;
            TLRPC.Dialog r33 = r02.get(r23);
            if (DialogObject.isChatDialog(r33.id) == false) goto L59;
            TLRPC.Chat r34 = r9.getMessagesController().getChat(Long.valueOf(-r33.id));
            if (r34 == null) goto L59;
            if (r34.migrated_to != null) goto L59;
            if (ChatObject.isChannel(r34) == true) goto L56;
        L58:
            this.contacts.add(r34);
            goto L59
        L56:
            if (r34.megagroup == true) goto L58;
        L59:
            r23 = r23 + 1;
            goto L45
        L17:
            if (GroupCreateActivity.m15363$$Nest$fgetisAlwaysShare(r9) == true) goto L20;
            if (GroupCreateActivity.m15364$$Nest$fgetisCall(r9) == false) goto L69;
            goto L20
        }

        private String firstLetter(String r2) {
            if (TextUtils.isEmpty(r2) == false) goto L7;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            return r2.substring(0, 1);
        }

        private /* synthetic */ void lambda$new$0(int r3) {
            GroupCreateActivity.m15392$$Nest$mshowItemsAnimated(this.this$0, this.currentItemsCount);
            if (this.searchRunnable == null) goto L5;
        L9:
            notifyDataSetChanged();
            return;
        L5:
            if (this.searchAdapterHelper.isSearchInProgress() == true) goto L9;
            if (getItemCount() != 0) goto L9;
            GroupCreateActivity.m15358$$Nest$fgetemptyView(this.this$0).showProgress(false, true);
            goto L9
        }

        private /* synthetic */ void lambda$onBindViewHolder$1(View r2) {
            GroupCreateActivity.m15379$$Nest$fputselectedPremium(this.this$0, null);
            GroupCreateActivity.m15371$$Nest$fgetselectedContacts(this.this$0).clear();
            GroupCreateActivity.m15374$$Nest$fgetspansContainer(this.this$0).removeAllSpans(true);
            GroupCreateActivity.m15388$$Nest$mcheckVisibleRows(this.this$0);
            GroupCreateActivity.m15394$$Nest$mupdateEditTextHint(this.this$0);
        }

        private /* synthetic */ void lambda$searchDialogs$2(String r18) {
            String r1 = r18.trim().toLowerCase();
            if (r1.isEmpty() == false) goto L6;
            updateSearchResults(new ArrayList(), new ArrayList());
            return;
        L6:
            String r2 = LocaleController.getInstance().getTranslitString(r1);
            if (r1.equals(r2) == false) goto L9;
        L10:
            r2 = null;
        L11:
            int r3 = 0;
            boolean r5 = true;
            if (r2 == null) goto L14;
            int r6 = 1;
        L15:
            int r62 = r6 + 1;
            String[] r7 = new String[r62];
            r7[0] = r1;
            if (r2 == null) goto L18;
            r7[1] = r2;
        L18:
            ArrayList<Object> r12 = new ArrayList();
            ArrayList<CharSequence> r22 = new ArrayList();
            int r8 = 0;
        L20:
            if (r8 >= this.contacts.size()) goto L61;
            TLObject r9 = this.contacts.get(r8);
            boolean r10 = r9 instanceof TLRPC.User;
            if (r10 == false) goto L25;
            TLRPC.User r11 = (TLRPC.User) r9;
            String r122 = ContactsController.formatName(r11.first_name, r11.last_name).toLowerCase();
            String r112 = UserObject.getPublicUsername(r11);
        L27:
            String r13 = LocaleController.getInstance().getTranslitString(r122);
            if (r122.equals(r13) == false) goto L30;
            r13 = null;
        L30:
            int r14 = r3;
            int r15 = r14;
        L31:
            if (r14 >= r62) goto L59;
            String r32 = r7[r14];
            if (r122.startsWith(r32) == false) goto L35;
        L46:
            r15 = 1;
        L47:
            if (r15 != 0) goto L48;
            r14 = r14 + 1;
            r5 = true;
            goto L31
        L48:
            boolean r4 = true;
            if (r15 != 1) goto L56;
            if (r10 == false) goto L54;
            TLRPC.User r52 = (TLRPC.User) r9;
            r22.add(AndroidUtilities.generateSearchName(r52.first_name, r52.last_name, r32));
        L52:
            Object r102 = null;
        L57:
            r12.add(r9);
        L60:
            r8 = r8 + 1;
            r5 = r4;
            r3 = 0;
            goto L20
        L54:
            if ((r9 instanceof TLRPC.Chat) == false) goto L52;
            r22.add(AndroidUtilities.generateSearchName(((TLRPC.Chat) r9).title, null, r32));
            goto L52
        L56:
            r102 = null;
            r22.add(AndroidUtilities.generateSearchName("@" + r112, null, "@" + r32));
            goto L57
        L35:
            if (r122.contains(" " + r32) == true) goto L46;
            if (r13 != null) goto L38;
        L42:
            if (r112 == null) goto L47;
            if (r112.startsWith(r32) == false) goto L47;
            r15 = 2;
            goto L47
        L38:
            if (r13.startsWith(r32) == true) goto L46;
            if (r13.contains(" " + r32) == false) goto L42;
        L59:
            r4 = r5;
            goto L60
        L25:
            if ((r9 instanceof TLRPC.Chat) == false) goto L59;
            TLRPC.Chat r113 = (TLRPC.Chat) r9;
            r122 = r113.title;
            r112 = ChatObject.getPublicUsername(r113);
            goto L27
        L61:
            updateSearchResults(r12, r22);
            return;
        L14:
            r6 = 0;
            goto L15
        L9:
            if (r2.isEmpty() == false) goto L11;
            goto L10
        }

        private /* synthetic */ void lambda$searchDialogs$3(String r13) {
            SearchAdapterHelper r0 = this.searchAdapterHelper;
            if (GroupCreateActivity.m15363$$Nest$fgetisAlwaysShare(this.this$0) == false) goto L5;
        L9:
            boolean r1 = true;
        L10:
            r0.queryServerSearch(r13, true, r1, true, false, false, 0, false, 0, 0);
            DispatchQueue r132 = Utilities.searchQueue;
            GroupCreateActivity$GroupCreateAdapter$$ExternalSyntheticLambda4 r02 = new GroupCreateActivity$GroupCreateAdapter$$ExternalSyntheticLambda4(this, r13);
            this.searchRunnable = r02;
            r132.postRunnable(r02);
            return;
        L5:
            if (GroupCreateActivity.m15365$$Nest$fgetisNeverShare(this.this$0) == true) goto L9;
            r1 = false;
            goto L10
        }

        private /* synthetic */ void lambda$searchDialogs$4(String r2) {
            AndroidUtilities.runOnUIThread(new GroupCreateActivity$GroupCreateAdapter$$ExternalSyntheticLambda3(this, r2));
        }

        private /* synthetic */ void lambda$updateSearchResults$5(ArrayList r2, ArrayList r3) {
            if (this.searching == false) goto L16;
            this.searchRunnable = null;
            this.searchResult = r2;
            this.searchResultNames = r3;
            this.searchAdapterHelper.mergeResults(r2);
            GroupCreateActivity.m15392$$Nest$mshowItemsAnimated(this.this$0, this.currentItemsCount);
            notifyDataSetChanged();
            if (this.searching == true) goto L8;
            return;
        L8:
            if (this.searchAdapterHelper.isSearchInProgress() == false) goto L10;
            return;
        L10:
            if (getItemCount() != 0) goto L15;
            GroupCreateActivity.m15358$$Nest$fgetemptyView(this.this$0).showProgress(false, true);
            return;
        L15:
            return;
        }

        private void updateSearchResults(ArrayList<Object> r2, ArrayList<CharSequence> r3) {
            AndroidUtilities.runOnUIThread(new GroupCreateActivity$GroupCreateAdapter$$ExternalSyntheticLambda5(this, r2, r3));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            this.noContactsStubRow = -1;
            this.userTypesHeaderRow = -1;
            this.createCallLinkRow = -1;
            this.firstSectionRow = -1;
            this.premiumRow = -1;
            this.miniAppsRow = -1;
            if (this.searching == false) goto L10;
            int r0 = this.searchResult.size();
            int r2 = this.searchAdapterHelper.getLocalServerSearch().size();
            int r3 = this.searchAdapterHelper.getGlobalSearch().size();
            int r02 = r0 + r2;
            if (r3 == 0) goto L7;
            r02 = r02 + (r3 + 1);
        L7:
            this.currentItemsCount = r02;
            return r02;
        L10:
            if (GroupCreateActivity.m15364$$Nest$fgetisCall(this.this$0) == false) goto L12;
            this.createCallLinkRow = 0;
            int r03 = 1;
        L14:
            if (GroupCreateActivity.m15354$$Nest$fgetallowPremium(this.this$0) == false) goto L17;
            int r32 = r03 + 1;
            this.firstSectionRow = r03;
            this.userTypesHeaderRow = r03;
            r03 = r03 + 2;
            this.premiumRow = r32;
        L20:
            this.usersStartRow = r03;
            int r04 = r03 + this.contacts.size();
            if (GroupCreateActivity.m15351$$Nest$fgetaddToGroup(this.this$0) == false) goto L38;
            long r33 = GroupCreateActivity.m15357$$Nest$fgetchatId(this.this$0);
            GroupCreateActivity r4 = this.this$0;
            if (r33 == 0) goto L26;
            this.inviteViaLink = ChatObject.canUserDoAdminAction(r4.getMessagesController().getChat(Long.valueOf(GroupCreateActivity.m15357$$Nest$fgetchatId(this.this$0))), 3) ? 1 : 0;
        L36:
            if (this.inviteViaLink == 0) goto L38;
            this.usersStartRow++;
            r04 = r04 + 1;
            goto L38
        L26:
            if (GroupCreateActivity.m15356$$Nest$fgetchannelId(r4) == 0) goto L34;
            TLRPC.Chat r34 = this.this$0.getMessagesController().getChat(Long.valueOf(GroupCreateActivity.m15356$$Nest$fgetchannelId(this.this$0)));
            if (ChatObject.canUserDoAdminAction(r34, 3) == true) goto L30;
        L32:
            int r35 = 0;
        L33:
            this.inviteViaLink = r35;
            goto L36
        L30:
            if (ChatObject.isPublic(r34) == true) goto L32;
            r35 = 2;
            goto L33
        L34:
            this.inviteViaLink = 0;
        L38:
            if (r04 != 0) goto L40;
            this.noContactsStubRow = 0;
            r04 = r04 + 1;
        L40:
            this.currentItemsCount = r04;
            return r04;
        L17:
            if (GroupCreateActivity.m15353$$Nest$fgetallowMiniApps(this.this$0) == false) goto L19;
            int r36 = r03 + 1;
            this.firstSectionRow = r03;
            this.userTypesHeaderRow = r03;
            r03 = r03 + 2;
            this.miniAppsRow = r36;
            goto L20
        L19:
            this.firstSectionRow = r03;
            goto L20
        L12:
            r03 = 0;
            goto L14
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r5) {
            if (this.searching == false) goto L9;
            if (r5 != (this.searchResult.size() + this.searchAdapterHelper.getLocalServerSearch().size())) goto L7;
            return 0;
        L7:
            return 1;
        L9:
            if (r5 != this.createCallLinkRow) goto L12;
            return 2;
        L12:
            if (r5 != this.userTypesHeaderRow) goto L15;
            return 0;
        L15:
            if (r5 != this.premiumRow) goto L17;
        L34:
            return 1;
        L17:
            if (r5 == this.miniAppsRow) goto L34;
            if (this.inviteViaLink == 0) goto L24;
            if (r5 != 0) goto L24;
            return 2;
        L24:
            if (this.noContactsStubRow != r5) goto L27;
            return 3;
        L27:
            int r0 = this.usersStartRow;
            if ((r5 - r0) < 0) goto L34;
            if ((r5 - r0) >= this.contacts.size()) goto L34;
            if ((this.contacts.get(r5 - this.usersStartRow) instanceof Letter) == false) goto L34;
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public String getLetter(int r5) {
            if (this.searching == false) goto L5;
            return null;
        L5:
            if (r5 < this.usersStartRow) goto L38;
            int r0 = this.contacts.size();
            int r1 = this.usersStartRow;
            if (r5 >= (r0 + r1)) goto L39;
            TLObject r4 = this.contacts.get(r5 - r1);
            if ((r4 instanceof Letter) == false) goto L14;
            return ((Letter) r4).letter;
        L14:
            if ((r4 instanceof TLRPC.User) == false) goto L16;
            TLRPC.User r42 = (TLRPC.User) r4;
            String r52 = r42.first_name;
            String r43 = r42.last_name;
        L18:
            if (LocaleController.nameDisplayOrder != 1) goto L28;
            if (TextUtils.isEmpty(r52) == true) goto L24;
            return r52.substring(0, 1).toUpperCase();
        L24:
            if (TextUtils.isEmpty(r43) == false) goto L26;
        L35:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L26:
            return r43.substring(0, 1).toUpperCase();
        L28:
            if (TextUtils.isEmpty(r43) == true) goto L32;
            return r43.substring(0, 1).toUpperCase();
        L32:
            if (TextUtils.isEmpty(r52) == true) goto L35;
            return r52.substring(0, 1).toUpperCase();
        L16:
            r52 = ((TLRPC.Chat) r4).title;
            r43 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L18
        L39:
            return null;
        L38:
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void getPositionForScrollProgress(RecyclerListView r1, float r2, int[] r3) {
            r3[0] = (int) (getItemCount() * r2);
            r3[1] = 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r6) {
            if (r6.getItemViewType() != 0) goto L6;
            return false;
        L6:
            if (GroupCreateActivity.m15362$$Nest$fgetignoreUsers(this.this$0) == null) goto L15;
            View r62 = r6.itemView;
            if ((r62 instanceof GroupCreateUserCell) == false) goto L15;
            Object r63 = ((GroupCreateUserCell) r62).getObject();
            if ((r63 instanceof TLRPC.User) == false) goto L15;
            if (GroupCreateActivity.m15362$$Nest$fgetignoreUsers(this.this$0).indexOfKey(((TLRPC.User) r63).id) >= 0) goto L14;
            return true;
        L14:
            return false;
        L15:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            GroupCreateActivity.m15394$$Nest$mupdateEditTextHint(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r11, int r12) {
            int r0 = r11.getItemViewType();
            boolean r1 = true;
            if (r0 != 0) goto L5;
            GraySectionCell r112 = (GraySectionCell) r11.itemView;
            if (this.searching == false) goto L108;
            r112.setText(LocaleController.getString(R.string.GlobalSearch));
        L118:
            if (r12 == this.firstSectionRow) goto L120;
            return;
        L120:
            if (GroupCreateActivity.m15373$$Nest$fgetselectedPremium(this.this$0) == null) goto L122;
        L125:
            String r122 = LocaleController.getString(R.string.DeselectAll);
        L126:
            r112.setRightText(r122, true, new GroupCreateActivity$GroupCreateAdapter$$ExternalSyntheticLambda0(this));
            return;
        L122:
            if (GroupCreateActivity.m15371$$Nest$fgetselectedContacts(this.this$0).isEmpty() == false) goto L125;
            r122 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L126
        L108:
            if (r12 != this.userTypesHeaderRow) goto L110;
            r112.setText(LocaleController.getString(R.string.PrivacyUserTypes));
            goto L118
        L110:
            int r02 = this.usersStartRow;
            if ((r12 - r02) < 0) goto L118;
            if ((r12 - r02) >= this.contacts.size()) goto L118;
            TLObject r03 = this.contacts.get(r12 - this.usersStartRow);
            if ((r03 instanceof Letter) == false) goto L118;
            r112.setText(((Letter) r03).letter.toUpperCase());
            goto L118
        L5:
            if (r0 != 1) goto L7;
            GroupCreateUserCell r113 = (GroupCreateUserCell) r11.itemView;
            CharSequence r3 = null;
            if (this.searching == false) goto L69;
            int r04 = this.searchResult.size();
            int r4 = this.searchAdapterHelper.getGlobalSearch().size();
            int r5 = this.searchAdapterHelper.getLocalServerSearch().size();
            if (r12 < 0) goto L25;
            if (r12 >= r04) goto L25;
            TLObject r42 = (TLObject) this.searchResult.get(r12);
        L35:
            if (r42 != null) goto L37;
        L66:
            CharSequence r123 = null;
        L67:
            CharSequence r9 = r3;
            r3 = r123;
            CharSequence r124 = r9;
        L85:
            r113.setObject(r42, r3, r124);
            if ((r42 instanceof TLRPC.User) == false) goto L89;
            long r32 = ((TLRPC.User) r42).id;
        L93:
            if (r32 != 0) goto L95;
            return;
        L95:
            if (GroupCreateActivity.m15362$$Nest$fgetignoreUsers(this.this$0) == null) goto L100;
            if (GroupCreateActivity.m15362$$Nest$fgetignoreUsers(this.this$0).indexOfKey(r32) < 0) goto L100;
            r113.setChecked(true, false);
            r113.setCheckBoxEnabled(false);
            return;
        L100:
            if (GroupCreateActivity.m15371$$Nest$fgetselectedContacts(this.this$0).indexOfKey(r32) < 0) goto L102;
            boolean r10 = true;
        L103:
            r113.setChecked(r10, false);
            r113.setCheckBoxEnabled(true);
            return;
        L102:
            r10 = false;
            goto L103
        L89:
            if ((r42 instanceof TLRPC.Chat) == false) goto L91;
            r32 = -((TLRPC.Chat) r42).id;
            goto L93
        L91:
            r32 = 0;
            goto L93
        L37:
            if ((r42 instanceof TLRPC.User) == false) goto L40;
            String r52 = ((TLRPC.User) r42).username;
        L43:
            if (r12 >= r04) goto L51;
            r123 = this.searchResultNames.get(r12);
            if (r123 == null) goto L67;
            if (TextUtils.isEmpty(r52) == true) goto L67;
            if (r123.toString().startsWith("@" + r52) == false) goto L67;
            r3 = r123;
            r123 = null;
            goto L67
        L51:
            if (r12 <= r04) goto L66;
            if (TextUtils.isEmpty(r52) == true) goto L66;
            String r125 = this.searchAdapterHelper.getLastFoundUsername();
            if (r125.startsWith("@") == false) goto L128;
            r125 = r125.substring(1);
        L128:
            SpannableStringBuilder r05 = new SpannableStringBuilder();     // Catch: Exception -> L65
            r05.append("@");     // Catch: Exception -> L65
            r05.append(r52);     // Catch: Exception -> L65
            int r6 = AndroidUtilities.indexOfIgnoreCase(r52, r125);     // Catch: Exception -> L65
            if (r6 == (-1)) goto L64;
            int r126 = r125.length();     // Catch: Exception -> L65
            if (r6 != 0) goto L62;
            r126 = r126 + 1;     // Catch: Exception -> L65
        L63:
            r05.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4)), r6, r126 + r6, 33);     // Catch: Exception -> L65
            goto L64
        L62:
            r6 = r6 + 1;     // Catch: Exception -> L65
        L64:
            r123 = null;
            r3 = r05;
        L65:
            r123 = null;
            r3 = r52;
            goto L67
        L40:
            if ((r42 instanceof TLRPC.Chat) == false) goto L130;
            r52 = ChatObject.getPublicUsername((TLRPC.Chat) r42);
            goto L43
        L130:
            return;
        L25:
            if (r12 < r04) goto L30;
            if (r12 >= (r5 + r04)) goto L30;
            r42 = this.searchAdapterHelper.getLocalServerSearch().get(r12 - r04);
        L30:
            if (r12 > (r04 + r5)) goto L32;
        L34:
            r42 = null;
            goto L35
        L32:
            if (r12 > ((r4 + r04) + r5)) goto L34;
            r42 = this.searchAdapterHelper.getGlobalSearch().get(((r12 - r04) - r5) - 1);
            goto L35
        L69:
            if (r12 != this.premiumRow) goto L77;
            r113.setPremium();
            if (GroupCreateActivity.m15373$$Nest$fgetselectedPremium(this.this$0) != null) goto L74;
            r1 = false;
        L74:
            r113.setChecked(r1, false);
            return;
        L77:
            if (r12 != this.miniAppsRow) goto L84;
            r113.setMiniapps();
            if (GroupCreateActivity.m15372$$Nest$fgetselectedMiniApps(this.this$0) != null) goto L82;
            r1 = false;
        L82:
            r113.setChecked(r1, false);
            return;
        L84:
            r42 = this.contacts.get(r12 - this.usersStartRow);
            r124 = null;
            goto L85
        L7:
            if (r0 != 2) goto L135;
            TextCell r114 = (TextCell) r11.itemView;
            if (r12 != this.createCallLinkRow) goto L14;
            r114.setTextAndIcon(LocaleController.getString(R.string.GroupCallCreateLink), R.drawable.menu_link_create2, false);
            r114.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            return;
        L14:
            if (this.inviteViaLink != 2) goto L17;
            r114.setTextAndIcon(LocaleController.getString(R.string.ChannelInviteViaLink), R.drawable.msg_link2, false);
            r114.setColors(Theme.key_windowBackgroundWhiteGrayIcon, Theme.key_windowBackgroundWhiteBlackText);
            return;
        L17:
            r114.setTextAndIcon(LocaleController.getString(R.string.InviteToGroupByLink), R.drawable.msg_link2, false);
            r114.setColors(Theme.key_windowBackgroundWhiteGrayIcon, Theme.key_windowBackgroundWhiteBlackText);
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v3, types: [android.view.View, org.telegram.ui.Components.StickerEmptyView, org.telegram.ui.GroupCreateActivity$GroupCreateAdapter$1] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r4, int r5) {
            if (r5 != 0) goto L4;
            View r42 = new GraySectionCell(this.context);
        L13:
            return new RecyclerListView.Holder(r42);
        L4:
            if (r5 != 1) goto L6;
            GroupCreateUserCell r52 = new GroupCreateUserCell(this.context, 1, 0, false);
        L9:
            r42 = r52;
            goto L13
        L6:
            if (r5 == 3) goto L8;
            r42 = new TextCell(this.context);
            goto L13
        L8:
            ?? r53 = new AnonymousClass1(this, this.context, null, 0);
            r53.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            r53.subtitle.setVisibility(8);
            r53.title.setText(LocaleController.getString(R.string.NoContacts));
            r53.setAnimateLayoutChange(true);
            r52 = r53;
            goto L9
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r1) {
            View r0 = r1.itemView;
            if ((r0 instanceof GroupCreateUserCell) == false) goto L6;
            ((GroupCreateUserCell) r0).recycle();
            return;
        }

        public void searchDialogs(String r15) {
            if (this.searchRunnable == null) goto L5;
            Utilities.searchQueue.cancelRunnable(this.searchRunnable);
            this.searchRunnable = null;
        L5:
            this.searchResult.clear();
            this.searchResultNames.clear();
            this.searchAdapterHelper.mergeResults(null);
            SearchAdapterHelper r2 = this.searchAdapterHelper;
            if (GroupCreateActivity.m15363$$Nest$fgetisAlwaysShare(this.this$0) == false) goto L8;
        L12:
            boolean r0 = true;
        L13:
            r2.queryServerSearch(null, true, r0, false, false, false, 0, false, 0, 0);
            notifyDataSetChanged();
            if (TextUtils.isEmpty(r15) == true) goto L17;
            DispatchQueue r02 = Utilities.searchQueue;
            GroupCreateActivity$GroupCreateAdapter$$ExternalSyntheticLambda2 r1 = new GroupCreateActivity$GroupCreateAdapter$$ExternalSyntheticLambda2(this, r15);
            this.searchRunnable = r1;
            r02.postRunnable(r1, 300);
            return;
        L17:
            return;
        L8:
            if (GroupCreateActivity.m15365$$Nest$fgetisNeverShare(this.this$0) == true) goto L12;
            r0 = false;
            goto L13
        }

        public void setSearching(boolean r2) {
            if (this.searching != r2) goto L5;
            return;
        L5:
            this.searching = r2;
            notifyDataSetChanged();
        }
    }

    public static class Letter extends TLRPC.TL_contact {
        public final String letter;

        public Letter(String r1) {
            this.letter = r1;
        }
    }

    public static /* synthetic */ void $r8$lambda$0_gYlnn6E61UbIcTa0e3rs1Acqs(GroupCreateActivity r0) {
        r0.lambda$createView$8();
    }

    public static /* synthetic */ void $r8$lambda$3jMl2PxTQmFF2sRcGtQqN4rNVWQ(GroupCreateActivity r0) {
        r0.lambda$getThemeDescriptions$17();
    }

    /* renamed from: $r8$lambda$5KA7FogSoPbgBrZ36xCI-ZrCWoM, reason: not valid java name */
    public static /* synthetic */ void m15346$r8$lambda$5KA7FogSoPbgBrZ36xCIZrCWoM(GroupCreateActivity r0) {
        r0.lambda$createView$9();
    }

    public static /* synthetic */ void $r8$lambda$70RpEkbvpIavIudvQ6z50ktBaoM(GroupCreateActivity r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$9CvpnHxRXj1QiNSRJKJq1LjAHlI(GroupCreateActivity r0) {
        r0.lambda$showPremiumBlockedToast$10();
    }

    /* renamed from: $r8$lambda$BN58Os9IFjZuZelW8kEY-FrTllA, reason: not valid java name */
    public static /* synthetic */ WindowInsetsCompat m15347$r8$lambda$BN58Os9IFjZuZelW8kEYFrTllA(GroupCreateActivity r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Hm7eKzClbRHgx4igRm8LotoOtSw(GroupCreateActivity r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$KojZvEEtWgsV8OgsrannkZl5DQw(GroupCreateActivity r0, Context r1, View r2, int r3) {
        r0.lambda$createView$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$SCJpyB1ZSGcSw5Z6UsVzCdmeGFU(GroupCreateActivity r0, int r1) {
        r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$UAtxiZsfWO5oYcY7GicX5jANl48(GroupCreateActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZNaQ_aCV0yxfoDRBhQOoYsJl1K0(CheckBoxCell[] r1, View r2) {
        r1[0].setChecked(!r1.isChecked(), true);
    }

    public static /* synthetic */ void $r8$lambda$ejXKn0dFRYpJInwz7pgNtF1HRx0(GroupCreateActivity r0, View r1) {
        r0.lambda$checkVisibleRows$12(r1);
    }

    /* renamed from: $r8$lambda$fFAVRKtcz7S6h-n_hKnY-Le_ovQ, reason: not valid java name */
    public static /* synthetic */ void m15348$r8$lambda$fFAVRKtcz7S6hn_hKnYLe_ovQ(GroupCreateActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$fWaArwWli2KZdOpETF8jnPJfLM8(GroupCreateActivity r0, int r1) {
        r0.lambda$showItemsAnimated$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$mo_qcj3SX4q5v7FM8cVF4ZzDzGo(GroupCreateActivity r0, CheckBoxCell[] r1, AlertDialog r2, int r3) {
        r0.lambda$onDonePressed$16(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$n_Yo_YwuU7i4DioL2tTU_oMJYDM(GroupCreateActivity r0, View r1) {
        r0.lambda$createView$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$pa9Bra2aViS_qAWJDydPupWcNkY(GroupCreateActivity r0, View r1) {
        r0.lambda$createView$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$qE4sZqSIGEUndnX4Cfv38_i_Pbg(GroupCreateActivity r0, TLRPC.User r1, AlertDialog r2, int r3) {
        r0.lambda$createView$3(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$qgvdef1pmFLA70r_0QaGUyYCxYk(GroupCreateActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$2(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetactionBarBackgroundView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m15349$$Nest$fgetactionBarBackgroundView(GroupCreateActivity r0) {
        return r0.actionBarBackgroundView;
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCreateAdapter m15350$$Nest$fgetadapter(GroupCreateActivity r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetaddToGroup, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15351$$Nest$fgetaddToGroup(GroupCreateActivity r0) {
        return r0.addToGroup;
    }

    /* renamed from: -$$Nest$fgetallSpans, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15352$$Nest$fgetallSpans(GroupCreateActivity r0) {
        return r0.allSpans;
    }

    /* renamed from: -$$Nest$fgetallowMiniApps, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15353$$Nest$fgetallowMiniApps(GroupCreateActivity r0) {
        return r0.allowMiniApps;
    }

    /* renamed from: -$$Nest$fgetallowPremium, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15354$$Nest$fgetallowPremium(GroupCreateActivity r0) {
        return r0.allowPremium;
    }

    /* renamed from: -$$Nest$fgetanimatorSelectorContainerHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FactorAnimator m15355$$Nest$fgetanimatorSelectorContainerHeight(GroupCreateActivity r0) {
        return r0.animatorSelectorContainerHeight;
    }

    /* renamed from: -$$Nest$fgetchannelId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m15356$$Nest$fgetchannelId(GroupCreateActivity r2) {
        return r2.channelId;
    }

    /* renamed from: -$$Nest$fgetchatId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m15357$$Nest$fgetchatId(GroupCreateActivity r2) {
        return r2.chatId;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickerEmptyView m15358$$Nest$fgetemptyView(GroupCreateActivity r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetheaderShadowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HeaderShadowView m15359$$Nest$fgetheaderShadowView(GroupCreateActivity r0) {
        return r0.headerShadowView;
    }

    /* renamed from: -$$Nest$fgetiBlur3Invalidated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15360$$Nest$fgetiBlur3Invalidated(GroupCreateActivity r0) {
        return r0.iBlur3Invalidated;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlassFrosted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m15361$$Nest$fgetiBlur3SourceGlassFrosted(GroupCreateActivity r0) {
        return r0.iBlur3SourceGlassFrosted;
    }

    /* renamed from: -$$Nest$fgetignoreUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m15362$$Nest$fgetignoreUsers(GroupCreateActivity r0) {
        return r0.ignoreUsers;
    }

    /* renamed from: -$$Nest$fgetisAlwaysShare, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15363$$Nest$fgetisAlwaysShare(GroupCreateActivity r0) {
        return r0.isAlwaysShare;
    }

    /* renamed from: -$$Nest$fgetisCall, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15364$$Nest$fgetisCall(GroupCreateActivity r0) {
        return r0.isCall;
    }

    /* renamed from: -$$Nest$fgetisNeverShare, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15365$$Nest$fgetisNeverShare(GroupCreateActivity r0) {
        return r0.isNeverShare;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m15366$$Nest$fgetlayoutManager(GroupCreateActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m15367$$Nest$fgetlistView(GroupCreateActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetnavigationBarHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15368$$Nest$fgetnavigationBarHeight(GroupCreateActivity r0) {
        return r0.navigationBarHeight;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m15369$$Nest$fgetscrollableViewNoiseSuppressor(GroupCreateActivity r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fgetsearchField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentSearchField m15370$$Nest$fgetsearchField(GroupCreateActivity r0) {
        return r0.searchField;
    }

    /* renamed from: -$$Nest$fgetselectedContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m15371$$Nest$fgetselectedContacts(GroupCreateActivity r0) {
        return r0.selectedContacts;
    }

    /* renamed from: -$$Nest$fgetselectedMiniApps, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCreateSpan m15372$$Nest$fgetselectedMiniApps(GroupCreateActivity r0) {
        return r0.selectedMiniApps;
    }

    /* renamed from: -$$Nest$fgetselectedPremium, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCreateSpan m15373$$Nest$fgetselectedPremium(GroupCreateActivity r0) {
        return r0.selectedPremium;
    }

    /* renamed from: -$$Nest$fgetspansContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentSpansContainer m15374$$Nest$fgetspansContainer(GroupCreateActivity r0) {
        return r0.spansContainer;
    }

    /* renamed from: -$$Nest$fputiBlur3Invalidated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15375$$Nest$fputiBlur3Invalidated(GroupCreateActivity r0, boolean r1) {
        r0.iBlur3Invalidated = r1;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15376$$Nest$fputsearchWas(GroupCreateActivity r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15377$$Nest$fputsearching(GroupCreateActivity r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$fputselectedMiniApps, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15378$$Nest$fputselectedMiniApps(GroupCreateActivity r0, GroupCreateSpan r1) {
        r0.selectedMiniApps = r1;
    }

    /* renamed from: -$$Nest$fputselectedPremium, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15379$$Nest$fputselectedPremium(GroupCreateActivity r0, GroupCreateSpan r1) {
        r0.selectedPremium = r1;
    }

    /* renamed from: -$$Nest$mblur3_InvalidateBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15380$$Nest$mblur3_InvalidateBlur(GroupCreateActivity r0) {
        r0.blur3_InvalidateBlur();
    }

    /* renamed from: -$$Nest$mcheckDiscard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15381$$Nest$mcheckDiscard(GroupCreateActivity r0, boolean r1) {
        return r0.checkDiscard(r1);
    }

    /* renamed from: -$$Nest$mcheckUi_bottomButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15382$$Nest$mcheckUi_bottomButtons(GroupCreateActivity r0) {
        r0.checkUi_bottomButtons();
    }

    /* renamed from: -$$Nest$mcheckUi_floatingButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15383$$Nest$mcheckUi_floatingButton(GroupCreateActivity r0) {
        r0.checkUi_floatingButton();
    }

    /* renamed from: -$$Nest$mcheckUi_headerShadowY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15384$$Nest$mcheckUi_headerShadowY(GroupCreateActivity r0) {
        r0.checkUi_headerShadowY();
    }

    /* renamed from: -$$Nest$mcheckUi_listClip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15385$$Nest$mcheckUi_listClip(GroupCreateActivity r0) {
        r0.checkUi_listClip();
    }

    /* renamed from: -$$Nest$mcheckUi_listViewPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15386$$Nest$mcheckUi_listViewPadding(GroupCreateActivity r0) {
        r0.checkUi_listViewPadding();
    }

    /* renamed from: -$$Nest$mcheckUi_searchFieldY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15387$$Nest$mcheckUi_searchFieldY(GroupCreateActivity r0) {
        r0.checkUi_searchFieldY();
    }

    /* renamed from: -$$Nest$mcheckVisibleRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15388$$Nest$mcheckVisibleRows(GroupCreateActivity r0) {
        r0.checkVisibleRows();
    }

    /* renamed from: -$$Nest$mcloseSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15389$$Nest$mcloseSearch(GroupCreateActivity r0) {
        r0.closeSearch();
    }

    /* renamed from: -$$Nest$mdrawBlurRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15390$$Nest$mdrawBlurRect(GroupCreateActivity r0, Canvas r1, RectF r2, Paint r3) {
        r0.drawBlurRect(r1, r2, r3);
    }

    /* renamed from: -$$Nest$monDonePressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15391$$Nest$monDonePressed(GroupCreateActivity r0, boolean r1) {
        return r0.onDonePressed(r1);
    }

    /* renamed from: -$$Nest$mshowItemsAnimated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15392$$Nest$mshowItemsAnimated(GroupCreateActivity r0, int r1) {
        r0.showItemsAnimated(r1);
    }

    /* renamed from: -$$Nest$mupdateButtonsVisibility, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15393$$Nest$mupdateButtonsVisibility(GroupCreateActivity r0) {
        r0.updateButtonsVisibility();
    }

    /* renamed from: -$$Nest$mupdateEditTextHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15394$$Nest$mupdateEditTextHint(GroupCreateActivity r0) {
        r0.updateEditTextHint();
    }

    /* renamed from: -$$Nest$mupdateHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15395$$Nest$mupdateHint(GroupCreateActivity r0) {
        r0.updateHint();
    }

    public GroupCreateActivity(Bundle r12) {
        super(r12);
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 31) goto L5;
        int r3 = 48;
    L6:
        this.ADDITIONAL_LIST_HEIGHT_DP = r3;
        CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorSelectorContainerHeight = new FactorAnimator(3, this, r7, 350);
        this.animatorCallButtonsVisible = new BoolAnimator(4, this, r7, 350);
        this.selectedContacts = new LongSparseArray();
        this.allSpans = new ArrayList();
        this.initialIds = new HashSet();
        this.shiftDp = -4;
        this.tmpClipRect = new Rect();
        ArrayList<RectF> r11 = new ArrayList(2);
        this.iBlur3Positions = r11;
        RectF r32 = new RectF();
        this.iBlur3PositionActionBar = r32;
        RectF r4 = new RectF();
        this.iBlur3PositionBottomBar = r4;
        r11.add(r32);
        r11.add(r4);
        int r112 = r12.getInt("chatType", 0);
        this.chatType = r112;
        this.forImport = r12.getBoolean("forImport", false);
        boolean r33 = r12.getBoolean("isAlwaysShare", false);
        this.isAlwaysShare = r33;
        boolean r42 = r12.getBoolean("isNeverShare", false);
        this.isNeverShare = r42;
        boolean r5 = r12.getBoolean("isCall", false);
        this.isCall = r5;
        boolean r72 = r12.getBoolean("addToGroup", false);
        this.addToGroup = r72;
        this.chatAddType = r12.getInt("chatAddType", 0);
        this.allowPremium = r12.getBoolean("allowPremium", false);
        this.allowMiniApps = r12.getBoolean("allowMiniapps", false);
        this.chatId = r12.getLong("chatId");
        this.channelId = r12.getLong("channelId");
        if (r33 == true) goto L17;
        if (r42 == true) goto L17;
        if (r72 == true) goto L17;
        if (r5 == false) goto L13;
        this.maxCount = getMessagesController().conferenceCallSizeLimit - 1;
    L19:
        if (r0 < 31) goto L22;
        this.scrollableViewNoiseSuppressor = new DownscaleScrollableNoiseSuppressor();
        this.iBlur3SourceGlassFrosted = new BlurredBackgroundSourceRenderNode(null);
        return;
    L22:
        this.scrollableViewNoiseSuppressor = null;
        this.iBlur3SourceGlassFrosted = null;
        return;
    L13:
        if (r112 != 0) goto L15;
        int r113 = getMessagesController().maxMegagroupCount;
    L16:
        this.maxCount = r113;
        goto L19
    L15:
        r113 = getMessagesController().maxBroadcastCount;
    L17:
        this.maxCount = 0;
        goto L19
    L5:
        r3 = 0;
        goto L6
    }

    public static /* synthetic */ ActionBar access$000(GroupCreateActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(GroupCreateActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$200(GroupCreateActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(GroupCreateActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$400(GroupCreateActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$500(GroupCreateActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$600(GroupCreateActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ContactsController access$700(GroupCreateActivity r0) {
        return r0.getContactsController();
    }

    private void blur3_InvalidateBlur() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L19;
        int r1 = AndroidUtilities.dp(48.0f);
        int r0 = AndroidUtilities.dp(48.0f) + this.maxSize;
        this.iBlur3PositionActionBar.set(0.0f, 0.0f, this.fragmentView.getMeasuredWidth(), this.actionBar.getMeasuredHeight() + r0);
        float r12 = -r1;
        this.iBlur3PositionActionBar.inset(0.0f, r12);
        if (this.buttonsContainer == null) goto L10;
        this.iBlur3PositionBottomBar.set(0.0f, this.fragmentView.getMeasuredHeight() - this.buttonsContainer.getMeasuredHeight(), this.fragmentView.getMeasuredWidth(), this.fragmentView.getMeasuredHeight());
        this.iBlur3PositionBottomBar.inset(0.0f, r12);
    L10:
        DownscaleScrollableNoiseSuppressor r02 = this.scrollableViewNoiseSuppressor;
        ArrayList<RectF> r13 = this.iBlur3Positions;
        if (this.buttonsContainer != null) goto L13;
    L15:
        int r2 = 1;
    L16:
        r02.setupRenderNodes(r13, r2);
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.iBlur3Capture, this.fragmentView.getMeasuredWidth(), this.fragmentView.getMeasuredHeight());
        return;
    L13:
        if (this.animatorCallButtonsVisible.getFloatValue() <= 0.0f) goto L15;
        r2 = 2;
        goto L16
    }

    private boolean checkDiscard(boolean r7) {
        if (this.showDiscardConfirm == true) goto L5;
        return true;
    L5:
        HashSet r0 = new HashSet();
        int r3 = 0;
    L7:
        if (r3 >= this.selectedContacts.size()) goto L9;
        r0.add(Long.valueOf(this.selectedContacts.keyAt(r3)));
        r3 = r3 + 1;
        goto L7
    L9:
        boolean r32 = this.initialPremium;
        if (this.selectedPremium != null) goto L12;
        boolean r4 = true;
    L13:
        if (r32 == r4) goto L23;
        boolean r33 = this.initialMiniApps;
        if (this.selectedMiniApps != null) goto L17;
        boolean r42 = true;
    L18:
        if (r33 == r42) goto L23;
        if (r0.size() != this.initialIds.size()) goto L23;
        boolean r34 = false;
    L24:
        if (r34 == true) goto L31;
        Iterator r02 = r0.iterator();
    L27:
        if (r02.hasNext() == false) goto L31;
        Long r43 = (Long) r02.next();
        r43.longValue();
        if (this.initialIds.contains(r43) == true) goto L27;
        r34 = true;
    L31:
        if (r34 == false) goto L35;
        if (r7 == false) goto L34;
        AlertDialog.Builder r72 = new AlertDialog.Builder(getParentActivity());
        r72.setTitle(LocaleController.getString(R.string.UserRestrictionsApplyChanges));
        r72.setMessage(LocaleController.getString(R.string.PrivacySettingsChangedAlert));
        r72.setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new GroupCreateActivity$$ExternalSyntheticLambda10(this));
        r72.setNegativeButton(LocaleController.getString(R.string.PassportDiscard), new GroupCreateActivity$$ExternalSyntheticLambda11(this));
        showDialog(r72.create());
    L34:
        return false;
    L35:
        return true;
    L17:
        r42 = false;
    L23:
        r34 = true;
        goto L24
    L12:
        r4 = false;
        goto L13
    }

    private void checkUi_bottomButtons() {
        if (this.buttonsContainer != null) goto L5;
        return;
    L5:
        float r0 = this.animatorCallButtonsVisible.getFloatValue();
        this.buttonsContainer.setTranslationY(AndroidUtilities.dp(12.0f) * (1.0f - r0));
        this.buttonsContainer.setAlpha(r0);
        FrameLayout r4 = this.buttonsContainer;
        if (r0 <= 0.0f) goto L8;
        int r02 = 0;
    L9:
        r4.setVisibility(r02);
        return;
    L8:
        r02 = 8;
        goto L9
    }

    private void checkUi_floatingButton() {
        FragmentFloatingButton r0 = this.floatingButton;
        if (r0 == null) goto L6;
        r0.setTranslationY(-Math.max(this.navigationBarHeight, this.imeInsetAnimatedHeight));
        return;
    }

    private void checkUi_headerShadowY() {
        this.headerShadowView.setTranslationY(AndroidUtilities.dp(48.0f) + this.animatorSelectorContainerHeight.getFactor());
    }

    private void checkUi_listClip() {
        if (this.listView.hasActiveEdgeEffects() == false) goto L6;
        this.listView.setClipBounds(null);
        return;
    L6:
        this.tmpClipRect.set(0, (AndroidUtilities.dp(this.ADDITIONAL_LIST_HEIGHT_DP + 48) + this.actionBar.getMeasuredHeight()) + ((int) this.animatorSelectorContainerHeight.getFactor()), this.listView.getMeasuredWidth(), (this.listView.getMeasuredHeight() - AndroidUtilities.dp(this.ADDITIONAL_LIST_HEIGHT_DP)) - ((int) ((this.navigationBarHeight + AndroidUtilities.dp(76.0f)) * this.animatorCallButtonsVisible.getFloatValue())));
        this.listView.setClipBounds(this.tmpClipRect);
    }

    private void checkUi_listViewPadding() {
        if (this.isCall == false) goto L5;
        int r0 = AndroidUtilities.dp(76.0f);
    L6:
        this.listView.setPadding(0, (AndroidUtilities.dp(this.ADDITIONAL_LIST_HEIGHT_DP + 48) + this.actionBar.getMeasuredHeight()) + ((int) this.animatorSelectorContainerHeight.getFactor()), 0, (AndroidUtilities.dp(this.ADDITIONAL_LIST_HEIGHT_DP) + this.navigationBarHeight) + r0);
        this.emptyView.setPadding(0, 0, 0, this.navigationBarHeight);
        return;
    L5:
        r0 = 0;
        goto L6
    }

    private void checkUi_searchFieldY() {
        this.searchField.setTranslationY(this.animatorSelectorContainerHeight.getFactor());
    }

    private void checkVisibleRows() {
        int r0 = this.listView.getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L55;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof GroupCreateUserCell) == false) goto L43;
        GroupCreateUserCell r32 = (GroupCreateUserCell) r3;
        Object r4 = r32.getObject();
        if ((r4 instanceof TLRPC.User) == false) goto L10;
        long r9 = ((TLRPC.User) r4).id;
    L31:
        if (r9 == 0) goto L54;
        LongSparseArray<TLObject> r42 = this.ignoreUsers;
        if (r42 == null) goto L38;
        if (r42.indexOfKey(r9) < 0) goto L38;
        r32.setChecked(true, false);
        r32.setCheckBoxEnabled(false);
    L38:
        if (this.selectedContacts.indexOfKey(r9) < 0) goto L40;
        boolean r43 = true;
    L41:
        r32.setChecked(r43, true);
        r32.setCheckBoxEnabled(true);
        goto L54
    L40:
        r43 = false;
    L54:
        r2 = r2 + 1;
        goto L3
    L10:
        if ((r4 instanceof TLRPC.Chat) == false) goto L12;
        r9 = -((TLRPC.Chat) r4).id;
        goto L31
    L12:
        boolean r6 = r4 instanceof String;
        if (r6 == true) goto L15;
    L21:
        if (r6 == true) goto L23;
    L29:
        r9 = 0;
        goto L31
    L23:
        if ("miniapps".equalsIgnoreCase((String) r4) == false) goto L29;
        if (this.selectedMiniApps == null) goto L27;
        boolean r44 = true;
    L28:
        r32.setChecked(r44, true);
        r32.setCheckBoxEnabled(true);
        goto L54
    L27:
        r44 = false;
        goto L28
    L15:
        if ("premium".equalsIgnoreCase((String) r4) == false) goto L21;
        if (this.selectedPremium == null) goto L19;
        boolean r45 = true;
    L20:
        r32.setChecked(r45, true);
        r32.setCheckBoxEnabled(true);
        goto L54
    L19:
        r45 = false;
        goto L20
    L43:
        if ((r3 instanceof GraySectionCell) == false) goto L54;
        if (this.listView.getChildAdapterPosition(r3) != GroupCreateAdapter.m15400$$Nest$fgetfirstSectionRow(this.adapter)) goto L54;
        GraySectionCell r33 = (GraySectionCell) r3;
        if (this.selectedPremium == null) goto L49;
    L52:
        String r46 = LocaleController.getString(R.string.DeselectAll);
    L53:
        r33.setRightText(r46, true, new GroupCreateActivity$$ExternalSyntheticLambda9(this));
        goto L54
    L49:
        if (this.selectedContacts.isEmpty() == false) goto L52;
        r46 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L53
    }

    private void closeSearch() {
        this.searching = false;
        this.searchWas = false;
        this.adapter.setSearching(false);
        this.adapter.searchDialogs(null);
        this.listView.setFastScrollVisible(true);
        this.listView.setVerticalScrollBarEnabled(false);
        showItemsAnimated(0);
    }

    private void drawBlurRect(Canvas r8, RectF r9, Paint r10) {
        r8.drawRect(r9, r10);
        if (Build.VERSION.SDK_INT >= 29) goto L5;
        return;
    L5:
        if (SharedConfig.chatBlurEnabled() == false) goto L12;
        BlurredBackgroundSourceRenderNode r1 = this.iBlur3SourceGlassFrosted;
        if (r1 == null) goto L13;
        r1.draw(r8, r9.left, r9.top, r9.right, r9.bottom);
        int r7 = r10.getAlpha();
        r10.setAlpha(178);
        r8.drawRect(r9, r10);
        r10.setAlpha(r7);
        return;
    L13:
        return;
    }

    private HashSet<Long> getSelectedUsers() {
        HashSet<Long> r0 = new HashSet();
        int r1 = 0;
    L4:
        if (r1 >= this.selectedContacts.size()) goto L6;
        r0.add(Long.valueOf(this.selectedContacts.keyAt(r1)));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    private /* synthetic */ void lambda$checkDiscard$13(AlertDialog r1, int r2) {
        onDonePressed(true);
    }

    private /* synthetic */ void lambda$checkDiscard$14(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$checkVisibleRows$12(View r2) {
        this.selectedPremium = null;
        this.selectedContacts.clear();
        this.spansContainer.removeAllSpans(true);
        checkVisibleRows();
        updateEditTextHint();
        updateHint();
    }

    private /* synthetic */ void lambda$createView$0(int r2) {
        this.animatorSelectorContainerHeight.animateTo(Math.min(r2, this.maxSize));
    }

    private /* synthetic */ void lambda$createView$1(View r1) {
        this.searchField.editText.clearFocus();
        this.searchField.editText.requestFocus();
        AndroidUtilities.showKeyboard(this.searchField.editText);
    }

    private /* synthetic */ boolean lambda$createView$2(TextView r1, int r2, KeyEvent r3) {
        if (r2 == 6) goto L5;
        return false;
    L5:
        if (onDonePressed(true) == false) goto L9;
        return true;
    L9:
        return false;
    }

    private /* synthetic */ void lambda$createView$3(TLRPC.User r1, AlertDialog r2, int r3) {
        this.delegate2.needAddBot(r1);
        if (this.searchField.editText.length() <= 0) goto L6;
        this.searchField.editText.setText(null);
        return;
    }

    private /* synthetic */ void lambda$createView$4(Context r12, View r13, int r14) {
        if (r14 != GroupCreateAdapter.m15399$$Nest$fgetcreateCallLinkRow(this.adapter)) goto L6;
        CallLogActivity.createCallLink(r12, this.currentAccount, this.resourceProvider, new GroupCreateActivity$$ExternalSyntheticLambda13(this));
        return;
    L6:
        boolean r0 = true;
        if (r14 != 0) goto L19;
        if (GroupCreateAdapter.m15401$$Nest$fgetinviteViaLink(this.adapter) == 0) goto L19;
        if (GroupCreateAdapter.m15403$$Nest$fgetsearching(this.adapter) == true) goto L19;
        TLRPC.ChatFull r7 = this.info;
        long r8 = this.chatId;
        if (this.channelId != 0) goto L16;
        r0 = false;
    L16:
        PermanentLinkBottomSheet r3 = new PermanentLinkBottomSheet(r12, false, this, r7, r8, r0);
        this.sharedLinkBottomSheet = r3;
        showDialog(r3);
        return;
    L19:
        if ((r13 instanceof GroupCreateUserCell) == false) goto L105;
        GroupCreateUserCell r132 = (GroupCreateUserCell) r13;
        if (r132.currentPremium == false) goto L29;
        GroupCreateSpan r11 = this.selectedPremium;
        if (r11 != null) goto L25;
        GroupCreateSpan r112 = new GroupCreateSpan(this.searchField.editText.getContext(), "premium");
        this.selectedPremium = r112;
        this.spansContainer.addSpan(r112);
        this.selectedPremium.setOnClickListener(this);
    L26:
        checkVisibleRows();
        return;
    L25:
        this.spansContainer.removeSpan(r11);
        this.selectedPremium = null;
        goto L26
    L29:
        if (r132.currentMiniapps == false) goto L36;
        GroupCreateSpan r113 = this.selectedMiniApps;
        if (r113 != null) goto L33;
        GroupCreateSpan r114 = new GroupCreateSpan(this.searchField.editText.getContext(), "miniapps");
        this.selectedMiniApps = r114;
        this.spansContainer.addSpan(r114);
        this.selectedMiniApps.setOnClickListener(this);
    L34:
        checkVisibleRows();
        return;
    L33:
        this.spansContainer.removeSpan(r113);
        this.selectedMiniApps = null;
        goto L34
    L36:
        Object r115 = r132.getObject();
        boolean r142 = r115 instanceof TLRPC.User;
        if (r142 == false) goto L40;
        long r32 = ((TLRPC.User) r115).id;
    L42:
        LongSparseArray<TLObject> r5 = this.ignoreUsers;
        if (r5 == null) goto L48;
        if (r5.indexOfKey(r32) < 0) goto L48;
        return;
    L48:
        if (r132.isBlocked() == false) goto L51;
        showPremiumBlockedToast(r132, r32);
        return;
    L51:
        GroupCreateSpan r133 = this.selectedContacts.get(r32);
        if (r133 == null) goto L55;
        this.spansContainer.removeSpan(r133);
    L92:
        updateHint();
        if (this.searching == false) goto L95;
    L98:
        AndroidUtilities.showKeyboard(this.searchField.editText);
    L100:
        if (this.searchField.editText.length() <= 0) goto L107;
        this.searchField.editText.setText(null);
        return;
    L107:
        return;
    L95:
        if (this.searchWas == true) goto L98;
        checkVisibleRows();
        goto L100
    L55:
        if (this.maxCount == 0) goto L60;
        if (this.selectedContacts.size() != this.maxCount) goto L60;
        return;
    L60:
        if (this.chatType == 0) goto L62;
    L65:
        if (r142 == false) goto L89;
        TLRPC.User r134 = (TLRPC.User) r115;
        if (this.addToGroup == true) goto L69;
    L87:
        getMessagesController().putUser(r134, true ^ this.searching);
    L91:
        GroupCreateSpan r135 = new GroupCreateSpan(this.searchField.editText.getContext(), r115);
        this.spansContainer.addSpan(r135);
        r135.setOnClickListener(this);
        goto L92
    L69:
        if (r134.bot == false) goto L87;
        long r33 = this.channelId;
        if (r33 != 0) goto L80;
        if (r134.bot_nochats == false) goto L80;
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.BotCantJoinGroups)).show();     // Catch: Exception -> L76
        return;
    L76:
        e = move-exception;
        FileLog.e(e);
        return;
    L80:
        if (r33 == 0) goto L87;
        TLRPC.Chat r116 = getMessagesController().getChat(Long.valueOf(this.channelId));
        AlertDialog.Builder r143 = new AlertDialog.Builder(getParentActivity());
        if (ChatObject.canAddAdmins(r116) == false) goto L84;
        r143.setTitle(LocaleController.getString(R.string.AddBotAdminAlert));
        r143.setMessage(LocaleController.getString(R.string.AddBotAsAdmin));
        r143.setPositiveButton(LocaleController.getString(R.string.AddAsAdmin), new GroupCreateActivity$$ExternalSyntheticLambda14(this, r134));
        r143.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
    L85:
        showDialog(r143.create());
        return;
    L84:
        r143.setMessage(LocaleController.getString(R.string.CantAddBotAsAdmin));
        r143.setPositiveButton(LocaleController.getString(R.string.OK), null);
        goto L85
    L89:
        if ((r115 instanceof TLRPC.Chat) == false) goto L91;
        MessagesController r144 = getMessagesController();
        r144.putChat((TLRPC.Chat) r115, true ^ this.searching);
        goto L91
    L62:
        if (this.selectedContacts.size() != getMessagesController().maxGroupCount) goto L65;
        AlertDialog.Builder r117 = new AlertDialog.Builder(getParentActivity());
        r117.setTitle(LocaleController.getString(R.string.AppName));
        r117.setMessage(LocaleController.getString(R.string.SoftUserLimitAlert));
        r117.setPositiveButton(LocaleController.getString(R.string.OK), null);
        showDialog(r117.create());
        return;
    L40:
        if ((r115 instanceof TLRPC.Chat) == false) goto L106;
        r32 = -((TLRPC.Chat) r115).id;
        goto L42
    L106:
        return;
    }

    private /* synthetic */ void lambda$createView$5(View r1) {
        onDonePressed(true);
    }

    private /* synthetic */ void lambda$createView$6(View r2) {
        onCallUsersSelected(getSelectedUsers(), false);
    }

    private /* synthetic */ void lambda$createView$7(View r2) {
        onCallUsersSelected(getSelectedUsers(), false);
    }

    private /* synthetic */ void lambda$createView$8() {
        checkUi_listClip();
        blur3_InvalidateBlur();
    }

    private /* synthetic */ void lambda$createView$9() {
        this.listView.postOnAnimation(new GroupCreateActivity$$ExternalSyntheticLambda15(this));
    }

    private /* synthetic */ void lambda$getThemeDescriptions$17() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L10;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof GroupCreateUserCell) == false) goto L9;
        ((GroupCreateUserCell) r3).update(0);
    L9:
        r2 = r2 + 1;
    L10:
        FragmentSearchField r03 = this.searchField;
        if (r03 == null) goto L13;
        r03.updateColors();
    L13:
        FragmentFloatingButton r5 = this.floatingButton;
        if (r5 == null) goto L20;
        r5.updateColors();
        return;
    }

    private /* synthetic */ void lambda$onDonePressed$16(CheckBoxCell[] r1, AlertDialog r2, int r3) {
        int r22 = 0;
        CheckBoxCell r12 = r1[0];
        if (r12 != null) goto L5;
    L7:
        onAddToGroupDone(r22);
        return;
    L5:
        if (r12.isChecked() == false) goto L7;
        r22 = 100;
        goto L7
    }

    private /* synthetic */ void lambda$showItemsAnimated$11(int r9) {
        AnimatorSet r0 = new AnimatorSet();
        int r1 = this.listView.getChildCount();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L9;
        View r4 = this.listView.getChildAt(r3);
        if (this.listView.getChildAdapterPosition(r4) < r9) goto L8;
        r4.setAlpha(0.0f);
        int r5 = (int) ((Math.min(this.listView.getMeasuredHeight(), Math.max(0, r4.getTop())) / this.listView.getMeasuredHeight()) * 100.0f);
        ObjectAnimator r42 = ObjectAnimator.ofFloat(r4, View.ALPHA, new float[]{0.0f, 1.0f});
        r42.setStartDelay(r5);
        r42.setDuration(200);
        r0.playTogether(new Animator[]{r42});
    L8:
        r3 = r3 + 1;
        goto L3
    L9:
        r0.start();
    }

    private /* synthetic */ void lambda$showPremiumBlockedToast$10() {
        presentFragment(new PremiumPreviewFragment("noncontacts"));
    }

    private void onAddToGroupDone(int r6) {
        ArrayList<TLRPC.User> r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= this.selectedContacts.size()) goto L6;
        r0.add(getMessagesController().getUser(Long.valueOf(this.selectedContacts.keyAt(r1))));
        r1 = r1 + 1;
        goto L4
    L6:
        ContactsAddActivityDelegate r12 = this.delegate2;
        if (r12 == null) goto L9;
        r12.didSelectUsers(r0, r6);
    L9:
        finishFragment();
    }

    private WindowInsetsCompat onApplyWindowInsets(View r2, WindowInsetsCompat r3) {
        int r32 = AndroidUtilities.getDefaultWindowInsets(r3, false).bottom;
        this.navigationBarHeight = r32;
        FrameLayout r0 = this.buttonsContainer;
        if (r0 == null) goto L5;
        r0.setPadding(0, 0, 0, r32);
    L5:
        checkUi_listViewPadding();
        checkUi_floatingButton();
        return WindowInsetsCompat.CONSUMED;
    }

    private boolean onDonePressed(boolean r11) {
        boolean r2 = false;
        int r22 = 0;
        if (this.selectedContacts.isEmpty() == false) goto L10;
        if (this.chatType == 2) goto L10;
        if (this.addToGroup == false) goto L10;
        return false;
    L10:
        if (r11 == false) goto L63;
        if (this.addToGroup == false) goto L63;
        if (getParentActivity() != null) goto L16;
        return false;
    L16:
        AlertDialog.Builder r112 = new AlertDialog.Builder(getParentActivity());
        r112.setTitle(LocaleController.formatPluralString("AddManyMembersAlertTitle", this.selectedContacts.size(), new Object[0]));
        StringBuilder r1 = new StringBuilder();
        int r4 = 0;
    L18:
        if (r4 >= this.selectedContacts.size()) goto L27;
        TLRPC.User r5 = getMessagesController().getUser(Long.valueOf(this.selectedContacts.keyAt(r4)));
        if (r5 == null) goto L26;
        if (r1.length() <= 0) goto L25;
        r1.append(", ");
    L25:
        r1.append("**");
        r1.append(ContactsController.formatName(r5.first_name, r5.last_name));
        r1.append("**");
    L26:
        r4 = r4 + 1;
        goto L18
    L27:
        MessagesController r42 = getMessagesController();
        long r52 = this.chatId;
        if (r52 != 0) goto L31;
        r52 = this.channelId;
    L31:
        TLRPC.Chat r43 = r42.getChat(Long.valueOf(r52));
        if (this.selectedContacts.size() <= 5) goto L41;
        int r53 = this.selectedContacts.size();
        if (r43 != null) goto L36;
        String r6 = _UrlKt.FRAGMENT_ENCODE_SET;
    L37:
        SpannableStringBuilder r12 = new SpannableStringBuilder(AndroidUtilities.replaceTags(LocaleController.formatPluralString("AddManyMembersAlertNamesText", r53, new Object[]{r6})));
        String r54 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(this.selectedContacts.size())});
        int r62 = TextUtils.indexOf(r12, r54);
        if (r62 < 0) goto L40;
        r12.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r62, r54.length() + r62, 33);
    L40:
        r112.setMessage(r12);
    L46:
        CheckBoxCell[] r13 = new CheckBoxCell[1];
        if (ChatObject.isChannel(r43) == true) goto L61;
        LinearLayout r44 = new LinearLayout(getParentActivity());
        r44.setOrientation(1);
        CheckBoxCell r55 = new CheckBoxCell(getParentActivity(), 1, this.resourceProvider);
        r13[0] = r55;
        r55.setBackground(Theme.getSelectorDrawable(false));
        r13[0].setMultiline(true);
        if (this.selectedContacts.size() != 1) goto L51;
        r13[0].setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AddOneMemberForwardMessages, new Object[]{UserObject.getFirstName(getMessagesController().getUser(Long.valueOf(this.selectedContacts.keyAt(0))))})), _UrlKt.FRAGMENT_ENCODE_SET, true, false);
    L52:
        CheckBoxCell r56 = r13[0];
        if (LocaleController.isRTL == false) goto L55;
        int r63 = AndroidUtilities.dp(16.0f);
    L57:
        if (LocaleController.isRTL == false) goto L59;
        int r7 = AndroidUtilities.dp(8.0f);
    L60:
        r56.setPadding(r63, 0, r7, 0);
        r44.addView(r13[0], LayoutHelper.createLinear(-1, -2));
        r13[0].setOnClickListener(new GroupCreateActivity$$ExternalSyntheticLambda16(r13));
        r112.setView(r44);
        goto L61
    L59:
        r7 = AndroidUtilities.dp(16.0f);
        goto L60
    L55:
        r63 = AndroidUtilities.dp(8.0f);
        goto L57
    L51:
        r13[0].setText(LocaleController.getString(R.string.AddMembersForwardMessages), _UrlKt.FRAGMENT_ENCODE_SET, true, false);
    L61:
        r112.setPositiveButton(LocaleController.getString(R.string.Add), new GroupCreateActivity$$ExternalSyntheticLambda17(this, r13));
        r112.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r112.create());
    L102:
        return true;
    L36:
        r6 = r43.title;
        goto L37
    L41:
        int r57 = R.string.AddMembersAlertNamesText;
        if (r43 != null) goto L44;
        String r64 = _UrlKt.FRAGMENT_ENCODE_SET;
    L45:
        r112.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(r57, new Object[]{r1, r64})));
        goto L46
    L44:
        r64 = r43.title;
    L63:
        if (this.chatType != 2) goto L73;
        ArrayList<TLRPC.InputUser> r113 = new ArrayList();
        int r14 = 0;
    L66:
        if (r14 >= this.selectedContacts.size()) goto L71;
        TLRPC.InputUser r45 = getMessagesController().getInputUser(getMessagesController().getUser(Long.valueOf(this.selectedContacts.keyAt(r14))));
        if (r45 == null) goto L70;
        r113.add(r45);
    L70:
        r14 = r14 + 1;
        goto L66
    L71:
        getMessagesController().addUsersToChannel(this.chatId, r113, null);
        getNotificationCenter().postNotificationName(NotificationCenter.closeChats, new Object[0]);
        Bundle r114 = new Bundle();
        r114.putLong("chat_id", this.chatId);
        r114.putBoolean("just_created_chat", true);
        presentFragment(new ChatActivity(r114), true);
        goto L102
    L73:
        if (this.doneButtonVisible == true) goto L76;
        return false;
    L76:
        if (this.addToGroup == false) goto L78;
        onAddToGroupDone(0);
        goto L102
    L78:
        ArrayList<Long> r115 = new ArrayList();
        int r0 = 0;
    L80:
        if (r0 >= this.selectedContacts.size()) goto L83;
        r115.add(Long.valueOf(this.selectedContacts.keyAt(r0)));
        r0 = r0 + 1;
        goto L80
    L83:
        if (this.isAlwaysShare == false) goto L85;
    L91:
        GroupCreateActivityDelegate r02 = this.delegate;
        if (r02 != null) goto L94;
    L101:
        finishFragment();
        goto L102
    L94:
        if (this.selectedPremium == null) goto L96;
        boolean r15 = true;
    L98:
        if (this.selectedMiniApps == null) goto L100;
        r2 = true;
    L100:
        r02.didSelectUsers(r15, r2, r115);
        goto L101
    L96:
        r15 = false;
        goto L98
    L85:
        if (this.isNeverShare == true) goto L91;
        Bundle r03 = new Bundle();
        int r16 = r115.size();
        long[] r46 = new long[r16];
    L88:
        if (r22 >= r16) goto L90;
        r46[r22] = r115.get(r22).longValue();
        r22 = r22 + 1;
        goto L88
    L90:
        r03.putLongArray("result", r46);
        r03.putInt("chatType", this.chatType);
        r03.putBoolean("forImport", this.forImport);
        presentFragment(new GroupCreateFinalActivity(r03));
        goto L102
    }

    private void showItemsAnimated(int r3) {
        if (this.isPaused == false) goto L5;
        return;
    L5:
        AndroidUtilities.doOnPreDraw(this.listView, new GroupCreateActivity$$ExternalSyntheticLambda18(this, r3));
    }

    private void showPremiumBlockedToast(View r3, long r4) {
        int r0 = -this.shiftDp;
        this.shiftDp = r0;
        AndroidUtilities.shakeViewSpring(r3, r0);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        if (r4 < 0) goto L5;
        String r32 = UserObject.getUserName(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r4)));
    L7:
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == false) goto L9;
        Bulletin r2 = BulletinFactory.of(this).createSimpleBulletin(R.raw.star_premium_2, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserBlockedNonPremium, new Object[]{r32})));
    L10:
        r2.show();
        return;
    L9:
        r2 = BulletinFactory.of(this).createSimpleBulletin(R.raw.star_premium_2, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserBlockedNonPremium, new Object[]{r32})), LocaleController.getString(R.string.UserBlockedNonPremiumButton), new GroupCreateActivity$$ExternalSyntheticLambda19(this));
        goto L10
    L5:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L7
    }

    private void updateButtonsVisibility() {
        if (this.buttonsContainer != null) goto L5;
        return;
    L5:
        this.animatorCallButtonsVisible.setValue(!this.selectedContacts.isEmpty(), true);
    }

    private void updateEditTextHint() {
        EditTextBoldCursor r0 = this.searchField.editText;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.chatType != 2) goto L10;
        r0.setHint(LocaleController.getString(R.string.AddMutual));
        return;
    L10:
        if (this.addToGroup == true) goto L29;
        GroupCreateAdapter r02 = this.adapter;
        if (r02 == null) goto L17;
        if (GroupCreateAdapter.m15402$$Nest$fgetnoContactsStubRow(r02) == 0) goto L29;
    L17:
        if (this.isAlwaysShare == false) goto L19;
    L27:
        this.searchField.editText.setHint(LocaleController.getString(R.string.SearchForPeopleAndGroups));
        return;
    L19:
        if (this.isNeverShare == true) goto L27;
        boolean r03 = this.isCall;
        FragmentSearchField r3 = this.searchField;
        if (r03 == false) goto L25;
        r3.editText.setHint(LocaleController.getString(R.string.NewCallSearch));
        return;
    L25:
        r3.editText.setHint(LocaleController.getString(R.string.SendMessageTo));
        return;
    L29:
        this.searchField.editText.setHint(LocaleController.getString(R.string.SearchForPeople));
    }

    private void updateHint() {
        if (this.isAlwaysShare == true) goto L16;
        if (this.isNeverShare == true) goto L16;
        if (this.addToGroup == true) goto L16;
        if (this.chatType != 2) goto L12;
        this.actionBar.setSubtitle(LocaleController.formatPluralString("Members", this.selectedContacts.size(), new Object[0]));
        goto L16
    L12:
        if (this.selectedContacts.isEmpty() == false) goto L14;
        this.actionBar.setSubtitle(LocaleController.formatString(R.string.MembersCountZero, new Object[]{LocaleController.formatPluralString("Members", this.maxCount + (this.isCall ? 1 : 0), new Object[0])}));
        goto L16
    L14:
        String r0 = LocaleController.getPluralString("MembersCountSelected", this.selectedContacts.size());
        this.actionBar.setSubtitle(String.format(r0, new Object[]{Integer.valueOf(this.selectedContacts.size()), Integer.valueOf(this.maxCount)}));
    L16:
        if (this.chatType != 2) goto L18;
        return;
    L18:
        if (this.addToGroup == true) goto L20;
        return;
    L20:
        if (this.doneButtonVisible == false) goto L26;
        if (this.allSpans.isEmpty() == false) goto L26;
        this.floatingButton.setButtonVisible(false, true);
        this.doneButtonVisible = false;
        return;
    L26:
        if (this.doneButtonVisible == false) goto L28;
        return;
    L28:
        if (this.allSpans.isEmpty() == true) goto L34;
        this.floatingButton.setButtonVisible(true, true);
        this.doneButtonVisible = true;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean canBeginSlide() {
        return checkDiscard(true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ActionBar createActionBar(Context r1) {
        ActionBar r0 = super.createActionBar(r1);
        r0.setAddToContainer(false);
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r23) {
        this.searching = false;
        this.searchWas = false;
        this.allSpans.clear();
        this.selectedContacts.clear();
        this.currentDeletingSpan = null;
        int r5 = 2;
        if (this.chatType != 2) goto L5;
        this.doneButtonVisible = true;
    L6:
        this.actionBar.setBackgroundColor(0);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        if (TextUtils.isEmpty(this.customTitle) == true) goto L9;
        this.actionBar.setTitle(this.customTitle);
    L45:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.searchField = new FragmentSearchField(r23, this.resourceProvider);
        AnonymousClass2 r3 = new AnonymousClass2(this, r23);
        this.fragmentView = r3;
        r3.setFocusableInTouchMode(true);
        r3.setDescendantFocusability(131072);
        AnonymousClass3 r6 = new AnonymousClass3(this, r23, this.currentAccount);
        this.spansContainer = r6;
        r6.setDelegate(new GroupCreateActivity$$ExternalSyntheticLambda0(this));
        this.spansContainer.getSpansContainer().setOnClickListener(new GroupCreateActivity$$ExternalSyntheticLambda1(this));
        FragmentSpansContainer r62 = this.spansContainer;
        this.selectedContacts = r62.selectedContacts;
        this.allSpans = r62.allSpans;
        updateEditTextHint();
        this.searchField.editText.setOnEditorActionListener(new GroupCreateActivity$$ExternalSyntheticLambda2(this));
        this.searchField.editText.setOnKeyListener(new AnonymousClass4(this));
        this.searchField.editText.addTextChangedListener(new AnonymousClass5(this));
        ArrayList<Long> r63 = this.toSelectIds;
        if (r63 == null) goto L48;
        select(r63, this.toSelectPremium, this.toSelectMiniApps);
    L48:
        FlickerLoadingView r64 = new FlickerLoadingView(r23);
        r64.setViewType(6);
        r64.showDate(false);
        StickerEmptyView r7 = new StickerEmptyView(r23, r64, 1);
        this.emptyView = r7;
        r7.addView(r64);
        this.emptyView.showProgress(true, false);
        this.emptyView.title.setText(LocaleController.getString(R.string.NoResult));
        r3.addView(this.emptyView);
        this.layoutManager = new LinearLayoutManager(r23, 1, false);
        RecyclerListView r65 = new RecyclerListView(r23);
        this.listView = r65;
        r65.setFastScrollEnabled(0);
        this.listView.setEmptyView(this.emptyView);
        RecyclerListView r66 = this.listView;
        GroupCreateAdapter r72 = new GroupCreateAdapter(this, r23);
        this.adapter = r72;
        r66.setAdapter(r72);
        this.listView.setLayoutManager(this.layoutManager);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setClipToPadding(false);
        RecyclerListView r67 = this.listView;
        if (LocaleController.isRTL == false) goto L51;
        r5 = 1;
    L51:
        r67.setVerticalScrollbarPosition(r5);
        RecyclerListView r52 = this.listView;
        int r68 = this.ADDITIONAL_LIST_HEIGHT_DP;
        r3.addView(r52, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, -r68, 0.0f, -r68));
        this.listView.setOnItemClickListener(new GroupCreateActivity$$ExternalSyntheticLambda3(this, r23));
        this.listView.setOnScrollListener(new AnonymousClass6(this));
        this.listView.setAnimateEmptyView(true, 0);
        FragmentFloatingButton r53 = new FragmentFloatingButton(r23, this.resourceProvider);
        this.floatingButton = r53;
        if (this.isNeverShare == false) goto L54;
    L59:
        r53.imageView.setImageResource(R.drawable.floating_check);
    L61:
        if (this.isCall == true) goto L63;
        r3.addView(this.floatingButton, FragmentFloatingButton.createDefaultLayoutParams());
    L63:
        this.floatingButton.setOnClickListener(new GroupCreateActivity$$ExternalSyntheticLambda4(this));
        this.floatingButton.setButtonVisible(this.doneButtonVisible, false);
        this.floatingButton.setContentDescription(LocaleController.getString(R.string.Next));
        if (this.isCall == false) goto L66;
        this.buttonsContainer = new AnonymousClass7(this, r23);
        View r54 = new View(r23);
        r54.setBackgroundColor(Theme.getColor(Theme.key_divider, this.resourceProvider));
        this.buttonsContainer.addView(r54, LayoutHelper.createFrame(-1, 1.0f / AndroidUtilities.density, 55, 0.0f, 0.0f, 0.0f, 0.0f));
        LinearLayout r55 = new LinearLayout(r23);
        r55.setOrientation(0);
        r55.setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f));
        this.buttonsContainer.addView(r55, LayoutHelper.createFrame(-1, -2, 87));
        ButtonWithCounterView r73 = new ButtonWithCounterView(r23, this.resourceProvider);
        r73.setRound();
        SpannableStringBuilder r10 = new SpannableStringBuilder();
        r10.append("x  ");
        r10.setSpan(new ColoredImageSpan(R.drawable.profile_phone), 0, 1, 33);
        r10.append(LocaleController.getString(R.string.GroupCallCreateVoice));
        r73.setText(r10, false);
        r55.addView(r73, LayoutHelper.createLinear(-1, 48, 1.0f, 119, 0, 0, 6, 0));
        r73.setOnClickListener(new GroupCreateActivity$$ExternalSyntheticLambda5(this));
        ButtonWithCounterView r74 = new ButtonWithCounterView(r23, this.resourceProvider);
        r74.setRound();
        SpannableStringBuilder r102 = new SpannableStringBuilder();
        r102.append("x  ");
        r102.setSpan(new ColoredImageSpan(R.drawable.profile_video), 0, 1, 33);
        r102.append(LocaleController.getString(R.string.GroupCallCreateVideo));
        r74.setText(r102, false);
        r55.addView(r74, LayoutHelper.createLinear(-1, 48, 1.0f, 119, 6, 0, 0, 0));
        r74.setOnClickListener(new GroupCreateActivity$$ExternalSyntheticLambda6(this));
        r3.addView(this.buttonsContainer, LayoutHelper.createFrame(-1, -2, 87));
        checkUi_bottomButtons();
    L66:
        updateHint();
        AnonymousClass8 r4 = new AnonymousClass8(this, r23);
        this.actionBarBackgroundView = r4;
        r3.addView(r4, LayoutHelper.createFrame(-1, 0, 48));
        r3.addView(this.actionBar);
        r3.addView(this.searchField, LayoutHelper.createFrame(-1, 40.0f, 48, 11.0f, 0.0f, 11.0f, 0.0f));
        r3.addView(this.spansContainer);
        RecyclerListView r75 = this.listView;
        Objects.requireNonNull(r75);
        this.iBlur3Capture = new ViewGroupPartRenderer(r75, r3, new ShareAlert$$ExternalSyntheticLambda16(r75));
        this.listView.addEdgeEffectListener(new GroupCreateActivity$$ExternalSyntheticLambda7(this));
        HeaderShadowView r42 = new HeaderShadowView(r23, this.parentLayout);
        this.headerShadowView = r42;
        r42.setShadowVisible(false, false);
        r3.addView(this.headerShadowView, LayoutHelper.createFrame(-1, 5, 48));
        LaunchActivity r1 = LaunchActivity.instance;
        if (r1 == null) goto L69;
        r1.getRootAnimatedInsetsListener().subscribeToWindowInsetsAnimation(this);
    L69:
        ViewCompat.setOnApplyWindowInsetsListener(this.fragmentView, new GroupCreateActivity$$ExternalSyntheticLambda8(this));
        return this.fragmentView;
    L54:
        if (this.isAlwaysShare == true) goto L59;
        if (this.addToGroup == true) goto L59;
        BackDrawable r56 = new BackDrawable(false);
        r56.setArrowRotation(180);
        this.floatingButton.imageView.setImageDrawable(r56);
        goto L61
    L9:
        int r32 = this.chatType;
        if (r32 != 2) goto L13;
        this.actionBar.setTitle(LocaleController.getString(R.string.ChannelAddSubscribers));
        goto L45
    L13:
        if (this.isCall == false) goto L16;
        this.actionBar.setTitle(LocaleController.getString(R.string.NewCall));
        goto L45
    L16:
        if (this.addToGroup == false) goto L22;
        long r69 = this.channelId;
        ActionBar r610 = this.actionBar;
        if (r69 == 0) goto L20;
        r610.setTitle(LocaleController.getString(R.string.ChannelAddSubscribers));
        goto L45
    L20:
        r610.setTitle(LocaleController.getString(R.string.GroupAddMembers));
        goto L45
    L22:
        if (this.isAlwaysShare == false) goto L31;
        int r33 = this.chatAddType;
        if (r33 != 2) goto L26;
        this.actionBar.setTitle(LocaleController.getString(R.string.FilterAlwaysShow));
        goto L45
    L26:
        ActionBar r611 = this.actionBar;
        if (r33 != 1) goto L29;
        r611.setTitle(LocaleController.getString(R.string.AlwaysAllow));
        goto L45
    L29:
        r611.setTitle(LocaleController.getString(R.string.AlwaysShareWithTitle));
        goto L45
    L31:
        if (this.isNeverShare == false) goto L39;
        int r34 = this.chatAddType;
        if (r34 != 2) goto L35;
        this.actionBar.setTitle(LocaleController.getString(R.string.FilterNeverShow));
        goto L45
    L35:
        ActionBar r612 = this.actionBar;
        if (r34 != 1) goto L38;
        r612.setTitle(LocaleController.getString(R.string.NeverAllow));
        goto L45
    L38:
        r612.setTitle(LocaleController.getString(R.string.NeverShareWithTitle));
        goto L45
    L39:
        ActionBar r613 = this.actionBar;
        if (r32 != 0) goto L43;
        int r35 = R.string.NewGroup;
    L44:
        r613.setTitle(LocaleController.getString(r35));
        goto L45
    L43:
        r35 = R.string.NewBroadcastList;
        goto L44
    L5:
        this.doneButtonVisible = !this.addToGroup;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.contactsDidLoad) goto L9;
        GroupCreateAdapter r2 = this.adapter;
        if (r2 == null) goto L31;
        r2.notifyDataSetChanged();
        return;
    L31:
        return;
    L9:
        if (r3 != NotificationCenter.updateInterfaces) goto L24;
        if (this.listView == null) goto L32;
        int r32 = 0;
        int r42 = ((Integer) r5[0]).intValue();
        int r52 = this.listView.getChildCount();
        if ((MessagesController.UPDATE_MASK_AVATAR & r42) != 0) goto L18;
        if ((MessagesController.UPDATE_MASK_NAME & r42) != 0) goto L18;
        if ((MessagesController.UPDATE_MASK_STATUS & r42) != 0) goto L18;
        return;
    L18:
        if (r32 >= r52) goto L35;
        View r0 = this.listView.getChildAt(r32);
        if ((r0 instanceof GroupCreateUserCell) == false) goto L22;
        ((GroupCreateUserCell) r0).update(r42);
    L22:
        r32 = r32 + 1;
        goto L18
    L35:
        return;
    L32:
        return;
    L24:
        if (r3 != NotificationCenter.chatDidCreated) goto L34;
        removeSelfFromStack();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean drawEdgeNavigationBar() {
        return false;
    }

    @Override // org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider.Listener
    public View getAnimatedInsetsTargetView() {
        return this.fragmentView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        GroupCreateActivity$$ExternalSyntheticLambda12 r8 = new GroupCreateActivity$$ExternalSyntheticLambda12(this);
        View r10 = this.fragmentView;
        int r11 = ThemeDescription.FLAG_BACKGROUND;
        int r16 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r10, r11, null, null, null, null, r16));
        ActionBar r2 = this.actionBar;
        int r19 = ThemeDescription.FLAG_BACKGROUND;
        int r24 = Theme.key_actionBarDefault;
        r1.add(new ThemeDescription(r2, r19, null, null, null, null, r24));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r24));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.spansContainer, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollActive));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollInactive));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{GroupCreateSectionCell.class}, null, null, null, Theme.key_graySection));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{GroupCreateSectionCell.class}, new String[]{"drawable"}, null, null, null, Theme.key_groupcreate_sectionShadow));
        int r22 = Theme.key_groupcreate_sectionText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateSectionCell.class}, new String[]{"textView"}, null, null, null, r22));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"textView"}, null, null, null, r22));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkbox));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkboxDisabled));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkboxCheck));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{GroupCreateUserCell.class}, new String[]{"statusTextView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueText));
        int r21 = Theme.key_windowBackgroundWhiteGrayText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{GroupCreateUserCell.class}, new String[]{"statusTextView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{GroupCreateUserCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        int r162 = Theme.key_avatar_backgroundBlue;
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, r162));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.spansContainer.getSpansContainer(), 0, new Class[]{GroupCreateSpan.class}, null, null, null, Theme.key_groupcreate_spanBackground));
        r1.add(new ThemeDescription(this.spansContainer.getSpansContainer(), 0, new Class[]{GroupCreateSpan.class}, null, null, null, Theme.key_groupcreate_spanText));
        r1.add(new ThemeDescription(this.spansContainer.getSpansContainer(), 0, new Class[]{GroupCreateSpan.class}, null, null, null, Theme.key_groupcreate_spanDelete));
        r1.add(new ThemeDescription(this.spansContainer.getSpansContainer(), 0, new Class[]{GroupCreateSpan.class}, null, null, null, r162));
        r1.add(new ThemeDescription(this.emptyView.title, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.emptyView.subtitle, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r21));
        PermanentLinkBottomSheet r0 = this.sharedLinkBottomSheet;
        if (r0 == null) goto L5;
        r1.addAll(r0.getThemeDescriptions());
    L5:
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider.Listener
    public void onAnimatedInsetsChanged(View r1, WindowInsetsCompat r2) {
        this.imeInsetAnimatedHeight = r2.getInsets(WindowInsetsCompat.Type.ime()).bottom;
        checkUi_floatingButton();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (checkDiscard(r2) == true) goto L7;
        return false;
    L7:
        return super.onBackPressed(r2);
    }

    public void onCallUsersSelected(HashSet<Long> r1, boolean r2) {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View r2) {
        GroupCreateSpan r22 = (GroupCreateSpan) r2;
        if (r22.isDeleting() == false) goto L6;
        this.currentDeletingSpan = null;
        this.spansContainer.removeSpan(r22);
        updateHint();
        checkVisibleRows();
        return;
    L6:
        GroupCreateSpan r0 = this.currentDeletingSpan;
        if (r0 == null) goto L9;
        r0.cancelDeleteAnimation();
    L9:
        this.currentDeletingSpan = r22;
        r22.startDeleteAnimation();
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 3) goto L11;
        int r12 = this.listView.getPaddingTop();
        checkUi_listViewPadding();
        checkUi_searchFieldY();
        checkUi_listClip();
        checkUi_headerShadowY();
        this.actionBarBackgroundView.invalidate();
        int r22 = this.listView.getPaddingTop();
        if (r22 != r12) goto L7;
        return;
    L7:
        if (this.headerShadowView.isShadowVisible() == true) goto L15;
        this.listView.scrollBy(0, r12 - r22);
        return;
    L15:
        return;
    L11:
        if (r1 != 4) goto L16;
        checkUi_bottomButtons();
        checkUi_listClip();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.contactsDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.updateInterfaces);
        getNotificationCenter().addObserver(this, NotificationCenter.chatDidCreated);
        getUserConfig().loadGlobalTTl();
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.contactsDidLoad);
        getNotificationCenter().removeObserver(this, NotificationCenter.updateInterfaces);
        getNotificationCenter().removeObserver(this, NotificationCenter.chatDidCreated);
    }

    public void select(ArrayList<Long> r6, boolean r7, boolean r8) {
        this.initialIds.clear();
        this.initialIds.addAll(r6);
        this.initialPremium = r7;
        this.initialMiniApps = r8;
        FragmentSpansContainer r0 = this.spansContainer;
        if (r0 != null) goto L7;
        this.toSelectIds = r6;
        this.toSelectPremium = r7;
        this.toSelectMiniApps = r8;
        return;
    L7:
        if (r7 == true) goto L9;
    L11:
        if (r7 == true) goto L15;
        GroupCreateSpan r72 = this.selectedPremium;
        if (r72 == null) goto L15;
        r0.removeSpan(r72);
        this.selectedPremium = null;
    L15:
        if (r8 == true) goto L17;
    L19:
        if (r8 == true) goto L23;
        GroupCreateSpan r73 = this.selectedMiniApps;
        if (r73 == null) goto L23;
        this.spansContainer.removeSpan(r73);
        this.selectedMiniApps = null;
    L23:
        int r74 = r6.size();
        int r82 = 0;
    L24:
        if (r82 >= r74) goto L32;
        Long r02 = r6.get(r82);
        r82 = r82 + 1;
        Long r03 = r02;
        long r1 = r03.longValue();
        if (r1 >= 0) goto L28;
        Object r04 = getMessagesController().getChat(Long.valueOf(-r1));
    L29:
        if (r04 == null) goto L24;
        GroupCreateSpan r12 = new GroupCreateSpan(getContext(), r04);
        this.spansContainer.addSpan(r12);
        r12.setOnClickListener(this);
        goto L24
    L28:
        r04 = getMessagesController().getUser(r03);
        goto L29
    L32:
        this.spansContainer.endAnimation();
        AndroidUtilities.updateVisibleRows(this.listView);
        return;
    L17:
        if (this.selectedMiniApps != null) goto L19;
        GroupCreateSpan r75 = new GroupCreateSpan(getContext(), "miniApps");
        this.selectedMiniApps = r75;
        this.spansContainer.addSpan(r75);
        this.selectedMiniApps.setOnClickListener(this);
        goto L23
    L9:
        if (this.selectedPremium != null) goto L11;
        GroupCreateSpan r76 = new GroupCreateSpan(getContext(), "premium");
        this.selectedPremium = r76;
        this.spansContainer.addSpan(r76);
        this.selectedPremium.setOnClickListener(this);
        goto L15
    }

    public void setDelegate(GroupCreateActivityDelegate r1) {
        this.delegate = r1;
    }

    public void setDelegate2(ContactsAddActivityDelegate r1) {
        this.delegate2 = r1;
    }

    public void setIgnoreUsers(LongSparseArray<TLObject> r1) {
        this.ignoreUsers = r1;
    }

    public void setInfo(TLRPC.ChatFull r1) {
        this.info = r1;
    }

    public void setShowDiscardConfirm(boolean r1) {
        this.showDiscardConfirm = r1;
    }

    public void setTitle(String r1) {
        this.customTitle = r1;
    }
}
