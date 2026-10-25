package org.telegram.ui.Gifts;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.FireworksOverlay;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes7.dex */
public class ResaleGiftsFragment extends BaseFragment implements FactorAnimator.Target {
    private final BoolAnimator animatorClearFiltersButtonVisible;
    private BackDrawable backDrawable;
    private Filter backdropButton;
    private TextView clearFiltersButton;
    private FrameLayout clearFiltersContainer;
    private Utilities.Callback<Boolean> closeParentSheet;
    private final long dialogId;
    private LargeEmptyView emptyView;
    private boolean emptyViewVisible;
    private HorizontalScrollView filterScrollView;
    private LinearLayout filtersContainer;
    private View filtersDivider;
    private boolean filtersShown;
    private FireworksOverlay fireworksOverlay;
    private final long gift_id;
    private final String gift_name;
    private BlurredBackgroundDrawableViewFactory iBlur3Factory;
    private BlurredBackgroundSourceColor iBlur3SourceColor;
    private final ResaleGiftsList list;
    private UniversalRecyclerView listView;
    private Filter modelButton;
    private FrameLayout onlyStarsContainer;
    private Filter patternButton;
    private Filter sortButton;

    /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ResaleGiftsFragment this$0;

        public AnonymousClass1(ResaleGiftsFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$10, reason: invalid class name */
    public class AnonymousClass10 implements TextWatcher {
        final /* synthetic */ ResaleGiftsFragment this$0;
        final /* synthetic */ UniversalRecyclerView val$listView;
        final /* synthetic */ String[] val$query;

        public AnonymousClass10(ResaleGiftsFragment r1, String[] r2, UniversalRecyclerView r3) {
            this.this$0 = r1;
            this.val$query = r2;
            this.val$listView = r3;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            this.val$query[0] = r3.toString();
            this.val$listView.adapter.update(true);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$11, reason: invalid class name */
    public class AnonymousClass11 extends AnimatorListenerAdapter {
        final /* synthetic */ ResaleGiftsFragment this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass11(ResaleGiftsFragment r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$show == true) goto L6;
            ResaleGiftsFragment.m14970$$Nest$fgetfilterScrollView(this.this$0).setVisibility(8);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$12, reason: invalid class name */
    public class AnonymousClass12 extends AnimatorListenerAdapter {
        final /* synthetic */ ResaleGiftsFragment this$0;
        final /* synthetic */ boolean val$visible;

        public AnonymousClass12(ResaleGiftsFragment r1, boolean r2) {
            this.this$0 = r1;
            this.val$visible = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$visible == true) goto L6;
            ResaleGiftsFragment.m14969$$Nest$fgetemptyView(this.this$0).setVisibility(8);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$13, reason: invalid class name */
    public class AnonymousClass13 extends ChatActivity {
        private boolean shownToast;
        final /* synthetic */ ResaleGiftsFragment this$0;
        final /* synthetic */ TL_stars.TL_starGiftUnique val$boughtGift;
        final /* synthetic */ long val$dialogId;

        public AnonymousClass13(ResaleGiftsFragment r1, Bundle r2, TL_stars.TL_starGiftUnique r3, long r4) {
            this.this$0 = r1;
            this.val$boughtGift = r3;
            this.val$dialogId = r4;
            super(r2);
            this.shownToast = false;
        }

        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.ActionBar.BaseFragment
        public void onBecomeFullyVisible() {
            super.onBecomeFullyVisible();
            if (this.shownToast == true) goto L8;
            this.shownToast = true;
            BulletinFactory.of(this).createSimpleBulletin(this.val$boughtGift.getDocument(), LocaleController.getString(R.string.BoughtResoldGiftToTitle), LocaleController.formatString(R.string.BoughtResoldGiftToText, new Object[]{DialogObject.getShortName(this.currentAccount, this.val$dialogId)})).hideAfterBottomSheet(false).show();
            FireworksOverlay r8 = this.fireworksOverlay;
            if (r8 == null) goto L9;
            r8.start(true);
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        final /* synthetic */ ResaleGiftsFragment this$0;

        public AnonymousClass2(ResaleGiftsFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) ResaleGiftsFragment.m14970$$Nest$fgetfilterScrollView(this.this$0).getLayoutParams();
            int r1 = ActionBar.getCurrentActionBarHeight();
            int r3 = 0;
            if (ResaleGiftsFragment.access$000(this.this$0).getOccupyStatusBar() == false) goto L5;
            int r2 = AndroidUtilities.statusBarHeight;
        L6:
            r0.topMargin = r1 + r2;
            FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) ResaleGiftsFragment.m14971$$Nest$fgetfiltersDivider(this.this$0).getLayoutParams();
            int r12 = ActionBar.getCurrentActionBarHeight();
            if (ResaleGiftsFragment.access$100(this.this$0).getOccupyStatusBar() == false) goto L9;
            int r22 = AndroidUtilities.statusBarHeight;
        L10:
            r02.topMargin = (r12 + r22) + AndroidUtilities.dp(47.0f);
            FrameLayout.LayoutParams r03 = (FrameLayout.LayoutParams) ResaleGiftsFragment.m14974$$Nest$fgetlistView(this.this$0).getLayoutParams();
            int r13 = ActionBar.getCurrentActionBarHeight();
            if (ResaleGiftsFragment.access$200(this.this$0).getOccupyStatusBar() == false) goto L13;
            int r23 = AndroidUtilities.statusBarHeight;
        L14:
            r03.topMargin = r13 + r23;
            FrameLayout.LayoutParams r04 = (FrameLayout.LayoutParams) ResaleGiftsFragment.m14969$$Nest$fgetemptyView(this.this$0).getLayoutParams();
            int r14 = ActionBar.getCurrentActionBarHeight();
            if (ResaleGiftsFragment.access$300(this.this$0).getOccupyStatusBar() == false) goto L17;
            r3 = AndroidUtilities.statusBarHeight;
        L17:
            r04.topMargin = r14 + r3;
            super.onMeasure(r5, r6);
            return;
        L13:
            r23 = 0;
            goto L14
        L9:
            r22 = 0;
            goto L10
        L5:
            r2 = 0;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$3, reason: invalid class name */
    public class AnonymousClass3 extends UniversalRecyclerView {
        final /* synthetic */ ResaleGiftsFragment this$0;

        public AnonymousClass3(ResaleGiftsFragment r1, BaseFragment r2, Utilities.Callback2 r3, Utilities.Callback5 r4, Utilities.Callback5Return r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public Integer getSelectorColor(int r1) {
            return 0;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ResaleGiftsFragment this$0;

        public AnonymousClass4(ResaleGiftsFragment r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (ResaleGiftsFragment.m14975$$Nest$misLoadingVisible(this.this$0) == false) goto L5;
            ResaleGiftsFragment.m14973$$Nest$fgetlist(this.this$0).load();
        L5:
            ViewPropertyAnimator r12 = ResaleGiftsFragment.m14971$$Nest$fgetfiltersDivider(this.this$0).animate();
            if (ResaleGiftsFragment.m14972$$Nest$fgetfiltersShown(this.this$0) == true) goto L8;
        L11:
            float r0 = 0.0f;
        L12:
            r12.alpha(r0).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(320).start();
            return;
        L8:
            if (ResaleGiftsFragment.m14974$$Nest$fgetlistView(this.this$0).canScrollVertically(-1) == false) goto L11;
            r0 = 1.0f;
            goto L12
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$5, reason: invalid class name */
    public class AnonymousClass5 extends UniversalRecyclerView {
        final /* synthetic */ ResaleGiftsFragment this$0;

        public AnonymousClass5(ResaleGiftsFragment r1, BaseFragment r2, Utilities.Callback2 r3, Utilities.Callback5 r4, Utilities.Callback5Return r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(Math.min((int) (AndroidUtilities.displaySize.y * 0.35f), View.MeasureSpec.getSize(r4)), View.MeasureSpec.getMode(r4)));
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$6, reason: invalid class name */
    public class AnonymousClass6 implements TextWatcher {
        final /* synthetic */ ResaleGiftsFragment this$0;
        final /* synthetic */ UniversalRecyclerView val$listView;
        final /* synthetic */ String[] val$query;

        public AnonymousClass6(ResaleGiftsFragment r1, String[] r2, UniversalRecyclerView r3) {
            this.this$0 = r1;
            this.val$query = r2;
            this.val$listView = r3;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            this.val$query[0] = r3.toString();
            this.val$listView.adapter.update(true);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$7, reason: invalid class name */
    public class AnonymousClass7 extends UniversalRecyclerView {
        final /* synthetic */ ResaleGiftsFragment this$0;

        public AnonymousClass7(ResaleGiftsFragment r1, BaseFragment r2, Utilities.Callback2 r3, Utilities.Callback5 r4, Utilities.Callback5Return r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(Math.min((int) (AndroidUtilities.displaySize.y * 0.35f), View.MeasureSpec.getSize(r4)), View.MeasureSpec.getMode(r4)));
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$8, reason: invalid class name */
    public class AnonymousClass8 implements TextWatcher {
        final /* synthetic */ ResaleGiftsFragment this$0;
        final /* synthetic */ UniversalRecyclerView val$listView;
        final /* synthetic */ String[] val$query;

        public AnonymousClass8(ResaleGiftsFragment r1, String[] r2, UniversalRecyclerView r3) {
            this.this$0 = r1;
            this.val$query = r2;
            this.val$listView = r3;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            this.val$query[0] = r3.toString();
            this.val$listView.adapter.update(true);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$9, reason: invalid class name */
    public class AnonymousClass9 extends UniversalRecyclerView {
        final /* synthetic */ ResaleGiftsFragment this$0;

        public AnonymousClass9(ResaleGiftsFragment r1, BaseFragment r2, Utilities.Callback2 r3, Utilities.Callback5 r4, Utilities.Callback5Return r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(Math.min((int) (AndroidUtilities.displaySize.y * 0.35f), View.MeasureSpec.getSize(r4)), View.MeasureSpec.getMode(r4)));
        }
    }

    public static class BackdropItem extends ActionBarMenuSubItem {

        public static class Factory extends UItem.UItemFactory<BackdropItem> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asBackdrop(TL_stars.starGiftAttributeBackdrop r1, int r2, String r3) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                r0.text = r3;
                r0.intValue = r2;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((BackdropItem) r1).set((TL_stars.starGiftAttributeBackdrop) r2.object, r2.intValue, (String) r2.text, r2.checked);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public BackdropItem createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new BackdropItem(r1, r5);
            }
        }

        public BackdropItem(Context r3, Theme.ResourcesProvider r4) {
            super(r3, false, false, r4);
            setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
            setColors(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r4), Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, r4));
            setIconColor(-1, PorterDuff.Mode.MULTIPLY);
            this.imageView.setTranslationX(AndroidUtilities.dp(2.0f));
            makeCheckView(2);
            setBackground(null);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuSubItem, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            int r0 = View.MeasureSpec.getSize(r3);
            if (View.MeasureSpec.getMode(r3) != Integer.MIN_VALUE) goto L5;
            r0 = AndroidUtilities.dp(250.0f);
        L5:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), r4);
        }

        public void set(TL_stars.starGiftAttributeBackdrop r4, int r5, String r6, boolean r7) {
            ShapeDrawable r0 = Theme.createCircleDrawable(AndroidUtilities.dp(20.0f), r4.center_color | (-16777216));
            CharSequence r42 = r4.name;
            if (TextUtils.isEmpty(r6) == true) goto L5;
            r42 = AndroidUtilities.highlightText(r42, r6, this.resourcesProvider);
        L5:
            if (r5 <= 0) goto L7;
            SpannableStringBuilder r62 = new SpannableStringBuilder(r42);
            r62.append("  ");
            int r43 = r62.length();
            r62.append(Integer.toString(r5));
            r62.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r43, r62.length(), 33);
            r42 = r62;
        L7:
            setTextAndIcon(r42, 0, r0);
            setChecked(r7);
        }
    }

    public static class EmptyView extends LinearLayout {
        private final BackupImageView imageView;
        private final TextView textView;

        public static class Factory extends UItem.UItemFactory<EmptyView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asEmptyView(CharSequence r1) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.text = r1;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((EmptyView) r1).set(r2.text);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public EmptyView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new EmptyView(r1, r5);
            }
        }

        public EmptyView(Context r14, Theme.ResourcesProvider r15) {
            super(r14);
            setOrientation(1);
            BackupImageView r1 = new BackupImageView(r14);
            this.imageView = r1;
            r1.setImageDrawable(new RLottieDrawable(R.raw.utyan_empty, AndroidUtilities.dp(130.0f), AndroidUtilities.dp(130.0f)));
            addView(r1, LayoutHelper.createLinear(64, 64, 17, 0, 32, 0, 0));
            TextView r12 = new TextView(r14);
            this.textView = r12;
            r12.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, r15));
            r12.setTextSize(1, 14.0f);
            r12.setGravity(17);
            addView(r12, LayoutHelper.createLinear(-1, -2, 7, 12, 12, 12, 24));
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            int r0 = View.MeasureSpec.getSize(r3);
            if (View.MeasureSpec.getMode(r3) != Integer.MIN_VALUE) goto L5;
            r0 = AndroidUtilities.dp(250.0f);
        L5:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), r4);
        }

        public void set(CharSequence r1) {
            this.textView.setText(r1);
        }
    }

    public static class Filter extends TextView {
        private ColoredImageSpan span;

        public Filter(Context r3, Theme.ResourcesProvider r4) {
            super(r3);
            int r32 = Theme.getColor(Theme.key_actionBarActionModeDefaultIcon, r4);
            setTextColor(r32);
            setBackground(Theme.createRadSelectorDrawable(Theme.multAlpha(r32, 0.08f), Theme.multAlpha(r32, 0.15f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f)));
            setPadding(AndroidUtilities.dp(11.0f), 0, AndroidUtilities.dp(11.0f), 0);
            setGravity(17);
            setTypeface(AndroidUtilities.bold());
            ScaleStateListAnimator.apply(this);
            ColoredImageSpan r33 = new ColoredImageSpan(R.drawable.arrows_select);
            this.span = r33;
            r33.spaceScaleX = 0.8f;
            r33.translate(0.0f, AndroidUtilities.dp(1.0f));
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(26.0f), TLObject.FLAG_30));
        }

        public void setSorting(ResaleGiftsList.Sorting r6) {
            SpannableStringBuilder r0 = new SpannableStringBuilder("v ");
            if (r6 != ResaleGiftsList.Sorting.BY_DATE) goto L6;
            ColoredImageSpan r62 = new ColoredImageSpan(R.drawable.mini_gift_sorting_date);
            r0.setSpan(r62, 0, 1, 33);
            r0.append(LocaleController.getString(R.string.ResellGiftFilterSortDateShort));
        L12:
            if (r62 == null) goto L14;
            r62.translate(0.0f, AndroidUtilities.dp(1.0f));
        L14:
            setText(r0);
            return;
        L6:
            if (r6 != ResaleGiftsList.Sorting.BY_PRICE) goto L9;
            r62 = new ColoredImageSpan(R.drawable.mini_gift_sorting_price);
            r0.setSpan(r62, 0, 1, 33);
            r0.append(LocaleController.getString(R.string.ResellGiftFilterSortPriceShort));
            goto L12
        L9:
            if (r6 != ResaleGiftsList.Sorting.BY_NUMBER) goto L11;
            r62 = new ColoredImageSpan(R.drawable.mini_gift_sorting_num);
            r0.setSpan(r62, 0, 1, 33);
            r0.append(LocaleController.getString(R.string.ResellGiftFilterSortNumberShort));
            goto L12
        L11:
            r62 = null;
            goto L12
        }

        public void setValue(CharSequence r5) {
            SpannableStringBuilder r52 = new SpannableStringBuilder(r5).append(" v");
            r52.setSpan(this.span, r52.length() - 1, r52.length(), 33);
            setText(r52);
        }
    }

    public static class LargeEmptyView extends FrameLayout {
        private final TextView buttonView;
        private final BackupImageView imageView;
        private final LinearLayout layout;
        private final TextView subtitleView;
        private final TextView titleView;

