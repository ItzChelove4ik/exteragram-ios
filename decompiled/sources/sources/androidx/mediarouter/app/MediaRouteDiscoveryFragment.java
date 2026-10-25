package androidx.mediarouter.app;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class MediaRouteDiscoveryFragment extends Fragment {
    private static final String ARGUMENT_SELECTOR = "selector";
    private MediaRouter.Callback mCallback;
    private MediaRouter mRouter;
    private MediaRouteSelector mSelector;

    /* renamed from: androidx.mediarouter.app.MediaRouteDiscoveryFragment$1, reason: invalid class name */
    public class AnonymousClass1 extends MediaRouter.Callback {
        final /* synthetic */ MediaRouteDiscoveryFragment this$0;

        public AnonymousClass1(MediaRouteDiscoveryFragment r1) {
            this.this$0 = r1;
        }
    }

    public MediaRouteDiscoveryFragment() {
    }

    private void ensureRouteSelector() {
        if (this.mSelector != null) goto L11;
        Bundle r0 = getArguments();
        if (r0 == null) goto L8;
        this.mSelector = MediaRouteSelector.fromBundle(r0.getBundle("selector"));
    L8:
        if (this.mSelector != null) goto L12;
        this.mSelector = MediaRouteSelector.EMPTY;
        return;
    L12:
        return;
    }

    private void ensureRouter() {
        if (this.mRouter != null) goto L6;
        this.mRouter = MediaRouter.getInstance(getContext());
        return;
    }

    public MediaRouter getMediaRouter() {
        ensureRouter();
        return this.mRouter;
    }

    public MediaRouteSelector getRouteSelector() {
        ensureRouteSelector();
        return this.mSelector;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle r3) {
        super.onCreate(r3);
        ensureRouteSelector();
        ensureRouter();
        MediaRouter.Callback r32 = onCreateCallback();
        this.mCallback = r32;
        if (r32 == null) goto L6;
        this.mRouter.addCallback(this.mSelector, r32, 0);
        return;
    }

    public MediaRouter.Callback onCreateCallback() {
        return new AnonymousClass1(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        MediaRouter.Callback r0 = this.mCallback;
        if (r0 == null) goto L5;
        this.mRouter.removeCallback(r0);
    L5:
        super.onDestroy();
    }

    public int onPrepareCallbackFlags() {
        return 4;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        MediaRouter.Callback r0 = this.mCallback;
        if (r0 == null) goto L6;
        this.mRouter.addCallback(this.mSelector, r0, onPrepareCallbackFlags());
        return;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        MediaRouter.Callback r0 = this.mCallback;
        if (r0 == null) goto L5;
        this.mRouter.addCallback(this.mSelector, r0, 0);
    L5:
        super.onStop();
    }

    public void setRouteSelector(MediaRouteSelector r3) {
        if (r3 == null) goto L12;
        ensureRouteSelector();
        if (this.mSelector.equals(r3) == true) goto L14;
        this.mSelector = r3;
        Bundle r0 = getArguments();
        if (r0 != null) goto L8;
        r0 = new Bundle();
    L8:
        r0.putBundle("selector", r3.asBundle());
        setArguments(r0);
        MediaRouter.Callback r32 = this.mCallback;
        if (r32 == null) goto L15;
        this.mRouter.removeCallback(r32);
        this.mRouter.addCallback(this.mSelector, this.mCallback, onPrepareCallbackFlags());
        return;
    L15:
        return;
    L14:
        return;
    L12:
        f$$ExternalSyntheticBUOutline1.m("selector must not be null");
    }
}
