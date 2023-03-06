import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.sin

fun main(){
    val n = arrayOf(2,3,4)
    val N = 100
    val a1 = 3.0
    val b1 = 5.0
    val b2 = 4.0
    val sumF = arrayOf(0.0,0.0,0.0)
    val sumS = arrayOf(0.0,0.0,0.0)
    for (k in n[0]..n[n.size-1]){
        when(k){
            2 -> for (i in 0 until N){
                sumF[0] += GaussIntegral1(k,(a1+i*(b1-a1)/N),a1+(i+1)*(b1-a1)/N)
            }

            3 -> for (i in 0 until N){
                sumF[1] += GaussIntegral1(k,(a1+i*(b1-a1)/N),a1+(i+1)*(b1-a1)/N)
            }
            else -> for (i in 0 until N){
                sumF[2] += GaussIntegral1(k,(a1+i*(b1-a1)/N),a1+(i+1)*(b1-a1)/N)
            }
        }
    }
    println("First Integral\nIntegral_1 at n = 2: ${sumF[0]}")
    println("Integral_1 at n = 3: ${sumF[1]}")
    println("\t|I2-I1| = ${abs(sumF[1]-sumF[0])}")
    println("Integral_1 at n = 4: ${sumF[2]}")
    println("\t|I3-I2| = ${abs(sumF[2]-sumF[1])} \n")

    for (k in n[0]..n[n.size-1]){
        when(k){
            2 -> for (i in 0 until N){
                sumS[0] += gaussIntegral2(k,(a1+i*(b2-a1)/N),a1+(i+1)*(b2-a1)/N)
            }
            3 -> for (i in 0 until N){
                sumS[1] += gaussIntegral2(k,(a1+i*(b2-a1)/N),a1+(i+1)*(b2-a1)/N)
            }
            else -> for (i in 0 until N){
                sumS[2] += gaussIntegral2(k,(a1+i*(b2-a1)/N),a1+(i+1)*(b2-a1)/N)
            }
        }
    }
    println("Second Integral\nIntegral_2 at n = 2: ${sumS[0]}")
    println("Integral_2 at n = 3: ${sumS[1]}")
    println("\t|I2-I1| = ${abs(sumS[1]-sumS[0])}")
    println("Integral_2 at n = 4: ${sumS[2]}")
    println("\t|I3-I2| = ${abs(sumS[2]-sumS[1])}")
}
fun in1(x:Double):Double{ //3-5
    return sin(1/(x*x))
}
fun in2(x:Double):Double{ //3-4
    return ln(x)*sin(1/(x*x*x))
}
fun GaussIntegral1(n:Int,a:Double,b:Double):Double {
    val X2 = arrayOf(-0.577350,0.577350)
    val C2 = arrayOf(1,1)
    val X3 = arrayOf(-0.7745967, 0.0, 0.7745967)
    val C3 = arrayOf(0.5555556,0.8888889,0.5555556)
    val X4 = arrayOf(-0.861136,-0.339981,0.339981 ,0.861136)
    val C4 = arrayOf(0.347855,0.652145,0.652145,0.347855)


    val part1 =(b-a)/2
    val part2 =(a+b)/2
    var nextElem: Double
    var summ = 0.0
    when (n) {
        2 -> {
            for (i in 0 until n) {
                nextElem = part2 + part1 * X2[i]
                summ += C2[i] * in1(nextElem)
            }
        }
        3 -> {
            for (i in 0 until n) {
                nextElem = part2 + part1 * X3[i]
                summ += C3[i] * in1(nextElem)
            }
        }
        else -> {
            for (i in 0 until n) {
                nextElem = part2 + part1 * X4[i]
                summ += C4[i] * in1(nextElem)
            }
        }
    }
    return part1*summ
}
fun gaussIntegral2(n:Int, a:Double, b:Double):Double {
    val X2 = arrayOf(-0.577350,0.577350)
    val C2 = arrayOf(1,1)
    val X3 = arrayOf(-0.7745967, 0.0, 0.7745967)
    val C3 = arrayOf(0.5555556,0.8888889,0.5555556)
    val X4 = arrayOf(-0.861136,-0.339981,0.339981 ,0.861136)
    val C4 = arrayOf(0.347855,0.652145,0.652145,0.347855)


    val part1 =(b-a)/2
    val part2 =(a+b)/2
    var nextElem: Double
    var summ = 0.0
    when (n) {
        2 -> {
            for (i in 0 until n) {
                nextElem = part2 + part1 * X2[i]
                summ += C2[i] * in2(nextElem)
            }
        }
        3 -> {
            for (i in 0 until n) {
                nextElem = part2 + part1 * X3[i]
                summ += C3[i] * in2(nextElem)
            }
        }
        else -> {
            for (i in 0 until n) {
                nextElem = part2 + part1 * X4[i]
                summ += C4[i] * in2(nextElem)
            }
        }
    }
    return part1*summ
}