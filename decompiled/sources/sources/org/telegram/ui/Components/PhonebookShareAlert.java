package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.widget.NestedScrollView;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.gms.cast.MediaError;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.ChatAttachAlertContactsLayout;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes7.dex */
public class PhonebookShareAlert extends BottomSheet {
    private ActionBar actionBar;
    private AnimatorSet actionBarAnimation;
    private View actionBarShadow;
    private Paint backgroundPaint;
    private TextView buttonTextView;
    private TLRPC.User currentUser;
    private ChatAttachAlertContactsLayout.PhonebookShareAlertDelegate delegate;
    private boolean inLayout;
    private boolean isImport;
    private LinearLayout linearLayout;
    private ListAdapter listAdapter;
    private ArrayList<AndroidUtilities.VcardItem> other;
    private BaseFragment parentFragment;
    private int phoneEndRow;
    private int phoneStartRow;
    private ArrayList<AndroidUtilities.VcardItem> phones;
    private int rowCount;
    private int scrollOffsetY;
    private NestedScrollView scrollView;
    private View shadow;
    private AnimatorSet shadowAnimation;
    private int userRow;
    private int vcardEndRow;
    private int vcardStartRow;

    /* renamed from: org.telegram.ui.Components.PhonebookShareAlert$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        private boolean ignoreLayout;
        private RectF rect;
        final /* synthetic */ PhonebookShareAlert this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass1(PhonebookShareAlert r1, Context r2, Context r3) {
            this.this$0 = r1;
            this.val$context = r3;
            super(r2);
            this.rect = new RectF();
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            int r0 = PhonebookShareAlert.m11679$$Nest$fgetscrollOffsetY(this.this$0) - PhonebookShareAlert.access$1000(this.this$0);
            int r1 = (getMeasuredHeight() + AndroidUtilities.dp(30.0f)) + PhonebookShareAlert.access$1100(this.this$0);
            float r2 = AndroidUtilities.dp(12.0f);
            if ((PhonebookShareAlert.access$1200(this.this$0) + r0) >= r2) goto L5;
            float r3 = 1.0f - Math.min(1.0f, ((r2 - r0) - PhonebookShareAlert.access$1300(this.this$0)) / r2);
        L6:
            int r5 = AndroidUtilities.statusBarHeight;
            PhonebookShareAlert.access$1400(this.this$0).setBounds(0, r0 + r5, getMeasuredWidth(), r1 - r5);
            PhonebookShareAlert.access$1500(this.this$0).draw(r9);
            if (r3 == 1.0f) goto L9;
            PhonebookShareAlert.m11668$$Nest$fgetbackgroundPaint(this.this$0).setColor(PhonebookShareAlert.access$1600(this.this$0, Theme.key_dialogBackground));
            this.rect.set(PhonebookShareAlert.access$1700(this.this$0), PhonebookShareAlert.access$1800(this.this$0) + r0, getMeasuredWidth() - PhonebookShareAlert.access$1900(this.this$0), (PhonebookShareAlert.access$2000(this.this$0) + r0) + AndroidUtilities.dp(24.0f));
            float r22 = r2 * r3;
            r9.drawRoundRect(this.rect, r22, r22, PhonebookShareAlert.m11668$$Nest$fgetbackgroundPaint(this.this$0));
        L9:
            int r02 = PhonebookShareAlert.access$2100(this.this$0, Theme.key_dialogBackground);
            PhonebookShareAlert.m11668$$Nest$fgetbackgroundPaint(this.this$0).setColor(Color.argb((int) (PhonebookShareAlert.m11666$$Nest$fgetactionBar(this.this$0).getAlpha() * 255.0f), (int) (Color.red(r02) * 0.8f), (int) (Color.green(r02) * 0.8f), (int) (Color.blue(r02) * 0.8f)));
            r9.drawRect(PhonebookShareAlert.access$2200(this.this$0), 0.0f, getMeasuredWidth() - PhonebookShareAlert.access$2300(this.this$0), AndroidUtilities.statusBarHeight, PhonebookShareAlert.m11668$$Nest$fgetbackgroundPaint(this.this$0));
            return;
        L5:
            r3 = 1.0f;
            goto L6
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L13;
            if (PhonebookShareAlert.m11679$$Nest$fgetscrollOffsetY(this.this$0) == 0) goto L13;
            if (r3.getY() >= PhonebookShareAlert.m11679$$Nest$fgetscrollOffsetY(this.this$0)) goto L13;
            if (PhonebookShareAlert.m11666$$Nest$fgetactionBar(this.this$0).getAlpha() != 0.0f) goto L13;
            this.this$0.dismiss();
            return true;
        L13:
            return super.onInterceptTouchEvent(r3);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
            PhonebookShareAlert.m11684$$Nest$fputinLayout(this.this$0, true);
            super.onLayout(r3, r4, r5, r6, r7);
            PhonebookShareAlert.m11684$$Nest$fputinLayout(this.this$0, false);
            PhonebookShareAlert.m11686$$Nest$mupdateLayout(this.this$0, false);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            int r92 = View.MeasureSpec.getSize(r9);
            this.ignoreLayout = true;
            setPadding(PhonebookShareAlert.access$700(this.this$0), AndroidUtilities.statusBarHeight, PhonebookShareAlert.access$800(this.this$0), 0);
            this.ignoreLayout = false;
            int r1 = r92 - getPaddingTop();
            View.MeasureSpec.getSize(r8);
            PhonebookShareAlert.access$900(this.this$0);
            ((FrameLayout.LayoutParams) PhonebookShareAlert.m11667$$Nest$fgetactionBarShadow(this.this$0).getLayoutParams()).topMargin = ActionBar.getCurrentActionBarHeight();
            this.ignoreLayout = true;
            int r0 = AndroidUtilities.dp(80.0f);
            int r2 = PhonebookShareAlert.m11672$$Nest$fgetlistAdapter(this.this$0).getItemCount();
            int r3 = 0;
        L3:
            if (r3 >= r2) goto L5;
            View r5 = PhonebookShareAlert.m11672$$Nest$fgetlistAdapter(this.this$0).createView(this.val$context, r3);
            r5.measure(r8, View.MeasureSpec.makeMeasureSpec(0, 0));
            r0 = r0 + r5.getMeasuredHeight();
            r3 = r3 + 1;
            goto L3
        L5:
            if (r0 >= r1) goto L7;
            int r12 = r1 - r0;
        L9:
            if (PhonebookShareAlert.m11680$$Nest$fgetscrollView(this.this$0).getPaddingTop() == r12) goto L11;
            PhonebookShareAlert.m11680$$Nest$fgetscrollView(this.this$0).getPaddingTop();
            PhonebookShareAlert.m11680$$Nest$fgetscrollView(this.this$0).setPadding(0, r12, 0, 0);
        L11:
            this.ignoreLayout = false;
            super.onMeasure(r8, View.MeasureSpec.makeMeasureSpec(r92, TLObject.FLAG_30));
            return;
        L7:
            r12 = r1 / 5;
            goto L9
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.isDismissed() == false) goto L5;
            return false;
        L5:
            if (super.onTouchEvent(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.PhonebookShareAlert$2, reason: invalid class name */
    public class AnonymousClass2 extends NestedScrollView {
        private View focusingView;
        final /* synthetic */ PhonebookShareAlert this$0;

        public AnonymousClass2(PhonebookShareAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.core.widget.NestedScrollView
        public int computeScrollDeltaToGetChildRectOnScreen(Rect r3) {
            if (this.focusingView != null) goto L5;
            return 0;
        L5:
            if (PhonebookShareAlert.m11671$$Nest$fgetlinearLayout(this.this$0).getTop() != getPaddingTop()) goto L14;
            int r0 = super.computeScrollDeltaToGetChildRectOnScreen(r3);
            int r1 = ((this.focusingView.getTop() - getScrollY()) + r3.top) + r0;
            int r2 = ActionBar.getCurrentActionBarHeight() - r1;
            if (r2 > 0) goto L10;
            return r0;
        L10:
            return r0 - (r2 + AndroidUtilities.dp(10.0f));
        L14:
            return 0;
        }

        @Override // androidx.core.widget.NestedScrollView, android.view.ViewGroup, android.view.ViewParent
        public void requestChildFocus(View r1, View r2) {
            this.focusingView = r2;
            super.requestChildFocus(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.PhonebookShareAlert$3, reason: invalid class name */
    public class AnonymousClass3 extends ActionBar {
        final /* synthetic */ PhonebookShareAlert this$0;

        public AnonymousClass3(PhonebookShareAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            PhonebookShareAlert.access$2400(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.PhonebookShareAlert$4, reason: invalid class name */
    public class AnonymousClass4 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PhonebookShareAlert this$0;

        public AnonymousClass4(PhonebookShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.dismiss();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.PhonebookShareAlert$5, reason: invalid class name */
    public class AnonymousClass5 implements DialogInterface.OnClickListener {
        final /* synthetic */ PhonebookShareAlert this$0;

        public AnonymousClass5(PhonebookShareAlert r1) {
            this.this$0 = r1;
        }

        private void fillRowWithType(String r5, ContentValues r6) {
            if (r5.startsWith("X-") == false) goto L7;
            r6.put("data2", 0);
            r6.put("data3", r5.substring(2));
            return;
        L7:
            if ("PREF".equalsIgnoreCase(r5) == false) goto L11;
            r6.put("data2", 12);
            return;
        L11:
            if ("HOME".equalsIgnoreCase(r5) == false) goto L15;
            r6.put("data2", 1);
            return;
        L15:
            if ("MOBILE".equalsIgnoreCase(r5) == false) goto L17;
        L60:
            r6.put("data2", 2);
            return;
        L17:
            if ("CELL".equalsIgnoreCase(r5) == true) goto L60;
            if ("OTHER".equalsIgnoreCase(r5) == false) goto L24;
            r6.put("data2", 7);
            return;
        L24:
            if ("WORK".equalsIgnoreCase(r5) == false) goto L28;
            r6.put("data2", 3);
            return;
        L28:
            if ("RADIO".equalsIgnoreCase(r5) == false) goto L30;
        L58:
            r6.put("data2", 14);
            return;
        L30:
            if ("VOICE".equalsIgnoreCase(r5) == true) goto L58;
            if ("PAGER".equalsIgnoreCase(r5) == false) goto L37;
            r6.put("data2", 6);
            return;
        L37:
            if ("CALLBACK".equalsIgnoreCase(r5) == false) goto L41;
            r6.put("data2", 8);
            return;
        L41:
            if ("CAR".equalsIgnoreCase(r5) == false) goto L45;
            r6.put("data2", 9);
            return;
        L45:
            if ("ASSISTANT".equalsIgnoreCase(r5) == false) goto L49;
            r6.put("data2", 19);
            return;
        L49:
            if ("MMS".equalsIgnoreCase(r5) == false) goto L53;
            r6.put("data2", 20);
            return;
        L53:
            if (r5.startsWith("FAX") == false) goto L56;
            r6.put("data2", 4);
            return;
        L56:
            r6.put("data2", 0);
            r6.put("data3", r5);
        }

        private void fillUrlRowWithType(String r5, ContentValues r6) {
            if (r5.startsWith("X-") == false) goto L7;
            r6.put("data2", 0);
            r6.put("data3", r5.substring(2));
            return;
        L7:
            if ("HOMEPAGE".equalsIgnoreCase(r5) == false) goto L11;
            r6.put("data2", 1);
            return;
        L11:
            if ("BLOG".equalsIgnoreCase(r5) == false) goto L15;
            r6.put("data2", 2);
            return;
        L15:
            if ("PROFILE".equalsIgnoreCase(r5) == false) goto L19;
            r6.put("data2", 3);
            return;
        L19:
            if ("HOME".equalsIgnoreCase(r5) == false) goto L23;
            r6.put("data2", 4);
            return;
        L23:
            if ("WORK".equalsIgnoreCase(r5) == false) goto L27;
            r6.put("data2", 5);
            return;
        L27:
            if ("FTP".equalsIgnoreCase(r5) == false) goto L31;
            r6.put("data2", 6);
            return;
        L31:
            if ("OTHER".equalsIgnoreCase(r5) == false) goto L34;
            r6.put("data2", 7);
            return;
        L34:
            r6.put("data2", 0);
            r6.put("data3", r5);
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface r25, int r26) {
            AnonymousClass5 r0 = this;
            int r2 = 3;
            Integer r3 = 3;
            int r6 = 1;
            if (r26 != 0) goto L5;
            Intent r1 = new Intent("android.intent.action.INSERT");
            r1.setType("vnd.android.cursor.dir/raw_contact");
        L8:
            r1.putExtra("name", ContactsController.formatName(PhonebookShareAlert.m11669$$Nest$fgetcurrentUser(r0.this$0).first_name, PhonebookShareAlert.m11669$$Nest$fgetcurrentUser(r0.this$0).last_name));
            ArrayList<? extends Parcelable> r8 = new ArrayList();
            boolean r9 = false;
            int r10 = 0;
        L9:
            String r12 = "data1";
            String r13 = "mimetype";
            if (r10 >= PhonebookShareAlert.m11677$$Nest$fgetphones(r0.this$0).size()) goto L12;
            AndroidUtilities.VcardItem r11 = (AndroidUtilities.VcardItem) PhonebookShareAlert.m11677$$Nest$fgetphones(r0.this$0).get(r10);
            ContentValues r14 = new ContentValues();
            r14.put("mimetype", "vnd.android.cursor.item/phone_v2");
            r14.put("data1", r11.getValue(false));
            r0.fillRowWithType(r11.getRawType(false), r14);
            r8.add(r14);
            r10 = r10 + 1;
            goto L9
        L12:
            int r102 = 0;
            boolean r112 = false;
        L14:
            if (r102 >= PhonebookShareAlert.m11673$$Nest$fgetother(r0.this$0).size()) goto L139;
            AndroidUtilities.VcardItem r142 = (AndroidUtilities.VcardItem) PhonebookShareAlert.m11673$$Nest$fgetother(r0.this$0).get(r102);
            int r15 = r142.type;
            if (r15 != r6) goto L19;
            ContentValues r152 = new ContentValues();
            r152.put(r13, "vnd.android.cursor.item/email_v2");
            r152.put(r12, r142.getValue(r9));
            r0.fillRowWithType(r142.getRawType(r9), r152);
            r8.add(r152);
        L18:
            Intent r20 = r1;
            Integer r17 = r3;
            boolean r153 = r9;
            int r18 = r102;
            boolean r19 = r112;
            String r113 = r12;
            String r23 = r13;
            AnonymousClass5 r92 = r0;
        L138:
            r102 = r18 + 1;
            r0 = r92;
            r12 = r113;
            r9 = r153;
            r3 = r17;
            r112 = r19;
            r1 = r20;
            r13 = r23;
            r2 = 3;
            r6 = 1;
            goto L14
        L19:
            if (r15 != r2) goto L22;
            ContentValues r62 = new ContentValues();
            r62.put(r13, "vnd.android.cursor.item/website");
            r62.put(r12, r142.getValue(r9));
            r0.fillUrlRowWithType(r142.getRawType(r9), r62);
            r8.add(r62);
            goto L18
        L22:
            if (r15 != 4) goto L25;
            ContentValues r63 = new ContentValues();
            r63.put(r13, "vnd.android.cursor.item/note");
            r63.put(r12, r142.getValue(r9));
            r8.add(r63);
            goto L18
        L25:
            if (r15 != 5) goto L27;
            ContentValues r64 = new ContentValues();
            r64.put(r13, "vnd.android.cursor.item/contact_event");
            r64.put(r12, r142.getValue(r9));
            r64.put("data2", r3);
            r8.add(r64);
            goto L18
        L27:
            boolean r16 = r9;
            r18 = r102;
            r19 = r112;
            r20 = r1;
            if (r15 != 2) goto L60;
            ContentValues r02 = new ContentValues();
            r02.put(r13, "vnd.android.cursor.item/postal-address_v2");
            String[] r154 = r142.getRawValue();
            String r22 = r12;
            if (r154.length <= 0) goto L33;
            r02.put("data5", r154[r16 ? 1 : 0]);
        L33:
            if (r154.length <= 1) goto L36;
            r02.put("data6", r154[1]);
        L36:
            if (r154.length <= 2) goto L39;
            r02.put("data4", r154[2]);
        L39:
            if (r154.length <= 3) goto L42;
            r02.put("data7", r154[3]);
        L42:
            if (r154.length <= 4) goto L45;
            r02.put("data8", r154[4]);
        L45:
            if (r154.length <= 5) goto L48;
            r02.put("data9", r154[5]);
        L48:
            if (r154.length <= 6) goto L50;
            r02.put("data10", r154[6]);
        L50:
            String r65 = r142.getRawType(r16);
            if ("HOME".equalsIgnoreCase(r65) == false) goto L54;
            r02.put("data2", 1);
        L59:
            r8.add(r02);
            r153 = false;
            r92 = this;
            r17 = r3;
            r23 = r13;
            r113 = r22;
            goto L138
        L54:
            if ("WORK".equalsIgnoreCase(r65) == false) goto L57;
            r02.put("data2", 2);
            goto L59
        L57:
            if ("OTHER".equalsIgnoreCase(r65) == false) goto L59;
            r02.put("data2", r3);
            goto L59
        L60:
            String r222 = r12;
            if (r15 != 20) goto L101;
            ContentValues r03 = new ContentValues();
            r03.put(r13, "vnd.android.cursor.item/im");
            String r155 = r142.getRawType(true);
            String r122 = r142.getRawType(false);
            r23 = r13;
            r113 = r222;
            r03.put(r113, r142.getValue(false));
            if ("AIM".equalsIgnoreCase(r155) == false) goto L66;
            r03.put("data5", 0);
        L91:
            if ("HOME".equalsIgnoreCase(r122) == false) goto L94;
            r03.put("data2", 1);
        L99:
            r8.add(r03);
        L100:
            r153 = false;
            r92 = this;
            r17 = r3;
            goto L138
        L94:
            if ("WORK".equalsIgnoreCase(r122) == false) goto L97;
            r03.put("data2", 2);
            goto L99
        L97:
            if ("OTHER".equalsIgnoreCase(r122) == false) goto L99;
            r03.put("data2", r3);
            goto L99
        L66:
            if ("MSN".equalsIgnoreCase(r155) == false) goto L69;
            r03.put("data5", 1);
            goto L91
        L69:
            if ("YAHOO".equalsIgnoreCase(r155) == false) goto L72;
            r03.put("data5", 2);
            goto L91
        L72:
            if ("SKYPE".equalsIgnoreCase(r155) == false) goto L75;
            r03.put("data5", r3);
            goto L91
        L75:
            if ("QQ".equalsIgnoreCase(r155) == false) goto L78;
            r03.put("data5", 4);
            goto L91
        L78:
            if ("GOOGLE-TALK".equalsIgnoreCase(r155) == false) goto L81;
            r03.put("data5", 5);
            goto L91
        L81:
            if ("ICQ".equalsIgnoreCase(r155) == false) goto L84;
            r03.put("data5", 6);
            goto L91
        L84:
            if ("JABBER".equalsIgnoreCase(r155) == false) goto L87;
            r03.put("data5", 7);
            goto L91
        L87:
            if ("NETMEETING".equalsIgnoreCase(r155) == false) goto L89;
            r03.put("data5", 8);
            goto L91
        L89:
            r03.put("data5", -1);
            r03.put("data6", r142.getRawType(true));
            goto L91
        L101:
            r23 = r13;
            r113 = r222;
            if (r15 != 6) goto L100;
            if (r19 == true) goto L100;
            ContentValues r66 = new ContentValues();
            String r123 = r23;
            r66.put(r123, "vnd.android.cursor.item/organization");
            r92 = this;
            int r132 = r18;
        L107:
            if (r132 >= PhonebookShareAlert.m11673$$Nest$fgetother(r92.this$0).size()) goto L137;
            AndroidUtilities.VcardItem r143 = (AndroidUtilities.VcardItem) PhonebookShareAlert.m11673$$Nest$fgetother(r92.this$0).get(r132);
            Integer r172 = r3;
            if (r143.type == 6) goto L111;
        L110:
            String r232 = r123;
        L136:
            r132 = r132 + 1;
            r3 = r172;
            r123 = r232;
            goto L107
        L111:
            String r32 = r143.getRawType(true);
            if ("ORG".equalsIgnoreCase(r32) == false) goto L123;
            String[] r33 = r143.getRawValue();
            if (r33.length == 0) goto L110;
            r232 = r123;
            if (r33.length < 1) goto L120;
            r66.put(r113, r33[0]);
        L120:
            if (r33.length < 2) goto L122;
            r66.put("data5", r33[1]);
        L122:
            boolean r124 = true;
        L130:
            String r34 = r143.getRawType(r124);
            if ("WORK".equalsIgnoreCase(r34) == false) goto L134;
            r66.put("data2", 1);
            goto L136
        L134:
            if ("OTHER".equalsIgnoreCase(r34) == false) goto L136;
            r66.put("data2", 2);
            goto L136
        L123:
            r232 = r123;
            if ("TITLE".equalsIgnoreCase(r32) == false) goto L128;
            r66.put("data4", r143.getValue(false));
        L126:
            r124 = true;
            goto L130
        L128:
            if ("ROLE".equalsIgnoreCase(r32) == false) goto L126;
            r66.put("data4", r143.getValue(false));
            goto L126
        L137:
            r17 = r3;
            r23 = r123;
            r153 = false;
            r8.add(r66);
            r19 = true;
            goto L138
        L139:
            AnonymousClass5 r93 = r0;
            r1.putExtra("finishActivityOnSaveCompleted", true);
            r1.putParcelableArrayListExtra("data", r8);
            PhonebookShareAlert.m11674$$Nest$fgetparentFragment(r93.this$0).getParentActivity().startActivity(r1);     // Catch: Exception -> L142
            r93.this$0.dismiss();     // Catch: Exception -> L142
            return;
        L142:
            e = move-exception;
            FileLog.e(e);
            return;
        L5:
            if (r26 != 1) goto L7;
            r1 = new Intent("android.intent.action.INSERT_OR_EDIT");
            r1.setType("vnd.android.cursor.item/contact");
            goto L8
        L7:
            r1 = null;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.Components.PhonebookShareAlert$6, reason: invalid class name */
    public class AnonymousClass6 implements Bulletin.Delegate {
        final /* synthetic */ PhonebookShareAlert this$0;

        public AnonymousClass6(PhonebookShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return AndroidUtilities.dp(74.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.PhonebookShareAlert$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ PhonebookShareAlert this$0;

        public AnonymousClass7(PhonebookShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            PhonebookShareAlert.m11683$$Nest$fputactionBarAnimation(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.PhonebookShareAlert$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ PhonebookShareAlert this$0;

        public AnonymousClass8(PhonebookShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            PhonebookShareAlert.m11685$$Nest$fputshadowAnimation(this.this$0, null);
        }
    }

    public class ListAdapter {
        final /* synthetic */ PhonebookShareAlert this$0;

        private ListAdapter(PhonebookShareAlert r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ListAdapter(PhonebookShareAlert r1, PhonebookShareAlertIA r2) {
            this(r1);
        }

        public View createView(Context r4, int r5) {
            int r0 = getItemViewType(r5);
            if (r0 == 0) goto L5;
            View r1 = new TextCheckBoxCell(this.this$0, r4);
        L6:
            onBindViewHolder(r1, r5, r0);
            return r1;
        L5:
            r1 = new UserCell(this.this$0, r4);
            goto L6
        }

        public int getItemCount() {
            return PhonebookShareAlert.m11678$$Nest$fgetrowCount(this.this$0);
        }

        public int getItemViewType(int r1) {
            if (r1 != PhonebookShareAlert.m11681$$Nest$fgetuserRow(this.this$0)) goto L6;
            return 0;
        L6:
            return 1;
        }

        public void onBindViewHolder(View r4, int r5, int r6) {
            boolean r0 = true;
            if (r6 != 1) goto L40;
            TextCheckBoxCell r42 = (TextCheckBoxCell) r4;
            if (r5 >= PhonebookShareAlert.m11676$$Nest$fgetphoneStartRow(this.this$0)) goto L7;
        L9:
            AndroidUtilities.VcardItem r62 = (AndroidUtilities.VcardItem) PhonebookShareAlert.m11673$$Nest$fgetother(this.this$0).get(r5 - PhonebookShareAlert.m11682$$Nest$fgetvcardStartRow(this.this$0));
            int r1 = r62.type;
            if (r1 != 1) goto L13;
            int r12 = R.drawable.msg_mention;
        L35:
            if (r5 != (getItemCount() - 1)) goto L38;
            r0 = false;
        L38:
            r42.setVCardItem(r62, r12, r0);
            return;
        L13:
            if (r1 != 2) goto L16;
            r12 = R.drawable.msg_location;
            goto L35
        L16:
            if (r1 != 3) goto L19;
            r12 = R.drawable.msg_link;
            goto L35
        L19:
            if (r1 != 4) goto L22;
            r12 = R.drawable.msg_info;
            goto L35
        L22:
            if (r1 != 5) goto L25;
            r12 = R.drawable.msg_calendar2;
            goto L35
        L25:
            if (r1 != 6) goto L31;
            if ("ORG".equalsIgnoreCase(r62.getRawType(true)) == false) goto L29;
            r12 = R.drawable.msg_work;
            goto L35
        L29:
            r12 = R.drawable.msg_jobtitle;
            goto L35
        L31:
            if (r1 != 20) goto L33;
            r12 = R.drawable.msg_info;
            goto L35
        L33:
            r12 = R.drawable.msg_info;
            goto L35
        L7:
            if (r5 >= PhonebookShareAlert.m11675$$Nest$fgetphoneEndRow(this.this$0)) goto L9;
            r62 = (AndroidUtilities.VcardItem) PhonebookShareAlert.m11677$$Nest$fgetphones(this.this$0).get(r5 - PhonebookShareAlert.m11676$$Nest$fgetphoneStartRow(this.this$0));
            r12 = R.drawable.msg_calls;
            goto L35
        }
    }

    public class TextCheckBoxCell extends FrameLayout {
        private Switch checkBox;
        private ImageView imageView;
        private boolean needDivider;
        private TextView textView;
        final /* synthetic */ PhonebookShareAlert this$0;
        private TextView valueTextView;

        public TextCheckBoxCell(PhonebookShareAlert r20, Context r21) {
            this.this$0 = r20;
            super(r21);
            TextView r3 = new TextView(r21);
            this.textView = r3;
            r3.setTextColor(PhonebookShareAlert.access$400(r20, Theme.key_windowBackgroundWhiteBlackText));
            this.textView.setTextSize(1, 16.0f);
            this.textView.setSingleLine(false);
            TextView r32 = this.textView;
            int r7 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r6 = 5;
        L6:
            r32.setGravity(r6 | 48);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            TextView r33 = this.textView;
            boolean r62 = LocaleController.isRTL;
            if (r62 == false) goto L9;
            int r9 = 5;
        L10:
            int r12 = r9 | 48;
            float r92 = 72.0f;
            if (r62 == true) goto L13;
            float r13 = 72.0f;
        L19:
            if (LocaleController.isRTL == false) goto L22;
            float r15 = 72.0f;
        L26:
            addView(r33, LayoutHelper.createFrame(-1, -1.0f, r12, r13, 10.0f, r15, 0.0f));
            TextView r34 = new TextView(r21);
            this.valueTextView = r34;
            r34.setTextColor(PhonebookShareAlert.access$500(r20, Theme.key_windowBackgroundWhiteGrayText2));
            this.valueTextView.setTextSize(1, 13.0f);
            this.valueTextView.setLines(1);
            this.valueTextView.setMaxLines(1);
            this.valueTextView.setSingleLine(true);
            TextView r35 = this.valueTextView;
            if (LocaleController.isRTL == false) goto L29;
            int r5 = 5;
        L30:
            r35.setGravity(r5);
            TextView r36 = this.valueTextView;
            boolean r52 = LocaleController.isRTL;
            if (r52 == false) goto L33;
            int r122 = 5;
        L34:
            if (r52 == true) goto L36;
            float r132 = 72.0f;
        L42:
            if (LocaleController.isRTL == false) goto L45;
        L49:
            addView(r36, LayoutHelper.createFrame(-2, -2.0f, r122, r132, 35.0f, r92, 0.0f));
            ImageView r37 = new ImageView(r21);
            this.imageView = r37;
            r37.setScaleType(ImageView.ScaleType.CENTER);
            this.imageView.setColorFilter(new PorterDuffColorFilter(PhonebookShareAlert.access$600(r20, Theme.key_windowBackgroundWhiteGrayIcon), PorterDuff.Mode.MULTIPLY));
            ImageView r38 = this.imageView;
            boolean r53 = LocaleController.isRTL;
            if (r53 == false) goto L52;
            int r63 = 5;
        L53:
            int r11 = r63 | 48;
            if (r53 == false) goto L56;
            float r123 = 0.0f;
        L57:
            if (r53 == false) goto L59;
            float r14 = 20.0f;
        L60:
            addView(r38, LayoutHelper.createFrame(-2, -2.0f, r11, r123, 20.0f, r14, 0.0f));
            if (PhonebookShareAlert.m11670$$Nest$fgetisImport(r20) == true) goto L67;
            Switch r1 = new Switch(r21);
            this.checkBox = r1;
            int r2 = Theme.key_switchTrack;
            int r39 = Theme.key_switchTrackChecked;
            int r54 = Theme.key_windowBackgroundWhite;
            r1.setColors(r2, r39, r54, r54);
            Switch r16 = this.checkBox;
            if (LocaleController.isRTL == true) goto L66;
            r7 = 5;
        L66:
            addView(r16, LayoutHelper.createFrame(37, 40.0f, r7 | 16, 22.0f, 0.0f, 22.0f, 0.0f));
        L67:
            setClipChildren(false);
            return;
        L59:
            r14 = 0.0f;
            goto L60
        L56:
            r123 = 20.0f;
            goto L57
        L52:
            r63 = 3;
            goto L53
        L45:
            if (PhonebookShareAlert.m11670$$Nest$fgetisImport(r20) == false) goto L47;
            int r55 = 17;
        L48:
            r92 = r55;
            goto L49
        L47:
            r55 = 64;
            goto L48
        L36:
            if (PhonebookShareAlert.m11670$$Nest$fgetisImport(r20) == false) goto L38;
            int r56 = 17;
        L39:
            r132 = r56;
            goto L42
        L38:
            r56 = 64;
            goto L39
        L33:
            r122 = 3;
            goto L34
        L29:
            r5 = 3;
            goto L30
        L22:
            if (PhonebookShareAlert.m11670$$Nest$fgetisImport(r20) == false) goto L24;
            int r64 = 17;
        L25:
            r15 = r64;
            goto L26
        L24:
            r64 = 64;
            goto L25
        L13:
            if (PhonebookShareAlert.m11670$$Nest$fgetisImport(r20) == false) goto L15;
            int r65 = 17;
        L16:
            r13 = r65;
            goto L19
        L15:
            r65 = 64;
            goto L16
        L9:
            r9 = 3;
            goto L10
        L5:
            r6 = 3;
            goto L6
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            Switch r0 = this.checkBox;
            if (r0 == null) goto L6;
            r0.invalidate();
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            if (this.needDivider == true) goto L5;
            return;
        L5:
            if (LocaleController.isRTL == false) goto L8;
            float r0 = 0.0f;
        L7:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L12;
            int r1 = AndroidUtilities.dp(70.0f);
        L13:
            r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L12:
            r1 = 0;
            goto L13
        L8:
            r0 = AndroidUtilities.dp(70.0f);
            goto L7
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r12 = this.textView.getMeasuredHeight() + AndroidUtilities.dp(13.0f);
            TextView r22 = this.valueTextView;
            r22.layout(r22.getLeft(), r12, this.valueTextView.getRight(), this.valueTextView.getMeasuredHeight() + r12);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r13, int r14) {
            measureChildWithMargins(this.textView, r13, 0, r14, 0);
            measureChildWithMargins(this.valueTextView, r13, 0, r14, 0);
            measureChildWithMargins(this.imageView, r13, 0, r14, 0);
            Switch r7 = this.checkBox;
            if (r7 == null) goto L5;
            measureChildWithMargins(r7, r13, 0, r14, 0);
        L5:
            setMeasuredDimension(View.MeasureSpec.getSize(r13), Math.max(AndroidUtilities.dp(64.0f), (this.textView.getMeasuredHeight() + this.valueTextView.getMeasuredHeight()) + AndroidUtilities.dp(20.0f)) + (this.needDivider ? 1 : 0));
        }

        public void setChecked(boolean r2) {
            Switch r1 = this.checkBox;
            if (r1 == null) goto L6;
            r1.setChecked(r2, true);
            return;
        }

        public void setVCardItem(AndroidUtilities.VcardItem r4, int r5, boolean r6) {
            this.textView.setText(r4.getValue(true));
            this.valueTextView.setText(r4.getType());
            Switch r0 = this.checkBox;
            if (r0 == null) goto L5;
            r0.setChecked(r4.checked, false);
        L5:
            ImageView r42 = this.imageView;
            if (r5 == 0) goto L8;
            r42.setImageResource(r5);
        L9:
            this.needDivider = r6;
            setWillNotDraw(!r6);
            return;
        L8:
            r42.setImageDrawable(null);
            goto L9
        }
    }

    public class UserCell extends LinearLayout {
        final /* synthetic */ PhonebookShareAlert this$0;

        public UserCell(PhonebookShareAlert r18, Context r19) {
            this.this$0 = r18;
            super(r19);
            setOrientation(1);
            if (PhonebookShareAlert.m11677$$Nest$fgetphones(r18).size() != 1) goto L8;
            if (PhonebookShareAlert.m11673$$Nest$fgetother(r18).size() != 0) goto L8;
            String r4 = ((AndroidUtilities.VcardItem) PhonebookShareAlert.m11677$$Nest$fgetphones(r18).get(0)).getValue(true);
            boolean r6 = false;
        L14:
            AvatarDrawable r7 = new AvatarDrawable();
            r7.setTextSize(AndroidUtilities.dp(30.0f));
            r7.setInfo(PhonebookShareAlert.access$100(r18), PhonebookShareAlert.m11669$$Nest$fgetcurrentUser(r18));
            BackupImageView r8 = new BackupImageView(r19);
            r8.setRoundRadius(ExteraConfig.getAvatarCorners(80.0f));
            r8.setForUserOrChat(PhonebookShareAlert.m11669$$Nest$fgetcurrentUser(r18), r7);
            addView(r8, LayoutHelper.createLinear(80, 80, 49, 0, 32, 0, 0));
            TextView r72 = new TextView(r19);
            r72.setTypeface(AndroidUtilities.bold());
            r72.setTextSize(1, 17.0f);
            r72.setTextColor(PhonebookShareAlert.access$200(r18, Theme.key_dialogTextBlack));
            r72.setSingleLine(true);
            TextUtils.TruncateAt r82 = TextUtils.TruncateAt.END;
            r72.setEllipsize(r82);
            r72.setText(ContactsController.formatName(PhonebookShareAlert.m11669$$Nest$fgetcurrentUser(r18).first_name, PhonebookShareAlert.m11669$$Nest$fgetcurrentUser(r18).last_name));
            int r9 = 27;
            if (r4 == null) goto L17;
            int r16 = 0;
        L18:
            addView(r72, LayoutHelper.createLinear(-2, -2, 49, 10, 10, 10, r16));
            if (r4 == null) goto L26;
            TextView r5 = new TextView(r19);
            r5.setTextSize(1, 14.0f);
            r5.setTextColor(PhonebookShareAlert.access$300(r18, Theme.key_dialogTextGray3));
            r5.setSingleLine(true);
            r5.setEllipsize(r82);
            r5.setText(r4);
            if (r6 == true) goto L24;
            r9 = 11;
        L24:
            addView(r5, LayoutHelper.createLinear(-2, -2, 49, 10, 3, 10, r9));
            return;
        L26:
            return;
        L17:
            r16 = 27;
        L8:
            if (PhonebookShareAlert.m11669$$Nest$fgetcurrentUser(r18).status != null) goto L10;
        L13:
            r4 = null;
        L12:
            r6 = true;
            goto L14
        L10:
            if (PhonebookShareAlert.m11669$$Nest$fgetcurrentUser(r18).status.expires == 0) goto L13;
            r4 = LocaleController.formatUserStatus(PhonebookShareAlert.access$000(r18), PhonebookShareAlert.m11669$$Nest$fgetcurrentUser(r18));
            goto L12
        }
    }

    public static /* synthetic */ boolean $r8$lambda$9NEFjdyTaQQ7134RWOjBJpP4wJk(PhonebookShareAlert r0, int r1, Theme.ResourcesProvider r2, Context r3, View r4) {
        return r0.lambda$new$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$AhlmWHgmJNrcLI6mvQsSZEEZCYs(PhonebookShareAlert r0, boolean r1, int r2, int r3) {
        r0.lambda$new$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$ZcNVY04eFfgYDFQkE0VycrUhaJE(PhonebookShareAlert r0, Long r1) {
        r0.lambda$new$5(r1);
    }

    /* renamed from: $r8$lambda$e1CfeUlkwJDLEgKC-CFoRYXWSfY, reason: not valid java name */
    public static /* synthetic */ void m11665$r8$lambda$e1CfeUlkwJDLEgKCCFoRYXWSfY(PhonebookShareAlert r0, Theme.ResourcesProvider r1, View r2) {
        r0.lambda$new$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$rzZ7K6C1Sv1ooN6Dm8ufpi_XtmM(PhonebookShareAlert r0, AndroidUtilities.VcardItem r1, DialogInterface r2, int r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$veCq0whWVUdfUQdvpoz4XXFS7so(PhonebookShareAlert r0, int r1, View r2, View r3) {
        r0.lambda$new$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$yX8OUURA0VPvwDpqAxqCehPl_mI(PhonebookShareAlert r0, NestedScrollView r1, int r2, int r3, int r4, int r5) {
        r0.lambda$new$0(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$fgetactionBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBar m11666$$Nest$fgetactionBar(PhonebookShareAlert r0) {
        return r0.actionBar;
    }

    /* renamed from: -$$Nest$fgetactionBarShadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m11667$$Nest$fgetactionBarShadow(PhonebookShareAlert r0) {
        return r0.actionBarShadow;
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11668$$Nest$fgetbackgroundPaint(PhonebookShareAlert r0) {
        return r0.backgroundPaint;
    }

    /* renamed from: -$$Nest$fgetcurrentUser, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.User m11669$$Nest$fgetcurrentUser(PhonebookShareAlert r0) {
        return r0.currentUser;
    }

    /* renamed from: -$$Nest$fgetisImport, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11670$$Nest$fgetisImport(PhonebookShareAlert r0) {
        return r0.isImport;
    }

    /* renamed from: -$$Nest$fgetlinearLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m11671$$Nest$fgetlinearLayout(PhonebookShareAlert r0) {
        return r0.linearLayout;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m11672$$Nest$fgetlistAdapter(PhonebookShareAlert r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetother, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11673$$Nest$fgetother(PhonebookShareAlert r0) {
        return r0.other;
    }

    /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m11674$$Nest$fgetparentFragment(PhonebookShareAlert r0) {
        return r0.parentFragment;
    }

    /* renamed from: -$$Nest$fgetphoneEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11675$$Nest$fgetphoneEndRow(PhonebookShareAlert r0) {
        return r0.phoneEndRow;
    }

    /* renamed from: -$$Nest$fgetphoneStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11676$$Nest$fgetphoneStartRow(PhonebookShareAlert r0) {
        return r0.phoneStartRow;
    }

    /* renamed from: -$$Nest$fgetphones, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11677$$Nest$fgetphones(PhonebookShareAlert r0) {
        return r0.phones;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11678$$Nest$fgetrowCount(PhonebookShareAlert r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11679$$Nest$fgetscrollOffsetY(PhonebookShareAlert r0) {
        return r0.scrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetscrollView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ NestedScrollView m11680$$Nest$fgetscrollView(PhonebookShareAlert r0) {
        return r0.scrollView;
    }

    /* renamed from: -$$Nest$fgetuserRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11681$$Nest$fgetuserRow(PhonebookShareAlert r0) {
        return r0.userRow;
    }

    /* renamed from: -$$Nest$fgetvcardStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11682$$Nest$fgetvcardStartRow(PhonebookShareAlert r0) {
        return r0.vcardStartRow;
    }

    /* renamed from: -$$Nest$fputactionBarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11683$$Nest$fputactionBarAnimation(PhonebookShareAlert r0, AnimatorSet r1) {
        r0.actionBarAnimation = r1;
    }

    /* renamed from: -$$Nest$fputinLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11684$$Nest$fputinLayout(PhonebookShareAlert r0, boolean r1) {
        r0.inLayout = r1;
    }

    /* renamed from: -$$Nest$fputshadowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11685$$Nest$fputshadowAnimation(PhonebookShareAlert r0, AnimatorSet r1) {
        r0.shadowAnimation = r1;
    }

    /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11686$$Nest$mupdateLayout(PhonebookShareAlert r0, boolean r1) {
        r0.updateLayout(r1);
    }

    public PhonebookShareAlert(BaseFragment r10, ContactsController.Contact r11, TLRPC.User r12, Uri r13, File r14, String r15, String r16) {
        this(r10, r11, r12, r13, r14, null, r15, r16);
    }

    public PhonebookShareAlert(BaseFragment r11, ContactsController.Contact r12, TLRPC.User r13, Uri r14, File r15, String r16, String r17, String r18) {
        this(r11, r12, r13, r14, r15, r16, r17, r18, null);
    }

    public PhonebookShareAlert(BaseFragment r14, ContactsController.Contact r15, TLRPC.User r16, Uri r17, File r18, String r19, String r20, String r21, Theme.ResourcesProvider r22) {
        super(r14.getParentActivity(), false, r22);
        this.backgroundPaint = new Paint(1);
        this.other = new ArrayList();
        this.phones = new ArrayList();
        String r4 = ContactsController.formatName(r20, r21);
        ArrayList r7 = new ArrayList();
        if (r17 == null) goto L5;
        ArrayList<TLRPC.User> r1 = AndroidUtilities.loadVCardFromStream(r17, this.currentAccount, false, r7, r4);
    L15:
        if (r16 != null) goto L18;
        if (r15 == null) goto L18;
        TLRPC.User r0 = r15.user;
    L19:
        if (r1 == null) goto L41;
        int r2 = 0;
    L22:
        if (r2 >= r7.size()) goto L36;
        AndroidUtilities.VcardItem r42 = (AndroidUtilities.VcardItem) r7.get(r2);
        if (r42.type != 0) goto L33;
        int r9 = 0;
    L26:
        int r10 = this.phones.size();
        ArrayList<AndroidUtilities.VcardItem> r11 = this.phones;
        if (r9 >= r10) goto L32;
        if (r11.get(r9).getValue(false).equals(r42.getValue(false)) == true) goto L30;
        r9 = r9 + 1;
        goto L26
    L30:
        r42.checked = false;
    L34:
        r2 = r2 + 1;
        goto L22
    L32:
        r11.add(r42);
        goto L34
    L33:
        this.other.add(r42);
        goto L34
    L36:
        if (r1.isEmpty() == true) goto L41;
        TLRPC.User r12 = r1.get(0);
        ArrayList<TLRPC.RestrictionReason> r23 = r12.restriction_reason;
        if (TextUtils.isEmpty(r20) == false) goto L40;
        String r43 = r12.first_name;
        String r13 = r12.last_name;
    L42:
        TLRPC.TL_userContact_old2 r72 = new TLRPC.TL_userContact_old2();
        this.currentUser = r72;
        if (r0 == null) goto L47;
        r72.id = r0.id;
        r72.access_hash = r0.access_hash;
        r72.photo = r0.photo;
        r72.status = r0.status;
        r72.first_name = r0.first_name;
        r72.last_name = r0.last_name;
        r72.phone = r0.phone;
        if (r23 == null) goto L48;
        r72.restriction_reason = r23;
    L48:
        this.parentFragment = r14;
        Activity r142 = r14.getParentActivity();
        updateRows();
        AnonymousClass1 r02 = new AnonymousClass1(this, r142, r142);
        r02.setWillNotDraw(false);
        this.containerView = r02;
        setApplyTopPadding(false);
        setApplyBottomPadding(false);
        this.listAdapter = new ListAdapter(this, null);
        AnonymousClass2 r110 = new AnonymousClass2(this, r142);
        this.scrollView = r110;
        r110.setClipToPadding(false);
        this.scrollView.setVerticalScrollBarEnabled(false);
        r02.addView(this.scrollView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 77.0f));
        LinearLayout r111 = new LinearLayout(r142);
        this.linearLayout = r111;
        r111.setOrientation(1);
        this.scrollView.addView(this.linearLayout, LayoutHelper.createScroll(-1, -1, 51));
        this.scrollView.setOnScrollChangeListener(new PhonebookShareAlert$$ExternalSyntheticLambda0(this));
        int r112 = this.listAdapter.getItemCount();
        int r24 = 0;
    L49:
        if (r24 >= r112) goto L60;
        View r44 = this.listAdapter.createView(r142, r24);
        this.linearLayout.addView(r44, LayoutHelper.createLinear(-1, -2));
        if (r24 < this.phoneStartRow) goto L55;
        if (r24 >= this.phoneEndRow) goto L55;
    L58:
        r44.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r44.setOnClickListener(new PhonebookShareAlert$$ExternalSyntheticLambda1(this, r24, r44));
        r44.setOnLongClickListener(new PhonebookShareAlert$$ExternalSyntheticLambda2(this, r24, r22, r142));
    L59:
        r24 = r24 + 1;
    L55:
        if (r24 < this.vcardStartRow) goto L59;
        if (r24 >= this.vcardEndRow) goto L59;
    L60:
        AnonymousClass3 r113 = new AnonymousClass3(this, r142);
        this.actionBar = r113;
        r113.setBackgroundColor(getThemedColor(Theme.key_dialogBackground));
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        ActionBar r114 = this.actionBar;
        int r25 = Theme.key_dialogTextBlack;
        r114.setItemsColor(getThemedColor(r25), false);
        this.actionBar.setItemsBackgroundColor(getThemedColor(Theme.key_dialogButtonSelector), false);
        this.actionBar.setTitleColor(getThemedColor(r25));
        this.actionBar.setOccupyStatusBar(false);
        this.actionBar.setAlpha(0.0f);
        boolean r115 = this.isImport;
        ActionBar r45 = this.actionBar;
        if (r115 == false) goto L63;
        r45.setTitle(LocaleController.getString(R.string.AddContactPhonebookTitle));
    L64:
        this.containerView.addView(this.actionBar, LayoutHelper.createFrame(-1, -2.0f));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass4(this));
        View r116 = new View(r142);
        this.actionBarShadow = r116;
        r116.setAlpha(0.0f);
        View r117 = this.actionBarShadow;
        int r46 = Theme.key_dialogShadowLine;
        r117.setBackgroundColor(getThemedColor(r46));
        this.containerView.addView(this.actionBarShadow, LayoutHelper.createFrame(-1, 1.0f));
        View r118 = new View(r142);
        this.shadow = r118;
        r118.setBackgroundColor(getThemedColor(r46));
        this.shadow.setAlpha(0.0f);
        this.containerView.addView(this.shadow, LayoutHelper.createFrame(-1, 1.0f, 83, 0.0f, 0.0f, 0.0f, 77.0f));
        TextView r119 = new TextView(r142);
        this.buttonTextView = r119;
        r119.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        this.buttonTextView.setGravity(17);
        this.buttonTextView.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        this.buttonTextView.setTextSize(1, 14.0f);
        boolean r143 = this.isImport;
        TextView r120 = this.buttonTextView;
        if (r143 == false) goto L67;
        r120.setText(LocaleController.getString(R.string.AddContactPhonebookTitle));
    L68:
        this.buttonTextView.setTypeface(AndroidUtilities.bold());
        this.buttonTextView.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), getThemedColor(Theme.key_featuredStickers_addButton), getThemedColor(Theme.key_featuredStickers_addButtonPressed)));
        r02.addView(this.buttonTextView, LayoutHelper.createFrame(-1, 48.0f, 83, 14.0f, 14.0f, 14.0f, 14.0f));
        this.buttonTextView.setOnClickListener(new PhonebookShareAlert$$ExternalSyntheticLambda3(this, r22));
        return;
    L67:
        r120.setText(LocaleController.getString(R.string.ShareContactTitle));
        goto L68
    L63:
        r45.setTitle(LocaleController.getString(R.string.ShareContactTitle));
        goto L64
    L47:
        r72.first_name = r43;
        r72.last_name = r13;
        goto L48
    L40:
        r43 = r20;
        r13 = r21;
    L41:
        r43 = r20;
        r13 = r21;
        r23 = null;
    L18:
        r0 = r16;
        goto L19
    L5:
        if (r18 == null) goto L8;
        r1 = AndroidUtilities.loadVCardFromStream(Uri.fromFile(r18), this.currentAccount, false, r7, r4);
        r18.delete();
        this.isImport = true;
        goto L15
    L8:
        if (r19 == null) goto L10;
        AndroidUtilities.VcardItem r47 = new AndroidUtilities.VcardItem();
        r47.type = 0;
        ArrayList<String> r92 = r47.vcardData;
        String r121 = "TEL;MOBILE:+".concat(r19);
        r47.fullData = r121;
        r92.add(r121);
        this.phones.add(r47);
        this.isImport = true;
    L14:
        r1 = null;
        goto L15
    L10:
        String r26 = r15.key;
        if (r26 == null) goto L13;
        r1 = AndroidUtilities.loadVCardFromStream(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_VCARD_URI, r26), this.currentAccount, true, r7, r4);
        goto L15
    L13:
        AndroidUtilities.VcardItem r27 = new AndroidUtilities.VcardItem();
        r27.type = 0;
        ArrayList<String> r48 = r27.vcardData;
        String r122 = "TEL;MOBILE:+" + r15.user.phone;
        r27.fullData = r122;
        r48.add(r122);
        this.phones.add(r27);
        goto L14
    }

    public PhonebookShareAlert(BaseFragment r11, ContactsController.Contact r12, TLRPC.User r13, Uri r14, File r15, String r16, String r17, Theme.ResourcesProvider r18) {
        this(r11, r12, r13, r14, r15, null, r16, r17, r18);
    }

    public static /* synthetic */ int access$000(PhonebookShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(PhonebookShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1000(PhonebookShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1100(PhonebookShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1200(PhonebookShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1300(PhonebookShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ Drawable access$1400(PhonebookShareAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ Drawable access$1500(PhonebookShareAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ int access$1600(PhonebookShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1700(PhonebookShareAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1800(PhonebookShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1900(PhonebookShareAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$200(PhonebookShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2000(PhonebookShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2100(PhonebookShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2200(PhonebookShareAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$2300(PhonebookShareAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ ViewGroup access$2400(PhonebookShareAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$300(PhonebookShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$400(PhonebookShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$500(PhonebookShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$600(PhonebookShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$700(PhonebookShareAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$800(PhonebookShareAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$900(PhonebookShareAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    private /* synthetic */ void lambda$new$0(NestedScrollView r1, int r2, int r3, int r4, int r5) {
        updateLayout(!this.inLayout);
    }

    private /* synthetic */ void lambda$new$1(AndroidUtilities.VcardItem r2, DialogInterface r3, int r4) {
        if (r4 != 0) goto L12;
        ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", r2.getValue(false)));     // Catch: Exception -> L7
        if (AndroidUtilities.shouldShowClipboardToast() == false) goto L13;
        Toast.makeText(this.parentFragment.getParentActivity(), LocaleController.getString(R.string.TextCopied), 0).show();     // Catch: Exception -> L7
        return;
    L13:
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    private /* synthetic */ void lambda$new$2(int r5, View r6, View r7) {
        int r0 = this.phoneStartRow;
        if (r5 >= r0) goto L5;
    L7:
        int r02 = this.vcardStartRow;
        if (r5 >= r02) goto L10;
    L12:
        AndroidUtilities.VcardItem r03 = null;
    L13:
        if (r03 != null) goto L15;
        return;
    L15:
        boolean r2 = true;
        if (this.isImport == false) goto L36;
        int r52 = r03.type;
        if (r52 == 0) goto L55;
        if (r52 != 1) goto L28;
        Browser.openUrl(this.parentFragment.getParentActivity(), "mailto:" + r03.getValue(false));
        return;
    L28:
        if (r52 != 3) goto L34;
        String r53 = r03.getValue(false);
        if (r53.startsWith("http") == true) goto L32;
        r53 = AndroidUtilities.defaultUrlScheme(r53) + r53;
    L32:
        Browser.openUrl(this.parentFragment.getParentActivity(), r53);
        return;
    L34:
        AlertDialog.Builder r54 = new AlertDialog.Builder(this.parentFragment.getParentActivity());
        r54.setItems(new CharSequence[]{LocaleController.getString(R.string.Copy)}, new PhonebookShareAlert$$ExternalSyntheticLambda6(this, r03));
        r54.show();
        return;
    L55:
        Intent r55 = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + r03.getValue(false)));     // Catch: Exception -> L21
        r55.addFlags(268435456);     // Catch: Exception -> L21
        this.parentFragment.getParentActivity().startActivityForResult(r55, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L21
        return;
    L21:
        e = move-exception;
        FileLog.e(e);
        return;
    L36:
        r03.checked = !r03.checked;
        if (r5 >= this.phoneStartRow) goto L39;
    L53:
        ((TextCheckBoxCell) r6).setChecked(r03.checked);
        return;
    L39:
        if (r5 >= this.phoneEndRow) goto L53;
        int r56 = 0;
    L42:
        if (r56 >= this.phones.size()) goto L47;
        if (this.phones.get(r56).checked == true) goto L48;
        r56 = r56 + 1;
    L48:
        int r57 = getThemedColor(Theme.key_featuredStickers_buttonText);
        this.buttonTextView.setEnabled(r2);
        TextView r4 = this.buttonTextView;
        if (r2 == true) goto L52;
        r57 = r57 & Integer.MAX_VALUE;
    L52:
        r4.setTextColor(r57);
        goto L53
    L47:
        r2 = false;
        goto L48
    L10:
        if (r5 >= this.vcardEndRow) goto L12;
        r03 = this.other.get(r5 - r02);
        goto L13
    L5:
        if (r5 >= this.phoneEndRow) goto L7;
        r03 = this.phones.get(r5 - r0);
        goto L13
    }

    private /* synthetic */ boolean lambda$new$3(int r3, Theme.ResourcesProvider r4, Context r5, View r6) {
        int r62 = this.phoneStartRow;
        if (r3 >= r62) goto L5;
    L7:
        int r63 = this.vcardStartRow;
        if (r3 >= r63) goto L10;
    L12:
        AndroidUtilities.VcardItem r32 = null;
    L14:
        if (r32 != null) goto L16;
        return false;
    L16:
        ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", r32.getValue(false)));
        if (BulletinFactory.canShowBulletin(this.parentFragment) == true) goto L19;
    L31:
        return true;
    L19:
        if (r32.type != 3) goto L21;
        BulletinFactory.of((FrameLayout) this.containerView, r4).createCopyLinkBulletin().show();
        goto L31
    L21:
        Bulletin.SimpleLayout r64 = new Bulletin.SimpleLayout(r5, r4);
        int r33 = r32.type;
        if (r33 != 0) goto L24;
        r64.textView.setText(LocaleController.getString(R.string.PhoneCopied));
        r64.imageView.setImageResource(R.drawable.msg_calls);
    L29:
        if (AndroidUtilities.shouldShowClipboardToast() == false) goto L31;
        Bulletin.make((FrameLayout) this.containerView, r64, 1500).show();
        goto L31
    L24:
        LinkSpanDrawable.LinksTextView r42 = r64.textView;
        if (r33 != 1) goto L27;
        r42.setText(LocaleController.getString(R.string.EmailCopied));
        r64.imageView.setImageResource(R.drawable.msg_mention);
        goto L29
    L27:
        r42.setText(LocaleController.getString(R.string.TextCopied));
        r64.imageView.setImageResource(R.drawable.msg_info);
        goto L29
    L10:
        if (r3 >= this.vcardEndRow) goto L12;
        r32 = this.other.get(r3 - r63);
        goto L14
    L5:
        if (r3 >= this.phoneEndRow) goto L7;
        r32 = this.phones.get(r3 - r62);
        goto L14
    }

    private /* synthetic */ void lambda$new$4(boolean r10, int r11, int r12) {
        this.delegate.didSelectContact(this.currentUser, r10, r11, 0, false, 0);
        dismiss();
    }

    private /* synthetic */ void lambda$new$5(Long r10) {
        this.delegate.didSelectContact(this.currentUser, true, 0, 0, false, r10.longValue());
        dismiss();
    }

    private /* synthetic */ void lambda$new$6(Theme.ResourcesProvider r10, View r11) {
        if (this.isImport == false) goto L7;
        AlertDialog.Builder r102 = new AlertDialog.Builder(getContext());
        r102.setTitle(LocaleController.getString(R.string.AddContactTitle));
        r102.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r102.setItems(new CharSequence[]{LocaleController.getString(R.string.CreateNewContact), LocaleController.getString(R.string.AddToExistingContact)}, new AnonymousClass5(this));
        r102.show();
        return;
    L7:
        if (this.currentUser.restriction_reason.isEmpty() == true) goto L9;
        StringBuilder r112 = new StringBuilder(this.currentUser.restriction_reason.get(0).text);
    L10:
        int r3 = r112.lastIndexOf("END:VCARD");
        if (r3 < 0) goto L36;
        this.currentUser.phone = null;
        int r0 = this.phones.size() - 1;
    L14:
        if (r0 < 0) goto L26;
        AndroidUtilities.VcardItem r5 = this.phones.get(r0);
        if (r5.checked == false) goto L25;
        TLRPC.User r6 = this.currentUser;
        if (r6.phone != null) goto L21;
        r6.phone = r5.getValue(false);
    L21:
        int r62 = 0;
    L23:
        if (r62 >= r5.vcardData.size()) goto L25;
        r112.insert(r3, r5.vcardData.get(r62) + "\n");
        r62 = r62 + 1;
    L25:
        r0 = r0 - 1;
        goto L14
    L26:
        int r02 = this.other.size() - 1;
    L27:
        if (r02 < 0) goto L35;
        AndroidUtilities.VcardItem r1 = this.other.get(r02);
        if (r1.checked == false) goto L34;
        int r52 = r1.vcardData.size() - 1;
    L32:
        if (r52 < 0) goto L34;
        r112.insert(r3, r1.vcardData.get(r52) + "\n");
        r52 = r52 + (-1);
    L34:
        r02 = r02 - 1;
        goto L27
    L35:
        this.currentUser.restriction_reason.clear();
        TLRPC.RestrictionReason r03 = new TLRPC.RestrictionReason();
        r03.text = r112.toString();
        r03.reason = _UrlKt.FRAGMENT_ENCODE_SET;
        r03.platform = _UrlKt.FRAGMENT_ENCODE_SET;
        this.currentUser.restriction_reason.add(r03);
    L36:
        BaseFragment r113 = this.parentFragment;
        if ((r113 instanceof ChatActivity) == true) goto L39;
    L42:
        BaseFragment r103 = this.parentFragment;
        if ((r103 instanceof ChatActivity) == false) goto L45;
        long r104 = ((ChatActivity) r103).getDialogId();
    L46:
        AlertsCreator.ensurePaidMessageConfirmation(this.currentAccount, r104, 1, new PhonebookShareAlert$$ExternalSyntheticLambda5(this));
        return;
    L45:
        r104 = 0;
        goto L46
    L39:
        if (((ChatActivity) r113).isInScheduleMode() == false) goto L42;
        AlertsCreator.createScheduleDatePickerDialog(getContext(), ((ChatActivity) this.parentFragment).getDialogId(), new PhonebookShareAlert$$ExternalSyntheticLambda4(this), r10);
        return;
    L9:
        Locale r32 = Locale.US;
        TLRPC.User r4 = this.currentUser;
        r112 = new StringBuilder(String.format(r32, "BEGIN:VCARD\nVERSION:3.0\nFN:%1$s\nEND:VCARD", new Object[]{ContactsController.formatName(r4.first_name, r4.last_name)}));
        goto L10
    }

    private void updateLayout(boolean r18) {
        Integer r2 = 1;
        View r3 = this.scrollView.getChildAt(0);
        int r5 = r3.getTop() - this.scrollView.getScrollY();
        if (r5 >= 0) goto L6;
        r5 = 0;
    L6:
        if (r5 > 0) goto L8;
        boolean r6 = true;
    L9:
        float r10 = 0.0f;
        if (r6 == true) goto L12;
    L13:
        if (r6 == false) goto L15;
    L43:
        char r16 = 0;
    L45:
        if (this.scrollOffsetY == r5) goto L47;
        this.scrollOffsetY = r5;
        this.containerView.invalidate();
    L47:
        r3.getBottom();
        this.scrollView.getMeasuredHeight();
        if ((r3.getBottom() - this.scrollView.getScrollY()) <= this.scrollView.getMeasuredHeight()) goto L50;
        char r32 = 1;
    L51:
        if (r32 != 0) goto L53;
    L54:
        if (r32 == 0) goto L56;
        return;
    L56:
        if (this.shadow.getTag() == null) goto L76;
    L57:
        View r4 = this.shadow;
        if (r32 != 0) goto L61;
        r2 = null;
    L61:
        r4.setTag(r2);
        AnimatorSet r22 = this.shadowAnimation;
        if (r22 == null) goto L64;
        r22.cancel();
        this.shadowAnimation = null;
    L64:
        if (r18 == false) goto L70;
        AnimatorSet r23 = new AnimatorSet();
        this.shadowAnimation = r23;
        r23.setDuration(180);
        AnimatorSet r24 = this.shadowAnimation;
        View r42 = this.shadow;
        Property r52 = View.ALPHA;
        if (r32 == 0) goto L68;
        r10 = 1.0f;
    L68:
        float[] r33 = new float[1];
        r33[r16] = r10;
        Animator[] r1 = new Animator[1];
        r1[r16] = ObjectAnimator.ofFloat(r42, r52, r33);
        r24.playTogether(r1);
        this.shadowAnimation.addListener(new AnonymousClass8(this));
        this.shadowAnimation.start();
        return;
    L70:
        View r0 = this.shadow;
        if (r32 == 0) goto L73;
        r10 = 1.0f;
    L73:
        r0.setAlpha(r10);
        return;
    L76:
        return;
    L53:
        if (this.shadow.getTag() == null) goto L57;
    L50:
        r32 = r16;
        goto L51
    L15:
        if (this.actionBar.getTag() == null) goto L43;
    L16:
        ActionBar r12 = this.actionBar;
        if (r6 == false) goto L19;
        Integer r13 = r2;
    L20:
        r12.setTag(r13);
        AnimatorSet r122 = this.actionBarAnimation;
        if (r122 == null) goto L23;
        r122.cancel();
        this.actionBarAnimation = null;
    L23:
        if (r18 == false) goto L34;
        AnimatorSet r123 = new AnimatorSet();
        this.actionBarAnimation = r123;
        r123.setDuration(180);
        AnimatorSet r124 = this.actionBarAnimation;
        ActionBar r132 = this.actionBar;
        Property r14 = View.ALPHA;
        if (r6 == false) goto L28;
        float r15 = 1.0f;
    L27:
        r16 = 0;
        ObjectAnimator r43 = ObjectAnimator.ofFloat(r132, r14, new float[]{r15});
        View r133 = this.actionBarShadow;
        if (r6 == false) goto L32;
        float r62 = 1.0f;
    L33:
        r124.playTogether(new Animator[]{r43, ObjectAnimator.ofFloat(r133, r14, new float[]{r62})});
        this.actionBarAnimation.addListener(new AnonymousClass7(this));
        this.actionBarAnimation.start();
        goto L45
    L32:
        r62 = 0.0f;
        goto L33
    L28:
        r15 = 0.0f;
        goto L27
    L34:
        r16 = 0;
        ActionBar r44 = this.actionBar;
        if (r6 == false) goto L37;
        float r125 = 1.0f;
    L38:
        r44.setAlpha(r125);
        View r45 = this.actionBarShadow;
        if (r6 == false) goto L41;
        float r63 = 1.0f;
    L42:
        r45.setAlpha(r63);
        goto L45
    L41:
        r63 = 0.0f;
        goto L42
    L37:
        r125 = 0.0f;
        goto L38
    L19:
        r13 = null;
        goto L20
    L12:
        if (this.actionBar.getTag() == null) goto L16;
    L8:
        r6 = false;
        goto L9
    }

    private void updateRows() {
        this.rowCount = 1;
        this.userRow = 0;
        if (this.phones.size() > 1) goto L9;
        if (this.other.isEmpty() == false) goto L9;
        this.phoneStartRow = -1;
        this.phoneEndRow = -1;
        this.vcardStartRow = -1;
        this.vcardEndRow = -1;
        return;
    L9:
        if (this.phones.isEmpty() == false) goto L11;
        this.phoneStartRow = -1;
        this.phoneEndRow = -1;
    L13:
        if (this.other.isEmpty() == false) goto L16;
        this.vcardStartRow = -1;
        this.vcardEndRow = -1;
        return;
    L16:
        int r0 = this.rowCount;
        this.vcardStartRow = r0;
        int r02 = r0 + this.other.size();
        this.rowCount = r02;
        this.vcardEndRow = r02;
        return;
    L11:
        int r03 = this.rowCount;
        this.phoneStartRow = r03;
        int r04 = r03 + this.phones.size();
        this.rowCount = r04;
        this.phoneEndRow = r04;
        goto L13
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onStart() {
        super.onStart();
        Bulletin.addDelegate((FrameLayout) this.containerView, new AnonymousClass6(this));
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        Bulletin.removeDelegate((FrameLayout) this.containerView);
    }

    public void setDelegate(ChatAttachAlertContactsLayout.PhonebookShareAlertDelegate r1) {
        this.delegate = r1;
    }
}
