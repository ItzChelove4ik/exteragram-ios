.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzava;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/concurrent/Callable;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzava;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    return-void
.end method


# virtual methods
.method public final synthetic call()Ljava/lang/Object;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzava;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;->zzb()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
