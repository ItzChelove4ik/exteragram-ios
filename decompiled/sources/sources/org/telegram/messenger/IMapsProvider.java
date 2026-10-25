package org.telegram.messenger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.location.Location;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Keep;
import androidx.core.util.Consumer;
import java.util.List;

@Keep
/* loaded from: classes.dex */
public interface IMapsProvider {
    public static final int MAP_TYPE_HYBRID = 2;
    public static final int MAP_TYPE_NORMAL = 0;
    public static final int MAP_TYPE_SATELLITE = 1;

    /* loaded from: classes5.dex */
    public static final class CameraPosition {
        public final LatLng target;
        public final float zoom;

        public CameraPosition(LatLng r1, float r2) {
            this.target = r1;
            this.zoom = r2;
        }
    }

    /* loaded from: classes5.dex */
    public interface ICallableMethod<R, A> {
        R call(A r1);
    }

    /* loaded from: classes5.dex */
    public interface ICameraUpdate {
    }

    /* loaded from: classes5.dex */
    public interface ICancelableCallback {
        void onCancel();

        void onFinish();
    }

    /* loaded from: classes5.dex */
    public interface ICircle {
        double getRadius();

        void remove();

        void setCenter(LatLng r1);

        void setFillColor(int r1);

        void setRadius(double r1);

        void setStrokeColor(int r1);
    }

    /* loaded from: classes5.dex */
    public interface ICircleOptions {
        ICircleOptions center(LatLng r1);

        ICircleOptions fillColor(int r1);

        ICircleOptions radius(double r1);

        ICircleOptions strokeColor(int r1);

        ICircleOptions strokePattern(List<PatternItem> r1);

        ICircleOptions strokeWidth(int r1);
    }

    /* loaded from: classes5.dex */
    public interface ILatLngBounds {
        LatLng getCenter();
    }

    /* loaded from: classes5.dex */
    public interface ILatLngBoundsBuilder {
        ILatLngBounds build();

        ILatLngBoundsBuilder include(LatLng r1);
    }

    /* loaded from: classes5.dex */
    public interface IMap {
        ICircle addCircle(ICircleOptions r1);

        IMarker addMarker(IMarkerOptions r1);

        void animateCamera(ICameraUpdate r1);

        void animateCamera(ICameraUpdate r1, int r2, ICancelableCallback r3);

        void animateCamera(ICameraUpdate r1, ICancelableCallback r2);

        CameraPosition getCameraPosition();

        float getMaxZoomLevel();

        float getMinZoomLevel();

        IProjection getProjection();

        IUISettings getUiSettings();

        void moveCamera(ICameraUpdate r1);

        void setMapStyle(IMapStyleOptions r1);

        void setMapType(int r1);

        void setMyLocationEnabled(boolean r1);

        void setOnCameraIdleListener(Runnable r1);

        void setOnCameraMoveListener(Runnable r1);

        void setOnCameraMoveStartedListener(OnCameraMoveStartedListener r1);

        void setOnMapLoadedCallback(Runnable r1);

        void setOnMarkerClickListener(OnMarkerClickListener r1);

        void setOnMyLocationChangeListener(Consumer<Location> r1);

        void setPadding(int r1, int r2, int r3, int r4);
    }

    /* loaded from: classes5.dex */
    public interface IMapStyleOptions {
    }

    /* loaded from: classes5.dex */
    public interface IMapView {
        default GLSurfaceView getGlSurfaceView() {
            return null;
        }

        void getMapAsync(Consumer<IMap> r1);

        View getView();

        void onCreate(Bundle r1);

        void onDestroy();

        void onLowMemory();

        void onPause();

        void onResume();

        void setOnDispatchTouchEventInterceptor(ITouchInterceptor r1);

        void setOnInterceptTouchEventInterceptor(ITouchInterceptor r1);

        void setOnLayoutListener(Runnable r1);
    }

    /* loaded from: classes5.dex */
    public interface IMarker {
        LatLng getPosition();

        Object getTag();

        void remove();

        void setIcon(int r1);

        void setIcon(Bitmap r1);

        void setPosition(LatLng r1);

        void setRotation(int r1);

        void setTag(Object r1);
    }

    /* loaded from: classes5.dex */
    public interface IMarkerOptions {
        IMarkerOptions anchor(float r1, float r2);

        IMarkerOptions flat(boolean r1);

        IMarkerOptions icon(int r1);

        IMarkerOptions icon(Bitmap r1);

        IMarkerOptions position(LatLng r1);

        IMarkerOptions snippet(String r1);

        IMarkerOptions title(String r1);
    }

    /* loaded from: classes5.dex */
    public interface IProjection {
        Point toScreenLocation(LatLng r1);
    }

    /* loaded from: classes5.dex */
    public interface ITouchInterceptor {
        boolean onInterceptTouchEvent(MotionEvent r1, ICallableMethod<Boolean, MotionEvent> r2);
    }

    /* loaded from: classes5.dex */
    public interface IUISettings {
        void setCompassEnabled(boolean r1);

        void setMyLocationButtonEnabled(boolean r1);

        void setZoomControlsEnabled(boolean r1);
    }

    /* loaded from: classes5.dex */
    public static final class LatLng {
        public final double latitude;
        public final double longitude;

        public LatLng(double r1, double r3) {
            this.latitude = r1;
            this.longitude = r3;
        }
    }

    /* loaded from: classes5.dex */
    public interface OnCameraMoveStartedListener {
        public static final int REASON_API_ANIMATION = 2;
        public static final int REASON_DEVELOPER_ANIMATION = 3;
        public static final int REASON_GESTURE = 1;

        void onCameraMoveStarted(int r1);
    }

    /* loaded from: classes5.dex */
    public interface OnMarkerClickListener {
        boolean onClick(IMarker r1);
    }

    /* loaded from: classes5.dex */
    public static class PatternItem {

        public static final class Dash extends PatternItem {
            public final int length;

            public Dash(int r1) {
                this.length = r1;
            }
        }

        public static final class Gap extends PatternItem {
            public final int length;

            public Gap(int r1) {
                this.length = r1;
            }
        }

        public PatternItem() {
        }
    }

    int getInstallMapsString();

    String getMapsAppPackageName();

    void initializeMaps(Context r1);

    IMapStyleOptions loadRawResourceStyle(Context r1, int r2);

    ICameraUpdate newCameraUpdateLatLng(LatLng r1);

    ICameraUpdate newCameraUpdateLatLngBounds(ILatLngBounds r1, int r2);

    ICameraUpdate newCameraUpdateLatLngZoom(LatLng r1, float r2);

    ICircleOptions onCreateCircleOptions();

    ILatLngBoundsBuilder onCreateLatLngBoundsBuilder();

    IMapView onCreateMapView(Context r1);

    IMarkerOptions onCreateMarkerOptions();
}
