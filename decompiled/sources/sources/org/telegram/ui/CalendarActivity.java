package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import j$.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.HideViewAfterAnimation;
import org.telegram.ui.Components.HintView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoryViewer;

/* loaded from: classes6.dex */
public class CalendarActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    TextPaint activeTextPaint;
    CalendarAdapter adapter;
    BackDrawable backDrawable;
    Paint blackoutPaint;
    private View blurredView;
    private FrameLayout bottomBar;
    private int calendarType;
    Callback callback;
    private boolean canClearHistory;
    ChatActivity chatActivity;
    private boolean checkEnterItems;
    FrameLayout contentView;
    private int dateSelectedEnd;
    private int dateSelectedStart;
    private long dialogId;
    boolean endReached;
    private boolean inSelectionMode;
    private boolean isOpened;
    int lastDaysSelected;
    int lastId;
    boolean lastInSelectionMode;
    LinearLayoutManager layoutManager;
    RecyclerListView listView;
    private boolean loading;
    private SpoilerEffect mediaSpoilerEffect;
    SparseArray<SparseArray<PeriodDay>> messagesByYearMounth;
    private int minDate;
    int minMontYear;
    int monthCount;
    private Path path;
    private int photosVideosTypeFilter;
    TextView removeDaysButton;
    TextView selectDaysButton;
    HintView selectDaysHint;
    private Paint selectOutlinePaint;
    private Paint selectPaint;
    int selectedMonth;
    int selectedYear;
    private ValueAnimator selectionAnimator;
    int startFromMonth;
    int startFromYear;
    int startOffset;
    private StoriesController.StoriesList storiesList;
    private int storiesPlaceDay;
    private StoryViewer.HolderDrawAbove storiesPlaceDrawAbove;
    private StoryViewer.PlaceProvider storiesPlaceProvider;
    TextPaint textPaint;
    TextPaint textPaint2;
    private long topicId;

    /* renamed from: org.telegram.ui.CalendarActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements StoryViewer.PlaceProvider {
        final /* synthetic */ CalendarActivity this$0;

        public static /* synthetic */ void $r8$lambda$4elQXquX_Ig0lkoD6bKPO68jbI0(AnonymousClass1 r0, Canvas r1, RectF r2, float r3, boolean r4) {
            r0.lambda$findView$0(r1, r2, r3, r4);
        }

        public AnonymousClass1(CalendarActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$findView$0(Canvas r4, RectF r5, float r6, boolean r7) {
            this.this$0.blackoutPaint.setAlpha((int) (80.0f * r6));
            float r72 = AndroidUtilities.lerp(0.0f, Math.min(r5.width(), r5.height()) / 2.0f, r6);
            r4.drawRoundRect(r5, r72, r72, this.this$0.blackoutPaint);
            float r62 = Utilities.clamp((r6 - 0.5f) / 0.5f, 1.0f, 0.0f);
            if (r62 <= 0.0f) goto L6;
            int r73 = this.this$0.activeTextPaint.getAlpha();
            this.this$0.activeTextPaint.setAlpha((int) (r73 * r62));
            r4.save();
            float r63 = Math.min(2.0f, Math.min(r5.height(), r5.width()) / AndroidUtilities.dp(44.0f));
            r4.scale(r63, r63, r5.centerX(), r5.centerY());
            r4.drawText(Integer.toString(CalendarActivity.m6496$$Nest$fgetstoriesPlaceDay(this.this$0) + 1), r5.centerX(), r5.centerY() + AndroidUtilities.dp(5.0f), this.this$0.activeTextPaint);
            r4.restore();
            this.this$0.activeTextPaint.setAlpha(r73);
            return;
        }

        @Override // org.telegram.ui.Stories.StoryViewer.PlaceProvider
        public boolean findView(long r3, int r5, int r6, int r7, StoryViewer.TransitionViewHolder r8) {
            if (this.this$0.listView != null) goto L5;
            return false;
        L5:
            int r32 = 0;
        L7:
            if (r32 >= this.this$0.listView.getChildCount()) goto L31;
            View r52 = this.this$0.listView.getChildAt(r32);
            if ((r52 instanceof MonthView) == false) goto L30;
            MonthView r53 = (MonthView) r52;
            if (r53.messagesByDays == null) goto L30;
            int r72 = 0;
        L16:
            if (r72 >= r53.messagesByDays.size()) goto L30;
            ArrayList<Integer> r0 = r53.messagesByDays.valueAt(r72).storyItems;
            if (r0 == null) goto L29;
            if (r0.contains(Integer.valueOf(r6)) == false) goto L29;
            CalendarActivity r33 = this.this$0;
            int r62 = r53.messagesByDays.keyAt(r72);
            CalendarActivity.m6505$$Nest$fputstoriesPlaceDay(r33, r62);
            ImageReceiver r34 = r53.imagesByDays.get(r62);
            if (r34 != null) goto L24;
            return false;
        L24:
            r8.storyImage = r34;
            if (CalendarActivity.m6497$$Nest$fgetstoriesPlaceDrawAbove(this.this$0) != null) goto L27;
            CalendarActivity.m6506$$Nest$fputstoriesPlaceDrawAbove(this.this$0, new CalendarActivity$1$$ExternalSyntheticLambda0(this));
        L27:
            r8.drawAbove = CalendarActivity.m6497$$Nest$fgetstoriesPlaceDrawAbove(this.this$0);
            r8.view = r53;
            r8.clipParent = this.this$0.fragmentView;
            r8.clipTop = AndroidUtilities.dp(36.0f);
            r8.clipBottom = this.this$0.fragmentView.getBottom();
            r8.avatarImage = null;
            return true;
        L29:
            r72 = r72 + 1;
        L30:
            r32 = r32 + 1;
            goto L7
        L31:
            return false;
        }

        @Override // org.telegram.ui.Stories.StoryViewer.PlaceProvider
        public void preLayout(long r1, int r3, Runnable r4) {
            if (this.this$0.listView != null) goto L5;
            r4.run();
        L5:
            this.this$0.listView.post(r4);
        }
    }

    /* renamed from: org.telegram.ui.CalendarActivity$10, reason: invalid class name */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ CalendarActivity this$0;

        public AnonymousClass10(CalendarActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r4) {
            int r42 = 0;
        L4:
            if (r42 >= this.this$0.listView.getChildCount()) goto L6;
            MonthView.m6517$$Nest$mstartSelectionAnimation((MonthView) this.this$0.listView.getChildAt(r42), CalendarActivity.m6486$$Nest$fgetdateSelectedStart(this.this$0), CalendarActivity.m6485$$Nest$fgetdateSelectedEnd(this.this$0));
            r42 = r42 + 1;
            goto L4
        }
    }

    /* renamed from: org.telegram.ui.CalendarActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        int lastSize;
        final /* synthetic */ CalendarActivity this$0;

        public AnonymousClass2(CalendarActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r12 = (getMeasuredHeight() + getMeasuredWidth()) << 16;
            if (this.lastSize == r12) goto L6;
            this.lastSize = r12;
            this.this$0.adapter.notifyDataSetChanged();
            return;
        }
    }

    /* renamed from: org.telegram.ui.CalendarActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView {
        final /* synthetic */ CalendarActivity this$0;

        public AnonymousClass3(CalendarActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r1) {
            super.dispatchDraw(r1);
            CalendarActivity.m6500$$Nest$fputcheckEnterItems(this.this$0, false);
        }
    }

    /* renamed from: org.telegram.ui.CalendarActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ CalendarActivity this$0;

        public AnonymousClass4(CalendarActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            CalendarActivity.m6508$$Nest$mcheckLoadNext(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.CalendarActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends View {
        final /* synthetic */ CalendarActivity this$0;
        final /* synthetic */ String[] val$daysOfWeek;
        final /* synthetic */ Drawable val$headerShadowDrawable;

        public AnonymousClass5(CalendarActivity r1, Context r2, String[] r3, Drawable r4) {
            this.this$0 = r1;
            this.val$daysOfWeek = r3;
            this.val$headerShadowDrawable = r4;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            float r0 = getMeasuredWidth() / 7.0f;
            int r2 = 0;
        L4:
            if (r2 >= 7) goto L6;
            r8.drawText(this.val$daysOfWeek[r2], (r2 * r0) + (r0 / 2.0f), ((getMeasuredHeight() - AndroidUtilities.dp(2.0f)) / 2.0f) + AndroidUtilities.dp(5.0f), this.this$0.textPaint2);
            r2 = r2 + 1;
            goto L4
        L6:
            this.val$headerShadowDrawable.setBounds(0, getMeasuredHeight() - AndroidUtilities.dp(3.0f), getMeasuredWidth(), getMeasuredHeight());
            this.val$headerShadowDrawable.draw(r8);
        }
    }

    /* renamed from: org.telegram.ui.CalendarActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ CalendarActivity this$0;

        public AnonymousClass6(CalendarActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 == (-1)) goto L5;
            return;
        L5:
            if (CalendarActivity.m6486$$Nest$fgetdateSelectedStart(this.this$0) == 0) goto L7;
        L13:
            CalendarActivity.m6503$$Nest$fputinSelectionMode(this.this$0, false);
            CalendarActivity.m6502$$Nest$fputdateSelectedStart(this.this$0, 0);
            CalendarActivity.m6501$$Nest$fputdateSelectedEnd(this.this$0, 0);
            CalendarActivity.m6512$$Nest$mupdateTitle(this.this$0);
            CalendarActivity.m6507$$Nest$manimateSelection(this.this$0);
            return;
        L7:
            if (CalendarActivity.m6485$$Nest$fgetdateSelectedEnd(this.this$0) != 0) goto L13;
            if (CalendarActivity.m6488$$Nest$fgetinSelectionMode(this.this$0) == true) goto L13;
            this.this$0.finishFragment();
        }
    }

    /* renamed from: org.telegram.ui.CalendarActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends FrameLayout {
        final /* synthetic */ CalendarActivity this$0;

        public AnonymousClass7(CalendarActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            r7.drawRect(0.0f, 0.0f, getMeasuredWidth(), AndroidUtilities.getShadowHeight(), Theme.dividerPaint);
        }
    }

    /* renamed from: org.telegram.ui.CalendarActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements MessagesStorage.BooleanCallback {
        final /* synthetic */ CalendarActivity this$0;

        public AnonymousClass8(CalendarActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.messenger.MessagesStorage.BooleanCallback
        public void run(boolean r5) {
            this.this$0.finishFragment();
            if (CalendarActivity.access$1400(this.this$0) != null) goto L5;
        L10:
            CalendarActivity r0 = this.this$0;
            ChatActivity r2 = r0.chatActivity;
            if (r2 == null) goto L15;
            r2.deleteHistory(CalendarActivity.m6486$$Nest$fgetdateSelectedStart(r0), CalendarActivity.m6485$$Nest$fgetdateSelectedEnd(this.this$0) + 86400, r5);
            return;
        L15:
            return;
        L5:
            if (CalendarActivity.access$1500(this.this$0).getFragmentStack().size() < 2) goto L10;
            BaseFragment r02 = CalendarActivity.access$1700(this.this$0).getFragmentStack().get(CalendarActivity.access$1600(this.this$0).getFragmentStack().size() - 2);
            if ((r02 instanceof ChatActivity) == false) goto L14;
            ((ChatActivity) r02).deleteHistory(CalendarActivity.m6486$$Nest$fgetdateSelectedStart(this.this$0), CalendarActivity.m6485$$Nest$fgetdateSelectedEnd(this.this$0) + 86400, r5);
            return;
        }
    }

    /* renamed from: org.telegram.ui.CalendarActivity$9, reason: invalid class name */
    public class AnonymousClass9 implements ThemeDescription.ThemeDescriptionDelegate {
        final /* synthetic */ CalendarActivity this$0;

        public AnonymousClass9(CalendarActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ThemeDescription.ThemeDescriptionDelegate
        public void didSetColor() {
            CalendarActivity.m6510$$Nest$mupdateColors(this.this$0);
        }
    }

    public class CalendarAdapter extends RecyclerView.Adapter {
        final /* synthetic */ CalendarActivity this$0;

        private CalendarAdapter(CalendarActivity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ CalendarAdapter(CalendarActivity r1, CalendarActivityIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.monthCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r5) {
            return ((this.this$0.startFromYear - (r5 / 12)) * 100) + (r4.startFromMonth - (r5 % 12));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r6, int r7) {
            MonthView r62 = (MonthView) r6.itemView;
            CalendarActivity r0 = this.this$0;
            int r1 = r0.startFromYear - (r7 / 12);
            int r2 = r0.startFromMonth - (r7 % 12);
            if (r2 >= 0) goto L6;
            r2 = r2 + 12;
            r1 = r1 - 1;
        L6:
            if (r62.currentYear == r1) goto L8;
        L10:
            boolean r72 = false;
        L11:
            r62.setDate(r1, r2, r0.messagesByYearMounth.get((r1 * 100) + r2), r72);
            MonthView.m6517$$Nest$mstartSelectionAnimation(r62, CalendarActivity.m6486$$Nest$fgetdateSelectedStart(this.this$0), CalendarActivity.m6485$$Nest$fgetdateSelectedEnd(this.this$0));
            MonthView.m6516$$Nest$msetSelectionValue(r62, 1.0f);
            CalendarActivity.m6511$$Nest$mupdateRowSelections(this.this$0, r62, false);
            return;
        L8:
            if (r62.currentMonthInYear != r2) goto L10;
            r72 = true;
            goto L11
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            return new RecyclerListView.Holder(new MonthView(this.this$0, r2.getContext()));
        }
    }

    public interface Callback {
        void onDateSelected(int r1, int r2);
    }

    public class MonthView extends FrameLayout {
        boolean attached;
        int cellCount;
        int currentMonthInYear;
        int currentYear;
        int daysInMonth;
        GestureDetectorCompat gestureDetector;
        SparseArray<ImageReceiver> imagesByDays;
        SparseArray<PeriodDay> messagesByDays;
        private SparseArray<ValueAnimator> rowAnimators;
        private SparseArray<RowAnimationValue> rowSelectionPos;
        int startDayOfWeek;
        int startMonthTime;
        final /* synthetic */ CalendarActivity this$0;
        SimpleTextView titleView;

        /* renamed from: org.telegram.ui.CalendarActivity$MonthView$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnClickListener {
            final /* synthetic */ MonthView this$1;
            final /* synthetic */ CalendarActivity val$this$0;

            public AnonymousClass1(MonthView r1, CalendarActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View r6) {
                MonthView r62 = this.this$1;
                if (r62.messagesByDays != null) goto L6;
                return;
            L6:
                if (CalendarActivity.m6488$$Nest$fgetinSelectionMode(r62.this$0) == false) goto L23;
                int r0 = 0;
                int r1 = -1;
                int r2 = -1;
            L8:
                MonthView r3 = this.this$1;
                if (r0 >= r3.daysInMonth) goto L16;
                PeriodDay r32 = r3.messagesByDays.get(r0, null);
                if (r32 == null) goto L15;
                if (r1 != (-1)) goto L14;
                r1 = r32.date;
            L14:
                r2 = r32.date;
            L15:
                r0 = r0 + 1;
                goto L8
            L16:
                if (r1 < 0) goto L24;
                if (r2 < 0) goto L25;
                CalendarActivity.m6502$$Nest$fputdateSelectedStart(r3.this$0, r1);
                CalendarActivity.m6501$$Nest$fputdateSelectedEnd(this.this$1.this$0, r2);
                CalendarActivity.m6512$$Nest$mupdateTitle(this.this$1.this$0);
                CalendarActivity.m6507$$Nest$manimateSelection(this.this$1.this$0);
                return;
            L25:
                return;
            L24:
                return;
            }
        }

        /* renamed from: org.telegram.ui.CalendarActivity$MonthView$2, reason: invalid class name */
        public class AnonymousClass2 extends GestureDetector.SimpleOnGestureListener {
            final /* synthetic */ MonthView this$1;
            final /* synthetic */ Context val$context;
            final /* synthetic */ CalendarActivity val$this$0;

            /* renamed from: org.telegram.ui.CalendarActivity$MonthView$2$1, reason: invalid class name */
            public class AnonymousClass1 implements MessagesStorage.BooleanCallback {
                final /* synthetic */ AnonymousClass2 this$2;
                final /* synthetic */ BaseFragment val$fragment;

                public AnonymousClass1(AnonymousClass2 r1, BaseFragment r2) {
                    this.this$2 = r1;
                    this.val$fragment = r2;
                }

                @Override // org.telegram.messenger.MessagesStorage.BooleanCallback
                public void run(boolean r4) {
                    this.this$2.this$1.this$0.finishFragment();
                    ((ChatActivity) this.val$fragment).deleteHistory(CalendarActivity.m6486$$Nest$fgetdateSelectedStart(this.this$2.this$1.this$0), CalendarActivity.m6485$$Nest$fgetdateSelectedEnd(this.this$2.this$1.this$0) + 86400, r4);
                }
            }

            /* renamed from: org.telegram.ui.CalendarActivity$MonthView$2$2, reason: invalid class name and collision with other inner class name */
            public class C00362 extends View {
                final /* synthetic */ AnonymousClass2 this$2;

                public C00362(AnonymousClass2 r1, Context r2) {
                    this.this$2 = r1;
                    super(r2);
                }

                @Override // android.view.View
                public void setAlpha(float r1) {
                    super.setAlpha(r1);
                    View r0 = this.this$2.this$1.this$0.fragmentView;
                    if (r0 == null) goto L6;
                    r0.invalidate();
                    return;
                }
            }

            public static /* synthetic */ void $r8$lambda$1Vc1KC2fmWYHfzmVaUqsB7Lekfs(AnonymousClass2 r0, BaseFragment r1, PeriodDay r2) {
                r0.lambda$onLongPress$0(r1, r2);
            }

            public static /* synthetic */ void $r8$lambda$CA6qgGG_useQBp5lrbx1tAXapQA(AnonymousClass2 r0, View r1) {
                r0.lambda$onLongPress$3(r1);
            }

            public static /* synthetic */ void $r8$lambda$VCOwIz_HyNcFW4065qCc2wcFb_8(AnonymousClass2 r0, View r1) {
                r0.lambda$onLongPress$4(r1);
            }

            public static /* synthetic */ void $r8$lambda$b8l7Hs6S_aoKbHXINl6SgdOCBjw(AnonymousClass2 r0, PeriodDay r1, View r2) {
                r0.lambda$onLongPress$2(r1, r2);
            }

            public static /* synthetic */ void $r8$lambda$n5Gt_ZOKInUDygZYawqbW1nQof4(AnonymousClass2 r0, PeriodDay r1, View r2) {
                r0.lambda$onLongPress$1(r1, r2);
            }

            public AnonymousClass2(MonthView r1, CalendarActivity r2, Context r3) {
                this.this$1 = r1;
                this.val$this$0 = r2;
                this.val$context = r3;
            }

            private PeriodDay getDayAtCoord(float r14, float r15) {
                MonthView r0 = this.this$1;
                if (r0.messagesByDays != null) goto L5;
                return null;
            L5:
                int r1 = r0.startDayOfWeek;
                float r02 = r0.getMeasuredWidth() / 7.0f;
                float r3 = AndroidUtilities.dp(52.0f);
                int r5 = AndroidUtilities.dp(44.0f) / 2;
                int r7 = 0;
                int r8 = 0;
            L7:
                if (r7 >= this.this$1.daysInMonth) goto L23;
                float r9 = (r1 * r02) + (r02 / 2.0f);
                float r11 = ((r8 * r3) + (r3 / 2.0f)) + AndroidUtilities.dp(44.0f);
                float r10 = r5;
                if (r14 >= (r9 - r10)) goto L11;
            L19:
                r1 = r1 + 1;
                if (r1 < 7) goto L22;
                r8 = r8 + 1;
                r1 = 0;
            L22:
                r7 = r7 + 1;
                goto L7
            L11:
                if (r14 > (r9 + r10)) goto L19;
                if (r15 < (r11 - r10)) goto L19;
                if (r15 > (r11 + r10)) goto L19;
                PeriodDay r92 = this.this$1.messagesByDays.get(r7, null);
                if (r92 == null) goto L19;
                return r92;
            L23:
                return null;
            }

            private /* synthetic */ void lambda$onLongPress$0(BaseFragment r1, PeriodDay r2) {
                this.this$1.this$0.finishFragment();
                ((ChatActivity) r1).jumpToDate(r2.date);
            }

            private /* synthetic */ void lambda$onLongPress$1(PeriodDay r3, View r4) {
                if (CalendarActivity.access$900(this.this$1.this$0) != null) goto L5;
            L9:
                this.this$1.this$0.finishPreviewFragment();
                return;
            L5:
                if (CalendarActivity.access$1000(this.this$1.this$0).getFragmentStack().size() < 3) goto L9;
                BaseFragment r42 = CalendarActivity.access$1200(this.this$1.this$0).getFragmentStack().get(CalendarActivity.access$1100(this.this$1.this$0).getFragmentStack().size() - 3);
                if ((r42 instanceof ChatActivity) == false) goto L9;
                AndroidUtilities.runOnUIThread(new CalendarActivity$MonthView$2$$ExternalSyntheticLambda4(this, r42, r3), 300);
                goto L9
            }

            private /* synthetic */ void lambda$onLongPress$2(PeriodDay r1, View r2) {
                CalendarActivity r22 = this.this$1.this$0;
                int r12 = r1.date;
                CalendarActivity.m6501$$Nest$fputdateSelectedEnd(r22, r12);
                CalendarActivity.m6502$$Nest$fputdateSelectedStart(r22, r12);
                CalendarActivity.m6503$$Nest$fputinSelectionMode(this.this$1.this$0, true);
                CalendarActivity.m6512$$Nest$mupdateTitle(this.this$1.this$0);
                CalendarActivity.m6507$$Nest$manimateSelection(this.this$1.this$0);
                this.this$1.this$0.finishPreviewFragment();
            }

            private /* synthetic */ void lambda$onLongPress$3(View r9) {
                if (CalendarActivity.access$600(this.this$1.this$0).getFragmentStack().size() < 3) goto L7;
                BaseFragment r92 = CalendarActivity.access$800(this.this$1.this$0).getFragmentStack().get(CalendarActivity.access$700(this.this$1.this$0).getFragmentStack().size() - 3);
                if ((r92 instanceof ChatActivity) == false) goto L7;
                CalendarActivity r1 = this.this$1.this$0;
                AlertsCreator.createClearDaysDialogAlert(r1, 1, r1.getMessagesController().getUser(Long.valueOf(CalendarActivity.m6487$$Nest$fgetdialogId(this.this$1.this$0))), null, false, new AnonymousClass1(this, r92), null);
            L7:
                this.this$1.this$0.finishPreviewFragment();
            }

            private /* synthetic */ void lambda$onLongPress$4(View r1) {
                this.this$1.this$0.finishPreviewFragment();
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent r1) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent r9) {
                super.onLongPress(r9);
                if (CalendarActivity.m6482$$Nest$fgetcalendarType(this.this$1.this$0) == 0) goto L5;
                return;
            L5:
                if (AndroidUtilities.isTablet() == true) goto L25;
                PeriodDay r92 = getDayAtCoord(r9.getX(), r9.getY());
                if (r92 != null) goto L21;
                return;
            L21:
                this.this$1.performHapticFeedback(0);     // Catch: Exception -> L20
            L11:
                Bundle r1 = new Bundle();
                long r2 = CalendarActivity.m6487$$Nest$fgetdialogId(this.this$1.this$0);
                MonthView r3 = this.this$1;
                if (r2 <= 0) goto L14;
                r1.putLong("user_id", CalendarActivity.m6487$$Nest$fgetdialogId(r3.this$0));
            L15:
                r1.putInt("start_from_date", r92.date);
                r1.putBoolean("need_remove_previous_same_chat_activity", false);
                ChatActivity r22 = new ChatActivity(r1);
                ActionBarPopupWindow.ActionBarPopupWindowLayout r12 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(this.this$1.this$0.getParentActivity(), R.drawable.popup_fixed_alert, this.this$1.this$0.getResourceProvider());
                r12.setBackgroundColor(this.this$1.this$0.getThemedColor(Theme.key_actionBarDefaultSubmenuBackground));
                ActionBarMenuSubItem r32 = new ActionBarMenuSubItem(this.this$1.this$0.getParentActivity(), true, false);
                r32.setTextAndIcon(LocaleController.getString(R.string.JumpToDate), R.drawable.msg_message);
                r32.setMinimumWidth(160);
                r32.setOnClickListener(new CalendarActivity$MonthView$2$$ExternalSyntheticLambda0(this, r92));
                r12.addView(r32);
                if (CalendarActivity.m6483$$Nest$fgetcanClearHistory(this.this$1.this$0) == false) goto L18;
                ActionBarMenuSubItem r33 = new ActionBarMenuSubItem(this.this$1.this$0.getParentActivity(), false, false);
                r33.setTextAndIcon(LocaleController.getString(R.string.SelectThisDay), R.drawable.msg_select);
                r33.setMinimumWidth(160);
                r33.setOnClickListener(new CalendarActivity$MonthView$2$$ExternalSyntheticLambda1(this, r92));
                r12.addView(r33);
                ActionBarMenuSubItem r93 = new ActionBarMenuSubItem(this.this$1.this$0.getParentActivity(), false, true);
                r93.setTextAndIcon(LocaleController.getString(R.string.ClearHistory), R.drawable.msg_delete);
                r93.setMinimumWidth(160);
                r93.setOnClickListener(new CalendarActivity$MonthView$2$$ExternalSyntheticLambda2(this));
                r12.addView(r93);
            L18:
                r12.setFitItems(true);
                CalendarActivity.m6499$$Nest$fputblurredView(this.this$1.this$0, new C00362(this, this.val$context));
                CalendarActivity.m6481$$Nest$fgetblurredView(this.this$1.this$0).setOnClickListener(new CalendarActivity$MonthView$2$$ExternalSyntheticLambda3(this));
                CalendarActivity.m6481$$Nest$fgetblurredView(this.this$1.this$0).setVisibility(8);
                CalendarActivity.m6481$$Nest$fgetblurredView(this.this$1.this$0).setFitsSystemWindows(true);
                CalendarActivity.access$500(this.this$1.this$0).getOverlayContainerView().addView(CalendarActivity.m6481$$Nest$fgetblurredView(this.this$1.this$0), LayoutHelper.createFrame(-1, -1.0f));
                CalendarActivity.m6509$$Nest$mprepareBlurBitmap(this.this$1.this$0);
                this.this$1.this$0.presentFragmentAsPreviewWithMenu(r22, r12);
                return;
            L14:
                r1.putLong("chat_id", -CalendarActivity.m6487$$Nest$fgetdialogId(r3.this$0));
                goto L15
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            @SuppressLint({"NotifyDataSetChanged"})
            public boolean onSingleTapUp(MotionEvent r11) {
                if (CalendarActivity.access$000(this.this$1.this$0) != null) goto L6;
                return false;
            L6:
                if (CalendarActivity.m6482$$Nest$fgetcalendarType(this.this$1.this$0) != 1) goto L10;
                if (this.this$1.messagesByDays == null) goto L10;
            L11:
                PeriodDay r0 = getDayAtCoord(r11.getX(), r11.getY());
                if (r0 != null) goto L14;
            L21:
                MonthView r02 = this.this$1;
                if (r02.messagesByDays != null) goto L24;
            L68:
                return false;
            L24:
                if (CalendarActivity.m6488$$Nest$fgetinSelectionMode(r02.this$0) == false) goto L55;
                PeriodDay r112 = getDayAtCoord(r11.getX(), r11.getY());
                if (r112 == null) goto L68;
                if (CalendarActivity.m6494$$Nest$fgetselectionAnimator(this.this$1.this$0) == null) goto L31;
                CalendarActivity.m6494$$Nest$fgetselectionAnimator(this.this$1.this$0).cancel();
                CalendarActivity.m6504$$Nest$fputselectionAnimator(this.this$1.this$0, null);
            L31:
                if (CalendarActivity.m6486$$Nest$fgetdateSelectedStart(this.this$1.this$0) != 0) goto L37;
                if (CalendarActivity.m6485$$Nest$fgetdateSelectedEnd(this.this$1.this$0) != 0) goto L37;
                CalendarActivity r03 = this.this$1.this$0;
                int r113 = r112.date;
                CalendarActivity.m6501$$Nest$fputdateSelectedEnd(r03, r113);
                CalendarActivity.m6502$$Nest$fputdateSelectedStart(r03, r113);
            L54:
                CalendarActivity.m6512$$Nest$mupdateTitle(this.this$1.this$0);
                CalendarActivity.m6507$$Nest$manimateSelection(this.this$1.this$0);
            L37:
                if (CalendarActivity.m6486$$Nest$fgetdateSelectedStart(this.this$1.this$0) == r112.date) goto L39;
            L41:
                int r04 = CalendarActivity.m6486$$Nest$fgetdateSelectedStart(this.this$1.this$0);
                int r2 = r112.date;
                MonthView r3 = this.this$1;
                if (r04 != r2) goto L44;
                CalendarActivity r114 = r3.this$0;
                CalendarActivity.m6502$$Nest$fputdateSelectedStart(r114, CalendarActivity.m6485$$Nest$fgetdateSelectedEnd(r114));
                goto L54
            L44:
                int r05 = CalendarActivity.m6485$$Nest$fgetdateSelectedEnd(r3.this$0);
                int r22 = r112.date;
                MonthView r32 = this.this$1;
                if (r05 != r22) goto L48;
                CalendarActivity r115 = r32.this$0;
                CalendarActivity.m6501$$Nest$fputdateSelectedEnd(r115, CalendarActivity.m6486$$Nest$fgetdateSelectedStart(r115));
                goto L54
            L48:
                if (CalendarActivity.m6486$$Nest$fgetdateSelectedStart(r32.this$0) != CalendarActivity.m6485$$Nest$fgetdateSelectedEnd(this.this$1.this$0)) goto L53;
                int r06 = r112.date;
                int r23 = CalendarActivity.m6485$$Nest$fgetdateSelectedEnd(this.this$1.this$0);
                MonthView r33 = this.this$1;
                if (r06 <= r23) goto L52;
                CalendarActivity.m6501$$Nest$fputdateSelectedEnd(r33.this$0, r112.date);
                goto L54
            L52:
                CalendarActivity.m6502$$Nest$fputdateSelectedStart(r33.this$0, r112.date);
                goto L54
            L53:
                CalendarActivity r07 = this.this$1.this$0;
                int r116 = r112.date;
                CalendarActivity.m6501$$Nest$fputdateSelectedEnd(r07, r116);
                CalendarActivity.m6502$$Nest$fputdateSelectedStart(r07, r116);
                goto L54
            L39:
                if (CalendarActivity.m6485$$Nest$fgetdateSelectedEnd(this.this$1.this$0) != r112.date) goto L41;
                CalendarActivity r117 = this.this$1.this$0;
                CalendarActivity.m6501$$Nest$fputdateSelectedEnd(r117, 0);
                CalendarActivity.m6502$$Nest$fputdateSelectedStart(r117, 0);
                goto L54
            L55:
                PeriodDay r118 = getDayAtCoord(r11.getX(), r11.getY());
                if (r118 != null) goto L58;
            L64:
                if (r118 == null) goto L68;
                CalendarActivity r08 = this.this$1.this$0;
                if (r08.chatActivity == null) goto L68;
                r08.finishFragment();
                this.this$1.this$0.chatActivity.jumpToDate(r118.date);
                goto L68
            L58:
                if (CalendarActivity.access$100(this.this$1.this$0) == null) goto L64;
                if (CalendarActivity.access$200(this.this$1.this$0).getFragmentStack().size() < 2) goto L64;
                BaseFragment r09 = CalendarActivity.access$400(this.this$1.this$0).getFragmentStack().get(CalendarActivity.access$300(this.this$1.this$0).getFragmentStack().size() - 2);
                if ((r09 instanceof ChatActivity) == false) goto L68;
                this.this$1.this$0.finishFragment();
                ((ChatActivity) r09).jumpToDate(r118.date);
                goto L68
            L14:
                if (r0.messageObject == null) goto L21;
                CalendarActivity r24 = this.this$1.this$0;
                if (r24.callback == null) goto L21;
                StoriesController.StoriesList r25 = CalendarActivity.m6495$$Nest$fgetstoriesList(r24);
                MonthView r34 = this.this$1;
                if (r25 == null) goto L20;
                StoryViewer r35 = r34.this$0.getOrCreateStoryViewer();
                Context r4 = this.this$1.getContext();
                MessageObject r010 = r0.messageObject;
                r35.open(r4, r010.storyItem, r010.getId(), CalendarActivity.m6495$$Nest$fgetstoriesList(this.this$1.this$0), true, CalendarActivity.m6498$$Nest$fgetstoriesPlaceProvider(this.this$1.this$0));
                goto L21
            L20:
                r34.this$0.callback.onDateSelected(r0.messageObject.getId(), r0.startOffset);
                this.this$1.this$0.finishFragment();
            L10:
                if (CalendarActivity.m6495$$Nest$fgetstoriesList(this.this$1.this$0) == null) goto L21;
                goto L11
            }
        }

        /* renamed from: org.telegram.ui.CalendarActivity$MonthView$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ MonthView this$1;
            final /* synthetic */ boolean val$appear;
            final /* synthetic */ float val$cxTo1;
            final /* synthetic */ float val$cxTo2;
            final /* synthetic */ RowAnimationValue val$pr;
            final /* synthetic */ int val$row;
            final /* synthetic */ float val$toAlpha;

            public AnonymousClass3(MonthView r1, RowAnimationValue r2, float r3, float r4, float r5, int r6, boolean r7) {
                this.this$1 = r1;
                this.val$pr = r2;
                this.val$cxTo1 = r3;
                this.val$cxTo2 = r4;
                this.val$toAlpha = r5;
                this.val$row = r6;
                this.val$appear = r7;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator r2) {
                RowAnimationValue r22 = this.val$pr;
                r22.startX = this.val$cxTo1;
                r22.endX = this.val$cxTo2;
                r22.alpha = this.val$toAlpha;
                this.this$1.invalidate();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                MonthView.m6514$$Nest$fgetrowAnimators(this.this$1).remove(this.val$row);
                if (this.val$appear == true) goto L6;
                MonthView.m6515$$Nest$fgetrowSelectionPos(this.this$1).remove(this.val$row);
                return;
            }
        }

        /* renamed from: $r8$lambda$0tNCc1Zt-MRNfDyAiJjSXOsp6PQ, reason: not valid java name */
        public static /* synthetic */ void m6513$r8$lambda$0tNCc1ZtMRNfDyAiJjSXOsp6PQ(MonthView r0, RowAnimationValue r1, float r2, float r3, float r4, float r5, float r6, float r7, ValueAnimator r8) {
            r0.lambda$animateRow$1(r1, r2, r3, r4, r5, r6, r7, r8);
        }

        public static /* synthetic */ boolean $r8$lambda$OWe7e1otqIt__ic0YWumfSWFECU(MonthView r0, View r1) {
            return r0.lambda$new$0(r1);
        }

        /* renamed from: -$$Nest$fgetrowAnimators, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseArray m6514$$Nest$fgetrowAnimators(MonthView r0) {
            return r0.rowAnimators;
        }

        /* renamed from: -$$Nest$fgetrowSelectionPos, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseArray m6515$$Nest$fgetrowSelectionPos(MonthView r0) {
            return r0.rowSelectionPos;
        }

        /* renamed from: -$$Nest$msetSelectionValue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m6516$$Nest$msetSelectionValue(MonthView r0, float r1) {
            r0.setSelectionValue(r1);
        }

        /* renamed from: -$$Nest$mstartSelectionAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m6517$$Nest$mstartSelectionAnimation(MonthView r0, int r1, int r2) {
            r0.startSelectionAnimation(r1, r2);
        }

        public MonthView(CalendarActivity r10, Context r11) {
            this.this$0 = r10;
            super(r11);
            this.messagesByDays = new SparseArray();
            this.imagesByDays = new SparseArray();
            this.rowAnimators = new SparseArray();
            this.rowSelectionPos = new SparseArray();
            boolean r0 = false;
            setWillNotDraw(false);
            this.titleView = new SimpleTextView(r11);
            if (CalendarActivity.m6482$$Nest$fgetcalendarType(r10) == 0) goto L5;
        L7:
            this.titleView.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 2));
            this.titleView.setTextSize(15);
            this.titleView.setTypeface(AndroidUtilities.bold());
            this.titleView.setGravity(17);
            this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            addView(this.titleView, LayoutHelper.createFrame(-1, 28.0f, 0, 0.0f, 12.0f, 0.0f, 4.0f));
            GestureDetectorCompat r1 = new GestureDetectorCompat(r11, new AnonymousClass2(this, r10, r11));
            this.gestureDetector = r1;
            if (CalendarActivity.m6482$$Nest$fgetcalendarType(r10) != 0) goto L10;
            r0 = true;
        L10:
            r1.setIsLongpressEnabled(r0);
            return;
        L5:
            if (CalendarActivity.m6483$$Nest$fgetcanClearHistory(r10) == false) goto L7;
            this.titleView.setOnLongClickListener(new CalendarActivity$MonthView$$ExternalSyntheticLambda0(this));
            this.titleView.setOnClickListener(new AnonymousClass1(this, r10));
            goto L7
        }

        private /* synthetic */ void lambda$animateRow$1(RowAnimationValue r1, float r2, float r3, float r4, float r5, float r6, float r7, ValueAnimator r8) {
            float r82 = ((Float) r8.getAnimatedValue()).floatValue();
            r1.startX = r2 + ((r3 - r2) * r82);
            r1.endX = r4 + ((r5 - r4) * r82);
            r1.alpha = r6 + ((r7 - r6) * r82);
            invalidate();
        }

        private /* synthetic */ boolean lambda$new$0(View r7) {
            if (this.messagesByDays != null) goto L5;
            return false;
        L5:
            int r2 = -1;
            int r3 = -1;
            int r1 = 0;
        L7:
            if (r1 >= this.daysInMonth) goto L14;
            PeriodDay r4 = this.messagesByDays.get(r1, null);
            if (r4 == null) goto L13;
            if (r2 != (-1)) goto L12;
            r2 = r4.date;
        L12:
            r3 = r4.date;
        L13:
            r1 = r1 + 1;
            goto L7
        L14:
            if (r2 < 0) goto L17;
            if (r3 < 0) goto L17;
            CalendarActivity.m6503$$Nest$fputinSelectionMode(this.this$0, true);
            CalendarActivity.m6502$$Nest$fputdateSelectedStart(this.this$0, r2);
            CalendarActivity.m6501$$Nest$fputdateSelectedEnd(this.this$0, r3);
            CalendarActivity.m6512$$Nest$mupdateTitle(this.this$0);
            CalendarActivity.m6507$$Nest$manimateSelection(this.this$0);
        L17:
            return false;
        }

        private void setSelectionValue(float r5) {
            if (this.messagesByDays == null) goto L11;
            int r0 = 0;
        L6:
            if (r0 >= this.daysInMonth) goto L11;
            PeriodDay r1 = this.messagesByDays.get(r0, null);
            if (r1 == null) goto L10;
            float r2 = r1.fromSelProgress;
            r1.selectProgress = r2 + ((r1.toSelProgress - r2) * r5);
            float r22 = r1.fromSelSEProgress;
            r1.selectStartEndProgress = r22 + ((r1.toSelSEProgress - r22) * r5);
        L10:
            r0 = r0 + 1;
        L11:
            invalidate();
        }

        private void startSelectionAnimation(int r7, int r8) {
            if (this.messagesByDays == null) goto L21;
            int r0 = 0;
        L6:
            if (r0 >= this.daysInMonth) goto L26;
            PeriodDay r1 = this.messagesByDays.get(r0, null);
            if (r1 == null) goto L20;
            r1.fromSelProgress = r1.selectProgress;
            int r2 = r1.date;
            if (r2 < r7) goto L13;
            if (r2 > r8) goto L13;
            float r5 = 1.0f;
        L14:
            r1.toSelProgress = r5;
            r1.fromSelSEProgress = r1.selectStartEndProgress;
            if (r2 == r7) goto L19;
            if (r2 == r8) goto L19;
            r1.toSelSEProgress = 0.0f;
        L19:
            r1.toSelSEProgress = 1.0f;
        L13:
            r5 = 0.0f;
        L20:
            r0 = r0 + 1;
            goto L6
        L26:
            return;
        }

        public void animateRow(int r13, int r14, int r15, boolean r16, boolean r17) {
            ValueAnimator r2 = this.rowAnimators.get(r13);
            if (r2 == null) goto L5;
            r2.cancel();
        L5:
            float r22 = getMeasuredWidth() / 7.0f;
            RowAnimationValue r3 = this.rowSelectionPos.get(r13);
            float r4 = 0.0f;
            if (r3 == null) goto L8;
            float r6 = r3.startX;
            float r7 = r3.endX;
            float r72 = r3.alpha;
            float r32 = r6;
            float r62 = 2.0f;
            float r5 = r7;
        L9:
            if (r16 == false) goto L11;
            float r0 = (r14 * r22) + (r22 / r62);
        L12:
            if (r16 == false) goto L14;
            float r63 = (r15 * r22) + (r22 / r62);
        L15:
            if (r16 == false) goto L17;
            r4 = 1.0f;
        L17:
            float r8 = r4;
            RowAnimationValue r23 = new RowAnimationValue(r32, r5);
            this.rowSelectionPos.put(r13, r23);
            if (r17 == false) goto L21;
            ValueAnimator r9 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(300);
            r9.setInterpolator(Easings.easeInOutQuad);
            float r42 = r0;
            r9.addUpdateListener(new CalendarActivity$MonthView$$ExternalSyntheticLambda1(this, r23, r32, r42, r5, r63, r72, r8));
            r9.addListener(new AnonymousClass3(this, r23, r42, r63, r8, r13, r16));
            r9.start();
            this.rowAnimators.put(r13, r9);
            return;
        L21:
            r23.startX = r0;
            r23.endX = r63;
            r23.alpha = r8;
            invalidate();
            return;
        L14:
            r63 = r5;
            goto L15
        L11:
            r0 = r32;
            goto L12
        L8:
            r72 = 0.0f;
            r32 = (r22 / 2.0f) + (r14 * r22);
            r62 = 2.0f;
            r5 = r32;
            goto L9
        }

        public void dismissRowAnimations(boolean r9) {
            int r0 = 0;
        L4:
            if (r0 >= this.rowSelectionPos.size()) goto L6;
            animateRow(this.rowSelectionPos.keyAt(r0), 0, 0, false, r9);
            r0 = r0 + 1;
            goto L4
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.attached = true;
            if (this.imagesByDays == null) goto L8;
            int r0 = 0;
        L6:
            if (r0 >= this.imagesByDays.size()) goto L10;
            this.imagesByDays.valueAt(r0).onAttachedToWindow();
            r0 = r0 + 1;
            goto L6
        L10:
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            int r0 = 0;
            this.attached = false;
            if (this.imagesByDays != null) goto L5;
            return;
        L5:
            if (r0 >= this.imagesByDays.size()) goto L9;
            this.imagesByDays.valueAt(r0).onDetachedFromWindow();
            r0 = r0 + 1;
            goto L5
        }

        @Override // android.view.View
        public void onDraw(Canvas r27) {
            Canvas r1 = r27;
            super.onDraw(r27);
            int r2 = this.startDayOfWeek;
            float r7 = 7.0f;
            float r8 = getMeasuredWidth() / 7.0f;
            float r9 = AndroidUtilities.dp(52.0f);
            float r10 = 44.0f;
            int r3 = AndroidUtilities.dp(44.0f);
            int r4 = 0;
        L3:
            float r12 = 2.0f;
            if (r4 >= Math.ceil((this.startDayOfWeek + this.daysInMonth) / 7.0f)) goto L9;
            float r5 = ((r4 * r9) + (r9 / 2.0f)) + AndroidUtilities.dp(44.0f);
            RowAnimationValue r6 = this.rowSelectionPos.get(r4);
            if (r6 == null) goto L8;
            CalendarActivity.m6493$$Nest$fgetselectPaint(this.this$0).setColor(Theme.getColor(Theme.key_chat_messagePanelVoiceBackground));
            CalendarActivity.m6493$$Nest$fgetselectPaint(this.this$0).setAlpha((int) (r6.alpha * 40.8f));
            RectF r13 = AndroidUtilities.rectTmp;
            float r15 = r3 / 2.0f;
            r13.set(r6.startX - r15, r5 - r15, r6.endX + r15, r5 + r15);
            float r52 = AndroidUtilities.dp(32.0f);
            r1.drawRoundRect(r13, r52, r52, CalendarActivity.m6493$$Nest$fgetselectPaint(this.this$0));
        L8:
            r4 = r4 + 1;
            goto L3
        L9:
            int r132 = r2;
            int r14 = 0;
            int r152 = 0;
        L11:
            if (r14 >= this.daysInMonth) goto L84;
            float r22 = (r132 * r8) + (r8 / r12);
            float r32 = ((r152 * r9) + (r9 / r12)) + AndroidUtilities.dp(r10);
            int r42 = (int) (System.currentTimeMillis() / 1000);
            SparseArray<PeriodDay> r53 = this.messagesByDays;
            PeriodDay r62 = null;
            if (r53 == null) goto L15;
            r62 = r53.get(r14, null);
        L15:
            PeriodDay r54 = r62;
            int r16 = r14 + 1;
            if (r42 >= (this.startMonthTime + (r16 * 86400))) goto L18;
        L21:
            float r19 = r7;
            float r222 = r10;
            float r25 = r12;
            int r23 = this.this$0.textPaint.getAlpha();
            this.this$0.textPaint.setAlpha((int) (r23 * 0.3f));
            r1.drawText(Integer.toString(r16), r22, AndroidUtilities.dp(5.0f) + r32, this.this$0.textPaint);
            this.this$0.textPaint.setAlpha(r23);
        L80:
            r132 = r132 + 1;
            if (r132 < 7) goto L83;
            r152 = r152 + 1;
            r132 = 0;
        L83:
            r14 = r16;
            r7 = r19;
            r10 = r222;
            r12 = r25;
            goto L11
        L18:
            if (CalendarActivity.m6490$$Nest$fgetminDate(this.this$0) > 0) goto L20;
        L22:
            r19 = r7;
            if (r54 != null) goto L25;
        L70:
            r222 = r10;
            r25 = r12;
            if (r54 != null) goto L73;
        L78:
            r1.drawText(Integer.toString(r16), r22, AndroidUtilities.dp(5.0f) + r32, this.this$0.textPaint);
            goto L80
        L73:
            if (r54.selectStartEndProgress < 0.01f) goto L78;
            CalendarActivity.m6493$$Nest$fgetselectPaint(this.this$0).setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            CalendarActivity.m6493$$Nest$fgetselectPaint(this.this$0).setAlpha((int) (r54.selectStartEndProgress * 255.0f));
            r1.drawCircle(r22, r32, AndroidUtilities.dp(r222) / r25, CalendarActivity.m6493$$Nest$fgetselectPaint(this.this$0));
            Paint r24 = CalendarActivity.m6492$$Nest$fgetselectOutlinePaint(this.this$0);
            int r102 = Theme.key_chat_messagePanelVoiceBackground;
            r24.setColor(Theme.getColor(r102));
            RectF r26 = AndroidUtilities.rectTmp;
            r26.set(r22 - (AndroidUtilities.dp(r222) / r25), r32 - (AndroidUtilities.dp(r222) / r25), (AndroidUtilities.dp(r222) / r25) + r22, (AndroidUtilities.dp(r222) / r25) + r32);
            r1.drawArc(r26, -90.0f, r54.selectStartEndProgress * 360.0f, false, CalendarActivity.m6492$$Nest$fgetselectOutlinePaint(this.this$0));
            int r28 = (int) (AndroidUtilities.dp(r19) * r54.selectStartEndProgress);
            CalendarActivity.m6493$$Nest$fgetselectPaint(this.this$0).setColor(Theme.getColor(r102));
            CalendarActivity.m6493$$Nest$fgetselectPaint(this.this$0).setAlpha((int) (r54.selectStartEndProgress * 255.0f));
            r1.drawCircle(r22, r32, (AndroidUtilities.dp(r222) - r28) / r25, CalendarActivity.m6493$$Nest$fgetselectPaint(this.this$0));
            float r29 = r54.selectStartEndProgress;
            if (r29 == 1.0f) goto L77;
            int r33 = this.this$0.textPaint.getAlpha();
            this.this$0.textPaint.setAlpha((int) (r33 * (1.0f - r29)));
            r1.drawText(Integer.toString(r16), r22, AndroidUtilities.dp(5.0f) + r32, this.this$0.textPaint);
            this.this$0.textPaint.setAlpha(r33);
            int r34 = this.this$0.textPaint.getAlpha();
            this.this$0.activeTextPaint.setAlpha((int) (r34 * r29));
            r1.drawText(Integer.toString(r16), r22, AndroidUtilities.dp(5.0f) + r32, this.this$0.activeTextPaint);
            this.this$0.activeTextPaint.setAlpha(r34);
            goto L80
        L77:
            r1.drawText(Integer.toString(r16), r22, AndroidUtilities.dp(5.0f) + r32, this.this$0.activeTextPaint);
            goto L80
        L25:
            if (r54.hasImage == false) goto L70;
            if (this.imagesByDays.get(r14) != null) goto L29;
            float r11 = r32;
            float r242 = 1.0f;
            r222 = r10;
            r25 = r12;
            float r72 = r22;
            float r103 = 1.0f;
        L67:
            if (r103 == r242) goto L69;
            int r210 = this.this$0.textPaint.getAlpha();
            this.this$0.textPaint.setAlpha((int) (r210 * (r242 - r103)));
            r1.drawText(Integer.toString(r16), r72, AndroidUtilities.dp(5.0f) + r11, this.this$0.textPaint);
            this.this$0.textPaint.setAlpha(r210);
            int r211 = this.this$0.textPaint.getAlpha();
            this.this$0.activeTextPaint.setAlpha((int) (r211 * r103));
            r1.drawText(Integer.toString(r16), r72, AndroidUtilities.dp(5.0f) + r11, this.this$0.activeTextPaint);
            this.this$0.activeTextPaint.setAlpha(r211);
            goto L80
        L69:
            r1.drawText(Integer.toString(r16), r72, AndroidUtilities.dp(5.0f) + r11, this.this$0.activeTextPaint);
            goto L80
        L29:
            if (CalendarActivity.m6484$$Nest$fgetcheckEnterItems(this.this$0) == true) goto L31;
        L33:
            r222 = r10;
        L34:
            float r43 = r54.startEnterDelay;
            if (r43 <= 0.0f) goto L41;
            float r44 = r43 - 16.0f;
            r54.startEnterDelay = r44;
            if (r44 >= 0.0f) goto L39;
            r54.startEnterDelay = 0.0f;
            goto L41
        L39:
            invalidate();
        L41:
            if (r54.startEnterDelay < 0.0f) goto L48;
            float r45 = r54.enterAlpha;
            if (r45 == 1.0f) goto L48;
            float r46 = r45 + 0.07272727f;
            r54.enterAlpha = r46;
            if (r46 <= 1.0f) goto L47;
            r54.enterAlpha = 1.0f;
            goto L48
        L47:
            invalidate();
        L48:
            r103 = r54.enterAlpha;
            if (r103 == 1.0f) goto L51;
            r1.save();
            float r47 = (0.2f * r103) + 0.8f;
            r1.scale(r47, r47, r22, r32);
        L51:
            int r48 = (int) (AndroidUtilities.dp(r19) * r54.selectProgress);
            if (r54.selectStartEndProgress < 0.01f) goto L54;
            r242 = 1.0f;
            CalendarActivity.m6493$$Nest$fgetselectPaint(this.this$0).setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            CalendarActivity.m6493$$Nest$fgetselectPaint(this.this$0).setAlpha((int) (r54.selectStartEndProgress * 255.0f));
            r1.drawCircle(r22, r32, AndroidUtilities.dp(r222) / r12, CalendarActivity.m6493$$Nest$fgetselectPaint(this.this$0));
            CalendarActivity.m6492$$Nest$fgetselectOutlinePaint(this.this$0).setColor(Theme.getColor(Theme.key_chat_messagePanelVoiceBackground));
            RectF r212 = AndroidUtilities.rectTmp;
            r25 = r12;
            r212.set(r22 - (AndroidUtilities.dp(r222) / r12), r32 - (AndroidUtilities.dp(r222) / r12), (AndroidUtilities.dp(r222) / r25) + r22, (AndroidUtilities.dp(r222) / r25) + r32);
            r11 = r32;
            PeriodDay r122 = r54;
            r72 = r22;
            int r17 = r48;
            r1 = r27;
            r1.drawArc(r212, -90.0f, r54.selectStartEndProgress * 360.0f, false, CalendarActivity.m6492$$Nest$fgetselectOutlinePaint(this.this$0));
        L55:
            this.imagesByDays.get(r14).setAlpha(r122.enterAlpha);
            this.imagesByDays.get(r14).setImageCoords(r72 - ((AndroidUtilities.dp(r222) - r17) / r25), r11 - ((AndroidUtilities.dp(r222) - r17) / r25), AndroidUtilities.dp(r222) - r17, AndroidUtilities.dp(r222) - r17);
            this.imagesByDays.get(r14).draw(r1);
            if (this.messagesByDays.get(r14) != null) goto L58;
        L62:
            this.this$0.blackoutPaint.setColor(ColorUtils.setAlphaComponent(-16777216, (int) (r122.enterAlpha * 80.0f)));
            r1.drawCircle(r72, r11, (AndroidUtilities.dp(r222) - r17) / r25, this.this$0.blackoutPaint);
            r122.wasDrawn = true;
            if (r103 == 1.0f) goto L67;
            r1.restore();
            goto L67
        L58:
            if (this.messagesByDays.get(r14).messageObject == null) goto L62;
            if (this.messagesByDays.get(r14).messageObject.hasMediaSpoilers() == false) goto L62;
            float r213 = (AndroidUtilities.dp(r222) - r17) / r25;
            CalendarActivity.m6491$$Nest$fgetpath(this.this$0).rewind();
            CalendarActivity.m6491$$Nest$fgetpath(this.this$0).addCircle(r72, r11, r213, Path.Direction.CW);
            r1.save();
            r1.clipPath(CalendarActivity.m6491$$Nest$fgetpath(this.this$0));
            CalendarActivity.m6489$$Nest$fgetmediaSpoilerEffect(this.this$0).setColor(ColorUtils.setAlphaComponent(-1, (int) ((Color.alpha(-1) * 0.325f) * r122.enterAlpha)));
            CalendarActivity.m6489$$Nest$fgetmediaSpoilerEffect(this.this$0).setBounds((int) (r72 - r213), (int) (r11 - r213), (int) (r72 + r213), (int) (r213 + r11));
            CalendarActivity.m6489$$Nest$fgetmediaSpoilerEffect(this.this$0).draw(r1);
            invalidate();
            r1.restore();
            goto L62
        L54:
            r11 = r32;
            r17 = r48;
            r242 = 1.0f;
            r25 = r12;
            r72 = r22;
            r122 = r54;
            goto L55
        L31:
            if (r54.wasDrawn == true) goto L33;
            r54.enterAlpha = 0.0f;
            r222 = r10;
            r54.startEnterDelay = Math.max(0.0f, ((getY() + r32) / this.this$0.listView.getMeasuredHeight()) * 150.0f);
            goto L34
        L20:
            if (CalendarActivity.m6490$$Nest$fgetminDate(this.this$0) <= (this.startMonthTime + ((r14 + 2) * 86400))) goto L22;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp((this.cellCount * 52) + 44), TLObject.FLAG_30));
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r1) {
            return this.gestureDetector.onTouchEvent(r1);
        }

        public void setDate(int r24, int r25, SparseArray<PeriodDay> r26, boolean r27) {
            boolean r6 = false;
            if (r24 == this.currentYear) goto L5;
        L8:
            boolean r4 = true;
        L9:
            this.currentYear = r24;
            this.currentMonthInYear = r25;
            this.messagesByDays = r26;
            ImageReceiver r7 = null;
            if (r4 == true) goto L12;
        L18:
            if (r26 != null) goto L20;
        L94:
            int r3 = r25 + 1;
            this.daysInMonth = YearMonth.of(r24, r3).lengthOfMonth();
            Calendar r42 = Calendar.getInstance();
            r42.set(r24, r25, 0);
            this.startDayOfWeek = (r42.get(7) + 6) % 7;
            this.startMonthTime = (int) (r42.getTimeInMillis() / 1000);
            int r5 = this.daysInMonth + this.startDayOfWeek;
            int r62 = (int) (r5 / 7.0f);
            if ((r5 % 7) != 0) goto L97;
            int r52 = 0;
        L98:
            this.cellCount = r62 + r52;
            r42.set(r24, r3, 0);
            this.titleView.setText(LocaleController.formatYearMont(r42.getTimeInMillis() / 1000, true));
            CalendarActivity.m6511$$Nest$mupdateRowSelections(this.this$0, this, false);
            return;
        L97:
            r52 = 1;
            goto L98
        L20:
            if (this.imagesByDays != null) goto L22;
            this.imagesByDays = new SparseArray();
        L22:
            int r43 = 0;
        L24:
            if (r43 >= r26.size()) goto L94;
            int r8 = r26.keyAt(r43);
            if (this.imagesByDays.get(r8, r7) != null) goto L93;
            if (r26.get(r8).hasImage == false) goto L93;
            ImageReceiver r10 = new ImageReceiver();
            r10.setParentView(this);
            MessageObject r15 = r26.get(r8).messageObject;
            if (r15 == null) goto L93;
            boolean r9 = r15.hasMediaSpoilers();
            String r14 = "44_44";
            if (r15.isVideo() == false) goto L49;
            TLRPC.Document r11 = r15.getDocument();
            TLRPC.PhotoSize r72 = FileLoader.getClosestPhotoSizeWithSize(r11.thumbs, 50);
            TLRPC.PhotoSize r12 = FileLoader.getClosestPhotoSizeWithSize(r11.thumbs, 320);
            if (r72 != r12) goto L37;
            r12 = null;
        L37:
            if (r72 != null) goto L39;
        L92:
            r10.setRoundRadius(AndroidUtilities.dp(22.0f));
            this.imagesByDays.put(r8, r10);
            goto L93
        L39:
            if (r15.strippedThumb == null) goto L45;
            ImageLocation r112 = ImageLocation.getForDocument(r12, r11);
            if (r9 == false) goto L43;
            String r122 = "5_5_b";
        L44:
            r10.setImage(r112, r122, r15.strippedThumb, null, r15, 0);
            goto L92
        L43:
            r122 = "44_44";
            goto L44
        L45:
            ImageLocation r123 = ImageLocation.getForDocument(r12, r11);
            if (r9 == false) goto L48;
            r14 = "5_5_b";
        L48:
            r10.setImage(r123, r14, ImageLocation.getForDocument(r72, r11), "b", null, r15, 0);
            goto L92
        L49:
            TLRPC.MessageMedia r73 = r15.messageOwner.media;
            if ((r73 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L92;
            if (r73.photo == null) goto L92;
            if (r15.photoThumbs.isEmpty() == true) goto L92;
            TLRPC.PhotoSize r74 = FileLoader.getClosestPhotoSizeWithSize(r15.photoThumbs, 50);
            TLRPC.PhotoSize r113 = FileLoader.getClosestPhotoSizeWithSize(r15.photoThumbs, 320, r6, r74, r6);
            if (r15.mediaExists == false) goto L58;
        L64:
            if (r113 != r74) goto L66;
            r74 = null;
        L66:
            BitmapDrawable r124 = r15.strippedThumb;
            TLObject r13 = r15.photoThumbsObject;
            int r17 = 2;
            long r18 = 0;
            if (r124 == null) goto L81;
            ImageLocation r75 = ImageLocation.getForObject(r113, r13);
            if (r9 == false) goto L72;
            String r125 = "5_5_b";
        L73:
            BitmapDrawable r152 = r15.strippedThumb;
            if (r113 == null) goto L77;
            r18 = r113.size;
        L77:
            if (r15.shouldEncryptPhotoOrVideo() == false) goto L79;
            int r20 = 2;
        L80:
            r10.setImage(r75, r125, null, null, r152, r18, null, r15, r20);
            goto L92
        L79:
            r20 = 1;
            goto L80
        L72:
            r125 = "44_44";
            goto L73
        L81:
            ImageLocation r126 = ImageLocation.getForObject(r113, r13);
            if (r9 == false) goto L84;
            r14 = "5_5_b";
        L84:
            ImageLocation r132 = ImageLocation.getForObject(r74, r15.photoThumbsObject);
            if (r113 == null) goto L88;
            r18 = r113.size;
        L88:
            if (r15.shouldEncryptPhotoOrVideo() == true) goto L91;
            r17 = 1;
        L91:
            r10.setImage(r126, r14, r132, "b", r18, null, r15, r17);
            goto L92
        L58:
            if (DownloadController.getInstance(CalendarActivity.access$1300(this.this$0)).canDownloadMedia(r15) == true) goto L64;
            BitmapDrawable r133 = r15.strippedThumb;
            if (r133 == null) goto L63;
            r10.setImage(null, null, r133, null, r15, 0);
            goto L92
        L63:
            r10.setImage(null, null, ImageLocation.getForObject(r74, r15.photoThumbsObject), "b", null, r15, 0);
        L93:
            r43 = r43 + 1;
            r6 = false;
            r7 = null;
            goto L24
        L12:
            if (this.imagesByDays == null) goto L18;
            int r44 = 0;
        L15:
            if (r44 >= this.imagesByDays.size()) goto L17;
            this.imagesByDays.valueAt(r44).onDetachedFromWindow();
            this.imagesByDays.valueAt(r44).setParentView(null);
            r44 = r44 + 1;
            goto L15
        L17:
            this.imagesByDays = null;
            goto L18
        L5:
            if (r25 != this.currentMonthInYear) goto L8;
            r4 = false;
            goto L9
        }
    }

    public class PeriodDay {
        int date;
        float enterAlpha;
        float fromSelProgress;
        float fromSelSEProgress;
        boolean hasImage;
        MessageObject messageObject;
        float selectProgress;
        float selectStartEndProgress;
        float startEnterDelay;
        int startOffset;
        ArrayList<Integer> storyItems;
        final /* synthetic */ CalendarActivity this$0;
        float toSelProgress;
        float toSelSEProgress;
        boolean wasDrawn;

        private PeriodDay(CalendarActivity r1) {
            this.this$0 = r1;
            this.enterAlpha = 1.0f;
            this.startEnterDelay = 1.0f;
            this.hasImage = true;
        }

        public /* synthetic */ PeriodDay(CalendarActivity r1, CalendarActivityIA r2) {
            this(r1);
        }
    }

    public static final class RowAnimationValue {
        float alpha;
        float endX;
        float startX;

        public RowAnimationValue(float r1, float r2) {
            this.startX = r1;
            this.endX = r2;
        }
    }

    public static /* synthetic */ void $r8$lambda$1AhzAvAu3VcB7mKSCYfd9ycFI6w(CalendarActivity r0, ValueAnimator r1) {
        r0.lambda$animateSelection$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$D0AtbVAa5NUyIh21ZRenFyLU6AA(CalendarActivity r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    /* renamed from: $r8$lambda$Sai1T45kBafASdzcwMW4-sQOx_4, reason: not valid java name */
    public static /* synthetic */ void m6480$r8$lambda$Sai1T45kBafASdzcwMW4sQOx_4(CalendarActivity r0, Calendar r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadNext$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$fcHWTUqJOobix7aKf7HhuPawPms(CalendarActivity r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$vSwRhB57UkkYEWK4_zZwp8iJz1g(CalendarActivity r0, TLRPC.TL_error r1, TLObject r2, Calendar r3) {
        r0.lambda$loadNext$2(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetblurredView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m6481$$Nest$fgetblurredView(CalendarActivity r0) {
        return r0.blurredView;
    }

    /* renamed from: -$$Nest$fgetcalendarType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6482$$Nest$fgetcalendarType(CalendarActivity r0) {
        return r0.calendarType;
    }

    /* renamed from: -$$Nest$fgetcanClearHistory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6483$$Nest$fgetcanClearHistory(CalendarActivity r0) {
        return r0.canClearHistory;
    }

    /* renamed from: -$$Nest$fgetcheckEnterItems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6484$$Nest$fgetcheckEnterItems(CalendarActivity r0) {
        return r0.checkEnterItems;
    }

    /* renamed from: -$$Nest$fgetdateSelectedEnd, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6485$$Nest$fgetdateSelectedEnd(CalendarActivity r0) {
        return r0.dateSelectedEnd;
    }

    /* renamed from: -$$Nest$fgetdateSelectedStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6486$$Nest$fgetdateSelectedStart(CalendarActivity r0) {
        return r0.dateSelectedStart;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6487$$Nest$fgetdialogId(CalendarActivity r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetinSelectionMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6488$$Nest$fgetinSelectionMode(CalendarActivity r0) {
        return r0.inSelectionMode;
    }

    /* renamed from: -$$Nest$fgetmediaSpoilerEffect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpoilerEffect m6489$$Nest$fgetmediaSpoilerEffect(CalendarActivity r0) {
        return r0.mediaSpoilerEffect;
    }

    /* renamed from: -$$Nest$fgetminDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6490$$Nest$fgetminDate(CalendarActivity r0) {
        return r0.minDate;
    }

    /* renamed from: -$$Nest$fgetpath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m6491$$Nest$fgetpath(CalendarActivity r0) {
        return r0.path;
    }

    /* renamed from: -$$Nest$fgetselectOutlinePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m6492$$Nest$fgetselectOutlinePaint(CalendarActivity r0) {
        return r0.selectOutlinePaint;
    }

    /* renamed from: -$$Nest$fgetselectPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m6493$$Nest$fgetselectPaint(CalendarActivity r0) {
        return r0.selectPaint;
    }

    /* renamed from: -$$Nest$fgetselectionAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m6494$$Nest$fgetselectionAnimator(CalendarActivity r0) {
        return r0.selectionAnimator;
    }

    /* renamed from: -$$Nest$fgetstoriesList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesController.StoriesList m6495$$Nest$fgetstoriesList(CalendarActivity r0) {
        return r0.storiesList;
    }

    /* renamed from: -$$Nest$fgetstoriesPlaceDay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6496$$Nest$fgetstoriesPlaceDay(CalendarActivity r0) {
        return r0.storiesPlaceDay;
    }

    /* renamed from: -$$Nest$fgetstoriesPlaceDrawAbove, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryViewer.HolderDrawAbove m6497$$Nest$fgetstoriesPlaceDrawAbove(CalendarActivity r0) {
        return r0.storiesPlaceDrawAbove;
    }

    /* renamed from: -$$Nest$fgetstoriesPlaceProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryViewer.PlaceProvider m6498$$Nest$fgetstoriesPlaceProvider(CalendarActivity r0) {
        return r0.storiesPlaceProvider;
    }

    /* renamed from: -$$Nest$fputblurredView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6499$$Nest$fputblurredView(CalendarActivity r0, View r1) {
        r0.blurredView = r1;
    }

    /* renamed from: -$$Nest$fputcheckEnterItems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6500$$Nest$fputcheckEnterItems(CalendarActivity r0, boolean r1) {
        r0.checkEnterItems = r1;
    }

    /* renamed from: -$$Nest$fputdateSelectedEnd, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6501$$Nest$fputdateSelectedEnd(CalendarActivity r0, int r1) {
        r0.dateSelectedEnd = r1;
    }

    /* renamed from: -$$Nest$fputdateSelectedStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6502$$Nest$fputdateSelectedStart(CalendarActivity r0, int r1) {
        r0.dateSelectedStart = r1;
    }

    /* renamed from: -$$Nest$fputinSelectionMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6503$$Nest$fputinSelectionMode(CalendarActivity r0, boolean r1) {
        r0.inSelectionMode = r1;
    }

    /* renamed from: -$$Nest$fputselectionAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6504$$Nest$fputselectionAnimator(CalendarActivity r0, ValueAnimator r1) {
        r0.selectionAnimator = r1;
    }

    /* renamed from: -$$Nest$fputstoriesPlaceDay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6505$$Nest$fputstoriesPlaceDay(CalendarActivity r0, int r1) {
        r0.storiesPlaceDay = r1;
    }

    /* renamed from: -$$Nest$fputstoriesPlaceDrawAbove, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6506$$Nest$fputstoriesPlaceDrawAbove(CalendarActivity r0, StoryViewer.HolderDrawAbove r1) {
        r0.storiesPlaceDrawAbove = r1;
    }

    /* renamed from: -$$Nest$manimateSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6507$$Nest$manimateSelection(CalendarActivity r0) {
        r0.animateSelection();
    }

    /* renamed from: -$$Nest$mcheckLoadNext, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6508$$Nest$mcheckLoadNext(CalendarActivity r0) {
        r0.checkLoadNext();
    }

    /* renamed from: -$$Nest$mprepareBlurBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6509$$Nest$mprepareBlurBitmap(CalendarActivity r0) {
        r0.prepareBlurBitmap();
    }

    /* renamed from: -$$Nest$mupdateColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6510$$Nest$mupdateColors(CalendarActivity r0) {
        r0.updateColors();
    }

    /* renamed from: -$$Nest$mupdateRowSelections, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6511$$Nest$mupdateRowSelections(CalendarActivity r0, MonthView r1, boolean r2) {
        r0.updateRowSelections(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6512$$Nest$mupdateTitle(CalendarActivity r0) {
        r0.updateTitle();
    }

    public CalendarActivity(Bundle r4, int r5, int r6) {
        super(r4);
        this.textPaint = new TextPaint(1);
        this.activeTextPaint = new TextPaint(1);
        this.textPaint2 = new TextPaint(1);
        this.selectOutlinePaint = new Paint(1);
        this.selectPaint = new Paint(1);
        this.blackoutPaint = new Paint(1);
        this.messagesByYearMounth = new SparseArray();
        this.startOffset = 0;
        this.path = new Path();
        this.mediaSpoilerEffect = new SpoilerEffect();
        this.photosVideosTypeFilter = r5;
        if (r6 == 0) goto L5;
        Calendar r42 = Calendar.getInstance();
        r42.setTimeInMillis(r6 * 1000);
        this.selectedYear = r42.get(1);
        this.selectedMonth = r42.get(2);
    L5:
        this.selectOutlinePaint.setStyle(Paint.Style.STROKE);
        this.selectOutlinePaint.setStrokeCap(Paint.Cap.ROUND);
        this.selectOutlinePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
    }

    public static /* synthetic */ INavigationLayout access$000(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$100(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$1000(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$1100(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$1200(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ int access$1300(CalendarActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ INavigationLayout access$1400(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$1500(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$1600(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$1700(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$200(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$300(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$400(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$500(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$600(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$700(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$800(CalendarActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$900(CalendarActivity r0) {
        return r0.parentLayout;
    }

    private void animateSelection() {
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(300);
        r0.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r0.addUpdateListener(new CalendarActivity$$ExternalSyntheticLambda2(this));
        r0.addListener(new AnonymousClass10(this));
        r0.start();
        this.selectionAnimator = r0;
        int r1 = 0;
    L4:
        if (r1 >= this.listView.getChildCount()) goto L6;
        updateRowSelections((MonthView) this.listView.getChildAt(r1), true);
        r1 = r1 + 1;
        goto L4
    L6:
        int r12 = 0;
    L8:
        if (r12 >= this.listView.getCachedChildCount()) goto L10;
        MonthView r2 = (MonthView) this.listView.getCachedChildAt(r12);
        updateRowSelections(r2, false);
        MonthView.m6517$$Nest$mstartSelectionAnimation(r2, this.dateSelectedStart, this.dateSelectedEnd);
        MonthView.m6516$$Nest$msetSelectionValue(r2, 1.0f);
        r12 = r12 + 1;
        goto L8
    L10:
        int r13 = 0;
    L12:
        if (r13 >= this.listView.getHiddenChildCount()) goto L14;
        MonthView r22 = (MonthView) this.listView.getHiddenChildAt(r13);
        updateRowSelections(r22, false);
        MonthView.m6517$$Nest$mstartSelectionAnimation(r22, this.dateSelectedStart, this.dateSelectedEnd);
        MonthView.m6516$$Nest$msetSelectionValue(r22, 1.0f);
        r13 = r13 + 1;
        goto L12
    L14:
        int r14 = 0;
    L16:
        if (r14 >= this.listView.getAttachedScrapChildCount()) goto L18;
        MonthView r23 = (MonthView) this.listView.getAttachedScrapChildAt(r14);
        updateRowSelections(r23, false);
        MonthView.m6517$$Nest$mstartSelectionAnimation(r23, this.dateSelectedStart, this.dateSelectedEnd);
        MonthView.m6516$$Nest$msetSelectionValue(r23, 1.0f);
        r14 = r14 + 1;
        goto L16
    }

    private void checkLoadNext() {
        if (this.loading == false) goto L5;
        return;
    L5:
        if (this.endReached == true) goto L26;
        int r0 = Integer.MAX_VALUE;
        int r1 = 0;
    L9:
        if (r1 >= this.listView.getChildCount()) goto L16;
        View r2 = this.listView.getChildAt(r1);
        if ((r2 instanceof MonthView) == false) goto L15;
        MonthView r22 = (MonthView) r2;
        int r3 = (r22.currentYear * 100) + r22.currentMonthInYear;
        if (r3 >= r0) goto L15;
        r0 = r3;
    L15:
        r1 = r1 + 1;
        goto L9
    L16:
        int r12 = this.minMontYear;
        int r23 = ((r12 / 100) * 12) + (r12 % 100);
        if ((r23 + 3) < (((r0 / 100) * 12) + (r0 % 100))) goto L25;
        loadNext();
        return;
    L25:
        return;
    }

    private /* synthetic */ void lambda$animateSelection$4(ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        int r0 = 0;
    L4:
        if (r0 >= this.listView.getChildCount()) goto L6;
        MonthView.m6516$$Nest$msetSelectionValue((MonthView) this.listView.getChildAt(r0), r32);
        r0 = r0 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$createView$0(View r1) {
        this.inSelectionMode = true;
        updateTitle();
    }

    private /* synthetic */ void lambda$createView$1(View r9) {
        int r1 = this.lastDaysSelected;
        if (r1 == 0) goto L5;
        AlertsCreator.createClearDaysDialogAlert(this, r1, getMessagesController().getUser(Long.valueOf(this.dialogId)), null, false, new AnonymousClass8(this), null);
        return;
    L5:
        if (this.selectDaysHint != null) goto L7;
        HintView r92 = new HintView(this.contentView.getContext(), 8);
        this.selectDaysHint = r92;
        r92.setExtraTranslationY(AndroidUtilities.dp(24.0f));
        this.contentView.addView(this.selectDaysHint, LayoutHelper.createFrame(-2, -2.0f, 51, 19.0f, 0.0f, 19.0f, 0.0f));
        this.selectDaysHint.setText(LocaleController.getString(R.string.SelectDaysTooltip));
    L7:
        this.selectDaysHint.showForView(this.bottomBar, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v4 */
    private /* synthetic */ void lambda$loadNext$2(TLRPC.TL_error r18, TLObject r19, Calendar r20) {
        if (r18 != null) goto L45;
        TLRPC.TL_messages_searchResultsCalendar r2 = (TLRPC.TL_messages_searchResultsCalendar) r19;
        ?? r3 = 0;
        int r4 = 0;
    L6:
        if (r4 >= r2.periods.size()) goto L20;
        r20.setTimeInMillis(r2.periods.get(r4).date * 1000);
        int r5 = (r20.get(1) * 100) + r20.get(2);
        SparseArray<PeriodDay> r7 = this.messagesByYearMounth.get(r5);
        if (r7 != null) goto L10;
        r7 = new SparseArray();
        this.messagesByYearMounth.put(r5, r7);
    L10:
        PeriodDay r12 = new PeriodDay(this, null);
        r12.messageObject = new MessageObject(this.currentAccount, r2.messages.get(r4), false, false);
        r12.date = (int) (r20.getTimeInMillis() / 1000);
        int r9 = this.startOffset + r2.periods.get(r4).count;
        this.startOffset = r9;
        r12.startOffset = r9;
        int r6 = r20.get(5) - 1;
        if (r7.get(r6, null) != null) goto L13;
    L14:
        r7.put(r6, r12);
    L15:
        int r62 = this.minMontYear;
        if (r5 < r62) goto L18;
        if (r62 == 0) goto L18;
    L19:
        r4 = r4 + 1;
    L18:
        this.minMontYear = r5;
        goto L19
    L13:
        if (r7.get(r6, null).hasImage == true) goto L15;
    L20:
        int r42 = (int) (System.currentTimeMillis() / 1000);
        int r52 = r2.min_date;
        this.minDate = r52;
    L21:
        r20.setTimeInMillis(r52 * 1000);
        r20.set(11, r3);
        r20.set(12, r3);
        r20.set(13, r3);
        r20.set(14, r3);
        if ((r20.getTimeInMillis() / 1000) > r42) goto L23;
        int r122 = (r20.get(1) * 100) + r20.get(2);
        SparseArray<PeriodDay> r13 = this.messagesByYearMounth.get(r122);
        if (r13 != null) goto L40;
        r13 = new SparseArray();
        this.messagesByYearMounth.put(r122, r13);
    L40:
        int r123 = r20.get(5) - 1;
        if (r13.get(r123, null) != null) goto L43;
        PeriodDay r14 = new PeriodDay(this, null);
        r14.hasImage = r3;
        int r182 = r42;
        r14.date = (int) (r20.getTimeInMillis() / 1000);
        r13.put(r123, r14);
    L44:
        r52 = r52 + 86400;
        r42 = r182;
        r3 = 0;
        goto L21
    L43:
        r182 = r42;
        goto L44
    L23:
        this.loading = r3;
        if (r2.messages.isEmpty() == true) goto L26;
        ArrayList<TLRPC.Message> r43 = r2.messages;
        this.lastId = r43.get(r43.size() - 1).id;
        this.endReached = r3;
        checkLoadNext();
    L28:
        if (this.isOpened == false) goto L30;
        this.checkEnterItems = true;
    L30:
        this.listView.invalidate();
        int r1 = ((int) (((r20.getTimeInMillis() / 1000) - r2.min_date) / 2629800)) + 1;
        this.adapter.notifyItemRangeChanged(r3, this.monthCount);
        int r22 = this.monthCount;
        if (r1 <= r22) goto L34;
        this.adapter.notifyItemRangeInserted(r22 + 1, r1);
        this.monthCount = r1;
    L34:
        if (this.endReached == false) goto L52;
        resumeDelayedFragmentAnimation();
        return;
    L52:
        return;
    L26:
        this.endReached = true;
        goto L28
    }

    private /* synthetic */ void lambda$loadNext$3(Calendar r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new CalendarActivity$$ExternalSyntheticLambda4(this, r4, r3, r2));
    }

    private void loadNext() {
        if (this.loading == false) goto L5;
        return;
    L5:
        if (this.endReached == false) goto L8;
        return;
    L8:
        if (this.storiesList == null) goto L11;
        updateFromStoriesList();
        this.storiesList.load(false, 100);
        this.loading = this.storiesList.isLoading();
        return;
    L11:
        this.loading = true;
        TLRPC.TL_messages_getSearchResultsCalendar r1 = new TLRPC.TL_messages_getSearchResultsCalendar();
        int r2 = this.photosVideosTypeFilter;
        if (r2 != 1) goto L15;
        r1.filter = new TLRPC.TL_inputMessagesFilterPhotos();
    L18:
        r1.peer = getMessagesController().getInputPeer(this.dialogId);
        if (this.topicId != 0) goto L21;
    L23:
        r1.offset_id = this.lastId;
        Calendar r0 = Calendar.getInstance();
        this.listView.setItemAnimator(null);
        getConnectionsManager().sendRequest(r1, new CalendarActivity$$ExternalSyntheticLambda3(this, r0));
        return;
    L21:
        if (this.dialogId != getUserConfig().getClientUserId()) goto L23;
        r1.flags |= 4;
        r1.saved_peer_id = getMessagesController().getInputPeer(this.topicId);
        goto L23
    L15:
        if (r2 != 2) goto L17;
        r1.filter = new TLRPC.TL_inputMessagesFilterVideo();
        goto L18
    L17:
        r1.filter = new TLRPC.TL_inputMessagesFilterPhotoVideo();
        goto L18
    }

    private void prepareBlurBitmap() {
        if (this.blurredView != null) goto L5;
        return;
    L5:
        int r0 = (int) (this.parentLayout.getView().getMeasuredWidth() / 6.0f);
        int r1 = (int) (this.parentLayout.getView().getMeasuredHeight() / 6.0f);
        Bitmap r2 = Bitmap.createBitmap(r0, r1, Bitmap.Config.ARGB_8888);
        Canvas r3 = new Canvas(r2);
        r3.scale(0.16666667f, 0.16666667f);
        this.parentLayout.getView().draw(r3);
        Utilities.stackBlurBitmap(r2, Math.max(7, Math.max(r0, r1) / 180));
        this.blurredView.setBackground(new BitmapDrawable(r2));
        this.blurredView.setAlpha(0.0f);
        this.blurredView.setVisibility(0);
    }

    private void updateColors() {
        this.actionBar.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.activeTextPaint.setColor(-1);
        TextPaint r0 = this.textPaint;
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setColor(Theme.getColor(r1));
        this.textPaint2.setColor(Theme.getColor(r1));
        this.actionBar.setTitleColor(Theme.getColor(r1));
        this.backDrawable.setColor(Theme.getColor(r1));
        this.actionBar.setItemsColor(Theme.getColor(r1), false);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_listSelector), false);
    }

    private void updateFromStoriesList() {
        this.loading = this.storiesList.isLoading();
        Calendar r0 = Calendar.getInstance();
        this.messagesByYearMounth.clear();
        this.minDate = Integer.MAX_VALUE;
        int r2 = 0;
    L4:
        if (r2 >= this.storiesList.messageObjects.size()) goto L16;
        MessageObject r3 = this.storiesList.messageObjects.get(r2);
        this.minDate = Math.min(this.minDate, r3.messageOwner.date);
        r0.setTimeInMillis(r3.messageOwner.date * 1000);
        int r10 = (r0.get(1) * 100) + r0.get(2);
        SparseArray<PeriodDay> r5 = this.messagesByYearMounth.get(r10);
        if (r5 != null) goto L8;
        r5 = new SparseArray();
        this.messagesByYearMounth.put(r10, r5);
    L8:
        int r4 = r0.get(5) - 1;
        PeriodDay r9 = r5.get(r4);
        if (r9 != null) goto L11;
        r9 = new PeriodDay(this, null);
        r9.storyItems = new ArrayList();
    L11:
        r9.storyItems.add(Integer.valueOf(r3.getId()));
        r9.messageObject = r3;
        r9.date = (int) (r0.getTimeInMillis() / 1000);
        r5.put(r4, r9);
        int r32 = this.minMontYear;
        if (r10 < r32) goto L14;
        if (r32 == 0) goto L14;
    L15:
        r2 = r2 + 1;
    L14:
        this.minMontYear = r10;
        goto L15
    L16:
        int r22 = (int) (System.currentTimeMillis() / 1000);
        int r33 = this.minDate;
    L17:
        if (r33 >= r22) goto L25;
        r0.setTimeInMillis(r33 * 1000);
        r0.set(11, 0);
        r0.set(12, 0);
        r0.set(13, 0);
        r0.set(14, 0);
        int r102 = (r0.get(1) * 100) + r0.get(2);
        SparseArray<PeriodDay> r11 = this.messagesByYearMounth.get(r102);
        if (r11 != null) goto L21;
        r11 = new SparseArray();
        this.messagesByYearMounth.put(r102, r11);
    L21:
        int r103 = r0.get(5) - 1;
        if (r11.get(r103, null) != null) goto L24;
        PeriodDay r12 = new PeriodDay(this, null);
        r12.hasImage = false;
        r12.date = (int) (r0.getTimeInMillis() / 1000);
        r11.put(r103, r12);
    L24:
        r33 = r33 + 86400;
        goto L17
    L25:
        this.endReached = this.storiesList.isFull();
        if (this.isOpened == false) goto L28;
        this.checkEnterItems = true;
    L28:
        this.listView.invalidate();
        int r02 = ((int) (((r0.getTimeInMillis() / 1000) - this.minDate) / 2629800)) + 1;
        this.adapter.notifyItemRangeChanged(0, this.monthCount);
        int r1 = this.monthCount;
        if (r02 <= r1) goto L32;
        this.adapter.notifyItemRangeInserted(r1 + 1, r02);
        this.monthCount = r02;
    L32:
        if (this.endReached == false) goto L41;
        resumeDelayedFragmentAnimation();
        return;
    }

    private void updateRowSelections(MonthView r12, boolean r13) {
        if (this.dateSelectedStart != 0) goto L5;
    L42:
        r12.dismissRowAnimations(r13);
        return;
    L5:
        if (this.dateSelectedEnd == 0) goto L42;
        if (r12.messagesByDays != null) goto L11;
        return;
    L11:
        if (r13 == true) goto L13;
        r12.dismissRowAnimations(false);
    L13:
        int r2 = r12.startDayOfWeek;
        int r5 = 0;
        int r9 = 0;
        int r3 = -1;
        int r4 = -1;
    L15:
        if (r9 >= r12.daysInMonth) goto L35;
        PeriodDay r6 = r12.messagesByDays.get(r9, null);
        if (r6 == null) goto L25;
        int r62 = r6.date;
        if (r62 < this.dateSelectedStart) goto L25;
        if (r62 > this.dateSelectedEnd) goto L25;
        if (r3 != (-1)) goto L24;
        r3 = r2;
    L24:
        r4 = r2;
    L25:
        r2 = r2 + 1;
        if (r2 < 7) goto L34;
        if (r3 == (-1)) goto L31;
        if (r4 == (-1)) goto L31;
        int r22 = r5;
        r12.animateRow(r22, r3, r4, true, r13);
    L32:
        r5 = r22 + 1;
        r2 = 0;
        r3 = -1;
        r4 = -1;
    L31:
        r22 = r5;
        r12.animateRow(r22, 0, 0, false, r13);
    L34:
        r9 = r9 + 1;
        goto L15
    L35:
        int r1 = r5;
        if (r3 == (-1)) goto L40;
        if (r4 == (-1)) goto L40;
        r12.animateRow(r1, r3, r4, true, r13);
        return;
    L40:
        r12.animateRow(r1, 0, 0, false, r13);
    }

    private void updateTitle() {
        if (this.canClearHistory == true) goto L6;
        this.actionBar.setTitle(LocaleController.getString(R.string.Calendar));
        this.backDrawable.setRotation(0.0f, true);
        return;
    L6:
        int r0 = this.dateSelectedStart;
        int r3 = this.dateSelectedEnd;
        if (r0 != r3) goto L10;
        if (r0 != 0) goto L10;
        int r02 = 0;
    L11:
        boolean r32 = this.lastInSelectionMode;
        int r5 = this.lastDaysSelected;
        if (r02 == r5) goto L14;
    L17:
        if (r5 <= r02) goto L19;
        boolean r33 = true;
    L20:
        this.lastDaysSelected = r02;
        boolean r52 = this.inSelectionMode;
        this.lastInSelectionMode = r52;
        float r6 = 1.0f;
        if (r02 <= 0) goto L23;
        String r53 = LocaleController.formatPluralString("Days", r02, new Object[0]);
        this.backDrawable.setRotation(1.0f, true);
    L26:
        if (r02 <= 1) goto L28;
        this.removeDaysButton.setText(LocaleController.formatString("ClearHistoryForTheseDays", R.string.ClearHistoryForTheseDays, new Object[0]));
    L32:
        this.actionBar.setTitleAnimated(r53, r33, 150);
        if (this.inSelectionMode == false) goto L35;
        if (r02 > 0) goto L35;
    L39:
        if (r02 > 0) goto L49;
        if (this.inSelectionMode == true) goto L49;
        if (this.selectDaysButton.getVisibility() != 8) goto L46;
        this.selectDaysButton.setAlpha(0.0f);
        this.selectDaysButton.setTranslationY(AndroidUtilities.dp(20.0f));
    L46:
        this.selectDaysButton.setVisibility(0);
        this.selectDaysButton.animate().setListener(null).cancel();
        this.removeDaysButton.animate().setListener(null).cancel();
        this.selectDaysButton.animate().alpha(1.0f).translationY(0.0f).start();
        this.removeDaysButton.animate().alpha(0.0f).translationY(-AndroidUtilities.dp(20.0f)).setDuration(150).setListener(new HideViewAfterAnimation(this.removeDaysButton)).start();
        this.selectDaysButton.setEnabled(true);
        this.removeDaysButton.setEnabled(false);
        return;
    L49:
        if (this.removeDaysButton.getVisibility() != 8) goto L51;
        this.removeDaysButton.setAlpha(0.0f);
        this.removeDaysButton.setTranslationY(-AndroidUtilities.dp(20.0f));
    L51:
        this.removeDaysButton.setVisibility(0);
        this.selectDaysButton.animate().setListener(null).cancel();
        this.removeDaysButton.animate().setListener(null).cancel();
        this.selectDaysButton.animate().alpha(0.0f).translationY(AndroidUtilities.dp(20.0f)).setDuration(150).setListener(new HideViewAfterAnimation(this.selectDaysButton)).start();
        ViewPropertyAnimator r34 = this.removeDaysButton.animate();
        if (r02 != 0) goto L54;
        r6 = 0.5f;
    L54:
        r34.alpha(r6).translationY(0.0f).start();
        this.selectDaysButton.setEnabled(false);
        this.removeDaysButton.setEnabled(true);
        return;
    L35:
        HintView r35 = this.selectDaysHint;
        if (r35 == null) goto L39;
        r35.hide();
        goto L39
    L28:
        if (r02 <= 0) goto L30;
    L31:
        this.removeDaysButton.setText(LocaleController.formatString("ClearHistoryForThisDay", R.string.ClearHistoryForThisDay, new Object[0]));
        goto L32
    L30:
        if (this.inSelectionMode == false) goto L32;
    L23:
        if (r52 == false) goto L25;
        r53 = LocaleController.getString(R.string.SelectDays);
        this.backDrawable.setRotation(1.0f, true);
        goto L26
    L25:
        r53 = LocaleController.getString(R.string.Calendar);
        this.backDrawable.setRotation(0.0f, true);
        goto L26
    L19:
        r33 = false;
        goto L20
    L14:
        if (r32 != this.inSelectionMode) goto L17;
        return;
    L10:
        r02 = (Math.abs(r0 - r3) / 86400) + 1;
        goto L11
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r15) {
        this.textPaint.setTextSize(AndroidUtilities.dp(16.0f));
        TextPaint r0 = this.textPaint;
        Paint.Align r2 = Paint.Align.CENTER;
        r0.setTextAlign(r2);
        this.textPaint2.setTextSize(AndroidUtilities.dp(11.0f));
        this.textPaint2.setTextAlign(r2);
        this.textPaint2.setTypeface(AndroidUtilities.bold());
        this.activeTextPaint.setTextSize(AndroidUtilities.dp(16.0f));
        this.activeTextPaint.setTypeface(AndroidUtilities.bold());
        this.activeTextPaint.setTextAlign(r2);
        this.contentView = new AnonymousClass2(this, r15);
        createActionBar(r15);
        this.contentView.addView(this.actionBar);
        this.actionBar.setTitle(LocaleController.getString(R.string.Calendar));
        this.actionBar.setCastShadows(false);
        AnonymousClass3 r02 = new AnonymousClass3(this, r15);
        this.listView = r02;
        LinearLayoutManager r22 = new LinearLayoutManager(r15);
        this.layoutManager = r22;
        r02.setLayoutManager(r22);
        this.layoutManager.setReverseLayout(true);
        RecyclerListView r03 = this.listView;
        CalendarAdapter r3 = new CalendarAdapter(this, null);
        this.adapter = r3;
        r03.setAdapter(r3);
        this.listView.addOnScrollListener(new AnonymousClass4(this));
        if (this.calendarType == 0) goto L5;
    L7:
        boolean r04 = false;
    L8:
        FrameLayout r32 = this.contentView;
        RecyclerListView r4 = this.listView;
        if (r04 == false) goto L11;
        float r13 = 48.0f;
    L12:
        r32.addView(r4, LayoutHelper.createFrame(-1, -1.0f, 0, 0.0f, 36.0f, 0.0f, r13));
        this.contentView.addView(new AnonymousClass5(this, r15, new String[]{LocaleController.getString(R.string.CalendarWeekNameShortMonday), LocaleController.getString(R.string.CalendarWeekNameShortTuesday), LocaleController.getString(R.string.CalendarWeekNameShortWednesday), LocaleController.getString(R.string.CalendarWeekNameShortThursday), LocaleController.getString(R.string.CalendarWeekNameShortFriday), LocaleController.getString(R.string.CalendarWeekNameShortSaturday), LocaleController.getString(R.string.CalendarWeekNameShortSunday)}, ContextCompat.getDrawable(r15, R.drawable.header_shadow).mutate()), LayoutHelper.createFrame(-1, 38.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass6(this));
        this.fragmentView = this.contentView;
        Calendar r33 = Calendar.getInstance();
        this.startFromYear = r33.get(1);
        int r34 = r33.get(2);
        this.startFromMonth = r34;
        int r6 = this.selectedYear;
        if (r6 == 0) goto L16;
        int r7 = (((this.startFromYear - r6) * 12) + r34) - this.selectedMonth;
        this.monthCount = r7 + 1;
        this.layoutManager.scrollToPositionWithOffset(r7, AndroidUtilities.dp(120.0f));
    L16:
        if (this.monthCount >= 3) goto L18;
        this.monthCount = 3;
    L18:
        BackDrawable r35 = new BackDrawable(false);
        this.backDrawable = r35;
        this.actionBar.setBackButtonDrawable(r35);
        this.backDrawable.setRotation(0.0f, false);
        loadNext();
        updateColors();
        this.activeTextPaint.setColor(-1);
        if (r04 == false) goto L22;
        AnonymousClass7 r05 = new AnonymousClass7(this, r15);
        this.bottomBar = r05;
        r05.setWillNotDraw(false);
        this.bottomBar.setPadding(0, AndroidUtilities.getShadowHeight(), 0, 0);
        this.bottomBar.setClipChildren(false);
        TextView r06 = new TextView(r15);
        this.selectDaysButton = r06;
        r06.setGravity(17);
        this.selectDaysButton.setTextSize(1, 15.0f);
        this.selectDaysButton.setTypeface(AndroidUtilities.bold());
        this.selectDaysButton.setOnClickListener(new CalendarActivity$$ExternalSyntheticLambda0(this));
        this.selectDaysButton.setText(LocaleController.getString(R.string.SelectDays));
        this.selectDaysButton.setAllCaps(true);
        this.bottomBar.addView(this.selectDaysButton, LayoutHelper.createFrame(-1, -1.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f));
        TextView r07 = new TextView(r15);
        this.removeDaysButton = r07;
        r07.setGravity(17);
        this.removeDaysButton.setTextSize(1, 15.0f);
        this.removeDaysButton.setTypeface(AndroidUtilities.bold());
        this.removeDaysButton.setOnClickListener(new CalendarActivity$$ExternalSyntheticLambda1(this));
        this.removeDaysButton.setAllCaps(true);
        this.removeDaysButton.setVisibility(8);
        this.bottomBar.addView(this.removeDaysButton, LayoutHelper.createFrame(-1, -1.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f));
        this.contentView.addView(this.bottomBar, LayoutHelper.createFrame(-1, 48.0f, 80, 0.0f, 0.0f, 0.0f, 0.0f));
        TextView r152 = this.selectDaysButton;
        int r08 = Theme.key_chat_fieldOverlayText;
        r152.setBackground(Theme.createSelectorDrawable(ColorUtils.setAlphaComponent(Theme.getColor(r08), 51), 2));
        TextView r153 = this.removeDaysButton;
        int r1 = Theme.key_text_RedBold;
        r153.setBackground(Theme.createSelectorDrawable(ColorUtils.setAlphaComponent(Theme.getColor(r1), 51), 2));
        this.selectDaysButton.setTextColor(Theme.getColor(r08));
        this.removeDaysButton.setTextColor(Theme.getColor(r1));
    L22:
        return this.fragmentView;
    L11:
        r13 = 0.0f;
        goto L12
    L5:
        if (this.canClearHistory == false) goto L7;
        r04 = true;
        goto L8
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.storiesListUpdated) goto L5;
        return;
    L5:
        if (this.storiesList != ((StoriesController.StoriesList) r3[0])) goto L9;
        updateFromStoriesList();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public int getNavigationBarColor() {
        return getThemedColor(Theme.key_windowBackgroundWhite);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        AnonymousClass9 r6 = new AnonymousClass9(this);
        new ArrayList();
        new ThemeDescription(null, 0, null, null, null, r6, Theme.key_windowBackgroundWhite);
        new ThemeDescription(null, 0, null, null, null, r6, Theme.key_windowBackgroundWhiteBlackText);
        new ThemeDescription(null, 0, null, null, null, r6, Theme.key_listSelector);
        return super.getThemeDescriptions();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_windowBackgroundWhite, null, true)) <= 0.699999988079071d) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean needDelayOpenAnimation() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (this.inSelectionMode == false) goto L9;
        if (r2 == false) goto L7;
        this.inSelectionMode = false;
        this.dateSelectedEnd = 0;
        this.dateSelectedStart = 0;
        updateTitle();
        animateSelection();
    L7:
        return false;
    L9:
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.dialogId = getArguments().getLong("dialog_id");
        this.topicId = getArguments().getLong("topic_id");
        int r0 = getArguments().getInt("type");
        this.calendarType = r0;
        if (r0 != 2) goto L6;
        this.storiesList = MessagesController.getInstance(this.currentAccount).getStoriesController().getStoriesList(this.dialogId, 0);
    L9:
        if (this.storiesList == null) goto L12;
        this.storiesPlaceProvider = new AnonymousClass1(this);
    L12:
        if (this.dialogId < 0) goto L14;
        this.canClearHistory = true;
    L16:
        if (this.storiesList == null) goto L19;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesListUpdated);
    L19:
        return super.onFragmentCreate();
    L14:
        this.canClearHistory = false;
        goto L16
    L6:
        if (r0 != 3) goto L9;
        this.storiesList = MessagesController.getInstance(this.currentAccount).getStoriesController().getStoriesList(this.dialogId, 1);
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (this.storiesList == null) goto L6;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesListUpdated);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        if (r1 == false) goto L9;
        View r12 = this.blurredView;
        if (r12 != null) goto L6;
        return;
    L6:
        if (r12.getVisibility() != 0) goto L11;
        this.blurredView.setVisibility(8);
        this.blurredView.setBackground(null);
        return;
    L11:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationProgress(boolean r2, float r3) {
        super.onTransitionAnimationProgress(r2, r3);
        View r0 = this.blurredView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getVisibility() != 0) goto L13;
        View r1 = this.blurredView;
        if (r2 == false) goto L10;
        r1.setAlpha(1.0f - r3);
        return;
    L10:
        r1.setAlpha(r3);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationStart(boolean r1, boolean r2) {
        super.onTransitionAnimationStart(r1, r2);
        this.isOpened = true;
    }

    public void setCallback(Callback r1) {
        this.callback = r1;
    }

    public void setChatActivity(ChatActivity r1) {
        this.chatActivity = r1;
    }
}
