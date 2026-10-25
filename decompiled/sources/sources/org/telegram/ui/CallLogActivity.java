package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.config.BottomNavigationBar;
import com.exteragram.messenger.utils.ui.MainTabsUiHelper;
import com.exteragram.messenger.utils.ui.TextPaint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.LoadingCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AvatarsImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.DialogsActivityTopPanelLayout;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.FragmentContextView;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.ProgressButton;
import org.telegram.ui.Components.QRCodeBottomSheet;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerAnimationScrollHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.ViewGroupPartRenderer;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.MainTabsActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes6.dex */
public class CallLogActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate, MainTabsActivity.TabFragmentDelegate {
    private final int ADDITIONAL_LIST_HEIGHT_DP;
    private final int ID_CREATE_CALL;
    private final int ID_SHOW_IN_MAIN_TABS;
    private ImageView actionModeCloseView;
    private final ArrayList<View> actionModeViews;
    private ArrayList<Long> activeGroupCalls;
    private int additionFloatingButtonOffset;
    private int additionNavigationBarHeight;
    private float additionalFloatingTranslation;
    private final ArrayList<CallLogRow> calls;
    private SizeNotifierFrameLayout contentView;
    private EmptyTextProgressView emptyView;
    private boolean endReached;
    private boolean firstLoaded;
    private FlickerLoadingView flickerLoadingView;
    private FragmentFloatingButton floatingButton;
    private FragmentContextView fragmentContextView;
    private FrameLayout fragmentContextViewWrapper;
    private Drawable greenDrawable;
    private Drawable greenDrawable2;
    private boolean hasMainTabs;
    private HeaderShadowView headerShadowView;
    private HintView2 hideCallTabsHintView;
    private boolean hideCallTabsHintWasShown;
    private IBlur3Capture iBlur3Capture;
    private final BlurredBackgroundDrawableViewFactory iBlur3FactoryLiquidGlass;
    private boolean iBlur3Invalidated;
    private final RectF iBlur3PositionActionBar;
    private final RectF iBlur3PositionMainTabs;
    private final ArrayList<RectF> iBlur3Positions;
    private final BlurredBackgroundSourceColor iBlur3SourceColor;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlass;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlassFrosted;
    private TLRPC.Chat lastCallChat;
    private TLRPC.User lastCallUser;
    private LinearLayoutManager layoutManager;
    private UniversalRecyclerView listView;
    private boolean loading;
    private MainTabsActivityController mainTabsActivityController;
    private boolean mainTabsHiddenByScroll;
    private int navigationBarHeight;
    private boolean needFinishFragment;
    private boolean openTransitionStarted;
    private ActionBarMenuItem otherItem;
    private Drawable redDrawable;
    private Drawable redDrawable2;
    private RecyclerAnimationScrollHelper scrollHelper;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    private NumberTextView selectedDialogsCountTextView;
    private final ArrayList<Integer> selectedIds;
    private final Rect tmpClipRect;
    private DialogsActivityTopPanelLayout topPanelLayout;
    private Long waitingForCallChatId;

    /* renamed from: org.telegram.ui.CallLogActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ CallLogActivity this$0;

        public AnonymousClass1(CallLogActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L11;
            boolean r22 = CallLogActivity.access$000(this.this$0).isActionModeShowed();
            CallLogActivity r1 = this.this$0;
            if (r22 == false) goto L8;
            CallLogActivity.m6558$$Nest$mhideActionMode(r1, true);
            return;
        L8:
            r1.finishFragment();
            return;
        L11:
            if (r2 != 2) goto L14;
            CallLogActivity.m6559$$Nest$mshowDeleteAlert(this.this$0, false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.CallLogActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        final /* synthetic */ CallLogActivity this$0;

