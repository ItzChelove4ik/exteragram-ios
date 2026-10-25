.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private zza:I

.field private final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpu;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpu;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpu;

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpu;

    return-void
.end method


# virtual methods
.method public final zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;
    .locals 0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza:I

    return-object p0
.end method

.method public final zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpv;
    .locals 2

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpq;

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zza:I

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpr;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpu;

    invoke-direct {v0, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpq;-><init>(ILcom/google/android/gms/internal/mlkit_genai_prompt/zzpu;)V

    return-object v0
.end method
