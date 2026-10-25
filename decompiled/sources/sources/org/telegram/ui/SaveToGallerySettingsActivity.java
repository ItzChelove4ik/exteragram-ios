package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.LongSparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.SaveToGallerySettingsHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Cells.UserCell2;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SeekBarView;

/* loaded from: classes6.dex */
public class SaveToGallerySettingsActivity extends BaseFragment {
    private final int VIEW_TYPE_ADD_EXCEPTION;
    private final int VIEW_TYPE_CHAT;
    private final int VIEW_TYPE_CHOOSER;
    private final int VIEW_TYPE_DELETE_ALL;
    private final int VIEW_TYPE_DIVIDER;
    private final int VIEW_TYPE_DIVIDER_INFO;
    private final int VIEW_TYPE_DIVIDER_LAST;
    private final int VIEW_TYPE_HEADER;
    private final int VIEW_TYPE_TOGGLE;
    Adapter adapter;

    @Keep
    public int addExceptionRow;

    @Keep
    public int deleteAllExceptionsRow;
    SaveToGallerySettingsHelper.DialogException dialogException;
    long dialogId;
    LongSparseArray<SaveToGallerySettingsHelper.DialogException> exceptionsDialogs;
    boolean isNewException;
    ArrayList<Item> items;

    @Keep
    public int maxVideoSizeRow;
    RecyclerListView recyclerListView;
    int savePhotosRow;
    int saveVideosRow;
    int type;
    int videoDividerRow;

    /* renamed from: org.telegram.ui.SaveToGallerySettingsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ SaveToGallerySettingsActivity this$0;

        public AnonymousClass1(SaveToGallerySettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    public class Adapter extends AdapterWithDiffUtils {
        final /* synthetic */ SaveToGallerySettingsActivity this$0;

        /* renamed from: org.telegram.ui.SaveToGallerySettingsActivity$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 implements SeekBarView.SeekBarViewDelegate {
            final /* synthetic */ Adapter this$1;
            final /* synthetic */ SelectableAnimatedTextView val$lowerTextView;
            final /* synthetic */ SelectableAnimatedTextView val$midTextView;
            final /* synthetic */ SeekBarView val$slideChooseView;
            final /* synthetic */ SelectableAnimatedTextView val$topTextView;

