package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Trace;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.icons.ui.picker.IconObserver;
import java.util.ArrayList;
import java.util.Iterator;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.LocationController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.SecretChatHelper;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.utils.LeakDetector;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.ui.ActionBar.ActionBarLayout;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ArticleViewer;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.EmptyBaseFragment;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.StoryViewer;
import org.telegram.utils.glass.GlassEngine;

/* loaded from: classes3.dex */
public abstract class BaseFragment {
    protected ActionBar actionBar;
    protected Bundle arguments;
    private int bottomInset;
    private Bulletin.Delegate bulletinDelegate;
    protected int classGuid;
    protected int currentAccount;
    protected boolean finishing;
    protected boolean fragmentBeginToShow;
    public View fragmentView;
    private Runnable fullyVisibleListener;
    protected final GlassEngine glassEngine;
    protected boolean hasOwnBackground;
    protected boolean inBubbleMode;
    protected boolean inMenuMode;
    protected boolean inPreviewMode;
    protected boolean inTransitionAnimation;
    public boolean isFinished;
    private boolean isFullyVisible;
    protected boolean isPaused;
    protected Dialog parentDialog;
    protected INavigationLayout parentLayout;
    private PreviewDelegate previewDelegate;
    private boolean removingFromStack;
    protected Theme.ResourcesProvider resourceProvider;
    public ArrayList<AttachedSheet> sheetsStack;
    public Dialog visibleDialog;

    /* renamed from: org.telegram.ui.ActionBar.BaseFragment$1, reason: invalid class name */
    public class AnonymousClass1 implements Bulletin.Delegate {
        final /* synthetic */ BaseFragment this$0;

