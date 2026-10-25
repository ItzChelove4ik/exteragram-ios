package com.exteragram.messenger.drawer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.utils.ui.AccountsUiHelper;
import java.util.ArrayList;
import java.util.Collections;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumGradient;

/* loaded from: classes.dex */
public class DrawerAccountPickerView extends FrameLayout {
    private static final int COLOR_KEY_ACCENT = 0;
    private static final int COLOR_KEY_ADD_ICON = 0;
    private static final int COLOR_KEY_BACKGROUND = 0;
    private static final int COLOR_KEY_SELECTOR = 0;
    private static final int COLOR_KEY_STATUS = 0;
    private static final int COLOR_KEY_SURFACE = 0;
    private static final int COLOR_KEY_TEXT = 0;
    private final ArrayList<Integer> accounts;
    private final AccountAdapter adapter;
    private BadgeDTO badgeOverride;
    private final Paint bgPaint;
    private final RectF bgRect;
    private LinearGradient bottomGradient;
    private final Paint bottomGradientPaint;
    private final Paint clipMaskPaint;
    private final FrameLayout clipWrapper;
    private final float cornerRadius;
    private int currentAnimatedHeight;
    private View draggingItemView;
    private ValueAnimator expandAnimator;
    private boolean expanded;
    private final ItemTouchHelper itemTouchHelper;
    private int lastHeight;
    private OnAccountLongClick onAccountLongClick;
    private Runnable onAccountSelected;
    private final RecyclerView recyclerView;
    private LinearGradient topGradient;
    private final Paint topGradientPaint;

    /* renamed from: com.exteragram.messenger.drawer.DrawerAccountPickerView$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ DrawerAccountPickerView this$0;

        public AnonymousClass1(DrawerAccountPickerView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r25) {
            DrawerAccountPickerView.m1259$$Nest$fgetbgPaint(this.this$0).setColor(Theme.getColor(DrawerAccountPickerView.m1286$$Nest$sfgetCOLOR_KEY_BACKGROUND()));
            DrawerAccountPickerView.m1260$$Nest$fgetbgRect(this.this$0).set(0.0f, 0.0f, getWidth(), getHeight());
            r25.drawRoundRect(DrawerAccountPickerView.m1260$$Nest$fgetbgRect(this.this$0), DrawerAccountPickerView.m1265$$Nest$fgetcornerRadius(this.this$0), DrawerAccountPickerView.m1265$$Nest$fgetcornerRadius(this.this$0), DrawerAccountPickerView.m1259$$Nest$fgetbgPaint(this.this$0));
            int r8 = r25.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null);
            super.dispatchDraw(r25);
            if (DrawerAccountPickerView.m1275$$Nest$fgettopGradient(this.this$0) != null) goto L5;
        L6:
            DrawerAccountPickerView.m1281$$Nest$fputlastHeight(this.this$0, getHeight());
            DrawerAccountPickerView r1 = this.this$0;
            Shader.TileMode r17 = Shader.TileMode.CLAMP;
            DrawerAccountPickerView.m1282$$Nest$fputtopGradient(r1, new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(16.0f), new int[]{-16777216, 0}, null, r17));
            DrawerAccountPickerView.m1276$$Nest$fgettopGradientPaint(this.this$0).setShader(DrawerAccountPickerView.m1275$$Nest$fgettopGradient(this.this$0));
            DrawerAccountPickerView.m1277$$Nest$fputbottomGradient(this.this$0, new LinearGradient(0.0f, getHeight(), 0.0f, getHeight() - AndroidUtilities.dp(16.0f), new int[]{-16777216, 0}, null, r17));
            DrawerAccountPickerView.m1262$$Nest$fgetbottomGradientPaint(this.this$0).setShader(DrawerAccountPickerView.m1261$$Nest$fgetbottomGradient(this.this$0));
        L7:
            int r12 = AndroidUtilities.dp(16.0f);
            int r3 = DrawerAccountPickerView.m1274$$Nest$fgetrecyclerView(this.this$0).computeVerticalScrollOffset();
            int r2 = Math.max(0, (DrawerAccountPickerView.m1274$$Nest$fgetrecyclerView(this.this$0).computeVerticalScrollRange() - DrawerAccountPickerView.m1274$$Nest$fgetrecyclerView(this.this$0).computeVerticalScrollExtent()) - r3);
            float r13 = r12;
            float r32 = Math.min(1.0f, Math.max(0.0f, r3 / r13));
            float r10 = Math.min(1.0f, r2 / r13);
            if (r32 <= 0.0f) goto L11;
            DrawerAccountPickerView.m1276$$Nest$fgettopGradientPaint(this.this$0).setAlpha((int) (r32 * 255.0f));
            r25.drawRect(0.0f, 0.0f, getWidth(), AndroidUtilities.dp(16.0f), DrawerAccountPickerView.m1276$$Nest$fgettopGradientPaint(this.this$0));
        L11:
            if (r10 <= 0.0f) goto L13;
            DrawerAccountPickerView.m1262$$Nest$fgetbottomGradientPaint(this.this$0).setAlpha((int) (r10 * 255.0f));
            Canvas r14 = r25;
            r14.drawRect(0.0f, getHeight() - AndroidUtilities.dp(16.0f), getWidth(), getHeight(), DrawerAccountPickerView.m1262$$Nest$fgetbottomGradientPaint(this.this$0));
        L14:
            r14.drawRoundRect(DrawerAccountPickerView.m1260$$Nest$fgetbgRect(this.this$0), DrawerAccountPickerView.m1265$$Nest$fgetcornerRadius(this.this$0), DrawerAccountPickerView.m1265$$Nest$fgetcornerRadius(this.this$0), DrawerAccountPickerView.m1263$$Nest$fgetclipMaskPaint(this.this$0));
            r14.restoreToCount(r8);
            return;
        L13:
            r14 = r25;
            goto L14
        L5:
            if (getHeight() == DrawerAccountPickerView.m1271$$Nest$fgetlastHeight(this.this$0)) goto L7;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            int r0 = DrawerAccountPickerView.m1257$$Nest$fgetadapter(this.this$0).getItemCount();
            if (r0 > 6) goto L5;
            float r02 = r0;
        L6:
            int r03 = (int) ((AndroidUtilities.dp(48.0f) * r02) + (AndroidUtilities.dp(4.0f) * 2));
            int r1 = View.MeasureSpec.getSize(r5);
            measureChildren(View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r03, TLObject.FLAG_30));
            if (DrawerAccountPickerView.m1266$$Nest$fgetcurrentAnimatedHeight(this.this$0) < 0) goto L10;
            setMeasuredDimension(r1, DrawerAccountPickerView.m1266$$Nest$fgetcurrentAnimatedHeight(this.this$0));
            return;
        L10:
            int r12 = View.MeasureSpec.getSize(r6);
            if (View.MeasureSpec.getMode(r6) == 0) goto L13;
            if (r12 > r03) goto L13;
        L14:
            super.onMeasure(r5, r6);
            return;
        L13:
            r6 = View.MeasureSpec.makeMeasureSpec(r03, Integer.MIN_VALUE);
            goto L14
        L5:
            r02 = 5.5f;
            goto L6
        }
    }

    /* renamed from: com.exteragram.messenger.drawer.DrawerAccountPickerView$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.ItemDecoration {
        final /* synthetic */ DrawerAccountPickerView this$0;
        final /* synthetic */ int val$itemInset;

