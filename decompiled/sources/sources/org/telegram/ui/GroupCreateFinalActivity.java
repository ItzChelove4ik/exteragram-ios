package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.GroupCreateUserCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AutoDeletePopupWrapper;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.EditTextEmoji;
import org.telegram.ui.Components.FillLastLinearLayoutManager;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.VerticalPositionAutoAnimator;

/* loaded from: classes6.dex */
public class GroupCreateFinalActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate, ImageUpdater.ImageUpdaterDelegate {
    private GroupCreateAdapter adapter;
    private TLRPC.FileLocation avatar;
    private AnimatorSet avatarAnimation;
    private TLRPC.FileLocation avatarBig;
    private AvatarDrawable avatarDrawable;
    private RLottieImageView avatarEditor;
    private BackupImageView avatarImage;
    private View avatarOverlay;
    private RadialProgressView avatarProgressView;
    private RLottieDrawable cameraDrawable;
    private boolean canToggleTopics;
    private int chatType;
    private boolean createAfterUpload;
    private String currentGroupCreateAddress;
    private Location currentGroupCreateLocation;
    private GroupCreateFinalActivityDelegate delegate;
    private boolean donePressed;
    private EditTextEmoji editText;
    private FrameLayout editTextContainer;
    private FragmentFloatingButton floatingButton;
    private boolean forImport;
    private ImageUpdater imageUpdater;
    private TLRPC.VideoSize inputEmojiMarkup;
    private TLRPC.InputFile inputPhoto;
    private TLRPC.InputFile inputVideo;
    private String inputVideoPath;
    private FillLastLinearLayoutManager linearLayoutManager;
    private RecyclerListView listView;
    private String nameToSet;
    ActionBarPopupWindow popupWindow;
    private int reqId;
    private ArrayList<Long> selectedContacts;
    private Drawable shadowDrawable;
    private int ttlPeriod;
    private double videoTimestamp;

