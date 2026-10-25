package org.telegram.messenger;

import android.app.Activity;
import com.google.android.recaptcha.Recaptcha;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaTasksClient;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import org.telegram.tgnet.ConnectionsManager;

/* loaded from: classes5.dex */
public class CaptchaController {
    public static HashMap<Integer, Request> currentRequests;

    public static class Request {
        public String action;
        public int currentAccount;
        public String key_id;
        public HashSet<Integer> requestTokens;

        public Request(int r2, String r3, String r4) {
            this.requestTokens = new HashSet();
            this.currentAccount = r2;
            this.action = r3;
            this.key_id = r4;
        }

        public void done(String r6) {
            CaptchaController.currentRequests.remove(Integer.valueOf(hashCode()));
            int[] r0 = new int[this.requestTokens.size()];
            Iterator<Integer> r1 = this.requestTokens.iterator();
            int r2 = 0;
        L4:
            if (r1.hasNext() == false) goto L6;
            r0[r2] = r1.next().intValue();
            r2 = r2 + 1;
            goto L4
        L6:
            ConnectionsManager.getInstance(this.currentAccount);
            ConnectionsManager.native_receivedCaptchaResult(this.currentAccount, r0, r6);
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Integer.valueOf(this.currentAccount), this.action, this.key_id});
        }
    }

    public static /* synthetic */ void $r8$lambda$AMiLmmmYyKOwfWSiRvRczX2HcNw(String r2, String r3, Request r4, String r5) {
        FileLog.d("CaptchaController: got token for {action=" + r2 + ", key_id=" + r3 + "}: " + r5);
        if (r5 != null) goto L6;
        r4.done("RECAPTCHA_FAILED_TOKEN_NULL");
        return;
    L6:
        r4.done(r5);
    }

    public static /* synthetic */ void $r8$lambda$UjfC46a1LwdNoQ4PzIEIXfzBxPc(Request r2, Exception r3) {
        FileLog.e("CaptchaController: executeTask failure", r3);
        r2.done("RECAPTCHA_FAILED_TASK_EXCEPTION_" + formatException(r3));
    }

    public static /* synthetic */ void $r8$lambda$sd2jL_u9Guq5e7dqFK5pWLGn8dc(String r1, String r2, Request r3, RecaptchaTasksClient r4) {
        r4.executeTask(getAction(r1)).addOnSuccessListener(new CaptchaController$$ExternalSyntheticLambda2(r1, r2, r3)).addOnFailureListener(new CaptchaController$$ExternalSyntheticLambda3(r3));
    }

    /* renamed from: $r8$lambda$stK-JxtC1RPLdV30yoHtFVwBWyI, reason: not valid java name */
    public static /* synthetic */ void m3914$r8$lambda$stKJxtC1RPLdV30yoHtFVwBWyI(Request r2, Exception r3) {
        FileLog.e("CaptchaController: getTasksClient failure", r3);
        r2.done("RECAPTCHA_FAILED_GETCLIENT_EXCEPTION_" + formatException(r3));
    }

    public CaptchaController() {
    }

    private static String formatException(Exception r2) {
        if (r2 != null) goto L6;
        return "NULL";
    L6:
        if (r2.getMessage() != null) goto L10;
        return "MSG_NULL";
    L10:
        return r2.getMessage().replaceAll(" ", "_").toUpperCase();
    }

    private static RecaptchaAction getAction(String r2) {
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -1849137896: goto L18;
            case -902467304: goto L14;
            case 72611657: goto L10;
            case 103149417: goto L6;
            default: goto L21;
        };
    L21:
        switch(r1) {
            case 0: goto L27;
            case 1: goto L27;
            case 2: goto L25;
            case 3: goto L25;
            default: goto L23;
        };
    L23:
        return RecaptchaAction.custom(r2);
    L25:
        return RecaptchaAction.LOGIN;
    L27:
        return RecaptchaAction.SIGNUP;
    L6:
        if (r2.equals("login") == false) goto L21;
        r1 = 3;
        goto L21
    L10:
        if (r2.equals("LOGIN") == false) goto L21;
        r1 = 2;
        goto L21
    L14:
        if (r2.equals("signup") == false) goto L21;
        r1 = 1;
        goto L21
    L18:
        if (r2.equals("SIGNUP") == false) goto L21;
        r1 = 0;
        goto L21
    }

    public static void request(int r2, int r3, String r4, String r5) {
        if (currentRequests != null) goto L5;
        currentRequests = new HashMap();
    L5:
        int r0 = Objects.hash(new Object[]{Integer.valueOf(r2), r4, r5});
        Request r02 = currentRequests.get(Integer.valueOf(r0));
        if (r02 == null) goto L9;
        r02.requestTokens.add(Integer.valueOf(r3));
        return;
    L9:
        Request r03 = new Request(r2, r4, r5);
        r03.requestTokens.add(Integer.valueOf(r3));
        Activity r22 = AndroidUtilities.getActivity();
        if (r22 != null) goto L13;
        FileLog.e("CaptchaController: no activity found");
        r03.done("RECAPTCHA_FAILED_NO_ACTIVITY");
        return;
    L13:
        Recaptcha.getTasksClient(r22.getApplication(), r5).addOnSuccessListener(new CaptchaController$$ExternalSyntheticLambda0(r4, r5, r03)).addOnFailureListener(new CaptchaController$$ExternalSyntheticLambda1(r03));
    }
}