            public AnonymousClass1(Adapter r1, SeekBarView r2, SelectableAnimatedTextView r3, SelectableAnimatedTextView r4, SelectableAnimatedTextView r5) {
                this.this$1 = r1;
                this.val$slideChooseView = r2;
                this.val$lowerTextView = r3;
                this.val$midTextView = r4;
                this.val$topTextView = r5;
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public void onSeekBarDrag(boolean r10, float r11) {
                boolean r0 = this.val$slideChooseView.isAttachedToWindow();
                if (r11 <= 0.7f) goto L5;
                long r1 = (long) (1.048576E8f + (4.0894464E9f * ((r11 - 0.7f) / 0.3f)));
            L7:
                if (r11 < 1.0f) goto L10;
                this.val$lowerTextView.setSelectedInternal(false, r0);
                this.val$midTextView.setSelectedInternal(false, r0);
                this.val$topTextView.setSelectedInternal(true, r0);
                AndroidUtilities.updateViewVisibilityAnimated(this.val$midTextView, false, 0.8f, r0);
            L13:
                if (r10 == false) goto L16;
                this.this$1.this$0.getSettings().limitVideo = r1;
                SaveToGallerySettingsActivity.m18514$$Nest$monSettingsUpdated(this.this$1.this$0);
                return;
            L16:
                return;
            L10:
                if (r11 != 0.0f) goto L12;
                this.val$lowerTextView.setSelectedInternal(true, r0);
                this.val$midTextView.setSelectedInternal(false, r0);
                this.val$topTextView.setSelectedInternal(false, r0);
                AndroidUtilities.updateViewVisibilityAnimated(this.val$midTextView, false, 0.8f, r0);
                goto L13
            L12:
                this.val$midTextView.setText(LocaleController.formatString("UpToFileSize", R.string.UpToFileSize, new Object[]{AndroidUtilities.formatFileSize(r1, true, false)}), false);
                this.val$lowerTextView.setSelectedInternal(false, r0);
                this.val$midTextView.setSelectedInternal(true, r0);
                this.val$topTextView.setSelectedInternal(false, r0);
                AndroidUtilities.updateViewVisibilityAnimated(this.val$midTextView, true, 0.8f, r0);
                goto L13
            L5:
                r1 = (long) ((1.0433331E8f * (r11 / 0.7f)) + 524288.0f);
                goto L7
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public void onSeekBarPressed(boolean r1) {
            }
        }

        private Adapter(SaveToGallerySettingsActivity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(SaveToGallerySettingsActivity r1, SaveToGallerySettingsActivityIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.items.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return this.this$0.items.get(r1).viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3) {
            if (r3.getItemViewType() != 1) goto L5;
        L13:
            return true;
        L5:
            if (r3.getItemViewType() == 2) goto L13;
            if (r3.getItemViewType() == 4) goto L13;
            if (r3.getItemViewType() == 6) goto L13;
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r12, int r13) {
            boolean r1 = false;
            if (this.this$0.items.get(r13).viewType != 1) goto L10;
            TextCell r122 = (TextCell) r12.itemView;
            if (this.this$0.exceptionsDialogs.size() <= 0) goto L7;
            r1 = true;
        L7:
            r122.setNeedDivider(r1);
            return;
        L10:
            if (this.this$0.items.get(r13).viewType != 6) goto L18;
            TextCheckCell r123 = (TextCheckCell) r12.itemView;
            SaveToGallerySettingsHelper.Settings r0 = this.this$0.getSettings();
            if (r13 != this.this$0.savePhotosRow) goto L15;
            r123.setTextAndCheck(LocaleController.getString(R.string.SaveToGalleryPhotos), r0.savePhoto, true);
            SaveToGallerySettingsActivity r132 = this.this$0;
            int r02 = Theme.key_statisticChartLine_lightblue;
            r123.setColorfullIcon(r132.getThemedColor(r02), R.drawable.msg_filled_data_photos);
            r123.setColorfullIcon(this.this$0.getThemedColor(r02), R.drawable.msg_filled_data_photos);
            return;
        L15:
            r123.setTextAndCheck(LocaleController.getString(R.string.SaveToGalleryVideos), r0.saveVideo, false);
            r123.setColorfullIcon(this.this$0.getThemedColor(Theme.key_statisticChartLine_green), R.drawable.msg_filled_data_videos);
            return;
        L18:
            if (this.this$0.items.get(r13).viewType != 7) goto L39;
            TextInfoPrivacyCell r124 = (TextInfoPrivacyCell) r12.itemView;
            SaveToGallerySettingsActivity r03 = this.this$0;
            if (r13 != r03.videoDividerRow) goto L36;
            long r5 = r03.getSettings().limitVideo;
            SaveToGallerySettingsActivity r11 = this.this$0;
            if (r11.dialogException == null) goto L25;
            r124.setText(LocaleController.formatString("SaveToGalleryVideoHintCurrent", R.string.SaveToGalleryVideoHintCurrent, new Object[0]));
            return;
        L25:
            int r112 = r11.type;
            if (r112 != 1) goto L30;
            r124.setText(LocaleController.formatString("SaveToGalleryVideoHintUser", R.string.SaveToGalleryVideoHintUser, new Object[0]));
            return;
        L30:
            if (r112 != 4) goto L33;
            r124.setText(LocaleController.formatString("SaveToGalleryVideoHintChannels", R.string.SaveToGalleryVideoHintChannels, new Object[0]));
            return;
        L33:
            if (r112 != 2) goto L64;
            r124.setText(LocaleController.formatString("SaveToGalleryVideoHintGroup", R.string.SaveToGalleryVideoHintGroup, new Object[0]));
            return;
        L64:
            return;
        L36:
            r124.setText(r03.items.get(r13).title);
            return;
        L39:
            if (this.this$0.items.get(r13).viewType != 5) goto L43;
            ((HeaderCell) r12.itemView).setText(this.this$0.items.get(r13).title);
            return;
        L43:
            if (this.this$0.items.get(r13).viewType != 2) goto L65;
            UserCell r52 = (UserCell) r12.itemView;
            SaveToGallerySettingsHelper.DialogException r125 = this.this$0.items.get(r13).exception;
            TLObject r6 = this.this$0.getMessagesController().getUserOrChat(r125.dialogId);
            if ((r6 instanceof TLRPC.User) == false) goto L52;
            TLRPC.User r04 = (TLRPC.User) r6;
            if (r04.self == false) goto L49;
            String r05 = LocaleController.getString(R.string.SavedMessages);
        L50:
            String r7 = r05;
            r52.setSelfAsSavedMessages(true);
            CharSequence r8 = r125.createDescription(SaveToGallerySettingsActivity.access$200(this.this$0));
            if (r13 != (this.this$0.items.size() - 1)) goto L58;
        L61:
            boolean r10 = true;
        L62:
            r52.setData(r6, r7, r8, 0, r10);
            return;
        L58:
            if (this.this$0.items.get(r13 + 1).viewType == 2) goto L61;
            r10 = false;
            goto L62
        L49:
            r05 = ContactsController.formatName(r04.first_name, r04.last_name);
            goto L50
        L52:
            if ((r6 instanceof TLRPC.Chat) == false) goto L54;
            r05 = ((TLRPC.Chat) r6).title;
            goto L50
        L54:
            r05 = null;
            goto L50
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r24, int r25) {
            View r2 = null;
            switch(r25) {
                case 1: goto L29;
                case 2: goto L28;
                case 3: goto L27;
                case 4: goto L26;
                case 5: goto L25;
                case 6: goto L24;
                case 7: goto L23;
                case 8: goto L11;
                case 9: goto L6;
                case 10: goto L5;
                default: goto L30;
            };
        L5:
            ShadowSectionCell r22 = new ShadowSectionCell(r24.getContext());
            r22.setBackgroundDrawable(Theme.getThemedDrawable(this.this$0.getContext(), R.drawable.greydivider_bottom, Theme.getColor(Theme.key_windowBackgroundGrayShadow, this.this$0.getResourceProvider())));
            r2 = r22;
            goto L30
        L6:
            UserCell2 r0 = new UserCell2(this.this$0.getContext(), 4, 0, this.this$0.getResourceProvider());
            boolean r3 = DialogObject.isUserDialog(this.this$0.dialogId);
            SaveToGallerySettingsActivity r4 = this.this$0;
            if (r3 == false) goto L9;
            TLObject r32 = MessagesController.getInstance(SaveToGallerySettingsActivity.access$000(r4)).getUser(Long.valueOf(this.this$0.dialogId));
        L10:
            r0.setData(r32, null, null, 0);
            r0.setBackgroundColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            r2 = r0;
            goto L30
        L9:
            r32 = MessagesController.getInstance(SaveToGallerySettingsActivity.access$100(r4)).getChat(Long.valueOf(-this.this$0.dialogId));
            goto L10
        L11:
            LinearLayout r9 = new LinearLayout(this.this$0.getContext());
            r9.setOrientation(1);
            SeekBarView r23 = new SeekBarView(this.this$0.getContext());
            FrameLayout r33 = new FrameLayout(this.this$0.getContext());
            SaveToGallerySettingsActivity r5 = this.this$0;
            SelectableAnimatedTextView r42 = new SelectableAnimatedTextView(r5, r5.getContext());
            r42.setTextSize(AndroidUtilities.dp(13.0f));
            r42.setText(AndroidUtilities.formatFileSize(524288, true, false));
            r33.addView(r42, LayoutHelper.createFrame(-2, -2, 83));
            SaveToGallerySettingsActivity r13 = this.this$0;
            SelectableAnimatedTextView r43 = new SelectableAnimatedTextView(r13, r13.getContext());
            r43.setTextSize(AndroidUtilities.dp(13.0f));
            r33.addView(r43, LayoutHelper.createFrame(-2, -2, 81));
            SaveToGallerySettingsActivity r14 = this.this$0;
            SelectableAnimatedTextView r52 = new SelectableAnimatedTextView(r14, r14.getContext());
            r52.setTextSize(AndroidUtilities.dp(13.0f));
            long r132 = SaveToGallerySettingsHelper.MAX_VIDEO_LIMIT;
            r52.setText(AndroidUtilities.formatFileSize(SaveToGallerySettingsHelper.MAX_VIDEO_LIMIT, true, false));
            r33.addView(r52, LayoutHelper.createFrame(-2, -2, 85));
            r9.addView(r33, LayoutHelper.createLinear(-1, 20, 0, 21, 10, 21, 0));
            r9.addView(r23, LayoutHelper.createLinear(-1, 38, 0, 5, 0, 5, 4));
            long r10 = this.this$0.getSettings().limitVideo;
            if (r10 >= 0) goto L14;
        L17:
            r23.setReportChanges(true);
            r23.setDelegate(new AnonymousClass1(this, r23, r42, r43, r52));
            if (r132 <= 7.340032E7f) goto L20;
            float r34 = (0.3f * ((r132 - SaveToGallerySettingsHelper.DEFAULT_VIDEO_LIMIT) / 4.0894464E9f)) + 0.7f;
        L21:
            r23.setProgress(r34);
            r23.delegate.onSeekBarDrag(false, r23.getProgress());
            r9.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            UserCell r92 = r9;
        L22:
            r2 = r92;
            goto L30
        L20:
            r34 = ((r132 - 524288) / 1.0433331E8f) * 0.7f;
            goto L21
        L14:
            if (r10 > SaveToGallerySettingsHelper.MAX_VIDEO_LIMIT) goto L17;
            r132 = r10;
            goto L17
        L23:
            r2 = new TextInfoPrivacyCell(r24.getContext());
            goto L30
        L24:
            TextCheckCell r26 = new TextCheckCell(r24.getContext());
            r26.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r2 = r26;
            goto L30
        L25:
            HeaderCell r27 = new HeaderCell(r24.getContext());
            r27.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r2 = r27;
            goto L30
        L26:
            TextCell r28 = new TextCell(r24.getContext());
            r28.setText(LocaleController.getString(R.string.NotificationsDeleteAllException), false);
            r28.setColors(-1, Theme.key_text_RedRegular);
            r28.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r2 = r28;
            goto L30
        L27:
            r2 = new ShadowSectionCell(r24.getContext());
            goto L30
        L28:
            UserCell r93 = new UserCell(r24.getContext(), 4, 0, false, false);
            r93.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r92 = r93;
            goto L22
        L29:
            TextCell r29 = new TextCell(r24.getContext());
            r29.setTextAndIcon(LocaleController.getString(R.string.NotificationsAddAnException), R.drawable.msg_contact_add, true);
            r29.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            r29.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r2 = r29;
        L30:
            r2.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r2);
        }
    }

