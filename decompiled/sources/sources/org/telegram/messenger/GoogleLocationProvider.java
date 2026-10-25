package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import androidx.core.util.Consumer;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.Task;
import org.telegram.messenger.ILocationServiceProvider;
import org.telegram.messenger.PushListenerController;

@SuppressLint({"MissingPermission"})
/* loaded from: classes5.dex */
public class GoogleLocationProvider implements ILocationServiceProvider {
    private FusedLocationProviderClient locationProviderClient;
    private SettingsClient settingsClient;

    /* renamed from: org.telegram.messenger.GoogleLocationProvider$1, reason: invalid class name */
    public class AnonymousClass1 extends LocationCallback {
        final /* synthetic */ GoogleLocationProvider this$0;
        final /* synthetic */ ILocationServiceProvider.ILocationListener val$locationListener;

        public AnonymousClass1(GoogleLocationProvider r1, ILocationServiceProvider.ILocationListener r2) {
            this.this$0 = r1;
            this.val$locationListener = r2;
        }

        @Override // com.google.android.gms.location.LocationCallback
        public void onLocationResult(LocationResult r1) {
            this.val$locationListener.onLocationChanged(r1.getLastLocation());
        }
    }

    /* renamed from: org.telegram.messenger.GoogleLocationProvider$2, reason: invalid class name */
    public class AnonymousClass2 extends LocationCallback {
        final /* synthetic */ GoogleLocationProvider this$0;
        final /* synthetic */ ILocationServiceProvider.ILocationListener val$locationListener;

        public AnonymousClass2(GoogleLocationProvider r1, ILocationServiceProvider.ILocationListener r2) {
            this.this$0 = r1;
            this.val$locationListener = r2;
        }

        @Override // com.google.android.gms.location.LocationCallback
        public void onLocationResult(LocationResult r1) {
            this.val$locationListener.onLocationChanged(r1.getLastLocation());
        }
    }

    /* renamed from: org.telegram.messenger.GoogleLocationProvider$3, reason: invalid class name */
    public class AnonymousClass3 implements GoogleApiClient.ConnectionCallbacks {
        final /* synthetic */ GoogleLocationProvider this$0;
        final /* synthetic */ ILocationServiceProvider.IAPIConnectionCallbacks val$connectionCallbacks;

