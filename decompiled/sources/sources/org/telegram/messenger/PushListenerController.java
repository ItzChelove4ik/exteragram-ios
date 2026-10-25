package org.telegram.messenger;

import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.CountDownLatch;
import kotlin.text.Typography;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;

@Keep
/* loaded from: classes.dex */
public class PushListenerController {
    public static final int NOTIFICATION_ID = 1;
    public static final int PUSH_TYPE_FIREBASE = 2;
    public static final int PUSH_TYPE_HUAWEI = 13;
    private static CountDownLatch countDownLatch;

    public static final class GooglePushListenerServiceProvider implements IPushListenerServiceProvider {
        public static final GooglePushListenerServiceProvider INSTANCE = null;
        private Boolean hasServices;

        public static /* synthetic */ void $r8$lambda$VdUVLLJcuAh1raWea36fJNClvE0(GooglePushListenerServiceProvider r0, Task r1) {
            r0.lambda$onRequestPushToken$0(r1);
        }

        /* renamed from: $r8$lambda$v_QkzGrUV-uU3IM315-48c9B_0k, reason: not valid java name */
        public static /* synthetic */ void m4817$r8$lambda$v_QkzGrUVuU3IM31548c9B_0k(GooglePushListenerServiceProvider r0) {
            r0.lambda$onRequestPushToken$1();
        }

        static {
            INSTANCE = new GooglePushListenerServiceProvider();
        }

        private GooglePushListenerServiceProvider() {
        }

        private /* synthetic */ void lambda$onRequestPushToken$0(Task r3) {
            SharedConfig.pushStringGetTimeEnd = SystemClock.elapsedRealtime();
            if (r3.isSuccessful() == false) goto L5;
            String r32 = (String) r3.getResult();
            if (TextUtils.isEmpty(r32) == true) goto L13;
            PushListenerController.sendRegistrationToServer(getPushType(), r32);
            return;
        L13:
            return;
        L5:
            if (BuildVars.LOGS_ENABLED == false) goto L7;
            FileLog.d("Failed to get regid");
        L7:
            SharedConfig.pushStringStatus = "__FIREBASE_FAILED__";
            PushListenerController.sendRegistrationToServer(getPushType(), null);
        }

        private /* synthetic */ void lambda$onRequestPushToken$1() {
            SharedConfig.pushStringGetTimeStart = SystemClock.elapsedRealtime();     // Catch: Throwable -> L4
            FirebaseApp.initializeApp(ApplicationLoader.applicationContext);     // Catch: Throwable -> L4
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new PushListenerController$GooglePushListenerServiceProvider$$ExternalSyntheticLambda0(this));     // Catch: Throwable -> L4
            return;
        L4:
            th = move-exception;
            FileLog.e(th);
        }

        @Override // org.telegram.messenger.PushListenerController.IPushListenerServiceProvider
        public String getLogTitle() {
            return "Google Play Services";
        }

        @Override // org.telegram.messenger.PushListenerController.IPushListenerServiceProvider
        public int getPushType() {
            return 2;
        }

