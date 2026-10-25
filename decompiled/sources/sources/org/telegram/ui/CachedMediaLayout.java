package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.CacheControlActivity;
import org.telegram.ui.Cells.SharedAudioCell;
import org.telegram.ui.Cells.SharedDocumentCell;
import org.telegram.ui.Cells.SharedPhotoVideoCell2;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.NestedSizeNotifierLayout;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Storage.CacheModel;
import org.telegram.ui.Stories.StoriesListPlaceProvider;

/* loaded from: classes6.dex */
public abstract class CachedMediaLayout extends FrameLayout implements NestedSizeNotifierLayout.ChildLayout {
    private final LinearLayout actionModeLayout;
    private final ArrayList<View> actionModeViews;
    Page[] allPages;
    private final BackDrawable backDrawable;
    private int bottomPadding;
    CacheModel cacheModel;
    private final ActionBarMenuItem clearItem;
    private final ImageView closeButton;
    Delegate delegate;
    private final View divider;
    ArrayList<Page> pages;
    BaseFragment parentFragment;
    BasePlaceProvider placeProvider;
    public final AnimatedTextView selectedMessagesCountTextView;
    private final ViewPagerFixed.TabsView tabs;
    ViewPagerFixed viewPagerFixed;

    /* renamed from: org.telegram.ui.CachedMediaLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        final /* synthetic */ CachedMediaLayout this$0;

