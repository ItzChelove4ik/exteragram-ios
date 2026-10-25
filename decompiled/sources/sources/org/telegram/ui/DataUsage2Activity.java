package org.telegram.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.text.style.MetricAffectingSpan;
import android.text.style.RelativeSizeSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import com.android.dx.rop.code.RegisterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.StatsController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.CacheChart;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.SettingsActivity;

/* loaded from: classes6.dex */
public class DataUsage2Activity extends BaseFragment {
    private static int[] colors;
    private static final int[][] colors2 = null;
    private static int[] particles;
    private static int[] stats;
    private static int[] titles;
    private boolean changeStatusBar;
    private ViewPagerFixed.Adapter pageAdapter;
    private ViewPagerFixed pager;
    private Theme.ResourcesProvider resourcesProvider;
    private ViewPagerFixed.TabsView tabsView;

    /* renamed from: org.telegram.ui.DataUsage2Activity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ DataUsage2Activity this$0;

        public AnonymousClass1(DataUsage2Activity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.DataUsage2Activity$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ DataUsage2Activity this$0;

        public AnonymousClass2(DataUsage2Activity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            super.dispatchDraw(r8);
            if (this.this$0.getParentLayout() != null) goto L5;
            return;
        L5:
            if (DataUsage2Activity.m14243$$Nest$fgettabsView(this.this$0) == null) goto L9;
            float r3 = DataUsage2Activity.m14243$$Nest$fgettabsView(this.this$0).getMeasuredHeight();
            r8.drawLine(0.0f, r3, getWidth(), r3, Theme.dividerPaint);
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
        }
    }

    public class Cell extends FrameLayout {
        ImageView arrowView;
        boolean divider;
        ImageView imageView;
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        TextView textView;
        final /* synthetic */ DataUsage2Activity this$0;
        TextView valueTextView;

