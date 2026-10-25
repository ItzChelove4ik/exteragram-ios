.class public abstract Lcom/google/android/gms/internal/recaptchabase/zzd;
.super Lcom/google/android/gms/internal/recaptchabase/zzb;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/recaptchabase/zze;


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 1
    const-string v0, "com.google.android.gms.recaptchabase.internal.IRecaptchaBaseCallbacks"

    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/recaptchabase/zzb;-><init>(Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public final zza(ILandroid/os/Parcel;Landroid/os/Parcel;I)Z
    .locals 0

    const/4 p3, 0x1

    if-eq p1, p3, :cond_1

    const/4 p4, 0x2

    if-eq p1, p4, :cond_0

    const/4 p0, 0x0

    return p0

    .line 1
    :cond_0
    sget-object p1, Lcom/google/android/gms/common/api/Status;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-static {p2, p1}, Lcom/google/android/gms/internal/recaptchabase/zzc;->zza(Landroid/os/Parcel;Landroid/os/Parcelable$Creator;)Landroid/os/Parcelable;

    move-result-object p1

    check-cast p1, Lcom/google/android/gms/common/api/Status;

    .line 2
    sget-object p4, Lcom/google/android/gms/recaptchabase/ExecuteResult;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-static {p2, p4}, Lcom/google/android/gms/internal/recaptchabase/zzc;->zza(Landroid/os/Parcel;Landroid/os/Parcelable$Creator;)Landroid/os/Parcelable;

    move-result-object p4

    check-cast p4, Lcom/google/android/gms/recaptchabase/ExecuteResult;

    .line 3
    invoke-static {p2}, Lcom/google/android/gms/internal/recaptchabase/zzc;->zzb(Landroid/os/Parcel;)V

    .line 4
    invoke-interface {p0, p1, p4}, Lcom/google/android/gms/internal/recaptchabase/zze;->zzb(Lcom/google/android/gms/common/api/Status;Lcom/google/android/gms/recaptchabase/ExecuteResult;)V

    goto :goto_0

    .line 5
    :cond_1
    sget-object p1, Lcom/google/android/gms/common/api/Status;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-static {p2, p1}, Lcom/google/android/gms/internal/recaptchabase/zzc;->zza(Landroid/os/Parcel;Landroid/os/Parcelable$Creator;)Landroid/os/Parcelable;

    move-result-object p1

    check-cast p1, Lcom/google/android/gms/common/api/Status;

    .line 6
    sget-object p4, Lcom/google/android/gms/recaptchabase/InitResult;->CREATOR:Landroid/os/Parcelable$Creator;

    invoke-static {p2, p4}, Lcom/google/android/gms/internal/recaptchabase/zzc;->zza(Landroid/os/Parcel;Landroid/os/Parcelable$Creator;)Landroid/os/Parcelable;

    move-result-object p4

    check-cast p4, Lcom/google/android/gms/recaptchabase/InitResult;

    .line 7
    invoke-static {p2}, Lcom/google/android/gms/internal/recaptchabase/zzc;->zzb(Landroid/os/Parcel;)V

    .line 8
    invoke-interface {p0, p1, p4}, Lcom/google/android/gms/internal/recaptchabase/zze;->zzc(Lcom/google/android/gms/common/api/Status;Lcom/google/android/gms/recaptchabase/InitResult;)V

    :goto_0
    return p3
.end method
