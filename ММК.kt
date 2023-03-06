import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sin
import kotlin.random.Random

fun main(args: Array<String>) {
    val n = 10.0
    val N1 = n.pow(4)
    val N2 = n.pow(6)
    val N3 = n.pow(8)
    val a = arrayOf(1.0,2.0,1.0)
    val b = arrayOf(2.0,4.0,5.0)
    var mult = 1.0
    var sumF = 0.0

    for (i in 1 until N1.toInt()){
        val t= Random.nextDouble()
        val bufX = a[0]+(b[0]-a[0])*t
        sumF += in5(bufX)
    }
    for(i in 0..2){
        mult *= (b[i]-a[i])
    }
    mult /= N1

    println(sumF*mult)

}
fun in1(x:Double):Double{
    return x
}
fun in2(x:Double,y:Double):Double{
    return x*(y.pow(2))
}
fun in3(y:Double):Double{
    return sin(y)
}
fun in4(x:Double,z:Double):Double{
    return x*(z.pow(3))
}
fun in5(x:Double):Double{
    return x.pow(3)
}
fun in6(x:Double,y:Double):Double{
    return x*(exp(y))
}