        public AnonymousClass3(GoogleLocationProvider r1, ILocationServiceProvider.IAPIConnectionCallbacks r2) {
            this.this$0 = r1;
            this.val$connectionCallbacks = r2;
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public void onConnected(Bundle r1) {
            this.val$connectionCallbacks.onConnected(r1);
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public void onConnectionSuspended(int r1) {
            this.val$connectionCallbacks.onConnectionSuspended(r1);
        }
    }

    public static final class GoogleApiClientImpl implements ILocationServiceProvider.IMapApiClient {
        private GoogleApiClient apiClient;

        private GoogleApiClientImpl(GoogleApiClient r1) {
            this.apiClient = r1;
        }

        public /* synthetic */ GoogleApiClientImpl(GoogleApiClient r1, GoogleLocationProviderIA r2) {
            this(r1);
        }

        @Override // org.telegram.messenger.ILocationServiceProvider.IMapApiClient
        public void connect() {
            this.apiClient.connect();
        }

        @Override // org.telegram.messenger.ILocationServiceProvider.IMapApiClient
        public void disconnect() {
            this.apiClient.disconnect();
        }
    }

    public static final class GoogleLocationRequest implements ILocationServiceProvider.ILocationRequest {
        private LocationRequest request;

        /* renamed from: -$$Nest$fgetrequest, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LocationRequest m4113$$Nest$fgetrequest(GoogleLocationRequest r0) {
            return r0.request;
        }

        private GoogleLocationRequest(LocationRequest r1) {
            this.request = r1;
        }

        public /* synthetic */ GoogleLocationRequest(LocationRequest r1, GoogleLocationProviderIA r2) {
            this(r1);
        }

        @Override // org.telegram.messenger.ILocationServiceProvider.ILocationRequest
        public void setFastestInterval(long r1) {
            this.request.setFastestInterval(r1);
        }

        @Override // org.telegram.messenger.ILocationServiceProvider.ILocationRequest
        public void setInterval(long r1) {
            this.request.setInterval(r1);
        }

        @Override // org.telegram.messenger.ILocationServiceProvider.ILocationRequest
        public void setPriority(int r2) {
            if (r2 != 1) goto L5;
            int r22 = 102;
        L12:
            this.request.setPriority(r22);
            return;
        L5:
            if (r2 != 2) goto L7;
            r22 = 104;
            goto L12
        L7:
            if (r2 == 3) goto L9;
            r22 = 100;
            goto L12
        L9:
            r22 = 105;
            goto L12
        }
    }

    /* renamed from: $r8$lambda$-vkutDO8gzoLBU7Z7WyI5UKFw2I, reason: not valid java name */
    public static /* synthetic */ void m4111$r8$lambda$vkutDO8gzoLBU7Z7WyI5UKFw2I(ILocationServiceProvider.IAPIOnConnectionFailedListener r0, ConnectionResult r1) {
        r0.onConnectionFailed();
    }

    /* renamed from: $r8$lambda$pg-imAzDDNnR94RGDhgpM2y3jLE, reason: not valid java name */
    public static /* synthetic */ void m4112$r8$lambda$pgimAzDDNnR94RGDhgpM2y3jLE(Consumer r1, Task r2) {
        if (r2.getException() == null) goto L5;
        return;
    L5:
        r1.accept((Location) r2.getResult());
    }

    public static /* synthetic */ void $r8$lambda$sOg18UH3CaGba6Zw0P6W33BoWok(Consumer r1, Task r2) {
        r2.getResult(ApiException.class);     // Catch: ApiException -> L4
        r1.accept(0);     // Catch: ApiException -> L4
        return;
    L4:
        e = move-exception;
        int r22 = e.getStatusCode();
        if (r22 != 6) goto L8;
        r1.accept(1);
        return;
    L8:
        if (r22 != 8502) goto L16;
        r1.accept(2);
        return;
    }

    public GoogleLocationProvider() {
    }

    @Override // org.telegram.messenger.ILocationServiceProvider
    public void checkLocationSettings(ILocationServiceProvider.ILocationRequest r2, Consumer<Integer> r3) {
        LocationSettingsRequest.Builder r22 = new LocationSettingsRequest.Builder().addLocationRequest(GoogleLocationRequest.m4113$$Nest$fgetrequest((GoogleLocationRequest) r2));
        this.settingsClient.checkLocationSettings(r22.build()).addOnCompleteListener(new GoogleLocationProvider$$ExternalSyntheticLambda2(r3));
    }

    @Override // org.telegram.messenger.ILocationServiceProvider
    public boolean checkServices() {
        return PushListenerController.GooglePushListenerServiceProvider.INSTANCE.hasServices();
    }

    @Override // org.telegram.messenger.ILocationServiceProvider
    public void getLastLocation(Consumer<Location> r2) {
        this.locationProviderClient.getLastLocation().addOnCompleteListener(new GoogleLocationProvider$$ExternalSyntheticLambda1(r2));
    }

    @Override // org.telegram.messenger.ILocationServiceProvider
    public void init(Context r2) {
        this.locationProviderClient = LocationServices.getFusedLocationProviderClient(r2);
        this.settingsClient = LocationServices.getSettingsClient(r2);
    }

    @Override // org.telegram.messenger.ILocationServiceProvider
    public ILocationServiceProvider.ILocationRequest onCreateLocationRequest() {
        return new GoogleLocationRequest(LocationRequest.create(), null);
    }

    @Override // org.telegram.messenger.ILocationServiceProvider
    public ILocationServiceProvider.IMapApiClient onCreateLocationServicesAPI(Context r3, ILocationServiceProvider.IAPIConnectionCallbacks r4, ILocationServiceProvider.IAPIOnConnectionFailedListener r5) {
        return new GoogleApiClientImpl(new GoogleApiClient.Builder(ApplicationLoader.applicationContext).addApi(LocationServices.API).addConnectionCallbacks(new AnonymousClass3(this, r4)).addOnConnectionFailedListener(new GoogleLocationProvider$$ExternalSyntheticLambda0(r5)).build(), null);
    }

    @Override // org.telegram.messenger.ILocationServiceProvider
    public void removeLocationUpdates(ILocationServiceProvider.ILocationListener r3) {
        this.locationProviderClient.removeLocationUpdates(new AnonymousClass2(this, r3));
    }

    @Override // org.telegram.messenger.ILocationServiceProvider
    public void requestLocationUpdates(ILocationServiceProvider.ILocationRequest r3, ILocationServiceProvider.ILocationListener r4) {
        this.locationProviderClient.requestLocationUpdates(GoogleLocationRequest.m4113$$Nest$fgetrequest((GoogleLocationRequest) r3), new AnonymousClass1(this, r4), Looper.getMainLooper());
    }
}
