.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzjo;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/function/BiConsumer;


# static fields
.field static final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzjo;


# direct methods
.method public static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzjo;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzjo;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzjo;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzjo;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic accept(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 2

    check-cast p2, Ljava/lang/Long;

    invoke-virtual {p2}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzc;

    invoke-virtual {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzc;->zzt(J)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzc;

    return-void
.end method
