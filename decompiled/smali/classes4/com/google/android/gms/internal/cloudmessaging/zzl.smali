.class final synthetic Lcom/google/android/gms/internal/cloudmessaging/zzl;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/common/api/internal/RemoteCall;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/cloudmessaging/zzm;

.field private final synthetic zzb:Lcom/google/android/gms/cloudmessaging/RegisterRequest;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/cloudmessaging/zzm;Lcom/google/android/gms/cloudmessaging/RegisterRequest;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/cloudmessaging/zzl;->zza:Lcom/google/android/gms/internal/cloudmessaging/zzm;

    iput-object p2, p0, Lcom/google/android/gms/internal/cloudmessaging/zzl;->zzb:Lcom/google/android/gms/cloudmessaging/RegisterRequest;

    return-void
.end method


# virtual methods
.method public final synthetic accept(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 3

    iget-object v0, p0, Lcom/google/android/gms/internal/cloudmessaging/zzl;->zza:Lcom/google/android/gms/internal/cloudmessaging/zzm;

    check-cast p2, Lcom/google/android/gms/tasks/TaskCompletionSource;

    check-cast p1, Lcom/google/android/gms/internal/cloudmessaging/zzd;

    .line 1
    new-instance v1, Lcom/google/android/gms/internal/cloudmessaging/zzi;

    invoke-direct {v1, v0, p2}, Lcom/google/android/gms/internal/cloudmessaging/zzi;-><init>(Lcom/google/android/gms/internal/cloudmessaging/zzm;Lcom/google/android/gms/tasks/TaskCompletionSource;)V

    const/4 p2, 0x0

    .line 2
    :try_start_0
    invoke-virtual {v0}, Lcom/google/android/gms/common/api/GoogleApi;->getApplicationContext()Landroid/content/Context;

    move-result-object v2

    invoke-static {v2}, Lcom/google/android/gms/common/wrappers/Wrappers;->packageManager(Landroid/content/Context;)Lcom/google/android/gms/common/wrappers/PackageManagerWrapper;

    move-result-object v2

    .line 3
    invoke-virtual {v0}, Lcom/google/android/gms/common/api/GoogleApi;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0, p2}, Lcom/google/android/gms/common/wrappers/PackageManagerWrapper;->getPackageInfo(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;

    move-result-object v0

    iget p2, v0, Landroid/content/pm/PackageInfo;->versionCode:I
    :try_end_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    iget-object p0, p0, Lcom/google/android/gms/internal/cloudmessaging/zzl;->zzb:Lcom/google/android/gms/cloudmessaging/RegisterRequest;

    .line 4
    invoke-virtual {p0, p2}, Lcom/google/android/gms/cloudmessaging/RegisterRequest;->zza(I)V

    .line 5
    invoke-virtual {p1}, Lcom/google/android/gms/common/internal/BaseGmsClient;->getService()Landroid/os/IInterface;

    move-result-object p2

    check-cast p2, Lcom/google/android/gms/internal/cloudmessaging/zze;

    .line 6
    invoke-virtual {p1}, Lcom/google/android/gms/common/internal/BaseGmsClient;->getContext()Landroid/content/Context;

    move-result-object p1

    .line 7
    invoke-static {p1}, Lcom/google/android/gms/internal/cloudmessaging/zzq;->zza(Landroid/content/Context;)Lcom/google/android/gms/common/api/ApiMetadata;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/gms/common/api/ApiMetadata;->toBuilder()Lcom/google/android/gms/common/api/ApiMetadata$Builder;

    move-result-object p1

    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Lcom/google/android/gms/common/api/ApiMetadata$Builder;->setCallbackSupportEnabled(Z)Lcom/google/android/gms/common/api/ApiMetadata$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/gms/common/api/ApiMetadata$Builder;->build()Lcom/google/android/gms/common/api/ApiMetadata;

    move-result-object p1

    .line 8
    invoke-virtual {p2, v1, p0, p1}, Lcom/google/android/gms/internal/cloudmessaging/zze;->zzc(Lcom/google/android/gms/internal/cloudmessaging/zzg;Lcom/google/android/gms/cloudmessaging/RegisterRequest;Lcom/google/android/gms/common/api/ApiMetadata;)V

    return-void
.end method