        public LargeEmptyView(Context r13, View.OnClickListener r14, Theme.ResourcesProvider r15) {
            super(r13);
            LinearLayout r0 = new LinearLayout(r13);
            this.layout = r0;
            r0.setOrientation(1);
            addView(r0, LayoutHelper.createFrame(-1, -2, 23));
            BackupImageView r2 = new BackupImageView(r13);
            this.imageView = r2;
            r2.setImageDrawable(new RLottieDrawable(R.raw.utyan_empty, AndroidUtilities.dp(130.0f), AndroidUtilities.dp(130.0f)));
            r0.addView(r2, LayoutHelper.createLinear(130, 130, 17));
            TextView r22 = new TextView(r13);
            this.titleView = r22;
            r22.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r15));
            r22.setTextSize(1, 17.0f);
            r22.setGravity(17);
            r22.setTypeface(AndroidUtilities.bold());
            r22.setText(LocaleController.getString(R.string.Gift2ResaleFiltersEmptyTitle));
            r0.addView(r22, LayoutHelper.createLinear(-2, -2, 17, 32, 12, 32, 9));
            LinkSpanDrawable.LinksTextView r23 = new LinkSpanDrawable.LinksTextView(r13);
            this.subtitleView = r23;
            r23.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, r15));
            r23.setTextSize(1, 14.0f);
            r23.setGravity(17);
            r23.setText(LocaleController.getString(R.string.Gift2ResaleFiltersEmptySubtitle));
            r23.setMaxWidth(AndroidUtilities.dp(200.0f));
            r0.addView(r23, LayoutHelper.createLinear(-2, -2, 17, 32, 0, 32, 12));
            TextView r1 = new TextView(r13);
            this.buttonView = r1;
            int r12 = Theme.key_featuredStickers_addButton;
            r1.setTextColor(Theme.getColor(r12, r15));
            r1.setBackground(Theme.createRadSelectorDrawable(Theme.multAlpha(Theme.getColor(r12, r15), 0.1f), 6, 6));
            r1.setGravity(17);
            r1.setText(LocaleController.getString(R.string.Gift2ResaleFiltersEmptyClear));
            r1.setPadding(AndroidUtilities.dp(13.0f), 0, AndroidUtilities.dp(13.0f), 0);
            ScaleStateListAnimator.apply(r1);
            r0.addView(r1, LayoutHelper.createLinear(-2, 27, 17, 32, 0, 32, 12));
            r1.setOnClickListener(r14);
        }
    }

    public static class ModelItem extends ActionBarMenuSubItem {
        private final int currentAccount;
        private AnimatedEmojiDrawable emojiDrawable;
        private long emojiDrawableId;

        /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$ModelItem$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnAttachStateChangeListener {
            final /* synthetic */ ModelItem this$0;

            public AnonymousClass1(ModelItem r1) {
                this.this$0 = r1;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View r1) {
                if (ModelItem.m14976$$Nest$fgetemojiDrawable(this.this$0) == null) goto L6;
                ModelItem.m14976$$Nest$fgetemojiDrawable(this.this$0).addView(this.this$0.imageView);
                return;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View r1) {
                if (ModelItem.m14976$$Nest$fgetemojiDrawable(this.this$0) == null) goto L6;
                ModelItem.m14976$$Nest$fgetemojiDrawable(this.this$0).removeView(this.this$0.imageView);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$ModelItem$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatedEmojiDrawable {
            final /* synthetic */ ModelItem this$0;

            public AnonymousClass2(ModelItem r1, int r2, int r3, TLRPC.Document r4) {
                this.this$0 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Components.AnimatedEmojiDrawable, android.graphics.drawable.Drawable
            public int getIntrinsicHeight() {
                return AndroidUtilities.dp(24.0f);
            }

            @Override // org.telegram.ui.Components.AnimatedEmojiDrawable, android.graphics.drawable.Drawable
            public int getIntrinsicWidth() {
                return AndroidUtilities.dp(24.0f);
            }
        }

        public static class Factory extends UItem.UItemFactory<ModelItem> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asModel(TL_stars.starGiftAttributeModel r1, int r2, String r3) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                r0.text = r3;
                r0.intValue = r2;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((ModelItem) r1).set((TL_stars.starGiftAttributeModel) r2.object, r2.intValue, (String) r2.text, r2.checked);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public ModelItem createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new ModelItem(r1, r3, r5);
            }
        }

        /* renamed from: -$$Nest$fgetemojiDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable m14976$$Nest$fgetemojiDrawable(ModelItem r0) {
            return r0.emojiDrawable;
        }

        public ModelItem(Context r2, int r3, Theme.ResourcesProvider r4) {
            super(r2, false, false, r4);
            this.currentAccount = r3;
            setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
            setColors(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r4), Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, r4));
            setIconColor(-1);
            this.imageView.setTranslationX(AndroidUtilities.dp(2.0f));
            this.imageView.setScaleX(1.2f);
            this.imageView.setScaleY(1.2f);
            makeCheckView(2);
            setBackground(null);
            this.imageView.addOnAttachStateChangeListener(new AnonymousClass1(this));
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuSubItem, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            int r0 = View.MeasureSpec.getSize(r3);
            if (View.MeasureSpec.getMode(r3) != Integer.MIN_VALUE) goto L5;
            r0 = AndroidUtilities.dp(250.0f);
        L5:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), r4);
        }

        public void set(TL_stars.starGiftAttributeModel r6, int r7, String r8, boolean r9) {
            AnimatedEmojiDrawable r0 = this.emojiDrawable;
            if (r0 != null) goto L5;
        L6:
            this.emojiDrawableId = r6.document.id;
            if (r0 == null) goto L9;
            r0.removeView(this.imageView);
        L9:
            this.emojiDrawable = new AnonymousClass2(this, 3, this.currentAccount, r6.document);
        L11:
            if (this.imageView.isAttachedToWindow() == false) goto L13;
            this.emojiDrawable.addView(this.imageView);
        L13:
            CharSequence r62 = r6.name;
            if (TextUtils.isEmpty(r8) == true) goto L16;
            r62 = AndroidUtilities.highlightText(r62, r8, this.resourcesProvider);
        L16:
            if (r7 <= 0) goto L18;
            SpannableStringBuilder r82 = new SpannableStringBuilder(r62);
            r82.append("  ");
            int r63 = r82.length();
            r82.append(Integer.toString(r7));
            r82.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r63, r82.length(), 33);
            r62 = r82;
        L18:
            setTextAndIcon(r62, 0, this.emojiDrawable);
            setChecked(r9);
            return;
        L5:
            if (this.emojiDrawableId == r6.document.id) goto L11;
            goto L6
        }
    }

    public static class PatternItem extends ActionBarMenuSubItem {
        private final int currentAccount;
        private AnimatedEmojiDrawable emojiDrawable;
        private long emojiDrawableId;

        /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$PatternItem$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnAttachStateChangeListener {
            final /* synthetic */ PatternItem this$0;

            public AnonymousClass1(PatternItem r1) {
                this.this$0 = r1;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View r1) {
                if (PatternItem.m14977$$Nest$fgetemojiDrawable(this.this$0) == null) goto L6;
                PatternItem.m14977$$Nest$fgetemojiDrawable(this.this$0).addView(this.this$0.imageView);
                return;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View r1) {
                if (PatternItem.m14977$$Nest$fgetemojiDrawable(this.this$0) == null) goto L6;
                PatternItem.m14977$$Nest$fgetemojiDrawable(this.this$0).removeView(this.this$0.imageView);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$PatternItem$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatedEmojiDrawable {
            final /* synthetic */ PatternItem this$0;

            public AnonymousClass2(PatternItem r1, int r2, int r3, TLRPC.Document r4) {
                this.this$0 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Components.AnimatedEmojiDrawable, android.graphics.drawable.Drawable
            public int getIntrinsicHeight() {
                return AndroidUtilities.dp(24.0f);
            }

            @Override // org.telegram.ui.Components.AnimatedEmojiDrawable, android.graphics.drawable.Drawable
            public int getIntrinsicWidth() {
                return AndroidUtilities.dp(24.0f);
            }
        }

        public static class Factory extends UItem.UItemFactory<PatternItem> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asPattern(TL_stars.starGiftAttributePattern r1, int r2, String r3) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                r0.text = r3;
                r0.intValue = r2;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((PatternItem) r1).set((TL_stars.starGiftAttributePattern) r2.object, r2.intValue, (String) r2.text, r2.checked);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public PatternItem createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new PatternItem(r1, r3, r5);
            }
        }

        /* renamed from: -$$Nest$fgetemojiDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable m14977$$Nest$fgetemojiDrawable(PatternItem r0) {
            return r0.emojiDrawable;
        }

        public PatternItem(Context r2, int r3, Theme.ResourcesProvider r4) {
            super(r2, false, false, r4);
            this.currentAccount = r3;
            setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
            int r22 = Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r4);
            int r32 = Theme.key_actionBarDefaultSubmenuItemIcon;
            setColors(r22, Theme.getColor(r32, r4));
            setIconColor(Theme.getColor(r32, r4), PorterDuff.Mode.SRC_IN);
            this.imageView.setTranslationX(AndroidUtilities.dp(2.0f));
            makeCheckView(2);
            setBackground(null);
            this.imageView.addOnAttachStateChangeListener(new AnonymousClass1(this));
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuSubItem, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            int r0 = View.MeasureSpec.getSize(r3);
            if (View.MeasureSpec.getMode(r3) != Integer.MIN_VALUE) goto L5;
            r0 = AndroidUtilities.dp(250.0f);
        L5:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), r4);
        }

        public void set(TL_stars.starGiftAttributePattern r6, int r7, String r8, boolean r9) {
            AnimatedEmojiDrawable r0 = this.emojiDrawable;
            if (r0 != null) goto L5;
        L6:
            this.emojiDrawableId = r6.document.id;
            if (r0 == null) goto L9;
            r0.removeView(this.imageView);
        L9:
            AnonymousClass2 r02 = new AnonymousClass2(this, 3, this.currentAccount, r6.document);
            this.emojiDrawable = r02;
            r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
        L11:
            if (this.imageView.isAttachedToWindow() == false) goto L13;
            this.emojiDrawable.addView(this.imageView);
        L13:
            CharSequence r62 = r6.name;
            if (TextUtils.isEmpty(r8) == true) goto L16;
            r62 = AndroidUtilities.highlightText(r62, r8, this.resourcesProvider);
        L16:
            if (r7 <= 0) goto L18;
            SpannableStringBuilder r82 = new SpannableStringBuilder(r62);
            r82.append("  ");
            int r63 = r82.length();
            r82.append(Integer.toString(r7));
            r82.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r63, r82.length(), 33);
            r62 = r82;
        L18:
            setTextAndIcon(r62, 0, this.emojiDrawable);
            setChecked(r9);
            return;
        L5:
            if (this.emojiDrawableId == r6.document.id) goto L11;
            goto L6
        }
    }

    public static class ResaleGiftsList implements StarsController.IGiftsList {
        private final int account;
        private long attributes_hash;
        public final ArrayList<TL_stars.starGiftAttributeBackdrop> backdropAttributes;
        public final HashMap<Integer, Integer> backdropAttributesCounter;
        public boolean endReached;
        private boolean for_craft;
        public final long gift_id;
        public final ArrayList<TL_stars.TL_starGiftUnique> gifts;
        private String last_offset;
        public boolean loading;
        public final ArrayList<TL_stars.starGiftAttributeModel> modelAttributes;
        public final HashMap<Long, Integer> modelAttributesCounter;
        public final HashSet<Integer> notSelectedBackdropAttributes;
        public final HashSet<Long> notSelectedModelAttributes;
        public final HashSet<Long> notSelectedPatternAttributes;
        private final Utilities.Callback<Boolean> onUpdate;
        public final ArrayList<TL_stars.starGiftAttributePattern> patternAttributes;
        public final HashMap<Long, Integer> patternAttributesCounter;
        private int reqId;
        private Sorting sorting;
        private boolean starsOnly;
        private int totalCount;

        public enum Sorting extends Enum<Sorting> {
            private static final /* synthetic */ Sorting[] $VALUES = null;
            public static final Sorting BY_DATE = null;
            public static final Sorting BY_NUMBER = null;
            public static final Sorting BY_PRICE = null;
            public int buttonStringResId;
            public int shortResId;

            private static /* synthetic */ Sorting[] $values() {
                return new Sorting[]{BY_PRICE, BY_DATE, BY_NUMBER};
            }

            static {
                BY_PRICE = new Sorting("BY_PRICE", 0, R.string.ResellGiftFilterSortPriceShort, R.string.ResellGiftFilterSortPrice);
                BY_DATE = new Sorting("BY_DATE", 1, R.string.ResellGiftFilterSortDateShort, R.string.ResellGiftFilterSortDate);
                BY_NUMBER = new Sorting("BY_NUMBER", 2, R.string.ResellGiftFilterSortNumberShort, R.string.ResellGiftFilterSortNumber);
                $VALUES = $values();
            }

            Sorting(String r1, int r2, int r3, int r4) {
                this.shortResId = r3;
                this.buttonStringResId = r4;
            }

            public static Sorting valueOf(String r1) {
                return (Sorting) Enum.valueOf(Sorting.class, r1);
            }

            public static Sorting[] values() {
                return (Sorting[]) $VALUES.clone();
            }
        }

        /* renamed from: $r8$lambda$esF0R8g-uhoV2-1LgyUuNNwCOcE, reason: not valid java name */
        public static /* synthetic */ void m14978$r8$lambda$esF0R8guhoV21LgyUuNNwCOcE(ResaleGiftsList r0, TL_stars.getResaleStarGifts r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$load$1(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$juxFmajv_LqUZGslBhKrCmg-T2s, reason: not valid java name */
        public static /* synthetic */ void m14979$r8$lambda$juxFmajv_LqUZGslBhKrCmgT2s(ResaleGiftsList r0, TLObject r1, TL_stars.getResaleStarGifts r2) {
            r0.lambda$load$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetstarsOnly, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m14980$$Nest$fgetstarsOnly(ResaleGiftsList r0) {
            return r0.starsOnly;
        }

        /* renamed from: -$$Nest$fputstarsOnly, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14981$$Nest$fputstarsOnly(ResaleGiftsList r0, boolean r1) {
            r0.starsOnly = r1;
        }

        public ResaleGiftsList(int r2, long r3, Utilities.Callback<Boolean> r5) {
            this.gifts = new ArrayList();
            this.modelAttributes = new ArrayList();
            this.backdropAttributes = new ArrayList();
            this.patternAttributes = new ArrayList();
            this.notSelectedModelAttributes = new HashSet();
            this.notSelectedBackdropAttributes = new HashSet();
            this.notSelectedPatternAttributes = new HashSet();
            this.modelAttributesCounter = new HashMap();
            this.backdropAttributesCounter = new HashMap();
            this.patternAttributesCounter = new HashMap();
            this.sorting = Sorting.BY_PRICE;
            this.endReached = false;
            this.reqId = -1;
            this.account = r2;
            this.gift_id = r3;
            this.onUpdate = r5;
        }

        private /* synthetic */ void lambda$load$0(TLObject r8, TL_stars.getResaleStarGifts r9) {
            this.reqId = -1;
            if ((r8 instanceof TL_stars.resaleStarGifts) == false) goto L57;
            TL_stars.resaleStarGifts r82 = (TL_stars.resaleStarGifts) r8;
            int r2 = 0;
            MessagesController.getInstance(this.account).putUsers(r82.users, false);
            MessagesController.getInstance(this.account).putChats(r82.chats, false);
            this.totalCount = r82.count;
            boolean r0 = true;
            if (TextUtils.isEmpty(r9.offset) == false) goto L7;
            this.gifts.clear();
            boolean r92 = true;
        L8:
            ArrayList<TL_stars.StarGift> r1 = r82.gifts;
            int r3 = r1.size();
            int r4 = 0;
        L9:
            if (r4 >= r3) goto L14;
            TL_stars.StarGift r5 = r1.get(r4);
            r4 = r4 + 1;
            TL_stars.StarGift r52 = r5;
            if ((r52 instanceof TL_stars.TL_starGiftUnique) == false) goto L9;
            this.gifts.add((TL_stars.TL_starGiftUnique) r52);
            goto L9
        L14:
            if (this.gifts.size() < this.totalCount) goto L16;
        L19:
            this.endReached = r0;
            this.last_offset = r82.next_offset;
            this.loading = false;
            ArrayList<TL_stars.StarGiftAttribute> r02 = r82.attributes;
            if (r02 == null) goto L25;
            if (r02.isEmpty() == true) goto L25;
            this.modelAttributes.clear();
            this.backdropAttributes.clear();
            this.patternAttributes.clear();
            this.modelAttributes.addAll(StarsController.findAttributes(r82.attributes, TL_stars.starGiftAttributeModel.class));
            this.backdropAttributes.addAll(StarsController.findAttributes(r82.attributes, TL_stars.starGiftAttributeBackdrop.class));
            this.patternAttributes.addAll(StarsController.findAttributes(r82.attributes, TL_stars.starGiftAttributePattern.class));
            this.attributes_hash = r82.attributes_hash;
        L25:
            if (r82.counters.isEmpty() == true) goto L37;
            this.backdropAttributesCounter.clear();
            this.patternAttributesCounter.clear();
            this.modelAttributesCounter.clear();
            ArrayList<TL_stars.starGiftAttributeCounter> r83 = r82.counters;
            int r03 = r83.size();
        L27:
            if (r2 >= r03) goto L37;
            TL_stars.starGiftAttributeCounter r12 = r83.get(r2);
            r2 = r2 + 1;
            TL_stars.starGiftAttributeCounter r13 = r12;
            TL_stars.StarGiftAttributeId r32 = r13.attribute;
            if ((r32 instanceof TL_stars.starGiftAttributeIdBackdrop) == true) goto L30;
            if ((r32 instanceof TL_stars.starGiftAttributeIdPattern) == true) goto L33;
            if ((r32 instanceof TL_stars.starGiftAttributeIdModel) == false) goto L27;
            this.modelAttributesCounter.put(Long.valueOf(r32.document_id), Integer.valueOf(r13.count));
            goto L27
        L33:
            this.patternAttributesCounter.put(Long.valueOf(r32.document_id), Integer.valueOf(r13.count));
            goto L27
        L30:
            this.backdropAttributesCounter.put(Integer.valueOf(r32.backdrop_id), Integer.valueOf(r13.count));
        L37:
            Utilities.Callback<Boolean> r7 = this.onUpdate;
            if (r7 == null) goto L58;
            r7.run(Boolean.valueOf(r92));
            return;
        L58:
            return;
        L16:
            if (TextUtils.isEmpty(r82.next_offset) == true) goto L19;
            r0 = false;
            goto L19
        L7:
            r92 = false;
            goto L8
        }

        private /* synthetic */ void lambda$load$1(TL_stars.getResaleStarGifts r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new ResaleGiftsFragment$ResaleGiftsList$$ExternalSyntheticLambda1(this, r2, r1));
        }

        public void cancel() {
            if (this.reqId < 0) goto L5;
            ConnectionsManager.getInstance(this.account).cancelRequest(this.reqId, true);
            this.reqId = -1;
        L5:
            this.loading = false;
        }

        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public int findGiftToUpgrade(int r1) {
            return -1;
        }

        public ResaleGiftsList forCraft() {
            this.for_craft = true;
            return this;
        }

        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public Object get(int r1) {
            return this.gifts.get(r1);
        }

        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public int getLoadedCount() {
            return this.gifts.size();
        }

        public Sorting getSorting() {
            return this.sorting;
        }

        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public int getTotalCount() {
            return this.totalCount;
        }

        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public int indexOf(Object r1) {
            return this.gifts.indexOf(r1);
        }

        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public void load() {
            load(false);
        }

        public void load(boolean r9) {
            if (this.loading == true) goto L74;
            if (r9 == false) goto L6;
        L8:
            this.loading = true;
            TL_stars.getResaleStarGifts r0 = new TL_stars.getResaleStarGifts();
            r0.gift_id = this.gift_id;
            String r1 = this.last_offset;
            if (r1 != null) goto L11;
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        L11:
            r0.offset = r1;
            r0.limit = 15;
            r0.for_craft = this.for_craft;
            r0.stars_only = this.starsOnly;
            Sorting r12 = this.sorting;
            int r3 = 0;
            if (r12 != Sorting.BY_NUMBER) goto L15;
            r0.sort_by_num = true;
            r0.sort_by_price = false;
        L20:
            long r13 = this.attributes_hash;
            if (r13 == 0) goto L24;
            r0.flags = 1 | r0.flags;
            r0.attributes_hash = r13;
        L31:
            if (this.notSelectedModelAttributes.isEmpty() == true) goto L33;
        L36:
            r0.flags |= 8;
            if (this.notSelectedModelAttributes.isEmpty() == true) goto L44;
            ArrayList<TL_stars.starGiftAttributeModel> r92 = this.modelAttributes;
            int r14 = r92.size();
            int r2 = 0;
        L39:
            if (r2 >= r14) goto L44;
            TL_stars.starGiftAttributeModel r4 = r92.get(r2);
            r2 = r2 + 1;
            TL_stars.starGiftAttributeModel r42 = r4;
            if (this.notSelectedModelAttributes.contains(Long.valueOf(r42.document.id)) == true) goto L39;
            TL_stars.starGiftAttributeIdModel r5 = new TL_stars.starGiftAttributeIdModel();
            r5.document_id = r42.document.id;
            r0.attributes.add(r5);
        L44:
            if (this.notSelectedBackdropAttributes.isEmpty() == true) goto L51;
            ArrayList<TL_stars.starGiftAttributeBackdrop> r93 = this.backdropAttributes;
            int r15 = r93.size();
            int r22 = 0;
        L46:
            if (r22 >= r15) goto L51;
            TL_stars.starGiftAttributeBackdrop r43 = r93.get(r22);
            r22 = r22 + 1;
            TL_stars.starGiftAttributeBackdrop r44 = r43;
            if (this.notSelectedBackdropAttributes.contains(Integer.valueOf(r44.backdrop_id)) == true) goto L46;
            TL_stars.starGiftAttributeIdBackdrop r52 = new TL_stars.starGiftAttributeIdBackdrop();
            r52.backdrop_id = r44.backdrop_id;
            r0.attributes.add(r52);
        L51:
            if (this.notSelectedPatternAttributes.isEmpty() == true) goto L57;
            ArrayList<TL_stars.starGiftAttributePattern> r94 = this.patternAttributes;
            int r16 = r94.size();
        L53:
            if (r3 >= r16) goto L57;
            TL_stars.starGiftAttributePattern r23 = r94.get(r3);
            r3 = r3 + 1;
            TL_stars.starGiftAttributePattern r24 = r23;
            if (this.notSelectedPatternAttributes.contains(Long.valueOf(r24.document.id)) == true) goto L53;
            TL_stars.starGiftAttributeIdPattern r45 = new TL_stars.starGiftAttributeIdPattern();
            r45.document_id = r24.document.id;
            r0.attributes.add(r45);
        L57:
            this.reqId = ConnectionsManager.getInstance(this.account).sendRequest(r0, new ResaleGiftsFragment$ResaleGiftsList$$ExternalSyntheticLambda0(this, r0));
            return;
        L33:
            if (this.notSelectedBackdropAttributes.isEmpty() == false) goto L36;
            if (this.notSelectedPatternAttributes.isEmpty() == true) goto L57;
        L24:
            if (this.modelAttributes.isEmpty() == false) goto L31;
            if (this.backdropAttributes.isEmpty() == false) goto L31;
            if (this.patternAttributes.isEmpty() == false) goto L31;
            r0.flags = 1 | r0.flags;
            r0.attributes_hash = 0;
            goto L31
        L15:
            if (r12 != Sorting.BY_DATE) goto L18;
            r0.sort_by_num = false;
            r0.sort_by_price = false;
            goto L20
        L18:
            if (r12 != Sorting.BY_PRICE) goto L20;
            r0.sort_by_num = false;
            r0.sort_by_price = true;
            goto L20
        L6:
            if (this.endReached == false) goto L8;
            return;
        }

        @Override // org.telegram.ui.Stars.StarsController.IGiftsList
        public void notifyUpdate() {
        }

        public void reload() {
            cancel();
            this.last_offset = null;
            this.gifts.clear();
            load(true);
            Utilities.Callback<Boolean> r1 = this.onUpdate;
            if (r1 == null) goto L6;
            r1.run(Boolean.TRUE);
            return;
        }

        public void setSorting(Sorting r2) {
            if (this.sorting == r2) goto L6;
            this.sorting = r2;
            reload();
            return;
        }
    }

    public static class SelectGiftSheet extends BottomSheetWithRecyclerListView {
        private StarGiftSheet.ActionView actionView;
        private UniversalAdapter adapter;
        private Filter backdropButton;
        private final String collectionName;
        private HorizontalScrollView filterScrollView;
        private LinearLayout filtersContainer;
        private boolean hadResaleGifts;
        private Filter modelButton;
        private Utilities.Callback<TL_stars.StarGift> onSelect;
        private Filter patternButton;
        private Filter sortButton;
        private final State state;
        private boolean willBeFirst;
        private HashSet<Long> without;

        /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$SelectGiftSheet$1, reason: invalid class name */
        public class AnonymousClass1 extends UniversalRecyclerView {
            final /* synthetic */ SelectGiftSheet this$0;

            public AnonymousClass1(SelectGiftSheet r1, Context r2, int r3, int r4, Utilities.Callback2 r5, Utilities.Callback5 r6, Utilities.Callback5Return r7, Theme.ResourcesProvider r8) {
                this.this$0 = r1;
                super(r2, r3, r4, r5, r6, r7, r8);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
            public void onMeasure(int r3, int r4) {
                super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(Math.min((int) (AndroidUtilities.displaySize.y * 0.35f), View.MeasureSpec.getSize(r4)), View.MeasureSpec.getMode(r4)));
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$SelectGiftSheet$2, reason: invalid class name */
        public class AnonymousClass2 implements TextWatcher {
            final /* synthetic */ SelectGiftSheet this$0;
            final /* synthetic */ UniversalRecyclerView val$listView;
            final /* synthetic */ String[] val$query;

            public AnonymousClass2(SelectGiftSheet r1, String[] r2, UniversalRecyclerView r3) {
                this.this$0 = r1;
                this.val$query = r2;
                this.val$listView = r3;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r3) {
                this.val$query[0] = r3.toString();
                this.val$listView.adapter.update(true);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$SelectGiftSheet$3, reason: invalid class name */
        public class AnonymousClass3 extends UniversalRecyclerView {
            final /* synthetic */ SelectGiftSheet this$0;

            public AnonymousClass3(SelectGiftSheet r1, Context r2, int r3, int r4, Utilities.Callback2 r5, Utilities.Callback5 r6, Utilities.Callback5Return r7, Theme.ResourcesProvider r8) {
                this.this$0 = r1;
                super(r2, r3, r4, r5, r6, r7, r8);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
            public void onMeasure(int r3, int r4) {
                super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(Math.min((int) (AndroidUtilities.displaySize.y * 0.35f), View.MeasureSpec.getSize(r4)), View.MeasureSpec.getMode(r4)));
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$SelectGiftSheet$4, reason: invalid class name */
        public class AnonymousClass4 implements TextWatcher {
            final /* synthetic */ SelectGiftSheet this$0;
            final /* synthetic */ UniversalRecyclerView val$listView;
            final /* synthetic */ String[] val$query;

            public AnonymousClass4(SelectGiftSheet r1, String[] r2, UniversalRecyclerView r3) {
                this.this$0 = r1;
                this.val$query = r2;
                this.val$listView = r3;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r3) {
                this.val$query[0] = r3.toString();
                this.val$listView.adapter.update(true);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$SelectGiftSheet$5, reason: invalid class name */
        public class AnonymousClass5 extends UniversalRecyclerView {
            final /* synthetic */ SelectGiftSheet this$0;

            public AnonymousClass5(SelectGiftSheet r1, Context r2, int r3, int r4, Utilities.Callback2 r5, Utilities.Callback5 r6, Utilities.Callback5Return r7, Theme.ResourcesProvider r8) {
                this.this$0 = r1;
                super(r2, r3, r4, r5, r6, r7, r8);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
            public void onMeasure(int r3, int r4) {
                super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(Math.min((int) (AndroidUtilities.displaySize.y * 0.35f), View.MeasureSpec.getSize(r4)), View.MeasureSpec.getMode(r4)));
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$SelectGiftSheet$6, reason: invalid class name */
        public class AnonymousClass6 implements TextWatcher {
            final /* synthetic */ SelectGiftSheet this$0;
            final /* synthetic */ UniversalRecyclerView val$listView;
            final /* synthetic */ String[] val$query;

            public AnonymousClass6(SelectGiftSheet r1, String[] r2, UniversalRecyclerView r3) {
                this.this$0 = r1;
                this.val$query = r2;
                this.val$listView = r3;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r3) {
                this.val$query[0] = r3.toString();
                this.val$listView.adapter.update(true);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$SelectGiftSheet$7, reason: invalid class name */
        public class AnonymousClass7 extends GridLayoutManager.SpanSizeLookup {
            final /* synthetic */ SelectGiftSheet this$0;

            public AnonymousClass7(SelectGiftSheet r1) {
                this.this$0 = r1;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int r1) {
                UItem r0 = SelectGiftSheet.m14993$$Nest$fgetadapter(this.this$0).getItem(r1 - 1);
                if (r0 == null) goto L8;
                int r02 = r0.spanCount;
                if (r02 == (-1)) goto L10;
                return r02;
            L10:
                return 3;
            L8:
                return 3;
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$SelectGiftSheet$8, reason: invalid class name */
        public class AnonymousClass8 extends RecyclerView.OnScrollListener {
            final /* synthetic */ SelectGiftSheet this$0;

            public AnonymousClass8(SelectGiftSheet r1) {
                this.this$0 = r1;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r1, int r2, int r3) {
                SelectGiftSheet.m14994$$Nest$monScroll(this.this$0);
            }
        }

        /* renamed from: org.telegram.ui.Gifts.ResaleGiftsFragment$SelectGiftSheet$9, reason: invalid class name */
        public class AnonymousClass9 extends UniversalAdapter {
            final /* synthetic */ SelectGiftSheet this$0;

            public AnonymousClass9(SelectGiftSheet r1, RecyclerListView r2, Context r3, int r4, int r5, Utilities.Callback2 r6, Theme.ResourcesProvider r7) {
                this.this$0 = r1;
                super(r2, r3, r4, r5, r6, r7);
            }

            @Override // org.telegram.ui.Components.UniversalAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r11, int r12) {
                if (r12 != 0) goto L6;
                return new RecyclerListView.Holder(new HeaderCell(this.this$0.getContext(), Theme.key_windowBackgroundWhiteBlueHeader, 13, 12, 4, false, SelectGiftSheet.access$400(this.this$0)));
            L6:
                if (r12 != 42) goto L10;
                return new RecyclerListView.Holder(new HeaderCell(this.this$0.getContext(), Theme.key_windowBackgroundWhiteBlueHeader, 13, 12, 4, false, true, SelectGiftSheet.access$500(this.this$0)));
            L10:
                return super.onCreateViewHolder(r11, r12);
            }
        }

        public static class State implements NotificationCenter.NotificationCenterDelegate {
            private boolean attached;
            public final int currentAccount;
            private Runnable currentListener;
            public final long giftId;
            private final StarsController.GiftsList list;
            private final ResaleGiftsList resaleList;

            public static /* synthetic */ void $r8$lambda$FTgwS8AETbJLkPsyJRkeJ6tSQbA(State r0, boolean r1) {
                r0.update(r1);
            }

            /* renamed from: -$$Nest$fgetlist, reason: not valid java name */
            public static /* bridge */ /* synthetic */ StarsController.GiftsList m14995$$Nest$fgetlist(State r0) {
                return r0.list;
            }

            /* renamed from: -$$Nest$fgetresaleList, reason: not valid java name */
            public static /* bridge */ /* synthetic */ ResaleGiftsList m14996$$Nest$fgetresaleList(State r0) {
                return r0.resaleList;
            }

            public State(int r5, long r6) {
                this.currentAccount = r5;
                this.giftId = r6;
                StarsController.GiftsList r0 = new StarsController.GiftsList(r5, 0, false);
                this.list = r0;
                r0.forCrafting(r6);
                this.resaleList = new ResaleGiftsList(r5, r6, new ResaleGiftsFragment$SelectGiftSheet$State$$ExternalSyntheticLambda0(this)).forCraft();
            }

            private void update(boolean r1) {
                Runnable r0 = this.currentListener;
                if (r0 == null) goto L6;
                r0.run();
                return;
            }

            public void attach() {
                if (this.attached == false) goto L5;
                return;
            L5:
                NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starUserGiftsLoaded);
                this.list.load();
                this.resaleList.load();
                this.attached = true;
            }

            public void detach() {
                if (this.attached == true) goto L5;
                return;
            L5:
                NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starUserGiftsLoaded);
                this.list.cancel();
                this.resaleList.cancel();
                this.attached = false;
            }

            @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
            public void didReceivedNotification(int r1, int r2, Object... r3) {
                if (r1 == NotificationCenter.starUserGiftsLoaded) goto L5;
                return;
            L5:
                if (r3[1] != this.list) goto L9;
                update(true);
                return;
            }

            public void listen(Runnable r1) {
                this.currentListener = r1;
            }
        }

        public static /* synthetic */ void $r8$lambda$0s38_injprGWQ2ZZ_sjURoz9hi8(State r1) {
            if (State.m14996$$Nest$fgetresaleList(r1).notSelectedPatternAttributes.isEmpty() == false) goto L5;
            return;
        L5:
            State.m14996$$Nest$fgetresaleList(r1).notSelectedPatternAttributes.clear();
            State.m14996$$Nest$fgetresaleList(r1).reload();
        }

        public static /* synthetic */ void $r8$lambda$3EswReWmd19lSpBIN9ogjK4WTRA(State r4, ItemOptions r5, UItem r6, View r7, Integer r8, Float r9, Float r10) {
            long r62 = ((TL_stars.starGiftAttributePattern) r6.object).document.id;
            if (State.m14996$$Nest$fgetresaleList(r4).notSelectedPatternAttributes.contains(Long.valueOf(r62)) == false) goto L5;
            State.m14996$$Nest$fgetresaleList(r4).notSelectedPatternAttributes.remove(Long.valueOf(r62));
        L13:
            State.m14996$$Nest$fgetresaleList(r4).reload();
            r5.dismiss();
            return;
        L5:
            if (State.m14996$$Nest$fgetresaleList(r4).notSelectedPatternAttributes.isEmpty() == false) goto L11;
            ArrayList<TL_stars.starGiftAttributePattern> r82 = State.m14996$$Nest$fgetresaleList(r4).patternAttributes;
            int r92 = r82.size();
            int r102 = 0;
        L7:
            if (r102 >= r92) goto L13;
            TL_stars.starGiftAttributePattern r0 = r82.get(r102);
            r102 = r102 + 1;
            TL_stars.starGiftAttributePattern r02 = r0;
            if (r02.document.id == r62) goto L7;
            State.m14996$$Nest$fgetresaleList(r4).notSelectedPatternAttributes.add(Long.valueOf(r02.document.id));
            goto L7
        L11:
            State.m14996$$Nest$fgetresaleList(r4).notSelectedPatternAttributes.add(Long.valueOf(r62));
            goto L13
        }

        /* renamed from: $r8$lambda$3_rEKninReNo61CL9UFz-UiQ8uQ, reason: not valid java name */
        public static /* synthetic */ void m14982$r8$lambda$3_rEKninReNo61CL9UFzUiQ8uQ(SelectGiftSheet r0) {
            r0.lambda$new$24();
        }

        public static /* synthetic */ void $r8$lambda$4zUsK7IE39TWmjo4NaJbrkFMSdk(State r4, ItemOptions r5, UItem r6, View r7, Integer r8, Float r9, Float r10) {
            long r62 = ((TL_stars.starGiftAttributeModel) r6.object).document.id;
            if (State.m14996$$Nest$fgetresaleList(r4).notSelectedModelAttributes.contains(Long.valueOf(r62)) == false) goto L5;
            State.m14996$$Nest$fgetresaleList(r4).notSelectedModelAttributes.remove(Long.valueOf(r62));
        L13:
            State.m14996$$Nest$fgetresaleList(r4).reload();
            r5.dismiss();
            return;
        L5:
            if (State.m14996$$Nest$fgetresaleList(r4).notSelectedModelAttributes.isEmpty() == false) goto L11;
            ArrayList<TL_stars.starGiftAttributeModel> r82 = State.m14996$$Nest$fgetresaleList(r4).modelAttributes;
            int r92 = r82.size();
            int r102 = 0;
        L7:
            if (r102 >= r92) goto L13;
            TL_stars.starGiftAttributeModel r0 = r82.get(r102);
            r102 = r102 + 1;
            TL_stars.starGiftAttributeModel r02 = r0;
            if (r02.document.id == r62) goto L7;
            State.m14996$$Nest$fgetresaleList(r4).notSelectedModelAttributes.add(Long.valueOf(r02.document.id));
            goto L7
        L11:
            State.m14996$$Nest$fgetresaleList(r4).notSelectedModelAttributes.add(Long.valueOf(r62));
            goto L13
        }

        public static /* synthetic */ void $r8$lambda$5mImhFIxfSOgZXmji0Ta_3PpzWo(SelectGiftSheet r0, State r1, View r2) {
            r0.lambda$new$3(r1, r2);
        }

        /* renamed from: $r8$lambda$7e_21KuIzds4YOe-fFeh4DlN2r4, reason: not valid java name */
        public static /* synthetic */ void m14983$r8$lambda$7e_21KuIzds4YOefFeh4DlN2r4(SelectGiftSheet r0, Browser.Progress r1, TL_stars.TL_starGiftUnique r2, Boolean r3, String r4) {
            r0.lambda$buyGift$25(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$9qQ0qNQLQGuU9Xb-oPkZn2ykjtA, reason: not valid java name */
        public static /* synthetic */ void m14984$r8$lambda$9qQ0qNQLQGuU9XboPkZn2ykjtA(SelectGiftSheet r0, State r1, View r2, int r3, float r4, float r5) {
            r0.lambda$new$22(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$BIAzuAS59rnUs4yLFwLF3Asds7k(SelectGiftSheet r0, State r1, Context r2, View r3) {
            r0.lambda$new$9(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$JGP3VTxFSY5ZesaZD3GWvNGw370(SelectGiftSheet r0, AlertDialog r1, AmountUtils$Currency r2, TL_stars.TL_starGiftUnique r3, long r4, TLRPC.TL_payments_paymentFormStarGift r6) {
            r0.lambda$buyGift$27(r1, r2, r3, r4, r6);
        }

        /* renamed from: $r8$lambda$LL-e_j3FNzfJQk9PFnQJUW5z-Uc, reason: not valid java name */
        public static /* synthetic */ int m14985$r8$lambda$LLe_j3FNzfJQk9PFnQJUW5zUc(State r3, TL_stars.starGiftAttributePattern r4, TL_stars.starGiftAttributePattern r5) {
            Integer r42 = State.m14996$$Nest$fgetresaleList(r3).patternAttributesCounter.get(Long.valueOf(r4.document.id));
            Integer r32 = State.m14996$$Nest$fgetresaleList(r3).patternAttributesCounter.get(Long.valueOf(r5.document.id));
            if (r42 != null) goto L6;
            return 1;
        L6:
            if (r32 != null) goto L10;
            return -1;
        L10:
            return r32.intValue() - r42.intValue();
        }

        /* renamed from: $r8$lambda$NuNOn8c9I0m-ZIx6PG-m5NVujes, reason: not valid java name */
        public static /* synthetic */ void m14986$r8$lambda$NuNOn8c9I0mZIx6PGm5NVujes(String[] r10, State r11, ArrayList r12, ArrayList r13, UniversalAdapter r14) {
            String r102 = r10[0].toLowerCase();
            String r0 = AndroidUtilities.translitSafe(r102);
            boolean r1 = State.m14996$$Nest$fgetresaleList(r11).notSelectedModelAttributes.isEmpty();
            int r2 = r12.size();
            int r3 = 0;
        L3:
            if (r3 >= r2) goto L26;
            Object r4 = r12.get(r3);
            r3 = r3 + 1;
            TL_stars.starGiftAttributeModel r42 = (TL_stars.starGiftAttributeModel) r4;
            boolean r5 = State.m14996$$Nest$fgetresaleList(r11).notSelectedModelAttributes.contains(Long.valueOf(r42.document.id));
            boolean r6 = !r5;
            if (TextUtils.isEmpty(r102) == true) goto L14;
            if (r42.name.toLowerCase().startsWith(r102) == true) goto L14;
            if (r42.name.toLowerCase().startsWith(r0) == true) goto L14;
            if (r42.name.toLowerCase().contains(" " + r102) == true) goto L14;
            if (r42.name.toLowerCase().contains(" " + r0) == false) goto L3;
        L14:
            Integer r7 = State.m14996$$Nest$fgetresaleList(r11).modelAttributesCounter.get(Long.valueOf(r42.document.id));
            if (r7 != null) goto L17;
            int r72 = 0;
        L18:
            UItem r43 = ModelItem.Factory.asModel(r42, r72, r102);
            if (TextUtils.isEmpty(r102) == true) goto L24;
            if (r1 == true) goto L23;
            if (r5 == true) goto L23;
            r6 = true;
        L23:
            r6 = false;
        L24:
            r13.add(r43.setChecked(r6));
            goto L3
        L17:
            r72 = r7.intValue();
            goto L18
        L26:
            if (r13.isEmpty() == false) goto L38;
            r13.add(EmptyView.Factory.asEmptyView(LocaleController.getString(R.string.Gift2ResaleFiltersModelEmpty)));
            return;
        }

        public static /* synthetic */ void $r8$lambda$QyaOa0UQfYVTgK8VN1C1GXU7Qlc(State r1) {
            if (State.m14996$$Nest$fgetresaleList(r1).notSelectedBackdropAttributes.isEmpty() == false) goto L5;
            return;
        L5:
            State.m14996$$Nest$fgetresaleList(r1).notSelectedBackdropAttributes.clear();
            State.m14996$$Nest$fgetresaleList(r1).reload();
        }

        public static /* synthetic */ void $r8$lambda$YLp_qyAEKaAElBpPL_JtSPU9wPo(SelectGiftSheet r0, ArrayList r1, UniversalAdapter r2) {
            r0.fillItems(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$_WSg2DXnHUikP99NhITT6thWTZk(String[] r10, State r11, ArrayList r12, ArrayList r13, UniversalAdapter r14) {
            String r102 = r10[0].toLowerCase();
            String r0 = AndroidUtilities.translitSafe(r102);
            boolean r1 = State.m14996$$Nest$fgetresaleList(r11).notSelectedPatternAttributes.isEmpty();
            int r2 = r12.size();
            int r3 = 0;
        L3:
            if (r3 >= r2) goto L26;
            Object r4 = r12.get(r3);
            r3 = r3 + 1;
            TL_stars.starGiftAttributePattern r42 = (TL_stars.starGiftAttributePattern) r4;
            boolean r5 = State.m14996$$Nest$fgetresaleList(r11).notSelectedPatternAttributes.contains(Long.valueOf(r42.document.id));
            boolean r6 = !r5;
            if (TextUtils.isEmpty(r102) == true) goto L14;
            if (r42.name.toLowerCase().startsWith(r102) == true) goto L14;
            if (r42.name.toLowerCase().startsWith(r0) == true) goto L14;
            if (r42.name.toLowerCase().contains(" " + r102) == true) goto L14;
            if (r42.name.toLowerCase().contains(" " + r0) == false) goto L3;
        L14:
            Integer r7 = State.m14996$$Nest$fgetresaleList(r11).patternAttributesCounter.get(Long.valueOf(r42.document.id));
            if (r7 != null) goto L17;
            int r72 = 0;
        L18:
            UItem r43 = PatternItem.Factory.asPattern(r42, r72, r102);
            if (TextUtils.isEmpty(r102) == true) goto L24;
            if (r1 == true) goto L23;
            if (r5 == true) goto L23;
            r6 = true;
        L23:
            r6 = false;
        L24:
            r13.add(r43.setChecked(r6));
            goto L3
        L17:
            r72 = r7.intValue();
            goto L18
        L26:
            if (r13.isEmpty() == false) goto L38;
            r13.add(EmptyView.Factory.asEmptyView(LocaleController.getString(R.string.Gift2ResaleFiltersSymbolEmpty)));
            return;
        }

        public static /* synthetic */ void $r8$lambda$djdi_mTAZUHbxCJ5zfbvYZwyVIU(ItemOptions r0) {
            ActionBarPopupWindow r02 = r0.actionBarPopupWindow;
            if (r02 == null) goto L6;
            AndroidUtilities.hideKeyboard(r02.getContentView());
            return;
        }

        public static /* synthetic */ void $r8$lambda$e0wxnRi6X5dMR5a7g0VV6uMCcIA(SelectGiftSheet r0, State r1, Context r2, View r3) {
            r0.lambda$new$21(r1, r2, r3);
        }

        public static /* synthetic */ Integer $r8$lambda$hvepRCM1oIxSlAU8elHLQG_OelQ(Integer r0) {
            return 0;
        }

        public static /* synthetic */ void $r8$lambda$jURQLAe3phVMInwDSXtL1wof3rI(State r1) {
            State.m14996$$Nest$fgetresaleList(r1).setSorting(ResaleGiftsList.Sorting.BY_PRICE);
        }

        public static /* synthetic */ void $r8$lambda$n8Cl92jiJChXSGAPBXgMh4pfiQY(SelectGiftSheet r0, TL_stars.TL_starGiftUnique r1, long r2, StarGiftSheet.PaymentFormState r4, Browser.Progress r5) {
            r0.lambda$buyGift$26(r1, r2, r4, r5);
        }

        /* renamed from: $r8$lambda$nm8A9QTA0pY-rnulXwF0nqEgaD8, reason: not valid java name */
        public static /* synthetic */ void m14987$r8$lambda$nm8A9QTA0pYrnulXwF0nqEgaD8(State r1) {
            State.m14996$$Nest$fgetresaleList(r1).setSorting(ResaleGiftsList.Sorting.BY_NUMBER);
        }

        public static /* synthetic */ int $r8$lambda$oUj5czK9SfWwZGzQYnwGVC91nKk(State r1, TL_stars.starGiftAttributeBackdrop r2, TL_stars.starGiftAttributeBackdrop r3) {
            Integer r22 = State.m14996$$Nest$fgetresaleList(r1).backdropAttributesCounter.get(Integer.valueOf(r2.backdrop_id));
            Integer r12 = State.m14996$$Nest$fgetresaleList(r1).backdropAttributesCounter.get(Integer.valueOf(r3.backdrop_id));
            if (r22 != null) goto L6;
            return 1;
        L6:
            if (r12 != null) goto L10;
            return -1;
        L10:
            return r12.intValue() - r22.intValue();
        }

        public static /* synthetic */ void $r8$lambda$pMd9R_SdftmoEPV9Hmt6GmhkqW8(String[] r10, State r11, ArrayList r12, ArrayList r13, UniversalAdapter r14) {
            String r102 = r10[0].toLowerCase();
            String r0 = AndroidUtilities.translitSafe(r102);
            boolean r1 = State.m14996$$Nest$fgetresaleList(r11).notSelectedBackdropAttributes.isEmpty();
            int r2 = r12.size();
            int r3 = 0;
        L3:
            if (r3 >= r2) goto L26;
            Object r4 = r12.get(r3);
            r3 = r3 + 1;
            TL_stars.starGiftAttributeBackdrop r42 = (TL_stars.starGiftAttributeBackdrop) r4;
            boolean r5 = State.m14996$$Nest$fgetresaleList(r11).notSelectedBackdropAttributes.contains(Integer.valueOf(r42.backdrop_id));
            boolean r6 = !r5;
            if (TextUtils.isEmpty(r102) == true) goto L14;
            if (r42.name.toLowerCase().startsWith(r102) == true) goto L14;
            if (r42.name.toLowerCase().startsWith(r0) == true) goto L14;
            if (r42.name.toLowerCase().contains(" " + r102) == true) goto L14;
            if (r42.name.toLowerCase().contains(" " + r0) == false) goto L3;
        L14:
            Integer r7 = State.m14996$$Nest$fgetresaleList(r11).backdropAttributesCounter.get(Integer.valueOf(r42.backdrop_id));
            if (r7 != null) goto L17;
            int r72 = 0;
        L18:
            UItem r43 = BackdropItem.Factory.asBackdrop(r42, r72, r102);
            if (TextUtils.isEmpty(r102) == true) goto L24;
            if (r1 == true) goto L23;
            if (r5 == true) goto L23;
            r6 = true;
        L23:
            r6 = false;
        L24:
            r13.add(r43.setChecked(r6));
            goto L3
        L17:
            r72 = r7.intValue();
            goto L18
        L26:
            if (r13.isEmpty() == false) goto L38;
            r13.add(EmptyView.Factory.asEmptyView(LocaleController.getString(R.string.Gift2ResaleFiltersBackdropEmpty)));
            return;
        }

        /* renamed from: $r8$lambda$qn1VK4C-6vWIgv_OkTc9loxTwE8, reason: not valid java name */
        public static /* synthetic */ void m14988$r8$lambda$qn1VK4C6vWIgv_OkTc9loxTwE8(State r1) {
            if (State.m14996$$Nest$fgetresaleList(r1).notSelectedModelAttributes.isEmpty() == false) goto L5;
            return;
        L5:
            State.m14996$$Nest$fgetresaleList(r1).notSelectedModelAttributes.clear();
            State.m14996$$Nest$fgetresaleList(r1).reload();
        }

        /* renamed from: $r8$lambda$qw_h22c-b6W0FWq8tjqvdNdqP94, reason: not valid java name */
        public static /* synthetic */ void m14989$r8$lambda$qw_h22cb6W0FWq8tjqvdNdqP94(SelectGiftSheet r0, State r1, Context r2, View r3) {
            r0.lambda$new$15(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$rKw4xUT3aiIjvhzvC9_FnWHqSvM(ItemOptions r0) {
            ActionBarPopupWindow r02 = r0.actionBarPopupWindow;
            if (r02 == null) goto L6;
            AndroidUtilities.hideKeyboard(r02.getContentView());
            return;
        }

        public static /* synthetic */ void $r8$lambda$rljTWKi1ta0olinV1UVkQpEFDxU(State r1) {
            State.m14996$$Nest$fgetresaleList(r1).setSorting(ResaleGiftsList.Sorting.BY_DATE);
        }

        /* renamed from: $r8$lambda$sn7TqtNUbV-qBdAJIr3uNpOA5Lc, reason: not valid java name */
        public static /* synthetic */ int m14990$r8$lambda$sn7TqtNUbVqBdAJIr3uNpOA5Lc(State r3, TL_stars.starGiftAttributeModel r4, TL_stars.starGiftAttributeModel r5) {
            Integer r42 = State.m14996$$Nest$fgetresaleList(r3).modelAttributesCounter.get(Long.valueOf(r4.document.id));
            Integer r32 = State.m14996$$Nest$fgetresaleList(r3).modelAttributesCounter.get(Long.valueOf(r5.document.id));
            if (r42 != null) goto L6;
            return 1;
        L6:
            if (r32 != null) goto L10;
            return -1;
        L10:
            return r32.intValue() - r42.intValue();
        }

        /* renamed from: $r8$lambda$v-vs1yz87O9ZsTYBNb8sIhiSnlk, reason: not valid java name */
        public static /* synthetic */ void m14991$r8$lambda$vvs1yz87O9ZsTYBNb8sIhiSnlk(ItemOptions r0) {
            ActionBarPopupWindow r02 = r0.actionBarPopupWindow;
            if (r02 == null) goto L6;
            AndroidUtilities.hideKeyboard(r02.getContentView());
            return;
        }

        public static /* synthetic */ void $r8$lambda$xRb_bKj58eZnF1PRB92b_PtnTis(State r1, ItemOptions r2, UItem r3, View r4, Integer r5, Float r6, Float r7) {
            int r32 = ((TL_stars.starGiftAttributeBackdrop) r3.object).backdrop_id;
            if (State.m14996$$Nest$fgetresaleList(r1).notSelectedBackdropAttributes.contains(Integer.valueOf(r32)) == false) goto L5;
            State.m14996$$Nest$fgetresaleList(r1).notSelectedBackdropAttributes.remove(Integer.valueOf(r32));
        L13:
            State.m14996$$Nest$fgetresaleList(r1).reload();
            r2.dismiss();
            return;
        L5:
            if (State.m14996$$Nest$fgetresaleList(r1).notSelectedBackdropAttributes.isEmpty() == false) goto L11;
            ArrayList<TL_stars.starGiftAttributeBackdrop> r42 = State.m14996$$Nest$fgetresaleList(r1).backdropAttributes;
            int r52 = r42.size();
            int r62 = 0;
        L7:
            if (r62 >= r52) goto L13;
            TL_stars.starGiftAttributeBackdrop r72 = r42.get(r62);
            r62 = r62 + 1;
            TL_stars.starGiftAttributeBackdrop r73 = r72;
            if (r73.backdrop_id == r32) goto L7;
            State.m14996$$Nest$fgetresaleList(r1).notSelectedBackdropAttributes.add(Integer.valueOf(r73.backdrop_id));
            goto L7
        L11:
            State.m14996$$Nest$fgetresaleList(r1).notSelectedBackdropAttributes.add(Integer.valueOf(r32));
            goto L13
        }

        /* renamed from: $r8$lambda$xfgwUtnzAr-tIyOX6hKfNdG7-p0, reason: not valid java name */
        public static /* synthetic */ void m14992$r8$lambda$xfgwUtnzArtIyOX6hKfNdG7p0(SelectGiftSheet r0) {
            r0.onScroll();
        }

        /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ UniversalAdapter m14993$$Nest$fgetadapter(SelectGiftSheet r0) {
            return r0.adapter;
        }

        /* renamed from: -$$Nest$monScroll, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14994$$Nest$monScroll(SelectGiftSheet r0) {
            r0.onScroll();
        }

        public SelectGiftSheet(Context r10, String r11, State r12) {
            super(r10, null, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, null);
            this.without = new HashSet();
            this.headerMoveTop = AndroidUtilities.dp(12.0f);
            fixNavigationBar();
            this.collectionName = r11;
            this.state = r12;
            this.actionBar.setTitle(getTitle());
            LinearLayout r9 = new LinearLayout(r10);
            this.filtersContainer = r9;
            r9.setPadding(AndroidUtilities.dp(11.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(6.0f));
            this.filtersContainer.setOrientation(0);
            this.filtersContainer.setClipChildren(false);
            this.filtersContainer.setClipToPadding(false);
            HorizontalScrollView r92 = new HorizontalScrollView(r10);
            this.filterScrollView = r92;
            r92.setHorizontalScrollBarEnabled(false);
            this.filterScrollView.setClipChildren(false);
            this.filterScrollView.setClipToPadding(false);
            this.filterScrollView.addView(this.filtersContainer);
            Filter r93 = new Filter(r10, this.resourcesProvider);
            this.sortButton = r93;
            r93.setSorting(State.m14996$$Nest$fgetresaleList(r12).getSorting());
            this.filtersContainer.addView(this.sortButton, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 6, 0));
            this.sortButton.setOnClickListener(new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda2(this, r12));
            Filter r94 = new Filter(r10, this.resourcesProvider);
            this.modelButton = r94;
            r94.setValue(LocaleController.getString(R.string.Gift2AttributeModel));
            this.filtersContainer.addView(this.modelButton, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 6, 0));
            this.modelButton.setOnClickListener(new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda3(this, r12, r10));
            Filter r95 = new Filter(r10, this.resourcesProvider);
            this.backdropButton = r95;
            r95.setValue(LocaleController.getString(R.string.Gift2AttributeBackdrop));
            this.filtersContainer.addView(this.backdropButton, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 6, 0));
            this.backdropButton.setOnClickListener(new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda4(this, r12, r10));
            Filter r96 = new Filter(r10, this.resourcesProvider);
            this.patternButton = r96;
            r96.setValue(LocaleController.getString(R.string.Gift2AttributeSymbol));
            this.filtersContainer.addView(this.patternButton, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 0, 0));
            this.patternButton.setOnClickListener(new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda5(this, r12, r10));
            GridLayoutManager r97 = new GridLayoutManager(getContext(), 3);
            r97.setSpanSizeLookup(new AnonymousClass7(this));
            this.recyclerListView.setLayoutManager(r97);
            this.recyclerListView.setOnItemClickListener(new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda6(this, r12));
            this.recyclerListView.setPadding(this.backgroundPaddingLeft + AndroidUtilities.dp(8.0f), 0, this.backgroundPaddingLeft + AndroidUtilities.dp(8.0f), 0);
            this.recyclerListView.setOnScrollListener(new AnonymousClass8(this));
            DefaultItemAnimator r98 = new DefaultItemAnimator();
            r98.setSupportsChangeAnimations(false);
            r98.setDelayAnimations(false);
            r98.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r98.setDurations(350);
            this.recyclerListView.setItemAnimator(r98);
            this.recyclerListView.setItemSelectorColorProvider(new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda7());
            StarGiftSheet.ActionView r99 = new StarGiftSheet.ActionView(r10);
            this.actionView = r99;
            r99.setPadding(AndroidUtilities.dp(20.0f), AndroidUtilities.dp(9.0f));
            this.actionView.setRoundRadius(AndroidUtilities.dp(22.0f));
            this.actionView.setFullRect(true);
            this.actionView.prepareBlur(null);
            this.actionView.setPivotY(0.0f);
            this.container.addView(this.actionView, LayoutHelper.createFrame(-1, -2, 55));
            this.adapter.update(false);
            r12.listen(new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda8(this));
        }

        public static /* synthetic */ Theme.ResourcesProvider access$400(SelectGiftSheet r0) {
            return r0.resourcesProvider;
        }

        public static /* synthetic */ Theme.ResourcesProvider access$500(SelectGiftSheet r0) {
            return r0.resourcesProvider;
        }

        private void buyGift(TL_stars.TL_starGiftUnique r9) {
            AlertDialog r2 = new AlertDialog(getContext(), 3);
            r2.showDelayed(400);
            long r5 = UserConfig.getInstance(this.currentAccount).getClientUserId();
            if (r9.resale_ton_only == false) goto L6;
            AmountUtils$Currency r0 = AmountUtils$Currency.TON;
        L5:
            AmountUtils$Currency r3 = r0;
            StarsController.getInstance(this.currentAccount, r3).getResellingGiftForm(r9, r5, new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda28(this, r2, r3, r9, r5));
            return;
        L6:
            r0 = AmountUtils$Currency.STARS;
            goto L5
        }

        private void fillItems(ArrayList<UItem> r19, UniversalAdapter r20) {
            State r2 = this.state;
            if (r2 != null) goto L5;
            return;
        L5:
            if (State.m14995$$Nest$fgetlist(r2) != null) goto L7;
            return;
        L7:
            if (State.m14996$$Nest$fgetresaleList(this.state) == null) goto L56;
            int r22 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
            r19.add(UItem.asHeader(-1, LocaleController.getString(R.string.GiftCraftSelectYour)));
            ArrayList<TL_stars.SavedStarGift> r3 = State.m14995$$Nest$fgetlist(this.state).gifts;
            int r4 = r3.size();
            int r5 = 0;
            int r8 = 0;
            int r9 = 0;
            boolean r7 = true;
        L10:
            if (r9 >= r4) goto L20;
            TL_stars.SavedStarGift r10 = r3.get(r9);
            r9 = r9 + 1;
            TL_stars.SavedStarGift r102 = r10;
            if (this.without.contains(Long.valueOf(r102.gift.id)) == true) goto L10;
            if (r102.can_craft_at > r22) goto L17;
            boolean r72 = true;
        L18:
            r19.add(GiftSheet.GiftCell.Factory.asStarGift(0, r102.gift, false, true, false, false, true).setEnabled(r72));
            r8 = r8 + 1;
            r7 = false;
            goto L10
        L17:
            r72 = false;
            goto L18
        L20:
            if (State.m14995$$Nest$fgetlist(this.state).loading == false) goto L22;
        L26:
            int r82 = r8 % 3;
            int r23 = 6 - r82;
            int r42 = 0;
        L27:
            if (r42 >= r23) goto L30;
            r19.add(UItem.asFlicker((r42 - r82) + 1, 35).setSpanCount(1));
            r42 = r42 + 1;
        L30:
            if (State.m14996$$Nest$fgetresaleList(this.state).getTotalCount() <= 0) goto L32;
        L33:
            this.hadResaleGifts = true;
            r19.add(UItem.asAnimatedHeader(-2, LocaleController.getString(R.string.GiftCraftSelectResale)));
            HorizontalScrollView r24 = this.filterScrollView;
            if (r24 == null) goto L36;
            r19.add(UItem.asCustom(-3, r24));
        L36:
            ArrayList<TL_stars.TL_starGiftUnique> r25 = State.m14996$$Nest$fgetresaleList(this.state).gifts;
            int r43 = r25.size();
        L37:
            if (r5 >= r43) goto L40;
            TL_stars.TL_starGiftUnique r73 = r25.get(r5);
            r5 = r5 + 1;
            r19.add(GiftSheet.GiftCell.Factory.asStarGift(0, r73, false, true, false, true, true));
            goto L37
        L40:
            if (State.m14996$$Nest$fgetresaleList(this.state).loading == false) goto L42;
        L43:
            r19.add(UItem.asFlicker(10, 35).setSpanCount(1));
            r19.add(UItem.asFlicker(11, 35).setSpanCount(1));
            r19.add(UItem.asFlicker(12, 35).setSpanCount(1));
            r19.add(UItem.asFlicker(13, 35).setSpanCount(1));
            r19.add(UItem.asFlicker(14, 35).setSpanCount(1));
            r19.add(UItem.asFlicker(15, 35).setSpanCount(1));
            return;
        L42:
            if (State.m14996$$Nest$fgetresaleList(this.state).endReached == false) goto L43;
            return;
        L32:
            if (this.hadResaleGifts == true) goto L33;
            return;
        L22:
            if (State.m14995$$Nest$fgetlist(this.state).endReached == false) goto L26;
            if (r7 == false) goto L30;
            r19.add(UItem.asCenterShadow(LocaleController.getString(R.string.GiftCraftSelectYourEmpty)));
            goto L30
        }

        private /* synthetic */ void lambda$buyGift$25(Browser.Progress r1, TL_stars.TL_starGiftUnique r2, Boolean r3, String r4) {
            r1.end();
            if (r3.booleanValue() == false) goto L9;
            Utilities.Callback<TL_stars.StarGift> r12 = this.onSelect;
            if (r12 == null) goto L7;
            r12.run(r2);
        L7:
            dismiss();
            return;
        }

        private /* synthetic */ void lambda$buyGift$26(TL_stars.TL_starGiftUnique r5, long r6, StarGiftSheet.PaymentFormState r8, Browser.Progress r9) {
            r9.init();
            StarsController.getInstance(this.currentAccount, r8.currency).buyResellingGift(r8.form, r5, r6, new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda0(this, r9, r5));
        }

        private /* synthetic */ void lambda$buyGift$27(AlertDialog r12, AmountUtils$Currency r13, TL_stars.TL_starGiftUnique r14, long r15, TLRPC.TL_payments_paymentFormStarGift r17) {
            r12.dismiss();
            if (r17 != null) goto L5;
            return;
        L5:
            StarGiftSheet.PaymentFormState r4 = new StarGiftSheet.PaymentFormState(r13, r17);
            new StarGiftSheet.ResaleBuyTransferAlert(getContext(), this.resourcesProvider, r14, r4, this.currentAccount, r15, r14.title + " #" + LocaleController.formatNumber(r14.num, ','), true, new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda29(this, r14, r15)).show();
        }

        private /* synthetic */ void lambda$new$15(State r23, Context r24, View r25) {
            if (State.m14996$$Nest$fgetresaleList(r23).backdropAttributes.isEmpty() == false) goto L5;
            return;
        L5:
            ItemOptions r12 = ItemOptions.makeOptions(this.container, this.resourcesProvider, this.backdropButton, false, true).setDrawScrim(false).setOnTopOfScrim().translate(0.0f, AndroidUtilities.dp(-8.0f)).needsFocus();
            r12.setOnDismiss(new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda15(r12));
            String[] r13 = {_UrlKt.FRAGMENT_ENCODE_SET};
            ArrayList r14 = new ArrayList(State.m14996$$Nest$fgetresaleList(r23).backdropAttributes);
            Collections.sort(r14, new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda16(r23));
            AnonymousClass3 r0 = new AnonymousClass3(this, r24, this.currentAccount, 0, new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda17(r13, r23, r14), new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda18(r23, r12), null, this.resourcesProvider);
            r0.adapter.setApplyBackground(false);
            FrameLayout r3 = new FrameLayout(r24);
            ImageView r4 = new ImageView(r24);
            r4.setScaleType(ImageView.ScaleType.CENTER);
            r4.setImageResource(R.drawable.smiles_inputsearch);
            r4.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon), PorterDuff.Mode.SRC_IN));
            r3.addView(r4, LayoutHelper.createFrame(24, 24.0f, 19, 10.0f, 0.0f, 0.0f, 0.0f));
            EditTextCaption r42 = new EditTextCaption(r24, this.resourcesProvider);
            r42.setTextSize(1, 16.0f);
            r42.setInputType(573441);
            r42.setRawInputType(573441);
            r42.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, this.resourcesProvider));
            r42.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
            r42.setCursorSize(AndroidUtilities.dp(19.0f));
            r42.setCursorWidth(1.5f);
            r42.setHint(LocaleController.getString(R.string.Gift2ResaleFiltersSearch));
            r42.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourcesProvider));
            r42.setBackground(null);
            r3.addView(r42, LayoutHelper.createFrame(-1, -2.0f, 19, 43.0f, 0.0f, 8.0f, 0.0f));
            r42.addTextChangedListener(new AnonymousClass4(this, r13, r0));
            if (r14.size() <= 8) goto L9;
            r12.addView(r3, LayoutHelper.createLinear(-1, 44));
            r12.addGap();
        L9:
            if (State.m14996$$Nest$fgetresaleList(r23).notSelectedBackdropAttributes.isEmpty() == true) goto L11;
            r12.add(R.drawable.msg_select, LocaleController.getString(R.string.SelectAll), new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda19(r23));
        L11:
            r12.addView(r0);
            r12.show();
        }

        private /* synthetic */ void lambda$new$21(State r23, Context r24, View r25) {
            if (State.m14996$$Nest$fgetresaleList(r23).patternAttributes.isEmpty() == false) goto L5;
            return;
        L5:
            ItemOptions r12 = ItemOptions.makeOptions(this.container, this.resourcesProvider, this.patternButton, false, true).setDrawScrim(false).setOnTopOfScrim().translate(0.0f, AndroidUtilities.dp(-8.0f)).needsFocus();
            r12.setOnDismiss(new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda20(r12));
            String[] r13 = {_UrlKt.FRAGMENT_ENCODE_SET};
            ArrayList r14 = new ArrayList(State.m14996$$Nest$fgetresaleList(r23).patternAttributes);
            Collections.sort(r14, new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda21(r23));
            AnonymousClass5 r0 = new AnonymousClass5(this, r24, this.currentAccount, 0, new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda22(r13, r23, r14), new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda23(r23, r12), null, this.resourcesProvider);
            r0.adapter.setApplyBackground(false);
            FrameLayout r3 = new FrameLayout(r24);
            ImageView r4 = new ImageView(r24);
            r4.setScaleType(ImageView.ScaleType.CENTER);
            r4.setImageResource(R.drawable.smiles_inputsearch);
            r4.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon), PorterDuff.Mode.SRC_IN));
            r3.addView(r4, LayoutHelper.createFrame(24, 24.0f, 19, 10.0f, 0.0f, 0.0f, 0.0f));
            EditTextCaption r42 = new EditTextCaption(r24, this.resourcesProvider);
            r42.setTextSize(1, 16.0f);
            r42.setInputType(573441);
            r42.setRawInputType(573441);
            r42.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, this.resourcesProvider));
            r42.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
            r42.setCursorSize(AndroidUtilities.dp(19.0f));
            r42.setCursorWidth(1.5f);
            r42.setHint(LocaleController.getString(R.string.Gift2ResaleFiltersSearch));
            r42.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourcesProvider));
            r42.setBackground(null);
            r3.addView(r42, LayoutHelper.createFrame(-1, -2.0f, 19, 43.0f, 0.0f, 8.0f, 0.0f));
            r42.addTextChangedListener(new AnonymousClass6(this, r13, r0));
            if (r14.size() <= 8) goto L9;
            r12.addView(r3, LayoutHelper.createLinear(-1, 44));
            r12.addGap();
        L9:
            if (State.m14996$$Nest$fgetresaleList(r23).notSelectedPatternAttributes.isEmpty() == true) goto L11;
            r12.add(R.drawable.msg_select, LocaleController.getString(R.string.SelectAll), new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda24(r23));
        L11:
            r12.addView(r0);
            r12.show();
        }

        private /* synthetic */ void lambda$new$22(State r4, View r5, int r6, float r7, float r8) {
            UItem r52 = this.adapter.getItem(r6 - 1);
            if (r52 == null) goto L38;
            Object r62 = r52.object;
            if ((r62 instanceof TL_stars.StarGift) == false) goto L37;
            TL_stars.StarGift r63 = (TL_stars.StarGift) r62;
            boolean r82 = r52.red;
            if (TextUtils.isEmpty(r63.gift_address) == false) goto L10;
        L13:
            if (r82 == true) goto L15;
        L18:
            if (r82 == true) goto L32;
            ArrayList<TL_stars.SavedStarGift> r42 = State.m14995$$Nest$fgetlist(r4).gifts;
            int r53 = r42.size();
            int r83 = 0;
        L20:
            if (r83 >= r53) goto L24;
            TL_stars.SavedStarGift r0 = r42.get(r83);
            r83 = r83 + 1;
            TL_stars.SavedStarGift r02 = r0;
            if (r02.gift != r63) goto L20;
        L25:
            if (r02 == null) goto L32;
            if (r02.can_craft_at <= 0) goto L32;
            if (r02.can_craft_at <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L32;
            new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(R.string.GiftCraftUnavailableTitle)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftCraftUnavailableTextTime, new Object[]{LocaleController.formatDateTime(r02.can_craft_at, true)}))).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
            return;
        L24:
            r02 = null;
        L32:
            this.onSelect.run(r63);
            dismiss();
            return;
        L15:
            if ((r63 instanceof TL_stars.TL_starGiftUnique) == false) goto L18;
            buyGift((TL_stars.TL_starGiftUnique) r52.object);
            return;
        L10:
            if (this.willBeFirst == false) goto L13;
            new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.GiftCraftCantChooseFirstTitle)).setMessage(LocaleController.getString(R.string.GiftCraftCantChooseFirst)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
            return;
        L37:
            return;
        }

        private /* synthetic */ void lambda$new$24() {
            this.adapter.update(true);
            AndroidUtilities.runOnUIThread(new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda14(this), 150);
        }

        private /* synthetic */ void lambda$new$3(State r3, View r4) {
            ItemOptions.makeOptions(this.container, this.resourcesProvider, this.sortButton).add(R.drawable.menu_sort_value, LocaleController.getString(ResaleGiftsList.Sorting.BY_PRICE.buttonStringResId), new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda25(r3)).add(R.drawable.menu_sort_date, LocaleController.getString(ResaleGiftsList.Sorting.BY_DATE.buttonStringResId), new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda26(r3)).add(R.drawable.menu_sort_number, LocaleController.getString(ResaleGiftsList.Sorting.BY_NUMBER.buttonStringResId), new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda27(r3)).setDrawScrim(false).setOnTopOfScrim().translate(0.0f, AndroidUtilities.dp(-8.0f)).show();
        }

        private /* synthetic */ void lambda$new$9(State r23, Context r24, View r25) {
            if (State.m14996$$Nest$fgetresaleList(r23).modelAttributes.isEmpty() == false) goto L5;
            return;
        L5:
            ItemOptions r12 = ItemOptions.makeOptions(this.container, this.resourcesProvider, this.modelButton, false, true).setDrawScrim(false).setOnTopOfScrim().translate(0.0f, AndroidUtilities.dp(-8.0f)).needsFocus();
            r12.setOnDismiss(new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda9(r12));
            String[] r13 = {_UrlKt.FRAGMENT_ENCODE_SET};
            ArrayList r14 = new ArrayList(State.m14996$$Nest$fgetresaleList(r23).modelAttributes);
            Collections.sort(r14, new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda10(r23));
            AnonymousClass1 r0 = new AnonymousClass1(this, r24, this.currentAccount, 0, new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda11(r13, r23, r14), new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda12(r23, r12), null, this.resourcesProvider);
            r0.adapter.setApplyBackground(false);
            FrameLayout r3 = new FrameLayout(r24);
            ImageView r4 = new ImageView(r24);
            r4.setScaleType(ImageView.ScaleType.CENTER);
            r4.setImageResource(R.drawable.smiles_inputsearch);
            r4.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon), PorterDuff.Mode.SRC_IN));
            r3.addView(r4, LayoutHelper.createFrame(24, 24.0f, 19, 10.0f, 0.0f, 0.0f, 0.0f));
            EditTextCaption r42 = new EditTextCaption(r24, this.resourcesProvider);
            r42.setTextSize(1, 16.0f);
            r42.setInputType(573441);
            r42.setRawInputType(573441);
            r42.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, this.resourcesProvider));
            r42.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
            r42.setCursorSize(AndroidUtilities.dp(19.0f));
            r42.setCursorWidth(1.5f);
            r42.setHint(LocaleController.getString(R.string.Gift2ResaleFiltersSearch));
            r42.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourcesProvider));
            r42.setBackground(null);
            r3.addView(r42, LayoutHelper.createFrame(-1, -2.0f, 19, 43.0f, 0.0f, 8.0f, 0.0f));
            r42.addTextChangedListener(new AnonymousClass2(this, r13, r0));
            if (r14.size() <= 8) goto L9;
            r12.addView(r3, LayoutHelper.createLinear(-1, 44));
            r12.addGap();
        L9:
            if (State.m14996$$Nest$fgetresaleList(r23).notSelectedModelAttributes.isEmpty() == true) goto L11;
            r12.add(R.drawable.msg_select, LocaleController.getString(R.string.SelectAll), new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda13(r23));
        L11:
            r12.addView(r0);
            r12.show();
        }

        private void onScroll() {
            int r0 = 0;
            boolean r1 = false;
            boolean r2 = false;
        L4:
            if (r0 >= this.recyclerListView.getChildCount()) goto L17;
            View r3 = this.recyclerListView.getChildAt(r0);
            if ((r3 instanceof FlickerLoadingView) == false) goto L16;
            int r32 = this.recyclerListView.getChildAdapterPosition(r3) - 1;
            if (r32 < 0) goto L16;
            UItem r33 = this.adapter.getItem(r32);
            if (r33 == null) goto L16;
            if (r33.id >= 10) goto L15;
            r1 = true;
            goto L16
        L15:
            r2 = true;
        L16:
            r0 = r0 + 1;
            goto L4
        L17:
            if (r1 == false) goto L19;
            State.m14995$$Nest$fgetlist(this.state).load();
        L19:
            if (r2 == false) goto L28;
            State.m14996$$Nest$fgetresaleList(this.state).load();
            return;
        }

        private void updateList(boolean r1) {
            this.adapter.update(true);
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
            AnonymousClass9 r0 = new AnonymousClass9(this, r9, getContext(), this.currentAccount, 0, new ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda1(this), this.resourcesProvider);
            this.adapter = r0;
            return r0;
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public CharSequence getTitle() {
            String r0 = this.collectionName;
            if (r0 == null) goto L6;
            return r0;
        L6:
            return LocaleController.getString(R.string.GiftCraftSelectTitle);
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public void onSheetTop(float r5) {
            float r52 = (r5 + this.containerView.getY()) - this.actionView.getMeasuredHeight();
            float r0 = 1.0f - Utilities.clamp01(Math.max(0.0f, (-r52) + AndroidUtilities.dp(8.0f)) / this.actionView.getMeasuredHeight());
            float r2 = this.container.getHeight() / 2.0f;
            if (r52 <= r2) goto L5;
            r0 = Math.min(r0, Utilities.clamp01(1.0f - ((r52 - r2) / AndroidUtilities.dpf2(128.0f))));
        L5:
            this.actionView.setScaleX(r0);
            this.actionView.setScaleY(r0);
            this.actionView.setAlpha(AndroidUtilities.ilerp(r0, 0.5f, 1.0f));
            this.actionView.setTranslationY(r52);
        }

        public SelectGiftSheet setActionText(CharSequence r2) {
            this.actionView.set(r2);
            return this;
        }

        public SelectGiftSheet setOnSelect(Utilities.Callback<TL_stars.StarGift> r1) {
            this.onSelect = r1;
            return this;
        }

        public SelectGiftSheet setWillBeFirst(boolean r1) {
            this.willBeFirst = r1;
            return this;
        }

        public SelectGiftSheet without(HashSet<Long> r2) {
            this.without.addAll(r2);
            updateList(false);
            return this;
        }
    }

    /* renamed from: $r8$lambda$-RUkQmYJ1b2YtRRpu61kq4klMys, reason: not valid java name */
    public static /* synthetic */ void m14958$r8$lambda$RUkQmYJ1b2YtRRpu61kq4klMys(ItemOptions r0) {
        ActionBarPopupWindow r02 = r0.actionBarPopupWindow;
        if (r02 == null) goto L6;
        AndroidUtilities.hideKeyboard(r02.getContentView());
        return;
    }

    /* renamed from: $r8$lambda$2QN-nC_OV2wpmy-gsmjKt3v9CsQ, reason: not valid java name */
    public static /* synthetic */ void m14959$r8$lambda$2QNnC_OV2wpmygsmjKt3v9CsQ(ResaleGiftsFragment r0) {
        r0.lambda$createView$5();
    }

    public static /* synthetic */ int $r8$lambda$53HhVkfAS83kWaODvRMCiBe3TdI(ResaleGiftsFragment r0, TL_stars.starGiftAttributePattern r1, TL_stars.starGiftAttributePattern r2) {
        return r0.lambda$createView$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$5IpWvtSlw3YMrbFZJlFe8hsoju8(ResaleGiftsFragment r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$6N2GBEQBYt0BtCxKxTEjZA55__k(ResaleGiftsFragment r0, TL_stars.TL_starGiftUnique r1, long r2, boolean r4) {
        r0.lambda$onItemClick$28(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$9GtQOFDBIjcgNhbR7Gw3R5FuLTo(ResaleGiftsFragment r0, boolean r1) {
        r0.updateList(r1);
    }

    /* renamed from: $r8$lambda$BRkLIqnDrdQLhKe8vB-kxohoS_Q, reason: not valid java name */
    public static /* synthetic */ boolean m14960$r8$lambda$BRkLIqnDrdQLhKe8vBkxohoS_Q(ResaleGiftsFragment r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onItemLongClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$C09_c7YVxiKRrszzKEIfCgDXw9E(ResaleGiftsFragment r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onItemClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ int $r8$lambda$HrJHymx28jpHjBkhjCgYMEBnggY(ResaleGiftsFragment r0, TL_stars.starGiftAttributeBackdrop r1, TL_stars.starGiftAttributeBackdrop r2) {
        return r0.lambda$createView$17(r1, r2);
    }

    /* renamed from: $r8$lambda$JucZpQc-PuoJ6oJz9VgiApt40mE, reason: not valid java name */
    public static /* synthetic */ void m14961$r8$lambda$JucZpQcPuoJ6oJz9VgiApt40mE(ResaleGiftsFragment r0, CheckBox2 r1) {
        r0.lambda$createView$8(r1);
    }

    /* renamed from: $r8$lambda$K-NUaWZ3QFNxEn_OVvaHupUohOY, reason: not valid java name */
    public static /* synthetic */ void m14962$r8$lambda$KNUaWZ3QFNxEn_OVvaHupUohOY(ResaleGiftsFragment r0, String[] r1, ArrayList r2, ArrayList r3, UniversalAdapter r4) {
        r0.lambda$createView$18(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$M4710f_hfbVJeZIsXhiPXEKmLxQ(ItemOptions r0) {
        ActionBarPopupWindow r02 = r0.actionBarPopupWindow;
        if (r02 == null) goto L6;
        AndroidUtilities.hideKeyboard(r02.getContentView());
        return;
    }

    public static /* synthetic */ void $r8$lambda$MYWK9K9bf93FZrmRVbq89YZKgMs(ResaleGiftsFragment r0) {
        r0.lambda$createView$14();
    }

    public static /* synthetic */ void $r8$lambda$NeoTk49dTl24zi7VuLZ3GfK1l2o(ResaleGiftsFragment r0, Context r1, View r2) {
        r0.lambda$createView$21(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$UCLD5D88z7yNP55otfmiM9AHDK0(ResaleGiftsFragment r0, CheckBox2 r1, View r2) {
        r0.lambda$createView$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$UiZl2xn7sgFzPG62fS70Hh4zvQ8(ResaleGiftsFragment r0, ItemOptions r1, UItem r2, View r3, Integer r4, Float r5, Float r6) {
        r0.lambda$createView$25(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$Yuhj9ZGqmU1iahIeO_uP0BHwjRs(ResaleGiftsFragment r0) {
        r0.lambda$createView$20();
    }

    public static /* synthetic */ void $r8$lambda$a3erJoMlKGjYUAgSytFqn2HzQTY(ResaleGiftsFragment r0) {
        r0.lambda$createView$26();
    }

    public static /* synthetic */ void $r8$lambda$aqOu4WtFPUnBKi8xp01__9ydfaE(ResaleGiftsFragment r0, CheckBox2 r1) {
        r0.lambda$createView$7(r1);
    }

    /* renamed from: $r8$lambda$dGAa-QUph54DckH7hyFpNTPVxZM, reason: not valid java name */
    public static /* synthetic */ void m14963$r8$lambda$dGAaQUph54DckH7hyFpNTPVxZM(ResaleGiftsFragment r0, StarsIntroActivity.StarsBalanceView r1, View r2) {
        r0.lambda$createView$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$eUEHmKnhYYysuI9AnHIU5e4m__s(ResaleGiftsFragment r0) {
        r0.lambda$createView$4();
    }

    public static /* synthetic */ void $r8$lambda$gHbT7wiDGsmvcWNChmG9aFX0CCQ(ResaleGiftsFragment r0, String[] r1, ArrayList r2, ArrayList r3, UniversalAdapter r4) {
        r0.lambda$createView$12(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$hy64K8mIC4ScPP4z9wEHo88wSy0(ResaleGiftsFragment r0, Context r1, View r2) {
        r0.lambda$createView$27(r1, r2);
    }

    /* renamed from: $r8$lambda$kH9LaQNaGSxv4RJm8BfQ-1MAX70, reason: not valid java name */
    public static /* synthetic */ void m14964$r8$lambda$kH9LaQNaGSxv4RJm8BfQ1MAX70(ItemOptions r0) {
        ActionBarPopupWindow r02 = r0.actionBarPopupWindow;
        if (r02 == null) goto L6;
        AndroidUtilities.hideKeyboard(r02.getContentView());
        return;
    }

    public static /* synthetic */ int $r8$lambda$kJEkh_nlmOKNgWpq0f6VIWUDh0k(ResaleGiftsFragment r0, TL_stars.starGiftAttributeModel r1, TL_stars.starGiftAttributeModel r2) {
        return r0.lambda$createView$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$lTdlJTo0durV1zDjCMJW9zP169Y(ResaleGiftsFragment r0, ItemOptions r1, UItem r2, View r3, Integer r4, Float r5, Float r6) {
        r0.lambda$createView$19(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$nemQeooMUZ_ruvNbR38u-9UEBAk, reason: not valid java name */
    public static /* synthetic */ void m14965$r8$lambda$nemQeooMUZ_ruvNbR38u9UEBAk(ResaleGiftsFragment r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    /* renamed from: $r8$lambda$op3RCSVKqs3YRy3XWqqI4kb-ZoA, reason: not valid java name */
    public static /* synthetic */ void m14966$r8$lambda$op3RCSVKqs3YRy3XWqqI4kbZoA(ResaleGiftsFragment r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    /* renamed from: $r8$lambda$pPzLtWZ-RDQNH4PKVUXo9C7h2sQ, reason: not valid java name */
    public static /* synthetic */ void m14967$r8$lambda$pPzLtWZRDQNH4PKVUXo9C7h2sQ(ResaleGiftsFragment r0) {
        r0.lambda$createView$6();
    }

    /* renamed from: $r8$lambda$q7pcRzRjGfpYi7i6RLiTmG-i8ZQ, reason: not valid java name */
    public static /* synthetic */ void m14968$r8$lambda$q7pcRzRjGfpYi7i6RLiTmGi8ZQ(ResaleGiftsFragment r0, CheckBox2 r1, View r2) {
        r0.lambda$createView$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$woAAe5nrEbeLgFsK6RJeTfV7tWU(ResaleGiftsFragment r0, ItemOptions r1, UItem r2, View r3, Integer r4, Float r5, Float r6) {
        r0.lambda$createView$13(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$ytVPD2lVEe5hU7u3_aQKZAhItKU(ResaleGiftsFragment r0, Context r1, View r2) {
        r0.lambda$createView$15(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$zxna53wWgBRnJD9QcBw3Wr_U0hw(ResaleGiftsFragment r0, String[] r1, ArrayList r2, ArrayList r3, UniversalAdapter r4) {
        r0.lambda$createView$24(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LargeEmptyView m14969$$Nest$fgetemptyView(ResaleGiftsFragment r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetfilterScrollView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HorizontalScrollView m14970$$Nest$fgetfilterScrollView(ResaleGiftsFragment r0) {
        return r0.filterScrollView;
    }

    /* renamed from: -$$Nest$fgetfiltersDivider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m14971$$Nest$fgetfiltersDivider(ResaleGiftsFragment r0) {
        return r0.filtersDivider;
    }

    /* renamed from: -$$Nest$fgetfiltersShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14972$$Nest$fgetfiltersShown(ResaleGiftsFragment r0) {
        return r0.filtersShown;
    }

    /* renamed from: -$$Nest$fgetlist, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ResaleGiftsList m14973$$Nest$fgetlist(ResaleGiftsFragment r0) {
        return r0.list;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m14974$$Nest$fgetlistView(ResaleGiftsFragment r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$misLoadingVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14975$$Nest$misLoadingVisible(ResaleGiftsFragment r0) {
        return r0.isLoadingVisible();
    }

    public ResaleGiftsFragment(long r7, String r9, long r10, Theme.ResourcesProvider r12) {
        this.animatorClearFiltersButtonVisible = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 380);
        this.filtersShown = true;
        this.dialogId = r7;
        this.gift_name = r9;
        this.gift_id = r10;
        this.resourceProvider = r12;
        ResaleGiftsList r6 = new ResaleGiftsList(this.currentAccount, r10, new ResaleGiftsFragment$$ExternalSyntheticLambda0(this));
        this.list = r6;
        r6.load();
    }

    public static /* synthetic */ ActionBar access$000(ResaleGiftsFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(ResaleGiftsFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$200(ResaleGiftsFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(ResaleGiftsFragment r0) {
        return r0.actionBar;
    }

    private void fillItems(ArrayList<UItem> r12, UniversalAdapter r13) {
        ArrayList<TL_stars.TL_starGiftUnique> r132 = this.list.gifts;
        int r0 = r132.size();
        boolean r1 = false;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        TL_stars.TL_starGiftUnique r3 = r132.get(r2);
        r2 = r2 + 1;
        r12.add(GiftSheet.GiftCell.Factory.asStarGift(0, r3, false, false, false, true, false));
        goto L3
    L5:
        ResaleGiftsList r133 = this.list;
        if (r133.loading == false) goto L8;
    L9:
        r12.add(UItem.asFlicker(-1, 34).setSpanCount(1));
        r12.add(UItem.asFlicker(-2, 34).setSpanCount(1));
        r12.add(UItem.asFlicker(-3, 34).setSpanCount(1));
        if (this.list.gifts.isEmpty() == false) goto L13;
        r12.add(UItem.asFlicker(-4, 34).setSpanCount(1));
        r12.add(UItem.asFlicker(-5, 34).setSpanCount(1));
        r12.add(UItem.asFlicker(-6, 34).setSpanCount(1));
        r12.add(UItem.asFlicker(-7, 34).setSpanCount(1));
        r12.add(UItem.asFlicker(-8, 34).setSpanCount(1));
        r12.add(UItem.asFlicker(-9, 34).setSpanCount(1));
        r12.add(UItem.asFlicker(-10, 34).setSpanCount(1));
        r12.add(UItem.asFlicker(-11, 34).setSpanCount(1));
        r12.add(UItem.asFlicker(-12, 34).setSpanCount(1));
        r12.add(UItem.asFlicker(-13, 34).setSpanCount(1));
        r12.add(UItem.asFlicker(-14, 34).setSpanCount(1));
        r12.add(UItem.asFlicker(-15, 34).setSpanCount(1));
    L13:
        if (r12.isEmpty() == true) goto L15;
    L17:
        updateEmptyView(r1);
        return;
    L15:
        if (this.list.loading == true) goto L17;
        r1 = true;
        goto L17
    L8:
        if (r133.endReached == true) goto L13;
        goto L9
    }

    private boolean isLoadingVisible() {
        int r1 = 0;
    L4:
        if (r1 >= this.listView.getChildCount()) goto L10;
        if ((this.listView.getChildAt(r1) instanceof FlickerLoadingView) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$createView$0(StarsIntroActivity.StarsBalanceView r3, View r4) {
        if (r3.lastBalance > 0) goto L5;
        return;
    L5:
        presentFragment(new StarsIntroActivity());
    }

    private /* synthetic */ void lambda$createView$1(View r1) {
        this.list.notSelectedBackdropAttributes.clear();
        this.list.notSelectedModelAttributes.clear();
        this.list.notSelectedPatternAttributes.clear();
        this.list.reload();
    }

    private /* synthetic */ int lambda$createView$11(TL_stars.starGiftAttributeModel r4, TL_stars.starGiftAttributeModel r5) {
        Integer r42 = this.list.modelAttributesCounter.get(Long.valueOf(r4.document.id));
        Integer r3 = this.list.modelAttributesCounter.get(Long.valueOf(r5.document.id));
        if (r42 != null) goto L6;
        return 1;
    L6:
        if (r3 != null) goto L10;
        return -1;
    L10:
        return r3.intValue() - r42.intValue();
    }

    private /* synthetic */ void lambda$createView$12(String[] r11, ArrayList r12, ArrayList r13, UniversalAdapter r14) {
        String r112 = r11[0].toLowerCase();
        String r0 = AndroidUtilities.translitSafe(r112);
        boolean r1 = this.list.notSelectedModelAttributes.isEmpty();
        int r2 = r12.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L26;
        Object r4 = r12.get(r3);
        r3 = r3 + 1;
        TL_stars.starGiftAttributeModel r42 = (TL_stars.starGiftAttributeModel) r4;
        boolean r5 = this.list.notSelectedModelAttributes.contains(Long.valueOf(r42.document.id));
        boolean r6 = !r5;
        if (TextUtils.isEmpty(r112) == true) goto L14;
        if (r42.name.toLowerCase().startsWith(r112) == true) goto L14;
        if (r42.name.toLowerCase().startsWith(r0) == true) goto L14;
        if (r42.name.toLowerCase().contains(" " + r112) == true) goto L14;
        if (r42.name.toLowerCase().contains(" " + r0) == false) goto L3;
    L14:
        Integer r7 = this.list.modelAttributesCounter.get(Long.valueOf(r42.document.id));
        if (r7 != null) goto L17;
        int r72 = 0;
    L18:
        UItem r43 = ModelItem.Factory.asModel(r42, r72, r112);
        if (TextUtils.isEmpty(r112) == true) goto L24;
        if (r1 == true) goto L23;
        if (r5 == true) goto L23;
        r6 = true;
    L23:
        r6 = false;
    L24:
        r13.add(r43.setChecked(r6));
        goto L3
    L17:
        r72 = r7.intValue();
        goto L18
    L26:
        if (r13.isEmpty() == false) goto L38;
        r13.add(EmptyView.Factory.asEmptyView(LocaleController.getString(R.string.Gift2ResaleFiltersModelEmpty)));
        return;
    }

    private /* synthetic */ void lambda$createView$13(ItemOptions r4, UItem r5, View r6, Integer r7, Float r8, Float r9) {
        long r52 = ((TL_stars.starGiftAttributeModel) r5.object).document.id;
        boolean r72 = this.list.notSelectedModelAttributes.contains(Long.valueOf(r52));
        ResaleGiftsList r82 = this.list;
        if (r72 == true) goto L12;
        boolean r73 = r82.notSelectedModelAttributes.isEmpty();
        ResaleGiftsList r83 = this.list;
        if (r73 == false) goto L11;
        ArrayList<TL_stars.starGiftAttributeModel> r74 = r83.modelAttributes;
        int r84 = r74.size();
        int r92 = 0;
    L7:
        if (r92 >= r84) goto L13;
        TL_stars.starGiftAttributeModel r0 = r74.get(r92);
        r92 = r92 + 1;
        long r02 = r0.document.id;
        if (r02 == r52) goto L7;
        this.list.notSelectedModelAttributes.add(Long.valueOf(r02));
    L13:
        this.list.reload();
        r4.dismiss();
        return;
    L11:
        r83.notSelectedModelAttributes.add(Long.valueOf(r52));
        goto L13
    L12:
        r82.notSelectedModelAttributes.remove(Long.valueOf(r52));
        goto L13
    }

    private /* synthetic */ void lambda$createView$14() {
        if (this.list.notSelectedModelAttributes.isEmpty() == false) goto L5;
        return;
    L5:
        this.list.notSelectedModelAttributes.clear();
        this.list.reload();
    }

    private /* synthetic */ void lambda$createView$15(Context r20, View r21) {
        if (this.filtersShown == true) goto L6;
        return;
    L6:
        if (this.list.modelAttributes.isEmpty() == false) goto L8;
        return;
    L8:
        ItemOptions r9 = ItemOptions.makeOptions(this, this.modelButton, false, true).setDrawScrim(false).setOnTopOfScrim().translate(0.0f, AndroidUtilities.dp(-8.0f)).needsFocus();
        r9.setOnDismiss(new ResaleGiftsFragment$$ExternalSyntheticLambda22(r9));
        String[] r10 = {_UrlKt.FRAGMENT_ENCODE_SET};
        ArrayList r11 = new ArrayList(this.list.modelAttributes);
        Collections.sort(r11, new ResaleGiftsFragment$$ExternalSyntheticLambda23(this));
        AnonymousClass5 r0 = new AnonymousClass5(this, this, new ResaleGiftsFragment$$ExternalSyntheticLambda24(this, r10, r11), new ResaleGiftsFragment$$ExternalSyntheticLambda25(this, r9), null);
        r0.adapter.setApplyBackground(false);
        FrameLayout r2 = new FrameLayout(r20);
        ImageView r3 = new ImageView(r20);
        r3.setScaleType(ImageView.ScaleType.CENTER);
        r3.setImageResource(R.drawable.smiles_inputsearch);
        r3.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon), PorterDuff.Mode.SRC_IN));
        r2.addView(r3, LayoutHelper.createFrame(24, 24.0f, 19, 10.0f, 0.0f, 0.0f, 0.0f));
        EditTextCaption r32 = new EditTextCaption(r20, this.resourceProvider);
        r32.setTextSize(1, 16.0f);
        r32.setInputType(573441);
        r32.setRawInputType(573441);
        r32.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, this.resourceProvider));
        r32.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourceProvider));
        r32.setCursorSize(AndroidUtilities.dp(19.0f));
        r32.setCursorWidth(1.5f);
        r32.setHint(LocaleController.getString(R.string.Gift2ResaleFiltersSearch));
        r32.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourceProvider));
        r32.setBackground(null);
        r2.addView(r32, LayoutHelper.createFrame(-1, -2.0f, 19, 43.0f, 0.0f, 8.0f, 0.0f));
        r32.addTextChangedListener(new AnonymousClass6(this, r10, r0));
        if (r11.size() <= 8) goto L12;
        r9.addView(r2, LayoutHelper.createLinear(-1, 44));
        r9.addGap();
    L12:
        if (this.list.notSelectedModelAttributes.isEmpty() == true) goto L14;
        r9.add(R.drawable.msg_select, LocaleController.getString(R.string.SelectAll), new ResaleGiftsFragment$$ExternalSyntheticLambda26(this));
    L14:
        r9.addView(r0);
        r9.show();
    }

    private /* synthetic */ int lambda$createView$17(TL_stars.starGiftAttributeBackdrop r2, TL_stars.starGiftAttributeBackdrop r3) {
        Integer r22 = this.list.backdropAttributesCounter.get(Integer.valueOf(r2.backdrop_id));
        Integer r1 = this.list.backdropAttributesCounter.get(Integer.valueOf(r3.backdrop_id));
        if (r22 != null) goto L6;
        return 1;
    L6:
        if (r1 != null) goto L10;
        return -1;
    L10:
        return r1.intValue() - r22.intValue();
    }

    private /* synthetic */ void lambda$createView$18(String[] r11, ArrayList r12, ArrayList r13, UniversalAdapter r14) {
        String r112 = r11[0].toLowerCase();
        String r0 = AndroidUtilities.translitSafe(r112);
        boolean r1 = this.list.notSelectedBackdropAttributes.isEmpty();
        int r2 = r12.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L26;
        Object r4 = r12.get(r3);
        r3 = r3 + 1;
        TL_stars.starGiftAttributeBackdrop r42 = (TL_stars.starGiftAttributeBackdrop) r4;
        boolean r5 = this.list.notSelectedBackdropAttributes.contains(Integer.valueOf(r42.backdrop_id));
        boolean r6 = !r5;
        if (TextUtils.isEmpty(r112) == true) goto L14;
        if (r42.name.toLowerCase().startsWith(r112) == true) goto L14;
        if (r42.name.toLowerCase().startsWith(r0) == true) goto L14;
        if (r42.name.toLowerCase().contains(" " + r112) == true) goto L14;
        if (r42.name.toLowerCase().contains(" " + r0) == false) goto L3;
    L14:
        Integer r7 = this.list.backdropAttributesCounter.get(Integer.valueOf(r42.backdrop_id));
        if (r7 != null) goto L17;
        int r72 = 0;
    L18:
        UItem r43 = BackdropItem.Factory.asBackdrop(r42, r72, r112);
        if (TextUtils.isEmpty(r112) == true) goto L24;
        if (r1 == true) goto L23;
        if (r5 == true) goto L23;
        r6 = true;
    L23:
        r6 = false;
    L24:
        r13.add(r43.setChecked(r6));
        goto L3
    L17:
        r72 = r7.intValue();
        goto L18
    L26:
        if (r13.isEmpty() == false) goto L38;
        r13.add(EmptyView.Factory.asEmptyView(LocaleController.getString(R.string.Gift2ResaleFiltersBackdropEmpty)));
        return;
    }

    private /* synthetic */ void lambda$createView$19(ItemOptions r2, UItem r3, View r4, Integer r5, Float r6, Float r7) {
        int r32 = ((TL_stars.starGiftAttributeBackdrop) r3.object).backdrop_id;
        boolean r42 = this.list.notSelectedBackdropAttributes.contains(Integer.valueOf(r32));
        ResaleGiftsList r52 = this.list;
        if (r42 == true) goto L12;
        boolean r43 = r52.notSelectedBackdropAttributes.isEmpty();
        ResaleGiftsList r53 = this.list;
        if (r43 == false) goto L11;
        ArrayList<TL_stars.starGiftAttributeBackdrop> r44 = r53.backdropAttributes;
        int r54 = r44.size();
        int r62 = 0;
    L7:
        if (r62 >= r54) goto L13;
        TL_stars.starGiftAttributeBackdrop r72 = r44.get(r62);
        r62 = r62 + 1;
        int r73 = r72.backdrop_id;
        if (r73 == r32) goto L7;
        this.list.notSelectedBackdropAttributes.add(Integer.valueOf(r73));
    L13:
        this.list.reload();
        r2.dismiss();
        return;
    L11:
        r53.notSelectedBackdropAttributes.add(Integer.valueOf(r32));
        goto L13
    L12:
        r52.notSelectedBackdropAttributes.remove(Integer.valueOf(r32));
        goto L13
    }

    private /* synthetic */ void lambda$createView$2(CheckBox2 r3, View r4) {
        ResaleGiftsList r42 = this.list;
        if (r42 == null) goto L6;
        ResaleGiftsList.m14981$$Nest$fputstarsOnly(r42, !ResaleGiftsList.m14980$$Nest$fgetstarsOnly(r42));
        r3.setChecked(ResaleGiftsList.m14980$$Nest$fgetstarsOnly(this.list), true);
        this.list.reload();
        return;
    }

    private /* synthetic */ void lambda$createView$20() {
        if (this.list.notSelectedBackdropAttributes.isEmpty() == false) goto L5;
        return;
    L5:
        this.list.notSelectedBackdropAttributes.clear();
        this.list.reload();
    }

    private /* synthetic */ void lambda$createView$21(Context r20, View r21) {
        if (this.filtersShown == true) goto L6;
        return;
    L6:
        if (this.list.backdropAttributes.isEmpty() == false) goto L8;
        return;
    L8:
        ItemOptions r9 = ItemOptions.makeOptions(this, this.backdropButton, false, true).setDrawScrim(false).setOnTopOfScrim().translate(0.0f, AndroidUtilities.dp(-8.0f)).needsFocus();
        r9.setOnDismiss(new ResaleGiftsFragment$$ExternalSyntheticLambda17(r9));
        String[] r10 = {_UrlKt.FRAGMENT_ENCODE_SET};
        ArrayList r11 = new ArrayList(this.list.backdropAttributes);
        Collections.sort(r11, new ResaleGiftsFragment$$ExternalSyntheticLambda18(this));
        AnonymousClass7 r0 = new AnonymousClass7(this, this, new ResaleGiftsFragment$$ExternalSyntheticLambda19(this, r10, r11), new ResaleGiftsFragment$$ExternalSyntheticLambda20(this, r9), null);
        r0.adapter.setApplyBackground(false);
        FrameLayout r2 = new FrameLayout(r20);
        ImageView r3 = new ImageView(r20);
        r3.setScaleType(ImageView.ScaleType.CENTER);
        r3.setImageResource(R.drawable.smiles_inputsearch);
        r3.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon), PorterDuff.Mode.SRC_IN));
        r2.addView(r3, LayoutHelper.createFrame(24, 24.0f, 19, 10.0f, 0.0f, 0.0f, 0.0f));
        EditTextCaption r32 = new EditTextCaption(r20, this.resourceProvider);
        r32.setTextSize(1, 16.0f);
        r32.setInputType(573441);
        r32.setRawInputType(573441);
        r32.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, this.resourceProvider));
        r32.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourceProvider));
        r32.setCursorSize(AndroidUtilities.dp(19.0f));
        r32.setCursorWidth(1.5f);
        r32.setHint(LocaleController.getString(R.string.Gift2ResaleFiltersSearch));
        r32.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourceProvider));
        r32.setBackground(null);
        r2.addView(r32, LayoutHelper.createFrame(-1, -2.0f, 19, 43.0f, 0.0f, 8.0f, 0.0f));
        r32.addTextChangedListener(new AnonymousClass8(this, r10, r0));
        if (r11.size() <= 8) goto L12;
        r9.addView(r2, LayoutHelper.createLinear(-1, 44));
        r9.addGap();
    L12:
        if (this.list.notSelectedBackdropAttributes.isEmpty() == true) goto L14;
        r9.add(R.drawable.msg_select, LocaleController.getString(R.string.SelectAll), new ResaleGiftsFragment$$ExternalSyntheticLambda21(this));
    L14:
        r9.addView(r0);
        r9.show();
    }

    private /* synthetic */ int lambda$createView$23(TL_stars.starGiftAttributePattern r4, TL_stars.starGiftAttributePattern r5) {
        Integer r42 = this.list.patternAttributesCounter.get(Long.valueOf(r4.document.id));
        Integer r3 = this.list.patternAttributesCounter.get(Long.valueOf(r5.document.id));
        if (r42 != null) goto L6;
        return 1;
    L6:
        if (r3 != null) goto L10;
        return -1;
    L10:
        return r3.intValue() - r42.intValue();
    }

    private /* synthetic */ void lambda$createView$24(String[] r11, ArrayList r12, ArrayList r13, UniversalAdapter r14) {
        String r112 = r11[0].toLowerCase();
        String r0 = AndroidUtilities.translitSafe(r112);
        boolean r1 = this.list.notSelectedPatternAttributes.isEmpty();
        int r2 = r12.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L26;
        Object r4 = r12.get(r3);
        r3 = r3 + 1;
        TL_stars.starGiftAttributePattern r42 = (TL_stars.starGiftAttributePattern) r4;
        boolean r5 = this.list.notSelectedPatternAttributes.contains(Long.valueOf(r42.document.id));
        boolean r6 = !r5;
        if (TextUtils.isEmpty(r112) == true) goto L14;
        if (r42.name.toLowerCase().startsWith(r112) == true) goto L14;
        if (r42.name.toLowerCase().startsWith(r0) == true) goto L14;
        if (r42.name.toLowerCase().contains(" " + r112) == true) goto L14;
        if (r42.name.toLowerCase().contains(" " + r0) == false) goto L3;
    L14:
        Integer r7 = this.list.patternAttributesCounter.get(Long.valueOf(r42.document.id));
        if (r7 != null) goto L17;
        int r72 = 0;
    L18:
        UItem r43 = PatternItem.Factory.asPattern(r42, r72, r112);
        if (TextUtils.isEmpty(r112) == true) goto L24;
        if (r1 == true) goto L23;
        if (r5 == true) goto L23;
        r6 = true;
    L23:
        r6 = false;
    L24:
        r13.add(r43.setChecked(r6));
        goto L3
    L17:
        r72 = r7.intValue();
        goto L18
    L26:
        if (r13.isEmpty() == false) goto L38;
        r13.add(EmptyView.Factory.asEmptyView(LocaleController.getString(R.string.Gift2ResaleFiltersSymbolEmpty)));
        return;
    }

    private /* synthetic */ void lambda$createView$25(ItemOptions r4, UItem r5, View r6, Integer r7, Float r8, Float r9) {
        long r52 = ((TL_stars.starGiftAttributePattern) r5.object).document.id;
        boolean r72 = this.list.notSelectedPatternAttributes.contains(Long.valueOf(r52));
        ResaleGiftsList r82 = this.list;
        if (r72 == true) goto L12;
        boolean r73 = r82.notSelectedPatternAttributes.isEmpty();
        ResaleGiftsList r83 = this.list;
        if (r73 == false) goto L11;
        ArrayList<TL_stars.starGiftAttributePattern> r74 = r83.patternAttributes;
        int r84 = r74.size();
        int r92 = 0;
    L7:
        if (r92 >= r84) goto L13;
        TL_stars.starGiftAttributePattern r0 = r74.get(r92);
        r92 = r92 + 1;
        long r02 = r0.document.id;
        if (r02 == r52) goto L7;
        this.list.notSelectedPatternAttributes.add(Long.valueOf(r02));
    L13:
        this.list.reload();
        r4.dismiss();
        return;
    L11:
        r83.notSelectedPatternAttributes.add(Long.valueOf(r52));
        goto L13
    L12:
        r82.notSelectedPatternAttributes.remove(Long.valueOf(r52));
        goto L13
    }

    private /* synthetic */ void lambda$createView$26() {
        if (this.list.notSelectedPatternAttributes.isEmpty() == false) goto L5;
        return;
    L5:
        this.list.notSelectedPatternAttributes.clear();
        this.list.reload();
    }

    private /* synthetic */ void lambda$createView$27(Context r20, View r21) {
        if (this.filtersShown == true) goto L6;
        return;
    L6:
        if (this.list.patternAttributes.isEmpty() == false) goto L8;
        return;
    L8:
        ItemOptions r9 = ItemOptions.makeOptions(this, this.patternButton, false, true).setDrawScrim(false).setOnTopOfScrim().translate(0.0f, AndroidUtilities.dp(-8.0f)).needsFocus();
        r9.setOnDismiss(new ResaleGiftsFragment$$ExternalSyntheticLambda12(r9));
        String[] r10 = {_UrlKt.FRAGMENT_ENCODE_SET};
        ArrayList r11 = new ArrayList(this.list.patternAttributes);
        Collections.sort(r11, new ResaleGiftsFragment$$ExternalSyntheticLambda13(this));
        AnonymousClass9 r0 = new AnonymousClass9(this, this, new ResaleGiftsFragment$$ExternalSyntheticLambda14(this, r10, r11), new ResaleGiftsFragment$$ExternalSyntheticLambda15(this, r9), null);
        r0.adapter.setApplyBackground(false);
        FrameLayout r2 = new FrameLayout(r20);
        ImageView r3 = new ImageView(r20);
        r3.setScaleType(ImageView.ScaleType.CENTER);
        r3.setImageResource(R.drawable.smiles_inputsearch);
        r3.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon), PorterDuff.Mode.SRC_IN));
        r2.addView(r3, LayoutHelper.createFrame(24, 24.0f, 19, 10.0f, 0.0f, 0.0f, 0.0f));
        EditTextCaption r32 = new EditTextCaption(r20, this.resourceProvider);
        r32.setTextSize(1, 16.0f);
        r32.setInputType(573441);
        r32.setRawInputType(573441);
        r32.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, this.resourceProvider));
        r32.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourceProvider));
        r32.setCursorSize(AndroidUtilities.dp(19.0f));
        r32.setCursorWidth(1.5f);
        r32.setHint(LocaleController.getString(R.string.Gift2ResaleFiltersSearch));
        r32.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourceProvider));
        r32.setBackground(null);
        r2.addView(r32, LayoutHelper.createFrame(-1, -2.0f, 19, 43.0f, 0.0f, 8.0f, 0.0f));
        r32.addTextChangedListener(new AnonymousClass10(this, r10, r0));
        if (r11.size() <= 8) goto L12;
        r9.addView(r2, LayoutHelper.createLinear(-1, 44));
        r9.addGap();
    L12:
        if (this.list.notSelectedPatternAttributes.isEmpty() == true) goto L14;
        r9.add(R.drawable.msg_select, LocaleController.getString(R.string.SelectAll), new ResaleGiftsFragment$$ExternalSyntheticLambda16(this));
    L14:
        r9.addView(r0);
        r9.show();
    }

    private /* synthetic */ void lambda$createView$3(View r1) {
        this.list.notSelectedBackdropAttributes.clear();
        this.list.notSelectedModelAttributes.clear();
        this.list.notSelectedPatternAttributes.clear();
        this.list.reload();
    }

    private /* synthetic */ void lambda$createView$4() {
        this.list.setSorting(ResaleGiftsList.Sorting.BY_PRICE);
    }

    private /* synthetic */ void lambda$createView$5() {
        this.list.setSorting(ResaleGiftsList.Sorting.BY_DATE);
    }

    private /* synthetic */ void lambda$createView$6() {
        this.list.setSorting(ResaleGiftsList.Sorting.BY_NUMBER);
    }

    private /* synthetic */ void lambda$createView$7(CheckBox2 r3) {
        if (ResaleGiftsList.m14980$$Nest$fgetstarsOnly(this.list) == false) goto L6;
        ResaleGiftsList.m14981$$Nest$fputstarsOnly(this.list, false);
        r3.setChecked(false, true);
        this.list.reload();
        return;
    }

    private /* synthetic */ void lambda$createView$8(CheckBox2 r3) {
        if (ResaleGiftsList.m14980$$Nest$fgetstarsOnly(this.list) == true) goto L6;
        ResaleGiftsList.m14981$$Nest$fputstarsOnly(this.list, true);
        r3.setChecked(true, true);
        this.list.reload();
        return;
    }

    private /* synthetic */ void lambda$createView$9(CheckBox2 r4, View r5) {
        if (this.filtersShown == true) goto L5;
        return;
    L5:
        ItemOptions.makeOptions(this, this.sortButton).add(R.drawable.menu_sort_value, LocaleController.getString(ResaleGiftsList.Sorting.BY_PRICE.buttonStringResId), new ResaleGiftsFragment$$ExternalSyntheticLambda27(this)).add(R.drawable.menu_sort_date, LocaleController.getString(ResaleGiftsList.Sorting.BY_DATE.buttonStringResId), new ResaleGiftsFragment$$ExternalSyntheticLambda28(this)).add(R.drawable.menu_sort_number, LocaleController.getString(ResaleGiftsList.Sorting.BY_NUMBER.buttonStringResId), new ResaleGiftsFragment$$ExternalSyntheticLambda29(this)).addGap().addChecked(!ResaleGiftsList.m14980$$Nest$fgetstarsOnly(this.list), LocaleController.getString(R.string.GiftResaleFilterAllListings), new ResaleGiftsFragment$$ExternalSyntheticLambda30(this, r4)).addChecked(ResaleGiftsList.m14980$$Nest$fgetstarsOnly(this.list), LocaleController.getString(R.string.GiftResaleFilterForStarsOnly), new ResaleGiftsFragment$$ExternalSyntheticLambda31(this, r4)).setDrawScrim(false).setOnTopOfScrim().translate(0.0f, AndroidUtilities.dp(-8.0f)).show();
    }

    private /* synthetic */ void lambda$onItemClick$28(TL_stars.TL_starGiftUnique r10, long r11, boolean r13) {
        if (r11 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L10;
        this.list.gifts.remove(r10);
        updateList(false);
        if (r11 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L7;
        BulletinFactory.of(this).createSimpleBulletin(r10.getDocument(), LocaleController.getString(R.string.BoughtResoldGiftTitle), LocaleController.formatString(R.string.BoughtResoldGiftText, new Object[]{r10.title + " #" + LocaleController.formatNumber(r10.num, ',')})).hideAfterBottomSheet(false).show();
    L8:
        this.fireworksOverlay.start(true);
        return;
    L7:
        BulletinFactory.of(this).createSimpleBulletin(r10.getDocument(), LocaleController.getString(R.string.BoughtResoldGiftToTitle), LocaleController.formatString(R.string.BoughtResoldGiftToText, new Object[]{DialogObject.getShortName(this.currentAccount, r11)})).hideAfterBottomSheet(false).show();
        goto L8
    L10:
        Bundle r5 = new Bundle();
        if (r11 < 0) goto L13;
        r5.putLong("user_id", r11);
    L14:
        AnonymousClass13 r3 = new AnonymousClass13(this, r5, r10, r11);
        INavigationLayout r9 = this.parentLayout;
        if (r9 != null) goto L17;
    L25:
        presentFragment(r3, true, r13);
    L26:
        Utilities.Callback<Boolean> r92 = this.closeParentSheet;
        if (r92 == null) goto L30;
        r92.run(Boolean.valueOf(r13));
        return;
    L30:
        return;
    L17:
        if (r9.isSheet() == false) goto L25;
        Dialog r93 = this.parentDialog;
        if ((r93 instanceof BottomSheet) == false) goto L22;
        if (r13 == false) goto L22;
        ((BottomSheet) r93).skipDismissAnimation();
    L22:
        finishFragment();
        BaseFragment r94 = LaunchActivity.getSafeLastFragment();
        if (r94 == null) goto L26;
        r94.presentFragment(r3, false, r13);
        goto L26
    L13:
        r5.putLong("chat_id", -r11);
        goto L14
    }

    private void onItemClick(UItem r7, View r8, int r9, float r10, float r11) {
        Object r72 = r7.object;
        if ((r72 instanceof TL_stars.TL_starGiftUnique) == false) goto L6;
        TL_stars.TL_starGiftUnique r73 = (TL_stars.TL_starGiftUnique) r72;
        StarGiftSheet r0 = new StarGiftSheet(getContext(), this.currentAccount, this.dialogId, this.resourceProvider);
        r0.set(r73.slug, r73, this.list);
        r0.setOnBoughtGift(new ResaleGiftsFragment$$ExternalSyntheticLambda32(this));
        showDialog(r0);
        return;
    }

    private boolean onItemLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        return false;
    }

    private void setFiltersShown(boolean r8, boolean r9) {
        if (this.filtersShown != r8) goto L5;
        return;
    L5:
        this.filtersShown = r8;
        HorizontalScrollView r0 = this.filterScrollView;
        float r2 = 1.0f;
        int r3 = 0;
        float r5 = 0.0f;
        if (r9 == false) goto L25;
        r0.setVisibility(0);
        ViewPropertyAnimator r92 = this.filterScrollView.animate();
        if (r8 == false) goto L10;
        float r02 = 0.0f;
    L11:
        ViewPropertyAnimator r93 = r92.translationY(r02);
        if (r8 == true) goto L15;
        r2 = 0.0f;
    L15:
        ViewPropertyAnimator r94 = r93.alpha(r2);
        CubicBezierInterpolator r03 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r94.setInterpolator(r03).setDuration(420).setListener(new AnonymousClass11(this, r8)).start();
        ViewPropertyAnimator r95 = this.filtersDivider.animate();
        if (r8 == false) goto L18;
        float r4 = 0.0f;
    L19:
        r95.translationY(r4).setInterpolator(r03).setDuration(420).start();
        ViewPropertyAnimator r7 = this.listView.animate();
        if (r8 == true) goto L23;
        r5 = -AndroidUtilities.dp(39.0f);
    L23:
        r7.translationY(r5).setInterpolator(r03).setDuration(420).start();
        return;
    L18:
        r4 = -AndroidUtilities.dp(45.0f);
        goto L19
    L10:
        r02 = -AndroidUtilities.dp(45.0f);
        goto L11
    L25:
        if (r8 == true) goto L28;
        r3 = 8;
    L28:
        r0.setVisibility(r3);
        HorizontalScrollView r96 = this.filterScrollView;
        if (r8 == false) goto L31;
        float r04 = 0.0f;
    L32:
        r96.setTranslationY(r04);
        HorizontalScrollView r97 = this.filterScrollView;
        if (r8 == true) goto L36;
        r2 = 0.0f;
    L36:
        r97.setAlpha(r2);
        View r98 = this.filtersDivider;
        if (r8 == false) goto L39;
        float r05 = 0.0f;
    L40:
        r98.setTranslationY(r05);
        UniversalRecyclerView r72 = this.listView;
        if (r8 == true) goto L44;
        r5 = -AndroidUtilities.dp(39.0f);
    L44:
        r72.setTranslationY(r5);
        return;
    L39:
        r05 = -AndroidUtilities.dp(45.0f);
        goto L40
    L31:
        r04 = -AndroidUtilities.dp(45.0f);
        goto L32
    }

    private void updateEmptyView(boolean r5) {
        if (this.emptyViewVisible != r5) goto L5;
        return;
    L5:
        this.emptyViewVisible = r5;
        this.emptyView.setVisibility(0);
        ViewPropertyAnimator r0 = this.emptyView.animate();
        float r1 = 1.0f;
        if (r5 == false) goto L8;
        float r2 = 1.0f;
    L9:
        ViewPropertyAnimator r02 = r0.alpha(r2);
        if (r5 == false) goto L12;
        float r3 = 1.0f;
    L13:
        ViewPropertyAnimator r03 = r02.scaleX(r3);
        if (r5 == true) goto L17;
        r1 = 0.95f;
    L17:
        r03.scaleY(r1).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(320).setListener(new AnonymousClass12(this, r5)).start();
        return;
    L12:
        r3 = 0.95f;
        goto L13
    L8:
        r2 = 0.0f;
        goto L9
    }

    private void updateList(boolean r5) {
        if (this.list.getTotalCount() <= 12) goto L5;
        setFiltersShown(true, true);
    L5:
        UniversalRecyclerView r0 = this.listView;
        boolean r1 = false;
        if (r0 == null) goto L12;
        UniversalAdapter r02 = r0.adapter;
        if (r02 == null) goto L12;
        r02.update(true);
        if (r5 == false) goto L12;
        this.listView.scrollToPosition(0);
    L12:
        ActionBar r52 = this.actionBar;
        if (r52 == null) goto L19;
        r52.setTitle(this.gift_name);
        ActionBar r53 = this.actionBar;
        if (this.list.getTotalCount() > 0) goto L17;
        String r03 = LocaleController.getString(R.string.Gift2ResaleNoCount);
    L18:
        r53.setSubtitle(r03);
        goto L19
    L17:
        r03 = LocaleController.formatPluralStringComma("Gift2ListingsCount", this.list.getTotalCount());
    L19:
        Filter r54 = this.sortButton;
        if (r54 == null) goto L23;
        r54.setSorting(this.list.getSorting());
    L23:
        if (this.modelButton == null) goto L33;
        int r55 = this.list.modelAttributes.size() - this.list.notSelectedModelAttributes.size();
        Filter r04 = this.modelButton;
        if (r55 > 0) goto L27;
    L30:
        String r56 = LocaleController.getString(R.string.Gift2ResaleFilterModel);
    L31:
        r04.setValue(r56);
        goto L33
    L27:
        if (r55 == this.list.modelAttributes.size()) goto L30;
        r56 = LocaleController.formatPluralStringComma("Gift2ResaleFilterModels", r55);
    L33:
        if (this.backdropButton == null) goto L43;
        int r57 = this.list.backdropAttributes.size() - this.list.notSelectedBackdropAttributes.size();
        Filter r05 = this.backdropButton;
        if (r57 > 0) goto L37;
    L40:
        String r58 = LocaleController.getString(R.string.Gift2ResaleFilterBackdrop);
    L41:
        r05.setValue(r58);
        goto L43
    L37:
        if (r57 == this.list.backdropAttributes.size()) goto L40;
        r58 = LocaleController.formatPluralStringComma("Gift2ResaleFilterBackdrops", r57);
    L43:
        if (this.patternButton == null) goto L53;
        int r59 = this.list.patternAttributes.size() - this.list.notSelectedPatternAttributes.size();
        Filter r06 = this.patternButton;
        if (r59 > 0) goto L47;
    L50:
        String r510 = LocaleController.getString(R.string.Gift2ResaleFilterSymbol);
    L51:
        r06.setValue(r510);
        goto L53
    L47:
        if (r59 == this.list.patternAttributes.size()) goto L50;
        r510 = LocaleController.formatPluralStringComma("Gift2ResaleFilterSymbols", r59);
    L53:
        if (isLoadingVisible() == false) goto L55;
        this.list.load();
    L55:
        ResaleGiftsList r511 = this.list;
        if (r511.loading == true) goto L60;
        if (r511.getTotalCount() > 0) goto L60;
    L66:
        this.animatorClearFiltersButtonVisible.setValue(r1, true);
        return;
    L60:
        if (this.list.notSelectedModelAttributes.isEmpty() == true) goto L62;
    L65:
        r1 = true;
        goto L66
    L62:
        if (this.list.notSelectedBackdropAttributes.isEmpty() == false) goto L65;
        if (this.list.notSelectedPatternAttributes.isEmpty() == true) goto L66;
        goto L65
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r29) {
        BlurredBackgroundSourceColor r0 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceColor = r0;
        int r7 = Theme.key_windowBackgroundWhite;
        r0.setColor(getThemedColor(r7));
        this.iBlur3Factory = new BlurredBackgroundDrawableViewFactory(this.iBlur3SourceColor);
        ActionBar r02 = this.actionBar;
        BackDrawable r2 = new BackDrawable(false);
        this.backDrawable = r2;
        r02.setBackButtonDrawable(r2);
        this.backDrawable.setAnimationTime(240.0f);
        this.actionBar.setCastShadows(false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.actionBar.setTitle(this.gift_name);
        this.actionBar.setBackgroundColor(getThemedColor(r7));
        ActionBar r03 = this.actionBar;
        int r22 = Theme.key_windowBackgroundWhiteBlackText;
        r03.setItemsColor(getThemedColor(r22), false);
        this.actionBar.setItemsColor(getThemedColor(r22), true);
        this.actionBar.setItemsBackgroundColor(getThemedColor(Theme.key_actionBarActionModeDefaultSelector), false);
        this.actionBar.setTitleColor(getThemedColor(r22));
        this.actionBar.setSubtitleColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText2));
        AnonymousClass2 r10 = new AnonymousClass2(this, r29);
        int r12 = Theme.blendOver(Theme.getColor(r7, this.resourceProvider), Theme.multAlpha(Theme.getColor(r22, this.resourceProvider), 0.04f));
        r10.setBackgroundColor(r12);
        this.fragmentView = r10;
        StarsIntroActivity.StarsBalanceView r04 = new StarsIntroActivity.StarsBalanceView(r29, this.currentAccount, this.resourceProvider);
        r04.withTon();
        ScaleStateListAnimator.apply(r04);
        r04.setOnClickListener(new ResaleGiftsFragment$$ExternalSyntheticLambda1(this, r04));
        this.actionBar.addView(r04, LayoutHelper.createFrame(-2, -2.0f, 85, 0.0f, 0.0f, 4.0f, 0.0f));
        AnonymousClass3 r05 = new AnonymousClass3(this, this, new ResaleGiftsFragment$$ExternalSyntheticLambda3(this), new ResaleGiftsFragment$$ExternalSyntheticLambda4(this), new ResaleGiftsFragment$$ExternalSyntheticLambda5(this));
        this.listView = r05;
        r05.adapter.setApplyBackground(false);
        this.listView.setSpanCount(3);
        this.listView.addOnScrollListener(new AnonymousClass4(this));
        this.listView.setPadding(0, AndroidUtilities.dp(45.0f), 0, AndroidUtilities.dp(101.0f));
        this.listView.setClipToPadding(false);
        r10.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f, 119, 7.33f, 0.0f, 7.33f, -45.0f));
        r10.addView(this.actionBar);
        LargeEmptyView r06 = new LargeEmptyView(r29, new ResaleGiftsFragment$$ExternalSyntheticLambda6(this), this.resourceProvider);
        this.emptyView = r06;
        this.emptyViewVisible = false;
        r06.setAlpha(0.0f);
        this.emptyView.setScaleX(0.95f);
        this.emptyView.setScaleY(0.95f);
        this.emptyView.setVisibility(8);
        r10.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, -45.0f));
        LinearLayout r07 = new LinearLayout(r29);
        this.filtersContainer = r07;
        r07.setPadding(AndroidUtilities.dp(11.0f), 0, AndroidUtilities.dp(11.0f), 0);
        this.filtersContainer.setOrientation(0);
        HorizontalScrollView r08 = new HorizontalScrollView(r29);
        this.filterScrollView = r08;
        r08.setHorizontalScrollBarEnabled(false);
        this.filterScrollView.addView(this.filtersContainer);
        this.filterScrollView.setBackgroundColor(r12);
        this.filterScrollView.setClipChildren(false);
        r10.addView(this.filterScrollView, LayoutHelper.createFrame(-1, 47, 55));
        View r09 = new View(r29);
        this.filtersDivider = r09;
        r09.setBackgroundColor(getThemedColor(Theme.key_divider));
        this.filtersDivider.setAlpha(0.0f);
        r10.addView(this.filtersDivider, LayoutHelper.createFrame(-1.0f, 2.0f / AndroidUtilities.density, 55));
        LinearLayout r010 = new LinearLayout(r29);
        r010.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(15.0f), 0);
        r010.setOrientation(0);
        CheckBox2 r122 = new CheckBox2(r29, 24, this.resourceProvider);
        r122.setColor(Theme.key_radioBackgroundChecked, Theme.key_checkboxDisabled, Theme.key_checkboxCheck);
        r122.setDrawUnchecked(true);
        r122.setChecked(false, false);
        r122.setDrawBackgroundAsArc(10);
        r122.setTranslationX(AndroidUtilities.dp(4.0f));
        r122.setScaleX(0.8f);
        r122.setScaleY(0.8f);
        r010.addView(r122, LayoutHelper.createLinear(26, 26, 16));
        TextView r23 = new TextView(r29);
        r23.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourceProvider));
        r23.setTextSize(1, 14.0f);
        r23.setText(LocaleController.getString(R.string.GiftResaleStarsOnly));
        r010.addView(r23, LayoutHelper.createLinear(-2, -2, 16, 9, 0, 0, 0));
        int r13 = AndroidUtilities.dp(18.0f);
        int r14 = getThemedColor(r7);
        int r15 = Theme.key_featuredStickers_addButton;
        r010.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r13, 0, Theme.blendOver(r14, Theme.multAlpha(getThemedColor(r15), 0.1f))));
        FrameLayout r24 = new FrameLayout(r29);
        this.onlyStarsContainer = r24;
        r24.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        FrameLayout r25 = this.onlyStarsContainer;
        r25.setBackground(this.iBlur3Factory.create(r25).setColorProvider(BlurredBackgroundProviderImpl.shadow(this.resourceProvider)).setPadding(AndroidUtilities.dp(8.0f)).setRadius(AndroidUtilities.dp(18.0f)));
        this.onlyStarsContainer.setOnClickListener(new ResaleGiftsFragment$$ExternalSyntheticLambda7(this, r122));
        this.onlyStarsContainer.addView(r010, LayoutHelper.createFrame(-2, -1.0f));
        ScaleStateListAnimator.apply(this.onlyStarsContainer, 0.04f, 1.5f);
        r10.addView(this.onlyStarsContainer, LayoutHelper.createFrame(-2, 52.0f, 81, 0.0f, 0.0f, 0.0f, AndroidUtilities.navigationBarHeight / AndroidUtilities.density));
        StarsController r011 = StarsController.getTonInstance(this.currentAccount);
        if (r011.balanceAvailable() == true) goto L5;
    L7:
        FrameLayout r012 = new FrameLayout(r29);
        this.clearFiltersContainer = r012;
        r012.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        FrameLayout r013 = this.clearFiltersContainer;
        r013.setBackground(this.iBlur3Factory.create(r013).setColorProvider(BlurredBackgroundProviderImpl.shadow(this.resourceProvider)).setPadding(AndroidUtilities.dp(8.0f)).setRadius(AndroidUtilities.dp(22.0f)));
        r10.addView(this.clearFiltersContainer, LayoutHelper.createFrame(-2, 60.0f, 81, 0.0f, 0.0f, 0.0f, AndroidUtilities.navigationBarHeight / AndroidUtilities.density));
        this.clearFiltersButton = new TextView(r29);
        SpannableStringBuilder r014 = new SpannableStringBuilder("x");
        r014.setSpan(new ColoredImageSpan(R.drawable.msg_clearcache), 0, 1, 33);
        r014.append(" ").append(LocaleController.getString(R.string.Gift2ResaleFiltersClear));
        this.clearFiltersButton.setText(r014);
        this.clearFiltersButton.setTextColor(getThemedColor(r15));
        this.clearFiltersButton.setTypeface(AndroidUtilities.bold());
        this.clearFiltersButton.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), 0);
        this.clearFiltersButton.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(22.0f), 0, Theme.blendOver(getThemedColor(r7), Theme.multAlpha(getThemedColor(r15), 0.1f))));
        this.clearFiltersButton.setGravity(17);
        this.clearFiltersContainer.setOnClickListener(new ResaleGiftsFragment$$ExternalSyntheticLambda8(this));
        this.clearFiltersContainer.addView(this.clearFiltersButton, LayoutHelper.createFrame(-2, -1.0f));
        this.clearFiltersContainer.setVisibility(8);
        ScaleStateListAnimator.apply(this.clearFiltersContainer, 0.05f, 1.5f);
        Filter r015 = new Filter(r29, this.resourceProvider);
        this.sortButton = r015;
        r015.setSorting(this.list.getSorting());
        this.filtersContainer.addView(this.sortButton, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 6, 0));
        this.sortButton.setOnClickListener(new ResaleGiftsFragment$$ExternalSyntheticLambda9(this, r122));
        Filter r016 = new Filter(r29, this.resourceProvider);
        this.modelButton = r016;
        r016.setValue(LocaleController.getString(R.string.Gift2AttributeModel));
        this.filtersContainer.addView(this.modelButton, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 6, 0));
        this.modelButton.setOnClickListener(new ResaleGiftsFragment$$ExternalSyntheticLambda10(this, r29));
        Filter r017 = new Filter(r29, this.resourceProvider);
        this.backdropButton = r017;
        r017.setValue(LocaleController.getString(R.string.Gift2AttributeBackdrop));
        this.filtersContainer.addView(this.backdropButton, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 6, 0));
        this.backdropButton.setOnClickListener(new ResaleGiftsFragment$$ExternalSyntheticLambda11(this, r29));
        Filter r018 = new Filter(r29, this.resourceProvider);
        this.patternButton = r018;
        r018.setValue(LocaleController.getString(R.string.Gift2AttributeSymbol));
        this.filtersContainer.addView(this.patternButton, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 0, 0));
        this.patternButton.setOnClickListener(new ResaleGiftsFragment$$ExternalSyntheticLambda2(this, r29));
        FireworksOverlay r019 = new FireworksOverlay(getContext());
        this.fireworksOverlay = r019;
        r10.addView(r019, LayoutHelper.createFrame(-1, -1.0f));
        setFiltersShown(false, false);
        return r10;
    L5:
        if (r011.getBalanceAmount().isZero() == true) goto L7;
        this.onlyStarsContainer.setVisibility(8);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (getLastStoryViewer() != null) goto L5;
    L7:
        int r0 = Theme.getColor(Theme.key_windowBackgroundWhite);
        if (this.actionBar.isActionModeShowed() == false) goto L11;
        r0 = Theme.getColor(Theme.key_actionBarActionModeDefault);
    L11:
        if (ColorUtils.calculateLuminance(r0) <= 0.699999988079071d) goto L14;
        return true;
    L14:
        return false;
    L5:
        if (getLastStoryViewer().isShown() == false) goto L7;
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 0) goto L5;
        this.onlyStarsContainer.setTranslationY((-AndroidUtilities.dp(52.0f)) * r2);
        FragmentFloatingButton.setAnimatedVisibility(this.clearFiltersContainer, r2);
        return;
    }

    public ResaleGiftsFragment setCloseParentSheet(Utilities.Callback<Boolean> r1) {
        this.closeParentSheet = r1;
        return this;
    }
}