    public class Item extends AdapterWithDiffUtils.Item {
        final SaveToGallerySettingsHelper.DialogException exception;
        final /* synthetic */ SaveToGallerySettingsActivity this$0;
        String title;

        private Item(SaveToGallerySettingsActivity r1, int r2) {
            this.this$0 = r1;
            super(r2, false);
            this.exception = null;
        }

        private Item(SaveToGallerySettingsActivity r1, int r2, String r3) {
            this.this$0 = r1;
            super(r2, false);
            this.title = r3;
            this.exception = null;
        }

        public /* synthetic */ Item(SaveToGallerySettingsActivity r1, int r2, String r3, SaveToGallerySettingsActivityIA r4) {
            this(r1, r2, r3);
        }

        private Item(SaveToGallerySettingsActivity r1, int r2, SaveToGallerySettingsHelper.DialogException r3) {
            this.this$0 = r1;
            super(r2, false);
            this.exception = r3;
        }

        public /* synthetic */ Item(SaveToGallerySettingsActivity r1, int r2, SaveToGallerySettingsHelper.DialogException r3, SaveToGallerySettingsActivityIA r4) {
            this(r1, r2, r3);
        }

        public /* synthetic */ Item(SaveToGallerySettingsActivity r1, int r2, SaveToGallerySettingsActivityIA r3) {
            this(r1, r2);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L26:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L26;
            Item r52 = (Item) r5;
            if (this.viewType == r52.viewType) goto L13;
            return false;
        L13:
            String r2 = this.title;
            if (r2 != null) goto L16;
            SaveToGallerySettingsHelper.DialogException r4 = this.exception;
            if (r4 == null) goto L25;
            SaveToGallerySettingsHelper.DialogException r53 = r52.exception;
            if (r53 == null) goto L25;
            if (r4.dialogId != r53.dialogId) goto L24;
            return true;
        L24:
            return false;
        L25:
            return true;
        L16:
            return Objects.equals(r2, r52.title);
        }
    }

