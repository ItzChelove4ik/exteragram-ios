package com.stripe.android;

import android.os.AsyncTask;
import com.stripe.android.exception.AuthenticationException;
import com.stripe.android.exception.StripeException;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.net.RequestOptions;
import com.stripe.android.net.StripeApiHandler;
import com.stripe.android.util.StripeNetworkUtils;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public class Stripe {
    private String defaultPublishableKey;
    TokenCreator tokenCreator;

    /* renamed from: com.stripe.android.Stripe$1, reason: invalid class name */
    public class AnonymousClass1 implements TokenCreator {
        final /* synthetic */ Stripe this$0;

        /* renamed from: com.stripe.android.Stripe$1$1, reason: invalid class name and collision with other inner class name */
        public class AsyncTaskC00301 extends AsyncTask<Void, Void, ResponseWrapper> {
            final /* synthetic */ AnonymousClass1 this$1;
            final /* synthetic */ TokenCallback val$callback;
            final /* synthetic */ Card val$card;
            final /* synthetic */ String val$publishableKey;

            public AsyncTaskC00301(AnonymousClass1 r1, String r2, Card r3, TokenCallback r4) {
                this.this$1 = r1;
                this.val$publishableKey = r2;
                this.val$card = r3;
                this.val$callback = r4;
            }

            /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
            public ResponseWrapper doInBackground2(Void... r4) {
                RequestOptions r0 = RequestOptions.builder(this.val$publishableKey).build();     // Catch: StripeException -> L5
                Token r02 = StripeApiHandler.createToken(StripeNetworkUtils.hashMapFromCard(this.val$card), r0);     // Catch: StripeException -> L5
                return new ResponseWrapper(this.this$1.this$0, r02, null, null);
            L5:
                e = move-exception;
                return new ResponseWrapper(this.this$1.this$0, null, e, null);
            }

            @Override // android.os.AsyncTask
            public /* bridge */ /* synthetic */ ResponseWrapper doInBackground(Void[] r1) {
                return doInBackground2(r1);
            }

            /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
            public void onPostExecute2(ResponseWrapper r2) {
                Stripe.access$100(this.this$1.this$0, r2, this.val$callback);
            }

            @Override // android.os.AsyncTask
            public /* bridge */ /* synthetic */ void onPostExecute(ResponseWrapper r1) {
                onPostExecute2(r1);
            }
        }

        public AnonymousClass1(Stripe r1) {
            this.this$0 = r1;
        }

        @Override // com.stripe.android.Stripe.TokenCreator
        public void create(Card r2, String r3, Executor r4, TokenCallback r5) {
            AsyncTaskC00301 r0 = new AsyncTaskC00301(this, r3, r2, r5);
            Stripe.access$200(this.this$0, r4, r0);
        }
    }

    public class ResponseWrapper {
        final Exception error;
        final /* synthetic */ Stripe this$0;
        final Token token;

        private ResponseWrapper(Stripe r1, Token r2, Exception r3) {
            this.this$0 = r1;
            this.error = r3;
            this.token = r2;
        }

        public /* synthetic */ ResponseWrapper(Stripe r1, Token r2, Exception r3, AnonymousClass1 r4) {
            this(r1, r2, r3);
        }
    }

    public interface TokenCreator {
        void create(Card r1, String r2, Executor r3, TokenCallback r4);
    }

    public Stripe(String r2) {
        this.tokenCreator = new AnonymousClass1(this);
        setDefaultPublishableKey(r2);
    }

    public static /* synthetic */ void access$100(Stripe r0, ResponseWrapper r1, TokenCallback r2) {
        r0.tokenTaskPostExecution(r1, r2);
    }

    public static /* synthetic */ void access$200(Stripe r0, Executor r1, AsyncTask r2) {
        r0.executeTokenTask(r1, r2);
    }

    private void executeTokenTask(Executor r1, AsyncTask<Void, Void, ResponseWrapper> r2) {
        if (r1 == null) goto L6;
        r2.executeOnExecutor(r1, new Void[0]);
        return;
    L6:
        r2.execute(new Void[0]);
    }

    private void tokenTaskPostExecution(ResponseWrapper r1, TokenCallback r2) {
        Token r0 = r1.token;
        if (r0 == null) goto L6;
        r2.onSuccess(r0);
        return;
    L6:
        Exception r02 = r1.error;
        if (r02 == null) goto L10;
        r2.onError(r02);
        return;
    L10:
        r2.onError(new RuntimeException("Somehow got neither a token response or an error response"));
    }

    private void validateKey(String r3) {
        if (r3 == null) goto L12;
        if (r3.length() == 0) goto L12;
        if (r3.startsWith("sk_") == true) goto L10;
        return;
    L10:
        throw new AuthenticationException("Invalid Publishable Key: You are using a secret key to create a token, instead of the publishable one. For more info, see https://stripe.com/docs/stripe.js", null, 0);
    L12:
        throw new AuthenticationException("Invalid Publishable Key: You must use a valid publishable key to create a token.  For more info, see https://stripe.com/docs/stripe.js.", null, 0);
    }

    public void createToken(Card r2, TokenCallback r3) {
        createToken(r2, this.defaultPublishableKey, r3);
    }

    public void createToken(Card r2, String r3, TokenCallback r4) {
        createToken(r2, r3, null, r4);
    }

    public void createToken(Card r1, String r2, Executor r3, TokenCallback r4) {
        if (r1 == null) goto L11;
        if (r4 == null) goto L9;
        validateKey(r2);     // Catch: AuthenticationException -> L6
        this.tokenCreator.create(r1, r2, r3, r4);     // Catch: AuthenticationException -> L6
        return;
    L9:
        throw new RuntimeException("Required Parameter: 'callback' is required to use the created token and handle errors");     // Catch: AuthenticationException -> L6
    L11:
        throw new RuntimeException("Required Parameter: 'card' is required to create a token");     // Catch: AuthenticationException -> L6
    L6:
        e = move-exception;
        r4.onError(e);
    }

    public void setDefaultPublishableKey(String r1) {
        validateKey(r1);
        this.defaultPublishableKey = r1;
    }
}
