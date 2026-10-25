package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.location.Location;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.util.Consumer;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.telegram.messenger.IMapsProvider;

/* loaded from: classes5.dex */
public class GoogleMapsProvider implements IMapsProvider {

    public static final class GoogleCameraUpdate implements IMapsProvider.ICameraUpdate {
        private CameraUpdate cameraUpdate;

        /* renamed from: -$$Nest$fgetcameraUpdate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CameraUpdate m4114$$Nest$fgetcameraUpdate(GoogleCameraUpdate r0) {
            return r0.cameraUpdate;
        }

        private GoogleCameraUpdate(CameraUpdate r1) {
            this.cameraUpdate = r1;
        }

        public /* synthetic */ GoogleCameraUpdate(CameraUpdate r1, GoogleMapsProviderIA r2) {
            this(r1);
        }
    }

    public static final class GoogleCircleOptions implements IMapsProvider.ICircleOptions {
        private CircleOptions circleOptions;

        /* renamed from: -$$Nest$fgetcircleOptions, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CircleOptions m4115$$Nest$fgetcircleOptions(GoogleCircleOptions r0) {
            return r0.circleOptions;
        }

        private GoogleCircleOptions() {
            this.circleOptions = new CircleOptions();
        }

        public /* synthetic */ GoogleCircleOptions(GoogleMapsProviderIA r1) {
            this();
        }

        @Override // org.telegram.messenger.IMapsProvider.ICircleOptions
        public IMapsProvider.ICircleOptions center(IMapsProvider.LatLng r7) {
            this.circleOptions.center(new LatLng(r7.latitude, r7.longitude));
            return this;
        }

        @Override // org.telegram.messenger.IMapsProvider.ICircleOptions
        public IMapsProvider.ICircleOptions fillColor(int r2) {
            this.circleOptions.fillColor(r2);
            return this;
        }

        @Override // org.telegram.messenger.IMapsProvider.ICircleOptions
        public IMapsProvider.ICircleOptions radius(double r2) {
            this.circleOptions.radius(r2);
            return this;
        }

        @Override // org.telegram.messenger.IMapsProvider.ICircleOptions
        public IMapsProvider.ICircleOptions strokeColor(int r2) {
            this.circleOptions.strokeColor(r2);
            return this;
        }

        @Override // org.telegram.messenger.IMapsProvider.ICircleOptions
        public IMapsProvider.ICircleOptions strokePattern(List<IMapsProvider.PatternItem> r4) {
            ArrayList r0 = new ArrayList();
            Iterator<IMapsProvider.PatternItem> r42 = r4.iterator();
        L4:
            if (r42.hasNext() == false) goto L11;
            IMapsProvider.PatternItem r1 = r42.next();
            if ((r1 instanceof IMapsProvider.PatternItem.Gap) == true) goto L7;
            if ((r1 instanceof IMapsProvider.PatternItem.Dash) == false) goto L4;
            r0.add(new Dash(((IMapsProvider.PatternItem.Dash) r1).length));
            goto L4
        L7:
            r0.add(new Gap(((IMapsProvider.PatternItem.Gap) r1).length));
            goto L4
        L11:
            this.circleOptions.strokePattern(r0);
            return this;
        }

        @Override // org.telegram.messenger.IMapsProvider.ICircleOptions
        public IMapsProvider.ICircleOptions strokeWidth(int r2) {
            this.circleOptions.strokeWidth(r2);
            return this;
        }
    }

    public static final class GoogleLatLngBounds implements IMapsProvider.ILatLngBounds {
        private LatLngBounds bounds;

        /* renamed from: -$$Nest$fgetbounds, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LatLngBounds m4116$$Nest$fgetbounds(GoogleLatLngBounds r0) {
            return r0.bounds;
        }

        private GoogleLatLngBounds(LatLngBounds r1) {
            this.bounds = r1;
        }

        public /* synthetic */ GoogleLatLngBounds(LatLngBounds r1, GoogleMapsProviderIA r2) {
            this(r1);
        }