        public AnonymousClass2(DrawerAccountPickerView r1, int r2) {
            this.this$0 = r1;
            this.val$itemInset = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            int r22 = r3.getChildAdapterPosition(r2);
            int r32 = r4.getItemCount();
            if (r22 >= 0) goto L5;
            return;
        L5:
            if (r22 >= (r32 - 1)) goto L9;
            r1.bottom = this.val$itemInset;
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.drawer.DrawerAccountPickerView$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ DrawerAccountPickerView this$0;

        public AnonymousClass3(DrawerAccountPickerView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            DrawerAccountPickerView.m1264$$Nest$fgetclipWrapper(this.this$0).invalidate();
        }
    }

    /* renamed from: com.exteragram.messenger.drawer.DrawerAccountPickerView$4, reason: invalid class name */
    public class AnonymousClass4 extends ItemTouchHelper.Callback {
        final /* synthetic */ DrawerAccountPickerView this$0;
        final /* synthetic */ RecyclerView.ChildDrawingOrderCallback val$dragDrawingOrderCallback;

        public AnonymousClass4(DrawerAccountPickerView r1, RecyclerView.ChildDrawingOrderCallback r2) {
            this.this$0 = r1;
            this.val$dragDrawingOrderCallback = r2;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView r3, RecyclerView.ViewHolder r4) {
            r4.itemView.setTranslationX(0.0f);
            r4.itemView.setTranslationY(0.0f);
            r4.itemView.setPressed(false);
            if (DrawerAccountPickerView.m1267$$Nest$fgetdraggingItemView(this.this$0) != r4.itemView) goto L5;
            DrawerAccountPickerView.m1279$$Nest$fputdraggingItemView(this.this$0, null);
        L5:
            r3.setChildDrawingOrderCallback(null);
            r3.invalidate();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r1, RecyclerView.ViewHolder r2) {
            if (r2.getAdapterPosition() < DrawerAccountPickerView.m1256$$Nest$fgetaccounts(this.this$0).size()) goto L6;
            return 0;
        L6:
            return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onChildDraw(Canvas r1, RecyclerView r2, RecyclerView.ViewHolder r3, float r4, float r5, int r6, boolean r7) {
            r3.itemView.setTranslationX(r4);
            r3.itemView.setTranslationY(r5);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r1, RecyclerView.ViewHolder r2, RecyclerView.ViewHolder r3) {
            int r12 = r2.getAdapterPosition();
            int r22 = r3.getAdapterPosition();
            if (r12 < DrawerAccountPickerView.m1256$$Nest$fgetaccounts(this.this$0).size()) goto L5;
            return false;
        L5:
            if (r22 >= DrawerAccountPickerView.m1256$$Nest$fgetaccounts(this.this$0).size()) goto L11;
            DrawerAccountPickerView.m1257$$Nest$fgetadapter(this.this$0).swapElements(r12, r22);
            return true;
        L11:
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r2, int r3) {
            if (r3 != 2) goto L7;
            if (r2 == null) goto L8;
            DrawerAccountPickerView.m1279$$Nest$fputdraggingItemView(this.this$0, r2.itemView);
            DrawerAccountPickerView.m1267$$Nest$fgetdraggingItemView(this.this$0).setPressed(false);
            DrawerAccountPickerView.m1267$$Nest$fgetdraggingItemView(this.this$0).jumpDrawablesToCurrentState();
            DrawerAccountPickerView.m1274$$Nest$fgetrecyclerView(this.this$0).setChildDrawingOrderCallback(this.val$dragDrawingOrderCallback);
            DrawerAccountPickerView.m1274$$Nest$fgetrecyclerView(this.this$0).invalidate();
            return;
        L8:
            return;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
        }
    }

    /* renamed from: com.exteragram.messenger.drawer.DrawerAccountPickerView$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        private boolean cancelled;
        final /* synthetic */ DrawerAccountPickerView this$0;

