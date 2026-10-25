package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.ai.AiController;
import com.exteragram.messenger.ai.TelegramAiReplacement;
import com.exteragram.messenger.translator.TranslationProviders;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.google.common.base.Charsets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONTokener;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LanguageDetector;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.RichMessageLayout;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.TranslateController;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.XiaomiUtilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public abstract class TranslateAlert2 extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    private static HashMap<String, Locale> localesByCode;
    public static final String[] userAgents = null;
    private PaddedAdapter adapter;
    private Runnable aiReplacementCancel;
    private Boolean buttonShadowShown;
    private boolean firstTranslation;
    private BaseFragment fragment;
    private String fromLanguage;
    private HeaderView headerView;
    private LinearLayoutManager layoutManager;
    private RecyclerListView listView;
    private LoadingTextView loadingTextView;
    private ButtonWithCounterView mainButton;
    private Utilities.CallbackReturn<URLSpan, Boolean> onLinkPress;
    private String prevToLanguage;
    private Integer reqId;
    private ArrayList<TLRPC.MessageEntity> reqMessageEntities;
    private int reqMessageId;
    private TLRPC.InputPeer reqPeer;
    private TL_iv.RichMessage reqRichMessage;
    private boolean reqSum;
    private CharSequence reqText;
    private RichMessageLayout.PreviewView richLoadingPreviewView;
    private RichMessageLayout.PreviewView richPreviewView;
    private AnimatedFloat sheetTopAnimated;
    private boolean sheetTopNotAnimate;
    private TextSelectionHelper.ArticleTextSelectionHelper textSelectionHelper;
    private TextSelectionHelper.TextSelectionOverlay textSelectionOverlay;
    private LinkSpanDrawable.LinksTextView textView;
    private FrameLayout textViewContainer;
    private String toLanguage;

    /* renamed from: org.telegram.ui.Components.TranslateAlert2$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ TranslateAlert2 this$0;

        public AnonymousClass1(TranslateAlert2 r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.TranslateAlert2$10, reason: invalid class name */
    public class AnonymousClass10 extends TranslateAlert2 {
        final /* synthetic */ Runnable val$onDismiss;

        public AnonymousClass10(Context r1, String r2, String r3, CharSequence r4, ArrayList r5, TLRPC.InputPeer r6, int r7, boolean r8, TL_iv.RichMessage r9, Theme.ResourcesProvider r10, Runnable r11) {
            this.val$onDismiss = r11;
            super(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, null);
        }

        @Override // org.telegram.ui.Components.TranslateAlert2, org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            super.dismiss();
            Runnable r0 = this.val$onDismiss;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.TranslateAlert2$11, reason: invalid class name */
    public class AnonymousClass11 extends TranslateAlert2 {
        final /* synthetic */ Runnable val$onDismiss;

        public AnonymousClass11(Context r1, String r2, String r3, CharSequence r4, ArrayList r5, Theme.ResourcesProvider r6, Runnable r7) {
            this.val$onDismiss = r7;
            super(r1, r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.TranslateAlert2, org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            super.dismiss();
            Runnable r0 = this.val$onDismiss;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.TranslateAlert2$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ TranslateAlert2 this$0;

        public AnonymousClass2(TranslateAlert2 r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r4) {
            if (r4.getAction() != 0) goto L9;
            if (r4.getY() >= (TranslateAlert2.m13405$$Nest$mgetSheetTop(this.this$0) - getTop())) goto L9;
            this.this$0.dismiss();
            return true;
        L9:
            return super.dispatchTouchEvent(r4);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onRequestFocusInDescendants(int r1, Rect r2) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.ViewParent
        public void requestChildFocus(View r1, View r2) {
        }
    }

    /* renamed from: org.telegram.ui.Components.TranslateAlert2$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ TranslateAlert2 this$0;

        public AnonymousClass3(TranslateAlert2 r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r2, int r3) {
            if (r3 != 0) goto L5;
            TranslateAlert2.m13403$$Nest$fputsheetTopNotAnimate(this.this$0, false);
        L5:
            if (r3 == 0) goto L9;
            if (r3 == 2) goto L9;
            return;
        L9:
            if (TranslateAlert2.m13406$$Nest$mgetSheetTop(this.this$0, false) > 0.0f) goto L11;
            return;
        L11:
            if (TranslateAlert2.m13406$$Nest$mgetSheetTop(this.this$0, false) < AndroidUtilities.dp(96.0f)) goto L13;
            return;
        L13:
            if (TranslateAlert2.m13388$$Nest$fgetlistView(this.this$0).canScrollVertically(1) == true) goto L15;
            return;
        L15:
            if (TranslateAlert2.m13407$$Nest$mhasEnoughHeight(this.this$0) == false) goto L22;
            TranslateAlert2.m13403$$Nest$fputsheetTopNotAnimate(this.this$0, true);
            TranslateAlert2.m13388$$Nest$fgetlistView(this.this$0).smoothScrollBy(0, (int) TranslateAlert2.m13406$$Nest$mgetSheetTop(this.this$0, false));
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            TranslateAlert2.access$000(this.this$0).invalidate();
            TranslateAlert2 r0 = this.this$0;
            TranslateAlert2.m13409$$Nest$mupdateButtonShadow(r0, TranslateAlert2.m13388$$Nest$fgetlistView(r0).canScrollVertically(1));
        }
    }

    /* renamed from: org.telegram.ui.Components.TranslateAlert2$4, reason: invalid class name */
    public class AnonymousClass4 extends DefaultItemAnimator {
        final /* synthetic */ TranslateAlert2 this$0;

        public AnonymousClass4(TranslateAlert2 r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onChangeAnimationUpdate(RecyclerView.ViewHolder r1) {
            TranslateAlert2.access$100(this.this$0).invalidate();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            TranslateAlert2.access$200(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.TranslateAlert2$5, reason: invalid class name */
    public class AnonymousClass5 implements TranslatorUtils.TranslateCallback {
        final /* synthetic */ TranslateAlert2 this$0;
        final /* synthetic */ String val$finalLang;
        final /* synthetic */ TLRPC.TL_textWithEntities val$textWithEntities;

        public AnonymousClass5(TranslateAlert2 r1, TLRPC.TL_textWithEntities r2, String r3) {
            this.this$0 = r1;
            this.val$textWithEntities = r2;
            this.val$finalLang = r3;
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onFailed() {
            BulletinFactory.of((FrameLayout) TranslateAlert2.access$300(this.this$0), TranslateAlert2.access$400(this.this$0)).createErrorBulletin(LocaleController.getString(R.string.TranslationFailedAlert2)).show();
            HeaderView.m13416$$Nest$fgettoLanguageTextView(TranslateAlert2.m13387$$Nest$fgetheaderView(this.this$0)).setText(TranslateAlert2.languageName(this.val$finalLang));
            TranslateAlert2.m13383$$Nest$fgetadapter(this.this$0).updateMainView(TranslateAlert2.m13398$$Nest$fgettextViewContainer(this.this$0));
            TranslateAlert2.m13390$$Nest$fgetmainButton(this.this$0).setLoading(false);
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onReqId(int r1) {
            TranslateAlert2.m13402$$Nest$fputreqId(this.this$0, Integer.valueOf(r1));
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onSuccess(TLObject r9, TLRPC.TL_error r10) {
            TranslateAlert2.m13402$$Nest$fputreqId(this.this$0, null);
            if ((r9 instanceof TLRPC.TL_messages_translateResult) == false) goto L13;
            TLRPC.TL_messages_translateResult r92 = (TLRPC.TL_messages_translateResult) r9;
            if (r92.result.isEmpty() == true) goto L13;
            if (r92.result.get(0) == null) goto L13;
            if (r92.result.get(0).text == null) goto L13;
            TranslateAlert2.m13400$$Nest$fputfirstTranslation(this.this$0, false);
            TLRPC.TL_textWithEntities r93 = TranslateAlert2.preprocess(this.val$textWithEntities, r92.result.get(0));
            SpannableStringBuilder r2 = SpannableStringBuilder.valueOf(r93.text);
            MessageObject.addEntitiesToText(r2, r93.entities, false, true, false, false);
            CharSequence r94 = TranslateAlert2.m13408$$Nest$mpreprocessText(this.this$0, r2);
            TranslateAlert2.m13397$$Nest$fgettextView(this.this$0).setText(r94);
            TranslateAlert2.m13383$$Nest$fgetadapter(this.this$0).updateMainView(TranslateAlert2.m13398$$Nest$fgettextViewContainer(this.this$0));
            AndroidUtilities.updateViewVisibilityAnimated(HeaderView.m13413$$Nest$fgetcopyButton(TranslateAlert2.m13387$$Nest$fgetheaderView(this.this$0)), true, 0.5f, true);
            TranslateAlert2.m13390$$Nest$fgetmainButton(this.this$0).setLoading(false);
            return;
        L13:
            if (TranslateAlert2.m13384$$Nest$fgetfirstTranslation(this.this$0) == false) goto L16;
            this.this$0.dismiss();
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.getString(R.string.TranslationFailedAlert2)});
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.TranslateAlert2$6, reason: invalid class name */
    public class AnonymousClass6 implements TranslatorUtils.TranslateCallback {
        final /* synthetic */ TranslateAlert2 this$0;
        final /* synthetic */ String val$finalLang;

        public AnonymousClass6(TranslateAlert2 r1, String r2) {
            this.this$0 = r1;
            this.val$finalLang = r2;
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onFailed() {
            BulletinFactory.of((FrameLayout) TranslateAlert2.access$500(this.this$0), TranslateAlert2.access$600(this.this$0)).createErrorBulletin(LocaleController.getString(R.string.TranslationFailedAlert2)).show();
            HeaderView.m13416$$Nest$fgettoLanguageTextView(TranslateAlert2.m13387$$Nest$fgetheaderView(this.this$0)).setText(TranslateAlert2.languageName(this.val$finalLang));
            TranslateAlert2.m13383$$Nest$fgetadapter(this.this$0).updateMainView(TranslateAlert2.m13398$$Nest$fgettextViewContainer(this.this$0));
            TranslateAlert2.m13390$$Nest$fgetmainButton(this.this$0).setLoading(false);
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onReqId(int r1) {
            TranslateAlert2.m13402$$Nest$fputreqId(this.this$0, Integer.valueOf(r1));
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onSuccess(TLRPC.TL_textWithEntities r10) {
            if (r10 == null) goto L9;
            if (r10.text == null) goto L9;
            TranslateAlert2.m13400$$Nest$fputfirstTranslation(this.this$0, false);
            SpannableStringBuilder r3 = SpannableStringBuilder.valueOf(r10.text);
            MessageObject.addEntitiesToText(r3, r10.entities, false, true, false, false);
            CharSequence r102 = TranslateAlert2.m13408$$Nest$mpreprocessText(this.this$0, r3);
            TranslateAlert2.m13397$$Nest$fgettextView(this.this$0).setText(r102);
            TranslateAlert2.m13383$$Nest$fgetadapter(this.this$0).updateMainView(TranslateAlert2.m13398$$Nest$fgettextViewContainer(this.this$0));
            AndroidUtilities.updateViewVisibilityAnimated(HeaderView.m13413$$Nest$fgetcopyButton(TranslateAlert2.m13387$$Nest$fgetheaderView(this.this$0)), true, 0.5f, true);
            TranslateAlert2.m13390$$Nest$fgetmainButton(this.this$0).setLoading(false);
            return;
        L9:
            if (TranslateAlert2.m13384$$Nest$fgetfirstTranslation(this.this$0) == false) goto L12;
            this.this$0.dismiss();
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.getString(R.string.TranslationFailedAlert2)});
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.TranslateAlert2$7, reason: invalid class name */
    public class AnonymousClass7 extends Thread {
        final /* synthetic */ Utilities.Callback2 val$done;
        final /* synthetic */ String val$fromLng;
        final /* synthetic */ String val$text;
        final /* synthetic */ String val$toLng;

        public static /* synthetic */ void $r8$lambda$lVihWXvr5cjxjnl1lfxiRooKeRg(Utilities.Callback2 r1, boolean r2) {
            r1.run(null, Boolean.valueOf(r2));
        }

        public static /* synthetic */ void $r8$lambda$mWtgxwy42n45DzJHI5_Sx5O247Q(Utilities.Callback2 r2) {
            r2.run(null, Boolean.FALSE);
        }

        public static /* synthetic */ void $r8$lambda$vC9mSOZTOvADbOtfubnnCIoM4SI(Utilities.Callback2 r1, String r2) {
            if (r1 == null) goto L5;
            r1.run(r2, Boolean.FALSE);
            return;
        }

        public AnonymousClass7(String r1, String r2, String r3, Utilities.Callback2 r4) {
            this.val$fromLng = r1;
            this.val$toLng = r2;
            this.val$text = r3;
            this.val$done = r4;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            boolean r3 = true;
            String r5 = null;
            HttpURLConnection r1 = (HttpURLConnection) new URI(("https://translate.googleapis.com/transl".concat("ate_a").concat("/singl") + "e?client=gtx&sl=" + Uri.encode(this.val$fromLng) + "&tl=" + Uri.encode(this.val$toLng) + "&dt=t&ie=UTF-8&oe=UTF-8&otf=1&ssel=0&tsel=0&kc=7&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&q=") + this.val$text).toURL().openConnection();     // Catch: Exception -> L43
            r1.setRequestMethod("GET");     // Catch: Exception -> L20
            r1.setRequestProperty("User-Agent", TranslateAlert2.userAgents[(int) Math.round(Math.random() * (r6.length - 1))]);     // Catch: Exception -> L20
            r1.setRequestProperty("Content-Type", "application/json");     // Catch: Exception -> L20
            StringBuilder r2 = new StringBuilder();     // Catch: Exception -> L20
            BufferedReader r6 = new BufferedReader(new InputStreamReader(r1.getInputStream(), Charsets.UTF_8));     // Catch: Exception -> L20
        L69:
            int r7 = r6.read();     // Catch: Throwable -> L9
            if (r7 == (-1)) goto L11;
            r2.append((char) r7);     // Catch: Throwable -> L9
            goto L69
        L11:
            r6.close();     // Catch: Exception -> L20
            JSONArray r22 = new JSONArray(new JSONTokener(r2.toString()));     // Catch: Exception -> L20
            JSONArray r62 = r22.getJSONArray(0);     // Catch: Exception -> L20
            String r23 = r22.getString(2);     // Catch: Exception -> L15
        L16:
            if (r23 != null) goto L18;
        L22:
            String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            int r24 = 0;
        L24:
            if (r24 >= r62.length()) goto L32;
            String r72 = r62.getJSONArray(r24).getString(0);     // Catch: Exception -> L20
            if (r72 == null) goto L30;
            if (r72.equals("null") == true) goto L30;
            r0 = r0 + r72;     // Catch: Exception -> L20
        L30:
            r24 = r24 + 1;     // Catch: Exception -> L20
            goto L24
        L32:
            if (this.val$text.length() > 0) goto L34;
        L36:
            AndroidUtilities.runOnUIThread(new TranslateAlert2$7$$ExternalSyntheticLambda0(this.val$done, r0));     // Catch: Exception -> L20
            return;
        L34:
            if (this.val$text.charAt(0) != '\n') goto L36;
            r0 = "\n" + r0;     // Catch: Exception -> L20
            goto L36
        L18:
            if (r23.contains("-") == false) goto L22;
            r23.substring(0, r23.indexOf("-"));     // Catch: Exception -> L20
        L15:
            r23 = null;
            goto L16
        L9:
            th = move-exception;
            r6.close();     // Catch: Throwable -> L40
        L42:
            throw th;     // Catch: Exception -> L20
        L40:
            th = move-exception;
            th.addSuppressed(th);     // Catch: Exception -> L20
        L20:
            e = e;
        L73:
            StringBuilder r63 = new StringBuilder();     // Catch: IOException -> L48
            r63.append("failed to translate a text ");     // Catch: IOException -> L48
            if (r1 == null) goto L50;
            Integer r73 = Integer.valueOf(r1.getResponseCode());     // Catch: IOException -> L48
        L51:
            r63.append(r73);     // Catch: IOException -> L48
            r63.append(" ");     // Catch: IOException -> L48
            if (r1 == null) goto L54;
            r5 = r1.getResponseMessage();     // Catch: IOException -> L48
        L54:
            r63.append(r5);     // Catch: IOException -> L48
            Log.e("translate", r63.toString());     // Catch: IOException -> L48
        L57:
            e.printStackTrace();
            if (r1 != null) goto L71;
        L62:
            r3 = false;
        L63:
            AndroidUtilities.runOnUIThread(new TranslateAlert2$7$$ExternalSyntheticLambda1(this.val$done, r3));     // Catch: Exception -> L65
            return;
        L65:
            AndroidUtilities.runOnUIThread(new TranslateAlert2$7$$ExternalSyntheticLambda2(this.val$done));
            return;
        L71:
            if (r1.getResponseCode() != 429) goto L62;
        L50:
            r73 = null;
        L48:
            e = move-exception;
            e.printStackTrace();
        L43:
            e = e;
            r1 = null;
            goto L73
        }
    }

    /* renamed from: org.telegram.ui.Components.TranslateAlert2$8, reason: invalid class name */
    public class AnonymousClass8 extends ClickableSpan {
        final /* synthetic */ TranslateAlert2 this$0;
        final /* synthetic */ URLSpan val$urlSpan;

        public AnonymousClass8(TranslateAlert2 r1, URLSpan r2) {
            this.this$0 = r1;
            this.val$urlSpan = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r2) {
            Utilities.CallbackReturn r22 = TranslateAlert2.m13391$$Nest$fgetonLinkPress(this.this$0);
            TranslateAlert2 r0 = this.this$0;
            if (r22 == null) goto L9;
            if (((Boolean) TranslateAlert2.m13391$$Nest$fgetonLinkPress(r0).run(this.val$urlSpan)).booleanValue() == false) goto L12;
            this.this$0.dismiss();
            return;
        L12:
            return;
        L9:
            if (TranslateAlert2.m13385$$Nest$fgetfragment(r0) == null) goto L13;
            AlertsCreator.showOpenUrlAlert(TranslateAlert2.m13385$$Nest$fgetfragment(this.this$0), this.val$urlSpan.getURL(), false, false);
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r3) {
            int r0 = Math.min(r3.getAlpha(), (r3.getColor() >> 24) & 255);
            if ((this.val$urlSpan instanceof URLSpanNoUnderline) == true) goto L5;
            r3.setUnderlineText(true);
        L5:
            r3.setColor(Theme.getColor(Theme.key_dialogTextLink));
            r3.setAlpha(r0);
        }
    }

    /* renamed from: org.telegram.ui.Components.TranslateAlert2$9, reason: invalid class name */
    public class AnonymousClass9 extends TranslateAlert2 {
        final /* synthetic */ Runnable val$onDismiss;

        public AnonymousClass9(Context r1, String r2, String r3, CharSequence r4, ArrayList r5, TLRPC.InputPeer r6, int r7, boolean r8, TL_iv.RichMessage r9, Theme.ResourcesProvider r10, Runnable r11) {
            this.val$onDismiss = r11;
            super(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, null);
        }

        @Override // org.telegram.ui.Components.TranslateAlert2, org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            super.dismiss();
            Runnable r0 = this.val$onDismiss;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }
    }

    public class ContainerView extends FrameLayout {
        private Paint bgPaint;
        private Path bgPath;
        private Boolean lightStatusBarFull;
        final /* synthetic */ TranslateAlert2 this$0;

        /* renamed from: org.telegram.ui.Components.TranslateAlert2$ContainerView$1, reason: invalid class name */
        public class AnonymousClass1 implements Bulletin.Delegate {
            final /* synthetic */ ContainerView this$1;

            public AnonymousClass1(ContainerView r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getBottomOffset(int r1) {
                return AndroidUtilities.dp(80.0f);
            }
        }

        public ContainerView(TranslateAlert2 r2, Context r3) {
            this.this$0 = r2;
            super(r3);
            this.bgPath = new Path();
            Paint r32 = new Paint(1);
            this.bgPaint = r32;
            r32.setColor(TranslateAlert2.access$3000(r2, Theme.key_dialogBackground));
            Theme.applyDefaultShadow(this.bgPaint);
        }

        private void updateLightStatusBar(boolean r2) {
            Boolean r0 = this.lightStatusBarFull;
            if (r0 != null) goto L5;
        L8:
            this.lightStatusBarFull = Boolean.valueOf(r2);
            Window r02 = this.this$0.getWindow();
            TranslateAlert2 r1 = this.this$0;
            if (r2 == false) goto L11;
            int r12 = TranslateAlert2.access$3100(r1, Theme.key_dialogBackground);
        L13:
            if (AndroidUtilities.computePerceivedBrightness(r12) <= 0.721f) goto L15;
            boolean r13 = true;
        L16:
            AndroidUtilities.setLightStatusBar(r02, r13);
            return;
        L15:
            r13 = false;
            goto L16
        L11:
            r12 = Theme.blendOver(TranslateAlert2.access$3200(r1, Theme.key_actionBarDefault), 855638016);
            goto L13
        L5:
            if (r0.booleanValue() != r2) goto L8;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            float r0 = TranslateAlert2.m13405$$Nest$mgetSheetTop(this.this$0);
            boolean r3 = false;
            float r1 = AndroidUtilities.lerp(0, AndroidUtilities.dp(12.0f), MathUtils.clamp(r0 / AndroidUtilities.dpf2(24.0f), 0.0f, 1.0f));
            TranslateAlert2.m13387$$Nest$fgetheaderView(this.this$0).setTranslationY(Math.max(AndroidUtilities.statusBarHeight, r0));
            if (r0 > (AndroidUtilities.statusBarHeight / 2.0f)) goto L5;
            r3 = true;
        L5:
            updateLightStatusBar(r3);
            this.this$0.topBulletinContainer.setTranslationY((((-r2.getTop()) - this.this$0.topBulletinContainer.getHeight()) + getTranslationY()) + Math.max((AndroidUtilities.statusBarHeight + AndroidUtilities.dp(56.0f)) + this.this$0.topBulletinContainer.getHeight(), r0));
            this.bgPath.rewind();
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(0.0f, r0, getWidth(), getHeight() + r1);
            this.bgPath.addRoundRect(r2, r1, r1, Path.Direction.CW);
            r8.drawPath(this.bgPath, this.bgPaint);
            super.dispatchDraw(r8);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r5) {
            if (TranslateAlert2.m13395$$Nest$fgettextSelectionHelper(this.this$0) == null) goto L23;
            if (TranslateAlert2.m13396$$Nest$fgettextSelectionOverlay(this.this$0) == null) goto L23;
            if (r5.getAction() != 0) goto L9;
        L10:
            Log.d("TA2", "container dispatch act=" + r5.getAction() + " inSel=" + TranslateAlert2.m13395$$Nest$fgettextSelectionHelper(this.this$0).isInSelectionMode());
        L12:
            if (TranslateAlert2.m13395$$Nest$fgettextSelectionHelper(this.this$0).isInSelectionMode() == true) goto L14;
        L17:
            boolean r0 = TranslateAlert2.m13396$$Nest$fgettextSelectionOverlay(this.this$0).checkOnTap(r5);
            if (r5.getAction() != 1) goto L20;
            Log.d("TA2", "checkOnTap=" + r0);
        L20:
            if (r0 == false) goto L23;
            r5.setAction(3);
            goto L23
        L14:
            if (TranslateAlert2.m13396$$Nest$fgettextSelectionOverlay(this.this$0).onTouchEvent(r5) == false) goto L17;
            Log.d("TA2", "overlay consumed (handle)");
            return true;
        L9:
            if (r5.getAction() != 1) goto L12;
        L23:
            return super.dispatchTouchEvent(r5);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            Bulletin.addDelegate(this, new AnonymousClass1(this));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            Bulletin.removeDelegate(this);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
        }

        @Override // android.view.View
        public void setTranslationY(float r4) {
            super.setTranslationY(r4);
            this.this$0.topBulletinContainer.setTranslationY((((-r0.getTop()) - this.this$0.topBulletinContainer.getHeight()) + r4) + Math.max((AndroidUtilities.statusBarHeight + AndroidUtilities.dp(56.0f)) + this.this$0.topBulletinContainer.getHeight(), TranslateAlert2.m13405$$Nest$mgetSheetTop(this.this$0)));
        }
    }

    public class HeaderView extends FrameLayout {
        private ImageView arrowView;
        private ImageView backButton;
        private View backgroundView;
        private ImageView copyButton;
        private TextView fromLanguageTextView;
        private View shadow;
        private LinearLayout subtitleView;
        final /* synthetic */ TranslateAlert2 this$0;
        private TextView titleTextView;
        private AnimatedTextView toLanguageTextView;

        /* renamed from: org.telegram.ui.Components.TranslateAlert2$HeaderView$1, reason: invalid class name */
        public class AnonymousClass1 extends TextView {
            final /* synthetic */ HeaderView this$1;
            final /* synthetic */ TranslateAlert2 val$this$0;

            public AnonymousClass1(HeaderView r1, Context r2, TranslateAlert2 r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.widget.TextView, android.view.View
            public void onMeasure(int r1, int r2) {
                super.onMeasure(r1, r2);
                if (LocaleController.isRTL == false) goto L6;
                HeaderView.m13415$$Nest$fgettitleTextView(this.this$1).setPivotX(getMeasuredWidth());
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.TranslateAlert2$HeaderView$2, reason: invalid class name */
        public class AnonymousClass2 extends LinearLayout {
            final /* synthetic */ HeaderView this$1;
            final /* synthetic */ TranslateAlert2 val$this$0;

            public AnonymousClass2(HeaderView r1, Context r2, TranslateAlert2 r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.widget.LinearLayout, android.view.View
            public void onMeasure(int r1, int r2) {
                super.onMeasure(r1, r2);
                if (LocaleController.isRTL == false) goto L6;
                HeaderView.m13414$$Nest$fgetsubtitleView(this.this$1).setPivotX(getMeasuredWidth());
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.TranslateAlert2$HeaderView$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatedTextView {
            private Paint bgPaint;
            private LinkSpanDrawable.LinkCollector links;
            final /* synthetic */ HeaderView this$1;
            final /* synthetic */ TranslateAlert2 val$this$0;

            public AnonymousClass3(HeaderView r1, Context r2, TranslateAlert2 r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
                this.bgPaint = new Paint(1);
                this.links = new LinkSpanDrawable.LinkCollector();
            }

            @Override // org.telegram.ui.Components.AnimatedTextView, android.view.View
            public void onDraw(Canvas r8) {
                if (LocaleController.isRTL == false) goto L5;
                AndroidUtilities.rectTmp.set(getWidth() - width(), (getHeight() - AndroidUtilities.dp(18.0f)) / 2.0f, getWidth(), (getHeight() + AndroidUtilities.dp(18.0f)) / 2.0f);
            L6:
                this.bgPaint.setColor(Theme.multAlpha(TranslateAlert2.access$1600(this.this$1.this$0, Theme.key_player_actionBarSubtitle), 0.1175f));
                r8.drawRoundRect(AndroidUtilities.rectTmp, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.bgPaint);
                if (this.links.draw(r8) == false) goto L9;
                invalidate();
            L9:
                super.onDraw(r8);
                return;
            L5:
                AndroidUtilities.rectTmp.set(0.0f, (getHeight() - AndroidUtilities.dp(18.0f)) / 2.0f, width(), (getHeight() + AndroidUtilities.dp(18.0f)) / 2.0f);
                goto L6
            }

            @Override // android.view.View
            public boolean onTouchEvent(MotionEvent r10) {
                if (r10.getAction() != 0) goto L11;
                LinkSpanDrawable r0 = new LinkSpanDrawable(null, TranslateAlert2.access$1700(this.this$1.this$0), r10.getX(), r10.getY());
                r0.setColor(Theme.multAlpha(TranslateAlert2.access$1800(this.this$1.this$0, Theme.key_player_actionBarSubtitle), 0.1175f));
                LinkPath r102 = r0.obtainNewPath();
                if (LocaleController.isRTL == false) goto L7;
                AndroidUtilities.rectTmp.set(getWidth() - width(), (getHeight() - AndroidUtilities.dp(18.0f)) / 2.0f, getWidth(), (getHeight() + AndroidUtilities.dp(18.0f)) / 2.0f);
            L8:
                r102.addRect(AndroidUtilities.rectTmp, Path.Direction.CW);
                this.links.addLink(r0);
                invalidate();
                return true;
            L7:
                AndroidUtilities.rectTmp.set(0.0f, (getHeight() - AndroidUtilities.dp(18.0f)) / 2.0f, width(), (getHeight() + AndroidUtilities.dp(18.0f)) / 2.0f);
                goto L8
            L11:
                if (r10.getAction() == 1) goto L15;
                if (r10.getAction() == 3) goto L15;
            L19:
                return super.onTouchEvent(r10);
            L15:
                if (r10.getAction() != 1) goto L17;
                performClick();
            L17:
                this.links.clear();
                invalidate();
                goto L19
            }
        }

        /* renamed from: org.telegram.ui.Components.TranslateAlert2$HeaderView$4, reason: invalid class name */
        public class AnonymousClass4 extends ActionBarPopupWindow.ActionBarPopupWindowLayout {
            final /* synthetic */ HeaderView this$1;

            public AnonymousClass4(HeaderView r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow.ActionBarPopupWindowLayout, android.widget.FrameLayout, android.view.View
            public void onMeasure(int r3, int r4) {
                super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(Math.min((int) (AndroidUtilities.displaySize.y * 0.33f), View.MeasureSpec.getSize(r4)), TLObject.FLAG_30));
            }
        }

        public static /* synthetic */ void $r8$lambda$5JAYNUMKjop86qFIrBdCcXsMGxk(HeaderView r0, View r1) {
            r0.lambda$new$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$HDnDVqp8r_KEar0EWlWhIGENiZg(HeaderView r0, int r1) {
            r0.lambda$openProviderSelect$4(r1);
        }

        public static /* synthetic */ boolean $r8$lambda$YfHPVLqxIBZktp41A47fAkdIJTk(HeaderView r0, TranslateController.Language r1) {
            return r0.lambda$openLanguagesSelect$5(r1);
        }

        /* renamed from: $r8$lambda$aqt-Y6Ti-Ajb6RGZVQ_DEmtLi3M, reason: not valid java name */
        public static /* synthetic */ void m13410$r8$lambda$aqtY6TiAjb6RGZVQ_DEmtLi3M(HeaderView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: $r8$lambda$cXx7m3Kp1cp1UHz3-Alb5H-oysU, reason: not valid java name */
        public static /* synthetic */ void m13411$r8$lambda$cXx7m3Kp1cp1UHz3Alb5HoysU(ActionBarPopupWindow r0) {
            r0.dismiss();
        }

        public static /* synthetic */ void $r8$lambda$iNJbXcerboyd99AH3MP8xaK95So(HeaderView r0, Runnable[] r1, TranslateController.Language r2, CharSequence r3, View r4) {
            r0.lambda$openLanguagesSelect$6(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$iNPJ-9s89vp_gw5u5NzLIOn4-RY, reason: not valid java name */
        public static /* synthetic */ void m13412$r8$lambda$iNPJ9s89vp_gw5u5NzLIOn4RY(HeaderView r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$uXKt6O0zxGHRFd_D1mBaz8scc3Q(HeaderView r0, View r1) {
            r0.lambda$new$2(r1);
        }

        /* renamed from: -$$Nest$fgetcopyButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageView m13413$$Nest$fgetcopyButton(HeaderView r0) {
            return r0.copyButton;
        }

        /* renamed from: -$$Nest$fgetsubtitleView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LinearLayout m13414$$Nest$fgetsubtitleView(HeaderView r0) {
            return r0.subtitleView;
        }

        /* renamed from: -$$Nest$fgettitleTextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m13415$$Nest$fgettitleTextView(HeaderView r0) {
            return r0.titleTextView;
        }

        /* renamed from: -$$Nest$fgettoLanguageTextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedTextView m13416$$Nest$fgettoLanguageTextView(HeaderView r0) {
            return r0.toLanguageTextView;
        }

        public HeaderView(TranslateAlert2 r22, Context r23) {
            this.this$0 = r22;
            super(r23);
            View r3 = new View(r23);
            this.backgroundView = r3;
            r3.setBackgroundColor(TranslateAlert2.access$700(r22, Theme.key_dialogBackground));
            addView(this.backgroundView, LayoutHelper.createFrame(-1, 44.0f, 55, 0.0f, 12.0f, 0.0f, 0.0f));
            ImageView r32 = new ImageView(r23);
            this.backButton = r32;
            ImageView.ScaleType r4 = ImageView.ScaleType.CENTER;
            r32.setScaleType(r4);
            this.backButton.setImageResource(R.drawable.ic_ab_back);
            ImageView r33 = this.backButton;
            int r6 = Theme.key_dialogTextBlack;
            int r7 = TranslateAlert2.access$800(r22, r6);
            PorterDuff.Mode r8 = PorterDuff.Mode.MULTIPLY;
            r33.setColorFilter(new PorterDuffColorFilter(r7, r8));
            ImageView r34 = this.backButton;
            int r5 = Theme.key_listSelector;
            r34.setBackground(Theme.createSelectorDrawable(TranslateAlert2.access$900(r22, r5)));
            this.backButton.setAlpha(0.0f);
            this.backButton.setOnClickListener(new TranslateAlert2$HeaderView$$ExternalSyntheticLambda0(this));
            addView(this.backButton, LayoutHelper.createFrame(54, 54.0f, 48, 1.0f, 1.0f, 1.0f, 1.0f));
            ImageView r35 = new ImageView(r23);
            this.copyButton = r35;
            ScaleStateListAnimator.apply(r35, 0.15f, 1.5f);
            this.copyButton.setScaleType(r4);
            this.copyButton.setImageResource(R.drawable.msg_copy);
            ImageView r36 = this.copyButton;
            int r9 = Theme.key_player_actionBarSubtitle;
            r36.setColorFilter(new PorterDuffColorFilter(TranslateAlert2.access$1000(r22, r9), r8));
            this.copyButton.setBackground(Theme.createSelectorDrawable(TranslateAlert2.access$1100(r22, r5)));
            this.copyButton.setVisibility(8);
            this.copyButton.setOnClickListener(new TranslateAlert2$HeaderView$$ExternalSyntheticLambda1(this));
            addView(this.copyButton, LayoutHelper.createFrame(48, 54.0f, 53, 1.0f, 1.0f, 16.0f, 1.0f));
            AnonymousClass1 r37 = new AnonymousClass1(this, r23, r22);
            this.titleTextView = r37;
            r37.setTextColor(TranslateAlert2.access$1200(r22, r6));
            this.titleTextView.setTextSize(1, 20.0f);
            this.titleTextView.setTypeface(AndroidUtilities.bold());
            this.titleTextView.setSingleLine(true);
            this.titleTextView.setEllipsize(TextUtils.TruncateAt.END);
            this.titleTextView.setText(TranslatorUtils.getCurrentTranslatorName());
            Drawable r38 = ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_drop_down);
            if (r38 == null) goto L8;
            Drawable r39 = r38.mutate();
            r39.setColorFilter(new PorterDuffColorFilter(TranslateAlert2.access$1300(r22, r6), r8));
            r39.setBounds(0, -AndroidUtilities.dp(1.0f), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(21.0f));
            boolean r62 = LocaleController.isRTL;
            TextView r11 = this.titleTextView;
            if (r62 == false) goto L7;
            r11.setCompoundDrawables(r39, null, null, null);
            goto L8
        L7:
            r11.setCompoundDrawables(null, null, r39, null);
        L8:
            this.titleTextView.setCompoundDrawablePadding(AndroidUtilities.dp(2.0f));
            this.titleTextView.setPadding(0, 0, 0, 0);
            TextView r310 = this.titleTextView;
            if (LocaleController.isRTL == false) goto L11;
            int r112 = 5;
        L12:
            r310.setGravity(r112);
            this.titleTextView.setIncludeFontPadding(false);
            this.titleTextView.setOnClickListener(new TranslateAlert2$HeaderView$$ExternalSyntheticLambda2(this));
            this.titleTextView.setPivotX(0.0f);
            this.titleTextView.setPivotY(0.0f);
            TextView r311 = this.titleTextView;
            if (LocaleController.isRTL == false) goto L15;
            int r113 = 5;
        L16:
            addView(r311, LayoutHelper.createFrame(-2, -2.0f, r113 | 48, 22.0f, 20.0f, 22.0f, 0.0f));
            AnonymousClass2 r312 = new AnonymousClass2(this, r23, r22);
            this.subtitleView = r312;
            if (LocaleController.isRTL == false) goto L19;
            r312.setGravity(5);
        L19:
            this.subtitleView.setPivotX(0.0f);
            this.subtitleView.setPivotY(0.0f);
            if (TextUtils.isEmpty(TranslateAlert2.m13386$$Nest$fgetfromLanguage(r22)) == false) goto L22;
        L24:
            ImageView r313 = new ImageView(r23);
            this.arrowView = r313;
            r313.setImageResource(R.drawable.search_arrow);
            this.arrowView.setColorFilter(new PorterDuffColorFilter(TranslateAlert2.access$1500(r22, r9), r8));
            if (LocaleController.isRTL == false) goto L27;
            this.arrowView.setScaleX(-1.0f);
        L27:
            AnonymousClass3 r314 = new AnonymousClass3(this, r23, r22);
            this.toLanguageTextView = r314;
            if (LocaleController.isRTL == false) goto L30;
            r314.setGravity(5);
        L30:
            this.toLanguageTextView.setAnimationProperties(0.25f, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.toLanguageTextView.setTextColor(TranslateAlert2.access$1900(r22, r9));
            this.toLanguageTextView.setTextSize(AndroidUtilities.dp(14.0f));
            this.toLanguageTextView.setText(TranslateAlert2.capitalFirst(TranslateAlert2.languageName(TranslateAlert2.m13399$$Nest$fgettoLanguage(r22))));
            this.toLanguageTextView.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(2.0f));
            this.toLanguageTextView.setOnClickListener(new TranslateAlert2$HeaderView$$ExternalSyntheticLambda3(this));
            if (LocaleController.isRTL == false) goto L39;
            LinearLayout r315 = this.subtitleView;
            AnimatedTextView r52 = this.toLanguageTextView;
            if (this.fromLanguageTextView == null) goto L35;
            int r18 = 3;
        L36:
            r315.addView(r52, LayoutHelper.createLinear(-2, -2, 16, 0, 0, r18, 0));
            if (this.fromLanguageTextView == null) goto L47;
            this.subtitleView.addView(this.arrowView, LayoutHelper.createLinear(-2, -2, 16, 0, 1, 0, 0));
            this.subtitleView.addView(this.fromLanguageTextView, LayoutHelper.createLinear(-2, -2, 16, 4, 0, 0, 0));
        L47:
            addView(this.subtitleView, LayoutHelper.createFrame(-1, -2.0f, 55, 22.0f, 43.0f, 22.0f, 0.0f));
            View r316 = new View(r23);
            this.shadow = r316;
            r316.setBackgroundColor(TranslateAlert2.access$2000(r22, Theme.key_dialogShadowLine));
            this.shadow.setAlpha(0.0f);
            addView(this.shadow, LayoutHelper.createFrame(-1, AndroidUtilities.getShadowHeight() / AndroidUtilities.dpf2(1.0f), 55, 0.0f, 56.0f, 0.0f, 0.0f));
            this.backButton.bringToFront();
            this.copyButton.bringToFront();
            return;
        L35:
            r18 = 0;
            goto L36
        L39:
            TextView r317 = this.fromLanguageTextView;
            if (r317 == null) goto L42;
            this.subtitleView.addView(r317, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 4, 0));
            this.subtitleView.addView(this.arrowView, LayoutHelper.createLinear(-2, -2, 16, 0, 1, 0, 0));
        L42:
            LinearLayout r318 = this.subtitleView;
            AnimatedTextView r53 = this.toLanguageTextView;
            if (this.fromLanguageTextView == null) goto L45;
            int r16 = 3;
        L46:
            r318.addView(r53, LayoutHelper.createLinear(-2, -2, 16, r16, 0, 0, 0));
            goto L47
        L45:
            r16 = 0;
            goto L46
        L22:
            if (TranslateController.UNKNOWN_LANGUAGE.equals(TranslateAlert2.m13386$$Nest$fgetfromLanguage(r22)) == true) goto L24;
            TextView r319 = new TextView(r23);
            this.fromLanguageTextView = r319;
            r319.setLines(1);
            this.fromLanguageTextView.setTextColor(TranslateAlert2.access$1400(r22, r9));
            this.fromLanguageTextView.setTextSize(1, 14.0f);
            this.fromLanguageTextView.setText(TranslateAlert2.capitalFirst(TranslateAlert2.languageName(TranslateAlert2.m13386$$Nest$fgetfromLanguage(r22))));
            this.fromLanguageTextView.setPadding(0, AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f));
            goto L24
        L15:
            r113 = 3;
            goto L16
        L11:
            r112 = 3;
            goto L12
        }

        private CharSequence[] ProviderNames() {
            return TranslationProviders.names();
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            this.this$0.dismiss();
        }

        private /* synthetic */ void lambda$new$1(View r1) {
            if (AndroidUtilities.addToClipboard(TranslateAlert2.m13397$$Nest$fgettextView(this.this$0).getText()) == false) goto L6;
            BulletinFactory.of((FrameLayout) TranslateAlert2.access$2800(this.this$0), TranslateAlert2.access$2900(this.this$0)).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
            return;
        }

        private /* synthetic */ void lambda$new$2(View r1) {
            openProviderSelect();
        }

        private /* synthetic */ void lambda$new$3(View r1) {
            openLanguagesSelect();
        }

        private /* synthetic */ boolean lambda$openLanguagesSelect$5(TranslateController.Language r2) {
            if (r2 != null) goto L4;
            return true;
        L4:
            if (TextUtils.isEmpty(r2.code) == false) goto L6;
            return true;
        L6:
            if (TextUtils.equals(r2.code, TranslateAlert2.m13386$$Nest$fgetfromLanguage(this.this$0)) == true) goto L13;
            return false;
        L13:
            return true;
        }

        private /* synthetic */ void lambda$openLanguagesSelect$6(Runnable[] r2, TranslateController.Language r3, CharSequence r4, View r5) {
            Runnable r22 = r2[0];
            if (r22 == null) goto L6;
            r22.run();
        L6:
            if (TextUtils.equals(TranslateAlert2.m13399$$Nest$fgettoLanguage(this.this$0), r3.code) == false) goto L9;
            return;
        L9:
            if (PaddedAdapter.m13417$$Nest$fgetmMainView(TranslateAlert2.m13383$$Nest$fgetadapter(this.this$0)) != TranslateAlert2.m13398$$Nest$fgettextViewContainer(this.this$0)) goto L11;
        L12:
            TranslateAlert2 r23 = this.this$0;
            TranslateAlert2.m13401$$Nest$fputprevToLanguage(r23, TranslateAlert2.m13399$$Nest$fgettoLanguage(r23));
        L13:
            this.toLanguageTextView.setText(r4);
            TranslateAlert2.m13404$$Nest$fputtoLanguage(this.this$0, r3.code);
            PaddedAdapter r24 = TranslateAlert2.m13383$$Nest$fgetadapter(this.this$0);
            TL_iv.RichMessage r42 = TranslateAlert2.m13392$$Nest$fgetreqRichMessage(this.this$0);
            TranslateAlert2 r0 = this.this$0;
            if (r42 == null) goto L16;
            View r43 = TranslateAlert2.m13393$$Nest$fgetrichLoadingPreviewView(r0);
        L17:
            r24.updateMainView(r43);
            TranslateAlert2.setToLanguage(r3.code);
            TranslateAlert2.m13390$$Nest$fgetmainButton(this.this$0).setLoading(true);
            AndroidUtilities.updateViewVisibilityAnimated(TranslateAlert2.m13387$$Nest$fgetheaderView(this.this$0).copyButton, false, 0.5f, true);
            this.this$0.translate();
            return;
        L16:
            r43 = TranslateAlert2.m13389$$Nest$fgetloadingTextView(r0);
            goto L17
        L11:
            if (PaddedAdapter.m13417$$Nest$fgetmMainView(TranslateAlert2.m13383$$Nest$fgetadapter(this.this$0)) != TranslateAlert2.m13394$$Nest$fgetrichPreviewView(this.this$0)) goto L13;
            goto L12
        }

        private /* synthetic */ void lambda$openProviderSelect$4(int r2) {
            if (ExteraConfig.getTranslationProvider() != r2) goto L5;
            return;
        L5:
            onProviderSelect(r2);
        }

        private void onProviderSelect(int r4) {
            String r0 = TranslateAlert2.m13399$$Nest$fgettoLanguage(this.this$0);
            ExteraConfig.setTranslationProvider(r4);
            TranslatorUtils.ensureTargetLanguageCompatibleWithProvider();
            this.titleTextView.setText(TranslatorUtils.getCurrentTranslatorName());
            String r42 = TranslateAlert2.getToLanguage();
            if (TextUtils.equals(r0, r42) == false) goto L5;
        L8:
            TranslateAlert2.m13383$$Nest$fgetadapter(this.this$0).updateMainView(TranslateAlert2.m13389$$Nest$fgetloadingTextView(this.this$0));
            TranslateAlert2.m13390$$Nest$fgetmainButton(this.this$0).setLoading(true);
            AndroidUtilities.updateViewVisibilityAnimated(TranslateAlert2.m13387$$Nest$fgetheaderView(this.this$0).copyButton, false, 0.5f, true);
            this.this$0.translate();
            return;
        L5:
            if (PaddedAdapter.m13417$$Nest$fgetmMainView(TranslateAlert2.m13383$$Nest$fgetadapter(this.this$0)) != TranslateAlert2.m13398$$Nest$fgettextViewContainer(this.this$0)) goto L7;
            TranslateAlert2.m13401$$Nest$fputprevToLanguage(this.this$0, r0);
        L7:
            TranslateAlert2.m13404$$Nest$fputtoLanguage(this.this$0, r42);
            this.toLanguageTextView.setText(TranslateAlert2.capitalFirst(TranslateAlert2.languageName(TranslateAlert2.m13399$$Nest$fgettoLanguage(this.this$0))));
            goto L8
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(78.0f), TLObject.FLAG_30));
        }

        public void openLanguagesSelect() {
            AnonymousClass4 r0 = new AnonymousClass4(this, getContext());
            Drawable r1 = ContextCompat.getDrawable(getContext(), R.drawable.popup_fixed_alert).mutate();
            r1.setColorFilter(new PorterDuffColorFilter(TranslateAlert2.access$2300(this.this$0, Theme.key_actionBarDefaultSubmenuBackground), PorterDuff.Mode.MULTIPLY));
            r0.setBackground(r1);
            Runnable[] r2 = new Runnable[1];
            ArrayList r3 = new ArrayList(TranslatorUtils.getCurrentTargetLanguages());
            r3.removeIf(new TranslateAlert2$HeaderView$$ExternalSyntheticLambda5(this));
            boolean r9 = true;
            int r5 = 0;
        L4:
            if (r5 >= r3.size()) goto L10;
            TranslateController.Language r12 = (TranslateController.Language) r3.get(r5);
            String r13 = r12.displayName;
            Context r7 = getContext();
            if (r5 != (r3.size() - 1)) goto L8;
            boolean r10 = true;
        L9:
            ActionBarMenuSubItem r6 = new ActionBarMenuSubItem(r7, 2, r9, r10, TranslateAlert2.access$2400(this.this$0));
            r6.setText(r13);
            r6.setChecked(TextUtils.equals(TranslateAlert2.m13399$$Nest$fgettoLanguage(this.this$0), r12.code));
            r6.setOnClickListener(new TranslateAlert2$HeaderView$$ExternalSyntheticLambda6(this, r2, r12, r13));
            r0.addView(r6);
            r5 = r5 + 1;
            r9 = false;
            goto L4
        L8:
            r10 = false;
            goto L9
        L10:
            ActionBarPopupWindow r32 = new ActionBarPopupWindow(r0, -2, -2);
            r2[0] = new TranslateAlert2$HeaderView$$ExternalSyntheticLambda7(r32);
            r32.setPauseNotifications(true);
            r32.setDismissAnimationDuration(Opcodes.REM_INT_LIT8);
            r32.setOutsideTouchable(true);
            r32.setClippingEnabled(true);
            r32.setAnimationStyle(R.style.PopupContextAnimation);
            r32.setFocusable(true);
            int[] r22 = new int[2];
            this.toLanguageTextView.getLocationInWindow(r22);
            r0.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.x, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.y, Integer.MIN_VALUE));
            int r02 = r0.getMeasuredHeight();
            int r14 = r22[1];
            if (r14 <= ((AndroidUtilities.displaySize.y * 0.9f) - r02)) goto L13;
            int r15 = (r14 - r02) + AndroidUtilities.dp(8.0f);
        L14:
            r32.showAtLocation(TranslateAlert2.access$2500(this.this$0), 51, r22[0] - AndroidUtilities.dp(8.0f), r15);
            return;
        L13:
            r15 = (r14 + this.toLanguageTextView.getMeasuredHeight()) - AndroidUtilities.dp(8.0f);
            goto L14
        }

        public void openProviderSelect() {
            ItemOptions r0 = ItemOptions.makeOptions(TranslateAlert2.access$2100(this.this$0), TranslateAlert2.access$2200(this.this$0), this.titleTextView).setDrawScrim(false).setDimAlpha(0);
            if (LocaleController.isRTL == false) goto L5;
            int r2 = 5;
        L6:
            ItemOptions r02 = r0.setGravity(r2);
            if (LocaleController.isRTL == false) goto L10;
            int r22 = AndroidUtilities.dp(8.0f);
        L11:
            ItemOptions r03 = r02.translate(r22, 0.0f);
            int r23 = ExteraConfig.getTranslationProvider();
            CharSequence[] r3 = ProviderNames();
            int r4 = 0;
        L13:
            if (r4 >= r3.length) goto L18;
            if (r23 != r4) goto L16;
            boolean r5 = true;
        L17:
            r03.addChecked(r5, r3[r4], new TranslateAlert2$HeaderView$$ExternalSyntheticLambda4(this, r4));
            r4 = r4 + 1;
            goto L13
        L16:
            r5 = false;
            goto L17
        L18:
            r03.show();
            return;
        L10:
            r22 = -AndroidUtilities.dp(8.0f);
            goto L11
        L5:
            r2 = 3;
            goto L6
        }

        @Override // android.view.View
        public void setTranslationY(float r7) {
            super.setTranslationY(r7);
            float r72 = MathUtils.clamp((r7 - AndroidUtilities.statusBarHeight) / AndroidUtilities.dp(64.0f), 0.0f, 1.0f);
            if (TranslateAlert2.m13407$$Nest$mhasEnoughHeight(this.this$0) == true) goto L5;
            r72 = 1.0f;
        L5:
            float r73 = CubicBezierInterpolator.EASE_OUT.getInterpolation(r72);
            this.titleTextView.setScaleX(AndroidUtilities.lerp(0.85f, 1.0f, r73));
            this.titleTextView.setScaleY(AndroidUtilities.lerp(0.85f, 1.0f, r73));
            this.titleTextView.setTranslationY(AndroidUtilities.lerp(AndroidUtilities.dpf2(-12.0f), 0.0f, r73));
            if (LocaleController.isRTL == true) goto L8;
            this.titleTextView.setTranslationX(AndroidUtilities.lerp(AndroidUtilities.dpf2(50.0f), 0.0f, r73));
            this.subtitleView.setTranslationX(AndroidUtilities.lerp(AndroidUtilities.dpf2(50.0f), 0.0f, r73));
        L8:
            this.subtitleView.setTranslationY(AndroidUtilities.lerp(AndroidUtilities.dpf2(-22.0f), 0.0f, r73));
            this.backButton.setTranslationX(AndroidUtilities.lerp(0.0f, AndroidUtilities.dpf2(-25.0f), r73));
            float r1 = 1.0f - r73;
            this.backButton.setAlpha(r1);
            this.copyButton.setTranslationX(AndroidUtilities.lerp(AndroidUtilities.dpf2(14.0f), AndroidUtilities.dpf2(8.0f), r73));
            this.copyButton.setTranslationY(AndroidUtilities.lerp(AndroidUtilities.dpf2(0.0f), AndroidUtilities.dpf2(16.0f), r73));
            this.copyButton.setColorFilter(ColorUtils.blendARGB(TranslateAlert2.access$2600(this.this$0, Theme.key_dialogTextBlack), TranslateAlert2.access$2700(this.this$0, Theme.key_player_actionBarSubtitle), r73), PorterDuff.Mode.MULTIPLY);
            this.shadow.setTranslationY(AndroidUtilities.lerp(0.0f, AndroidUtilities.dpf2(22.0f), r73));
            this.shadow.setAlpha(r1);
        }
    }

    public class LoadingTextView extends TextView {
        private final LoadingDrawable loadingDrawable;
        private final LinkPath path;
        final /* synthetic */ TranslateAlert2 this$0;

        public LoadingTextView(TranslateAlert2 r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            LinkPath r12 = new LinkPath(true);
            this.path = r12;
            LoadingDrawable r22 = new LoadingDrawable();
            this.loadingDrawable = r22;
            r22.usePath(r12);
            r22.setSpeed(0.65f);
            r22.setRadiiDp(4.0f);
            setBackground(r22);
        }

        private void updateDrawable() {
            LinkPath r0 = this.path;
            if (r0 != null) goto L5;
            return;
        L5:
            if (this.loadingDrawable == null) goto L15;
            r0.rewind();
            if (getLayout() != null) goto L10;
        L12:
            this.loadingDrawable.updateBounds();
            return;
        L10:
            if (getLayout().getText() == null) goto L12;
            this.path.setCurrentLayout(getLayout(), 0, getPaddingLeft(), getPaddingTop());
            getLayout().getSelectionPath(0, getLayout().getText().length(), this.path);
            goto L12
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.loadingDrawable.reset();
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            updateDrawable();
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r1, TextView.BufferType r2) {
            super.setText(r1, r2);
            updateDrawable();
        }

        @Override // android.widget.TextView
        public void setTextColor(int r5) {
            super.setTextColor(Theme.multAlpha(r5, 0.2f));
            this.loadingDrawable.setColors(Theme.multAlpha(r5, 0.03f), Theme.multAlpha(r5, 0.175f), Theme.multAlpha(r5, 0.2f), Theme.multAlpha(r5, 0.45f));
        }
    }

    public static class PaddedAdapter extends RecyclerView.Adapter {
        private Context mContext;
        private View mMainView;
        private int mainViewType;

        /* renamed from: org.telegram.ui.Components.TranslateAlert2$PaddedAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ PaddedAdapter this$0;

            public AnonymousClass1(PaddedAdapter r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r3, int r4) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (AndroidUtilities.displaySize.y * 0.4f), TLObject.FLAG_30));
            }
        }

        /* renamed from: -$$Nest$fgetmMainView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m13417$$Nest$fgetmMainView(PaddedAdapter r0) {
            return r0.mMainView;
        }

        public PaddedAdapter(Context r2, View r3) {
            this.mainViewType = 1;
            this.mContext = r2;
            this.mMainView = r3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return 2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 != 0) goto L6;
            return 0;
        L6:
            return this.mainViewType;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L6;
            return new RecyclerListView.Holder(new AnonymousClass1(this, this.mContext));
        L6:
            return new RecyclerListView.Holder(this.mMainView);
        }

        public void updateMainView(View r3) {
            if (this.mMainView != r3) goto L5;
            return;
        L5:
            this.mainViewType++;
            this.mMainView = r3;
            notifyItemChanged(1);
        }
    }

    public static /* synthetic */ void $r8$lambda$0EnTe7f_rrxXwR3Kj5loBrXB1gw(boolean[] r2, ArrayList r3, int r4, Utilities.Callback2 r5, String r6, Boolean r7) {
        if (r2[0] == false) goto L6;
        return;
    L6:
        if (r6 == null) goto L16;
        r3.set(r4, r6);
        int r42 = 0;
    L9:
        if (r42 >= r3.size()) goto L14;
        if (r3.get(r42) == null) goto L20;
        r42 = r42 + 1;
        goto L9
    L20:
        return;
    L14:
        r2[0] = true;
        r5.run(TextUtils.join(_UrlKt.FRAGMENT_ENCODE_SET, r3), Boolean.FALSE);
        return;
    L16:
        r2[0] = true;
        r5.run(null, r7);
    }

    public static /* synthetic */ void $r8$lambda$3u54QgPdS6I8Bf51ghDdskVS7v4(TranslateAlert2 r0, TLRPC.TL_textWithEntities r1, TLRPC.TL_textWithEntities r2, TLRPC.TL_error r3) {
        r0.lambda$translate$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$5Ge2I6AumC53y0nPnMMkPm3Gzc4(String r0, String r1, Utilities.Callback2 r2, String r3) {
        alternativeTranslate(r0, r3, r1, r2);
    }

    /* renamed from: $r8$lambda$7rfZwD19e3bftL8-Wc7aC3kgIMM, reason: not valid java name */
    public static /* synthetic */ void m13381$r8$lambda$7rfZwD19e3bftL8Wc7aC3kgIMM(TranslateAlert2 r0, Integer r1, String r2) {
        r0.lambda$translate$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$UWvyg6hGvbZlGavdInsfKYJOOfc(TranslateAlert2 r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$translate$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$YJvJk47_N6nMkYbwSvgDQG2k1zs(TranslateAlert2 r0, TLObject r1) {
        r0.lambda$translate$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$kbWC1iBRRTDdn7x8ZVp2WlDoAqQ(TranslateAlert2 r0, Utilities.Callback2 r1, TLRPC.TL_textWithEntities r2) {
        r0.lambda$translate$4(r1, r2);
    }

    /* renamed from: $r8$lambda$oOxNoZY7-93R7A8hD77are-1q6Y, reason: not valid java name */
    public static /* synthetic */ void m13382$r8$lambda$oOxNoZY793R7A8hD77are1q6Y(TranslateAlert2 r0, String r1, Boolean r2) {
        r0.lambda$translateAlt$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$szmsaOr0449pnp64ClhXmzcRG24(TranslateAlert2 r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$xA6D78R26uj49onSHze_InLCZVE(String r0, String r1, Utilities.Callback2 r2, Exception r3) {
        alternativeTranslate(r0, "en", r1, r2);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaddedAdapter m13383$$Nest$fgetadapter(TranslateAlert2 r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetfirstTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13384$$Nest$fgetfirstTranslation(TranslateAlert2 r0) {
        return r0.firstTranslation;
    }

    /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m13385$$Nest$fgetfragment(TranslateAlert2 r0) {
        return r0.fragment;
    }

    /* renamed from: -$$Nest$fgetfromLanguage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m13386$$Nest$fgetfromLanguage(TranslateAlert2 r0) {
        return r0.fromLanguage;
    }

    /* renamed from: -$$Nest$fgetheaderView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HeaderView m13387$$Nest$fgetheaderView(TranslateAlert2 r0) {
        return r0.headerView;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m13388$$Nest$fgetlistView(TranslateAlert2 r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetloadingTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LoadingTextView m13389$$Nest$fgetloadingTextView(TranslateAlert2 r0) {
        return r0.loadingTextView;
    }

    /* renamed from: -$$Nest$fgetmainButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ButtonWithCounterView m13390$$Nest$fgetmainButton(TranslateAlert2 r0) {
        return r0.mainButton;
    }

    /* renamed from: -$$Nest$fgetonLinkPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.CallbackReturn m13391$$Nest$fgetonLinkPress(TranslateAlert2 r0) {
        return r0.onLinkPress;
    }

    /* renamed from: -$$Nest$fgetreqRichMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_iv.RichMessage m13392$$Nest$fgetreqRichMessage(TranslateAlert2 r0) {
        return r0.reqRichMessage;
    }

    /* renamed from: -$$Nest$fgetrichLoadingPreviewView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichMessageLayout.PreviewView m13393$$Nest$fgetrichLoadingPreviewView(TranslateAlert2 r0) {
        return r0.richLoadingPreviewView;
    }

    /* renamed from: -$$Nest$fgetrichPreviewView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichMessageLayout.PreviewView m13394$$Nest$fgetrichPreviewView(TranslateAlert2 r0) {
        return r0.richPreviewView;
    }

    /* renamed from: -$$Nest$fgettextSelectionHelper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextSelectionHelper.ArticleTextSelectionHelper m13395$$Nest$fgettextSelectionHelper(TranslateAlert2 r0) {
        return r0.textSelectionHelper;
    }

    /* renamed from: -$$Nest$fgettextSelectionOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextSelectionHelper.TextSelectionOverlay m13396$$Nest$fgettextSelectionOverlay(TranslateAlert2 r0) {
        return r0.textSelectionOverlay;
    }

    /* renamed from: -$$Nest$fgettextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinkSpanDrawable.LinksTextView m13397$$Nest$fgettextView(TranslateAlert2 r0) {
        return r0.textView;
    }

    /* renamed from: -$$Nest$fgettextViewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m13398$$Nest$fgettextViewContainer(TranslateAlert2 r0) {
        return r0.textViewContainer;
    }

    /* renamed from: -$$Nest$fgettoLanguage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m13399$$Nest$fgettoLanguage(TranslateAlert2 r0) {
        return r0.toLanguage;
    }

    /* renamed from: -$$Nest$fputfirstTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13400$$Nest$fputfirstTranslation(TranslateAlert2 r0, boolean r1) {
        r0.firstTranslation = r1;
    }

    /* renamed from: -$$Nest$fputprevToLanguage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13401$$Nest$fputprevToLanguage(TranslateAlert2 r0, String r1) {
        r0.prevToLanguage = r1;
    }

    /* renamed from: -$$Nest$fputreqId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13402$$Nest$fputreqId(TranslateAlert2 r0, Integer r1) {
        r0.reqId = r1;
    }

    /* renamed from: -$$Nest$fputsheetTopNotAnimate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13403$$Nest$fputsheetTopNotAnimate(TranslateAlert2 r0, boolean r1) {
        r0.sheetTopNotAnimate = r1;
    }

    /* renamed from: -$$Nest$fputtoLanguage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13404$$Nest$fputtoLanguage(TranslateAlert2 r0, String r1) {
        r0.toLanguage = r1;
    }

    /* renamed from: -$$Nest$mgetSheetTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13405$$Nest$mgetSheetTop(TranslateAlert2 r0) {
        return r0.getSheetTop();
    }

    /* renamed from: -$$Nest$mgetSheetTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13406$$Nest$mgetSheetTop(TranslateAlert2 r0, boolean r1) {
        return r0.getSheetTop(r1);
    }

    /* renamed from: -$$Nest$mhasEnoughHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13407$$Nest$mhasEnoughHeight(TranslateAlert2 r0) {
        return r0.hasEnoughHeight();
    }

    /* renamed from: -$$Nest$mpreprocessText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m13408$$Nest$mpreprocessText(TranslateAlert2 r0, CharSequence r1) {
        return r0.preprocessText(r1);
    }

    /* renamed from: -$$Nest$mupdateButtonShadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13409$$Nest$mupdateButtonShadow(TranslateAlert2 r0, boolean r1) {
        r0.updateButtonShadow(r1);
    }

    static {
        userAgents = new String[]{"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:94.0) Gecko/20100101 Firefox/94.0", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:95.0) Gecko/20100101 Firefox/95.0", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.55 Safari/537.36"};
    }

    private TranslateAlert2(Context r4, String r5, String r6, CharSequence r7, ArrayList<TLRPC.MessageEntity> r8, TLRPC.InputPeer r9, int r10, boolean r11, TL_iv.RichMessage r12, Theme.ResourcesProvider r13) {
        super(r4, false, r13);
        this.firstTranslation = true;
        this.backgroundPaddingLeft = 0;
        fixNavigationBar();
        this.reqText = r7;
        this.reqPeer = r9;
        this.reqMessageId = r10;
        this.reqSum = r11;
        this.reqRichMessage = r12;
        this.fromLanguage = r5;
        this.toLanguage = r6;
        ContainerView r52 = new ContainerView(this, r4);
        this.containerView = r52;
        this.sheetTopAnimated = new AnimatedFloat(r52, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        LoadingTextView r53 = new LoadingTextView(this, r4);
        this.loadingTextView = r53;
        r53.setPadding(AndroidUtilities.dp(22.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(6.0f));
        this.loadingTextView.setTextSize(1, SharedConfig.fontSize);
        LoadingTextView r54 = this.loadingTextView;
        int r92 = Theme.key_dialogTextBlack;
        r54.setTextColor(getThemedColor(r92));
        this.loadingTextView.setLinkTextColor(Theme.multAlpha(getThemedColor(r92), 0.2f));
        LoadingTextView r55 = this.loadingTextView;
        if (r7 != null) goto L5;
        String r72 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r55.setText(Emoji.replaceEmoji(r72, this.loadingTextView.getPaint().getFontMetricsInt(), true));
        this.textViewContainer = new AnonymousClass1(this, r4);
        LinkSpanDrawable.LinksTextView r56 = new LinkSpanDrawable.LinksTextView(r4, r13);
        this.textView = r56;
        r56.setDisablePaddingsOffsetY(true);
        this.textView.setPadding(AndroidUtilities.dp(22.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(6.0f));
        this.textView.setTextSize(1, SharedConfig.fontSize);
        this.textView.setTextColor(getThemedColor(r92));
        this.textView.setLinkTextColor(getThemedColor(Theme.key_chat_messageLinkIn));
        this.textView.setTextIsSelectable(true);
        this.textView.setHighlightColor(getThemedColor(Theme.key_chat_inTextSelectionHighlight));
        int r57 = getThemedColor(Theme.key_chat_TextSelectionCursor);
        if (Build.VERSION.SDK_INT < 29) goto L12;
        if (XiaomiUtilities.isMIUI() == true) goto L12;
        Drawable r73 = this.textView.getTextSelectHandleLeft();     // Catch: Exception -> L24
        PorterDuff.Mode r93 = PorterDuff.Mode.SRC_IN;     // Catch: Exception -> L24
        r73.setColorFilter(r57, r93);     // Catch: Exception -> L24
        this.textView.setTextSelectHandleLeft(r73);     // Catch: Exception -> L24
        Drawable r74 = this.textView.getTextSelectHandleRight();     // Catch: Exception -> L24
        r74.setColorFilter(r57, r93);     // Catch: Exception -> L24
        this.textView.setTextSelectHandleRight(r74);     // Catch: Exception -> L24
    L12:
        this.textViewContainer.addView(this.textView, LayoutHelper.createFrame(-1, -1.0f));
        if (this.reqRichMessage == null) goto L15;
        RichMessageLayout.PreviewView r58 = new RichMessageLayout.PreviewView(r4, this.currentAccount, r13);
        this.richLoadingPreviewView = r58;
        r58.setPadding(AndroidUtilities.dp(22.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(6.0f));
        this.richLoadingPreviewView.set(this.reqRichMessage);
        this.richLoadingPreviewView.setTranslationLoading(true);
        RichMessageLayout.PreviewView r59 = new RichMessageLayout.PreviewView(r4, this.currentAccount, r13);
        this.richPreviewView = r59;
        r59.setPadding(AndroidUtilities.dp(22.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(6.0f));
    L15:
        AnonymousClass2 r510 = new AnonymousClass2(this, r4);
        this.listView = r510;
        r510.setOverScrollMode(1);
        this.listView.setPadding(0, AndroidUtilities.statusBarHeight + AndroidUtilities.dp(56.0f), 0, AndroidUtilities.dp(80.0f));
        this.listView.setClipToPadding(true);
        RecyclerListView r511 = this.listView;
        LinearLayoutManager r62 = new LinearLayoutManager(r4);
        this.layoutManager = r62;
        r511.setLayoutManager(r62);
        RecyclerListView r512 = this.listView;
        if (this.reqRichMessage == null) goto L18;
        View r75 = this.richLoadingPreviewView;
    L19:
        PaddedAdapter r63 = new PaddedAdapter(r4, r75);
        this.adapter = r63;
        r512.setAdapter(r63);
        this.listView.setOnScrollListener(new AnonymousClass3(this));
        AnonymousClass4 r513 = new AnonymousClass4(this);
        r513.setDurations(180);
        r513.setInterpolator(new LinearInterpolator());
        this.listView.setItemAnimator(r513);
        this.containerView.addView(this.listView, LayoutHelper.createFrame(-1, -2, 80));
        TextSelectionHelper.ArticleTextSelectionHelper r514 = new TextSelectionHelper.ArticleTextSelectionHelper();
        this.textSelectionHelper = r514;
        r514.setParentView(this.listView);
        TextSelectionHelper.ArticleTextSelectionHelper r515 = this.textSelectionHelper;
        r515.layoutManager = this.layoutManager;
        TextSelectionHelper<Cell>.TextSelectionOverlay r516 = r515.getOverlayView(r4);
        this.textSelectionOverlay = r516;
        AndroidUtilities.removeFromParent(r516);
        this.containerView.addView(this.textSelectionOverlay, LayoutHelper.createFrame(-1, -1, 119));
        RichMessageLayout.PreviewView r517 = this.richPreviewView;
        if (r517 == null) goto L22;
        r517.setTextSelectionHelper(this.textSelectionHelper);
    L22:
        HeaderView r518 = new HeaderView(this, r4);
        this.headerView = r518;
        this.containerView.addView(r518, LayoutHelper.createFrame(-1, 78, 55));
        ButtonWithCounterView r519 = new ButtonWithCounterView(r4, r13);
        this.mainButton = r519;
        r519.setRound();
        this.mainButton.setColor(Theme.getColor(Theme.key_featuredStickers_addButton));
        this.mainButton.setText(LocaleController.getString(R.string.CloseTranslation), false);
        this.mainButton.setLoading(true);
        this.mainButton.setOnClickListener(new TranslateAlert2$$ExternalSyntheticLambda3(this));
        this.containerView.addView(this.mainButton, LayoutHelper.createFrame(-1, 48.0f, 87, 16.0f, 16.0f, 16.0f, 16.0f));
        translate();
        return;
    L18:
        r75 = this.loadingTextView;
        goto L19
    L5:
        r72 = r7.toString();
        goto L6
    }

    public /* synthetic */ TranslateAlert2(Context r1, String r2, String r3, CharSequence r4, ArrayList r5, TLRPC.InputPeer r6, int r7, boolean r8, TL_iv.RichMessage r9, Theme.ResourcesProvider r10, TranslateAlert2IA r11) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    public TranslateAlert2(Context r12, String r13, String r14, CharSequence r15, ArrayList<TLRPC.MessageEntity> r16, Theme.ResourcesProvider r17) {
        this(r12, r13, r14, r15, r16, null, 0, false, null, r17);
    }

    public static /* synthetic */ ViewGroup access$000(TranslateAlert2 r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$100(TranslateAlert2 r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$1000(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1100(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1200(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1300(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1400(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1500(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1600(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1700(TranslateAlert2 r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$1800(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1900(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ ViewGroup access$200(TranslateAlert2 r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$2000(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ ViewGroup access$2100(TranslateAlert2 r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2200(TranslateAlert2 r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$2300(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2400(TranslateAlert2 r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$2500(TranslateAlert2 r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$2600(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2700(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ ViewGroup access$2800(TranslateAlert2 r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2900(TranslateAlert2 r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$300(TranslateAlert2 r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$3000(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$3100(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$3200(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$400(TranslateAlert2 r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$500(TranslateAlert2 r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$600(TranslateAlert2 r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$700(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$800(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$900(TranslateAlert2 r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static void alternativeTranslate(String r5, String r6, String r7, Utilities.Callback2<String, Boolean> r8) {
        if (r8 == null) goto L17;
        if (r6 != null) goto L7;
        LanguageDetector.detectLanguage(r5, new TranslateAlert2$$ExternalSyntheticLambda0(r5, r7, r8), new TranslateAlert2$$ExternalSyntheticLambda1(r5, r7, r8));
        return;
    L7:
        String r52 = Uri.encode(r5);
        if (r52.length() <= 5000) goto L18;
        ArrayList<String> r53 = cut(r52, 5000);
        ArrayList r0 = new ArrayList();
        int r1 = 0;
        int r2 = 0;
    L11:
        if (r2 >= r53.size()) goto L13;
        r0.add(null);
        r2 = r2 + 1;
        goto L11
    L13:
        boolean[] r22 = new boolean[1];
    L15:
        if (r1 >= r53.size()) goto L22;
        alternativeTranslateInternal(r53.get(r1), r6, r7, new TranslateAlert2$$ExternalSyntheticLambda2(r22, r0, r1, r8));
        r1 = r1 + 1;
        goto L15
    L22:
        return;
    L18:
        alternativeTranslateInternal(r52, r6, r7, r8);
        return;
    }

    private static void alternativeTranslateInternal(String r1, String r2, String r3, Utilities.Callback2<String, Boolean> r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        new AnonymousClass7(r2, r3, r1, r4).start();
    }

    public static CharSequence capitalFirst(CharSequence r3) {
        if (r3 != null) goto L4;
        return null;
    L4:
        if (r3.length() > 0) goto L7;
        return null;
    L7:
        if ((r3 instanceof SpannableStringBuilder) == false) goto L9;
        SpannableStringBuilder r32 = (SpannableStringBuilder) r3;
    L10:
        r32.replace(0, 1, r32.toString().substring(0, 1).toUpperCase());
        return r32;
    L9:
        r32 = SpannableStringBuilder.valueOf(r3);
        goto L10
    }

    public static String capitalFirst(String r3) {
        if (r3 != null) goto L4;
        return null;
    L4:
        if (r3.length() > 0) goto L7;
        return null;
    L7:
        return r3.substring(0, 1).toUpperCase() + r3.substring(1);
    }

    public static ArrayList<String> cut(String r5, int r6) {
        ArrayList<String> r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r5.length()) goto L12;
        int r2 = Math.min(r1 + r6, r5.length());
        int r3 = lastIndexOfSafe(r5, "%0A", r1, r2);
        if (r3 != (-1)) goto L8;
        r3 = lastIndexOfSafe(r5, "%20", r1, r2);
    L8:
        if (r3 == (-1)) goto L11;
        r2 = r3 + 3;
    L11:
        r0.add(r5.substring(r1, r2));
        r1 = r2;
        goto L4
    L12:
        return r0;
    }

    private float getSheetTop() {
        return getSheetTop(true);
    }

    private float getSheetTop(boolean r5) {
        float r0 = this.listView.getTop();
        if (this.listView.getChildCount() < 1) goto L5;
        RecyclerListView r1 = this.listView;
        r0 = r0 + Math.max(0, r1.getChildAt(r1.getChildCount() - 1).getTop());
    L5:
        float r02 = Math.max(0.0f, r0 - AndroidUtilities.dp(78.0f));
        if (r5 == false) goto L16;
        AnimatedFloat r52 = this.sheetTopAnimated;
        if (r52 == null) goto L16;
        if (this.listView.scrollingByUser == false) goto L12;
    L15:
        r52.set(r02, true);
        goto L16
    L12:
        if (this.sheetTopNotAnimate == true) goto L15;
        return r52.set(r02);
    L16:
        return r02;
    }

    public static String getToLanguage() {
        return TranslatorUtils.getResolvedTargetLanguageCode();
    }

    private static HashMap<String, ArrayList<Emoji.EmojiSpanRange>> groupEmojiRanges(CharSequence r6) {
        HashMap<String, ArrayList<Emoji.EmojiSpanRange>> r0 = new HashMap();
        if (r6 == null) goto L21;
        ArrayList<Emoji.EmojiSpanRange> r1 = Emoji.parseEmojis(r6);
        if (r1 == null) goto L21;
        String r62 = r6.toString();
        int r2 = 0;
    L10:
        if (r2 >= r1.size()) goto L21;
        Emoji.EmojiSpanRange r3 = r1.get(r2);
        if (r3 == null) goto L20;
        if (r3.code == null) goto L20;
        String r4 = r62.substring(r3.start, r3.end);
        ArrayList<Emoji.EmojiSpanRange> r5 = r0.get(r4);
        if (r5 != null) goto L19;
        r5 = new ArrayList();
        r0.put(r4, r5);
    L19:
        r5.add(r3);
    L20:
        r2 = r2 + 1;
    L21:
        return r0;
    }

    private boolean hasEnoughHeight() {
        float r0 = 0.0f;
        int r2 = 0;
    L3:
        int r3 = this.listView.getChildCount();
        RecyclerListView r4 = this.listView;
        if (r2 >= r3) goto L10;
        if (this.listView.getChildAdapterPosition(r4.getChildAt(r2)) != 1) goto L8;
        r0 = r0 + r3.getHeight();
    L8:
        r2 = r2 + 1;
        goto L3
    L10:
        if (r0 < ((r4.getHeight() - this.listView.getPaddingTop()) - this.listView.getPaddingBottom())) goto L12;
        return true;
    L12:
        return false;
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$translate$1(TLObject r3) {
        this.reqId = null;
        if ((r3 instanceof TLRPC.TL_messages_translatedRichMessage) == false) goto L14;
        TLRPC.TL_messages_translatedRichMessage r32 = (TLRPC.TL_messages_translatedRichMessage) r3;
        if (r32.result.isEmpty() == true) goto L14;
        if (r32.result.get(0) == null) goto L14;
        this.firstTranslation = false;
        TL_iv.RichMessage r33 = r32.result.get(0);
        RichMessageLayout.PreviewView r0 = this.richPreviewView;
        if (r0 == null) goto L11;
        r0.set(r33);
        this.adapter.updateMainView(this.richPreviewView);
    L11:
        this.mainButton.setLoading(false);
        return;
    L14:
        if (this.firstTranslation == false) goto L17;
        dismiss();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.getString(R.string.TranslationFailedAlert2)});
        return;
    L17:
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createErrorBulletin(LocaleController.getString(R.string.TranslationFailedAlert2)).show();
        AnimatedTextView r34 = HeaderView.m13416$$Nest$fgettoLanguageTextView(this.headerView);
        String r02 = this.prevToLanguage;
        this.toLanguage = r02;
        r34.setText(languageName(r02));
        this.mainButton.setLoading(false);
    }

    private /* synthetic */ void lambda$translate$2(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new TranslateAlert2$$ExternalSyntheticLambda9(this, r1));
    }

    private /* synthetic */ void lambda$translate$3(TLRPC.TL_textWithEntities r7, TLRPC.TL_textWithEntities r8, TLRPC.TL_error r9) {
        this.reqId = null;
        if (r9 != null) goto L5;
    L8:
        if (r8 == null) goto L12;
        this.firstTranslation = false;
        TLRPC.TL_textWithEntities r72 = preprocess(r7, r8);
        SpannableStringBuilder r0 = SpannableStringBuilder.valueOf(r72.text);
        MessageObject.addEntitiesToText(r0, r72.entities, false, true, false, false);
        CharSequence r73 = preprocessText(r0);
        this.textView.setText(r73);
        this.adapter.updateMainView(this.textViewContainer);
        return;
    L12:
        if (this.firstTranslation == false) goto L15;
        dismiss();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.getString(R.string.TranslationFailedAlert2)});
        return;
    L15:
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createErrorBulletin(LocaleController.getString(R.string.TranslationFailedAlert2)).show();
        AnimatedTextView r74 = HeaderView.m13416$$Nest$fgettoLanguageTextView(this.headerView);
        String r82 = this.prevToLanguage;
        this.toLanguage = r82;
        r74.setText(languageName(r82));
        this.adapter.updateMainView(this.textViewContainer);
        return;
    L5:
        if ("TRANSLATIONS_DISABLED_ALT".equalsIgnoreCase(r9.text) == false) goto L8;
        translateAlt();
    }

    private /* synthetic */ void lambda$translate$4(Utilities.Callback2 r2, TLRPC.TL_textWithEntities r3) {
        this.aiReplacementCancel = null;
        r2.run(r3, null);
    }

    private /* synthetic */ void lambda$translate$5(Integer r4, String r5) {
        this.aiReplacementCancel = null;
        if (this.firstTranslation == false) goto L8;
        dismiss();
        BaseFragment r3 = LaunchActivity.getSafeLastFragment();
        if (r3 == null) goto L10;
        AiController.showErrorBulletin(r3, r4.intValue(), r5);
        return;
    L10:
        return;
    L8:
        AiController.showErrorBulletin((FrameLayout) this.containerView, this.resourcesProvider, r4.intValue(), r5, null);
        AnimatedTextView r42 = HeaderView.m13416$$Nest$fgettoLanguageTextView(this.headerView);
        String r52 = this.prevToLanguage;
        this.toLanguage = r52;
        r42.setText(languageName(r52));
        this.adapter.updateMainView(this.textViewContainer);
    }

    private /* synthetic */ void lambda$translateAlt$6(String r2, Boolean r3) {
        if (r2 == null) goto L6;
        this.firstTranslation = false;
        this.textView.setText(preprocessText(r2));
        this.adapter.updateMainView(this.textViewContainer);
        return;
    L6:
        if (isDismissed() == false) goto L9;
        return;
    L9:
        if (this.firstTranslation == false) goto L16;
        dismiss();
        NotificationCenter r1 = NotificationCenter.getGlobalInstance();
        int r22 = NotificationCenter.showBulletin;
        if (r3.booleanValue() == false) goto L13;
        int r32 = R.string.TranslationFailedAlert1;
    L14:
        r1.postNotificationName(r22, new Object[]{1, LocaleController.getString(r32)});
        return;
    L13:
        r32 = R.string.TranslationFailedAlert2;
        goto L14
    L16:
        BulletinFactory r23 = BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider);
        if (r3.booleanValue() == false) goto L19;
        int r33 = R.string.TranslationFailedAlert1;
    L20:
        r23.createErrorBulletin(LocaleController.getString(r33)).show();
        AnimatedTextView r24 = HeaderView.m13416$$Nest$fgettoLanguageTextView(this.headerView);
        String r34 = this.prevToLanguage;
        this.toLanguage = r34;
        r24.setText(languageName(r34));
        this.adapter.updateMainView(this.textViewContainer);
        return;
    L19:
        r33 = R.string.TranslationFailedAlert2;
        goto L20
    }

    public static String languageName(String r1) {
        return languageName(r1, null, null);
    }

    public static String languageName(String r1, boolean[] r2) {
        return languageName(r1, r2, null);
    }

    public static String languageName(String r9, boolean[] r10, boolean[] r11) {
        if (r9 != null) goto L5;
    L51:
        return null;
    L5:
        if (r9.equals(TranslateController.UNKNOWN_LANGUAGE) == true) goto L51;
        if (r9.equals("auto") == true) goto L51;
        String r1 = r9.split("_")[0];
        if ("nb".equals(r1) == false) goto L12;
        r1 = "no";
    L12:
        boolean r4 = true;
        if (r10 == null) goto L23;
        String r7 = LocaleController.getString("TranslateLanguage" + r1.toUpperCase());
        if (r7 != null) goto L17;
    L19:
        boolean r8 = false;
    L20:
        r10[0] = r8;
        if (r8 == false) goto L23;
        return r7;
    L17:
        if (r7.startsWith("LOC_ERR") == true) goto L19;
        r8 = true;
    L23:
        if (r11 == null) goto L33;
        String r102 = LocaleController.getString("TranslateLanguageGenitive" + r1.toUpperCase());
        if (r102 != null) goto L27;
    L29:
        r4 = false;
    L30:
        r11[0] = r4;
        if (r4 == false) goto L33;
        return r102;
    L27:
        if (r102.startsWith("LOC_ERR") == true) goto L29;
    L33:
        String r103 = systemLanguageName(r9);
        if (r103 != null) goto L36;
        r103 = systemLanguageName(r1);
    L36:
        if (r103 == null) goto L39;
        return r103;
    L39:
        if ("no".equals(r9) == false) goto L41;
        r9 = "nb";
    L41:
        LocaleController.LocaleInfo r104 = LocaleController.getInstance().getCurrentLocaleInfo();
        LocaleController.LocaleInfo r92 = LocaleController.getInstance().getBuiltinLanguageByPlural(r9);
        if (r92 != null) goto L44;
        return null;
    L44:
        if (r104 == null) goto L50;
        if ("en".equals(r104.pluralLangCode) == false) goto L50;
        return r92.nameEnglish;
    L50:
        return r92.name;
    }

    public static String languageNameCapital(String r3) {
        String r32 = languageName(r3);
        if (r32 != null) goto L7;
        return null;
    L7:
        return r32.substring(0, 1).toUpperCase() + r32.substring(1);
    }

    private static int lastIndexOfSafe(String r0, String r1, int r2, int r3) {
        int r02 = r0.lastIndexOf(r1, r3 - 1);
        if (r02 < r2) goto L5;
        return r02;
    L5:
        return -1;
    }

    public static TLRPC.TL_textWithEntities preprocess(TLRPC.TL_textWithEntities r11, TLRPC.TL_textWithEntities r12) {
        if (r12 != null) goto L4;
        return null;
    L4:
        if (r12.text == null) goto L113;
        int r1 = 0;
    L8:
        if (r1 >= r12.entities.size()) goto L35;
        TLRPC.MessageEntity r2 = r12.entities.get(r1);
        if ((r2 instanceof TLRPC.TL_messageEntityTextUrl) == false) goto L25;
        if (r2.url == null) goto L34;
        String r3 = r12.text;
        int r4 = r2.offset;
        String r32 = r3.substring(r4, r2.length + r4);
        if (TextUtils.equals(r32, r2.url) == false) goto L18;
        TLRPC.TL_messageEntityUrl r33 = new TLRPC.TL_messageEntityUrl();
        r33.offset = r2.offset;
        r33.length = r2.length;
        r12.entities.set(r1, r33);
        goto L34
    L18:
        if (r2.url.startsWith("https://t.me/") == false) goto L34;
        if (r32.startsWith("@") == false) goto L34;
        if (TextUtils.equals(r32.substring(1), r2.url.substring(13)) == false) goto L34;
        TLRPC.TL_messageEntityMention r34 = new TLRPC.TL_messageEntityMention();
        r34.offset = r2.offset;
        r34.length = r2.length;
        r12.entities.set(r1, r34);
    L34:
        r1 = r1 + 1;
        goto L8
    L25:
        if ((r2 instanceof TLRPC.TL_messageEntityPre) == false) goto L34;
        if (r11 == null) goto L34;
        ArrayList<TLRPC.MessageEntity> r35 = r11.entities;
        if (r35 == null) goto L34;
        if (r1 >= r35.size()) goto L34;
        if ((r11.entities.get(r1) instanceof TLRPC.TL_messageEntityPre) == false) goto L34;
        r2.language = r11.entities.get(r1).language;
        goto L34
    L35:
        if (r11 != null) goto L37;
    L80:
        return r12;
    L37:
        if (r11.text == null) goto L80;
        if (r11.entities.isEmpty() == true) goto L80;
        HashMap<String, ArrayList<Emoji.EmojiSpanRange>> r13 = groupEmojiRanges(r11.text);
        HashMap<String, ArrayList<Emoji.EmojiSpanRange>> r22 = groupEmojiRanges(r12.text);
        int r36 = 0;
    L42:
        if (r36 >= r11.entities.size()) goto L80;
        TLRPC.MessageEntity r42 = r11.entities.get(r36);
        if ((r42 instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L79;
        String r5 = r11.text;
        int r6 = r42.offset;
        String r52 = r5.substring(r6, r42.length + r6);
        if (TextUtils.isEmpty(r52) == true) goto L79;
        ArrayList<Emoji.EmojiSpanRange> r62 = r13.get(r52);
        ArrayList<Emoji.EmojiSpanRange> r53 = r22.get(r52);
        if (r62 == null) goto L79;
        if (r53 == null) goto L79;
        int r7 = 0;
    L54:
        if (r7 >= r62.size()) goto L61;
        Emoji.EmojiSpanRange r8 = r62.get(r7);
        int r9 = r8.start;
        int r10 = r42.offset;
        if (r9 != r10) goto L60;
        if (r8.end != (r10 + r42.length)) goto L60;
    L62:
        if (r7 < 0) goto L79;
        if (r7 >= r53.size()) goto L79;
        Emoji.EmojiSpanRange r54 = r53.get(r7);
        if (r54 == null) goto L79;
        int r63 = 0;
    L71:
        if (r63 >= r12.entities.size()) goto L78;
        TLRPC.MessageEntity r72 = r12.entities.get(r63);
        if ((r72 instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L77;
        int r82 = r54.start;
        int r92 = r54.end;
        int r102 = r72.offset;
        if (AndroidUtilities.intersect1d(r82, r92, r102, r72.length + r102) == true) goto L79;
    L77:
        r63 = r63 + 1;
        goto L71
    L78:
        TLRPC.TL_messageEntityCustomEmoji r64 = new TLRPC.TL_messageEntityCustomEmoji();
        TLRPC.TL_messageEntityCustomEmoji r43 = (TLRPC.TL_messageEntityCustomEmoji) r42;
        r64.document_id = r43.document_id;
        r64.document = r43.document;
        int r44 = r54.start;
        r64.offset = r44;
        r64.length = r54.end - r44;
        r12.entities.add(r64);
    L60:
        r7 = r7 + 1;
        goto L54
    L61:
        r7 = -1;
    L79:
        r36 = r36 + 1;
        goto L42
    L113:
        return null;
    }

    private CharSequence preprocessText(CharSequence r7) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(r7);
        if (this.onLinkPress == null) goto L5;
    L6:
        int r2 = 0;
        URLSpan[] r72 = (URLSpan[]) r0.getSpans(0, r0.length(), URLSpan.class);
    L8:
        if (r2 >= r72.length) goto L16;
        URLSpan r1 = r72[r2];
        int r3 = r0.getSpanStart(r1);
        int r4 = r0.getSpanEnd(r1);
        if (r3 == (-1)) goto L14;
        if (r4 == (-1)) goto L14;
        r0.removeSpan(r1);
        r0.setSpan(new AnonymousClass8(this, r1), r3, r4, 33);
    L14:
        r2 = r2 + 1;
    L16:
        return Emoji.replaceEmoji(r0, this.textView.getPaint().getFontMetricsInt(), true);
    L5:
        if (this.fragment == null) goto L16;
        goto L6
    }

    public static void setToLanguage(String r0) {
        TranslatorUtils.setTargetLanguage(r0);
    }

    public static TranslateAlert2 showAlert(Context r8, BaseFragment r9, int r10, String r11, String r12, CharSequence r13, ArrayList<TLRPC.MessageEntity> r14, boolean r15, Utilities.CallbackReturn<URLSpan, Boolean> r16, Runnable r17) {
        if (r8 != null) goto L5;
        return null;
    L5:
        AnonymousClass11 r0 = new AnonymousClass11(r8, r11, r12, r13, r14, null, r17);
        r0.setNoforwards(r15);
        r0.setFragment(r9);
        r0.setOnLinkPress(r16);
        if (r9 != null) goto L8;
        r0.show();
        return r0;
    L8:
        if (r9.getParentActivity() == null) goto L10;
        r9.showDialog(r0);
    L10:
        return r0;
    }

    public static TranslateAlert2 showAlert(Context r12, BaseFragment r13, int r14, TLRPC.InputPeer r15, int r16, String r17, String r18, TL_iv.RichMessage r19, boolean r20, Utilities.CallbackReturn<URLSpan, Boolean> r21, Runnable r22) {
        if (r12 != null) goto L5;
        return null;
    L5:
        AnonymousClass10 r0 = new AnonymousClass10(r12, r17, r18, null, null, r15, r16, false, r19, null, r22);
        r0.setNoforwards(r20);
        r0.setFragment(r13);
        r0.setOnLinkPress(r21);
        if (r13 != null) goto L8;
        r0.show();
        return r0;
    L8:
        if (r13.getParentActivity() == null) goto L10;
        r13.showDialog(r0);
    L10:
        return r0;
    }

    public static TranslateAlert2 showAlert(Context r12, BaseFragment r13, int r14, TLRPC.InputPeer r15, int r16, boolean r17, String r18, String r19, CharSequence r20, ArrayList<TLRPC.MessageEntity> r21, boolean r22, Utilities.CallbackReturn<URLSpan, Boolean> r23, Runnable r24) {
        AnonymousClass9 r0 = new AnonymousClass9(r12, r18, r19, r20, r21, r15, r16, r17, null, null, r24);
        r0.setNoforwards(r22);
        r0.setFragment(r13);
        r0.setOnLinkPress(r23);
        if (r13 != null) goto L5;
        r0.show();
        return r0;
    L5:
        if (r13.getParentActivity() == null) goto L7;
        r13.showDialog(r0);
    L7:
        return r0;
    }

    public static String systemLanguageName(String r1) {
        return systemLanguageName(r1, false);
    }

    public static String systemLanguageName(String r8, boolean r9) {
        if (r8 != null) goto L6;
        return null;
    L6:
        if (localesByCode != null) goto L17;
        localesByCode = new HashMap();
        Locale[] r1 = Locale.getAvailableLocales();     // Catch: Exception -> L33
        int r3 = 0;
    L9:
        if (r3 >= r1.length) goto L17;
        localesByCode.put(r1[r3].getLanguage(), r1[r3]);     // Catch: Exception -> L33
        String r4 = r1[r3].getCountry();     // Catch: Exception -> L33
        if (r4 == null) goto L16;
        if (r4.length() <= 0) goto L16;
        localesByCode.put(r1[r3].getLanguage() + "-" + r4.toLowerCase(), r1[r3]);     // Catch: Exception -> L33
    L16:
        r3 = r3 + 1;
    L17:
        String r82 = r8.replace("_", "-").toLowerCase();
        Locale r12 = localesByCode.get(r82);     // Catch: Exception -> L34
        if (r12 == null) goto L32;
        if (r9 == false) goto L22;
        Locale r32 = r12;
    L23:
        String r33 = r12.getDisplayLanguage(r32);     // Catch: Exception -> L34
        if (r82.contains("-") == false) goto L43;
        if (r9 == false) goto L27;
        Locale r83 = r12;
    L28:
        String r84 = r12.getDisplayCountry(r83);     // Catch: Exception -> L34
        if (TextUtils.isEmpty(r84) == true) goto L44;
        return r33 + " (" + r84 + ")";
    L44:
        return r33;
    L27:
        r83 = Locale.getDefault();     // Catch: Exception -> L34
        goto L28
    L43:
        return r33;
    L22:
        r32 = Locale.getDefault();     // Catch: Exception -> L34
    L32:
        return null;
    }

    private void translateAlt() {
        CharSequence r0 = this.reqText;
        if (r0 != null) goto L5;
        String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        String r1 = this.fromLanguage;
        if (r1 == null) goto L9;
        r1 = r1.split("_")[0];
    L9:
        String r6 = "no";
        if ("nb".equals(r1) == false) goto L12;
        r1 = "no";
    L12:
        String r5 = this.toLanguage;
        if (r5 == null) goto L16;
        r5 = r5.split("_")[0];
    L16:
        if ("nb".equals(r5) == true) goto L19;
        r6 = r5;
    L19:
        alternativeTranslate(r02, r1, r6, new TranslateAlert2$$ExternalSyntheticLambda8(this));
        return;
    L5:
        r02 = r0.toString();
        goto L6
    }

    private void updateButtonShadow(boolean r2) {
        Boolean r0 = this.buttonShadowShown;
        if (r0 != null) goto L5;
    L8:
        this.buttonShadowShown = Boolean.valueOf(r2);
        return;
    L5:
        if (r0.booleanValue() != r2) goto L8;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.emojiLoaded) goto L6;
        this.loadingTextView.invalidate();
        this.textView.invalidate();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        Runnable r0 = this.aiReplacementCancel;
        if (r0 == null) goto L5;
        r0.run();
        this.aiReplacementCancel = null;
    L5:
        super.dismiss();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.translationModelDownloaded);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.translationModelDownloading);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        if (this.reqId == null) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId.intValue(), true);
        this.reqId = null;
    L5:
        super.dismissInternal();
    }

    public void setFragment(BaseFragment r1) {
        this.fragment = r1;
    }

    public void setNoforwards(boolean r3) {
        LinkSpanDrawable.LinksTextView r0 = this.textView;
        if (r0 == null) goto L6;
        r0.setTextIsSelectable(!r3);
    L6:
        if (r3 == false) goto L9;
        getWindow().addFlags(8192);
        AndroidUtilities.logFlagSecure();
        return;
    L9:
        getWindow().clearFlags(8192);
        AndroidUtilities.logFlagSecure();
    }

    public void setOnLinkPress(Utilities.CallbackReturn<URLSpan, Boolean> r1) {
        this.onLinkPress = r1;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.translationModelDownloaded);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.translationModelDownloading);
    }

    public void translate() {
        if (this.reqId == null) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId.intValue(), true);
        this.reqId = null;
    L5:
        Runnable r0 = this.aiReplacementCancel;
        if (r0 == null) goto L9;
        r0.run();
        this.aiReplacementCancel = null;
    L9:
        if ("alternative".equalsIgnoreCase(MessagesController.getInstance(this.currentAccount).translationsManualEnabled) == false) goto L12;
        translateAlt();
        return;
    L12:
        String r02 = this.toLanguage;
        if (r02 == null) goto L16;
        r02 = r02.split("_")[0];
    L16:
        if ("nb".equals(r02) == false) goto L18;
        r02 = "no";
    L18:
        String r6 = r02;
        if (this.reqRichMessage != null) goto L21;
    L24:
        TLRPC.TL_textWithEntities r03 = new TLRPC.TL_textWithEntities();
        CharSequence r1 = this.reqText;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 != null) goto L27;
        String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L28:
        r03.text = r12;
        ArrayList<TLRPC.MessageEntity> r13 = this.reqMessageEntities;
        if (r13 == null) goto L32;
        r03.entities = r13;
    L32:
        if (this.reqSum == true) goto L34;
    L41:
        TLRPC.TL_messages_translateText r14 = new TLRPC.TL_messages_translateText();
        TLRPC.InputPeer r4 = this.reqPeer;
        int r5 = r14.flags;
        if (r4 == null) goto L44;
        r14.flags = 1 | r5;
        r14.peer = r4;
        r14.id.add(Integer.valueOf(this.reqMessageId));
    L45:
        r14.to_lang = TranslateController.normalizeLanguage(r6);
        boolean r15 = TranslationProviders.isTelegram();
        CharSequence r2 = this.reqText;
        if (r15 == false) goto L52;
        if (r2 == null) goto L50;
        r3 = r2.toString();
    L50:
        TranslatorUtils.translateWithDefault(r3, this.reqPeer, this.reqMessageId, r6, this.reqMessageEntities, new AnonymousClass5(this, r03, r6));
        return;
    L52:
        if (r2 == null) goto L55;
        r3 = r2.toString();
    L55:
        TranslatorUtils.translate(r3, r6, this.reqMessageEntities, new AnonymousClass6(this, r6));
        return;
    L44:
        r14.flags = r5 | 2;
        r14.text.add(r03);
        goto L45
    L34:
        if (this.reqPeer == null) goto L41;
        TLRPC.TL_messages_summarizeText r16 = new TLRPC.TL_messages_summarizeText();
        r16.flags = 1 | r16.flags;
        r16.peer = this.reqPeer;
        r16.id = this.reqMessageId;
        r16.to_lang = TranslateController.normalizeLanguage(r6);
        TranslateAlert2$$ExternalSyntheticLambda5 r22 = new TranslateAlert2$$ExternalSyntheticLambda5(this, r03);
        if (TelegramAiReplacement.replacesSummaries(this.currentAccount) == false) goto L39;
        this.aiReplacementCancel = TelegramAiReplacement.translate(r03.text, r6, new TranslateAlert2$$ExternalSyntheticLambda6(this, r22), new TranslateAlert2$$ExternalSyntheticLambda7(this));
        return;
    L39:
        this.reqId = Integer.valueOf(ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r16, new AiTonesController$$ExternalSyntheticLambda0(), r22));
        return;
    L27:
        r12 = r1.toString();
        goto L28
    L21:
        if (this.reqPeer == null) goto L24;
        TLRPC.TL_messages_translateRichMessage r04 = new TLRPC.TL_messages_translateRichMessage();
        r04.flags |= 1;
        r04.peer = this.reqPeer;
        r04.id.add(Integer.valueOf(this.reqMessageId));
        r04.to_lang = TranslateController.normalizeLanguage(r6);
        this.reqId = Integer.valueOf(ConnectionsManager.getInstance(this.currentAccount).sendRequest(r04, new TranslateAlert2$$ExternalSyntheticLambda4(this)));
    }
}
