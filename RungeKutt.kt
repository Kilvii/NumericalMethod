import kotlin.math.pow

fun main(args: Array<String>) {
    val h1 = 0.1
    val h2 = 0.01
    var h3 = 0.001;

    val runge = RungeKuttCurr(1.0,0.0,h1)
    val exc = ExactFuncValue(1.0,h1)
    for (i in 0 until runge.size){
        println("${runge[i]}")
    }
    println()
//    for (i in 0 until exc.size){
//        println("${exc[i]}")
//    }
}
fun RungeKuttCurr(y0:Double,y1_H:Double, h:Double):List<Double>{
    val N = 1/h
    var buffY:Double
    var buffZ:Double
    var xNext = 0.0
    var q1:Double
    var q2:Double
    var q3:Double
    var k1:Double
    var k2:Double
    var k3:Double
    val yS = mutableListOf<Double>()
    val zS = mutableListOf<Double>()
    val y1 = y1_H*h + y0
    yS.add(y0)
    yS.add(y1)
    zS.add(y1_H)

    for(i in 1 until N.toInt()){
        xNext += h
        q1 = curr(xNext)
        k1 = zS[i-1]
        q2 = curr(xNext+h/2)
        k2 = zS[i-1]+q1*h/2
        q3 = curr(xNext+h)
        k3 = zS[i-1]+q3*h
        buffZ = zS[i-1] + h/6*(q1+4*q2+q3)
        zS.add(buffZ)
        buffY = yS[i] + h/6*(k1+4*k2+k3)
        yS.add(buffY)
    }
    return yS
}
fun ExactFuncValue(y0:Double,h:Double):List<Double>{
    val N = 1/h
    var buff:Double
    var xNext = 0.0
    val yS = mutableListOf<Double>()
    yS.add(y0)

    for(i in 1 .. N.toInt()){
        xNext += h
        buff = exact(xNext)
        yS.add(buff)
    }
    return yS
}
fun exact(x:Double): Double {
    return (x.pow(4)/12)+1
}
fun curr(x:Double): Double {
    return x.pow(2)
}