    /* renamed from: org.telegram.ui.GroupCreateFinalActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ GroupCreateFinalActivity this$0;

        public AnonymousClass1(GroupCreateFinalActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateFinalActivity$10, reason: invalid class name */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCreateFinalActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass10(GroupCreateFinalActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            GroupCreateFinalActivity.m15422$$Nest$fputavatarAnimation(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (GroupCreateFinalActivity.m15408$$Nest$fgetavatarAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (GroupCreateFinalActivity.m15409$$Nest$fgetavatarEditor(this.this$0) == null) goto L14;
            boolean r32 = this.val$show;
            GroupCreateFinalActivity r0 = this.this$0;
            if (r32 == false) goto L10;
            GroupCreateFinalActivity.m15409$$Nest$fgetavatarEditor(r0).setVisibility(4);
        L11:
            GroupCreateFinalActivity.m15422$$Nest$fputavatarAnimation(this.this$0, null);
            return;
        L10:
            GroupCreateFinalActivity.m15412$$Nest$fgetavatarProgressView(r0).setVisibility(4);
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateFinalActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        private boolean ignoreLayout;
        final /* synthetic */ GroupCreateFinalActivity this$0;

        public AnonymousClass2(GroupCreateFinalActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
            int r112 = getChildCount();
            int r0 = measureKeyboardHeight();
            int r2 = 0;
            if (r0 <= AndroidUtilities.dp(20.0f)) goto L5;
        L9:
            int r1 = 0;
        L10:
            setBottomClip(r1);
        L11:
            if (r2 >= r112) goto L48;
            View r3 = getChildAt(r2);
            if (r3.getVisibility() == 8) goto L47;
            FrameLayout.LayoutParams r4 = (FrameLayout.LayoutParams) r3.getLayoutParams();
            int r5 = r3.getMeasuredWidth();
            int r6 = r3.getMeasuredHeight();
            int r7 = r4.gravity;
            if (r7 != (-1)) goto L18;
            r7 = 51;
        L18:
            int r8 = r7 & 112;
            int r72 = r7 & 7;
            if (r72 != 1) goto L21;
            int r73 = (((r14 - r12) - r5) / 2) + r4.leftMargin;
            int r9 = r4.rightMargin;
        L24:
            int r74 = r73 - r9;
        L27:
            if (r8 != 16) goto L29;
            int r82 = ((((r15 - r1) - r13) - r6) / 2) + r4.topMargin;
            int r42 = r4.bottomMargin;
        L34:
            int r43 = r82 - r42;
        L38:
            if (GroupCreateFinalActivity.m15416$$Nest$fgeteditText(this.this$0) != null) goto L40;
        L46:
            r3.layout(r74, r43, r5 + r74, r6 + r43);
            goto L47
        L40:
            if (GroupCreateFinalActivity.m15416$$Nest$fgeteditText(this.this$0).isPopupView(r3) == false) goto L46;
            if (AndroidUtilities.isTablet() == false) goto L45;
            int r44 = getMeasuredHeight();
            int r83 = r3.getMeasuredHeight();
        L44:
            r43 = r44 - r83;
            goto L46
        L45:
            r44 = getMeasuredHeight() + r0;
            r83 = r3.getMeasuredHeight();
            goto L44
        L29:
            if (r8 != 48) goto L31;
            r43 = r4.topMargin + getPaddingTop();
            goto L38
        L31:
            if (r8 == 80) goto L33;
            r43 = r4.topMargin;
            goto L38
        L33:
            r82 = ((r15 - r1) - r13) - r6;
            r42 = r4.bottomMargin;
            goto L34
        L21:
            if (r72 == 5) goto L23;
            r74 = r4.leftMargin;
            goto L27
        L23:
            r73 = r14 - r5;
            r9 = r4.rightMargin;
        L47:
            r2 = r2 + 1;
            goto L11
        L48:
            notifyHeightChanged();
            return;
        L5:
            if (AndroidUtilities.isInMultiwindow == true) goto L9;
            if (AndroidUtilities.isTablet() == true) goto L9;
            r1 = GroupCreateFinalActivity.m15416$$Nest$fgeteditText(this.this$0).getEmojiPadding();
            goto L10
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r12, int r13) {
            int r6 = View.MeasureSpec.getSize(r12);
            int r1 = View.MeasureSpec.getSize(r13);
            setMeasuredDimension(r6, r1);
            int r7 = r1 - getPaddingTop();
            measureChildWithMargins(GroupCreateFinalActivity.access$000(this.this$0), r12, 0, r13, 0);
            if (measureKeyboardHeight() > AndroidUtilities.dp(20.0f)) goto L5;
        L7:
            int r8 = getChildCount();
            int r9 = 0;
        L8:
            if (r9 >= r8) goto L36;
            View r14 = getChildAt(r9);
            if (r14 == null) goto L35;
            if (r14.getVisibility() == 8) goto L35;
            if (r14 == GroupCreateFinalActivity.access$100(this.this$0)) goto L35;
            if (GroupCreateFinalActivity.m15416$$Nest$fgeteditText(this.this$0) != null) goto L19;
        L34:
            measureChildWithMargins(r14, r12, 0, r13, 0);
            goto L35
        L19:
            if (GroupCreateFinalActivity.m15416$$Nest$fgeteditText(this.this$0).isPopupView(r14) == false) goto L34;
            if (AndroidUtilities.isInMultiwindow == true) goto L27;
            if (AndroidUtilities.isTablet() == true) goto L27;
            r14.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r14.getLayoutParams().height, TLObject.FLAG_30));
        L27:
            if (AndroidUtilities.isTablet() == false) goto L33;
            int r2 = View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30);
            if (AndroidUtilities.isTablet() == false) goto L31;
            float r4 = 200.0f;
        L32:
            r14.measure(r2, View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(r4), (r7 - AndroidUtilities.statusBarHeight) + getPaddingTop()), TLObject.FLAG_30));
            goto L35
        L31:
            r4 = 320.0f;
            goto L32
        L33:
            r14.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((r7 - AndroidUtilities.statusBarHeight) + getPaddingTop(), TLObject.FLAG_30));
        L35:
            r9 = r9 + 1;
            goto L8
        L36:
            return;
        L5:
            if (GroupCreateFinalActivity.m15416$$Nest$fgeteditText(this.this$0).isPopupShowing() == true) goto L7;
            this.ignoreLayout = true;
            GroupCreateFinalActivity.m15416$$Nest$fgeteditText(this.this$0).hideEmojiView();
            this.ignoreLayout = false;
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

    /* renamed from: org.telegram.ui.GroupCreateFinalActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends LinearLayout {
        final /* synthetic */ GroupCreateFinalActivity this$0;

        public AnonymousClass3(GroupCreateFinalActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r4, View r5, long r6) {
            boolean r62 = super.drawChild(r4, r5, r6);
            if (r5 == GroupCreateFinalActivity.m15418$$Nest$fgetlistView(this.this$0)) goto L5;
        L7:
            return r62;
        L5:
            if (GroupCreateFinalActivity.m15420$$Nest$fgetshadowDrawable(this.this$0) == null) goto L7;
            int r52 = GroupCreateFinalActivity.m15417$$Nest$fgeteditTextContainer(this.this$0).getMeasuredHeight();
            GroupCreateFinalActivity.m15420$$Nest$fgetshadowDrawable(this.this$0).setBounds(0, r52, getMeasuredWidth(), GroupCreateFinalActivity.m15420$$Nest$fgetshadowDrawable(this.this$0).getIntrinsicHeight() + r52);
            GroupCreateFinalActivity.m15420$$Nest$fgetshadowDrawable(this.this$0).draw(r4);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateFinalActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends BackupImageView {
        final /* synthetic */ GroupCreateFinalActivity this$0;

        public AnonymousClass4(GroupCreateFinalActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void invalidate() {
            if (GroupCreateFinalActivity.m15411$$Nest$fgetavatarOverlay(this.this$0) == null) goto L5;
            GroupCreateFinalActivity.m15411$$Nest$fgetavatarOverlay(this.this$0).invalidate();
        L5:
            super.invalidate();
        }

        @Override // android.view.View
        public void invalidate(int r2, int r3, int r4, int r5) {
            if (GroupCreateFinalActivity.m15411$$Nest$fgetavatarOverlay(this.this$0) == null) goto L5;
            GroupCreateFinalActivity.m15411$$Nest$fgetavatarOverlay(this.this$0).invalidate();
        L5:
            super.invalidate(r2, r3, r4, r5);
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateFinalActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends View {
        final /* synthetic */ GroupCreateFinalActivity this$0;
        final /* synthetic */ Paint val$paint;

        public AnonymousClass5(GroupCreateFinalActivity r1, Context r2, Paint r3) {
            this.this$0 = r1;
            this.val$paint = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            if (GroupCreateFinalActivity.m15410$$Nest$fgetavatarImage(this.this$0) != null) goto L5;
            return;
        L5:
            if (GroupCreateFinalActivity.m15412$$Nest$fgetavatarProgressView(this.this$0).getVisibility() == 0) goto L7;
            return;
        L7:
            if (GroupCreateFinalActivity.m15410$$Nest$fgetavatarImage(this.this$0).getImageReceiver().hasNotThumb() == false) goto L12;
            this.val$paint.setAlpha((int) ((GroupCreateFinalActivity.m15410$$Nest$fgetavatarImage(this.this$0).getImageReceiver().getCurrentAlpha() * 85.0f) * GroupCreateFinalActivity.m15412$$Nest$fgetavatarProgressView(this.this$0).getAlpha()));
            r10.drawRoundRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), ExteraConfig.getAvatarCorners(getMeasuredWidth(), true), ExteraConfig.getAvatarCorners(getMeasuredWidth(), true), this.val$paint);
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateFinalActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends RLottieImageView {
        final /* synthetic */ GroupCreateFinalActivity this$0;

        public AnonymousClass6(GroupCreateFinalActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            GroupCreateFinalActivity.m15411$$Nest$fgetavatarOverlay(this.this$0).invalidate();
        }

        @Override // android.view.View
        public void invalidate(int r1, int r2, int r3, int r4) {
            super.invalidate(r1, r2, r3, r4);
            GroupCreateFinalActivity.m15411$$Nest$fgetavatarOverlay(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateFinalActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends RadialProgressView {
        final /* synthetic */ GroupCreateFinalActivity this$0;

        public AnonymousClass7(GroupCreateFinalActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RadialProgressView, android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            GroupCreateFinalActivity.m15411$$Nest$fgetavatarOverlay(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateFinalActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends RecyclerView.OnScrollListener {
        final /* synthetic */ GroupCreateFinalActivity this$0;

        public AnonymousClass8(GroupCreateFinalActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(GroupCreateFinalActivity.m15416$$Nest$fgeteditText(this.this$0));
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCreateFinalActivity$9, reason: invalid class name */
    public class AnonymousClass9 implements AutoDeletePopupWrapper.Callback {
        final /* synthetic */ GroupCreateFinalActivity this$0;

        public AnonymousClass9(GroupCreateFinalActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.AutoDeletePopupWrapper.Callback
        public void dismiss() {
            this.this$0.popupWindow.dismiss();
        }

        @Override // org.telegram.ui.Components.AutoDeletePopupWrapper.Callback
        public void setAutoDeleteHistory(int r1, int r2) {
            GroupCreateFinalActivity.m15423$$Nest$fputttlPeriod(this.this$0, r1);
            AndroidUtilities.updateVisibleRows(GroupCreateFinalActivity.m15418$$Nest$fgetlistView(this.this$0));
        }
    }

    public class GroupCreateAdapter extends RecyclerListView.SelectionAdapter {
        private Context context;
        ArrayList<InnerItem> items;
        final /* synthetic */ GroupCreateFinalActivity this$0;
        private int usersStartRow;

        public class InnerItem extends AdapterWithDiffUtils.Item {
            String string;
            final /* synthetic */ GroupCreateAdapter this$1;

            public InnerItem(GroupCreateAdapter r1, int r2) {
                this.this$1 = r1;
                super(r2, true);
            }

            public InnerItem(GroupCreateAdapter r1, int r2, String r3) {
                this.this$1 = r1;
                super(r2, true);
                this.string = r3;
            }
        }

        public GroupCreateAdapter(GroupCreateFinalActivity r1, Context r2) {
            this.this$0 = r1;
            this.items = new ArrayList();
            this.context = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.items.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return this.items.get(r1).viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3) {
            if (r3.getItemViewType() != 3) goto L5;
            return true;
        L5:
            if (r3.getItemViewType() != 4) goto L7;
            return true;
        L7:
            if (r3.getItemViewType() == 6) goto L9;
            return false;
        L9:
            if (GroupCreateFinalActivity.m15413$$Nest$fgetcanToggleTopics(this.this$0) == true) goto L17;
            return false;
        L17:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            this.items.clear();
            int r2 = 0;
            this.items.add(new InnerItem(this, 0));
            int r0 = GroupCreateFinalActivity.m15414$$Nest$fgetchatType(this.this$0);
            ArrayList<InnerItem> r1 = this.items;
            if (r0 != 5) goto L5;
            r1.add(new InnerItem(this, 6));
            this.items.add(new InnerItem(this, 5, LocaleController.getString(R.string.ForumToggleDescription)));
        L7:
            if (GroupCreateFinalActivity.m15415$$Nest$fgetcurrentGroupCreateAddress(this.this$0) == null) goto L10;
            this.items.add(new InnerItem(this, 1));
            this.items.add(new InnerItem(this, 3));
            this.items.add(new InnerItem(this, 0));
        L10:
            if (GroupCreateFinalActivity.m15419$$Nest$fgetselectedContacts(this.this$0).size() <= 0) goto L16;
            this.items.add(new InnerItem(this, 1));
            this.usersStartRow = this.items.size();
        L12:
            int r02 = GroupCreateFinalActivity.m15419$$Nest$fgetselectedContacts(this.this$0).size();
            ArrayList<InnerItem> r12 = this.items;
            if (r2 >= r02) goto L15;
            r12.add(new InnerItem(this, 2));
            r2 = r2 + 1;
            goto L12
        L15:
            r12.add(new InnerItem(this, 7));
        L16:
            super.notifyDataSetChanged();
            return;
        L5:
            r1.add(new InnerItem(this, 4));
            this.items.add(new InnerItem(this, 5, LocaleController.getString(R.string.GroupCreateAutodeleteDescription)));
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            boolean r1 = false;
            switch(r7.getItemViewType()) {
                case 1: goto L23;
                case 2: goto L18;
                case 3: goto L16;
                case 4: goto L9;
                case 5: goto L7;
                case 6: goto L5;
                default: goto L4;
            };
        L4:
            return;
        L5:
            TextCell r6 = (TextCell) r7.itemView;
            r6.setTextAndCheckAndIcon(LocaleController.getString(R.string.ChannelTopics), true, R.drawable.msg_topics, false);
            r6.getCheckBox().setAlpha(0.75f);
            return;
        L7:
            ((TextInfoPrivacyCell) r7.itemView).setText(this.items.get(r8).string);
            return;
        L9:
            TextCell r0 = (TextCell) r7.itemView;
            if (GroupCreateFinalActivity.m15421$$Nest$fgetttlPeriod(this.this$0) != 0) goto L13;
            String r72 = LocaleController.getString(R.string.PasswordOff);
        L14:
            r0.setTextAndValueAndIcon(LocaleController.getString(R.string.AutoDeleteMessages), r72, GroupCreateFinalActivity.access$200(this.this$0), R.drawable.msg_autodelete, false);
            return;
        L13:
            r72 = LocaleController.formatTTLString(GroupCreateFinalActivity.m15421$$Nest$fgetttlPeriod(this.this$0));
            goto L14
        L16:
            ((TextSettingsCell) r7.itemView).setText(GroupCreateFinalActivity.m15415$$Nest$fgetcurrentGroupCreateAddress(this.this$0), false);
            return;
        L18:
            GroupCreateUserCell r73 = (GroupCreateUserCell) r7.itemView;
            r73.setObject(this.this$0.getMessagesController().getUser((Long) GroupCreateFinalActivity.m15419$$Nest$fgetselectedContacts(this.this$0).get(r8 - this.usersStartRow)), null, null);
            if (r8 == (this.items.size() - 1)) goto L21;
            r1 = true;
        L21:
            r73.setDrawDivider(r1);
            return;
        L23:
            HeaderCell r74 = (HeaderCell) r7.itemView;
            if (GroupCreateFinalActivity.m15415$$Nest$fgetcurrentGroupCreateAddress(this.this$0) == null) goto L28;
            if (r8 != 1) goto L28;
            r74.setText(LocaleController.getString(R.string.AttachLocation));
            return;
        L28:
            r74.setText(LocaleController.formatPluralString("Members", GroupCreateFinalActivity.m15419$$Nest$fgetselectedContacts(this.this$0).size(), new Object[0]));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            if (r8 != 0) goto L4;
            View r72 = new ShadowSectionCell(this.context);
        L24:
            return new RecyclerListView.Holder(r72);
        L4:
            if (r8 != 1) goto L6;
            HeaderCell r73 = new HeaderCell(this.context);
            r73.setHeight(46);
            r72 = r73;
            goto L24
        L6:
            if (r8 != 2) goto L8;
            r72 = new GroupCreateUserCell(this.context, 0, 3, false);
            goto L24
        L8:
            if (r8 != 4) goto L10;
            r72 = new TextCell(this.context);
            goto L24
        L10:
            if (r8 != 5) goto L12;
            r72 = new TextInfoPrivacyCell(this.context);
            goto L24
        L12:
            if (r8 != 6) goto L14;
            r72 = new TextCell(this.context, 23, false, true, this.this$0.getResourceProvider());
            goto L24
        L14:
            if (r8 == 7) goto L16;
            r72 = new TextSettingsCell(this.context);
            goto L24
        L16:
            View r74 = new View(this.context);
            r74.setTag(-33024);
            r72 = r74;
            goto L24
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() != 2) goto L6;
            ((GroupCreateUserCell) r2.itemView).recycle();
            return;
        }
    }

    public interface GroupCreateFinalActivityDelegate {
        void didFailChatCreation();

        void didFinishChatCreation(GroupCreateFinalActivity r1, long r2);

        void didStartChatCreation();
    }

    /* renamed from: $r8$lambda$-jSA0pBBBT7aXGLYDLgT7mdbklE, reason: not valid java name */
    public static /* synthetic */ void m15404$r8$lambda$jSA0pBBBT7aXGLYDLgT7mdbklE(GroupCreateFinalActivity r0, DialogInterface r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$6L8rsnAEvzeAN4rXFNMUlt_yttM(GroupCreateFinalActivity r0) {
        r0.lambda$getThemeDescriptions$9();
    }

    public static /* synthetic */ boolean $r8$lambda$9jDkFcdWVIDNQSQrylARqKCyd5o(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$A2bQTHc05DA-LvzrQnHEQeezf4A, reason: not valid java name */
    public static /* synthetic */ void m15405$r8$lambda$A2bQTHc05DALvzrQnHEQeezf4A(GroupCreateFinalActivity r0, ArrayList r1, ArrayList r2, CountDownLatch r3) {
        r0.lambda$onFragmentCreate$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$CrNE3RISPAETRiNE-WTsmIK_wrI, reason: not valid java name */
    public static /* synthetic */ void m15406$r8$lambda$CrNE3RISPAETRiNEWTsmIK_wrI(GroupCreateFinalActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$QwxzswHuWomMjXuPYzyNlCgSp6o(GroupCreateFinalActivity r0, View r1) {
        r0.lambda$createView$7(r1);
    }

    /* renamed from: $r8$lambda$TtdZV5De-XXxuHf0IBiqgAawlOM, reason: not valid java name */
    public static /* synthetic */ void m15407$r8$lambda$TtdZV5DeXXxuHf0IBiqgAawlOM(GroupCreateFinalActivity r0) {
        r0.lambda$createView$2();
    }

    public static /* synthetic */ void $r8$lambda$nzbnetLGyjcOgW6DlWNmEa6I_pI(GroupCreateFinalActivity r0, TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5) {
        r0.lambda$createView$5(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$rL4C4i1h_zT9ktL22N4_YBy0ym8(GroupCreateFinalActivity r0, TLRPC.InputFile r1, TLRPC.InputFile r2, TLRPC.VideoSize r3, String r4, double r5, TLRPC.PhotoSize r7, TLRPC.PhotoSize r8) {
        r0.lambda$didUploadPhoto$8(r1, r2, r3, r4, r5, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$tMM_y9CE7LUUOSBlQ49TWScId1w(GroupCreateFinalActivity r0, View r1, int r2, float r3, float r4) {
        r0.lambda$createView$6(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetavatarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m15408$$Nest$fgetavatarAnimation(GroupCreateFinalActivity r0) {
        return r0.avatarAnimation;
    }

    /* renamed from: -$$Nest$fgetavatarEditor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m15409$$Nest$fgetavatarEditor(GroupCreateFinalActivity r0) {
        return r0.avatarEditor;
    }

    /* renamed from: -$$Nest$fgetavatarImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m15410$$Nest$fgetavatarImage(GroupCreateFinalActivity r0) {
        return r0.avatarImage;
    }

    /* renamed from: -$$Nest$fgetavatarOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m15411$$Nest$fgetavatarOverlay(GroupCreateFinalActivity r0) {
        return r0.avatarOverlay;
    }

    /* renamed from: -$$Nest$fgetavatarProgressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m15412$$Nest$fgetavatarProgressView(GroupCreateFinalActivity r0) {
        return r0.avatarProgressView;
    }

    /* renamed from: -$$Nest$fgetcanToggleTopics, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15413$$Nest$fgetcanToggleTopics(GroupCreateFinalActivity r0) {
        return r0.canToggleTopics;
    }

    /* renamed from: -$$Nest$fgetchatType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15414$$Nest$fgetchatType(GroupCreateFinalActivity r0) {
        return r0.chatType;
    }

    /* renamed from: -$$Nest$fgetcurrentGroupCreateAddress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m15415$$Nest$fgetcurrentGroupCreateAddress(GroupCreateFinalActivity r0) {
        return r0.currentGroupCreateAddress;
    }

    /* renamed from: -$$Nest$fgeteditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextEmoji m15416$$Nest$fgeteditText(GroupCreateFinalActivity r0) {
        return r0.editText;
    }

    /* renamed from: -$$Nest$fgeteditTextContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m15417$$Nest$fgeteditTextContainer(GroupCreateFinalActivity r0) {
        return r0.editTextContainer;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m15418$$Nest$fgetlistView(GroupCreateFinalActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetselectedContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15419$$Nest$fgetselectedContacts(GroupCreateFinalActivity r0) {
        return r0.selectedContacts;
    }

    /* renamed from: -$$Nest$fgetshadowDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m15420$$Nest$fgetshadowDrawable(GroupCreateFinalActivity r0) {
        return r0.shadowDrawable;
    }

    /* renamed from: -$$Nest$fgetttlPeriod, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15421$$Nest$fgetttlPeriod(GroupCreateFinalActivity r0) {
        return r0.ttlPeriod;
    }

    /* renamed from: -$$Nest$fputavatarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15422$$Nest$fputavatarAnimation(GroupCreateFinalActivity r0, AnimatorSet r1) {
        r0.avatarAnimation = r1;
    }

    /* renamed from: -$$Nest$fputttlPeriod, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15423$$Nest$fputttlPeriod(GroupCreateFinalActivity r0, int r1) {
        r0.ttlPeriod = r1;
    }

    public GroupCreateFinalActivity(Bundle r3) {
        super(r3);
        this.chatType = r3.getInt("chatType", 0);
        this.avatarDrawable = new AvatarDrawable();
        this.currentGroupCreateAddress = r3.getString("address");
        this.currentGroupCreateLocation = (Location) r3.getParcelable("location");
        this.forImport = r3.getBoolean("forImport", false);
        this.nameToSet = r3.getString("title", null);
        this.canToggleTopics = r3.getBoolean("canToggleTopics", true);
    }

    public static /* synthetic */ ActionBar access$000(GroupCreateFinalActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(GroupCreateFinalActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ boolean access$200(GroupCreateFinalActivity r0) {
        return r0.fragmentBeginToShow;
    }

    private String getFirstNameByPos(int r2) {
        return getMessagesController().getUser(this.selectedContacts.get(r2)).first_name;
    }

    private /* synthetic */ void lambda$createView$2() {
        this.avatar = null;
        this.avatarBig = null;
        this.inputPhoto = null;
        this.inputVideo = null;
        this.inputVideoPath = null;
        this.inputEmojiMarkup = null;
        this.videoTimestamp = 0.0d;
        showAvatarProgress(false, true);
        this.avatarImage.setImage(null, null, this.avatarDrawable, null);
        this.avatarEditor.setAnimation(this.cameraDrawable);
        this.cameraDrawable.setCurrentFrame(0);
    }

    private /* synthetic */ void lambda$createView$3(DialogInterface r2) {
        boolean r22 = this.imageUpdater.isUploadingImage();
        RLottieDrawable r0 = this.cameraDrawable;
        if (r22 == true) goto L6;
        r0.setCustomEndFrame(86);
        this.avatarEditor.playAnimation();
        return;
    L6:
        r0.setCurrentFrame(0, false);
    }

    private /* synthetic */ void lambda$createView$4(View r5) {
        ImageUpdater r52 = this.imageUpdater;
        if (this.avatar == null) goto L5;
        boolean r0 = true;
    L6:
        r52.openMenu(r0, new GroupCreateFinalActivity$$ExternalSyntheticLambda8(this), new GroupCreateFinalActivity$$ExternalSyntheticLambda9(this), 0);
        this.cameraDrawable.setCurrentFrame(0);
        this.cameraDrawable.setCustomEndFrame(43);
        this.avatarEditor.playAnimation();
        return;
    L5:
        r0 = false;
        goto L6
    }

    private /* synthetic */ void lambda$createView$5(TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5) {
        this.currentGroupCreateLocation.setLatitude(r1.geo.lat);
        this.currentGroupCreateLocation.setLongitude(r1.geo._long);
        this.currentGroupCreateAddress = r1.address;
    }

    private /* synthetic */ void lambda$createView$6(View r8, int r9, float r10, float r11) {
        if ((r8 instanceof TextSettingsCell) == false) goto L9;
        if (AndroidUtilities.isMapsInstalled(this) == false) goto L21;
        LocationActivity r92 = new LocationActivity(4);
        r92.setDialogId(0);
        r92.setDelegate(new GroupCreateFinalActivity$$ExternalSyntheticLambda7(this));
        presentFragment(r92);
        goto L9
    L21:
        return;
    L9:
        if ((r8 instanceof TextCell) == true) goto L11;
        return;
    L11:
        if (this.chatType == 5) goto L20;
        ActionBarPopupWindow r93 = this.popupWindow;
        if (r93 != null) goto L15;
    L17:
        AutoDeletePopupWrapper r0 = new AutoDeletePopupWrapper(getContext(), null, new AnonymousClass9(this), true, 1, null);
        r0.updateItems(this.ttlPeriod);
        ActionBarPopupWindow r94 = new ActionBarPopupWindow(r0.windowLayout, -2, -2);
        this.popupWindow = r94;
        r94.setPauseNotifications(true);
        this.popupWindow.setDismissAnimationDuration(Opcodes.REM_INT_LIT8);
        this.popupWindow.setOutsideTouchable(true);
        this.popupWindow.setClippingEnabled(true);
        this.popupWindow.setAnimationStyle(R.style.PopupContextAnimation);
        this.popupWindow.setFocusable(true);
        r0.windowLayout.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
        this.popupWindow.setInputMethodMode(2);
        this.popupWindow.getContentView().setFocusableInTouchMode(true);
        this.popupWindow.showAtLocation(getFragmentView(), 0, (int) (r8.getX() + r10), (int) ((r8.getY() + r11) + (r0.windowLayout.getMeasuredHeight() / 2.0f)));
        this.popupWindow.dimBehind();
        return;
    L15:
        if (r93.isShowing() == false) goto L17;
        return;
    }

    private /* synthetic */ void lambda$createView$7(View r12) {
        if (this.donePressed == false) goto L6;
        return;
    L6:
        if (this.editText.length() != 0) goto L9;
        this.editText.performHapticFeedback(3, 2);
        AndroidUtilities.shakeView(this.editText);
        return;
    L9:
        this.donePressed = true;
        AndroidUtilities.hideKeyboard(this.editText);
        this.editText.setEnabled(false);
        if (this.imageUpdater.isUploadingImage() == false) goto L13;
        this.createAfterUpload = true;
        return;
    L13:
        showEditDoneProgress(true);
        this.reqId = getMessagesController().createChat(this.editText.getText().toString(), this.selectedContacts, null, this.chatType, this.forImport, this.currentGroupCreateLocation, this.currentGroupCreateAddress, this.ttlPeriod, this);
    }

    private /* synthetic */ void lambda$didUploadPhoto$8(TLRPC.InputFile r14, TLRPC.InputFile r15, TLRPC.VideoSize r16, String r17, double r18, TLRPC.PhotoSize r20, TLRPC.PhotoSize r21) {
        if (r14 != null) goto L9;
        if (r15 != null) goto L9;
        if (r16 != null) goto L9;
        TLRPC.FileLocation r0 = r20.location;
        this.avatar = r0;
        this.avatarBig = r21.location;
        this.avatarImage.setImage(ImageLocation.getForLocal(r0), "50_50", this.avatarDrawable, null);
        showAvatarProgress(true, false);
        return;
    L9:
        this.inputPhoto = r14;
        this.inputVideo = r15;
        this.inputEmojiMarkup = r16;
        this.inputVideoPath = r17;
        this.videoTimestamp = r18;
        if (this.createAfterUpload == false) goto L15;
        GroupCreateFinalActivityDelegate r02 = this.delegate;
        if (r02 == null) goto L14;
        r02.didStartChatCreation();
    L14:
        getMessagesController().createChat(this.editText.getText().toString(), this.selectedContacts, null, this.chatType, this.forImport, this.currentGroupCreateLocation, this.currentGroupCreateAddress, this.ttlPeriod, this);
    L15:
        showAvatarProgress(false, true);
        this.avatarEditor.setImageDrawable(null);
    }

    private /* synthetic */ void lambda$getThemeDescriptions$9() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L10;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof GroupCreateUserCell) == false) goto L9;
        ((GroupCreateUserCell) r3).update(0);
    L9:
        r2 = r2 + 1;
    L10:
        FragmentFloatingButton r5 = this.floatingButton;
        if (r5 == null) goto L17;
        r5.updateColors();
        return;
    }

    private /* synthetic */ void lambda$onFragmentCreate$0(ArrayList r1, ArrayList r2, CountDownLatch r3) {
        r1.addAll(MessagesStorage.getInstance(this.currentAccount).getUsers(r2));
        r3.countDown();
    }

    private void setDefaultGroupName() {
        TLRPC.User r0 = getUserConfig().getCurrentUser();
        int r1 = this.selectedContacts.size() + 1;
        if (r1 >= 2) goto L5;
        return;
    L5:
        if (r1 <= 5) goto L7;
        return;
    L7:
        if (TextUtils.isEmpty(this.editText.getText()) == false) goto L33;
        String r6 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 != 2) goto L11;
        r6 = LocaleController.formatString("GroupCreateMembersTwo", R.string.GroupCreateMembersTwo, new Object[]{r0.first_name, getFirstNameByPos(0)});     // Catch: Exception -> L17
    L28:
    L25:
        if (TextUtils.isEmpty(r6) == true) goto L34;
        this.editText.setText(r6);
        EditTextEmoji r9 = this.editText;
        r9.setSelection(0, r9.getText().length());
        return;
    L34:
        return;
    L11:
        if (r1 != 3) goto L13;
        r6 = LocaleController.formatString("GroupCreateMembersThree", R.string.GroupCreateMembersThree, new Object[]{r0.first_name, getFirstNameByPos(0), getFirstNameByPos(1)});     // Catch: Exception -> L17
        goto L28
    L13:
        if (r1 == 4) goto L19;
        if (r1 != 5) goto L25;
        r6 = LocaleController.formatString("GroupCreateMembersFive", R.string.GroupCreateMembersFive, new Object[]{r0.first_name, getFirstNameByPos(0), getFirstNameByPos(1), getFirstNameByPos(2), getFirstNameByPos(3)});     // Catch: Exception -> L17
        goto L28
    L19:
        r6 = LocaleController.formatString("GroupCreateMembersFour", R.string.GroupCreateMembersFour, new Object[]{r0.first_name, getFirstNameByPos(0), getFirstNameByPos(1), getFirstNameByPos(2)});     // Catch: Exception -> L17
    L17:
        e = move-exception;
        FileLog.e(e);
        goto L25
    }

    private void showAvatarProgress(boolean r9, boolean r10) {
        if (this.avatarEditor != null) goto L5;
        return;
    L5:
        AnimatorSet r0 = this.avatarAnimation;
        if (r0 == null) goto L9;
        r0.cancel();
        this.avatarAnimation = null;
    L9:
        if (r10 == false) goto L16;
        this.avatarAnimation = new AnimatorSet();
        if (r9 == false) goto L13;
        this.avatarProgressView.setVisibility(0);
        AnimatorSet r4 = this.avatarAnimation;
        RLottieImageView r5 = this.avatarEditor;
        Property r6 = View.ALPHA;
        r4.playTogether(new Animator[]{ObjectAnimator.ofFloat(r5, r6, new float[]{0.0f}), ObjectAnimator.ofFloat(this.avatarProgressView, r6, new float[]{1.0f})});
    L14:
        this.avatarAnimation.setDuration(180);
        this.avatarAnimation.addListener(new AnonymousClass10(this, r9));
        this.avatarAnimation.start();
        return;
    L13:
        this.avatarEditor.setVisibility(0);
        AnimatorSet r42 = this.avatarAnimation;
        RLottieImageView r52 = this.avatarEditor;
        Property r62 = View.ALPHA;
        r42.playTogether(new Animator[]{ObjectAnimator.ofFloat(r52, r62, new float[]{1.0f}), ObjectAnimator.ofFloat(this.avatarProgressView, r62, new float[]{0.0f})});
        goto L14
    L16:
        RLottieImageView r102 = this.avatarEditor;
        if (r9 == false) goto L20;
        r102.setAlpha(1.0f);
        this.avatarEditor.setVisibility(4);
        this.avatarProgressView.setAlpha(1.0f);
        this.avatarProgressView.setVisibility(0);
        return;
    L20:
        r102.setAlpha(1.0f);
        this.avatarEditor.setVisibility(0);
        this.avatarProgressView.setAlpha(0.0f);
        this.avatarProgressView.setVisibility(4);
    }

    private void showEditDoneProgress(boolean r2) {
        FragmentFloatingButton r1 = this.floatingButton;
        if (r1 == null) goto L6;
        r1.setProgressVisible(r2, true);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r30) {
        EditTextEmoji r0 = this.editText;
        if (r0 == null) goto L5;
        r0.onDestroy();
    L5:
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        int r6 = 1;
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.NewGroup));
        ActionBar r02 = this.actionBar;
        int r2 = Theme.key_windowBackgroundGray;
        r02.setBackgroundColor(getThemedColor(r2));
        this.actionBar.setCastShadows(false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        AnonymousClass2 r03 = new AnonymousClass2(this, r30);
        r03.setBackgroundColor(getThemedColor(r2));
        this.fragmentView = r03;
        r03.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.fragmentView.setOnTouchListener(new GroupCreateFinalActivity$$ExternalSyntheticLambda1());
        this.shadowDrawable = r30.getResources().getDrawable(R.drawable.greydivider_top).mutate();
        AnonymousClass3 r9 = new AnonymousClass3(this, r30);
        r9.setOrientation(1);
        r03.addView(r9, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r22 = new FrameLayout(r30);
        this.editTextContainer = r22;
        r22.setBackground(Theme.createRoundRectDrawableShadowed(AndroidUtilities.dp(16.0f), getThemedColor(Theme.key_windowBackgroundWhite)));
        r9.addView(this.editTextContainer, LayoutHelper.createLinear(-1, -2, 9.0f, 0.0f, 9.0f, 0.0f));
        AnonymousClass4 r23 = new AnonymousClass4(this, r30);
        this.avatarImage = r23;
        if (this.chatType != 5) goto L8;
        boolean r4 = true;
    L9:
        r23.setRoundRadius(ExteraConfig.getAvatarCorners(56.0f, false, r4));
        this.avatarDrawable.setInfo(5, null, null);
        this.avatarImage.setImageDrawable(this.avatarDrawable);
        this.avatarImage.setContentDescription(LocaleController.getString(R.string.ChoosePhoto));
        FrameLayout r24 = this.editTextContainer;
        BackupImageView r42 = this.avatarImage;
        boolean r5 = LocaleController.isRTL;
        int r12 = 3;
        if (r5 == false) goto L12;
        int r13 = 5;
    L13:
        int r16 = r13 | 48;
        if (r5 == false) goto L16;
        float r17 = 0.0f;
    L17:
        if (r5 == false) goto L19;
        float r19 = 24.0f;
    L20:
        r24.addView(r42, LayoutHelper.createFrame(56, 56.0f, r16, r17, 20.0f, r19, 12.0f));
        Paint r25 = new Paint(1);
        r25.setColor(1426063360);
        AnonymousClass5 r43 = new AnonymousClass5(this, r30, r25);
        this.avatarOverlay = r43;
        FrameLayout r26 = this.editTextContainer;
        boolean r52 = LocaleController.isRTL;
        if (r52 == false) goto L23;
        int r14 = 5;
    L24:
        int r242 = r14 | 48;
        if (r52 == false) goto L27;
        float r252 = 0.0f;
    L28:
        if (r52 == false) goto L30;
        float r27 = 24.0f;
    L31:
        r26.addView(r43, LayoutHelper.createFrame(56, 56.0f, r242, r252, 20.0f, r27, 12.0f));
        this.avatarOverlay.setOnClickListener(new GroupCreateFinalActivity$$ExternalSyntheticLambda2(this));
        this.cameraDrawable = new RLottieDrawable(R.raw.camera, AndroidUtilities.dp(52.0f), AndroidUtilities.dp(52.0f), false, null);
        AnonymousClass6 r28 = new AnonymousClass6(this, r30);
        this.avatarEditor = r28;
        r28.setScaleType(ImageView.ScaleType.CENTER);
        this.avatarEditor.setAnimation(this.cameraDrawable);
        this.avatarEditor.setEnabled(false);
        this.avatarEditor.setClickable(false);
        this.avatarEditor.setPadding(AndroidUtilities.dp(0.0f), 0, 0, AndroidUtilities.dp(1.0f));
        FrameLayout r29 = this.editTextContainer;
        RLottieImageView r44 = this.avatarEditor;
        boolean r53 = LocaleController.isRTL;
        if (r53 == false) goto L34;
        int r142 = 5;
    L35:
        int r243 = r142 | 48;
        if (r53 == false) goto L38;
        float r253 = 0.0f;
    L39:
        if (r53 == false) goto L41;
        float r272 = 24.0f;
    L42:
        r29.addView(r44, LayoutHelper.createFrame(56, 56.0f, r243, r253, 20.0f, r272, 12.0f));
        AnonymousClass7 r210 = new AnonymousClass7(this, r30);
        this.avatarProgressView = r210;
        r210.setSize(AndroidUtilities.dp(30.0f));
        this.avatarProgressView.setProgressColor(-1);
        this.avatarProgressView.setNoProgress(false);
        FrameLayout r211 = this.editTextContainer;
        RadialProgressView r45 = this.avatarProgressView;
        boolean r54 = LocaleController.isRTL;
        if (r54 == false) goto L45;
        r12 = 5;
    L45:
        int r162 = r12 | 48;
        if (r54 == false) goto L48;
        float r172 = 0.0f;
    L49:
        if (r54 == false) goto L51;
        float r192 = 24.0f;
    L52:
        r211.addView(r45, LayoutHelper.createFrame(56, 56.0f, r162, r172, 20.0f, r192, 12.0f));
        showAvatarProgress(false, false);
        EditTextEmoji r04 = new EditTextEmoji(r30, r03, this, 0, false);
        this.editText = r04;
        int r46 = this.chatType;
        if (r46 != 0) goto L55;
    L60:
        int r47 = R.string.EnterGroupNamePlaceholder;
    L61:
        r04.setHint(LocaleController.getString(r47));
        String r05 = this.nameToSet;
        if (r05 == null) goto L64;
        this.editText.setText(r05);
        EditTextEmoji r06 = this.editText;
        r06.setSelection(r06.getText().length());
        this.nameToSet = null;
    L64:
        setDefaultGroupName();
        this.editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
        this.editText.getEditText().setSingleLine(true);
        FrameLayout r07 = this.editTextContainer;
        EditTextEmoji r48 = this.editText;
        boolean r55 = LocaleController.isRTL;
        if (r55 == false) goto L67;
        float r143 = 24.0f;
    L68:
        if (r55 == false) goto L70;
        float r163 = 96.0f;
    L71:
        r07.addView(r48, LayoutHelper.createFrame(-1, -2.0f, 16, r143, 12.0f, r163, 12.0f));
        RecyclerListView r08 = new RecyclerListView(r30);
        this.listView = r08;
        r08.setSections();
        this.linearLayoutManager = new FillLastLinearLayoutManager(r30, 1, this.listView);
        RecyclerListView r09 = this.listView;
        GroupCreateAdapter r49 = new GroupCreateAdapter(this, r30);
        this.adapter = r49;
        r09.setAdapter(r49);
        this.listView.setLayoutManager(this.linearLayoutManager);
        this.listView.setVerticalScrollBarEnabled(false);
        RecyclerListView r010 = this.listView;
        if (LocaleController.isRTL == true) goto L75;
        r6 = 2;
    L75:
        r010.setVerticalScrollbarPosition(r6);
        r9.addView(this.listView, LayoutHelper.createLinear(-1, -1));
        this.listView.setOnScrollListener(new AnonymousClass8(this));
        this.listView.setOnItemClickListener(new GroupCreateFinalActivity$$ExternalSyntheticLambda3(this));
        FragmentFloatingButton r011 = new FragmentFloatingButton(r30, this.resourceProvider);
        this.floatingButton = r011;
        VerticalPositionAutoAnimator.attach(r011);
        r03.addView(this.floatingButton, FragmentFloatingButton.createDefaultLayoutParams());
        this.floatingButton.setOnClickListener(new GroupCreateFinalActivity$$ExternalSyntheticLambda4(this));
        this.floatingButton.setContentDescription(LocaleController.getString(R.string.Done));
        this.floatingButton.imageView.setImageResource(R.drawable.checkbig);
        return this.fragmentView;
    L70:
        r163 = 24.0f;
        goto L71
    L67:
        r143 = 96.0f;
        goto L68
    L55:
        if (r46 == 4) goto L60;
        if (r46 == 5) goto L60;
        r47 = R.string.EnterListName;
        goto L61
    L51:
        r192 = 0.0f;
        goto L52
    L48:
        r172 = 24.0f;
        goto L49
    L41:
        r272 = 0.0f;
        goto L42
    L38:
        r253 = 24.0f;
        goto L39
    L34:
        r142 = 3;
        goto L35
    L30:
        r27 = 0.0f;
        goto L31
    L27:
        r252 = 24.0f;
        goto L28
    L23:
        r14 = 3;
        goto L24
    L19:
        r19 = 0.0f;
        goto L20
    L16:
        r17 = 24.0f;
        goto L17
    L12:
        r13 = 3;
        goto L13
    L8:
        r4 = false;
        goto L9
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r19, int r20, Object... r21) {
        int r3 = 0;
        if (r19 != NotificationCenter.updateInterfaces) goto L20;
        if (this.listView == null) goto L49;
        int r1 = ((Integer) r21[0]).intValue();
        if ((MessagesController.UPDATE_MASK_AVATAR & r1) == 0) goto L10;
    L13:
        int r2 = this.listView.getChildCount();
    L14:
        if (r3 >= r2) goto L50;
        View r4 = this.listView.getChildAt(r3);
        if ((r4 instanceof GroupCreateUserCell) == false) goto L18;
        ((GroupCreateUserCell) r4).update(r1);
    L18:
        r3 = r3 + 1;
        goto L14
    L50:
        return;
    L10:
        if ((MessagesController.UPDATE_MASK_NAME & r1) != 0) goto L13;
        if ((MessagesController.UPDATE_MASK_STATUS & r1) != 0) goto L13;
        return;
    L49:
        return;
    L20:
        if (r19 != NotificationCenter.chatDidFailCreate) goto L29;
        this.reqId = 0;
        this.donePressed = false;
        showEditDoneProgress(false);
        EditTextEmoji r12 = this.editText;
        if (r12 == null) goto L24;
        r12.setEnabled(true);
    L24:
        GroupCreateFinalActivityDelegate r0 = this.delegate;
        if (r0 == null) goto L46;
        r0.didFailChatCreation();
        return;
    L46:
        return;
    L29:
        if (r19 != NotificationCenter.chatDidCreated) goto L47;
        this.reqId = 0;
        long r6 = ((Long) r21[0]).longValue();
        GroupCreateFinalActivityDelegate r13 = this.delegate;
        if (r13 == null) goto L33;
        r13.didFinishChatCreation(this, r6);
    L35:
        if (this.inputPhoto == null) goto L37;
    L40:
        getMessagesController().changeChatAvatar(r6, null, this.inputPhoto, this.inputVideo, this.inputEmojiMarkup, this.videoTimestamp, this.inputVideoPath, this.avatar, this.avatarBig, null);
        return;
    L37:
        if (this.inputVideo != null) goto L40;
        if (this.inputEmojiMarkup != null) goto L40;
        return;
    L33:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.closeChats, new Object[0]);
        Bundle r14 = new Bundle();
        r14.putLong("chat_id", r6);
        r14.putBoolean("just_created_chat", true);
        presentFragment(new ChatActivity(r14), true);
        goto L35
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didStartUpload(boolean r1, boolean r2) {
        RadialProgressView r0 = this.avatarProgressView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setProgress(0.0f);
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didUploadPhoto(TLRPC.InputFile r5, TLRPC.InputFile r6, double r7, String r9, TLRPC.PhotoSize r10, TLRPC.PhotoSize r11, boolean r12, TLRPC.VideoSize r13) {
        AndroidUtilities.runOnUIThread(new GroupCreateFinalActivity$$ExternalSyntheticLambda6(this, r5, r6, r13, r9, r7, r11, r10));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void dismissCurrentDialog() {
        if (this.imageUpdater.dismissCurrentDialog(this.visibleDialog) == false) goto L5;
        return;
    L5:
        super.dismissCurrentDialog();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean dismissDialogOnPause(Dialog r2) {
        if (this.imageUpdater.dismissDialogOnPause(r2) == true) goto L5;
        return false;
    L5:
        if (super.dismissDialogOnPause(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public String getInitialSearchString() {
        return this.editText.getText().toString();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        GroupCreateFinalActivity$$ExternalSyntheticLambda5 r8 = new GroupCreateFinalActivity$$ExternalSyntheticLambda5(this);
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        ActionBar r11 = this.actionBar;
        int r12 = ThemeDescription.FLAG_BACKGROUND;
        int r17 = Theme.key_windowBackgroundGray;
        r1.add(new ThemeDescription(r11, r12, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollActive));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollInactive));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        EditTextEmoji r10 = this.editText;
        int r112 = ThemeDescription.FLAG_TEXTCOLOR;
        int r16 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(r10, r112, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_groupcreate_hintText));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, Theme.key_groupcreate_cursor));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_windowBackgroundWhiteInputField));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_windowBackgroundWhiteInputFieldActivated));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{ShadowSectionCell.class}, null, null, null, r17));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"textView"}, null, null, null, Theme.key_groupcreate_sectionText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{GroupCreateUserCell.class}, new String[]{"statusTextView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{GroupCreateUserCell.class}, new String[]{"statusTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{GroupCreateUserCell.class}, null, Theme.avatarDrawables, r8, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r16));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean hideKeyboardOnShow() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r1, int r2, Intent r3) {
        super.onActivityResultFragment(r1, r2, r3);
        this.imageUpdater.onActivityResult(r1, r2, r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        EditTextEmoji r0 = this.editText;
        if (r0 == null) goto L11;
        if (r0.isPopupShowing() == false) goto L11;
        if (r2 == false) goto L12;
        this.editText.hidePopup(true);
        return false;
    L12:
        return false;
    L11:
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatDidCreated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatDidFailCreate);
        ImageUpdater r0 = new ImageUpdater(true, 2, true);
        this.imageUpdater = r0;
        r0.parentFragment = this;
        r0.setDelegate(this);
        long[] r02 = getArguments().getLongArray("result");
        int r1 = 0;
        if (r02 == null) goto L8;
        this.selectedContacts = new ArrayList(r02.length);
        int r3 = 0;
    L6:
        if (r3 >= r02.length) goto L8;
        this.selectedContacts.add(Long.valueOf(r02[r3]));
        r3 = r3 + 1;
    L8:
        ArrayList r03 = new ArrayList();
        int r32 = 0;
    L10:
        if (r32 >= this.selectedContacts.size()) goto L16;
        Long r4 = this.selectedContacts.get(r32);
        if (getMessagesController().getUser(r4) != null) goto L14;
        r03.add(r4);
    L14:
        r32 = r32 + 1;
        goto L10
    L16:
        if (r03.isEmpty() == true) goto L31;
        CountDownLatch r33 = new CountDownLatch(1);
        ArrayList r42 = new ArrayList();
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new GroupCreateFinalActivity$$ExternalSyntheticLambda0(this, r42, r03, r33));
        r33.await();     // Catch: Exception -> L20
    L23:
        if (r03.size() == r42.size()) goto L26;
        return false;
    L26:
        if (r42.isEmpty() == true) goto L30;
        int r04 = r42.size();
    L28:
        if (r1 >= r04) goto L31;
        Object r34 = r42.get(r1);
        r1 = r1 + 1;
        getMessagesController().putUser((TLRPC.User) r34, true);
        goto L28
    L30:
        return false;
    L20:
        e = move-exception;
        FileLog.e(e);
    L31:
        this.ttlPeriod = getUserConfig().getGlobalTTl() * 60;
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatDidCreated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatDidFailCreate);
        this.imageUpdater.clear();
        if (this.reqId == 0) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
    L5:
        EditTextEmoji r0 = this.editText;
        if (r0 == null) goto L8;
        r0.onDestroy();
    L8:
        AndroidUtilities.removeAdjustResize(getParentActivity(), this.classGuid);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        EditTextEmoji r0 = this.editText;
        if (r0 == null) goto L5;
        r0.onPause();
    L5:
        this.imageUpdater.onPause();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r1, String[] r2, int[] r3) {
        this.imageUpdater.onRequestPermissionsResultFragment(r1, r2, r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        EditTextEmoji r0 = this.editText;
        if (r0 == null) goto L5;
        r0.onResume();
    L5:
        GroupCreateAdapter r02 = this.adapter;
        if (r02 == null) goto L8;
        r02.notifyDataSetChanged();
    L8:
        this.imageUpdater.onResume();
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        if (r1 == false) goto L5;
        this.editText.openKeyboard();
        return;
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void onUploadProgressChanged(float r1) {
        RadialProgressView r0 = this.avatarProgressView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setProgress(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void restoreSelfArgs(Bundle r3) {
        ImageUpdater r0 = this.imageUpdater;
        if (r0 == null) goto L5;
        r0.currentPicturePath = r3.getString("path");
    L5:
        String r32 = r3.getString("nameTextView");
        if (r32 == null) goto L13;
        EditTextEmoji r02 = this.editText;
        if (r02 == null) goto L11;
        r02.setText(r32);
        return;
    L11:
        this.nameToSet = r32;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void saveSelfArgs(Bundle r3) {
        ImageUpdater r0 = this.imageUpdater;
        if (r0 == null) goto L7;
        String r02 = r0.currentPicturePath;
        if (r02 == null) goto L7;
        r3.putString("path", r02);
    L7:
        EditTextEmoji r2 = this.editText;
        if (r2 == null) goto L13;
        String r22 = r2.getText().toString();
        if (r22.length() == 0) goto L14;
        r3.putString("nameTextView", r22);
        return;
    L14:
        return;
    }

    public void setDelegate(GroupCreateFinalActivityDelegate r1) {
        this.delegate = r1;
    }
}
