.class public final synthetic Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/utils/RemoteUtils$RemoteCall;


# instance fields
.field public final synthetic f$0:Landroidx/car/app/HostDispatcher;

.field public final synthetic f$1:Ljava/lang/String;

.field public final synthetic f$2:Ljava/lang/String;

.field public final synthetic f$3:Landroidx/car/app/HostCall;


# direct methods
.method public synthetic constructor <init>(Landroidx/car/app/HostDispatcher;Ljava/lang/String;Ljava/lang/String;Landroidx/car/app/HostCall;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda0;->f$0:Landroidx/car/app/HostDispatcher;

    iput-object p2, p0, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda0;->f$1:Ljava/lang/String;

    iput-object p3, p0, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda0;->f$2:Ljava/lang/String;

    iput-object p4, p0, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda0;->f$3:Landroidx/car/app/HostCall;

    return-void
.end method


# virtual methods
.method public final call()Ljava/lang/Object;
    .locals 3

    .line 0
    iget-object v0, p0, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda0;->f$0:Landroidx/car/app/HostDispatcher;

    iget-object v1, p0, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda0;->f$1:Ljava/lang/String;

    iget-object v2, p0, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda0;->f$2:Ljava/lang/String;

    iget-object p0, p0, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda0;->f$3:Landroidx/car/app/HostCall;

    invoke-static {v0, v1, v2, p0}, Landroidx/car/app/HostDispatcher;->$r8$lambda$s4xJ5LW05J7W13ehpZbJ4VKDjTE(Landroidx/car/app/HostDispatcher;Ljava/lang/String;Ljava/lang/String;Landroidx/car/app/HostCall;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
