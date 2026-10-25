package org.telegram.ui;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ChatsWidgetProvider;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.ContactsWidgetProvider;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.GroupCreateUserCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackgroundGradientDrawable;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.ForegroundColorSpanThemable;
import org.telegram.ui.Components.InviteMembersBottomSheet;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class EditWidgetActivity extends BaseFragment {
    private int chatsEndRow;
    private int chatsStartRow;
    private int currentWidgetId;
    private EditWidgetActivityDelegate delegate;
    private int infoRow;
    private ItemTouchHelper itemTouchHelper;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private ImageView previewImageView;
    private int previewRow;
    private int rowCount;
    private int selectChatsRow;
    private ArrayList<Long> selectedDialogs;
    private WidgetPreviewCell widgetPreviewCell;
    private int widgetType;

    /* renamed from: org.telegram.ui.EditWidgetActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ EditWidgetActivity this$0;

        public AnonymousClass1(EditWidgetActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r7) {
            if (r7 != (-1)) goto L11;
            EditWidgetActivityDelegate r72 = EditWidgetActivity.m14683$$Nest$fgetdelegate(this.this$0);
            EditWidgetActivity r6 = this.this$0;
            if (r72 != null) goto L8;
            EditWidgetActivity.m14697$$Nest$mfinishActivity(r6);
            return;
        L8:
            r6.finishFragment();
            return;
        L11:
            if (r7 == 1) goto L13;
            return;
        L13:
            if (this.this$0.getParentActivity() == null) goto L31;
            ArrayList<MessagesStorage.TopicKey> r73 = new ArrayList();
            int r1 = 0;
        L16:
            int r2 = EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0).size();
            EditWidgetActivity r3 = this.this$0;
            if (r1 >= r2) goto L19;
            r73.add(MessagesStorage.TopicKey.of(((Long) EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(r3).get(r1)).longValue(), 0));
            r1 = r1 + 1;
            goto L16
        L19:
            r3.getMessagesStorage().putWidgetDialogs(EditWidgetActivity.m14682$$Nest$fgetcurrentWidgetId(this.this$0), r73);
            SharedPreferences.Editor r74 = this.this$0.getParentActivity().getSharedPreferences("shortcut_widget", 0).edit();
            r74.putInt("account" + EditWidgetActivity.m14682$$Nest$fgetcurrentWidgetId(this.this$0), EditWidgetActivity.access$200(this.this$0));
            r74.putInt("type" + EditWidgetActivity.m14682$$Nest$fgetcurrentWidgetId(this.this$0), EditWidgetActivity.m14694$$Nest$fgetwidgetType(this.this$0));
            r74.apply();
            AppWidgetManager r75 = AppWidgetManager.getInstance(this.this$0.getParentActivity());
            int r0 = EditWidgetActivity.m14694$$Nest$fgetwidgetType(this.this$0);
            EditWidgetActivity r12 = this.this$0;
            if (r0 != 0) goto L22;
            ChatsWidgetProvider.updateWidget(r12.getParentActivity(), r75, EditWidgetActivity.m14682$$Nest$fgetcurrentWidgetId(this.this$0));
        L23:
            EditWidgetActivityDelegate r76 = EditWidgetActivity.m14683$$Nest$fgetdelegate(this.this$0);
            EditWidgetActivity r02 = this.this$0;
            if (r76 == null) goto L27;
            EditWidgetActivity.m14683$$Nest$fgetdelegate(r02).didSelectDialogs(EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0));
            return;
        L27:
            EditWidgetActivity.m14697$$Nest$mfinishActivity(r02);
            return;
        L22:
            ContactsWidgetProvider.updateWidget(r12.getParentActivity(), r75, EditWidgetActivity.m14682$$Nest$fgetcurrentWidgetId(this.this$0));
            goto L23
        }
    }

    /* renamed from: org.telegram.ui.EditWidgetActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements RecyclerListView.OnItemLongClickListenerExtended {
        private Rect rect;
        final /* synthetic */ EditWidgetActivity this$0;

        public static /* synthetic */ void $r8$lambda$M8e9mrsa1jwFi92gDTdjNH_FA6k(AnonymousClass2 r0, int r1, DialogInterface r2, int r3) {
            r0.lambda$onItemClick$0(r1, r2, r3);
        }

        public AnonymousClass2(EditWidgetActivity r1) {
            this.this$0 = r1;
            this.rect = new Rect();
        }

        private /* synthetic */ void lambda$onItemClick$0(int r1, DialogInterface r2, int r3) {
            if (r3 != 0) goto L7;
            EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0).remove(r1 - EditWidgetActivity.m14681$$Nest$fgetchatsStartRow(this.this$0));
            EditWidgetActivity.m14698$$Nest$mupdateRows(this.this$0);
            if (EditWidgetActivity.m14693$$Nest$fgetwidgetPreviewCell(this.this$0) == null) goto L8;
            EditWidgetActivity.m14693$$Nest$fgetwidgetPreviewCell(this.this$0).updateDialogs();
            return;
        L8:
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended
        public boolean onItemClick(View r3, int r4, float r5, float r6) {
            if (this.this$0.getParentActivity() != null) goto L5;
        L11:
            return false;
        L5:
            if ((r3 instanceof GroupCreateUserCell) == false) goto L11;
            ((ImageView) r3.getTag(R.id.object_tag)).getHitRect(this.rect);
            if (this.rect.contains((int) r5, (int) r6) == true) goto L11;
            AlertDialog.Builder r32 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r32.setItems(new CharSequence[]{LocaleController.getString(R.string.Delete)}, new EditWidgetActivity$2$$ExternalSyntheticLambda0(this, r4));
            this.this$0.showDialog(r32.create());
            return true;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended
        public void onLongClickRelease() {
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended
        public void onMove(float r1, float r2) {
        }
    }

    public interface EditWidgetActivityDelegate {
        void didSelectDialogs(ArrayList<Long> r1);
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ EditWidgetActivity this$0;

        /* renamed from: $r8$lambda$fgf3u4Jfmrur_FlWNYGYn-jTjQU, reason: not valid java name */
        public static /* synthetic */ boolean m14699$r8$lambda$fgf3u4Jfmrur_FlWNYGYnjTjQU(ListAdapter r0, GroupCreateUserCell r1, View r2, MotionEvent r3) {
            return r0.lambda$onCreateViewHolder$0(r1, r2, r3);
        }

        public ListAdapter(EditWidgetActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ boolean lambda$onCreateViewHolder$0(GroupCreateUserCell r1, View r2, MotionEvent r3) {
            if (r3.getAction() != 0) goto L7;
            EditWidgetActivity.m14685$$Nest$fgetitemTouchHelper(this.this$0).startDrag(EditWidgetActivity.m14687$$Nest$fgetlistView(this.this$0).getChildViewHolder(r1));
            return false;
        L7:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return EditWidgetActivity.m14690$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != EditWidgetActivity.m14689$$Nest$fgetpreviewRow(this.this$0)) goto L7;
            return 2;
        L7:
            if (r2 != EditWidgetActivity.m14691$$Nest$fgetselectChatsRow(this.this$0)) goto L11;
            return 1;
        L11:
            if (r2 != EditWidgetActivity.m14684$$Nest$fgetinfoRow(this.this$0)) goto L14;
            return 0;
        L14:
            return 3;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            int r1 = r2.getItemViewType();
            if (r1 != 1) goto L5;
        L9:
            return true;
        L5:
            if (r1 == 3) goto L9;
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            int r0 = r9.getItemViewType();
            boolean r1 = true;
            if (r0 != 0) goto L5;
            TextInfoPrivacyCell r92 = (TextInfoPrivacyCell) r9.itemView;
            if (r10 != EditWidgetActivity.m14684$$Nest$fgetinfoRow(this.this$0)) goto L42;
            SpannableStringBuilder r102 = new SpannableStringBuilder();
            if (EditWidgetActivity.m14694$$Nest$fgetwidgetType(this.this$0) != 0) goto L35;
            r102.append(LocaleController.getString(R.string.EditWidgetChatsInfo));
        L38:
            if (SharedConfig.passcodeHash.length() <= 0) goto L40;
            r102.append("\n\n").append(AndroidUtilities.replaceTags(LocaleController.getString(R.string.WidgetPasscode2)));
        L40:
            r92.setText(r102);
            return;
        L35:
            if (EditWidgetActivity.m14694$$Nest$fgetwidgetType(this.this$0) != 1) goto L38;
            r102.append(LocaleController.getString(R.string.EditWidgetContactsInfo));
            goto L38
        L42:
            return;
        L5:
            if (r0 != 1) goto L7;
            TextCell r93 = (TextCell) r9.itemView;
            r93.setColors(-1, Theme.key_windowBackgroundWhiteBlueText4);
            Drawable r103 = this.mContext.getResources().getDrawable(R.drawable.poll_add_circle);
            Drawable r3 = this.mContext.getResources().getDrawable(R.drawable.poll_add_plus);
            int r5 = Theme.getColor(Theme.key_switchTrackChecked);
            PorterDuff.Mode r6 = PorterDuff.Mode.MULTIPLY;
            r103.setColorFilter(new PorterDuffColorFilter(r5, r6));
            r3.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_checkboxCheck), r6));
            CombinedDrawable r4 = new CombinedDrawable(r103, r3);
            String r104 = LocaleController.getString(R.string.SelectChats);
            if (EditWidgetActivity.m14681$$Nest$fgetchatsStartRow(this.this$0) != (-1)) goto L27;
            r1 = false;
        L27:
            r93.setTextAndIcon(r104, r4, r1);
            r93.getImageView().setPadding(0, AndroidUtilities.dp(7.0f), 0, 0);
            return;
        L7:
            if (r0 != 3) goto L43;
            GroupCreateUserCell r94 = (GroupCreateUserCell) r9.itemView;
            Long r02 = (Long) EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0).get(r10 - EditWidgetActivity.m14681$$Nest$fgetchatsStartRow(this.this$0));
            long r32 = r02.longValue();
            boolean r52 = DialogObject.isUserDialog(r32);
            EditWidgetActivity r62 = this.this$0;
            if (r52 == false) goto L17;
            TLRPC.User r03 = r62.getMessagesController().getUser(r02);
            if (r10 != (EditWidgetActivity.m14680$$Nest$fgetchatsEndRow(this.this$0) - 1)) goto L15;
            r1 = false;
        L15:
            r94.setObject(r03, null, null, r1);
            return;
        L17:
            TLRPC.Chat r04 = r62.getMessagesController().getChat(Long.valueOf(-r32));
            if (r10 != (EditWidgetActivity.m14680$$Nest$fgetchatsEndRow(this.this$0) - 1)) goto L21;
            r1 = false;
        L21:
            r94.setObject(r04, null, null, r1);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r9, int r10) {
            if (r10 != 0) goto L4;
            FrameLayout r92 = new TextInfoPrivacyCell(this.mContext);
            r92.setBackgroundDrawable(Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
            FrameLayout r93 = r92;
        L16:
            return new RecyclerListView.Holder(r93);
        L4:
            if (r10 != 1) goto L6;
            FrameLayout r94 = new TextCell(this.mContext);
            r94.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r93 = r94;
            goto L16
        L6:
            if (r10 == 2) goto L12;
            GroupCreateUserCell r95 = new GroupCreateUserCell(this.mContext, 0, 0, false);
            ImageView r102 = new ImageView(this.mContext);
            r102.setImageResource(R.drawable.list_reorder);
            r102.setScaleType(ImageView.ScaleType.CENTER);
            r95.setTag(R.id.object_tag, r102);
            if (LocaleController.isRTL == false) goto L10;
            int r0 = 3;
        L11:
            r95.addView(r102, LayoutHelper.createFrame(40, -1.0f, r0 | 16, 10.0f, 0.0f, 10.0f, 0.0f));
            r102.setOnTouchListener(new EditWidgetActivity$ListAdapter$$ExternalSyntheticLambda0(this, r95));
            r102.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_pinnedIcon), PorterDuff.Mode.MULTIPLY));
            r93 = r95;
            goto L16
        L10:
            r0 = 5;
            goto L11
        L12:
            EditWidgetActivity r96 = this.this$0;
            WidgetPreviewCell r103 = new WidgetPreviewCell(this.this$0, this.mContext);
            EditWidgetActivity.m14696$$Nest$fputwidgetPreviewCell(r96, r103);
            r93 = r103;
            goto L16
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r2) {
            int r1 = r2.getItemViewType();
            if (r1 != 3) goto L5;
        L8:
            r2.itemView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            return;
        L5:
            if (r1 == 1) goto L8;
        }

        public boolean swapElements(int r6, int r7) {
            int r0 = r6 - EditWidgetActivity.m14681$$Nest$fgetchatsStartRow(this.this$0);
            int r1 = r7 - EditWidgetActivity.m14681$$Nest$fgetchatsStartRow(this.this$0);
            int r2 = EditWidgetActivity.m14680$$Nest$fgetchatsEndRow(this.this$0) - EditWidgetActivity.m14681$$Nest$fgetchatsStartRow(this.this$0);
            if (r0 < 0) goto L10;
            if (r1 < 0) goto L12;
            if (r0 >= r2) goto L13;
            if (r1 >= r2) goto L14;
            Long r22 = (Long) EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0).get(r0);
            Long r3 = (Long) EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0).get(r1);
            EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0).set(r0, r3);
            EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0).set(r1, r22);
            notifyItemMoved(r6, r7);
            return true;
        L14:
            return false;
        L13:
            return false;
        L12:
            return false;
        L10:
            return false;
        }
    }

    public class TouchHelperCallback extends ItemTouchHelper.Callback {
        private boolean moved;
        final /* synthetic */ EditWidgetActivity this$0;

        public TouchHelperCallback(EditWidgetActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
            super.clearView(r1, r2);
            r2.itemView.setPressed(false);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r1, RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() == 3) goto L7;
            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        L7:
            return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onChildDraw(Canvas r1, RecyclerView r2, RecyclerView.ViewHolder r3, float r4, float r5, int r6, boolean r7) {
            super.onChildDraw(r1, r2, r3, r4, r5, r6, r7);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r5, RecyclerView.ViewHolder r6, RecyclerView.ViewHolder r7) {
            boolean r1 = false;
            if (r6.getItemViewType() == r7.getItemViewType()) goto L5;
            return false;
        L5:
            int r52 = r6.getAdapterPosition();
            int r0 = r7.getAdapterPosition();
            if (EditWidgetActivity.m14686$$Nest$fgetlistAdapter(this.this$0).swapElements(r52, r0) == false) goto L15;
            GroupCreateUserCell r62 = (GroupCreateUserCell) r6.itemView;
            if (r0 == (EditWidgetActivity.m14680$$Nest$fgetchatsEndRow(this.this$0) - 1)) goto L10;
            boolean r02 = true;
        L11:
            r62.setDrawDivider(r02);
            GroupCreateUserCell r63 = (GroupCreateUserCell) r7.itemView;
            if (r52 == (EditWidgetActivity.m14680$$Nest$fgetchatsEndRow(this.this$0) - 1)) goto L14;
            r1 = true;
        L14:
            r63.setDrawDivider(r1);
            this.moved = true;
            goto L15
        L10:
            r02 = false;
        L15:
            return true;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r3, int r4) {
            if (r4 == 0) goto L6;
            EditWidgetActivity.m14687$$Nest$fgetlistView(this.this$0).cancelClickRunnables(false);
            r3.itemView.setPressed(true);
        L11:
            super.onSelectedChanged(r3, r4);
            return;
        L6:
            if (this.moved == false) goto L11;
            if (EditWidgetActivity.m14693$$Nest$fgetwidgetPreviewCell(this.this$0) == null) goto L10;
            EditWidgetActivity.m14693$$Nest$fgetwidgetPreviewCell(this.this$0).updateDialogs();
        L10:
            this.moved = false;
            goto L11
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
        }
    }

    public class WidgetPreviewCell extends FrameLayout {
        private Drawable backgroundDrawable;
        private BackgroundGradientDrawable.Disposable backgroundGradientDisposable;
        private RectF bitmapRect;
        private ViewGroup[] cells;
        private Drawable oldBackgroundDrawable;
        private BackgroundGradientDrawable.Disposable oldBackgroundGradientDisposable;
        private Paint roundPaint;
        private Drawable shadowDrawable;
        final /* synthetic */ EditWidgetActivity this$0;

        public WidgetPreviewCell(EditWidgetActivity r19, Context r20) {
            this.this$0 = r19;
            super(r20);
            this.roundPaint = new Paint(1);
            this.bitmapRect = new RectF();
            this.cells = new ViewGroup[2];
            int r5 = 0;
            setWillNotDraw(false);
            setPadding(0, AndroidUtilities.dp(24.0f), 0, AndroidUtilities.dp(24.0f));
            LinearLayout r6 = new LinearLayout(r20);
            r6.setOrientation(1);
            addView(r6, LayoutHelper.createFrame(-2, -2, 17));
            ChatActionCell r9 = new ChatActionCell(r20);
            r9.setCustomText(LocaleController.getString(R.string.WidgetPreview));
            r6.addView(r9, LayoutHelper.createLinear(-2, -2, 17, 0, 0, 0, 4));
            LinearLayout r92 = new LinearLayout(r20);
            r92.setOrientation(1);
            r92.setBackgroundResource(R.drawable.widget_bg);
            r6.addView(r92, LayoutHelper.createLinear(-2, -2, 17, 10, 0, 10, 0));
            EditWidgetActivity.m14695$$Nest$fputpreviewImageView(r19, new ImageView(r20));
            if (EditWidgetActivity.m14694$$Nest$fgetwidgetType(r19) != 0) goto L8;
        L4:
            if (r5 >= 2) goto L6;
            this.cells[r5] = (ViewGroup) r19.getParentActivity().getLayoutInflater().inflate(R.layout.shortcut_widget_item, null);
            r92.addView(this.cells[r5], LayoutHelper.createLinear(-1, -2));
            r5 = r5 + 1;
            goto L4
        L6:
            r92.addView(EditWidgetActivity.m14688$$Nest$fgetpreviewImageView(r19), LayoutHelper.createLinear(Opcodes.MUL_INT_LIT8, 160, 17));
            EditWidgetActivity.m14688$$Nest$fgetpreviewImageView(r19).setImageResource(R.drawable.chats_widget_preview);
        L12:
            updateDialogs();
            this.shadowDrawable = Theme.getThemedDrawableByKey(r20, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow);
            return;
        L8:
            if (EditWidgetActivity.m14694$$Nest$fgetwidgetType(r19) != 1) goto L12;
        L9:
            if (r5 >= 2) goto L11;
            this.cells[r5] = (ViewGroup) r19.getParentActivity().getLayoutInflater().inflate(R.layout.contacts_widget_item, null);
            r92.addView(this.cells[r5], LayoutHelper.createLinear(160, -2));
            r5 = r5 + 1;
            goto L9
        L11:
            r92.addView(EditWidgetActivity.m14688$$Nest$fgetpreviewImageView(r19), LayoutHelper.createLinear(160, 160, 17));
            EditWidgetActivity.m14688$$Nest$fgetpreviewImageView(r19).setImageResource(R.drawable.contacts_widget_preview);
            goto L12
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchSetPressed(boolean r1) {
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return false;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            BackgroundGradientDrawable.Disposable r0 = this.backgroundGradientDisposable;
            if (r0 == null) goto L5;
            r0.dispose();
            this.backgroundGradientDisposable = null;
        L5:
            BackgroundGradientDrawable.Disposable r02 = this.oldBackgroundGradientDisposable;
            if (r02 == null) goto L9;
            r02.dispose();
            this.oldBackgroundGradientDisposable = null;
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r12) {
            Drawable r0 = Theme.getCachedWallpaperNonBlocking();
            if (r0 == this.backgroundDrawable) goto L12;
            if (r0 == null) goto L12;
            if (Theme.isAnimatingColor() == false) goto L8;
            this.oldBackgroundDrawable = this.backgroundDrawable;
            this.oldBackgroundGradientDisposable = this.backgroundGradientDisposable;
        L11:
            this.backgroundDrawable = r0;
            goto L12
        L8:
            BackgroundGradientDrawable.Disposable r1 = this.backgroundGradientDisposable;
            if (r1 == null) goto L11;
            r1.dispose();
            this.backgroundGradientDisposable = null;
        L12:
            float r02 = EditWidgetActivity.access$000(this.this$0).getThemeAnimationValue();
            int r3 = 0;
        L14:
            if (r3 >= 2) goto L56;
            if (r3 != 0) goto L17;
            Drawable r5 = this.oldBackgroundDrawable;
        L18:
            if (r5 == null) goto L55;
            if (r3 == 1) goto L23;
        L27:
            r5.setAlpha(255);
        L29:
            if ((r5 instanceof ColorDrawable) == false) goto L31;
        L42:
            r5.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            if ((r5 instanceof BackgroundGradientDrawable) == false) goto L45;
            this.backgroundGradientDisposable = ((BackgroundGradientDrawable) r5).drawExactBoundsSize(r12, this);
        L46:
            if (r3 != 0) goto L55;
            if (this.oldBackgroundDrawable == null) goto L55;
            if (r02 < 1.0f) goto L55;
            BackgroundGradientDrawable.Disposable r4 = this.oldBackgroundGradientDisposable;
            if (r4 == null) goto L54;
            r4.dispose();
            this.oldBackgroundGradientDisposable = null;
        L54:
            this.oldBackgroundDrawable = null;
            invalidate();
            goto L55
        L45:
            r5.draw(r12);
            goto L46
        L31:
            if ((r5 instanceof GradientDrawable) == true) goto L42;
            if ((r5 instanceof MotionBackgroundDrawable) == true) goto L42;
            if ((r5 instanceof BitmapDrawable) == false) goto L46;
            if (((BitmapDrawable) r5).getTileModeX() != Shader.TileMode.REPEAT) goto L40;
            r12.save();
            float r42 = 2.0f / AndroidUtilities.density;
            r12.scale(r42, r42);
            r5.setBounds(0, 0, (int) Math.ceil(getMeasuredWidth() / r42), (int) Math.ceil(getMeasuredHeight() / r42));
        L41:
            r5.draw(r12);
            r12.restore();
            goto L46
        L40:
            int r6 = getMeasuredHeight();
            float r7 = Math.max(getMeasuredWidth() / r5.getIntrinsicWidth(), r6 / r5.getIntrinsicHeight());
            int r8 = (int) Math.ceil(r5.getIntrinsicWidth() * r7);
            int r72 = (int) Math.ceil(r5.getIntrinsicHeight() * r7);
            int r9 = (getMeasuredWidth() - r8) / 2;
            int r62 = (r6 - r72) / 2;
            r12.save();
            r12.clipRect(0, 0, r8, getMeasuredHeight());
            r5.setBounds(r9, r62, r8 + r9, r72 + r62);
            goto L41
        L23:
            if (this.oldBackgroundDrawable == null) goto L27;
            if (EditWidgetActivity.access$100(this.this$0) == null) goto L27;
            r5.setAlpha((int) (255.0f * r02));
        L55:
            r3 = r3 + 1;
            goto L14
        L17:
            r5 = this.backgroundDrawable;
            goto L18
        L56:
            this.shadowDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            this.shadowDrawable.draw(r12);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r1) {
            return false;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(264.0f), TLObject.FLAG_30));
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r1) {
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r11v0 */
        /* JADX WARN: Type inference failed for: r11v5, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r11v7 */
        public void updateDialogs() {
            int r3 = 12;
            int r6 = 8;
            int r9 = 2;
            ?? r11 = 1;
            if (EditWidgetActivity.m14694$$Nest$fgetwidgetType(this.this$0) != 0) goto L262;
            int r13 = 0;
        L5:
            if (r13 >= r9) goto L260;
            boolean r0 = EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0).isEmpty();
            EditWidgetActivity r14 = this.this$0;
            if (r0 == false) goto L14;
            if (r13 >= r14.getMessagesController().dialogsServerOnly.size()) goto L11;
            TLRPC.Dialog r02 = this.this$0.getMessagesController().dialogsServerOnly.get(r13);
        L12:
            TLRPC.Dialog r142 = r02;
        L19:
            ViewGroup[] r03 = this.cells;
            if (r142 != null) goto L22;
            r03[r13].setVisibility(r6);
            int r18 = r9;
            boolean r24 = r11;
        L259:
            r13 = r13 + 1;
            r9 = r18;
            r11 = r24;
            r3 = 12;
            r6 = 8;
            goto L5
        L22:
            r03[r13].setVisibility(0);
            boolean r04 = DialogObject.isUserDialog(r142.id);
            EditWidgetActivity r62 = this.this$0;
            if (r04 == false) goto L53;
            int r8 = r9;
            TLRPC.User r05 = r62.getMessagesController().getUser(Long.valueOf(r142.id));
            if (r05 != null) goto L27;
            float r10 = 48.0f;
            String r63 = _UrlKt.FRAGMENT_ENCODE_SET;
        L51:
            int r5 = r8;
            TLRPC.FileLocation r4 = null;
        L49:
            TLRPC.Chat r82 = null;
        L68:
            r18 = r5;
            ((TextView) this.cells[r13].findViewById(R.id.shortcut_widget_item_text)).setText(r63);
            if (r4 != null) goto L430;
            Bitmap r42 = null;
        L74:
            int r52 = AndroidUtilities.dp(r10);     // Catch: Throwable -> L71
            Bitmap r64 = Bitmap.createBitmap(r52, r52, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L71
            r64.eraseColor(0);     // Catch: Throwable -> L71
            Canvas r92 = new Canvas(r64);     // Catch: Throwable -> L71
            if (r42 != null) goto L96;
            if (r05 == null) goto L83;
            AvatarDrawable r43 = new AvatarDrawable(r05);     // Catch: Throwable -> L71
            if (UserObject.isReplyUser(r05) == false) goto L80;
            r43.setAvatarType(r3);     // Catch: Throwable -> L71
            AvatarDrawable r44 = r43;
        L84:
            float r06 = r52;
            if (r82 == null) goto L93;
            if (r82.forum == false) goto L93;
            boolean r102 = r11;
        L94:
            r44.setRoundRadius(ExteraConfig.getAvatarCorners(r06, r11, r102));     // Catch: Throwable -> L90
            r44.setBounds(0, 0, r52, r52);     // Catch: Throwable -> L90
            r44.draw(r92);     // Catch: Throwable -> L90
        L106:
            r92.setBitmap(null);     // Catch: Throwable -> L90
            ((ImageView) this.cells[r13].findViewById(R.id.shortcut_widget_item_avatar)).setImageBitmap(r64);     // Catch: Throwable -> L90
        L109:
            ArrayList<MessageObject> r07 = this.this$0.getMessagesController().dialogMessage.get(r142.id);
            if (r07 != null) goto L112;
        L114:
            MessageObject r45 = null;
        L115:
            if (r45 == null) goto L247;
            long r53 = r45.getFromChatId();
            EditWidgetActivity r93 = this.this$0;
            if (r53 <= 0) goto L119;
            TLRPC.User r54 = r93.getMessagesController().getUser(Long.valueOf(r53));
            TLRPC.Chat r08 = null;
        L120:
            int r65 = getContext().getResources().getColor(R.color.widget_text);
            if ((r45.messageOwner instanceof TLRPC.TL_messageService) == true) goto L123;
            String r15 = "🖼 ";
            if (r82 != null) goto L134;
        L141:
            r24 = r11;
            TLRPC.MessageMedia r09 = r45.messageOwner.media;
            if ((r09 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L208;
            if ((r09.photo instanceof TLRPC.TL_photoEmpty) == false) goto L208;
            if (r09.ttl_seconds == 0) goto L208;
            CharSequence r7 = LocaleController.getString(R.string.AttachPhotoExpired);
        L246:
            ((TextView) this.cells[r13].findViewById(R.id.shortcut_widget_item_time)).setText(LocaleController.stringForMessageListDate(r45.messageOwner.date));
            ((TextView) this.cells[r13].findViewById(R.id.shortcut_widget_item_message)).setText(r7.toString());
            ((TextView) this.cells[r13].findViewById(R.id.shortcut_widget_item_message)).setTextColor(r65);
        L252:
            int r010 = r142.unread_count;
            ViewGroup[] r32 = this.cells;
            if (r010 <= 0) goto L258;
            ((TextView) r32[r13].findViewById(R.id.shortcut_widget_item_badge)).setText(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r142.unread_count)}));
            this.cells[r13].findViewById(R.id.shortcut_widget_item_badge).setVisibility(0);
            boolean r011 = this.this$0.getMessagesController().isDialogMuted(r142.id, 0);
            ViewGroup[] r33 = this.cells;
            if (r011 == false) goto L257;
            r33[r13].findViewById(R.id.shortcut_widget_item_badge).setBackgroundResource(R.drawable.widget_counter_muted);
            goto L259
        L257:
            r33[r13].findViewById(R.id.shortcut_widget_item_badge).setBackgroundResource(R.drawable.widget_counter);
            goto L259
        L258:
            r32[r13].findViewById(R.id.shortcut_widget_item_badge).setVisibility(8);
        L208:
            if ((r09 instanceof TLRPC.TL_messageMediaDocument) == false) goto L215;
            if ((r09.document instanceof TLRPC.TL_documentEmpty) == false) goto L215;
            if (r09.ttl_seconds == 0) goto L215;
            r7 = LocaleController.getString(R.string.AttachVideoExpired);
        L215:
            if (r45.caption == null) goto L231;
            if (r45.isVideo() == false) goto L220;
            String r103 = "📹 ";
        L229:
            r7 = r103 + r45.caption;
            goto L246
        L220:
            if (r45.isVoice() == false) goto L223;
            r103 = "🎤 ";
            goto L229
        L223:
            if (r45.isMusic() == false) goto L226;
            r103 = "🎧 ";
            goto L229
        L226:
            if (r45.isPhoto() == false) goto L228;
            r103 = "🖼 ";
            goto L229
        L228:
            r103 = "📎 ";
            goto L229
        L231:
            if ((r09 instanceof TLRPC.TL_messageMediaPoll) == false) goto L235;
            CharSequence r012 = "📊 " + ((TLRPC.TL_messageMediaPoll) r09).poll.question.text;
        L233:
            CharSequence r72 = r012;
            r7 = r72;
            if (r45.messageOwner.media == null) goto L246;
            r7 = r72;
            if (r45.isMediaEmpty() == true) goto L246;
            r65 = getContext().getResources().getColor(R.color.widget_action_text);
            r7 = r72;
            goto L246
        L235:
            if ((r09 instanceof TLRPC.TL_messageMediaGame) == false) goto L238;
            r012 = "🎮 " + r45.messageOwner.media.game.title;
            goto L233
        L238:
            if (r45.type != 14) goto L240;
            r012 = String.format("🎧 %s - %s", new Object[]{r45.getMusicAuthor(), r45.getMusicTitle()});
            goto L233
        L240:
            r012 = r45.messageText;
            AndroidUtilities.highlightText(r012, r45.highlightedWords, null);
            goto L233
        L134:
            if (r82.id <= 0) goto L141;
            if (r08 != null) goto L141;
            if (ChatObject.isChannel(r82) == false) goto L143;
            if (ChatObject.isMegagroup(r82) == false) goto L141;
        L143:
            if (r45.isOutOwner() == false) goto L146;
            String r013 = LocaleController.getString(R.string.FromYou);
        L145:
            String r55 = r013;
            CharSequence r014 = r45.caption;
            r24 = r11;
            if (r014 == null) goto L170;
            String r015 = r014.toString();
            if (r015.length() <= 150) goto L155;
            r015 = r015.substring(0, 150);
        L155:
            if (r45.isVideo() == false) goto L158;
            r15 = "📹 ";
        L167:
            SpannableStringBuilder r016 = SpannableStringBuilder.valueOf(String.format("%2$s: \u2068%1$s\u2069", new Object[]{r15 + r015.replace('\n', ' '), r55}));
        L168:
            SpannableStringBuilder r73 = r016;
        L442:
            r73.setSpan(new ForegroundColorSpanThemable(Theme.key_chats_nameMessage), 0, r55.length() + 1, 33);     // Catch: Exception -> L198
            r7 = r73;
        L198:
            e = move-exception;
            FileLog.e(e);
            r7 = r73;
            goto L246
        L158:
            if (r45.isVoice() == false) goto L161;
            r15 = "🎤 ";
            goto L167
        L161:
            if (r45.isMusic() == false) goto L164;
            r15 = "🎧 ";
            goto L167
        L164:
            if (r45.isPhoto() == true) goto L167;
            r15 = "📎 ";
            goto L167
        L170:
            if (r45.messageOwner.media != null) goto L172;
        L189:
            String r017 = r45.messageOwner.message;
            if (r017 != null) goto L192;
            r016 = SpannableStringBuilder.valueOf(_UrlKt.FRAGMENT_ENCODE_SET);
            goto L168
        L192:
            if (r017.length() <= 150) goto L194;
            r017 = r017.substring(0, 150);
        L194:
            r016 = SpannableStringBuilder.valueOf(String.format("%2$s: \u2068%1$s\u2069", new Object[]{r017.replace('\n', ' ').trim(), r55}));
            goto L168
        L172:
            if (r45.isMediaEmpty() == true) goto L189;
            r65 = getContext().getResources().getColor(R.color.widget_action_text);
            TLRPC.MessageMedia r018 = r45.messageOwner.media;
            if ((r018 instanceof TLRPC.TL_messageMediaPoll) == false) goto L177;
            String r019 = String.format("📊 \u2068%s\u2069", new Object[]{((TLRPC.TL_messageMediaPoll) r018).poll.question.text});
        L183:
            SpannableStringBuilder r34 = SpannableStringBuilder.valueOf(String.format("%2$s: \u2068%1$s\u2069", new Object[]{r019.replace('\n', ' '), r55}));
            r34.setSpan(new ForegroundColorSpanThemable(Theme.key_chats_attachMessage), r55.length() + 2, r34.length(), 33);     // Catch: Exception -> L186
        L188:
            r73 = r34;
        L186:
            e = move-exception;
            FileLog.e(e);
            goto L188
        L177:
            if ((r018 instanceof TLRPC.TL_messageMediaGame) == false) goto L180;
            r019 = String.format("🎮 \u2068%s\u2069", new Object[]{r018.game.title});
            goto L183
        L180:
            if (r45.type != 14) goto L182;
            r019 = String.format("🎧 \u2068%s - %s\u2069", new Object[]{r45.getMusicAuthor(), r45.getMusicTitle()});
            goto L183
        L182:
            r019 = r45.messageText.toString();
            goto L183
        L146:
            if (r54 == null) goto L148;
            r013 = UserObject.getFirstName(r54).replace("\n", _UrlKt.FRAGMENT_ENCODE_SET);
            goto L145
        L148:
            r013 = "DELETED";
            goto L145
        L123:
            if (ChatObject.isChannel(r82) == false) goto L129;
            TLRPC.MessageAction r020 = r45.messageOwner.action;
            boolean r56 = r020 instanceof TLRPC.TL_messageActionHistoryClear;
            CharSequence r74 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r56 == true) goto L130;
            boolean r021 = r020 instanceof TLRPC.TL_messageActionChannelMigrateFrom;
            r74 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r021 == false) goto L129;
        L130:
            r65 = getContext().getResources().getColor(R.color.widget_action_text);
            r24 = r11;
            r7 = r74;
        L129:
            r74 = r45.messageText;
            goto L130
        L119:
            r08 = r93.getMessagesController().getChat(Long.valueOf(-r53));
            r54 = null;
            goto L120
        L247:
            r24 = r11;
            int r022 = r142.last_message_date;
            ViewGroup[] r35 = this.cells;
            if (r022 == 0) goto L250;
            ((TextView) r35[r13].findViewById(R.id.shortcut_widget_item_time)).setText(LocaleController.stringForMessageListDate(r142.last_message_date));
        L251:
            ((TextView) this.cells[r13].findViewById(R.id.shortcut_widget_item_message)).setText(_UrlKt.FRAGMENT_ENCODE_SET);
            goto L252
        L250:
            ((TextView) r35[r13].findViewById(R.id.shortcut_widget_item_time)).setText(_UrlKt.FRAGMENT_ENCODE_SET);
            goto L251
        L112:
            if (r07.size() <= 0) goto L114;
            r45 = r07.get(0);
        L90:
            th = th;
        L108:
            FileLog.e(th);
        L93:
            r102 = false;
            goto L94
        L80:
            r44 = r43;
            if (UserObject.isUserSelf(r05) == false) goto L84;
            r43.setAvatarType(r11);     // Catch: Throwable -> L71
            r44 = r43;
            goto L84
        L83:
            r44 = new AvatarDrawable(r82);     // Catch: Throwable -> L71
            goto L84
        L96:
            Shader.TileMode r104 = Shader.TileMode.CLAMP;     // Catch: Throwable -> L90
            BitmapShader r023 = new BitmapShader(r42, r104, r104);     // Catch: Throwable -> L90
            if (this.roundPaint != null) goto L99;
            this.roundPaint = new Paint(r11);     // Catch: Throwable -> L90
            this.bitmapRect = new RectF();     // Catch: Throwable -> L90
        L99:
            float r57 = r52 / r42.getWidth();     // Catch: Throwable -> L90
            r92.save();     // Catch: Throwable -> L90
            r92.scale(r57, r57);     // Catch: Throwable -> L90
            float r58 = r42.getWidth();     // Catch: Throwable -> L90
            if (r82 != null) goto L102;
        L104:
            boolean r105 = false;
        L105:
            float r59 = ExteraConfig.getAvatarCorners(r58, r11, r105);     // Catch: Throwable -> L90
            this.roundPaint.setShader(r023);     // Catch: Throwable -> L90
            this.bitmapRect.set(0.0f, 0.0f, r42.getWidth(), r42.getHeight());     // Catch: Throwable -> L90
            r92.drawRoundRect(this.bitmapRect, r59, r59, this.roundPaint);     // Catch: Throwable -> L90
            r92.restore();     // Catch: Throwable -> L90
            goto L106
        L102:
            if (r82.forum == false) goto L104;
            r105 = r11;
        L71:
            th = th;
            goto L108
        L430:
            r42 = BitmapFactory.decodeFile(this.this$0.getFileLoader().getPathToAttach(r4, r11).toString());     // Catch: Throwable -> L71
            goto L74
        L27:
            if (UserObject.isUserSelf(r05) == false) goto L30;
            r63 = LocaleController.getString(R.string.SavedMessages);
        L37:
            if (UserObject.isReplyUser(r05) == false) goto L39;
        L50:
            r10 = 48.0f;
            goto L51
        L39:
            if (UserObject.isUserSelf(r05) == true) goto L50;
            TLRPC.UserProfilePhoto r94 = r05.photo;
            if (r94 == null) goto L50;
            TLRPC.FileLocation r95 = r94.photo_small;
            if (r95 == null) goto L50;
            r10 = 48.0f;
            if (r95.volume_id == 0) goto L51;
            if (r95.local_id == 0) goto L51;
            r5 = r8;
            r4 = r95;
            goto L49
        L30:
            if (UserObject.isReplyUser(r05) == false) goto L33;
            r63 = LocaleController.getString(R.string.RepliesTitle);
            goto L37
        L33:
            if (UserObject.isDeleted(r05) == false) goto L35;
            r63 = LocaleController.getString(R.string.HiddenName);
            goto L37
        L35:
            r63 = ContactsController.formatName(r05.first_name, r05.last_name);
            goto L37
        L53:
            int r83 = r9;
            r10 = 48.0f;
            TLRPC.Chat r024 = r62.getMessagesController().getChat(Long.valueOf(-r142.id));
            if (r024 == null) goto L67;
            r63 = r024.title;
            TLRPC.ChatPhoto r46 = r024.photo;
            if (r46 == null) goto L64;
            r4 = r46.photo_small;
            if (r4 == null) goto L64;
            r5 = r83;
            if (r4.volume_id != 0) goto L62;
        L65:
            r82 = r024;
        L66:
            r05 = null;
            r4 = null;
            goto L68
        L62:
            if (r4.local_id == 0) goto L65;
            r82 = r024;
            r05 = null;
        L64:
            r5 = r83;
            goto L65
        L67:
            r5 = r83;
            r82 = r024;
            r63 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L66
        L11:
            r02 = null;
            goto L12
        L14:
            if (r13 >= EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(r14).size()) goto L18;
            r02 = this.this$0.getMessagesController().dialogs_dict.get(((Long) EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0).get(r13)).longValue());
            if (r02 != null) goto L12;
            r02 = new TLRPC.TL_dialog();
            r02.id = ((Long) EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0).get(r13)).longValue();
            goto L12
        L18:
            r142 = null;
            goto L19
        L260:
            char r242 = r11;
            this.cells[0].findViewById(R.id.shortcut_widget_item_divider).setVisibility(this.cells[r242].getVisibility());
            this.cells[r242].findViewById(R.id.shortcut_widget_item_divider).setVisibility(8);
        L423:
            int r025 = this.cells[0].getVisibility();
            EditWidgetActivity r1 = this.this$0;
            if (r025 != 0) goto L426;
            EditWidgetActivity.m14688$$Nest$fgetpreviewImageView(r1).setVisibility(8);
            return;
        L426:
            EditWidgetActivity.m14688$$Nest$fgetpreviewImageView(r1).setVisibility(0);
            return;
        L262:
            if (EditWidgetActivity.m14694$$Nest$fgetwidgetType(this.this$0) != 1) goto L423;
            int r36 = 0;
            int r84 = 2;
        L264:
            if (r36 >= r84) goto L423;
            int r47 = 0;
        L266:
            if (r47 >= r84) goto L422;
            int r026 = (r36 * 2) + r47;
            boolean r510 = EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0).isEmpty();
            EditWidgetActivity r66 = this.this$0;
            if (r510 == false) goto L277;
            if (r026 >= r66.getMediaDataController().hints.size()) goto L275;
            long r511 = this.this$0.getMediaDataController().hints.get(r026).peer.user_id;
            TLRPC.Dialog r75 = this.this$0.getMessagesController().dialogs_dict.get(r511);
            if (r75 != null) goto L274;
            r75 = new TLRPC.TL_dialog();
            r75.id = r511;
        L274:
            TLRPC.Dialog r512 = r75;
        L281:
            ViewGroup[] r67 = this.cells;
            if (r512 != null) goto L294;
            ViewGroup r513 = r67[r36];
            if (r47 != 0) goto L286;
            int r68 = R.id.contacts_widget_item1;
        L287:
            r513.findViewById(r68).setVisibility(4);
            if (r026 != 0) goto L290;
        L293:
            this.cells[r36].setVisibility(8);
        L421:
            r47 = r47 + 1;
            r84 = 2;
            goto L266
        L290:
            if (r026 != 2) goto L421;
        L286:
            r68 = R.id.contacts_widget_item2;
            goto L287
        L294:
            ViewGroup r69 = r67[r36];
            if (r47 != 0) goto L297;
            int r76 = R.id.contacts_widget_item1;
        L298:
            r69.findViewById(r76).setVisibility(0);
            if (r026 == 0) goto L301;
            if (r026 == 2) goto L301;
        L302:
            boolean r027 = DialogObject.isUserDialog(r512.id);
            EditWidgetActivity r610 = this.this$0;
            if (r027 == false) goto L330;
            TLRPC.User r028 = r610.getMessagesController().getUser(Long.valueOf(r512.id));
            if (UserObject.isUserSelf(r028) == false) goto L308;
            String r611 = LocaleController.getString(R.string.SavedMessages);
        L315:
            if (UserObject.isReplyUser(r028) == false) goto L317;
        L329:
            String r96 = r611;
            TLRPC.Chat r612 = null;
            TLRPC.FileLocation r77 = null;
        L342:
            ViewGroup r106 = this.cells[r36];
            if (r47 != 0) goto L345;
            int r112 = R.id.contacts_widget_item_text1;
        L346:
            ((TextView) r106.findViewById(r112)).setText(r96);
            if (r77 != null) goto L436;
            Bitmap r78 = null;
        L353:
            int r97 = AndroidUtilities.dp(48.0f);     // Catch: Throwable -> L349
            Bitmap r107 = Bitmap.createBitmap(r97, r97, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L349
            r107.eraseColor(0);     // Catch: Throwable -> L349
            Canvas r113 = new Canvas(r107);     // Catch: Throwable -> L349
            if (r78 != null) goto L377;
            if (r028 != null) goto L440;
            AvatarDrawable r79 = new AvatarDrawable(r612);     // Catch: Throwable -> L360
        L368:
            r79.setBounds(0, 0, r97, r97);     // Catch: Throwable -> L360
            float r029 = r97;
            if (r612 != null) goto L371;
        L374:
            boolean r613 = false;
        L375:
            r79.setRoundRadius(ExteraConfig.getAvatarCorners(r029, true, r613));     // Catch: Throwable -> L360
            r79.draw(r113);     // Catch: Throwable -> L360
        L432:
            r113.setBitmap(null);     // Catch: Throwable -> L391
            ViewGroup r030 = this.cells[r36];     // Catch: Throwable -> L391
            if (r47 != 0) goto L393;
            int r710 = R.id.contacts_widget_item_avatar1;     // Catch: Throwable -> L391
        L394:
            ((ImageView) r030.findViewById(r710)).setImageBitmap(r107);     // Catch: Throwable -> L391
        L401:
            int r031 = r512.unread_count;
            if (r031 > 0) goto L404;
            ViewGroup r032 = this.cells[r36];
            if (r47 != 0) goto L419;
            int r514 = R.id.contacts_widget_item_badge_bg1;
        L420:
            r032.findViewById(r514).setVisibility(8);
            goto L421
        L419:
            r514 = R.id.contacts_widget_item_badge_bg2;
            goto L420
        L404:
            if (r031 <= 99) goto L406;
            String r033 = String.format("%d+", new Object[]{99});
        L407:
            ViewGroup r515 = this.cells[r36];
            if (r47 != 0) goto L410;
            int r711 = R.id.contacts_widget_item_badge1;
        L411:
            ((TextView) r515.findViewById(r711)).setText(r033);
            ViewGroup r034 = this.cells[r36];
            if (r47 != 0) goto L414;
            int r516 = R.id.contacts_widget_item_badge_bg1;
        L415:
            r034.findViewById(r516).setVisibility(0);
            goto L421
        L414:
            r516 = R.id.contacts_widget_item_badge_bg2;
            goto L415
        L410:
            r711 = R.id.contacts_widget_item_badge2;
            goto L411
        L406:
            r033 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r031)});
            goto L407
        L393:
            r710 = R.id.contacts_widget_item_avatar2;     // Catch: Throwable -> L391
        L391:
            th = th;
        L400:
            FileLog.e(th);
            goto L401
        L371:
            if (r612.forum == false) goto L374;
            r613 = true;
        L360:
            th = th;
            goto L400
        L440:
            r79 = new AvatarDrawable(r028);     // Catch: Throwable -> L365
            if (UserObject.isReplyUser(r028) == false) goto L363;
            r79.setAvatarType(12);     // Catch: Throwable -> L360
            goto L368
        L363:
            if (UserObject.isUserSelf(r028) == false) goto L368;
            r79.setAvatarType(1);     // Catch: Throwable -> L360
        L365:
            th = th;
            goto L400
        L377:
            Shader.TileMode r143 = Shader.TileMode.CLAMP;     // Catch: Throwable -> L360
            BitmapShader r035 = new BitmapShader(r78, r143, r143);     // Catch: Throwable -> L360
            float r98 = r97 / r78.getWidth();     // Catch: Throwable -> L360
            r113.save();     // Catch: Throwable -> L360
            r113.scale(r98, r98);     // Catch: Throwable -> L360
            float r99 = r78.getWidth();     // Catch: Throwable -> L360
            if (r612 != null) goto L380;
        L383:
            boolean r614 = false;
        L438:
            float r615 = ExteraConfig.getAvatarCorners(r99, true, r614);     // Catch: Throwable -> L398
            this.roundPaint.setShader(r035);     // Catch: Throwable -> L398
            this.bitmapRect.set(0.0f, 0.0f, r78.getWidth(), r78.getHeight());     // Catch: Throwable -> L396
            r113.drawRoundRect(this.bitmapRect, r615, r615, this.roundPaint);     // Catch: Throwable -> L396
            r113.restore();     // Catch: Throwable -> L396
        L396:
            th = th;
        L398:
            th = th;
            goto L400
        L380:
            if (r612.forum == false) goto L383;
            r614 = true;
        L349:
            th = th;
            goto L400
        L436:
            r78 = BitmapFactory.decodeFile(this.this$0.getFileLoader().getPathToAttach(r77, true).toString());     // Catch: Throwable -> L349
            goto L353
        L345:
            r112 = R.id.contacts_widget_item_text2;
            goto L346
        L317:
            if (UserObject.isUserSelf(r028) == true) goto L329;
            if (r028 == null) goto L329;
            TLRPC.UserProfilePhoto r712 = r028.photo;
            if (r712 == null) goto L329;
            r77 = r712.photo_small;
            if (r77 == null) goto L329;
            if (r77.volume_id == 0) goto L329;
            if (r77.local_id == 0) goto L329;
            r96 = r611;
            r612 = null;
            goto L342
        L308:
            if (UserObject.isReplyUser(r028) == false) goto L311;
            r611 = LocaleController.getString(R.string.RepliesTitle);
            goto L315
        L311:
            if (UserObject.isDeleted(r028) == false) goto L313;
            r611 = LocaleController.getString(R.string.HiddenName);
            goto L315
        L313:
            r611 = UserObject.getFirstName(r028);
            goto L315
        L330:
            TLRPC.Chat r036 = r610.getMessagesController().getChat(Long.valueOf(-r512.id));
            String r616 = r036.title;
            TLRPC.ChatPhoto r713 = r036.photo;
            if (r713 == null) goto L341;
            r77 = r713.photo_small;
            if (r77 == null) goto L341;
            if (r77.volume_id == 0) goto L341;
            if (r77.local_id == 0) goto L341;
            r96 = r616;
        L339:
            r612 = r036;
            r028 = null;
        L341:
            r96 = r616;
            r77 = null;
        L301:
            this.cells[r36].setVisibility(0);
            goto L302
        L297:
            r76 = R.id.contacts_widget_item2;
        L275:
            r512 = null;
            goto L281
        L277:
            if (r026 >= EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(r66).size()) goto L275;
            r512 = this.this$0.getMessagesController().dialogs_dict.get(((Long) EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0).get(r026)).longValue());
            if (r512 != null) goto L281;
            r512 = new TLRPC.TL_dialog();
            r512.id = ((Long) EditWidgetActivity.m14692$$Nest$fgetselectedDialogs(this.this$0).get(r026)).longValue();
            goto L281
        L422:
            r36 = r36 + 1;
            r84 = 2;
            goto L264
        }
    }

    public static /* synthetic */ void $r8$lambda$F5mmdFkYH5KZw4tbCCE7dccoYMA(EditWidgetActivity r0, Context r1, View r2, int r3) {
        r0.lambda$createView$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$TlEk8lkaKP_GRPRHsOAntdmzobg(EditWidgetActivity r0, ArrayList r1) {
        r0.lambda$createView$0(r1);
    }

    /* renamed from: -$$Nest$fgetchatsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14680$$Nest$fgetchatsEndRow(EditWidgetActivity r0) {
        return r0.chatsEndRow;
    }

    /* renamed from: -$$Nest$fgetchatsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14681$$Nest$fgetchatsStartRow(EditWidgetActivity r0) {
        return r0.chatsStartRow;
    }

    /* renamed from: -$$Nest$fgetcurrentWidgetId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14682$$Nest$fgetcurrentWidgetId(EditWidgetActivity r0) {
        return r0.currentWidgetId;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditWidgetActivityDelegate m14683$$Nest$fgetdelegate(EditWidgetActivity r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetinfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14684$$Nest$fgetinfoRow(EditWidgetActivity r0) {
        return r0.infoRow;
    }

    /* renamed from: -$$Nest$fgetitemTouchHelper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ItemTouchHelper m14685$$Nest$fgetitemTouchHelper(EditWidgetActivity r0) {
        return r0.itemTouchHelper;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m14686$$Nest$fgetlistAdapter(EditWidgetActivity r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m14687$$Nest$fgetlistView(EditWidgetActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetpreviewImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m14688$$Nest$fgetpreviewImageView(EditWidgetActivity r0) {
        return r0.previewImageView;
    }

    /* renamed from: -$$Nest$fgetpreviewRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14689$$Nest$fgetpreviewRow(EditWidgetActivity r0) {
        return r0.previewRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14690$$Nest$fgetrowCount(EditWidgetActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetselectChatsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14691$$Nest$fgetselectChatsRow(EditWidgetActivity r0) {
        return r0.selectChatsRow;
    }

    /* renamed from: -$$Nest$fgetselectedDialogs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m14692$$Nest$fgetselectedDialogs(EditWidgetActivity r0) {
        return r0.selectedDialogs;
    }

    /* renamed from: -$$Nest$fgetwidgetPreviewCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WidgetPreviewCell m14693$$Nest$fgetwidgetPreviewCell(EditWidgetActivity r0) {
        return r0.widgetPreviewCell;
    }

    /* renamed from: -$$Nest$fgetwidgetType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14694$$Nest$fgetwidgetType(EditWidgetActivity r0) {
        return r0.widgetType;
    }

    /* renamed from: -$$Nest$fputpreviewImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14695$$Nest$fputpreviewImageView(EditWidgetActivity r0, ImageView r1) {
        r0.previewImageView = r1;
    }

    /* renamed from: -$$Nest$fputwidgetPreviewCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14696$$Nest$fputwidgetPreviewCell(EditWidgetActivity r0, WidgetPreviewCell r1) {
        r0.widgetPreviewCell = r1;
    }

    /* renamed from: -$$Nest$mfinishActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14697$$Nest$mfinishActivity(EditWidgetActivity r0) {
        r0.finishActivity();
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14698$$Nest$mupdateRows(EditWidgetActivity r0) {
        r0.updateRows();
    }

    public EditWidgetActivity(int r9, int r10) {
        this.selectedDialogs = new ArrayList();
        this.widgetType = r9;
        this.currentWidgetId = r10;
        ArrayList<TLRPC.User> r5 = new ArrayList();
        ArrayList<TLRPC.Chat> r6 = new ArrayList();
        getMessagesStorage().getWidgetDialogIds(this.currentWidgetId, this.widgetType, this.selectedDialogs, r5, r6, true);
        getMessagesController().putUsers(r5, true);
        getMessagesController().putChats(r6, true);
        updateRows();
    }

    public static /* synthetic */ INavigationLayout access$000(EditWidgetActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$100(EditWidgetActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ int access$200(EditWidgetActivity r0) {
        return r0.currentAccount;
    }

    private void finishActivity() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        getParentActivity().finish();
        AndroidUtilities.runOnUIThread(new EditWidgetActivity$$ExternalSyntheticLambda0(this), 1000);
    }

    private /* synthetic */ void lambda$createView$0(ArrayList r2) {
        this.selectedDialogs.clear();
        this.selectedDialogs.addAll(r2);
        updateRows();
        WidgetPreviewCell r1 = this.widgetPreviewCell;
        if (r1 == null) goto L6;
        r1.updateDialogs();
        return;
    }

    private /* synthetic */ void lambda$createView$1(Context r9, View r10, int r11) {
        if (r11 != this.selectChatsRow) goto L6;
        InviteMembersBottomSheet r0 = new InviteMembersBottomSheet(r9, this.currentAccount, null, 0, this, null);
        r0.setDelegate(new EditWidgetActivity$$ExternalSyntheticLambda2(this), this.selectedDialogs);
        r0.setSelectedContacts(this.selectedDialogs);
        showDialog(r0);
        return;
    }

    private void updateRows() {
        this.previewRow = 0;
        this.rowCount = 1 + 1;
        this.selectChatsRow = 1;
        if (this.selectedDialogs.isEmpty() == false) goto L5;
        this.chatsStartRow = -1;
        this.chatsEndRow = -1;
    L6:
        int r0 = this.rowCount;
        this.rowCount = r0 + 1;
        this.infoRow = r0;
        ListAdapter r2 = this.listAdapter;
        if (r2 == null) goto L10;
        r2.notifyDataSetChanged();
        return;
    L10:
        return;
    L5:
        int r02 = this.rowCount;
        this.chatsStartRow = r02;
        int r03 = r02 + this.selectedDialogs.size();
        this.rowCount = r03;
        this.chatsEndRow = r03;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r6) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(false);
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
    L7:
        int r02 = this.widgetType;
        ActionBar r2 = this.actionBar;
        if (r02 != 0) goto L10;
        r2.setTitle(LocaleController.getString(R.string.WidgetChats));
    L11:
        this.actionBar.createMenu().addItem(1, LocaleController.getString(R.string.Done).toUpperCase());
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.listAdapter = new ListAdapter(this, r6);
        FrameLayout r03 = new FrameLayout(r6);
        r03.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.fragmentView = r03;
        RecyclerListView r22 = new RecyclerListView(r6);
        this.listView = r22;
        r22.setLayoutManager(new LinearLayoutManager(r6, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setAdapter(this.listAdapter);
        ((DefaultItemAnimator) this.listView.getItemAnimator()).setDelayAnimations(false);
        r03.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        ItemTouchHelper r04 = new ItemTouchHelper(new TouchHelperCallback(this));
        this.itemTouchHelper = r04;
        r04.attachToRecyclerView(this.listView);
        this.listView.setOnItemClickListener(new EditWidgetActivity$$ExternalSyntheticLambda1(this, r6));
        this.listView.setOnItemLongClickListener(new AnonymousClass2(this));
        return this.fragmentView;
    L10:
        r2.setTitle(LocaleController.getString(R.string.WidgetShortcuts));
        goto L11
    L5:
        if (r0.isLayersLayout() == false) goto L7;
        this.actionBar.setOccupyStatusBar(false);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        ActionBar r3 = this.actionBar;
        int r4 = ThemeDescription.FLAG_BACKGROUND;
        int r18 = Theme.key_actionBarDefault;
        r1.add(new ThemeDescription(r3, r4, null, null, null, null, r18));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r18));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUBACKGROUND, null, null, null, null, Theme.key_actionBarDefaultSubmenuBackground));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM, null, null, null, null, Theme.key_actionBarDefaultSubmenuItem));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM | ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_actionBarDefaultSubmenuItemIcon));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        int r21 = Theme.key_windowBackgroundWhiteBlueText4;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r21));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (this.delegate != null) goto L9;
        if (r2 == false) goto L10;
        finishActivity();
        return false;
    L10:
        return false;
    L9:
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        DialogsActivity.loadDialogs(AccountInstance.getInstance(this.currentAccount));
        getMediaDataController().loadHints(true);
        return super.onFragmentCreate();
    }

    public void setDelegate(EditWidgetActivityDelegate r1) {
        this.delegate = r1;
    }
}
