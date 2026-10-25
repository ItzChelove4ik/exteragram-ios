.class public Lcom/chaquo/python/Reflector;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/chaquo/python/Reflector$DeniedMethod;
    }
.end annotation


# static fields
.field private static final DENIED_METHODS:[Lcom/chaquo/python/Reflector$DeniedMethod;

.field private static final instances:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/Class<",
            "*>;",
            "Ljava/lang/ref/WeakReference<",
            "Lcom/chaquo/python/Reflector;",
            ">;>;"
        }
    .end annotation
.end field


# instance fields
.field private classes:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Class<",
            "*>;>;"
        }
    .end annotation
.end field

.field private classesAll:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Class<",
            "*>;>;"
        }
    .end annotation
.end field

.field private fields:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Field;",
            ">;"
        }
    .end annotation
.end field

.field private fieldsAll:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Field;",
            ">;"
        }
    .end annotation
.end field

.field private final klass:Ljava/lang/Class;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation
.end field

.field private methods:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;"
        }
    .end annotation
.end field

.field private methodsAll:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;"
        }
    .end annotation
.end field

.field private multipleMethods:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;"
        }
    .end annotation
.end field

.field private multipleMethodsAll:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;"
        }
    .end annotation
.end field

.field private multiplePropertyGetters:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;"
        }
    .end annotation
.end field

.field private multiplePropertyGettersAll:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;"
        }
    .end annotation
.end field

.field private multiplePropertySetters:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;"
        }
    .end annotation
.end field

.field private multiplePropertySettersAll:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;"
        }
    .end annotation
.end field

.field private multipleStaticPropertyGetters:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;"
        }
    .end annotation
.end field

.field private multipleStaticPropertyGettersAll:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;"
        }
    .end annotation
.end field

.field private multipleStaticPropertySetters:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;"
        }
    .end annotation
.end field

.field private multipleStaticPropertySettersAll:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;"
        }
    .end annotation
.end field

.field private propertyGetters:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;"
        }
    .end annotation
.end field

.field private propertyGettersAll:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;"
        }
    .end annotation
.end field

.field private propertySetters:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;"
        }
    .end annotation
.end field

.field private propertySettersAll:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;"
        }
    .end annotation
.end field

.field private staticPropertyGetters:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;"
        }
    .end annotation
.end field

.field private staticPropertyGettersAll:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;"
        }
    .end annotation
.end field

.field private staticPropertySetters:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;"
        }
    .end annotation
.end field

