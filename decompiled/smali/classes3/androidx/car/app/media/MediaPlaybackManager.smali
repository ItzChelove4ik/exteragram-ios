.class public Landroidx/car/app/media/MediaPlaybackManager;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/managers/Manager;


# instance fields
.field private final mHostDispatcher:Landroidx/car/app/HostDispatcher;


# direct methods
.method public constructor <init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)V
    .locals 0

    .line 95
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 96
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 97
    invoke-static {p2}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p2, p0, Landroidx/car/app/media/MediaPlaybackManager;->mHostDispatcher:Landroidx/car/app/HostDispatcher;

    .line 98
    new-instance p1, Landroidx/car/app/media/MediaPlaybackManager$1;

    invoke-direct {p1, p0, p3}, Landroidx/car/app/media/MediaPlaybackManager$1;-><init>(Landroidx/car/app/media/MediaPlaybackManager;Landroidx/lifecycle/Lifecycle;)V

    .line 104
    invoke-virtual {p3, p1}, Landroidx/lifecycle/Lifecycle;->addObserver(Landroidx/lifecycle/LifecycleObserver;)V

    return-void
.end method

.method public static create(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/media/MediaPlaybackManager;
    .locals 1

    .line 85
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 86
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 87
    invoke-static {p2}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 89
    new-instance v0, Landroidx/car/app/media/MediaPlaybackManager;

    invoke-direct {v0, p0, p1, p2}, Landroidx/car/app/media/MediaPlaybackManager;-><init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)V

    return-object v0
.end method
