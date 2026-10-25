package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.media.MediaRouter;
import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public abstract class MediaRouteProvider {
    static final int MSG_DELIVER_DESCRIPTOR_CHANGED = 1;
    static final int MSG_DELIVER_DISCOVERY_REQUEST_CHANGED = 2;
    private Callback mCallback;
    private final Context mContext;
    private MediaRouteProviderDescriptor mDescriptor;
    private MediaRouteDiscoveryRequest mDiscoveryRequest;
    private final ProviderHandler mHandler;
    private final ProviderMetadata mMetadata;
    private boolean mPendingDescriptorChange;
    private boolean mPendingDiscoveryRequestChange;

    public static abstract class Callback {
        public Callback() {
        }

        public void onDescriptorChanged(MediaRouteProvider r1, MediaRouteProviderDescriptor r2) {
        }
    }

    public static abstract class DynamicGroupRouteController extends RouteController {
        Executor mExecutor;
        OnDynamicRoutesChangedListener mListener;
        private final Object mLock;
        MediaRouteDescriptor mPendingGroupRoute;
        Collection<DynamicRouteDescriptor> mPendingRoutes;

        public static final class DynamicRouteDescriptor {
            static final String KEY_IS_GROUPABLE = "isGroupable";
            static final String KEY_IS_TRANSFERABLE = "isTransferable";
            static final String KEY_IS_UNSELECTABLE = "isUnselectable";
            static final String KEY_MEDIA_ROUTE_DESCRIPTOR = "mrDescriptor";
            static final String KEY_SELECTION_STATE = "selectionState";
            public static final int NOT_IN_GROUP = 4;
            public static final int SELECTED = 3;
            public static final int SELECTING = 2;
            public static final int UNSELECTED = 1;
            public static final int UNSELECTING = 0;
            Bundle mBundle;
            final boolean mIsGroupable;
            final boolean mIsTransferable;
            final boolean mIsUnselectable;
            final MediaRouteDescriptor mMediaRouteDescriptor;
            final int mSelectionState;

            public static final class Builder {
                private boolean mIsGroupable;
                private boolean mIsTransferable;
                private boolean mIsUnselectable;
                private final MediaRouteDescriptor mRouteDescriptor;
                private int mSelectionState;

                public Builder(MediaRouteDescriptor r2) {
                    this.mSelectionState = 1;
                    this.mIsUnselectable = false;
                    this.mIsGroupable = false;
                    this.mIsTransferable = false;
                    if (r2 == null) goto L6;
                    this.mRouteDescriptor = r2;
                    return;
                L6:
                    f$$ExternalSyntheticBUOutline2.m("descriptor must not be null");
                    throw null;
                }

                public Builder(DynamicRouteDescriptor r2) {
                    this.mSelectionState = 1;
                    this.mIsUnselectable = false;
                    this.mIsGroupable = false;
                    this.mIsTransferable = false;
                    if (r2 == null) goto L6;
                    this.mRouteDescriptor = r2.getRouteDescriptor();
                    this.mSelectionState = r2.getSelectionState();
                    this.mIsUnselectable = r2.isUnselectable();
                    this.mIsGroupable = r2.isGroupable();
                    this.mIsTransferable = r2.isTransferable();
                    return;
                L6:
                    f$$ExternalSyntheticBUOutline2.m("dynamicRouteDescriptor must not be null");
                    throw null;
                }

                public DynamicRouteDescriptor build() {
                    return new DynamicRouteDescriptor(this.mRouteDescriptor, this.mSelectionState, this.mIsUnselectable, this.mIsGroupable, this.mIsTransferable);
                }

                public Builder setIsGroupable(boolean r1) {
                    this.mIsGroupable = r1;
                    return this;
                }

                public Builder setIsTransferable(boolean r1) {
                    this.mIsTransferable = r1;
                    return this;
                }

                public Builder setIsUnselectable(boolean r1) {
                    this.mIsUnselectable = r1;
                    return this;
                }

                public Builder setSelectionState(int r1) {
                    this.mSelectionState = r1;
                    return this;
                }
            }

            @Retention(RetentionPolicy.SOURCE)
            public @interface SelectionState {
            }

            public DynamicRouteDescriptor(MediaRouteDescriptor r1, int r2, boolean r3, boolean r4, boolean r5) {
                this.mMediaRouteDescriptor = r1;
                this.mSelectionState = r2;
                this.mIsUnselectable = r3;
                this.mIsGroupable = r4;
                this.mIsTransferable = r5;
            }

            public static DynamicRouteDescriptor fromBundle(Bundle r7) {
                if (r7 != null) goto L6;
                return null;
            L6:
                return new DynamicRouteDescriptor(MediaRouteDescriptor.fromBundle(r7.getBundle(KEY_MEDIA_ROUTE_DESCRIPTOR)), r7.getInt(KEY_SELECTION_STATE, 1), r7.getBoolean(KEY_IS_UNSELECTABLE, false), r7.getBoolean(KEY_IS_GROUPABLE, false), r7.getBoolean(KEY_IS_TRANSFERABLE, false));
            }

            public MediaRouteDescriptor getRouteDescriptor() {
                return this.mMediaRouteDescriptor;
            }

            public int getSelectionState() {
                return this.mSelectionState;
            }

            public boolean isGroupable() {
                return this.mIsGroupable;
            }

            public boolean isTransferable() {
                return this.mIsTransferable;
            }

            public boolean isUnselectable() {
                return this.mIsUnselectable;
            }

            public Bundle toBundle() {
                if (this.mBundle != null) goto L6;
                Bundle r0 = new Bundle();
                this.mBundle = r0;
                r0.putBundle(KEY_MEDIA_ROUTE_DESCRIPTOR, this.mMediaRouteDescriptor.asBundle());
                this.mBundle.putInt(KEY_SELECTION_STATE, this.mSelectionState);
                this.mBundle.putBoolean(KEY_IS_UNSELECTABLE, this.mIsUnselectable);
                this.mBundle.putBoolean(KEY_IS_GROUPABLE, this.mIsGroupable);
                this.mBundle.putBoolean(KEY_IS_TRANSFERABLE, this.mIsTransferable);
            L6:
                return this.mBundle;
            }
        }

        public interface OnDynamicRoutesChangedListener {
            void onRoutesChanged(DynamicGroupRouteController r1, MediaRouteDescriptor r2, Collection<DynamicRouteDescriptor> r3);
        }

        public static /* synthetic */ void $r8$lambda$CXMYDRMDED4ym91bYunTbpwOul8(DynamicGroupRouteController r1, OnDynamicRoutesChangedListener r2, Collection r3) {
            r1.getClass();
            r2.onRoutesChanged(r1, null, r3);
        }

        public static /* synthetic */ void $r8$lambda$GkvzHmFi7J0hPJ2hkNMUQ5T3hOI(DynamicGroupRouteController r0, OnDynamicRoutesChangedListener r1, MediaRouteDescriptor r2, Collection r3) {
            r0.getClass();
            r1.onRoutesChanged(r0, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$jW55csm3zybsNPVmkVatnJA7rDI(DynamicGroupRouteController r0, OnDynamicRoutesChangedListener r1, MediaRouteDescriptor r2, Collection r3) {
            r0.getClass();
            r1.onRoutesChanged(r0, r2, r3);
        }

        public DynamicGroupRouteController() {
            this.mLock = new Object();
        }

        public String getGroupableSelectionTitle() {
            return null;
        }

        public String getTransferableSectionTitle() {
            return null;
        }

        public final void notifyDynamicRoutesChanged(MediaRouteDescriptor r5, Collection<DynamicRouteDescriptor> r6) {
            if (r5 == null) goto L18;
            if (r6 == null) goto L16;
            Object r0 = this.mLock;
            monitor-enter(r0);
            Executor r1 = this.mExecutor;     // Catch: Throwable -> L9
            if (r1 == null) goto L11;
            r1.execute(new MediaRouteProvider$DynamicGroupRouteController$$ExternalSyntheticLambda0(this, this.mListener, r5, r6));     // Catch: Throwable -> L9
        L12:
            monitor-exit(r0);     // Catch: Throwable -> L9
            return;
        L11:
            this.mPendingGroupRoute = r5;     // Catch: Throwable -> L9
            this.mPendingRoutes = new ArrayList(r6);     // Catch: Throwable -> L9
        L9:
            th = move-exception;
            throw th;
        L16:
            f$$ExternalSyntheticBUOutline2.m("dynamicRoutes must not be null");
            return;
        L18:
            f$$ExternalSyntheticBUOutline2.m("groupRoute must not be null");
        }

        @Deprecated
        public final void notifyDynamicRoutesChanged(Collection<DynamicRouteDescriptor> r5) {
            if (r5 == null) goto L15;
            Object r0 = this.mLock;
            monitor-enter(r0);
            Executor r1 = this.mExecutor;     // Catch: Throwable -> L8
            if (r1 == null) goto L10;
            r1.execute(new MediaRouteProvider$DynamicGroupRouteController$$ExternalSyntheticLambda2(this, this.mListener, r5));     // Catch: Throwable -> L8
        L11:
            monitor-exit(r0);     // Catch: Throwable -> L8
            return;
        L10:
            this.mPendingRoutes = new ArrayList(r5);     // Catch: Throwable -> L8
        L8:
            th = move-exception;
            throw th;
        L15:
            f$$ExternalSyntheticBUOutline2.m("routes must not be null");
        }

        public abstract void onAddMemberRoute(String r1);

        public abstract void onRemoveMemberRoute(String r1);

        public abstract void onUpdateMemberRoutes(List<String> r1);

        public void setOnDynamicRoutesChangedListener(Executor r5, OnDynamicRoutesChangedListener r6) {
            Object r0 = this.mLock;
            monitor-enter(r0);
            if (r5 == null) goto L18;
            if (r6 == null) goto L16;
            this.mExecutor = r5;     // Catch: Throwable -> L11
            this.mListener = r6;     // Catch: Throwable -> L11
            Collection<DynamicRouteDescriptor> r52 = this.mPendingRoutes;     // Catch: Throwable -> L11
            if (r52 != null) goto L9;
        L13:
            monitor-exit(r0);     // Catch: Throwable -> L11
            return;
        L9:
            if (r52.isEmpty() == true) goto L13;
            MediaRouteDescriptor r53 = this.mPendingGroupRoute;     // Catch: Throwable -> L11
            Collection<DynamicRouteDescriptor> r1 = this.mPendingRoutes;     // Catch: Throwable -> L11
            this.mPendingGroupRoute = null;     // Catch: Throwable -> L11
            this.mPendingRoutes = null;     // Catch: Throwable -> L11
            this.mExecutor.execute(new MediaRouteProvider$DynamicGroupRouteController$$ExternalSyntheticLambda1(this, r6, r53, r1));     // Catch: Throwable -> L11
            goto L13
        L16:
            throw new NullPointerException("Listener shouldn't be null");     // Catch: Throwable -> L11
        L18:
            throw new NullPointerException("Executor shouldn't be null");     // Catch: Throwable -> L11
        L11:
            th = move-exception;
            throw th;
        }
    }

    public final class ProviderHandler extends Handler {
        final /* synthetic */ MediaRouteProvider this$0;

        public ProviderHandler(MediaRouteProvider r1) {
            this.this$0 = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message r2) {
            int r22 = r2.what;
            if (r22 != 1) goto L5;
            this.this$0.deliverDescriptorChanged();
            return;
        L5:
            if (r22 == 2) goto L7;
            return;
        L7:
            this.this$0.deliverDiscoveryRequestChanged();
        }
    }

    public static final class ProviderMetadata {
        private final ComponentName mComponentName;

        public ProviderMetadata(ComponentName r1) {
            if (r1 == null) goto L6;
            this.mComponentName = r1;
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("componentName must not be null");
            throw null;
        }

        public ComponentName getComponentName() {
            return this.mComponentName;
        }

        public String getPackageName() {
            return this.mComponentName.getPackageName();
        }

        public String toString() {
            return "ProviderMetadata{ componentName=" + this.mComponentName.flattenToShortString() + " }";
        }
    }

    public static abstract class RouteController {
        public RouteController() {
        }

        public boolean onControlRequest(Intent r1, MediaRouter.ControlRequestCallback r2) {
            return false;
        }

        public void onRelease() {
        }

        public void onSelect() {
        }

        public void onSetVolume(int r1) {
        }

        @Deprecated
        public void onUnselect() {
        }

        public void onUnselect(int r1) {
            onUnselect();
        }

        public void onUpdateVolume(int r1) {
        }
    }

    public static final class RouteControllerOptions {
        static final RouteControllerOptions EMPTY = null;
        private static final String EMPTY_CLIENT_PACKAGE_NAME = "";
        private static final String KEY_CLIENT_PACKAGE_NAME = "clientPackageName";
        private static final String KEY_CONTROL_HINTS = "controlHints";
        private final Bundle mBundle;

        public static final class Builder {
            private final Bundle mBundle;

            public Builder() {
                this.mBundle = new Bundle();
            }

            public Builder(RouteControllerOptions r2) {
                this.mBundle = new Bundle();
                setControlHints(r2.getControlHints());
                setClientPackageName(r2.getClientPackageName());
            }

            public RouteControllerOptions build() {
                return new RouteControllerOptions(this.mBundle);
            }

            public Builder setClientPackageName(String r3) {
                this.mBundle.putString(RouteControllerOptions.KEY_CLIENT_PACKAGE_NAME, r3);
                return this;
            }

            public Builder setControlHints(Bundle r3) {
                this.mBundle.putParcelable(RouteControllerOptions.KEY_CONTROL_HINTS, r3);
                return this;
            }
        }

        static {
            EMPTY = new Builder().build();
        }

        public RouteControllerOptions(Bundle r2) {
            this.mBundle = new Bundle(r2);
        }

        public Bundle asBundle() {
            return this.mBundle;
        }

        public String getClientPackageName() {
            return this.mBundle.getString(KEY_CLIENT_PACKAGE_NAME, "");
        }

        public Bundle getControlHints() {
            Bundle r1 = (Bundle) this.mBundle.getParcelable(KEY_CONTROL_HINTS);
            if (r1 == null) goto L6;
            return r1;
        L6:
            return Bundle.EMPTY;
        }
    }

    public MediaRouteProvider(Context r2) {
        this(r2, null);
    }

    public MediaRouteProvider(Context r3, ProviderMetadata r4) {
        this.mHandler = new ProviderHandler(this);
        if (r3 == null) goto L10;
        this.mContext = r3;
        if (r4 != null) goto L8;
        this.mMetadata = new ProviderMetadata(new ComponentName(r3, getClass()));
        return;
    L8:
        this.mMetadata = r4;
        return;
    L10:
        f$$ExternalSyntheticBUOutline1.m("context must not be null");
        throw null;
    }

    public final void deliverDescriptorChanged() {
        this.mPendingDescriptorChange = false;
        Callback r0 = this.mCallback;
        if (r0 == null) goto L6;
        r0.onDescriptorChanged(this, this.mDescriptor);
        return;
    }

    public final void deliverDiscoveryRequestChanged() {
        this.mPendingDiscoveryRequestChange = false;
        onDiscoveryRequestChanged(this.mDiscoveryRequest);
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final MediaRouteProviderDescriptor getDescriptor() {
        return this.mDescriptor;
    }

    public final MediaRouteDiscoveryRequest getDiscoveryRequest() {
        return this.mDiscoveryRequest;
    }

    public final Handler getHandler() {
        return this.mHandler;
    }

    public final ProviderMetadata getMetadata() {
        return this.mMetadata;
    }

    public DynamicGroupRouteController onCreateDynamicGroupRouteController(String r1) {
        if (r1 == null) goto L5;
        return null;
    L5:
        f$$ExternalSyntheticBUOutline1.m("initialMemberRouteId cannot be null.");
        return null;
    }

    public DynamicGroupRouteController onCreateDynamicGroupRouteController(String r1, RouteControllerOptions r2) {
        return onCreateDynamicGroupRouteController(r1);
    }

    public RouteController onCreateRouteController(String r1) {
        if (r1 == null) goto L5;
        return null;
    L5:
        f$$ExternalSyntheticBUOutline1.m("routeId cannot be null");
        return null;
    }

    public RouteController onCreateRouteController(String r1, RouteControllerOptions r2) {
        return onCreateRouteController(r1);
    }

    public RouteController onCreateRouteController(String r2, String r3) {
        if (r2 == null) goto L9;
        if (r3 != null) goto L6;
        f$$ExternalSyntheticBUOutline1.m("routeGroupId cannot be null");
        return null;
    L6:
        return onCreateRouteController(r2, RouteControllerOptions.EMPTY);
    L9:
        f$$ExternalSyntheticBUOutline1.m("routeId cannot be null");
        return null;
    }

    public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest r1) {
    }

    public final void setCallback(Callback r1) {
        MediaRouter.checkCallingThread();
        this.mCallback = r1;
    }

    public final void setDescriptor(MediaRouteProviderDescriptor r2) {
        MediaRouter.checkCallingThread();
        if (this.mDescriptor == r2) goto L8;
        this.mDescriptor = r2;
        if (this.mPendingDescriptorChange == true) goto L9;
        this.mPendingDescriptorChange = true;
        this.mHandler.sendEmptyMessage(1);
        return;
    L9:
        return;
    }

    public final void setDiscoveryRequest(MediaRouteDiscoveryRequest r2) {
        MediaRouter.checkCallingThread();
        if (ObjectsCompat.equals(this.mDiscoveryRequest, r2) == false) goto L5;
        return;
    L5:
        setDiscoveryRequestInternal(r2);
    }

    public final void setDiscoveryRequestInternal(MediaRouteDiscoveryRequest r1) {
        this.mDiscoveryRequest = r1;
        if (this.mPendingDiscoveryRequestChange == true) goto L6;
        this.mPendingDiscoveryRequestChange = true;
        this.mHandler.sendEmptyMessage(2);
        return;
    }
}
