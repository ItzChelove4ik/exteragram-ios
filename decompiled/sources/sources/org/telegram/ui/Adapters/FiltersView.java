package org.telegram.ui.Adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.AvatarCornerType;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.gms.cast.CastStatusCodes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.time.DurationKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes3.dex */
public class FiltersView extends RecyclerListView {
    public static final MediaFilterData[] filters = null;
    private static final Pattern longDate = null;
    private static final Pattern monthYearOrDayPatter = null;
    private static final int[] numberOfDaysEachMonth = null;
    private static final Pattern shortDate = null;
    private static final Pattern yearOrDayAndMonthPatter = null;
    private static final Pattern yearPatter = null;
    DiffUtil.Callback diffUtilsCallback;
    public boolean drawDivider;
    LinearLayoutManager layoutManager;
    private ArrayList<MediaFilterData> oldItems;
    private ArrayList<MediaFilterData> usersFilters;

    /* renamed from: org.telegram.ui.Adapters.FiltersView$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayoutManager {
        final /* synthetic */ FiltersView this$0;

        public AnonymousClass1(FiltersView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler r1, RecyclerView.State r2, AccessibilityNodeInfoCompat r3) {
            super.onInitializeAccessibilityNodeInfo(r1, r2, r3);
            if (this.this$0.isEnabled() == true) goto L6;
            r3.setVisibleToUser(false);
            return;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Adapters.FiltersView$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.ItemDecoration {
        final /* synthetic */ FiltersView this$0;

        public AnonymousClass2(FiltersView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            super.getItemOffsets(r1, r2, r3, r4);
            int r0 = r3.getChildAdapterPosition(r2);
            r1.left = AndroidUtilities.dp(8.0f);
            if (r0 != (r4.getItemCount() - 1)) goto L5;
            r1.right = AndroidUtilities.dp(10.0f);
        L5:
            if (r0 != 0) goto L8;
            r1.left = AndroidUtilities.dp(10.0f);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Adapters.FiltersView$3, reason: invalid class name */
    public class AnonymousClass3 extends DefaultItemAnimator {
        private final float scaleFrom;
        final /* synthetic */ FiltersView this$0;

        /* renamed from: org.telegram.ui.Adapters.FiltersView$3$1, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass3 this$1;
            final /* synthetic */ ViewPropertyAnimator val$animation;
            final /* synthetic */ RecyclerView.ViewHolder val$holder;
            final /* synthetic */ View val$view;

            public AnonymousClass1(AnonymousClass3 r1, RecyclerView.ViewHolder r2, View r3, ViewPropertyAnimator r4) {
                this.this$1 = r1;
                this.val$holder = r2;
                this.val$view = r3;
                this.val$animation = r4;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator r1) {
                this.val$view.setAlpha(1.0f);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                this.val$animation.setListener(null);
                this.this$1.dispatchAddFinished(this.val$holder);
                AnonymousClass3.access$000(this.this$1).remove(this.val$holder);
                AnonymousClass3.access$100(this.this$1);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator r1) {
                this.this$1.dispatchAddStarting(this.val$holder);
            }
        }

        /* renamed from: org.telegram.ui.Adapters.FiltersView$3$2, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass3 this$1;
            final /* synthetic */ ViewPropertyAnimator val$animation;
            final /* synthetic */ RecyclerView.ViewHolder val$holder;
            final /* synthetic */ View val$view;

            public AnonymousClass2(AnonymousClass3 r1, RecyclerView.ViewHolder r2, ViewPropertyAnimator r3, View r4) {
                this.this$1 = r1;
                this.val$holder = r2;
                this.val$animation = r3;
                this.val$view = r4;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                this.val$animation.setListener(null);
                this.val$view.setAlpha(1.0f);
                this.val$view.setTranslationX(0.0f);
                this.val$view.setTranslationY(0.0f);
                this.val$view.setScaleX(1.0f);
                this.val$view.setScaleY(1.0f);
                this.this$1.dispatchRemoveFinished(this.val$holder);
                AnonymousClass3.access$200(this.this$1).remove(this.val$holder);
                AnonymousClass3.access$300(this.this$1);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator r1) {
                this.this$1.dispatchRemoveStarting(this.val$holder);
            }
        }

        public AnonymousClass3(FiltersView r1) {
            this.this$0 = r1;
            this.scaleFrom = 0.0f;
        }

        public static /* synthetic */ ArrayList access$000(AnonymousClass3 r0) {
            return r0.mAddAnimations;
        }

        public static /* synthetic */ void access$100(AnonymousClass3 r0) {
            r0.dispatchFinishedWhenDone();
        }

        public static /* synthetic */ ArrayList access$200(AnonymousClass3 r0) {
            return r0.mRemoveAnimations;
        }

        public static /* synthetic */ void access$300(AnonymousClass3 r0) {
            r0.dispatchFinishedWhenDone();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.SimpleItemAnimator
        public boolean animateAdd(RecyclerView.ViewHolder r3) {
            boolean r2 = super.animateAdd(r3);
            if (r2 == false) goto L5;
            r3.itemView.setScaleX(0.0f);
            r3.itemView.setScaleY(0.0f);
        L5:
            return r2;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void animateAddImpl(RecyclerView.ViewHolder r6) {
            View r0 = r6.itemView;
            ViewPropertyAnimator r1 = r0.animate();
            this.mAddAnimations.add(r6);
            r1.alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(getAddDuration()).setListener(new AnonymousClass1(this, r6, r0, r1)).start();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void animateRemoveImpl(RecyclerView.ViewHolder r5) {
            View r0 = r5.itemView;
            ViewPropertyAnimator r1 = r0.animate();
            this.mRemoveAnimations.add(r5);
            r1.setDuration(getRemoveDuration()).alpha(0.0f).scaleX(0.0f).scaleY(0.0f).setListener(new AnonymousClass2(this, r5, r1, r0)).start();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public long getAddAnimationDelay(long r1, long r3, long r5) {
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
        public long getAddDuration() {
            return 220;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public long getMoveAnimationDelay() {
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
        public long getMoveDuration() {
            return 220;
        }
    }

    /* renamed from: org.telegram.ui.Adapters.FiltersView$4, reason: invalid class name */
    public class AnonymousClass4 extends DiffUtil.Callback {
        final /* synthetic */ FiltersView this$0;

        public AnonymousClass4(FiltersView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int r1, int r2) {
            return true;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int r6, int r7) {
            MediaFilterData r62 = (MediaFilterData) FiltersView.m5898$$Nest$fgetoldItems(this.this$0).get(r6);
            MediaFilterData r5 = (MediaFilterData) FiltersView.m5899$$Nest$fgetusersFilters(this.this$0).get(r7);
            if (r62.isSameType(r5) == false) goto L29;
            int r72 = r62.filterType;
            if (r72 != 4) goto L23;
            TLObject r63 = r62.chat;
            if ((r63 instanceof TLRPC.User) == false) goto L15;
            TLObject r73 = r5.chat;
            if ((r73 instanceof TLRPC.User) == false) goto L15;
            if (((TLRPC.User) r63).id != ((TLRPC.User) r73).id) goto L13;
            return true;
        L13:
            return false;
        L15:
            if ((r63 instanceof TLRPC.Chat) == false) goto L29;
            TLObject r52 = r5.chat;
            if ((r52 instanceof TLRPC.Chat) == false) goto L29;
            if (((TLRPC.Chat) r63).id != ((TLRPC.Chat) r52).id) goto L21;
            return true;
        L21:
            return false;
        L23:
            if (r72 != 6) goto L27;
            return MediaFilterData.m5901$$Nest$fgettitle(r62).equals(MediaFilterData.m5901$$Nest$fgettitle(r5));
        L27:
            if (r72 != 7) goto L29;
            return true;
        L29:
            return false;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return FiltersView.m5899$$Nest$fgetusersFilters(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return FiltersView.m5898$$Nest$fgetoldItems(this.this$0).size();
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ FiltersView this$0;

        private Adapter(FiltersView r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(FiltersView r1, FiltersViewIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return FiltersView.m5899$$Nest$fgetusersFilters(this.this$0).size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
            MediaFilterData r0 = (MediaFilterData) FiltersView.m5899$$Nest$fgetusersFilters(this.this$0).get(r2);
            ((ViewHolder) r1).filterView.setData(r0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            return onCreateViewHolder(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            ViewHolder r42 = new ViewHolder(this.this$0, new FilterView(r3.getContext(), FiltersView.access$400(this.this$0)));
            RecyclerView.LayoutParams r2 = new RecyclerView.LayoutParams(-2, AndroidUtilities.dp(30.0f));
            r42.itemView.setLayoutParams(r2);
            return r42;
        }
    }

    /* loaded from: classes6.dex */
    public static class DateData {
        public final long maxDate;
        public final long minDate;
        public final String title;

        private DateData(String r1, long r2, long r4) {
            this.title = r1;
            this.minDate = r2;
            this.maxDate = r4;
        }

        public /* synthetic */ DateData(String r1, long r2, long r4, FiltersViewIA r6) {
            this(r1, r2, r4);
        }
    }

    /* loaded from: classes6.dex */
    public static class FilterView extends FrameLayout {
        BackupImageView avatarImageView;
        MediaFilterData data;
        private final Theme.ResourcesProvider resourcesProvider;
        CombinedDrawable thumbDrawable;
        TextView titleView;

        /* renamed from: -$$Nest$mupdateColors, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5900$$Nest$mupdateColors(FilterView r0) {
            r0.updateColors();
        }

        public FilterView(Context r8, Theme.ResourcesProvider r9) {
            super(r8);
            this.resourcesProvider = r9;
            BackupImageView r92 = new BackupImageView(r8);
            this.avatarImageView = r92;
            addView(r92, LayoutHelper.createFrame(30, 30.0f));
            TextView r93 = new TextView(r8);
            this.titleView = r93;
            r93.setTextSize(1, 14.0f);
            addView(this.titleView, LayoutHelper.createFrame(-2, -2.0f, 16, 36.0f, 0.0f, 14.0f, 0.0f));
            updateColors();
        }

        private void updateColors() {
            setBackground(Theme.createRoundRectDrawable(ExteraConfig.getAvatarCorners(32.0f), getThemedColor(Theme.key_groupcreate_spanBackground)));
            this.titleView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
            CombinedDrawable r0 = this.thumbDrawable;
            if (r0 != null) goto L5;
            return;
        L5:
            if (this.data.filterType != 7) goto L8;
            Theme.setCombinedDrawableColor(r0, getThemedColor(Theme.key_featuredStickers_addButton), false);
            Theme.setCombinedDrawableColor(this.thumbDrawable, getThemedColor(Theme.key_featuredStickers_buttonText), true);
            return;
        L8:
            Theme.setCombinedDrawableColor(r0, getThemedColor(Theme.key_featuredStickers_addButton), false);
            Theme.setCombinedDrawableColor(this.thumbDrawable, getThemedColor(Theme.key_featuredStickers_buttonText), true);
        }

        public int getThemedColor(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        public void setData(MediaFilterData r12) {
            this.data = r12;
            this.avatarImageView.getImageReceiver().clearImage();
            if (r12.filterType != 7) goto L6;
            CombinedDrawable r0 = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(32.0f), R.drawable.chats_archive);
            this.thumbDrawable = r0;
            r0.setIconSize(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            Theme.setCombinedDrawableColor(this.thumbDrawable, getThemedColor(Theme.key_featuredStickers_addButton), false);
            Theme.setCombinedDrawableColor(this.thumbDrawable, getThemedColor(Theme.key_featuredStickers_buttonText), true);
            this.avatarImageView.setImageDrawable(this.thumbDrawable);
            this.titleView.setText(MediaFilterData.m5901$$Nest$fgettitle(r12));
            return;
        L6:
            CombinedDrawable r02 = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(32.0f), r12.iconResFilled);
            this.thumbDrawable = r02;
            int r1 = Theme.key_featuredStickers_addButton;
            Theme.setCombinedDrawableColor(r02, getThemedColor(r1), false);
            CombinedDrawable r03 = this.thumbDrawable;
            int r6 = Theme.key_featuredStickers_buttonText;
            Theme.setCombinedDrawableColor(r03, getThemedColor(r6), true);
            if (r12.filterType != 4) goto L21;
            TLObject r04 = r12.chat;
            if ((r04 instanceof TLRPC.User) == false) goto L15;
            TLRPC.User r05 = (TLRPC.User) r04;
            if (UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().id != r05.id) goto L13;
            CombinedDrawable r06 = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(32.0f), R.drawable.chats_saved);
            r06.setIconSize(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            Theme.setCombinedDrawableColor(r06, getThemedColor(r1), false);
            Theme.setCombinedDrawableColor(r06, getThemedColor(r6), true);
            this.avatarImageView.setImageDrawable(r06);
        L22:
            this.titleView.setText(MediaFilterData.m5901$$Nest$fgettitle(r12));
            return;
        L13:
            this.avatarImageView.getImageReceiver().setRoundRadius(ExteraConfig.getAvatarCorners(32.0f));
            this.avatarImageView.getImageReceiver().setForUserOrChat(r05, this.thumbDrawable);
            goto L22
        L15:
            if ((r04 instanceof TLRPC.Chat) == false) goto L22;
            TLRPC.Chat r07 = (TLRPC.Chat) r04;
            ImageReceiver r13 = this.avatarImageView.getImageReceiver();
            if (ChatObject.isCommunity(r07) == false) goto L19;
            AvatarCornerType r2 = AvatarCornerType.COMMUNITY;
        L20:
            r13.setRoundRadius(ExteraConfig.getAvatarCorners(32.0f, false, r2));
            this.avatarImageView.getImageReceiver().setForUserOrChat(r07, this.thumbDrawable);
            goto L22
        L19:
            r2 = AvatarCornerType.DEFAULT;
            goto L20
        L21:
            this.avatarImageView.setImageDrawable(this.thumbDrawable);
            goto L22
        }
    }

    public static class MediaFilterData {
        public TLObject chat;
        public DateData dateData;
        public TLRPC.MessagesFilter filter;
        public int filterType;
        public int iconResFilled;
        public ReactionsLayoutInBubble.VisibleReaction reaction;
        public boolean removable;
        private String title;
        public int titleResId;

        /* renamed from: -$$Nest$fgettitle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m5901$$Nest$fgettitle(MediaFilterData r0) {
            return r0.title;
        }

        public MediaFilterData(int r2, int r3, TLRPC.MessagesFilter r4, int r5) {
            this.removable = true;
            this.iconResFilled = r2;
            this.titleResId = r3;
            this.filter = r4;
            this.filterType = r5;
        }

        public MediaFilterData(int r2, String r3, TLRPC.MessagesFilter r4, int r5) {
            this.removable = true;
            this.iconResFilled = r2;
            this.title = r3;
            this.filter = r4;
            this.filterType = r5;
        }

        public String getTitle() {
            String r0 = this.title;
            if (r0 == null) goto L6;
            return r0;
        L6:
            return LocaleController.getString(this.titleResId);
        }

        public boolean isMedia() {
            int r2 = this.filterType;
            if (r2 == 0) goto L14;
            if (r2 == 1) goto L14;
            if (r2 == 2) goto L14;
            if (r2 == 3) goto L14;
            if (r2 == 5) goto L14;
            return false;
        L14:
            return true;
        }

        public boolean isSameType(MediaFilterData r4) {
            if (this.filterType != r4.filterType) goto L6;
            return true;
        L6:
            if (isMedia() == true) goto L8;
            return false;
        L8:
            if (r4.isMedia() == false) goto L12;
            return true;
        L12:
            return false;
        }

        public void setDate(DateData r1) {
            this.dateData = r1;
        }

        public void setUser(TLObject r1) {
            this.chat = r1;
        }
    }

    /* loaded from: classes6.dex */
    public static class UpdateCallback implements ListUpdateCallback {
        final RecyclerView.Adapter adapter;
        boolean changed;

        private UpdateCallback(RecyclerView.Adapter r1) {
            this.adapter = r1;
        }

        public /* synthetic */ UpdateCallback(RecyclerView.Adapter r1, FiltersViewIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onChanged(int r1, int r2, Object r3) {
            this.adapter.notifyItemRangeChanged(r1, r2, r3);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onInserted(int r2, int r3) {
            this.changed = true;
            this.adapter.notifyItemRangeInserted(r2, r3);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onMoved(int r2, int r3) {
            this.changed = true;
            this.adapter.notifyItemMoved(r2, r3);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onRemoved(int r2, int r3) {
            this.changed = true;
            this.adapter.notifyItemRangeRemoved(r2, r3);
        }
    }

    /* loaded from: classes6.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        FilterView filterView;
        final /* synthetic */ FiltersView this$0;

        public ViewHolder(FiltersView r1, FilterView r2) {
            this.this$0 = r1;
            super(r2);
            this.filterView = r2;
        }
    }

    /* renamed from: -$$Nest$fgetoldItems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5898$$Nest$fgetoldItems(FiltersView r0) {
        return r0.oldItems;
    }

    /* renamed from: -$$Nest$fgetusersFilters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5899$$Nest$fgetusersFilters(FiltersView r0) {
        return r0.usersFilters;
    }

    static {
        filters = new MediaFilterData[]{new MediaFilterData(R.drawable.search_media_filled, R.string.SharedMediaTab2, new TLRPC.TL_inputMessagesFilterPhotoVideo(), 0), new MediaFilterData(R.drawable.search_links_filled, R.string.SharedLinksTab2, new TLRPC.TL_inputMessagesFilterUrl(), 2), new MediaFilterData(R.drawable.search_files_filled, R.string.SharedFilesTab2, new TLRPC.TL_inputMessagesFilterDocument(), 1), new MediaFilterData(R.drawable.search_music_filled, R.string.SharedMusicTab2, new TLRPC.TL_inputMessagesFilterMusic(), 3), new MediaFilterData(R.drawable.search_voice_filled, R.string.SharedVoiceTab2, new TLRPC.TL_inputMessagesFilterRoundVoice(), 5)};
        yearPatter = Pattern.compile("20[0-9]{1,2}");
        monthYearOrDayPatter = Pattern.compile("(\\w{3,}) ([0-9]{0,4})");
        yearOrDayAndMonthPatter = Pattern.compile("([0-9]{0,4}) (\\w{2,})");
        shortDate = Pattern.compile("^([0-9]{1,4})(\\.| |/|\\-)([0-9]{1,4})$");
        longDate = Pattern.compile("^([0-9]{1,2})(\\.| |/|\\-)([0-9]{1,2})(\\.| |/|\\-)([0-9]{1,4})$");
        numberOfDaysEachMonth = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    }

    public FiltersView(Context r2, Theme.ResourcesProvider r3) {
        super(r2, r3);
        this.usersFilters = new ArrayList();
        this.oldItems = new ArrayList();
        this.drawDivider = true;
        this.diffUtilsCallback = new AnonymousClass4(this);
        AnonymousClass1 r32 = new AnonymousClass1(this, r2);
        this.layoutManager = r32;
        r32.setOrientation(0);
        setLayoutManager(this.layoutManager);
        setAdapter(new Adapter(this, null));
        addItemDecoration(new AnonymousClass2(this));
        setItemAnimator(new AnonymousClass3(this));
        setWillNotDraw(false);
        setHideIfEmpty(false);
        setSelectorRadius(AndroidUtilities.dp(28.0f));
        setSelectorDrawableColor(getThemedColor(Theme.key_listSelector));
    }

    public static /* synthetic */ Theme.ResourcesProvider access$400(FiltersView r0) {
        return r0.resourcesProvider;
    }

    private static void createForDayMonth(ArrayList<DateData> r18, int r19, int r20) {
        if (validDateForMont(r19, r20) == false) goto L22;
        int r2 = Calendar.getInstance().get(1);
        long r4 = Calendar.getInstance().getTimeInMillis();
        GregorianCalendar r6 = (GregorianCalendar) Calendar.getInstance();
        int r8 = r2;
    L6:
        if (r8 < 2013) goto L28;
        if (r20 == 1) goto L10;
    L14:
        Calendar r7 = Calendar.getInstance();
        r7.set(r8, r20, r19 + 1, 0, 0, 0);
        long r14 = r7.getTimeInMillis();
        if (r14 > r4) goto L21;
        r7.set(r8, r20, r19 + 2, 0, 0, 0);
        long r9 = r7.getTimeInMillis() - 1;
        if (r8 != r2) goto L20;
        r18.add(new DateData(LocaleController.getInstance().getFormatterDayMonth().format(r14), r14, r9, null));
        goto L21
    L20:
        r18.add(new DateData(LocaleController.getInstance().getFormatterYearMax().format(r14), r14, r9, null));
    L21:
        r8 = r8 - 1;
        goto L6
    L10:
        if (r19 != 28) goto L14;
        if (r6.isLeapYear(r8) == true) goto L14;
    L28:
        return;
    }

    private static void createForMonthYear(ArrayList<DateData> r13, int r14, int r15) {
        int r0 = Calendar.getInstance().get(1);
        long r2 = Calendar.getInstance().getTimeInMillis();
        if (r15 < 2013) goto L10;
        if (r15 > r0) goto L11;
        Calendar r5 = Calendar.getInstance();
        r5.set(r15, r14, 1, 0, 0, 0);
        long r8 = r5.getTimeInMillis();
        if (r8 > r2) goto L12;
        r5.add(2, 1);
        long r10 = r5.getTimeInMillis() - 1;
        r13.add(new DateData(LocaleController.getInstance().getFormatterMonthYear().format(r8), r8, r10, null));
        return;
    L12:
        return;
    L11:
        return;
    }

    public static void fillTipDates(String r26, ArrayList<DateData> r27) {
        r27.clear();
        if (r26 == null) goto L121;
        String r1 = r26.trim();
        if (r1.length() >= 3) goto L9;
        return;
    L9:
        if (LocaleController.getString(R.string.SearchTipToday).toLowerCase().startsWith(r1) == false) goto L11;
    L106:
        Calendar r9 = Calendar.getInstance();
        int r10 = r9.get(1);
        int r11 = r9.get(2);
        int r12 = r9.get(5);
        r9.set(r10, r11, r12, 0, 0, 0);
        long r15 = r9.getTimeInMillis();
        r9.set(r10, r11, r12 + 1, 0, 0, 0);
        r27.add(new DateData(LocaleController.getString(R.string.SearchTipToday), r15, r9.getTimeInMillis() - 1, null));
        return;
    L11:
        if ("today".startsWith(r1) == true) goto L106;
        if (LocaleController.getString(R.string.SearchTipYesterday).toLowerCase().startsWith(r1) == false) goto L16;
    L104:
        Calendar r152 = Calendar.getInstance();
        int r16 = r152.get(1);
        int r17 = r152.get(2);
        int r18 = r152.get(5);
        r152.set(r16, r17, r18, 0, 0, 0);
        long r13 = r152.getTimeInMillis() - DurationKt.MILLIS_IN_DAY;
        r152.set(r16, r17, r18 + 1, 0, 0, 0);
        r27.add(new DateData(LocaleController.getString(R.string.SearchTipYesterday), r13, r152.getTimeInMillis() - 86400001, null));
        return;
    L16:
        if ("yesterday".startsWith(r1) == true) goto L104;
        int r2 = getDayOfWeek(r1);
        if (r2 < 0) goto L25;
        Calendar r92 = Calendar.getInstance();
        long r102 = r92.getTimeInMillis();
        r92.set(7, r2);
        if (r92.getTimeInMillis() <= r102) goto L23;
        r92.setTimeInMillis(r92.getTimeInMillis() - 604800000);
    L23:
        int r103 = r92.get(1);
        int r112 = r92.get(2);
        int r122 = r92.get(5);
        r92.set(r103, r112, r122, 0, 0, 0);
        long r14 = r92.getTimeInMillis();
        r92.set(r103, r112, r122 + 1, 0, 0, 0);
        r27.add(new DateData(LocaleController.getInstance().getFormatterWeekLong().format(r14), r14, r92.getTimeInMillis() - 1, null));
        return;
    L25:
        Matcher r22 = shortDate.matcher(r1);
        if (r22.matches() == false) goto L41;
        String r19 = r22.group(1);
        String r23 = r22.group(3);
        int r110 = Integer.parseInt(r19);
        int r24 = Integer.parseInt(r23);
        if (r110 <= 0) goto L37;
        if (r110 > 31) goto L37;
        if (r24 < 2013) goto L34;
        if (r110 > 12) goto L34;
        createForMonthYear(r27, r110 - 1, r24);
        return;
    L34:
        if (r24 > 12) goto L103;
        createForDayMonth(r27, r110 - 1, r24 - 1);
        return;
    L103:
        return;
    L37:
        if (r110 < 2013) goto L112;
        if (r24 > 12) goto L113;
        createForMonthYear(r27, r24 - 1, r110);
        return;
    L113:
        return;
    L112:
        return;
    L41:
        Matcher r25 = longDate.matcher(r1);
        if (r25.matches() == false) goto L58;
        String r111 = r25.group(1);
        String r3 = r25.group(3);
        String r4 = r25.group(5);
        if (r25.group(2).equals(r25.group(4)) == false) goto L123;
        int r153 = Integer.parseInt(r111);
        int r142 = Integer.parseInt(r3) - 1;
        int r113 = Integer.parseInt(r4);
        if (r113 >= 10) goto L49;
    L51:
        int r132 = r113;
        int r114 = Calendar.getInstance().get(1);
        if (validDateForMont(r153 - 1, r142) == false) goto L114;
        if (r132 < 2013) goto L115;
        if (r132 > r114) goto L116;
        Calendar r162 = Calendar.getInstance();
        r162.set(r132, r142, r153, 0, 0, 0);
        long r115 = r162.getTimeInMillis();
        r162.set(r132, r142, r153 + 1, 0, 0, 0);
        r27.add(new DateData(LocaleController.getInstance().getFormatterYearMax().format(r115), r115, r162.getTimeInMillis() - 1, null));
        return;
    L116:
        return;
    L115:
        return;
    L114:
        return;
    L49:
        if (r113 > 99) goto L51;
        r113 = r113 + CastStatusCodes.AUTHENTICATION_FAILED;
        goto L51
    L123:
        return;
    L58:
        if (yearPatter.matcher(r1).matches() == false) goto L67;
        int r133 = Integer.valueOf(r1).intValue();
        int r116 = Calendar.getInstance().get(1);
        if (r133 >= 2013) goto L64;
        int r154 = r116;
    L62:
        if (r154 < 2013) goto L124;
        Calendar r143 = Calendar.getInstance();
        r143.set(r154, 0, 1, 0, 0, 0);
        long r117 = r143.getTimeInMillis();
        r143.set(r154 + 1, 0, 1, 0, 0, 0);
        r27.add(new DateData(Integer.toString(r154), r117, r143.getTimeInMillis() - 1, null));
        r154 = r154 - 1;
        goto L62
    L124:
        return;
    L64:
        if (r133 > r116) goto L117;
        Calendar r163 = Calendar.getInstance();
        r163.set(r133, 0, 1, 0, 0, 0);
        long r118 = r163.getTimeInMillis();
        r163.set(r133 + 1, 0, 1, 0, 0, 0);
        r27.add(new DateData(Integer.toString(r133), r118, r163.getTimeInMillis() - 1, null));
        return;
    L117:
        return;
    L67:
        Matcher r28 = monthYearOrDayPatter.matcher(r1);
        if (r28.matches() == false) goto L79;
        String r32 = r28.group(1);
        String r29 = r28.group(2);
        int r33 = getMonth(r32);
        if (r33 < 0) goto L79;
        int r210 = Integer.valueOf(r29).intValue();
        if (r210 <= 0) goto L76;
        if (r210 > 31) goto L76;
        createForDayMonth(r27, r210 - 1, r33);
        return;
    L76:
        if (r210 < 2013) goto L79;
        createForMonthYear(r27, r33, r210);
        return;
    L79:
        Matcher r211 = yearOrDayAndMonthPatter.matcher(r1);
        if (r211.matches() == false) goto L91;
        String r34 = r211.group(1);
        int r212 = getMonth(r211.group(2));
        if (r212 < 0) goto L91;
        int r35 = Integer.valueOf(r34).intValue();
        if (r35 <= 0) goto L88;
        if (r35 > 31) goto L88;
        createForDayMonth(r27, r35 - 1, r212);
        return;
    L88:
        if (r35 < 2013) goto L91;
        createForMonthYear(r27, r212, r35);
    L91:
        if (TextUtils.isEmpty(r1) == false) goto L93;
        return;
    L93:
        if (r1.length() <= 2) goto L119;
        int r144 = getMonth(r1);
        long r119 = Calendar.getInstance().getTimeInMillis();
        if (r144 < 0) goto L120;
        int r134 = Calendar.getInstance().get(1);
    L97:
        if (r134 < 2013) goto L125;
        Calendar r123 = Calendar.getInstance();
        r123.set(r134, r144, 1, 0, 0, 0);
        long r36 = r123.getTimeInMillis();
        if (r36 > r119) goto L102;
        r123.add(2, 1);
        r27.add(new DateData(LocaleController.getInstance().getFormatterMonthYear().format(r36), r36, r123.getTimeInMillis() - 1, null));
    L102:
        r134 = r134 - 1;
        goto L97
    L125:
        return;
    L120:
        return;
    L119:
        return;
    }

    public static int getDayOfWeek(String r6) {
        Calendar r0 = Calendar.getInstance();
        if (r6.length() > 3) goto L5;
        return -1;
    L5:
        SimpleDateFormat r1 = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        int r2 = 0;
    L7:
        if (r2 >= 7) goto L15;
        r0.set(7, r2);
        if (LocaleController.getInstance().getFormatterWeekLong().format(r0.getTime()).toLowerCase().startsWith(r6) == true) goto L13;
        if (r1.format(r0.getTime()).toLowerCase().startsWith(r6) == true) goto L13;
        r2 = r2 + 1;
    L13:
        return r2;
    L15:
        return -1;
    }

    public static int getMonth(String r13) {
        String[] r0 = {LocaleController.getString(R.string.January).toLowerCase(), LocaleController.getString(R.string.February).toLowerCase(), LocaleController.getString(R.string.March).toLowerCase(), LocaleController.getString(R.string.April).toLowerCase(), LocaleController.getString(R.string.May).toLowerCase(), LocaleController.getString(R.string.June).toLowerCase(), LocaleController.getString(R.string.July).toLowerCase(), LocaleController.getString(R.string.August).toLowerCase(), LocaleController.getString(R.string.September).toLowerCase(), LocaleController.getString(R.string.October).toLowerCase(), LocaleController.getString(R.string.November).toLowerCase(), LocaleController.getString(R.string.December).toLowerCase()};
        String[] r2 = new String[12];
        Calendar r3 = Calendar.getInstance();
        int r10 = 1;
    L3:
        if (r10 > 12) goto L5;
        r3.set(0, 0, 0, 0, 0, 0);
        r3.set(2, r10);
        r2[r10 - 1] = r3.getDisplayName(2, 2, Locale.ENGLISH).toLowerCase();
        r10 = r10 + 1;
        goto L3
    L5:
        int r32 = 0;
    L6:
        if (r32 >= 12) goto L14;
        if (r2[r32].startsWith(r13) == true) goto L13;
        if (r0[r32].startsWith(r13) == true) goto L13;
        r32 = r32 + 1;
    L13:
        return r32;
    L14:
        return -1;
    }

    private static boolean validDateForMont(int r1, int r2) {
        if (r2 >= 0) goto L4;
        return false;
    L4:
        if (r2 >= 12) goto L12;
        if (r1 >= 0) goto L7;
        return false;
    L7:
        if (r1 >= numberOfDaysEachMonth[r2]) goto L14;
        return true;
    L14:
        return false;
    L12:
        return false;
    }

    public MediaFilterData getFilterAt(int r2) {
        if (this.usersFilters.isEmpty() == false) goto L7;
        return filters[r2];
    L7:
        return this.usersFilters.get(r2);
    }

    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r0 = new ArrayList();
        r0.add(new ThemeDescription(this, 0, null, null, null, null, Theme.key_graySection));
        r0.add(new ThemeDescription(this, 0, null, null, null, null, Theme.key_graySectionText));
        return r0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onDraw(Canvas r8) {
        super.onDraw(r8);
        if (this.drawDivider == false) goto L6;
        r8.drawRect(0.0f, getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight(), Theme.dividerPaint);
        return;
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r2) {
        if (isEnabled() == true) goto L7;
        return false;
    L7:
        return super.onInterceptTouchEvent(r2);
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        if (isEnabled() == true) goto L7;
        return false;
    L7:
        return super.onTouchEvent(r2);
    }

    public void setUsersAndDates(ArrayList<Object> r12, ArrayList<DateData> r13, boolean r14) {
        this.oldItems.clear();
        this.oldItems.addAll(this.usersFilters);
        this.usersFilters.clear();
        if (r12 == null) goto L21;
        int r2 = 0;
    L6:
        if (r2 >= r12.size()) goto L21;
        Object r3 = r12.get(r2);
        if ((r3 instanceof TLRPC.User) == false) goto L15;
        TLRPC.User r32 = (TLRPC.User) r3;
        if (UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().id != r32.id) goto L12;
        String r4 = LocaleController.getString(R.string.SavedMessages);
    L13:
        MediaFilterData r6 = new MediaFilterData(R.drawable.search_users_filled, r4, null, 4);
        r6.setUser(r32);
        this.usersFilters.add(r6);
    L20:
        r2 = r2 + 1;
        goto L6
    L12:
        r4 = ContactsController.formatName(r32.first_name, r32.last_name, 10);
        goto L13
    L15:
        if ((r3 instanceof TLRPC.Chat) == false) goto L20;
        TLRPC.Chat r33 = (TLRPC.Chat) r3;
        String r42 = r33.title;
        if (r42.length() <= 12) goto L19;
        r42 = String.format("%s...", new Object[]{r42.substring(0, 10)});
    L19:
        MediaFilterData r62 = new MediaFilterData(R.drawable.search_users_filled, r42, null, 4);
        r62.setUser(r33);
        this.usersFilters.add(r62);
    L21:
        if (r13 == null) goto L26;
        int r122 = 0;
    L24:
        if (r122 >= r13.size()) goto L26;
        DateData r22 = r13.get(r122);
        MediaFilterData r34 = new MediaFilterData(R.drawable.search_date_filled, r22.title, null, 6);
        r34.setDate(r22);
        this.usersFilters.add(r34);
        r122 = r122 + 1;
    L26:
        if (r14 == false) goto L29;
        this.usersFilters.add(new MediaFilterData(R.drawable.chats_archive, R.string.ArchiveSearchFilter, null, 7));
    L29:
        if (getAdapter() == null) goto L41;
        UpdateCallback r123 = new UpdateCallback(getAdapter(), null);
        DiffUtil.calculateDiff(this.diffUtilsCallback).dispatchUpdatesTo(r123);
        if (this.usersFilters.isEmpty() == false) goto L33;
        return;
    L33:
        if (r123.changed == false) goto L43;
        this.layoutManager.scrollToPositionWithOffset(0, 0);
        return;
    L43:
        return;
    }

    public void updateColors() {
        getRecycledViewPool().clear();
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L9;
        View r2 = getChildAt(r1);
        if ((r2 instanceof FilterView) == false) goto L8;
        FilterView.m5900$$Nest$mupdateColors((FilterView) r2);
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        int r12 = 0;
    L11:
        if (r12 >= getCachedChildCount()) goto L17;
        View r22 = getCachedChildAt(r12);
        if ((r22 instanceof FilterView) == false) goto L15;
        FilterView.m5900$$Nest$mupdateColors((FilterView) r22);
    L15:
        r12 = r12 + 1;
    L17:
        if (r0 >= getAttachedScrapChildCount()) goto L22;
        View r13 = getAttachedScrapChildAt(r0);
        if ((r13 instanceof FilterView) == false) goto L21;
        FilterView.m5900$$Nest$mupdateColors((FilterView) r13);
    L21:
        r0 = r0 + 1;
        goto L17
    L22:
        setSelectorDrawableColor(getThemedColor(Theme.key_listSelector));
    }
}
