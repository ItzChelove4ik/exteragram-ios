package org.telegram.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.ui.UIUtil;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.PhotoPickerAlbumsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.EditTextEmoji;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.PhotoPickerActivity;

/* loaded from: classes6.dex */
public class PhotoAlbumPickerActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    public static int SELECT_TYPE_ALL = 0;
    public static int SELECT_TYPE_AVATAR = 1;
    public static int SELECT_TYPE_AVATAR_VIDEO = 3;
    public static int SELECT_TYPE_QR = 10;
    public static int SELECT_TYPE_WALLPAPER = 2;
    private ArrayList<MediaController.AlbumEntry> albumsSorted;
    private boolean allowCaption;
    private boolean allowGifs;
    private boolean allowOrder;
    private boolean allowSearchImages;
    private CharSequence caption;
    private ChatActivity chatActivity;
    private int columnsCount;
    private EditTextEmoji commentTextView;
    private PhotoAlbumPickerActivityDelegate delegate;
    private TextView emptyView;
    private FrameLayout frameLayout2;
    private ActionBarMenuSubItem[] itemCells;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private boolean loading;
    private int maxSelectedPhotos;
    private Paint paint;
    private FrameLayout progressView;
    private RectF rect;
    private int selectPhotoType;
    private View selectedCountView;
    private HashMap<Object, Object> selectedPhotos;
    private ArrayList<Object> selectedPhotosOrder;
    private ActionBarPopupWindow.ActionBarPopupWindowLayout sendPopupLayout;
    private ActionBarPopupWindow sendPopupWindow;
    private boolean sendPressed;
    private View shadow;
    private SizeNotifierFrameLayout sizeNotifierFrameLayout;
    private TextPaint textPaint;
    private ImageView writeButton;
    private FrameLayout writeButtonContainer;
    private Drawable writeButtonDrawable;

    /* renamed from: org.telegram.ui.PhotoAlbumPickerActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PhotoAlbumPickerActivity this$0;

        public AnonymousClass1(PhotoAlbumPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r3 != 1) goto L13;
            if (PhotoAlbumPickerActivity.m16774$$Nest$fgetdelegate(this.this$0) == null) goto L16;
            this.this$0.finishFragment(false);
            PhotoAlbumPickerActivity.m16774$$Nest$fgetdelegate(this.this$0).startPhotoSelectActivity();
            return;
        L16:
            return;
        L13:
            if (r3 != 2) goto L17;
            PhotoAlbumPickerActivity.m16784$$Nest$mopenPhotoPicker(this.this$0, null, 0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.PhotoAlbumPickerActivity$10, reason: invalid class name */
    public class AnonymousClass10 implements PhotoPickerActivity.PhotoPickerActivityDelegate {
        final /* synthetic */ PhotoAlbumPickerActivity this$0;
        final /* synthetic */ ArrayList val$order;
        final /* synthetic */ HashMap val$photos;

        public AnonymousClass10(PhotoAlbumPickerActivity r1, HashMap r2, ArrayList r3) {
            this.this$0 = r1;
            this.val$photos = r2;
            this.val$order = r3;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void actionButtonPressed(boolean r1, boolean r2, int r3, int r4) {
            this.this$0.removeSelfFromStack();
            if (r1 == true) goto L6;
            PhotoAlbumPickerActivity.m16785$$Nest$msendSelectedPhotos(this.this$0, this.val$photos, this.val$order, r2, r3);
            return;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void onCaptionChanged(CharSequence r2) {
            EditTextEmoji r0 = PhotoAlbumPickerActivity.m16773$$Nest$fgetcommentTextView(this.this$0);
            PhotoAlbumPickerActivity.m16782$$Nest$fputcaption(this.this$0, r2);
            r0.setText(r2);
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void selectedPhotosChanged() {
        }
    }

    /* renamed from: org.telegram.ui.PhotoAlbumPickerActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        private boolean ignoreLayout;
        private int lastNotifyWidth;
        final /* synthetic */ PhotoAlbumPickerActivity this$0;

        public AnonymousClass2(PhotoAlbumPickerActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r9, int r10, int r11, int r12, int r13) {
            int r122 = r12 - r10;
            if (this.lastNotifyWidth == r122) goto L9;
            this.lastNotifyWidth = r122;
            if (PhotoAlbumPickerActivity.m16780$$Nest$fgetsendPopupWindow(this.this$0) == null) goto L9;
            if (PhotoAlbumPickerActivity.m16780$$Nest$fgetsendPopupWindow(this.this$0).isShowing() == false) goto L9;
            PhotoAlbumPickerActivity.m16780$$Nest$fgetsendPopupWindow(this.this$0).dismiss();
        L9:
            int r92 = getChildCount();
            int r0 = 0;
            if (AndroidUtilities.dp(20.0f) >= 0) goto L12;
        L16:
            int r102 = 0;
        L17:
            setBottomClip(r102);
        L18:
            if (r0 >= r92) goto L55;
            View r1 = getChildAt(r0);
            if (r1.getVisibility() == 8) goto L54;
            FrameLayout.LayoutParams r2 = (FrameLayout.LayoutParams) r1.getLayoutParams();
            int r3 = r1.getMeasuredWidth();
            int r4 = r1.getMeasuredHeight();
            int r5 = r2.gravity;
            if (r5 != (-1)) goto L25;
            r5 = 51;
        L25:
            int r6 = r5 & 112;
            int r52 = r5 & 7;
            if (r52 != 1) goto L28;
            int r53 = ((r122 - r3) / 2) + r2.leftMargin;
            int r7 = r2.rightMargin;
        L31:
            int r54 = r53 - r7;
        L34:
            if (r6 != 16) goto L36;
            int r62 = ((((r13 - r102) - r11) - r4) / 2) + r2.topMargin;
            int r22 = r2.bottomMargin;
        L41:
            int r23 = r62 - r22;
        L45:
            if (PhotoAlbumPickerActivity.m16773$$Nest$fgetcommentTextView(this.this$0) != null) goto L47;
        L53:
            r1.layout(r54, r23, r3 + r54, r4 + r23);
            goto L54
        L47:
            if (PhotoAlbumPickerActivity.m16773$$Nest$fgetcommentTextView(this.this$0).isPopupView(r1) == false) goto L53;
            if (AndroidUtilities.isTablet() == false) goto L52;
            int r24 = getMeasuredHeight();
            int r63 = r1.getMeasuredHeight();
        L51:
            r23 = r24 - r63;
            goto L53
        L52:
            r24 = getMeasuredHeight();
            r63 = r1.getMeasuredHeight();
            goto L51
        L36:
            if (r6 != 48) goto L38;
            r23 = r2.topMargin + getPaddingTop();
            goto L45
        L38:
            if (r6 == 80) goto L40;
            r23 = r2.topMargin;
            goto L45
        L40:
            r62 = ((r13 - r102) - r11) - r4;
            r22 = r2.bottomMargin;
            goto L41
        L28:
            if (r52 == 5) goto L30;
            r54 = r2.leftMargin + getPaddingLeft();
            goto L34
        L30:
            r53 = (r122 - r3) - r2.rightMargin;
            r7 = getPaddingRight();
        L54:
            r0 = r0 + 1;
            goto L18
        L55:
            notifyHeightChanged();
            return;
        L12:
            if (AndroidUtilities.isInMultiwindow == true) goto L16;
            if (AndroidUtilities.isTablet() == true) goto L16;
            r102 = PhotoAlbumPickerActivity.m16773$$Nest$fgetcommentTextView(this.this$0).getEmojiPadding();
            goto L17
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r12, int r13) {
            int r0 = View.MeasureSpec.getSize(r12);
            int r1 = View.MeasureSpec.getSize(r13);
            setMeasuredDimension(r0, r1);
            int r3 = 0;
            if (AndroidUtilities.dp(20.0f) >= 0) goto L5;
            this.ignoreLayout = true;
            PhotoAlbumPickerActivity.m16773$$Nest$fgetcommentTextView(this.this$0).hideEmojiView();
            this.ignoreLayout = false;
        L7:
            int r9 = r13;
            int r132 = getChildCount();
        L10:
            if (r3 >= r132) goto L36;
            View r6 = this.getChildAt(r3);
            if (r6 != null) goto L14;
        L15:
            AnonymousClass2 r5 = this;
            int r7 = r12;
        L35:
            r3 = r3 + 1;
            AnonymousClass2 this = r5;
            r12 = r7;
            goto L10
        L14:
            if (r6.getVisibility() == 8) goto L15;
            if (PhotoAlbumPickerActivity.m16773$$Nest$fgetcommentTextView(this.this$0) != null) goto L19;
        L34:
            r5 = this;
            r7 = r12;
            r5.measureChildWithMargins(r6, r7, 0, r9, 0);
            goto L35
        L19:
            if (PhotoAlbumPickerActivity.m16773$$Nest$fgetcommentTextView(this.this$0).isPopupView(r6) == false) goto L34;
            if (AndroidUtilities.isInMultiwindow == true) goto L27;
            if (AndroidUtilities.isTablet() == true) goto L27;
            r6.measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r6.getLayoutParams().height, TLObject.FLAG_30));
        L27:
            if (AndroidUtilities.isTablet() == false) goto L33;
            int r2 = View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30);
            if (AndroidUtilities.isTablet() == false) goto L31;
            float r52 = 200.0f;
        L32:
            r6.measure(r2, View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(r52), (r1 - AndroidUtilities.statusBarHeight) + this.getPaddingTop()), TLObject.FLAG_30));
            goto L15
        L31:
            r52 = 320.0f;
            goto L32
        L33:
            r6.measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((r1 - AndroidUtilities.statusBarHeight) + this.getPaddingTop(), TLObject.FLAG_30));
            goto L15
        L36:
            return;
        L5:
            if (AndroidUtilities.isInMultiwindow == true) goto L7;
            r1 = r1 - PhotoAlbumPickerActivity.m16773$$Nest$fgetcommentTextView(this.this$0).getEmojiPadding();
            r13 = View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30);
            goto L7
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.PhotoAlbumPickerActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        final /* synthetic */ PhotoAlbumPickerActivity this$0;

        public AnonymousClass3(PhotoAlbumPickerActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
            super.onInitializeAccessibilityNodeInfo(r3);
            r3.setText(LocaleController.formatPluralString("AccDescrSendPhotos", PhotoAlbumPickerActivity.m16778$$Nest$fgetselectedPhotos(this.this$0).size(), new Object[0]));
            r3.setClassName(Button.class.getName());
            r3.setLongClickable(true);
            r3.setClickable(true);
        }
    }

    /* renamed from: org.telegram.ui.PhotoAlbumPickerActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ViewOutlineProvider {
        final /* synthetic */ PhotoAlbumPickerActivity this$0;

        public AnonymousClass4(PhotoAlbumPickerActivity r1) {
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

    /* renamed from: org.telegram.ui.PhotoAlbumPickerActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements View.OnTouchListener {
        private Rect popupRect;
        final /* synthetic */ PhotoAlbumPickerActivity this$0;

        public AnonymousClass5(PhotoAlbumPickerActivity r1) {
            this.this$0 = r1;
            this.popupRect = new Rect();
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View r2, MotionEvent r3) {
            if (r3.getActionMasked() == 0) goto L5;
            return false;
        L5:
            if (PhotoAlbumPickerActivity.m16780$$Nest$fgetsendPopupWindow(this.this$0) != null) goto L7;
            return false;
        L7:
            if (PhotoAlbumPickerActivity.m16780$$Nest$fgetsendPopupWindow(this.this$0).isShowing() == false) goto L15;
            r2.getHitRect(this.popupRect);
            if (this.popupRect.contains((int) r3.getX(), (int) r3.getY()) == true) goto L16;
            PhotoAlbumPickerActivity.m16780$$Nest$fgetsendPopupWindow(this.this$0).dismiss();
            return false;
        L16:
            return false;
        L15:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.PhotoAlbumPickerActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends View {
        final /* synthetic */ PhotoAlbumPickerActivity this$0;

        public AnonymousClass6(PhotoAlbumPickerActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r11) {
            String r0 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(Math.max(1, PhotoAlbumPickerActivity.m16779$$Nest$fgetselectedPhotosOrder(this.this$0).size()))});
            int r2 = Math.max(AndroidUtilities.dp(16.0f) + ((int) Math.ceil(PhotoAlbumPickerActivity.m16781$$Nest$fgettextPaint(this.this$0).measureText(r0))), AndroidUtilities.dp(24.0f));
            int r3 = getMeasuredWidth() / 2;
            getMeasuredHeight();
            PhotoAlbumPickerActivity.m16781$$Nest$fgettextPaint(this.this$0).setColor(Theme.getColor(Theme.key_dialogRoundCheckBoxCheck));
            PhotoAlbumPickerActivity.m16776$$Nest$fgetpaint(this.this$0).setColor(Theme.getColor(Theme.key_dialogBackground));
            int r22 = r2 / 2;
            PhotoAlbumPickerActivity.m16777$$Nest$fgetrect(this.this$0).set(r3 - r22, 0.0f, r22 + r3, getMeasuredHeight());
            r11.drawRoundRect(PhotoAlbumPickerActivity.m16777$$Nest$fgetrect(this.this$0), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), PhotoAlbumPickerActivity.m16776$$Nest$fgetpaint(this.this$0));
            PhotoAlbumPickerActivity.m16776$$Nest$fgetpaint(this.this$0).setColor(Theme.getColor(Theme.key_dialogRoundCheckBox));
            PhotoAlbumPickerActivity.m16777$$Nest$fgetrect(this.this$0).set(r5 + AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), r2 - AndroidUtilities.dp(2.0f), getMeasuredHeight() - AndroidUtilities.dp(2.0f));
            r11.drawRoundRect(PhotoAlbumPickerActivity.m16777$$Nest$fgetrect(this.this$0), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), PhotoAlbumPickerActivity.m16776$$Nest$fgetpaint(this.this$0));
            r11.drawText(r0, r3 - (r1 / 2), AndroidUtilities.dp(16.2f), PhotoAlbumPickerActivity.m16781$$Nest$fgettextPaint(this.this$0));
        }
    }

    /* renamed from: org.telegram.ui.PhotoAlbumPickerActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ PhotoAlbumPickerActivity this$0;

        public AnonymousClass7(PhotoAlbumPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            PhotoAlbumPickerActivity.m16783$$Nest$mfixLayoutInternal(this.this$0);
            if (PhotoAlbumPickerActivity.m16775$$Nest$fgetlistView(this.this$0) == null) goto L7;
            PhotoAlbumPickerActivity.m16775$$Nest$fgetlistView(this.this$0).getViewTreeObserver().removeOnPreDrawListener(this);
            return true;
        L7:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.PhotoAlbumPickerActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements PhotoPickerActivity.PhotoPickerActivityDelegate {
        final /* synthetic */ PhotoAlbumPickerActivity this$0;

        public AnonymousClass8(PhotoAlbumPickerActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void actionButtonPressed(boolean r1, boolean r2, int r3, int r4) {
            this.this$0.removeSelfFromStack();
            if (r1 == true) goto L6;
            PhotoAlbumPickerActivity r12 = this.this$0;
            PhotoAlbumPickerActivity.m16785$$Nest$msendSelectedPhotos(r12, PhotoAlbumPickerActivity.m16778$$Nest$fgetselectedPhotos(r12), PhotoAlbumPickerActivity.m16779$$Nest$fgetselectedPhotosOrder(this.this$0), r2, r3);
            return;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void onCaptionChanged(CharSequence r2) {
            EditTextEmoji r0 = PhotoAlbumPickerActivity.m16773$$Nest$fgetcommentTextView(this.this$0);
            PhotoAlbumPickerActivity.m16782$$Nest$fputcaption(this.this$0, r2);
            r0.setText(r2);
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void selectedPhotosChanged() {
            PhotoAlbumPickerActivity.m16786$$Nest$mupdatePhotosButton(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.PhotoAlbumPickerActivity$9, reason: invalid class name */
    public class AnonymousClass9 implements PhotoPickerActivity.PhotoPickerActivityDelegate {
        final /* synthetic */ PhotoAlbumPickerActivity this$0;
        final /* synthetic */ ArrayList val$order;
        final /* synthetic */ HashMap val$photos;

        public AnonymousClass9(PhotoAlbumPickerActivity r1, HashMap r2, ArrayList r3) {
            this.this$0 = r1;
            this.val$photos = r2;
            this.val$order = r3;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void actionButtonPressed(boolean r1, boolean r2, int r3, int r4) {
            this.this$0.removeSelfFromStack();
            if (r1 == true) goto L6;
            PhotoAlbumPickerActivity.m16785$$Nest$msendSelectedPhotos(this.this$0, this.val$photos, this.val$order, r2, r3);
            return;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void onCaptionChanged(CharSequence r2) {
            EditTextEmoji r0 = PhotoAlbumPickerActivity.m16773$$Nest$fgetcommentTextView(this.this$0);
            PhotoAlbumPickerActivity.m16782$$Nest$fputcaption(this.this$0, r2);
            r0.setText(r2);
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void selectedPhotosChanged() {
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ PhotoAlbumPickerActivity this$0;

        /* renamed from: $r8$lambda$hu4vJI-GLjwbLOkH9f0rDPL98xI, reason: not valid java name */
        public static /* synthetic */ void m16787$r8$lambda$hu4vJIGLjwbLOkH9f0rDPL98xI(ListAdapter r0, MediaController.AlbumEntry r1) {
            r0.lambda$onCreateViewHolder$0(r1);
        }

        public ListAdapter(PhotoAlbumPickerActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(MediaController.AlbumEntry r2) {
            PhotoAlbumPickerActivity.m16784$$Nest$mopenPhotoPicker(this.this$0, r2, 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (PhotoAlbumPickerActivity.m16771$$Nest$fgetalbumsSorted(this.this$0) != null) goto L5;
            return 0;
        L5:
            return (int) Math.ceil(PhotoAlbumPickerActivity.m16771$$Nest$fgetalbumsSorted(this.this$0).size() / PhotoAlbumPickerActivity.m16772$$Nest$fgetcolumnsCount(this.this$0));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
            PhotoPickerAlbumsCell r42 = (PhotoPickerAlbumsCell) r4.itemView;
            r42.setAlbumsCount(PhotoAlbumPickerActivity.m16772$$Nest$fgetcolumnsCount(this.this$0));
            int r0 = 0;
        L4:
            if (r0 >= PhotoAlbumPickerActivity.m16772$$Nest$fgetcolumnsCount(this.this$0)) goto L10;
            int r1 = (PhotoAlbumPickerActivity.m16772$$Nest$fgetcolumnsCount(this.this$0) * r5) + r0;
            if (r1 >= PhotoAlbumPickerActivity.m16771$$Nest$fgetalbumsSorted(this.this$0).size()) goto L8;
            r42.setAlbum(r0, (MediaController.AlbumEntry) PhotoAlbumPickerActivity.m16771$$Nest$fgetalbumsSorted(this.this$0).get(r1));
        L9:
            r0 = r0 + 1;
            goto L4
        L8:
            r42.setAlbum(r0, null);
            goto L9
        L10:
            r42.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            PhotoPickerAlbumsCell r12 = new PhotoPickerAlbumsCell(this.mContext);
            r12.setDelegate(new PhotoAlbumPickerActivity$ListAdapter$$ExternalSyntheticLambda0(this));
            return new RecyclerListView.Holder(r12);
        }
    }

    public interface PhotoAlbumPickerActivityDelegate {
        void didSelectPhotos(ArrayList<SendMessagesHelper.SendingMediaInfo> r1, boolean r2, int r3);

        void startPhotoSelectActivity();
    }

    public static /* synthetic */ void $r8$lambda$0JRSwOJgGO2mFYCdEWEAfKrxwAc(PhotoAlbumPickerActivity r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    /* renamed from: $r8$lambda$0VZbYeGjzCfTycUF10rT-CkWue0, reason: not valid java name */
    public static /* synthetic */ void m16768$r8$lambda$0VZbYeGjzCfTycUF10rTCkWue0(PhotoAlbumPickerActivity r0, boolean r1, int r2, int r3) {
        r0.lambda$createView$2(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$4MoU0zFzx1jCCJMBFAoZICFPUz4(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$MI661QVeZSfZC2V0mcV93qxhc4g(PhotoAlbumPickerActivity r0, KeyEvent r1) {
        r0.lambda$createView$5(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$OhxBm6EbBAJ4AfISCsNf77oR8aY(PhotoAlbumPickerActivity r0, View r1, MotionEvent r2) {
        return r0.lambda$createView$4(r1, r2);
    }

    /* renamed from: $r8$lambda$YZz6451RuRRNMrM7rW-GGWTCB5g, reason: not valid java name */
    public static /* synthetic */ boolean m16769$r8$lambda$YZz6451RuRRNMrM7rWGGWTCB5g(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ boolean $r8$lambda$awjXssKUrEvc5SHS5FP4HePrS1E(PhotoAlbumPickerActivity r0, View r1) {
        return r0.lambda$createView$8(r1);
    }

    /* renamed from: $r8$lambda$cXusyrIO0wrKBYf-3Ij9jpx-_XY, reason: not valid java name */
    public static /* synthetic */ void m16770$r8$lambda$cXusyrIO0wrKBYf3Ij9jpx_XY(PhotoAlbumPickerActivity r0, int r1, View r2) {
        r0.lambda$createView$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kJ3Gn2nBJLlrtX59MWoajt9_fOY(PhotoAlbumPickerActivity r0, boolean r1, int r2, int r3) {
        r0.lambda$createView$6(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetalbumsSorted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16771$$Nest$fgetalbumsSorted(PhotoAlbumPickerActivity r0) {
        return r0.albumsSorted;
    }

    /* renamed from: -$$Nest$fgetcolumnsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16772$$Nest$fgetcolumnsCount(PhotoAlbumPickerActivity r0) {
        return r0.columnsCount;
    }

    /* renamed from: -$$Nest$fgetcommentTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextEmoji m16773$$Nest$fgetcommentTextView(PhotoAlbumPickerActivity r0) {
        return r0.commentTextView;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoAlbumPickerActivityDelegate m16774$$Nest$fgetdelegate(PhotoAlbumPickerActivity r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m16775$$Nest$fgetlistView(PhotoAlbumPickerActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetpaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m16776$$Nest$fgetpaint(PhotoAlbumPickerActivity r0) {
        return r0.paint;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m16777$$Nest$fgetrect(PhotoAlbumPickerActivity r0) {
        return r0.rect;
    }

    /* renamed from: -$$Nest$fgetselectedPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16778$$Nest$fgetselectedPhotos(PhotoAlbumPickerActivity r0) {
        return r0.selectedPhotos;
    }

    /* renamed from: -$$Nest$fgetselectedPhotosOrder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16779$$Nest$fgetselectedPhotosOrder(PhotoAlbumPickerActivity r0) {
        return r0.selectedPhotosOrder;
    }

    /* renamed from: -$$Nest$fgetsendPopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarPopupWindow m16780$$Nest$fgetsendPopupWindow(PhotoAlbumPickerActivity r0) {
        return r0.sendPopupWindow;
    }

    /* renamed from: -$$Nest$fgettextPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextPaint m16781$$Nest$fgettextPaint(PhotoAlbumPickerActivity r0) {
        return r0.textPaint;
    }

    /* renamed from: -$$Nest$fputcaption, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16782$$Nest$fputcaption(PhotoAlbumPickerActivity r0, CharSequence r1) {
        r0.caption = r1;
    }

    /* renamed from: -$$Nest$mfixLayoutInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16783$$Nest$mfixLayoutInternal(PhotoAlbumPickerActivity r0) {
        r0.fixLayoutInternal();
    }

    /* renamed from: -$$Nest$mopenPhotoPicker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16784$$Nest$mopenPhotoPicker(PhotoAlbumPickerActivity r0, MediaController.AlbumEntry r1, int r2) {
        r0.openPhotoPicker(r1, r2);
    }

    /* renamed from: -$$Nest$msendSelectedPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16785$$Nest$msendSelectedPhotos(PhotoAlbumPickerActivity r0, HashMap r1, ArrayList r2, boolean r3, int r4) {
        r0.sendSelectedPhotos(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$mupdatePhotosButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16786$$Nest$mupdatePhotosButton(PhotoAlbumPickerActivity r0) {
        r0.updatePhotosButton();
    }

    static {
    }

    public PhotoAlbumPickerActivity(int r3, boolean r4, boolean r5, ChatActivity r6) {
        this.selectedPhotos = new HashMap();
        this.selectedPhotosOrder = new ArrayList();
        this.albumsSorted = null;
        this.loading = false;
        this.columnsCount = 2;
        this.allowSearchImages = true;
        this.allowOrder = true;
        this.textPaint = new TextPaint(1);
        this.rect = new RectF();
        this.paint = new Paint(1);
        this.chatActivity = r6;
        this.selectPhotoType = r3;
        this.allowGifs = r4;
        this.allowCaption = r5;
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
        this.columnsCount = 2;
        if (AndroidUtilities.isTablet() == false) goto L8;
    L12:
        this.listAdapter.notifyDataSetChanged();
        return;
    L8:
        if (r0 != 3) goto L10;
    L11:
        this.columnsCount = 4;
        goto L12
    L10:
        if (r0 != 1) goto L12;
        goto L11
    }

    private /* synthetic */ void lambda$createView$2(boolean r2, int r3, int r4) {
        sendSelectedPhotos(this.selectedPhotos, this.selectedPhotosOrder, r2, r3);
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$3(View r4) {
        ChatActivity r42 = this.chatActivity;
        if (r42 != null) goto L5;
    L8:
        sendSelectedPhotos(this.selectedPhotos, this.selectedPhotosOrder, true, 0);
        finishFragment();
        return;
    L5:
        if (r42.isInScheduleMode() == false) goto L8;
        AlertsCreator.createScheduleDatePickerDialog(getParentActivity(), this.chatActivity.getDialogId(), new PhotoAlbumPickerActivity$$ExternalSyntheticLambda7(this));
    }

    private /* synthetic */ boolean lambda$createView$4(View r2, MotionEvent r3) {
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

    private /* synthetic */ void lambda$createView$5(KeyEvent r3) {
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

    private /* synthetic */ void lambda$createView$6(boolean r2, int r3, int r4) {
        sendSelectedPhotos(this.selectedPhotos, this.selectedPhotosOrder, r2, r3);
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$7(int r3, View r4) {
        ActionBarPopupWindow r42 = this.sendPopupWindow;
        if (r42 != null) goto L5;
    L7:
        if (r3 != 0) goto L10;
        AlertsCreator.createScheduleDatePickerDialog(getParentActivity(), this.chatActivity.getDialogId(), new PhotoAlbumPickerActivity$$ExternalSyntheticLambda8(this));
        return;
    L10:
        sendSelectedPhotos(this.selectedPhotos, this.selectedPhotosOrder, true, 0);
        finishFragment();
        return;
    L5:
        if (r42.isShowing() == false) goto L7;
        this.sendPopupWindow.dismiss();
        goto L7
    }

    private /* synthetic */ boolean lambda$createView$8(View r11) {
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
        this.sendPopupLayout.setOnTouchListener(new AnonymousClass5(this));
        this.sendPopupLayout.setDispatchKeyEventListener(new PhotoAlbumPickerActivity$$ExternalSyntheticLambda5(this));
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
        this.itemCells[r22].setOnClickListener(new PhotoAlbumPickerActivity$$ExternalSyntheticLambda6(this, r22));
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
        this.sendPopupLayout.setupRadialSelectors(Theme.getColor(Theme.key_dialogButtonSelector));
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

    private void openPhotoPicker(MediaController.AlbumEntry r11, int r12) {
        if (r11 == null) goto L5;
        PhotoPickerActivity r0 = new PhotoPickerActivity(r12, r11, this.selectedPhotos, this.selectedPhotosOrder, this.selectPhotoType, this.allowCaption, this.chatActivity, false);
        Editable r112 = this.commentTextView.getText();
        this.caption = r112;
        r0.setCaption(r112);
        r0.setDelegate(new AnonymousClass8(this));
        r0.setMaxSelectedPhotos(this.maxSelectedPhotos, this.allowOrder);
        presentFragment(r0);
        return;
    L5:
        HashMap r1 = new HashMap();
        ArrayList r5 = new ArrayList();
        if (this.allowGifs == false) goto L9;
        PhotoPickerSearchActivity r02 = new PhotoPickerSearchActivity(r1, r5, this.selectPhotoType, this.allowCaption, this.chatActivity);
        Editable r113 = this.commentTextView.getText();
        this.caption = r113;
        r02.setCaption(r113);
        r02.setDelegate(new AnonymousClass9(this, r1, r5));
        r02.setMaxSelectedPhotos(this.maxSelectedPhotos, this.allowOrder);
        presentFragment(r02);
        return;
    L9:
        PhotoPickerActivity r13 = new PhotoPickerActivity(0, r11, r1, r5, this.selectPhotoType, this.allowCaption, this.chatActivity, false);
        Editable r122 = this.commentTextView.getText();
        this.caption = r122;
        r13.setCaption(r122);
        r13.setDelegate(new AnonymousClass10(this, r1, r5));
        r13.setMaxSelectedPhotos(this.maxSelectedPhotos, this.allowOrder);
        presentFragment(r13);
    }

    private void sendSelectedPhotos(HashMap<Object, Object> r9, ArrayList<Object> r10, boolean r11, int r12) {
        if (r9.isEmpty() == false) goto L5;
        return;
    L5:
        if (this.delegate != null) goto L7;
        return;
    L7:
        if (this.sendPressed == true) goto L46;
        this.sendPressed = true;
        ArrayList<SendMessagesHelper.SendingMediaInfo> r1 = new ArrayList();
        int r2 = 0;
    L11:
        if (r2 >= r10.size()) goto L38;
        Object r3 = r9.get(r10.get(r2));
        SendMessagesHelper.SendingMediaInfo r4 = new SendMessagesHelper.SendingMediaInfo();
        r1.add(r4);
        String r6 = null;
        if ((r3 instanceof MediaController.PhotoEntry) == false) goto L23;
        MediaController.PhotoEntry r32 = (MediaController.PhotoEntry) r3;
        String r5 = r32.imagePath;
        if (r5 == null) goto L17;
        r4.path = r5;
    L18:
        r4.thumbPath = r32.thumbPath;
        r4.coverPath = r32.coverPath;
        r4.videoEditedInfo = r32.editedInfo;
        r4.isVideo = r32.isVideo;
        CharSequence r52 = r32.caption;
        if (r52 == null) goto L21;
        r6 = r52.toString();
    L21:
        r4.caption = r6;
        r4.entities = r32.entities;
        r4.masks = r32.stickers;
        r4.ttl = r32.ttl;
    L37:
        r2 = r2 + 1;
        goto L11
    L17:
        r4.path = r32.path;
        goto L18
    L23:
        if ((r3 instanceof MediaController.SearchImage) == false) goto L37;
        MediaController.SearchImage r33 = (MediaController.SearchImage) r3;
        String r53 = r33.imagePath;
        if (r53 == null) goto L27;
        r4.path = r53;
    L28:
        r4.thumbPath = r33.thumbPath;
        r4.coverPath = r33.coverPath;
        r4.videoEditedInfo = r33.editedInfo;
        CharSequence r54 = r33.caption;
        if (r54 == null) goto L31;
        r6 = r54.toString();
    L31:
        r4.caption = r6;
        r4.entities = r33.entities;
        r4.masks = r33.stickers;
        r4.ttl = r33.ttl;
        TLRPC.BotInlineResult r55 = r33.inlineResult;
        if (r55 != null) goto L34;
    L36:
        r33.date = (int) (System.currentTimeMillis() / 1000);
        goto L37
    L34:
        if (r33.type != 1) goto L36;
        r4.inlineResult = r55;
        r4.params = r33.params;
        goto L36
    L27:
        r4.searchImage = r33;
        goto L28
    L38:
        this.delegate.didSelectPhotos(r1, r11, r12);
        return;
    }

    private boolean showCommentTextView(boolean r7) {
        if (this.frameLayout2.getTag() == null) goto L5;
        boolean r0 = true;
    L6:
        if (r7 != r0) goto L8;
        return false;
    L8:
        FrameLayout r02 = this.frameLayout2;
        if (r7 == false) goto L11;
        Integer r3 = 1;
    L12:
        r02.setTag(r3);
        if (this.commentTextView.getEditText().isFocused() == false) goto L15;
        AndroidUtilities.hideKeyboard(this.commentTextView.getEditText());
    L15:
        this.commentTextView.hidePopup(true);
        FrameLayout r03 = this.frameLayout2;
        if (r7 == false) goto L18;
        r03.setVisibility(0);
        this.writeButtonContainer.setVisibility(0);
    L19:
        FrameLayout r04 = this.writeButtonContainer;
        float r1 = 0.2f;
        float r32 = 1.0f;
        if (r7 == false) goto L22;
        float r4 = 1.0f;
    L23:
        r04.setScaleX(r4);
        FrameLayout r05 = this.writeButtonContainer;
        if (r7 == false) goto L26;
        float r42 = 1.0f;
    L27:
        r05.setScaleY(r42);
        FrameLayout r06 = this.writeButtonContainer;
        float r43 = 0.0f;
        if (r7 == false) goto L30;
        float r5 = 1.0f;
    L31:
        r06.setAlpha(r5);
        View r07 = this.selectedCountView;
        if (r7 == false) goto L34;
        float r52 = 1.0f;
    L35:
        r07.setScaleX(r52);
        View r08 = this.selectedCountView;
        if (r7 == false) goto L38;
        r1 = 1.0f;
    L38:
        r08.setScaleY(r1);
        View r09 = this.selectedCountView;
        if (r7 == true) goto L42;
        r32 = 0.0f;
    L42:
        r09.setAlpha(r32);
        FrameLayout r010 = this.frameLayout2;
        if (r7 == false) goto L45;
        float r33 = 0.0f;
    L46:
        r010.setTranslationY(r33);
        View r6 = this.shadow;
        if (r7 == true) goto L50;
        r43 = AndroidUtilities.dp(48.0f);
    L50:
        r6.setTranslationY(r43);
        return true;
    L45:
        r33 = AndroidUtilities.dp(48.0f);
        goto L46
    L34:
        r52 = 0.2f;
        goto L35
    L30:
        r5 = 0.0f;
        goto L31
    L26:
        r42 = 0.2f;
        goto L27
    L22:
        r4 = 0.2f;
        goto L23
    L18:
        r03.setVisibility(4);
        this.writeButtonContainer.setVisibility(4);
        goto L19
    L11:
        r3 = null;
        goto L12
    L5:
        r0 = false;
        goto L6
    }

    private void updatePhotosButton() {
        int r0 = this.selectedPhotos.size();
        View r1 = this.selectedCountView;
        if (r0 != 0) goto L6;
        r1.setPivotX(0.0f);
        this.selectedCountView.setPivotY(0.0f);
        showCommentTextView(false);
        return;
    L6:
        r1.invalidate();
        showCommentTextView(true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r20) {
        ActionBar r1 = this.actionBar;
        int r3 = Theme.key_dialogBackground;
        r1.setBackgroundColor(Theme.getColor(r3));
        ActionBar r12 = this.actionBar;
        int r4 = Theme.key_dialogTextBlack;
        r12.setTitleColor(Theme.getColor(r4));
        this.actionBar.setItemsColor(Theme.getColor(r4), false);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_dialogButtonSelector), false);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenu r13 = this.actionBar.createMenu();
        if (this.allowSearchImages == false) goto L5;
        r13.addItem(2, R.drawable.outline_header_search).setContentDescription(LocaleController.getString(R.string.Search));
    L5:
        ActionBarMenuItem r14 = r13.addItem(0, R.drawable.ic_ab_other);
        r14.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        r14.addSubItem(1, R.drawable.msg_openin, LocaleController.getString(R.string.OpenInExternalApp));
        AnonymousClass2 r15 = new AnonymousClass2(this, r20);
        this.sizeNotifierFrameLayout = r15;
        r15.setBackgroundColor(Theme.getColor(r3));
        this.fragmentView = this.sizeNotifierFrameLayout;
        this.actionBar.setTitle(LocaleController.getString(R.string.Gallery));
        RecyclerListView r16 = new RecyclerListView(r20);
        this.listView = r16;
        r16.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(54.0f));
        this.listView.setClipToPadding(false);
        this.listView.setHorizontalScrollBarEnabled(false);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setLayoutManager(new LinearLayoutManager(r20, 1, false));
        this.listView.setDrawingCacheEnabled(false);
        this.sizeNotifierFrameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        RecyclerListView r17 = this.listView;
        ListAdapter r42 = new ListAdapter(this, r20);
        this.listAdapter = r42;
        r17.setAdapter(r42);
        this.listView.setGlowColor(Theme.getColor(r3));
        TextView r18 = new TextView(r20);
        this.emptyView = r18;
        r18.setTextColor(-8355712);
        this.emptyView.setTextSize(1, 20.0f);
        this.emptyView.setGravity(17);
        this.emptyView.setVisibility(8);
        this.emptyView.setText(LocaleController.getString(R.string.NoPhotos));
        this.sizeNotifierFrameLayout.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 48.0f));
        this.emptyView.setOnTouchListener(new PhotoAlbumPickerActivity$$ExternalSyntheticLambda0());
        FrameLayout r19 = new FrameLayout(r20);
        this.progressView = r19;
        r19.setVisibility(8);
        this.sizeNotifierFrameLayout.addView(this.progressView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 48.0f));
        RadialProgressView r110 = new RadialProgressView(r20);
        r110.setProgressColor(-11371101);
        this.progressView.addView(r110, LayoutHelper.createFrame(-2, -2, 17));
        View r111 = new View(r20);
        this.shadow = r111;
        r111.setBackgroundResource(R.drawable.header_shadow_reverse);
        this.shadow.setTranslationY(AndroidUtilities.dp(48.0f));
        this.sizeNotifierFrameLayout.addView(this.shadow, LayoutHelper.createFrame(-1, 3.0f, 83, 0.0f, 0.0f, 0.0f, 48.0f));
        FrameLayout r112 = new FrameLayout(r20);
        this.frameLayout2 = r112;
        r112.setBackgroundColor(Theme.getColor(r3));
        this.frameLayout2.setVisibility(4);
        this.frameLayout2.setTranslationY(AndroidUtilities.dp(48.0f));
        this.sizeNotifierFrameLayout.addView(this.frameLayout2, LayoutHelper.createFrame(-1, 48, 83));
        this.frameLayout2.setOnTouchListener(new PhotoAlbumPickerActivity$$ExternalSyntheticLambda1());
        EditTextEmoji r113 = this.commentTextView;
        if (r113 == null) goto L8;
        r113.onDestroy();
    L8:
        this.commentTextView = new EditTextEmoji(r20, this.sizeNotifierFrameLayout, null, 1, false);
        this.commentTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MessagesController.getInstance(UserConfig.selectedAccount).maxCaptionLength)});
        this.commentTextView.setHint(LocaleController.getString(R.string.AddCaption));
        EditTextCaption r114 = this.commentTextView.getEditText();
        r114.setMaxLines(1);
        r114.setSingleLine(true);
        this.frameLayout2.addView(this.commentTextView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 84.0f, 0.0f));
        CharSequence r115 = this.caption;
        if (r115 == null) goto L11;
        this.commentTextView.setText(r115);
    L11:
        AnonymousClass3 r116 = new AnonymousClass3(this, r20);
        this.writeButtonContainer = r116;
        r116.setFocusable(true);
        this.writeButtonContainer.setFocusableInTouchMode(true);
        this.writeButtonContainer.setVisibility(4);
        this.writeButtonContainer.setScaleX(0.2f);
        this.writeButtonContainer.setScaleY(0.2f);
        this.writeButtonContainer.setAlpha(0.0f);
        this.sizeNotifierFrameLayout.addView(this.writeButtonContainer, LayoutHelper.createFrame(60, 60.0f, 85, 0.0f, 0.0f, 12.0f, 10.0f));
        this.writeButton = new ImageView(r20);
        Drawable r117 = UIUtil.createFabBackground(56, getThemedColor(Theme.key_chat_editMediaButton), getThemedColor(Theme.key_dialogFloatingButtonPressed));
        this.writeButtonDrawable = r117;
        this.writeButton.setBackgroundDrawable(r117);
        this.writeButton.setImageResource(R.drawable.attach_send);
        this.writeButton.setImportantForAccessibility(2);
        this.writeButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogFloatingIcon), PorterDuff.Mode.MULTIPLY));
        this.writeButton.setScaleType(ImageView.ScaleType.CENTER);
        this.writeButton.setOutlineProvider(new AnonymousClass4(this));
        this.writeButtonContainer.addView(this.writeButton, LayoutHelper.createFrame(56, 56.0f, 51, 2.0f, 0.0f, 0.0f, 0.0f));
        this.writeButton.setOnClickListener(new PhotoAlbumPickerActivity$$ExternalSyntheticLambda2(this));
        this.writeButton.setOnTouchListener(new PhotoAlbumPickerActivity$$ExternalSyntheticLambda3(this));
        this.writeButton.setOnLongClickListener(new PhotoAlbumPickerActivity$$ExternalSyntheticLambda4(this));
        this.textPaint.setTextSize(AndroidUtilities.dp(12.0f));
        this.textPaint.setTypeface(AndroidUtilities.bold());
        AnonymousClass6 r118 = new AnonymousClass6(this, r20);
        this.selectedCountView = r118;
        r118.setAlpha(0.0f);
        this.selectedCountView.setScaleX(0.2f);
        this.selectedCountView.setScaleY(0.2f);
        this.sizeNotifierFrameLayout.addView(this.selectedCountView, LayoutHelper.createFrame(42, 24.0f, 85, 0.0f, 0.0f, -2.0f, 9.0f));
        if (this.selectPhotoType == SELECT_TYPE_ALL) goto L15;
        this.commentTextView.setVisibility(8);
    L15:
        if (this.loading == false) goto L21;
        ArrayList<MediaController.AlbumEntry> r119 = this.albumsSorted;
        if (r119 != null) goto L19;
    L20:
        this.progressView.setVisibility(0);
        this.listView.setEmptyView(null);
    L23:
        return this.fragmentView;
    L19:
        if (r119.isEmpty() == true) goto L20;
    L21:
        this.progressView.setVisibility(8);
        this.listView.setEmptyView(this.emptyView);
        goto L23
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.albumsDidLoad) goto L31;
        int r42 = ((Integer) r5[0]).intValue();
        if (this.classGuid != r42) goto L34;
        int r43 = this.selectPhotoType;
        if (r43 != SELECT_TYPE_AVATAR) goto L9;
    L16:
        this.albumsSorted = (ArrayList) r5[2];
    L17:
        FrameLayout r44 = this.progressView;
        if (r44 == null) goto L20;
        r44.setVisibility(8);
    L20:
        RecyclerListView r45 = this.listView;
        if (r45 != null) goto L23;
    L25:
        ListAdapter r46 = this.listAdapter;
        if (r46 == null) goto L28;
        r46.notifyDataSetChanged();
    L28:
        this.loading = false;
        return;
    L23:
        if (r45.getEmptyView() != null) goto L25;
        this.listView.setEmptyView(this.emptyView);
        goto L25
    L9:
        if (r43 == SELECT_TYPE_WALLPAPER) goto L16;
        if (r43 == SELECT_TYPE_QR) goto L16;
        if (this.allowSearchImages == false) goto L16;
        this.albumsSorted = (ArrayList) r5[1];
        goto L17
    L34:
        return;
    L31:
        if (r3 != NotificationCenter.closeChats) goto L35;
        removeSelfFromStack(true);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        View r3 = this.fragmentView;
        int r4 = ThemeDescription.FLAG_BACKGROUND;
        int r12 = Theme.key_dialogBackground;
        r1.add(new ThemeDescription(r3, r4, null, null, null, null, r12));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r12));
        ActionBar r14 = this.actionBar;
        int r15 = ThemeDescription.FLAG_AB_ITEMSCOLOR;
        int r20 = Theme.key_dialogTextBlack;
        r1.add(new ThemeDescription(r14, r15, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_dialogButtonSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r12));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, null, new Drawable[]{Theme.chat_attachEmptyDrawable}, null, Theme.key_chat_attachEmptyImage));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, null, null, null, Theme.key_chat_attachPhotoBackground));
        return r1;
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
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        fixLayout();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        int r0 = this.selectPhotoType;
        if (r0 != SELECT_TYPE_AVATAR) goto L5;
    L12:
        this.albumsSorted = MediaController.allPhotoAlbums;
    L14:
        if (this.albumsSorted != null) goto L16;
        boolean r02 = true;
    L17:
        this.loading = r02;
        MediaController.loadGalleryPhotosAlbums(this.classGuid);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.albumsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.closeChats);
        return super.onFragmentCreate();
    L16:
        r02 = false;
        goto L17
    L5:
        if (r0 == SELECT_TYPE_WALLPAPER) goto L12;
        if (r0 == SELECT_TYPE_QR) goto L12;
        if (this.allowSearchImages == false) goto L12;
        this.albumsSorted = MediaController.allMediaAlbums;
        goto L14
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        EditTextEmoji r0 = this.commentTextView;
        if (r0 == null) goto L5;
        r0.onDestroy();
    L5:
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.albumsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.closeChats);
        super.onFragmentDestroy();
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
        fixLayout();
    }

    public void setAllowSearchImages(boolean r1) {
        this.allowSearchImages = r1;
    }

    public void setDelegate(PhotoAlbumPickerActivityDelegate r1) {
        this.delegate = r1;
    }

    public void setMaxSelectedPhotos(int r1, boolean r2) {
        this.maxSelectedPhotos = r1;
        this.allowOrder = r2;
    }
}