    public class SelectableAnimatedTextView extends AnimatedTextView {
        AnimatedFloat progressToSelect;
        boolean selected;
        final /* synthetic */ SaveToGallerySettingsActivity this$0;

        public SelectableAnimatedTextView(SaveToGallerySettingsActivity r2, Context r3) {
            this.this$0 = r2;
            super(r3, true, true, false);
            this.progressToSelect = new AnimatedFloat(this);
            getDrawable().setAllowCancel(true);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r4) {
            AnimatedFloat r0 = this.progressToSelect;
            if (this.selected == false) goto L5;
            float r1 = 1.0f;
        L6:
            r0.set(r1);
            setTextColor(ColorUtils.blendARGB(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteGrayText), this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlueText), this.progressToSelect.get()));
            super.dispatchDraw(r4);
            return;
        L5:
            r1 = 0.0f;
            goto L6
        }

        public void setSelectedInternal(boolean r2, boolean r3) {
            if (this.selected == r2) goto L10;
            this.selected = r2;
            AnimatedFloat r0 = this.progressToSelect;
            if (r2 == false) goto L7;
            float r22 = 1.0f;
        L8:
            r0.set(r22, r3);
            invalidate();
            return;
        L7:
            r22 = 0.0f;
            goto L8
        }
    }

    public static /* synthetic */ boolean $r8$lambda$6jwZRUcuGLe0jRYjI8Z93dB0DqE(SaveToGallerySettingsActivity r0, DialogsActivity r1, ArrayList r2, CharSequence r3, boolean r4, boolean r5, int r6, int r7, TopicsFragment r8) {
        return r0.lambda$createView$0(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$I7AKAJXyQG0jhVB-RxwUDIet36Y, reason: not valid java name */
    public static /* synthetic */ void m18511$r8$lambda$I7AKAJXyQG0jhVBRxwUDIet36Y(SaveToGallerySettingsActivity r0, View r1, int r2, float r3, float r4) {
        r0.lambda$createView$2(r1, r2, r3, r4);
    }

    public static /* synthetic */ boolean $r8$lambda$LoKI7TdJgkOOlrRfeLESzKOrefE(SaveToGallerySettingsActivity r0, View r1, int r2, float r3, float r4) {
        return r0.lambda$createView$5(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$NLdpExMTVk3BGbKTFTkg-fA4jK4, reason: not valid java name */
    public static /* synthetic */ void m18512$r8$lambda$NLdpExMTVk3BGbKTFTkgfA4jK4(SaveToGallerySettingsActivity r0, View r1) {
        r0.lambda$createView$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$gQfxSSPCFqdFZwxVdK7ZuAlsxrE(SaveToGallerySettingsActivity r0) {
        r0.lambda$createView$1();
    }

    public static /* synthetic */ void $r8$lambda$inE0AvvmrFKJPK1ONTtQpBy_U4A(SaveToGallerySettingsActivity r0, ActionBarPopupWindow r1, int r2, View r3) {
        r0.lambda$createView$3(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$wYsYoHXc2iY1R_HntlmDt-oCfHs, reason: not valid java name */
    public static /* synthetic */ void m18513$r8$lambda$wYsYoHXc2iY1R_HntlmDtoCfHs(SaveToGallerySettingsActivity r0, ActionBarPopupWindow r1, SaveToGallerySettingsHelper.DialogException r2, View r3) {
        r0.lambda$createView$4(r1, r2, r3);
    }

    /* renamed from: -$$Nest$monSettingsUpdated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18514$$Nest$monSettingsUpdated(SaveToGallerySettingsActivity r0) {
        r0.onSettingsUpdated();
    }

    public SaveToGallerySettingsActivity(Bundle r1) {
        super(r1);
        this.VIEW_TYPE_ADD_EXCEPTION = 1;
        this.VIEW_TYPE_CHAT = 2;
        this.VIEW_TYPE_DIVIDER = 3;
        this.VIEW_TYPE_DELETE_ALL = 4;
        this.VIEW_TYPE_HEADER = 5;
        this.VIEW_TYPE_TOGGLE = 6;
        this.VIEW_TYPE_DIVIDER_INFO = 7;
        this.VIEW_TYPE_CHOOSER = 8;
        this.VIEW_TYPE_DIVIDER_LAST = 10;
        this.items = new ArrayList();
        this.exceptionsDialogs = new LongSparseArray();
    }

    public static /* synthetic */ int access$000(SaveToGallerySettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(SaveToGallerySettingsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(SaveToGallerySettingsActivity r0) {
        return r0.currentAccount;
    }

    private /* synthetic */ boolean lambda$createView$0(DialogsActivity r1, ArrayList r2, CharSequence r3, boolean r4, boolean r5, int r6, int r7, TopicsFragment r8) {
        Bundle r12 = new Bundle();
        r12.putLong("dialog_id", ((MessagesStorage.TopicKey) r2.get(0)).dialogId);
        r12.putInt("type", this.type);
        presentFragment(new SaveToGallerySettingsActivity(r12), true);
        return true;
    }

    private /* synthetic */ void lambda$createView$1() {
        this.exceptionsDialogs.clear();
        getUserConfig().updateSaveGalleryExceptions(this.type, this.exceptionsDialogs);
        updateRows();
    }

    private /* synthetic */ void lambda$createView$2(View r8, int r9, float r10, float r11) {
        if (r9 != this.savePhotosRow) goto L7;
        getSettings().savePhoto = !r8.savePhoto;
        onSettingsUpdated();
        updateRows();
        return;
    L7:
        if (r9 != this.saveVideosRow) goto L11;
        getSettings().saveVideo = !r8.saveVideo;
        onSettingsUpdated();
        updateRows();
        return;
    L11:
        if (this.items.get(r9).viewType != 1) goto L21;
        Bundle r82 = new Bundle();
        r82.putBoolean("onlySelect", true);
        r82.putBoolean("checkCanWrite", false);
        int r92 = this.type;
        if (r92 != 2) goto L15;
        r82.putInt("dialogsType", 6);
    L18:
        r82.putBoolean("allowGlobalSearch", false);
        DialogsActivity r93 = new DialogsActivity(r82);
        r93.setDelegate(new SaveToGallerySettingsActivity$$ExternalSyntheticLambda3(this));
        presentFragment(r93);
        return;
    L15:
        if (r92 != 4) goto L17;
        r82.putInt("dialogsType", 5);
        goto L18
    L17:
        r82.putInt("dialogsType", 4);
        goto L18
    L21:
        if (this.items.get(r9).viewType != 2) goto L25;
        Bundle r83 = new Bundle();
        r83.putLong("dialog_id", this.items.get(r9).exception.dialogId);
        r83.putInt("type", this.type);
        presentFragment(new SaveToGallerySettingsActivity(r83));
        return;
    L25:
        if (this.items.get(r9).viewType != 4) goto L28;
        AlertDialog r7 = AlertsCreator.createSimpleAlert(getContext(), LocaleController.getString(R.string.NotificationsDeleteAllExceptionTitle), LocaleController.getString(R.string.NotificationsDeleteAllExceptionAlert), LocaleController.getString(R.string.Delete), new SaveToGallerySettingsActivity$$ExternalSyntheticLambda4(this), null).create();
        r7.show();
        r7.redPositive();
        return;
    }

    private /* synthetic */ void lambda$createView$3(ActionBarPopupWindow r2, int r3, View r4) {
        r2.dismiss();
        Bundle r22 = new Bundle();
        r22.putLong("dialog_id", this.items.get(r3).exception.dialogId);
        r22.putInt("type", this.type);
        presentFragment(new SaveToGallerySettingsActivity(r22));
    }

    private /* synthetic */ void lambda$createView$4(ActionBarPopupWindow r1, SaveToGallerySettingsHelper.DialogException r2, View r3) {
        r1.dismiss();
        LongSparseArray<SaveToGallerySettingsHelper.DialogException> r12 = getUserConfig().getSaveGalleryExceptions(this.type);
        r12.remove(r2.dialogId);
        getUserConfig().updateSaveGalleryExceptions(this.type, r12);
        updateRows();
    }

    private /* synthetic */ boolean lambda$createView$5(View r8, int r9, float r10, float r11) {
        if (this.items.get(r9).viewType != 2) goto L6;
        SaveToGallerySettingsHelper.DialogException r0 = this.items.get(r9).exception;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r1 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(getContext());
        ActionBarMenuSubItem r3 = ActionBarMenuItem.addItem(r1, R.drawable.msg_customize, LocaleController.getString(R.string.EditException), false, null);
        ActionBarMenuSubItem r2 = ActionBarMenuItem.addItem(r1, R.drawable.msg_delete, LocaleController.getString(R.string.DeleteException), false, null);
        int r4 = Theme.key_text_RedRegular;
        r2.setColors(Theme.getColor(r4), Theme.getColor(r4));
        ActionBarPopupWindow r82 = AlertsCreator.createSimplePopup(this, r1, r8, r10, r11);
        r1.setParentWindow(r82);
        r3.setOnClickListener(new SaveToGallerySettingsActivity$$ExternalSyntheticLambda5(this, r82, r9));
        r2.setOnClickListener(new SaveToGallerySettingsActivity$$ExternalSyntheticLambda6(this, r82, r0));
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$createView$6(View r4) {
        if (this.isNewException == false) goto L5;
        LongSparseArray<SaveToGallerySettingsHelper.DialogException> r42 = getUserConfig().getSaveGalleryExceptions(this.type);
        SaveToGallerySettingsHelper.DialogException r0 = this.dialogException;
        r42.put(r0.dialogId, r0);
        getUserConfig().updateSaveGalleryExceptions(this.type, r42);
    L5:
        finishFragment();
    }

    private void onSettingsUpdated() {
        if (this.isNewException == false) goto L6;
        return;
    L6:
        if (this.dialogException == null) goto L9;
        LongSparseArray<SaveToGallerySettingsHelper.DialogException> r0 = getUserConfig().getSaveGalleryExceptions(this.type);
        SaveToGallerySettingsHelper.DialogException r1 = this.dialogException;
        r0.put(r1.dialogId, r1);
        getUserConfig().updateSaveGalleryExceptions(this.type, r0);
        return;
    L9:
        SaveToGallerySettingsHelper.saveSettings(this.type);
    }

    private void updateRows() {
        this.maxVideoSizeRow = -1;
        this.addExceptionRow = -1;
        this.deleteAllExceptionsRow = -1;
        int r2 = 0;
        if (this.isPaused == false) goto L5;
    L7:
        boolean r1 = false;
    L9:
        if (r1 == false) goto L11;
        ArrayList<? extends AdapterWithDiffUtils.Item> r12 = new ArrayList();
        r12.addAll(this.items);
    L12:
        this.items.clear();
        if (this.dialogException == null) goto L15;
        this.items.add(new Item(this, 9, null));
        this.items.add(new Item(this, 3, null));
    L15:
        this.items.add(new Item(this, 5, LocaleController.getString(R.string.SaveToGallery), null));
        this.savePhotosRow = this.items.size();
        this.items.add(new Item(this, 6, null));
        this.saveVideosRow = this.items.size();
        this.items.add(new Item(this, 6, null));
        if (this.dialogException == null) goto L18;
        String r5 = LocaleController.getString(R.string.SaveToGalleryHintCurrent);
    L26:
        this.items.add(new Item(this, 7, r5, null));
        if (getSettings().saveVideo == false) goto L29;
        this.items.add(new Item(this, 5, LocaleController.getString(R.string.MaxVideoSize), null));
        this.maxVideoSizeRow = this.items.size();
        this.items.add(new Item(this, 8, null));
        this.videoDividerRow = this.items.size();
        this.items.add(new Item(this, 7, null));
    L31:
        if (this.dialogException != null) goto L39;
        this.exceptionsDialogs = getUserConfig().getSaveGalleryExceptions(this.type);
        this.addExceptionRow = this.items.size();
        this.items.add(new Item(this, 1, null));
        boolean r0 = false;
    L34:
        if (r2 >= this.exceptionsDialogs.size()) goto L36;
        this.items.add(new Item(this, 2, this.exceptionsDialogs.valueAt(r2), null));
        r2 = r2 + 1;
        r0 = true;
        goto L34
    L36:
        if (r0 == false) goto L38;
        this.items.add(new Item(this, 3, null));
        this.deleteAllExceptionsRow = this.items.size();
        this.items.add(new Item(this, 4, null));
    L38:
        this.items.add(new Item(this, 10, null));
    L39:
        Adapter r02 = this.adapter;
        if (r02 == null) goto L47;
        if (r12 == null) goto L44;
        r02.setItems(r12, this.items);
        return;
    L44:
        r02.notifyDataSetChanged();
        return;
    L47:
        return;
    L29:
        this.videoDividerRow = -1;
        goto L31
    L18:
        int r52 = this.type;
        if (r52 != 1) goto L21;
        r5 = LocaleController.getString(R.string.SaveToGalleryHintUser);
        goto L26
    L21:
        if (r52 != 4) goto L23;
        r5 = LocaleController.getString(R.string.SaveToGalleryHintChannels);
        goto L26
    L23:
        if (r52 != 2) goto L25;
        r5 = LocaleController.getString(R.string.SaveToGalleryHintGroup);
        goto L26
    L25:
        r5 = null;
        goto L26
    L11:
        r12 = null;
        goto L12
    L5:
        if (this.adapter == null) goto L7;
        r1 = true;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r10) {
        FrameLayout r0 = new FrameLayout(r10);
        this.fragmentView = r0;
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        if (this.dialogException == null) goto L8;
        boolean r1 = this.isNewException;
        ActionBar r4 = this.actionBar;
        if (r1 == false) goto L7;
        r4.setTitle(LocaleController.getString(R.string.NotificationsNewException));
    L15:
        RecyclerListView r12 = new RecyclerListView(r10);
        this.recyclerListView = r12;
        r12.setSections();
        this.actionBar.setAdaptiveBackground(this.recyclerListView);
        DefaultItemAnimator r13 = new DefaultItemAnimator();
        r13.setDurations(400);
        r13.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r13.setDelayAnimations(false);
        r13.setSupportsChangeAnimations(false);
        this.recyclerListView.setItemAnimator(r13);
        this.recyclerListView.setLayoutManager(new LinearLayoutManager(r10));
        RecyclerListView r102 = this.recyclerListView;
        Adapter r14 = new Adapter(this, null);
        this.adapter = r14;
        r102.setAdapter(r14);
        this.recyclerListView.setOnItemClickListener(new SaveToGallerySettingsActivity$$ExternalSyntheticLambda0(this));
        this.recyclerListView.setOnItemLongClickListener(new SaveToGallerySettingsActivity$$ExternalSyntheticLambda1(this));
        r0.addView(this.recyclerListView);
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        if (this.dialogException == null) goto L23;
        FrameLayout r103 = new FrameLayout(getContext());
        r103.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{8.0f}));
        TextView r15 = new TextView(getContext());
        r15.setTextSize(1, 14.0f);
        if (this.isNewException == false) goto L21;
        int r2 = R.string.AddException;
    L22:
        r15.setText(LocaleController.getString(r2));
        r15.setGravity(17);
        r15.setTypeface(AndroidUtilities.bold());
        r15.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        r103.addView(r15, LayoutHelper.createFrame(-2, -2, 17));
        r103.setOnClickListener(new SaveToGallerySettingsActivity$$ExternalSyntheticLambda2(this));
        r0.addView(r103, LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 16.0f, 16.0f, 16.0f));
        goto L23
    L21:
        r2 = R.string.SaveException;
    L23:
        updateRows();
        return this.fragmentView;
    L7:
        r4.setTitle(LocaleController.getString(R.string.SaveToGalleryException));
        goto L15
    L8:
        int r16 = this.type;
        if (r16 != 1) goto L11;
        this.actionBar.setTitle(LocaleController.getString(R.string.SaveToGalleryPrivate));
        goto L15
    L11:
        ActionBar r42 = this.actionBar;
        if (r16 != 2) goto L14;
        r42.setTitle(LocaleController.getString(R.string.SaveToGalleryGroups));
        goto L15
    L14:
        r42.setTitle(LocaleController.getString(R.string.SaveToGalleryChannels));
        goto L15
    }

    public SaveToGallerySettingsHelper.Settings getSettings() {
        SaveToGallerySettingsHelper.DialogException r0 = this.dialogException;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return SaveToGallerySettingsHelper.getSettings(this.type);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.type = getArguments().getInt("type");
        this.exceptionsDialogs = getUserConfig().getSaveGalleryExceptions(this.type);
        long r0 = getArguments().getLong("dialog_id");
        this.dialogId = r0;
        if (r0 == 0) goto L8;
        SaveToGallerySettingsHelper.DialogException r02 = UserConfig.getInstance(this.currentAccount).getSaveGalleryExceptions(this.type).get(this.dialogId);
        this.dialogException = r02;
        if (r02 != null) goto L8;
        this.isNewException = true;
        this.dialogException = new SaveToGallerySettingsHelper.DialogException();
        SaveToGallerySettingsHelper.Settings r03 = SaveToGallerySettingsHelper.getSettings(this.type);
        SaveToGallerySettingsHelper.DialogException r1 = this.dialogException;
        r1.savePhoto = r03.savePhoto;
        r1.saveVideo = r03.saveVideo;
        r1.limitVideo = r03.limitVideo;
        r1.dialogId = this.dialogId;
    L8:
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        updateRows();
    }
}