        @Override // org.telegram.messenger.IMapsProvider.ILatLngBounds
        public IMapsProvider.LatLng getCenter() {
            LatLng r5 = this.bounds.getCenter();
            return new IMapsProvider.LatLng(r5.latitude, r5.longitude);
        }
    }

    public static final class GoogleLatLngBoundsBuilder implements IMapsProvider.ILatLngBoundsBuilder {
        private LatLngBounds.Builder builder;

        private GoogleLatLngBoundsBuilder() {
            this.builder = new LatLngBounds.Builder();
        }

        public /* synthetic */ GoogleLatLngBoundsBuilder(GoogleMapsProviderIA r1) {
            this();
        }

        @Override // org.telegram.messenger.IMapsProvider.ILatLngBoundsBuilder
        public IMapsProvider.ILatLngBounds build() {
            return new GoogleLatLngBounds(this.builder.build(), null);
        }

        @Override // org.telegram.messenger.IMapsProvider.ILatLngBoundsBuilder
        public IMapsProvider.ILatLngBoundsBuilder include(IMapsProvider.LatLng r7) {
            this.builder.include(new LatLng(r7.latitude, r7.longitude));
            return this;
        }
    }

    public static final class GoogleMapImpl implements IMapsProvider.IMap {
        private GoogleMap googleMap;
        private Map<Circle, GoogleCircle> implToAbsCircleMap;
        private Map<Marker, GoogleMarker> implToAbsMarkerMap;

        /* renamed from: org.telegram.messenger.GoogleMapsProvider$GoogleMapImpl$1, reason: invalid class name */
        public class AnonymousClass1 implements GoogleMap.CancelableCallback {
            final /* synthetic */ GoogleMapImpl this$0;
            final /* synthetic */ IMapsProvider.ICancelableCallback val$callback;

            public AnonymousClass1(GoogleMapImpl r1, IMapsProvider.ICancelableCallback r2) {
                this.this$0 = r1;
                this.val$callback = r2;
            }

            @Override // com.google.android.gms.maps.GoogleMap.CancelableCallback
            public void onCancel() {
                this.val$callback.onCancel();
            }

            @Override // com.google.android.gms.maps.GoogleMap.CancelableCallback
            public void onFinish() {
                this.val$callback.onFinish();
            }
        }

        /* renamed from: org.telegram.messenger.GoogleMapsProvider$GoogleMapImpl$2, reason: invalid class name */
        public class AnonymousClass2 implements GoogleMap.CancelableCallback {
            final /* synthetic */ GoogleMapImpl this$0;
            final /* synthetic */ IMapsProvider.ICancelableCallback val$callback;

            public AnonymousClass2(GoogleMapImpl r1, IMapsProvider.ICancelableCallback r2) {
                this.this$0 = r1;
                this.val$callback = r2;
            }

            @Override // com.google.android.gms.maps.GoogleMap.CancelableCallback
            public void onCancel() {
                this.val$callback.onCancel();
            }

            @Override // com.google.android.gms.maps.GoogleMap.CancelableCallback
            public void onFinish() {
                this.val$callback.onFinish();
            }
        }

        public final class GoogleCircle implements IMapsProvider.ICircle {
            private Circle circle;
            final /* synthetic */ GoogleMapImpl this$0;

            private GoogleCircle(GoogleMapImpl r1, Circle r2) {
                this.this$0 = r1;
                this.circle = r2;
            }

            public /* synthetic */ GoogleCircle(GoogleMapImpl r1, Circle r2, GoogleMapsProviderIA r3) {
                this(r1, r2);
            }

            @Override // org.telegram.messenger.IMapsProvider.ICircle
            public double getRadius() {
                return this.circle.getRadius();
            }

            @Override // org.telegram.messenger.IMapsProvider.ICircle
            public void remove() {
                this.circle.remove();
                GoogleMapImpl.m4118$$Nest$fgetimplToAbsCircleMap(this.this$0).remove(this.circle);
            }

