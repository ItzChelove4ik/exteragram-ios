.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzar;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzl;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzas;


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 1
    const-string v0, "com.google.android.apps.aicore.aidl.IAiCoreServiceProviderCallback"

    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzl;-><init>(Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public final zza(ILandroid/os/Parcel;Landroid/os/Parcel;I)Z
    .locals 0

    const/4 p3, 0x2

    if-eq p1, p3, :cond_1

    const/4 p3, 0x3

    if-eq p1, p3, :cond_0

    const/4 p0, 0x0

    return p0

    .line 1
    :cond_0
    invoke-virtual {p2}, Landroid/os/Parcel;->readInt()I

    move-result p1

    .line 2
    invoke-virtual {p2}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object p3

    .line 3
    invoke-static {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzm;->zzc(Landroid/os/Parcel;)V

    .line 4
    invoke-interface {p0, p1, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzas;->zzc(ILjava/lang/String;)V

    goto :goto_1

    .line 5
    :cond_1
    invoke-virtual {p2}, Landroid/os/Parcel;->readStrongBinder()Landroid/os/IBinder;

    move-result-object p1

    if-nez p1, :cond_2

    const/4 p1, 0x0

    goto :goto_0

    .line 9
    :cond_2
    const-string p3, "com.google.android.apps.aicore.aidl.IAICoreService"

    .line 6
    invoke-interface {p1, p3}, Landroid/os/IBinder;->queryLocalInterface(Ljava/lang/String;)Landroid/os/IInterface;

    move-result-object p3

    instance-of p4, p3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;

    if-eqz p4, :cond_3

    .line 7
    move-object p1, p3

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;

    goto :goto_0

    :cond_3
    new-instance p3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzal;

    invoke-direct {p3, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzal;-><init>(Landroid/os/IBinder;)V

    move-object p1, p3

    .line 8
    :goto_0
    invoke-static {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzm;->zzc(Landroid/os/Parcel;)V

    .line 9
    invoke-interface {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzas;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;)V

    :goto_1
    const/4 p0, 0x1

    return p0
.end method
