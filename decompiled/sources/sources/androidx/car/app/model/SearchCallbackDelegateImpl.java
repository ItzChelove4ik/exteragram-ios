package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.ISearchCallback;
import androidx.car.app.model.SearchTemplate;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class SearchCallbackDelegateImpl implements SearchCallbackDelegate {
    private final ISearchCallback mStubCallback;

    public static class SearchCallbackStub extends ISearchCallback.Stub {
        private final SearchTemplate.SearchCallback mCallback;

        public static /* synthetic */ Object $r8$lambda$RqmlWNPg55YHdmSVQQAEDPV549Q(SearchCallbackStub r0, String r1) {
            r0.getClass();
            throw null;
        }

        public static /* synthetic */ Object $r8$lambda$dx39ga_t255O8OhkV41BCLGktgU(SearchCallbackStub r0, String r1) {
            r0.getClass();
            throw null;
        }

        public SearchCallbackStub(SearchTemplate.SearchCallback r1) {
        }

        @Override // androidx.car.app.model.ISearchCallback
        public void onSearchSubmitted(String r2, IOnDoneCallback r3) {
            RemoteUtils.dispatchCallFromHost(r3, "onSearchSubmitted", new SearchCallbackDelegateImpl$SearchCallbackStub$$ExternalSyntheticLambda0(this, r2));
        }

        @Override // androidx.car.app.model.ISearchCallback
        public void onSearchTextChanged(String r2, IOnDoneCallback r3) {
            RemoteUtils.dispatchCallFromHost(r3, "onSearchTextChanged", new SearchCallbackDelegateImpl$SearchCallbackStub$$ExternalSyntheticLambda1(this, r2));
        }
    }

    private SearchCallbackDelegateImpl() {
        this.mStubCallback = null;
    }

    private SearchCallbackDelegateImpl(SearchTemplate.SearchCallback r2) {
        this.mStubCallback = new SearchCallbackStub(r2);
    }

    @SuppressLint({"ExecutorRegistration"})
    public static SearchCallbackDelegate create(SearchTemplate.SearchCallback r1) {
        return new SearchCallbackDelegateImpl(r1);
    }

    public void sendSearchSubmitted(String r1, OnDoneCallback r2) {
        ISearchCallback r0 = this.mStubCallback;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onSearchSubmitted(r1, RemoteUtils.createOnDoneCallbackStub(r2));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    public void sendSearchTextChanged(String r1, OnDoneCallback r2) {
        ISearchCallback r0 = this.mStubCallback;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onSearchTextChanged(r1, RemoteUtils.createOnDoneCallbackStub(r2));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }
}