        public Cell(DataUsage2Activity r22, Context r23) {
            this.this$0 = r22;
            super(r23);
            setBackgroundColor(r22.getThemedColor(Theme.key_windowBackgroundWhite));
            ImageView r3 = new ImageView(r23);
            this.imageView = r3;
            r3.setScaleType(ImageView.ScaleType.CENTER);
            ImageView r32 = this.imageView;
            int r5 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r4 = 5;
        L6:
            addView(r32, LayoutHelper.createFrame(28, 28.0f, r4 | 16, 18.0f, 0.0f, 18.0f, 0.0f));
            LinearLayout r33 = new LinearLayout(r23);
            this.linearLayout = r33;
            r33.setOrientation(0);
            this.linearLayout.setWeightSum(2.0f);
            LinearLayout r34 = this.linearLayout;
            if (LocaleController.isRTL == false) goto L9;
            int r9 = 5;
        L10:
            addView(r34, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, r9 | 16, 64.0f, 0.0f, 20.0f, 0.0f));
            LinearLayout r35 = new LinearLayout(r23);
            this.linearLayout2 = r35;
            r35.setOrientation(0);
            if (LocaleController.isRTL == false) goto L13;
            this.linearLayout2.setGravity(5);
        L13:
            this.linearLayout2.setWeightSum(2.0f);
            TextView r36 = new TextView(r23);
            this.textView = r36;
            r36.setTextSize(1, 16.0f);
            TextView r37 = this.textView;
            int r11 = Theme.key_windowBackgroundWhiteBlackText;
            r37.setTextColor(r22.getThemedColor(r11));
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setSingleLine();
            this.textView.setLines(1);
            ImageView r38 = new ImageView(r23);
            this.arrowView = r38;
            r38.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.arrowView.setImageResource(R.drawable.arrow_more);
            this.arrowView.setColorFilter(new PorterDuffColorFilter(r22.getThemedColor(r11), PorterDuff.Mode.MULTIPLY));
            this.arrowView.setTranslationY(AndroidUtilities.dp(1.0f));
            this.arrowView.setVisibility(8);
            boolean r39 = LocaleController.isRTL;
            LinearLayout r112 = this.linearLayout2;
            if (r39 == false) goto L16;
            r112.addView(this.arrowView, LayoutHelper.createLinear(16, 16, 21, 3, 0, 0, 0));
            this.linearLayout2.addView(this.textView, LayoutHelper.createLinear(-2, -2, 21));
        L17:
            TextView r310 = new TextView(r23);
            this.valueTextView = r310;
            r310.setTextSize(1, 16.0f);
            this.valueTextView.setTextColor(r22.getThemedColor(Theme.key_windowBackgroundWhiteBlueText2));
            TextView r1 = this.valueTextView;
            if (LocaleController.isRTL == true) goto L21;
            r5 = 5;
        L21:
            r1.setGravity(r5);
            boolean r12 = LocaleController.isRTL;
            LinearLayout r2 = this.linearLayout;
            if (r12 == false) goto L25;
            r2.addView(this.valueTextView, LayoutHelper.createLinear(-2, -2, 19));
            this.linearLayout.addView(this.linearLayout2, LayoutHelper.createLinear(0, -2, 2.0f, 21));
            return;
        L25:
            r2.addView(this.linearLayout2, LayoutHelper.createLinear(0, -2, 2.0f, 16));
            this.linearLayout.addView(this.valueTextView, LayoutHelper.createLinear(-2, -2, 21));
            return;
        L16:
            r112.addView(this.textView, LayoutHelper.createLinear(-2, -2, 16));
            this.linearLayout2.addView(this.arrowView, LayoutHelper.createLinear(16, 16, 16, 3, 0, 0, 0));
            goto L17
        L9:
            r9 = 3;
            goto L10
        L5:
            r4 = 3;
            goto L6
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            super.onDraw(r9);
            if (this.divider == true) goto L5;
            return;
        L5:
            if (LocaleController.isRTL == false) goto L8;
            float r0 = 0.0f;
        L7:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L12;
            int r1 = AndroidUtilities.dp(64.0f);
        L13:
            r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L12:
            r1 = 0;
            goto L13
        L8:
            r0 = AndroidUtilities.dp(64.0f);
            goto L7
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        }

        public void set(int r4, int r5, int r6, CharSequence r7, CharSequence r8, boolean r9) {
            ImageView r0 = this.imageView;
            if (r6 != 0) goto L5;
            r0.setVisibility(8);
        L14:
            this.textView.setText(r7);
            this.valueTextView.setText(r8);
            this.divider = r9;
            setWillNotDraw(!r9);
            return;
        L5:
            r0.setVisibility(0);
            if (DataUsage2Activity.m14242$$Nest$fgetresourcesProvider(this.this$0) == null) goto L8;
            boolean r02 = DataUsage2Activity.m14242$$Nest$fgetresourcesProvider(this.this$0).isDark();
        L9:
            SettingsActivity.SettingCell.Background r1 = new SettingsActivity.SettingCell.Background();
            r1.setColor(r4, r5, Theme.isCurrentThemeMonet());
            r1.setDrawBorder(r02);
            this.imageView.setBackground(r1);
            ImageView r42 = this.imageView;
            if (Theme.isCurrentThemeMonet() == false) goto L12;
            int r03 = Theme.getColor(Theme.key_chats_actionIcon);
        L13:
            r42.setColorFilter(new PorterDuffColorFilter(r03, PorterDuff.Mode.SRC_IN));
            this.imageView.setImageResource(r6);
            goto L14
        L12:
            r03 = -1;
            goto L13
        L8:
            r02 = Theme.isCurrentThemeDark();
            goto L9
        }

        public void setArrow(Boolean r3) {
            ImageView r0 = this.arrowView;
            if (r3 != null) goto L6;
            r0.setVisibility(8);
            return;
        L6:
            r0.setVisibility(0);
            ViewPropertyAnimator r2 = this.arrowView.animate();
            if (r3.booleanValue() == false) goto L9;
            float r32 = 0.0f;
        L10:
            r2.rotation(r32).setDuration(360).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            return;
        L9:
            r32 = 180.0f;
            goto L10
        }
    }

    public class CustomCharacterSpan extends MetricAffectingSpan {
        double ratio;
        final /* synthetic */ DataUsage2Activity this$0;

        public CustomCharacterSpan(DataUsage2Activity r1, double r2) {
            this.this$0 = r1;
            this.ratio = r2;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r6) {
            r6.baselineShift += (int) (r6.ascent() * this.ratio);
        }

        @Override // android.text.style.MetricAffectingSpan
        public void updateMeasureState(TextPaint r6) {
            r6.baselineShift += (int) (r6.ascent() * this.ratio);
        }
    }

    public static class ItemInner extends AdapterWithDiffUtils.Item {
        public int imageColorBottom;
        public int imageColorTop;
        public int imageResId;
        public int index;
        public int key;
        public boolean pad;
        public CharSequence text;
        public CharSequence valueText;

        public ItemInner(int r2) {
            super(r2, false);
        }

        private ItemInner(int r2, int r3, int r4, int r5, int r6, CharSequence r7, CharSequence r8) {
            super(r2, false);
            this.index = r3;
            this.imageResId = r4;
            this.imageColorTop = r5;
            this.imageColorBottom = r6;
            this.text = r7;
            this.valueText = r8;
        }

        private ItemInner(int r2, CharSequence r3) {
            super(r2, false);
            this.text = r3;
        }

        public /* synthetic */ ItemInner(int r1, CharSequence r2, DataUsage2ActivityIA r3) {
            this(r1, r2);
        }

        public static ItemInner asCell(int r8, int r9, int r10, int r11, CharSequence r12, CharSequence r13) {
            return new ItemInner(2, r8, r9, r10, r11, r12, r13);
        }

        public static ItemInner asCell(int r8, int r9, int r10, CharSequence r11, CharSequence r12) {
            return new ItemInner(2, r8, r9, r10, r10, r11, r12);
        }

        public static ItemInner asHeader(String r2) {
            return new ItemInner(4, r2);
        }

        public static ItemInner asSeparator() {
            return new ItemInner(3);
        }

        public static ItemInner asSeparator(String r2) {
            return new ItemInner(3, r2);
        }

        public static ItemInner asSubtitle(String r2) {
            return new ItemInner(1, r2);
        }

        public boolean equals(Object r5) {
            if ((r5 instanceof ItemInner) == true) goto L5;
            return false;
        L5:
            ItemInner r52 = (ItemInner) r5;
            int r0 = r52.viewType;
            int r2 = this.viewType;
            if (r0 == r2) goto L9;
            return false;
        L9:
            if (r2 == 1) goto L36;
            if (r2 == 4) goto L36;
            if (r2 == 3) goto L36;
            if (r2 == 5) goto L36;
            if (r2 != 2) goto L32;
            if (r52.index == this.index) goto L22;
        L30:
            return false;
        L22:
            if (TextUtils.equals(this.text, r52.text) == false) goto L30;
            if (r52.imageColorTop != this.imageColorTop) goto L30;
            if (r52.imageColorBottom != this.imageColorBottom) goto L30;
            if (r52.imageResId != this.imageResId) goto L30;
            return true;
        L32:
            if (r52.key != this.key) goto L34;
            return true;
        L34:
            return false;
        L36:
            return TextUtils.equals(this.text, r52.text);
        }
    }

    public class ListView extends RecyclerListView {
        Adapter adapter;
        private boolean animateChart;
        private CacheChart chart;
        private Size[] chartSegments;
        private boolean[] collapsed;
        int currentType;
        private boolean empty;
        private ArrayList<ItemInner> itemInners;
        LinearLayoutManager layoutManager;
        private ArrayList<ItemInner> oldItems;
        private ArrayList<Integer> removedSegments;
        private Size[] segments;
        private int[] tempPercents;
        private float[] tempSizes;
        final /* synthetic */ DataUsage2Activity this$0;
        private long totalSize;
        private long totalSizeIn;
        private long totalSizeOut;

        public class Adapter extends AdapterWithDiffUtils {
            final /* synthetic */ ListView this$1;

            /* renamed from: org.telegram.ui.DataUsage2Activity$ListView$Adapter$1, reason: invalid class name */
            public class AnonymousClass1 extends CacheChart {
                final /* synthetic */ Adapter this$2;

                public static /* synthetic */ int $r8$lambda$ucK5uk8HNpiH2ZmDh4izJakau6w(int r0) {
                    return r0;
                }

                public AnonymousClass1(Adapter r1, Context r2, int r3, int[] r4, int r5, int[] r6) {
                    this.this$2 = r1;
                    super(r2, r3, r4, r5, r6);
                }

                @Override // org.telegram.ui.Components.CacheChart
                public int heightDp() {
                    return Opcodes.ADD_INT_LIT8;
                }

                @Override // org.telegram.ui.Components.CacheChart
                public void onSectionDown(int r6, boolean r7) {
                    if (r7 == true) goto L5;
                    this.this$2.this$1.removeHighlightRow();
                    return;
                L5:
                    if (r6 >= 0) goto L7;
                    return;
                L7:
                    if (r6 >= ListView.m14255$$Nest$fgetsegments(this.this$2.this$1).length) goto L42;
                    int r0 = 0;
                L10:
                    int r2 = -1;
                    if (r0 >= ListView.m14255$$Nest$fgetsegments(this.this$2.this$1).length) goto L16;
                    if (ListView.m14255$$Nest$fgetsegments(this.this$2.this$1)[r0].index == r6) goto L17;
                    r0 = r0 + 1;
                L17:
                    int r62 = 0;
                L19:
                    if (r62 >= ListView.m14254$$Nest$fgetitemInners(this.this$2.this$1).size()) goto L28;
                    ItemInner r1 = (ItemInner) ListView.m14254$$Nest$fgetitemInners(this.this$2.this$1).get(r62);
                    if (r1 == null) goto L27;
                    if (r1.viewType != 2) goto L27;
                    if (r1.index != r0) goto L27;
                    r2 = r62;
                L27:
                    r62 = r62 + 1;
                L28:
                    Adapter r5 = this.this$2;
                    if (r2 < 0) goto L32;
                    r5.this$1.highlightRow(new DataUsage2Activity$ListView$Adapter$1$$ExternalSyntheticLambda0(r2), 0);
                    return;
                L32:
                    r5.this$1.removeHighlightRow();
                    return;
                L16:
                    r0 = -1;
                    goto L17
                }

                @Override // org.telegram.ui.Components.CacheChart
                public int padInsideDp() {
                    return 10;
                }
            }

            /* renamed from: org.telegram.ui.DataUsage2Activity$ListView$Adapter$2, reason: invalid class name */
            public class AnonymousClass2 extends View {
                final /* synthetic */ Adapter this$2;

                public AnonymousClass2(Adapter r1, Context r2) {
                    this.this$2 = r1;
                    super(r2);
                    setBackgroundColor(ListView.access$300(r1.this$1, Theme.key_windowBackgroundWhite));
                }

                @Override // android.view.View
                public void onMeasure(int r2, int r3) {
                    super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(4.0f), TLObject.FLAG_30));
                }
            }

            private Adapter(ListView r1) {
                this.this$1 = r1;
            }

            public /* synthetic */ Adapter(ListView r1, DataUsage2ActivityIA r2) {
                this(r1);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return ListView.m14254$$Nest$fgetitemInners(this.this$1).size();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r1) {
                return ((ItemInner) ListView.m14254$$Nest$fgetitemInners(this.this$1).get(r1)).viewType;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r2) {
                ItemInner r1 = (ItemInner) ListView.m14254$$Nest$fgetitemInners(this.this$1).get(r2.getAdapterPosition());
                int r22 = r1.viewType;
                if (r22 != 5) goto L5;
                return true;
            L5:
                if (r22 == 2) goto L7;
                return false;
            L7:
                if (r1.index != (-1)) goto L14;
                return false;
            L14:
                return true;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r12, int r13) {
                ItemInner r0 = (ItemInner) ListView.m14254$$Nest$fgetitemInners(this.this$1).get(r12.getAdapterPosition());
                int r1 = r12.getItemViewType();
                if (r1 != 0) goto L10;
                CacheChart r122 = (CacheChart) r12.itemView;
                if (ListView.m14255$$Nest$fgetsegments(this.this$1) == null) goto L7;
                r122.setSegments(ListView.m14256$$Nest$fgettotalSize(this.this$1), ListView.m14250$$Nest$fgetanimateChart(this.this$1), ListView.m14252$$Nest$fgetchartSegments(this.this$1));
            L7:
                ListView.m14257$$Nest$fputanimateChart(this.this$1, false);
                return;
            L10:
                if (r1 != 1) goto L14;
                ((SubtitleCell) r12.itemView).setText(r0.text);
                return;
            L14:
                if (r1 != 2) goto L35;
                Cell r4 = (Cell) r12.itemView;
                int r5 = r0.imageColorTop;
                int r6 = r0.imageColorBottom;
                int r7 = r0.imageResId;
                CharSequence r8 = r0.text;
                CharSequence r9 = r0.valueText;
                int r132 = r13 + 1;
                if (r132 < getItemCount()) goto L18;
            L20:
                boolean r10 = false;
            L21:
                r4.set(r5, r6, r7, r8, r9, r10);
                if (r0.pad == true) goto L31;
                int r123 = r0.index;
                if (r123 < 0) goto L31;
                if (r123 < ListView.m14255$$Nest$fgetsegments(this.this$1).length) goto L28;
            L30:
                Boolean r11 = Boolean.valueOf(ListView.m14253$$Nest$fgetcollapsed(this.this$1)[r0.index]);
            L32:
                r4.setArrow(r11);
                return;
            L28:
                if (ListView.m14255$$Nest$fgetsegments(this.this$1)[r0.index].size > 0) goto L30;
            L31:
                r11 = null;
                goto L32
            L18:
                if (((ItemInner) ListView.m14254$$Nest$fgetitemInners(this.this$1).get(r132)).viewType != r1) goto L20;
                r10 = true;
                goto L21
            L35:
                if (r1 != 3) goto L39;
                ((TextInfoPrivacyCell) r12.itemView).setText(r0.text);
                return;
            L39:
                if (r1 != 4) goto L43;
                ((HeaderCell) r12.itemView).setText(r0.text);
                return;
            L43:
                if (r1 != 5) goto L47;
                ((TextCell) r12.itemView).setText(r0.text.toString(), false);
                return;
            L47:
                if (r1 != 6) goto L50;
                ((RoundingCell) r12.itemView).setTop(true);
                return;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r8, int r9) {
                if (r9 != 0) goto L5;
                ListView.m14258$$Nest$fputchart(this.this$1, new AnonymousClass1(this, this.this$1.getContext(), DataUsage2Activity.m14244$$Nest$sfgetcolors().length, DataUsage2Activity.m14244$$Nest$sfgetcolors(), 1, DataUsage2Activity.m14246$$Nest$sfgetparticles()));
                ListView.m14251$$Nest$fgetchart(this.this$1).setInterceptTouch(false);
                View r7 = ListView.m14251$$Nest$fgetchart(this.this$1);
                r7.setTag(-33024);
                View r82 = r7;
            L25:
                return new RecyclerListView.Holder(r82);
            L5:
                if (r9 != 1) goto L7;
                ListView r72 = this.this$1;
                View r92 = new SubtitleCell(r72.this$0, r72.getContext());
                r92.setTag(-33024);
                r82 = r92;
                goto L25
            L7:
                if (r9 != 3) goto L9;
                r82 = new TextInfoPrivacyCell(this.this$1.getContext());
                goto L25
            L9:
                if (r9 != 4) goto L11;
                View r83 = new HeaderCell(this.this$1.getContext());
                r83.setBackgroundColor(ListView.access$000(this.this$1, Theme.key_windowBackgroundWhite));
                r82 = r83;
                goto L25
            L11:
                if (r9 != 5) goto L13;
                TextCell r84 = new TextCell(this.this$1.getContext());
                r84.setTextColor(ListView.access$100(this.this$1, Theme.key_text_RedRegular));
                r84.setBackgroundColor(ListView.access$200(this.this$1, Theme.key_windowBackgroundWhite));
                r82 = r84;
                goto L25
            L13:
                if (r9 != 6) goto L15;
                r82 = new RoundingCell(this.this$1.getContext());
                goto L25
            L15:
                if (r9 == 7) goto L17;
                ListView r73 = this.this$1;
                r82 = new Cell(r73.this$0, r73.getContext());
                goto L25
            L17:
                r82 = new AnonymousClass2(this, this.this$1.getContext());
                goto L25
            }
        }

        public class Size extends CacheChart.SegmentSize {
            int inCount;
            long inSize;
            int index;
            int outCount;
            long outSize;
            final /* synthetic */ ListView this$1;

            public Size(ListView r1, int r2, long r3, long r5, long r7, int r9, int r10) {
                this.this$1 = r1;
                this.index = r2;
                this.size = r3;
                this.selected = true;
                this.inSize = r5;
                this.inCount = r9;
                this.outSize = r7;
                this.outCount = r10;
            }
        }

        public static /* synthetic */ void $r8$lambda$7dC2TFzvhKoD54h22VWUiZPBHRI(ListView r0, View r1, int r2) {
            r0.lambda$new$1(r1, r2);
        }

        /* renamed from: $r8$lambda$FIpa3WzTDpRTgdSrQCQk9IPBp-o, reason: not valid java name */
        public static /* synthetic */ int m14249$r8$lambda$FIpa3WzTDpRTgdSrQCQk9IPBpo(Size r2, Size r3) {
            return Long.compare(r3.size, r2.size);
        }

        public static /* synthetic */ int $r8$lambda$XtvKPuaDaeXFisrNSe5HTOlIaZc(ListView r0, int r1) {
            return r0.lambda$scrollTo$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$fz3rPUYDMTys0TUmllCGWW6bvaI(ListView r0, AlertDialog r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetanimateChart, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m14250$$Nest$fgetanimateChart(ListView r0) {
            return r0.animateChart;
        }

        /* renamed from: -$$Nest$fgetchart, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CacheChart m14251$$Nest$fgetchart(ListView r0) {
            return r0.chart;
        }

        /* renamed from: -$$Nest$fgetchartSegments, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Size[] m14252$$Nest$fgetchartSegments(ListView r0) {
            return r0.chartSegments;
        }

        /* renamed from: -$$Nest$fgetcollapsed, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean[] m14253$$Nest$fgetcollapsed(ListView r0) {
            return r0.collapsed;
        }

        /* renamed from: -$$Nest$fgetitemInners, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m14254$$Nest$fgetitemInners(ListView r0) {
            return r0.itemInners;
        }

        /* renamed from: -$$Nest$fgetsegments, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Size[] m14255$$Nest$fgetsegments(ListView r0) {
            return r0.segments;
        }

        /* renamed from: -$$Nest$fgettotalSize, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m14256$$Nest$fgettotalSize(ListView r2) {
            return r2.totalSize;
        }

        /* renamed from: -$$Nest$fputanimateChart, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14257$$Nest$fputanimateChart(ListView r0, boolean r1) {
            r0.animateChart = r1;
        }

        /* renamed from: -$$Nest$fputchart, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14258$$Nest$fputchart(ListView r0, CacheChart r1) {
            r0.chart = r1;
        }

        public ListView(DataUsage2Activity r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.animateChart = false;
            this.currentType = 0;
            this.oldItems = new ArrayList();
            this.itemInners = new ArrayList();
            this.tempSizes = new float[7];
            this.tempPercents = new int[7];
            this.removedSegments = new ArrayList();
            this.collapsed = new boolean[7];
            LinearLayoutManager r0 = new LinearLayoutManager(r4);
            this.layoutManager = r0;
            setLayoutManager(r0);
            Adapter r42 = new Adapter(this, null);
            this.adapter = r42;
            setAdapter(r42);
            setSections();
            setOnItemClickListener(new DataUsage2Activity$ListView$$ExternalSyntheticLambda0(this));
            DefaultItemAnimator r43 = new DefaultItemAnimator();
            r43.setDurations(220);
            r43.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r43.setDelayAnimations(false);
            r43.setSupportsChangeAnimations(false);
            setItemAnimator(r43);
        }

        public static /* synthetic */ int access$000(ListView r0, int r1) {
            return r0.getThemedColor(r1);
        }

        public static /* synthetic */ int access$100(ListView r0, int r1) {
            return r0.getThemedColor(r1);
        }

        public static /* synthetic */ int access$200(ListView r0, int r1) {
            return r0.getThemedColor(r1);
        }

        public static /* synthetic */ int access$300(ListView r0, int r1) {
            return r0.getThemedColor(r1);
        }

        private String formatPercent(int r1) {
            if (r1 > 0) goto L6;
            return String.format("<%d%%", new Object[]{1});
        L6:
            return String.format("%d%%", new Object[]{Integer.valueOf(r1)});
        }

        private long getBytesCount(int r3) {
            return getSentBytesCount(r3) + getReceivedBytesCount(r3);
        }

        private long getReceivedBytesCount(int r6) {
            int r0 = this.currentType;
            if (r0 == 1) goto L11;
            if (r0 == 2) goto L11;
            if (r0 == 3) goto L11;
            return (StatsController.getInstance(DataUsage2Activity.access$1700(this.this$0)).getReceivedBytesCount(0, r6) + StatsController.getInstance(DataUsage2Activity.access$1800(this.this$0)).getReceivedBytesCount(1, r6)) + StatsController.getInstance(DataUsage2Activity.access$1900(this.this$0)).getReceivedBytesCount(2, r6);
        L11:
            return StatsController.getInstance(DataUsage2Activity.access$1600(this.this$0)).getReceivedBytesCount(this.currentType - 1, r6);
        }

        private int getReceivedItemsCount(int r5) {
            int r0 = this.currentType;
            if (r0 == 1) goto L11;
            if (r0 == 2) goto L11;
            if (r0 == 3) goto L11;
            return (StatsController.getInstance(DataUsage2Activity.access$900(this.this$0)).getRecivedItemsCount(0, r5) + StatsController.getInstance(DataUsage2Activity.access$1000(this.this$0)).getRecivedItemsCount(1, r5)) + StatsController.getInstance(DataUsage2Activity.access$1100(this.this$0)).getRecivedItemsCount(2, r5);
        L11:
            return StatsController.getInstance(DataUsage2Activity.access$800(this.this$0)).getRecivedItemsCount(this.currentType - 1, r5);
        }

        private long getResetStatsDate() {
            int r0 = this.currentType;
            if (r0 == 1) goto L11;
            if (r0 == 2) goto L11;
            if (r0 == 3) goto L11;
            return min(new long[]{StatsController.getInstance(DataUsage2Activity.access$2100(this.this$0)).getResetStatsDate(0), StatsController.getInstance(DataUsage2Activity.access$2200(this.this$0)).getResetStatsDate(1), StatsController.getInstance(DataUsage2Activity.access$2300(this.this$0)).getResetStatsDate(2)});
        L11:
            return StatsController.getInstance(DataUsage2Activity.access$2000(this.this$0)).getResetStatsDate(this.currentType - 1);
        }

        private long getSentBytesCount(int r6) {
            int r0 = this.currentType;
            if (r0 == 1) goto L11;
            if (r0 == 2) goto L11;
            if (r0 == 3) goto L11;
            return (StatsController.getInstance(DataUsage2Activity.access$1300(this.this$0)).getSentBytesCount(0, r6) + StatsController.getInstance(DataUsage2Activity.access$1400(this.this$0)).getSentBytesCount(1, r6)) + StatsController.getInstance(DataUsage2Activity.access$1500(this.this$0)).getSentBytesCount(2, r6);
        L11:
            return StatsController.getInstance(DataUsage2Activity.access$1200(this.this$0)).getSentBytesCount(this.currentType - 1, r6);
        }

        private int getSentItemsCount(int r5) {
            int r0 = this.currentType;
            if (r0 == 1) goto L11;
            if (r0 == 2) goto L11;
            if (r0 == 3) goto L11;
            return (StatsController.getInstance(DataUsage2Activity.access$500(this.this$0)).getSentItemsCount(0, r5) + StatsController.getInstance(DataUsage2Activity.access$600(this.this$0)).getSentItemsCount(1, r5)) + StatsController.getInstance(DataUsage2Activity.access$700(this.this$0)).getSentItemsCount(2, r5);
        L11:
            return StatsController.getInstance(DataUsage2Activity.access$400(this.this$0)).getSentItemsCount(this.currentType - 1, r5);
        }

        private /* synthetic */ void lambda$new$0(AlertDialog r6, int r7) {
            this.removedSegments.clear();
            int r72 = 0;
        L3:
            Size[] r0 = this.segments;
            if (r72 >= r0.length) goto L9;
            Size r02 = r0[r72];
            if (r02.size <= 0) goto L8;
            this.removedSegments.add(Integer.valueOf(r02.index));
        L8:
            r72 = r72 + 1;
            goto L3
        L9:
            StatsController.getInstance(DataUsage2Activity.access$2400(this.this$0)).resetStats(0);
            StatsController.getInstance(DataUsage2Activity.access$2500(this.this$0)).resetStats(1);
            StatsController.getInstance(DataUsage2Activity.access$2600(this.this$0)).resetStats(2);
            this.animateChart = true;
            setup();
            updateRows(true);
        }

        private /* synthetic */ void lambda$new$1(View r3, int r4) {
            if ((r3 instanceof Cell) == false) goto L18;
            if (r4 < 0) goto L18;
            if (r4 >= this.itemInners.size()) goto L18;
            ItemInner r32 = this.itemInners.get(r4);
            if (r32 == null) goto L23;
            int r33 = r32.index;
            if (r33 < 0) goto L14;
            this.collapsed[r33] = !r0[r33];
            updateRows(true);
            return;
        L14:
            if (r33 != (-2)) goto L24;
            this.this$0.presentFragment(new DataAutoDownloadActivity(this.currentType - 1));
            return;
        L24:
            return;
        L23:
            return;
        L18:
            if ((r3 instanceof TextCell) == false) goto L25;
            AlertDialog.Builder r34 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r34.setTitle(LocaleController.getString(R.string.ResetStatisticsAlertTitle));
            r34.setMessage(LocaleController.getString(R.string.ResetStatisticsAlert));
            r34.setPositiveButton(LocaleController.getString(R.string.Reset), new DataUsage2Activity$ListView$$ExternalSyntheticLambda3(this));
            r34.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            AlertDialog r35 = r34.create();
            this.this$0.showDialog(r35);
            TextView r2 = (TextView) r35.getButton(-1);
            if (r2 == null) goto L26;
            r2.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        L26:
            return;
        }

        private /* synthetic */ int lambda$scrollTo$3(int r4) {
            int r0 = 0;
        L4:
            if (r0 >= this.itemInners.size()) goto L9;
            if (this.itemInners.get(r0).viewType == r4) goto L10;
            r0 = r0 + 1;
        L10:
            if (r0 >= 0) goto L12;
            return -1;
        L12:
            this.layoutManager.scrollToPositionWithOffset(r0, AndroidUtilities.dp(60.0f));
            return r0;
        L9:
            r0 = -1;
            goto L10
        }

        private long min(long... r6) {
            long r0 = LongCompanionObject.MAX_VALUE;
            int r5 = 0;
        L4:
            if (r5 >= r6.length) goto L9;
            long r2 = r6[r5];
            if (r0 <= r2) goto L8;
            r0 = r2;
        L8:
            r5 = r5 + 1;
            goto L4
        L9:
            return r0;
        }

        private void setup() {
            this.totalSize = getBytesCount(6);
            this.totalSizeIn = getReceivedBytesCount(6);
            this.totalSizeOut = getSentBytesCount(6);
            if (this.segments != null) goto L6;
            this.segments = new Size[7];
        L6:
            if (this.chartSegments != null) goto L8;
            this.chartSegments = new Size[7];
        L8:
            int r3 = 0;
        L10:
            if (r3 >= DataUsage2Activity.m14247$$Nest$sfgetstats().length) goto L12;
            long r4 = this.getBytesCount(DataUsage2Activity.m14247$$Nest$sfgetstats()[r3]);
            Size[] r0 = this.chartSegments;
            Size[] r12 = this.segments;
            ListView r2 = this;
            Size r1 = new Size(r2, r3, r4, this.getReceivedBytesCount(DataUsage2Activity.m14247$$Nest$sfgetstats()[r3]), this.getSentBytesCount(DataUsage2Activity.m14247$$Nest$sfgetstats()[r3]), this.getReceivedItemsCount(DataUsage2Activity.m14247$$Nest$sfgetstats()[r3]), this.getSentItemsCount(DataUsage2Activity.m14247$$Nest$sfgetstats()[r3]));
            r12[r3] = r1;
            r0[r3] = r1;
            r2.tempSizes[r3] = r4 / r2.totalSize;
            r3 = r3 + 1;
            ListView this = r2;
            goto L10
        L12:
            ListView r22 = this;
            Arrays.sort(r22.segments, new DataUsage2Activity$ListView$$ExternalSyntheticLambda2());
            AndroidUtilities.roundPercents(r22.tempSizes, r22.tempPercents);
            Arrays.fill(r22.collapsed, true);
        }

        private void updateRows(boolean r22) {
            this.oldItems.clear();
            this.oldItems.addAll(this.itemInners);
            this.itemInners.clear();
            this.itemInners.add(new ItemInner(0));
            long r4 = 0;
            if (this.totalSize <= 0) goto L5;
            String r1 = LocaleController.formatString(R.string.YourNetworkUsageSince, new Object[]{LocaleController.getInstance().getFormatterStats().format(getResetStatsDate())});
        L6:
            this.itemInners.add(ItemInner.asSubtitle(r1));
            ArrayList r2 = new ArrayList();
            int r6 = 0;
        L7:
            Size[] r7 = this.segments;
            if (r6 >= r7.length) goto L27;
            Size r72 = r7[r6];
            long r13 = r72.size;
            int r73 = r72.index;
            if (this.empty == false) goto L12;
        L15:
            boolean r8 = true;
        L17:
            if (r13 > r4) goto L20;
            if (r8 == true) goto L20;
            long r16 = r4;
        L26:
            r6 = r6 + 1;
            r4 = r16;
        L20:
            r16 = r4;
            SpannableString r82 = new SpannableString(formatPercent(this.tempPercents[r73]));
            r82.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r82.length(), 33);
            r82.setSpan(new RelativeSizeSpan(0.8f), 0, r82.length(), 33);
            r82.setSpan(new CustomCharacterSpan(this.this$0, 0.1d), 0, r82.length(), 33);
            int r42 = DataUsage2Activity.m14246$$Nest$sfgetparticles()[r73];
            int r5 = DataUsage2Activity.m14245$$Nest$sfgetcolors2()[r73][0];
            int r11 = DataUsage2Activity.m14245$$Nest$sfgetcolors2()[r73][1];
            if (r13 != r4) goto L24;
            CharSequence r74 = LocaleController.getString(DataUsage2Activity.m14248$$Nest$sfgettitles()[r73]);
        L25:
            r2.add(ItemInner.asCell(r6, r42, r5, r11, r74, AndroidUtilities.formatFileSize(r13)));
            goto L26
        L24:
            r74 = TextUtils.concat(new CharSequence[]{LocaleController.getString(DataUsage2Activity.m14248$$Nest$sfgettitles()[r73]), "  ", r82});
            goto L25
        L12:
            if (this.removedSegments.contains(Integer.valueOf(r73)) == true) goto L15;
            r8 = false;
            goto L17
        L27:
            long r162 = r4;
            if (r2.isEmpty() == true) goto L77;
            SpannableString r43 = new SpannableString("^");
            Drawable r52 = getContext().getResources().getDrawable(R.drawable.msg_mini_upload).mutate();
            int r75 = Theme.key_windowBackgroundWhiteBlackText;
            int r83 = getThemedColor(r75);
            PorterDuff.Mode r12 = PorterDuff.Mode.MULTIPLY;
            r52.setColorFilter(new PorterDuffColorFilter(r83, r12));
            r52.setBounds(0, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(18.0f));
            r43.setSpan(new ImageSpan(r52, 2), 0, 1, 33);
            SpannableString r53 = new SpannableString(RegisterSpec.PREFIX);
            Drawable r62 = getContext().getResources().getDrawable(R.drawable.msg_mini_download).mutate();
            r62.setColorFilter(new PorterDuffColorFilter(getThemedColor(r75), r12));
            r62.setBounds(0, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(18.0f));
            r53.setSpan(new ImageSpan(r62, 2), 0, 1, 33);
            int r63 = 0;
        L31:
            if (r63 >= r2.size()) goto L73;
            int r76 = ((ItemInner) r2.get(r63)).index;
            if (r76 < 0) goto L72;
            if (this.collapsed[r76] == true) goto L72;
            Size r77 = this.segments[r76];
            if (DataUsage2Activity.m14247$$Nest$sfgetstats()[r77.index] == 0) goto L39;
            int r84 = DataUsage2Activity.m14247$$Nest$sfgetstats()[r77.index];
            long r122 = r77.outSize;
            if (r84 == 1) goto L63;
            if (r122 <= r162) goto L55;
        L56:
            r63 = r63 + 1;
            r2.add(r63, ItemInner.asCell(-1, 0, 0, TextUtils.concat(new CharSequence[]{r43, " ", AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("FilesSentCount", r77.outCount))}), AndroidUtilities.formatFileSize(r77.outSize)));
        L58:
            if (r77.inSize <= r162) goto L60;
        L61:
            r63 = r63 + 1;
            r2.add(r63, ItemInner.asCell(-1, 0, 0, TextUtils.concat(new CharSequence[]{r53, " ", AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("FilesReceivedCount", r77.inCount))}), AndroidUtilities.formatFileSize(r77.inSize)));
            goto L72
        L60:
            if (r77.inCount <= 0) goto L72;
        L55:
            if (r77.outCount <= 0) goto L58;
        L63:
            if (r122 <= r162) goto L65;
        L66:
            r63 = r63 + 1;
            r2.add(r63, ItemInner.asCell(-1, 0, 0, TextUtils.concat(new CharSequence[]{r43, " ", LocaleController.getString(R.string.BytesSent)}), AndroidUtilities.formatFileSize(r77.outSize)));
        L68:
            if (r77.inSize <= r162) goto L70;
        L71:
            r63 = r63 + 1;
            r2.add(r63, ItemInner.asCell(-1, 0, 0, TextUtils.concat(new CharSequence[]{r53, " ", LocaleController.getString(R.string.BytesReceived)}), AndroidUtilities.formatFileSize(r77.inSize)));
            goto L72
        L70:
            if (r77.inCount <= 0) goto L72;
        L65:
            if (r77.outCount <= 0) goto L68;
        L39:
            if (r77.outSize <= r162) goto L41;
        L42:
            r63 = r63 + 1;
            r2.add(r63, ItemInner.asCell(-1, 0, 0, LocaleController.formatPluralStringComma("OutgoingCallsCount", r77.outCount), AndroidUtilities.formatFileSize(r77.outSize)));
        L44:
            if (r77.inSize <= r162) goto L46;
        L49:
            r63 = r63 + 1;
            r2.add(r63, ItemInner.asCell(-1, 0, 0, LocaleController.formatPluralStringComma("IncomingCallsCount", r77.inCount), AndroidUtilities.formatFileSize(r77.inSize)));
            goto L72
        L46:
            if (r77.inCount <= 0) goto L72;
        L41:
            if (r77.outCount <= 0) goto L44;
        L72:
            r63 = r63 + 1;
            goto L31
        L73:
            this.itemInners.addAll(r2);
            if (this.empty == true) goto L77;
            this.itemInners.add(ItemInner.asSeparator(LocaleController.getString(R.string.DataUsageSectionsInfo) + "\n"));
        L77:
            if (this.empty == true) goto L80;
            this.itemInners.add(ItemInner.asHeader(LocaleController.getString(R.string.TotalNetworkUsage)));
            this.itemInners.add(ItemInner.asCell(-1, R.drawable.msg_filled_data_sent, -11565578, -13276952, LocaleController.getString(R.string.BytesSent), AndroidUtilities.formatFileSize(this.totalSizeOut)));
            this.itemInners.add(ItemInner.asCell(-1, R.drawable.msg_filled_data_received, -11154873, -14175180, LocaleController.getString(R.string.BytesReceived), AndroidUtilities.formatFileSize(this.totalSizeIn)));
        L80:
            if (r2.isEmpty() == true) goto L83;
            this.itemInners.add(ItemInner.asSeparator(r1));
        L83:
            if (this.currentType == 0) goto L95;
            if (r2.isEmpty() == false) goto L87;
            this.itemInners.add(ItemInner.asSeparator());
        L87:
            this.itemInners.add(ItemInner.asCell(-2, R.drawable.msg_download_settings, -11565578, -13276952, LocaleController.getString(R.string.AutomaticDownloadSettings), null));
            int r14 = this.currentType;
            if (r14 == 1) goto L92;
            if (r14 == 3) goto L91;
            String r15 = LocaleController.getString(R.string.AutomaticDownloadSettingsInfoWiFi);
        L93:
            this.itemInners.add(ItemInner.asSeparator(r15));
            goto L95
        L91:
            r15 = LocaleController.getString(R.string.AutomaticDownloadSettingsInfoRoaming);
            goto L93
        L92:
            r15 = LocaleController.getString(R.string.AutomaticDownloadSettingsInfoMobile);
        L95:
            if (r2.isEmpty() == true) goto L97;
            this.itemInners.add(new ItemInner(5, LocaleController.getString(R.string.ResetStatistics), null));
        L97:
            this.itemInners.add(ItemInner.asSeparator());
            Adapter r17 = this.adapter;
            if (r17 == null) goto L110;
            if (r22 == false) goto L102;
            r17.setItems(this.oldItems, this.itemInners);
            return;
        L102:
            r17.notifyDataSetChanged();
            return;
        L110:
            return;
        L5:
            r1 = LocaleController.formatString(R.string.NoNetworkUsageSince, new Object[]{LocaleController.getInstance().getFormatterStats().format(getResetStatsDate())});
            goto L6
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
        }

        public void scrollTo(int r2) {
            highlightRow(new DataUsage2Activity$ListView$$ExternalSyntheticLambda1(this, r2));
        }

        public void setType(int r5) {
            this.currentType = r5;
            this.removedSegments.clear();
            if (getBytesCount(6) > 0) goto L5;
            boolean r52 = true;
        L6:
            this.empty = r52;
            setup();
            updateRows(false);
            return;
        L5:
            r52 = false;
            goto L6
        }
    }

    public class PageAdapter extends ViewPagerFixed.Adapter {
        final /* synthetic */ DataUsage2Activity this$0;

        private PageAdapter(DataUsage2Activity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ PageAdapter(DataUsage2Activity r1, DataUsage2ActivityIA r2) {
            this(r1);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r1, int r2, int r3) {
            ListView r12 = (ListView) r1;
            r12.setType(r2);
            r12.scrollToPosition(0);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r2) {
            DataUsage2Activity r1 = this.this$0;
            return new ListView(r1, r1.getContext());
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return 4;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public /* bridge */ /* synthetic */ CharSequence getItemTitle(int r1) {
            return getItemTitle(r1);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public String getItemTitle(int r1) {
            if (r1 == 0) goto L18;
            if (r1 == 1) goto L16;
            if (r1 == 2) goto L14;
            if (r1 == 3) goto L12;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            return LocaleController.getString(R.string.NetworkUsageRoamingTab);
        L14:
            return LocaleController.getString(R.string.NetworkUsageWiFiTab);
        L16:
            return LocaleController.getString(R.string.NetworkUsageMobileTab);
        L18:
            return LocaleController.getString(R.string.NetworkUsageAllTab);
        }
    }

    public static class RoundingCell extends View {
        Paint paint;
        Path path;
        private boolean top;

        public RoundingCell(Context r5) {
            super(r5);
            this.path = new Path();
            Paint r52 = new Paint(1);
            this.paint = r52;
            this.top = true;
            r52.setShadowLayer(AndroidUtilities.dp(1.0f), 0.0f, AndroidUtilities.dp(-0.66f), 251658240);
            this.paint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration r1) {
            super.onConfigurationChanged(r1);
            requestLayout();
        }

        @Override // android.view.View
        public void onDraw(Canvas r2) {
            super.onDraw(r2);
            r2.drawPath(this.path, this.paint);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(13.0f), TLObject.FLAG_30));
            setTop(this.top);
        }

        public void setTop(boolean r7) {
            this.path.rewind();
            this.top = r7;
            if (r7 == false) goto L6;
            float r72 = AndroidUtilities.dp(14.0f);
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(0.0f, AndroidUtilities.dp(4.0f), getMeasuredWidth(), AndroidUtilities.dp(4.0f) + (getMeasuredHeight() * 2));
            this.path.addRoundRect(r2, r72, r72, Path.Direction.CW);
            return;
        L6:
            float r73 = AndroidUtilities.dp(8.0f);
            RectF r22 = AndroidUtilities.rectTmp;
            r22.set(0.0f, ((-getMeasuredHeight()) * 2) - AndroidUtilities.dp(4.0f), getMeasuredWidth(), getMeasuredHeight() - AndroidUtilities.dp(4.0f));
            this.path.addRoundRect(r22, r73, r73, Path.Direction.CW);
        }
    }

    public class SubtitleCell extends FrameLayout {
        TextView textView;
        final /* synthetic */ DataUsage2Activity this$0;

        public SubtitleCell(DataUsage2Activity r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            TextView r0 = new TextView(r9);
            this.textView = r0;
            r0.setGravity(17);
            this.textView.setTextSize(1, 13.0f);
            this.textView.setTextColor(r8.getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
            addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 119, 24.0f, 0.0f, 24.0f, 14.0f));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void setText(CharSequence r1) {
            this.textView.setText(r1);
        }
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m14242$$Nest$fgetresourcesProvider(DataUsage2Activity r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgettabsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPagerFixed.TabsView m14243$$Nest$fgettabsView(DataUsage2Activity r0) {
        return r0.tabsView;
    }

    /* renamed from: -$$Nest$sfgetcolors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m14244$$Nest$sfgetcolors() {
        return colors;
    }

    /* renamed from: -$$Nest$sfgetcolors2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[][] m14245$$Nest$sfgetcolors2() {
        return colors2;
    }

    /* renamed from: -$$Nest$sfgetparticles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m14246$$Nest$sfgetparticles() {
        return particles;
    }

    /* renamed from: -$$Nest$sfgetstats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m14247$$Nest$sfgetstats() {
        return stats;
    }

    /* renamed from: -$$Nest$sfgettitles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m14248$$Nest$sfgettitles() {
        return titles;
    }

    static {
        colors2 = new int[][]{new int[]{-14899731, -15431455}, new int[]{-11154873, -14175180}, new int[]{-11565578, -13276952}, new int[]{-1007845, -1996271}, new int[]{-765355, -2148011}, new int[]{-3903756, -6335009}, new int[]{-13451058, -14836538}};
        colors = new int[]{Theme.key_statisticChartLine_blue, Theme.key_statisticChartLine_green, Theme.key_statisticChartLine_lightblue, Theme.key_statisticChartLine_golden, Theme.key_statisticChartLine_red, Theme.key_statisticChartLine_purple, Theme.key_statisticChartLine_cyan};
        particles = new int[]{R.drawable.msg_filled_data_videos, R.drawable.msg_filled_data_files, R.drawable.msg_filled_data_photos, R.drawable.msg_filled_data_messages, R.drawable.msg_filled_data_music, R.drawable.msg_filled_data_voice, R.drawable.msg_filled_data_calls};
        titles = new int[]{R.string.LocalVideoCache, R.string.LocalDocumentCache, R.string.LocalPhotoCache, R.string.MessagesSettings, R.string.LocalMusicCache, R.string.LocalAudioCache, R.string.CallsDataUsage};
        stats = new int[]{2, 5, 4, 1, 7, 3, 0};
    }

    public DataUsage2Activity() {
        this(null);
    }

    public DataUsage2Activity(Theme.ResourcesProvider r1) {
        this.resourcesProvider = r1;
    }

    public static /* synthetic */ int access$1000(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1100(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1200(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1300(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1400(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1500(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1600(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1700(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1800(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1900(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2000(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2100(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2200(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2300(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2400(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2500(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2600(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$600(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$700(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$800(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$900(DataUsage2Activity r0) {
        return r0.currentAccount;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r9) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setTitle(LocaleController.getString(R.string.NetworkUsage));
        ActionBar r0 = this.actionBar;
        int r1 = Theme.key_actionBarActionModeDefault;
        r0.setBackgroundColor(getThemedColor(r1));
        ActionBar r02 = this.actionBar;
        int r2 = Theme.key_windowBackgroundWhiteBlackText;
        r02.setTitleColor(getThemedColor(r2));
        this.actionBar.setItemsColor(getThemedColor(r2), false);
        this.actionBar.setItemsBackgroundColor(getThemedColor(Theme.key_listSelector), false);
        this.actionBar.setCastShadows(false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        AnonymousClass2 r03 = new AnonymousClass2(this, r9);
        r03.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundGray));
        ViewPagerFixed r22 = new ViewPagerFixed(r9);
        this.pager = r22;
        PageAdapter r92 = new PageAdapter(this, null);
        this.pageAdapter = r92;
        r22.setAdapter(r92);
        ViewPagerFixed.TabsView r93 = this.pager.createTabsView(true, 8);
        this.tabsView = r93;
        r93.setBackgroundColor(getThemedColor(r1));
        r03.addView(this.tabsView, LayoutHelper.createFrame(-1, 48, 55));
        r03.addView(this.pager, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 48.0f, 0.0f, 0.0f));
        this.fragmentView = r03;
        return r03;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public Theme.ResourcesProvider getResourceProvider() {
        return this.resourcesProvider;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (this.changeStatusBar == true) goto L7;
        return super.isLightStatusBar();
    L7:
        if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(Theme.key_actionBarActionModeDefault)) <= 0.721f) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r4) {
        if (r4 == null) goto L8;
        if (r4.getY() > (ActionBar.getCurrentActionBarHeight() + AndroidUtilities.dp(48.0f))) goto L8;
        return true;
    L8:
        if (this.pager.getCurrentPosition() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationProgress(boolean r4, float r5) {
        if (r5 > 0.5f) goto L5;
    L7:
        super.onTransitionAnimationProgress(r4, r5);
        return;
    L5:
        if (this.changeStatusBar == true) goto L7;
        this.changeStatusBar = true;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
        goto L7
    }

    public void scrollToReset() {
        View r1 = this.pager.getCurrentView();
        if ((r1 instanceof ListView) == true) goto L5;
        return;
    L5:
        ((ListView) r1).scrollTo(5);
    }

    public void selectTab(int r1) {
        this.tabsView.scrollToTab(r1, r1);
    }
}