            @Override // org.telegram.messenger.IMapsProvider.ICircle
            public void setCenter(IMapsProvider.LatLng r6) {
                this.circle.setCenter(new LatLng(r6.latitude, r6.longitude));
            }

            @Override // org.telegram.messenger.IMapsProvider.ICircle
            public void setFillColor(int r1) {
                this.circle.setFillColor(r1);
            }

            @Override // org.telegram.messenger.IMapsProvider.ICircle
            public void setRadius(double r1) {
                this.circle.setRadius(r1);
            }

            @Override // org.telegram.messenger.IMapsProvider.ICircle
            public void setStrokeColor(int r1) {
                this.circle.setStrokeColor(r1);
            }
        }

        public final class GoogleMarker implements IMapsProvider.IMarker {
            private Marker marker;
            final /* synthetic */ GoogleMapImpl this$0;

            private GoogleMarker(GoogleMapImpl r1, Marker r2) {
                this.this$0 = r1;
                this.marker = r2;
            }

            public /* synthetic */ GoogleMarker(GoogleMapImpl r1, Marker r2, GoogleMapsProviderIA r3) {
                this(r1, r2);
            }

            @Override // org.telegram.messenger.IMapsProvider.IMarker
            public IMapsProvider.LatLng getPosition() {
                LatLng r5 = this.marker.getPosition();
                return new IMapsProvider.LatLng(r5.latitude, r5.longitude);
            }

            @Override // org.telegram.messenger.IMapsProvider.IMarker
            public Object getTag() {
                return this.marker.getTag();
            }

            @Override // org.telegram.messenger.IMapsProvider.IMarker
            public void remove() {
                this.marker.remove();
                GoogleMapImpl.m4119$$Nest$fgetimplToAbsMarkerMap(this.this$0).remove(this.marker);
            }

            @Override // org.telegram.messenger.IMapsProvider.IMarker
            public void setIcon(int r1) {
                this.marker.setIcon(BitmapDescriptorFactory.fromResource(r1));
            }

            @Override // org.telegram.messenger.IMapsProvider.IMarker
            public void setIcon(Bitmap r1) {
                this.marker.setIcon(BitmapDescriptorFactory.fromBitmap(r1));
            }

            @Override // org.telegram.messenger.IMapsProvider.IMarker
            public void setPosition(IMapsProvider.LatLng r6) {
                this.marker.setPosition(new LatLng(r6.latitude, r6.longitude));
            }

            @Override // org.telegram.messenger.IMapsProvider.IMarker
            public void setRotation(int r1) {
                this.marker.setRotation(r1);
            }

            @Override // org.telegram.messenger.IMapsProvider.IMarker
            public void setTag(Object r1) {
                this.marker.setTag(r1);
            }
        }

        /* renamed from: $r8$lambda$r9-kkoqpW97OxjoEF5_g6CEGB9s, reason: not valid java name */
        public static /* synthetic */ void m4117$r8$lambda$r9kkoqpW97OxjoEF5_g6CEGB9s(IMapsProvider.OnCameraMoveStartedListener r1, int r2) {
            int r0 = 2;
            if (r2 == 2) goto L7;
            r0 = 3;
            if (r2 == 3) goto L7;
            r0 = 1;
        L7:
            r1.onCameraMoveStarted(r0);
        }

        public static /* synthetic */ boolean $r8$lambda$zkoWPOSjj37cOg4icYE3UQPcyKY(GoogleMapImpl r0, IMapsProvider.OnMarkerClickListener r1, Marker r2) {
            return r0.lambda$setOnMarkerClickListener$1(r1, r2);
        }

        /* renamed from: -$$Nest$fgetimplToAbsCircleMap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Map m4118$$Nest$fgetimplToAbsCircleMap(GoogleMapImpl r0) {
            return r0.implToAbsCircleMap;
        }

        /* renamed from: -$$Nest$fgetimplToAbsMarkerMap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Map m4119$$Nest$fgetimplToAbsMarkerMap(GoogleMapImpl r0) {
            return r0.implToAbsMarkerMap;
        }

        private GoogleMapImpl(GoogleMap r2) {
            this.implToAbsMarkerMap = new HashMap();
            this.implToAbsCircleMap = new HashMap();
            this.googleMap = r2;
        }

        public /* synthetic */ GoogleMapImpl(GoogleMap r1, GoogleMapsProviderIA r2) {
            this(r1);
        }

