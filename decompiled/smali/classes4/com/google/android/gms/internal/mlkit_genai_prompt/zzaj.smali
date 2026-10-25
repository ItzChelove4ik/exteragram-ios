.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;
.super Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;
.source "SourceFile"


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator<",
            "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final zza:I

.field private final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

.field private final zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzq;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzak;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzak;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method public constructor <init>(ILcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzq;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;-><init>()V

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;->zza:I

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzq;

    return-void
.end method


# virtual methods
.method public final writeToParcel(Landroid/os/Parcel;I)V
    .locals 4

    .line 1
    invoke-static {p1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->beginObjectHeader(Landroid/os/Parcel;)I

    move-result v0

    const/4 v1, 0x1

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;->zza:I

    .line 2
    invoke-static {p1, v1, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbt;

    const/4 v2, 0x2

    const/4 v3, 0x0

    .line 3
    invoke-static {p1, v2, v1, p2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeParcelable(Landroid/os/Parcel;ILandroid/os/Parcelable;IZ)V

    const/4 v1, 0x3

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzq;

    .line 4
    invoke-static {p1, v1, p0, p2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeParcelable(Landroid/os/Parcel;ILandroid/os/Parcelable;IZ)V

    .line 5
    invoke-static {p1, v0}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->finishObjectHeader(Landroid/os/Parcel;I)V

    return-void
.end method

.method public final zza()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;->zza:I

    return p0
.end method

.method public final zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzq;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaj;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzq;

    return-object p0
.end method
