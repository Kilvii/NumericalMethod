import kotlin.math.pow

fun main(args: Array<String>) {
    val N = 101
    val h =0.1
    val result = tridiagonalMatrixAlgorithm(1.0,1.0/3.0,h,N)
    for(i in 0 .. N){
        println(result[i])
    }
}
fun tridiagonalMatrixAlgorithm(y0:Double,y_h:Double,h:Double, N:Int) :Array<Double>{
    val beta = Array(N+1,{0.0})
    val alpha = Array(N+1,{0.0})
    val y = Array(N+1,{1.0})
    val a = Array(N+1,{1.0})
    val b = Array(N+1,{1.0})
    val c = Array(N+1,{2.0})
    val F = Array(N+1,{0.0})

    alpha[1] = 0.0
    beta[1] = y0
    y[0] = y0
    y[N] = y_h

    for(i in 1 until N-1){
        F[i] = -current(i*h.pow(2))*h.pow(2)  //current(i*h.pow(2))
        alpha[i+1] = b[i]/(c[i]-alpha[i]*a[i])
        beta[i+1] = (a[i]*beta[i] + (-F[i]))/(c[i]-alpha[i]*a[i])
    }
    for(i in N-1 downTo 1){
        y[i] = alpha[i+1]*y[i+1]+beta[i+1]
    }

    return y
}

fun current(x:Double):Double{
    return x.pow(2)
}