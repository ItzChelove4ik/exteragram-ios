package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public class FiltersListBottomSheet extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    private ListAdapter adapter;
    private FiltersListBottomSheetDelegate delegate;
    private ArrayList<MessagesController.DialogFilter> dialogFilters;
    private final BaseFragment fragment;
    private boolean ignoreLayout;
    private RecyclerListView listView;
    private int scrollOffsetY;
    private final ArrayList<Long> selectedDialogs;
    private View shadow;
    private AnimatorSet shadowAnimation;
    private TextView titleTextView;

    /* renamed from: org.telegram.ui.Components.FiltersListBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        private boolean fullHeight;
        private RectF rect;
        private Boolean statusBarOpen;
        final /* synthetic */ FiltersListBottomSheet this$0;

        public AnonymousClass1(FiltersListBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new RectF();
        }

        private void updateLightStatusBar(boolean r7) {
            Boolean r0 = this.statusBarOpen;
            if (r0 != null) goto L5;
        L7:
            boolean r2 = false;
            if (AndroidUtilities.computePerceivedBrightness(FiltersListBottomSheet.access$1700(this.this$0, Theme.key_dialogBackground)) <= 0.721f) goto L10;
            boolean r02 = true;
        L12:
            if (AndroidUtilities.computePerceivedBrightness(Theme.blendOver(FiltersListBottomSheet.access$1800(this.this$0, Theme.key_actionBarDefault), 855638016)) <= 0.721f) goto L14;
            r2 = true;
        L14:
            this.statusBarOpen = Boolean.valueOf(r7);
            if (r7 == true) goto L18;
            r02 = r2;
        L18:
            AndroidUtilities.setLightStatusBar(this.this$0.getWindow(), r02);
            return;
        L10:
            r02 = false;
            goto L12
        L5:
            if (r0.booleanValue() != r7) goto L7;
        }

        @Override // android.view.View
        public void onDraw(Canvas r12) {
            int r0 = ((FiltersListBottomSheet.m10655$$Nest$fgetscrollOffsetY(this.this$0) - FiltersListBottomSheet.access$400(this.this$0)) - AndroidUtilities.dp(8.0f)) + AndroidUtilities.statusBarHeight;
            boolean r2 = false;
            if (this.fullHeight == false) goto L12;
            int r1 = FiltersListBottomSheet.access$500(this.this$0) + r0;
            int r4 = AndroidUtilities.statusBarHeight;
            if (r1 >= (r4 * 2)) goto L7;
            r0 = r0 - Math.min(r4, ((r4 * 2) - r0) - FiltersListBottomSheet.access$600(this.this$0));
            float r13 = 1.0f - Math.min(1.0f, (r1 * 2) / AndroidUtilities.statusBarHeight);
        L8:
            int r42 = FiltersListBottomSheet.access$700(this.this$0) + r0;
            int r5 = AndroidUtilities.statusBarHeight;
            if (r42 >= r5) goto L11;
            int r43 = Math.min(r5, (r5 - r0) - FiltersListBottomSheet.access$800(this.this$0));
        L13:
            FiltersListBottomSheet.access$900(this.this$0).setBounds(0, r0, getMeasuredWidth(), getMeasuredHeight());
            FiltersListBottomSheet.access$1000(this.this$0).draw(r12);
            if (r13 == 1.0f) goto L16;
            Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_dialogBackground));
            this.rect.set(FiltersListBottomSheet.access$1100(this.this$0), FiltersListBottomSheet.access$1200(this.this$0) + r0, getMeasuredWidth() - FiltersListBottomSheet.access$1300(this.this$0), (FiltersListBottomSheet.access$1400(this.this$0) + r0) + AndroidUtilities.dp(24.0f));
            r12.drawRoundRect(this.rect, AndroidUtilities.dp(12.0f) * r13, AndroidUtilities.dp(12.0f) * r13, Theme.dialogs_onlineCirclePaint);
        L16:
            if (r43 <= 0) goto L19;
            Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_dialogBackground));
            r12.drawRect(FiltersListBottomSheet.access$1500(this.this$0), AndroidUtilities.statusBarHeight - r43, getMeasuredWidth() - FiltersListBottomSheet.access$1600(this.this$0), AndroidUtilities.statusBarHeight, Theme.dialogs_onlineCirclePaint);
        L19:
            if (r43 <= (AndroidUtilities.statusBarHeight / 2)) goto L21;
            r2 = true;
        L21:
            updateLightStatusBar(r2);
            return;
        L11:
            r43 = 0;
            goto L13
        L7:
            r13 = 1.0f;
            goto L8
        L12:
            r43 = 0;
            r13 = 1.0f;
            goto L13
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L11;
            if (FiltersListBottomSheet.m10655$$Nest$fgetscrollOffsetY(this.this$0) == 0) goto L11;
            if (r3.getY() >= FiltersListBottomSheet.m10655$$Nest$fgetscrollOffsetY(this.this$0)) goto L11;
            this.this$0.dismiss();
            return true;
        L11:
            return super.onInterceptTouchEvent(r3);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            FiltersListBottomSheet.m10661$$Nest$mupdateLayout(this.this$0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r12, int r13) {
            int r132 = View.MeasureSpec.getSize(r13);
            boolean r1 = true;
            FiltersListBottomSheet.m10659$$Nest$fputignoreLayout(this.this$0, true);
            setPadding(FiltersListBottomSheet.access$000(this.this$0), AndroidUtilities.statusBarHeight, FiltersListBottomSheet.access$100(this.this$0), 0);
            FiltersListBottomSheet.m10659$$Nest$fputignoreLayout(this.this$0, false);
            int r2 = ((AndroidUtilities.dp(48.0f) + (AndroidUtilities.dp(48.0f) * FiltersListBottomSheet.m10651$$Nest$fgetadapter(this.this$0).getItemCount())) + FiltersListBottomSheet.access$200(this.this$0)) + AndroidUtilities.statusBarHeight;
            int r0 = r132 / 5;
            if (r2 >= (r0 * 3.2d)) goto L5;
            int r02 = 0;
        L6:
            if (r02 == 0) goto L9;
            if (r2 >= r132) goto L9;
            r02 = r02 - (r132 - r2);
        L9:
            if (r02 != 0) goto L12;
            r02 = FiltersListBottomSheet.access$300(this.this$0);
        L12:
            if (FiltersListBottomSheet.m10654$$Nest$fgetlistView(this.this$0).getPaddingTop() == r02) goto L14;
            FiltersListBottomSheet.m10659$$Nest$fputignoreLayout(this.this$0, true);
            FiltersListBottomSheet.m10654$$Nest$fgetlistView(this.this$0).setPadding(AndroidUtilities.dp(10.0f), r02, AndroidUtilities.dp(10.0f), 0);
            FiltersListBottomSheet.m10659$$Nest$fputignoreLayout(this.this$0, false);
        L14:
            if (r2 >= r132) goto L17;
            r1 = false;
        L17:
            this.fullHeight = r1;
            super.onMeasure(r12, View.MeasureSpec.makeMeasureSpec(Math.min(r2, r132), TLObject.FLAG_30));
            return;
        L5:
            r02 = r0 * 2;
            goto L6
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.isDismissed() == false) goto L5;
            return false;
        L5:
            if (super.onTouchEvent(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (FiltersListBottomSheet.m10653$$Nest$fgetignoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.FiltersListBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ FiltersListBottomSheet this$0;

        public AnonymousClass2(FiltersListBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (FiltersListBottomSheet.m10653$$Nest$fgetignoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.FiltersListBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ FiltersListBottomSheet this$0;

        public AnonymousClass3(FiltersListBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            FiltersListBottomSheet.m10661$$Nest$mupdateLayout(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.FiltersListBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ FiltersListBottomSheet this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass4(FiltersListBottomSheet r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (FiltersListBottomSheet.m10658$$Nest$fgetshadowAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (FiltersListBottomSheet.m10658$$Nest$fgetshadowAnimation(this.this$0).equals(r2) == false) goto L9;
            FiltersListBottomSheet.m10660$$Nest$fputshadowAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (FiltersListBottomSheet.m10658$$Nest$fgetshadowAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (FiltersListBottomSheet.m10658$$Nest$fgetshadowAnimation(this.this$0).equals(r2) == true) goto L7;
            return;
        L7:
            if (this.val$show == true) goto L9;
            FiltersListBottomSheet.m10657$$Nest$fgetshadow(this.this$0).setVisibility(4);
        L9:
            FiltersListBottomSheet.m10660$$Nest$fputshadowAnimation(this.this$0, null);
        }
    }

    public interface FiltersListBottomSheetDelegate {
        void didSelectFilter(MessagesController.DialogFilter r1, boolean r2);
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context context;
        final /* synthetic */ FiltersListBottomSheet this$0;

        public ListAdapter(FiltersListBottomSheet r1, Context r2) {
            this.this$0 = r1;
            this.context = r2;
        }

        public MessagesController.DialogFilter getItem(int r2) {
            if (r2 < FiltersListBottomSheet.m10652$$Nest$fgetdialogFilters(this.this$0).size()) goto L5;
            return null;
        L5:
            return (MessagesController.DialogFilter) FiltersListBottomSheet.m10652$$Nest$fgetdialogFilters(this.this$0).get(r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            int r1 = FiltersListBottomSheet.m10652$$Nest$fgetdialogFilters(this.this$0).size();
            if (r1 < 10) goto L5;
            return r1;
        L5:
            return r1 + 1;
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
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            BottomSheet.BottomSheetCell r72 = (BottomSheet.BottomSheetCell) r7.itemView;
            if (r8 >= FiltersListBottomSheet.m10652$$Nest$fgetdialogFilters(this.this$0).size()) goto L34;
            r72.getImageView().setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogIcon), PorterDuff.Mode.MULTIPLY));
            MessagesController.DialogFilter r82 = (MessagesController.DialogFilter) FiltersListBottomSheet.m10652$$Nest$fgetdialogFilters(this.this$0).get(r8);
            r72.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
            int r0 = r82.flags;
            if ((MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS & r0) != (MessagesController.DIALOG_FILTER_FLAG_CONTACTS | MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS)) goto L8;
            int r02 = R.drawable.msg_openprofile;
        L25:
            r72.setTextAndIcon(MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(r82.name, r72.getTextView().getPaint().getFontMetricsInt(), false), r82.entities, r72.getTextView().getPaint().getFontMetricsInt()), 0, new FolderDrawable(this.this$0.getContext(), r02, r82.color), false);
            r72.getTextView().setEmojiColor(Theme.getColor(Theme.key_featuredStickers_addButton, FiltersListBottomSheet.access$1900(this.this$0)));
            boolean r03 = true;
            int r2 = 0;
        L27:
            if (r2 >= FiltersListBottomSheet.m10656$$Nest$fgetselectedDialogs(this.this$0).size()) goto L32;
            if (r82.includesDialog(AccountInstance.getInstance(FiltersListBottomSheet.access$2000(this.this$0)), ((Long) FiltersListBottomSheet.m10656$$Nest$fgetselectedDialogs(this.this$0).get(r2)).longValue()) == true) goto L31;
            r03 = false;
        L31:
            r2 = r2 + 1;
            goto L27
        L32:
            r72.setChecked(r03);
            return;
        L8:
            if ((MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ & r0) == 0) goto L13;
            int r22 = MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS;
            if ((r0 & r22) != r22) goto L13;
            r02 = R.drawable.msg_markunread;
        L13:
            if ((MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS & r0) != MessagesController.DIALOG_FILTER_FLAG_CHANNELS) goto L16;
            r02 = R.drawable.msg_channel;
            goto L25
        L16:
            if ((MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS & r0) != MessagesController.DIALOG_FILTER_FLAG_GROUPS) goto L19;
            r02 = R.drawable.msg_groups;
            goto L25
        L19:
            if ((MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS & r0) != MessagesController.DIALOG_FILTER_FLAG_CONTACTS) goto L22;
            r02 = R.drawable.msg_contacts;
            goto L25
        L22:
            if ((r0 & MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS) != MessagesController.DIALOG_FILTER_FLAG_BOTS) goto L24;
            r02 = R.drawable.msg_bots;
            goto L25
        L24:
            r02 = R.drawable.msg_folders;
            goto L25
        L34:
            r72.setChecked(false);
            r72.getImageView().setColorFilter(null);
            Drawable r83 = this.context.getResources().getDrawable(R.drawable.poll_add_circle);
            Drawable r6 = this.context.getResources().getDrawable(R.drawable.poll_add_plus);
            int r1 = Theme.getColor(Theme.key_switchTrackChecked);
            PorterDuff.Mode r23 = PorterDuff.Mode.MULTIPLY;
            r83.setColorFilter(new PorterDuffColorFilter(r1, r23));
            r6.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_checkboxCheck), r23));
            CombinedDrawable r04 = new CombinedDrawable(r83, r6);
            r72.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
            r72.setTextAndIcon(LocaleController.getString(R.string.CreateNewFilter), r04);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            BottomSheet.BottomSheetCell r22 = new BottomSheet.BottomSheetCell(this.context, 0);
            r22.setBackground(null);
            r22.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r22);
        }
    }

    public static /* synthetic */ void $r8$lambda$J8m0cA55LaViNKwBsR4tzCbS44Y(View r1) {
        if ((r1 instanceof BottomSheet.BottomSheetCell) == false) goto L6;
        ((BottomSheet.BottomSheetCell) r1).getTextView().invalidate();
        return;
    L6:
        r1.invalidate();
    }

    public static /* synthetic */ void $r8$lambda$tPdlyW16oWmlYuls2T28FqvoppE(FiltersListBottomSheet r0, View r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m10651$$Nest$fgetadapter(FiltersListBottomSheet r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetdialogFilters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10652$$Nest$fgetdialogFilters(FiltersListBottomSheet r0) {
        return r0.dialogFilters;
    }

    /* renamed from: -$$Nest$fgetignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10653$$Nest$fgetignoreLayout(FiltersListBottomSheet r0) {
        return r0.ignoreLayout;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m10654$$Nest$fgetlistView(FiltersListBottomSheet r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10655$$Nest$fgetscrollOffsetY(FiltersListBottomSheet r0) {
        return r0.scrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetselectedDialogs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10656$$Nest$fgetselectedDialogs(FiltersListBottomSheet r0) {
        return r0.selectedDialogs;
    }

    /* renamed from: -$$Nest$fgetshadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m10657$$Nest$fgetshadow(FiltersListBottomSheet r0) {
        return r0.shadow;
    }

    /* renamed from: -$$Nest$fgetshadowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m10658$$Nest$fgetshadowAnimation(FiltersListBottomSheet r0) {
        return r0.shadowAnimation;
    }

    /* renamed from: -$$Nest$fputignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10659$$Nest$fputignoreLayout(FiltersListBottomSheet r0, boolean r1) {
        r0.ignoreLayout = r1;
    }

    /* renamed from: -$$Nest$fputshadowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10660$$Nest$fputshadowAnimation(FiltersListBottomSheet r0, AnimatorSet r1) {
        r0.shadowAnimation = r1;
    }

    /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10661$$Nest$mupdateLayout(FiltersListBottomSheet r0) {
        r0.updateLayout();
    }

    public FiltersListBottomSheet(BaseFragment r11, ArrayList<Long> r12) {
        super(r11.getParentActivity(), false);
        fixNavigationBar();
        this.selectedDialogs = r12;
        this.fragment = r11;
        this.dialogFilters = new ArrayList(r11.getMessagesController().dialogFilters);
        int r122 = 0;
    L4:
        if (r122 >= this.dialogFilters.size()) goto L9;
        if (this.dialogFilters.get(r122).isDefault() == false) goto L8;
        this.dialogFilters.remove(r122);
        r122 = r122 - 1;
    L8:
        r122 = r122 + 1;
        goto L4
    L9:
        Activity r112 = r11.getParentActivity();
        AnonymousClass1 r123 = new AnonymousClass1(this, r112);
        this.containerView = r123;
        r123.setWillNotDraw(false);
        ViewGroup r124 = this.containerView;
        int r0 = this.backgroundPaddingLeft;
        r124.setPadding(r0, 0, r0, 0);
        FrameLayout.LayoutParams r125 = new FrameLayout.LayoutParams(-1, AndroidUtilities.getShadowHeight(), 51);
        r125.topMargin = AndroidUtilities.dp(48.0f);
        View r02 = new View(r112);
        this.shadow = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_dialogShadowLine));
        this.shadow.setAlpha(0.0f);
        this.shadow.setVisibility(4);
        this.shadow.setTag(1);
        this.containerView.addView(this.shadow, r125);
        AnonymousClass2 r126 = new AnonymousClass2(this, r112);
        this.listView = r126;
        r126.setTag(14);
        this.listView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        RecyclerListView r127 = this.listView;
        ListAdapter r03 = new ListAdapter(this, r112);
        this.adapter = r03;
        r127.setAdapter(r03);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
        this.listView.setClipToPadding(false);
        this.listView.setGlowColor(Theme.getColor(Theme.key_dialogScrollGlow));
        this.listView.setOnScrollListener(new AnonymousClass3(this));
        this.listView.setOnItemClickListener(new FiltersListBottomSheet$$ExternalSyntheticLambda1(this));
        this.containerView.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 48.0f, 0.0f, 0.0f));
        TextView r128 = new TextView(r112);
        this.titleTextView = r128;
        r128.setLines(1);
        this.titleTextView.setSingleLine(true);
        this.titleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        this.titleTextView.setTextSize(1, 20.0f);
        this.titleTextView.setLinkTextColor(Theme.getColor(Theme.key_dialogTextLink));
        this.titleTextView.setHighlightColor(Theme.getColor(Theme.key_dialogLinkSelection));
        this.titleTextView.setEllipsize(TextUtils.TruncateAt.END);
        this.titleTextView.setPadding(AndroidUtilities.dp(24.0f), 0, AndroidUtilities.dp(24.0f), 0);
        this.titleTextView.setGravity(16);
        this.titleTextView.setText(LocaleController.getString(R.string.FilterChoose));
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        this.containerView.addView(this.titleTextView, LayoutHelper.createFrame(-1, 50.0f, 51, 0.0f, 0.0f, 40.0f, 0.0f));
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
    }

    public static /* synthetic */ int access$000(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$100(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ Drawable access$1000(FiltersListBottomSheet r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ int access$1100(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1200(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1300(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1400(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1500(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1600(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1700(FiltersListBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1800(FiltersListBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1900(FiltersListBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$200(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2000(FiltersListBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$400(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$500(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$600(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$700(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$800(FiltersListBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ Drawable access$900(FiltersListBottomSheet r0) {
        return r0.shadowDrawable;
    }

    public static ArrayList<MessagesController.DialogFilter> getCanAddDialogFilters(BaseFragment r2, Long r3) {
        ArrayList r0 = new ArrayList(1);
        r0.add(r3);
        return getCanAddDialogFilters(r2, r0);
    }

    public static ArrayList<MessagesController.DialogFilter> getCanAddDialogFilters(BaseFragment r6, ArrayList<Long> r7) {
        ArrayList<MessagesController.DialogFilter> r0 = new ArrayList();
        ArrayList<MessagesController.DialogFilter> r1 = r6.getMessagesController().dialogFilters;
        int r2 = r1.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L10;
        MessagesController.DialogFilter r4 = r1.get(r3);
        if (getDialogsCount(r6, r4, r7, true, true).isEmpty() == true) goto L9;
        if (r4.isDefault() == true) goto L9;
        r0.add(r4);
    L9:
        r3 = r3 + 1;
        goto L3
    L10:
        return r0;
    }

    public static ArrayList<Long> getDialogsCount(BaseFragment r7, MessagesController.DialogFilter r8, ArrayList<Long> r9, boolean r10, boolean r11) {
        ArrayList<Long> r0 = new ArrayList();
        int r1 = r9.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L23;
        long r3 = r9.get(r2).longValue();
        if (DialogObject.isEncryptedDialog(r3) == false) goto L11;
        TLRPC.EncryptedChat r32 = r7.getMessagesController().getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r3)));
        if (r32 == null) goto L22;
        r3 = r32.user_id;
        if (r0.contains(Long.valueOf(r3)) == false) goto L11;
    L22:
        r2 = r2 + 1;
    L11:
        if (r8 == null) goto L19;
        if (r10 == true) goto L14;
    L15:
        if (r10 == true) goto L19;
        if (r8.neverShow.contains(Long.valueOf(r3)) == false) goto L19;
    L14:
        if (r8.alwaysShow.contains(Long.valueOf(r3)) == true) goto L22;
    L19:
        r0.add(Long.valueOf(r3));
        if (r11 == false) goto L22;
    L23:
        return r0;
    }

    public static boolean hasCustomDialogFilters(BaseFragment r4) {
        ArrayList<MessagesController.DialogFilter> r42 = r4.getMessagesController().getDialogFilters();
        int r0 = r42.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r42.get(r2).isDefault() == false) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private /* synthetic */ void lambda$new$0(View r3, int r4) {
        FiltersListBottomSheetDelegate r0 = this.delegate;
        MessagesController.DialogFilter r42 = this.adapter.getItem(r4);
        if ((r3 instanceof BottomSheet.BottomSheetCell) == false) goto L5;
        boolean r32 = ((BottomSheet.BottomSheetCell) r3).isChecked();
    L6:
        r0.didSelectFilter(r42, r32);
        dismiss();
        return;
    L5:
        r32 = false;
        goto L6
    }

    private void runShadowAnimation(boolean r8) {
        if (r8 == true) goto L4;
    L5:
        if (r8 == false) goto L7;
        return;
    L7:
        if (this.shadow.getTag() != null) goto L25;
    L8:
        View r0 = this.shadow;
        if (r8 == false) goto L11;
        Integer r2 = null;
    L12:
        r0.setTag(r2);
        if (r8 == false) goto L15;
        this.shadow.setVisibility(0);
    L15:
        AnimatorSet r22 = this.shadowAnimation;
        if (r22 == null) goto L18;
        r22.cancel();
    L18:
        AnimatorSet r23 = new AnimatorSet();
        this.shadowAnimation = r23;
        View r3 = this.shadow;
        Property r4 = View.ALPHA;
        if (r8 == false) goto L21;
        float r5 = 1.0f;
    L22:
        r23.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, r4, new float[]{r5})});
        this.shadowAnimation.setDuration(150);
        this.shadowAnimation.addListener(new AnonymousClass4(this, r8));
        this.shadowAnimation.start();
        return;
    L21:
        r5 = 0.0f;
        goto L22
    L11:
        r2 = 1;
        goto L12
    L25:
        return;
    L4:
        if (this.shadow.getTag() != null) goto L8;
        goto L5
    }

    private void updateLayout() {
        int r0 = this.listView.getChildCount();
        RecyclerListView r1 = this.listView;
        if (r0 > 0) goto L6;
        int r02 = r1.getPaddingTop();
        this.scrollOffsetY = r02;
        r1.setTopGlowOffset(r02);
        this.titleTextView.setTranslationY(this.scrollOffsetY);
        this.shadow.setTranslationY(this.scrollOffsetY);
        this.containerView.invalidate();
        return;
    L6:
        int r03 = 0;
        View r12 = r1.getChildAt(0);
        RecyclerListView.Holder r2 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r12);
        int r13 = r12.getTop();
        if (r13 < 0) goto L12;
        if (r2 == null) goto L12;
        if (r2.getAdapterPosition() != 0) goto L12;
        runShadowAnimation(false);
        r03 = r13;
    L14:
        if (this.scrollOffsetY == r03) goto L17;
        RecyclerListView r14 = this.listView;
        this.scrollOffsetY = r03;
        r14.setTopGlowOffset(r03);
        this.titleTextView.setTranslationY(this.scrollOffsetY);
        this.shadow.setTranslationY(this.scrollOffsetY);
        this.containerView.invalidate();
        return;
    L17:
        return;
    L12:
        runShadowAnimation(true);
        goto L14
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.emojiLoaded) goto L6;
        AndroidUtilities.forEachViews(this.listView, new FiltersListBottomSheet$$ExternalSyntheticLambda0());
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
    }

    public void setDelegate(FiltersListBottomSheetDelegate r1) {
        this.delegate = r1;
    }
}