        public AnonymousClass1(CachedMediaLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            r8.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
        }
    }

    /* renamed from: org.telegram.ui.CachedMediaLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewPagerFixed.Adapter {
        private ActionBarPopupWindow popupWindow;
        final /* synthetic */ CachedMediaLayout this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ BaseFragment val$parentFragment;

        /* renamed from: org.telegram.ui.CachedMediaLayout$2$1, reason: invalid class name */
        public class AnonymousClass1 implements RecyclerListView.OnItemClickListener {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ RecyclerListView val$recyclerListView;

            public AnonymousClass1(AnonymousClass2 r1, RecyclerListView r2) {
                this.this$1 = r1;
                this.val$recyclerListView = r2;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
            public void onItemClick(View r4, int r5) {
                BaseAdapter r0 = (BaseAdapter) this.val$recyclerListView.getAdapter();
                ItemInner r52 = r0.itemInners.get(r5);
                if ((r4 instanceof SharedPhotoVideoCell2) == false) goto L10;
                MediaAdapter r02 = (MediaAdapter) r0;
                if (r02.isStories == false) goto L8;
                TL_stories.TL_storyItem r42 = new TL_stories.TL_storyItem();
                CacheModel.FileInfo r03 = r52.file;
                r42.dialogId = r03.dialogId;
                r42.id = Objects.hash(new Object[]{r03.file.getAbsolutePath()});
                r42.attachPath = r52.file.file.getAbsolutePath();
                r42.date = -1;
                this.this$1.val$parentFragment.getOrCreateStoryViewer().open(this.this$1.val$context, r42, StoriesListPlaceProvider.of(this.val$recyclerListView));
                return;
            L8:
                CachedMediaLayout.m6478$$Nest$mopenPhoto(this.this$1.this$0, r52, r02, this.val$recyclerListView, (SharedPhotoVideoCell2) r4);
                return;
            L10:
                Delegate r3 = this.this$1.this$0.delegate;
                if (r3 == null) goto L14;
                r3.onItemSelected(r52.entities, r52.file, false);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$5PEEru8E27ixJhH_YLb5yiiPgIk(AnonymousClass2 r0, ItemInner r1, View r2) {
            r0.lambda$createView$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$I1jbzkL0sqp8LVm8qPwBftPitq0(AnonymousClass2 r0, ItemInner r1, BaseFragment r2, View r3) {
            r0.lambda$createView$3(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$RRoRSmik3KrbXg6fO_XzTHjjbDM(AnonymousClass2 r0, ItemInner r1, View r2, View r3) {
            r0.lambda$createView$1(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$aNeVpCILaSf9LDng2kUx-QY4l1Y, reason: not valid java name */
        public static /* synthetic */ void m6479$r8$lambda$aNeVpCILaSf9LDng2kUxQY4l1Y(AnonymousClass2 r0, ItemInner r1, BaseAdapter r2, RecyclerListView r3, View r4, View r5) {
            r0.lambda$createView$0(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ boolean $r8$lambda$gfOOSj4mYZOZCvVD81PZrlLJxF8(AnonymousClass2 r0, RecyclerListView r1, BaseFragment r2, View r3, int r4, float r5, float r6) {
            return r0.lambda$createView$5(r1, r2, r3, r4, r5, r6);
        }

        public static /* synthetic */ void $r8$lambda$os4pggvhhAQoOuth3MhVuJgDAbA(AnonymousClass2 r0, ItemInner r1, View r2, View r3) {
            r0.lambda$createView$2(r1, r2, r3);
        }

        public AnonymousClass2(CachedMediaLayout r1, Context r2, BaseFragment r3) {
            this.this$0 = r1;
            this.val$context = r2;
            this.val$parentFragment = r3;
        }

        private /* synthetic */ void lambda$createView$0(ItemInner r1, BaseAdapter r2, RecyclerListView r3, View r4, View r5) {
            CachedMediaLayout.m6478$$Nest$mopenPhoto(this.this$0, r1, (MediaAdapter) r2, r3, (SharedPhotoVideoCell2) r4);
            ActionBarPopupWindow r0 = this.popupWindow;
            if (r0 == null) goto L6;
            r0.dismiss();
            return;
        }

        private /* synthetic */ void lambda$createView$1(ItemInner r1, View r2, View r3) {
            CachedMediaLayout.m6477$$Nest$mopenItem(this.this$0, r1.file, (CacheCell) r2);
            ActionBarPopupWindow r0 = this.popupWindow;
            if (r0 == null) goto L6;
            r0.dismiss();
            return;
        }

        private /* synthetic */ void lambda$createView$2(ItemInner r1, View r2, View r3) {
            CachedMediaLayout.m6477$$Nest$mopenItem(this.this$0, r1.file, (CacheCell) r2);
            ActionBarPopupWindow r0 = this.popupWindow;
            if (r0 == null) goto L6;
            r0.dismiss();
            return;
        }

        private /* synthetic */ void lambda$createView$3(ItemInner r5, BaseFragment r6, View r7) {
            Bundle r72 = new Bundle();
            long r0 = r5.file.dialogId;
            if (r0 <= 0) goto L5;
            r72.putLong("user_id", r0);
        L6:
            r72.putInt("message_id", r5.file.messageId);
            r6.presentFragment(new ChatActivity(r72));
            this.this$0.delegate.dismiss();
            ActionBarPopupWindow r4 = this.popupWindow;
            if (r4 == null) goto L10;
            r4.dismiss();
            return;
        L10:
            return;
        L5:
            r72.putLong("chat_id", -r0);
            goto L6
        }

        private /* synthetic */ void lambda$createView$4(ItemInner r3, View r4) {
            Delegate r42 = this.this$0.delegate;
            if (r42 == null) goto L5;
            r42.onItemSelected(r3.entities, r3.file, true);
        L5:
            ActionBarPopupWindow r2 = this.popupWindow;
            if (r2 == null) goto L9;
            r2.dismiss();
            return;
        }

        private /* synthetic */ boolean lambda$createView$5(RecyclerListView r17, BaseFragment r18, View r19, int r20, float r21, float r22) {
            BaseAdapter r3 = (BaseAdapter) r17.getAdapter();
            ItemInner r2 = r3.itemInners.get(r20);
            if ((r19 instanceof CacheCell) == false) goto L5;
        L11:
            ActionBarPopupWindow.ActionBarPopupWindowLayout r8 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(this.this$0.getContext());
            if ((r19 instanceof SharedPhotoVideoCell2) == false) goto L15;
            ActionBarMenuItem.addItem(r8, R.drawable.msg_view_file, LocaleController.getString(R.string.CacheOpenFile), false, null).setOnClickListener(new CachedMediaLayout$2$$ExternalSyntheticLambda1(this, r2, r3, r17, r19));
        L18:
            CacheModel.FileInfo r0 = r2.file;
            if (r0.dialogId != 0) goto L21;
        L23:
            int r02 = R.drawable.msg_select;
            if (this.this$0.cacheModel.selectedFiles.contains(r2.file) == true) goto L27;
            int r32 = R.string.Select;
        L28:
            ActionBarMenuItem.addItem(r8, r02, LocaleController.getString(r32), false, null).setOnClickListener(new CachedMediaLayout$2$$ExternalSyntheticLambda5(this, r2));
            this.popupWindow = AlertsCreator.createSimplePopup(r18, r8, r19, (int) r21, (int) r22);
            this.this$0.getRootView().dispatchTouchEvent(MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0));
            return true;
        L27:
            r32 = R.string.Deselect;
            goto L28
        L21:
            if (r0.messageId == 0) goto L23;
            ActionBarMenuItem.addItem(r8, R.drawable.msg_viewintopic, LocaleController.getString(R.string.ViewInChat), false, null).setOnClickListener(new CachedMediaLayout$2$$ExternalSyntheticLambda4(this, r2, r18));
            goto L23
        L15:
            if ((((CacheCell) r19).container.getChildAt(0) instanceof SharedAudioCell) == false) goto L17;
            ActionBarMenuItem.addItem(r8, R.drawable.msg_played, LocaleController.getString(R.string.PlayFile), false, null).setOnClickListener(new CachedMediaLayout$2$$ExternalSyntheticLambda2(this, r2, r19));
            goto L18
        L17:
            ActionBarMenuItem.addItem(r8, R.drawable.msg_view_file, LocaleController.getString(R.string.CacheOpenFile), false, null).setOnClickListener(new CachedMediaLayout$2$$ExternalSyntheticLambda3(this, r2, r19));
            goto L18
        L5:
            if ((r19 instanceof SharedPhotoVideoCell2) == true) goto L11;
            Delegate r03 = this.this$0.delegate;
            if (r03 == null) goto L10;
            r03.onItemSelected(r2.entities, r2.file, true);
        L10:
            return true;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r3, int r4, int r5) {
            RecyclerListView r52 = (RecyclerListView) r3;
            r52.setAdapter(this.this$0.pages.get(r4).adapter);
            if (this.this$0.pages.get(r4).type != 1) goto L5;
        L8:
            r52.setLayoutManager(new GridLayoutManager(r3.getContext(), 3));
        L9:
            r52.setTag(Integer.valueOf(this.this$0.pages.get(r4).type));
            return;
        L5:
            if (this.this$0.pages.get(r4).type == 4) goto L8;
            r52.setLayoutManager(new LinearLayoutManager(r3.getContext()));
            goto L9
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r3) {
            RecyclerListView r32 = new RecyclerListView(this.val$context);
            DefaultItemAnimator r0 = (DefaultItemAnimator) r32.getItemAnimator();
            r0.setDelayAnimations(false);
            r0.setSupportsChangeAnimations(false);
            r32.setClipToPadding(false);
            r32.setPadding(0, 0, 0, CachedMediaLayout.m6474$$Nest$fgetbottomPadding(this.this$0));
            r32.setOnItemClickListener(new AnonymousClass1(this, r32));
            r32.setOnItemLongClickListener(new CachedMediaLayout$2$$ExternalSyntheticLambda0(this, r32, this.val$parentFragment));
            return r32;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return this.this$0.pages.size();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemId(int r1) {
            return this.this$0.pages.get(r1).type;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public /* bridge */ /* synthetic */ CharSequence getItemTitle(int r1) {
            return getItemTitle(r1);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public String getItemTitle(int r1) {
            return this.this$0.pages.get(r1).title;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public boolean hasStableId() {
            return true;
        }
    }

    public abstract class BaseAdapter extends AdapterWithDiffUtils {
        ArrayList<ItemInner> itemInners;
        final /* synthetic */ CachedMediaLayout this$0;
        final int type;

        public BaseAdapter(CachedMediaLayout r1, int r2) {
            this.this$0 = r1;
            this.itemInners = new ArrayList();
            this.type = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.itemInners.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return this.itemInners.get(r1).viewType;
        }

        public abstract void update();
    }

    public abstract class BaseFilesAdapter extends BaseAdapter {
        ArrayList<ItemInner> oldItems;
        final /* synthetic */ CachedMediaLayout this$0;

        public BaseFilesAdapter(CachedMediaLayout r1, int r2) {
            this.this$0 = r1;
            super(r1, r2);
            this.oldItems = new ArrayList();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // org.telegram.ui.CachedMediaLayout.BaseAdapter
        public void update() {
            this.oldItems.clear();
            this.oldItems.addAll(this.itemInners);
            this.itemInners.clear();
            CacheModel r0 = this.this$0.cacheModel;
            if (r0 == null) goto L24;
            int r1 = this.type;
            if (r1 != 1) goto L7;
            ArrayList<CacheModel.FileInfo> r02 = r0.media;
        L19:
            if (r02 == null) goto L24;
            int r12 = 0;
        L22:
            if (r12 >= r02.size()) goto L24;
            this.itemInners.add(new ItemInner(this.this$0, 2, r02.get(r12)));
            r12 = r12 + 1;
            goto L22
        L7:
            if (r1 != 2) goto L10;
            r02 = r0.documents;
            goto L19
        L10:
            if (r1 != 3) goto L13;
            r02 = r0.music;
            goto L19
        L13:
            if (r1 != 5) goto L16;
            r02 = r0.voice;
            goto L19
        L16:
            if (r1 != 4) goto L18;
            r02 = r0.stories;
            goto L19
        L18:
            r02 = null;
        L24:
            setItems(this.oldItems, this.itemInners);
        }
    }

    public class BasePlaceProvider extends PhotoViewer.EmptyPhotoViewerProvider {
        RecyclerListView recyclerListView;
        final /* synthetic */ CachedMediaLayout this$0;

        private BasePlaceProvider(CachedMediaLayout r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ BasePlaceProvider(CachedMediaLayout r1, CachedMediaLayoutIA r2) {
            this(r1);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r1, TLRPC.FileLocation r2, int r3, boolean r4, boolean r5) {
            SharedPhotoVideoCell2 r12 = CachedMediaLayout.m6476$$Nest$mgetCellForIndex(this.this$0, r3);
            if (r12 == null) goto L6;
            int[] r22 = new int[2];
            r12.getLocationInWindow(r22);
            PhotoViewer.PlaceProviderObject r32 = new PhotoViewer.PlaceProviderObject();
            r32.viewX = r22[0];
            r32.viewY = r22[1];
            r32.parentView = this.recyclerListView;
            ImageReceiver r0 = r12.imageReceiver;
            r32.imageReceiver = r0;
            r32.thumb = r0.getBitmapSafe();
            r32.scale = r12.getScaleX();
            return r32;
        L6:
            return null;
        }

        public void setRecyclerListView(RecyclerListView r1) {
            this.recyclerListView = r1;
        }
    }

    public class CacheCell extends FrameLayout {
        CheckBox2 checkBox;
        FrameLayout container;
        boolean drawDivider;
        TextView sizeTextView;
        final /* synthetic */ CachedMediaLayout this$0;
        int type;

        public static /* synthetic */ void $r8$lambda$jmHucI0JVrPURyA3p1feDmUrwDo(CacheCell r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public CacheCell(CachedMediaLayout r9, Context r10) {
            this.this$0 = r9;
            super(r10);
            CheckBox2 r92 = new CheckBox2(r10, 21);
            this.checkBox = r92;
            r92.setDrawBackgroundAsArc(14);
            this.checkBox.setColor(Theme.key_checkbox, Theme.key_radioBackground, Theme.key_checkboxCheck);
            View r93 = new View(getContext());
            r93.setOnClickListener(new CachedMediaLayout$CacheCell$$ExternalSyntheticLambda0(this));
            this.container = new FrameLayout(r10);
            TextView r0 = new TextView(r10);
            this.sizeTextView = r0;
            r0.setTextSize(1, 16.0f);
            this.sizeTextView.setGravity(5);
            this.sizeTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText));
            boolean r102 = LocaleController.isRTL;
            CheckBox2 r02 = this.checkBox;
            if (r102 == false) goto L6;
            addView(r02, LayoutHelper.createFrame(24, 24.0f, 21, 0.0f, 0.0f, 18.0f, 0.0f));
            addView(r93, LayoutHelper.createFrame(40, 40.0f, 21, 0.0f, 0.0f, 0.0f, 0.0f));
            addView(this.container, LayoutHelper.createFrame(-1, -2.0f, 0, 90.0f, 0.0f, 40.0f, 0.0f));
            addView(this.sizeTextView, LayoutHelper.createFrame(69, -2.0f, 19, 0.0f, 0.0f, 0.0f, 0.0f));
            return;
        L6:
            addView(r02, LayoutHelper.createFrame(24, 24.0f, 19, 18.0f, 0.0f, 0.0f, 0.0f));
            addView(r93, LayoutHelper.createFrame(40, 40.0f, 19, 0.0f, 0.0f, 0.0f, 0.0f));
            addView(this.container, LayoutHelper.createFrame(-1, -2.0f, 0, 48.0f, 0.0f, 90.0f, 0.0f));
            addView(this.sizeTextView, LayoutHelper.createFrame(69, -2.0f, 21, 0.0f, 0.0f, 21.0f, 0.0f));
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            onCheckBoxPressed();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r14) {
            super.dispatchDraw(r14);
            if (this.drawDivider == true) goto L5;
            return;
        L5:
            if (LocaleController.isRTL == false) goto L8;
            r14.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth() - AndroidUtilities.dp(48.0f), getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L8:
            r14.drawLine(getMeasuredWidth() - AndroidUtilities.dp(90.0f), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
        }

        public abstract void onCheckBoxPressed();
    }

    public interface Delegate {
        void clear();

        void clearSelection();

        default void dismiss() {
        }

        void onItemSelected(CacheControlActivity.DialogFileEntities r1, CacheModel.FileInfo r2, boolean r3);
    }

    public class DialogsAdapter extends BaseAdapter {
        ArrayList<ItemInner> old;
        final /* synthetic */ CachedMediaLayout this$0;

        private DialogsAdapter(CachedMediaLayout r2) {
            this.this$0 = r2;
            super(r2, 0);
            this.old = new ArrayList();
        }

        public /* synthetic */ DialogsAdapter(CachedMediaLayout r1, CachedMediaLayoutIA r2) {
            this(r1);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
            boolean r1 = true;
            if (r10.getItemViewType() == 1) goto L5;
            return;
        L5:
            CacheControlActivity.UserCell r102 = (CacheControlActivity.UserCell) r10.itemView;
            CacheControlActivity.DialogFileEntities r0 = this.itemInners.get(r11).entities;
            TLObject r2 = this.this$0.parentFragment.getMessagesController().getUserOrChat(r0.dialogId);
            CacheControlActivity.DialogFileEntities r3 = r102.dialogFileEntities;
            if (r3 != null) goto L8;
        L10:
            boolean r32 = false;
        L12:
            if (r0.dialogId != LongCompanionObject.MAX_VALUE) goto L14;
            String r5 = LocaleController.getString(R.string.CacheOtherChats);
            r102.getImageView().getAvatarDrawable().setAvatarType(14);
            r102.getImageView().setForUserOrChat(null, r102.getImageView().getAvatarDrawable());
        L15:
            r102.dialogFileEntities = r0;
            BackupImageView r6 = r102.getImageView();
            if ((r2 instanceof TLRPC.Chat) == true) goto L18;
        L20:
            boolean r22 = false;
        L21:
            r6.setRoundRadius(ExteraConfig.getAvatarCorners(38.0f, false, r22));
            String r23 = AndroidUtilities.formatFileSize(r0.totalSize);
            if (r11 < (getItemCount() - 1)) goto L25;
            r1 = false;
        L25:
            r102.setTextAndValue(r5, r23, r1);
            r102.setChecked(this.this$0.cacheModel.isSelected(r0.dialogId), r32);
            return;
        L18:
            if (((TLRPC.Chat) r2).forum == false) goto L20;
            r22 = true;
            goto L21
        L14:
            r5 = DialogObject.setDialogPhotoTitle(r102.getImageView(), r2);
            goto L15
        L8:
            if (r3.dialogId != r0.dialogId) goto L10;
            r32 = true;
            goto L12
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            CacheControlActivity.UserCell r0 = null;
            if (r3 != 1) goto L7;
            CacheControlActivity.UserCell r22 = new CacheControlActivity.UserCell(this.this$0.getContext(), null);
            r22.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r0 = r22;
        L7:
            return new RecyclerListView.Holder(r0);
        }

        @Override // org.telegram.ui.CachedMediaLayout.BaseAdapter
        public void update() {
            this.old.clear();
            this.old.addAll(this.itemInners);
            this.itemInners.clear();
            if (this.this$0.cacheModel == null) goto L8;
            int r0 = 0;
        L6:
            if (r0 >= this.this$0.cacheModel.entities.size()) goto L8;
            ArrayList<ItemInner> r1 = this.itemInners;
            CachedMediaLayout r3 = this.this$0;
            r1.add(new ItemInner(r3, 1, r3.cacheModel.entities.get(r0)));
            r0 = r0 + 1;
        L8:
            setItems(this.old, this.itemInners);
        }
    }

    public class DocumentsAdapter extends BaseFilesAdapter {
        ArrayList<Object> photoEntries;
        final /* synthetic */ CachedMediaLayout this$0;

        /* renamed from: org.telegram.ui.CachedMediaLayout$DocumentsAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends CacheCell {
            final /* synthetic */ DocumentsAdapter this$1;

            public AnonymousClass1(DocumentsAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r1.this$0, r2);
            }

            @Override // org.telegram.ui.CachedMediaLayout.CacheCell
            public void onCheckBoxPressed() {
                CacheModel.FileInfo r0 = (CacheModel.FileInfo) getTag();
                this.this$1.this$0.delegate.onItemSelected(null, r0, true);
            }
        }

        private DocumentsAdapter(CachedMediaLayout r2) {
            this.this$0 = r2;
            super(r2, 2);
            this.photoEntries = new ArrayList();
        }

        public /* synthetic */ DocumentsAdapter(CachedMediaLayout r1, CachedMediaLayoutIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r13, int r14) {
            CacheCell r0 = (CacheCell) r13.itemView;
            SharedDocumentCell r3 = (SharedDocumentCell) r0.container.getChildAt(0);
            CacheModel.FileInfo r1 = this.itemInners.get(r14).file;
            if (r1 != r13.itemView.getTag()) goto L5;
            boolean r10 = true;
        L7:
            if (r14 == (this.itemInners.size() - 1)) goto L9;
            boolean r9 = true;
        L10:
            r13.itemView.setTag(r1);
            long r132 = r1.file.lastModified();
            if (r1.messageType != 5) goto L14;
            String r2 = LocaleController.getString(R.string.AttachRound);
        L15:
            r3.setTextAndValueAndTypeAndThumb(r2, LocaleController.formatDateAudio(r132 / 1000, true), Utilities.getExtension(r1.file.getName()), null, 0, r9);
            if (r10 == true) goto L18;
            r3.setPhoto(r1.file.getPath());
        L18:
            BackupImageView r133 = r3.getImageView();
            if (r1.messageType != 5) goto L22;
            float r142 = 20.0f;
        L23:
            r133.setRoundRadius(AndroidUtilities.dp(r142));
            r0.drawDivider = r9;
            r0.sizeTextView.setText(AndroidUtilities.formatFileSize(r1.size));
            r0.checkBox.setChecked(this.this$0.cacheModel.isSelected(r1), r10);
            return;
        L22:
            r142 = 4.0f;
            goto L23
        L14:
            r2 = r1.file.getName();
            goto L15
        L9:
            r9 = false;
            goto L10
        L5:
            r10 = false;
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            AnonymousClass1 r42 = new AnonymousClass1(this, r3.getContext());
            r42.type = 2;
            SharedDocumentCell r2 = new SharedDocumentCell(r3.getContext(), 3, null);
            r42.container.addView(r2);
            return new RecyclerListView.Holder(r42);
        }

        @Override // org.telegram.ui.CachedMediaLayout.BaseFilesAdapter, org.telegram.ui.CachedMediaLayout.BaseAdapter
        public void update() {
            super.update();
            this.photoEntries.clear();
            int r1 = 0;
        L4:
            if (r1 >= this.itemInners.size()) goto L10;
            ArrayList<Object> r2 = this.photoEntries;
            String r8 = this.itemInners.get(r1).file.file.getPath();
            if (this.itemInners.get(r1).file.type != 1) goto L8;
            boolean r10 = true;
        L9:
            r2.add(new MediaController.PhotoEntry(0, 0, 0, r8, 0, r10, 0, 0, 0));
            r1 = r1 + 1;
            goto L4
        L8:
            r10 = false;
            goto L9
        }
    }

    public class ItemInner extends AdapterWithDiffUtils.Item {
        CacheControlActivity.DialogFileEntities entities;
        CacheModel.FileInfo file;
        final /* synthetic */ CachedMediaLayout this$0;

        public ItemInner(CachedMediaLayout r1, int r2, CacheControlActivity.DialogFileEntities r3) {
            this.this$0 = r1;
            super(r2, true);
            this.entities = r3;
        }

        public ItemInner(CachedMediaLayout r1, int r2, CacheModel.FileInfo r3) {
            this.this$0 = r1;
            super(r2, true);
            this.file = r3;
        }

        public boolean equals(Object r6) {
            if (this != r6) goto L6;
            return true;
        L6:
            if (r6 != null) goto L8;
        L29:
            return false;
        L8:
            if (getClass() != r6.getClass()) goto L29;
            ItemInner r62 = (ItemInner) r6;
            int r2 = this.viewType;
            if (r2 != r62.viewType) goto L29;
            if (r2 != 1) goto L22;
            CacheControlActivity.DialogFileEntities r3 = this.entities;
            if (r3 == null) goto L22;
            CacheControlActivity.DialogFileEntities r4 = r62.entities;
            if (r4 == null) goto L22;
            if (r3.dialogId != r4.dialogId) goto L20;
            return true;
        L20:
            return false;
        L22:
            if (r2 != 2) goto L29;
            CacheModel.FileInfo r5 = this.file;
            if (r5 == null) goto L29;
            CacheModel.FileInfo r63 = r62.file;
            if (r63 == null) goto L29;
            return Objects.equals(r5.file, r63.file);
        }
    }

    public class MediaAdapter extends BaseFilesAdapter {
        boolean isStories;
        ArrayList<Object> photoEntries;
        private SharedPhotoVideoCell2.SharedResources sharedResources;
        final /* synthetic */ CachedMediaLayout this$0;
        CombinedDrawable thumb;

        /* renamed from: org.telegram.ui.CachedMediaLayout$MediaAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends SharedPhotoVideoCell2 {
            final /* synthetic */ MediaAdapter this$1;

            public AnonymousClass1(MediaAdapter r1, Context r2, SharedPhotoVideoCell2.SharedResources r3, int r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Cells.SharedPhotoVideoCell2
            public void onCheckBoxPressed() {
                CacheModel.FileInfo r0 = (CacheModel.FileInfo) getTag();
                this.this$1.this$0.delegate.onItemSelected(null, r0, true);
            }
        }

        private MediaAdapter(CachedMediaLayout r2, boolean r3) {
            this.this$0 = r2;
            if (r3 == false) goto L5;
            int r0 = 4;
        L6:
            super(r2, r0);
            this.photoEntries = new ArrayList();
            this.isStories = r3;
            return;
        L5:
            r0 = 1;
            goto L6
        }

        public /* synthetic */ MediaAdapter(CachedMediaLayout r1, boolean r2, CachedMediaLayoutIA r3) {
            this(r1, r2);
        }

        public ArrayList<Object> getPhotos() {
            return this.photoEntries;
        }

        @Override // org.telegram.ui.CachedMediaLayout.BaseFilesAdapter, org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r14, int r15) {
            if (this.thumb != null) goto L5;
            CombinedDrawable r0 = new CombinedDrawable(new ColorDrawable(Theme.getColor(Theme.key_chat_attachPhotoBackground)), Theme.chat_attachEmptyDrawable);
            this.thumb = r0;
            r0.setFullsize(true);
        L5:
            SharedPhotoVideoCell2 r142 = (SharedPhotoVideoCell2) r14.itemView;
            CacheModel.FileInfo r152 = this.itemInners.get(r15).file;
            if (r152 != r142.getTag()) goto L8;
            boolean r02 = true;
        L9:
            r142.setTag(r152);
            int r3 = (int) Math.max(100.0f, AndroidUtilities.getRealScreenSize().x / AndroidUtilities.density);
            if (this.isStories == false) goto L16;
            boolean r2 = r152.file.getAbsolutePath().endsWith(".mp4");
            ImageReceiver r6 = r142.imageReceiver;
            if (r2 == false) goto L14;
            r6.setImage(ImageLocation.getForPath(r152.file.getAbsolutePath()), r3 + "_" + r3 + "_pframe", this.thumb, null, null, 0);
        L15:
            r142.storyId = Objects.hash(new Object[]{r152.file.getAbsolutePath()});
            r142.isStory = true;
            r142.setVideoText(AndroidUtilities.formatFileSize(r152.size), true);
        L20:
            r142.setChecked(this.this$0.cacheModel.isSelected(r152), r02);
            return;
        L14:
            r6.setImage(ImageLocation.getForPath(r152.file.getAbsolutePath()), r3 + "_" + r3, this.thumb, null, null, 0);
            goto L15
        L16:
            int r4 = r152.type;
            ImageReceiver r62 = r142.imageReceiver;
            if (r4 != 1) goto L19;
            r62.setImage(ImageLocation.getForPath("vthumb://0:" + r152.file.getAbsolutePath()), r3 + "_" + r3, this.thumb, null, null, 0);
            r142.setVideoText(AndroidUtilities.formatFileSize(r152.size), true);
            goto L20
        L19:
            r62.setImage(ImageLocation.getForPath("thumb://0:" + r152.file.getAbsolutePath()), r3 + "_" + r3, this.thumb, null, null, 0);
            r142.setVideoText(AndroidUtilities.formatFileSize(r152.size), false);
            goto L20
        L8:
            r02 = false;
            goto L9
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (this.sharedResources != null) goto L5;
            this.sharedResources = new SharedPhotoVideoCell2.SharedResources(r3.getContext(), null);
        L5:
            AnonymousClass1 r42 = new AnonymousClass1(this, r3.getContext(), this.sharedResources, this.this$0.parentFragment.getCurrentAccount());
            r42.setStyle(1);
            return new RecyclerListView.Holder(r42);
        }

        @Override // org.telegram.ui.CachedMediaLayout.BaseFilesAdapter, org.telegram.ui.CachedMediaLayout.BaseAdapter
        public void update() {
            super.update();
            this.photoEntries.clear();
            int r1 = 0;
        L4:
            if (r1 >= this.itemInners.size()) goto L10;
            ArrayList<Object> r2 = this.photoEntries;
            String r8 = this.itemInners.get(r1).file.file.getPath();
            if (this.itemInners.get(r1).file.type != 1) goto L8;
            boolean r10 = true;
        L9:
            r2.add(new MediaController.PhotoEntry(0, 0, 0, r8, 0, r10, 0, 0, 0));
            r1 = r1 + 1;
            goto L4
        L8:
            r10 = false;
            goto L9
        }
    }

    public class MusicAdapter extends BaseFilesAdapter {
        final /* synthetic */ CachedMediaLayout this$0;

        /* renamed from: org.telegram.ui.CachedMediaLayout$MusicAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends CacheCell {
            final /* synthetic */ MusicAdapter this$1;

            public AnonymousClass1(MusicAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r1.this$0, r2);
            }

            @Override // org.telegram.ui.CachedMediaLayout.CacheCell
            public void onCheckBoxPressed() {
                CacheModel.FileInfo r0 = (CacheModel.FileInfo) getTag();
                this.this$1.this$0.delegate.onItemSelected(null, r0, true);
            }
        }

        /* renamed from: org.telegram.ui.CachedMediaLayout$MusicAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends SharedAudioCell {
            final /* synthetic */ MusicAdapter this$1;
            final /* synthetic */ CacheCell val$cacheCell;

            public AnonymousClass2(MusicAdapter r1, Context r2, int r3, Theme.ResourcesProvider r4, CacheCell r5) {
                this.this$1 = r1;
                this.val$cacheCell = r5;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Cells.SharedAudioCell
            public void didPressedButton() {
                CachedMediaLayout.m6477$$Nest$mopenItem(this.this$1.this$0, (CacheModel.FileInfo) this.val$cacheCell.getTag(), this.val$cacheCell);
            }
        }

        private MusicAdapter(CachedMediaLayout r2) {
            this.this$0 = r2;
            super(r2, 3);
        }

        public /* synthetic */ MusicAdapter(CachedMediaLayout r1, CachedMediaLayoutIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            CacheCell r72 = (CacheCell) r7.itemView;
            boolean r1 = false;
            SharedAudioCell r0 = (SharedAudioCell) r72.container.getChildAt(0);
            CacheModel.FileInfo r2 = this.itemInners.get(r8).file;
            if (r2 != r72.getTag()) goto L5;
            boolean r3 = true;
        L7:
            if (r8 == (this.itemInners.size() - 1)) goto L9;
            r1 = true;
        L9:
            r72.setTag(r2);
            CachedMediaLayout.m6475$$Nest$mcheckMessageObjectForAudio(this.this$0, r2, r8);
            r0.setMessageObject(r2.messageObject, r1);
            r0.showName(!r2.metadata.loading, r3);
            r72.drawDivider = r1;
            r72.sizeTextView.setText(AndroidUtilities.formatFileSize(r2.size));
            r72.checkBox.setChecked(this.this$0.cacheModel.isSelected(r2), r3);
            return;
        L5:
            r3 = false;
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            AnonymousClass1 r5 = new AnonymousClass1(this, r7.getContext());
            r5.type = 3;
            AnonymousClass2 r0 = new AnonymousClass2(this, r7.getContext(), 0, null, r5);
            r0.setCheckForButtonPress(true);
            r5.container.addView(r0);
            return new RecyclerListView.Holder(r5);
        }
    }

    public class Page {
        public final BaseAdapter adapter;
        final /* synthetic */ CachedMediaLayout this$0;
        public final String title;
        public final int type;

        private Page(CachedMediaLayout r1, String r2, int r3, BaseAdapter r4) {
            this.this$0 = r1;
            this.title = r2;
            this.type = r3;
            this.adapter = r4;
        }

        public /* synthetic */ Page(CachedMediaLayout r1, String r2, int r3, BaseAdapter r4, CachedMediaLayoutIA r5) {
            this(r1, r2, r3, r4);
        }
    }

    public static /* synthetic */ void $r8$lambda$BhK4a2xsP3DLYvKEOGav2J8QGco(CachedMediaLayout r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$Cuw4-lcuu0FOrEfEArGnJySj_mU, reason: not valid java name */
    public static /* synthetic */ void m6472$r8$lambda$Cuw4lcuu0FOrEfEArGnJySj_mU(CachedMediaLayout r0, CacheModel.FileInfo r1, TLRPC.TL_documentAttributeAudio r2) {
        r0.lambda$checkMessageObjectForAudio$3(r1, r2);
    }

    /* renamed from: $r8$lambda$bWkEwXeIeGQYHf4S0V4Cfi-V6nY, reason: not valid java name */
    public static /* synthetic */ void m6473$r8$lambda$bWkEwXeIeGQYHf4S0V4CfiV6nY(CachedMediaLayout r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$xWlSN0lRTgJbuUKXGxt_wjfKHI0(CachedMediaLayout r0, CacheModel.FileInfo r1, TLRPC.TL_documentAttributeAudio r2, String r3, String r4) {
        r0.lambda$checkMessageObjectForAudio$2(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetbottomPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6474$$Nest$fgetbottomPadding(CachedMediaLayout r0) {
        return r0.bottomPadding;
    }

    /* renamed from: -$$Nest$mcheckMessageObjectForAudio, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6475$$Nest$mcheckMessageObjectForAudio(CachedMediaLayout r0, CacheModel.FileInfo r1, int r2) {
        r0.checkMessageObjectForAudio(r1, r2);
    }

    /* renamed from: -$$Nest$mgetCellForIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SharedPhotoVideoCell2 m6476$$Nest$mgetCellForIndex(CachedMediaLayout r0, int r1) {
        return r0.getCellForIndex(r1);
    }

    /* renamed from: -$$Nest$mopenItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6477$$Nest$mopenItem(CachedMediaLayout r0, CacheModel.FileInfo r1, CacheCell r2) {
        r0.openItem(r1, r2);
    }

    /* renamed from: -$$Nest$mopenPhoto, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6478$$Nest$mopenPhoto(CachedMediaLayout r0, ItemInner r1, MediaAdapter r2, RecyclerListView r3, SharedPhotoVideoCell2 r4) {
        r0.openPhoto(r1, r2, r3, r4);
    }

    public CachedMediaLayout(Context r21, BaseFragment r22) {
        super(r21);
        this.actionModeViews = new ArrayList();
        this.pages = new ArrayList();
        Page[] r8 = new Page[5];
        this.allPages = r8;
        this.parentFragment = r22;
        r8[0] = new Page(this, LocaleController.getString(R.string.FilterChats), 0, new DialogsAdapter(this, null), null);
        this.allPages[1] = new Page(this, LocaleController.getString(R.string.MediaTab), 1, new MediaAdapter(this, false, null), null);
        this.allPages[2] = new Page(this, LocaleController.getString(R.string.SharedFilesTab2), 2, new DocumentsAdapter(this, null), null);
        this.allPages[3] = new Page(this, LocaleController.getString(R.string.Music), 3, new MusicAdapter(this, null), null);
        int r0 = 0;
    L3:
        Page[] r2 = this.allPages;
        if (r0 >= r2.length) goto L10;
        Page r23 = r2[r0];
        if (r23 == null) goto L9;
        this.pages.add(r0, r23);
    L9:
        r0 = r0 + 1;
        goto L3
    L10:
        ViewPagerFixed r02 = new ViewPagerFixed(getContext());
        this.viewPagerFixed = r02;
        r02.setAllowDisallowInterceptTouch(false);
        addView(this.viewPagerFixed, LayoutHelper.createFrame(-1, -1.0f, 0, 0.0f, 48.0f, 0.0f, 0.0f));
        ViewPagerFixed.TabsView r03 = this.viewPagerFixed.createTabsView(true, 3);
        this.tabs = r03;
        addView(r03, LayoutHelper.createFrame(-1, 48.0f));
        View r04 = new AnonymousClass1(this, getContext());
        this.divider = r04;
        addView(r04, LayoutHelper.createFrame(-1, 1.0f, 0, 0.0f, 48.0f, 0.0f, 0.0f));
        r04.getLayoutParams().height = 1;
        this.viewPagerFixed.setAdapter(new AnonymousClass2(this, r21, r22));
        LinearLayout r7 = new LinearLayout(r21);
        this.actionModeLayout = r7;
        r7.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        r7.setAlpha(0.0f);
        r7.setClickable(true);
        addView(r7, LayoutHelper.createFrame(-1, 48.0f));
        AndroidUtilities.updateViewVisibilityAnimated(r7, false, 1.0f, false);
        ImageView r05 = new ImageView(r21);
        this.closeButton = r05;
        r05.setScaleType(ImageView.ScaleType.CENTER);
        BackDrawable r1 = new BackDrawable(true);
        this.backDrawable = r1;
        r05.setImageDrawable(r1);
        int r24 = Theme.key_actionBarActionModeDefaultIcon;
        r1.setColor(Theme.getColor(r24));
        int r12 = Theme.key_actionBarActionModeDefaultSelector;
        r05.setBackground(Theme.createSelectorDrawable(Theme.getColor(r12), 1));
        r05.setContentDescription(LocaleController.getString(R.string.Close));
        r7.addView(r05, new LinearLayout.LayoutParams(AndroidUtilities.dp(54.0f), -1));
        this.actionModeViews.add(r05);
        r05.setOnClickListener(new CachedMediaLayout$$ExternalSyntheticLambda0(this));
        AnimatedTextView r06 = new AnimatedTextView(r21, true, true, true);
        this.selectedMessagesCountTextView = r06;
        r06.setTextSize(AndroidUtilities.dp(18.0f));
        r06.setTypeface(AndroidUtilities.bold());
        r06.setTextColor(Theme.getColor(r24));
        r7.addView(r06, LayoutHelper.createLinear(0, -1, 1.0f, 18, 0, 0, 0));
        this.actionModeViews.add(r06);
        ActionBarMenuItem r07 = new ActionBarMenuItem(r21, null, Theme.getColor(r12), Theme.getColor(r24), false);
        this.clearItem = r07;
        r07.setIcon(R.drawable.msg_clear);
        r07.setContentDescription(LocaleController.getString(R.string.Delete));
        r07.setDuplicateParentStateEnabled(false);
        r7.addView(r07, new LinearLayout.LayoutParams(AndroidUtilities.dp(54.0f), -1));
        this.actionModeViews.add(r07);
        r07.setOnClickListener(new CachedMediaLayout$$ExternalSyntheticLambda1(this));
    }

    private void checkMessageObjectForAudio(CacheModel.FileInfo r7, int r8) {
        if (r7.messageObject != null) goto L13;
        TLRPC.TL_message r0 = new TLRPC.TL_message();
        r0.out = true;
        r0.id = r8;
        r0.peer_id = new TLRPC.TL_peerUser();
        TLRPC.TL_peerUser r82 = new TLRPC.TL_peerUser();
        r0.from_id = r82;
        TLRPC.Peer r2 = r0.peer_id;
        long r3 = UserConfig.getInstance(this.parentFragment.getCurrentAccount()).getClientUserId();
        r82.user_id = r3;
        r2.user_id = r3;
        r0.date = (int) (System.currentTimeMillis() / 1000);
        r0.message = _UrlKt.FRAGMENT_ENCODE_SET;
        r0.attachPath = r7.file.getPath();
        TLRPC.TL_messageMediaDocument r83 = new TLRPC.TL_messageMediaDocument();
        r0.media = r83;
        r83.flags |= 3;
        r83.document = new TLRPC.TL_document();
        r0.flags |= 768;
        r0.dialog_id = r7.dialogId;
        String r84 = FileLoader.getFileExtension(r7.file);
        TLRPC.Document r22 = r0.media.document;
        r22.id = 0;
        r22.access_hash = 0;
        r22.file_reference = new byte[0];
        r22.date = r0.date;
        if (r84.length() > 0) goto L8;
        r84 = HlsSegmentFormat.MP3;
    L8:
        r22.mime_type = "audio/".concat(r84);
        TLRPC.Document r85 = r0.media.document;
        r85.size = r7.size;
        r85.dc_id = 0;
        TLRPC.TL_documentAttributeAudio r86 = new TLRPC.TL_documentAttributeAudio();
        if (r7.metadata != null) goto L11;
        CacheModel.FileInfo.FileMetadata r23 = new CacheModel.FileInfo.FileMetadata();
        r7.metadata = r23;
        r23.loading = true;
        Utilities.globalQueue.postRunnable(new CachedMediaLayout$$ExternalSyntheticLambda2(this, r7, r86));
    L11:
        r86.flags |= 3;
        r0.media.document.attributes.add(r86);
        TLRPC.TL_documentAttributeFilename r87 = new TLRPC.TL_documentAttributeFilename();
        r87.file_name = r7.file.getName();
        r0.media.document.attributes.add(r87);
        MessageObject r88 = new MessageObject(this.parentFragment.getCurrentAccount(), r0, false, false);
        r7.messageObject = r88;
        r88.mediaExists = true;
        return;
    }

    public static boolean fileIsMedia(File r3) {
        String r0 = r3.getName().toLowerCase();
        if (r3.getName().endsWith("mp4") == false) goto L5;
        return true;
    L5:
        if (r3.getName().endsWith(".jpg") == false) goto L7;
        return true;
    L7:
        if (r0.endsWith(".jpeg") == false) goto L9;
        return true;
    L9:
        if (r0.endsWith(".png") == false) goto L11;
        return true;
    L11:
        if (r0.endsWith(".gif") == true) goto L20;
        return false;
    L20:
        return true;
    }

    private SharedPhotoVideoCell2 getCellForIndex(int r4) {
        RecyclerListView r3 = getListView();
        int r0 = 0;
    L4:
        if (r0 >= r3.getChildCount()) goto L12;
        View r1 = r3.getChildAt(r0);
        if (r3.getChildAdapterPosition(r1) != r4) goto L11;
        if ((r1 instanceof SharedPhotoVideoCell2) == false) goto L11;
        return (SharedPhotoVideoCell2) r1;
    L11:
        r0 = r0 + 1;
        goto L4
    L12:
        return null;
    }

    private /* synthetic */ void lambda$checkMessageObjectForAudio$2(CacheModel.FileInfo r3, TLRPC.TL_documentAttributeAudio r4, String r5, String r6) {
        CacheModel.FileInfo.FileMetadata r0 = r3.metadata;
        r0.loading = false;
        r0.title = r5;
        r4.title = r5;
        r0.author = r6;
        r4.performer = r6;
        updateRow(r3, 3);
    }

    private /* synthetic */ void lambda$checkMessageObjectForAudio$3(CacheModel.FileInfo r12, TLRPC.TL_documentAttributeAudio r13) {
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        MediaMetadataRetriever r2 = null;
        MediaMetadataRetriever r3 = new MediaMetadataRetriever();     // Catch: Throwable -> L17 Exception -> L19
        Uri r0 = Uri.fromFile(r12.file);     // Catch: Throwable -> L10 Exception -> L14
        r3.setDataSource(getContext(), r0);     // Catch: Throwable -> L10 Exception -> L14
        String r22 = r3.extractMetadata(7);     // Catch: Throwable -> L10 Exception -> L14
        r1 = r3.extractMetadata(2);     // Catch: Throwable -> L10 Exception -> L12
        r3.release();     // Catch: Throwable -> L30
    L8:
        String r8 = r22;
    L25:
        AndroidUtilities.runOnUIThread(new CachedMediaLayout$$ExternalSyntheticLambda3(this, r12, r13, r8, r1));
        return;
    L12:
        e = e;
        String r32 = r22;
        r2 = r3;
    L21:
        FileLog.e(e);     // Catch: Throwable -> L17
        if (r2 != null) goto L44;
    L24:
        r8 = r32;
        goto L25
    L44:
        r2.release();     // Catch: Throwable -> L31
    L14:
        e = e;
        r2 = r3;
    L16:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        th = move-exception;
        Throwable r11 = th;
        r2 = r3;
    L27:
        if (r2 == null) goto L46;
        r2.release();     // Catch: Throwable -> L32
        throw r11;
    L47:
        throw r11;
    L46:
        throw r11;
    L17:
        th = move-exception;
        r11 = th;
    L19:
        e = e;
        goto L16
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        this.delegate.clearSelection();
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        this.delegate.clear();
    }

    private void openItem(CacheModel.FileInfo r19, CacheCell r20) {
        RecyclerListView r3 = (RecyclerListView) this.viewPagerFixed.getCurrentView();
        if (r20.type != 2) goto L19;
        if ((r3.getAdapter() instanceof DocumentsAdapter) == false) goto L31;
        DocumentsAdapter r4 = (DocumentsAdapter) r3.getAdapter();
        PhotoViewer.getInstance().setParentActivity(this.parentFragment);
        if (this.placeProvider != null) goto L10;
        this.placeProvider = new BasePlaceProvider(this, null);
    L10:
        this.placeProvider.setRecyclerListView(r3);
        if (fileIsMedia(r19.file) == false) goto L17;
        ArrayList<Object> r5 = new ArrayList();
        String r11 = r19.file.getPath();
        boolean r42 = true;
        if (r19.type == 1) goto L16;
        r42 = false;
    L16:
        r5.add(new MediaController.PhotoEntry(0, 0, 0, r11, 0, r42, 0, 0, 0));
        PhotoViewer.getInstance().openPhotoForSelect(r5, 0, -1, false, this.placeProvider, null);
        goto L19
    L17:
        File r112 = r19.file;
        AndroidUtilities.openForView(r112, r112.getName(), null, this.parentFragment.getParentActivity(), null, false);
        goto L19
    L31:
        return;
    L19:
        if (r20.type == 3) goto L21;
        return;
    L21:
        if (MediaController.getInstance().isPlayingMessage(r19.messageObject) == true) goto L23;
        MediaController.getInstance().playMessage(r19.messageObject);
        return;
    L23:
        if (MediaController.getInstance().isMessagePaused() == true) goto L26;
        MediaController.getInstance().pauseMessage(r19.messageObject);
        return;
    L26:
        MediaController.getInstance().playMessage(r19.messageObject);
    }

    private void openPhoto(ItemInner r8, MediaAdapter r9, RecyclerListView r10, SharedPhotoVideoCell2 r11) {
        PhotoViewer.getInstance().setParentActivity(this.parentFragment);
        if (this.placeProvider != null) goto L5;
        this.placeProvider = new BasePlaceProvider(this, null);
    L5:
        this.placeProvider.setRecyclerListView(r10);
        if (r9.itemInners.indexOf(r8) < 0) goto L9;
        PhotoViewer.getInstance().openPhotoForSelect(r9.getPhotos(), r9.itemInners.indexOf(r8), -1, false, this.placeProvider, null);
        return;
    }

    private void updateRow(CacheModel.FileInfo r6, int r7) {
        int r1 = 0;
    L4:
        if (r1 >= this.viewPagerFixed.getViewPages().length) goto L17;
        RecyclerListView r2 = (RecyclerListView) this.viewPagerFixed.getViewPages()[r1];
        if (r2 == null) goto L16;
        if (((BaseAdapter) r2.getAdapter()).type != r7) goto L16;
        BaseAdapter r22 = (BaseAdapter) r2.getAdapter();
        int r3 = 0;
    L11:
        if (r3 >= r22.itemInners.size()) goto L16;
        if (r22.itemInners.get(r3).file == r6) goto L14;
        r3 = r3 + 1;
        goto L11
    L14:
        r22.notifyItemChanged(r3);
    L16:
        r1 = r1 + 1;
        goto L4
    }

    @Override // org.telegram.ui.Components.NestedSizeNotifierLayout.ChildLayout
    public RecyclerListView getListView() {
        if (this.viewPagerFixed.getCurrentView() != null) goto L7;
        return null;
    L7:
        return (RecyclerListView) this.viewPagerFixed.getCurrentView();
    }

    @Override // org.telegram.ui.Components.NestedSizeNotifierLayout.ChildLayout
    public boolean isAttached() {
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
    }

    public void setBottomPadding(int r4) {
        this.bottomPadding = r4;
        int r1 = 0;
    L4:
        if (r1 >= this.viewPagerFixed.getViewPages().length) goto L9;
        RecyclerListView r2 = (RecyclerListView) this.viewPagerFixed.getViewPages()[r1];
        if (r2 == null) goto L8;
        r2.setPadding(0, 0, 0, r4);
    L8:
        r1 = r1 + 1;
        goto L4
    }

    public void setCacheModel(CacheModel r1) {
        this.cacheModel = r1;
        update();
    }

    public void setDelegate(Delegate r1) {
        this.delegate = r1;
    }

    public void showActionMode(boolean r1) {
    }

    public void update() {
        ArrayList r0 = new ArrayList();
        r0.addAll(this.pages);
        this.pages.clear();
        int r2 = 0;
        if (this.cacheModel == null) goto L42;
        int r1 = 0;
    L5:
        Page[] r4 = this.allPages;
        if (r1 >= r4.length) goto L42;
        Page r42 = r4[r1];
        if (r42 == null) goto L40;
        if (r42.type != 0) goto L16;
        if (this.cacheModel.entities.isEmpty() == true) goto L16;
        this.pages.add(this.allPages[r1]);
    L16:
        if (this.allPages[r1].type != 1) goto L21;
        if (this.cacheModel.media.isEmpty() == true) goto L21;
        this.pages.add(this.allPages[r1]);
    L21:
        if (this.allPages[r1].type != 2) goto L26;
        if (this.cacheModel.documents.isEmpty() == true) goto L26;
        this.pages.add(this.allPages[r1]);
    L26:
        if (this.allPages[r1].type != 3) goto L31;
        if (this.cacheModel.music.isEmpty() == true) goto L31;
        this.pages.add(this.allPages[r1]);
    L31:
        if (this.allPages[r1].type != 5) goto L36;
        if (this.cacheModel.voice.isEmpty() == true) goto L36;
        this.pages.add(this.allPages[r1]);
    L36:
        if (this.allPages[r1].type != 4) goto L40;
        if (this.cacheModel.stories.isEmpty() == true) goto L40;
        this.pages.add(this.allPages[r1]);
    L40:
        r1 = r1 + 1;
    L42:
        if (this.pages.size() != 1) goto L47;
        if (this.cacheModel.isDialog == false) goto L47;
        this.tabs.setVisibility(8);
        ((ViewGroup.MarginLayoutParams) this.viewPagerFixed.getLayoutParams()).topMargin = 0;
        ((ViewGroup.MarginLayoutParams) this.divider.getLayoutParams()).topMargin = 0;
    L47:
        if (r0.size() != this.pages.size()) goto L55;
        int r12 = 0;
    L50:
        if (r12 >= r0.size()) goto L57;
        if (((Page) r0.get(r12)).type != this.pages.get(r12).type) goto L55;
        r12 = r12 + 1;
    L57:
        if (r2 >= this.pages.size()) goto L62;
        if (this.pages.get(r2).adapter == null) goto L61;
        this.pages.get(r2).adapter.update();
    L61:
        r2 = r2 + 1;
        goto L57
    L62:
        return;
    L55:
        this.viewPagerFixed.rebuild(true);
        goto L57
    }

    public void updateVisibleRows() {
        int r0 = 0;
    L4:
        if (r0 >= this.viewPagerFixed.getViewPages().length) goto L6;
        AndroidUtilities.updateVisibleRows((RecyclerListView) this.viewPagerFixed.getViewPages()[r0]);
        r0 = r0 + 1;
        goto L4
    }
}
