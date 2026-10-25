package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Keep;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.material.timepicker.TimeModel;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.exception.APIConnectionException;
import com.stripe.android.exception.APIException;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.net.TokenParser;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mvel2.MVEL;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SRPHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.EditTextSettingsCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.PaymentInfoCell;
import org.telegram.ui.Cells.RadioCell;
import org.telegram.ui.Cells.RecurrentPaymentsAcceptCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextDetailSettingsCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextPriceCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ContextProgressView;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EmbedBottomSheet$$ExternalSyntheticLambda6;
import org.telegram.ui.Components.HintEditText;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.CountrySelectActivity;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes6.dex */
public class PaymentFormActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private static final List<String> BLACKLISTED_PROTOCOLS = null;
    private static final List<String> WEBVIEW_PROTOCOLS = null;
    private boolean allowUnregistered;
    private TLRPC.User botUser;
    private TextInfoPrivacyCell[] bottomCell;
    private BottomFrameLayout bottomLayout;
    private boolean canceled;
    private String cardName;
    private TextCheckCell checkCell1;
    private EditTextSettingsCell codeFieldCell;
    private HashMap<String, String> codesMap;
    private ArrayList<String> countriesArray;
    private HashMap<String, String> countriesMap;
    private CountrySelectActivity.Country country;
    private String countryName;
    private String currentBotName;
    private String currentItemName;
    private TL_account.Password currentPassword;
    private int currentStep;
    private Utilities.Callback<TLRPC.payments_PaymentResult> customAnyResultReceiver;
    private Utilities.CallbackReturn<TLRPC.TL_error, Boolean> customErrorReceiver;
    private Utilities.Callback<TLRPC.TL_payments_paymentResult> customResultReceiver;
    private PaymentFormActivityDelegate delegate;
    private TextDetailSettingsCell[] detailSettingsCell;
    private ArrayList<View> dividers;
    private ActionBarMenuItem doneItem;
    private AnimatorSet doneItemAnimation;
    private boolean donePressed;
    private int emailCodeLength;
    private FrameLayout googlePayButton;
    private FrameLayout googlePayContainer;
    private String googlePayCountryCode;
    private TLRPC.TL_inputPaymentCredentialsGooglePay googlePayCredentials;
    private JSONObject googlePayParameters;
    private String googlePayPublicKey;
    private HeaderCell[] headerCell;
    private boolean ignoreOnCardChange;
    private boolean ignoreOnPhoneChange;
    private boolean ignoreOnTextChange;
    private boolean initGooglePay;
    private EditTextBoldCursor[] inputFields;
    private TLRPC.InputInvoice invoiceInput;
    private String invoiceSlug;
    private InvoiceStatus invoiceStatus;
    private boolean isCheckoutPreview;
    private boolean isWebView;
    private LinearLayout linearLayout2;
    private boolean loadingPasswordInfo;
    private MessageObject messageObject;
    private boolean needPayAfterTransition;
    private boolean need_card_country;
    private boolean need_card_name;
    private boolean need_card_postcode;
    private BaseFragment parentFragment;
    private PaymentFormActivity passwordFragment;
    private boolean passwordOk;
    private TextView payTextView;
    private TLRPC.PaymentForm paymentForm;
    private PaymentFormCallback paymentFormCallback;
    private TLRPC.TL_paymentFormMethod paymentFormMethod;
    private PaymentInfoCell paymentInfoCell;
    private String paymentJson;
    private TLRPC.PaymentReceipt paymentReceipt;
    private boolean paymentStatusSent;
    private PaymentsClient paymentsClient;
    private HashMap<String, String> phoneFormatMap;
    private ArrayList<TLRPC.TL_labeledPrice> prices;
    private ContextProgressView progressView;
    private ContextProgressView progressViewButton;
    private String providerApiKey;
    private RadioCell[] radioCells;
    private RecurrentPaymentsAcceptCell recurrentAcceptCell;
    private boolean recurrentAccepted;
    private TLRPC.TL_payments_validatedRequestedInfo requestedInfo;
    private Theme.ResourcesProvider resourcesProvider;
    private boolean saveCardInfo;
    private boolean saveShippingInfo;
    private TLRPC.TL_paymentSavedCredentialsCard savedCredentialsCard;
    private ScrollView scrollView;
    private ShadowSectionCell[] sectionCell;
    private TextSettingsCell[] settingsCell;
    private float shiftDp;
    private TLRPC.TL_shippingOption shippingOption;
    private Runnable shortPollRunnable;
    private boolean shouldNavigateBack;
    private boolean swipeBackEnabled;
    private TextView textView;
    private Long tipAmount;
    private LinearLayout tipLayout;
    private TextPriceCell totalCell;
    private String[] totalPrice;
    private String totalPriceDecimal;
    private TLRPC.TL_payments_validateRequestedInfo validateRequest;
    private boolean waitingForEmail;
    private WebView webView;
    private String webViewUrl;
    private boolean webviewLoading;

    /* renamed from: org.telegram.ui.PaymentFormActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass1(PaymentFormActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r4) {
            if (r4 != (-1)) goto L10;
            if (PaymentFormActivity.m16613$$Nest$fgetdonePressed(this.this$0) == true) goto L46;
            this.this$0.finishFragment();
            return;
        L46:
            return;
        L10:
            if (r4 == 1) goto L12;
            return;
        L12:
            if (PaymentFormActivity.m16613$$Nest$fgetdonePressed(this.this$0) == false) goto L15;
            return;
        L15:
            if (PaymentFormActivity.m16608$$Nest$fgetcurrentStep(this.this$0) == 3) goto L17;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
        L17:
            int r42 = PaymentFormActivity.m16608$$Nest$fgetcurrentStep(this.this$0);
            if (r42 == 0) goto L41;
            int r2 = 0;
            if (r42 == 1) goto L34;
            if (r42 == 2) goto L31;
            if (r42 != 3) goto L25;
            PaymentFormActivity.m16651$$Nest$mcheckPassword(this.this$0);
            return;
        L25:
            if (r42 != 6) goto L48;
            PaymentFormActivity.m16655$$Nest$msendSavePassword(this.this$0, false);
            return;
        L48:
            return;
        L31:
            PaymentFormActivity.m16653$$Nest$msendCardData(this.this$0);
            return;
        L34:
            if (r2 >= PaymentFormActivity.m16629$$Nest$fgetradioCells(this.this$0).length) goto L39;
            if (PaymentFormActivity.m16629$$Nest$fgetradioCells(this.this$0)[r2].isChecked() == true) goto L37;
            r2 = r2 + 1;
            goto L34
        L37:
            PaymentFormActivity r43 = this.this$0;
            PaymentFormActivity.m16646$$Nest$fputshippingOption(r43, PaymentFormActivity.m16630$$Nest$fgetrequestedInfo(r43).shipping_options.get(r2));
        L39:
            PaymentFormActivity.m16652$$Nest$mgoToNextStep(this.this$0);
            return;
        L41:
            PaymentFormActivity.m16657$$Nest$msetDonePressed(this.this$0, true);
            PaymentFormActivity.m16654$$Nest$msendForm(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$10, reason: invalid class name */
    public class AnonymousClass10 extends View {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass10(PaymentFormActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            if (LocaleController.isRTL == false) goto L6;
            float r0 = 0.0f;
        L5:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L10;
            int r1 = AndroidUtilities.dp(20.0f);
        L11:
            r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L10:
            r1 = 0;
            goto L11
        L6:
            r0 = AndroidUtilities.dp(20.0f);
            goto L5
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$11, reason: invalid class name */
    public class AnonymousClass11 implements TextWatcher {
        private boolean anyBefore;
        private int beforeTextLength;
        char[] commas;
        private int enteredCharacterStart;
        private boolean isDeletedChar;
        private boolean lastDotEntered;
        private String overrideText;
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass11(PaymentFormActivity r1) {
            this.this$0 = r1;
            this.commas = new char[]{',', '.', 1643, 12289, 11841, 65040, 65041, 65104, 65105, 65292, 65380, 699};
        }

        private int indexOfComma(String r4) {
            int r0 = 0;
        L3:
            char[] r1 = this.commas;
            if (r0 >= r1.length) goto L9;
            int r12 = r4.indexOf(r1[r0]);
            if (r12 >= 0) goto L7;
            r0 = r0 + 1;
            goto L3
        L7:
            return r12;
        L9:
            return -1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r19) {
            if (PaymentFormActivity.m16619$$Nest$fgetignoreOnTextChange(this.this$0) == false) goto L6;
            return;
        L6:
            if (PaymentFormActivity.m16632$$Nest$fgettipAmount(this.this$0) == null) goto L8;
            long r4 = PaymentFormActivity.m16632$$Nest$fgettipAmount(this.this$0).longValue();
        L9:
            String r1 = this.overrideText;
            if (r1 != null) goto L13;
            r1 = LocaleController.fixNumbers(r19.toString());
        L13:
            int r6 = indexOfComma(r1);
            if (r6 < 0) goto L16;
            boolean r13 = true;
        L17:
            int r9 = LocaleController.getCurrencyExpDivider(PaymentFormActivity.m16624$$Nest$fgetpaymentForm(this.this$0).invoice.currency);
            if (r6 < 0) goto L20;
            String r10 = r1.substring(0, r6);
        L21:
            String r11 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r6 < 0) goto L24;
            String r12 = r1.substring(r6 + 1);
        L25:
            long r14 = Utilities.parseLong(PhoneFormat.stripExceptNumbers(r10)).longValue() * r9;
            long r15 = Utilities.parseLong(PhoneFormat.stripExceptNumbers(r12)).longValue();
            String r3 = _UrlKt.FRAGMENT_ENCODE_SET + r15;
            String r92 = _UrlKt.FRAGMENT_ENCODE_SET + (r9 - 1);
            if (r6 > 0) goto L28;
        L34:
            PaymentFormActivity.m16648$$Nest$fputtipAmount(this.this$0, Long.valueOf(r14 + r15));
            if (PaymentFormActivity.m16624$$Nest$fgetpaymentForm(this.this$0).invoice.max_tip_amount != 0) goto L37;
        L39:
            int r16 = PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[0].getSelectionStart();
            PaymentFormActivity.m16641$$Nest$fputignoreOnTextChange(this.this$0, true);
            long r2 = PaymentFormActivity.m16632$$Nest$fgettipAmount(this.this$0).longValue();
            PaymentFormActivity r32 = this.this$0;
            if (r2 != 0) goto L42;
            PaymentFormActivity.m16620$$Nest$fgetinputFields(r32)[0].setText(_UrlKt.FRAGMENT_ENCODE_SET);
        L44:
            if (r4 >= PaymentFormActivity.m16632$$Nest$fgettipAmount(this.this$0).longValue()) goto L52;
            if (r4 == 0) goto L52;
            if (this.anyBefore == false) goto L52;
            if (r16 < 0) goto L52;
            PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[0].setSelection(Math.min(r16, PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[0].length()));
        L66:
            this.lastDotEntered = r13;
            PaymentFormActivity.m16660$$Nest$mupdateTotalPrice(this.this$0);
            this.overrideText = null;
            PaymentFormActivity.m16641$$Nest$fputignoreOnTextChange(this.this$0, false);
            return;
        L52:
            if (this.isDeletedChar == false) goto L58;
            if (this.beforeTextLength == PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[0].length()) goto L58;
            PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[0].setSelection(Math.max(0, Math.min(r16, PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[0].length())));
        L58:
            if (this.lastDotEntered == true) goto L65;
            if (r13 == false) goto L65;
            if (r6 < 0) goto L65;
            int r17 = indexOfComma(r11);
            PaymentFormActivity r22 = this.this$0;
            if (r17 <= 0) goto L64;
            PaymentFormActivity.m16620$$Nest$fgetinputFields(r22)[0].setSelection(r17 + 1);
            goto L66
        L64:
            PaymentFormActivity.m16620$$Nest$fgetinputFields(r22)[0].setSelection(PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[0].length());
        L65:
            PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[0].setSelection(PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[0].length());
            goto L66
        L42:
            EditTextBoldCursor r23 = PaymentFormActivity.m16620$$Nest$fgetinputFields(r32)[0];
            r11 = LocaleController.getInstance().formatCurrencyString(PaymentFormActivity.m16632$$Nest$fgettipAmount(this.this$0).longValue(), false, r13, true, PaymentFormActivity.m16624$$Nest$fgetpaymentForm(this.this$0).invoice.currency);
            r23.setText(r11);
            goto L44
        L37:
            if (PaymentFormActivity.m16632$$Nest$fgettipAmount(this.this$0).longValue() <= PaymentFormActivity.m16624$$Nest$fgetpaymentForm(this.this$0).invoice.max_tip_amount) goto L39;
            PaymentFormActivity r18 = this.this$0;
            PaymentFormActivity.m16648$$Nest$fputtipAmount(r18, Long.valueOf(PaymentFormActivity.m16624$$Nest$fgetpaymentForm(r18).invoice.max_tip_amount));
            goto L39
        L28:
            if (r3.length() <= r92.length()) goto L34;
            if ((this.enteredCharacterStart - r6) >= r3.length()) goto L32;
            String r110 = r3.substring(0, r92.length());
        L33:
            r15 = Utilities.parseLong(r110).longValue();
            goto L34
        L32:
            r110 = r3.substring(r3.length() - r92.length());
            goto L33
        L24:
            r12 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L25
        L20:
            r10 = r1;
            goto L21
        L16:
            r13 = false;
            goto L17
        L8:
            r4 = 0;
            goto L9
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r10, int r11, int r12, int r13) {
            if (PaymentFormActivity.m16619$$Nest$fgetignoreOnTextChange(this.this$0) == true) goto L51;
            this.anyBefore = !TextUtils.isEmpty(r10);
            this.overrideText = null;
            if (r10 != null) goto L8;
            int r2 = 0;
        L9:
            this.beforeTextLength = r2;
            this.enteredCharacterStart = r11;
            if (r12 != 1) goto L13;
            if (r13 != 0) goto L13;
            boolean r122 = true;
        L14:
            this.isDeletedChar = r122;
            if (r122 == false) goto L42;
            String r102 = LocaleController.fixNumbers(r10);
            char r123 = r102.charAt(r11);
            int r132 = indexOfComma(r102);
            if (r132 < 0) goto L19;
            String r22 = r102.substring(r132 + 1);
        L20:
            long r3 = Utilities.parseLong(PhoneFormat.stripExceptNumbers(r22)).longValue();
            if (r123 < '0') goto L24;
            if (r123 > '9') goto L24;
        L28:
            if (r132 <= 0) goto L48;
            if (r11 > r132) goto L31;
            return;
        L31:
            if (r3 != 0) goto L50;
            this.overrideText = r102.substring(0, r132 - 1);
            return;
        L50:
            return;
        L48:
            return;
        L24:
            if (r22.length() == 0) goto L34;
            if (r3 == 0) goto L28;
        L34:
            int r124 = r11 - 1;
            if (r124 < 0) goto L52;
            char r133 = r102.charAt(r124);
            if (r133 < '0') goto L41;
            if (r133 > '9') goto L41;
            this.overrideText = r102.substring(0, r124).concat(r102.substring(r11));
            return;
        L41:
            r11 = r124;
            goto L34
        L52:
            return;
        L19:
            r22 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L20
        L42:
            return;
        L13:
            r122 = false;
            goto L14
        L8:
            r2 = r10.length();
            goto L9
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$12, reason: invalid class name */
    public class AnonymousClass12 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ PaymentFormActivity this$0;
        final /* synthetic */ int val$N;
        final /* synthetic */ int[] val$maxTextWidth;
        final /* synthetic */ int[] val$textWidths;

        public AnonymousClass12(PaymentFormActivity r1, Context r2, int r3, int[] r4, int[] r5) {
            this.this$0 = r1;
            this.val$N = r3;
            this.val$maxTextWidth = r4;
            this.val$textWidths = r5;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r11, int r12) {
            int r0 = View.MeasureSpec.getSize(r11);
            this.ignoreLayout = true;
            int r2 = AndroidUtilities.dp(9.0f);
            int r3 = this.val$N;
            int r22 = r2 * (r3 - 1);
            int r4 = (this.val$maxTextWidth[0] * r3) + r22;
            float r32 = 1.0f;
            if (r4 > r0) goto L8;
            setWeightSum(1.0f);
            int r02 = getChildCount();
            int r1 = 0;
        L5:
            if (r1 >= r02) goto L23;
            getChildAt(r1).getLayoutParams().width = 0;
            ((LinearLayout.LayoutParams) getChildAt(r1).getLayoutParams()).weight = 1.0f / r02;
            r1 = r1 + 1;
        L23:
            this.ignoreLayout = false;
            super.onMeasure(r11, r12);
            return;
        L8:
            if ((this.val$textWidths[0] + r22) > r0) goto L20;
            setWeightSum(1.0f);
            int r03 = r0 - r22;
            int r23 = getChildCount();
            int r42 = 0;
        L10:
            if (r42 >= r23) goto L12;
            LinearLayout.LayoutParams r8 = (LinearLayout.LayoutParams) getChildAt(r42).getLayoutParams();
            r8.width = 0;
            float r7 = ((Integer) r7.getTag(R.id.width_tag)).intValue() / r03;
            r8.weight = r7;
            r32 = r32 - r7;
            r42 = r42 + 1;
            goto L10
        L12:
            float r33 = r32 / (this.val$N - 1);
            if (r33 <= 0.0f) goto L23;
            int r04 = getChildCount();
            int r13 = 0;
        L15:
            if (r13 >= r04) goto L23;
            View r24 = getChildAt(r13);
            LinearLayout.LayoutParams r43 = (LinearLayout.LayoutParams) r24.getLayoutParams();
            if (((Integer) r24.getTag(R.id.width_tag)).intValue() == this.val$maxTextWidth[0]) goto L19;
            r43.weight += r33;
        L19:
            r13 = r13 + 1;
            goto L15
        L20:
            setWeightSum(0.0f);
            int r05 = getChildCount();
            int r14 = 0;
        L21:
            if (r14 >= r05) goto L23;
            getChildAt(r14).getLayoutParams().width = -2;
            ((LinearLayout.LayoutParams) getChildAt(r14).getLayoutParams()).weight = 0.0f;
            r14 = r14 + 1;
            goto L21
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$13, reason: invalid class name */
    public class AnonymousClass13 extends TextDetailSettingsCell {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass13(PaymentFormActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setVisibility(int r1) {
            super.setVisibility(r1);
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$14, reason: invalid class name */
    public class AnonymousClass14 implements PaymentFormActivityDelegate {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass14(PaymentFormActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PaymentFormActivity.PaymentFormActivityDelegate
        public void didSelectNewAddress(TLRPC.TL_payments_validateRequestedInfo r2) {
            PaymentFormActivity.m16649$$Nest$fputvalidateRequest(this.this$0, r2);
            PaymentFormActivity r1 = this.this$0;
            PaymentFormActivity.m16656$$Nest$msetAddressFields(r1, PaymentFormActivity.m16633$$Nest$fgetvalidateRequest(r1).info);
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$15, reason: invalid class name */
    public class AnonymousClass15 implements PaymentFormActivityDelegate {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass15(PaymentFormActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PaymentFormActivity.PaymentFormActivityDelegate
        public void didSelectNewAddress(TLRPC.TL_payments_validateRequestedInfo r2) {
            PaymentFormActivity.m16649$$Nest$fputvalidateRequest(this.this$0, r2);
            PaymentFormActivity r1 = this.this$0;
            PaymentFormActivity.m16656$$Nest$msetAddressFields(r1, PaymentFormActivity.m16633$$Nest$fgetvalidateRequest(r1).info);
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$16, reason: invalid class name */
    public class AnonymousClass16 implements PaymentFormActivityDelegate {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass16(PaymentFormActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PaymentFormActivity.PaymentFormActivityDelegate
        public void didSelectNewAddress(TLRPC.TL_payments_validateRequestedInfo r2) {
            PaymentFormActivity.m16649$$Nest$fputvalidateRequest(this.this$0, r2);
            PaymentFormActivity r1 = this.this$0;
            PaymentFormActivity.m16656$$Nest$msetAddressFields(r1, PaymentFormActivity.m16633$$Nest$fgetvalidateRequest(r1).info);
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$17, reason: invalid class name */
    public class AnonymousClass17 implements PaymentFormActivityDelegate {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass17(PaymentFormActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PaymentFormActivity.PaymentFormActivityDelegate
        public void didSelectNewAddress(TLRPC.TL_payments_validateRequestedInfo r2) {
            PaymentFormActivity.m16649$$Nest$fputvalidateRequest(this.this$0, r2);
            PaymentFormActivity r1 = this.this$0;
            PaymentFormActivity.m16656$$Nest$msetAddressFields(r1, PaymentFormActivity.m16633$$Nest$fgetvalidateRequest(r1).info);
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$18, reason: invalid class name */
    public class AnonymousClass18 extends WebView {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass18(PaymentFormActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.webkit.WebView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            getParent().requestDisallowInterceptTouchEvent(true);
            return super.onTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$19, reason: invalid class name */
    public class AnonymousClass19 extends WebViewClient {
        final /* synthetic */ PaymentFormActivity this$0;
        final /* synthetic */ Context val$context;

        /* renamed from: $r8$lambda$QFzFtoq5PH3k8kW_ZVuScfjj-Es, reason: not valid java name */
        public static /* synthetic */ void m16664$r8$lambda$QFzFtoq5PH3k8kW_ZVuScfjjEs(AnonymousClass19 r0) {
            r0.lambda$onRenderProcessGone$0();
        }

        public AnonymousClass19(PaymentFormActivity r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        private /* synthetic */ void lambda$onRenderProcessGone$0() {
            Browser.openUrl(this.this$0.getContext(), "https://play.google.com/store/apps/details?id=com.google.android.webview");
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView r2, String r3) {
            super.onPageFinished(r2, r3);
            PaymentFormActivity.m16650$$Nest$fputwebviewLoading(this.this$0, false);
            PaymentFormActivity.m16658$$Nest$mshowEditDoneProgress(this.this$0, true, false);
            PaymentFormActivity.m16659$$Nest$mupdateSavePaymentField(this.this$0);
        }

        @Override // android.webkit.WebViewClient
        public boolean onRenderProcessGone(WebView r3, RenderProcessGoneDetail r4) {
        L7:
            e = move-exception;
            FileLog.e(e);
            return false;
        L3:
            if (AndroidUtilities.isSafeToShow(this.this$0.getContext()) == true) goto L5;
            return true;
        L5:
            new AlertDialog.Builder(this.this$0.getContext(), PaymentFormActivity.m16631$$Nest$fgetresourcesProvider(this.this$0)).setTitle(LocaleController.getString(R.string.ChromeCrashTitle)).setMessage(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.ChromeCrashMessage), new PaymentFormActivity$19$$ExternalSyntheticLambda0(this))).setPositiveButton(LocaleController.getString(R.string.OK), null).show();     // Catch: Exception -> L7
            return true;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView r4, String r5) {
            Uri r42 = Uri.parse(r5);     // Catch: Exception -> L19
            if ("t.me".equals(r42.getHost()) == false) goto L7;
            PaymentFormActivity.m16652$$Nest$mgoToNextStep(this.this$0);     // Catch: Exception -> L19
            return true;
        L7:
            if (PaymentFormActivity.m16661$$Nest$sfgetBLACKLISTED_PROTOCOLS().contains(r42.getScheme()) == false) goto L10;
            return true;
        L10:
            if (PaymentFormActivity.m16662$$Nest$sfgetWEBVIEW_PROTOCOLS().contains(r42.getScheme()) == false) goto L20;
            return false;
        L20:
        L15:
            new AlertDialog.Builder(this.val$context).setTitle(PaymentFormActivity.m16606$$Nest$fgetcurrentBotName(this.this$0)).setMessage(LocaleController.getString(R.string.PaymentAppNotFoundForDeeplink)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();     // Catch: Exception -> L19
        L16:
            return true;
        L12:
            if ((this.this$0.getContext() instanceof Activity) == false) goto L16;
            ((Activity) this.this$0.getContext()).startActivityForResult(new Intent("android.intent.action.VIEW", r42), Opcodes.MUL_INT_LIT16);     // Catch: ActivityNotFoundException -> L15 Exception -> L19
        L23:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends View {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass2(PaymentFormActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            if (LocaleController.isRTL == false) goto L6;
            float r0 = 0.0f;
        L5:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L10;
            int r1 = AndroidUtilities.dp(20.0f);
        L11:
            r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L10:
            r1 = 0;
            goto L11
        L6:
            r0 = AndroidUtilities.dp(20.0f);
            goto L5
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$20, reason: invalid class name */
    public class AnonymousClass20 implements TextWatcher {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass20(PaymentFormActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r2) {
            if (PaymentFormActivity.m16614$$Nest$fgetemailCodeLength(this.this$0) != 0) goto L5;
            return;
        L5:
            if (r2.length() != PaymentFormActivity.m16614$$Nest$fgetemailCodeLength(this.this$0)) goto L9;
            PaymentFormActivity.m16655$$Nest$msendSavePassword(this.this$0, false);
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$21, reason: invalid class name */
    public class AnonymousClass21 extends View {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass21(PaymentFormActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            if (LocaleController.isRTL == false) goto L6;
            float r0 = 0.0f;
        L5:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L10;
            int r1 = AndroidUtilities.dp(20.0f);
        L11:
            r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L10:
            r1 = 0;
            goto L11
        L6:
            r0 = AndroidUtilities.dp(20.0f);
            goto L5
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$22, reason: invalid class name */
    public class AnonymousClass22 implements PaymentFormActivityDelegate {
        final /* synthetic */ PaymentFormActivity this$0;
        final /* synthetic */ Runnable val$callback;

        public AnonymousClass22(PaymentFormActivity r1, Runnable r2) {
            this.this$0 = r1;
            this.val$callback = r2;
        }

        @Override // org.telegram.ui.PaymentFormActivity.PaymentFormActivityDelegate
        public boolean didSelectNewCard(String r2, String r3, boolean r4, TLRPC.TL_inputPaymentCredentialsGooglePay r5, TLRPC.TL_paymentSavedCredentialsCard r6) {
            PaymentFormActivity.m16645$$Nest$fputsavedCredentialsCard(this.this$0, r6);
            PaymentFormActivity.m16643$$Nest$fputpaymentJson(this.this$0, r2);
            PaymentFormActivity.m16644$$Nest$fputsaveCardInfo(this.this$0, r4);
            PaymentFormActivity.m16635$$Nest$fputcardName(this.this$0, r3);
            PaymentFormActivity.m16638$$Nest$fputgooglePayCredentials(this.this$0, r5);
            if (PaymentFormActivity.m16610$$Nest$fgetdetailSettingsCell(this.this$0)[0] == null) goto L13;
            PaymentFormActivity.m16610$$Nest$fgetdetailSettingsCell(this.this$0)[0].setVisibility(0);
            TextDetailSettingsCell r22 = PaymentFormActivity.m16610$$Nest$fgetdetailSettingsCell(this.this$0)[0];
            if (PaymentFormActivity.m16602$$Nest$fgetcardName(this.this$0) != null) goto L7;
        L9:
            String r42 = PaymentFormActivity.m16602$$Nest$fgetcardName(this.this$0);
        L10:
            r22.setTextAndValueAndIcon(r42, LocaleController.getString(R.string.PaymentCheckoutMethod), R.drawable.msg_payment_card, true);
            if (PaymentFormActivity.m16610$$Nest$fgetdetailSettingsCell(this.this$0)[1] == null) goto L13;
            PaymentFormActivity.m16610$$Nest$fgetdetailSettingsCell(this.this$0)[1].setVisibility(0);
            goto L13
        L7:
            if (PaymentFormActivity.m16602$$Nest$fgetcardName(this.this$0).length() <= 1) goto L9;
            r42 = PaymentFormActivity.m16602$$Nest$fgetcardName(this.this$0).substring(0, 1).toUpperCase() + PaymentFormActivity.m16602$$Nest$fgetcardName(this.this$0).substring(1);
        L13:
            Runnable r1 = this.val$callback;
            if (r1 == null) goto L16;
            r1.run();
        L16:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$23, reason: invalid class name */
    public class AnonymousClass23 extends JSONObject {
        final /* synthetic */ PaymentFormActivity this$0;

        /* renamed from: org.telegram.ui.PaymentFormActivity$23$1, reason: invalid class name */
        public class AnonymousClass1 extends JSONObject {
            final /* synthetic */ AnonymousClass23 this$1;

            public AnonymousClass1(AnonymousClass23 r3) {
                this.this$1 = r3;
                put("protocolVersion", "ECv2");
                put("publicKey", PaymentFormActivity.m16616$$Nest$fgetgooglePayPublicKey(r3.this$0));
            }
        }

        public AnonymousClass23(PaymentFormActivity r2) {
            this.this$0 = r2;
            put("type", "DIRECT");
            put("parameters", new AnonymousClass1(this));
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$24, reason: invalid class name */
    public class AnonymousClass24 extends JSONObject {
        final /* synthetic */ PaymentFormActivity this$0;

        /* renamed from: org.telegram.ui.PaymentFormActivity$24$1, reason: invalid class name */
        public class AnonymousClass1 extends JSONObject {
            final /* synthetic */ AnonymousClass24 this$1;

            public AnonymousClass1(AnonymousClass24 r3) {
                this.this$1 = r3;
                put("gateway", "stripe");
                put("stripe:publishableKey", PaymentFormActivity.m16628$$Nest$fgetproviderApiKey(r3.this$0));
                put("stripe:version", "3.5.0");
            }
        }

        public AnonymousClass24(PaymentFormActivity r3) {
            this.this$0 = r3;
            put("type", "PAYMENT_GATEWAY");
            if (PaymentFormActivity.m16615$$Nest$fgetgooglePayParameters(r3) == null) goto L6;
            put("parameters", PaymentFormActivity.m16615$$Nest$fgetgooglePayParameters(r3));
            return;
        L6:
            put("parameters", new AnonymousClass1(this));
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$25, reason: invalid class name */
    public class AnonymousClass25 implements PaymentFormActivityDelegate {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass25(PaymentFormActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PaymentFormActivity.PaymentFormActivityDelegate
        public void currentPasswordUpdated(TL_account.Password r1) {
            PaymentFormActivity.m16636$$Nest$fputcurrentPassword(this.this$0, r1);
        }

        @Override // org.telegram.ui.PaymentFormActivity.PaymentFormActivityDelegate
        public boolean didSelectNewCard(String r8, String r9, boolean r10, TLRPC.TL_inputPaymentCredentialsGooglePay r11, TLRPC.TL_paymentSavedCredentialsCard r12) {
            if (PaymentFormActivity.m16609$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            PaymentFormActivity.m16609$$Nest$fgetdelegate(this.this$0).didSelectNewCard(r8, r9, r10, r11, r12);
        L6:
            if (PaymentFormActivity.m16621$$Nest$fgetisWebView(this.this$0) == false) goto L9;
            this.this$0.removeSelfFromStack();
        L9:
            if (PaymentFormActivity.m16609$$Nest$fgetdelegate(this.this$0) == null) goto L12;
            return true;
        L12:
            return false;
        }

        @Override // org.telegram.ui.PaymentFormActivity.PaymentFormActivityDelegate
        public void onFragmentDestroyed() {
            PaymentFormActivity.m16642$$Nest$fputpasswordFragment(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$26, reason: invalid class name */
    public class AnonymousClass26 implements TokenCallback {
        final /* synthetic */ PaymentFormActivity this$0;

        public static /* synthetic */ void $r8$lambda$rjBKzdSYTNGfpLBj8fhYXc8WidQ(AnonymousClass26 r0) {
            r0.lambda$onSuccess$0();
        }

        public AnonymousClass26(PaymentFormActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onSuccess$0() {
            PaymentFormActivity.m16652$$Nest$mgoToNextStep(this.this$0);
            PaymentFormActivity.m16658$$Nest$mshowEditDoneProgress(this.this$0, true, false);
            PaymentFormActivity.m16657$$Nest$msetDonePressed(this.this$0, false);
        }

        @Override // com.stripe.android.TokenCallback
        public void onError(Exception r4) {
            if (PaymentFormActivity.m16601$$Nest$fgetcanceled(this.this$0) == false) goto L5;
            return;
        L5:
            PaymentFormActivity.m16658$$Nest$mshowEditDoneProgress(this.this$0, true, false);
            PaymentFormActivity.m16657$$Nest$msetDonePressed(this.this$0, false);
            if ((r4 instanceof APIConnectionException) == false) goto L8;
        L12:
            AlertsCreator.showSimpleToast(this.this$0, LocaleController.getString(R.string.PaymentConnectionFailed));
            return;
        L8:
            if ((r4 instanceof APIException) == true) goto L12;
            AlertsCreator.showSimpleToast(this.this$0, r4.getMessage());
        }

        @Override // com.stripe.android.TokenCallback
        public void onSuccess(Token r4) {
            if (PaymentFormActivity.m16601$$Nest$fgetcanceled(this.this$0) == false) goto L5;
            return;
        L5:
            PaymentFormActivity.m16643$$Nest$fputpaymentJson(this.this$0, String.format(Locale.US, "{\"type\":\"%1$s\", \"id\":\"%2$s\"}", new Object[]{r4.getType(), r4.getId()}));
            AndroidUtilities.runOnUIThread(new PaymentFormActivity$26$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$27, reason: invalid class name */
    public class AnonymousClass27 extends AsyncTask<Object, Object, String> {
        final /* synthetic */ PaymentFormActivity this$0;
        final /* synthetic */ Card val$card;

        public AnonymousClass27(PaymentFormActivity r1, Card r2) {
            this.this$0 = r1;
            this.val$card = r2;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ String doInBackground(Object[] r1) {
            return doInBackground2(r1);
        }

        /* JADX WARN: Not initialized variable reg: 4, insn: 0x013e: MOVE (r2 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:35:0x013e */
        @Override // android.os.AsyncTask
        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public String doInBackground2(Object... r10) {
            HttpURLConnection r2 = null;
            JSONObject r3 = new JSONObject();     // Catch: Throwable -> L12 Exception -> L17
            JSONObject r4 = new JSONObject();     // Catch: Throwable -> L12 Exception -> L17
            r4.put("number", this.val$card.getNumber());     // Catch: Throwable -> L12 Exception -> L17
            r4.put("expiration_month", String.format(Locale.US, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{this.val$card.getExpMonth()}));     // Catch: Throwable -> L12 Exception -> L17
            r4.put("expiration_year", _UrlKt.FRAGMENT_ENCODE_SET + this.val$card.getExpYear());     // Catch: Throwable -> L12 Exception -> L17
            r4.put("security_code", _UrlKt.FRAGMENT_ENCODE_SET + this.val$card.getCVC());     // Catch: Throwable -> L12 Exception -> L17
            r3.put("card", r4);     // Catch: Throwable -> L12 Exception -> L17
            if (PaymentFormActivity.m16624$$Nest$fgetpaymentForm(this.this$0).native_params != null) goto L65;
        L14:
            String r42 = null;
        L15:
            if (r42 == null) goto L20;
            URL r5 = new URL(r42);     // Catch: Throwable -> L12 Exception -> L17
        L23:
            HttpURLConnection r43 = (HttpURLConnection) r5.openConnection();     // Catch: Throwable -> L12 Exception -> L17
        L58:
            r43.setConnectTimeout(30000);     // Catch: Throwable -> L34 Exception -> L36
            r43.setReadTimeout(80000);     // Catch: Throwable -> L34 Exception -> L36
            r43.setUseCaches(false);     // Catch: Throwable -> L34 Exception -> L36
            r43.setDoOutput(true);     // Catch: Throwable -> L34 Exception -> L36
            r43.setRequestMethod("POST");     // Catch: Throwable -> L34 Exception -> L36
            r43.setRequestProperty("Content-Type", "application/json");     // Catch: Throwable -> L34 Exception -> L36
            r43.setRequestProperty("X-PUBLIC-TOKEN", PaymentFormActivity.m16628$$Nest$fgetproviderApiKey(this.this$0));     // Catch: Throwable -> L34 Exception -> L36
            OutputStream r9 = r43.getOutputStream();     // Catch: Throwable -> L34 Exception -> L36
            r9.write(r3.toString().getBytes("UTF-8"));     // Catch: Throwable -> L42
            r9.close();     // Catch: Throwable -> L34 Exception -> L36
            int r92 = r43.getResponseCode();     // Catch: Throwable -> L34 Exception -> L36
            if (r92 < 200) goto L39;
            if (r92 >= 300) goto L39;
            JSONObject r93 = new JSONObject();     // Catch: Throwable -> L34 Exception -> L36
            r93.put("token", new JSONObject(PaymentFormActivity.m16663$$Nest$smgetResponseBody(r43.getInputStream())).getJSONObject("data").getString("token"));     // Catch: Throwable -> L34 Exception -> L36
            r93.put("type", "card");     // Catch: Throwable -> L34 Exception -> L36
            String r94 = r93.toString();     // Catch: Throwable -> L34 Exception -> L36
            r43.disconnect();
            return r94;
        L39:
            if (BuildVars.DEBUG_VERSION == false) goto L41;
            FileLog.e(_UrlKt.FRAGMENT_ENCODE_SET + PaymentFormActivity.m16663$$Nest$smgetResponseBody(r43.getErrorStream()));     // Catch: Throwable -> L34 Exception -> L36
        L41:
            r43.disconnect();
        L52:
            return null;
        L42:
            th = move-exception;
            if (r9 != null) goto L67;
        L48:
            throw th;     // Catch: Throwable -> L34 Exception -> L36
        L67:
            r9.close();     // Catch: Throwable -> L46
        L46:
            th = move-exception;
            th.addSuppressed(th);     // Catch: Throwable -> L34 Exception -> L36
        L36:
            e = e;
        L49:
            FileLog.e(e);     // Catch: Throwable -> L34
            if (r43 == null) goto L52;
        L20:
            if (PaymentFormActivity.m16624$$Nest$fgetpaymentForm(this.this$0).invoice.test == false) goto L22;
            r5 = new URL("https://tgb-playground.smart-glocal.com/cds/v1/tokenize/card");     // Catch: Throwable -> L12 Exception -> L17
            goto L23
        L22:
            r5 = new URL("https://tgb.smart-glocal.com/cds/v1/tokenize/card");     // Catch: Throwable -> L12 Exception -> L17
            goto L23
        L65:
            r42 = new JSONObject(PaymentFormActivity.m16624$$Nest$fgetpaymentForm(this.this$0).native_params.data).getString("tokenize_url");     // Catch: Throwable -> L12 Exception -> L57
            if (r42 == null) goto L15;
            if (r42.startsWith("https://") == false) goto L14;
            if (r42.endsWith(".smart-glocal.com/cds/v1/tokenize/card") == true) goto L15;
        L12:
            th = th;
        L53:
            if (r2 == null) goto L55;
            r2.disconnect();
        L55:
            throw th;
        L17:
            e = e;
            r43 = null;
        L34:
            th = th;
            r2 = r4;
            goto L53
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(String r1) {
            onPostExecute2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(String r3) {
            if (PaymentFormActivity.m16601$$Nest$fgetcanceled(this.this$0) == false) goto L5;
            return;
        L5:
            PaymentFormActivity r0 = this.this$0;
            if (r3 != null) goto L8;
            AlertsCreator.showSimpleToast(r0, LocaleController.getString(R.string.PaymentConnectionFailed));
        L9:
            PaymentFormActivity.m16658$$Nest$mshowEditDoneProgress(this.this$0, true, false);
            PaymentFormActivity.m16657$$Nest$msetDonePressed(this.this$0, false);
            return;
        L8:
            PaymentFormActivity.m16643$$Nest$fputpaymentJson(r0, r3);
            PaymentFormActivity.m16652$$Nest$mgoToNextStep(this.this$0);
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$28, reason: invalid class name */
    public class AnonymousClass28 extends AnimatorListenerAdapter {
        final /* synthetic */ PaymentFormActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass28(PaymentFormActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (PaymentFormActivity.m16612$$Nest$fgetdoneItemAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (PaymentFormActivity.m16612$$Nest$fgetdoneItemAnimation(this.this$0).equals(r2) == false) goto L9;
            PaymentFormActivity.m16637$$Nest$fputdoneItemAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (PaymentFormActivity.m16612$$Nest$fgetdoneItemAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (PaymentFormActivity.m16612$$Nest$fgetdoneItemAnimation(this.this$0).equals(r2) == false) goto L13;
            boolean r22 = this.val$show;
            PaymentFormActivity r1 = this.this$0;
            if (r22 == true) goto L10;
            PaymentFormActivity.m16626$$Nest$fgetprogressView(r1).setVisibility(4);
            return;
        L10:
            PaymentFormActivity.m16611$$Nest$fgetdoneItem(r1).getContentView().setVisibility(4);
            return;
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$29, reason: invalid class name */
    public class AnonymousClass29 extends AnimatorListenerAdapter {
        final /* synthetic */ PaymentFormActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass29(PaymentFormActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (PaymentFormActivity.m16612$$Nest$fgetdoneItemAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (PaymentFormActivity.m16612$$Nest$fgetdoneItemAnimation(this.this$0).equals(r2) == false) goto L9;
            PaymentFormActivity.m16637$$Nest$fputdoneItemAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (PaymentFormActivity.m16612$$Nest$fgetdoneItemAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (PaymentFormActivity.m16612$$Nest$fgetdoneItemAnimation(this.this$0).equals(r2) == false) goto L13;
            boolean r22 = this.val$show;
            PaymentFormActivity r1 = this.this$0;
            if (r22 == true) goto L10;
            PaymentFormActivity.m16627$$Nest$fgetprogressViewButton(r1).setVisibility(4);
            return;
        L10:
            PaymentFormActivity.m16623$$Nest$fgetpayTextView(r1).setVisibility(4);
            return;
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements TextWatcher {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass3(PaymentFormActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r10) {
            if (PaymentFormActivity.m16619$$Nest$fgetignoreOnTextChange(this.this$0) == false) goto L5;
            return;
        L5:
            PaymentFormActivity.m16641$$Nest$fputignoreOnTextChange(this.this$0, true);
            String r102 = PhoneFormat.stripExceptNumbers(PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[8].getText().toString());
            PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[8].setText(r102);
            HintEditText r2 = (HintEditText) PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[9];
            if (r102.length() != 0) goto L8;
            r2.setHintText(null);
            r2.setHint(LocaleController.getString(R.string.PaymentShippingPhoneNumber));
        L31:
            PaymentFormActivity.m16641$$Nest$fputignoreOnTextChange(this.this$0, false);
            return;
        L8:
            int r7 = 4;
            if (r102.length() <= 4) goto L18;
        L10:
            if (r7 < 1) goto L15;
            String r4 = r102.substring(0, r7);
            if (((String) PaymentFormActivity.m16603$$Nest$fgetcodesMap(this.this$0).get(r4)) != null) goto L13;
            r7 = r7 - 1;
            goto L10
        L13:
            String r103 = r102.substring(r7) + PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[9].getText().toString();
            PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[8].setText(r4);
            String r42 = r103;
            r102 = r4;
            boolean r72 = true;
        L16:
            if (r72 == true) goto L19;
            r42 = r102.substring(1) + PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[9].getText().toString();
            EditTextBoldCursor r3 = PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[8];
            r102 = r102.substring(0, 1);
            r3.setText(r102);
        L19:
            String r0 = (String) PaymentFormActivity.m16603$$Nest$fgetcodesMap(this.this$0).get(r102);
            if (r0 != null) goto L22;
        L26:
            r2.setHintText(null);
            r2.setHint(LocaleController.getString(R.string.PaymentShippingPhoneNumber));
        L27:
            if (r72 == true) goto L29;
            PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[8].setSelection(PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[8].getText().length());
        L29:
            if (r42 == null) goto L31;
            r2.requestFocus();
            r2.setText(r42);
            r2.setSelection(r2.length());
            goto L31
        L22:
            if (PaymentFormActivity.m16604$$Nest$fgetcountriesArray(this.this$0).indexOf(r0) == (-1)) goto L26;
            String r104 = (String) PaymentFormActivity.m16625$$Nest$fgetphoneFormatMap(this.this$0).get(r102);
            if (r104 == null) goto L26;
            r2.setHintText(r104.replace('X', Typography.ndash));
            r2.setHint(null);
            goto L27
        L15:
            r72 = false;
            r42 = null;
            goto L16
        L18:
            r72 = false;
            r42 = null;
            goto L19
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements TextWatcher {
        private int actionPosition;
        private int characterAction;
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass4(PaymentFormActivity r1) {
            this.this$0 = r1;
            this.characterAction = -1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r11) {
            if (PaymentFormActivity.m16618$$Nest$fgetignoreOnPhoneChange(this.this$0) == false) goto L5;
            return;
        L5:
            HintEditText r112 = (HintEditText) PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[9];
            int r0 = r112.getSelectionStart();
            String r1 = r112.getText().toString();
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
            PaymentFormActivity.m16640$$Nest$fputignoreOnPhoneChange(this.this$0, true);
            String r12 = r112.getHintText();
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
            r112.setText(r2);
            if (r0 < 0) goto L40;
            r112.setSelection(Math.min(r0, r112.length()));
        L40:
            r112.onTextChange();
            PaymentFormActivity.m16640$$Nest$fputignoreOnPhoneChange(this.this$0, false);
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

    /* renamed from: org.telegram.ui.PaymentFormActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends WebView {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass5(PaymentFormActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
        }

        @Override // android.webkit.WebView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            ((ViewGroup) this.this$0.fragmentView).requestDisallowInterceptTouchEvent(true);
            return super.onTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends WebViewClient {
        final /* synthetic */ PaymentFormActivity this$0;
        final /* synthetic */ Context val$context;

        public static /* synthetic */ void $r8$lambda$HZLuqCbzugG4l0gXudC2ZMsH7Gw(AnonymousClass6 r0) {
            r0.lambda$onRenderProcessGone$0();
        }

        public AnonymousClass6(PaymentFormActivity r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        private /* synthetic */ void lambda$onRenderProcessGone$0() {
            Browser.openUrl(this.this$0.getContext(), "https://play.google.com/store/apps/details?id=com.google.android.webview");
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView r1, String r2) {
            super.onLoadResource(r1, r2);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView r2, String r3) {
            super.onPageFinished(r2, r3);
            PaymentFormActivity.m16650$$Nest$fputwebviewLoading(this.this$0, false);
            PaymentFormActivity.m16658$$Nest$mshowEditDoneProgress(this.this$0, true, false);
            PaymentFormActivity.m16659$$Nest$mupdateSavePaymentField(this.this$0);
        }

        @Override // android.webkit.WebViewClient
        public boolean onRenderProcessGone(WebView r3, RenderProcessGoneDetail r4) {
        L7:
            e = move-exception;
            FileLog.e(e);
            return false;
        L3:
            if (AndroidUtilities.isSafeToShow(this.this$0.getContext()) == true) goto L5;
            return true;
        L5:
            new AlertDialog.Builder(this.this$0.getContext(), PaymentFormActivity.m16631$$Nest$fgetresourcesProvider(this.this$0)).setTitle(LocaleController.getString(R.string.ChromeCrashTitle)).setMessage(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.ChromeCrashMessage), new PaymentFormActivity$6$$ExternalSyntheticLambda0(this))).setPositiveButton(LocaleController.getString(R.string.OK), null).show();     // Catch: Exception -> L7
            return true;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView r6, String r7) {
            PaymentFormActivity.m16647$$Nest$fputshouldNavigateBack(this.this$0, !r7.equals(PaymentFormActivity.m16634$$Nest$fgetwebViewUrl(r0)));
            Uri r0 = Uri.parse(r7);     // Catch: Exception -> L20
            if ("t.me".equals(r0.getHost()) == false) goto L8;
            PaymentFormActivity.m16652$$Nest$mgoToNextStep(this.this$0);     // Catch: Exception -> L20
            return true;
        L8:
            if (PaymentFormActivity.m16661$$Nest$sfgetBLACKLISTED_PROTOCOLS().contains(r0.getScheme()) == false) goto L11;
            return true;
        L11:
            if (PaymentFormActivity.m16662$$Nest$sfgetWEBVIEW_PROTOCOLS().contains(r0.getScheme()) == true) goto L19;
        L16:
            new AlertDialog.Builder(this.val$context).setTitle(PaymentFormActivity.m16606$$Nest$fgetcurrentBotName(this.this$0)).setMessage(LocaleController.getString(R.string.PaymentAppNotFoundForDeeplink)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();     // Catch: Exception -> L20
        L17:
            return true;
        L13:
            if ((this.this$0.getContext() instanceof Activity) == false) goto L17;
            ((Activity) this.this$0.getContext()).startActivityForResult(new Intent("android.intent.action.VIEW", r0), Opcodes.MUL_INT_LIT16);     // Catch: ActivityNotFoundException -> L16 Exception -> L20
        L19:
            return super.shouldOverrideUrlLoading(r6, r7);
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements TextWatcher {
        public final String[] PREFIXES_14;
        public final String[] PREFIXES_15;
        public final String[] PREFIXES_16;
        private int actionPosition;
        private int characterAction;
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass7(PaymentFormActivity r45) {
            this.this$0 = r45;
            this.PREFIXES_15 = new String[]{"34", "37"};
            this.PREFIXES_14 = new String[]{"300", "301", "302", "303", "304", "305", "309", "36", "38", "39"};
            this.PREFIXES_16 = new String[]{"2221", "2222", "2223", "2224", "2225", "2226", "2227", "2228", "2229", "2200", "2201", "2202", "2203", "2204", "8600", "9860", "223", "224", "225", "226", "227", "228", "229", "23", "24", "25", "26", "270", "271", "2720", "50", "51", "52", "53", "54", "55", "4", "60", "62", "64", "65", "35"};
            this.characterAction = -1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r18) {
            if (PaymentFormActivity.m16617$$Nest$fgetignoreOnCardChange(this.this$0) == false) goto L5;
            return;
        L5:
            int r2 = 0;
            EditTextBoldCursor r1 = PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[0];
            int r3 = r1.getSelectionStart();
            String r4 = r1.getText().toString();
            int r7 = 1;
            if (this.characterAction != 3) goto L8;
            r4 = r4.substring(0, this.actionPosition).concat(r4.substring(this.actionPosition + 1));
            r3 = r3 - 1;
        L8:
            StringBuilder r5 = new StringBuilder(r4.length());
            int r8 = 0;
        L10:
            if (r8 >= r4.length()) goto L15;
            int r9 = r8 + 1;
            String r82 = r4.substring(r8, r9);
            if ("0123456789".contains(r82) == false) goto L14;
            r5.append(r82);
        L14:
            r8 = r9;
            goto L10
        L15:
            PaymentFormActivity.m16639$$Nest$fputignoreOnCardChange(this.this$0, true);
            String r83 = null;
            int r92 = 100;
            if (r5.length() <= 0) goto L44;
            String r42 = r5.toString();
            int r10 = 0;
        L18:
            if (r10 >= 3) goto L40;
            if (r10 == 0) goto L23;
            if (r10 == r7) goto L22;
            String[] r11 = this.PREFIXES_14;
            int r12 = 14;
            String r13 = "xxxx xxxx xxxx xx";
        L24:
            int r14 = r2;
        L26:
            if (r14 >= r11.length) goto L36;
            String r15 = r11[r14];
            int r16 = r7;
            if (r42.length() > r15.length()) goto L33;
            if (r15.startsWith(r42) == false) goto L35;
        L34:
            r92 = r12;
            r83 = r13;
        L37:
            if (r83 != null) goto L42;
            r10 = r10 + 1;
            r7 = r16;
            r2 = 0;
        L42:
            if (r5.length() <= r92) goto L45;
            r5.setLength(r92);
        L45:
            if (r83 == null) goto L70;
            if (r5.length() != r92) goto L49;
            PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[r16].requestFocus();
        L49:
            r1.setTextColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
            int r22 = 0;
        L51:
            if (r22 >= r5.length()) goto L70;
            if (r22 >= r83.length()) goto L63;
            if (r83.charAt(r22) != ' ') goto L62;
            r5.insert(r22, ' ');
            r22 = r22 + 1;
            if (r3 != r22) goto L62;
            int r43 = this.characterAction;
            if (r43 == 2) goto L62;
            if (r43 == 3) goto L62;
            r3 = r3 + 1;
        L62:
            r22 = r22 + 1;
            goto L51
        L63:
            r5.insert(r22, ' ');
            if (r3 != (r22 + 1)) goto L70;
            int r23 = this.characterAction;
            if (r23 == 2) goto L70;
            if (r23 == 3) goto L70;
            r3 = r3 + 1;
        L70:
            if (r5.toString().equals(r18.toString()) == true) goto L72;
            boolean r6 = false;
            r18.replace(0, r18.length(), r5);
        L73:
            if (r3 < 0) goto L75;
            r1.setSelection(Math.min(r3, r1.length()));
        L75:
            PaymentFormActivity.m16639$$Nest$fputignoreOnCardChange(this.this$0, r6);
            return;
        L72:
            r6 = false;
        L35:
            r14 = r14 + 1;
            r7 = r16;
            goto L26
        L33:
            if (r42.startsWith(r15) == false) goto L35;
        L36:
            r16 = r7;
            goto L37
        L22:
            r11 = this.PREFIXES_15;
            r12 = 15;
            r13 = "xxxx xxxx xxxx xxx";
            goto L24
        L23:
            r11 = this.PREFIXES_16;
            r12 = 16;
            r13 = "xxxx xxxx xxxx xxxx";
            goto L24
        L40:
            r16 = r7;
            goto L42
        L44:
            r16 = 1;
            goto L45
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

    /* renamed from: org.telegram.ui.PaymentFormActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements TextWatcher {
        private int actionPosition;
        private int characterAction;
        private boolean isYear;
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass8(PaymentFormActivity r1) {
            this.this$0 = r1;
            this.characterAction = -1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r13) {
            if (PaymentFormActivity.m16617$$Nest$fgetignoreOnCardChange(this.this$0) == false) goto L5;
            return;
        L5:
            boolean r0 = true;
            EditTextBoldCursor r132 = PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[1];
            int r1 = r132.getSelectionStart();
            String r2 = r132.getText().toString();
            char r4 = 3;
            if (this.characterAction != 3) goto L8;
            r2 = r2.substring(0, this.actionPosition).concat(r2.substring(this.actionPosition + 1));
            r1 = r1 - 1;
        L8:
            StringBuilder r3 = new StringBuilder(r2.length());
            int r6 = 0;
        L10:
            if (r6 >= r2.length()) goto L15;
            int r7 = r6 + 1;
            String r62 = r2.substring(r6, r7);
            if ("0123456789".contains(r62) == false) goto L14;
            r3.append(r62);
        L14:
            r6 = r7;
            goto L10
        L15:
            PaymentFormActivity.m16639$$Nest$fputignoreOnCardChange(this.this$0, true);
            PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[1].setTextColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
            if (r3.length() <= 4) goto L19;
            r3.setLength(4);
        L19:
            if (r3.length() >= 2) goto L22;
            this.isYear = false;
        L22:
            if (this.isYear == false) goto L57;
            if (r3.length() <= 2) goto L26;
            int r22 = 2;
        L27:
            String[] r9 = new String[r22];
            r9[0] = r3.substring(0, 2);
            if (r22 != 2) goto L31;
            r9[1] = r3.substring(2);
        L31:
            if (r3.length() != 4) goto L52;
            if (r22 != 2) goto L52;
            int r23 = Utilities.parseInt(r9[0]);
            int r8 = Utilities.parseInt(r9[1]) + CastStatusCodes.AUTHENTICATION_FAILED;
            Calendar r92 = Calendar.getInstance();
            if (UserConfig.getInstance(PaymentFormActivity.access$000(this.this$0)).getClientPhone().startsWith("7") == false) goto L36;
        L41:
            boolean r10 = true;
        L42:
            if (r10 == false) goto L44;
            int r11 = 2022;
        L45:
            if (r10 == false) goto L47;
            int r93 = 1;
        L48:
            if (r8 < r11) goto L51;
            if (r8 != r11) goto L62;
            if (r23 < r93) goto L51;
        L62:
            r0 = false;
        L72:
            if (r0 == true) goto L80;
            if (r3.length() != 4) goto L80;
            EditTextBoldCursor[] r02 = PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0);
            if (PaymentFormActivity.m16622$$Nest$fgetneed_card_name(this.this$0) == false) goto L78;
            r4 = 2;
        L78:
            r02[r4].requestFocus();
        L80:
            if (r3.length() != 2) goto L84;
            r3.append('/');
        L82:
            r1 = r1 + 1;
        L88:
            r132.setText(r3);
            if (r1 < 0) goto L91;
            r132.setSelection(Math.min(r1, r132.length()));
        L91:
            PaymentFormActivity.m16639$$Nest$fputignoreOnCardChange(this.this$0, false);
            return;
        L84:
            if (r3.length() <= 2) goto L88;
            if (r3.charAt(2) == '/') goto L88;
            r3.insert(2, '/');
        L51:
            PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[1].setTextColor(this.this$0.getThemedColor(Theme.key_text_RedRegular));
            goto L72
        L47:
            r93 = r92.get(2) + 1;
            goto L48
        L44:
            r11 = r92.get(1);
            goto L45
        L36:
            if (PaymentFormActivity.m16605$$Nest$fgetcountry(this.this$0) != null) goto L38;
        L40:
            r10 = false;
            goto L42
        L38:
            if (PaymentFormActivity.m16605$$Nest$fgetcountry(this.this$0).code.equals("7") == false) goto L40;
        L52:
            int r24 = Utilities.parseInt(r9[0]);
            if (r24 > 12) goto L55;
            if (r24 != 0) goto L62;
        L55:
            PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[1].setTextColor(this.this$0.getThemedColor(Theme.key_text_RedRegular));
            goto L72
        L26:
            r22 = 1;
            goto L27
        L57:
            if (r3.length() != 1) goto L64;
            int r25 = Utilities.parseInt(r3.toString());
            if (r25 == 1) goto L62;
            if (r25 == 0) goto L62;
            r3.insert(0, MVEL.VERSION_SUB);
            r1 = r1 + 1;
            goto L62
        L64:
            if (r3.length() != 2) goto L62;
            int r26 = Utilities.parseInt(r3.toString());
            if (r26 > 12) goto L70;
            if (r26 == 0) goto L70;
            r0 = false;
        L71:
            r1 = r1 + 1;
        L70:
            PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[1].setTextColor(this.this$0.getThemedColor(Theme.key_text_RedRegular));
            goto L71
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r5, int r6, int r7, int r8) {
            boolean r0 = false;
            if (r7 != 0) goto L10;
            if (r8 != 1) goto L10;
            if (TextUtils.indexOf(PaymentFormActivity.m16620$$Nest$fgetinputFields(this.this$0)[1].getText(), '/') == (-1)) goto L8;
            r0 = true;
        L8:
            this.isYear = r0;
            this.characterAction = 1;
            return;
        L10:
            if (r7 != 1) goto L19;
            if (r8 != 0) goto L19;
            if (r5.charAt(r6) != '/') goto L17;
            if (r6 <= 0) goto L17;
            this.isYear = false;
            this.characterAction = 3;
            this.actionPosition = r6 - 1;
            return;
        L17:
            this.characterAction = 2;
            return;
        L19:
            this.characterAction = -1;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PaymentFormActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends View {
        final /* synthetic */ PaymentFormActivity this$0;

        public AnonymousClass9(PaymentFormActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            if (LocaleController.isRTL == false) goto L6;
            float r0 = 0.0f;
        L5:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L10;
            int r1 = AndroidUtilities.dp(20.0f);
        L11:
            r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L10:
            r1 = 0;
            goto L11
        L6:
            r0 = AndroidUtilities.dp(20.0f);
            goto L5
        }
    }

    public class BottomFrameLayout extends FrameLayout {
        Paint paint;
        float progress;
        SpringAnimation springAnimation;
        final /* synthetic */ PaymentFormActivity this$0;

        public static /* synthetic */ void $r8$lambda$Mq1BNCcio0uRW_gQqHdZyH0ywYw(BottomFrameLayout r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
            r0.lambda$setChecked$1(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$l_gHTo93ZsebI016BBakN5Ex7Kc(BottomFrameLayout r0, DynamicAnimation r1, float r2, float r3) {
            r0.lambda$setChecked$0(r1, r2, r3);
        }

        public BottomFrameLayout(PaymentFormActivity r1, Context r2, TLRPC.PaymentForm r3) {
            this.this$0 = r1;
            super(r2);
            this.paint = new Paint(1);
            setWillNotDraw(false);
        }

        private /* synthetic */ void lambda$setChecked$0(DynamicAnimation r1, float r2, float r3) {
            this.progress = r2 / 100.0f;
            if (PaymentFormActivity.m16623$$Nest$fgetpayTextView(this.this$0) == null) goto L5;
            PaymentFormActivity.m16623$$Nest$fgetpayTextView(this.this$0).setAlpha((this.progress * 0.2f) + 0.8f);
        L5:
            invalidate();
        }

        private /* synthetic */ void lambda$setChecked$1(DynamicAnimation r1, boolean r2, float r3, float r4) {
            if (r1 != this.springAnimation) goto L6;
            this.springAnimation = null;
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            super.onDraw(r5);
            r5.drawColor(this.this$0.getThemedColor(Theme.key_switchTrackBlue));
            this.paint.setColor(this.this$0.getThemedColor(Theme.key_contacts_inviteBackground));
            if (LocaleController.isRTL == false) goto L6;
            int r0 = getWidth() - AndroidUtilities.dp(28.0f);
        L7:
            r5.drawCircle(r0, -AndroidUtilities.dp(28.0f), Math.max(getWidth(), getHeight()) * this.progress, this.paint);
            return;
        L6:
            r0 = AndroidUtilities.dp(28.0f);
            goto L7
        }

        public void setChecked(boolean r6, boolean r7) {
            SpringAnimation r0 = this.springAnimation;
            if (r0 == null) goto L6;
            r0.cancel();
        L6:
            if (r6 == false) goto L8;
            float r1 = 1.0f;
        L9:
            if (r7 == true) goto L11;
            this.progress = r1;
            if (PaymentFormActivity.m16623$$Nest$fgetpayTextView(this.this$0) == null) goto L22;
            PaymentFormActivity.m16623$$Nest$fgetpayTextView(this.this$0).setAlpha((this.progress * 0.2f) + 0.8f);
        L22:
            invalidate();
            return;
        L11:
            if (this.progress != r1) goto L13;
            return;
        L13:
            SpringAnimation r72 = new SpringAnimation(new FloatValueHolder(this.progress * 100.0f));
            SpringForce r2 = new SpringForce(r1 * 100.0f);
            if (r6 == false) goto L16;
            float r62 = 500.0f;
        L17:
            SpringAnimation r63 = r72.setSpring(r2.setStiffness(r62).setDampingRatio(1.0f));
            this.springAnimation = r63;
            r63.addUpdateListener(new PaymentFormActivity$BottomFrameLayout$$ExternalSyntheticLambda0(this));
            this.springAnimation.addEndListener(new PaymentFormActivity$BottomFrameLayout$$ExternalSyntheticLambda1(this));
            this.springAnimation.start();
            return;
        L16:
            r62 = 650.0f;
            goto L17
        L8:
            r1 = 0.0f;
            goto L9
        }
    }

    public enum InvoiceStatus extends Enum<InvoiceStatus> {
        private static final /* synthetic */ InvoiceStatus[] $VALUES = null;
        public static final InvoiceStatus CANCELLED = null;
        public static final InvoiceStatus FAILED = null;
        public static final InvoiceStatus PAID = null;
        public static final InvoiceStatus PENDING = null;

        private static /* synthetic */ InvoiceStatus[] $values() {
            return new InvoiceStatus[]{PAID, CANCELLED, PENDING, FAILED};
        }

        static {
            PAID = new InvoiceStatus("PAID", 0);
            CANCELLED = new InvoiceStatus("CANCELLED", 1);
            PENDING = new InvoiceStatus("PENDING", 2);
            FAILED = new InvoiceStatus("FAILED", 3);
            $VALUES = $values();
        }

        InvoiceStatus(String r1, int r2) {
        }

        public static InvoiceStatus valueOf(String r1) {
            return (InvoiceStatus) Enum.valueOf(InvoiceStatus.class, r1);
        }

        public static InvoiceStatus[] values() {
            return (InvoiceStatus[]) $VALUES.clone();
        }
    }

    public class LinkSpan extends ClickableSpan {
        final /* synthetic */ PaymentFormActivity this$0;

        public LinkSpan(PaymentFormActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r3) {
            PaymentFormActivity r2 = this.this$0;
            r2.presentFragment(new TwoStepVerificationSetupActivity(6, PaymentFormActivity.m16607$$Nest$fgetcurrentPassword(r2)));
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            super.updateDrawState(r1);
            r1.setUnderlineText(false);
        }
    }

    public interface PaymentFormActivityDelegate {
        default void currentPasswordUpdated(TL_account.Password r1) {
        }

        default void didSelectNewAddress(TLRPC.TL_payments_validateRequestedInfo r1) {
        }

        default boolean didSelectNewCard(String r1, String r2, boolean r3, TLRPC.TL_inputPaymentCredentialsGooglePay r4, TLRPC.TL_paymentSavedCredentialsCard r5) {
            return false;
        }

        default void onFragmentDestroyed() {
        }
    }

    public interface PaymentFormCallback {
        void onInvoiceStatusChanged(InvoiceStatus r1);
    }

    public class TelegramWebviewProxy {
        final /* synthetic */ PaymentFormActivity this$0;

        /* renamed from: $r8$lambda$7_NV29RzX-nfFl7oHHEFlngBJsI, reason: not valid java name */
        public static /* synthetic */ void m16665$r8$lambda$7_NV29RzXnfFl7oHHEFlngBJsI(TelegramWebviewProxy r0, String r1, String r2) {
            r0.lambda$postEvent$0(r1, r2);
        }

        private TelegramWebviewProxy(PaymentFormActivity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ TelegramWebviewProxy(PaymentFormActivity r1, PaymentFormActivityIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$postEvent$0(String r3, String r4) {
            if (this.this$0.getParentActivity() != null) goto L6;
            return;
        L6:
            if (r3.equals("payment_form_submit") == false) goto L15;
            JSONObject r32 = new JSONObject(r4);     // Catch: Throwable -> L9
            JSONObject r0 = r32.getJSONObject("credentials");     // Catch: Throwable -> L9
            PaymentFormActivity.m16643$$Nest$fputpaymentJson(this.this$0, r0.toString());     // Catch: Throwable -> L9
            PaymentFormActivity.m16635$$Nest$fputcardName(this.this$0, r32.getString("title"));     // Catch: Throwable -> L9
        L11:
            PaymentFormActivity.m16652$$Nest$mgoToNextStep(this.this$0);
            return;
        L9:
            th = move-exception;
            PaymentFormActivity.m16643$$Nest$fputpaymentJson(this.this$0, r4);
            FileLog.e(th);
            goto L11
        }

        @JavascriptInterface
        @Keep
        public void postEvent(String r2, String r3) {
            AndroidUtilities.runOnUIThread(new PaymentFormActivity$TelegramWebviewProxy$$ExternalSyntheticLambda0(this, r2, r3));
        }
    }

    /* renamed from: $r8$lambda$-n8ObnsWdeFAzl5V43M-Atxv73w, reason: not valid java name */
    public static /* synthetic */ void m16579$r8$lambda$n8ObnsWdeFAzl5V43MAtxv73w(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$1VftdLZyzFbjjgNBN5avbTNWmL0(PaymentFormActivity r0, TLObject r1) {
        r0.lambda$sendForm$53(r1);
    }

    /* renamed from: $r8$lambda$3-G1LCgf_w57RVwNJWe7l0yufOU, reason: not valid java name */
    public static /* synthetic */ void m16580$r8$lambda$3G1LCgf_w57RVwNJWe7l0yufOU(PaymentFormActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$loadPasswordInfo$34(r1, r2);
    }

    /* renamed from: $r8$lambda$47E0KClHybHi_atqsj-_WoQ4DdA, reason: not valid java name */
    public static /* synthetic */ void m16581$r8$lambda$47E0KClHybHi_atqsj_WoQ4DdA(PaymentFormActivity r0, Bulletin r1, boolean r2, TLRPC.Message[] r3, View r4) {
        r0.lambda$sendData$59(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$4JokoYWywPtEE8HLbzHLZz20mHs(PaymentFormActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$22(r1, r2);
    }

    /* renamed from: $r8$lambda$5VPLG1d-sp7HD8Wa5NxCy1xPsiQ, reason: not valid java name */
    public static /* synthetic */ void m16582$r8$lambda$5VPLG1dsp7HD8Wa5NxCy1xPsiQ(PaymentFormActivity r0, String r1, View r2) {
        r0.lambda$createView$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$5nvyAjxqbyNiRynvE2VTH7aufaA(PaymentFormActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$sendSavePassword$42(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$88mc2wptY_uSbH6eXINp15iZgf8(PaymentFormActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$sendData$58(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8_TJ_HnIyI5xA7QMT9GL3jvVxUQ(PaymentFormActivity r0, TLRPC.TL_error r1, TLObject r2, String r3, TL_account.getPassword r4) {
        r0.lambda$checkPassword$73(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$8ub0Ybh2v8zxYqiZelgBFpUaEJ4(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$A8uX3X2mRmzD4TLQ5BgXvTuWQf4(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$21(r1);
    }

    /* renamed from: $r8$lambda$B6tPZwyts6K-U1eZyWg3ocnYOv0, reason: not valid java name */
    public static /* synthetic */ boolean m16583$r8$lambda$B6tPZwyts6KU1eZyWg3ocnYOv0(PaymentFormActivity r0, View r1, MotionEvent r2) {
        return r0.lambda$createView$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$C3qxmrCHzS0gfzsY2zzJ5QHH8ho(long r1) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L6;
        r0.presentFragment(ChatActivity.of(r1));
        return;
    }

    public static /* synthetic */ void $r8$lambda$Cr3lrjpS800AZh8Fx60gqzeCY7s(PaymentFormActivity r0, CountrySelectActivity.Country r1) {
        r0.lambda$createView$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$F4Bikb8GRgqWg40OsUcJ8yMw344(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$20(r1);
    }

    /* renamed from: $r8$lambda$G603tuk-zOtRBJy13FsRtJkfGdA, reason: not valid java name */
    public static /* synthetic */ void m16584$r8$lambda$G603tukzOtRBJy13FsRtJkfGdA(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$I9BiOgtQ0f7PhNUVLJ2WBrurq4c(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$J0DrDFG_ByIOcpwnk9x4PtnSkl4(PaymentFormActivity r0, TextView r1, long r2, View r4) {
        r0.lambda$createView$15(r1, r2, r4);
    }

    /* renamed from: $r8$lambda$JcuAQ4fwZ6rXvkIoKESTap-ERKU, reason: not valid java name */
    public static /* synthetic */ void m16585$r8$lambda$JcuAQ4fwZ6rXvkIoKESTapERKU(PaymentFormActivity r0, Bulletin r1, boolean r2, TLRPC.Message r3, View r4) {
        r0.lambda$sendData$64(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Jox2wIxMQRbDPHDEhXoq3tBFVio(PaymentFormActivity r0, TLObject r1, TLRPC.TL_error r2, TL_account.getTmpPassword r3) {
        r0.lambda$checkPassword$70(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$LJ7taWewZmyobC2bDo5KjRl87mk(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$9(r1);
    }

    /* renamed from: $r8$lambda$LNd7Piq_e1HQLD4H8r-26M1v8Bg, reason: not valid java name */
    public static /* synthetic */ void m16586$r8$lambda$LNd7Piq_e1HQLD4H8r26M1v8Bg(PaymentFormActivity r0, TLRPC.TL_error r1) {
        r0.lambda$sendSavePassword$41(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$Lai7ru93IFq_rGOPdiYzN4MqkaA(PaymentFormActivity r0, INavigationLayout r1, Activity r2, TLRPC.Message r3) {
        return r0.lambda$sendData$66(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$MdrtzY6bufXsYZvHOsyBFcWm1N4(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$N5EBCheEeuvSVEmlqehvGd50Iaw(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$NZu8tC-Gi6yRkUUEZmVf7KVyuXU, reason: not valid java name */
    public static /* synthetic */ void m16587$r8$lambda$NZu8tCGi6yRkUUEZmVf7KVyuXU(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$OxfEZRoQkOdtQIfy7WJ9_mLP5UY(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$SHBL6KOK5prvVWnq1q6SuEDzde4(PaymentFormActivity r0, TLObject r1) {
        r0.lambda$sendData$62(r1);
    }

    public static /* synthetic */ void $r8$lambda$T_gry006pOxQ3kuWcEy47Tm1JI8(PaymentFormActivity r0, TLRPC.TL_error r1, boolean r2, TLObject r3, String r4) {
        r0.lambda$sendSavePassword$46(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Tm2gprLVvpaoWs5sonNrlU9ctP0(PaymentFormActivity r0, String r1, AlertDialog r2, int r3) {
        r0.lambda$sendSavePassword$45(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$VGB-Rr_CANqzPH5TfhoWHErZCuk, reason: not valid java name */
    public static /* synthetic */ void m16588$r8$lambda$VGBRr_CANqzPH5TfhoWHErZCuk(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$VnQCGqea5Gnhrzdim4zwTwUsbEM(PaymentFormActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$sendSavedForm$50(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$WFJ9oQIhTIQj9AUn47_VNPmd3Xo(PaymentFormActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadPasswordInfo$35(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$WVKpca5FJRb7BxJqgicrWmxHNfI(PaymentFormActivity r0) {
        r0.lambda$onTransitionAnimationEnd$38();
    }

    public static /* synthetic */ void $r8$lambda$WuBwMaT3TPfTtFhfs8FC6Mal3QU(PaymentFormActivity r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$sendSavePassword$44(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Z-VZ9CEe8DIe8empyawDg0Woe8w, reason: not valid java name */
    public static /* synthetic */ void m16589$r8$lambda$ZVZ9CEe8DIe8empyawDg0Woe8w(PaymentFormActivity r0, int r1, Intent r2) {
        r0.lambda$onActivityResultFragment$39(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZJ8iWvU0Atoc4gtD3s7p9mjG9tk(PaymentFormActivity r0, TLRPC.TL_error r1, TLObject r2, boolean r3) {
        r0.lambda$sendSavePassword$43(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$ZwxLWeRkeD62yJ8Gykbml7RGJkY(long r1) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L6;
        r0.presentFragment(ChatActivity.of(r1));
        return;
    }

    public static /* synthetic */ void $r8$lambda$_3okfJ_QcSlzkNMX4WlaQcJCYi0(PaymentFormActivity r0, TLRPC.Message[] r1) {
        r0.lambda$sendData$60(r1);
    }

    public static /* synthetic */ void $r8$lambda$aZpM07rqWUhVvQZQBp7WAWLKvgs(PaymentFormActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$sendData$63(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$apyIbfzHBBXFM4_uOGe60OIWP4s(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$29(r1);
    }

    /* renamed from: $r8$lambda$b-AwhPlFNRamlaZQ996Mbg3zCf8, reason: not valid java name */
    public static /* synthetic */ boolean m16590$r8$lambda$bAwhPlFNRamlaZQ996Mbg3zCf8(PaymentFormActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$25(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$bFME_NWAgNo3QhUz3anuQUMCjGc(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$clhnAHlXXZFdI73xM_L4Kll3auY(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$27(r1);
    }

    public static /* synthetic */ void $r8$lambda$cyLex0ax2prHtsRyCNwZ6bdh4jk(PaymentFormActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$28(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$dN1mvTitdcpNgn5Y0wMJakwA52s(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$d_V4HpOfHigP7g1TgU8SCz0Dnns(PaymentFormActivity r0, TLRPC.TL_error r1, TLRPC.TL_payments_sendPaymentForm r2) {
        r0.lambda$sendData$68(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$fgzM2hD_dT_iF2yKuddTwXjYG4w(PaymentFormActivity r0, AlertDialog r1, int r2) {
        r0.lambda$showPayAlert$36(r1, r2);
    }

    /* renamed from: $r8$lambda$hRmY-Kvxh7hNca5ChI1jb7qIyMs, reason: not valid java name */
    public static /* synthetic */ void m16591$r8$lambda$hRmYKvxh7hNca5ChI1jb7qIyMs(PaymentFormActivity r0, CountrySelectActivity.Country r1) {
        r0.lambda$createView$0(r1);
    }

    /* renamed from: $r8$lambda$i6n6m4gigVRkI-BgRvo1PRIo8Q8, reason: not valid java name */
    public static /* synthetic */ void m16592$r8$lambda$i6n6m4gigVRkIBgRvo1PRIo8Q8(PaymentFormActivity r0, TLObject r1) {
        r0.lambda$sendData$67(r1);
    }

    /* renamed from: $r8$lambda$jEU67ttzYTlmU6-fhmv81JV4pnw, reason: not valid java name */
    public static /* synthetic */ void m16593$r8$lambda$jEU67ttzYTlmU6fhmv81JV4pnw(PaymentFormActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$sendForm$54(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ksfvNJeWpbgE9PHgJ6QRS1Eekaw(PaymentFormActivity r0) {
        r0.goToNextStep();
    }

    public static /* synthetic */ void $r8$lambda$lIZL8gSeoPB8ovwMarAUTpkAoig(PaymentFormActivity r0, INavigationLayout r1, Activity r2, TLRPC.Message r3) {
        r0.lambda$sendData$65(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$lq9LWx85ffust2Y_olLL_Z-tadc, reason: not valid java name */
    public static /* synthetic */ boolean m16594$r8$lambda$lq9LWx85ffust2Y_olLL_Ztadc(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ boolean $r8$lambda$mCxyPUxEj2Az_K5txtnRdQl75WI(PaymentFormActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$2(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$m_E6UemCD2jSJbZACbnkSi7OV54(PaymentFormActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$mtR83BqOEeNRvywAqqn65S_NasI(PaymentFormActivity r0, boolean r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$sendSavePassword$47(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$nGGe2IILBWdlE8E7KvRHslcDZGI(PaymentFormActivity r0, TLObject r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$sendForm$55(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$nrP14jyiyBmj4Z21jffRLgudr4c(PaymentFormActivity r0, View r1) {
        r0.lambda$createView$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$oEE4sF270DCXotD2MFbyjcteTwI(PaymentFormActivity r0) {
        r0.lambda$loadPasswordInfo$33();
    }

    /* renamed from: $r8$lambda$phzUXWgSCUGbub-kcGe8rCQtca0, reason: not valid java name */
    public static /* synthetic */ void m16595$r8$lambda$phzUXWgSCUGbubkcGe8rCQtca0(PaymentFormActivity r0, Runnable r1, TLObject r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$sendSavedForm$51(r1, r2, r3, r4);
    }

    public static /* synthetic */ boolean $r8$lambda$qB42dO2zsLKDVVNpqFZbZuqEBmg(PaymentFormActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$7(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$qRwVeNJPG5n6D7rwP85s4F4yn9Y(PaymentFormActivity r0, View r1) {
        r0.lambda$createGooglePayButton$32(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$rEqECLjzvu5gdDSrjwrhtb6A3kY(PaymentFormActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$30(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$s8YOD6CUTY-0phCdj5SVAugjUN0, reason: not valid java name */
    public static /* synthetic */ void m16596$r8$lambda$s8YOD6CUTY0phCdj5SVAugjUN0(PaymentFormActivity r0, TLObject r1, Runnable r2) {
        r0.lambda$sendSavedForm$49(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$tUuiFqz04HejTHYR0rl9A18lFqc(PaymentFormActivity r0, TLObject r1) {
        r0.lambda$sendData$57(r1);
    }

    public static /* synthetic */ void $r8$lambda$tbvD8WJ9lc3vE4ZzSZQqEQCyNEU(PaymentFormActivity r0, String r1, TL_account.getPassword r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$checkPassword$74(r1, r2, r3, r4);
    }

    public static /* synthetic */ boolean $r8$lambda$twxww_K7r_wLtF8pcrVN4TZOQQk(TextView r0, int r1, KeyEvent r2) {
        if (r1 != 6) goto L6;
        AndroidUtilities.hideKeyboard(r0);
        return true;
    L6:
        return false;
    }

    /* renamed from: $r8$lambda$uU5v0loIvCAs-oQyjF1EBEJie6E, reason: not valid java name */
    public static /* synthetic */ boolean m16597$r8$lambda$uU5v0loIvCAsoQyjF1EBEJie6E(PaymentFormActivity r0, View r1, MotionEvent r2) {
        return r0.lambda$createView$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$uZfDTN7MY2yu_obYyN1S4RFH1ic(PaymentFormActivity r0) {
        r0.lambda$goToNextStep$40();
    }

    /* renamed from: $r8$lambda$v42I-aoxCvXwQpVbT4TNF_wL_Uo, reason: not valid java name */
    public static /* synthetic */ void m16598$r8$lambda$v42IaoxCvXwQpVbT4TNF_wL_Uo(PaymentFormActivity r0, TL_account.getTmpPassword r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$checkPassword$71(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$vnj2n34tZAK-S_0oPwIRPeWySFI, reason: not valid java name */
    public static /* synthetic */ void m16599$r8$lambda$vnj2n34tZAKS_0oPwIRPeWySFI(PaymentFormActivity r0, Runnable r1, List r2, List r3, DialogInterface r4, int r5) {
        r0.lambda$showChoosePaymentMethod$31(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$xBOhHUxu_l_d2VC5wh1uFnGz61g(PaymentFormActivity r0, TL_account.Password r1, byte[] r2) {
        r0.lambda$checkPassword$72(r1, r2);
    }

    /* renamed from: $r8$lambda$xy60vNErIFjopNQ1OiXM_YwS-BM, reason: not valid java name */
    public static /* synthetic */ void m16600$r8$lambda$xy60vNErIFjopNQ1OiXM_YwSBM(PaymentFormActivity r0, boolean r1, String r2, String r3, TL_account.updatePasswordSettings r4) {
        r0.lambda$sendSavePassword$48(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$y6Fucx86E72GVsYFlQXRrz9LVzE(PaymentFormActivity r0, Task r1) {
        r0.lambda$initGooglePay$37(r1);
    }

    public static /* synthetic */ void $r8$lambda$zehGHevpiWJL8hlVWSE6vxEROhQ(PaymentFormActivity r0, TLRPC.TL_payments_sendPaymentForm r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$sendData$69(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetcanceled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16601$$Nest$fgetcanceled(PaymentFormActivity r0) {
        return r0.canceled;
    }

    /* renamed from: -$$Nest$fgetcardName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16602$$Nest$fgetcardName(PaymentFormActivity r0) {
        return r0.cardName;
    }

    /* renamed from: -$$Nest$fgetcodesMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16603$$Nest$fgetcodesMap(PaymentFormActivity r0) {
        return r0.codesMap;
    }

    /* renamed from: -$$Nest$fgetcountriesArray, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16604$$Nest$fgetcountriesArray(PaymentFormActivity r0) {
        return r0.countriesArray;
    }

    /* renamed from: -$$Nest$fgetcountry, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CountrySelectActivity.Country m16605$$Nest$fgetcountry(PaymentFormActivity r0) {
        return r0.country;
    }

    /* renamed from: -$$Nest$fgetcurrentBotName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16606$$Nest$fgetcurrentBotName(PaymentFormActivity r0) {
        return r0.currentBotName;
    }

    /* renamed from: -$$Nest$fgetcurrentPassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_account.Password m16607$$Nest$fgetcurrentPassword(PaymentFormActivity r0) {
        return r0.currentPassword;
    }

    /* renamed from: -$$Nest$fgetcurrentStep, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16608$$Nest$fgetcurrentStep(PaymentFormActivity r0) {
        return r0.currentStep;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaymentFormActivityDelegate m16609$$Nest$fgetdelegate(PaymentFormActivity r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdetailSettingsCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextDetailSettingsCell[] m16610$$Nest$fgetdetailSettingsCell(PaymentFormActivity r0) {
        return r0.detailSettingsCell;
    }

    /* renamed from: -$$Nest$fgetdoneItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m16611$$Nest$fgetdoneItem(PaymentFormActivity r0) {
        return r0.doneItem;
    }

    /* renamed from: -$$Nest$fgetdoneItemAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m16612$$Nest$fgetdoneItemAnimation(PaymentFormActivity r0) {
        return r0.doneItemAnimation;
    }

    /* renamed from: -$$Nest$fgetdonePressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16613$$Nest$fgetdonePressed(PaymentFormActivity r0) {
        return r0.donePressed;
    }

    /* renamed from: -$$Nest$fgetemailCodeLength, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16614$$Nest$fgetemailCodeLength(PaymentFormActivity r0) {
        return r0.emailCodeLength;
    }

    /* renamed from: -$$Nest$fgetgooglePayParameters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ JSONObject m16615$$Nest$fgetgooglePayParameters(PaymentFormActivity r0) {
        return r0.googlePayParameters;
    }

    /* renamed from: -$$Nest$fgetgooglePayPublicKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16616$$Nest$fgetgooglePayPublicKey(PaymentFormActivity r0) {
        return r0.googlePayPublicKey;
    }

    /* renamed from: -$$Nest$fgetignoreOnCardChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16617$$Nest$fgetignoreOnCardChange(PaymentFormActivity r0) {
        return r0.ignoreOnCardChange;
    }

    /* renamed from: -$$Nest$fgetignoreOnPhoneChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16618$$Nest$fgetignoreOnPhoneChange(PaymentFormActivity r0) {
        return r0.ignoreOnPhoneChange;
    }

    /* renamed from: -$$Nest$fgetignoreOnTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16619$$Nest$fgetignoreOnTextChange(PaymentFormActivity r0) {
        return r0.ignoreOnTextChange;
    }

    /* renamed from: -$$Nest$fgetinputFields, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor[] m16620$$Nest$fgetinputFields(PaymentFormActivity r0) {
        return r0.inputFields;
    }

    /* renamed from: -$$Nest$fgetisWebView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16621$$Nest$fgetisWebView(PaymentFormActivity r0) {
        return r0.isWebView;
    }

    /* renamed from: -$$Nest$fgetneed_card_name, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16622$$Nest$fgetneed_card_name(PaymentFormActivity r0) {
        return r0.need_card_name;
    }

    /* renamed from: -$$Nest$fgetpayTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m16623$$Nest$fgetpayTextView(PaymentFormActivity r0) {
        return r0.payTextView;
    }

    /* renamed from: -$$Nest$fgetpaymentForm, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.PaymentForm m16624$$Nest$fgetpaymentForm(PaymentFormActivity r0) {
        return r0.paymentForm;
    }

    /* renamed from: -$$Nest$fgetphoneFormatMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16625$$Nest$fgetphoneFormatMap(PaymentFormActivity r0) {
        return r0.phoneFormatMap;
    }

    /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContextProgressView m16626$$Nest$fgetprogressView(PaymentFormActivity r0) {
        return r0.progressView;
    }

    /* renamed from: -$$Nest$fgetprogressViewButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContextProgressView m16627$$Nest$fgetprogressViewButton(PaymentFormActivity r0) {
        return r0.progressViewButton;
    }

    /* renamed from: -$$Nest$fgetproviderApiKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16628$$Nest$fgetproviderApiKey(PaymentFormActivity r0) {
        return r0.providerApiKey;
    }

    /* renamed from: -$$Nest$fgetradioCells, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadioCell[] m16629$$Nest$fgetradioCells(PaymentFormActivity r0) {
        return r0.radioCells;
    }

    /* renamed from: -$$Nest$fgetrequestedInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_payments_validatedRequestedInfo m16630$$Nest$fgetrequestedInfo(PaymentFormActivity r0) {
        return r0.requestedInfo;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m16631$$Nest$fgetresourcesProvider(PaymentFormActivity r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgettipAmount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Long m16632$$Nest$fgettipAmount(PaymentFormActivity r0) {
        return r0.tipAmount;
    }

    /* renamed from: -$$Nest$fgetvalidateRequest, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_payments_validateRequestedInfo m16633$$Nest$fgetvalidateRequest(PaymentFormActivity r0) {
        return r0.validateRequest;
    }

    /* renamed from: -$$Nest$fgetwebViewUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16634$$Nest$fgetwebViewUrl(PaymentFormActivity r0) {
        return r0.webViewUrl;
    }

    /* renamed from: -$$Nest$fputcardName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16635$$Nest$fputcardName(PaymentFormActivity r0, String r1) {
        r0.cardName = r1;
    }

    /* renamed from: -$$Nest$fputcurrentPassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16636$$Nest$fputcurrentPassword(PaymentFormActivity r0, TL_account.Password r1) {
        r0.currentPassword = r1;
    }

    /* renamed from: -$$Nest$fputdoneItemAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16637$$Nest$fputdoneItemAnimation(PaymentFormActivity r0, AnimatorSet r1) {
        r0.doneItemAnimation = r1;
    }

    /* renamed from: -$$Nest$fputgooglePayCredentials, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16638$$Nest$fputgooglePayCredentials(PaymentFormActivity r0, TLRPC.TL_inputPaymentCredentialsGooglePay r1) {
        r0.googlePayCredentials = r1;
    }

    /* renamed from: -$$Nest$fputignoreOnCardChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16639$$Nest$fputignoreOnCardChange(PaymentFormActivity r0, boolean r1) {
        r0.ignoreOnCardChange = r1;
    }

    /* renamed from: -$$Nest$fputignoreOnPhoneChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16640$$Nest$fputignoreOnPhoneChange(PaymentFormActivity r0, boolean r1) {
        r0.ignoreOnPhoneChange = r1;
    }

    /* renamed from: -$$Nest$fputignoreOnTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16641$$Nest$fputignoreOnTextChange(PaymentFormActivity r0, boolean r1) {
        r0.ignoreOnTextChange = r1;
    }

    /* renamed from: -$$Nest$fputpasswordFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16642$$Nest$fputpasswordFragment(PaymentFormActivity r0, PaymentFormActivity r1) {
        r0.passwordFragment = r1;
    }

    /* renamed from: -$$Nest$fputpaymentJson, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16643$$Nest$fputpaymentJson(PaymentFormActivity r0, String r1) {
        r0.paymentJson = r1;
    }

    /* renamed from: -$$Nest$fputsaveCardInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16644$$Nest$fputsaveCardInfo(PaymentFormActivity r0, boolean r1) {
        r0.saveCardInfo = r1;
    }

    /* renamed from: -$$Nest$fputsavedCredentialsCard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16645$$Nest$fputsavedCredentialsCard(PaymentFormActivity r0, TLRPC.TL_paymentSavedCredentialsCard r1) {
        r0.savedCredentialsCard = r1;
    }

    /* renamed from: -$$Nest$fputshippingOption, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16646$$Nest$fputshippingOption(PaymentFormActivity r0, TLRPC.TL_shippingOption r1) {
        r0.shippingOption = r1;
    }

    /* renamed from: -$$Nest$fputshouldNavigateBack, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16647$$Nest$fputshouldNavigateBack(PaymentFormActivity r0, boolean r1) {
        r0.shouldNavigateBack = r1;
    }

    /* renamed from: -$$Nest$fputtipAmount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16648$$Nest$fputtipAmount(PaymentFormActivity r0, Long r1) {
        r0.tipAmount = r1;
    }

    /* renamed from: -$$Nest$fputvalidateRequest, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16649$$Nest$fputvalidateRequest(PaymentFormActivity r0, TLRPC.TL_payments_validateRequestedInfo r1) {
        r0.validateRequest = r1;
    }

    /* renamed from: -$$Nest$fputwebviewLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16650$$Nest$fputwebviewLoading(PaymentFormActivity r0, boolean r1) {
        r0.webviewLoading = r1;
    }

    /* renamed from: -$$Nest$mcheckPassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16651$$Nest$mcheckPassword(PaymentFormActivity r0) {
        r0.checkPassword();
    }

    /* renamed from: -$$Nest$mgoToNextStep, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16652$$Nest$mgoToNextStep(PaymentFormActivity r0) {
        r0.goToNextStep();
    }

    /* renamed from: -$$Nest$msendCardData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16653$$Nest$msendCardData(PaymentFormActivity r0) {
        return r0.sendCardData();
    }

    /* renamed from: -$$Nest$msendForm, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16654$$Nest$msendForm(PaymentFormActivity r0) {
        r0.sendForm();
    }

    /* renamed from: -$$Nest$msendSavePassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16655$$Nest$msendSavePassword(PaymentFormActivity r0, boolean r1) {
        r0.sendSavePassword(r1);
    }

    /* renamed from: -$$Nest$msetAddressFields, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16656$$Nest$msetAddressFields(PaymentFormActivity r0, TLRPC.TL_paymentRequestedInfo r1) {
        r0.setAddressFields(r1);
    }

    /* renamed from: -$$Nest$msetDonePressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16657$$Nest$msetDonePressed(PaymentFormActivity r0, boolean r1) {
        r0.setDonePressed(r1);
    }

    /* renamed from: -$$Nest$mshowEditDoneProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16658$$Nest$mshowEditDoneProgress(PaymentFormActivity r0, boolean r1, boolean r2) {
        r0.showEditDoneProgress(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateSavePaymentField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16659$$Nest$mupdateSavePaymentField(PaymentFormActivity r0) {
        r0.updateSavePaymentField();
    }

    /* renamed from: -$$Nest$mupdateTotalPrice, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16660$$Nest$mupdateTotalPrice(PaymentFormActivity r0) {
        r0.updateTotalPrice();
    }

    /* renamed from: -$$Nest$sfgetBLACKLISTED_PROTOCOLS, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m16661$$Nest$sfgetBLACKLISTED_PROTOCOLS() {
        return BLACKLISTED_PROTOCOLS;
    }

    /* renamed from: -$$Nest$sfgetWEBVIEW_PROTOCOLS, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m16662$$Nest$sfgetWEBVIEW_PROTOCOLS() {
        return WEBVIEW_PROTOCOLS;
    }

    /* renamed from: -$$Nest$smgetResponseBody, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16663$$Nest$smgetResponseBody(InputStream r0) {
        return getResponseBody(r0);
    }

    static {
        WEBVIEW_PROTOCOLS = Arrays.asList(new String[]{"http", "https"});
        BLACKLISTED_PROTOCOLS = Collections.singletonList("tg");
    }

    private PaymentFormActivity(TLRPC.InputInvoice r3, TLRPC.PaymentForm r4, MessageObject r5, String r6, int r7, TLRPC.TL_payments_validatedRequestedInfo r8, TLRPC.TL_shippingOption r9, Long r10, String r11, String r12, TLRPC.TL_payments_validateRequestedInfo r13, boolean r14, TLRPC.TL_inputPaymentCredentialsGooglePay r15, BaseFragment r16, boolean r17) {
        this.countriesArray = new ArrayList();
        this.countriesMap = new HashMap();
        this.codesMap = new HashMap();
        this.phoneFormatMap = new HashMap();
        this.swipeBackEnabled = true;
        this.headerCell = new HeaderCell[3];
        this.dividers = new ArrayList();
        this.sectionCell = new ShadowSectionCell[3];
        this.bottomCell = new TextInfoPrivacyCell[3];
        this.settingsCell = new TextSettingsCell[2];
        this.detailSettingsCell = new TextDetailSettingsCell[7];
        this.shiftDp = -4.5f;
        this.emailCodeLength = 6;
        init(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17);
    }

    public PaymentFormActivity(TLRPC.PaymentForm r2, String r3, BaseFragment r4) {
        this(r2, null, r3, r4);
    }

    public PaymentFormActivity(TLRPC.PaymentForm r17, MessageObject r18, String r19, BaseFragment r20) {
        this.countriesArray = new ArrayList();
        this.countriesMap = new HashMap();
        this.codesMap = new HashMap();
        this.phoneFormatMap = new HashMap();
        this.swipeBackEnabled = true;
        this.headerCell = new HeaderCell[3];
        this.dividers = new ArrayList();
        this.sectionCell = new ShadowSectionCell[3];
        this.bottomCell = new TextInfoPrivacyCell[3];
        this.settingsCell = new TextSettingsCell[2];
        this.detailSettingsCell = new TextDetailSettingsCell[7];
        this.shiftDp = -4.5f;
        this.emailCodeLength = 6;
        this.isCheckoutPreview = true;
        init(null, r17, r18, r19, 4, null, null, null, null, null, null, false, null, r20, false);
    }

    public PaymentFormActivity(TLRPC.PaymentForm r2, MessageObject r3, BaseFragment r4) {
        this(r2, r3, null, r4);
    }

    public PaymentFormActivity(TLRPC.PaymentForm r17, TLRPC.InputInvoice r18, BaseFragment r19) {
        this(r18, r17, null, null, 4, null, null, null, null, null, null, false, null, r19, false);
        this.isCheckoutPreview = true;
    }

    public PaymentFormActivity(TLRPC.PaymentForm r17, TLRPC.InputInvoice r18, boolean r19, BaseFragment r20) {
        this(r18, r17, null, null, 4, null, null, null, null, null, null, false, null, r20, r19);
        this.isCheckoutPreview = r19;
    }

    public PaymentFormActivity(TLRPC.PaymentReceipt r5) {
        this.countriesArray = new ArrayList();
        this.countriesMap = new HashMap();
        this.codesMap = new HashMap();
        this.phoneFormatMap = new HashMap();
        this.swipeBackEnabled = true;
        this.headerCell = new HeaderCell[3];
        this.dividers = new ArrayList();
        this.sectionCell = new ShadowSectionCell[3];
        this.bottomCell = new TextInfoPrivacyCell[3];
        this.settingsCell = new TextSettingsCell[2];
        this.detailSettingsCell = new TextDetailSettingsCell[7];
        this.shiftDp = -4.5f;
        this.emailCodeLength = 6;
        this.currentStep = 5;
        TLRPC.PaymentForm r0 = new TLRPC.PaymentForm();
        this.paymentForm = r0;
        this.paymentReceipt = r5;
        r0.bot_id = r5.bot_id;
        r0.invoice = r5.invoice;
        r0.provider_id = r5.provider_id;
        r0.users = r5.users;
        this.shippingOption = r5.shipping;
        long r02 = r5.tip_amount;
        if (r02 == 0) goto L5;
        this.tipAmount = Long.valueOf(r02);
    L5:
        TLRPC.User r03 = getMessagesController().getUser(Long.valueOf(r5.bot_id));
        this.botUser = r03;
        if (r03 == null) goto L8;
        this.currentBotName = r03.first_name;
    L9:
        this.currentItemName = r5.title;
        if (r5.info == null) goto L16;
        this.validateRequest = new TLRPC.TL_payments_validateRequestedInfo();
        if (this.messageObject == null) goto L14;
        TLRPC.TL_inputInvoiceMessage r04 = new TLRPC.TL_inputInvoiceMessage();
        r04.peer = getMessagesController().getInputPeer(r5.bot_id);
        this.validateRequest.invoice = r04;
    L15:
        TLRPC.TL_payments_validateRequestedInfo r05 = this.validateRequest;
        r05.info = r5.info;
        goto L16
    L14:
        TLRPC.TL_inputInvoiceSlug r06 = new TLRPC.TL_inputInvoiceSlug();
        r06.slug = this.invoiceSlug;
        this.validateRequest.invoice = r06;
    L16:
        this.cardName = r5.credentials_title;
        return;
    L8:
        this.currentBotName = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    }

    public static /* synthetic */ int access$000(PaymentFormActivity r0) {
        return r0.currentAccount;
    }

    private void checkPassword() {
        int r1 = 0;
        if (UserConfig.getInstance(this.currentAccount).tmpPassword == null) goto L8;
        if (UserConfig.getInstance(this.currentAccount).tmpPassword.valid_until >= (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() + 60)) goto L8;
        UserConfig.getInstance(this.currentAccount).tmpPassword = null;
        UserConfig.getInstance(this.currentAccount).saveConfig(false);
    L8:
        if (UserConfig.getInstance(this.currentAccount).tmpPassword == null) goto L11;
        sendData();
        return;
    L11:
        int r0 = this.inputFields[1].length();
        EditTextBoldCursor[] r3 = this.inputFields;
        if (r0 == 0) goto L22;
        String r02 = r3[1].getText().toString();
        showEditDoneProgress(true, true);
        setDonePressed(true);
        TL_account.getPassword r2 = new TL_account.getPassword();
        ConnectionsManager r32 = ConnectionsManager.getInstance(this.currentAccount);
        PaymentFormActivity$$ExternalSyntheticLambda50 r5 = new PaymentFormActivity$$ExternalSyntheticLambda50(this, r02, r2);
        if (this.allowUnregistered == false) goto L19;
        r1 = 8;
    L19:
        r32.sendRequest(r2, r5, r1 | 2);
        return;
    L22:
        r3[1].performHapticFeedback(3, 2);     // Catch: Exception -> L21
    L14:
        AndroidUtilities.shakeViewSpring(this.inputFields[1], 2.5f);
    }

    private void createGooglePayButton(Context r8) {
        FrameLayout r0 = new FrameLayout(r8);
        this.googlePayContainer = r0;
        r0.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.googlePayContainer.setVisibility(8);
        FrameLayout r02 = new FrameLayout(r8);
        this.googlePayButton = r02;
        r02.setClickable(true);
        this.googlePayButton.setFocusable(true);
        this.googlePayButton.setBackgroundResource(R.drawable.googlepay_button_no_shadow_background);
        String r03 = this.googlePayPublicKey;
        FrameLayout r2 = this.googlePayButton;
        if (r03 != null) goto L5;
        r2.setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(2.0f));
    L6:
        this.googlePayContainer.addView(this.googlePayButton, LayoutHelper.createFrame(-1, 48.0f));
        this.googlePayButton.setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda38(this));
        LinearLayout r04 = new LinearLayout(r8);
        r04.setWeightSum(2.0f);
        r04.setGravity(16);
        r04.setOrientation(1);
        r04.setDuplicateParentStateEnabled(true);
        this.googlePayButton.addView(r04, LayoutHelper.createFrame(-1, -1.0f));
        ImageView r22 = new ImageView(r8);
        r22.setScaleType(ImageView.ScaleType.FIT_CENTER);
        r22.setDuplicateParentStateEnabled(true);
        r22.setImageResource(R.drawable.buy_with_googlepay_button_content);
        r04.addView(r22, LayoutHelper.createLinear(-1, 0, 1.0f));
        ImageView r05 = new ImageView(r8);
        r05.setScaleType(ImageView.ScaleType.FIT_XY);
        r05.setDuplicateParentStateEnabled(true);
        r05.setImageResource(R.drawable.googlepay_button_overlay);
        this.googlePayButton.addView(r05, LayoutHelper.createFrame(-1, -1.0f));
        return;
    L5:
        r2.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
        goto L6
    }

    private JSONObject getBaseCardPaymentMethod() {
        List r4 = Arrays.asList(new String[]{"AMEX", "DISCOVER", "JCB", "MASTERCARD", "VISA"});
        List r0 = Arrays.asList(new String[]{"PAN_ONLY", "CRYPTOGRAM_3DS"});
        JSONObject r1 = new JSONObject();
        r1.put("type", "CARD");
        JSONObject r2 = new JSONObject();
        r2.put("allowedAuthMethods", new JSONArray(r0));
        r2.put("allowedCardNetworks", new JSONArray(r4));
        r1.put("parameters", r2);
        return r1;
    }

    private JSONObject getBaseRequest() {
        return new JSONObject().put("apiVersion", 2).put("apiVersionMinor", 0);
    }

    private static String getResponseBody(InputStream r2) {
        String r0 = new Scanner(r2, "UTF-8").useDelimiter("\\A").next();
        r2.close();
        return r0;
    }

    private long getStars() {
        TLRPC.InputInvoice r2 = this.invoiceInput;
        if ((r2 instanceof TLRPC.TL_inputInvoiceStars) == false) goto L16;
        TLRPC.InputStorePaymentPurpose r22 = ((TLRPC.TL_inputInvoiceStars) r2).purpose;
        if ((r22 instanceof TLRPC.TL_inputStorePaymentStarsGift) == false) goto L9;
        return ((TLRPC.TL_inputStorePaymentStarsGift) r22).stars;
    L9:
        if ((r22 instanceof TLRPC.TL_inputStorePaymentStarsTopup) == false) goto L13;
        return ((TLRPC.TL_inputStorePaymentStarsTopup) r22).stars;
    L13:
        if ((r22 instanceof TLRPC.TL_inputStorePaymentStarsGiveaway) == true) goto L15;
        return 0;
    L15:
        return ((TLRPC.TL_inputStorePaymentStarsGiveaway) r22).stars;
    L16:
        return 0;
    }

    private long getStarsGiftUserId() {
        TLRPC.InputInvoice r2 = this.invoiceInput;
        if ((r2 instanceof TLRPC.TL_inputInvoiceStars) == false) goto L16;
        TLRPC.InputStorePaymentPurpose r22 = ((TLRPC.TL_inputInvoiceStars) r2).purpose;
        if ((r22 instanceof TLRPC.TL_inputStorePaymentStarsGift) == false) goto L11;
        TLRPC.InputUser r23 = ((TLRPC.TL_inputStorePaymentStarsGift) r22).user_id;
        if (r23 != null) goto L9;
        return 0;
    L9:
        return r23.user_id;
    L11:
        if ((r22 instanceof TLRPC.TL_inputStorePaymentStarsGiveaway) == false) goto L19;
        TLRPC.InputPeer r24 = ((TLRPC.TL_inputStorePaymentStarsGiveaway) r22).boost_peer;
        if (r24 != null) goto L15;
        return 0;
    L15:
        return DialogObject.getPeerDialogId(r24);
    L19:
        return 0;
    L16:
        return 0;
    }

    private String getTotalPriceDecimalString(ArrayList<TLRPC.TL_labeledPrice> r7) {
        long r0 = 0;
        int r3 = 0;
    L4:
        if (r3 >= r7.size()) goto L7;
        r0 = r0 + r7.get(r3).amount;
        r3 = r3 + 1;
        goto L4
    L7:
        return LocaleController.getInstance().formatCurrencyDecimalString(r0, this.paymentForm.invoice.currency, false);
    }

    private String getTotalPriceString(ArrayList<TLRPC.TL_labeledPrice> r6) {
        long r0 = 0;
        int r2 = 0;
    L4:
        if (r2 >= r6.size()) goto L6;
        r0 = r0 + r6.get(r2).amount;
        r2 = r2 + 1;
        goto L4
    L6:
        Long r62 = this.tipAmount;
        if (r62 == null) goto L10;
        r0 = r0 + r62.longValue();
    L10:
        return LocaleController.getInstance().formatCurrencyString(r0, this.paymentForm.invoice.currency);
    }

    private void goToNextStep() {
        int r1 = this.currentStep;
        if (r1 == 0) goto L78;
        if (r1 == 1) goto L49;
        if (r1 == 2) goto L36;
        if (r1 == 3) goto L31;
        if (r1 == 4) goto L18;
        if (r1 == 6) goto L12;
        return;
    L12:
        if (this.delegate.didSelectNewCard(this.paymentJson, this.cardName, this.saveCardInfo, this.googlePayCredentials, this.savedCredentialsCard) == true) goto L15;
        presentFragment(new PaymentFormActivity(this.invoiceInput, this.paymentForm, this.messageObject, this.invoiceSlug, 4, this.requestedInfo, this.shippingOption, this.tipAmount, this.paymentJson, this.cardName, this.validateRequest, this.saveCardInfo, this.googlePayCredentials, this.parentFragment, false).setCustomResultReceiver(this.customResultReceiver).setCustomAnyResultReceiver(this.customAnyResultReceiver), true);
        return;
    L15:
        finishFragment();
        return;
    L18:
        if (this.isCheckoutPreview == false) goto L20;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.paymentFinished);
    L20:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.paymentFinished, new Object[0]);
        if (getMessagesController().newMessageCallback == null) goto L23;
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda40(this), 500);
        return;
    L23:
        if (onCheckoutSuccess(getParentLayout(), getParentActivity()) == false) goto L25;
        return;
    L25:
        if (isFinishing() == true) goto L112;
        finishFragment();
        return;
    L112:
        return;
    L31:
        if (this.passwordOk == false) goto L33;
        int r13 = 4;
    L34:
        presentFragment(new PaymentFormActivity(this.invoiceInput, this.paymentForm, this.messageObject, this.invoiceSlug, r13, this.requestedInfo, this.shippingOption, this.tipAmount, this.paymentJson, this.cardName, this.validateRequest, this.saveCardInfo, this.googlePayCredentials, this.parentFragment, this.allowUnregistered).setCustomResultReceiver(this.customResultReceiver).setCustomAnyResultReceiver(this.customAnyResultReceiver), true);
        return;
    L33:
        r13 = 2;
        goto L34
    L36:
        TLRPC.PaymentForm r8 = this.paymentForm;
        if (r8.password_missing == false) goto L42;
        boolean r12 = this.saveCardInfo;
        if (r12 == false) goto L42;
        PaymentFormActivity r6 = new PaymentFormActivity(this.invoiceInput, r8, this.messageObject, this.invoiceSlug, 6, this.requestedInfo, this.shippingOption, this.tipAmount, this.paymentJson, this.cardName, this.validateRequest, r12, this.googlePayCredentials, this.parentFragment, this.allowUnregistered);
        this.passwordFragment = r6;
        r6.setCustomResultReceiver(this.customResultReceiver);
        this.passwordFragment.setCustomAnyResultReceiver(this.customAnyResultReceiver);
        this.passwordFragment.setCurrentPassword(this.currentPassword);
        this.passwordFragment.setDelegate(new AnonymousClass25(this));
        presentFragment(this.passwordFragment, this.isWebView);
        return;
    L42:
        PaymentFormActivityDelegate r14 = this.delegate;
        if (r14 == null) goto L46;
        r14.didSelectNewCard(this.paymentJson, this.cardName, this.saveCardInfo, this.googlePayCredentials, null);
        finishFragment();
        return;
    L46:
        presentFragment(new PaymentFormActivity(this.invoiceInput, r8, this.messageObject, this.invoiceSlug, 4, this.requestedInfo, this.shippingOption, this.tipAmount, this.paymentJson, this.cardName, this.validateRequest, this.saveCardInfo, this.googlePayCredentials, this.parentFragment, this.allowUnregistered).setCustomResultReceiver(this.customResultReceiver).setCustomAnyResultReceiver(this.customAnyResultReceiver), this.isWebView);
        return;
    L49:
        if (this.paymentJson == null) goto L51;
    L62:
        int r132 = 4;
    L65:
        if (r132 == 2) goto L67;
    L76:
        presentFragment(new PaymentFormActivity(this.invoiceInput, this.paymentForm, this.messageObject, this.invoiceSlug, r132, this.requestedInfo, this.shippingOption, this.tipAmount, this.paymentJson, this.cardName, this.validateRequest, this.saveCardInfo, this.googlePayCredentials, this.parentFragment, this.allowUnregistered).setCustomResultReceiver(this.customResultReceiver).setCustomAnyResultReceiver(this.customAnyResultReceiver), this.isWebView);
        return;
    L67:
        if (this.cardName != null) goto L76;
        if (this.savedCredentialsCard != null) goto L76;
        if (this.paymentJson != null) goto L76;
        if (this.paymentForm.additional_methods.isEmpty() == true) goto L76;
        showChoosePaymentMethod(new PaymentFormActivity$$ExternalSyntheticLambda39(this));
        return;
    L51:
        if (this.cardName != null) goto L62;
        if (this.savedCredentialsCard != null) goto L56;
        r132 = 2;
        goto L65
    L56:
        if (UserConfig.getInstance(this.currentAccount).tmpPassword == null) goto L61;
        if (UserConfig.getInstance(this.currentAccount).tmpPassword.valid_until >= (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() + 60)) goto L61;
        UserConfig.getInstance(this.currentAccount).tmpPassword = null;
        UserConfig.getInstance(this.currentAccount).saveConfig(false);
    L61:
        if (UserConfig.getInstance(this.currentAccount).tmpPassword != null) goto L62;
        r132 = 3;
        goto L65
    L78:
        PaymentFormActivityDelegate r15 = this.delegate;
        if (r15 == null) goto L83;
        r15.didSelectNewAddress(this.validateRequest);
        finishFragment();
        return;
    L83:
        if (this.paymentForm.invoice.flexible == false) goto L86;
        int r133 = 1;
    L100:
        if (r133 == 2) goto L102;
    L109:
        presentFragment(new PaymentFormActivity(this.invoiceInput, this.paymentForm, this.messageObject, this.invoiceSlug, r133, this.requestedInfo, null, null, this.paymentJson, this.cardName, this.validateRequest, this.saveCardInfo, this.googlePayCredentials, this.parentFragment, this.allowUnregistered).setCustomResultReceiver(this.customResultReceiver).setCustomAnyResultReceiver(this.customAnyResultReceiver), this.isWebView);
        return;
    L102:
        if (this.savedCredentialsCard != null) goto L109;
        if (this.paymentJson != null) goto L109;
        if (this.paymentForm.additional_methods.isEmpty() == true) goto L109;
        showChoosePaymentMethod(new PaymentFormActivity$$ExternalSyntheticLambda39(this));
        return;
    L86:
        if (this.savedCredentialsCard != null) goto L92;
        if (this.paymentJson != null) goto L92;
        r133 = 2;
    L92:
        if (UserConfig.getInstance(this.currentAccount).tmpPassword == null) goto L97;
        if (UserConfig.getInstance(this.currentAccount).tmpPassword.valid_until >= (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() + 60)) goto L97;
        UserConfig.getInstance(this.currentAccount).tmpPassword = null;
        UserConfig.getInstance(this.currentAccount).saveConfig(false);
    L97:
        if (UserConfig.getInstance(this.currentAccount).tmpPassword == null) goto L99;
        r133 = 4;
        goto L100
    L99:
        r133 = 3;
        goto L100
    }

    private void init(TLRPC.InputInvoice r4, TLRPC.PaymentForm r5, MessageObject r6, String r7, int r8, TLRPC.TL_payments_validatedRequestedInfo r9, TLRPC.TL_shippingOption r10, Long r11, String r12, String r13, TLRPC.TL_payments_validateRequestedInfo r14, boolean r15, TLRPC.TL_inputPaymentCredentialsGooglePay r16, BaseFragment r17, boolean r18) {
        if (r17 == null) goto L5;
        this.currentAccount = r17.getCurrentAccount();
    L5:
        this.currentStep = r8;
        this.parentFragment = r17;
        this.paymentJson = r12;
        this.googlePayCredentials = r16;
        this.requestedInfo = r9;
        this.paymentForm = r5;
        this.shippingOption = r10;
        this.tipAmount = r11;
        this.messageObject = r6;
        this.invoiceSlug = r7;
        this.invoiceInput = r4;
        this.saveCardInfo = r15;
        this.allowUnregistered = r18;
        if ("stripe".equals(r5.native_provider) == false) goto L8;
    L10:
        boolean r42 = false;
    L11:
        this.isWebView = r42;
        TLRPC.User r43 = getMessagesController().getUser(Long.valueOf(r5.bot_id));
        this.botUser = r43;
        if (r43 == null) goto L14;
        this.currentBotName = r43.first_name;
    L15:
        this.currentItemName = r5.title;
        this.validateRequest = r14;
        this.saveShippingInfo = true;
        if (r15 == false) goto L18;
    L21:
        this.saveCardInfo = r15;
    L22:
        if (r13 == null) goto L24;
        this.cardName = r13;
        return;
    L24:
        if (this.paymentForm.saved_credentials.isEmpty() == true) goto L29;
        TLRPC.TL_paymentSavedCredentialsCard r44 = this.paymentForm.saved_credentials.get(0);
        this.savedCredentialsCard = r44;
        this.cardName = r44.title;
        return;
    L29:
        return;
    L18:
        if (this.currentStep == 4) goto L21;
        this.saveCardInfo = !this.paymentForm.saved_credentials.isEmpty();
        goto L22
    L14:
        this.currentBotName = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L15
    L8:
        if ("smartglocal".equals(this.paymentForm.native_provider) == true) goto L10;
        r42 = true;
        goto L11
    }

    private void initGooglePay(Context r4) {
        if (getParentActivity() == null) goto L17;
        Wallet.WalletOptions.Builder r0 = new Wallet.WalletOptions.Builder();
        if (this.paymentForm.invoice.test == false) goto L8;
        int r1 = 3;
    L9:
        this.paymentsClient = Wallet.getPaymentsClient(r4, r0.setEnvironment(r1).setTheme(1).build());
        Optional<JSONObject> r42 = getIsReadyToPayRequest();
        if (r42.isPresent() == false) goto L18;
        IsReadyToPayRequest r43 = IsReadyToPayRequest.fromJson(r42.get().toString());
        if (r43 != null) goto L15;
        return;
    L15:
        this.paymentsClient.isReadyToPay(r43).addOnCompleteListener(getParentActivity(), new PaymentFormActivity$$ExternalSyntheticLambda30(this));
        return;
    L18:
        return;
    L8:
        r1 = 1;
        goto L9
    }

    private /* synthetic */ void lambda$checkPassword$70(TLObject r4, TLRPC.TL_error r5, TL_account.getTmpPassword r6) {
        showEditDoneProgress(true, false);
        setDonePressed(false);
        if (r4 == null) goto L7;
        this.passwordOk = true;
        TL_account.tmpPassword r42 = (TL_account.tmpPassword) r4;
        UserConfig.getInstance(this.currentAccount).tmpPassword = r42;
        UserConfig.getInstance(this.currentAccount).saveConfig(false);
        goToNextStep();
        return;
    L7:
        if (r5.text.equals("PASSWORD_HASH_INVALID") == true) goto L14;
        AlertsCreator.processError(this.currentAccount, r5, this, r6, new Object[0]);
        return;
    L14:
        this.inputFields[1].performHapticFeedback(3, 2);     // Catch: Exception -> L13
    L9:
        AndroidUtilities.shakeViewSpring(this.inputFields[1], 3.25f);
        this.inputFields[1].setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    private /* synthetic */ void lambda$checkPassword$71(TL_account.getTmpPassword r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda72(this, r3, r4, r2));
    }

    private /* synthetic */ void lambda$checkPassword$72(TL_account.Password r7, byte[] r8) {
        TLRPC.PasswordKdfAlgo r0 = r7.current_algo;
        if ((r0 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L5;
        byte[] r82 = SRPHelper.getX(r8, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r0);
    L6:
        TL_account.getTmpPassword r02 = new TL_account.getTmpPassword();
        r02.period = 1800;
        PaymentFormActivity$$ExternalSyntheticLambda67 r1 = new PaymentFormActivity$$ExternalSyntheticLambda67(this, r02);
        TLRPC.PasswordKdfAlgo r3 = r7.current_algo;
        if ((r3 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L14;
        TLRPC.TL_inputCheckPasswordSRP r72 = SRPHelper.startCheck(r82, r7.srp_id, r7.srp_B, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r3);
        r02.password = r72;
        if (r72 != null) goto L12;
        TLRPC.TL_error r6 = new TLRPC.TL_error();
        r6.text = "ALGO_INVALID";
        r1.run(null, r6);
        return;
    L12:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, r1, 10);
        return;
    L14:
        TLRPC.TL_error r62 = new TLRPC.TL_error();
        r62.text = "PASSWORD_HASH_INVALID";
        r1.run(null, r62);
        return;
    L5:
        r82 = null;
        goto L6
    }

    private /* synthetic */ void lambda$checkPassword$73(TLRPC.TL_error r3, TLObject r4, String r5, TL_account.getPassword r6) {
        if (r3 != null) goto L14;
        TL_account.Password r42 = (TL_account.Password) r4;
        if (TwoStepVerificationActivity.canHandleCurrentPassword(r42, false) == true) goto L9;
        AlertsCreator.showUpdateAppAlert(getParentActivity(), LocaleController.getString(R.string.UpdateAppAlert), true);
        return;
    L9:
        if (r42.has_password == true) goto L12;
        this.passwordOk = false;
        goToNextStep();
        return;
    L12:
        byte[] r32 = AndroidUtilities.getStringBytes(r5);
        Utilities.globalQueue.postRunnable(new PaymentFormActivity$$ExternalSyntheticLambda58(this, r42, r32));
        return;
    L14:
        AlertsCreator.processError(this.currentAccount, r3, this, r6, new Object[0]);
        showEditDoneProgress(true, false);
        setDonePressed(false);
    }

    private /* synthetic */ void lambda$checkPassword$74(String r7, TL_account.getPassword r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda57(this, r10, r9, r7, r8));
    }

    private /* synthetic */ void lambda$createGooglePayButton$32(View r5) {
        this.googlePayButton.setClickable(false);
        JSONObject r52 = getBaseRequest();     // Catch: JSONException -> L20
        JSONObject r0 = getBaseCardPaymentMethod();     // Catch: JSONException -> L20
        if (this.googlePayPublicKey != null) goto L7;
    L9:
        r0.put("tokenizationSpecification", new AnonymousClass24(this));     // Catch: JSONException -> L20
    L10:
        r52.put("allowedPaymentMethods", new JSONArray().put(r0));     // Catch: JSONException -> L20
        JSONObject r02 = new JSONObject();     // Catch: JSONException -> L20
        ArrayList<TLRPC.TL_labeledPrice> r1 = new ArrayList(this.paymentForm.invoice.prices);     // Catch: JSONException -> L20
        TLRPC.TL_shippingOption r2 = this.shippingOption;     // Catch: JSONException -> L20
        if (r2 == null) goto L13;
        r1.addAll(r2.prices);     // Catch: JSONException -> L20
    L13:
        String r12 = getTotalPriceDecimalString(r1);     // Catch: JSONException -> L20
        this.totalPriceDecimal = r12;     // Catch: JSONException -> L20
        r02.put("totalPrice", r12);     // Catch: JSONException -> L20
        r02.put("totalPriceStatus", "FINAL");     // Catch: JSONException -> L20
        if (TextUtils.isEmpty(this.googlePayCountryCode) == true) goto L16;
        r02.put("countryCode", this.googlePayCountryCode);     // Catch: JSONException -> L20
    L16:
        r02.put("currencyCode", this.paymentForm.invoice.currency);     // Catch: JSONException -> L20
        r02.put("checkoutOption", "COMPLETE_IMMEDIATE_PURCHASE");     // Catch: JSONException -> L20
        r52.put("transactionInfo", r02);     // Catch: JSONException -> L20
        r52.put("merchantInfo", new JSONObject().put("merchantName", this.currentBotName));     // Catch: JSONException -> L20
        PaymentDataRequest r53 = PaymentDataRequest.fromJson(r52.toString());     // Catch: JSONException -> L20
        if (r53 == null) goto L25;
        AutoResolveHelper.resolveTask(this.paymentsClient.loadPaymentData(r53), getParentActivity(), 991);     // Catch: JSONException -> L20
        return;
    L25:
        return;
    L7:
        if (this.googlePayParameters != null) goto L9;
        r0.put("tokenizationSpecification", new AnonymousClass23(this));     // Catch: JSONException -> L20
    L20:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$createView$0(CountrySelectActivity.Country r3) {
        this.country = r3;
        this.inputFields[4].setText(r3.name);
        this.countryName = r3.shortname;
    }

    private /* synthetic */ boolean lambda$createView$1(View r2, MotionEvent r3) {
        if (getParentActivity() != null) goto L6;
        return false;
    L6:
        if (r3.getAction() != 1) goto L8;
        CountrySelectActivity r22 = new CountrySelectActivity(false);
        r22.setDisableAnonymousNumbers(true);
        r22.setCountrySelectActivityDelegate(new PaymentFormActivity$$ExternalSyntheticLambda42(this));
        presentFragment(r22);
    L8:
        return true;
    }

    private /* synthetic */ boolean lambda$createView$11(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L6;
        this.doneItem.performClick();
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$createView$12(View r1) {
        this.passwordOk = false;
        goToNextStep();
    }

    private /* synthetic */ void lambda$createView$13(View r2) {
        this.inputFields[0].requestFocus();
        AndroidUtilities.showKeyboard(this.inputFields[0]);
    }

    private /* synthetic */ void lambda$createView$15(TextView r8, long r9, View r11) {
        long r0 = ((Long) r8.getTag()).longValue();
        Long r82 = this.tipAmount;
        if (r82 != null) goto L5;
    L7:
        this.inputFields[0].setText(LocaleController.getInstance().formatCurrencyString(r9, false, true, true, this.paymentForm.invoice.currency));
    L8:
        EditTextBoldCursor r7 = this.inputFields[0];
        r7.setSelection(r7.length());
        return;
    L5:
        if (r0 != r82.longValue()) goto L7;
        this.ignoreOnTextChange = true;
        this.inputFields[0].setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.ignoreOnTextChange = false;
        this.tipAmount = 0L;
        updateTotalPrice();
        goto L8
    }

    private /* synthetic */ void lambda$createView$16(View r1) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        showChoosePaymentMethod();
    }

    private /* synthetic */ void lambda$createView$17(View r18) {
        PaymentFormActivity r1 = new PaymentFormActivity(this.invoiceInput, this.paymentForm, this.messageObject, this.invoiceSlug, 0, this.requestedInfo, this.shippingOption, this.tipAmount, null, this.cardName, this.validateRequest, this.saveCardInfo, null, this.parentFragment, this.allowUnregistered);
        r1.setCustomResultReceiver(this.customResultReceiver);
        r1.setCustomAnyResultReceiver(this.customAnyResultReceiver);
        r1.setDelegate(new AnonymousClass14(this));
        presentFragment(r1);
    }

    private /* synthetic */ void lambda$createView$18(View r18) {
        PaymentFormActivity r1 = new PaymentFormActivity(this.invoiceInput, this.paymentForm, this.messageObject, this.invoiceSlug, 0, this.requestedInfo, this.shippingOption, this.tipAmount, null, this.cardName, this.validateRequest, this.saveCardInfo, null, this.parentFragment, this.allowUnregistered);
        r1.setCustomResultReceiver(this.customResultReceiver);
        r1.setCustomAnyResultReceiver(this.customAnyResultReceiver);
        r1.setDelegate(new AnonymousClass15(this));
        presentFragment(r1);
    }

    private /* synthetic */ void lambda$createView$19(View r18) {
        PaymentFormActivity r1 = new PaymentFormActivity(this.invoiceInput, this.paymentForm, this.messageObject, this.invoiceSlug, 0, this.requestedInfo, this.shippingOption, this.tipAmount, null, this.cardName, this.validateRequest, this.saveCardInfo, null, this.parentFragment, this.allowUnregistered);
        r1.setCustomResultReceiver(this.customResultReceiver);
        r1.setCustomAnyResultReceiver(this.customAnyResultReceiver);
        r1.setDelegate(new AnonymousClass16(this));
        presentFragment(r1);
    }

    private /* synthetic */ boolean lambda$createView$2(TextView r2, int r3, KeyEvent r4) {
        if (r3 != 5) goto L14;
        int r22 = ((Integer) r2.getTag()).intValue();
    L5:
        r22 = r22 + 1;
        EditTextBoldCursor[] r32 = this.inputFields;
        if (r22 >= r32.length) goto L12;
        if (r22 == 4) goto L5;
        if (((View) r32[r22].getParent()).getVisibility() != 0) goto L5;
        this.inputFields[r22].requestFocus();
    L12:
        return true;
    L14:
        if (r3 != 6) goto L17;
        this.doneItem.performClick();
        return true;
    L17:
        return false;
    }

    private /* synthetic */ void lambda$createView$20(View r18) {
        PaymentFormActivity r1 = new PaymentFormActivity(this.invoiceInput, this.paymentForm, this.messageObject, this.invoiceSlug, 0, this.requestedInfo, this.shippingOption, this.tipAmount, null, this.cardName, this.validateRequest, this.saveCardInfo, null, this.parentFragment, this.allowUnregistered);
        r1.setCustomResultReceiver(this.customResultReceiver);
        r1.setCustomAnyResultReceiver(this.customAnyResultReceiver);
        r1.setDelegate(new AnonymousClass17(this));
        presentFragment(r1);
    }

    private /* synthetic */ void lambda$createView$21(View r2) {
        setDonePressed(false);
        r2.callOnClick();
    }

    private /* synthetic */ void lambda$createView$22(AlertDialog r1, int r2) {
        showPayAlert(this.totalPrice[0]);
    }

    private /* synthetic */ void lambda$createView$23(String r23, View r24) {
        RecurrentPaymentsAcceptCell r2 = this.recurrentAcceptCell;
        if (r2 != null) goto L5;
    L9:
        boolean r22 = this.isCheckoutPreview;
        if (r22 == false) goto L18;
        if (this.paymentForm.saved_info == null) goto L18;
        if (this.validateRequest != null) goto L18;
        setDonePressed(true);
        sendSavedForm(new PaymentFormActivity$$ExternalSyntheticLambda31(this, r24));
        return;
    L18:
        if (r22 == false) goto L65;
        TLRPC.PaymentForm r25 = this.paymentForm;
        TLRPC.TL_paymentRequestedInfo r6 = r25.saved_info;
        if (r6 != null) goto L30;
        TLRPC.TL_invoice r7 = r25.invoice;
        if (r7.shipping_address_requested == false) goto L24;
    L39:
        if (r6 != null) goto L50;
        TLRPC.TL_invoice r62 = r25.invoice;
        if (r62.shipping_address_requested == false) goto L43;
    L48:
        int r11 = 0;
    L57:
        if (r11 == 2) goto L59;
    L62:
        presentFragment(new PaymentFormActivity(this.invoiceInput, this.paymentForm, this.messageObject, this.invoiceSlug, r11, this.requestedInfo, this.shippingOption, this.tipAmount, this.paymentJson, this.cardName, this.validateRequest, this.saveCardInfo, null, this.parentFragment, this.allowUnregistered).setCustomResultReceiver(this.customResultReceiver).setCustomAnyResultReceiver(this.customAnyResultReceiver));
        return;
    L59:
        if (r25.additional_methods.isEmpty() == true) goto L62;
        Objects.requireNonNull(r24);
        showChoosePaymentMethod(new EmbedBottomSheet$$ExternalSyntheticLambda6(r24));
        return;
    L43:
        if (r62.email_requested == true) goto L48;
        if (r62.name_requested == true) goto L48;
        if (r62.phone_requested == true) goto L48;
    L50:
        if (this.savedCredentialsCard == null) goto L52;
    L56:
        r11 = 1;
        goto L57
    L52:
        if (this.paymentJson != null) goto L56;
        if (this.googlePayCredentials != null) goto L56;
        r11 = 2;
        goto L57
    L24:
        if (r7.email_requested == true) goto L39;
        if (r7.name_requested == true) goto L39;
        if (r7.phone_requested == true) goto L39;
    L30:
        if (this.savedCredentialsCard != null) goto L36;
        if (this.paymentJson != null) goto L36;
        if (this.googlePayCredentials == null) goto L39;
    L36:
        if (this.shippingOption != null) goto L65;
        if (r25.invoice.flexible == true) goto L39;
    L65:
        if (this.paymentForm.password_missing == false) goto L67;
    L80:
        TLRPC.User r1 = this.botUser;
        if (r1 != null) goto L83;
    L90:
        showPayAlert(this.totalPrice[0]);
        return;
    L83:
        if (r1.verified == true) goto L90;
        String r12 = "payment_warning_" + this.botUser.id;
        SharedPreferences r26 = MessagesController.getNotificationsSettings(this.currentAccount);
        if (r26.getBoolean(r12, false) == true) goto L88;
        r26.edit().putBoolean(r12, true).apply();
        AlertDialog.Builder r13 = new AlertDialog.Builder(getParentActivity());
        r13.setTitle(LocaleController.getString(R.string.PaymentWarning));
        r13.setMessage(LocaleController.formatString("PaymentWarningText", R.string.PaymentWarningText, new Object[]{this.currentBotName, r23}));
        r13.setPositiveButton(LocaleController.getString(R.string.Continue), new PaymentFormActivity$$ExternalSyntheticLambda32(this));
        showDialog(r13.create());
        return;
    L88:
        showPayAlert(this.totalPrice[0]);
        return;
    L67:
        if (this.savedCredentialsCard == null) goto L80;
        if (UserConfig.getInstance(this.currentAccount).tmpPassword == null) goto L74;
        if (UserConfig.getInstance(this.currentAccount).tmpPassword.valid_until >= (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() + 60)) goto L74;
        UserConfig.getInstance(this.currentAccount).tmpPassword = null;
        UserConfig.getInstance(this.currentAccount).saveConfig(false);
    L74:
        if (UserConfig.getInstance(this.currentAccount).tmpPassword != null) goto L78;
        this.needPayAfterTransition = true;
        presentFragment(new PaymentFormActivity(this.invoiceInput, this.paymentForm, this.messageObject, this.invoiceSlug, 3, this.requestedInfo, this.shippingOption, this.tipAmount, null, this.cardName, this.validateRequest, this.saveCardInfo, null, this.parentFragment, this.allowUnregistered).setCustomResultReceiver(this.customResultReceiver).setCustomAnyResultReceiver(this.customAnyResultReceiver));
        this.needPayAfterTransition = false;
        return;
    L78:
        if (this.isCheckoutPreview == false) goto L80;
        this.isCheckoutPreview = false;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.paymentFinished);
        goto L80
    L5:
        if (this.recurrentAccepted == true) goto L9;
        this.shiftDp = -this.shiftDp;
        AndroidUtilities.shakeViewSpring(r2.getTextView(), this.shiftDp);
        AndroidUtilities.shakeViewSpring(this.recurrentAcceptCell.getCheckBox(), this.shiftDp);
        this.recurrentAcceptCell.performHapticFeedback(3, 2);     // Catch: Exception -> L92
        return;
    }

    private /* synthetic */ void lambda$createView$24(View r3) {
        if (this.donePressed == false) goto L5;
        return;
    L5:
        boolean r32 = !this.recurrentAccepted;
        this.recurrentAccepted = r32;
        this.recurrentAcceptCell.setChecked(r32);
        this.bottomLayout.setChecked(this.recurrentAccepted, true);
    }

    private /* synthetic */ boolean lambda$createView$25(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L6;
        sendSavePassword(false);
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$createView$27(View r4) {
        TL_account.resendPasswordEmail r42 = new TL_account.resendPasswordEmail();
        ConnectionsManager r0 = ConnectionsManager.getInstance(this.currentAccount);
        PaymentFormActivity$$ExternalSyntheticLambda41 r1 = new PaymentFormActivity$$ExternalSyntheticLambda41();
        if (this.allowUnregistered == false) goto L5;
        int r2 = 8;
    L6:
        r0.sendRequest(r42, r1, r2);
        AlertDialog.Builder r43 = new AlertDialog.Builder(getParentActivity());
        r43.setMessage(LocaleController.getString(R.string.ResendCodeInfo));
        r43.setTitle(LocaleController.getString(R.string.AppName));
        r43.setPositiveButton(LocaleController.getString(R.string.OK), null);
        showDialog(r43.create());
        return;
    L5:
        r2 = 0;
        goto L6
    }

    private /* synthetic */ void lambda$createView$28(AlertDialog r1, int r2) {
        sendSavePassword(true);
    }

    private /* synthetic */ void lambda$createView$29(View r3) {
        AlertDialog.Builder r32 = new AlertDialog.Builder(getParentActivity());
        String r0 = LocaleController.getString(R.string.TurnPasswordOffQuestion);
        if (this.currentPassword.has_secure_values == false) goto L5;
        r0 = r0 + "\n\n" + LocaleController.getString(R.string.TurnPasswordOffPassport);
    L5:
        r32.setMessage(r0);
        r32.setTitle(LocaleController.getString(R.string.TurnPasswordOffQuestionTitle));
        r32.setPositiveButton(LocaleController.getString(R.string.Disable), new PaymentFormActivity$$ExternalSyntheticLambda35(this));
        r32.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r33 = r32.create();
        showDialog(r33);
        TextView r34 = (TextView) r33.getButton(-1);
        if (r34 == null) goto L9;
        r34.setTextColor(getThemedColor(Theme.key_text_RedBold));
        return;
    }

    private /* synthetic */ void lambda$createView$3(View r1) {
        boolean r12 = !this.saveShippingInfo;
        this.saveShippingInfo = r12;
        this.checkCell1.setChecked(r12);
    }

    private /* synthetic */ boolean lambda$createView$30(TextView r2, int r3, KeyEvent r4) {
        if (r3 != 6) goto L7;
        this.doneItem.performClick();
        return true;
    L7:
        if (r3 != 5) goto L15;
        int r22 = ((Integer) r2.getTag()).intValue();
        if (r22 != 0) goto L11;
        this.inputFields[1].requestFocus();
        return false;
    L11:
        if (r22 != 1) goto L17;
        this.inputFields[2].requestFocus();
        return false;
    L17:
        return false;
    L15:
        return false;
    }

    private /* synthetic */ void lambda$createView$4(View r1) {
        boolean r12 = !this.saveCardInfo;
        this.saveCardInfo = r12;
        this.checkCell1.setChecked(r12);
    }

    private /* synthetic */ void lambda$createView$5(CountrySelectActivity.Country r2) {
        this.country = r2;
        this.inputFields[4].setText(r2.name);
    }

    private /* synthetic */ boolean lambda$createView$6(View r2, MotionEvent r3) {
        if (getParentActivity() != null) goto L6;
        return false;
    L6:
        if (r3.getAction() != 1) goto L8;
        CountrySelectActivity r22 = new CountrySelectActivity(false);
        r22.setDisableAnonymousNumbers(true);
        r22.setCountrySelectActivityDelegate(new PaymentFormActivity$$ExternalSyntheticLambda33(this));
        presentFragment(r22);
    L8:
        return true;
    }

    private /* synthetic */ boolean lambda$createView$7(TextView r3, int r4, KeyEvent r5) {
        if (r4 != 5) goto L16;
        int r32 = ((Integer) r3.getTag()).intValue();
    L5:
        int r42 = r32 + 1;
        EditTextBoldCursor[] r52 = this.inputFields;
        if (r42 >= r52.length) goto L14;
        if (r42 != 4) goto L10;
        r32 = r32 + 2;
    L12:
        if (((View) r52[r32].getParent()).getVisibility() != 0) goto L5;
        this.inputFields[r32].requestFocus();
        goto L14
    L10:
        r32 = r42;
    L14:
        return true;
    L16:
        if (r4 != 6) goto L19;
        this.doneItem.performClick();
        return true;
    L19:
        return false;
    }

    private /* synthetic */ void lambda$createView$8(View r1) {
        boolean r12 = !this.saveCardInfo;
        this.saveCardInfo = r12;
        this.checkCell1.setChecked(r12);
    }

    private /* synthetic */ void lambda$createView$9(View r6) {
        int r62 = ((Integer) r6.getTag()).intValue();
        int r1 = 0;
    L3:
        RadioCell[] r2 = this.radioCells;
        if (r1 >= r2.length) goto L10;
        RadioCell r22 = r2[r1];
        if (r62 != r1) goto L8;
        boolean r4 = true;
    L9:
        r22.setChecked(r4, true);
        r1 = r1 + 1;
        goto L3
    L8:
        r4 = false;
        goto L9
    }

    private /* synthetic */ void lambda$goToNextStep$40() {
        getMessagesController().newMessageCallback = null;
        if (this.invoiceStatus != InvoiceStatus.PENDING) goto L12;
        if (isFinishing() == true) goto L12;
        InvoiceStatus r0 = InvoiceStatus.FAILED;
        this.invoiceStatus = r0;
        PaymentFormCallback r1 = this.paymentFormCallback;
        if (r1 == null) goto L9;
        r1.onInvoiceStatusChanged(r0);
    L9:
        finishFragment();
        return;
    L12:
        if (this.invoiceStatus == InvoiceStatus.PAID) goto L14;
        return;
    L14:
        if (isFinishing() == true) goto L18;
        finishFragment();
        return;
    }

    private /* synthetic */ void lambda$initGooglePay$37(Task r2) {
        if (r2.isSuccessful() == false) goto L8;
        FrameLayout r1 = this.googlePayContainer;
        if (r1 == null) goto L10;
        r1.setVisibility(0);
        return;
    L10:
        return;
    L8:
        FileLog.e("isReadyToPay failed", r2.getException());
    }

    private /* synthetic */ void lambda$loadPasswordInfo$33() {
        if (this.shortPollRunnable != null) goto L5;
        return;
    L5:
        loadPasswordInfo();
        this.shortPollRunnable = null;
    }

    private /* synthetic */ void lambda$loadPasswordInfo$34(TLRPC.TL_error r3, TLObject r4) {
        this.loadingPasswordInfo = false;
        if (r3 != null) goto L22;
        TL_account.Password r42 = (TL_account.Password) r4;
        this.currentPassword = r42;
        if (TwoStepVerificationActivity.canHandleCurrentPassword(r42, false) == true) goto L8;
        AlertsCreator.showUpdateAppAlert(getParentActivity(), LocaleController.getString(R.string.UpdateAppAlert), true);
        return;
    L8:
        TLRPC.PaymentForm r32 = this.paymentForm;
        if (r32 != null) goto L11;
    L13:
        TwoStepVerificationActivity.initPasswordNewAlgo(this.currentPassword);
        PaymentFormActivity r33 = this.passwordFragment;
        if (r33 == null) goto L17;
        r33.setCurrentPassword(this.currentPassword);
    L17:
        if (this.currentPassword.has_password == false) goto L19;
        return;
    L19:
        if (this.shortPollRunnable != null) goto L24;
        PaymentFormActivity$$ExternalSyntheticLambda49 r34 = new PaymentFormActivity$$ExternalSyntheticLambda49(this);
        this.shortPollRunnable = r34;
        AndroidUtilities.runOnUIThread(r34, 5000);
        return;
    L24:
        return;
    L11:
        if (this.currentPassword.has_password == false) goto L13;
        r32.password_missing = false;
        r32.can_save_credentials = true;
        updateSavePaymentField();
        goto L13
    }

    private /* synthetic */ void lambda$loadPasswordInfo$35(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda34(this, r3, r2));
    }

    private /* synthetic */ void lambda$onActivityResultFragment$39(int r5, Intent r6) {
        if (r5 != (-1)) goto L25;
        PaymentData r52 = PaymentData.getFromIntent(r6);
        if (r52 == null) goto L38;
        String r53 = r52.toJson();
        if (r53 == null) goto L39;
        JSONObject r54 = new JSONObject(r53).getJSONObject("paymentMethodData");     // Catch: JSONException -> L16
        JSONObject r62 = r54.getJSONObject("tokenizationData");     // Catch: JSONException -> L16
        r62.getString("type");     // Catch: JSONException -> L16
        String r0 = r62.getString("token");     // Catch: JSONException -> L16
        if (this.googlePayPublicKey == null) goto L13;
    L18:
        TLRPC.TL_inputPaymentCredentialsGooglePay r02 = new TLRPC.TL_inputPaymentCredentialsGooglePay();     // Catch: JSONException -> L16
        this.googlePayCredentials = r02;     // Catch: JSONException -> L16
        r02.payment_token = new TLRPC.TL_dataJSON();     // Catch: JSONException -> L16
        this.googlePayCredentials.payment_token.data = r62.toString();     // Catch: JSONException -> L16
        String r55 = r54.optString(MediaTrack.ROLE_DESCRIPTION);     // Catch: JSONException -> L16
        if (TextUtils.isEmpty(r55) == true) goto L21;
        this.cardName = r55;     // Catch: JSONException -> L16
    L22:
        goToNextStep();     // Catch: JSONException -> L16
    L31:
        showEditDoneProgress(true, false);
        setDonePressed(false);
        FrameLayout r4 = this.googlePayButton;
        if (r4 == null) goto L37;
        r4.setClickable(true);
        return;
    L37:
        return;
    L21:
        this.cardName = "Android Pay";     // Catch: JSONException -> L16
        goto L22
    L13:
        if (this.googlePayParameters != null) goto L18;
        Token r56 = TokenParser.parseToken(r0);     // Catch: JSONException -> L16
        this.paymentJson = String.format(Locale.US, "{\"type\":\"%1$s\", \"id\":\"%2$s\"}", new Object[]{r56.getType(), r56.getId()});     // Catch: JSONException -> L16
        Card r57 = r56.getCard();     // Catch: JSONException -> L16
        this.cardName = r57.getBrand() + " *" + r57.getLast4();     // Catch: JSONException -> L16
    L16:
        e = move-exception;
        FileLog.e(e);
        goto L31
    L39:
        return;
    L38:
        return;
    L25:
        if (r5 != 1) goto L31;
        Status r58 = AutoResolveHelper.getStatusFromIntent(r6);
        StringBuilder r63 = new StringBuilder("android pay error ");
        if (r58 == null) goto L29;
        String r59 = r58.getStatusMessage();
    L30:
        r63.append(r59);
        FileLog.e(r63.toString());
        goto L31
    L29:
        r59 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L30
    }

    private /* synthetic */ void lambda$onTransitionAnimationEnd$38() {
        this.inputFields[0].requestFocus();
        AndroidUtilities.showKeyboard(this.inputFields[0]);
    }

    private /* synthetic */ void lambda$sendData$57(TLObject r5) {
        if ((r5 instanceof TLRPC.TL_payments_paymentReceiptStars) == false) goto L7;
        StarsIntroActivity.showTransactionSheet(getContext(), false, this.currentAccount, (TLRPC.TL_payments_paymentReceiptStars) r5, getResourceProvider());
        return;
    L7:
        if ((r5 instanceof TLRPC.PaymentReceipt) == false) goto L12;
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 == null) goto L13;
        BaseFragment.BottomSheetParams r2 = new BaseFragment.BottomSheetParams();
        r2.transitionFromLeft = true;
        r2.allowNestedScroll = false;
        r0.showAsSheet(new PaymentFormActivity((TLRPC.PaymentReceipt) r5).setCustomResultReceiver(this.customResultReceiver).setCustomAnyResultReceiver(this.customAnyResultReceiver), r2);
        return;
    L13:
        return;
    }

    private /* synthetic */ void lambda$sendData$58(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda75(this, r1));
    }

    private /* synthetic */ void lambda$sendData$59(Bulletin r3, boolean r4, TLRPC.Message[] r5, View r6) {
        r3.hide();
        if (r4 == false) goto L8;
        BaseFragment r2 = LaunchActivity.getSafeLastFragment();
        if (r2 == null) goto L10;
        r2.presentFragment(ChatActivity.of(MessageObject.getDialogId(r5[0]), r5[0].id));
        return;
    L10:
        return;
    L8:
        TLRPC.TL_payments_getPaymentReceipt r42 = new TLRPC.TL_payments_getPaymentReceipt();
        r42.msg_id = r5[0].id;
        r42.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r5[0].peer_id);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r42, new PaymentFormActivity$$ExternalSyntheticLambda70(this), 2);
    }

    private /* synthetic */ void lambda$sendData$60(TLRPC.Message[] r20) {
        Context r2 = getContext();
        if (r2 != null) goto L5;
        r2 = ApplicationLoader.applicationContext;
    L5:
        if (r2 != null) goto L7;
        r2 = LaunchActivity.instance;
    L7:
        if (r2 == null) goto L80;
        boolean r22 = true;
        this.paymentStatusSent = true;
        InvoiceStatus r3 = InvoiceStatus.PAID;
        this.invoiceStatus = r3;
        TLRPC.InputInvoice r4 = this.invoiceInput;
        boolean r5 = r4 instanceof TLRPC.TL_inputInvoiceStars;
        if (r5 == true) goto L12;
    L14:
        boolean r7 = false;
    L15:
        if (r5 == true) goto L17;
    L19:
        r22 = false;
    L20:
        if (r5 == true) goto L24;
        PaymentFormCallback r42 = this.paymentFormCallback;
        if (r42 == null) goto L24;
        r42.onInvoiceStatusChanged(r3);
    L24:
        goToNextStep();
        if (r5 == false) goto L29;
        PaymentFormCallback r32 = this.paymentFormCallback;
        if (r32 == null) goto L29;
        r32.onInvoiceStatusChanged(this.invoiceStatus);
    L29:
        long r33 = getStarsGiftUserId();
        if (r33 <= 0) goto L32;
        String r9 = UserObject.getForcedFirstName(getMessagesController().getUser(Long.valueOf(r33)));
    L37:
        long r10 = getStars();
        if (r5 == false) goto L45;
        if (r7 == true) goto L44;
        if (r22 == true) goto L44;
        int r12 = R.raw.stars_topup;
    L43:
        int r14 = r12;
        if (r5 == true) goto L49;
        String r122 = null;
    L48:
        String r15 = r122;
        if (r5 == false) goto L62;
        if (r22 == false) goto L58;
        String r52 = LocaleController.formatPluralStringComma("StarsGiveawaySentPopupInfo", (int) r10);
    L63:
        SpannableStringBuilder r16 = AndroidUtilities.replaceTags(r52);
        BaseFragment r53 = LaunchActivity.getSafeLastFragment();
        if (r53 != null) goto L66;
        return;
    L66:
        BulletinFactory r13 = BulletinFactory.of(r53);
        if (r33 == 0) goto L72;
        if (r15 == null) goto L72;
        if (r22 == true) goto L72;
        Bulletin r34 = r13.createSimpleBulletin(r14, r15, r16, LocaleController.getString(R.string.ViewInChat), new PaymentFormActivity$$ExternalSyntheticLambda68(r33));
    L75:
        r34.hideAfterBottomSheet = false;
        r34.setDuration(5000);
        if (r20[0] == null) goto L78;
        r34.setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda69(this, r34, r7, r20));
    L78:
        r34.show(r22);
        return;
    L72:
        if (r15 == null) goto L74;
        r34 = r13.createSimpleBulletin(r14, r15, r16);
        goto L75
    L74:
        r34 = r13.createSimpleBulletin(r14, r16);
        goto L75
    L58:
        if (r7 == false) goto L60;
        String r54 = "StarsGiftSentPopupInfo";
    L61:
        r52 = LocaleController.formatPluralStringComma(r54, (int) r10, new Object[]{r9});
        goto L63
    L60:
        r54 = "StarsAcquiredInfo";
        goto L61
    L62:
        r52 = LocaleController.formatString(R.string.PaymentInfoHint, new Object[]{this.totalPrice[0], this.currentItemName});
        goto L63
    L49:
        if (r22 == false) goto L52;
        int r123 = R.string.StarsGiveawaySentPopup;
    L51:
        r122 = LocaleController.getString(r123);
        goto L48
    L52:
        if (r7 == false) goto L54;
        r123 = R.string.StarsGiftSentPopup;
        goto L51
    L54:
        r123 = R.string.StarsAcquired;
    L44:
        r12 = R.raw.stars_send;
        goto L43
    L45:
        r12 = R.raw.payment_success;
        goto L43
    L32:
        r9 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r33 >= 0) goto L37;
        TLRPC.Chat r102 = getMessagesController().getChat(Long.valueOf(-r33));
        if (r102 == null) goto L37;
        r9 = r102.title;
        goto L37
    L17:
        if ((((TLRPC.TL_inputInvoiceStars) r4).purpose instanceof TLRPC.TL_inputStorePaymentStarsGiveaway) == false) goto L19;
    L12:
        if ((((TLRPC.TL_inputInvoiceStars) r4).purpose instanceof TLRPC.TL_inputStorePaymentStarsGift) == false) goto L14;
        r7 = true;
        goto L15
    }

    private /* synthetic */ void lambda$sendData$62(TLObject r5) {
        if ((r5 instanceof TLRPC.TL_payments_paymentReceiptStars) == false) goto L7;
        StarsIntroActivity.showTransactionSheet(getContext(), false, this.currentAccount, (TLRPC.TL_payments_paymentReceiptStars) r5, getResourceProvider());
        return;
    L7:
        if ((r5 instanceof TLRPC.PaymentReceipt) == false) goto L12;
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 == null) goto L13;
        BaseFragment.BottomSheetParams r2 = new BaseFragment.BottomSheetParams();
        r2.transitionFromLeft = true;
        r2.allowNestedScroll = false;
        r0.showAsSheet(new PaymentFormActivity((TLRPC.PaymentReceipt) r5).setCustomResultReceiver(this.customResultReceiver).setCustomAnyResultReceiver(this.customAnyResultReceiver), r2);
        return;
    L13:
        return;
    }

    private /* synthetic */ void lambda$sendData$63(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda1(this, r1));
    }

    private /* synthetic */ void lambda$sendData$64(Bulletin r1, boolean r2, TLRPC.Message r3, View r4) {
        r1.hide();
        if (r2 == false) goto L8;
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L10;
        r0.presentFragment(ChatActivity.of(MessageObject.getDialogId(r3), r3.id));
        return;
    L10:
        return;
    L8:
        TLRPC.TL_payments_getPaymentReceipt r12 = new TLRPC.TL_payments_getPaymentReceipt();
        r12.msg_id = r3.id;
        r12.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r3.peer_id);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, new PaymentFormActivity$$ExternalSyntheticLambda0(this), 2);
    }

    private /* synthetic */ void lambda$sendData$65(INavigationLayout r20, Activity r21, TLRPC.Message r22) {
        boolean r2 = true;
        this.paymentStatusSent = true;
        this.invoiceStatus = InvoiceStatus.PAID;
        onCheckoutSuccess(r20, r21);
        TLRPC.InputInvoice r3 = this.invoiceInput;
        boolean r4 = r3 instanceof TLRPC.TL_inputInvoiceStars;
        if (r4 == true) goto L5;
    L7:
        boolean r6 = false;
    L8:
        if (r4 == true) goto L10;
    L12:
        r2 = false;
    L13:
        if (r4 == true) goto L17;
        PaymentFormCallback r32 = this.paymentFormCallback;
        if (r32 == null) goto L17;
        r32.onInvoiceStatusChanged(this.invoiceStatus);
    L17:
        goToNextStep();
        if (r4 == false) goto L22;
        PaymentFormCallback r33 = this.paymentFormCallback;
        if (r33 == null) goto L22;
        r33.onInvoiceStatusChanged(this.invoiceStatus);
    L22:
        long r7 = getStarsGiftUserId();
        if (r7 <= 0) goto L25;
        String r9 = UserObject.getForcedFirstName(getMessagesController().getUser(Long.valueOf(r7)));
    L30:
        long r10 = getStars();
        if (r4 == false) goto L38;
        if (r6 == true) goto L37;
        if (r2 == true) goto L37;
        int r12 = R.raw.stars_topup;
    L36:
        int r14 = r12;
        if (r4 == true) goto L42;
        String r122 = null;
    L41:
        String r15 = r122;
        if (r4 == false) goto L55;
        if (r2 == false) goto L51;
        String r42 = LocaleController.formatPluralStringComma("StarsGiveawaySentPopupInfo", (int) r10);
    L56:
        SpannableStringBuilder r16 = AndroidUtilities.replaceTags(r42);
        BaseFragment r43 = LaunchActivity.getSafeLastFragment();
        if (r43 != null) goto L59;
        return;
    L59:
        BulletinFactory r13 = BulletinFactory.of(r43);
        if (r7 == 0) goto L65;
        if (r15 == null) goto L65;
        if (r2 == true) goto L65;
        Bulletin r34 = r13.createSimpleBulletin(r14, r15, r16, LocaleController.getString(R.string.ViewInChat), new PaymentFormActivity$$ExternalSyntheticLambda73(r7));
    L68:
        r34.hideAfterBottomSheet = false;
        r34.setDuration(5000);
        if (r22 == null) goto L71;
        r34.setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda74(this, r34, r6, r22));
    L71:
        r34.show(r2);
        return;
    L65:
        if (r15 == null) goto L67;
        r34 = r13.createSimpleBulletin(r14, r15, r16);
        goto L68
    L67:
        r34 = r13.createSimpleBulletin(r14, r16);
        goto L68
    L51:
        if (r6 == false) goto L53;
        String r44 = "StarsGiftSentPopupInfo";
    L54:
        r42 = LocaleController.formatPluralStringComma(r44, (int) r10, new Object[]{r9});
        goto L56
    L53:
        r44 = "StarsAcquiredInfo";
        goto L54
    L55:
        r42 = LocaleController.formatString(R.string.PaymentInfoHint, new Object[]{this.totalPrice[0], this.currentItemName});
        goto L56
    L42:
        if (r2 == false) goto L45;
        int r123 = R.string.StarsGiveawaySentPopup;
    L44:
        r122 = LocaleController.getString(r123);
        goto L41
    L45:
        if (r6 == false) goto L47;
        r123 = R.string.StarsGiftSentPopup;
        goto L44
    L47:
        r123 = R.string.StarsAcquired;
    L37:
        r12 = R.raw.stars_send;
        goto L36
    L38:
        r12 = R.raw.payment_success;
        goto L36
    L25:
        r9 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r7 >= 0) goto L30;
        TLRPC.Chat r102 = getMessagesController().getChat(Long.valueOf(-r7));
        if (r102 == null) goto L30;
        r9 = r102.title;
        goto L30
    L10:
        if ((((TLRPC.TL_inputInvoiceStars) r3).purpose instanceof TLRPC.TL_inputStorePaymentStarsGiveaway) == false) goto L12;
    L5:
        if ((((TLRPC.TL_inputInvoiceStars) r3).purpose instanceof TLRPC.TL_inputStorePaymentStarsGift) == false) goto L7;
        r6 = true;
        goto L8
    }

    private /* synthetic */ boolean lambda$sendData$66(INavigationLayout r5, Activity r6, TLRPC.Message r7) {
        if (MessageObject.getPeerId(r7.peer_id) == this.botUser.id) goto L5;
        return false;
    L5:
        if ((r7.action instanceof TLRPC.TL_messageActionPaymentSent) == false) goto L10;
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda71(this, r5, r6, r7));
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$sendData$67(TLObject r7) {
        Utilities.Callback<TLRPC.payments_PaymentResult> r0 = this.customAnyResultReceiver;
        if (r0 == null) goto L5;
        r0.run((TLRPC.TL_payments_paymentVerificationNeeded) r7);
    L5:
        setDonePressed(false);
        this.webviewLoading = true;
        showEditDoneProgress(true, true);
        ContextProgressView r2 = this.progressView;
        if (r2 == null) goto L8;
        r2.setVisibility(0);
    L8:
        ActionBarMenuItem r22 = this.doneItem;
        if (r22 == null) goto L11;
        r22.setEnabled(false);
        this.doneItem.getContentView().setVisibility(4);
    L11:
        INavigationLayout r23 = getParentLayout();
        Activity r3 = getParentActivity();
        MessagesController r4 = getMessagesController();
        r4.newMessageCallback = new PaymentFormActivity$$ExternalSyntheticLambda65(this, r23, r3);
        WebView r24 = this.webView;
        if (r24 == null) goto L14;
        r24.setVisibility(0);
        WebView r02 = this.webView;
        String r72 = ((TLRPC.TL_payments_paymentVerificationNeeded) r7).url;
        this.webViewUrl = r72;
        r02.loadUrl(r72);
    L14:
        this.paymentStatusSent = true;
        InvoiceStatus r73 = InvoiceStatus.PENDING;
        this.invoiceStatus = r73;
        PaymentFormCallback r6 = this.paymentFormCallback;
        if (r6 == null) goto L18;
        r6.onInvoiceStatusChanged(r73);
        return;
    }

    private /* synthetic */ void lambda$sendData$68(TLRPC.TL_error r4, TLRPC.TL_payments_sendPaymentForm r5) {
        AlertsCreator.processError(this.currentAccount, r4, this, r5, new Object[0]);
        setDonePressed(false);
        showEditDoneProgress(false, false);
        this.paymentStatusSent = true;
        InvoiceStatus r42 = InvoiceStatus.FAILED;
        this.invoiceStatus = r42;
        PaymentFormCallback r3 = this.paymentFormCallback;
        if (r3 == null) goto L6;
        r3.onInvoiceStatusChanged(r42);
        return;
    }

    private /* synthetic */ void lambda$sendData$69(TLRPC.TL_payments_sendPaymentForm r5, TLObject r6, TLRPC.TL_error r7) {
        if (r6 != null) goto L4;
        Utilities.CallbackReturn<TLRPC.TL_error, Boolean> r62 = this.customErrorReceiver;
        if (r62 != null) goto L27;
    L29:
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda61(this, r7, r5));
        return;
    L27:
        if (r62.run(r7).booleanValue() == false) goto L29;
        return;
    L4:
        if ((r6 instanceof TLRPC.TL_payments_paymentResult) == false) goto L21;
        Utilities.Callback<TLRPC.TL_payments_paymentResult> r52 = this.customResultReceiver;
        if (r52 == null) goto L9;
        r52.run((TLRPC.TL_payments_paymentResult) r6);
        return;
    L9:
        TLRPC.Updates r53 = ((TLRPC.TL_payments_paymentResult) r6).updates;
        TLRPC.Message[] r63 = new TLRPC.Message[1];
        int r72 = r53.updates.size();
        int r1 = 0;
    L10:
        if (r1 >= r72) goto L18;
        TLRPC.Update r2 = r53.updates.get(r1);
        if ((r2 instanceof TL_update.TL_updateNewMessage) == true) goto L13;
        if ((r2 instanceof TL_update.TL_updateNewChannelMessage) == true) goto L16;
        r1 = r1 + 1;
        goto L10
    L16:
        r63[0] = ((TL_update.TL_updateNewChannelMessage) r2).message;
        goto L18
    L13:
        r63[0] = ((TL_update.TL_updateNewMessage) r2).message;
    L18:
        getMessagesController().processUpdates(r53, false);
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda59(this, r63));
        return;
    L21:
        if ((r6 instanceof TLRPC.TL_payments_paymentVerificationNeeded) == false) goto L28;
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda60(this, r6));
        return;
    }

    private /* synthetic */ void lambda$sendForm$53(TLObject r6) {
        this.requestedInfo = (TLRPC.TL_payments_validatedRequestedInfo) r6;
        if (this.paymentForm.saved_info != null) goto L5;
    L11:
        goToNextStep();
        setDonePressed(false);
        showEditDoneProgress(true, false);
        return;
    L5:
        if (this.saveShippingInfo == true) goto L11;
        TLRPC.TL_payments_clearSavedInfo r62 = new TLRPC.TL_payments_clearSavedInfo();
        r62.info = true;
        ConnectionsManager r2 = ConnectionsManager.getInstance(this.currentAccount);
        PaymentFormActivity$$ExternalSyntheticLambda62 r3 = new PaymentFormActivity$$ExternalSyntheticLambda62();
        if (this.allowUnregistered == false) goto L9;
        int r4 = 8;
    L10:
        r2.sendRequest(r62, r3, r4);
        goto L11
    L9:
        r4 = 0;
        goto L10
    }

    private /* synthetic */ void lambda$sendForm$54(TLRPC.TL_error r12, TLObject r13) {
        setDonePressed(false);
        showEditDoneProgress(true, false);
        if (r12 == null) goto L64;
        String r2 = r12.text;
        r2.getClass();
        char r10 = 65535;
        switch(r2.hashCode()) {
            case -2092780146: goto L40;
            case -1623547228: goto L36;
            case -1224177757: goto L32;
            case -1031752045: goto L28;
            case -274035920: goto L24;
            case 417441502: goto L20;
            case 708423542: goto L16;
            case 863965605: goto L12;
            case 889106340: goto L8;
            default: goto L43;
        };
    L43:
        switch(r10) {
            case 0: goto L62;
            case 1: goto L60;
            case 2: goto L58;
            case 3: goto L56;
            case 4: goto L54;
            case 5: goto L52;
            case 6: goto L50;
            case 7: goto L48;
            case 8: goto L46;
            default: goto L44;
        };
    L44:
        AlertsCreator.processError(this.currentAccount, r12, this, r13, new Object[0]);
        return;
    L46:
        shakeField(7);
        return;
    L48:
        shakeField(1);
        return;
    L50:
        shakeField(9);
        return;
    L52:
        shakeField(3);
        return;
    L54:
        shakeField(5);
        return;
    L56:
        shakeField(6);
        return;
    L58:
        shakeField(4);
        return;
    L60:
        shakeField(0);
        return;
    L62:
        shakeField(2);
        return;
    L8:
        if (r2.equals("REQ_INFO_EMAIL_INVALID") == false) goto L43;
        r10 = '\b';
        goto L43
    L12:
        if (r2.equals("ADDRESS_STREET_LINE2_INVALID") == false) goto L43;
        r10 = 7;
        goto L43
    L16:
        if (r2.equals("REQ_INFO_PHONE_INVALID") == false) goto L43;
        r10 = 6;
        goto L43
    L20:
        if (r2.equals("ADDRESS_STATE_INVALID") == false) goto L43;
        r10 = 5;
        goto L43
    L24:
        if (r2.equals("ADDRESS_POSTCODE_INVALID") == false) goto L43;
        r10 = 4;
        goto L43
    L28:
        if (r2.equals("REQ_INFO_NAME_INVALID") == false) goto L43;
        r10 = 3;
        goto L43
    L32:
        if (r2.equals("ADDRESS_COUNTRY_INVALID") == false) goto L43;
        r10 = 2;
        goto L43
    L36:
        if (r2.equals("ADDRESS_STREET_LINE1_INVALID") == false) goto L43;
        r10 = 1;
        goto L43
    L40:
        if (r2.equals("ADDRESS_CITY_INVALID") == false) goto L43;
        r10 = 0;
        goto L43
    }

    private /* synthetic */ void lambda$sendForm$55(TLObject r2, TLObject r3, TLRPC.TL_error r4) {
        if ((r3 instanceof TLRPC.TL_payments_validatedRequestedInfo) == false) goto L6;
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda52(this, r3));
        return;
    L6:
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda53(this, r4, r2));
    }

    private /* synthetic */ void lambda$sendSavePassword$41(TLRPC.TL_error r4) {
        showEditDoneProgress(true, false);
        if (r4 != null) goto L13;
        if (getParentActivity() != null) goto L7;
        return;
    L7:
        Runnable r42 = this.shortPollRunnable;
        if (r42 == null) goto L10;
        AndroidUtilities.cancelRunOnUIThread(r42);
        this.shortPollRunnable = null;
    L10:
        goToNextStep();
        return;
    L13:
        if (r4.text.startsWith("CODE_INVALID") == false) goto L17;
        shakeView(this.codeFieldCell);
        this.codeFieldCell.setText(_UrlKt.FRAGMENT_ENCODE_SET, false);
        return;
    L17:
        if (r4.text.startsWith("FLOOD_WAIT") == false) goto L24;
        int r43 = Utilities.parseInt(r4.text);
        if (r43 >= 60) goto L21;
        String r44 = LocaleController.formatPluralString("Seconds", r43, new Object[0]);
    L22:
        showAlertWithText(LocaleController.getString(R.string.AppName), LocaleController.formatString("FloodWaitTime", R.string.FloodWaitTime, new Object[]{r44}));
        return;
    L21:
        r44 = LocaleController.formatPluralString("Minutes", r43 / 60, new Object[0]);
        goto L22
    L24:
        showAlertWithText(LocaleController.getString(R.string.AppName), r4.text);
    }

    private /* synthetic */ void lambda$sendSavePassword$42(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda45(this, r2));
    }

    private /* synthetic */ void lambda$sendSavePassword$43(TLRPC.TL_error r1, TLObject r2, boolean r3) {
        if (r1 != null) goto L5;
        TL_account.Password r22 = (TL_account.Password) r2;
        this.currentPassword = r22;
        TwoStepVerificationActivity.initPasswordNewAlgo(r22);
        sendSavePassword(r3);
        return;
    }

    private /* synthetic */ void lambda$sendSavePassword$44(boolean r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda66(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$sendSavePassword$45(String r1, AlertDialog r2, int r3) {
        this.waitingForEmail = true;
        this.currentPassword.email_unconfirmed_pattern = r1;
        updatePasswordFields();
    }

    private /* synthetic */ void lambda$sendSavePassword$46(TLRPC.TL_error r3, boolean r4, TLObject r5, String r6) {
        if (r3 != null) goto L4;
    L7:
        showEditDoneProgress(true, false);
        if (r4 == false) goto L11;
        TL_account.Password r32 = this.currentPassword;
        r32.has_password = false;
        r32.current_algo = null;
        this.delegate.currentPasswordUpdated(r32);
        finishFragment();
        return;
    L11:
        if (r3 == null) goto L13;
    L19:
        if (r3 != null) goto L21;
        return;
    L21:
        if (r3.text.equals("EMAIL_UNCONFIRMED") == false) goto L23;
    L39:
        this.emailCodeLength = Utilities.parseInt(r3.text);
        AlertDialog.Builder r33 = new AlertDialog.Builder(getParentActivity());
        r33.setPositiveButton(LocaleController.getString(R.string.OK), new PaymentFormActivity$$ExternalSyntheticLambda64(this, r6));
        r33.setMessage(LocaleController.getString(R.string.YourEmailAlmostThereText));
        r33.setTitle(LocaleController.getString(R.string.YourEmailAlmostThere));
        Dialog r2 = showDialog(r33.create());
        if (r2 == null) goto L44;
        r2.setCanceledOnTouchOutside(false);
        r2.setCancelable(false);
        return;
    L44:
        return;
    L23:
        if (r3.text.startsWith("EMAIL_UNCONFIRMED_") == true) goto L39;
        if (r3.text.equals("EMAIL_INVALID") == false) goto L30;
        showAlertWithText(LocaleController.getString(R.string.AppName), LocaleController.getString(R.string.PasswordEmailInvalid));
        return;
    L30:
        if (r3.text.startsWith("FLOOD_WAIT") == false) goto L37;
        int r34 = Utilities.parseInt(r3.text);
        if (r34 >= 60) goto L34;
        String r35 = LocaleController.formatPluralString("Seconds", r34, new Object[0]);
    L35:
        showAlertWithText(LocaleController.getString(R.string.AppName), LocaleController.formatString("FloodWaitTime", R.string.FloodWaitTime, new Object[]{r35}));
        return;
    L34:
        r35 = LocaleController.formatPluralString("Minutes", r34 / 60, new Object[0]);
        goto L35
    L37:
        showAlertWithText(LocaleController.getString(R.string.AppName), r3.text);
        return;
    L13:
        if ((r5 instanceof TLRPC.TL_boolTrue) == false) goto L19;
        if (getParentActivity() == null) goto L45;
        goToNextStep();
        return;
    L45:
        return;
    L4:
        if ("SRP_ID_INVALID".equals(r3.text) == false) goto L7;
        TL_account.getPassword r36 = new TL_account.getPassword();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r36, new PaymentFormActivity$$ExternalSyntheticLambda63(this, r4), 8);
    }

    private /* synthetic */ void lambda$sendSavePassword$47(boolean r7, String r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda51(this, r10, r7, r9, r8));
    }

    private /* synthetic */ void lambda$sendSavePassword$48(boolean r4, String r5, String r6, TL_account.updatePasswordSettings r7) {
        PaymentFormActivity$$ExternalSyntheticLambda44 r0 = new PaymentFormActivity$$ExternalSyntheticLambda44(this, r4, r5);
        if (r4 == true) goto L13;
        byte[] r42 = AndroidUtilities.getStringBytes(r6);
        TLRPC.PasswordKdfAlgo r62 = this.currentPassword.new_algo;
        if ((r62 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L11;
        TL_account.passwordInputSettings r1 = r7.new_settings;
        r1.new_password_hash = SRPHelper.getVBytes(r42, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r62);
        if (r7.new_settings.new_password_hash != null) goto L9;
        TLRPC.TL_error r43 = new TLRPC.TL_error();
        r43.text = "ALGO_INVALID";
        r0.run(null, r43);
    L9:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r7, r0, 10);
        return;
    L11:
        TLRPC.TL_error r3 = new TLRPC.TL_error();
        r3.text = "PASSWORD_HASH_INVALID";
        r0.run(null, r3);
        return;
    L13:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r7, r0, 10);
    }

    private /* synthetic */ void lambda$sendSavedForm$49(TLObject r1, Runnable r2) {
        this.requestedInfo = (TLRPC.TL_payments_validatedRequestedInfo) r1;
        r2.run();
        setDonePressed(false);
        showEditDoneProgress(true, false);
    }

    private /* synthetic */ void lambda$sendSavedForm$50(TLRPC.TL_error r3, TLObject r4) {
        setDonePressed(false);
        showEditDoneProgress(true, false);
        if (r3 == null) goto L6;
        AlertsCreator.processError(this.currentAccount, r3, this, r4, new Object[0]);
        return;
    }

    private /* synthetic */ void lambda$sendSavedForm$51(Runnable r2, TLObject r3, TLObject r4, TLRPC.TL_error r5) {
        if ((r4 instanceof TLRPC.TL_payments_validatedRequestedInfo) == false) goto L6;
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda54(this, r4, r2));
        return;
    L6:
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda55(this, r5, r3));
    }

    private /* synthetic */ void lambda$showChoosePaymentMethod$31(Runnable r21, List r22, List r23, DialogInterface r24, int r25) {
        AnonymousClass22 r2 = new AnonymousClass22(this, r21);
        TLRPC.TL_paymentSavedCredentialsCard r3 = this.savedCredentialsCard;
        if (r3 == null) goto L5;
    L8:
        int r5 = 1;
    L9:
        if (r3 == null) goto L11;
    L12:
        if (r25 == 0) goto L28;
    L14:
        if (r25 < r5) goto L20;
        if (r25 >= (r22.size() + r5)) goto L20;
        TLRPC.TL_paymentSavedCredentialsCard r1 = (TLRPC.TL_paymentSavedCredentialsCard) r22.get(r25 - r5);
        this.savedCredentialsCard = r1;
        r2.didSelectNewCard(null, r1.title, true, null, r1);
        return;
    L20:
        if (r25 >= (r23.size() - 1)) goto L24;
        TLRPC.TL_paymentFormMethod r12 = this.paymentForm.additional_methods.get((r25 - r22.size()) - r5);
        PaymentFormActivity r32 = new PaymentFormActivity(this.invoiceInput, this.paymentForm, this.messageObject, this.invoiceSlug, 2, this.requestedInfo, this.shippingOption, this.tipAmount, null, this.cardName, this.validateRequest, this.saveCardInfo, null, this.parentFragment, this.allowUnregistered);
        r32.setCustomResultReceiver(this.customResultReceiver);
        r32.setCustomAnyResultReceiver(this.customAnyResultReceiver);
        r32.setPaymentMethod(r12);
        r32.setDelegate(r2);
        presentFragment(r32);
        return;
    L24:
        if (r25 != (r23.size() - 1)) goto L27;
        PaymentFormActivity r4 = new PaymentFormActivity(this.invoiceInput, this.paymentForm, this.messageObject, this.invoiceSlug, 2, this.requestedInfo, this.shippingOption, this.tipAmount, null, this.cardName, this.validateRequest, this.saveCardInfo, null, this.parentFragment, this.allowUnregistered);
        r4.setCustomResultReceiver(this.customResultReceiver);
        r4.setCustomAnyResultReceiver(this.customAnyResultReceiver);
        r4.setDelegate(r2);
        presentFragment(r4);
        return;
    L27:
        return;
    L28:
        return;
    L11:
        if (this.cardName == null) goto L14;
    L5:
        if (this.cardName != null) goto L8;
        r5 = 0;
        goto L9
    }

    private /* synthetic */ void lambda$showPayAlert$36(AlertDialog r1, int r2) {
        setDonePressed(true);
        sendData();
    }

    private void loadPasswordInfo() {
        if (this.loadingPasswordInfo == false) goto L5;
        return;
    L5:
        this.loadingPasswordInfo = true;
        TL_account.getPassword r0 = new TL_account.getPassword();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new PaymentFormActivity$$ExternalSyntheticLambda4(this), 10);
    }

    private boolean onCheckoutSuccess(INavigationLayout r7, Activity r8) {
        int r1 = 0;
        if (this.invoiceInput == null) goto L12;
        if (r7 == null) goto L11;
        ArrayList r6 = new ArrayList(r7.getFragmentStack());
        int r72 = r6.size();
    L6:
        if (r1 >= r72) goto L10;
        Object r82 = r6.get(r1);
        r1 = r1 + 1;
        BaseFragment r83 = (BaseFragment) r82;
        if ((r83 instanceof PaymentFormActivity) == false) goto L6;
        r83.removeSelfFromStack();
        goto L6
    L10:
        return true;
    L11:
        return false;
    L12:
        String r0 = this.botUser.username;
        if (r0 == null) goto L19;
        if (r0.equalsIgnoreCase(getMessagesController().premiumBotUsername) == false) goto L19;
        if (this.invoiceSlug != null) goto L19;
    L24:
        if (r7 == null) goto L37;
        ArrayList r02 = new ArrayList(r7.getFragmentStack());
        int r3 = r02.size();
    L26:
        if (r1 >= r3) goto L32;
        Object r4 = r02.get(r1);
        r1 = r1 + 1;
        BaseFragment r42 = (BaseFragment) r4;
        if ((r42 instanceof ChatActivity) == true) goto L31;
        if ((r42 instanceof PremiumPreviewFragment) == false) goto L26;
    L31:
        r42.removeSelfFromStack();
        goto L26
    L32:
        r7.presentFragment(new PremiumPreviewFragment(null).setForcePremium(), !isFinishing());
        if ((r8 instanceof LaunchActivity) == true) goto L39;
    L36:
        return true;
    L39:
        this.fragmentView.performHapticFeedback(3, 2);     // Catch: Exception -> L38
    L35:
        ((LaunchActivity) r8).getFireworksOverlay().start();
    L37:
        return false;
    L19:
        if (this.invoiceSlug == null) goto L37;
        if (getMessagesController().premiumInvoiceSlug == null) goto L37;
        if (Objects.equals(this.invoiceSlug, getMessagesController().premiumInvoiceSlug) == false) goto L37;
        goto L24
    }

    private void onPresentFragment(BaseFragment r2) {
        AndroidUtilities.hideKeyboard(this.fragmentView);
        if ((r2 instanceof PaymentFormActivity) == false) goto L6;
        PaymentFormActivity r22 = (PaymentFormActivity) r2;
        r22.paymentFormCallback = this.paymentFormCallback;
        r22.resourcesProvider = this.resourcesProvider;
        r22.needPayAfterTransition = this.needPayAfterTransition;
        r22.savedCredentialsCard = this.savedCredentialsCard;
        return;
    }

    private boolean sendCardData() {
        String[] r1 = this.inputFields[1].getText().toString().split("/");
        if (r1.length != 2) goto L5;
        Integer r3 = Integer.valueOf(Utilities.parseInt(r1[0]));
        Integer r10 = Integer.valueOf(Utilities.parseInt(r1[1]));
        Integer r9 = r3;
    L6:
        Card r7 = new Card(this.inputFields[0].getText().toString(), r9, r10, this.inputFields[3].getText().toString(), this.inputFields[2].getText().toString(), null, null, null, null, this.inputFields[5].getText().toString(), this.inputFields[4].getText().toString(), null);
        this.cardName = r7.getBrand() + " *" + r7.getLast4();
        if (r9 == null) goto L20;
        if (r10 == null) goto L20;
        if (UserConfig.getInstance(this.currentAccount).getClientPhone().startsWith("7") == false) goto L20;
        if ("smartglocal".equals(this.paymentForm.native_provider) == false) goto L20;
        if (r10.intValue() <= 22) goto L16;
    L19:
        boolean r92 = true;
    L22:
        if (r7.validateNumber() == true) goto L25;
        shakeField(0);
        return false;
    L25:
        if (r92 == true) goto L35;
        if (r7.validateExpMonth() == true) goto L29;
    L32:
        shakeField(1);
        return false;
    L29:
        if (r7.validateExpYear() == false) goto L32;
        if (r7.validateExpiryDate() == false) goto L32;
    L35:
        if (this.need_card_name == false) goto L41;
        if (this.inputFields[2].length() != 0) goto L41;
        shakeField(2);
        return false;
    L41:
        if (r7.validateCVC() == true) goto L45;
        shakeField(3);
        return false;
    L45:
        if (this.need_card_country == false) goto L51;
        if (this.inputFields[4].length() != 0) goto L51;
        shakeField(4);
        return false;
    L51:
        if (this.need_card_postcode == true) goto L53;
    L56:
        showEditDoneProgress(true, true);
    L60:
        e = move-exception;
        FileLog.e(e);
    L67:
        return true;
    L58:
        if ("stripe".equals(this.paymentForm.native_provider) == false) goto L63;
        new Stripe(this.providerApiKey).createToken(r7, new AnonymousClass26(this));     // Catch: Exception -> L60
    L68:
        goto L67
    L63:
        if ("smartglocal".equals(this.paymentForm.native_provider) == false) goto L68;
        new AnonymousClass27(this, r7).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[]{null, null, null});     // Catch: Exception -> L60
        goto L68
    L53:
        if (this.inputFields[5].length() != 0) goto L56;
        shakeField(5);
        return false;
    L16:
        if (r10.intValue() != 22) goto L20;
        if (r9.intValue() > 1) goto L19;
    L20:
        r92 = false;
        goto L22
    L5:
        r9 = null;
        r10 = null;
        goto L6
    }

    private void sendData() {
        if (this.canceled == false) goto L5;
        return;
    L5:
        int r0 = 0;
        showEditDoneProgress(false, true);
        TLRPC.TL_payments_sendPaymentForm r2 = new TLRPC.TL_payments_sendPaymentForm();
        TLRPC.InputInvoice r3 = this.invoiceInput;
        if (r3 == null) goto L9;
        r2.invoice = r3;
    L12:
        r2.form_id = this.paymentForm.form_id;
        if (UserConfig.getInstance(this.currentAccount).tmpPassword != null) goto L15;
    L17:
        TLRPC.TL_inputPaymentCredentialsGooglePay r32 = this.googlePayCredentials;
        if (r32 == null) goto L20;
        r2.credentials = r32;
    L21:
        TLRPC.TL_payments_validatedRequestedInfo r33 = this.requestedInfo;
        if (r33 == null) goto L26;
        String r34 = r33.id;
        if (r34 == null) goto L26;
        r2.requested_info_id = r34;
        r2.flags = 1 | r2.flags;
    L26:
        TLRPC.TL_shippingOption r1 = this.shippingOption;
        if (r1 == null) goto L30;
        r2.shipping_option_id = r1.id;
        r2.flags |= 2;
    L30:
        if ((this.paymentForm.invoice.flags & 256) == 0) goto L36;
        Long r12 = this.tipAmount;
        if (r12 == null) goto L34;
        long r35 = r12.longValue();
    L35:
        r2.tip_amount = r35;
        r2.flags |= 4;
        goto L36
    L34:
        r35 = 0;
    L36:
        ConnectionsManager r13 = ConnectionsManager.getInstance(this.currentAccount);
        PaymentFormActivity$$ExternalSyntheticLambda56 r36 = new PaymentFormActivity$$ExternalSyntheticLambda56(this, r2);
        if (this.allowUnregistered == false) goto L39;
        r0 = 8;
    L39:
        r13.sendRequest(r2, r36, r0 | 2);
        return;
    L20:
        TLRPC.TL_inputPaymentCredentials r37 = new TLRPC.TL_inputPaymentCredentials();
        r2.credentials = r37;
        r37.save = this.saveCardInfo;
        r37.data = new TLRPC.TL_dataJSON();
        r2.credentials.data.data = this.paymentJson;
        goto L21
    L15:
        if (this.savedCredentialsCard == null) goto L17;
        TLRPC.TL_inputPaymentCredentialsSaved r38 = new TLRPC.TL_inputPaymentCredentialsSaved();
        r2.credentials = r38;
        r38.id = this.savedCredentialsCard.id;
        r38.tmp_password = UserConfig.getInstance(this.currentAccount).tmpPassword.tmp_password;
        goto L21
    L9:
        if (this.messageObject == null) goto L11;
        TLRPC.TL_inputInvoiceMessage r39 = new TLRPC.TL_inputInvoiceMessage();
        r39.peer = getMessagesController().getInputPeer(this.messageObject.messageOwner.peer_id);
        r39.msg_id = this.messageObject.getId();
        r2.invoice = r39;
        goto L12
    L11:
        TLRPC.TL_inputInvoiceSlug r310 = new TLRPC.TL_inputInvoiceSlug();
        r310.slug = this.invoiceSlug;
        r2.invoice = r310;
        goto L12
    }

    private void sendForm() {
        if (this.canceled == false) goto L5;
        return;
    L5:
        showEditDoneProgress(true, true);
        TLRPC.TL_payments_validateRequestedInfo r1 = new TLRPC.TL_payments_validateRequestedInfo();
        this.validateRequest = r1;
        TLRPC.InputInvoice r2 = this.invoiceInput;
        if (r2 == null) goto L9;
        r1.invoice = r2;
    L12:
        TLRPC.TL_payments_validateRequestedInfo r12 = this.validateRequest;
        r12.save = this.saveShippingInfo;
        r12.info = new TLRPC.TL_paymentRequestedInfo();
        if (this.paymentForm.invoice.name_requested == false) goto L15;
        this.validateRequest.info.name = this.inputFields[6].getText().toString();
        this.validateRequest.info.flags |= 1;
    L15:
        int r3 = 8;
        if (this.paymentForm.invoice.phone_requested == false) goto L19;
        this.validateRequest.info.phone = "+" + this.inputFields[8].getText().toString() + this.inputFields[9].getText().toString();
        TLRPC.TL_paymentRequestedInfo r13 = this.validateRequest.info;
        r13.flags = r13.flags | 2;
    L19:
        if (this.paymentForm.invoice.email_requested == false) goto L22;
        this.validateRequest.info.email = this.inputFields[7].getText().toString().trim();
        this.validateRequest.info.flags |= 4;
    L22:
        if (this.paymentForm.invoice.shipping_address_requested == false) goto L28;
        this.validateRequest.info.shipping_address = new TLRPC.TL_postAddress();
        this.validateRequest.info.shipping_address.street_line1 = this.inputFields[0].getText().toString();
        this.validateRequest.info.shipping_address.street_line2 = this.inputFields[1].getText().toString();
        this.validateRequest.info.shipping_address.city = this.inputFields[2].getText().toString();
        this.validateRequest.info.shipping_address.state = this.inputFields[3].getText().toString();
        TLRPC.TL_postAddress r0 = this.validateRequest.info.shipping_address;
        String r14 = this.countryName;
        if (r14 != null) goto L27;
        r14 = _UrlKt.FRAGMENT_ENCODE_SET;
    L27:
        r0.country_iso2 = r14;
        r0.post_code = this.inputFields[5].getText().toString();
        this.validateRequest.info.flags |= 8;
    L28:
        TLRPC.TL_payments_validateRequestedInfo r02 = this.validateRequest;
        ConnectionsManager r15 = ConnectionsManager.getInstance(this.currentAccount);
        TLRPC.TL_payments_validateRequestedInfo r5 = this.validateRequest;
        PaymentFormActivity$$ExternalSyntheticLambda48 r6 = new PaymentFormActivity$$ExternalSyntheticLambda48(this, r02);
        if (this.allowUnregistered == true) goto L32;
        r3 = 0;
    L32:
        r15.sendRequest(r5, r6, r3 | 2);
        return;
    L9:
        if (this.messageObject == null) goto L11;
        TLRPC.TL_inputInvoiceMessage r16 = new TLRPC.TL_inputInvoiceMessage();
        r16.peer = getMessagesController().getInputPeer(this.messageObject.messageOwner.peer_id);
        r16.msg_id = this.messageObject.getId();
        this.validateRequest.invoice = r16;
        goto L12
    L11:
        TLRPC.TL_inputInvoiceSlug r17 = new TLRPC.TL_inputInvoiceSlug();
        r17.slug = this.invoiceSlug;
        this.validateRequest.invoice = r17;
        goto L12
    }

    private void sendSavePassword(boolean r10) {
        if (r10 == false) goto L5;
    L12:
        TL_account.updatePasswordSettings r7 = new TL_account.updatePasswordSettings();
        if (r10 == false) goto L15;
        this.doneItem.setVisibility(0);
        TL_account.passwordInputSettings r3 = new TL_account.passwordInputSettings();
        r7.new_settings = r3;
        r3.flags = 2;
        r3.email = _UrlKt.FRAGMENT_ENCODE_SET;
        r7.password = new TLRPC.TL_inputCheckPasswordEmpty();
        String r5 = null;
        String r6 = null;
    L36:
        showEditDoneProgress(true, true);
        Utilities.globalQueue.postRunnable(new PaymentFormActivity$$ExternalSyntheticLambda37(this, r10, r5, r6, r7));
        return;
    L15:
        String r4 = this.inputFields[0].getText().toString();
        if (TextUtils.isEmpty(r4) == false) goto L20;
        shakeField(0);
        return;
    L20:
        if (r4.equals(this.inputFields[1].getText().toString()) == false) goto L40;
        String r32 = this.inputFields[2].getText().toString();
        if (r32.length() >= 3) goto L31;
        shakeField(2);
        return;
    L31:
        int r52 = r32.lastIndexOf(46);
        int r8 = r32.lastIndexOf(64);
        if (r8 < 0) goto L38;
        if (r52 < r8) goto L38;
        r7.password = new TLRPC.TL_inputCheckPasswordEmpty();
        TL_account.passwordInputSettings r2 = new TL_account.passwordInputSettings();
        r7.new_settings = r2;
        int r53 = r2.flags;
        r2.flags = r53 | 1;
        r2.hint = _UrlKt.FRAGMENT_ENCODE_SET;
        r2.new_algo = this.currentPassword.new_algo;
        r2.flags = r53 | 3;
        r2.email = r32.trim();
        r5 = r32;
        r6 = r4;
    L38:
        shakeField(2);
        return;
    L40:
        Toast.makeText(getParentActivity(), LocaleController.getString(R.string.PasswordDoNotMatch), 0).show();     // Catch: Exception -> L23
    L25:
        shakeField(1);
        return;
    L23:
        e = move-exception;
        FileLog.e(e);
        goto L25
    L5:
        if (this.codeFieldCell.getVisibility() != 0) goto L12;
        String r102 = this.codeFieldCell.getText();
        if (r102.length() != 0) goto L10;
        shakeView(this.codeFieldCell);
        return;
    L10:
        showEditDoneProgress(true, true);
        TL_account.confirmPasswordEmail r0 = new TL_account.confirmPasswordEmail();
        r0.code = r102;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new PaymentFormActivity$$ExternalSyntheticLambda36(this), 10);
    }

    private void sendSavedForm(Runnable r5) {
        if (this.canceled == false) goto L5;
        return;
    L5:
        showEditDoneProgress(true, true);
        TLRPC.TL_payments_validateRequestedInfo r1 = new TLRPC.TL_payments_validateRequestedInfo();
        this.validateRequest = r1;
        TLRPC.InputInvoice r2 = this.invoiceInput;
        if (r2 == null) goto L9;
        r1.invoice = r2;
    L12:
        TLRPC.TL_payments_validateRequestedInfo r12 = this.validateRequest;
        r12.save = true;
        r12.info = this.paymentForm.saved_info;
        ConnectionsManager r0 = ConnectionsManager.getInstance(this.currentAccount);
        PaymentFormActivity$$ExternalSyntheticLambda46 r22 = new PaymentFormActivity$$ExternalSyntheticLambda46(this, r5, r12);
        if (this.allowUnregistered == false) goto L15;
        int r4 = 8;
    L16:
        r0.sendRequest(r12, r22, r4 | 2);
        return;
    L15:
        r4 = 0;
        goto L16
    L9:
        if (this.messageObject == null) goto L11;
        TLRPC.TL_inputInvoiceMessage r13 = new TLRPC.TL_inputInvoiceMessage();
        r13.peer = getMessagesController().getInputPeer(this.messageObject.messageOwner.peer_id);
        r13.msg_id = this.messageObject.getId();
        this.validateRequest.invoice = r13;
        goto L12
    L11:
        TLRPC.TL_inputInvoiceSlug r14 = new TLRPC.TL_inputInvoiceSlug();
        r14.slug = this.invoiceSlug;
        this.validateRequest.invoice = r14;
        goto L12
    }

    private void setAddressFields(TLRPC.TL_paymentRequestedInfo r10) {
        TLRPC.TL_postAddress r0 = r10.shipping_address;
        boolean r2 = true;
        if (r0 == null) goto L5;
        this.detailSettingsCell[2].setTextAndValueAndIcon(String.format("%s %s, %s, %s, %s, %s", new Object[]{r0.street_line1, r0.street_line2, r0.city, r0.state, r0.country_iso2, r0.post_code}), LocaleController.getString(R.string.PaymentShippingAddress), R.drawable.msg_payment_address, true);
    L5:
        TextDetailSettingsCell r02 = this.detailSettingsCell[2];
        int r3 = 8;
        if (r10.shipping_address == null) goto L8;
        int r1 = 0;
    L9:
        r02.setVisibility(r1);
        String r03 = r10.name;
        if (r03 == null) goto L12;
        this.detailSettingsCell[3].setTextAndValueAndIcon(r03, LocaleController.getString(R.string.PaymentCheckoutName), R.drawable.msg_contacts, true);
    L12:
        TextDetailSettingsCell r04 = this.detailSettingsCell[3];
        if (r10.name == null) goto L15;
        int r12 = 0;
    L16:
        r04.setVisibility(r12);
        if (r10.phone == null) goto L26;
        TextDetailSettingsCell r05 = this.detailSettingsCell[4];
        String r5 = PhoneFormat.getInstance().format(r10.phone);
        String r6 = LocaleController.getString(R.string.PaymentCheckoutPhoneNumber);
        int r7 = R.drawable.msg_calls;
        if (r10.email == null) goto L21;
    L24:
        boolean r8 = true;
    L25:
        r05.setTextAndValueAndIcon(r5, r6, r7, r8);
        goto L26
    L21:
        if (this.shippingOption != null) goto L24;
        r8 = false;
    L26:
        TextDetailSettingsCell r06 = this.detailSettingsCell[4];
        if (r10.phone == null) goto L29;
        int r13 = 0;
    L30:
        r06.setVisibility(r13);
        String r07 = r10.email;
        if (r07 == null) goto L37;
        TextDetailSettingsCell r52 = this.detailSettingsCell[5];
        String r62 = LocaleController.getString(R.string.PaymentCheckoutEmail);
        int r72 = R.drawable.msg_mention;
        if (this.shippingOption != null) goto L36;
        r2 = false;
    L36:
        r52.setTextAndValueAndIcon(r07, r62, r72, r2);
    L37:
        TextDetailSettingsCell r9 = this.detailSettingsCell[5];
        if (r10.email == null) goto L40;
        r3 = 0;
    L40:
        r9.setVisibility(r3);
        return;
    L29:
        r13 = 8;
        goto L30
    L15:
        r12 = 8;
        goto L16
    L8:
        r1 = 8;
        goto L9
    }

    private void setCurrentPassword(TL_account.Password r2) {
        if (r2 != null) goto L4;
    L10:
        this.currentPassword = r2;
        if (r2 != null) goto L13;
    L15:
        boolean r22 = false;
    L16:
        this.waitingForEmail = r22;
        updatePasswordFields();
        return;
    L13:
        if (TextUtils.isEmpty(r2.email_unconfirmed_pattern) == true) goto L15;
        r22 = true;
        goto L16
    L4:
        if (r2.has_password == false) goto L10;
        if (getParentActivity() != null) goto L8;
        return;
    L8:
        goToNextStep();
    }

    private void setDelegate(PaymentFormActivityDelegate r1) {
        this.delegate = r1;
    }

    private void setDonePressed(boolean r2) {
        this.donePressed = r2;
        this.swipeBackEnabled = !r2;
        ActionBar r22 = this.actionBar;
        if (r22 != null) goto L5;
    L7:
        TextDetailSettingsCell r23 = this.detailSettingsCell[0];
        if (r23 == null) goto L11;
        r23.setEnabled(!this.donePressed);
        return;
    L11:
        return;
    L5:
        if (r22.getBackButton() == null) goto L7;
        this.actionBar.getBackButton().setEnabled(!this.donePressed);
        goto L7
    }

    private void setPaymentMethod(TLRPC.TL_paymentFormMethod r1) {
        this.paymentFormMethod = r1;
    }

    private void shakeField(int r2) {
        shakeView(this.inputFields[r2]);
    }

    private void shakeView(View r2) {
        r2.performHapticFeedback(3, 2);     // Catch: Exception -> L6
    L4:
        AndroidUtilities.shakeViewSpring(r2, 2.5f);
    }

    private void showAlertWithText(String r4, String r5) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r0.setTitle(r4);
        r0.setMessage(r5);
        showDialog(r0.create());
    }

    private void showChoosePaymentMethod() {
        showChoosePaymentMethod(null);
    }

    private void showChoosePaymentMethod(Runnable r12) {
        BottomSheet.Builder r0 = new BottomSheet.Builder(getParentActivity()).setTitle(LocaleController.getString(R.string.PaymentCheckoutMethod), true);
        ArrayList r1 = new ArrayList();
        ArrayList r2 = new ArrayList();
        TLRPC.TL_paymentSavedCredentialsCard r3 = this.savedCredentialsCard;
        if (r3 == null) goto L5;
        r1.add(r3.title);
        r2.add(Integer.valueOf(R.drawable.msg_payment_card));
    L8:
        ArrayList r32 = new ArrayList();
        ArrayList<TLRPC.TL_paymentSavedCredentialsCard> r4 = this.paymentForm.saved_credentials;
        int r5 = r4.size();
        int r7 = 0;
    L9:
        if (r7 >= r5) goto L15;
        TLRPC.TL_paymentSavedCredentialsCard r8 = r4.get(r7);
        r7 = r7 + 1;
        TLRPC.TL_paymentSavedCredentialsCard r82 = r8;
        TLRPC.TL_paymentSavedCredentialsCard r9 = this.savedCredentialsCard;
        if (r9 == null) goto L14;
        if (Objects.equals(r82.id, r9.id) == true) goto L9;
    L14:
        r1.add(r82.title);
        r2.add(Integer.valueOf(R.drawable.msg_payment_card));
        r32.add(r82);
        goto L9
    L15:
        ArrayList<TLRPC.TL_paymentFormMethod> r42 = this.paymentForm.additional_methods;
        int r52 = r42.size();
        int r72 = 0;
    L16:
        if (r72 >= r52) goto L18;
        TLRPC.TL_paymentFormMethod r83 = r42.get(r72);
        r72 = r72 + 1;
        r1.add(r83.title);
        r2.add(Integer.valueOf(R.drawable.msg_payment_provider));
        goto L16
    L18:
        r1.add(LocaleController.getString(R.string.PaymentCheckoutMethodNewCard));
        r2.add(Integer.valueOf(R.drawable.msg_addbot));
        int[] r43 = new int[r2.size()];
        int r53 = 0;
    L20:
        if (r53 >= r2.size()) goto L22;
        r43[r53] = ((Integer) r2.get(r53)).intValue();
        r53 = r53 + 1;
        goto L20
    L22:
        r0.setItems((CharSequence[]) r1.toArray(new CharSequence[0]), r43, new PaymentFormActivity$$ExternalSyntheticLambda43(this, r12, r32, r1));
        showDialog(r0.create());
        return;
    L5:
        String r33 = this.cardName;
        if (r33 == null) goto L8;
        r1.add(r33);
        r2.add(Integer.valueOf(R.drawable.msg_payment_card));
        goto L8
    }

    private void showEditDoneProgress(boolean r22, boolean r23) {
        AnimatorSet r2 = this.doneItemAnimation;
        if (r2 == null) goto L6;
        r2.cancel();
    L6:
        if (r22 == false) goto L21;
        if (this.doneItem == null) goto L21;
        AnimatorSet r14 = new AnimatorSet();
        this.doneItemAnimation = r14;
        if (r23 == false) goto L13;
        this.progressView.setVisibility(0);
        this.doneItem.setEnabled(false);
        AnimatorSet r142 = this.doneItemAnimation;
        View r15 = this.doneItem.getContentView();
        Property r24 = View.SCALE_X;
        ObjectAnimator r3 = ObjectAnimator.ofFloat(r15, r24, new float[]{0.1f});
        View r152 = this.doneItem.getContentView();
        Property r6 = View.SCALE_Y;
        ObjectAnimator r10 = ObjectAnimator.ofFloat(r152, r6, new float[]{0.1f});
        View r12 = this.doneItem.getContentView();
        Property r153 = View.ALPHA;
        r142.playTogether(new Animator[]{r3, r10, ObjectAnimator.ofFloat(r12, r153, new float[]{0.0f}), ObjectAnimator.ofFloat(this.progressView, r24, new float[]{1.0f}), ObjectAnimator.ofFloat(this.progressView, r6, new float[]{1.0f}), ObjectAnimator.ofFloat(this.progressView, r153, new float[]{1.0f})});
    L18:
        this.doneItemAnimation.addListener(new AnonymousClass28(this, r23));
        this.doneItemAnimation.setDuration(150);
        this.doneItemAnimation.start();
        return;
    L13:
        if (this.webView == null) goto L15;
        r14.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.progressView, View.SCALE_X, new float[]{0.1f}), ObjectAnimator.ofFloat(this.progressView, View.SCALE_Y, new float[]{0.1f}), ObjectAnimator.ofFloat(this.progressView, View.ALPHA, new float[]{0.0f})});
        goto L18
    L15:
        this.doneItem.getContentView().setVisibility(0);
        this.doneItem.setEnabled(true);
        AnimatorSet r25 = this.doneItemAnimation;
        ContextProgressView r32 = this.progressView;
        Property r4 = View.SCALE_X;
        ObjectAnimator r33 = ObjectAnimator.ofFloat(r32, r4, new float[]{0.1f});
        ContextProgressView r62 = this.progressView;
        Property r7 = View.SCALE_Y;
        ObjectAnimator r63 = ObjectAnimator.ofFloat(r62, r7, new float[]{0.1f});
        ContextProgressView r8 = this.progressView;
        Property r102 = View.ALPHA;
        r25.playTogether(new Animator[]{r33, r63, ObjectAnimator.ofFloat(r8, r102, new float[]{0.0f})});
        if (isFinishing() == true) goto L18;
        this.doneItemAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.doneItem.getContentView(), r4, new float[]{1.0f}), ObjectAnimator.ofFloat(this.doneItem.getContentView(), r7, new float[]{1.0f}), ObjectAnimator.ofFloat(this.doneItem.getContentView(), r102, new float[]{1.0f})});
    L21:
        if (this.payTextView == null) goto L28;
        this.doneItemAnimation = new AnimatorSet();
        if (r23 == false) goto L25;
        this.progressViewButton.setVisibility(0);
        this.bottomLayout.setEnabled(false);
        AnimatorSet r26 = this.doneItemAnimation;
        TextView r34 = this.payTextView;
        Property r64 = View.SCALE_X;
        ObjectAnimator r35 = ObjectAnimator.ofFloat(r34, r64, new float[]{0.1f});
        TextView r72 = this.payTextView;
        Property r82 = View.SCALE_Y;
        ObjectAnimator r73 = ObjectAnimator.ofFloat(r72, r82, new float[]{0.1f});
        TextView r103 = this.payTextView;
        Property r122 = View.ALPHA;
        r26.playTogether(new Animator[]{r35, r73, ObjectAnimator.ofFloat(r103, r122, new float[]{0.0f}), ObjectAnimator.ofFloat(this.progressViewButton, r64, new float[]{1.0f}), ObjectAnimator.ofFloat(this.progressViewButton, r82, new float[]{1.0f}), ObjectAnimator.ofFloat(this.progressViewButton, r122, new float[]{1.0f})});
    L26:
        this.doneItemAnimation.addListener(new AnonymousClass29(this, r23));
        this.doneItemAnimation.setDuration(150);
        this.doneItemAnimation.start();
        return;
    L25:
        this.payTextView.setVisibility(0);
        this.bottomLayout.setEnabled(true);
        AnimatorSet r27 = this.doneItemAnimation;
        ContextProgressView r36 = this.progressViewButton;
        Property r65 = View.SCALE_X;
        ObjectAnimator r37 = ObjectAnimator.ofFloat(r36, r65, new float[]{0.1f});
        ContextProgressView r74 = this.progressViewButton;
        Property r83 = View.SCALE_Y;
        ObjectAnimator r75 = ObjectAnimator.ofFloat(r74, r83, new float[]{0.1f});
        ContextProgressView r104 = this.progressViewButton;
        Property r123 = View.ALPHA;
        r27.playTogether(new Animator[]{r37, r75, ObjectAnimator.ofFloat(r104, r123, new float[]{0.0f}), ObjectAnimator.ofFloat(this.payTextView, r65, new float[]{1.0f}), ObjectAnimator.ofFloat(this.payTextView, r83, new float[]{1.0f}), ObjectAnimator.ofFloat(this.payTextView, r123, new float[]{1.0f})});
        goto L26
    }

    private void showPayAlert(String r5) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.PaymentTransactionReview));
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("PaymentTransactionMessage2", R.string.PaymentTransactionMessage2, new Object[]{r5, this.currentBotName, this.currentItemName})));
        r0.setPositiveButton(LocaleController.getString(R.string.Continue), new PaymentFormActivity$$ExternalSyntheticLambda47(this));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r0.create());
    }

    private void updatePasswordFields() {
        if (this.currentStep == 6) goto L5;
        return;
    L5:
        if (this.bottomCell[2] == null) goto L41;
        int r2 = 0;
        this.doneItem.setVisibility(0);
        if (this.currentPassword != null) goto L15;
        showEditDoneProgress(true, true);
        this.bottomCell[2].setVisibility(8);
        this.settingsCell[0].setVisibility(8);
        this.settingsCell[1].setVisibility(8);
        this.codeFieldCell.setVisibility(8);
        this.headerCell[0].setVisibility(8);
        this.headerCell[1].setVisibility(8);
        this.bottomCell[0].setVisibility(8);
        int r0 = 0;
    L10:
        if (r0 >= 3) goto L13;
        ((View) this.inputFields[r0].getParent()).setVisibility(8);
        r0 = r0 + 1;
    L13:
        if (r2 >= this.dividers.size()) goto L42;
        this.dividers.get(r2).setVisibility(8);
        r2 = r2 + 1;
        goto L13
    L42:
        return;
    L15:
        showEditDoneProgress(true, false);
        boolean r02 = this.waitingForEmail;
        TextInfoPrivacyCell[] r6 = this.bottomCell;
        if (r02 == false) goto L27;
        TextInfoPrivacyCell r03 = r6[2];
        int r62 = R.string.EmailPasswordConfirmText2;
        String r7 = this.currentPassword.email_unconfirmed_pattern;
        if (r7 != null) goto L21;
        r7 = _UrlKt.FRAGMENT_ENCODE_SET;
    L21:
        r03.setText(LocaleController.formatString("EmailPasswordConfirmText2", r62, new Object[]{r7}));
        this.bottomCell[2].setVisibility(0);
        this.settingsCell[0].setVisibility(0);
        this.settingsCell[1].setVisibility(0);
        this.codeFieldCell.setVisibility(0);
        this.bottomCell[1].setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.headerCell[0].setVisibility(8);
        this.headerCell[1].setVisibility(8);
        this.bottomCell[0].setVisibility(8);
        int r04 = 0;
    L22:
        if (r04 >= 3) goto L25;
        ((View) this.inputFields[r04].getParent()).setVisibility(8);
        r04 = r04 + 1;
    L25:
        if (r2 >= this.dividers.size()) goto L43;
        this.dividers.get(r2).setVisibility(8);
        r2 = r2 + 1;
        goto L25
    L43:
        return;
    L27:
        r6[2].setVisibility(8);
        this.settingsCell[0].setVisibility(8);
        this.settingsCell[1].setVisibility(8);
        this.bottomCell[1].setText(LocaleController.getString(R.string.PaymentPasswordEmailInfo));
        this.codeFieldCell.setVisibility(8);
        this.headerCell[0].setVisibility(0);
        this.headerCell[1].setVisibility(0);
        this.bottomCell[0].setVisibility(0);
        int r05 = 0;
    L28:
        if (r05 >= 3) goto L30;
        ((View) this.inputFields[r05].getParent()).setVisibility(0);
        r05 = r05 + 1;
        goto L28
    L30:
        int r06 = 0;
    L32:
        if (r06 >= this.dividers.size()) goto L44;
        this.dividers.get(r06).setVisibility(0);
        r06 = r06 + 1;
        goto L32
    L44:
        return;
    }

    private void updateSavePaymentField() {
        if (this.bottomCell[0] != null) goto L5;
        return;
    L5:
        if (this.sectionCell[2] == null) goto L27;
        TLRPC.PaymentForm r0 = this.paymentForm;
        if (r0.password_missing == true) goto L12;
        if (r0.can_save_credentials == true) goto L12;
    L16:
        this.checkCell1.setVisibility(8);
        this.bottomCell[0].setVisibility(8);
        ShadowSectionCell r8 = this.sectionCell[2];
        r8.setBackground(Theme.getThemedDrawableByKey(r8.getContext(), R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        return;
    L12:
        if (this.webView != null) goto L14;
    L18:
        SpannableStringBuilder r02 = new SpannableStringBuilder(LocaleController.getString(R.string.PaymentCardSavePaymentInformationInfoLine1));
        if (this.paymentForm.password_missing == false) goto L24;
        loadPasswordInfo();
        r02.append("\n");
        int r3 = r02.length();
        String r5 = LocaleController.getString(R.string.PaymentCardSavePaymentInformationInfoLine2);
        int r7 = r5.indexOf(42);
        int r6 = r5.lastIndexOf(42);
        r02.append(r5);
        if (r7 == (-1)) goto L24;
        if (r6 == (-1)) goto L24;
        int r72 = r7 + r3;
        int r62 = r6 + r3;
        this.bottomCell[0].getTextView().setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r02.replace(r62, r62 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
        r02.replace(r72, r72 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
        r02.setSpan(new LinkSpan(this), r72, r62 - 1, 33);
    L24:
        this.checkCell1.setEnabled(true);
        this.bottomCell[0].setText(r02);
        this.checkCell1.setVisibility(0);
        this.bottomCell[0].setVisibility(0);
        ShadowSectionCell r82 = this.sectionCell[2];
        r82.setBackground(Theme.getThemedDrawableByKey(r82.getContext(), R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
        return;
    L14:
        if (this.webviewLoading == true) goto L16;
    }

    private void updateTotalPrice() {
        int r2 = 0;
        this.totalPrice[0] = getTotalPriceString(this.prices);
        this.totalCell.setTextAndValue(LocaleController.getString(R.string.PaymentTransactionTotal), this.totalPrice[0], true);
        TextView r0 = this.payTextView;
        if (r0 == null) goto L6;
        r0.setText(LocaleController.formatString("PaymentCheckoutPay", R.string.PaymentCheckoutPay, new Object[]{this.totalPrice[0]}));
    L6:
        if (this.tipLayout == null) goto L14;
        int r02 = getThemedColor(Theme.key_contacts_inviteBackground);
        int r1 = this.tipLayout.getChildCount();
    L8:
        if (r2 >= r1) goto L18;
        TextView r3 = (TextView) this.tipLayout.getChildAt(r2);
        if (r3.getTag().equals(this.tipAmount) == false) goto L12;
        Theme.setDrawableColor(r3.getBackground(), r02);
        r3.setTextColor(getThemedColor(Theme.key_contacts_inviteText));
    L13:
        r3.invalidate();
        r2 = r2 + 1;
        goto L8
    L12:
        Theme.setDrawableColor(r3.getBackground(), 536870911 & r02);
        r3.setTextColor(getThemedColor(Theme.key_chats_secretName));
        goto L13
    L18:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v282, types: [android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r5v321, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout] */
    /* JADX WARN: Type inference failed for: r6v249, types: [android.view.ViewGroup, android.widget.LinearLayout] */
    @Override // org.telegram.ui.ActionBar.BaseFragment
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    public View createView(Context r38) {
        switch(this.currentStep) {
            case 0: goto L21;
            case 1: goto L20;
            case 2: goto L14;
            case 3: goto L14;
            case 4: goto L10;
            case 5: goto L6;
            case 6: goto L5;
            default: goto L22;
        };
    L5:
        this.actionBar.setTitle(LocaleController.getString(R.string.PaymentPassword));
        goto L22
    L6:
        boolean r0 = this.paymentForm.invoice.test;
        ActionBar r5 = this.actionBar;
        if (r0 == false) goto L9;
        r5.setTitle("Test " + LocaleController.getString(R.string.PaymentReceipt));
        goto L22
    L9:
        r5.setTitle(LocaleController.getString(R.string.PaymentReceipt));
        goto L22
    L10:
        boolean r02 = this.paymentForm.invoice.test;
        ActionBar r52 = this.actionBar;
        if (r02 == false) goto L13;
        r52.setTitle("Test " + LocaleController.getString(R.string.PaymentCheckout));
        goto L22
    L13:
        r52.setTitle(LocaleController.getString(R.string.PaymentCheckout));
        goto L22
    L14:
        TLRPC.TL_paymentFormMethod r03 = this.paymentFormMethod;
        if (r03 != null) goto L17;
    L19:
        this.actionBar.setTitle(LocaleController.getString(R.string.PaymentCardInfo));
        goto L22
    L17:
        if (TextUtils.isEmpty(r03.title) == true) goto L19;
        this.actionBar.setTitle(this.paymentFormMethod.title);
        goto L22
    L20:
        this.actionBar.setTitle(LocaleController.getString(R.string.PaymentShippingMethod));
        goto L22
    L21:
        this.actionBar.setTitle(LocaleController.getString(R.string.PaymentShippingInfo));
    L22:
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenu r04 = this.actionBar.createMenu();
        int r4 = this.currentStep;
        float r53 = 0.0f;
        int r8 = 6;
        int r10 = 3;
        if (r4 == 0) goto L30;
        if (r4 == 1) goto L30;
        if (r4 == 2) goto L30;
        if (r4 == 3) goto L30;
        if (r4 == 4) goto L30;
        if (r4 == 6) goto L30;
    L31:
        FrameLayout r13 = new FrameLayout(r38);
        this.fragmentView = r13;
        r13.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundGray));
        ScrollView r05 = new ScrollView(r38);
        this.scrollView = r05;
        r05.setFillViewport(true);
        AndroidUtilities.setScrollViewEdgeEffectColor(this.scrollView, getThemedColor(Theme.key_actionBarDefault));
        ScrollView r06 = this.scrollView;
        if (this.currentStep != 4) goto L34;
        r53 = 48.0f;
    L34:
        r13.addView(r06, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, r53));
        LinearLayout r07 = new LinearLayout(r38);
        this.linearLayout2 = r07;
        r07.setOrientation(1);
        this.linearLayout2.setClipChildren(false);
        this.scrollView.addView(this.linearLayout2, new FrameLayout.LayoutParams(-1, -2));
        int r08 = this.currentStep;
        String r11 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r23 = 1;
        int r7 = 8;
        if (r08 != 0) goto L269;
        HashMap r3 = new HashMap();
        HashMap r132 = new HashMap();
        BufferedReader r09 = new BufferedReader(new InputStreamReader(r38.getResources().getAssets().open("countries.txt")));     // Catch: Exception -> L43
    L38:
        String r42 = r09.readLine();     // Catch: Exception -> L43
        if (r42 == null) goto L46;
        String[] r43 = r42.split(";");     // Catch: Exception -> L43
        this.countriesArray.add(0, r43[2]);     // Catch: Exception -> L43
        this.countriesMap.put(r43[2], r43[0]);     // Catch: Exception -> L43
        this.codesMap.put(r43[0], r43[2]);     // Catch: Exception -> L43
        r132.put(r43[1], r43[2]);     // Catch: Exception -> L43
        if (r43.length <= 3) goto L45;
        this.phoneFormatMap.put(r43[0], r43[3]);     // Catch: Exception -> L43
    L45:
        r3.put(r43[1], r43[2]);     // Catch: Exception -> L43
        goto L38
    L46:
        r09.close();     // Catch: Exception -> L43
    L49:
        Collections.sort(this.countriesArray, new PassportActivity$$ExternalSyntheticLambda8());
        int r010 = 10;
        this.inputFields = new EditTextBoldCursor[10];
        int r44 = 0;
    L50:
        if (r44 >= r010) goto L186;
        if (r44 != 0) goto L53;
        this.headerCell[0] = new HeaderCell(r38, this.resourcesProvider);
        this.headerCell[0].setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        this.headerCell[0].setText(LocaleController.getString(R.string.PaymentShippingAddress));
        this.linearLayout2.addView(this.headerCell[0], LayoutHelper.createLinear(-1, -2));
    L55:
        if (r44 != r7) goto L59;
        ?? r54 = new LinearLayout(r38);
        r54.setClipChildren(false);
        r54.setOrientation(0);
        this.linearLayout2.addView(r54, LayoutHelper.createLinear(-1, 50));
        r54.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        FrameLayout r55 = r54;
    L57:
        int r27 = r8;
    L81:
        EditTextBoldCursor[] r011 = this.inputFields;
        if (r44 != 9) goto L84;
        r011[r44] = new HintEditText(r38);
    L85:
        this.inputFields[r44].setTag(Integer.valueOf(r44));
        this.inputFields[r44].setTextSize(1, 16.0f);
        this.inputFields[r44].setHintTextColor(getThemedColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r012 = this.inputFields[r44];
        int r6 = Theme.key_windowBackgroundWhiteBlackText;
        r012.setTextColor(getThemedColor(r6));
        this.inputFields[r44].setBackgroundDrawable(null);
        this.inputFields[r44].setCursorColor(getThemedColor(r6));
        this.inputFields[r44].setCursorSize(AndroidUtilities.dp(20.0f));
        this.inputFields[r44].setCursorWidth(1.5f);
        if (r44 != 4) goto L89;
        this.inputFields[r44].setOnTouchListener(new PaymentFormActivity$$ExternalSyntheticLambda15(this));
        this.inputFields[r44].setInputType(0);
    L89:
        if (r44 == 9) goto L96;
        if (r44 == r7) goto L96;
        EditTextBoldCursor[] r013 = this.inputFields;
        if (r44 != 7) goto L95;
        r013[r44].setInputType(1);
    L97:
        this.inputFields[r44].setImeOptions(268435461);
        switch(r44) {
            case 0: goto L139;
            case 1: goto L134;
            case 2: goto L129;
            case 3: goto L124;
            case 4: goto L115;
            case 5: goto L110;
            case 6: goto L105;
            case 7: goto L100;
            default: goto L144;
        };
    L100:
        this.inputFields[r44].setHint(LocaleController.getString(R.string.PaymentShippingEmailPlaceholder));
        TLRPC.TL_paymentRequestedInfo r014 = this.paymentForm.saved_info;
        if (r014 == null) goto L144;
        String r015 = r014.email;
        if (r015 == null) goto L144;
        this.inputFields[r44].setText(r015);
        goto L144
    L105:
        this.inputFields[r44].setHint(LocaleController.getString(R.string.PaymentShippingName));
        TLRPC.TL_paymentRequestedInfo r016 = this.paymentForm.saved_info;
        if (r016 == null) goto L144;
        String r017 = r016.name;
        if (r017 == null) goto L144;
        this.inputFields[r44].setText(r017);
        goto L144
    L110:
        this.inputFields[r44].setHint(LocaleController.getString(R.string.PaymentShippingZipPlaceholder));
        TLRPC.TL_paymentRequestedInfo r018 = this.paymentForm.saved_info;
        if (r018 == null) goto L144;
        TLRPC.TL_postAddress r019 = r018.shipping_address;
        if (r019 == null) goto L144;
        this.inputFields[r44].setText(r019.post_code);
        goto L144
    L115:
        this.inputFields[r44].setHint(LocaleController.getString(R.string.PaymentShippingCountry));
        TLRPC.TL_paymentRequestedInfo r020 = this.paymentForm.saved_info;
        if (r020 == null) goto L144;
        TLRPC.TL_postAddress r021 = r020.shipping_address;
        if (r021 == null) goto L144;
        String r022 = (String) r132.get(r021.country_iso2);
        String r82 = this.paymentForm.saved_info.shipping_address.country_iso2;
        this.countryName = r82;
        EditTextBoldCursor r9 = this.inputFields[r44];
        if (r022 != null) goto L123;
        r022 = r82;
    L123:
        r9.setText(r022);
        goto L144
    L124:
        this.inputFields[r44].setHint(LocaleController.getString(R.string.PaymentShippingStatePlaceholder));
        TLRPC.TL_paymentRequestedInfo r023 = this.paymentForm.saved_info;
        if (r023 == null) goto L144;
        TLRPC.TL_postAddress r024 = r023.shipping_address;
        if (r024 == null) goto L144;
        this.inputFields[r44].setText(r024.state);
        goto L144
    L129:
        this.inputFields[r44].setHint(LocaleController.getString(R.string.PaymentShippingCityPlaceholder));
        TLRPC.TL_paymentRequestedInfo r025 = this.paymentForm.saved_info;
        if (r025 == null) goto L144;
        TLRPC.TL_postAddress r026 = r025.shipping_address;
        if (r026 == null) goto L144;
        this.inputFields[r44].setText(r026.city);
        goto L144
    L134:
        this.inputFields[r44].setHint(LocaleController.getString(R.string.PaymentShippingAddress2Placeholder));
        TLRPC.TL_paymentRequestedInfo r027 = this.paymentForm.saved_info;
        if (r027 == null) goto L144;
        TLRPC.TL_postAddress r028 = r027.shipping_address;
        if (r028 == null) goto L144;
        this.inputFields[r44].setText(r028.street_line2);
        goto L144
    L139:
        this.inputFields[r44].setHint(LocaleController.getString(R.string.PaymentShippingAddress1Placeholder));
        TLRPC.TL_paymentRequestedInfo r029 = this.paymentForm.saved_info;
        if (r029 == null) goto L144;
        TLRPC.TL_postAddress r030 = r029.shipping_address;
        if (r030 == null) goto L144;
        this.inputFields[r44].setText(r030.street_line1);
    L144:
        EditTextBoldCursor r031 = this.inputFields[r44];
        r031.setSelection(r031.length());
        if (r44 != r7) goto L147;
        TextView r83 = new TextView(r38);
        this.textView = r83;
        r83.setText("+");
        this.textView.setTextColor(getThemedColor(r6));
        this.textView.setTextSize(1, 16.0f);
        r55.addView(this.textView, LayoutHelper.createLinear(-2, -2, 21.0f, 12.0f, 0.0f, 6.0f));
        this.inputFields[r44].setPadding(AndroidUtilities.dp(10.0f), 0, 0, 0);
        this.inputFields[r44].setGravity(19);
        this.inputFields[r44].setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        r55.addView(this.inputFields[r44], LayoutHelper.createLinear(55, -2, 0.0f, 12.0f, 21.0f, 6.0f));
        this.inputFields[r44].addTextChangedListener(new AnonymousClass3(this));
    L155:
        this.inputFields[r44].setOnEditorActionListener(new PaymentFormActivity$$ExternalSyntheticLambda22(this));
        if (r44 != 9) goto L184;
        TLRPC.TL_invoice r032 = this.paymentForm.invoice;
        if (r032.email_to_provider == false) goto L160;
    L163:
        int r56 = 0;
        TLRPC.User r033 = null;
    L165:
        if (r56 >= this.paymentForm.users.size()) goto L170;
        TLRPC.User r62 = this.paymentForm.users.get(r56);
        int r28 = r10;
        if (r62.id != this.paymentForm.provider_id) goto L169;
        r033 = r62;
    L169:
        r56 = r56 + 1;
        r10 = r28;
        goto L165
    L170:
        int r282 = r10;
        if (r033 == null) goto L173;
        String r034 = ContactsController.formatName(r033.first_name, r033.last_name);
    L174:
        this.bottomCell[1] = new TextInfoPrivacyCell(r38, this.resourcesProvider);
        this.bottomCell[1].setBackgroundDrawable(Theme.getThemedDrawableByKey(r38, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.linearLayout2.addView(this.bottomCell[1], LayoutHelper.createLinear(-1, -2));
        TLRPC.TL_invoice r57 = this.paymentForm.invoice;
        boolean r63 = r57.email_to_provider;
        if (r63 == true) goto L177;
    L179:
        TextInfoPrivacyCell[] r58 = this.bottomCell;
        if (r63 == false) goto L182;
        r58[1].setText(LocaleController.formatString("PaymentEmailToProvider", R.string.PaymentEmailToProvider, new Object[]{r034}));
    L183:
        TextCheckCell r035 = new TextCheckCell(r38, this.resourcesProvider);
        this.checkCell1 = r035;
        r035.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.checkCell1.setTextAndCheck(LocaleController.getString(R.string.PaymentShippingSave), this.saveShippingInfo, false);
        this.linearLayout2.addView(this.checkCell1, LayoutHelper.createLinear(-1, -2));
        this.checkCell1.setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda23(this));
        this.bottomCell[0] = new TextInfoPrivacyCell(r38, this.resourcesProvider);
        this.bottomCell[0].setBackgroundDrawable(Theme.getThemedDrawableByKey(r38, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.bottomCell[0].setText(LocaleController.getString(R.string.PaymentShippingSaveInfo));
        this.linearLayout2.addView(this.bottomCell[0], LayoutHelper.createLinear(-1, -2));
    L185:
        r44 = r44 + 1;
        r8 = r27;
        r10 = r282;
        r010 = 10;
        r7 = 8;
        r23 = 1;
        goto L50
    L182:
        r58[1].setText(LocaleController.formatString("PaymentPhoneToProvider", R.string.PaymentPhoneToProvider, new Object[]{r034}));
        goto L183
    L177:
        if (r57.phone_to_provider == false) goto L179;
        this.bottomCell[1].setText(LocaleController.formatString("PaymentPhoneEmailToProvider", R.string.PaymentPhoneEmailToProvider, new Object[]{r034}));
        goto L183
    L173:
        r034 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L174
    L160:
        if (r032.phone_to_provider == true) goto L163;
        this.sectionCell[1] = new ShadowSectionCell(r38, this.resourcesProvider);
        this.linearLayout2.addView(this.sectionCell[1], LayoutHelper.createLinear(-1, -2));
        r282 = r10;
        goto L183
    L184:
        r282 = r10;
        goto L185
    L147:
        EditTextBoldCursor[] r64 = this.inputFields;
        if (r44 != 9) goto L150;
        r64[r44].setPadding(0, 0, 0, 0);
        this.inputFields[r44].setGravity(19);
        r55.addView(this.inputFields[r44], LayoutHelper.createLinear(-1, -2, 0.0f, 12.0f, 21.0f, 6.0f));
        this.inputFields[r44].addTextChangedListener(new AnonymousClass4(this));
        goto L155
    L150:
        r64[r44].setPadding(0, 0, 0, AndroidUtilities.dp(6.0f));
        EditTextBoldCursor r036 = this.inputFields[r44];
        if (LocaleController.isRTL == false) goto L153;
        int r65 = 5;
    L154:
        r036.setGravity(r65);
        r55.addView(this.inputFields[r44], LayoutHelper.createFrame(-1, -2.0f, 51, 21.0f, 12.0f, 21.0f, 6.0f));
        goto L155
    L153:
        r65 = r10;
        goto L154
    L95:
        r013[r44].setInputType(16385);
    L96:
        this.inputFields[r44].setInputType(r10);
        goto L97
    L84:
        r011[r44] = new EditTextBoldCursor(r38);
        goto L85
    L59:
        if (r44 != 9) goto L61;
        r55 = (ViewGroup) this.inputFields[r7].getParent();
        goto L57
    L61:
        r55 = new FrameLayout(r38);
        r55.setClipChildren(false);
        this.linearLayout2.addView(r55, LayoutHelper.createLinear(-1, 50));
        int r037 = Theme.key_windowBackgroundWhite;
        r55.setBackgroundColor(getThemedColor(r037));
        if (r44 == 5) goto L64;
        int r66 = r23;
    L65:
        if (r66 != 0) goto L67;
    L78:
        r27 = r8;
    L79:
        if (r66 == 0) goto L81;
        AnonymousClass2 r67 = new AnonymousClass2(this, r38);
        r67.setBackgroundColor(getThemedColor(r037));
        this.dividers.add(r67);
        r55.addView(r67, new FrameLayout.LayoutParams(-1, r23, 83));
        goto L81
    L67:
        if (r44 == 7) goto L69;
    L72:
        if (r44 != r8) goto L78;
        TLRPC.TL_invoice r92 = this.paymentForm.invoice;
        r27 = r8;
        if (r92.phone_requested == true) goto L79;
        if (r92.email_requested == true) goto L79;
    L71:
        r66 = 0;
        goto L79
    L69:
        if (this.paymentForm.invoice.phone_requested == true) goto L72;
        r27 = r8;
        goto L71
    L64:
        r66 = 0;
        goto L65
    L53:
        if (r44 != r8) goto L55;
        this.sectionCell[0] = new ShadowSectionCell(r38, this.resourcesProvider);
        this.linearLayout2.addView(this.sectionCell[0], LayoutHelper.createLinear(-1, -2));
        this.headerCell[r23] = new HeaderCell(r38, this.resourcesProvider);
        this.headerCell[r23].setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        this.headerCell[r23].setText(LocaleController.getString(R.string.PaymentShippingReceiver));
        this.linearLayout2.addView(this.headerCell[r23], LayoutHelper.createLinear(-1, -2));
        goto L55
    L186:
        int r272 = r8;
        int r283 = r10;
        if (this.paymentForm.invoice.name_requested == true) goto L189;
        int r2 = 8;
        ((ViewGroup) this.inputFields[r272].getParent()).setVisibility(8);
    L191:
        if (this.paymentForm.invoice.phone_requested == true) goto L194;
        ((ViewGroup) this.inputFields[r2].getParent()).setVisibility(r2);
    L194:
        if (this.paymentForm.invoice.email_requested == true) goto L196;
        ((ViewGroup) this.inputFields[7].getParent()).setVisibility(r2);
    L196:
        TLRPC.TL_invoice r038 = this.paymentForm.invoice;
        if (r038.phone_requested == false) goto L200;
        this.inputFields[9].setImeOptions(268435462);
    L206:
        ShadowSectionCell r039 = this.sectionCell[1];
        if (r039 == null) goto L218;
        TLRPC.TL_invoice r22 = this.paymentForm.invoice;
        if (r22.name_requested == false) goto L211;
    L216:
        int r24 = 0;
    L217:
        r039.setVisibility(r24);
    L230:
        HeaderCell r040 = this.headerCell[1];
        TLRPC.TL_invoice r25 = this.paymentForm.invoice;
        if (r25.name_requested == false) goto L233;
    L238:
        int r26 = 0;
    L239:
        r040.setVisibility(r26);
        if (this.paymentForm.invoice.shipping_address_requested == true) goto L242;
        this.headerCell[0].setVisibility(8);
        this.sectionCell[0].setVisibility(8);
        ((ViewGroup) this.inputFields[0].getParent()).setVisibility(8);
        ((ViewGroup) this.inputFields[1].getParent()).setVisibility(8);
        ((ViewGroup) this.inputFields[2].getParent()).setVisibility(8);
        ((ViewGroup) this.inputFields[r283].getParent()).setVisibility(8);
        ((ViewGroup) this.inputFields[4].getParent()).setVisibility(8);
        ((ViewGroup) this.inputFields[5].getParent()).setVisibility(8);
    L242:
        TLRPC.TL_paymentRequestedInfo r041 = this.paymentForm.saved_info;
        if (r041 != null) goto L245;
    L247:
        fillNumber(null);
    L249:
        if (this.inputFields[8].length() != 0) goto L726;
        TLRPC.PaymentForm r042 = this.paymentForm;
        if (r042.invoice.phone_requested == false) goto L726;
        TLRPC.TL_paymentRequestedInfo r043 = r042.saved_info;
        if (r043 != null) goto L255;
    L735:
        TelephonyManager r044 = (TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone");     // Catch: Exception -> L260
        if (r044 == null) goto L262;
        String r68 = r044.getSimCountryIso().toUpperCase();     // Catch: Exception -> L260
    L263:
        if (r68 == null) goto L726;
        String r045 = (String) r3.get(r68);
        if (r045 == null) goto L726;
        if (this.countriesArray.indexOf(r045) == (-1)) goto L726;
        this.inputFields[8].setText(this.countriesMap.get(r045));
    L262:
        r68 = null;
    L260:
        e = move-exception;
        FileLog.e(e);
        goto L262
    L255:
        if (TextUtils.isEmpty(r043.phone) == true) goto L735;
    L726:
        return this.fragmentView;
    L245:
        if (TextUtils.isEmpty(r041.phone) == true) goto L247;
        fillNumber(this.paymentForm.saved_info.phone);
        goto L249
    L233:
        if (r25.phone_requested == true) goto L238;
        if (r25.email_requested == true) goto L238;
        r26 = 8;
        goto L239
    L211:
        if (r22.phone_requested == true) goto L216;
        if (r22.email_requested == true) goto L216;
        r24 = 8;
        goto L217
    L218:
        TextInfoPrivacyCell r046 = this.bottomCell[1];
        if (r046 == null) goto L230;
        TLRPC.TL_invoice r29 = this.paymentForm.invoice;
        if (r29.name_requested == false) goto L223;
    L228:
        int r210 = 0;
    L229:
        r046.setVisibility(r210);
        goto L230
    L223:
        if (r29.phone_requested == true) goto L228;
        if (r29.email_requested == true) goto L228;
        r210 = 8;
        goto L229
    L200:
        if (r038.email_requested == false) goto L202;
        this.inputFields[7].setImeOptions(268435462);
        goto L206
    L202:
        boolean r047 = r038.name_requested;
        EditTextBoldCursor[] r45 = this.inputFields;
        if (r047 == false) goto L205;
        r45[r272].setImeOptions(268435462);
        goto L206
    L205:
        r45[5].setImeOptions(268435462);
        goto L206
    L189:
        r2 = 8;
    L43:
        e = move-exception;
        FileLog.e(e);
        goto L49
    L269:
        int r284 = 3;
        if (r08 != 2) goto L426;
        if (this.paymentForm.native_params == null) goto L282;
        JSONObject r048 = new JSONObject(this.paymentForm.native_params.data);     // Catch: Exception -> L276
        String r46 = r048.optString("google_pay_public_key");     // Catch: Exception -> L276
        if (TextUtils.isEmpty(r46) == true) goto L278;
        this.googlePayPublicKey = r46;     // Catch: Exception -> L276
    L278:
        this.googlePayCountryCode = r048.optString("acquirer_bank_country");     // Catch: Exception -> L276
        this.googlePayParameters = r048.optJSONObject("gpay_parameters");     // Catch: Exception -> L276
    L276:
        e = move-exception;
        FileLog.e(e);
    L282:
        if (this.isWebView == true) goto L420;
        if (this.paymentFormMethod != null) goto L420;
        if (this.paymentForm.native_params == null) goto L311;
        JSONObject r049 = new JSONObject(this.paymentForm.native_params.data);     // Catch: Exception -> L302
        this.need_card_country = r049.getBoolean("need_country");     // Catch: Exception -> L291
    L727:
        this.need_card_postcode = r049.getBoolean("need_zip");     // Catch: Exception -> L294
    L729:
        this.need_card_name = r049.getBoolean("need_cardholder_name");     // Catch: Exception -> L297
    L299:
        if (r049.has("public_token") == false) goto L737;
        this.providerApiKey = r049.getString("public_token");     // Catch: Exception -> L302
    L307:
        this.initGooglePay = !r049.optBoolean("google_pay_hidden", false);     // Catch: Exception -> L302
        goto L311
    L737:
        this.providerApiKey = r049.getString("publishable_key");     // Catch: Exception -> L306
    L306:
        this.providerApiKey = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Exception -> L302
    L297:
        this.need_card_name = false;     // Catch: Exception -> L302
    L294:
        this.need_card_postcode = false;     // Catch: Exception -> L302
        goto L729
    L291:
        this.need_card_country = false;     // Catch: Exception -> L302
    L302:
        e = move-exception;
        FileLog.e(e);
    L311:
        if (this.initGooglePay == true) goto L313;
    L319:
        int r32 = 6;
        this.inputFields = new EditTextBoldCursor[6];
        int r050 = 0;
    L320:
        if (r050 >= r32) goto L411;
        if (r050 != 0) goto L325;
        this.headerCell[0] = new HeaderCell(r38, this.resourcesProvider);
        this.headerCell[0].setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        this.headerCell[0].setText(LocaleController.getString(R.string.PaymentCardTitle));
        this.linearLayout2.addView(this.headerCell[0], LayoutHelper.createLinear(-1, -2));
    L327:
        if (r050 != r284) goto L329;
    L335:
        boolean r33 = false;
    L336:
        FrameLayout r47 = new FrameLayout(r38);
        r47.setClipChildren(false);
        int r59 = Theme.key_windowBackgroundWhite;
        r47.setBackgroundColor(getThemedColor(r59));
        this.linearLayout2.addView(r47, LayoutHelper.createLinear(-1, 50));
        this.inputFields[r050] = new EditTextBoldCursor(r38);
        this.inputFields[r050].setTag(Integer.valueOf(r050));
        this.inputFields[r050].setTextSize(1, 16.0f);
        this.inputFields[r050].setHintTextColor(getThemedColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r69 = this.inputFields[r050];
        int r72 = Theme.key_windowBackgroundWhiteBlackText;
        r69.setTextColor(getThemedColor(r72));
        this.inputFields[r050].setBackgroundDrawable(null);
        this.inputFields[r050].setCursorColor(getThemedColor(r72));
        this.inputFields[r050].setCursorSize(AndroidUtilities.dp(20.0f));
        this.inputFields[r050].setCursorWidth(1.5f);
        if (r050 != 3) goto L340;
        this.inputFields[r050].setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        this.inputFields[r050].setInputType(130);
        this.inputFields[r050].setTypeface(Typeface.DEFAULT);
        this.inputFields[r050].setTransformationMethod(PasswordTransformationMethod.getInstance());
    L352:
        this.inputFields[r050].setImeOptions(268435461);
        if (r050 != 0) goto L355;
        this.inputFields[r050].setHint(LocaleController.getString(R.string.PaymentCardNumber));
    L371:
        if (r050 != 0) goto L374;
        this.inputFields[r050].addTextChangedListener(new AnonymousClass7(this));
    L376:
        this.inputFields[r050].setPadding(0, 0, 0, AndroidUtilities.dp(6.0f));
        EditTextBoldCursor r610 = this.inputFields[r050];
        if (LocaleController.isRTL == false) goto L379;
        int r73 = 5;
    L380:
        r610.setGravity(r73);
        r47.addView(this.inputFields[r050], LayoutHelper.createFrame(-1, -2.0f, 51, 21.0f, 12.0f, 21.0f, 6.0f));
        this.inputFields[r050].setOnEditorActionListener(new PaymentFormActivity$$ExternalSyntheticLambda26(this));
        if (r050 != 3) goto L384;
        this.sectionCell[0] = new ShadowSectionCell(r38, this.resourcesProvider);
        this.linearLayout2.addView(this.sectionCell[0], LayoutHelper.createLinear(-1, -2));
    L392:
        if (r33 == false) goto L395;
        AnonymousClass9 r34 = new AnonymousClass9(this, r38);
        r34.setBackgroundColor(getThemedColor(r59));
        this.dividers.add(r34);
        r47.addView(r34, new FrameLayout.LayoutParams(-1, 1, 83));
    L395:
        if (r050 != 4) goto L400;
        if (this.need_card_country == true) goto L400;
    L408:
        r47.setVisibility(8);
    L409:
        r050 = r050 + 1;
        r32 = 6;
        r284 = 3;
    L400:
        if (r050 != 5) goto L404;
        if (this.need_card_postcode == false) goto L408;
    L404:
        if (r050 != 2) goto L409;
        if (this.need_card_name == true) goto L409;
    L384:
        if (r050 != 5) goto L386;
        this.sectionCell[2] = new ShadowSectionCell(r38, this.resourcesProvider);
        this.linearLayout2.addView(this.sectionCell[2], LayoutHelper.createLinear(-1, -2));
        TextCheckCell r611 = new TextCheckCell(r38, this.resourcesProvider);
        this.checkCell1 = r611;
        r611.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.checkCell1.setTextAndCheck(LocaleController.getString(R.string.PaymentCardSavePaymentInformation), this.saveCardInfo, false);
        this.linearLayout2.addView(this.checkCell1, LayoutHelper.createLinear(-1, -2));
        this.checkCell1.setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda27(this));
        this.bottomCell[0] = new TextInfoPrivacyCell(r38, this.resourcesProvider);
        this.bottomCell[0].setBackgroundDrawable(Theme.getThemedDrawableByKey(r38, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        updateSavePaymentField();
        this.linearLayout2.addView(this.bottomCell[0], LayoutHelper.createLinear(-1, -2));
        goto L392
    L386:
        if (r050 != 0) goto L392;
        createGooglePayButton(r38);
        FrameLayout r612 = this.googlePayContainer;
        if (LocaleController.isRTL == false) goto L390;
        int r74 = 3;
    L391:
        r47.addView(r612, LayoutHelper.createFrame(-2, -2.0f, r74 | 16, 0.0f, 0.0f, 4.0f, 0.0f));
        goto L392
    L390:
        r74 = 5;
        goto L391
    L379:
        r73 = 3;
        goto L380
    L374:
        if (r050 != 1) goto L376;
        this.inputFields[r050].addTextChangedListener(new AnonymousClass8(this));
        goto L376
    L355:
        if (r050 != 1) goto L357;
        this.inputFields[r050].setHint(LocaleController.getString(R.string.PaymentCardExpireDate));
        goto L371
    L357:
        if (r050 != 2) goto L359;
        this.inputFields[r050].setHint(LocaleController.getString(R.string.PaymentCardName));
        goto L371
    L359:
        if (r050 != 3) goto L361;
        this.inputFields[r050].setHint(LocaleController.getString(R.string.PaymentCardCvv));
        goto L371
    L361:
        if (r050 != 4) goto L363;
        this.inputFields[r050].setHint(LocaleController.getString(R.string.PaymentShippingCountry));
        goto L371
    L363:
        if (r050 != 5) goto L371;
        this.inputFields[r050].setHint(LocaleController.getString(R.string.PaymentShippingZipPlaceholder));
        goto L371
    L340:
        if (r050 != 0) goto L343;
        this.inputFields[r050].setInputType(3);
        goto L352
    L343:
        if (r050 != 4) goto L346;
        this.inputFields[r050].setOnTouchListener(new PaymentFormActivity$$ExternalSyntheticLambda25(this));
        this.inputFields[r050].setInputType(0);
        goto L352
    L346:
        if (r050 != 1) goto L348;
        this.inputFields[r050].setInputType(16386);
        goto L352
    L348:
        EditTextBoldCursor[] r613 = this.inputFields;
        if (r050 != 2) goto L351;
        r613[r050].setInputType(4097);
        goto L352
    L351:
        r613[r050].setInputType(16385);
        goto L352
    L329:
        if (r050 == 5) goto L335;
        if (r050 == 4) goto L333;
    L334:
        r33 = true;
        goto L336
    L333:
        if (this.need_card_postcode == false) goto L335;
    L325:
        if (r050 != 4) goto L327;
        this.headerCell[1] = new HeaderCell(r38, this.resourcesProvider);
        this.headerCell[1].setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        this.headerCell[1].setText(LocaleController.getString(R.string.PaymentBillingAddress));
        this.linearLayout2.addView(this.headerCell[1], LayoutHelper.createLinear(-1, -2));
        goto L327
    L411:
        if (this.need_card_country == false) goto L413;
    L415:
        boolean r051 = this.need_card_postcode;
        EditTextBoldCursor[] r211 = this.inputFields;
        if (r051 == false) goto L418;
        r211[5].setImeOptions(268435462);
        goto L726
    L418:
        r211[3].setImeOptions(268435462);
        goto L726
    L413:
        if (this.need_card_postcode == true) goto L415;
        this.headerCell[1].setVisibility(8);
        this.sectionCell[0].setVisibility(8);
        goto L415
    L313:
        if (TextUtils.isEmpty(this.providerApiKey) == true) goto L317;
        if ("stripe".equals(this.paymentForm.native_provider) == false) goto L317;
    L318:
        initGooglePay(r38);
    L317:
        if (this.googlePayParameters == null) goto L319;
    L420:
        if (this.googlePayPublicKey == null) goto L422;
    L423:
        initGooglePay(r38);
    L424:
        createGooglePayButton(r38);
        this.linearLayout2.addView(this.googlePayContainer, LayoutHelper.createLinear(-1, 50));
        this.webviewLoading = true;
        showEditDoneProgress(true, true);
        this.progressView.setVisibility(0);
        this.doneItem.setEnabled(false);
        this.doneItem.getContentView().setVisibility(4);
        AndroidUtilities.checkAndroidTheme(r38, true);
        AnonymousClass5 r052 = new AnonymousClass5(this, r38);
        this.webView = r052;
        r052.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.getSettings().setSupportZoom(true);
        this.webView.getSettings().setBuiltInZoomControls(true);
        this.webView.getSettings().setDisplayZoomControls(false);
        this.webView.getSettings().setUseWideViewPort(true);
        this.webView.getSettings().setMixedContentMode(0);
        CookieManager.getInstance().setAcceptThirdPartyCookies(this.webView, true);
        this.webView.addJavascriptInterface(new TelegramWebviewProxy(this, null), "TelegramWebviewProxy");
        this.webView.setWebViewClient(new AnonymousClass6(this, r38));
        this.linearLayout2.addView(this.webView, LayoutHelper.createFrame(-1, -2.0f));
        this.sectionCell[2] = new ShadowSectionCell(r38, this.resourcesProvider);
        this.linearLayout2.addView(this.sectionCell[2], LayoutHelper.createLinear(-1, -2));
        TextCheckCell r053 = new TextCheckCell(r38, this.resourcesProvider);
        this.checkCell1 = r053;
        r053.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.checkCell1.setTextAndCheck(LocaleController.getString(R.string.PaymentCardSavePaymentInformation), this.saveCardInfo, false);
        this.linearLayout2.addView(this.checkCell1, LayoutHelper.createLinear(-1, -2));
        this.checkCell1.setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda24(this));
        this.bottomCell[0] = new TextInfoPrivacyCell(r38, this.resourcesProvider);
        this.bottomCell[0].setBackgroundDrawable(Theme.getThemedDrawableByKey(r38, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        updateSavePaymentField();
        this.linearLayout2.addView(this.bottomCell[0], LayoutHelper.createLinear(-1, -2));
        goto L726
    L422:
        if (this.googlePayParameters == null) goto L424;
    L426:
        if (r08 != 1) goto L444;
        ArrayList<TLRPC.TL_shippingOption> r054 = this.requestedInfo.shipping_options;
        if (r054 != null) goto L430;
        int r055 = 0;
    L431:
        this.radioCells = new RadioCell[r055];
        int r35 = 0;
    L432:
        if (r35 >= r055) goto L442;
        TLRPC.TL_shippingOption r48 = this.requestedInfo.shipping_options.get(r35);
        this.radioCells[r35] = new RadioCell(r38);
        this.radioCells[r35].setTag(Integer.valueOf(r35));
        this.radioCells[r35].setBackgroundDrawable(Theme.getSelectorDrawable(true));
        RadioCell r510 = this.radioCells[r35];
        String r49 = String.format("%s - %s", new Object[]{getTotalPriceString(r48.prices), r48.title});
        if (r35 != 0) goto L436;
        boolean r614 = true;
    L438:
        if (r35 == (r055 - 1)) goto L440;
        boolean r75 = true;
    L441:
        r510.setText(r49, r614, r75);
        this.radioCells[r35].setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda28(this));
        this.linearLayout2.addView(this.radioCells[r35]);
        r35 = r35 + 1;
        goto L432
    L440:
        r75 = false;
        goto L441
    L436:
        r614 = false;
        goto L438
    L442:
        this.bottomCell[0] = new TextInfoPrivacyCell(r38, this.resourcesProvider);
        this.bottomCell[0].setBackgroundDrawable(Theme.getThemedDrawableByKey(r38, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.linearLayout2.addView(this.bottomCell[0], LayoutHelper.createLinear(-1, -2));
        goto L726
    L430:
        r055 = r054.size();
        goto L431
    L444:
        if (r08 != 3) goto L496;
        int r76 = 2;
        this.inputFields = new EditTextBoldCursor[2];
        int r056 = 0;
    L446:
        if (r056 >= r76) goto L726;
        if (r056 != 0) goto L449;
        this.headerCell[0] = new HeaderCell(r38, this.resourcesProvider);
        this.headerCell[0].setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        this.headerCell[0].setText(LocaleController.getString(R.string.PaymentCardTitle));
        this.linearLayout2.addView(this.headerCell[0], LayoutHelper.createLinear(-1, -2));
    L449:
        FrameLayout r410 = new FrameLayout(r38);
        r410.setClipChildren(false);
        this.linearLayout2.addView(r410, LayoutHelper.createLinear(-1, 50));
        int r511 = Theme.key_windowBackgroundWhite;
        r410.setBackgroundColor(getThemedColor(r511));
        if (r056 == 1) goto L452;
        boolean r615 = true;
    L454:
        if (r615 == false) goto L466;
        if (r056 != 7) goto L460;
        if (this.paymentForm.invoice.phone_requested == true) goto L460;
    L458:
        r615 = false;
    L460:
        if (r056 != 6) goto L466;
        TLRPC.TL_invoice r77 = this.paymentForm.invoice;
        if (r77.phone_requested == true) goto L466;
        if (r77.email_requested == false) goto L458;
    L466:
        if (r615 == false) goto L468;
        AnonymousClass10 r616 = new AnonymousClass10(this, r38);
        r616.setBackgroundColor(getThemedColor(r511));
        this.dividers.add(r616);
        r410.addView(r616, new FrameLayout.LayoutParams(-1, 1, 83));
    L468:
        this.inputFields[r056] = new EditTextBoldCursor(r38);
        this.inputFields[r056].setTag(Integer.valueOf(r056));
        this.inputFields[r056].setTextSize(1, 16.0f);
        this.inputFields[r056].setHintTextColor(getThemedColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r512 = this.inputFields[r056];
        int r617 = Theme.key_windowBackgroundWhiteBlackText;
        r512.setTextColor(getThemedColor(r617));
        this.inputFields[r056].setBackgroundDrawable(null);
        this.inputFields[r056].setCursorColor(getThemedColor(r617));
        this.inputFields[r056].setCursorSize(AndroidUtilities.dp(20.0f));
        this.inputFields[r056].setCursorWidth(1.5f);
        EditTextBoldCursor[] r513 = this.inputFields;
        if (r056 != 0) goto L471;
        r513[r056].setOnTouchListener(new PaymentFormActivity$$ExternalSyntheticLambda29());
        this.inputFields[r056].setInputType(0);
    L472:
        this.inputFields[r056].setImeOptions(268435462);
        if (r056 != 0) goto L475;
        EditTextBoldCursor r514 = this.inputFields[r056];
        TLRPC.TL_paymentSavedCredentialsCard r618 = this.savedCredentialsCard;
        if (r618 != null) goto L481;
        String r619 = _UrlKt.FRAGMENT_ENCODE_SET;
    L482:
        r514.setText(r619);
    L483:
        this.inputFields[r056].setPadding(0, 0, 0, AndroidUtilities.dp(6.0f));
        EditTextBoldCursor r515 = this.inputFields[r056];
        if (LocaleController.isRTL == false) goto L486;
        int r620 = 5;
    L487:
        r515.setGravity(r620);
        r410.addView(this.inputFields[r056], LayoutHelper.createFrame(-1, -2.0f, 51, 21.0f, 12.0f, 21.0f, 6.0f));
        this.inputFields[r056].setOnEditorActionListener(new PaymentFormActivity$$ExternalSyntheticLambda5(this));
        if (r056 != 1) goto L494;
        this.bottomCell[0] = new TextInfoPrivacyCell(r38, this.resourcesProvider);
        TextInfoPrivacyCell r411 = this.bottomCell[0];
        int r516 = R.string.PaymentConfirmationMessage;
        TLRPC.TL_paymentSavedCredentialsCard r621 = this.savedCredentialsCard;
        if (r621 != null) goto L492;
        String r622 = _UrlKt.FRAGMENT_ENCODE_SET;
    L493:
        r411.setText(LocaleController.formatString("PaymentConfirmationMessage", r516, new Object[]{r622}));
        TextInfoPrivacyCell r412 = this.bottomCell[0];
        int r517 = R.drawable.greydivider;
        int r623 = Theme.key_windowBackgroundGrayShadow;
        r412.setBackgroundDrawable(Theme.getThemedDrawableByKey(r38, r517, r623));
        this.linearLayout2.addView(this.bottomCell[0], LayoutHelper.createLinear(-1, -2));
        this.settingsCell[0] = new TextSettingsCell(r38, this.resourcesProvider);
        this.settingsCell[0].setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.settingsCell[0].setText(LocaleController.getString(R.string.PaymentConfirmationNewCard), false);
        this.linearLayout2.addView(this.settingsCell[0], LayoutHelper.createLinear(-1, -2));
        this.settingsCell[0].setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda6(this));
        this.bottomCell[1] = new TextInfoPrivacyCell(r38, this.resourcesProvider);
        this.bottomCell[1].setBackgroundDrawable(Theme.getThemedDrawableByKey(r38, R.drawable.greydivider_bottom, r623));
        this.linearLayout2.addView(this.bottomCell[1], LayoutHelper.createLinear(-1, -2));
        goto L494
    L492:
        r622 = r621.title;
    L494:
        r056 = r056 + 1;
        r76 = 2;
        goto L446
    L486:
        r620 = 3;
        goto L487
    L481:
        r619 = r618.title;
        goto L482
    L475:
        if (r056 != 1) goto L483;
        this.inputFields[r056].setHint(LocaleController.getString(R.string.LoginPassword));
        this.inputFields[r056].requestFocus();
        goto L483
    L471:
        r513[r056].setInputType(129);
        this.inputFields[r056].setTypeface(Typeface.DEFAULT);
        goto L472
    L452:
        r615 = false;
        goto L454
    L496:
        if (r08 != 4) goto L498;
    L541:
        PaymentInfoCell r057 = new PaymentInfoCell(r38);
        this.paymentInfoCell = r057;
        r057.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        MessageObject r058 = this.messageObject;
        if (r058 == null) goto L544;
        this.paymentInfoCell.setInvoice((TLRPC.TL_messageMediaInvoice) r058.messageOwner.media, this.currentBotName);
    L552:
        this.linearLayout2.addView(this.paymentInfoCell, LayoutHelper.createLinear(-1, -2));
        this.sectionCell[0] = new ShadowSectionCell(r38, this.resourcesProvider);
        this.linearLayout2.addView(this.sectionCell[0], LayoutHelper.createLinear(-1, -2));
        ArrayList<TLRPC.TL_labeledPrice> r059 = new ArrayList(this.paymentForm.invoice.prices);
        this.prices = r059;
        TLRPC.TL_shippingOption r36 = this.shippingOption;
        if (r36 == null) goto L555;
        r059.addAll(r36.prices);
    L555:
        this.totalPrice = new String[1];
        int r060 = 0;
    L557:
        if (r060 >= this.prices.size()) goto L560;
        TLRPC.TL_labeledPrice r37 = this.prices.get(r060);
        TextPriceCell r413 = new TextPriceCell(r38);
        r413.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        r413.setTextAndValue(r37.label, LocaleController.getInstance().formatCurrencyString(r37.amount, this.paymentForm.invoice.currency), false);
        this.linearLayout2.addView(r413);
        r060 = r060 + 1;
        goto L557
    L560:
        if (this.currentStep == 5) goto L562;
    L564:
        TextPriceCell r061 = new TextPriceCell(r38);
        this.totalCell = r061;
        int r39 = Theme.key_windowBackgroundWhite;
        r061.setBackgroundColor(getThemedColor(r39));
        this.totalPrice[0] = getTotalPriceString(this.prices);
        this.totalCell.setTextAndValue(LocaleController.getString(R.string.PaymentTransactionTotal), this.totalPrice[0], true);
        float r84 = 14.0f;
        if (this.currentStep == 4) goto L567;
    L589:
        this.linearLayout2.addView(this.totalCell);
        this.sectionCell[2] = new ShadowSectionCell(r38, this.resourcesProvider);
        this.sectionCell[2].setBackgroundDrawable(Theme.getThemedDrawableByKey(r38, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.linearLayout2.addView(this.sectionCell[2], LayoutHelper.createLinear(-1, -2));
        this.detailSettingsCell[0] = new AnonymousClass13(this, r38);
        this.detailSettingsCell[0].setBackground(Theme.getSelectorDrawable(true));
        TextDetailSettingsCell r062 = this.detailSettingsCell[0];
        String r310 = this.cardName;
        if (r310 != null) goto L592;
    L594:
        String r311 = this.cardName;
    L595:
        r062.setTextAndValueAndIcon(r311, LocaleController.getString(R.string.PaymentCheckoutMethod), R.drawable.msg_payment_card, true);
        if (this.isCheckoutPreview == false) goto L598;
    L601:
        String r063 = this.cardName;
        if (r063 != null) goto L604;
    L606:
        int r064 = 8;
    L607:
        int r312 = r064;
    L608:
        this.detailSettingsCell[0].setVisibility(r312);
        this.linearLayout2.addView(this.detailSettingsCell[0]);
        if (this.currentStep != 4) goto L611;
        this.detailSettingsCell[0].setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda10(this));
    L611:
        int r065 = 0;
        TLRPC.User r85 = null;
    L613:
        if (r065 >= this.paymentForm.users.size()) goto L618;
        TLRPC.User r414 = this.paymentForm.users.get(r065);
        if (r414.id != this.paymentForm.provider_id) goto L617;
        r85 = r414;
    L617:
        r065 = r065 + 1;
        goto L613
    L618:
        char r232 = 1;
        this.detailSettingsCell[1] = new TextDetailSettingsCell(r38);
        this.detailSettingsCell[1].setBackground(Theme.getSelectorDrawable(true));
        if (r85 == null) goto L635;
        TextDetailSettingsCell r066 = this.detailSettingsCell[1];
        r11 = ContactsController.formatName(r85.first_name, r85.last_name);
        String r415 = LocaleController.getString(R.string.PaymentCheckoutProvider);
        int r518 = R.drawable.msg_payment_provider;
        TLRPC.TL_payments_validateRequestedInfo r624 = this.validateRequest;
        if (r624 == null) goto L628;
        TLRPC.TL_paymentRequestedInfo r625 = r624.info;
        if (r625 == null) goto L627;
        if (r625.shipping_address == null) goto L627;
    L632:
        boolean r626 = true;
    L634:
        r066.setTextAndValueAndIcon(r11, r415, r518, r626);
        r232 = 1;
        this.linearLayout2.addView(this.detailSettingsCell[1]);
    L627:
        if (this.shippingOption != null) goto L632;
    L628:
        TLRPC.TL_paymentRequestedInfo r627 = this.paymentForm.saved_info;
        if (r627 != null) goto L631;
    L633:
        r626 = false;
        goto L634
    L631:
        if (r627.shipping_address == null) goto L633;
    L635:
        TextDetailSettingsCell r067 = this.detailSettingsCell[r232];
        if (r85 == null) goto L638;
        int r416 = r312;
    L639:
        r067.setVisibility(r416);
        TLRPC.TL_payments_validateRequestedInfo r068 = this.validateRequest;
        if (r068 == null) goto L642;
    L647:
        if (r068 == null) goto L649;
        TLRPC.TL_paymentRequestedInfo r069 = r068.info;
    L650:
        this.detailSettingsCell[2] = new TextDetailSettingsCell(r38);
        this.detailSettingsCell[2].setVisibility(8);
        this.linearLayout2.addView(this.detailSettingsCell[2]);
        if (r069.shipping_address == null) goto L656;
        this.detailSettingsCell[2].setVisibility(0);
        int r417 = this.currentStep;
        TextDetailSettingsCell[] r519 = this.detailSettingsCell;
        if (r417 != 4) goto L655;
        r519[2].setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.detailSettingsCell[2].setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda11(this));
        goto L656
    L655:
        r519[2].setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
    L656:
        this.detailSettingsCell[3] = new TextDetailSettingsCell(r38);
        this.detailSettingsCell[3].setVisibility(8);
        this.linearLayout2.addView(this.detailSettingsCell[3]);
        if (r069.name == null) goto L662;
        this.detailSettingsCell[3].setVisibility(0);
        int r418 = this.currentStep;
        TextDetailSettingsCell[] r520 = this.detailSettingsCell;
        if (r418 != 4) goto L661;
        r520[3].setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.detailSettingsCell[3].setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda12(this));
        goto L662
    L661:
        r520[3].setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
    L662:
        this.detailSettingsCell[4] = new TextDetailSettingsCell(r38);
        this.detailSettingsCell[4].setVisibility(8);
        this.linearLayout2.addView(this.detailSettingsCell[4]);
        if (r069.phone == null) goto L668;
        this.detailSettingsCell[4].setVisibility(0);
        int r419 = this.currentStep;
        TextDetailSettingsCell[] r521 = this.detailSettingsCell;
        if (r419 != 4) goto L667;
        r521[4].setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.detailSettingsCell[4].setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda13(this));
        goto L668
    L667:
        r521[4].setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
    L668:
        this.detailSettingsCell[5] = new TextDetailSettingsCell(r38);
        this.detailSettingsCell[5].setVisibility(8);
        this.linearLayout2.addView(this.detailSettingsCell[5]);
        if (r069.email == null) goto L675;
        this.detailSettingsCell[5].setVisibility(0);
        int r420 = this.currentStep;
        TextDetailSettingsCell[] r522 = this.detailSettingsCell;
        if (r420 != 4) goto L673;
        r522[5].setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.detailSettingsCell[5].setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda14(this));
        goto L675
    L673:
        r522[5].setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
    L675:
        if (this.shippingOption == null) goto L677;
        this.detailSettingsCell[6] = new TextDetailSettingsCell(r38);
        this.detailSettingsCell[6].setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        this.detailSettingsCell[6].setTextAndValueAndIcon(this.shippingOption.title, LocaleController.getString(R.string.PaymentCheckoutShippingMethod), R.drawable.msg_payment_delivery, false);
        this.linearLayout2.addView(this.detailSettingsCell[6]);
    L677:
        setAddressFields(r069);
    L679:
        if (this.currentStep != 4) goto L711;
        this.recurrentAccepted = !this.isCheckoutPreview;
        if ((this.invoiceInput instanceof TLRPC.TL_inputInvoiceStars) == false) goto L683;
        this.recurrentAccepted = true;
    L683:
        this.bottomLayout = new BottomFrameLayout(this, r38, this.paymentForm);
        View r070 = new View(r38);
        r070.setBackground(Theme.getSelectorDrawable(getThemedColor(Theme.key_listSelector), false));
        this.bottomLayout.addView(r070, LayoutHelper.createFrame(-1, -1.0f));
        r13.addView(this.bottomLayout, LayoutHelper.createFrame(-1, 48, 80));
        this.bottomLayout.setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda16(this, r11));
        TextView r071 = new TextView(r38);
        this.payTextView = r071;
        int r421 = Theme.key_contacts_inviteText;
        r071.setTextColor(getThemedColor(r421));
        this.payTextView.setText(LocaleController.formatString(R.string.PaymentCheckoutPay, new Object[]{this.totalPrice[0]}));
        this.payTextView.setTextSize(1, 14.0f);
        this.payTextView.setGravity(17);
        this.payTextView.setTypeface(AndroidUtilities.bold());
        this.bottomLayout.addView(this.payTextView, LayoutHelper.createFrame(-1, -1.0f));
        ContextProgressView r072 = new ContextProgressView(r38, 0);
        this.progressViewButton = r072;
        r072.setVisibility(4);
        int r073 = getThemedColor(r421);
        this.progressViewButton.setColors(805306367 & r073, r073);
        this.bottomLayout.addView(this.progressViewButton, LayoutHelper.createFrame(-1, -1.0f));
        this.bottomLayout.setChecked(this.recurrentAccepted, false);
        TextView r074 = this.payTextView;
        if (this.recurrentAccepted == true) goto L686;
        float r422 = 0.8f;
    L687:
        r074.setAlpha(r422);
        this.doneItem.setEnabled(false);
        this.doneItem.getContentView().setVisibility(4);
        AndroidUtilities.checkAndroidTheme(r38, true);
        AnonymousClass18 r075 = new AnonymousClass18(this, r38);     // Catch: Exception -> L690
        this.webView = r075;     // Catch: Exception -> L690
        r075.setBackgroundColor(-1);     // Catch: Exception -> L690
        this.webView.getSettings().setJavaScriptEnabled(true);     // Catch: Exception -> L690
        this.webView.getSettings().setDomStorageEnabled(true);     // Catch: Exception -> L690
        this.webView.getSettings().setSupportZoom(true);     // Catch: Exception -> L690
        this.webView.getSettings().setBuiltInZoomControls(true);     // Catch: Exception -> L690
        this.webView.getSettings().setDisplayZoomControls(false);     // Catch: Exception -> L690
        this.webView.getSettings().setUseWideViewPort(true);     // Catch: Exception -> L690
        this.webView.getSettings().setMixedContentMode(0);     // Catch: Exception -> L690
        CookieManager.getInstance().setAcceptThirdPartyCookies(this.webView, true);     // Catch: Exception -> L690
        this.webView.setWebViewClient(new AnonymousClass19(this, r38));     // Catch: Exception -> L690
    L692:
        this.recurrentAcceptCell = null;
        if (this.paymentForm.invoice.terms_url == null) goto L708;
        RecurrentPaymentsAcceptCell r076 = new RecurrentPaymentsAcceptCell(r38, getResourceProvider());
        this.recurrentAcceptCell = r076;
        r076.setChecked(this.recurrentAccepted);
        if (TextUtils.isEmpty(this.currentBotName) == false) goto L700;
        String r077 = LocaleController.getString(R.string.PaymentCheckoutAcceptRecurrentFee);
        SpannableStringBuilder r523 = new SpannableStringBuilder(r077);
        int r628 = r077.indexOf(42);
        int r423 = r077.lastIndexOf(42);
        if (r628 == (-1)) goto L707;
        if (r423 == (-1)) goto L707;
        SpannableString r78 = new SpannableString(r077.substring(r628 + 1, r423));
        r78.setSpan(new URLSpanNoUnderline(this.paymentForm.invoice.terms_url), 0, r78.length(), 33);
        int r424 = r423 + 1;
        r523.replace(r628, r424, r78);
        r077.substring(0, r628);
        r78.toString();
        r077.substring(r424);
    L707:
        this.recurrentAcceptCell.setText(r523);
        this.recurrentAcceptCell.setBackground(Theme.createSelectorWithBackgroundDrawable(getThemedColor(Theme.key_windowBackgroundWhite), getThemedColor(Theme.key_listSelector)));
        this.recurrentAcceptCell.setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda17(this));
        r13.addView(this.recurrentAcceptCell, LayoutHelper.createFrame(-1, -2.0f, 80, 0.0f, 0.0f, 0.0f, 48.0f));
        goto L708
    L700:
        String r078 = LocaleController.getString(R.string.PaymentCheckoutAcceptRecurrent);
        r523 = new SpannableStringBuilder(r078);
        int r629 = r078.indexOf(42);
        int r425 = r078.lastIndexOf(42);
        if (r629 == (-1)) goto L704;
        if (r425 == (-1)) goto L704;
        SpannableString r79 = new SpannableString(r078.substring(r629 + 1, r425));
        r79.setSpan(new URLSpanNoUnderline(this.paymentForm.invoice.terms_url), 0, r79.length(), 33);
        int r426 = r425 + 1;
        r523.replace(r629, r426, r79);
        r078 = r078.substring(0, r629) + r79 + r078.substring(r426);
    L704:
        int r079 = r078.indexOf("%1$s");
        if (r079 == (-1)) goto L707;
        r523.replace(r079, r079 + 4, this.currentBotName);
        r523.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r079, this.currentBotName.length() + r079, 33);
    L708:
        WebView r080 = this.webView;
        if (r080 == null) goto L711;
        r13.addView(r080, LayoutHelper.createFrame(-1, -1.0f));
        this.webView.setVisibility(8);
    L690:
        e = move-exception;
        FileLog.e(e);
        goto L692
    L686:
        r422 = 1.0f;
    L711:
        this.sectionCell[1] = new ShadowSectionCell(r38, this.resourcesProvider);
        this.sectionCell[1].setBackgroundDrawable(Theme.getThemedDrawableByKey(r38, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        if (r312 != 0) goto L714;
    L722:
        char r233 = 1;
    L724:
        this.linearLayout2.addView(this.sectionCell[r233], LayoutHelper.createLinear(-1, -2));
        goto L726
    L714:
        if (this.currentStep != 4) goto L722;
        if (this.validateRequest != null) goto L722;
        TLRPC.PaymentForm r081 = this.paymentForm;
        if (r081 != null) goto L720;
    L723:
        r233 = 1;
        this.sectionCell[1].setVisibility(r312);
        goto L724
    L720:
        if (r081.saved_info != null) goto L722;
    L649:
        r069 = this.paymentForm.saved_info;
        goto L650
    L642:
        if (this.isCheckoutPreview == false) goto L679;
        TLRPC.PaymentForm r427 = this.paymentForm;
        if (r427 == null) goto L679;
        if (r427.saved_info == null) goto L679;
    L638:
        r416 = 8;
        goto L639
    L604:
        if (r063.length() <= 1) goto L606;
        r064 = 0;
        goto L607
    L598:
        if (this.allowUnregistered == true) goto L601;
        r312 = 0;
        goto L608
    L592:
        if (r310.length() <= 1) goto L594;
        r311 = this.cardName.substring(0, 1).toUpperCase() + this.cardName.substring(1);
        goto L595
    L567:
        if ((this.paymentForm.invoice.flags & 256) == 0) goto L589;
        FrameLayout r082 = new FrameLayout(r38);
        r082.setClipChildren(false);
        r082.setBackgroundColor(getThemedColor(r39));
        LinearLayout r428 = this.linearLayout2;
        if (this.paymentForm.invoice.suggested_tip_amounts.isEmpty() == false) goto L571;
        int r524 = 40;
    L572:
        r428.addView(r082, LayoutHelper.createLinear(-1, r524));
        r082.setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda7(this));
        TextPriceCell r429 = new TextPriceCell(r38);
        r429.setBackgroundColor(getThemedColor(r39));
        r429.setTextAndValue(LocaleController.getString(R.string.PaymentTipOptional), _UrlKt.FRAGMENT_ENCODE_SET, false);
        r082.addView(r429);
        this.inputFields = new EditTextBoldCursor[]{new EditTextBoldCursor(r38)};
        this.inputFields[0].setTag(0);
        this.inputFields[0].setTextSize(1, 16.0f);
        EditTextBoldCursor r313 = this.inputFields[0];
        int r430 = Theme.key_windowBackgroundWhiteGrayText2;
        r313.setHintTextColor(getThemedColor(r430));
        this.inputFields[0].setTextColor(getThemedColor(r430));
        this.inputFields[0].setBackgroundDrawable(null);
        this.inputFields[0].setCursorColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        this.inputFields[0].setCursorSize(AndroidUtilities.dp(20.0f));
        this.inputFields[0].setCursorWidth(1.5f);
        this.inputFields[0].setInputType(3);
        this.inputFields[0].setImeOptions(268435462);
        this.inputFields[0].setHint(LocaleController.getInstance().formatCurrencyString(0, this.paymentForm.invoice.currency));
        this.inputFields[0].setPadding(0, 0, 0, AndroidUtilities.dp(6.0f));
        EditTextBoldCursor r314 = this.inputFields[0];
        if (LocaleController.isRTL == false) goto L575;
        int r431 = 3;
    L576:
        r314.setGravity(r431);
        r082.addView(this.inputFields[0], LayoutHelper.createFrame(-1, -2.0f, 51, 21.0f, 9.0f, 21.0f, 1.0f));
        this.inputFields[0].addTextChangedListener(new AnonymousClass11(this));
        this.inputFields[0].setOnEditorActionListener(new PaymentFormActivity$$ExternalSyntheticLambda8());
        this.inputFields[0].requestFocus();
        if (this.paymentForm.invoice.suggested_tip_amounts.isEmpty() == true) goto L589;
        HorizontalScrollView r93 = new HorizontalScrollView(r38);
        r93.setHorizontalScrollBarEnabled(false);
        r93.setVerticalScrollBarEnabled(false);
        r93.setClipToPadding(false);
        r93.setPadding(AndroidUtilities.dp(21.0f), 0, AndroidUtilities.dp(21.0f), 0);
        r93.setFillViewport(true);
        r082.addView(r93, LayoutHelper.createFrame(-1, 30.0f, 51, 0.0f, 44.0f, 0.0f, 0.0f));
        int[] r432 = new int[1];
        int[] r525 = new int[1];
        int r315 = this.paymentForm.invoice.suggested_tip_amounts.size();
        AnonymousClass12 r083 = new AnonymousClass12(this, r38, r315, r432, r525);
        this.tipLayout = r083;
        r083.setOrientation(0);
        r93.addView(this.tipLayout, LayoutHelper.createScroll(-1, 30, 51));
        int r084 = getThemedColor(Theme.key_contacts_inviteBackground);
        int r630 = 0;
    L579:
        if (r630 >= r315) goto L589;
        boolean r94 = LocaleController.isRTL;
        TLRPC.PaymentForm r102 = this.paymentForm;
        if (r94 == false) goto L583;
        long r95 = r102.invoice.suggested_tip_amounts.get((r315 - r630) - 1).longValue();
    L584:
        String r12 = LocaleController.getInstance().formatCurrencyString(r95, this.paymentForm.invoice.currency);
        TextView r15 = new TextView(r38);
        r15.setTextSize(1, r84);
        r15.setTypeface(AndroidUtilities.bold());
        r15.setLines(1);
        r15.setTag(Long.valueOf(r95));
        r15.setMaxLines(1);
        r15.setText(r12);
        r15.setPadding(AndroidUtilities.dp(15.0f), 0, AndroidUtilities.dp(15.0f), 0);
        r15.setTextColor(getThemedColor(Theme.key_chats_secretName));
        r15.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(15.0f), 536870911 & r084));
        r15.setSingleLine(true);
        r15.setGravity(17);
        LinearLayout r710 = this.tipLayout;
        if (r630 == (r315 - 1)) goto L587;
        int r352 = 9;
    L588:
        r710.addView(r15, LayoutHelper.createLinear(-2, -1, 19, 0, 0, r352, 0));
        r15.setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda9(this, r15, r95));
        int r711 = ((int) Math.ceil(r15.getPaint().measureText(r12))) + AndroidUtilities.dp(30.0f);
        r15.setTag(R.id.width_tag, Integer.valueOf(r711));
        r432[0] = Math.max(r432[0], r711);
        r525[0] = r525[0] + r711;
        r630 = r630 + 1;
        r84 = 14.0f;
        goto L579
    L587:
        r352 = 0;
        goto L588
    L583:
        r95 = r102.invoice.suggested_tip_amounts.get(r630).longValue();
        goto L584
    L575:
        r431 = 5;
        goto L576
    L571:
        r524 = 78;
        goto L572
    L562:
        if (this.tipAmount == null) goto L564;
        TextPriceCell r085 = new TextPriceCell(r38);
        r085.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        r085.setTextAndValue(LocaleController.getString(R.string.PaymentTip), LocaleController.getInstance().formatCurrencyString(this.tipAmount.longValue(), this.paymentForm.invoice.currency), false);
        this.linearLayout2.addView(r085);
        goto L564
    L544:
        TLRPC.PaymentReceipt r086 = this.paymentReceipt;
        if (r086 == null) goto L548;
        this.paymentInfoCell.setReceipt(r086, this.currentBotName);
        goto L552
    L548:
        if (this.invoiceSlug == null) goto L550;
    L551:
        PaymentInfoCell r087 = this.paymentInfoCell;
        TLRPC.PaymentForm r316 = this.paymentForm;
        r087.setInfo(r316.title, r316.description, r316.photo, this.currentBotName, r316);
        goto L552
    L550:
        if (this.invoiceInput == null) goto L552;
    L498:
        if (r08 == 5) goto L541;
        if (r08 != 6) goto L726;
        EditTextSettingsCell r088 = new EditTextSettingsCell(r38);
        this.codeFieldCell = r088;
        r088.setTextAndHint(_UrlKt.FRAGMENT_ENCODE_SET, LocaleController.getString(R.string.PasswordCode), false);
        this.codeFieldCell.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        EditTextBoldCursor r089 = this.codeFieldCell.getTextView();
        r089.setInputType(3);
        r089.setImeOptions(6);
        r089.setOnEditorActionListener(new PaymentFormActivity$$ExternalSyntheticLambda18(this));
        r089.addTextChangedListener(new AnonymousClass20(this));
        this.linearLayout2.addView(this.codeFieldCell, LayoutHelper.createLinear(-1, -2));
        this.bottomCell[2] = new TextInfoPrivacyCell(r38, this.resourcesProvider);
        this.bottomCell[2].setBackgroundDrawable(Theme.getThemedDrawableByKey(r38, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
        this.linearLayout2.addView(this.bottomCell[2], LayoutHelper.createLinear(-1, -2));
        this.settingsCell[1] = new TextSettingsCell(r38, this.resourcesProvider);
        this.settingsCell[1].setBackgroundDrawable(Theme.getSelectorDrawable(true));
        TextSettingsCell r090 = this.settingsCell[1];
        int r433 = Theme.key_windowBackgroundWhiteBlackText;
        r090.setTag(Integer.valueOf(r433));
        this.settingsCell[1].setTextColor(getThemedColor(r433));
        this.settingsCell[1].setText(LocaleController.getString(R.string.ResendCode), true);
        this.linearLayout2.addView(this.settingsCell[1], LayoutHelper.createLinear(-1, -2));
        this.settingsCell[1].setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda19(this));
        this.settingsCell[0] = new TextSettingsCell(r38, this.resourcesProvider);
        this.settingsCell[0].setBackgroundDrawable(Theme.getSelectorDrawable(true));
        TextSettingsCell r091 = this.settingsCell[0];
        int r434 = Theme.key_text_RedRegular;
        r091.setTag(Integer.valueOf(r434));
        this.settingsCell[0].setTextColor(getThemedColor(r434));
        this.settingsCell[0].setText(LocaleController.getString(R.string.AbortPassword), false);
        this.linearLayout2.addView(this.settingsCell[0], LayoutHelper.createLinear(-1, -2));
        this.settingsCell[0].setOnClickListener(new PaymentFormActivity$$ExternalSyntheticLambda20(this));
        int r712 = 3;
        this.inputFields = new EditTextBoldCursor[3];
        int r092 = 0;
    L503:
        if (r092 >= r712) goto L540;
        if (r092 != 0) goto L507;
        this.headerCell[0] = new HeaderCell(r38, this.resourcesProvider);
        this.headerCell[0].setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        this.headerCell[0].setText(LocaleController.getString(R.string.PaymentPasswordTitle));
        this.linearLayout2.addView(this.headerCell[0], LayoutHelper.createLinear(-1, -2));
    L509:
        FrameLayout r435 = new FrameLayout(r38);
        r435.setClipChildren(false);
        this.linearLayout2.addView(r435, LayoutHelper.createLinear(-1, 50));
        int r526 = Theme.key_windowBackgroundWhite;
        r435.setBackgroundColor(getThemedColor(r526));
        if (r092 != 0) goto L513;
        AnonymousClass21 r713 = new AnonymousClass21(this, r38);
        r713.setBackgroundColor(getThemedColor(r526));
        this.dividers.add(r713);
        r435.addView(r713, new FrameLayout.LayoutParams(-1, 1, 83));
    L513:
        this.inputFields[r092] = new EditTextBoldCursor(r38);
        this.inputFields[r092].setTag(Integer.valueOf(r092));
        this.inputFields[r092].setTextSize(1, 16.0f);
        this.inputFields[r092].setHintTextColor(getThemedColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r527 = this.inputFields[r092];
        int r714 = Theme.key_windowBackgroundWhiteBlackText;
        r527.setTextColor(getThemedColor(r714));
        this.inputFields[r092].setBackgroundDrawable(null);
        this.inputFields[r092].setCursorColor(getThemedColor(r714));
        this.inputFields[r092].setCursorSize(AndroidUtilities.dp(20.0f));
        this.inputFields[r092].setCursorWidth(1.5f);
        if (r092 != 0) goto L516;
    L519:
        this.inputFields[r092].setInputType(129);
        this.inputFields[r092].setTypeface(Typeface.DEFAULT);
        this.inputFields[r092].setImeOptions(268435461);
    L520:
        if (r092 != 0) goto L522;
        this.inputFields[r092].setHint(LocaleController.getString(R.string.PaymentPasswordEnter));
        this.inputFields[r092].requestFocus();
    L529:
        this.inputFields[r092].setPadding(0, 0, 0, AndroidUtilities.dp(6.0f));
        EditTextBoldCursor r528 = this.inputFields[r092];
        if (LocaleController.isRTL == false) goto L532;
        int r103 = 5;
    L533:
        r528.setGravity(r103);
        r435.addView(this.inputFields[r092], LayoutHelper.createFrame(-1, -2.0f, 51, 21.0f, 12.0f, 21.0f, 6.0f));
        this.inputFields[r092].setOnEditorActionListener(new PaymentFormActivity$$ExternalSyntheticLambda21(this));
        if (r092 != 1) goto L537;
        this.bottomCell[0] = new TextInfoPrivacyCell(r38, this.resourcesProvider);
        this.bottomCell[0].setText(LocaleController.getString(R.string.PaymentPasswordInfo));
        this.bottomCell[0].setBackgroundDrawable(Theme.getThemedDrawableByKey(r38, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
        this.linearLayout2.addView(this.bottomCell[0], LayoutHelper.createLinear(-1, -2));
    L539:
        r092 = r092 + 1;
        r712 = 3;
        goto L503
    L537:
        if (r092 != 2) goto L539;
        this.bottomCell[1] = new TextInfoPrivacyCell(r38, this.resourcesProvider);
        this.bottomCell[1].setText(LocaleController.getString(R.string.PaymentPasswordEmailInfo));
        this.bottomCell[1].setBackgroundDrawable(Theme.getThemedDrawableByKey(r38, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.linearLayout2.addView(this.bottomCell[1], LayoutHelper.createLinear(-1, -2));
        goto L539
    L532:
        r103 = 3;
        goto L533
    L522:
        if (r092 != 1) goto L524;
        this.inputFields[r092].setHint(LocaleController.getString(R.string.PaymentPasswordReEnter));
        goto L529
    L524:
        if (r092 != 2) goto L529;
        this.inputFields[r092].setHint(LocaleController.getString(R.string.PaymentPasswordEmail));
        goto L529
    L516:
        if (r092 == 1) goto L519;
        this.inputFields[r092].setInputType(33);
        this.inputFields[r092].setImeOptions(268435462);
        goto L520
    L507:
        if (r092 != 2) goto L509;
        this.headerCell[1] = new HeaderCell(r38, this.resourcesProvider);
        this.headerCell[1].setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        this.headerCell[1].setText(LocaleController.getString(R.string.PaymentPasswordEmailTitle));
        this.linearLayout2.addView(this.headerCell[1], LayoutHelper.createLinear(-1, -2));
        goto L509
    L540:
        updatePasswordFields();
    L30:
        this.doneItem = r04.addItemWithWidth(1, R.drawable.ic_ab_done, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        ContextProgressView r093 = new ContextProgressView(r38, 1);
        this.progressView = r093;
        r093.setAlpha(0.0f);
        this.progressView.setScaleX(0.1f);
        this.progressView.setScaleY(0.1f);
        this.progressView.setVisibility(4);
        this.doneItem.addView(this.progressView, LayoutHelper.createFrame(-1, -1.0f));
        goto L31
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        if (r2 != NotificationCenter.twoStepPasswordChanged) goto L7;
        TLRPC.PaymentForm r22 = this.paymentForm;
        r22.password_missing = false;
        r22.can_save_credentials = true;
        updateSavePaymentField();
        return;
    L7:
        if (r2 != NotificationCenter.didRemoveTwoStepPassword) goto L11;
        TLRPC.PaymentForm r23 = this.paymentForm;
        r23.password_missing = true;
        r23.can_save_credentials = false;
        updateSavePaymentField();
        return;
    L11:
        if (r2 != NotificationCenter.paymentFinished) goto L14;
        this.paymentStatusSent = true;
        removeSelfFromStack();
        return;
    }

    @SuppressLint({"HardwareIds"})
    public void fillNumber(String r8) {
        TelephonyManager r0 = (TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone");     // Catch: Exception -> L32
        if (r8 != null) goto L9;
        if (r0.getSimState() != 1) goto L7;
        return;
    L7:
        if (r0.getPhoneType() != 0) goto L9;
        return;
    L9:
        if (getParentActivity().checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) goto L11;
        boolean r2 = true;
    L12:
        if (r8 != null) goto L14;
        if (r2 == true) goto L14;
        return;
    L14:
        if (r8 != null) goto L17;
        r8 = PhoneFormat.stripExceptNumbers(r0.getLine1Number());     // Catch: Exception -> L32
    L17:
        if (TextUtils.isEmpty(r8) == true) goto L42;
        int r22 = 4;
        String r4 = null;
        if (r8.length() > 4) goto L21;
    L29:
        if (r4 == null) goto L43;
        this.inputFields[9].setText(r4);     // Catch: Exception -> L32
        EditTextBoldCursor r7 = this.inputFields[9];     // Catch: Exception -> L32
        r7.setSelection(r7.length());     // Catch: Exception -> L32
        return;
    L43:
        return;
    L21:
        if (r22 < 1) goto L26;
        String r5 = r8.substring(0, r22);     // Catch: Exception -> L32
        if (this.codesMap.get(r5) != null) goto L24;
        r22 = r22 - 1;
        goto L21
    L24:
        r4 = r8.substring(r22);     // Catch: Exception -> L32
        this.inputFields[8].setText(r5);     // Catch: Exception -> L32
        boolean r23 = true;
    L27:
        if (r23 == true) goto L29;
        r4 = r8.substring(1);     // Catch: Exception -> L32
        this.inputFields[8].setText(r8.substring(0, 1));     // Catch: Exception -> L32
        goto L29
    L26:
        r23 = false;
        goto L27
    L42:
        return;
    L11:
        r2 = false;
    L32:
        e = move-exception;
        FileLog.e(e);
    }

    public Optional<JSONObject> getIsReadyToPayRequest() {
        JSONObject r0 = getBaseRequest();     // Catch: JSONException -> L4
        r0.put("allowedPaymentMethods", new JSONArray().put(getBaseCardPaymentMethod()));     // Catch: JSONException -> L4
        return Optional.of(r0);
    L5:
        return Optional.empty();
    }

    public int getOtherSameFragmentDiff() {
        INavigationLayout r0 = this.parentLayout;
        int r1 = 0;
        if (r0 != null) goto L5;
        return 0;
    L5:
        if (r0.getFragmentStack() == null) goto L23;
        int r02 = this.parentLayout.getFragmentStack().indexOf(this);
        if (r02 != (-1)) goto L11;
        r02 = this.parentLayout.getFragmentStack().size();
    L11:
        if (r1 >= this.parentLayout.getFragmentStack().size()) goto L16;
        if ((this.parentLayout.getFragmentStack().get(r1) instanceof PaymentFormActivity) == true) goto L18;
        r1 = r1 + 1;
    L18:
        return r1 - r02;
    L16:
        r1 = r02;
        goto L18
    L23:
        return 0;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public Theme.ResourcesProvider getResourceProvider() {
        return this.resourcesProvider;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        ActionBar r4 = this.actionBar;
        int r5 = ThemeDescription.FLAG_BACKGROUND;
        int r10 = Theme.key_actionBarDefault;
        r1.add(new ThemeDescription(r4, r5, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.scrollView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCH, null, null, null, null, Theme.key_actionBarDefaultSearch));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER, null, null, null, null, Theme.key_actionBarDefaultSearchPlaceholder));
        r1.add(new ThemeDescription(this.linearLayout2, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        ContextProgressView r52 = this.progressView;
        int r11 = Theme.key_contextProgressInner2;
        r1.add(new ThemeDescription(r52, 0, null, null, null, null, r11));
        ContextProgressView r13 = this.progressView;
        int r19 = Theme.key_contextProgressOuter2;
        r1.add(new ThemeDescription(r13, 0, null, null, null, null, r19));
        r1.add(new ThemeDescription(this.progressViewButton, 0, null, null, null, null, r11));
        r1.add(new ThemeDescription(this.progressViewButton, 0, null, null, null, null, r19));
        if (this.inputFields == null) goto L8;
        int r2 = 0;
    L6:
        if (r2 >= this.inputFields.length) goto L10;
        r1.add(new ThemeDescription((View) this.inputFields[r2].getParent(), ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.inputFields[r2], ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.inputFields[r2], ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        r2 = r2 + 1;
    L10:
        if (this.radioCells == null) goto L15;
        int r22 = 0;
    L13:
        if (r22 >= this.radioCells.length) goto L16;
        r1.add(new ThemeDescription(this.radioCells[r22], ThemeDescription.FLAG_SELECTORWHITE, null, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.radioCells[r22], ThemeDescription.FLAG_SELECTORWHITE, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.radioCells[r22], 0, new Class[]{RadioCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.radioCells[r22], ThemeDescription.FLAG_CHECKBOX, new Class[]{RadioCell.class}, new String[]{"radioButton"}, null, null, null, Theme.key_radioBackground));
        r1.add(new ThemeDescription(this.radioCells[r22], ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{RadioCell.class}, new String[]{"radioButton"}, null, null, null, Theme.key_radioBackgroundChecked));
        r22 = r22 + 1;
    L16:
        int r23 = 0;
    L18:
        if (r23 >= this.headerCell.length) goto L20;
        r1.add(new ThemeDescription(this.headerCell[r23], ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.headerCell[r23], 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r23 = r23 + 1;
        goto L18
    L20:
        int r24 = 0;
    L22:
        if (r24 >= this.sectionCell.length) goto L24;
        r1.add(new ThemeDescription(this.sectionCell[r24], ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r24 = r24 + 1;
        goto L22
    L24:
        int r25 = 0;
    L26:
        if (r25 >= this.bottomCell.length) goto L28;
        r1.add(new ThemeDescription(this.bottomCell[r25], ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(this.bottomCell[r25], 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.bottomCell[r25], ThemeDescription.FLAG_LINKCOLOR, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteLinkText));
        r25 = r25 + 1;
        goto L26
    L28:
        int r26 = 0;
    L30:
        if (r26 >= this.dividers.size()) goto L32;
        r1.add(new ThemeDescription(this.dividers.get(r26), ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        r26 = r26 + 1;
        goto L30
    L32:
        EditTextSettingsCell r8 = this.codeFieldCell;
        int r9 = ThemeDescription.FLAG_BACKGROUND;
        int r17 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r8, r9, null, null, null, null, r17));
        int r16 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.codeFieldCell, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{EditTextSettingsCell.class}, new String[]{"textView"}, null, null, null, r16));
        r1.add(new ThemeDescription(this.codeFieldCell, ThemeDescription.FLAG_HINTTEXTCOLOR, new Class[]{EditTextSettingsCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        r1.add(new ThemeDescription(this.textView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.checkCell1, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, r16));
        r1.add(new ThemeDescription(this.checkCell1, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrack));
        r1.add(new ThemeDescription(this.checkCell1, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrackChecked));
        r1.add(new ThemeDescription(this.checkCell1, ThemeDescription.FLAG_SELECTORWHITE, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.checkCell1, ThemeDescription.FLAG_SELECTORWHITE, null, null, null, null, Theme.key_listSelector));
        int r27 = 0;
    L34:
        if (r27 >= this.settingsCell.length) goto L36;
        r1.add(new ThemeDescription(this.settingsCell[r27], ThemeDescription.FLAG_SELECTORWHITE, null, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.settingsCell[r27], ThemeDescription.FLAG_SELECTORWHITE, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.settingsCell[r27], 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r27 = r27 + 1;
        goto L34
    L36:
        r1.add(new ThemeDescription(this.payTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlueText6));
        int r15 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextPriceCell.class}, null, null, null, r15));
        int r242 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextPriceCell.class}, new String[]{"textView"}, null, null, null, r242));
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextPriceCell.class}, new String[]{"valueTextView"}, null, null, null, r242));
        int r243 = Theme.key_windowBackgroundWhiteGrayText2;
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextPriceCell.class}, new String[]{"textView"}, null, null, null, r243));
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextPriceCell.class}, new String[]{"valueTextView"}, null, null, null, r243));
        r1.add(new ThemeDescription(this.detailSettingsCell[0], ThemeDescription.FLAG_SELECTORWHITE, null, null, null, null, r15));
        r1.add(new ThemeDescription(this.detailSettingsCell[0], ThemeDescription.FLAG_SELECTORWHITE, null, null, null, null, Theme.key_listSelector));
        int r28 = 1;
    L38:
        if (r28 >= this.detailSettingsCell.length) goto L40;
        r1.add(new ThemeDescription(this.detailSettingsCell[r28], ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.detailSettingsCell[r28], 0, new Class[]{TextDetailSettingsCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.detailSettingsCell[r28], 0, new Class[]{TextDetailSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r28 = r28 + 1;
        goto L38
    L40:
        PaymentInfoCell r82 = this.paymentInfoCell;
        int r92 = ThemeDescription.FLAG_BACKGROUND;
        int r172 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r82, r92, null, null, null, null, r172));
        int r162 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.paymentInfoCell, 0, new Class[]{PaymentInfoCell.class}, new String[]{"nameTextView"}, null, null, null, r162));
        r1.add(new ThemeDescription(this.paymentInfoCell, 0, new Class[]{PaymentInfoCell.class}, new String[]{"detailTextView"}, null, null, null, r162));
        r1.add(new ThemeDescription(this.paymentInfoCell, 0, new Class[]{PaymentInfoCell.class}, new String[]{"detailExTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r1.add(new ThemeDescription(this.bottomLayout, ThemeDescription.FLAG_SELECTORWHITE, null, null, null, null, r172));
        r1.add(new ThemeDescription(this.bottomLayout, ThemeDescription.FLAG_SELECTORWHITE, null, null, null, null, Theme.key_listSelector));
        return r1;
    L15:
        r1.add(new ThemeDescription(null, 0, new Class[]{RadioCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(null, ThemeDescription.FLAG_CHECKBOX, new Class[]{RadioCell.class}, new String[]{"radioButton"}, null, null, null, Theme.key_radioBackground));
        r1.add(new ThemeDescription(null, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{RadioCell.class}, new String[]{"radioButton"}, null, null, null, Theme.key_radioBackgroundChecked));
        goto L16
    L8:
        r1.add(new ThemeDescription(null, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(null, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        goto L10
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return this.swipeBackEnabled;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r2, int r3, Intent r4) {
        super.onActivityResultFragment(r2, r3, r4);
        if (r2 != 991) goto L6;
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda2(this, r3, r4));
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        WebView r0 = this.webView;
        if (r0 == null) goto L11;
        if (this.shouldNavigateBack == false) goto L11;
        if (r3 == false) goto L9;
        r0.loadUrl(this.webViewUrl);
        this.shouldNavigateBack = false;
    L9:
        return false;
    L11:
        return !this.donePressed;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyVisible() {
        super.onBecomeFullyVisible();
        if (this.currentStep == 4) goto L5;
        return;
    L5:
        if (this.needPayAfterTransition == false) goto L9;
        this.needPayAfterTransition = false;
        this.bottomLayout.callOnClick();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.twoStepPasswordChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didRemoveTwoStepPassword);
        if (this.currentStep == 4) goto L5;
    L6:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.paymentFinished);
    L8:
        return super.onFragmentCreate();
    L5:
        if (this.isCheckoutPreview == false) goto L8;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        PaymentFormActivityDelegate r0 = this.delegate;
        if (r0 == null) goto L5;
        r0.onFragmentDestroyed();
    L5:
        AndroidUtilities.checkAndroidTheme(getContext(), false);
        if (this.paymentStatusSent == true) goto L12;
        this.invoiceStatus = InvoiceStatus.CANCELLED;
        if (this.paymentFormCallback == null) goto L12;
        if (getOtherSameFragmentDiff() != 0) goto L12;
        this.paymentFormCallback.onInvoiceStatusChanged(this.invoiceStatus);
    L12:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.twoStepPasswordChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didRemoveTwoStepPassword);
        if (this.currentStep == 4) goto L15;
    L16:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.paymentFinished);
    L17:
        WebView r02 = this.webView;
        if (r02 != null) goto L45;
    L43:
        int r03 = this.currentStep;     // Catch: Throwable -> L36
        if (r03 == 2) goto L32;
        if (r03 == 6) goto L32;
    L41:
        super.onFragmentDestroy();
        this.canceled = true;
        return;
    L32:
        if (SharedConfig.passcodeHash.length() != 0) goto L34;
    L38:
        getParentActivity().getWindow().clearFlags(8192);     // Catch: Throwable -> L36
        AndroidUtilities.logFlagSecure();     // Catch: Throwable -> L36
        goto L41
    L34:
        if (SharedConfig.allowScreenCapture == false) goto L41;
    L36:
        th = move-exception;
        FileLog.e(th);
        goto L41
    L45:
        ViewParent r04 = r02.getParent();     // Catch: Exception -> L22
        if (r04 == null) goto L24;
        ((ViewGroup) r04).removeView(this.webView);     // Catch: Exception -> L22
    L24:
        this.webView.stopLoading();     // Catch: Exception -> L22
        this.webView.loadUrl("about:blank");     // Catch: Exception -> L22
        this.webViewUrl = null;     // Catch: Exception -> L22
        this.webView.destroy();     // Catch: Exception -> L22
        this.webView = null;     // Catch: Exception -> L22
    L22:
        e = move-exception;
        FileLog.e(e);
        goto L43
    L15:
        if (this.isCheckoutPreview == false) goto L17;
        goto L16
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        int r0 = this.currentStep;     // Catch: Throwable -> L19
        if (r0 == 2) goto L8;
        if (r0 == 6) goto L8;
    L12:
        if (SharedConfig.passcodeHash.length() != 0) goto L14;
    L17:
        getParentActivity().getWindow().clearFlags(8192);     // Catch: Throwable -> L19
        AndroidUtilities.logFlagSecure();     // Catch: Throwable -> L19
        return;
    L14:
        if (SharedConfig.allowScreenCapture == true) goto L17;
        return;
    L8:
        if (this.paymentForm.invoice.test == true) goto L12;
        getParentActivity().getWindow().setFlags(8192, 8192);     // Catch: Throwable -> L19
        AndroidUtilities.logFlagSecure();     // Catch: Throwable -> L19
        return;
    L19:
        th = move-exception;
        FileLog.e(th);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r3, boolean r4) {
        if (r3 == false) goto L33;
        if (r4 == true) goto L34;
        WebView r32 = this.webView;
        int r42 = this.currentStep;
        if (r32 == null) goto L14;
        if (r42 == 4) goto L35;
        TLRPC.TL_paymentFormMethod r43 = this.paymentFormMethod;
        if (r43 == null) goto L11;
        String r44 = r43.url;
        this.webViewUrl = r44;
        r32.loadUrl(r44);
        return;
    L11:
        String r45 = this.paymentForm.url;
        this.webViewUrl = r45;
        r32.loadUrl(r45);
        return;
    L35:
        return;
    L14:
        if (r42 != 2) goto L18;
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$$ExternalSyntheticLambda3(this), 100);
        return;
    L18:
        if (r42 != 3) goto L22;
        this.inputFields[1].requestFocus();
        AndroidUtilities.showKeyboard(this.inputFields[1]);
        return;
    L22:
        if (r42 != 4) goto L28;
        EditTextBoldCursor[] r2 = this.inputFields;
        if (r2 == null) goto L36;
        r2[0].requestFocus();
        return;
    L36:
        return;
    L28:
        if (r42 == 6) goto L30;
        return;
    L30:
        if (this.waitingForEmail == true) goto L38;
        this.inputFields[0].requestFocus();
        AndroidUtilities.showKeyboard(this.inputFields[0]);
        return;
    L38:
        return;
    L34:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean presentFragment(BaseFragment r1) {
        onPresentFragment(r1);
        return super.presentFragment(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean presentFragment(BaseFragment r1, boolean r2) {
        onPresentFragment(r1);
        return super.presentFragment(r1, r2);
    }

    public PaymentFormActivity setCustomAnyResultReceiver(Utilities.Callback<TLRPC.payments_PaymentResult> r1) {
        this.customAnyResultReceiver = r1;
        return this;
    }

    public void setCustomErrorReceiver(Utilities.CallbackReturn<TLRPC.TL_error, Boolean> r1) {
        this.customErrorReceiver = r1;
    }

    public PaymentFormActivity setCustomResultReceiver(Utilities.Callback<TLRPC.TL_payments_paymentResult> r1) {
        this.customResultReceiver = r1;
        return this;
    }

    public void setPaymentFormCallback(PaymentFormCallback r1) {
        this.paymentFormCallback = r1;
    }

    public void setResourcesProvider(Theme.ResourcesProvider r1) {
        this.resourcesProvider = r1;
    }
}