        public AnonymousClass2(CallLogActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            if (Build.VERSION.SDK_INT >= 31) goto L5;
        L23:
            super.dispatchDraw(r6);
            return;
        L5:
            if (CallLogActivity.m6548$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L23;
            CallLogActivity.m6553$$Nest$mblur3_InvalidateBlur(this.this$0);
            int r0 = getMeasuredWidth();
            int r1 = getMeasuredHeight();
            if (CallLogActivity.m6543$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) == null) goto L15;
            if (CallLogActivity.m6543$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).inRecording() == true) goto L15;
            RecordingCanvas r2 = CallLogActivity.m6543$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).beginRecording(r0, r1);
            r2.drawColor(this.this$0.getThemedColor(Theme.key_windowBackgroundGray));
            if (SharedConfig.chatBlurEnabled() == false) goto L13;
            CallLogActivity.m6548$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r2, -3);
        L13:
            CallLogActivity.m6543$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).endRecording();
        L15:
            if (CallLogActivity.m6542$$Nest$fgetiBlur3SourceGlass(this.this$0) != null) goto L17;
        L22:
            CallLogActivity.m6551$$Nest$fputiBlur3Invalidated(this.this$0, false);
            goto L23
        L17:
            if (CallLogActivity.m6542$$Nest$fgetiBlur3SourceGlass(this.this$0).inRecording() == true) goto L22;
            RecordingCanvas r02 = CallLogActivity.m6542$$Nest$fgetiBlur3SourceGlass(this.this$0).beginRecording(r0, r1);
            r02.drawColor(this.this$0.getThemedColor(Theme.key_windowBackgroundGray));
            if (SharedConfig.chatBlurEnabled() == false) goto L21;
            CallLogActivity.m6548$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r02, -2);
        L21:
            CallLogActivity.m6542$$Nest$fgetiBlur3SourceGlass(this.this$0).endRecording();
            goto L22
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public void drawBlurRect(Canvas r7, float r8, Rect r9, Paint r10, boolean r11) {
            if (Build.VERSION.SDK_INT < 29) goto L11;
            if (SharedConfig.chatBlurEnabled() == false) goto L11;
            if (CallLogActivity.m6543$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) == null) goto L11;
            r7.save();
            r7.translate(0.0f, -r8);
            CallLogActivity.m6543$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).draw(r7, r9.left, r9.top + r8, r9.right, r9.bottom + r8);
            r7.restore();
            int r6 = r10.getAlpha();
            r10.setAlpha(178);
            r7.drawRect(r9, r10);
            r10.setAlpha(r6);
            return;
        L11:
            r7.drawRect(r9, r10);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            CallLogActivity.m6554$$Nest$mcheckUi_floatingButton(this.this$0);
            CallLogActivity.m6555$$Nest$mcheckUi_listClip(this.this$0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            measureChildWithMargins(CallLogActivity.access$100(this.this$0), r8, 0, r9, 0);
            ViewGroup.MarginLayoutParams r7 = (ViewGroup.MarginLayoutParams) CallLogActivity.m6549$$Nest$fgettopPanelLayout(this.this$0).getLayoutParams();
            r7.topMargin = CallLogActivity.access$200(this.this$0).getMeasuredHeight() - AndroidUtilities.dp(14.0f);
            ViewGroup.MarginLayoutParams r72 = (ViewGroup.MarginLayoutParams) CallLogActivity.m6537$$Nest$fgetemptyView(this.this$0).getLayoutParams();
            r72.topMargin = CallLogActivity.access$300(this.this$0).getMeasuredHeight();
            ViewGroup.MarginLayoutParams r73 = (ViewGroup.MarginLayoutParams) CallLogActivity.m6541$$Nest$fgetheaderShadowView(this.this$0).getLayoutParams();
            r73.topMargin = CallLogActivity.access$400(this.this$0).getMeasuredHeight();
            CallLogActivity.m6556$$Nest$mcheckUi_listViewPadding(this.this$0);
            super.onMeasure(r8, r9);
        }
    }

    /* renamed from: org.telegram.ui.CallLogActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        private boolean scrollUpdated;
        final /* synthetic */ CallLogActivity this$0;

        public static /* synthetic */ void $r8$lambda$iRFEqehapgbXV6OFMVt1wkqlR7U(AnonymousClass3 r0, CallLogRow r1) {
            r0.lambda$onScrolled$0(r1);
        }

        public AnonymousClass3(CallLogActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onScrolled$0(CallLogRow r2) {
            CallLogActivity.m6557$$Nest$mgetCalls(this.this$0, r2.calls.get(r2.size() - 1).id, 100);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r7, int r8, int r9) {
            int r0 = CallLogActivity.m6544$$Nest$fgetlayoutManager(this.this$0).findFirstVisibleItemPosition();
            boolean r2 = false;
            if (r0 != (-1)) goto L5;
            int r1 = 0;
        L6:
            if (r1 <= 0) goto L16;
            int r4 = CallLogActivity.m6545$$Nest$fgetlistView(this.this$0).adapter.getItemCount();
            if (CallLogActivity.m6538$$Nest$fgetendReached(this.this$0) == true) goto L16;
            if (CallLogActivity.m6546$$Nest$fgetloading(this.this$0) == true) goto L16;
            if (CallLogActivity.m6536$$Nest$fgetcalls(this.this$0).isEmpty() == true) goto L16;
            if ((r1 + r0) < (r4 - 5)) goto L16;
            AndroidUtilities.runOnUIThread(new CallLogActivity$3$$ExternalSyntheticLambda0(this, (CallLogRow) CallLogActivity.m6536$$Nest$fgetcalls(this.this$0).get(CallLogActivity.m6536$$Nest$fgetcalls(this.this$0).size() - 1)));
        L16:
            View r12 = r7.getChildAt(0);
            if (r12 == null) goto L19;
            int r13 = r12.getTop();
        L20:
            if (r9 != 0) goto L22;
        L36:
            this.scrollUpdated = true;
            HeaderShadowView r72 = CallLogActivity.m6541$$Nest$fgetheaderShadowView(this.this$0);
            if (r0 == 0) goto L39;
        L40:
            r2 = true;
        L41:
            r72.setShadowVisible(r2, true);
            if (Build.VERSION.SDK_INT >= 31) goto L44;
            return;
        L44:
            if (CallLogActivity.m6548$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L48;
            CallLogActivity.m6548$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r8, r9);
            CallLogActivity.m6553$$Nest$mblur3_InvalidateBlur(this.this$0);
            return;
        L48:
            return;
        L39:
            if (r13 >= CallLogActivity.m6545$$Nest$fgetlistView(this.this$0).getPaddingTop()) goto L41;
        L22:
            if (this.scrollUpdated == false) goto L36;
            boolean r73 = r7.canScrollVertically(1);
            FragmentFloatingButton r42 = CallLogActivity.m6539$$Nest$fgetfloatingButton(this.this$0);
            if (r9 < 0) goto L28;
            if (r73 == false) goto L28;
            boolean r5 = false;
        L29:
            r42.setButtonVisible(r5, true);
            CallLogActivity r43 = this.this$0;
            if (BottomNavigationBar.floating() == false) goto L34;
            if (r9 <= 0) goto L34;
            if (r73 == false) goto L34;
            boolean r74 = true;
        L35:
            CallLogActivity.m6552$$Nest$fputmainTabsHiddenByScroll(r43, r74);
            this.this$0.updateMainTabsVisibility();
        L34:
            r74 = false;
        L28:
            r5 = true;
            goto L29
        L19:
            r13 = 0;
            goto L20
        L5:
            r1 = Math.abs(CallLogActivity.m6544$$Nest$fgetlayoutManager(this.this$0).findLastVisibleItemPosition() - r0) + 1;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.CallLogActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends FragmentContextView {
        final /* synthetic */ CallLogActivity this$0;

        public AnonymousClass4(CallLogActivity r1, Context r2, BaseFragment r3, View r4, boolean r5, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.FragmentContextView, android.view.View
        public void setVisibility(int r2) {
            DialogsActivityTopPanelLayout r0 = CallLogActivity.m6549$$Nest$fgettopPanelLayout(this.this$0);
            FrameLayout r1 = CallLogActivity.m6540$$Nest$fgetfragmentContextViewWrapper(this.this$0);
            if (r2 != 0) goto L5;
            boolean r22 = true;
        L6:
            r0.setViewVisible(r1, r22);
            return;
        L5:
            r22 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.CallLogActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements Bulletin.Delegate {
        final /* synthetic */ CallLogActivity this$0;

        public AnonymousClass5(CallLogActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return CallLogActivity.m6547$$Nest$fgetnavigationBarHeight(this.this$0) + CallLogActivity.m6535$$Nest$fgetadditionFloatingButtonOffset(this.this$0);
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public void onBottomOffsetChange(float r3) {
            CallLogActivity.m6550$$Nest$fputadditionalFloatingTranslation(this.this$0, Math.max(0.0f, (r3 - CallLogActivity.m6547$$Nest$fgetnavigationBarHeight(r0)) - CallLogActivity.m6535$$Nest$fgetadditionFloatingButtonOffset(this.this$0)));
            CallLogActivity.m6554$$Nest$mcheckUi_floatingButton(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.CallLogActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends TextView {
        private final Paint paint;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass6(Context r1, Theme.ResourcesProvider r2) {
            this.val$resourcesProvider = r2;
            super(r1);
            this.paint = new Paint(1);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r11) {
            this.paint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.val$resourcesProvider), 0.8f));
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(1.0f);
            float r4 = getHeight() / 2.0f;
            Layout r0 = getLayout();
            int r2 = 0;
            int r3 = 0;
        L4:
            if (r2 >= r0.getLineCount()) goto L6;
            r3 = Math.max(r3, (int) r0.getLineWidth(r2));
            r2 = r2 + 1;
            goto L4
        L6:
            float r8 = r3 / 2.0f;
            r11.drawLine(0.0f, r4, ((getWidth() / 2.0f) - r8) - AndroidUtilities.dp(8.0f), r4, this.paint);
            r11.drawLine(((getWidth() / 2.0f) + r8) + AndroidUtilities.dp(8.0f), r4, getWidth(), r4, this.paint);
            super.dispatchDraw(r11);
        }
    }

    /* renamed from: org.telegram.ui.CallLogActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ AtomicBoolean val$changed;
        final /* synthetic */ String val$formatted;
        final /* synthetic */ LinkSpanDrawable.LinksTextView val$linkText;

        public AnonymousClass7(AtomicBoolean r1, LinkSpanDrawable.LinksTextView r2, String r3) {
            this.val$changed = r1;
            this.val$linkText = r2;
            this.val$formatted = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.val$changed.get() == true) goto L6;
            this.val$changed.set(true);
            this.val$linkText.setText(this.val$formatted);
            return;
        }
    }

    /* renamed from: org.telegram.ui.CallLogActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends ShareAlert {
        final /* synthetic */ BottomSheet val$sheet;

        public AnonymousClass8(Context r1, ArrayList r2, String r3, boolean r4, String r5, boolean r6, Theme.ResourcesProvider r7, BottomSheet r8) {
            this.val$sheet = r8;
            super(r1, r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onSend(LongSparseArray<TLRPC.Dialog> r5, int r6, TLRPC.TL_forumTopic r7, boolean r8) {
            if (r8 == true) goto L4;
            return;
        L4:
            int r72 = 1;
            if (r5 != null) goto L7;
        L15:
            int r82 = R.string.InvLinkToChats;
            if (r5 == null) goto L19;
            r72 = r5.size();
        L19:
            String r52 = LocaleController.formatString(r82, new Object[]{LocaleController.formatPluralString("Chats", r72, new Object[0])});
        L20:
            Bulletin r4 = BulletinFactory.of(this.val$sheet.topBulletinContainer, this.resourcesProvider).createSimpleBulletin(R.raw.forward, AndroidUtilities.replaceTags(r52));
            r4.hideAfterBottomSheet = false;
            r4.show();
            return;
        L7:
            if (r5.size() != 1) goto L15;
            long r0 = r5.valueAt(0).id;
            if (r0 != 0) goto L11;
        L14:
            r52 = LocaleController.getString(R.string.InvLinkToSavedMessages);
            goto L20
        L11:
            if (r0 == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L14;
            r52 = LocaleController.formatString(R.string.InvLinkToUser, new Object[]{MessagesController.getInstance(this.currentAccount).getPeerName(r0, true)});
            goto L20
        }
    }

    /* renamed from: org.telegram.ui.CallLogActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends GroupCreateActivity {
        final /* synthetic */ int val$account;
        final /* synthetic */ BaseFragment val$parent;

        /* renamed from: $r8$lambda$F00SF-bmKv03BnxNOavxCXE6D3U, reason: not valid java name */
        public static /* synthetic */ void m6560$r8$lambda$F00SFbmKv03BnxNOavxCXE6D3U(int r7, boolean r8, HashSet r9, BaseFragment r10, TLObject r11, TLRPC.TL_error r12) {
            AndroidUtilities.runOnUIThread(new CallLogActivity$9$$ExternalSyntheticLambda3(r11, r7, r8, r9, r12, r10));
        }

        /* renamed from: $r8$lambda$HNxXsgbSysl7L8tDB-tVrPFkeYc, reason: not valid java name */
        public static /* synthetic */ void m6561$r8$lambda$HNxXsgbSysl7L8tDBtVrPFkeYc(TLObject r8, int r9, boolean r10, HashSet r11, TLRPC.TL_error r12, BaseFragment r13) {
            int r1 = 0;
            if ((r8 instanceof TLRPC.Updates) == false) goto L14;
            TLRPC.Updates r82 = (TLRPC.Updates) r8;
            MessagesController.getInstance(r9).putUsers(r82.users, false);
            MessagesController.getInstance(r9).putChats(r82.chats, false);
            ArrayList r83 = MessagesController.findUpdatesAndRemove(r82, TL_update.TL_updateGroupCall.class);
            int r122 = r83.size();
            TLRPC.GroupCall r6 = null;
        L5:
            if (r1 >= r122) goto L8;
            Object r132 = r83.get(r1);
            r1 = r1 + 1;
            r6 = ((TL_update.TL_updateGroupCall) r132).call;
            goto L5
        L8:
            if (LaunchActivity.instance == null) goto L26;
            if (r6 == null) goto L24;
            TLRPC.TL_inputGroupCall r4 = new TLRPC.TL_inputGroupCall();
            r4.id = r6.id;
            r4.access_hash = r6.access_hash;
            VoIPHelper.joinConference(LaunchActivity.instance, r9, r4, r10, r6, r11);
            return;
        L24:
            return;
        L26:
            return;
        L14:
            if ((r8 instanceof TL_phone.groupCall) == false) goto L20;
            TL_phone.groupCall r84 = (TL_phone.groupCall) r8;
            MessagesController.getInstance(r9).putUsers(r84.users, false);
            MessagesController.getInstance(r9).putChats(r84.chats, false);
            if (LaunchActivity.instance == null) goto L27;
            TLRPC.TL_inputGroupCall r102 = new TLRPC.TL_inputGroupCall();
            TLRPC.GroupCall r123 = r84.call;
            r102.id = r123.id;
            r102.access_hash = r123.access_hash;
            VoIPHelper.joinConference(LaunchActivity.instance, r9, r102, r10, r123, r11);
            return;
        L27:
            return;
        L20:
            if (r12 == null) goto L25;
            BulletinFactory.of(r13).showForError(r12);
            return;
        }

        public static /* synthetic */ void $r8$lambda$O0LH8HKDgZ7dP2FTjz5ZKVY5TeM(AnonymousClass9 r0, int r1, TLRPC.User r2, boolean r3, TLObject r4, TLRPC.TL_error r5) {
            r0.lambda$onCallUsersSelected$1(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$YGNELXTi2_NiypRWqvBZe54Ojzc(AnonymousClass9 r0, TLObject r1, int r2, TLRPC.User r3, boolean r4) {
            r0.lambda$onCallUsersSelected$0(r1, r2, r3, r4);
        }

        public AnonymousClass9(Bundle r1, int r2, BaseFragment r3) {
            this.val$account = r2;
            this.val$parent = r3;
            super(r1);
        }

        private /* synthetic */ void lambda$onCallUsersSelected$0(TLObject r9, int r10, TLRPC.User r11, boolean r12) {
            boolean r1 = false;
            if ((r9 instanceof TLRPC.TL_users_userFull) == false) goto L6;
            TLRPC.TL_users_userFull r92 = (TLRPC.TL_users_userFull) r9;
            MessagesController.getInstance(r10).putUsers(r92.users, false);
            MessagesController.getInstance(r10).putChats(r92.chats, false);
            TLRPC.UserFull r93 = r92.full_user;
        L5:
            TLRPC.UserFull r6 = r93;
            if (r6 != null) goto L9;
        L11:
            VoIPHelper.startCall(r11, r12, r1, getParentActivity(), r6, AccountInstance.getInstance(r10));
            return;
        L9:
            if (r6.video_calls_available == false) goto L11;
            r1 = true;
            goto L11
        L6:
            r93 = null;
            goto L5
        }

        private /* synthetic */ void lambda$onCallUsersSelected$1(int r2, TLRPC.User r3, boolean r4, TLObject r5, TLRPC.TL_error r6) {
            AndroidUtilities.runOnUIThread(new CallLogActivity$9$$ExternalSyntheticLambda2(this, r5, r2, r3, r4));
        }

        @Override // org.telegram.ui.GroupCreateActivity
        public void onCallUsersSelected(HashSet<Long> r8, boolean r9) {
            if (r8.size() != 1) goto L9;
            TLRPC.User r1 = MessagesController.getInstance(this.val$account).getUser(r8.iterator().next());
            TLRPC.UserFull r5 = MessagesController.getInstance(this.val$account).getUserFull(r1.id);
            if (r5 != null) goto L8;
            TLRPC.TL_users_getFullUser r82 = new TLRPC.TL_users_getFullUser();
            r82.id = MessagesController.getInstance(this.val$account).getInputUser(r1.id);
            ConnectionsManager.getInstance(this.val$account).sendRequest(r82, new CallLogActivity$9$$ExternalSyntheticLambda0(this, this.val$account, r1, r9));
            return;
        L8:
            VoIPHelper.startCall(r1, r9, r5.video_calls_available, getParentActivity(), r5, AccountInstance.getInstance(this.val$account));
        L10:
            finishFragment();
            return;
        L9:
            TL_phone.createConferenceCall r92 = new TL_phone.createConferenceCall();
            r92.random_id = Utilities.random.nextInt();
            ConnectionsManager.getInstance(this.val$account).sendRequest(r92, new CallLogActivity$9$$ExternalSyntheticLambda1(this.val$account, r9, r8, this.val$parent));
            goto L10
        }
    }

    public static class CallCell extends FrameLayout {
        private final AvatarsImageView avatarsImageView;
        private final CheckBox2 checkBox;
        private final int currentAccount;
        private final ImageView imageView;
        private final ProfileSearchCell profileSearchCell;

        public static final class Factory extends UItem.UItemFactory<CallCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem of(CallLogRow r1, View.OnClickListener r2) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                r0.clickCallback = r2;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                CallCell r12 = (CallCell) r1;
                r12.set((CallLogRow) r2.object, r2.clickCallback);
                r12.setChecked(r2.checked, false);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public CallCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new CallCell(r1, r3);
            }
        }

        /* renamed from: -$$Nest$fgetprofileSearchCell, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ProfileSearchCell m6562$$Nest$fgetprofileSearchCell(CallCell r0) {
            return r0.profileSearchCell;
        }

        public CallCell(Context r14, int r15) {
            super(r14);
            this.currentAccount = r15;
            ProfileSearchCell r152 = new ProfileSearchCell(r14);
            this.profileSearchCell = r152;
            r152.setCallCellStyle();
            if (LocaleController.isRTL == false) goto L5;
            int r0 = AndroidUtilities.dp(32.0f);
        L7:
            if (LocaleController.isRTL == false) goto L9;
            int r1 = 0;
        L10:
            r152.setPadding(r0, 0, r1, 0);
            if (LocaleController.isRTL == false) goto L13;
            float r02 = 2.0f;
        L14:
            r152.setSublabelOffset(AndroidUtilities.dp(r02), -AndroidUtilities.dp(7.0f));
            addView(r152, LayoutHelper.createFrame(-1, -1.0f));
            AvatarsImageView r153 = new AvatarsImageView(r14, false);
            this.avatarsImageView = r153;
            r153.setAvatarsTextSize(AndroidUtilities.dp(18.0f));
            r153.setStepFactor(0.4f);
            r153.setSize(AndroidUtilities.dp(29.0f));
            r153.setCentered(true);
            r153.setVisibility(8);
            int r4 = 5;
            if (LocaleController.isRTL == false) goto L17;
            int r8 = 5;
        L18:
            addView(r153, LayoutHelper.createFrame(72, -1.0f, r8, -2.0f, 0.0f, 0.0f, 0.0f));
            ImageView r154 = new ImageView(r14);
            this.imageView = r154;
            r154.setColorFilter(Theme.getColor(Theme.key_telegram_color_text), PorterDuff.Mode.SRC_IN);
            r154.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 1));
            r154.setScaleType(ImageView.ScaleType.CENTER);
            r154.setContentDescription(LocaleController.getString(R.string.Call));
            if (LocaleController.isRTL == false) goto L21;
            int r03 = 3;
        L22:
            addView(r154, LayoutHelper.createFrame(48, 48.0f, r03 | 16, 8.0f, 0.0f, 8.0f, 0.0f));
            CheckBox2 r155 = new CheckBox2(r14, 21);
            this.checkBox = r155;
            r155.getCheckBoxBase().setBackgroundColor(Theme.getColor(Theme.key_telegram_color));
            r155.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
            r155.setDrawUnchecked(false);
            r155.setDrawBackgroundAsArc(3);
            if (LocaleController.isRTL == true) goto L26;
            r4 = 3;
        L26:
            addView(r155, LayoutHelper.createFrame(24, 24.0f, r4 | 48, 42.0f, 32.0f, 42.0f, 0.0f));
            return;
        L21:
            r03 = 5;
            goto L22
        L17:
            r8 = 3;
            goto L18
        L13:
            r02 = -2.0f;
            goto L14
        L9:
            r1 = AndroidUtilities.dp(32.0f);
            goto L10
        L5:
            r0 = 0;
            goto L7
        }

        private static ImageSpan iconIn(Context r4) {
            Drawable r42 = r4.getResources().getDrawable(R.drawable.mini_call_in_16).mutate();
            r42.setBounds(0, 0, r42.getIntrinsicWidth(), r42.getIntrinsicHeight());
            r42.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3), PorterDuff.Mode.MULTIPLY));
            return new ImageSpan(r42, 0);
        }

        private static ImageSpan iconMissedIn(Context r4) {
            Drawable r42 = r4.getResources().getDrawable(R.drawable.mini_call_in_16).mutate();
            r42.setBounds(0, 0, r42.getIntrinsicWidth(), r42.getIntrinsicHeight());
            r42.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_fill_RedNormal), PorterDuff.Mode.MULTIPLY));
            return new ImageSpan(r42, 0);
        }

        private static ImageSpan iconMissedOut(Context r4) {
            Drawable r42 = r4.getResources().getDrawable(R.drawable.mini_call_out_16).mutate();
            r42.setBounds(0, 0, r42.getIntrinsicWidth(), r42.getIntrinsicHeight());
            r42.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_fill_RedNormal), PorterDuff.Mode.MULTIPLY));
            return new ImageSpan(r42, 0);
        }

        private static ImageSpan iconOut(Context r4) {
            Drawable r42 = r4.getResources().getDrawable(R.drawable.mini_call_out_16).mutate();
            r42.setBounds(0, 0, r42.getIntrinsicWidth(), r42.getIntrinsicHeight());
            r42.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3), PorterDuff.Mode.MULTIPLY));
            return new ImageSpan(r42, 0);
        }

        public void set(CallLogRow r14, View.OnClickListener r15) {
            ImageView r0 = this.imageView;
            if (r14.video == false) goto L5;
            int r1 = R.drawable.menu_videocall;
        L6:
            r0.setImageResource(r1);
            TLRPC.Message r02 = r14.calls.get(0);
            if (LocaleController.isRTL == false) goto L9;
            String r2 = "\u202b";
        L11:
            if (r14.calls.size() != 1) goto L14;
            SpannableString r3 = new SpannableString(r2 + "  " + LocaleController.formatDateCallLog(r02.date));
        L13:
            SpannableString r9 = r3;
            int r03 = r14.type;
            if (r03 == 0) goto L25;
            if (r03 != 1) goto L19;
            r9.setSpan(iconIn(getContext()), r2.length(), r2.length() + 1, 33);
        L26:
            TLRPC.User r22 = null;
            if (r14.call_id == 0) goto L45;
            StringBuilder r04 = new StringBuilder();
            int r32 = 0;
        L30:
            if (r32 >= Math.min(3, r14.users.size())) goto L35;
            if (r32 <= 0) goto L33;
            r04.append(", ");
        L33:
            r04.append(DialogObject.getShortName(r14.users.get(r32)));
            r32 = r32 + 1;
            goto L30
        L35:
            if (r14.users.size() <= 3) goto L37;
            r04.append(" ");
            r04.append(LocaleController.formatPluralString("AndOther", r14.users.size() - 3, new Object[0]));
        L37:
            ArrayList r33 = new ArrayList(r14.users);
            r33.add(UserConfig.getInstance(this.currentAccount).getCurrentUser());
            this.profileSearchCell.setAllowEmojiStatus(false);
            ProfileSearchCell r5 = this.profileSearchCell;
            if (r14.users.isEmpty() == true) goto L40;
            r22 = r14.users.get(0);
        L40:
            r5.setData(r22, null, r04.toString(), r9, false, false);
            this.avatarsImageView.setVisibility(0);
            this.profileSearchCell.avatarImage.clearImage();
            this.profileSearchCell.dontDrawAvatar = true;
            int r05 = Math.min(3, r33.size());
            int r23 = 0;
        L41:
            AvatarsImageView r4 = this.avatarsImageView;
            if (r23 >= r05) goto L44;
            r4.setObject(r23, this.currentAccount, (TLObject) r33.get(r23));
            r23 = r23 + 1;
            goto L41
        L44:
            r4.commitTransition(false);
        L49:
            this.imageView.setTag(r14);
            this.imageView.setOnClickListener(r15);
            return;
        L45:
            this.profileSearchCell.setAllowEmojiStatus(true);
            ProfileSearchCell r52 = this.profileSearchCell;
            if (r14.users.isEmpty() == true) goto L48;
            r22 = r14.users.get(0);
        L48:
            r52.setData(r22, null, null, r9, false, false);
            this.avatarsImageView.setVisibility(8);
            this.profileSearchCell.dontDrawAvatar = false;
            goto L49
        L19:
            if (r03 == 2) goto L23;
            if (r03 != 3) goto L26;
            r9.setSpan(iconMissedOut(getContext()), r2.length(), r2.length() + 1, 33);
            goto L26
        L23:
            r9.setSpan(iconMissedIn(getContext()), r2.length(), r2.length() + 1, 33);
            goto L26
        L25:
            r9.setSpan(iconOut(getContext()), r2.length(), r2.length() + 1, 33);
            goto L26
        L14:
            r3 = new SpannableString(String.format(r2.concat("  (%d) %s"), new Object[]{Integer.valueOf(r14.calls.size()), LocaleController.formatDateCallLog(r02.date)}));
            goto L13
        L9:
            r2 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L11
        L5:
            r1 = R.drawable.menu_call_create_2_24;
            goto L6
        }

        public void setChecked(boolean r1, boolean r2) {
            CheckBox2 r0 = this.checkBox;
            if (r0 != null) goto L5;
            return;
        L5:
            r0.setChecked(r1, r2);
        }
    }

    public static class CallLogRow {
        public long call_id;
        public final ArrayList<TLRPC.Message> calls;
        public int type;
        public final ArrayList<TLRPC.User> users;
        public boolean video;

        private CallLogRow() {
            this.users = new ArrayList();
            this.calls = new ArrayList();
        }

        public /* synthetic */ CallLogRow(CallLogActivityIA r1) {
            this();
        }
    }

    public class EmptyTextProgressView extends FrameLayout {
        private final TextView emptyTextView1;
        private final TextView emptyTextView2;
        private final RLottieImageView imageView;
        private final View progressView;
        final /* synthetic */ CallLogActivity this$0;

        /* renamed from: $r8$lambda$-r2yXPcVr5CyXI-jl7raCNSCXp8, reason: not valid java name */
        public static /* synthetic */ boolean m6563$r8$lambda$r2yXPcVr5CyXIjl7raCNSCXp8(View r0, MotionEvent r1) {
            return true;
        }

        public static /* synthetic */ void $r8$lambda$OxhmBW6fhTroA3w5W619shCJEUE(EmptyTextProgressView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public EmptyTextProgressView(CallLogActivity r13, Context r14, View r15) {
            this.this$0 = r13;
            super(r14);
            addView(r15, LayoutHelper.createFrame(-1, -1.0f));
            this.progressView = r15;
            RLottieImageView r0 = new RLottieImageView(r14);
            this.imageView = r0;
            r0.setAnimation(R.raw.utyan_call, 110, 110);
            r0.setAutoRepeat(false);
            addView(r0, LayoutHelper.createFrame(110, 110.0f, 17, 52.0f, 17.0f, 52.0f, 60.0f));
            r0.setOnClickListener(new CallLogActivity$EmptyTextProgressView$$ExternalSyntheticLambda0(this));
            TextView r1 = new TextView(r14);
            this.emptyTextView1 = r1;
            r1.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            r1.setText(LocaleController.getString(R.string.MakeYourFirstCall));
            r1.setTextSize(1, 20.0f);
            r1.setTypeface(AndroidUtilities.bold());
            r1.setGravity(17);
            addView(r1, LayoutHelper.createFrame(-1, -2.0f, 17, 17.0f, 40.0f, 17.0f, 0.0f));
            TextView r4 = new TextView(r14);
            this.emptyTextView2 = r4;
            String r132 = LocaleController.formatString(R.string.MakeYourFirstCallHint, new Object[]{Integer.valueOf(r13.getMessagesController().conferenceCallSizeLimit)});
            if (AndroidUtilities.isTablet() == true) goto L5;
        L7:
            r4.setText(r132);
            r4.setTextColor(Theme.getColor(Theme.key_emptyListPlaceholder));
            r4.setTextSize(1, 14.0f);
            r4.setGravity(17);
            r4.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(r4, LayoutHelper.createFrame(-1, -2.0f, 17, 17.0f, 80.0f, 17.0f, 0.0f));
            r15.setAlpha(0.0f);
            r0.setAlpha(0.0f);
            r1.setAlpha(0.0f);
            r4.setAlpha(0.0f);
            setOnTouchListener(new CallLogActivity$EmptyTextProgressView$$ExternalSyntheticLambda1());
            return;
        L5:
            if (AndroidUtilities.isSmallTablet() == true) goto L7;
            r132 = r132.replace('\n', ' ');
            goto L7
        }

        private /* synthetic */ void lambda$new$0(View r2) {
            if (this.imageView.isPlaying() == true) goto L6;
            this.imageView.setProgress(0.0f);
            this.imageView.playAnimation();
            return;
        }

        @Override // android.view.View
        public boolean hasOverlappingRendering() {
            return false;
        }

        public void showProgress() {
            this.imageView.animate().alpha(0.0f).setDuration(150).start();
            this.emptyTextView1.animate().alpha(0.0f).setDuration(150).start();
            this.emptyTextView2.animate().alpha(0.0f).setDuration(150).start();
            this.progressView.animate().alpha(1.0f).setDuration(150).start();
        }

        public void showTextView() {
            this.imageView.animate().alpha(1.0f).setDuration(150).start();
            this.emptyTextView1.animate().alpha(1.0f).setDuration(150).start();
            this.emptyTextView2.animate().alpha(1.0f).setDuration(150).start();
            this.progressView.animate().alpha(0.0f).setDuration(150).start();
            this.imageView.playAnimation();
        }
    }

    public static class GroupCallCell extends FrameLayout {
        private final ProgressButton button;
        private TLRPC.Chat currentChat;
        private final ProfileSearchCell profileSearchCell;

        public static final class Factory extends UItem.UItemFactory<GroupCallCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem of(TLRPC.Chat r1, View.OnClickListener r2) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                r0.clickCallback = r2;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((GroupCallCell) r1).setChat((TLRPC.Chat) r2.object, r2.clickCallback);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public GroupCallCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new GroupCallCell(r1);
            }
        }

        /* renamed from: -$$Nest$fgetcurrentChat, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.Chat m6564$$Nest$fgetcurrentChat(GroupCallCell r0) {
            return r0.currentChat;
        }

        public GroupCallCell(Context r14) {
            super(r14);
            String r0 = LocaleController.getString(R.string.VoipChatJoin);
            ProgressButton r1 = new ProgressButton(r14);
            this.button = r1;
            int r2 = (int) Math.ceil(r1.getPaint().measureText(r0));
            ProfileSearchCell r3 = new ProfileSearchCell(r14);
            this.profileSearchCell = r3;
            r3.setCallCellStyle();
            if (LocaleController.isRTL == false) goto L5;
            int r142 = AndroidUtilities.dp(44.0f) + r2;
        L7:
            if (LocaleController.isRTL == false) goto L9;
            int r4 = 0;
        L10:
            r3.setPadding(r142, 0, r4, 0);
            r3.setSublabelOffset(0, -AndroidUtilities.dp(4.0f));
            addView(r3, LayoutHelper.createFrame(-1, -1.0f));
            r1.setText(r0);
            r1.setTextSize(1, 14.0f);
            r1.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
            r1.setProgressColor(Theme.getColor(Theme.key_featuredStickers_buttonProgress));
            r1.setBackgroundRoundRect(Theme.getColor(Theme.key_telegram_color), Theme.getColor(Theme.key_featuredStickers_addButtonPressed), 16.0f);
            r1.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
            addView(r1, LayoutHelper.createFrameRelatively(-2.0f, 28.0f, 8388661, 0.0f, 16.0f, 14.0f, 0.0f));
            return;
        L9:
            r4 = AndroidUtilities.dp(44.0f) + r2;
            goto L10
        L5:
            r142 = 0;
            goto L7
        }

        public void setChat(TLRPC.Chat r9, View.OnClickListener r10) {
            this.currentChat = r9;
            this.button.setTag(Long.valueOf(r9.id));
            if (ChatObject.isChannel(r9) == false) goto L12;
            if (r9.megagroup == true) goto L12;
            if (ChatObject.isPublic(r9) == true) goto L10;
            String r0 = LocaleController.getString(R.string.ChannelPrivate).toLowerCase();
        L9:
            String r5 = r0;
            this.profileSearchCell.setData(r9, null, null, r5, false, false);
            this.button.setOnClickListener(r10);
            return;
        L10:
            r0 = LocaleController.getString(R.string.ChannelPublic).toLowerCase();
        L12:
            if (r9.has_geo == false) goto L15;
            r0 = LocaleController.getString(R.string.MegaLocation);
            goto L9
        L15:
            if (ChatObject.isPublic(r9) == true) goto L17;
            r0 = LocaleController.getString(R.string.MegaPrivate).toLowerCase();
            goto L9
        L17:
            r0 = LocaleController.getString(R.string.MegaPublic).toLowerCase();
            goto L9
        }
    }

    /* renamed from: $r8$lambda$-dLleP_JXuFgdRE8qZKi2yebA6w, reason: not valid java name */
    public static /* synthetic */ void m6518$r8$lambda$dLleP_JXuFgdRE8qZKi2yebA6w(CallLogActivity r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$deleteAllMessages$15(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$1AAYfCqj63puMb9lzhgTNOeBKdY(String[] r1, FrameLayout r2, LinkSpanDrawable.LinksTextView r3, BottomSheet r4, Theme.ResourcesProvider r5, TLObject r6, TLRPC.TL_error r7) {
        AndroidUtilities.runOnUIThread(new CallLogActivity$$ExternalSyntheticLambda51(r6, r1, r2, r3, r4, r5));
    }

    /* renamed from: $r8$lambda$1EFOwZ77UqkTcqErUyM45x-hGTk, reason: not valid java name */
    public static /* synthetic */ void m6519$r8$lambda$1EFOwZ77UqkTcqErUyM45xhGTk(String[] r0, BottomSheet r1, Theme.ResourcesProvider r2, View r3) {
        AndroidUtilities.addToClipboard(r0[0]);
        BulletinFactory.of(r1.topBulletinContainer, r2).createCopyBulletin(LocaleController.getString(R.string.LinkCopied)).show();
    }

    public static /* synthetic */ void $r8$lambda$1eqLQe7XXil8QKhSc0HOpDp6Sis(String r3, int r4, BottomSheet[] r5) {
        TLRPC.TL_inputGroupCallSlug r0 = new TLRPC.TL_inputGroupCallSlug();
        r0.slug = Uri.parse(r3).getPathSegments().get(r3.getPathSegments().size() - 1);
        VoIPHelper.joinConference(LaunchActivity.instance, r4, r0, false, null);
        r5[0].dismiss();
    }

    /* renamed from: $r8$lambda$2APogG8bzr8lCYioXMwf4Dg-Jr8, reason: not valid java name */
    public static /* synthetic */ void m6520$r8$lambda$2APogG8bzr8lCYioXMwf4DgJr8(CallLogActivity r0, int r1, DialogInterface r2) {
        r0.lambda$onClick$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$2YW0ALavcNSnHzpvbV9GAAQZhLA(BottomSheet r6, Theme.ResourcesProvider r7, FrameLayout r8, String[] r9, Context r10, boolean r11, Runnable r12, View r13) {
        ItemOptions.makeOptions(r6.container, r7, r8).add(R.drawable.msg_copy, LocaleController.getString(R.string.Copy), new CallLogActivity$$ExternalSyntheticLambda48(r9, r6, r7)).add(R.drawable.msg_qrcode, LocaleController.getString(R.string.GetQRCode), new CallLogActivity$$ExternalSyntheticLambda49(r10, r9)).addIf(r11, R.drawable.msg_delete, LocaleController.getString(R.string.RevokeLink), true, r12).show();
    }

    /* renamed from: $r8$lambda$2prmd-rrmA-f0kUoNk0dOqcJaAs, reason: not valid java name */
    public static /* synthetic */ void m6521$r8$lambda$2prmdrrmAf0kUoNk0dOqcJaAs(TLRPC.InputGroupCall r10, int r11, String[] r12, FrameLayout r13, LinkSpanDrawable.LinksTextView r14, BottomSheet r15, Theme.ResourcesProvider r16) {
        TL_phone.toggleGroupCallSettings r0 = new TL_phone.toggleGroupCallSettings();
        r0.call = r10;
        r0.reset_invite_hash = true;
        ConnectionsManager.getInstance(r11).sendRequest(r0, new CallLogActivity$$ExternalSyntheticLambda47(r11, r10, r12, r13, r14, r15, r16));
    }

    public static /* synthetic */ void $r8$lambda$6AhaqvrIiq5wFX29xFU_O67xjQc(Runnable r0, View r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$7dK4xubnFWMf3um7nnA8Xq7QuJk(CallLogActivity r0) {
        r0.lambda$createView$2();
    }

    public static /* synthetic */ void $r8$lambda$BeTGso8e_ARiK037hxSZQvOnB50(CallLogActivity r0) {
        r0.lambda$showItemOptions$43();
    }

    public static /* synthetic */ void $r8$lambda$Bo6gEJhINE42WZ6lKNJrFi9zxuU(CallLogActivity r0, AlertDialog r1, HashSet r2, TLRPC.TL_inputGroupCallInviteMessage r3, boolean r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$onClick$11(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$BsdDJzHH2jXeuHHHw9yzq0gKRJM(String[] r0, BottomSheet r1, Theme.ResourcesProvider r2, View r3) {
        AndroidUtilities.addToClipboard(r0[0]);
        BulletinFactory.of(r1.topBulletinContainer, r2).createCopyBulletin(LocaleController.getString(R.string.LinkCopied)).show();
    }

    /* renamed from: $r8$lambda$CV2cuKbj3p0aPhmFGGE-KQ_kd0w, reason: not valid java name */
    public static /* synthetic */ void m6522$r8$lambda$CV2cuKbj3p0aPhmFGGEKQ_kd0w(CallLogActivity r0) {
        r0.lambda$getThemeDescriptions$22();
    }

    public static /* synthetic */ void $r8$lambda$GHC6S6IidW7fVoaBk9qPej04rH0(int r1, TLRPC.InputGroupCall r2, String[] r3, FrameLayout r4, LinkSpanDrawable.LinksTextView r5, BottomSheet r6, Theme.ResourcesProvider r7, TLObject r8, TLRPC.TL_error r9) {
        if ((r8 instanceof TLRPC.Updates) == false) goto L5;
        MessagesController.getInstance(r1).processUpdates((TLRPC.Updates) r8, false);
    L5:
        TL_phone.exportGroupCallInvite r82 = new TL_phone.exportGroupCallInvite();
        r82.call = r2;
        ConnectionsManager.getInstance(r1).sendRequest(r82, new CallLogActivity$$ExternalSyntheticLambda50(r3, r4, r5, r6, r7));
    }

    /* renamed from: $r8$lambda$J-kbgyAyaq4BFCDu-ln3yQbzsi4, reason: not valid java name */
    public static /* synthetic */ boolean m6523$r8$lambda$JkbgyAyaq4BFCDuln3yQbzsi4(CallLogActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onLongClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$MrvoDL5Z7fGIDocVGeU5cmO2-ho, reason: not valid java name */
    public static /* synthetic */ void m6524$r8$lambda$MrvoDL5Z7fGIDocVGeU5cmO2ho(CallLogActivity r0, View r1) {
        r0.onGroupCallClick(r1);
    }

    public static /* synthetic */ void $r8$lambda$NUg5oR0YGqUioXSsLddOJrppiq8(CallLogActivity r0, int r1, DialogInterface r2) {
        r0.lambda$onCallClick$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$PLg6mSTanxGc9UtnR7bnEEzTVXc(String[] r1, BottomSheet r2, Theme.ResourcesProvider r3) {
        AndroidUtilities.addToClipboard(r1[0]);
        BulletinFactory.of(r2.topBulletinContainer, r3).createCopyBulletin(LocaleController.getString(R.string.LinkCopied)).show();
    }

    public static /* synthetic */ void $r8$lambda$QNeaNZs9dHyaPKY9Uvb0ULRNf5w(FrameLayout r3, AtomicBoolean r4, LinkSpanDrawable.LinksTextView r5, String r6, ValueAnimator r7) {
        float r72 = ((Float) r7.getAnimatedValue()).floatValue();
        float r1 = (Math.abs(r72 - 0.5f) / 5.0f) + 0.9f;
        r3.setScaleX(r1);
        r3.setScaleY(r1);
        if (r72 >= 0.5f) goto L5;
        return;
    L5:
        if (r4.get() == true) goto L9;
        r4.set(true);
        r5.setText(r6);
        return;
    }

    public static /* synthetic */ void $r8$lambda$WRRJWnJhl2ZiUtnCcopBNHFNQYA(BottomSheet r0, Theme.ResourcesProvider r1, ImageView r2, Runnable r3, View r4) {
        ItemOptions.makeOptions(r0.getContainer(), r1, r2).add(R.drawable.menu_link_revoke, LocaleController.getString(R.string.GroupCallCreatedLinkRevoke), r3).setOnTopOfScrim().translate(0.0f, -AndroidUtilities.dp(6.0f)).setDimAlpha(0).show();
    }

    public static /* synthetic */ void $r8$lambda$WnGfn1KzUPCOTMSElJc6HCH2rQI(Context r9, String r10, String[] r11, Theme.ResourcesProvider r12, BottomSheet r13, View r14) {
        new AnonymousClass8(r9, null, r10, false, r11[0], false, r12, r13).show();
    }

    /* renamed from: $r8$lambda$X-P3zZ7XrhNj3c7Y0VBP453XMXs, reason: not valid java name */
    public static /* synthetic */ void m6525$r8$lambda$XP3zZ7XrhNj3c7Y0VBP453XMXs(TLObject r2, String[] r3, FrameLayout r4, LinkSpanDrawable.LinksTextView r5, BottomSheet r6, Theme.ResourcesProvider r7) {
        if ((r2 instanceof TL_phone.exportedGroupCallInvite) == false) goto L9;
        String r22 = ((TL_phone.exportedGroupCallInvite) r2).link;
        r3[0] = r22;
        if (r22.startsWith("https://") == false) goto L7;
        r22 = r22.substring(8);
    L7:
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(220);
        AtomicBoolean r0 = new AtomicBoolean();
        r32.addUpdateListener(new CallLogActivity$$ExternalSyntheticLambda0(r4, r0, r5, r22));
        r32.addListener(new AnonymousClass7(r0, r5, r22));
        r32.start();
        BulletinFactory.of(r6.topBulletinContainer, r7).createSimpleBulletin(R.raw.linkbroken, LocaleController.getString(R.string.GroupCallCreatedLinkRevokedTitle), LocaleController.getString(R.string.GroupCallCreatedLinkRevokedText)).show();
        return;
    }

    public static /* synthetic */ void $r8$lambda$YEkmznIu4o4jKbK1SOhm_YjwDpY(CallLogActivity r0) {
        r0.lambda$createView$4();
    }

    /* renamed from: $r8$lambda$ZLzpwE0SLFWE0G1D-VvgXX0SUoA, reason: not valid java name */
    public static /* synthetic */ void m6526$r8$lambda$ZLzpwE0SLFWE0G1DVvgXX0SUoA(CallLogActivity r0, View r1) {
        r0.lambda$createActionMode$16(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$_0SBMTDZzqEhCFeXNeob46nKQg4(long r2, TLRPC.User r4) {
        if (r4.id != r2) goto L6;
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ boolean $r8$lambda$_WRNyBc9vp0gUodekqiw3i7dFMI(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$b7VR1oxly_J98XQ-jJIEuRQtazc, reason: not valid java name */
    public static /* synthetic */ void m6527$r8$lambda$b7VR1oxly_J98XQjJIEuRQtazc(CallLogActivity r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    /* renamed from: $r8$lambda$bZZLZrc-nfKLwN6U_NV8Mm2huYw, reason: not valid java name */
    public static /* synthetic */ void m6528$r8$lambda$bZZLZrcnfKLwN6U_NV8Mm2huYw(int r1, AlertDialog r2, Context r3, Theme.ResourcesProvider r4, Runnable r5, TLObject r6, TLRPC.TL_error r7) {
        AndroidUtilities.runOnUIThread(new CallLogActivity$$ExternalSyntheticLambda32(r6, r1, r2, r3, r4, r5));
    }

    public static /* synthetic */ void $r8$lambda$eYcPO4NWtGlnlCJLYMBFQqvdDNY(AlertDialog r0, Context r1, int r2, TL_phone.exportGroupCallInvite r3, Theme.ResourcesProvider r4, Runnable r5, TLObject r6, TLRPC.TL_error r7) {
        AndroidUtilities.runOnUIThread(new CallLogActivity$$ExternalSyntheticLambda46(r6, r0, r1, r2, r3, r4, r5));
    }

    public static /* synthetic */ void $r8$lambda$ea3QZUqZjtBp901NxSIT47OqzwY(CallLogActivity r0) {
        r0.lambda$showItemOptions$42();
    }

    /* renamed from: $r8$lambda$gyhVDjUE7-ARRTUf1l96t6o-ff0, reason: not valid java name */
    public static /* synthetic */ void m6529$r8$lambda$gyhVDjUE7ARRTUf1l96t6off0(TLObject r18, int r19, AlertDialog r20, Context r21, Theme.ResourcesProvider r22, Runnable r23) {
        int r2 = 0;
        if ((r18 instanceof TLRPC.Updates) == false) goto L12;
        TLRPC.Updates r0 = (TLRPC.Updates) r18;
        MessagesController.getInstance(r19).putUsers(r0.users, false);
        MessagesController.getInstance(r19).putChats(r0.chats, false);
        ArrayList r02 = MessagesController.findUpdatesAndRemove(r0, TL_update.TL_updateGroupCall.class);
        int r1 = r02.size();
        TLRPC.GroupCall r3 = null;
    L5:
        if (r2 >= r1) goto L7;
        Object r32 = r02.get(r2);
        r2 = r2 + 1;
        r3 = ((TL_update.TL_updateGroupCall) r32).call;
        goto L5
    L7:
        r20.dismiss();
        if (r3 == null) goto L18;
        TLRPC.TL_inputGroupCall r6 = new TLRPC.TL_inputGroupCall();
        r6.id = r3.id;
        r6.access_hash = r3.access_hash;
        showCallLinkSheet(r21, r19, r6, r3.invite_link, r22, true, true);
        AndroidUtilities.runOnUIThread(r23);
        return;
    L18:
        return;
    L12:
        if ((r18 instanceof TL_phone.groupCall) == false) goto L15;
        TL_phone.groupCall r03 = (TL_phone.groupCall) r18;
        MessagesController.getInstance(r19).putUsers(r03.users, false);
        MessagesController.getInstance(r19).putChats(r03.chats, false);
        TL_phone.exportGroupCallInvite r15 = new TL_phone.exportGroupCallInvite();
        TLRPC.TL_inputGroupCall r12 = new TLRPC.TL_inputGroupCall();
        r15.call = r12;
        TLRPC.GroupCall r04 = r03.call;
        r12.id = r04.id;
        r12.access_hash = r04.access_hash;
        ConnectionsManager.getInstance(r19).sendRequest(r15, new CallLogActivity$$ExternalSyntheticLambda37(r20, r21, r19, r15, r22, r23));
        return;
    L15:
        r20.dismiss();
        AndroidUtilities.runOnUIThread(r23);
    }

    public static /* synthetic */ void $r8$lambda$hpKXWDjZF1V9V9vsTbrSFTGoTUk(CallLogActivity r0) {
        r0.blur3_InvalidateBlur();
    }

    public static /* synthetic */ boolean $r8$lambda$j1RuPRJt9VDbdYTsNdijSEZTiRQ(long r2, TLRPC.User r4) {
        if (r4.id != r2) goto L6;
        return true;
    L6:
        return false;
    }

    /* renamed from: $r8$lambda$k3EfdJ6Yoa-m0W237sh_2inFJEA, reason: not valid java name */
    public static /* synthetic */ void m6530$r8$lambda$k3EfdJ6Yoam0W237sh_2inFJEA(TLObject r7, AlertDialog r8, Context r9, int r10, TL_phone.exportGroupCallInvite r11, Theme.ResourcesProvider r12, Runnable r13) {
        if ((r7 instanceof TL_phone.exportedGroupCallInvite) == false) goto L5;
        r8.dismiss();
        showCallLinkSheet(r9, r10, r11.call, ((TL_phone.exportedGroupCallInvite) r7).link, r12, true, true);
    L6:
        AndroidUtilities.runOnUIThread(r13);
        return;
    L5:
        r8.dismiss();
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$kDdpfKUl0qzdsVUkxI8ips4nmMM(CallLogActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kJdBSDpNl7YJgeIvrhxmLv1HnZw(CallLogActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$getCalls$21(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$keLWJ34JC_YQeY7U5TFUwpW2txU(CallLogActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$getCalls$20(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$mAimd8PmCkWDGxrkDc7rEuYEcBU(CallLogActivity r0) {
        r0.lambda$createView$1();
    }

    /* renamed from: $r8$lambda$ofiza-Q8fz8pIkZ0CmoIzuZ1WxU, reason: not valid java name */
    public static /* synthetic */ void m6531$r8$lambda$ofizaQ8fz8pIkZ0CmoIzuZ1WxU(Context r6, String[] r7) {
        QRCodeBottomSheet r0 = new QRCodeBottomSheet(r6, LocaleController.getString(R.string.InviteByQRCode), r7[0], LocaleController.getString(R.string.QRCodeLinkGroupCall), false);
        r0.setCenterAnimation(R.raw.qr_code_logo);
        r0.show();
    }

    public static /* synthetic */ void $r8$lambda$pY9AHgjqkIsyCYvzgFi1AAcjxdk(CallLogActivity r0, boolean r1, boolean[] r2, AlertDialog r3, int r4) {
        r0.lambda$showDeleteAlert$14(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$pojNxASutfhgCzMh6KWO_g3co_E(CallLogActivity r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    /* renamed from: $r8$lambda$px0N_fJNXVle0PhKhZFkMA-qr0M, reason: not valid java name */
    public static /* synthetic */ void m6532$r8$lambda$px0N_fJNXVle0PhKhZFkMAqr0M(CallLogActivity r0) {
        r0.lambda$onClick$9();
    }

    public static /* synthetic */ void $r8$lambda$sWyZT23p7mnjau3NQ_fn3r5oHHY(CallLogActivity r0, CallLogRow r1, View r2) {
        r0.lambda$onCallClick$8(r1, r2);
    }

    /* renamed from: $r8$lambda$uJg-jzr2fTQ0t49ePNCgT-u8vOc, reason: not valid java name */
    public static /* synthetic */ void m6533$r8$lambda$uJgjzr2fTQ0t49ePNCgTu8vOc(CallLogActivity r0, AlertDialog r1, TLObject r2, HashSet r3, TLRPC.TL_inputGroupCallInviteMessage r4, boolean r5, TLRPC.TL_error r6) {
        r0.lambda$onCallClick$5(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$uV1lQwC7qTyx7Jl5yfLxKGDNuYM(boolean[] r3, View r4) {
        boolean r1 = !r3[0];
        r3[0] = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
    }

    public static /* synthetic */ void $r8$lambda$wlqncP4tfRwbQUBs5shx_lOkCSE(CallLogActivity r0) {
        r0.lambda$showItemOptions$41();
    }

    public static /* synthetic */ void $r8$lambda$xG4xj3ItgkUftG33NtSMv7nlYLg(CallLogActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$ylZ-9FX2U9C_6TbXxQPNvgZutew, reason: not valid java name */
    public static /* synthetic */ void m6534$r8$lambda$ylZ9FX2U9C_6TbXxQPNvgZutew(CallLogActivity r0, AlertDialog r1, HashSet r2, TLRPC.TL_inputGroupCallInviteMessage r3, boolean r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$onCallClick$6(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$yytdJp4RWcYs9xvqjsCIAZmjExI(CallLogActivity r0, AlertDialog r1, TLObject r2, HashSet r3, TLRPC.TL_inputGroupCallInviteMessage r4, boolean r5, TLRPC.TL_error r6) {
        r0.lambda$onClick$10(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: -$$Nest$fgetadditionFloatingButtonOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6535$$Nest$fgetadditionFloatingButtonOffset(CallLogActivity r0) {
        return r0.additionFloatingButtonOffset;
    }

    /* renamed from: -$$Nest$fgetcalls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m6536$$Nest$fgetcalls(CallLogActivity r0) {
        return r0.calls;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmptyTextProgressView m6537$$Nest$fgetemptyView(CallLogActivity r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetendReached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6538$$Nest$fgetendReached(CallLogActivity r0) {
        return r0.endReached;
    }

    /* renamed from: -$$Nest$fgetfloatingButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentFloatingButton m6539$$Nest$fgetfloatingButton(CallLogActivity r0) {
        return r0.floatingButton;
    }

    /* renamed from: -$$Nest$fgetfragmentContextViewWrapper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m6540$$Nest$fgetfragmentContextViewWrapper(CallLogActivity r0) {
        return r0.fragmentContextViewWrapper;
    }

    /* renamed from: -$$Nest$fgetheaderShadowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HeaderShadowView m6541$$Nest$fgetheaderShadowView(CallLogActivity r0) {
        return r0.headerShadowView;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlass, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m6542$$Nest$fgetiBlur3SourceGlass(CallLogActivity r0) {
        return r0.iBlur3SourceGlass;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlassFrosted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m6543$$Nest$fgetiBlur3SourceGlassFrosted(CallLogActivity r0) {
        return r0.iBlur3SourceGlassFrosted;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m6544$$Nest$fgetlayoutManager(CallLogActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m6545$$Nest$fgetlistView(CallLogActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetloading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6546$$Nest$fgetloading(CallLogActivity r0) {
        return r0.loading;
    }

    /* renamed from: -$$Nest$fgetnavigationBarHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6547$$Nest$fgetnavigationBarHeight(CallLogActivity r0) {
        return r0.navigationBarHeight;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m6548$$Nest$fgetscrollableViewNoiseSuppressor(CallLogActivity r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fgettopPanelLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DialogsActivityTopPanelLayout m6549$$Nest$fgettopPanelLayout(CallLogActivity r0) {
        return r0.topPanelLayout;
    }

    /* renamed from: -$$Nest$fputadditionalFloatingTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6550$$Nest$fputadditionalFloatingTranslation(CallLogActivity r0, float r1) {
        r0.additionalFloatingTranslation = r1;
    }

    /* renamed from: -$$Nest$fputiBlur3Invalidated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6551$$Nest$fputiBlur3Invalidated(CallLogActivity r0, boolean r1) {
        r0.iBlur3Invalidated = r1;
    }

    /* renamed from: -$$Nest$fputmainTabsHiddenByScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6552$$Nest$fputmainTabsHiddenByScroll(CallLogActivity r0, boolean r1) {
        r0.mainTabsHiddenByScroll = r1;
    }

    /* renamed from: -$$Nest$mblur3_InvalidateBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6553$$Nest$mblur3_InvalidateBlur(CallLogActivity r0) {
        r0.blur3_InvalidateBlur();
    }

    /* renamed from: -$$Nest$mcheckUi_floatingButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6554$$Nest$mcheckUi_floatingButton(CallLogActivity r0) {
        r0.checkUi_floatingButton();
    }

    /* renamed from: -$$Nest$mcheckUi_listClip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6555$$Nest$mcheckUi_listClip(CallLogActivity r0) {
        r0.checkUi_listClip();
    }

    /* renamed from: -$$Nest$mcheckUi_listViewPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6556$$Nest$mcheckUi_listViewPadding(CallLogActivity r0) {
        r0.checkUi_listViewPadding();
    }

    /* renamed from: -$$Nest$mgetCalls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6557$$Nest$mgetCalls(CallLogActivity r0, int r1, int r2) {
        r0.getCalls(r1, r2);
    }

    /* renamed from: -$$Nest$mhideActionMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6558$$Nest$mhideActionMode(CallLogActivity r0, boolean r1) {
        r0.hideActionMode(r1);
    }

    /* renamed from: -$$Nest$mshowDeleteAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6559$$Nest$mshowDeleteAlert(CallLogActivity r0, boolean r1) {
        r0.showDeleteAlert(r1);
    }

    public CallLogActivity() {
        this(null);
    }

    public CallLogActivity(Bundle r5) {
        super(r5);
        int r52 = Build.VERSION.SDK_INT;
        if (r52 < 31) goto L5;
        int r2 = 48;
    L6:
        this.ADDITIONAL_LIST_HEIGHT_DP = r2;
        this.needFinishFragment = true;
        this.actionModeViews = new ArrayList();
        this.calls = new ArrayList();
        this.selectedIds = new ArrayList();
        this.ID_CREATE_CALL = 1;
        this.ID_SHOW_IN_MAIN_TABS = 2;
        this.hideCallTabsHintWasShown = false;
        this.tmpClipRect = new Rect();
        ArrayList<RectF> r0 = new ArrayList();
        this.iBlur3Positions = r0;
        RectF r22 = new RectF();
        this.iBlur3PositionActionBar = r22;
        RectF r3 = new RectF();
        this.iBlur3PositionMainTabs = r3;
        r0.add(r22);
        r0.add(r3);
        BlurredBackgroundSourceColor r02 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceColor = r02;
        r02.setColor(getThemedColor(Theme.key_windowBackgroundGray));
        if (r52 < 31) goto L10;
        this.scrollableViewNoiseSuppressor = new DownscaleScrollableNoiseSuppressor();
        this.iBlur3SourceGlassFrosted = new BlurredBackgroundSourceRenderNode(null);
        BlurredBackgroundSourceRenderNode r53 = new BlurredBackgroundSourceRenderNode(null);
        this.iBlur3SourceGlass = r53;
        BlurredBackgroundDrawableViewFactory r03 = new BlurredBackgroundDrawableViewFactory(r53);
        this.iBlur3FactoryLiquidGlass = r03;
        r03.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
        return;
    L10:
        this.scrollableViewNoiseSuppressor = null;
        this.iBlur3SourceGlassFrosted = null;
        this.iBlur3SourceGlass = null;
        this.iBlur3FactoryLiquidGlass = new BlurredBackgroundDrawableViewFactory(r02);
        return;
    L5:
        r2 = 0;
        goto L6
    }

    public static /* synthetic */ ActionBar access$000(CallLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(CallLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$200(CallLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(CallLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$400(CallLogActivity r0) {
        return r0.actionBar;
    }

    private boolean addOrRemoveSelectedDialog(ArrayList<TLRPC.Message> r7, CallCell r8) {
        int r1 = 0;
        if (r7.isEmpty() == false) goto L6;
        return false;
    L6:
        if (isSelected(r7) == false) goto L12;
        int r0 = r7.size();
        int r3 = 0;
    L8:
        if (r3 >= r0) goto L10;
        this.selectedIds.remove(Integer.valueOf(r7.get(r3).id));
        r3 = r3 + 1;
        goto L8
    L10:
        r8.setChecked(false, true);
        showOrUpdateActionMode();
        return false;
    L12:
        int r02 = r7.size();
    L13:
        if (r1 >= r02) goto L18;
        Integer r32 = Integer.valueOf(r7.get(r1).id);
        if (this.selectedIds.contains(r32) == true) goto L17;
        this.selectedIds.add(r32);
    L17:
        r1 = r1 + 1;
        goto L13
    L18:
        r8.setChecked(true, true);
        showOrUpdateActionMode();
        return true;
    }

    private void blur3_InvalidateBlur() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L18;
        int r1 = AndroidUtilities.dp(48.0f) + ((int) this.topPanelLayout.getAnimatedHeightWithPadding(AndroidUtilities.dp(7.0f)));
        this.iBlur3PositionActionBar.set(0.0f, -r1, this.fragmentView.getMeasuredWidth(), this.actionBar.getMeasuredHeight() + r1);
        MainTabsUiHelper.setBlurBounds(this.iBlur3PositionMainTabs, this.fragmentView, this.navigationBarHeight);
        RectF r12 = this.iBlur3PositionMainTabs;
        if (LiteMode.isEnabled(262144) == false) goto L10;
        float r0 = 0.0f;
    L11:
        r12.inset(0.0f, r0);
        DownscaleScrollableNoiseSuppressor r02 = this.scrollableViewNoiseSuppressor;
        ArrayList<RectF> r13 = this.iBlur3Positions;
        if (this.hasMainTabs == false) goto L14;
        int r2 = 2;
    L15:
        r02.setupRenderNodes(r13, r2);
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.iBlur3Capture, this.fragmentView.getMeasuredWidth(), this.fragmentView.getMeasuredHeight());
        return;
    L14:
        r2 = 1;
        goto L15
    L10:
        r0 = -AndroidUtilities.dp(48.0f);
        goto L11
    }

    private void checkUi_floatingButton() {
        this.floatingButton.setTranslationY(((-this.navigationBarHeight) - this.additionFloatingButtonOffset) - this.additionalFloatingTranslation);
    }

    private void checkUi_listClip() {
        if (this.listView.hasActiveEdgeEffects() == false) goto L6;
        this.listView.setClipBounds(null);
        return;
    L6:
        this.tmpClipRect.set(0, AndroidUtilities.dp(this.ADDITIONAL_LIST_HEIGHT_DP) + this.actionBar.getMeasuredHeight(), this.listView.getMeasuredWidth(), this.listView.getMeasuredHeight() - AndroidUtilities.dp(this.ADDITIONAL_LIST_HEIGHT_DP));
        this.listView.setClipBounds(this.tmpClipRect);
    }

    private void checkUi_listViewPadding() {
        int r0 = MainTabsUiHelper.getFloatingTabsPadding(this.hasMainTabs);
        this.listView.setPadding(0, (AndroidUtilities.dp(this.ADDITIONAL_LIST_HEIGHT_DP) + this.actionBar.getMeasuredHeight()) + ((int) this.topPanelLayout.getAnimatedHeightWithPadding(AndroidUtilities.dp(14.0f))), 0, ((AndroidUtilities.dp(this.ADDITIONAL_LIST_HEIGHT_DP) + this.navigationBarHeight) + this.additionNavigationBarHeight) + r0);
        this.emptyView.setPadding(0, 0, 0, (this.navigationBarHeight + this.additionNavigationBarHeight) + r0);
    }

    private void createActionMode() {
        if (this.actionBar.actionModeIsExist(null) == false) goto L5;
        return;
    L5:
        ActionBarMenu r0 = this.actionBar.createActionMode();
        if (this.hasMainTabs == false) goto L8;
        ImageView r1 = new ImageView(getContext());
        this.actionModeCloseView = r1;
        r1.setScaleType(ImageView.ScaleType.CENTER);
        this.actionModeCloseView.setImageDrawable(new BackDrawable(true));
        this.actionModeCloseView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarActionModeDefaultIcon), PorterDuff.Mode.MULTIPLY));
        this.actionModeCloseView.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_actionBarActionModeDefaultSelector)));
        this.actionModeCloseView.setOnClickListener(new CallLogActivity$$ExternalSyntheticLambda19(this));
        r0.addView(this.actionModeCloseView, LayoutHelper.createLinear(54, 54, 16));
        this.actionModeViews.add(this.actionModeCloseView);
    L8:
        NumberTextView r12 = new NumberTextView(r0.getContext());
        this.selectedDialogsCountTextView = r12;
        int r2 = 18;
        r12.setTextSize(18);
        this.selectedDialogsCountTextView.setTypeface(AndroidUtilities.bold());
        this.selectedDialogsCountTextView.setTextColor(Theme.getColor(Theme.key_actionBarActionModeDefaultIcon));
        NumberTextView r13 = this.selectedDialogsCountTextView;
        if (this.hasMainTabs == true) goto L12;
        r2 = 72;
    L12:
        r0.addView(r13, LayoutHelper.createLinear(0, -1, 1.0f, r2, 0, 0, 0));
        this.selectedDialogsCountTextView.setOnTouchListener(new CallLogActivity$$ExternalSyntheticLambda20());
        this.actionModeViews.add(r0.addItemWithWidth(2, R.drawable.msg_delete, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.Delete)));
    }

    public static void createCallLink(Context r8, int r9, Theme.ResourcesProvider r10, Runnable r11) {
        AlertDialog r2 = new AlertDialog(r8, 3);
        r2.showDelayed(500);
        TL_phone.createConferenceCall r6 = new TL_phone.createConferenceCall();
        r6.random_id = Utilities.random.nextInt();
        ConnectionsManager.getInstance(r9).sendRequest(r6, new CallLogActivity$$ExternalSyntheticLambda29(r9, r2, r8, r10, r11));
    }

    private void deleteAllMessages(boolean r4) {
        TLRPC.TL_messages_deletePhoneCallHistory r0 = new TLRPC.TL_messages_deletePhoneCallHistory();
        r0.revoke = r4;
        getConnectionsManager().sendRequest(r0, new CallLogActivity$$ExternalSyntheticLambda35(this, r4));
    }

    private static boolean eq(long r5, ArrayList<TLRPC.User> r7) {
        if (r7.size() == 1) goto L5;
    L7:
        return false;
    L5:
        if (r7.get(0).id != r5) goto L7;
        return true;
    }

    private static boolean eq(Set<Long> r5, ArrayList<TLRPC.User> r6) {
        if (r5.size() == r6.size()) goto L5;
        return false;
    L5:
        int r0 = r6.size();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L10;
        TLRPC.User r3 = r6.get(r1);
        r1 = r1 + 1;
        if (r5.contains(Long.valueOf(r3.id)) == true) goto L6;
        return false;
    L10:
        return true;
    }

    private void fillItems(ArrayList<UItem> r8, UniversalAdapter r9) {
        boolean r92 = this.activeGroupCalls.isEmpty();
        boolean r0 = this.calls.isEmpty();
        if (r92 == false) goto L5;
        if (r0 == false) goto L5;
    L9:
        int r2 = 0;
        if (r92 == true) goto L21;
        ArrayList<Long> r93 = this.activeGroupCalls;
        int r3 = r93.size();
        int r4 = 0;
    L12:
        if (r4 >= r3) goto L20;
        Long r5 = r93.get(r4);
        r4 = r4 + 1;
        Long r52 = r5;
        if (r52 == null) goto L12;
        TLRPC.Chat r53 = getMessagesController().getChat(r52);
        if (r53 == null) goto L12;
        r8.add(GroupCallCell.Factory.of(r53, new CallLogActivity$$ExternalSyntheticLambda18(this)));
        goto L12
    L20:
        r8.add(UItem.asShadow(null));
    L21:
        if (r0 == true) goto L37;
        ArrayList<CallLogRow> r94 = this.calls;
        int r02 = r94.size();
    L23:
        if (r2 >= r02) goto L26;
        CallLogRow r1 = r94.get(r2);
        r2 = r2 + 1;
        CallLogRow r12 = r1;
        r8.add(CallCell.Factory.of(r12, onCallClick(r12)).setChecked(isSelected(r12.calls)));
        goto L23
    L26:
        if (this.endReached == true) goto L38;
        r8.add(UItem.asFlicker(-1, 8));
        r8.add(UItem.asFlicker(-2, 8));
        r8.add(UItem.asFlicker(-3, 8));
        return;
    L38:
        return;
    L37:
        return;
    L5:
        r8.add(UItem.asButton(1, R.drawable.menu_call_create, LocaleController.getString(R.string.GroupCallCreate2)).accent());
        if (getUserConfig().showCallsTab == true) goto L8;
        r8.add(UItem.asButton(2, R.drawable.menu_add_tab_24, LocaleController.getString(R.string.GroupCallShowInMainTabs)).accent());
    L8:
        r8.add(UItem.asShadow(null));
        goto L9
    }

    private void getCalls(int r4, int r5) {
        if (this.loading == false) goto L5;
        return;
    L5:
        this.loading = true;
        EmptyTextProgressView r1 = this.emptyView;
        if (r1 != null) goto L8;
    L10:
        UniversalRecyclerView r12 = this.listView;
        if (r12 == null) goto L13;
        r12.adapter.update(true);
    L13:
        TLRPC.TL_messages_search r0 = new TLRPC.TL_messages_search();
        r0.limit = r5;
        r0.peer = new TLRPC.TL_inputPeerEmpty();
        r0.filter = new TLRPC.TL_inputMessagesFilterPhoneCalls();
        r0.q = _UrlKt.FRAGMENT_ENCODE_SET;
        r0.offset_id = r4;
        int r42 = getConnectionsManager().sendRequest(r0, new CallLogActivity$$ExternalSyntheticLambda1(this), 2);
        getConnectionsManager().bindRequestToGuid(r42, this.classGuid);
        return;
    L8:
        if (this.firstLoaded == true) goto L10;
        r1.showProgress();
        goto L10
    }

    private void hideActionMode(boolean r6) {
        this.actionBar.hideActionMode();
        this.selectedIds.clear();
        int r0 = this.listView.getChildCount();
        int r2 = 0;
    L3:
        UniversalRecyclerView r3 = this.listView;
        if (r2 >= r0) goto L9;
        View r32 = r3.getChildAt(r2);
        if ((r32 instanceof CallCell) == false) goto L8;
        ((CallCell) r32).setChecked(false, r6);
    L8:
        r2 = r2 + 1;
        goto L3
    L9:
        r3.adapter.update(true);
    }

    private boolean isSelected(ArrayList<TLRPC.Message> r6) {
        int r0 = r6.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (this.selectedIds.contains(Integer.valueOf(r6.get(r2).id)) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private /* synthetic */ void lambda$createActionMode$16(View r1) {
        hideActionMode(true);
    }

    private /* synthetic */ void lambda$createView$0(View r1) {
        showItemOptions();
    }

    private /* synthetic */ void lambda$createView$1() {
        checkUi_listClip();
        blur3_InvalidateBlur();
    }

    private /* synthetic */ void lambda$createView$2() {
        this.listView.postOnAnimation(new CallLogActivity$$ExternalSyntheticLambda16(this));
    }

    private /* synthetic */ void lambda$createView$3(View r1) {
        openCreateCall();
    }

    private /* synthetic */ void lambda$createView$4() {
        blur3_InvalidateBlur();
        checkUi_listViewPadding();
    }

    private /* synthetic */ void lambda$deleteAllMessages$15(boolean r3, TLObject r4, TLRPC.TL_error r5) {
        if (r4 == null) goto L7;
        TLRPC.TL_messages_affectedFoundMessages r42 = (TLRPC.TL_messages_affectedFoundMessages) r4;
        TL_update.TL_updateDeleteMessages r52 = new TL_update.TL_updateDeleteMessages();
        r52.messages = r42.messages;
        r52.pts = r42.pts;
        r52.pts_count = r42.pts_count;
        TLRPC.TL_updates r0 = new TLRPC.TL_updates();
        r0.updates.add(r52);
        getMessagesController().processUpdates(r0, false);
        if (r42.offset == 0) goto L8;
        deleteAllMessages(r3);
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$getCalls$20(TLRPC.TL_error r18, TLObject r19) {
        int r1 = 0;
        int r2 = 1;
        if (r18 != null) goto L130;
        TLRPC.messages_Messages r3 = (TLRPC.messages_Messages) r19;
        MessagesController.getInstance(this.currentAccount).putUsers(r3.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r3.chats, false);
        this.endReached = r3.messages.isEmpty();
        if (this.calls.isEmpty() == true) goto L7;
        ArrayList<CallLogRow> r4 = this.calls;
        CallLogRow r42 = r4.get(r4.size() - 1);
    L8:
        int r6 = 0;
    L10:
        if (r6 >= r3.messages.size()) goto L123;
        TLRPC.Message r7 = r3.messages.get(r6);
        TLRPC.MessageAction r8 = r7.action;
        if (r8 != null) goto L14;
    L15:
        TLRPC.messages_Messages r192 = r3;
        int r16 = r6;
    L122:
        r6 = r16 + 1;
        r3 = r192;
        r2 = 1;
        goto L10
    L14:
        if ((r8 instanceof TLRPC.TL_messageActionHistoryClear) == true) goto L15;
        long r82 = MessageObject.getFromChatId(r7);
        if (r82 != getUserConfig().getClientUserId()) goto L19;
        r82 = r7.peer_id.user_id;
    L19:
        HashSet r10 = new HashSet();
        if (MessageObject.getFromChatId(r7) != getUserConfig().getClientUserId()) goto L22;
        int r11 = 0;
    L23:
        TLRPC.MessageAction r12 = r7.action;
        if ((r12 instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L81;
        TLRPC.TL_messageActionConferenceCall r122 = (TLRPC.TL_messageActionConferenceCall) r12;
        r10.add(Long.valueOf(r82));
        r10.addAll((Collection) r122.other_participants.stream().map(new CallLogActivity$$ExternalSyntheticLambda12()).collect(Collectors.toSet()));
        if (r11 == r2) goto L28;
    L30:
        if (r11 == 0) goto L32;
    L34:
        int r14 = r11;
    L35:
        r192 = r3;
        if (r42 == null) goto L40;
        int r112 = r14;
        if (r42.call_id != r122.call_id) goto L41;
        CallLogRow r32 = r42;
        r16 = r6;
    L49:
        if (r32 == null) goto L61;
        r32.calls.add(0, r7);
        Iterator r22 = r10.iterator();
    L52:
        if (r22.hasNext() == false) goto L122;
        Long r5 = (Long) r22.next();
        long r62 = r5.longValue();
        ArrayList<TLRPC.User> r83 = r32.users;
        int r9 = r83.size();
        int r102 = 0;
    L54:
        if (r102 >= r9) goto L58;
        TLRPC.User r113 = r83.get(r102);
        r102 = r102 + 1;
        if (r62 != r113.id) goto L54;
    L58:
        TLRPC.User r52 = getMessagesController().getUser(r5);
        if (r52 == null) goto L52;
        r32.users.add(r52);
        goto L52
    L61:
        if (r42 != null) goto L63;
    L65:
        r42 = new CallLogRow(null);
        r42.call_id = r122.call_id;
        r42.calls.clear();
        r42.calls.add(r7);
        Iterator r23 = r10.iterator();
    L67:
        if (r23.hasNext() == false) goto L73;
        Long r33 = (Long) r23.next();
        r33.longValue();
        if (r42.users.stream().noneMatch(new CallLogActivity$$ExternalSyntheticLambda26(r82)) == false) goto L67;
        TLRPC.User r34 = getMessagesController().getUser(r33);
        if (r34 == null) goto L67;
        r42.users.add(r34);
        goto L67
    L73:
        r42.type = r112;
        TLRPC.MessageAction r24 = r7.action;
        if (r24 != null) goto L76;
    L78:
        boolean r13 = false;
    L79:
        r42.video = r13;
        goto L122
    L76:
        if (r24.video == false) goto L78;
        r13 = true;
        goto L79
    L63:
        if (this.calls.contains(r42) == true) goto L65;
        this.calls.add(r42);
    L41:
        int r25 = 0;
    L43:
        if (r25 >= this.calls.size()) goto L48;
        r32 = this.calls.get(r25);
        r16 = r6;
        if (r32.call_id == r122.call_id) goto L49;
        r25 = r25 + 1;
        r6 = r16;
        goto L43
    L48:
        r16 = r6;
        r32 = null;
        goto L49
    L40:
        r112 = r14;
        goto L41
    L32:
        if (r122.missed == false) goto L34;
        r14 = 3;
        goto L35
    L28:
        if (r122.missed == false) goto L30;
        r11 = 2;
        goto L30
    L81:
        r192 = r3;
        r16 = r6;
        r10.add(Long.valueOf(r82));
        TLRPC.PhoneCallDiscardReason r26 = r7.action.reason;
        if (r11 == 1) goto L84;
    L88:
        if (r11 == 0) goto L90;
    L94:
        int r142 = r11;
    L95:
        if (r42 != null) goto L97;
    L102:
        if (r42 != null) goto L104;
    L106:
        r42 = new CallLogRow(null);
        r42.calls.clear();
        Iterator r35 = r10.iterator();
    L108:
        if (r35.hasNext() == false) goto L114;
        Long r53 = (Long) r35.next();
        r53.longValue();
        if (r42.users.stream().noneMatch(new CallLogActivity$$ExternalSyntheticLambda27(r82)) == false) goto L108;
        TLRPC.User r54 = getMessagesController().getUser(r53);
        if (r54 == null) goto L108;
        r42.users.add(r54);
        goto L108
    L114:
        r42.type = r142;
        TLRPC.MessageAction r36 = r7.action;
        if (r36 != null) goto L117;
    L119:
        boolean r37 = false;
    L120:
        r42.video = r37;
    L121:
        r42.calls.add(r7);
        goto L122
    L117:
        if (r36.video == false) goto L119;
        r37 = true;
        goto L120
    L104:
        if (this.calls.contains(r42) == true) goto L106;
        this.calls.add(r42);
        goto L106
    L97:
        if (eq(r10, r42.users) == false) goto L102;
        if (r42.type == r142) goto L121;
    L90:
        if ((r26 instanceof TLRPC.TL_phoneCallDiscardReasonMissed) == false) goto L92;
    L93:
        r142 = 3;
        goto L95
    L92:
        if ((r26 instanceof TLRPC.TL_phoneCallDiscardReasonBusy) == false) goto L94;
    L84:
        if ((r26 instanceof TLRPC.TL_phoneCallDiscardReasonMissed) == false) goto L86;
    L87:
        r11 = 2;
        goto L88
    L86:
        if ((r26 instanceof TLRPC.TL_phoneCallDiscardReasonBusy) == false) goto L88;
    L22:
        r11 = r2;
        goto L23
    L123:
        if (r42 != null) goto L125;
    L129:
        boolean r132 = true;
    L131:
        this.loading = false;
        if (this.firstLoaded == true) goto L134;
        resumeDelayedFragmentAnimation();
    L134:
        this.firstLoaded = r132;
        ActionBarMenuItem r27 = this.otherItem;
        if (this.calls.isEmpty() == true) goto L137;
    L139:
        r27.setVisibility(r1);
        EmptyTextProgressView r15 = this.emptyView;
        if (r15 == null) goto L142;
        r15.showTextView();
    L142:
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L173;
        r0.adapter.update(true);
        return;
    L173:
        return;
    L137:
        if (getUserConfig().showCallsTab == true) goto L139;
        r1 = 8;
        goto L139
    L125:
        if (r42.calls.isEmpty() == true) goto L129;
        if (this.calls.contains(r42) == true) goto L129;
        this.calls.add(r42);
        goto L129
    L7:
        r42 = null;
        goto L8
    L130:
        r132 = true;
        this.endReached = true;
        goto L131
    }

    private /* synthetic */ void lambda$getCalls$21(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new CallLogActivity$$ExternalSyntheticLambda17(this, r3, r2));
    }

    private /* synthetic */ void lambda$getThemeDescriptions$22() {
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L10;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof CallCell) == false) goto L9;
        CallCell.m6562$$Nest$fgetprofileSearchCell((CallCell) r3).update(0);
    L9:
        r2 = r2 + 1;
    L10:
        ImageView r03 = this.actionModeCloseView;
        if (r03 == null) goto L13;
        r03.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarActionModeDefaultIcon), PorterDuff.Mode.MULTIPLY));
        this.actionModeCloseView.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_actionBarActionModeDefaultSelector)));
    L13:
        ActionBar r5 = this.actionBar;
        if (r5 == null) goto L20;
        r5.updateColors();
        return;
    }

    private /* synthetic */ void lambda$onCallClick$5(AlertDialog r2, TLObject r3, HashSet r4, TLRPC.TL_inputGroupCallInviteMessage r5, boolean r6, TLRPC.TL_error r7) {
        r2.dismiss();
        if ((r3 instanceof TL_phone.groupCall) == false) goto L10;
        TL_phone.groupCall r32 = (TL_phone.groupCall) r3;
        getMessagesController().putUsers(r32.users, false);
        getMessagesController().putChats(r32.chats, false);
        if (r32.participants.isEmpty() == false) goto L8;
        showDialog(new CreateGroupCallSheet(getContext(), r4));
        return;
    L8:
        VoIPHelper.joinConference(getParentActivity(), this.currentAccount, r5, r6, r32.call);
        return;
    L10:
        if (r7 != null) goto L12;
    L15:
        if (r7 == null) goto L18;
        BulletinFactory.of(this).showForError(r7);
        return;
    L18:
        return;
    L12:
        if ("GROUPCALL_INVALID".equalsIgnoreCase(r7.text) == false) goto L15;
        showDialog(new CreateGroupCallSheet(getContext(), r4));
    }

    private /* synthetic */ void lambda$onCallClick$6(AlertDialog r9, HashSet r10, TLRPC.TL_inputGroupCallInviteMessage r11, boolean r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new CallLogActivity$$ExternalSyntheticLambda36(this, r9, r13, r10, r11, r12, r14));
    }

    private /* synthetic */ void lambda$onCallClick$7(int r1, DialogInterface r2) {
        getConnectionsManager().cancelRequest(r1, true);
    }

    private /* synthetic */ void lambda$onCallClick$8(CallLogRow r12, View r13) {
        if (r12.users.size() != 1) goto L14;
        TLRPC.User r5 = r12.users.get(0);
        TLRPC.UserFull r2 = getMessagesController().getUserFull(r5.id);
        this.lastCallUser = r5;
        boolean r6 = r12.video;
        if (r6 == true) goto L11;
        if (r2 != null) goto L8;
    L10:
        boolean r7 = false;
    L12:
        VoIPHelper.startCall(r5, r6, r7, getParentActivity(), null, getAccountInstance());
        return;
    L8:
        if (r2.video_calls_available == false) goto L10;
    L11:
        r7 = true;
        goto L12
    L14:
        boolean r52 = r12.video;
        HashSet r22 = new HashSet();
        ArrayList<TLRPC.User> r4 = r12.users;
        int r62 = r4.size();
        int r72 = 0;
    L15:
        if (r72 >= r62) goto L17;
        TLRPC.User r8 = r4.get(r72);
        r72 = r72 + 1;
        r22.add(Long.valueOf(r8.id));
        goto L15
    L17:
        TLRPC.TL_inputGroupCallInviteMessage r42 = new TLRPC.TL_inputGroupCallInviteMessage();
        r42.msg_id = r12.calls.get(0).id;
        AlertDialog r23 = new AlertDialog(getContext(), 3);
        TL_phone.getGroupCall r63 = new TL_phone.getGroupCall();
        r63.call = r42;
        r63.limit = getMessagesController().conferenceCallSizeLimit;
        r23.setOnCancelListener(new CallLogActivity$$ExternalSyntheticLambda34(this, getConnectionsManager().sendRequest(r63, new CallLogActivity$$ExternalSyntheticLambda33(this, r23, r22, r42, r52))));
        r23.showDelayed(600);
    }

    private /* synthetic */ void lambda$onClick$10(AlertDialog r2, TLObject r3, HashSet r4, TLRPC.TL_inputGroupCallInviteMessage r5, boolean r6, TLRPC.TL_error r7) {
        r2.dismiss();
        if ((r3 instanceof TL_phone.groupCall) == false) goto L10;
        TL_phone.groupCall r32 = (TL_phone.groupCall) r3;
        getMessagesController().putUsers(r32.users, false);
        getMessagesController().putChats(r32.chats, false);
        if (r32.participants.isEmpty() == false) goto L8;
        showDialog(new CreateGroupCallSheet(getContext(), r4));
        return;
    L8:
        VoIPHelper.joinConference(getParentActivity(), this.currentAccount, r5, r6, r32.call);
        return;
    L10:
        if (r7 != null) goto L12;
    L15:
        if (r7 == null) goto L18;
        BulletinFactory.of(this).showForError(r7);
        return;
    L18:
        return;
    L12:
        if ("GROUPCALL_INVALID".equalsIgnoreCase(r7.text) == false) goto L15;
        showDialog(new CreateGroupCallSheet(getContext(), r4));
    }

    private /* synthetic */ void lambda$onClick$11(AlertDialog r9, HashSet r10, TLRPC.TL_inputGroupCallInviteMessage r11, boolean r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new CallLogActivity$$ExternalSyntheticLambda28(this, r9, r13, r10, r11, r12, r14));
    }

    private /* synthetic */ void lambda$onClick$12(int r1, DialogInterface r2) {
        getConnectionsManager().cancelRequest(r1, true);
    }

    private /* synthetic */ void lambda$onClick$9() {
        setCallsTabVisible(false);
    }

    private /* synthetic */ void lambda$showDeleteAlert$14(boolean r10, boolean[] r11, AlertDialog r12, int r13) {
        if (r10 == false) goto L8;
        deleteAllMessages(r11[0]);
        this.calls.clear();
        this.loading = false;
        this.endReached = true;
        if (getUserConfig().showCallsTab == true) goto L7;
        this.otherItem.setVisibility(8);
    L7:
        this.listView.adapter.update(true);
    L9:
        hideActionMode(false);
        return;
    L8:
        getMessagesController().deleteMessages(new ArrayList(this.selectedIds), null, null, 0, 0, r11[0], 0);
        goto L9
    }

    private /* synthetic */ void lambda$showItemOptions$41() {
        setCallsTabVisible(true);
    }

    private /* synthetic */ void lambda$showItemOptions$42() {
        setCallsTabVisible(false);
        if (this.calls.isEmpty() == false) goto L6;
        this.otherItem.setVisibility(8);
    L6:
        if (this.hasMainTabs == false) goto L9;
        BulletinFactory r0 = BulletinFactory.global();
    L8:
        BulletinFactory r1 = r0;
        r1.createSimpleBulletin(R.raw.contact_check, AndroidUtilities.replaceTags(LocaleController.getString(R.string.GroupCallTabWasHiddenTitle)), LocaleController.getString(R.string.UndoNoCaps), 5000, true, new CallLogActivity$$ExternalSyntheticLambda25(this)).setDuration(5000).show();
        return;
    L9:
        r0 = BulletinFactory.of(this);
        goto L8
    }

    private /* synthetic */ void lambda$showItemOptions$43() {
        showDeleteAlert(true);
    }

    private View.OnClickListener onCallClick(CallLogRow r2) {
        return new CallLogActivity$$ExternalSyntheticLambda24(this, r2);
    }

    private void onClick(UItem r9, View r10, int r11, float r12, float r13) {
        int r112 = r9.id;
        if (r112 != 2) goto L6;
        setCallsTabVisible(true);
        this.otherItem.setVisibility(0);
        BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, AndroidUtilities.replaceTags(LocaleController.getString(R.string.GroupCallTabWasShownTitle)), LocaleController.getString(R.string.UndoNoCaps), 5000, true, new CallLogActivity$$ExternalSyntheticLambda21(this)).setDuration(5000).show();
        return;
    L6:
        if (r112 != 1) goto L9;
        openCreateCall();
        return;
    L9:
        Object r92 = r9.object;
        if ((r92 instanceof CallLogRow) == false) goto L27;
        CallLogRow r93 = (CallLogRow) r92;
        if (this.actionBar.isActionModeShowed() == false) goto L16;
        addOrRemoveSelectedDialog(r93.calls, (CallCell) r10);
        return;
    L16:
        if (r93.call_id != 0) goto L18;
    L24:
        Bundle r8 = new Bundle();
        r8.putLong("user_id", MessageObject.getDialogId(r93.calls.get(0)));
        r8.putInt("message_id", r93.calls.get(0).id);
        getNotificationCenter().postNotificationName(NotificationCenter.closeChats, new Object[0]);
        presentFragment(new ChatActivity(r8), this.needFinishFragment);
        return;
    L18:
        if (r93.calls.isEmpty() == true) goto L24;
        boolean r6 = r93.video;
        HashSet r4 = new HashSet();
        ArrayList<TLRPC.User> r102 = r93.users;
        int r113 = r102.size();
        int r122 = 0;
    L20:
        if (r122 >= r113) goto L22;
        TLRPC.User r132 = r102.get(r122);
        r122 = r122 + 1;
        r4.add(Long.valueOf(r132.id));
        goto L20
    L22:
        TLRPC.TL_inputGroupCallInviteMessage r5 = new TLRPC.TL_inputGroupCallInviteMessage();
        r5.msg_id = r93.calls.get(0).id;
        AlertDialog r3 = new AlertDialog(getContext(), 3);
        TL_phone.getGroupCall r94 = new TL_phone.getGroupCall();
        r94.call = r5;
        r94.limit = getMessagesController().conferenceCallSizeLimit;
        r3.setOnCancelListener(new CallLogActivity$$ExternalSyntheticLambda23(this, getConnectionsManager().sendRequest(r94, new CallLogActivity$$ExternalSyntheticLambda22(this, r3, r4, r5, r6))));
        r3.showDelayed(600);
        return;
    L27:
        if ((r10 instanceof GroupCallCell) == false) goto L31;
        Bundle r82 = new Bundle();
        r82.putLong("chat_id", GroupCallCell.m6564$$Nest$fgetcurrentChat((GroupCallCell) r10).id);
        getNotificationCenter().postNotificationName(NotificationCenter.closeChats, new Object[0]);
        presentFragment(new ChatActivity(r82), this.needFinishFragment);
        return;
    }

    private void onGroupCallClick(View r12) {
        Long r122 = (Long) r12.getTag();
        ChatObject.Call r0 = getMessagesController().getGroupCall(r122.longValue(), false);
        TLRPC.Chat r4 = getMessagesController().getChat(r122);
        this.lastCallChat = r4;
        if (r0 == null) goto L6;
        VoIPHelper.startCall(r4, null, null, false, getParentActivity(), this, getAccountInstance());
        return;
    L6:
        this.waitingForCallChatId = r122;
        getMessagesController().loadFullChat(r122.longValue(), 0, true);
    }

    private boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        Object r12 = r1.object;
        if ((r12 instanceof CallLogRow) == false) goto L6;
        addOrRemoveSelectedDialog(((CallLogRow) r12).calls, (CallCell) r2);
        return true;
    L6:
        return false;
    }

    private void openCreateCall() {
        openCreateCall(this);
    }

    public static void openCreateCall(BaseFragment r3) {
        Bundle r0 = new Bundle();
        r0.putBoolean("isCall", true);
        r3.presentFragment(new AnonymousClass9(r0, r3.getCurrentAccount(), r3));
    }

    private void setCallsTabVisible(boolean r2) {
        if (r2 != getUserConfig().showCallsTab) goto L5;
        return;
    L5:
        getUserConfig().setShowCallsTab(r2);
        this.listView.adapter.update(true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.callTabsVisibleToggled, new Object[0]);
    }

    public static void showCallLinkSheet(Context r32, int r33, TLRPC.InputGroupCall r34, String r35, Theme.ResourcesProvider r36, boolean r37, boolean r38) {
        BottomSheet.Builder r0 = new BottomSheet.Builder(r32, false, r36, Theme.getColor(Theme.key_dialogBackground, r36));
        String[] r12 = {r35};
        LinearLayout r3 = new LinearLayout(r32);
        r3.setOrientation(1);
        r3.setPadding(0, 0, 0, AndroidUtilities.dp(8.0f));
        FrameLayout r6 = new FrameLayout(r32);
        r6.setClipChildren(false);
        r6.setClipToPadding(false);
        r3.addView(r6, LayoutHelper.createLinear(-1, 92, 17, 0, 0, 0, 0));
        FrameLayout r7 = new FrameLayout(r32);
        ImageView r9 = new ImageView(r32);
        ImageView.ScaleType r10 = ImageView.ScaleType.CENTER;
        r9.setScaleType(r10);
        r9.setImageResource(R.drawable.story_link);
        r9.setScaleX(2.0f);
        r9.setScaleY(2.0f);
        r7.addView(r9, LayoutHelper.createFrame(-1, -1, 17));
        r7.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r36)));
        r6.addView(r7, LayoutHelper.createFrame(80, 80.0f, 1, 0.0f, 12.0f, 0.0f, 0.0f));
        ImageView r72 = new ImageView(r32);
        r72.setScaleType(r10);
        r72.setImageResource(R.drawable.ic_ab_other);
        int r11 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r36);
        PorterDuff.Mode r14 = PorterDuff.Mode.SRC_IN;
        r72.setColorFilter(new PorterDuffColorFilter(r11, r14));
        int r92 = Theme.key_listSelector;
        r72.setBackground(Theme.createSelectorDrawable(Theme.getColor(r92, r36)));
        if (r38 == false) goto L5;
        r6.addView(r72, LayoutHelper.createFrame(56, 56.0f, 53, 0.0f, 0.0f, 0.0f, 0.0f));
    L5:
        int r62 = Theme.key_windowBackgroundWhiteBlackText;
        LinkSpanDrawable.LinksTextView r112 = TextHelper.makeLinkTextView(r32, 20.0f, r62, true, r36);
        r112.setText(LocaleController.getString(R.string.GroupCallCreatedLinkTitle));
        r112.setGravity(17);
        r3.addView(r112, LayoutHelper.createLinear(-1, -2, 17, 32, 16, 32, 8));
        LinkSpanDrawable.LinksTextView r15 = TextHelper.makeLinkTextView(r32, 14.0f, r62, false, r36);
        r15.setText(LocaleController.getString(R.string.GroupCallCreatedLinkText));
        r15.setGravity(17);
        r15.setMaxWidth(HintView2.cutInFancyHalf(r15.getText(), r15.getPaint()));
        r3.addView(r15, LayoutHelper.createLinear(-1, -2, 17, 32, 0, 32, 18));
        if (r35.startsWith("https://") == false) goto L8;
        String r113 = r35.substring(8);
    L9:
        FrameLayout r13 = new FrameLayout(r32);
        ScaleStateListAnimator.apply(r13, 0.01f, 1.2f);
        int r152 = Theme.key_windowBackgroundGray;
        r13.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(r152, r36), Theme.blendOver(Theme.getColor(r152, r36), Theme.getColor(r92, r36)), 12, 12));
        r3.addView(r13, LayoutHelper.createLinear(-1, -2, 7, 16, 0, 16, 0));
        LinkSpanDrawable.LinksTextView r5 = TextHelper.makeLinkTextView(r32, 13.0f, r62, false, r36);
        r5.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f));
        r5.setText(r113);
        r13.addView(r5, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 30.0f, 0.0f));
        ImageView r73 = new ImageView(r32);
        r73.setImageDrawable(ContextCompat.getDrawable(r32, R.drawable.ic_ab_other));
        r73.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        r73.setScaleType(r10);
        r73.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogTextGray3, r36), r14));
        r13.addView(r73, LayoutHelper.createFrame(40, 48, 21));
        LinearLayout r93 = new LinearLayout(r32);
        r93.setOrientation(0);
        r3.addView(r93, LayoutHelper.createLinear(-1, -2, 16.0f, 12.0f, 16.0f, 0.0f));
        ButtonWithCounterView r102 = new ButtonWithCounterView(r32, r36);
        SpannableStringBuilder r114 = new SpannableStringBuilder("c ");
        r114.append(LocaleController.getString(R.string.GroupCallCreatedLinkCopy));
        r114.setSpan(new ColoredImageSpan(R.drawable.msg_copy_filled), 0, 1, 33);
        r102.setText(r114, false);
        r93.addView(r102, LayoutHelper.createLinear(-1, 48, 1.0f, 51, 0, 0, 6, 0));
        ButtonWithCounterView r52 = new ButtonWithCounterView(r32, r36);
        SpannableStringBuilder r74 = new SpannableStringBuilder("c ");
        r74.append(LocaleController.getString(R.string.GroupCallCreatedLinkShare));
        r74.setSpan(new ColoredImageSpan(R.drawable.msg_share_filled), 0, 1, 33);
        r52.setText(r74, false);
        r93.addView(r52, LayoutHelper.createLinear(-1, 48, 1.0f, 51, 6, 0, 0, 0));
        BottomSheet[] r4 = new BottomSheet[1];
        if (r37 == false) goto L12;
        AnonymousClass6 r75 = new AnonymousClass6(r32, r36);
        r75.setGravity(17);
        r75.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r36));
        r75.setText(" " + LocaleController.getString(R.string.GroupCallCreatedLinkJoinOr) + " ");
        r75.setTextSize(14.0f);
        r3.addView(r75, LayoutHelper.createLinear(190, -2, 1, 28, 12, 28, 8));
        int r115 = r33;
        CallLogActivity$$ExternalSyntheticLambda38 r76 = new CallLogActivity$$ExternalSyntheticLambda38(r35, r115, r4);
        LinkSpanDrawable.LinksTextView r63 = TextHelper.makeLinkTextView(r32, 14.0f, r62, false, r36);
        r63.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.GroupCallCreatedLinkJoinText), r76), true));
        r63.setGravity(17);
        r63.setMaxWidth(HintView2.cutInFancyHalf(r63.getText(), r63.getPaint()));
        r3.addView(r63, LayoutHelper.createLinear(-1, -2, 17, 32, 8, 32, 12));
        ScaleStateListAnimator.apply(r63, 0.05f, 1.2f);
        r63.setOnClickListener(new CallLogActivity$$ExternalSyntheticLambda39(r76));
    L13:
        r0.setCustomView(r3);
        BottomSheet r153 = r0.show();
        r4[0] = r153;
        r13.setOnClickListener(new CallLogActivity$$ExternalSyntheticLambda40(r12, r153, r36));
        r102.setOnClickListener(new CallLogActivity$$ExternalSyntheticLambda41(r12, r153, r36));
        CallLogActivity$$ExternalSyntheticLambda42 r94 = new CallLogActivity$$ExternalSyntheticLambda42(r34, r115, r12, r13, r5, r153, r36);
        r73.setOnClickListener(new CallLogActivity$$ExternalSyntheticLambda43(r153, r36, r13, r12, r32, r38, r94));
        r52.setOnClickListener(new CallLogActivity$$ExternalSyntheticLambda44(r32, r35, r12, r36, r153));
        if (r38 == false) goto L17;
        r72.setOnClickListener(new CallLogActivity$$ExternalSyntheticLambda45(r153, r36, r72, r94));
        return;
    L17:
        return;
    L12:
        r115 = r33;
        goto L13
    L8:
        r113 = r35;
        goto L9
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void showDeleteAlert(boolean r15) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        if (r15 == false) goto L5;
        r0.setTitle(LocaleController.getString(R.string.DeleteAllCalls));
        r0.setMessage(LocaleController.getString(R.string.DeleteAllCallsText));
    L6:
        boolean[] r2 = {false};
        FrameLayout r4 = new FrameLayout(getParentActivity());
        CheckBoxCell r5 = new CheckBoxCell(getParentActivity(), 1);
        r5.setBackground(Theme.getSelectorDrawable(false));
        r5.setText(LocaleController.getString(R.string.DeleteCallsForEveryone), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        if (LocaleController.isRTL == false) goto L9;
        int r1 = AndroidUtilities.dp(8.0f);
    L11:
        if (LocaleController.isRTL == false) goto L13;
        int r6 = 0;
    L14:
        r5.setPadding(r1, 0, r6, 0);
        r4.addView(r5, LayoutHelper.createFrame(-1, 48.0f, 51, 8.0f, 0.0f, 8.0f, 0.0f));
        r5.setOnClickListener(new CallLogActivity$$ExternalSyntheticLambda30(r2));
        r0.setView(r4);
        r0.setPositiveButton(LocaleController.getString(R.string.Delete), new CallLogActivity$$ExternalSyntheticLambda31(this, r15, r2));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r152 = r0.create();
        showDialog(r152);
        TextView r14 = (TextView) r152.getButton(-1);
        if (r14 == null) goto L18;
        r14.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L18:
        return;
    L13:
        r6 = AndroidUtilities.dp(8.0f);
        goto L14
    L9:
        r1 = 0;
        goto L11
    L5:
        r0.setTitle(LocaleController.getString(R.string.DeleteCalls));
        r0.setMessage(LocaleController.getString(R.string.DeleteSelectedCallsText));
        goto L6
    }

    private void showItemOptions() {
        ItemOptions r1 = ItemOptions.makeOptions(this, this.otherItem);
        r1.setDimAlpha(8);
        r1.addIf(getUserConfig().showCallsTab, R.drawable.msg_archive_hide, LocaleController.getString(R.string.HideCallTab), new CallLogActivity$$ExternalSyntheticLambda14(this));
        r1.addIf(!this.calls.isEmpty(), R.drawable.msg_delete, LocaleController.getString(R.string.DeleteAllCalls), true, new CallLogActivity$$ExternalSyntheticLambda15(this));
        r1.show();
        r1.setTranslationY(-AndroidUtilities.dp(64.0f));
    }

    private void showOrUpdateActionMode() {
        if (this.actionBar.isActionModeShowed() == false) goto L8;
        boolean r1 = true;
        if (this.selectedIds.isEmpty() == false) goto L13;
        hideActionMode(true);
        return;
    L13:
        this.selectedDialogsCountTextView.setNumber(this.selectedIds.size(), r1);
        return;
    L8:
        createActionMode();
        this.actionBar.showActionMode();
        AnimatorSet r0 = new AnimatorSet();
        ArrayList r12 = new ArrayList();
        int r3 = 0;
    L10:
        if (r3 >= this.actionModeViews.size()) goto L12;
        View r4 = this.actionModeViews.get(r3);
        r4.setPivotY(ActionBar.getCurrentActionBarHeight() / 2.0f);
        AndroidUtilities.clearDrawableAnimation(r4);
        r12.add(ObjectAnimator.ofFloat(r4, View.SCALE_Y, new float[]{0.1f, 1.0f}));
        r3 = r3 + 1;
        goto L10
    L12:
        r0.playTogether(r12);
        r0.setDuration(200);
        r0.start();
        r1 = false;
        goto L13
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public boolean canParentTabsSlide(MotionEvent r1, boolean r2) {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ActionBar createActionBar(Context r3) {
        ActionBar r2 = super.createActionBar(r3);
        r2.setUseContainerForTitles();
        r2.createAdditionalSubTitleOverlayContainer();
        r2.getAdditionalSubTitleOverlayContainer().setTranslationX(AndroidUtilities.dp(4.0f));
        r2.getAdditionalSubTitleOverlayContainer().setTranslationY(-AndroidUtilities.dp(2.0f));
        r2.getTitlesContainer().setTranslationX(AndroidUtilities.dp(4.0f));
        r2.setAddToContainer(false);
        return r2;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public View createView(Context r17) {
        this.additionNavigationBarHeight = MainTabsUiHelper.getAdditionalNavigationBarHeight(this.hasMainTabs);
        this.additionFloatingButtonOffset = MainTabsUiHelper.getTabsFabOffset(this.hasMainTabs);
        if (this.hasMainTabs == true) goto L5;
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
    L5:
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.Calls));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenuItem r0 = this.actionBar.createMenu().addItem(10, R.drawable.ic_ab_other);
        this.otherItem = r0;
        r0.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        this.otherItem.setOnClickListener(new CallLogActivity$$ExternalSyntheticLambda2(this));
        UniversalRecyclerView r02 = new UniversalRecyclerView(this, new CallLogActivity$$ExternalSyntheticLambda3(this), new CallLogActivity$$ExternalSyntheticLambda4(this), new CallLogActivity$$ExternalSyntheticLambda5(this));
        this.listView = r02;
        int r4 = Theme.key_windowBackgroundGray;
        r02.setBackgroundColor(Theme.getColor(r4, this.resourceProvider));
        this.listView.setSections();
        this.listView.adapter.setApplyBackground(false);
        this.contentView = new AnonymousClass2(this, r17);
        this.iBlur3FactoryLiquidGlass.setSourceRootView(new ViewPositionWatcher(this.contentView), this.contentView);
        UniversalRecyclerView r5 = this.listView;
        SizeNotifierFrameLayout r6 = this.contentView;
        Objects.requireNonNull(r5);
        this.iBlur3Capture = new ViewGroupPartRenderer(r5, r6, new CallLogActivity$$ExternalSyntheticLambda6(r5));
        this.listView.addEdgeEffectListener(new CallLogActivity$$ExternalSyntheticLambda7(this));
        SizeNotifierFrameLayout r03 = this.contentView;
        this.fragmentView = r03;
        r03.setBackgroundColor(Theme.getColor(r4));
        FlickerLoadingView r04 = new FlickerLoadingView(r17);
        this.flickerLoadingView = r04;
        r04.setViewType(8);
        this.flickerLoadingView.setBackgroundColor(Theme.getColor(r4));
        this.flickerLoadingView.showDate(false);
        EmptyTextProgressView r05 = new EmptyTextProgressView(this, r17, this.flickerLoadingView);
        this.emptyView = r05;
        this.contentView.addView(r05, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setClipToPadding(false);
        this.listView.setEmptyView(this.emptyView);
        UniversalRecyclerView r06 = this.listView;
        LinearLayoutManager r42 = new LinearLayoutManager(r17, 1, false);
        this.layoutManager = r42;
        r06.setLayoutManager(r42);
        UniversalRecyclerView r07 = this.listView;
        if (LocaleController.isRTL == false) goto L8;
        int r43 = 1;
    L9:
        r07.setVerticalScrollbarPosition(r43);
        RecyclerAnimationScrollHelper r08 = new RecyclerAnimationScrollHelper(this.listView, this.layoutManager);
        this.scrollHelper = r08;
        r08.setScrollListener(new CallLogActivity$$ExternalSyntheticLambda8(this));
        SizeNotifierFrameLayout r09 = this.contentView;
        UniversalRecyclerView r44 = this.listView;
        int r52 = this.ADDITIONAL_LIST_HEIGHT_DP;
        r09.addView(r44, LayoutHelper.createFrame(-1, -1.0f, 3, 0.0f, -r52, 0.0f, -r52));
        this.listView.setOnScrollListener(new AnonymousClass3(this));
        boolean r010 = this.loading;
        EmptyTextProgressView r45 = this.emptyView;
        if (r010 == false) goto L12;
        r45.showProgress();
    L13:
        FragmentFloatingButton r011 = new FragmentFloatingButton(r17, this.resourceProvider);
        this.floatingButton = r011;
        r011.imageView.setImageResource(R.drawable.filled_calls_plus);
        this.floatingButton.setContentDescription(LocaleController.getString(R.string.Call));
        this.floatingButton.setOnClickListener(new CallLogActivity$$ExternalSyntheticLambda9(this));
        this.contentView.addView(this.floatingButton, FragmentFloatingButton.createDefaultLayoutParams());
        DialogsActivityTopPanelLayout r012 = new DialogsActivityTopPanelLayout(r17);
        this.topPanelLayout = r012;
        r012.setPadding(AndroidUtilities.dp(11.0f), AndroidUtilities.dp(21.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(21.0f));
        this.topPanelLayout.setOnAnimatedHeightChangedListener(new CallLogActivity$$ExternalSyntheticLambda10(this));
        BlurredBackgroundDrawable r013 = this.iBlur3FactoryLiquidGlass.create(this.topPanelLayout, BlurredBackgroundProviderImpl.topPanel(this.resourceProvider));
        r013.setRadius(AndroidUtilities.dp(24.0f));
        r013.setPadding(AndroidUtilities.dp(7.0f));
        this.topPanelLayout.setBlurredBackground(r013);
        FrameLayout r014 = new FrameLayout(r17);
        this.fragmentContextViewWrapper = r014;
        this.topPanelLayout.addView(r014);
        this.topPanelLayout.setViewVisible(this.fragmentContextViewWrapper, true, false);
        AnonymousClass4 r015 = new AnonymousClass4(this, r17, this, this.contentView, false, this.resourceProvider);
        this.fragmentContextView = r015;
        this.fragmentContextViewWrapper.addView(r015);
        this.topPanelLayout.setCallFragmentContextView(this.fragmentContextView);
        this.contentView.addView(this.topPanelLayout, LayoutHelper.createFrame(-1, -2.0f, 48, 0.0f, -14.0f, 0.0f, 0.0f));
        this.contentView.addView(this.actionBar);
        HeaderShadowView r016 = new HeaderShadowView(r17, this.parentLayout);
        this.headerShadowView = r016;
        r016.setShadowVisible(false, false);
        this.contentView.addView(this.headerShadowView, LayoutHelper.createFrame(-1, 5, 48));
        this.actionBar.setDrawBlurBackground(this.contentView);
        this.actionBar.setAdaptiveBackground(this.listView);
        Bulletin.addDelegate(this, new AnonymousClass5(this));
        if (this.hasMainTabs == false) goto L17;
        ViewCompat.setOnApplyWindowInsetsListener(this.fragmentView, new CallLogActivity$$ExternalSyntheticLambda11(this));
    L17:
        return this.fragmentView;
    L12:
        r45.showTextView();
        goto L13
    L8:
        r43 = 2;
        goto L9
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2, types: [int] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4, types: [int] */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    @SuppressLint({"NotifyDataSetChanged"})
    public void didReceivedNotification(int r17, int r18, Object... r19) {
        CallLogActivity r5 = this;
        int r3 = 0;
        boolean r4 = true;
        if (r17 != NotificationCenter.didReceiveNewMessages) goto L108;
        if (r5.firstLoaded == true) goto L8;
        return;
    L8:
        if (((Boolean) r19[2]).booleanValue() == true) goto L207;
        ArrayList r0 = (ArrayList) r19[1];
        int r1 = r0.size();
        int r6 = 0;
    L11:
        if (r6 >= r1) goto L98;
        Object r8 = r0.get(r6);
        r6 = r6 + 1;
        MessageObject r82 = (MessageObject) r8;
        TLRPC.MessageAction r9 = r82.messageOwner.action;
        ?? r11 = 3;
        ?? r112 = 3;
        r11 = 3;
        if ((r9 instanceof TLRPC.TL_messageActionPhoneCall) == true) goto L14;
        if ((r9 instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L97;
        TLRPC.TL_messageActionConferenceCall r92 = (TLRPC.TL_messageActionConferenceCall) r9;
        long r12 = r82.getFromChatId();
        Set r10 = (Set) r92.other_participants.stream().map(new CallLogActivity$$ExternalSyntheticLambda12()).collect(Collectors.toSet());
        if (r12 != r5.getUserConfig().getClientUserId()) goto L52;
        long r14 = r82.messageOwner.peer_id.user_id;
    L53:
        r10.add(Long.valueOf(r14));
        if (r12 != r5.getUserConfig().getClientUserId()) goto L56;
        boolean r122 = false;
    L57:
        if (r122 == r4) goto L59;
    L61:
        if (r122 == false) goto L63;
    L65:
        r112 = r122;
    L67:
        if (r5.calls.isEmpty() == true) goto L90;
        int r123 = 0;
    L70:
        if (r123 >= r5.calls.size()) goto L75;
        CallLogRow r13 = r5.calls.get(r123);
        if (r13.call_id == r92.call_id) goto L76;
        r123 = r123 + 1;
        r5 = this;
    L76:
        if (r13 == null) goto L89;
        r13.calls.add(0, r82.messageOwner);
        Iterator r42 = r10.iterator();
    L79:
        if (r42.hasNext() == false) goto L88;
        Long r52 = (Long) r42.next();
        long r83 = r52.longValue();
        ArrayList<TLRPC.User> r102 = r13.users;
        int r113 = r102.size();
        int r124 = 0;
    L81:
        if (r124 >= r113) goto L85;
        TLRPC.User r142 = r102.get(r124);
        r124 = r124 + 1;
        if (r83 != r142.id) goto L81;
    L85:
        TLRPC.User r53 = getMessagesController().getUser(r52);
        if (r53 == null) goto L79;
        r13.users.add(r53);
        goto L79
    L88:
        r5 = this;
        r5.listView.adapter.update(true);
        r4 = true;
        goto L11
    L89:
        r5 = this;
        goto L90
    L75:
        r13 = null;
    L90:
        CallLogRow r43 = new CallLogRow(null);
        r43.call_id = r92.call_id;
        r43.calls.clear();
        r43.calls.add(r82.messageOwner);
        r43.users.clear();
        Iterator r93 = r10.iterator();
    L92:
        if (r93.hasNext() == false) goto L96;
        Long r103 = (Long) r93.next();
        r103.longValue();
        TLRPC.User r104 = r5.getMessagesController().getUser(r103);
        if (r104 == null) goto L92;
        r43.users.add(r104);
        goto L92
    L96:
        r43.type = r112;
        r43.video = r82.isVideoCall();
        r5.calls.add(0, r43);
        r5.listView.adapter.update(true);
        goto L97
    L63:
        if (r92.missed == false) goto L65;
    L59:
        if (r92.missed == false) goto L61;
        r122 = 2;
        goto L61
    L56:
        r122 = r4;
        goto L57
    L52:
        r14 = r12;
    L97:
        r4 = true;
        goto L11
    L14:
        long r94 = r82.getFromChatId();
        if (r94 != r5.getUserConfig().getClientUserId()) goto L17;
        long r125 = r82.messageOwner.peer_id.user_id;
    L19:
        if (r94 != r5.getUserConfig().getClientUserId()) goto L21;
        boolean r95 = false;
    L22:
        TLRPC.PhoneCallDiscardReason r105 = r82.messageOwner.action.reason;
        if (r95 == r4) goto L25;
    L29:
        if (r95 == false) goto L31;
    L35:
        r11 = r95;
    L37:
        if (r5.calls.isEmpty() == true) goto L43;
        CallLogRow r96 = r5.calls.get(0);
        if (eq(r125, r96.users) == false) goto L43;
        if (r96.type != r11) goto L43;
        r96.calls.add(0, r82.messageOwner);
    L43:
        CallLogRow r97 = new CallLogRow(null);
        r97.calls.clear();
        r97.calls.add(r82.messageOwner);
        r97.users.clear();
        TLRPC.User r106 = r5.getMessagesController().getUser(Long.valueOf(r125));
        if (r106 == null) goto L46;
        r97.users.add(r106);
    L46:
        r97.type = r11;
        r97.video = r82.isVideoCall();
        r5.calls.add(0, r97);
        r5.listView.adapter.update(r4);
        goto L97
    L31:
        if ((r105 instanceof TLRPC.TL_phoneCallDiscardReasonMissed) == true) goto L37;
        if ((r105 instanceof TLRPC.TL_phoneCallDiscardReasonBusy) == false) goto L35;
    L25:
        if ((r105 instanceof TLRPC.TL_phoneCallDiscardReasonMissed) == false) goto L27;
    L28:
        r95 = 2;
        goto L29
    L27:
        if ((r105 instanceof TLRPC.TL_phoneCallDiscardReasonBusy) == false) goto L29;
    L21:
        r95 = r4;
        goto L22
    L17:
        r125 = r94;
        goto L19
    L98:
        ActionBarMenuItem r02 = r5.otherItem;
        if (r02 != null) goto L101;
        return;
    L101:
        if (r5.calls.isEmpty() == true) goto L103;
    L105:
        r02.setVisibility(r3);
        return;
    L103:
        if (r5.getUserConfig().showCallsTab == true) goto L105;
        r3 = 8;
        goto L105
    L207:
        return;
    L108:
        if (r17 != NotificationCenter.messagesDeleted) goto L133;
        if (r5.firstLoaded == true) goto L113;
        return;
    L113:
        if (((Boolean) r19[2]).booleanValue() == true) goto L209;
        ArrayList r03 = (ArrayList) r19[0];
        Iterator<CallLogRow> r15 = r5.calls.iterator();
    L117:
        if (r15.hasNext() == false) goto L127;
        CallLogRow r2 = r15.next();
        Iterator<TLRPC.Message> r44 = r2.calls.iterator();
    L120:
        if (r44.hasNext() == false) goto L125;
        if (r03.contains(Integer.valueOf(r44.next().id)) == false) goto L120;
        r44.remove();
        r3 = 1;
        goto L120
    L125:
        if (r2.calls.isEmpty() == false) goto L117;
        r15.remove();
        goto L117
    L127:
        if (r3 == 0) goto L199;
        UniversalRecyclerView r04 = r5.listView;
        if (r04 == null) goto L200;
        r04.adapter.update(true);
        return;
    L200:
        return;
    L199:
        return;
    L209:
        return;
    L133:
        if (r17 != NotificationCenter.activeGroupCallsUpdated) goto L139;
        r5.activeGroupCalls = r5.getMessagesController().getActiveGroupCalls();
        UniversalRecyclerView r05 = r5.listView;
        if (r05 == null) goto L201;
        r05.adapter.update(true);
        return;
    L201:
        return;
    L139:
        if (r17 != NotificationCenter.chatInfoDidLoad) goto L150;
        Long r06 = r5.waitingForCallChatId;
        if (r06 != null) goto L144;
        return;
    L144:
        if (((TLRPC.ChatFull) r19[0]).id == r06.longValue()) goto L146;
        return;
    L146:
        if (r5.getMessagesController().getGroupCall(r5.waitingForCallChatId.longValue(), true) == null) goto L203;
        VoIPHelper.startCall(r5.lastCallChat, null, null, false, r5.getParentActivity(), r5, r5.getAccountInstance());
        r5.waitingForCallChatId = null;
        return;
    L203:
        return;
    L150:
        if (r17 != NotificationCenter.groupCallUpdated) goto L204;
        Long r07 = r5.waitingForCallChatId;
        if (r07 != null) goto L155;
        return;
    L155:
        if (r07.equals((Long) r19[0]) == false) goto L205;
        VoIPHelper.startCall(r5.lastCallChat, null, null, false, r5.getParentActivity(), r5, r5.getAccountInstance());
        r5.waitingForCallChatId = null;
        return;
    L205:
        return;
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public BlurredBackgroundSourceRenderNode getGlassSource() {
        return this.iBlur3SourceGlass;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        CallLogActivity$$ExternalSyntheticLambda13 r8 = new CallLogActivity$$ExternalSyntheticLambda13(this);
        View r10 = this.fragmentView;
        int r11 = ThemeDescription.FLAG_BACKGROUND;
        int r16 = Theme.key_windowBackgroundGray;
        r1.add(new ThemeDescription(r10, r11, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{EmptyTextProgressView.class}, new String[]{"emptyTextView1"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{EmptyTextProgressView.class}, new String[]{"emptyTextView2"}, null, null, null, Theme.key_emptyListPlaceholder));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LoadingCell.class}, new String[]{"progressBar"}, null, null, null, Theme.key_progressCircle));
        int r24 = Theme.key_windowBackgroundGrayShadow;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r24));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        if (this.floatingButton == null) goto L5;
        r1.add(new ThemeDescription(this.floatingButton.imageView, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chats_actionIcon));
        r1.add(new ThemeDescription(this.floatingButton.imageView, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_chats_actionBackground));
        r1.add(new ThemeDescription(this.floatingButton.imageView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_chats_actionPressedBackground));
    L5:
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{CallCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_telegram_color_text));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{CallCell.class}, null, new Drawable[]{Theme.dialogs_verifiedCheckDrawable}, null, Theme.key_chats_verifiedCheck));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{CallCell.class}, null, new Drawable[]{Theme.dialogs_verifiedDrawable}, null, Theme.key_chats_verifiedBackground));
        TextPaint r29 = Theme.dialogs_offlinePaint;
        int r32 = Theme.key_windowBackgroundWhiteGrayText3;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{CallCell.class}, r29, null, null, r32));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{CallCell.class}, Theme.dialogs_onlinePaint, null, null, Theme.key_windowBackgroundWhiteBlueText3));
        TextPaint[] r4 = Theme.dialogs_namePaint;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{CallCell.class}, null, new Paint[]{r4[0], r4[1], Theme.dialogs_searchNamePaint}, null, null, Theme.key_chats_name));
        TextPaint[] r42 = Theme.dialogs_nameEncryptedPaint;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{CallCell.class}, null, new Paint[]{r42[0], r42[1], Theme.dialogs_searchNameEncryptedPaint}, null, null, Theme.key_chats_secretName));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{CallCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, null, new Drawable[]{this.greenDrawable, this.greenDrawable2, Theme.calllog_msgCallUpRedDrawable, Theme.calllog_msgCallDownRedDrawable}, null, r32));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, null, new Drawable[]{this.redDrawable, this.redDrawable2, Theme.calllog_msgCallUpGreenDrawable, Theme.calllog_msgCallDownGreenDrawable}, null, Theme.key_fill_RedNormal));
        r1.add(new ThemeDescription(this.flickerLoadingView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, r24));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean needDelayOpenAnimation() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (this.actionBar.isActionModeShowed() == false) goto L9;
        if (r2 == false) goto L10;
        hideActionMode(true);
        return false;
    L10:
        return false;
    L9:
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyVisible() {
        super.onBecomeFullyVisible();
        if (this.hideCallTabsHintWasShown == false) goto L5;
        return;
    L5:
        if (getUserConfig().showCallsTab == true) goto L7;
        return;
    L7:
        if (MessagesController.getGlobalMainSettings().getInt("hidecallshint", 0) >= 2) goto L12;
        HintView2 r0 = new HintView2(getContext(), 1);
        this.hideCallTabsHintView = r0;
        r0.setDuration(3000);
        this.hideCallTabsHintView.setJoint(1.0f, -25.0f);
        this.hideCallTabsHintView.setPadding(0, AndroidUtilities.dp(4.0f), 0, 0);
        this.hideCallTabsHintView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.TapToHideCallsTab)));
        this.contentView.addView(this.hideCallTabsHintView, LayoutHelper.createFrame(-1, 80, 48));
        this.hideCallTabsHintView.setTranslationY((AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.dp(16.0f));
        this.hideCallTabsHintView.show();
        this.hideCallTabsHintWasShown = true;
        MessagesController.getGlobalMainSettings().edit().putInt("hidecallshint", MessagesController.getGlobalMainSettings().getInt("hidecallshint", 0) + 1).apply();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        getCalls(0, 50);
        this.activeGroupCalls = getMessagesController().getActiveGroupCalls();
        getNotificationCenter().addObserver(this, NotificationCenter.didReceiveNewMessages);
        getNotificationCenter().addObserver(this, NotificationCenter.messagesDeleted);
        getNotificationCenter().addObserver(this, NotificationCenter.activeGroupCallsUpdated);
        getNotificationCenter().addObserver(this, NotificationCenter.chatInfoDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.groupCallUpdated);
        Bundle r0 = this.arguments;
        if (r0 == null) goto L5;
        this.needFinishFragment = r0.getBoolean("needFinishFragment", true);
        this.hasMainTabs = this.arguments.getBoolean("hasMainTabs", false);
    L5:
        this.additionNavigationBarHeight = MainTabsUiHelper.getAdditionalNavigationBarHeight(this.hasMainTabs);
        this.additionFloatingButtonOffset = MainTabsUiHelper.getTabsFabOffset(this.hasMainTabs);
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.didReceiveNewMessages);
        getNotificationCenter().removeObserver(this, NotificationCenter.messagesDeleted);
        getNotificationCenter().removeObserver(this, NotificationCenter.activeGroupCallsUpdated);
        getNotificationCenter().removeObserver(this, NotificationCenter.chatInfoDidLoad);
        getNotificationCenter().removeObserver(this, NotificationCenter.groupCallUpdated);
        Bulletin.removeDelegate(this);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.navigationBarHeight = r4;
        checkUi_listViewPadding();
        checkUi_floatingButton();
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public void onParentScrollToTop() {
        if (this.layoutManager.findFirstVisibleItemPosition() >= 15) goto L6;
        this.listView.smoothScrollToPosition(0);
        return;
    L6:
        this.scrollHelper.setScrollDirection(1);
        this.scrollHelper.scrollToPosition(0, 0, false, true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r13, String[] r14, int[] r15) {
        if (r13 == 101) goto L8;
        if (r13 == 102) goto L8;
        if (r13 == 103) goto L8;
        return;
    L8:
        int r142 = r15.length;
        int r3 = 0;
    L10:
        if (r3 >= r142) goto L15;
        if (r15[r3] != 0) goto L13;
        r3 = r3 + 1;
        goto L10
    L13:
        boolean r143 = false;
    L16:
        TLRPC.UserFull r32 = null;
        if (r15.length <= 0) goto L38;
        if (r143 == false) goto L38;
        if (r13 != 103) goto L23;
        VoIPHelper.startCall(this.lastCallChat, null, null, false, getParentActivity(), this, getAccountInstance());
        return;
    L23:
        if (this.lastCallUser == null) goto L25;
        r32 = getMessagesController().getUserFull(this.lastCallUser.id);
    L25:
        TLRPC.User r5 = this.lastCallUser;
        if (r13 != 102) goto L28;
        boolean r6 = true;
    L29:
        if (r13 == 102) goto L35;
        if (r32 != null) goto L32;
    L34:
        boolean r7 = false;
    L36:
        VoIPHelper.startCall(r5, r6, r7, getParentActivity(), null, getAccountInstance());
        return;
    L32:
        if (r32.video_calls_available == false) goto L34;
    L35:
        r7 = true;
        goto L36
    L28:
        r6 = false;
    L38:
        VoIPHelper.permissionDenied(getParentActivity(), null, r13);
        return;
    L15:
        r143 = true;
        goto L16
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        this.mainTabsHiddenByScroll = false;
        updateMainTabsVisibility();
        UniversalRecyclerView r1 = this.listView;
        if (r1 == null) goto L6;
        r1.adapter.update(true);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationStart(boolean r1, boolean r2) {
        super.onTransitionAnimationStart(r1, r2);
        if (r1 == false) goto L6;
        this.openTransitionStarted = true;
        return;
    }

    public void setMainTabsActivityController(MainTabsActivityController r1) {
        this.mainTabsActivityController = r1;
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public void updateMainTabsVisibility() {
        MainTabsActivityController r0 = this.mainTabsActivityController;
        if (r0 != null) goto L5;
        return;
    L5:
        if (BottomNavigationBar.visible() == true) goto L7;
    L9:
        boolean r2 = false;
    L10:
        r0.setTabsVisible(r2);
        return;
    L7:
        if (this.mainTabsHiddenByScroll == true) goto L9;
        r2 = true;
        goto L10
    }
}
