package org.telegram.ui.Stories;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedColor;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.GradientTools;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.Shaker$$ExternalSyntheticLambda0;
import org.telegram.ui.Components.Text;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes3.dex */
public abstract class StoriesUtilities {
    public static GradientTools closeFriendsGradientTools;
    static Runnable debugRunnable;
    static int debugState;
    public static GradientTools errorGradientTools;
    public static Drawable expiredStoryDrawable;
    private static final Path forumRoundRectPath = null;
    private static final PathMeasure forumRoundRectPathMeasure = null;
    private static final Path forumSegmentPath = null;
    private static final Path forumWrappedSegmentPath = null;
    public static int grayLastColor;
    public static Paint grayPaint;
    public static Paint liveCutPaint;
    public static GradientTools liveGradientTools;
    public static Paint livePaint;
    public static RectF liveRect;
    public static Text liveText;
    private static final RectF rectTmp = null;
    static boolean scheduled;
    public static GradientTools[] storiesGradientTools;
    private static final int[] storyCellGrayLastColor = null;
    public static Paint[] storyCellGreyPaint;

    /* renamed from: org.telegram.ui.Stories.StoriesUtilities$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        public AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int r2 = Math.abs(Utilities.random.nextInt() % 3);
            StoriesUtilities.debugState = r2;
            if (r2 != 2) goto L5;
            StoriesUtilities.debugState = 1;
        L6:
            NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{0});
            AndroidUtilities.runOnUIThread(StoriesUtilities.debugRunnable, 1000);
            LaunchActivity.getLastFragment().getFragmentView();
            return;
        L5:
            StoriesUtilities.debugState = 2;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoriesUtilities$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends ImageReceiver {
        final /* synthetic */ EnsureStoryFileLoadedObject val$ensureStoryFileLoadedObject;
        final /* synthetic */ Runnable[] val$runnableRef;

        public AnonymousClass2(Runnable[] r1, EnsureStoryFileLoadedObject r2) {
            this.val$runnableRef = r1;
            this.val$ensureStoryFileLoadedObject = r2;
        }

