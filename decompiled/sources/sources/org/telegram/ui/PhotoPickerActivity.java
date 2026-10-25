package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.exteragram.messenger.utils.ui.UIUtil;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationBadge;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.DividerCell;
import org.telegram.ui.Cells.PhotoAttachPhotoCell;
import org.telegram.ui.Cells.SharedDocumentCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.EditTextEmoji;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerViewItemRangeSelector;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes6.dex */
public class PhotoPickerActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private int alertOnlyOnce;
    private boolean allowCaption;
    private boolean allowIndices;
    private boolean allowOrder;
    private AnimatorSet animatorSet;
    private CharSequence caption;
    private ChatActivity chatActivity;
    protected EditTextEmoji commentTextView;
    private PhotoPickerActivityDelegate delegate;
    private final int dialogBackgroundKey;
    private StickerEmptyView emptyView;
    private FlickerLoadingView flickerView;
    private final boolean forceDarckTheme;
    protected FrameLayout frameLayout2;
    private int imageReqId;
    private boolean imageSearchEndReached;
    private String initialSearchString;
    private boolean isDocumentsPicker;
    private ActionBarMenuSubItem[] itemCells;
    private RecyclerViewItemRangeSelector itemRangeSelector;
    private int itemSize;
    private int itemsPerRow;
    private String lastSearchImageString;
    private String lastSearchString;
    private int lastSearchToken;
    private GridLayoutManager layoutManager;
    private ListAdapter listAdapter;
    private boolean listSort;
    private RecyclerListView listView;
    private int maxSelectedPhotos;
    private boolean needsBottomLayout;
    private String nextImagesSearchOffset;
    private Paint paint;
    private PhotoViewer.PhotoViewerProvider provider;
    private ArrayList<String> recentSearches;
    private RectF rect;
    private PhotoPickerActivitySearchDelegate searchDelegate;
    private ActionBarMenuItem searchItem;
    private ArrayList<MediaController.SearchImage> searchResult;
    private HashMap<String, MediaController.SearchImage> searchResultKeys;
    private HashMap<String, MediaController.SearchImage> searchResultUrls;
    private boolean searching;
    private boolean searchingUser;
    private int selectPhotoType;
    private MediaController.AlbumEntry selectedAlbum;
    protected View selectedCountView;
    private HashMap<Object, Object> selectedPhotos;
    private ArrayList<Object> selectedPhotosOrder;
    private final int selectorKey;
    private ActionBarPopupWindow.ActionBarPopupWindowLayout sendPopupLayout;
    private ActionBarPopupWindow sendPopupWindow;
    private boolean sendPressed;
    protected View shadow;
    private ActionBarMenuSubItem showAsListItem;
    private SizeNotifierFrameLayout sizeNotifierFrameLayout;
    private final int textKey;
    private TextPaint textPaint;
    private int type;
    private ImageView writeButton;
    protected FrameLayout writeButtonContainer;
    private Drawable writeButtonDrawable;

    /* renamed from: org.telegram.ui.PhotoPickerActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends PhotoViewer.EmptyPhotoViewerProvider {
        final /* synthetic */ PhotoPickerActivity this$0;

        public AnonymousClass1(PhotoPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean allowCaption() {
            return PhotoPickerActivity.m16800$$Nest$fgetallowCaption(this.this$0);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean cancelButtonPressed() {
            PhotoPickerActivity.m16805$$Nest$fgetdelegate(this.this$0).actionButtonPressed(true, true, 0, 0);
            this.this$0.finishFragment();
            return true;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r2, TLRPC.FileLocation r3, int r4, boolean r5, boolean r6) {
            PhotoAttachPhotoCell r22 = PhotoPickerActivity.m16847$$Nest$mgetCellForIndex(this.this$0, r4);
            if (r22 == null) goto L6;
            BackupImageView r32 = r22.getImageView();
            int[] r42 = new int[2];
            r32.getLocationInWindow(r42);
            PhotoViewer.PlaceProviderObject r52 = new PhotoViewer.PlaceProviderObject();
            r52.viewX = r42[0];
            r52.viewY = r42[1];
            r52.parentView = PhotoPickerActivity.m16817$$Nest$fgetlistView(this.this$0);
            ImageReceiver r1 = r32.getImageReceiver();
            r52.imageReceiver = r1;
            r52.thumb = r1.getBitmapSafe();
            r52.scale = r22.getScale();
            r22.showCheck(false);
            return r52;
        L6:
            return null;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public int getSelectedCount() {
            return PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$0).size();
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public HashMap<Object, Object> getSelectedPhotos() {
            return PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$0);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public ArrayList<Object> getSelectedPhotosOrder() {
            return PhotoPickerActivity.m16831$$Nest$fgetselectedPhotosOrder(this.this$0);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public ImageReceiver.BitmapHolder getThumbForPhoto(MessageObject r1, TLRPC.FileLocation r2, int r3) {
            PhotoAttachPhotoCell r0 = PhotoPickerActivity.m16847$$Nest$mgetCellForIndex(this.this$0, r3);
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.getImageView().getImageReceiver().getBitmapSafe();
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean isPhotoChecked(int r4) {
            if (PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0) == null) goto L11;
            if (r4 >= 0) goto L6;
        L10:
            return false;
        L6:
            if (r4 >= PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0).photos.size()) goto L10;
            if (PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$0).containsKey(Integer.valueOf(PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0).photos.get(r4).imageId)) == false) goto L10;
            return true;
        L11:
            if (r4 >= 0) goto L13;
        L17:
            return false;
        L13:
            if (r4 >= PhotoPickerActivity.m16825$$Nest$fgetsearchResult(this.this$0).size()) goto L17;
            if (PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$0).containsKey(((MediaController.SearchImage) PhotoPickerActivity.m16825$$Nest$fgetsearchResult(this.this$0).get(r4)).id) == false) goto L17;
            return true;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean scaleToFill() {
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void sendButtonPressed(int r1, VideoEditedInfo r2, boolean r3, int r4, int r5, boolean r6) {
            if (PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$0).isEmpty() == true) goto L5;
        L17:
            PhotoPickerActivity.m16850$$Nest$msendSelectedPhotos(this.this$0, r3, r4, 0);
            return;
        L5:
            if (PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0) == null) goto L11;
            if (r1 >= 0) goto L8;
            return;
        L8:
            if (r1 >= PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0).photos.size()) goto L20;
            MediaController.PhotoEntry r12 = PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0).photos.get(r1);
            r12.editedInfo = r2;
            PhotoPickerActivity.m16846$$Nest$maddToSelectedPhotos(this.this$0, r12, -1);
            goto L17
        L20:
            return;
        L11:
            if (r1 >= 0) goto L13;
            return;
        L13:
            if (r1 >= PhotoPickerActivity.m16825$$Nest$fgetsearchResult(this.this$0).size()) goto L21;
            MediaController.SearchImage r13 = (MediaController.SearchImage) PhotoPickerActivity.m16825$$Nest$fgetsearchResult(this.this$0).get(r1);
            r13.editedInfo = r2;
            PhotoPickerActivity.m16846$$Nest$maddToSelectedPhotos(this.this$0, r13, -1);
            goto L17
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public int setPhotoChecked(int r9, VideoEditedInfo r10) {
            int r3 = -1;
            int r4 = 1;
            if (PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0) == null) goto L13;
            if (r9 >= 0) goto L6;
        L12:
            return -1;
        L6:
            if (r9 >= PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0).photos.size()) goto L12;
            MediaController.PhotoEntry r0 = PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0).photos.get(r9);
            int r5 = PhotoPickerActivity.m16846$$Nest$maddToSelectedPhotos(this.this$0, r0, -1);
            if (r5 != (-1)) goto L11;
            r0.editedInfo = r10;
            r5 = PhotoPickerActivity.m16831$$Nest$fgetselectedPhotosOrder(this.this$0).indexOf(Integer.valueOf(r0.imageId));
        L20:
            boolean r102 = true;
        L23:
            int r02 = PhotoPickerActivity.m16817$$Nest$fgetlistView(this.this$0).getChildCount();
            int r1 = 0;
        L24:
            if (r1 >= r02) goto L32;
            View r6 = PhotoPickerActivity.m16817$$Nest$fgetlistView(this.this$0).getChildAt(r1);
            if (((Integer) r6.getTag()).intValue() == r9) goto L27;
            r1 = r1 + 1;
            goto L24
        L27:
            PhotoAttachPhotoCell r62 = (PhotoAttachPhotoCell) r6;
            if (PhotoPickerActivity.m16801$$Nest$fgetallowIndices(this.this$0) == false) goto L30;
            r3 = r5;
        L30:
            r62.setChecked(r3, r102, false);
        L32:
            PhotoPickerActivity r92 = this.this$0;
            if (r102 == true) goto L36;
            r4 = 2;
        L36:
            r92.updatePhotosButton(r4);
            PhotoPickerActivity.m16805$$Nest$fgetdelegate(this.this$0).selectedPhotosChanged();
            return r5;
        L11:
            r0.editedInfo = null;
        L22:
            r102 = false;
            goto L23
        L13:
            if (r9 >= 0) goto L15;
        L38:
            return -1;
        L15:
            if (r9 >= PhotoPickerActivity.m16825$$Nest$fgetsearchResult(this.this$0).size()) goto L38;
            MediaController.SearchImage r03 = (MediaController.SearchImage) PhotoPickerActivity.m16825$$Nest$fgetsearchResult(this.this$0).get(r9);
            r5 = PhotoPickerActivity.m16846$$Nest$maddToSelectedPhotos(this.this$0, r03, -1);
            if (r5 != (-1)) goto L21;
            r03.editedInfo = r10;
            r5 = PhotoPickerActivity.m16831$$Nest$fgetselectedPhotosOrder(this.this$0).indexOf(r03.id);
            goto L20
        L21:
            r03.editedInfo = null;
            goto L22
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public int setPhotoUnchecked(Object r3) {
            if ((r3 instanceof MediaController.PhotoEntry) == false) goto L6;
            Object r32 = Integer.valueOf(((MediaController.PhotoEntry) r3).imageId);
        L10:
            if (r32 != null) goto L13;
            return -1;
        L13:
            if (PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$0).containsKey(r32) == false) goto L21;
            PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$0).remove(r32);
            int r33 = PhotoPickerActivity.m16831$$Nest$fgetselectedPhotosOrder(this.this$0).indexOf(r32);
            if (r33 < 0) goto L18;
            PhotoPickerActivity.m16831$$Nest$fgetselectedPhotosOrder(this.this$0).remove(r33);
        L18:
            if (PhotoPickerActivity.m16801$$Nest$fgetallowIndices(this.this$0) == false) goto L20;
            PhotoPickerActivity.m16851$$Nest$mupdateCheckedPhotoIndices(this.this$0);
        L20:
            return r33;
        L21:
            return -1;
        L6:
            if ((r3 instanceof MediaController.SearchImage) == false) goto L8;
            r32 = ((MediaController.SearchImage) r3).id;
            goto L10
        L8:
            r32 = null;
            goto L10
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void updatePhotoAtIndex(int r5) {
            PhotoAttachPhotoCell r0 = PhotoPickerActivity.m16847$$Nest$mgetCellForIndex(this.this$0, r5);
            if (r0 != null) goto L5;
            return;
        L5:
            if (PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0) == null) goto L22;
            BackupImageView r02 = r0.getImageView();
            r02.setOrientation(0, true);
            MediaController.PhotoEntry r4 = PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0).photos.get(r5);
            String r52 = r4.thumbPath;
            if (r52 == null) goto L11;
            r02.setImage(r52, null, Theme.chat_attachEmptyDrawable);
            return;
        L11:
            if (r4.path == null) goto L20;
            r02.setOrientation(r4.orientation, r4.invert, true);
            if (r4.isVideo == true) goto L15;
        L18:
            r02.setImage("thumb://" + r4.imageId + ":" + r4.path, null, Theme.chat_attachEmptyDrawable);
            return;
        L15:
            if (r4.isLivePhoto() == true) goto L18;
            r02.setImage("vthumb://" + r4.imageId + ":" + r4.path, null, Theme.chat_attachEmptyDrawable);
            return;
        L20:
            r02.setImageDrawable(Theme.chat_attachEmptyDrawable);
            return;
        L22:
            r0.setPhotoEntry((MediaController.SearchImage) PhotoPickerActivity.m16825$$Nest$fgetsearchResult(this.this$0).get(r5), true, false);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void willHidePhotoViewer() {
            int r0 = PhotoPickerActivity.m16817$$Nest$fgetlistView(this.this$0).getChildCount();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            View r2 = PhotoPickerActivity.m16817$$Nest$fgetlistView(this.this$0).getChildAt(r1);
            if ((r2 instanceof PhotoAttachPhotoCell) == false) goto L7;
            ((PhotoAttachPhotoCell) r2).showCheck(true);
        L7:
            r1 = r1 + 1;
            goto L3
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void willSwitchFromPhoto(MessageObject r4, TLRPC.FileLocation r5, int r6) {
            int r42 = PhotoPickerActivity.m16817$$Nest$fgetlistView(this.this$0).getChildCount();
            int r52 = 0;
        L3:
            if (r52 >= r42) goto L21;
            View r0 = PhotoPickerActivity.m16817$$Nest$fgetlistView(this.this$0).getChildAt(r52);
            if (r0.getTag() == null) goto L20;
            PhotoAttachPhotoCell r1 = (PhotoAttachPhotoCell) r0;
            int r02 = ((Integer) r0.getTag()).intValue();
            if (PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0) == null) goto L13;
            if (r02 < 0) goto L20;
            if (r02 >= PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0).photos.size()) goto L20;
        L17:
            if (r02 != r6) goto L20;
            r1.showCheck(true);
            return;
        L13:
            if (r02 < 0) goto L20;
            if (r02 < PhotoPickerActivity.m16825$$Nest$fgetsearchResult(this.this$0).size()) goto L17;
        L20:
            r52 = r52 + 1;
            goto L3
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$10, reason: invalid class name */
    public class AnonymousClass10 extends RecyclerView.OnScrollListener {
        final /* synthetic */ PhotoPickerActivity this$0;

        public AnonymousClass10(PhotoPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r3, int r4, int r5) {
            if (PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0) != null) goto L20;
            int r32 = PhotoPickerActivity.m16814$$Nest$fgetlayoutManager(this.this$0).findFirstVisibleItemPosition();
            boolean r52 = false;
            if (r32 != (-1)) goto L7;
            int r42 = 0;
        L8:
            if (r42 > 0) goto L10;
            return;
        L10:
            if ((r32 + r42) > (PhotoPickerActivity.m16814$$Nest$fgetlayoutManager(this.this$0).getItemCount() - 2)) goto L12;
            return;
        L12:
            if (PhotoPickerActivity.m16827$$Nest$fgetsearching(this.this$0) == false) goto L14;
            return;
        L14:
            if (PhotoPickerActivity.m16810$$Nest$fgetimageSearchEndReached(this.this$0) == true) goto L24;
            PhotoPickerActivity r33 = this.this$0;
            if (PhotoPickerActivity.m16836$$Nest$fgettype(r33) != 1) goto L18;
            r52 = true;
        L18:
            PhotoPickerActivity.m16849$$Nest$msearchImages(r33, r52, PhotoPickerActivity.m16813$$Nest$fgetlastSearchString(this.this$0), PhotoPickerActivity.m16819$$Nest$fgetnextImagesSearchOffset(this.this$0), true);
            return;
        L24:
            return;
        L7:
            r42 = Math.abs(PhotoPickerActivity.m16814$$Nest$fgetlayoutManager(this.this$0).findLastVisibleItemPosition() - r32) + 1;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$11, reason: invalid class name */
    public class AnonymousClass11 implements TextWatcher {
        final /* synthetic */ PhotoPickerActivity this$0;

        public AnonymousClass11(PhotoPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r2) {
            if (PhotoPickerActivity.m16805$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            PhotoPickerActivity.m16805$$Nest$fgetdelegate(this.this$0).onCaptionChanged(r2);
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$12, reason: invalid class name */
    public class AnonymousClass12 extends FrameLayout {
        final /* synthetic */ PhotoPickerActivity this$0;

        public AnonymousClass12(PhotoPickerActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
            super.onInitializeAccessibilityNodeInfo(r3);
            r3.setText(LocaleController.formatPluralString("AccDescrSendPhotos", PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$0).size(), new Object[0]));
            r3.setClassName(Button.class.getName());
            r3.setLongClickable(true);
            r3.setClickable(true);
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$13, reason: invalid class name */
    public class AnonymousClass13 extends ViewOutlineProvider {
        final /* synthetic */ PhotoPickerActivity this$0;

        public AnonymousClass13(PhotoPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        @SuppressLint({"NewApi"})
        public void getOutline(View r7, Outline r8) {
            if (ExteraConfig.getSquareFab() == false) goto L6;
            r8.setRoundRect(0, 0, AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f), AndroidUtilities.dp(16.0f));
            return;
        L6:
            r8.setOval(0, 0, AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f));
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$14, reason: invalid class name */
    public class AnonymousClass14 implements View.OnTouchListener {
        private Rect popupRect;
        final /* synthetic */ PhotoPickerActivity this$0;

        public AnonymousClass14(PhotoPickerActivity r1) {
            this.this$0 = r1;
            this.popupRect = new Rect();
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View r2, MotionEvent r3) {
            if (r3.getActionMasked() == 0) goto L5;
            return false;
        L5:
            if (PhotoPickerActivity.m16832$$Nest$fgetsendPopupWindow(this.this$0) != null) goto L7;
            return false;
        L7:
            if (PhotoPickerActivity.m16832$$Nest$fgetsendPopupWindow(this.this$0).isShowing() == false) goto L15;
            r2.getHitRect(this.popupRect);
            if (this.popupRect.contains((int) r3.getX(), (int) r3.getY()) == true) goto L16;
            PhotoPickerActivity.m16832$$Nest$fgetsendPopupWindow(this.this$0).dismiss();
            return false;
        L16:
            return false;
        L15:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$15, reason: invalid class name */
    public class AnonymousClass15 extends View {
        final /* synthetic */ PhotoPickerActivity this$0;

        public AnonymousClass15(PhotoPickerActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r11) {
            String r0 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(Math.max(1, PhotoPickerActivity.m16831$$Nest$fgetselectedPhotosOrder(this.this$0).size()))});
            int r2 = Math.max(AndroidUtilities.dp(16.0f) + ((int) Math.ceil(PhotoPickerActivity.m16835$$Nest$fgettextPaint(this.this$0).measureText(r0))), AndroidUtilities.dp(24.0f));
            int r3 = getMeasuredWidth() / 2;
            getMeasuredHeight();
            PhotoPickerActivity.m16835$$Nest$fgettextPaint(this.this$0).setColor(Theme.getColor(Theme.key_dialogRoundCheckBoxCheck));
            PhotoPickerActivity.m16820$$Nest$fgetpaint(this.this$0).setColor(Theme.getColor(PhotoPickerActivity.m16806$$Nest$fgetdialogBackgroundKey(this.this$0)));
            int r22 = r2 / 2;
            PhotoPickerActivity.m16823$$Nest$fgetrect(this.this$0).set(r3 - r22, 0.0f, r22 + r3, getMeasuredHeight());
            r11.drawRoundRect(PhotoPickerActivity.m16823$$Nest$fgetrect(this.this$0), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), PhotoPickerActivity.m16820$$Nest$fgetpaint(this.this$0));
            PhotoPickerActivity.m16820$$Nest$fgetpaint(this.this$0).setColor(Theme.getColor(Theme.key_dialogRoundCheckBox));
            PhotoPickerActivity.m16823$$Nest$fgetrect(this.this$0).set(r5 + AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), r2 - AndroidUtilities.dp(2.0f), getMeasuredHeight() - AndroidUtilities.dp(2.0f));
            r11.drawRoundRect(PhotoPickerActivity.m16823$$Nest$fgetrect(this.this$0), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), PhotoPickerActivity.m16820$$Nest$fgetpaint(this.this$0));
            r11.drawText(r0, r3 - (r1 / 2), AndroidUtilities.dp(16.2f), PhotoPickerActivity.m16835$$Nest$fgettextPaint(this.this$0));
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$16, reason: invalid class name */
    public class AnonymousClass16 extends AnimatorListenerAdapter {
        final /* synthetic */ PhotoPickerActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass16(PhotoPickerActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (r2.equals(PhotoPickerActivity.m16803$$Nest$fgetanimatorSet(this.this$0)) == false) goto L6;
            PhotoPickerActivity.m16838$$Nest$fputanimatorSet(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(PhotoPickerActivity.m16803$$Nest$fgetanimatorSet(this.this$0)) == true) goto L5;
            return;
        L5:
            if (this.val$show == true) goto L7;
            this.this$0.frameLayout2.setVisibility(4);
            this.this$0.writeButtonContainer.setVisibility(4);
        L7:
            PhotoPickerActivity.m16838$$Nest$fputanimatorSet(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PhotoPickerActivity this$0;

        public AnonymousClass2(PhotoPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r6) {
            if (r6 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r6 != 1) goto L15;
            PhotoPickerActivity r62 = this.this$0;
            PhotoPickerActivity.m16844$$Nest$fputlistSort(r62, true ^ PhotoPickerActivity.m16816$$Nest$fgetlistSort(r62));
            boolean r63 = PhotoPickerActivity.m16816$$Nest$fgetlistSort(this.this$0);
            PhotoPickerActivity r0 = this.this$0;
            if (r63 == false) goto L11;
            PhotoPickerActivity.m16817$$Nest$fgetlistView(r0).setPadding(0, 0, 0, AndroidUtilities.dp(48.0f));
        L12:
            PhotoPickerActivity.m16817$$Nest$fgetlistView(this.this$0).stopScroll();
            PhotoPickerActivity.m16814$$Nest$fgetlayoutManager(this.this$0).scrollToPositionWithOffset(0, 0);
            PhotoPickerActivity.m16815$$Nest$fgetlistAdapter(this.this$0).notifyDataSetChanged();
            return;
        L11:
            PhotoPickerActivity.m16817$$Nest$fgetlistView(r0).setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(50.0f));
            goto L12
        L15:
            if (r6 == 2) goto L17;
            return;
        L17:
            if (PhotoPickerActivity.m16805$$Nest$fgetdelegate(this.this$0) == null) goto L19;
            PhotoPickerActivity.m16805$$Nest$fgetdelegate(this.this$0).onOpenInPressed();
        L19:
            this.this$0.finishFragment();
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements ActionBarMenuItem.ActionBarSubMenuItemDelegate {
        final /* synthetic */ PhotoPickerActivity this$0;

        public AnonymousClass3(PhotoPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarSubMenuItemDelegate
        public void onHideSubMenu() {
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarSubMenuItemDelegate
        public void onShowSubMenu() {
            ActionBarMenuSubItem r0 = PhotoPickerActivity.m16833$$Nest$fgetshowAsListItem(this.this$0);
            if (PhotoPickerActivity.m16816$$Nest$fgetlistSort(this.this$0) == false) goto L6;
            int r1 = R.string.ShowAsGrid;
        L7:
            r0.setText(LocaleController.getString(r1));
            ActionBarMenuSubItem r02 = PhotoPickerActivity.m16833$$Nest$fgetshowAsListItem(this.this$0);
            if (PhotoPickerActivity.m16816$$Nest$fgetlistSort(this.this$0) == false) goto L10;
            int r2 = R.drawable.msg_media;
        L11:
            r02.setIcon(r2);
            return;
        L10:
            r2 = R.drawable.msg_list;
            goto L11
        L6:
            r1 = R.string.ShowAsList;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ PhotoPickerActivity this$0;
        Runnable updateSearch;

        public static /* synthetic */ void $r8$lambda$XWHLeEw0k5piHHHq7KuVlcCasuE(AnonymousClass4 r0) {
            r0.lambda$$0();
        }

        public AnonymousClass4(PhotoPickerActivity r1) {
            this.this$0 = r1;
            this.updateSearch = new PhotoPickerActivity$4$$ExternalSyntheticLambda0(this);
        }

        private /* synthetic */ void lambda$$0() {
            PhotoPickerActivity r1 = this.this$0;
            PhotoPickerActivity.m16848$$Nest$mprocessSearch(r1, PhotoPickerActivity.m16824$$Nest$fgetsearchItem(r1).getSearchField());
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public boolean canCollapseSearch() {
            this.this$0.finishFragment();
            return false;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchPressed(EditText r1) {
            PhotoPickerActivity.m16848$$Nest$mprocessSearch(this.this$0, r1);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r4) {
            if (r4.getText().length() != 0) goto L9;
            PhotoPickerActivity.m16825$$Nest$fgetsearchResult(this.this$0).clear();
            PhotoPickerActivity.m16826$$Nest$fgetsearchResultKeys(this.this$0).clear();
            PhotoPickerActivity.m16843$$Nest$fputlastSearchString(this.this$0, null);
            PhotoPickerActivity.m16840$$Nest$fputimageSearchEndReached(this.this$0, true);
            PhotoPickerActivity.m16845$$Nest$fputsearching(this.this$0, false);
            if (PhotoPickerActivity.m16809$$Nest$fgetimageReqId(this.this$0) == 0) goto L7;
            ConnectionsManager.getInstance(PhotoPickerActivity.access$000(this.this$0)).cancelRequest(PhotoPickerActivity.m16809$$Nest$fgetimageReqId(this.this$0), true);
            PhotoPickerActivity.m16839$$Nest$fputimageReqId(this.this$0, 0);
        L7:
            PhotoPickerActivity.m16807$$Nest$fgetemptyView(this.this$0).title.setText(LocaleController.getString(R.string.NoRecentSearches));
            PhotoPickerActivity.m16807$$Nest$fgetemptyView(this.this$0).showProgress(false);
            PhotoPickerActivity.m16852$$Nest$mupdateSearchInterface(this.this$0);
            return;
        L9:
            AndroidUtilities.cancelRunOnUIThread(this.updateSearch);
            AndroidUtilities.runOnUIThread(this.updateSearch, 1200);
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends SizeNotifierFrameLayout {
        private boolean ignoreLayout;
        private int lastItemSize;
        private int lastNotifyWidth;
        final /* synthetic */ PhotoPickerActivity this$0;

        public static /* synthetic */ void $r8$lambda$Qsg_iYTI_3DlqJF0MzMNdYxxgZM(AnonymousClass5 r0) {
            r0.lambda$onMeasure$0();
        }

        public AnonymousClass5(PhotoPickerActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$onMeasure$0() {
            PhotoPickerActivity.m16815$$Nest$fgetlistAdapter(this.this$0).notifyDataSetChanged();
        }

        private void onMeasureInternal(int r13, int r14) {
            int r0 = View.MeasureSpec.getSize(r13);
            int r1 = View.MeasureSpec.getSize(r14);
            setMeasuredDimension(r0, r1);
            int r2 = measureKeyboardHeight();
            if (AndroidUtilities.dp(20.0f) >= 0) goto L5;
        L11:
            int r10 = r14;
            int r3 = 0;
            if (r2 <= AndroidUtilities.dp(20.0f)) goto L16;
            EditTextEmoji r142 = this.this$0.commentTextView;
            if (r142 == null) goto L16;
            this.ignoreLayout = true;
            r142.hideEmojiView();
            this.ignoreLayout = false;
        L16:
            EditTextEmoji r143 = this.this$0.commentTextView;
            if (r143 != null) goto L19;
        L21:
            int r144 = getChildCount();
        L22:
            if (r3 >= r144) goto L48;
            View r7 = this.getChildAt(r3);
            if (r7 != null) goto L26;
        L27:
            AnonymousClass5 r6 = this;
            int r8 = r13;
        L47:
            r3 = r3 + 1;
            AnonymousClass5 this = r6;
            r13 = r8;
            goto L22
        L26:
            if (r7.getVisibility() == 8) goto L27;
            EditTextEmoji r22 = this.this$0.commentTextView;
            if (r22 != null) goto L31;
        L46:
            r6 = this;
            r8 = r13;
            r6.measureChildWithMargins(r7, r8, 0, r10, 0);
            goto L47
        L31:
            if (r22.isPopupView(r7) == false) goto L46;
            if (AndroidUtilities.isInMultiwindow == true) goto L39;
            if (AndroidUtilities.isTablet() == true) goto L39;
            r7.measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r7.getLayoutParams().height, TLObject.FLAG_30));
        L39:
            if (AndroidUtilities.isTablet() == false) goto L45;
            int r23 = View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30);
            if (AndroidUtilities.isTablet() == false) goto L43;
            float r4 = 200.0f;
        L44:
            r7.measure(r23, View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(r4), (r1 - AndroidUtilities.statusBarHeight) + this.getPaddingTop()), TLObject.FLAG_30));
            goto L27
        L43:
            r4 = 320.0f;
            goto L44
        L45:
            r7.measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((r1 - AndroidUtilities.statusBarHeight) + this.getPaddingTop(), TLObject.FLAG_30));
            goto L27
        L48:
            return;
        L19:
            if (r143.isPopupShowing() == false) goto L21;
            this.this$0.fragmentView.setTranslationY(0.0f);
            PhotoPickerActivity.m16817$$Nest$fgetlistView(this.this$0).setTranslationY(0.0f);
            PhotoPickerActivity.m16807$$Nest$fgetemptyView(this.this$0).setTranslationY(0.0f);
            goto L21
        L5:
            if (AndroidUtilities.isInMultiwindow == true) goto L11;
            PhotoPickerActivity r42 = this.this$0;
            if (r42.commentTextView == null) goto L11;
            if (r42.frameLayout2.getParent() != this) goto L11;
            r1 = r1 - this.this$0.commentTextView.getEmojiPadding();
            r14 = View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30);
            goto L11
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r9, int r10, int r11, int r12, int r13) {
            int r122 = r12 - r10;
            if (this.lastNotifyWidth == r122) goto L12;
            this.lastNotifyWidth = r122;
            if (PhotoPickerActivity.m16815$$Nest$fgetlistAdapter(this.this$0) == null) goto L8;
            PhotoPickerActivity.m16815$$Nest$fgetlistAdapter(this.this$0).notifyDataSetChanged();
        L8:
            if (PhotoPickerActivity.m16832$$Nest$fgetsendPopupWindow(this.this$0) == null) goto L12;
            if (PhotoPickerActivity.m16832$$Nest$fgetsendPopupWindow(this.this$0).isShowing() == false) goto L12;
            PhotoPickerActivity.m16832$$Nest$fgetsendPopupWindow(this.this$0).dismiss();
        L12:
            int r92 = getChildCount();
            PhotoPickerActivity r102 = this.this$0;
            int r1 = 0;
            if (r102.commentTextView != null) goto L15;
        L23:
            int r103 = 0;
        L24:
            setBottomClip(r103);
        L25:
            if (r1 >= r92) goto L62;
            View r0 = getChildAt(r1);
            if (r0.getVisibility() == 8) goto L61;
            FrameLayout.LayoutParams r2 = (FrameLayout.LayoutParams) r0.getLayoutParams();
            int r3 = r0.getMeasuredWidth();
            int r4 = r0.getMeasuredHeight();
            int r5 = r2.gravity;
            if (r5 != (-1)) goto L32;
            r5 = 51;
        L32:
            int r6 = r5 & 112;
            int r52 = r5 & 7;
            if (r52 != 1) goto L35;
            int r53 = ((r122 - r3) / 2) + r2.leftMargin;
            int r7 = r2.rightMargin;
        L38:
            int r54 = r53 - r7;
        L41:
            if (r6 != 16) goto L43;
            int r62 = ((((r13 - r103) - r11) - r4) / 2) + r2.topMargin;
            int r22 = r2.bottomMargin;
        L48:
            int r23 = r62 - r22;
        L51:
            EditTextEmoji r63 = this.this$0.commentTextView;
            if (r63 != null) goto L54;
        L60:
            r0.layout(r54, r23, r3 + r54, r4 + r23);
            goto L61
        L54:
            if (r63.isPopupView(r0) == false) goto L60;
            if (AndroidUtilities.isTablet() == false) goto L59;
            int r24 = getMeasuredHeight();
            int r64 = r0.getMeasuredHeight();
        L58:
            r23 = r24 - r64;
            goto L60
        L59:
            r24 = getMeasuredHeight();
            r64 = r0.getMeasuredHeight();
            goto L58
        L43:
            if (r6 != 48) goto L45;
            r23 = r2.topMargin + getPaddingTop();
            goto L51
        L45:
            if (r6 == 80) goto L47;
            r23 = r2.topMargin;
            goto L51
        L47:
            r62 = ((r13 - r103) - r11) - r4;
            r22 = r2.bottomMargin;
            goto L48
        L35:
            if (r52 == 5) goto L37;
            r54 = r2.leftMargin + getPaddingLeft();
            goto L41
        L37:
            r53 = (r122 - r3) - r2.rightMargin;
            r7 = getPaddingRight();
        L61:
            r1 = r1 + 1;
            goto L25
        L62:
            notifyHeightChanged();
            return;
        L15:
            if (r102.frameLayout2.getParent() != this) goto L23;
            if (AndroidUtilities.dp(20.0f) < 0) goto L23;
            if (AndroidUtilities.isInMultiwindow == true) goto L23;
            if (AndroidUtilities.isTablet() == true) goto L23;
            r103 = this.this$0.commentTextView.getEmojiPadding();
            goto L24
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            int r72 = View.MeasureSpec.getSize(r7);
            int r0 = View.MeasureSpec.getSize(r6);
            if (AndroidUtilities.isTablet() == false) goto L5;
            PhotoPickerActivity.m16842$$Nest$fputitemsPerRow(this.this$0, 4);
        L9:
            this.ignoreLayout = true;
            PhotoPickerActivity.m16841$$Nest$fputitemSize(this.this$0, ((r0 - AndroidUtilities.dp(4.0f)) - AndroidUtilities.dp(4.0f)) / PhotoPickerActivity.m16812$$Nest$fgetitemsPerRow(this.this$0));
            if (this.lastItemSize == PhotoPickerActivity.m16811$$Nest$fgetitemSize(this.this$0)) goto L12;
            this.lastItemSize = PhotoPickerActivity.m16811$$Nest$fgetitemSize(this.this$0);
            AndroidUtilities.runOnUIThread(new PhotoPickerActivity$5$$ExternalSyntheticLambda0(this));
        L12:
            boolean r02 = PhotoPickerActivity.m16816$$Nest$fgetlistSort(this.this$0);
            PhotoPickerActivity r2 = this.this$0;
            if (r02 == false) goto L15;
            PhotoPickerActivity.m16814$$Nest$fgetlayoutManager(r2).setSpanCount(1);
        L16:
            this.ignoreLayout = false;
            onMeasureInternal(r6, View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30));
            return;
        L15:
            PhotoPickerActivity.m16814$$Nest$fgetlayoutManager(r2).setSpanCount(Math.max(1, (PhotoPickerActivity.m16811$$Nest$fgetitemSize(this.this$0) * PhotoPickerActivity.m16812$$Nest$fgetitemsPerRow(this.this$0)) + (AndroidUtilities.dp(2.0f) * (PhotoPickerActivity.m16812$$Nest$fgetitemsPerRow(this.this$0) - 1))));
            goto L16
        L5:
            Point r1 = AndroidUtilities.displaySize;
            int r3 = r1.x;
            int r12 = r1.y;
            PhotoPickerActivity r4 = this.this$0;
            if (r3 <= r12) goto L8;
            PhotoPickerActivity.m16842$$Nest$fputitemsPerRow(r4, 4);
            goto L9
        L8:
            PhotoPickerActivity.m16842$$Nest$fputitemsPerRow(r4, 3);
            goto L9
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends GridLayoutManager {
        final /* synthetic */ PhotoPickerActivity this$0;

        public AnonymousClass6(PhotoPickerActivity r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ PhotoPickerActivity this$0;

        public AnonymousClass7(PhotoPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r4) {
            if (PhotoPickerActivity.m16815$$Nest$fgetlistAdapter(this.this$0).getItemViewType(r4) == 1) goto L18;
            if (PhotoPickerActivity.m16816$$Nest$fgetlistSort(this.this$0) == true) goto L18;
            if (PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0) == null) goto L9;
        L11:
            int r0 = PhotoPickerActivity.m16811$$Nest$fgetitemSize(this.this$0);
            if ((r4 % PhotoPickerActivity.m16812$$Nest$fgetitemsPerRow(this.this$0)) == (PhotoPickerActivity.m16812$$Nest$fgetitemsPerRow(this.this$0) - 1)) goto L14;
            int r3 = AndroidUtilities.dp(2.0f);
        L16:
            return r0 + r3;
        L14:
            r3 = 0;
            goto L16
        L9:
            if (TextUtils.isEmpty(PhotoPickerActivity.m16813$$Nest$fgetlastSearchString(this.this$0)) == false) goto L11;
        L18:
            return PhotoPickerActivity.m16814$$Nest$fgetlayoutManager(this.this$0).getSpanCount();
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements RecyclerViewItemRangeSelector.RecyclerViewItemRangeSelectorDelegate {
        final /* synthetic */ PhotoPickerActivity this$0;

        public AnonymousClass8(PhotoPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.RecyclerViewItemRangeSelector.RecyclerViewItemRangeSelectorDelegate
        public int getItemCount() {
            return PhotoPickerActivity.m16815$$Nest$fgetlistAdapter(this.this$0).getItemCount();
        }

        @Override // org.telegram.ui.Components.RecyclerViewItemRangeSelector.RecyclerViewItemRangeSelectorDelegate
        public boolean isIndexSelectable(int r1) {
            if (PhotoPickerActivity.m16815$$Nest$fgetlistAdapter(this.this$0).getItemViewType(r1) != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerViewItemRangeSelector.RecyclerViewItemRangeSelectorDelegate
        public boolean isSelected(int r3) {
            MediaController.AlbumEntry r0 = PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0);
            PhotoPickerActivity r1 = this.this$0;
            if (r0 == null) goto L5;
            Object r32 = Integer.valueOf(PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(r1).photos.get(r3).imageId);
        L7:
            return PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$0).containsKey(r32);
        L5:
            r32 = ((MediaController.SearchImage) PhotoPickerActivity.m16825$$Nest$fgetsearchResult(r1).get(r3)).id;
            goto L7
        }

        @Override // org.telegram.ui.Components.RecyclerViewItemRangeSelector.RecyclerViewItemRangeSelectorDelegate
        public void onStartStopSelection(boolean r2) {
            PhotoPickerActivity.m16837$$Nest$fputalertOnlyOnce(this.this$0, r2 ? 1 : 0);
            if (r2 == false) goto L5;
            PhotoPickerActivity.access$100(this.this$0).getView().requestDisallowInterceptTouchEvent(true);
        L5:
            PhotoPickerActivity.m16817$$Nest$fgetlistView(this.this$0).hideSelector(true);
        }

        @Override // org.telegram.ui.Components.RecyclerViewItemRangeSelector.RecyclerViewItemRangeSelectorDelegate
        public void setSelected(View r2, int r3, boolean r4) {
            if (isSelected(r3) != r4) goto L6;
            return;
        L6:
            if ((r2 instanceof PhotoAttachPhotoCell) == false) goto L9;
            ((PhotoAttachPhotoCell) r2).callDelegate();
            return;
        L9:
            PhotoPickerActivity.m16821$$Nest$fgetprovider(this.this$0).setPhotoChecked(r3, null);
        }
    }

    /* renamed from: org.telegram.ui.PhotoPickerActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends FlickerLoadingView {
        final /* synthetic */ PhotoPickerActivity this$0;

        public AnonymousClass9(PhotoPickerActivity r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.FlickerLoadingView
        public int getColumnsCount() {
            return 3;
        }

        @Override // org.telegram.ui.Components.FlickerLoadingView
        public int getViewType() {
            return 2;
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ PhotoPickerActivity this$0;

        /* renamed from: org.telegram.ui.PhotoPickerActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 implements PhotoAttachPhotoCell.PhotoAttachPhotoCellDelegate {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass1(ListAdapter r1) {
                this.this$1 = r1;
            }

            private void checkSlowMode() {
                if (PhotoPickerActivity.m16802$$Nest$fgetallowOrder(this.this$1.this$0) == true) goto L5;
                return;
            L5:
                if (PhotoPickerActivity.m16804$$Nest$fgetchatActivity(this.this$1.this$0) == null) goto L19;
                TLRPC.Chat r0 = PhotoPickerActivity.m16804$$Nest$fgetchatActivity(this.this$1.this$0).getCurrentChat();
                if (r0 != null) goto L9;
                return;
            L9:
                if (ChatObject.hasAdminRights(r0) == false) goto L11;
                return;
            L11:
                if (r0.slowmode_enabled == true) goto L13;
                return;
            L13:
                if (PhotoPickerActivity.m16799$$Nest$fgetalertOnlyOnce(this.this$1.this$0) == 2) goto L23;
                AlertsCreator.showSimpleAlert(this.this$1.this$0, LocaleController.getString(R.string.Slowmode), LocaleController.getString(R.string.SlowmodeSelectSendError));
                if (PhotoPickerActivity.m16799$$Nest$fgetalertOnlyOnce(this.this$1.this$0) != 1) goto L24;
                PhotoPickerActivity.m16837$$Nest$fputalertOnlyOnce(this.this$1.this$0, 2);
                return;
            L24:
                return;
            L23:
                return;
            }

            @Override // org.telegram.ui.Cells.PhotoAttachPhotoCell.PhotoAttachPhotoCellDelegate
            public void onCheckClick(PhotoAttachPhotoCell r9) {
                int r0 = ((Integer) r9.getTag()).intValue();
                MediaController.AlbumEntry r1 = PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$1.this$0);
                ListAdapter r2 = this.this$1;
                int r3 = -1;
                int r4 = 1;
                if (r1 == null) goto L17;
                MediaController.PhotoEntry r12 = PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(r2.this$0).photos.get(r0);
                boolean r22 = PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$1.this$0).containsKey(Integer.valueOf(r12.imageId));
                boolean r5 = !r22;
                if (r22 == true) goto L13;
                if (PhotoPickerActivity.m16818$$Nest$fgetmaxSelectedPhotos(this.this$1.this$0) <= 0) goto L13;
                if (PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$1.this$0).size() < PhotoPickerActivity.m16818$$Nest$fgetmaxSelectedPhotos(this.this$1.this$0)) goto L13;
                checkSlowMode();
                return;
            L13:
                if (PhotoPickerActivity.m16801$$Nest$fgetallowIndices(this.this$1.this$0) == false) goto L16;
                if (r22 == true) goto L16;
                r3 = PhotoPickerActivity.m16831$$Nest$fgetselectedPhotosOrder(this.this$1.this$0).size();
            L16:
                r9.setChecked(r3, r5, true);
                PhotoPickerActivity.m16846$$Nest$maddToSelectedPhotos(this.this$1.this$0, r12, r0);
            L30:
                PhotoPickerActivity r92 = this.this$1.this$0;
                if (r5 == true) goto L34;
                r4 = 2;
            L34:
                r92.updatePhotosButton(r4);
                PhotoPickerActivity.m16805$$Nest$fgetdelegate(this.this$1.this$0).selectedPhotosChanged();
                return;
            L17:
                AndroidUtilities.hideKeyboard(r2.this$0.getParentActivity().getCurrentFocus());
                MediaController.SearchImage r13 = (MediaController.SearchImage) PhotoPickerActivity.m16825$$Nest$fgetsearchResult(this.this$1.this$0).get(r0);
                boolean r23 = PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$1.this$0).containsKey(r13.id);
                r5 = !r23;
                if (r23 == true) goto L26;
                if (PhotoPickerActivity.m16818$$Nest$fgetmaxSelectedPhotos(this.this$1.this$0) <= 0) goto L26;
                if (PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$1.this$0).size() < PhotoPickerActivity.m16818$$Nest$fgetmaxSelectedPhotos(this.this$1.this$0)) goto L26;
                checkSlowMode();
                return;
            L26:
                if (PhotoPickerActivity.m16801$$Nest$fgetallowIndices(this.this$1.this$0) == false) goto L29;
                if (r23 == true) goto L29;
                r3 = PhotoPickerActivity.m16831$$Nest$fgetselectedPhotosOrder(this.this$1.this$0).size();
            L29:
                r9.setChecked(r3, r5, true);
                PhotoPickerActivity.m16846$$Nest$maddToSelectedPhotos(this.this$1.this$0, r13, r0);
                goto L30
            }
        }

        public ListAdapter(PhotoPickerActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            MediaController.AlbumEntry r0 = PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0);
            PhotoPickerActivity r1 = this.this$0;
            if (r0 != null) goto L17;
            boolean r02 = PhotoPickerActivity.m16825$$Nest$fgetsearchResult(r1).isEmpty();
            PhotoPickerActivity r12 = this.this$0;
            if (r02 == false) goto L15;
            if (TextUtils.isEmpty(PhotoPickerActivity.m16813$$Nest$fgetlastSearchString(r12)) == true) goto L9;
            return 0;
        L9:
            if (PhotoPickerActivity.m16822$$Nest$fgetrecentSearches(this.this$0).isEmpty() == false) goto L12;
            return 0;
        L12:
            return PhotoPickerActivity.m16822$$Nest$fgetrecentSearches(this.this$0).size() + 2;
        L15:
            return PhotoPickerActivity.m16825$$Nest$fgetsearchResult(r12).size() + (!PhotoPickerActivity.m16810$$Nest$fgetimageSearchEndReached(this.this$0) ? 1 : 0);
        L17:
            return PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(r1).photos.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r1) {
            return r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (PhotoPickerActivity.m16816$$Nest$fgetlistSort(this.this$0) == false) goto L7;
            return 2;
        L7:
            if (PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0) == null) goto L9;
            return 0;
        L9:
            boolean r0 = PhotoPickerActivity.m16825$$Nest$fgetsearchResult(this.this$0).isEmpty();
            PhotoPickerActivity r2 = this.this$0;
            if (r0 == false) goto L18;
            if (r3 != PhotoPickerActivity.m16822$$Nest$fgetrecentSearches(r2).size()) goto L15;
            return 4;
        L15:
            return 3;
        L18:
            if (r3 >= PhotoPickerActivity.m16825$$Nest$fgetsearchResult(r2).size()) goto L20;
            return 0;
        L20:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r4) {
            if (PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0) == null) goto L5;
            return true;
        L5:
            if (TextUtils.isEmpty(PhotoPickerActivity.m16813$$Nest$fgetlastSearchString(this.this$0)) == false) goto L11;
            if (r4.getItemViewType() != 3) goto L9;
            return true;
        L9:
            return false;
        L11:
            if (r4.getAdapterPosition() >= PhotoPickerActivity.m16825$$Nest$fgetsearchResult(this.this$0).size()) goto L13;
            return true;
        L13:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r11, int r12) {
            int r0 = r11.getItemViewType();
            int r1 = -1;
            int r3 = 0;
            if (r0 == 0) goto L22;
            if (r0 != 1) goto L6;
            ViewGroup.LayoutParams r122 = r11.itemView.getLayoutParams();
            if (r122 == null) goto L42;
            r122.width = -1;
            r122.height = PhotoPickerActivity.m16811$$Nest$fgetitemSize(this.this$0);
            r11.itemView.setLayoutParams(r122);
            return;
        L42:
            return;
        L6:
            if (r0 != 2) goto L8;
            MediaController.PhotoEntry r02 = PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0).photos.get(r12);
            SharedDocumentCell r112 = (SharedDocumentCell) r11.itemView;
            r112.setPhotoEntry(r02);
            r112.setChecked(PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$0).containsKey(Integer.valueOf(r02.imageId)), false);
            r112.setTag(Integer.valueOf(r12));
            return;
        L8:
            if (r0 != 3) goto L43;
            TextCell r113 = (TextCell) r11.itemView;
            if (r12 >= PhotoPickerActivity.m16822$$Nest$fgetrecentSearches(this.this$0).size()) goto L14;
            r113.setTextAndIcon((CharSequence) PhotoPickerActivity.m16822$$Nest$fgetrecentSearches(this.this$0).get(r12), R.drawable.msg_recent, false);
            return;
        L14:
            r113.setTextAndIcon(LocaleController.getString(R.string.ClearRecentHistory), R.drawable.msg_clear_recent, false);
            return;
        L43:
            return;
        L22:
            PhotoAttachPhotoCell r4 = (PhotoAttachPhotoCell) r11.itemView;
            r4.setItemSize(PhotoPickerActivity.m16811$$Nest$fgetitemSize(this.this$0));
            BackupImageView r114 = r4.getImageView();
            r4.setTag(Integer.valueOf(r12));
            r114.setOrientation(0, true);
            MediaController.AlbumEntry r03 = PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(this.this$0);
            PhotoPickerActivity r5 = this.this$0;
            if (r03 == null) goto L32;
            MediaController.PhotoEntry r52 = PhotoPickerActivity.m16829$$Nest$fgetselectedAlbum(r5).photos.get(r12);
            if (PhotoPickerActivity.m16831$$Nest$fgetselectedPhotosOrder(this.this$0).size() <= 1) goto L27;
            boolean r6 = true;
        L28:
            r4.setPhotoEntry(r52, r6, true, false, false);
            if (PhotoPickerActivity.m16801$$Nest$fgetallowIndices(this.this$0) == false) goto L31;
            r1 = PhotoPickerActivity.m16831$$Nest$fgetselectedPhotosOrder(this.this$0).indexOf(Integer.valueOf(r52.imageId));
        L31:
            r4.setChecked(r1, PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$0).containsKey(Integer.valueOf(r52.imageId)), false);
            boolean r123 = PhotoViewer.isShowingImage(r52.path);
        L36:
            r114.getImageReceiver().setVisible(!r123, true);
            CheckBox2 r115 = r4.getCheckBox();
            if (PhotoPickerActivity.m16828$$Nest$fgetselectPhotoType(this.this$0) != PhotoAlbumPickerActivity.SELECT_TYPE_ALL) goto L39;
            if (r123 == true) goto L39;
        L40:
            r115.setVisibility(r3);
            return;
        L39:
            r3 = 8;
            goto L40
        L27:
            r6 = false;
            goto L28
        L32:
            MediaController.SearchImage r124 = (MediaController.SearchImage) PhotoPickerActivity.m16825$$Nest$fgetsearchResult(r5).get(r12);
            r4.setPhotoEntry(r124, true, false);
            r4.getVideoInfoContainer().setVisibility(4);
            if (PhotoPickerActivity.m16801$$Nest$fgetallowIndices(this.this$0) == false) goto L35;
            r1 = PhotoPickerActivity.m16831$$Nest$fgetselectedPhotosOrder(this.this$0).indexOf(r124.id);
        L35:
            r4.setChecked(r1, PhotoPickerActivity.m16830$$Nest$fgetselectedPhotos(this.this$0).containsKey(r124.id), false);
            r123 = PhotoViewer.isShowingImage(r124.getPathToAttach());
            goto L36
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r5, int r6) {
            if (r6 != 0) goto L4;
            PhotoAttachPhotoCell r52 = new PhotoAttachPhotoCell(this.mContext, null);
            r52.setDelegate(new AnonymousClass1(this));
            FrameLayout r62 = r52.getCheckFrame();
            if (PhotoPickerActivity.m16828$$Nest$fgetselectPhotoType(this.this$0) == PhotoAlbumPickerActivity.SELECT_TYPE_ALL) goto L19;
            int r4 = 8;
        L20:
            r62.setVisibility(r4);
            ViewGroup r53 = r52;
        L22:
            return new RecyclerListView.Holder(r53);
        L19:
            r4 = 0;
            goto L20
        L4:
            if (r6 != 1) goto L6;
            ViewGroup r63 = new FrameLayout(this.mContext);
            r63.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            RadialProgressView r54 = new RadialProgressView(this.mContext);
            r54.setProgressColor(-11371101);
            r63.addView(r54, LayoutHelper.createFrame(-1, -1.0f));
            ViewGroup r64 = r63;
        L13:
            r53 = r64;
            goto L22
        L6:
            if (r6 != 2) goto L8;
            r53 = new SharedDocumentCell(this.mContext, 1);
            goto L22
        L8:
            if (r6 == 3) goto L10;
            DividerCell r55 = new DividerCell(this.mContext);
            r55.setForceDarkTheme(PhotoPickerActivity.m16808$$Nest$fgetforceDarckTheme(this.this$0));
            r53 = r55;
            goto L22
        L10:
            TextCell r65 = new TextCell(this.mContext, 23, true);
            r65.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            r64 = r65;
            if (PhotoPickerActivity.m16808$$Nest$fgetforceDarckTheme(this.this$0) == false) goto L13;
            r65.textView.setTextColor(Theme.getColor(PhotoPickerActivity.m16834$$Nest$fgettextKey(this.this$0)));
            r65.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_voipgroup_mutedIcon), PorterDuff.Mode.MULTIPLY));
            r64 = r65;
            goto L13
        }
    }

    public interface PhotoPickerActivityDelegate {
        void actionButtonPressed(boolean r1, boolean r2, int r3, int r4);

        default boolean canFinishFragment() {
            return true;
        }

        void onCaptionChanged(CharSequence r1);

        default void onOpenInPressed() {
        }

        void selectedPhotosChanged();
    }

    public interface PhotoPickerActivitySearchDelegate {
        void shouldClearRecentSearch();

        void shouldSearchText(String r1);
    }

    /* renamed from: $r8$lambda$01-weqBJ5trvjF5mjX4ktIphM1Y, reason: not valid java name */
    public static /* synthetic */ void m16795$r8$lambda$01weqBJ5trvjF5mjX4ktIphM1Y(PhotoPickerActivity r0, TLObject r1, boolean r2) {
        r0.lambda$searchBotUser$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$3JYdgowF1dLSWXCzZOpPbwJmr9A(PhotoPickerActivity r0, KeyEvent r1) {
        r0.lambda$createView$7(r1);
    }

    /* renamed from: $r8$lambda$GDWSJKFSYXgeecf-Raoh3Uhu6xo, reason: not valid java name */
    public static /* synthetic */ void m16796$r8$lambda$GDWSJKFSYXgeecfRaoh3Uhu6xo(PhotoPickerActivity r0, boolean r1, int r2, int r3) {
        r0.lambda$createView$8(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$INvfK2ePY7dXxwB6ZpXGZasBaKQ(PhotoPickerActivity r0, View r1, MotionEvent r2) {
        return r0.lambda$createView$6(r1, r2);
    }

    /* renamed from: $r8$lambda$QEFfUM-9ShBtqBNAGTbKOEmZMpM, reason: not valid java name */
    public static /* synthetic */ void m16797$r8$lambda$QEFfUM9ShBtqBNAGTbKOEmZMpM(PhotoPickerActivity r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$searchBotUser$12(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$QMITbrhAjOKiftc1PUHnVMIODzI(PhotoPickerActivity r0, View r1, int r2) {
        r0.lambda$createView$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VbUGLCj1olRUEM8XIV0SkvmLPQc(PhotoPickerActivity r0, String r1, int r2, TLObject r3, boolean r4, TLRPC.User r5) {
        r0.lambda$searchImages$13(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ boolean $r8$lambda$YnUimsF9JGs2zDduf6BxgVqOwzk(PhotoPickerActivity r0, View r1, int r2) {
        return r0.lambda$createView$2(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$biqJhsFy0NvdiXZkGBDECwTrj08(PhotoPickerActivity r0, View r1) {
        return r0.lambda$createView$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$kSLF_AzXeKkznTOM6HdgLxtAuIE(PhotoPickerActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$0(r1, r2);
    }

    /* renamed from: $r8$lambda$pDj7VGquUnS2Z1Hgg2-VQRAtYUY, reason: not valid java name */
    public static /* synthetic */ void m16798$r8$lambda$pDj7VGquUnS2Z1Hgg2VQRAtYUY(PhotoPickerActivity r0, boolean r1, int r2, int r3) {
        r0.lambda$createView$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$uqG9YGFI9XasqPTZq8kwGjgW3XM(PhotoPickerActivity r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$x1QJSyPMWZ73Hs8bz_FPze2o3qk(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$xGg3O1u8YO8VblV8tfEEDKfjx1I(PhotoPickerActivity r0, int r1, View r2) {
        r0.lambda$createView$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$zgjJcaXnmzBZe_QrP74vPaCOYDc(PhotoPickerActivity r0, String r1, int r2, boolean r3, TLRPC.User r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$searchImages$14(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: -$$Nest$fgetalertOnlyOnce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16799$$Nest$fgetalertOnlyOnce(PhotoPickerActivity r0) {
        return r0.alertOnlyOnce;
    }

    /* renamed from: -$$Nest$fgetallowCaption, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16800$$Nest$fgetallowCaption(PhotoPickerActivity r0) {
        return r0.allowCaption;
    }

    /* renamed from: -$$Nest$fgetallowIndices, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16801$$Nest$fgetallowIndices(PhotoPickerActivity r0) {
        return r0.allowIndices;
    }

    /* renamed from: -$$Nest$fgetallowOrder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16802$$Nest$fgetallowOrder(PhotoPickerActivity r0) {
        return r0.allowOrder;
    }

    /* renamed from: -$$Nest$fgetanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m16803$$Nest$fgetanimatorSet(PhotoPickerActivity r0) {
        return r0.animatorSet;
    }

    /* renamed from: -$$Nest$fgetchatActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivity m16804$$Nest$fgetchatActivity(PhotoPickerActivity r0) {
        return r0.chatActivity;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoPickerActivityDelegate m16805$$Nest$fgetdelegate(PhotoPickerActivity r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdialogBackgroundKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16806$$Nest$fgetdialogBackgroundKey(PhotoPickerActivity r0) {
        return r0.dialogBackgroundKey;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickerEmptyView m16807$$Nest$fgetemptyView(PhotoPickerActivity r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetforceDarckTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16808$$Nest$fgetforceDarckTheme(PhotoPickerActivity r0) {
        return r0.forceDarckTheme;
    }

    /* renamed from: -$$Nest$fgetimageReqId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16809$$Nest$fgetimageReqId(PhotoPickerActivity r0) {
        return r0.imageReqId;
    }

    /* renamed from: -$$Nest$fgetimageSearchEndReached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16810$$Nest$fgetimageSearchEndReached(PhotoPickerActivity r0) {
        return r0.imageSearchEndReached;
    }

    /* renamed from: -$$Nest$fgetitemSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16811$$Nest$fgetitemSize(PhotoPickerActivity r0) {
        return r0.itemSize;
    }

    /* renamed from: -$$Nest$fgetitemsPerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16812$$Nest$fgetitemsPerRow(PhotoPickerActivity r0) {
        return r0.itemsPerRow;
    }

    /* renamed from: -$$Nest$fgetlastSearchString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16813$$Nest$fgetlastSearchString(PhotoPickerActivity r0) {
        return r0.lastSearchString;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GridLayoutManager m16814$$Nest$fgetlayoutManager(PhotoPickerActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m16815$$Nest$fgetlistAdapter(PhotoPickerActivity r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistSort, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16816$$Nest$fgetlistSort(PhotoPickerActivity r0) {
        return r0.listSort;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m16817$$Nest$fgetlistView(PhotoPickerActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetmaxSelectedPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16818$$Nest$fgetmaxSelectedPhotos(PhotoPickerActivity r0) {
        return r0.maxSelectedPhotos;
    }

    /* renamed from: -$$Nest$fgetnextImagesSearchOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16819$$Nest$fgetnextImagesSearchOffset(PhotoPickerActivity r0) {
        return r0.nextImagesSearchOffset;
    }

    /* renamed from: -$$Nest$fgetpaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m16820$$Nest$fgetpaint(PhotoPickerActivity r0) {
        return r0.paint;
    }

    /* renamed from: -$$Nest$fgetprovider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoViewer.PhotoViewerProvider m16821$$Nest$fgetprovider(PhotoPickerActivity r0) {
        return r0.provider;
    }

    /* renamed from: -$$Nest$fgetrecentSearches, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16822$$Nest$fgetrecentSearches(PhotoPickerActivity r0) {
        return r0.recentSearches;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m16823$$Nest$fgetrect(PhotoPickerActivity r0) {
        return r0.rect;
    }

    /* renamed from: -$$Nest$fgetsearchItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m16824$$Nest$fgetsearchItem(PhotoPickerActivity r0) {
        return r0.searchItem;
    }

    /* renamed from: -$$Nest$fgetsearchResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16825$$Nest$fgetsearchResult(PhotoPickerActivity r0) {
        return r0.searchResult;
    }

    /* renamed from: -$$Nest$fgetsearchResultKeys, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16826$$Nest$fgetsearchResultKeys(PhotoPickerActivity r0) {
        return r0.searchResultKeys;
    }

    /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16827$$Nest$fgetsearching(PhotoPickerActivity r0) {
        return r0.searching;
    }

    /* renamed from: -$$Nest$fgetselectPhotoType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16828$$Nest$fgetselectPhotoType(PhotoPickerActivity r0) {
        return r0.selectPhotoType;
    }

    /* renamed from: -$$Nest$fgetselectedAlbum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaController.AlbumEntry m16829$$Nest$fgetselectedAlbum(PhotoPickerActivity r0) {
        return r0.selectedAlbum;
    }

    /* renamed from: -$$Nest$fgetselectedPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16830$$Nest$fgetselectedPhotos(PhotoPickerActivity r0) {
        return r0.selectedPhotos;
    }

    /* renamed from: -$$Nest$fgetselectedPhotosOrder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16831$$Nest$fgetselectedPhotosOrder(PhotoPickerActivity r0) {
        return r0.selectedPhotosOrder;
    }

    /* renamed from: -$$Nest$fgetsendPopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarPopupWindow m16832$$Nest$fgetsendPopupWindow(PhotoPickerActivity r0) {
        return r0.sendPopupWindow;
    }

    /* renamed from: -$$Nest$fgetshowAsListItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m16833$$Nest$fgetshowAsListItem(PhotoPickerActivity r0) {
        return r0.showAsListItem;
    }

    /* renamed from: -$$Nest$fgettextKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16834$$Nest$fgettextKey(PhotoPickerActivity r0) {
        return r0.textKey;
    }

    /* renamed from: -$$Nest$fgettextPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextPaint m16835$$Nest$fgettextPaint(PhotoPickerActivity r0) {
        return r0.textPaint;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16836$$Nest$fgettype(PhotoPickerActivity r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$fputalertOnlyOnce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16837$$Nest$fputalertOnlyOnce(PhotoPickerActivity r0, int r1) {
        r0.alertOnlyOnce = r1;
    }

    /* renamed from: -$$Nest$fputanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16838$$Nest$fputanimatorSet(PhotoPickerActivity r0, AnimatorSet r1) {
        r0.animatorSet = r1;
    }

    /* renamed from: -$$Nest$fputimageReqId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16839$$Nest$fputimageReqId(PhotoPickerActivity r0, int r1) {
        r0.imageReqId = r1;
    }

    /* renamed from: -$$Nest$fputimageSearchEndReached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16840$$Nest$fputimageSearchEndReached(PhotoPickerActivity r0, boolean r1) {
        r0.imageSearchEndReached = r1;
    }

    /* renamed from: -$$Nest$fputitemSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16841$$Nest$fputitemSize(PhotoPickerActivity r0, int r1) {
        r0.itemSize = r1;
    }

    /* renamed from: -$$Nest$fputitemsPerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16842$$Nest$fputitemsPerRow(PhotoPickerActivity r0, int r1) {
        r0.itemsPerRow = r1;
    }

    /* renamed from: -$$Nest$fputlastSearchString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16843$$Nest$fputlastSearchString(PhotoPickerActivity r0, String r1) {
        r0.lastSearchString = r1;
    }

    /* renamed from: -$$Nest$fputlistSort, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16844$$Nest$fputlistSort(PhotoPickerActivity r0, boolean r1) {
        r0.listSort = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16845$$Nest$fputsearching(PhotoPickerActivity r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$maddToSelectedPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16846$$Nest$maddToSelectedPhotos(PhotoPickerActivity r0, Object r1, int r2) {
        return r0.addToSelectedPhotos(r1, r2);
    }

    /* renamed from: -$$Nest$mgetCellForIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoAttachPhotoCell m16847$$Nest$mgetCellForIndex(PhotoPickerActivity r0, int r1) {
        return r0.getCellForIndex(r1);
    }

    /* renamed from: -$$Nest$mprocessSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16848$$Nest$mprocessSearch(PhotoPickerActivity r0, EditText r1) {
        r0.processSearch(r1);
    }

    /* renamed from: -$$Nest$msearchImages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16849$$Nest$msearchImages(PhotoPickerActivity r0, boolean r1, String r2, String r3, boolean r4) {
        r0.searchImages(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$msendSelectedPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16850$$Nest$msendSelectedPhotos(PhotoPickerActivity r0, boolean r1, int r2, int r3) {
        r0.sendSelectedPhotos(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mupdateCheckedPhotoIndices, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16851$$Nest$mupdateCheckedPhotoIndices(PhotoPickerActivity r0) {
        r0.updateCheckedPhotoIndices();
    }

    /* renamed from: -$$Nest$mupdateSearchInterface, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16852$$Nest$mupdateSearchInterface(PhotoPickerActivity r0) {
        r0.updateSearchInterface();
    }

    public PhotoPickerActivity(int r3, MediaController.AlbumEntry r4, HashMap<Object, Object> r5, ArrayList<Object> r6, int r7, boolean r8, ChatActivity r9, boolean r10) {
        this.searchResult = new ArrayList();
        this.searchResultKeys = new HashMap();
        this.searchResultUrls = new HashMap();
        this.recentSearches = new ArrayList();
        this.imageSearchEndReached = true;
        this.allowOrder = true;
        this.itemSize = 100;
        this.itemsPerRow = 3;
        this.textPaint = new TextPaint(1);
        this.rect = new RectF();
        this.paint = new Paint(1);
        this.needsBottomLayout = true;
        this.provider = new AnonymousClass1(this);
        this.selectedAlbum = r4;
        this.selectedPhotos = r5;
        this.selectedPhotosOrder = r6;
        this.type = r3;
        this.selectPhotoType = r7;
        this.chatActivity = r9;
        this.allowCaption = r8;
        this.forceDarckTheme = r10;
        if (r4 != null) goto L5;
        loadRecentSearch();
    L5:
        if (r10 == false) goto L8;
        this.dialogBackgroundKey = Theme.key_voipgroup_dialogBackground;
        this.textKey = Theme.key_voipgroup_actionBarItems;
        this.selectorKey = Theme.key_voipgroup_actionBarItemsSelector;
        return;
    L8:
        this.dialogBackgroundKey = Theme.key_dialogBackground;
        this.textKey = Theme.key_dialogTextBlack;
        this.selectorKey = Theme.key_dialogButtonSelector;
    }

    public static /* synthetic */ int access$000(PhotoPickerActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ INavigationLayout access$100(PhotoPickerActivity r0) {
        return r0.parentLayout;
    }

    private void addToRecentSearches(String r5) {
        int r0 = this.recentSearches.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        if (this.recentSearches.get(r2).equalsIgnoreCase(r5) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        this.recentSearches.remove(r2);
    L8:
        this.recentSearches.add(0, r5);
    L10:
        if (this.recentSearches.size() <= 20) goto L12;
        this.recentSearches.remove(r5.size() - 1);
        goto L10
    L12:
        saveRecentSearch();
    }

    private int addToSelectedPhotos(Object r6, int r7) {
        boolean r0 = r6 instanceof MediaController.PhotoEntry;
        if (r0 == false) goto L6;
        Object r1 = Integer.valueOf(((MediaController.PhotoEntry) r6).imageId);
    L10:
        if (r1 != null) goto L12;
        return -1;
    L12:
        boolean r3 = this.selectedPhotos.containsKey(r1);
        HashMap<Object, Object> r4 = this.selectedPhotos;
        if (r3 == false) goto L28;
        r4.remove(r1);
        int r12 = this.selectedPhotosOrder.indexOf(r1);
        if (r12 < 0) goto L18;
        this.selectedPhotosOrder.remove(r12);
    L18:
        if (this.allowIndices == false) goto L20;
        updateCheckedPhotoIndices();
    L20:
        if (r7 < 0) goto L27;
        if (r0 == false) goto L24;
        ((MediaController.PhotoEntry) r6).reset();
    L26:
        this.provider.updatePhotoAtIndex(r7);
        goto L27
    L24:
        if ((r6 instanceof MediaController.SearchImage) == false) goto L26;
        ((MediaController.SearchImage) r6).reset();
    L27:
        return r12;
    L28:
        r4.put(r1, r6);
        this.selectedPhotosOrder.add(r1);
        return -1;
    L6:
        if ((r6 instanceof MediaController.SearchImage) == false) goto L8;
        r1 = ((MediaController.SearchImage) r6).id;
        goto L10
    L8:
        r1 = null;
        goto L10
    }

    private void applyCaption() {
        EditTextEmoji r0 = this.commentTextView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.length() <= 0) goto L17;
        Object r02 = this.selectedPhotosOrder.get(0);
        Object r03 = this.selectedPhotos.get(r02);
        if ((r03 instanceof MediaController.PhotoEntry) == false) goto L12;
        String r2 = this.commentTextView.getText().toString();
        ((MediaController.PhotoEntry) r03).caption = r2;
        return;
    L12:
        if ((r03 instanceof MediaController.SearchImage) == false) goto L16;
        String r22 = this.commentTextView.getText().toString();
        ((MediaController.SearchImage) r03).caption = r22;
        return;
    L16:
        return;
    }

    private PhotoAttachPhotoCell getCellForIndex(int r6) {
        int r0 = this.listView.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L19;
        View r2 = this.listView.getChildAt(r1);
        if ((r2 instanceof PhotoAttachPhotoCell) == false) goto L18;
        PhotoAttachPhotoCell r22 = (PhotoAttachPhotoCell) r2;
        int r3 = ((Integer) r22.getTag()).intValue();
        MediaController.AlbumEntry r4 = this.selectedAlbum;
        if (r4 == null) goto L12;
        if (r3 < 0) goto L18;
        if (r3 >= r4.photos.size()) goto L18;
    L16:
        if (r3 != r6) goto L18;
        return r22;
    L12:
        if (r3 < 0) goto L18;
        if (r3 < this.searchResult.size()) goto L16;
    L18:
        r1 = r1 + 1;
        goto L3
    L19:
        return null;
    }

    private /* synthetic */ void lambda$createView$0(AlertDialog r1, int r2) {
        PhotoPickerActivitySearchDelegate r12 = this.searchDelegate;
        if (r12 == null) goto L6;
        r12.shouldClearRecentSearch();
        return;
    L6:
        clearRecentSearch();
    }

    private /* synthetic */ void lambda$createView$1(View r10, int r11) {
        int r1 = 1;
        if (this.selectedAlbum == null) goto L5;
    L20:
        MediaController.AlbumEntry r0 = this.selectedAlbum;
        if (r0 == null) goto L24;
        ArrayList<Object> r02 = r0.photos;
    L23:
        ArrayList<Object> r3 = r02;
        if (r11 >= 0) goto L27;
        return;
    L27:
        if (r11 >= r3.size()) goto L57;
        ActionBarMenuItem r03 = this.searchItem;
        if (r03 == null) goto L33;
        AndroidUtilities.hideKeyboard(r03.getSearchField());
    L33:
        if (this.listSort == false) goto L36;
        onListItemClick(r10, r3.get(r11));
        return;
    L36:
        int r102 = this.selectPhotoType;
        if (r102 != PhotoAlbumPickerActivity.SELECT_TYPE_AVATAR) goto L39;
    L44:
        int r5 = r1;
        PhotoViewer.getInstance().setParentActivity(this);
        PhotoViewer.getInstance().setMaxSelectedPhotos(this.maxSelectedPhotos, this.allowOrder);
        PhotoViewer.getInstance().openPhotoForSelect(r3, r11, r5, this.isDocumentsPicker, this.provider, this.chatActivity);
        return;
    L39:
        if (r102 == PhotoAlbumPickerActivity.SELECT_TYPE_AVATAR_VIDEO) goto L44;
        if (r102 != PhotoAlbumPickerActivity.SELECT_TYPE_WALLPAPER) goto L46;
        r1 = 3;
        goto L44
    L46:
        if (r102 != PhotoAlbumPickerActivity.SELECT_TYPE_QR) goto L49;
        r1 = 10;
        goto L44
    L49:
        if (this.chatActivity != null) goto L51;
        r1 = 4;
        goto L44
    L51:
        r1 = 0;
        goto L44
    L57:
        return;
    L24:
        r02 = this.searchResult;
        goto L23
    L5:
        if (this.searchResult.isEmpty() == false) goto L20;
        int r103 = this.recentSearches.size();
        ArrayList<String> r04 = this.recentSearches;
        if (r11 >= r103) goto L15;
        String r104 = r04.get(r11);
        PhotoPickerActivitySearchDelegate r112 = this.searchDelegate;
        if (r112 == null) goto L12;
        r112.shouldSearchText(r104);
        return;
    L12:
        this.searchItem.getSearchField().setText(r104);
        this.searchItem.getSearchField().setSelection(r104.length());
        processSearch(this.searchItem.getSearchField());
        return;
    L15:
        if (r11 != (r04.size() + 1)) goto L54;
        AlertDialog.Builder r105 = new AlertDialog.Builder(getParentActivity());
        r105.setTitle(LocaleController.getString(R.string.ClearSearchAlertTitle));
        r105.setMessage(LocaleController.getString(R.string.ClearSearchAlert));
        r105.setPositiveButton(LocaleController.getString(R.string.ClearButton), new PhotoPickerActivity$$ExternalSyntheticLambda8(this));
        r105.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r106 = r105.create();
        showDialog(r106);
        TextView r9 = (TextView) r106.getButton(-1);
        if (r9 == null) goto L55;
        r9.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L55:
        return;
    }

    private /* synthetic */ boolean lambda$createView$10(View r11) {
        ChatActivity r0 = this.chatActivity;
        if (r0 != null) goto L5;
    L37:
        return false;
    L5:
        if (this.maxSelectedPhotos == 1) goto L37;
        r0.getCurrentChat();
        TLRPC.User r02 = this.chatActivity.getCurrentUser();
        if (this.sendPopupLayout != null) goto L35;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r2 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(getParentActivity());
        this.sendPopupLayout = r2;
        r2.setAnimationEnabled(false);
        this.sendPopupLayout.setOnTouchListener(new AnonymousClass14(this));
        this.sendPopupLayout.setDispatchKeyEventListener(new PhotoPickerActivity$$ExternalSyntheticLambda11(this));
        this.sendPopupLayout.setShownFromBottom(false);
        this.itemCells = new ActionBarMenuSubItem[2];
        int r22 = 0;
    L10:
        if (r22 >= 2) goto L34;
        if (r22 == 0) goto L13;
    L14:
        if (r22 == 1) goto L16;
    L18:
        ActionBarMenuSubItem[] r5 = this.itemCells;
        Activity r7 = getParentActivity();
        if (r22 != 0) goto L21;
        boolean r8 = true;
    L22:
        if (r22 != 1) goto L24;
        boolean r9 = true;
    L25:
        r5[r22] = new ActionBarMenuSubItem(r7, r8, r9);
        if (r22 != 0) goto L31;
        boolean r52 = UserObject.isUserSelf(r02);
        ActionBarMenuSubItem[] r6 = this.itemCells;
        if (r52 == false) goto L30;
        r6[r22].setTextAndIcon(LocaleController.getString(R.string.SetReminder), R.drawable.msg_calendar2);
    L32:
        this.itemCells[r22].setMinimumWidth(AndroidUtilities.dp(196.0f));
        this.sendPopupLayout.addView(this.itemCells[r22], LayoutHelper.createLinear(-1, 48));
        this.itemCells[r22].setOnClickListener(new PhotoPickerActivity$$ExternalSyntheticLambda12(this, r22));
    L33:
        r22 = r22 + 1;
        goto L10
    L30:
        r6[r22].setTextAndIcon(LocaleController.getString(R.string.ScheduleMessage), R.drawable.msg_calendar2);
        goto L32
    L31:
        this.itemCells[r22].setTextAndIcon(LocaleController.getString(R.string.SendWithoutSound), R.drawable.input_notify_off);
        goto L32
    L24:
        r9 = false;
        goto L25
    L21:
        r8 = false;
        goto L22
    L16:
        if (UserObject.isUserSelf(r02) == false) goto L18;
    L13:
        if (this.chatActivity.canScheduleMessage() == false) goto L33;
    L34:
        this.sendPopupLayout.setupRadialSelectors(Theme.getColor(this.selectorKey));
        ActionBarPopupWindow r03 = new ActionBarPopupWindow(this.sendPopupLayout, -2, -2);
        this.sendPopupWindow = r03;
        r03.setAnimationEnabled(false);
        this.sendPopupWindow.setAnimationStyle(R.style.PopupContextAnimation2);
        this.sendPopupWindow.setOutsideTouchable(true);
        this.sendPopupWindow.setClippingEnabled(true);
        this.sendPopupWindow.setInputMethodMode(2);
        this.sendPopupWindow.setSoftInputMode(0);
        this.sendPopupWindow.getContentView().setFocusableInTouchMode(true);
    L35:
        this.sendPopupLayout.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
        this.sendPopupWindow.setFocusable(true);
        int[] r04 = new int[2];
        r11.getLocationInWindow(r04);
        this.sendPopupWindow.showAtLocation(r11, 51, ((r04[0] + r11.getMeasuredWidth()) - this.sendPopupLayout.getMeasuredWidth()) + AndroidUtilities.dp(8.0f), (r04[1] - this.sendPopupLayout.getMeasuredHeight()) - AndroidUtilities.dp(2.0f));
        this.sendPopupWindow.dimBehind();
        r11.performHapticFeedback(3, 2);     // Catch: Exception -> L38
        goto L37
    }

    private /* synthetic */ boolean lambda$createView$2(View r2, int r3) {
        if (this.listSort == false) goto L7;
        onListItemClick(r2, this.selectedAlbum.photos.get(r3));
        return true;
    L7:
        if ((r2 instanceof PhotoAttachPhotoCell) == false) goto L11;
        this.itemRangeSelector.startSelection(r2, r3);
        return false;
    L11:
        return false;
    }

    private /* synthetic */ void lambda$createView$4(boolean r1, int r2, int r3) {
        sendSelectedPhotos(r1, r2, 0);
    }

    private /* synthetic */ void lambda$createView$5(View r4) {
        ChatActivity r42 = this.chatActivity;
        if (r42 != null) goto L5;
    L8:
        sendSelectedPhotos(true, 0, 0);
        return;
    L5:
        if (r42.isInScheduleMode() == false) goto L8;
        AlertsCreator.createScheduleDatePickerDialog(getParentActivity(), this.chatActivity.getDialogId(), new PhotoPickerActivity$$ExternalSyntheticLambda10(this));
    }

    private /* synthetic */ boolean lambda$createView$6(View r2, MotionEvent r3) {
        ActionBarPopupWindow r0 = this.sendPopupWindow;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isShowing() == true) goto L7;
        return false;
    L7:
        if (this.sendPopupLayout != null) goto L9;
        return false;
    L9:
        if (r2.getParent() == null) goto L11;
        r2.getParent().requestDisallowInterceptTouchEvent(true);
    L11:
        ItemOptions.dispatchCapturedTouchEvent(this.sendPopupLayout, r3);
        return false;
    }

    private /* synthetic */ void lambda$createView$7(KeyEvent r3) {
        if (r3.getKeyCode() == 4) goto L5;
        return;
    L5:
        if (r3.getRepeatCount() != 0) goto L13;
        ActionBarPopupWindow r32 = this.sendPopupWindow;
        if (r32 != null) goto L9;
        return;
    L9:
        if (r32.isShowing() == false) goto L15;
        this.sendPopupWindow.dismiss();
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$createView$8(boolean r1, int r2, int r3) {
        sendSelectedPhotos(r1, r2, 0);
    }

    private /* synthetic */ void lambda$createView$9(int r3, View r4) {
        ActionBarPopupWindow r42 = this.sendPopupWindow;
        if (r42 != null) goto L5;
    L7:
        if (r3 != 0) goto L10;
        AlertsCreator.createScheduleDatePickerDialog(getParentActivity(), this.chatActivity.getDialogId(), new PhotoPickerActivity$$ExternalSyntheticLambda13(this));
        return;
    L10:
        sendSelectedPhotos(true, 0, 0);
        return;
    L5:
        if (r42.isShowing() == false) goto L7;
        this.sendPopupWindow.dismiss();
        goto L7
    }

    private /* synthetic */ void lambda$searchBotUser$11(TLObject r5, boolean r6) {
        TLRPC.TL_contacts_resolvedPeer r52 = (TLRPC.TL_contacts_resolvedPeer) r5;
        MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r52.chats, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r52.users, r52.chats, true, true);
        String r53 = this.lastSearchImageString;
        this.lastSearchImageString = null;
        searchImages(r6, r53, _UrlKt.FRAGMENT_ENCODE_SET, false);
    }

    private /* synthetic */ void lambda$searchBotUser$12(boolean r1, TLObject r2, TLRPC.TL_error r3) {
        if (r2 == null) goto L5;
        AndroidUtilities.runOnUIThread(new PhotoPickerActivity$$ExternalSyntheticLambda14(this, r2, r1));
        return;
    }

    private /* synthetic */ void lambda$searchImages$13(String r10, int r11, TLObject r12, boolean r13, TLRPC.User r14) {
        addToRecentSearches(r10);
        if (r11 != this.lastSearchToken) goto L99;
        int r102 = this.searchResult.size();
        if (r12 == null) goto L75;
        TLRPC.messages_BotResults r122 = (TLRPC.messages_BotResults) r12;
        this.nextImagesSearchOffset = r122.next_offset;
        int r1 = r122.results.size();
        int r2 = 0;
        int r3 = 0;
    L8:
        if (r2 >= r1) goto L68;
        TLRPC.BotInlineResult r4 = r122.results.get(r2);
        if (r13 == false) goto L12;
    L13:
        if (r13 == false) goto L18;
        if ("gif".equals(r4.type) == true) goto L18;
    L66:
        r2 = r2 + 1;
    L18:
        if (this.searchResultKeys.containsKey(r4.id) == true) goto L66;
        MediaController.SearchImage r5 = new MediaController.SearchImage();
        if (r13 == true) goto L23;
    L39:
        if (r13 == true) goto L47;
        TLRPC.Photo r6 = r4.photo;
        if (r6 == null) goto L47;
        TLRPC.PhotoSize r62 = FileLoader.getClosestPhotoSizeWithSize(r6.sizes, AndroidUtilities.getPhotoSize());
        TLRPC.PhotoSize r7 = FileLoader.getClosestPhotoSizeWithSize(r4.photo.sizes, 320);
        if (r62 == null) goto L66;
        r5.width = r62.w;
        r5.height = r62.h;
        r5.photoSize = r62;
        r5.photo = r4.photo;
        r5.size = r62.size;
        r5.thumbPhotoSize = r7;
    L65:
        r5.id = r4.id;
        r5.type = r13 ? 1 : 0;
        r5.inlineResult = r4;
        HashMap<String, String> r63 = new HashMap();
        r5.params = r63;
        r63.put("id", r4.id);
        r5.params.put("query_id", _UrlKt.FRAGMENT_ENCODE_SET + r122.query_id);
        r5.params.put("bot_name", UserObject.getPublicUsername(r14));
        this.searchResult.add(r5);
        this.searchResultKeys.put(r5.id, r5);
        r3 = r3 + 1;
    L47:
        if (r4.content == null) goto L66;
        int r64 = 0;
    L51:
        if (r64 >= r4.content.attributes.size()) goto L56;
        TLRPC.DocumentAttribute r72 = r4.content.attributes.get(r64);
        if ((r72 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L54;
        r64 = r64 + 1;
        goto L51
    L54:
        r5.width = r72.w;
        r5.height = r72.h;
    L56:
        TLRPC.WebDocument r65 = r4.thumb;
        if (r65 == null) goto L59;
        r5.thumbUrl = r65.url;
    L60:
        TLRPC.WebDocument r66 = r4.content;
        r5.imageUrl = r66.url;
        if (r13 == false) goto L63;
        int r67 = 0;
    L64:
        r5.size = r67;
        goto L65
    L63:
        r67 = r66.size;
        goto L64
    L59:
        r5.thumbUrl = null;
        goto L60
    L23:
        if (r4.document == null) goto L39;
        int r68 = 0;
    L26:
        if (r68 >= r4.document.attributes.size()) goto L34;
        TLRPC.DocumentAttribute r73 = r4.document.attributes.get(r68);
        if ((r73 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L33;
        if ((r73 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L33;
        r68 = r68 + 1;
    L33:
        r5.width = r73.w;
        r5.height = r73.h;
    L34:
        r5.document = r4.document;
        r5.size = 0;
        TLRPC.Photo r69 = r4.photo;
        if (r69 == null) goto L65;
        TLRPC.PhotoSize r610 = FileLoader.getClosestPhotoSizeWithSize(r69.sizes, this.itemSize, true);
        if (r610 == null) goto L65;
        r4.document.thumbs.add(r610);
        r4.document.flags |= 1;
        goto L65
    L12:
        if ("photo".equals(r4.type) == false) goto L66;
    L68:
        if (r102 != this.searchResult.size()) goto L70;
    L73:
        boolean r123 = true;
    L74:
        this.imageSearchEndReached = r123;
    L76:
        this.searching = false;
        if (r3 == 0) goto L80;
        this.listAdapter.notifyItemRangeInserted(r102, r3);
    L83:
        if (this.searchResult.size() > 0) goto L98;
        this.emptyView.showProgress(false);
        return;
    L98:
        return;
    L80:
        if (this.imageSearchEndReached == false) goto L83;
        this.listAdapter.notifyItemRemoved(this.searchResult.size() - 1);
        goto L83
    L70:
        if (this.nextImagesSearchOffset == null) goto L73;
        r123 = false;
        goto L74
    L75:
        r3 = 0;
        goto L76
    }

    private /* synthetic */ void lambda$searchImages$14(String r1, int r2, boolean r3, TLRPC.User r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new PhotoPickerActivity$$ExternalSyntheticLambda9(this, r1, r2, r5, r3, r4));
    }

    private void loadRecentSearch() {
        int r2 = 0;
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("web_recent_search", 0);
        int r1 = r0.getInt(NotificationBadge.NewHtcHomeBadger.COUNT, 0);
    L3:
        if (r2 >= r1) goto L8;
        String r3 = r0.getString("recent" + r2, null);
        if (r3 == null) goto L11;
        this.recentSearches.add(r3);
        r2 = r2 + 1;
        goto L3
    L11:
        return;
    }

    private void onListItemClick(View r4, Object r5) {
        int r1 = 1;
        if (addToSelectedPhotos(r5, -1) != (-1)) goto L5;
        boolean r52 = true;
    L7:
        if ((r4 instanceof SharedDocumentCell) == false) goto L9;
        ((SharedDocumentCell) r4).setChecked(this.selectedPhotosOrder.contains(Integer.valueOf(this.selectedAlbum.photos.get(((Integer) r4.getTag()).intValue()).imageId)), true);
    L9:
        if (r52 == true) goto L12;
        r1 = 2;
    L12:
        updatePhotosButton(r1);
        this.delegate.selectedPhotosChanged();
        return;
    L5:
        r52 = false;
        goto L7
    }

    private void processSearch(EditText r4) {
        if (r4.getText().length() != 0) goto L5;
        return;
    L5:
        String r42 = r4.getText().toString();
        this.searchResult.clear();
        this.searchResultKeys.clear();
        this.imageSearchEndReached = true;
        if (this.type != 1) goto L8;
        boolean r1 = true;
    L9:
        searchImages(r1, r42, _UrlKt.FRAGMENT_ENCODE_SET, true);
        this.lastSearchString = r42;
        if (r42.length() != 0) goto L12;
        this.lastSearchString = null;
        this.emptyView.title.setText(LocaleController.getString(R.string.NoRecentSearches));
    L13:
        updateSearchInterface();
        return;
    L12:
        this.emptyView.title.setText(LocaleController.formatString("NoResultFoundFor", R.string.NoResultFoundFor, new Object[]{this.lastSearchString}));
        goto L13
    L8:
        r1 = false;
        goto L9
    }

    private void saveRecentSearch() {
        int r2 = 0;
        SharedPreferences.Editor r0 = ApplicationLoader.applicationContext.getSharedPreferences("web_recent_search", 0).edit();
        r0.clear();
        r0.putInt(NotificationBadge.NewHtcHomeBadger.COUNT, this.recentSearches.size());
        int r1 = this.recentSearches.size();
    L3:
        if (r2 >= r1) goto L5;
        r0.putString("recent" + r2, this.recentSearches.get(r2));
        r2 = r2 + 1;
        goto L3
    L5:
        r0.apply();
    }

    private void searchBotUser(boolean r4) {
        if (this.searchingUser == false) goto L5;
        return;
    L5:
        this.searchingUser = true;
        TLRPC.TL_contacts_resolveUsername r0 = new TLRPC.TL_contacts_resolveUsername();
        MessagesController r1 = MessagesController.getInstance(this.currentAccount);
        if (r4 == false) goto L8;
        String r12 = r1.gifSearchBot;
    L9:
        r0.username = r12;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new PhotoPickerActivity$$ExternalSyntheticLambda7(this, r4));
        return;
    L8:
        r12 = r1.imageSearchBot;
        goto L9
    }

    private void searchImages(boolean r10, String r11, String r12, boolean r13) {
        if (this.searching == false) goto L7;
        this.searching = false;
        if (this.imageReqId == 0) goto L7;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.imageReqId, true);
        this.imageReqId = 0;
    L7:
        this.lastSearchImageString = r11;
        this.searching = true;
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        MessagesController r2 = MessagesController.getInstance(this.currentAccount);
        if (r10 == false) goto L10;
        String r22 = r2.gifSearchBot;
    L11:
        TLObject r02 = r0.getUserOrChat(r22);
        if ((r02 instanceof TLRPC.User) == true) goto L16;
        if (r13 == false) goto L29;
        searchBotUser(r10);
        return;
    L29:
        return;
    L16:
        TLRPC.User r8 = (TLRPC.User) r02;
        TLRPC.TL_messages_getInlineBotResults r132 = new TLRPC.TL_messages_getInlineBotResults();
        if (r11 != null) goto L19;
        String r03 = _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        r132.query = r03;
        r132.bot = MessagesController.getInstance(this.currentAccount).getInputUser(r8);
        r132.offset = r12;
        ChatActivity r122 = this.chatActivity;
        if (r122 == null) goto L26;
        long r23 = r122.getDialogId();
        if (DialogObject.isEncryptedDialog(r23) == false) goto L25;
        r132.peer = new TLRPC.TL_inputPeerEmpty();
    L27:
        int r6 = this.lastSearchToken + 1;
        this.lastSearchToken = r6;
        this.imageReqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r132, new PhotoPickerActivity$$ExternalSyntheticLambda6(this, r11, r6, r10, r8));
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(this.imageReqId, this.classGuid);
        return;
    L25:
        r132.peer = getMessagesController().getInputPeer(r23);
        goto L27
    L26:
        r132.peer = new TLRPC.TL_inputPeerEmpty();
        goto L27
    L19:
        r03 = r11;
        goto L20
    L10:
        r22 = r2.imageSearchBot;
        goto L11
    }

    private void sendSelectedPhotos(boolean r3, int r4, int r5) {
        if (this.selectedPhotos.isEmpty() == false) goto L5;
        return;
    L5:
        if (this.delegate != null) goto L7;
        return;
    L7:
        if (this.sendPressed == true) goto L21;
        applyCaption();
        this.sendPressed = true;
        this.delegate.actionButtonPressed(false, r3, r4, r5);
        if (this.selectPhotoType == PhotoAlbumPickerActivity.SELECT_TYPE_WALLPAPER) goto L19;
        PhotoPickerActivityDelegate r32 = this.delegate;
        if (r32 != null) goto L14;
    L15:
        finishFragment();
        return;
    L14:
        if (r32.canFinishFragment() == true) goto L15;
        return;
    L19:
        return;
    }

    private boolean showCommentTextView(boolean r13, boolean r14) {
        if (this.commentTextView != null) goto L6;
        return false;
    L6:
        if (this.frameLayout2.getTag() == null) goto L8;
        boolean r0 = true;
    L9:
        if (r13 != r0) goto L11;
        return false;
    L11:
        AnimatorSet r02 = this.animatorSet;
        if (r02 == null) goto L14;
        r02.cancel();
    L14:
        FrameLayout r03 = this.frameLayout2;
        if (r13 == false) goto L17;
        Integer r3 = 1;
    L18:
        r03.setTag(r3);
        if (this.commentTextView.getEditText().isFocused() == false) goto L21;
        AndroidUtilities.hideKeyboard(this.commentTextView.getEditText());
    L21:
        this.commentTextView.hidePopup(true);
        if (r13 == false) goto L24;
        this.frameLayout2.setVisibility(0);
        this.writeButtonContainer.setVisibility(0);
    L24:
        float r32 = 0.0f;
        float r4 = 0.2f;
        float r5 = 1.0f;
        if (r14 == false) goto L58;
        this.animatorSet = new AnimatorSet();
        ArrayList r142 = new ArrayList();
        FrameLayout r6 = this.writeButtonContainer;
        Property r7 = View.SCALE_X;
        if (r13 == false) goto L29;
        float r8 = 1.0f;
    L30:
        r142.add(ObjectAnimator.ofFloat(r6, r7, new float[]{r8}));
        FrameLayout r62 = this.writeButtonContainer;
        Property r82 = View.SCALE_Y;
        if (r13 == false) goto L33;
        float r9 = 1.0f;
    L34:
        r142.add(ObjectAnimator.ofFloat(r62, r82, new float[]{r9}));
        FrameLayout r63 = this.writeButtonContainer;
        Property r92 = View.ALPHA;
        if (r13 == false) goto L37;
        float r10 = 1.0f;
    L38:
        r142.add(ObjectAnimator.ofFloat(r63, r92, new float[]{r10}));
        View r64 = this.selectedCountView;
        if (r13 == false) goto L41;
        float r102 = 1.0f;
    L42:
        r142.add(ObjectAnimator.ofFloat(r64, r7, new float[]{r102}));
        View r65 = this.selectedCountView;
        if (r13 == false) goto L45;
        r4 = 1.0f;
    L45:
        r142.add(ObjectAnimator.ofFloat(r65, r82, new float[]{r4}));
        View r42 = this.selectedCountView;
        if (r13 == true) goto L49;
        r5 = 0.0f;
    L49:
        r142.add(ObjectAnimator.ofFloat(r42, r92, new float[]{r5}));
        FrameLayout r43 = this.frameLayout2;
        Property r52 = View.TRANSLATION_Y;
        if (r13 == false) goto L52;
        float r66 = 0.0f;
    L53:
        r142.add(ObjectAnimator.ofFloat(r43, r52, new float[]{r66}));
        View r44 = this.shadow;
        if (r13 == true) goto L57;
        r32 = AndroidUtilities.dp(48.0f);
    L57:
        r142.add(ObjectAnimator.ofFloat(r44, r52, new float[]{r32}));
        this.animatorSet.playTogether(r142);
        this.animatorSet.setInterpolator(new DecelerateInterpolator());
        this.animatorSet.setDuration(180);
        this.animatorSet.addListener(new AnonymousClass16(this, r13));
        this.animatorSet.start();
    L92:
        return true;
    L52:
        r66 = AndroidUtilities.dp(48.0f);
        goto L53
    L41:
        r102 = 0.2f;
        goto L42
    L37:
        r10 = 0.0f;
        goto L38
    L33:
        r9 = 0.2f;
        goto L34
    L29:
        r8 = 0.2f;
        goto L30
    L58:
        FrameLayout r143 = this.writeButtonContainer;
        if (r13 == false) goto L61;
        float r1 = 1.0f;
    L62:
        r143.setScaleX(r1);
        FrameLayout r144 = this.writeButtonContainer;
        if (r13 == false) goto L65;
        float r12 = 1.0f;
    L66:
        r144.setScaleY(r12);
        FrameLayout r145 = this.writeButtonContainer;
        if (r13 == false) goto L69;
        float r15 = 1.0f;
    L70:
        r145.setAlpha(r15);
        View r146 = this.selectedCountView;
        if (r13 == false) goto L73;
        float r16 = 1.0f;
    L74:
        r146.setScaleX(r16);
        View r147 = this.selectedCountView;
        if (r13 == false) goto L77;
        r4 = 1.0f;
    L77:
        r147.setScaleY(r4);
        View r148 = this.selectedCountView;
        if (r13 == true) goto L81;
        r5 = 0.0f;
    L81:
        r148.setAlpha(r5);
        FrameLayout r149 = this.frameLayout2;
        if (r13 == false) goto L84;
        float r17 = 0.0f;
    L85:
        r149.setTranslationY(r17);
        View r1410 = this.shadow;
        if (r13 == true) goto L89;
        r32 = AndroidUtilities.dp(48.0f);
    L89:
        r1410.setTranslationY(r32);
        if (r13 == true) goto L92;
        this.frameLayout2.setVisibility(4);
        this.writeButtonContainer.setVisibility(4);
        goto L92
    L84:
        r17 = AndroidUtilities.dp(48.0f);
        goto L85
    L73:
        r16 = 0.2f;
        goto L74
    L69:
        r15 = 0.0f;
        goto L70
    L65:
        r12 = 0.2f;
        goto L66
    L61:
        r1 = 0.2f;
        goto L62
    L17:
        r3 = null;
        goto L18
    L8:
        r0 = false;
        goto L9
    }

    private void updateCheckedPhotoIndices() {
        if (this.allowIndices == false) goto L27;
        int r0 = this.listView.getChildCount();
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L33;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof PhotoAttachPhotoCell) == false) goto L20;
        PhotoAttachPhotoCell r4 = (PhotoAttachPhotoCell) r3;
        Integer r32 = (Integer) r3.getTag();
        MediaController.AlbumEntry r5 = this.selectedAlbum;
        int r6 = -1;
        if (r5 == null) goto L15;
        MediaController.PhotoEntry r33 = r5.photos.get(r32.intValue());
        if (this.allowIndices == false) goto L14;
        r6 = this.selectedPhotosOrder.indexOf(Integer.valueOf(r33.imageId));
    L14:
        r4.setNum(r6);
    L26:
        r2 = r2 + 1;
        goto L6
    L15:
        MediaController.SearchImage r34 = this.searchResult.get(r32.intValue());
        if (this.allowIndices == false) goto L18;
        r6 = this.selectedPhotosOrder.indexOf(r34.id);
    L18:
        r4.setNum(r6);
        goto L26
    L20:
        if ((r3 instanceof SharedDocumentCell) == false) goto L26;
        MediaController.PhotoEntry r42 = this.selectedAlbum.photos.get(((Integer) r3.getTag()).intValue());
        SharedDocumentCell r35 = (SharedDocumentCell) r3;
        if (this.selectedPhotosOrder.indexOf(Integer.valueOf(r42.imageId)) == 0) goto L24;
        boolean r43 = true;
    L25:
        r35.setChecked(r43, false);
        goto L26
    L24:
        r43 = false;
        goto L25
    L33:
        return;
    }

    private void updateSearchInterface() {
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
    L6:
        if (this.searching == false) goto L8;
    L16:
        this.emptyView.showProgress(true);
        return;
    L8:
        if (this.recentSearches.size() <= 0) goto L14;
        String r02 = this.lastSearchString;
        if (r02 == null) goto L16;
        if (TextUtils.isEmpty(r02) == true) goto L16;
    L14:
        this.emptyView.showProgress(false);
    }

    public void clearRecentSearch() {
        this.recentSearches.clear();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L5;
        r0.notifyDataSetChanged();
    L5:
        this.emptyView.showProgress(false);
        saveRecentSearch();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r21) {
        this.listSort = false;
        this.actionBar.setBackgroundColor(Theme.getColor(this.dialogBackgroundKey));
        this.actionBar.setTitleColor(Theme.getColor(this.textKey));
        this.actionBar.setItemsColor(Theme.getColor(this.textKey), false);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(this.selectorKey), false);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        MediaController.AlbumEntry r1 = this.selectedAlbum;
        if (r1 == null) goto L5;
        this.actionBar.setTitle(r1.bucketName);
    L10:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        if (this.isDocumentsPicker == false) goto L14;
        ActionBarMenuItem r12 = this.actionBar.createMenu().addItem(0, R.drawable.ic_ab_other);
        r12.setSubMenuDelegate(new AnonymousClass3(this));
        this.showAsListItem = r12.addSubItem(1, R.drawable.msg_list, LocaleController.getString(R.string.ShowAsList));
        r12.addSubItem(2, R.drawable.msg_openin, LocaleController.getString(R.string.OpenInExternalApp));
    L14:
        if (this.selectedAlbum != null) goto L17;
        ActionBarMenuItem r13 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass4(this));
        this.searchItem = r13;
        EditTextBoldCursor r14 = r13.getSearchField();
        r14.setTextColor(Theme.getColor(this.textKey));
        r14.setCursorColor(Theme.getColor(this.textKey));
        r14.setHintTextColor(Theme.getColor(Theme.key_chat_messagePanelHint));
    L17:
        if (this.selectedAlbum != null) goto L23;
        int r15 = this.type;
        if (r15 != 0) goto L21;
        this.searchItem.setSearchFieldHint(LocaleController.getString(R.string.SearchImagesTitle));
        goto L23
    L21:
        if (r15 != 1) goto L23;
        this.searchItem.setSearchFieldHint(LocaleController.getString(R.string.SearchGifsTitle));
    L23:
        AnonymousClass5 r16 = new AnonymousClass5(this, r21);
        this.sizeNotifierFrameLayout = r16;
        r16.setBackgroundColor(Theme.getColor(this.dialogBackgroundKey));
        this.fragmentView = this.sizeNotifierFrameLayout;
        RecyclerListView r17 = new RecyclerListView(r21);
        this.listView = r17;
        r17.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(50.0f));
        this.listView.setClipToPadding(false);
        this.listView.setHorizontalScrollBarEnabled(false);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setItemAnimator(null);
        this.listView.setLayoutAnimation(null);
        RecyclerListView r18 = this.listView;
        AnonymousClass6 r3 = new AnonymousClass6(this, r21, 4);
        this.layoutManager = r3;
        r18.setLayoutManager(r3);
        this.layoutManager.setSpanSizeLookup(new AnonymousClass7(this));
        this.sizeNotifierFrameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        RecyclerListView r19 = this.listView;
        ListAdapter r32 = new ListAdapter(this, r21);
        this.listAdapter = r32;
        r19.setAdapter(r32);
        this.listView.setGlowColor(Theme.getColor(this.dialogBackgroundKey));
        this.listView.setOnItemClickListener(new PhotoPickerActivity$$ExternalSyntheticLambda0(this));
        if (this.maxSelectedPhotos == 1) goto L26;
        this.listView.setOnItemLongClickListener(new PhotoPickerActivity$$ExternalSyntheticLambda1(this));
    L26:
        RecyclerViewItemRangeSelector r110 = new RecyclerViewItemRangeSelector(new AnonymousClass8(this));
        this.itemRangeSelector = r110;
        if (this.maxSelectedPhotos == 1) goto L29;
        this.listView.addOnItemTouchListener(r110);
    L29:
        AnonymousClass9 r111 = new AnonymousClass9(this, r21, getResourceProvider());
        this.flickerView = r111;
        r111.setAlpha(0.0f);
        this.flickerView.setVisibility(8);
        StickerEmptyView r112 = new StickerEmptyView(r21, this.flickerView, 1, getResourceProvider());
        this.emptyView = r112;
        r112.setAnimateLayoutChange(true);
        this.emptyView.title.setTypeface(Typeface.DEFAULT);
        this.emptyView.title.setTextSize(1, 16.0f);
        this.emptyView.title.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        this.emptyView.addView(this.flickerView, 0);
        MediaController.AlbumEntry r113 = this.selectedAlbum;
        StickerEmptyView r33 = this.emptyView;
        if (r113 == null) goto L32;
        r33.title.setText(LocaleController.getString(R.string.NoPhotos));
    L33:
        this.emptyView.showProgress(false, false);
        this.sizeNotifierFrameLayout.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 126.0f, 0.0f, 0.0f));
        this.listView.setOnScrollListener(new AnonymousClass10(this));
        if (this.selectedAlbum != null) goto L37;
        updateSearchInterface();
    L37:
        if (this.needsBottomLayout == false) goto L55;
        View r114 = new View(r21);
        this.shadow = r114;
        r114.setBackgroundResource(R.drawable.header_shadow_reverse);
        this.shadow.setTranslationY(AndroidUtilities.dp(48.0f));
        this.sizeNotifierFrameLayout.addView(this.shadow, LayoutHelper.createFrame(-1, 3.0f, 83, 0.0f, 0.0f, 0.0f, 48.0f));
        FrameLayout r115 = new FrameLayout(r21);
        this.frameLayout2 = r115;
        r115.setBackgroundColor(Theme.getColor(this.dialogBackgroundKey));
        this.frameLayout2.setVisibility(4);
        this.frameLayout2.setTranslationY(AndroidUtilities.dp(48.0f));
        this.sizeNotifierFrameLayout.addView(this.frameLayout2, LayoutHelper.createFrame(-1, 48, 83));
        this.frameLayout2.setOnTouchListener(new PhotoPickerActivity$$ExternalSyntheticLambda2());
        EditTextEmoji r116 = this.commentTextView;
        if (r116 == null) goto L41;
        r116.onDestroy();
    L41:
        SizeNotifierFrameLayout r34 = this.sizeNotifierFrameLayout;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L44;
    L47:
        boolean r6 = true;
    L48:
        this.commentTextView = new EditTextEmoji(r21, r34, null, 1, r6);
        this.commentTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MessagesController.getInstance(UserConfig.selectedAccount).maxCaptionLength)});
        this.commentTextView.allowEmojisForNonPremium(LocaleUtils.canUseLocalPremiumEmojis(this.currentAccount));
        this.commentTextView.setHint(LocaleController.getString(R.string.AddCaption));
        this.commentTextView.onResume();
        EditTextCaption r117 = this.commentTextView.getEditText();
        r117.setMaxLines(1);
        r117.setSingleLine(true);
        this.frameLayout2.addView(this.commentTextView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 84.0f, 0.0f));
        CharSequence r118 = this.caption;
        if (r118 == null) goto L51;
        this.commentTextView.setText(r118);
    L51:
        this.commentTextView.getEditText().addTextChangedListener(new AnonymousClass11(this));
        AnonymousClass12 r119 = new AnonymousClass12(this, r21);
        this.writeButtonContainer = r119;
        r119.setFocusable(true);
        this.writeButtonContainer.setFocusableInTouchMode(true);
        this.writeButtonContainer.setVisibility(4);
        this.writeButtonContainer.setScaleX(0.2f);
        this.writeButtonContainer.setScaleY(0.2f);
        this.writeButtonContainer.setAlpha(0.0f);
        this.sizeNotifierFrameLayout.addView(this.writeButtonContainer, LayoutHelper.createFrame(60, 60.0f, 85, 0.0f, 0.0f, 12.0f, 10.0f));
        this.writeButton = new ImageView(r21);
        Drawable r120 = UIUtil.createFabBackground(56, getThemedColor(Theme.key_chat_editMediaButton), getThemedColor(Theme.key_dialogFloatingButtonPressed));
        this.writeButtonDrawable = r120;
        this.writeButton.setBackgroundDrawable(r120);
        this.writeButton.setImageResource(R.drawable.attach_send);
        this.writeButton.setImportantForAccessibility(2);
        this.writeButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogFloatingIcon), PorterDuff.Mode.MULTIPLY));
        this.writeButton.setScaleType(ImageView.ScaleType.CENTER);
        this.writeButton.setOutlineProvider(new AnonymousClass13(this));
        this.writeButtonContainer.addView(this.writeButton, LayoutHelper.createFrame(56, 56.0f, 51, 2.0f, 0.0f, 0.0f, 0.0f));
        this.writeButton.setOnClickListener(new PhotoPickerActivity$$ExternalSyntheticLambda3(this));
        this.writeButton.setOnTouchListener(new PhotoPickerActivity$$ExternalSyntheticLambda4(this));
        this.writeButton.setOnLongClickListener(new PhotoPickerActivity$$ExternalSyntheticLambda5(this));
        this.textPaint.setTextSize(AndroidUtilities.dp(12.0f));
        this.textPaint.setTypeface(AndroidUtilities.bold());
        AnonymousClass15 r121 = new AnonymousClass15(this, r21);
        this.selectedCountView = r121;
        r121.setAlpha(0.0f);
        this.selectedCountView.setScaleX(0.2f);
        this.selectedCountView.setScaleY(0.2f);
        this.sizeNotifierFrameLayout.addView(this.selectedCountView, LayoutHelper.createFrame(42, 24.0f, 85, 0.0f, 0.0f, -2.0f, 9.0f));
        if (this.selectPhotoType == PhotoAlbumPickerActivity.SELECT_TYPE_ALL) goto L55;
        this.commentTextView.setVisibility(8);
        goto L55
    L44:
        if (LocaleUtils.canUseLocalPremiumEmojis(this.currentAccount) == true) goto L47;
        r6 = false;
    L55:
        if (this.selectedAlbum != null) goto L60;
        int r122 = this.type;
        if (r122 == 0) goto L60;
        if (r122 == 1) goto L60;
    L62:
        boolean r123 = false;
    L63:
        this.allowIndices = r123;
        this.listView.setEmptyView(this.emptyView);
        this.listView.setAnimateEmptyView(true, 0);
        updatePhotosButton(0);
        return this.fragmentView;
    L60:
        if (this.allowOrder == false) goto L62;
        r123 = true;
        goto L63
    L32:
        r33.title.setText(LocaleController.getString(R.string.NoRecentSearches));
        goto L33
    L5:
        int r124 = this.type;
        if (r124 != 0) goto L8;
        this.actionBar.setTitle(LocaleController.getString(R.string.SearchImagesTitle));
        goto L10
    L8:
        if (r124 != 1) goto L10;
        this.actionBar.setTitle(LocaleController.getString(R.string.SearchGifsTitle));
        goto L10
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.closeChats) goto L6;
        removeSelfFromStack(true);
        return;
    }

    public RecyclerListView getListView() {
        return this.listView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.sizeNotifierFrameLayout, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, this.dialogBackgroundKey));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, this.dialogBackgroundKey));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, this.textKey));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, this.textKey));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, this.selectorKey));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCH, null, null, null, null, this.textKey));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER, null, null, null, null, Theme.key_chat_messagePanelHint));
        ActionBarMenuItem r2 = this.searchItem;
        if (r2 == null) goto L6;
        EditTextBoldCursor r22 = r2.getSearchField();
    L5:
        EditTextBoldCursor r10 = r22;
        r1.add(new ThemeDescription(r10, ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, this.textKey));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, this.dialogBackgroundKey));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, null, new Drawable[]{Theme.chat_attachEmptyDrawable}, null, Theme.key_chat_attachEmptyImage));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, null, null, null, Theme.key_chat_attachPhotoBackground));
        return r1;
    L6:
        r22 = null;
        goto L5
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(Theme.key_windowBackgroundGray)) <= 0.721f) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        EditTextEmoji r0 = this.commentTextView;
        if (r0 == null) goto L11;
        if (r0.isPopupShowing() == false) goto L11;
        if (r2 == false) goto L12;
        this.commentTextView.hidePopup(true);
        return false;
    L12:
        return false;
    L11:
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.closeChats);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.closeChats);
        if (this.imageReqId == 0) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.imageReqId, true);
        this.imageReqId = 0;
    L5:
        EditTextEmoji r0 = this.commentTextView;
        if (r0 == null) goto L8;
        r0.onDestroy();
    L8:
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPanTranslationUpdate(float r2) {
        if (this.listView != null) goto L6;
        return;
    L6:
        if (this.commentTextView.isPopupShowing() == false) goto L9;
        this.fragmentView.setTranslationY(r2);
        this.listView.setTranslationY(0.0f);
        return;
    L9:
        this.listView.setTranslationY(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L5;
        r0.notifyDataSetChanged();
    L5:
        EditTextEmoji r02 = this.commentTextView;
        if (r02 == null) goto L8;
        r02.onResume();
    L8:
        ActionBarMenuItem r03 = this.searchItem;
        if (r03 == null) goto L15;
        r03.openSearch(true);
        if (TextUtils.isEmpty(this.initialSearchString) == true) goto L13;
        this.searchItem.setSearchFieldText(this.initialSearchString, false);
        this.initialSearchString = null;
        processSearch(this.searchItem.getSearchField());
    L13:
        getParentActivity().getWindow().setSoftInputMode(32);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        if (r1 == false) goto L7;
        ActionBarMenuItem r0 = this.searchItem;
        if (r0 == null) goto L8;
        AndroidUtilities.showKeyboard(r0.getSearchField());
        return;
    L8:
        return;
    }

    public void setCaption(CharSequence r1) {
        this.caption = r1;
        EditTextEmoji r0 = this.commentTextView;
        if (r0 == null) goto L6;
        r0.setText(r1);
        return;
    }

    public void setDelegate(PhotoPickerActivityDelegate r1) {
        this.delegate = r1;
    }

    public void setDocumentsPicker(boolean r1) {
        this.isDocumentsPicker = r1;
    }

    public void setInitialSearchString(String r1) {
        this.initialSearchString = r1;
    }

    public void setLayoutViews(FrameLayout r1, FrameLayout r2, View r3, View r4, EditTextEmoji r5) {
        this.frameLayout2 = r1;
        this.writeButtonContainer = r2;
        this.commentTextView = r5;
        this.selectedCountView = r3;
        this.shadow = r4;
        this.needsBottomLayout = false;
    }

    public void setMaxSelectedPhotos(int r1, boolean r2) {
        this.maxSelectedPhotos = r1;
        this.allowOrder = r2;
        if (r1 > 0) goto L5;
        return;
    L5:
        if (this.type != 1) goto L9;
        this.maxSelectedPhotos = 1;
        return;
    }

    public void setSearchDelegate(PhotoPickerActivitySearchDelegate r1) {
        this.searchDelegate = r1;
    }

    public void updatePhotosButton(int r11) {
        int r0 = this.selectedPhotos.size();
        View r1 = this.selectedCountView;
        boolean r4 = true;
        if (r0 != 0) goto L10;
        r1.setPivotX(0.0f);
        this.selectedCountView.setPivotY(0.0f);
        if (r11 != 0) goto L8;
        r4 = false;
    L8:
        showCommentTextView(false, r4);
        return;
    L10:
        r1.invalidate();
        if (r11 == 0) goto L13;
        boolean r02 = true;
    L15:
        if (showCommentTextView(true, r02) == true) goto L26;
        if (r11 == 0) goto L26;
        this.selectedCountView.setPivotX(AndroidUtilities.dp(21.0f));
        this.selectedCountView.setPivotY(AndroidUtilities.dp(12.0f));
        AnimatorSet r03 = new AnimatorSet();
        View r12 = this.selectedCountView;
        Property r3 = View.SCALE_X;
        float r5 = 0.9f;
        if (r11 != 1) goto L20;
        float r7 = 1.1f;
    L21:
        ObjectAnimator r13 = ObjectAnimator.ofFloat(r12, r3, new float[]{r7, 1.0f});
        View r10 = this.selectedCountView;
        Property r32 = View.SCALE_Y;
        if (r11 != 1) goto L24;
        r5 = 1.1f;
    L24:
        r03.playTogether(new Animator[]{r13, ObjectAnimator.ofFloat(r10, r32, new float[]{r5, 1.0f})});
        r03.setInterpolator(new OvershootInterpolator());
        r03.setDuration(180);
        r03.start();
        return;
    L20:
        r7 = 0.9f;
    L26:
        this.selectedCountView.setPivotX(0.0f);
        this.selectedCountView.setPivotY(0.0f);
        return;
    L13:
        r02 = false;
        goto L15
    }
}
