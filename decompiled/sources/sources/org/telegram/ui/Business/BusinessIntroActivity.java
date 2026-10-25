package org.telegram.ui.Business;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.ChatGreetingsView;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalFragment;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.ContentPreviewViewer;
import org.telegram.ui.Stories.recorder.EmojiBottomSheet;
import org.telegram.ui.Stories.recorder.KeyboardNotifier;
import org.telegram.ui.Stories.recorder.PreviewView;

/* loaded from: classes6.dex */
public class BusinessIntroActivity extends UniversalFragment implements NotificationCenter.NotificationCenterDelegate {
    private ChatAttachAlert chatAttachAlert;
    private boolean clearVisible;
    private String currentMessage;
    private long currentSticker;
    private String currentTitle;
    private ActionBarMenuItem doneButton;
    private CrossfadeDrawable doneButtonDrawable;
    private ChatGreetingsView greetingsView;
    private Drawable greetingsViewBackground;
    private TLRPC.InputDocument inputSticker;
    private String inputStickerPath;
    private boolean keyboardVisible;
    private EditTextCell messageEdit;
    private FrameLayout previewContainer;
    private int shiftDp;
    private TLRPC.Document sticker;
    private boolean stickerRandom;
    private EditTextCell titleEdit;
    private final Runnable updateRandomStickerRunnable;
    private boolean valueSet;

    /* renamed from: org.telegram.ui.Business.BusinessIntroActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ChatGreetingsView {
        final /* synthetic */ BusinessIntroActivity this$0;

