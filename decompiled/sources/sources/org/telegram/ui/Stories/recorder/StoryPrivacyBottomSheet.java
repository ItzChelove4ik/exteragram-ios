package org.telegram.ui.Stories.recorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.SlideIntChooseView;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CreateRtmpStreamBottomSheet;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.GroupCreateSpan;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.RadioButton;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.recyclerview.LinearSmoothScrollerCustom;

/* loaded from: classes7.dex */
public class StoryPrivacyBottomSheet extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    private int activePage;
    private boolean allowComments;
    private boolean allowCover;
    private boolean allowScreenshots;
    private boolean allowSmallChats;
    private boolean applyWhenDismiss;
    private final Paint backgroundPaint;
    private boolean canChangePeer;
    private int commentsPrice;
    private Drawable coverDrawable;
    private final ArrayList<Long> excludedContacts;
    private final ArrayList<Long> excludedEveryone;
    private final HashMap<Long, ArrayList<Long>> excludedEveryoneByGroup;
    private int excludedEveryoneCount;
    private boolean isEdit;
    private boolean isLive;
    private boolean isRtmpStream;
    private boolean keepOnMyPage;
    private boolean liveSettings;
    private boolean loadedContacts;
    private final ArrayList<Long> messageUsers;
    private Utilities.Callback<StoryPrivacy> onDismiss;
    private DoneCallback onDone;
    private Utilities.Callback<ArrayList<Long>> onDone2;
    private Utilities.Callback<HashSet<Integer>> onSelectedAlbums;
    private Utilities.Callback<TLRPC.InputPeer> onSelectedPeer;
    private final HashSet<Integer> selectedAlbums;
    private final ArrayList<Long> selectedContacts;
    private final HashMap<Long, ArrayList<Long>> selectedContactsByGroup;
    private int selectedContactsCount;
    public TLRPC.InputPeer selectedPeer;
    private int selectedType;
    private boolean sendAsMessageEnabled;
    private int shiftDp;
    private HashMap<Long, Integer> smallChatsParticipantsCount;
    private boolean startedFromSendAsMessage;
    private int storiesCount;
    private int storyPeriod;
    private ViewPagerFixed viewPager;
    private ArrayList<String> warnUsers;
    private Runnable whenCoverClicked;

    /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewPagerFixed.Adapter {
        final /* synthetic */ StoryPrivacyBottomSheet this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass1(StoryPrivacyBottomSheet r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r1, int r2, int r3) {
            ((Page) r1).bind(r3);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r2) {
            return new Page(this.this$0, this.val$context);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return 2;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemViewType(int r1) {
            if (r1 != 0) goto L6;
            return 0;
        L6:
            return StoryPrivacyBottomSheet.m20244$$Nest$fgetactivePage(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 implements Bulletin.Delegate {
        final /* synthetic */ StoryPrivacyBottomSheet this$0;

        public AnonymousClass2(StoryPrivacyBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getTopOffset(int r1) {
            return AndroidUtilities.statusBarHeight;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends ViewPagerFixed {
        final /* synthetic */ StoryPrivacyBottomSheet this$0;

        public AnonymousClass3(StoryPrivacyBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public boolean canScroll(MotionEvent r6) {
            View r0 = StoryPrivacyBottomSheet.m20276$$Nest$fgetviewPager(this.this$0).getCurrentView();
            if ((r0 instanceof Page) == true) goto L5;
            return true;
        L5:
            if (getCurrentPosition() <= 0) goto L8;
            this.this$0.closeKeyboard();
            return true;
        L8:
            Page r02 = (Page) r0;
            int r62 = r02.getTypeOn(r6);
            if (r62 == (-1)) goto L26;
            StoryPrivacyBottomSheet.m20278$$Nest$fputactivePage(this.this$0, r62);
            if (r62 == 3) goto L13;
            StoryPrivacyBottomSheet r3 = this.this$0;
            if (r62 == 4) goto L20;
            StoryPrivacyBottomSheet.m20286$$Nest$fputselectedType(r3, r62);
        L25:
            r02.updateCheckboxes(true);
            r02.updateButton(true);
            goto L26
        L20:
            if (StoryPrivacyBottomSheet.m20255$$Nest$fgetexcludedEveryone(r3).isEmpty() == true) goto L25;
            if (StoryPrivacyBottomSheet.m20256$$Nest$fgetexcludedEveryoneByGroup(this.this$0).isEmpty() == true) goto L25;
            StoryPrivacyBottomSheet.m20286$$Nest$fputselectedType(this.this$0, r62);
            goto L25
        L13:
            if (StoryPrivacyBottomSheet.m20268$$Nest$fgetselectedContacts(this.this$0).isEmpty() == true) goto L25;
            if (StoryPrivacyBottomSheet.m20269$$Nest$fgetselectedContactsByGroup(this.this$0).isEmpty() == true) goto L25;
            StoryPrivacyBottomSheet.m20286$$Nest$fputselectedType(this.this$0, r62);
        L26:
            if (r62 == (-1)) goto L28;
            this.this$0.closeKeyboard();
        L28:
            if (r62 == (-1)) goto L30;
            return true;
        L30:
            return false;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onItemSelected(View r1, View r2, int r3, int r4) {
            if (StoryPrivacyBottomSheet.access$8700(this.this$0) == false) goto L6;
            this.this$0.closeKeyboard();
            return;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r1) {
            StoryPrivacyBottomSheet.access$8600(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends ViewPagerFixed.Adapter {
        final /* synthetic */ StoryPrivacyBottomSheet this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ int val$singlePageType;

        public AnonymousClass4(StoryPrivacyBottomSheet r1, Context r2, int r3) {
            this.this$0 = r1;
            this.val$context = r2;
            this.val$singlePageType = r3;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r1, int r2, int r3) {
            ((Page) r1).bind(r3);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r2) {
            return new Page(this.this$0, this.val$context);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return 1;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemViewType(int r1) {
            return this.val$singlePageType;
        }
    }

    public static class ChoosePeerSheet extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
        private final Adapter adapter;
        private final int currentAccount;
        private final TextView headerView;
        private final boolean isLive;
        private final RecyclerListView listView;
        private final Utilities.Callback<TLRPC.InputPeer> onPeerSelected;
        private List<TLRPC.InputPeer> peers;
        private final TLRPC.InputPeer selectedPeer;

        /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$ChoosePeerSheet$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            private final Paint backgroundPaint;
            private final AnimatedFloat statusBarT;
            final /* synthetic */ ChoosePeerSheet this$0;
            final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

            public AnonymousClass1(ChoosePeerSheet r8, Context r9, Theme.ResourcesProvider r10) {
                this.this$0 = r8;
                this.val$resourcesProvider = r10;
                super(r9);
                this.backgroundPaint = new Paint(1);
                this.statusBarT = new AnimatedFloat(this, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r8) {
                this.backgroundPaint.setColor(Theme.getColor(Theme.key_dialogBackground, this.val$resourcesProvider));
                float r0 = Math.max(0.0f, ChoosePeerSheet.m20301$$Nest$mtop(this.this$0));
                AnimatedFloat r2 = this.statusBarT;
                if (r0 >= AndroidUtilities.statusBarHeight) goto L5;
                boolean r3 = true;
            L6:
                float r02 = AndroidUtilities.lerp(r0, 0.0f, r2.set(r3));
                RectF r1 = AndroidUtilities.rectTmp;
                r1.set(ChoosePeerSheet.access$9100(this.this$0), r02, getWidth() - ChoosePeerSheet.access$9200(this.this$0), getHeight() + AndroidUtilities.dp(14.0f));
                float r22 = AndroidUtilities.dp(14.0f) * (1.0f - this.statusBarT.get());
                r8.drawRoundRect(r1, r22, r22, this.backgroundPaint);
                ChoosePeerSheet.m20298$$Nest$fgetheaderView(this.this$0).setTranslationY(Math.max(AndroidUtilities.statusBarHeight + AndroidUtilities.dp(8.0f), AndroidUtilities.dp(14.0f) + r02));
                r8.save();
                r8.clipRect(ChoosePeerSheet.access$9300(this.this$0), AndroidUtilities.statusBarHeight + AndroidUtilities.dp(14.0f), getWidth() - ChoosePeerSheet.access$9400(this.this$0), getHeight());
                super.dispatchDraw(r8);
                r8.restore();
                return;
            L5:
                r3 = false;
                goto L6
            }

            @Override // android.view.ViewGroup, android.view.View
            public boolean dispatchTouchEvent(MotionEvent r3) {
                if (r3.getY() >= ChoosePeerSheet.m20301$$Nest$mtop(this.this$0)) goto L7;
                this.this$0.dismiss();
                return true;
            L7:
                return super.dispatchTouchEvent(r3);
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$ChoosePeerSheet$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerView.OnScrollListener {
            final /* synthetic */ ChoosePeerSheet this$0;

            public AnonymousClass2(ChoosePeerSheet r1) {
                this.this$0 = r1;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r1, int r2, int r3) {
                ChoosePeerSheet.access$9500(this.this$0).invalidate();
            }
        }

        public class Adapter extends RecyclerListView.SelectionAdapter {
            final /* synthetic */ ChoosePeerSheet this$0;

            private Adapter(ChoosePeerSheet r1) {
                this.this$0 = r1;
            }

            public /* synthetic */ Adapter(ChoosePeerSheet r1, StoryPrivacyBottomSheetIA r2) {
                this(r1);
            }

            private long did(TLRPC.InputPeer r2) {
                if ((r2 instanceof TLRPC.TL_inputPeerSelf) == false) goto L7;
                return UserConfig.getInstance(ChoosePeerSheet.m20297$$Nest$fgetcurrentAccount(this.this$0)).getClientUserId();
            L7:
                return DialogObject.getPeerDialogId(r2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return ChoosePeerSheet.m20299$$Nest$fgetpeers(this.this$0).size() + 2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r1) {
                if (r1 != 0) goto L6;
                return 0;
            L6:
                if (r1 != 1) goto L8;
                return 1;
            L8:
                return 2;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r1) {
                if (r1.getItemViewType() != 2) goto L6;
                return true;
            L6:
                return false;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
                if (r8.getItemViewType() != 2) goto L29;
                UserCell r82 = (UserCell) r8.itemView;
                boolean r0 = true;
                r82.setIsSendAs(true, true);
                TLRPC.InputPeer r2 = (TLRPC.InputPeer) ChoosePeerSheet.m20299$$Nest$fgetpeers(this.this$0).get(r9 - 2);
                if ((r2 instanceof TLRPC.TL_inputPeerSelf) == false) goto L8;
                r82.setUser(UserConfig.getInstance(ChoosePeerSheet.m20297$$Nest$fgetcurrentAccount(this.this$0)).getCurrentUser());
            L16:
                r82.checkBox.setVisibility(8);
                r82.radioButton.setVisibility(0);
                if (ChoosePeerSheet.m20300$$Nest$fgetselectedPeer(this.this$0) != null) goto L20;
                if (r9 != 2) goto L20;
            L21:
                boolean r1 = true;
            L23:
                r82.setChecked(r1, false);
                if (r9 != (getItemCount() - 1)) goto L27;
                r0 = false;
            L27:
                r82.setDivider(r0);
                return;
            L20:
                if (did(ChoosePeerSheet.m20300$$Nest$fgetselectedPeer(this.this$0)) == did(r2)) goto L21;
                r1 = false;
                goto L23
            L8:
                if ((r2 instanceof TLRPC.TL_inputPeerUser) == false) goto L11;
                r82.setUser(MessagesController.getInstance(ChoosePeerSheet.m20297$$Nest$fgetcurrentAccount(this.this$0)).getUser(Long.valueOf(r2.user_id)));
                goto L16
            L11:
                if ((r2 instanceof TLRPC.TL_inputPeerChat) == false) goto L14;
                r82.setChat(MessagesController.getInstance(ChoosePeerSheet.m20297$$Nest$fgetcurrentAccount(this.this$0)).getChat(Long.valueOf(r2.chat_id)), 0);
                goto L16
            L14:
                if ((r2 instanceof TLRPC.TL_inputPeerChannel) == false) goto L16;
                r82.setChat(MessagesController.getInstance(ChoosePeerSheet.m20297$$Nest$fgetcurrentAccount(this.this$0)).getChat(Long.valueOf(r2.channel_id)), 0);
                goto L16
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
                if (r3 != 0) goto L4;
            L7:
                View r22 = new View(this.this$0.getContext());
                if (r3 != 0) goto L10;
                int r1 = (AndroidUtilities.displaySize.y + AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(306.0f);
            L11:
                r22.setLayoutParams(new RecyclerView.LayoutParams(-1, r1));
            L13:
                return new RecyclerListView.Holder(r22);
            L10:
                r1 = AndroidUtilities.dp(54.0f);
                goto L11
            L4:
                if (r3 == 1) goto L7;
                r22 = new UserCell(this.this$0.getContext(), ChoosePeerSheet.access$9600(this.this$0));
                goto L13
            }
        }

        /* renamed from: $r8$lambda$n3-fTCQAITu8YC5M_ytfMu6Aojs, reason: not valid java name */
        public static /* synthetic */ void m20296$r8$lambda$n3fTCQAITu8YC5M_ytfMu6Aojs(AlertDialog r0, Utilities.Callback r1, TLRPC.InputPeer r2, Boolean r3) {
            r0.dismiss();
            if (r3.booleanValue() == false) goto L7;
            if (r1 == null) goto L8;
            r1.run(r2);
            return;
        L8:
            return;
        }

        public static /* synthetic */ void $r8$lambda$pagQsZYAH4gSipVzled2d6Q35Ss(ChoosePeerSheet r0, Utilities.Callback r1, Theme.ResourcesProvider r2, int r3, View r4, int r5) {
            r0.lambda$new$1(r1, r2, r3, r4, r5);
        }

        /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m20297$$Nest$fgetcurrentAccount(ChoosePeerSheet r0) {
            return r0.currentAccount;
        }

        /* renamed from: -$$Nest$fgetheaderView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m20298$$Nest$fgetheaderView(ChoosePeerSheet r0) {
            return r0.headerView;
        }

        /* renamed from: -$$Nest$fgetpeers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ List m20299$$Nest$fgetpeers(ChoosePeerSheet r0) {
            return r0.peers;
        }

        /* renamed from: -$$Nest$fgetselectedPeer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.InputPeer m20300$$Nest$fgetselectedPeer(ChoosePeerSheet r0) {
            return r0.selectedPeer;
        }

        /* renamed from: -$$Nest$mtop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m20301$$Nest$mtop(ChoosePeerSheet r0) {
            return r0.top();
        }

        public ChoosePeerSheet(Context r3, int r4, boolean r5, TLRPC.InputPeer r6, Utilities.Callback<TLRPC.InputPeer> r7, Theme.ResourcesProvider r8) {
            super(r3, false, r8);
            fixNavigationBar();
            MessagesController.getInstance(r4).getStoriesController().loadSendAs();
            this.isLive = r5;
            this.currentAccount = r4;
            this.peers = MessagesController.getInstance(r4).getStoriesController().sendAs;
            this.selectedPeer = r6;
            this.onPeerSelected = r7;
            this.containerView = new AnonymousClass1(this, r3, r8);
            RecyclerListView r62 = new RecyclerListView(r3, r8);
            this.listView = r62;
            int r1 = this.backgroundPaddingLeft;
            r62.setPadding(r1, 0, r1, 0);
            Adapter r0 = new Adapter(this, null);
            this.adapter = r0;
            r62.setAdapter(r0);
            r62.setLayoutManager(new LinearLayoutManager(r3));
            this.containerView.addView(r62, LayoutHelper.createFrame(-1, -1, 119));
            r62.setOnItemClickListener(new StoryPrivacyBottomSheet$ChoosePeerSheet$$ExternalSyntheticLambda0(this, r7, r8, r4));
            r62.setOnScrollListener(new AnonymousClass2(this));
            TextView r32 = new TextView(getContext());
            this.headerView = r32;
            r32.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r8));
            r32.setTextSize(1, 20.0f);
            r32.setPadding(this.backgroundPaddingLeft + AndroidUtilities.dp(22.0f), AndroidUtilities.dp(2.0f), this.backgroundPaddingLeft + AndroidUtilities.dp(22.0f), AndroidUtilities.dp(14.0f));
            r32.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, r8));
            r32.setTypeface(AndroidUtilities.bold());
            if (r5 == false) goto L5;
            int r42 = R.string.StoryPrivacyPublishLiveAs;
        L6:
            r32.setText(LocaleController.getString(r42));
            this.containerView.addView(r32, LayoutHelper.createFrame(-1, -2.0f));
            return;
        L5:
            r42 = R.string.StoryPrivacyPublishAs;
            goto L6
        }

        public static /* synthetic */ int access$9100(ChoosePeerSheet r0) {
            return r0.backgroundPaddingLeft;
        }

        public static /* synthetic */ int access$9200(ChoosePeerSheet r0) {
            return r0.backgroundPaddingLeft;
        }

        public static /* synthetic */ int access$9300(ChoosePeerSheet r0) {
            return r0.backgroundPaddingLeft;
        }

        public static /* synthetic */ int access$9400(ChoosePeerSheet r0) {
            return r0.backgroundPaddingLeft;
        }

        public static /* synthetic */ ViewGroup access$9500(ChoosePeerSheet r0) {
            return r0.containerView;
        }

        public static /* synthetic */ Theme.ResourcesProvider access$9600(ChoosePeerSheet r0) {
            return r0.resourcesProvider;
        }

        private /* synthetic */ void lambda$new$1(Utilities.Callback r7, Theme.ResourcesProvider r8, int r9, View r10, int r11) {
            if (r11 > 1) goto L5;
            return;
        L5:
            TLRPC.InputPeer r102 = this.peers.get(r11 - 2);
            if (r102.channel_id == 0) goto L8;
        L11:
            AlertDialog r112 = new AlertDialog(getContext(), 3, r8);
            r112.showDelayed(200);
            MessagesController.getInstance(r9).getStoriesController().canSendStoryFor(DialogObject.getPeerDialogId(r102), new StoryPrivacyBottomSheet$ChoosePeerSheet$$ExternalSyntheticLambda1(r112, r7, r102), true, r8);
            dismiss();
            return;
        L8:
            if (r102.chat_id != 0) goto L11;
            r7.run(r102);
            dismiss();
        }

        private float top() {
            float r0 = this.containerView.getMeasuredHeight();
            int r1 = 0;
        L4:
            if (r1 >= this.listView.getChildCount()) goto L14;
            View r2 = this.listView.getChildAt(r1);
            if (r2 == null) goto L13;
            int r3 = this.listView.getChildAdapterPosition(r2);
            if (r3 == (-1)) goto L13;
            if (r3 <= 0) goto L13;
            r0 = Math.min(AndroidUtilities.lerp(r0, r2.getY(), r2.getAlpha()), r0);
        L13:
            r1 = r1 + 1;
            goto L4
        L14:
            return r0;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public boolean canDismissWithSwipe() {
            if (top() <= ((int) (AndroidUtilities.displaySize.y * 0.5f))) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 != NotificationCenter.storiesSendAsUpdate) goto L6;
            this.peers = MessagesController.getInstance(this.currentAccount).getStoriesController().sendAs;
            this.adapter.notifyDataSetChanged();
            return;
        }

        @Override // android.app.Dialog, android.view.Window.Callback
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesSendAsUpdate);
        }

        @Override // android.app.Dialog, android.view.Window.Callback
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesSendAsUpdate);
        }
    }

    public class ContainerView extends FrameLayout {
        private final AnimatedFloat isActionBar;
        private final Path path;
        final /* synthetic */ StoryPrivacyBottomSheet this$0;
        private float top;

        public ContainerView(StoryPrivacyBottomSheet r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.isActionBar = new AnimatedFloat(this, 250, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.path = new Path();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            View[] r0 = StoryPrivacyBottomSheet.m20276$$Nest$fgetviewPager(this.this$0).getViewPages();
            float r1 = 0.0f;
            this.top = 0.0f;
            int r3 = 0;
        L4:
            if (r3 >= r0.length) goto L15;
            View r4 = r0[r3];
            if (r4 == null) goto L14;
            Page r42 = (Page) r4;
            this.top += r42.top() * Utilities.clamp(1.0f - Math.abs(r42.getTranslationX() / r42.getMeasuredWidth()), 1.0f, 0.0f);
            if (StoryPrivacyBottomSheet.access$8800(this.this$0) == false) goto L12;
            int r5 = r42.pageType;
        L12:
            if (r42.getVisibility() != 0) goto L14;
            r42.updateTops();
        L14:
            r3 = r3 + 1;
            goto L4
        L15:
            AnimatedFloat r02 = this.isActionBar;
            if (this.top > AndroidUtilities.statusBarHeight) goto L18;
            r1 = 1.0f;
        L18:
            float r03 = r02.set(r1);
            this.top = Math.max(AndroidUtilities.statusBarHeight, this.top) - (AndroidUtilities.statusBarHeight * r03);
            RectF r12 = AndroidUtilities.rectTmp;
            r12.set(StoryPrivacyBottomSheet.access$8900(this.this$0), this.top, getWidth() - StoryPrivacyBottomSheet.access$9000(this.this$0), getHeight() + AndroidUtilities.dp(8.0f));
            float r04 = AndroidUtilities.lerp(AndroidUtilities.dp(14.0f), 0, r03);
            r9.drawRoundRect(r12, r04, r04, StoryPrivacyBottomSheet.m20250$$Nest$fgetbackgroundPaint(this.this$0));
            r9.save();
            this.path.rewind();
            this.path.addRoundRect(r12, r04, r04, Path.Direction.CW);
            r9.clipPath(this.path);
            super.dispatchDraw(r9);
            r9.restore();
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L9;
            if (r3.getY() >= this.top) goto L9;
            this.this$0.dismiss();
            return true;
        L9:
            return super.dispatchTouchEvent(r3);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
        }
    }

    public interface DoneCallback {
        void done(StoryPrivacy r1, boolean r2, boolean r3, boolean r4, boolean r5, TLRPC.InputPeer r6, int r7, Runnable r8, Runnable r9);
    }

    public static class HeaderCell extends FrameLayout {
        public BackDrawable backDrawable;
        private ImageView closeView;
        private final Paint dividerPaint;
        private Runnable onCloseClickListener;
        private final Theme.ResourcesProvider resourcesProvider;
        private TextView textView;

        /* renamed from: $r8$lambda$0p3m-PjP2795OSvwzIdejqQBefo, reason: not valid java name */
        public static /* synthetic */ void m20302$r8$lambda$0p3mPjP2795OSvwzIdejqQBefo(HeaderCell r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public HeaderCell(Context r13, Theme.ResourcesProvider r14) {
            super(r13);
            this.dividerPaint = new Paint(1);
            this.resourcesProvider = r14;
            TextView r0 = new TextView(r13);
            this.textView = r0;
            r0.setTypeface(AndroidUtilities.bold());
            this.textView.setTextSize(1, 20.0f);
            TextView r02 = this.textView;
            int r2 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r1 = 5;
        L6:
            r02.setGravity(r1);
            this.textView.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r14));
            TextView r142 = this.textView;
            boolean r03 = LocaleController.isRTL;
            if (r03 == false) goto L9;
            float r8 = 16.0f;
        L10:
            if (r03 == false) goto L12;
            float r10 = 53.0f;
        L13:
            addView(r142, LayoutHelper.createFrame(-1, -2.0f, 23, r8, 0.0f, r10, 0.0f));
            ImageView r143 = new ImageView(r13);
            this.closeView = r143;
            BackDrawable r132 = new BackDrawable(false);
            this.backDrawable = r132;
            r143.setImageDrawable(r132);
            this.backDrawable.setColor(-1);
            this.backDrawable.setRotatedColor(-1);
            this.backDrawable.setAnimationTime(220.0f);
            ImageView r133 = this.closeView;
            if (LocaleController.isRTL == false) goto L16;
            r2 = 5;
        L16:
            addView(r133, LayoutHelper.createFrame(24, 24.0f, r2 | 16, 16.0f, 0.0f, 16.0f, 0.0f));
            this.closeView.setOnClickListener(new StoryPrivacyBottomSheet$HeaderCell$$ExternalSyntheticLambda0(this));
            return;
        L12:
            r10 = 16.0f;
            goto L13
        L9:
            r8 = 53.0f;
            goto L10
        L5:
            r1 = 3;
            goto L6
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            Runnable r0 = this.onCloseClickListener;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            super.dispatchDraw(r8);
            this.dividerPaint.setColor(Theme.getColor(Theme.key_divider, this.resourcesProvider));
            r8.drawRect(0.0f, getHeight() - AndroidUtilities.getShadowHeight(), getWidth(), getHeight(), this.dividerPaint);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
        }

        public void setCloseImageVisible(boolean r11) {
            ImageView r0 = this.closeView;
            if (r11 == false) goto L5;
            int r1 = 0;
        L6:
            r0.setVisibility(r1);
            TextView r10 = this.textView;
            boolean r02 = LocaleController.isRTL;
            if (r02 == true) goto L11;
            if (r11 == false) goto L11;
            float r6 = 53.0f;
        L12:
            if (r02 == false) goto L15;
            if (r11 == false) goto L15;
            float r8 = 53.0f;
        L16:
            r10.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, 23, r6, 0.0f, r8, 0.0f));
            return;
        L15:
            r8 = 22.0f;
        L11:
            r6 = 22.0f;
            goto L12
        L5:
            r1 = 8;
            goto L6
        }

        public void setOnCloseClickListener(Runnable r1) {
            this.onCloseClickListener = r1;
        }

        public void setText(CharSequence r1) {
            this.textView.setText(r1);
        }
    }

    public static class HeaderCell2 extends LinearLayout {
        private final Theme.ResourcesProvider resourcesProvider;
        private final TextView subtitleTextView;
        private final TextView titleTextView;

        public HeaderCell2(Context r11, Theme.ResourcesProvider r12, boolean r13) {
            super(r11);
            setOrientation(1);
            this.resourcesProvider = r12;
            TextView r1 = new TextView(r11);
            this.titleTextView = r1;
            r1.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r12));
            r1.setTextSize(1, 20.0f);
            r1.setTypeface(AndroidUtilities.bold());
            if (r13 == false) goto L6;
            int r2 = 4;
        L7:
            addView(r1, LayoutHelper.createLinear(-1, -2, 55, 27, 16, 27, r2));
            TextView r14 = new TextView(r11);
            this.subtitleTextView = r14;
            r14.setTextColor(Theme.getColor(Theme.key_dialogTextGray2, r12));
            r14.setTextSize(1, 14.0f);
            if (r13 == false) goto L11;
            addView(r14, LayoutHelper.createLinear(-1, -2, 55, 27, 0, 27, 13));
            return;
        L11:
            return;
        L6:
            r2 = 13;
            goto L7
        }

        public void setText(CharSequence r2, CharSequence r3) {
            this.titleTextView.setText(r2);
            this.subtitleTextView.setText(r3);
        }
    }

    public static class ItemInner extends AdapterWithDiffUtils.Item {
        public TLRPC.Chat chat;
        public boolean checked;
        public Drawable drawable;
        public boolean halfChecked;
        public int id;
        public int padHeight;
        public boolean red;
        public int resId;
        public boolean sendAs;
        public int subtractHeight;
        public CharSequence text;
        public CharSequence text2;
        public int type;
        public int typeCount;
        public TLRPC.User user;

        private ItemInner(int r1, boolean r2) {
            super(r1, r2);
            this.padHeight = -1;
        }

        public static ItemInner asButton(int r3, CharSequence r4, Drawable r5) {
            ItemInner r0 = new ItemInner(9, false);
            r0.text = r4;
            r0.drawable = r5;
            r0.id = r3;
            return r0;
        }

        public static ItemInner asButton(int r3, CharSequence r4, CharSequence r5) {
            ItemInner r0 = new ItemInner(9, false);
            r0.text = r4;
            r0.text2 = r5;
            r0.id = r3;
            return r0;
        }

        public static ItemInner asChat(TLRPC.Chat r3, boolean r4) {
            ItemInner r0 = new ItemInner(3, true);
            r0.chat = r3;
            r0.checked = r4;
            return r0;
        }

        public static ItemInner asCheck(CharSequence r3, int r4, boolean r5) {
            ItemInner r0 = new ItemInner(7, false);
            r0.resId = r4;
            r0.text = r3;
            r0.checked = r5;
            return r0;
        }

        public static ItemInner asHeader() {
            return new ItemInner(0, false);
        }

        public static ItemInner asHeader2(CharSequence r3, CharSequence r4) {
            ItemInner r0 = new ItemInner(4, false);
            r0.text = r3;
            r0.text2 = r4;
            return r0;
        }

        public static ItemInner asHeader3(CharSequence r3) {
            ItemInner r0 = new ItemInner(11, false);
            r0.text = r3;
            return r0;
        }

        public static ItemInner asHeaderCell(CharSequence r3) {
            ItemInner r0 = new ItemInner(8, false);
            r0.text = r3;
            return r0;
        }

        public static ItemInner asNoUsers() {
            return new ItemInner(5, false);
        }

        public static ItemInner asPad() {
            return asPad(-1);
        }

        public static ItemInner asPad(int r3) {
            ItemInner r0 = new ItemInner(-1, false);
            r0.subtractHeight = r3;
            return r0;
        }

        public static ItemInner asPadding(int r3) {
            ItemInner r0 = new ItemInner(-1, false);
            r0.padHeight = r3;
            return r0;
        }

        public static ItemInner asSearchField() {
            return new ItemInner(1, false);
        }

        public static ItemInner asSection() {
            return new ItemInner(2, false);
        }

        public static ItemInner asShadow(CharSequence r3) {
            ItemInner r0 = new ItemInner(6, false);
            r0.text = r3;
            return r0;
        }

        public static ItemInner asSlider(int r3) {
            ItemInner r0 = new ItemInner(10, false);
            r0.id = r3;
            return r0;
        }

        public static ItemInner asType(int r3, boolean r4, int r5) {
            ItemInner r0 = new ItemInner(3, false);
            r0.type = r3;
            r0.checked = r4;
            r0.typeCount = r5;
            return r0;
        }

        public static ItemInner asUser(TLRPC.User r3, boolean r4, boolean r5) {
            ItemInner r0 = new ItemInner(3, true);
            r0.user = r3;
            r0.checked = r4;
            r0.halfChecked = r5;
            return r0;
        }

        public ItemInner asSendAs() {
            this.sendAs = true;
            return this;
        }

        public boolean equals(Object r6) {
            if (this != r6) goto L6;
            return true;
        L6:
            if (r6 != null) goto L8;
        L93:
            return false;
        L8:
            if (getClass() != r6.getClass()) goto L93;
            ItemInner r62 = (ItemInner) r6;
            int r2 = this.viewType;
            if (r2 == r62.viewType) goto L14;
            return false;
        L14:
            if (r2 != (-1)) goto L21;
            if (this.subtractHeight == r62.subtractHeight) goto L18;
        L19:
            return false;
        L18:
            if (this.padHeight != r62.padHeight) goto L19;
        L21:
            if (r2 == 3) goto L23;
        L37:
            if (r2 != 0) goto L42;
            if (this.resId == r62.resId) goto L42;
            return false;
        L42:
            if (r2 != 2) goto L47;
            if (TextUtils.equals(this.text, r62.text) == true) goto L47;
            return false;
        L47:
            if (this.viewType == 8) goto L49;
        L51:
            int r22 = this.viewType;
            if (r22 == 4) goto L56;
            if (r22 == 11) goto L56;
        L61:
            if (this.viewType != 6) goto L68;
            if (TextUtils.equals(this.text, r62.text) == true) goto L65;
        L66:
            return false;
        L65:
            if (this.resId != r62.resId) goto L66;
        L68:
            if (this.viewType != 7) goto L77;
            if (this.resId == r62.resId) goto L72;
        L75:
            return false;
        L72:
            if (TextUtils.equals(this.text, r62.text) == false) goto L75;
            if (this.checked != r62.checked) goto L75;
        L77:
            if (this.viewType != 9) goto L88;
            if (this.id == r62.id) goto L81;
        L86:
            return false;
        L81:
            if (this.drawable != r62.drawable) goto L86;
            if (TextUtils.equals(this.text, r62.text) == false) goto L86;
            if (TextUtils.equals(this.text2, r62.text2) == false) goto L86;
        L88:
            if (this.viewType == 10) goto L90;
        L92:
            return true;
        L90:
            if (this.id == r62.id) goto L92;
            return false;
        L56:
            if (TextUtils.equals(this.text, r62.text) == false) goto L93;
            if (TextUtils.equals(this.text2, r62.text2) == true) goto L61;
        L49:
            if (TextUtils.equals(this.text, r62.text) == true) goto L51;
            return false;
        L23:
            if (this.user == r62.user) goto L25;
        L36:
            return false;
        L25:
            if (this.chat != r62.chat) goto L36;
            if (this.type != r62.type) goto L36;
            if (this.typeCount != r62.typeCount) goto L36;
            if (this.checked != r62.checked) goto L36;
            if (this.red != r62.red) goto L36;
            if (this.sendAs == r62.sendAs) goto L37;
            goto L36
        }

        public ItemInner red(boolean r1) {
            this.red = r1;
            return this;
        }
    }

    public class Page extends FrameLayout implements View.OnClickListener, NotificationCenter.NotificationCenterDelegate {
        private Adapter adapter;
        private final ArrayList<TLObject> atTop;
        private final ButtonWithCounterView button;
        private final ButtonWithCounterView button2;
        private final ButtonContainer buttonContainer;
        private final LongSparseArray<Boolean> changelog;
        private boolean containsHeader;
        private final FrameLayout contentView;
        private HeaderCell headerView;
        private boolean isActionBar;
        private final ArrayList<ItemInner> items;
        private int keyboardHeight;
        private boolean keyboardMoving;
        private int lastSelectedType;
        private LinearLayoutManager layoutManager;
        private RecyclerListView listView;
        private final ArrayList<ItemInner> oldItems;
        public int pageType;
        private AlertDialog progressDialog;
        private String query;
        private boolean scrolling;
        private SearchUsersCell searchField;
        private ValueAnimator searchFieldAnimator;
        private int searchPosition;
        private boolean searchTranslationAnimating;
        private float searchTranslationAnimatingTo;
        private GraySectionCell sectionCell;
        private final ArrayList<Long> selectedUsers;
        private final HashMap<Long, ArrayList<Long>> selectedUsersByGroup;
        final /* synthetic */ StoryPrivacyBottomSheet this$0;
        private final View underKeyboardView;
        private long waitingForChatId;
        public boolean wasAtBottom;
        public boolean wasAtTop;
        private boolean wasKeyboardVisible;

        /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$Page$1, reason: invalid class name */
        public class AnonymousClass1 extends SearchUsersCell {
            final /* synthetic */ Page this$1;
            final /* synthetic */ StoryPrivacyBottomSheet val$this$0;

            public AnonymousClass1(Page r1, Context r2, Theme.ResourcesProvider r3, Runnable r4, StoryPrivacyBottomSheet r5) {
                this.this$1 = r1;
                this.val$this$0 = r5;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet.SearchUsersCell
            public void setContainerHeight(float r4) {
                super.setContainerHeight(r4);
                GraySectionCell r42 = Page.m20318$$Nest$fgetsectionCell(this.this$1);
                float r0 = getY();
                if (Page.m20313$$Nest$fgetcontentView(this.this$1) != null) goto L5;
                int r1 = 0;
            L6:
                r42.setTranslationY(((r0 - r1) + Math.min(AndroidUtilities.dp(150.0f), this.containerHeight)) - 1.0f);
                if (Page.m20313$$Nest$fgetcontentView(this.this$1) == null) goto L10;
                Page.m20313$$Nest$fgetcontentView(this.this$1).invalidate();
                return;
            L10:
                return;
            L5:
                r1 = Page.m20313$$Nest$fgetcontentView(this.this$1).getPaddingTop();
                goto L6
            }

            @Override // android.view.View
            public void setTranslationY(float r4) {
                super.setTranslationY(r4);
                GraySectionCell r42 = Page.m20318$$Nest$fgetsectionCell(this.this$1);
                float r0 = getY();
                if (Page.m20313$$Nest$fgetcontentView(this.this$1) != null) goto L5;
                int r1 = 0;
            L6:
                r42.setTranslationY(((r0 - r1) + Math.min(AndroidUtilities.dp(150.0f), this.containerHeight)) - 1.0f);
                if (Page.m20313$$Nest$fgetcontentView(this.this$1) == null) goto L10;
                Page.m20313$$Nest$fgetcontentView(this.this$1).invalidate();
                return;
            L10:
                return;
            L5:
                r1 = Page.m20313$$Nest$fgetcontentView(this.this$1).getPaddingTop();
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$Page$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerView.OnScrollListener {
            private boolean canScrollDown;
            final /* synthetic */ Page this$1;
            final /* synthetic */ StoryPrivacyBottomSheet val$this$0;

            public AnonymousClass2(Page r1, StoryPrivacyBottomSheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView r3, int r4) {
                boolean r32 = true;
                if (r4 == 1) goto L5;
            L9:
                if (r4 != 0) goto L11;
                Page r0 = this.this$1;
                r0.wasAtTop = r0.atTop();
                Page r02 = this.this$1;
                r02.wasAtBottom = r02.atBottom();
            L11:
                Page r2 = this.this$1;
                if (r4 != 0) goto L15;
                r32 = false;
            L15:
                Page.m20319$$Nest$fputscrolling(r2, r32);
                return;
            L5:
                if (StoryPrivacyBottomSheet.access$900(this.this$1.this$0) == false) goto L9;
                if (Page.m20317$$Nest$fgetsearchField(this.this$1) == null) goto L9;
                this.this$1.this$0.closeKeyboard();
                goto L9
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r1, int r2, int r3) {
                boolean r12 = Page.m20316$$Nest$fgetlistView(this.this$1).canScrollVertically(1);
                if (r12 == this.canScrollDown) goto L5;
                Page.m20312$$Nest$fgetbuttonContainer(this.this$1).invalidate();
                this.canScrollDown = r12;
            L5:
                Page.m20313$$Nest$fgetcontentView(this.this$1).invalidate();
                StoryPrivacyBottomSheet.access$600(this.this$1.this$0).invalidate();
                Page r13 = this.this$1;
                if (r13.pageType == 6) goto L8;
                return;
            L8:
                if (Page.m20316$$Nest$fgetlistView(r13).getChildCount() > 0) goto L10;
                return;
            L10:
                if (Page.m20316$$Nest$fgetlistView(this.this$1).getChildAdapterPosition(Page.m20316$$Nest$fgetlistView(this.this$1).getChildAt(0)) < MessagesController.getInstance(StoryPrivacyBottomSheet.access$700(this.this$1.this$0)).getStoriesController().blocklist.size()) goto L15;
                MessagesController.getInstance(StoryPrivacyBottomSheet.access$800(this.this$1.this$0)).getStoriesController().loadBlocklist(false);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$Page$3, reason: invalid class name */
        public class AnonymousClass3 extends DefaultItemAnimator {
            final /* synthetic */ Page this$1;
            final /* synthetic */ StoryPrivacyBottomSheet val$this$0;

            public AnonymousClass3(Page r1, StoryPrivacyBottomSheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.SimpleItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
            public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder r1) {
                return true;
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onAddAnimationUpdate(RecyclerView.ViewHolder r1) {
                StoryPrivacyBottomSheet.access$1200(this.this$1.this$0).invalidate();
                Page.m20313$$Nest$fgetcontentView(this.this$1).invalidate();
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onChangeAnimationUpdate(RecyclerView.ViewHolder r1) {
                StoryPrivacyBottomSheet.access$1100(this.this$1.this$0).invalidate();
                Page.m20313$$Nest$fgetcontentView(this.this$1).invalidate();
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
                StoryPrivacyBottomSheet.access$1000(this.this$1.this$0).invalidate();
                Page.m20313$$Nest$fgetcontentView(this.this$1).invalidate();
                Page.m20316$$Nest$fgetlistView(this.this$1).invalidate();
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onRemoveAnimationUpdate(RecyclerView.ViewHolder r1) {
                StoryPrivacyBottomSheet.access$1300(this.this$1.this$0).invalidate();
                Page.m20313$$Nest$fgetcontentView(this.this$1).invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$Page$4, reason: invalid class name */
        public class AnonymousClass4 extends AnimatorListenerAdapter {
            final /* synthetic */ Page this$1;

            public AnonymousClass4(Page r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                Page.m20320$$Nest$fputsearchTranslationAnimating(this.this$1, false);
            }
        }

        public class Adapter extends AdapterWithDiffUtils {
            private Context context;
            private RecyclerListView listView;
            private Runnable onBack;
            private Theme.ResourcesProvider resourcesProvider;
            public boolean reversedLayout;
            private SearchUsersCell searchField;
            final /* synthetic */ Page this$1;

            /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$Page$Adapter$1, reason: invalid class name */
            public class AnonymousClass1 extends View {
                final /* synthetic */ Adapter this$2;

                public AnonymousClass1(Adapter r1, Context r2) {
                    this.this$2 = r1;
                    super(r2);
                }

                @Override // android.view.View
                public void onMeasure(int r2, int r3) {
                    super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30));
                }
            }

            public static /* synthetic */ CharSequence $r8$lambda$UIS_Jkk_zlzxU3kJNwsMLu39uAs(Integer r1, Integer r2) {
                if (r1.intValue() != 0) goto L11;
                if (r2.intValue() != 0) goto L9;
                return LocaleController.getString(R.string.LiveStoryPricePerCommentFree);
            L9:
                return LocaleController.formatPluralStringComma("Stars", r2.intValue());
            L11:
                return _UrlKt.FRAGMENT_ENCODE_SET + r2;
            }

            public static /* synthetic */ void $r8$lambda$l0OkHVJs_XfhXzGhOuPOf8LdEmI(Adapter r0, Integer r1) {
                r0.lambda$onBindViewHolder$1(r1);
            }

            /* renamed from: -$$Nest$fputlistView, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m20321$$Nest$fputlistView(Adapter r0, RecyclerListView r1) {
                r0.listView = r1;
            }

            public Adapter(Page r1, Context r2, Theme.ResourcesProvider r3, SearchUsersCell r4, Runnable r5) {
                this.this$1 = r1;
                this.context = r2;
                this.resourcesProvider = r3;
                this.searchField = r4;
                this.onBack = r5;
            }

            private /* synthetic */ void lambda$onBindViewHolder$1(Integer r1) {
                StoryPrivacyBottomSheet.m20281$$Nest$fputcommentsPrice(this.this$1.this$0, r1.intValue());
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                if (Page.m20314$$Nest$fgetitems(this.this$1) != null) goto L7;
                return 0;
            L7:
                return Page.m20314$$Nest$fgetitems(this.this$1).size();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r2) {
                if (Page.m20314$$Nest$fgetitems(this.this$1) == null) goto L10;
                if (r2 >= 0) goto L6;
                return -1;
            L6:
                if (r2 < Page.m20314$$Nest$fgetitems(this.this$1).size()) goto L9;
                return -1;
            L9:
                return ((ItemInner) Page.m20314$$Nest$fgetitems(this.this$1).get(r2)).viewType;
            L10:
                return -1;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r3) {
                if (r3.getItemViewType() != 3) goto L7;
                if (StoryPrivacyBottomSheet.m20251$$Nest$fgetcanChangePeer(this.this$1.this$0) == false) goto L7;
                return true;
            L7:
                if (r3.getItemViewType() != 7) goto L9;
                return true;
            L9:
                if (r3.getItemViewType() != 9) goto L11;
                return true;
            L11:
                if (r3.getItemViewType() == 10) goto L19;
                return false;
            L19:
                return true;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
                if (Page.m20314$$Nest$fgetitems(this.this$1) == null) goto L126;
                if (r11 >= 0) goto L6;
                return;
            L6:
                if (r11 >= Page.m20314$$Nest$fgetitems(this.this$1).size()) goto L130;
                ItemInner r1 = (ItemInner) Page.m20314$$Nest$fgetitems(this.this$1).get(r11);
                int r2 = r10.getItemViewType();
                boolean r5 = true;
                if (this.reversedLayout == false) goto L14;
                if (r11 <= 0) goto L13;
                ArrayList r3 = Page.m20314$$Nest$fgetitems(this.this$1);
                int r112 = r11 - 1;
            L12:
                ItemInner r113 = (ItemInner) r3.get(r112);
            L18:
                if (r113 == null) goto L25;
                int r7 = r113.viewType;
                if (r7 == r2) goto L24;
                if (r7 != 9) goto L25;
                if (r113.id != 1) goto L25;
            L24:
                boolean r114 = true;
            L27:
                if (r2 != 3) goto L51;
                UserCell r102 = (UserCell) r10.itemView;
                boolean r0 = r1.sendAs;
                r102.setIsSendAs(r0, !r0);
                int r02 = r1.type;
                float r22 = 1.0f;
                if (r02 <= 0) goto L31;
                r102.setType(r02, r1.typeCount, r1.user);
                r102.setCheckboxAlpha(1.0f, false);
            L43:
                if (r1.checked == false) goto L45;
            L48:
                r102.setChecked(r5, false);
                r102.setDivider(r114);
                r102.setRedCheckbox(r1.red);
                UserCell.m20343$$Nest$fputdrawArrow(r102, StoryPrivacyBottomSheet.m20251$$Nest$fgetcanChangePeer(this.this$1.this$0));
                return;
            L45:
                if (r1.halfChecked == true) goto L48;
                r5 = false;
                goto L48
            L31:
                TLRPC.User r03 = r1.user;
                if (r03 == null) goto L39;
                r102.setUser(r03);
                if (r1.halfChecked == true) goto L36;
            L38:
                r102.setCheckboxAlpha(r22, false);
                goto L43
            L36:
                if (r1.checked == true) goto L38;
                r22 = 0.5f;
                goto L38
            L39:
                TLRPC.Chat r04 = r1.chat;
                if (r04 == null) goto L43;
                r102.setChat(r04, StoryPrivacyBottomSheet.m20292$$Nest$mgetParticipantsCount(this.this$1.this$0, r04));
                goto L43
            L51:
                if (r2 != 2) goto L54;
                return;
            L54:
                if (r2 != 0) goto L57;
                r10.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(56.0f)));
                return;
            L57:
                if (r2 == (-1)) goto L59;
                if (r2 != 1) goto L77;
                r10.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, Math.min(AndroidUtilities.dp(150.0f), this.searchField.resultContainerHeight)));
                return;
            L77:
                if (r2 != 4) goto L81;
                ((HeaderCell2) r10.itemView).setText(r1.text, r1.text2);
                return;
            L81:
                if (r2 != 11) goto L85;
                ((HeaderCell2) r10.itemView).setText(r1.text, null);
                return;
            L85:
                if (r2 != 5) goto L89;
                ((StickerEmptyView) r10.itemView).stickerView.getImageReceiver().startAnimation();     // Catch: Exception -> L123
                return;
            L132:
                return;
            L89:
                if (r2 != 6) goto L97;
                TextInfoPrivacyCell r9 = (TextInfoPrivacyCell) r10.itemView;
                if (r1.text != null) goto L94;
                r9.setFixedSize(12);
                r9.setText(null);
                return;
            L94:
                r9.setFixedSize(0);
                r9.setText(r1.text);
                return;
            L97:
                if (r2 != 7) goto L108;
                int r05 = r1.resId;
                if (r05 != 0) goto L102;
                ((TextCell) r10.itemView).setTextAndCheck(r1.text, StoryPrivacyBottomSheet.m20247$$Nest$fgetallowScreenshots(this.this$1.this$0), r114);
                return;
            L102:
                if (r05 != 1) goto L105;
                ((TextCell) r10.itemView).setTextAndCheck(r1.text, StoryPrivacyBottomSheet.m20261$$Nest$fgetkeepOnMyPage(this.this$1.this$0), r114);
                return;
            L105:
                if (r05 != 2) goto L128;
                ((TextCell) r10.itemView).setTextAndCheck(r1.text, StoryPrivacyBottomSheet.m20245$$Nest$fgetallowComments(this.this$1.this$0), r114);
                return;
            L128:
                return;
            L108:
                if (r2 != 9) goto L116;
                Drawable r92 = r1.drawable;
                View r103 = r10.itemView;
                if (r92 == null) goto L113;
                ((TextCell) r103).setTextAndValueDrawable(r1.text, r92, r114);
                return;
            L113:
                ((TextCell) r103).setTextAndValue(r1.text, r1.text2, r114);
                return;
            L116:
                if (r2 != 8) goto L120;
                ((org.telegram.ui.Cells.HeaderCell) r10.itemView).setText(r1.text);
                return;
            L120:
                if (r2 != 10) goto L129;
                int r115 = (int) MessagesController.getInstance(StoryPrivacyBottomSheet.access$6300(this.this$1.this$0)).starsPaidMessageAmountMax;
                ((SlideIntChooseView) r10.itemView).set(Utilities.clamp(StoryPrivacyBottomSheet.m20252$$Nest$fgetcommentsPrice(this.this$1.this$0), r115, 0), SlideIntChooseView.Options.make(0, SlideIntChooseView.cut(new int[]{0, 1, 10, 50, 100, 200, 250, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, 1000, 2500, 5000, 7500, 9000, 10000}, r115), 20, new StoryPrivacyBottomSheet$Page$Adapter$$ExternalSyntheticLambda0()), new StoryPrivacyBottomSheet$Page$Adapter$$ExternalSyntheticLambda1(this));
                return;
            L129:
                return;
            L59:
                if (r1.subtractHeight <= 0) goto L67;
                RecyclerListView r116 = this.listView;
                if (r116 != null) goto L63;
            L65:
                int r117 = AndroidUtilities.displaySize.y;
            L66:
                int r93 = Math.max(r117 - r1.subtractHeight, AndroidUtilities.dp(120.0f));
                r10.itemView.setTag(33);
            L71:
                r10.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, r93));
                return;
            L63:
                if (r116.getMeasuredHeight() <= 0) goto L65;
                r117 = this.listView.getMeasuredHeight() + Page.m20315$$Nest$fgetkeyboardHeight(this.this$1);
                goto L66
            L67:
                r93 = r1.padHeight;
                if (r93 < 0) goto L70;
                r10.itemView.setTag(null);
                goto L71
            L70:
                r93 = (int) (AndroidUtilities.displaySize.y * 0.3f);
                r10.itemView.setTag(33);
            L25:
                r114 = false;
            L13:
                r113 = null;
                goto L18
            L14:
                r112 = r11 + 1;
                if (r112 >= Page.m20314$$Nest$fgetitems(this.this$1).size()) goto L13;
                r3 = Page.m20314$$Nest$fgetitems(this.this$1);
                goto L12
            L130:
                return;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
                if (r8 != (-1)) goto L5;
                View r72 = new PadView(this.this$1, this.context);
            L41:
                return new RecyclerListView.Holder(r72);
            L5:
                if (r8 != 0) goto L8;
                r72 = new View(this.context);
                r72.setTag(35);
                goto L41
            L8:
                if (r8 != 1) goto L11;
                r72 = new View(this.context);
                r72.setTag(34);
                goto L41
            L11:
                if (r8 != 3) goto L14;
                r72 = new UserCell(this.context, this.resourcesProvider);
                goto L41
            L14:
                if (r8 != 4) goto L18;
                View r82 = new HeaderCell2(this.context, this.resourcesProvider, true);
            L16:
                r72 = r82;
                goto L41
            L18:
                if (r8 != 11) goto L21;
                r72 = new HeaderCell2(this.context, this.resourcesProvider, false);
                goto L41
            L21:
                if (r8 != 8) goto L24;
                r72 = new org.telegram.ui.Cells.HeaderCell(this.context, this.resourcesProvider);
                r72.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider));
                goto L41
            L24:
                if (r8 != 5) goto L27;
                StickerEmptyView r83 = new StickerEmptyView(this.context, null, 1, this.resourcesProvider);
                r83.title.setText(LocaleController.getString(R.string.NoResult));
                r83.subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
                r83.linearLayout.setTranslationY(AndroidUtilities.dp(24.0f));
                r82 = r83;
                goto L16
            L27:
                if (r8 != 6) goto L30;
                r72 = new TextInfoPrivacyCell(this.context, this.resourcesProvider);
                r72.setBackgroundColor(-15921907);
                goto L41
            L30:
                if (r8 != 7) goto L34;
                TextCell r0 = new TextCell(this.context, 23, true, true, this.resourcesProvider);
            L32:
                r72 = r0;
                goto L41
            L34:
                if (r8 != 9) goto L37;
                r0 = new TextCell(this.context, 23, true, false, this.resourcesProvider);
                goto L32
            L37:
                if (r8 != 10) goto L39;
                r72 = new SlideIntChooseView(this.context, this.resourcesProvider);
                goto L41
            L39:
                r72 = new AnonymousClass1(this, this.context);
                goto L41
            }
        }

        public class ButtonContainer extends LinearLayout {
            final AnimatedFloat alpha;
            private ValueAnimator animator;
            final Paint dividerPaint;
            private ValueAnimator hideAnimator;
            final /* synthetic */ Page this$1;
            private float translationY;
            private float translationY2;

            /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$Page$ButtonContainer$1, reason: invalid class name */
            public class AnonymousClass1 extends AnimatorListenerAdapter {
                final /* synthetic */ ButtonContainer this$2;
                final /* synthetic */ boolean val$hide;

                public AnonymousClass1(ButtonContainer r1, boolean r2) {
                    this.this$2 = r1;
                    this.val$hide = r2;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r2) {
                    if (this.val$hide == false) goto L5;
                    this.this$2.setVisibility(8);
                L5:
                    ButtonContainer.m20323$$Nest$fputhideAnimator(this.this$2, null);
                }
            }

            /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$Page$ButtonContainer$2, reason: invalid class name */
            public class AnonymousClass2 extends AnimatorListenerAdapter {
                final /* synthetic */ ButtonContainer this$2;
                final /* synthetic */ float val$to;

                public AnonymousClass2(ButtonContainer r1, float r2) {
                    this.this$2 = r1;
                    this.val$to = r2;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r2) {
                    this.this$2.setTranslationY(this.val$to);
                    ButtonContainer.m20322$$Nest$fputanimator(this.this$2, null);
                }
            }

            public static /* synthetic */ void $r8$lambda$C4fi4b1ZX943gsuauHdbGiewxh8(ButtonContainer r0, ValueAnimator r1) {
                r0.lambda$hide$0(r1);
            }

            public static /* synthetic */ void $r8$lambda$Kk1hB3VtrUq1t9ZVA1TvVVD4xz4(ButtonContainer r0, ValueAnimator r1) {
                r0.lambda$translateY$1(r1);
            }

            /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m20322$$Nest$fputanimator(ButtonContainer r0, ValueAnimator r1) {
                r0.animator = r1;
            }

            /* renamed from: -$$Nest$fputhideAnimator, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m20323$$Nest$fputhideAnimator(ButtonContainer r0, ValueAnimator r1) {
                r0.hideAnimator = r1;
            }

            public ButtonContainer(Page r1, Context r2) {
                this.this$1 = r1;
                super(r2);
                this.dividerPaint = new Paint(1);
                this.alpha = new AnimatedFloat(this);
            }

            private /* synthetic */ void lambda$hide$0(ValueAnimator r2) {
                float r22 = ((Float) r2.getAnimatedValue()).floatValue();
                this.translationY2 = r22;
                super.setTranslationY(r22 + this.translationY);
            }

            private /* synthetic */ void lambda$translateY$1(ValueAnimator r1) {
                setTranslationY(((Float) r1.getAnimatedValue()).floatValue());
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r8) {
                super.dispatchDraw(r8);
                this.dividerPaint.setColor(Theme.getColor(Theme.key_windowBackgroundGray, StoryPrivacyBottomSheet.access$2000(this.this$1.this$0)));
                Paint r0 = this.dividerPaint;
                AnimatedFloat r1 = this.alpha;
                if (Page.m20316$$Nest$fgetlistView(this.this$1).canScrollVertically(1) == false) goto L5;
                float r2 = 1.0f;
            L6:
                r0.setAlpha((int) (r1.set(r2) * 255.0f));
                r8.drawRect(0.0f, 0.0f, getWidth(), 1.0f, this.dividerPaint);
                return;
            L5:
                r2 = 0.0f;
                goto L6
            }

            public void hide(boolean r4, boolean r5) {
                ValueAnimator r0 = this.hideAnimator;
                if (r0 == null) goto L5;
                r0.cancel();
            L5:
                float r02 = 0.0f;
                int r1 = 0;
                if (r5 == false) goto L12;
                setVisibility(0);
                float r52 = this.translationY2;
                if (r4 == false) goto L10;
                r02 = getMeasuredHeight();
            L10:
                ValueAnimator r53 = ValueAnimator.ofFloat(new float[]{r52, r02});
                this.hideAnimator = r53;
                r53.addUpdateListener(new StoryPrivacyBottomSheet$Page$ButtonContainer$$ExternalSyntheticLambda0(this));
                this.hideAnimator.addListener(new AnonymousClass1(this, r4));
                this.hideAnimator.setDuration(320);
                this.hideAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
                this.hideAnimator.start();
                return;
            L12:
                if (r4 == false) goto L14;
                r1 = 8;
            L14:
                setVisibility(r1);
                if (r4 == false) goto L17;
                r02 = getMeasuredHeight();
            L17:
                this.translationY2 = r02;
                super.setTranslationY(r02 + this.translationY);
            }

            @Override // android.view.View
            public void setTranslationY(float r2) {
                float r0 = this.translationY2;
                this.translationY = r2;
                super.setTranslationY(r0 + r2);
            }

            public void translateY(float r3, float r4) {
                ValueAnimator r0 = this.animator;
                if (r0 == null) goto L5;
                r0.cancel();
                this.animator = null;
            L5:
                ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{r3, r4});
                this.animator = r32;
                r32.addUpdateListener(new StoryPrivacyBottomSheet$Page$ButtonContainer$$ExternalSyntheticLambda1(this));
                this.animator.addListener(new AnonymousClass2(this, r4));
                this.animator.setDuration(250);
                this.animator.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
                this.animator.start();
            }
        }

        public class PadView extends View {
            final /* synthetic */ Page this$1;

            public PadView(Page r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }
        }

        public static /* synthetic */ void $r8$lambda$0D_68wto3HB4nBmb6C01pDEfP70(Page r0, CreateRtmpStreamBottomSheet[] r1, Browser.Progress r2) {
            r0.lambda$new$11(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$2rRpeoc6M7pbvWAHj82lI20kzKU(Page r0, ValueAnimator r1) {
            r0.lambda$updateSearchFieldTop$22(r1);
        }

        /* renamed from: $r8$lambda$4z-bvEiq_JJuu8ZEgXWIY1kCQS4, reason: not valid java name */
        public static /* synthetic */ void m20303$r8$lambda$4zbvEiq_JJuu8ZEgXWIY1kCQS4(Page r0, View r1) {
            r0.lambda$updateSectionCell$23(r1);
        }

        /* renamed from: $r8$lambda$5_jaWJMJUCbbB02R-cgGF38FW5w, reason: not valid java name */
        public static /* synthetic */ void m20304$r8$lambda$5_jaWJMJUCbbB02RcgGF38FW5w(Page r0) {
            r0.lambda$new$1();
        }

        public static /* synthetic */ void $r8$lambda$8G3MmHkSYXtg_Uab60nIbclyPcw(Page r0, boolean r1, long r2) {
            r0.lambda$new$4(r1, r2);
        }

        /* renamed from: $r8$lambda$8MoLp7SCRY-4MxvaAfQYxi5eYjg, reason: not valid java name */
        public static /* synthetic */ void m20305$r8$lambda$8MoLp7SCRY4MxvaAfQYxi5eYjg(Page r0, View r1) {
            r0.onButton2Click(r1);
        }

        public static /* synthetic */ void $r8$lambda$9mYLMDRWovk6QNXIAAtfI3AwUVs(Page r0, long r1, TLRPC.ChatFull r3) {
            r0.lambda$new$5(r1, r3);
        }

        /* renamed from: $r8$lambda$FXJQpxpW8c7VpGquz2_r8-50Ydw, reason: not valid java name */
        public static /* synthetic */ void m20306$r8$lambda$FXJQpxpW8c7VpGquz2_r850Ydw(Page r0, View r1) {
            r0.onButton1Click(r1);
        }

        public static /* synthetic */ void $r8$lambda$GcrkILYlbl2_1ga9MRy6l_G9n80(Page r0, StoriesController.StoryAlbum r1) {
            r0.lambda$new$7(r1);
        }

        /* renamed from: $r8$lambda$If0CkHEhLwUImvQ23_aV9w5mf-c, reason: not valid java name */
        public static /* synthetic */ void m20307$r8$lambda$If0CkHEhLwUImvQ23_aV9w5mfc(Page r0, ArrayList r1) {
            r0.lambda$onButton2Click$20(r1);
        }

        public static /* synthetic */ void $r8$lambda$P7W35jZjM1pdaK0_oFQElv3qR_8(Page r0, long r1, String r3) {
            r0.lambda$new$8(r1, r3);
        }

        /* renamed from: $r8$lambda$SgDNV3b4aLdU-gIuQ7ouNaLMlww, reason: not valid java name */
        public static /* synthetic */ void m20308$r8$lambda$SgDNV3b4aLdUgIuQ7ouNaLMlww(Page r0) {
            r0.lambda$onButton1Click$18();
        }

        public static /* synthetic */ void $r8$lambda$YwnHls6oCUexV3ERXbgFQxswr6s(Page r0, long r1) {
            r0.lambda$new$9(r1);
        }

        /* renamed from: $r8$lambda$aJol6h4XQf-FqDLveuN9QBAHqcY, reason: not valid java name */
        public static /* synthetic */ void m20309$r8$lambda$aJol6h4XQfFqDLveuN9QBAHqcY(Page r0) {
            r0.lambda$updateItems$21();
        }

        public static /* synthetic */ void $r8$lambda$guI08yEAS7tIkmLlEGfG4SNJ41o(Page r0, MessagesController r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onButton1Click$17(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$mvNiQ54FuQiSKIutj5YSUYO22eE(Page r0) {
            r0.lambda$onButton1Click$19();
        }

        public static /* synthetic */ void $r8$lambda$oK6jh7TrvMjUaOX1REI7j4TK_QM(Page r0, AlertDialog r1, TLObject r2, TL_phone.getGroupCallStreamRtmpUrl r3, TLRPC.TL_error r4) {
            r0.lambda$new$12(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$pTi830J1JkMDiEr3vq2LCypwNZE(Page r0, AlertDialog r1, TL_phone.getGroupCallStreamRtmpUrl r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$new$13(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$pWPfqS99J0hoXgiuypQOGBnm9oY(Page r0) {
            r0.lambda$onMeasure$24();
        }

        public static /* synthetic */ void $r8$lambda$phQmhSBT77uV4fy7L42kRUC1hW4(Page r0, long r1, ArrayList r3, AlertDialog r4, int r5) {
            r0.lambda$selectChat$15(r1, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$qdyAu4ldsrwjjfgtJt9aZzbjJrM(Page r0, Context r1, View r2, int r3, float r4, float r5) {
            r0.lambda$new$14(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$rCDd0xLHbReyIld380sTBgxQSh0(Page r0, TLRPC.Chat r1, MessagesStorage r2, long r3) {
            r0.lambda$new$6(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$rzKIEQSC4RvlU9PiSAWSQUOuFjA(Page r0) {
            r0.lambda$new$0();
        }

        public static /* synthetic */ void $r8$lambda$sOHaZtq4shRTTR7Apit_8kcO8hw(Page r0, ItemOptions r1, StoriesController.StoryAlbum r2) {
            r0.lambda$new$10(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$tZG4Utso5fuabxS65JdIY8k39LA(Page r0, long r1, TLRPC.TL_channels_channelParticipants r3) {
            r0.lambda$new$3(r1, r3);
        }

        /* renamed from: $r8$lambda$wfsAlaJpDFjcDc-7oWbxfxovMZI, reason: not valid java name */
        public static /* synthetic */ void m20310$r8$lambda$wfsAlaJpDFjcDc7oWbxfxovMZI(Page r0, TLObject r1, MessagesController r2) {
            r0.lambda$onButton1Click$16(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$xxDM1Gxrt9LGATCLalG06fC6Aa4(Page r0, TLRPC.InputPeer r1) {
            r0.lambda$new$2(r1);
        }

        /* renamed from: -$$Nest$fgetbutton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ButtonWithCounterView m20311$$Nest$fgetbutton(Page r0) {
            return r0.button;
        }

        /* renamed from: -$$Nest$fgetbuttonContainer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ButtonContainer m20312$$Nest$fgetbuttonContainer(Page r0) {
            return r0.buttonContainer;
        }

        /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m20313$$Nest$fgetcontentView(Page r0) {
            return r0.contentView;
        }

        /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m20314$$Nest$fgetitems(Page r0) {
            return r0.items;
        }

        /* renamed from: -$$Nest$fgetkeyboardHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m20315$$Nest$fgetkeyboardHeight(Page r0) {
            return r0.keyboardHeight;
        }

        /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RecyclerListView m20316$$Nest$fgetlistView(Page r0) {
            return r0.listView;
        }

        /* renamed from: -$$Nest$fgetsearchField, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SearchUsersCell m20317$$Nest$fgetsearchField(Page r0) {
            return r0.searchField;
        }

        /* renamed from: -$$Nest$fgetsectionCell, reason: not valid java name */
        public static /* bridge */ /* synthetic */ GraySectionCell m20318$$Nest$fgetsectionCell(Page r0) {
            return r0.sectionCell;
        }

        /* renamed from: -$$Nest$fputscrolling, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20319$$Nest$fputscrolling(Page r0, boolean r1) {
            r0.scrolling = r1;
        }

        /* renamed from: -$$Nest$fputsearchTranslationAnimating, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20320$$Nest$fputsearchTranslationAnimating(Page r0, boolean r1) {
            r0.searchTranslationAnimating = r1;
        }

        public Page(StoryPrivacyBottomSheet r18, Context r19) {
            this.this$0 = r18;
            super(r19);
            this.changelog = new LongSparseArray();
            this.selectedUsers = new ArrayList();
            this.selectedUsersByGroup = new HashMap();
            this.searchPosition = -1;
            this.atTop = new ArrayList();
            this.oldItems = new ArrayList();
            this.items = new ArrayList();
            this.lastSelectedType = -1;
            this.sectionCell = new GraySectionCell(r19, StoryPrivacyBottomSheet.access$000(r18));
            AnonymousClass1 r0 = new AnonymousClass1(this, r19, StoryPrivacyBottomSheet.access$100(r18), new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda3(this), r18);
            this.searchField = r0;
            int r8 = Theme.key_dialogBackground;
            r0.setBackgroundColor(StoryPrivacyBottomSheet.access$200(r18, r8));
            this.searchField.setOnSearchTextChange(new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda4(this));
            HeaderCell r02 = new HeaderCell(r19, StoryPrivacyBottomSheet.access$300(r18));
            this.headerView = r02;
            r02.setOnCloseClickListener(new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda5(this));
            FrameLayout r9 = new FrameLayout(r19);
            this.contentView = r9;
            r9.setPadding(0, AndroidUtilities.statusBarHeight + AndroidUtilities.dp(56.0f), 0, 0);
            r9.setClipToPadding(true);
            addView(r9, LayoutHelper.createFrame(-1, -1, 119));
            RecyclerListView r03 = new RecyclerListView(r19, StoryPrivacyBottomSheet.access$400(r18));
            this.listView = r03;
            r03.setClipToPadding(false);
            this.listView.setTranslateSelector(true);
            RecyclerListView r12 = this.listView;
            Adapter r04 = new Adapter(this, r19, StoryPrivacyBottomSheet.access$500(r18), this.searchField, new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda6(r18));
            this.adapter = r04;
            r12.setAdapter(r04);
            Adapter.m20321$$Nest$fputlistView(this.adapter, this.listView);
            RecyclerListView r05 = this.listView;
            LinearLayoutManager r3 = new LinearLayoutManager(r19);
            this.layoutManager = r3;
            r05.setLayoutManager(r3);
            this.listView.setOnScrollListener(new AnonymousClass2(this, r18));
            this.listView.setOnItemClickListener(new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda7(this, r19));
            r9.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
            AnonymousClass3 r06 = new AnonymousClass3(this, r18);
            r06.setDurations(350);
            r06.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r06.setDelayAnimations(false);
            r06.setSupportsChangeAnimations(false);
            this.listView.setItemAnimator(r06);
            r9.addView(this.searchField, LayoutHelper.createFrame(-1, -2, 55));
            r9.addView(this.sectionCell, LayoutHelper.createFrame(-1, 32, 55));
            addView(this.headerView, LayoutHelper.createFrame(-1, -2, 55));
            ButtonContainer r07 = new ButtonContainer(this, r19);
            this.buttonContainer = r07;
            r07.setClickable(true);
            r07.setOrientation(1);
            r07.setPadding(AndroidUtilities.dp(10.0f) + StoryPrivacyBottomSheet.access$1400(r18), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f) + StoryPrivacyBottomSheet.access$1500(r18), AndroidUtilities.dp(10.0f));
            r07.setBackgroundColor(Theme.getColor(r8, StoryPrivacyBottomSheet.access$1600(r18)));
            ButtonWithCounterView r4 = new ButtonWithCounterView(r19, StoryPrivacyBottomSheet.access$1700(r18));
            this.button = r4;
            r4.setOnClickListener(new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda8(this));
            r4.setRound();
            r07.addView(r4, LayoutHelper.createLinear(-1, 48, 87));
            ButtonWithCounterView r42 = new ButtonWithCounterView(r19, false, StoryPrivacyBottomSheet.access$1800(r18));
            this.button2 = r42;
            r42.setOnClickListener(new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda9(this));
            r42.setRound();
            r07.addView(r42, LayoutHelper.createLinear(-1, 48, 87, 0, 8, 0, 0));
            View r43 = new View(r19);
            this.underKeyboardView = r43;
            r43.setBackgroundColor(Theme.getColor(r8, StoryPrivacyBottomSheet.access$1900(r18)));
            addView(r43, LayoutHelper.createFrame(-1, 500.0f, 87, 0.0f, 0.0f, 0.0f, -500.0f));
            addView(r07, LayoutHelper.createFrame(-1, -2, 87));
        }

        private long getDialogId() {
            StoryPrivacyBottomSheet r2 = this.this$0;
            TLRPC.InputPeer r0 = r2.selectedPeer;
            if (r0 == null) goto L7;
            return DialogObject.getPeerDialogId(r0);
        L7:
            return UserConfig.getInstance(StoryPrivacyBottomSheet.access$5000(r2)).getClientUserId();
        }

        private float getSearchFieldTop() {
            int r0 = 0;
            float r1 = -Math.max(0, Math.min(AndroidUtilities.dp(150.0f), this.searchField.resultContainerHeight) - AndroidUtilities.dp(150.0f));
        L4:
            if (r0 >= this.listView.getChildCount()) goto L12;
            View r2 = this.listView.getChildAt(r0);
            if ((r2.getTag() instanceof Integer) == false) goto L11;
            if (((Integer) r2.getTag()).intValue() != 34) goto L11;
            return Math.max(r1, r2.getY());
        L11:
            r0 = r0 + 1;
            goto L4
        L12:
            return r1;
        }

        private /* synthetic */ void lambda$new$0() {
            this.adapter.notifyItemChanged(2);
            this.listView.forceLayout();
            updateTops();
        }

        private /* synthetic */ void lambda$new$1() {
            int r0 = this.pageType;
            StoryPrivacyBottomSheet r1 = this.this$0;
            if (r0 != 0) goto L6;
            r1.dismiss();
            return;
        L6:
            r1.onBackPressed();
        }

        private /* synthetic */ void lambda$new$10(ItemOptions r1, StoriesController.StoryAlbum r2) {
            r1.dismiss();
            updateItems(true);
            if (StoryPrivacyBottomSheet.m20265$$Nest$fgetonSelectedAlbums(this.this$0) == null) goto L6;
            StoryPrivacyBottomSheet.m20265$$Nest$fgetonSelectedAlbums(this.this$0).run(new HashSet(StoryPrivacyBottomSheet.m20267$$Nest$fgetselectedAlbums(this.this$0)));
            return;
        }

        private /* synthetic */ void lambda$new$11(CreateRtmpStreamBottomSheet[] r2, Browser.Progress r3) {
            StoryPrivacyBottomSheet.m20283$$Nest$fputisRtmpStream(this.this$0, true);
            r2[0].dismiss();
            updateItems(true);
        }

        private /* synthetic */ void lambda$new$12(AlertDialog r8, TLObject r9, TL_phone.getGroupCallStreamRtmpUrl r10, TLRPC.TL_error r11) {
            r8.dismiss();
            if ((r9 instanceof TL_phone.groupCallStreamRtmpUrl) == false) goto L10;
            CreateRtmpStreamBottomSheet[] r82 = new CreateRtmpStreamBottomSheet[1];
            Context r1 = getContext();
            int r2 = StoryPrivacyBottomSheet.access$8100(this.this$0);
            TL_phone.groupCallStreamRtmpUrl r4 = (TL_phone.groupCallStreamRtmpUrl) r9;
            if (StoryPrivacyBottomSheet.m20262$$Nest$fgetliveSettings(this.this$0) == false) goto L7;
            StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda29 r5 = null;
        L8:
            CreateRtmpStreamBottomSheet r0 = new CreateRtmpStreamBottomSheet(r1, r2, r10, r4, r5, new DarkThemeResourceProvider());
            r82[0] = r0;
            r0.show();
            return;
        L7:
            r5 = new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda29(this, r82);
            goto L8
        L10:
            if (r11 == null) goto L13;
            StoryPrivacyBottomSheet r7 = this.this$0;
            BulletinFactory.of(r7.container, StoryPrivacyBottomSheet.access$8200(r7)).showForError(r11, true);
            return;
        }

        private /* synthetic */ void lambda$new$13(AlertDialog r7, TL_phone.getGroupCallStreamRtmpUrl r8, TLObject r9, TLRPC.TL_error r10) {
            AndroidUtilities.runOnUIThread(new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda24(this, r7, r9, r8, r10));
        }

        private /* synthetic */ void lambda$new$14(Context r19, View r20, int r21, float r22, float r23) {
            if (r21 >= 0) goto L5;
            return;
        L5:
            if (r21 >= this.items.size()) goto L190;
            ItemInner r2 = this.items.get(r21);
            int r3 = r2.viewType;
            int r6 = 0;
            if (r3 != 3) goto L108;
            if (r2.sendAs == true) goto L12;
        L15:
            int r0 = r2.type;
            if (r0 == 1) goto L18;
            if (r0 == 3) goto L26;
            if (r0 == 2) goto L36;
            if (r0 == 4) goto L42;
            if (r0 <= 0) goto L48;
            this.selectedUsers.clear();
            this.selectedUsersByGroup.clear();
            StoryPrivacyBottomSheet.m20286$$Nest$fputselectedType(this.this$0, r2.type);
            this.searchField.spansContainer.removeAllSpans(true);
        L105:
            updateCheckboxes(true);
            updateButton(true);
            this.searchField.scrollToBottom();
            return;
        L48:
            TLRPC.Chat r02 = r2.chat;
            if (r02 == null) goto L81;
            long r24 = r02.id;
            if (StoryPrivacyBottomSheet.m20292$$Nest$mgetParticipantsCount(this.this$0, r02) <= 200) goto L55;
            performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Throwable -> L171
        L53:
            new AlertDialog.Builder(getContext(), StoryPrivacyBottomSheet.access$6900(this.this$0)).setTitle(LocaleController.getString(R.string.GroupTooLarge)).setMessage(LocaleController.getString(R.string.GroupTooLargeMessage)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
            goto L105
        L55:
            if (this.selectedUsersByGroup.containsKey(Long.valueOf(r24)) == false) goto L62;
            ArrayList<Long> r03 = this.selectedUsersByGroup.get(Long.valueOf(r24));
            if (r03 == null) goto L61;
            int r4 = r03.size();
        L59:
            if (r6 >= r4) goto L61;
            Long r5 = r03.get(r6);
            r6 = r6 + 1;
            this.changelog.put(r5.longValue(), Boolean.FALSE);
        L61:
            this.selectedUsersByGroup.remove(Long.valueOf(r24));
            updateSpans(true);
            goto L105
        L62:
            TLRPC.Chat r04 = MessagesController.getInstance(StoryPrivacyBottomSheet.access$7000(this.this$0)).getChat(Long.valueOf(r24));
            TLRPC.ChatFull r42 = MessagesController.getInstance(StoryPrivacyBottomSheet.access$7100(this.this$0)).getChatFull(r24);
            if (r42 == null) goto L73;
            TLRPC.ChatParticipants r10 = r42.participants;
            if (r10 == null) goto L73;
            ArrayList<TLRPC.ChatParticipant> r102 = r10.participants;
            if (r102 == null) goto L73;
            if (r102.isEmpty() == true) goto L73;
            if (r42.participants.participants.size() < (r42.participants_count - 1)) goto L73;
            selectChat(r24, r42.participants);
        L78:
            if (TextUtils.isEmpty(this.query) == true) goto L105;
            this.searchField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.query = null;
            updateItems(false);
        L73:
            AlertDialog r43 = this.progressDialog;
            if (r43 == null) goto L76;
            r43.dismiss();
            this.progressDialog = null;
        L76:
            this.waitingForChatId = r24;
            AlertDialog r44 = new AlertDialog(getContext(), 3, StoryPrivacyBottomSheet.access$7200(this.this$0));
            this.progressDialog = r44;
            r44.showDelayed(50);
            MessagesStorage r45 = MessagesStorage.getInstance(StoryPrivacyBottomSheet.access$7300(this.this$0));
            r45.getStorageQueue().postRunnable(new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda16(this, r04, r45, r24));
            goto L78
        L81:
            if (r2.user == null) goto L105;
            if (this.pageType != 0) goto L85;
            StoryPrivacyBottomSheet.m20286$$Nest$fputselectedType(this.this$0, 0);
        L85:
            long r25 = r2.user.id;
            HashSet r05 = new HashSet(this.selectedUsers);
            boolean r46 = this.selectedUsers.contains(Long.valueOf(r25));
            HashMap<Long, ArrayList<Long>> r52 = this.selectedUsersByGroup;
            if (r46 == false) goto L94;
            Iterator<Map.Entry<Long, ArrayList<Long>>> r47 = r52.entrySet().iterator();
        L89:
            if (r47.hasNext() == false) goto L93;
            Map.Entry<Long, ArrayList<Long>> r53 = r47.next();
            if (r53.getValue().contains(Long.valueOf(r25)) == false) goto L89;
            r47.remove();
            r05.addAll(r53.getValue());
            goto L89
        L93:
            r05.remove(Long.valueOf(r25));
            this.changelog.put(r25, Boolean.FALSE);
        L104:
            this.selectedUsers.clear();
            this.selectedUsers.addAll(r05);
            updateSpans(true);
            goto L105
        L94:
            Iterator<Map.Entry<Long, ArrayList<Long>>> r48 = r52.entrySet().iterator();
        L96:
            if (r48.hasNext() == false) goto L100;
            Map.Entry<Long, ArrayList<Long>> r54 = r48.next();
            if (r54.getValue().contains(Long.valueOf(r25)) == false) goto L96;
            r48.remove();
            r05.addAll(r54.getValue());
            goto L96
        L100:
            r05.add(Long.valueOf(r25));
            if (TextUtils.isEmpty(this.query) == true) goto L103;
            this.searchField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.query = null;
            updateItems(false);
        L103:
            this.changelog.put(r25, Boolean.TRUE);
            goto L104
        L42:
            if (StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0) != 4) goto L44;
            StoryPrivacyBottomSheet.m20278$$Nest$fputactivePage(this.this$0, 4);
            StoryPrivacyBottomSheet.m20276$$Nest$fgetviewPager(this.this$0).scrollToPosition(1);
        L44:
            StoryPrivacyBottomSheet.m20286$$Nest$fputselectedType(this.this$0, 4);
            updateCheckboxes(true);
            return;
        L36:
            if (StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0) != 2) goto L38;
            StoryPrivacyBottomSheet.m20278$$Nest$fputactivePage(this.this$0, 2);
            StoryPrivacyBottomSheet.m20276$$Nest$fgetviewPager(this.this$0).scrollToPosition(1);
        L38:
            StoryPrivacyBottomSheet.m20286$$Nest$fputselectedType(this.this$0, 2);
            updateCheckboxes(true);
            return;
        L26:
            if (StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0) != 3) goto L28;
        L31:
            StoryPrivacyBottomSheet.m20278$$Nest$fputactivePage(this.this$0, 3);
            StoryPrivacyBottomSheet.m20276$$Nest$fgetviewPager(this.this$0).scrollToPosition(1);
        L32:
            StoryPrivacyBottomSheet.m20286$$Nest$fputselectedType(this.this$0, 3);
            updateCheckboxes(true);
            return;
        L28:
            if (StoryPrivacyBottomSheet.m20268$$Nest$fgetselectedContacts(this.this$0).isEmpty() == false) goto L32;
            if (StoryPrivacyBottomSheet.m20269$$Nest$fgetselectedContactsByGroup(this.this$0).isEmpty() == false) goto L32;
        L18:
            if (StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0) != 1) goto L20;
        L21:
            StoryPrivacyBottomSheet.m20278$$Nest$fputactivePage(this.this$0, 1);
            StoryPrivacyBottomSheet.m20276$$Nest$fgetviewPager(this.this$0).scrollToPosition(1);
        L22:
            StoryPrivacyBottomSheet.m20286$$Nest$fputselectedType(this.this$0, 1);
            updateCheckboxes(true);
            return;
        L20:
            if (StoryPrivacyBottomSheet.m20290$$Nest$mgetCloseFriends(this.this$0).isEmpty() == false) goto L22;
        L12:
            if (StoryPrivacyBottomSheet.m20251$$Nest$fgetcanChangePeer(this.this$0) == false) goto L15;
            new ChoosePeerSheet(r19, StoryPrivacyBottomSheet.access$6700(this.this$0), StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0), this.this$0.selectedPeer, new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda15(this), StoryPrivacyBottomSheet.access$6800(this.this$0)).show();
            return;
        L108:
            if (r3 != 7) goto L150;
            if ((r20 instanceof TextCell) == false) goto L191;
            TextCell r06 = (TextCell) r20;
            r06.setChecked(!r06.isChecked());
            r2.checked = r06.isChecked();
            int r26 = r2.resId;
            if (r26 != 0) goto L131;
            StoryPrivacyBottomSheet.m20280$$Nest$fputallowScreenshots(this.this$0, r06.isChecked());
            if (StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0) != 4) goto L117;
            r6 = 1;
        L117:
            boolean r07 = StoryPrivacyBottomSheet.m20247$$Nest$fgetallowScreenshots(this.this$0);
            StoryPrivacyBottomSheet r1 = this.this$0;
            if (r07 == false) goto L125;
            BulletinFactory r08 = BulletinFactory.of(r1.container, StoryPrivacyBottomSheet.access$7400(r1));
            int r12 = R.raw.ic_save_to_gallery;
            if (r6 == 0) goto L122;
            int r27 = R.string.StoryEnabledScreenshotsShare;
        L123:
            r08.createSimpleBulletin(r12, LocaleController.getString(r27), 4).setDuration(5000).show(true);
            return;
        L122:
            r27 = R.string.StoryEnabledScreenshots;
            goto L123
        L125:
            BulletinFactory r09 = BulletinFactory.of(r1.container, StoryPrivacyBottomSheet.access$7500(r1));
            int r13 = R.raw.passcode_lock_close;
            if (r6 == 0) goto L128;
            int r28 = R.string.StoryDisabledScreenshotsShare;
        L129:
            r09.createSimpleBulletin(r13, LocaleController.getString(r28), 4).setDuration(5000).show(true);
            return;
        L128:
            r28 = R.string.StoryDisabledScreenshots;
            goto L129
        L131:
            if (r26 != 1) goto L146;
            StoryPrivacyBottomSheet.m20284$$Nest$fputkeepOnMyPage(this.this$0, r06.isChecked());
            StoryPrivacyBottomSheet r010 = this.this$0;
            boolean r29 = r010.selectedPeer instanceof TLRPC.TL_inputPeerChannel;
            boolean r011 = StoryPrivacyBottomSheet.m20261$$Nest$fgetkeepOnMyPage(r010);
            StoryPrivacyBottomSheet r49 = this.this$0;
            if (r011 == false) goto L139;
            BulletinFactory r012 = BulletinFactory.of(r49.container, StoryPrivacyBottomSheet.access$7600(r49));
            int r410 = R.raw.msg_story_keep;
            if (r29 == false) goto L137;
            int r210 = R.string.StoryChannelEnableKeep;
        L138:
            r012.createSimpleBulletin(r410, LocaleController.getString(r210), 4).setDuration(5000).show(true);
        L144:
            updateItems(true);
            return;
        L137:
            r210 = R.string.StoryEnableKeep;
            goto L138
        L139:
            BulletinFactory r013 = BulletinFactory.of(r49.container, StoryPrivacyBottomSheet.access$7700(r49));
            int r411 = R.raw.fire_on;
            if (r29 == false) goto L142;
            int r211 = R.string.StoryChannelDisableKeep;
        L143:
            r013.createSimpleBulletin(r411, LocaleController.getString(r211), 4).setDuration(5000).show(true);
            goto L144
        L142:
            r211 = R.string.StoryDisableKeep;
            goto L143
        L146:
            if (r26 != 2) goto L186;
            StoryPrivacyBottomSheet.m20279$$Nest$fputallowComments(this.this$0, r06.isChecked());
            updateItems(true);
            return;
        L186:
            return;
        L191:
            return;
        L150:
            if (r3 != 9) goto L187;
            int r212 = r2.id;
            if (r212 == 0) goto L154;
            if (r212 != 1) goto L161;
            long r213 = getDialogId();
            ItemOptions r014 = ItemOptions.makeOptions(this, StoryPrivacyBottomSheet.access$7800(this.this$0), r20);
            r014.add(R.drawable.msg_addfolder, LocaleController.getString(R.string.StoriesAlbumNewAlbum), new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda17(this, r213));
            r014.addGap();
            ItemOptions.addAlbumsItemOptions(r014, this.this$0.getStoriesController().getStoryAlbumsList(r213), StoryPrivacyBottomSheet.m20267$$Nest$fgetselectedAlbums(this.this$0), false, null, new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda18(this, r014));
            r014.show();
            return;
        L161:
            if (r212 != 5) goto L168;
            AlertDialog r015 = new AlertDialog(getContext(), 3, StoryPrivacyBottomSheet.access$7900(this.this$0));
            r015.showDelayed(500);
            TL_phone.getGroupCallStreamRtmpUrl r214 = new TL_phone.getGroupCallStreamRtmpUrl();
            r214.live_story = true;
            TLRPC.InputPeer r32 = this.this$0.selectedPeer;
            if (r32 != null) goto L165;
            r32 = new TLRPC.TL_inputPeerSelf();
        L165:
            r214.peer = r32;
            ConnectionsManager.getInstance(StoryPrivacyBottomSheet.access$8000(this.this$0)).sendRequest(r214, new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda19(this, r015, r214));
            return;
        L168:
            if (r212 != 6) goto L189;
            StoryPrivacyBottomSheet.m20283$$Nest$fputisRtmpStream(this.this$0, false);
            updateItems(true);
            return;
        L189:
            return;
        L154:
            if (StoryPrivacyBottomSheet.m20277$$Nest$fgetwhenCoverClicked(this.this$0) == null) goto L188;
            StoryPrivacyBottomSheet.m20277$$Nest$fgetwhenCoverClicked(this.this$0).run();
            return;
        L188:
            return;
        L187:
            return;
        }

        private /* synthetic */ void lambda$new$2(TLRPC.InputPeer r3) {
            StoryPrivacyBottomSheet r0 = this.this$0;
            r0.selectedPeer = r3;
            StoryPrivacyBottomSheet.m20267$$Nest$fgetselectedAlbums(r0).clear();
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == false) goto L8;
            if (StoryPrivacyBottomSheet.m20260$$Nest$fgetisRtmpStream(this.this$0) == false) goto L8;
            StoryPrivacyBottomSheet.m20283$$Nest$fputisRtmpStream(this.this$0, false);
        L8:
            if (StoryPrivacyBottomSheet.m20266$$Nest$fgetonSelectedPeer(this.this$0) == null) goto L11;
            StoryPrivacyBottomSheet.m20266$$Nest$fgetonSelectedPeer(this.this$0).run(this.this$0.selectedPeer);
        L11:
            if (StoryPrivacyBottomSheet.m20265$$Nest$fgetonSelectedAlbums(this.this$0) == null) goto L13;
            StoryPrivacyBottomSheet.m20265$$Nest$fgetonSelectedAlbums(this.this$0).run(new HashSet(StoryPrivacyBottomSheet.m20267$$Nest$fgetselectedAlbums(this.this$0)));
        L13:
            updateItems(true);
        }

        private /* synthetic */ void lambda$new$3(long r9, TLRPC.TL_channels_channelParticipants r11) {
            AlertDialog r0 = this.progressDialog;
            if (r0 == null) goto L5;
            r0.dismissUnless(350);
            this.progressDialog = null;
        L5:
            if (r11 != null) goto L7;
            return;
        L7:
            if (r11.participants.isEmpty() == true) goto L26;
            TLRPC.TL_chatParticipants r02 = new TLRPC.TL_chatParticipants();
            int r1 = 0;
        L11:
            if (r1 >= r11.participants.size()) goto L20;
            TLRPC.ChannelParticipant r2 = r11.participants.get(r1);
            TLRPC.TL_chatParticipant r3 = new TLRPC.TL_chatParticipant();
            TLRPC.Peer r4 = r2.peer;
            if (r4 == null) goto L17;
            long r42 = DialogObject.getPeerDialogId(r4);
            if (r42 < 0) goto L19;
        L18:
            r3.user_id = r42;
            r02.participants.add(r3);
        L19:
            r1 = r1 + 1;
            goto L11
        L17:
            r42 = r2.user_id;
            goto L18
        L20:
            selectChat(r9, r02);
            return;
        }

        private /* synthetic */ void lambda$new$4(boolean r3, long r4) {
            StoryPrivacyBottomSheet r0 = this.this$0;
            if (r3 == false) goto L6;
            MessagesController.getInstance(StoryPrivacyBottomSheet.access$8400(r0)).loadChannelParticipants(Long.valueOf(r4), new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda28(this, r4), 200);
            return;
        L6:
            MessagesController.getInstance(StoryPrivacyBottomSheet.access$8500(r0)).loadFullChat(r4, 0, true);
        }

        private /* synthetic */ void lambda$new$5(long r1, TLRPC.ChatFull r3) {
            selectChat(r1, r3.participants);
        }

        private /* synthetic */ void lambda$new$6(TLRPC.Chat r8, MessagesStorage r9, long r10) {
            boolean r3 = ChatObject.isChannel(r8);
            TLRPC.ChatFull r82 = r9.loadChatInfoInQueue(r10, r3, true, true, 0);
            if (r82 == null) goto L13;
            TLRPC.ChatParticipants r92 = r82.participants;
            if (r92 == null) goto L13;
            ArrayList<TLRPC.ChatParticipant> r93 = r92.participants;
            if (r93 != null) goto L9;
        L11:
            AndroidUtilities.runOnUIThread(new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda23(this, r10, r82));
            return;
        L9:
            if (r93.size() >= (r82.participants_count - 1)) goto L11;
        L13:
            AndroidUtilities.runOnUIThread(new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda22(this, r3, r10));
        }

        private /* synthetic */ void lambda$new$7(StoriesController.StoryAlbum r2) {
            StoryPrivacyBottomSheet.m20267$$Nest$fgetselectedAlbums(this.this$0).add(Integer.valueOf(r2.album_id));
            updateItems(true);
            if (StoryPrivacyBottomSheet.m20265$$Nest$fgetonSelectedAlbums(this.this$0) == null) goto L6;
            StoryPrivacyBottomSheet.m20265$$Nest$fgetonSelectedAlbums(this.this$0).run(new HashSet(StoryPrivacyBottomSheet.m20267$$Nest$fgetselectedAlbums(this.this$0)));
            return;
        }

        private /* synthetic */ void lambda$new$8(long r3, String r5) {
            this.this$0.getStoriesController().createAlbum(r3, r5, new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda27(this));
        }

        private /* synthetic */ void lambda$new$9(long r5) {
            Context r0 = getContext();
            StoryPrivacyBottomSheet r1 = this.this$0;
            AlertsCreator.createStoriesAlbumEnterNameForCreate(r0, r1.attachedFragment, StoryPrivacyBottomSheet.access$8300(r1), new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda25(this, r5));
        }

        private /* synthetic */ void lambda$onButton1Click$16(TLObject r7, MessagesController r8) {
            this.button.setLoading(false);
            if (r7 == null) goto L18;
            ArrayList r72 = StoryPrivacyBottomSheet.m20291$$Nest$mgetContacts(this.this$0);
            int r0 = 0;
        L6:
            if (r0 >= r72.size()) goto L18;
            TLRPC.User r2 = (TLRPC.User) r72.get(r0);
            if (r2 == null) goto L17;
            boolean r3 = this.selectedUsers.contains(Long.valueOf(r2.id));
            if (r3 == r2.close_friend) goto L17;
            r2.close_friend = r3;
            int r4 = r2.flags2;
            if (r3 == false) goto L15;
            int r32 = r4 | 4;
        L16:
            r2.flags2 = r32;
            r8.putUser(r2, false);
            goto L17
        L15:
            r32 = r4 & (-5);
        L17:
            r0 = r0 + 1;
        L18:
            this.this$0.closeKeyboard();
            boolean r73 = StoryPrivacyBottomSheet.m20258$$Nest$fgetisEdit(this.this$0);
            StoryPrivacyBottomSheet r82 = this.this$0;
            if (r73 == false) goto L22;
            StoryPrivacyBottomSheet.m20288$$Nest$mdone(r82, new StoryPrivacy(1, StoryPrivacyBottomSheet.access$6600(r82), null), new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda11(this.this$0));
            return;
        L22:
            r82.closeKeyboard();
            StoryPrivacyBottomSheet.m20276$$Nest$fgetviewPager(this.this$0).scrollToPosition(0);
        }

        private /* synthetic */ void lambda$onButton1Click$17(MessagesController r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda26(this, r2, r1));
        }

        private /* synthetic */ void lambda$onButton1Click$18() {
            Bulletin.removeDelegate(this.this$0.container);
            StoryPrivacyBottomSheet.access$6501(this.this$0);
        }

        private /* synthetic */ void lambda$onButton1Click$19() {
            this.button.setLoading(false);
            this.this$0.closeKeyboard();
            StoryPrivacyBottomSheet.m20276$$Nest$fgetviewPager(this.this$0).scrollToPosition(0);
        }

        private /* synthetic */ void lambda$onButton2Click$20(ArrayList r5) {
            StoryPrivacyBottomSheet r0 = this.this$0;
            StoryPrivacyBottomSheet.m20288$$Nest$mdone(r0, new StoryPrivacy(5, StoryPrivacyBottomSheet.access$6400(r0), r5), new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda11(this.this$0));
        }

        private /* synthetic */ void lambda$onMeasure$24() {
            this.keyboardMoving = false;
        }

        private /* synthetic */ void lambda$selectChat$15(long r4, ArrayList r6, AlertDialog r7, int r8) {
            this.selectedUsersByGroup.put(Long.valueOf(r4), r6);
            int r42 = r6.size();
            int r5 = 0;
        L3:
            if (r5 >= r42) goto L5;
            Object r82 = r6.get(r5);
            r5 = r5 + 1;
            long r0 = ((Long) r82).longValue();
            this.changelog.put(r0, Boolean.TRUE);
            goto L3
        L5:
            updateSpans(true);
            updateButton(true);
            updateCheckboxes(true);
            r7.dismiss();
            this.searchField.scrollToBottom();
        }

        private /* synthetic */ void lambda$updateItems$21() {
            StoryPrivacyBottomSheet.m20278$$Nest$fputactivePage(this.this$0, 6);
            StoryPrivacyBottomSheet.m20276$$Nest$fgetviewPager(this.this$0).scrollToPosition(1);
        }

        private /* synthetic */ void lambda$updateSearchFieldTop$22(ValueAnimator r1) {
            this.searchField.setTranslationY(((Float) r1.getAnimatedValue()).floatValue());
        }

        private /* synthetic */ void lambda$updateSectionCell$23(View r9) {
            ArrayList<Long> r92 = this.selectedUsers;
            int r0 = r92.size();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L5;
            Long r3 = r92.get(r2);
            r2 = r2 + 1;
            this.changelog.put(r3.longValue(), Boolean.FALSE);
            goto L3
        L5:
            Iterator<ArrayList<Long>> r93 = this.selectedUsersByGroup.values().iterator();
        L7:
            if (r93.hasNext() == false) goto L11;
            ArrayList<Long> r02 = r93.next();
            int r22 = r02.size();
            int r32 = 0;
        L9:
            if (r32 >= r22) goto L7;
            Long r4 = r02.get(r32);
            r32 = r32 + 1;
            this.changelog.put(r4.longValue(), Boolean.FALSE);
            goto L9
        L11:
            this.selectedUsers.clear();
            this.selectedUsersByGroup.clear();
            StoryPrivacyBottomSheet.m20263$$Nest$fgetmessageUsers(this.this$0).clear();
            this.searchField.spansContainer.removeAllSpans(true);
            updateCheckboxes(true);
            updateButton(true);
        }

        private boolean match(TLObject r5, String r6) {
            if (TextUtils.isEmpty(r6) == false) goto L6;
            return true;
        L6:
            if ((r5 instanceof TLRPC.User) == false) goto L31;
            TLRPC.User r52 = (TLRPC.User) r5;
            String r4 = AndroidUtilities.translitSafe(UserObject.getUserName(r52)).toLowerCase();
            if (r4.startsWith(r6) == false) goto L10;
        L29:
            return true;
        L10:
            if (r4.contains(" " + r6) == true) goto L29;
            String r42 = AndroidUtilities.translitSafe(UserObject.getPublicUsername(r52)).toLowerCase();
            if (r42.startsWith(r6) == true) goto L29;
            if (r42.contains(" " + r6) == true) goto L29;
            ArrayList<TLRPC.TL_username> r43 = r52.usernames;
            if (r43 == null) goto L55;
            int r53 = 0;
        L21:
            if (r53 >= r43.size()) goto L55;
            TLRPC.TL_username r2 = r43.get(r53);
            if (r2.active == false) goto L28;
            if (AndroidUtilities.translitSafe(r2.username).toLowerCase().startsWith(r6) == false) goto L28;
            return true;
        L28:
            r53 = r53 + 1;
        L55:
            return false;
        L31:
            if ((r5 instanceof TLRPC.Chat) == false) goto L55;
            TLRPC.Chat r54 = (TLRPC.Chat) r5;
            String r44 = AndroidUtilities.translitSafe(r54.title).toLowerCase();
            if (r44.startsWith(r6) == false) goto L35;
        L54:
            return true;
        L35:
            if (r44.contains(" " + r6) == true) goto L54;
            String r45 = AndroidUtilities.translitSafe(ChatObject.getPublicUsername(r54)).toLowerCase();
            if (r45.startsWith(r6) == true) goto L54;
            if (r45.contains(" " + r6) == true) goto L54;
            ArrayList<TLRPC.TL_username> r46 = r54.usernames;
            if (r46 == null) goto L55;
            int r55 = 0;
        L46:
            if (r55 >= r46.size()) goto L55;
            TLRPC.TL_username r22 = r46.get(r55);
            if (r22.active == false) goto L53;
            if (AndroidUtilities.translitSafe(r22.username).toLowerCase().startsWith(r6) == false) goto L53;
            return true;
        L53:
            r55 = r55 + 1;
            goto L46
        }

        private void onButton1Click(View r5) {
            if (this.button.isLoading() == true) goto L61;
            MessagesController r52 = MessagesController.getInstance(StoryPrivacyBottomSheet.access$2600(this.this$0));
            int r0 = this.pageType;
            if (r0 != 5) goto L13;
            if (StoryPrivacyBottomSheet.m20264$$Nest$fgetonDone2(this.this$0) == null) goto L10;
            StoryPrivacyBottomSheet.m20264$$Nest$fgetonDone2(this.this$0).run(this.selectedUsers);
        L10:
            this.this$0.dismiss();
            return;
        L13:
            if (r0 != 1) goto L17;
            TLRPC.TL_editCloseFriends r02 = new TLRPC.TL_editCloseFriends();
            r02.id.addAll(this.selectedUsers);
            this.button.setLoading(true);
            ConnectionsManager.getInstance(StoryPrivacyBottomSheet.access$2700(this.this$0)).sendRequest(r02, new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda10(this, r52));
            return;
        L17:
            if (r0 != 0) goto L35;
            boolean r03 = StoryPrivacyBottomSheet.m20249$$Nest$fgetapplyWhenDismiss(this.this$0);
            StoryPrivacyBottomSheet r1 = this.this$0;
            if (r03 == true) goto L32;
            int r04 = StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(r1);
            StoryPrivacyBottomSheet r12 = this.this$0;
            if (r04 != 3) goto L23;
            HashSet r53 = StoryPrivacyBottomSheet.m20294$$Nest$mmergeUsers(r12, StoryPrivacyBottomSheet.m20268$$Nest$fgetselectedContacts(r12), StoryPrivacyBottomSheet.m20269$$Nest$fgetselectedContactsByGroup(this.this$0));
            StoryPrivacy r05 = new StoryPrivacy(StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0), StoryPrivacyBottomSheet.access$2800(this.this$0), new ArrayList(r53));
            r05.selectedUserIds.clear();
            r05.selectedUserIds.addAll(StoryPrivacyBottomSheet.m20268$$Nest$fgetselectedContacts(this.this$0));
            r05.selectedUserIdsByGroup.clear();
            r05.selectedUserIdsByGroup.putAll(StoryPrivacyBottomSheet.m20269$$Nest$fgetselectedContactsByGroup(this.this$0));
        L30:
            StoryPrivacyBottomSheet r4 = this.this$0;
            StoryPrivacyBottomSheet.m20288$$Nest$mdone(r4, r05, new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda11(r4));
            return;
        L23:
            int r06 = StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(r12);
            StoryPrivacyBottomSheet r13 = this.this$0;
            if (r06 != 2) goto L26;
            r05 = new StoryPrivacy(StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(r13), StoryPrivacyBottomSheet.access$2900(this.this$0), StoryPrivacyBottomSheet.m20254$$Nest$fgetexcludedContacts(this.this$0));
            goto L30
        L26:
            int r54 = StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(r13);
            StoryPrivacyBottomSheet r07 = this.this$0;
            if (r54 != 4) goto L29;
            HashSet r55 = StoryPrivacyBottomSheet.m20294$$Nest$mmergeUsers(r07, StoryPrivacyBottomSheet.m20255$$Nest$fgetexcludedEveryone(r07), StoryPrivacyBottomSheet.m20256$$Nest$fgetexcludedEveryoneByGroup(this.this$0));
            r05 = new StoryPrivacy(StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0), StoryPrivacyBottomSheet.access$3000(this.this$0), new ArrayList(r55));
            r05.selectedUserIds.clear();
            r05.selectedUserIds.addAll(StoryPrivacyBottomSheet.m20255$$Nest$fgetexcludedEveryone(this.this$0));
            r05.selectedUserIdsByGroup.clear();
            r05.selectedUserIdsByGroup.putAll(StoryPrivacyBottomSheet.m20256$$Nest$fgetexcludedEveryoneByGroup(this.this$0));
            goto L30
        L29:
            r05 = new StoryPrivacy(StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(r07), StoryPrivacyBottomSheet.access$3100(this.this$0), null);
            goto L30
        L32:
            r1.dismiss();
            return;
        L35:
            if (r0 != 2) goto L42;
            boolean r08 = StoryPrivacyBottomSheet.m20258$$Nest$fgetisEdit(this.this$0);
            StoryPrivacyBottomSheet r14 = this.this$0;
            if (r08 == false) goto L40;
            r14.closeKeyboard();
            StoryPrivacyBottomSheet r09 = this.this$0;
            StoryPrivacyBottomSheet.m20288$$Nest$mdone(r09, new StoryPrivacy(2, StoryPrivacyBottomSheet.access$3200(r09), this.selectedUsers), new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda11(this.this$0));
            return;
        L40:
            r14.closeKeyboard();
            StoryPrivacyBottomSheet.m20276$$Nest$fgetviewPager(this.this$0).scrollToPosition(0);
            return;
        L42:
            if (r0 != 3) goto L55;
            boolean r56 = StoryPrivacyBottomSheet.m20258$$Nest$fgetisEdit(this.this$0);
            StoryPrivacyBottomSheet r010 = this.this$0;
            if (r56 == false) goto L51;
            HashSet r57 = StoryPrivacyBottomSheet.m20294$$Nest$mmergeUsers(r010, this.selectedUsers, this.selectedUsersByGroup);
            if (r57.isEmpty() == true) goto L62;
            this.this$0.closeKeyboard();
            StoryPrivacy r011 = new StoryPrivacy(3, StoryPrivacyBottomSheet.access$3300(this.this$0), new ArrayList(r57));
            r011.selectedUserIds.clear();
            r011.selectedUserIds.addAll(this.selectedUsers);
            r011.selectedUserIdsByGroup.clear();
            r011.selectedUserIdsByGroup.putAll(this.selectedUsersByGroup);
            StoryPrivacyBottomSheet.m20288$$Nest$mdone(this.this$0, r011, new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda12(this));
            return;
        L62:
            return;
        L51:
            if (StoryPrivacyBottomSheet.m20294$$Nest$mmergeUsers(r010, this.selectedUsers, this.selectedUsersByGroup).isEmpty() == false) goto L53;
            return;
        L53:
            StoryPrivacyBottomSheet.m20286$$Nest$fputselectedType(this.this$0, 3);
            this.this$0.closeKeyboard();
            StoryPrivacyBottomSheet.m20276$$Nest$fgetviewPager(this.this$0).scrollToPosition(0);
            return;
        L55:
            StoryPrivacyBottomSheet r58 = this.this$0;
            if (r0 != 6) goto L59;
            HashSet<Long> r59 = StoryPrivacyBottomSheet.m20294$$Nest$mmergeUsers(r58, this.selectedUsers, this.selectedUsersByGroup);
            this.button.setLoading(true);
            MessagesController.getInstance(StoryPrivacyBottomSheet.access$3400(this.this$0)).getStoriesController().updateBlockedUsers(r59, new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda13(this));
            return;
        L59:
            StoryPrivacyBottomSheet.m20286$$Nest$fputselectedType(r58, r0);
            this.this$0.closeKeyboard();
            StoryPrivacyBottomSheet.m20276$$Nest$fgetviewPager(this.this$0).scrollToPosition(0);
            return;
        }

        private void onButton2Click(View r5) {
            if (StoryPrivacyBottomSheet.m20273$$Nest$fgetstartedFromSendAsMessage(this.this$0) == false) goto L6;
            StoryPrivacyBottomSheet.m20278$$Nest$fputactivePage(this.this$0, 5);
            StoryPrivacyBottomSheet.m20276$$Nest$fgetviewPager(this.this$0).scrollToPosition(1);
            return;
        L6:
            StoryPrivacyBottomSheet r52 = StoryPrivacyBottomSheet.m20295$$Nest$mwhenSelectedShare(new StoryPrivacyBottomSheet(5, getContext(), StoryPrivacyBottomSheet.access$3500(this.this$0), null), new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda21(this));
            StoryPrivacyBottomSheet.m20287$$Nest$fputstoryPeriod(r52, StoryPrivacyBottomSheet.m20275$$Nest$fgetstoryPeriod(this.this$0));
            r52.show();
        }

        private void selectChat(long r15, TLRPC.ChatParticipants r17) {
            ArrayList<Long> r1 = new ArrayList();
            ArrayList r2 = new ArrayList();
            int r3 = this.pageType;
            int r4 = 0;
            if (r3 != 1) goto L5;
        L8:
            boolean r32 = true;
        L9:
            if (r17 == null) goto L33;
            if (r17.participants == null) goto L33;
            int r6 = 0;
        L14:
            if (r6 >= r17.participants.size()) goto L33;
            long r7 = r17.participants.get(r6).user_id;
            TLRPC.User r9 = MessagesController.getInstance(StoryPrivacyBottomSheet.access$2100(this.this$0)).getUser(Long.valueOf(r7));
            if (r9 == null) goto L31;
            if (UserObject.isUserSelf(r9) == true) goto L31;
            if (r9.bot == true) goto L31;
            if (r9.id == 777000) goto L31;
            if (r7 == 0) goto L31;
            if (r32 == true) goto L27;
        L29:
            r1.add(Long.valueOf(r7));
        L30:
            this.selectedUsers.remove(Long.valueOf(r7));
            goto L31
        L27:
            if (r9.contact == true) goto L29;
            r2.add(Long.valueOf(r7));
        L31:
            r6 = r6 + 1;
        L33:
            if (r2.isEmpty() == false) goto L35;
            this.selectedUsersByGroup.put(Long.valueOf(r15), r1);
            int r0 = r1.size();
        L41:
            if (r4 >= r0) goto L43;
            Long r22 = r1.get(r4);
            r4 = r4 + 1;
            this.changelog.put(r22.longValue(), Boolean.TRUE);
            goto L41
        L43:
            updateSpans(true);
            updateButton(true);
            updateCheckboxes(true);
            this.searchField.scrollToBottom();
            return;
        L35:
            if (r1.isEmpty() == false) goto L38;
            new AlertDialog.Builder(getContext(), StoryPrivacyBottomSheet.access$2200(this.this$0)).setMessage("All group members are not in your contact list.").setNegativeButton("Cancel", null).show();
            return;
        L38:
            new AlertDialog.Builder(getContext(), StoryPrivacyBottomSheet.access$2300(this.this$0)).setMessage(r2.size() + " members are not in your contact list").setPositiveButton("Add " + r1.size() + " contacts", new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda2(this, r15, r1)).setNegativeButton("Cancel", null).show();
            return;
        L5:
            if (r3 == 2) goto L8;
            r32 = false;
            goto L9
        }

        private void updateHeaderTop() {
            boolean r0 = this.containsHeader;
            HeaderCell r1 = this.headerView;
            if (r0 == true) goto L6;
            r1.setVisibility(8);
            return;
        L6:
            boolean r02 = false;
            r1.setVisibility(0);
            float r12 = -this.headerView.getHeight();
            int r2 = 0;
        L8:
            if (r2 >= this.listView.getChildCount()) goto L15;
            View r3 = this.listView.getChildAt(r2);
            if ((r3.getTag() instanceof Integer) == false) goto L14;
            if (((Integer) r3.getTag()).intValue() != 35) goto L14;
            r12 = this.contentView.getPaddingTop() + r3.getY();
        L17:
            if (this.isActionBar == r02) goto L26;
            this.isActionBar = r02;
            BackDrawable r22 = this.headerView.backDrawable;
            if (r02 == false) goto L21;
        L24:
            float r03 = 0.0f;
        L25:
            r22.setRotation(r03, true);
            goto L26
        L21:
            if (this.pageType != 0) goto L24;
            r03 = 1.0f;
        L26:
            this.headerView.setTranslationY(Math.max(AndroidUtilities.statusBarHeight, r12));
            return;
        L14:
            r2 = r2 + 1;
            goto L8
        L15:
            r02 = true;
            goto L17
        }

        private void updateSearchFieldTop() {
            float r0 = getSearchFieldTop();
            if (this.scrolling == false) goto L5;
        L20:
            this.searchTranslationAnimating = false;
            ValueAnimator r1 = this.searchFieldAnimator;
            if (r1 == null) goto L23;
            r1.cancel();
            this.searchFieldAnimator = null;
        L23:
            this.searchField.setTranslationY(r0);
            return;
        L5:
            if (this.keyboardMoving == true) goto L20;
            if (getTranslationX() != 0.0f) goto L20;
            if (this.searchTranslationAnimating == true) goto L12;
        L15:
            this.searchTranslationAnimating = true;
            ValueAnimator r4 = this.searchFieldAnimator;
            if (r4 == null) goto L18;
            r4.cancel();
            this.searchFieldAnimator = null;
        L18:
            float r3 = this.searchField.getTranslationY();
            this.searchTranslationAnimatingTo = r0;
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{r3, r0});
            this.searchFieldAnimator = r02;
            r02.addUpdateListener(new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda14(this));
            this.searchFieldAnimator.addListener(new AnonymousClass4(this));
            this.searchFieldAnimator.setInterpolator(new LinearInterpolator());
            this.searchFieldAnimator.setDuration(180);
            this.searchFieldAnimator.start();
            return;
        L12:
            if (Math.abs(this.searchTranslationAnimatingTo - r0) > 1.0f) goto L15;
        }

        private void updateSectionCell(boolean r4) {
            if (this.sectionCell != null) goto L6;
            return;
        L6:
            if (StoryPrivacyBottomSheet.m20294$$Nest$mmergeUsers(this.this$0, this.selectedUsers, this.selectedUsersByGroup).size() <= 0) goto L9;
            this.sectionCell.setRightText(LocaleController.getString(R.string.UsersDeselectAll), true, new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda20(this));
            return;
        L9:
            GraySectionCell r3 = this.sectionCell;
            if (r4 == false) goto L13;
            r3.setRightText(null);
            return;
        L13:
            r3.setRightText(null, null);
        }

        private void updateSpans(boolean r13) {
            HashSet r0 = StoryPrivacyBottomSheet.m20294$$Nest$mmergeUsers(this.this$0, this.selectedUsers, this.selectedUsersByGroup);
            int r1 = this.pageType;
            if (r1 != 3) goto L6;
            StoryPrivacyBottomSheet.m20285$$Nest$fputselectedContactsCount(this.this$0, r0.size());
        L8:
            MessagesController r12 = MessagesController.getInstance(StoryPrivacyBottomSheet.access$2400(this.this$0));
            ArrayList<GroupCreateSpan> r2 = new ArrayList();
            ArrayList<GroupCreateSpan> r3 = new ArrayList();
            int r5 = 0;
        L10:
            if (r5 >= this.searchField.allSpans.size()) goto L15;
            GroupCreateSpan r6 = this.searchField.allSpans.get(r5);
            if (r0.contains(Long.valueOf(r6.getUid())) == true) goto L14;
            r2.add(r6);
        L14:
            r5 = r5 + 1;
            goto L10
        L15:
            Iterator r02 = r0.iterator();
        L17:
            if (r02.hasNext() == false) goto L34;
            Long r52 = (Long) r02.next();
            long r62 = r52.longValue();
            int r8 = 0;
        L20:
            if (r8 >= this.searchField.allSpans.size()) goto L26;
            if (this.searchField.allSpans.get(r8).getUid() == r62) goto L17;
            r8 = r8 + 1;
            goto L20
        L26:
            if (r62 < 0) goto L29;
            Object r53 = r12.getUser(r52);
        L28:
            Object r82 = r53;
            if (r82 == null) goto L17;
            GroupCreateSpan r63 = new GroupCreateSpan(getContext(), r82, null, true, StoryPrivacyBottomSheet.access$2500(this.this$0));
            r63.setOnClickListener(this);
            r3.add(r63);
            goto L17
        L29:
            r53 = r12.getChat(r52);
            goto L28
        L34:
            if (r2.isEmpty() == true) goto L36;
        L39:
            this.searchField.spansContainer.updateSpans(r2, r3, r13);
            return;
        L36:
            if (r3.isEmpty() == false) goto L39;
            return;
        L6:
            if (r1 != 4) goto L8;
            StoryPrivacyBottomSheet.m20282$$Nest$fputexcludedEveryoneCount(this.this$0, r0.size());
            goto L8
        }

        public void applyBlocklist(boolean r6) {
            if (this.pageType != 6) goto L23;
            this.selectedUsers.clear();
            HashSet<Long> r0 = MessagesController.getInstance(StoryPrivacyBottomSheet.access$3600(this.this$0)).getStoriesController().blocklist;
            this.selectedUsers.addAll(r0);
            int r02 = 0;
        L7:
            if (r02 >= this.changelog.size()) goto L15;
            long r1 = this.changelog.keyAt(r02);
            boolean r3 = this.changelog.valueAt(r02).booleanValue();
            ArrayList<Long> r4 = this.selectedUsers;
            if (r3 == true) goto L11;
            r4.remove(Long.valueOf(r1));
        L14:
            r02 = r02 + 1;
            goto L7
        L11:
            if (r4.contains(Long.valueOf(r1)) == true) goto L14;
            this.selectedUsers.add(Long.valueOf(r1));
            goto L14
        L15:
            if (r6 == false) goto L22;
            updateItems(true);
            updateButton(true);
            updateCheckboxes(true);
            return;
        L22:
            return;
        }

        public boolean atBottom() {
            return !this.listView.canScrollVertically(1);
        }

        public boolean atTop() {
            return !this.listView.canScrollVertically(-1);
        }

        public void bind(int r7) {
            this.pageType = r7;
            this.changelog.clear();
            this.selectedUsers.clear();
            this.selectedUsersByGroup.clear();
            if (r7 != 4) goto L6;
            this.selectedUsers.addAll(StoryPrivacyBottomSheet.m20255$$Nest$fgetexcludedEveryone(this.this$0));
            this.selectedUsersByGroup.putAll(StoryPrivacyBottomSheet.m20256$$Nest$fgetexcludedEveryoneByGroup(this.this$0));
        L23:
            LinearLayoutManager r0 = this.layoutManager;
            this.adapter.reversedLayout = false;
            r0.setReverseLayout(false);
            updateSpans(false);
            this.searchField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            SearchUsersCell r02 = this.searchField;
            if (r7 != 0) goto L26;
            int r72 = 8;
        L27:
            r02.setVisibility(r72);
            this.searchField.scrollToBottom();
            this.query = null;
            updateItems(false);
            updateButton(false);
            updateCheckboxes(false);
            scrollToTop();
            this.listView.requestLayout();
            this.lastSelectedType = -1;
            return;
        L26:
            r72 = 0;
            goto L27
        L6:
            if (r7 != 5) goto L9;
            this.selectedUsers.addAll(StoryPrivacyBottomSheet.m20263$$Nest$fgetmessageUsers(this.this$0));
            goto L23
        L9:
            if (r7 != 1) goto L15;
            ArrayList r03 = StoryPrivacyBottomSheet.m20290$$Nest$mgetCloseFriends(this.this$0);
            int r2 = 0;
        L12:
            if (r2 >= r03.size()) goto L23;
            this.selectedUsers.add(Long.valueOf(((TLRPC.User) r03.get(r2)).id));
            r2 = r2 + 1;
            goto L12
        L15:
            if (r7 != 2) goto L18;
            this.selectedUsers.addAll(StoryPrivacyBottomSheet.m20254$$Nest$fgetexcludedContacts(this.this$0));
            goto L23
        L18:
            if (r7 != 3) goto L21;
            this.selectedUsers.addAll(StoryPrivacyBottomSheet.m20268$$Nest$fgetselectedContacts(this.this$0));
            this.selectedUsersByGroup.putAll(StoryPrivacyBottomSheet.m20269$$Nest$fgetselectedContactsByGroup(this.this$0));
            goto L23
        L21:
            if (r7 != 6) goto L23;
            applyBlocklist(false);
            goto L23
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r5, int r6, Object... r7) {
            if (r5 != NotificationCenter.chatInfoDidLoad) goto L13;
            TLRPC.ChatFull r52 = (TLRPC.ChatFull) r7[0];
            if (r52 == null) goto L14;
            AlertDialog r62 = this.progressDialog;
            if (r62 != null) goto L9;
            return;
        L9:
            if (this.waitingForChatId != r52.id) goto L16;
            r62.dismissUnless(350);
            this.progressDialog = null;
            this.waitingForChatId = -1;
            selectChat(r52.id, r52.participants);
            return;
        L16:
            return;
        L14:
            return;
        }

        public int getTypeOn(MotionEvent r6) {
            if (this.pageType != 0) goto L28;
            if (r6 == null) goto L28;
            View r0 = this.listView.findChildViewUnder(r6.getX(), r6.getY() - this.contentView.getPaddingTop());
            if (r0 != null) goto L9;
            return -1;
        L9:
            int r02 = this.listView.getChildAdapterPosition(r0);
            if (r02 < 0) goto L28;
            if (r02 >= this.items.size()) goto L28;
            ItemInner r03 = this.items.get(r02);
            if (r03.viewType != 3) goto L28;
            if (r03.sendAs == true) goto L28;
            if (LocaleController.isRTL == false) goto L25;
            if (r6.getX() >= (getWidth() - AndroidUtilities.dp(100.0f))) goto L28;
        L27:
            return r03.type;
        L25:
            if (r6.getX() > AndroidUtilities.dp(100.0f)) goto L27;
        L28:
            return -1;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            NotificationCenter.getInstance(StoryPrivacyBottomSheet.access$6100(this.this$0)).addObserver(this, NotificationCenter.chatInfoDidLoad);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r6) {
            if (this.searchField.allSpans.contains(r6) == true) goto L5;
            return;
        L5:
            GroupCreateSpan r62 = (GroupCreateSpan) r6;
            boolean r0 = r62.isDeleting();
            SearchUsersCell r1 = this.searchField;
            if (r0 == false) goto L16;
            SearchUsersCell.m20333$$Nest$fputcurrentDeletingSpan(r1, null);
            this.searchField.spansContainer.removeSpan(r62);
            long r02 = r62.getUid();
            Iterator<Map.Entry<Long, ArrayList<Long>>> r63 = this.selectedUsersByGroup.entrySet().iterator();
        L9:
            if (r63.hasNext() == false) goto L13;
            Map.Entry<Long, ArrayList<Long>> r2 = r63.next();
            if (r2.getValue().contains(Long.valueOf(r02)) == false) goto L9;
            r63.remove();
            this.selectedUsers.addAll(r2.getValue());
            this.selectedUsers.remove(Long.valueOf(r02));
            goto L9
        L13:
            this.selectedUsers.remove(Long.valueOf(r02));
            updateCheckboxes(true);
            updateButton(true);
            return;
        L16:
            if (SearchUsersCell.m20324$$Nest$fgetcurrentDeletingSpan(r1) == null) goto L18;
            SearchUsersCell.m20324$$Nest$fgetcurrentDeletingSpan(this.searchField).cancelDeleteAnimation();
            SearchUsersCell.m20333$$Nest$fputcurrentDeletingSpan(this.searchField, null);
        L18:
            SearchUsersCell.m20333$$Nest$fputcurrentDeletingSpan(this.searchField, r62);
            r62.startDeleteAnimation();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getInstance(StoryPrivacyBottomSheet.access$6200(this.this$0)).removeObserver(this, NotificationCenter.chatInfoDidLoad);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            if (StoryPrivacyBottomSheet.access$5201(this.this$0) <= 0) goto L5;
            this.keyboardHeight = StoryPrivacyBottomSheet.access$5301(this.this$0);
        L5:
            super.onMeasure(r5, r6);
            FrameLayout r52 = this.contentView;
            int r62 = AndroidUtilities.statusBarHeight;
            if (this.pageType != 0) goto L8;
            int r0 = 0;
        L9:
            r52.setPadding(0, r62 + r0, 0, 0);
            if (this.wasKeyboardVisible == StoryPrivacyBottomSheet.access$5400(this.this$0)) goto L36;
            float r53 = getSearchFieldTop();
            if (StoryPrivacyBottomSheet.access$5500(this.this$0) == true) goto L14;
        L16:
            int r54 = this.pageType;
            ButtonContainer r63 = this.buttonContainer;
            float r02 = 0.0f;
            if (r54 == 0) goto L19;
            boolean r55 = StoryPrivacyBottomSheet.access$5800(this.this$0);
            int r2 = this.keyboardHeight;
            if (r55 == false) goto L29;
            float r56 = r2;
        L30:
            r63.translateY(r56, 0.0f);
            View r57 = this.underKeyboardView;
            boolean r64 = StoryPrivacyBottomSheet.access$5900(this.this$0);
            int r22 = this.keyboardHeight;
            if (r64 == false) goto L33;
            float r65 = r22;
        L34:
            r57.setTranslationY(r65);
            this.keyboardMoving = true;
            this.underKeyboardView.animate().translationY(0.0f).setDuration(250).setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator).withEndAction(new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda0(this)).start();
        L35:
            this.wasKeyboardVisible = StoryPrivacyBottomSheet.access$6000(this.this$0);
            goto L36
        L33:
            r65 = -r22;
            goto L34
        L29:
            r56 = -r2;
            goto L30
        L19:
            if (StoryPrivacyBottomSheet.access$5600(this.this$0) == false) goto L21;
            float r58 = this.keyboardHeight;
        L22:
            r63.setTranslationY(r58);
            View r59 = this.underKeyboardView;
            if (StoryPrivacyBottomSheet.access$5700(this.this$0) == false) goto L25;
            r02 = this.keyboardHeight;
        L25:
            r59.setTranslationY(r02);
            goto L35
        L21:
            r58 = 0.0f;
            goto L22
        L14:
            if ((r53 + Math.min(AndroidUtilities.dp(150.0f), this.searchField.resultContainerHeight)) <= this.listView.getPaddingTop()) goto L16;
            scrollToTopSmoothly();
        L36:
            this.listView.setPadding(0, 0, 0, this.buttonContainer.getMeasuredHeight());
            return;
        L8:
            r0 = AndroidUtilities.dp(56.0f);
            goto L9
        }

        public void onSearch(String r2) {
            if (r2 != null) goto L4;
        L6:
            this.query = r2;
            updateItems(false);
            return;
        L4:
            if (r2.isEmpty() == false) goto L6;
            r2 = null;
            goto L6
        }

        public void scrollToTop() {
            if (this.pageType == 0) goto L6;
            this.listView.scrollToPosition(0);
            return;
        }

        public void scrollToTopSmoothly() {
            LinearSmoothScrollerCustom r0 = new LinearSmoothScrollerCustom(getContext(), 2, 0.7f);
            r0.setTargetPosition(1);
            r0.setOffset(-AndroidUtilities.dp(56.0f));
            this.layoutManager.startSmoothScroll(r0);
        }

        public float top() {
            if (this.layoutManager.getReverseLayout() == false) goto L5;
        L8:
            float r0 = AndroidUtilities.displaySize.y;
        L9:
            int r1 = 0;
        L11:
            if (r1 >= this.listView.getChildCount()) goto L35;
            View r2 = this.listView.getChildAt(r1);
            if (this.layoutManager.getReverseLayout() == false) goto L18;
            float r3 = this.contentView.getPaddingTop() + r2.getY();
            float r22 = r2.getAlpha();
            if (r3 >= r0) goto L34;
            r0 = AndroidUtilities.lerp(r0, r3, r22);
        L34:
            r1 = r1 + 1;
            goto L11
        L18:
            if (this.pageType != 0) goto L23;
            if ((r2 instanceof PadView) == true) goto L34;
            r0 = Math.min(this.contentView.getPaddingTop() + r2.getY(), r0);
            goto L34
        L23:
            if ((r2.getTag() instanceof Integer) == false) goto L30;
            if (((Integer) r2.getTag()).intValue() != 33) goto L30;
            float r5 = this.contentView.getPaddingTop() + r2.getBottom();
            float r02 = r2.getTranslationY();
        L28:
            return r5 + r02;
        L30:
            if ((r2.getTag() instanceof Integer) == false) goto L34;
            if (((Integer) r2.getTag()).intValue() != 35) goto L34;
            r5 = this.contentView.getPaddingTop();
            r02 = r2.getY();
            goto L28
        L35:
            return r0;
        L5:
            if (this.pageType == 0) goto L8;
            r0 = 0.0f;
            goto L9
        }

        public void updateButton(boolean r7) {
            int r0 = this.pageType;
            int r1 = 8;
            boolean r2 = true;
            int r3 = 0;
            if (r0 != 0) goto L23;
            this.button.setShowZero(false);
            this.button.setEnabled(true);
            this.button.setCount(0, r7);
            if (StoryPrivacyBottomSheet.m20262$$Nest$fgetliveSettings(this.this$0) == false) goto L8;
            this.button.setText(LocaleController.getString(R.string.StoryPrivacyButtonSave), r7);
        L18:
            ButtonWithCounterView r72 = this.button2;
            if (StoryPrivacyBottomSheet.m20272$$Nest$fgetsendAsMessageEnabled(this.this$0) == false) goto L21;
            r1 = 0;
        L21:
            r72.setVisibility(r1);
            return;
        L8:
            if (StoryPrivacyBottomSheet.m20258$$Nest$fgetisEdit(this.this$0) == false) goto L10;
            this.button.setText(LocaleController.getString(R.string.StoryPrivacyButtonSave), r7);
            goto L18
        L10:
            int r02 = StoryPrivacyBottomSheet.m20274$$Nest$fgetstoriesCount(this.this$0);
            ButtonWithCounterView r4 = this.button;
            if (r02 == 1) goto L13;
            r4.setText(LocaleController.formatPluralStringComma("StoryPrivacyButtonPostMultiple", StoryPrivacyBottomSheet.m20274$$Nest$fgetstoriesCount(this.this$0)), r7);
            goto L18
        L13:
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == false) goto L15;
            int r03 = R.string.StoryLivePrivacyButtonPost;
        L16:
            r4.setText(LocaleController.getString(r03), r7);
            goto L18
        L15:
            r03 = R.string.StoryPrivacyButtonPost;
            goto L16
        L23:
            if (r0 != 1) goto L27;
            this.button.setShowZero(false);
            this.button.setEnabled(true);
            this.button.setText(LocaleController.getString(R.string.StoryPrivacyButtonSaveCloseFriends), r7);
            this.button.setCount(this.selectedUsers.size(), r7);
            this.button2.setVisibility(8);
            return;
        L27:
            if (r0 != 3) goto L39;
            StoryPrivacyBottomSheet r04 = this.this$0;
            int r42 = StoryPrivacyBottomSheet.m20294$$Nest$mmergeUsers(r04, this.selectedUsers, this.selectedUsersByGroup).size();
            StoryPrivacyBottomSheet.m20285$$Nest$fputselectedContactsCount(r04, r42);
            this.button.setText(LocaleController.getString(R.string.StoryPrivacyButtonSave), r7);
            this.button.setShowZero(false);
            ButtonContainer r05 = this.buttonContainer;
            if (r42 > 0) goto L31;
            boolean r5 = true;
        L32:
            r05.hide(r5, r7);
            this.button.setCount(r42, r7);
            ButtonWithCounterView r73 = this.button;
            if (r42 > 0) goto L36;
            r2 = false;
        L36:
            r73.setEnabled(r2);
            this.button2.setVisibility(8);
            return;
        L31:
            r5 = false;
            goto L32
        L39:
            if (r0 != 2) goto L47;
            this.button.setShowZero(false);
            this.button.setEnabled(true);
            boolean r06 = this.selectedUsers.isEmpty();
            ButtonWithCounterView r22 = this.button;
            if (r06 == false) goto L43;
            r22.setText(LocaleController.getString(R.string.StoryPrivacyButtonSave), r7);
            this.button.setCount(0, r7);
        L44:
            this.button2.setVisibility(8);
            return;
        L43:
            r22.setText(LocaleController.getString(R.string.StoryPrivacyButtonExcludeContacts), r7);
            this.button.setCount(this.selectedUsers.size(), r7);
            goto L44
        L47:
            if (r0 != 5) goto L51;
            this.button.setShowZero(true);
            this.button.setEnabled(true ^ this.selectedUsers.isEmpty());
            this.button.setCount(this.selectedUsers.size(), r7);
            this.button2.setVisibility(8);
            return;
        L51:
            if (r0 != 6) goto L62;
            this.button.setShowZero(false);
            this.button.setEnabled(true);
            this.button.setText(LocaleController.getString(R.string.StoryPrivacyButtonSaveCloseFriends), r7);
            StoriesController r07 = MessagesController.getInstance(StoryPrivacyBottomSheet.access$5100(this.this$0)).getStoriesController();
            if (r07.blocklistFull == false) goto L55;
            this.button.setCount(this.selectedUsers.size(), r7);
        L59:
            this.button2.setVisibility(8);
            return;
        L55:
            r07.getBlocklistCount();
        L57:
            if (r3 >= this.changelog.size()) goto L59;
            long r43 = this.changelog.keyAt(r3);
            this.changelog.valueAt(r3).booleanValue();
            r07.blocklist.contains(Long.valueOf(r43));
            r3 = r3 + 1;
            goto L57
        L62:
            if (r0 != 4) goto L66;
            StoryPrivacyBottomSheet r08 = this.this$0;
            int r44 = StoryPrivacyBottomSheet.m20294$$Nest$mmergeUsers(r08, StoryPrivacyBottomSheet.m20255$$Nest$fgetexcludedEveryone(r08), StoryPrivacyBottomSheet.m20256$$Nest$fgetexcludedEveryoneByGroup(this.this$0)).size();
            StoryPrivacyBottomSheet.m20282$$Nest$fputexcludedEveryoneCount(r08, r44);
            this.button.setText(LocaleController.getString(R.string.StoryPrivacyButtonSave), r7);
            this.button.setShowZero(false);
            this.buttonContainer.hide(false, r7);
            this.button.setCount(r44, r7);
            this.button.setEnabled(true);
            this.button2.setVisibility(8);
            return;
        }

        public void updateCheckboxes(boolean r10) {
            int r0 = this.pageType;
            if (r0 != 4) goto L6;
            StoryPrivacyBottomSheet.m20255$$Nest$fgetexcludedEveryone(this.this$0).clear();
            StoryPrivacyBottomSheet.m20256$$Nest$fgetexcludedEveryoneByGroup(this.this$0).clear();
            StoryPrivacyBottomSheet.m20255$$Nest$fgetexcludedEveryone(this.this$0).addAll(this.selectedUsers);
            StoryPrivacyBottomSheet.m20256$$Nest$fgetexcludedEveryoneByGroup(this.this$0).putAll(this.selectedUsersByGroup);
        L13:
            if (this.pageType == 3) goto L15;
        L29:
            HashSet r02 = StoryPrivacyBottomSheet.m20294$$Nest$mmergeUsers(this.this$0, this.selectedUsers, this.selectedUsersByGroup);
            int r2 = 0;
        L30:
            boolean r4 = true;
            if (r2 >= this.items.size()) goto L54;
            ItemInner r3 = this.items.get(r2);
            if (r3 == null) goto L53;
            if (r3.type > 0) goto L37;
            TLRPC.User r5 = r3.user;
            if (r5 == null) goto L50;
            boolean r52 = this.selectedUsers.contains(Long.valueOf(r5.id));
            r3.checked = r52;
            if (r52 == false) goto L46;
        L48:
            r4 = false;
        L49:
            r3.halfChecked = r4;
            goto L53
        L46:
            if (r02.contains(Long.valueOf(r3.user.id)) == false) goto L48;
        L50:
            TLRPC.Chat r42 = r3.chat;
            if (r42 == null) goto L53;
            r3.checked = this.selectedUsersByGroup.containsKey(Long.valueOf(r42.id));
            r3.halfChecked = false;
            goto L53
        L37:
            if (StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0) == r3.type) goto L40;
            r4 = false;
        L40:
            r3.checked = r4;
            r3.halfChecked = false;
        L53:
            r2 = r2 + 1;
            goto L30
        L54:
            int r03 = 0;
        L56:
            if (r03 >= this.listView.getChildCount()) goto L83;
            View r22 = this.listView.getChildAt(r03);
            if ((r22 instanceof UserCell) == false) goto L82;
            int r32 = this.listView.getChildAdapterPosition(r22);
            if (r32 < 0) goto L82;
            if (r32 >= this.items.size()) goto L82;
            ItemInner r33 = this.items.get(r32);
            UserCell r23 = (UserCell) r22;
            if (r33.checked == false) goto L66;
        L69:
            boolean r53 = true;
        L70:
            r23.setChecked(r53, r10);
            TLRPC.Chat r54 = r33.chat;
            float r6 = 1.0f;
            if (r54 == null) goto L77;
            if (StoryPrivacyBottomSheet.m20292$$Nest$mgetParticipantsCount(this.this$0, r54) <= 200) goto L75;
            r6 = 0.3f;
        L75:
            r23.setCheckboxAlpha(r6, r10);
            goto L82
        L77:
            if (r33.halfChecked == true) goto L79;
        L81:
            r23.setCheckboxAlpha(r6, r10);
            goto L82
        L79:
            if (r33.checked == true) goto L81;
            r6 = 0.5f;
            goto L81
        L66:
            if (r33.halfChecked == true) goto L69;
            r53 = false;
        L82:
            r03 = r03 + 1;
            goto L56
        L83:
            updateSectionCell(r10);
            return;
        L15:
            if (StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0) != 3) goto L21;
            if (this.selectedUsers.isEmpty() == false) goto L29;
            if (this.selectedUsersByGroup.isEmpty() == false) goto L29;
        L21:
            if (this.selectedUsers.isEmpty() == true) goto L23;
        L28:
            this.lastSelectedType = StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0);
            StoryPrivacyBottomSheet.m20286$$Nest$fputselectedType(this.this$0, 3);
            goto L29
        L23:
            if (this.selectedUsersByGroup.isEmpty() == false) goto L28;
            int r04 = this.lastSelectedType;
            if (r04 == (-1)) goto L29;
            StoryPrivacyBottomSheet.m20286$$Nest$fputselectedType(this.this$0, r04);
            goto L29
        L6:
            if (r0 != 2) goto L8;
            StoryPrivacyBottomSheet.m20254$$Nest$fgetexcludedContacts(this.this$0).clear();
            StoryPrivacyBottomSheet.m20254$$Nest$fgetexcludedContacts(this.this$0).addAll(this.selectedUsers);
            goto L13
        L8:
            if (r0 != 3) goto L10;
            StoryPrivacyBottomSheet.m20268$$Nest$fgetselectedContacts(this.this$0).clear();
            StoryPrivacyBottomSheet.m20269$$Nest$fgetselectedContactsByGroup(this.this$0).clear();
            StoryPrivacyBottomSheet.m20268$$Nest$fgetselectedContacts(this.this$0).addAll(this.selectedUsers);
            StoryPrivacyBottomSheet.m20269$$Nest$fgetselectedContactsByGroup(this.this$0).putAll(this.selectedUsersByGroup);
            goto L13
        L10:
            if (r0 != 0) goto L13;
            StoryPrivacyBottomSheet.m20263$$Nest$fgetmessageUsers(this.this$0).clear();
            StoryPrivacyBottomSheet.m20263$$Nest$fgetmessageUsers(this.this$0).addAll(this.selectedUsers);
            goto L13
        }

        public void updateItems(boolean r2) {
            updateItems(r2, true);
        }

        public void updateItems(boolean r28, boolean r29) {
            this.oldItems.clear();
            this.oldItems.addAll(this.items);
            this.items.clear();
            int r1 = this.pageType;
            int r10 = 0;
            if (r1 != 0) goto L254;
            this.containsHeader = false;
            this.sectionCell.setVisibility(8);
            ArrayList<TLRPC.InputPeer> r12 = MessagesController.getInstance(StoryPrivacyBottomSheet.access$3700(this.this$0)).getStoriesController().sendAs;
            ItemInner r13 = ItemInner.asPad(24);
            this.items.add(r13);
            float r17 = 150.0f;
            if (StoryPrivacyBottomSheet.m20262$$Nest$fgetliveSettings(this.this$0) == false) goto L8;
            this.items.add(ItemInner.asHeader3(LocaleController.getString(R.string.LiveStorySettingsHeader)));
            r13.subtractHeight += AndroidUtilities.dp(72.0f);
            int r22 = 8;
            float r21 = 56.0f;
            float r23 = 40.0f;
        L33:
            boolean r8 = true;
            boolean r14 = false;
        L65:
            if (r8 == true) goto L67;
        L129:
            float r24 = 80.0f;
        L131:
            if (StoryPrivacyBottomSheet.m20258$$Nest$fgetisEdit(this.this$0) == false) goto L133;
        L157:
            String r142 = "StoryKeepGroupInfo";
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == false) goto L160;
        L186:
            this.items.add(ItemInner.asShadow(null));
            r13.subtractHeight += AndroidUtilities.dp(12.0f);
        L188:
            if (StoryPrivacyBottomSheet.m20261$$Nest$fgetkeepOnMyPage(this.this$0) == true) goto L190;
        L210:
            boolean r6 = r8;
        L212:
            if (StoryPrivacyBottomSheet.m20258$$Nest$fgetisEdit(this.this$0) == true) goto L240;
            if (StoryPrivacyBottomSheet.m20246$$Nest$fgetallowCover(this.this$0) == false) goto L218;
            if (StoryPrivacyBottomSheet.m20277$$Nest$fgetwhenCoverClicked(this.this$0) != null) goto L240;
        L218:
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == true) goto L240;
            if (StoryPrivacyBottomSheet.m20262$$Nest$fgetliveSettings(this.this$0) == true) goto L240;
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == false) goto L229;
            if (r6 == false) goto L225;
            int r15 = R.string.StoryLiveKeepInfo;
        L228:
            String r16 = LocaleController.getString(r15);
        L238:
            this.items.add(ItemInner.asShadow(r16));
            r13.subtractHeight += AndroidUtilities.dp(r24);
            goto L240
        L225:
            if (r14 == false) goto L227;
            r15 = R.string.StoryLiveKeepChannelInfo;
            goto L228
        L227:
            r15 = R.string.StoryLiveKeepGroupInfo;
            goto L228
        L229:
            if (r6 == false) goto L231;
            r142 = "StoryKeepInfo";
        L234:
            if (StoryPrivacyBottomSheet.m20275$$Nest$fgetstoryPeriod(this.this$0) != Integer.MAX_VALUE) goto L236;
            int r122 = 86400;
        L237:
            r16 = LocaleController.formatPluralString(r142, r122 / 3600, new Object[0]);
            goto L238
        L236:
            r122 = StoryPrivacyBottomSheet.m20275$$Nest$fgetstoryPeriod(this.this$0);
            goto L237
        L231:
            if (r14 == false) goto L234;
            r142 = "StoryKeepChannelInfo";
        L240:
            if (StoryPrivacyBottomSheet.m20261$$Nest$fgetkeepOnMyPage(this.this$0) == false) goto L247;
            if (StoryPrivacyBottomSheet.m20246$$Nest$fgetallowCover(this.this$0) == false) goto L247;
            if (StoryPrivacyBottomSheet.m20277$$Nest$fgetwhenCoverClicked(this.this$0) == null) goto L247;
            this.items.add(ItemInner.asButton(0, LocaleController.getString(R.string.StoryEditCover), StoryPrivacyBottomSheet.m20253$$Nest$fgetcoverDrawable(this.this$0)));
            r13.subtractHeight += AndroidUtilities.dp(50.0f);
            this.items.add(ItemInner.asShadow(LocaleController.getString(R.string.StoryEditCoverInfo)));
            r13.subtractHeight += AndroidUtilities.dp(72.0f);
        L247:
            if (StoryPrivacyBottomSheet.m20262$$Nest$fgetliveSettings(this.this$0) == false) goto L250;
            this.items.add(ItemInner.asButton(5, LocaleController.getString(R.string.LiveStoryRTMPSettings), _UrlKt.FRAGMENT_ENCODE_SET));
            r13.subtractHeight += AndroidUtilities.dp(50.0f);
            this.items.add(ItemInner.asShadow(null));
            r13.subtractHeight += AndroidUtilities.dp(52.0f);
        L250:
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == true) goto L252;
        L272:
            float r18 = 0.0f;
        L273:
            boolean r3 = TextUtils.isEmpty(this.query);
            if (this.pageType == 0) goto L393;
            String r4 = AndroidUtilities.translitSafe(this.query).toLowerCase();
            int r5 = this.pageType;
            StoryPrivacyBottomSheet r62 = this.this$0;
            if (r5 != 5) goto L278;
            ArrayList r52 = StoryPrivacyBottomSheet.m20289$$Nest$mgetChats(r62);
        L293:
            HashSet r63 = StoryPrivacyBottomSheet.m20294$$Nest$mmergeUsers(this.this$0, this.selectedUsers, this.selectedUsersByGroup);
            if (r3 == false) goto L340;
            if (r28 == true) goto L309;
            this.atTop.clear();
            int r7 = 0;
        L298:
            if (r7 >= r52.size()) goto L309;
            TLObject r82 = (TLObject) r52.get(r7);
            if ((r82 instanceof TLRPC.User) == false) goto L303;
            boolean r11 = this.selectedUsers.contains(Long.valueOf(((TLRPC.User) r82).id));
        L306:
            if (r11 == false) goto L308;
            this.atTop.add(r82);
        L308:
            r7 = r7 + 1;
            goto L298
        L303:
            if ((r82 instanceof TLRPC.Chat) == false) goto L305;
            r11 = this.selectedUsersByGroup.containsKey(Long.valueOf(((TLRPC.Chat) r82).id));
            goto L306
        L305:
            r11 = false;
        L309:
            int r72 = 0;
            int r83 = 0;
        L311:
            if (r72 >= this.atTop.size()) goto L341;
            TLObject r112 = this.atTop.get(r72);
            if ((r112 instanceof TLRPC.User) == false) goto L330;
            TLRPC.User r113 = (TLRPC.User) r112;
            boolean r123 = this.selectedUsers.contains(Long.valueOf(r113.id));
            if (r123 == false) goto L317;
        L319:
            boolean r132 = false;
        L320:
            ArrayList<ItemInner> r143 = this.items;
            ItemInner r114 = ItemInner.asUser(r113, r123, r132);
            int r124 = this.pageType;
            if (r124 != 2) goto L323;
        L326:
            boolean r125 = true;
        L327:
            r143.add(r114.red(r125));
            int r115 = AndroidUtilities.dp(r21);
        L328:
            r18 = r18 + r115;
            r83 = r83 + 1;
        L339:
            r72 = r72 + 1;
            goto L311
        L323:
            if (r124 == 4) goto L326;
            r125 = false;
            goto L327
        L317:
            if (r63.contains(Long.valueOf(r113.id)) == false) goto L319;
            r132 = true;
            goto L320
        L330:
            if ((r112 instanceof TLRPC.Chat) == false) goto L339;
            TLRPC.Chat r116 = (TLRPC.Chat) r112;
            ArrayList<ItemInner> r126 = this.items;
            ItemInner r117 = ItemInner.asChat(r116, this.selectedUsersByGroup.containsKey(Long.valueOf(r116.id)));
            int r133 = this.pageType;
            if (r133 != 2) goto L334;
        L337:
            boolean r134 = true;
        L338:
            r126.add(r117.red(r134));
            r115 = AndroidUtilities.dp(r21);
            goto L328
        L334:
            if (r133 == 4) goto L337;
            r134 = false;
        L341:
            int r73 = 0;
        L343:
            if (r73 >= r52.size()) goto L378;
            TLObject r118 = (TLObject) r52.get(r73);
            if (r3 == false) goto L349;
            if (this.atTop.contains(r118) == false) goto L349;
        L377:
            r73 = r73 + 1;
        L349:
            if (match(r118, r4) == false) goto L377;
            if ((r118 instanceof TLRPC.User) == false) goto L369;
            TLRPC.User r119 = (TLRPC.User) r118;
            boolean r127 = this.selectedUsers.contains(Long.valueOf(r119.id));
            if (r127 == false) goto L356;
        L358:
            boolean r135 = false;
        L359:
            ArrayList<ItemInner> r144 = this.items;
            ItemInner r1110 = ItemInner.asUser(r119, r127, r135);
            int r128 = this.pageType;
            if (r128 != 2) goto L362;
        L365:
            boolean r129 = true;
        L366:
            r144.add(r1110.red(r129));
            r18 = r18 + AndroidUtilities.dp(r21);
            r83 = r83 + 1;
            goto L377
        L362:
            if (r128 == 4) goto L365;
            r129 = false;
            goto L366
        L356:
            if (r63.contains(Long.valueOf(r119.id)) == false) goto L358;
            r135 = true;
            goto L359
        L369:
            if ((r118 instanceof TLRPC.Chat) == false) goto L377;
            TLRPC.Chat r1111 = (TLRPC.Chat) r118;
            ArrayList<ItemInner> r1210 = this.items;
            ItemInner r1112 = ItemInner.asChat(r1111, this.selectedUsersByGroup.containsKey(Long.valueOf(r1111.id)));
            int r136 = this.pageType;
            if (r136 == 2) goto L375;
            if (r136 == 4) goto L375;
            boolean r137 = false;
        L376:
            r1210.add(r1112.red(r137));
            r18 = r18 + AndroidUtilities.dp(r21);
            r83 = r83 + 1;
        L375:
            r137 = true;
            goto L376
        L378:
            if (r3 == true) goto L393;
            if (r83 != 0) goto L381;
            this.items.add(ItemInner.asNoUsers());
            r18 = r18 + AndroidUtilities.dp(r17);
        L381:
            RecyclerListView r2 = this.listView;
            if (r2 == null) goto L388;
            int r25 = (r2.getMeasuredHeight() - this.listView.getPaddingTop()) - this.listView.getPaddingBottom();
            if (StoryPrivacyBottomSheet.access$4900(this.this$0) == false) goto L386;
            r10 = this.keyboardHeight;
        L386:
            int r26 = r25 + r10;
        L389:
            float r27 = r26 - r18;
            if (r27 <= 0.0f) goto L393;
            this.items.add(ItemInner.asPadding((int) r27));
            goto L393
        L388:
            r26 = ((AndroidUtilities.displaySize.y - AndroidUtilities.dp(r21)) - AndroidUtilities.navigationBarHeight) - AndroidUtilities.dp(42.0f);
            goto L389
        L340:
            r83 = 0;
            goto L341
        L278:
            if (r5 != 1) goto L280;
        L283:
            boolean r53 = true;
        L285:
            if (StoryPrivacyBottomSheet.m20248$$Nest$fgetallowSmallChats(r62) == false) goto L291;
            int r74 = this.pageType;
            if (r74 != 3) goto L289;
        L290:
            boolean r75 = true;
        L292:
            r52 = StoryPrivacyBottomSheet.m20293$$Nest$mgetUsers(r62, r53, r75);
            goto L293
        L289:
            if (r74 == 6) goto L290;
        L291:
            r75 = false;
            goto L292
        L280:
            if (r5 == 2) goto L283;
            r53 = false;
        L393:
            if (this.layoutManager.getReverseLayout() == false) goto L395;
            Collections.reverse(this.items);
        L395:
            if (r29 == true) goto L397;
        L403:
            this.contentView.invalidate();
            return;
        L397:
            if (this.adapter == null) goto L403;
            if (r28 == true) goto L400;
        L402:
            this.adapter.notifyDataSetChanged();
            goto L403
        L400:
            if (StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0) == 0) goto L402;
            this.adapter.setItems(this.oldItems, this.items);
            goto L403
        L252:
            if (StoryPrivacyBottomSheet.m20245$$Nest$fgetallowComments(this.this$0) == false) goto L272;
            this.items.add(ItemInner.asHeaderCell(LocaleController.getString(R.string.LiveStoryPricePerComment)));
            r13.subtractHeight += AndroidUtilities.dp(r23);
            this.items.add(ItemInner.asSlider(r22));
            r13.subtractHeight += AndroidUtilities.dp(75.0f);
            this.items.add(ItemInner.asShadow(LocaleController.getString(R.string.LiveStoryPricePerCommentInfo)));
            r13.subtractHeight += AndroidUtilities.dp(50.0f);
            goto L272
        L190:
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == true) goto L210;
            if (StoryPrivacyBottomSheet.m20262$$Nest$fgetliveSettings(this.this$0) == true) goto L210;
            r6 = r8;
            long r76 = getDialogId();
            this.this$0.getStoriesController().getStoryAlbumsList(r76);
            StringBuilder r1211 = new StringBuilder();
            if (StoryPrivacyBottomSheet.m20267$$Nest$fgetselectedAlbums(this.this$0).isEmpty() == false) goto L196;
            r1211.append(LocaleController.getString(R.string.StoriesAlbumNameAllStories));
        L209:
            this.items.add(ItemInner.asButton(1, LocaleController.getString(R.string.StoriesAlbum), r1211));
            r13.subtractHeight += AndroidUtilities.dp(50.0f);
            goto L212
        L196:
            int r210 = StoryPrivacyBottomSheet.m20267$$Nest$fgetselectedAlbums(this.this$0).size();
            StoryPrivacyBottomSheet r32 = this.this$0;
            if (r210 >= 3) goto L208;
            Iterator r211 = StoryPrivacyBottomSheet.m20267$$Nest$fgetselectedAlbums(r32).iterator();
        L200:
            if (r211.hasNext() == false) goto L209;
            StoriesController.StoryAlbum r33 = this.this$0.getStoriesController().getStoryAlbumsList(r76).findById(((Integer) r211.next()).intValue());
            if (r33 == null) goto L200;
            if (r1211.length() == 0) goto L207;
            r1211.append(", ");
        L207:
            r1211.append(r33.title);
            goto L200
        L208:
            r1211.append(LocaleController.formatPluralString("StoriesAlbumCount", StoryPrivacyBottomSheet.m20267$$Nest$fgetselectedAlbums(r32).size(), new Object[0]));
            goto L209
        L160:
            if (StoryPrivacyBottomSheet.m20262$$Nest$fgetliveSettings(this.this$0) == true) goto L186;
            if (StoryPrivacyBottomSheet.m20258$$Nest$fgetisEdit(this.this$0) == true) goto L188;
            if (StoryPrivacyBottomSheet.m20246$$Nest$fgetallowCover(this.this$0) == false) goto L188;
            if (StoryPrivacyBottomSheet.m20277$$Nest$fgetwhenCoverClicked(this.this$0) == null) goto L188;
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == false) goto L175;
            if (r8 == false) goto L171;
            int r64 = R.string.StoryLiveKeepInfo;
        L174:
            String r65 = LocaleController.getString(r64);
        L185:
            this.items.add(ItemInner.asShadow(r65));
            r13.subtractHeight += AndroidUtilities.dp(r24);
            goto L188
        L171:
            if (r14 == false) goto L173;
            r64 = R.string.StoryLiveKeepChannelInfo;
            goto L174
        L173:
            r64 = R.string.StoryLiveKeepGroupInfo;
            goto L174
        L175:
            if (r8 == false) goto L177;
            String r66 = "StoryKeepInfo";
        L181:
            if (StoryPrivacyBottomSheet.m20275$$Nest$fgetstoryPeriod(this.this$0) != Integer.MAX_VALUE) goto L183;
            int r1212 = 86400;
        L184:
            r65 = LocaleController.formatPluralString(r66, r1212 / 3600, new Object[0]);
            goto L185
        L183:
            r1212 = StoryPrivacyBottomSheet.m20275$$Nest$fgetstoryPeriod(this.this$0);
            goto L184
        L177:
            if (r14 == false) goto L179;
            r66 = "StoryKeepChannelInfo";
            goto L181
        L179:
            r66 = "StoryKeepGroupInfo";
            goto L181
        L133:
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == false) goto L141;
            if (StoryPrivacyBottomSheet.m20262$$Nest$fgetliveSettings(this.this$0) == true) goto L141;
            boolean r67 = StoryPrivacyBottomSheet.m20260$$Nest$fgetisRtmpStream(this.this$0);
            ArrayList<ItemInner> r1213 = this.items;
            if (r67 == false) goto L139;
            r1213.add(ItemInner.asButton(5, LocaleController.getString(R.string.LiveStoryRTMPSettings), _UrlKt.FRAGMENT_ENCODE_SET));
            r13.subtractHeight += AndroidUtilities.dp(50.0f);
            this.items.add(ItemInner.asButton(6, LocaleController.getString(R.string.LiveStoryRTMPDisable), _UrlKt.FRAGMENT_ENCODE_SET));
            r13.subtractHeight += AndroidUtilities.dp(50.0f);
            this.items.add(ItemInner.asShadow(null));
            r13.subtractHeight += AndroidUtilities.dp(12.0f);
            goto L141
        L139:
            r1213.add(ItemInner.asButton(5, LocaleController.getString(R.string.LiveStoryRTMPEnable), _UrlKt.FRAGMENT_ENCODE_SET));
            r13.subtractHeight += AndroidUtilities.dp(50.0f);
            this.items.add(ItemInner.asShadow(LocaleController.getString(R.string.LiveStoryRTMPEnableInfo)));
            r13.subtractHeight += AndroidUtilities.dp(30.0f);
        L141:
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == false) goto L144;
            this.items.add(ItemInner.asCheck(LocaleController.getString(R.string.StoryLiveAllowComments), 2, StoryPrivacyBottomSheet.m20245$$Nest$fgetallowComments(this.this$0)));
            r13.subtractHeight += AndroidUtilities.dp(50.0f);
        L144:
            if (StoryPrivacyBottomSheet.m20262$$Nest$fgetliveSettings(this.this$0) == true) goto L147;
            this.items.add(ItemInner.asCheck(LocaleController.getString(R.string.StoryAllowScreenshots), 0, StoryPrivacyBottomSheet.m20247$$Nest$fgetallowScreenshots(this.this$0)));
            r13.subtractHeight += AndroidUtilities.dp(50.0f);
        L147:
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == true) goto L157;
            if (StoryPrivacyBottomSheet.m20262$$Nest$fgetliveSettings(this.this$0) == true) goto L157;
            ArrayList<ItemInner> r68 = this.items;
            if (r8 == false) goto L153;
            int r1214 = R.string.StoryKeep;
        L156:
            r68.add(ItemInner.asCheck(LocaleController.getString(r1214), 1, StoryPrivacyBottomSheet.m20261$$Nest$fgetkeepOnMyPage(this.this$0)));
            r13.subtractHeight += AndroidUtilities.dp(50.0f);
            goto L157
        L153:
            if (r14 == false) goto L155;
            r1214 = R.string.StoryKeepChannel;
            goto L156
        L155:
            r1214 = R.string.StoryKeepGroup;
            goto L156
        L67:
            if (StoryPrivacyBottomSheet.m20262$$Nest$fgetliveSettings(this.this$0) == true) goto L129;
            ArrayList<ItemInner> r145 = this.items;
            if (StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0) != 4) goto L72;
            boolean r152 = true;
        L71:
            r24 = 80.0f;
            ItemInner r1215 = ItemInner.asType(4, r152, StoryPrivacyBottomSheet.m20257$$Nest$fgetexcludedEveryoneCount(this.this$0));
            r145.add(r1215);
            r13.subtractHeight += AndroidUtilities.dp(r21);
            if (StoryPrivacyBottomSheet.m20257$$Nest$fgetexcludedEveryoneCount(this.this$0) != 1) goto L85;
            int r146 = StoryPrivacyBottomSheet.m20255$$Nest$fgetexcludedEveryone(this.this$0).size();
            StoryPrivacyBottomSheet r153 = this.this$0;
            if (r146 != 1) goto L78;
            r1215.user = MessagesController.getInstance(StoryPrivacyBottomSheet.access$4200(r153)).getUser((Long) StoryPrivacyBottomSheet.m20255$$Nest$fgetexcludedEveryone(this.this$0).get(0));
            goto L85
        L78:
            Iterator r147 = StoryPrivacyBottomSheet.m20256$$Nest$fgetexcludedEveryoneByGroup(r153).values().iterator();
        L80:
            if (r147.hasNext() == false) goto L85;
            ArrayList r154 = (ArrayList) r147.next();
            if (r154.size() < 1) goto L80;
            r1215.user = MessagesController.getInstance(StoryPrivacyBottomSheet.access$4300(this.this$0)).getUser((Long) r154.get(0));
        L85:
            ArrayList<ItemInner> r69 = this.items;
            if (StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0) != 2) goto L88;
            boolean r1216 = true;
        L89:
            ItemInner r1217 = ItemInner.asType(2, r1216, StoryPrivacyBottomSheet.m20254$$Nest$fgetexcludedContacts(this.this$0).size());
            r69.add(r1217);
            r13.subtractHeight += AndroidUtilities.dp(r21);
            if (StoryPrivacyBottomSheet.m20254$$Nest$fgetexcludedContacts(this.this$0).size() != 1) goto L92;
            r1217.user = MessagesController.getInstance(StoryPrivacyBottomSheet.access$4400(this.this$0)).getUser((Long) StoryPrivacyBottomSheet.m20254$$Nest$fgetexcludedContacts(this.this$0).get(0));
        L92:
            ArrayList r610 = StoryPrivacyBottomSheet.m20290$$Nest$mgetCloseFriends(this.this$0);
            ArrayList<ItemInner> r1218 = this.items;
            if (StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0) != 1) goto L95;
            boolean r148 = true;
        L96:
            ItemInner r149 = ItemInner.asType(1, r148, r610.size());
            r1218.add(r149);
            r13.subtractHeight += AndroidUtilities.dp(r21);
            if (r610.size() == 1) goto L99;
        L101:
            ArrayList<ItemInner> r611 = this.items;
            if (StoryPrivacyBottomSheet.m20271$$Nest$fgetselectedType(this.this$0) != 3) goto L104;
            boolean r1219 = true;
        L105:
            ItemInner r1220 = ItemInner.asType(3, r1219, StoryPrivacyBottomSheet.m20270$$Nest$fgetselectedContactsCount(this.this$0));
            r611.add(r1220);
            r13.subtractHeight += AndroidUtilities.dp(r21);
            if (StoryPrivacyBottomSheet.m20270$$Nest$fgetselectedContactsCount(this.this$0) != 1) goto L116;
            int r612 = StoryPrivacyBottomSheet.m20268$$Nest$fgetselectedContacts(this.this$0).size();
            StoryPrivacyBottomSheet r1410 = this.this$0;
            if (r612 != 1) goto L110;
            r1220.user = MessagesController.getInstance(StoryPrivacyBottomSheet.access$4500(r1410)).getUser((Long) StoryPrivacyBottomSheet.m20268$$Nest$fgetselectedContacts(this.this$0).get(0));
            goto L116
        L110:
            Iterator r613 = StoryPrivacyBottomSheet.m20269$$Nest$fgetselectedContactsByGroup(r1410).values().iterator();
        L112:
            if (r613.hasNext() == false) goto L116;
            ArrayList r1411 = (ArrayList) r613.next();
            if (r1411.size() < 1) goto L112;
            r1220.user = MessagesController.getInstance(StoryPrivacyBottomSheet.access$4600(this.this$0)).getUser((Long) r1411.get(0));
        L116:
            int r614 = MessagesController.getInstance(StoryPrivacyBottomSheet.access$4700(this.this$0)).getStoriesController().getBlocklistCount();
            ArrayList<ItemInner> r1221 = this.items;
            StoryPrivacyBottomSheet r1412 = this.this$0;
            if (r614 > 0) goto L124;
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(r1412) == false) goto L121;
            int r615 = R.string.StoryLiveBlockListEmpty;
        L122:
            String r616 = LocaleController.getString(r615);
        L128:
            r1221.add(ItemInner.asShadow(AndroidUtilities.replaceSingleTag(r616, Theme.key_chat_messageLinkIn, 0, new StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda1(this), StoryPrivacyBottomSheet.access$4800(this.this$0))));
            r13.subtractHeight += AndroidUtilities.dp(80.0f);
            goto L131
        L121:
            r615 = R.string.StoryBlockListEmpty;
            goto L122
        L124:
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(r1412) == false) goto L126;
            String r1413 = "StoryLiveBlockList";
        L127:
            r616 = LocaleController.formatPluralString(r1413, r614, new Object[0]);
            goto L128
        L126:
            r1413 = "StoryBlockList";
            goto L127
        L104:
            r1219 = false;
            goto L105
        L99:
            if ((r610.get(0) instanceof TLRPC.User) == false) goto L101;
            r149.user = (TLRPC.User) r610.get(0);
            goto L101
        L95:
            r148 = false;
            goto L96
        L88:
            r1216 = false;
            goto L89
        L72:
            r152 = false;
            goto L71
        L8:
            if (StoryPrivacyBottomSheet.m20251$$Nest$fgetcanChangePeer(this.this$0) == true) goto L10;
        L15:
            r22 = 8;
            r21 = 56.0f;
            r23 = 40.0f;
            ArrayList<ItemInner> r19 = this.items;
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == false) goto L37;
            int r84 = R.string.StoryLivePrivacyPublishAs;
        L38:
            r19.add(ItemInner.asHeaderCell(LocaleController.getString(r84)));
            r13.subtractHeight += AndroidUtilities.dp(50.0f);
            StoryPrivacyBottomSheet r110 = this.this$0;
            TLRPC.InputPeer r85 = r110.selectedPeer;
            if (r85 != null) goto L41;
        L52:
            this.items.add(ItemInner.asUser(UserConfig.getInstance(StoryPrivacyBottomSheet.access$3800(r110)).getCurrentUser(), false, false).asSendAs());
            r13.subtractHeight += AndroidUtilities.dp(62.0f);
        L53:
            r8 = true;
            r14 = false;
        L54:
            ItemInner r1222 = ItemInner.asShadow(null);
            if (r8 == false) goto L57;
            int r1414 = 1;
        L58:
            r1222.resId = r1414;
            this.items.add(r1222);
            r13.subtractHeight += AndroidUtilities.dp(12.0f);
            if (r8 == false) goto L65;
            ArrayList<ItemInner> r1223 = this.items;
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == false) goto L63;
            int r1415 = R.string.StoryLivePrivacyWhoCanView;
        L64:
            r1223.add(ItemInner.asHeaderCell(LocaleController.getString(r1415)));
            r13.subtractHeight += AndroidUtilities.dp(40.0f);
            goto L65
        L63:
            r1415 = R.string.StoryPrivacyWhoCanView;
            goto L64
        L57:
            r1414 = 2;
            goto L58
        L41:
            if ((r85 instanceof TLRPC.TL_inputPeerSelf) == true) goto L52;
            if ((r85 instanceof TLRPC.TL_inputPeerUser) == false) goto L47;
            this.items.add(ItemInner.asUser(MessagesController.getInstance(StoryPrivacyBottomSheet.access$3900(r110)).getUser(Long.valueOf(this.this$0.selectedPeer.user_id)), false, false).asSendAs());
            r13.subtractHeight += AndroidUtilities.dp(62.0f);
            goto L53
        L47:
            if ((r85 instanceof TLRPC.TL_inputPeerChannel) == false) goto L50;
            TLRPC.Chat r111 = MessagesController.getInstance(StoryPrivacyBottomSheet.access$4000(r110)).getChat(Long.valueOf(this.this$0.selectedPeer.channel_id));
            this.items.add(ItemInner.asChat(r111, false).asSendAs());
            r14 = ChatObject.isChannelAndNotMegaGroup(r111);
            r13.subtractHeight += AndroidUtilities.dp(104.0f);
            r8 = false;
            goto L54
        L50:
            if ((r85 instanceof TLRPC.TL_inputPeerChat) == false) goto L53;
            this.items.add(ItemInner.asChat(MessagesController.getInstance(StoryPrivacyBottomSheet.access$4100(r110)).getChat(Long.valueOf(this.this$0.selectedPeer.chat_id)), false).asSendAs());
            r13.subtractHeight += AndroidUtilities.dp(104.0f);
            r14 = false;
            r8 = false;
            goto L54
        L37:
            r84 = R.string.StoryPrivacyPublishAs;
            goto L38
        L10:
            if (StoryPrivacyBottomSheet.m20258$$Nest$fgetisEdit(this.this$0) == true) goto L16;
            if (r12 == null) goto L16;
            if (r12.size() > 1) goto L15;
        L16:
            ArrayList<ItemInner> r120 = this.items;
            if (StoryPrivacyBottomSheet.m20258$$Nest$fgetisEdit(this.this$0) == false) goto L21;
            String r1416 = LocaleController.getString(R.string.StoryPrivacyAlertEditTitle);
        L19:
            r21 = 56.0f;
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == false) goto L29;
            String r86 = LocaleController.getString(R.string.StoryLivePrivacyAlertSubtitleProfile);
            r22 = 8;
            r23 = 40.0f;
        L32:
            r120.add(ItemInner.asHeader2(r1416, r86));
            r13.subtractHeight += AndroidUtilities.dp(72.0f);
            goto L33
        L29:
            if (StoryPrivacyBottomSheet.m20275$$Nest$fgetstoryPeriod(this.this$0) == Integer.MAX_VALUE) goto L31;
            r22 = 8;
            r23 = 40.0f;
            r86 = LocaleController.formatPluralString("StoryPrivacyAlertSubtitle", StoryPrivacyBottomSheet.m20275$$Nest$fgetstoryPeriod(this.this$0) / 3600, new Object[0]);
            goto L32
        L31:
            r22 = 8;
            r23 = 40.0f;
            r86 = LocaleController.getString(R.string.StoryPrivacyAlertSubtitleProfile);
            goto L32
        L21:
            if (StoryPrivacyBottomSheet.m20259$$Nest$fgetisLive(this.this$0) == false) goto L23;
            int r1417 = R.string.StoryLivePrivacyAlertTitle;
        L24:
            r1416 = LocaleController.getString(r1417);
            goto L19
        L23:
            r1417 = R.string.StoryPrivacyAlertTitle;
            goto L24
        L254:
            r17 = 150.0f;
            r21 = 56.0f;
            if (r1 != 1) goto L258;
            this.headerView.setText(LocaleController.getString(R.string.StoryPrivacyAlertCloseFriendsTitle));
            this.headerView.setCloseImageVisible(true);
            this.headerView.backDrawable.setRotation(0.0f, false);
            this.items.add(ItemInner.asPad());
            this.items.add(ItemInner.asHeader());
            this.searchPosition = this.items.size();
            this.items.add(ItemInner.asSearchField());
            this.items.add(ItemInner.asSection());
            r18 = ((AndroidUtilities.dp(56.0f) + 0.0f) + AndroidUtilities.dp(150.0f)) + AndroidUtilities.dp(32.0f);
            this.sectionCell.setText(LocaleController.getString(R.string.StoryPrivacyAlertCloseFriendsSubtitle));
            updateSectionCell(r28);
            this.containsHeader = true;
            goto L273
        L258:
            if (r1 != 2) goto L261;
            this.headerView.setText(LocaleController.getString(R.string.StoryPrivacyAlertExcludedContactsTitle));
            this.headerView.setCloseImageVisible(true);
            this.headerView.backDrawable.setRotation(0.0f, false);
            this.items.add(ItemInner.asPad());
            this.items.add(ItemInner.asHeader());
            this.searchPosition = this.items.size();
            this.items.add(ItemInner.asSearchField());
            this.items.add(ItemInner.asSection());
            r18 = ((AndroidUtilities.dp(56.0f) + 0.0f) + AndroidUtilities.dp(150.0f)) + AndroidUtilities.dp(32.0f);
            this.sectionCell.setText(LocaleController.getString(R.string.StoryPrivacyAlertExcludedContactsSubtitle));
            updateSectionCell(r28);
            this.containsHeader = true;
            goto L273
        L261:
            if (r1 != 3) goto L264;
            this.headerView.setText(LocaleController.getString(R.string.StoryPrivacyAlertSelectContactsTitle));
            this.headerView.setCloseImageVisible(true);
            this.headerView.backDrawable.setRotation(0.0f, false);
            this.items.add(ItemInner.asPad());
            this.items.add(ItemInner.asHeader());
            this.searchPosition = this.items.size();
            this.items.add(ItemInner.asSearchField());
            this.items.add(ItemInner.asSection());
            r18 = ((AndroidUtilities.dp(56.0f) + 0.0f) + AndroidUtilities.dp(150.0f)) + AndroidUtilities.dp(32.0f);
            this.sectionCell.setText(LocaleController.getString(R.string.StoryPrivacyAlertSelectContactsSubtitle));
            updateSectionCell(r28);
            this.containsHeader = true;
            goto L273
        L264:
            if (r1 != 5) goto L267;
            this.headerView.setText(LocaleController.getString(R.string.StoryPrivacyAlertAsMessageTitle));
            this.headerView.setCloseImageVisible(StoryPrivacyBottomSheet.m20273$$Nest$fgetstartedFromSendAsMessage(this.this$0));
            this.headerView.backDrawable.setRotation(0.0f, false);
            this.items.add(ItemInner.asPad());
            this.items.add(ItemInner.asHeader());
            this.searchPosition = this.items.size();
            this.items.add(ItemInner.asSearchField());
            this.items.add(ItemInner.asSection());
            r18 = ((AndroidUtilities.dp(56.0f) + 0.0f) + AndroidUtilities.dp(150.0f)) + AndroidUtilities.dp(32.0f);
            this.sectionCell.setText(LocaleController.getString(R.string.StoryPrivacyAlertAsMessageSubtitle));
            updateSectionCell(r28);
            this.containsHeader = true;
            goto L273
        L267:
            if (r1 != 6) goto L270;
            this.headerView.setText(LocaleController.getString(R.string.StoryPrivacyAlertBlocklistTitle));
            this.headerView.setCloseImageVisible(true);
            this.headerView.backDrawable.setRotation(0.0f, false);
            this.items.add(ItemInner.asPad());
            this.items.add(ItemInner.asHeader());
            this.searchPosition = this.items.size();
            this.items.add(ItemInner.asSearchField());
            this.items.add(ItemInner.asSection());
            r18 = ((AndroidUtilities.dp(56.0f) + 0.0f) + AndroidUtilities.dp(150.0f)) + AndroidUtilities.dp(32.0f);
            this.sectionCell.setText(LocaleController.getString(R.string.StoryPrivacyAlertBlocklistSubtitle));
            updateSectionCell(r28);
            this.containsHeader = true;
            goto L273
        L270:
            if (r1 != 4) goto L272;
            this.headerView.setText(LocaleController.getString(R.string.StoryPrivacyAlertExcludeFromEveryoneTitle));
            this.headerView.setCloseImageVisible(true);
            this.headerView.backDrawable.setRotation(0.0f, false);
            this.items.add(ItemInner.asPad());
            this.items.add(ItemInner.asHeader());
            this.searchPosition = this.items.size();
            this.items.add(ItemInner.asSearchField());
            this.items.add(ItemInner.asSection());
            r18 = ((AndroidUtilities.dp(56.0f) + 0.0f) + AndroidUtilities.dp(150.0f)) + AndroidUtilities.dp(32.0f);
            this.sectionCell.setText(LocaleController.getString(R.string.StoryPrivacyAlertExcludeFromEveryoneSubtitle));
            updateSectionCell(r28);
            this.containsHeader = true;
            goto L273
        }

        public void updateTops() {
            updateSearchFieldTop();
            updateHeaderTop();
        }
    }

    public static class SearchUsersCell extends ScrollView {
        public ArrayList<GroupCreateSpan> allSpans;
        private final LinearGradient bottomGradient;
        private final AnimatedFloat bottomGradientAlpha;
        private final Matrix bottomGradientMatrix;
        private final Paint bottomGradientPaint;
        public float containerHeight;
        private GroupCreateSpan currentDeletingSpan;
        private EditTextBoldCursor editText;
        private int fieldY;
        private int hintTextWidth;
        private boolean ignoreScrollEvent;
        private boolean ignoreTextChange;
        private Utilities.Callback<String> onSearchTextChange;
        private int prevResultContainerHeight;
        private final Theme.ResourcesProvider resourcesProvider;
        public int resultContainerHeight;
        private boolean scroll;
        public SpansContainer spansContainer;
        private final LinearGradient topGradient;
        private final AnimatedFloat topGradientAlpha;
        private final Matrix topGradientMatrix;
        private final Paint topGradientPaint;
        private Runnable updateHeight;

        /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$SearchUsersCell$1, reason: invalid class name */
        public class AnonymousClass1 extends EditTextBoldCursor {
            final /* synthetic */ SearchUsersCell this$0;

            public AnonymousClass1(SearchUsersCell r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
            public boolean onTouchEvent(MotionEvent r3) {
                if (SearchUsersCell.m20324$$Nest$fgetcurrentDeletingSpan(this.this$0) == null) goto L6;
                SearchUsersCell.m20324$$Nest$fgetcurrentDeletingSpan(this.this$0).cancelDeleteAnimation();
                SearchUsersCell.m20333$$Nest$fputcurrentDeletingSpan(this.this$0, null);
            L6:
                if (r3.getAction() != 0) goto L11;
                if (AndroidUtilities.showKeyboard(this) == true) goto L11;
                this.this$0.fullScroll(130);
                clearFocus();
                requestFocus();
            L11:
                return super.onTouchEvent(r3);
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$SearchUsersCell$2, reason: invalid class name */
        public class AnonymousClass2 implements TextWatcher {
            final /* synthetic */ SearchUsersCell this$0;

            public AnonymousClass2(SearchUsersCell r1) {
                this.this$0 = r1;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r2) {
                if (SearchUsersCell.m20329$$Nest$fgetignoreTextChange(this.this$0) == false) goto L6;
                return;
            L6:
                if (SearchUsersCell.m20330$$Nest$fgetonSearchTextChange(this.this$0) == null) goto L10;
                if (r2 == null) goto L11;
                SearchUsersCell.m20330$$Nest$fgetonSearchTextChange(this.this$0).run(r2.toString());
                return;
            L11:
                return;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        public class SpansContainer extends ViewGroup {
            private View addingSpan;
            private ArrayList<View> animAddingSpans;
            private ArrayList<View> animRemovingSpans;
            private boolean animationStarted;
            private ArrayList<Animator> animators;
            private AnimatorSet currentAnimation;
            private final int heightDp;
            private final int padDp;
            private final int padXDp;
            private final int padYDp;
            private final ArrayList<View> removingSpans;
            final /* synthetic */ SearchUsersCell this$0;

            /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$SearchUsersCell$SpansContainer$1, reason: invalid class name */
            public class AnonymousClass1 extends AnimatorListenerAdapter {
                final /* synthetic */ SpansContainer this$1;
                final /* synthetic */ GroupCreateSpan val$span;

                public AnonymousClass1(SpansContainer r1, GroupCreateSpan r2) {
                    this.this$1 = r1;
                    this.val$span = r2;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r3) {
                    this.this$1.removeView(this.val$span);
                    SpansContainer.m20339$$Nest$fgetremovingSpans(this.this$1).clear();
                    SpansContainer.m20342$$Nest$fputcurrentAnimation(this.this$1, null);
                    SpansContainer.m20341$$Nest$fputanimationStarted(this.this$1, false);
                    SearchUsersCell.m20325$$Nest$fgeteditText(this.this$1.this$0).setAllowDrawCursor(true);
                    if (SearchUsersCell.m20332$$Nest$fgetupdateHeight(this.this$1.this$0) == null) goto L6;
                    SearchUsersCell.m20332$$Nest$fgetupdateHeight(this.this$1.this$0).run();
                L6:
                    if (SearchUsersCell.m20331$$Nest$fgetscroll(this.this$1.this$0) == false) goto L9;
                    this.this$1.this$0.fullScroll(130);
                    SearchUsersCell.m20337$$Nest$fputscroll(this.this$1.this$0, false);
                    return;
                }
            }

            /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$SearchUsersCell$SpansContainer$2, reason: invalid class name */
            public class AnonymousClass2 extends AnimatorListenerAdapter {
                final /* synthetic */ SpansContainer this$1;
                final /* synthetic */ ArrayList val$toDelete;

                public AnonymousClass2(SpansContainer r1, ArrayList r2) {
                    this.this$1 = r1;
                    this.val$toDelete = r2;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r4) {
                    int r0 = 0;
                L3:
                    int r1 = this.val$toDelete.size();
                    SpansContainer r2 = this.this$1;
                    if (r0 >= r1) goto L6;
                    r2.removeView((View) this.val$toDelete.get(r0));
                    r0 = r0 + 1;
                    goto L3
                L6:
                    SpansContainer.m20340$$Nest$fputaddingSpan(r2, null);
                    SpansContainer.m20339$$Nest$fgetremovingSpans(this.this$1).clear();
                    SpansContainer.m20342$$Nest$fputcurrentAnimation(this.this$1, null);
                    SpansContainer.m20341$$Nest$fputanimationStarted(this.this$1, false);
                    SearchUsersCell.m20325$$Nest$fgeteditText(this.this$1.this$0).setAllowDrawCursor(true);
                    if (SearchUsersCell.m20332$$Nest$fgetupdateHeight(this.this$1.this$0) == null) goto L10;
                    SearchUsersCell.m20332$$Nest$fgetupdateHeight(this.this$1.this$0).run();
                L10:
                    if (SearchUsersCell.m20331$$Nest$fgetscroll(this.this$1.this$0) == false) goto L14;
                    this.this$1.this$0.fullScroll(130);
                    SearchUsersCell.m20337$$Nest$fputscroll(this.this$1.this$0, false);
                    return;
                }
            }

            /* renamed from: org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet$SearchUsersCell$SpansContainer$3, reason: invalid class name */
            public class AnonymousClass3 extends AnimatorListenerAdapter {
                final /* synthetic */ SpansContainer this$1;
                final /* synthetic */ ArrayList val$spans;

                public AnonymousClass3(SpansContainer r1, ArrayList r2) {
                    this.this$1 = r1;
                    this.val$spans = r2;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r4) {
                    int r0 = 0;
                L3:
                    int r1 = this.val$spans.size();
                    SpansContainer r2 = this.this$1;
                    if (r0 >= r1) goto L6;
                    r2.removeView((View) this.val$spans.get(r0));
                    r0 = r0 + 1;
                    goto L3
                L6:
                    SpansContainer.m20339$$Nest$fgetremovingSpans(r2).clear();
                    SpansContainer.m20342$$Nest$fputcurrentAnimation(this.this$1, null);
                    SpansContainer.m20341$$Nest$fputanimationStarted(this.this$1, false);
                    SearchUsersCell.m20325$$Nest$fgeteditText(this.this$1.this$0).setAllowDrawCursor(true);
                    if (SearchUsersCell.m20332$$Nest$fgetupdateHeight(this.this$1.this$0) == null) goto L10;
                    SearchUsersCell.m20332$$Nest$fgetupdateHeight(this.this$1.this$0).run();
                L10:
                    if (SearchUsersCell.m20331$$Nest$fgetscroll(this.this$1.this$0) == false) goto L14;
                    this.this$1.this$0.fullScroll(130);
                    SearchUsersCell.m20337$$Nest$fputscroll(this.this$1.this$0, false);
                    return;
                }
            }

            public static /* synthetic */ void $r8$lambda$WnR55WYGCDjkf_IaiG0oKHvmLrE(SpansContainer r0) {
                r0.lambda$onMeasure$0();
            }

            /* renamed from: -$$Nest$fgetremovingSpans, reason: not valid java name */
            public static /* bridge */ /* synthetic */ ArrayList m20339$$Nest$fgetremovingSpans(SpansContainer r0) {
                return r0.removingSpans;
            }

            /* renamed from: -$$Nest$fputaddingSpan, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m20340$$Nest$fputaddingSpan(SpansContainer r0, View r1) {
                r0.addingSpan = r1;
            }

            /* renamed from: -$$Nest$fputanimationStarted, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m20341$$Nest$fputanimationStarted(SpansContainer r0, boolean r1) {
                r0.animationStarted = r1;
            }

            /* renamed from: -$$Nest$fputcurrentAnimation, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m20342$$Nest$fputcurrentAnimation(SpansContainer r0, AnimatorSet r1) {
                r0.currentAnimation = r1;
            }

            public SpansContainer(SearchUsersCell r1, Context r2) {
                this.this$0 = r1;
                super(r2);
                this.animAddingSpans = new ArrayList();
                this.animRemovingSpans = new ArrayList();
                this.animators = new ArrayList();
                this.removingSpans = new ArrayList();
                this.padDp = 7;
                this.padYDp = 4;
                this.padXDp = 4;
                this.heightDp = 28;
            }

            private /* synthetic */ void lambda$onMeasure$0() {
                this.this$0.fullScroll(130);
            }

            private void setupEndValues() {
                AnimatorSet r0 = this.currentAnimation;
                if (r0 == null) goto L5;
                r0.cancel();
            L5:
                int r02 = 0;
                int r1 = 0;
            L7:
                if (r1 >= this.animAddingSpans.size()) goto L10;
                this.animAddingSpans.get(r1).setScaleX(1.0f);
                this.animAddingSpans.get(r1).setScaleY(1.0f);
                this.animAddingSpans.get(r1).setAlpha(1.0f);
                r1 = r1 + 1;
            L10:
                if (r02 >= this.animRemovingSpans.size()) goto L12;
                this.animRemovingSpans.get(r02).setScaleX(0.0f);
                this.animRemovingSpans.get(r02).setScaleY(0.0f);
                this.animRemovingSpans.get(r02).setAlpha(0.0f);
                r02 = r02 + 1;
                goto L10
            L12:
                this.animAddingSpans.clear();
                this.animRemovingSpans.clear();
            }

            @Override // android.view.ViewGroup, android.view.View
            public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
                int r22 = getChildCount();
                int r42 = 0;
            L3:
                if (r42 >= r22) goto L5;
                View r52 = getChildAt(r42);
                r52.layout(0, 0, r52.getMeasuredWidth(), r52.getMeasuredHeight());
                r42 = r42 + 1;
                goto L3
            }

            @Override // android.view.View
            public void onMeasure(int r18, int r19) {
                int r1 = getChildCount();
                int r2 = View.MeasureSpec.getSize(r18);
                int r3 = r2 - AndroidUtilities.dp(14.0f);
                int r5 = AndroidUtilities.dp(10.0f);
                int r4 = AndroidUtilities.dp(10.0f);
                int r7 = 0;
                int r8 = 0;
                int r9 = 0;
            L3:
                Property r10 = View.TRANSLATION_Y;
                Property r11 = View.TRANSLATION_X;
                if (r7 >= r1) goto L36;
                View r15 = getChildAt(r7);
                if ((r15 instanceof GroupCreateSpan) == true) goto L8;
                int r16 = r1;
            L34:
                r7 = r7 + 1;
                r1 = r16;
                goto L3
            L8:
                r15.measure(View.MeasureSpec.makeMeasureSpec(r2, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(28.0f), TLObject.FLAG_30));
                boolean r6 = this.removingSpans.contains(r15);
                if (r6 == true) goto L14;
                if ((r15.getMeasuredWidth() + r8) <= r3) goto L14;
                r5 = r5 + (r15.getMeasuredHeight() + AndroidUtilities.dp(4.0f));
                r8 = 0;
            L14:
                if ((r15.getMeasuredWidth() + r9) <= r3) goto L16;
                r4 = r4 + (r15.getMeasuredHeight() + AndroidUtilities.dp(4.0f));
                r9 = 0;
            L16:
                int r12 = AndroidUtilities.dp(7.0f) + r8;
                if (this.animationStarted == true) goto L20;
                if (r6 == false) goto L22;
                r15.setTranslationX(AndroidUtilities.dp(7.0f) + r9);
                r15.setTranslationY(r4);
                goto L20
            L22:
                if (this.removingSpans.isEmpty() == true) goto L30;
                float r122 = r12;
                if (r15.getTranslationX() == r122) goto L26;
                r16 = r1;
                this.animators.add(ObjectAnimator.ofFloat(r15, r11, new float[]{r122}));
            L27:
                float r112 = r5;
                if (r15.getTranslationY() == r112) goto L31;
                this.animators.add(ObjectAnimator.ofFloat(r15, r10, new float[]{r112}));
            L31:
                if (r6 == true) goto L33;
                r8 = r8 + (r15.getMeasuredWidth() + AndroidUtilities.dp(4.0f));
            L33:
                r9 = r9 + (r15.getMeasuredWidth() + AndroidUtilities.dp(4.0f));
                goto L34
            L26:
                r16 = r1;
                goto L27
            L30:
                r16 = r1;
                r15.setTranslationX(r12);
                r15.setTranslationY(r5);
            L20:
                r16 = r1;
                goto L31
            L36:
                if (AndroidUtilities.isTablet() == false) goto L38;
                int r13 = AndroidUtilities.dp(394.0f) / 3;
            L40:
                if ((r3 - r8) >= r13) goto L43;
                r5 = r5 + AndroidUtilities.dp(36.0f);
                r8 = 0;
            L43:
                if ((r3 - r9) >= r13) goto L45;
                r4 = r4 + AndroidUtilities.dp(36.0f);
            L45:
                SearchUsersCell.m20325$$Nest$fgeteditText(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r3 - r8, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(28.0f), TLObject.FLAG_30));
                EditTextBoldCursor r14 = SearchUsersCell.m20325$$Nest$fgeteditText(this.this$0);
                if (SearchUsersCell.m20325$$Nest$fgeteditText(this.this$0).getMeasuredWidth() <= SearchUsersCell.m20327$$Nest$fgethintTextWidth(this.this$0)) goto L48;
                boolean r32 = true;
            L49:
                r14.setHintVisible(r32, true);
                if (this.animationStarted == true) goto L73;
                int r42 = r4 + AndroidUtilities.dp(38.0f);
                int r82 = r8 + AndroidUtilities.dp(16.0f);
                SearchUsersCell.m20334$$Nest$fputfieldY(this.this$0, r5);
                if (this.currentAnimation == null) goto L65;
                int r52 = r5 + AndroidUtilities.dp(38.0f);
                SearchUsersCell r17 = this.this$0;
                r17.resultContainerHeight = r52;
                float r43 = r52;
                if (r17.containerHeight == r43) goto L56;
                this.animators.add(SearchUsersCell.m20338$$Nest$mgetContainerHeightAnimator(r17, r43));
            L56:
                float r33 = r82;
                if (SearchUsersCell.m20325$$Nest$fgeteditText(this.this$0).getTranslationX() == r33) goto L60;
                this.animators.add(ObjectAnimator.ofFloat(SearchUsersCell.m20325$$Nest$fgeteditText(this.this$0), r11, new float[]{r33}));
            L60:
                if (SearchUsersCell.m20325$$Nest$fgeteditText(this.this$0).getTranslationY() == SearchUsersCell.m20326$$Nest$fgetfieldY(this.this$0)) goto L62;
                this.animators.add(ObjectAnimator.ofFloat(SearchUsersCell.m20325$$Nest$fgeteditText(this.this$0), r10, new float[]{SearchUsersCell.m20326$$Nest$fgetfieldY(this.this$0)}));
            L62:
                SearchUsersCell.m20325$$Nest$fgeteditText(this.this$0).setAllowDrawCursor(false);
                this.currentAnimation.playTogether(this.animators);
                this.currentAnimation.setDuration(180);
                this.currentAnimation.setInterpolator(new LinearInterpolator());
                this.currentAnimation.start();
                this.animationStarted = true;
                if (SearchUsersCell.m20332$$Nest$fgetupdateHeight(this.this$0) == null) goto L71;
                SearchUsersCell.m20332$$Nest$fgetupdateHeight(this.this$0).run();
            L71:
                SearchUsersCell r110 = this.this$0;
                SearchUsersCell.m20336$$Nest$fputprevResultContainerHeight(r110, r110.resultContainerHeight);
            L82:
                setMeasuredDimension(r2, (int) this.this$0.containerHeight);
                return;
            L65:
                SearchUsersCell r111 = this.this$0;
                r111.resultContainerHeight = r42;
                r111.containerHeight = r42;
                SearchUsersCell.m20325$$Nest$fgeteditText(r111).setTranslationX(r82);
                SearchUsersCell.m20325$$Nest$fgeteditText(this.this$0).setTranslationY(SearchUsersCell.m20326$$Nest$fgetfieldY(this.this$0));
                if (SearchUsersCell.m20332$$Nest$fgetupdateHeight(this.this$0) == null) goto L69;
                SearchUsersCell.m20332$$Nest$fgetupdateHeight(this.this$0).run();
            L69:
                if (SearchUsersCell.m20331$$Nest$fgetscroll(this.this$0) == false) goto L71;
                post(new StoryPrivacyBottomSheet$SearchUsersCell$SpansContainer$$ExternalSyntheticLambda0(this));
                SearchUsersCell.m20337$$Nest$fputscroll(this.this$0, false);
                goto L71
            L73:
                if (this.currentAnimation == null) goto L82;
                if (SearchUsersCell.m20328$$Nest$fgetignoreScrollEvent(this.this$0) == true) goto L80;
                if (this.removingSpans.isEmpty() == false) goto L80;
                SearchUsersCell.m20325$$Nest$fgeteditText(this.this$0).bringPointIntoView(SearchUsersCell.m20325$$Nest$fgeteditText(this.this$0).getSelectionStart());
            L80:
                if (SearchUsersCell.m20331$$Nest$fgetscroll(this.this$0) == false) goto L82;
                this.this$0.fullScroll(130);
                SearchUsersCell.m20337$$Nest$fputscroll(this.this$0, false);
                goto L82
            L48:
                r32 = false;
                goto L49
            L38:
                Point r113 = AndroidUtilities.displaySize;
                r13 = (Math.min(r113.x, r113.y) - AndroidUtilities.dp(136.0f)) / 3;
                goto L40
            }

            public void removeAllSpans(boolean r7) {
                SearchUsersCell.m20335$$Nest$fputignoreScrollEvent(this.this$0, true);
                ArrayList r0 = new ArrayList(this.this$0.allSpans);
                this.removingSpans.clear();
                this.removingSpans.addAll(this.this$0.allSpans);
                this.this$0.allSpans.clear();
                int r2 = 0;
                int r3 = 0;
            L4:
                if (r3 >= r0.size()) goto L6;
                ((GroupCreateSpan) r0.get(r3)).setOnClickListener(null);
                r3 = r3 + 1;
                goto L4
            L6:
                setupEndValues();
                if (r7 == false) goto L12;
                this.animationStarted = false;
                AnimatorSet r72 = new AnimatorSet();
                this.currentAnimation = r72;
                r72.addListener(new AnonymousClass3(this, r0));
                this.animators.clear();
                this.animAddingSpans.clear();
                this.animRemovingSpans.clear();
            L10:
                if (r2 >= r0.size()) goto L17;
                GroupCreateSpan r73 = (GroupCreateSpan) r0.get(r2);
                this.animAddingSpans.add(r73);
                this.animators.add(ObjectAnimator.ofFloat(r73, View.SCALE_X, new float[]{1.0f, 0.01f}));
                this.animators.add(ObjectAnimator.ofFloat(r73, View.SCALE_Y, new float[]{1.0f, 0.01f}));
                this.animators.add(ObjectAnimator.ofFloat(r73, View.ALPHA, new float[]{1.0f, 0.0f}));
                r2 = r2 + 1;
            L17:
                requestLayout();
                return;
            L12:
                int r74 = 0;
            L14:
                if (r74 >= r0.size()) goto L16;
                removeView((View) r0.get(r74));
                r74 = r74 + 1;
                goto L14
            L16:
                this.removingSpans.clear();
                this.currentAnimation = null;
                this.animationStarted = false;
                SearchUsersCell.m20325$$Nest$fgeteditText(this.this$0).setAllowDrawCursor(true);
                goto L17
            }

            public void removeSpan(GroupCreateSpan r5) {
                SearchUsersCell.m20335$$Nest$fputignoreScrollEvent(this.this$0, true);
                this.this$0.allSpans.remove(r5);
                r5.setOnClickListener(null);
                setupEndValues();
                this.animationStarted = false;
                AnimatorSet r0 = new AnimatorSet();
                this.currentAnimation = r0;
                r0.addListener(new AnonymousClass1(this, r5));
                this.removingSpans.clear();
                this.removingSpans.add(r5);
                this.animAddingSpans.clear();
                this.animRemovingSpans.clear();
                this.animAddingSpans.add(r5);
                this.animators.clear();
                ArrayList<Animator> r02 = this.animators;
                Property r3 = View.SCALE_X;
                r02.add(ObjectAnimator.ofFloat(r5, r3, new float[]{1.0f, 0.01f}));
                ArrayList<Animator> r03 = this.animators;
                Property r32 = View.SCALE_Y;
                r03.add(ObjectAnimator.ofFloat(r5, r32, new float[]{1.0f, 0.01f}));
                ArrayList<Animator> r04 = this.animators;
                Property r2 = View.ALPHA;
                r04.add(ObjectAnimator.ofFloat(r5, r2, new float[]{1.0f, 0.0f}));
                requestLayout();
            }

            public void updateSpans(ArrayList<GroupCreateSpan> r9, ArrayList<GroupCreateSpan> r10, boolean r11) {
                SearchUsersCell.m20335$$Nest$fputignoreScrollEvent(this.this$0, true);
                this.this$0.allSpans.removeAll(r9);
                this.this$0.allSpans.addAll(r10);
                this.removingSpans.clear();
                this.removingSpans.addAll(r9);
                int r0 = 0;
                int r2 = 0;
            L4:
                if (r2 >= r9.size()) goto L6;
                r9.get(r2).setOnClickListener(null);
                r2 = r2 + 1;
                goto L4
            L6:
                setupEndValues();
                if (r11 == false) goto L16;
                this.animationStarted = false;
                AnimatorSet r112 = new AnimatorSet();
                this.currentAnimation = r112;
                r112.addListener(new AnonymousClass2(this, r9));
                this.animators.clear();
                this.animAddingSpans.clear();
                this.animRemovingSpans.clear();
                int r113 = 0;
            L9:
                int r1 = r9.size();
                Property r3 = View.ALPHA;
                Property r4 = View.SCALE_Y;
                Property r5 = View.SCALE_X;
                if (r113 >= r1) goto L12;
                GroupCreateSpan r12 = r9.get(r113);
                this.animRemovingSpans.add(r12);
                this.animators.add(ObjectAnimator.ofFloat(r12, r5, new float[]{1.0f, 0.01f}));
                this.animators.add(ObjectAnimator.ofFloat(r12, r4, new float[]{1.0f, 0.01f}));
                this.animators.add(ObjectAnimator.ofFloat(r12, r3, new float[]{1.0f, 0.0f}));
                r113 = r113 + 1;
                goto L9
            L12:
                int r92 = 0;
            L14:
                if (r92 >= r10.size()) goto L22;
                GroupCreateSpan r114 = r10.get(r92);
                this.animAddingSpans.add(r114);
                this.animators.add(ObjectAnimator.ofFloat(r114, r5, new float[]{0.01f, 1.0f}));
                this.animators.add(ObjectAnimator.ofFloat(r114, r4, new float[]{0.01f, 1.0f}));
                this.animators.add(ObjectAnimator.ofFloat(r114, r3, new float[]{0.0f, 1.0f}));
                r92 = r92 + 1;
            L22:
                if (r0 >= r10.size()) goto L24;
                addView(r10.get(r0));
                r0 = r0 + 1;
                goto L22
            L24:
                requestLayout();
                return;
            L16:
                int r115 = 0;
            L18:
                if (r115 >= r9.size()) goto L20;
                removeView(r9.get(r115));
                r115 = r115 + 1;
                goto L18
            L20:
                this.addingSpan = null;
                this.removingSpans.clear();
                this.currentAnimation = null;
                this.animationStarted = false;
                SearchUsersCell.m20325$$Nest$fgeteditText(this.this$0).setAllowDrawCursor(true);
                goto L22
            }
        }

        public static /* synthetic */ void $r8$lambda$fZMlLWdxEpmzVmEJ4OE7YL5lJVw(SearchUsersCell r0, ValueAnimator r1) {
            r0.lambda$getContainerHeightAnimator$0(r1);
        }

        /* renamed from: -$$Nest$fgetcurrentDeletingSpan, reason: not valid java name */
        public static /* bridge */ /* synthetic */ GroupCreateSpan m20324$$Nest$fgetcurrentDeletingSpan(SearchUsersCell r0) {
            return r0.currentDeletingSpan;
        }

        /* renamed from: -$$Nest$fgeteditText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EditTextBoldCursor m20325$$Nest$fgeteditText(SearchUsersCell r0) {
            return r0.editText;
        }

        /* renamed from: -$$Nest$fgetfieldY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m20326$$Nest$fgetfieldY(SearchUsersCell r0) {
            return r0.fieldY;
        }

        /* renamed from: -$$Nest$fgethintTextWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m20327$$Nest$fgethintTextWidth(SearchUsersCell r0) {
            return r0.hintTextWidth;
        }

        /* renamed from: -$$Nest$fgetignoreScrollEvent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m20328$$Nest$fgetignoreScrollEvent(SearchUsersCell r0) {
            return r0.ignoreScrollEvent;
        }

        /* renamed from: -$$Nest$fgetignoreTextChange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m20329$$Nest$fgetignoreTextChange(SearchUsersCell r0) {
            return r0.ignoreTextChange;
        }

        /* renamed from: -$$Nest$fgetonSearchTextChange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Utilities.Callback m20330$$Nest$fgetonSearchTextChange(SearchUsersCell r0) {
            return r0.onSearchTextChange;
        }

        /* renamed from: -$$Nest$fgetscroll, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m20331$$Nest$fgetscroll(SearchUsersCell r0) {
            return r0.scroll;
        }

        /* renamed from: -$$Nest$fgetupdateHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m20332$$Nest$fgetupdateHeight(SearchUsersCell r0) {
            return r0.updateHeight;
        }

        /* renamed from: -$$Nest$fputcurrentDeletingSpan, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20333$$Nest$fputcurrentDeletingSpan(SearchUsersCell r0, GroupCreateSpan r1) {
            r0.currentDeletingSpan = r1;
        }

        /* renamed from: -$$Nest$fputfieldY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20334$$Nest$fputfieldY(SearchUsersCell r0, int r1) {
            r0.fieldY = r1;
        }

        /* renamed from: -$$Nest$fputignoreScrollEvent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20335$$Nest$fputignoreScrollEvent(SearchUsersCell r0, boolean r1) {
            r0.ignoreScrollEvent = r1;
        }

        /* renamed from: -$$Nest$fputprevResultContainerHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20336$$Nest$fputprevResultContainerHeight(SearchUsersCell r0, int r1) {
            r0.prevResultContainerHeight = r1;
        }

        /* renamed from: -$$Nest$fputscroll, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20337$$Nest$fputscroll(SearchUsersCell r0, boolean r1) {
            r0.scroll = r1;
        }

        /* renamed from: -$$Nest$mgetContainerHeightAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Animator m20338$$Nest$mgetContainerHeightAnimator(SearchUsersCell r0, float r1) {
            return r0.getContainerHeightAnimator(r1);
        }

        public SearchUsersCell(Context r27, Theme.ResourcesProvider r28, Runnable r29) {
            super(r27);
            this.allSpans = new ArrayList();
            CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.topGradientAlpha = new AnimatedFloat(this, 0, 300, r6);
            Shader.TileMode r16 = Shader.TileMode.CLAMP;
            LinearGradient r9 = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(8.0f), new int[]{-16777216, 0}, new float[]{0.0f, 1.0f}, r16);
            this.topGradient = r9;
            Paint r10 = new Paint(1);
            this.topGradientPaint = r10;
            this.topGradientMatrix = new Matrix();
            this.bottomGradientAlpha = new AnimatedFloat(this, 0, 300, r6);
            LinearGradient r18 = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(8.0f), new int[]{0, -16777216}, new float[]{0.0f, 1.0f}, r16);
            this.bottomGradient = r18;
            Paint r2 = new Paint(1);
            this.bottomGradientPaint = r2;
            this.bottomGradientMatrix = new Matrix();
            r10.setShader(r9);
            PorterDuff.Mode r4 = PorterDuff.Mode.DST_OUT;
            r10.setXfermode(new PorterDuffXfermode(r4));
            r2.setShader(r18);
            r2.setXfermode(new PorterDuffXfermode(r4));
            this.resourcesProvider = r28;
            this.updateHeight = r29;
            setVerticalScrollBarEnabled(false);
            AndroidUtilities.setScrollViewEdgeEffectColor(this, Theme.getColor(Theme.key_windowBackgroundWhite));
            SpansContainer r0 = new SpansContainer(this, r27);
            this.spansContainer = r0;
            addView(r0, LayoutHelper.createFrame(-1, -2.0f));
            AnonymousClass1 r02 = new AnonymousClass1(this, r27);
            this.editText = r02;
            if (Build.VERSION.SDK_INT < 25) goto L5;
            r02.setRevealOnFocusHint(false);
        L5:
            this.editText.setTextSize(1, 16.0f);
            this.editText.setHintColor(Theme.getColor(Theme.key_groupcreate_hintText, r28));
            this.editText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r28));
            EditTextBoldCursor r03 = this.editText;
            int r22 = Theme.key_groupcreate_cursor;
            r03.setCursorColor(Theme.getColor(r22, r28));
            this.editText.setHandlesColor(Theme.getColor(r22, r28));
            this.editText.setCursorWidth(1.5f);
            EditTextBoldCursor r04 = this.editText;
            r04.setInputType(r04.getInputType() | 176);
            this.editText.setSingleLine(true);
            this.editText.setBackgroundDrawable(null);
            this.editText.setVerticalScrollBarEnabled(false);
            this.editText.setHorizontalScrollBarEnabled(false);
            this.editText.setTextIsSelectable(false);
            this.editText.setPadding(0, 0, 0, 0);
            this.editText.setImeOptions(268435462);
            EditTextBoldCursor r05 = this.editText;
            if (LocaleController.isRTL == false) goto L8;
            int r23 = 5;
        L9:
            r05.setGravity(r23 | 16);
            this.spansContainer.addView(this.editText);
            this.editText.setHintText(LocaleController.getString(R.string.Search));
            this.hintTextWidth = (int) this.editText.getPaint().measureText(LocaleController.getString(R.string.Search));
            this.editText.addTextChangedListener(new AnonymousClass2(this));
            return;
        L8:
            r23 = 3;
            goto L9
        }

        private Animator getContainerHeightAnimator(float r4) {
            ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{this.containerHeight, r4});
            r42.addUpdateListener(new StoryPrivacyBottomSheet$SearchUsersCell$$ExternalSyntheticLambda0(this));
            return r42;
        }

        private /* synthetic */ void lambda$getContainerHeightAnimator$0(ValueAnimator r1) {
            setContainerHeight(((Float) r1.getAnimatedValue()).floatValue());
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r15) {
            float r3 = getScrollY();
            r15.saveLayerAlpha(0.0f, r3, getWidth(), getHeight() + r0, 255, 31);
            super.dispatchDraw(r15);
            r15.save();
            float r152 = this.topGradientAlpha.set(canScrollVertically(-1));
            this.topGradientMatrix.reset();
            this.topGradientMatrix.postTranslate(0.0f, r3);
            this.topGradient.setLocalMatrix(this.topGradientMatrix);
            this.topGradientPaint.setAlpha((int) (r152 * 255.0f));
            r15.drawRect(0.0f, r3, getWidth(), AndroidUtilities.dp(8.0f) + r0, this.topGradientPaint);
            float r2 = this.bottomGradientAlpha.set(canScrollVertically(1));
            this.bottomGradientMatrix.reset();
            this.bottomGradientMatrix.postTranslate(0.0f, (getHeight() + r0) - AndroidUtilities.dp(8.0f));
            this.bottomGradient.setLocalMatrix(this.bottomGradientMatrix);
            this.bottomGradientPaint.setAlpha((int) (r2 * 255.0f));
            r15.drawRect(0.0f, (getHeight() + r0) - AndroidUtilities.dp(8.0f), getWidth(), r0 + getHeight(), this.bottomGradientPaint);
            r15.restore();
            r15.restore();
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent r1) {
            return super.dispatchKeyEvent(r1);
        }

        @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(150.0f), Integer.MIN_VALUE));
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
        public boolean requestChildRectangleOnScreen(View r4, Rect r5, boolean r6) {
            if (this.ignoreScrollEvent == false) goto L6;
            this.ignoreScrollEvent = false;
            return false;
        L6:
            r5.offset(r4.getLeft() - r4.getScrollX(), r4.getTop() - r4.getScrollY());
            r5.top += this.fieldY + AndroidUtilities.dp(20.0f);
            r5.bottom += this.fieldY + AndroidUtilities.dp(50.0f);
            return super.requestChildRectangleOnScreen(r4, r5, r6);
        }

        public void scrollToBottom() {
            this.scroll = true;
        }

        public void setContainerHeight(float r1) {
            this.containerHeight = r1;
            SpansContainer r0 = this.spansContainer;
            if (r0 == null) goto L6;
            r0.requestLayout();
            return;
        }

        public void setOnSearchTextChange(Utilities.Callback<String> r1) {
            this.onSearchTextChange = r1;
        }

        public void setText(CharSequence r2) {
            this.ignoreTextChange = true;
            this.editText.setText(r2);
            this.ignoreTextChange = false;
        }
    }

    public static class StoryPrivacy {
        public final ArrayList<TLRPC.InputPrivacyRule> rules;
        public final ArrayList<TLRPC.InputUser> selectedInputUsers;
        public final ArrayList<Long> selectedUserIds;
        public final HashMap<Long, ArrayList<Long>> selectedUserIdsByGroup;
        public final ArrayList<Long> sendToUsers;
        public final int type;

        public StoryPrivacy() {
            ArrayList<TLRPC.InputPrivacyRule> r0 = new ArrayList();
            this.rules = r0;
            this.selectedUserIds = new ArrayList();
            this.selectedUserIdsByGroup = new HashMap();
            this.selectedInputUsers = new ArrayList();
            this.sendToUsers = new ArrayList();
            this.type = 4;
            r0.add(new TLRPC.TL_inputPrivacyValueAllowAll());
        }

        public StoryPrivacy(int r6, int r7, ArrayList<Long> r8) {
            ArrayList<TLRPC.InputPrivacyRule> r0 = new ArrayList();
            this.rules = r0;
            this.selectedUserIds = new ArrayList();
            this.selectedUserIdsByGroup = new HashMap();
            this.selectedInputUsers = new ArrayList();
            ArrayList<Long> r1 = new ArrayList();
            this.sendToUsers = r1;
            this.type = r6;
            int r3 = 0;
            if (r6 != 4) goto L21;
            r0.add(new TLRPC.TL_inputPrivacyValueAllowAll());
            if (r7 < 0) goto L76;
            if (r8 != null) goto L8;
            return;
        L8:
            if (r8.isEmpty() == true) goto L78;
            TLRPC.TL_inputPrivacyValueDisallowUsers r62 = new TLRPC.TL_inputPrivacyValueDisallowUsers();
        L11:
            if (r3 >= r8.size()) goto L18;
            Long r02 = r8.get(r3);
            long r12 = r02.longValue();
            this.selectedUserIds.add(r02);
            TLRPC.InputUser r03 = MessagesController.getInstance(r7).getInputUser(r12);
            if (r03 == null) goto L17;
            if ((r03 instanceof TLRPC.TL_inputUserEmpty) == true) goto L17;
            r62.users.add(r03);
            this.selectedInputUsers.add(r03);
        L17:
            r3 = r3 + 1;
            goto L11
        L18:
            this.rules.add(r62);
            return;
        L78:
            return;
        L76:
            return;
        L21:
            if (r6 != 1) goto L25;
            r0.add(new TLRPC.TL_inputPrivacyValueAllowCloseFriends());
            return;
        L25:
            if (r6 != 2) goto L43;
            r0.add(new TLRPC.TL_inputPrivacyValueAllowContacts());
            if (r7 < 0) goto L79;
            if (r8 != null) goto L30;
            return;
        L30:
            if (r8.isEmpty() == true) goto L81;
            TLRPC.TL_inputPrivacyValueDisallowUsers r63 = new TLRPC.TL_inputPrivacyValueDisallowUsers();
        L33:
            if (r3 >= r8.size()) goto L40;
            Long r04 = r8.get(r3);
            long r13 = r04.longValue();
            this.selectedUserIds.add(r04);
            TLRPC.InputUser r05 = MessagesController.getInstance(r7).getInputUser(r13);
            if (r05 == null) goto L39;
            if ((r05 instanceof TLRPC.TL_inputUserEmpty) == true) goto L39;
            r63.users.add(r05);
            this.selectedInputUsers.add(r05);
        L39:
            r3 = r3 + 1;
            goto L33
        L40:
            this.rules.add(r63);
            return;
        L81:
            return;
        L79:
            return;
        L43:
            if (r6 != 3) goto L60;
            TLRPC.TL_inputPrivacyValueAllowUsers r64 = new TLRPC.TL_inputPrivacyValueAllowUsers();
            if (r7 < 0) goto L57;
            if (r8 == null) goto L57;
            if (r8.isEmpty() == true) goto L57;
        L50:
            if (r3 >= r8.size()) goto L57;
            Long r06 = r8.get(r3);
            long r14 = r06.longValue();
            this.selectedUserIds.add(r06);
            TLRPC.InputUser r07 = MessagesController.getInstance(r7).getInputUser(r14);
            if (r07 == null) goto L56;
            if ((r07 instanceof TLRPC.TL_inputUserEmpty) == true) goto L56;
            r64.users.add(r07);
            this.selectedInputUsers.add(r07);
        L56:
            r3 = r3 + 1;
        L57:
            this.rules.add(r64);
            return;
        L60:
            if (r6 != 5) goto L82;
            if (r8 == null) goto L83;
            r1.addAll(r8);
            return;
        L83:
            return;
        }

        public StoryPrivacy(int r6, ArrayList<TLRPC.PrivacyRule> r7) {
            ArrayList<TLRPC.InputPrivacyRule> r0 = new ArrayList();
            this.rules = r0;
            this.selectedUserIds = new ArrayList();
            this.selectedUserIdsByGroup = new HashMap();
            this.selectedInputUsers = new ArrayList();
            this.sendToUsers = new ArrayList();
            int r4 = 0;
            if (containsRule(r7, TLRPC.TL_privacyValueAllowAll.class) == null) goto L16;
            this.type = 4;
            r0.add(new TLRPC.TL_inputPrivacyValueAllowAll());
            TLRPC.TL_privacyValueDisallowUsers r72 = (TLRPC.TL_privacyValueDisallowUsers) containsRule(r7, TLRPC.TL_privacyValueDisallowUsers.class);
            if (r72 == null) goto L57;
            TLRPC.TL_inputPrivacyValueDisallowUsers r02 = new TLRPC.TL_inputPrivacyValueDisallowUsers();
            MessagesController r62 = MessagesController.getInstance(r6);
        L8:
            if (r4 >= r72.users.size()) goto L13;
            Long r1 = r72.users.get(r4);
            TLRPC.InputUser r2 = r62.getInputUser(r1.longValue());
            if ((r2 instanceof TLRPC.TL_inputUserEmpty) == true) goto L12;
            r02.users.add(r2);
            this.selectedUserIds.add(r1);
            this.selectedInputUsers.add(r2);
        L12:
            r4 = r4 + 1;
            goto L8
        L13:
            this.rules.add(r02);
            return;
        L57:
            return;
        L16:
            if (containsRule(r7, TLRPC.TL_privacyValueAllowCloseFriends.class) == null) goto L19;
            this.type = 1;
            r0.add(new TLRPC.TL_inputPrivacyValueAllowCloseFriends());
            return;
        L19:
            TLRPC.TL_privacyValueAllowUsers r12 = (TLRPC.TL_privacyValueAllowUsers) containsRule(r7, TLRPC.TL_privacyValueAllowUsers.class);
            if (r12 == null) goto L33;
            this.type = 3;
            TLRPC.TL_inputPrivacyValueAllowUsers r73 = new TLRPC.TL_inputPrivacyValueAllowUsers();
            MessagesController r63 = MessagesController.getInstance(r6);
        L23:
            if (r4 >= r12.users.size()) goto L30;
            Long r03 = r12.users.get(r4);
            TLRPC.InputUser r22 = r63.getInputUser(r03.longValue());
            if (r22 == null) goto L29;
            if ((r22 instanceof TLRPC.TL_inputUserEmpty) == true) goto L29;
            r73.users.add(r22);
            this.selectedUserIds.add(r03);
            this.selectedInputUsers.add(r22);
        L29:
            r4 = r4 + 1;
            goto L23
        L30:
            this.rules.add(r73);
            return;
        L33:
            if (containsRule(r7, TLRPC.TL_privacyValueAllowContacts.class) == null) goto L45;
            this.type = 2;
            r0.add(new TLRPC.TL_inputPrivacyValueAllowContacts());
            TLRPC.TL_privacyValueDisallowUsers r74 = (TLRPC.TL_privacyValueDisallowUsers) containsRule(r7, TLRPC.TL_privacyValueDisallowUsers.class);
            if (r74 == null) goto L58;
            TLRPC.TL_inputPrivacyValueDisallowUsers r04 = new TLRPC.TL_inputPrivacyValueDisallowUsers();
            MessagesController r64 = MessagesController.getInstance(r6);
        L38:
            if (r4 >= r74.users.size()) goto L43;
            Long r13 = r74.users.get(r4);
            TLRPC.InputUser r23 = r64.getInputUser(r13.longValue());
            if ((r23 instanceof TLRPC.TL_inputUserEmpty) == true) goto L42;
            r04.users.add(r23);
            this.selectedUserIds.add(r13);
            this.selectedInputUsers.add(r23);
        L42:
            r4 = r4 + 1;
            goto L38
        L43:
            this.rules.add(r04);
            return;
        L58:
            return;
        L45:
            this.type = 4;
        }

        public StoryPrivacy(int r5, ArrayList<TLRPC.InputUser> r6, int r7) {
            ArrayList<TLRPC.InputPrivacyRule> r72 = new ArrayList();
            this.rules = r72;
            this.selectedUserIds = new ArrayList();
            this.selectedUserIdsByGroup = new HashMap();
            this.selectedInputUsers = new ArrayList();
            this.sendToUsers = new ArrayList();
            this.type = r5;
            int r1 = 0;
            if (r5 != 4) goto L18;
            r72.add(new TLRPC.TL_inputPrivacyValueAllowAll());
            if (r6 != null) goto L7;
            return;
        L7:
            if (r6.isEmpty() == true) goto L72;
            TLRPC.TL_inputPrivacyValueDisallowUsers r52 = new TLRPC.TL_inputPrivacyValueDisallowUsers();
        L10:
            if (r1 >= r6.size()) goto L15;
            TLRPC.InputUser r73 = r6.get(r1);
            if (r73 == null) goto L14;
            r52.users.add(r73);
            this.selectedUserIds.add(Long.valueOf(r73.user_id));
            this.selectedInputUsers.add(r73);
        L14:
            r1 = r1 + 1;
            goto L10
        L15:
            this.rules.add(r52);
            return;
        L72:
            return;
        L18:
            if (r5 != 1) goto L22;
            r72.add(new TLRPC.TL_inputPrivacyValueAllowCloseFriends());
            return;
        L22:
            if (r5 != 2) goto L37;
            r72.add(new TLRPC.TL_inputPrivacyValueAllowContacts());
            if (r6 != null) goto L26;
            return;
        L26:
            if (r6.isEmpty() == true) goto L74;
            TLRPC.TL_inputPrivacyValueDisallowUsers r53 = new TLRPC.TL_inputPrivacyValueDisallowUsers();
        L29:
            if (r1 >= r6.size()) goto L34;
            TLRPC.InputUser r74 = r6.get(r1);
            if (r74 == null) goto L33;
            r53.users.add(r74);
            this.selectedUserIds.add(Long.valueOf(r74.user_id));
            this.selectedInputUsers.add(r74);
        L33:
            r1 = r1 + 1;
            goto L29
        L34:
            this.rules.add(r53);
            return;
        L74:
            return;
        L37:
            if (r5 != 3) goto L51;
            TLRPC.TL_inputPrivacyValueAllowUsers r54 = new TLRPC.TL_inputPrivacyValueAllowUsers();
            if (r6 != null) goto L41;
        L48:
            this.rules.add(r54);
            return;
        L41:
            if (r6.isEmpty() == true) goto L48;
        L43:
            if (r1 >= r6.size()) goto L48;
            TLRPC.InputUser r75 = r6.get(r1);
            if (r75 == null) goto L47;
            r54.users.add(r75);
            this.selectedUserIds.add(Long.valueOf(r75.user_id));
            this.selectedInputUsers.add(r75);
        L47:
            r1 = r1 + 1;
            goto L43
        L51:
            if (r5 != 5) goto L75;
            if (r6 != null) goto L54;
            return;
        L54:
            if (r1 >= r6.size()) goto L77;
            TLRPC.InputUser r55 = r6.get(r1);
            if (r55 == null) goto L58;
            this.sendToUsers.add(Long.valueOf(r55.user_id));
        L58:
            r1 = r1 + 1;
            goto L54
        L77:
            return;
        }

        private <T> T containsRule(ArrayList<TLRPC.PrivacyRule> r3, Class<T> r4) {
            int r2 = 0;
        L4:
            if (r2 >= r3.size()) goto L9;
            T r0 = (T) ((TLRPC.PrivacyRule) r3.get(r2));
            if (r4.isInstance(r0) == true) goto L7;
            r2 = r2 + 1;
            goto L4
        L7:
            return r0;
        L9:
            return null;
        }

        public static ArrayList<TLRPC.InputPrivacyRule> toInput(int r8, ArrayList<TLRPC.PrivacyRule> r9) {
            MessagesController r82 = MessagesController.getInstance(r8);
            ArrayList<TLRPC.InputPrivacyRule> r0 = new ArrayList();
            int r2 = 0;
        L4:
            if (r2 >= r9.size()) goto L38;
            TLRPC.PrivacyRule r3 = r9.get(r2);
            if (r3 == null) goto L37;
            if ((r3 instanceof TLRPC.TL_privacyValueAllowAll) == false) goto L12;
            r0.add(new TLRPC.TL_inputPrivacyValueAllowAll());
            goto L37
        L12:
            if ((r3 instanceof TLRPC.TL_privacyValueAllowCloseFriends) == false) goto L15;
            r0.add(new TLRPC.TL_inputPrivacyValueAllowCloseFriends());
            goto L37
        L15:
            if ((r3 instanceof TLRPC.TL_privacyValueAllowContacts) == false) goto L18;
            r0.add(new TLRPC.TL_inputPrivacyValueAllowContacts());
            goto L37
        L18:
            if ((r3 instanceof TLRPC.TL_privacyValueDisallowUsers) == false) goto L28;
            TLRPC.TL_privacyValueDisallowUsers r32 = (TLRPC.TL_privacyValueDisallowUsers) r3;
            TLRPC.TL_inputPrivacyValueDisallowUsers r4 = new TLRPC.TL_inputPrivacyValueDisallowUsers();
            int r5 = 0;
        L21:
            if (r5 >= r32.users.size()) goto L26;
            TLRPC.InputUser r6 = r82.getInputUser(r32.users.get(r5).longValue());
            if ((r6 instanceof TLRPC.TL_inputUserEmpty) == true) goto L25;
            r4.users.add(r6);
        L25:
            r5 = r5 + 1;
            goto L21
        L26:
            r0.add(r4);
            goto L37
        L28:
            if ((r3 instanceof TLRPC.TL_privacyValueAllowUsers) == false) goto L37;
            TLRPC.TL_privacyValueAllowUsers r33 = (TLRPC.TL_privacyValueAllowUsers) r3;
            TLRPC.TL_inputPrivacyValueAllowUsers r42 = new TLRPC.TL_inputPrivacyValueAllowUsers();
            int r52 = 0;
        L31:
            if (r52 >= r33.users.size()) goto L36;
            TLRPC.InputUser r62 = r82.getInputUser(r33.users.get(r52).longValue());
            if ((r62 instanceof TLRPC.TL_inputUserEmpty) == true) goto L35;
            r42.users.add(r62);
        L35:
            r52 = r52 + 1;
            goto L31
        L36:
            r0.add(r42);
        L37:
            r2 = r2 + 1;
            goto L4
        L38:
            return r0;
        }

        public static ArrayList<TLRPC.PrivacyRule> toOutput(ArrayList<TLRPC.InputPrivacyRule> r9) {
            ArrayList<TLRPC.PrivacyRule> r0 = new ArrayList();
            int r2 = 0;
        L4:
            if (r2 >= r9.size()) goto L32;
            TLRPC.InputPrivacyRule r3 = r9.get(r2);
            if (r3 == null) goto L31;
            if ((r3 instanceof TLRPC.TL_inputPrivacyValueAllowAll) == false) goto L12;
            r0.add(new TLRPC.TL_privacyValueAllowAll());
            goto L31
        L12:
            if ((r3 instanceof TLRPC.TL_inputPrivacyValueAllowCloseFriends) == false) goto L15;
            r0.add(new TLRPC.TL_privacyValueAllowCloseFriends());
            goto L31
        L15:
            if ((r3 instanceof TLRPC.TL_inputPrivacyValueAllowContacts) == false) goto L18;
            r0.add(new TLRPC.TL_privacyValueAllowContacts());
            goto L31
        L18:
            if ((r3 instanceof TLRPC.TL_inputPrivacyValueDisallowUsers) == false) goto L25;
            TLRPC.TL_inputPrivacyValueDisallowUsers r32 = (TLRPC.TL_inputPrivacyValueDisallowUsers) r3;
            TLRPC.TL_privacyValueDisallowUsers r4 = new TLRPC.TL_privacyValueDisallowUsers();
            int r5 = 0;
        L21:
            if (r5 >= r32.users.size()) goto L23;
            r4.users.add(Long.valueOf(r32.users.get(r5).user_id));
            r5 = r5 + 1;
            goto L21
        L23:
            r0.add(r4);
            goto L31
        L25:
            if ((r3 instanceof TLRPC.TL_inputPrivacyValueAllowUsers) == false) goto L31;
            TLRPC.TL_inputPrivacyValueAllowUsers r33 = (TLRPC.TL_inputPrivacyValueAllowUsers) r3;
            TLRPC.TL_privacyValueAllowUsers r42 = new TLRPC.TL_privacyValueAllowUsers();
            int r52 = 0;
        L28:
            if (r52 >= r33.users.size()) goto L30;
            r42.users.add(Long.valueOf(r33.users.get(r52).user_id));
            r52 = r52 + 1;
            goto L28
        L30:
            r0.add(r42);
        L31:
            r2 = r2 + 1;
            goto L4
        L32:
            return r0;
        }

        public boolean containsUser(TLRPC.User r7) {
            if (r7 != null) goto L5;
            return false;
        L5:
            int r1 = this.type;
            if (r1 != 4) goto L10;
            return !this.selectedUserIds.contains(Long.valueOf(r7.id));
        L10:
            if (r1 == 2) goto L12;
            if (r1 != 1) goto L21;
            return r7.close_friend;
        L21:
            if (r1 == 3) goto L23;
        L31:
            return false;
        L23:
            if (this.selectedUserIds.contains(Long.valueOf(r7.id)) == false) goto L25;
            return true;
        L25:
            Iterator<ArrayList<Long>> r6 = this.selectedUserIdsByGroup.values().iterator();
        L27:
            if (r6.hasNext() == false) goto L31;
            if (r6.next().contains(Long.valueOf(r7.id)) == false) goto L27;
            return true;
        L12:
            if (this.selectedUserIds.contains(Long.valueOf(r7.id)) == false) goto L14;
        L16:
            return false;
        L14:
            if (r7.contact == false) goto L16;
            return true;
        }

        public boolean isCloseFriends() {
            if (this.type != 1) goto L5;
            return true;
        L5:
            return false;
        }

        public boolean isNone() {
            if (this.sendToUsers.isEmpty() == true) goto L5;
            return false;
        L5:
            if (this.rules.isEmpty() == false) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isShare() {
            if (this.type != 5) goto L6;
            return true;
        L6:
            return false;
        }

        public String toString() {
            if (this.sendToUsers.isEmpty() == true) goto L7;
            return LocaleController.formatPluralString("StoryPrivacyRecipients", this.sendToUsers.size(), new Object[0]);
        L7:
            if (this.rules.isEmpty() == true) goto L9;
            TLRPC.InputPrivacyRule r0 = this.rules.get(0);
            int r2 = this.type;
            TLRPC.InputPrivacyRule r4 = null;
            if (r2 == 4) goto L13;
            if (r2 != 1) goto L27;
            return LocaleController.getString(R.string.StoryPrivacyCloseFriends);
        L27:
            if (r2 == 3) goto L29;
        L32:
            if (r2 == 2) goto L34;
            if (r2 != 0) goto L58;
            if ((r0 instanceof TLRPC.TL_inputPrivacyValueAllowUsers) == false) goto L56;
            int r8 = ((TLRPC.TL_inputPrivacyValueAllowUsers) r0).users.size();
            if (r8 > 0) goto L54;
            return LocaleController.getString(R.string.StoryPrivacyNone);
        L54:
            return LocaleController.formatPluralString("StoryPrivacyContacts", r8, new Object[0]);
        L56:
            return LocaleController.getString(R.string.StoryPrivacyNone);
        L58:
            return LocaleController.getString(R.string.StoryPrivacyNone);
        L34:
            if (this.rules.size() < 2) goto L37;
            r4 = this.rules.get(1);
        L37:
            if ((r4 instanceof TLRPC.TL_inputPrivacyValueDisallowUsers) == false) goto L45;
            int r82 = ((TLRPC.TL_inputPrivacyValueDisallowUsers) r4).users.size();
            if (r82 <= 0) goto L43;
            return LocaleController.formatPluralString("StoryPrivacyContactsExclude", r82, new Object[0]);
        L43:
            return LocaleController.getString(R.string.StoryPrivacyAllContacts);
        L45:
            return LocaleController.getString(R.string.StoryPrivacyAllContacts);
        L29:
            if ((r0 instanceof TLRPC.TL_inputPrivacyValueAllowUsers) == false) goto L32;
            return LocaleController.formatPluralString("StoryPrivacyContacts", ((TLRPC.TL_inputPrivacyValueAllowUsers) r0).users.size(), new Object[0]);
        L13:
            if (this.rules.size() < 2) goto L16;
            r4 = this.rules.get(1);
        L16:
            if ((r4 instanceof TLRPC.TL_inputPrivacyValueDisallowUsers) == false) goto L22;
            int r83 = ((TLRPC.TL_inputPrivacyValueDisallowUsers) r4).users.size();
            if (r83 <= 0) goto L22;
            return LocaleController.formatPluralString("StoryPrivacyEveryoneExclude", r83, new Object[0]);
        L22:
            return LocaleController.getString(R.string.StoryPrivacyEveryone);
        L9:
            return LocaleController.getString(R.string.StoryPrivacyNone);
        }

        public ArrayList<TLRPC.PrivacyRule> toValue() {
            ArrayList<TLRPC.PrivacyRule> r0 = new ArrayList();
            int r2 = 0;
        L4:
            if (r2 >= this.rules.size()) goto L29;
            TLRPC.InputPrivacyRule r3 = this.rules.get(r2);
            if ((r3 instanceof TLRPC.TL_inputPrivacyValueAllowAll) == false) goto L9;
            r0.add(new TLRPC.TL_privacyValueAllowAll());
        L28:
            r2 = r2 + 1;
            goto L4
        L9:
            if ((r3 instanceof TLRPC.TL_inputPrivacyValueAllowCloseFriends) == false) goto L12;
            r0.add(new TLRPC.TL_privacyValueAllowCloseFriends());
            goto L28
        L12:
            if ((r3 instanceof TLRPC.TL_inputPrivacyValueAllowContacts) == false) goto L15;
            r0.add(new TLRPC.TL_privacyValueAllowContacts());
            goto L28
        L15:
            if ((r3 instanceof TLRPC.TL_inputPrivacyValueDisallowUsers) == false) goto L22;
            TLRPC.TL_inputPrivacyValueDisallowUsers r32 = (TLRPC.TL_inputPrivacyValueDisallowUsers) r3;
            TLRPC.TL_privacyValueDisallowUsers r4 = new TLRPC.TL_privacyValueDisallowUsers();
            int r5 = 0;
        L18:
            if (r5 >= r32.users.size()) goto L20;
            r4.users.add(Long.valueOf(r32.users.get(r5).user_id));
            r5 = r5 + 1;
            goto L18
        L20:
            r0.add(r4);
            goto L28
        L22:
            if ((r3 instanceof TLRPC.TL_inputPrivacyValueAllowUsers) == false) goto L28;
            TLRPC.TL_inputPrivacyValueAllowUsers r33 = (TLRPC.TL_inputPrivacyValueAllowUsers) r3;
            TLRPC.TL_privacyValueAllowUsers r42 = new TLRPC.TL_privacyValueAllowUsers();
            int r52 = 0;
        L25:
            if (r52 >= r33.users.size()) goto L27;
            r42.users.add(Long.valueOf(r33.users.get(r52).user_id));
            r52 = r52 + 1;
            goto L25
        L27:
            r0.add(r42);
            goto L28
        L29:
            return r0;
        }
    }

    public static class UserCell extends FrameLayout {
        private Paint arrowPaint;
        private Path arrowPath;
        private final AvatarDrawable avatarDrawable;
        public final CheckBox2 checkBox;
        public long dialogId;
        private final Paint dividerPaint;
        private boolean drawArrow;
        private final BackupImageView imageView;
        private boolean[] isOnline;
        private boolean needCheck;
        private boolean needDivider;
        public final RadioButton radioButton;
        private final Theme.ResourcesProvider resourcesProvider;
        private boolean sendAs;
        private final SimpleTextView subtitleTextView;
        private final SimpleTextView titleTextView;

        /* renamed from: -$$Nest$fputdrawArrow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20343$$Nest$fputdrawArrow(UserCell r0, boolean r1) {
            r0.drawArrow = r1;
        }

        public UserCell(Context r8, Theme.ResourcesProvider r9) {
            super(r8);
            AvatarDrawable r0 = new AvatarDrawable();
            this.avatarDrawable = r0;
            this.dividerPaint = new Paint(1);
            this.sendAs = false;
            this.needCheck = true;
            this.drawArrow = true;
            this.isOnline = new boolean[1];
            this.resourcesProvider = r9;
            r0.setRoundRadius(ExteraConfig.getAvatarCorners(40.0f));
            BackupImageView r02 = new BackupImageView(r8);
            this.imageView = r02;
            r02.setRoundRadius(ExteraConfig.getAvatarCorners(40.0f));
            addView(r02);
            SimpleTextView r03 = new SimpleTextView(r8);
            this.titleTextView = r03;
            r03.setTypeface(AndroidUtilities.bold());
            r03.setTextSize(16);
            int r3 = Theme.key_dialogTextBlack;
            r03.setTextColor(Theme.getColor(r3, r9));
            int r5 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r4 = 5;
        L6:
            r03.setGravity(r4);
            NotificationCenter.listenEmojiLoading(r03);
            addView(r03);
            SimpleTextView r04 = new SimpleTextView(r8);
            this.subtitleTextView = r04;
            r04.setTextSize(14);
            r04.setTextColor(Theme.getColor(r3, r9));
            if (LocaleController.isRTL == false) goto L9;
            r5 = 5;
        L9:
            r04.setGravity(r5);
            NotificationCenter.listenEmojiLoading(r04);
            addView(r04);
            CheckBox2 r05 = new CheckBox2(r8, 21, r9);
            this.checkBox = r05;
            int r32 = Theme.key_dialogRoundCheckBox;
            int r42 = Theme.key_checkboxDisabled;
            r05.setColor(r32, r42, Theme.key_dialogRoundCheckBoxCheck);
            r05.setDrawUnchecked(true);
            r05.setDrawBackgroundAsArc(10);
            addView(r05);
            r05.setChecked(false, false);
            r05.setVisibility(8);
            RadioButton r06 = new RadioButton(r8);
            this.radioButton = r06;
            r06.setSize(AndroidUtilities.dp(20.0f));
            r06.setColor(Theme.getColor(r42, r9), Theme.getColor(Theme.key_dialogRadioBackgroundChecked, r9));
            addView(r06);
            r06.setVisibility(8);
            updateLayouts();
            return;
        L5:
            r4 = 3;
            goto L6
        }

        private void setSubtitle(CharSequence r3) {
            SimpleTextView r0 = this.titleTextView;
            if (r3 != null) goto L6;
            r0.setTranslationY(0.0f);
            this.subtitleTextView.setVisibility(8);
            return;
        L6:
            r0.setTranslationY(AndroidUtilities.dp(-9.0f));
            this.subtitleTextView.setTranslationY(AndroidUtilities.dp(12.0f));
            this.subtitleTextView.setText(r3);
            this.subtitleTextView.setVisibility(0);
        }

        private void updateLayouts() {
            BackupImageView r1 = this.imageView;
            int r3 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r2 = 5;
        L6:
            int r7 = r2 | 16;
            boolean r22 = this.needCheck;
            if (r22 == false) goto L9;
            float r8 = 53.0f;
        L10:
            if (r22 == false) goto L12;
            float r10 = 53.0f;
        L13:
            r1.setLayoutParams(LayoutHelper.createFrame(40, 40.0f, r7, r8, 0.0f, r10, 0.0f));
            SimpleTextView r12 = this.titleTextView;
            boolean r23 = LocaleController.isRTL;
            if (r23 == false) goto L16;
            int r5 = 5;
        L17:
            int r82 = r5 | 16;
            int r52 = 68;
            float r14 = 20.0f;
            if (r23 == false) goto L21;
            float r9 = 20.0f;
        L25:
            if (r23 == true) goto L27;
            float r11 = 20.0f;
        L32:
            r12.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r82, r9, 0.0f, r11, 0.0f));
            SimpleTextView r13 = this.subtitleTextView;
            boolean r24 = LocaleController.isRTL;
            if (r24 == false) goto L35;
            int r6 = 5;
        L36:
            int r17 = r6 | 16;
            if (r24 == false) goto L40;
            float r18 = 20.0f;
        L44:
            if (r24 == true) goto L46;
        L49:
            r13.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r17, r18, 0.0f, r14, 0.0f));
            CheckBox2 r15 = this.checkBox;
            if (LocaleController.isRTL == false) goto L52;
            int r25 = 5;
        L53:
            r15.setLayoutParams(LayoutHelper.createFrame(24, 24.0f, r25 | 16, 13.0f, 0.0f, 14.0f, 0.0f));
            RadioButton r0 = this.radioButton;
            if (LocaleController.isRTL == false) goto L56;
            r3 = 5;
        L56:
            r0.setLayoutParams(LayoutHelper.createFrame(22, 22.0f, r3 | 16, 14.0f, 0.0f, 15.0f, 0.0f));
            return;
        L52:
            r25 = 3;
            goto L53
        L46:
            if (this.needCheck == false) goto L48;
            r52 = 105;
        L48:
            r14 = r52;
            goto L49
        L40:
            if (this.needCheck == false) goto L42;
            int r62 = 105;
        L43:
            r18 = r62;
            goto L44
        L42:
            r62 = 68;
            goto L43
        L35:
            r6 = 3;
            goto L36
        L27:
            if (this.needCheck == false) goto L29;
            int r26 = 105;
        L30:
            r11 = r26;
            goto L32
        L29:
            r26 = 68;
            goto L30
        L21:
            if (this.needCheck == false) goto L23;
            int r63 = 105;
        L24:
            r9 = r63;
            goto L25
        L23:
            r63 = 68;
            goto L24
        L16:
            r5 = 3;
            goto L17
        L12:
            r10 = 16.0f;
            goto L13
        L9:
            r8 = 16.0f;
            goto L10
        L5:
            r2 = 3;
            goto L6
        }

        private CharSequence withArrow(CharSequence r7) {
            SpannableString r0 = new SpannableString(">");
            Drawable r6 = getContext().getResources().getDrawable(R.drawable.attach_arrow_right);
            ColoredImageSpan r1 = new ColoredImageSpan(r6, 2);
            r6.setBounds(0, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(12.0f));
            r0.setSpan(r1, 0, r0.length(), 33);
            SpannableStringBuilder r62 = new SpannableStringBuilder();
            r62.append(r7).append(" ").append(r0);
            return r62;
        }

        @Override // android.view.View
        public void onDraw(Canvas r15) {
            super.onDraw(r15);
            if (this.needDivider == false) goto L8;
            this.dividerPaint.setColor(Theme.getColor(Theme.key_divider, this.resourcesProvider));
            if (LocaleController.isRTL == false) goto L7;
            Canvas r2 = r15;
            r2.drawRect(0.0f, getHeight() - 1, getWidth() - AndroidUtilities.dp(105.0f), getHeight(), this.dividerPaint);
        L9:
            Path r152 = this.arrowPath;
            if (r152 == null) goto L21;
            Paint r0 = this.arrowPaint;
            if (r0 != null) goto L14;
            return;
        L14:
            if (this.needCheck == false) goto L16;
            return;
        L16:
            if (this.sendAs == true) goto L18;
            return;
        L18:
            if (this.drawArrow == false) goto L25;
            r2.drawPath(r152, r0);
            return;
        L25:
            return;
        L21:
            return;
        L7:
            r2 = r15;
            r2.drawRect(AndroidUtilities.dp(105.0f), getHeight() - 1, getWidth(), getHeight(), this.dividerPaint);
            goto L9
        L8:
            r2 = r15;
            goto L9
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r5) {
            super.onInitializeAccessibilityNodeInfo(r5);
            CheckBox2 r0 = this.checkBox;     // Catch: Exception -> L26
            boolean r1 = false;
            if (r0 != null) goto L6;
        L8:
            boolean r02 = false;
        L9:
            RadioButton r3 = this.radioButton;     // Catch: Exception -> L26
            if (r3 != null) goto L12;
        L14:
            if (r02 == true) goto L16;
            if (r1 == true) goto L16;
            return;
        L16:
            r5.setCheckable(true);     // Catch: Exception -> L26
            if (r02 == false) goto L19;
            boolean r4 = this.checkBox.isChecked();     // Catch: Exception -> L26
        L20:
            r5.setChecked(r4);     // Catch: Exception -> L26
            if (r02 == false) goto L23;
            String r42 = "android.widget.CheckBox";
        L24:
            r5.setClassName(r42);     // Catch: Exception -> L26
            return;
        L23:
            r42 = "android.widget.RadioButton";
            goto L24
        L19:
            r4 = this.radioButton.isChecked();     // Catch: Exception -> L26
            goto L20
        L12:
            if (r3.getVisibility() != 0) goto L14;
            r1 = true;
            goto L14
        L6:
            if (r0.getVisibility() != 0) goto L8;
            r02 = true;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            int r52 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5), TLObject.FLAG_30);
            if (this.sendAs == true) goto L5;
        L7:
            float r0 = 56.0f;
        L8:
            super.onMeasure(r52, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r0), TLObject.FLAG_30));
            if (this.needCheck == false) goto L11;
            return;
        L11:
            if (this.sendAs == false) goto L31;
            Path r53 = this.arrowPath;
            if (r53 != null) goto L15;
            this.arrowPath = new Path();
        L17:
            if (LocaleController.isRTL == false) goto L20;
            int r54 = AndroidUtilities.dp(31.0f);
        L19:
            float r55 = r54;
            float r62 = getMeasuredHeight() / 2.0f;
            if (LocaleController.isRTL == false) goto L24;
            float r02 = -1.0f;
        L25:
            this.arrowPath.moveTo(r55, r62 - AndroidUtilities.dp(6.0f));
            this.arrowPath.lineTo((r02 * AndroidUtilities.dp(6.0f)) + r55, r62);
            this.arrowPath.lineTo(r55, r62 + AndroidUtilities.dp(6.0f));
            if (this.arrowPaint != null) goto L28;
            Paint r56 = new Paint(1);
            this.arrowPaint = r56;
            r56.setStyle(Paint.Style.STROKE);
            this.arrowPaint.setStrokeCap(Paint.Cap.ROUND);
        L28:
            this.arrowPaint.setStrokeWidth(AndroidUtilities.dpf2(1.86f));
            this.arrowPaint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider), 0.3f));
            return;
        L24:
            r02 = 1.0f;
            goto L25
        L20:
            r54 = getMeasuredWidth() - AndroidUtilities.dp(31.0f);
            goto L19
        L15:
            r53.rewind();
            goto L17
        L31:
            return;
        L5:
            if (this.needCheck == true) goto L7;
            r0 = 62.0f;
            goto L8
        }

        public void set(Object r4) {
            if ((r4 instanceof TLRPC.User) == false) goto L7;
            this.titleTextView.setTypeface(AndroidUtilities.bold());
            this.titleTextView.setTranslationX(0.0f);
            setUser((TLRPC.User) r4);
            return;
        L7:
            if ((r4 instanceof TLRPC.Chat) == false) goto L11;
            this.titleTextView.setTypeface(AndroidUtilities.bold());
            this.titleTextView.setTranslationX(0.0f);
            setChat((TLRPC.Chat) r4, 0);
            return;
        L11:
            if ((r4 instanceof String) == false) goto L18;
            this.titleTextView.setTypeface(null);
            SimpleTextView r0 = this.titleTextView;
            int r1 = -AndroidUtilities.dp(52.0f);
            if (LocaleController.isRTL == false) goto L15;
            int r2 = -1;
        L16:
            r0.setTranslationX(r1 * r2);
            this.titleTextView.setText((String) r4);
            return;
        L15:
            r2 = 1;
            goto L16
        }

        public void setChat(TLRPC.Chat r6, int r7) {
            if (r6 != null) goto L4;
            long r0 = 0;
        L5:
            this.dialogId = r0;
            this.avatarDrawable.setInfo(r6);
            this.imageView.setRoundRadius(ExteraConfig.getAvatarCorners(40.0f, false, ChatObject.isForum(r6)));
            this.imageView.setForUserOrChat(r6, this.avatarDrawable);
            this.titleTextView.setText(Emoji.replaceEmoji(r6.title, this.titleTextView.getPaint().getFontMetricsInt(), false));
            this.isOnline[0] = false;
            String r1 = "Members";
            if (this.sendAs == false) goto L19;
            if (r7 > 0) goto L9;
            r7 = r6.participants_count;
        L9:
            boolean r62 = ChatObject.isChannelAndNotMegaGroup(r6);
            if (r7 < 1) goto L14;
            if (r62 == false) goto L13;
            r1 = "Subscribers";
        L13:
            String r63 = LocaleController.formatPluralString(r1, r7, new Object[0]);
        L37:
            setSubtitle(r63);
            SimpleTextView r64 = this.subtitleTextView;
            if (this.isOnline[0] == false) goto L40;
            int r02 = Theme.key_dialogTextBlue2;
        L41:
            r64.setTextColor(Theme.getColor(r02, this.resourcesProvider));
            CheckBox2 r65 = this.checkBox;
            if (this.needCheck == false) goto L44;
            int r03 = 0;
        L45:
            r65.setVisibility(r03);
            this.radioButton.setVisibility(8);
            if (r7 <= 200) goto L48;
            float r66 = 0.3f;
        L49:
            setCheckboxAlpha(r66, false);
            return;
        L48:
            r66 = 1.0f;
            goto L49
        L44:
            r03 = 8;
            goto L45
        L40:
            r02 = Theme.key_dialogTextGray3;
            goto L41
        L14:
            if (r62 == false) goto L16;
            int r67 = R.string.DiscussChannel;
        L17:
            r63 = LocaleController.getString(r67);
            goto L37
        L16:
            r67 = R.string.AccDescrGroup;
            goto L17
        L19:
            if (ChatObject.isChannel(r6) == true) goto L21;
        L28:
            if (r7 < 1) goto L31;
            r63 = LocaleController.formatPluralStringComma("Members", r7 - 1);
            goto L37
        L31:
            if (r6.has_geo == false) goto L34;
            r63 = LocaleController.getString(R.string.MegaLocation);
            goto L37
        L34:
            if (ChatObject.isPublic(r6) == true) goto L36;
            r63 = LocaleController.getString(R.string.MegaPrivate).toLowerCase();
            goto L37
        L36:
            r63 = LocaleController.getString(R.string.MegaPublic).toLowerCase();
            goto L37
        L21:
            if (r6.megagroup == true) goto L28;
            if (r7 < 1) goto L25;
            r63 = LocaleController.formatPluralStringComma("Subscribers", r7 - 1);
            goto L37
        L25:
            if (ChatObject.isPublic(r6) == true) goto L27;
            r63 = LocaleController.getString(R.string.ChannelPrivate).toLowerCase();
            goto L37
        L27:
            r63 = LocaleController.getString(R.string.ChannelPublic).toLowerCase();
            goto L37
        L4:
            r0 = -r6.id;
            goto L5
        }

        public void setCheckboxAlpha(float r5, boolean r6) {
            CheckBox2 r0 = this.checkBox;
            if (r6 == true) goto L5;
            r0.animate().cancel();
            this.checkBox.setAlpha(r5);
            this.radioButton.animate().cancel();
            this.radioButton.setAlpha(r5);
            return;
        L5:
            if (Math.abs(r0.getAlpha() - r5) <= 0.1d) goto L8;
            this.checkBox.animate().cancel();
            this.checkBox.animate().alpha(r5).start();
        L8:
            if (Math.abs(this.radioButton.getAlpha() - r5) <= 0.1d) goto L13;
            this.radioButton.animate().cancel();
            this.radioButton.animate().alpha(r5).start();
            return;
        }

        public void setChecked(boolean r2, boolean r3) {
            if (this.checkBox.getVisibility() != 0) goto L6;
            this.checkBox.setChecked(r2, r3);
        L6:
            if (this.radioButton.getVisibility() != 0) goto L9;
            this.radioButton.setChecked(r2, r3);
            return;
        }

        public void setDivider(boolean r1) {
            this.needDivider = r1;
            if (r1 == false) goto L5;
        L9:
            boolean r12 = false;
        L10:
            setWillNotDraw(r12);
            return;
        L5:
            if (this.needCheck == false) goto L7;
        L8:
            r12 = true;
            goto L10
        L7:
            if (this.sendAs == true) goto L9;
            goto L8
        }

        public void setIsSendAs(boolean r1, boolean r2) {
            this.sendAs = r1;
            if (r2 == this.needCheck) goto L6;
            this.needCheck = r2;
            updateLayouts();
        L6:
            if (this.needCheck == true) goto L9;
            this.radioButton.setVisibility(8);
            this.checkBox.setVisibility(8);
        L9:
            if (this.needDivider == false) goto L11;
        L15:
            boolean r12 = false;
        L16:
            setWillNotDraw(r12);
            return;
        L11:
            if (this.needCheck == false) goto L13;
        L14:
            r12 = true;
            goto L16
        L13:
            if (this.sendAs == true) goto L15;
            goto L14
        }

        public void setRedCheckbox(boolean r3) {
            CheckBox2 r2 = this.checkBox;
            if (r3 == false) goto L5;
            int r32 = Theme.key_color_red;
        L6:
            r2.setColor(r32, Theme.key_checkboxDisabled, Theme.key_dialogRoundCheckBoxCheck);
            return;
        L5:
            r32 = Theme.key_dialogRoundCheckBox;
            goto L6
        }

        public void setType(int r6, int r7, TLRPC.User r8) {
            int r2 = 0;
            if (r6 != 4) goto L13;
            this.titleTextView.setText(LocaleController.getString(R.string.StoryPrivacyOptionEveryone));
            if (r7 != 1) goto L8;
            if (r8 == null) goto L8;
            setSubtitle(withArrow(Emoji.replaceEmoji(LocaleController.formatString(R.string.StoryPrivacyOptionExcludePerson, new Object[]{UserObject.getUserName(r8)}), this.subtitleTextView.getPaint().getFontMetricsInt(), false)));
        L11:
            this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2, this.resourcesProvider));
            this.avatarDrawable.setAvatarType(7);
            this.avatarDrawable.setColor(-15292942, -15630089);
        L42:
            this.checkBox.setVisibility(8);
            RadioButton r62 = this.radioButton;
            if (this.needCheck == true) goto L46;
            r2 = 8;
        L46:
            r62.setVisibility(r2);
            this.imageView.setImageDrawable(this.avatarDrawable);
            this.imageView.setRoundRadius(ExteraConfig.getAvatarCorners(40.0f));
            return;
        L8:
            if (r7 <= 0) goto L10;
            setSubtitle(withArrow(LocaleController.formatPluralString("StoryPrivacyOptionExcludePeople", r7, new Object[0])));
            goto L11
        L10:
            setSubtitle(withArrow(LocaleController.getString(R.string.StoryPrivacyOptionContactsDetail)));
            goto L11
        L13:
            if (r6 != 2) goto L23;
            this.titleTextView.setText(LocaleController.getString(R.string.StoryPrivacyOptionContacts));
            if (r7 != 1) goto L18;
            if (r8 == null) goto L18;
            setSubtitle(withArrow(Emoji.replaceEmoji(LocaleController.formatString(R.string.StoryPrivacyOptionExcludePerson, new Object[]{UserObject.getUserName(r8)}), this.subtitleTextView.getPaint().getFontMetricsInt(), false)));
        L21:
            this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2, this.resourcesProvider));
            this.avatarDrawable.setAvatarType(4);
            this.avatarDrawable.setColor(-3905294, -6923014);
        L18:
            if (r7 <= 0) goto L20;
            setSubtitle(withArrow(LocaleController.formatPluralString("StoryPrivacyOptionExcludePeople", r7, new Object[0])));
            goto L21
        L20:
            setSubtitle(withArrow(LocaleController.getString(R.string.StoryPrivacyOptionContactsDetail)));
            goto L21
        L23:
            if (r6 != 1) goto L33;
            this.titleTextView.setText(LocaleController.getString(R.string.StoryPrivacyOptionCloseFriends));
            if (r7 != 1) goto L28;
            if (r8 == null) goto L28;
            setSubtitle(withArrow(Emoji.replaceEmoji(UserObject.getUserName(r8), this.subtitleTextView.getPaint().getFontMetricsInt(), false)));
        L31:
            this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2, this.resourcesProvider));
            this.avatarDrawable.setAvatarType(15);
            this.avatarDrawable.setColor(-7808710, -13781445);
        L28:
            if (r7 <= 0) goto L30;
            setSubtitle(withArrow(LocaleController.formatPluralString("StoryPrivacyOptionPeople", r7, new Object[0])));
            goto L31
        L30:
            setSubtitle(withArrow(LocaleController.getString(R.string.StoryPrivacyOptionCloseFriendsDetail)));
            goto L31
        L33:
            if (r6 != 3) goto L42;
            this.titleTextView.setText(LocaleController.getString(R.string.StoryPrivacyOptionSelectedContacts));
            if (r7 != 1) goto L38;
            if (r8 == null) goto L38;
            setSubtitle(withArrow(Emoji.replaceEmoji(UserObject.getUserName(r8), this.subtitleTextView.getPaint().getFontMetricsInt(), false)));
        L41:
            this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2, this.resourcesProvider));
            this.avatarDrawable.setAvatarType(6);
            this.avatarDrawable.setColor(-18621, -618956);
        L38:
            if (r7 <= 0) goto L40;
            setSubtitle(withArrow(LocaleController.formatPluralString("StoryPrivacyOptionPeople", r7, new Object[0])));
            goto L41
        L40:
            setSubtitle(withArrow(LocaleController.getString(R.string.StoryPrivacyOptionSelectedContactsDetail)));
            goto L41
        }

        public void setUser(TLRPC.User r4) {
            if (r4 != null) goto L4;
            long r0 = 0;
        L5:
            this.dialogId = r0;
            this.avatarDrawable.setInfo(r4);
            this.imageView.setRoundRadius(ExteraConfig.getAvatarCorners(40.0f));
            this.imageView.setForUserOrChat(r4, this.avatarDrawable);
            int r2 = 0;
            this.titleTextView.setText(Emoji.replaceEmoji(UserObject.getUserName(r4), this.titleTextView.getPaint().getFontMetricsInt(), false));
            boolean[] r02 = this.isOnline;
            r02[0] = false;
            if (this.sendAs == false) goto L8;
            setSubtitle(LocaleController.getString(R.string.VoipGroupPersonalAccount));
            this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray3, this.resourcesProvider));
        L13:
            CheckBox2 r42 = this.checkBox;
            if (this.needCheck == true) goto L17;
            r2 = 8;
        L17:
            r42.setVisibility(r2);
            this.checkBox.setAlpha(1.0f);
            this.radioButton.setVisibility(8);
            return;
        L8:
            setSubtitle(LocaleController.formatUserStatus(UserConfig.selectedAccount, r4, r02));
            SimpleTextView r43 = this.subtitleTextView;
            if (this.isOnline[0] == false) goto L11;
            int r03 = Theme.key_dialogTextBlue2;
        L12:
            r43.setTextColor(Theme.getColor(r03, this.resourcesProvider));
            goto L13
        L11:
            r03 = Theme.key_dialogTextGray3;
            goto L12
        L4:
            r0 = r4.id;
            goto L5
        }
    }

    public static /* synthetic */ void $r8$lambda$8Cpua6kyzNCcRtneTjr8g0MLnbE(StoryPrivacyBottomSheet r0, MessagesStorage r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$e3bbDBvodcFRakBqslpoVJPZ-vA, reason: not valid java name */
    public static /* synthetic */ void m20243$r8$lambda$e3bbDBvodcFRakBqslpoVJPZvA(ButtonWithCounterView r1, Runnable r2) {
        if (r1 == null) goto L4;
        r1.setLoading(false);
    L4:
        if (r2 == null) goto L7;
        r2.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$ryY0CAPwZuhGCSE0RCXuSED951o(StoryPrivacyBottomSheet r0, StoryPrivacy r1, Runnable r2, AlertDialog r3, int r4) {
        r0.lambda$done$2(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$xgbUnmtkXWy3z9ELTorUtJl5Ujg(StoryPrivacyBottomSheet r0, HashMap r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$zzb6aiHvxhSXXSbTPKrYLHlWclE(ButtonWithCounterView r1) {
        if (r1 == null) goto L5;
        r1.setLoading(false);
        return;
    }

    /* renamed from: -$$Nest$fgetactivePage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20244$$Nest$fgetactivePage(StoryPrivacyBottomSheet r0) {
        return r0.activePage;
    }

    /* renamed from: -$$Nest$fgetallowComments, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20245$$Nest$fgetallowComments(StoryPrivacyBottomSheet r0) {
        return r0.allowComments;
    }

    /* renamed from: -$$Nest$fgetallowCover, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20246$$Nest$fgetallowCover(StoryPrivacyBottomSheet r0) {
        return r0.allowCover;
    }

    /* renamed from: -$$Nest$fgetallowScreenshots, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20247$$Nest$fgetallowScreenshots(StoryPrivacyBottomSheet r0) {
        return r0.allowScreenshots;
    }

    /* renamed from: -$$Nest$fgetallowSmallChats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20248$$Nest$fgetallowSmallChats(StoryPrivacyBottomSheet r0) {
        return r0.allowSmallChats;
    }

    /* renamed from: -$$Nest$fgetapplyWhenDismiss, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20249$$Nest$fgetapplyWhenDismiss(StoryPrivacyBottomSheet r0) {
        return r0.applyWhenDismiss;
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m20250$$Nest$fgetbackgroundPaint(StoryPrivacyBottomSheet r0) {
        return r0.backgroundPaint;
    }

    /* renamed from: -$$Nest$fgetcanChangePeer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20251$$Nest$fgetcanChangePeer(StoryPrivacyBottomSheet r0) {
        return r0.canChangePeer;
    }

    /* renamed from: -$$Nest$fgetcommentsPrice, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20252$$Nest$fgetcommentsPrice(StoryPrivacyBottomSheet r0) {
        return r0.commentsPrice;
    }

    /* renamed from: -$$Nest$fgetcoverDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m20253$$Nest$fgetcoverDrawable(StoryPrivacyBottomSheet r0) {
        return r0.coverDrawable;
    }

    /* renamed from: -$$Nest$fgetexcludedContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20254$$Nest$fgetexcludedContacts(StoryPrivacyBottomSheet r0) {
        return r0.excludedContacts;
    }

    /* renamed from: -$$Nest$fgetexcludedEveryone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20255$$Nest$fgetexcludedEveryone(StoryPrivacyBottomSheet r0) {
        return r0.excludedEveryone;
    }

    /* renamed from: -$$Nest$fgetexcludedEveryoneByGroup, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m20256$$Nest$fgetexcludedEveryoneByGroup(StoryPrivacyBottomSheet r0) {
        return r0.excludedEveryoneByGroup;
    }

    /* renamed from: -$$Nest$fgetexcludedEveryoneCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20257$$Nest$fgetexcludedEveryoneCount(StoryPrivacyBottomSheet r0) {
        return r0.excludedEveryoneCount;
    }

    /* renamed from: -$$Nest$fgetisEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20258$$Nest$fgetisEdit(StoryPrivacyBottomSheet r0) {
        return r0.isEdit;
    }

    /* renamed from: -$$Nest$fgetisLive, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20259$$Nest$fgetisLive(StoryPrivacyBottomSheet r0) {
        return r0.isLive;
    }

    /* renamed from: -$$Nest$fgetisRtmpStream, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20260$$Nest$fgetisRtmpStream(StoryPrivacyBottomSheet r0) {
        return r0.isRtmpStream;
    }

    /* renamed from: -$$Nest$fgetkeepOnMyPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20261$$Nest$fgetkeepOnMyPage(StoryPrivacyBottomSheet r0) {
        return r0.keepOnMyPage;
    }

    /* renamed from: -$$Nest$fgetliveSettings, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20262$$Nest$fgetliveSettings(StoryPrivacyBottomSheet r0) {
        return r0.liveSettings;
    }

    /* renamed from: -$$Nest$fgetmessageUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20263$$Nest$fgetmessageUsers(StoryPrivacyBottomSheet r0) {
        return r0.messageUsers;
    }

    /* renamed from: -$$Nest$fgetonDone2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback m20264$$Nest$fgetonDone2(StoryPrivacyBottomSheet r0) {
        return r0.onDone2;
    }

    /* renamed from: -$$Nest$fgetonSelectedAlbums, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback m20265$$Nest$fgetonSelectedAlbums(StoryPrivacyBottomSheet r0) {
        return r0.onSelectedAlbums;
    }

    /* renamed from: -$$Nest$fgetonSelectedPeer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback m20266$$Nest$fgetonSelectedPeer(StoryPrivacyBottomSheet r0) {
        return r0.onSelectedPeer;
    }

    /* renamed from: -$$Nest$fgetselectedAlbums, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashSet m20267$$Nest$fgetselectedAlbums(StoryPrivacyBottomSheet r0) {
        return r0.selectedAlbums;
    }

    /* renamed from: -$$Nest$fgetselectedContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20268$$Nest$fgetselectedContacts(StoryPrivacyBottomSheet r0) {
        return r0.selectedContacts;
    }

    /* renamed from: -$$Nest$fgetselectedContactsByGroup, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m20269$$Nest$fgetselectedContactsByGroup(StoryPrivacyBottomSheet r0) {
        return r0.selectedContactsByGroup;
    }

    /* renamed from: -$$Nest$fgetselectedContactsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20270$$Nest$fgetselectedContactsCount(StoryPrivacyBottomSheet r0) {
        return r0.selectedContactsCount;
    }

    /* renamed from: -$$Nest$fgetselectedType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20271$$Nest$fgetselectedType(StoryPrivacyBottomSheet r0) {
        return r0.selectedType;
    }

    /* renamed from: -$$Nest$fgetsendAsMessageEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20272$$Nest$fgetsendAsMessageEnabled(StoryPrivacyBottomSheet r0) {
        return r0.sendAsMessageEnabled;
    }

    /* renamed from: -$$Nest$fgetstartedFromSendAsMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20273$$Nest$fgetstartedFromSendAsMessage(StoryPrivacyBottomSheet r0) {
        return r0.startedFromSendAsMessage;
    }

    /* renamed from: -$$Nest$fgetstoriesCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20274$$Nest$fgetstoriesCount(StoryPrivacyBottomSheet r0) {
        return r0.storiesCount;
    }

    /* renamed from: -$$Nest$fgetstoryPeriod, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20275$$Nest$fgetstoryPeriod(StoryPrivacyBottomSheet r0) {
        return r0.storyPeriod;
    }

    /* renamed from: -$$Nest$fgetviewPager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPagerFixed m20276$$Nest$fgetviewPager(StoryPrivacyBottomSheet r0) {
        return r0.viewPager;
    }

    /* renamed from: -$$Nest$fgetwhenCoverClicked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m20277$$Nest$fgetwhenCoverClicked(StoryPrivacyBottomSheet r0) {
        return r0.whenCoverClicked;
    }

    /* renamed from: -$$Nest$fputactivePage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20278$$Nest$fputactivePage(StoryPrivacyBottomSheet r0, int r1) {
        r0.activePage = r1;
    }

    /* renamed from: -$$Nest$fputallowComments, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20279$$Nest$fputallowComments(StoryPrivacyBottomSheet r0, boolean r1) {
        r0.allowComments = r1;
    }

    /* renamed from: -$$Nest$fputallowScreenshots, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20280$$Nest$fputallowScreenshots(StoryPrivacyBottomSheet r0, boolean r1) {
        r0.allowScreenshots = r1;
    }

    /* renamed from: -$$Nest$fputcommentsPrice, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20281$$Nest$fputcommentsPrice(StoryPrivacyBottomSheet r0, int r1) {
        r0.commentsPrice = r1;
    }

    /* renamed from: -$$Nest$fputexcludedEveryoneCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20282$$Nest$fputexcludedEveryoneCount(StoryPrivacyBottomSheet r0, int r1) {
        r0.excludedEveryoneCount = r1;
    }

    /* renamed from: -$$Nest$fputisRtmpStream, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20283$$Nest$fputisRtmpStream(StoryPrivacyBottomSheet r0, boolean r1) {
        r0.isRtmpStream = r1;
    }

    /* renamed from: -$$Nest$fputkeepOnMyPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20284$$Nest$fputkeepOnMyPage(StoryPrivacyBottomSheet r0, boolean r1) {
        r0.keepOnMyPage = r1;
    }

    /* renamed from: -$$Nest$fputselectedContactsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20285$$Nest$fputselectedContactsCount(StoryPrivacyBottomSheet r0, int r1) {
        r0.selectedContactsCount = r1;
    }

    /* renamed from: -$$Nest$fputselectedType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20286$$Nest$fputselectedType(StoryPrivacyBottomSheet r0, int r1) {
        r0.selectedType = r1;
    }

    /* renamed from: -$$Nest$fputstoryPeriod, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20287$$Nest$fputstoryPeriod(StoryPrivacyBottomSheet r0, int r1) {
        r0.storyPeriod = r1;
    }

    /* renamed from: -$$Nest$mdone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20288$$Nest$mdone(StoryPrivacyBottomSheet r0, StoryPrivacy r1, Runnable r2) {
        r0.done(r1, r2);
    }

    /* renamed from: -$$Nest$mgetChats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20289$$Nest$mgetChats(StoryPrivacyBottomSheet r0) {
        return r0.getChats();
    }

    /* renamed from: -$$Nest$mgetCloseFriends, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20290$$Nest$mgetCloseFriends(StoryPrivacyBottomSheet r0) {
        return r0.getCloseFriends();
    }

    /* renamed from: -$$Nest$mgetContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20291$$Nest$mgetContacts(StoryPrivacyBottomSheet r0) {
        return r0.getContacts();
    }

    /* renamed from: -$$Nest$mgetParticipantsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20292$$Nest$mgetParticipantsCount(StoryPrivacyBottomSheet r0, TLRPC.Chat r1) {
        return r0.getParticipantsCount(r1);
    }

    /* renamed from: -$$Nest$mgetUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20293$$Nest$mgetUsers(StoryPrivacyBottomSheet r0, boolean r1, boolean r2) {
        return r0.getUsers(r1, r2);
    }

    /* renamed from: -$$Nest$mmergeUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashSet m20294$$Nest$mmergeUsers(StoryPrivacyBottomSheet r0, ArrayList r1, HashMap r2) {
        return r0.mergeUsers(r1, r2);
    }

    /* renamed from: -$$Nest$mwhenSelectedShare, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryPrivacyBottomSheet m20295$$Nest$mwhenSelectedShare(StoryPrivacyBottomSheet r0, Utilities.Callback r1) {
        return r0.whenSelectedShare(r1);
    }

    private StoryPrivacyBottomSheet(int r3, Context r4, Theme.ResourcesProvider r5) {
        super(r4, true, r5);
        this.excludedEveryone = new ArrayList();
        this.excludedEveryoneByGroup = new HashMap();
        this.excludedEveryoneCount = 0;
        this.excludedContacts = new ArrayList();
        this.selectedContacts = new ArrayList();
        this.selectedContactsByGroup = new HashMap();
        this.selectedContactsCount = 0;
        this.selectedAlbums = new HashSet();
        this.allowComments = true;
        this.allowScreenshots = true;
        this.keepOnMyPage = false;
        this.allowCover = true;
        this.canChangePeer = true;
        this.isRtmpStream = false;
        this.commentsPrice = 0;
        this.storiesCount = 1;
        this.messageUsers = new ArrayList();
        this.activePage = 1;
        this.selectedType = 4;
        this.sendAsMessageEnabled = false;
        this.smallChatsParticipantsCount = new HashMap();
        this.shiftDp = -6;
        this.storyPeriod = 86400;
        this.backgroundPaint = new Paint(1);
        this.applyWhenDismiss = false;
        this.allowSmallChats = true;
        this.isEdit = false;
        init(r4);
        this.viewPager.setAdapter(new AnonymousClass4(this, r4, r3));
    }

    public /* synthetic */ StoryPrivacyBottomSheet(int r1, Context r2, Theme.ResourcesProvider r3, StoryPrivacyBottomSheetIA r4) {
        this(r1, r2, r3);
    }

    public StoryPrivacyBottomSheet(Context r3, int r4, Theme.ResourcesProvider r5) {
        super(r3, true, r5);
        this.excludedEveryone = new ArrayList();
        this.excludedEveryoneByGroup = new HashMap();
        this.excludedEveryoneCount = 0;
        this.excludedContacts = new ArrayList();
        this.selectedContacts = new ArrayList();
        this.selectedContactsByGroup = new HashMap();
        this.selectedContactsCount = 0;
        this.selectedAlbums = new HashSet();
        this.allowComments = true;
        this.allowScreenshots = true;
        this.keepOnMyPage = false;
        this.allowCover = true;
        this.canChangePeer = true;
        this.isRtmpStream = false;
        this.commentsPrice = 0;
        this.storiesCount = 1;
        this.messageUsers = new ArrayList();
        this.activePage = 1;
        this.selectedType = 4;
        this.sendAsMessageEnabled = false;
        this.smallChatsParticipantsCount = new HashMap();
        this.shiftDp = -6;
        this.storyPeriod = 86400;
        this.backgroundPaint = new Paint(1);
        this.applyWhenDismiss = false;
        this.allowSmallChats = true;
        this.isEdit = false;
        this.storyPeriod = r4;
        pullSaved();
        init(r3);
        this.viewPager.setAdapter(new AnonymousClass1(this, r3));
        MessagesStorage r32 = MessagesStorage.getInstance(this.currentAccount);
        r32.getStorageQueue().postRunnable(new StoryPrivacyBottomSheet$$ExternalSyntheticLambda0(this, r32));
        MessagesController.getInstance(this.currentAccount).getStoriesController().loadBlocklist(false);
        MessagesController.getInstance(this.currentAccount).getStoriesController().loadSendAs();
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$1000(StoryPrivacyBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$1100(StoryPrivacyBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$1200(StoryPrivacyBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$1300(StoryPrivacyBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$1400(StoryPrivacyBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1500(StoryPrivacyBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1600(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1700(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1800(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1900(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$200(StoryPrivacyBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2000(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$2100(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2200(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2300(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$2400(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2500(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$2600(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2700(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2800(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2900(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$3000(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3100(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3200(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3300(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3400(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$3500(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$3600(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3700(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3800(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3900(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$400(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$4000(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4100(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4200(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4300(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4400(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4500(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4600(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4700(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4800(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ boolean access$4900(StoryPrivacyBottomSheet r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$500(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$5000(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$5100(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$5201(StoryPrivacyBottomSheet r0) {
        return r0.keyboardHeight;
    }

    public static /* synthetic */ int access$5301(StoryPrivacyBottomSheet r0) {
        return r0.keyboardHeight;
    }

    public static /* synthetic */ boolean access$5400(StoryPrivacyBottomSheet r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ boolean access$5500(StoryPrivacyBottomSheet r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ boolean access$5600(StoryPrivacyBottomSheet r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ boolean access$5700(StoryPrivacyBottomSheet r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ boolean access$5800(StoryPrivacyBottomSheet r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ boolean access$5900(StoryPrivacyBottomSheet r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ ViewGroup access$600(StoryPrivacyBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ boolean access$6000(StoryPrivacyBottomSheet r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ int access$6100(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$6200(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$6300(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$6400(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ void access$6501(StoryPrivacyBottomSheet r0) {
        super.dismiss();
    }

    public static /* synthetic */ int access$6600(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$6700(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$6800(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$6900(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$700(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$7000(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$7100(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$7200(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$7300(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$7400(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$7500(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$7600(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$7700(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$7800(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$7900(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$800(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$8000(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$8100(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$8200(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$8300(StoryPrivacyBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$8400(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$8500(StoryPrivacyBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ViewGroup access$8600(StoryPrivacyBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ boolean access$8700(StoryPrivacyBottomSheet r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ boolean access$8800(StoryPrivacyBottomSheet r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ int access$8900(StoryPrivacyBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ boolean access$900(StoryPrivacyBottomSheet r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ int access$9000(StoryPrivacyBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    private void done(StoryPrivacy r2, Runnable r3) {
        done(r2, r3, false);
    }

    private void done(StoryPrivacy r13, Runnable r14, boolean r15) {
        ArrayList r3 = new ArrayList();
        if (this.warnUsers == null) goto L21;
        if (r13 == null) goto L21;
        MessagesController r4 = MessagesController.getInstance(this.currentAccount);
        int r6 = 0;
    L7:
        if (r6 >= this.warnUsers.size()) goto L21;
        String r7 = this.warnUsers.get(r6);
        TLObject r8 = r4.getUserOrChat(r7);
        if ((r8 instanceof TLRPC.User) == false) goto L19;
        TLRPC.User r82 = (TLRPC.User) r8;
        TLRPC.User r9 = r4.getUser(Long.valueOf(r82.id));
        if (r9 == null) goto L15;
        r82 = r9;
    L15:
        if (r82.bot == true) goto L19;
        if (r13.containsUser(r82) == true) goto L19;
        r3.add(r7);
    L19:
        r6 = r6 + 1;
    L21:
        if (r3.isEmpty() == true) goto L31;
        if (r15 == true) goto L31;
        SpannableStringBuilder r42 = new SpannableStringBuilder();
        int r72 = 0;
    L25:
        if (r72 >= Math.min(2, r3.size())) goto L29;
        if (r72 <= 0) goto L28;
        r42.append(", ");
    L28:
        SpannableString r83 = new SpannableString("@" + ((String) r3.get(r72)));
        r83.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r83.length(), 33);
        r42.append(r83);
        r72 = r72 + 1;
        goto L25
    L29:
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.StoryRestrictions)).setMessage(AndroidUtilities.replaceCharSequence("%s", LocaleController.getString(R.string.StoryRestrictionsInfo), r42)).setPositiveButton(LocaleController.getString(R.string.Proceed), new StoryPrivacyBottomSheet$$ExternalSyntheticLambda2(this, r13, r14)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
        return;
    L31:
        View r32 = this.viewPager.getViewPages()[0];
        if ((r32 instanceof Page) == false) goto L34;
        ButtonWithCounterView r33 = Page.m20311$$Nest$fgetbutton((Page) r32);
    L35:
        if (r14 == null) goto L38;
        if (r33 == null) goto L38;
        r33.setLoading(true);
    L38:
        DoneCallback r43 = this.onDone;
        if (r43 == null) goto L45;
        boolean r5 = this.allowComments;
        boolean r73 = this.allowScreenshots;
        boolean r44 = this.keepOnMyPage;
        boolean r52 = this.isRtmpStream;
        StoryPrivacyBottomSheet$$ExternalSyntheticLambda3 r10 = null;
        TLRPC.InputPeer r62 = this.selectedPeer;
        int r0 = this.commentsPrice;
        if (r14 == null) goto L43;
        r10 = new StoryPrivacyBottomSheet$$ExternalSyntheticLambda3(r33, r14);
    L43:
        r43.done(r13, r5, r73, r44, r52, r62, r0, r10, new StoryPrivacyBottomSheet$$ExternalSyntheticLambda4(r33));
        return;
    L45:
        if (r14 == null) goto L56;
        r14.run();
        return;
    L56:
        return;
    L34:
        r33 = null;
        goto L35
    }

    private ArrayList<TLObject> getChats() {
        ArrayList<TLObject> r0 = new ArrayList();
        MessagesController r8 = MessagesController.getInstance(this.currentAccount);
        ArrayList<TLRPC.Dialog> r1 = r8.getAllDialogs();
        int r2 = 0;
    L4:
        if (r2 >= r1.size()) goto L27;
        TLRPC.Dialog r3 = r1.get(r2);
        if (r8.canAddToForward(r3) == false) goto L26;
        boolean r4 = DialogObject.isUserDialog(r3.id);
        long r5 = r3.id;
        if (r4 == false) goto L20;
        TLRPC.User r32 = r8.getUser(Long.valueOf(r5));
        if (r32 == null) goto L26;
        if (r32.bot == true) goto L26;
        if (r32.id == 777000) goto L26;
        if (UserObject.isUserSelf(r32) == true) goto L26;
        r0.add(r32);
        goto L26
    L20:
        if (DialogObject.isChatDialog(r5) == false) goto L26;
        TLRPC.Chat r33 = r8.getChat(Long.valueOf(-r3.id));
        if (r33 == null) goto L26;
        if (ChatObject.isForum(r33) == true) goto L26;
        r0.add(r33);
    L26:
        r2 = r2 + 1;
        goto L4
    L27:
        return r0;
    }

    private ArrayList<TLObject> getCloseFriends() {
        ArrayList<TLObject> r3 = getContacts();
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L12;
        TLObject r1 = r3.get(r0);
        if ((r1 instanceof TLRPC.User) == false) goto L11;
        if (((TLRPC.User) r1).close_friend == true) goto L11;
        r3.remove(r0);
        r0 = r0 - 1;
    L11:
        r0 = r0 + 1;
        goto L4
    L12:
        return r3;
    }

    private ArrayList<TLObject> getContacts() {
        ArrayList<TLObject> r0 = new ArrayList();
        ArrayList<TLRPC.TL_contact> r1 = ContactsController.getInstance(this.currentAccount).contacts;
        int r2 = 0;
        if (r1 != null) goto L5;
    L6:
        ContactsController.getInstance(this.currentAccount).loadContacts(false, 0);
    L7:
        MessagesController r8 = MessagesController.getInstance(this.currentAccount);
        if (r1 != null) goto L10;
    L23:
        return r0;
    L10:
        if (r2 >= r1.size()) goto L23;
        TLRPC.TL_contact r3 = r1.get(r2);
        if (r3 == null) goto L22;
        TLRPC.User r32 = r8.getUser(Long.valueOf(r3.user_id));
        if (r32 == null) goto L22;
        if (UserObject.isUserSelf(r32) == true) goto L22;
        if (r32.bot == true) goto L22;
        if (r32.id == 777000) goto L22;
        r0.add(r32);
    L22:
        r2 = r2 + 1;
        goto L10
    L5:
        if (r1.isEmpty() == false) goto L7;
        goto L6
    }

    private int getParticipantsCount(TLRPC.Chat r4) {
        TLRPC.ChatFull r0 = MessagesController.getInstance(this.currentAccount).getChatFull(r4.id);
        if (r0 == null) goto L7;
        int r02 = r0.participants_count;
        if (r02 <= 0) goto L7;
        return r02;
    L7:
        HashMap<Long, Integer> r3 = this.smallChatsParticipantsCount;
        if (r3 == null) goto L14;
        Integer r32 = r3.get(Long.valueOf(r4.id));
        if (r32 == null) goto L14;
        return r32.intValue();
    L14:
        return r4.participants_count;
    }

    private ArrayList<TLObject> getUsers(boolean r11, boolean r12) {
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        HashMap r1 = new HashMap();
        ArrayList<TLObject> r2 = new ArrayList();
        ArrayList<TLRPC.Dialog> r3 = r0.getAllDialogs();
        ConcurrentHashMap<Long, TLRPC.TL_contact> r4 = ContactsController.getInstance(this.currentAccount).contactsDict;
        int r5 = 0;
        if (r4 == null) goto L7;
        if (r4.isEmpty() == true) goto L7;
    L11:
        if (r5 >= r3.size()) goto L40;
        TLRPC.Dialog r10 = r3.get(r5);
        if (DialogObject.isUserDialog(r10.id) == false) goto L30;
        TLRPC.User r102 = r0.getUser(Long.valueOf(r10.id));
        if (r102 == null) goto L39;
        if (r102.bot == true) goto L39;
        if (r102.id == 777000) goto L39;
        if (UserObject.isUserSelf(r102) == true) goto L39;
        if (r102.deleted == true) goto L39;
        if (r11 == false) goto L29;
        if (r4 == null) goto L39;
        if (r4.get(Long.valueOf(r102.id)) == null) goto L39;
    L29:
        r1.put(Long.valueOf(r102.id), Boolean.TRUE);
        r2.add(r102);
    L39:
        r5 = r5 + 1;
        goto L11
    L30:
        if (r12 == false) goto L39;
        if (DialogObject.isChatDialog(r10.id) == false) goto L39;
        TLRPC.Chat r103 = r0.getChat(Long.valueOf(-r10.id));
        if (r103 == null) goto L39;
        if (ChatObject.isChannelAndNotMegaGroup(r103) == true) goto L39;
        r1.put(Long.valueOf(-r103.id), Boolean.TRUE);
        r2.add(r103);
        goto L39
    L40:
        if (r4 == null) goto L55;
        Iterator<Map.Entry<Long, TLRPC.TL_contact>> r104 = r4.entrySet().iterator();
    L43:
        if (r104.hasNext() == false) goto L55;
        Long r112 = r104.next().getKey();
        r112.longValue();
        if (r1.containsKey(r112) == true) goto L43;
        TLRPC.User r113 = r0.getUser(r112);
        if (r113 == null) goto L43;
        if (r113.bot == true) goto L43;
        if (r113.id == 777000) goto L43;
        if (UserObject.isUserSelf(r113) == true) goto L43;
        r2.add(r113);
        r1.put(Long.valueOf(r113.id), Boolean.TRUE);
    L55:
        return r2;
    L7:
        if (this.loadedContacts == true) goto L9;
        ContactsController.getInstance(this.currentAccount).loadContacts(false, 0);
    L9:
        this.loadedContacts = true;
        goto L11
    }

    private void init(Context r4) {
        Bulletin.addDelegate(this.container, new AnonymousClass2(this));
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.contactsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesBlocklistUpdate);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesSendAsUpdate);
        Paint r0 = this.backgroundPaint;
        int r1 = Theme.key_dialogBackground;
        r0.setColor(Theme.getColor(r1, this.resourcesProvider));
        fixNavigationBar(Theme.getColor(r1, this.resourcesProvider));
        this.containerView = new ContainerView(this, r4);
        AnonymousClass3 r02 = new AnonymousClass3(this, r4);
        this.viewPager = r02;
        int r42 = this.backgroundPaddingLeft;
        r02.setPadding(r42, 0, r42, 0);
        this.containerView.addView(this.viewPager, LayoutHelper.createFrame(-1, -1, 119));
    }

    private /* synthetic */ void lambda$done$2(StoryPrivacy r1, Runnable r2, AlertDialog r3, int r4) {
        done(r1, r2, true);
    }

    private /* synthetic */ void lambda$new$0(HashMap r2) {
        if (this.smallChatsParticipantsCount != null) goto L5;
        this.smallChatsParticipantsCount = new HashMap();
    L5:
        this.smallChatsParticipantsCount.putAll(r2);
    }

    private /* synthetic */ void lambda$new$1(MessagesStorage r2) {
        HashMap<Long, Integer> r22 = r2.getSmallGroupsParticipantsCount();
        if (r22 != null) goto L5;
        return;
    L5:
        if (r22.isEmpty() == true) goto L10;
        AndroidUtilities.runOnUIThread(new StoryPrivacyBottomSheet$$ExternalSyntheticLambda1(this, r22));
        return;
    }

    private HashSet<Long> mergeUsers(ArrayList<Long> r1, HashMap<Long, ArrayList<Long>> r2) {
        HashSet<Long> r0 = new HashSet();
        if (r1 == null) goto L5;
        r0.addAll(r1);
    L5:
        if (r2 == null) goto L10;
        Iterator<ArrayList<Long>> r12 = r2.values().iterator();
    L8:
        if (r12.hasNext() == false) goto L10;
        r0.addAll(r12.next());
    L10:
        return r0;
    }

    private void pullSaved() {
        String r0 = MessagesController.getInstance(this.currentAccount).getMainSettings().getString("story_prv_contacts", null);
        if (r0 == null) goto L9;
        String[] r02 = r0.split(",");
        this.selectedContacts.clear();
        int r4 = 0;
    L6:
        if (r4 >= r02.length) goto L9;
        this.selectedContacts.add(Long.valueOf(Long.parseLong(r02[r4])));     // Catch: Exception -> L55
    L8:
        r4 = r4 + 1;
    L9:
        String r03 = MessagesController.getInstance(this.currentAccount).getMainSettings().getString("story_prv_grpcontacts", null);
        if (r03 == null) goto L24;
        String[] r04 = r03.split(";");
        this.selectedContactsByGroup.clear();
        int r6 = 0;
    L13:
        if (r6 >= r04.length) goto L24;
        String[] r7 = r04[r6].split(",");
        if (r7.length <= 0) goto L23;
        long r8 = Long.parseLong(r7[0]);     // Catch: Exception -> L56
        ArrayList<Long> r10 = new ArrayList();
        int r11 = 1;
    L20:
        if (r11 >= r7.length) goto L22;
        r10.add(Long.valueOf(Long.parseLong(r7[r11])));
        r11 = r11 + 1;
        goto L20
    L22:
        this.selectedContactsByGroup.put(Long.valueOf(r8), r10);
    L23:
        r6 = r6 + 1;
    L24:
        String r05 = MessagesController.getInstance(this.currentAccount).getMainSettings().getString("story_prv_everyoneexcept", null);
        if (r05 == null) goto L31;
        String[] r06 = r05.split(",");
        this.excludedEveryone.clear();
        int r62 = 0;
    L28:
        if (r62 >= r06.length) goto L31;
        this.excludedEveryone.add(Long.valueOf(Long.parseLong(r06[r62])));     // Catch: Exception -> L57
    L30:
        r62 = r62 + 1;
    L31:
        String r07 = MessagesController.getInstance(this.currentAccount).getMainSettings().getString("story_prv_grpeveryoneexcept", null);
        if (r07 == null) goto L46;
        String[] r08 = r07.split(";");
        this.excludedEveryoneByGroup.clear();
        int r42 = 0;
    L35:
        if (r42 >= r08.length) goto L46;
        String[] r63 = r08[r42].split(",");
        if (r63.length <= 0) goto L45;
        long r72 = Long.parseLong(r63[0]);     // Catch: Exception -> L58
        ArrayList<Long> r9 = new ArrayList();
        int r102 = 1;
    L42:
        if (r102 >= r63.length) goto L44;
        r9.add(Long.valueOf(Long.parseLong(r63[r102])));
        r102 = r102 + 1;
        goto L42
    L44:
        this.excludedEveryoneByGroup.put(Long.valueOf(r72), r9);
    L45:
        r42 = r42 + 1;
    L46:
        String r09 = MessagesController.getInstance(this.currentAccount).getMainSettings().getString("story_prv_excluded", null);
        if (r09 == null) goto L53;
        String[] r010 = r09.split(",");
        this.excludedContacts.clear();
        int r1 = 0;
    L50:
        if (r1 >= r010.length) goto L53;
        this.excludedContacts.add(Long.valueOf(Long.parseLong(r010[r1])));     // Catch: Exception -> L59
    L52:
        r1 = r1 + 1;
    L53:
        this.selectedContactsCount = mergeUsers(this.selectedContacts, this.selectedContactsByGroup).size();
        this.excludedEveryoneCount = mergeUsers(this.excludedEveryone, this.excludedEveryoneByGroup).size();
        this.allowScreenshots = !MessagesController.getInstance(this.currentAccount).getMainSettings().getBoolean("story_noforwards", false);
        this.keepOnMyPage = MessagesController.getInstance(this.currentAccount).getMainSettings().getBoolean("story_keep", true);
    }

    private void save() {
        StringBuilder r0 = new StringBuilder();
        Iterator<Map.Entry<Long, ArrayList<Long>>> r1 = this.selectedContactsByGroup.entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        Map.Entry<Long, ArrayList<Long>> r2 = r1.next();
        if (r0.length() <= 0) goto L8;
        r0.append(";");
    L8:
        r0.append(r2.getKey());
        r0.append(",");
        r0.append(TextUtils.join(",", r2.getValue()));
        goto L4
    L9:
        StringBuilder r12 = new StringBuilder();
        Iterator<Map.Entry<Long, ArrayList<Long>>> r22 = this.excludedEveryoneByGroup.entrySet().iterator();
    L11:
        if (r22.hasNext() == false) goto L16;
        Map.Entry<Long, ArrayList<Long>> r5 = r22.next();
        if (r12.length() <= 0) goto L15;
        r12.append(";");
    L15:
        r12.append(r5.getKey());
        r12.append(",");
        r12.append(TextUtils.join(",", r5.getValue()));
        goto L11
    L16:
        MessagesController.getInstance(this.currentAccount).getMainSettings().edit().putString("story_prv_everyoneexcept", TextUtils.join(",", this.excludedEveryone)).putString("story_prv_grpeveryoneexcept", r12.toString()).putString("story_prv_contacts", TextUtils.join(",", this.selectedContacts)).putString("story_prv_grpcontacts", r0.toString()).putString("story_prv_excluded", TextUtils.join(",", this.excludedContacts)).putBoolean("story_noforwards", !this.allowScreenshots).putBoolean("story_keep", this.keepOnMyPage).apply();
    }

    private StoryPrivacyBottomSheet whenSelectedShare(Utilities.Callback<ArrayList<Long>> r1) {
        this.onDone2 = r1;
        return this;
    }

    public StoryPrivacyBottomSheet allowCover(boolean r5) {
        this.allowCover = r5;
        ViewPagerFixed r52 = this.viewPager;
        if (r52 == null) goto L11;
        View[] r53 = r52.getViewPages();
        int r1 = 0;
    L6:
        if (r1 >= r53.length) goto L11;
        View r2 = r53[r1];
        if ((r2 instanceof Page) == false) goto L10;
        ((Page) r2).updateButton(false);
    L10:
        r1 = r1 + 1;
    L11:
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        View r1 = this.viewPager.getCurrentView();
        if ((r1 instanceof Page) == true) goto L5;
        return true;
    L5:
        return ((Page) r1).wasAtTop;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canSwipeToBack(MotionEvent r1) {
        return false;
    }

    public void closeKeyboard() {
        View[] r3 = this.viewPager.getViewPages();
        int r0 = 0;
    L4:
        if (r0 >= r3.length) goto L11;
        View r1 = r3[r0];
        if ((r1 instanceof Page) == false) goto L10;
        Page r12 = (Page) r1;
        if (Page.m20317$$Nest$fgetsearchField(r12) == null) goto L10;
        AndroidUtilities.hideKeyboard(SearchUsersCell.m20325$$Nest$fgeteditText(Page.m20317$$Nest$fgetsearchField(r12)));
    L10:
        r0 = r0 + 1;
        goto L4
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        ViewPagerFixed r2 = this.viewPager;
        if (r2 == null) goto L49;
        int r52 = 0;
        if (r3 != NotificationCenter.contactsDidLoad) goto L15;
        View[] r22 = r2.getViewPages();
        View r32 = r22[0];
        if ((r32 instanceof Page) == false) goto L10;
        ((Page) r32).updateItems(true);
    L10:
        View r23 = r22[1];
        if ((r23 instanceof Page) == false) goto L38;
        ((Page) r23).updateItems(true);
        return;
    L38:
        return;
    L15:
        if (r3 != NotificationCenter.storiesBlocklistUpdate) goto L28;
        View[] r24 = r2.getViewPages();
    L18:
        if (r52 >= r24.length) goto L50;
        View r33 = r24[r52];
        if ((r33 instanceof Page) == false) goto L26;
        Page r34 = (Page) r33;
        int r42 = r34.pageType;
        if (r42 != 6) goto L24;
        r34.applyBlocklist(true);
        goto L26
    L24:
        if (r42 != 0) goto L26;
        r34.updateItems(true);
    L26:
        r52 = r52 + 1;
        goto L18
    L50:
        return;
    L28:
        if (r3 != NotificationCenter.storiesSendAsUpdate) goto L48;
        View[] r25 = r2.getViewPages();
    L31:
        if (r52 >= r25.length) goto L51;
        View r35 = r25[r52];
        if ((r35 instanceof Page) == false) goto L37;
        Page r36 = (Page) r35;
        if (r36.pageType != 0) goto L37;
        r36.updateItems(true);
    L37:
        r52 = r52 + 1;
        goto L31
    L51:
        return;
    L48:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        if (this.onDismiss == null) goto L16;
        int r0 = this.selectedType;
        if (r0 != 3) goto L8;
        HashSet<Long> r02 = mergeUsers(this.selectedContacts, this.selectedContactsByGroup);
        StoryPrivacy r1 = new StoryPrivacy(this.selectedType, this.currentAccount, new ArrayList(r02));
        r1.selectedUserIds.clear();
        r1.selectedUserIds.addAll(this.selectedContacts);
        r1.selectedUserIdsByGroup.clear();
        r1.selectedUserIdsByGroup.putAll(this.selectedContactsByGroup);
    L15:
        this.onDismiss.run(r1);
        this.onDismiss = null;
        goto L16
    L8:
        if (r0 != 4) goto L10;
        HashSet<Long> r03 = mergeUsers(this.excludedEveryone, this.excludedEveryoneByGroup);
        r1 = new StoryPrivacy(this.selectedType, this.currentAccount, new ArrayList(r03));
        r1.selectedUserIds.clear();
        r1.selectedUserIds.addAll(this.excludedEveryone);
        r1.selectedUserIdsByGroup.clear();
        r1.selectedUserIdsByGroup.putAll(this.excludedEveryoneByGroup);
        goto L15
    L10:
        int r12 = this.currentAccount;
        if (r0 != 2) goto L14;
        StoryPrivacy r3 = new StoryPrivacy(r0, r12, this.excludedContacts);
    L13:
        r1 = r3;
        goto L15
    L14:
        r3 = new StoryPrivacy(r0, r12, null);
    L16:
        Bulletin.removeDelegate(this.container);
        save();
        super.dismiss();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.contactsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesBlocklistUpdate);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesSendAsUpdate);
        super.dismissInternal();
    }

    public StoryPrivacyBottomSheet enableSharing(boolean r5) {
        this.sendAsMessageEnabled = r5;
        ViewPagerFixed r52 = this.viewPager;
        if (r52 == null) goto L11;
        View[] r53 = r52.getViewPages();
        int r1 = 0;
    L6:
        if (r1 >= r53.length) goto L11;
        View r2 = r53[r1];
        if ((r2 instanceof Page) == false) goto L10;
        ((Page) r2).updateButton(false);
    L10:
        r1 = r1 + 1;
    L11:
        return this;
    }

    public StoriesController getStoriesController() {
        return MessagesController.getInstance(this.currentAccount).getStoriesController();
    }

    public StoryPrivacyBottomSheet isEdit(boolean r5) {
        this.isEdit = r5;
        ViewPagerFixed r52 = this.viewPager;
        if (r52 == null) goto L11;
        View[] r53 = r52.getViewPages();
        int r1 = 0;
    L6:
        if (r1 >= r53.length) goto L11;
        View r2 = r53[r1];
        if ((r2 instanceof Page) == false) goto L10;
        Page r22 = (Page) r2;
        r22.updateItems(false);
        r22.updateButton(false);
    L10:
        r1 = r1 + 1;
    L11:
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        if (this.viewPager.getCurrentPosition() <= 0) goto L6;
        closeKeyboard();
        this.viewPager.scrollToPosition(r1.getCurrentPosition() - 1);
        return;
    L6:
        super.onBackPressed();
    }

    public StoryPrivacyBottomSheet set(boolean r1, boolean r2, boolean r3, int r4) {
        this.allowComments = r1;
        this.allowScreenshots = r2;
        this.keepOnMyPage = r3;
        this.commentsPrice = r4;
        View[] r12 = this.viewPager.getViewPages();
        View r22 = r12[0];
        if ((r22 instanceof Page) == false) goto L5;
        ((Page) r22).bind(((Page) r22).pageType);
    L5:
        View r13 = r12[1];
        if ((r13 instanceof Page) == false) goto L8;
        ((Page) r13).bind(((Page) r13).pageType);
    L8:
        return this;
    }

    public StoryPrivacyBottomSheet setCanChangePeer(boolean r1) {
        this.canChangePeer = r1;
        return this;
    }

    public StoryPrivacyBottomSheet setCount(int r5) {
        this.storiesCount = r5;
        ViewPagerFixed r52 = this.viewPager;
        if (r52 == null) goto L11;
        View[] r53 = r52.getViewPages();
        int r1 = 0;
    L6:
        if (r1 >= r53.length) goto L11;
        View r2 = r53[r1];
        if ((r2 instanceof Page) == false) goto L10;
        ((Page) r2).updateButton(false);
    L10:
        r1 = r1 + 1;
    L11:
        return this;
    }

    public StoryPrivacyBottomSheet setCover(Bitmap r5) {
        if (r5 != null) goto L4;
        BitmapDrawable r52 = null;
    L5:
        this.coverDrawable = r52;
        ViewPagerFixed r53 = this.viewPager;
        if (r53 == null) goto L14;
        View[] r54 = r53.getViewPages();
        int r1 = 0;
    L9:
        if (r1 >= r54.length) goto L14;
        View r2 = r54[r1];
        if ((r2 instanceof Page) == false) goto L13;
        Page r22 = (Page) r2;
        r22.updateItems(false);
        r22.updateButton(false);
    L13:
        r1 = r1 + 1;
    L14:
        return this;
    L4:
        r52 = new BitmapDrawable(r5);
        goto L5
    }

    public StoryPrivacyBottomSheet setCover(Bitmap r4, Runnable r5) {
        if (r4 != null) goto L4;
        BitmapDrawable r42 = null;
    L5:
        this.coverDrawable = r42;
        this.whenCoverClicked = r5;
        ViewPagerFixed r43 = this.viewPager;
        if (r43 == null) goto L14;
        View[] r44 = r43.getViewPages();
        int r0 = 0;
    L9:
        if (r0 >= r44.length) goto L14;
        View r1 = r44[r0];
        if ((r1 instanceof Page) == false) goto L13;
        Page r12 = (Page) r1;
        r12.updateItems(false);
        r12.updateButton(false);
    L13:
        r0 = r0 + 1;
    L14:
        return this;
    L4:
        r42 = new BitmapDrawable(r4);
        goto L5
    }

    public StoryPrivacyBottomSheet setLive(boolean r3) {
        this.isLive = r3;
        View[] r32 = this.viewPager.getViewPages();
        View r0 = r32[0];
        if ((r0 instanceof Page) == false) goto L5;
        ((Page) r0).bind(((Page) r0).pageType);
    L5:
        View r33 = r32[1];
        if ((r33 instanceof Page) == false) goto L8;
        ((Page) r33).bind(((Page) r33).pageType);
    L8:
        return this;
    }

    public StoryPrivacyBottomSheet setLiveSettings(boolean r3) {
        this.liveSettings = r3;
        View[] r32 = this.viewPager.getViewPages();
        View r0 = r32[0];
        if ((r0 instanceof Page) == false) goto L5;
        ((Page) r0).bind(((Page) r0).pageType);
    L5:
        View r33 = r32[1];
        if ((r33 instanceof Page) == false) goto L8;
        ((Page) r33).bind(((Page) r33).pageType);
    L8:
        return this;
    }

    public StoryPrivacyBottomSheet setPeer(TLRPC.InputPeer r3) {
        this.selectedPeer = r3;
        this.selectedAlbums.clear();
        View[] r32 = this.viewPager.getViewPages();
        View r0 = r32[0];
        if ((r0 instanceof Page) == false) goto L5;
        ((Page) r0).bind(((Page) r0).pageType);
    L5:
        View r33 = r32[1];
        if ((r33 instanceof Page) == false) goto L8;
        ((Page) r33).bind(((Page) r33).pageType);
    L8:
        return this;
    }

    public StoryPrivacyBottomSheet setValue(StoryPrivacy r4) {
        if (r4 == null) goto L22;
        int r0 = r4.type;
        this.selectedType = r0;
        if (r0 != 2) goto L8;
        this.excludedContacts.clear();
        this.excludedContacts.addAll(r4.selectedUserIds);
    L14:
        if (r4.isShare() == false) goto L16;
        this.startedFromSendAsMessage = true;
        this.activePage = 5;
        this.messageUsers.clear();
        this.messageUsers.addAll(r4.sendToUsers);
        this.viewPager.setPosition(1);
    L16:
        View[] r42 = this.viewPager.getViewPages();
        View r02 = r42[0];
        if ((r02 instanceof Page) == false) goto L19;
        ((Page) r02).bind(((Page) r02).pageType);
    L19:
        View r43 = r42[1];
        if ((r43 instanceof Page) == false) goto L22;
        ((Page) r43).bind(((Page) r43).pageType);
        goto L22
    L8:
        if (r0 != 3) goto L11;
        this.selectedContacts.clear();
        this.selectedContacts.addAll(r4.selectedUserIds);
        this.selectedContactsByGroup.clear();
        this.selectedContactsByGroup.putAll(r4.selectedUserIdsByGroup);
        this.selectedContactsCount = mergeUsers(this.selectedContacts, this.selectedContactsByGroup).size();
        goto L14
    L11:
        if (r0 != 4) goto L14;
        this.excludedEveryone.clear();
        this.excludedEveryone.addAll(r4.selectedUserIds);
        this.excludedEveryoneByGroup.clear();
        this.excludedEveryoneByGroup.putAll(r4.selectedUserIdsByGroup);
        this.excludedEveryoneCount = mergeUsers(this.excludedEveryone, this.excludedEveryoneByGroup).size();
    L22:
        return this;
    }

    public StoryPrivacyBottomSheet setWarnUsers(ArrayList<String> r1) {
        this.warnUsers = r1;
        return this;
    }

    public StoryPrivacyBottomSheet whenDismiss(Utilities.Callback<StoryPrivacy> r1) {
        this.onDismiss = r1;
        return this;
    }

    public StoryPrivacyBottomSheet whenSelectedAlbums(Utilities.Callback<HashSet<Integer>> r1) {
        this.onSelectedAlbums = r1;
        return this;
    }

    public StoryPrivacyBottomSheet whenSelectedPeer(Utilities.Callback<TLRPC.InputPeer> r1) {
        this.onSelectedPeer = r1;
        return this;
    }

    public StoryPrivacyBottomSheet whenSelectedRules(DoneCallback r1, boolean r2) {
        this.onDone = r1;
        this.applyWhenDismiss = r2;
        return this;
    }
}