        public AnonymousClass5(DrawerAccountPickerView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.cancelled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.cancelled == false) goto L5;
            return;
        L5:
            if (DrawerAccountPickerView.m1268$$Nest$fgetexpandAnimator(this.this$0) != r2) goto L14;
            DrawerAccountPickerView.m1280$$Nest$fputexpandAnimator(this.this$0, null);
            DrawerAccountPickerView.m1278$$Nest$fputcurrentAnimatedHeight(this.this$0, -1);
            boolean r22 = DrawerAccountPickerView.m1269$$Nest$fgetexpanded(this.this$0);
            DrawerAccountPickerView r0 = this.this$0;
            if (r22 == true) goto L11;
            r0.setVisibility(8);
            return;
        L11:
            ViewGroup.LayoutParams r23 = DrawerAccountPickerView.m1264$$Nest$fgetclipWrapper(r0).getLayoutParams();
            r23.height = -2;
            DrawerAccountPickerView.m1264$$Nest$fgetclipWrapper(this.this$0).setLayoutParams(r23);
            return;
        }
    }

    public class AccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        final /* synthetic */ DrawerAccountPickerView this$0;

        /* renamed from: com.exteragram.messenger.drawer.DrawerAccountPickerView$AccountAdapter$1, reason: invalid class name */
        /* loaded from: classes4.dex */
        public class AnonymousClass1 extends RecyclerView.ViewHolder {
            final /* synthetic */ AccountAdapter this$1;

            public AnonymousClass1(AccountAdapter r1, View r2) {
                this.this$1 = r1;
                super(r2);
            }
        }

        /* renamed from: com.exteragram.messenger.drawer.DrawerAccountPickerView$AccountAdapter$2, reason: invalid class name */
        /* loaded from: classes4.dex */
        public class AnonymousClass2 extends RecyclerView.ViewHolder {
            final /* synthetic */ AccountAdapter this$1;

            public AnonymousClass2(AccountAdapter r1, View r2) {
                this.this$1 = r1;
                super(r2);
            }
        }

        public static /* synthetic */ void $r8$lambda$AVhhPi5pUbja8Z2BuGsXETG7Fio(AccountAdapter r0, Integer r1, View r2) {
            r0.lambda$bindAccountViewHolder$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$wr_S9TybOs2xLArPfiMkzMyUGZ0(AccountAdapter r0, View r1) {
            r0.lambda$bindAddAccountViewHolder$2(r1);
        }

        /* renamed from: $r8$lambda$yFLt-7OL7WaHMiVyWxwyCvKY7j8, reason: not valid java name */
        public static /* synthetic */ boolean m1291$r8$lambda$yFLt7OL7WaHMiVyWxwyCvKY7j8(AccountAdapter r0, Integer r1, RecyclerView.ViewHolder r2, View r3) {
            return r0.lambda$bindAccountViewHolder$1(r1, r2, r3);
        }

        private AccountAdapter(DrawerAccountPickerView r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ AccountAdapter(DrawerAccountPickerView r1, DrawerAccountPickerViewIA r2) {
            this(r1);
        }

        private void bindAccountViewHolder(RecyclerView.ViewHolder r5, int r6) {
            View r0 = r5.itemView;
            if ((r0 instanceof AccountRowView) == false) goto L10;
            AccountRowView r02 = (AccountRowView) r0;
            Integer r62 = (Integer) DrawerAccountPickerView.m1256$$Nest$fgetaccounts(this.this$0).get(r6);
            int r1 = r62.intValue();
            if (r62.intValue() != UserConfig.selectedAccount) goto L7;
            BadgeDTO r2 = DrawerAccountPickerView.m1258$$Nest$fgetbadgeOverride(this.this$0);
        L8:
            r02.bind(r1, r2);
            r02.setOnClickListener(new DrawerAccountPickerView$AccountAdapter$$ExternalSyntheticLambda1(this, r62));
            r02.setOnLongClickListener(new DrawerAccountPickerView$AccountAdapter$$ExternalSyntheticLambda2(this, r62, r5));
            return;
        L7:
            r2 = null;
            goto L8
        }

        private void bindAddAccountViewHolder(RecyclerView.ViewHolder r3) {
            View r0 = r3.itemView;
            if ((r0 instanceof AddAccountView) == false) goto L5;
            ((AddAccountView) r0).updateColors();
        L5:
            r3.itemView.setOnClickListener(new DrawerAccountPickerView$AccountAdapter$$ExternalSyntheticLambda0(this));
        }

        private /* synthetic */ void lambda$bindAccountViewHolder$0(Integer r2, View r3) {
            if (r2.intValue() != UserConfig.selectedAccount) goto L5;
            return;
        L5:
            if (DrawerAccountPickerView.m1273$$Nest$fgetonAccountSelected(this.this$0) == null) goto L7;
            DrawerAccountPickerView.m1273$$Nest$fgetonAccountSelected(this.this$0).run();
        L7:
            AccountsUiHelper.switchTo(r2.intValue());
        }

        private /* synthetic */ boolean lambda$bindAccountViewHolder$1(Integer r4, RecyclerView.ViewHolder r5, View r6) {
            int r0 = r4.intValue();
            int r1 = UserConfig.selectedAccount;
            DrawerAccountPickerView r2 = this.this$0;
            if (r0 != r1) goto L6;
            DrawerAccountPickerView.m1270$$Nest$fgetitemTouchHelper(r2).startDrag(r5);
            return true;
        L6:
            if (DrawerAccountPickerView.m1272$$Nest$fgetonAccountLongClick(r2) == null) goto L11;
            DrawerAccountPickerView.m1272$$Nest$fgetonAccountLongClick(this.this$0).onLongClick(r4.intValue(), r6);
            return true;
        L11:
            return true;
        }

        private /* synthetic */ void lambda$bindAddAccountViewHolder$2(View r3) {
            if (DrawerAccountPickerView.m1273$$Nest$fgetonAccountSelected(this.this$0) == null) goto L5;
            DrawerAccountPickerView.m1273$$Nest$fgetonAccountSelected(this.this$0).run();
        L5:
            AndroidUtilities.runOnUIThread(new DrawerAccountPickerView$AccountAdapter$$ExternalSyntheticLambda3(), 150);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return DrawerAccountPickerView.m1256$$Nest$fgetaccounts(this.this$0).size() + (DrawerAccountPickerView.m1283$$Nest$mcanAddAccount(this.this$0) ? 1 : 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 >= DrawerAccountPickerView.m1256$$Nest$fgetaccounts(this.this$0).size()) goto L6;
            return 0;
        L6:
            return 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
            int r0 = getItemViewType(r3);
            if (r0 != 0) goto L5;
            bindAccountViewHolder(r2, r3);
            return;
        L5:
            if (r0 == 1) goto L7;
            return;
        L7:
            bindAddAccountViewHolder(r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 == 1) goto L7;
            return new AnonymousClass2(this, new AccountRowView(r2.getContext()));
        L7:
            return new AnonymousClass1(this, new AddAccountView(r2.getContext()));
        }

        public void swapElements(int r5, int r6) {
            if (r5 < 0) goto L11;
            if (r6 >= 0) goto L5;
            return;
        L5:
            if (r5 < DrawerAccountPickerView.m1256$$Nest$fgetaccounts(this.this$0).size()) goto L7;
            return;
        L7:
            if (r6 >= DrawerAccountPickerView.m1256$$Nest$fgetaccounts(this.this$0).size()) goto L14;
            UserConfig r0 = UserConfig.getInstance(((Integer) DrawerAccountPickerView.m1256$$Nest$fgetaccounts(this.this$0).get(r5)).intValue());
            UserConfig r1 = UserConfig.getInstance(((Integer) DrawerAccountPickerView.m1256$$Nest$fgetaccounts(this.this$0).get(r6)).intValue());
            int r2 = r0.loginTime;
            r0.loginTime = r1.loginTime;
            r1.loginTime = r2;
            r0.saveConfig(false);
            r1.saveConfig(false);
            Collections.swap(DrawerAccountPickerView.m1256$$Nest$fgetaccounts(this.this$0), r5, r6);
            notifyItemMoved(r5, r6);
            return;
        L14:
            return;
        }
    }

    /* loaded from: classes4.dex */
    public static class AccountRowView extends FrameLayout {
        private final AvatarDrawable avatarDrawable;
        private final RectF avatarRect;
        private final BackupImageView avatarView;
        private final Paint checkPaint;
        private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable exteraBadgeDrawable;
        private final SimpleTextView nameView;
        private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable premiumStatusDrawable;
        private boolean selected;
        private final DrawerAccountUnreadBadge unreadBadge;

        public AccountRowView(Context r12) {
            super(r12);
            Paint r0 = new Paint(1);
            this.checkPaint = r0;
            this.avatarRect = new RectF();
            setWillNotDraw(false);
            setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(44.0f)));
            setBackground(DrawerAccountPickerView.m1289$$Nest$smcreateAccountItemRippleDrawable());
            AvatarDrawable r3 = new AvatarDrawable();
            this.avatarDrawable = r3;
            r3.setTextSize(AndroidUtilities.dp(20.0f));
            BackupImageView r32 = new BackupImageView(r12);
            this.avatarView = r32;
            updateAvatarRadius();
            addView(r32, LayoutHelper.createFrame(34, 34.0f, 19, 8.0f, 0.0f, 0.0f, 0.0f));
            SimpleTextView r33 = new SimpleTextView(r12);
            this.nameView = r33;
            r33.setTextSize(15);
            r33.setTypeface(AndroidUtilities.bold());
            r33.setTextColor(Theme.getColor(DrawerAccountPickerView.m1288$$Nest$sfgetCOLOR_KEY_TEXT()));
            r33.setGravity(19);
            r33.setEllipsizeByGradient(true);
            r33.setCanHideRightDrawable(false);
            r33.setRightDrawableOutside(true);
            addView(r33, LayoutHelper.createFrame(-1, -1.0f, 3, 54.0f, 0.0f, 12.0f, 0.0f));
            this.premiumStatusDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r33, AndroidUtilities.dp(18.0f));
            this.exteraBadgeDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r33, AndroidUtilities.dp(18.0f));
            this.unreadBadge = new DrawerAccountUnreadBadge();
            r0.setStyle(Paint.Style.STROKE);
            r0.setStrokeWidth(AndroidUtilities.dp(1.67f));
            r0.setStrokeCap(Paint.Cap.ROUND);
            r0.setStrokeJoin(Paint.Join.ROUND);
        }

        private void applyNameDrawables(Drawable r3, Drawable r4) {
            if (r3 != null) goto L4;
        L6:
            this.nameView.setRightDrawable(r3);
            this.nameView.setRightDrawable2(r4);
            return;
        L4:
            if (r3 != this.nameView.getRightDrawable2()) goto L6;
            this.nameView.setRightDrawable2(null);
            goto L6
        }

        private void clearBadgeDrawables() {
            this.exteraBadgeDrawable.set(null, false);
            this.exteraBadgeDrawable.setParticles(false, false);
            this.exteraBadgeDrawable.setColor(null);
        }

        private void updateAvatarRadius() {
            this.avatarView.setRoundRadius(ExteraConfig.getAvatarCorners(34.0f));
        }

        private Drawable updateBadgeDrawable(BadgeDTO r4, int r5) {
            if (r4 != null) goto L5;
            clearBadgeDrawables();
            return null;
        L5:
            this.exteraBadgeDrawable.set(r4.getDocumentId(), false);
            this.exteraBadgeDrawable.setParticles(true, false);
            this.exteraBadgeDrawable.setColor(Integer.valueOf(r5));
            return this.exteraBadgeDrawable;
        }

        public void bind(int r9, BadgeDTO r10) {
            TLRPC.User r0 = UserConfig.getInstance(r9).getCurrentUser();
            if (r0 != null) goto L5;
            return;
        L5:
            updateAvatarRadius();
            this.avatarDrawable.setInfo(r9, r0);
            this.nameView.setTextColor(Theme.getColor(DrawerAccountPickerView.m1288$$Nest$sfgetCOLOR_KEY_TEXT()));
            this.nameView.setText(ContactsController.formatName(r0.first_name, r0.last_name));
            this.avatarView.getImageReceiver().setCurrentAccount(r9);
            this.avatarView.setForUserOrChat(r0, this.avatarDrawable);
            this.premiumStatusDrawable.setCurrentAccount(r9);
            this.exteraBadgeDrawable.setCurrentAccount(r9);
            int r1 = Theme.getColor(DrawerAccountPickerView.m1287$$Nest$sfgetCOLOR_KEY_STATUS());
            long r2 = DialogObject.getEmojiStatusDocumentId(r0.emoji_status);
            boolean r4 = MessagesController.getInstance(r9).isPremiumUser(r0);
            if (r10 != null) goto L9;
            r10 = BadgesController.INSTANCE.getBadge(r0);
        L9:
            Drawable r6 = null;
            if (r2 == 0) goto L12;
            this.premiumStatusDrawable.set(r2, false);
            Drawable r22 = this.premiumStatusDrawable;
        L16:
            this.premiumStatusDrawable.setColor(Integer.valueOf(r1));
            this.premiumStatusDrawable.setParticles(DialogObject.isEmojiStatusCollectible(r0.emoji_status), false);
            Drawable r102 = updateBadgeDrawable(r10, r1);
            if (r102 == null) goto L21;
            if (r22 == null) goto L20;
            r6 = r102;
            goto L21
        L20:
            r22 = r102;
        L21:
            applyNameDrawables(r22, r6);
            this.unreadBadge.bind(r9, this.nameView);
            this.checkPaint.setColor(Theme.getColor(DrawerAccountPickerView.m1284$$Nest$sfgetCOLOR_KEY_ACCENT()));
            if (r9 != UserConfig.selectedAccount) goto L24;
            boolean r92 = true;
        L25:
            this.selected = r92;
            if (r92 == false) goto L28;
            float r93 = 0.785f;
        L29:
            this.avatarView.setScaleX(r93);
            this.avatarView.setScaleY(r93);
            if (this.selected == false) goto L32;
            Drawable r94 = DrawerAccountPickerView.m1290$$Nest$smcreateSelectedAccountBackgroundDrawable();
        L33:
            setBackground(r94);
            setPadding(0, 0, 0, 0);
            invalidate();
            return;
        L32:
            r94 = DrawerAccountPickerView.m1289$$Nest$smcreateAccountItemRippleDrawable();
            goto L33
        L28:
            r93 = 1.0f;
            goto L29
        L24:
            r92 = false;
            goto L25
        L12:
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r23 = this.premiumStatusDrawable;
            if (r4 == false) goto L15;
            r23.set(PremiumGradient.getInstance().premiumStarDrawableMini, false);
            r22 = this.premiumStatusDrawable;
            goto L16
        L15:
            r23.set(null, false);
            r22 = null;
            goto L16
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            super.dispatchDraw(r7);
            this.unreadBadge.draw(this, r7);
            if (this.selected == false) goto L6;
            float r0 = this.checkPaint.getStrokeWidth() / 2.0f;
            this.avatarRect.set(this.avatarView.getLeft() + r0, this.avatarView.getTop() + r0, this.avatarView.getRight() - r0, this.avatarView.getBottom() - r0);
            float r02 = ExteraConfig.getAvatarCorners(34.0f);
            r7.drawRoundRect(this.avatarRect, r02, r02, this.checkPaint);
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.premiumStatusDrawable.attach();
            this.exteraBadgeDrawable.attach();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.premiumStatusDrawable.detach();
            this.exteraBadgeDrawable.detach();
        }

        public void updateUnreadCounter() {
            this.unreadBadge.update(this.nameView);
            invalidate();
        }
    }

    /* loaded from: classes4.dex */
    public static class AddAccountView extends LinearLayout {
        private final Drawable circleDrawable;
        private final Drawable plusDrawable;
        private final SimpleTextView textView;

        /* renamed from: com.exteragram.messenger.drawer.DrawerAccountPickerView$AddAccountView$1, reason: invalid class name */
        public class AnonymousClass1 extends CombinedDrawable {
            final /* synthetic */ AddAccountView this$0;

            public AnonymousClass1(AddAccountView r1, Drawable r2, Drawable r3) {
                this.this$0 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Components.CombinedDrawable, android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter r1) {
            }
        }

        public AddAccountView(Context r12) {
            super(r12);
            setOrientation(0);
            setGravity(16);
            setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(44.0f)));
            ImageView r0 = new ImageView(r12);
            r0.setScaleType(ImageView.ScaleType.CENTER);
            Drawable r1 = ResourcesCompat.getDrawable(r12.getResources(), R.drawable.poll_add_circle, null);
            this.circleDrawable = r1;
            Drawable r2 = ResourcesCompat.getDrawable(r12.getResources(), R.drawable.poll_add_plus, null);
            this.plusDrawable = r2;
            if (r1 == null) goto L5;
            r1.mutate();
        L5:
            if (r2 == null) goto L7;
            r2.mutate();
        L7:
            AnonymousClass1 r3 = new AnonymousClass1(this, r1, r2);
            r3.setCustomSize(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
            r0.setImageDrawable(r3);
            addView(r0, LayoutHelper.createLinear(34, 34, 16, 8, 0, 0, 0));
            SimpleTextView r02 = new SimpleTextView(r12);
            this.textView = r02;
            r02.setTextSize(15);
            r02.setTypeface(AndroidUtilities.bold());
            r02.setGravity(19);
            r02.setText(LocaleController.getString(R.string.AddAccount));
            addView(r02, LayoutHelper.createLinear(-2, -1, 16, 12, 0, 12, 0));
            updateColors();
        }

        public void updateColors() {
            setBackground(DrawerAccountPickerView.m1289$$Nest$smcreateAccountItemRippleDrawable());
            Drawable r0 = this.circleDrawable;
            if (r0 == null) goto L5;
            r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(DrawerAccountPickerView.m1284$$Nest$sfgetCOLOR_KEY_ACCENT()), PorterDuff.Mode.SRC_IN));
        L5:
            Drawable r02 = this.plusDrawable;
            if (r02 == null) goto L8;
            r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(DrawerAccountPickerView.m1285$$Nest$sfgetCOLOR_KEY_ADD_ICON()), PorterDuff.Mode.SRC_IN));
        L8:
            this.textView.setTextColor(Theme.getColor(DrawerAccountPickerView.m1288$$Nest$sfgetCOLOR_KEY_TEXT()));
        }
    }

    @FunctionalInterface
    public interface OnAccountLongClick {
        void onLongClick(int r1, View r2);
    }

    /* renamed from: $r8$lambda$0pqCI1Qy9Ux22E5F-WWU8qJS-HI, reason: not valid java name */
    public static /* synthetic */ void m1254$r8$lambda$0pqCI1Qy9Ux22E5FWWU8qJSHI(DrawerAccountPickerView r0, ValueAnimator r1) {
        r0.lambda$setExpanded$1(r1);
    }

    /* renamed from: $r8$lambda$xJXbc9j69H2-umTBsNy2kOY6EKM, reason: not valid java name */
    public static /* synthetic */ int m1255$r8$lambda$xJXbc9j69H2umTBsNy2kOY6EKM(DrawerAccountPickerView r0, int r1, int r2) {
        return r0.lambda$new$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetaccounts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m1256$$Nest$fgetaccounts(DrawerAccountPickerView r0) {
        return r0.accounts;
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AccountAdapter m1257$$Nest$fgetadapter(DrawerAccountPickerView r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetbadgeOverride, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BadgeDTO m1258$$Nest$fgetbadgeOverride(DrawerAccountPickerView r0) {
        return r0.badgeOverride;
    }

    /* renamed from: -$$Nest$fgetbgPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m1259$$Nest$fgetbgPaint(DrawerAccountPickerView r0) {
        return r0.bgPaint;
    }

    /* renamed from: -$$Nest$fgetbgRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m1260$$Nest$fgetbgRect(DrawerAccountPickerView r0) {
        return r0.bgRect;
    }

    /* renamed from: -$$Nest$fgetbottomGradient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearGradient m1261$$Nest$fgetbottomGradient(DrawerAccountPickerView r0) {
        return r0.bottomGradient;
    }

    /* renamed from: -$$Nest$fgetbottomGradientPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m1262$$Nest$fgetbottomGradientPaint(DrawerAccountPickerView r0) {
        return r0.bottomGradientPaint;
    }

    /* renamed from: -$$Nest$fgetclipMaskPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m1263$$Nest$fgetclipMaskPaint(DrawerAccountPickerView r0) {
        return r0.clipMaskPaint;
    }

    /* renamed from: -$$Nest$fgetclipWrapper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m1264$$Nest$fgetclipWrapper(DrawerAccountPickerView r0) {
        return r0.clipWrapper;
    }

    /* renamed from: -$$Nest$fgetcornerRadius, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m1265$$Nest$fgetcornerRadius(DrawerAccountPickerView r0) {
        return r0.cornerRadius;
    }

    /* renamed from: -$$Nest$fgetcurrentAnimatedHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1266$$Nest$fgetcurrentAnimatedHeight(DrawerAccountPickerView r0) {
        return r0.currentAnimatedHeight;
    }

    /* renamed from: -$$Nest$fgetdraggingItemView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m1267$$Nest$fgetdraggingItemView(DrawerAccountPickerView r0) {
        return r0.draggingItemView;
    }

    /* renamed from: -$$Nest$fgetexpandAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m1268$$Nest$fgetexpandAnimator(DrawerAccountPickerView r0) {
        return r0.expandAnimator;
    }

    /* renamed from: -$$Nest$fgetexpanded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1269$$Nest$fgetexpanded(DrawerAccountPickerView r0) {
        return r0.expanded;
    }

    /* renamed from: -$$Nest$fgetitemTouchHelper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ItemTouchHelper m1270$$Nest$fgetitemTouchHelper(DrawerAccountPickerView r0) {
        return r0.itemTouchHelper;
    }

    /* renamed from: -$$Nest$fgetlastHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1271$$Nest$fgetlastHeight(DrawerAccountPickerView r0) {
        return r0.lastHeight;
    }

    /* renamed from: -$$Nest$fgetonAccountLongClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OnAccountLongClick m1272$$Nest$fgetonAccountLongClick(DrawerAccountPickerView r0) {
        return r0.onAccountLongClick;
    }

    /* renamed from: -$$Nest$fgetonAccountSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m1273$$Nest$fgetonAccountSelected(DrawerAccountPickerView r0) {
        return r0.onAccountSelected;
    }

    /* renamed from: -$$Nest$fgetrecyclerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerView m1274$$Nest$fgetrecyclerView(DrawerAccountPickerView r0) {
        return r0.recyclerView;
    }

    /* renamed from: -$$Nest$fgettopGradient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearGradient m1275$$Nest$fgettopGradient(DrawerAccountPickerView r0) {
        return r0.topGradient;
    }

    /* renamed from: -$$Nest$fgettopGradientPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m1276$$Nest$fgettopGradientPaint(DrawerAccountPickerView r0) {
        return r0.topGradientPaint;
    }

    /* renamed from: -$$Nest$fputbottomGradient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1277$$Nest$fputbottomGradient(DrawerAccountPickerView r0, LinearGradient r1) {
        r0.bottomGradient = r1;
    }

    /* renamed from: -$$Nest$fputcurrentAnimatedHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1278$$Nest$fputcurrentAnimatedHeight(DrawerAccountPickerView r0, int r1) {
        r0.currentAnimatedHeight = r1;
    }

    /* renamed from: -$$Nest$fputdraggingItemView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1279$$Nest$fputdraggingItemView(DrawerAccountPickerView r0, View r1) {
        r0.draggingItemView = r1;
    }

    /* renamed from: -$$Nest$fputexpandAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1280$$Nest$fputexpandAnimator(DrawerAccountPickerView r0, ValueAnimator r1) {
        r0.expandAnimator = r1;
    }

    /* renamed from: -$$Nest$fputlastHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1281$$Nest$fputlastHeight(DrawerAccountPickerView r0, int r1) {
        r0.lastHeight = r1;
    }

    /* renamed from: -$$Nest$fputtopGradient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1282$$Nest$fputtopGradient(DrawerAccountPickerView r0, LinearGradient r1) {
        r0.topGradient = r1;
    }

    /* renamed from: -$$Nest$mcanAddAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1283$$Nest$mcanAddAccount(DrawerAccountPickerView r0) {
        return r0.canAddAccount();
    }

    /* renamed from: -$$Nest$sfgetCOLOR_KEY_ACCENT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1284$$Nest$sfgetCOLOR_KEY_ACCENT() {
        return COLOR_KEY_ACCENT;
    }

    /* renamed from: -$$Nest$sfgetCOLOR_KEY_ADD_ICON, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1285$$Nest$sfgetCOLOR_KEY_ADD_ICON() {
        return COLOR_KEY_ADD_ICON;
    }

    /* renamed from: -$$Nest$sfgetCOLOR_KEY_BACKGROUND, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1286$$Nest$sfgetCOLOR_KEY_BACKGROUND() {
        return COLOR_KEY_BACKGROUND;
    }

    /* renamed from: -$$Nest$sfgetCOLOR_KEY_STATUS, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1287$$Nest$sfgetCOLOR_KEY_STATUS() {
        return COLOR_KEY_STATUS;
    }

    /* renamed from: -$$Nest$sfgetCOLOR_KEY_TEXT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1288$$Nest$sfgetCOLOR_KEY_TEXT() {
        return COLOR_KEY_TEXT;
    }

    /* renamed from: -$$Nest$smcreateAccountItemRippleDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m1289$$Nest$smcreateAccountItemRippleDrawable() {
        return createAccountItemRippleDrawable();
    }

    /* renamed from: -$$Nest$smcreateSelectedAccountBackgroundDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m1290$$Nest$smcreateSelectedAccountBackgroundDrawable() {
        return createSelectedAccountBackgroundDrawable();
    }

    static {
        COLOR_KEY_BACKGROUND = Theme.key_windowBackgroundGray;
        COLOR_KEY_SELECTOR = Theme.key_listSelector;
        COLOR_KEY_SURFACE = Theme.key_windowBackgroundWhite;
        COLOR_KEY_TEXT = Theme.key_windowBackgroundWhiteBlackText;
        COLOR_KEY_STATUS = Theme.key_profile_verifiedBackground;
        COLOR_KEY_ACCENT = Theme.key_featuredStickers_addButton;
        COLOR_KEY_ADD_ICON = Theme.key_featuredStickers_buttonText;
    }

    public DrawerAccountPickerView(Context r14) {
        super(r14);
        this.accounts = new ArrayList();
        this.bgPaint = new Paint(1);
        Paint r0 = new Paint(1);
        this.clipMaskPaint = r0;
        this.bgRect = new RectF();
        float r2 = AndroidUtilities.dp(16.0f);
        this.cornerRadius = r2;
        Paint r3 = new Paint();
        this.topGradientPaint = r3;
        Paint r4 = new Paint();
        this.bottomGradientPaint = r4;
        this.currentAnimatedHeight = -1;
        this.expanded = MessagesController.getGlobalMainSettings().getBoolean("accountsShown", true);
        r0.setStyle(Paint.Style.FILL);
        r0.setColor(-16777216);
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        PorterDuff.Mode r6 = PorterDuff.Mode.DST_OUT;
        r3.setXfermode(new PorterDuffXfermode(r6));
        r4.setXfermode(new PorterDuffXfermode(r6));
        AnonymousClass1 r02 = new AnonymousClass1(this, r14);
        this.clipWrapper = r02;
        r02.setOutlineProvider(ViewOutlineProviderImpl.boundsWithRoundRect(r2));
        r02.setClipToOutline(true);
        addView(r02, LayoutHelper.createFrame(-1, 0.0f, 48, 12.0f, 0.0f, 12.0f, 0.0f));
        AccountAdapter r1 = new AccountAdapter(this, null);
        this.adapter = r1;
        RecyclerView r22 = new RecyclerView(r14);
        this.recyclerView = r22;
        r22.setLayoutManager(new LinearLayoutManager(r14));
        r22.setAdapter(r1);
        int r142 = AndroidUtilities.dp(4.0f);
        r22.setPadding(r142, r142, r142, r142);
        r22.setClipToPadding(false);
        r22.addItemDecoration(new AnonymousClass2(this, r142));
        r22.setOverScrollMode(2);
        r22.setVerticalScrollBarEnabled(false);
        r22.addOnScrollListener(new AnonymousClass3(this));
        r02.addView(r22, LayoutHelper.createFrame(-1, -2.0f));
        ItemTouchHelper r143 = new ItemTouchHelper(new AnonymousClass4(this, new DrawerAccountPickerView$$ExternalSyntheticLambda1(this)));
        this.itemTouchHelper = r143;
        r143.attachToRecyclerView(r22);
        if (this.expanded == false) goto L6;
        loadAccounts();
        setVisibility(0);
        ViewGroup.LayoutParams r13 = r02.getLayoutParams();
        r13.height = -2;
        r02.setLayoutParams(r13);
        return;
    L6:
        setVisibility(8);
    }

    private boolean canAddAccount() {
        if (AccountsUiHelper.freeSlotWithinLimit() == null) goto L6;
        return true;
    L6:
        return false;
    }

    private static Drawable createAccountItemRippleDrawable() {
        return Theme.createRadSelectorDrawable(Theme.getColor(COLOR_KEY_SELECTOR), 12, 12);
    }

    private static Drawable createSelectedAccountBackgroundDrawable() {
        return Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(12.0f), Theme.getColor(COLOR_KEY_SURFACE), Theme.getColor(COLOR_KEY_SELECTOR));
    }

    private /* synthetic */ int lambda$new$0(int r2, int r3) {
        View r0 = this.draggingItemView;
        if (r0 == null) goto L12;
        int r1 = this.recyclerView.indexOfChild(r0);
        if (r1 < 0) goto L12;
        if (r3 != (r2 - 1)) goto L11;
        return r1;
    L11:
        if (r3 < r1) goto L12;
        return r3 + 1;
    L12:
        return r3;
    }

    private /* synthetic */ void lambda$setExpanded$1(ValueAnimator r1) {
        this.currentAnimatedHeight = ((Integer) r1.getAnimatedValue()).intValue();
        this.clipWrapper.requestLayout();
    }

    public void dispose() {
        ValueAnimator r0 = this.expandAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.expandAnimator = null;
    L5:
        this.draggingItemView = null;
        this.recyclerView.setChildDrawingOrderCallback(null);
        this.recyclerView.stopScroll();
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    public void loadAccounts() {
        loadAccounts(null);
    }

    public void loadAccounts(BadgeDTO r2) {
        this.badgeOverride = r2;
        this.accounts.clear();
        this.accounts.addAll(AccountsUiHelper.activated());
        this.adapter.notifyDataSetChanged();
    }

    public void setExpanded(boolean r7) {
        if (this.expanded != r7) goto L5;
        return;
    L5:
        this.expanded = r7;
        MessagesController.getGlobalMainSettings().edit().putBoolean("accountsShown", r7).apply();
        int r0 = 0;
        if (r7 == false) goto L8;
        loadAccounts();
        setVisibility(0);
    L8:
        ValueAnimator r1 = this.expandAnimator;
        if (r1 == null) goto L11;
        r1.cancel();
    L11:
        int r12 = this.currentAnimatedHeight;
        if (r12 >= 0) goto L21;
        r12 = this.clipWrapper.getLayoutParams().height;
        if (r12 >= 0) goto L17;
        r12 = this.clipWrapper.getHeight();
    L17:
        if (r12 >= 0) goto L21;
        if (r7 == false) goto L20;
        r12 = 0;
        goto L21
    L20:
        r12 = this.clipWrapper.getMeasuredHeight();
    L21:
        this.currentAnimatedHeight = -1;
        int r2 = this.adapter.getItemCount();
        if (r2 > 6) goto L24;
        float r22 = r2;
    L25:
        this.clipWrapper.measure(View.MeasureSpec.makeMeasureSpec(((View) getParent()).getMeasuredWidth() - AndroidUtilities.dp(24.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) ((AndroidUtilities.dp(48.0f) * r22) + (AndroidUtilities.dp(4.0f) * 2)), Integer.MIN_VALUE));
        if (r7 == false) goto L28;
        r0 = this.clipWrapper.getMeasuredHeight();
    L28:
        this.currentAnimatedHeight = r12;
        ValueAnimator r72 = ValueAnimator.ofInt(new int[]{r12, r0});
        this.expandAnimator = r72;
        r72.setDuration(250);
        r72.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r72.addUpdateListener(new DrawerAccountPickerView$$ExternalSyntheticLambda0(this));
        r72.addListener(new AnonymousClass5(this));
        r72.start();
        return;
    L24:
        r22 = 5.5f;
        goto L25
    }

    public void setOnAccountLongClick(OnAccountLongClick r1) {
        this.onAccountLongClick = r1;
    }

    public void setOnAccountSelected(Runnable r1) {
        this.onAccountSelected = r1;
    }

    public void toggleExpand() {
        setExpanded(!this.expanded);
    }

    public void updateColors() {
        this.bgPaint.setColor(Theme.getColor(COLOR_KEY_BACKGROUND));
        invalidate();
        this.adapter.notifyDataSetChanged();
    }

    public void updateUnreadCounters() {
        if (this.recyclerView.getChildCount() == 0) goto L12;
        int r0 = 0;
    L7:
        if (r0 >= this.recyclerView.getChildCount()) goto L16;
        View r1 = this.recyclerView.getChildAt(r0);
        if ((r1 instanceof AccountRowView) == false) goto L11;
        ((AccountRowView) r1).updateUnreadCounter();
    L11:
        r0 = r0 + 1;
        goto L7
    L16:
        return;
    }
}