        public AnonymousClass1(BusinessIntroActivity r1, Context r2, TLRPC.User r3, int r4, TLRPC.Document r5, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.ChatGreetingsView, android.widget.LinearLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            setPivotX(getMeasuredWidth() / 2.0f);
            setPivotY(getMeasuredHeight());
        }
    }

    /* renamed from: org.telegram.ui.Business.BusinessIntroActivity$10, reason: invalid class name */
    public class AnonymousClass10 implements ChatAttachAlert.ChatAttachViewDelegate {
        final /* synthetic */ BusinessIntroActivity this$0;

        public AnonymousClass10(BusinessIntroActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10) {
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void doOnIdle(Runnable r1) {
            NotificationCenter.getInstance(BusinessIntroActivity.access$200(this.this$0)).doOnIdle(r1);
        }
    }

    /* renamed from: org.telegram.ui.Business.BusinessIntroActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        private final Rect bg;
        int minHeight;
        final /* synthetic */ BusinessIntroActivity this$0;
        private final AnimatedFloat width;

        public AnonymousClass2(BusinessIntroActivity r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.minHeight = -1;
            this.bg = new Rect();
            this.width = new AnimatedFloat(this, 220, CubicBezierInterpolator.EASE_OUT_QUINT);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r9, View r10, long r11) {
            float r0 = getWidth() / 2.0f;
            float r2 = this.width.set(BusinessIntroActivity.m6321$$Nest$fgetgreetingsView(this.this$0).getWidth()) / 2.0f;
            this.bg.set((int) (r0 - (BusinessIntroActivity.m6321$$Nest$fgetgreetingsView(this.this$0).getScaleX() * r2)), (int) (BusinessIntroActivity.m6321$$Nest$fgetgreetingsView(this.this$0).getY() + (BusinessIntroActivity.m6321$$Nest$fgetgreetingsView(this.this$0).getHeight() * (1.0f - BusinessIntroActivity.m6321$$Nest$fgetgreetingsView(this.this$0).getScaleY()))), (int) (r0 + (r2 * BusinessIntroActivity.m6321$$Nest$fgetgreetingsView(this.this$0).getScaleX())), (int) (BusinessIntroActivity.m6321$$Nest$fgetgreetingsView(this.this$0).getY() + BusinessIntroActivity.m6321$$Nest$fgetgreetingsView(this.this$0).getHeight()));
            BusinessIntroActivity.m6322$$Nest$fgetgreetingsViewBackground(this.this$0).setBounds(this.bg);
            BusinessIntroActivity.m6322$$Nest$fgetgreetingsViewBackground(this.this$0).draw(r9);
            return super.drawChild(r9, r10, r11);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            BusinessIntroActivity.m6321$$Nest$fgetgreetingsView(this.this$0).measure(r3, r4);
            invalidate();
            super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(Math.max(this.minHeight, BusinessIntroActivity.m6321$$Nest$fgetgreetingsView(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(36.0f)), TLObject.FLAG_30));
            if (this.minHeight >= 0) goto L6;
            this.minHeight = getMeasuredHeight();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Business.BusinessIntroActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ImageView {
        final /* synthetic */ BusinessIntroActivity this$0;

        public AnonymousClass3(BusinessIntroActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.ImageView, android.view.View
        public void onMeasure(int r6, int r7) {
            super.onMeasure(r6, r7);
            Matrix r62 = getImageMatrix();
            int r72 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            int r0 = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            int r1 = getDrawable().getIntrinsicWidth();
            int r2 = getDrawable().getIntrinsicHeight();
            if ((r1 * r0) <= (r2 * r72)) goto L6;
            float r73 = r0;
            float r02 = r2;
        L5:
            float r74 = r73 / r02;
            r62.setScale(r74, r74);
            setImageMatrix(r62);
            return;
        L6:
            r73 = r72;
            r02 = r1;
            goto L5
        }
    }

    /* renamed from: org.telegram.ui.Business.BusinessIntroActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends EditTextCell {
        final /* synthetic */ BusinessIntroActivity this$0;

        public AnonymousClass4(BusinessIntroActivity r1, Context r2, String r3, boolean r4, boolean r5, int r6, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Cells.EditTextCell
        public void onFocusChanged(boolean r1) {
            if (r1 == false) goto L7;
            UniversalRecyclerView r0 = this.this$0.listView;
            if (r0 == null) goto L8;
            r0.smoothScrollToPosition(2);
            return;
        L8:
            return;
        }

        @Override // org.telegram.ui.Cells.EditTextCell
        public void onTextChanged(CharSequence r3) {
            BusinessIntroActivity.m6321$$Nest$fgetgreetingsView(this.this$0).setPreview(BusinessIntroActivity.m6324$$Nest$fgettitleEdit(this.this$0).getText().toString(), BusinessIntroActivity.m6323$$Nest$fgetmessageEdit(this.this$0).getText().toString());
            BusinessIntroActivity.m6325$$Nest$mcheckDone(this.this$0, true, true);
        }
    }

    /* renamed from: org.telegram.ui.Business.BusinessIntroActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends EditTextCell {
        final /* synthetic */ BusinessIntroActivity this$0;

        public AnonymousClass5(BusinessIntroActivity r1, Context r2, String r3, boolean r4, boolean r5, int r6, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Cells.EditTextCell
        public void onFocusChanged(boolean r1) {
            if (r1 == false) goto L7;
            UniversalRecyclerView r0 = this.this$0.listView;
            if (r0 == null) goto L8;
            r0.smoothScrollToPosition(3);
            return;
        L8:
            return;
        }

        @Override // org.telegram.ui.Cells.EditTextCell
        public void onTextChanged(CharSequence r3) {
            BusinessIntroActivity.m6321$$Nest$fgetgreetingsView(this.this$0).setPreview(BusinessIntroActivity.m6324$$Nest$fgettitleEdit(this.this$0).getText().toString(), BusinessIntroActivity.m6323$$Nest$fgetmessageEdit(this.this$0).getText().toString());
            BusinessIntroActivity.m6325$$Nest$mcheckDone(this.this$0, true, true);
        }
    }

    /* renamed from: org.telegram.ui.Business.BusinessIntroActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ BusinessIntroActivity this$0;

        public AnonymousClass6(BusinessIntroActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            BusinessIntroActivity.m6326$$Nest$mprocessDone(this.this$0);
            return;
        L12:
            return;
        L5:
            if (this.this$0.onBackPressed(true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Business.BusinessIntroActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements View.OnLayoutChangeListener {
        final /* synthetic */ BusinessIntroActivity this$0;

        public AnonymousClass7(BusinessIntroActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
            BusinessIntroActivity.m6327$$Nest$mupdateGreetingScale(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Business.BusinessIntroActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends RecyclerView.OnScrollListener {
        final /* synthetic */ BusinessIntroActivity this$0;

        public AnonymousClass8(BusinessIntroActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            BusinessIntroActivity.m6327$$Nest$mupdateGreetingScale(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Business.BusinessIntroActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends ChatAttachAlert {
        final /* synthetic */ BusinessIntroActivity this$0;

        public AnonymousClass9(BusinessIntroActivity r1, Context r2, BaseFragment r3, boolean r4, boolean r5, boolean r6, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert, org.telegram.ui.ActionBar.BottomSheet
        public void dismissInternal() {
            if (BusinessIntroActivity.m6320$$Nest$fgetchatAttachAlert(this.this$0) != null) goto L5;
        L7:
            super.dismissInternal();
            return;
        L5:
            if (BusinessIntroActivity.m6320$$Nest$fgetchatAttachAlert(this.this$0).isShowing() == false) goto L7;
            AndroidUtilities.requestAdjustResize(this.this$0.getParentActivity(), BusinessIntroActivity.access$000(this.this$0));
            goto L7
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public void onDismissAnimationStart() {
            if (BusinessIntroActivity.m6320$$Nest$fgetchatAttachAlert(this.this$0) == null) goto L6;
            BusinessIntroActivity.m6320$$Nest$fgetchatAttachAlert(this.this$0).setFocusable(false);
        L6:
            if (BusinessIntroActivity.m6320$$Nest$fgetchatAttachAlert(this.this$0) != null) goto L8;
            return;
        L8:
            if (BusinessIntroActivity.m6320$$Nest$fgetchatAttachAlert(this.this$0).isShowing() == false) goto L12;
            AndroidUtilities.requestAdjustResize(this.this$0.getParentActivity(), BusinessIntroActivity.access$100(this.this$0));
            return;
        }
    }

    public static /* synthetic */ Boolean $r8$lambda$5nzfwVsJEILQ_10N2_eAZiSYlYk(BusinessIntroActivity r0, View r1, Object r2, TLRPC.Document r3, Boolean r4) {
        return r0.lambda$onClick$2(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$GK3smV8CVHYVdJXauG6ePB-VQ7Y, reason: not valid java name */
    public static /* synthetic */ void m6315$r8$lambda$GK3smV8CVHYVdJXauG6ePBVQ7Y(BusinessIntroActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$processDone$3(r1, r2);
    }

    /* renamed from: $r8$lambda$HXn0XKCG-6qrt74oHQm972XHsXU, reason: not valid java name */
    public static /* synthetic */ void m6316$r8$lambda$HXn0XKCG6qrt74oHQm972XHsXU(BusinessIntroActivity r0, String r1, TLRPC.InputDocument r2) {
        r0.setCustomSticker(r1, r2);
    }

    /* renamed from: $r8$lambda$OiB3uibPJF-Ic36oYwo7oJLehP0, reason: not valid java name */
    public static /* synthetic */ void m6317$r8$lambda$OiB3uibPJFIc36oYwo7oJLehP0(BusinessIntroActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$6(r1, r2);
    }

    /* renamed from: $r8$lambda$UI0qdZkKvL86rGFlF4Tl_-2ogKY, reason: not valid java name */
    public static /* synthetic */ void m6318$r8$lambda$UI0qdZkKvL86rGFlF4Tl_2ogKY(BusinessIntroActivity r0, Integer r1) {
        r0.lambda$createView$1(r1);
    }

    /* renamed from: $r8$lambda$X4boHzg_L9WHSx5xY-eeGjTZhag, reason: not valid java name */
    public static /* synthetic */ void m6319$r8$lambda$X4boHzg_L9WHSx5xYeeGjTZhag(BusinessIntroActivity r0) {
        r0.lambda$updateRandomSticker$0();
    }

    public static /* synthetic */ void $r8$lambda$jDm0odhYeSrgc08n3mMnFjnyJN4(BusinessIntroActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$oSdZT3NgWH4MNAkaJvaBTdeDYMQ(BusinessIntroActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$processDone$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sXOJXimCAcTBzG87EogrUSdDsoc(BusinessIntroActivity r0) {
        r0.updateRandomSticker();
    }

    public static /* synthetic */ void $r8$lambda$wL0XFnrcT57Mi79EV3C6iYxX1bM(BusinessIntroActivity r0) {
        r0.openCustomStickerEditor();
    }

    /* renamed from: -$$Nest$fgetchatAttachAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlert m6320$$Nest$fgetchatAttachAlert(BusinessIntroActivity r0) {
        return r0.chatAttachAlert;
    }

    /* renamed from: -$$Nest$fgetgreetingsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatGreetingsView m6321$$Nest$fgetgreetingsView(BusinessIntroActivity r0) {
        return r0.greetingsView;
    }

    /* renamed from: -$$Nest$fgetgreetingsViewBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m6322$$Nest$fgetgreetingsViewBackground(BusinessIntroActivity r0) {
        return r0.greetingsViewBackground;
    }

    /* renamed from: -$$Nest$fgetmessageEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextCell m6323$$Nest$fgetmessageEdit(BusinessIntroActivity r0) {
        return r0.messageEdit;
    }

    /* renamed from: -$$Nest$fgettitleEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextCell m6324$$Nest$fgettitleEdit(BusinessIntroActivity r0) {
        return r0.titleEdit;
    }

    /* renamed from: -$$Nest$mcheckDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6325$$Nest$mcheckDone(BusinessIntroActivity r0, boolean r1, boolean r2) {
        r0.checkDone(r1, r2);
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6326$$Nest$mprocessDone(BusinessIntroActivity r0) {
        r0.processDone();
    }

    /* renamed from: -$$Nest$mupdateGreetingScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6327$$Nest$mupdateGreetingScale(BusinessIntroActivity r0) {
        r0.updateGreetingScale();
    }

    public BusinessIntroActivity() {
        this.updateRandomStickerRunnable = new BusinessIntroActivity$$ExternalSyntheticLambda5(this);
        this.stickerRandom = true;
        this.sticker = getMediaDataController().getGreetingsSticker();
        this.clearVisible = isEmpty();
        this.shiftDp = -4;
    }

    public static /* synthetic */ int access$000(BusinessIntroActivity r0) {
        return r0.classGuid;
    }

    public static /* synthetic */ int access$100(BusinessIntroActivity r0) {
        return r0.classGuid;
    }

    public static /* synthetic */ int access$200(BusinessIntroActivity r0) {
        return r0.currentAccount;
    }

    private void checkDone(boolean r4, boolean r5) {
        if (this.doneButton == null) goto L41;
        boolean r52 = hasChanges();
        this.doneButton.setEnabled(r52);
        ActionBarMenuItem r0 = this.doneButton;
        float r1 = 0.0f;
        if (r4 == false) goto L19;
        ViewPropertyAnimator r42 = r0.animate();
        if (r52 == false) goto L10;
        float r02 = 1.0f;
    L11:
        ViewPropertyAnimator r43 = r42.alpha(r02);
        if (r52 == false) goto L14;
        float r03 = 1.0f;
    L15:
        ViewPropertyAnimator r44 = r43.scaleX(r03);
        if (r52 == false) goto L18;
        r1 = 1.0f;
    L18:
        r44.scaleY(r1).setDuration(180).start();
    L30:
        UniversalRecyclerView r45 = this.listView;
        if (r45 != null) goto L33;
        return;
    L33:
        if (r45.adapter != null) goto L35;
        return;
    L35:
        if (this.clearVisible == (!isEmpty())) goto L40;
        saveScrollPosition();
        this.listView.adapter.update(true);
        applyScrolledPosition();
        return;
    L40:
        return;
    L14:
        r03 = 0.0f;
        goto L15
    L10:
        r02 = 0.0f;
        goto L11
    L19:
        if (r52 == false) goto L21;
        float r46 = 1.0f;
    L22:
        r0.setAlpha(r46);
        ActionBarMenuItem r47 = this.doneButton;
        if (r52 == false) goto L25;
        float r04 = 1.0f;
    L26:
        r47.setScaleX(r04);
        ActionBarMenuItem r48 = this.doneButton;
        if (r52 == false) goto L29;
        r1 = 1.0f;
    L29:
        r48.setScaleY(r1);
        goto L30
    L25:
        r04 = 0.0f;
        goto L26
    L21:
        r46 = 0.0f;
        goto L22
    }

    private void createChatAttachView() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        if (getContext() != null) goto L8;
        return;
    L8:
        if (this.chatAttachAlert != null) goto L12;
        AnonymousClass9 r1 = new AnonymousClass9(this, getParentActivity(), this, false, false, true, this.resourceProvider);
        this.chatAttachAlert = r1;
        r1.setDelegate(new AnonymousClass10(this));
        return;
    }

    private /* synthetic */ void lambda$createView$1(Integer r3) {
        if (r3.intValue() <= AndroidUtilities.dp(20.0f)) goto L5;
        boolean r32 = true;
    L7:
        if (this.keyboardVisible == r32) goto L14;
        this.keyboardVisible = r32;
        if (r32 == true) goto L13;
        this.listView.smoothScrollToPosition(0);
        return;
    L13:
        return;
    L14:
        return;
    L5:
        r32 = false;
        goto L7
    }

    private /* synthetic */ void lambda$onBackPressed$5(AlertDialog r1, int r2) {
        processDone();
    }

    private /* synthetic */ void lambda$onBackPressed$6(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ Boolean lambda$onClick$2(View r1, Object r2, TLRPC.Document r3, Boolean r4) {
        this.stickerRandom = false;
        AndroidUtilities.cancelRunOnUIThread(this.updateRandomStickerRunnable);
        ChatGreetingsView r42 = this.greetingsView;
        this.sticker = r3;
        r42.setSticker(r3);
        ((TextCell) r1).setValueSticker(r3);
        checkDone(true, false);
        return Boolean.TRUE;
    }

    private /* synthetic */ void lambda$processDone$3(TLRPC.TL_error r3, TLObject r4) {
        if (r3 == null) goto L7;
        this.doneButtonDrawable.animateToProgress(0.0f);
        BulletinFactory.showError(r3);
        return;
    L7:
        if ((r4 instanceof TLRPC.TL_boolFalse) == false) goto L11;
        this.doneButtonDrawable.animateToProgress(0.0f);
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
        return;
    L11:
        if (this.inputSticker == null) goto L13;
        getMessagesController().loadFullUser(getUserConfig().getCurrentUser(), 0, true);
    L13:
        finishFragment();
    }

    private /* synthetic */ void lambda$processDone$4(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new BusinessIntroActivity$$ExternalSyntheticLambda9(this, r3, r2));
    }

    private /* synthetic */ void lambda$updateRandomSticker$0() {
        AndroidUtilities.cancelRunOnUIThread(this.updateRandomStickerRunnable);
        AndroidUtilities.runOnUIThread(this.updateRandomStickerRunnable, 5000);
    }

    private void openCustomStickerEditor() {
        ContentPreviewViewer.getInstance().setStickerSetForCustomSticker(null);
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        createChatAttachView();
        this.chatAttachAlert.getPhotoLayout().loadGalleryPhotos();
        this.chatAttachAlert.setMaxSelectedPhotos(1, false);
        this.chatAttachAlert.setOpenWithFrontFaceCamera(true);
        this.chatAttachAlert.enableStickerMode(new BusinessIntroActivity$$ExternalSyntheticLambda7(this));
        this.chatAttachAlert.init();
        ChatAttachAlert r0 = this.chatAttachAlert;
        r0.parentThemeDelegate = null;
        if (this.visibleDialog == null) goto L9;
        r0.show();
        return;
    L9:
        showDialog(r0);
    }

    private void processDone() {
        if (this.doneButtonDrawable.getProgress() <= 0.0f) goto L5;
        return;
    L5:
        this.doneButtonDrawable.animateToProgress(1.0f);
        TLRPC.UserFull r0 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        TL_account.updateBusinessIntro r1 = new TL_account.updateBusinessIntro();
        if (isEmpty() == true) goto L23;
        r1.flags |= 1;
        TL_account.TL_inputBusinessIntro r2 = new TL_account.TL_inputBusinessIntro();
        r1.intro = r2;
        r2.title = this.titleEdit.getText().toString();
        r1.intro.description = this.messageEdit.getText().toString();
        if (this.stickerRandom == false) goto L10;
    L17:
        if (r0 == null) goto L25;
        r0.flags2 |= 16;
        TL_account.TL_businessIntro r22 = new TL_account.TL_businessIntro();
        r0.business_intro = r22;
        TL_account.TL_inputBusinessIntro r3 = r1.intro;
        r22.title = r3.title;
        r22.description = r3.description;
        if (this.stickerRandom == true) goto L25;
        TLRPC.Document r32 = this.sticker;
        if (r32 == null) goto L25;
        r22.flags |= 1;
        r22.sticker = r32;
    L25:
        getConnectionsManager().sendRequest(r1, new BusinessIntroActivity$$ExternalSyntheticLambda6(this));
        getMessagesStorage().updateUserInfo(r0, false);
        return;
    L10:
        if (this.sticker == null) goto L12;
    L13:
        TL_account.TL_inputBusinessIntro r23 = r1.intro;
        r23.flags |= 1;
        TLRPC.InputDocument r33 = this.inputSticker;
        if (r33 == null) goto L16;
        r23.sticker = r33;
        goto L17
    L16:
        r23.sticker = getMessagesController().getInputDocument(this.sticker);
        goto L17
    L12:
        if (this.inputSticker == null) goto L17;
    L23:
        if (r0 == null) goto L25;
        r0.flags2 &= -17;
        r0.business_intro = null;
        goto L25
    }

    private void setCustomSticker(String r2, TLRPC.InputDocument r3) {
        this.chatAttachAlert.dismiss();
        this.inputStickerPath = r2;
        this.inputSticker = r3;
        this.stickerRandom = false;
        AndroidUtilities.cancelRunOnUIThread(this.updateRandomStickerRunnable);
        this.greetingsView.setSticker(this.inputStickerPath);
        checkDone(true, false);
        UniversalRecyclerView r1 = this.listView;
        if (r1 == null) goto L8;
        UniversalAdapter r12 = r1.adapter;
        if (r12 == null) goto L9;
        r12.update(true);
        return;
    L9:
        return;
    }

    private void setValue() {
        if (this.valueSet == false) goto L5;
        return;
    L5:
        TLRPC.UserFull r0 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        if (r0 != null) goto L9;
        getMessagesController().loadUserInfo(getUserConfig().getCurrentUser(), true, getClassGuid());
        return;
    L9:
        TL_account.TL_businessIntro r2 = r0.business_intro;
        EditTextCell r3 = this.titleEdit;
        if (r2 == null) goto L12;
        String r22 = r2.title;
        this.currentTitle = r22;
        r3.setText(r22);
        EditTextCell r23 = this.messageEdit;
        String r32 = r0.business_intro.description;
        this.currentMessage = r32;
        r23.setText(r32);
        this.sticker = r0.business_intro.sticker;
    L13:
        TLRPC.Document r02 = this.sticker;
        if (r02 != null) goto L16;
        long r24 = 0;
    L17:
        this.currentSticker = r24;
        if (r02 != null) goto L20;
        boolean r03 = true;
    L21:
        this.stickerRandom = r03;
        ChatGreetingsView r04 = this.greetingsView;
        if (r04 == null) goto L30;
        r04.setPreview(this.titleEdit.getText().toString(), this.messageEdit.getText().toString());
        ChatGreetingsView r05 = this.greetingsView;
        TLRPC.Document r25 = this.sticker;
        if (r25 != null) goto L26;
    L27:
        r25 = MediaDataController.getInstance(this.currentAccount).getGreetingsSticker();
    L28:
        r05.setSticker(r25);
        goto L30
    L26:
        if (this.stickerRandom == false) goto L28;
    L30:
        if (this.stickerRandom == false) goto L32;
        AndroidUtilities.cancelRunOnUIThread(this.updateRandomStickerRunnable);
        AndroidUtilities.runOnUIThread(this.updateRandomStickerRunnable, 5000);
    L32:
        UniversalRecyclerView r06 = this.listView;
        if (r06 == null) goto L37;
        UniversalAdapter r07 = r06.adapter;
        if (r07 == null) goto L37;
        r07.update(true);
    L37:
        this.valueSet = true;
        return;
    L20:
        r03 = false;
        goto L21
    L16:
        r24 = r02.id;
        goto L17
    L12:
        this.currentTitle = _UrlKt.FRAGMENT_ENCODE_SET;
        r3.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        EditTextCell r26 = this.messageEdit;
        this.currentMessage = _UrlKt.FRAGMENT_ENCODE_SET;
        r26.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.inputSticker = null;
        this.sticker = null;
        goto L13
    }

    private void updateGreetingScale() {
        if ((this.previewContainer.getParent() instanceof View) == true) goto L5;
        return;
    L5:
        int r0 = ((View) this.previewContainer.getParent()).getTop();
        float r02 = Utilities.clamp((r0 + r1) / (this.previewContainer.getMeasuredHeight() - AndroidUtilities.dp(36.0f)), 1.0f, 0.65f);
        this.greetingsView.setScaleX(r02);
        this.greetingsView.setScaleY(r02);
        this.greetingsView.setAlpha(Utilities.clamp(r02 * 2.0f, 1.0f, 0.0f));
        this.previewContainer.invalidate();
    }

    private void updateRandomSticker() {
        ChatGreetingsView r0 = this.greetingsView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isAttachedToWindow() == true) goto L7;
        return;
    L7:
        if (this.stickerRandom == false) goto L13;
        this.greetingsView.setNextSticker(MediaDataController.getInstance(this.currentAccount).getGreetingsSticker(), new BusinessIntroActivity$$ExternalSyntheticLambda8(this));
        return;
    }

    @Override // org.telegram.ui.Components.UniversalFragment, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r17) {
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        this.greetingsView = new AnonymousClass1(this, r17, getUserConfig().getCurrentUser(), this.currentAccount, this.sticker, getResourceProvider());
        AnonymousClass2 r0 = new AnonymousClass2(this, r17);
        this.previewContainer = r0;
        r0.setWillNotDraw(false);
        this.greetingsViewBackground = Theme.createServiceDrawable(AndroidUtilities.dp(16.0f), this.greetingsView, this.previewContainer, getThemedPaint("paintChatActionBackground"));
        this.greetingsView.setBackground(new ColorDrawable(0));
        AnonymousClass3 r02 = new AnonymousClass3(this, r17);
        r02.setScaleType(ImageView.ScaleType.MATRIX);
        r02.setImageDrawable(PreviewView.getBackgroundDrawable(null, this.currentAccount, getUserConfig().getClientUserId(), Theme.isCurrentThemeDark()));
        this.previewContainer.addView(r02, LayoutHelper.createFrame(-1, -1, 119));
        this.previewContainer.addView(this.greetingsView, LayoutHelper.createFrame(-2, -2.0f, 17, 42.0f, 18.0f, 42.0f, 18.0f));
        AnonymousClass4 r03 = new AnonymousClass4(this, r17, LocaleController.getString(R.string.BusinessIntroTitleHint), false, false, getMessagesController().introTitleLengthLimit, this.resourceProvider);
        this.titleEdit = r03;
        r03.autofocused = true;
        r03.setShowLimitOnFocus(true);
        EditTextCell r04 = this.titleEdit;
        int r10 = Theme.key_windowBackgroundWhite;
        r04.setBackgroundColor(getThemedColor(r10));
        this.titleEdit.setDivider(true);
        this.titleEdit.hideKeyboardOnEnter();
        AnonymousClass5 r05 = new AnonymousClass5(this, r17, LocaleController.getString(R.string.BusinessIntroMessageHint), true, false, getMessagesController().introDescriptionLengthLimit, this.resourceProvider);
        this.messageEdit = r05;
        r05.setShowLimitOnFocus(true);
        this.messageEdit.setBackgroundColor(getThemedColor(r10));
        this.messageEdit.setDivider(true);
        this.messageEdit.hideKeyboardOnEnter();
        this.greetingsView.setPreview(_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);
        super.createView(r17);
        this.listView.setSections();
        this.listView.adapter.setApplyBackground(false);
        this.actionBar.setAdaptiveBackground(this.listView);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass6(this));
        Drawable r06 = r17.getResources().getDrawable(R.drawable.ic_ab_done).mutate();
        int r3 = Theme.key_actionBarDefaultIcon;
        r06.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r3), PorterDuff.Mode.MULTIPLY));
        this.doneButtonDrawable = new CrossfadeDrawable(r06, new CircularProgressDrawable(Theme.getColor(r3)));
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(1, this.doneButtonDrawable, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        checkDone(false, true);
        this.listView.addOnLayoutChangeListener(new AnonymousClass7(this));
        this.listView.addOnScrollListener(new AnonymousClass8(this));
        this.listView.doNotDetachViews();
        this.listView.setClipChildren(false);
        View r07 = this.fragmentView;
        if ((r07 instanceof ViewGroup) == false) goto L5;
        ((ViewGroup) r07).setClipChildren(false);
    L5:
        setValue();
        new KeyboardNotifier(this.fragmentView, new BusinessIntroActivity$$ExternalSyntheticLambda0(this));
        return this.fragmentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.userInfoDidLoad) goto L6;
        setValue();
        return;
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public void fillItems(ArrayList<UItem> r3, UniversalAdapter r4) {
        r3.add(UItem.asCustom(this.previewContainer));
        r3.add(UItem.asHeader(LocaleController.getString(R.string.BusinessIntroHeader)));
        r3.add(UItem.asCustom(this.titleEdit));
        r3.add(UItem.asCustom(this.messageEdit));
        if (this.stickerRandom == false) goto L6;
        r3.add(UItem.asButton(1, LocaleController.getString(R.string.BusinessIntroSticker), LocaleController.getString(R.string.BusinessIntroStickerRandom)));
    L9:
        r3.add(UItem.asShadow(LocaleController.getString(R.string.BusinessIntroInfo)));
        boolean r42 = isEmpty();
        this.clearVisible = !r42;
        if (r42 == true) goto L12;
        r3.add(UItem.asShadow(null));
        r3.add(UItem.asButton(2, LocaleController.getString(R.string.BusinessIntroReset)).red());
    L12:
        r3.add(UItem.asLargeShadow(null));
        return;
    L6:
        if (this.inputStickerPath == null) goto L8;
        r3.add(UItem.asStickerButton(1, LocaleController.getString(R.string.BusinessIntroSticker), this.inputStickerPath));
        goto L9
    L8:
        r3.add(UItem.asStickerButton(1, LocaleController.getString(R.string.BusinessIntroSticker), this.sticker));
        goto L9
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.BusinessIntro);
    }

    public boolean hasChanges() {
        String r0 = this.titleEdit.getText().toString();
        String r1 = this.currentTitle;
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 != null) goto L6;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (TextUtils.equals(r0, r1) == false) goto L28;
        String r02 = this.messageEdit.getText().toString();
        String r12 = this.currentMessage;
        if (r12 == null) goto L12;
        r2 = r12;
    L12:
        if (TextUtils.equals(r02, r2) == false) goto L31;
        boolean r03 = this.stickerRandom;
        if (r03 == true) goto L19;
        TLRPC.Document r13 = this.sticker;
        if (r13 == null) goto L19;
        long r14 = r13.id;
    L21:
        if (r14 != this.currentSticker) goto L32;
        if (r03 == false) goto L24;
        return false;
    L24:
        if (this.inputSticker != null) goto L33;
        return false;
    L33:
        return true;
    L32:
        return true;
    L19:
        r14 = 0;
        goto L21
    L31:
        return true;
    L28:
        return true;
    }

    public boolean isEmpty() {
        EditTextCell r0 = this.titleEdit;
        if (r0 != null) goto L5;
    L16:
        return true;
    L5:
        if (this.messageEdit == null) goto L16;
        if (TextUtils.isEmpty(r0.getText()) == true) goto L10;
        return false;
    L10:
        if (TextUtils.isEmpty(this.messageEdit.getText()) == true) goto L12;
        return false;
    L12:
        if (this.stickerRandom == false) goto L18;
        return true;
    L18:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        if (hasChanges() == false) goto L9;
        if (r3 == false) goto L10;
        AlertDialog.Builder r32 = new AlertDialog.Builder(getParentActivity());
        r32.setTitle(LocaleController.getString(R.string.UnsavedChanges));
        r32.setMessage(LocaleController.getString(R.string.BusinessIntroUnsavedChanges));
        r32.setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new BusinessIntroActivity$$ExternalSyntheticLambda3(this));
        r32.setNegativeButton(LocaleController.getString(R.string.PassportDiscard), new BusinessIntroActivity$$ExternalSyntheticLambda4(this));
        showDialog(r32.create());
        return false;
    L10:
        return false;
    L9:
        return super.onBackPressed(r3);
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        int r12 = r1.id;
        if (r12 != 1) goto L7;
        EmojiBottomSheet r13 = new EmojiBottomSheet(getContext(), true, getResourceProvider(), true);
        r13.whenDocumentSelected(new BusinessIntroActivity$$ExternalSyntheticLambda1(this, r2));
        r13.whenPlusSelected(new BusinessIntroActivity$$ExternalSyntheticLambda2(this));
        showDialog(r13);
        return;
    L7:
        if (r12 != 2) goto L10;
        this.titleEdit.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.messageEdit.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        AndroidUtilities.hideKeyboard(this.titleEdit.editText);
        AndroidUtilities.hideKeyboard(this.messageEdit.editText);
        this.stickerRandom = true;
        this.greetingsView.setPreview(_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);
        ChatGreetingsView r14 = this.greetingsView;
        TLRPC.Document r22 = MediaDataController.getInstance(this.currentAccount).getGreetingsSticker();
        this.sticker = r22;
        r14.setSticker(r22);
        AndroidUtilities.cancelRunOnUIThread(this.updateRandomStickerRunnable);
        AndroidUtilities.runOnUIThread(this.updateRandomStickerRunnable, 5000);
        checkDone(true, false);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.userInfoDidLoad);
        MediaDataController.getInstance(this.currentAccount).checkStickers(0);
        MediaDataController.getInstance(this.currentAccount).loadRecents(0, false, true, false);
        MediaDataController.getInstance(this.currentAccount).loadRecents(2, false, true, false);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.userInfoDidLoad);
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        return false;
    }
}
