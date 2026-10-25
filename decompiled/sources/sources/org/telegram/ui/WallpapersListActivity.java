package org.telegram.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.LongSparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.WallpaperCell;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.WallpaperUpdater;

/* loaded from: classes6.dex */
public class WallpapersListActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private static final int[][] defaultColorsDark = null;
    private static final int[][] defaultColorsLight = null;
    private static final int[] searchColors = null;
    private static final String[] searchColorsNames = null;
    private static final int[] searchColorsNamesR = null;
    private ArrayList<View> actionModeViews;
    private ColorWallpaper addedColorWallpaper;
    private FileWallpaper addedFileWallpaper;
    private ArrayList<Object> allWallPapers;
    private HashMap<String, Object> allWallPapersDict;
    private ColorWallpaper catsWallpaper;
    private Paint colorFramePaint;
    private Paint colorPaint;
    private int columnsCount;
    private int currentType;
    private final long dialogId;
    private int galleryHintRow;
    private int galleryRow;
    private LinearLayoutManager layoutManager;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private HashMap<String, Object> localDict;
    private ArrayList<ColorWallpaper> localWallPapers;
    private ArrayList<Object> patterns;
    private HashMap<Long, Object> patternsDict;
    private AlertDialog progressDialog;
    private int resetInfoRow;
    private int resetRow;
    private int resetSectionRow;
    private int rowCount;
    private boolean scrolling;
    private SearchAdapter searchAdapter;
    private EmptyTextProgressView searchEmptyView;
    private ActionBarMenuItem searchItem;
    private int sectionRow;
    private boolean selectedBackgroundBlurred;
    private boolean selectedBackgroundMotion;
    private String selectedBackgroundSlug;
    private int selectedColor;
    private int selectedGradientColor1;
    private int selectedGradientColor2;
    private int selectedGradientColor3;
    private int selectedGradientRotation;
    private float selectedIntensity;
    private NumberTextView selectedMessagesCountTextView;
    private final LongSparseArray<Object> selectedWallPapers;
    private int setColorRow;
    private FileWallpaper themeWallpaper;
    private int totalWallpaperRows;
    private WallpaperUpdater updater;

    @Keep
    private int uploadImageRow;
    private int wallPaperStartRow;
    private ArrayList<Object> wallPapers;

    /* renamed from: org.telegram.ui.WallpapersListActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements WallpaperUpdater.WallpaperUpdaterDelegate {
        final /* synthetic */ WallpapersListActivity this$0;

        public static /* synthetic */ void $r8$lambda$AsS4yYT_tiugaH8ccTih_PQMTLg(AnonymousClass1 r0, TLRPC.WallPaper r1) {
            r0.lambda$didSelectWallpaper$0(r1);
        }

        public AnonymousClass1(WallpapersListActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$didSelectWallpaper$0(TLRPC.WallPaper r1) {
            this.this$0.removeSelfFromStack();
        }

        @Override // org.telegram.ui.Components.WallpaperUpdater.WallpaperUpdaterDelegate
        public void didSelectWallpaper(File r4, Bitmap r5, boolean r6) {
            ThemePreviewActivity r0 = new ThemePreviewActivity(new FileWallpaper(_UrlKt.FRAGMENT_ENCODE_SET, r4, r4), r5);
            r0.setDialogId(WallpapersListActivity.m21231$$Nest$fgetdialogId(this.this$0));
            if (WallpapersListActivity.m21231$$Nest$fgetdialogId(this.this$0) == 0) goto L5;
            r0.setDelegate(new WallpapersListActivity$1$$ExternalSyntheticLambda0(this));
        L5:
            this.this$0.presentFragment(r0, r6);
        }

        @Override // org.telegram.ui.Components.WallpaperUpdater.WallpaperUpdaterDelegate
        public void needOpenColorPicker() {
        }
    }

    /* renamed from: org.telegram.ui.WallpapersListActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ WallpapersListActivity this$0;

        public static /* synthetic */ void $r8$lambda$Nf2AqC0Y8CaUBkD4IZ6iDcyGaZo(AnonymousClass2 r0, int[] r1) {
            r0.lambda$onItemClick$0(r1);
        }

        /* renamed from: $r8$lambda$TUrytXO1OG2ZTE3-Zmra108a_so, reason: not valid java name */
        public static /* synthetic */ void m21273$r8$lambda$TUrytXO1OG2ZTE3Zmra108a_so(AnonymousClass2 r0, AlertDialog r1, int r2) {
            r0.lambda$onItemClick$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$mhZrOjukErnPTsuJk32bz2lUgg8(AnonymousClass2 r0, int[] r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onItemClick$1(r1, r2, r3);
        }

        public static /* synthetic */ boolean $r8$lambda$uxPdhqJM6jfmXEbTx_KfRtTGhjY(AnonymousClass2 r0, DialogsActivity r1, ArrayList r2, CharSequence r3, boolean r4, boolean r5, int r6, int r7, TopicsFragment r8) {
            return r0.lambda$onItemClick$3(r1, r2, r3, r4, r5, r6, r7, r8);
        }

        public AnonymousClass2(WallpapersListActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onItemClick$0(int[] r4) {
            int r1 = r4[0] - 1;
            r4[0] = r1;
            if (r1 != 0) goto L6;
            WallpapersListActivity.m21266$$Nest$mloadWallpapers(this.this$0, true);
            return;
        }

        private /* synthetic */ void lambda$onItemClick$1(int[] r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new WallpapersListActivity$2$$ExternalSyntheticLambda3(this, r1));
        }

        private /* synthetic */ void lambda$onItemClick$2(AlertDialog r10, int r11) {
            WallpapersListActivity.m21262$$Nest$fputprogressDialog(this.this$0, new AlertDialog(this.this$0.getParentActivity(), 3));
            WallpapersListActivity.m21239$$Nest$fgetprogressDialog(this.this$0).setCanCancel(false);
            WallpapersListActivity.m21239$$Nest$fgetprogressDialog(this.this$0).show();
            new ArrayList();
            int[] r0 = {0};
            int r1 = 0;
        L4:
            if (r1 >= WallpapersListActivity.m21256$$Nest$fgetselectedWallPapers(this.this$0).size()) goto L32;
            Object r2 = WallpapersListActivity.m21256$$Nest$fgetselectedWallPapers(this.this$0).valueAt(r1);
            if ((r2 instanceof ColorWallpaper) == false) goto L14;
            ColorWallpaper r3 = (ColorWallpaper) r2;
            TLRPC.WallPaper r4 = r3.parentWallpaper;
            if (r4 != null) goto L10;
        L12:
            r2 = r4;
            goto L14
        L10:
            if (r4.id >= 0) goto L12;
            this.this$0.getMessagesStorage().deleteWallpaper(r3.parentWallpaper.id);
            WallpapersListActivity.m21238$$Nest$fgetlocalWallPapers(this.this$0).remove(r3);
            WallpapersListActivity.m21237$$Nest$fgetlocalDict(this.this$0).remove(r3.getHash());
        L14:
            if ((r2 instanceof TLRPC.WallPaper) == false) goto L30;
            r0[0] = r0[0] + 1;
            TLRPC.WallPaper r32 = (TLRPC.WallPaper) r2;
            TL_account.saveWallPaper r42 = new TL_account.saveWallPaper();
            r42.settings = new TLRPC.TL_wallPaperSettings();
            r42.unsave = true;
            if ((r2 instanceof TLRPC.TL_wallPaperNoFile) == false) goto L19;
            TLRPC.TL_inputWallPaperNoFile r22 = new TLRPC.TL_inputWallPaperNoFile();
            r22.id = r32.id;
            r42.wallpaper = r22;
        L20:
            String r23 = r32.slug;
            if (r23 != null) goto L23;
        L29:
            ConnectionsManager.getInstance(WallpapersListActivity.access$1000(this.this$0)).sendRequest(r42, new WallpapersListActivity$2$$ExternalSyntheticLambda2(this, r0));
            goto L30
        L23:
            if (r23.equals(WallpapersListActivity.m21249$$Nest$fgetselectedBackgroundSlug(this.this$0)) == false) goto L29;
            WallpapersListActivity r24 = this.this$0;
            if (Theme.hasWallpaperFromTheme() == false) goto L27;
            String r33 = "t";
        L28:
            WallpapersListActivity.m21264$$Nest$fputselectedBackgroundSlug(r24, r33);
            Theme.getActiveTheme().setOverrideWallpaper(null);
            Theme.reloadWallpaper(true);
            goto L29
        L27:
            r33 = "d";
            goto L28
        L19:
            TLRPC.TL_inputWallPaper r25 = new TLRPC.TL_inputWallPaper();
            r25.id = r32.id;
            r25.access_hash = r32.access_hash;
            r42.wallpaper = r25;
        L30:
            r1 = r1 + 1;
            goto L4
        L32:
            if (r0[0] != 0) goto L34;
            WallpapersListActivity.m21266$$Nest$mloadWallpapers(this.this$0, true);
        L34:
            WallpapersListActivity.m21256$$Nest$fgetselectedWallPapers(this.this$0).clear();
            WallpapersListActivity.access$1100(this.this$0).hideActionMode();
            WallpapersListActivity.access$1200(this.this$0).closeSearchField();
        }

        private /* synthetic */ boolean lambda$onItemClick$3(DialogsActivity r23, ArrayList r24, CharSequence r25, boolean r26, boolean r27, int r28, int r29, TopicsFragment r30) {
            StringBuilder r2 = new StringBuilder();
            int r3 = 0;
            int r4 = 0;
        L3:
            int r5 = WallpapersListActivity.m21256$$Nest$fgetselectedWallPapers(this.this$0).size();
            WallpapersListActivity r6 = this.this$0;
            if (r4 >= r5) goto L18;
            Object r52 = WallpapersListActivity.m21256$$Nest$fgetselectedWallPapers(r6).valueAt(r4);
            if ((r52 instanceof TLRPC.TL_wallPaper) == false) goto L9;
            String r53 = AndroidUtilities.getWallPaperUrl(r52);
        L12:
            if (TextUtils.isEmpty(r53) == true) goto L17;
            if (r2.length() <= 0) goto L16;
            r2.append('\n');
        L16:
            r2.append(r53);
        L17:
            r4 = r4 + 1;
            goto L3
        L9:
            if ((r52 instanceof ColorWallpaper) == false) goto L17;
            r53 = ((ColorWallpaper) r52).getUrl();
            goto L12
        L18:
            WallpapersListActivity.m21256$$Nest$fgetselectedWallPapers(r6).clear();
            WallpapersListActivity.access$200(this.this$0).hideActionMode();
            WallpapersListActivity.access$300(this.this$0).closeSearchField();
            if (r24.size() > 1) goto L37;
            if (((MessagesStorage.TopicKey) r24.get(0)).dialogId == UserConfig.getInstance(WallpapersListActivity.access$400(this.this$0)).getClientUserId()) goto L37;
            if (r25 != null) goto L37;
            long r7 = ((MessagesStorage.TopicKey) r24.get(0)).dialogId;
            Bundle r1 = new Bundle();
            r1.putBoolean("scrollToTopOnResume", true);
            if (DialogObject.isEncryptedDialog(r7) == false) goto L28;
            r1.putInt("enc_id", DialogObject.getEncryptedChatId(r7));
        L36:
            NotificationCenter.getInstance(WallpapersListActivity.access$800(this.this$0)).postNotificationName(NotificationCenter.closeChats, new Object[0]);
            this.this$0.presentFragment(new ChatActivity(r1), true);
            SendMessagesHelper.getInstance(WallpapersListActivity.access$900(this.this$0)).sendMessage(SendMessagesHelper.SendMessageParams.of(r2.toString(), r7, null, null, null, true, null, null, null, true, 0, 0, null, false));
        L48:
            return true;
        L28:
            if (DialogObject.isUserDialog(r7) == false) goto L31;
            r1.putLong("user_id", r7);
        L34:
            if (MessagesController.getInstance(WallpapersListActivity.access$700(this.this$0)).checkCanOpenChat(r1, r23) == true) goto L36;
            return true;
        L31:
            if (DialogObject.isChatDialog(r7) == false) goto L34;
            r1.putLong("chat_id", -r7);
        L37:
            WallpapersListActivity.m21269$$Nest$mupdateRowsSelection(this.this$0);
        L39:
            if (r3 >= r24.size()) goto L47;
            long r8 = ((MessagesStorage.TopicKey) r24.get(r3)).dialogId;
            if (r25 == null) goto L44;
            SendMessagesHelper.getInstance(WallpapersListActivity.access$500(this.this$0)).sendMessage(SendMessagesHelper.SendMessageParams.of(r25.toString(), r8, null, null, null, true, null, null, null, true, 0, 0, null, false));
        L44:
            if (TextUtils.isEmpty(r2) == true) goto L46;
            SendMessagesHelper.getInstance(WallpapersListActivity.access$600(this.this$0)).sendMessage(SendMessagesHelper.SendMessageParams.of(r2.toString(), r8, null, null, null, true, null, null, null, true, 0, 0, null, false));
        L46:
            r3 = r3 + 1;
            goto L39
        L47:
            r23.finishFragment();
            goto L48
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r6) {
            if (r6 != (-1)) goto L11;
            boolean r62 = WallpapersListActivity.access$000(this.this$0).isActionModeShowed();
            WallpapersListActivity r0 = this.this$0;
            if (r62 == false) goto L8;
            WallpapersListActivity.m21256$$Nest$fgetselectedWallPapers(r0).clear();
            WallpapersListActivity.access$100(this.this$0).hideActionMode();
            WallpapersListActivity.m21269$$Nest$mupdateRowsSelection(this.this$0);
            return;
        L8:
            r0.finishFragment();
            return;
        L11:
            if (r6 != 4) goto L20;
            if (this.this$0.getParentActivity() == null) goto L25;
            AlertDialog.Builder r63 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r63.setTitle(LocaleController.formatPluralString("DeleteBackground", WallpapersListActivity.m21256$$Nest$fgetselectedWallPapers(this.this$0).size(), new Object[0]));
            r63.setMessage(LocaleController.formatString("DeleteChatBackgroundsAlert", R.string.DeleteChatBackgroundsAlert, new Object[0]));
            r63.setPositiveButton(LocaleController.getString(R.string.Delete), new WallpapersListActivity$2$$ExternalSyntheticLambda0(this));
            r63.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            AlertDialog r64 = r63.create();
            this.this$0.showDialog(r64);
            TextView r5 = (TextView) r64.getButton(-1);
            if (r5 == null) goto L23;
            r5.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        L23:
            return;
        L25:
            return;
        L20:
            if (r6 != 3) goto L24;
            Bundle r65 = new Bundle();
            r65.putBoolean("onlySelect", true);
            r65.putInt("dialogsType", 3);
            DialogsActivity r02 = new DialogsActivity(r65);
            r02.setDelegate(new WallpapersListActivity$2$$ExternalSyntheticLambda1(this));
            this.this$0.presentFragment(r02);
            return;
        }
    }

    /* renamed from: org.telegram.ui.WallpapersListActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ WallpapersListActivity this$0;

        public AnonymousClass3(WallpapersListActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onCaptionCleared() {
            WallpapersListActivity.m21245$$Nest$fgetsearchAdapter(this.this$0).clearColor();
            WallpapersListActivity.m21247$$Nest$fgetsearchItem(this.this$0).setSearchFieldHint(LocaleController.getString(R.string.SearchBackgrounds));
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            WallpapersListActivity.m21236$$Nest$fgetlistView(this.this$0).setAdapter(WallpapersListActivity.m21235$$Nest$fgetlistAdapter(this.this$0));
            WallpapersListActivity.m21236$$Nest$fgetlistView(this.this$0).invalidate();
            SearchAdapter.m21278$$Nest$mprocessSearch(WallpapersListActivity.m21245$$Nest$fgetsearchAdapter(this.this$0), null, true);
            WallpapersListActivity.m21247$$Nest$fgetsearchItem(this.this$0).setSearchFieldCaption(null);
            onCaptionCleared();
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            WallpapersListActivity.m21236$$Nest$fgetlistView(this.this$0).setAdapter(WallpapersListActivity.m21245$$Nest$fgetsearchAdapter(this.this$0));
            WallpapersListActivity.m21236$$Nest$fgetlistView(this.this$0).invalidate();
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r2) {
            SearchAdapter.m21278$$Nest$mprocessSearch(WallpapersListActivity.m21245$$Nest$fgetsearchAdapter(this.this$0), r2.getText().toString(), false);
        }
    }

    /* renamed from: org.telegram.ui.WallpapersListActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends LinearLayoutManager {
        final /* synthetic */ WallpapersListActivity this$0;

        public AnonymousClass4(WallpapersListActivity r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.WallpapersListActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ WallpapersListActivity this$0;

        public AnonymousClass5(WallpapersListActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r2, int r3) {
            boolean r22 = true;
            if (r3 != 1) goto L5;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
        L5:
            WallpapersListActivity r1 = this.this$0;
            if (r3 != 0) goto L9;
            r22 = false;
        L9:
            WallpapersListActivity.m21263$$Nest$fputscrolling(r1, r22);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (WallpapersListActivity.m21236$$Nest$fgetlistView(this.this$0).getAdapter() != WallpapersListActivity.m21245$$Nest$fgetsearchAdapter(this.this$0)) goto L15;
            int r12 = WallpapersListActivity.m21234$$Nest$fgetlayoutManager(this.this$0).findFirstVisibleItemPosition();
            if (r12 != (-1)) goto L7;
            int r22 = 0;
        L8:
            if (r22 <= 0) goto L16;
            int r32 = WallpapersListActivity.m21234$$Nest$fgetlayoutManager(this.this$0).getItemCount();
            if (r22 != 0) goto L12;
            return;
        L12:
            if ((r12 + r22) <= (r32 - 2)) goto L18;
            WallpapersListActivity.m21245$$Nest$fgetsearchAdapter(this.this$0).loadMoreResults();
            return;
        L18:
            return;
        L16:
            return;
        L7:
            r22 = Math.abs(WallpapersListActivity.m21234$$Nest$fgetlayoutManager(this.this$0).findLastVisibleItemPosition() - r12) + 1;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.WallpapersListActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends ThemePreviewActivity {
        final /* synthetic */ WallpapersListActivity this$0;

        public AnonymousClass6(WallpapersListActivity r1, Object r2, Bitmap r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.ThemePreviewActivity
        public boolean insideBottomSheet() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.WallpapersListActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ WallpapersListActivity this$0;

        public AnonymousClass7(WallpapersListActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            WallpapersListActivity.m21265$$Nest$mfixLayoutInternal(this.this$0);
            if (WallpapersListActivity.m21236$$Nest$fgetlistView(this.this$0) == null) goto L7;
            WallpapersListActivity.m21236$$Nest$fgetlistView(this.this$0).getViewTreeObserver().removeOnPreDrawListener(this);
            return true;
        L7:
            return true;
        }
    }

    public class ColorCell extends View {
        private int color;
        final /* synthetic */ WallpapersListActivity this$0;

        public ColorCell(WallpapersListActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            WallpapersListActivity.m21228$$Nest$fgetcolorPaint(this.this$0).setColor(this.color);
            r8.drawCircle(AndroidUtilities.dp(25.0f), AndroidUtilities.dp(31.0f), AndroidUtilities.dp(18.0f), WallpapersListActivity.m21228$$Nest$fgetcolorPaint(this.this$0));
            if (this.color != Theme.getColor(Theme.key_windowBackgroundWhite)) goto L6;
            r8.drawCircle(AndroidUtilities.dp(25.0f), AndroidUtilities.dp(31.0f), AndroidUtilities.dp(18.0f), WallpapersListActivity.m21227$$Nest$fgetcolorFramePaint(this.this$0));
            return;
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(AndroidUtilities.dp(50.0f), AndroidUtilities.dp(62.0f));
        }

        public void setColor(int r1) {
            this.color = r1;
        }
    }

    public static class ColorWallpaper {
        public int color;
        public Bitmap defaultCache;
        public int gradientColor1;
        public int gradientColor2;
        public int gradientColor3;
        public int gradientRotation;
        public float intensity;
        public boolean isGradient;
        public boolean motion;
        public TLRPC.WallPaper parentWallpaper;
        public File path;
        public TLRPC.TL_wallPaper pattern;
        public long patternId;
        public String slug;

        public ColorWallpaper(String r1, int r2, int r3, int r4) {
            this.slug = r1;
            this.color = r2 | (-16777216);
            if (r3 != 0) goto L5;
            int r12 = 0;
        L6:
            this.gradientColor1 = r12;
            if (r12 != 0) goto L10;
            r4 = 0;
        L10:
            this.gradientRotation = r4;
            this.intensity = 1.0f;
            return;
        L5:
            r12 = (-16777216) | r3;
            goto L6
        }

        public ColorWallpaper(String r1, int r2, int r3, int r4, int r5) {
            this.slug = r1;
            this.color = r2 | (-16777216);
            int r22 = 0;
            if (r3 != 0) goto L5;
            int r32 = 0;
        L6:
            this.gradientColor1 = r32;
            if (r4 != 0) goto L9;
            int r33 = 0;
        L10:
            this.gradientColor2 = r33;
            if (r5 == 0) goto L14;
            r22 = r5 | (-16777216);
        L14:
            this.gradientColor3 = r22;
            this.intensity = 1.0f;
            this.isGradient = true;
            return;
        L9:
            r33 = r4 | (-16777216);
            goto L10
        L5:
            r32 = r3 | (-16777216);
            goto L6
        }

        public ColorWallpaper(String r1, int r2, int r3, int r4, int r5, int r6, float r7, boolean r8, File r9) {
            this.slug = r1;
            this.color = r2 | (-16777216);
            int r22 = 0;
            if (r3 != 0) goto L5;
            int r32 = 0;
        L6:
            this.gradientColor1 = r32;
            if (r4 != 0) goto L9;
            int r42 = 0;
        L10:
            this.gradientColor2 = r42;
            if (r5 == 0) goto L14;
            r22 = r5 | (-16777216);
        L14:
            this.gradientColor3 = r22;
            if (r32 != 0) goto L18;
            r6 = 45;
        L18:
            this.gradientRotation = r6;
            this.intensity = r7;
            this.path = r9;
            this.motion = r8;
            return;
        L9:
            r42 = r4 | (-16777216);
            goto L10
        L5:
            r32 = r3 | (-16777216);
            goto L6
        }

        public String getHash() {
            StringBuilder r0 = new StringBuilder();
            r0.append(String.valueOf(this.color));
            r0.append(this.gradientColor1);
            r0.append(this.gradientColor2);
            r0.append(this.gradientColor3);
            r0.append(this.gradientRotation);
            r0.append(this.intensity);
            String r2 = this.slug;
            if (r2 != null) goto L6;
            r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            r0.append(r2);
            return Utilities.MD5(r0.toString());
        }

        public String getUrl() {
            int r0 = this.gradientColor1;
            String r1 = null;
            if (r0 == 0) goto L5;
            String r02 = String.format("%02x%02x%02x", new Object[]{Integer.valueOf(((byte) (r0 >> 16)) & UByte.MAX_VALUE), Integer.valueOf(((byte) (this.gradientColor1 >> 8)) & UByte.MAX_VALUE), Byte.valueOf((byte) (this.gradientColor1 & 255))}).toLowerCase();
        L6:
            String r3 = String.format("%02x%02x%02x", new Object[]{Integer.valueOf(((byte) (this.color >> 16)) & UByte.MAX_VALUE), Integer.valueOf(((byte) (this.color >> 8)) & UByte.MAX_VALUE), Byte.valueOf((byte) (this.color & 255))}).toLowerCase();
            int r4 = this.gradientColor2;
            if (r4 == 0) goto L9;
            String r42 = String.format("%02x%02x%02x", new Object[]{Integer.valueOf(((byte) (r4 >> 16)) & UByte.MAX_VALUE), Integer.valueOf(((byte) (this.gradientColor2 >> 8)) & UByte.MAX_VALUE), Byte.valueOf((byte) (this.gradientColor2 & 255))}).toLowerCase();
        L10:
            int r5 = this.gradientColor3;
            if (r5 == 0) goto L13;
            r1 = String.format("%02x%02x%02x", new Object[]{Integer.valueOf(((byte) (r5 >> 16)) & UByte.MAX_VALUE), Integer.valueOf(((byte) (this.gradientColor3 >> 8)) & UByte.MAX_VALUE), Byte.valueOf((byte) (this.gradientColor3 & 255))}).toLowerCase();
        L13:
            if (r02 == null) goto L19;
            if (r42 == null) goto L19;
            if (r1 == null) goto L18;
            r3 = r3 + "~" + r02 + "~" + r42 + "~" + r1;
        L25:
            if (this.pattern == null) goto L32;
            String r03 = "https://" + MessagesController.getInstance(UserConfig.selectedAccount).linkPrefix + "/bg/" + this.pattern.slug + "?intensity=" + ((int) (this.intensity * 100.0f)) + "&bg_color=" + r3;
            if (this.motion == true) goto L29;
            return r03;
        L29:
            return r03.concat("&mode=motion");
        L32:
            return "https://" + MessagesController.getInstance(UserConfig.selectedAccount).linkPrefix + "/bg/" + r3;
        L18:
            r3 = r3 + "~" + r02 + "~" + r42;
        L19:
            if (r02 == null) goto L25;
            String r04 = r3 + "-" + r02;
            if (this.pattern == null) goto L23;
            r3 = r04 + "&rotation=" + AndroidUtilities.getWallpaperRotation(this.gradientRotation, true);
            goto L25
        L23:
            r3 = r04 + "?rotation=" + AndroidUtilities.getWallpaperRotation(this.gradientRotation, true);
            goto L25
        L9:
            r42 = null;
            goto L10
        L5:
            r02 = null;
            goto L6
        }
    }

    public static class FileWallpaper {
        public File originalPath;
        public File path;
        public int resId;
        public String slug;
        public int thumbResId;

        public FileWallpaper(String r1, int r2, int r3) {
            this.slug = r1;
            this.resId = r2;
            this.thumbResId = r3;
        }

        public FileWallpaper(String r1, File r2, File r3) {
            this.slug = r1;
            this.path = r2;
            this.originalPath = r3;
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ WallpapersListActivity this$0;

        /* renamed from: org.telegram.ui.WallpapersListActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends WallpaperCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass1(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Cells.WallpaperCell
            public void onWallpaperClick(Object r2, int r3) {
                WallpapersListActivity.m21267$$Nest$monItemClick(this.this$1.this$0, this, r2, r3);
            }

            @Override // org.telegram.ui.Cells.WallpaperCell
            public boolean onWallpaperLongClick(Object r2, int r3) {
                return WallpapersListActivity.m21268$$Nest$monItemLongClick(this.this$1.this$0, this, r2, r3);
            }
        }

        public ListAdapter(WallpapersListActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return WallpapersListActivity.m21243$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != WallpapersListActivity.m21259$$Nest$fgetuploadImageRow(this.this$0)) goto L5;
            return 0;
        L5:
            if (r2 != WallpapersListActivity.m21233$$Nest$fgetgalleryRow(this.this$0)) goto L7;
            return 0;
        L7:
            if (r2 != WallpapersListActivity.m21257$$Nest$fgetsetColorRow(this.this$0)) goto L9;
            return 0;
        L9:
            if (r2 != WallpapersListActivity.m21241$$Nest$fgetresetRow(this.this$0)) goto L12;
            return 0;
        L12:
            if (r2 != WallpapersListActivity.m21248$$Nest$fgetsectionRow(this.this$0)) goto L14;
            return 1;
        L14:
            if (r2 != WallpapersListActivity.m21242$$Nest$fgetresetSectionRow(this.this$0)) goto L17;
            return 1;
        L17:
            if (r2 != WallpapersListActivity.m21240$$Nest$fgetresetInfoRow(this.this$0)) goto L19;
            return 3;
        L19:
            if (r2 == WallpapersListActivity.m21232$$Nest$fgetgalleryHintRow(this.this$0)) goto L29;
            return 2;
        L29:
            return 3;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r13, int r14) {
            int r0 = r13.getItemViewType();
            if (r0 != 0) goto L5;
            TextCell r132 = (TextCell) r13.itemView;
            if (r14 != WallpapersListActivity.m21259$$Nest$fgetuploadImageRow(this.this$0)) goto L118;
            r132.setTextAndIcon(LocaleController.getString(R.string.SelectFromGallery), R.drawable.msg_photos, true);
            return;
        L118:
            if (r14 != WallpapersListActivity.m21257$$Nest$fgetsetColorRow(this.this$0)) goto L122;
            r132.setTextAndIcon(LocaleController.getString(R.string.SetColor), R.drawable.msg_palette, true);
            return;
        L122:
            if (r14 != WallpapersListActivity.m21241$$Nest$fgetresetRow(this.this$0)) goto L126;
            r132.setText(LocaleController.getString(R.string.ResetChatBackgrounds), false);
            return;
        L126:
            if (r14 != WallpapersListActivity.m21233$$Nest$fgetgalleryRow(this.this$0)) goto L133;
            r132.setTextAndIcon("Choose from gallery", R.drawable.msg_background, false);
            r132.setLockLevel(false, 10);
            return;
        L133:
            return;
        L5:
            if (r0 != 2) goto L7;
            WallpaperCell r3 = (WallpaperCell) r13.itemView;
            int r142 = (r14 - WallpapersListActivity.m21260$$Nest$fgetwallPaperStartRow(this.this$0)) * WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0);
            int r133 = WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0);
            if (r142 != 0) goto L20;
            boolean r02 = true;
        L22:
            if ((r142 / WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0)) != (WallpapersListActivity.m21258$$Nest$fgettotalWallpaperRows(this.this$0) - 1)) goto L24;
            boolean r4 = true;
        L25:
            r3.setParams(r133, r02, r4);
            int r5 = 0;
        L27:
            if (r5 >= WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0)) goto L135;
            int r134 = r142 + r5;
            Object r42 = null;
            if (r134 >= WallpapersListActivity.m21261$$Nest$fgetwallPapers(this.this$0).size()) goto L31;
            Object r6 = WallpapersListActivity.m21261$$Nest$fgetwallPapers(this.this$0).get(r134);
        L33:
            if ((r6 instanceof TLRPC.TL_wallPaper) == false) goto L62;
            TLRPC.TL_wallPaper r135 = (TLRPC.TL_wallPaper) r6;
            Theme.OverrideWallpaperInfo r7 = Theme.getActiveTheme().overrideWallpaper;
            if (WallpapersListActivity.m21249$$Nest$fgetselectedBackgroundSlug(this.this$0).equals(r135.slug) == true) goto L37;
        L60:
            long r72 = r135.id;
        L61:
            long r10 = r72;
            r3.setWallpaper(WallpapersListActivity.m21230$$Nest$fgetcurrentType(this.this$0), r5, r6, r42, null, false);
            boolean r136 = WallpapersListActivity.access$2600(this.this$0).isActionModeShowed();
            WallpapersListActivity r03 = this.this$0;
            if (r136 == true) goto L107;
            r3.setChecked(r5, false, !WallpapersListActivity.m21244$$Nest$fgetscrolling(r03));
        L112:
            r5 = r5 + 1;
            goto L27
        L107:
            if (WallpapersListActivity.m21256$$Nest$fgetselectedWallPapers(r03).indexOfKey(r10) < 0) goto L109;
            boolean r137 = true;
        L110:
            r3.setChecked(r5, r137, !WallpapersListActivity.m21244$$Nest$fgetscrolling(this.this$0));
            goto L112
        L109:
            r137 = false;
            goto L110
        L37:
            if (WallpapersListActivity.m21249$$Nest$fgetselectedBackgroundSlug(this.this$0).equals(r135.slug) == true) goto L39;
        L59:
            r42 = r135;
            goto L60
        L39:
            if (r135.settings == null) goto L59;
            if (WallpapersListActivity.m21250$$Nest$fgetselectedColor(this.this$0) != Theme.getWallpaperColor(r135.settings.background_color)) goto L60;
            if (WallpapersListActivity.m21251$$Nest$fgetselectedGradientColor1(this.this$0) != Theme.getWallpaperColor(r135.settings.second_background_color)) goto L60;
            if (WallpapersListActivity.m21252$$Nest$fgetselectedGradientColor2(this.this$0) != Theme.getWallpaperColor(r135.settings.third_background_color)) goto L60;
            if (WallpapersListActivity.m21253$$Nest$fgetselectedGradientColor3(this.this$0) != Theme.getWallpaperColor(r135.settings.fourth_background_color)) goto L60;
            if (WallpapersListActivity.m21251$$Nest$fgetselectedGradientColor1(this.this$0) == 0) goto L59;
            if (WallpapersListActivity.m21252$$Nest$fgetselectedGradientColor2(this.this$0) != 0) goto L59;
            if (WallpapersListActivity.m21254$$Nest$fgetselectedGradientRotation(this.this$0) == AndroidUtilities.getWallpaperRotation(r135.settings.rotation, false)) goto L59;
            if (r135.pattern == false) goto L59;
            if (Math.abs(Theme.getThemeIntensity(r135.settings.intensity / 100.0f) - WallpapersListActivity.m21255$$Nest$fgetselectedIntensity(this.this$0)) <= 0.001f) goto L59;
        L62:
            r72 = 0;
            if ((r6 instanceof ColorWallpaper) == false) goto L100;
            ColorWallpaper r138 = (ColorWallpaper) r6;
            if ("d".equals(r138.slug) == false) goto L72;
            if (WallpapersListActivity.m21249$$Nest$fgetselectedBackgroundSlug(this.this$0) == null) goto L72;
            if (WallpapersListActivity.m21249$$Nest$fgetselectedBackgroundSlug(this.this$0).equals(r138.slug) == false) goto L72;
        L95:
            r42 = r6;
        L96:
            TLRPC.WallPaper r139 = r138.parentWallpaper;
            if (r139 == null) goto L61;
            r72 = r139.id;
        L72:
            if (r138.color != WallpapersListActivity.m21250$$Nest$fgetselectedColor(this.this$0)) goto L96;
            if (r138.gradientColor1 != WallpapersListActivity.m21251$$Nest$fgetselectedGradientColor1(this.this$0)) goto L96;
            if (r138.gradientColor2 != WallpapersListActivity.m21252$$Nest$fgetselectedGradientColor2(this.this$0)) goto L96;
            if (r138.gradientColor3 != WallpapersListActivity.m21253$$Nest$fgetselectedGradientColor3(this.this$0)) goto L96;
            if (WallpapersListActivity.m21251$$Nest$fgetselectedGradientColor1(this.this$0) == 0) goto L85;
            if (r138.gradientRotation != WallpapersListActivity.m21254$$Nest$fgetselectedGradientRotation(this.this$0)) goto L96;
        L85:
            if ("c".equals(WallpapersListActivity.m21249$$Nest$fgetselectedBackgroundSlug(this.this$0)) == false) goto L89;
            if (r138.slug != null) goto L96;
        L89:
            if ("c".equals(WallpapersListActivity.m21249$$Nest$fgetselectedBackgroundSlug(this.this$0)) == true) goto L95;
            if (TextUtils.equals(WallpapersListActivity.m21249$$Nest$fgetselectedBackgroundSlug(this.this$0), r138.slug) == false) goto L96;
            if (((int) (r138.intensity * 100.0f)) == ((int) (WallpapersListActivity.m21255$$Nest$fgetselectedIntensity(this.this$0) * 100.0f))) goto L95;
        L100:
            if ((r6 instanceof FileWallpaper) == false) goto L61;
            if (WallpapersListActivity.m21249$$Nest$fgetselectedBackgroundSlug(this.this$0).equals(((FileWallpaper) r6).slug) == false) goto L61;
            r42 = r6;
            goto L61
        L31:
            r6 = null;
            goto L33
        L135:
            return;
        L24:
            r4 = false;
            goto L25
        L20:
            r02 = false;
            goto L22
        L7:
            if (r0 != 3) goto L134;
            TextInfoPrivacyCell r1310 = (TextInfoPrivacyCell) r13.itemView;
            if (r14 != WallpapersListActivity.m21240$$Nest$fgetresetInfoRow(this.this$0)) goto L14;
            r1310.setText(LocaleController.getString(R.string.ResetChatBackgroundsInfo));
            return;
        L14:
            if (r14 != WallpapersListActivity.m21232$$Nest$fgetgalleryHintRow(this.this$0)) goto L132;
            r1310.setText("Upload your own background for the channel.");
            return;
        L132:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            if (r2 != 0) goto L4;
            View r12 = new TextCell(this.mContext);
        L12:
            return new RecyclerListView.Holder(r12);
        L4:
            if (r2 != 1) goto L6;
            r12 = new ShadowSectionCell(this.mContext);
            goto L12
        L6:
            if (r2 == 3) goto L8;
            r12 = new AnonymousClass1(this, this.mContext);
            goto L12
        L8:
            r12 = new TextInfoPrivacyCell(this.mContext);
            goto L12
        }
    }

    public class SearchAdapter extends RecyclerListView.SelectionAdapter {
        private boolean bingSearchEndReached;
        private int imageReqId;
        private RecyclerListView innerListView;
        private String lastSearchImageString;
        private String lastSearchString;
        private int lastSearchToken;
        private Context mContext;
        private String nextImagesSearchOffset;
        private ArrayList<MediaController.SearchImage> searchResult;
        private HashMap<String, MediaController.SearchImage> searchResultKeys;
        private Runnable searchRunnable;
        private boolean searchingUser;
        private String selectedColor;
        final /* synthetic */ WallpapersListActivity this$0;

        /* renamed from: org.telegram.ui.WallpapersListActivity$SearchAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends WallpaperCell {
            final /* synthetic */ SearchAdapter this$1;

            public AnonymousClass1(SearchAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Cells.WallpaperCell
            public void onWallpaperClick(Object r4, int r5) {
                this.this$1.this$0.presentFragment(new ThemePreviewActivity(r4, null, true, false));
            }
        }

        /* renamed from: org.telegram.ui.WallpapersListActivity$SearchAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerListView {
            final /* synthetic */ SearchAdapter this$1;

            public AnonymousClass2(SearchAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
            public boolean onInterceptTouchEvent(MotionEvent r3) {
                if (getParent() == null) goto L8;
                if (getParent().getParent() == null) goto L8;
                getParent().getParent().requestDisallowInterceptTouchEvent(canScrollHorizontally(-1));
            L8:
                return super.onInterceptTouchEvent(r3);
            }
        }

        /* renamed from: org.telegram.ui.WallpapersListActivity$SearchAdapter$3, reason: invalid class name */
        public class AnonymousClass3 extends LinearLayoutManager {
            final /* synthetic */ SearchAdapter this$1;

            public AnonymousClass3(SearchAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        }

        public class CategoryAdapterRecycler extends RecyclerListView.SelectionAdapter {
            final /* synthetic */ SearchAdapter this$1;

            private CategoryAdapterRecycler(SearchAdapter r1) {
                this.this$1 = r1;
            }

            public /* synthetic */ CategoryAdapterRecycler(SearchAdapter r1, WallpapersListActivityIA r2) {
                this(r1);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return WallpapersListActivity.m21270$$Nest$sfgetsearchColors().length;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r1) {
                return true;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
                ((ColorCell) r1.itemView).setColor(WallpapersListActivity.m21270$$Nest$sfgetsearchColors()[r2]);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
                SearchAdapter r0 = this.this$1;
                return new RecyclerListView.Holder(new ColorCell(r0.this$0, SearchAdapter.m21277$$Nest$fgetmContext(r0)));
            }
        }

        public static /* synthetic */ void $r8$lambda$0zPsTsnUoZ9hmfGN9af7sz3lCDQ(SearchAdapter r0, View r1, int r2) {
            r0.lambda$onCreateViewHolder$5(r1, r2);
        }

        /* renamed from: $r8$lambda$80FvRyzXz-NDNGk6oTxT7t5Cs5g, reason: not valid java name */
        public static /* synthetic */ void m21274$r8$lambda$80FvRyzXzNDNGk6oTxT7t5Cs5g(SearchAdapter r0, TLObject r1) {
            r0.lambda$searchBotUser$1(r1);
        }

        /* renamed from: $r8$lambda$8_U25fR_0HbfS3R3Ws5-SDZPQb8, reason: not valid java name */
        public static /* synthetic */ void m21275$r8$lambda$8_U25fR_0HbfS3R3Ws5SDZPQb8(SearchAdapter r0, String r1) {
            r0.lambda$processSearch$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$lsSoGABYArClyqOPMsjofX2XNJo(SearchAdapter r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$searchBotUser$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$pgvg5cTVAo5z42iunNDjIINASFM(SearchAdapter r0, int r1, TLObject r2) {
            r0.lambda$searchImages$3(r1, r2);
        }

        /* renamed from: $r8$lambda$t8-zW4MxoxMYm-ZgVqBKpIhRYBs, reason: not valid java name */
        public static /* synthetic */ void m21276$r8$lambda$t8zW4MxoxMYmZgVqBKpIhRYBs(SearchAdapter r0, int r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$searchImages$4(r1, r2, r3);
        }

        /* renamed from: -$$Nest$fgetmContext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Context m21277$$Nest$fgetmContext(SearchAdapter r0) {
            return r0.mContext;
        }

        /* renamed from: -$$Nest$mprocessSearch, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21278$$Nest$mprocessSearch(SearchAdapter r0, String r1, boolean r2) {
            r0.processSearch(r1, r2);
        }

        public SearchAdapter(WallpapersListActivity r1, Context r2) {
            this.this$0 = r1;
            this.searchResult = new ArrayList();
            this.searchResultKeys = new HashMap();
            this.bingSearchEndReached = true;
            this.mContext = r2;
        }

        private void doSearch(String r3) {
            this.searchResult.clear();
            this.searchResultKeys.clear();
            this.bingSearchEndReached = true;
            searchImages(r3, _UrlKt.FRAGMENT_ENCODE_SET, true);
            this.lastSearchString = r3;
            notifyDataSetChanged();
        }

        private /* synthetic */ void lambda$onCreateViewHolder$5(View r5, int r6) {
            String r52 = LocaleController.getString(R.string.BackgroundSearchColor);
            SpannableString r0 = new SpannableString(r52 + " " + LocaleController.getString(WallpapersListActivity.m21271$$Nest$sfgetsearchColorsNames()[r6], WallpapersListActivity.m21272$$Nest$sfgetsearchColorsNamesR()[r6]));
            r0.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_actionBarDefaultSubtitle)), r52.length(), r0.length(), 33);
            WallpapersListActivity.m21247$$Nest$fgetsearchItem(this.this$0).setSearchFieldCaption(r0);
            WallpapersListActivity.m21247$$Nest$fgetsearchItem(this.this$0).setSearchFieldHint(null);
            WallpapersListActivity.m21247$$Nest$fgetsearchItem(this.this$0).setSearchFieldText(_UrlKt.FRAGMENT_ENCODE_SET, true);
            this.selectedColor = WallpapersListActivity.m21271$$Nest$sfgetsearchColorsNames()[r6];
            processSearch(_UrlKt.FRAGMENT_ENCODE_SET, true);
        }

        private /* synthetic */ void lambda$processSearch$0(String r1) {
            doSearch(r1);
            this.searchRunnable = null;
        }

        private /* synthetic */ void lambda$searchBotUser$1(TLObject r5) {
            TLRPC.TL_contacts_resolvedPeer r52 = (TLRPC.TL_contacts_resolvedPeer) r5;
            MessagesController.getInstance(WallpapersListActivity.access$2400(this.this$0)).putUsers(r52.users, false);
            MessagesController.getInstance(WallpapersListActivity.access$2500(this.this$0)).putChats(r52.chats, false);
            this.this$0.getMessagesStorage().putUsersAndChats(r52.users, r52.chats, true, true);
            String r53 = this.lastSearchImageString;
            this.lastSearchImageString = null;
            searchImages(r53, _UrlKt.FRAGMENT_ENCODE_SET, false);
        }

        private /* synthetic */ void lambda$searchBotUser$2(TLObject r1, TLRPC.TL_error r2) {
            if (r1 == null) goto L5;
            AndroidUtilities.runOnUIThread(new WallpapersListActivity$SearchAdapter$$ExternalSyntheticLambda5(this, r1));
            return;
        }

        private /* synthetic */ void lambda$searchImages$3(int r10, TLObject r11) {
            if (r10 == this.lastSearchToken) goto L5;
            return;
        L5:
            boolean r102 = false;
            this.imageReqId = 0;
            int r0 = this.searchResult.size();
            if (r11 == null) goto L45;
            TLRPC.messages_BotResults r112 = (TLRPC.messages_BotResults) r11;
            this.nextImagesSearchOffset = r112.next_offset;
            int r2 = r112.results.size();
            int r3 = 0;
        L8:
            if (r3 >= r2) goto L39;
            TLRPC.BotInlineResult r4 = r112.results.get(r3);
            if ("photo".equals(r4.type) == false) goto L37;
            if (this.searchResultKeys.containsKey(r4.id) == true) goto L37;
            MediaController.SearchImage r5 = new MediaController.SearchImage();
            TLRPC.Photo r6 = r4.photo;
            if (r6 == null) goto L22;
            TLRPC.PhotoSize r62 = FileLoader.getClosestPhotoSizeWithSize(r6.sizes, AndroidUtilities.getPhotoSize());
            TLRPC.PhotoSize r7 = FileLoader.getClosestPhotoSizeWithSize(r4.photo.sizes, 320);
            if (r62 == null) goto L37;
            r5.width = r62.w;
            r5.height = r62.h;
            r5.photoSize = r62;
            r5.photo = r4.photo;
            r5.size = r62.size;
            r5.thumbPhotoSize = r7;
        L36:
            r5.id = r4.id;
            r5.type = 0;
            this.searchResult.add(r5);
            this.searchResultKeys.put(r5.id, r5);
            goto L37
        L22:
            if (r4.content == null) goto L37;
            int r63 = 0;
        L26:
            if (r63 >= r4.content.attributes.size()) goto L31;
            TLRPC.DocumentAttribute r72 = r4.content.attributes.get(r63);
            if ((r72 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L29;
            r63 = r63 + 1;
            goto L26
        L29:
            r5.width = r72.w;
            r5.height = r72.h;
        L31:
            TLRPC.WebDocument r64 = r4.thumb;
            if (r64 == null) goto L34;
            r5.thumbUrl = r64.url;
        L35:
            TLRPC.WebDocument r65 = r4.content;
            r5.imageUrl = r65.url;
            r5.size = r65.size;
            goto L36
        L34:
            r5.thumbUrl = null;
        L37:
            r3 = r3 + 1;
            goto L8
        L39:
            if (r0 != this.searchResult.size()) goto L41;
        L42:
            r102 = true;
        L43:
            this.bingSearchEndReached = r102;
            goto L45
        L41:
            if (this.nextImagesSearchOffset != null) goto L43;
        L45:
            if (r0 == this.searchResult.size()) goto L50;
            int r103 = r0 % WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0);
            float r113 = r0;
            int r02 = (int) Math.ceil(r113 / WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0));
            if (r103 == 0) goto L49;
            notifyItemChanged(((int) Math.ceil(r113 / WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0))) - 1);
        L49:
            WallpapersListActivity.m21245$$Nest$fgetsearchAdapter(this.this$0).notifyItemRangeInserted(r02, ((int) Math.ceil(this.searchResult.size() / WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0))) - r02);
        L50:
            WallpapersListActivity.m21246$$Nest$fgetsearchEmptyView(this.this$0).showTextView();
        }

        private /* synthetic */ void lambda$searchImages$4(int r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new WallpapersListActivity$SearchAdapter$$ExternalSyntheticLambda4(this, r1, r2));
        }

        private void processSearch(String r3, boolean r4) {
            if (r3 != null) goto L4;
        L6:
            Runnable r0 = this.searchRunnable;
            if (r0 == null) goto L10;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.searchRunnable = null;
        L10:
            if (TextUtils.isEmpty(r3) == false) goto L15;
            this.searchResult.clear();
            this.searchResultKeys.clear();
            this.bingSearchEndReached = true;
            this.lastSearchString = null;
            if (this.imageReqId == 0) goto L14;
            ConnectionsManager.getInstance(WallpapersListActivity.access$1400(this.this$0)).cancelRequest(this.imageReqId, true);
            this.imageReqId = 0;
        L14:
            WallpapersListActivity.m21246$$Nest$fgetsearchEmptyView(this.this$0).showTextView();
        L19:
            notifyDataSetChanged();
            return;
        L15:
            WallpapersListActivity.m21246$$Nest$fgetsearchEmptyView(this.this$0).showProgress();
            if (r4 == false) goto L18;
            doSearch(r3);
            goto L19
        L18:
            WallpapersListActivity$SearchAdapter$$ExternalSyntheticLambda1 r42 = new WallpapersListActivity$SearchAdapter$$ExternalSyntheticLambda1(this, r3);
            this.searchRunnable = r42;
            AndroidUtilities.runOnUIThread(r42, 500);
            goto L19
        L4:
            if (this.selectedColor == null) goto L6;
            r3 = "#color" + this.selectedColor + " " + r3;
            goto L6
        }

        private void searchBotUser() {
            if (this.searchingUser == false) goto L5;
            return;
        L5:
            this.searchingUser = true;
            TLRPC.TL_contacts_resolveUsername r0 = new TLRPC.TL_contacts_resolveUsername();
            r0.username = MessagesController.getInstance(WallpapersListActivity.access$1500(this.this$0)).imageSearchBot;
            ConnectionsManager.getInstance(WallpapersListActivity.access$1600(this.this$0)).sendRequest(r0, new WallpapersListActivity$SearchAdapter$$ExternalSyntheticLambda3(this));
        }

        private void searchImages(String r5, String r6, boolean r7) {
            if (this.imageReqId == 0) goto L5;
            ConnectionsManager.getInstance(WallpapersListActivity.access$1700(this.this$0)).cancelRequest(this.imageReqId, true);
            this.imageReqId = 0;
        L5:
            this.lastSearchImageString = r5;
            TLObject r0 = MessagesController.getInstance(WallpapersListActivity.access$1900(this.this$0)).getUserOrChat(MessagesController.getInstance(WallpapersListActivity.access$1800(this.this$0)).imageSearchBot);
            if ((r0 instanceof TLRPC.User) == true) goto L10;
            if (r7 == false) goto L12;
            searchBotUser();
            return;
        L12:
            return;
        L10:
            TLRPC.TL_messages_getInlineBotResults r72 = new TLRPC.TL_messages_getInlineBotResults();
            r72.query = "#wallpaper " + r5;
            r72.bot = MessagesController.getInstance(WallpapersListActivity.access$2000(this.this$0)).getInputUser((TLRPC.User) r0);
            r72.offset = r6;
            r72.peer = new TLRPC.TL_inputPeerEmpty();
            int r52 = this.lastSearchToken + 1;
            this.lastSearchToken = r52;
            this.imageReqId = ConnectionsManager.getInstance(WallpapersListActivity.access$2100(this.this$0)).sendRequest(r72, new WallpapersListActivity$SearchAdapter$$ExternalSyntheticLambda2(this, r52));
            ConnectionsManager.getInstance(WallpapersListActivity.access$2300(this.this$0)).bindRequestToGuid(this.imageReqId, WallpapersListActivity.access$2200(this.this$0));
        }

        public void clearColor() {
            this.selectedColor = null;
            processSearch(null, true);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (TextUtils.isEmpty(this.lastSearchString) == false) goto L7;
            return 2;
        L7:
            return (int) Math.ceil(this.searchResult.size() / WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (TextUtils.isEmpty(this.lastSearchString) == false) goto L9;
            if (r1 != 0) goto L7;
            return 2;
        L7:
            return 1;
        L9:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() == 2) goto L6;
            return true;
        L6:
            return false;
        }

        public void loadMoreResults() {
            if (this.bingSearchEndReached == false) goto L5;
            return;
        L5:
            if (this.imageReqId != 0) goto L10;
            searchImages(this.lastSearchString, this.nextImagesSearchOffset, true);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            int r0 = r8.getItemViewType();
            if (r0 != 0) goto L5;
            WallpaperCell r02 = (WallpaperCell) r8.itemView;
            int r92 = r9 * WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0);
            int r82 = (int) Math.ceil(this.searchResult.size() / WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0));
            int r1 = WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0);
            int r2 = 0;
            boolean r3 = true;
            if (r92 != 0) goto L12;
            boolean r4 = true;
        L14:
            if ((r92 / WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0)) == (r82 - 1)) goto L17;
            r3 = false;
        L17:
            r02.setParams(r1, r4, r3);
        L19:
            if (r2 >= WallpapersListActivity.m21229$$Nest$fgetcolumnsCount(this.this$0)) goto L28;
            int r83 = r92 + r2;
            if (r83 >= this.searchResult.size()) goto L24;
            MediaController.SearchImage r84 = this.searchResult.get(r83);
        L25:
            r02.setWallpaper(WallpapersListActivity.m21230$$Nest$fgetcurrentType(this.this$0), r2, r84, _UrlKt.FRAGMENT_ENCODE_SET, null, false);
            r2 = r2 + 1;
            goto L19
        L24:
            r84 = null;
            goto L25
        L28:
            return;
        L12:
            r4 = false;
            goto L14
        L5:
            if (r0 != 2) goto L26;
            ((GraySectionCell) r8.itemView).setText(LocaleController.getString(R.string.SearchByColor));
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            if (r8 == 0) goto L11;
            View r0 = null;
            if (r8 != 1) goto L7;
            AnonymousClass2 r1 = new AnonymousClass2(this, this.mContext);
            r1.setItemAnimator(null);
            r1.setLayoutAnimation(null);
            AnonymousClass3 r2 = new AnonymousClass3(this, this.mContext);
            r1.setPadding(AndroidUtilities.dp(7.0f), 0, AndroidUtilities.dp(7.0f), 0);
            r1.setClipToPadding(false);
            r2.setOrientation(0);
            r1.setLayoutManager(r2);
            r1.setAdapter(new CategoryAdapterRecycler(this, null));
            r1.setOnItemClickListener(new WallpapersListActivity$SearchAdapter$$ExternalSyntheticLambda0(this));
            this.innerListView = r1;
            r0 = r1;
        L13:
            if (r8 != 1) goto L15;
            r0.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(60.0f)));
        L17:
            return new RecyclerListView.Holder(r0);
        L15:
            r0.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            goto L17
        L7:
            if (r8 != 2) goto L13;
            r0 = new GraySectionCell(this.mContext);
            goto L13
        L11:
            r0 = new AnonymousClass1(this, this.mContext);
            goto L13
        }

        public void onDestroy() {
            if (this.imageReqId == 0) goto L6;
            ConnectionsManager.getInstance(WallpapersListActivity.access$1300(this.this$0)).cancelRequest(this.imageReqId, true);
            this.imageReqId = 0;
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$4n1CssYXHDB8i8s6r_RmGj0fQGc(WallpapersListActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createView$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Cy7qxb0M11VhDdbotIPZ86FfSNQ(WallpapersListActivity r0, TLRPC.WallPaper r1) {
        r0.lambda$onItemClick$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$HhDhuNg81zr1ggbDv1oVFo3Hzrw(WallpapersListActivity r0, View r1, int r2) {
        r0.lambda$createView$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Lb2_Uk2G4JrAzMeaCS6DQIh9uMY(WallpapersListActivity r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadWallpapers$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Oe8q29dPgOtF3VwoatJS4XXPLuQ(WallpapersListActivity r0) {
        r0.lambda$createView$1();
    }

    public static /* synthetic */ void $r8$lambda$QT7crivIVzXN44kNXEXc29Ly6Js(WallpapersListActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$3(r1, r2);
    }

    public static /* synthetic */ int $r8$lambda$hssq_IcYBPz9EZRhrmIu7jwiH3Y(WallpapersListActivity r0, long r1, String r3, boolean r4, Object r5, Object r6) {
        return r0.lambda$fillWallpapersWithCustom$9(r1, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$p41xIpHLfGQyp5LlhnHQSzty48w(WallpapersListActivity r0, TLObject r1, boolean r2) {
        r0.lambda$loadWallpapers$7(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$wfA44qyGaX_B4XNajFoxnQYt0RQ(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$yG7ynEQts-MnqEjMn7RNb6iIn_U, reason: not valid java name */
    public static /* synthetic */ void m21226$r8$lambda$yG7ynEQtsMnqEjMn7RNb6iIn_U() {
        PhotoViewer.getInstance().closePhoto(false, false);
    }

    /* renamed from: -$$Nest$fgetcolorFramePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m21227$$Nest$fgetcolorFramePaint(WallpapersListActivity r0) {
        return r0.colorFramePaint;
    }

    /* renamed from: -$$Nest$fgetcolorPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m21228$$Nest$fgetcolorPaint(WallpapersListActivity r0) {
        return r0.colorPaint;
    }

    /* renamed from: -$$Nest$fgetcolumnsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21229$$Nest$fgetcolumnsCount(WallpapersListActivity r0) {
        return r0.columnsCount;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21230$$Nest$fgetcurrentType(WallpapersListActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m21231$$Nest$fgetdialogId(WallpapersListActivity r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetgalleryHintRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21232$$Nest$fgetgalleryHintRow(WallpapersListActivity r0) {
        return r0.galleryHintRow;
    }

    /* renamed from: -$$Nest$fgetgalleryRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21233$$Nest$fgetgalleryRow(WallpapersListActivity r0) {
        return r0.galleryRow;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m21234$$Nest$fgetlayoutManager(WallpapersListActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m21235$$Nest$fgetlistAdapter(WallpapersListActivity r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m21236$$Nest$fgetlistView(WallpapersListActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlocalDict, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m21237$$Nest$fgetlocalDict(WallpapersListActivity r0) {
        return r0.localDict;
    }

    /* renamed from: -$$Nest$fgetlocalWallPapers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m21238$$Nest$fgetlocalWallPapers(WallpapersListActivity r0) {
        return r0.localWallPapers;
    }

    /* renamed from: -$$Nest$fgetprogressDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AlertDialog m21239$$Nest$fgetprogressDialog(WallpapersListActivity r0) {
        return r0.progressDialog;
    }

    /* renamed from: -$$Nest$fgetresetInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21240$$Nest$fgetresetInfoRow(WallpapersListActivity r0) {
        return r0.resetInfoRow;
    }

    /* renamed from: -$$Nest$fgetresetRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21241$$Nest$fgetresetRow(WallpapersListActivity r0) {
        return r0.resetRow;
    }

    /* renamed from: -$$Nest$fgetresetSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21242$$Nest$fgetresetSectionRow(WallpapersListActivity r0) {
        return r0.resetSectionRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21243$$Nest$fgetrowCount(WallpapersListActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetscrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21244$$Nest$fgetscrolling(WallpapersListActivity r0) {
        return r0.scrolling;
    }

    /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchAdapter m21245$$Nest$fgetsearchAdapter(WallpapersListActivity r0) {
        return r0.searchAdapter;
    }

    /* renamed from: -$$Nest$fgetsearchEmptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmptyTextProgressView m21246$$Nest$fgetsearchEmptyView(WallpapersListActivity r0) {
        return r0.searchEmptyView;
    }

    /* renamed from: -$$Nest$fgetsearchItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m21247$$Nest$fgetsearchItem(WallpapersListActivity r0) {
        return r0.searchItem;
    }

    /* renamed from: -$$Nest$fgetsectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21248$$Nest$fgetsectionRow(WallpapersListActivity r0) {
        return r0.sectionRow;
    }

    /* renamed from: -$$Nest$fgetselectedBackgroundSlug, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m21249$$Nest$fgetselectedBackgroundSlug(WallpapersListActivity r0) {
        return r0.selectedBackgroundSlug;
    }

    /* renamed from: -$$Nest$fgetselectedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21250$$Nest$fgetselectedColor(WallpapersListActivity r0) {
        return r0.selectedColor;
    }

    /* renamed from: -$$Nest$fgetselectedGradientColor1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21251$$Nest$fgetselectedGradientColor1(WallpapersListActivity r0) {
        return r0.selectedGradientColor1;
    }

    /* renamed from: -$$Nest$fgetselectedGradientColor2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21252$$Nest$fgetselectedGradientColor2(WallpapersListActivity r0) {
        return r0.selectedGradientColor2;
    }

    /* renamed from: -$$Nest$fgetselectedGradientColor3, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21253$$Nest$fgetselectedGradientColor3(WallpapersListActivity r0) {
        return r0.selectedGradientColor3;
    }

    /* renamed from: -$$Nest$fgetselectedGradientRotation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21254$$Nest$fgetselectedGradientRotation(WallpapersListActivity r0) {
        return r0.selectedGradientRotation;
    }

    /* renamed from: -$$Nest$fgetselectedIntensity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21255$$Nest$fgetselectedIntensity(WallpapersListActivity r0) {
        return r0.selectedIntensity;
    }

    /* renamed from: -$$Nest$fgetselectedWallPapers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m21256$$Nest$fgetselectedWallPapers(WallpapersListActivity r0) {
        return r0.selectedWallPapers;
    }

    /* renamed from: -$$Nest$fgetsetColorRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21257$$Nest$fgetsetColorRow(WallpapersListActivity r0) {
        return r0.setColorRow;
    }

    /* renamed from: -$$Nest$fgettotalWallpaperRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21258$$Nest$fgettotalWallpaperRows(WallpapersListActivity r0) {
        return r0.totalWallpaperRows;
    }

    /* renamed from: -$$Nest$fgetuploadImageRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21259$$Nest$fgetuploadImageRow(WallpapersListActivity r0) {
        return r0.uploadImageRow;
    }

    /* renamed from: -$$Nest$fgetwallPaperStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21260$$Nest$fgetwallPaperStartRow(WallpapersListActivity r0) {
        return r0.wallPaperStartRow;
    }

    /* renamed from: -$$Nest$fgetwallPapers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m21261$$Nest$fgetwallPapers(WallpapersListActivity r0) {
        return r0.wallPapers;
    }

    /* renamed from: -$$Nest$fputprogressDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21262$$Nest$fputprogressDialog(WallpapersListActivity r0, AlertDialog r1) {
        r0.progressDialog = r1;
    }

    /* renamed from: -$$Nest$fputscrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21263$$Nest$fputscrolling(WallpapersListActivity r0, boolean r1) {
        r0.scrolling = r1;
    }

    /* renamed from: -$$Nest$fputselectedBackgroundSlug, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21264$$Nest$fputselectedBackgroundSlug(WallpapersListActivity r0, String r1) {
        r0.selectedBackgroundSlug = r1;
    }

    /* renamed from: -$$Nest$mfixLayoutInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21265$$Nest$mfixLayoutInternal(WallpapersListActivity r0) {
        r0.fixLayoutInternal();
    }

    /* renamed from: -$$Nest$mloadWallpapers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21266$$Nest$mloadWallpapers(WallpapersListActivity r0, boolean r1) {
        r0.loadWallpapers(r1);
    }

    /* renamed from: -$$Nest$monItemClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21267$$Nest$monItemClick(WallpapersListActivity r0, WallpaperCell r1, Object r2, int r3) {
        r0.onItemClick(r1, r2, r3);
    }

    /* renamed from: -$$Nest$monItemLongClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21268$$Nest$monItemLongClick(WallpapersListActivity r0, WallpaperCell r1, Object r2, int r3) {
        return r0.onItemLongClick(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mupdateRowsSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21269$$Nest$mupdateRowsSelection(WallpapersListActivity r0) {
        r0.updateRowsSelection();
    }

    /* renamed from: -$$Nest$sfgetsearchColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m21270$$Nest$sfgetsearchColors() {
        return searchColors;
    }

    /* renamed from: -$$Nest$sfgetsearchColorsNames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String[] m21271$$Nest$sfgetsearchColorsNames() {
        return searchColorsNames;
    }

    /* renamed from: -$$Nest$sfgetsearchColorsNamesR, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m21272$$Nest$sfgetsearchColorsNamesR() {
        return searchColorsNamesR;
    }

    static {
        defaultColorsLight = new int[][]{new int[]{-2368069, -9722489, -2762611, -7817084}, new int[]{-7487253, -4599318, -3755537, -1320977}, new int[]{-6832405, -5117462, -3755537, -1067044}, new int[]{-7676942, -7827988, -1859606, -9986835}, new int[]{-5190165, -6311702, -4461867, -5053475}, new int[]{-2430264, -6114049, -1258497, -4594945}, new int[]{-2298990, -7347754, -9985038, -8006011}, new int[]{-1399954, -990074, -876865, -1523602}, new int[]{-15438, -1916673, -6222, -471346}, new int[]{-2891798}, new int[]{-5913125}, new int[]{-9463352}, new int[]{-2956375}, new int[]{-5974898}, new int[]{-8537234}, new int[]{-1647186}, new int[]{-2769263}, new int[]{-3431303}, new int[]{-1326919}, new int[]{-2054243}, new int[]{-3573648}, new int[]{-1328696}, new int[]{-2056777}, new int[]{-2984557}, new int[]{-2440467}, new int[]{-2906649}, new int[]{-4880430}, new int[]{-4013331}, new int[]{-5921305}, new int[]{-8421424}, new int[]{-4005139}, new int[]{-5908761}, new int[]{-8406320}, new int[]{-2702663}, new int[]{-6518654}, new int[]{-16777216}};
        defaultColorsDark = new int[][]{new int[]{-14797481, -15394250, -14924974, -14006975}, new int[]{-14867905, -14870478, -14997181, -15460815}, new int[]{-14666695, -15720408, -14861254, -15260107}, new int[]{-14932175, -15066075, -14208965, -15000799}, new int[]{-12968902, -14411460, -13029826, -15067598}, new int[]{-13885157, -12307670, -14542561, -12899018}, new int[]{-14797481, -15196106, -14924974, -15325638}, new int[]{-15658442, -15449521, -16047308, -12897955}, new int[]{-13809610, -15258855, -13221071, -15715791}, new int[]{-14865092}, new int[]{-15656154}, new int[]{-16051170}, new int[]{-14731745}, new int[]{-15524075}, new int[]{-15853808}, new int[]{-13685209}, new int[]{-14014945}, new int[]{-15132649}, new int[]{-12374480}, new int[]{-13755362}, new int[]{-14740716}, new int[]{-12374468}, new int[]{-13755352}, new int[]{-14740709}, new int[]{-12833213}, new int[]{-14083026}, new int[]{-14872031}, new int[]{-13554109}, new int[]{-14803922}, new int[]{-15461855}, new int[]{-13680833}, new int[]{-14602960}, new int[]{-15458784}, new int[]{-14211804}, new int[]{-15132906}, new int[]{-16777216}};
        searchColors = new int[]{-16746753, Opcodes.V_PREVIEW, -30208, -13824, -16718798, -14702165, -9240406, -409915, -9224159, -16777216, -10725281, -1};
        searchColorsNames = new String[]{"Blue", "Red", "Orange", "Yellow", "Green", "Teal", "Purple", "Pink", "Brown", "Black", "Gray", "White"};
        searchColorsNamesR = new int[]{R.string.Blue, R.string.Red, R.string.Orange, R.string.Yellow, R.string.Green, R.string.Teal, R.string.Purple, R.string.Pink, R.string.Brown, R.string.Black, R.string.Gray, R.string.White};
    }

    public WallpapersListActivity(int r3) {
        this(r3, 0);
    }

    public WallpapersListActivity(int r2, long r3) {
        this.actionModeViews = new ArrayList();
        this.columnsCount = 3;
        this.selectedBackgroundSlug = _UrlKt.FRAGMENT_ENCODE_SET;
        this.allWallPapers = new ArrayList();
        this.allWallPapersDict = new HashMap();
        this.localDict = new HashMap();
        this.wallPapers = new ArrayList();
        this.localWallPapers = new ArrayList();
        this.patterns = new ArrayList();
        this.patternsDict = new HashMap();
        this.selectedWallPapers = new LongSparseArray();
        this.currentType = r2;
        this.dialogId = r3;
    }

    public static /* synthetic */ ActionBar access$000(WallpapersListActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(WallpapersListActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$1000(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$1100(WallpapersListActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1200(WallpapersListActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$1300(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1400(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1500(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1600(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1700(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1800(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1900(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$200(WallpapersListActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$2000(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2100(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2200(WallpapersListActivity r0) {
        return r0.classGuid;
    }

    public static /* synthetic */ int access$2300(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2400(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2500(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$2600(WallpapersListActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(WallpapersListActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$400(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$600(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$700(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$800(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$900(WallpapersListActivity r0) {
        return r0.currentAccount;
    }

    public static void fillDefaultColors(ArrayList<Object> r12, boolean r13) {
        if (r13 == false) goto L4;
        int[][] r132 = defaultColorsDark;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r132.length) goto L13;
        int[] r2 = r132[r1];
        if (r2.length != 1) goto L11;
        r12.add(new ColorWallpaper("c", r2[0], 0, 45));
    L12:
        r1 = r1 + 1;
        goto L7
    L11:
        r12.add(new ColorWallpaper("c", r2[0], r2[1], r2[2], r2[3]));
        goto L12
    L13:
        return;
    L4:
        r132 = defaultColorsLight;
        goto L5
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10, types: [org.telegram.tgnet.TLRPC$TL_wallPaper, org.telegram.tgnet.TLRPC$WallPaper] */
    /* JADX WARN: Type inference failed for: r5v11, types: [org.telegram.ui.WallpapersListActivity$ColorWallpaper] */
    private void fillWallpapersWithCustom() {
        int r0 = this.currentType;
        if (r0 != 0) goto L5;
    L7:
        MessagesController.getGlobalMainSettings();
        ColorWallpaper r02 = this.addedColorWallpaper;
        if (r02 == null) goto L10;
        this.wallPapers.remove(r02);
        this.addedColorWallpaper = null;
    L10:
        FileWallpaper r03 = this.addedFileWallpaper;
        if (r03 == null) goto L13;
        this.wallPapers.remove(r03);
        this.addedFileWallpaper = null;
    L13:
        ColorWallpaper r04 = this.catsWallpaper;
        if (r04 != null) goto L16;
        ColorWallpaper r7 = new ColorWallpaper("d", -2368069, -9722489, -2762611, -7817084);
        this.catsWallpaper = r7;
        r7.intensity = 0.34f;
    L17:
        FileWallpaper r05 = this.themeWallpaper;
        if (r05 == null) goto L20;
        this.wallPapers.remove(r05);
    L20:
        int r06 = this.wallPapers.size();
        int r2 = 0;
    L22:
        if (r2 >= r06) goto L68;
        Object r5 = this.wallPapers.get(r2);
        if ((r5 instanceof ColorWallpaper) == false) goto L47;
        TLRPC.TL_wallPaper r52 = (ColorWallpaper) r5;
        String r9 = r52.slug;
        if (r9 == null) goto L29;
        r52.pattern = (TLRPC.TL_wallPaper) this.allWallPapersDict.get(r9);
    L29:
        if ("c".equals(r52.slug) == true) goto L35;
        String r92 = r52.slug;
        if (r92 == null) goto L35;
        if (TextUtils.equals(this.selectedBackgroundSlug, r92) == true) goto L35;
    L67:
        r2 = r2 + 1;
    L35:
        if (this.selectedColor != r52.color) goto L67;
        int r93 = this.selectedGradientColor1;
        if (r93 != r52.gradientColor1) goto L67;
        if (this.selectedGradientColor2 != r52.gradientColor2) goto L67;
        if (this.selectedGradientColor3 != r52.gradientColor3) goto L67;
        if (r93 == 0) goto L70;
        if (this.selectedGradientRotation != r52.gradientRotation) goto L67;
    L70:
        if ((r52 instanceof TLRPC.WallPaper) == false) goto L94;
        TLRPC.TL_wallPaper r07 = r52;
        Theme.OverrideWallpaperInfo r22 = Theme.getActiveTheme().overrideWallpaper;
        TLRPC.WallPaperSettings r23 = r07.settings;
        if (r23 == null) goto L92;
        if (r23 != null) goto L75;
    L91:
        String r24 = this.selectedBackgroundSlug;
        TLRPC.TL_wallPaper r3 = null;
        Object r53 = r52;
    L93:
        String r4 = r24;
        TLRPC.TL_wallPaper r94 = r3;
        Object r10 = r53;
        long r25 = r07.id;
    L169:
        Collections.sort(this.wallPapers, new WallpapersListActivity$$ExternalSyntheticLambda3(this, r25, r4, Theme.getCurrentTheme().isDark()));     // Catch: Exception -> L104
    L107:
        if (Theme.hasWallpaperFromTheme() == true) goto L109;
    L114:
        this.themeWallpaper = null;
    L115:
        Theme.ThemeInfo r08 = Theme.getActiveTheme();
        if (TextUtils.isEmpty(this.selectedBackgroundSlug) == true) goto L136;
        if ("d".equals(this.selectedBackgroundSlug) == true) goto L121;
        if (r10 == null) goto L136;
    L121:
        if (r10 != null) goto L161;
        if (this.selectedColor == 0) goto L161;
        if ("c".equals(this.selectedBackgroundSlug) == false) goto L161;
        int r11 = this.selectedGradientColor1;
        if (r11 == 0) goto L133;
        int r12 = this.selectedGradientColor2;
        if (r12 == 0) goto L133;
        int r13 = this.selectedGradientColor3;
        if (r13 == 0) goto L133;
        ColorWallpaper r8 = new ColorWallpaper(this.selectedBackgroundSlug, this.selectedColor, r11, r12, r13);
        this.addedColorWallpaper = r8;
        r8.gradientRotation = this.selectedGradientRotation;
    L134:
        this.wallPapers.add(0, this.addedColorWallpaper);
    L133:
        this.addedColorWallpaper = new ColorWallpaper(this.selectedBackgroundSlug, this.selectedColor, r11, this.selectedGradientRotation);
    L161:
        if ("d".equals(this.selectedBackgroundSlug) == false) goto L163;
    L166:
        this.wallPapers.add(0, this.catsWallpaper);
    L167:
        updateRows();
        return;
    L163:
        if (this.wallPapers.isEmpty() == true) goto L166;
        this.wallPapers.add(1, this.catsWallpaper);
    L136:
        if ("c".equals(this.selectedBackgroundSlug) == true) goto L142;
        int r122 = this.selectedColor;
        if (r122 == 0) goto L142;
        if (r08.overrideWallpaper == null) goto L161;
        ColorWallpaper r102 = new ColorWallpaper(this.selectedBackgroundSlug, r122, this.selectedGradientColor1, this.selectedGradientColor2, this.selectedGradientColor3, this.selectedGradientRotation, this.selectedIntensity, this.selectedBackgroundMotion, new File(ApplicationLoader.getFilesDirFixed(), r08.overrideWallpaper.fileName));
        this.addedColorWallpaper = r102;
        r102.pattern = r94;
        this.wallPapers.add(0, r102);
    L142:
        int r132 = this.selectedColor;
        if (r132 == 0) goto L152;
        int r14 = this.selectedGradientColor1;
        if (r14 == 0) goto L149;
        int r15 = this.selectedGradientColor2;
        if (r15 == 0) goto L149;
        ColorWallpaper r112 = new ColorWallpaper(this.selectedBackgroundSlug, r132, r14, r15, this.selectedGradientColor3);
        this.addedColorWallpaper = r112;
        r112.gradientRotation = this.selectedGradientRotation;
    L150:
        this.wallPapers.add(0, this.addedColorWallpaper);
    L149:
        this.addedColorWallpaper = new ColorWallpaper(this.selectedBackgroundSlug, r132, r14, this.selectedGradientRotation);
        goto L150
    L152:
        if (r08.overrideWallpaper == null) goto L161;
        if (this.allWallPapersDict.containsKey(this.selectedBackgroundSlug) == true) goto L161;
        FileWallpaper r26 = new FileWallpaper(this.selectedBackgroundSlug, new File(ApplicationLoader.getFilesDirFixed(), r08.overrideWallpaper.fileName), new File(ApplicationLoader.getFilesDirFixed(), r08.overrideWallpaper.originalFileName));
        this.addedFileWallpaper = r26;
        ArrayList<Object> r09 = this.wallPapers;
        if (this.themeWallpaper == null) goto L158;
        int r42 = 1;
    L159:
        r09.add(r42, r26);
        goto L161
    L158:
        r42 = 0;
        goto L159
    L109:
        if (Theme.isThemeWallpaperPublic() == true) goto L114;
        if (this.themeWallpaper != null) goto L113;
        this.themeWallpaper = new FileWallpaper("t", -2, -2);
    L113:
        this.wallPapers.add(0, this.themeWallpaper);
    L104:
        e = move-exception;
        FileLog.e(e);
        goto L107
    L75:
        if (this.selectedColor != Theme.getWallpaperColor(r23.background_color)) goto L92;
        if (this.selectedGradientColor1 != Theme.getWallpaperColor(r07.settings.second_background_color)) goto L92;
        if (this.selectedGradientColor2 != Theme.getWallpaperColor(r07.settings.third_background_color)) goto L92;
        if (this.selectedGradientColor3 != Theme.getWallpaperColor(r07.settings.fourth_background_color)) goto L92;
        if (this.selectedGradientColor1 == 0) goto L91;
        if (this.selectedGradientColor2 != 0) goto L91;
        if (this.selectedGradientRotation == AndroidUtilities.getWallpaperRotation(r07.settings.rotation, false)) goto L91;
        if (Math.abs(Theme.getThemeIntensity(r07.settings.intensity / 100.0f) - this.selectedIntensity) <= 0.001f) goto L91;
    L92:
        r24 = _UrlKt.FRAGMENT_ENCODE_SET;
        r3 = r07;
        r53 = null;
        goto L93
    L94:
        String r27 = this.selectedBackgroundSlug;
        if ((r52 instanceof ColorWallpaper) == false) goto L100;
        TLRPC.WallPaper r010 = r52.parentWallpaper;
        if (r010 == null) goto L100;
        long r95 = r010.id;
    L99:
        r4 = r27;
        r25 = r95;
        r10 = r52;
        r94 = null;
    L100:
        r95 = 0;
        goto L99
    L47:
        if ((r5 instanceof TLRPC.TL_wallPaper) == false) goto L67;
        r52 = (TLRPC.TL_wallPaper) r5;
        if (r52.settings == null) goto L67;
        if (TextUtils.equals(this.selectedBackgroundSlug, r52.slug) == false) goto L67;
        if (this.selectedColor != Theme.getWallpaperColor(r52.settings.background_color)) goto L67;
        if (this.selectedGradientColor1 != Theme.getWallpaperColor(r52.settings.second_background_color)) goto L67;
        if (this.selectedGradientColor2 != Theme.getWallpaperColor(r52.settings.third_background_color)) goto L67;
        if (this.selectedGradientColor3 != Theme.getWallpaperColor(r52.settings.fourth_background_color)) goto L67;
        if (this.selectedGradientColor1 == 0) goto L65;
        if (this.selectedGradientRotation != AndroidUtilities.getWallpaperRotation(r52.settings.rotation, false)) goto L67;
    L65:
        if (Math.abs(Theme.getThemeIntensity(r52.settings.intensity / 100.0f) - this.selectedIntensity) > 0.001f) goto L67;
    L68:
        r52 = 0;
        goto L70
    L16:
        this.wallPapers.remove(r04);
        goto L17
    L5:
        if (r0 == 2) goto L7;
    }

    private void fixLayout() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L6;
        r0.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass7(this));
        return;
    }

    private void fixLayoutInternal() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        int r0 = ((WindowManager) ApplicationLoader.applicationContext.getSystemService("window")).getDefaultDisplay().getRotation();
        if (AndroidUtilities.isTablet() == false) goto L8;
        this.columnsCount = 3;
    L14:
        updateRows();
        return;
    L8:
        if (r0 != 3) goto L10;
    L13:
        this.columnsCount = 5;
        goto L14
    L10:
        if (r0 == 1) goto L13;
        this.columnsCount = 3;
        goto L14
    }

    private String getWallPaperSlug(Object r1) {
        if ((r1 instanceof TLRPC.TL_wallPaper) == false) goto L7;
        return ((TLRPC.TL_wallPaper) r1).slug;
    L7:
        if ((r1 instanceof ColorWallpaper) == false) goto L11;
        return ((ColorWallpaper) r1).slug;
    L11:
        if ((r1 instanceof FileWallpaper) == true) goto L13;
        return null;
    L13:
        return ((FileWallpaper) r1).slug;
    }

    private /* synthetic */ void lambda$createView$1() {
        loadWallpapers(false);
    }

    private /* synthetic */ void lambda$createView$2(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new WallpapersListActivity$$ExternalSyntheticLambda7(this));
    }

    private /* synthetic */ void lambda$createView$3(AlertDialog r2, int r3) {
        if (this.actionBar.isActionModeShowed() == false) goto L5;
        this.selectedWallPapers.clear();
        this.actionBar.hideActionMode();
        updateRowsSelection();
    L5:
        AlertDialog r22 = new AlertDialog(getParentActivity(), 3);
        this.progressDialog = r22;
        r22.setCanCancel(false);
        this.progressDialog.show();
        TL_account.resetWallPapers r23 = new TL_account.resetWallPapers();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r23, new WallpapersListActivity$$ExternalSyntheticLambda6(this));
    }

    private /* synthetic */ void lambda$createView$4(View r2, int r3) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        if (this.listView.getAdapter() != this.searchAdapter) goto L8;
        return;
    L8:
        if (r3 != this.uploadImageRow) goto L12;
        this.updater.openGallery();
        return;
    L12:
        if (r3 != this.setColorRow) goto L16;
        WallpapersListActivity r22 = new WallpapersListActivity(1);
        r22.patterns = this.patterns;
        presentFragment(r22);
        return;
    L16:
        if (r3 != this.resetRow) goto L22;
        AlertDialog.Builder r23 = new AlertDialog.Builder(getParentActivity());
        r23.setTitle(LocaleController.getString(R.string.ResetChatBackgroundsAlertTitle));
        r23.setMessage(LocaleController.getString(R.string.ResetChatBackgroundsAlert));
        r23.setPositiveButton(LocaleController.getString(R.string.Reset), new WallpapersListActivity$$ExternalSyntheticLambda4(this));
        r23.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r24 = r23.create();
        showDialog(r24);
        TextView r1 = (TextView) r24.getButton(-1);
        if (r1 == null) goto L23;
        r1.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L23:
        return;
    }

    private /* synthetic */ int lambda$fillWallpapersWithCustom$9(long r7, String r9, boolean r10, Object r11, Object r12) {
        if ((r11 instanceof ColorWallpaper) == false) goto L6;
        r11 = ((ColorWallpaper) r11).parentWallpaper;
    L6:
        if ((r12 instanceof ColorWallpaper) == false) goto L9;
        r12 = ((ColorWallpaper) r12).parentWallpaper;
    L9:
        if ((r11 instanceof TLRPC.WallPaper) == true) goto L11;
    L54:
        return 0;
    L11:
        if ((r12 instanceof TLRPC.WallPaper) == false) goto L54;
        TLRPC.WallPaper r112 = (TLRPC.WallPaper) r11;
        TLRPC.WallPaper r122 = (TLRPC.WallPaper) r12;
        if (r7 == 0) goto L21;
        if (r112.id != r7) goto L18;
        return -1;
    L18:
        if (r122.id != r7) goto L26;
        return 1;
    L26:
        if (r10 == false) goto L28;
    L33:
        int r72 = this.allWallPapers.indexOf(r112);
        int r6 = this.allWallPapers.indexOf(r122);
        boolean r8 = r112.dark;
        if (r8 == true) goto L36;
    L37:
        if (r8 == false) goto L39;
    L45:
        if (r8 == true) goto L47;
    L51:
        if (r10 == false) goto L53;
        return 1;
    L53:
        return -1;
    L47:
        if (r122.dark == true) goto L51;
        if (r10 == false) goto L50;
        return -1;
    L50:
        return 1;
    L39:
        if (r122.dark == true) goto L45;
    L40:
        if (r72 <= r6) goto L42;
        return 1;
    L42:
        if (r72 >= r6) goto L44;
        return -1;
    L44:
        return 0;
    L36:
        if (r122.dark == true) goto L40;
    L28:
        if ("qeZWES8rGVIEAAAARfWlK1lnfiI".equals(r112.slug) == false) goto L31;
        return -1;
    L31:
        if ("qeZWES8rGVIEAAAARfWlK1lnfiI".equals(r122.slug) == false) goto L33;
        return 1;
    L21:
        if (r9.equals(r112.slug) == false) goto L24;
        return -1;
    L24:
        if (r9.equals(r122.slug) == false) goto L26;
        return 1;
    }

    private /* synthetic */ void lambda$loadWallpapers$7(TLObject r14, boolean r15) {
        if ((r14 instanceof TL_account.TL_wallPapers) == false) goto L60;
        TL_account.TL_wallPapers r142 = (TL_account.TL_wallPapers) r14;
        this.patterns.clear();
        this.patternsDict.clear();
        int r0 = this.currentType;
        if (r0 == 1) goto L8;
        if (r0 == 2) goto L8;
        this.wallPapers.clear();
        this.allWallPapersDict.clear();
        this.allWallPapers.clear();
        this.allWallPapers.addAll(r142.wallpapers);
        this.wallPapers.addAll(this.localWallPapers);
    L8:
        int r02 = r142.wallpapers.size();
        int r4 = 0;
    L9:
        if (r4 >= r02) goto L59;
        TLRPC.WallPaper r5 = r142.wallpapers.get(r4);
        if ("fqv01SQemVIBAAAApND8LDRUhRU".equals(r5.slug) == true) goto L58;
        if ((r5 instanceof TLRPC.TL_wallPaper) == false) goto L43;
        if ((r5.document instanceof TLRPC.TL_documentEmpty) == true) goto L43;
        this.allWallPapersDict.put(r5.slug, r5);
        if (r5.pattern == false) goto L24;
        TLRPC.Document r6 = r5.document;
        if (r6 == null) goto L24;
        if (this.patternsDict.containsKey(Long.valueOf(r6.id)) == true) goto L24;
        this.patterns.add(r5);
        this.patternsDict.put(Long.valueOf(r5.document.id), r5);
    L24:
        int r62 = this.currentType;
        if (r62 == 1) goto L58;
        boolean r7 = r5.pattern;
        if (r7 == false) goto L32;
        TLRPC.WallPaperSettings r8 = r5.settings;
        if (r8 == null) goto L58;
        if (r8.background_color == 0) goto L58;
    L32:
        if (r62 != 2) goto L35;
        if (r7 == false) goto L58;
    L35:
        if (Theme.isCurrentThemeDark() == true) goto L41;
        TLRPC.WallPaperSettings r63 = r5.settings;
        if (r63 == null) goto L41;
        if (r63.intensity < 0) goto L58;
    L41:
        this.wallPapers.add(r5);
    L43:
        if (r5.settings.background_color == 0) goto L58;
        if (Theme.isCurrentThemeDark() == true) goto L51;
        TLRPC.WallPaperSettings r64 = r5.settings;
        if (r64 == null) goto L51;
        if (r64.intensity < 0) goto L58;
    L51:
        TLRPC.WallPaperSettings r65 = r5.settings;
        int r10 = r65.second_background_color;
        if (r10 == 0) goto L56;
        int r11 = r65.third_background_color;
        if (r11 == 0) goto L56;
        ColorWallpaper r72 = new ColorWallpaper(null, r65.background_color, r10, r11, r65.fourth_background_color);
    L57:
        r72.slug = r5.slug;
        TLRPC.WallPaperSettings r66 = r5.settings;
        r72.intensity = r66.intensity / 100.0f;
        r72.gradientRotation = AndroidUtilities.getWallpaperRotation(r66.rotation, false);
        r72.parentWallpaper = r5;
        this.wallPapers.add(r72);
    L56:
        r72 = new ColorWallpaper(null, r65.background_color, r10, r65.rotation);
    L58:
        r4 = r4 + 1;
        goto L9
    L59:
        fillWallpapersWithCustom();
        getMessagesStorage().putWallpapers(r142.wallpapers, 1);
    L60:
        AlertDialog r143 = this.progressDialog;
        if (r143 == null) goto L77;
        r143.dismiss();
        if (r15 == true) goto L78;
        this.listView.smoothScrollToPosition(0);
        return;
    L78:
        return;
    }

    private /* synthetic */ void lambda$loadWallpapers$8(boolean r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new WallpapersListActivity$$ExternalSyntheticLambda5(this, r2, r1));
    }

    private /* synthetic */ void lambda$onItemClick$5(TLRPC.WallPaper r1) {
        removeSelfFromStack();
    }

    private void loadWallpapers(boolean r10) {
        long r0 = 0;
        if (r10 == true) goto L15;
        int r2 = this.allWallPapers.size();
        int r3 = 0;
        long r4 = 0;
    L5:
        if (r3 >= r2) goto L14;
        Object r6 = this.allWallPapers.get(r3);
        if ((r6 instanceof TLRPC.WallPaper) == false) goto L13;
        long r62 = ((TLRPC.WallPaper) r6).id;
        if (r62 < 0) goto L13;
        r4 = MediaDataController.calcHash(r4, r62);
    L13:
        r3 = r3 + 1;
        goto L5
    L14:
        r0 = r4;
    L15:
        TL_account.getWallPapers r22 = new TL_account.getWallPapers();
        r22.hash = r0;
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(ConnectionsManager.getInstance(this.currentAccount).sendRequest(r22, new WallpapersListActivity$$ExternalSyntheticLambda2(this, r10)), this.classGuid);
    }

    private void onItemClick(WallpaperCell r19, Object r20, int r21) {
        boolean r3 = false;
        if (this.actionBar.isActionModeShowed() == true) goto L5;
        String r7 = getWallPaperSlug(r20);
        if ((r20 instanceof TLRPC.TL_wallPaper) == false) goto L29;
        TLRPC.TL_wallPaper r2 = (TLRPC.TL_wallPaper) r20;
        if (r2.pattern == false) goto L29;
        String r9 = r2.slug;
        TLRPC.WallPaperSettings r0 = r2.settings;
        ColorWallpaper r8 = new ColorWallpaper(r9, r0.background_color, r0.second_background_color, r0.third_background_color, r0.fourth_background_color, AndroidUtilities.getWallpaperRotation(r0.rotation, false), r0.intensity / 100.0f, r2.settings.motion, null);
        r8.pattern = r2;
        r8.parentWallpaper = r2;
        Object r22 = r8;
    L30:
        AnonymousClass6 r02 = new AnonymousClass6(this, r22, null, true, false);
        if (this.currentType != 1) goto L33;
    L34:
        r02.setDelegate(new WallpapersListActivity$$ExternalSyntheticLambda8(this));
    L36:
        if (this.selectedBackgroundSlug.equals(r7) == false) goto L38;
        r02.setInitialModes(this.selectedBackgroundBlurred, this.selectedBackgroundMotion, this.selectedIntensity);
    L38:
        r02.setPatterns(this.patterns);
        r02.setDialogId(this.dialogId);
        showAsSheet(r02);
        return;
    L33:
        if (this.dialogId == 0) goto L36;
    L29:
        r22 = r20;
        goto L30
    L5:
        if ((r20 instanceof ColorWallpaper) == false) goto L7;
        Object r23 = ((ColorWallpaper) r20).parentWallpaper;
    L9:
        if ((r23 instanceof TLRPC.WallPaper) == true) goto L11;
        return;
    L11:
        TLRPC.WallPaper r24 = (TLRPC.WallPaper) r23;
        int r4 = this.selectedWallPapers.indexOfKey(r24.id);
        LongSparseArray<Object> r5 = this.selectedWallPapers;
        if (r4 < 0) goto L14;
        r5.remove(r24.id);
    L16:
        if (this.selectedWallPapers.size() != 0) goto L18;
        this.actionBar.hideActionMode();
    L19:
        this.scrolling = false;
        if (this.selectedWallPapers.indexOfKey(r24.id) < 0) goto L22;
        r3 = true;
    L22:
        r19.setChecked(r21, r3, true);
        return;
    L18:
        this.selectedMessagesCountTextView.setNumber(this.selectedWallPapers.size(), true);
        goto L19
    L14:
        r5.put(r24.id, r20);
        goto L16
    L7:
        r23 = r20;
        goto L9
    }

    private boolean onItemLongClick(WallpaperCell r9, Object r10, int r11) {
        int r0 = this.currentType;
        if (r0 != 2) goto L5;
    L24:
        return false;
    L5:
        if (r0 == 3) goto L24;
        if ((r10 instanceof ColorWallpaper) == false) goto L10;
        Object r02 = ((ColorWallpaper) r10).parentWallpaper;
    L12:
        if (this.actionBar.isActionModeShowed() == true) goto L24;
        if (getParentActivity() == null) goto L24;
        if ((r02 instanceof TLRPC.WallPaper) == false) goto L24;
        AndroidUtilities.hideKeyboard(getParentActivity().getCurrentFocus());
        this.selectedWallPapers.put(((TLRPC.WallPaper) r02).id, r10);
        this.selectedMessagesCountTextView.setNumber(1, false);
        AnimatorSet r102 = new AnimatorSet();
        ArrayList r3 = new ArrayList();
        int r4 = 0;
    L20:
        if (r4 >= this.actionModeViews.size()) goto L22;
        View r5 = this.actionModeViews.get(r4);
        AndroidUtilities.clearDrawableAnimation(r5);
        r3.add(ObjectAnimator.ofFloat(r5, View.SCALE_Y, new float[]{0.1f, 1.0f}));
        r4 = r4 + 1;
        goto L20
    L22:
        r102.playTogether(r3);
        r102.setDuration(250);
        r102.start();
        this.scrolling = false;
        this.actionBar.showActionMode();
        r9.setChecked(r11, true, true);
        return true;
    L10:
        r02 = r10;
        goto L12
    }

    private void showAsSheet(ThemePreviewActivity r4) {
        BaseFragment.BottomSheetParams r0 = new BaseFragment.BottomSheetParams();
        r0.transitionFromLeft = true;
        r0.allowNestedScroll = false;
        r4.setResourceProvider(this.resourceProvider);
        r0.onOpenAnimationFinished = new WallpapersListActivity$$ExternalSyntheticLambda9();
        r0.occupyNavigationBar = true;
        showAsSheet(r4, r0);
    }

    private void updateRows() {
        this.rowCount = 0;
        int r1 = this.currentType;
        if (r1 != 0) goto L6;
        this.uploadImageRow = 0;
        int r0 = 1 + 1;
        this.setColorRow = 1;
        this.rowCount = r0 + 1;
        this.sectionRow = r0;
        this.galleryRow = -1;
        this.galleryHintRow = -1;
    L10:
        if (this.wallPapers.isEmpty() == true) goto L12;
        int r02 = (int) Math.ceil(this.wallPapers.size() / this.columnsCount);
        this.totalWallpaperRows = r02;
        int r12 = this.rowCount;
        this.wallPaperStartRow = r12;
        this.rowCount = r12 + r02;
    L14:
        if (this.currentType != 0) goto L16;
        int r03 = this.rowCount;
        this.resetSectionRow = r03;
        this.resetRow = r03 + 1;
        this.rowCount = r03 + 3;
        this.resetInfoRow = r03 + 2;
    L17:
        ListAdapter r04 = this.listAdapter;
        if (r04 == null) goto L21;
        this.scrolling = true;
        r04.notifyDataSetChanged();
        return;
    L21:
        return;
    L16:
        this.resetSectionRow = -1;
        this.resetRow = -1;
        this.resetInfoRow = -1;
        goto L17
    L12:
        this.wallPaperStartRow = -1;
        goto L14
    L6:
        if (r1 != 2) goto L8;
        this.uploadImageRow = -1;
        this.setColorRow = -1;
        this.sectionRow = -1;
        this.galleryRow = 0;
        this.rowCount = 1 + 1;
        this.galleryHintRow = 1;
        goto L10
    L8:
        this.uploadImageRow = -1;
        this.setColorRow = -1;
        this.sectionRow = -1;
        this.galleryRow = -1;
        this.galleryHintRow = -1;
        goto L10
    }

    private void updateRowsSelection() {
        int r0 = this.listView.getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof WallpaperCell) == false) goto L10;
        WallpaperCell r32 = (WallpaperCell) r3;
        int r4 = 0;
    L8:
        if (r4 >= 5) goto L10;
        r32.setChecked(r4, false, true);
        r4 = r4 + 1;
    L10:
        r2 = r2 + 1;
        goto L3
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r13) {
        this.colorPaint = new Paint(1);
        Paint r0 = new Paint(1);
        this.colorFramePaint = r0;
        r0.setStrokeWidth(AndroidUtilities.dp(1.0f));
        this.colorFramePaint.setStyle(Paint.Style.STROKE);
        this.colorFramePaint.setColor(855638016);
        this.updater = new WallpaperUpdater(getParentActivity(), this, new AnonymousClass1(this));
        this.hasOwnBackground = true;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        int r02 = this.currentType;
        if (r02 != 0) goto L6;
        this.actionBar.setTitle(LocaleController.getString(R.string.ChatBackground));
    L10:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        if (this.currentType != 0) goto L13;
        ActionBarMenuItem r03 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass3(this));
        this.searchItem = r03;
        r03.setSearchFieldHint(LocaleController.getString(R.string.SearchBackgrounds));
        ActionBarMenu r04 = this.actionBar.createActionMode(false, null);
        r04.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefault));
        ActionBar r4 = this.actionBar;
        int r5 = Theme.key_actionBarDefaultIcon;
        r4.setItemsColor(Theme.getColor(r5), true);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSelector), true);
        NumberTextView r42 = new NumberTextView(r04.getContext());
        this.selectedMessagesCountTextView = r42;
        r42.setTextSize(18);
        this.selectedMessagesCountTextView.setTypeface(AndroidUtilities.bold());
        this.selectedMessagesCountTextView.setTextColor(Theme.getColor(r5));
        this.selectedMessagesCountTextView.setOnTouchListener(new WallpapersListActivity$$ExternalSyntheticLambda0());
        r04.addView(this.selectedMessagesCountTextView, LayoutHelper.createLinear(0, -1, 1.0f, 65, 0, 0, 0));
        this.actionModeViews.add(r04.addItemWithWidth(3, R.drawable.msg_forward, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.Forward)));
        this.actionModeViews.add(r04.addItemWithWidth(4, R.drawable.msg_delete, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.Delete)));
        this.selectedWallPapers.clear();
    L13:
        FrameLayout r05 = new FrameLayout(r13);
        this.fragmentView = r05;
        RecyclerListView r43 = new RecyclerListView(r13);
        this.listView = r43;
        r43.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        RecyclerListView r44 = this.listView;
        int r52 = Theme.key_windowBackgroundGray;
        r44.setBackgroundColor(getThemedColor(r52));
        this.listView.setClipToPadding(false);
        this.listView.setHorizontalScrollBarEnabled(false);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setItemAnimator(null);
        this.listView.setLayoutAnimation(null);
        RecyclerListView r2 = this.listView;
        AnonymousClass4 r45 = new AnonymousClass4(this, r13, 1, false);
        this.layoutManager = r45;
        r2.setLayoutManager(r45);
        r05.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        RecyclerListView r22 = this.listView;
        ListAdapter r3 = new ListAdapter(this, r13);
        this.listAdapter = r3;
        r22.setAdapter(r3);
        this.searchAdapter = new SearchAdapter(this, r13);
        this.listView.setGlowColor(Theme.getColor(Theme.key_avatar_backgroundActionBarBlue));
        this.listView.setOnItemClickListener(new WallpapersListActivity$$ExternalSyntheticLambda1(this));
        this.listView.setOnScrollListener(new AnonymousClass5(this));
        EmptyTextProgressView r23 = new EmptyTextProgressView(r13);
        this.searchEmptyView = r23;
        r23.setVisibility(8);
        this.searchEmptyView.setShowAtCenter(true);
        this.searchEmptyView.setBackgroundColor(Theme.getColor(r52));
        this.searchEmptyView.setText(LocaleController.getString(R.string.NoResult));
        this.listView.setEmptyView(this.searchEmptyView);
        r05.addView(this.searchEmptyView, LayoutHelper.createFrame(-1, -1.0f));
        updateRows();
        return this.fragmentView;
    L6:
        if (r02 != 2) goto L8;
        this.actionBar.setTitle("Channel Wallpaper");
        goto L10
    L8:
        if (r02 != 1) goto L10;
        this.actionBar.setTitle(LocaleController.getString(R.string.SelectColorTitle));
        goto L10
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r20, int r21, Object... r22) {
        if (r20 != NotificationCenter.wallpapersDidLoad) goto L77;
        ArrayList r2 = (ArrayList) r22[0];
        this.patterns.clear();
        this.patternsDict.clear();
        int r3 = this.currentType;
        if (r3 == 1) goto L8;
        if (r3 == 2) goto L8;
        this.wallPapers.clear();
        this.localWallPapers.clear();
        this.localDict.clear();
        this.allWallPapers.clear();
        this.allWallPapersDict.clear();
        this.allWallPapers.addAll(r2);
    L8:
        int r32 = r2.size();
        int r7 = 0;
        ArrayList r8 = null;
    L10:
        if (r7 >= r32) goto L67;
        TLRPC.WallPaper r11 = (TLRPC.WallPaper) r2.get(r7);
        if ("fqv01SQemVIBAAAApND8LDRUhRU".equals(r11.slug) == true) goto L66;
        if ((r11 instanceof TLRPC.TL_wallPaper) == false) goto L42;
        TLRPC.Document r12 = r11.document;
        if ((r12 instanceof TLRPC.TL_documentEmpty) == true) goto L42;
        if (r11.pattern == false) goto L24;
        if (r12 == null) goto L24;
        if (this.patternsDict.containsKey(Long.valueOf(r12.id)) == true) goto L24;
        this.patterns.add(r11);
        this.patternsDict.put(Long.valueOf(r11.document.id), r11);
    L24:
        this.allWallPapersDict.put(r11.slug, r11);
        int r9 = this.currentType;
        if (r9 == 1) goto L66;
        boolean r10 = r11.pattern;
        if (r10 == false) goto L32;
        TLRPC.WallPaperSettings r122 = r11.settings;
        if (r122 == null) goto L66;
        if (r122.background_color == 0) goto L66;
    L32:
        if (r9 != 2) goto L35;
        if (r10 == false) goto L66;
    L35:
        if (Theme.isCurrentThemeDark() == true) goto L41;
        TLRPC.WallPaperSettings r92 = r11.settings;
        if (r92 == null) goto L41;
        if (r92.intensity < 0) goto L66;
    L41:
        this.wallPapers.add(r11);
    L42:
        TLRPC.WallPaperSettings r123 = r11.settings;
        int r15 = r123.background_color;
        if (r15 == 0) goto L66;
        int r13 = r123.second_background_color;
        if (r13 == 0) goto L49;
        int r14 = r123.third_background_color;
        if (r14 == 0) goto L49;
        ColorWallpaper r132 = new ColorWallpaper(null, r15, r13, r14, r123.fourth_background_color);
    L50:
        r132.slug = r11.slug;
        TLRPC.WallPaperSettings r124 = r11.settings;
        r132.intensity = r124.intensity / 100.0f;
        r132.gradientRotation = AndroidUtilities.getWallpaperRotation(r124.rotation, false);
        r132.parentWallpaper = r11;
        if (r11.id >= 0) goto L59;
        String r93 = r132.getHash();
        if (this.localDict.containsKey(r93) == false) goto L57;
        if (r8 != null) goto L56;
        r8 = new ArrayList();
    L56:
        r8.add(r11);
        goto L66
    L57:
        this.localWallPapers.add(r132);
        this.localDict.put(r93, r132);
    L59:
        if (Theme.isCurrentThemeDark() == true) goto L65;
        TLRPC.WallPaperSettings r94 = r11.settings;
        if (r94 == null) goto L65;
        if (r94.intensity < 0) goto L66;
    L65:
        this.wallPapers.add(r132);
    L49:
        r132 = new ColorWallpaper(null, r15, r13, r123.rotation);
    L66:
        r7 = r7 + 1;
        goto L10
    L67:
        if (r8 == null) goto L72;
        int r23 = r8.size();
        int r33 = 0;
    L69:
        if (r33 >= r23) goto L72;
        getMessagesStorage().deleteWallpaper(((TLRPC.WallPaper) r8.get(r33)).id);
        r33 = r33 + 1;
    L72:
        if (this.dialogId != 0) goto L74;
        this.selectedBackgroundSlug = Theme.getSelectedBackgroundSlug();
    L74:
        fillWallpapersWithCustom();
        loadWallpapers(false);
        return;
    L77:
        if (r20 != NotificationCenter.didSetNewWallpapper) goto L86;
        RecyclerListView r1 = this.listView;
        if (r1 == null) goto L81;
        r1.invalidateViews();
    L81:
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L102;
        r0.closeSearchField();
        return;
    L102:
        return;
    L86:
        if (r20 != NotificationCenter.wallpapersNeedReload) goto L103;
        getMessagesStorage().getWallpapers();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        View r3 = this.fragmentView;
        int r9 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r3, 0, null, null, null, null, r9));
        View r11 = this.fragmentView;
        int r19 = Theme.key_windowBackgroundGray;
        r1.add(new ThemeDescription(r11, 0, null, null, null, null, r19));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r19));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r19));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{ShadowSectionCell.class}, null, null, null, r19));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayIcon));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{GraySectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_graySectionText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{GraySectionCell.class}, null, null, null, Theme.key_graySection));
        r1.add(new ThemeDescription(this.searchEmptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder));
        r1.add(new ThemeDescription(this.searchEmptyView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle));
        r1.add(new ThemeDescription(this.searchEmptyView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r9));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r1, int r2, Intent r3) {
        this.updater.onActivityResult(r1, r2, r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        fixLayout();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        int r0 = this.currentType;
        if (r0 != 0) goto L5;
    L12:
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.wallpapersDidLoad);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetNewWallpapper);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.wallpapersNeedReload);
        getMessagesStorage().getWallpapers();
    L14:
        return super.onFragmentCreate();
    L5:
        if (r0 == 2) goto L12;
        fillDefaultColors(this.wallPapers, Theme.isCurrentThemeDark());
        if (this.currentType != 1) goto L14;
        if (this.patterns.isEmpty() == false) goto L14;
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.wallpapersDidLoad);
        getMessagesStorage().getWallpapers();
        goto L14
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        int r0 = this.currentType;
        if (r0 != 0) goto L5;
    L10:
        this.searchAdapter.onDestroy();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.wallpapersDidLoad);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetNewWallpapper);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.wallpapersNeedReload);
    L11:
        this.updater.cleanup();
        super.onFragmentDestroy();
        return;
    L5:
        if (r0 == 2) goto L10;
        if (r0 != 1) goto L11;
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.wallpapersDidLoad);
        goto L11
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        MessagesController.getGlobalMainSettings();
        Theme.ThemeInfo r0 = Theme.getActiveTheme();
        if (this.dialogId == 0) goto L14;
        TLRPC.UserFull r02 = getMessagesController().getUserFull(this.dialogId);
        if (r02 == null) goto L25;
        TLRPC.WallPaper r03 = r02.wallpaper;
        if (r03 == null) goto L25;
        String r1 = r03.slug;
        this.selectedBackgroundSlug = r1;
        if (r1 != null) goto L11;
        this.selectedBackgroundSlug = _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        TLRPC.WallPaperSettings r04 = r03.settings;
        if (r04 == null) goto L25;
        this.selectedColor = r04.background_color;
        this.selectedGradientColor1 = r04.second_background_color;
        this.selectedGradientColor2 = r04.third_background_color;
        this.selectedGradientColor3 = r04.fourth_background_color;
        this.selectedGradientRotation = r04.rotation;
        this.selectedIntensity = r04.intensity;
        this.selectedBackgroundMotion = r04.motion;
        this.selectedBackgroundBlurred = r04.blur;
    L25:
        fillWallpapersWithCustom();
        fixLayout();
        return;
    L14:
        Theme.OverrideWallpaperInfo r05 = r0.overrideWallpaper;
        if (r05 == null) goto L21;
        String r12 = r05.slug;
        this.selectedBackgroundSlug = r12;
        if (r12 != null) goto L19;
        this.selectedBackgroundSlug = _UrlKt.FRAGMENT_ENCODE_SET;
    L19:
        this.selectedColor = r05.color;
        this.selectedGradientColor1 = r05.gradientColor1;
        this.selectedGradientColor2 = r05.gradientColor2;
        this.selectedGradientColor3 = r05.gradientColor3;
        this.selectedGradientRotation = r05.rotation;
        this.selectedIntensity = r05.intensity;
        this.selectedBackgroundMotion = r05.isMotion;
        this.selectedBackgroundBlurred = r05.isBlurred;
        goto L25
    L21:
        if (Theme.hasWallpaperFromTheme() == false) goto L23;
        String r06 = "t";
    L24:
        this.selectedBackgroundSlug = r06;
        this.selectedColor = 0;
        this.selectedGradientColor1 = 0;
        this.selectedGradientColor2 = 0;
        this.selectedGradientColor3 = 0;
        this.selectedGradientRotation = 45;
        this.selectedIntensity = 1.0f;
        this.selectedBackgroundMotion = false;
        this.selectedBackgroundBlurred = false;
        goto L25
    L23:
        r06 = "d";
        goto L24
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void restoreSelfArgs(Bundle r2) {
        this.updater.setCurrentPicturePath(r2.getString("path"));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void saveSelfArgs(Bundle r2) {
        String r1 = this.updater.getCurrentPicturePath();
        if (r1 == null) goto L6;
        r2.putString("path", r1);
        return;
    }
}
