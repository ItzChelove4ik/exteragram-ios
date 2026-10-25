.class public final synthetic Landroidx/car/app/AppManager$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/HostCall;


# instance fields
.field public final synthetic f$0:Landroidx/car/app/media/OpenMicrophoneRequest;


# direct methods
.method public synthetic constructor <init>(Landroidx/car/app/media/OpenMicrophoneRequest;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/car/app/AppManager$$ExternalSyntheticLambda0;->f$0:Landroidx/car/app/media/OpenMicrophoneRequest;

    return-void
.end method


# virtual methods
.method public final dispatch(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 0
    iget-object p0, p0, Landroidx/car/app/AppManager$$ExternalSyntheticLambda0;->f$0:Landroidx/car/app/media/OpenMicrophoneRequest;

    check-cast p1, Landroidx/car/app/IAppHost;

    invoke-static {p0, p1}, Landroidx/car/app/AppManager;->$r8$lambda$-eRdGEweqivF9t3_swK7uQbdXz4(Landroidx/car/app/media/OpenMicrophoneRequest;Landroidx/car/app/IAppHost;)Landroidx/car/app/media/OpenMicrophoneResponse;

    move-result-object p0

    return-object p0
.end method