        @Override // org.telegram.messenger.PushListenerController.IPushListenerServiceProvider
        public boolean hasServices() {
            if (this.hasServices != null) goto L13;
        L10:
            e = move-exception;
            FileLog.e(e);
            this.hasServices = Boolean.FALSE;
            goto L13
        L5:
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(ApplicationLoader.applicationContext) != 0) goto L7;
            boolean r0 = true;
        L8:
            this.hasServices = Boolean.valueOf(r0);     // Catch: Exception -> L10
            goto L13
        L7:
            r0 = false;
        L13:
            return this.hasServices.booleanValue();
        }

        @Override // org.telegram.messenger.PushListenerController.IPushListenerServiceProvider
        public void onRequestPushToken() {
            String r0 = SharedConfig.pushString;
            if (TextUtils.isEmpty(r0) == true) goto L10;
            if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L7;
        L12:
            Utilities.globalQueue.postRunnable(new PushListenerController$GooglePushListenerServiceProvider$$ExternalSyntheticLambda1(this));
            return;
        L7:
            if (BuildVars.LOGS_ENABLED == false) goto L12;
            FileLog.d("FCM regId = " + r0);
            goto L12
        L10:
            if (BuildVars.LOGS_ENABLED == false) goto L12;
            FileLog.d("FCM Registration not found.");
            goto L12
        }
    }

    @Keep
    public interface IPushListenerServiceProvider {
        String getLogTitle();

        int getPushType();

        boolean hasServices();

        void onRequestPushToken();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes5.dex */
    public @interface PushType {
    }

    public static /* synthetic */ void $r8$lambda$P1a24T7VvkcTjlUqZlJxlqIf4HM(int r4) {
        if (UserConfig.getInstance(r4).getClientUserId() == 0) goto L6;
        UserConfig.getInstance(r4).clearConfig();
        MessagesController.getInstance(r4).performLogout(0);
        return;
    }

    public static /* synthetic */ void $r8$lambda$X4AFqiYuWKNVxKgP6ag2LMkLD4I(int r1, long r2, int r4) {
        MessagesController.getInstance(r1).reportMessageDelivery(r2, r4, true);
    }

    public static /* synthetic */ void $r8$lambda$ajFFKH7fIgEVRIQyyG8JkwTnc2M(String r0, String r1, long r2) {
        lambda$processRemoteMessage$6(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$dNjoKzLxdgjjiAu7LAa_JzI_oiA(int r0, int r1, String r2) {
        MessagesController.getInstance(r0).registerForPush(r1, r2);
    }

    /* renamed from: $r8$lambda$gWkEZv3oxmETvqnhw1v-3CF-Wl4, reason: not valid java name */
    public static /* synthetic */ void m4815$r8$lambda$gWkEZv3oxmETvqnhw1v3CFWl4(String r12, int r13) {
        ConnectionsManager.setRegId(r12, r13, SharedConfig.pushStringStatus);
        if (r12 != null) goto L6;
        return;
    L6:
        if (SharedConfig.pushStringGetTimeStart != 0) goto L8;
    L14:
        boolean r0 = false;
    L15:
        SharedConfig.pushString = r12;
        SharedConfig.pushType = r13;
        int r5 = 0;
    L17:
        if (r5 >= 16) goto L32;
        UserConfig r6 = UserConfig.getInstance(r5);
        r6.registeredForPush = false;
        r6.saveConfig(false);
        if (r6.getClientUserId() == 0) goto L27;
        if (r0 == true) goto L22;
    L26:
        AndroidUtilities.runOnUIThread(new PushListenerController$$ExternalSyntheticLambda5(r5, r13, r12));
        goto L27
    L22:
        if (r13 != 2) goto L24;
        String r02 = "fcm";
    L25:
        TLRPC.TL_help_saveAppLog r62 = new TLRPC.TL_help_saveAppLog();
        TLRPC.TL_inputAppEvent r7 = new TLRPC.TL_inputAppEvent();
        r7.time = SharedConfig.pushStringGetTimeStart;
        r7.type = r02.concat("_token_request");
        r7.peer = 0;
        r7.data = new TLRPC.TL_jsonNull();
        r62.events.add(r7);
        TLRPC.TL_inputAppEvent r72 = new TLRPC.TL_inputAppEvent();
        r72.time = SharedConfig.pushStringGetTimeEnd;
        r72.type = r02.concat("_token_response");
        r72.peer = SharedConfig.pushStringGetTimeEnd - SharedConfig.pushStringGetTimeStart;
        r72.data = new TLRPC.TL_jsonNull();
        r62.events.add(r72);
        SharedConfig.pushStatSent = true;
        SharedConfig.saveConfig();
        ConnectionsManager.getInstance(r5).sendRequest(r62, null);
        r0 = false;
        goto L26
    L24:
        r02 = "hcm";
    L27:
        r5 = r5 + 1;
        goto L17
    L32:
        return;
    L8:
        if (SharedConfig.pushStringGetTimeEnd == 0) goto L14;
        if (SharedConfig.pushStatSent == true) goto L12;
    L13:
        SharedConfig.pushStatSent = false;
        r0 = true;
        goto L15
    L12:
        if (TextUtils.equals(SharedConfig.pushString, r12) == true) goto L14;
        goto L13
    }

    public static /* synthetic */ void $r8$lambda$iPr3PgkHJLl5aQDihBXflfLF_jI(int r1, TLRPC.TL_updates r2) {
        MessagesController.getInstance(r1).processUpdates(r2, false);
    }

    /* renamed from: $r8$lambda$k5j5e9Lujg-skyyg8gtnnTTFOME, reason: not valid java name */
    public static /* synthetic */ void m4816$r8$lambda$k5j5e9Lujgskyyg8gtnnTTFOME(int r0) {
        LocationController.getInstance(r0).setNewLocationEndWatchTime();
    }

    public static /* synthetic */ void $r8$lambda$nYIZ4V9UJXbKQeRZEvfo7jiLnHo(String r2, String r3, long r4) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d(r2 + " PRE INIT APP");
    L5:
        ApplicationLoader.postInitApplication();
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        FileLog.d(r2 + " POST INIT APP");
    L8:
        Utilities.stageQueue.postRunnable(new PushListenerController$$ExternalSyntheticLambda6(r2, r3, r4));
    }

    static {
        countDownLatch = new CountDownLatch(1);
    }

    public PushListenerController() {
    }

    private static String getReactedText(String r2, Object[] r3) {
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -2114646919: goto L158;
            case -1891797827: goto L154;
            case -1773019340: goto L150;
            case -1553058678: goto L146;
            case -1415696683: goto L142;
            case -1375264434: goto L138;
            case -1105974394: goto L134;
            case -861247200: goto L130;
            case -661458538: goto L126;
            case 51977938: goto L122;
            case 52259487: goto L118;
            case 52294965: goto L114;
            case 52369421: goto L110;
            case 52378406: goto L106;
            case 147425325: goto L102;
            case 192842257: goto L98;
            case 192844842: goto L94;
            case 192844957: goto L90;
            case 591941181: goto L86;
            case 635226735: goto L82;
            case 648703179: goto L78;
            case 650764327: goto L74;
            case 654263060: goto L70;
            case 731873318: goto L66;
            case 932558943: goto L62;
            case 1149769750: goto L58;
            case 1606362326: goto L54;
            case 1619838770: goto L50;
            case 1621899918: goto L46;
            case 1622966773: goto L42;
            case 1625398651: goto L38;
            case 1664242232: goto L34;
            case 1664244817: goto L30;
            case 1664244932: goto L26;
            case 1683218969: goto L22;
            case 1683500518: goto L18;
            case 1683535996: goto L14;
            case 1683610452: goto L10;
            case 1683619437: goto L6;
            default: goto L161;
        };
    L161:
        switch(r1) {
            case 0: goto L241;
            case 1: goto L239;
            case 2: goto L237;
            case 3: goto L235;
            case 4: goto L233;
            case 5: goto L231;
            case 6: goto L229;
            case 7: goto L227;
            case 8: goto L225;
            case 9: goto L223;
            case 10: goto L221;
            case 11: goto L219;
            case 12: goto L217;
            case 13: goto L215;
            case 14: goto L213;
            case 15: goto L211;
            case 16: goto L209;
            case 17: goto L207;
            case 18: goto L205;
            case 19: goto L203;
            case 20: goto L201;
            case 21: goto L199;
            case 22: goto L197;
            case 23: goto L195;
            case 24: goto L193;
            case 25: goto L191;
            case 26: goto L189;
            case 27: goto L187;
            case 28: goto L185;
            case 29: goto L183;
            case 30: goto L181;
            case 31: goto L179;
            case 32: goto L177;
            case 33: goto L175;
            case 34: goto L173;
            case 35: goto L171;
            case 36: goto L169;
            case 37: goto L167;
            case 38: goto L165;
            default: goto L162;
        };
    L162:
        return null;
    L165:
        return LocaleController.formatString(R.string.PushChatReactTodo, r3);
    L167:
        return LocaleController.formatString(R.string.PushChatReactText, r3);
    L169:
        return LocaleController.formatString(R.string.PushChatReactQuiz, r3);
    L171:
        return LocaleController.formatString(R.string.PushChatReactPoll, r3);
    L173:
        return LocaleController.formatString(R.string.PushChatReactGame, r3);
    L175:
        return LocaleController.formatString(R.string.PushReactGif, r3);
    L177:
        return LocaleController.formatString(R.string.PushReactGeo, r3);
    L179:
        return LocaleController.formatString(R.string.PushReactDoc, r3);
    L181:
        return LocaleController.formatString(R.string.PushReactVideo, r3);
    L183:
        return LocaleController.formatString(R.string.PushReactStory, r3);
    L185:
        return LocaleController.formatString(R.string.PushReactRound, r3);
    L187:
        return LocaleController.formatString(R.string.PushReactPhoto, r3);
    L189:
        return LocaleController.formatString(R.string.PushReactAudio, r3);
    L191:
        return LocaleController.formatString(R.string.PushChatReactGeoLive, r3);
    L193:
        return LocaleController.formatString(R.string.NotificationReactGiveaway, r3);
    L195:
        return LocaleController.formatString(R.string.NotificationChatReactGiveaway, r3);
    L197:
        return LocaleController.formatString(R.string.PushChatReactVideo, r3);
    L199:
        return LocaleController.formatString(R.string.PushChatReactRound, r3);
    L201:
        return LocaleController.formatString(R.string.PushChatReactPhoto, r3);
    L203:
        return LocaleController.formatString(R.string.PushChatReactAudio, r3);
    L205:
        return LocaleController.formatString(R.string.PushReactSticker, r3);
    L207:
        return LocaleController.formatString(R.string.PushChatReactGif, r3);
    L209:
        return LocaleController.formatString(R.string.PushChatReactGeo, r3);
    L211:
        return LocaleController.formatString(R.string.PushChatReactDoc, r3);
    L213:
        return LocaleController.formatString(R.string.PushReactInvoice, r3);
    L215:
        return LocaleController.formatString(R.string.PushReactTodo, r3);
    L217:
        return LocaleController.formatString(R.string.PushReactText, r3);
    L219:
        return LocaleController.formatString(R.string.PushReactQuiz, r3);
    L221:
        return LocaleController.formatString(R.string.PushReactPoll, r3);
    L223:
        return LocaleController.formatString(R.string.PushReactGame, r3);
    L225:
        return LocaleController.formatString(R.string.PushChatReactSticker, r3);
    L227:
        return LocaleController.formatString(R.string.PushReactContect, r3);
    L229:
        return LocaleController.formatString(R.string.PushChatReactInvoice, r3);
    L231:
        return LocaleController.formatString(R.string.PushReactNoText, r3);
    L233:
        return LocaleController.formatString(R.string.PushChatReactNotext, r3);
    L235:
        return LocaleController.formatString(R.string.PushReactHidden, r3);
    L237:
        return LocaleController.formatString(R.string.PushReactStoryHidden, r3);
    L239:
        return LocaleController.formatString(R.string.PushReactGeoLocation, r3);
    L241:
        return LocaleController.formatString(R.string.PushChatReactContact, r3);
    L6:
        if (r2.equals("CHAT_REACT_TODO") == false) goto L161;
        r1 = Typography.amp;
        goto L161
    L10:
        if (r2.equals("CHAT_REACT_TEXT") == false) goto L161;
        r1 = '%';
        goto L161
    L14:
        if (r2.equals("CHAT_REACT_QUIZ") == false) goto L161;
        r1 = Typography.dollar;
        goto L161
    L18:
        if (r2.equals("CHAT_REACT_POLL") == false) goto L161;
        r1 = '#';
        goto L161
    L22:
        if (r2.equals("CHAT_REACT_GAME") == false) goto L161;
        r1 = Typography.quote;
        goto L161
    L26:
        if (r2.equals("REACT_GIF") == false) goto L161;
        r1 = '!';
        goto L161
    L30:
        if (r2.equals("REACT_GEO") == false) goto L161;
        r1 = ' ';
        goto L161
    L34:
        if (r2.equals("REACT_DOC") == false) goto L161;
        r1 = 31;
        goto L161
    L38:
        if (r2.equals("REACT_VIDEO") == false) goto L161;
        r1 = 30;
        goto L161
    L42:
        if (r2.equals("REACT_STORY") == false) goto L161;
        r1 = 29;
        goto L161
    L46:
        if (r2.equals("REACT_ROUND") == false) goto L161;
        r1 = 28;
        goto L161
    L50:
        if (r2.equals("REACT_PHOTO") == false) goto L161;
        r1 = 27;
        goto L161
    L54:
        if (r2.equals("REACT_AUDIO") == false) goto L161;
        r1 = 26;
        goto L161
    L58:
        if (r2.equals("CHAT_REACT_GEOLIVE") == false) goto L161;
        r1 = 25;
        goto L161
    L62:
        if (r2.equals("REACT_GIVEAWAY") == false) goto L161;
        r1 = 24;
        goto L161
    L66:
        if (r2.equals("CHAT_REACT_GIVEAWAY") == false) goto L161;
        r1 = 23;
        goto L161
    L70:
        if (r2.equals("CHAT_REACT_VIDEO") == false) goto L161;
        r1 = 22;
        goto L161
    L74:
        if (r2.equals("CHAT_REACT_ROUND") == false) goto L161;
        r1 = 21;
        goto L161
    L78:
        if (r2.equals("CHAT_REACT_PHOTO") == false) goto L161;
        r1 = 20;
        goto L161
    L82:
        if (r2.equals("CHAT_REACT_AUDIO") == false) goto L161;
        r1 = 19;
        goto L161
    L86:
        if (r2.equals("REACT_STICKER") == false) goto L161;
        r1 = 18;
        goto L161
    L90:
        if (r2.equals("CHAT_REACT_GIF") == false) goto L161;
        r1 = 17;
        goto L161
    L94:
        if (r2.equals("CHAT_REACT_GEO") == false) goto L161;
        r1 = 16;
        goto L161
    L98:
        if (r2.equals("CHAT_REACT_DOC") == false) goto L161;
        r1 = 15;
        goto L161
    L102:
        if (r2.equals("REACT_INVOICE") == false) goto L161;
        r1 = 14;
        goto L161
    L106:
        if (r2.equals("REACT_TODO") == false) goto L161;
        r1 = '\r';
        goto L161
    L110:
        if (r2.equals("REACT_TEXT") == false) goto L161;
        r1 = '\f';
        goto L161
    L114:
        if (r2.equals("REACT_QUIZ") == false) goto L161;
        r1 = 11;
        goto L161
    L118:
        if (r2.equals("REACT_POLL") == false) goto L161;
        r1 = '\n';
        goto L161
    L122:
        if (r2.equals("REACT_GAME") == false) goto L161;
        r1 = '\t';
        goto L161
    L126:
        if (r2.equals("CHAT_REACT_STICKER") == false) goto L161;
        r1 = '\b';
        goto L161
    L130:
        if (r2.equals("REACT_CONTACT") == false) goto L161;
        r1 = 7;
        goto L161
    L134:
        if (r2.equals("CHAT_REACT_INVOICE") == false) goto L161;
        r1 = 6;
        goto L161
    L138:
        if (r2.equals("REACT_NOTEXT") == false) goto L161;
        r1 = 5;
        goto L161
    L142:
        if (r2.equals("CHAT_REACT_NOTEXT") == false) goto L161;
        r1 = 4;
        goto L161
    L146:
        if (r2.equals("REACT_HIDDEN") == false) goto L161;
        r1 = 3;
        goto L161
    L150:
        if (r2.equals("REACT_STORY_HIDDEN") == false) goto L161;
        r1 = 2;
        goto L161
    L154:
        if (r2.equals("REACT_GEOLIVE") == false) goto L161;
        r1 = 1;
        goto L161
    L158:
        if (r2.equals("CHAT_REACT_CONTACT") == false) goto L161;
        r1 = 0;
        goto L161
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    private static /* synthetic */ void lambda$processRemoteMessage$6(java.lang.String r69, java.lang.String r70, long r71) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001d: CONSTRUCTOR (r10v74 ?? I:java.lang.StringBuilder) =  A[DECLARE_VAR, MD:():void (c)] call: java.lang.StringBuilder.<init>():void type: CONSTRUCTOR in method: org.telegram.messenger.PushListenerController.lambda$processRemoteMessage$6(java.lang.String, java.lang.String, long):void, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:355)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.ArgType.getPrimitiveType()" because "type" is null
            	at jadx.core.codegen.ClassGen.useType(ClassGen.java:554)
            	at jadx.core.codegen.InsnGen.useType(InsnGen.java:269)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:166)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:159)
            	at jadx.core.codegen.InsnGen.assignVar(InsnGen.java:152)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:299)
            	... 11 more
            */
        /*
            Method dump skipped, instructions count: 9542
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.telegram.messenger.PushListenerController.lambda$processRemoteMessage$6(java.lang.String, java.lang.String, long):void");
    }

    private static void onDecryptError() {
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L9;
        if (UserConfig.getInstance(r0).isClientActivated() == false) goto L8;
        ConnectionsManager.onInternalPushReceived(r0);
        ConnectionsManager.getInstance(r0).resumeNetworkMaybe();
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        countDownLatch.countDown();
    }

    public static void processRemoteMessage(int r3, String r4, long r5) {
        if (r3 != 2) goto L5;
        String r32 = "FCM";
    L7:
        if (BuildVars.LOGS_ENABLED == false) goto L9;
        FileLog.d(r32.concat(" PRE START PROCESSING"));
    L9:
        long r0 = SystemClock.elapsedRealtime();
        AndroidUtilities.runOnUIThread(new PushListenerController$$ExternalSyntheticLambda7(r32, r4, r5));
        countDownLatch.await();     // Catch: Throwable -> L15
    L12:
        if (BuildVars.DEBUG_VERSION == false) goto L18;
        FileLog.d("finished " + r32 + " service, time = " + (SystemClock.elapsedRealtime() - r0));
        return;
    L18:
        return;
    L5:
        r32 = "HCM";
        goto L7
    }

    public static void sendRegistrationToServer(int r2, String r3) {
        Utilities.stageQueue.postRunnable(new PushListenerController$$ExternalSyntheticLambda0(r3, r2));
    }
}