        private /* synthetic */ boolean lambda$setOnMarkerClickListener$1(IMapsProvider.OnMarkerClickListener r3, Marker r4) {
            GoogleMarker r0 = this.implToAbsMarkerMap.get(r4);
            if (r0 != null) goto L6;
            r0 = new GoogleMarker(this, r4, null);
            this.implToAbsMarkerMap.put(r4, r0);
        L6:
            return r3.onClick(r0);
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public IMapsProvider.ICircle addCircle(IMapsProvider.ICircleOptions r3) {
            Circle r32 = this.googleMap.addCircle(GoogleCircleOptions.m4115$$Nest$fgetcircleOptions((GoogleCircleOptions) r3));
            GoogleCircle r0 = new GoogleCircle(this, r32, null);
            this.implToAbsCircleMap.put(r32, r0);
            return r0;
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public IMapsProvider.IMarker addMarker(IMapsProvider.IMarkerOptions r3) {
            Marker r32 = this.googleMap.addMarker(GoogleMarkerOptions.m4125$$Nest$fgetmarkerOptions((GoogleMarkerOptions) r3));
            GoogleMarker r0 = new GoogleMarker(this, r32, null);
            this.implToAbsMarkerMap.put(r32, r0);
            return r0;
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public void animateCamera(IMapsProvider.ICameraUpdate r1) {
            this.googleMap.animateCamera(GoogleCameraUpdate.m4114$$Nest$fgetcameraUpdate((GoogleCameraUpdate) r1));
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public void animateCamera(IMapsProvider.ICameraUpdate r3, int r4, IMapsProvider.ICancelableCallback r5) {
            GoogleMap r0 = this.googleMap;
            CameraUpdate r32 = GoogleCameraUpdate.m4114$$Nest$fgetcameraUpdate((GoogleCameraUpdate) r3);
            if (r5 != null) goto L5;
            AnonymousClass2 r2 = null;
        L6:
            r0.animateCamera(r32, r4, r2);
            return;
        L5:
            r2 = new AnonymousClass2(this, r5);
            goto L6
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public void animateCamera(IMapsProvider.ICameraUpdate r3, IMapsProvider.ICancelableCallback r4) {
            GoogleMap r0 = this.googleMap;
            CameraUpdate r32 = GoogleCameraUpdate.m4114$$Nest$fgetcameraUpdate((GoogleCameraUpdate) r3);
            if (r4 != null) goto L5;
            AnonymousClass1 r2 = null;
        L6:
            r0.animateCamera(r32, r2);
            return;
        L5:
            r2 = new AnonymousClass1(this, r4);
            goto L6
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public IMapsProvider.CameraPosition getCameraPosition() {
            CameraPosition r7 = this.googleMap.getCameraPosition();
            LatLng r2 = r7.target;
            return new IMapsProvider.CameraPosition(new IMapsProvider.LatLng(r2.latitude, r2.longitude), r7.zoom);
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public float getMaxZoomLevel() {
            return this.googleMap.getMaxZoomLevel();
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public float getMinZoomLevel() {
            return this.googleMap.getMinZoomLevel();
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public IMapsProvider.IProjection getProjection() {
            return new GoogleProjection(this.googleMap.getProjection(), null);
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public IMapsProvider.IUISettings getUiSettings() {
            return new GoogleUISettings(this.googleMap.getUiSettings(), null);
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public void moveCamera(IMapsProvider.ICameraUpdate r1) {
            this.googleMap.moveCamera(GoogleCameraUpdate.m4114$$Nest$fgetcameraUpdate((GoogleCameraUpdate) r1));
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public void setMapStyle(IMapsProvider.IMapStyleOptions r1) {
            GoogleMap r0 = this.googleMap;
            if (r1 != null) goto L5;
            MapStyleOptions r12 = null;
        L6:
            r0.setMapStyle(r12);
            return;
        L5:
            r12 = GoogleMapStyleOptions.m4120$$Nest$fgetmapStyleOptions((GoogleMapStyleOptions) r1);
            goto L6
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public void setMapType(int r3) {
            if (r3 != 0) goto L5;
            this.googleMap.setMapType(1);
            return;
        L5:
            if (r3 == 1) goto L10;
            if (r3 == 2) goto L8;
            return;
        L8:
            this.googleMap.setMapType(4);
            return;
        L10:
            this.googleMap.setMapType(2);
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        @SuppressLint({"MissingPermission"})
        public void setMyLocationEnabled(boolean r1) {
            this.googleMap.setMyLocationEnabled(r1);
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public void setOnCameraIdleListener(Runnable r2) {
            GoogleMap r1 = this.googleMap;
            Objects.requireNonNull(r2);
            r1.setOnCameraIdleListener(new GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda2(r2));
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public void setOnCameraMoveListener(Runnable r2) {
            GoogleMap r1 = this.googleMap;
            Objects.requireNonNull(r2);
            r1.setOnCameraMoveListener(new GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda1(r2));
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public void setOnCameraMoveStartedListener(IMapsProvider.OnCameraMoveStartedListener r2) {
            this.googleMap.setOnCameraMoveStartedListener(new GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda5(r2));
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public void setOnMapLoadedCallback(Runnable r2) {
            GoogleMap r1 = this.googleMap;
            Objects.requireNonNull(r2);
            r1.setOnMapLoadedCallback(new GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda4(r2));
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public void setOnMarkerClickListener(IMapsProvider.OnMarkerClickListener r3) {
            this.googleMap.setOnMarkerClickListener(new GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda3(this, r3));
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public void setOnMyLocationChangeListener(Consumer<Location> r2) {
            GoogleMap r1 = this.googleMap;
            Objects.requireNonNull(r2);
            r1.setOnMyLocationChangeListener(new GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda0(r2));
        }

        @Override // org.telegram.messenger.IMapsProvider.IMap
        public void setPadding(int r1, int r2, int r3, int r4) {
            this.googleMap.setPadding(r1, r2, r3, r4);
        }
    }

    public static final class GoogleMapStyleOptions implements IMapsProvider.IMapStyleOptions {
        private MapStyleOptions mapStyleOptions;

        /* renamed from: -$$Nest$fgetmapStyleOptions, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MapStyleOptions m4120$$Nest$fgetmapStyleOptions(GoogleMapStyleOptions r0) {
            return r0.mapStyleOptions;
        }

        private GoogleMapStyleOptions(MapStyleOptions r1) {
            this.mapStyleOptions = r1;
        }

        public /* synthetic */ GoogleMapStyleOptions(MapStyleOptions r1, GoogleMapsProviderIA r2) {
            this(r1);
        }
    }

    public static final class GoogleMapView implements IMapsProvider.IMapView {
        private IMapsProvider.ITouchInterceptor dispatchInterceptor;
        private GLSurfaceView glSurfaceView;
        private IMapsProvider.ITouchInterceptor interceptInterceptor;
        private MapView mapView;
        private Runnable onLayoutListener;

        /* renamed from: org.telegram.messenger.GoogleMapsProvider$GoogleMapView$1, reason: invalid class name */
        public class AnonymousClass1 extends MapView {
            final /* synthetic */ GoogleMapView this$0;

            public static /* synthetic */ Boolean $r8$lambda$KxdPSHHi4P36JuTFyEeLefcE18Y(AnonymousClass1 r0, MotionEvent r1) {
                return r0.lambda$dispatchTouchEvent$0(r1);
            }

            public static /* synthetic */ Boolean $r8$lambda$Paa4RGv4XFq7lyduGQj9fliNYFc(AnonymousClass1 r0, MotionEvent r1) {
                return r0.lambda$onInterceptTouchEvent$1(r1);
            }

            public AnonymousClass1(GoogleMapView r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            private /* synthetic */ Boolean lambda$dispatchTouchEvent$0(MotionEvent r1) {
                return Boolean.valueOf(super.dispatchTouchEvent(r1));
            }

            private /* synthetic */ Boolean lambda$onInterceptTouchEvent$1(MotionEvent r1) {
                return Boolean.valueOf(super.onInterceptTouchEvent(r1));
            }

            @Override // android.view.ViewGroup, android.view.View
            public boolean dispatchTouchEvent(MotionEvent r3) {
                if (GoogleMapView.m4122$$Nest$fgetdispatchInterceptor(this.this$0) == null) goto L7;
                return GoogleMapView.m4122$$Nest$fgetdispatchInterceptor(this.this$0).onInterceptTouchEvent(r3, new GoogleMapsProvider$GoogleMapView$1$$ExternalSyntheticLambda0(this));
            L7:
                return super.dispatchTouchEvent(r3);
            }

            @Override // android.view.ViewGroup
            public boolean onInterceptTouchEvent(MotionEvent r3) {
                if (GoogleMapView.m4123$$Nest$fgetinterceptInterceptor(this.this$0) == null) goto L7;
                return GoogleMapView.m4123$$Nest$fgetinterceptInterceptor(this.this$0).onInterceptTouchEvent(r3, new GoogleMapsProvider$GoogleMapView$1$$ExternalSyntheticLambda1(this));
            L7:
                return super.onInterceptTouchEvent(r3);
            }

            @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
            public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
                super.onLayout(r1, r2, r3, r4, r5);
                if (GoogleMapView.m4124$$Nest$fgetonLayoutListener(this.this$0) == null) goto L6;
                GoogleMapView.m4124$$Nest$fgetonLayoutListener(this.this$0).run();
                return;
            }
        }

        /* renamed from: $r8$lambda$FM-Zm8X3f-oe7lisub9JfmitZn8, reason: not valid java name */
        public static /* synthetic */ void m4121$r8$lambda$FMZm8X3foe7lisub9JfmitZn8(GoogleMapView r0, Consumer r1, GoogleMap r2) {
            r0.lambda$getMapAsync$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetdispatchInterceptor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ IMapsProvider.ITouchInterceptor m4122$$Nest$fgetdispatchInterceptor(GoogleMapView r0) {
            return r0.dispatchInterceptor;
        }

        /* renamed from: -$$Nest$fgetinterceptInterceptor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ IMapsProvider.ITouchInterceptor m4123$$Nest$fgetinterceptInterceptor(GoogleMapView r0) {
            return r0.interceptInterceptor;
        }

        /* renamed from: -$$Nest$fgetonLayoutListener, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m4124$$Nest$fgetonLayoutListener(GoogleMapView r0) {
            return r0.onLayoutListener;
        }

        private GoogleMapView(Context r2) {
            this.mapView = new AnonymousClass1(this, r2);
        }

        public /* synthetic */ GoogleMapView(Context r1, GoogleMapsProviderIA r2) {
            this(r1);
        }

        private void findGlSurfaceView(View r3) {
            if ((r3 instanceof GLSurfaceView) == false) goto L6;
            this.glSurfaceView = (GLSurfaceView) r3;
        L6:
            if ((r3 instanceof ViewGroup) == false) goto L11;
            ViewGroup r32 = (ViewGroup) r3;
            int r0 = 0;
        L9:
            if (r0 >= r32.getChildCount()) goto L13;
            findGlSurfaceView(r32.getChildAt(r0));
            r0 = r0 + 1;
            goto L9
        L13:
            return;
        }

        private /* synthetic */ void lambda$getMapAsync$0(Consumer r3, GoogleMap r4) {
            r3.accept(new GoogleMapImpl(r4, null));
            findGlSurfaceView(this.mapView);
        }

        @Override // org.telegram.messenger.IMapsProvider.IMapView
        public GLSurfaceView getGlSurfaceView() {
            return this.glSurfaceView;
        }

        @Override // org.telegram.messenger.IMapsProvider.IMapView
        public void getMapAsync(Consumer<IMapsProvider.IMap> r3) {
            this.mapView.getMapAsync(new GoogleMapsProvider$GoogleMapView$$ExternalSyntheticLambda0(this, r3));
        }

        @Override // org.telegram.messenger.IMapsProvider.IMapView
        public View getView() {
            return this.mapView;
        }

        @Override // org.telegram.messenger.IMapsProvider.IMapView
        public void onCreate(Bundle r1) {
            this.mapView.onCreate(r1);
        }

        @Override // org.telegram.messenger.IMapsProvider.IMapView
        public void onDestroy() {
            this.mapView.onDestroy();
        }

        @Override // org.telegram.messenger.IMapsProvider.IMapView
        public void onLowMemory() {
            this.mapView.onLowMemory();
        }

        @Override // org.telegram.messenger.IMapsProvider.IMapView
        public void onPause() {
            this.mapView.onPause();
        }

        @Override // org.telegram.messenger.IMapsProvider.IMapView
        public void onResume() {
            this.mapView.onResume();
        }

        @Override // org.telegram.messenger.IMapsProvider.IMapView
        public void setOnDispatchTouchEventInterceptor(IMapsProvider.ITouchInterceptor r1) {
            this.dispatchInterceptor = r1;
        }

        @Override // org.telegram.messenger.IMapsProvider.IMapView
        public void setOnInterceptTouchEventInterceptor(IMapsProvider.ITouchInterceptor r1) {
            this.interceptInterceptor = r1;
        }

        @Override // org.telegram.messenger.IMapsProvider.IMapView
        public void setOnLayoutListener(Runnable r1) {
            this.onLayoutListener = r1;
        }
    }

    public static final class GoogleMarkerOptions implements IMapsProvider.IMarkerOptions {
        private MarkerOptions markerOptions;

        /* renamed from: -$$Nest$fgetmarkerOptions, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MarkerOptions m4125$$Nest$fgetmarkerOptions(GoogleMarkerOptions r0) {
            return r0.markerOptions;
        }

        private GoogleMarkerOptions() {
            this.markerOptions = new MarkerOptions();
        }

        public /* synthetic */ GoogleMarkerOptions(GoogleMapsProviderIA r1) {
            this();
        }

        @Override // org.telegram.messenger.IMapsProvider.IMarkerOptions
        public IMapsProvider.IMarkerOptions anchor(float r2, float r3) {
            this.markerOptions.anchor(r2, r3);
            return this;
        }

        @Override // org.telegram.messenger.IMapsProvider.IMarkerOptions
        public IMapsProvider.IMarkerOptions flat(boolean r2) {
            this.markerOptions.flat(r2);
            return this;
        }

        @Override // org.telegram.messenger.IMapsProvider.IMarkerOptions
        public IMapsProvider.IMarkerOptions icon(int r2) {
            this.markerOptions.icon(BitmapDescriptorFactory.fromResource(r2));
            return this;
        }

        @Override // org.telegram.messenger.IMapsProvider.IMarkerOptions
        public IMapsProvider.IMarkerOptions icon(Bitmap r2) {
            this.markerOptions.icon(BitmapDescriptorFactory.fromBitmap(r2));
            return this;
        }

        @Override // org.telegram.messenger.IMapsProvider.IMarkerOptions
        public IMapsProvider.IMarkerOptions position(IMapsProvider.LatLng r7) {
            this.markerOptions.position(new LatLng(r7.latitude, r7.longitude));
            return this;
        }

        @Override // org.telegram.messenger.IMapsProvider.IMarkerOptions
        public IMapsProvider.IMarkerOptions snippet(String r2) {
            this.markerOptions.snippet(r2);
            return this;
        }

        @Override // org.telegram.messenger.IMapsProvider.IMarkerOptions
        public IMapsProvider.IMarkerOptions title(String r2) {
            this.markerOptions.title(r2);
            return this;
        }
    }

    public static final class GoogleProjection implements IMapsProvider.IProjection {
        private Projection projection;

        private GoogleProjection(Projection r1) {
            this.projection = r1;
        }

        public /* synthetic */ GoogleProjection(Projection r1, GoogleMapsProviderIA r2) {
            this(r1);
        }

        @Override // org.telegram.messenger.IMapsProvider.IProjection
        public Point toScreenLocation(IMapsProvider.LatLng r6) {
            return this.projection.toScreenLocation(new LatLng(r6.latitude, r6.longitude));
        }
    }

    public static final class GoogleUISettings implements IMapsProvider.IUISettings {
        private UiSettings uiSettings;

        private GoogleUISettings(UiSettings r1) {
            this.uiSettings = r1;
        }

        public /* synthetic */ GoogleUISettings(UiSettings r1, GoogleMapsProviderIA r2) {
            this(r1);
        }

        @Override // org.telegram.messenger.IMapsProvider.IUISettings
        public void setCompassEnabled(boolean r1) {
            this.uiSettings.setCompassEnabled(r1);
        }

        @Override // org.telegram.messenger.IMapsProvider.IUISettings
        public void setMyLocationButtonEnabled(boolean r1) {
            this.uiSettings.setMyLocationButtonEnabled(r1);
        }

        @Override // org.telegram.messenger.IMapsProvider.IUISettings
        public void setZoomControlsEnabled(boolean r1) {
            this.uiSettings.setZoomControlsEnabled(r1);
        }
    }

    public GoogleMapsProvider() {
    }

    @Override // org.telegram.messenger.IMapsProvider
    public int getInstallMapsString() {
        return R.string.InstallGoogleMaps;
    }

    @Override // org.telegram.messenger.IMapsProvider
    public String getMapsAppPackageName() {
        return "com.google.android.apps.maps";
    }

    @Override // org.telegram.messenger.IMapsProvider
    public void initializeMaps(Context r1) {
        MapsInitializer.initialize(r1);
    }

    @Override // org.telegram.messenger.IMapsProvider
    public IMapsProvider.IMapStyleOptions loadRawResourceStyle(Context r1, int r2) {
        return new GoogleMapStyleOptions(MapStyleOptions.loadRawResourceStyle(r1, r2), null);
    }

    @Override // org.telegram.messenger.IMapsProvider
    public IMapsProvider.ICameraUpdate newCameraUpdateLatLng(IMapsProvider.LatLng r6) {
        return new GoogleCameraUpdate(CameraUpdateFactory.newLatLng(new LatLng(r6.latitude, r6.longitude)), null);
    }

    @Override // org.telegram.messenger.IMapsProvider
    public IMapsProvider.ICameraUpdate newCameraUpdateLatLngBounds(IMapsProvider.ILatLngBounds r1, int r2) {
        return new GoogleCameraUpdate(CameraUpdateFactory.newLatLngBounds(GoogleLatLngBounds.m4116$$Nest$fgetbounds((GoogleLatLngBounds) r1), r2), null);
    }

    @Override // org.telegram.messenger.IMapsProvider
    public IMapsProvider.ICameraUpdate newCameraUpdateLatLngZoom(IMapsProvider.LatLng r6, float r7) {
        return new GoogleCameraUpdate(CameraUpdateFactory.newLatLngZoom(new LatLng(r6.latitude, r6.longitude), r7), null);
    }

    @Override // org.telegram.messenger.IMapsProvider
    public IMapsProvider.ICircleOptions onCreateCircleOptions() {
        return new GoogleCircleOptions(null);
    }

    @Override // org.telegram.messenger.IMapsProvider
    public IMapsProvider.ILatLngBoundsBuilder onCreateLatLngBoundsBuilder() {
        return new GoogleLatLngBoundsBuilder(null);
    }

    @Override // org.telegram.messenger.IMapsProvider
    public IMapsProvider.IMapView onCreateMapView(Context r2) {
        return new GoogleMapView(r2, null);
    }

    @Override // org.telegram.messenger.IMapsProvider
    public IMapsProvider.IMarkerOptions onCreateMarkerOptions() {
        return new GoogleMarkerOptions(null);
    }
}