        public AnonymousClass1(BaseFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            if (this.this$0.isSupportEdgeToEdge() == true) goto L5;
            return 0;
        L5:
            return AndroidUtilities.navigationBarHeight;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.BaseFragment$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends BottomSheet {
        final /* synthetic */ BaseFragment this$0;
        final /* synthetic */ INavigationLayout[] val$actionBarLayout;
        final /* synthetic */ BottomSheet[] val$bottomSheet;
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ BottomSheetParams val$params;

        /* renamed from: $r8$lambda$Pf2A33RJlV2-_ds4cYarjA_UrGw, reason: not valid java name */
        public static /* synthetic */ void m5659$r8$lambda$Pf2A33RJlV2_ds4cYarjA_UrGw(BaseFragment r0, BottomSheetParams r1, DialogInterface r2) {
            r0.onPause();
            r0.onFragmentDestroy();
            if (r1 == null) goto L8;
            Runnable r02 = r1.onDismiss;
            if (r02 == null) goto L9;
            r02.run();
            return;
        L9:
            return;
        }

        public AnonymousClass2(BaseFragment r1, Context r2, boolean r3, Theme.ResourcesProvider r4, BottomSheetParams r5, INavigationLayout[] r6, BaseFragment r7, BottomSheet[] r8) {
            this.this$0 = r1;
            this.val$params = r5;
            this.val$actionBarLayout = r6;
            this.val$fragment = r7;
            this.val$bottomSheet = r8;
            super(r2, r3, r4);
            if (r5 != null) goto L5;
        L7:
            boolean r32 = false;
        L8:
            this.occupyNavigationBar = r32;
            this.drawNavigationBar = true ^ r32;
            r6[0].setFragmentStack(new ArrayList());
            r6[0].addFragmentToStack(r7);
            r6[0].showLastFragment();
            ViewGroup r12 = r6[0].getView();
            int r33 = this.backgroundPaddingLeft;
            r12.setPadding(r33, 0, r33, 0);
            this.containerView = r6[0].getView();
            setApplyBottomPadding(false);
            setOnDismissListener(new BaseFragment$2$$ExternalSyntheticLambda0(r7, r5));
            return;
        L5:
            if (r5.occupyNavigationBar == false) goto L7;
            r32 = true;
            goto L8
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public boolean canDismissWithSwipe() {
            return false;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public boolean canSwipeToBack(MotionEvent r4) {
            BottomSheetParams r0 = this.val$params;
            if (r0 != null) goto L5;
        L16:
            return false;
        L5:
            if (r0.transitionFromLeft == false) goto L16;
            INavigationLayout r02 = this.val$actionBarLayout[0];
            if (r02 == null) goto L16;
            if (r02.getFragmentStack().size() > 1) goto L16;
            if (this.val$actionBarLayout[0].getFragmentStack().size() == 1) goto L13;
        L15:
            return true;
        L13:
            if (this.val$actionBarLayout[0].getFragmentStack().get(0).isSwipeBackEnabled(r4) == true) goto L15;
            return false;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            if (isDismissed() == true) goto L9;
            BottomSheetParams r0 = this.val$params;
            if (r0 == null) goto L9;
            Runnable r02 = r0.onPreFinished;
            if (r02 == null) goto L9;
            r02.run();
        L9:
            super.dismiss();
            LaunchActivity.instance.sheetFragmentsStack.remove(this.val$actionBarLayout[0]);
            this.val$actionBarLayout[0] = null;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
        public void onBackPressed() {
            INavigationLayout r0 = this.val$actionBarLayout[0];
            if (r0 != null) goto L5;
        L9:
            super.onBackPressed();
            return;
        L5:
            if (r0.getFragmentStack().size() <= 1) goto L9;
            this.val$actionBarLayout[0].onBackPressed();
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
        public void onCreate(Bundle r3) {
            super.onCreate(r3);
            this.val$actionBarLayout[0].setWindow(this.val$bottomSheet[0].getWindow());
            BottomSheetParams r32 = this.val$params;
            if (r32 != null) goto L5;
        L8:
            fixNavigationBar(Theme.getColor(Theme.key_dialogBackgroundGray, this.val$fragment.getResourceProvider()));
        L9:
            AndroidUtilities.setLightStatusBar(this, this.val$fragment.isLightStatusBar());
            this.val$fragment.onBottomSheetCreated();
            return;
        L5:
            if (r32.occupyNavigationBar == false) goto L8;
            AndroidUtilities.setLightNavigationBar(this.val$bottomSheet[0], true);
            goto L9
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public void onInsetsChanged() {
            INavigationLayout r2 = this.val$actionBarLayout[0];
            if (r2 == null) goto L10;
            Iterator<BaseFragment> r22 = r2.getFragmentStack().iterator();
        L6:
            if (r22.hasNext() == false) goto L16;
            BaseFragment r0 = r22.next();
            if (r0.getFragmentView() == null) goto L6;
            r0.getFragmentView().requestLayout();
            goto L6
        L16:
            return;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public void onOpenAnimationEnd() {
            this.val$fragment.onTransitionAnimationEnd(true, false);
            BottomSheetParams r3 = this.val$params;
            if (r3 == null) goto L8;
            Runnable r32 = r3.onOpenAnimationFinished;
            if (r32 == null) goto L9;
            r32.run();
            return;
        L9:
            return;
        }
    }

    public interface AttachedSheet {
        boolean attachedToParent();

        void dismiss();

        default void dismiss(boolean r1) {
            dismiss();
        }

        default BulletinFactory getBulletinFactory() {
            return null;
        }

        int getNavigationBarColor(int r1);

        View getWindowView();

        boolean isAttachedLightStatusBar();

        boolean isFullyVisible();

        boolean isShown();

        boolean onAttachedBackPressed();

        void setKeyboardHeightFromParent(int r1);

        default void setLastVisible(boolean r1) {
        }

        void setOnDismissListener(Runnable r1);

        boolean showDialog(Dialog r1);
    }

    public interface AttachedSheetWindow {
    }

    public static class BottomSheetParams {
        public boolean allowNestedScroll;
        public boolean occupyNavigationBar;
        public Runnable onDismiss;
        public Runnable onOpenAnimationFinished;
        public Runnable onPreFinished;
        public boolean transitionFromLeft;

        public BottomSheetParams() {
        }
    }

    public interface PreviewDelegate {
        void finishFragment();
    }

    public static /* synthetic */ void $r8$lambda$AzkNGVjYDfNdhtxkt3cv3xjZJgk(BaseFragment r0, DialogInterface.OnDismissListener r1, DialogInterface r2) {
        r0.lambda$showDialog$0(r1, r2);
    }

    public static /* synthetic */ BottomSheet $r8$lambda$aMpXmKmokozlm5AWV_1jB0NtqhE(BottomSheet[] r1) {
        return r1[0];
    }

    public BaseFragment() {
        this(null);
    }

    public BaseFragment(Bundle r3) {
        this.currentAccount = UserConfig.selectedAccount;
        this.hasOwnBackground = false;
        this.isPaused = true;
        this.inTransitionAnimation = false;
        this.glassEngine = new GlassEngine();
        this.arguments = r3;
        this.classGuid = ConnectionsManager.generateClassGuid();
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L5;
        LeakDetector.getInstance().add(this);
    L5:
        Bulletin.addDelegate(this, new AnonymousClass1(this));
    }

    public static boolean hasFullyVisibleSheets(BaseFragment r3) {
        if (r3 != null) goto L6;
        return false;
    L6:
        if (r3.hasShownFullyVisibleSheet() == false) goto L9;
        return true;
    L9:
        if ((r3.getParentLayout() instanceof ActionBarLayout) == true) goto L11;
        return false;
    L11:
        EmptyBaseFragment r32 = ((ActionBarLayout) r3.getParentLayout()).getSheetFragment(false);
        if (r32 != null) goto L14;
    L16:
        return false;
    L14:
        if (r32.hasShownFullyVisibleSheet() == false) goto L16;
        return true;
    }

    public static boolean hasSheets(BaseFragment r3) {
        if (r3 != null) goto L6;
        return false;
    L6:
        if (r3.hasShownSheet() == false) goto L9;
        return true;
    L9:
        if ((r3.getParentLayout() instanceof ActionBarLayout) == true) goto L11;
        return false;
    L11:
        EmptyBaseFragment r32 = ((ActionBarLayout) r3.getParentLayout()).getSheetFragment(false);
        if (r32 != null) goto L14;
    L16:
        return false;
    L14:
        if (r32.hasShownSheet() == false) goto L16;
        return true;
    }

    private /* synthetic */ void lambda$showDialog$0(DialogInterface.OnDismissListener r1, DialogInterface r2) {
        if (r1 == null) goto L4;
        r1.onDismiss(r2);
    L4:
        onDialogDismiss((Dialog) r2);
        if (r2 != this.visibleDialog) goto L8;
        this.visibleDialog = null;
        return;
    }

    private View performCreateViewImpl(Context r1) {
        View r12 = createView(r1);
        onViewCreated(r12);
        return r12;
    }

    public static void removeViewFromParent(ViewGroup r1, View r2) {
        if (r1 == null) goto L13;
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.getParent() == r1) goto L8;
        return;
    L8:
        if (r1.isInLayout() == false) goto L11;
        r1.removeViewInLayout(r2);
        return;
    L11:
        r1.removeView(r2);
        return;
    }

    private void setParentDialog(Dialog r1) {
        this.parentDialog = r1;
    }

    public void addSheet(AttachedSheet r2) {
        if (this.sheetsStack != null) goto L5;
        this.sheetsStack = new ArrayList();
    L5:
        StoryViewer r0 = getLastStoryViewer();
        if (r0 == null) goto L8;
        r0.listenToAttachedSheet(r2);
    L8:
        this.sheetsStack.add(r2);
        updateSheetsVisibility();
    }

    public boolean allowFinishFragmentInsteadOfRemoveFromStack() {
        return true;
    }

    public boolean allowPresentFragment() {
        return true;
    }

    public void attachSheets(ActionBarLayout.LayoutContainer r4) {
        if (this.sheetsStack == null) goto L13;
        int r0 = 0;
    L6:
        if (r0 >= this.sheetsStack.size()) goto L18;
        AttachedSheet r1 = this.sheetsStack.get(r0);
        if (r1 == null) goto L12;
        if (r1.attachedToParent() == false) goto L12;
        AndroidUtilities.removeFromParent(r1.getWindowView());
        r4.addView(r1.getWindowView());
    L12:
        r0 = r0 + 1;
        goto L6
    L18:
        return;
    }

    public boolean canBeginSlide() {
        return true;
    }

    public void checkSystemBarColors() {
        Activity r1 = getParentActivity();
        if ((r1 instanceof LaunchActivity) == false) goto L6;
        ((LaunchActivity) r1).checkSystemBarColors(true, true, true);
        return;
    }

    public void clearSheets() {
        ArrayList<AttachedSheet> r0 = this.sheetsStack;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isEmpty() == true) goto L15;
        int r02 = this.sheetsStack.size() - 1;
    L8:
        ArrayList<AttachedSheet> r2 = this.sheetsStack;
        if (r02 < 0) goto L11;
        r2.get(r02).dismiss(true);
        r02 = r02 - 1;
        goto L8
    L11:
        r2.clear();
        return;
    }

    public void clearViews() {
        View r0 = this.fragmentView;
        if (r0 == null) goto L11;
        ViewGroup r02 = (ViewGroup) r0.getParent();
        if (r02 != null) goto L24;
    L10:
        this.fragmentView = null;
        goto L11
    L24:
        onRemoveFromParent();     // Catch: Exception -> L8
        removeViewFromParent(r02, this.fragmentView);     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
    L11:
        ActionBar r03 = this.actionBar;
        if (r03 == null) goto L20;
        ViewGroup r04 = (ViewGroup) r03.getParent();
        if (r04 != null) goto L22;
    L19:
        this.actionBar = null;
        goto L20
    L22:
        removeViewFromParent(r04, this.actionBar);     // Catch: Exception -> L17
    L17:
        e = move-exception;
        FileLog.e(e);
    L20:
        clearSheets();
        this.parentLayout = null;
    }

    public boolean closeLastFragment() {
        return false;
    }

    public boolean closeSheet() {
        ArrayList<AttachedSheet> r0 = this.sheetsStack;
        if (r0 == null) goto L11;
        int r02 = r0.size() - 1;
    L5:
        if (r02 < 0) goto L15;
        if (this.sheetsStack.get(r02).isShown() == true) goto L9;
        r02 = r02 - 1;
        goto L5
    L9:
        return this.sheetsStack.get(r02).onAttachedBackPressed();
    L15:
        return false;
    L11:
        return false;
    }

    public ActionBar createActionBar(Context r4) {
        ActionBar r0 = new ActionBar(r4, getResourceProvider());
        r0.setBackgroundColor(getThemedColor(Theme.key_actionBarDefault));
        r0.setItemsBackgroundColor(getThemedColor(Theme.key_actionBarDefaultSelector), false);
        r0.setItemsBackgroundColor(getThemedColor(Theme.key_actionBarActionModeDefaultSelector), true);
        r0.setItemsColor(getThemedColor(Theme.key_actionBarDefaultIcon), false);
        r0.setItemsColor(getThemedColor(Theme.key_actionBarActionModeDefaultIcon), true);
        if (this.inPreviewMode == false) goto L5;
    L12:
        r0.setOccupyStatusBar(false);
        return r0;
    L5:
        if (this.inBubbleMode == true) goto L12;
        INavigationLayout r3 = this.parentLayout;
        if (r3 != null) goto L9;
    L11:
        return r0;
    L9:
        if (r3.isLayersLayout() == false) goto L11;
        goto L11
    }

    public ArticleViewer createArticleViewer(boolean r2) {
        if (this.sheetsStack != null) goto L5;
        this.sheetsStack = new ArrayList();
    L5:
        if (r2 == false) goto L7;
    L22:
        ArticleViewer r22 = ArticleViewer.makeSheet(this);
        addSheet(r22.sheet);
        BottomSheetTabDialog.checkSheet(r22.sheet);
        return r22;
    L7:
        if ((getLastSheet() instanceof ArticleViewer.Sheet) == true) goto L9;
    L12:
        INavigationLayout r23 = this.parentLayout;
        if ((r23 instanceof ActionBarLayout) == false) goto L22;
        if (((ActionBarLayout) r23).getSheetFragment(false) == null) goto L22;
        if ((((ActionBarLayout) this.parentLayout).getSheetFragment(false).getLastSheet() instanceof ArticleViewer.Sheet) == false) goto L22;
        ArticleViewer.Sheet r24 = (ArticleViewer.Sheet) ((ActionBarLayout) this.parentLayout).getSheetFragment(false).getLastSheet();
        if (r24.isShown() == false) goto L22;
        return r24.getArticleViewer();
    L9:
        if (getLastSheet().isShown() == false) goto L12;
        return ((ArticleViewer.Sheet) getLastSheet()).getArticleViewer();
    }

    public StoryViewer createOverlayStoryViewer() {
        if (this.sheetsStack != null) goto L5;
        this.sheetsStack = new ArrayList();
    L5:
        StoryViewer r0 = new StoryViewer(this);
        INavigationLayout r1 = this.parentLayout;
        if (r1 != null) goto L8;
    L10:
        this.sheetsStack.add(r0);
        updateSheetsVisibility();
        return r0;
    L8:
        if (r1.isSheet() == false) goto L10;
        r0.fromBottomSheet = true;
        goto L10
    }

    public View createView(Context r1) {
        return null;
    }

    public void detachSheets() {
        if (this.sheetsStack == null) goto L13;
        int r0 = 0;
    L6:
        if (r0 >= this.sheetsStack.size()) goto L18;
        AttachedSheet r1 = this.sheetsStack.get(r0);
        if (r1 == null) goto L12;
        if (r1.attachedToParent() == false) goto L12;
        AndroidUtilities.removeFromParent(r1.getWindowView());
    L12:
        r0 = r0 + 1;
        goto L6
    L18:
        return;
    }

    public void dismissCurrentDialog() {
        Dialog r0 = this.visibleDialog;
        if (r0 != null) goto L10;
        return;
    L10:
        r0.dismiss();     // Catch: Exception -> L7
        this.visibleDialog = null;     // Catch: Exception -> L7
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
    }

    public boolean dismissDialogOnPause(Dialog r1) {
        return true;
    }

    public boolean drawEdgeNavigationBar() {
        return isSupportEdgeToEdge();
    }

    public void dumpCanvas() {
        AndroidUtilities.dumpCanvas(this.fragmentView);
    }

    public boolean extendActionMode(Menu r1) {
        return false;
    }

    public void finishFragment() {
        Dialog r0 = this.parentDialog;
        if (r0 == null) goto L7;
        r0.dismiss();
        return;
    L7:
        if (this.inPreviewMode == false) goto L12;
        PreviewDelegate r02 = this.previewDelegate;
        if (r02 == null) goto L12;
        r02.finishFragment();
        return;
    L12:
        finishFragment(true);
    }

    public boolean finishFragment(boolean r3) {
        if (this.isFinished == true) goto L9;
        INavigationLayout r0 = this.parentLayout;
        if (r0 == null) goto L11;
        this.finishing = true;
        r0.closeLastFragment(r3);
        return true;
    L11:
        return false;
    L9:
        return false;
    }

    public void finishPreviewFragment() {
        INavigationLayout r0 = this.parentLayout;
        if (r0 == null) goto L6;
        r0.finishPreviewFragment();
        return;
    }

    public AccountInstance getAccountInstance() {
        return AccountInstance.getInstance(this.currentAccount);
    }

    public ActionBar getActionBar() {
        return this.actionBar;
    }

    public Bundle getArguments() {
        return this.arguments;
    }

    public ArticleViewer getArticleViewer() {
        if ((getLastSheet() instanceof ArticleViewer.Sheet) == true) goto L5;
    L8:
        INavigationLayout r0 = this.parentLayout;
        if ((r0 instanceof ActionBarLayout) == true) goto L11;
        return null;
    L11:
        if (((ActionBarLayout) r0).getSheetFragment(false) != null) goto L13;
        return null;
    L13:
        if ((((ActionBarLayout) this.parentLayout).getSheetFragment(false).getLastSheet() instanceof ArticleViewer.Sheet) == false) goto L21;
        ArticleViewer.Sheet r2 = (ArticleViewer.Sheet) ((ActionBarLayout) this.parentLayout).getSheetFragment(false).getLastSheet();
        if (r2.isShown() == true) goto L17;
        return null;
    L17:
        return r2.getArticleViewer();
    L21:
        return null;
    L5:
        if (getLastSheet().isShown() == false) goto L8;
        return ((ArticleViewer.Sheet) getLastSheet()).getArticleViewer();
    }

    public INavigationLayout.BackButtonState getBackButtonState() {
        ActionBar r0 = this.actionBar;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getBackButtonState();
    }

    public int getBottomInset() {
        return this.bottomInset;
    }

    public Bulletin.Delegate getBulletinDelegate() {
        return this.bulletinDelegate;
    }

    public FrameLayout getBulletinLayoutContainer() {
        return getLayoutContainer();
    }

    public int getClassGuid() {
        return this.classGuid;
    }

    public ConnectionsManager getConnectionsManager() {
        return getAccountInstance().getConnectionsManager();
    }

    public ContactsController getContactsController() {
        return getAccountInstance().getContactsController();
    }

    public Context getContext() {
        return getParentActivity();
    }

    public int getCurrentAccount() {
        return this.currentAccount;
    }

    public Animator getCustomSlideTransition(boolean r1, boolean r2, float r3) {
        return null;
    }

    public DownloadController getDownloadController() {
        return getAccountInstance().getDownloadController();
    }

    public EdgeToEdgeSupportMode getEdgeToEdgeSupportMode() {
        if (isSupportEdgeToEdge() == false) goto L7;
        return EdgeToEdgeSupportMode.VERTICAL;
    L7:
        return EdgeToEdgeSupportMode.NONE;
    }

    public FileLoader getFileLoader() {
        return getAccountInstance().getFileLoader();
    }

    public boolean getFragmentBeginToShow() {
        return this.fragmentBeginToShow;
    }

    public BaseFragment getFragmentForAlert(int r3) {
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
        return this;
    L5:
        if (r0.getFragmentStack().size() > (r3 + 1)) goto L8;
        return this;
    L8:
        return this.parentLayout.getFragmentStack().get((this.parentLayout.getFragmentStack().size() - 2) - r3);
    }

    public View getFragmentView() {
        return this.fragmentView;
    }

    public GiftAuctionController getGiftAuctionsController() {
        return getAccountInstance().getGiftAuctionsController();
    }

    public boolean getInPassivePreviewMode() {
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isInPassivePreviewMode() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public AttachedSheet getLastSheet() {
        ArrayList<AttachedSheet> r0 = this.sheetsStack;
        if (r0 != null) goto L5;
    L14:
        return null;
    L5:
        if (r0.isEmpty() == true) goto L14;
        int r02 = this.sheetsStack.size() - 1;
    L8:
        if (r02 < 0) goto L14;
        if (this.sheetsStack.get(r02).isShown() == true) goto L12;
        r02 = r02 - 1;
        goto L8
    L12:
        return this.sheetsStack.get(r02);
    }

    public StoryViewer getLastStoryViewer() {
        ArrayList<AttachedSheet> r0 = this.sheetsStack;
        if (r0 != null) goto L5;
    L16:
        return null;
    L5:
        if (r0.isEmpty() == true) goto L16;
        int r02 = this.sheetsStack.size() - 1;
    L8:
        if (r02 < 0) goto L16;
        if ((this.sheetsStack.get(r02) instanceof StoryViewer) == false) goto L15;
        if (this.sheetsStack.get(r02).isShown() == false) goto L15;
        return (StoryViewer) this.sheetsStack.get(r02);
    L15:
        r02 = r02 - 1;
        goto L8
    }

    public FrameLayout getLayoutContainer() {
        View r1 = this.fragmentView;
        if (r1 == null) goto L8;
        ViewParent r12 = r1.getParent();
        if ((r12 instanceof FrameLayout) == true) goto L7;
        return null;
    L7:
        return (FrameLayout) r12;
    L8:
        return null;
    }

    public LocationController getLocationController() {
        return getAccountInstance().getLocationController();
    }

    public MediaController getMediaController() {
        return MediaController.getInstance();
    }

    public MediaDataController getMediaDataController() {
        return getAccountInstance().getMediaDataController();
    }

    public MessagesController getMessagesController() {
        return getAccountInstance().getMessagesController();
    }

    public MessagesStorage getMessagesStorage() {
        return getAccountInstance().getMessagesStorage();
    }

    public int getNavigationBarColor() {
        int r0 = Theme.getColor(Theme.key_windowBackgroundGray, getResourceProvider());
        if (this.sheetsStack == null) goto L11;
        int r1 = 0;
    L6:
        if (r1 >= this.sheetsStack.size()) goto L11;
        AttachedSheet r2 = this.sheetsStack.get(r1);
        if (r2.attachedToParent() == false) goto L10;
        r0 = r2.getNavigationBarColor(r0);
    L10:
        r1 = r1 + 1;
    L11:
        return r0;
    }

    public NotificationCenter getNotificationCenter() {
        return getAccountInstance().getNotificationCenter();
    }

    public NotificationsController getNotificationsController() {
        return getAccountInstance().getNotificationsController();
    }

    public SharedPreferences getNotificationsSettings() {
        return getAccountInstance().getNotificationsSettings();
    }

    public StoryViewer getOrCreateStoryViewer() {
        if (this.sheetsStack != null) goto L6;
        this.sheetsStack = new ArrayList();
    L6:
        if (this.sheetsStack.isEmpty() == true) goto L10;
        ArrayList<AttachedSheet> r0 = this.sheetsStack;
        if ((r0.get(r0.size() - 1) instanceof StoryViewer) == false) goto L10;
        ArrayList<AttachedSheet> r02 = this.sheetsStack;
        StoryViewer r03 = (StoryViewer) r02.get(r02.size() - 1);
    L11:
        if (r03 != null) goto L18;
        r03 = new StoryViewer(this);
        INavigationLayout r2 = this.parentLayout;
        if (r2 != null) goto L15;
    L17:
        this.sheetsStack.add(r03);
        updateSheetsVisibility();
        goto L18
    L15:
        if (r2.isSheet() == false) goto L17;
        r03.fromBottomSheet = true;
    L18:
        return r03;
    L10:
        r03 = null;
        goto L11
    }

    public StoryViewer getOrCreateStoryViewer(int r5) {
        if (this.sheetsStack != null) goto L5;
        this.sheetsStack = new ArrayList();
    L5:
        StoryViewer r1 = null;
        if (this.sheetsStack.isEmpty() == true) goto L10;
        ArrayList<AttachedSheet> r0 = this.sheetsStack;
        if ((r0.get(r0.size() - 1) instanceof StoryViewer) == false) goto L10;
        ArrayList<AttachedSheet> r02 = this.sheetsStack;
        StoryViewer r03 = (StoryViewer) r02.get(r02.size() - 1);
    L11:
        if (r03 != null) goto L13;
    L15:
        r1 = r03;
    L16:
        if (r1 != null) goto L24;
        StoryViewer r52 = new StoryViewer(this);
        INavigationLayout r04 = this.parentLayout;
        if (r04 != null) goto L20;
    L22:
        this.sheetsStack.add(r52);
        updateSheetsVisibility();
        return r52;
    L20:
        if (r04.isSheet() == false) goto L22;
        r52.fromBottomSheet = true;
        goto L22
    L24:
        return r1;
    L13:
        if (r03.currentAccount == r5) goto L15;
        r03.close(true);
        removeSheet(r03);
    L10:
        r03 = null;
        goto L11
    }

    public Activity getParentActivity() {
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getParentActivity();
    }

    public INavigationLayout getParentLayout() {
        return this.parentLayout;
    }

    public int getPreviewHeight() {
        return -1;
    }

    public Theme.ResourcesProvider getResourceProvider() {
        return this.resourceProvider;
    }

    public SecretChatHelper getSecretChatHelper() {
        return getAccountInstance().getSecretChatHelper();
    }

    public SendMessagesHelper getSendMessagesHelper() {
        return getAccountInstance().getSendMessagesHelper();
    }

    public ArrayList<ThemeDescription> getThemeDescriptions() {
        return new ArrayList();
    }

    public int getThemedColor(int r1) {
        return Theme.getColor(r1, getResourceProvider());
    }

    public Drawable getThemedDrawable(String r1) {
        return Theme.getThemeDrawable(r1);
    }

    public Paint getThemedPaint(String r2) {
        if (getResourceProvider() == null) goto L5;
        Paint r1 = getResourceProvider().getPaint(r2);
    L6:
        if (r1 == null) goto L9;
        return r1;
    L9:
        return Theme.getThemePaint(r2);
    L5:
        r1 = null;
        goto L6
    }

    public UserConfig getUserConfig() {
        return getAccountInstance().getUserConfig();
    }

    public Dialog getVisibleDialog() {
        return this.visibleDialog;
    }

    public boolean hasForceLightStatusBar() {
        return false;
    }

    public boolean hasOwnBackground() {
        return this.hasOwnBackground;
    }

    public boolean hasSheet() {
        ArrayList<AttachedSheet> r0 = this.sheetsStack;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isEmpty() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasShownFullyVisibleSheet() {
        if (hasSheet() == true) goto L5;
        return false;
    L5:
        int r0 = this.sheetsStack.size() - 1;
    L6:
        if (r0 < 0) goto L13;
        if (this.sheetsStack.get(r0).isShown() == false) goto L12;
        if (this.sheetsStack.get(r0).isFullyVisible() == false) goto L12;
        return true;
    L12:
        r0 = r0 - 1;
        goto L6
    L13:
        return false;
    }

    public boolean hasShownSheet() {
        if (hasSheet() == true) goto L5;
        return false;
    L5:
        int r0 = this.sheetsStack.size() - 1;
    L6:
        if (r0 < 0) goto L11;
        if (this.sheetsStack.get(r0).isShown() == true) goto L9;
        r0 = r0 - 1;
        goto L6
    L9:
        return true;
    L11:
        return false;
    }

    public boolean hasStoryViewer() {
        if (getLastStoryViewer() == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hideKeyboardOnShow() {
        return true;
    }

    public boolean isActionBarCrossfadeEnabled() {
        if (this.actionBar == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isBeginToShow() {
        return this.fragmentBeginToShow;
    }

    public boolean isBotView(View r3) {
        if (this.sheetsStack == null) goto L8;
        int r32 = 0;
    L6:
        if (r32 >= this.sheetsStack.size()) goto L8;
        AttachedSheet r1 = this.sheetsStack.get(r32);
        r32 = r32 + 1;
    L8:
        return false;
    }

    public boolean isFinishing() {
        return this.finishing;
    }

    public boolean isInBubbleMode() {
        return this.inBubbleMode;
    }

    public boolean isInPreviewMode() {
        return this.inPreviewMode;
    }

    public boolean isLastFragment() {
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getLastFragment() != this) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isLightStatusBar() {
        if (getLastStoryViewer() == null) goto L8;
        if (getLastStoryViewer().isShown() == false) goto L8;
        return false;
    L8:
        if (hasForceLightStatusBar() == true) goto L10;
    L12:
        Theme.ResourcesProvider r0 = getResourceProvider();
        int r3 = Theme.key_actionBarDefault;
        ActionBar r7 = this.actionBar;
        if (r7 != null) goto L15;
    L17:
        if (r0 == null) goto L19;
        int r72 = r0.getColorOrDefault(r3);
    L21:
        if (ColorUtils.calculateLuminance(r72) <= 0.699999988079071d) goto L23;
        return true;
    L23:
        return false;
    L19:
        r72 = Theme.getColor(r3, null, true);
        goto L21
    L15:
        if (r7.isActionModeShowed() == false) goto L17;
        r3 = Theme.key_actionBarActionModeDefault;
        goto L17
    L10:
        if (Theme.getCurrentTheme().isDark() == true) goto L12;
        return true;
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public boolean isRemovingFromStack() {
        return this.removingFromStack;
    }

    public boolean isStoryViewer(View r5) {
        if (this.sheetsStack == null) goto L14;
        int r0 = 0;
    L6:
        if (r0 >= this.sheetsStack.size()) goto L14;
        AttachedSheet r2 = this.sheetsStack.get(r0);
        if ((r2 instanceof StoryViewer) == false) goto L13;
        if (r5 != r2.getWindowView()) goto L13;
        return true;
    L13:
        r0 = r0 + 1;
    L14:
        return false;
    }

    @Deprecated
    public boolean isSupportEdgeToEdge() {
        return false;
    }

    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return true;
    }

    public void movePreviewFragment(float r1) {
        this.parentLayout.movePreviewFragment(r1);
    }

    public boolean needDelayOpenAnimation() {
        return false;
    }

    public void onActivityResultFragment(int r1, int r2, Intent r3) {
        IconManager.INSTANCE.onActivityResult(r1, r2, r3);
    }

    public boolean onBackPressed(boolean r2) {
        if (hasShownSheet() == false) goto L8;
        if (r2 == false) goto L10;
        closeSheet();
        return false;
    L10:
        return false;
    L8:
        return true;
    }

    public void onBecomeFullyHidden() {
        this.isFullyVisible = false;
        updateSheetsVisibility();
    }

    public void onBecomeFullyVisible() {
        this.isFullyVisible = true;
        if (((AccessibilityManager) ApplicationLoader.applicationContext.getSystemService("accessibility")).isEnabled() == false) goto L9;
        ActionBar r0 = getActionBar();
        if (r0 == null) goto L9;
        String r02 = r0.getTitle();
        if (TextUtils.isEmpty(r02) == true) goto L9;
        setParentActivityTitle(r02);
    L9:
        Runnable r03 = this.fullyVisibleListener;
        if (r03 == null) goto L12;
        this.fullyVisibleListener = null;
        r03.run();
    L12:
        updateSheetsVisibility();
        checkSystemBarColors();
    }

    public void onBeginSlide() {
        Dialog r0 = this.visibleDialog;     // Catch: Exception -> L8
        if (r0 != null) goto L5;
    L10:
        ActionBar r1 = this.actionBar;
        if (r1 == null) goto L16;
        r1.onPause();
        return;
    L16:
        return;
    L5:
        if (r0.isShowing() == false) goto L10;
        this.visibleDialog.dismiss();     // Catch: Exception -> L8
        this.visibleDialog = null;     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L10
    }

    public void onBottomSheetCreated() {
    }

    public void onConfigurationChanged(Configuration r1) {
    }

    public AnimatorSet onCustomTransitionAnimation(boolean r1, Runnable r2) {
        return null;
    }

    public void onDialogDismiss(Dialog r1) {
    }

    public void onFragmentClosed() {
    }

    public boolean onFragmentCreate() {
        return true;
    }

    public void onFragmentDestroy() {
        getConnectionsManager().cancelRequestsForGuid(this.classGuid);
        getMessagesStorage().cancelTasksForGuid(this.classGuid);
        this.isFinished = true;
        ActionBar r1 = this.actionBar;
        if (r1 == null) goto L5;
        r1.setEnabled(false);
    L5:
        IconObserver.INSTANCE.removeSource(this);
        if (hasForceLightStatusBar() == true) goto L8;
    L20:
        ArrayList<AttachedSheet> r12 = this.sheetsStack;
        if (r12 == null) goto L25;
        int r13 = r12.size() - 1;
    L23:
        if (r13 < 0) goto L27;
        AttachedSheet r3 = this.sheetsStack.get(r13);
        r3.setLastVisible(false);
        r3.dismiss(true);
        this.sheetsStack.remove(r13);
        r13 = r13 - 1;
        goto L23
    L27:
        return;
    L25:
        return;
    L8:
        if (AndroidUtilities.isTablet() == true) goto L20;
        if (getParentLayout().getLastFragment() != this) goto L20;
        if (getParentActivity() == null) goto L20;
        if (this.finishing == true) goto L20;
        Activity r14 = getParentActivity();
        if (Theme.getColor(Theme.key_actionBarDefault) != (-1)) goto L18;
        boolean r32 = true;
    L19:
        AndroidUtilities.setLightStatusBar(r14, r32);
        goto L20
    L18:
        r32 = false;
        goto L19
    }

    public void onInsets(int r1, int r2, int r3, int r4) {
    }

    public WindowInsetsCompat onInsetsInternal(View r3, WindowInsetsCompat r4) {
        Insets r32 = r4.getInsets(WindowInsetsCompat.Type.navigationBars() | WindowInsetsCompat.Type.statusBars());
        int r42 = r32.left;
        int r0 = r32.top;
        int r1 = r32.right;
        int r33 = r32.bottom;
        this.bottomInset = r33;
        onInsets(r42, r0, r1, r33);
        return WindowInsetsCompat.CONSUMED;
    }

    public void onLowMemory() {
    }

    public void onPanTransitionEnd() {
    }

    public void onPanTransitionStart() {
    }

    public void onPanTranslationUpdate(float r1) {
    }

    public void onPause() {
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L5;
        r0.onPause();
    L5:
        this.isPaused = true;
        Dialog r02 = this.visibleDialog;     // Catch: Exception -> L14
        if (r02 == null) goto L17;
        if (r02.isShowing() == false) goto L17;
        if (dismissDialogOnPause(this.visibleDialog) == false) goto L17;
        this.visibleDialog.dismiss();     // Catch: Exception -> L14
        this.visibleDialog = null;     // Catch: Exception -> L14
    L17:
        if (getLastStoryViewer() == null) goto L22;
        getLastStoryViewer().onPause();
        getLastStoryViewer().updatePlayingMode();
        return;
    L22:
        return;
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L17
    }

    public void onPreviewOpenAnimationEnd() {
    }

    public void onRemoveFromParent() {
        ArrayList<AttachedSheet> r0 = this.sheetsStack;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isEmpty() == true) goto L10;
        updateSheetsVisibility();
        return;
    }

    public void onRequestPermissionsResultFragment(int r1, String[] r2, int[] r3) {
    }

    public void onResume() {
        this.isPaused = false;
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L6;
        r0.onResume();
    L6:
        if (getLastStoryViewer() == null) goto L9;
        getLastStoryViewer().onResume();
        getLastStoryViewer().updatePlayingMode();
        return;
    }

    public void onSlideProgress(boolean r1, float r2) {
    }

    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        this.inTransitionAnimation = false;
    }

    public void onTransitionAnimationProgress(boolean r1, float r2) {
    }

    public void onTransitionAnimationStart(boolean r1, boolean r2) {
        this.inTransitionAnimation = true;
        if (r1 == false) goto L6;
        this.fragmentBeginToShow = true;
        return;
    }

    public void onUserLeaveHint() {
    }

    public void onViewCreated(View r1) {
        this.glassEngine.setRoot(r1);
    }

    public View performCreateView(Context r4) {
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L5;
        String r0 = getClass().getSimpleName();
        String r2 = "Fragment#createView#";
        if (TextUtils.isEmpty(r0) == true) goto L10;
        r2 = "Fragment#createView#".concat(r0);
    L10:
        Trace.beginSection(r2);
        View r3 = performCreateViewImpl(r4);     // Catch: Throwable -> L14
        Trace.endSection();
        return r3;
    L14:
        th = move-exception;
        Trace.endSection();
        throw th;
    L5:
        return performCreateViewImpl(r4);
    }

    public void prepareFragmentToSlide(boolean r1, boolean r2) {
    }

    public boolean presentFragment(BaseFragment r2) {
        if (allowPresentFragment() == false) goto L10;
        INavigationLayout r1 = this.parentLayout;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.presentFragment(r2) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean presentFragment(BaseFragment r2, boolean r3) {
        if (allowPresentFragment() == false) goto L10;
        INavigationLayout r1 = this.parentLayout;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.presentFragment(r2, r3) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean presentFragment(BaseFragment r9, boolean r10, boolean r11) {
        if (allowPresentFragment() == false) goto L10;
        INavigationLayout r1 = this.parentLayout;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.presentFragment(r9, r10, r11, true, false, null) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean presentFragment(INavigationLayout.NavigationParams r2) {
        if (allowPresentFragment() == false) goto L10;
        INavigationLayout r1 = this.parentLayout;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.presentFragment(r2) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean presentFragmentAsPreview(BaseFragment r2) {
        if (allowPresentFragment() == false) goto L10;
        INavigationLayout r1 = this.parentLayout;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.presentFragmentAsPreview(r2) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean presentFragmentAsPreviewWithMenu(BaseFragment r2, ActionBarPopupWindow.ActionBarPopupWindowLayout r3) {
        if (allowPresentFragment() == false) goto L10;
        INavigationLayout r1 = this.parentLayout;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.presentFragmentAsPreviewWithMenu(r2, r3) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public void removeSelfFromStack() {
        removeSelfFromStack(false);
    }

    public void removeSelfFromStack(boolean r3) {
        if (this.isFinished == true) goto L13;
        INavigationLayout r0 = this.parentLayout;
        if (r0 == null) goto L14;
        Dialog r1 = this.parentDialog;
        if (r1 == null) goto L11;
        r1.dismiss();
        return;
    L11:
        r0.removeFragmentFromStack(this, r3);
        return;
    L14:
        return;
    }

    public void removeSheet(AttachedSheet r2) {
        ArrayList<AttachedSheet> r0 = this.sheetsStack;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.remove(r2);
        updateSheetsVisibility();
    }

    public void resetFragment() {
        if (this.isFinished == false) goto L6;
        clearViews();
        this.isFinished = false;
        this.finishing = false;
        return;
    }

    public void restoreSelfArgs(Bundle r1) {
    }

    public void resumeDelayedFragmentAnimation() {
        INavigationLayout r0 = this.parentLayout;
        if (r0 == null) goto L6;
        r0.resumeDelayedFragmentAnimation();
        return;
    }

    public void saveKeyboardPositionBeforeTransition() {
    }

    public void saveSelfArgs(Bundle r1) {
    }

    public void setBulletinDelegate(Bulletin.Delegate r1) {
        this.bulletinDelegate = r1;
    }

    public void setCurrentAccount(int r2) {
        if (this.fragmentView != null) goto L6;
        this.currentAccount = r2;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("trying to set current account when fragment UI already created");
    }

    public void setFinishing(boolean r1) {
        this.finishing = r1;
    }

    public void setFragmentPanTranslationOffset(int r1) {
        INavigationLayout r0 = this.parentLayout;
        if (r0 == null) goto L6;
        r0.setFragmentPanTranslationOffset(r1);
        return;
    }

    public void setFragmentView(View r1) {
        this.fragmentView = r1;
    }

    public void setHasOwnBackground(boolean r1) {
        this.hasOwnBackground = r1;
    }

    public void setInBubbleMode(boolean r1) {
        this.inBubbleMode = r1;
    }

    public void setInMenuMode(boolean r1) {
        this.inMenuMode = r1;
    }

    public void setInPreviewMode(boolean r1) {
        this.inPreviewMode = r1;
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L6;
        r0.setOccupyStatusBar(!r1);
        return;
    }

    public void setKeyboardHeightFromParent(int r3) {
        if (this.sheetsStack == null) goto L11;
        int r0 = 0;
    L6:
        if (r0 >= this.sheetsStack.size()) goto L15;
        AttachedSheet r1 = this.sheetsStack.get(r0);
        if (r1 == null) goto L10;
        r1.setKeyboardHeightFromParent(r3);
    L10:
        r0 = r0 + 1;
        goto L6
    L15:
        return;
    }

    public void setNavigationBarColor(int r5) {
        if (isSupportEdgeToEdge() == true) goto L22;
        Activity r0 = getParentActivity();
        if ((r0 instanceof LaunchActivity) == false) goto L8;
        ((LaunchActivity) r0).setNavigationBarColor(r5);
    L14:
        if (AndroidUtilities.computePerceivedBrightness(r5) < 0.721f) goto L16;
        boolean r1 = true;
    L17:
        AndroidUtilities.setLightNavigationBar(r0, r1);
        INavigationLayout r4 = this.parentLayout;
        if (r4 == null) goto L21;
        r4.setNavigationBarColor(r5);
        return;
    L21:
        return;
    L16:
        r1 = false;
        goto L17
    L8:
        if (r0 == null) goto L14;
        Window r12 = r0.getWindow();
        if (Build.VERSION.SDK_INT < 26) goto L14;
        if (r12 == null) goto L14;
        r12.getNavigationBarColor();
        goto L14
    }

    public void setParentActivityTitle(CharSequence r1) {
        Activity r0 = getParentActivity();
        if (r0 == null) goto L6;
        r0.setTitle(r1);
        return;
    }

    public void setParentFragment(BaseFragment r1) {
        setParentLayout(r1.parentLayout);
        this.fragmentView = createView(this.parentLayout.getView().getContext());
    }

    public void setParentLayout(INavigationLayout r5) {
        if (this.parentLayout == r5) goto L53;
        this.parentLayout = r5;
        boolean r0 = false;
        if (r5 != null) goto L7;
    L9:
        boolean r52 = false;
    L10:
        this.inBubbleMode = r52;
        View r53 = this.fragmentView;
        if (r53 == null) goto L24;
        ViewGroup r54 = (ViewGroup) r53.getParent();
        if (r54 != null) goto L51;
    L18:
        INavigationLayout r55 = this.parentLayout;
        if (r55 == null) goto L24;
        if (r55.getView().getContext() == this.fragmentView.getContext()) goto L24;
        this.fragmentView = null;
        clearSheets();
        goto L24
    L51:
        onRemoveFromParent();     // Catch: Exception -> L16
        removeViewFromParent(r54, this.fragmentView);     // Catch: Exception -> L16
    L16:
        e = move-exception;
        FileLog.e(e);
    L24:
        if (this.actionBar == null) goto L41;
        INavigationLayout r56 = this.parentLayout;
        if (r56 == null) goto L31;
        if (r56.getView().getContext() == this.actionBar.getContext()) goto L31;
        r0 = true;
    L31:
        if (this.actionBar.shouldAddToContainer() == true) goto L33;
        if (r0 == true) goto L33;
    L39:
        if (r0 == false) goto L41;
        this.actionBar = null;
    L33:
        ViewGroup r57 = (ViewGroup) this.actionBar.getParent();
        if (r57 == null) goto L39;
        removeViewFromParent(r57, this.actionBar);     // Catch: Exception -> L37
    L37:
        e = move-exception;
        FileLog.e(e);
    L41:
        INavigationLayout r58 = this.parentLayout;
        if (r58 != null) goto L44;
        return;
    L44:
        if (this.actionBar != null) goto L55;
        ActionBar r59 = createActionBar(r58.getView().getContext());
        this.actionBar = r59;
        if (r59 == null) goto L56;
        r59.parentFragment = this;
        return;
    L56:
        return;
    L55:
        return;
    L7:
        if (r5.isInBubbleMode() == false) goto L9;
        r52 = true;
        goto L10
    }

    public void setPaused(boolean r2) {
        if (this.isPaused != r2) goto L5;
        return;
    L5:
        if (r2 == false) goto L8;
        onPause();
        return;
    L8:
        onResume();
    }

    public void setPreviewDelegate(PreviewDelegate r1) {
        this.previewDelegate = r1;
    }

    public void setPreviewOpenedProgress(float r1) {
    }

    public void setPreviewReplaceProgress(float r1) {
    }

    public void setRemovingFromStack(boolean r1) {
        this.removingFromStack = r1;
    }

    public void setResourceProvider(Theme.ResourcesProvider r1) {
        this.resourceProvider = r1;
    }

    public void setTitleOverlayText(String r1, int r2, Runnable r3) {
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L6;
        r0.setTitleOverlayText(r1, r2, r3);
        return;
    }

    public void setTitleOverlayTextIfActionBarAttached(String r2, int r3, Runnable r4) {
        ActionBar r0 = this.actionBar;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.shouldAddToContainer() == false) goto L9;
        setTitleOverlayText(r2, r3, r4);
        return;
    }

    public void setVisibleDialog(Dialog r1) {
        this.visibleDialog = r1;
    }

    public boolean shouldOverrideSlideTransition(boolean r1, boolean r2) {
        return false;
    }

    public INavigationLayout[] showAsSheet(BaseFragment r2) {
        return showAsSheet(r2, null);
    }

    public INavigationLayout[] showAsSheet(BaseFragment r12, BottomSheetParams r13) {
        if (getParentActivity() != null) goto L6;
        return null;
    L6:
        INavigationLayout[] r7 = {INavigationLayout.newLayout(getParentActivity(), false, new BaseFragment$$ExternalSyntheticLambda0(r9))};
        r7[0].setIsSheet(true);
        LaunchActivity.instance.sheetFragmentsStack.add(r7[0]);
        r12.onTransitionAnimationStart(true, false);
        AnonymousClass2 r1 = new AnonymousClass2(this, getParentActivity(), true, r12.getResourceProvider(), r13, r7, r12, r9);
        BottomSheet[] r9 = {r1};
        if (r13 == null) goto L9;
        r1.setAllowNestedScroll(r13.allowNestedScroll);
        r9[0].transitionFromRight(r13.transitionFromLeft);
    L9:
        r12.setParentDialog(r9[0]);
        r9[0].setOpenNoDelay(true);
        r9[0].show();
        return r7;
    }

    public Dialog showDialog(Dialog r3) {
        return showDialog(r3, false, null);
    }

    public Dialog showDialog(Dialog r2, DialogInterface.OnDismissListener r3) {
        return showDialog(r2, false, r3);
    }

    public Dialog showDialog(Dialog r4, boolean r5, DialogInterface.OnDismissListener r6) {
        if (r4 == null) goto L34;
        INavigationLayout r1 = this.parentLayout;
        if (r1 == null) goto L34;
        if (r1.isTransitionAnimationInProgress() == true) goto L34;
        if (this.parentLayout.isSwipeInProgress() == true) goto L34;
        if (r5 == false) goto L12;
    L14:
        ArrayList<AttachedSheet> r52 = this.sheetsStack;
        if (r52 == null) goto L37;
        int r53 = r52.size() - 1;
    L17:
        if (r53 < 0) goto L37;
        if (this.sheetsStack.get(r53).isShown() == false) goto L23;
        if (this.sheetsStack.get(r53).showDialog(r4) == false) goto L23;
        return r4;
    L23:
        r53 = r53 - 1;
    L37:
        Dialog r54 = this.visibleDialog;     // Catch: Exception -> L28
        if (r54 == null) goto L35;
        r54.dismiss();     // Catch: Exception -> L28
    L35:
        this.visibleDialog = r4;     // Catch: Exception -> L32
        r4.setCanceledOnTouchOutside(true);     // Catch: Exception -> L32
        this.visibleDialog.setOnDismissListener(new BaseFragment$$ExternalSyntheticLambda1(this, r6));     // Catch: Exception -> L32
        this.visibleDialog.show();     // Catch: Exception -> L32
        return this.visibleDialog;
    L32:
        e = move-exception;
        FileLog.e(e);
    L28:
        e = move-exception;
        FileLog.e(e);
        goto L35
    L12:
        if (this.parentLayout.checkTransitionAnimation() == false) goto L14;
    L34:
        return null;
    }

    public void startActivityForResult(Intent r1, int r2) {
        INavigationLayout r0 = this.parentLayout;
        if (r0 == null) goto L6;
        r0.startActivityForResult(r1, r2);
        return;
    }

    public void updateSheetsVisibility() {
        if (this.sheetsStack == null) goto L15;
        int r1 = 0;
    L7:
        if (r1 >= this.sheetsStack.size()) goto L19;
        AttachedSheet r2 = this.sheetsStack.get(r1);
        boolean r4 = true;
        if (r1 == (this.sheetsStack.size() - 1)) goto L11;
    L13:
        r4 = false;
    L14:
        r2.setLastVisible(r4);
        r1 = r1 + 1;
        goto L7
    L11:
        if (this.isFullyVisible == false) goto L13;
    L19:
        return;
    }

    public void whenFullyVisible(Runnable r1) {
        this.fullyVisibleListener = r1;
    }
}
