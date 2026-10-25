package org.telegram.ui;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.exteragram.messenger.utils.ui.FolderIcons;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FolderBottomSheet;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.ProgressButton;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UndoView;
import org.telegram.ui.FilterCreateActivity;

/* loaded from: classes6.dex */
public class FiltersSetupActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private ListAdapter adapter;
    private int filtersSectionEnd;
    private int filtersSectionStart;
    private int filtersStartPosition;
    private int folderTagsPosition;
    private boolean highlightTags;
    private boolean ignoreUpdates;
    private ItemTouchHelper itemTouchHelper;
    private ArrayList<ItemInner> items;
    private RecyclerListView listView;
    private boolean loadedColors;
    private boolean loadingFiltersForColors;
    private ArrayList<ItemInner> oldItems;
    private boolean orderChanged;
    private int shiftDp;

    @Keep
    private int showTagsRow;
    private UndoView undoView;

    /* renamed from: org.telegram.ui.FiltersSetupActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ FiltersSetupActivity this$0;

        public AnonymousClass1(FiltersSetupActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.FiltersSetupActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ FiltersSetupActivity this$0;

        public static /* synthetic */ void $r8$lambda$YDP3sxVQFr1KA6uUoKNginyj3BE(AnonymousClass2 r0) {
            r0.lambda$onTouchEvent$0();
        }

        public AnonymousClass2(FiltersSetupActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$onTouchEvent$0() {
            this.this$0.getMessagesController().lockFiltersInternal();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent r4) {
            if (r4.getAction() != 1) goto L5;
        L6:
            AndroidUtilities.runOnUIThread(new FiltersSetupActivity$2$$ExternalSyntheticLambda0(this), 250);
        L8:
            return super.onTouchEvent(r4);
        L5:
            if (r4.getAction() != 3) goto L8;
            goto L6
        }
    }

    public class FilterCell extends FrameLayout {
        private final View colorImageView;
        private MessagesController.DialogFilter currentFilter;
        private int lastAppliedColor;
        private int lastColor;
        private final ImageView moveImageView;
        private ValueAnimator moveImageViewAnimator;
        private boolean needDivider;
        private final ImageView optionsImageView;
        float progressToLock;
        private final ImageView shareImageView;
        private boolean shareLoading;
        private final LoadingDrawable shareLoadingDrawable;
        private final SimpleTextView textView;
        final /* synthetic */ FiltersSetupActivity this$0;
        private final TextView valueTextView;

        /* renamed from: org.telegram.ui.FiltersSetupActivity$FilterCell$1, reason: invalid class name */
        public class AnonymousClass1 extends ImageView {
            final /* synthetic */ FilterCell this$1;
            final /* synthetic */ int val$stroke;
            final /* synthetic */ FiltersSetupActivity val$this$0;

            public AnonymousClass1(FilterCell r1, Context r2, FiltersSetupActivity r3, int r4) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                this.val$stroke = r4;
                super(r2);
            }

            @Override // android.widget.ImageView, android.view.View
            public void onDraw(Canvas r7) {
                super.onDraw(r7);
                if (FilterCell.m14850$$Nest$fgetshareLoading(this.this$1) == false) goto L6;
                LoadingDrawable r0 = FilterCell.m14851$$Nest$fgetshareLoadingDrawable(this.this$1);
                int r1 = this.val$stroke;
                r0.setBounds(r1 / 2, r1 / 2, getWidth() - (this.val$stroke / 2), getHeight() - (this.val$stroke / 2));
                FilterCell.m14851$$Nest$fgetshareLoadingDrawable(this.this$1).draw(r7);
                return;
            }

            @Override // android.widget.ImageView, android.view.View
            public boolean verifyDrawable(Drawable r2) {
                if (r2 != FilterCell.m14851$$Nest$fgetshareLoadingDrawable(this.this$1)) goto L5;
                return true;
            L5:
                if (super.verifyDrawable(r2) == true) goto L11;
                return false;
            L11:
                return true;
            }
        }

        public static /* synthetic */ void $r8$lambda$bp47cusjLx6_q_Ih8nUZugikkPc(FilterCell r0, ValueAnimator r1) {
            r0.lambda$setFilter$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$flI8t_wmbW3I4iOhSyWCha4DT7g(FilterCell r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$qMoVKZYaN5Ddubjbych5kpRFbNU(FilterCell r0) {
            r0.lambda$new$0();
        }

        /* renamed from: -$$Nest$fgetshareLoading, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m14850$$Nest$fgetshareLoading(FilterCell r0) {
            return r0.shareLoading;
        }

        /* renamed from: -$$Nest$fgetshareLoadingDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LoadingDrawable m14851$$Nest$fgetshareLoadingDrawable(FilterCell r0) {
            return r0.shareLoadingDrawable;
        }

        public FilterCell(FiltersSetupActivity r30, Context r31) {
            this.this$0 = r30;
            super(r31);
            this.lastColor = -2;
            this.lastAppliedColor = -1;
            this.shareLoading = false;
            setWillNotDraw(false);
            ImageView r4 = new ImageView(r31);
            this.moveImageView = r4;
            r4.setFocusable(false);
            ImageView.ScaleType r5 = ImageView.ScaleType.CENTER;
            r4.setScaleType(r5);
            r4.setImageResource(R.drawable.list_reorder);
            int r7 = Theme.key_stickers_menu;
            int r8 = Theme.getColor(r7);
            PorterDuff.Mode r9 = PorterDuff.Mode.MULTIPLY;
            r4.setColorFilter(new PorterDuffColorFilter(r8, r9));
            r4.setContentDescription(LocaleController.getString(R.string.FilterReorder));
            r4.setClickable(true);
            if (LocaleController.isRTL == false) goto L5;
            int r82 = 5;
        L6:
            addView(r4, LayoutHelper.createFrame(48, 48.0f, r82 | 16, 7.0f, 0.0f, 6.0f, 0.0f));
            View r42 = new View(r31);
            this.colorImageView = r42;
            if (LocaleController.isRTL == false) goto L9;
            int r83 = 5;
        L10:
            addView(r42, LayoutHelper.createFrame(20, 20.0f, r83 | 16, 22.0f, 0.0f, 22.0f, 0.0f));
            SimpleTextView r43 = new SimpleTextView(r31);
            this.textView = r43;
            r43.setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f));
            r43.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            r43.setTextSize(16);
            r43.setMaxLines(1);
            if (LocaleController.isRTL == false) goto L13;
            int r84 = 5;
        L14:
            r43.setGravity(r84 | 16);
            Drawable r85 = ContextCompat.getDrawable(getContext(), R.drawable.other_lockedfolders2);
            r85.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r7), r9));
            r43.setRightDrawable(r85);
            r43.setEmojiColor(Theme.getColor(Theme.key_featuredStickers_addButton, FiltersSetupActivity.access$000(r30)));
            boolean r86 = LocaleController.isRTL;
            if (r86 == false) goto L17;
            int r13 = 5;
        L18:
            int r16 = r13 | 48;
            if (r86 == false) goto L21;
            float r17 = 80.0f;
        L22:
            if (r86 == false) goto L24;
            float r19 = 64.0f;
        L25:
            addView(r43, LayoutHelper.createFrame(-1, -2.0f, r16, r17, 10.0f, r19, 0.0f));
            TextView r44 = new TextView(r31);
            this.valueTextView = r44;
            r44.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
            r44.setTextSize(1, 13.0f);
            r44.setTypeface(AndroidUtilities.regular());
            if (LocaleController.isRTL == false) goto L28;
            int r87 = 5;
        L29:
            r44.setGravity(r87);
            r44.setLines(1);
            r44.setMaxLines(1);
            r44.setSingleLine(true);
            r44.setPadding(0, 0, 0, 0);
            r44.setEllipsize(TextUtils.TruncateAt.END);
            boolean r88 = LocaleController.isRTL;
            if (r88 == false) goto L32;
            int r14 = 5;
        L33:
            int r24 = r14 | 48;
            if (r88 == false) goto L36;
            float r25 = 80.0f;
        L37:
            if (r88 == false) goto L39;
            float r27 = 64.0f;
        L40:
            addView(r44, LayoutHelper.createFrame(-2, -2.0f, r24, r25, 35.0f, r27, 0.0f));
            r44.setVisibility(8);
            LoadingDrawable r45 = new LoadingDrawable();
            this.shareLoadingDrawable = r45;
            r45.setAppearByGradient(true);
            r45.setGradientScale(2.0f);
            int r6 = Theme.key_listSelector;
            int r132 = Theme.getColor(r6);
            r45.setColors(Theme.multAlpha(r132, 0.4f), Theme.multAlpha(r132, 1.0f), Theme.multAlpha(r132, 0.9f), Theme.multAlpha(r132, 1.7f));
            int r10 = AndroidUtilities.dp(1.0f);
            r45.strokePaint.setStrokeWidth(r10);
            r45.setRadiiDp(40.0f);
            AnonymousClass1 r11 = new AnonymousClass1(this, r31, r30, r10);
            this.shareImageView = r11;
            r45.setCallback(r11);
            r11.setFocusable(false);
            r11.setScaleType(r5);
            r11.setBackground(Theme.createSelectorDrawable(r132));
            r11.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r7), r9));
            r11.setContentDescription(LocaleController.getString(R.string.FilterShare));
            r11.setVisibility(8);
            r11.setImageResource(R.drawable.msg_link_folder);
            r11.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r7), r9));
            boolean r1 = LocaleController.isRTL;
            if (r1 == false) goto L43;
            int r46 = 3;
        L44:
            int r21 = r46 | 16;
            if (r1 == false) goto L47;
            float r22 = 52.0f;
        L48:
            if (r1 == false) goto L50;
            float r242 = 6.0f;
        L51:
            addView(r11, LayoutHelper.createFrame(40, 40.0f, r21, r22, 0.0f, r242, 0.0f));
            r11.setOnClickListener(new FiltersSetupActivity$FilterCell$$ExternalSyntheticLambda0(this));
            ImageView r12 = new ImageView(r31);
            this.optionsImageView = r12;
            r12.setFocusable(false);
            r12.setScaleType(r5);
            r12.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(r6)));
            r12.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r7), r9));
            r12.setImageResource(R.drawable.msg_actions);
            r12.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
            if (LocaleController.isRTL == false) goto L54;
            int r102 = 3;
        L55:
            addView(r12, LayoutHelper.createFrame(40, 40.0f, r102 | 16, 6.0f, 0.0f, 6.0f, 0.0f));
            return;
        L54:
            r102 = 5;
            goto L55
        L50:
            r242 = 52.0f;
            goto L51
        L47:
            r22 = 6.0f;
            goto L48
        L43:
            r46 = 5;
            goto L44
        L39:
            r27 = 80.0f;
            goto L40
        L36:
            r25 = 64.0f;
            goto L37
        L32:
            r14 = 3;
            goto L33
        L28:
            r87 = 3;
            goto L29
        L24:
            r19 = 80.0f;
            goto L25
        L21:
            r17 = 64.0f;
            goto L22
        L17:
            r13 = 3;
            goto L18
        L13:
            r84 = 3;
            goto L14
        L9:
            r83 = 3;
            goto L10
        L5:
            r82 = 3;
            goto L6
        }

        private /* synthetic */ void lambda$new$0() {
            this.shareLoadingDrawable.disappear();
            this.shareImageView.invalidate();
            FiltersSetupActivity.m14849$$Nest$mupdateRows(this.this$0, true);
        }

        private /* synthetic */ void lambda$new$1(View r3) {
            if (this.shareLoading == false) goto L7;
            if (this.shareLoadingDrawable.isDisappeared() == true) goto L7;
            return;
        L7:
            if (this.currentFilter == null) goto L11;
            this.shareLoading = true;
            this.shareLoadingDrawable.reset();
            this.shareLoadingDrawable.resetDisappear();
            this.shareImageView.invalidate();
            FilterCreateActivity.FilterInvitesBottomSheet.show(this.this$0, this.currentFilter, new FiltersSetupActivity$FilterCell$$ExternalSyntheticLambda2(this));
            return;
        }

        private /* synthetic */ void lambda$setFilter$2(ValueAnimator r4) {
            float r42 = ((Float) r4.getAnimatedValue()).floatValue();
            this.moveImageView.setAlpha(r42);
            float r2 = (r42 * 0.5f) + 0.5f;
            this.moveImageView.setScaleX(r2);
            this.moveImageView.setScaleY(r2);
            float r22 = 1.0f - r42;
            this.colorImageView.setAlpha(r22);
            float r23 = (r22 * 0.5f) + 0.5f;
            this.colorImageView.setScaleX(r23);
            this.colorImageView.setScaleY(r23);
        }

        public MessagesController.DialogFilter getCurrentFilter() {
            return this.currentFilter;
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            if (this.needDivider == true) goto L5;
        L13:
            MessagesController.DialogFilter r102 = this.currentFilter;
            if (r102 == null) goto L24;
            boolean r103 = r102.locked;
            if (r103 == false) goto L20;
            float r3 = this.progressToLock;
            if (r3 == 1.0f) goto L20;
            this.progressToLock = r3 + 0.10666667f;
            invalidate();
        L20:
            if (r103 == true) goto L24;
            float r104 = this.progressToLock;
            if (r104 == 0.0f) goto L24;
            this.progressToLock = r104 - 0.10666667f;
            invalidate();
        L24:
            float r105 = Utilities.clamp(this.progressToLock, 1.0f, 0.0f);
            this.progressToLock = r105;
            this.textView.setRightDrawableScale(r105);
            this.textView.invalidate();
            return;
        L5:
            if (LocaleController.isRTL == false) goto L7;
            float r4 = 0.0f;
        L8:
            float r5 = getMeasuredHeight() - 1;
            int r0 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L11;
            int r2 = AndroidUtilities.dp(62.0f);
        L12:
            r10.drawLine(r4, r5, r0 - r2, getMeasuredHeight() - 1, Theme.dividerPaint);
            goto L13
        L11:
            r2 = 0;
            goto L12
        L7:
            r4 = AndroidUtilities.dp(62.0f);
            goto L8
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
        }

        public void setFilter(MessagesController.DialogFilter r12, boolean r13, int r14) {
            MessagesController.DialogFilter r0 = this.currentFilter;
            int r1 = -1;
            if (r0 != null) goto L5;
            int r02 = -1;
        L6:
            this.currentFilter = r12;
            if (r12 != null) goto L9;
            int r2 = -1;
        L11:
            if (r02 == r2) goto L13;
            boolean r5 = true;
        L15:
            if (this.this$0.getMessagesController().folderTags == false) goto L17;
            r1 = r12.color;
        L17:
            if (r1 >= 0) goto L19;
        L21:
            float r7 = 0.0f;
            if (r1 == this.lastColor) goto L63;
            ValueAnimator r6 = this.moveImageViewAnimator;
            if (r6 == null) goto L26;
            r6.cancel();
        L26:
            ImageView r62 = this.moveImageView;
            if (r02 != r2) goto L38;
            float r03 = r62.getAlpha();
            if (r1 < 0) goto L31;
            float r22 = 0.0f;
        L32:
            ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{r03, r22});
            this.moveImageViewAnimator = r04;
            r04.addUpdateListener(new FiltersSetupActivity$FilterCell$$ExternalSyntheticLambda1(this));
            this.moveImageViewAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.moveImageViewAnimator.setDuration(340);
            ValueAnimator r05 = this.moveImageViewAnimator;
            FiltersSetupActivity r23 = this.this$0;
            if (r1 < 0) goto L36;
            int r142 = Math.max(0, FiltersSetupActivity.m14842$$Nest$fgetfiltersSectionEnd(r23) - r14);
        L37:
            r05.setStartDelay(r142 * 27);
            this.moveImageViewAnimator.start();
        L62:
            this.lastColor = r1;
            goto L63
        L36:
            r142 = Math.max(0, r14 - FiltersSetupActivity.m14843$$Nest$fgetfiltersSectionStart(r23));
            goto L37
        L31:
            r22 = 1.0f;
            goto L32
        L38:
            float r143 = 0.5f;
            if (r1 < 0) goto L41;
            float r06 = 0.5f;
        L42:
            r62.setScaleX(r06);
            ImageView r07 = this.moveImageView;
            if (r1 < 0) goto L45;
            float r24 = 0.5f;
        L46:
            r07.setScaleY(r24);
            ImageView r08 = this.moveImageView;
            if (r1 < 0) goto L49;
            float r25 = 0.0f;
        L50:
            r08.setAlpha(r25);
            View r09 = this.colorImageView;
            if (r1 < 0) goto L53;
            float r26 = 1.0f;
        L54:
            r09.setScaleX(r26);
            View r010 = this.colorImageView;
            if (r1 < 0) goto L57;
            r143 = 1.0f;
        L57:
            r010.setScaleY(r143);
            View r144 = this.colorImageView;
            if (r1 < 0) goto L60;
            float r011 = 1.0f;
        L61:
            r144.setAlpha(r011);
            goto L62
        L60:
            r011 = 0.0f;
            goto L61
        L53:
            r26 = 0.5f;
            goto L54
        L49:
            r25 = 1.0f;
            goto L50
        L45:
            r24 = 1.0f;
            goto L46
        L41:
            r06 = 1.0f;
        L63:
            ImageView r145 = this.shareImageView;
            if (r12.isChatlist() == false) goto L66;
            int r012 = 0;
        L67:
            r145.setVisibility(r012);
            StringBuilder r146 = new StringBuilder();
            if (r12.isDefault() == true) goto L102;
            int r013 = r12.flags;
            int r3 = MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS;
            if ((r013 & r3) == r3) goto L102;
            if ((r013 & MessagesController.DIALOG_FILTER_FLAG_CONTACTS) == 0) goto L79;
            if (r146.length() == 0) goto L77;
            r146.append(", ");
        L77:
            r146.append(LocaleController.getString(R.string.FilterContacts));
        L79:
            if ((r12.flags & MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS) == 0) goto L85;
            if (r146.length() == 0) goto L83;
            r146.append(", ");
        L83:
            r146.append(LocaleController.getString(R.string.FilterNonContacts));
        L85:
            if ((r12.flags & MessagesController.DIALOG_FILTER_FLAG_GROUPS) == 0) goto L91;
            if (r146.length() == 0) goto L89;
            r146.append(", ");
        L89:
            r146.append(LocaleController.getString(R.string.FilterGroups));
        L91:
            if ((r12.flags & MessagesController.DIALOG_FILTER_FLAG_CHANNELS) == 0) goto L97;
            if (r146.length() == 0) goto L95;
            r146.append(", ");
        L95:
            r146.append(LocaleController.getString(R.string.FilterChannels));
        L97:
            if ((r12.flags & MessagesController.DIALOG_FILTER_FLAG_BOTS) == 0) goto L104;
            if (r146.length() == 0) goto L101;
            r146.append(", ");
        L101:
            r146.append(LocaleController.getString(R.string.FilterBots));
        L104:
            if (r12.alwaysShow.isEmpty() == false) goto L108;
            if (r12.neverShow.isEmpty() == false) goto L108;
        L112:
            if (r146.length() != 0) goto L114;
            r146.append(LocaleController.getString(R.string.FilterNoChats));
        L114:
            String r014 = r12.name;
            if (r12.isDefault() == false) goto L117;
            r014 = LocaleController.getString(R.string.FilterAllChats);
        L117:
            if (r5 == false) goto L119;
        L122:
            Spannable r015 = MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(r014, this.textView.getPaint().getFontMetricsInt(), false), r12.entities, this.textView.getPaint().getFontMetricsInt());
            SimpleTextView r27 = this.textView;
            if (r12.title_noanimate == false) goto L125;
            int r32 = 26;
        L126:
            r27.setEmojiCacheType(r32);
            this.textView.setText(r015);
            this.valueTextView.setText(r146);
            this.needDivider = r13;
            boolean r122 = r12.isDefault();
            ImageView r132 = this.optionsImageView;
            if (r122 == false) goto L129;
            r132.setVisibility(8);
        L130:
            invalidate();
            return;
        L129:
            r132.setVisibility(0);
            goto L130
        L125:
            r32 = 0;
            goto L126
        L119:
            if (this.currentFilter.locked == false) goto L121;
            r7 = 1.0f;
        L121:
            this.progressToLock = r7;
        L108:
            if (r146.length() == 0) goto L110;
            r146.append(", ");
        L110:
            r146.append(LocaleController.formatPluralString("Exception", r12.alwaysShow.size() + r12.neverShow.size(), new Object[0]));
        L102:
            r146.append(LocaleController.getString(R.string.FilterAllChats));
            goto L104
        L66:
            r012 = 8;
            goto L67
        L19:
            if (r12.color == this.lastAppliedColor) goto L21;
            View r63 = this.colorImageView;
            int r72 = AndroidUtilities.dp(22.0f);
            FiltersSetupActivity r8 = this.this$0;
            int[] r9 = Theme.keys_avatar_nameInMessage;
            this.lastAppliedColor = r1;
            r63.setBackground(Theme.createCircleDrawable(r72, r8.getThemedColor(r9[r1 % r9.length])));
            goto L21
        L13:
            r5 = false;
            goto L15
        L9:
            r2 = r12.id;
            goto L11
        L5:
            r02 = r0.id;
            goto L6
        }

        public void setOnOptionsClick(View.OnClickListener r1) {
            this.optionsImageView.setOnClickListener(r1);
        }

        @SuppressLint({"ClickableViewAccessibility"})
        public void setOnReorderButtonTouchListener(View.OnTouchListener r1) {
            this.moveImageView.setOnTouchListener(r1);
        }
    }

    public static class HintInnerCell extends FrameLayout {
        private RLottieImageView imageView;
        private TextView messageTextView;

        public static /* synthetic */ void $r8$lambda$i6MxLz_OawSBUB7sVkzsa4mPQ7Q(HintInnerCell r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public HintInnerCell(Context r8, int r9, CharSequence r10) {
            super(r8);
            RLottieImageView r0 = new RLottieImageView(r8);
            this.imageView = r0;
            r0.setAnimation(r9, 90, 90);
            this.imageView.setScaleType(ImageView.ScaleType.CENTER);
            this.imageView.playAnimation();
            this.imageView.setImportantForAccessibility(2);
            addView(this.imageView, LayoutHelper.createFrame(90, 90.0f, 49, 0.0f, 14.0f, 0.0f, 0.0f));
            this.imageView.setOnClickListener(new FiltersSetupActivity$HintInnerCell$$ExternalSyntheticLambda0(this));
            TextView r92 = new TextView(r8);
            this.messageTextView = r92;
            r92.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
            this.messageTextView.setTextSize(1, 14.0f);
            this.messageTextView.setTypeface(AndroidUtilities.regular());
            this.messageTextView.setGravity(17);
            this.messageTextView.setText(r10);
            addView(this.messageTextView, LayoutHelper.createFrame(-1, -2.0f, 49, 40.0f, 121.0f, 40.0f, 24.0f));
        }

        private /* synthetic */ void lambda$new$0(View r2) {
            if (this.imageView.isPlaying() == true) goto L6;
            this.imageView.setProgress(0.0f);
            this.imageView.playAnimation();
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }
    }

    public static class ItemInner extends AdapterWithDiffUtils.Item {
        MessagesController.DialogFilter filter;
        TLRPC.TL_dialogFilterSuggested suggested;
        CharSequence text;

        public ItemInner(int r2) {
            super(r2, false);
        }

        public static ItemInner asButton(CharSequence r2) {
            ItemInner r0 = new ItemInner(4);
            r0.text = r2;
            return r0;
        }

        public static ItemInner asCheck(CharSequence r2) {
            ItemInner r0 = new ItemInner(6);
            r0.text = r2;
            return r0;
        }

        public static ItemInner asFilter(MessagesController.DialogFilter r2) {
            ItemInner r0 = new ItemInner(2);
            r0.filter = r2;
            return r0;
        }

        public static ItemInner asHeader(CharSequence r2) {
            ItemInner r0 = new ItemInner(0);
            r0.text = r2;
            return r0;
        }

        public static ItemInner asHint() {
            return new ItemInner(1);
        }

        public static ItemInner asShadow(CharSequence r2) {
            ItemInner r0 = new ItemInner(3);
            r0.text = r2;
            return r0;
        }

        public static ItemInner asSuggested(TLRPC.TL_dialogFilterSuggested r2) {
            ItemInner r0 = new ItemInner(5);
            r0.suggested = r2;
            return r0;
        }

        public boolean equals(Object r8) {
            if (r8 != this) goto L6;
            return true;
        L6:
            if ((r8 instanceof ItemInner) == true) goto L8;
            return false;
        L8:
            ItemInner r82 = (ItemInner) r8;
            int r1 = r82.viewType;
            int r3 = this.viewType;
            if (r1 == r3) goto L11;
            return false;
        L11:
            if (r3 == 0) goto L19;
            if (r3 == 4) goto L19;
            if (r3 == 3) goto L19;
            if (r3 == 6) goto L19;
        L21:
            int r12 = this.viewType;
            if (r12 != 2) goto L38;
            MessagesController.DialogFilter r32 = this.filter;
            if (r32 != null) goto L26;
            boolean r4 = true;
        L27:
            MessagesController.DialogFilter r5 = r82.filter;
            if (r5 != null) goto L30;
            boolean r6 = true;
        L31:
            if (r4 == r6) goto L33;
            return false;
        L33:
            if (r32 == null) goto L38;
            if (r32.id == r5.id) goto L38;
            return false;
        L30:
            r6 = false;
            goto L31
        L26:
            r4 = false;
        L38:
            if (r12 != 5) goto L53;
            TLRPC.TL_dialogFilterSuggested r7 = this.suggested;
            if (r7 != null) goto L42;
            boolean r13 = true;
        L43:
            TLRPC.TL_dialogFilterSuggested r83 = r82.suggested;
            if (r83 != null) goto L46;
            boolean r33 = true;
        L47:
            if (r13 == r33) goto L49;
            return false;
        L49:
            if (r7 == null) goto L53;
            if (r7.filter.id == r83.filter.id) goto L53;
            return false;
        L46:
            r33 = false;
            goto L47
        L42:
            r13 = false;
        L53:
            return true;
        L19:
            if (TextUtils.equals(this.text, r82.text) == true) goto L21;
            return false;
        }
    }

    public class ListAdapter extends AdapterWithDiffUtils {
        private Context mContext;
        final /* synthetic */ FiltersSetupActivity this$0;

        /* renamed from: $r8$lambda$-ieVLR8K2b0B8FXw7X8n5CqRBiA, reason: not valid java name */
        public static /* synthetic */ void m14852$r8$lambda$ieVLR8K2b0B8FXw7X8n5CqRBiA(ListAdapter r0, MessagesController.DialogFilter r1) {
            r0.lambda$onCreateViewHolder$6(r1);
        }

        public static /* synthetic */ void $r8$lambda$3XX99aLZGFyAC85BrQhbbav2fko(ListAdapter r0, Boolean r1) {
            r0.lambda$onCreateViewHolder$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$LIgIKwvacfGv0QXo2WmoHNt96mU(ListAdapter r0, AlertDialog r1, MessagesController.DialogFilter r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$onCreateViewHolder$4(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$PT3Nx6AhsOH5GNUpY3_DEfOeK1w(ListAdapter r0, SuggestedFilterCell r1, View r2) {
            r0.lambda$onCreateViewHolder$9(r1, r2);
        }

        public static /* synthetic */ boolean $r8$lambda$TCLinH4AJH5vAtLWouTVVa8ZM4g(ListAdapter r0, FilterCell r1, View r2, MotionEvent r3) {
            return r0.lambda$onCreateViewHolder$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$WmYPdjmInPp0h2fqc_0aXBqQa1Y(ListAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$7(r1);
        }

        /* renamed from: $r8$lambda$djz_RjLYx45PLX5j-Sccs5FKTaI, reason: not valid java name */
        public static /* synthetic */ void m14853$r8$lambda$djz_RjLYx45PLX5jSccs5FKTaI(ListAdapter r0, AlertDialog r1, MessagesController.DialogFilter r2) {
            r0.lambda$onCreateViewHolder$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$ks0hB8fUWLURTAr8gwGLUMJfKPE(ListAdapter r0, MessagesController.DialogFilter r1) {
            r0.lambda$onCreateViewHolder$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$nyUJlsLNvrvTNxxQQiN64_YW8BQ(ListAdapter r0, TLRPC.TL_dialogFilterSuggested r1) {
            r0.lambda$onCreateViewHolder$8(r1);
        }

        public static /* synthetic */ void $r8$lambda$xkfU7xJgcUNjA_GpwPyJvGaQC6s(ListAdapter r0, MessagesController.DialogFilter r1, AlertDialog r2, int r3) {
            r0.lambda$onCreateViewHolder$5(r1, r2, r3);
        }

        public ListAdapter(FiltersSetupActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ boolean lambda$onCreateViewHolder$0(FilterCell r1, View r2, MotionEvent r3) {
            if (r3.getAction() != 0) goto L7;
            FiltersSetupActivity.m14845$$Nest$fgetitemTouchHelper(this.this$0).startDrag(FiltersSetupActivity.m14847$$Nest$fgetlistView(this.this$0).getChildViewHolder(r1));
            return false;
        L7:
            return false;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$1(MessagesController.DialogFilter r9) {
            boolean r0 = r9.locked;
            FiltersSetupActivity r1 = this.this$0;
            if (r0 == false) goto L6;
            FiltersSetupActivity r3 = this.this$0;
            r1.showDialog(new LimitReachedBottomSheet(r3, this.mContext, 3, FiltersSetupActivity.access$100(r3), null));
            return;
        L6:
            r1.presentFragment(new FilterCreateActivity(r9));
        }

        private /* synthetic */ void lambda$onCreateViewHolder$2(Boolean r1) {
            FiltersSetupActivity.m14849$$Nest$mupdateRows(this.this$0, true);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$3(AlertDialog r1, MessagesController.DialogFilter r2) {
            if (r1 != null) goto L9;
        L7:
            this.this$0.getMessagesController().removeFilter(r2);
            this.this$0.getMessagesStorage().deleteDialogFilter(r2);
            return;
        L9:
            r1.dismiss();     // Catch: Exception -> L5
        L5:
            e = move-exception;
            FileLog.e(e);
            goto L7
        }

        private /* synthetic */ void lambda$onCreateViewHolder$4(AlertDialog r1, MessagesController.DialogFilter r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda9(this, r1, r2));
        }

        private /* synthetic */ void lambda$onCreateViewHolder$5(MessagesController.DialogFilter r3, AlertDialog r4, int r5) {
            if (this.this$0.getParentActivity() == null) goto L5;
            AlertDialog r42 = new AlertDialog(this.this$0.getParentActivity(), 3);
            r42.setCanCancel(false);
            r42.show();
        L6:
            TLRPC.TL_messages_updateDialogFilter r52 = new TLRPC.TL_messages_updateDialogFilter();
            r52.id = r3.id;
            this.this$0.getConnectionsManager().sendRequest(r52, new FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda8(this, r42, r3));
            return;
        L5:
            r42 = null;
            goto L6
        }

        private /* synthetic */ void lambda$onCreateViewHolder$6(MessagesController.DialogFilter r4) {
            boolean r0 = r4.isChatlist();
            FiltersSetupActivity r1 = this.this$0;
            if (r0 == false) goto L6;
            FolderBottomSheet.showForDeletion(r1, r4.id, new FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda6(this));
            return;
        L6:
            AlertDialog.Builder r02 = new AlertDialog.Builder(r1.getParentActivity());
            r02.setTitle(LocaleController.getString(R.string.FilterDelete));
            r02.setMessage(LocaleController.getString(R.string.FilterDeleteAlert));
            r02.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            r02.setPositiveButton(LocaleController.getString(R.string.Delete), new FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda7(this, r4));
            AlertDialog r42 = r02.create();
            this.this$0.showDialog(r42);
            TextView r3 = (TextView) r42.getButton(-1);
            if (r3 == null) goto L10;
            r3.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$7(View r6) {
            FilterCell r62 = (FilterCell) r6.getParent();
            MessagesController.DialogFilter r0 = r62.getCurrentFilter();
            ItemOptions r1 = ItemOptions.makeOptions(this.this$0, r62);
            r1.add(R.drawable.msg_edit, LocaleController.getString(R.string.FilterEditItem), new FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda3(this, r0));
            r1.add(R.drawable.msg_delete, LocaleController.getString(R.string.FilterDeleteItem), true, new FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda4(this, r0));
            if (LocaleController.isRTL == false) goto L5;
            r1.setGravity(3);
        L5:
            r1.setScrimViewBackground(FiltersSetupActivity.m14847$$Nest$fgetlistView(this.this$0).getClipBackground(r62));
            r1.show();
        }

        private /* synthetic */ void lambda$onCreateViewHolder$8(TLRPC.TL_dialogFilterSuggested r2) {
            this.this$0.getMessagesController().suggestedFilters.remove(r2);
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.dialogFiltersUpdated, new Object[0]);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$9(SuggestedFilterCell r20, View r21) {
            TLRPC.TL_dialogFilterSuggested r1 = r20.getSuggestedFilter();
            MessagesController.DialogFilter r2 = new MessagesController.DialogFilter();
            TLRPC.TL_textWithEntities r3 = r1.filter.title;
            r2.name = r3.text;
            r2.entities = r3.entities;
            r2.id = 2;
        L4:
            if (this.this$0.getMessagesController().dialogFiltersById.get(r2.id) == null) goto L6;
            r2.id++;
            goto L4
        L6:
            r2.order = this.this$0.getMessagesController().getDialogFilters().size();
            r2.unreadCount = -1;
            r2.pendingUnreadCount = -1;
            int r5 = 0;
        L7:
            if (r5 >= 2) goto L26;
            TLRPC.DialogFilter r6 = r1.filter;
            if (r5 != 0) goto L11;
            ArrayList<TLRPC.InputPeer> r62 = r6.include_peers;
        L12:
            if (r5 != 0) goto L14;
            ArrayList<Long> r7 = r2.alwaysShow;
        L15:
            int r8 = r62.size();
            int r9 = 0;
        L16:
            if (r9 >= r8) goto L25;
            TLRPC.InputPeer r10 = r62.get(r9);
            long r11 = r10.user_id;
            if (r11 != 0) goto L24;
            long r112 = r10.chat_id;
            if (r112 == 0) goto L23;
            r11 = -r112;
            goto L24
        L23:
            r11 = -r10.channel_id;
        L24:
            r7.add(Long.valueOf(r11));
            r9 = r9 + 1;
            goto L16
        L25:
            r5 = r5 + 1;
            goto L7
        L14:
            r7 = r2.neverShow;
            goto L15
        L11:
            r62 = r6.exclude_peers;
            goto L12
        L26:
            TLRPC.DialogFilter r32 = r1.filter;
            if (r32.groups == false) goto L30;
            r2.flags |= MessagesController.DIALOG_FILTER_FLAG_GROUPS;
        L30:
            if (r32.bots == false) goto L33;
            r2.flags |= MessagesController.DIALOG_FILTER_FLAG_BOTS;
        L33:
            if (r32.contacts == false) goto L36;
            r2.flags |= MessagesController.DIALOG_FILTER_FLAG_CONTACTS;
        L36:
            if (r32.non_contacts == false) goto L39;
            r2.flags |= MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS;
        L39:
            if (r32.broadcasts == false) goto L42;
            r2.flags |= MessagesController.DIALOG_FILTER_FLAG_CHANNELS;
        L42:
            if (r32.exclude_archived == false) goto L45;
            r2.flags |= MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED;
        L45:
            if (r32.exclude_read == false) goto L48;
            r2.flags |= MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ;
        L48:
            if (r32.exclude_muted == false) goto L51;
            r2.flags |= MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_MUTED;
        L51:
            if (TextUtils.isEmpty(r32.emoticon) == false) goto L54;
            String r33 = FolderIcons.getEmoticonFromFlags(r2.flags).second;
        L53:
            String r82 = r33;
            r2.emoticon = r82;
            FilterCreateActivity.saveFilterToServer(r2, r2.flags, r2.name, r2.entities, r2.title_noanimate, r2.color, r82, r2.alwaysShow, r2.neverShow, r2.pinnedDialogs, true, true, true, true, true, this.this$0, new FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda5(this, r1));
            return;
        L54:
            r33 = r1.filter.emoticon;
            goto L53
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return FiltersSetupActivity.m14846$$Nest$fgetitems(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 >= 0) goto L5;
        L12:
            return 3;
        L5:
            if (r3 >= FiltersSetupActivity.m14846$$Nest$fgetitems(this.this$0).size()) goto L12;
            ItemInner r2 = (ItemInner) FiltersSetupActivity.m14846$$Nest$fgetitems(this.this$0).get(r3);
            if (r2 != null) goto L11;
            return 3;
        L11:
            return r2.viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            int r0 = r1.getItemViewType();
            if (r0 == 3) goto L10;
            if (r0 != 0) goto L6;
            return false;
        L6:
            if (r0 != 5) goto L8;
            return false;
        L8:
            if (r0 == 1) goto L14;
            return true;
        L14:
            return false;
        L10:
            return false;
        }

        public void moveElementToStart(int r4) {
            ArrayList<MessagesController.DialogFilter> r0 = this.this$0.getMessagesController().dialogFilters;
            if (r4 >= 0) goto L5;
            return;
        L5:
            if (r4 >= r0.size()) goto L14;
            int r2 = 0;
            r0.add(0, r0.remove(r4));
        L8:
            if (r2 > r4) goto L10;
            r0.get(r2).order = r2;
            r2 = r2 + 1;
            goto L8
        L10:
            FiltersSetupActivity.m14848$$Nest$fputorderChanged(this.this$0, true);
            FiltersSetupActivity.m14849$$Nest$mupdateRows(this.this$0, true);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            ItemInner r0 = (ItemInner) FiltersSetupActivity.m14846$$Nest$fgetitems(this.this$0).get(r9);
            if (r0 == null) goto L49;
            int r1 = r9 + 1;
            boolean r4 = true;
            int r5 = 0;
            if (r1 < FiltersSetupActivity.m14846$$Nest$fgetitems(this.this$0).size()) goto L8;
        L10:
            boolean r2 = false;
        L12:
            if (r1 >= FiltersSetupActivity.m14846$$Nest$fgetitems(this.this$0).size()) goto L15;
            r4 = false;
        L15:
            int r12 = r8.getItemViewType();
            if (r12 != 0) goto L18;
            ((HeaderCell) r8.itemView).setText(r0.text);
            return;
        L18:
            if (r12 == 2) goto L45;
            if (r12 != 3) goto L21;
            TextInfoPrivacyCell r7 = (TextInfoPrivacyCell) r8.itemView;
            if (TextUtils.isEmpty(r0.text) == false) goto L39;
            r7.setText(null);
            r7.setFixedSize(12);
        L40:
            if (r4 == false) goto L42;
            int r82 = 32;
        L43:
            r7.setBottomPadding(r82);
            return;
        L42:
            r82 = 17;
            goto L43
        L39:
            r7.setFixedSize(0);
            r7.setText(r0.text);
            goto L40
        L21:
            if (r12 != 4) goto L23;
            TextCell r83 = (TextCell) r8.itemView;
            Drawable r92 = this.mContext.getResources().getDrawable(R.drawable.poll_add_circle);
            Drawable r72 = this.mContext.getResources().getDrawable(R.drawable.poll_add_plus);
            int r22 = Theme.getColor(Theme.key_switchTrackChecked);
            PorterDuff.Mode r3 = PorterDuff.Mode.MULTIPLY;
            r92.setColorFilter(new PorterDuffColorFilter(r22, r3));
            r72.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_checkboxCheck), r3));
            r83.setTextAndIcon(r0.text + _UrlKt.FRAGMENT_ENCODE_SET, new CombinedDrawable(r92, r72), false);
            return;
        L23:
            if (r12 != 5) goto L25;
            ((SuggestedFilterCell) r8.itemView).setFilter(r0.suggested, r2);
            return;
        L25:
            if (r12 == 6) goto L27;
            return;
        L27:
            TextCheckCell r84 = (TextCheckCell) r8.itemView;
            r84.setTextAndCheck(r0.text, this.this$0.getMessagesController().folderTags, r2);
            if (this.this$0.getUserConfig().isPremium() == true) goto L30;
            r5 = R.drawable.permission_locked;
        L30:
            r84.setCheckBoxIcon(r5);
            return;
        L45:
            ((FilterCell) r8.itemView).setFilter(r0.filter, r2, r9);
            return;
        L8:
            if (((ItemInner) FiltersSetupActivity.m14846$$Nest$fgetitems(this.this$0).get(r1)).viewType == 3) goto L10;
            r2 = true;
            goto L12
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L4;
            FrameLayout r32 = new HeaderCell(this.mContext);
        L21:
            return new RecyclerListView.Holder(r32);
        L4:
            if (r4 != 1) goto L6;
            r32 = new HintInnerCell(this.mContext, R.raw.filters, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.CreateNewFilterInfo, new Object[0])));
            goto L21
        L6:
            if (r4 != 2) goto L8;
            FilterCell r33 = new FilterCell(this.this$0, this.mContext);
            r33.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r33.setOnReorderButtonTouchListener(new FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda0(this, r33));
            r33.setOnOptionsClick(new FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda1(this));
            r32 = r33;
            goto L21
        L8:
            if (r4 != 3) goto L10;
            r32 = new TextInfoPrivacyCell(this.mContext);
            goto L21
        L10:
            if (r4 != 4) goto L12;
            r32 = new TextCell(this.mContext);
            goto L21
        L12:
            if (r4 == 6) goto L14;
            SuggestedFilterCell r34 = new SuggestedFilterCell(this.mContext);
            r34.setAddOnClickListener(new FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda2(this, r34));
            r32 = r34;
            goto L21
        L14:
            r32 = new TextCheckCell(this.mContext);
            goto L21
        }

        public void swapElements(int r7, int r8) {
            if (r7 >= FiltersSetupActivity.m14844$$Nest$fgetfiltersStartPosition(this.this$0)) goto L5;
            return;
        L5:
            if (r8 < FiltersSetupActivity.m14844$$Nest$fgetfiltersStartPosition(this.this$0)) goto L26;
            ItemInner r0 = (ItemInner) FiltersSetupActivity.m14846$$Nest$fgetitems(this.this$0).get(r7);
            ItemInner r1 = (ItemInner) FiltersSetupActivity.m14846$$Nest$fgetitems(this.this$0).get(r8);
            if (r0 == null) goto L23;
            if (r1 == null) goto L24;
            MessagesController.DialogFilter r2 = r0.filter;
            if (r2 == null) goto L25;
            MessagesController.DialogFilter r3 = r1.filter;
            if (r3 == null) goto L27;
            int r4 = r2.order;
            r2.order = r3.order;
            r3.order = r4;
            ArrayList<MessagesController.DialogFilter> r22 = this.this$0.getMessagesController().dialogFilters;
            r22.set(r7 - FiltersSetupActivity.m14844$$Nest$fgetfiltersStartPosition(this.this$0), r1.filter);     // Catch: Exception -> L19
            r22.set(r8 - FiltersSetupActivity.m14844$$Nest$fgetfiltersStartPosition(this.this$0), r0.filter);     // Catch: Exception -> L19
        L17:
            FiltersSetupActivity.m14848$$Nest$fputorderChanged(this.this$0, true);
            FiltersSetupActivity.m14849$$Nest$mupdateRows(this.this$0, true);
            return;
        L27:
            return;
        L25:
            return;
        L24:
            return;
        L23:
            return;
        }
    }

    public static class SuggestedFilterCell extends FrameLayout {
        private ProgressButton addButton;
        private boolean needDivider;
        private TLRPC.TL_dialogFilterSuggested suggestedFilter;
        private TextView textView;
        private TextView valueTextView;

        public SuggestedFilterCell(Context r14) {
            super(r14);
            TextView r0 = new TextView(r14);
            this.textView = r0;
            r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.textView.setTextSize(1, 16.0f);
            this.textView.setTypeface(AndroidUtilities.regular());
            this.textView.setLines(1);
            this.textView.setMaxLines(1);
            this.textView.setSingleLine(true);
            TextView r02 = this.textView;
            TextUtils.TruncateAt r1 = TextUtils.TruncateAt.END;
            r02.setEllipsize(r1);
            TextView r03 = this.textView;
            if (LocaleController.isRTL == false) goto L5;
            int r3 = 5;
        L6:
            r03.setGravity(r3);
            TextView r04 = this.textView;
            if (LocaleController.isRTL == false) goto L9;
            int r8 = 5;
        L10:
            addView(r04, LayoutHelper.createFrame(-2, -2.0f, r8, 22.0f, 10.0f, 22.0f, 0.0f));
            TextView r05 = new TextView(r14);
            this.valueTextView = r05;
            r05.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
            this.valueTextView.setTextSize(1, 13.0f);
            this.valueTextView.setTypeface(AndroidUtilities.regular());
            this.valueTextView.setLines(1);
            this.valueTextView.setMaxLines(1);
            this.valueTextView.setSingleLine(true);
            this.valueTextView.setEllipsize(r1);
            TextView r06 = this.valueTextView;
            if (LocaleController.isRTL == false) goto L13;
            int r12 = 5;
        L14:
            r06.setGravity(r12);
            TextView r07 = this.valueTextView;
            if (LocaleController.isRTL == false) goto L17;
            int r82 = 5;
        L18:
            addView(r07, LayoutHelper.createFrame(-2, -2.0f, r82, 22.0f, 35.0f, 22.0f, 0.0f));
            ProgressButton r08 = new ProgressButton(r14);
            this.addButton = r08;
            r08.setText(LocaleController.getString(R.string.Add));
            this.addButton.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
            this.addButton.setProgressColor(Theme.getColor(Theme.key_featuredStickers_buttonProgress));
            this.addButton.setBackgroundRoundRect(Theme.getColor(Theme.key_featuredStickers_addButton), Theme.getColor(Theme.key_featuredStickers_addButtonPressed));
            addView(this.addButton, LayoutHelper.createFrameRelatively(-2.0f, 28.0f, 8388661, 0.0f, 18.0f, 14.0f, 0.0f));
            return;
        L17:
            r82 = 3;
            goto L18
        L13:
            r12 = 3;
            goto L14
        L9:
            r8 = 3;
            goto L10
        L5:
            r3 = 3;
            goto L6
        }

        public TLRPC.TL_dialogFilterSuggested getSuggestedFilter() {
            return this.suggestedFilter;
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            if (this.needDivider == false) goto L6;
            r8.drawLine(0.0f, getHeight() - 1, getWidth() - getPaddingRight(), getHeight() - 1, Theme.dividerPaint);
            return;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            r2.setEnabled(true);
            r2.setText(this.addButton.getText());
            r2.setClassName("android.widget.Button");
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r7, int r8) {
            setMeasuredDimension(View.MeasureSpec.getSize(r7), AndroidUtilities.dp(64.0f));
            measureChildWithMargins(this.addButton, r7, 0, r8, 0);
            measureChildWithMargins(this.textView, r7, this.addButton.getMeasuredWidth(), r8, 0);
            measureChildWithMargins(this.valueTextView, r7, this.addButton.getMeasuredWidth(), r8, 0);
        }

        public void setAddOnClickListener(View.OnClickListener r1) {
            this.addButton.setOnClickListener(r1);
        }

        public void setFilter(TLRPC.TL_dialogFilterSuggested r2, boolean r3) {
            this.needDivider = r3;
            this.suggestedFilter = r2;
            setWillNotDraw(!r3);
            this.textView.setText(r2.filter.title.text);
            this.valueTextView.setText(r2.description);
        }
    }

    public static class TextCell extends FrameLayout {
        private ImageView imageView;
        private SimpleTextView textView;

        public TextCell(Context r4) {
            super(r4);
            SimpleTextView r0 = new SimpleTextView(r4);
            this.textView = r0;
            r0.setTextSize(16);
            SimpleTextView r02 = this.textView;
            if (LocaleController.isRTL == false) goto L5;
            int r1 = 5;
        L6:
            r02.setGravity(r1);
            SimpleTextView r03 = this.textView;
            int r12 = Theme.key_windowBackgroundWhiteBlueText2;
            r03.setTextColor(Theme.getColor(r12));
            this.textView.setTag(Integer.valueOf(r12));
            addView(this.textView);
            ImageView r04 = new ImageView(r4);
            this.imageView = r04;
            r04.setScaleType(ImageView.ScaleType.CENTER);
            addView(this.imageView);
            return;
        L5:
            r1 = 3;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            int r52 = r5 - r3;
            int r62 = ((r6 - r4) - this.textView.getTextHeight()) / 2;
            float r32 = 23.0f;
            if (LocaleController.isRTL == false) goto L9;
            int r22 = getMeasuredWidth() - this.textView.getMeasuredWidth();
            if (this.imageView.getVisibility() != 0) goto L7;
            r32 = 64.0f;
        L7:
            int r23 = r22 - AndroidUtilities.dp(r32);
        L12:
            SimpleTextView r33 = this.textView;
            r33.layout(r23, r62, r33.getMeasuredWidth() + r23, this.textView.getMeasuredHeight() + r62);
            if (LocaleController.isRTL == true) goto L15;
            int r24 = AndroidUtilities.dp(20.0f);
        L16:
            ImageView r34 = this.imageView;
            r34.layout(r24, 0, r34.getMeasuredWidth() + r24, this.imageView.getMeasuredHeight());
            return;
        L15:
            r24 = (r52 - this.imageView.getMeasuredWidth()) - AndroidUtilities.dp(20.0f);
            goto L16
        L9:
            if (this.imageView.getVisibility() != 0) goto L11;
            r32 = 64.0f;
        L11:
            r23 = AndroidUtilities.dp(r32);
            goto L12
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            int r52 = View.MeasureSpec.getSize(r5);
            AndroidUtilities.dp(48.0f);
            this.textView.measure(View.MeasureSpec.makeMeasureSpec(r52 - AndroidUtilities.dp(94.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
            this.imageView.measure(View.MeasureSpec.makeMeasureSpec(r52, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
            setMeasuredDimension(r52, AndroidUtilities.dp(50.0f));
        }

        public void setTextAndIcon(String r1, Drawable r2, boolean r3) {
            this.textView.setText(r1);
            this.imageView.setImageDrawable(r2);
        }
    }

    public class TouchHelperCallback extends ItemTouchHelper.Callback {
        final /* synthetic */ FiltersSetupActivity this$0;

        public static /* synthetic */ void $r8$lambda$bHasRkv5J8FrAl_KOnvePgUk3Ms(TouchHelperCallback r0) {
            r0.resetDefaultPosition();
        }

        public TouchHelperCallback(FiltersSetupActivity r1) {
            this.this$0 = r1;
        }

        private void resetDefaultPosition() {
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L14;
            ArrayList<MessagesController.DialogFilter> r0 = this.this$0.getMessagesController().getDialogFilters();
            int r2 = 0;
        L7:
            if (r2 >= r0.size()) goto L19;
            if (r0.get(r2).isDefault() == false) goto L13;
            if (r2 == 0) goto L13;
            FiltersSetupActivity.m14841$$Nest$fgetadapter(this.this$0).moveElementToStart(r2);
            FiltersSetupActivity.m14847$$Nest$fgetlistView(this.this$0).scrollToPosition(0);
            this.this$0.onDefaultTabMoved();
            return;
        L13:
            r2 = r2 + 1;
            goto L7
        L19:
            return;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
            super.clearView(r1, r2);
            r2.itemView.setPressed(false);
            r2.itemView.setTag(R.id.dragging, null);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r1, RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() == 2) goto L7;
            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        L7:
            return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onChildDraw(Canvas r1, RecyclerView r2, RecyclerView.ViewHolder r3, float r4, float r5, int r6, boolean r7) {
            super.onChildDraw(r1, r2, r3, r4, r5, r6, r7);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r2, RecyclerView.ViewHolder r3, RecyclerView.ViewHolder r4) {
            if (r3.getItemViewType() == r4.getItemViewType()) goto L6;
            return false;
        L6:
            FiltersSetupActivity.m14841$$Nest$fgetadapter(this.this$0).swapElements(r3.getAdapterPosition(), r4.getAdapterPosition());
            return true;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r4, int r5) {
            if (r5 == 0) goto L4;
            FiltersSetupActivity.m14847$$Nest$fgetlistView(this.this$0).cancelClickRunnables(false);
            r4.itemView.setPressed(true);
        L5:
            super.onSelectedChanged(r4, r5);
            if (r4 == null) goto L13;
            View r3 = r4.itemView;
            int r42 = R.id.dragging;
            if (r5 != 2) goto L10;
            Boolean r52 = Boolean.TRUE;
        L11:
            r3.setTag(r42, r52);
            return;
        L10:
            r52 = null;
            goto L11
        L13:
            return;
        L4:
            AndroidUtilities.cancelRunOnUIThread(new FiltersSetupActivity$TouchHelperCallback$$ExternalSyntheticLambda0(this));
            AndroidUtilities.runOnUIThread(new FiltersSetupActivity$TouchHelperCallback$$ExternalSyntheticLambda0(this), 320);
            goto L5
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
        }
    }

    /* renamed from: $r8$lambda$6gk-5WteYq7xAAyaVLYo1gF_sFQ, reason: not valid java name */
    public static /* synthetic */ void m14840$r8$lambda$6gk5WteYq7xAAyaVLYo1gF_sFQ(FiltersSetupActivity r0, Context r1, View r2, int r3, float r4, float r5) {
        r0.lambda$createView$4(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$Dv9kArEpyPuiPHkAPZ_F11CCrTo(FiltersSetupActivity r0) {
        r0.lambda$updateRows$0();
    }

    public static /* synthetic */ void $r8$lambda$RN90wDWAVhNGopom69AOWvkV7OM(FiltersSetupActivity r0, TLRPC.TL_messages_toggleDialogFilterTags r1) {
        r0.lambda$createView$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$VyfLJIlvIrybTM9y4BOIAbmfvYI(FiltersSetupActivity r0) {
        r0.lambda$onDefaultTabMoved$7();
    }

    public static /* synthetic */ int $r8$lambda$_JDR3N_KzAZhR8rBHlbkG6juAd8(FiltersSetupActivity r0) {
        return r0.lambda$createView$5();
    }

    public static /* synthetic */ void $r8$lambda$momTES3DrrhcaDP_m7wnE6dYZYs(FiltersSetupActivity r0, TLRPC.TL_messages_toggleDialogFilterTags r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$createView$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$q02bfgnvRAZB4LIF7Pb1_5oq19g(FiltersSetupActivity r0) {
        r0.lambda$createView$6();
    }

    public static /* synthetic */ void $r8$lambda$viSCAHUal90ZrKwbIMu73qciUSE(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m14841$$Nest$fgetadapter(FiltersSetupActivity r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetfiltersSectionEnd, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14842$$Nest$fgetfiltersSectionEnd(FiltersSetupActivity r0) {
        return r0.filtersSectionEnd;
    }

    /* renamed from: -$$Nest$fgetfiltersSectionStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14843$$Nest$fgetfiltersSectionStart(FiltersSetupActivity r0) {
        return r0.filtersSectionStart;
    }

    /* renamed from: -$$Nest$fgetfiltersStartPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14844$$Nest$fgetfiltersStartPosition(FiltersSetupActivity r0) {
        return r0.filtersStartPosition;
    }

    /* renamed from: -$$Nest$fgetitemTouchHelper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ItemTouchHelper m14845$$Nest$fgetitemTouchHelper(FiltersSetupActivity r0) {
        return r0.itemTouchHelper;
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m14846$$Nest$fgetitems(FiltersSetupActivity r0) {
        return r0.items;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m14847$$Nest$fgetlistView(FiltersSetupActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fputorderChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14848$$Nest$fputorderChanged(FiltersSetupActivity r0, boolean r1) {
        r0.orderChanged = r1;
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14849$$Nest$mupdateRows(FiltersSetupActivity r0, boolean r1) {
        r0.updateRows(r1);
    }

    public FiltersSetupActivity() {
        this.oldItems = new ArrayList();
        this.items = new ArrayList();
        this.filtersSectionStart = -1;
        this.filtersSectionEnd = -1;
        this.shiftDp = -4;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(FiltersSetupActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$100(FiltersSetupActivity r0) {
        return r0.currentAccount;
    }

    private /* synthetic */ void lambda$createView$2(TLRPC.TL_messages_toggleDialogFilterTags r2) {
        if (r2.enabled == true) goto L5;
        return;
    L5:
        if (this.loadedColors == true) goto L9;
        this.loadingFiltersForColors = true;
        getMessagesController().loadRemoteFilters(true);
        this.loadedColors = true;
        return;
    }

    private /* synthetic */ void lambda$createView$3(TLRPC.TL_messages_toggleDialogFilterTags r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new FiltersSetupActivity$$ExternalSyntheticLambda6(this, r1));
    }

    private /* synthetic */ void lambda$createView$4(Context r7, View r8, int r9, float r10, float r11) {
        if (r9 >= 0) goto L4;
        return;
    L4:
        if (r9 >= this.items.size()) goto L37;
        ItemInner r92 = this.items.get(r9);
        if (r92 == null) goto L38;
        int r102 = r92.viewType;
        if (r102 != 6) goto L18;
        if (getUserConfig().isPremium() == true) goto L15;
        showDialog(new PremiumFeatureBottomSheet(this, 35, true));
        return;
    L15:
        TLRPC.TL_messages_toggleDialogFilterTags r72 = new TLRPC.TL_messages_toggleDialogFilterTags();
        r72.enabled = true ^ getMessagesController().folderTags;
        getMessagesController().setFolderTags(r72.enabled);
        getConnectionsManager().sendRequest(r72, new FiltersSetupActivity$$ExternalSyntheticLambda5(this, r72));
        ((TextCheckCell) r8).setChecked(getMessagesController().folderTags);
        ListAdapter r73 = this.adapter;
        int r82 = this.filtersSectionStart;
        r73.notifyItemRangeChanged(r82, this.filtersSectionEnd - r82);
        return;
    L18:
        if (r102 != 2) goto L31;
        MessagesController.DialogFilter r83 = r92.filter;
        if (r83 != null) goto L22;
        return;
    L22:
        if (r83.isDefault() == false) goto L25;
        return;
    L25:
        if (r83.locked == false) goto L28;
        showDialog(new LimitReachedBottomSheet(this, r7, 3, this.currentAccount, null));
        return;
    L28:
        presentFragment(new FilterCreateActivity(r83));
        return;
    L31:
        if (r102 != 4) goto L36;
        createFolder(getParentLayout());
        return;
    L36:
        return;
    L38:
        return;
    }

    private /* synthetic */ int lambda$createView$5() {
        return this.folderTagsPosition;
    }

    private /* synthetic */ void lambda$createView$6() {
        this.listView.highlightRow(new FiltersSetupActivity$$ExternalSyntheticLambda4(this));
    }

    private /* synthetic */ void lambda$onDefaultTabMoved$7() {
        showDialog(new PremiumFeatureBottomSheet(this, 9, true));
    }

    private /* synthetic */ void lambda$updateRows$0() {
        presentFragment(new PremiumPreviewFragment("settings"));
    }

    private void updateRows(boolean r11) {
        this.showTagsRow = -1;
        RecyclerListView r1 = this.listView;
        if (r1 == null) goto L8;
        ArrayList<Long> r2 = r1.forcedSections;
        if (r2 != null) goto L7;
        r1.forcedSections = new ArrayList();
        goto L8
    L7:
        r2.clear();
    L8:
        this.oldItems.clear();
        this.oldItems.addAll(this.items);
        this.items.clear();
        ArrayList<TLRPC.TL_dialogFilterSuggested> r12 = getMessagesController().suggestedFilters;
        ArrayList<MessagesController.DialogFilter> r22 = getMessagesController().getDialogFilters();
        this.items.add(ItemInner.asHint());
        int r5 = 0;
        if (r12.isEmpty() == true) goto L24;
        if (r22.size() >= 10) goto L24;
        int r3 = this.items.size();
        this.items.add(ItemInner.asHeader(LocaleController.getString(R.string.FilterRecommended)));
        if (ExteraConfig.getSectionsSeparatedHeaders() == false) goto L15;
        r3 = this.items.size();
    L15:
        int r7 = 0;
    L17:
        if (r7 >= r12.size()) goto L19;
        this.items.add(ItemInner.asSuggested(r12.get(r7)));
        r7 = r7 + 1;
        goto L17
    L19:
        RecyclerListView r13 = this.listView;
        if (r13 == null) goto L22;
        r13.forcedSections.add(Long.valueOf(AndroidUtilities.pack(r3, this.items.size() - 1)));
    L22:
        this.items.add(ItemInner.asShadow(null));
    L24:
        if (r22.isEmpty() == true) goto L44;
        this.filtersSectionStart = this.items.size();
        this.items.add(ItemInner.asHeader(LocaleController.getString(R.string.Filters)));
        this.filtersStartPosition = this.items.size();
        int r0 = 0;
    L26:
        int r14 = r22.size();
        ArrayList<ItemInner> r32 = this.items;
        if (r0 >= r14) goto L34;
        r32.add(ItemInner.asFilter(r22.get(r0)));
        if (MessagesController.getInstance(this.currentAccount).folderTags == false) goto L33;
        if (r22.get(r0).color < 0) goto L33;
        this.loadedColors = true;
    L33:
        r0 = r0 + 1;
        goto L26
    L34:
        this.filtersSectionEnd = r32.size();
        if (ExteraConfig.getSectionsSeparatedHeaders() == false) goto L37;
        int r02 = this.filtersStartPosition;
    L38:
        RecyclerListView r15 = this.listView;
        if (r15 == null) goto L46;
        ArrayList<Long> r16 = r15.forcedSections;
        int r33 = this.filtersSectionEnd - 1;
        if (r22.size() >= getMessagesController().dialogFiltersLimitPremium) goto L43;
        r5 = 1;
    L43:
        r16.add(Long.valueOf(AndroidUtilities.pack(r02, r33 + r5)));
    L46:
        if (r22.size() >= getMessagesController().dialogFiltersLimitPremium) goto L48;
        this.items.add(ItemInner.asButton(LocaleController.getString(R.string.CreateNewFilter)));
    L48:
        this.items.add(ItemInner.asShadow(null));
        this.folderTagsPosition = this.items.size();
        this.showTagsRow = this.items.size();
        this.items.add(ItemInner.asCheck(LocaleController.getString(R.string.FolderShowTags)));
        ArrayList<ItemInner> r03 = this.items;
        if (getUserConfig().isPremium() == true) goto L51;
        CharSequence r17 = AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.FolderShowTagsInfoPremium), Theme.key_windowBackgroundWhiteBlueHeader, 2, new FiltersSetupActivity$$ExternalSyntheticLambda1(this));
    L52:
        r03.add(ItemInner.asShadow(r17));
        ListAdapter r04 = this.adapter;
        if (r04 == null) goto L64;
        if (r11 == false) goto L57;
        r04.setItems(this.oldItems, this.items);
        return;
    L57:
        r04.notifyDataSetChanged();
        return;
    L64:
        return;
    L51:
        r17 = LocaleController.getString(R.string.FolderShowTagsInfo);
        goto L52
    L37:
        r02 = this.filtersSectionStart;
        goto L38
    L44:
        this.filtersSectionEnd = -1;
        this.filtersSectionStart = -1;
        goto L46
    }

    public void createFolder(INavigationLayout r9) {
        int r0 = getMessagesController().getDialogFilters().size();
        if ((r0 - 1) < getMessagesController().dialogFiltersLimitDefault) goto L7;
        if (getUserConfig().isPremium() == true) goto L7;
    L8:
        showDialog(new LimitReachedBottomSheet(this, getContext(), 3, this.currentAccount, null));
        return;
    L7:
        if (r0 >= getMessagesController().dialogFiltersLimitPremium) goto L8;
        if (r9 == null) goto L13;
        r9.presentFragment(new FilterCreateActivity());
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r7) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.Filters));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
    L7:
        FrameLayout r02 = new FrameLayout(r7);
        this.fragmentView = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        AnonymousClass2 r2 = new AnonymousClass2(this, r7);
        this.listView = r2;
        r2.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        DefaultItemAnimator r22 = new DefaultItemAnimator();
        r22.setDurations(350);
        r22.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r22.setDelayAnimations(false);
        r22.setSupportsChangeAnimations(false);
        this.listView.setItemAnimator(r22);
        ((DefaultItemAnimator) this.listView.getItemAnimator()).setDelayAnimations(false);
        this.listView.setLayoutManager(new LinearLayoutManager(r7, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        ItemTouchHelper r23 = new ItemTouchHelper(new TouchHelperCallback(this));
        this.itemTouchHelper = r23;
        r23.attachToRecyclerView(this.listView);
        r02.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r03 = this.listView;
        ListAdapter r24 = new ListAdapter(this, r7);
        this.adapter = r24;
        r03.setAdapter(r24);
        this.listView.setOnItemClickListener(new FiltersSetupActivity$$ExternalSyntheticLambda2(this, r7));
        if (this.highlightTags == false) goto L11;
        updateRows(false);
        this.highlightTags = false;
        this.listView.scrollToPosition(this.adapter.getItemCount() - 1);
        AndroidUtilities.runOnUIThread(new FiltersSetupActivity$$ExternalSyntheticLambda3(this), 200);
    L11:
        return this.fragmentView;
    L5:
        if (r0.isRightLayout() == false) goto L7;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_close);
        goto L7
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.dialogFiltersUpdated) goto L10;
        if (this.ignoreUpdates == true) goto L14;
        updateRows(true);
        return;
    L14:
        return;
    L10:
        if (r1 != NotificationCenter.suggestedFiltersLoaded) goto L13;
        updateRows(true);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{HeaderCell.class, TextCell.class, FilterCell.class, SuggestedFilterCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{FilterCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{FilterCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        int r21 = Theme.key_stickers_menu;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{FilterCell.class}, new String[]{"moveImageView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{FilterCell.class}, new String[]{"optionsImageView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, new Class[]{FilterCell.class}, new String[]{"optionsImageView"}, null, null, null, Theme.key_stickers_menuSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueText2));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_switchTrackChecked));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_checkboxCheck));
        return r1;
    }

    public UndoView getUndoView() {
        if (getContext() != null) goto L7;
        return null;
    L7:
        if (this.undoView != null) goto L10;
        FrameLayout r0 = (FrameLayout) this.fragmentView;
        UndoView r1 = new UndoView(getContext());
        this.undoView = r1;
        r0.addView(r1, LayoutHelper.createFrame(-1, -2.0f, 83, 8.0f, 0.0f, 8.0f, 8.0f));
        this.undoView.setTranslationY(-getBottomInset());
    L10:
        return this.undoView;
    }

    public FiltersSetupActivity highlightTags() {
        this.highlightTags = true;
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    public void onDefaultTabMoved() {
        this.fragmentView.performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Exception -> L5
    L3:
        BulletinFactory.of(this).createSimpleBulletin(R.raw.filter_reorder, AndroidUtilities.replaceTags(LocaleController.formatString("LimitReachedReorderFolder", R.string.LimitReachedReorderFolder, new Object[]{LocaleController.getString(R.string.FilterAllChats)})), LocaleController.getString(R.string.PremiumMore), 5000, new FiltersSetupActivity$$ExternalSyntheticLambda7(this)).show();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        updateRows(false);
        getMessagesController().loadRemoteFilters(true);
        getNotificationCenter().addObserver(this, NotificationCenter.dialogFiltersUpdated);
        getNotificationCenter().addObserver(this, NotificationCenter.suggestedFiltersLoaded);
        if (getMessagesController().suggestedFilters.isEmpty() == false) goto L6;
        getMessagesController().loadSuggestedFilters();
    L6:
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        NotificationCenter r0 = getNotificationCenter();
        int r1 = NotificationCenter.dialogFiltersUpdated;
        r0.removeObserver(this, r1);
        getNotificationCenter().removeObserver(this, NotificationCenter.suggestedFiltersLoaded);
        if (this.orderChanged == false) goto L8;
        int r2 = 0;
        getNotificationCenter().postNotificationName(r1, new Object[0]);
        getMessagesStorage().saveDialogFiltersOrder();
        TLRPC.TL_messages_updateDialogFiltersOrder r02 = new TLRPC.TL_messages_updateDialogFiltersOrder();
        ArrayList<MessagesController.DialogFilter> r12 = getMessagesController().getDialogFilters();
        int r3 = r12.size();
    L5:
        if (r2 >= r3) goto L7;
        MessagesController.DialogFilter r4 = r12.get(r2);
        r02.order.add(Integer.valueOf(r4.id));
        r2 = r2 + 1;
        goto L5
    L7:
        getConnectionsManager().sendRequest(r02, new FiltersSetupActivity$$ExternalSyntheticLambda0());
    L8:
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
        UndoView r0 = this.undoView;
        if (r0 == null) goto L6;
        r0.setTranslationY(-r4);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.adapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }
}
