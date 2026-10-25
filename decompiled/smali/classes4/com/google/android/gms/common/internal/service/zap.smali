.class final synthetic Lcom/google/android/gms/common/internal/service/zap;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/common/api/internal/RemoteCall;


# instance fields
.field private final synthetic zaa:Lcom/google/android/gms/common/internal/zab;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/common/internal/zab;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/common/internal/service/zap;->zaa:Lcom/google/android/gms/common/internal/zab;

    return-void
.end method


# virtual methods
.method public final synthetic accept(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 1

    check-cast p2, Lcom/google/android/gms/tasks/TaskCompletionSource;

    check-cast p1, Lcom/google/android/gms/common/internal/service/zab;

    sget v0, Lcom/google/android/gms/common/internal/service/zaq;->$r8$clinit:I

    .line 1
    invoke-virtual {p1}, Lcom/google/android/gms/common/internal/BaseGmsClient;->getService()Landroid/os/IInterface;

    move-result-object p1

    check-cast p1, Lcom/google/android/gms/common/internal/service/zaj;

    iget-object p0, p0, Lcom/google/android/gms/common/internal/service/zap;->zaa:Lcom/google/android/gms/common/internal/zab;

    invoke-virtual {p1, p0}, Lcom/google/android/gms/common/internal/service/zaj;->zae(Lcom/google/android/gms/common/internal/zab;)V

    const/4 p0, 0x0

    .line 2
    invoke-virtual {p2, p0}, Lcom/google/android/gms/tasks/TaskCompletionSource;->setResult(Ljava/lang/Object;)V

    return-void
.end method
