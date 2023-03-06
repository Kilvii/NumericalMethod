import kotlin.math.exp
import kotlin.math.pow

fun main(args: Array<String>) {
    val h1 = 0.1
    val h2 = 0.01
    var h3 = 0.001

    var result = FiniteDifference(1.0,2.0,h3)

    for (i in 0 until result.size){
        println(result[i])
    }



}
fun FiniteDifference(y0:Double,y1_h:Double,h:Double):List<Double>{
    val N = 1/h
    val y1 = h*y1_h + y0
    var buff:Double
    var xNext = 0.0
    val yS = mutableListOf<Double>()
//    val yS1 = mutableListOf<Double>()
    yS.add(y0)
    yS.add(y1)

    for(i in 1 until N.toInt()){
        buff = -yS[i-1] + 2*yS[i]-h.pow(2)*yS[i]*(-4.0)
        yS.add(buff)
//        yS1.add(exp(2*xNext))
        xNext += h
    }

//    for (i in 0 until yS1.size){
//        println(yS1[i])
//    }
    println()
    return yS
}

