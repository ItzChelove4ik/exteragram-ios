.class public final Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/IMapsProvider$IMap;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/GoogleMapsProvider;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "GoogleMapImpl"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$GoogleMarker;,
        Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$GoogleCircle;
    }
.end annotation


# instance fields
.field private googleMap:Lcom/google/android/gms/maps/GoogleMap;

.field private implToAbsCircleMap:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Lcom/google/android/gms/maps/model/Circle;",
            "Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$GoogleCircle;",
            ">;"
        }
    .end annotation
.end field

.field private implToAbsMarkerMap:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Lcom/google/android/gms/maps/model/Marker;",
            "Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$GoogleMarker;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static synthetic $r8$lambda$r9-kkoqpW97OxjoEF5_g6CEGB9s(Lorg/telegram/messenger/IMapsProvider$OnCameraMoveStartedListener;I)V
    .locals 1

    const/4 v0, 0x2

    if-eq p1, v0, :cond_0

    const/4 v0, 0x3

    if-eq p1, v0, :cond_0

    const/4 v0, 0x1

    .line 157
    :cond_0
    invoke-interface {p0, v0}, Lorg/telegram/messenger/IMapsProvider$OnCameraMoveStartedListener;->onCameraMoveStarted(I)V

    return-void
.end method

.method public static synthetic $r8$lambda$zkoWPOSjj37cOg4icYE3UQPcyKY(Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;Lorg/telegram/messenger/IMapsProvider$OnMarkerClickListener;Lcom/google/android/gms/maps/model/Marker;)Z
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->lambda$setOnMarkerClickListener$1(Lorg/telegram/messenger/IMapsProvider$OnMarkerClickListener;Lcom/google/android/gms/maps/model/Marker;)Z

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetimplToAbsCircleMap(Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;)Ljava/util/Map;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->implToAbsCircleMap:Ljava/util/Map;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetimplToAbsMarkerMap(Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;)Ljava/util/Map;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->implToAbsMarkerMap:Ljava/util/Map;

    return-object p0
.end method

.method private constructor <init>(Lcom/google/android/gms/maps/GoogleMap;)V
    .locals 1

    .line 101
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 98
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->implToAbsMarkerMap:Ljava/util/Map;

    .line 99
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->implToAbsCircleMap:Ljava/util/Map;

    .line 102
    iput-object p1, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    return-void
.end method

