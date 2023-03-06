import kotlin.math.*

fun main(){
    val eps = 1e-2
    var n = 1
    var f = sIntegral1(n,3.0,5.0)
    do{
        val f1 = f
        n *= 2
        f = sIntegral1(n,3.0,5.0)
    } while (abs(f-f1)>eps)
    println("Integral: $f")
    println("Number of partitions: $n")

    n=1
    f = sIntegral2(n,3.0,4.0)
    do{
        val f1 = f
        n *= 2
        f = sIntegral2(n,3.0,4.0)
    } while (abs(f-f1)>eps)
    println("Integral: $f")
    println("Number of partitions: $n")

    n=1
    f = sIntegral3(n,1.0,2.0)
    do{
        val f1 = f
        n *= 2
        f = sIntegral3(n,1.0,2.0)
    } while (abs(f-f1)>eps)
    println("Integral: $f")
    println("Number of partitions: $n")

    n=1
    f = sIntegral4(n,2.0,3.0)
    do{
        val f1 = f
        n *= 2
        f = sIntegral4(n,2.0,3.0)
    } while (abs(f-f1)>eps)
    println("Integral: $f")
    println("Number of partitions: $n")

    n=1
    f = sIntegral5(n,1.0,2.0)
    do{
        val f1 = f
        n *= 2
        f = sIntegral5(n,1.0,2.0)
    } while (abs(f-f1)>eps)
    println("Integral: $f")
    println("Number of partitions: $n")
}

fun in1(x:Double):Double{
    return sin(1/(x*x))
}
fun in2(x:Double):Double{
    return ln(x)*sin(1/(x*x*x))
}
fun in3(x:Double):Double{
    return exp(2*x)*cos(1/x)
}
fun in4(x:Double):Double{
    return (exp(x)/x)*sin(1/(x*x))
}
fun in5(x:Double):Double{
    return (3.0.pow(x)/(x*x))*ln(x*x)
}
fun sIntegral1(n:Int, a:Double, b:Double):Double{
    var sum = 0.0
    var sum2 = 0.0
    val x = Array(n,{1.0})
    val h=(b-a)/n
    for(i in 0 until n){
        x[i]=a+i*h
    }
    for(j in 1 until n){
        sum+=in1(x[j])
        sum2+=in1((x[j-1]+x[j])/2)
    }
    return h/6*(in1(a)+in1(b)+2*sum+4*(sum2+b))
}
fun sIntegral2(n:Int, a:Double, b:Double):Double{
    var sum = 0.0
    var sum2 = 0.0
    val x = Array(n,{1.0})
    val h=(b-a)/n
    for(i in 0 until n){
        x[i]=a+i*h
    }
    for(j in 1 until n){
        sum+=in2(x[j])
        sum2+=in2((x[j-1]+x[j])/2)
    }
    return h/6*(in2(a)+in2(b)+2*sum+4*(sum2+b))
}
fun sIntegral3(n:Int, a:Double, b:Double):Double{
    var sum = 0.0
    var sum2 = 0.0
    val x = Array(n,{1.0})
    val h=(b-a)/n
    for(i in 0 until n){
        x[i]=a+i*h
    }
    for(j in 1 until n){
        sum+=in3(x[j])
        sum2+=in3((x[j-1]+x[j])/2)
    }
    return h/6*(in3(a)+in3(b)+2*sum+4*(sum2+b))
}
fun sIntegral4(n:Int, a:Double, b:Double):Double{
    var sum = 0.0
    var sum2 = 0.0
    val x = Array(n,{1.0})
    val h=(b-a)/n
    for(i in 0 until n){
        x[i]=a+i*h
    }
    for(j in 1 until n){
        sum+=in4(x[j])
        sum2+=in4((x[j-1]+x[j])/2)
    }
    return h/6*(in4(a)+in4(b)+2*sum+4*(sum2+b))
}
fun sIntegral5(n:Int, a:Double, b:Double):Double{
    var sum = 0.0
    var sum2 = 0.0
    val x = Array(n,{1.0})
    val h=(b-a)/n
    for(i in 0 until n){
        x[i]=a+i*h
    }
    for(j in 1 until n){
        sum+=in5(x[j])
        sum2+=in5((x[j-1]+x[j])/2)
    }
    return h/6*(in5(a)+in5(b)+2*sum+4*(sum2+b))
}

//fun Trap(a: Double, b: Double, h: Double): Double {
//    var result = 0.0
//    val n = ((b - a) / h).toInt()
//    result += (f(a) + f(b)) / 2
//    for (i in 1 until n) result += f(a + h * i)
//    return h * result
//}