package org.telegram.ui;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.CameraScanActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedPhoneNumberEditText;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.ContextProgressView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.OutlineEditText;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Components.PermissionRequest;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.CountrySelectActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public class NewContactBottomSheet extends BottomSheet implements AdapterView.OnItemSelectedListener {
    private CheckBox2 checkBox;
    private LinearLayout checkLayout;
    private TextView checkTextView;
    int classGuid;
    private View codeDividerView;
    private AnimatedPhoneNumberEditText codeField;
    private HashMap<String, List<CountrySelectActivity.Country>> codesMap;
    private LinearLayout contentLayout;
    private ArrayList<CountrySelectActivity.Country> countriesArray;
    private String countryCodeForHint;
    private TextView countryFlag;
    private TextView doneButton;
    private FrameLayout doneButtonContainer;
    private boolean donePressed;
    private ContextProgressView editDoneItemProgress;
    private OutlineEditText firstNameField;
    private boolean ignoreOnPhoneChange;
    private boolean ignoreOnTextChange;
    private boolean ignoreSelection;
    private String initialFirstName;
    private String initialLastName;
    private String initialPhoneNumber;
    private boolean initialPhoneNumberWithCountryCode;
    private OutlineEditText lastNameField;
    private String lastPhone;
    private OutlineEditText notesField;
    BaseFragment parentFragment;
    private AnimatedPhoneNumberEditText phoneField;
    private HashMap<String, List<String>> phoneFormatMap;
    private OutlineTextContainerView phoneOutlineView;
    private ImageView phoneStatusView;
    private TextView plusTextView;
    private RadialProgressView progressView;
    private ButtonWithCounterView qrButton;
    private FrameLayout qrButtonContainer;
    private View qrButtonSeparator;
    private int requestingPhoneId;
    private TextView underPhoneTextView;
    private int wasCountryHintIndex;

    /* renamed from: org.telegram.ui.NewContactBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends TextView {
        final NotificationCenter.NotificationCenterDelegate delegate;
        final /* synthetic */ NewContactBottomSheet this$0;

        public static /* synthetic */ void $r8$lambda$6IAxcvZv8LZa0GZrClFvmYZCHJY(AnonymousClass1 r0, int r1, int r2, Object[] r3) {
            r0.lambda$$0(r1, r2, r3);
        }

        public AnonymousClass1(NewContactBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.delegate = new NewContactBottomSheet$1$$ExternalSyntheticLambda0(this);
        }

        private /* synthetic */ void lambda$$0(int r1, int r2, Object[] r3) {
            invalidate();
        }

        @Override // android.widget.TextView, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            NotificationCenter.getGlobalInstance().addObserver(this.delegate, NotificationCenter.emojiLoaded);
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getGlobalInstance().removeObserver(this.delegate, NotificationCenter.emojiLoaded);
        }
    }

    /* renamed from: org.telegram.ui.NewContactBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 implements CountrySelectActivity.CountrySelectActivityDelegate {
        final /* synthetic */ NewContactBottomSheet this$0;

        public static /* synthetic */ void $r8$lambda$vlocP4DETKqm1hhxE1Dbm1p_AR0(AnonymousClass2 r0) {
            r0.lambda$didSelectCountry$0();
        }

        public AnonymousClass2(NewContactBottomSheet r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$didSelectCountry$0() {
            AndroidUtilities.showKeyboard(NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0));
        }

        @Override // org.telegram.ui.CountrySelectActivity.CountrySelectActivityDelegate
        public void didSelectCountry(CountrySelectActivity.Country r3) {
            this.this$0.selectCountry(r3);
            AndroidUtilities.runOnUIThread(new NewContactBottomSheet$2$$ExternalSyntheticLambda0(this), 300);
            NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).requestFocus();
            NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).setSelection(NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).length());
        }
    }

    /* renamed from: org.telegram.ui.NewContactBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatedPhoneNumberEditText {
        final /* synthetic */ NewContactBottomSheet this$0;

        public AnonymousClass3(NewContactBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean r1, int r2, Rect r3) {
            super.onFocusChanged(r1, r2, r3);
            OutlineTextContainerView r22 = NewContactBottomSheet.m16248$$Nest$fgetphoneOutlineView(this.this$0);
            if (r1 == false) goto L5;
        L8:
            float r0 = 1.0f;
        L9:
            r22.animateSelection(r0);
            return;
        L5:
            if (NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).isFocused() == true) goto L8;
            r0 = 0.0f;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.NewContactBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 implements TextWatcher {
        final /* synthetic */ NewContactBottomSheet this$0;

        public AnonymousClass4(NewContactBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r14) {
            if (NewContactBottomSheet.m16246$$Nest$fgetignoreOnTextChange(this.this$0) == false) goto L5;
            return;
        L5:
            NewContactBottomSheet.m16250$$Nest$fputignoreOnTextChange(this.this$0, true);
            String r142 = PhoneFormat.stripExceptNumbers(NewContactBottomSheet.m16242$$Nest$fgetcodeField(this.this$0).getText().toString());
            NewContactBottomSheet.m16242$$Nest$fgetcodeField(this.this$0).setText(r142);
            if (r142.length() != 0) goto L8;
            NewContactBottomSheet.m16252$$Nest$msetCountryButtonText(this.this$0, null);
            NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).setHintText(null);
        L65:
            NewContactBottomSheet.m16250$$Nest$fputignoreOnTextChange(this.this$0, false);
            NewContactBottomSheet.m16254$$Nest$mupdatedTextPhone(this.this$0);
            return;
        L8:
            int r5 = 4;
            if (r142.length() <= 4) goto L30;
        L10:
            if (r5 < 1) goto L27;
            String r1 = r142.substring(0, r5);
            List r6 = (List) NewContactBottomSheet.m16243$$Nest$fgetcodesMap(this.this$0).get(r1);
            if (r6 != null) goto L15;
            CountrySelectActivity.Country r62 = null;
        L24:
            if (r62 != null) goto L25;
            r5 = r5 - 1;
            goto L10
        L25:
            String r143 = r142.substring(r5) + NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).getText().toString();
            NewContactBottomSheet.m16242$$Nest$fgetcodeField(this.this$0).setText(r1);
            String r12 = r143;
            r142 = r1;
            boolean r52 = true;
        L28:
            if (r52 == true) goto L31;
            r12 = r142.substring(1) + NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).getText().toString();
            AnimatedPhoneNumberEditText r63 = NewContactBottomSheet.m16242$$Nest$fgetcodeField(this.this$0);
            r142 = r142.substring(0, 1);
            r63.setText(r142);
        L31:
            ArrayList r64 = NewContactBottomSheet.m16244$$Nest$fgetcountriesArray(this.this$0);
            int r7 = r64.size();
            int r8 = 0;
            int r10 = 0;
            CountrySelectActivity.Country r9 = null;
        L32:
            if (r10 >= r7) goto L38;
            Object r11 = r64.get(r10);
            r10 = r10 + 1;
            CountrySelectActivity.Country r112 = (CountrySelectActivity.Country) r11;
            if (r112.code.startsWith(r142) == false) goto L32;
            r8 = r8 + 1;
            if (r112.code.equals(r142) == false) goto L32;
            r9 = r112;
            goto L32
        L38:
            if (r8 != 1) goto L42;
            if (r9 == null) goto L42;
            if (r12 != null) goto L42;
            r12 = r142.substring(r9.code.length()) + NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).getText().toString();
            AnimatedPhoneNumberEditText r144 = NewContactBottomSheet.m16242$$Nest$fgetcodeField(this.this$0);
            String r65 = r9.code;
            r144.setText(r65);
            r142 = r65;
        L42:
            List r66 = (List) NewContactBottomSheet.m16243$$Nest$fgetcodesMap(this.this$0).get(r142);
            if (r66 != null) goto L46;
            CountrySelectActivity.Country r67 = null;
        L55:
            NewContactBottomSheet r4 = this.this$0;
            if (r67 == null) goto L58;
            NewContactBottomSheet.m16251$$Nest$fputignoreSelection(r4, true);
            NewContactBottomSheet.m16253$$Nest$msetCountryHint(this.this$0, r142, r67);
        L59:
            if (r52 == true) goto L61;
            NewContactBottomSheet.m16242$$Nest$fgetcodeField(this.this$0).setSelection(NewContactBottomSheet.m16242$$Nest$fgetcodeField(this.this$0).getText().length());
        L61:
            if (r12 == null) goto L65;
            if (r12.length() == 0) goto L65;
            NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).requestFocus();
            NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).setText(r12);
            NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).setSelection(NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).length());
            goto L65
        L58:
            NewContactBottomSheet.m16252$$Nest$msetCountryButtonText(r4, null);
            NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).setHintText(null);
            goto L59
        L46:
            if (r66.size() <= 1) goto L54;
            String r42 = MessagesController.getGlobalMainSettings().getString("phone_code_last_matched_" + r142, null);
            r67 = (CountrySelectActivity.Country) r66.get(r66.size() - 1);
            if (r42 == null) goto L55;
            ArrayList r72 = NewContactBottomSheet.m16244$$Nest$fgetcountriesArray(this.this$0);
            int r82 = r72.size();
            int r92 = 0;
        L50:
            if (r92 >= r82) goto L55;
            Object r102 = r72.get(r92);
            r92 = r92 + 1;
            CountrySelectActivity.Country r103 = (CountrySelectActivity.Country) r102;
            if (Objects.equals(r103.shortname, r42) == false) goto L50;
            r67 = r103;
            goto L55
        L54:
            r67 = (CountrySelectActivity.Country) r66.get(0);
            goto L55
        L15:
            if (r6.size() <= 1) goto L23;
            String r73 = MessagesController.getGlobalMainSettings().getString("phone_code_last_matched_".concat(r1), null);
            r62 = (CountrySelectActivity.Country) r6.get(r6.size() - 1);
            if (r73 == null) goto L24;
            ArrayList r83 = NewContactBottomSheet.m16244$$Nest$fgetcountriesArray(this.this$0);
            int r93 = r83.size();
            int r104 = 0;
        L19:
            if (r104 >= r93) goto L24;
            Object r113 = r83.get(r104);
            r104 = r104 + 1;
            CountrySelectActivity.Country r114 = (CountrySelectActivity.Country) r113;
            if (Objects.equals(r114.shortname, r73) == false) goto L19;
            r62 = r114;
            goto L24
        L23:
            r62 = (CountrySelectActivity.Country) r6.get(0);
            goto L24
        L27:
            r52 = false;
            r12 = null;
            goto L28
        L30:
            r52 = false;
            r12 = null;
            goto L31
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.NewContactBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatedPhoneNumberEditText {
        final /* synthetic */ NewContactBottomSheet this$0;

        public AnonymousClass5(NewContactBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean r1, int r2, Rect r3) {
            super.onFocusChanged(r1, r2, r3);
            OutlineTextContainerView r22 = NewContactBottomSheet.m16248$$Nest$fgetphoneOutlineView(this.this$0);
            if (r1 == false) goto L5;
        L8:
            float r0 = 1.0f;
        L9:
            r22.animateSelection(r0);
            return;
        L5:
            if (NewContactBottomSheet.m16242$$Nest$fgetcodeField(this.this$0).isFocused() == true) goto L8;
            r0 = 0.0f;
            goto L9
        }

        @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int r3, KeyEvent r4) {
            if (r3 != 67) goto L8;
            if (NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).length() != 0) goto L8;
            NewContactBottomSheet.m16242$$Nest$fgetcodeField(this.this$0).requestFocus();
            NewContactBottomSheet.m16242$$Nest$fgetcodeField(this.this$0).setSelection(NewContactBottomSheet.m16242$$Nest$fgetcodeField(this.this$0).length());
            NewContactBottomSheet.m16242$$Nest$fgetcodeField(this.this$0).dispatchKeyEvent(r4);
        L8:
            return super.onKeyDown(r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.NewContactBottomSheet$6, reason: invalid class name */
    public class AnonymousClass6 implements TextWatcher {
        private int actionPosition;
        private int characterAction;
        final /* synthetic */ NewContactBottomSheet this$0;

        public AnonymousClass6(NewContactBottomSheet r1) {
            this.this$0 = r1;
            this.characterAction = -1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r11) {
            if (NewContactBottomSheet.m16245$$Nest$fgetignoreOnPhoneChange(this.this$0) == false) goto L5;
            return;
        L5:
            int r0 = NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).getSelectionStart();
            String r1 = NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).getText().toString();
            if (this.characterAction != 3) goto L8;
            r1 = r1.substring(0, this.actionPosition).concat(r1.substring(this.actionPosition + 1));
            r0 = r0 - 1;
        L8:
            StringBuilder r2 = new StringBuilder(r1.length());
            int r6 = 0;
        L10:
            if (r6 >= r1.length()) goto L15;
            int r7 = r6 + 1;
            String r62 = r1.substring(r6, r7);
            if ("0123456789".contains(r62) == false) goto L14;
            r2.append(r62);
        L14:
            r6 = r7;
            goto L10
        L15:
            NewContactBottomSheet.m16249$$Nest$fputignoreOnPhoneChange(this.this$0, true);
            String r12 = NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).getHintText();
            if (r12 == null) goto L37;
            int r63 = 0;
        L19:
            if (r63 >= r2.length()) goto L37;
            if (r63 >= r12.length()) goto L31;
            if (r12.charAt(r63) != ' ') goto L30;
            r2.insert(r63, ' ');
            r63 = r63 + 1;
            if (r0 != r63) goto L30;
            int r72 = this.characterAction;
            if (r72 == 2) goto L30;
            if (r72 == 3) goto L30;
            r0 = r0 + 1;
        L30:
            r63 = r63 + 1;
            goto L19
        L31:
            r2.insert(r63, ' ');
            if (r0 != (r63 + 1)) goto L37;
            int r13 = this.characterAction;
            if (r13 == 2) goto L37;
            if (r13 == 3) goto L37;
            r0 = r0 + 1;
        L37:
            r11.replace(0, r11.length(), r2);
            if (r0 < 0) goto L40;
            NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).setSelection(Math.min(r0, NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).length()));
        L40:
            NewContactBottomSheet.m16247$$Nest$fgetphoneField(this.this$0).onTextChange();
            NewContactBottomSheet.m16249$$Nest$fputignoreOnPhoneChange(this.this$0, false);
            NewContactBottomSheet.m16254$$Nest$mupdatedTextPhone(this.this$0);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r2, int r3, int r4, int r5) {
            if (r4 != 0) goto L7;
            if (r5 != 1) goto L7;
            this.characterAction = 1;
            return;
        L7:
            if (r4 != 1) goto L16;
            if (r5 != 0) goto L16;
            if (r2.charAt(r3) != ' ') goto L14;
            if (r3 <= 0) goto L14;
            this.characterAction = 3;
            this.actionPosition = r3 - 1;
            return;
        L14:
            this.characterAction = 2;
            return;
        L16:
            this.characterAction = -1;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.NewContactBottomSheet$7, reason: invalid class name */
    public class AnonymousClass7 implements CameraScanActivity.CameraScanActivityDelegate {
        final /* synthetic */ NewContactBottomSheet this$0;

        public static /* synthetic */ void $r8$lambda$SJ59l0jRtRwwlz1VbS1ZtEKnbmQ(Long r4) {
            if (r4 != null) goto L4;
        L10:
            AndroidUtilities.runOnUIThread(new NewContactBottomSheet$7$$ExternalSyntheticLambda2());
            return;
        L4:
            if (r4.longValue() == LongCompanionObject.MAX_VALUE) goto L10;
            BaseFragment r0 = LaunchActivity.getSafeLastFragment();
            if (r0 == null) goto L12;
            r0.presentFragment(ProfileActivity.of(r4.longValue()));
            return;
        }

        /* renamed from: $r8$lambda$haDp_3KDjnFWTRycr5X38-rgqvs, reason: not valid java name */
        public static /* synthetic */ void m16255$r8$lambda$haDp_3KDjnFWTRycr5X38rgqvs() {
            BulletinFactory.global().createSimpleBulletin(LocaleController.getString(R.string.ScanQrCode), LocaleController.getString(R.string.ErrorOccurred)).show();
        }

        public static /* synthetic */ void $r8$lambda$u_UDTs14feypRWBS4_BaLVR6Eiw() {
            BulletinFactory.global().createSimpleBulletin(LocaleController.getString(R.string.ScanQrCode), LocaleController.getString(R.string.ErrorOccurred)).show();
        }

        public AnonymousClass7(NewContactBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.CameraScanActivity.CameraScanActivityDelegate
        public void didFindQr(String r2) {
            String r22 = Browser.extractUsername(r2);
            if (TextUtils.isEmpty(r22) == true) goto L6;
            MessagesController.getInstance(NewContactBottomSheet.access$000(this.this$0)).getUserNameResolver().resolve(r22, new NewContactBottomSheet$7$$ExternalSyntheticLambda0());
            return;
        L6:
            AndroidUtilities.runOnUIThread(new NewContactBottomSheet$7$$ExternalSyntheticLambda1());
        }
    }

    public static class AccountInfo {
    }

    public static /* synthetic */ String $r8$lambda$0M9bxP7_nAGGvZtAgup5X1aop3k(CountrySelectActivity.Country r0) {
        return r0.name;
    }

    public static /* synthetic */ void $r8$lambda$0PY0qvgygP7aPM219MWjKFoPcbM(Utilities.Callback r0, TLRPC.User r1) {
        r0.run(r1);
    }

    /* renamed from: $r8$lambda$0z0j2pYS5klEhhSfTKb-nZ_7ahQ, reason: not valid java name */
    public static /* synthetic */ void m16234$r8$lambda$0z0j2pYS5klEhhSfTKbnZ_7ahQ(NewContactBottomSheet r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    /* renamed from: $r8$lambda$3dMo8wzBk0k-JbnHMZGkWjxrUa0, reason: not valid java name */
    public static /* synthetic */ void m16235$r8$lambda$3dMo8wzBk0kJbnHMZGkWjxrUa0(NewContactBottomSheet r0, View r1) {
        r0.lambda$createView$6(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$5Cd9lGyGI5QTt7YvIi2qRk8Wpg4(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$9ifIqMxLheppfoRz6wX97W-gQ9w, reason: not valid java name */
    public static /* synthetic */ void m16236$r8$lambda$9ifIqMxLheppfoRz6wX97WgQ9w(NewContactBottomSheet r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$updatedPhone$20(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$CpljrHMaIAqFTZHiW6dKgh8o7nU(NewContactBottomSheet r0, View r1) {
        r0.lambda$createView$7(r1);
    }

    /* renamed from: $r8$lambda$DL8fv78VwHKyP3gJ-aC3QhX7vMw, reason: not valid java name */
    public static /* synthetic */ void m16237$r8$lambda$DL8fv78VwHKyP3gJaC3QhX7vMw(NewContactBottomSheet r0, String r1, TLRPC.User r2) {
        r0.lambda$updatedPhone$16(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$DdJXRzUZasLhsvu7l3NvCQbbBBU(NewContactBottomSheet r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$1(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$EtiHdbjDsnebNYG9Pf-nPiq-B_Y, reason: not valid java name */
    public static /* synthetic */ void m16238$r8$lambda$EtiHdbjDsnebNYG9PfnPiqB_Y(NewContactBottomSheet r0) {
        r0.lambda$dismiss$25();
    }

    public static /* synthetic */ void $r8$lambda$GJfB395zhaQDzkTF_oZ33bCXQ7k(NewContactBottomSheet r0, TLRPC.TL_contact r1, Utilities.Callback r2) {
        r0.lambda$updatedPhone$18(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$J7kNgOja4ip_TiqczYIQWUzafqg(NewContactBottomSheet r0) {
        r0.lambda$show$24();
    }

    public static /* synthetic */ void $r8$lambda$QACjOc6uZJ9a6QXB8Z4sy_5y7DQ(NewContactBottomSheet r0, Boolean r1) {
        r0.lambda$doOnDone$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$SThkTrpozKElu1GdN6D1wWxpYE0(NewContactBottomSheet r0, TLRPC.TL_contacts_importedContacts r1, TLRPC.TL_inputPhoneContact r2, TLRPC.TL_error r3, TLRPC.TL_contacts_importContacts r4) {
        r0.lambda$done$22(r1, r2, r3, r4);
    }

    public static /* synthetic */ boolean $r8$lambda$T45EydQYa54gWp9evuvPRpvLGwg(NewContactBottomSheet r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Vq1w46aXsnCPd0u7I1VD5vc3CKk(NewContactBottomSheet r0, TLRPC.User r1) {
        r0.lambda$updatedPhone$15(r1);
    }

    /* renamed from: $r8$lambda$ZJ2KERld7MlhiOMk-q_LaM4M9s8, reason: not valid java name */
    public static /* synthetic */ boolean m16239$r8$lambda$ZJ2KERld7MlhiOMkq_LaM4M9s8(NewContactBottomSheet r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$4(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$hDwQTMvJ5GwttteHJPULsbBtFUU(NewContactBottomSheet r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$jWeFzcfEek__E4Uim_1QrO9wHaY(NewContactBottomSheet r0, TLObject r1, Utilities.Callback r2) {
        r0.lambda$updatedPhone$19(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$jdPkE1D7MZCkGhpEdopsxSSl5Z8(NewContactBottomSheet r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$kVUsKvkEVB9d9fjGHYF2k_d2GzE(NewContactBottomSheet r0, boolean r1) {
        r0.lambda$updateQrButtonVisible$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$lA_d3h6Lkqdn9_2PP3dUApfOjfw(NewContactBottomSheet r0, View r1) {
        r0.lambda$createView$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$nwM5v4pJOChaKkaB19CZz_CcGJI(NewContactBottomSheet r0, String r1) {
        r0.lambda$updatedPhone$14(r1);
    }

    /* renamed from: $r8$lambda$onsNsJAGS0Gx3WSVhKxE2gw-x4M, reason: not valid java name */
    public static /* synthetic */ void m16240$r8$lambda$onsNsJAGS0Gx3WSVhKxE2gwx4M(NewContactBottomSheet r0, boolean r1) {
        r0.lambda$updateQrButtonVisible$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$tSkcyCoVlHIv5QM9xbOQS6XMSCk(NewContactBottomSheet r0, boolean r1) {
        r0.lambda$updateQrButtonVisible$11(r1);
    }

    /* renamed from: $r8$lambda$y7B7niSVWLnDL3UwrdJ-I--U4kY, reason: not valid java name */
    public static /* synthetic */ void m16241$r8$lambda$y7B7niSVWLnDL3UwrdJIU4kY(NewContactBottomSheet r0, TLRPC.TL_inputPhoneContact r1, TLRPC.TL_contacts_importContacts r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$done$23(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetcodeField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedPhoneNumberEditText m16242$$Nest$fgetcodeField(NewContactBottomSheet r0) {
        return r0.codeField;
    }

    /* renamed from: -$$Nest$fgetcodesMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16243$$Nest$fgetcodesMap(NewContactBottomSheet r0) {
        return r0.codesMap;
    }

    /* renamed from: -$$Nest$fgetcountriesArray, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16244$$Nest$fgetcountriesArray(NewContactBottomSheet r0) {
        return r0.countriesArray;
    }

    /* renamed from: -$$Nest$fgetignoreOnPhoneChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16245$$Nest$fgetignoreOnPhoneChange(NewContactBottomSheet r0) {
        return r0.ignoreOnPhoneChange;
    }

    /* renamed from: -$$Nest$fgetignoreOnTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16246$$Nest$fgetignoreOnTextChange(NewContactBottomSheet r0) {
        return r0.ignoreOnTextChange;
    }

    /* renamed from: -$$Nest$fgetphoneField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedPhoneNumberEditText m16247$$Nest$fgetphoneField(NewContactBottomSheet r0) {
        return r0.phoneField;
    }

    /* renamed from: -$$Nest$fgetphoneOutlineView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OutlineTextContainerView m16248$$Nest$fgetphoneOutlineView(NewContactBottomSheet r0) {
        return r0.phoneOutlineView;
    }

    /* renamed from: -$$Nest$fputignoreOnPhoneChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16249$$Nest$fputignoreOnPhoneChange(NewContactBottomSheet r0, boolean r1) {
        r0.ignoreOnPhoneChange = r1;
    }

    /* renamed from: -$$Nest$fputignoreOnTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16250$$Nest$fputignoreOnTextChange(NewContactBottomSheet r0, boolean r1) {
        r0.ignoreOnTextChange = r1;
    }

    /* renamed from: -$$Nest$fputignoreSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16251$$Nest$fputignoreSelection(NewContactBottomSheet r0, boolean r1) {
        r0.ignoreSelection = r1;
    }

    /* renamed from: -$$Nest$msetCountryButtonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16252$$Nest$msetCountryButtonText(NewContactBottomSheet r0, CharSequence r1) {
        r0.setCountryButtonText(r1);
    }

    /* renamed from: -$$Nest$msetCountryHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16253$$Nest$msetCountryHint(NewContactBottomSheet r0, String r1, CountrySelectActivity.Country r2) {
        r0.setCountryHint(r1, r2);
    }

    /* renamed from: -$$Nest$mupdatedTextPhone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16254$$Nest$mupdatedTextPhone(NewContactBottomSheet r0) {
        r0.updatedTextPhone();
    }

    public NewContactBottomSheet(BaseFragment r2, Context r3) {
        super(r3, true);
        this.countriesArray = new ArrayList();
        this.codesMap = new HashMap();
        this.phoneFormatMap = new HashMap();
        this.requestingPhoneId = -1;
        fixNavigationBar();
        this.waitingKeyboard = true;
        this.smoothKeyboardAnimationEnabled = true;
        this.classGuid = ConnectionsManager.generateClassGuid();
        this.parentFragment = r2;
        setCustomView(createView(getContext()));
        setTitle(LocaleController.getString(R.string.NewContactTitle), true);
    }

    public static /* synthetic */ int access$000(NewContactBottomSheet r0) {
        return r0.currentAccount;
    }

    private void doOnDone() {
        if (this.donePressed == true) goto L27;
        BaseFragment r0 = this.parentFragment;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.getParentActivity() != null) goto L10;
        return;
    L10:
        if (this.firstNameField.getEditText().length() != 0) goto L14;
        VibratorUtils.vibrate();
        AndroidUtilities.shakeView(this.firstNameField);
        return;
    L14:
        if (this.codeField.length() != 0) goto L18;
        VibratorUtils.vibrate();
        AndroidUtilities.shakeView(this.codeField);
        return;
    L18:
        if (this.phoneField.length() != 0) goto L22;
        VibratorUtils.vibrate();
        AndroidUtilities.shakeView(this.phoneField);
        return;
    L22:
        if (this.checkBox.isChecked() == false) goto L25;
        PermissionRequest.ensurePermission(R.raw.permission_request_contacts, R.string.PermissionNoContactsSaving, "android.permission.WRITE_CONTACTS", new NewContactBottomSheet$$ExternalSyntheticLambda13(this));
        return;
    L25:
        done();
        return;
    }

    private void done() {
        this.donePressed = true;
        showEditDoneProgress(true, true);
        String r4 = "+" + this.codeField.getText().toString() + this.phoneField.getText().toString();
        String r5 = this.firstNameField.getEditText().getText().toString();
        String r6 = this.lastNameField.getEditText().getText().toString();
        if (this.notesField.getVisibility() != 0) goto L5;
        String r1 = this.notesField.getEditText().getText().toString();
    L6:
        TLRPC.TL_contacts_importContacts r2 = new TLRPC.TL_contacts_importContacts();
        TLRPC.TL_inputPhoneContact r3 = new TLRPC.TL_inputPhoneContact();
        r3.first_name = r5;
        r3.last_name = r6;
        r3.phone = r4;
        if (TextUtils.isEmpty(r1) == true) goto L9;
        r3.flags = 1 | r3.flags;
        TLRPC.TL_textWithEntities r0 = new TLRPC.TL_textWithEntities();
        r3.note = r0;
        r0.text = r1;
    L9:
        r2.contacts.add(r3);
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new NewContactBottomSheet$$ExternalSyntheticLambda20(this, r3, r2), 2), this.classGuid);
        if (this.checkBox.isChecked() == false) goto L13;
        saveContact(getContext(), r4, r5, r6, null, null);
        return;
    L13:
        return;
    L5:
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    public static String getPhoneNumber(Context r5, TLRPC.User r6, String r7, boolean r8) {
        HashMap r0 = new HashMap();
        BufferedReader r2 = new BufferedReader(new InputStreamReader(r5.getResources().getAssets().open("countries.txt")));     // Catch: Exception -> L7
    L4:
        String r52 = r2.readLine();     // Catch: Exception -> L7
        if (r52 == null) goto L9;
        String[] r53 = r52.split(";");     // Catch: Exception -> L7
        r0.put(r53[0], r53[2]);     // Catch: Exception -> L7
        goto L4
    L9:
        r2.close();     // Catch: Exception -> L7
    L13:
        if (r7.startsWith("+") == true) goto L28;
        if (r8 == true) goto L30;
        if (r6 == null) goto L30;
        if (TextUtils.isEmpty(r6.phone) == true) goto L30;
        String r62 = r6.phone;
        int r82 = 4;
    L22:
        if (r82 < 1) goto L28;
        String r22 = r62.substring(0, r82);
        if (((String) r0.get(r22)) != null) goto L26;
        r82 = r82 - 1;
        goto L22
    L26:
        return "+" + r22 + r7;
    L30:
        return "+".concat(r7);
    L28:
        return r7;
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L13
    }

    private void invalidateCountryHint() {
        String r0 = this.countryCodeForHint;
        if (this.phoneField.getText() == null) goto L5;
        String r1 = this.phoneField.getText().toString().replace(" ", _UrlKt.FRAGMENT_ENCODE_SET);
    L6:
        String r5 = null;
        if (this.phoneFormatMap.get(r0) == null) goto L42;
        if (this.phoneFormatMap.get(r0).isEmpty() == true) goto L42;
        List<String> r4 = this.phoneFormatMap.get(r0);
        int r10 = 0;
        if (r1.isEmpty() == true) goto L19;
        int r7 = 0;
    L14:
        if (r7 >= r4.size()) goto L19;
        if (r1.startsWith(r4.get(r7).replace(" ", _UrlKt.FRAGMENT_ENCODE_SET).replace("X", _UrlKt.FRAGMENT_ENCODE_SET).replace(MVEL.VERSION_SUB, _UrlKt.FRAGMENT_ENCODE_SET)) == true) goto L20;
        r7 = r7 + 1;
    L20:
        if (r7 != (-1)) goto L33;
        int r12 = 0;
    L23:
        if (r12 >= r4.size()) goto L31;
        String r2 = r4.get(r12);
        if (r2.startsWith("X") == true) goto L30;
        if (r2.startsWith(MVEL.VERSION_SUB) == true) goto L30;
        r12 = r12 + 1;
    L30:
        r7 = r12;
    L31:
        if (r7 != (-1)) goto L33;
    L35:
        if (this.wasCountryHintIndex == r10) goto L50;
        String r02 = this.phoneFormatMap.get(r0).get(r10);
        int r13 = this.phoneField.getSelectionStart();
        int r22 = this.phoneField.getSelectionEnd();
        AnimatedPhoneNumberEditText r3 = this.phoneField;
        if (r02 == null) goto L39;
        r5 = r02.replace('X', '0');
    L39:
        r3.setHintText(r5);
        this.phoneField.setSelection(r13, r22);
        this.wasCountryHintIndex = r10;
        return;
    L50:
        return;
    L33:
        r10 = r7;
    L19:
        r7 = -1;
    L42:
        if (this.wasCountryHintIndex == (-1)) goto L51;
        int r03 = this.phoneField.getSelectionStart();
        int r14 = this.phoneField.getSelectionEnd();
        this.phoneField.setHintText(null);
        this.phoneField.setSelection(r03, r14);
        this.wasCountryHintIndex = -1;
        return;
    L51:
        return;
    L5:
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private /* synthetic */ boolean lambda$createView$1(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L6;
        this.lastNameField.requestFocus();
        this.lastNameField.getEditText().setSelection(this.lastNameField.getEditText().length());
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$createView$10(View r1) {
        doOnDone();
    }

    private /* synthetic */ boolean lambda$createView$2(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L6;
        this.codeField.requestFocus();
        AnimatedPhoneNumberEditText r0 = this.codeField;
        r0.setSelection(r0.length());
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$createView$3(View r2) {
        CountrySelectActivity r22 = new CountrySelectActivity(true);
        r22.setCountrySelectActivityDelegate(new AnonymousClass2(this));
        this.parentFragment.showAsSheet(r22);
    }

    private /* synthetic */ boolean lambda$createView$4(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L6;
        this.phoneField.requestFocus();
        AnimatedPhoneNumberEditText r0 = this.phoneField;
        r0.setSelection(r0.length());
        return true;
    L6:
        return false;
    }

    private /* synthetic */ boolean lambda$createView$5(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L6;
        this.doneButtonContainer.callOnClick();
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$createView$6(View r3) {
        this.checkBox.setChecked(!r3.isChecked(), true);
        updateQrButtonVisible(true);
    }

    private /* synthetic */ void lambda$createView$7(View r3) {
        dismiss();
        CameraScanActivity.showAsSheet(LaunchActivity.instance, false, 1, new AnonymousClass7(this));
    }

    private /* synthetic */ boolean lambda$createView$8(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L6;
        this.codeField.requestFocus();
        AnimatedPhoneNumberEditText r0 = this.codeField;
        r0.setSelection(r0.length());
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$dismiss$25() {
        AndroidUtilities.hideKeyboard(this.contentLayout);
    }

    private /* synthetic */ void lambda$doOnDone$21(Boolean r1) {
        if (r1.booleanValue() == false) goto L6;
        done();
        return;
    }

    private /* synthetic */ void lambda$done$22(TLRPC.TL_contacts_importedContacts r8, TLRPC.TL_inputPhoneContact r9, TLRPC.TL_error r10, TLRPC.TL_contacts_importContacts r11) {
        this.donePressed = false;
        if (r8 != null) goto L5;
        showEditDoneProgress(false, true);
        AlertsCreator.processError(this.currentAccount, r10, this.parentFragment, r11, new Object[0]);
        return;
    L5:
        if (r8.users.isEmpty() == true) goto L9;
        MessagesController.getInstance(this.currentAccount).putUsers(r8.users, false);
        MessagesController.getInstance(this.currentAccount).openChatOrProfileWith(r8.users.get(0), null, this.parentFragment, 1, false);
        dismiss();
        return;
    L9:
        if (this.parentFragment.getParentActivity() != null) goto L11;
        return;
    L11:
        showEditDoneProgress(false, true);
        AlertsCreator.createContactInviteDialog(this.parentFragment, r9.first_name, r9.last_name, r9.phone);
    }

    private /* synthetic */ void lambda$done$23(TLRPC.TL_inputPhoneContact r7, TLRPC.TL_contacts_importContacts r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new NewContactBottomSheet$$ExternalSyntheticLambda21(this, (TLRPC.TL_contacts_importedContacts) r9, r7, r10, r8));
    }

    private /* synthetic */ void lambda$show$24() {
        AndroidUtilities.showKeyboard(this.firstNameField.getEditText());
    }

    private /* synthetic */ void lambda$updateQrButtonVisible$11(boolean r1) {
        if (r1 == true) goto L5;
        this.qrButton.setVisibility(4);
        return;
    }

    private /* synthetic */ void lambda$updateQrButtonVisible$12(boolean r1) {
        if (r1 == true) goto L5;
        this.qrButtonSeparator.setVisibility(4);
        return;
    }

    private /* synthetic */ void lambda$updateQrButtonVisible$13(boolean r1) {
        if (r1 == false) goto L5;
        this.notesField.setVisibility(4);
        return;
    }

    private /* synthetic */ void lambda$updatedPhone$14(String r4) {
        Intent r0 = new Intent("android.intent.action.VIEW");
        r0.setData(Uri.parse("sms:+" + r4));
        r0.putExtra("sms_body", LocaleController.formatString(R.string.InviteText2, new Object[]{"https://telegram.org/dl"}));
        getContext().startActivity(r0);
    }

    private /* synthetic */ void lambda$updatedPhone$15(TLRPC.User r3) {
        dismiss();
        BaseFragment r2 = LaunchActivity.getSafeLastFragment();
        if (r2 == null) goto L6;
        r2.presentFragment(ProfileActivity.of(r3.id));
        return;
    }

    private /* synthetic */ void lambda$updatedPhone$16(String r7, TLRPC.User r8) {
        if (r8 != null) goto L5;
        this.phoneStatusView.setImageDrawable(null);
        this.underPhoneTextView.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag("This phone number is not on Telegram. **Invite >**", new NewContactBottomSheet$$ExternalSyntheticLambda23(this, r7)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.0f)));
    L9:
        updateBottomTranslation(false);
        return;
    L5:
        Drawable r72 = getContext().getResources().getDrawable(R.drawable.msg_text_check).mutate();
        r72.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_windowBackgroundWhiteBlueIcon), PorterDuff.Mode.SRC_IN));
        this.phoneStatusView.setImageDrawable(r72);
        boolean r73 = r8.contact;
        TextView r3 = this.underPhoneTextView;
        if (r73 == false) goto L8;
        r3.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag("This phone number is already in your contacts. **View >**", new NewContactBottomSheet$$ExternalSyntheticLambda24(this, r8)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.0f)));
        goto L9
    L8:
        r3.setText("This phone number is on Telegram.");
        goto L9
    }

    private /* synthetic */ void lambda$updatedPhone$18(TLRPC.TL_contact r3, Utilities.Callback r4) {
        AndroidUtilities.runOnUIThread(new NewContactBottomSheet$$ExternalSyntheticLambda25(r4, MessagesStorage.getInstance(this.currentAccount).getUser(r3.user_id)));
    }

    private /* synthetic */ void lambda$updatedPhone$19(TLObject r5, Utilities.Callback r6) {
        if ((r5 instanceof TLRPC.TL_contacts_resolvedPeer) == false) goto L7;
        TLRPC.TL_contacts_resolvedPeer r52 = (TLRPC.TL_contacts_resolvedPeer) r5;
        MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r52.chats, false);
        long r0 = DialogObject.getPeerDialogId(r52.peer);
        if (r0 < 0) goto L7;
        TLRPC.User r4 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r0));
    L8:
        r6.run(r4);
        return;
    L7:
        r4 = null;
        goto L8
    }

    private /* synthetic */ void lambda$updatedPhone$20(Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new NewContactBottomSheet$$ExternalSyntheticLambda22(this, r2, r1));
    }

    public static boolean saveContact(Context r7, String r8, String r9, String r10, String r11, AccountInfo r12) {
        ArrayList<ContentProviderOperation> r122 = new ArrayList();
        ContentProviderOperation.Builder r0 = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI);
        r0.withValue("account_type", null);
        r0.withValue("account_name", null);
        r122.add(r0.build());
        Uri r02 = ContactsContract.Data.CONTENT_URI;
        ContentProviderOperation.Builder r1 = ContentProviderOperation.newInsert(r02).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name");
        if (TextUtils.isEmpty(r9) == true) goto L6;
        r1 = r1.withValue("data2", r9);
    L6:
        if (TextUtils.isEmpty(r10) == true) goto L8;
        r1 = r1.withValue("data2", r10);
    L8:
        r122.add(r1.build());
        if (r8 != null) goto L11;
    L13:
        if (r11 != null) goto L15;
    L25:
        r7.getContentResolver().applyBatch("com.android.contacts", r122);     // Catch: OperationApplicationException -> L20 Throwable -> L22
        return true;
    L22:
        e = move-exception;
        e.printStackTrace();
        return false;
    L15:
        if (r11.isEmpty() == true) goto L25;
        r122.add(ContentProviderOperation.newInsert(r02).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/note").withValue("data1", r11).build());
        goto L25
    L11:
        if (r8.isEmpty() == true) goto L13;
        r122.add(ContentProviderOperation.newInsert(r02).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", r8).withValue("data2", 2).build());
        goto L13
    }

    private void setCountryButtonText(CharSequence r7) {
        boolean r0 = TextUtils.isEmpty(r7);
        TextView r1 = this.countryFlag;
        if (r0 == false) goto L6;
        ViewPropertyAnimator r72 = r1.animate();
        CubicBezierInterpolator r02 = CubicBezierInterpolator.DEFAULT;
        r72.setInterpolator(r02).translationY(AndroidUtilities.dp(30.0f)).setDuration(150);
        this.plusTextView.animate().setInterpolator(r02).translationX(-AndroidUtilities.dp(30.0f)).setDuration(150);
        this.codeField.animate().setInterpolator(r02).translationX(-AndroidUtilities.dp(30.0f)).setDuration(150);
        return;
    L6:
        r1.animate().setInterpolator(AndroidUtilities.overshootInterpolator).translationY(0.0f).setDuration(350).start();
        ViewPropertyAnimator r03 = this.plusTextView.animate();
        CubicBezierInterpolator r4 = CubicBezierInterpolator.DEFAULT;
        r03.setInterpolator(r4).translationX(0.0f).setDuration(150);
        this.codeField.animate().setInterpolator(r4).translationX(0.0f).setDuration(150);
        this.countryFlag.setText(r7);
    }

    private void setCountryHint(String r3, CountrySelectActivity.Country r4) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        String r42 = LocaleController.getLanguageFlag(r4.shortname);
        if (r42 == null) goto L5;
        r0.append(r42);
    L5:
        setCountryButtonText(Emoji.replaceEmoji(r0, this.countryFlag.getPaint().getFontMetricsInt(), false));
        this.countryCodeForHint = r3;
        this.wasCountryHintIndex = -1;
        invalidateCountryHint();
    }

    private void showEditDoneProgress(boolean r4, boolean r5) {
        AndroidUtilities.updateViewVisibilityAnimated(this.doneButton, !r4, 0.5f, r5);
        AndroidUtilities.updateViewVisibilityAnimated(this.progressView, r4, 0.5f, r5);
    }

    private void updateBottomTranslation(boolean r6) {
        ViewPropertyAnimator r0 = this.checkLayout.animate();
        float r1 = 0.0f;
        if (r6 == false) goto L5;
        float r2 = -AndroidUtilities.dp(21.33f);
    L6:
        ViewPropertyAnimator r02 = r0.translationY(r2);
        CubicBezierInterpolator r22 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r02.setInterpolator(r22).setDuration(420).start();
        ViewPropertyAnimator r5 = this.qrButtonContainer.animate();
        if (r6 == false) goto L9;
        r1 = -AndroidUtilities.dp(10.665f);
    L9:
        r5.translationY(r1).setInterpolator(r22).setDuration(420).start();
        return;
    L5:
        r2 = 0.0f;
        goto L6
    }

    private void updateQrButtonVisible(boolean r10) {
        boolean r0 = this.checkBox.isChecked();
        boolean r1 = !r0;
        ButtonWithCounterView r2 = this.qrButton;
        float r3 = 0.0f;
        int r5 = 0;
        if (r10 == false) goto L17;
        r2.setVisibility(0);
        ViewPropertyAnimator r102 = this.qrButton.animate();
        if (r0 == true) goto L7;
        float r22 = 1.0f;
    L8:
        ViewPropertyAnimator r103 = r102.alpha(r22);
        CubicBezierInterpolator r23 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r103.setInterpolator(r23).setDuration(420).withEndAction(new NewContactBottomSheet$$ExternalSyntheticLambda14(this, r1)).start();
        this.qrButtonSeparator.setVisibility(0);
        ViewPropertyAnimator r104 = this.qrButtonSeparator.animate();
        if (r0 == true) goto L11;
        float r8 = 1.0f;
    L12:
        r104.alpha(r8).setInterpolator(r23).setDuration(420).withEndAction(new NewContactBottomSheet$$ExternalSyntheticLambda15(this, r1)).start();
        this.notesField.setVisibility(0);
        ViewPropertyAnimator r105 = this.notesField.animate();
        if (r0 == false) goto L15;
        r3 = 1.0f;
    L15:
        r105.alpha(r3).setInterpolator(r23).setDuration(420).withEndAction(new NewContactBottomSheet$$ExternalSyntheticLambda16(this, r1)).start();
        return;
    L11:
        r8 = 0.0f;
        goto L12
    L7:
        r22 = 0.0f;
        goto L8
    L17:
        r2.animate().cancel();
        ButtonWithCounterView r106 = this.qrButton;
        if (r0 == true) goto L20;
        int r24 = 0;
    L21:
        r106.setVisibility(r24);
        ButtonWithCounterView r107 = this.qrButton;
        if (r0 == true) goto L24;
        float r25 = 1.0f;
    L25:
        r107.setAlpha(r25);
        this.qrButtonSeparator.animate().cancel();
        View r108 = this.qrButtonSeparator;
        if (r0 == true) goto L28;
        int r26 = 0;
    L29:
        r108.setVisibility(r26);
        View r109 = this.qrButtonSeparator;
        if (r0 == true) goto L32;
        float r27 = 1.0f;
    L33:
        r109.setAlpha(r27);
        this.notesField.animate().cancel();
        OutlineEditText r1010 = this.notesField;
        if (r0 == true) goto L37;
        r5 = 4;
    L37:
        r1010.setVisibility(r5);
        OutlineEditText r9 = this.notesField;
        if (r0 == false) goto L40;
        r3 = 1.0f;
    L40:
        r9.setAlpha(r3);
        return;
    L32:
        r27 = 0.0f;
        goto L33
    L28:
        r26 = 8;
        goto L29
    L24:
        r25 = 0.0f;
        goto L25
    L20:
        r24 = 4;
        goto L21
    }

    private void updatedPhone(String r8) {
        if (this.requestingPhoneId < 0) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.requestingPhoneId, true);
        this.requestingPhoneId = -1;
    L5:
        boolean r0 = TextUtils.isEmpty(r8);
        ImageView r2 = this.phoneStatusView;
        if (r0 == false) goto L9;
        r2.animate().scaleX(0.5f).scaleY(0.5f).alpha(0.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(420).start();
        this.underPhoneTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        updateBottomTranslation(true);
        return;
    L9:
        r2.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(420).start();
        this.phoneStatusView.setImageDrawable(new CircularProgressDrawable(AndroidUtilities.dp(30.0f), AndroidUtilities.dp(3.0f), getThemedColor(Theme.key_dialogTextBlue)));
        this.underPhoneTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        updateBottomTranslation(true);
        NewContactBottomSheet$$ExternalSyntheticLambda17 r02 = new NewContactBottomSheet$$ExternalSyntheticLambda17(this, r8);
        TLRPC.TL_contact r1 = ContactsController.getInstance(this.currentAccount).contactsByPhone.get(PhoneFormat.stripExceptNumbers(r8));
        if (r1 == null) goto L17;
        TLRPC.User r82 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r1.user_id));
        if (r82 == null) goto L15;
        r02.run(r82);
        return;
    L15:
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new NewContactBottomSheet$$ExternalSyntheticLambda18(this, r1, r02));
        return;
    L17:
        TLRPC.TL_contacts_resolvePhone r12 = new TLRPC.TL_contacts_resolvePhone();
        r12.phone = PhoneFormat.stripExceptNumbers(r8);
        this.requestingPhoneId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, new NewContactBottomSheet$$ExternalSyntheticLambda19(this, r02));
    }

    private void updatedTextPhone() {
        String r0 = (this.codeField.getText().toString() + this.phoneField.getText().toString()).replaceAll("[^\\d]+", _UrlKt.FRAGMENT_ENCODE_SET);
        int r1 = Math.min(3, r0.length());
        boolean r4 = false;
    L3:
        if (r1 < 0) goto L22;
        String r5 = r0.substring(0, r1);
        List<CountrySelectActivity.Country> r6 = this.codesMap.get(r5);
        if (r6 != null) goto L7;
    L19:
        if (r4 == true) goto L22;
    L21:
        r1 = r1 - 1;
        goto L3
    L7:
        if (r6.isEmpty() == true) goto L19;
        List<String> r52 = this.phoneFormatMap.get(r5);
        if (r52 == null) goto L21;
        if (r52.isEmpty() == true) goto L21;
        Iterator<String> r53 = r52.iterator();
    L15:
        if (r53.hasNext() == false) goto L19;
        if ((r0.length() - r1) < r53.next().replace(" ", _UrlKt.FRAGMENT_ENCODE_SET).length()) goto L15;
        r4 = true;
    L22:
        String r12 = this.lastPhone;
        if (r4 == true) goto L29;
        if (TextUtils.isEmpty(r12) == true) goto L40;
        this.lastPhone = null;
        updatedPhone(null);
        return;
    L40:
        return;
    L29:
        if (TextUtils.equals(r12, r0) == true) goto L41;
        this.lastPhone = r0;
        updatedPhone(r0);
        return;
    }

    public View createView(Context r27) {
        ContextProgressView r0 = new ContextProgressView(r27, 1);
        this.editDoneItemProgress = r0;
        r0.setVisibility(4);
        ScrollView r5 = new ScrollView(r27);
        LinearLayout r02 = new LinearLayout(r27);
        this.contentLayout = r02;
        r02.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        this.contentLayout.setOrientation(1);
        r5.addView(this.contentLayout, LayoutHelper.createScroll(-1, -2, 51));
        this.contentLayout.setOnTouchListener(new NewContactBottomSheet$$ExternalSyntheticLambda2());
        FrameLayout r03 = new FrameLayout(r27);
        this.contentLayout.addView(r03, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.0f));
        OutlineEditText r7 = new OutlineEditText(r27);
        this.firstNameField = r7;
        r7.getEditText().setInputType(49152);
        this.firstNameField.getEditText().setImeOptions(5);
        this.firstNameField.setHint(LocaleController.getString(R.string.FirstName));
        if (this.initialFirstName == null) goto L5;
        this.firstNameField.getEditText().setText(this.initialFirstName);
        this.initialFirstName = null;
    L5:
        r03.addView(this.firstNameField, LayoutHelper.createFrame(-1, 58.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        this.firstNameField.getEditText().setOnEditorActionListener(new NewContactBottomSheet$$ExternalSyntheticLambda4(this));
        OutlineEditText r72 = new OutlineEditText(r27);
        this.lastNameField = r72;
        r72.setBackground(null);
        this.lastNameField.getEditText().setInputType(49152);
        this.lastNameField.getEditText().setImeOptions(5);
        this.lastNameField.setHint(LocaleController.getString(R.string.LastName));
        if (this.initialLastName == null) goto L8;
        this.lastNameField.getEditText().setText(this.initialLastName);
        this.initialLastName = null;
    L8:
        r03.addView(this.lastNameField, LayoutHelper.createFrame(-1, 58.0f, 51, 0.0f, 68.0f, 0.0f, 0.0f));
        this.lastNameField.getEditText().setOnEditorActionListener(new NewContactBottomSheet$$ExternalSyntheticLambda5(this));
        LinearLayout r04 = new LinearLayout(r27);
        r04.setOrientation(0);
        OutlineTextContainerView r73 = new OutlineTextContainerView(r27);
        this.phoneOutlineView = r73;
        r73.addView(r04, LayoutHelper.createFrame(-1, -2.0f, 16, 4.0f, 8.0f, 16.0f, 8.0f));
        this.phoneOutlineView.setText(LocaleController.getString(R.string.PhoneNumber));
        this.contentLayout.addView(this.phoneOutlineView, LayoutHelper.createLinear(-1, 58, 0.0f, 12.0f, 0.0f, 6.0f));
        LinkSpanDrawable.LinksTextView r74 = new LinkSpanDrawable.LinksTextView(r27);
        this.underPhoneTextView = r74;
        r74.setTextSize(1, 12.0f);
        this.underPhoneTextView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        this.underPhoneTextView.setLinkTextColor(getThemedColor(Theme.key_chat_messageLinkIn));
        this.contentLayout.addView(this.underPhoneTextView, LayoutHelper.createLinear(-1, -2, 12.0f, 0.0f, 12.0f, 0.0f));
        FrameLayout r75 = new FrameLayout(r27);
        AnonymousClass1 r14 = new AnonymousClass1(this, r27);
        this.countryFlag = r14;
        r14.setTextSize(1, 16.0f);
        this.countryFlag.setFocusable(false);
        this.countryFlag.setGravity(17);
        r75.setOnClickListener(new NewContactBottomSheet$$ExternalSyntheticLambda6(this));
        int r142 = AndroidUtilities.dp(6.0f);
        int r15 = Theme.key_listSelector;
        r75.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r142, 0, Theme.getColor(r15)));
        r75.addView(this.countryFlag, LayoutHelper.createFrame(-1, -2, 16));
        r04.addView(r75, LayoutHelper.createLinear(42, -1));
        TextView r4 = new TextView(r27);
        this.plusTextView = r4;
        r4.setText("+");
        this.plusTextView.setTextSize(1, 16.0f);
        this.plusTextView.setFocusable(false);
        r04.addView(this.plusTextView, LayoutHelper.createLinear(-2, -2));
        AnonymousClass3 r42 = new AnonymousClass3(this, r27);
        this.codeField = r42;
        int r143 = Theme.key_windowBackgroundWhiteBlackText;
        r42.setTextColor(Theme.getColor(r143));
        this.codeField.setInputType(3);
        this.codeField.setCursorSize(AndroidUtilities.dp(20.0f));
        this.codeField.setCursorWidth(1.5f);
        this.codeField.setPadding(AndroidUtilities.dp(10.0f), 0, 0, 0);
        this.codeField.setTextSize(1, 16.0f);
        this.codeField.setMaxLines(1);
        this.codeField.setGravity(19);
        this.codeField.setImeOptions(268435461);
        this.codeField.setBackground(null);
        this.codeField.setContentDescription(LocaleController.getString(R.string.LoginAccessibilityCountryCode));
        r04.addView(this.codeField, LayoutHelper.createLinear(55, 36, -9.0f, 0.0f, 0.0f, 0.0f));
        this.codeField.addTextChangedListener(new AnonymousClass4(this));
        this.codeField.setOnEditorActionListener(new NewContactBottomSheet$$ExternalSyntheticLambda7(this));
        this.codeDividerView = new View(r27);
        LinearLayout.LayoutParams r43 = LayoutHelper.createLinear(0, -1, 4.0f, 8.0f, 12.0f, 8.0f);
        r43.width = Math.max(2, AndroidUtilities.dp(0.5f));
        r04.addView(this.codeDividerView, r43);
        AnonymousClass5 r44 = new AnonymousClass5(this, r27);
        this.phoneField = r44;
        r44.setTextColor(Theme.getColor(r143));
        this.phoneField.setInputType(3);
        this.phoneField.setPadding(0, 0, 0, 0);
        this.phoneField.setCursorSize(AndroidUtilities.dp(20.0f));
        this.phoneField.setCursorWidth(1.5f);
        this.phoneField.setTextSize(1, 16.0f);
        this.phoneField.setMaxLines(1);
        this.phoneField.setGravity(19);
        this.phoneField.setImeOptions(268435461);
        this.phoneField.setBackground(null);
        this.phoneField.setContentDescription(LocaleController.getString(R.string.PhoneNumber));
        r04.addView(this.phoneField, LayoutHelper.createLinear(-1, 36));
        this.phoneField.addTextChangedListener(new AnonymousClass6(this));
        this.phoneField.setOnEditorActionListener(new NewContactBottomSheet$$ExternalSyntheticLambda8(this));
        ImageView r05 = new ImageView(r27);
        this.phoneStatusView = r05;
        r05.setScaleX(0.5f);
        this.phoneStatusView.setScaleY(0.5f);
        this.phoneStatusView.setAlpha(0.0f);
        this.phoneOutlineView.addView(this.phoneStatusView, LayoutHelper.createFrame(24, 24.0f, 21, 0.0f, 0.0f, 12.0f, 0.0f));
        CheckBox2 r06 = new CheckBox2(r27, 21, this.resourcesProvider);
        this.checkBox = r06;
        r06.setColor(Theme.key_radioBackgroundChecked, Theme.key_checkboxDisabled, Theme.key_checkboxCheck);
        this.checkBox.setDrawUnchecked(true);
        this.checkBox.setChecked(false, false);
        this.checkBox.setDrawBackgroundAsArc(10);
        TextView r07 = new TextView(r27);
        this.checkTextView = r07;
        r07.setTextColor(Theme.getColor(r143, this.resourcesProvider));
        this.checkTextView.setTextSize(1, 14.0f);
        this.checkTextView.setText(LocaleController.getString(R.string.AddContactSync));
        LinearLayout r08 = new LinearLayout(r27);
        this.checkLayout = r08;
        r08.setOrientation(0);
        this.checkLayout.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f));
        this.checkLayout.addView(this.checkBox, LayoutHelper.createLinear(21, 21, 16, 0, 0, 9, 0));
        this.checkLayout.addView(this.checkTextView, LayoutHelper.createLinear(-2, -2, 16));
        this.checkLayout.setOnClickListener(new NewContactBottomSheet$$ExternalSyntheticLambda9(this));
        this.checkLayout.setTranslationY(AndroidUtilities.dp(-21.33f));
        this.checkLayout.setPivotX(0.0f);
        ScaleStateListAnimator.apply(this.checkLayout, 0.0125f, 1.2f);
        this.checkLayout.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(r15, this.resourcesProvider), 6, 6));
        this.contentLayout.addView(this.checkLayout, LayoutHelper.createLinear(-2, -2, 0.0f, 5.0f, 0.0f, 0.0f));
        FrameLayout r09 = new FrameLayout(r27);
        this.qrButtonContainer = r09;
        r09.setTranslationY(AndroidUtilities.dp(-10.665f));
        this.contentLayout.addView(this.qrButtonContainer, LayoutHelper.createLinear(-1, -2, 0.0f, 6.0f, 0.0f, -6.0f));
        View r010 = new View(r27);
        this.qrButtonSeparator = r010;
        r010.setBackgroundColor(Theme.getColor(Theme.key_divider, this.resourcesProvider));
        this.qrButtonContainer.addView(this.qrButtonSeparator, LayoutHelper.createFrame(-1, 1.0f / AndroidUtilities.density, 48, 0.0f, 6.0f, 0.0f, 0.0f));
        ButtonWithCounterView r011 = new ButtonWithCounterView(r27, false, this.resourcesProvider);
        this.qrButton = r011;
        r011.setRound();
        SpannableStringBuilder r012 = new SpannableStringBuilder("QR");
        r012.setSpan(new ColoredImageSpan(R.drawable.header_qr_24), 0, r012.length(), 33);
        r012.append("  ");
        r012.append(LocaleController.getString(R.string.AddContactQr));
        this.qrButton.setText(r012, false);
        this.qrButton.setOnClickListener(new NewContactBottomSheet$$ExternalSyntheticLambda10(this));
        this.qrButtonContainer.addView(this.qrButton, LayoutHelper.createFrame(-1, 48.0f, 48, 0.0f, 12.0f, 0.0f, 0.0f));
        OutlineEditText r013 = new OutlineEditText(r27);
        this.notesField = r013;
        r013.setBackground(null);
        this.notesField.getEditText().setInputType(49152);
        this.notesField.getEditText().setImeOptions(5);
        this.notesField.setHint("Notes");
        this.qrButtonContainer.addView(this.notesField, LayoutHelper.createFrame(-1, 58.0f, 48, 0.0f, 0.0f, 0.0f, 0.0f));
        this.notesField.getEditText().setOnEditorActionListener(new NewContactBottomSheet$$ExternalSyntheticLambda11(this));
        updateQrButtonVisible(false);
        HashMap r45 = new HashMap();
        BufferedReader r014 = new BufferedReader(new InputStreamReader(ApplicationLoader.applicationContext.getResources().getAssets().open("countries.txt")));     // Catch: Exception -> L15
    L10:
        String r6 = r014.readLine();     // Catch: Exception -> L15
        if (r6 == null) goto L21;
        String[] r62 = r6.split(";");     // Catch: Exception -> L15
        CountrySelectActivity.Country r10 = new CountrySelectActivity.Country();     // Catch: Exception -> L15
        r10.name = r62[2];     // Catch: Exception -> L15
        r10.code = r62[0];     // Catch: Exception -> L15
        r10.shortname = r62[1];     // Catch: Exception -> L15
        this.countriesArray.add(0, r10);     // Catch: Exception -> L15
        List<CountrySelectActivity.Country> r11 = this.codesMap.get(r62[0]);     // Catch: Exception -> L15
        if (r11 != null) goto L17;
        HashMap<String, List<CountrySelectActivity.Country>> r112 = this.codesMap;     // Catch: Exception -> L15
        String r12 = r62[0];     // Catch: Exception -> L15
        ArrayList r144 = new ArrayList();     // Catch: Exception -> L15
        r112.put(r12, r144);     // Catch: Exception -> L15
        r11 = r144;
    L17:
        r11.add(r10);     // Catch: Exception -> L15
        if (r62.length <= 3) goto L20;
        this.phoneFormatMap.put(r62[0], Collections.singletonList(r62[3]));     // Catch: Exception -> L15
    L20:
        r45.put(r62[1], r62[2]);     // Catch: Exception -> L15
        goto L10
    L21:
        r014.close();     // Catch: Exception -> L15
    L24:
        Collections.sort(this.countriesArray, Comparator.comparing(new NewContactBottomSheet$$ExternalSyntheticLambda12()));
        if (TextUtils.isEmpty(this.initialPhoneNumber) == true) goto L71;
        TLRPC.User r015 = this.parentFragment.getUserConfig().getCurrentUser();
        if (this.initialPhoneNumber.startsWith("+") == false) goto L30;
        this.codeField.setText(this.initialPhoneNumber.substring(1));
    L43:
        this.initialPhoneNumber = null;
    L67:
        this.doneButtonContainer = new FrameLayout(getContext());
        TextView r016 = new TextView(r27);
        this.doneButton = r016;
        r016.setEllipsize(TextUtils.TruncateAt.END);
        this.doneButton.setGravity(17);
        this.doneButton.setLines(1);
        this.doneButton.setSingleLine(true);
        this.doneButton.setText(LocaleController.getString(R.string.CreateContact));
        TextView r017 = this.doneButton;
        BaseFragment r46 = this.parentFragment;
        int r63 = Theme.key_featuredStickers_buttonText;
        r017.setTextColor(r46.getThemedColor(r63));
        this.doneButton.setTextSize(1, 15.0f);
        this.doneButton.setTypeface(AndroidUtilities.bold());
        ScaleStateListAnimator.apply(this.doneButtonContainer, 0.02f, 1.2f);
        RadialProgressView r018 = new RadialProgressView(r27);
        this.progressView = r018;
        r018.setSize(AndroidUtilities.dp(20.0f));
        this.progressView.setProgressColor(this.parentFragment.getThemedColor(r63));
        this.doneButtonContainer.addView(this.doneButton, LayoutHelper.createFrame(-1, -1.0f));
        this.doneButtonContainer.addView(this.progressView, LayoutHelper.createFrame(40, 40, 17));
        this.contentLayout.addView(this.doneButtonContainer, LayoutHelper.createLinear(-1, 48, 0, 0, 8, 0, 4));
        AndroidUtilities.updateViewVisibilityAnimated(this.doneButton, true, 1.0f, false);
        AndroidUtilities.updateViewVisibilityAnimated(this.progressView, false, 1.0f, false);
        this.doneButtonContainer.setBackground(Theme.AdaptiveRipple.filledRect(this.parentFragment.getThemedColor(Theme.key_featuredStickers_addButton), new float[]{24.0f}));
        this.doneButtonContainer.setOnClickListener(new NewContactBottomSheet$$ExternalSyntheticLambda3(this));
        this.plusTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.codeDividerView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhiteInputField));
        return r5;
    L30:
        if (this.initialPhoneNumberWithCountryCode == true) goto L42;
        if (r015 == null) goto L42;
        if (TextUtils.isEmpty(r015.phone) == true) goto L42;
        String r019 = r015.phone;
        int r47 = 4;
    L36:
        if (r47 < 1) goto L41;
        String r64 = r019.substring(0, r47);
        if (this.codesMap.get(r64) != null) goto L39;
        r47 = r47 - 1;
        goto L36
    L39:
        this.codeField.setText(r64);
    L41:
        this.phoneField.setText(this.initialPhoneNumber);
    L42:
        this.codeField.setText(this.initialPhoneNumber);
        goto L43
    L71:
        TelephonyManager r020 = (TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone");     // Catch: Exception -> L48
        if (r020 == null) goto L50;
        String r021 = r020.getSimCountryIso().toUpperCase();     // Catch: Exception -> L48
    L51:
        if (r021 == null) goto L65;
        String r022 = (String) r45.get(r021);
        if (r022 == null) goto L65;
        int r48 = 0;
    L56:
        if (r48 >= this.countriesArray.size()) goto L61;
        if (Objects.equals(this.countriesArray.get(r48).name, r022) == true) goto L59;
        r48 = r48 + 1;
        goto L56
    L59:
        CountrySelectActivity.Country r023 = this.countriesArray.get(r48);
    L62:
        if (r023 == null) goto L65;
        this.codeField.setText(r023.code);
        goto L65
    L61:
        r023 = null;
    L65:
        if (this.codeField.length() != 0) goto L67;
        this.phoneField.setHintText(null);
    L50:
        r021 = null;
    L48:
        e = move-exception;
        FileLog.e(e);
    L15:
        e = move-exception;
        FileLog.e(e);
        goto L24
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        AndroidUtilities.runOnUIThread(new NewContactBottomSheet$$ExternalSyntheticLambda0(this), 50);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        OutlineEditText r3 = this.firstNameField;
        int r4 = ThemeDescription.FLAG_TEXTCOLOR;
        int r12 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(r3, r4, null, null, null, null, r12));
        OutlineEditText r42 = this.firstNameField;
        int r5 = ThemeDescription.FLAG_HINTTEXTCOLOR;
        int r20 = Theme.key_windowBackgroundWhiteHintText;
        r1.add(new ThemeDescription(r42, r5, null, null, null, null, r20));
        OutlineEditText r52 = this.firstNameField;
        int r6 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        int r28 = Theme.key_windowBackgroundWhiteInputField;
        r1.add(new ThemeDescription(r52, r6, null, null, null, null, r28));
        OutlineEditText r2 = this.firstNameField;
        int r31 = ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE;
        int r10 = Theme.key_windowBackgroundWhiteInputFieldActivated;
        r1.add(new ThemeDescription(r2, r31, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.lastNameField, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r12));
        r1.add(new ThemeDescription(this.lastNameField, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.lastNameField, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r28));
        OutlineEditText r43 = this.lastNameField;
        int r22 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        r1.add(new ThemeDescription(r43, ThemeDescription.FLAG_DRAWABLESELECTEDSTATE | r22, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.codeField, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r12));
        r1.add(new ThemeDescription(this.codeField, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r28));
        AnimatedPhoneNumberEditText r44 = this.codeField;
        int r23 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        r1.add(new ThemeDescription(r44, ThemeDescription.FLAG_DRAWABLESELECTEDSTATE | r23, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.phoneField, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r12));
        r1.add(new ThemeDescription(this.phoneField, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.phoneField, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r28));
        AnimatedPhoneNumberEditText r45 = this.phoneField;
        int r24 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        r1.add(new ThemeDescription(r45, ThemeDescription.FLAG_DRAWABLESELECTEDSTATE | r24, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.editDoneItemProgress, 0, null, null, null, null, Theme.key_contextProgressInner2));
        r1.add(new ThemeDescription(this.editDoneItemProgress, 0, null, null, null, null, Theme.key_contextProgressOuter2));
        return r1;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> r1, View r2, int r3, long r4) {
        if (this.ignoreSelection == false) goto L6;
        this.ignoreSelection = false;
        return;
    L6:
        this.ignoreOnTextChange = true;
        CountrySelectActivity.Country r12 = this.countriesArray.get(r3);
        this.codeField.setText(r12.code);
        this.ignoreOnTextChange = false;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> r1) {
    }

    public void selectCountry(CountrySelectActivity.Country r3) {
        this.ignoreOnTextChange = true;
        String r0 = r3.code;
        this.codeField.setText(r0);
        setCountryHint(r0, r3);
        this.ignoreOnTextChange = false;
    }

    public void setInitialName(String r2, String r3) {
        OutlineEditText r0 = this.firstNameField;
        if (r0 == null) goto L5;
        r0.getEditText().setText(r2);
    L6:
        OutlineEditText r22 = this.lastNameField;
        if (r22 == null) goto L10;
        r22.getEditText().setText(r3);
        return;
    L10:
        this.initialLastName = r3;
        return;
    L5:
        this.initialFirstName = r2;
        goto L6
    }

    public NewContactBottomSheet setInitialPhoneNumber(String r6, boolean r7) {
        this.initialPhoneNumber = r6;
        this.initialPhoneNumberWithCountryCode = r7;
        if (TextUtils.isEmpty(r6) == true) goto L38;
        TLRPC.User r62 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        if (this.initialPhoneNumber.startsWith("+") == false) goto L8;
        this.codeField.setText(this.initialPhoneNumber.substring(1));
    L37:
        this.initialPhoneNumber = null;
        goto L38
    L8:
        if (this.initialPhoneNumberWithCountryCode == true) goto L36;
        if (r62 == null) goto L36;
        if (TextUtils.isEmpty(r62.phone) == true) goto L36;
        String r63 = r62.phone;
        int r72 = 4;
    L15:
        if (r72 < 1) goto L26;
        List<CountrySelectActivity.Country> r3 = this.codesMap.get(r63.substring(0, r72));
        if (r3 == null) goto L25;
        if (r3.size() <= 0) goto L25;
        String r64 = r3.get(0).code;
        this.codeField.setText(r64);
        if (r64.endsWith(MVEL.VERSION_SUB) == true) goto L23;
    L35:
        this.phoneField.setText(this.initialPhoneNumber);
        goto L37
    L23:
        if (this.initialPhoneNumber.startsWith(MVEL.VERSION_SUB) == false) goto L35;
        this.initialPhoneNumber = this.initialPhoneNumber.substring(1);
    L25:
        r72 = r72 - 1;
        goto L15
    L26:
        Context r65 = ApplicationLoader.applicationContext;
        if (r65 == null) goto L29;
        String r66 = ((TelephonyManager) r65.getSystemService(TelephonyManager.class)).getSimCountryIso().toUpperCase(Locale.US);
    L30:
        this.codeField.setText(r66);
        if (r66.endsWith(MVEL.VERSION_SUB) == false) goto L35;
        if (this.initialPhoneNumber.startsWith(MVEL.VERSION_SUB) == false) goto L35;
        this.initialPhoneNumber = this.initialPhoneNumber.substring(1);
        goto L35
    L29:
        r66 = Locale.getDefault().getCountry();
    L36:
        this.codeField.setText(this.initialPhoneNumber);
    L38:
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        this.firstNameField.getEditText().requestFocus();
        this.firstNameField.getEditText().setSelection(this.firstNameField.getEditText().length());
        AndroidUtilities.runOnUIThread(new NewContactBottomSheet$$ExternalSyntheticLambda1(this), 50);
    }
}