.field private staticPropertySettersAll:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 5

    .line 13
    new-instance v0, Lcom/chaquo/python/Reflector$DeniedMethod;

    sget-object v1, Ljava/lang/Long;->TYPE:Ljava/lang/Class;

    filled-new-array {v1}, [Ljava/lang/Class;

    move-result-object v1

    const-string v2, "android.widget.TextView"

    const-string/jumbo v3, "setTextColor"

    sget-object v4, Ljava/lang/Void;->TYPE:Ljava/lang/Class;

    invoke-direct {v0, v2, v3, v4, v1}, Lcom/chaquo/python/Reflector$DeniedMethod;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V

    filled-new-array {v0}, [Lcom/chaquo/python/Reflector$DeniedMethod;

    move-result-object v0

    sput-object v0, Lcom/chaquo/python/Reflector;->DENIED_METHODS:[Lcom/chaquo/python/Reflector$DeniedMethod;

    .line 50
    new-instance v0, Ljava/util/WeakHashMap;

    invoke-direct {v0}, Ljava/util/WeakHashMap;-><init>()V

    sput-object v0, Lcom/chaquo/python/Reflector;->instances:Ljava/util/Map;

    return-void
.end method

.method private constructor <init>(Ljava/lang/Class;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;)V"
        }
    .end annotation

    .line 63
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 64
    iput-object p1, p0, Lcom/chaquo/python/Reflector;->klass:Ljava/lang/Class;

    return-void
.end method

.method private addPropertyAlias(Ljava/util/List;Ljava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/lang/String;",
            ")V"
        }
    .end annotation

    if-eqz p2, :cond_0

    .line 376
    invoke-interface {p1, p2}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_0

    .line 377
    invoke-interface {p1, p2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_0
    return-void
.end method

.method private booleanPropertyAliasName(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .line 386
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 389
    :cond_0
    const-string v0, "Is"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v0

    const/4 v1, 0x2

    if-le v0, v1, :cond_1

    .line 390
    invoke-virtual {p1, v1}, Ljava/lang/String;->charAt(I)C

    move-result v0

    invoke-static {v0}, Ljava/lang/Character;->isUpperCase(C)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 391
    invoke-direct {p0, p1}, Lcom/chaquo/python/Reflector;->decapitalizePropertyName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 393
    :cond_1
    const-string/jumbo p0, "is"

    invoke-virtual {p0, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private decapitalizePropertyName(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 397
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 400
    :cond_0
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Ljava/lang/String;->charAt(I)C

    move-result v0

    invoke-static {v0}, Ljava/lang/Character;->toLowerCase(C)C

    move-result v0

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private getDeclaredFields()Ljava/util/Collection;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Collection<",
            "Ljava/lang/reflect/Field;",
            ">;"
        }
    .end annotation

    .line 438
    :try_start_0
    iget-object v0, p0, Lcom/chaquo/python/Reflector;->klass:Ljava/lang/Class;

    invoke-virtual {v0}, Ljava/lang/Class;->getDeclaredFields()[Ljava/lang/reflect/Field;

    move-result-object v0

    invoke-static {v0}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/NoClassDefFoundError; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    .line 442
    :catch_0
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    .line 447
    :try_start_1
    iget-object v1, p0, Lcom/chaquo/python/Reflector;->klass:Ljava/lang/Class;

    invoke-virtual {v1}, Ljava/lang/Class;->getFields()[Ljava/lang/reflect/Field;

    move-result-object v1

    array-length v2, v1

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_1

    aget-object v4, v1, v3

    .line 448
    invoke-virtual {v4}, Ljava/lang/reflect/Field;->getDeclaringClass()Ljava/lang/Class;

    move-result-object v5

    iget-object v6, p0, Lcom/chaquo/python/Reflector;->klass:Ljava/lang/Class;
    :try_end_1
    .catch Ljava/lang/NoClassDefFoundError; {:try_start_1 .. :try_end_1} :catch_2

    if-ne v5, v6, :cond_0

    .line 450
    :try_start_2
    invoke-virtual {v4}, Ljava/lang/reflect/Field;->getType()Ljava/lang/Class;

    .line 451
    invoke-interface {v0, v4}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
    :try_end_2
    .catch Ljava/lang/NoClassDefFoundError; {:try_start_2 .. :try_end_2} :catch_1

    :catch_1
    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :catch_2
    :cond_1
    return-object v0
.end method

.method private getDeclaredMethods()Ljava/util/Collection;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Collection<",
            "Ljava/lang/reflect/Method;",
            ">;"
        }
    .end annotation

    .line 267
    :try_start_0
    iget-object v0, p0, Lcom/chaquo/python/Reflector;->klass:Ljava/lang/Class;

    invoke-virtual {v0}, Ljava/lang/Class;->getDeclaredMethods()[Ljava/lang/reflect/Method;

    move-result-object v0

    invoke-static {v0}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/NoClassDefFoundError; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    .line 275
    :catch_0
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    .line 279
    :try_start_1
    iget-object v1, p0, Lcom/chaquo/python/Reflector;->klass:Ljava/lang/Class;

    invoke-virtual {v1}, Ljava/lang/Class;->getMethods()[Ljava/lang/reflect/Method;

    move-result-object v1

    array-length v2, v1

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_1

    aget-object v4, v1, v3

    .line 280
    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getDeclaringClass()Ljava/lang/Class;

    move-result-object v5

    iget-object v6, p0, Lcom/chaquo/python/Reflector;->klass:Ljava/lang/Class;
    :try_end_1
    .catch Ljava/lang/NoClassDefFoundError; {:try_start_1 .. :try_end_1} :catch_2

    if-ne v5, v6, :cond_0

    .line 282
    :try_start_2
    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getReturnType()Ljava/lang/Class;

    .line 283
    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    .line 284
    invoke-interface {v0, v4}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
    :try_end_2
    .catch Ljava/lang/NoClassDefFoundError; {:try_start_2 .. :try_end_2} :catch_1

    :catch_1
    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 291
    :catch_2
    :cond_1
    iget-object v1, p0, Lcom/chaquo/python/Reflector;->klass:Ljava/lang/Class;

    :cond_2
    invoke-virtual {v1}, Ljava/lang/Class;->getSuperclass()Ljava/lang/Class;

    move-result-object v1

    if-eqz v1, :cond_3

    .line 292
    invoke-static {v1}, Lcom/chaquo/python/Reflector;->getInstance(Ljava/lang/Class;)Lcom/chaquo/python/Reflector;

    move-result-object v2

    invoke-direct {v2}, Lcom/chaquo/python/Reflector;->getDeclaredMethods()Ljava/util/Collection;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :catch_3
    :goto_1
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/reflect/Method;

    .line 294
    :try_start_3
    iget-object v4, p0, Lcom/chaquo/python/Reflector;->klass:Ljava/lang/Class;

    invoke-virtual {v3}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v5

    .line 295
    invoke-virtual {v3}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    move-result-object v3

    .line 294
    invoke-virtual {v4, v5, v3}, Ljava/lang/Class;->getDeclaredMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v3

    invoke-interface {v0, v3}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
    :try_end_3
    .catch Ljava/lang/NoSuchMethodException; {:try_start_3 .. :try_end_3} :catch_3

    goto :goto_1

    :cond_3
    return-object v0
.end method

.method public static declared-synchronized getInstance(Ljava/lang/Class;)Lcom/chaquo/python/Reflector;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;)",
            "Lcom/chaquo/python/Reflector;"
        }
    .end annotation

    const-class v0, Lcom/chaquo/python/Reflector;

    monitor-enter v0

    .line 53
    :try_start_0
    sget-object v1, Lcom/chaquo/python/Reflector;->instances:Ljava/util/Map;

    invoke-interface {v1, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/ref/WeakReference;

    if-nez v2, :cond_0

    const/4 v2, 0x0

    goto :goto_0

    .line 54
    :cond_0
    invoke-virtual {v2}, Ljava/lang/ref/Reference;->get()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/chaquo/python/Reflector;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :goto_0
    if-eqz v2, :cond_1

    .line 56
    monitor-exit v0

    return-object v2

    .line 58
    :cond_1
    :try_start_1
    new-instance v2, Lcom/chaquo/python/Reflector;

    invoke-direct {v2, p0}, Lcom/chaquo/python/Reflector;-><init>(Ljava/lang/Class;)V

    .line 59
    new-instance v3, Ljava/lang/ref/WeakReference;

    invoke-direct {v3, v2}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    invoke-interface {v1, p0, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 60
    monitor-exit v0

    return-object v2

    :catchall_0
    move-exception p0

    :try_start_2
    monitor-exit v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p0
.end method

.method private getPropertyMethods(Ljava/lang/String;ZILjava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)[Ljava/lang/reflect/Member;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "ZI",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;)[",
            "Ljava/lang/reflect/Member;"
        }
    .end annotation

    const/4 p0, 0x1

    if-eqz p2, :cond_1

    if-ne p3, p0, :cond_0

    move-object p8, p10

    :cond_0
    if-ne p3, p0, :cond_4

    move-object p9, p11

    goto :goto_0

    :cond_1
    if-ne p3, p0, :cond_2

    move-object p4, p6

    :cond_2
    if-ne p3, p0, :cond_3

    move-object p5, p7

    :cond_3
    move-object p8, p4

    move-object p9, p5

    .line 165
    :cond_4
    :goto_0
    invoke-interface {p9, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/util/List;

    const/4 p3, 0x0

    if-eqz p2, :cond_5

    .line 167
    new-array p0, p3, [Ljava/lang/reflect/Member;

    invoke-interface {p2, p0}, Ljava/util/List;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object p0

    check-cast p0, [Ljava/lang/reflect/Member;

    return-object p0

    .line 169
    :cond_5
    invoke-interface {p8, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/reflect/Member;

    if-eqz p1, :cond_6

    .line 171
    new-array p0, p0, [Ljava/lang/reflect/Member;

    aput-object p1, p0, p3

    return-object p0

    :cond_6
    const/4 p0, 0x0

    return-object p0
.end method

.method private includeMember(Ljava/lang/reflect/Member;Z)Z
    .locals 0

    if-eqz p2, :cond_1

    .line 495
    invoke-interface {p1}, Ljava/lang/reflect/Member;->isSynthetic()Z

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    invoke-direct {p0, p1}, Lcom/chaquo/python/Reflector;->isAccessible(Ljava/lang/reflect/Member;)Z

    move-result p0

    return p0
.end method

.method private isAccessible(I)Z
    .locals 0

    .line 0
    and-int/lit8 p0, p1, 0x5

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private isAccessible(Ljava/lang/reflect/Member;)Z
    .locals 1

    .line 506
    invoke-interface {p1}, Ljava/lang/reflect/Member;->getModifiers()I

    move-result v0

    invoke-direct {p0, v0}, Lcom/chaquo/python/Reflector;->isAccessible(I)Z

    move-result p0

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return v0

    .line 511
    :cond_0
    invoke-interface {p1}, Ljava/lang/reflect/Member;->isSynthetic()Z

    move-result p0

    if-eqz p0, :cond_1

    return v0

    :cond_1
    const/4 p0, 0x1

    return p0
.end method

.method private isBooleanType(Ljava/lang/Class;)Z
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;)Z"
        }
    .end annotation

    .line 382
    sget-object p0, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    if-eq p1, p0, :cond_1

    const-class p0, Ljava/lang/Boolean;

    if-ne p1, p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method private isDeniedMethod(Ljava/lang/reflect/Method;)Z
    .locals 4

    .line 235
    sget-object p0, Lcom/chaquo/python/Reflector;->DENIED_METHODS:[Lcom/chaquo/python/Reflector$DeniedMethod;

    array-length v0, p0

    const/4 v1, 0x0

    move v2, v1

    :goto_0
    if-ge v2, v0, :cond_1

    aget-object v3, p0, v2

    .line 236
    invoke-virtual {v3, p1}, Lcom/chaquo/python/Reflector$DeniedMethod;->matches(Ljava/lang/reflect/Method;)Z

    move-result v3

    if-eqz v3, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_1
    return v1
.end method

.method private loadClasses(Z)V
    .locals 7

    .line 479
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    .line 480
    iget-object v1, p0, Lcom/chaquo/python/Reflector;->klass:Ljava/lang/Class;

    invoke-virtual {v1}, Ljava/lang/Class;->getDeclaredClasses()[Ljava/lang/Class;

    move-result-object v1

    array-length v2, v1

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_3

    aget-object v4, v1, v3

    if-nez p1, :cond_0

    .line 481
    invoke-virtual {v4}, Ljava/lang/Class;->getModifiers()I

    move-result v5

    invoke-direct {p0, v5}, Lcom/chaquo/python/Reflector;->isAccessible(I)Z

    move-result v5

    if-eqz v5, :cond_2

    .line 482
    :cond_0
    invoke-virtual {v4}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v5

    .line 483
    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v6

    if-eqz v6, :cond_1

    goto :goto_1

    .line 484
    :cond_1
    invoke-interface {v0, v5, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_2
    :goto_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_3
    if-eqz p1, :cond_4

    .line 488
    iput-object v0, p0, Lcom/chaquo/python/Reflector;->classesAll:Ljava/util/Map;

    return-void

    .line 490
    :cond_4
    iput-object v0, p0, Lcom/chaquo/python/Reflector;->classes:Ljava/util/Map;

    return-void
.end method

.method private loadFields(Z)V
    .locals 4

    .line 422
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    .line 423
    invoke-direct {p0}, Lcom/chaquo/python/Reflector;->getDeclaredFields()Ljava/util/Collection;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/reflect/Field;

    if-nez p1, :cond_1

    .line 424
    invoke-direct {p0, v2}, Lcom/chaquo/python/Reflector;->isAccessible(Ljava/lang/reflect/Member;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 425
    :cond_1
    invoke-direct {p0, v2, p1}, Lcom/chaquo/python/Reflector;->prepareAccessible(Ljava/lang/reflect/AccessibleObject;Z)V

    .line 426
    invoke-virtual {v2}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object v3

    invoke-interface {v0, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    :cond_2
    if-eqz p1, :cond_3

    .line 430
    iput-object v0, p0, Lcom/chaquo/python/Reflector;->fieldsAll:Ljava/util/Map;

    return-void

    .line 432
    :cond_3
    iput-object v0, p0, Lcom/chaquo/python/Reflector;->fields:Ljava/util/Map;

    return-void
.end method

.method private loadMethod(Ljava/util/Map;Ljava/util/Map;Ljava/lang/reflect/Member;Ljava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;",
            "Ljava/lang/reflect/Member;",
            "Ljava/lang/String;",
            ")V"
        }
    .end annotation

    .line 306
    invoke-interface {p1, p4}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/reflect/Member;

    if-eqz p0, :cond_0

    .line 308
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    .line 309
    invoke-interface {p1, p0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 310
    invoke-interface {p1, p3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 311
    invoke-interface {p2, p4, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void

    .line 312
    :cond_0
    invoke-interface {p2, p4}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/List;

    if-eqz p0, :cond_1

    .line 313
    invoke-interface {p0, p3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-void

    .line 315
    :cond_1
    invoke-interface {p1, p4, p3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method private loadMethods(Z)V
    .locals 17

    move-object/from16 v0, p0

    move/from16 v10, p1

    .line 177
    new-instance v11, Ljava/util/HashMap;

    invoke-direct {v11}, Ljava/util/HashMap;-><init>()V

    .line 178
    new-instance v12, Ljava/util/HashMap;

    invoke-direct {v12}, Ljava/util/HashMap;-><init>()V

    .line 179
    new-instance v2, Ljava/util/HashMap;

    invoke-direct {v2}, Ljava/util/HashMap;-><init>()V

    .line 180
    new-instance v3, Ljava/util/HashMap;

    invoke-direct {v3}, Ljava/util/HashMap;-><init>()V

    .line 181
    new-instance v4, Ljava/util/HashMap;

    invoke-direct {v4}, Ljava/util/HashMap;-><init>()V

    .line 182
    new-instance v5, Ljava/util/HashMap;

    invoke-direct {v5}, Ljava/util/HashMap;-><init>()V

    .line 183
    new-instance v6, Ljava/util/HashMap;

    invoke-direct {v6}, Ljava/util/HashMap;-><init>()V

    .line 184
    new-instance v7, Ljava/util/HashMap;

    invoke-direct {v7}, Ljava/util/HashMap;-><init>()V

    .line 185
    new-instance v8, Ljava/util/HashMap;

    invoke-direct {v8}, Ljava/util/HashMap;-><init>()V

    .line 186
    new-instance v9, Ljava/util/HashMap;

    invoke-direct {v9}, Ljava/util/HashMap;-><init>()V

    .line 187
    iget-object v1, v0, Lcom/chaquo/python/Reflector;->klass:Ljava/lang/Class;

    invoke-virtual {v1}, Ljava/lang/Class;->getDeclaredConstructors()[Ljava/lang/reflect/Constructor;

    move-result-object v1

    array-length v13, v1

    const/4 v14, 0x0

    :goto_0
    if-ge v14, v13, :cond_1

    aget-object v15, v1, v14

    .line 188
    invoke-direct {v0, v15, v10}, Lcom/chaquo/python/Reflector;->includeMember(Ljava/lang/reflect/Member;Z)Z

    move-result v16

    if-eqz v16, :cond_0

    .line 189
    invoke-direct {v0, v15, v10}, Lcom/chaquo/python/Reflector;->prepareAccessible(Ljava/lang/reflect/AccessibleObject;Z)V

    move-object/from16 v16, v1

    .line 190
    const-string v1, "<init>"

    invoke-direct {v0, v11, v12, v15, v1}, Lcom/chaquo/python/Reflector;->loadMethod(Ljava/util/Map;Ljava/util/Map;Ljava/lang/reflect/Member;Ljava/lang/String;)V

    goto :goto_1

    :cond_0
    move-object/from16 v16, v1

    :goto_1
    add-int/lit8 v14, v14, 0x1

    move-object/from16 v1, v16

    goto :goto_0

    .line 193
    :cond_1
    invoke-direct {v0}, Lcom/chaquo/python/Reflector;->getDeclaredMethods()Ljava/util/Collection;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v13

    :cond_2
    :goto_2
    invoke-interface {v13}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_4

    invoke-interface {v13}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/reflect/Method;

    .line 194
    invoke-direct {v0, v1}, Lcom/chaquo/python/Reflector;->isDeniedMethod(Ljava/lang/reflect/Method;)Z

    move-result v14

    if-eqz v14, :cond_3

    goto :goto_2

    .line 197
    :cond_3
    invoke-direct {v0, v1, v10}, Lcom/chaquo/python/Reflector;->includeMember(Ljava/lang/reflect/Member;Z)Z

    move-result v14

    if-eqz v14, :cond_2

    .line 198
    invoke-direct {v0, v1, v10}, Lcom/chaquo/python/Reflector;->prepareAccessible(Ljava/lang/reflect/AccessibleObject;Z)V

    .line 199
    invoke-virtual {v1}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v14

    invoke-direct {v0, v11, v12, v1, v14}, Lcom/chaquo/python/Reflector;->loadMethod(Ljava/util/Map;Ljava/util/Map;Ljava/lang/reflect/Member;Ljava/lang/String;)V

    .line 200
    invoke-direct/range {v0 .. v9}, Lcom/chaquo/python/Reflector;->loadPropertyMethods(Ljava/lang/reflect/Method;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V

    goto :goto_2

    :cond_4
    if-eqz v10, :cond_5

    .line 210
    iput-object v11, v0, Lcom/chaquo/python/Reflector;->methodsAll:Ljava/util/Map;

    .line 211
    iput-object v12, v0, Lcom/chaquo/python/Reflector;->multipleMethodsAll:Ljava/util/Map;

    .line 212
    iput-object v2, v0, Lcom/chaquo/python/Reflector;->propertyGettersAll:Ljava/util/Map;

    .line 213
    iput-object v3, v0, Lcom/chaquo/python/Reflector;->multiplePropertyGettersAll:Ljava/util/Map;

    .line 214
    iput-object v4, v0, Lcom/chaquo/python/Reflector;->staticPropertyGettersAll:Ljava/util/Map;

    .line 215
    iput-object v5, v0, Lcom/chaquo/python/Reflector;->multipleStaticPropertyGettersAll:Ljava/util/Map;

    .line 216
    iput-object v6, v0, Lcom/chaquo/python/Reflector;->propertySettersAll:Ljava/util/Map;

    .line 217
    iput-object v7, v0, Lcom/chaquo/python/Reflector;->multiplePropertySettersAll:Ljava/util/Map;

    .line 218
    iput-object v8, v0, Lcom/chaquo/python/Reflector;->staticPropertySettersAll:Ljava/util/Map;

    .line 219
    iput-object v9, v0, Lcom/chaquo/python/Reflector;->multipleStaticPropertySettersAll:Ljava/util/Map;

    return-void

    .line 221
    :cond_5
    iput-object v11, v0, Lcom/chaquo/python/Reflector;->methods:Ljava/util/Map;

    .line 222
    iput-object v12, v0, Lcom/chaquo/python/Reflector;->multipleMethods:Ljava/util/Map;

    .line 223
    iput-object v2, v0, Lcom/chaquo/python/Reflector;->propertyGetters:Ljava/util/Map;

    .line 224
    iput-object v3, v0, Lcom/chaquo/python/Reflector;->multiplePropertyGetters:Ljava/util/Map;

    .line 225
    iput-object v4, v0, Lcom/chaquo/python/Reflector;->staticPropertyGetters:Ljava/util/Map;

    .line 226
    iput-object v5, v0, Lcom/chaquo/python/Reflector;->multipleStaticPropertyGetters:Ljava/util/Map;

    .line 227
    iput-object v6, v0, Lcom/chaquo/python/Reflector;->propertySetters:Ljava/util/Map;

    .line 228
    iput-object v7, v0, Lcom/chaquo/python/Reflector;->multiplePropertySetters:Ljava/util/Map;

    .line 229
    iput-object v8, v0, Lcom/chaquo/python/Reflector;->staticPropertySetters:Ljava/util/Map;

    .line 230
    iput-object v9, v0, Lcom/chaquo/python/Reflector;->multipleStaticPropertySetters:Ljava/util/Map;

    return-void
.end method

.method private loadPropertyMethods(Ljava/lang/reflect/Method;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/reflect/Method;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Member;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Member;",
            ">;>;)V"
        }
    .end annotation

    .line 325
    invoke-virtual {p1}, Ljava/lang/reflect/Method;->getModifiers()I

    move-result v0

    invoke-static {v0}, Ljava/lang/reflect/Modifier;->isStatic(I)Z

    move-result v0

    .line 327
    invoke-direct {p0, p1}, Lcom/chaquo/python/Reflector;->propertyGetterNames(Ljava/lang/reflect/Method;)Ljava/util/List;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 328
    invoke-direct {p0, p2, p3, p1, v2}, Lcom/chaquo/python/Reflector;->loadMethod(Ljava/util/Map;Ljava/util/Map;Ljava/lang/reflect/Member;Ljava/lang/String;)V

    if-eqz v0, :cond_0

    .line 330
    invoke-direct {p0, p4, p5, p1, v2}, Lcom/chaquo/python/Reflector;->loadMethod(Ljava/util/Map;Ljava/util/Map;Ljava/lang/reflect/Member;Ljava/lang/String;)V

    goto :goto_0

    .line 334
    :cond_1
    invoke-direct {p0, p1}, Lcom/chaquo/python/Reflector;->propertySetterNames(Ljava/lang/reflect/Method;)Ljava/util/List;

    move-result-object p2

    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :cond_2
    :goto_1
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result p3

    if-eqz p3, :cond_3

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Ljava/lang/String;

    .line 335
    invoke-direct {p0, p6, p7, p1, p3}, Lcom/chaquo/python/Reflector;->loadMethod(Ljava/util/Map;Ljava/util/Map;Ljava/lang/reflect/Member;Ljava/lang/String;)V

    if-eqz v0, :cond_2

    .line 337
    invoke-direct {p0, p8, p9, p1, p3}, Lcom/chaquo/python/Reflector;->loadMethod(Ljava/util/Map;Ljava/util/Map;Ljava/lang/reflect/Member;Ljava/lang/String;)V

    goto :goto_1

    :cond_3
    return-void
.end method

.method private prepareAccessible(Ljava/lang/reflect/AccessibleObject;Z)V
    .locals 0

    if-nez p2, :cond_0

    return-void

    :cond_0
    const/4 p0, 0x1

    .line 501
    :try_start_0
    invoke-virtual {p1, p0}, Ljava/lang/reflect/AccessibleObject;->setAccessible(Z)V
    :try_end_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return-void
.end method

.method private propertyGetterNames(Ljava/lang/reflect/Method;)Ljava/util/List;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/reflect/Method;",
            ")",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 343
    invoke-virtual {p1}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v0

    .line 344
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 345
    const-string v2, "get"

    invoke-virtual {v0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v2

    const/4 v3, 0x3

    if-le v2, v3, :cond_0

    invoke-virtual {p1}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    move-result-object v2

    array-length v2, v2

    if-nez v2, :cond_0

    .line 346
    invoke-virtual {p1}, Ljava/lang/reflect/Method;->getReturnType()Ljava/lang/Class;

    move-result-object v2

    sget-object v4, Ljava/lang/Void;->TYPE:Ljava/lang/Class;

    if-eq v2, v4, :cond_0

    .line 347
    invoke-virtual {v0, v3}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    .line 348
    invoke-direct {p0, v2}, Lcom/chaquo/python/Reflector;->decapitalizePropertyName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-direct {p0, v1, v3}, Lcom/chaquo/python/Reflector;->addPropertyAlias(Ljava/util/List;Ljava/lang/String;)V

    .line 349
    invoke-virtual {p1}, Ljava/lang/reflect/Method;->getReturnType()Ljava/lang/Class;

    move-result-object v3

    invoke-direct {p0, v3}, Lcom/chaquo/python/Reflector;->isBooleanType(Ljava/lang/Class;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 350
    invoke-direct {p0, v2}, Lcom/chaquo/python/Reflector;->booleanPropertyAliasName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-direct {p0, v1, v2}, Lcom/chaquo/python/Reflector;->addPropertyAlias(Ljava/util/List;Ljava/lang/String;)V

    .line 354
    :cond_0
    const-string/jumbo v2, "is"

    invoke-virtual {v0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v2

    const/4 v3, 0x2

    if-le v2, v3, :cond_1

    invoke-virtual {p1}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    move-result-object v2

    array-length v2, v2

    if-nez v2, :cond_1

    .line 355
    invoke-virtual {p1}, Ljava/lang/reflect/Method;->getReturnType()Ljava/lang/Class;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/chaquo/python/Reflector;->isBooleanType(Ljava/lang/Class;)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 356
    invoke-virtual {v0, v3}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/chaquo/python/Reflector;->decapitalizePropertyName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, v1, p1}, Lcom/chaquo/python/Reflector;->addPropertyAlias(Ljava/util/List;Ljava/lang/String;)V

    .line 357
    invoke-direct {p0, v1, v0}, Lcom/chaquo/python/Reflector;->addPropertyAlias(Ljava/util/List;Ljava/lang/String;)V

    :cond_1
    return-object v1
.end method

.method private propertySetterNames(Ljava/lang/reflect/Method;)Ljava/util/List;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/reflect/Method;",
            ")",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 363
    invoke-virtual {p1}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v0

    .line 364
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 365
    const-string/jumbo v2, "set"

    invoke-virtual {v0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v2

    const/4 v3, 0x3

    if-le v2, v3, :cond_0

    invoke-virtual {p1}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    move-result-object v2

    array-length v2, v2

    if-eqz v2, :cond_0

    .line 366
    invoke-virtual {v0, v3}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v0

    .line 367
    invoke-direct {p0, v0}, Lcom/chaquo/python/Reflector;->decapitalizePropertyName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-direct {p0, v1, v2}, Lcom/chaquo/python/Reflector;->addPropertyAlias(Ljava/util/List;Ljava/lang/String;)V

    .line 368
    invoke-virtual {p1}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    move-result-object p1

    const/4 v2, 0x0

    aget-object p1, p1, v2

    invoke-direct {p0, p1}, Lcom/chaquo/python/Reflector;->isBooleanType(Ljava/lang/Class;)Z

    move-result p1

    if-eqz p1, :cond_0

    .line 369
    invoke-direct {p0, v0}, Lcom/chaquo/python/Reflector;->booleanPropertyAliasName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, v1, p1}, Lcom/chaquo/python/Reflector;->addPropertyAlias(Ljava/util/List;Ljava/lang/String;)V

    :cond_0
    return-object v1
.end method


# virtual methods
.method public declared-synchronized dir()[Ljava/lang/String;
    .locals 1

    monitor-enter p0

    const/4 v0, 0x0

    .line 68
    :try_start_0
    invoke-virtual {p0, v0}, Lcom/chaquo/python/Reflector;->dir(Z)[Ljava/lang/String;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object v0

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public declared-synchronized dir(Z)[Ljava/lang/String;
    .locals 3

    monitor-enter p0

    const/4 v0, 0x0

    if-eqz p1, :cond_2

    .line 77
    :try_start_0
    iget-object v1, p0, Lcom/chaquo/python/Reflector;->methodsAll:Ljava/util/Map;

    const/4 v2, 0x1

    if-nez v1, :cond_0

    invoke-direct {p0, v2}, Lcom/chaquo/python/Reflector;->loadMethods(Z)V

    goto :goto_0

    :catchall_0
    move-exception p1

    goto/16 :goto_6

    .line 78
    :cond_0
    :goto_0
    iget-object v1, p0, Lcom/chaquo/python/Reflector;->fieldsAll:Ljava/util/Map;

    if-nez v1, :cond_1

    invoke-direct {p0, v2}, Lcom/chaquo/python/Reflector;->loadFields(Z)V

    .line 79
    :cond_1
    iget-object v1, p0, Lcom/chaquo/python/Reflector;->classesAll:Ljava/util/Map;

    if-nez v1, :cond_5

    invoke-direct {p0, v2}, Lcom/chaquo/python/Reflector;->loadClasses(Z)V

    goto :goto_1

    .line 81
    :cond_2
    iget-object v1, p0, Lcom/chaquo/python/Reflector;->methods:Ljava/util/Map;

    if-nez v1, :cond_3

    invoke-direct {p0, v0}, Lcom/chaquo/python/Reflector;->loadMethods(Z)V

    .line 82
    :cond_3
    iget-object v1, p0, Lcom/chaquo/python/Reflector;->fields:Ljava/util/Map;

    if-nez v1, :cond_4

    invoke-direct {p0, v0}, Lcom/chaquo/python/Reflector;->loadFields(Z)V

    .line 83
    :cond_4
    iget-object v1, p0, Lcom/chaquo/python/Reflector;->classes:Ljava/util/Map;

    if-nez v1, :cond_5

    invoke-direct {p0, v0}, Lcom/chaquo/python/Reflector;->loadClasses(Z)V

    .line 85
    :cond_5
    :goto_1
    new-instance v1, Ljava/util/HashSet;

    invoke-direct {v1}, Ljava/util/HashSet;-><init>()V

    if-eqz p1, :cond_6

    .line 86
    iget-object v2, p0, Lcom/chaquo/python/Reflector;->methodsAll:Ljava/util/Map;

    goto :goto_2

    :cond_6
    iget-object v2, p0, Lcom/chaquo/python/Reflector;->methods:Ljava/util/Map;

    :goto_2
    invoke-interface {v2}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v2

    invoke-interface {v1, v2}, Ljava/util/Set;->addAll(Ljava/util/Collection;)Z

    if-eqz p1, :cond_7

    .line 87
    iget-object v2, p0, Lcom/chaquo/python/Reflector;->multipleMethodsAll:Ljava/util/Map;

    goto :goto_3

    :cond_7
    iget-object v2, p0, Lcom/chaquo/python/Reflector;->multipleMethods:Ljava/util/Map;

    :goto_3
    invoke-interface {v2}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v2

    invoke-interface {v1, v2}, Ljava/util/Set;->addAll(Ljava/util/Collection;)Z

    if-eqz p1, :cond_8

    .line 88
    iget-object v2, p0, Lcom/chaquo/python/Reflector;->fieldsAll:Ljava/util/Map;

    goto :goto_4

    :cond_8
    iget-object v2, p0, Lcom/chaquo/python/Reflector;->fields:Ljava/util/Map;

    :goto_4
    invoke-interface {v2}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v2

    invoke-interface {v1, v2}, Ljava/util/Set;->addAll(Ljava/util/Collection;)Z

    if-eqz p1, :cond_9

    .line 89
    iget-object p1, p0, Lcom/chaquo/python/Reflector;->classesAll:Ljava/util/Map;

    goto :goto_5

    :cond_9
    iget-object p1, p0, Lcom/chaquo/python/Reflector;->classes:Ljava/util/Map;

    :goto_5
    invoke-interface {p1}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object p1

    invoke-interface {v1, p1}, Ljava/util/Set;->addAll(Ljava/util/Collection;)Z

    .line 90
    new-array p1, v0, [Ljava/lang/String;

    invoke-interface {v1, p1}, Ljava/util/Set;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object p1

    check-cast p1, [Ljava/lang/String;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :goto_6
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized dirAll()[Ljava/lang/String;
    .locals 1

    monitor-enter p0

    const/4 v0, 0x1

    .line 72
    :try_start_0
    invoke-virtual {p0, v0}, Lcom/chaquo/python/Reflector;->dir(Z)[Ljava/lang/String;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object v0

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public declared-synchronized getField(Ljava/lang/String;)Ljava/lang/reflect/Field;
    .locals 1

    monitor-enter p0

    const/4 v0, 0x0

    .line 404
    :try_start_0
    invoke-virtual {p0, p1, v0}, Lcom/chaquo/python/Reflector;->getField(Ljava/lang/String;Z)Ljava/lang/reflect/Field;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized getField(Ljava/lang/String;Z)Ljava/lang/reflect/Field;
    .locals 0

    monitor-enter p0

    if-eqz p2, :cond_1

    .line 413
    :try_start_0
    iget-object p2, p0, Lcom/chaquo/python/Reflector;->fieldsAll:Ljava/util/Map;

    if-nez p2, :cond_0

    const/4 p2, 0x1

    invoke-direct {p0, p2}, Lcom/chaquo/python/Reflector;->loadFields(Z)V

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 414
    :cond_0
    :goto_0
    iget-object p2, p0, Lcom/chaquo/python/Reflector;->fieldsAll:Ljava/util/Map;

    invoke-interface {p2, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/reflect/Field;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    .line 416
    :cond_1
    :try_start_1
    iget-object p2, p0, Lcom/chaquo/python/Reflector;->fields:Ljava/util/Map;

    if-nez p2, :cond_2

    const/4 p2, 0x0

    invoke-direct {p0, p2}, Lcom/chaquo/python/Reflector;->loadFields(Z)V

    .line 417
    :cond_2
    iget-object p2, p0, Lcom/chaquo/python/Reflector;->fields:Ljava/util/Map;

    invoke-interface {p2, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/reflect/Field;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    monitor-exit p0

    return-object p1

    :goto_1
    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p1
.end method

.method public declared-synchronized getFieldAll(Ljava/lang/String;)Ljava/lang/reflect/Field;
    .locals 1

    monitor-enter p0

    const/4 v0, 0x1

    .line 408
    :try_start_0
    invoke-virtual {p0, p1, v0}, Lcom/chaquo/python/Reflector;->getField(Ljava/lang/String;Z)Ljava/lang/reflect/Field;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized getMethods(Ljava/lang/String;)[Ljava/lang/reflect/Member;
    .locals 1

    monitor-enter p0

    const/4 v0, 0x0

    .line 94
    :try_start_0
    invoke-virtual {p0, p1, v0}, Lcom/chaquo/python/Reflector;->getMethods(Ljava/lang/String;Z)[Ljava/lang/reflect/Member;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized getMethods(Ljava/lang/String;Z)[Ljava/lang/reflect/Member;
    .locals 3

    monitor-enter p0

    const/4 v0, 0x1

    const/4 v1, 0x0

    if-eqz p2, :cond_0

    .line 103
    :try_start_0
    iget-object v2, p0, Lcom/chaquo/python/Reflector;->methodsAll:Ljava/util/Map;

    if-nez v2, :cond_1

    invoke-direct {p0, v0}, Lcom/chaquo/python/Reflector;->loadMethods(Z)V

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_3

    .line 105
    :cond_0
    iget-object v2, p0, Lcom/chaquo/python/Reflector;->methods:Ljava/util/Map;

    if-nez v2, :cond_1

    invoke-direct {p0, v1}, Lcom/chaquo/python/Reflector;->loadMethods(Z)V

    :cond_1
    :goto_0
    if-eqz p2, :cond_2

    .line 107
    iget-object v2, p0, Lcom/chaquo/python/Reflector;->methodsAll:Ljava/util/Map;

    goto :goto_1

    :cond_2
    iget-object v2, p0, Lcom/chaquo/python/Reflector;->methods:Ljava/util/Map;

    :goto_1
    if-eqz p2, :cond_3

    .line 108
    iget-object p2, p0, Lcom/chaquo/python/Reflector;->multipleMethodsAll:Ljava/util/Map;

    goto :goto_2

    :cond_3
    iget-object p2, p0, Lcom/chaquo/python/Reflector;->multipleMethods:Ljava/util/Map;

    .line 109
    :goto_2
    invoke-interface {p2, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/util/List;

    if-eqz p2, :cond_4

    .line 111
    new-array p1, v1, [Ljava/lang/reflect/Member;

    invoke-interface {p2, p1}, Ljava/util/List;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object p1

    check-cast p1, [Ljava/lang/reflect/Member;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    .line 113
    :cond_4
    :try_start_1
    invoke-interface {v2, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/reflect/Member;

    if-eqz p1, :cond_5

    .line 115
    new-array p2, v0, [Ljava/lang/reflect/Member;

    aput-object p1, p2, v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    monitor-exit p0

    return-object p2

    .line 117
    :cond_5
    monitor-exit p0

    const/4 p0, 0x0

    return-object p0

    :goto_3
    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p1
.end method

.method public declared-synchronized getMethodsAll(Ljava/lang/String;)[Ljava/lang/reflect/Member;
    .locals 1

    monitor-enter p0

    const/4 v0, 0x1

    .line 98
    :try_start_0
    invoke-virtual {p0, p1, v0}, Lcom/chaquo/python/Reflector;->getMethods(Ljava/lang/String;Z)[Ljava/lang/reflect/Member;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized getNestedClass(Ljava/lang/String;)Ljava/lang/Class;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation

    monitor-enter p0

    const/4 v0, 0x0

    .line 461
    :try_start_0
    invoke-virtual {p0, p1, v0}, Lcom/chaquo/python/Reflector;->getNestedClass(Ljava/lang/String;Z)Ljava/lang/Class;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized getNestedClass(Ljava/lang/String;Z)Ljava/lang/Class;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Z)",
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation

    monitor-enter p0

    if-eqz p2, :cond_1

    .line 470
    :try_start_0
    iget-object p2, p0, Lcom/chaquo/python/Reflector;->classesAll:Ljava/util/Map;

    if-nez p2, :cond_0

    const/4 p2, 0x1

    invoke-direct {p0, p2}, Lcom/chaquo/python/Reflector;->loadClasses(Z)V

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 471
    :cond_0
    :goto_0
    iget-object p2, p0, Lcom/chaquo/python/Reflector;->classesAll:Ljava/util/Map;

    invoke-interface {p2, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Class;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    .line 473
    :cond_1
    :try_start_1
    iget-object p2, p0, Lcom/chaquo/python/Reflector;->classes:Ljava/util/Map;

    if-nez p2, :cond_2

    const/4 p2, 0x0

    invoke-direct {p0, p2}, Lcom/chaquo/python/Reflector;->loadClasses(Z)V

    .line 474
    :cond_2
    iget-object p2, p0, Lcom/chaquo/python/Reflector;->classes:Ljava/util/Map;

    invoke-interface {p2, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Class;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    monitor-exit p0

    return-object p1

    :goto_1
    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p1
.end method

.method public declared-synchronized getNestedClassAll(Ljava/lang/String;)Ljava/lang/Class;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation

    monitor-enter p0

    const/4 v0, 0x1

    .line 465
    :try_start_0
    invoke-virtual {p0, p1, v0}, Lcom/chaquo/python/Reflector;->getNestedClass(Ljava/lang/String;Z)Ljava/lang/Class;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized getPropertyGetters(Ljava/lang/String;ZI)[Ljava/lang/reflect/Member;
    .locals 13

    monitor-enter p0

    if-eqz p2, :cond_0

    .line 122
    :try_start_0
    iget-object v0, p0, Lcom/chaquo/python/Reflector;->propertyGettersAll:Ljava/util/Map;

    if-nez v0, :cond_1

    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lcom/chaquo/python/Reflector;->loadMethods(Z)V

    goto :goto_0

    :catchall_0
    move-exception v0

    move-object p1, v0

    goto :goto_1

    .line 124
    :cond_0
    iget-object v0, p0, Lcom/chaquo/python/Reflector;->propertyGetters:Ljava/util/Map;

    if-nez v0, :cond_1

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/chaquo/python/Reflector;->loadMethods(Z)V

    .line 126
    :cond_1
    :goto_0
    iget-object v5, p0, Lcom/chaquo/python/Reflector;->propertyGetters:Ljava/util/Map;

    iget-object v6, p0, Lcom/chaquo/python/Reflector;->multiplePropertyGetters:Ljava/util/Map;

    iget-object v7, p0, Lcom/chaquo/python/Reflector;->staticPropertyGetters:Ljava/util/Map;

    iget-object v8, p0, Lcom/chaquo/python/Reflector;->multipleStaticPropertyGetters:Ljava/util/Map;

    iget-object v9, p0, Lcom/chaquo/python/Reflector;->propertyGettersAll:Ljava/util/Map;

    iget-object v10, p0, Lcom/chaquo/python/Reflector;->multiplePropertyGettersAll:Ljava/util/Map;

    iget-object v11, p0, Lcom/chaquo/python/Reflector;->staticPropertyGettersAll:Ljava/util/Map;

    iget-object v12, p0, Lcom/chaquo/python/Reflector;->multipleStaticPropertyGettersAll:Ljava/util/Map;

    move-object v1, p0

    move-object v2, p1

    move v3, p2

    move/from16 v4, p3

    invoke-direct/range {v1 .. v12}, Lcom/chaquo/python/Reflector;->getPropertyMethods(Ljava/lang/String;ZILjava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)[Ljava/lang/reflect/Member;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :goto_1
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public declared-synchronized getPropertySetters(Ljava/lang/String;ZI)[Ljava/lang/reflect/Member;
    .locals 13

    monitor-enter p0

    if-eqz p2, :cond_0

    .line 137
    :try_start_0
    iget-object v0, p0, Lcom/chaquo/python/Reflector;->propertySettersAll:Ljava/util/Map;

    if-nez v0, :cond_1

    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lcom/chaquo/python/Reflector;->loadMethods(Z)V

    goto :goto_0

    :catchall_0
    move-exception v0

    move-object p1, v0

    goto :goto_1

    .line 139
    :cond_0
    iget-object v0, p0, Lcom/chaquo/python/Reflector;->propertySetters:Ljava/util/Map;

    if-nez v0, :cond_1

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/chaquo/python/Reflector;->loadMethods(Z)V

    .line 141
    :cond_1
    :goto_0
    iget-object v5, p0, Lcom/chaquo/python/Reflector;->propertySetters:Ljava/util/Map;

    iget-object v6, p0, Lcom/chaquo/python/Reflector;->multiplePropertySetters:Ljava/util/Map;

    iget-object v7, p0, Lcom/chaquo/python/Reflector;->staticPropertySetters:Ljava/util/Map;

    iget-object v8, p0, Lcom/chaquo/python/Reflector;->multipleStaticPropertySetters:Ljava/util/Map;

    iget-object v9, p0, Lcom/chaquo/python/Reflector;->propertySettersAll:Ljava/util/Map;

    iget-object v10, p0, Lcom/chaquo/python/Reflector;->multiplePropertySettersAll:Ljava/util/Map;

    iget-object v11, p0, Lcom/chaquo/python/Reflector;->staticPropertySettersAll:Ljava/util/Map;

    iget-object v12, p0, Lcom/chaquo/python/Reflector;->multipleStaticPropertySettersAll:Ljava/util/Map;

    move-object v1, p0

    move-object v2, p1

    move v3, p2

    move/from16 v4, p3

    invoke-direct/range {v1 .. v12}, Lcom/chaquo/python/Reflector;->getPropertyMethods(Ljava/lang/String;ZILjava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)[Ljava/lang/reflect/Member;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :goto_1
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method