.method public synthetic constructor <init>(Lcom/google/android/gms/maps/GoogleMap;Lorg/telegram/messenger/GoogleMapsProvider-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;-><init>(Lcom/google/android/gms/maps/GoogleMap;)V

    return-void
.end method

.method private synthetic lambda$setOnMarkerClickListener$1(Lorg/telegram/messenger/IMapsProvider$OnMarkerClickListener;Lcom/google/android/gms/maps/model/Marker;)Z
    .locals 2

    .line 216
    iget-object v0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->implToAbsMarkerMap:Ljava/util/Map;

    invoke-interface {v0, p2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$GoogleMarker;

    if-nez v0, :cond_0

    .line 218
    new-instance v0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$GoogleMarker;

    const/4 v1, 0x0

    invoke-direct {v0, p0, p2, v1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$GoogleMarker;-><init>(Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;Lcom/google/android/gms/maps/model/Marker;Lorg/telegram/messenger/GoogleMapsProvider-IA;)V

    .line 219
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->implToAbsMarkerMap:Ljava/util/Map;

    invoke-interface {p0, p2, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 221
    :cond_0
    invoke-interface {p1, v0}, Lorg/telegram/messenger/IMapsProvider$OnMarkerClickListener;->onClick(Lorg/telegram/messenger/IMapsProvider$IMarker;)Z

    move-result p0

    return p0
.end method


# virtual methods
.method public addCircle(Lorg/telegram/messenger/IMapsProvider$ICircleOptions;)Lorg/telegram/messenger/IMapsProvider$ICircle;
    .locals 2

    .line 202
    iget-object v0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    check-cast p1, Lorg/telegram/messenger/GoogleMapsProvider$GoogleCircleOptions;

    invoke-static {p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleCircleOptions;->-$$Nest$fgetcircleOptions(Lorg/telegram/messenger/GoogleMapsProvider$GoogleCircleOptions;)Lcom/google/android/gms/maps/model/CircleOptions;

    move-result-object p1

    invoke-virtual {v0, p1}, Lcom/google/android/gms/maps/GoogleMap;->addCircle(Lcom/google/android/gms/maps/model/CircleOptions;)Lcom/google/android/gms/maps/model/Circle;

    move-result-object p1

    .line 203
    new-instance v0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$GoogleCircle;

    const/4 v1, 0x0

    invoke-direct {v0, p0, p1, v1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$GoogleCircle;-><init>(Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;Lcom/google/android/gms/maps/model/Circle;Lorg/telegram/messenger/GoogleMapsProvider-IA;)V

    .line 204
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->implToAbsCircleMap:Ljava/util/Map;

    invoke-interface {p0, p1, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-object v0
.end method

.method public addMarker(Lorg/telegram/messenger/IMapsProvider$IMarkerOptions;)Lorg/telegram/messenger/IMapsProvider$IMarker;
    .locals 2

    .line 194
    iget-object v0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    check-cast p1, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMarkerOptions;

    invoke-static {p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMarkerOptions;->-$$Nest$fgetmarkerOptions(Lorg/telegram/messenger/GoogleMapsProvider$GoogleMarkerOptions;)Lcom/google/android/gms/maps/model/MarkerOptions;

    move-result-object p1

    invoke-virtual {v0, p1}, Lcom/google/android/gms/maps/GoogleMap;->addMarker(Lcom/google/android/gms/maps/model/MarkerOptions;)Lcom/google/android/gms/maps/model/Marker;

    move-result-object p1

    .line 195
    new-instance v0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$GoogleMarker;

    const/4 v1, 0x0

    invoke-direct {v0, p0, p1, v1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$GoogleMarker;-><init>(Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;Lcom/google/android/gms/maps/model/Marker;Lorg/telegram/messenger/GoogleMapsProvider-IA;)V

    .line 196
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->implToAbsMarkerMap:Ljava/util/Map;

    invoke-interface {p0, p1, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-object v0
.end method

.method public animateCamera(Lorg/telegram/messenger/IMapsProvider$ICameraUpdate;)V
    .locals 0

    .line 232
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    check-cast p1, Lorg/telegram/messenger/GoogleMapsProvider$GoogleCameraUpdate;

    invoke-static {p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleCameraUpdate;->-$$Nest$fgetcameraUpdate(Lorg/telegram/messenger/GoogleMapsProvider$GoogleCameraUpdate;)Lcom/google/android/gms/maps/CameraUpdate;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/google/android/gms/maps/GoogleMap;->animateCamera(Lcom/google/android/gms/maps/CameraUpdate;)V

    return-void
.end method

.method public animateCamera(Lorg/telegram/messenger/IMapsProvider$ICameraUpdate;ILorg/telegram/messenger/IMapsProvider$ICancelableCallback;)V
    .locals 2

    .line 252
    iget-object v0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    check-cast p1, Lorg/telegram/messenger/GoogleMapsProvider$GoogleCameraUpdate;

    invoke-static {p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleCameraUpdate;->-$$Nest$fgetcameraUpdate(Lorg/telegram/messenger/GoogleMapsProvider$GoogleCameraUpdate;)Lcom/google/android/gms/maps/CameraUpdate;

    move-result-object p1

    if-nez p3, :cond_0

    const/4 p0, 0x0

    goto :goto_0

    :cond_0
    new-instance v1, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$2;

    invoke-direct {v1, p0, p3}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$2;-><init>(Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;Lorg/telegram/messenger/IMapsProvider$ICancelableCallback;)V

    move-object p0, v1

    :goto_0
    invoke-virtual {v0, p1, p2, p0}, Lcom/google/android/gms/maps/GoogleMap;->animateCamera(Lcom/google/android/gms/maps/CameraUpdate;ILcom/google/android/gms/maps/GoogleMap$CancelableCallback;)V

    return-void
.end method

.method public animateCamera(Lorg/telegram/messenger/IMapsProvider$ICameraUpdate;Lorg/telegram/messenger/IMapsProvider$ICancelableCallback;)V
    .locals 2

    .line 237
    iget-object v0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    check-cast p1, Lorg/telegram/messenger/GoogleMapsProvider$GoogleCameraUpdate;

    invoke-static {p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleCameraUpdate;->-$$Nest$fgetcameraUpdate(Lorg/telegram/messenger/GoogleMapsProvider$GoogleCameraUpdate;)Lcom/google/android/gms/maps/CameraUpdate;

    move-result-object p1

    if-nez p2, :cond_0

    const/4 p0, 0x0

    goto :goto_0

    :cond_0
    new-instance v1, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$1;

    invoke-direct {v1, p0, p2}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$1;-><init>(Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;Lorg/telegram/messenger/IMapsProvider$ICancelableCallback;)V

    move-object p0, v1

    :goto_0
    invoke-virtual {v0, p1, p0}, Lcom/google/android/gms/maps/GoogleMap;->animateCamera(Lcom/google/android/gms/maps/CameraUpdate;Lcom/google/android/gms/maps/GoogleMap$CancelableCallback;)V

    return-void
.end method

.method public getCameraPosition()Lorg/telegram/messenger/IMapsProvider$CameraPosition;
    .locals 7

    .line 168
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    invoke-virtual {p0}, Lcom/google/android/gms/maps/GoogleMap;->getCameraPosition()Lcom/google/android/gms/maps/model/CameraPosition;

    move-result-object p0

    .line 169
    new-instance v0, Lorg/telegram/messenger/IMapsProvider$CameraPosition;

    new-instance v1, Lorg/telegram/messenger/IMapsProvider$LatLng;

    iget-object v2, p0, Lcom/google/android/gms/maps/model/CameraPosition;->target:Lcom/google/android/gms/maps/model/LatLng;

    iget-wide v3, v2, Lcom/google/android/gms/maps/model/LatLng;->latitude:D

    iget-wide v5, v2, Lcom/google/android/gms/maps/model/LatLng;->longitude:D

    invoke-direct {v1, v3, v4, v5, v6}, Lorg/telegram/messenger/IMapsProvider$LatLng;-><init>(DD)V

    iget p0, p0, Lcom/google/android/gms/maps/model/CameraPosition;->zoom:F

    invoke-direct {v0, v1, p0}, Lorg/telegram/messenger/IMapsProvider$CameraPosition;-><init>(Lorg/telegram/messenger/IMapsProvider$LatLng;F)V

    return-object v0
.end method

.method public getMaxZoomLevel()F
    .locals 0

    .line 122
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    invoke-virtual {p0}, Lcom/google/android/gms/maps/GoogleMap;->getMaxZoomLevel()F

    move-result p0

    return p0
.end method

.method public getMinZoomLevel()F
    .locals 0

    .line 127
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    invoke-virtual {p0}, Lcom/google/android/gms/maps/GoogleMap;->getMinZoomLevel()F

    move-result p0

    return p0
.end method

.method public getProjection()Lorg/telegram/messenger/IMapsProvider$IProjection;
    .locals 2

    .line 179
    new-instance v0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleProjection;

    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    invoke-virtual {p0}, Lcom/google/android/gms/maps/GoogleMap;->getProjection()Lcom/google/android/gms/maps/Projection;

    move-result-object p0

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleProjection;-><init>(Lcom/google/android/gms/maps/Projection;Lorg/telegram/messenger/GoogleMapsProvider-IA;)V

    return-object v0
.end method

.method public getUiSettings()Lorg/telegram/messenger/IMapsProvider$IUISettings;
    .locals 2

    .line 138
    new-instance v0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleUISettings;

    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    invoke-virtual {p0}, Lcom/google/android/gms/maps/GoogleMap;->getUiSettings()Lcom/google/android/gms/maps/UiSettings;

    move-result-object p0

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleUISettings;-><init>(Lcom/google/android/gms/maps/UiSettings;Lorg/telegram/messenger/GoogleMapsProvider-IA;)V

    return-object v0
.end method

.method public moveCamera(Lorg/telegram/messenger/IMapsProvider$ICameraUpdate;)V
    .locals 0

    .line 267
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    check-cast p1, Lorg/telegram/messenger/GoogleMapsProvider$GoogleCameraUpdate;

    invoke-static {p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleCameraUpdate;->-$$Nest$fgetcameraUpdate(Lorg/telegram/messenger/GoogleMapsProvider$GoogleCameraUpdate;)Lcom/google/android/gms/maps/CameraUpdate;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/google/android/gms/maps/GoogleMap;->moveCamera(Lcom/google/android/gms/maps/CameraUpdate;)V

    return-void
.end method

.method public setMapStyle(Lorg/telegram/messenger/IMapsProvider$IMapStyleOptions;)V
    .locals 0

    .line 189
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    if-nez p1, :cond_0

    const/4 p1, 0x0

    goto :goto_0

    :cond_0
    check-cast p1, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapStyleOptions;

    invoke-static {p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapStyleOptions;->-$$Nest$fgetmapStyleOptions(Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapStyleOptions;)Lcom/google/android/gms/maps/model/MapStyleOptions;

    move-result-object p1

    :goto_0
    invoke-virtual {p0, p1}, Lcom/google/android/gms/maps/GoogleMap;->setMapStyle(Lcom/google/android/gms/maps/model/MapStyleOptions;)Z

    return-void
.end method

.method public setMapType(I)V
    .locals 2

    const/4 v0, 0x1

    if-eqz p1, :cond_2

    const/4 v1, 0x2

    if-eq p1, v0, :cond_1

    if-eq p1, v1, :cond_0

    return-void

    .line 115
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    const/4 p1, 0x4

    invoke-virtual {p0, p1}, Lcom/google/android/gms/maps/GoogleMap;->setMapType(I)V

    return-void

    .line 112
    :cond_1
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    invoke-virtual {p0, v1}, Lcom/google/android/gms/maps/GoogleMap;->setMapType(I)V

    return-void

    .line 109
    :cond_2
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    invoke-virtual {p0, v0}, Lcom/google/android/gms/maps/GoogleMap;->setMapType(I)V

    return-void
.end method

.method public setMyLocationEnabled(Z)V
    .locals 0
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "MissingPermission"
        }
    .end annotation

    .line 133
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/maps/GoogleMap;->setMyLocationEnabled(Z)V

    return-void
.end method

.method public setOnCameraIdleListener(Ljava/lang/Runnable;)V
    .locals 1

    .line 163
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda2;

    invoke-direct {v0, p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda2;-><init>(Ljava/lang/Runnable;)V

    invoke-virtual {p0, v0}, Lcom/google/android/gms/maps/GoogleMap;->setOnCameraIdleListener(Lcom/google/android/gms/maps/GoogleMap$OnCameraIdleListener;)V

    return-void
.end method

.method public setOnCameraMoveListener(Ljava/lang/Runnable;)V
    .locals 1

    .line 227
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda1;

    invoke-direct {v0, p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda1;-><init>(Ljava/lang/Runnable;)V

    invoke-virtual {p0, v0}, Lcom/google/android/gms/maps/GoogleMap;->setOnCameraMoveListener(Lcom/google/android/gms/maps/GoogleMap$OnCameraMoveListener;)V

    return-void
.end method

.method public setOnCameraMoveStartedListener(Lorg/telegram/messenger/IMapsProvider$OnCameraMoveStartedListener;)V
    .locals 1

    .line 143
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    new-instance v0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda5;

    invoke-direct {v0, p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda5;-><init>(Lorg/telegram/messenger/IMapsProvider$OnCameraMoveStartedListener;)V

    invoke-virtual {p0, v0}, Lcom/google/android/gms/maps/GoogleMap;->setOnCameraMoveStartedListener(Lcom/google/android/gms/maps/GoogleMap$OnCameraMoveStartedListener;)V

    return-void
.end method

.method public setOnMapLoadedCallback(Ljava/lang/Runnable;)V
    .locals 1

    .line 174
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda4;

    invoke-direct {v0, p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda4;-><init>(Ljava/lang/Runnable;)V

    invoke-virtual {p0, v0}, Lcom/google/android/gms/maps/GoogleMap;->setOnMapLoadedCallback(Lcom/google/android/gms/maps/GoogleMap$OnMapLoadedCallback;)V

    return-void
.end method

.method public setOnMarkerClickListener(Lorg/telegram/messenger/IMapsProvider$OnMarkerClickListener;)V
    .locals 2

    .line 215
    iget-object v0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    new-instance v1, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda3;

    invoke-direct {v1, p0, p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda3;-><init>(Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;Lorg/telegram/messenger/IMapsProvider$OnMarkerClickListener;)V

    invoke-virtual {v0, v1}, Lcom/google/android/gms/maps/GoogleMap;->setOnMarkerClickListener(Lcom/google/android/gms/maps/GoogleMap$OnMarkerClickListener;)V

    return-void
.end method

.method public setOnMyLocationChangeListener(Landroidx/core/util/Consumer;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroidx/core/util/Consumer<",
            "Landroid/location/Location;",
            ">;)V"
        }
    .end annotation

    .line 210
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda0;

    invoke-direct {v0, p1}, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl$$ExternalSyntheticLambda0;-><init>(Landroidx/core/util/Consumer;)V

    invoke-virtual {p0, v0}, Lcom/google/android/gms/maps/GoogleMap;->setOnMyLocationChangeListener(Lcom/google/android/gms/maps/GoogleMap$OnMyLocationChangeListener;)V

    return-void
.end method

.method public setPadding(IIII)V
    .locals 0

    .line 184
    iget-object p0, p0, Lorg/telegram/messenger/GoogleMapsProvider$GoogleMapImpl;->googleMap:Lcom/google/android/gms/maps/GoogleMap;

    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/google/android/gms/maps/GoogleMap;->setPadding(IIII)V

    return-void
.end method