        @Override // org.telegram.messenger.ImageReceiver
        public boolean setImageBitmapByKey(Drawable r1, String r2, int r3, boolean r4, int r5) {
            boolean r12 = super.setImageBitmapByKey(r1, r2, r3, r4, r5);
            Runnable r22 = this.val$runnableRef[0];
            if (r22 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r22);
            this.val$ensureStoryFileLoadedObject.runnable.run();
        L5:
            AndroidUtilities.runOnUIThread(new StoriesUtilities$2$$ExternalSyntheticLambda0(this));
            return r12;
        }
    }

    public static class AvatarStoryParams {
        public float additionalInset;
        public boolean allowLongress;
        public float alpha;
        public boolean animate;
        public int animateFromUnreadState;
        public float avatarRadius;
        ButtonBounce buttonBounce;
        public View child;
        public long crossfadeToDialog;
        public float crossfadeToDialogProgress;
        public int currentState;
        private long dialogId;
        public boolean drawHiddenStoriesAsSegments;
        public boolean drawInside;
        public boolean drawSegments;
        public boolean drawnLive;
        public boolean forceAnimateProgressToSegments;
        public int forceState;
        float globalAngle;
        public int globalState;
        boolean inc;
        public boolean isArchive;
        public boolean isDialogStoriesCell;
        public boolean isFirst;
        public boolean isLast;
        public final boolean isStoryCell;
        public float leftBottomAngleToExclude;
        public float leftTopAngleToExclude;
        Runnable longPressRunnable;
        UserStoriesLoadOperation operation;
        public RectF originalAvatarRect;
        boolean pressed;
        public int prevState;
        public int prevUnreadState;
        public float progressToArc;
        public float progressToProgressSegments;
        public float progressToSate;
        public float progressToSegments;
        public Theme.ResourcesProvider resourcesProvider;
        public float rightBottomAngleToExclude;
        public float rightTopAngleToExclude;
        public boolean showProgress;
        float startX;
        float startY;
        public int storyId;
        public TL_stories.StoryItem storyItem;
        float sweepAngle;
        public int unreadState;
        public boolean useArcProgress;

        public static /* synthetic */ void $r8$lambda$gbLo5ospYFYscwhLt7Lpbuuf3Wo(AvatarStoryParams r0, View r1) {
            r0.lambda$checkOnTouchEvent$0(r1);
        }

        /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m19730$$Nest$fgetdialogId(AvatarStoryParams r2) {
            return r2.dialogId;
        }

        /* renamed from: -$$Nest$fputdialogId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19731$$Nest$fputdialogId(AvatarStoryParams r0, long r1) {
            r0.dialogId = r1;
        }

        /* renamed from: -$$Nest$mupdateProgressParams, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19732$$Nest$mupdateProgressParams(AvatarStoryParams r0) {
            r0.updateProgressParams();
        }

        public AvatarStoryParams(boolean r2) {
            this(r2, null);
        }

        public AvatarStoryParams(boolean r4, Theme.ResourcesProvider r5) {
            this.drawSegments = true;
            this.avatarRadius = -1.0f;
            this.animate = true;
            this.progressToSegments = 1.0f;
            this.progressToArc = 0.0f;
            this.rightTopAngleToExclude = 0.0f;
            this.rightBottomAngleToExclude = 0.0f;
            this.leftTopAngleToExclude = 0.0f;
            this.leftBottomAngleToExclude = 0.0f;
            this.useArcProgress = true;
            this.alpha = 1.0f;
            this.progressToSate = 1.0f;
            this.showProgress = false;
            this.originalAvatarRect = new RectF();
            this.allowLongress = false;
            this.isStoryCell = r4;
            this.resourcesProvider = r5;
        }

        private /* synthetic */ void lambda$checkOnTouchEvent$0(View r3) {
            r3.performHapticFeedback(0);     // Catch: Exception -> L12
        L4:
            ButtonBounce r1 = this.buttonBounce;
            if (r1 == null) goto L7;
            r1.setPressed(false);
        L7:
            ViewParent r32 = r3.getParent();
            if ((r32 instanceof ViewGroup) == false) goto L10;
            ((ViewGroup) r32).requestDisallowInterceptTouchEvent(false);
        L10:
            this.pressed = false;
            onLongPress();
        }

        private void processOpenStory(View r11) {
            if (onAvatarClick(r11, this.dialogId) == true) goto L46;
            MessagesController r0 = MessagesController.getInstance(UserConfig.selectedAccount);
            StoriesController r1 = r0.getStoriesController();
            if (this.drawHiddenStoriesAsSegments == false) goto L10;
            openStory(0, null);
            return;
        L10:
            if (this.dialogId == UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId()) goto L37;
            boolean r12 = r1.hasStories(this.dialogId);
            long r6 = this.dialogId;
            if (r12 == false) goto L16;
            openStory(r6, null);
            return;
        L16:
            if (r6 <= 0) goto L27;
            TLRPC.User r02 = r0.getUser(Long.valueOf(r6));
            if (r02 != null) goto L20;
            return;
        L20:
            if (r02.stories_unavailable == true) goto L39;
            TLRPC.TL_recentStory r03 = r02.stories_max_id;
            if (r03 != null) goto L24;
            return;
        L24:
            if (r03.max_id <= 0) goto L41;
            new UserStoriesLoadOperation().load(this.dialogId, r11, this);
            return;
        L41:
            return;
        L39:
            return;
        L27:
            TLRPC.Chat r04 = r0.getChat(Long.valueOf(-r6));
            if (r04 != null) goto L30;
            return;
        L30:
            if (r04.stories_unavailable == true) goto L43;
            TLRPC.TL_recentStory r05 = r04.stories_max_id;
            if (r05 != null) goto L34;
            return;
        L34:
            if (r05.max_id <= 0) goto L45;
            new UserStoriesLoadOperation().load(this.dialogId, r11, this);
            return;
        L45:
            return;
        L43:
            return;
        L37:
            return;
        }

        private void updateProgressParams() {
            boolean r0 = this.inc;
            float r1 = this.sweepAngle;
            if (r0 == false) goto L7;
            float r12 = r1 + 0.016f;
            this.sweepAngle = r12;
            if (r12 < 1.0f) goto L10;
            this.sweepAngle = 1.0f;
            this.inc = false;
        L10:
            this.globalAngle += 1.152f;
            return;
        L7:
            float r13 = r1 - 0.016f;
            this.sweepAngle = r13;
            if (r13 >= 0.0f) goto L10;
            this.sweepAngle = 0.0f;
            this.inc = true;
            goto L10
        }

        public boolean checkOnTouchEvent(MotionEvent r12, View r13) {
            this.child = r13;
            StoriesController r0 = MessagesController.getInstance(UserConfig.selectedAccount).getStoriesController();
            boolean r2 = false;
            if (r12.getAction() != 0) goto L53;
            if (this.originalAvatarRect.contains(r12.getX(), r12.getY()) == false) goto L53;
            TLRPC.Chat r4 = null;
            if (this.dialogId <= 0) goto L9;
            TLRPC.User r1 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(this.dialogId));
        L11:
            if (isAvatarClickable(this.dialogId, r4, r1) == false) goto L14;
            boolean r02 = true;
        L40:
            if (this.dialogId == UserConfig.getInstance(UserConfig.selectedAccount).clientUserId) goto L86;
            if (r02 == false) goto L86;
            ButtonBounce r03 = this.buttonBounce;
            if (r03 != null) goto L45;
            this.buttonBounce = new ButtonBounce(r13, 1.5f, 5.0f);
        L46:
            r13.getParent().requestDisallowInterceptTouchEvent(true);
            this.buttonBounce.setPressed(true);
            this.pressed = true;
            this.startX = r12.getX();
            this.startY = r12.getY();
            if (this.allowLongress == false) goto L86;
            Runnable r122 = this.longPressRunnable;
            if (r122 == null) goto L51;
            AndroidUtilities.cancelRunOnUIThread(r122);
        L51:
            StoriesUtilities$AvatarStoryParams$$ExternalSyntheticLambda0 r123 = new StoriesUtilities$AvatarStoryParams$$ExternalSyntheticLambda0(this, r13);
            this.longPressRunnable = r123;
            AndroidUtilities.runOnUIThread(r123, ViewConfiguration.getLongPressTimeout());
            goto L86
        L45:
            r03.setView(r13);
        L86:
            return this.pressed;
        L14:
            if (this.drawHiddenStoriesAsSegments == false) goto L17;
            r02 = r0.hasHiddenStories();
            goto L40
        L17:
            if (this.dialogId <= 0) goto L30;
            if (MessagesController.getInstance(UserConfig.selectedAccount).getStoriesController().hasStories(this.dialogId) == true) goto L27;
            if (r1 != null) goto L22;
        L28:
            r02 = r2;
            goto L40
        L22:
            if (r1.stories_unavailable == true) goto L28;
            TLRPC.TL_recentStory r04 = r1.stories_max_id;
            if (r04 == null) goto L28;
            if (r04.max_id <= 0) goto L28;
        L27:
            r2 = true;
            goto L28
        L30:
            if (MessagesController.getInstance(UserConfig.selectedAccount).getStoriesController().hasStories(this.dialogId) == true) goto L27;
            if (r4 == null) goto L28;
            if (r4.stories_unavailable == true) goto L28;
            TLRPC.TL_recentStory r05 = r4.stories_max_id;
            if (r05 == null) goto L28;
            if (r05.max_id <= 0) goto L28;
        L9:
            r4 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-this.dialogId));
            r1 = null;
        L53:
            if (r12.getAction() != 2) goto L68;
            if (this.pressed == false) goto L68;
            if (Math.abs(this.startX - r12.getX()) <= AndroidUtilities.touchSlop) goto L59;
        L60:
            ButtonBounce r124 = this.buttonBounce;
            if (r124 == null) goto L63;
            r124.setView(r13);
            this.buttonBounce.setPressed(false);
        L63:
            Runnable r125 = this.longPressRunnable;
            if (r125 == null) goto L66;
            AndroidUtilities.cancelRunOnUIThread(r125);
        L66:
            r13.getParent().requestDisallowInterceptTouchEvent(false);
            this.pressed = false;
            goto L86
        L59:
            if (Math.abs(this.startY - r12.getY()) <= AndroidUtilities.touchSlop) goto L86;
        L68:
            if (r12.getAction() != 1) goto L70;
        L71:
            ButtonBounce r06 = this.buttonBounce;
            if (r06 == null) goto L75;
            r06.setView(r13);
            this.buttonBounce.setPressed(false);
        L75:
            if (this.pressed == true) goto L77;
        L79:
            ViewParent r126 = r13.getParent();
            if ((r126 instanceof ViewGroup) == false) goto L82;
            ((ViewGroup) r126).requestDisallowInterceptTouchEvent(false);
        L82:
            this.pressed = false;
            Runnable r127 = this.longPressRunnable;
            if (r127 == null) goto L86;
            AndroidUtilities.cancelRunOnUIThread(r127);
            goto L86
        L77:
            if (r12.getAction() != 1) goto L79;
            processOpenStory(r13);
            goto L79
        L70:
            if (r12.getAction() != 3) goto L86;
            goto L71
        }

        public float getScale() {
            ButtonBounce r1 = this.buttonBounce;
            if (r1 != null) goto L7;
            return 1.0f;
        L7:
            return r1.getScale(0.08f);
        }

        public boolean isAvatarClickable(long r1, TLRPC.Chat r3, TLRPC.User r4) {
            return false;
        }

        public boolean onAvatarClick(View r1, long r2) {
            return false;
        }

        public void onDetachFromWindow() {
            reset();
        }

        public void onLongPress() {
        }

        public void openStory(long r3, Runnable r5) {
            BaseFragment r0 = LaunchActivity.getLastFragment();
            if (r0 != null) goto L5;
            return;
        L5:
            if (this.child == null) goto L13;
            r0.getOrCreateStoryViewer().doOnAnimationReady(r5);
            ViewParent r2 = this.child.getParent();
            if ((r2 instanceof RecyclerView) == false) goto L9;
            StoriesListPlaceProvider r22 = StoriesListPlaceProvider.of((RecyclerListView) r2);
        L10:
            r0.getOrCreateStoryViewer().open(r0.getContext(), r3, r22);
            return;
        L9:
            r22 = null;
            goto L10
        }

        public void reset() {
            UserStoriesLoadOperation r0 = this.operation;
            if (r0 == null) goto L5;
            r0.cancel();
            this.operation = null;
        L5:
            this.buttonBounce = null;
            this.pressed = false;
        }
    }

    /* loaded from: classes7.dex */
    public static class EnsureStoryFileLoadedObject {
        private boolean cancelled;
        long dialogId;
        ImageReceiver imageReceiver;
        public Runnable runnable;
        StoriesController storiesController;

        /* renamed from: -$$Nest$fgetcancelled, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19733$$Nest$fgetcancelled(EnsureStoryFileLoadedObject r0) {
            return r0.cancelled;
        }

        private EnsureStoryFileLoadedObject(StoriesController r2, long r3) {
            this.cancelled = false;
            this.dialogId = r3;
            this.storiesController = r2;
        }

        public /* synthetic */ EnsureStoryFileLoadedObject(StoriesController r1, long r2, StoriesUtilitiesIA r4) {
            this(r1, r2);
        }

        public void cancel() {
            this.cancelled = true;
            this.storiesController.setLoading(this.dialogId, false);
        }
    }

    /* loaded from: classes7.dex */
    public static class StoryGradientTools {
        private final AnimatedColor animatedColor1;
        private final AnimatedColor animatedColor2;
        private int color1;
        private int color2;
        public final int currentAccount;
        private final Runnable invalidate;
        private final boolean isDialogCell;
        private final GradientTools tools;

        public StoryGradientTools(View r2, boolean r3) {
            Objects.requireNonNull(r2);
            this(new Shaker$$ExternalSyntheticLambda0(r2), r3);
        }

        public StoryGradientTools(Runnable r4, boolean r5) {
            this.currentAccount = UserConfig.selectedAccount;
            this.invalidate = r4;
            this.isDialogCell = r5;
            CubicBezierInterpolator r0 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.animatedColor1 = new AnimatedColor(r4, 350, r0);
            this.animatedColor2 = new AnimatedColor(r4, 350, r0);
            GradientTools r42 = new GradientTools();
            this.tools = r42;
            r42.isDiagonal = true;
            r42.isRotate = true;
            resetColors(false);
            r42.paint.setStrokeWidth(AndroidUtilities.dpf2(2.0f));
            r42.paint.setStyle(Paint.Style.STROKE);
            r42.paint.setStrokeCap(Paint.Cap.ROUND);
        }

        private void resetColors(boolean r3) {
            if (this.isDialogCell == false) goto L6;
            setColors(Theme.getColor(Theme.key_stories_circle_dialog1), Theme.getColor(Theme.key_stories_circle_dialog2), r3);
            return;
        L6:
            setColors(Theme.getColor(Theme.key_stories_circle1), Theme.getColor(Theme.key_stories_circle2), r3);
        }

        private void setColors(int r2, int r3, boolean r4) {
            this.color1 = r2;
            this.color2 = r3;
            if (r4 == true) goto L5;
            this.animatedColor1.set(r2, true);
            this.animatedColor2.set(r3, true);
        L5:
            Runnable r1 = this.invalidate;
            if (r1 == null) goto L9;
            r1.run();
            return;
        }

        public Paint getPaint(RectF r5) {
            this.tools.setColors(this.animatedColor1.set(this.color1), this.animatedColor2.set(this.color2));
            this.tools.setBounds(r5.left, r5.top, r5.right, r5.bottom);
            return this.tools.paint;
        }

        public void setChat(TLRPC.Chat r1, boolean r2) {
            if (r1 == null) goto L6;
            TLRPC.PeerColor r12 = r1.profile_color;
            if (r12 == null) goto L6;
            int r13 = r12.color;
        L7:
            setColorId(r13, r2);
            return;
        L6:
            r13 = -1;
            goto L7
        }

        public void setColor(MessagesController.PeerColor r3, boolean r4) {
            if (r3 == null) goto L5;
            setColors(r3.getStoryColor1(Theme.isCurrentThemeDark()), r3.getStoryColor2(Theme.isCurrentThemeDark()), r4);
            return;
        L5:
            resetColors(r4);
        }

        public void setColorId(int r2, boolean r3) {
            MessagesController.PeerColors r0 = MessagesController.getInstance(this.currentAccount).profilePeerColors;
            if (r0 != null) goto L5;
            MessagesController.PeerColor r22 = null;
        L6:
            setColor(r22, r3);
            return;
        L5:
            r22 = r0.getColor(r2);
            goto L6
        }

        public void setUser(TLRPC.User r1, boolean r2) {
            if (r1 == null) goto L6;
            TLRPC.PeerColor r12 = r1.profile_color;
            if (r12 == null) goto L6;
            int r13 = r12.color;
        L7:
            setColorId(r13, r2);
            return;
        L6:
            r13 = -1;
            goto L7
        }
    }

    /* loaded from: classes7.dex */
    public static class UserStoriesLoadOperation {
        boolean canceled;
        private int currentAccount;
        long dialogId;
        int guid;
        AvatarStoryParams params;
        int reqId;
        View view;

        /* renamed from: $r8$lambda$YBOwZ9NQtDixOdGcEqhgZcQU-gk, reason: not valid java name */
        public static /* synthetic */ void m19734$r8$lambda$YBOwZ9NQtDixOdGcEqhgZcQUgk(UserStoriesLoadOperation r0, TLObject r1, long r2, View r4, AvatarStoryParams r5, MessagesController r6) {
            r0.lambda$load$2(r1, r2, r4, r5, r6);
        }

        public static /* synthetic */ void $r8$lambda$cmKn7pA0N6UaY9HYn27pHAg7VHE(UserStoriesLoadOperation r0, View r1, long r2, AvatarStoryParams r4) {
            r0.lambda$load$1(r1, r2, r4);
        }

        /* renamed from: $r8$lambda$fR4VaUSPUq4sDd-6i7Awdws0rvE, reason: not valid java name */
        public static /* synthetic */ void m19735$r8$lambda$fR4VaUSPUq4sDd6i7Awdws0rvE(UserStoriesLoadOperation r0, View r1, long r2) {
            r0.lambda$load$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$yvxfYkylhwAW7_LLM5yj5Oc9gjo(UserStoriesLoadOperation r0, long r1, View r3, AvatarStoryParams r4, MessagesController r5, TLObject r6, TLRPC.TL_error r7) {
            r0.lambda$load$3(r1, r3, r4, r5, r6, r7);
        }

        public UserStoriesLoadOperation() {
            this.guid = ConnectionsManager.generateClassGuid();
        }

        private /* synthetic */ void lambda$load$0(View r1, long r2) {
            r1.invalidate();
            MessagesController.getInstance(this.currentAccount).getStoriesController().setLoading(r2, false);
        }

        private /* synthetic */ void lambda$load$1(View r2, long r3, AvatarStoryParams r5) {
            AndroidUtilities.runOnUIThread(new StoriesUtilities$UserStoriesLoadOperation$$ExternalSyntheticLambda3(this, r2, r3), 500);
            r5.openStory(r3, null);
        }

        private /* synthetic */ void lambda$load$2(TLObject r10, long r11, View r13, AvatarStoryParams r14, MessagesController r15) {
            if (r10 == null) goto L7;
            TL_stories.TL_stories_peerStories r102 = (TL_stories.TL_stories_peerStories) r10;
            MessagesController.getInstance(this.currentAccount).putUsers(r102.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r102.chats, false);
            TL_stories.PeerStories r103 = r102.stories;
            if (r103.stories.isEmpty() == true) goto L7;
            MessagesController.getInstance(this.currentAccount).getStoriesController().putStories(r11, r103);
            UserStoriesLoadOperation r4 = this;
            long r6 = r11;
            View r5 = r13;
            StoriesUtilities.ensureStoryFileLoaded(r103, new StoriesUtilities$UserStoriesLoadOperation$$ExternalSyntheticLambda2(r4, r5, r6, r14));
            boolean r9 = false;
        L9:
            if (r6 <= 0) goto L13;
            TLRPC.User r12 = r15.getUser(Long.valueOf(r6));
            if (r12 == null) goto L13;
            r12.stories_unavailable = true;
            MessagesStorage.getInstance(r4.currentAccount).putUsersAndChats(Collections.singletonList(r12), null, false, true);
            r15.putUser(r12, false);
        L13:
            if (r6 >= 0) goto L17;
            TLRPC.Chat r104 = r15.getChat(Long.valueOf(-r6));
            if (r104 == null) goto L17;
            r104.stories_unavailable = true;
            MessagesStorage.getInstance(r4.currentAccount).putUsersAndChats(null, Collections.singletonList(r104), false, true);
            r15.putChat(r104, false);
        L17:
            if (r9 == false) goto L20;
            r5.invalidate();
            MessagesController.getInstance(r4.currentAccount).getStoriesController().setLoading(r6, false);
            return;
        L20:
            return;
        L7:
            r4 = this;
            r6 = r11;
            r5 = r13;
            r9 = true;
            goto L9
        }

        private /* synthetic */ void lambda$load$3(long r3, View r5, AvatarStoryParams r6, MessagesController r7, TLObject r8, TLRPC.TL_error r9) {
            AndroidUtilities.runOnUIThread(new StoriesUtilities$UserStoriesLoadOperation$$ExternalSyntheticLambda1(this, r8, r3, r5, r6, r7));
        }

        public void cancel() {
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, false);
            this.canceled = true;
            this.params = null;
        }

        public void load(long r10, View r12, AvatarStoryParams r13) {
            int r0 = UserConfig.selectedAccount;
            this.currentAccount = r0;
            this.dialogId = r10;
            this.params = r13;
            this.view = r12;
            MessagesController r7 = MessagesController.getInstance(r0);
            r7.getStoriesController().setLoading(r10, true);
            r12.invalidate();
            TL_stories.TL_stories_getPeerStories r02 = new TL_stories.TL_stories_getPeerStories();
            r02.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r10);
            this.reqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new StoriesUtilities$UserStoriesLoadOperation$$ExternalSyntheticLambda0(this, r10, r12, r13, r7));
        }
    }

    /* renamed from: $r8$lambda$kfHW3-kZyQMzq2sFs9GFKip6YvU, reason: not valid java name */
    public static /* synthetic */ void m19728$r8$lambda$kfHW3kZyQMzq2sFs9GFKip6YvU(Runnable[] r2, EnsureStoryFileLoadedObject r3) {
        r2[0] = null;
        r3.runnable.run();
        ImageReceiver r22 = r3.imageReceiver;
        if (r22 == null) goto L6;
        r22.onDetachedFromWindow();
        return;
    }

    /* renamed from: $r8$lambda$vj-5KJCnxfshabut8bbOCF844fQ, reason: not valid java name */
    public static /* synthetic */ void m19729$r8$lambda$vj5KJCnxfshabut8bbOCF844fQ(EnsureStoryFileLoadedObject r0, Runnable r1) {
        if (EnsureStoryFileLoadedObject.m19733$$Nest$fgetcancelled(r0) == false) goto L5;
        return;
    L5:
        r1.run();
    }

    static {
        storiesGradientTools = new GradientTools[2];
        storyCellGreyPaint = new Paint[2];
        storyCellGrayLastColor = new int[2];
        rectTmp = new RectF();
        scheduled = false;
        debugState = 0;
        debugRunnable = new AnonymousClass1();
        forumRoundRectPath = new Path();
        forumRoundRectPathMeasure = new PathMeasure();
        forumSegmentPath = new Path();
        forumWrappedSegmentPath = new Path();
    }

    private static void applyAvatarRadius(ImageReceiver r1, AvatarStoryParams r2, float r3) {
        float r22 = r2.avatarRadius;
        if (r22 < 0.0f) goto L13;
        int r23 = Math.max(0, Math.round(r22 - r3));
        int[] r0 = r1.getRoundRadius();
        if (r0[0] == r23) goto L8;
    L11:
        r1.setRoundRadius(r23);
        return;
    L8:
        if (r0[3] != r23) goto L11;
        return;
    }

    public static void applyUploadingStr(SimpleTextView r4, boolean r5, boolean r6) {
        if (r6 == false) goto L4;
        String r62 = LocaleController.getString(R.string.StoryEditing);
    L6:
        if (r62.indexOf("…") <= 0) goto L9;
        SpannableStringBuilder r63 = SpannableStringBuilder.valueOf(r62);
        UploadingDotsSpannable r0 = new UploadingDotsSpannable();
        r63.setSpan(r0, r63.length() - 1, r63.length(), 0);
        r0.setParent(r4, r5);
        r4.setText(r63);
        return;
    L9:
        r4.setText(r62);
        return;
    L4:
        r62 = LocaleController.getString(R.string.UploadingStory);
        goto L6
    }

    public static void applyViewedUser(TL_stories.StoryItem r4, TLRPC.User r5) {
        if (r5 != null) goto L5;
        return;
    L5:
        if (r4.dialogId == UserConfig.getInstance(UserConfig.selectedAccount).clientUserId) goto L7;
        return;
    L7:
        if (hasExpiredViews(r4) == false) goto L9;
        return;
    L9:
        if (r4.views != null) goto L11;
        r4.views = new TL_stories.TL_storyViews();
    L11:
        TL_stories.StoryViews r42 = r4.views;
        if (r42.views_count != 0) goto L17;
        r42.views_count = 1;
        r42.recent_viewers.add(Long.valueOf(r5.id));
        return;
    }

    private static void checkGrayPaint(Theme.ResourcesProvider r3) {
        if (grayPaint != null) goto L5;
        Paint r0 = new Paint(1);
        grayPaint = r0;
        r0.setStyle(Paint.Style.STROKE);
        grayPaint.setStrokeWidth(AndroidUtilities.dpf2(1.3f));
        grayPaint.setStrokeCap(Paint.Cap.ROUND);
    L5:
        int r32 = Theme.getColor(Theme.key_windowBackgroundWhite, r3);
        if (grayLastColor == r32) goto L17;
        grayLastColor = r32;
        float r02 = AndroidUtilities.computePerceivedBrightness(r32);
        if (r02 < 0.721f) goto L10;
        grayPaint.setColor(ColorUtils.blendARGB(r32, -16777216, 0.2f));
        return;
    L10:
        if (r02 >= 0.25f) goto L13;
        grayPaint.setColor(ColorUtils.blendARGB(r32, -1, 0.2f));
        return;
    L13:
        grayPaint.setColor(ColorUtils.blendARGB(r32, -1, 0.44f));
        return;
    }

    private static void checkStoriesGradientTools(boolean r3) {
        GradientTools[] r0 = storiesGradientTools;
        if (r0[r3 ? 1 : 0] != null) goto L10;
        GradientTools r1 = new GradientTools();
        r0[r3 ? 1 : 0] = r1;
        GradientTools r02 = storiesGradientTools[r3 ? 1 : 0];
        r02.isDiagonal = true;
        r02.isRotate = true;
        if (r3 == false) goto L7;
        r02.setColors(Theme.getColor(Theme.key_stories_circle_dialog1), Theme.getColor(Theme.key_stories_circle_dialog2));
    L8:
        storiesGradientTools[r3 ? 1 : 0].paint.setStrokeWidth(AndroidUtilities.dpf2(2.0f));
        storiesGradientTools[r3 ? 1 : 0].paint.setStyle(Paint.Style.STROKE);
        storiesGradientTools[r3 ? 1 : 0].paint.setStrokeCap(Paint.Cap.ROUND);
        return;
    L7:
        r02.setColors(Theme.getColor(Theme.key_stories_circle1), Theme.getColor(Theme.key_stories_circle2));
        goto L8
    }

    private static void checkStoryCellGrayPaint(boolean r3, Theme.ResourcesProvider r4) {
        Paint[] r0 = storyCellGreyPaint;
        if (r0[r3 ? 1 : 0] != null) goto L5;
        Paint r1 = new Paint(1);
        r0[r3 ? 1 : 0] = r1;
        storyCellGreyPaint[r3 ? 1 : 0].setStyle(Paint.Style.STROKE);
        storyCellGreyPaint[r3 ? 1 : 0].setStrokeWidth(AndroidUtilities.dpf2(1.3f));
        storyCellGreyPaint[r3 ? 1 : 0].setStrokeCap(Paint.Cap.ROUND);
    L5:
        if (r3 == true) goto L7;
        int r02 = Theme.key_actionBarDefault;
    L8:
        int r42 = Theme.getColor(r02, r4);
        int[] r03 = storyCellGrayLastColor;
        if (r03[r3 ? 1 : 0] == r42) goto L20;
        r03[r3 ? 1 : 0] = r42;
        float r04 = AndroidUtilities.computePerceivedBrightness(r42);
        if (r04 < 0.721f) goto L13;
        storyCellGreyPaint[r3 ? 1 : 0].setColor(ColorUtils.blendARGB(r42, -16777216, 0.2f));
        return;
    L13:
        if (r04 >= 0.25f) goto L16;
        storyCellGreyPaint[r3 ? 1 : 0].setColor(ColorUtils.blendARGB(r42, -1, 0.2f));
        return;
    L16:
        storyCellGreyPaint[r3 ? 1 : 0].setColor(ColorUtils.blendARGB(r42, -1, 0.44f));
        return;
    L20:
        return;
    L7:
        r02 = Theme.key_actionBarDefaultArchived;
        goto L8
    }

    public static CharSequence createExpiredStoryString() {
        return createExpiredStoryString(false, R.string.ExpiredStory, new Object[0]);
    }

    public static CharSequence createExpiredStoryString(boolean r2, int r3, Object... r4) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        r0.append("d ").append(LocaleController.formatString(r3, r4));
        ColoredImageSpan r32 = new ColoredImageSpan(R.drawable.msg_mini_bomb);
        if (r2 == false) goto L5;
        r32.setScale(0.8f, 0.8f);
    L6:
        r0.setSpan(r32, 0, 1, 0);
        return r0;
    L5:
        r32.setTopOffset(-1);
        goto L6
    }

    public static CharSequence createReplyStoryString() {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        r0.append("d ").append(LocaleController.getString(R.string.Story));
        r0.setSpan(new ColoredImageSpan(R.drawable.msg_mini_replystory2), 0, 1, 0);
        return r0;
    }

    public static void drawArcExcludeArc(Canvas r15, RectF r16, Paint r17, float r18, float r19, float r20, float r21) {
        float r8 = r19 - r18;
        if (r18 < r20) goto L5;
    L7:
        float r2 = r18;
        boolean r0 = false;
    L8:
        float r11 = Math.max(r2, r21);
        float r1 = Math.min(r19, r20 + 360.0f);
        if (r1 >= r11) goto L17;
        if (r0 == false) goto L12;
        return;
    L12:
        if (r2 > r20) goto L14;
    L15:
        r15.drawArc(r16, r2, r8, false, r17);
        return;
    L14:
        if (r19 >= r21) goto L15;
        return;
    L17:
        r15.drawArc(r16, r11, r1 - r11, false, r17);
        return;
    L5:
        if (r19 >= (r20 + r8)) goto L7;
        r2 = r18;
        r15.drawArc(r16, r2, Math.min(r19, r20) - r18, false, r17);
        r0 = true;
        goto L8
    }

    public static void drawAvatarWithStory(long r7, Canvas r9, ImageReceiver r10, AvatarStoryParams r11) {
        boolean r0 = MessagesController.getInstance(UserConfig.selectedAccount).getStoriesController().hasStories(r7);
        if (UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId() == r7) goto L7;
        if (r0 == false) goto L7;
        boolean r02 = true;
    L8:
        drawAvatarWithStory(r7, r9, r10, r02, r11);
        return;
    L7:
        r02 = false;
        goto L8
    }

    public static void drawAvatarWithStory(long r25, Canvas r27, ImageReceiver r28, boolean r29, AvatarStoryParams r30) {
        if (ExteraConfig.getHideStories() == false) goto L6;
        applyAvatarRadius(r28, r30, 0.0f);
        r28.setImageCoords(r30.originalAvatarRect);
        r28.draw(r27);
        return;
    L6:
        StoriesController r10 = MessagesController.getInstance(UserConfig.selectedAccount).getStoriesController();
        boolean r3 = r30.animate;
        if (AvatarStoryParams.m19730$$Nest$fgetdialogId(r30) == r25) goto L9;
        AvatarStoryParams.m19731$$Nest$fputdialogId(r30, r25);
        r30.reset();
        r3 = false;
    L9:
        boolean r4 = r10.isLoading(r25);
        if (ChatObject.isForum(UserConfig.selectedAccount, r25) == true) goto L12;
    L14:
        boolean r13 = false;
    L16:
        if (r30.drawHiddenStoriesAsSegments == false) goto L18;
        boolean r5 = r10.hasHiddenStories();
    L20:
        if (r30.storyItem == null) goto L23;
        r10.getUnreadState(r25, r30.storyId);
        r4 = false;
    L23:
        if (r4 == true) goto L25;
        if (r5 == true) goto L30;
        int r0 = getPredictiveUnreadState(r10, r25);
        int r1 = r0;
    L38:
        int r52 = r30.forceState;
        if (r52 == 0) goto L41;
        r0 = r52;
        r1 = r0;
    L41:
        int r53 = r30.currentState;
        if (r53 == r1) goto L50;
        if (r53 != 3) goto L45;
        r3 = true;
    L45:
        if (r1 != 3) goto L47;
        r30.animateFromUnreadState = r0;
        r30.progressToProgressSegments = 0.0f;
    L47:
        if (r3 == false) goto L49;
        r30.prevState = r53;
        r30.prevUnreadState = r30.unreadState;
        r30.currentState = r1;
        r30.progressToSate = 0.0f;
        goto L50
    L49:
        r30.currentState = r1;
        r30.progressToSate = 1.0f;
    L50:
        r30.unreadState = r0;
        ButtonBounce r12 = r30.buttonBounce;
        if (r12 == null) goto L53;
        float r14 = r12.getScale(0.08f);
    L55:
        if (r30.showProgress == r4) goto L58;
        if (r4 == false) goto L58;
        r30.sweepAngle = 1.0f;
        r30.inc = false;
    L58:
        r30.showProgress = r4;
        if (r30.currentState == 0) goto L61;
    L64:
        int r42 = r27.save();
        if (r14 == 1.0f) goto L68;
        r27.scale(r14, r14, r30.originalAvatarRect.centerX(), r30.originalAvatarRect.centerY());
    L68:
        if (r10.hasLiveStory(AvatarStoryParams.m19730$$Nest$fgetdialogId(r30)) == false) goto L70;
        float r142 = r30.progressToSegments;
    L71:
        float r15 = r30.progressToSate;
        if (r15 == 1.0f) goto L74;
        r15 = CubicBezierInterpolator.DEFAULT.getInterpolation(r15);
    L74:
        float r152 = r15;
        if (r30.isStoryCell == true) goto L77;
    L79:
        float r16 = AndroidUtilities.lerp(getInset(r30.prevState, r30.animateFromUnreadState), getInset(r30.currentState, r30.animateFromUnreadState), r30.progressToSate);
    L80:
        applyAvatarRadius(r28, r30, r16);
        RectF r54 = r30.originalAvatarRect;
        if (r16 != 0.0f) goto L83;
        r28.setImageCoords(r54);
    L85:
        if (r142 <= 0.0f) goto L87;
        RectF r32 = rectTmp;
        int r11 = r42;
        float r17 = r16;
        float r18 = 0.08f;
        int r9 = r0;
        Canvas r02 = r27;
        r02.saveLayerAlpha(r32.left - AndroidUtilities.dp(15.0f), r32.top - AndroidUtilities.dp(15.0f), r32.right + AndroidUtilities.dp(15.0f), r32.bottom + AndroidUtilities.dp(15.0f), 255, 31);
    L89:
        if (r30.prevState != 1) goto L94;
        float r19 = 1.0f;
        if (r30.progressToSate == 1.0f) goto L96;
    L98:
        if (r9 != 2) goto L101;
        getCloseFriendsPaint(r28);
        GradientTools r110 = closeFriendsGradientTools;
    L105:
        if (r30.prevState == 1) goto L107;
    L109:
        boolean r2 = false;
    L111:
        if (r30.isStoryCell == true) goto L113;
    L115:
        float r33 = 0.0f;
    L116:
        if (r2 == false) goto L118;
        float r34 = r33 + (AndroidUtilities.dp(5.0f) * r152);
        r110.paint.setAlpha((int) ((r30.alpha * 255.0f) * (r19 - r152)));
    L119:
        float r35 = r34 + r30.additionalInset;
        RectF r22 = rectTmp;
        r22.set(r30.originalAvatarRect);
        r22.inset(r35, r35);
        drawCircleInternal(r02, r28.getParentView(), r30, r110.paint, r13);
    L120:
        int r111 = r30.prevState;
        if (r111 != 2) goto L125;
        if (r30.progressToSate == r19) goto L125;
    L126:
        if (r111 == 2) goto L128;
    L130:
        boolean r112 = false;
    L132:
        if (r30.isStoryCell == false) goto L135;
        checkStoryCellGrayPaint(r30.isArchive, r30.resourcesProvider);
        Paint r23 = storyCellGreyPaint[r30.isArchive ? 1 : 0];
    L134:
        Paint r43 = r23;
        if (r30.drawSegments == false) goto L139;
        Paint r24 = getUnreadCirclePaint(r28, r30.isStoryCell);
        r24.setAlpha((int) (r30.alpha * 255.0f));
        Paint r36 = getCloseFriendsPaint(r28);
        r36.setAlpha((int) (r30.alpha * 255.0f));
        Paint r55 = getLivePaint(r28);
        r55.setAlpha((int) (r30.alpha * 255.0f));
        checkGrayPaint(r30.resourcesProvider);
        Paint r6 = r55;
        Paint r56 = r24;
    L140:
        boolean r26 = r30.drawSegments;
        float r252 = 2.7f;
        boolean r92 = r30.isStoryCell;
        if (r26 == false) goto L148;
        if (r92 == true) goto L144;
    L147:
        float r210 = 0.0f;
    L152:
        if (r112 == false) goto L154;
        float r211 = r210 + (AndroidUtilities.dp(5.0f) * r152);
        r43.setAlpha((int) ((r30.alpha * 255.0f) * (r19 - r152)));
    L155:
        float r212 = r211 + r30.additionalInset;
        RectF r113 = rectTmp;
        r113.set(r30.originalAvatarRect);
        r113.inset(r212, r212);
        if (r30.drawSegments == false) goto L158;
        ImageReceiver r213 = r28;
        StoriesController r114 = r10;
        float r93 = r19;
        Paint r7 = r36;
        AvatarStoryParams r37 = r30;
        boolean r8 = r13;
        drawSegmentsInternal(r02, r114, r213, r37, r43, r56, r6, r7, r8);
    L161:
        if (r37.prevState != 3) goto L165;
        if (r37.progressToSate == r93) goto L165;
    L166:
        int r44 = r37.animateFromUnreadState;
        boolean r57 = r37.isStoryCell;
        if (r44 != 1) goto L169;
        getUnreadCirclePaint(r213, r57);
        Paint r45 = storiesGradientTools[r37.isStoryCell ? 1 : 0].paint;
    L172:
        r45.setAlpha((int) (r152 * 255.0f));
        if (r37.drawSegments == false) goto L175;
        Paint r58 = getUnreadCirclePaint(r213, r37.isStoryCell);
        r58.setAlpha((int) (r37.alpha * 255.0f));
        Paint r62 = getCloseFriendsPaint(r213);
        r62.setAlpha((int) (r37.alpha * 255.0f));
        Paint r72 = getLivePaint(r213);
        r72.setAlpha((int) (r37.alpha * 255.0f));
        checkGrayPaint(r37.resourcesProvider);
        Paint r73 = r62;
        Paint r63 = r72;
    L176:
        boolean r102 = r37.drawSegments;
        boolean r132 = r37.isStoryCell;
        if (r102 == false) goto L184;
        if (r132 == true) goto L180;
    L183:
        float r103 = 0.0f;
    L189:
        if (r37.prevState == 3) goto L191;
    L193:
        r45.setAlpha((int) ((r37.alpha * 255.0f) * r152));
        float r104 = r103 + (AndroidUtilities.dp(5.0f) * (r93 - r152));
    L194:
        float r105 = r104 + r37.additionalInset;
        RectF r122 = rectTmp;
        r122.set(r37.originalAvatarRect);
        r122.inset(r105, r105);
        boolean r106 = r37.drawSegments;
        if (r106 == true) goto L197;
    L207:
        if (r106 == false) goto L211;
        int r115 = r114.getUnreadState(AvatarStoryParams.m19730$$Nest$fgetdialogId(r37));
        if (r115 != 2) goto L213;
        r45 = r73;
        goto L211
    L213:
        if (r115 != 3) goto L215;
        r45 = r63;
        goto L211
    L215:
        boolean r64 = true;
        if (r115 != 1) goto L218;
        r45 = r58;
    L218:
        drawProgress(r02, r37, r213.getParentView(), r45);
    L220:
        r213.draw(r02);
        if (r142 > 0.5f) goto L224;
        r64 = false;
    L224:
        r37.drawnLive = r64;
        if (r142 <= 0.0f) goto L227;
        float r116 = r17 + r37.additionalInset;
        RectF r46 = rectTmp;
        r46.set(r37.originalAvatarRect);
        r46.inset(r116, r116);
        drawLive(r02, r46, r142, r213.getVisible(), 0.0f);
    L227:
        float r117 = r37.progressToSate;
        if (r117 == r93) goto L235;
        float r118 = r117 + (AndroidUtilities.screenRefreshTime / 250.0f);
        r37.progressToSate = r118;
        if (r118 <= r93) goto L233;
        r37.progressToSate = r93;
    L233:
        if (r213.getParentView() == null) goto L235;
        r213.invalidate();
        r213.getParentView().invalidate();
    L235:
        if (r11 == 0) goto L238;
        r02.restoreToCount(r11);
        return;
    L238:
        return;
    L211:
        r64 = true;
        goto L218
    L197:
        if (r37.currentState != 3) goto L207;
        float r123 = r37.progressToProgressSegments;
        if (r123 == r93) goto L207;
        float r124 = r123 + r18;
        r37.progressToProgressSegments = r124;
        if (r124 <= r93) goto L203;
        r37.progressToProgressSegments = r93;
    L203:
        float r107 = r37.progressToSegments;
        r37.progressToSegments = r93 - r37.progressToProgressSegments;
        drawSegmentsInternal(r02, r114, r213, r37, r45, r58, r63, r73, r8);
        r37.progressToSegments = r107;
        if (r213.getParentView() == null) goto L206;
        r213.invalidate();
        r213.getParentView().invalidate();
    L206:
        r64 = true;
        goto L220
    L191:
        if (r37.progressToSate == r93) goto L193;
        r104 = r103 + (AndroidUtilities.dp(7.0f) * r152);
        r45.setAlpha((int) ((r37.alpha * 255.0f) * (r93 - r152)));
        goto L194
    L180:
        if (r37.drawInside == true) goto L183;
        float r108 = AndroidUtilities.dpf2(3.5f);
    L182:
        r103 = -r108;
        goto L189
    L184:
        if (r132 == false) goto L183;
        if (r37.drawInside == true) goto L183;
        r108 = AndroidUtilities.dpf2(r252);
        goto L182
    L175:
        r58 = null;
        r63 = null;
        r73 = null;
        goto L176
    L169:
        if (r57 == false) goto L171;
        checkStoryCellGrayPaint(r37.isArchive, r37.resourcesProvider);
        r45 = storyCellGreyPaint[r37.isArchive ? 1 : 0];
        goto L172
    L171:
        checkGrayPaint(r37.resourcesProvider);
        r45 = grayPaint;
    L165:
        if (r37.currentState == 3) goto L166;
        r64 = true;
        goto L220
    L158:
        r213 = r28;
        r37 = r30;
        r114 = r10;
        r8 = r13;
        r93 = r19;
        drawCircleInternal(r02, r213.getParentView(), r37, r43, r8);
        goto L161
    L154:
        r43.setAlpha((int) ((r30.alpha * 255.0f) * r152));
        r211 = r210 + (AndroidUtilities.dp(5.0f) * (r19 - r152));
        goto L155
    L144:
        if (r30.drawInside == true) goto L147;
        float r214 = AndroidUtilities.dpf2(3.5f);
    L146:
        r210 = -r214;
        goto L152
    L148:
        if (r92 == false) goto L147;
        if (r30.drawInside == true) goto L147;
        r214 = AndroidUtilities.dpf2(2.7f);
        goto L146
    L139:
        r36 = null;
        r56 = null;
        r6 = null;
        goto L140
    L135:
        checkGrayPaint(r30.resourcesProvider);
        r23 = grayPaint;
        goto L134
    L128:
        if (r30.progressToSate == r19) goto L130;
        r112 = true;
    L125:
        if (r30.currentState == 2) goto L126;
        r213 = r28;
        r37 = r30;
        r114 = r10;
        r8 = r13;
        r93 = r19;
        r252 = 2.7f;
        goto L161
    L118:
        r110.paint.setAlpha((int) ((r30.alpha * 255.0f) * r152));
        r34 = r33 + (AndroidUtilities.dp(5.0f) * (r19 - r152));
        goto L119
    L113:
        if (r30.drawInside == true) goto L115;
        r33 = -AndroidUtilities.dp(4.0f);
        goto L116
    L107:
        if (r30.progressToSate == r19) goto L109;
        r2 = true;
        goto L111
    L101:
        if (r9 != 3) goto L103;
        getLivePaint(r28);
        r110 = liveGradientTools;
        goto L105
    L103:
        getUnreadCirclePaint(r28, r30.isStoryCell);
        r110 = storiesGradientTools[r30.isStoryCell ? 1 : 0];
    L96:
        if (r30.currentState != 1) goto L120;
    L94:
        r19 = 1.0f;
        goto L96
    L87:
        r9 = r0;
        r17 = r16;
        r02 = r27;
        r11 = r42;
        r18 = 0.08f;
        goto L89
    L83:
        RectF r38 = rectTmp;
        r38.set(r54);
        r38.inset(r16, r16);
        r28.setImageCoords(r38);
        goto L85
    L77:
        if (r30.drawInside == true) goto L79;
        r16 = 0.0f;
        goto L80
    L70:
        r142 = 0.0f;
        goto L71
    L61:
        if (r30.progressToSate != 1.0f) goto L64;
        applyAvatarRadius(r28, r30, 0.0f);
        r28.setImageCoords(r30.originalAvatarRect);
        r27.save();
        r27.scale(r14, r14, r30.originalAvatarRect.centerX(), r30.originalAvatarRect.centerY());
        r28.draw(r27);
        r27.restore();
        return;
    L53:
        r14 = 1.0f;
        goto L55
    L30:
        if (r30.drawSegments == false) goto L33;
        r0 = 2;
    L32:
        r1 = 2;
        goto L38
    L33:
        r0 = r10.getUnreadState(r25, r30.storyId);
        if (r0 == 0) goto L32;
        r1 = 1;
        goto L38
    L25:
        if (r10.hasStories(r25) == false) goto L27;
        r3 = false;
        r1 = 3;
        r0 = 2;
        goto L38
    L27:
        r0 = getPredictiveUnreadState(r10, r25);
        r3 = false;
        r1 = 3;
        goto L38
    L18:
        r5 = r29;
        goto L20
    L12:
        if (r30.isDialogStoriesCell == true) goto L14;
        r13 = true;
        goto L16
    }

    private static void drawCircleInternal(Canvas r8, View r9, AvatarStoryParams r10, Paint r11, boolean r12) {
        if (r12 == false) goto L5;
    L13:
        RectF r6 = rectTmp;
        float r2 = getRingCornerRadius(r6, r10, r12);
        float r1 = r10.progressToArc;
        if (r1 != 0.0f) goto L17;
        r8.drawRoundRect(r6, r2, r2, r11);
        return;
    L17:
        drawRoundRectSegmentExcluding(r8, r6, r2, 0.0f, 360.0f, (-r1) / 2.0f, r1 / 2.0f, r11);
        return;
    L5:
        if (ExteraConfig.getAvatarCorners() < 28.0f) goto L13;
        float r13 = r10.progressToArc;
        if (r13 != 0.0f) goto L11;
        RectF r14 = rectTmp;
        r8.drawCircle(r14.centerX(), r14.centerY(), r14.width() / 2.0f, r11);
        return;
    L11:
        r8.drawArc(rectTmp, (r13 / 2.0f) + 360.0f, 360.0f - r13, false, r11);
    }

    public static void drawLive(Canvas r11, RectF r12, float r13, boolean r14, float r15) {
        if (liveText != null) goto L6;
        liveText = new Text(LocaleController.getString(R.string.LiveStoryBadge), 9.66f, AndroidUtilities.bold());
    L6:
        if (liveCutPaint != null) goto L9;
        Paint r0 = new Paint(1);
        liveCutPaint = r0;
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    L9:
        if (livePaint != null) goto L12;
        livePaint = new Paint(1);
    L12:
        if (liveRect != null) goto L14;
        liveRect = new RectF();
    L14:
        livePaint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_stories_circle_live2), r13));
        float r02 = AndroidUtilities.lerp(AndroidUtilities.dp(4.66f), AndroidUtilities.dp(7.0f), r15);
        float r1 = (liveText.getWidth() + r02) + r02;
        float r152 = AndroidUtilities.lerp(AndroidUtilities.dp(15.0f), AndroidUtilities.dp(18.0f), r15);
        float r3 = AndroidUtilities.dp(2.0f);
        r11.save();
        float r16 = r1 / 2.0f;
        float r7 = 0.8f * r152;
        float r153 = r152 * 0.2f;
        liveRect.set((r12.centerX() - r16) - r3, (r12.bottom - r7) - r3, (r12.centerX() + r16) + r3, (r12.bottom + r153) + r3);
        float r32 = AndroidUtilities.lerp(0.7f, 1.0f, r13);
        r11.scale(r32, r32, liveRect.centerX(), liveRect.centerY());
        AndroidUtilities.scaleRect(liveRect, r13);
        RectF r33 = liveRect;
        r11.drawRoundRect(r33, r33.height() / 2.0f, liveRect.height() / 2.0f, liveCutPaint);
        if (r14 == false) goto L17;
        liveRect.set(r12.centerX() - r16, r12.bottom - r7, r12.centerX() + r16, r12.bottom + r153);
        RectF r122 = liveRect;
        r11.drawRoundRect(r122, r122.height() / 2.0f, liveRect.height() / 2.0f, livePaint);
        Text r2 = liveText;
        RectF r123 = liveRect;
        Canvas r34 = r11;
        r2.draw(r34, r123.left + r02, r123.centerY(), -1, r13);
    L18:
        r34.restore();
        return;
    L17:
        r34 = r11;
        goto L18
    }

    private static void drawProgress(Canvas r11, AvatarStoryParams r12, View r13, Paint r14) {
        AvatarStoryParams.m19732$$Nest$mupdateProgressParams(r12);
        r13.invalidate();
        boolean r132 = r12.inc;
        float r2 = r12.globalAngle;
        if (r132 == false) goto L5;
        r11.drawArc(rectTmp, r2, r12.sweepAngle * 360.0f, false, r14);
        Paint r10 = r14;
        Canvas r5 = r11;
    L6:
        int r112 = 0;
    L8:
        if (r112 >= 16) goto L10;
        float r133 = (r112 * 22.5f) + 10.0f;
        r5.drawArc(rectTmp, r12.globalAngle + r133, ((22.5f + r133) - 10.0f) - r133, false, r10);
        r112 = r112 + 1;
        goto L8
    L10:
        return;
    L5:
        r5 = r11;
        r10 = r14;
        r5.drawArc(rectTmp, r2 + 360.0f, r12.sweepAngle * (-360.0f), false, r10);
        goto L6
    }

    public static void drawRoundRectSegment(Canvas r8, RectF r9, float r10, float r11, float r12, Paint r13) {
        float r122 = (r12 - r11) / 360.0f;
        if (r122 > 0.0f) goto L5;
        return;
    L5:
        float r102 = Math.min(r10, Math.min(r9.width(), r9.height()) / 2.0f);
        if (r122 < 1.0f) goto L9;
        r8.drawRoundRect(r9, r102, r102, r13);
        return;
    L9:
        double r3 = (r11 - ((float) Math.toDegrees(Math.atan2((r9.height() / 2.0f) - r102, (-r9.width()) / 2.0f)))) / 360.0f;
        float r112 = (float) (r3 - Math.floor(r3));
        float r123 = r122 + r112;
        Path r0 = forumRoundRectPath;
        r0.rewind();
        r0.addRoundRect(r9, r102, r102, Path.Direction.CW);
        PathMeasure r92 = forumRoundRectPathMeasure;
        r92.setPath(r0, false);
        float r103 = r92.getLength();
        Path r02 = forumSegmentPath;
        r02.reset();
        r92.getSegment(r112 * r103, Math.min(r123, 1.0f) * r103, r02, true);
        if (r123 <= 1.0f) goto L12;
        Path r113 = forumWrappedSegmentPath;
        r113.rewind();
        r92.getSegment(0.0f, r103 * (r123 - 1.0f), r113, true);
        r02.addPath(r113);
    L12:
        r8.drawPath(r02, r13);
    }

    private static void drawRoundRectSegmentExcluding(Canvas r14, RectF r15, float r16, float r17, float r18, float r19, float r20, Paint r21) {
        float r0 = r15.width() / 2.0f;
        float r2 = r15.height() / 2.0f;
        float r3 = Math.min(r16, Math.min(r0, r2));
        float r4 = (r20 - r19) / 2.0f;
        float r10 = r2 - r3;
        float r11 = (((r0 - r3) * 4.0f) + (4.0f * r10)) + (6.2831855f * r3);
        if (r4 > 0.0f) goto L5;
    L8:
        float r22 = 0.0f;
    L10:
        if (r22 > 0.0f) goto L13;
        drawRoundRectSegment(r14, r15, r16, r17, r18, r21);
        return;
    L13:
        float r02 = ((float) Math.toDegrees(Math.atan2(r10, -r0))) + (((r10 / r11) + 0.5f) * 360.0f);
        if (Math.cos(Math.toRadians((r19 + r20) / 2.0f)) >= 0.0d) goto L17;
        r02 = r02 + 180.0f;
    L17:
        if (r02 >= (r17 - 180.0f)) goto L20;
        r02 = r02 + 360.0f;
    L20:
        if (r02 <= (r18 + 180.0f)) goto L22;
        r02 = r02 - 360.0f;
        goto L20
    L22:
        float r8 = r02 - r22;
        float r7 = r02 + r22;
        if ((r18 - r17) < 360.0f) goto L27;
        drawRoundRectSegment(r14, r15, r16, r7, r8 + 360.0f, r21);
        return;
    L27:
        if (r7 <= r17) goto L38;
        if (r8 >= r18) goto L38;
        if (r8 <= r17) goto L35;
        drawRoundRectSegment(r14, r15, r16, r17, r8, r21);
    L35:
        if (r7 >= r18) goto L43;
        drawRoundRectSegment(r14, r15, r16, r7, r18, r21);
        return;
    L43:
        return;
    L38:
        drawRoundRectSegment(r14, r15, r16, r17, r18, r21);
        return;
    L5:
        if (r11 <= 0.0f) goto L8;
        r22 = Math.min(180.0f, (ringLengthToAngle(r0, r2, r3, r4) * 360.0f) / r11);
        goto L10
    }

    private static void drawSegment(Canvas r12, RectF r13, Paint r14, float r15, float r16, AvatarStoryParams r17, boolean r18) {
        if (r17.useArcProgress == false) goto L22;
        boolean r2 = r17.isFirst;
        if (r2 == true) goto L14;
        if (r17.isLast == true) goto L14;
        float r3 = r17.progressToArc;
        if (r15 >= 90.0f) goto L12;
        float r22 = (-r3) / 2.0f;
        float r32 = r3 / 2.0f;
    L11:
        float r9 = r22;
        float r10 = r32;
    L28:
        if (r18 == false) goto L30;
    L34:
        drawRoundRectSegmentExcluding(r12, r13, getRingCornerRadius(r13, r17, r18), r15, r16, r9, r10, r14);
        return;
    L30:
        if (ExteraConfig.getAvatarCorners() < 28.0f) goto L34;
        drawArcExcludeArc(r12, r13, r14, r15, r16, r9, r10);
        return;
    L12:
        r22 = ((-r3) / 2.0f) + 180.0f;
        r32 = (r3 / 2.0f) + 180.0f;
    L14:
        if (r17.isLast == false) goto L18;
        float r23 = r17.progressToArc;
    L16:
        float r33 = ((-r23) / 2.0f) + 180.0f;
        float r24 = (r23 / 2.0f) + 180.0f;
    L17:
        r10 = r24;
        r9 = r33;
        goto L28
    L18:
        if (r2 == false) goto L20;
        float r25 = r17.progressToArc;
        r33 = (-r25) / 2.0f;
        r24 = r25 / 2.0f;
        goto L17
    L20:
        r9 = 0.0f;
        r10 = 0.0f;
        goto L28
    L22:
        if (r17.isLast == false) goto L25;
        r23 = r17.progressToArc;
        goto L16
    L25:
        if (r15 >= 90.0f) goto L27;
        r22 = r17.rightTopAngleToExclude;
        r32 = r17.rightBottomAngleToExclude;
        goto L11
    L27:
        r22 = -r17.leftTopAngleToExclude;
        r32 = r17.leftBottomAngleToExclude;
        goto L11
    }

    private static void drawSegmentsInternal(Canvas r20, StoriesController r21, ImageReceiver r22, AvatarStoryParams r23, Paint r24, Paint r25, Paint r26, Paint r27, boolean r28) {
        AvatarStoryParams r5 = r23;
        checkGrayPaint(r5.resourcesProvider);
        checkStoryCellGrayPaint(r5.isArchive, r5.resourcesProvider);
        long r1 = r5.crossfadeToDialog;
        if (r1 == 0) goto L5;
        int r12 = r21.getUnreadState(r1);
    L6:
        int r8 = 2;
        if (r12 != 0) goto L9;
        int r2 = 2;
    L10:
        r5.globalState = r2;
        TL_stories.PeerStories r29 = r21.getStories(AvatarStoryParams.m19730$$Nest$fgetdialogId(r5));
        if (r29 != null) goto L13;
        r29 = r21.getStoriesFromFullPeer(AvatarStoryParams.m19730$$Nest$fgetdialogId(r5));
    L13:
        TL_stories.PeerStories r10 = r29;
        if (r5.drawHiddenStoriesAsSegments == false) goto L17;
        int r210 = r21.getHiddenList().size();
    L16:
        int r11 = r210;
        if (r12 != 2) goto L27;
        getCloseFriendsPaint(r22);
        Paint r13 = closeFriendsGradientTools.paint;
    L26:
        Paint r7 = r13;
        if (r11 > 1) goto L51;
        int r0 = r21.hasUnreadStoriesLive(AvatarStoryParams.m19730$$Nest$fgetdialogId(r5));
        if (r0 != 2) goto L41;
        Paint r211 = r26;
    L46:
        RectF r14 = rectTmp;
        drawSegment(r20, r14, r211, -90.0f, 90.0f, r5, r28);
        drawSegment(r20, r14, r211, 90.0f, 270.0f, r23, r28);
        float r02 = r23.progressToSegments;
        if (r02 == 1.0f) goto L96;
        if (r211 == r7) goto L101;
        r7.setAlpha((int) ((1.0f - r02) * 255.0f));
        drawSegment(r20, r14, r7, -90.0f, 90.0f, r23, r28);
        drawSegment(r20, r14, r7, 90.0f, 270.0f, r23, r28);
        r7.setAlpha(255);
        return;
    L101:
        return;
    L96:
        return;
    L41:
        if (r7 != closeFriendsGradientTools.paint) goto L43;
        r211 = r27;
        goto L46
    L43:
        if (r0 != 1) goto L45;
        r211 = r25;
        goto L46
    L45:
        r211 = r24;
        goto L46
    L51:
        Paint r15 = r7;
        float r16 = 360.0f / r11;
        if (r11 <= 20) goto L54;
        int r212 = 3;
    L55:
        float r213 = r212 * r5.progressToSegments;
        if (r213 <= r16) goto L58;
        r213 = 0.0f;
    L58:
        float r17 = r213;
        int r3 = 0;
        if (r5.drawHiddenStoriesAsSegments == false) goto L61;
        int r214 = 0;
    L62:
        if (r3 >= r11) goto L102;
        if (r5.isStoryCell == false) goto L66;
        Paint r4 = storyCellGreyPaint[r5.isArchive ? 1 : 0];
    L68:
        if (r5.drawHiddenStoriesAsSegments == false) goto L77;
        int r6 = r21.getUnreadState(DialogObject.getPeerDialogId(r21.getHiddenList().get((r11 - 1) - r3).peer));
        if (r6 == r8) goto L88;
        if (r6 == 3) goto L85;
        if (r6 == 1) goto L89;
    L90:
        float r62 = (r3 * r16) - 90.0f;
        float r72 = r62 + r16;
        float r63 = r62 + r17;
        float r73 = r72 - r17;
        int r18 = r214;
        RectF r215 = rectTmp;
        Paint r82 = r15;
        int r19 = r3;
        Paint r32 = r4;
        AvatarStoryParams r64 = r5;
        drawSegment(r20, r215, r32, r63, r73, r64, r28);
        if (r64.progressToSegments == 1.0f) goto L94;
        if (r32 == r82) goto L94;
        r82.getStrokeWidth();
        r82.setAlpha((int) ((1.0f - r64.progressToSegments) * 255.0f));
        drawSegment(r20, r215, r82, r63, r73, r64, r28);
        Paint r216 = r82;
        r216.setAlpha(255);
    L95:
        r3 = r19 + 1;
        r5 = r23;
        r15 = r216;
        r214 = r18;
        r8 = 2;
    L94:
        r216 = r82;
    L89:
        r4 = r25;
    L85:
        r4 = r26;
    L88:
        r4 = r27;
        goto L90
    L77:
        if (r3 >= r10.stories.size()) goto L89;
        if (r10.stories.get(r3).justUploaded == true) goto L84;
        if (r10.stories.get(r3).id <= r214) goto L90;
    L84:
        if ((r10.stories.get(r3).media instanceof TLRPC.TL_messageMediaVideoStream) == true) goto L85;
        if (r10.stories.get(r3).close_friends == false) goto L89;
    L66:
        r4 = grayPaint;
        goto L68
    L102:
        return;
    L61:
        r214 = Math.max(r10.max_read_id, r21.dialogIdToMaxReadId.get(AvatarStoryParams.m19730$$Nest$fgetdialogId(r5), 0));
        goto L62
    L54:
        r212 = 5;
        goto L55
    L27:
        if (r12 != 3) goto L29;
        getLivePaint(r22);
        r13 = liveGradientTools.paint;
        goto L26
    L29:
        boolean r217 = r5.isStoryCell;
        if (r12 != 1) goto L32;
        getUnreadCirclePaint(r22, r217);
        r13 = storiesGradientTools[r5.isStoryCell ? 1 : 0].paint;
        goto L26
    L32:
        if (r217 == false) goto L34;
        r13 = storyCellGreyPaint[r5.isArchive ? 1 : 0];
        goto L26
    L34:
        r13 = grayPaint;
        goto L26
    L17:
        if (r10 != null) goto L19;
    L22:
        r210 = 1;
        goto L16
    L19:
        if (r10.stories.size() == 1) goto L22;
        r210 = r10.stories.size();
        goto L16
    L9:
        r2 = 1;
        goto L10
    L5:
        r12 = r21.getUnreadState(AvatarStoryParams.m19730$$Nest$fgetdialogId(r5));
        goto L6
    }

    public static EnsureStoryFileLoadedObject ensureStoryFileLoaded(TL_stories.PeerStories r18, Runnable r19) {
        if (r18 == null) goto L68;
        if (r18.stories.isEmpty() == true) goto L68;
        if (DialogObject.getPeerDialogId(r18.peer) == UserConfig.getInstance(UserConfig.selectedAccount).clientUserId) goto L68;
        StoriesController r2 = MessagesController.getInstance(UserConfig.selectedAccount).storiesController;
        int r3 = r2.dialogIdToMaxReadId.get(DialogObject.getPeerDialogId(r18.peer));
        int r5 = 0;
    L11:
        if (r5 >= r18.stories.size()) goto L16;
        if (r18.stories.get(r5).id > r3) goto L14;
        r5 = r5 + 1;
        goto L11
    L14:
        TL_stories.StoryItem r32 = r18.stories.get(r5);
    L17:
        if (r32 != null) goto L19;
        r32 = r18.stories.get(0);
    L19:
        TL_stories.StoryItem r14 = r32;
        TLRPC.MessageMedia r33 = r14.media;
        if (r33 != null) goto L22;
    L39:
        if (r33 == null) goto L41;
        TLRPC.Photo r34 = r33.photo;
    L42:
        if (r34 == null) goto L66;
        ArrayList<TLRPC.PhotoSize> r35 = r34.sizes;
        if (r35 == null) goto L66;
        File r36 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(FileLoader.getClosestPhotoSizeWithSize(r35, Integer.MAX_VALUE), _UrlKt.FRAGMENT_ENCODE_SET, false);
        if (r36 != null) goto L48;
    L51:
        EnsureStoryFileLoadedObject r37 = new EnsureStoryFileLoadedObject(r2, DialogObject.getPeerDialogId(r18.peer), null);
        r37.runnable = new StoriesUtilities$$ExternalSyntheticLambda0(r37, r19);
        Runnable[] r0 = {r2};
        StoriesUtilities$$ExternalSyntheticLambda1 r22 = new StoriesUtilities$$ExternalSyntheticLambda1(r0, r37);
        AndroidUtilities.runOnUIThread(r22, 3000);
        AnonymousClass2 r23 = new AnonymousClass2(r0, r37);
        r37.imageReceiver = r23;
        r23.setAllowLoadingOnAttachedOnly(true);
        r37.imageReceiver.onAttachedToWindow();
        String r9 = getStoryImageFilter();
        TLRPC.MessageMedia r02 = r14.media;
        if (r02 == null) goto L56;
        TLRPC.Document r24 = r02.document;
        if (r24 == null) goto L56;
        r37.imageReceiver.setImage(ImageLocation.getForDocument(r24), r9 + "_pframe", null, null, null, 0, null, r14, 0);
    L63:
        return r37;
    L56:
        if (r02 == null) goto L58;
        TLRPC.Photo r03 = r02.photo;
    L59:
        if (r03 == null) goto L64;
        ArrayList<TLRPC.PhotoSize> r25 = r03.sizes;
        if (r25 == null) goto L64;
        r37.imageReceiver.setImage(null, null, ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r25, Integer.MAX_VALUE), r03), r9, null, null, null, 0, null, r14, 0);
    L64:
        r37.runnable.run();
        return null;
    L58:
        r03 = null;
        goto L59
    L48:
        if (r36.exists() == false) goto L51;
        r19.run();
        return null;
    L66:
        r19.run();
        return null;
    L41:
        r34 = null;
        goto L42
    L22:
        if (r33.document == null) goto L39;
        File r38 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r14.media.document, _UrlKt.FRAGMENT_ENCODE_SET, false);
        if (r38 != null) goto L26;
    L29:
        File r39 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r14.media.document, _UrlKt.FRAGMENT_ENCODE_SET, true);
        if (r39 == null) goto L51;
        int r6 = r39.getName().lastIndexOf(".");     // Catch: Exception -> L70
        if (r6 <= 0) goto L51;
        File r8 = new File(r39.getParentFile(), r39.getName().substring(0, r6) + ".temp");     // Catch: Exception -> L70
        if (r8.exists() == false) goto L51;
        if (r8.length() <= 0) goto L51;
        r19.run();     // Catch: Exception -> L70
        return null;
    L26:
        if (r38.exists() == false) goto L29;
        r19.run();
        return null;
    L16:
        r32 = null;
    L68:
        r19.run();
        return null;
    }

    public static Paint getCloseFriendsPaint(ImageReceiver r4) {
        if (closeFriendsGradientTools != null) goto L5;
        GradientTools r0 = new GradientTools();
        closeFriendsGradientTools = r0;
        r0.isDiagonal = true;
        r0.isRotate = true;
        r0.setColors(Theme.getColor(Theme.key_stories_circle_closeFriends1), Theme.getColor(Theme.key_stories_circle_closeFriends2));
        closeFriendsGradientTools.paint.setStrokeWidth(AndroidUtilities.dpf2(2.0f));
        closeFriendsGradientTools.paint.setStyle(Paint.Style.STROKE);
        closeFriendsGradientTools.paint.setStrokeCap(Paint.Cap.ROUND);
    L5:
        closeFriendsGradientTools.setBounds(r4.getImageX(), r4.getImageY(), r4.getImageX2(), r4.getImageY2());
        return closeFriendsGradientTools.paint;
    }

    public static Paint getErrorPaint(RectF r4) {
        if (errorGradientTools != null) goto L5;
        GradientTools r0 = new GradientTools();
        errorGradientTools = r0;
        r0.isDiagonal = true;
        r0.isRotate = true;
        int r02 = Theme.getColor(Theme.key_color_orange);
        int r1 = Theme.getColor(Theme.key_text_RedBold);
        int r03 = ColorUtils.blendARGB(r02, r1, 0.25f);
        errorGradientTools.setColors(r03, r1);
        errorGradientTools.paint.setStrokeWidth(AndroidUtilities.dpf2(2.0f));
        errorGradientTools.paint.setStyle(Paint.Style.STROKE);
        errorGradientTools.paint.setStrokeCap(Paint.Cap.ROUND);
    L5:
        errorGradientTools.setBounds(r4.left, r4.top, r4.right, r4.bottom);
        return errorGradientTools.paint;
    }

    public static Paint getErrorPaint(ImageReceiver r4) {
        if (errorGradientTools != null) goto L5;
        GradientTools r0 = new GradientTools();
        errorGradientTools = r0;
        r0.isDiagonal = true;
        r0.isRotate = true;
        int r02 = Theme.getColor(Theme.key_color_orange);
        int r1 = Theme.getColor(Theme.key_text_RedBold);
        int r03 = ColorUtils.blendARGB(r02, r1, 0.25f);
        errorGradientTools.setColors(r03, r1);
        errorGradientTools.paint.setStrokeWidth(AndroidUtilities.dpf2(2.0f));
        errorGradientTools.paint.setStyle(Paint.Style.STROKE);
        errorGradientTools.paint.setStrokeCap(Paint.Cap.ROUND);
    L5:
        errorGradientTools.setBounds(r4.getImageX(), r4.getImageY(), r4.getImageX2(), r4.getImageY2());
        return errorGradientTools.paint;
    }

    public static Drawable getExpiredStoryDrawable() {
        if (expiredStoryDrawable != null) goto L6;
        Bitmap r0 = Bitmap.createBitmap(360, 180, Bitmap.Config.ARGB_8888);
        r0.eraseColor(-7829368);
        Canvas r1 = new Canvas(r0);
        TextPaint r2 = new TextPaint(1);
        r2.setTextSize(15.0f);
        r2.setTextAlign(Paint.Align.CENTER);
        r2.setColor(ColorUtils.setAlphaComponent(-16777216, 100));
        r1.drawText("expired", 180.0f, 86.0f, r2);
        r1.drawText("story", 180.0f, 106.0f, r2);
        expiredStoryDrawable = new BitmapDrawable(r0);
    L6:
        return expiredStoryDrawable;
    }

    public static int getInset(int r1, int r2) {
        if (r1 != 3) goto L6;
        r1 = r2;
    L6:
        if (r1 != 2) goto L10;
        return AndroidUtilities.dp(3.0f);
    L10:
        if (r1 == 1) goto L12;
        return 0;
    L12:
        return AndroidUtilities.dp(4.0f);
    }

    public static Paint getLivePaint(ImageReceiver r4) {
        if (liveGradientTools != null) goto L5;
        GradientTools r0 = new GradientTools();
        liveGradientTools = r0;
        r0.isDiagonal = true;
        r0.isRotate = true;
        r0.setColors(Theme.getColor(Theme.key_stories_circle_live1), Theme.getColor(Theme.key_stories_circle_live2));
        liveGradientTools.paint.setStrokeWidth(AndroidUtilities.dpf2(2.0f));
        liveGradientTools.paint.setStyle(Paint.Style.STROKE);
        liveGradientTools.paint.setStrokeCap(Paint.Cap.ROUND);
    L5:
        liveGradientTools.setBounds(r4.getImageX(), r4.getImageY(), r4.getImageX2(), r4.getImageY2());
        return liveGradientTools.paint;
    }

    public static int getPredictiveUnreadState(StoriesController r7, long r8) {
        if (r8 != 0) goto L6;
        return 0;
    L6:
        if (r8 <= 0) goto L24;
        TLRPC.User r0 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r8));
        if (r8 == UserConfig.getInstance(UserConfig.selectedAccount).clientUserId) goto L23;
        if (r0 == null) goto L23;
        TLRPC.TL_recentStory r5 = r0.stories_max_id;
        if (r5 == null) goto L23;
        if (r5.max_id <= 0) goto L23;
        if (r0.stories_unavailable == true) goto L23;
        int r72 = r7.dialogIdToMaxReadId.get(r8, 0);
        TLRPC.TL_recentStory r82 = r0.stories_max_id;
        if (r82.live == false) goto L20;
        return 3;
    L20:
        if (r82.max_id <= r72) goto L22;
        return 1;
    L22:
        return 2;
    L23:
        return 0;
    L24:
        TLRPC.Chat r02 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r8));
        if (r02 == null) goto L39;
        TLRPC.TL_recentStory r52 = r02.stories_max_id;
        if (r52 == null) goto L39;
        if (r52.max_id <= 0) goto L39;
        if (r02.stories_unavailable == true) goto L39;
        int r73 = r7.dialogIdToMaxReadId.get(r8, 0);
        TLRPC.TL_recentStory r83 = r02.stories_max_id;
        if (r83.live == false) goto L36;
        return 3;
    L36:
        if (r83.max_id <= r73) goto L38;
        return 1;
    L38:
        return 2;
    L39:
        return 0;
    }

    private static float getRingCornerRadius(RectF r4, AvatarStoryParams r5, boolean r6) {
        float r0 = r5.originalAvatarRect.width();
        if (r0 <= 0.0f) goto L5;
        float r52 = r5.avatarRadius;
        if (r52 >= 0.0f) goto L11;
        r52 = ExteraConfig.getAvatarCorners(r0, true, r6);
    L11:
        return Math.max(0.0f, r52 + ((r4.width() - r0) / 2.0f));
    L5:
        return ExteraConfig.getAvatarCorners(r4.width(), true, r6);
    }

    public static String getStoryImageFilter() {
        int r0 = (int) (Math.max(AndroidUtilities.getRealScreenSize().x, AndroidUtilities.getRealScreenSize().y) / AndroidUtilities.density);
        return r0 + "_" + r0;
    }

    public static Paint getUnreadCirclePaint(ImageReceiver r4, boolean r5) {
        checkStoriesGradientTools(r5);
        storiesGradientTools[r5 ? 1 : 0].setBounds(r4.getImageX(), r4.getImageY(), r4.getImageX2(), r4.getImageY2());
        return storiesGradientTools[r5 ? 1 : 0].paint;
    }

    public static CharSequence getUploadingStr(TextView r4, boolean r5, boolean r6) {
        if (r6 == false) goto L4;
        String r62 = LocaleController.getString(R.string.StoryEditing);
    L6:
        if (r62.indexOf("…") <= 0) goto L9;
        SpannableStringBuilder r63 = SpannableStringBuilder.valueOf(r62);
        UploadingDotsSpannable r0 = new UploadingDotsSpannable();
        r63.setSpan(r0, r63.length() - 1, r63.length(), 0);
        r0.setParent(r4, r5);
        return r63;
    L9:
        return r62;
    L4:
        r62 = LocaleController.getString(R.string.UploadingStory);
        goto L6
    }

    public static boolean hasExpiredViews(TL_stories.StoryItem r3) {
        if (r3 != null) goto L6;
        return false;
    L6:
        if (ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime() <= (r3.expire_date + 86400)) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isExpired(int r0, TL_stories.StoryItem r1) {
        if (ConnectionsManager.getInstance(r0).getCurrentTime() <= r1.expire_date) goto L6;
        return true;
    L6:
        return false;
    }

    private static float ringCornerAngle(double r8, double r10, float r12, float r13, float r14) {
        double r0 = r12;
        double r122 = r13;
        double r2 = (r8 * r0) + (r10 * r122);
        double r6 = r14;
        double r22 = r2 + Math.sqrt(Math.max(0.0d, (r2 * r2) - (((r0 * r0) + (r122 * r122)) - (r6 * r6))));
        double r82 = (r8 * r22) - r0;
        double r23 = (r22 * r10) - r122;
        if (r82 != 0.0d) goto L9;
        if (r23 != 0.0d) goto L9;
        return 0.0f;
    L9:
        return (float) Math.atan2(r23, r82);
    }

    private static float ringLengthToAngle(float r20, float r21, float r22, float r23) {
        if (r23 > 0.0f) goto L5;
        return 0.0f;
    L5:
        float r9 = r20 - r22;
        float r5 = r21 - r22;
        float r3 = (3.1415927f * r22) / 2.0f;
        if (r23 >= 180.0f) goto L8;
        double r6 = Math.toRadians(r23);
        double r10 = Math.cos(r6);
        double r7 = Math.sin(r6);
        if (r10 <= 0.0d) goto L15;
        double r14 = r20 * r7;
        double r16 = 0.0d;
        if (r14 <= (r5 * r10)) goto L14;
    L16:
        double r1 = r21 * r10;
        if (r1 > (r9 * r7)) goto L19;
        float r4 = -r9;
        if (r1 < (r4 * r7)) goto L25;
        return (float) (((r5 + r3) + r9) - (r1 / r7));
    L25:
        if (r10 >= r16) goto L31;
        double r102 = r20 * r7;
        if (r102 > ((-r5) * r10)) goto L31;
        return (float) ((((r5 * 2.0f) + (r9 * 2.0f)) + (r3 * 2.0f)) + (r102 / r10));
    L31:
        return ((r5 + r3) + (r9 * 2.0f)) + ((ringCornerAngle(r10, r7, r4, r5, r22) - 1.5707964f) * r22);
    L19:
        return r5 + (ringCornerAngle(r10, r7, r9, r5, r22) * r22);
    L14:
        return (float) (r14 / r10);
    L15:
        r16 = 0.0d;
        goto L16
    L8:
        return ((r5 * 2.0f) + (r9 * 2.0f)) + (r3 * 2.0f);
    }

    public static void setImage(ImageReceiver r1, TL_stories.StoryItem r2) {
        setImage(r1, r2, "320_320");
    }

    public static void setImage(ImageReceiver r13, TL_stories.StoryItem r14, String r15) {
        if (r14 != null) goto L4;
        return;
    L4:
        TLRPC.MessageMedia r0 = r14.media;
        if (r0 == null) goto L10;
        TLRPC.Document r2 = r0.document;
        if (r2 == null) goto L10;
        r13.setImage(ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r2.thumbs, Integer.MAX_VALUE), r14.media.document), r15, null, null, ImageLoader.createStripedBitmap(r14.media.document.thumbs), 0, null, r14, 0);
        r13.addDecorator(new StoryWidgetsImageDecorator(r14));
        return;
    L10:
        if (r0 == null) goto L12;
        TLRPC.Photo r3 = r0.photo;
    L14:
        if ((r0 instanceof TLRPC.TL_messageMediaUnsupported) == false) goto L17;
        Bitmap r02 = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        r02.eraseColor(ColorUtils.blendARGB(-16777216, -1, 0.2f));
        r13.setImageBitmap(r02);
        r13.addDecorator(new StoryWidgetsImageDecorator(r14));
        return;
    L17:
        if (r3 == null) goto L22;
        ArrayList<TLRPC.PhotoSize> r03 = r3.sizes;
        if (r03 == null) goto L22;
        r13.setImage(null, null, ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r03, Integer.MAX_VALUE), r3), r15, null, null, ImageLoader.createStripedBitmap(r3.sizes), 0, null, r14, 0);
        r13.addDecorator(new StoryWidgetsImageDecorator(r14));
        return;
    L22:
        r13.clearImage();
        return;
    L12:
        r3 = null;
        goto L14
    }

    public static void setImage(ImageReceiver r23, StoriesController.UploadingStory r24) {
        if (r24.entry.isVideo == false) goto L6;
        r23.setImage(ImageLocation.getForPath(r24.firstFramePath), "320_180", null, null, null, 0, null, null, 0);
        return;
    L6:
        r23.setImage(ImageLocation.getForPath(r24.path), "320_180", null, null, null, 0, null, null, 0);
    }

    public static void setStoryMiniImage(ImageReceiver r13, TL_stories.StoryItem r14) {
        if (r14 != null) goto L4;
        return;
    L4:
        TLRPC.MessageMedia r0 = r14.media;
        TLRPC.Document r1 = r0.document;
        if (r1 == null) goto L8;
        r13.setImage(ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r1.thumbs, 1000), r14.media.document), "100_100", null, null, ImageLoader.createStripedBitmap(r14.media.document.thumbs), 0, null, r14, 0);
        return;
    L8:
        if (r0 == null) goto L10;
        TLRPC.Photo r02 = r0.photo;
    L11:
        if (r02 == null) goto L16;
        ArrayList<TLRPC.PhotoSize> r12 = r02.sizes;
        if (r12 == null) goto L16;
        r13.setImage(null, null, ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r12, 1000), r02), "100_100", null, null, ImageLoader.createStripedBitmap(r02.sizes), 0, null, r14, 0);
        return;
    L16:
        r13.clearImage();
        return;
    L10:
        r02 = null;
        goto L11
    }

    public static void setThumbImage(AvatarDrawable r13, ImageReceiver r14, TL_stories.StoryItem r15, int r16, int r17) {
        TLRPC.MessageMedia r3 = r15.media;
        if ((r3 instanceof TLRPC.TL_messageMediaVideoStream) == false) goto L7;
        TLObject r1 = MessagesController.getInstance(r14.getCurrentAccount()).getUserOrChat(r15.dialogId);
        r13.setInfo(r1);
        r14.setForUserOrChat(r1, r13);
        return;
    L7:
        if (r3 == null) goto L12;
        TLRPC.Document r8 = r3.document;
        if (r8 == null) goto L12;
        r14.setImage(ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r8.thumbs, AndroidUtilities.dp(Math.max(r16, r17)), false, null, true), r15.media.document), r16 + "_" + r17, null, null, ImageLoader.createStripedBitmap(r15.media.document.thumbs), 0, null, r15, 0);
        return;
    L12:
        if (r3 == null) goto L14;
        TLRPC.Photo r32 = r3.photo;
    L15:
        if (r32 == null) goto L20;
        ArrayList<TLRPC.PhotoSize> r4 = r32.sizes;
        if (r4 == null) goto L20;
        r14.setImage(null, null, ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r4, AndroidUtilities.dp(Math.max(r16, r17)), false, null, true), r32), r16 + "_" + r17, null, null, ImageLoader.createStripedBitmap(r32.sizes), 0, null, r15, 0);
        return;
    L20:
        r14.clearImage();
        return;
    L14:
        r32 = null;
        goto L15
    }

    public static void updateColors() {
        GradientTools r0 = closeFriendsGradientTools;
        if (r0 == null) goto L5;
        r0.setColors(Theme.getColor(Theme.key_stories_circle_closeFriends1), Theme.getColor(Theme.key_stories_circle_closeFriends2));
    L5:
        GradientTools r02 = liveGradientTools;
        if (r02 == null) goto L8;
        r02.setColors(Theme.getColor(Theme.key_stories_circle_live1), Theme.getColor(Theme.key_stories_circle_live2));
    L8:
        GradientTools r03 = storiesGradientTools[0];
        if (r03 == null) goto L11;
        r03.setColors(Theme.getColor(Theme.key_stories_circle_dialog1), Theme.getColor(Theme.key_stories_circle_dialog2));
    L11:
        GradientTools r04 = storiesGradientTools[1];
        if (r04 == null) goto L15;
        r04.setColors(Theme.getColor(Theme.key_stories_circle1), Theme.getColor(Theme.key_stories_circle2));
    L15:
        if (errorGradientTools == null) goto L18;
        int r05 = Theme.getColor(Theme.key_color_orange);
        int r1 = Theme.getColor(Theme.key_text_RedBold);
        int r06 = ColorUtils.blendARGB(r05, r1, 0.25f);
        errorGradientTools.setColors(r06, r1);
        return;
    }
}
