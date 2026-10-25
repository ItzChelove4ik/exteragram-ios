package org.telegram.ui.Business;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.WebFile;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedColor;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.ClipRoundedDrawable;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public class LocationActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private final int BUTTON_CLEAR;
    private final int BUTTON_MAP;
    final int MAX_NAME_LENGTH;
    private String address;
    private boolean clearVisible;
    private TLRPC.TL_businessLocation currentLocation;
    private ActionBarMenuItem doneButton;
    private CrossfadeDrawable doneButtonDrawable;
    private EditTextBoldCursor editText;
    private FrameLayout editTextContainer;
    private TLRPC.GeoPoint geo;
    private boolean ignoreEditText;
    private UniversalRecyclerView listView;
    private boolean mapAddress;
    private ClipRoundedDrawable mapLoadingDrawable;
    private View mapMarker;
    private BackupImageView mapPreview;
    private FrameLayout mapPreviewContainer;
    private int shiftDp;
    private boolean valueSet;

    /* renamed from: org.telegram.ui.Business.LocationActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass1(LocationActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            LocationActivity.m6377$$Nest$mprocessDone(this.this$0);
            return;
        L12:
            return;
        L5:
            if (this.this$0.onBackPressed(true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Business.LocationActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends EditTextBoldCursor {
        AnimatedTextView.AnimatedTextDrawable limit;
        AnimatedColor limitColor;
        private int limitCount;
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass2(LocationActivity r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            this.limitColor = new AnimatedColor(this);
            AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
            this.limit = r0;
            r0.setAnimationProperties(0.2f, 0, 160, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.limit.setTextSize(AndroidUtilities.dp(15.33f));
            this.limit.setCallback(this);
            this.limit.setGravity(5);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            AnimatedTextView.AnimatedTextDrawable r0 = this.limit;
            AnimatedColor r1 = this.limitColor;
            if (this.limitCount >= 0) goto L5;
            int r2 = Theme.key_text_RedRegular;
        L6:
            r0.setTextColor(r1.set(Theme.getColor(r2, this.this$0.getResourceProvider())));
            this.limit.setBounds(getScrollX(), 0, getScrollX() + getWidth(), getHeight());
            this.limit.draw(r6);
            return;
        L5:
            r2 = Theme.key_dialogSearchHint;
            goto L6
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            super.onTextChanged(r1, r2, r3, r4);
            if (this.limit == null) goto L10;
            this.limitCount = 96 - r1.length();
            this.limit.cancelAnimation();
            AnimatedTextView.AnimatedTextDrawable r12 = this.limit;
            int r22 = this.limitCount;
            String r42 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r22 > 12) goto L8;
            r42 = _UrlKt.FRAGMENT_ENCODE_SET + this.limitCount;
        L8:
            r12.setText(r42);
            return;
        }

        @Override // android.widget.TextView, android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.limit) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Business.LocationActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements TextWatcher {
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass3(LocationActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            if (LocationActivity.m6371$$Nest$fgetignoreEditText(this.this$0) == true) goto L6;
            LocationActivity.m6375$$Nest$fputmapAddress(this.this$0, false);
            LocationActivity.m6374$$Nest$fputaddress(this.this$0, r3.toString());
            LocationActivity.m6376$$Nest$mcheckDone(this.this$0, true);
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Business.LocationActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements InputFilter {
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass4(LocationActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence r1, int r2, int r3, Spanned r4, int r5, int r6) {
            if (r1 == null) goto L7;
            String r0 = r1.toString();
            if (r0.contains("\n") == true) goto L6;
            return null;
        L6:
            return r0.replaceAll("\n", _UrlKt.FRAGMENT_ENCODE_SET);
        L7:
            return null;
        }
    }

    /* renamed from: org.telegram.ui.Business.LocationActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends BackupImageView {
        final /* synthetic */ LocationActivity this$0;

        /* renamed from: org.telegram.ui.Business.LocationActivity$5$1, reason: invalid class name */
        public class AnonymousClass1 extends ImageReceiver {
            final /* synthetic */ AnonymousClass5 this$1;

            public AnonymousClass1(AnonymousClass5 r1, View r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.messenger.ImageReceiver
            public boolean setImageBitmapByKey(Drawable r4, String r5, int r6, boolean r7, int r8) {
                if (r4 == null) goto L7;
                if (r6 == 1) goto L7;
                LocationActivity.m6373$$Nest$fgetmapMarker(this.this$1.this$0).animate().alpha(1.0f).translationY(0.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_BACK).setDuration(250).start();
            L7:
                return super.setImageBitmapByKey(r4, r5, r6, r7, r8);
            }
        }

        public AnonymousClass5(LocationActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.BackupImageView
        public ImageReceiver createImageReciever() {
            return new AnonymousClass1(this, this);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(240.0f), TLObject.FLAG_30));
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != LocationActivity.m6372$$Nest$fgetmapLoadingDrawable(this.this$0)) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Business.LocationActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends View {
        final AvatarDrawable avatarDrawable;
        final ImageReceiver avatarImage;
        final Drawable pin;
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass6(LocationActivity r2, Context r3) {
            this.this$0 = r2;
            super(r3);
            this.pin = getContext().getResources().getDrawable(R.drawable.map_pin_photo).mutate();
            AvatarDrawable r32 = new AvatarDrawable();
            this.avatarDrawable = r32;
            ImageReceiver r0 = new ImageReceiver(this);
            this.avatarImage = r0;
            r32.setInfo(r2.getUserConfig().getCurrentUser());
            r0.setForUserOrChat(r2.getUserConfig().getCurrentUser(), r32);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r6) {
            this.pin.setBounds(0, 0, AndroidUtilities.dp(62.0f), AndroidUtilities.dp(85.0f));
            this.pin.draw(r6);
            this.avatarImage.setRoundRadius(AndroidUtilities.dp(62.0f));
            this.avatarImage.setImageCoords(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f));
            this.avatarImage.draw(r6);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(62.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(85.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: $r8$lambda$-OxsYmqcHJ5G9DLJgCq9-oQxRUo, reason: not valid java name */
    public static /* synthetic */ void m6367$r8$lambda$OxsYmqcHJ5G9DLJgCq9oQxRUo(LocationActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FSkNTns7nTW_pyvhVbYxYGQzNdw(LocationActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$processDone$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$HIfGsLEbk4O5wzQRwoACf389yiE(LocationActivity r0, org.telegram.ui.LocationActivity r1, AlertDialog r2) {
        r0.lambda$showLocationAlert$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$OSqn_qBZEMMRBIgI54ZRELWwBDg(LocationActivity r0, org.telegram.ui.LocationActivity r1, TLRPC.MessageMedia r2, int r3, boolean r4, int r5, long r6) {
        r0.lambda$showLocationAlert$7(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$PxlioW-4gfUTWt8OoXUgcOaBXz4, reason: not valid java name */
    public static /* synthetic */ void m6368$r8$lambda$PxlioW4gfUTWt8OoXUgcOaBXz4(LocationActivity r0, AlertDialog r1, org.telegram.ui.LocationActivity r2) {
        r0.lambda$showLocationAlert$8(r1, r2);
    }

    /* renamed from: $r8$lambda$TNpQWihHR8eL6x-vBIJCaho7640, reason: not valid java name */
    public static /* synthetic */ void m6369$r8$lambda$TNpQWihHR8eL6xvBIJCaho7640(LocationActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_ZqYsviTztG_mpMZXjC7nQwo13Y(LocationActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$onClick$4(r1, r2);
    }

    /* renamed from: $r8$lambda$b2FX7y4Bi5g3x7QK-0MlvQguBeA, reason: not valid java name */
    public static /* synthetic */ void m6370$r8$lambda$b2FX7y4Bi5g3x7QK0MlvQguBeA(LocationActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$bZd68Hvhq2UvCGo9y_ANDWzsN7M(LocationActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$cs1HzGVXfnfCMZrJ1m_Q5by5UlA(LocationActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$onClick$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sQQj8D_aj9iGSaycIxkLPhLJSIQ(LocationActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$processDone$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$vIB_i2LPkpj8R8HqKyI39pdAqqE(LocationActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onClick$6(r1, r2);
    }

    /* renamed from: -$$Nest$fgetignoreEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6371$$Nest$fgetignoreEditText(LocationActivity r0) {
        return r0.ignoreEditText;
    }

    /* renamed from: -$$Nest$fgetmapLoadingDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ClipRoundedDrawable m6372$$Nest$fgetmapLoadingDrawable(LocationActivity r0) {
        return r0.mapLoadingDrawable;
    }

    /* renamed from: -$$Nest$fgetmapMarker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m6373$$Nest$fgetmapMarker(LocationActivity r0) {
        return r0.mapMarker;
    }

    /* renamed from: -$$Nest$fputaddress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6374$$Nest$fputaddress(LocationActivity r0, String r1) {
        r0.address = r1;
    }

    /* renamed from: -$$Nest$fputmapAddress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6375$$Nest$fputmapAddress(LocationActivity r0, boolean r1) {
        r0.mapAddress = r1;
    }

    /* renamed from: -$$Nest$mcheckDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6376$$Nest$mcheckDone(LocationActivity r0, boolean r1) {
        r0.checkDone(r1);
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6377$$Nest$mprocessDone(LocationActivity r0) {
        r0.processDone();
    }

    public LocationActivity() {
        this.MAX_NAME_LENGTH = 96;
        this.shiftDp = -4;
        this.BUTTON_MAP = 1;
        this.BUTTON_CLEAR = 2;
    }

    private void checkDone(boolean r5) {
        if (this.doneButton == null) goto L48;
        boolean r0 = hasChanges();
        this.doneButton.setEnabled(r0);
        ActionBarMenuItem r1 = this.doneButton;
        float r2 = 0.0f;
        if (r5 == false) goto L19;
        ViewPropertyAnimator r52 = r1.animate();
        if (r0 == false) goto L10;
        float r12 = 1.0f;
    L11:
        ViewPropertyAnimator r53 = r52.alpha(r12);
        if (r0 == false) goto L14;
        float r13 = 1.0f;
    L15:
        ViewPropertyAnimator r54 = r53.scaleX(r13);
        if (r0 == false) goto L18;
        r2 = 1.0f;
    L18:
        r54.scaleY(r2).setDuration(180).start();
    L30:
        UniversalRecyclerView r55 = this.listView;
        if (r55 != null) goto L33;
        return;
    L33:
        if (r55.adapter == null) goto L46;
        boolean r56 = this.clearVisible;
        if (this.currentLocation != null) goto L37;
    L41:
        boolean r02 = false;
    L42:
        if (r56 == r02) goto L47;
        this.listView.adapter.update(true);
        return;
    L47:
        return;
    L37:
        if (this.geo == null) goto L39;
    L40:
        r02 = true;
        goto L42
    L39:
        if (TextUtils.isEmpty(this.address) == true) goto L41;
    L46:
        return;
    L14:
        r13 = 0.0f;
        goto L15
    L10:
        r12 = 0.0f;
        goto L11
    L19:
        if (r0 == false) goto L21;
        float r57 = 1.0f;
    L22:
        r1.setAlpha(r57);
        ActionBarMenuItem r58 = this.doneButton;
        if (r0 == false) goto L25;
        float r14 = 1.0f;
    L26:
        r58.setScaleX(r14);
        ActionBarMenuItem r59 = this.doneButton;
        if (r0 == false) goto L29;
        r2 = 1.0f;
    L29:
        r59.setScaleY(r2);
        goto L30
    L25:
        r14 = 0.0f;
        goto L26
    L21:
        r57 = 0.0f;
        goto L22
    }

    private void fillItems(ArrayList<UItem> r5, UniversalAdapter r6) {
        r5.add(UItem.asTopView(LocaleController.getString(R.string.BusinessLocation), LocaleController.getString(R.string.BusinessLocationInfo), R.raw.biz_map));
        r5.add(UItem.asCustom(this.editTextContainer));
        r5.add(UItem.asShadow(null));
        UItem r0 = UItem.asCheck(1, LocaleController.getString(R.string.BusinessLocationMap));
        boolean r3 = false;
        if (this.geo == null) goto L5;
        boolean r2 = true;
    L6:
        r5.add(r0.setChecked(r2));
        if (this.geo == null) goto L9;
        r5.add(UItem.asCustom(this.mapPreviewContainer));
    L9:
        r5.add(UItem.asShadow(null));
        if (this.currentLocation != null) goto L12;
    L16:
        this.clearVisible = r3;
        if (r3 == false) goto L19;
        r5.add(UItem.asButton(2, LocaleController.getString(R.string.BusinessLocationClear)).red());
        r5.add(UItem.asShadow(null));
    L19:
        checkDone(true);
        return;
    L12:
        if (this.geo == null) goto L14;
    L15:
        r3 = true;
        goto L16
    L14:
        if (TextUtils.isEmpty(this.address) == true) goto L16;
    L5:
        r2 = false;
        goto L6
    }

    private /* synthetic */ void lambda$onBackPressed$2(AlertDialog r1, int r2) {
        processDone();
    }

    private /* synthetic */ void lambda$onBackPressed$3(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$onClick$4(TLRPC.TL_error r3, TLObject r4) {
        this.doneButtonDrawable.animateToProgress(0.0f);
        if (r3 == null) goto L7;
        BulletinFactory.showError(r3);
        return;
    L7:
        if ((r4 instanceof TLRPC.TL_boolFalse) == false) goto L10;
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
        return;
    L10:
        finishFragment();
    }

    private /* synthetic */ void lambda$onClick$5(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new LocationActivity$$ExternalSyntheticLambda11(this, r3, r2));
    }

    private /* synthetic */ void lambda$onClick$6(AlertDialog r3, int r4) {
        this.doneButtonDrawable.animateToProgress(1.0f);
        TLRPC.UserFull r32 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        TL_account.updateBusinessLocation r42 = new TL_account.updateBusinessLocation();
        if (r32 == null) goto L5;
        r32.business_location = null;
        r32.flags2 &= -3;
    L5:
        getConnectionsManager().sendRequest(r42, new LocationActivity$$ExternalSyntheticLambda6(this));
    }

    private /* synthetic */ void lambda$processDone$0(TLRPC.TL_error r2, TLObject r3) {
        if (r2 == null) goto L7;
        this.doneButtonDrawable.animateToProgress(0.0f);
        BulletinFactory.showError(r2);
        return;
    L7:
        if ((r3 instanceof TLRPC.TL_boolFalse) == false) goto L10;
        this.doneButtonDrawable.animateToProgress(0.0f);
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
        return;
    L10:
        finishFragment();
    }

    private /* synthetic */ void lambda$processDone$1(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new LocationActivity$$ExternalSyntheticLambda9(this, r3, r2));
    }

    private /* synthetic */ void lambda$showLocationAlert$7(org.telegram.ui.LocationActivity r1, TLRPC.MessageMedia r2, int r3, boolean r4, int r5, long r6) {
        this.geo = r2.geo;
        if (TextUtils.isEmpty(this.address) == false) goto L7;
        if (TextUtils.isEmpty(r1.getAddressName()) == true) goto L7;
    L8:
        this.mapAddress = true;
        String r12 = r1.getAddressName();
        this.address = r12;
        if (r12 != null) goto L11;
        this.address = _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        EditTextBoldCursor r13 = this.editText;
        if (r13 == null) goto L14;
        this.ignoreEditText = true;
        r13.setText(this.address);
        EditTextBoldCursor r14 = this.editText;
        r14.setSelection(r14.getText().length());
        this.ignoreEditText = false;
    L14:
        updateMapPreview();
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L7:
        if (this.mapAddress == false) goto L14;
        goto L8
    }

    private /* synthetic */ void lambda$showLocationAlert$8(AlertDialog r1, org.telegram.ui.LocationActivity r2) {
        r1.dismiss();
        presentFragment(r2);
    }

    private /* synthetic */ void lambda$showLocationAlert$9(org.telegram.ui.LocationActivity r6, AlertDialog r7) {
        List<Address> r0 = new Geocoder(getContext(), LocaleController.getInstance().getCurrentLocale()).getFromLocationName(this.address, 1);     // Catch: Exception -> L6
        if (r0.isEmpty() == true) goto L8;
        Address r02 = r0.get(0);     // Catch: Exception -> L6
        TLRPC.TL_channelLocation r1 = new TLRPC.TL_channelLocation();     // Catch: Exception -> L6
        r1.address = this.address;     // Catch: Exception -> L6
        TLRPC.TL_geoPoint r2 = new TLRPC.TL_geoPoint();     // Catch: Exception -> L6
        r1.geo_point = r2;     // Catch: Exception -> L6
        r2.lat = r02.getLatitude();     // Catch: Exception -> L6
        TLRPC.GeoPoint r22 = r1.geo_point;     // Catch: Exception -> L6
        r22._long = r02.getLongitude();     // Catch: Exception -> L6
        r6.setInitialLocation(r1);     // Catch: Exception -> L6
    L8:
        AndroidUtilities.runOnUIThread(new LocationActivity$$ExternalSyntheticLambda10(this, r7, r6));
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    private void onClick(UItem r2, View r3, int r4, float r5, float r6) {
        int r32 = r2.id;
        if (r32 == 1) goto L12;
        if (r2.view == this.mapPreviewContainer) goto L12;
        if (r32 != 2) goto L20;
        AlertDialog.Builder r22 = new AlertDialog.Builder(getParentActivity());
        r22.setTitle(LocaleController.getString(R.string.BusinessLocationClearTitle));
        r22.setMessage(LocaleController.getString(R.string.BusinessLocationClearMessage));
        r22.setPositiveButton(LocaleController.getString(R.string.Remove), new LocationActivity$$ExternalSyntheticLambda5(this));
        r22.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r22.create());
        return;
    L20:
        return;
    L12:
        if (this.geo != null) goto L14;
    L18:
        showLocationAlert();
        return;
    L14:
        if (r2.view == this.mapPreviewContainer) goto L18;
        this.geo = null;
        this.listView.adapter.update(true);
    }

    private void processDone() {
        if (this.doneButtonDrawable.getProgress() <= 0.0f) goto L6;
        return;
    L6:
        if (this.geo == null) goto L8;
    L10:
        boolean r0 = false;
    L11:
        if (r0 == false) goto L13;
    L26:
        this.doneButtonDrawable.animateToProgress(1.0f);
        TLRPC.UserFull r3 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        TL_account.updateBusinessLocation r4 = new TL_account.updateBusinessLocation();
        if (r0 == false) goto L29;
        if (r3 == null) goto L38;
        r3.flags2 &= -3;
        r3.business_location = null;
    L38:
        getConnectionsManager().sendRequest(r4, new LocationActivity$$ExternalSyntheticLambda4(this));
        getMessagesStorage().updateUserInfo(r3, false);
        return;
    L29:
        if (this.geo == null) goto L31;
        r4.flags |= 2;
        TLRPC.TL_inputGeoPoint r02 = new TLRPC.TL_inputGeoPoint();
        r4.geo_point = r02;
        TLRPC.GeoPoint r5 = this.geo;
        r02.lat = r5.lat;
        r02._long = r5._long;
    L31:
        r4.flags |= 1;
        r4.address = this.address;
        if (r3 == null) goto L38;
        r3.flags2 |= 2;
        TLRPC.TL_businessLocation r03 = new TLRPC.TL_businessLocation();
        r3.business_location = r03;
        r03.address = this.address;
        if (this.geo == null) goto L38;
        r03.flags = 1 | r03.flags;
        r03.geo_point = new TLRPC.TL_geoPoint();
        TLRPC.GeoPoint r04 = r3.business_location.geo_point;
        TLRPC.GeoPoint r2 = this.geo;
        r04.lat = r2.lat;
        r04._long = r2._long;
        goto L38
    L13:
        if (hasChanges() == true) goto L16;
        finishFragment();
        return;
    L16:
        String r32 = this.address;
        if (r32 != null) goto L19;
        String r33 = _UrlKt.FRAGMENT_ENCODE_SET;
    L21:
        if (TextUtils.isEmpty(r33) == false) goto L23;
    L24:
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        EditTextBoldCursor r05 = this.editText;
        int r1 = -this.shiftDp;
        this.shiftDp = r1;
        AndroidUtilities.shakeViewSpring(r05, r1);
        return;
    L23:
        if (r33.length() <= 96) goto L26;
    L19:
        r33 = r32.trim();
        goto L21
    L8:
        if (TextUtils.isEmpty(this.address) == false) goto L10;
        r0 = true;
        goto L11
    }

    private void setValue() {
        if (this.valueSet == false) goto L5;
        return;
    L5:
        long r0 = getUserConfig().getClientUserId();
        TLRPC.UserFull r02 = getMessagesController().getUserFull(r0);
        if (r02 != null) goto L9;
        getMessagesController().loadUserInfo(getUserConfig().getCurrentUser(), true, getClassGuid());
        return;
    L9:
        TLRPC.TL_businessLocation r03 = r02.business_location;
        this.currentLocation = r03;
        if (r03 == null) goto L12;
        this.geo = r03.geo_point;
        this.address = r03.address;
    L13:
        EditTextBoldCursor r04 = this.editText;
        if (r04 == null) goto L16;
        this.ignoreEditText = true;
        r04.setText(this.address);
        EditTextBoldCursor r05 = this.editText;
        r05.setSelection(r05.getText().length());
        this.ignoreEditText = false;
    L16:
        updateMapPreview();
        UniversalRecyclerView r06 = this.listView;
        if (r06 == null) goto L21;
        UniversalAdapter r07 = r06.adapter;
        if (r07 == null) goto L21;
        r07.update(true);
    L21:
        this.valueSet = true;
        return;
    L12:
        this.geo = null;
        this.address = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L13
    }

    private void showLocationAlert() {
        org.telegram.ui.LocationActivity r0 = new org.telegram.ui.LocationActivity(8);
        if (this.geo == null) goto L5;
        TLRPC.TL_channelLocation r1 = new TLRPC.TL_channelLocation();
        r1.address = this.address;
        r1.geo_point = this.geo;
        r0.setInitialLocation(r1);
    L5:
        r0.setDelegate(new LocationActivity$$ExternalSyntheticLambda7(this, r0));
        if (this.geo == null) goto L8;
    L11:
        presentFragment(r0);
        return;
    L8:
        if (TextUtils.isEmpty(this.address) == true) goto L11;
        AlertDialog r12 = new AlertDialog(getContext(), 3);
        r12.setCanCancel(false);
        r12.showDelayed(200);
        Utilities.searchQueue.postRunnable(new LocationActivity$$ExternalSyntheticLambda8(this, r0, r12));
    }

    private void updateMapPreview() {
        View r0 = this.mapMarker;
        if (r0 == null) goto L17;
        BackupImageView r1 = this.mapPreview;
        if (r1 != null) goto L8;
        return;
    L8:
        if (this.geo == null) goto L15;
        r0.setAlpha(0.0f);
        this.mapMarker.setTranslationY(-AndroidUtilities.dp(12.0f));
        if (this.mapPreview.getMeasuredWidth() > 0) goto L12;
        int r02 = AndroidUtilities.displaySize.x;
    L13:
        float r12 = AndroidUtilities.density;
        int r03 = (int) (r02 / r12);
        int r122 = Math.min(2, (int) Math.ceil(r12));
        BackupImageView r13 = this.mapPreview;
        TLRPC.GeoPoint r2 = this.geo;
        r13.setImage(ImageLocation.getForWebFile(WebFile.createWithGeoPoint(r2.lat, r2._long, 0, r122 * r03, r122 * 240, 15, r122)), r03 + "_240", this.mapLoadingDrawable, 0, null);
        return;
    L12:
        r02 = this.mapPreview.getMeasuredWidth();
        goto L13
    L15:
        r1.setImageBitmap(null);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r15) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.BusinessLocation));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        Drawable r0 = r15.getResources().getDrawable(R.drawable.ic_ab_done).mutate();
        int r3 = Theme.key_actionBarDefaultIcon;
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r3), PorterDuff.Mode.MULTIPLY));
        this.doneButtonDrawable = new CrossfadeDrawable(r0, new CircularProgressDrawable(Theme.getColor(r3)));
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(1, this.doneButtonDrawable, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        checkDone(false);
        FrameLayout r2 = new FrameLayout(r15);
        r2.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        AnonymousClass2 r32 = new AnonymousClass2(this, getContext());
        this.editText = r32;
        r32.setTextSize(1, 17.0f);
        this.editText.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r33 = this.editText;
        int r4 = Theme.key_windowBackgroundWhiteBlackText;
        r33.setTextColor(Theme.getColor(r4));
        this.editText.setBackgroundDrawable(null);
        int r6 = 5;
        this.editText.setMaxLines(5);
        this.editText.setSingleLine(false);
        this.editText.setPadding(0, 0, AndroidUtilities.dp(42.0f), 0);
        EditTextBoldCursor r34 = this.editText;
        if (LocaleController.isRTL == true) goto L6;
        r6 = 3;
    L6:
        r34.setGravity(r6 | 48);
        this.editText.setInputType(180225);
        this.editText.setHint(LocaleController.getString(R.string.BusinessLocationAddress));
        this.editText.setCursorColor(Theme.getColor(r4));
        this.editText.setCursorSize(AndroidUtilities.dp(19.0f));
        this.editText.setCursorWidth(1.5f);
        this.editText.addTextChangedListener(new AnonymousClass3(this));
        this.editText.setFilters(new InputFilter[]{new AnonymousClass4(this)});
        FrameLayout r35 = new FrameLayout(r15);
        this.editTextContainer = r35;
        r35.addView(this.editText, LayoutHelper.createFrame(-1, -1.0f, 48, 21.0f, 15.0f, 21.0f, 15.0f));
        FrameLayout r36 = this.editTextContainer;
        int r62 = Theme.key_windowBackgroundWhite;
        r36.setBackgroundColor(getThemedColor(r62));
        EditTextBoldCursor r37 = this.editText;
        if (r37 == null) goto L9;
        this.ignoreEditText = true;
        r37.setText(this.address);
        EditTextBoldCursor r38 = this.editText;
        r38.setSelection(r38.getText().length());
        this.ignoreEditText = false;
    L9:
        this.mapPreview = new AnonymousClass5(this, r15);
        SvgHelper.SvgDrawable r39 = DocumentObject.getSvgThumb(R.raw.map_placeholder, Theme.key_chat_outLocationIcon, 0.2f);
        r39.setColorKey(r4, getResourceProvider());
        r39.setAspectCenter(true);
        r39.setParent(this.mapPreview.getImageReceiver());
        ClipRoundedDrawable r42 = new ClipRoundedDrawable(r39);
        this.mapLoadingDrawable = r42;
        r42.setCallback(this.mapPreview);
        this.mapPreview.setBackgroundColor(getThemedColor(r62));
        this.mapMarker = new AnonymousClass6(this, r15);
        FrameLayout r310 = new FrameLayout(r15);
        this.mapPreviewContainer = r310;
        r310.addView(this.mapPreview, LayoutHelper.createFrame(-1, -1.0f));
        this.mapPreviewContainer.addView(this.mapMarker, LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, -31.0f, 0.0f, 0.0f));
        updateMapPreview();
        UniversalRecyclerView r152 = new UniversalRecyclerView(this, new LocationActivity$$ExternalSyntheticLambda0(this), new LocationActivity$$ExternalSyntheticLambda1(this), null);
        this.listView = r152;
        r152.setSections();
        this.listView.adapter.setApplyBackground(false);
        r2.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.actionBar.setAdaptiveBackground(this.listView, true);
        setValue();
        this.fragmentView = r2;
        return r2;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.userInfoDidLoad) goto L6;
        setValue();
        return;
    }

    public boolean hasChanges() {
        if (this.geo == null) goto L5;
    L8:
        boolean r0 = true;
    L10:
        if (this.currentLocation == null) goto L12;
        boolean r3 = true;
    L13:
        if (r0 == r3) goto L16;
        return true;
    L16:
        if (this.geo == null) goto L18;
    L21:
        boolean r02 = true;
    L22:
        TLRPC.TL_businessLocation r32 = this.currentLocation;
        if (r32 != null) goto L25;
    L27:
        boolean r4 = false;
    L28:
        if (r02 == r4) goto L30;
        return true;
    L30:
        String r03 = this.address;
        if (r32 == null) goto L33;
        String r33 = r32.address;
    L35:
        if (TextUtils.equals(r03, r33) == true) goto L37;
        return true;
    L37:
        TLRPC.GeoPoint r04 = this.geo;
        if (r04 == null) goto L40;
        boolean r34 = true;
    L41:
        TLRPC.TL_businessLocation r7 = this.currentLocation;
        if (r7 != null) goto L44;
    L46:
        boolean r42 = false;
    L47:
        if (r34 == r42) goto L49;
        return true;
    L49:
        if (r04 == null) goto L60;
        if (r7 == null) goto L59;
        TLRPC.GeoPoint r72 = r7.geo_point;
        if (r72 == null) goto L59;
        if ((r72 instanceof TLRPC.TL_geoPointEmpty) == true) goto L60;
        if (r04.lat != r72.lat) goto L59;
        if (r04._long == r72._long) goto L60;
    L59:
        return true;
    L60:
        return false;
    L44:
        if (r7.geo_point == null) goto L46;
        r42 = true;
        goto L47
    L40:
        r34 = false;
        goto L41
    L33:
        r33 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L35
    L25:
        if ((r32.geo_point instanceof TLRPC.TL_geoPointEmpty) == true) goto L27;
        r4 = true;
        goto L28
    L18:
        if (TextUtils.isEmpty(this.address) == false) goto L21;
        r02 = false;
        goto L22
    L12:
        r3 = false;
        goto L13
    L5:
        if (TextUtils.isEmpty(this.address) == false) goto L8;
        r0 = false;
        goto L10
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return !hasChanges();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r4) {
        if (this.geo == null) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (hasChanges() == false) goto L15;
        if (r0 == true) goto L15;
        if (r4 == false) goto L13;
        AlertDialog.Builder r42 = new AlertDialog.Builder(getParentActivity());
        r42.setTitle(LocaleController.getString(R.string.UnsavedChanges));
        r42.setMessage(LocaleController.getString(R.string.BusinessLocationUnsavedChanges));
        r42.setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new LocationActivity$$ExternalSyntheticLambda2(this));
        r42.setNegativeButton(LocaleController.getString(R.string.PassportDiscard), new LocationActivity$$ExternalSyntheticLambda3(this));
        showDialog(r42.create());
    L13:
        return false;
    L15:
        return super.onBackPressed(r4);
    L5:
        if (TextUtils.isEmpty(this.address) == false) goto L7;
        r0 = true;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.userInfoDidLoad);
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
}
